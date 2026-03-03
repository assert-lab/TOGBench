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

import static org.junit.jupiter.api.Assertions.fail;

class SumTest_OE25Dev {

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
    void testSum_simple_1_oe() {
        Assertions.assertEquals(0d, Sum.create().getAsDouble());
    }

    @Test
    void testAdd_sumInstance_1_oe() {
        final double a = Math.PI;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);
        final double e = Math.scalb(a, -27);
        final double f = Math.scalb(a, -50);

        Assertions.assertEquals(exactSum(a, b, c, d), Sum.of(a, b, c, d).add(Sum.create()).getAsDouble());
    }

    @Test
    void testAdd_sumInstance_2_oe() {
        final double a = Math.PI;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);
        final double e = Math.scalb(a, -27);
        final double f = Math.scalb(a, -50);

        Assertions.assertEquals(exactSum(a,a,b,c,d,e,f),Sum.of(a,b).add(Sum.of(a,c)).add(Sum.of(d,e,f)).getAsDouble());
    }

    @Test
    void testAdd_sumInstance_3_oe() {
        final double a = Math.PI;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);
        final double e = Math.scalb(a, -27);
        final double f = Math.scalb(a, -50);


        final Sum s = Sum.of(a, b);
        Assertions.assertEquals(exactSum(a, b, a, b), s.add(s).getAsDouble());
    }

    @Test
    void testSumOfProducts_dimensionMismatch_1_oe() {
        final Sum sum = Sum.create();
        try {
    sum.addProducts(new double[1], new double[2]);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testSumOfProducts_dimensionMismatch_2_oe() {
        final Sum sum = Sum.create();

        try {
    Sum.ofProducts(new double[1], new double[2]);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testSumOfProducts_singleElement_1_oe() {
        final double[] a = {1.23456789};
        final double[] b = {98765432.1};

        Assertions.assertEquals(a[0] * b[0], Sum.ofProducts(a, b).getAsDouble());
    }

    @Test
    void testSumOfProducts_1_oe() {
        final BigDecimal[] aFN = new BigDecimal[] {
            BigDecimal.valueOf(-1321008684645961L),
            BigDecimal.valueOf(-5774608829631843L),
            BigDecimal.valueOf(-7645843051051357L),
        };
        final BigDecimal[] aFD = new BigDecimal[] {
            BigDecimal.valueOf(268435456L),
            BigDecimal.valueOf(268435456L),
            BigDecimal.valueOf(8589934592L)
        };
        final BigDecimal[] bFN = new BigDecimal[] {
            BigDecimal.valueOf(-5712344449280879L),
            BigDecimal.valueOf(-4550117129121957L),
            BigDecimal.valueOf(8846951984510141L)
        };
        final BigDecimal[] bFD = new BigDecimal[] {
            BigDecimal.valueOf(2097152L),
            BigDecimal.valueOf(2097152L),
            BigDecimal.valueOf(131072L)
        };

        final int len = aFN.length;
        final double[] a = new double[len];
        final double[] b = new double[len];
        for (int i = 0; i < len; i++) {
            a[i] = aFN[i].doubleValue() / aFD[i].doubleValue();
            b[i] = bFN[i].doubleValue() / bFD[i].doubleValue();
        }

        final double sum = Sum.ofProducts(a, b).getAsDouble();

        BigDecimal result = BigDecimal.ZERO;
        for (int i = 0; i < a.length; i++) {
            result = result.add(aFN[i].divide(aFD[i]).multiply(bFN[i].divide(bFD[i])));
        }
        final double expected = result.doubleValue();
        Assertions.assertEquals(expected, sum, 1e-15);
    }

    @Test
    void testSumOfProducts_2_oe() {
        final BigDecimal[] aFN = new BigDecimal[] {
            BigDecimal.valueOf(-1321008684645961L),
            BigDecimal.valueOf(-5774608829631843L),
            BigDecimal.valueOf(-7645843051051357L),
        };
        final BigDecimal[] aFD = new BigDecimal[] {
            BigDecimal.valueOf(268435456L),
            BigDecimal.valueOf(268435456L),
            BigDecimal.valueOf(8589934592L)
        };
        final BigDecimal[] bFN = new BigDecimal[] {
            BigDecimal.valueOf(-5712344449280879L),
            BigDecimal.valueOf(-4550117129121957L),
            BigDecimal.valueOf(8846951984510141L)
        };
        final BigDecimal[] bFD = new BigDecimal[] {
            BigDecimal.valueOf(2097152L),
            BigDecimal.valueOf(2097152L),
            BigDecimal.valueOf(131072L)
        };

        final int len = aFN.length;
        final double[] a = new double[len];
        final double[] b = new double[len];
        for (int i = 0; i < len; i++) {
            a[i] = aFN[i].doubleValue() / aFD[i].doubleValue();
            b[i] = bFN[i].doubleValue() / bFD[i].doubleValue();
        }

        final double sum = Sum.ofProducts(a, b).getAsDouble();

        BigDecimal result = BigDecimal.ZERO;
        for (int i = 0; i < a.length; i++) {
            result = result.add(aFN[i].divide(aFD[i]).multiply(bFN[i].divide(bFD[i])));
        }
        final double expected = result.doubleValue();

        final double naive = a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
        Assertions.assertTrue(Math.abs(naive - sum) > 1.5);
    }

    @Test
    void testSumOfProducts_huge_1_oe() {
        int scale = 971;
        final double[] a = new double[] {
            -1321008684645961.0 / 268435456.0,
            -5774608829631843.0 / 268435456.0,
            -7645843051051357.0 / 8589934592.0
        };
        final double[] b = new double[] {
            -5712344449280879.0 / 2097152.0,
            -4550117129121957.0 / 2097152.0,
            8846951984510141.0 / 131072.0
        };

        final int len = a.length;
        final double[] scaledA = new double[len];
        final double[] scaledB = new double[len];
        for (int i = 0; i < len; ++i) {
            scaledA[i] = Math.scalb(a[i], -scale);
            scaledB[i] = Math.scalb(b[i], scale);
        }

        final double sum = Sum.ofProducts(scaledA, scaledB).getAsDouble();

        Assertions.assertEquals(-1.8551294182586248737720779899, sum, 1e-15);
    }

    @Test
    void testSumOfProducts_huge_2_oe() {
        int scale = 971;
        final double[] a = new double[] {
            -1321008684645961.0 / 268435456.0,
            -5774608829631843.0 / 268435456.0,
            -7645843051051357.0 / 8589934592.0
        };
        final double[] b = new double[] {
            -5712344449280879.0 / 2097152.0,
            -4550117129121957.0 / 2097152.0,
            8846951984510141.0 / 131072.0
        };

        final int len = a.length;
        final double[] scaledA = new double[len];
        final double[] scaledB = new double[len];
        for (int i = 0; i < len; ++i) {
            scaledA[i] = Math.scalb(a[i], -scale);
            scaledB[i] = Math.scalb(b[i], scale);
        }

        final double sum = Sum.ofProducts(scaledA, scaledB).getAsDouble();


        final double naive = scaledA[0] * scaledB[0] + scaledA[1] * scaledB[1] + scaledA[2] * scaledB[2];
        Assertions.assertTrue(Math.abs(naive - sum) > 1.5);
    }

    @Test
    void testSumOfProducts_overflow_1_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();
        Assertions.assertNotEquals(xxMxy, xxMxyHighPrecision, "High precision result should be different");
    }

    @Test
    void testSumOfProducts_overflow_2_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();

        Assertions.assertEquals(0, Math.getExponent(x));
    }

    @Test
    void testSumOfProducts_overflow_3_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();

        Assertions.assertEquals(0, Math.getExponent(y));
    }

    @Test
    void testSumOfProducts_overflow_4_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();


        final double a1 = Math.scalb(x, 1022 - 30);
        final double b1 = Math.scalb(x, 30);
        final double a2 = a1;
        final double b2 = Math.scalb(y, 30);
        final double sxxMxy = Math.scalb(xxMxy, 1022);
        Assertions.assertEquals(sxxMxy, a1 * b1 + a2 * b2);
    }

    @Test
    void testSumOfProducts_overflow_5_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();


        final double a1 = Math.scalb(x, 1022 - 30);
        final double b1 = Math.scalb(x, 30);
        final double a2 = a1;
        final double b2 = Math.scalb(y, 30);
        final double sxxMxy = Math.scalb(xxMxy, 1022);
        Assertions.assertTrue(Double.isFinite(sxxMxy));
    }

    @Test
    void testSumOfProducts_overflow_6_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();


        final double a1 = Math.scalb(x, 1022 - 30);
        final double b1 = Math.scalb(x, 30);
        final double a2 = a1;
        final double b2 = Math.scalb(y, 30);
        final double sxxMxy = Math.scalb(xxMxy, 1022);

        final double m = (1 << 27) + 1;
        double c;
        c = a1 * m;
        final double ha1 = c - (c - a1);
        c = b1 * m;
        final double hb1 = c - (c - b1);
        c = a2 * m;
        final double ha2 = c - (c - a2);
        c = b2 * m;
        final double hb2 = c - (c - b2);
        Assertions.assertTrue(Double.isFinite(ha1));
    }

    @Test
    void testSumOfProducts_overflow_7_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();


        final double a1 = Math.scalb(x, 1022 - 30);
        final double b1 = Math.scalb(x, 30);
        final double a2 = a1;
        final double b2 = Math.scalb(y, 30);
        final double sxxMxy = Math.scalb(xxMxy, 1022);

        final double m = (1 << 27) + 1;
        double c;
        c = a1 * m;
        final double ha1 = c - (c - a1);
        c = b1 * m;
        final double hb1 = c - (c - b1);
        c = a2 * m;
        final double ha2 = c - (c - a2);
        c = b2 * m;
        final double hb2 = c - (c - b2);
        Assertions.assertTrue(Double.isFinite(hb1));
    }

    @Test
    void testSumOfProducts_overflow_8_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();


        final double a1 = Math.scalb(x, 1022 - 30);
        final double b1 = Math.scalb(x, 30);
        final double a2 = a1;
        final double b2 = Math.scalb(y, 30);
        final double sxxMxy = Math.scalb(xxMxy, 1022);

        final double m = (1 << 27) + 1;
        double c;
        c = a1 * m;
        final double ha1 = c - (c - a1);
        c = b1 * m;
        final double hb1 = c - (c - b1);
        c = a2 * m;
        final double ha2 = c - (c - a2);
        c = b2 * m;
        final double hb2 = c - (c - b2);
        Assertions.assertTrue(Double.isFinite(ha2));
    }

    @Test
    void testSumOfProducts_overflow_9_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();


        final double a1 = Math.scalb(x, 1022 - 30);
        final double b1 = Math.scalb(x, 30);
        final double a2 = a1;
        final double b2 = Math.scalb(y, 30);
        final double sxxMxy = Math.scalb(xxMxy, 1022);

        final double m = (1 << 27) + 1;
        double c;
        c = a1 * m;
        final double ha1 = c - (c - a1);
        c = b1 * m;
        final double hb1 = c - (c - b1);
        c = a2 * m;
        final double ha2 = c - (c - a2);
        c = b2 * m;
        final double hb2 = c - (c - b2);
        Assertions.assertTrue(Double.isFinite(hb2));
    }

    @Test
    void testSumOfProducts_overflow_10_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();


        final double a1 = Math.scalb(x, 1022 - 30);
        final double b1 = Math.scalb(x, 30);
        final double a2 = a1;
        final double b2 = Math.scalb(y, 30);
        final double sxxMxy = Math.scalb(xxMxy, 1022);

        final double m = (1 << 27) + 1;
        double c;
        c = a1 * m;
        final double ha1 = c - (c - a1);
        c = b1 * m;
        final double hb1 = c - (c - b1);
        c = a2 * m;
        final double ha2 = c - (c - a2);
        c = b2 * m;
        final double hb2 = c - (c - b2);
        Assertions.assertTrue(Math.abs(ha1) > Math.abs(a1));
    }

    @Test
    void testSumOfProducts_overflow_11_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();


        final double a1 = Math.scalb(x, 1022 - 30);
        final double b1 = Math.scalb(x, 30);
        final double a2 = a1;
        final double b2 = Math.scalb(y, 30);
        final double sxxMxy = Math.scalb(xxMxy, 1022);

        final double m = (1 << 27) + 1;
        double c;
        c = a1 * m;
        final double ha1 = c - (c - a1);
        c = b1 * m;
        final double hb1 = c - (c - b1);
        c = a2 * m;
        final double ha2 = c - (c - a2);
        c = b2 * m;
        final double hb2 = c - (c - b2);
        Assertions.assertTrue(Math.abs(hb1) > Math.abs(b1));
    }

    @Test
    void testSumOfProducts_overflow_12_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();


        final double a1 = Math.scalb(x, 1022 - 30);
        final double b1 = Math.scalb(x, 30);
        final double a2 = a1;
        final double b2 = Math.scalb(y, 30);
        final double sxxMxy = Math.scalb(xxMxy, 1022);

        final double m = (1 << 27) + 1;
        double c;
        c = a1 * m;
        final double ha1 = c - (c - a1);
        c = b1 * m;
        final double hb1 = c - (c - b1);
        c = a2 * m;
        final double ha2 = c - (c - a2);
        c = b2 * m;
        final double hb2 = c - (c - b2);
        Assertions.assertTrue(Math.abs(ha2) > Math.abs(a2));
    }

    @Test
    void testSumOfProducts_overflow_13_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();


        final double a1 = Math.scalb(x, 1022 - 30);
        final double b1 = Math.scalb(x, 30);
        final double a2 = a1;
        final double b2 = Math.scalb(y, 30);
        final double sxxMxy = Math.scalb(xxMxy, 1022);

        final double m = (1 << 27) + 1;
        double c;
        c = a1 * m;
        final double ha1 = c - (c - a1);
        c = b1 * m;
        final double hb1 = c - (c - b1);
        c = a2 * m;
        final double ha2 = c - (c - a2);
        c = b2 * m;
        final double hb2 = c - (c - b2);
        Assertions.assertTrue(Math.abs(hb2) > Math.abs(b2));
    }

    @Test
    void testSumOfProducts_overflow_14_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();


        final double a1 = Math.scalb(x, 1022 - 30);
        final double b1 = Math.scalb(x, 30);
        final double a2 = a1;
        final double b2 = Math.scalb(y, 30);
        final double sxxMxy = Math.scalb(xxMxy, 1022);

        final double m = (1 << 27) + 1;
        double c;
        c = a1 * m;
        final double ha1 = c - (c - a1);
        c = b1 * m;
        final double hb1 = c - (c - b1);
        c = a2 * m;
        final double ha2 = c - (c - a2);
        c = b2 * m;
        final double hb2 = c - (c - b2);
        Assertions.assertEquals(Double.POSITIVE_INFINITY, ha1 * hb1, "Expected split high part to overflow");
    }

    @Test
    void testSumOfProducts_overflow_15_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();


        final double a1 = Math.scalb(x, 1022 - 30);
        final double b1 = Math.scalb(x, 30);
        final double a2 = a1;
        final double b2 = Math.scalb(y, 30);
        final double sxxMxy = Math.scalb(xxMxy, 1022);

        final double m = (1 << 27) + 1;
        double c;
        c = a1 * m;
        final double ha1 = c - (c - a1);
        c = b1 * m;
        final double hb1 = c - (c - b1);
        c = a2 * m;
        final double ha2 = c - (c - a2);
        c = b2 * m;
        final double hb2 = c - (c - b2);
        Assertions.assertEquals(Double.NEGATIVE_INFINITY, ha2 * hb2, "Expected split high part to overflow");
    }

    @Test
    void testMixedSingleTermAndProduct_1_oe() {
        final double a = 9.999999999;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);

        Assertions.assertEquals(exactLinearCombination(1,a,-1,b,2,c,4,d),Sum.create().add(a).add(-b).addProduct(2,c).addProduct(d,4).getAsDouble());
    }

    @Test
    void testMixedSingleTermAndProduct_2_oe() {
        final double a = 9.999999999;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);


        Assertions.assertEquals(exactLinearCombination(1,a,-1,b,2,c,4,d),Sum.create().addProduct(d,4).add(a).addProduct(2,c).add(-b).getAsDouble());
    }

    @Test
    void testUnityValuesInProduct_1_oe() {
        final double a = 9.999999999;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);

        Assertions.assertEquals(exactLinearCombination(1,a,-1,b,2,c,4,d),Sum.create().addProduct(1,a).addProduct(-1,b).addProduct(2,c).addProduct(d,4).getAsDouble());
    }

    @Test
    void testUnityValuesInProduct_2_oe() {
        final double a = 9.999999999;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);


        Assertions.assertEquals(exactLinearCombination(1,a,-1,b,2,c,4,d),Sum.create().addProduct(a,1).addProduct(b,-1).addProduct(2,c).addProduct(d,4).getAsDouble());
    }

