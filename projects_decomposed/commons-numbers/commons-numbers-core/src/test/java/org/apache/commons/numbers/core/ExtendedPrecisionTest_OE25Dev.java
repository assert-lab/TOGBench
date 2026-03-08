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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for the {@link ExtendedPrecision} class.
 */
class ExtendedPrecisionTest_OE25Dev {
    @Test
    void testSplitAssumptions() {
        // The multiplier used to split the double value into high and low parts.
        final double scale = (1 << 27) + 1;
        // The upper limit above which a number may overflow during the split into a high part.
        final double limit = 0x1.0p996;
        Assertions.assertTrue(Double.isFinite(limit * scale));
        Assertions.assertTrue(Double.isFinite(-limit * scale));
        // Cannot make the limit the next power up
        Assertions.assertEquals(Double.POSITIVE_INFINITY, limit * 2 * scale);
        Assertions.assertEquals(Double.NEGATIVE_INFINITY, -limit * 2 * scale);
        // Check the level for the safe upper limit of the exponent of the sum of the absolute
        // components of the product
        Assertions.assertTrue(Math.getExponent(2 * Math.sqrt(Double.MAX_VALUE)) - 2 > 508);
    }

    @Test
    void testHighPartUnscaled() {
        Assertions.assertEquals(Double.NaN, ExtendedPrecision.highPartUnscaled(Double.POSITIVE_INFINITY));
        Assertions.assertEquals(Double.NaN, ExtendedPrecision.highPartUnscaled(Double.NEGATIVE_INFINITY));
        Assertions.assertEquals(Double.NaN, ExtendedPrecision.highPartUnscaled(Double.NaN));
        // Large finite numbers will overflow during the split
        Assertions.assertEquals(Double.NaN, ExtendedPrecision.highPartUnscaled(Double.MAX_VALUE));
        Assertions.assertEquals(Double.NaN, ExtendedPrecision.highPartUnscaled(-Double.MAX_VALUE));
    }

    /**
     * Test {@link ExtendedPrecision#productLow(double, double, double)} computes the same
     * result as JDK 9 Math.fma(x, y, -x * y) for edge cases.
     */
    @Test
    void testProductLow() {
        assertProductLow(0.0, 1.0, Math.nextDown(Double.MIN_NORMAL));
        assertProductLow(0.0, -1.0, Math.nextDown(Double.MIN_NORMAL));
        assertProductLow(Double.NaN, 1.0, Double.POSITIVE_INFINITY);
        assertProductLow(Double.NaN, 1.0, Double.NEGATIVE_INFINITY);
        assertProductLow(Double.NaN, 1.0, Double.NaN);
        assertProductLow(0.0, 1.0, Double.MAX_VALUE);
        assertProductLow(Double.NaN, 2.0, Double.MAX_VALUE);
    }

    private static void assertProductLow(double expected, double x, double y) {
        // Requires a delta of 0.0 to assert -0.0 == 0.0
        Assertions.assertEquals(expected, ExtendedPrecision.productLow(x, y, x * y), 0.0);
    }

    @Test
    void testIsNotNormal() {
        for (double a : new double[] {Double.MAX_VALUE, 1.0, Double.MIN_NORMAL}) {
            Assertions.assertFalse(ExtendedPrecision.isNotNormal(a));
            Assertions.assertFalse(ExtendedPrecision.isNotNormal(-a));
        }
        for (double a : new double[] {Double.POSITIVE_INFINITY, 0.0,
                                      Math.nextDown(Double.MIN_NORMAL), Double.NaN}) {
            Assertions.assertTrue(ExtendedPrecision.isNotNormal(a));
            Assertions.assertTrue(ExtendedPrecision.isNotNormal(-a));
        }
    }

    /**
     * This demonstrates splitting a sub normal number with no information in the upper 26 bits
     * of the mantissa.
     */
    @Test
    void testSubNormalSplit() {
        final double a = Double.longBitsToDouble(1L << 25);

        // A split using masking of the mantissa bits computes the high part incorrectly
        final double hi1 = Double.longBitsToDouble(Double.doubleToRawLongBits(a) & ((-1L) << 27));
        final double lo1 = a - hi1;
        Assertions.assertEquals(0, hi1);
        Assertions.assertEquals(a, lo1);
        Assertions.assertFalse(Math.abs(hi1) > Math.abs(lo1));

        // Dekker's split
        final double hi2 = ExtendedPrecision.highPartUnscaled(a);
        final double lo2 = a - hi2;
        Assertions.assertEquals(a, hi2);
        Assertions.assertEquals(0, lo2);

        Assertions.assertTrue(Math.abs(hi2) > Math.abs(lo2));
    }

