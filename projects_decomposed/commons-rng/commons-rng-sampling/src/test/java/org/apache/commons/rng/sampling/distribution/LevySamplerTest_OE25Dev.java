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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
