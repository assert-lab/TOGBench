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
package org.apache.commons.numbers.examples.jmh.core;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.SplittableRandom;
import java.util.stream.Stream;

import org.apache.commons.numbers.examples.jmh.core.LinearCombination.FourD;
import org.apache.commons.numbers.examples.jmh.core.LinearCombination.ND;
import org.apache.commons.numbers.examples.jmh.core.LinearCombination.ThreeD;
import org.apache.commons.numbers.examples.jmh.core.LinearCombination.TwoD;
import org.apache.commons.numbers.fraction.BigFraction;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test each implementation of the LinearCombination interface.
 */
class LinearCombinationsTest_OE25Dev {
    /** Double.MIN_VALUE as a BigDecimal. Use string constructor to truncate precision to 4.9e-324. */
    private static final BigDecimal MIN = BigDecimal.valueOf(Double.MIN_VALUE);

    /**
     * Provide instances of the LinearCombination interface as arguments.
     *
     * @return the stream
     */
    static Stream<Arguments> provideLinearCombination() {
        return Stream.of(
            Arguments.of(LinearCombinations.Dekker.INSTANCE),
            Arguments.of(LinearCombinations.Dot2s.INSTANCE),
            Arguments.of(LinearCombinations.DotK.DOT_3),
            Arguments.of(LinearCombinations.DotK.DOT_4),
            Arguments.of(LinearCombinations.DotK.DOT_5),
            Arguments.of(LinearCombinations.DotK.DOT_6),
            Arguments.of(LinearCombinations.DotK.DOT_7),
            Arguments.of(LinearCombinations.ExtendedPrecision.INSTANCE),
            Arguments.of(LinearCombinations.ExtendedPrecision.DOUBLE),
            Arguments.of(LinearCombinations.ExtendedPrecision.EXACT),
            Arguments.of(LinearCombinations.ExtendedPrecision.EXACT2),
            Arguments.of(LinearCombinations.Exact.INSTANCE)
        );
    }

    /**
     * This creates a scenario where the split product will overflow but the standard
     * precision computation will not. The result is expected to be in extended precision,
     * i.e. the method correctly detects and handles intermediate overflow.
     *
     * <p>Note: This test assumes that LinearCombination computes a split number
     * using Dekker's method. This can result in the high part of the number being
     * greater in magnitude than the the original number due to round-off in the split.
     */

    /**
     * This is an extreme case of the sum x^2 + y^2 - 1 when x^2 + y^2 are 1.0 within
     * floating-point error but if performed using high precision subtracting 1.0 is not 0.0.
     * This case is derived from computations on a complex cis number.
     */
    @Test
    void testCisNumber() {
        final double theta = 5.992112452678286E-7;
        final double x = Math.cos(theta);
        final double y = Math.sin(theta);
        assertValue(LinearCombinations.DotK.DOT_3.value(x, x, y, y, 1, -1),
                new double[] {x, y, 1},
                new double[] {x, y, -1});
    }

    /**
     * Test the sum of vectors composed of sub-vectors of [a1, a2, a3, a4] * [a1, a2, -a3, -a4]
     * where a1^2 + a2^2 = 1 and a3^2 + a4^2 = 1 such that the sum is approximately 0 every
     * 4 products. This is a test that is failed by various implementations that accumulate the
     * round-off sum in single or 2-fold precision.
     */
    @Test
    void testSumZero() {
        // Fixed seed for stability
        final UniformRandomProvider rng = RandomSource.create(RandomSource.XO_RO_SHI_RO_128_PP, 876543L);
        final int size = 10;
        // Create random doublets of pairs of numbers that sum to 1 or -1.
        for (int length = 4; length <= 12; length += 4) {
            final double[] a = new double[length];
            final double[] b = new double[length];
            for (int i = 0; i < size; i++) {
                // Flip-flop above and below zero
                double sign = 1;
                for (int k = 0; k < length; k += 4) {
                    // Create 2 complex cis numbers
                    final double theta1 = rng.nextDouble() * Math.PI / 2;
                    final double theta2 = rng.nextDouble() * Math.PI / 2;
                    a[k + 0] = b[k + 0] = Math.cos(theta1);
                    a[k + 1] = b[k + 1] = Math.sin(theta1);
                    a[k + 2] = b[k + 2] = Math.cos(theta2);
                    a[k + 3] = b[k + 3] = Math.sin(theta2);
                    a[k + 0] *= sign;
                    a[k + 1] *= sign;
                    a[k + 2] *= sign;
                    a[k + 3] *= sign;
                    // Invert second pair.
                    // The sum of the pairs should be zero +/- floating point error.
                    a[k + 2] = -a[k + 2];
                    a[k + 3] = -a[k + 3];
                    sign = -sign;
                }
                assertValue(LinearCombinations.DotK.DOT_3.value(a, b), a, b);
            }
        }
    }

