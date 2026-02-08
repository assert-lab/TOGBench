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
package org.apache.commons.rng.core.util;

import org.apache.commons.math3.util.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link NumberFactory}.
 */
class NumberFactoryTest_OE25Dev {
    /** sizeof(int). */
    private static final int INT_SIZE = 4;
    /** sizeof(long). */
    private static final int LONG_SIZE = 8;

    /** Test values. */
    private static final long[] LONG_TEST_VALUES = new long[] {0L, 1L, -1L, 19337L, 1234567891011213L,
        -11109876543211L, Long.valueOf(Integer.MAX_VALUE), Long.valueOf(Integer.MIN_VALUE), Long.MAX_VALUE,
        Long.MIN_VALUE, 0x9e3779b97f4a7c13L};
    /** Test values. */
    private static final int[] INT_TEST_VALUES = new int[] {0, 1, -1, 19337, 1234567891, -1110987656,
        Integer.MAX_VALUE, Integer.MIN_VALUE, 0x9e3779b9};

    /**
     * Test different methods for generation of a {@code float} from a {@code int}. The output
     * value should be in the range between 0 and 1.
     */

    /**
     * Test different methods for generation of a {@code double} from a {@code long}. The output
     * value should be in the range between 0 and 1.
     */

    /**
     * Assert that the value is close to but <strong>not above</strong> 1. This is used to test
     * the output from methods that produce a {@code float} value that must be in the range
     * between 0 and 1.
     *
     * @param value the value
     * @param maxUlps {@code (maxUlps - 1)} is the number of floating point values between x and y.
     * @see Precision#equals(float, float, int)
     */
    private static void assertCloseToNotAbove1(float value, int maxUlps) {
        Assertions.assertTrue(value <= 1.0f, "Not <= 1.0f");
        Assertions.assertTrue(Precision.equals(1.0f,value,maxUlps),()-> "Not equal to 1.0f within units of least precision: " + maxUlps);
    }

    /**
     * Assert that the value is close to but <strong>not above</strong> 1. This is used to test
     * the output from methods that produce a {@code double} value that must be in the range
     * between 0 and 1.
     *
     * @param value the value
     * @param maxUlps {@code (maxUlps - 1)} is the number of floating point values between x and y.
     * @see Precision#equals(double, double, int)
     */
    private static void assertCloseToNotAbove1(double value, int maxUlps) {
        Assertions.assertTrue(value <= 1.0, "Not <= 1.0");
        Assertions.assertTrue(Precision.equals(1.0,value,maxUlps),()-> "Not equal to 1.0 within units of least precision: " + maxUlps);
    }

    @Test
    void testMakeBooleanFromInt_1_oe() {
        // Test if the bit is set differently then the booleans are opposite
        final boolean b1 = NumberFactory.makeBoolean(0);
        final boolean b2 = NumberFactory.makeBoolean(0xffffffff);
        Assertions.assertNotEquals(b1, b2);
    }

    @Test
    void testMakeBooleanFromLong_1_oe() {
        // Test if the bit is set differently then the booleans are opposite
        final boolean b1 = NumberFactory.makeBoolean(0L);
        final boolean b2 = NumberFactory.makeBoolean(0xffffffffffffffffL);
        Assertions.assertNotEquals(b1, b2);
    }

    @Test
    void testMakeIntFromLong_1_oe() {
        // Test the high order bits and low order bits are xor'd together
        Assertions.assertEquals(0xffffffff, NumberFactory.makeInt(0xffffffff00000000L));
    }

    @Test
    void testMakeIntFromLong_2_oe() {
        // Test the high order bits and low order bits are xor'd together
        // removed other assertion
        Assertions.assertEquals(0x00000000, NumberFactory.makeInt(0xffffffffffffffffL));
    }

