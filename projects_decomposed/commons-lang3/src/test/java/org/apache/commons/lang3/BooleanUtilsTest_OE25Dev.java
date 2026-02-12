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

import static org.apache.commons.lang3.ArraySorter.sort;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests {@link org.apache.commons.lang3.BooleanUtils}.
 */
public class BooleanUtilsTest_OE25Dev {

    @Test
    public void test_booleanValues_1_oe() {
        final Boolean[] expected = new Boolean[] {false, true};
        assertArrayEquals(sort(expected), BooleanUtils.booleanValues());
    }

    @Test
    public void test_isFalse_Boolean_1_oe() {
        assertFalse(BooleanUtils.isFalse(Boolean.TRUE));
    }

    @Test
    public void test_isFalse_Boolean_2_oe() {
        // removed other assertion
        assertTrue(BooleanUtils.isFalse(Boolean.FALSE));
    }

    @Test
    public void test_isFalse_Boolean_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.isFalse(null));
    }

    @Test
    public void test_isNotFalse_Boolean_1_oe() {
        assertTrue(BooleanUtils.isNotFalse(Boolean.TRUE));
    }

    @Test
    public void test_isNotFalse_Boolean_2_oe() {
        // removed other assertion
        assertFalse(BooleanUtils.isNotFalse(Boolean.FALSE));
    }

    @Test
    public void test_isNotFalse_Boolean_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.isNotFalse(null));
    }

    @Test
    public void test_isNotTrue_Boolean_1_oe() {
        assertFalse(BooleanUtils.isNotTrue(Boolean.TRUE));
    }

    @Test
    public void test_isNotTrue_Boolean_2_oe() {
        // removed other assertion
        assertTrue(BooleanUtils.isNotTrue(Boolean.FALSE));
    }

    @Test
    public void test_isNotTrue_Boolean_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.isNotTrue(null));
    }

    @Test
    public void test_isTrue_Boolean_1_oe() {
        assertTrue(BooleanUtils.isTrue(Boolean.TRUE));
    }

    @Test
    public void test_isTrue_Boolean_2_oe() {
        // removed other assertion
        assertFalse(BooleanUtils.isTrue(Boolean.FALSE));
    }

    @Test
    public void test_isTrue_Boolean_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.isTrue(null));
    }

    @Test
    public void test_negate_Boolean_1_oe() {
        assertSame(null, BooleanUtils.negate(null));
    }

    @Test
    public void test_negate_Boolean_2_oe() {
        // removed other assertion
        assertSame(Boolean.TRUE, BooleanUtils.negate(Boolean.FALSE));
    }

    @Test
    public void test_negate_Boolean_3_oe() {
        // removed other assertion
        // removed other assertion
        assertSame(Boolean.FALSE, BooleanUtils.negate(Boolean.TRUE));
    }

    @Test
    public void test_primitiveValues_1_oe() {
        assertArrayEquals(new boolean[] {false, true}, BooleanUtils.primitiveValues());
    }

    @Test
    public void test_toBoolean_Boolean_1_oe() {
        assertTrue(BooleanUtils.toBoolean(Boolean.TRUE));
    }

    @Test
    public void test_toBoolean_Boolean_2_oe() {
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean(Boolean.FALSE));
    }

    @Test
    public void test_toBoolean_Boolean_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean((Boolean) null));
    }

    @Test
    public void test_toBoolean_int_1_oe() {
        assertTrue(BooleanUtils.toBoolean(1));
    }

    @Test
    public void test_toBoolean_int_2_oe() {
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean(-1));
    }

    @Test
    public void test_toBoolean_int_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean(0));
    }

    @Test
    public void test_toBoolean_int_int_int_1_oe() {
        assertTrue(BooleanUtils.toBoolean(6, 6, 7));
    }

    @Test
    public void test_toBoolean_int_int_int_2_oe() {
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean(7, 6, 7));
    }

    @Test
    public void test_toBoolean_int_int_int_noMatch_1_oe() throws Exception {
        try {
    BooleanUtils.toBoolean(8, 6, 7);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBoolean_Integer_Integer_Integer_1_oe() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);

        assertTrue(BooleanUtils.toBoolean(null, null, seven));
    }

    @Test
    public void test_toBoolean_Integer_Integer_Integer_2_oe() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);

        // removed other assertion
        assertFalse(BooleanUtils.toBoolean(null, six, null));
    }

    @Test
    public void test_toBoolean_Integer_Integer_Integer_3_oe() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);

        // removed other assertion
        // removed other assertion

        assertTrue(BooleanUtils.toBoolean(Integer.valueOf(6), six, seven));
    }

    @Test
    public void test_toBoolean_Integer_Integer_Integer_4_oe() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(BooleanUtils.toBoolean(Integer.valueOf(7), six, seven));
    }

    @Test
    public void test_toBoolean_Integer_Integer_Integer_noMatch_1_oe() throws Exception {
        try {
    BooleanUtils.toBoolean(Integer.valueOf(8), Integer.valueOf(6), Integer.valueOf(7));
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBoolean_Integer_Integer_Integer_nullValue_1_oe() throws Exception {
        try {
    BooleanUtils.toBoolean(null, Integer.valueOf(6), Integer.valueOf(7));
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBoolean_String_1_oe() {
        assertFalse(BooleanUtils.toBoolean((String) null));
    }

    @Test
    public void test_toBoolean_String_2_oe() {
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean(""));
    }

    @Test
    public void test_toBoolean_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean("off"));
    }

    @Test
    public void test_toBoolean_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean("oof"));
    }

    @Test
    public void test_toBoolean_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean("yep"));
    }

    @Test
    public void test_toBoolean_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean("trux"));
    }

    @Test
    public void test_toBoolean_String_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean("false"));
    }

    @Test
    public void test_toBoolean_String_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean("a"));
    }

    @Test
    public void test_toBoolean_String_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("true"));// interned handled differently assertTrue(BooleanUtils.toBoolean(new StringBuilder("tr").append("ue").toString()));
    }

    @Test
    public void test_toBoolean_String_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("truE"));
    }

    @Test
    public void test_toBoolean_String_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("trUe"));
    }

    @Test
    public void test_toBoolean_String_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("trUE"));
    }

    @Test
    public void test_toBoolean_String_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("tRue"));
    }

    @Test
    public void test_toBoolean_String_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("tRuE"));
    }

    @Test
    public void test_toBoolean_String_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("tRUe"));
    }

    @Test
    public void test_toBoolean_String_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("tRUE"));
    }

    @Test
    public void test_toBoolean_String_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("TRUE"));
    }

    @Test
    public void test_toBoolean_String_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("TRUe"));
    }

    @Test
    public void test_toBoolean_String_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("TRuE"));
    }

    @Test
    public void test_toBoolean_String_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("TRue"));
    }

    @Test
    public void test_toBoolean_String_21_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("TrUE"));
    }

    @Test
    public void test_toBoolean_String_22_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("TrUe"));
    }

    @Test
    public void test_toBoolean_String_23_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("TruE"));
    }

    @Test
    public void test_toBoolean_String_24_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("True"));
    }

    @Test
    public void test_toBoolean_String_25_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("on"));
    }

    @Test
    public void test_toBoolean_String_26_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("oN"));
    }

    @Test
    public void test_toBoolean_String_27_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("On"));
    }

    @Test
    public void test_toBoolean_String_28_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("ON"));
    }

    @Test
    public void test_toBoolean_String_29_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("yes"));
    }

    @Test
    public void test_toBoolean_String_30_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("yeS"));
    }

    @Test
    public void test_toBoolean_String_31_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("yEs"));
    }

    @Test
    public void test_toBoolean_String_32_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("yES"));
    }

    @Test
    public void test_toBoolean_String_33_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("Yes"));
    }

    @Test
    public void test_toBoolean_String_34_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("YeS"));
    }

    @Test
    public void test_toBoolean_String_35_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("YEs"));
    }

    @Test
    public void test_toBoolean_String_36_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("YES"));
    }

    @Test
    public void test_toBoolean_String_37_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("1"));
    }

    @Test
    public void test_toBoolean_String_38_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean("yes?"));
    }

    @Test
    public void test_toBoolean_String_39_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean("0"));
    }

    @Test
    public void test_toBoolean_String_40_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean("tru"));
    }

    @Test
    public void test_toBoolean_String_41_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(BooleanUtils.toBoolean("no"));
    }

    @Test
    public void test_toBoolean_String_42_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(BooleanUtils.toBoolean("off"));
    }

    @Test
    public void test_toBoolean_String_43_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean("yoo"));
    }

    @Test
    public void test_toBoolean_String_String_String_1_oe() {
        assertTrue(BooleanUtils.toBoolean(null, null, "N"));
    }

    @Test
    public void test_toBoolean_String_String_String_2_oe() {
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean(null, "Y", null));
    }

    @Test
    public void test_toBoolean_String_String_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("Y", "Y", "N"));
    }

    @Test
    public void test_toBoolean_String_String_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("Y", "Y", "N"));
    }

    @Test
    public void test_toBoolean_String_String_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean("N", "Y", "N"));
    }

    @Test
    public void test_toBoolean_String_String_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.toBoolean("N", "Y", "N"));
    }

    @Test
    public void test_toBoolean_String_String_String_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean((String) null, null, null));
    }

    @Test
    public void test_toBoolean_String_String_String_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("Y", "Y", "Y"));
    }

    @Test
    public void test_toBoolean_String_String_String_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBoolean("Y", "Y", "Y"));
    }

    @Test
    public void test_toBoolean_String_String_String_noMatch_1_oe() throws Exception {
        try {
    BooleanUtils.toBoolean("X", "Y", "N");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBoolean_String_String_String_nullValue_1_oe() throws Exception {
        try {
    BooleanUtils.toBoolean(null, "Y", "N");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBooleanDefaultIfNull_Boolean_boolean_1_oe() {
        assertTrue(BooleanUtils.toBooleanDefaultIfNull(Boolean.TRUE, true));
    }

    @Test
    public void test_toBooleanDefaultIfNull_Boolean_boolean_2_oe() {
        // removed other assertion
        assertTrue(BooleanUtils.toBooleanDefaultIfNull(Boolean.TRUE, false));
    }

    @Test
    public void test_toBooleanDefaultIfNull_Boolean_boolean_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.toBooleanDefaultIfNull(Boolean.FALSE, true));
    }

    @Test
    public void test_toBooleanDefaultIfNull_Boolean_boolean_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.toBooleanDefaultIfNull(Boolean.FALSE, false));
    }

    @Test
    public void test_toBooleanDefaultIfNull_Boolean_boolean_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.toBooleanDefaultIfNull(null, true));
    }

    @Test
    public void test_toBooleanDefaultIfNull_Boolean_boolean_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(BooleanUtils.toBooleanDefaultIfNull(null, false));
    }

    @Test
    public void test_toBooleanObject_int_1_oe() {
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(1));
    }

    @Test
    public void test_toBooleanObject_int_2_oe() {
        // removed other assertion
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(-1));
    }

    @Test
    public void test_toBooleanObject_int_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(0));
    }

    @Test
    public void test_toBooleanObject_int_int_int_1_oe() {
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(6, 6, 7, 8));
    }

    @Test
    public void test_toBooleanObject_int_int_int_2_oe() {
        // removed other assertion
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(7, 6, 7, 8));
    }

    @Test
    public void test_toBooleanObject_int_int_int_3_oe() {
        // removed other assertion
        // removed other assertion
        assertNull(BooleanUtils.toBooleanObject(8, 6, 7, 8));
    }

    @Test
    public void test_toBooleanObject_int_int_int_noMatch_1_oe() throws Exception {
        try {
    BooleanUtils.toBooleanObject(9, 6, 7, 8);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBooleanObject_Integer_1_oe() {
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(Integer.valueOf(1)));
    }

    @Test
    public void test_toBooleanObject_Integer_2_oe() {
        // removed other assertion
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(Integer.valueOf(-1)));
    }

    @Test
    public void test_toBooleanObject_Integer_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(Integer.valueOf(0)));
    }

    @Test
    public void test_toBooleanObject_Integer_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(BooleanUtils.toBooleanObject((Integer) null));
    }

    @Test
    public void test_toBooleanObject_Integer_Integer_Integer_Integer_1_oe() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);
        final Integer eight = Integer.valueOf(8);

        assertSame(Boolean.TRUE, BooleanUtils.toBooleanObject(null, null, seven, eight));
    }

    @Test
    public void test_toBooleanObject_Integer_Integer_Integer_Integer_2_oe() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);
        final Integer eight = Integer.valueOf(8);

        // removed other assertion
        assertSame(Boolean.FALSE, BooleanUtils.toBooleanObject(null, six, null, eight));
    }

    @Test
    public void test_toBooleanObject_Integer_Integer_Integer_Integer_3_oe() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);
        final Integer eight = Integer.valueOf(8);

        // removed other assertion
        // removed other assertion
        assertSame(null, BooleanUtils.toBooleanObject(null, six, seven, null));
    }

    @Test
    public void test_toBooleanObject_Integer_Integer_Integer_Integer_4_oe() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);
        final Integer eight = Integer.valueOf(8);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(Integer.valueOf(6), six, seven, eight));
    }

    @Test
    public void test_toBooleanObject_Integer_Integer_Integer_Integer_5_oe() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);
        final Integer eight = Integer.valueOf(8);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(Integer.valueOf(7), six, seven, eight));
    }

    @Test
    public void test_toBooleanObject_Integer_Integer_Integer_Integer_6_oe() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);
        final Integer eight = Integer.valueOf(8);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNull(BooleanUtils.toBooleanObject(Integer.valueOf(8), six, seven, eight));
    }

    @Test
    public void test_toBooleanObject_Integer_Integer_Integer_Integer_noMatch_1_oe() throws Exception {
        try {
    BooleanUtils.toBooleanObject(Integer.valueOf(9), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8));
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBooleanObject_Integer_Integer_Integer_Integer_nullValue_1_oe() throws Exception {
        try {
    BooleanUtils.toBooleanObject(null, Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8));
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBooleanObject_String_1_oe() {
        assertNull(BooleanUtils.toBooleanObject((String) null));
    }

    @Test
    public void test_toBooleanObject_String_2_oe() {
        // removed other assertion
        assertNull(BooleanUtils.toBooleanObject(""));
    }

    @Test
    public void test_toBooleanObject_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("false"));
    }

    @Test
    public void test_toBooleanObject_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("no"));
    }

    @Test
    public void test_toBooleanObject_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("off"));
    }

    @Test
    public void test_toBooleanObject_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("FALSE"));
    }

    @Test
    public void test_toBooleanObject_String_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("NO"));
    }

    @Test
    public void test_toBooleanObject_String_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("OFF"));
    }

    @Test
    public void test_toBooleanObject_String_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(BooleanUtils.toBooleanObject("oof"));
    }

    @Test
    public void test_toBooleanObject_String_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("true"));
    }

    @Test
    public void test_toBooleanObject_String_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("yes"));
    }

    @Test
    public void test_toBooleanObject_String_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("on"));
    }

    @Test
    public void test_toBooleanObject_String_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("TRUE"));
    }

    @Test
    public void test_toBooleanObject_String_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("ON"));
    }

    @Test
    public void test_toBooleanObject_String_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("YES"));
    }

    @Test
    public void test_toBooleanObject_String_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("TruE"));
    }

    @Test
    public void test_toBooleanObject_String_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("TruE"));
    }

    @Test
    public void test_toBooleanObject_String_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(Boolean.TRUE,BooleanUtils.toBooleanObject("y"));// yes assertEquals(Boolean.TRUE,BooleanUtils.toBooleanObject("Y"));
    }

    @Test
    public void test_toBooleanObject_String_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(Boolean.TRUE,BooleanUtils.toBooleanObject("t"));// true assertEquals(Boolean.TRUE,BooleanUtils.toBooleanObject("T"));
    }

    @Test
    public void test_toBooleanObject_String_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("1"));
    }

    @Test
    public void test_toBooleanObject_String_21_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.FALSE,BooleanUtils.toBooleanObject("f"));// false assertEquals(Boolean.FALSE,BooleanUtils.toBooleanObject("F"));
    }

    @Test
    public void test_toBooleanObject_String_22_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.FALSE,BooleanUtils.toBooleanObject("n"));// No assertEquals(Boolean.FALSE,BooleanUtils.toBooleanObject("N"));
    }

    @Test
    public void test_toBooleanObject_String_23_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("0"));
    }

    @Test
    public void test_toBooleanObject_String_24_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(BooleanUtils.toBooleanObject("z"));
    }

    @Test
    public void test_toBooleanObject_String_25_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertNull(BooleanUtils.toBooleanObject("ab"));
    }

    @Test
    public void test_toBooleanObject_String_26_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertNull(BooleanUtils.toBooleanObject("yoo"));
    }

    @Test
    public void test_toBooleanObject_String_27_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNull(BooleanUtils.toBooleanObject("true "));
    }

    @Test
    public void test_toBooleanObject_String_28_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(BooleanUtils.toBooleanObject("ono"));
    }

    @Test
    public void test_toBooleanObject_String_String_String_String_1_oe() {
        assertSame(Boolean.TRUE, BooleanUtils.toBooleanObject(null, null, "N", "U"));
    }

    @Test
    public void test_toBooleanObject_String_String_String_String_2_oe() {
        // removed other assertion
        assertSame(Boolean.FALSE, BooleanUtils.toBooleanObject(null, "Y", null, "U"));
    }

    @Test
    public void test_toBooleanObject_String_String_String_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertSame(null, BooleanUtils.toBooleanObject(null, "Y", "N", null));
    }

    @Test
    public void test_toBooleanObject_String_String_String_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("Y", "Y", "N", "U"));
    }

    @Test
    public void test_toBooleanObject_String_String_String_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("N", "Y", "N", "U"));
    }

    @Test
    public void test_toBooleanObject_String_String_String_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNull(BooleanUtils.toBooleanObject("U", "Y", "N", "U"));
    }

    @Test
    public void test_toBooleanObject_String_String_String_String_noMatch_1_oe() throws Exception {
        try {
    BooleanUtils.toBooleanObject("X", "Y", "N", "U");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBooleanObject_String_String_String_String_nullValue_1_oe() throws Exception {
        try {
    BooleanUtils.toBooleanObject(null, "Y", "N", "U");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toInteger_boolean_1_oe() {
        assertEquals(1, BooleanUtils.toInteger(true));
    }

    @Test
    public void test_toInteger_boolean_2_oe() {
        // removed other assertion
        assertEquals(0, BooleanUtils.toInteger(false));
    }

    @Test
    public void test_toInteger_boolean_int_int_1_oe() {
        assertEquals(6, BooleanUtils.toInteger(true, 6, 7));
    }

    @Test
    public void test_toInteger_boolean_int_int_2_oe() {
        // removed other assertion
        assertEquals(7, BooleanUtils.toInteger(false, 6, 7));
    }

    @Test
    public void test_toInteger_Boolean_int_int_int_1_oe() {
        assertEquals(6, BooleanUtils.toInteger(Boolean.TRUE, 6, 7, 8));
    }

    @Test
    public void test_toInteger_Boolean_int_int_int_2_oe() {
        // removed other assertion
        assertEquals(7, BooleanUtils.toInteger(Boolean.FALSE, 6, 7, 8));
    }

    @Test
    public void test_toInteger_Boolean_int_int_int_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(8, BooleanUtils.toInteger(null, 6, 7, 8));
    }

    @Test
    public void test_toIntegerObject_boolean_1_oe() {
        assertEquals(Integer.valueOf(1), BooleanUtils.toIntegerObject(true));
    }

    @Test
    public void test_toIntegerObject_boolean_2_oe() {
        // removed other assertion
        assertEquals(Integer.valueOf(0), BooleanUtils.toIntegerObject(false));
    }

    @Test
    public void test_toIntegerObject_Boolean_1_oe() {
        assertEquals(Integer.valueOf(1), BooleanUtils.toIntegerObject(Boolean.TRUE));
    }

    @Test
    public void test_toIntegerObject_Boolean_2_oe() {
        // removed other assertion
        assertEquals(Integer.valueOf(0), BooleanUtils.toIntegerObject(Boolean.FALSE));
    }

    @Test
    public void test_toIntegerObject_Boolean_3_oe() {
        // removed other assertion
        // removed other assertion
        assertNull(BooleanUtils.toIntegerObject(null));
    }

    @Test
    public void test_toIntegerObject_boolean_Integer_Integer_1_oe() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);
        assertEquals(six, BooleanUtils.toIntegerObject(true, six, seven));
    }

    @Test
    public void test_toIntegerObject_boolean_Integer_Integer_2_oe() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);
        // removed other assertion
        assertEquals(seven, BooleanUtils.toIntegerObject(false, six, seven));
    }

    @Test
    public void test_toIntegerObject_Boolean_Integer_Integer_Integer_1_oe() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);
        final Integer eight = Integer.valueOf(8);
        assertEquals(six, BooleanUtils.toIntegerObject(Boolean.TRUE, six, seven, eight));
    }

    @Test
    public void test_toIntegerObject_Boolean_Integer_Integer_Integer_2_oe() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);
        final Integer eight = Integer.valueOf(8);
        // removed other assertion
        assertEquals(seven, BooleanUtils.toIntegerObject(Boolean.FALSE, six, seven, eight));
    }

    @Test
    public void test_toIntegerObject_Boolean_Integer_Integer_Integer_3_oe() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);
        final Integer eight = Integer.valueOf(8);
        // removed other assertion
        // removed other assertion
        assertEquals(eight, BooleanUtils.toIntegerObject(null, six, seven, eight));
    }

    @Test
    public void test_toIntegerObject_Boolean_Integer_Integer_Integer_4_oe() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);
        final Integer eight = Integer.valueOf(8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(BooleanUtils.toIntegerObject(null, six, seven, null));
    }

    @Test
    public void test_toString_boolean_String_String_String_1_oe() {
        assertEquals("Y", BooleanUtils.toString(true, "Y", "N"));
    }

    @Test
    public void test_toString_boolean_String_String_String_2_oe() {
        // removed other assertion
        assertEquals("N", BooleanUtils.toString(false, "Y", "N"));
    }

    @Test
    public void test_toString_Boolean_String_String_String_1_oe() {
        assertEquals("U", BooleanUtils.toString(null, "Y", "N", "U"));
    }

    @Test
    public void test_toString_Boolean_String_String_String_2_oe() {
        // removed other assertion
        assertEquals("Y", BooleanUtils.toString(Boolean.TRUE, "Y", "N", "U"));
    }

    @Test
    public void test_toString_Boolean_String_String_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("N", BooleanUtils.toString(Boolean.FALSE, "Y", "N", "U"));
    }

    @Test
    public void test_toStringOnOff_boolean_1_oe() {
        assertEquals("on", BooleanUtils.toStringOnOff(true));
    }

    @Test
    public void test_toStringOnOff_boolean_2_oe() {
        // removed other assertion
        assertEquals("off", BooleanUtils.toStringOnOff(false));
    }

    @Test
    public void test_toStringOnOff_Boolean_1_oe() {
        assertNull(BooleanUtils.toStringOnOff(null));
    }

    @Test
    public void test_toStringOnOff_Boolean_2_oe() {
        // removed other assertion
        assertEquals("on", BooleanUtils.toStringOnOff(Boolean.TRUE));
    }

    @Test
    public void test_toStringOnOff_Boolean_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("off", BooleanUtils.toStringOnOff(Boolean.FALSE));
    }

    @Test
    public void test_toStringTrueFalse_boolean_1_oe() {
        assertEquals("true", BooleanUtils.toStringTrueFalse(true));
    }

    @Test
    public void test_toStringTrueFalse_boolean_2_oe() {
        // removed other assertion
        assertEquals("false", BooleanUtils.toStringTrueFalse(false));
    }

    @Test
    public void test_toStringTrueFalse_Boolean_1_oe() {
        assertNull(BooleanUtils.toStringTrueFalse(null));
    }

    @Test
    public void test_toStringTrueFalse_Boolean_2_oe() {
        // removed other assertion
        assertEquals("true", BooleanUtils.toStringTrueFalse(Boolean.TRUE));
    }

    @Test
    public void test_toStringTrueFalse_Boolean_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("false", BooleanUtils.toStringTrueFalse(Boolean.FALSE));
    }

    @Test
    public void test_toStringYesNo_boolean_1_oe() {
        assertEquals("yes", BooleanUtils.toStringYesNo(true));
    }

    @Test
    public void test_toStringYesNo_boolean_2_oe() {
        // removed other assertion
        assertEquals("no", BooleanUtils.toStringYesNo(false));
    }

    @Test
    public void test_toStringYesNo_Boolean_1_oe() {
        assertNull(BooleanUtils.toStringYesNo(null));
    }

    @Test
    public void test_toStringYesNo_Boolean_2_oe() {
        // removed other assertion
        assertEquals("yes", BooleanUtils.toStringYesNo(Boolean.TRUE));
    }

    @Test
    public void test_toStringYesNo_Boolean_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("no", BooleanUtils.toStringYesNo(Boolean.FALSE));
    }

    @Test
    public void testAnd_object_emptyInput_1_oe() throws Exception {
        try {
    BooleanUtils.and(new Boolean[] {});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAnd_object_nullElementInput_1_oe() throws Exception {
        try {
    BooleanUtils.and(new Boolean[] {null});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAnd_object_nullInput_1_oe() throws Exception {
        try {
    BooleanUtils.and((Boolean[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testAnd_object_validInput_2items_1_oe() {
        assertTrue( BooleanUtils .and(new Boolean[] { Boolean.TRUE, Boolean.TRUE }) .booleanValue(), "False result for (true, true)");
    }

    @Test
    public void testAnd_object_validInput_2items_2_oe() {
        // removed other assertion

        assertTrue( ! BooleanUtils .and(new Boolean[] { Boolean.FALSE, Boolean.FALSE }) .booleanValue(), "True result for (false, false)");
    }

    @Test
    public void testAnd_object_validInput_2items_3_oe() {
        // removed other assertion

        // removed other assertion

        assertTrue( ! BooleanUtils .and(new Boolean[] { Boolean.TRUE, Boolean.FALSE }) .booleanValue(), "True result for (true, false)");
    }

    @Test
    public void testAnd_object_validInput_2items_4_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( ! BooleanUtils .and(new Boolean[] { Boolean.FALSE, Boolean.TRUE }) .booleanValue(), "True result for (false, true)");
    }

    @Test
    public void testAnd_object_validInput_3items_1_oe() {
        assertTrue( ! BooleanUtils .and( new Boolean[] { Boolean.FALSE, Boolean.FALSE, Boolean.TRUE }) .booleanValue(), "True result for (false, false, true)");
    }

    @Test
    public void testAnd_object_validInput_3items_2_oe() {
        // removed other assertion

        assertTrue( ! BooleanUtils .and( new Boolean[] { Boolean.FALSE, Boolean.TRUE, Boolean.FALSE }) .booleanValue(), "True result for (false, true, false)");
    }

    @Test
    public void testAnd_object_validInput_3items_3_oe() {
        // removed other assertion

        // removed other assertion

        assertTrue( ! BooleanUtils .and( new Boolean[] { Boolean.TRUE, Boolean.FALSE, Boolean.FALSE }) .booleanValue(), "True result for (true, false, false)");
    }

    @Test
    public void testAnd_object_validInput_3items_4_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( BooleanUtils .and(new Boolean[] { Boolean.TRUE, Boolean.TRUE, Boolean.TRUE }) .booleanValue(), "False result for (true, true, true)");
    }

    @Test
    public void testAnd_object_validInput_3items_5_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( ! BooleanUtils.and( new Boolean[] { Boolean.FALSE, Boolean.FALSE, Boolean.FALSE }) .booleanValue(), "True result for (false, false)");
    }

    @Test
    public void testAnd_object_validInput_3items_6_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( ! BooleanUtils.and( new Boolean[] { Boolean.TRUE, Boolean.TRUE, Boolean.FALSE }) .booleanValue(), "True result for (true, true, false)");
    }

    @Test
    public void testAnd_object_validInput_3items_7_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( ! BooleanUtils.and( new Boolean[] { Boolean.TRUE, Boolean.FALSE, Boolean.TRUE }) .booleanValue(), "True result for (true, false, true)");
    }

    @Test
    public void testAnd_object_validInput_3items_8_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( ! BooleanUtils.and( new Boolean[] { Boolean.FALSE, Boolean.TRUE, Boolean.TRUE }) .booleanValue(), "True result for (false, true, true)");
    }

    @Test
    public void testAnd_primitive_emptyInput_1_oe() throws Exception {
        try {
    BooleanUtils.and(new boolean[] {});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAnd_primitive_nullInput_1_oe() throws Exception {
        try {
    BooleanUtils.and((boolean[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testAnd_primitive_validInput_2items_1_oe() {
        assertTrue( BooleanUtils.and(new boolean[] { true, true }), "False result for (true, true)");
    }

    @Test
    public void testAnd_primitive_validInput_2items_2_oe() {
        // removed other assertion

        assertTrue( ! BooleanUtils.and(new boolean[] { false, false }), "True result for (false, false)");
    }

    @Test
    public void testAnd_primitive_validInput_2items_3_oe() {
        // removed other assertion

        // removed other assertion

        assertTrue( ! BooleanUtils.and(new boolean[] { true, false }), "True result for (true, false)");
    }

    @Test
    public void testAnd_primitive_validInput_2items_4_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( ! BooleanUtils.and(new boolean[] { false, true }), "True result for (false, true)");
    }

    @Test
    public void testAnd_primitive_validInput_3items_1_oe() {
        assertTrue( ! BooleanUtils.and(new boolean[] { false, false, true }), "True result for (false, false, true)");
    }

    @Test
    public void testAnd_primitive_validInput_3items_2_oe() {
        // removed other assertion

        assertTrue( ! BooleanUtils.and(new boolean[] { false, true, false }), "True result for (false, true, false)");
    }

    @Test
    public void testAnd_primitive_validInput_3items_3_oe() {
        // removed other assertion

        // removed other assertion

        assertTrue( ! BooleanUtils.and(new boolean[] { true, false, false }), "True result for (true, false, false)");
    }

    @Test
    public void testAnd_primitive_validInput_3items_4_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( BooleanUtils.and(new boolean[] { true, true, true }), "False result for (true, true, true)");
    }

    @Test
    public void testAnd_primitive_validInput_3items_5_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( ! BooleanUtils.and(new boolean[] { false, false, false }), "True result for (false, false)");
    }

    @Test
    public void testAnd_primitive_validInput_3items_6_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( ! BooleanUtils.and(new boolean[] { true, true, false }), "True result for (true, true, false)");
    }

    @Test
    public void testAnd_primitive_validInput_3items_7_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( ! BooleanUtils.and(new boolean[] { true, false, true }), "True result for (true, false, true)");
    }

    @Test
    public void testAnd_primitive_validInput_3items_8_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( ! BooleanUtils.and(new boolean[] { false, true, true }), "True result for (false, true, true)");
    }

    @Test
    public void testCompare_1_oe() {
        assertTrue(BooleanUtils.compare(true, false) > 0);
    }

    @Test
    public void testCompare_2_oe() {
        // removed other assertion
        assertEquals(0, BooleanUtils.compare(true, true));
    }

    @Test
    public void testCompare_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(0, BooleanUtils.compare(false, false));
    }

    @Test
    public void testCompare_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(BooleanUtils.compare(false, true) < 0);
    }

    @Test
    public void testConstructor_1_oe() {
        assertNotNull(new BooleanUtils());
    }

    @Test
    public void testConstructor_2_oe() {
        // removed other assertion
        final Constructor<?>[] cons = BooleanUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
    }

    @Test
    public void testConstructor_3_oe() {
        // removed other assertion
        final Constructor<?>[] cons = BooleanUtils.class.getDeclaredConstructors();
        // removed other assertion
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
    }

    @Test
    public void testConstructor_4_oe() {
        // removed other assertion
        final Constructor<?>[] cons = BooleanUtils.class.getDeclaredConstructors();
        // removed other assertion
        // removed other assertion
        assertTrue(Modifier.isPublic(BooleanUtils.class.getModifiers()));
    }

    @Test
    public void testConstructor_5_oe() {
        // removed other assertion
        final Constructor<?>[] cons = BooleanUtils.class.getDeclaredConstructors();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(Modifier.isFinal(BooleanUtils.class.getModifiers()));
    }

    @Test
    public void testOr_object_emptyInput_1_oe() throws Exception {
        try {
    BooleanUtils.or(new Boolean[] {});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testOr_object_nullElementInput_1_oe() throws Exception {
        try {
    BooleanUtils.or(new Boolean[] {null});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testOr_object_nullInput_1_oe() throws Exception {
        try {
    BooleanUtils.or((Boolean[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testOr_object_validInput_2items_1_oe() {
        assertTrue( BooleanUtils .or(new Boolean[] { Boolean.TRUE, Boolean.TRUE }) .booleanValue(), "False result for (true, true)");
    }

    @Test
    public void testOr_object_validInput_2items_2_oe() {
        // removed other assertion

        assertTrue( ! BooleanUtils .or(new Boolean[] { Boolean.FALSE, Boolean.FALSE }) .booleanValue(), "True result for (false, false)");
    }

    @Test
    public void testOr_object_validInput_2items_3_oe() {
        // removed other assertion

        // removed other assertion

        assertTrue( BooleanUtils .or(new Boolean[] { Boolean.TRUE, Boolean.FALSE }) .booleanValue(), "False result for (true, false)");
    }

    @Test
    public void testOr_object_validInput_2items_4_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( BooleanUtils .or(new Boolean[] { Boolean.FALSE, Boolean.TRUE }) .booleanValue(), "False result for (false, true)");
    }

    @Test
    public void testOr_object_validInput_3items_1_oe() {
        assertTrue( BooleanUtils .or( new Boolean[] { Boolean.FALSE, Boolean.FALSE, Boolean.TRUE }) .booleanValue(), "False result for (false, false, true)");
    }

    @Test
    public void testOr_object_validInput_3items_2_oe() {
        // removed other assertion

        assertTrue( BooleanUtils .or( new Boolean[] { Boolean.FALSE, Boolean.TRUE, Boolean.FALSE }) .booleanValue(), "False result for (false, true, false)");
    }

    @Test
    public void testOr_object_validInput_3items_3_oe() {
        // removed other assertion

        // removed other assertion

        assertTrue( BooleanUtils .or( new Boolean[] { Boolean.TRUE, Boolean.FALSE, Boolean.FALSE }) .booleanValue(), "False result for (true, false, false)");
    }

    @Test
    public void testOr_object_validInput_3items_4_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( BooleanUtils .or(new Boolean[] { Boolean.TRUE, Boolean.TRUE, Boolean.TRUE }) .booleanValue(), "False result for (true, true, true)");
    }

    @Test
    public void testOr_object_validInput_3items_5_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( ! BooleanUtils.or( new Boolean[] { Boolean.FALSE, Boolean.FALSE, Boolean.FALSE }) .booleanValue(), "True result for (false, false)");
    }

    @Test
    public void testOr_object_validInput_3items_6_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( BooleanUtils.or( new Boolean[] { Boolean.TRUE, Boolean.TRUE, Boolean.FALSE }) .booleanValue(), "False result for (true, true, false)");
    }

    @Test
    public void testOr_object_validInput_3items_7_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( BooleanUtils.or( new Boolean[] { Boolean.TRUE, Boolean.FALSE, Boolean.TRUE }) .booleanValue(), "False result for (true, false, true)");
    }

    @Test
    public void testOr_object_validInput_3items_8_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( BooleanUtils.or( new Boolean[] { Boolean.FALSE, Boolean.TRUE, Boolean.TRUE }) .booleanValue(), "False result for (false, true, true)");
    }

    @Test
    public void testOr_primitive_emptyInput_1_oe() throws Exception {
        try {
    BooleanUtils.or(new boolean[] {});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testOr_primitive_nullInput_1_oe() throws Exception {
        try {
    BooleanUtils.or((boolean[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testOr_primitive_validInput_2items_1_oe() {
        assertTrue( BooleanUtils.or(new boolean[] { true, true }), "False result for (true, true)");
    }

    @Test
    public void testOr_primitive_validInput_2items_2_oe() {
        // removed other assertion

        assertTrue( ! BooleanUtils.or(new boolean[] { false, false }), "True result for (false, false)");
    }

    @Test
    public void testOr_primitive_validInput_2items_3_oe() {
        // removed other assertion

        // removed other assertion

        assertTrue( BooleanUtils.or(new boolean[] { true, false }), "False result for (true, false)");
    }

    @Test
    public void testOr_primitive_validInput_2items_4_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( BooleanUtils.or(new boolean[] { false, true }), "False result for (false, true)");
    }

    @Test
    public void testOr_primitive_validInput_3items_1_oe() {
        assertTrue( BooleanUtils.or(new boolean[] { false, false, true }), "False result for (false, false, true)");
    }

    @Test
    public void testOr_primitive_validInput_3items_2_oe() {
        // removed other assertion

        assertTrue( BooleanUtils.or(new boolean[] { false, true, false }), "False result for (false, true, false)");
    }

    @Test
    public void testOr_primitive_validInput_3items_3_oe() {
        // removed other assertion

        // removed other assertion

        assertTrue( BooleanUtils.or(new boolean[] { true, false, false }), "False result for (true, false, false)");
    }

    @Test
    public void testOr_primitive_validInput_3items_4_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( BooleanUtils.or(new boolean[] { true, true, true }), "False result for (true, true, true)");
    }

    @Test
    public void testOr_primitive_validInput_3items_5_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( ! BooleanUtils.or(new boolean[] { false, false, false }), "True result for (false, false)");
    }

    @Test
    public void testOr_primitive_validInput_3items_6_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( BooleanUtils.or(new boolean[] { true, true, false }), "False result for (true, true, false)");
    }

    @Test
    public void testOr_primitive_validInput_3items_7_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( BooleanUtils.or(new boolean[] { true, false, true }), "False result for (true, false, true)");
    }

    @Test
    public void testOr_primitive_validInput_3items_8_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue( BooleanUtils.or(new boolean[] { false, true, true }), "False result for (false, true, true)");
    }

    @Test
    public void testXor_object_emptyInput_1_oe() throws Exception {
        try {
    BooleanUtils.xor(new Boolean[] {});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testXor_object_nullElementInput_1_oe() throws Exception {
        try {
    BooleanUtils.xor(new Boolean[] {null});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testXor_object_nullInput_1_oe() throws Exception {
        try {
    BooleanUtils.xor((Boolean[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testXor_object_validInput_2items_1_oe() {
        assertEquals( false ^ false, BooleanUtils.xor(new Boolean[] { Boolean.FALSE, Boolean.FALSE }).booleanValue(), "false ^ false");
    }

    @Test
    public void testXor_object_validInput_2items_2_oe() {
        // removed other assertion

        assertEquals( false ^ true, BooleanUtils.xor(new Boolean[] { Boolean.FALSE, Boolean.TRUE }).booleanValue(), "false ^ true");
    }

    @Test
    public void testXor_object_validInput_2items_3_oe() {
        // removed other assertion

        // removed other assertion

        assertEquals( true ^ false, BooleanUtils.xor(new Boolean[] { Boolean.TRUE, Boolean.FALSE }).booleanValue(), "true ^ false");
    }

    @Test
    public void testXor_object_validInput_2items_4_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals( true ^ true, BooleanUtils.xor(new Boolean[] { Boolean.TRUE, Boolean.TRUE }).booleanValue(), "true ^ true");
    }

    @Test
    public void testXor_object_validInput_3items_1_oe() {
        assertEquals( false ^ false ^ false, BooleanUtils.xor( new Boolean[] { Boolean.FALSE, Boolean.FALSE, Boolean.FALSE }) .booleanValue(), "false ^ false ^ false");
    }

    @Test
    public void testXor_object_validInput_3items_2_oe() {
        // removed other assertion

        assertEquals( false ^ false ^ true, BooleanUtils .xor( new Boolean[] { Boolean.FALSE, Boolean.FALSE, Boolean.TRUE }) .booleanValue(), "false ^ false ^ true");
    }

    @Test
    public void testXor_object_validInput_3items_3_oe() {
        // removed other assertion

        // removed other assertion

        assertEquals( false ^ true ^ false, BooleanUtils .xor( new Boolean[] { Boolean.FALSE, Boolean.TRUE, Boolean.FALSE }) .booleanValue(), "false ^ true ^ false");
    }

    @Test
    public void testXor_object_validInput_3items_4_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals( true ^ false ^ false, BooleanUtils .xor( new Boolean[] { Boolean.TRUE, Boolean.FALSE, Boolean.FALSE }) .booleanValue(), "true ^ false ^ false");
    }

    @Test
    public void testXor_object_validInput_3items_5_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals( true ^ false ^ true, BooleanUtils.xor( new Boolean[] { Boolean.TRUE, Boolean.FALSE, Boolean.TRUE }) .booleanValue(), "true ^ false ^ true");
    }

    @Test
    public void testXor_object_validInput_3items_6_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals( true ^ true ^ false, BooleanUtils.xor( new Boolean[] { Boolean.TRUE, Boolean.TRUE, Boolean.FALSE }) .booleanValue(), "true ^ true ^ false");
    }

    @Test
    public void testXor_object_validInput_3items_7_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals( false ^ true ^ true, BooleanUtils.xor( new Boolean[] { Boolean.FALSE, Boolean.TRUE, Boolean.TRUE }) .booleanValue(), "false ^ true ^ true");
    }

    @Test
    public void testXor_object_validInput_3items_8_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals( true ^ true ^ true, BooleanUtils.xor( new Boolean[] { Boolean.TRUE, Boolean.TRUE, Boolean.TRUE }) .booleanValue(), "true ^ true ^ true");
    }

    @Test
    public void testXor_primitive_emptyInput_1_oe() throws Exception {
        try {
    BooleanUtils.xor(new boolean[] {});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testXor_primitive_nullInput_1_oe() throws Exception {
        try {
    BooleanUtils.xor((boolean[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testXor_primitive_validInput_2items_1_oe() {
        assertEquals( true ^ true, BooleanUtils.xor(new boolean[] { true, true }), "true ^ true");
    }

    @Test
    public void testXor_primitive_validInput_2items_2_oe() {
        // removed other assertion

        assertEquals( false ^ false, BooleanUtils.xor(new boolean[] { false, false }), "false ^ false");
    }

    @Test
    public void testXor_primitive_validInput_2items_3_oe() {
        // removed other assertion

        // removed other assertion

        assertEquals( true ^ false, BooleanUtils.xor(new boolean[] { true, false }), "true ^ false");
    }

    @Test
    public void testXor_primitive_validInput_2items_4_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals( false ^ true, BooleanUtils.xor(new boolean[] { false, true }), "false ^ true");
    }

    @Test
    public void testXor_primitive_validInput_3items_1_oe() {
        assertEquals( false ^ false ^ false, BooleanUtils.xor(new boolean[] { false, false, false }), "false ^ false ^ false");
    }

    @Test
    public void testXor_primitive_validInput_3items_2_oe() {
        // removed other assertion

        assertEquals( false ^ false ^ true, BooleanUtils.xor(new boolean[] { false, false, true }), "false ^ false ^ true");
    }

    @Test
    public void testXor_primitive_validInput_3items_3_oe() {
        // removed other assertion

        // removed other assertion

        assertEquals( false ^ true ^ false, BooleanUtils.xor(new boolean[] { false, true, false }), "false ^ true ^ false");
    }

    @Test
    public void testXor_primitive_validInput_3items_4_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals( false ^ true ^ true, BooleanUtils.xor(new boolean[] { false, true, true }), "false ^ true ^ true");
    }

    @Test
    public void testXor_primitive_validInput_3items_5_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals( true ^ false ^ false, BooleanUtils.xor(new boolean[] { true, false, false }), "true ^ false ^ false");
    }

    @Test
    public void testXor_primitive_validInput_3items_6_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals( true ^ false ^ true, BooleanUtils.xor(new boolean[] { true, false, true }), "true ^ false ^ true");
    }

    @Test
    public void testXor_primitive_validInput_3items_7_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals( true ^ true ^ false, BooleanUtils.xor(new boolean[] { true, true, false }), "true ^ true ^ false");
    }

    @Test
    public void testXor_primitive_validInput_3items_8_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals( true ^ true ^ true, BooleanUtils.xor(new boolean[] { true, true, true }), "true ^ true ^ true");
    }

}
