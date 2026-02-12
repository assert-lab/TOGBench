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

package org.apache.commons.rng.sampling;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.sampling.CompositeSamplers.Builder;
import org.apache.commons.rng.sampling.CompositeSamplers.DiscreteProbabilitySampler;
import org.apache.commons.rng.sampling.CompositeSamplers.DiscreteProbabilitySamplerFactory;
import org.apache.commons.rng.sampling.distribution.AliasMethodDiscreteSampler;
import org.apache.commons.rng.sampling.distribution.ContinuousSampler;
import org.apache.commons.rng.sampling.distribution.DiscreteSampler;
import org.apache.commons.rng.sampling.distribution.GuideTableDiscreteSampler;
import org.apache.commons.rng.sampling.distribution.LongSampler;
import org.apache.commons.rng.sampling.distribution.SharedStateContinuousSampler;
import org.apache.commons.rng.sampling.distribution.SharedStateDiscreteSampler;
import org.apache.commons.rng.sampling.distribution.SharedStateLongSampler;
import org.apache.commons.rng.simple.RandomSource;

/**
 * Test class for {@link CompositeSamplers}.
 */
class CompositeSamplersTest_OE25Dev {
    /**
     * Test the default implementations of the discrete probability sampler factory.
     */

    /**
     * Test an empty builder cannot build a sampler.
     */

    /**
     * Test adding null sampler to a builder.
     */

    /**
     * Test invalid weights (zero, negative, NaN, infinte).
     */

    /**
     * Test a single sampler added to the builder is returned without a composite.
     */

    /**
     * Test sampling is uniform across several ObjectSampler samplers.
     */

    /**
     * Test sampling is uniform across several SharedStateObjectSampler samplers.
     */

    /**
     * Test sampling is uniform across several SharedStateObjectSampler samplers
     * using a custom factory that implements SharedStateDiscreteSampler.
     */

    /**
     * Test sampling is uniform across several SharedStateObjectSampler samplers
     * using a custom factory that implements DiscreteSampler (so must be wrapped).
     */

    /**
     * Test sampling is uniform across several ObjectSampler samplers with a uniform
     * weighting. This tests an edge case where there is no requirement for a
     * sampler from a discrete probability distribution as the distribution is
     * uniform.
     */

    /**
     * Test sampling is uniform across several ObjectSampler samplers with very
     * large weights. This tests an edge case where the weights with sum to
     * infinity.
     */

    /**
     * Test sampling is uniform across several ObjectSampler samplers with very
     * small weights. This tests an edge case where the weights divided by their sum
     * are valid (due to accurate floating-point division) but cannot be multiplied
     * by the reciprocal of the sum.
     */

    /**
     * Add samplers to the builder that sample from contiguous ranges between the
     * minimum and maximum. Note: {@code max - min >= n}
     *
     * @param builder the builder
     * @param n the number of samplers (must be {@code >= 2})
     * @param min the minimum (inclusive)
     * @param max the maximum (exclusive)
     * @param rng the source of randomness
     */
    private static void addObjectSamplers(Builder<? super SharedStateObjectSampler<Integer>> builder, int n, int min,
            int max, UniformRandomProvider rng) {
        // Create the ranges using n-1 random ticks in the range (min, max),
        // adding the limits and then sorting in ascending order.
        // The samplers are then constructed:
        //
        // min-------A-----B----max
        // Sampler 1 = [min, A)
        // Sampler 2 = [A, B)
        // Sampler 3 = [B, max)

        // Use a combination sampler to ensure the ticks are unique in the range.
        // This will throw if the range is negative.
        final int range = max - min - 1;
        int[] ticks = new CombinationSampler(rng, range, n - 1).sample();
        // Shift the ticks into the range
        for (int i = 0; i < ticks.length; i++) {
            ticks[i] += min + 1;
        }
        // Add the min and max
        ticks = Arrays.copyOf(ticks, n + 1);
        ticks[n - 1] = min;
        ticks[n] = max;
        Arrays.sort(ticks);

        // Sample within the ranges between the ticks
        final int before = builder.size();
        for (int i = 1; i < ticks.length; i++) {
            final RangeSampler sampler = new RangeSampler(ticks[i - 1], ticks[i], rng);
            // Weight using the range
            builder.add(sampler, sampler.range);
        }

        Assertions.assertEquals(n, builder.size() - before, "Failed to add the correct number of samplers");
    }

