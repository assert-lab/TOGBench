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
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests for {@link CombinationSampler}.
 */
class CombinationSamplerTest_OE25Dev {
    private final UniformRandomProvider rng = RandomSource.XO_RO_SHI_RO_128_PP.create();

    /**
     * Test the SharedStateSampler implementation.
     */
    @Test
    void testSharedStateSampler() {
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
        final int n = 17;
        final int k = 3;
        final CombinationSampler sampler1 =
            new CombinationSampler(rng1, n, k);
        final CombinationSampler sampler2 = sampler1.withUniformRandomProvider(rng2);
        RandomAssert.assertProduceSameSequence(sampler1, sampler2);
    }

    //// Support methods.

    /**
     * Asserts the sample value is in the range 0 to n-1.
     *
     * @param n     the n
     * @param value the sample value
     */
    private static void assertIsInDomain(int n, int value) {
        if (value < 0 || value >= n) {
            Assertions.fail("sample " + value + " not in the domain " + n);
        }
    }

    private void assertUniformSamples(int n, int k) {
        // The C(n, k) should generate a sample of unspecified order.
        // To test this each combination is allocated a unique code
        // based on setting k of the first n-bits in an integer.
        // Codes are positive for all combinations of bits that use k-bits,
        // otherwise they are negative.
        final int totalBitCombinations = 1 << n;
        int[] codeLookup = new int[totalBitCombinations];
        Arrays.fill(codeLookup, -1); // initialize as negative
        int codes = 0;
        for (int i = 0; i < totalBitCombinations; i++) {
            if (Integer.bitCount(i) == k) {
                // This is a valid sample so allocate a code
                codeLookup[i] = codes++;
            }
        }

        // The number of combinations C(n, k) is the binomial coefficient
        Assertions.assertEquals(CombinatoricsUtils.binomialCoefficient(n,k),codes,"Incorrect number of combination codes");

        final long[] observed = new long[codes];
        final int numSamples = 6000;

        final CombinationSampler sampler = new CombinationSampler(rng, n, k);
        for (int i = 0; i < numSamples; i++) {
            observed[findCode(codeLookup, sampler.sample())]++;
        }

        // Chi squared test of uniformity
        final double numExpected = numSamples / (double) codes;
        final double[] expected = new double[codes];
        Arrays.fill(expected, numExpected);
        final ChiSquareTest chiSquareTest = new ChiSquareTest();
        // Pass if we cannot reject null hypothesis that distributions are the same.
        Assertions.assertFalse(chiSquareTest.chiSquareTest(expected, observed, 0.001));
    }

    private static int findCode(int[] codeLookup, int[] sample) {
        // Each sample index is used to set a bit in an integer.
        // The resulting bits should be a valid code.
        int bits = 0;
        for (int s : sample) {
            // This shift will be from 0 to n-1 since it is from the
            // domain of size n.
            bits |= 1 << s;
        }
        if (bits >= codeLookup.length) {
            Assertions.fail("Bad bit combination: " + Arrays.toString(sample));
        }
        final int code = codeLookup[bits];
        if (code < 0) {
            Assertions.fail("Bad bit code: " + Arrays.toString(sample));
        }
        return code;
    }

    @Test
    void testSampleWhenNequalsKIsNotShuffled_1_oe() {
        for (int n = 1; n < 3; n++) {
             int k = n;
             CombinationSampler sampler = new CombinationSampler(rng, n, k);
             int[] sample = sampler.sample();
            Assertions.assertEquals(n, sample.length, "Incorrect sample length");
    }
    }

    @Test
    void testSampleWhenNequalsKIsNotShuffled_2_oe() {
        for (int n = 1; n < 3; n++) {
             int k = n;
             CombinationSampler sampler = new CombinationSampler(rng, n, k);
             int[] sample = sampler.sample();
            for (int i = 0; i < n; i++) {
                Assertions.assertEquals(i, sample[i], "Sample was shuffled");
    }
    }
    }

