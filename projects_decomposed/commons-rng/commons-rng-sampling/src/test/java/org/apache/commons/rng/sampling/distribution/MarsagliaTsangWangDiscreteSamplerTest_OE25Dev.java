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

import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.core.source32.IntProvider;
import org.apache.commons.rng.core.source64.SplitMix64;
import org.apache.commons.rng.sampling.RandomAssert;
import org.apache.commons.rng.simple.RandomSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for the {@link MarsagliaTsangWangDiscreteSampler}. The tests hit edge cases for
 * the sampler factory methods that build the normalised probability distribution.
 *
 * <p>Statistical testing of the sampler is performed using entries in {@link DiscreteSamplersList}.</p>
 */
class MarsagliaTsangWangDiscreteSamplerTest_OE25Dev {
    @Test
    void testCreateDiscreteDistributionThrowsWithNullProbabilites() {
        assertEnumeratedSamplerConstructorThrows(null);
    }

    @Test
    void testCreateDiscreteDistributionThrowsWithZeroLengthProbabilites() {
        assertEnumeratedSamplerConstructorThrows(new double[0]);
    }

    @Test
    void testCreateDiscreteDistributionThrowsWithNegativeProbabilites() {
        assertEnumeratedSamplerConstructorThrows(new double[] {-1, 0.1, 0.2});
    }

    @Test
    void testCreateDiscreteDistributionThrowsWithNaNProbabilites() {
        assertEnumeratedSamplerConstructorThrows(new double[] {0.1, Double.NaN, 0.2});
    }

    @Test
    void testCreateDiscreteDistributionThrowsWithInfiniteProbabilites() {
        assertEnumeratedSamplerConstructorThrows(new double[] {0.1, Double.POSITIVE_INFINITY, 0.2});
    }

    @Test
    void testCreateDiscreteDistributionThrowsWithInfiniteSumProbabilites() {
        assertEnumeratedSamplerConstructorThrows(new double[] {Double.MAX_VALUE, Double.MAX_VALUE});
    }

    @Test
    void testCreateDiscreteDistributionThrowsWithZeroSumProbabilites() {
        assertEnumeratedSamplerConstructorThrows(new double[4]);
    }