    /**
     * Assert sampling is uniform between the minimum and maximum.
     *
     * @param sampler the sampler
     * @param min the minimum (inclusive)
     * @param max the maximum (exclusive)
     */
    private static void assertObjectSamplerSamples(ObjectSampler<Integer> sampler, int min, int max) {
        final int n = 100000;
        final long[] observed = new long[max - min];
        for (int i = 0; i < n; i++) {
            observed[sampler.sample() - min]++;
        }

        final double[] expected = new double[observed.length];
        Arrays.fill(expected, (double) n / expected.length);
        final double p = new ChiSquareTest().chiSquareTest(expected, observed);
        Assertions.assertFalse(p < 0.001, () -> "p-value too small: " + p);
    }

    /**
     * Test sampling is uniform across several DiscreteSampler samplers.
     */

    /**
     * Test sampling is uniform across several SharedStateDiscreteSampler samplers.
     */

    /**
     * Add samplers to the builder that sample from contiguous ranges between the
     * minimum and maximum. Note: {@code max - min >= n}
     *
     * @param builder the builder
     * @param n the number of samplers (must be {@code >= 2})
     * @param min the minimum (inclusive)
     * @param max the maximum (exclusive)
     * @param rng the source of randomness
     */
    private static void addDiscreteSamplers(Builder<? super SharedStateDiscreteSampler> builder, int n, int min,
            int max, UniformRandomProvider rng) {
        // Create the ranges using n-1 random ticks in the range (min, max),
        // adding the limits and then sorting in ascending order.
        // The samplers are then constructed:
        //
        // min-------A-----B----max
        // Sampler 1 = [min, A)
        // Sampler 2 = [A, B)
        // Sampler 3 = [B, max)

        // Use a combination sampler to ensure the ticks are unique in the range.
        // This will throw if the range is negative.
        final int range = max - min - 1;
        int[] ticks = new CombinationSampler(rng, range, n - 1).sample();
        // Shift the ticks into the range
        for (int i = 0; i < ticks.length; i++) {
            ticks[i] += min + 1;
        }
        // Add the min and max
        ticks = Arrays.copyOf(ticks, n + 1);
        ticks[n - 1] = min;
        ticks[n] = max;
        Arrays.sort(ticks);

        // Sample within the ranges between the ticks
        final int before = builder.size();
        for (int i = 1; i < ticks.length; i++) {
            final IntRangeSampler sampler = new IntRangeSampler(rng, ticks[i - 1], ticks[i]);
            // Weight using the range
            builder.add(sampler, sampler.range);
        }

        Assertions.assertEquals(n, builder.size() - before, "Failed to add the correct number of samplers");
    }

    /**
     * Assert sampling is uniform between the minimum and maximum.
     *
     * @param sampler the sampler
     * @param min the minimum (inclusive)
     * @param max the maximum (exclusive)
     */
    private static void assertDiscreteSamplerSamples(DiscreteSampler sampler, int min, int max) {
        final int n = 100000;
        final long[] observed = new long[max - min];
        for (int i = 0; i < n; i++) {
            observed[sampler.sample() - min]++;
        }

        final double[] expected = new double[observed.length];
        Arrays.fill(expected, (double) n / expected.length);
        final double p = new ChiSquareTest().chiSquareTest(expected, observed);
        Assertions.assertFalse(p < 0.001, () -> "p-value too small: " + p);
    }

    /**
     * Test sampling is uniform across several ContinuousSampler samplers.
     */

    /**
     * Test sampling is uniform across several SharedStateContinuousSampler samplers.
     */

