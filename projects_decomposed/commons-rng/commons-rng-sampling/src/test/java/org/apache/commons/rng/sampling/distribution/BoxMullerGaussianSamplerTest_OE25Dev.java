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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test for the {@link BoxMullerGaussianSampler}. The tests hit edge cases for the sampler.
 */
class BoxMullerGaussianSamplerTest_OE25Dev {
    /**
     * Test the constructor with a bad standard deviation.
     */

    @Test
    void testConstructorThrowsWithZeroStandardDeviation_1_oe() {
         RestorableUniformRandomProvider rng =
            RandomSource.SPLIT_MIX_64.create(0L);
         double mean = 1;
         double standardDeviation = 0;
        try {
    new BoxMullerGaussianSampler(rng, mean, standardDeviation);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
