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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.apache.commons.math3.stat.inference.ChiSquareTest;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests for {@link PermutationSampler}.
 */
class PermutationSamplerTest_OE25Dev {
    private final UniformRandomProvider rng = RandomSource.ISAAC.create(1232343456L);
    private final ChiSquareTest chiSquareTest = new ChiSquareTest();

    @Test
    void testSampleChiSquareTest() {
        final int n = 3;
        final int k = 3;
        final int[][] p = {{0, 1, 2}, {0, 2, 1},
                           {1, 0, 2}, {1, 2, 0},
                           {2, 0, 1}, {2, 1, 0}};
        runSampleChiSquareTest(n, k, p);
    }

    @Test
    void testSubSampleChiSquareTest() {
        final int n = 4;
        final int k = 2;
        final int[][] p = {{0, 1}, {1, 0},
                           {0, 2}, {2, 0},
                           {0, 3}, {3, 0},
                           {1, 2}, {2, 1},
                           {1, 3}, {3, 1},
                           {2, 3}, {3, 2}};
        runSampleChiSquareTest(n, k, p);
    }

    /**
     * Test the SharedStateSampler implementation.
     */
    @Test
    void testSharedStateSampler() {
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
        final int n = 17;
        final int k = 13;
        final PermutationSampler sampler1 =
            new PermutationSampler(rng1, n, k);
        final PermutationSampler sampler2 = sampler1.withUniformRandomProvider(rng2);
        RandomAssert.assertProduceSameSequence(sampler1, sampler2);
    }

    //// Support methods.

    private void runSampleChiSquareTest(int n,
                                        int k,
                                        int[][] p) {
        final int len = p.length;
        final long[] observed = new long[len];
        final int numSamples = 6000;
        final double numExpected = numSamples / (double) len;
        final double[] expected = new double[len];
        Arrays.fill(expected, numExpected);

        final PermutationSampler sampler = new PermutationSampler(rng, n, k);
        for (int i = 0; i < numSamples; i++) {
            observed[findPerm(p, sampler.sample())]++;
        }

        // Pass if we cannot reject null hypothesis that distributions are the same.
        Assertions.assertFalse(chiSquareTest.chiSquareTest(expected, observed, 0.001));
    }

    private static int findPerm(int[][] p,
                                int[] samp) {
        for (int i = 0; i < p.length; i++) {
            if (Arrays.equals(p[i], samp)) {
                return i;
            }
        }
        Assertions.fail("Permutation not found");
        return -1;
    }

    @Test
    void testSampleTrivial_1_oe() {
         int n = 6;
         int k = 3;
         PermutationSampler sampler = new PermutationSampler(RandomSource.KISS.create(),
                                                                  n, k);
         int[] random = sampler.sample();
        SAMPLE: for (int s : random) {
            for (int i = 0; i < n; i++) {
                if (i == s) {
                    continue SAMPLE;
                }
            }
            Assertions.fail("number " + s + " not in array");
    }
    }

    @Test
    void testSampleBoundaryCase_1_oe() {
         PermutationSampler sampler = new PermutationSampler(rng, 1, 1);
         int[] perm = sampler.sample();
        Assertions.assertEquals(1, perm.length);
    }

    @Test
    void testSampleBoundaryCase_2_oe() {
         PermutationSampler sampler = new PermutationSampler(rng, 1, 1);
         int[] perm = sampler.sample();
        Assertions.assertEquals(0, perm[0]);
    }

    @Test
    void testSamplePrecondition1_1_oe() {
        try {
    new PermutationSampler(rng, 2, 3);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testSamplePrecondition2_1_oe() {
        try {
    new PermutationSampler(rng, 0, 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testSamplePrecondition3_1_oe() {
        try {
    new PermutationSampler(rng, -1, 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testSamplePrecondition4_1_oe() {
        try {
    new PermutationSampler(rng, 1, -1);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testNatural_1_oe() {
         int n = 4;
         int[] expected = {0, 1, 2, 3};

         int[] natural = PermutationSampler.natural(n);
        for (int i = 0; i < n; i++) {
            Assertions.assertEquals(expected[i], natural[i]);
    }
    }

    @Test
    void testNaturalZero_1_oe() {
         int[] natural = PermutationSampler.natural(0);
        Assertions.assertEquals(0, natural.length);
    }

    @Test
    void testShuffleNoDuplicates_1_oe() {
         int n = 100;
         int[] orig = PermutationSampler.natural(n);
        PermutationSampler.shuffle(rng, orig);

         int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            count[orig[i]] += 1;
        }

        for (int i = 0; i < n; i++) {
            Assertions.assertEquals(1, count[i]);
    }
    }

    @Test
    void testShuffleTail_1_oe() {
         int[] orig = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
         int[] list = orig.clone();
         int start = 4;
        PermutationSampler.shuffle(rng, list, start, false);

        for (int i = 0; i < start; i++) {
            Assertions.assertEquals(orig[i], list[i]);
    }
    }

    @Test
    void testShuffleTail_2_oe() {
         int[] orig = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
         int[] list = orig.clone();
         int start = 4;
        PermutationSampler.shuffle(rng, list, start, false);

        for (int i = 0; i < start; i++) {
        }

        boolean ok = false;
        for (int i = start; i < orig.length - 1; i++) {
            if (orig[i] != list[i]) {
                ok = true;
                break;
            }
        }
        Assertions.assertTrue(ok);
    }

    @Test
    void testShuffleHead_1_oe() {
         int[] orig = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
         int[] list = orig.clone();
         int start = 4;
        PermutationSampler.shuffle(rng, list, start, true);

        for (int i = start + 1; i < orig.length; i++) {
            Assertions.assertEquals(orig[i], list[i]);
    }
    }

    @Test
    void testShuffleHead_2_oe() {
         int[] orig = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
         int[] list = orig.clone();
         int start = 4;
        PermutationSampler.shuffle(rng, list, start, true);

        for (int i = start + 1; i < orig.length; i++) {
        }

        boolean ok = false;
        for (int i = 0; i <= start; i++) {
            if (orig[i] != list[i]) {
                ok = true;
                break;
            }
        }
        Assertions.assertTrue(ok);
    }

}
