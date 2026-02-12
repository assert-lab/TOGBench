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

import java.util.Arrays;
import java.util.Collections;
import java.math.RoundingMode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for the {@link Precision} class.
 *
 */
class PrecisionTest_OE25Dev {

    // Interfaces to allow testing equals variants with the same conditions

    @FunctionalInterface
    private interface EqualsWithDelta {
        boolean equals(double a, double b, double delta);
    }

    @FunctionalInterface
    private interface EqualsWithUlps {
        boolean equals(double a, double b, int ulps);
    }

    @FunctionalInterface
    private interface FloatEqualsWithDelta {
        boolean equals(float a, float b, float delta);
    }

    @FunctionalInterface
    private interface FloatEqualsWithUlps {
        boolean equals(float a, float b, int ulps);
    }

    private static void assertEqualsWithAllowedDelta(EqualsWithDelta fun, boolean nanAreEqual) {
        Assertions.assertTrue(fun.equals(153.0000, 153.0000, .0625));
        Assertions.assertTrue(fun.equals(153.0000, 153.0625, .0625));
        Assertions.assertTrue(fun.equals(152.9375, 153.0000, .0625));
        Assertions.assertFalse(fun.equals(153.0000, 153.0625, .0624));
        Assertions.assertFalse(fun.equals(152.9374, 153.0000, .0625));
        Assertions.assertEquals(nanAreEqual, fun.equals(Double.NaN, Double.NaN, 1.0));
        Assertions.assertTrue(fun.equals(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 1.0));
        Assertions.assertTrue(fun.equals(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 1.0));
        Assertions.assertFalse(fun.equals(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1.0));
    }

    private static void assertEqualsIncludingNaNWithAllowedUlps(EqualsWithUlps fun,
            boolean nanAreEqual, boolean fixed1Ulp) {
        Assertions.assertTrue(fun.equals(0.0, -0.0, 1));

        Assertions.assertTrue(fun.equals(1.0, 1 + Math.ulp(1d), 1));
        Assertions.assertFalse(fun.equals(1.0, 1 + 2 * Math.ulp(1d), 1));

        for (double value : new double[] {153.0, -128.0, 0.0, 1.0}) {
            Assertions.assertTrue(fun.equals(value, value, 1));
            Assertions.assertTrue(fun.equals(value, Math.nextUp(value), 1));
            Assertions.assertFalse(fun.equals(value, Math.nextUp(Math.nextUp(value)), 1));
            Assertions.assertTrue(fun.equals(value, Math.nextDown(value), 1));
            Assertions.assertFalse(fun.equals(value, Math.nextDown(Math.nextDown(value)), 1));
            // This test is conditional
            if (!fixed1Ulp) {
                Assertions.assertFalse(fun.equals(value, Math.nextUp(value), 0));
                Assertions.assertTrue(fun.equals(value, Math.nextUp(Math.nextUp(value)), 2));
                Assertions.assertTrue(fun.equals(value, Math.nextDown(Math.nextDown(value)), 2));
            }
        }

        Assertions.assertTrue(fun.equals(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 1));
        Assertions.assertTrue(fun.equals(Double.MAX_VALUE, Double.POSITIVE_INFINITY, 1));

        Assertions.assertTrue(fun.equals(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 1));
        Assertions.assertTrue(fun.equals(-Double.MAX_VALUE, Double.NEGATIVE_INFINITY, 1));

        Assertions.assertEquals(nanAreEqual, fun.equals(Double.NaN, Double.NaN, 1));
        Assertions.assertEquals(nanAreEqual, fun.equals(Double.NaN, Double.NaN, 0));
        Assertions.assertFalse(fun.equals(Double.NaN, 0, 0));
        Assertions.assertFalse(fun.equals(0, Double.NaN, 0));
        Assertions.assertFalse(fun.equals(Double.NaN, Double.POSITIVE_INFINITY, 0));
        Assertions.assertFalse(fun.equals(Double.NaN, Double.NEGATIVE_INFINITY, 0));

        Assertions.assertFalse(fun.equals(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 100000));
    }

    // Tests for floating point equality match the above tests with arguments
    // converted to float

    private static void assertFloatEqualsWithAllowedDelta(FloatEqualsWithDelta fun, boolean nanAreEqual) {
        Assertions.assertTrue(fun.equals(153.0000f, 153.0000f, .0625f));
        Assertions.assertTrue(fun.equals(153.0000f, 153.0625f, .0625f));
        Assertions.assertTrue(fun.equals(152.9375f, 153.0000f, .0625f));
        Assertions.assertFalse(fun.equals(153.0000f, 153.0625f, .0624f));
        Assertions.assertFalse(fun.equals(152.9374f, 153.0000f, .0625f));
        Assertions.assertEquals(nanAreEqual, fun.equals(Float.NaN, Float.NaN, 1.0f));
        Assertions.assertTrue(fun.equals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 1.0f));
        Assertions.assertTrue(fun.equals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, 1.0f));
        Assertions.assertFalse(fun.equals(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 1.0f));
    }

    private static void assertFloatEqualsIncludingNaNWithAllowedUlps(FloatEqualsWithUlps fun,
            boolean nanAreEqual, boolean fixed1Ulp) {
        Assertions.assertTrue(fun.equals(0.0f, -0.0f, 1));

        Assertions.assertTrue(fun.equals(1.0f, 1f + Math.ulp(1f), 1));
        Assertions.assertFalse(fun.equals(1.0f, 1f + 2 * Math.ulp(1f), 1));

        for (float value : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
            Assertions.assertTrue(fun.equals(value, value, 1));
            Assertions.assertTrue(fun.equals(value, Math.nextUp(value), 1));
            Assertions.assertFalse(fun.equals(value, Math.nextUp(Math.nextUp(value)), 1));
            Assertions.assertTrue(fun.equals(value, Math.nextDown(value), 1));
            Assertions.assertFalse(fun.equals(value, Math.nextDown(Math.nextDown(value)), 1));
            // This test is conditional
            if (!fixed1Ulp) {
                Assertions.assertFalse(fun.equals(value, Math.nextUp(value), 0));
                Assertions.assertTrue(fun.equals(value, Math.nextUp(Math.nextUp(value)), 2));
                Assertions.assertTrue(fun.equals(value, Math.nextDown(Math.nextDown(value)), 2));
            }
        }

        Assertions.assertTrue(fun.equals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 1));
        Assertions.assertTrue(fun.equals(Float.MAX_VALUE, Float.POSITIVE_INFINITY, 1));

        Assertions.assertTrue(fun.equals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, 1));
        Assertions.assertTrue(fun.equals(-Float.MAX_VALUE, Float.NEGATIVE_INFINITY, 1));

        Assertions.assertEquals(nanAreEqual, fun.equals(Float.NaN, Float.NaN, 1));
        Assertions.assertEquals(nanAreEqual, fun.equals(Float.NaN, Float.NaN, 0));
        Assertions.assertFalse(fun.equals(Float.NaN, 0, 0));
        Assertions.assertFalse(fun.equals(0, Float.NaN, 0));
        Assertions.assertFalse(fun.equals(Float.NaN, Float.POSITIVE_INFINITY, 0));
        Assertions.assertFalse(fun.equals(Float.NaN, Float.NEGATIVE_INFINITY, 0));

        Assertions.assertFalse(fun.equals(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 100000));
    }


}