@Test
    void testSum_simple_3_oe() {

        assertSum(Math.PI + Math.E, Math.PI, Math.E);
    }

@Test
    void testSum_simple_4_oe() {


        assertSum(0, 0, 0, 0);
    }

@Test
    void testSum_simple_5_oe() {


        assertSum(6, 1, 2, 3);
    }

@Test
    void testSum_simple_6_oe() {


        assertSum(2, 1, -2, 3);
    }

@Test
    void testSum_simple_7_oe() {



        assertSum(Double.NaN, Double.NaN, 0, 0);
    }

@Test
    void testSum_simple_8_oe() {



        assertSum(Double.NaN, 0, Double.NaN, 0);
    }

@Test
    void testSum_simple_9_oe() {



        assertSum(Double.NaN, 0, 0, Double.NaN);
    }

@Test
    void testSum_simple_10_oe() {




        assertSum(Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, 0);
    }

@Test
    void testSum_simple_11_oe() {





        assertSum(Double.POSITIVE_INFINITY, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
    }

@Test
    void testSum_simple_12_oe() {






        assertSum(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 1, 1);
    }

@Test
    void testSum_simple_13_oe() {






        assertSum(Double.NEGATIVE_INFINITY, 1, Double.NEGATIVE_INFINITY, 1);
    }

@Test
    void testSumAccuracy_2_oe() {
        final double a = 9.999999999;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);
        final double e = Math.scalb(a, -27);
        final double f = Math.scalb(a, -50);


        assertSumExact(a, b);
    }