    @Test
    void testKgreaterThanNThrows_1_oe() {
         int n = 2;
         int k = 3;
        try {
    new CombinationSampler(rng, n, k);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testNequalsZeroThrows_1_oe() {
         int n = 0;
         int k = 3;
        try {
    new CombinationSampler(rng, n, k);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testKequalsZeroThrows_1_oe() {
         int n = 2;
         int k = 0;
        try {
    new CombinationSampler(rng, n, k);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testNisNegativeThrows_1_oe() {
         int n = -1;
         int k = 3;
        try {
    new CombinationSampler(rng, n, k);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testKisNegativeThrows_1_oe() {
         int n = 0;
         int k = -1;
        try {
    new CombinationSampler(rng, n, k);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testSampleIsInDomain_1_oe_1_oe() {
         int n = 6;
        for (int k = 1; k <= n; k++) {
             CombinationSampler sampler = new CombinationSampler(rng, n, k);
             int[] random = sampler.sample();
            for (int s : random) {
                                 int n0 = n;
                 int value0 = s;
                if (value0 < 0 || value0 >= n0) {
                            Assertions.fail("sample " + value0 + " not in the domain " + n0);
    }
    }
    }
    }

    @Test
    void testUniformWithKlessThanHalfN_1_oe_1_oe() {
         int n = 8;
         int k = 2;
                 int n0 = n;
         int k0 = k;
                 int totalBitCombinations0 = 1 << n0;
                int[] codeLookup0 = new int[totalBitCombinations0];
                Arrays.fill(codeLookup0, -1); // initialize as negative
                int codes0 = 0;
                for (int i0 = 0; i0 < totalBitCombinations0; i0++) {
                    if (Integer.bitCount(i0) == k0) {
                        codeLookup0[i0] = codes0++;
                    }
                }
        
                Assertions.assertEquals(CombinatoricsUtils.binomialCoefficient(n0,k0),codes0,"Incorrect number of combination codes0");
    }

    @Test
    void testUniformWithKlessThanHalfN_1_oe_2_oe() {
         int n = 8;
         int k = 2;
                 int n0 = n;
         int k0 = k;
                 int totalBitCombinations0 = 1 << n0;
                int[] codeLookup0 = new int[totalBitCombinations0];
                Arrays.fill(codeLookup0, -1); // initialize as negative
                int codes0 = 0;
                for (int i0 = 0; i0 < totalBitCombinations0; i0++) {
                    if (Integer.bitCount(i0) == k0) {
                        codeLookup0[i0] = codes0++;
                    }
                }
        
        
                 long[] observed0 = new long[codes0];
                 int numSamples0 = 6000;
        
                 CombinationSampler sampler0 = new CombinationSampler(rng, n0, k0);
                for (int i0 = 0; i0 < numSamples0; i0++) {
                    observed0[findCode(codeLookup0, sampler0.sample())]++;
                }
        
                 double numExpected0 = numSamples0 / (double) codes0;
                 double[] expected0 = new double[codes0];
                Arrays.fill(expected0, numExpected0);
                 ChiSquareTest chiSquareTest0 = new ChiSquareTest();
                Assertions.assertFalse(chiSquareTest0.chiSquareTest(expected0, observed0, 0.001));
    }

    @Test
    void testUniformWithKmoreThanHalfN_1_oe_1_oe() {
         int n = 8;
         int k = 6;
                 int n0 = n;
         int k0 = k;
                 int totalBitCombinations0 = 1 << n0;
                int[] codeLookup0 = new int[totalBitCombinations0];
                Arrays.fill(codeLookup0, -1); // initialize as negative
                int codes0 = 0;
                for (int i0 = 0; i0 < totalBitCombinations0; i0++) {
                    if (Integer.bitCount(i0) == k0) {
                        codeLookup0[i0] = codes0++;
                    }
                }
        
                Assertions.assertEquals(CombinatoricsUtils.binomialCoefficient(n0,k0),codes0,"Incorrect number of combination codes0");
    }

}
