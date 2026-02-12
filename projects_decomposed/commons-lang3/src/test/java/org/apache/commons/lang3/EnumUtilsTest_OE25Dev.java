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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 *
 */
public class EnumUtilsTest_OE25Dev {

    @Test
    public void testConstructable() {
        // enforce public constructor
        new EnumUtils();
    }

    private void assertArrayEquals(final long[] actual, final long... expected) {
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void test_getEnumMap_1_oe() {
        final Map<String, Traffic> test = EnumUtils.getEnumMap(Traffic.class);
        assertEquals("{RED=RED, AMBER=AMBER, GREEN=GREEN}", test.toString(), "getEnumMap not created correctly");
    }

    @Test
    public void test_getEnumMap_2_oe() {
        final Map<String, Traffic> test = EnumUtils.getEnumMap(Traffic.class);
        // removed other assertion
        assertEquals(3, test.size());
    }

    @Test
    public void test_getEnumMap_3_oe() {
        final Map<String, Traffic> test = EnumUtils.getEnumMap(Traffic.class);
        // removed other assertion
        // removed other assertion
        assertTrue(test.containsKey("RED"));
    }

    @Test
    public void test_getEnumMap_4_oe() {
        final Map<String, Traffic> test = EnumUtils.getEnumMap(Traffic.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.RED, test.get("RED"));
    }

    @Test
    public void test_getEnumMap_5_oe() {
        final Map<String, Traffic> test = EnumUtils.getEnumMap(Traffic.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(test.containsKey("AMBER"));
    }

    @Test
    public void test_getEnumMap_6_oe() {
        final Map<String, Traffic> test = EnumUtils.getEnumMap(Traffic.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.AMBER, test.get("AMBER"));
    }

    @Test
    public void test_getEnumMap_7_oe() {
        final Map<String, Traffic> test = EnumUtils.getEnumMap(Traffic.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(test.containsKey("GREEN"));
    }

    @Test
    public void test_getEnumMap_8_oe() {
        final Map<String, Traffic> test = EnumUtils.getEnumMap(Traffic.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.GREEN, test.get("GREEN"));
    }

    @Test
    public void test_getEnumMap_9_oe() {
        final Map<String, Traffic> test = EnumUtils.getEnumMap(Traffic.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(test.containsKey("PURPLE"));
    }

    @Test
    public void test_getEnumList_1_oe() {
        final List<Traffic> test = EnumUtils.getEnumList(Traffic.class);
        assertEquals(3, test.size());
    }

    @Test
    public void test_getEnumList_2_oe() {
        final List<Traffic> test = EnumUtils.getEnumList(Traffic.class);
        // removed other assertion
        assertEquals(Traffic.RED, test.get(0));
    }

    @Test
    public void test_getEnumList_3_oe() {
        final List<Traffic> test = EnumUtils.getEnumList(Traffic.class);
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.AMBER, test.get(1));
    }

    @Test
    public void test_getEnumList_4_oe() {
        final List<Traffic> test = EnumUtils.getEnumList(Traffic.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.GREEN, test.get(2));
    }

    @Test
    public void test_isValidEnum_1_oe() {
        assertTrue(EnumUtils.isValidEnum(Traffic.class, "RED"));
    }

    @Test
    public void test_isValidEnum_2_oe() {
        // removed other assertion
        assertTrue(EnumUtils.isValidEnum(Traffic.class, "AMBER"));
    }

    @Test
    public void test_isValidEnum_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(EnumUtils.isValidEnum(Traffic.class, "GREEN"));
    }

    @Test
    public void test_isValidEnum_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(EnumUtils.isValidEnum(Traffic.class, "PURPLE"));
    }

    @Test
    public void test_isValidEnum_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(EnumUtils.isValidEnum(Traffic.class, null));
    }

    @Test
    public void test_isValidEnum_nullClass_1_oe() throws Exception {
        try {
    EnumUtils.isValidEnum(null, "PURPLE");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_isValidEnumIgnoreCase_1_oe() {
        assertTrue(EnumUtils.isValidEnumIgnoreCase(Traffic.class, "red"));
    }

    @Test
    public void test_isValidEnumIgnoreCase_2_oe() {
        // removed other assertion
        assertTrue(EnumUtils.isValidEnumIgnoreCase(Traffic.class, "Amber"));
    }

    @Test
    public void test_isValidEnumIgnoreCase_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(EnumUtils.isValidEnumIgnoreCase(Traffic.class, "grEEn"));
    }

    @Test
    public void test_isValidEnumIgnoreCase_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(EnumUtils.isValidEnumIgnoreCase(Traffic.class, "purple"));
    }