@Test
    void testSumAccuracy_3_oe() {
        final double a = 9.999999999;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);
        final double e = Math.scalb(a, -27);
        final double f = Math.scalb(a, -50);


        assertSumExact(b, a);
    }

@Test
    void testSumAccuracy_4_oe() {
        final double a = 9.999999999;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);
        final double e = Math.scalb(a, -27);
        final double f = Math.scalb(a, -50);



        assertSumExact(a, b, c);
    }

@Test
    void testSumAccuracy_5_oe() {
        final double a = 9.999999999;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);
        final double e = Math.scalb(a, -27);
        final double f = Math.scalb(a, -50);



        assertSumExact(c, b, a);
    }

@Test
    void testSumAccuracy_6_oe() {
        final double a = 9.999999999;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);
        final double e = Math.scalb(a, -27);
        final double f = Math.scalb(a, -50);




        assertSumExact(a, b, c, d);
    }

@Test
    void testSumAccuracy_7_oe() {
        final double a = 9.999999999;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);
        final double e = Math.scalb(a, -27);
        final double f = Math.scalb(a, -50);




        assertSumExact(d, c, b, a);
    }

@Test
    void testSumAccuracy_8_oe() {
        final double a = 9.999999999;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);
        final double e = Math.scalb(a, -27);
        final double f = Math.scalb(a, -50);





        assertSumExact(a, -b, c, -d);
    }