    /**
     * Add samplers to the builder that sample from contiguous ranges between the
     * minimum and maximum. Note: {@code max - min >= n}
     *
     * @param builder the builder
     * @param n the number of samplers (must be {@code >= 2})
     * @param min the minimum (inclusive)
     * @param max the maximum (exclusive)
     * @param rng the source of randomness
     */
    private static void addContinuousSamplers(Builder<? super SharedStateContinuousSampler> builder, int n, double min,
            double max, UniformRandomProvider rng) {
        // Create the ranges using n-1 random ticks in the range (min, max),
        // adding the limits and then sorting in ascending order.
        // The samplers are then constructed:
        //
        // min-------A-----B----max
        // Sampler 1 = [min, A)
        // Sampler 2 = [A, B)
        // Sampler 3 = [B, max)

        // For double values it is extremely unlikely the same value will be generated.
        // An assertion is performed to ensure we create the correct number of samplers.
        DoubleRangeSampler sampler = new DoubleRangeSampler(rng, min, max);
        final double[] ticks = new double[n + 1];
        ticks[0] = min;
        ticks[1] = max;
        // Shift the ticks into the range
        for (int i = 2; i < ticks.length; i++) {
            ticks[i] = sampler.sample();
        }
        Arrays.sort(ticks);

        // Sample within the ranges between the ticks
        final int before = builder.size();
        for (int i = 1; i < ticks.length; i++) {
            sampler = new DoubleRangeSampler(rng, ticks[i - 1], ticks[i]);
            // Weight using the range
            builder.add(sampler, sampler.range());
        }

        Assertions.assertEquals(n, builder.size() - before, "Failed to add the correct number of samplers");
    }

    /**
     * Assert sampling is uniform between the minimum and maximum.
     *
     * @param sampler the sampler
     * @param min the minimum (inclusive)
     * @param max the maximum (exclusive)
     */
    private static void assertContinuousSamplerSamples(ContinuousSampler sampler, double min, double max) {
        final int n = 100000;
        final int bins = 200;
        final long[] observed = new long[bins];
        final double scale = bins / (max - min);
        for (int i = 0; i < n; i++) {
            // scale the sample into a bin within the range:
            // bin = bins * (x - min) / (max - min)
            observed[(int) (scale * (sampler.sample() - min))]++;
        }

        final double[] expected = new double[observed.length];
        Arrays.fill(expected, (double) n / expected.length);
        final double p = new ChiSquareTest().chiSquareTest(expected, observed);
        Assertions.assertFalse(p < 0.001, () -> "p-value too small: " + p);
    }

    /**
     * Test sampling is uniform across several LongSampler samplers.
     */

    /**
     * Test sampling is uniform across several SharedStateLongSampler samplers.
     */

    /**
     * Add samplers to the builder that sample from contiguous ranges between the
     * minimum and maximum. Note: {@code max - min >= n}
     *
     * @param builder the builder
     * @param n the number of samplers (must be {@code >= 2})
     * @param min the minimum (inclusive)
     * @param max the maximum (exclusive)
     * @param rng the source of randomness
     */
    private static void addLongSamplers(Builder<? super SharedStateLongSampler> builder, int n, long min,
            long max, UniformRandomProvider rng) {
        // Create the ranges using n-1 random ticks in the range (min, max),
        // adding the limits and then sorting in ascending order.
        // The samplers are then constructed:
        //
        // min-------A-----B----max
        // Sampler 1 = [min, A)
        // Sampler 2 = [A, B)
        // Sampler 3 = [B, max)

        // For long values it is extremely unlikely the same value will be generated.
        // An assertion is performed to ensure we create the correct number of samplers.
        LongRangeSampler sampler = new LongRangeSampler(rng, min, max);
        final long[] ticks = new long[n + 1];
        ticks[0] = min;
        ticks[1] = max;
        // Shift the ticks into the range
        for (int i = 2; i < ticks.length; i++) {
            ticks[i] = sampler.sample();
        }
        Arrays.sort(ticks);


        // Sample within the ranges between the ticks
        final int before = builder.size();
        for (int i = 1; i < ticks.length; i++) {
            sampler = new LongRangeSampler(rng, ticks[i - 1], ticks[i]);
            // Weight using the range
            builder.add(sampler, sampler.range);
        }

        Assertions.assertEquals(n, builder.size() - before, "Failed to add the correct number of samplers");
    }

