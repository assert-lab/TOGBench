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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link DoublePrecision}.
 */
class DoublePrecisionTest_OE25Dev {

    /**
     * Test {@link DoublePrecision#productLow(double, double, double)} computes the same
     * result as JDK 9 Math.fma(x, y, -x * y) for edge cases.
     */

    private static void assertProductLow(double expected, double x, double y) {
        // Requires a delta of 0.0 to assert -0.0 == 0.0
        Assertions.assertEquals(expected, DoublePrecision.productLow(x, y, x * y), 0.0);
    }

    @Test
    void testSplitAssumptions_1_oe() {
        final double scale = (1 << 27) + 1;
        final double limit = 0x1.0p996;
        Assertions.assertTrue(Double.isFinite(limit * scale));
    }

    @Test
    void testSplitAssumptions_2_oe() {
        final double scale = (1 << 27) + 1;
        final double limit = 0x1.0p996;
        Assertions.assertTrue(Double.isFinite(-limit * scale));
    }

    @Test
    void testSplitAssumptions_3_oe() {
        final double scale = (1 << 27) + 1;
        final double limit = 0x1.0p996;
        Assertions.assertEquals(Double.POSITIVE_INFINITY, limit * 2 * scale);
    }

    @Test
    void testSplitAssumptions_4_oe() {
        final double scale = (1 << 27) + 1;
        final double limit = 0x1.0p996;
        Assertions.assertEquals(Double.NEGATIVE_INFINITY, -limit * 2 * scale);
    }

    @Test
    void testSplitAssumptions_5_oe() {
        final double scale = (1 << 27) + 1;
        final double limit = 0x1.0p996;
        Assertions.assertTrue(Math.getExponent(2 * Math.sqrt(Double.MAX_VALUE)) - 2 > 508);
    }

    @Test
    void testHighPart_1_oe() {
        Assertions.assertEquals(Double.NaN, DoublePrecision.highPart(Double.POSITIVE_INFINITY));
    }

    @Test
    void testHighPart_2_oe() {
        Assertions.assertEquals(Double.NaN, DoublePrecision.highPart(Double.NEGATIVE_INFINITY));
    }

    @Test
    void testHighPart_3_oe() {
        Assertions.assertEquals(Double.NaN, DoublePrecision.highPart(Double.NaN));
    }

    @Test
    void testHighPart_4_oe() {
        Assertions.assertTrue(Double.isFinite(DoublePrecision.highPart(Double.MAX_VALUE)));
    }

    @Test
    void testHighPart_5_oe() {
        Assertions.assertTrue(Double.isFinite(DoublePrecision.highPart(-Double.MAX_VALUE)));
    }

    @Test
    void testHighPartUnscaled_1_oe() {
        Assertions.assertEquals(Double.NaN, DoublePrecision.highPartUnscaled(Double.POSITIVE_INFINITY));
    }

    @Test
    void testHighPartUnscaled_2_oe() {
        Assertions.assertEquals(Double.NaN, DoublePrecision.highPartUnscaled(Double.NEGATIVE_INFINITY));
    }

    @Test
    void testHighPartUnscaled_3_oe() {
        Assertions.assertEquals(Double.NaN, DoublePrecision.highPartUnscaled(Double.NaN));
    }

    @Test
    void testHighPartUnscaled_4_oe() {
        Assertions.assertEquals(Double.NaN, DoublePrecision.highPartUnscaled(Double.MAX_VALUE));
    }

    @Test
    void testHighPartUnscaled_5_oe() {
        Assertions.assertEquals(Double.NaN, DoublePrecision.highPartUnscaled(-Double.MAX_VALUE));
    }

    @Test
    void testIsNotNormal_1_oe() {
        for (double a : new double[] {Double.MAX_VALUE, 1.0, Double.MIN_NORMAL}) {
            Assertions.assertFalse(DoublePrecision.isNotNormal(a));
    }
    }

    @Test
    void testIsNotNormal_2_oe() {
        for (double a : new double[] {Double.MAX_VALUE, 1.0, Double.MIN_NORMAL}) {
            Assertions.assertFalse(DoublePrecision.isNotNormal(-a));
    }
    }

    @Test
    void testIsNotNormal_3_oe() {
        for (double a : new double[] {Double.MAX_VALUE, 1.0, Double.MIN_NORMAL}) {
        }
        for (double a : new double[] {Double.POSITIVE_INFINITY, 0.0,
                                      Math.nextDown(Double.MIN_NORMAL), Double.NaN}) {
            Assertions.assertTrue(DoublePrecision.isNotNormal(a));
    }
    }

    @Test
    void testIsNotNormal_4_oe() {
        for (double a : new double[] {Double.MAX_VALUE, 1.0, Double.MIN_NORMAL}) {
        }
        for (double a : new double[] {Double.POSITIVE_INFINITY, 0.0,
                                      Math.nextDown(Double.MIN_NORMAL), Double.NaN}) {
            Assertions.assertTrue(DoublePrecision.isNotNormal(-a));
    }
    }

    @Test
    void testProductLow_1_oe_1_oe() {
                final double expected0 = 0.0;
        final double x0 = 1.0;
        final double y0 = Math.nextDown(Double.MIN_NORMAL);
                Assertions.assertEquals(expected0, DoublePrecision.productLow(x0, y0, x0 * y0), 0.0);
    }

    @Test
    void testProductLow_2_oe_1_oe() {
                final double expected0 = 0.0;
        final double x0 = -1.0;
        final double y0 = Math.nextDown(Double.MIN_NORMAL);
                Assertions.assertEquals(expected0, DoublePrecision.productLow(x0, y0, x0 * y0), 0.0);
    }

    @Test
    void testProductLow_3_oe_1_oe() {
                final double expected0 = Double.NaN;
        final double x0 = 1.0;
        final double y0 = Double.POSITIVE_INFINITY;
                Assertions.assertEquals(expected0, DoublePrecision.productLow(x0, y0, x0 * y0), 0.0);
    }

    @Test
    void testProductLow_4_oe_1_oe() {
                final double expected0 = Double.NaN;
        final double x0 = 1.0;
        final double y0 = Double.NEGATIVE_INFINITY;
                Assertions.assertEquals(expected0, DoublePrecision.productLow(x0, y0, x0 * y0), 0.0);
    }

    @Test
    void testProductLow_5_oe_1_oe() {
                final double expected0 = Double.NaN;
        final double x0 = 1.0;
        final double y0 = Double.NaN;
                Assertions.assertEquals(expected0, DoublePrecision.productLow(x0, y0, x0 * y0), 0.0);
    }

    @Test
    void testProductLow_6_oe_1_oe() {
                final double expected0 = 0.0;
        final double x0 = 1.0;
        final double y0 = Double.MAX_VALUE;
                Assertions.assertEquals(expected0, DoublePrecision.productLow(x0, y0, x0 * y0), 0.0);
    }

    @Test
    void testProductLow_7_oe_1_oe() {
                final double expected0 = Double.NaN;
        final double x0 = 2.0;
        final double y0 = Double.MAX_VALUE;
                Assertions.assertEquals(expected0, DoublePrecision.productLow(x0, y0, x0 * y0), 0.0);
    }

}
