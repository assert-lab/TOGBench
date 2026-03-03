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

    @Test
    void testEqualsWithRelativeTolerance_1_oe() {
        Assertions.assertTrue(Precision.equalsWithRelativeTolerance(0d, 0d, 0d));
    }

    @Test
    void testEqualsWithRelativeTolerance_2_oe() {
        Assertions.assertTrue(Precision.equalsWithRelativeTolerance(0d, 1 / Double.NEGATIVE_INFINITY, 0d));
    }

    @Test
    void testEqualsWithRelativeTolerance_3_oe() {

        final double eps = 1e-14;
        Assertions.assertFalse(Precision.equalsWithRelativeTolerance(1.987654687654968, 1.987654687654988, eps));
    }

    @Test
    void testEqualsWithRelativeTolerance_4_oe() {

        final double eps = 1e-14;
        Assertions.assertTrue(Precision.equalsWithRelativeTolerance(1.987654687654968, 1.987654687654987, eps));
    }

    @Test
    void testEqualsWithRelativeTolerance_5_oe() {

        final double eps = 1e-14;
        Assertions.assertFalse(Precision.equalsWithRelativeTolerance(1.987654687654968, 1.987654687654948, eps));
    }

    @Test
    void testEqualsWithRelativeTolerance_6_oe() {

        final double eps = 1e-14;
        Assertions.assertTrue(Precision.equalsWithRelativeTolerance(1.987654687654968, 1.987654687654949, eps));
    }

    @Test
    void testEqualsWithRelativeTolerance_7_oe() {

        final double eps = 1e-14;

        Assertions.assertFalse(Precision.equalsWithRelativeTolerance(Precision.SAFE_MIN, 0.0, eps));
    }

    @Test
    void testEqualsWithRelativeTolerance_8_oe() {

        final double eps = 1e-14;


        Assertions.assertFalse(Precision.equalsWithRelativeTolerance(1.0000000000001e-300, 1e-300, eps));
    }

    @Test
    void testEqualsWithRelativeTolerance_9_oe() {

        final double eps = 1e-14;


        Assertions.assertTrue(Precision.equalsWithRelativeTolerance(1.00000000000001e-300, 1e-300, eps));
    }

    @Test
    void testEqualsWithRelativeTolerance_10_oe() {

        final double eps = 1e-14;



        Assertions.assertFalse(Precision.equalsWithRelativeTolerance(Double.NEGATIVE_INFINITY, 1.23, eps));
    }

    @Test
    void testEqualsWithRelativeTolerance_11_oe() {

        final double eps = 1e-14;



        Assertions.assertFalse(Precision.equalsWithRelativeTolerance(Double.POSITIVE_INFINITY, 1.23, eps));
    }

    @Test
    void testEqualsWithRelativeTolerance_12_oe() {

        final double eps = 1e-14;




        Assertions.assertTrue(Precision.equalsWithRelativeTolerance(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, eps));
    }

    @Test
    void testEqualsWithRelativeTolerance_13_oe() {

        final double eps = 1e-14;




        Assertions.assertTrue(Precision.equalsWithRelativeTolerance(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, eps));
    }

    @Test
    void testEqualsWithRelativeTolerance_14_oe() {

        final double eps = 1e-14;




        Assertions.assertFalse(Precision.equalsWithRelativeTolerance(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, eps));
    }

    @Test
    void testEqualsWithRelativeTolerance_15_oe() {

        final double eps = 1e-14;





        Assertions.assertFalse(Precision.equalsWithRelativeTolerance(Double.NaN, 1.23, eps));
    }

    @Test
    void testEqualsWithRelativeTolerance_16_oe() {

        final double eps = 1e-14;





        Assertions.assertFalse(Precision.equalsWithRelativeTolerance(Double.NaN, Double.NaN, eps));
    }

    @Test
    void testEqualsIncludingNaN_1_oe() {
        double[] testArray = {
            Double.NaN,
            Double.POSITIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            1d,
            0d };
        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray.length; j++) {
                if (i == j) {
                    Assertions.assertTrue(Precision.equalsIncludingNaN(testArray[i], testArray[j]));
    }
    }
    }
    }

    @Test
    void testEqualsIncludingNaN_2_oe() {
        double[] testArray = {
            Double.NaN,
            Double.POSITIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            1d,
            0d };
        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray.length; j++) {
                if (i == j) {
                    Assertions.assertTrue(Precision.equalsIncludingNaN(testArray[j], testArray[i]));
    }
    }
    }
    }

    @Test
    void testEqualsIncludingNaN_3_oe() {
        double[] testArray = {
            Double.NaN,
            Double.POSITIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            1d,
            0d };
        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray.length; j++) {
                if (i == j) {
                } else {
                    Assertions.assertFalse(Precision.equalsIncludingNaN(testArray[i], testArray[j]));
    }
    }
    }
    }

    @Test
    void testEqualsIncludingNaN_4_oe() {
        double[] testArray = {
            Double.NaN,
            Double.POSITIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            1d,
            0d };
        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray.length; j++) {
                if (i == j) {
                } else {
                    Assertions.assertFalse(Precision.equalsIncludingNaN(testArray[j], testArray[i]));
    }
    }
    }
    }

    @Test
    void testFloatEqualsIncludingNaN_1_oe() {
        float[] testArray = {
            Float.NaN,
            Float.POSITIVE_INFINITY,
            Float.NEGATIVE_INFINITY,
            1f,
            0f };
        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray.length; j++) {
                if (i == j) {
                    Assertions.assertTrue(Precision.equalsIncludingNaN(testArray[i], testArray[j]));
    }
    }
    }
    }

    @Test
    void testFloatEqualsIncludingNaN_2_oe() {
        float[] testArray = {
            Float.NaN,
            Float.POSITIVE_INFINITY,
            Float.NEGATIVE_INFINITY,
            1f,
            0f };
        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray.length; j++) {
                if (i == j) {
                    Assertions.assertTrue(Precision.equalsIncludingNaN(testArray[j], testArray[i]));
    }
    }
    }
    }

    @Test
    void testFloatEqualsIncludingNaN_3_oe() {
        float[] testArray = {
            Float.NaN,
            Float.POSITIVE_INFINITY,
            Float.NEGATIVE_INFINITY,
            1f,
            0f };
        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray.length; j++) {
                if (i == j) {
                } else {
                    Assertions.assertFalse(Precision.equalsIncludingNaN(testArray[i], testArray[j]));
    }
    }
    }
    }

    @Test
    void testFloatEqualsIncludingNaN_4_oe() {
        float[] testArray = {
            Float.NaN,
            Float.POSITIVE_INFINITY,
            Float.NEGATIVE_INFINITY,
            1f,
            0f };
        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray.length; j++) {
                if (i == j) {
                } else {
                    Assertions.assertFalse(Precision.equalsIncludingNaN(testArray[j], testArray[i]));
    }
    }
    }
    }

    @Test
    void testCompareToEpsilon_1_oe() {
        Assertions.assertEquals(0, Precision.compareTo(152.33, 152.32, .011));
    }

    @Test
    void testCompareToEpsilon_2_oe() {
        Assertions.assertTrue(Precision.compareTo(152.308, 152.32, .011) < 0);
    }

    @Test
    void testCompareToEpsilon_3_oe() {
        Assertions.assertTrue(Precision.compareTo(152.33, 152.318, .011) > 0);
    }

    @Test
    void testCompareToEpsilon_4_oe() {
        Assertions.assertEquals(0, Precision.compareTo(Double.MIN_VALUE, +0.0, Double.MIN_VALUE));
    }

    @Test
    void testCompareToEpsilon_5_oe() {
        Assertions.assertEquals(0, Precision.compareTo(Double.MIN_VALUE, -0.0, Double.MIN_VALUE));
    }

    @Test
    void testSortWithCompareTo_1_oe() {
        final Double[] array = {Double.NaN, 0.02, 0.01, Double.NaN, 2.0, 1.0};
        final double eps = 0.1;
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(Arrays.asList(array));
            Arrays.sort(array, (a, b) -> Precision.compareTo(a, b, eps));

            for (int j = 0; j < array.length - 1; j++) {
                final int c = Precision.compareTo(array[j],
                                                  array[j + 1],
                                                  eps);
                Assertions.assertNotEquals(1, c);
    }
    }
    }

    @Test
    void testSortWithCompareTo_2_oe() {
        final Double[] array = {Double.NaN, 0.02, 0.01, Double.NaN, 2.0, 1.0};
        final double eps = 0.1;
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(Arrays.asList(array));
            Arrays.sort(array, (a, b) -> Precision.compareTo(a, b, eps));

            for (int j = 0; j < array.length - 1; j++) {
                final int c = Precision.compareTo(array[j],
                                                  array[j + 1],
                                                  eps);
            }
            Assertions.assertTrue(array[0] == 0.01 || array[0] == 0.02);
    }
    }

    @Test
    void testSortWithCompareTo_3_oe() {
        final Double[] array = {Double.NaN, 0.02, 0.01, Double.NaN, 2.0, 1.0};
        final double eps = 0.1;
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(Arrays.asList(array));
            Arrays.sort(array, (a, b) -> Precision.compareTo(a, b, eps));

            for (int j = 0; j < array.length - 1; j++) {
                final int c = Precision.compareTo(array[j],
                                                  array[j + 1],
                                                  eps);
            }
            Assertions.assertTrue(array[1] == 0.01 || array[1] == 0.02);
    }
    }

    @Test
    void testSortWithCompareTo_4_oe() {
        final Double[] array = {Double.NaN, 0.02, 0.01, Double.NaN, 2.0, 1.0};
        final double eps = 0.1;
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(Arrays.asList(array));
            Arrays.sort(array, (a, b) -> Precision.compareTo(a, b, eps));

            for (int j = 0; j < array.length - 1; j++) {
                final int c = Precision.compareTo(array[j],
                                                  array[j + 1],
                                                  eps);
            }
            Assertions.assertEquals(1, array[2], 0d);
    }
    }

    @Test
    void testSortWithCompareTo_5_oe() {
        final Double[] array = {Double.NaN, 0.02, 0.01, Double.NaN, 2.0, 1.0};
        final double eps = 0.1;
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(Arrays.asList(array));
            Arrays.sort(array, (a, b) -> Precision.compareTo(a, b, eps));

            for (int j = 0; j < array.length - 1; j++) {
                final int c = Precision.compareTo(array[j],
                                                  array[j + 1],
                                                  eps);
            }
            Assertions.assertEquals(2, array[3], 0d);
    }
    }

    @Test
    void testSortWithCompareTo_6_oe() {
        final Double[] array = {Double.NaN, 0.02, 0.01, Double.NaN, 2.0, 1.0};
        final double eps = 0.1;
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(Arrays.asList(array));
            Arrays.sort(array, (a, b) -> Precision.compareTo(a, b, eps));

            for (int j = 0; j < array.length - 1; j++) {
                final int c = Precision.compareTo(array[j],
                                                  array[j + 1],
                                                  eps);
            }
            Assertions.assertTrue(Double.isNaN(array[4]));
    }
    }

    @Test
    void testSortWithCompareTo_7_oe() {
        final Double[] array = {Double.NaN, 0.02, 0.01, Double.NaN, 2.0, 1.0};
        final double eps = 0.1;
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(Arrays.asList(array));
            Arrays.sort(array, (a, b) -> Precision.compareTo(a, b, eps));

            for (int j = 0; j < array.length - 1; j++) {
                final int c = Precision.compareTo(array[j],
                                                  array[j + 1],
                                                  eps);
            }
            Assertions.assertTrue(Double.isNaN(array[5]));
    }
    }

    @Test
    void testCompareToMaxUlps_1_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
                Assertions.assertEquals(+0, Precision.compareTo(a, a + i * delta, 5));
    }
    }
    }

    @Test
    void testCompareToMaxUlps_2_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
                Assertions.assertEquals(+0, Precision.compareTo(a, a - i * delta, 5));
    }
    }
    }

    @Test
    void testCompareToMaxUlps_3_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
                Assertions.assertEquals(-1, Precision.compareTo(a, a + i * delta, 5));
    }
    }
    }

    @Test
    void testCompareToMaxUlps_4_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
                Assertions.assertEquals(+1, Precision.compareTo(a, a - i * delta, 5));
    }
    }
    }

    @Test
    void testCompareToMaxUlps_5_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
            }
        }

        Assertions.assertEquals(+0, Precision.compareTo(-0.0, 0.0, 0));
    }

    @Test
    void testCompareToMaxUlps_6_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
            }
        }


        Assertions.assertEquals(-1, Precision.compareTo(-Double.MIN_VALUE, -0.0, 0));
    }

    @Test
    void testCompareToMaxUlps_7_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
            }
        }


        Assertions.assertEquals(+0, Precision.compareTo(-Double.MIN_VALUE, -0.0, 1));
    }

    @Test
    void testCompareToMaxUlps_8_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
            }
        }


        Assertions.assertEquals(-1, Precision.compareTo(-Double.MIN_VALUE, +0.0, 0));
    }

    @Test
    void testCompareToMaxUlps_9_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
            }
        }


        Assertions.assertEquals(+0, Precision.compareTo(-Double.MIN_VALUE, +0.0, 1));
    }

    @Test
    void testCompareToMaxUlps_10_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
            }
        }



        Assertions.assertEquals(+1, Precision.compareTo(+Double.MIN_VALUE, -0.0, 0));
    }

    @Test
    void testCompareToMaxUlps_11_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
            }
        }



        Assertions.assertEquals(+0, Precision.compareTo(+Double.MIN_VALUE, -0.0, 1));
    }

    @Test
    void testCompareToMaxUlps_12_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
            }
        }



        Assertions.assertEquals(+1, Precision.compareTo(+Double.MIN_VALUE, +0.0, 0));
    }

    @Test
    void testCompareToMaxUlps_13_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
            }
        }



        Assertions.assertEquals(+0, Precision.compareTo(+Double.MIN_VALUE, +0.0, 1));
    }

    @Test
    void testCompareToMaxUlps_14_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
            }
        }




        Assertions.assertEquals(-1, Precision.compareTo(-Double.MIN_VALUE, Double.MIN_VALUE, 0));
    }

    @Test
    void testCompareToMaxUlps_15_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
            }
        }




        Assertions.assertEquals(-1, Precision.compareTo(-Double.MIN_VALUE, Double.MIN_VALUE, 1));
    }

    @Test
    void testCompareToMaxUlps_16_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
            }
        }




        Assertions.assertEquals(+0, Precision.compareTo(-Double.MIN_VALUE, Double.MIN_VALUE, 2));
    }

    @Test
    void testCompareToMaxUlps_17_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
            }
        }





        Assertions.assertEquals(+0, Precision.compareTo(Double.MAX_VALUE, Double.POSITIVE_INFINITY, 1));
    }

    @Test
    void testCompareToMaxUlps_18_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
            }
        }





        Assertions.assertEquals(-1, Precision.compareTo(Double.MAX_VALUE, Double.POSITIVE_INFINITY, 0));
    }

    @Test
    void testCompareToMaxUlps_19_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
            }
        }






        Assertions.assertEquals(+1, Precision.compareTo(Double.MAX_VALUE, Double.NaN, Integer.MAX_VALUE));
    }

    @Test
    void testCompareToMaxUlps_20_oe() {
        double a = 152.32;
        double delta = Math.ulp(a);
        for (int i = 0; i <= 10; ++i) {
            if (i <= 5) {
            } else {
            }
        }






        Assertions.assertEquals(+1, Precision.compareTo(Double.NaN, Double.MAX_VALUE, Integer.MAX_VALUE));
    }

    @Test
    void testRoundDouble_1_oe() {
        double x = 1.234567890;
        Assertions.assertEquals(1.23, Precision.round(x, 2));
    }

    @Test
    void testRoundDouble_2_oe() {
        double x = 1.234567890;
        Assertions.assertEquals(1.235, Precision.round(x, 3));
    }

    @Test
    void testRoundDouble_3_oe() {
        double x = 1.234567890;
        Assertions.assertEquals(1.2346, Precision.round(x, 4));
    }

    @Test
    void testRoundDouble_4_oe() {
        double x = 1.234567890;

        Assertions.assertEquals(39.25, Precision.round(39.245, 2));
    }

    @Test
    void testRoundDouble_5_oe() {
        double x = 1.234567890;

        Assertions.assertEquals(39.24, Precision.round(39.245, 2, RoundingMode.DOWN));
    }

    @Test
    void testRoundDouble_6_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;
        Assertions.assertEquals(39.25, Precision.round(xx, 2));
    }

    @Test
    void testRoundDouble_7_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;

        Assertions.assertEquals(30.1d, Precision.round(30.095d, 2));
    }

    @Test
    void testRoundDouble_8_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;

        Assertions.assertEquals(30.1d, Precision.round(30.095d, 1));
    }

    @Test
    void testRoundDouble_9_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;

        Assertions.assertEquals(33.1d, Precision.round(33.095d, 1));
    }

    @Test
    void testRoundDouble_10_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;

        Assertions.assertEquals(33.1d, Precision.round(33.095d, 2));
    }

    @Test
    void testRoundDouble_11_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;

        Assertions.assertEquals(50.09d, Precision.round(50.085d, 2));
    }

    @Test
    void testRoundDouble_12_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;

        Assertions.assertEquals(50.19d, Precision.round(50.185d, 2));
    }

    @Test
    void testRoundDouble_13_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;

        Assertions.assertEquals(50.01d, Precision.round(50.005d, 2));
    }

    @Test
    void testRoundDouble_14_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;

        Assertions.assertEquals(30.01d, Precision.round(30.005d, 2));
    }

    @Test
    void testRoundDouble_15_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;

        Assertions.assertEquals(30.65d, Precision.round(30.645d, 2));
    }

    @Test
    void testRoundDouble_16_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;


        Assertions.assertEquals(1.24, Precision.round(x, 2, RoundingMode.CEILING));
    }

    @Test
    void testRoundDouble_17_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;


        Assertions.assertEquals(1.235, Precision.round(x, 3, RoundingMode.CEILING));
    }

    @Test
    void testRoundDouble_18_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;


        Assertions.assertEquals(1.2346, Precision.round(x, 4, RoundingMode.CEILING));
    }

    @Test
    void testRoundDouble_19_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;


        Assertions.assertEquals(-1.23, Precision.round(-x, 2, RoundingMode.CEILING));
    }

    @Test
    void testRoundDouble_20_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;


        Assertions.assertEquals(-1.234, Precision.round(-x, 3, RoundingMode.CEILING));
    }

    @Test
    void testRoundDouble_21_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;


        Assertions.assertEquals(-1.2345, Precision.round(-x, 4, RoundingMode.CEILING));
    }

    @Test
    void testRoundDouble_22_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;



        Assertions.assertEquals(1.23, Precision.round(x, 2, RoundingMode.DOWN));
    }

    @Test
    void testRoundDouble_23_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;



        Assertions.assertEquals(1.234, Precision.round(x, 3, RoundingMode.DOWN));
    }

    @Test
    void testRoundDouble_24_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;



        Assertions.assertEquals(1.2345, Precision.round(x, 4, RoundingMode.DOWN));
    }

    @Test
    void testRoundDouble_25_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;



        Assertions.assertEquals(-1.23, Precision.round(-x, 2, RoundingMode.DOWN));
    }

    @Test
    void testRoundDouble_26_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;



        Assertions.assertEquals(-1.234, Precision.round(-x, 3, RoundingMode.DOWN));
    }

    @Test
    void testRoundDouble_27_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;



        Assertions.assertEquals(-1.2345, Precision.round(-x, 4, RoundingMode.DOWN));
    }

    @Test
    void testRoundDouble_28_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;




        Assertions.assertEquals(1.23, Precision.round(x, 2, RoundingMode.FLOOR));
    }

    @Test
    void testRoundDouble_29_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;




        Assertions.assertEquals(1.234, Precision.round(x, 3, RoundingMode.FLOOR));
    }

    @Test
    void testRoundDouble_30_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;




        Assertions.assertEquals(1.2345, Precision.round(x, 4, RoundingMode.FLOOR));
    }

    @Test
    void testRoundDouble_31_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;




        Assertions.assertEquals(-1.24, Precision.round(-x, 2, RoundingMode.FLOOR));
    }

    @Test
    void testRoundDouble_32_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;




        Assertions.assertEquals(-1.235, Precision.round(-x, 3, RoundingMode.FLOOR));
    }

    @Test
    void testRoundDouble_33_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;




        Assertions.assertEquals(-1.2346, Precision.round(-x, 4, RoundingMode.FLOOR));
    }

    @Test
    void testRoundDouble_34_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;





        Assertions.assertEquals(1.23, Precision.round(x, 2, RoundingMode.HALF_DOWN));
    }

    @Test
    void testRoundDouble_35_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;





        Assertions.assertEquals(1.235, Precision.round(x, 3, RoundingMode.HALF_DOWN));
    }

    @Test
    void testRoundDouble_36_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;





        Assertions.assertEquals(1.2346, Precision.round(x, 4, RoundingMode.HALF_DOWN));
    }

    @Test
    void testRoundDouble_37_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;





        Assertions.assertEquals(-1.23, Precision.round(-x, 2, RoundingMode.HALF_DOWN));
    }

    @Test
    void testRoundDouble_38_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;





        Assertions.assertEquals(-1.235, Precision.round(-x, 3, RoundingMode.HALF_DOWN));
    }

    @Test
    void testRoundDouble_39_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;





        Assertions.assertEquals(-1.2346, Precision.round(-x, 4, RoundingMode.HALF_DOWN));
    }

    @Test
    void testRoundDouble_40_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;





        Assertions.assertEquals(1.234, Precision.round(1.2345, 3, RoundingMode.HALF_DOWN));
    }

    @Test
    void testRoundDouble_41_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;





        Assertions.assertEquals(-1.234, Precision.round(-1.2345, 3, RoundingMode.HALF_DOWN));
    }

    @Test
    void testRoundDouble_42_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;






        Assertions.assertEquals(1.23, Precision.round(x, 2, RoundingMode.HALF_EVEN));
    }

    @Test
    void testRoundDouble_43_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;






        Assertions.assertEquals(1.235, Precision.round(x, 3, RoundingMode.HALF_EVEN));
    }

    @Test
    void testRoundDouble_44_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;






        Assertions.assertEquals(1.2346, Precision.round(x, 4, RoundingMode.HALF_EVEN));
    }

    @Test
    void testRoundDouble_45_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;






        Assertions.assertEquals(-1.23, Precision.round(-x, 2, RoundingMode.HALF_EVEN));
    }

    @Test
    void testRoundDouble_46_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;






        Assertions.assertEquals(-1.235, Precision.round(-x, 3, RoundingMode.HALF_EVEN));
    }

    @Test
    void testRoundDouble_47_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;






        Assertions.assertEquals(-1.2346, Precision.round(-x, 4, RoundingMode.HALF_EVEN));
    }

    @Test
    void testRoundDouble_48_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;






        Assertions.assertEquals(1.234, Precision.round(1.2345, 3, RoundingMode.HALF_EVEN));
    }

    @Test
    void testRoundDouble_49_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;






        Assertions.assertEquals(-1.234, Precision.round(-1.2345, 3, RoundingMode.HALF_EVEN));
    }

    @Test
    void testRoundDouble_50_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;






        Assertions.assertEquals(1.236, Precision.round(1.2355, 3, RoundingMode.HALF_EVEN));
    }

    @Test
    void testRoundDouble_51_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;






        Assertions.assertEquals(-1.236, Precision.round(-1.2355, 3, RoundingMode.HALF_EVEN));
    }

    @Test
    void testRoundDouble_52_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;







        Assertions.assertEquals(1.23, Precision.round(x, 2, RoundingMode.HALF_UP));
    }

    @Test
    void testRoundDouble_53_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;







        Assertions.assertEquals(1.235, Precision.round(x, 3, RoundingMode.HALF_UP));
    }

    @Test
    void testRoundDouble_54_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;







        Assertions.assertEquals(1.2346, Precision.round(x, 4, RoundingMode.HALF_UP));
    }

    @Test
    void testRoundDouble_55_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;







        Assertions.assertEquals(-1.23, Precision.round(-x, 2, RoundingMode.HALF_UP));
    }

    @Test
    void testRoundDouble_56_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;







        Assertions.assertEquals(-1.235, Precision.round(-x, 3, RoundingMode.HALF_UP));
    }

    @Test
    void testRoundDouble_57_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;







        Assertions.assertEquals(-1.2346, Precision.round(-x, 4, RoundingMode.HALF_UP));
    }

    @Test
    void testRoundDouble_58_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;







        Assertions.assertEquals(1.235, Precision.round(1.2345, 3, RoundingMode.HALF_UP));
    }

    @Test
    void testRoundDouble_59_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;







        Assertions.assertEquals(-1.235, Precision.round(-1.2345, 3, RoundingMode.HALF_UP));
    }

    @Test
    void testRoundDouble_60_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;








        Assertions.assertEquals(-1.23, Precision.round(-1.23, 2, RoundingMode.UNNECESSARY));
    }

    @Test
    void testRoundDouble_61_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;








        Assertions.assertEquals(1.23, Precision.round(1.23, 2, RoundingMode.UNNECESSARY));
    }

    @Test
    void testRoundDouble_63_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;









        try {
            Precision.round(1.234, 2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException ex) {
        }

        Assertions.assertEquals(1.24, Precision.round(x, 2, RoundingMode.UP));
    }

    @Test
    void testRoundDouble_64_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;









        try {
            Precision.round(1.234, 2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException ex) {
        }

        Assertions.assertEquals(1.235, Precision.round(x, 3, RoundingMode.UP));
    }

    @Test
    void testRoundDouble_65_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;









        try {
            Precision.round(1.234, 2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException ex) {
        }

        Assertions.assertEquals(1.2346, Precision.round(x, 4, RoundingMode.UP));
    }

    @Test
    void testRoundDouble_66_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;









        try {
            Precision.round(1.234, 2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException ex) {
        }

        Assertions.assertEquals(-1.24, Precision.round(-x, 2, RoundingMode.UP));
    }

    @Test
    void testRoundDouble_67_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;









        try {
            Precision.round(1.234, 2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException ex) {
        }

        Assertions.assertEquals(-1.235, Precision.round(-x, 3, RoundingMode.UP));
    }

    @Test
    void testRoundDouble_68_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;









        try {
            Precision.round(1.234, 2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException ex) {
        }

        Assertions.assertEquals(-1.2346, Precision.round(-x, 4, RoundingMode.UP));
    }

    @Test
    void testRoundDouble_69_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;









        try {
            Precision.round(1.234, 2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException ex) {
        }


        Assertions.assertEquals(39.25, Precision.round(39.245, 2, RoundingMode.HALF_UP));
    }

    @Test
    void testRoundDouble_70_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;









        try {
            Precision.round(1.234, 2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException ex) {
        }



        Assertions.assertEquals(Double.NaN, Precision.round(Double.NaN, 2));
    }

    @Test
    void testRoundDouble_71_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;









        try {
            Precision.round(1.234, 2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException ex) {
        }



        Assertions.assertEquals(0.0, Precision.round(0.0, 2));
    }

    @Test
    void testRoundDouble_72_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;









        try {
            Precision.round(1.234, 2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException ex) {
        }



        Assertions.assertEquals(Double.POSITIVE_INFINITY, Precision.round(Double.POSITIVE_INFINITY, 2));
    }

    @Test
    void testRoundDouble_73_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;









        try {
            Precision.round(1.234, 2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException ex) {
        }



        Assertions.assertEquals(Double.NEGATIVE_INFINITY, Precision.round(Double.NEGATIVE_INFINITY, 2));
    }

    @Test
    void testRoundDouble_74_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;









        try {
            Precision.round(1.234, 2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException ex) {
        }



        Assertions.assertEquals("-0.0", Double.toString(Precision.round(-0.0, 0)));
    }

    @Test
    void testRoundDouble_75_oe() {
        double x = 1.234567890;

        double xx = 39.0;
        xx += 245d / 1000d;









        try {
            Precision.round(1.234, 2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException ex) {
        }



        Assertions.assertEquals("-0.0", Double.toString(Precision.round(-1e-10, 0)));
    }

    @Test
    void testRepresentableDelta_1_oe() {
        int nonRepresentableCount = 0;
        final double x = 100;
        final int numTrials = 10000;
        for (int i = 0; i < numTrials; i++) {
            final double originalDelta = Math.random();
            final double delta = Precision.representableDelta(x, originalDelta);
            if (delta != originalDelta) {
                ++nonRepresentableCount;
            }
        }

        Assertions.assertTrue(nonRepresentableCount / (double) numTrials > 0.9);
    }

    @Test
    void testIssue721_1_oe() {
        Assertions.assertEquals(-53, Math.getExponent(Precision.EPSILON));
    }

    @Test
    void testIssue721_2_oe() {
        Assertions.assertEquals(-1022, Math.getExponent(Precision.SAFE_MIN));
    }

    @Test
    void testMath475_1_oe() {
        final double a = 1.7976931348623182E16;
        final double b = Math.nextUp(a);

        double diff = Math.abs(a - b);
        Assertions.assertTrue(Precision.equals(a, b, 0.5 * diff));
    }

    @Test
    void testMath475_2_oe() {
        final double a = 1.7976931348623182E16;
        final double b = Math.nextUp(a);

        double diff = Math.abs(a - b);

        final double c = Math.nextUp(b);
        diff = Math.abs(a - c);
        Assertions.assertTrue(Precision.equals(a, c, diff));
    }

    @Test
    void testMath475_3_oe() {
        final double a = 1.7976931348623182E16;
        final double b = Math.nextUp(a);

        double diff = Math.abs(a - b);

        final double c = Math.nextUp(b);
        diff = Math.abs(a - c);
        Assertions.assertFalse(Precision.equals(a, c, Math.nextDown(1.0) * diff));
    }

    @Test
    void testMath475Float_1_oe() {
        final float a = 1.7976931348623182E16f;
        final float b = Math.nextUp(a);

        float diff = Math.abs(a - b);
        Assertions.assertTrue(Precision.equals(a, b, 0.5f * diff));
    }

    @Test
    void testMath475Float_2_oe() {
        final float a = 1.7976931348623182E16f;
        final float b = Math.nextUp(a);

        float diff = Math.abs(a - b);

        final float c = Math.nextUp(b);
        diff = Math.abs(a - c);
        Assertions.assertTrue(Precision.equals(a, c, diff));
    }

    @Test
    void testMath475Float_3_oe() {
        final float a = 1.7976931348623182E16f;
        final float b = Math.nextUp(a);

        float diff = Math.abs(a - b);

        final float c = Math.nextUp(b);
        diff = Math.abs(a - c);
        Assertions.assertFalse(Precision.equals(a, c, Math.nextDown(1.0f) * diff));
    }

    @Test
    void testMath843_1_oe() {
        final double afterEpsilon = Math.nextAfter(Precision.EPSILON,
                                                   Double.POSITIVE_INFINITY);

        Assertions.assertEquals(1, 1 + Precision.EPSILON);
    }

    @Test
    void testMath843_2_oe() {
        final double afterEpsilon = Math.nextAfter(Precision.EPSILON,
                                                   Double.POSITIVE_INFINITY);


        Assertions.assertNotEquals(1, 1 + afterEpsilon, 0.0);
    }

    @Test
    void testMath1127_1_oe() {
        Assertions.assertFalse(Precision.equals(2.0, -2.0, 1));
    }

    @Test
    void testMath1127_2_oe() {
        Assertions.assertTrue(Precision.equals(0.0, -0.0, 0));
    }

    @Test
    void testMath1127_3_oe() {
        Assertions.assertFalse(Precision.equals(2.0f, -2.0f, 1));
    }

    @Test
    void testMath1127_4_oe() {
        Assertions.assertTrue(Precision.equals(0.0f, -0.0f, 0));
    }

    @Test
    void testEqualsWithAllowedDelta_1_oe_1_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        Assertions.assertTrue(fun0.equals(153.0000, 153.0000, .0625));
    }

    @Test
    void testEqualsWithAllowedDelta_1_oe_2_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertTrue(fun0.equals(153.0000, 153.0625, .0625));
    }

    @Test
    void testEqualsWithAllowedDelta_1_oe_3_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertTrue(fun0.equals(152.9375, 153.0000, .0625));
    }

    @Test
    void testEqualsWithAllowedDelta_1_oe_4_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertFalse(fun0.equals(153.0000, 153.0625, .0624));
    }

    @Test
    void testEqualsWithAllowedDelta_1_oe_5_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertFalse(fun0.equals(152.9374, 153.0000, .0625));
    }

    @Test
    void testEqualsWithAllowedDelta_1_oe_6_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertEquals(nanAreEqual0, fun0.equals(Double.NaN, Double.NaN, 1.0));
    }

    @Test
    void testEqualsWithAllowedDelta_1_oe_7_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertTrue(fun0.equals(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 1.0));
    }

    @Test
    void testEqualsWithAllowedDelta_1_oe_8_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertTrue(fun0.equals(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 1.0));
    }

    @Test
    void testEqualsWithAllowedDelta_1_oe_9_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertFalse(fun0.equals(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1.0));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedDelta_1_oe_1_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        Assertions.assertTrue(fun0.equals(153.0000, 153.0000, .0625));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedDelta_1_oe_2_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertTrue(fun0.equals(153.0000, 153.0625, .0625));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedDelta_1_oe_3_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertTrue(fun0.equals(152.9375, 153.0000, .0625));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedDelta_1_oe_4_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertFalse(fun0.equals(153.0000, 153.0625, .0624));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedDelta_1_oe_5_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertFalse(fun0.equals(152.9374, 153.0000, .0625));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedDelta_1_oe_6_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertEquals(nanAreEqual0, fun0.equals(Double.NaN, Double.NaN, 1.0));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedDelta_1_oe_7_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertTrue(fun0.equals(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 1.0));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedDelta_1_oe_8_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertTrue(fun0.equals(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 1.0));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedDelta_1_oe_9_oe() {
                final EqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertFalse(fun0.equals(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1.0));
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_1_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        Assertions.assertTrue(fun0.equals(0.0, -0.0, 1));
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_2_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
                Assertions.assertTrue(fun0.equals(1.0, 1 + Math.ulp(1d), 1));
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_3_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
                Assertions.assertFalse(fun0.equals(1.0, 1 + 2 * Math.ulp(1d), 1));
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_4_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    Assertions.assertTrue(fun0.equals(value0, value0, 1));
    }
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_5_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    Assertions.assertTrue(fun0.equals(value0, Math.nextUp(value0), 1));
    }
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_6_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    Assertions.assertFalse(fun0.equals(value0, Math.nextUp(Math.nextUp(value0)), 1));
    }
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_7_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    Assertions.assertTrue(fun0.equals(value0, Math.nextDown(value0), 1));
    }
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_8_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    Assertions.assertFalse(fun0.equals(value0, Math.nextDown(Math.nextDown(value0)), 1));
    }
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_9_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertFalse(fun0.equals(value0, Math.nextUp(value0), 0));
    }
    }
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_10_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertTrue(fun0.equals(value0, Math.nextUp(Math.nextUp(value0)), 2));
    }
    }
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_11_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertTrue(fun0.equals(value0, Math.nextDown(Math.nextDown(value0)), 2));
    }
    }
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_12_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
                Assertions.assertTrue(fun0.equals(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 1));
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_13_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
                Assertions.assertTrue(fun0.equals(Double.MAX_VALUE, Double.POSITIVE_INFINITY, 1));
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_14_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
                Assertions.assertTrue(fun0.equals(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 1));
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_15_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
                Assertions.assertTrue(fun0.equals(-Double.MAX_VALUE, Double.NEGATIVE_INFINITY, 1));
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_16_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertEquals(nanAreEqual0, fun0.equals(Double.NaN, Double.NaN, 1));
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_17_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertEquals(nanAreEqual0, fun0.equals(Double.NaN, Double.NaN, 0));
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_18_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Double.NaN, 0, 0));
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_19_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(0, Double.NaN, 0));
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_20_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Double.NaN, Double.POSITIVE_INFINITY, 0));
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_21_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Double.NaN, Double.NEGATIVE_INFINITY, 0));
    }

    @Test
    void testEqualsWithAllowedUlps_1_oe_22_oe() {
                final EqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
        
                Assertions.assertFalse(fun0.equals(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 100000));
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_1_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        Assertions.assertTrue(fun0.equals(0.0, -0.0, 1));
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_2_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
                Assertions.assertTrue(fun0.equals(1.0, 1 + Math.ulp(1d), 1));
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_3_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
                Assertions.assertFalse(fun0.equals(1.0, 1 + 2 * Math.ulp(1d), 1));
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_4_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    Assertions.assertTrue(fun0.equals(value0, value0, 1));
    }
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_5_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    Assertions.assertTrue(fun0.equals(value0, Math.nextUp(value0), 1));
    }
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_6_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    Assertions.assertFalse(fun0.equals(value0, Math.nextUp(Math.nextUp(value0)), 1));
    }
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_7_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    Assertions.assertTrue(fun0.equals(value0, Math.nextDown(value0), 1));
    }
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_8_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    Assertions.assertFalse(fun0.equals(value0, Math.nextDown(Math.nextDown(value0)), 1));
    }
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_9_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertFalse(fun0.equals(value0, Math.nextUp(value0), 0));
    }
    }
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_10_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertTrue(fun0.equals(value0, Math.nextUp(Math.nextUp(value0)), 2));
    }
    }
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_11_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertTrue(fun0.equals(value0, Math.nextDown(Math.nextDown(value0)), 2));
    }
    }
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_12_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
                Assertions.assertTrue(fun0.equals(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 1));
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_13_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
                Assertions.assertTrue(fun0.equals(Double.MAX_VALUE, Double.POSITIVE_INFINITY, 1));
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_14_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
                Assertions.assertTrue(fun0.equals(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 1));
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_15_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
                Assertions.assertTrue(fun0.equals(-Double.MAX_VALUE, Double.NEGATIVE_INFINITY, 1));
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_16_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertEquals(nanAreEqual0, fun0.equals(Double.NaN, Double.NaN, 1));
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_17_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertEquals(nanAreEqual0, fun0.equals(Double.NaN, Double.NaN, 0));
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_18_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Double.NaN, 0, 0));
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_19_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(0, Double.NaN, 0));
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_20_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Double.NaN, Double.POSITIVE_INFINITY, 0));
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_21_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Double.NaN, Double.NEGATIVE_INFINITY, 0));
    }

    @Test
    void testEqualsWithImplicitAllowedUlpsOf1_1_oe_22_oe() {
                final EqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
        
                Assertions.assertFalse(fun0.equals(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 100000));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_1_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        Assertions.assertTrue(fun0.equals(0.0, -0.0, 1));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_2_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
                Assertions.assertTrue(fun0.equals(1.0, 1 + Math.ulp(1d), 1));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_3_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
                Assertions.assertFalse(fun0.equals(1.0, 1 + 2 * Math.ulp(1d), 1));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_4_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    Assertions.assertTrue(fun0.equals(value0, value0, 1));
    }
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_5_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    Assertions.assertTrue(fun0.equals(value0, Math.nextUp(value0), 1));
    }
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_6_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    Assertions.assertFalse(fun0.equals(value0, Math.nextUp(Math.nextUp(value0)), 1));
    }
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_7_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    Assertions.assertTrue(fun0.equals(value0, Math.nextDown(value0), 1));
    }
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_8_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    Assertions.assertFalse(fun0.equals(value0, Math.nextDown(Math.nextDown(value0)), 1));
    }
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_9_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertFalse(fun0.equals(value0, Math.nextUp(value0), 0));
    }
    }
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_10_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertTrue(fun0.equals(value0, Math.nextUp(Math.nextUp(value0)), 2));
    }
    }
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_11_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertTrue(fun0.equals(value0, Math.nextDown(Math.nextDown(value0)), 2));
    }
    }
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_12_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
                Assertions.assertTrue(fun0.equals(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 1));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_13_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
                Assertions.assertTrue(fun0.equals(Double.MAX_VALUE, Double.POSITIVE_INFINITY, 1));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_14_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
                Assertions.assertTrue(fun0.equals(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 1));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_15_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
                Assertions.assertTrue(fun0.equals(-Double.MAX_VALUE, Double.NEGATIVE_INFINITY, 1));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_16_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertEquals(nanAreEqual0, fun0.equals(Double.NaN, Double.NaN, 1));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_17_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertEquals(nanAreEqual0, fun0.equals(Double.NaN, Double.NaN, 0));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_18_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Double.NaN, 0, 0));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_19_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(0, Double.NaN, 0));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_20_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Double.NaN, Double.POSITIVE_INFINITY, 0));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_21_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Double.NaN, Double.NEGATIVE_INFINITY, 0));
    }

    @Test
    void testEqualsIncludingNaNWithAllowedUlps_1_oe_22_oe() {
                final EqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (double value0 : new double[] {153.0, -128.0, 0.0, 1.0}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
        
                Assertions.assertFalse(fun0.equals(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 100000));
    }

    @Test
    void testFloatEqualsWithAllowedDelta_1_oe_1_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        Assertions.assertTrue(fun0.equals(153.0000f, 153.0000f, .0625f));
    }

    @Test
    void testFloatEqualsWithAllowedDelta_1_oe_2_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertTrue(fun0.equals(153.0000f, 153.0625f, .0625f));
    }

    @Test
    void testFloatEqualsWithAllowedDelta_1_oe_3_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertTrue(fun0.equals(152.9375f, 153.0000f, .0625f));
    }

    @Test
    void testFloatEqualsWithAllowedDelta_1_oe_4_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertFalse(fun0.equals(153.0000f, 153.0625f, .0624f));
    }

    @Test
    void testFloatEqualsWithAllowedDelta_1_oe_5_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertFalse(fun0.equals(152.9374f, 153.0000f, .0625f));
    }

    @Test
    void testFloatEqualsWithAllowedDelta_1_oe_6_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertEquals(nanAreEqual0, fun0.equals(Float.NaN, Float.NaN, 1.0f));
    }

    @Test
    void testFloatEqualsWithAllowedDelta_1_oe_7_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertTrue(fun0.equals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 1.0f));
    }

    @Test
    void testFloatEqualsWithAllowedDelta_1_oe_8_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertTrue(fun0.equals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, 1.0f));
    }

    @Test
    void testFloatEqualsWithAllowedDelta_1_oe_9_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertFalse(fun0.equals(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 1.0f));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedDelta_1_oe_1_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        Assertions.assertTrue(fun0.equals(153.0000f, 153.0000f, .0625f));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedDelta_1_oe_2_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertTrue(fun0.equals(153.0000f, 153.0625f, .0625f));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedDelta_1_oe_3_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertTrue(fun0.equals(152.9375f, 153.0000f, .0625f));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedDelta_1_oe_4_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertFalse(fun0.equals(153.0000f, 153.0625f, .0624f));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedDelta_1_oe_5_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertFalse(fun0.equals(152.9374f, 153.0000f, .0625f));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedDelta_1_oe_6_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertEquals(nanAreEqual0, fun0.equals(Float.NaN, Float.NaN, 1.0f));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedDelta_1_oe_7_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertTrue(fun0.equals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 1.0f));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedDelta_1_oe_8_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertTrue(fun0.equals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, 1.0f));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedDelta_1_oe_9_oe() {
                final FloatEqualsWithDelta fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
                Assertions.assertFalse(fun0.equals(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 1.0f));
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_1_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        Assertions.assertTrue(fun0.equals(0.0f, -0.0f, 1));
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_2_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
                Assertions.assertTrue(fun0.equals(1.0f, 1f + Math.ulp(1f), 1));
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_3_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
                Assertions.assertFalse(fun0.equals(1.0f, 1f + 2 * Math.ulp(1f), 1));
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_4_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    Assertions.assertTrue(fun0.equals(value0, value0, 1));
    }
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_5_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    Assertions.assertTrue(fun0.equals(value0, Math.nextUp(value0), 1));
    }
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_6_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    Assertions.assertFalse(fun0.equals(value0, Math.nextUp(Math.nextUp(value0)), 1));
    }
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_7_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    Assertions.assertTrue(fun0.equals(value0, Math.nextDown(value0), 1));
    }
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_8_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    Assertions.assertFalse(fun0.equals(value0, Math.nextDown(Math.nextDown(value0)), 1));
    }
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_9_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertFalse(fun0.equals(value0, Math.nextUp(value0), 0));
    }
    }
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_10_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertTrue(fun0.equals(value0, Math.nextUp(Math.nextUp(value0)), 2));
    }
    }
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_11_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertTrue(fun0.equals(value0, Math.nextDown(Math.nextDown(value0)), 2));
    }
    }
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_12_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
                Assertions.assertTrue(fun0.equals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 1));
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_13_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
                Assertions.assertTrue(fun0.equals(Float.MAX_VALUE, Float.POSITIVE_INFINITY, 1));
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_14_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
                Assertions.assertTrue(fun0.equals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, 1));
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_15_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
                Assertions.assertTrue(fun0.equals(-Float.MAX_VALUE, Float.NEGATIVE_INFINITY, 1));
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_16_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertEquals(nanAreEqual0, fun0.equals(Float.NaN, Float.NaN, 1));
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_17_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertEquals(nanAreEqual0, fun0.equals(Float.NaN, Float.NaN, 0));
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_18_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Float.NaN, 0, 0));
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_19_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(0, Float.NaN, 0));
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_20_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Float.NaN, Float.POSITIVE_INFINITY, 0));
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_21_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Float.NaN, Float.NEGATIVE_INFINITY, 0));
    }

    @Test
    void testFloatEqualsWithAllowedUlps_1_oe_22_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equals;
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
        
                Assertions.assertFalse(fun0.equals(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 100000));
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_1_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        Assertions.assertTrue(fun0.equals(0.0f, -0.0f, 1));
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_2_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
                Assertions.assertTrue(fun0.equals(1.0f, 1f + Math.ulp(1f), 1));
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_3_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
                Assertions.assertFalse(fun0.equals(1.0f, 1f + 2 * Math.ulp(1f), 1));
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_4_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    Assertions.assertTrue(fun0.equals(value0, value0, 1));
    }
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_5_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    Assertions.assertTrue(fun0.equals(value0, Math.nextUp(value0), 1));
    }
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_6_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    Assertions.assertFalse(fun0.equals(value0, Math.nextUp(Math.nextUp(value0)), 1));
    }
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_7_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    Assertions.assertTrue(fun0.equals(value0, Math.nextDown(value0), 1));
    }
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_8_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    Assertions.assertFalse(fun0.equals(value0, Math.nextDown(Math.nextDown(value0)), 1));
    }
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_9_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertFalse(fun0.equals(value0, Math.nextUp(value0), 0));
    }
    }
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_10_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertTrue(fun0.equals(value0, Math.nextUp(Math.nextUp(value0)), 2));
    }
    }
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_11_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertTrue(fun0.equals(value0, Math.nextDown(Math.nextDown(value0)), 2));
    }
    }
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_12_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
                Assertions.assertTrue(fun0.equals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 1));
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_13_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
                Assertions.assertTrue(fun0.equals(Float.MAX_VALUE, Float.POSITIVE_INFINITY, 1));
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_14_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
                Assertions.assertTrue(fun0.equals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, 1));
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_15_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
                Assertions.assertTrue(fun0.equals(-Float.MAX_VALUE, Float.NEGATIVE_INFINITY, 1));
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_16_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertEquals(nanAreEqual0, fun0.equals(Float.NaN, Float.NaN, 1));
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_17_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertEquals(nanAreEqual0, fun0.equals(Float.NaN, Float.NaN, 0));
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_18_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Float.NaN, 0, 0));
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_19_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(0, Float.NaN, 0));
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_20_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Float.NaN, Float.POSITIVE_INFINITY, 0));
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_21_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Float.NaN, Float.NEGATIVE_INFINITY, 0));
    }

    @Test
    void testFloatEqualsWithImplicitAllowedUlpsOf1_1_oe_22_oe() {
                final FloatEqualsWithUlps fun0 = (a, b, ulp) -> Precision.equals(a, b);
        final boolean nanAreEqual0 = false;
        final boolean fixed1Ulp0 = true;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
        
                Assertions.assertFalse(fun0.equals(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 100000));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_1_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        Assertions.assertTrue(fun0.equals(0.0f, -0.0f, 1));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_2_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
                Assertions.assertTrue(fun0.equals(1.0f, 1f + Math.ulp(1f), 1));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_3_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
                Assertions.assertFalse(fun0.equals(1.0f, 1f + 2 * Math.ulp(1f), 1));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_4_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    Assertions.assertTrue(fun0.equals(value0, value0, 1));
    }
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_5_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    Assertions.assertTrue(fun0.equals(value0, Math.nextUp(value0), 1));
    }
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_6_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    Assertions.assertFalse(fun0.equals(value0, Math.nextUp(Math.nextUp(value0)), 1));
    }
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_7_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    Assertions.assertTrue(fun0.equals(value0, Math.nextDown(value0), 1));
    }
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_8_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    Assertions.assertFalse(fun0.equals(value0, Math.nextDown(Math.nextDown(value0)), 1));
    }
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_9_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertFalse(fun0.equals(value0, Math.nextUp(value0), 0));
    }
    }
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_10_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertTrue(fun0.equals(value0, Math.nextUp(Math.nextUp(value0)), 2));
    }
    }
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_11_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                        Assertions.assertTrue(fun0.equals(value0, Math.nextDown(Math.nextDown(value0)), 2));
    }
    }
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_12_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
                Assertions.assertTrue(fun0.equals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 1));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_13_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
                Assertions.assertTrue(fun0.equals(Float.MAX_VALUE, Float.POSITIVE_INFINITY, 1));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_14_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
                Assertions.assertTrue(fun0.equals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, 1));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_15_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
                Assertions.assertTrue(fun0.equals(-Float.MAX_VALUE, Float.NEGATIVE_INFINITY, 1));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_16_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertEquals(nanAreEqual0, fun0.equals(Float.NaN, Float.NaN, 1));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_17_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertEquals(nanAreEqual0, fun0.equals(Float.NaN, Float.NaN, 0));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_18_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Float.NaN, 0, 0));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_19_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(0, Float.NaN, 0));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_20_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Float.NaN, Float.POSITIVE_INFINITY, 0));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_21_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
                Assertions.assertFalse(fun0.equals(Float.NaN, Float.NEGATIVE_INFINITY, 0));
    }

    @Test
    void testFloatEqualsIncludingNaNWithAllowedUlps_1_oe_22_oe() {
                final FloatEqualsWithUlps fun0 = Precision::equalsIncludingNaN;
        final boolean nanAreEqual0 = true;
        final boolean fixed1Ulp0 = false;
        
        
                for (float value0 : new float[] {153.0f, -128.0f, 0.0f, 1.0f}) {
                    if (!fixed1Ulp0) {
                    }
                }
        
        
        
        
                Assertions.assertFalse(fun0.equals(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 100000));
    }

}