    /**
     * Assert sampling is uniform between the minimum and maximum.
     *
     * @param sampler the sampler
     * @param min the minimum (inclusive)
     * @param max the maximum (exclusive)
     */
    private static void assertLongSamplerSamples(LongSampler sampler, long min, long max) {
        final int n = 100000;
        final int bins = 200;
        final long[] observed = new long[bins];
        final long range = max - min;
        for (int i = 0; i < n; i++) {
            // scale the sample into a bin within the range:
            observed[(int) (bins * (sampler.sample() - min) / range)]++;
        }

        final double[] expected = new double[observed.length];
        Arrays.fill(expected, (double) n / expected.length);
        final double p = new ChiSquareTest().chiSquareTest(expected, observed);
        Assertions.assertFalse(p < 0.001, () -> "p-value too small: " + p);
    }

    /**
     * Test the SharedStateSampler implementation for the composite
     * SharedStateObjectSampler.
     */
    @Test
    void testSharedStateObjectSampler() {
        testSharedStateObjectSampler(false);
    }

    /**
     * Test the SharedStateSampler implementation for the composite
     * SharedStateObjectSampler with a factory that does not support a shared state sampler.
     */
    @Test
    void testSharedStateObjectSamplerWithCustomFactory() {
        testSharedStateObjectSampler(true);
    }

    /**
     * Test the SharedStateSampler implementation for the composite
     * SharedStateObjectSampler.
     *
     * @param customFactory Set to true to use a custom discrete sampler factory that does not
     * support a shared stated sampler.
     */
    private static void testSharedStateObjectSampler(boolean customFactory) {
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);

        final Builder<SharedStateObjectSampler<Integer>> builder = CompositeSamplers
                .newSharedStateObjectSamplerBuilder();

        if (customFactory) {
            addFactoryWithNoSharedStateSupport(builder);
        }

        // Sample within the ranges between the ticks
        final int[] ticks = {6, 13, 42, 99};
        for (int i = 1; i < ticks.length; i++) {
            final RangeSampler sampler = new RangeSampler(ticks[i - 1], ticks[i], rng1);
            // Weight using the range
            builder.add(sampler, sampler.range);
        }