    /**
     * Assert the enumerated sampler factory constructor throws an {@link IllegalArgumentException}.
     *
     * @param probabilities Probabilities.
     */
    private static void assertEnumeratedSamplerConstructorThrows(double[] probabilities) {
        final UniformRandomProvider rng = new SplitMix64(0L);
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> MarsagliaTsangWangDiscreteSampler.Enumerated.of(rng, probabilities));
    }

    /**
     * Test the {@link Object#toString()} method contains the algorithm author names.
     */

    // Sampling tests

    /**
     * Test offset samples. This test hits all code paths in the sampler for 8, 16, and 32-bit
     * storage using different offsets to control the maximum sample value.
     */

    /**
     * Creates the probabilities using zero padding below the values.
     *
     * @param offset Offset for first given probability (i.e. the zero padding size).
     * @param prob Probability values.
     * @return the zero-padded probabilities
     */
    private static double[] createProbabilities(int offset, int[] prob) {
        double[] probabilities = new double[offset + prob.length];
        for (int i = 0; i < prob.length; i++) {
            probabilities[i + offset] = prob[i];
        }
        return probabilities;
    }

    /**
     * Test samples from a distribution expressed using {@code double} probabilities.
     */

    /**
     * Test the storage requirements for a worst case set of 2^8 probabilities. This tests the
     * limits described in the class Javadoc is correct.
     */
    @Test
    void testStorageRequirements8() {
        // Max digits from 2^22:
        // (2^4 + 2^6 + 2^6 + 2^6)
        // Storage in bytes
        // = (15 + 3 * 63) * 2^8
        // = 52224 B
        // = 0.0522 MB
        checkStorageRequirements(8, 0.06);
    }

    /**
     * Test the storage requirements for a worst case set of 2^16 probabilities. This tests the
     * limits described in the class Javadoc is correct.
     */
    @Test
    void testStorageRequirements16() {
        // Max digits from 2^14:
        // (2^2 + 2^6 + 2^6)
        // Storage in bytes
        // = 2 * (3 + 2 * 63) * 2^16
        // = 16908288 B
        // = 16.91 MB
        checkStorageRequirements(16, 17.0);
    }

    /**
     * Test the storage requirements for a worst case set of 2^k probabilities. This
     * tests the limits described in the class Javadoc is correct.
     *
     * @param k Base is 2^k.
     * @param expectedLimitMB Expected limit in MB.
     */
    private static void checkStorageRequirements(int k, double expectedLimitMB) {
        // Worst case scenario is a uniform distribution of 2^k samples each with the highest
        // mask set for base 64 digits.
        // The max number of samples: 2^k
        final int maxSamples = 1 << k;

        // The highest value for each sample:
        // 2^30 / 2^k = 2^(30-k)
        // The highest mask is all bits set
        final int m = (1 << (30 - k)) - 1;

        // Check the sum is less than 2^30
        final long sum = (long) maxSamples * m;
        final int total = 1 << 30;
        Assertions.assertTrue(sum < total, "Worst case uniform distribution is above 2^30");

        // Get the digits as per the sampler and compute storage
        final int d1 = getBase64Digit(m, 1);
        final int d2 = getBase64Digit(m, 2);
        final int d3 = getBase64Digit(m, 3);
        final int d4 = getBase64Digit(m, 4);
        final int d5 = getBase64Digit(m, 5);
        // Compute storage in MB assuming 2 byte storage
        int bytes;
        if (k <= 8) {
            bytes = 1;
        } else if (k <= 16) {
            bytes = 2;
        } else {
            bytes = 4;
        }
        final double storageMB = bytes * 1e-6 * (d1 + d2 + d3 + d4 + d5) * maxSamples;
        Assertions.assertTrue(storageMB < expectedLimitMB,()-> "Worst case uniform distribution storage " + storageMB + "MB is above expected limit: " + expectedLimitMB);
    }

    /**
     * Gets the k<sup>th</sup> base 64 digit of {@code m}.
     *
     * @param m Value m.
     * @param k Digit.
     * @return the base 64 digit
     */
    private static int getBase64Digit(int m, int k) {
        return (m >>> (30 - 6 * k)) & 63;
    }

    /**
     * Test the Poisson distribution with a bad mean that is above the supported range.
     */

    /**
     * Test the Poisson distribution with a bad mean that is below the supported range.
     */

    /**
     * Test the Poisson distribution with the maximum mean.
     */
    @Test
    void testCreatePoissonDistributionWithMaximumMean() {
        final UniformRandomProvider rng = new FixedRNG();
        final double mean = 1024;
        final DiscreteSampler sampler = MarsagliaTsangWangDiscreteSampler.Poisson.of(rng, mean);
        // Note: No assertions. This will throw if the table does not sum to 2^30
        // as the RNG outputs the maximum index into the look-up tables.
        sampler.sample();
    }

    /**
     * Test the Poisson distribution with a small mean that hits the edge case where the
     * probability sum is not 2^30.
     */
    @Test
    void testCreatePoissonDistributionWithSmallMean() {
        final UniformRandomProvider rng = new FixedRNG();
        final double mean = 0.25;
        final DiscreteSampler sampler = MarsagliaTsangWangDiscreteSampler.Poisson.of(rng, mean);
        // Note: No assertions. This will throw if the table does not sum to 2^30
        // as the RNG outputs the maximum index into the look-up tables.
        sampler.sample();
    }

    /**
     * Test the Poisson distribution with a medium mean that is at the switch point
     * for how the probability distribution is computed. This hits the edge case
     * where the loop from the mean decrements to reach zero.
     */
    @Test
    void testCreatePoissonDistributionWithMediumMean() {
        final UniformRandomProvider rng = new FixedRNG();
        final double mean = 21.4;
        final DiscreteSampler sampler = MarsagliaTsangWangDiscreteSampler.Poisson.of(rng, mean);
        // Note: No assertions. This will throw if the table does not sum to 2^30
        // as the RNG outputs the maximum index into the look-up tables.
        sampler.sample();
    }

    /**
     * Test the Binomial distribution with a bad number of trials.
     */

    /**
     * Test the Binomial distribution with an unsupported number of trials.
     */

    /**
     * Test the Binomial distribution with probability {@code < 0}.
     */

    /**
     * Test the Binomial distribution with probability {@code > 1}.
     */

    /**
     * Test the Binomial distribution with distribution parameters that create a very small p(0)
     * with a high probability of success.
     */

    /**
     * Test the Binomial distribution with distribution parameters that create a p(0)
     * that is zero (thus the distribution cannot be computed).
     */

    /**
     * Test the Binomial distribution with distribution parameters that create a very small p(0)
     * with a high number of trials.
     */

    /**
     * Gets the p(0) value for the Binomial distribution.
     *
     * @param trials Number of trials.
     * @param probabilityOfSuccess Probability of success.
     * @return the p(0) value
     */
    private static double getBinomialP0(int trials, double probabilityOfSuccess) {
        return Math.exp(trials * Math.log(1 - probabilityOfSuccess));
    }

    /**
     * Test the Binomial distribution with a probability of 0 where the sampler should equal 0.
     */

    /**
     * Test the Binomial distribution with a probability of 1 where the sampler should equal
     * the number of trials.
     */

    /**
     * Test the sampler with a large number of trials. This tests the sampler can create the
     * Binomial distribution for a large size when a limiting distribution (e.g. the Normal distribution)
     * could be used instead.
     */
    @Test
    void testCreateBinomialDistributionWithLargeNumberOfTrials() {
        final UniformRandomProvider rng = new FixedRNG();
        final int trials = 65000;
        final double p = 0.01;
        final DiscreteSampler sampler = MarsagliaTsangWangDiscreteSampler.Binomial.of(rng, trials, p);
        // Note: No assertions. This will throw if the table does not sum to 2^30
        // as the RNG outputs the maximum index into the look-up tables.
        sampler.sample();
    }

    /**
     * Test the sampler with a probability of 0.5. This should hit the edge case in the loop to
     * search for the last probability of the Binomial distribution.
     */
    @Test
    void testCreateBinomialDistributionWithProbability50Percent() {
        final UniformRandomProvider rng = new FixedRNG();
        final int trials = 10;
        final double p = 0.5;
        final DiscreteSampler sampler = MarsagliaTsangWangDiscreteSampler.Binomial.of(rng, trials, p);
        // Note: No assertions. This will throw if the table does not sum to 2^30
        // as the RNG outputs the maximum index into the look-up tables.
        sampler.sample();
    }

    /**
     * Test the sampler with a probability that requires inversion has the same name for
     * {@link Object#toString()}.
     */

    /**
     * Test the SharedStateSampler implementation with the 8-bit storage implementation.
     */
    @Test
    void testSharedStateSamplerWith8bitStorage() {
        testSharedStateSampler(0, new int[] {1, 2, 3, 4, 5});
    }

    /**
     * Test the SharedStateSampler implementation with the 16-bit storage implementation.
     */
    @Test
    void testSharedStateSamplerWith16bitStorage() {
        testSharedStateSampler(1 << 8, new int[] {1, 2, 3, 4, 5});
    }

    /**
     * Test the SharedStateSampler implementation with the 32-bit storage implementation.
     */
    @Test
    void testSharedStateSamplerWith32bitStorage() {
        testSharedStateSampler(1 << 16, new int[] {1, 2, 3, 4, 5});
    }

    /**
     * Test the SharedStateSampler implementation using zero padded probabilities to force
     * different storage implementations.
     *
     * @param offset Offset for first given probability (i.e. the zero padding size).
     * @param prob Probability values.
     */
    private static void testSharedStateSampler(int offset, int[] prob) {
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
        double[] probabilities = createProbabilities(offset, prob);
        final SharedStateDiscreteSampler sampler1 =
                MarsagliaTsangWangDiscreteSampler.Enumerated.of(rng1, probabilities);
        final SharedStateDiscreteSampler sampler2 = sampler1.withUniformRandomProvider(rng2);
        RandomAssert.assertProduceSameSequence(sampler1, sampler2);
    }

    /**
     * Test the SharedStateSampler implementation with a Binomial distribution with a fixed result.
     */
    @Test
    void testSharedStateSamplerWithFixedBinomialDistribution() {
        testSharedStateSampler(10, 1.0);
    }

    /**
     * Test the SharedStateSampler implementation with a Binomial distribution that requires
     * inversion (probability of success > 0.5).
     */
    @Test
    void testSharedStateSamplerWithInvertedBinomialDistribution() {
        testSharedStateSampler(10, 0.999);
    }

    /**
     * Test the SharedStateSampler implementation using a binomial distribution to exercise
     * special implementations.
     *
     * @param trials Number of trials.
     * @param probabilityOfSuccess Probability of success.
     */
    private static void testSharedStateSampler(int trials, double probabilityOfSuccess) {
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
        final SharedStateDiscreteSampler sampler1 =
                MarsagliaTsangWangDiscreteSampler.Binomial.of(rng1, trials, probabilityOfSuccess);
        final SharedStateDiscreteSampler sampler2 = sampler1.withUniformRandomProvider(rng2);
        RandomAssert.assertProduceSameSequence(sampler1, sampler2);
    }

    /**
     * Return a fixed sequence of {@code int} output.
     */
    private static class FixedSequenceIntProvider extends IntProvider {
        /** The count of values output. */
        private int count;
        /** The values. */
        private final int[] values;

        /**
         * Instantiates a new fixed sequence int provider.
         *
         * @param values Values.
         */
        FixedSequenceIntProvider(int[] values) {
            this.values = values;
        }

        @Override
        public int next() {
            // This should not be called enough to overflow count
            return values[count++ % values.length];
        }
    }

    /**
     * A RNG returning a fixed {@code int} value with all the bits set.
     */
    private static class FixedRNG extends IntProvider {
        @Override
        public int next() {
            return 0xffffffff;
        }
    }

    @Test
    void testCreatePoissonDistributionThrowsWithMeanLargerThanUpperBound_1_oe() {
         UniformRandomProvider rng = new FixedRNG();
         double mean = 1025;
        try {
    MarsagliaTsangWangDiscreteSampler.Poisson.of(rng, mean);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testCreatePoissonDistributionThrowsWithZeroMean_1_oe() {
         UniformRandomProvider rng = new FixedRNG();
         double mean = 0;
        try {
    MarsagliaTsangWangDiscreteSampler.Poisson.of(rng, mean);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testCreateBinomialDistributionThrowsWithTrialsBelow0_1_oe() {
         UniformRandomProvider rng = new FixedRNG();
         int trials = -1;
         double p = 0.5;
        try {
    MarsagliaTsangWangDiscreteSampler.Binomial.of(rng, trials, p);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testCreateBinomialDistributionThrowsWithTrialsAboveMax_1_oe() {
         UniformRandomProvider rng = new FixedRNG();
         int trials = 1 << 16; // 2^16
         double p = 0.5;
        try {
    MarsagliaTsangWangDiscreteSampler.Binomial.of(rng, trials, p);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testCreateBinomialDistributionThrowsWithProbabilityBelow0_1_oe() {
         UniformRandomProvider rng = new FixedRNG();
         int trials = 1;
         double p = -0.5;
        try {
    MarsagliaTsangWangDiscreteSampler.Binomial.of(rng, trials, p);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testCreateBinomialDistributionThrowsWithProbabilityAbove1_1_oe() {
         UniformRandomProvider rng = new FixedRNG();
         int trials = 1;
         double p = 1.5;
        try {
    MarsagliaTsangWangDiscreteSampler.Binomial.of(rng, trials, p);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testCreateBinomialDistributionThrowsWhenP0IsZero_2_oe() {
         UniformRandomProvider rng = new FixedRNG();
        // As above but increase the trials so p(0) should be zero
         int trials = 1 + (int) Math.floor(Math.log(Double.MIN_VALUE) / Math.log(0.5));
         double p = 0.5;
        // Validate set-up
        // removed other assertion
        try {
    MarsagliaTsangWangDiscreteSampler.Binomial.of(rng, trials, p);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
