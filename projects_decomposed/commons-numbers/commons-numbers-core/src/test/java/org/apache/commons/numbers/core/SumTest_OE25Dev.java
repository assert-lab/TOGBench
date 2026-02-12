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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SumTest_OE25Dev {

    @Test
    void testSumAccuracy() {
        // arrange
        final double a = 9.999999999;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);
        final double e = Math.scalb(a, -27);
        final double f = Math.scalb(a, -50);

        // act/assert
        assertSumExact(a);

        assertSumExact(a, b);
        assertSumExact(b, a);

        assertSumExact(a, b, c);
        assertSumExact(c, b, a);

        assertSumExact(a, b, c, d);
        assertSumExact(d, c, b, a);

        assertSumExact(a, -b, c, -d);
        assertSumExact(d, -c, b, -a);

        assertSumExact(a, b, c, d, e, f);
        assertSumExact(f, e, d, c, b, a);

        assertSumExact(a, -b, c, -d, e, f);
        assertSumExact(f, -e, d, -c, b, -a);
    }

    @Test
    void testSumOfProducts_nonFinite() {
        // arrange
        final double[][] a = new double[][] {
            {1, 2, 3, 4},
            {1, Double.POSITIVE_INFINITY, 3, 4},
            {1, 2, Double.POSITIVE_INFINITY, 4},
            {1, Double.POSITIVE_INFINITY, 3, Double.NEGATIVE_INFINITY},
            {1, 2, 3, 4},
            {1, 2, 3, 4},
            {1, 2, 3, 4},
            {1, 2, 3, 4},
            {1, Double.MAX_VALUE, 3, 4},
            {1, 2, Double.MAX_VALUE, 4},
            {1, Double.MAX_VALUE / 2, 3, -Double.MAX_VALUE / 4},
        };
        final double[][] b = new double[][] {
            {1, -2, 3, 4},
            {1, -2, 3, 4},
            {1, -2, 3, 4},
            {1, -2, 3, 4},
            {1, Double.POSITIVE_INFINITY, 3, 4},
            {1, -2, Double.POSITIVE_INFINITY, 4},
            {1, Double.POSITIVE_INFINITY, 3, Double.NEGATIVE_INFINITY},
            {Double.NaN, -2, 3, 4},
            {1, -2, 3, 4},
            {1, -2, 3, 4},
            {1, -2, 3, 4},
        };

        // act/assert
        assertSumOfProducts(-3,
                a[0][0], b[0][0],
                a[0][1], b[0][1]);
        assertSumOfProducts(6,
                a[0][0], b[0][0],
                a[0][1], b[0][1],
                a[0][2], b[0][2]);
        assertSumOfProducts(22, a[0], b[0]);

        assertSumOfProducts(Double.NEGATIVE_INFINITY,
                a[1][0], b[1][0],
                a[1][1], b[1][1]);
        assertSumOfProducts(Double.NEGATIVE_INFINITY,
                a[1][0], b[1][0],
                a[1][1], b[1][1],
                a[1][2], b[1][2]);
        assertSumOfProducts(Double.NEGATIVE_INFINITY, a[1], b[1]);

        assertSumOfProducts(-3,
                a[2][0], b[2][0],
                a[2][1], b[2][1]);
        assertSumOfProducts(Double.POSITIVE_INFINITY,
                a[2][0], b[2][0],
                a[2][1], b[2][1],
                a[2][2], b[2][2]);
        assertSumOfProducts(Double.POSITIVE_INFINITY, a[2], b[2]);

        assertSumOfProducts(Double.NEGATIVE_INFINITY,
                a[3][0], b[3][0],
                a[3][1], b[3][1]);
        assertSumOfProducts(Double.NEGATIVE_INFINITY,
                a[3][0], b[3][0],
                a[3][1], b[3][1],
                a[3][2], b[3][2]);
        assertSumOfProducts(Double.NEGATIVE_INFINITY, a[3], b[3]);

        assertSumOfProducts(Double.POSITIVE_INFINITY,
                a[4][0], b[4][0],
                a[4][1], b[4][1]);
        assertSumOfProducts(Double.POSITIVE_INFINITY,
                a[4][0], b[4][0],
                a[4][1], b[4][1],
                a[4][2], b[4][2]);
        assertSumOfProducts(Double.POSITIVE_INFINITY, a[4], b[4]);

        assertSumOfProducts(-3,
                a[5][0], b[5][0],
                a[5][1], b[5][1]);
        assertSumOfProducts(Double.POSITIVE_INFINITY,
                a[5][0], b[5][0],
                a[5][1], b[5][1],
                a[5][2], b[5][2]);
        assertSumOfProducts(Double.POSITIVE_INFINITY, a[5], b[5]);

        assertSumOfProducts(Double.POSITIVE_INFINITY,
                a[6][0], b[6][0],
                a[6][1], b[6][1]);
        assertSumOfProducts(Double.POSITIVE_INFINITY,
                a[6][0], b[6][0],
                a[6][1], b[6][1],
                a[6][2], b[6][2]);
        assertSumOfProducts(Double.NaN, a[6], b[6]);

        assertSumOfProducts(Double.NaN,
                a[7][0], b[7][0],
                a[7][1], b[7][1]);
        assertSumOfProducts(Double.NaN,
                a[7][0], b[7][0],
                a[7][1], b[7][1],
                a[7][2], b[7][2]);
        assertSumOfProducts(Double.NaN, a[7], b[7]);

        assertSumOfProducts(Double.NEGATIVE_INFINITY,
                a[8][0], b[8][0],
                a[8][1], b[8][1]);
        assertSumOfProducts(Double.NEGATIVE_INFINITY,
                a[8][0], b[8][0],
                a[8][1], b[8][1],
                a[8][2], b[8][2]);
        assertSumOfProducts(Double.NEGATIVE_INFINITY, a[8], b[8]);

        assertSumOfProducts(-3,
                a[9][0], b[9][0],
                a[9][1], b[9][1]);
        assertSumOfProducts(Double.POSITIVE_INFINITY,
                a[9][0], b[9][0],
                a[9][1], b[9][1],
                a[9][2], b[9][2]);
        assertSumOfProducts(Double.POSITIVE_INFINITY, a[9], b[9]);

        assertSumOfProducts(-Double.MAX_VALUE,
                a[10][0], b[10][0],
                a[10][1], b[10][1]);
        assertSumOfProducts(-Double.MAX_VALUE,
                a[10][0], b[10][0],
                a[10][1], b[10][1],
                a[10][2], b[10][2]);
        assertSumOfProducts(Double.NEGATIVE_INFINITY, a[10], b[10]);
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

    private static void assertSumExact(final double... values) {
        final double exact = exactSum(values);
        assertSum(exact, values);
    }

    private static void assertSum(final double expected, final double... values) {
        // check non-array method variants
        final int len = values.length;
        if (len == 1) {
            Assertions.assertEquals(expected, Sum.of(values[0]).getAsDouble());
        } else if (len == 2) {
            Assertions.assertEquals(expected, Sum.of(values[0], values[1]).getAsDouble());
        } else if (len == 3) {
            Assertions.assertEquals(expected, Sum.of(values[0], values[1], values[2]).getAsDouble());
        } else if (len == 4) {
            Assertions.assertEquals(expected, Sum.of(values[0], values[1], values[2], values[3]).getAsDouble());
        }

        // check use with add()
        final Sum addAccumulator = Sum.create();
        for (int i = 0; i < len; ++i) {
            addAccumulator.add(values[i]);
        }
        Assertions.assertEquals(expected, addAccumulator.getAsDouble());

        // check with accept()
        final Sum acceptAccumulator = Sum.create();
        for (int i = 0; i < len; ++i) {
            acceptAccumulator.accept(values[i]);
        }
        Assertions.assertEquals(expected, acceptAccumulator.getAsDouble());

        // check using stream
        final Sum streamAccumulator = Sum.create();
        Arrays.stream(values).forEach(streamAccumulator);
        Assertions.assertEquals(expected, streamAccumulator.getAsDouble());

        // check array instance method
        Assertions.assertEquals(expected, Sum.create().add(values).getAsDouble());

        // check array factory method
        Assertions.assertEquals(expected, Sum.of(values).getAsDouble());
    }

    private static void assertSumOfProducts(final double expected, final double... args) {
        final int halfLen = args.length / 2;

        final double[] a = new double[halfLen];
        final double[] b = new double[halfLen];
        for (int i = 0; i < halfLen; ++i) {
            a[i] = args[2 * i];
            b[i] = args[(2 * i) + 1];
        }

        assertSumOfProducts(expected, a, b);
    }

    private static void assertSumOfProducts(final double expected, final double[] a, final double[] b) {
        final int len = a.length;

        // check use of addProduct()
        final Sum accumulator = Sum.create();
        for (int i = 0; i < len; ++i) {
            accumulator.addProduct(a[i], b[i]);
        }
        Assertions.assertEquals(expected, accumulator.getAsDouble());

        // check use of array instance method
        Assertions.assertEquals(expected, Sum.create().addProducts(a, b).getAsDouble());

        // check use of array factory method
        Assertions.assertEquals(expected, Sum.ofProducts(a, b).getAsDouble());
    }

    /** Return the double estimation of the exact summation result computed with unlimited precision.
     * @param values values to add
     * @return double value closest to the exact result
     */
    private static double exactSum(final double... values) {
        BigDecimal sum = BigDecimal.ZERO;
        for (double value : values) {
            sum = sum.add(new BigDecimal(value), MathContext.UNLIMITED);
        }

        return sum.doubleValue();
    }

    /** Return the double estimation of the exact linear combination result. Factors are
     * listed sequentially in the argument array, e.g., {@code a1, b1, a2, b2, ...}.
     * @param values linear combination input
     * @return double value closest to the exact result
     */
    private static double exactLinearCombination(final double... values) {
        final int halfLen = values.length / 2;

        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0; i < halfLen; ++i) {
            final BigDecimal a = new BigDecimal(values[2 * i]);
            final BigDecimal b = new BigDecimal(values[(2 * i) + 1]);

            sum = sum.add(a.multiply(b, MathContext.UNLIMITED));
        }

        return sum.doubleValue();
    }

    @Test
    void testSumOfProducts_dimensionMismatch_1_oe() {
        // act/assert
        final Sum sum = Sum.create();
        try {
    sum.addProducts(new double[1], new double[2]);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testSumOfProducts_dimensionMismatch_2_oe() {
        // act/assert
        final Sum sum = Sum.create();
        // removed other assertion

        try {
    Sum.ofProducts(new double[1], new double[2]);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