@Test
    void testSumAccuracy_9_oe() {
        final double a = 9.999999999;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);
        final double e = Math.scalb(a, -27);
        final double f = Math.scalb(a, -50);





        assertSumExact(d, -c, b, -a);
    }

@Test
    void testSumAccuracy_10_oe() {
        final double a = 9.999999999;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);
        final double e = Math.scalb(a, -27);
        final double f = Math.scalb(a, -50);






        assertSumExact(a, b, c, d, e, f);
    }

@Test
    void testSumAccuracy_11_oe() {
        final double a = 9.999999999;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);
        final double e = Math.scalb(a, -27);
        final double f = Math.scalb(a, -50);






        assertSumExact(f, e, d, c, b, a);
    }

@Test
    void testSumAccuracy_12_oe() {
        final double a = 9.999999999;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);
        final double e = Math.scalb(a, -27);
        final double f = Math.scalb(a, -50);







        assertSumExact(a, -b, c, -d, e, f);
    }

@Test
    void testSumAccuracy_13_oe() {
        final double a = 9.999999999;
        final double b = Math.scalb(a, -53);
        final double c = Math.scalb(a, -53);
        final double d = Math.scalb(a, -27);
        final double e = Math.scalb(a, -27);
        final double f = Math.scalb(a, -50);







        assertSumExact(f, -e, d, -c, b, -a);
    }