    @Test
    void testMakeIntFromLong_3_oe() {
        // Test the high order bits and low order bits are xor'd together
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0xffffffff, NumberFactory.makeInt(0x00000000ffffffffL));
    }

    @Test
    void testMakeIntFromLong_4_oe() {
        // Test the high order bits and low order bits are xor'd together
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0x00000000, NumberFactory.makeInt(0x0000000000000000L));
    }

    @Test
    void testMakeIntFromLong_5_oe() {
        // Test the high order bits and low order bits are xor'd together
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0x0f0f0f0f, NumberFactory.makeInt(0x0f0f0f0f00000000L));
    }

    @Test
    void testMakeIntFromLong_6_oe() {
        // Test the high order bits and low order bits are xor'd together
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0xf0f0f0f0, NumberFactory.makeInt(0x00000000f0f0f0f0L));
    }

    @Test
    void testMakeIntFromLong_7_oe() {
        // Test the high order bits and low order bits are xor'd together
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0x00000000, NumberFactory.makeInt(0x0f0f0f0f0f0f0f0fL));
    }

    @Test
    void testMakeIntFromLong_8_oe() {
        // Test the high order bits and low order bits are xor'd together
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0xffffffff, NumberFactory.makeInt(0x0f0f0f0ff0f0f0f0L));
    }

    @Test
    void testExtractLoExtractHi_1_oe() {
        for (long v : LONG_TEST_VALUES) {
            final int vL = NumberFactory.extractLo(v);
            final int vH = NumberFactory.extractHi(v);

            final long actual = (((long) vH) << 32) | (vL & 0xffffffffL);
            Assertions.assertEquals(v, actual);
    }
    }

    @Test
    void testLong2Long_1_oe() {
        for (long v : LONG_TEST_VALUES) {
            final int vL = NumberFactory.extractLo(v);
            final int vH = NumberFactory.extractHi(v);

            Assertions.assertEquals(v, NumberFactory.makeLong(vH, vL));
    }
    }

    @Test
    void testLongToByteArraySignificanceOrder_1_oe() {
        // Start at the least significant bit
        long value = 1;
        for (int i = 0; i < LONG_SIZE; i++) {
            final byte[] b = NumberFactory.makeByteArray(value);
            for (int j = 0; j < LONG_SIZE; j++) {
                // Only one byte should be non zero
                Assertions.assertEquals(b[j] != 0, j == i);
    }
    }
    }

    @Test
    void testLongFromByteArray2Long_1_oe() {
        for (long expected : LONG_TEST_VALUES) {
            final byte[] b = NumberFactory.makeByteArray(expected);
            Assertions.assertEquals(expected, NumberFactory.makeLong(b));
    }
    }

    @Test
    void testLongArrayFromByteArray2LongArray_1_oe() {
        final byte[] b = NumberFactory.makeByteArray(LONG_TEST_VALUES);
        Assertions.assertArrayEquals(LONG_TEST_VALUES, NumberFactory.makeLongArray(b));
    }

    @Test
    void testLongArrayToByteArrayMatchesLongToByteArray_1_oe() {
        // Test individually the bytes are the same as the array conversion
        for (int i = 0; i < LONG_TEST_VALUES.length; i++) {
            final byte[] b1 = NumberFactory.makeByteArray(LONG_TEST_VALUES[i]);
            final byte[] b2 = NumberFactory.makeByteArray(new long[] {LONG_TEST_VALUES[i]});
            Assertions.assertArrayEquals(b1, b2);
    }
    }

    @Test
    void testIntToByteArraySignificanceOrder_1_oe() {
        // Start at the least significant bit
        int value = 1;
        for (int i = 0; i < INT_SIZE; i++) {
            final byte[] b = NumberFactory.makeByteArray(value);
            for (int j = 0; j < INT_SIZE; j++) {
                // Only one byte should be non zero
                Assertions.assertEquals(b[j] != 0, j == i);
    }
    }
    }

    @Test
    void testIntFromByteArray2Int_1_oe() {
        for (int expected : INT_TEST_VALUES) {
            final byte[] b = NumberFactory.makeByteArray(expected);
            Assertions.assertEquals(expected, NumberFactory.makeInt(b));
    }
    }

    @Test
    void testIntArrayFromByteArray2IntArray_1_oe() {
        final byte[] b = NumberFactory.makeByteArray(INT_TEST_VALUES);
        Assertions.assertArrayEquals(INT_TEST_VALUES, NumberFactory.makeIntArray(b));
    }

    @Test
    void testIntArrayToByteArrayMatchesIntToByteArray_1_oe() {
        // Test individually the bytes are the same as the array conversion
        for (int i = 0; i < INT_TEST_VALUES.length; i++) {
            final byte[] b1 = NumberFactory.makeByteArray(INT_TEST_VALUES[i]);
            final byte[] b2 = NumberFactory.makeByteArray(new int[] {INT_TEST_VALUES[i]});
            Assertions.assertArrayEquals(b1, b2);
    }
    }

    @Test
    void testMakeIntPrecondition1_1_oe() {
        for (int i = 0; i <= 10; i++) {
            final byte[] bytes = new byte[i];
            if (i != INT_SIZE) {
                Assertions.assertThrows(IllegalArgumentException.class, () -> NumberFactory.makeInt(bytes));
    }
    }
    }

    @Test
    void testMakeIntPrecondition1_2_oe() {
        for (int i = 0; i <= 10; i++) {
            final byte[] bytes = new byte[i];
            if (i != INT_SIZE) {
                // removed other assertion
            } else {
                Assertions.assertEquals(0, NumberFactory.makeInt(bytes));
    }
    }
    }

    @Test
    void testMakeIntArrayPrecondition1_1_oe() {
        for (int i = 0; i <= 20; i++) {
            final byte[] bytes = new byte[i];
            if (i != 0 && i % INT_SIZE != 0) {
                Assertions.assertThrows(IllegalArgumentException.class, () -> NumberFactory.makeIntArray(bytes));
    }
    }
    }

    @Test
    void testMakeIntArrayPrecondition1_2_oe() {
        for (int i = 0; i <= 20; i++) {
            final byte[] bytes = new byte[i];
            if (i != 0 && i % INT_SIZE != 0) {
                // removed other assertion
            } else {
                Assertions.assertArrayEquals(new int[i / INT_SIZE], NumberFactory.makeIntArray(bytes));
    }
    }
    }

    @Test
    void testMakeLongPrecondition1_1_oe() {
        for (int i = 0; i <= 10; i++) {
            final byte[] bytes = new byte[i];
            if (i != LONG_SIZE) {
                Assertions.assertThrows(IllegalArgumentException.class, () -> NumberFactory.makeLong(bytes));
    }
    }
    }

    @Test
    void testMakeLongPrecondition1_2_oe() {
        for (int i = 0; i <= 10; i++) {
            final byte[] bytes = new byte[i];
            if (i != LONG_SIZE) {
                // removed other assertion
            } else {
                Assertions.assertEquals(0L, NumberFactory.makeLong(bytes));
    }
    }
    }

    @Test
    void testMakeLongArrayPrecondition1_1_oe() {
        for (int i = 0; i <= 20; i++) {
            final byte[] bytes = new byte[i];
            if (i != 0 && i % LONG_SIZE != 0) {
                Assertions.assertThrows(IllegalArgumentException.class, () -> NumberFactory.makeLongArray(bytes));
    }
    }
    }

    @Test
    void testMakeLongArrayPrecondition1_2_oe() {
        for (int i = 0; i <= 20; i++) {
            final byte[] bytes = new byte[i];
            if (i != 0 && i % LONG_SIZE != 0) {
                // removed other assertion
            } else {
                Assertions.assertArrayEquals(new long[i / LONG_SIZE], NumberFactory.makeLongArray(bytes));
    }
    }
    }

    @Test
    void testFloatGenerationMethods_4_oe() {
        final int allBits = 0xffffffff;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final int noBits = 0;
        Assertions.assertEquals(0.0f, (noBits >>> 9) * 0x1.0p-23f);
    }

    @Test
    void testFloatGenerationMethods_5_oe() {
        final int allBits = 0xffffffff;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final int noBits = 0;
        // removed other assertion
        Assertions.assertEquals(0.0f, (noBits >>> 8) * 0x1.0p-24f);
    }

    @Test
    void testFloatGenerationMethods_6_oe() {
        final int allBits = 0xffffffff;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final int noBits = 0;
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0f, Float.intBitsToFloat(0x7f << 23 | noBits >>> 9) - 1.0f);
    }

    @Test
    void testDoubleGenerationMethods_4_oe() {
        final long allBits = 0xffffffffffffffffL;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final long noBits = 0;
        Assertions.assertEquals(0.0, (noBits >>> 12) * 0x1.0p-52d);
    }

    @Test
    void testDoubleGenerationMethods_5_oe() {
        final long allBits = 0xffffffffffffffffL;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final long noBits = 0;
        // removed other assertion
        Assertions.assertEquals(0.0, (noBits >>> 11) * 0x1.0p-53d);
    }

    @Test
    void testDoubleGenerationMethods_6_oe() {
        final long allBits = 0xffffffffffffffffL;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final long noBits = 0;
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0, Double.longBitsToDouble(0x3ffL << 52 | noBits >>> 12) - 1.0);
    }

    @Test
    void testMakeDoubleFromLong_2_oe() {
        final long allBits = 0xffffffffffffffffL;
        final long noBits = 0;
        // Within 1 ULP of 1.0
        // removed other assertion
        Assertions.assertEquals(0.0, NumberFactory.makeDouble(noBits));
    }

    @Test
    void testMakeDoubleFromIntInt_2_oe() {
        final int allBits = 0xffffffff;
        final int noBits = 0;
        // Within 1 ULP of 1.0
        // removed other assertion
        Assertions.assertEquals(0.0, NumberFactory.makeDouble(noBits, noBits));
    }

    @Test
    void testMakeFloatFromInt_2_oe() {
        final int allBits = 0xffffffff;
        final int noBits = 0;
        // Within 1 ULP of 1.0f
        // removed other assertion
        Assertions.assertEquals(0.0f, NumberFactory.makeFloat(noBits), 0);
    }

    @Test
    void testFloatGenerationMethods_1_oe_1_oe() {
        final int allBits = 0xffffffff;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
                final float value0 = (allBits >>> 9) * 0x1.0p-23f;
        final int maxUlps0 = 2;
        Assertions.assertTrue(value0 <= 1.0f, "Not <= 1.0f");
    }

    @Test
    void testFloatGenerationMethods_1_oe_2_oe() {
        final int allBits = 0xffffffff;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
                final float value0 = (allBits >>> 9) * 0x1.0p-23f;
        final int maxUlps0 = 2;
        // removed other assertion
                Assertions.assertTrue(Precision.equals(1.0f,value0,maxUlps0),()-> "Not equal to 1.0f within units of least precision: " + maxUlps0);
    }

    @Test
    void testFloatGenerationMethods_2_oe_1_oe() {
        final int allBits = 0xffffffff;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
        // removed other assertion
                final float value0 = (allBits >>> 8) * 0x1.0p-24f;
        final int maxUlps0 = 1;
        Assertions.assertTrue(value0 <= 1.0f, "Not <= 1.0f");
    }

    @Test
    void testFloatGenerationMethods_2_oe_2_oe() {
        final int allBits = 0xffffffff;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
        // removed other assertion
                final float value0 = (allBits >>> 8) * 0x1.0p-24f;
        final int maxUlps0 = 1;
        // removed other assertion
                Assertions.assertTrue(Precision.equals(1.0f,value0,maxUlps0),()-> "Not equal to 1.0f within units of least precision: " + maxUlps0);
    }

    @Test
    void testFloatGenerationMethods_3_oe_1_oe() {
        final int allBits = 0xffffffff;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
        // removed other assertion
        // removed other assertion
                final float value0 = Float.intBitsToFloat(0x7f << 23 | allBits >>> 9) - 1.0f;
        final int maxUlps0 = 2;
        Assertions.assertTrue(value0 <= 1.0f, "Not <= 1.0f");
    }

    @Test
    void testFloatGenerationMethods_3_oe_2_oe() {
        final int allBits = 0xffffffff;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
        // removed other assertion
        // removed other assertion
                final float value0 = Float.intBitsToFloat(0x7f << 23 | allBits >>> 9) - 1.0f;
        final int maxUlps0 = 2;
        // removed other assertion
                Assertions.assertTrue(Precision.equals(1.0f,value0,maxUlps0),()-> "Not equal to 1.0f within units of least precision: " + maxUlps0);
    }

    @Test
    void testDoubleGenerationMethods_1_oe_1_oe() {
        final long allBits = 0xffffffffffffffffL;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
                final float value0 = (allBits >>> 12) * 0x1.0p-52d;
        final int maxUlps0 = 2;
        Assertions.assertTrue(value0 <= 1.0f, "Not <= 1.0f");
    }

    @Test
    void testDoubleGenerationMethods_1_oe_2_oe() {
        final long allBits = 0xffffffffffffffffL;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
                final float value0 = (allBits >>> 12) * 0x1.0p-52d;
        final int maxUlps0 = 2;
        // removed other assertion
                Assertions.assertTrue(Precision.equals(1.0f,value0,maxUlps0),()-> "Not equal to 1.0f within units of least precision: " + maxUlps0);
    }

    @Test
    void testDoubleGenerationMethods_2_oe_1_oe() {
        final long allBits = 0xffffffffffffffffL;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
        // removed other assertion
                final float value0 = (allBits >>> 11) * 0x1.0p-53d;
        final int maxUlps0 = 1;
        Assertions.assertTrue(value0 <= 1.0f, "Not <= 1.0f");
    }

    @Test
    void testDoubleGenerationMethods_2_oe_2_oe() {
        final long allBits = 0xffffffffffffffffL;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
        // removed other assertion
                final float value0 = (allBits >>> 11) * 0x1.0p-53d;
        final int maxUlps0 = 1;
        // removed other assertion
                Assertions.assertTrue(Precision.equals(1.0f,value0,maxUlps0),()-> "Not equal to 1.0f within units of least precision: " + maxUlps0);
    }

    @Test
    void testDoubleGenerationMethods_3_oe_1_oe() {
        final long allBits = 0xffffffffffffffffL;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
        // removed other assertion
        // removed other assertion
                final float value0 = Double.longBitsToDouble(0x3ffL << 52 | allBits >>> 12) - 1.0;
        final int maxUlps0 = 2;
        Assertions.assertTrue(value0 <= 1.0f, "Not <= 1.0f");
    }

    @Test
    void testDoubleGenerationMethods_3_oe_2_oe() {
        final long allBits = 0xffffffffffffffffL;

        // Not capable of generating 1. Set the delta with 1 or 2 ULP of 1.
        // removed other assertion
        // removed other assertion
                final float value0 = Double.longBitsToDouble(0x3ffL << 52 | allBits >>> 12) - 1.0;
        final int maxUlps0 = 2;
        // removed other assertion
                Assertions.assertTrue(Precision.equals(1.0f,value0,maxUlps0),()-> "Not equal to 1.0f within units of least precision: " + maxUlps0);
    }

    @Test
    void testMakeDoubleFromLong_1_oe_1_oe() {
        final long allBits = 0xffffffffffffffffL;
        final long noBits = 0;
        // Within 1 ULP of 1.0
                final float value0 = NumberFactory.makeDouble(allBits);
        final int maxUlps0 = 1;
        Assertions.assertTrue(value0 <= 1.0f, "Not <= 1.0f");
    }

    @Test
    void testMakeDoubleFromLong_1_oe_2_oe() {
        final long allBits = 0xffffffffffffffffL;
        final long noBits = 0;
        // Within 1 ULP of 1.0
                final float value0 = NumberFactory.makeDouble(allBits);
        final int maxUlps0 = 1;
        // removed other assertion
                Assertions.assertTrue(Precision.equals(1.0f,value0,maxUlps0),()-> "Not equal to 1.0f within units of least precision: " + maxUlps0);
    }

    @Test
    void testMakeDoubleFromIntInt_1_oe_1_oe() {
        final int allBits = 0xffffffff;
        final int noBits = 0;
        // Within 1 ULP of 1.0
                final float value0 = NumberFactory.makeDouble(allBits, allBits);
        final int maxUlps0 = 1;
        Assertions.assertTrue(value0 <= 1.0f, "Not <= 1.0f");
    }

    @Test
    void testMakeDoubleFromIntInt_1_oe_2_oe() {
        final int allBits = 0xffffffff;
        final int noBits = 0;
        // Within 1 ULP of 1.0
                final float value0 = NumberFactory.makeDouble(allBits, allBits);
        final int maxUlps0 = 1;
        // removed other assertion
                Assertions.assertTrue(Precision.equals(1.0f,value0,maxUlps0),()-> "Not equal to 1.0f within units of least precision: " + maxUlps0);
    }

    @Test
    void testMakeFloatFromInt_1_oe_1_oe() {
        final int allBits = 0xffffffff;
        final int noBits = 0;
        // Within 1 ULP of 1.0f
                final float value0 = NumberFactory.makeFloat(allBits);
        final int maxUlps0 = 1;
        Assertions.assertTrue(value0 <= 1.0f, "Not <= 1.0f");
    }

    @Test
    void testMakeFloatFromInt_1_oe_2_oe() {
        final int allBits = 0xffffffff;
        final int noBits = 0;
        // Within 1 ULP of 1.0f
                final float value0 = NumberFactory.makeFloat(allBits);
        final int maxUlps0 = 1;
        // removed other assertion
                Assertions.assertTrue(Precision.equals(1.0f,value0,maxUlps0),()-> "Not equal to 1.0f within units of least precision: " + maxUlps0);
    }

}