    @Test
    public void test_isValidEnumIgnoreCase_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(EnumUtils.isValidEnumIgnoreCase(Traffic.class, null));
    }

    @Test
    public void test_isValidEnumIgnoreCase_nullClass_1_oe() throws Exception {
        try {
    EnumUtils.isValidEnumIgnoreCase(null, "PURPLE");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_getEnum_1_oe() {
        assertEquals(Traffic.RED, EnumUtils.getEnum(Traffic.class, "RED"));
    }

    @Test
    public void test_getEnum_2_oe() {
        // removed other assertion
        assertEquals(Traffic.AMBER, EnumUtils.getEnum(Traffic.class, "AMBER"));
    }

    @Test
    public void test_getEnum_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.GREEN, EnumUtils.getEnum(Traffic.class, "GREEN"));
    }

    @Test
    public void test_getEnum_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(EnumUtils.getEnum(Traffic.class, "PURPLE"));
    }

    @Test
    public void test_getEnum_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(EnumUtils.getEnum(Traffic.class, null));
    }

    @Test
    public void test_getEnum_nonEnumClass_1_oe() {
        final Class rawType = Object.class;
        assertNull(EnumUtils.getEnum(rawType, "rawType"));
    }

    @Test
    public void test_getEnum_nullClass_1_oe() throws Exception {
        try {
    EnumUtils.getEnum((Class<Traffic>) null, "PURPLE");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_getEnum_defaultEnum_1_oe() {
        assertEquals(Traffic.RED, EnumUtils.getEnum(Traffic.class, "RED", Traffic.AMBER));
    }

    @Test
    public void test_getEnum_defaultEnum_2_oe() {
        // removed other assertion
        assertEquals(Traffic.AMBER, EnumUtils.getEnum(Traffic.class, "AMBER", Traffic.GREEN));
    }

    @Test
    public void test_getEnum_defaultEnum_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.GREEN, EnumUtils.getEnum(Traffic.class, "GREEN", Traffic.RED));
    }

    @Test
    public void test_getEnum_defaultEnum_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.AMBER, EnumUtils.getEnum(Traffic.class, "PURPLE", Traffic.AMBER));
    }

    @Test
    public void test_getEnum_defaultEnum_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.GREEN, EnumUtils.getEnum(Traffic.class, "PURPLE", Traffic.GREEN));
    }

    @Test
    public void test_getEnum_defaultEnum_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.RED, EnumUtils.getEnum(Traffic.class, "PURPLE", Traffic.RED));
    }

    @Test
    public void test_getEnum_defaultEnum_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.AMBER, EnumUtils.getEnum(Traffic.class, null, Traffic.AMBER));
    }

    @Test
    public void test_getEnum_defaultEnum_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.GREEN, EnumUtils.getEnum(Traffic.class, null, Traffic.GREEN));
    }

    @Test
    public void test_getEnum_defaultEnum_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.RED, EnumUtils.getEnum(Traffic.class, null, Traffic.RED));
    }

    @Test
    public void test_getEnum_defaultEnum_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(EnumUtils.getEnum(Traffic.class, "PURPLE", null));
    }

    @Test
    public void test_getEnumIgnoreCase_1_oe() {
        assertEquals(Traffic.RED, EnumUtils.getEnumIgnoreCase(Traffic.class, "red"));
    }

    @Test
    public void test_getEnumIgnoreCase_2_oe() {
        // removed other assertion
        assertEquals(Traffic.AMBER, EnumUtils.getEnumIgnoreCase(Traffic.class, "Amber"));
    }

    @Test
    public void test_getEnumIgnoreCase_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.GREEN, EnumUtils.getEnumIgnoreCase(Traffic.class, "grEEn"));
    }

    @Test
    public void test_getEnumIgnoreCase_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(EnumUtils.getEnumIgnoreCase(Traffic.class, "purple"));
    }

    @Test
    public void test_getEnumIgnoreCase_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(EnumUtils.getEnumIgnoreCase(Traffic.class, null));
    }

    @Test
    public void test_getEnumIgnoreCase_nonEnumClass_1_oe() {
        final Class rawType = Object.class;
        assertNull(EnumUtils.getEnumIgnoreCase(rawType, "rawType"));
    }

    @Test
    public void test_getEnumIgnoreCase_nullClass_1_oe() throws Exception {
        try {
    EnumUtils.getEnumIgnoreCase((Class<Traffic>) null, "PURPLE");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_getEnumIgnoreCase_defaultEnum_1_oe() {
        assertEquals(Traffic.RED, EnumUtils.getEnumIgnoreCase(Traffic.class, "red", Traffic.AMBER));
    }

    @Test
    public void test_getEnumIgnoreCase_defaultEnum_2_oe() {
        // removed other assertion
        assertEquals(Traffic.AMBER, EnumUtils.getEnumIgnoreCase(Traffic.class, "Amber", Traffic.GREEN));
    }

    @Test
    public void test_getEnumIgnoreCase_defaultEnum_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.GREEN, EnumUtils.getEnumIgnoreCase(Traffic.class, "grEEn", Traffic.RED));
    }

    @Test
    public void test_getEnumIgnoreCase_defaultEnum_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.AMBER, EnumUtils.getEnumIgnoreCase(Traffic.class, "PURPLE", Traffic.AMBER));
    }

    @Test
    public void test_getEnumIgnoreCase_defaultEnum_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.GREEN, EnumUtils.getEnumIgnoreCase(Traffic.class, "purple", Traffic.GREEN));
    }

    @Test
    public void test_getEnumIgnoreCase_defaultEnum_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.RED, EnumUtils.getEnumIgnoreCase(Traffic.class, "pUrPlE", Traffic.RED));
    }

    @Test
    public void test_getEnumIgnoreCase_defaultEnum_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.AMBER, EnumUtils.getEnumIgnoreCase(Traffic.class, null, Traffic.AMBER));
    }

    @Test
    public void test_getEnumIgnoreCase_defaultEnum_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.GREEN, EnumUtils.getEnumIgnoreCase(Traffic.class, null, Traffic.GREEN));
    }

    @Test
    public void test_getEnumIgnoreCase_defaultEnum_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Traffic.RED, EnumUtils.getEnumIgnoreCase(Traffic.class, null, Traffic.RED));
    }

    @Test
    public void test_getEnumIgnoreCase_defaultEnum_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(EnumUtils.getEnumIgnoreCase(Traffic.class, "PURPLE", null));
    }

    @Test
    public void test_generateBitVector_nullClass_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVector(null, EnumSet.of(Traffic.RED));
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVectors_nullClass_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVectors(null, EnumSet.of(Traffic.RED));
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVector_nullIterable_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVector(Traffic.class, (Iterable<Traffic>) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVectors_nullIterable_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVectors(null, (Iterable<Traffic>) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVector_nullElement_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVector(Traffic.class, Arrays.asList(Traffic.RED, null));
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVectors_nullElement_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVectors(Traffic.class, Arrays.asList(Traffic.RED, null));
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVector_nullClassWithArray_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVector(null, Traffic.RED);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVectors_nullClassWithArray_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVectors(null, Traffic.RED);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVector_nullArray_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVector(Traffic.class, (Traffic[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVectors_nullArray_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVectors(Traffic.class, (Traffic[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVector_nullArrayElement_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVector(Traffic.class, Traffic.RED, null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_generateBitVectors_nullArrayElement_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVectors(Traffic.class, Traffic.RED, null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_generateBitVector_longClass_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVector(TooMany.class, EnumSet.of(TooMany.A1));
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_generateBitVector_longClassWithArray_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVector(TooMany.class, TooMany.A1);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_generateBitVector_nonEnumClass_1_oe() throws Exception {
        @SuppressWarnings("rawtypes")
        final
        Class rawType = Object.class;
        @SuppressWarnings("rawtypes")
        final
        List rawList = new ArrayList();
        try {
    EnumUtils.generateBitVector(rawType, rawList);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_generateBitVectors_nonEnumClass_1_oe() throws Exception {
        @SuppressWarnings("rawtypes")
        final
        Class rawType = Object.class;
        @SuppressWarnings("rawtypes")
        final
        List rawList = new ArrayList();
        try {
    EnumUtils.generateBitVectors(rawType, rawList);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_generateBitVector_nonEnumClassWithArray_1_oe() throws Exception {
        @SuppressWarnings("rawtypes")
        final
        Class rawType = Object.class;
        try {
    EnumUtils.generateBitVector(rawType);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_generateBitVectors_nonEnumClassWithArray_1_oe() throws Exception {
        @SuppressWarnings("rawtypes")
        final
        Class rawType = Object.class;
        try {
    EnumUtils.generateBitVectors(rawType);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_generateBitVector_1_oe() {
        assertEquals(0L, EnumUtils.generateBitVector(Traffic.class, EnumSet.noneOf(Traffic.class)));
    }

    @Test
    public void test_generateBitVector_2_oe() {
        // removed other assertion
        assertEquals(1L, EnumUtils.generateBitVector(Traffic.class, EnumSet.of(Traffic.RED)));
    }

    @Test
    public void test_generateBitVector_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(2L, EnumUtils.generateBitVector(Traffic.class, EnumSet.of(Traffic.AMBER)));
    }

    @Test
    public void test_generateBitVector_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4L, EnumUtils.generateBitVector(Traffic.class, EnumSet.of(Traffic.GREEN)));
    }

    @Test
    public void test_generateBitVector_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3L, EnumUtils.generateBitVector(Traffic.class, EnumSet.of(Traffic.RED, Traffic.AMBER)));
    }

    @Test
    public void test_generateBitVector_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5L, EnumUtils.generateBitVector(Traffic.class, EnumSet.of(Traffic.RED, Traffic.GREEN)));
    }

    @Test
    public void test_generateBitVector_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6L, EnumUtils.generateBitVector(Traffic.class, EnumSet.of(Traffic.AMBER, Traffic.GREEN)));
    }

    @Test
    public void test_generateBitVector_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7L, EnumUtils.generateBitVector(Traffic.class, EnumSet.of(Traffic.RED, Traffic.AMBER, Traffic.GREEN)));
    }

    @Test
    public void test_generateBitVector_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        assertEquals((1L << 31), EnumUtils.generateBitVector(Enum64.class, EnumSet.of(Enum64.A31)));
    }

    @Test
    public void test_generateBitVector_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        assertEquals((1L << 32), EnumUtils.generateBitVector(Enum64.class, EnumSet.of(Enum64.A32)));
    }

    @Test
    public void test_generateBitVector_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        // removed other assertion
        assertEquals((1L << 63), EnumUtils.generateBitVector(Enum64.class, EnumSet.of(Enum64.A63)));
    }

    @Test
    public void test_generateBitVector_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.MIN_VALUE, EnumUtils.generateBitVector(Enum64.class, EnumSet.of(Enum64.A63)));
    }

    @Test
    public void test_generateBitVectors_1_oe() {
        assertArrayEquals(EnumUtils.generateBitVectors(Traffic.class, EnumSet.noneOf(Traffic.class)), 0L);
    }

    @Test
    public void test_generateBitVectors_2_oe() {
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Traffic.class, EnumSet.of(Traffic.RED)), 1L);
    }

    @Test
    public void test_generateBitVectors_3_oe() {
        // removed other assertion
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Traffic.class, EnumSet.of(Traffic.AMBER)), 2L);
    }

    @Test
    public void test_generateBitVectors_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Traffic.class, EnumSet.of(Traffic.GREEN)), 4L);
    }

    @Test
    public void test_generateBitVectors_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Traffic.class, EnumSet.of(Traffic.RED, Traffic.AMBER)), 3L);
    }

    @Test
    public void test_generateBitVectors_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Traffic.class, EnumSet.of(Traffic.RED, Traffic.GREEN)), 5L);
    }

    @Test
    public void test_generateBitVectors_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Traffic.class, EnumSet.of(Traffic.AMBER, Traffic.GREEN)), 6L);
    }

    @Test
    public void test_generateBitVectors_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Traffic.class, EnumSet.of(Traffic.RED, Traffic.AMBER, Traffic.GREEN)), 7L);
    }

    @Test
    public void test_generateBitVectors_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        assertArrayEquals(EnumUtils.generateBitVectors(Enum64.class, EnumSet.of(Enum64.A31)), (1L << 31));
    }

    @Test
    public void test_generateBitVectors_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Enum64.class, EnumSet.of(Enum64.A32)), (1L << 32));
    }

    @Test
    public void test_generateBitVectors_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Enum64.class, EnumSet.of(Enum64.A63)), (1L << 63));
    }

    @Test
    public void test_generateBitVectors_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Enum64.class, EnumSet.of(Enum64.A63)), Long.MIN_VALUE);
    }

    @Test
    public void test_generateBitVectors_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // More than 64 values Enum
        assertArrayEquals(EnumUtils.generateBitVectors(TooMany.class, EnumSet.of(TooMany.M2)), 1L, 0L);
    }

    @Test
    public void test_generateBitVectors_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // More than 64 values Enum
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(TooMany.class, EnumSet.of(TooMany.L2, TooMany.M2)), 1L, (1L << 63));
    }

    @Test
    public void test_generateBitVectorFromArray_1_oe() {
        assertEquals(0L, EnumUtils.generateBitVector(Traffic.class));
    }

    @Test
    public void test_generateBitVectorFromArray_2_oe() {
        // removed other assertion
        assertEquals(1L, EnumUtils.generateBitVector(Traffic.class, Traffic.RED));
    }

    @Test
    public void test_generateBitVectorFromArray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(2L, EnumUtils.generateBitVector(Traffic.class, Traffic.AMBER));
    }

    @Test
    public void test_generateBitVectorFromArray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4L, EnumUtils.generateBitVector(Traffic.class, Traffic.GREEN));
    }

    @Test
    public void test_generateBitVectorFromArray_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3L, EnumUtils.generateBitVector(Traffic.class, Traffic.RED, Traffic.AMBER));
    }

    @Test
    public void test_generateBitVectorFromArray_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5L, EnumUtils.generateBitVector(Traffic.class, Traffic.RED, Traffic.GREEN));
    }

    @Test
    public void test_generateBitVectorFromArray_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6L, EnumUtils.generateBitVector(Traffic.class, Traffic.AMBER, Traffic.GREEN));
    }

    @Test
    public void test_generateBitVectorFromArray_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7L, EnumUtils.generateBitVector(Traffic.class, Traffic.RED, Traffic.AMBER, Traffic.GREEN));
    }

    @Test
    public void test_generateBitVectorFromArray_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //gracefully handles duplicates:
        assertEquals(7L, EnumUtils.generateBitVector(Traffic.class, Traffic.RED, Traffic.AMBER, Traffic.GREEN, Traffic.GREEN));
    }

    @Test
    public void test_generateBitVectorFromArray_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //gracefully handles duplicates:
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        assertEquals((1L << 31), EnumUtils.generateBitVector(Enum64.class, Enum64.A31));
    }

    @Test
    public void test_generateBitVectorFromArray_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //gracefully handles duplicates:
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        assertEquals((1L << 32), EnumUtils.generateBitVector(Enum64.class, Enum64.A32));
    }

    @Test
    public void test_generateBitVectorFromArray_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //gracefully handles duplicates:
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        // removed other assertion
        assertEquals((1L << 63), EnumUtils.generateBitVector(Enum64.class, Enum64.A63));
    }

    @Test
    public void test_generateBitVectorFromArray_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //gracefully handles duplicates:
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.MIN_VALUE, EnumUtils.generateBitVector(Enum64.class, Enum64.A63));
    }

    @Test
    public void test_generateBitVectorsFromArray_1_oe() {
        assertArrayEquals(EnumUtils.generateBitVectors(Traffic.class), 0L);
    }

    @Test
    public void test_generateBitVectorsFromArray_2_oe() {
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Traffic.class, Traffic.RED), 1L);
    }

    @Test
    public void test_generateBitVectorsFromArray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Traffic.class, Traffic.AMBER), 2L);
    }

    @Test
    public void test_generateBitVectorsFromArray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Traffic.class, Traffic.GREEN), 4L);
    }

    @Test
    public void test_generateBitVectorsFromArray_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Traffic.class, Traffic.RED, Traffic.AMBER), 3L);
    }

    @Test
    public void test_generateBitVectorsFromArray_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Traffic.class, Traffic.RED, Traffic.GREEN), 5L);
    }

    @Test
    public void test_generateBitVectorsFromArray_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Traffic.class, Traffic.AMBER, Traffic.GREEN), 6L);
    }

    @Test
    public void test_generateBitVectorsFromArray_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Traffic.class, Traffic.RED, Traffic.AMBER, Traffic.GREEN), 7L);
    }

    @Test
    public void test_generateBitVectorsFromArray_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //gracefully handles duplicates:
        assertArrayEquals(EnumUtils.generateBitVectors(Traffic.class, Traffic.RED, Traffic.AMBER, Traffic.GREEN, Traffic.GREEN), 7L);
    }

    @Test
    public void test_generateBitVectorsFromArray_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //gracefully handles duplicates:
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        assertArrayEquals(EnumUtils.generateBitVectors(Enum64.class, Enum64.A31), (1L << 31));
    }

    @Test
    public void test_generateBitVectorsFromArray_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //gracefully handles duplicates:
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Enum64.class, Enum64.A32), (1L << 32));
    }

    @Test
    public void test_generateBitVectorsFromArray_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //gracefully handles duplicates:
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Enum64.class, Enum64.A63), (1L << 63));
    }

    @Test
    public void test_generateBitVectorsFromArray_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //gracefully handles duplicates:
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(Enum64.class, Enum64.A63), Long.MIN_VALUE);
    }

    @Test
    public void test_generateBitVectorsFromArray_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //gracefully handles duplicates:
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // More than 64 values Enum
        assertArrayEquals(EnumUtils.generateBitVectors(TooMany.class, TooMany.M2), 1L, 0L);
    }

    @Test
    public void test_generateBitVectorsFromArray_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //gracefully handles duplicates:
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // More than 64 values Enum
        // removed other assertion
        assertArrayEquals(EnumUtils.generateBitVectors(TooMany.class, TooMany.L2, TooMany.M2), 1L, (1L << 63));
    }

    @Test
    public void test_processBitVector_nullClass_1_oe() throws Exception {
        final Class<Traffic> empty = null;
        try {
    EnumUtils.processBitVector(empty, 0L);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_processBitVectors_nullClass_1_oe() throws Exception {
        final Class<Traffic> empty = null;
        try {
    EnumUtils.processBitVectors(empty, 0L);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_processBitVector_1_oe() {
        assertEquals(EnumSet.noneOf(Traffic.class), EnumUtils.processBitVector(Traffic.class, 0L));
    }

    @Test
    public void test_processBitVector_2_oe() {
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.RED), EnumUtils.processBitVector(Traffic.class, 1L));
    }

    @Test
    public void test_processBitVector_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.AMBER), EnumUtils.processBitVector(Traffic.class, 2L));
    }

    @Test
    public void test_processBitVector_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.RED, Traffic.AMBER), EnumUtils.processBitVector(Traffic.class, 3L));
    }

    @Test
    public void test_processBitVector_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.GREEN), EnumUtils.processBitVector(Traffic.class, 4L));
    }

    @Test
    public void test_processBitVector_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.RED, Traffic.GREEN), EnumUtils.processBitVector(Traffic.class, 5L));
    }

    @Test
    public void test_processBitVector_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.AMBER, Traffic.GREEN), EnumUtils.processBitVector(Traffic.class, 6L));
    }

    @Test
    public void test_processBitVector_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.RED, Traffic.AMBER, Traffic.GREEN), EnumUtils.processBitVector(Traffic.class, 7L));
    }

    @Test
    public void test_processBitVector_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        assertEquals(EnumSet.of(Enum64.A31), EnumUtils.processBitVector(Enum64.class, (1L << 31)));
    }

    @Test
    public void test_processBitVector_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        assertEquals(EnumSet.of(Enum64.A32), EnumUtils.processBitVector(Enum64.class, (1L << 32)));
    }

    @Test
    public void test_processBitVector_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Enum64.A63), EnumUtils.processBitVector(Enum64.class, (1L << 63)));
    }

    @Test
    public void test_processBitVector_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Enum64.A63), EnumUtils.processBitVector(Enum64.class, Long.MIN_VALUE));
    }

    @Test
    public void test_processBitVectors_1_oe() {
        assertEquals(EnumSet.noneOf(Traffic.class), EnumUtils.processBitVectors(Traffic.class, 0L));
    }

    @Test
    public void test_processBitVectors_2_oe() {
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.RED), EnumUtils.processBitVectors(Traffic.class, 1L));
    }

    @Test
    public void test_processBitVectors_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.AMBER), EnumUtils.processBitVectors(Traffic.class, 2L));
    }

    @Test
    public void test_processBitVectors_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.RED, Traffic.AMBER), EnumUtils.processBitVectors(Traffic.class, 3L));
    }

    @Test
    public void test_processBitVectors_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.GREEN), EnumUtils.processBitVectors(Traffic.class, 4L));
    }

    @Test
    public void test_processBitVectors_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.RED, Traffic.GREEN), EnumUtils.processBitVectors(Traffic.class, 5L));
    }

    @Test
    public void test_processBitVectors_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.AMBER, Traffic.GREEN), EnumUtils.processBitVectors(Traffic.class, 6L));
    }

    @Test
    public void test_processBitVectors_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.RED, Traffic.AMBER, Traffic.GREEN), EnumUtils.processBitVectors(Traffic.class, 7L));
    }

    @Test
    public void test_processBitVectors_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(EnumSet.noneOf(Traffic.class), EnumUtils.processBitVectors(Traffic.class, 0L, 0L));
    }

    @Test
    public void test_processBitVectors_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(EnumSet.of(Traffic.RED), EnumUtils.processBitVectors(Traffic.class, 0L, 1L));
    }

    @Test
    public void test_processBitVectors_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.AMBER), EnumUtils.processBitVectors(Traffic.class, 0L, 2L));
    }

    @Test
    public void test_processBitVectors_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.RED, Traffic.AMBER), EnumUtils.processBitVectors(Traffic.class, 0L, 3L));
    }

    @Test
    public void test_processBitVectors_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.GREEN), EnumUtils.processBitVectors(Traffic.class, 0L, 4L));
    }

    @Test
    public void test_processBitVectors_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.RED, Traffic.GREEN), EnumUtils.processBitVectors(Traffic.class, 0L, 5L));
    }

    @Test
    public void test_processBitVectors_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.AMBER, Traffic.GREEN), EnumUtils.processBitVectors(Traffic.class, 0L, 6L));
    }

    @Test
    public void test_processBitVectors_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.RED, Traffic.AMBER, Traffic.GREEN), EnumUtils.processBitVectors(Traffic.class, 0L, 7L));
    }

    @Test
    public void test_processBitVectors_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        assertEquals(EnumSet.noneOf(Traffic.class), EnumUtils.processBitVectors(Traffic.class, 666L, 0L));
    }

    @Test
    public void test_processBitVectors_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.RED), EnumUtils.processBitVectors(Traffic.class, 666L, 1L));
    }

    @Test
    public void test_processBitVectors_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.AMBER), EnumUtils.processBitVectors(Traffic.class, 666L, 2L));
    }

    @Test
    public void test_processBitVectors_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.RED, Traffic.AMBER), EnumUtils.processBitVectors(Traffic.class, 666L, 3L));
    }

    @Test
    public void test_processBitVectors_21_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.GREEN), EnumUtils.processBitVectors(Traffic.class, 666L, 4L));
    }

    @Test
    public void test_processBitVectors_22_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.RED, Traffic.GREEN), EnumUtils.processBitVectors(Traffic.class, 666L, 5L));
    }

    @Test
    public void test_processBitVectors_23_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.AMBER, Traffic.GREEN), EnumUtils.processBitVectors(Traffic.class, 666L, 6L));
    }

    @Test
    public void test_processBitVectors_24_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Traffic.RED, Traffic.AMBER, Traffic.GREEN), EnumUtils.processBitVectors(Traffic.class, 666L, 7L));
    }

    @Test
    public void test_processBitVectors_25_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        assertEquals(EnumSet.of(Enum64.A31), EnumUtils.processBitVectors(Enum64.class, (1L << 31)));
    }

    @Test
    public void test_processBitVectors_26_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        assertEquals(EnumSet.of(Enum64.A32), EnumUtils.processBitVectors(Enum64.class, (1L << 32)));
    }

    @Test
    public void test_processBitVectors_27_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Enum64.A63), EnumUtils.processBitVectors(Enum64.class, (1L << 63)));
    }

    @Test
    public void test_processBitVectors_28_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 64 values Enum (to test whether no int<->long jdk conversion issue exists)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(Enum64.A63), EnumUtils.processBitVectors(Enum64.class, Long.MIN_VALUE));
    }

    @Test
    public void test_processBitVector_longClass_1_oe() throws Exception {
        try {
    EnumUtils.processBitVector(TooMany.class, 0L);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_processBitVectors_longClass_1_oe() {
        assertEquals(EnumSet.noneOf(TooMany.class), EnumUtils.processBitVectors(TooMany.class, 0L));
    }

    @Test
    public void test_processBitVectors_longClass_2_oe() {
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A), EnumUtils.processBitVectors(TooMany.class, 1L));
    }

    @Test
    public void test_processBitVectors_longClass_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.B), EnumUtils.processBitVectors(TooMany.class, 2L));
    }

    @Test
    public void test_processBitVectors_longClass_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A, TooMany.B), EnumUtils.processBitVectors(TooMany.class, 3L));
    }

    @Test
    public void test_processBitVectors_longClass_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.C), EnumUtils.processBitVectors(TooMany.class, 4L));
    }

    @Test
    public void test_processBitVectors_longClass_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A, TooMany.C), EnumUtils.processBitVectors(TooMany.class, 5L));
    }

    @Test
    public void test_processBitVectors_longClass_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.B, TooMany.C), EnumUtils.processBitVectors(TooMany.class, 6L));
    }

    @Test
    public void test_processBitVectors_longClass_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A, TooMany.B, TooMany.C), EnumUtils.processBitVectors(TooMany.class, 7L));
    }

    @Test
    public void test_processBitVectors_longClass_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(EnumSet.noneOf(TooMany.class), EnumUtils.processBitVectors(TooMany.class, 0L, 0L));
    }

    @Test
    public void test_processBitVectors_longClass_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A), EnumUtils.processBitVectors(TooMany.class, 0L, 1L));
    }

    @Test
    public void test_processBitVectors_longClass_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.B), EnumUtils.processBitVectors(TooMany.class, 0L, 2L));
    }

    @Test
    public void test_processBitVectors_longClass_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A, TooMany.B), EnumUtils.processBitVectors(TooMany.class, 0L, 3L));
    }

    @Test
    public void test_processBitVectors_longClass_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.C), EnumUtils.processBitVectors(TooMany.class, 0L, 4L));
    }

    @Test
    public void test_processBitVectors_longClass_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A, TooMany.C), EnumUtils.processBitVectors(TooMany.class, 0L, 5L));
    }

    @Test
    public void test_processBitVectors_longClass_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.B, TooMany.C), EnumUtils.processBitVectors(TooMany.class, 0L, 6L));
    }

    @Test
    public void test_processBitVectors_longClass_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A, TooMany.B, TooMany.C), EnumUtils.processBitVectors(TooMany.class, 0L, 7L));
    }

    @Test
    public void test_processBitVectors_longClass_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A, TooMany.B, TooMany.C), EnumUtils.processBitVectors(TooMany.class, 0L, 7L));
    }

    @Test
    public void test_processBitVectors_longClass_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(EnumSet.of(TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 1L, 0L));
    }

    @Test
    public void test_processBitVectors_longClass_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A, TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 1L, 1L));
    }

    @Test
    public void test_processBitVectors_longClass_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.B, TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 1L, 2L));
    }

    @Test
    public void test_processBitVectors_longClass_21_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A, TooMany.B, TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 1L, 3L));
    }

    @Test
    public void test_processBitVectors_longClass_22_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.C, TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 1L, 4L));
    }

    @Test
    public void test_processBitVectors_longClass_23_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A, TooMany.C, TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 1L, 5L));
    }

    @Test
    public void test_processBitVectors_longClass_24_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.B, TooMany.C, TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 1L, 6L));
    }

    @Test
    public void test_processBitVectors_longClass_25_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A, TooMany.B, TooMany.C, TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 1L, 7L));
    }

    @Test
    public void test_processBitVectors_longClass_26_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A, TooMany.B, TooMany.C, TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 1L, 7L));
    }

    @Test
    public void test_processBitVectors_longClass_27_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        assertEquals(EnumSet.of(TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 9L, 0L));
    }

    @Test
    public void test_processBitVectors_longClass_28_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A, TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 9L, 1L));
    }

    @Test
    public void test_processBitVectors_longClass_29_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.B, TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 9L, 2L));
    }

    @Test
    public void test_processBitVectors_longClass_30_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A, TooMany.B, TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 9L, 3L));
    }

    @Test
    public void test_processBitVectors_longClass_31_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.C, TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 9L, 4L));
    }

    @Test
    public void test_processBitVectors_longClass_32_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A, TooMany.C, TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 9L, 5L));
    }

    @Test
    public void test_processBitVectors_longClass_33_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.B, TooMany.C, TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 9L, 6L));
    }

    @Test
    public void test_processBitVectors_longClass_34_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A, TooMany.B, TooMany.C, TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 9L, 7L));
    }

    @Test
    public void test_processBitVectors_longClass_35_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // demonstrate tolerance of irrelevant high-order digits:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(EnumSet.of(TooMany.A, TooMany.B, TooMany.C, TooMany.M2), EnumUtils.processBitVectors(TooMany.class, 9L, 7L));
    }

}


