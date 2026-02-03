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

import org.apache.commons.rng.RestorableUniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This test checks the {@link PoissonSamplerCache} functions exactly like the
 * constructor of the {@link PoissonSampler}, irrespective of the range
 * covered by the cache.
 */
class PoissonSamplerCacheTest_OE25Dev {

    // Set a range so that the SmallMeanPoissonSampler is also required.

    /** The minimum of the range of the mean */
    private final int minRange = (int) Math.floor(PoissonSampler.PIVOT - 2);
    /** The maximum of the range of the mean */
    private final int maxRange = (int) Math.floor(PoissonSampler.PIVOT + 6);
    /** The mid-point of the range of the mean */
    private final int midRange = (minRange + maxRange) / 2;

    /**
     * Test the cache reports the minimum mean that uses an algorithm that supports caching.
     * This mean is the same level as the algorithm switch point in the PoissonSampler.
     */

    // Edge cases for construction

    /**
     * Test the cache can be created without a range that requires a cache.
     * In this case the cache will be a pass through to the constructor
     * of the SmallMeanPoissonSampler.
     */

    /**
     * Test the cache can be created with a range of 1.
     * In this case the cache will be valid for all mean values
     * in the range {@code n <= mean < n+1}.
     */

    /**
     * Test the cache can be created with a range of 1.
     * In this case the cache will be valid for all mean values
     * in the range {@code n <= mean < n+1}.
     */

    /**
     * Test the cache requires a range with {@code max >= min}.
     */

    /**
     * Test the cache can be created with a min range below 0.
     * In this case the range is truncated to 0.
     */

    /**
     * Test the cache can be created with a max range below 0.
     * In this case the range is truncated to 0, i.e. no cache.
     */

    /**
     * Test the cache can be created without a range that requires a cache.
     * In this case the cache will be a pass through to the constructor
     * of the SmallMeanPoissonSampler.
     */

    /**
     * Test the cache can be created with a range of 1.
     * In this case the cache will be valid for all mean values
     * in the range {@code n <= mean < n+1}.
     */

    /**
     * Test the cache can be created with a range of 1.
     * In this case the cache will be valid for all mean values
     * in the range {@code n <= mean < n+1}.
     */

    /**
     * Test the cache requires a range with {@code max >= min}.
     */

    /**
     * Test the cache can be created with a min range below 0.
     * In this case the range is truncated to 0.
     */

    /**
     * Test the cache can be created from a cache with no capacity.
     */

    /**
     * Test the withinRange function of the cache signals when construction
     * cost is minimal.
     */

    // Edge cases for creating a Poisson sampler

    /**
     * Test createSharedStateSampler() with a bad mean.
     *
     * <p>Note this test actually tests the SmallMeanPoissonSampler throws.
     */

    /**
     * Test createSharedStateSampler() with a mean that is too large.
     */

    // Sampling tests

    /**
     * Test the cache returns the same samples as the PoissonSampler when it
     * covers the entire range.
     */
    @Test
    void testCanComputeSameSamplesAsPoissonSamplerWithFullRangeCache() {
        checkComputeSameSamplesAsPoissonSampler(minRange,
                                                maxRange);
    }

    /**
     * Test the cache returns the same samples as the PoissonSampler
     * with no cache.
     */
    @Test
    void testCanComputeSameSamplesAsPoissonSamplerWithNoCache() {
        checkComputeSameSamplesAsPoissonSampler(0,
                                                minRange - 2);
    }

    /**
     * Test the cache returns the same samples as the PoissonSampler with
     * partial cache covering the lower range.
     */
    @Test
    void testCanComputeSameSamplesAsPoissonSamplerWithPartialCacheCoveringLowerRange() {
        checkComputeSameSamplesAsPoissonSampler(minRange,
                                                midRange);
    }

    /**
     * Test the cache returns the same samples as the PoissonSampler with
     * partial cache covering the upper range.
     */
    @Test
    void testCanComputeSameSamplesAsPoissonSamplerWithPartialCacheCoveringUpperRange() {
        checkComputeSameSamplesAsPoissonSampler(midRange,
                                                maxRange);
    }

    /**
     * Test the cache returns the same samples as the PoissonSampler with
     * cache above the upper range.
     */
    @Test
    void testCanComputeSameSamplesAsPoissonSamplerWithCacheAboveTheUpperRange() {
        checkComputeSameSamplesAsPoissonSampler(maxRange + 10,
                                                maxRange + 20);
    }