        final SharedStateObjectSampler<Integer> sampler1 = builder.build(rng1);
        final SharedStateObjectSampler<Integer> sampler2 = sampler1.withUniformRandomProvider(rng2);
        RandomAssert.assertProduceSameSequence(sampler1, sampler2);
    }

    /**
     * Test the SharedStateSampler implementation for the composite
     * SharedStateDiscreteSampler.
     */
    @Test
    void testSharedStateDiscreteSampler() {
        testSharedStateDiscreteSampler(false);
    }

    /**
     * Test the SharedStateSampler implementation for the composite
     * SharedStateDiscreteSampler with a factory that does not support a shared state sampler.
     */
    @Test
    void testSharedStateDiscreteSamplerWithCustomFactory() {
        testSharedStateDiscreteSampler(true);
    }

    /**
     * Test the SharedStateSampler implementation for the composite
     * SharedStateDiscreteSampler.
     *
     * @param customFactory Set to true to use a custom discrete sampler factory that does not
     * support a shared stated sampler.
     */
    private static void testSharedStateDiscreteSampler(boolean customFactory) {
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);

        final Builder<SharedStateDiscreteSampler> builder = CompositeSamplers.newSharedStateDiscreteSamplerBuilder();

        if (customFactory) {
            addFactoryWithNoSharedStateSupport(builder);
        }

        // Sample within the ranges between the ticks
        final int[] ticks = {-3, 5, 14, 22};
        for (int i = 1; i < ticks.length; i++) {
            final IntRangeSampler sampler = new IntRangeSampler(rng1, ticks[i - 1], ticks[i]);
            // Weight using the range
            builder.add(sampler, sampler.range);
        }

        final SharedStateDiscreteSampler sampler1 = builder.build(rng1);
        final SharedStateDiscreteSampler sampler2 = sampler1.withUniformRandomProvider(rng2);
        RandomAssert.assertProduceSameSequence(sampler1, sampler2);
    }

    /**
     * Test the SharedStateSampler implementation for the composite
     * SharedStateContinuousSampler.
     */
    @Test
    void testSharedStateContinuousSampler() {
        testSharedStateContinuousSampler(false);
    }

    /**
     * Test the SharedStateSampler implementation for the composite
     * SharedStateContinuousSampler with a factory that does not support a shared state sampler.
     */
    @Test
    void testSharedStateContinuousSamplerWithCustomFactory() {
        testSharedStateContinuousSampler(true);
    }

    /**
     * Test the SharedStateSampler implementation for the composite
     * SharedStateContinuousSampler.
     *
     * @param customFactory Set to true to use a custom discrete sampler factory that does not
     * support a shared stated sampler.
     */
    private static void testSharedStateContinuousSampler(boolean customFactory) {
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);

        final Builder<SharedStateContinuousSampler> builder = CompositeSamplers
                .newSharedStateContinuousSamplerBuilder();

        if (customFactory) {
            addFactoryWithNoSharedStateSupport(builder);
        }

        // Sample within the ranges between the ticks
        final double[] ticks = {7.89, 13.99, 21.7, 35.6, 45.5};
        for (int i = 1; i < ticks.length; i++) {
            final DoubleRangeSampler sampler = new DoubleRangeSampler(rng1, ticks[i - 1], ticks[i]);
            // Weight using the range
            builder.add(sampler, sampler.range());
        }

        final SharedStateContinuousSampler sampler1 = builder.build(rng1);
        final SharedStateContinuousSampler sampler2 = sampler1.withUniformRandomProvider(rng2);
        RandomAssert.assertProduceSameSequence(sampler1, sampler2);
    }

    /**
     * Adds a DiscreteSamplerFactory to the builder that creates samplers that do not share state.
     *
     * @param builder the builder
     */
    private static void addFactoryWithNoSharedStateSupport(Builder<?> builder) {
        builder.setFactory(new DiscreteProbabilitySamplerFactory() {
            @Override
            public DiscreteSampler create(UniformRandomProvider rng, double[] probabilities) {
                // Wrap so it is not a SharedStateSamplerInstance.
                final DiscreteSampler sampler = GuideTableDiscreteSampler.of(rng, probabilities, 2);
                // Destroy the probabilities to check that custom factories are not trusted.
                Arrays.fill(probabilities, Double.NaN);
                return new DiscreteSampler() {
                    @Override
                    public int sample() {
                        return sampler.sample();
                    }
                };
            }
        });
    }

    /**
     * Test the SharedStateSampler implementation for the composite
     * SharedStateLongSampler.
     */
    @Test
    void testSharedStateLongSampler() {
        testSharedStateLongSampler(false);
    }

    /**
     * Test the SharedStateSampler implementation for the composite
     * SharedStateLongSampler with a factory that does not support a shared state sampler.
     */
    @Test
    void testSharedStateLongSamplerWithCustomFactory() {
        testSharedStateLongSampler(true);
    }

    /**
     * Test the SharedStateSampler implementation for the composite
     * SharedStateLongSampler.
     *
     * @param customFactory Set to true to use a custom discrete sampler factory that does not
     * support a shared stated sampler.
     */
    private static void testSharedStateLongSampler(boolean customFactory) {
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);

        final Builder<SharedStateLongSampler> builder = CompositeSamplers.newSharedStateLongSamplerBuilder();

        if (customFactory) {
            addFactoryWithNoSharedStateSupport(builder);
        }

        // Sample within the ranges between the ticks
        final long[] ticks = {-32634628368L, 516234712, 1472839427384234L, 72364572187368423L};
        for (int i = 1; i < ticks.length; i++) {
            final LongRangeSampler sampler = new LongRangeSampler(rng1, ticks[i - 1], ticks[i]);
            // Weight using the range
            builder.add(sampler, sampler.range);
        }

        final SharedStateLongSampler sampler1 = builder.build(rng1);
        final SharedStateLongSampler sampler2 = sampler1.withUniformRandomProvider(rng2);
        RandomAssert.assertProduceSameSequence(sampler1, sampler2);
    }

    /**
     * Sample an object {@code Integer} from a range.
     */
    private static class RangeSampler implements SharedStateObjectSampler<Integer> {
        private final int min;
        private final int range;
        private final UniformRandomProvider rng;

        /**
         * @param min the minimum (inclusive)
         * @param max the maximum (exclusive)
         * @param rng the source of randomness
         */
        RangeSampler(int min, int max, UniformRandomProvider rng) {
            this.min = min;
            this.range = max - min;
            this.rng = rng;
        }

        @Override
        public Integer sample() {
            return min + rng.nextInt(range);
        }

        @Override
        public SharedStateObjectSampler<Integer> withUniformRandomProvider(UniformRandomProvider generator) {
            return new RangeSampler(min, min + range, generator);
        }
    }

    /**
     * Sample a primitive {@code integer} from a range.
     */
    private static class IntRangeSampler implements SharedStateDiscreteSampler {
        private final int min;
        private final int range;
        private final UniformRandomProvider rng;

        /**
         * @param rng the source of randomness
         * @param min the minimum (inclusive)
         * @param max the maximum (exclusive)
         */
        IntRangeSampler(UniformRandomProvider rng, int min, int max) {
            this.min = min;
            this.range = max - min;
            this.rng = rng;
        }

        @Override
        public int sample() {
            return min + rng.nextInt(range);
        }

        @Override
        public SharedStateDiscreteSampler withUniformRandomProvider(UniformRandomProvider generator) {
            return new IntRangeSampler(generator, min, min + range);
        }
    }

    /**
     * Sample a primitive {@code double} from a range between a and b.
     */
    private static class DoubleRangeSampler implements SharedStateContinuousSampler {
        private final double a;
        private final double b;
        private final UniformRandomProvider rng;

        /**
         * @param rng the source of randomness
         * @param a bound a
         * @param b bound b
         */
        DoubleRangeSampler(UniformRandomProvider rng, double a, double b) {
            this.a = a;
            this.b = b;
            this.rng = rng;
        }

        /**
         * Get the range from a to b.
         *
         * @return the range
         */
        double range() {
            return Math.abs(b - a);
        }

        @Override
        public double sample() {
            // a + u * (b - a) == u * b + (1 - u) * a
            final double u = rng.nextDouble();
            return u * b + (1 - u) * a;
        }

        @Override
        public SharedStateContinuousSampler withUniformRandomProvider(UniformRandomProvider generator) {
            return new DoubleRangeSampler(generator, a, b);
        }
    }

    /**
     * Sample a primitive {@code long} from a range.
     */
    private static class LongRangeSampler implements SharedStateLongSampler {
        private final long min;
        private final long range;
        private final UniformRandomProvider rng;

        /**
         * @param rng the source of randomness
         * @param min the minimum (inclusive)
         * @param max the maximum (exclusive)
         */
        LongRangeSampler(UniformRandomProvider rng, long min, long max) {
            this.min = min;
            this.range = max - min;
            this.rng = rng;
        }

        @Override
        public long sample() {
            return min + rng.nextLong(range);
        }

        @Override
        public SharedStateLongSampler withUniformRandomProvider(UniformRandomProvider generator) {
            return new LongRangeSampler(generator, min, min + range);
        }
    }

    @Test
    void testEmptyBuilderThrows_2_oe() {
         UniformRandomProvider rng = RandomSource.SPLIT_MIX_64.create(0L);
         Builder<SharedStateObjectSampler<Integer>> builder = CompositeSamplers
                .newSharedStateObjectSamplerBuilder();
        // removed other assertion
        try {
    builder.build(rng);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testNullSharedStateObjectSamplerThrows_1_oe() {
         Builder<SharedStateObjectSampler<Integer>> builder = CompositeSamplers
                .newSharedStateObjectSamplerBuilder();
        try {
    builder.add(null, 1.0);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testInvalidWeights_3_oe() {
         UniformRandomProvider rng = RandomSource.SPLIT_MIX_64.create(0L);
         Builder<SharedStateObjectSampler<Integer>> builder = CompositeSamplers
                .newSharedStateObjectSamplerBuilder();
         RangeSampler sampler = new RangeSampler(45, 63, rng);
        // Zero weight is ignored
        // removed other assertion
        builder.add(sampler, 0.0);
        // removed other assertion

         double[] bad = {-1, Double.NaN, Double.POSITIVE_INFINITY};
        for ( double weight : bad) {
            try {
    builder.add(sampler, weight);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: () -> \"Did not detect invalid weight: \" + weight");
} catch (IllegalArgumentException e) {
}
    }
    }

}
