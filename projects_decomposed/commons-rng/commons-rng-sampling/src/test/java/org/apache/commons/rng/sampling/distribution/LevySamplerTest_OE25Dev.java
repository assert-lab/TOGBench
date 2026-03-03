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
import org.apache.commons.rng.core.source64.SplitMix64;
import org.apache.commons.rng.sampling.RandomAssert;
import org.apache.commons.rng.simple.RandomSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test for the {@link LevySampler}.
 */
class LevySamplerTest_OE25Dev {
    /**
     * Test the constructor with a negative scale.
     */

    /**
     * Test the constructor with a zero scale.
     */

    /**
     * Test the SharedStateSampler implementation.
     */
    @Test
    void testSharedStateSampler() {
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
        final double location = 4.56;
        final double scale = 1.23;
        final LevySampler sampler1 = LevySampler.of(rng1, location, scale);
        final LevySampler sampler2 = sampler1.withUniformRandomProvider(rng2);
        RandomAssert.assertProduceSameSequence(sampler1, sampler2);
    }

    /**
     * Test the support of the standard distribution is {@code [0, inf)}.
     */

    @Test
    void testConstructorThrowsWithNegativeScale_1_oe() {
         UniformRandomProvider rng = RandomSource.SPLIT_MIX_64.create(0L);
         double location = 1;
         double scale = -1e-6;
        try {
    LevySampler.of(rng, location, scale);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConstructorThrowsWithZeroScale_1_oe() {
         UniformRandomProvider rng = RandomSource.SPLIT_MIX_64.create(0L);
         double location = 1;
         double scale = 0;
        try {
    LevySampler.of(rng, location, scale);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testSupport_1_oe() {
         double location = 0.0;
         double scale = 1.0;
         LevySampler s1 = LevySampler.of(
            new SplitMix64(0L) {
                @Override
                public long next() {
                    return 0L;
                }
            }, location, scale);
        Assertions.assertEquals(Double.POSITIVE_INFINITY, s1.sample());
    }

    @Test
    void testSupport_2_oe() {
         double location = 0.0;
         double scale = 1.0;
         LevySampler s1 = LevySampler.of(
            new SplitMix64(0L) {
                @Override
                public long next() {
                    return 0L;
                }
            }, location, scale);

         long[] sequence = {
            -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, 0,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0,
        };
         LevySampler s2 = LevySampler.of(
            new SplitMix64(0L) {
                private int i;
                @Override
                public long next() {
                    if (i++ < sequence.length) {
                        return sequence[i - 1];
                    }
                    return super.next();
                }
            }, location, scale);
         double s = 4 * 7.569274694148063 * 0.27502700159745347 + 3.6360066255009455861;
         double expected = 1 / (s * s);
        Assertions.assertEquals(expected, s2.sample());
    }

}