    /**
     * Check poisson samples are the same from the {@link PoissonSampler}
     * and {@link PoissonSamplerCache}.
     *
     * @param minMean the min mean of the cache
     * @param maxMean the max mean of the cache
     */
    private void checkComputeSameSamplesAsPoissonSampler(int minMean,
                                                         int maxMean) {
        // Two identical RNGs
        final RandomSource source = RandomSource.SPLIT_MIX_64;
        final long seed = RandomSource.createLong();
        final RestorableUniformRandomProvider rng1 = source.create(seed);
        final RestorableUniformRandomProvider rng2 = source.create(seed);

        // Create the cache with the given range
        final PoissonSamplerCache cache =
                createPoissonSamplerCache(minMean, maxMean);
        // Test all means in the test range (which may be different
        // from the cache range).
        for (int i = minRange; i <= maxRange; i++) {
            // Test integer mean (no SmallMeanPoissonSampler required)
            testPoissonSamples(rng1, rng2, cache, i);
            // Test non-integer mean (SmallMeanPoissonSampler required)
            testPoissonSamples(rng1, rng2, cache, i + 0.5);
        }
    }

    /**
     * Creates the poisson sampler cache with the given range.
     *
     * @param minMean the min mean
     * @param maxMean the max mean
     * @return the poisson sampler cache
     */
    private static PoissonSamplerCache createPoissonSamplerCache(double minMean,
                                                          double maxMean) {
        return new PoissonSamplerCache(minMean, maxMean);
    }

    /**
     * Creates a poisson sampler cache that will have a valid range for the cache.
     *
     * @return the poisson sampler cache
     */
    private static PoissonSamplerCache createPoissonSamplerCache() {
        return new PoissonSamplerCache(PoissonSampler.PIVOT,
                                       PoissonSampler.PIVOT + 10);
    }

    /**
     * Test poisson samples are the same from the {@link PoissonSampler}
     * and {@link PoissonSamplerCache}. The random providers must be
     * identical (including state).
     *
     * @param rng1  the first random provider
     * @param rng2  the second random provider
     * @param cache the cache
     * @param mean  the mean
     */
    private static void testPoissonSamples(
            final RestorableUniformRandomProvider rng1,
            final RestorableUniformRandomProvider rng2,
            PoissonSamplerCache cache,
            double mean) {
        final DiscreteSampler s1 = PoissonSampler.of(rng1, mean);
        final DiscreteSampler s2 = cache.createSharedStateSampler(rng2, mean);
        for (int j = 0; j < 10; j++) {
            Assertions.assertEquals(s1.sample(), s2.sample());
        }
    }

    /**
     * Test the cache returns the same samples as the PoissonSampler with
     * a new cache reusing the entire range.
     */
    @Test
    void testCanComputeSameSamplesAsPoissonSamplerReusingCacheEntireRange() {
        checkComputeSameSamplesAsPoissonSamplerReusingCache(midRange,
                                                            maxRange,
                                                            midRange,
                                                            maxRange);
    }

    /**
     * Test the cache returns the same samples as the PoissonSampler with
     * a new cache reusing none of the range.
     */
    @Test
    void testCanComputeSameSamplesAsPoissonSamplerReusingCacheNoRange() {
        checkComputeSameSamplesAsPoissonSamplerReusingCache(midRange,
                                                            maxRange,
                                                            maxRange + 10,
                                                            maxRange + 20);
    }

    /**
     * Test the cache returns the same samples as the PoissonSampler with
     * a new cache reusing some of the lower range.
     */
    @Test
    void testCanComputeSameSamplesAsPoissonSamplerReusingCacheLowerRange() {
        checkComputeSameSamplesAsPoissonSamplerReusingCache(midRange,
                                                            maxRange,
                                                            minRange,
                                                            midRange + 1);
    }

    /**
     * Test the cache returns the same samples as the PoissonSampler with
     * a new cache reusing some of the upper range.
     */
    @Test
    void testCanComputeSameSamplesAsPoissonSamplerReusingCacheUpperRange() {
        checkComputeSameSamplesAsPoissonSamplerReusingCache(midRange,
                                                            maxRange,
                                                            maxRange - 1,
                                                            maxRange + 5);
    }

