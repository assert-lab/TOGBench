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
package org.apache.commons.rng.sampling.distribution;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.core.source64.LongProvider;
import org.apache.commons.rng.core.source64.SplitMix64;
import org.apache.commons.rng.sampling.RandomAssert;
import org.apache.commons.rng.simple.RandomSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;

/**
 * Test for the {@link UniformLongSampler}. The tests hit edge cases for the sampler
 * and demonstrates uniformity of output when the underlying RNG output is uniform.
 *
 * <p>Note: No statistical tests for uniformity are performed on the output. The tests
 * are constructed on the premise that the underlying sampling methods correctly
 * use the random bits from {@link UniformRandomProvider}. Correctness
 * for a small range is tested against {@link UniformRandomProvider#nextLong(long)}
 * and correctness for a large range is tested that the {@link UniformRandomProvider#nextLong()}
 * is within the range limits. Power of two ranges are tested against a bit shift
 * of a random long.
 */
class UniformLongSamplerTest_OE25Dev {
    /**
     * Test the constructor with a bad range.
     */

    /**
     * Test samples with a full long range.
     * The output should be the same as the long values produced from a RNG.
     */

    /**
     * Test samples with a non-power of 2 range.
     * The output should be the same as the long values produced from a RNG
     * based on o.a.c.rng.core.BaseProvider as the rejection algorithm is
     * the same.
     */

    /**
     * Creates a RNG which will return full bits for the first sample.
     *
     * @return the uniform random provider
     */
    private static UniformRandomProvider createRngWithFullBitsOnFirstCall() {
        return new SplitMix64(0L) {
            private int i;
            @Override
            public long next() {
                return i++ == 0 ? -1L : super.next();
            }
        };
    }

    /**
     * Test samples with a power of 2 range.
     * This tests the minimum and maximum output should be the range limits.
     */

    /**
     * Test samples with a power of 2 range.
     * This tests the output is created using a bit shift.
     */

    /**
     * Test samples with a large non-power of 2 range.
     * This tests the large range algorithm uses a rejection method.
     */

    private static void assertOffsetSamples(long range) {
        final Long seed = RandomSource.createLong();
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(seed);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(seed);
        final UniformRandomProvider rng3 = RandomSource.SPLIT_MIX_64.create(seed);

        // Since the upper limit is inclusive
        range = range - 1;
        final long offsetLo = -13;
        final long offsetHi = 42;
        final UniformLongSampler sampler = UniformLongSampler.of(rng1, 0, range);
        final UniformLongSampler samplerLo = UniformLongSampler.of(rng2, offsetLo, offsetLo + range);
        final UniformLongSampler samplerHi = UniformLongSampler.of(rng3, offsetHi, offsetHi + range);
        for (int i = 0; i < 10; i++) {
            final long sample1 = sampler.sample();
            final long sample2 = samplerLo.sample();
            final long sample3 = samplerHi.sample();
            Assertions.assertEquals(sample1 + offsetLo, sample2, "Incorrect negative offset sample");
            Assertions.assertEquals(sample1 + offsetHi, sample3, "Incorrect positive offset sample");
        }
    }

    /**
     * Test the sample uniformity when using a small range that is a power of 2.
     */

    /**
     * Test the SharedStateSampler implementation returns the same sequence as the source sampler
     * when using an identical RNG.
     *
     * @param lower Lower.
     * @param upper Upper.
     */
    private static void assertSharedStateSampler(long lower, long upper) {
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
        final UniformLongSampler sampler1 = UniformLongSampler.of(rng1, lower, upper);
        final UniformLongSampler sampler2 = sampler1.withUniformRandomProvider(rng2);
        RandomAssert.assertProduceSameSequence(sampler1, sampler2);
    }

    /**
     * Test the toString method contains the term "uniform". This is true of all samplers
     * even for a fixed sample from a range of 1.
     *
     * @param lower Lower.
     * @param upper Upper.
     */
    private static void assertToString(long lower, long upper) {
        final UniformRandomProvider rng = RandomSource.SPLIT_MIX_64.create(0L);
        final UniformLongSampler sampler = UniformLongSampler.of(rng, lower, upper);
        Assertions.assertTrue(sampler.toString().toLowerCase(Locale.US).contains("uniform"));
    }

    @Test
    void testConstructorThrowsWithLowerAboveUpper_1_oe() {
         long upper = 55;
         long lower = upper + 1;
         UniformRandomProvider rng = RandomSource.SPLIT_MIX_64.create(0L);
        try {
    UniformLongSampler.of(rng, lower, upper);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
