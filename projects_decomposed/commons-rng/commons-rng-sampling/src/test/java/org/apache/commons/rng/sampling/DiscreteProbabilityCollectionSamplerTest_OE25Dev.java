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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

/**
 * Test class for {@link DiscreteProbabilityCollectionSampler}.
 */
class DiscreteProbabilityCollectionSamplerTest_OE25Dev {
    /** RNG. */
    private final UniformRandomProvider rng = RandomSource.XO_SHI_RO_128_PP.create();

    /**
     * Edge-case test:
     * Create a sampler that will return 1 for nextDouble() forcing the search to
     * identify the end item of the cumulative probability array.
     */

    /**
     * Test the SharedStateSampler implementation.
     */
    @Test
    void testSharedStateSampler() {
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
        final List<Double> items = Arrays.asList(1d, 2d, 3d, 4d);
        final DiscreteProbabilityCollectionSampler<Double> sampler1 =
            new DiscreteProbabilityCollectionSampler<>(rng1,
                                                       items,
                                                       new double[] {0.1, 0.2, 0.3, 0.4});
        final DiscreteProbabilityCollectionSampler<Double> sampler2 = sampler1.withUniformRandomProvider(rng2);
        RandomAssert.assertProduceSameSequence(sampler1, sampler2);
    }

    @Test
    void testPrecondition1_1_oe() {
        // Size mismatch
        try {
    new DiscreteProbabilityCollectionSampler<>(rng, Arrays.asList(1d, 2d), new double[] {0d});
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPrecondition2_1_oe() {
        // Negative probability
        try {
    new DiscreteProbabilityCollectionSampler<>(rng, Arrays.asList(1d, 2d), new double[] {0d, -1d});
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPrecondition3_1_oe() {
        // Probabilities do not sum above 0
        try {
    new DiscreteProbabilityCollectionSampler<>(rng, Arrays.asList(1d, 2d), new double[] {0d, 0d});
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPrecondition4_1_oe() {
        // NaN probability
        try {
    new DiscreteProbabilityCollectionSampler<>(rng, Arrays.asList(1d, 2d), new double[] {0d, Double.NaN});
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPrecondition5_1_oe() {
        // Infinite probability
        try {
    new DiscreteProbabilityCollectionSampler<>(rng, Arrays.asList(1d, 2d), new double[] {0d, Double.POSITIVE_INFINITY});
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPrecondition6_1_oe() {
        // Empty Map<T, Double> not allowed
        try {
    new DiscreteProbabilityCollectionSampler<>(rng, new HashMap<>());
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPrecondition7_1_oe() {
        // Empty List<T> not allowed
        try {
    new DiscreteProbabilityCollectionSampler<>(rng, Collections.<Double>emptyList(), new double[0]);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