    /**
     * Check poisson samples are the same from the {@link PoissonSampler}
     * and a {@link PoissonSamplerCache} created reusing values.
     *
     * <p>Note: This cannot check the cache values were reused but ensures
     * that a new cache created with a range functions correctly.
     *
     * @param minMean  the min mean of the cache
     * @param maxMean  the max mean of the cache
     * @param minMean2 the min mean of the second cache
     * @param maxMean2 the max mean of the second cache
     */
    private void checkComputeSameSamplesAsPoissonSamplerReusingCache(int minMean,
                                                                     int maxMean,
                                                                     int minMean2,
                                                                     int maxMean2) {
        // Two identical RNGs
        final RandomSource source = RandomSource.SPLIT_MIX_64;
        final long seed = RandomSource.createLong();
        final RestorableUniformRandomProvider rng1 = source.create(seed);
        final RestorableUniformRandomProvider rng2 = source.create(seed);

        // Create the cache with the given range and fill it
        final PoissonSamplerCache cache =
                createPoissonSamplerCache(minMean, maxMean);
        // Test all means in the test range (which may be different
        // from the cache range).
        for (int i = minMean; i <= maxMean; i++) {
            cache.createSharedStateSampler(rng1, i);
        }

        final PoissonSamplerCache cache2 = cache.withRange(minMean2, maxMean2);
        Assertions.assertNotSame(cache, cache2, "WithRange cache is the same object");

        // Test all means in the test range (which may be different
        // from the cache range).
        for (int i = minRange; i <= maxRange; i++) {
            // Test integer mean (no SmallMeanPoissonSampler required)
            testPoissonSamples(rng1, rng2, cache2, i);
            // Test non-integer mean (SmallMeanPoissonSampler required)
            testPoissonSamples(rng1, rng2, cache2, i + 0.5);
        }
    }

    /**
     * Explicit test for the deprecated method createPoissonSampler().
     * All other uses of this method have been removed.
     */

    @Test
    void testMinimumCachedMean_1_oe() {
        Assertions.assertEquals(PoissonSampler.PIVOT, PoissonSamplerCache.getMinimumCachedMean());
    }

    @Test
    void testConstructorWithNoCache_1_oe() {
        final double min = 0;
        final double max = PoissonSampler.PIVOT - 2;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        Assertions.assertFalse(cache.isValidRange());
    }

    @Test
    void testConstructorWithNoCache_2_oe() {
        final double min = 0;
        final double max = PoissonSampler.PIVOT - 2;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        // removed other assertion
        Assertions.assertEquals(0, cache.getMinMean());
    }

