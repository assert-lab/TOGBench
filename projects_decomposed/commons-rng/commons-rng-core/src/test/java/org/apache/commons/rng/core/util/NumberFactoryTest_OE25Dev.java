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

    @Test
    void testLongToByteArraySignificanceOrder() {
        // Start at the least significant bit
        long value = 1;
        for (int i = 0; i < LONG_SIZE; i++) {
            final byte[] b = NumberFactory.makeByteArray(value);
            for (int j = 0; j < LONG_SIZE; j++) {
                // Only one byte should be non zero
                Assertions.assertEquals(b[j] != 0, j == i);
            }
            // Shift to the next byte
            value <<= 8;
        }
    }

    @Test
    void testIntToByteArraySignificanceOrder() {
        // Start at the least significant bit
        int value = 1;
        for (int i = 0; i < INT_SIZE; i++) {
            final byte[] b = NumberFactory.makeByteArray(value);
            for (int j = 0; j < INT_SIZE; j++) {
                // Only one byte should be non zero
                Assertions.assertEquals(b[j] != 0, j == i);
            }
            // Shift to the next byte
            value <<= 8;
        }
    }

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
    void testMakeIntPrecondition1_1_oe() {
        for (int i = 0; i <= 10; i++) {
             byte[] bytes = new byte[i];
            if (i != INT_SIZE) {
                try {
    NumberFactory.makeInt(bytes);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }
    }
    }

    @Test
    void testMakeIntArrayPrecondition1_1_oe() {
        for (int i = 0; i <= 20; i++) {
             byte[] bytes = new byte[i];
            if (i != 0 && i % INT_SIZE != 0) {
                try {
    NumberFactory.makeIntArray(bytes);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }
    }
    }

    @Test
    void testMakeLongPrecondition1_1_oe() {
        for (int i = 0; i <= 10; i++) {
             byte[] bytes = new byte[i];
            if (i != LONG_SIZE) {
                try {
    NumberFactory.makeLong(bytes);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }
    }
    }

    @Test
    void testMakeLongArrayPrecondition1_1_oe() {
        for (int i = 0; i <= 20; i++) {
             byte[] bytes = new byte[i];
            if (i != 0 && i % LONG_SIZE != 0) {
                try {
    NumberFactory.makeLongArray(bytes);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }
    }
    }

}