@Test
    void testSumOfProducts_nonFinite_1_oe() {
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

        assertSumOfProducts(-3, a[0][0], b[0][0], a[0][1], b[0][1]);
    }

@Test
    void testSumOfProducts_nonFinite_2_oe() {
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

        assertSumOfProducts(6, a[0][0], b[0][0], a[0][1], b[0][1], a[0][2], b[0][2]);
    }

@Test
    void testSumOfProducts_nonFinite_3_oe() {
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

        assertSumOfProducts(22, a[0], b[0]);
    }

@Test
    void testSumOfProducts_nonFinite_4_oe() {
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


        assertSumOfProducts(Double.NEGATIVE_INFINITY, a[1][0], b[1][0], a[1][1], b[1][1]);
    }

@Test
    void testSumOfProducts_nonFinite_5_oe() {
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


        assertSumOfProducts(Double.NEGATIVE_INFINITY, a[1][0], b[1][0], a[1][1], b[1][1], a[1][2], b[1][2]);
    }

@Test
    void testSumOfProducts_nonFinite_6_oe() {
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


        assertSumOfProducts(Double.NEGATIVE_INFINITY, a[1], b[1]);
    }

@Test
    void testSumOfProducts_nonFinite_7_oe() {
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



        assertSumOfProducts(-3, a[2][0], b[2][0], a[2][1], b[2][1]);
    }