    @Test
    void testConstructorWithNoCache_3_oe() {
        final double min = 0;
        final double max = PoissonSampler.PIVOT - 2;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, cache.getMaxMean());
    }

    @Test
    void testConstructorWhenMaxEqualsMin_1_oe() {
        final double min = PoissonSampler.PIVOT + 2;
        final double max = min;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        Assertions.assertTrue(cache.isValidRange());
    }

    @Test
    void testConstructorWhenMaxEqualsMin_2_oe() {
        final double min = PoissonSampler.PIVOT + 2;
        final double max = min;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        // removed other assertion
        Assertions.assertEquals(min, cache.getMinMean());
    }

    @Test
    void testConstructorWhenMaxEqualsMin_3_oe() {
        final double min = PoissonSampler.PIVOT + 2;
        final double max = min;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.nextDown(Math.floor(max)+ 1),cache.getMaxMean());
    }

    @Test
    void testConstructorWhenMaxAboveMin_1_oe() {
        final double min = PoissonSampler.PIVOT + 2;
        final double max = min + 10;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        Assertions.assertTrue(cache.isValidRange());
    }

    @Test
    void testConstructorWhenMaxAboveMin_2_oe() {
        final double min = PoissonSampler.PIVOT + 2;
        final double max = min + 10;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        // removed other assertion
        Assertions.assertEquals(min, cache.getMinMean());
    }

    @Test
    void testConstructorWhenMaxAboveMin_3_oe() {
        final double min = PoissonSampler.PIVOT + 2;
        final double max = min + 10;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.nextDown(Math.floor(max)+ 1),cache.getMaxMean());
    }

    @Test
    void testConstructorThrowsWhenMaxIsLessThanMin_1_oe() {
        final double min = PoissonSampler.PIVOT;
        final double max = Math.nextDown(min);
        Assertions.assertThrows(IllegalArgumentException.class, () -> createPoissonSamplerCache(min, max));
    }

    @Test
    void testConstructorWhenMinBelow0_1_oe() {
        final double min = -1;
        final double max = PoissonSampler.PIVOT + 2;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        Assertions.assertTrue(cache.isValidRange());
    }

    @Test
    void testConstructorWhenMinBelow0_2_oe() {
        final double min = -1;
        final double max = PoissonSampler.PIVOT + 2;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        // removed other assertion
        Assertions.assertEquals(PoissonSampler.PIVOT, cache.getMinMean());
    }

    @Test
    void testConstructorWhenMinBelow0_3_oe() {
        final double min = -1;
        final double max = PoissonSampler.PIVOT + 2;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.nextDown(Math.floor(max)+ 1),cache.getMaxMean());
    }

    @Test
    void testConstructorWhenMaxBelow0_1_oe() {
        final double min = -10;
        final double max = -1;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        Assertions.assertFalse(cache.isValidRange());
    }

    @Test
    void testConstructorWhenMaxBelow0_2_oe() {
        final double min = -10;
        final double max = -1;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        // removed other assertion
        Assertions.assertEquals(0, cache.getMinMean());
    }

    @Test
    void testConstructorWhenMaxBelow0_3_oe() {
        final double min = -10;
        final double max = -1;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, cache.getMaxMean());
    }

    @Test
    void testWithRangeConstructorWithNoCache_1_oe() {
        final double min = 0;
        final double max = PoissonSampler.PIVOT - 2;
        final PoissonSamplerCache cache = createPoissonSamplerCache().withRange(min, max);
        Assertions.assertFalse(cache.isValidRange());
    }

    @Test
    void testWithRangeConstructorWithNoCache_2_oe() {
        final double min = 0;
        final double max = PoissonSampler.PIVOT - 2;
        final PoissonSamplerCache cache = createPoissonSamplerCache().withRange(min, max);
        // removed other assertion
        Assertions.assertEquals(0, cache.getMinMean());
    }

    @Test
    void testWithRangeConstructorWithNoCache_3_oe() {
        final double min = 0;
        final double max = PoissonSampler.PIVOT - 2;
        final PoissonSamplerCache cache = createPoissonSamplerCache().withRange(min, max);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, cache.getMaxMean());
    }

    @Test
    void testWithRangeConstructorWhenMaxEqualsMin_1_oe() {
        final double min = PoissonSampler.PIVOT + 2;
        final double max = min;
        final PoissonSamplerCache cache = createPoissonSamplerCache().withRange(min, max);
        Assertions.assertTrue(cache.isValidRange());
    }

    @Test
    void testWithRangeConstructorWhenMaxEqualsMin_2_oe() {
        final double min = PoissonSampler.PIVOT + 2;
        final double max = min;
        final PoissonSamplerCache cache = createPoissonSamplerCache().withRange(min, max);
        // removed other assertion
        Assertions.assertEquals(min, cache.getMinMean());
    }

    @Test
    void testWithRangeConstructorWhenMaxEqualsMin_3_oe() {
        final double min = PoissonSampler.PIVOT + 2;
        final double max = min;
        final PoissonSamplerCache cache = createPoissonSamplerCache().withRange(min, max);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.nextDown(Math.floor(max)+ 1),cache.getMaxMean());
    }

    @Test
    void testWithRangeConstructorWhenMaxAboveMin_1_oe() {
        final double min = PoissonSampler.PIVOT + 2;
        final double max = min + 10;
        final PoissonSamplerCache cache = createPoissonSamplerCache().withRange(min, max);
        Assertions.assertTrue(cache.isValidRange());
    }

    @Test
    void testWithRangeConstructorWhenMaxAboveMin_2_oe() {
        final double min = PoissonSampler.PIVOT + 2;
        final double max = min + 10;
        final PoissonSamplerCache cache = createPoissonSamplerCache().withRange(min, max);
        // removed other assertion
        Assertions.assertEquals(min, cache.getMinMean());
    }

    @Test
    void testWithRangeConstructorWhenMaxAboveMin_3_oe() {
        final double min = PoissonSampler.PIVOT + 2;
        final double max = min + 10;
        final PoissonSamplerCache cache = createPoissonSamplerCache().withRange(min, max);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.nextDown(Math.floor(max)+ 1),cache.getMaxMean());
    }

    @Test
    void testWithRangeConstructorThrowsWhenMaxIsLessThanMin_1_oe() {
        final double min = PoissonSampler.PIVOT;
        final double max = Math.nextDown(min);
        Assertions.assertThrows(IllegalArgumentException.class, () -> createPoissonSamplerCache().withRange(min, max));
    }

    @Test
    void testWithRangeConstructorWhenMinBelow0_1_oe() {
        final double min = -1;
        final double max = PoissonSampler.PIVOT + 2;
        final PoissonSamplerCache cache = createPoissonSamplerCache().withRange(min, max);
        Assertions.assertTrue(cache.isValidRange());
    }

    @Test
    void testWithRangeConstructorWhenMinBelow0_2_oe() {
        final double min = -1;
        final double max = PoissonSampler.PIVOT + 2;
        final PoissonSamplerCache cache = createPoissonSamplerCache().withRange(min, max);
        // removed other assertion
        Assertions.assertEquals(PoissonSampler.PIVOT, cache.getMinMean());
    }

    @Test
    void testWithRangeConstructorWhenMinBelow0_3_oe() {
        final double min = -1;
        final double max = PoissonSampler.PIVOT + 2;
        final PoissonSamplerCache cache = createPoissonSamplerCache().withRange(min, max);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.nextDown(Math.floor(max)+ 1),cache.getMaxMean());
    }

    @Test
    void testWithRangeConstructorWhenCacheHasNoCapcity_1_oe() {
        final double min = PoissonSampler.PIVOT + 2;
        final double max = min + 10;
        final PoissonSamplerCache cache = createPoissonSamplerCache(0, 0).withRange(min, max);
        Assertions.assertTrue(cache.isValidRange());
    }

    @Test
    void testWithRangeConstructorWhenCacheHasNoCapcity_2_oe() {
        final double min = PoissonSampler.PIVOT + 2;
        final double max = min + 10;
        final PoissonSamplerCache cache = createPoissonSamplerCache(0, 0).withRange(min, max);
        // removed other assertion
        Assertions.assertEquals(min, cache.getMinMean());
    }

    @Test
    void testWithRangeConstructorWhenCacheHasNoCapcity_3_oe() {
        final double min = PoissonSampler.PIVOT + 2;
        final double max = min + 10;
        final PoissonSamplerCache cache = createPoissonSamplerCache(0, 0).withRange(min, max);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.nextDown(Math.floor(max)+ 1),cache.getMaxMean());
    }

    @Test
    void testWithinRange_1_oe() {
        final double min = PoissonSampler.PIVOT + 10;
        final double max = PoissonSampler.PIVOT + 20;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        // Under the pivot point is always within range
        Assertions.assertTrue(cache.withinRange(PoissonSampler.PIVOT - 1));
    }

    @Test
    void testWithinRange_2_oe() {
        final double min = PoissonSampler.PIVOT + 10;
        final double max = PoissonSampler.PIVOT + 20;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        // Under the pivot point is always within range
        // removed other assertion
        Assertions.assertFalse(cache.withinRange(min - 1));
    }

    @Test
    void testWithinRange_3_oe() {
        final double min = PoissonSampler.PIVOT + 10;
        final double max = PoissonSampler.PIVOT + 20;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        // Under the pivot point is always within range
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(cache.withinRange(min));
    }

    @Test
    void testWithinRange_4_oe() {
        final double min = PoissonSampler.PIVOT + 10;
        final double max = PoissonSampler.PIVOT + 20;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        // Under the pivot point is always within range
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(cache.withinRange(max));
    }

    @Test
    void testWithinRange_5_oe() {
        final double min = PoissonSampler.PIVOT + 10;
        final double max = PoissonSampler.PIVOT + 20;
        final PoissonSamplerCache cache = createPoissonSamplerCache(min, max);
        // Under the pivot point is always within range
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(cache.withinRange(max + 10));
    }

    @Test
    void testCreateSharedStateSamplerThrowsWithZeroMean_1_oe() {
        final RestorableUniformRandomProvider rng =
                RandomSource.SPLIT_MIX_64.create(0L);
        final PoissonSamplerCache cache = createPoissonSamplerCache();
        Assertions.assertThrows(IllegalArgumentException.class, () -> cache.createSharedStateSampler(rng, 0));
    }

    @Test
    void testCreateSharedStateSamplerThrowsWithNonIntegerMean_1_oe() {
        final RestorableUniformRandomProvider rng =
                RandomSource.SPLIT_MIX_64.create(0L);
        final PoissonSamplerCache cache = createPoissonSamplerCache();
        final double mean = Integer.MAX_VALUE + 1.0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> cache.createSharedStateSampler(rng, mean));
    }

    @Test
    void testCreatePoissonSampler_1_oe() {
        final PoissonSamplerCache cache = createPoissonSamplerCache(0, 100);
        final DiscreteSampler s2 = cache.createPoissonSampler(null, 42);
        Assertions.assertTrue(s2 instanceof LargeMeanPoissonSampler);
    }

}
