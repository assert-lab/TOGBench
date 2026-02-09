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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Precision.DoubleEquivalence} instances created with
 * {@link Precision#doubleEquivalenceOfEpsilon(double)}.
 */
class EpsilonDoubleEquivalenceTest_OE25Dev {

    /**
     * Increments the given double value {@code count} number of times
     * using {@link Math#nextUp(double)}.
     * @param n
     * @param count
     * @return
     */
    private static double nextUp(final double n, final int count) {
        double result = n;
        for (int i = 0; i < count; ++i) {
            result = Math.nextUp(result);
        }

        return result;
    }

    /**
     * Decrements the given double value {@code count} number of times
     * using {@link Math#nextDown(double)}.
     * @param n
     * @param count
     * @return
     */
    private static double nextDown(final double n, final int count) {
        double result = n;
        for (int i = 0; i < count; ++i) {
            result = Math.nextDown(result);
        }

        return result;
    }

    @Test
    void testInvalidEpsilonValues_1_oe() {
        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> Precision.doubleEquivalenceOfEpsilon(-1d));
    }

    @Test
    void testInvalidEpsilonValues_2_oe() {
        // act/assert
        // removed other assertion

        String msg;

        msg = Assertions.assertThrows(IllegalArgumentException.class, () -> Precision.doubleEquivalenceOfEpsilon(Double.NaN)).getMessage();
    }

    @Test
    void testInvalidEpsilonValues_4_oe() {
        // act/assert
        // removed other assertion

        String msg;

        // removed other assertion
        // removed other assertion

        msg = Assertions.assertThrows(IllegalArgumentException.class, () -> Precision.doubleEquivalenceOfEpsilon(Double.POSITIVE_INFINITY)).getMessage();
    }

    @Test
    void testInvalidEpsilonValues_6_oe() {
        // act/assert
        // removed other assertion

        String msg;

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        msg = Assertions.assertThrows(IllegalArgumentException.class, () -> Precision.doubleEquivalenceOfEpsilon(Double.NEGATIVE_INFINITY)).getMessage();
    }

    @Test
    void testSignum_1_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        Assertions.assertEquals(Double.POSITIVE_INFINITY, 1 / cmp.signum(0.0), 0d);
    }

    @Test
    void testSignum_2_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(Double.NEGATIVE_INFINITY, 1 / cmp.signum(-0.0), 0d);
    }

    @Test
    void testSignum_3_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Double.POSITIVE_INFINITY, 1 / cmp.signum(eps), 0d);
    }

    @Test
    void testSignum_4_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(Double.NEGATIVE_INFINITY, 1 / cmp.signum(-eps), 0d);
    }

    @Test
    void testSignum_5_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, cmp.signum(Math.nextUp(eps)), 0d);
    }

    @Test
    void testSignum_6_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(-1, cmp.signum(Math.nextDown(-eps)), 0d);
    }

    @Test
    void testSignum_7_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(Double.isNaN(cmp.signum(Double.NaN)));
    }

    @Test
    void testSignum_8_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, cmp.signum(Double.POSITIVE_INFINITY), 0d);
    }

    @Test
    void testSignum_9_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-1, cmp.signum(Double.NEGATIVE_INFINITY), 0d);
    }

    @Test
    void testCompare_simple_1_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-10);

        // act/assert
        Assertions.assertEquals(0, cmp.compare(1, 1));
    }

    @Test
    void testCompare_simple_2_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-10);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(-1, cmp.compare(1, 2));
    }

    @Test
    void testCompare_simple_3_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-10);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, cmp.compare(2, 1));
    }

    @Test
    void testCompare_simple_4_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-10);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, cmp.compare(-1, -1));
    }

    @Test
    void testCompare_simple_5_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-10);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, cmp.compare(-1, -2));
    }

    @Test
    void testCompare_simple_6_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-10);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-1, cmp.compare(-2, -1));
    }

    @Test
    void testCompare_compareToZero_1_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        Assertions.assertEquals(0, cmp.compare(0.0, 0.0));
    }

    @Test
    void testCompare_compareToZero_2_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(0, cmp.compare(+0.0, -0.0));
    }

    @Test
    void testCompare_compareToZero_3_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, cmp.compare(eps, -0.0));
    }

    @Test
    void testCompare_compareToZero_4_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, cmp.compare(+0.0, eps));
    }

    @Test
    void testCompare_compareToZero_5_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, cmp.compare(-eps, -0.0));
    }

    @Test
    void testCompare_compareToZero_6_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0, cmp.compare(+0.0, -eps));
    }

    @Test
    void testCompare_compareToZero_7_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-1, cmp.compare(0.0, 1.0));
    }

    @Test
    void testCompare_compareToZero_8_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, cmp.compare(1.0, 0.0));
    }

    @Test
    void testCompare_compareToZero_9_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, cmp.compare(0.0, -1.0));
    }

    @Test
    void testCompare_compareToZero_10_oe() {
        // arrange
        final double eps = 1e-2;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(-1, cmp.compare(-1.0, 0.0));
    }

    @Test
    void testCompare_compareNonZero_1_oe() {
        // arrange
        final double eps = 1e-5;
        final double small = 1e-3;
        final double big = 1e100;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        Assertions.assertEquals(0, cmp.compare(eps, 2 * eps));
    }

    @Test
    void testCompare_compareNonZero_2_oe() {
        // arrange
        final double eps = 1e-5;
        final double small = 1e-3;
        final double big = 1e100;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(0, cmp.compare(-2 * eps, -eps));
    }

    @Test
    void testCompare_compareNonZero_3_oe() {
        // arrange
        final double eps = 1e-5;
        final double small = 1e-3;
        final double big = 1e100;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, cmp.compare(small, small + (0.9 * eps)));
    }

    @Test
    void testCompare_compareNonZero_4_oe() {
        // arrange
        final double eps = 1e-5;
        final double small = 1e-3;
        final double big = 1e100;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0, cmp.compare(-small - (0.9 * eps), -small));
    }

    @Test
    void testCompare_compareNonZero_5_oe() {
        // arrange
        final double eps = 1e-5;
        final double small = 1e-3;
        final double big = 1e100;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, cmp.compare(big, nextUp(big, 1)));
    }

    @Test
    void testCompare_compareNonZero_6_oe() {
        // arrange
        final double eps = 1e-5;
        final double small = 1e-3;
        final double big = 1e100;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0, cmp.compare(nextDown(-big, 1), -big));
    }

    @Test
    void testCompare_compareNonZero_7_oe() {
        // arrange
        final double eps = 1e-5;
        final double small = 1e-3;
        final double big = 1e100;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-1, cmp.compare(small, small + (1.1 * eps)));
    }

    @Test
    void testCompare_compareNonZero_8_oe() {
        // arrange
        final double eps = 1e-5;
        final double small = 1e-3;
        final double big = 1e100;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, cmp.compare(-small, -small - (1.1 * eps)));
    }

    @Test
    void testCompare_compareNonZero_9_oe() {
        // arrange
        final double eps = 1e-5;
        final double small = 1e-3;
        final double big = 1e100;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-1, cmp.compare(big, nextUp(big, 2)));
    }

    @Test
    void testCompare_compareNonZero_10_oe() {
        // arrange
        final double eps = 1e-5;
        final double small = 1e-3;
        final double big = 1e100;

        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, cmp.compare(-big, nextDown(-big, 2)));
    }

    @Test
    void testCompare_NaN_1_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        Assertions.assertEquals(-1, cmp.compare(0, Double.NaN));
    }

    @Test
    void testCompare_NaN_2_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(1, cmp.compare(Double.NaN, 0));
    }

    @Test
    void testCompare_NaN_3_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, cmp.compare(Double.NaN, Double.NaN));
    }

    @Test
    void testCompare_NaN_4_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-1, cmp.compare(Double.POSITIVE_INFINITY, Double.NaN));
    }

    @Test
    void testCompare_NaN_5_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, cmp.compare(Double.NaN, Double.POSITIVE_INFINITY));
    }

    @Test
    void testCompare_NaN_6_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-1, cmp.compare(Double.NEGATIVE_INFINITY, Double.NaN));
    }

    @Test
    void testCompare_NaN_7_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, cmp.compare(Double.NaN, Double.NEGATIVE_INFINITY));
    }

    @Test
    void testCompare_infinity_1_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        Assertions.assertEquals(-1, cmp.compare(0, Double.POSITIVE_INFINITY));
    }

    @Test
    void testCompare_infinity_2_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(1, cmp.compare(Double.POSITIVE_INFINITY, 0));
    }

    @Test
    void testCompare_infinity_3_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, cmp.compare(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY));
    }

    @Test
    void testCompare_infinity_4_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, cmp.compare(0, Double.NEGATIVE_INFINITY));
    }

    @Test
    void testCompare_infinity_5_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(-1, cmp.compare(Double.NEGATIVE_INFINITY, 0));
    }

    @Test
    void testCompare_infinity_6_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, cmp.compare(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY));
    }

    @Test
    void testEq_1_oe() {
        // arrange
        final double eps = Math.ulp(1.0);
        final double twoEps = 2 * eps;
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        Assertions.assertTrue(cmp.eq(0.0, 0.0));
    }

    @Test
    void testEq_2_oe() {
        // arrange
        final double eps = Math.ulp(1.0);
        final double twoEps = 2 * eps;
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion

        Assertions.assertTrue(cmp.eq(1.0, 1.0));
    }

    @Test
    void testEq_3_oe() {
        // arrange
        final double eps = Math.ulp(1.0);
        final double twoEps = 2 * eps;
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(cmp.eq(1.0, 1.0 + eps));
    }

    @Test
    void testEq_4_oe() {
        // arrange
        final double eps = Math.ulp(1.0);
        final double twoEps = 2 * eps;
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(cmp.eq(1.0, 1.0 - eps));
    }

    @Test
    void testEq_5_oe() {
        // arrange
        final double eps = Math.ulp(1.0);
        final double twoEps = 2 * eps;
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(cmp.eq(1.0, 1.0 + twoEps));
    }

    @Test
    void testEq_6_oe() {
        // arrange
        final double eps = Math.ulp(1.0);
        final double twoEps = 2 * eps;
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(cmp.eq(1.0, 1.0 - twoEps));
    }

    @Test
    void testEq_7_oe() {
        // arrange
        final double eps = Math.ulp(1.0);
        final double twoEps = 2 * eps;
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(cmp.eq(-1.0, -1.0));
    }

    @Test
    void testEq_8_oe() {
        // arrange
        final double eps = Math.ulp(1.0);
        final double twoEps = 2 * eps;
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(cmp.eq(-1.0, -1.0 + eps));
    }

    @Test
    void testEq_9_oe() {
        // arrange
        final double eps = Math.ulp(1.0);
        final double twoEps = 2 * eps;
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(cmp.eq(-1.0, -1.0 - eps));
    }

    @Test
    void testEq_10_oe() {
        // arrange
        final double eps = Math.ulp(1.0);
        final double twoEps = 2 * eps;
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(cmp.eq(-1.0, -1.0 + twoEps));
    }

    @Test
    void testEq_11_oe() {
        // arrange
        final double eps = Math.ulp(1.0);
        final double twoEps = 2 * eps;
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(cmp.eq(-1.0, -1.0 - twoEps));
    }

    @Test
    void testEqZero_1_oe() {
        // arrange
        final double eps = 1e-6;
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        Assertions.assertTrue(cmp.eqZero(0.0));
    }

    @Test
    void testEqZero_2_oe() {
        // arrange
        final double eps = 1e-6;
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion

        Assertions.assertFalse(cmp.eqZero(Math.nextUp(eps)));
    }

    @Test
    void testEqZero_3_oe() {
        // arrange
        final double eps = 1e-6;
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(eps);

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(cmp.eqZero(Math.nextDown(-eps)));
    }

    @Test
    void testLt_1_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        Assertions.assertTrue(cmp.lt(1, 2));
    }

    @Test
    void testLt_2_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        Assertions.assertTrue(cmp.lt(-2, -1));
    }

    @Test
    void testLt_3_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(cmp.lt(1, 1));
    }

    @Test
    void testLt_4_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(cmp.lt(-1, -1));
    }

    @Test
    void testLt_5_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(cmp.lt(2, 1));
    }

    @Test
    void testLt_6_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(cmp.lt(-1, -2));
    }

    @Test
    void testLte_1_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        Assertions.assertTrue(cmp.lte(1, 2));
    }

    @Test
    void testLte_2_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        Assertions.assertTrue(cmp.lte(-2, -1));
    }

    @Test
    void testLte_3_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(cmp.lte(1, 1));
    }

    @Test
    void testLte_4_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(cmp.lte(-1, -1));
    }

    @Test
    void testLte_5_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(cmp.lte(2, 1));
    }

    @Test
    void testLte_6_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(cmp.lte(-1, -2));
    }

    @Test
    void testGt_1_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        Assertions.assertTrue(cmp.gt(2, 1));
    }

    @Test
    void testGt_2_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        Assertions.assertTrue(cmp.gt(-1, -2));
    }

    @Test
    void testGt_3_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(cmp.gt(1, 1));
    }

    @Test
    void testGt_4_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(cmp.gt(-1, -1));
    }

    @Test
    void testGt_5_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(cmp.gt(1, 2));
    }

    @Test
    void testGt_6_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(cmp.gt(-2, -1));
    }

    @Test
    void testGte_1_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        Assertions.assertTrue(cmp.gte(2, 1));
    }

    @Test
    void testGte_2_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        Assertions.assertTrue(cmp.gte(-1, -2));
    }

    @Test
    void testGte_3_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(cmp.gte(1, 1));
    }

    @Test
    void testGte_4_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(cmp.gte(-1, -1));
    }

    @Test
    void testGte_5_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(cmp.gte(1, 2));
    }

    @Test
    void testGte_6_oe() {
        // arrange
        final Precision.DoubleEquivalence cmp = Precision.doubleEquivalenceOfEpsilon(1e-6);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(cmp.gte(-2, -1));
    }

}