@Test
    void testSumOfProducts_nonFinite_8_oe() {
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



        assertSumOfProducts(Double.POSITIVE_INFINITY, a[2][0], b[2][0], a[2][1], b[2][1], a[2][2], b[2][2]);
    }

@Test
    void testSumOfProducts_nonFinite_9_oe() {
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



        assertSumOfProducts(Double.POSITIVE_INFINITY, a[2], b[2]);
    }

@Test
    void testSumOfProducts_nonFinite_10_oe() {
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




        assertSumOfProducts(Double.NEGATIVE_INFINITY, a[3][0], b[3][0], a[3][1], b[3][1]);
    }

@Test
    void testSumOfProducts_nonFinite_11_oe() {
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




        assertSumOfProducts(Double.NEGATIVE_INFINITY, a[3][0], b[3][0], a[3][1], b[3][1], a[3][2], b[3][2]);
    }

@Test
    void testSumOfProducts_nonFinite_12_oe() {
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




        assertSumOfProducts(Double.NEGATIVE_INFINITY, a[3], b[3]);
    }

@Test
    void testSumOfProducts_nonFinite_13_oe() {
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





        assertSumOfProducts(Double.POSITIVE_INFINITY, a[4][0], b[4][0], a[4][1], b[4][1]);
    }

@Test
    void testSumOfProducts_nonFinite_14_oe() {
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





        assertSumOfProducts(Double.POSITIVE_INFINITY, a[4][0], b[4][0], a[4][1], b[4][1], a[4][2], b[4][2]);
    }