    @Test
    void testSquareLowUnscaled() {
        assertSquareLowUnscaled(0.0, 1.0);
        assertSquareLowUnscaled(0.0, -1.0);
        final double expected = new BigDecimal(Math.PI).pow(2)
                .subtract(new BigDecimal(Math.PI * Math.PI)).doubleValue();
        assertSquareLowUnscaled(expected, Math.PI);

        assertSquareLowUnscaled(Double.NaN, Double.POSITIVE_INFINITY);
        assertSquareLowUnscaled(Double.NaN, Double.NEGATIVE_INFINITY);
        assertSquareLowUnscaled(Double.NaN, Double.NaN);
        assertSquareLowUnscaled(Double.NaN, Double.MAX_VALUE);
    }

    private static void assertSquareLowUnscaled(final double expected, final double x) {
        Assertions.assertEquals(expected, ExtendedPrecision.squareLowUnscaled(x, x * x));
    }

    @Test
    void testSplitAssumptions_1_oe() {
        // The multiplier used to split the double value into high and low parts.
        final double scale = (1 << 27) + 1;
        // The upper limit above which a number may overflow during the split into a high part.
        final double limit = 0x1.0p996;
        Assertions.assertTrue(Double.isFinite(limit * scale));
    }

    @Test
    void testSplitAssumptions_2_oe() {
        // The multiplier used to split the double value into high and low parts.
        final double scale = (1 << 27) + 1;
        // The upper limit above which a number may overflow during the split into a high part.
        final double limit = 0x1.0p996;
        // removed other assertion
        Assertions.assertTrue(Double.isFinite(-limit * scale));
    }

    @Test
    void testSplitAssumptions_3_oe() {
        // The multiplier used to split the double value into high and low parts.
        final double scale = (1 << 27) + 1;
        // The upper limit above which a number may overflow during the split into a high part.
        final double limit = 0x1.0p996;
        // removed other assertion
        // removed other assertion
        // Cannot make the limit the next power up
        Assertions.assertEquals(Double.POSITIVE_INFINITY, limit * 2 * scale);
    }

    @Test
    void testSplitAssumptions_4_oe() {
        // The multiplier used to split the double value into high and low parts.
        final double scale = (1 << 27) + 1;
        // The upper limit above which a number may overflow during the split into a high part.
        final double limit = 0x1.0p996;
        // removed other assertion
        // removed other assertion
        // Cannot make the limit the next power up
        // removed other assertion
        Assertions.assertEquals(Double.NEGATIVE_INFINITY, -limit * 2 * scale);
    }

    @Test
    void testSplitAssumptions_5_oe() {
        // The multiplier used to split the double value into high and low parts.
        final double scale = (1 << 27) + 1;
        // The upper limit above which a number may overflow during the split into a high part.
        final double limit = 0x1.0p996;
        // removed other assertion
        // removed other assertion
        // Cannot make the limit the next power up
        // removed other assertion
        // removed other assertion
        // Check the level for the safe upper limit of the exponent of the sum of the absolute
        // components of the product
        Assertions.assertTrue(Math.getExponent(2 * Math.sqrt(Double.MAX_VALUE)) - 2 > 508);
    }

    @Test
    void testHighPartUnscaled_1_oe() {
        Assertions.assertEquals(Double.NaN, ExtendedPrecision.highPartUnscaled(Double.POSITIVE_INFINITY));
    }

    @Test
    void testHighPartUnscaled_2_oe() {
        // removed other assertion
        Assertions.assertEquals(Double.NaN, ExtendedPrecision.highPartUnscaled(Double.NEGATIVE_INFINITY));
    }

