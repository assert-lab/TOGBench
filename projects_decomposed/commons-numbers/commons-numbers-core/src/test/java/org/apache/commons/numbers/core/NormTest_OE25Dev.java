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
package org.apache.commons.numbers.core;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.function.ToDoubleFunction;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NormTest_OE25Dev {

    private static final int SMALL_THRESH_EXP = -511;

    private static final int LARGE_THRESH_EXP = +496;

    private static final int RAND_VECTOR_CNT = 1_000;

    private static final int MAX_ULP_ERR = 1;

    private static final double HYPOT_COMPARE_EPS = 1e-2;

    private static final BigDecimal BD_MAX_VALUE = new BigDecimal(Double.MAX_VALUE);
    private static final BigDecimal BD_MIN_NORMAL = new BigDecimal(Double.MIN_NORMAL);

    /** The scale, used to scale the sqrt of the sum of squares. */
    private static final double SCALE = 0x1.0p200;

    /** The scale squared, used to scale the sum of squares. */
    private static final BigDecimal SCALE2 = new BigDecimal(SCALE * SCALE);

    @Test
    void testEuclidean_2d_scaled() {
        // arrange
        final double[] ones = new double[] {1, 1};
        final double[] multiplesOfTen = new double[] {1, 10};

        // act/assert
        checkScaledEuclideanNorm(ones, 0);
        checkScaledEuclideanNorm(ones, LARGE_THRESH_EXP);
        checkScaledEuclideanNorm(ones, LARGE_THRESH_EXP + 1);
        checkScaledEuclideanNorm(ones, -100);
        checkScaledEuclideanNorm(ones, -101);
        checkScaledEuclideanNorm(ones, SMALL_THRESH_EXP);
        checkScaledEuclideanNorm(ones, SMALL_THRESH_EXP - 1);


        checkScaledEuclideanNorm(multiplesOfTen, 0);
        checkScaledEuclideanNorm(multiplesOfTen, -100);
        checkScaledEuclideanNorm(multiplesOfTen, -101);
        checkScaledEuclideanNorm(multiplesOfTen, LARGE_THRESH_EXP - 1);
        checkScaledEuclideanNorm(multiplesOfTen, SMALL_THRESH_EXP);
    }

    @Test
    void testEuclidean_2d_random() {
        // arrange
        final UniformRandomProvider rng = RandomSource.create(RandomSource.XO_RO_SHI_RO_1024_PP, 1L);

        // act/assert
        checkEuclideanRandom(2, rng);
    }

    /** Assert that the Norms euclidean 2D computation produces similar error behavior to Math.hypot().
     * @param minExp minimum exponent for random inputs
     * @param maxExp maximum exponent for random inputs
     * @param samples sample count
     * @param rng random number generator
     */
    private static void assertEuclidean2dVersusHypot(final int minExp,
                                                     final int maxExp,
                                                     final int samples,
                                                     final UniformRandomProvider rng) {
        // generate random inputs
        final double[][] inputs = new double[samples][];
        for (int i = 0; i < samples; ++i) {
            inputs[i] = DoubleTestUtils.randomArray(2, minExp, maxExp, rng);
        }

        // compute exact results
        final double[] exactResults = new double[samples];
        for (int i = 0; i < samples; ++i) {
            exactResults[i] = exactEuclideanNorm(inputs[i]);
        }

        // compute the std devs
        final UlpErrorStats hypotStats = computeUlpErrorStats(inputs, exactResults, v -> Math.hypot(v[0], v[1]));
        final UlpErrorStats normStats = computeUlpErrorStats(inputs, exactResults, v -> Norm.L2.of(v[0], v[1]));

        // ensure that we are within the ballpark of Math.hypot
        Assertions.assertTrue(normStats.getMean()<=(hypotStats.getMean()+ HYPOT_COMPARE_EPS),()-> "Expected 2D norm result to have similar error mean to Math.hypot(): hypot error mean= " + hypotStats.getMean()+ ",norm error mean= " + normStats.getMean());

        Assertions.assertTrue(normStats.getStdDev()<=(hypotStats.getStdDev()+ HYPOT_COMPARE_EPS),()-> "Expected 2D norm result to have similar std deviation to Math.hypot(): hypot std dev= " + hypotStats.getStdDev()+ ",norm std dev= " + normStats.getStdDev());
    }

    @Test
    void testEuclidean_3d_scaled() {
        // arrange
        final double[] ones = new double[] {1, 1, 1};
        final double[] multiplesOfTen = new double[] {1, 10, 100};

        // act/assert
        checkScaledEuclideanNorm(ones, 0);
        checkScaledEuclideanNorm(ones, LARGE_THRESH_EXP);
        checkScaledEuclideanNorm(ones, LARGE_THRESH_EXP + 1);
        checkScaledEuclideanNorm(ones, -100);
        checkScaledEuclideanNorm(ones, -101);
        checkScaledEuclideanNorm(ones, SMALL_THRESH_EXP);
        checkScaledEuclideanNorm(ones, SMALL_THRESH_EXP - 1);

        checkScaledEuclideanNorm(multiplesOfTen, 0);
        checkScaledEuclideanNorm(multiplesOfTen, -100);
        checkScaledEuclideanNorm(multiplesOfTen, -101);
        checkScaledEuclideanNorm(multiplesOfTen, LARGE_THRESH_EXP - 1);
        checkScaledEuclideanNorm(multiplesOfTen, SMALL_THRESH_EXP - 1);
    }

    @Test
    void testEuclidean_3d_random() {
        // arrange
        final UniformRandomProvider rng = RandomSource.create(RandomSource.XO_RO_SHI_RO_1024_PP, 1L);

        // act/assert
        checkEuclideanRandom(3, rng);
    }

    @Test
    void testEuclidean_array_scaled() {
        // arrange
        final double[] ones = new double[] {1, 1, 1, 1};
        final double[] multiplesOfTen = new double[] {1, 10, 100, 1000};

        // act/assert
        checkScaledEuclideanNorm(ones, 0);
        checkScaledEuclideanNorm(ones, LARGE_THRESH_EXP);
        checkScaledEuclideanNorm(ones, LARGE_THRESH_EXP + 1);
        checkScaledEuclideanNorm(ones, SMALL_THRESH_EXP);
        checkScaledEuclideanNorm(ones, SMALL_THRESH_EXP - 1);

        checkScaledEuclideanNorm(multiplesOfTen, 1);
        checkScaledEuclideanNorm(multiplesOfTen, LARGE_THRESH_EXP - 1);
        checkScaledEuclideanNorm(multiplesOfTen, SMALL_THRESH_EXP - 1);
    }

    @Test
    void testEuclidean_array_random() {
        // arrange
        final UniformRandomProvider rng = RandomSource.create(RandomSource.XO_RO_SHI_RO_1024_PP, 1L);

        // act/assert
        checkEuclideanRandom(2, rng);
        checkEuclideanRandom(3, rng);
        checkEuclideanRandom(4, rng);
        checkEuclideanRandom(10, rng);
        checkEuclideanRandom(100, rng);
    }

    /** Check a number of random vectors of length {@code len} with various exponent
     * ranges.
     * @param len vector array length
     * @param rng random number generator
     * @param fn euclidean norm test function
     */
    private static void checkEuclideanRandom(final int len,
                                             final UniformRandomProvider rng) {
        checkEuclideanRandom(len, +600, +620, rng);
        checkEuclideanRandom(len, LARGE_THRESH_EXP - 10, LARGE_THRESH_EXP + 10, rng);
        checkEuclideanRandom(len, +400, +420, rng);
        checkEuclideanRandom(len, +100, +120, rng);
        checkEuclideanRandom(len, -10, +10, rng);
        checkEuclideanRandom(len, -120, -100, rng);
        checkEuclideanRandom(len, -420, -400, rng);
        checkEuclideanRandom(len, SMALL_THRESH_EXP - 10, SMALL_THRESH_EXP + 10, rng);
        checkEuclideanRandom(len, -620, -600, rng);

        checkEuclideanRandom(len, -600, +600, rng);
    }

    /** Check a number of random vectors of length {@code len} with elements containing
     * exponents in the range {@code [minExp, maxExp]}.
     * @param len vector array length
     * @param minExp min exponent
     * @param maxExp max exponent
     * @param rng random number generator
     * @param fn euclidean norm test function
     */
    private static void checkEuclideanRandom(final int len,
                                             final int minExp,
                                             final int maxExp,
                                             final UniformRandomProvider rng) {
        for (int i = 0; i < RAND_VECTOR_CNT; ++i) {
            // arrange
            final double[] v = DoubleTestUtils.randomArray(len, minExp, maxExp, rng);

            final double exact = exactEuclideanNorm(v);
            final double direct = directEuclideanNorm(v);

            // act
            final double actual = Norm.L2.of(v);

            // assert
            Assertions.assertTrue(Double.isFinite(actual),()-> "Computed norm was not finite;vector= " + Arrays.toString(v)+ ",exact= " + exact + ",direct= " + direct + ",actual= " + actual);

            final int ulpError = Math.abs(DoubleTestUtils.computeUlpDifference(exact, actual));

            Assertions.assertTrue(ulpError <= MAX_ULP_ERR,()-> "Computed norm ulp error exceeds bounds;vector= " + Arrays.toString(v)+ ",exact= " + exact + ",actual= " + actual + ",ulpError= " + ulpError);
        }
    }

    /** Assert that {@code directNorm(v) * 2^scaleExp = fn(v * 2^scaleExp)}.
     * @param v unscaled vector
     * @param scaleExp scale factor exponent
     */
    private static void checkScaledEuclideanNorm(final double[] v,
                                                 final int scaleExp) {

        final double scale = Math.scalb(1d, scaleExp);
        final double[] scaledV = new double[v.length];
        for (int i = 0; i < v.length; ++i) {
            scaledV[i] = v[i] * scale;
        }

        final double norm = directEuclideanNorm(v);
        final double scaledNorm = Norm.L2.of(scaledV);

        Assertions.assertEquals(norm * scale, scaledNorm);
    }

    /** Direct euclidean norm computation.
     * @param v array
     * @return euclidean norm using direct summation.
     */
    private static double directEuclideanNorm(final double[] v) {
        double n = 0;
        for (int i = 0; i < v.length; i++) {
            n += v[i] * v[i];
        }
        return Math.sqrt(n);
    }

    /** Compute the exact double value of the vector norm using BigDecimals
     * with a math context of {@link MathContext#DECIMAL128}.
     * @param v array
     * @return euclidean norm using BigDecimal with MathContext.DECIMAL128
     */
    private static double exactEuclideanNorm(final double[] v) {
        final MathContext ctx = MathContext.DECIMAL128;

        BigDecimal sum = BigDecimal.ZERO;
        for (final double d : v) {
            sum = sum.add(new BigDecimal(d).pow(2), ctx);
        }
        if (sum.equals(BigDecimal.ZERO)) {
            return 0;
        }

        // Java 9+:
        // sum.sqrt(ctx).doubleValue()

        // Require the sum to be in the range of a double for conversion before sqrt().
        // We scale by a power of 2. Rescaling uses the square root of this which is also
        // a power of 2 and can be accumulated for exact rescaling.
        double rescale = 1.0;
        if (sum.compareTo(BD_MIN_NORMAL) < 0) {
            while (sum.compareTo(BD_MIN_NORMAL) < 0) {
                sum = sum.multiply(SCALE2);
                rescale /= SCALE;
            }
        } else if (sum.compareTo(BD_MAX_VALUE) > 0) {
            while (sum.compareTo(BD_MAX_VALUE) > 0) {
                sum = sum.divide(SCALE2);
                rescale *= SCALE;
            }
        }

        return Math.sqrt(sum.doubleValue()) * rescale;
    }

    /** Compute statistics for the ulp error of {@code fn} for the given inputs and
     * array of exact results.
     * @param inputs sample inputs
     * @param exactResults array containing the exact expected results
     * @param fn function to perform the computation
     * @return ulp error statistics
     */
    private static UlpErrorStats computeUlpErrorStats(final double[][] inputs,
                                                      final double[] exactResults,
                                                      ToDoubleFunction<double[]> fn) {

        // compute the ulp errors for each input
        final int[] ulpErrors = new int[inputs.length];
        int sum = 0;
        for (int i = 0; i < inputs.length; ++i) {
            final double exact = exactResults[i];
            final double actual = fn.applyAsDouble(inputs[i]);

            final int error = DoubleTestUtils.computeUlpDifference(exact, actual);
            ulpErrors[i] = error;
            sum += error;
        }

        // compute the mean
        final double mean = sum / (double) ulpErrors.length;

        // compute the std dev
        double diffSumSq = 0d;
        double diff;
        for (int ulpError : ulpErrors) {
            diff = ulpError - mean;
            diffSumSq += diff * diff;
        }

        final double stdDev = Math.sqrt(diffSumSq / (inputs.length - 1));

        return new UlpErrorStats(mean, stdDev);
    }

    /** Class containing ULP error statistics. */
    private static final class UlpErrorStats {

        private final double mean;

        private final double stdDev;

        UlpErrorStats(final double mean, final double stdDev) {
            this.mean = mean;
            this.stdDev = stdDev;
        }

        public double getMean() {
            return mean;
        }

        public double getStdDev() {
            return stdDev;
        }
    }

    @Test
    void testManhattan_array_1_oe() {
        // act/assert
        try {
    Norm.L1.of(new double[0]);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testEuclidean_array_simple_1_oe() {
        // act/assert
        try {
    Norm.L2.of(new double[0]);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testMaximum_array_1_oe() {
        // act/assert
        try {
    Norm.LINF.of(new double[0]);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
