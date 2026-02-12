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
import org.apache.commons.rng.simple.RandomSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for the {@link SamplerBase}. The class is deprecated but is public. The methods
 * should be tested to ensure correct functionality.
 */
class SamplerBaseTest_OE25Dev {
    /**
     * Extends the {@link SamplerBase} to allow access to methods.
     */
    @SuppressWarnings("deprecation")
    private static class SimpleSampler extends SamplerBase {
        SimpleSampler(UniformRandomProvider rng) {
            super(rng);
        }

        @Override
        public double nextDouble() {
            return super.nextDouble();
        }

        @Override
        public int nextInt() {
            return super.nextInt();
        }

        @Override
        public int nextInt(int max) {
            return super.nextInt(max);
        }

        @Override
        public long nextLong() {
            return super.nextLong();
        }
    }

    @Test
    void testNextMethods_1_oe() {
         UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
         UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
         SimpleSampler sampler = new SimpleSampler(rng2);
         int n = 256;
        for (int i = 0; i < 3; i++) {
            Assertions.assertEquals(rng1.nextDouble(), sampler.nextDouble());
    }
    }

    @Test
    void testNextMethods_2_oe() {
         UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
         UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
         SimpleSampler sampler = new SimpleSampler(rng2);
         int n = 256;
        for (int i = 0; i < 3; i++) {
            // removed other assertion
            Assertions.assertEquals(rng1.nextInt(), sampler.nextInt());
    }
    }

    @Test
    void testNextMethods_3_oe() {
         UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
         UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
         SimpleSampler sampler = new SimpleSampler(rng2);
         int n = 256;
        for (int i = 0; i < 3; i++) {
            // removed other assertion
            // removed other assertion
            Assertions.assertEquals(rng1.nextInt(n), sampler.nextInt(n));
    }
    }

    @Test
    void testNextMethods_4_oe() {
         UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
         UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
         SimpleSampler sampler = new SimpleSampler(rng2);
         int n = 256;
        for (int i = 0; i < 3; i++) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            Assertions.assertEquals(rng1.nextLong(), sampler.nextLong());
    }
    }

    @Test
    void testToString_1_oe() {
         UniformRandomProvider rng = RandomSource.SPLIT_MIX_64.create(0L);
         SimpleSampler sampler = new SimpleSampler(rng);
        Assertions.assertTrue(sampler.toString().contains("rng"));
    }

}