    /**
     * Compute the sum of the product of factors in arbitrary precision and compare it to the
     * given value.
     *
     * @param value the value
     * @param a factors
     * @param b factors
     */
    private static void assertValue(double value, double[] a, double[] b) {
        final double expected = computeValue(a, b);
        Assertions.assertEquals(expected,value,Math.ulp(expected),()-> "Difference in Ulps = " + ulps(expected,value));
    }

    /**
     * Compute the sum of the product of pairs of input data using BigDecimal.
     * The BigDecimal is not allowed to underflow Double.MIN_VALUE.
     *
     * @param data the data
     * @return the sum of products
     */
    private static double computeValue(double[] a, double[] b) {
        BigDecimal sum = new BigDecimal(a[0]).multiply(new BigDecimal(b[0]));
        for (int i = 1; i < a.length; i++) {
            sum = clip(sum.add(clip(new BigDecimal(a[i]).multiply(new BigDecimal(b[i])))));
        }
        return sum.doubleValue();
    }

    /**
     * Compute the units of least precision (ulps) between the two numbers.
     *
     * @param a first number
     * @param b second number
     * @return the ulps
     */
    private static long ulps(double a, double b) {
        long x = Double.doubleToLongBits(a);
        long y = Double.doubleToLongBits(b);
        if (x != y) {
            if ((x ^ y) < 0L) {
                // Opposite signs. Measure the combined distance to zero.
                if (x < 0) {
                    final long tmp = x;
                    x = y;
                    y = tmp;
                }
                return (x - Double.doubleToLongBits(0.0)) + (y - Double.doubleToLongBits(-0.0)) + 1;
            }
            return Math.abs(x - y);
        }
        return 0;
    }

    /**
     * Clip the value to the minimum value that can be stored by a double.
     * Ideally this should round BigDecimal to values occupied by sub-normal numbers.
     * That is non-trivial so this just removes excess precision in the significand and
     * clips it to Double.MIN_VALUE or zero if the value is very small. The ultimate use for
     * the BigDecimal is rounded to the closest double so this method is adequate. It would
     * take many summations of extended precision sub-normal numbers to create more
     * than a few ULP difference to the final double value
     *
     * <p>In data output by the various tests the values have never been known to require
     * clipping so this is just a safety threshold.
     *
     * @param a the value
     * @return the clipped value
     */
    private static BigDecimal clip(BigDecimal a) {
        // Min value is approx 4.9e-324. Anything with fewer decimal digits to the right of the
        // decimal point is OK.
        if (a.scale() < 324) {
            return a;
        }
        // Reduce the scale
        final BigDecimal b = a.setScale(MIN.scale(), RoundingMode.HALF_UP);
        // Clip to min value
        final BigDecimal bb = b.abs();
        if (bb.compareTo(MIN) < 0) {
            // Note the number may be closer to MIN than zero so do rounding
            if (MIN.subtract(bb).compareTo(bb) < 0) {
                // Closer to MIN
                return a.signum() == -1 ? MIN.negate() : MIN;
            }
            // Closer to zero
            return BigDecimal.ZERO;
        }
        // Anything above min is allowed.
        return b;
    }

    /**
     * Test the clip method does what it specifies.
     */


}
