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

import static org.junit.jupiter.api.Assertions.fail;

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
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testSamplesWithRangeOf1_1_oe() {
         long upper = 99;
         long lower = upper;
         UniformRandomProvider rng = RandomSource.SPLIT_MIX_64.create();
         UniformLongSampler sampler = UniformLongSampler.of(rng, lower, upper);
        for (int i = 0; i < 5; i++) {
            Assertions.assertEquals(lower, sampler.sample());
    }
    }

    @Test
    void testSamplesWithFullRange_1_oe() {
         long upper = Long.MAX_VALUE;
         long lower = Long.MIN_VALUE;
         UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
         UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
         UniformLongSampler sampler = UniformLongSampler.of(rng2, lower, upper);
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(rng1.nextLong(), sampler.sample());
    }
    }

    @Test
    void testSamplesWithSmallNonPowerOf2Range_1_oe() {
         long upper = 234293789329234L;
        for ( long lower : new long[] {-13, 0, 13}) {
             long n = upper - lower + 1;
             UniformRandomProvider rng1 = createRngWithFullBitsOnFirstCall();
             UniformRandomProvider rng2 = createRngWithFullBitsOnFirstCall();
             UniformLongSampler sampler = UniformLongSampler.of(rng2, lower, upper);
            for (int i = 0; i < 10; i++) {
                Assertions.assertEquals(lower + rng1.nextLong(n), sampler.sample());
    }
    }
    }

    @Test
    void testSamplesWithPowerOf2Range_1_oe() {
         UniformRandomProvider rngZeroBits = new LongProvider() {
            @Override
            public long next() {
                return 0L;
            }
        };
         UniformRandomProvider rngAllBits = new LongProvider() {
            @Override
            public long next() {
                return -1L;
            }
        };

         long lower = -3;
        UniformLongSampler sampler;
        for (int i = 0; i < 64; i++) {
             long range = 1L << i;
             long upper = lower + range - 1;
            sampler = UniformLongSampler.of(rngZeroBits, lower, upper);
            Assertions.assertEquals(lower, sampler.sample(), "Zero bits sample");
    }
    }

    @Test
    void testSamplesWithPowerOf2Range_2_oe() {
         UniformRandomProvider rngZeroBits = new LongProvider() {
            @Override
            public long next() {
                return 0L;
            }
        };
         UniformRandomProvider rngAllBits = new LongProvider() {
            @Override
            public long next() {
                return -1L;
            }
        };

         long lower = -3;
        UniformLongSampler sampler;
        for (int i = 0; i < 64; i++) {
             long range = 1L << i;
             long upper = lower + range - 1;
            sampler = UniformLongSampler.of(rngZeroBits, lower, upper);
            sampler = UniformLongSampler.of(rngAllBits, lower, upper);
            Assertions.assertEquals(upper, sampler.sample(), "All bits sample");
    }
    }

    @Test
    void testSamplesWithPowerOf2RangeIsBitShift_1_oe() {
         long lower = 0;
        UniformLongSampler sampler;
        for (int i = 1; i <= 63; i++) {
             long upper = (1L << i) - 1;
             int shift = 64 - i;
             UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
             UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
            sampler = UniformLongSampler.of(rng2, lower, upper);
            for (int j = 0; j < 10; j++) {
                Assertions.assertEquals(rng1.nextLong() >>> shift, sampler.sample());
    }
    }
    }

    @Test
    void testSamplesWithLargeNonPowerOf2RangeIsRejectionMethod_1_oe() {
         long upper = Long.MAX_VALUE / 2 + 1;
         long lower = Long.MIN_VALUE / 2 - 1;
         UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
         UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
         UniformLongSampler sampler = UniformLongSampler.of(rng2, lower, upper);
        for (int i = 0; i < 10; i++) {
            long expected;
            do {
                expected = rng1.nextLong();
            } while (expected < lower || expected > upper);
            Assertions.assertEquals(expected, sampler.sample());
    }
    }

    @Test
    void testSampleUniformityWithPowerOf2Range_1_oe() {
         UniformRandomProvider rng = new LongProvider() {
            private long bits = 0;

            @Override
            public long next() {
                return Long.reverse(bits++);
            }
        };

         int n = 32; // power of 2
         int[] histogram = new int[n];

         long lower = 0;
         long upper = n - 1;

         UniformLongSampler sampler = UniformLongSampler.of(rng, lower, upper);

         int expected = 2;
        for (int i = expected * n; i-- > 0;) {
            histogram[(int) sampler.sample()]++;
        }

        for (int value : histogram) {
            Assertions.assertEquals(expected, value);
    }
    }

    @Test
    void testOffsetSamplesWithNonPowerOf2Range_1_oe_1_oe() {
                 long range0 = 257;
         Long seed0 = RandomSource.createLong();
                 UniformRandomProvider rng10 = RandomSource.SPLIT_MIX_64.create(seed0);
                 UniformRandomProvider rng20 = RandomSource.SPLIT_MIX_64.create(seed0);
                 UniformRandomProvider rng30 = RandomSource.SPLIT_MIX_64.create(seed0);
        
                range0 = range0 - 1;
                 long offsetLo0 = -13;
                 long offsetHi0 = 42;
                 UniformLongSampler sampler0 = UniformLongSampler.of(rng10, 0, range0);
                 UniformLongSampler samplerLo0 = UniformLongSampler.of(rng20, offsetLo0, offsetLo0 + range0);
                 UniformLongSampler samplerHi0 = UniformLongSampler.of(rng30, offsetHi0, offsetHi0 + range0);
                for (int i0 = 0; i0 < 10; i0++) {
                     long sample10 = sampler0.sample();
                     long sample20 = samplerLo0.sample();
                     long sample30 = samplerHi0.sample();
                    Assertions.assertEquals(sample10 + offsetLo0, sample20, "Incorrect negative offset sample");
    }
    }

    @Test
    void testOffsetSamplesWithNonPowerOf2Range_1_oe_2_oe() {
                 long range0 = 257;
         Long seed0 = RandomSource.createLong();
                 UniformRandomProvider rng10 = RandomSource.SPLIT_MIX_64.create(seed0);
                 UniformRandomProvider rng20 = RandomSource.SPLIT_MIX_64.create(seed0);
                 UniformRandomProvider rng30 = RandomSource.SPLIT_MIX_64.create(seed0);
        
                range0 = range0 - 1;
                 long offsetLo0 = -13;
                 long offsetHi0 = 42;
                 UniformLongSampler sampler0 = UniformLongSampler.of(rng10, 0, range0);
                 UniformLongSampler samplerLo0 = UniformLongSampler.of(rng20, offsetLo0, offsetLo0 + range0);
                 UniformLongSampler samplerHi0 = UniformLongSampler.of(rng30, offsetHi0, offsetHi0 + range0);
                for (int i0 = 0; i0 < 10; i0++) {
                     long sample10 = sampler0.sample();
                     long sample20 = samplerLo0.sample();
                     long sample30 = samplerHi0.sample();
                    Assertions.assertEquals(sample10 + offsetHi0, sample30, "Incorrect positive offset sample");
    }
    }

    @Test
    void testOffsetSamplesWithPowerOf2Range_1_oe_1_oe() {
                 long range0 = 256;
         Long seed0 = RandomSource.createLong();
                 UniformRandomProvider rng10 = RandomSource.SPLIT_MIX_64.create(seed0);
                 UniformRandomProvider rng20 = RandomSource.SPLIT_MIX_64.create(seed0);
                 UniformRandomProvider rng30 = RandomSource.SPLIT_MIX_64.create(seed0);
        
                range0 = range0 - 1;
                 long offsetLo0 = -13;
                 long offsetHi0 = 42;
                 UniformLongSampler sampler0 = UniformLongSampler.of(rng10, 0, range0);
                 UniformLongSampler samplerLo0 = UniformLongSampler.of(rng20, offsetLo0, offsetLo0 + range0);
                 UniformLongSampler samplerHi0 = UniformLongSampler.of(rng30, offsetHi0, offsetHi0 + range0);
                for (int i0 = 0; i0 < 10; i0++) {
                     long sample10 = sampler0.sample();
                     long sample20 = samplerLo0.sample();
                     long sample30 = samplerHi0.sample();
                    Assertions.assertEquals(sample10 + offsetLo0, sample20, "Incorrect negative offset sample");
    }
    }

    @Test
    void testOffsetSamplesWithPowerOf2Range_1_oe_2_oe() {
                 long range0 = 256;
         Long seed0 = RandomSource.createLong();
                 UniformRandomProvider rng10 = RandomSource.SPLIT_MIX_64.create(seed0);
                 UniformRandomProvider rng20 = RandomSource.SPLIT_MIX_64.create(seed0);
                 UniformRandomProvider rng30 = RandomSource.SPLIT_MIX_64.create(seed0);
        
                range0 = range0 - 1;
                 long offsetLo0 = -13;
                 long offsetHi0 = 42;
                 UniformLongSampler sampler0 = UniformLongSampler.of(rng10, 0, range0);
                 UniformLongSampler samplerLo0 = UniformLongSampler.of(rng20, offsetLo0, offsetLo0 + range0);
                 UniformLongSampler samplerHi0 = UniformLongSampler.of(rng30, offsetHi0, offsetHi0 + range0);
                for (int i0 = 0; i0 < 10; i0++) {
                     long sample10 = sampler0.sample();
                     long sample20 = samplerLo0.sample();
                     long sample30 = samplerHi0.sample();
                    Assertions.assertEquals(sample10 + offsetHi0, sample30, "Incorrect positive offset sample");
    }
    }

    @Test
    void testOffsetSamplesWithRangeOf1_1_oe_1_oe() {
                 long range0 = 1;
         Long seed0 = RandomSource.createLong();
                 UniformRandomProvider rng10 = RandomSource.SPLIT_MIX_64.create(seed0);
                 UniformRandomProvider rng20 = RandomSource.SPLIT_MIX_64.create(seed0);
                 UniformRandomProvider rng30 = RandomSource.SPLIT_MIX_64.create(seed0);
        
                range0 = range0 - 1;
                 long offsetLo0 = -13;
                 long offsetHi0 = 42;
                 UniformLongSampler sampler0 = UniformLongSampler.of(rng10, 0, range0);
                 UniformLongSampler samplerLo0 = UniformLongSampler.of(rng20, offsetLo0, offsetLo0 + range0);
                 UniformLongSampler samplerHi0 = UniformLongSampler.of(rng30, offsetHi0, offsetHi0 + range0);
                for (int i0 = 0; i0 < 10; i0++) {
                     long sample10 = sampler0.sample();
                     long sample20 = samplerLo0.sample();
                     long sample30 = samplerHi0.sample();
                    Assertions.assertEquals(sample10 + offsetLo0, sample20, "Incorrect negative offset sample");
    }
    }

    @Test
    void testOffsetSamplesWithRangeOf1_1_oe_2_oe() {
                 long range0 = 1;
         Long seed0 = RandomSource.createLong();
                 UniformRandomProvider rng10 = RandomSource.SPLIT_MIX_64.create(seed0);
                 UniformRandomProvider rng20 = RandomSource.SPLIT_MIX_64.create(seed0);
                 UniformRandomProvider rng30 = RandomSource.SPLIT_MIX_64.create(seed0);
        
                range0 = range0 - 1;
                 long offsetLo0 = -13;
                 long offsetHi0 = 42;
                 UniformLongSampler sampler0 = UniformLongSampler.of(rng10, 0, range0);
                 UniformLongSampler samplerLo0 = UniformLongSampler.of(rng20, offsetLo0, offsetLo0 + range0);
                 UniformLongSampler samplerHi0 = UniformLongSampler.of(rng30, offsetHi0, offsetHi0 + range0);
                for (int i0 = 0; i0 < 10; i0++) {
                     long sample10 = sampler0.sample();
                     long sample20 = samplerLo0.sample();
                     long sample30 = samplerHi0.sample();
                    Assertions.assertEquals(sample10 + offsetHi0, sample30, "Incorrect positive offset sample");
    }
    }

    @Test
    void testSharedStateSamplerWithSmallRange_1_oe_1_oe() {
                 long lower0 = 5;
         long upper0 = 67;
         UniformRandomProvider rng10 = RandomSource.SPLIT_MIX_64.create(0L);
                 UniformRandomProvider rng20 = RandomSource.SPLIT_MIX_64.create(0L);
                 UniformLongSampler sampler10 = UniformLongSampler.of(rng10, lower0, upper0);
                 UniformLongSampler sampler20 = sampler10.withUniformRandomProvider(rng20);
                RandomAssert.assertProduceSameSequence(sampler10, sampler20);
    }

    @Test
    void testSharedStateSamplerWithLargeRange_1_oe_1_oe() {
                 long lower0 = Long.MIN_VALUE / 2 - 1;
         long upper0 = Long.MAX_VALUE / 2 + 1;
         UniformRandomProvider rng10 = RandomSource.SPLIT_MIX_64.create(0L);
                 UniformRandomProvider rng20 = RandomSource.SPLIT_MIX_64.create(0L);
                 UniformLongSampler sampler10 = UniformLongSampler.of(rng10, lower0, upper0);
                 UniformLongSampler sampler20 = sampler10.withUniformRandomProvider(rng20);
                RandomAssert.assertProduceSameSequence(sampler10, sampler20);
    }

    @Test
    void testSharedStateSamplerWithPowerOf2Range_1_oe_1_oe() {
                 long lower0 = 0;
         long upper0 = (1L << 45) - 1;
         UniformRandomProvider rng10 = RandomSource.SPLIT_MIX_64.create(0L);
                 UniformRandomProvider rng20 = RandomSource.SPLIT_MIX_64.create(0L);
                 UniformLongSampler sampler10 = UniformLongSampler.of(rng10, lower0, upper0);
                 UniformLongSampler sampler20 = sampler10.withUniformRandomProvider(rng20);
                RandomAssert.assertProduceSameSequence(sampler10, sampler20);
    }

    @Test
    void testSharedStateSamplerWithRangeOf1_1_oe_1_oe() {
                 long lower0 = 968757657572323L;
         long upper0 = 968757657572323L;
         UniformRandomProvider rng10 = RandomSource.SPLIT_MIX_64.create(0L);
                 UniformRandomProvider rng20 = RandomSource.SPLIT_MIX_64.create(0L);
                 UniformLongSampler sampler10 = UniformLongSampler.of(rng10, lower0, upper0);
                 UniformLongSampler sampler20 = sampler10.withUniformRandomProvider(rng20);
                RandomAssert.assertProduceSameSequence(sampler10, sampler20);
    }

    @Test
    void testToStringWithSmallRange_1_oe_1_oe() {
                 long lower0 = 5;
         long upper0 = 67;
         UniformRandomProvider rng0 = RandomSource.SPLIT_MIX_64.create(0L);
                 UniformLongSampler sampler0 = UniformLongSampler.of(rng0, lower0, upper0);
                Assertions.assertTrue(sampler0.toString().toLowerCase(Locale.US).contains("uniform"));
    }

    @Test
    void testToStringWithLargeRange_1_oe_1_oe() {
                 long lower0 = -99999999;
         long upper0 = Long.MAX_VALUE;
         UniformRandomProvider rng0 = RandomSource.SPLIT_MIX_64.create(0L);
                 UniformLongSampler sampler0 = UniformLongSampler.of(rng0, lower0, upper0);
                Assertions.assertTrue(sampler0.toString().toLowerCase(Locale.US).contains("uniform"));
    }

    @Test
    void testToStringWithPowerOf2Range_1_oe_1_oe() {
                 long lower0 = 0;
         long upper0 = 31;
         UniformRandomProvider rng0 = RandomSource.SPLIT_MIX_64.create(0L);
                 UniformLongSampler sampler0 = UniformLongSampler.of(rng0, lower0, upper0);
                Assertions.assertTrue(sampler0.toString().toLowerCase(Locale.US).contains("uniform"));
    }

    @Test
    void testToStringWithRangeOf1_1_oe_1_oe() {
                 long lower0 = 9;
         long upper0 = 9;
         UniformRandomProvider rng0 = RandomSource.SPLIT_MIX_64.create(0L);
                 UniformLongSampler sampler0 = UniformLongSampler.of(rng0, lower0, upper0);
                Assertions.assertTrue(sampler0.toString().toLowerCase(Locale.US).contains("uniform"));
    }

}
