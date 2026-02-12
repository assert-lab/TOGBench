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
package org.apache.commons.rng.simple.internal;

import java.util.Map;
import java.util.HashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.core.source32.IntProvider;
import org.apache.commons.rng.core.source64.RandomLongSource;
import org.apache.commons.rng.core.util.NumberFactory;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests for {@link SeedFactory}.
 */
class SeedFactoryTest_OE25Dev {
    @Test
    void testCreateLong() {
        final Map<Long, Integer> values = new HashMap<>();

        final int n = 100000;
        for (int i = 0; i < n; i++) {
            final long v = SeedFactory.createLong();

            Integer count = values.get(v);
            if (count == null) {
                count = 0;
            }
            values.put(v, count + 1);
        }

        // Check that all seeds are different.
        assertDifferentValues(values);
    }

    @Test
    void testCreateIntArray() {
        final Map<Long, Integer> values = new HashMap<>();

        for (int i = 0; i < 50000; i++) {
            final int[] a = SeedFactory.createIntArray(2);
            final long v = NumberFactory.makeLong(a[0], a[1]);
            Integer count = values.get(v);
            if (count == null) {
                count = 0;
            }
            values.put(v, count + 1);
        }

        // Check that all pairs in are different.
        assertDifferentValues(values);
    }

    /**
     * Asserts that all the keys in given {@code map} have their
     * value equal to 1.
     *
     * @param map Map to counts.
     */
    private static <T> void assertDifferentValues(Map<T, Integer> map) {
        final StringBuilder sb = new StringBuilder();

        int duplicates = 0;
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            final int count = entry.getValue();
            if (count <= 0) {
                throw new IllegalStateException();
            }

            if (count > 1) {
                duplicates += count - 1;
                sb.append(entry.getKey() + ": " + count + "\n");
            }
        }

        if (duplicates > 0) {
            Assertions.fail(duplicates + " duplicates\n" + sb);
        }
    }

    /**
     * Checks that the int array values can be placed into 2 bins with
     * approximately equal number of counts.
     * The test uses the expectation from a fixed-step "random walk".
     *
     * @param n The size of the array.
     */
    private static void assertCreateIntArray(int n) {
        final int[] array = SeedFactory.createIntArray(n);
        Assertions.assertEquals(n, array.length, "Incorrect array length");
        // The bit count should be 50%.
        int bitCount = 0;
        for (final int i : array) {
            bitCount += Integer.bitCount(i);
        }
        final int numberOfBits = n * Integer.SIZE;
        assertMonobit(bitCount, numberOfBits);
    }

    /**
     * Checks that the long array values can be placed into 2 bins with
     * approximately equal number of counts.
     * The test uses the expectation from a fixed-step "random walk".
     *
     * @param n The size of the array.
     */
    private static void assertCreateLongArray(int n) {
        final long[] array = SeedFactory.createLongArray(n);
        Assertions.assertEquals(n, array.length, "Incorrect array length");
        // The bit count should be 50%.
        int bitCount = 0;
        for (final long i : array) {
            bitCount += Long.bitCount(i);
        }
        final int numberOfBits = n * Long.SIZE;
        assertMonobit(bitCount, numberOfBits);
    }

    /**
     * Assert that the number of 1 bits is approximately 50%. This is based upon a
     * fixed-step "random walk" of +1/-1 from zero.
     *
     * <p>The test is equivalent to the NIST Monobit test with a fixed p-value of 0.0001.
     * The number of bits is recommended to be above 100.</p>
     *
     * @see <A
     * href="https://csrc.nist.gov/publications/detail/sp/800-22/rev-1a/final">Bassham, et
     * al (2010) NIST SP 800-22: A Statistical Test Suite for Random and Pseudorandom
     * Number Generators for Cryptographic Applications. Section 2.1.</a>
     *
     * @param bitCount The bit count.
     * @param numberOfBits Number of bits.
     */
    private static void assertMonobit(int bitCount, int numberOfBits) {
        // Convert the bit count into a number of +1/-1 steps.
        final double sum = 2.0 * bitCount - numberOfBits;
        // The reference distribution is Normal with a standard deviation of sqrt(n).
        // Check the absolute position is not too far from the mean of 0 with a fixed
        // p-value of 0.0001 taken from a 2-tailed Normal distribution. Computation of
        // the p-value requires the complimentary error function.
        // The p-value is set to be equal to a 0.01 with 1 allowed re-run.
        // (Re-runs are not configured for this test.)
        final double absSum = Math.abs(sum);
        final double max = Math.sqrt(numberOfBits) * 3.891;
        Assertions.assertTrue(absSum <= max,()-> "Walked too far astray: " + absSum + " > " + max + "(test will fail randomly about 1 in 10,000 times)");
    }

    /**
     * Assert that the SeedFactory uses the bytes exactly as generated by the
     * {@link UniformRandomProvider#nextBytes(byte[])} method (assuming they are not all zero).
     *
     * @param expected the expected
     */
    private static void assertCreateByteArray(final byte[] expected) {
        final UniformRandomProvider rng = new IntProvider() {
            @Override
            public int next() {
                Assertions.fail("This method should not be used");
                return 0;
            }

            @Override
            public void nextBytes(byte[] bytes) {
                System.arraycopy(expected, 0, bytes, 0, Math.min(expected.length, bytes.length));
            }
        };

        final byte[] seed = SeedFactory.createByteArray(rng, expected.length);
        Assertions.assertArrayEquals(expected, seed);
    }

    @Test
    void testEnsureNonZeroIntArrayIgnoresEmptySeed() {
        final int[] seed = new int[0];
        SeedFactory.ensureNonZero(seed);
        // Note: Nothing to assert.
        // This tests an ArrayIndexOutOfBoundsException does not occur.
    }

    @Test
    void testEnsureNonZeroLongArrayIgnoresEmptySeed() {
        final long[] seed = new long[0];
        SeedFactory.ensureNonZero(seed);
        // Note: Nothing to assert.
        // This tests an ArrayIndexOutOfBoundsException does not occur.
    }


}