    @Test
    void testHighPartUnscaled_3_oe() {
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Double.NaN, ExtendedPrecision.highPartUnscaled(Double.NaN));
    }

    @Test
    void testHighPartUnscaled_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Large finite numbers will overflow during the split
        Assertions.assertEquals(Double.NaN, ExtendedPrecision.highPartUnscaled(Double.MAX_VALUE));
    }

    @Test
    void testHighPartUnscaled_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Large finite numbers will overflow during the split
        // removed other assertion
        Assertions.assertEquals(Double.NaN, ExtendedPrecision.highPartUnscaled(-Double.MAX_VALUE));
    }

    @Test
    void testIsNotNormal_1_oe() {
        for (double a : new double[] {Double.MAX_VALUE, 1.0, Double.MIN_NORMAL}) {
            Assertions.assertFalse(ExtendedPrecision.isNotNormal(a));
    }
    }

    @Test
    void testIsNotNormal_2_oe() {
        for (double a : new double[] {Double.MAX_VALUE, 1.0, Double.MIN_NORMAL}) {
            // removed other assertion
            Assertions.assertFalse(ExtendedPrecision.isNotNormal(-a));
    }
    }

    @Test
    void testIsNotNormal_3_oe() {
        for (double a : new double[] {Double.MAX_VALUE, 1.0, Double.MIN_NORMAL}) {
            // removed other assertion
            // removed other assertion
        }
        for (double a : new double[] {Double.POSITIVE_INFINITY, 0.0,
                                      Math.nextDown(Double.MIN_NORMAL), Double.NaN}) {
            Assertions.assertTrue(ExtendedPrecision.isNotNormal(a));
    }
    }

    @Test
    void testIsNotNormal_4_oe() {
        for (double a : new double[] {Double.MAX_VALUE, 1.0, Double.MIN_NORMAL}) {
            // removed other assertion
            // removed other assertion
        }
        for (double a : new double[] {Double.POSITIVE_INFINITY, 0.0,
                                      Math.nextDown(Double.MIN_NORMAL), Double.NaN}) {
            // removed other assertion
            Assertions.assertTrue(ExtendedPrecision.isNotNormal(-a));
    }
    }

    @Test
    void testSubNormalSplit_1_oe() {
        final double a = Double.longBitsToDouble(1L << 25);

        // A split using masking of the mantissa bits computes the high part incorrectly
        final double hi1 = Double.longBitsToDouble(Double.doubleToRawLongBits(a) & ((-1L) << 27));
        final double lo1 = a - hi1;
        Assertions.assertEquals(0, hi1);
    }

    @Test
    void testSubNormalSplit_2_oe() {
        final double a = Double.longBitsToDouble(1L << 25);

        // A split using masking of the mantissa bits computes the high part incorrectly
        final double hi1 = Double.longBitsToDouble(Double.doubleToRawLongBits(a) & ((-1L) << 27));
        final double lo1 = a - hi1;
        // removed other assertion
        Assertions.assertEquals(a, lo1);
    }

    @Test
    void testSubNormalSplit_3_oe() {
        final double a = Double.longBitsToDouble(1L << 25);

        // A split using masking of the mantissa bits computes the high part incorrectly
        final double hi1 = Double.longBitsToDouble(Double.doubleToRawLongBits(a) & ((-1L) << 27));
        final double lo1 = a - hi1;
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(Math.abs(hi1) > Math.abs(lo1));
    }

    @Test
    void testSubNormalSplit_4_oe() {
        final double a = Double.longBitsToDouble(1L << 25);

        // A split using masking of the mantissa bits computes the high part incorrectly
        final double hi1 = Double.longBitsToDouble(Double.doubleToRawLongBits(a) & ((-1L) << 27));
        final double lo1 = a - hi1;
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Dekker's split
        final double hi2 = ExtendedPrecision.highPartUnscaled(a);
        final double lo2 = a - hi2;
        Assertions.assertEquals(a, hi2);
    }

    @Test
    void testSubNormalSplit_5_oe() {
        final double a = Double.longBitsToDouble(1L << 25);

        // A split using masking of the mantissa bits computes the high part incorrectly
        final double hi1 = Double.longBitsToDouble(Double.doubleToRawLongBits(a) & ((-1L) << 27));
        final double lo1 = a - hi1;
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Dekker's split
        final double hi2 = ExtendedPrecision.highPartUnscaled(a);
        final double lo2 = a - hi2;
        // removed other assertion
        Assertions.assertEquals(0, lo2);
    }

    @Test
    void testSubNormalSplit_6_oe() {
        final double a = Double.longBitsToDouble(1L << 25);

        // A split using masking of the mantissa bits computes the high part incorrectly
        final double hi1 = Double.longBitsToDouble(Double.doubleToRawLongBits(a) & ((-1L) << 27));
        final double lo1 = a - hi1;
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Dekker's split
        final double hi2 = ExtendedPrecision.highPartUnscaled(a);
        final double lo2 = a - hi2;
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(Math.abs(hi2) > Math.abs(lo2));
    }

    @Test
    void testProductLow_1_oe_1_oe() {
                final double expected0 = 0.0;
        final double x0 = 1.0;
        final double y0 = Math.nextDown(Double.MIN_NORMAL);
        // Requires a delta of 0.0 to assert -0.0 == 0.0
                Assertions.assertEquals(expected0, ExtendedPrecision.productLow(x0, y0, x0 * y0), 0.0);
    }

    @Test
    void testProductLow_2_oe_1_oe() {
        // removed other assertion
                final double expected0 = 0.0;
        final double x0 = -1.0;
        final double y0 = Math.nextDown(Double.MIN_NORMAL);
        // Requires a delta of 0.0 to assert -0.0 == 0.0
                Assertions.assertEquals(expected0, ExtendedPrecision.productLow(x0, y0, x0 * y0), 0.0);
    }

    @Test
    void testProductLow_3_oe_1_oe() {
        // removed other assertion
        // removed other assertion
                final double expected0 = Double.NaN;
        final double x0 = 1.0;
        final double y0 = Double.POSITIVE_INFINITY;
        // Requires a delta of 0.0 to assert -0.0 == 0.0
                Assertions.assertEquals(expected0, ExtendedPrecision.productLow(x0, y0, x0 * y0), 0.0);
    }

    @Test
    void testProductLow_4_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double expected0 = Double.NaN;
        final double x0 = 1.0;
        final double y0 = Double.NEGATIVE_INFINITY;
        // Requires a delta of 0.0 to assert -0.0 == 0.0
                Assertions.assertEquals(expected0, ExtendedPrecision.productLow(x0, y0, x0 * y0), 0.0);
    }

    @Test
    void testProductLow_5_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double expected0 = Double.NaN;
        final double x0 = 1.0;
        final double y0 = Double.NaN;
        // Requires a delta of 0.0 to assert -0.0 == 0.0
                Assertions.assertEquals(expected0, ExtendedPrecision.productLow(x0, y0, x0 * y0), 0.0);
    }

    @Test
    void testProductLow_6_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double expected0 = 0.0;
        final double x0 = 1.0;
        final double y0 = Double.MAX_VALUE;
        // Requires a delta of 0.0 to assert -0.0 == 0.0
                Assertions.assertEquals(expected0, ExtendedPrecision.productLow(x0, y0, x0 * y0), 0.0);
    }

    @Test
    void testProductLow_7_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double expected0 = Double.NaN;
        final double x0 = 2.0;
        final double y0 = Double.MAX_VALUE;
        // Requires a delta of 0.0 to assert -0.0 == 0.0
                Assertions.assertEquals(expected0, ExtendedPrecision.productLow(x0, y0, x0 * y0), 0.0);
    }

    @Test
    void testSquareLowUnscaled_1_oe_1_oe() {
                final double expected0 = 0.0;
        final double x0 = 1.0;
        Assertions.assertEquals(expected0, ExtendedPrecision.squareLowUnscaled(x0, x0 * x0));
    }

    @Test
    void testSquareLowUnscaled_2_oe_1_oe() {
        // removed other assertion
                final double expected0 = 0.0;
        final double x0 = -1.0;
        Assertions.assertEquals(expected0, ExtendedPrecision.squareLowUnscaled(x0, x0 * x0));
    }

    @Test
    void testSquareLowUnscaled_3_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        final double expected = new BigDecimal(Math.PI).pow(2)
                .subtract(new BigDecimal(Math.PI * Math.PI)).doubleValue();
                final double expected0 = expected;
        final double x0 = Math.PI;
        Assertions.assertEquals(expected0, ExtendedPrecision.squareLowUnscaled(x0, x0 * x0));
    }

    @Test
    void testSquareLowUnscaled_4_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        final double expected = new BigDecimal(Math.PI).pow(2)
                .subtract(new BigDecimal(Math.PI * Math.PI)).doubleValue();
        // removed other assertion

                final double expected0 = Double.NaN;
        final double x0 = Double.POSITIVE_INFINITY;
        Assertions.assertEquals(expected0, ExtendedPrecision.squareLowUnscaled(x0, x0 * x0));
    }

    @Test
    void testSquareLowUnscaled_5_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        final double expected = new BigDecimal(Math.PI).pow(2)
                .subtract(new BigDecimal(Math.PI * Math.PI)).doubleValue();
        // removed other assertion

        // removed other assertion
                final double expected0 = Double.NaN;
        final double x0 = Double.NEGATIVE_INFINITY;
        Assertions.assertEquals(expected0, ExtendedPrecision.squareLowUnscaled(x0, x0 * x0));
    }

    @Test
    void testSquareLowUnscaled_6_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        final double expected = new BigDecimal(Math.PI).pow(2)
                .subtract(new BigDecimal(Math.PI * Math.PI)).doubleValue();
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final double expected0 = Double.NaN;
        final double x0 = Double.NaN;
        Assertions.assertEquals(expected0, ExtendedPrecision.squareLowUnscaled(x0, x0 * x0));
    }

    @Test
    void testSquareLowUnscaled_7_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        final double expected = new BigDecimal(Math.PI).pow(2)
                .subtract(new BigDecimal(Math.PI * Math.PI)).doubleValue();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double expected0 = Double.NaN;
        final double x0 = Double.MAX_VALUE;
        Assertions.assertEquals(expected0, ExtendedPrecision.squareLowUnscaled(x0, x0 * x0));
    }

}