@Test
    void testSumOfProducts_nonFinite_15_oe() {
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





        assertSumOfProducts(Double.POSITIVE_INFINITY, a[4], b[4]);
    }

@Test
    void testSumOfProducts_nonFinite_16_oe() {
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






        assertSumOfProducts(-3, a[5][0], b[5][0], a[5][1], b[5][1]);
    }

@Test
    void testSumOfProducts_nonFinite_17_oe() {
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






        assertSumOfProducts(Double.POSITIVE_INFINITY, a[5][0], b[5][0], a[5][1], b[5][1], a[5][2], b[5][2]);
    }

@Test
    void testSumOfProducts_nonFinite_18_oe() {
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






        assertSumOfProducts(Double.POSITIVE_INFINITY, a[5], b[5]);
    }

@Test
    void testSumOfProducts_nonFinite_19_oe() {
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







        assertSumOfProducts(Double.POSITIVE_INFINITY, a[6][0], b[6][0], a[6][1], b[6][1]);
    }

@Test
    void testSumOfProducts_nonFinite_20_oe() {
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







        assertSumOfProducts(Double.POSITIVE_INFINITY, a[6][0], b[6][0], a[6][1], b[6][1], a[6][2], b[6][2]);
    }

@Test
    void testSumOfProducts_nonFinite_21_oe() {
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







        assertSumOfProducts(Double.NaN, a[6], b[6]);
    }

@Test
    void testSumOfProducts_nonFinite_22_oe() {
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








        assertSumOfProducts(Double.NaN, a[7][0], b[7][0], a[7][1], b[7][1]);
    }

@Test
    void testSumOfProducts_nonFinite_23_oe() {
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








        assertSumOfProducts(Double.NaN, a[7][0], b[7][0], a[7][1], b[7][1], a[7][2], b[7][2]);
    }

@Test
    void testSumOfProducts_nonFinite_24_oe() {
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








        assertSumOfProducts(Double.NaN, a[7], b[7]);
    }

@Test
    void testSumOfProducts_nonFinite_25_oe() {
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









        assertSumOfProducts(Double.NEGATIVE_INFINITY, a[8][0], b[8][0], a[8][1], b[8][1]);
    }

@Test
    void testSumOfProducts_nonFinite_26_oe() {
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









        assertSumOfProducts(Double.NEGATIVE_INFINITY, a[8][0], b[8][0], a[8][1], b[8][1], a[8][2], b[8][2]);
    }

@Test
    void testSumOfProducts_nonFinite_27_oe() {
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









        assertSumOfProducts(Double.NEGATIVE_INFINITY, a[8], b[8]);
    }

