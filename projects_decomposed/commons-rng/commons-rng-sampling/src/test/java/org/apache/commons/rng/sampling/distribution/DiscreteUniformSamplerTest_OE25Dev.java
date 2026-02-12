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
import org.apache.commons.rng.core.source32.IntProvider;
import org.apache.commons.rng.sampling.RandomAssert;
import org.apache.commons.rng.simple.RandomSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;

/**
 * Test for the {@link DiscreteUniformSampler}. The tests hit edge cases for the sampler
 * and demonstrates uniformity of output when the underlying RNG output is uniform.
 */
class DiscreteUniformSamplerTest_OE25Dev {
    /**
     * Test the constructor with a bad range.
     */

    /**
     * Test samples with a full integer range.
     * The output should be the same as the int values produced from a RNG.
     */

    /**
     * Test samples with a non-power of 2 range.
     * The output should be the same as the long values produced from a RNG
     * based on o.a.c.rng.core.BaseProvider as the rejection algorithm is
     * the same.
     */

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

    private static void assertOffsetSamples(int range) {
        final Long seed = RandomSource.createLong();
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(seed);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(seed);
        final UniformRandomProvider rng3 = RandomSource.SPLIT_MIX_64.create(seed);

        // Since the upper limit is inclusive
        range = range - 1;
        final int offsetLo = -13;
        final int offsetHi = 42;
        final SharedStateDiscreteSampler sampler = DiscreteUniformSampler.of(rng1, 0, range);
        final SharedStateDiscreteSampler samplerLo = DiscreteUniformSampler.of(rng2, offsetLo, offsetLo + range);
        final SharedStateDiscreteSampler samplerHi = DiscreteUniformSampler.of(rng3, offsetHi, offsetHi + range);
        for (int i = 0; i < 10; i++) {
            final int sample1 = sampler.sample();
            final int sample2 = samplerLo.sample();
            final int sample3 = samplerHi.sample();
            Assertions.assertEquals(sample1 + offsetLo, sample2, "Incorrect negative offset sample");
            Assertions.assertEquals(sample1 + offsetHi, sample3, "Incorrect positive offset sample");
        }
    }

    /**
     * Test the sample uniformity when using a small range that is not a power of 2.
     */

    /**
     * Test the sample uniformity when using a small range that is a power of 2.
     */

    /**
     * Test the sample rejection when using a range that is not a power of 2. The rejection
     * algorithm of Lemire (2019) splits the entire 32-bit range into intervals of size 2^32/n. It
     * will reject the lowest value in each interval that is over sampled. This test uses 0
     * as the first value from the RNG and tests it is rejected.
     */

    @Test
    void testSharedStateSamplerWithSmallRange() {
        testSharedStateSampler(5, 67);
    }

    @Test
    void testSharedStateSamplerWithLargeRange() {
        // Set the range so rejection below or above the threshold occurs with approximately p=0.25
        testSharedStateSampler(Integer.MIN_VALUE / 2 - 1, Integer.MAX_VALUE / 2 + 1);
    }

    @Test
    void testSharedStateSamplerWithPowerOf2Range() {
        testSharedStateSampler(0, 31);
    }

    @Test
    void testSharedStateSamplerWithRangeOf1() {
        testSharedStateSampler(9, 9);
    }

    /**
     * Test the SharedStateSampler implementation.
     *
     * @param lower Lower.
     * @param upper Upper.
     */
    private static void testSharedStateSampler(int lower, int upper) {
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
        // Use instance constructor not factory constructor to exercise 1.X public API
        final SharedStateDiscreteSampler sampler1 =
            new DiscreteUniformSampler(rng1, lower, upper);
        final SharedStateDiscreteSampler sampler2 = sampler1.withUniformRandomProvider(rng2);
        RandomAssert.assertProduceSameSequence(sampler1, sampler2);
    }

    /**
     * Test the toString method contains the term "uniform". This is true of all samplers
     * even for a fixed sample from a range of 1.
     *
     * @param lower Lower.
     * @param upper Upper.
     */
    private static void assertToString(int lower, int upper) {
        final UniformRandomProvider rng = RandomSource.SPLIT_MIX_64.create(0L);
        final DiscreteUniformSampler sampler = new DiscreteUniformSampler(rng, lower, upper);
        Assertions.assertTrue(sampler.toString().toLowerCase(Locale.US).contains("uniform"));
    }

    @Test
    void testConstructorThrowsWithLowerAboveUpper_1_oe() {
         int upper = 55;
         int lower = upper + 1;
         UniformRandomProvider rng = RandomSource.SPLIT_MIX_64.create(0L);
        try {
    DiscreteUniformSampler.of(rng, lower, upper);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
