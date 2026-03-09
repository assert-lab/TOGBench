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
    @Test
    public void testConstructor() {
        assertNotNull(new CharSetUtils());
        final Constructor<?>[] cons = CharSetUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        assertTrue(Modifier.isPublic(CharSetUtils.class.getModifiers()));
        assertFalse(Modifier.isFinal(CharSetUtils.class.getModifiers()));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testSqueeze_StringString() {
        assertNull(CharSetUtils.squeeze(null, (String) null));
        assertNull(CharSetUtils.squeeze(null, ""));

        assertEquals("", CharSetUtils.squeeze("", (String) null));
        assertEquals("", CharSetUtils.squeeze("", ""));
        assertEquals("", CharSetUtils.squeeze("", "a-e"));

        assertEquals("hello", CharSetUtils.squeeze("hello", (String) null));
        assertEquals("hello", CharSetUtils.squeeze("hello", ""));
        assertEquals("hello", CharSetUtils.squeeze("hello", "a-e"));
        assertEquals("helo", CharSetUtils.squeeze("hello", "l-p"));
        assertEquals("heloo", CharSetUtils.squeeze("helloo", "l"));
        assertEquals("hello", CharSetUtils.squeeze("helloo", "^l"));
    }

    @Test
    public void testSqueeze_StringStringarray() {
        assertNull(CharSetUtils.squeeze(null, (String[]) null));
        assertNull(CharSetUtils.squeeze(null));
        assertNull(CharSetUtils.squeeze(null, null));
        assertNull(CharSetUtils.squeeze(null, "el"));

        assertEquals("", CharSetUtils.squeeze("", (String[]) null));
        assertEquals("", CharSetUtils.squeeze(""));
        assertEquals("", CharSetUtils.squeeze("", null));
        assertEquals("", CharSetUtils.squeeze("", "a-e"));

        assertEquals("hello", CharSetUtils.squeeze("hello", (String[]) null));
        assertEquals("hello", CharSetUtils.squeeze("hello"));
        assertEquals("hello", CharSetUtils.squeeze("hello", null));
        assertEquals("hello", CharSetUtils.squeeze("hello", "a-e"));

        assertEquals("helo", CharSetUtils.squeeze("hello", "el"));
        assertEquals("hello", CharSetUtils.squeeze("hello", "e"));
        assertEquals("fofof", CharSetUtils.squeeze("fooffooff", "of"));
        assertEquals("fof", CharSetUtils.squeeze("fooooff", "fo"));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testContainsAny_StringString() {
        assertFalse(CharSetUtils.containsAny(null, (String) null));
        assertFalse(CharSetUtils.containsAny(null, ""));

        assertFalse(CharSetUtils.containsAny("", (String) null));
        assertFalse(CharSetUtils.containsAny("", ""));
        assertFalse(CharSetUtils.containsAny("", "a-e"));

        assertFalse(CharSetUtils.containsAny("hello", (String) null));
        assertFalse(CharSetUtils.containsAny("hello", ""));
        assertTrue(CharSetUtils.containsAny("hello", "a-e"));
        assertTrue(CharSetUtils.containsAny("hello", "l-p"));
    }

    @Test
    public void testContainsAny_StringStringarray() {
        assertFalse(CharSetUtils.containsAny(null, (String[]) null));
        assertFalse(CharSetUtils.containsAny(null));
        assertFalse(CharSetUtils.containsAny(null, null));
        assertFalse(CharSetUtils.containsAny(null, "a-e"));

        assertFalse(CharSetUtils.containsAny("", (String[]) null));
        assertFalse(CharSetUtils.containsAny(""));
        assertFalse(CharSetUtils.containsAny("", null));
        assertFalse(CharSetUtils.containsAny("", "a-e"));

        assertFalse(CharSetUtils.containsAny("hello", (String[]) null));
        assertFalse(CharSetUtils.containsAny("hello"));
        assertFalse(CharSetUtils.containsAny("hello", null));
        assertTrue(CharSetUtils.containsAny("hello", "a-e"));

        assertTrue(CharSetUtils.containsAny("hello", "el"));
        assertFalse(CharSetUtils.containsAny("hello", "x"));
        assertTrue(CharSetUtils.containsAny("hello", "e-i"));
        assertTrue(CharSetUtils.containsAny("hello", "a-z"));
        assertFalse(CharSetUtils.containsAny("hello", ""));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testCount_StringString() {
        assertEquals(0, CharSetUtils.count(null, (String) null));
        assertEquals(0, CharSetUtils.count(null, ""));

        assertEquals(0, CharSetUtils.count("", (String) null));
        assertEquals(0, CharSetUtils.count("", ""));
        assertEquals(0, CharSetUtils.count("", "a-e"));

        assertEquals(0, CharSetUtils.count("hello", (String) null));
        assertEquals(0, CharSetUtils.count("hello", ""));
        assertEquals(1, CharSetUtils.count("hello", "a-e"));
        assertEquals(3, CharSetUtils.count("hello", "l-p"));
    }

    @Test
    public void testCount_StringStringarray() {
        assertEquals(0, CharSetUtils.count(null, (String[]) null));
        assertEquals(0, CharSetUtils.count(null));
        assertEquals(0, CharSetUtils.count(null, null));
        assertEquals(0, CharSetUtils.count(null, "a-e"));

        assertEquals(0, CharSetUtils.count("", (String[]) null));
        assertEquals(0, CharSetUtils.count(""));
        assertEquals(0, CharSetUtils.count("", null));
        assertEquals(0, CharSetUtils.count("", "a-e"));

        assertEquals(0, CharSetUtils.count("hello", (String[]) null));
        assertEquals(0, CharSetUtils.count("hello"));
        assertEquals(0, CharSetUtils.count("hello", null));
        assertEquals(1, CharSetUtils.count("hello", "a-e"));

        assertEquals(3, CharSetUtils.count("hello", "el"));
        assertEquals(0, CharSetUtils.count("hello", "x"));
        assertEquals(2, CharSetUtils.count("hello", "e-i"));
        assertEquals(5, CharSetUtils.count("hello", "a-z"));
        assertEquals(0, CharSetUtils.count("hello", ""));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testKeep_StringString() {
        assertNull(CharSetUtils.keep(null, (String) null));
        assertNull(CharSetUtils.keep(null, ""));

        assertEquals("", CharSetUtils.keep("", (String) null));
        assertEquals("", CharSetUtils.keep("", ""));
        assertEquals("", CharSetUtils.keep("", "a-e"));

        assertEquals("", CharSetUtils.keep("hello", (String) null));
        assertEquals("", CharSetUtils.keep("hello", ""));
        assertEquals("", CharSetUtils.keep("hello", "xyz"));
        assertEquals("hello", CharSetUtils.keep("hello", "a-z"));
        assertEquals("hello", CharSetUtils.keep("hello", "oleh"));
        assertEquals("ell", CharSetUtils.keep("hello", "el"));
    }

    @Test
    public void testKeep_StringStringarray() {
        assertNull(CharSetUtils.keep(null, (String[]) null));
        assertNull(CharSetUtils.keep(null));
        assertNull(CharSetUtils.keep(null, null));
        assertNull(CharSetUtils.keep(null, "a-e"));

        assertEquals("", CharSetUtils.keep("", (String[]) null));
        assertEquals("", CharSetUtils.keep(""));
        assertEquals("", CharSetUtils.keep("", null));
        assertEquals("", CharSetUtils.keep("", "a-e"));

        assertEquals("", CharSetUtils.keep("hello", (String[]) null));
        assertEquals("", CharSetUtils.keep("hello"));
        assertEquals("", CharSetUtils.keep("hello", null));
        assertEquals("e", CharSetUtils.keep("hello", "a-e"));

        assertEquals("e", CharSetUtils.keep("hello", "a-e"));
        assertEquals("ell", CharSetUtils.keep("hello", "el"));
        assertEquals("hello", CharSetUtils.keep("hello", "elho"));
        assertEquals("hello", CharSetUtils.keep("hello", "a-z"));
        assertEquals("----", CharSetUtils.keep("----", "-"));
        assertEquals("ll", CharSetUtils.keep("hello", "l"));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testDelete_StringString() {
        assertNull(CharSetUtils.delete(null, (String) null));
        assertNull(CharSetUtils.delete(null, ""));

        assertEquals("", CharSetUtils.delete("", (String) null));
        assertEquals("", CharSetUtils.delete("", ""));
        assertEquals("", CharSetUtils.delete("", "a-e"));

        assertEquals("hello", CharSetUtils.delete("hello", (String) null));
        assertEquals("hello", CharSetUtils.delete("hello", ""));
        assertEquals("hllo", CharSetUtils.delete("hello", "a-e"));
        assertEquals("he", CharSetUtils.delete("hello", "l-p"));
        assertEquals("hello", CharSetUtils.delete("hello", "z"));
    }

    @Test
    public void testDelete_StringStringarray() {
        assertNull(CharSetUtils.delete(null, (String[]) null));
        assertNull(CharSetUtils.delete(null));
        assertNull(CharSetUtils.delete(null, null));
        assertNull(CharSetUtils.delete(null, "el"));

        assertEquals("", CharSetUtils.delete("", (String[]) null));
        assertEquals("", CharSetUtils.delete(""));
        assertEquals("", CharSetUtils.delete("", null));
        assertEquals("", CharSetUtils.delete("", "a-e"));

        assertEquals("hello", CharSetUtils.delete("hello", (String[]) null));
        assertEquals("hello", CharSetUtils.delete("hello"));
        assertEquals("hello", CharSetUtils.delete("hello", null));
        assertEquals("hello", CharSetUtils.delete("hello", "xyz"));

        assertEquals("ho", CharSetUtils.delete("hello", "el"));
        assertEquals("", CharSetUtils.delete("hello", "elho"));
        assertEquals("hello", CharSetUtils.delete("hello", ""));
        assertEquals("hello", CharSetUtils.delete("hello", ""));
        assertEquals("", CharSetUtils.delete("hello", "a-z"));
        assertEquals("", CharSetUtils.delete("----", "-"));
        assertEquals("heo", CharSetUtils.delete("hello", "l"));
    }

    @Test
    public void testConstructor_1_oe() {
        assertNotNull(new CharSetUtils());
    }

    @Test
    public void testConstructor_2_oe() {
        final Constructor<?>[] cons = CharSetUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
    }

    @Test
    public void testConstructor_3_oe() {
        final Constructor<?>[] cons = CharSetUtils.class.getDeclaredConstructors();
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
    }

    @Test
    public void testConstructor_4_oe() {
        final Constructor<?>[] cons = CharSetUtils.class.getDeclaredConstructors();
        assertTrue(Modifier.isPublic(CharSetUtils.class.getModifiers()));
    }

    @Test
    public void testConstructor_5_oe() {
        final Constructor<?>[] cons = CharSetUtils.class.getDeclaredConstructors();
        assertFalse(Modifier.isFinal(CharSetUtils.class.getModifiers()));
    }

    @Test
    public void testSqueeze_StringString_1_oe() {
        assertNull(CharSetUtils.squeeze(null, (String) null));
    }

    @Test
    public void testSqueeze_StringString_2_oe() {
        assertNull(CharSetUtils.squeeze(null, ""));
    }

    @Test
    public void testSqueeze_StringString_3_oe() {

        assertEquals("", CharSetUtils.squeeze("", (String) null));
    }

    @Test
    public void testSqueeze_StringString_4_oe() {

        assertEquals("", CharSetUtils.squeeze("", ""));
    }

    @Test
    public void testSqueeze_StringString_5_oe() {

        assertEquals("", CharSetUtils.squeeze("", "a-e"));
    }

    @Test
    public void testSqueeze_StringString_6_oe() {


        assertEquals("hello", CharSetUtils.squeeze("hello", (String) null));
    }

    @Test
    public void testSqueeze_StringString_7_oe() {


        assertEquals("hello", CharSetUtils.squeeze("hello", ""));
    }

    @Test
    public void testSqueeze_StringString_8_oe() {


        assertEquals("hello", CharSetUtils.squeeze("hello", "a-e"));
    }

    @Test
    public void testSqueeze_StringString_9_oe() {


        assertEquals("helo", CharSetUtils.squeeze("hello", "l-p"));
    }

    @Test
    public void testSqueeze_StringString_10_oe() {


        assertEquals("heloo", CharSetUtils.squeeze("helloo", "l"));
    }

    @Test
    public void testSqueeze_StringString_11_oe() {


        assertEquals("hello", CharSetUtils.squeeze("helloo", "^l"));
    }

    @Test
    public void testSqueeze_StringStringarray_1_oe() {
        assertNull(CharSetUtils.squeeze(null, (String[]) null));
    }

    @Test
    public void testSqueeze_StringStringarray_2_oe() {
        assertNull(CharSetUtils.squeeze(null));
    }

    @Test
    public void testSqueeze_StringStringarray_3_oe() {
        assertNull(CharSetUtils.squeeze(null, null));
    }

    @Test
    public void testSqueeze_StringStringarray_4_oe() {
        assertNull(CharSetUtils.squeeze(null, "el"));
    }

    @Test
    public void testSqueeze_StringStringarray_5_oe() {

        assertEquals("", CharSetUtils.squeeze("", (String[]) null));
    }

    @Test
    public void testSqueeze_StringStringarray_6_oe() {

        assertEquals("", CharSetUtils.squeeze(""));
    }

    @Test
    public void testSqueeze_StringStringarray_7_oe() {

        assertEquals("", CharSetUtils.squeeze("", null));
    }

    @Test
    public void testSqueeze_StringStringarray_8_oe() {

        assertEquals("", CharSetUtils.squeeze("", "a-e"));
    }

    @Test
    public void testSqueeze_StringStringarray_9_oe() {


        assertEquals("hello", CharSetUtils.squeeze("hello", (String[]) null));
    }

    @Test
    public void testSqueeze_StringStringarray_10_oe() {


        assertEquals("hello", CharSetUtils.squeeze("hello"));
    }

    @Test
    public void testSqueeze_StringStringarray_11_oe() {


        assertEquals("hello", CharSetUtils.squeeze("hello", null));
    }

    @Test
    public void testSqueeze_StringStringarray_12_oe() {


        assertEquals("hello", CharSetUtils.squeeze("hello", "a-e"));
    }

    @Test
    public void testSqueeze_StringStringarray_13_oe() {



        assertEquals("helo", CharSetUtils.squeeze("hello", "el"));
    }

    @Test
    public void testSqueeze_StringStringarray_14_oe() {



        assertEquals("hello", CharSetUtils.squeeze("hello", "e"));
    }

    @Test
    public void testSqueeze_StringStringarray_15_oe() {



        assertEquals("fofof", CharSetUtils.squeeze("fooffooff", "of"));
    }

    @Test
    public void testSqueeze_StringStringarray_16_oe() {



        assertEquals("fof", CharSetUtils.squeeze("fooooff", "fo"));
    }

    @Test
    public void testContainsAny_StringString_1_oe() {
        assertFalse(CharSetUtils.containsAny(null, (String) null));
    }

    @Test
    public void testContainsAny_StringString_2_oe() {
        assertFalse(CharSetUtils.containsAny(null, ""));
    }

    @Test
    public void testContainsAny_StringString_3_oe() {

        assertFalse(CharSetUtils.containsAny("", (String) null));
    }

    @Test
    public void testContainsAny_StringString_4_oe() {

        assertFalse(CharSetUtils.containsAny("", ""));
    }

    @Test
    public void testContainsAny_StringString_5_oe() {

        assertFalse(CharSetUtils.containsAny("", "a-e"));
    }

    @Test
    public void testContainsAny_StringString_6_oe() {


        assertFalse(CharSetUtils.containsAny("hello", (String) null));
    }

    @Test
    public void testContainsAny_StringString_7_oe() {


        assertFalse(CharSetUtils.containsAny("hello", ""));
    }

    @Test
    public void testContainsAny_StringString_8_oe() {


        assertTrue(CharSetUtils.containsAny("hello", "a-e"));
    }

    @Test
    public void testContainsAny_StringString_9_oe() {


        assertTrue(CharSetUtils.containsAny("hello", "l-p"));
    }

    @Test
    public void testContainsAny_StringStringarray_1_oe() {
        assertFalse(CharSetUtils.containsAny(null, (String[]) null));
    }

    @Test
    public void testContainsAny_StringStringarray_2_oe() {
        assertFalse(CharSetUtils.containsAny(null));
    }

    @Test
    public void testContainsAny_StringStringarray_3_oe() {
        assertFalse(CharSetUtils.containsAny(null, null));
    }

    @Test
    public void testContainsAny_StringStringarray_4_oe() {
        assertFalse(CharSetUtils.containsAny(null, "a-e"));
    }

    @Test
    public void testContainsAny_StringStringarray_5_oe() {

        assertFalse(CharSetUtils.containsAny("", (String[]) null));
    }

    @Test
    public void testContainsAny_StringStringarray_6_oe() {

        assertFalse(CharSetUtils.containsAny(""));
    }

    @Test
    public void testContainsAny_StringStringarray_7_oe() {

        assertFalse(CharSetUtils.containsAny("", null));
    }

    @Test
    public void testContainsAny_StringStringarray_8_oe() {

        assertFalse(CharSetUtils.containsAny("", "a-e"));
    }

    @Test
    public void testContainsAny_StringStringarray_9_oe() {


        assertFalse(CharSetUtils.containsAny("hello", (String[]) null));
    }

    @Test
    public void testContainsAny_StringStringarray_10_oe() {


        assertFalse(CharSetUtils.containsAny("hello"));
    }

    @Test
    public void testContainsAny_StringStringarray_11_oe() {


        assertFalse(CharSetUtils.containsAny("hello", null));
    }

    @Test
    public void testContainsAny_StringStringarray_12_oe() {


        assertTrue(CharSetUtils.containsAny("hello", "a-e"));
    }

    @Test
    public void testContainsAny_StringStringarray_13_oe() {



        assertTrue(CharSetUtils.containsAny("hello", "el"));
    }

    @Test
    public void testContainsAny_StringStringarray_14_oe() {



        assertFalse(CharSetUtils.containsAny("hello", "x"));
    }

    @Test
    public void testContainsAny_StringStringarray_15_oe() {



        assertTrue(CharSetUtils.containsAny("hello", "e-i"));
    }

    @Test
    public void testContainsAny_StringStringarray_16_oe() {



        assertTrue(CharSetUtils.containsAny("hello", "a-z"));
    }

    @Test
    public void testContainsAny_StringStringarray_17_oe() {



        assertFalse(CharSetUtils.containsAny("hello", ""));
    }

    @Test
    public void testCount_StringString_1_oe() {
        assertEquals(0, CharSetUtils.count(null, (String) null));
    }

    @Test
    public void testCount_StringString_2_oe() {
        assertEquals(0, CharSetUtils.count(null, ""));
    }

    @Test
    public void testCount_StringString_3_oe() {

        assertEquals(0, CharSetUtils.count("", (String) null));
    }

    @Test
    public void testCount_StringString_4_oe() {

        assertEquals(0, CharSetUtils.count("", ""));
    }

    @Test
    public void testCount_StringString_5_oe() {

        assertEquals(0, CharSetUtils.count("", "a-e"));
    }

    @Test
    public void testCount_StringString_6_oe() {


        assertEquals(0, CharSetUtils.count("hello", (String) null));
    }

    @Test
    public void testCount_StringString_7_oe() {


        assertEquals(0, CharSetUtils.count("hello", ""));
    }

    @Test
    public void testCount_StringString_8_oe() {


        assertEquals(1, CharSetUtils.count("hello", "a-e"));
    }

    @Test
    public void testCount_StringString_9_oe() {


        assertEquals(3, CharSetUtils.count("hello", "l-p"));
    }

    @Test
    public void testCount_StringStringarray_1_oe() {
        assertEquals(0, CharSetUtils.count(null, (String[]) null));
    }

    @Test
    public void testCount_StringStringarray_2_oe() {
        assertEquals(0, CharSetUtils.count(null));
    }

    @Test
    public void testCount_StringStringarray_3_oe() {
        assertEquals(0, CharSetUtils.count(null, null));
    }

    @Test
    public void testCount_StringStringarray_4_oe() {
        assertEquals(0, CharSetUtils.count(null, "a-e"));
    }

    @Test
    public void testCount_StringStringarray_5_oe() {

        assertEquals(0, CharSetUtils.count("", (String[]) null));
    }

    @Test
    public void testCount_StringStringarray_6_oe() {

        assertEquals(0, CharSetUtils.count(""));
    }

    @Test
    public void testCount_StringStringarray_7_oe() {

        assertEquals(0, CharSetUtils.count("", null));
    }

    @Test
    public void testCount_StringStringarray_8_oe() {

        assertEquals(0, CharSetUtils.count("", "a-e"));
    }

    @Test
    public void testCount_StringStringarray_9_oe() {


        assertEquals(0, CharSetUtils.count("hello", (String[]) null));
    }

    @Test
    public void testCount_StringStringarray_10_oe() {


        assertEquals(0, CharSetUtils.count("hello"));
    }

    @Test
    public void testCount_StringStringarray_11_oe() {


        assertEquals(0, CharSetUtils.count("hello", null));
    }

    @Test
    public void testCount_StringStringarray_12_oe() {


        assertEquals(1, CharSetUtils.count("hello", "a-e"));
    }

    @Test
    public void testCount_StringStringarray_13_oe() {



        assertEquals(3, CharSetUtils.count("hello", "el"));
    }

    @Test
    public void testCount_StringStringarray_14_oe() {



        assertEquals(0, CharSetUtils.count("hello", "x"));
    }

    @Test
    public void testCount_StringStringarray_15_oe() {



        assertEquals(2, CharSetUtils.count("hello", "e-i"));
    }

    @Test
    public void testCount_StringStringarray_16_oe() {



        assertEquals(5, CharSetUtils.count("hello", "a-z"));
    }

    @Test
    public void testCount_StringStringarray_17_oe() {



        assertEquals(0, CharSetUtils.count("hello", ""));
    }

    @Test
    public void testKeep_StringString_1_oe() {
        assertNull(CharSetUtils.keep(null, (String) null));
    }

    @Test
    public void testKeep_StringString_2_oe() {
        assertNull(CharSetUtils.keep(null, ""));
    }

    @Test
    public void testKeep_StringString_3_oe() {

        assertEquals("", CharSetUtils.keep("", (String) null));
    }

    @Test
    public void testKeep_StringString_4_oe() {

        assertEquals("", CharSetUtils.keep("", ""));
    }

    @Test
    public void testKeep_StringString_5_oe() {

        assertEquals("", CharSetUtils.keep("", "a-e"));
    }

    @Test
    public void testKeep_StringString_6_oe() {


        assertEquals("", CharSetUtils.keep("hello", (String) null));
    }

    @Test
    public void testKeep_StringString_7_oe() {


        assertEquals("", CharSetUtils.keep("hello", ""));
    }

    @Test
    public void testKeep_StringString_8_oe() {


        assertEquals("", CharSetUtils.keep("hello", "xyz"));
    }

    @Test
    public void testKeep_StringString_9_oe() {


        assertEquals("hello", CharSetUtils.keep("hello", "a-z"));
    }

    @Test
    public void testKeep_StringString_10_oe() {


        assertEquals("hello", CharSetUtils.keep("hello", "oleh"));
    }

    @Test
    public void testKeep_StringString_11_oe() {


        assertEquals("ell", CharSetUtils.keep("hello", "el"));
    }

    @Test
    public void testKeep_StringStringarray_1_oe() {
        assertNull(CharSetUtils.keep(null, (String[]) null));
    }

    @Test
    public void testKeep_StringStringarray_2_oe() {
        assertNull(CharSetUtils.keep(null));
    }

    @Test
    public void testKeep_StringStringarray_3_oe() {
        assertNull(CharSetUtils.keep(null, null));
    }

    @Test
    public void testKeep_StringStringarray_4_oe() {
        assertNull(CharSetUtils.keep(null, "a-e"));
    }

    @Test
    public void testKeep_StringStringarray_5_oe() {

        assertEquals("", CharSetUtils.keep("", (String[]) null));
    }

    @Test
    public void testKeep_StringStringarray_6_oe() {

        assertEquals("", CharSetUtils.keep(""));
    }

    @Test
    public void testKeep_StringStringarray_7_oe() {

        assertEquals("", CharSetUtils.keep("", null));
    }

    @Test
    public void testKeep_StringStringarray_8_oe() {

        assertEquals("", CharSetUtils.keep("", "a-e"));
    }

    @Test
    public void testKeep_StringStringarray_9_oe() {


        assertEquals("", CharSetUtils.keep("hello", (String[]) null));
    }

    @Test
    public void testKeep_StringStringarray_10_oe() {


        assertEquals("", CharSetUtils.keep("hello"));
    }

    @Test
    public void testKeep_StringStringarray_11_oe() {


        assertEquals("", CharSetUtils.keep("hello", null));
    }

    @Test
    public void testKeep_StringStringarray_12_oe() {


        assertEquals("e", CharSetUtils.keep("hello", "a-e"));
    }

    @Test
    public void testKeep_StringStringarray_13_oe() {



        assertEquals("e", CharSetUtils.keep("hello", "a-e"));
    }

    @Test
    public void testKeep_StringStringarray_14_oe() {



        assertEquals("ell", CharSetUtils.keep("hello", "el"));
    }

    @Test
    public void testKeep_StringStringarray_15_oe() {



        assertEquals("hello", CharSetUtils.keep("hello", "elho"));
    }

    @Test
    public void testKeep_StringStringarray_16_oe() {



        assertEquals("hello", CharSetUtils.keep("hello", "a-z"));
    }

    @Test
    public void testKeep_StringStringarray_17_oe() {



        assertEquals("----", CharSetUtils.keep("----", "-"));
    }

    @Test
    public void testKeep_StringStringarray_18_oe() {



        assertEquals("ll", CharSetUtils.keep("hello", "l"));
    }

    @Test
    public void testDelete_StringString_1_oe() {
        assertNull(CharSetUtils.delete(null, (String) null));
    }

    @Test
    public void testDelete_StringString_2_oe() {
        assertNull(CharSetUtils.delete(null, ""));
    }

    @Test
    public void testDelete_StringString_3_oe() {

        assertEquals("", CharSetUtils.delete("", (String) null));
    }

    @Test
    public void testDelete_StringString_4_oe() {

        assertEquals("", CharSetUtils.delete("", ""));
    }

    @Test
    public void testDelete_StringString_5_oe() {

        assertEquals("", CharSetUtils.delete("", "a-e"));
    }

    @Test
    public void testDelete_StringString_6_oe() {


        assertEquals("hello", CharSetUtils.delete("hello", (String) null));
    }

    @Test
    public void testDelete_StringString_7_oe() {


        assertEquals("hello", CharSetUtils.delete("hello", ""));
    }

    @Test
    public void testDelete_StringString_8_oe() {


        assertEquals("hllo", CharSetUtils.delete("hello", "a-e"));
    }

    @Test
    public void testDelete_StringString_9_oe() {


        assertEquals("he", CharSetUtils.delete("hello", "l-p"));
    }

    @Test
    public void testDelete_StringString_10_oe() {


        assertEquals("hello", CharSetUtils.delete("hello", "z"));
    }

    @Test
    public void testDelete_StringStringarray_1_oe() {
        assertNull(CharSetUtils.delete(null, (String[]) null));
    }

    @Test
    public void testDelete_StringStringarray_2_oe() {
        assertNull(CharSetUtils.delete(null));
    }

    @Test
    public void testDelete_StringStringarray_3_oe() {
        assertNull(CharSetUtils.delete(null, null));
    }

    @Test
    public void testDelete_StringStringarray_4_oe() {
        assertNull(CharSetUtils.delete(null, "el"));
    }

    @Test
    public void testDelete_StringStringarray_5_oe() {

        assertEquals("", CharSetUtils.delete("", (String[]) null));
    }

    @Test
    public void testDelete_StringStringarray_6_oe() {

        assertEquals("", CharSetUtils.delete(""));
    }

    @Test
    public void testDelete_StringStringarray_7_oe() {

        assertEquals("", CharSetUtils.delete("", null));
    }

    @Test
    public void testDelete_StringStringarray_8_oe() {

        assertEquals("", CharSetUtils.delete("", "a-e"));
    }

    @Test
    public void testDelete_StringStringarray_9_oe() {


        assertEquals("hello", CharSetUtils.delete("hello", (String[]) null));
    }

    @Test
    public void testDelete_StringStringarray_10_oe() {


        assertEquals("hello", CharSetUtils.delete("hello"));
    }

    @Test
    public void testDelete_StringStringarray_11_oe() {


        assertEquals("hello", CharSetUtils.delete("hello", null));
    }

    @Test
    public void testDelete_StringStringarray_12_oe() {


        assertEquals("hello", CharSetUtils.delete("hello", "xyz"));
    }

    @Test
    public void testDelete_StringStringarray_13_oe() {



        assertEquals("ho", CharSetUtils.delete("hello", "el"));
    }

    @Test
    public void testDelete_StringStringarray_14_oe() {



        assertEquals("", CharSetUtils.delete("hello", "elho"));
    }

    @Test
    public void testDelete_StringStringarray_15_oe() {



        assertEquals("hello", CharSetUtils.delete("hello", ""));
    }

    @Test
    public void testDelete_StringStringarray_16_oe() {



        assertEquals("hello", CharSetUtils.delete("hello", ""));
    }

    @Test
    public void testDelete_StringStringarray_17_oe() {



        assertEquals("", CharSetUtils.delete("hello", "a-z"));
    }

    @Test
    public void testDelete_StringStringarray_18_oe() {



        assertEquals("", CharSetUtils.delete("----", "-"));
    }

    @Test
    public void testDelete_StringStringarray_19_oe() {



        assertEquals("heo", CharSetUtils.delete("hello", "l"));
    }

}