@Test
    void testSumOfProducts_nonFinite_28_oe() {
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










        assertSumOfProducts(-3, a[9][0], b[9][0], a[9][1], b[9][1]);
    }

@Test
    void testSumOfProducts_nonFinite_29_oe() {
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










        assertSumOfProducts(Double.POSITIVE_INFINITY, a[9][0], b[9][0], a[9][1], b[9][1], a[9][2], b[9][2]);
    }

@Test
    void testSumOfProducts_nonFinite_30_oe() {
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










        assertSumOfProducts(Double.POSITIVE_INFINITY, a[9], b[9]);
    }

@Test
    void testSumOfProducts_nonFinite_31_oe() {
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











        assertSumOfProducts(-Double.MAX_VALUE, a[10][0], b[10][0], a[10][1], b[10][1]);
    }

@Test
    void testSumOfProducts_nonFinite_32_oe() {
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











        assertSumOfProducts(-Double.MAX_VALUE, a[10][0], b[10][0], a[10][1], b[10][1], a[10][2], b[10][2]);
    }

@Test
    void testSumOfProducts_nonFinite_33_oe() {
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











        assertSumOfProducts(Double.NEGATIVE_INFINITY, a[10], b[10]);
    }

@Test
    void testSumOfProducts_overflow_16_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();


        final double a1 = Math.scalb(x, 1022 - 30);
        final double b1 = Math.scalb(x, 30);
        final double a2 = a1;
        final double b2 = Math.scalb(y, 30);
        final double sxxMxy = Math.scalb(xxMxy, 1022);

        final double m = (1 << 27) + 1;
        double c;
        c = a1 * m;
        final double ha1 = c - (c - a1);
        c = b1 * m;
        final double hb1 = c - (c - b1);
        c = a2 * m;
        final double ha2 = c - (c - a2);
        c = b2 * m;
        final double hb2 = c - (c - b2);

        final double expected = Math.scalb(xxMxyHighPrecision, 1022);
        assertSumOfProducts(expected, a1, b1, a2, b2);
    }

@Test
    void testSumOfProducts_overflow_17_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();


        final double a1 = Math.scalb(x, 1022 - 30);
        final double b1 = Math.scalb(x, 30);
        final double a2 = a1;
        final double b2 = Math.scalb(y, 30);
        final double sxxMxy = Math.scalb(xxMxy, 1022);

        final double m = (1 << 27) + 1;
        double c;
        c = a1 * m;
        final double ha1 = c - (c - a1);
        c = b1 * m;
        final double hb1 = c - (c - b1);
        c = a2 * m;
        final double ha2 = c - (c - a2);
        c = b2 * m;
        final double hb2 = c - (c - b2);

        final double expected = Math.scalb(xxMxyHighPrecision, 1022);
        assertSumOfProducts(expected, a1, b1, a2, b2, 0, 0);
    }

@Test
    void testSumOfProducts_overflow_18_oe() {
        final double x = Math.nextDown(2.0);
        final double y = -Math.nextDown(x);
        final double xxMxy = x * x + x * y;
        final double xxMxyHighPrecision = Sum.create().addProduct(x, x).addProduct(x, y).getAsDouble();


        final double a1 = Math.scalb(x, 1022 - 30);
        final double b1 = Math.scalb(x, 30);
        final double a2 = a1;
        final double b2 = Math.scalb(y, 30);
        final double sxxMxy = Math.scalb(xxMxy, 1022);

        final double m = (1 << 27) + 1;
        double c;
        c = a1 * m;
        final double ha1 = c - (c - a1);
        c = b1 * m;
        final double hb1 = c - (c - b1);
        c = a2 * m;
        final double ha2 = c - (c - a2);
        c = b2 * m;
        final double hb2 = c - (c - b2);

        final double expected = Math.scalb(xxMxyHighPrecision, 1022);
        assertSumOfProducts(expected, a1, b1, a2, b2, 0, 0, 0, 0);
    }

}
