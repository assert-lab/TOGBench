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
package org.apache.commons.geometry.euclidean.internal;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.euclidean.oned.Vector1D;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VectorsTest_OE25Dev {

    private static final double EPS = Math.ulp(1d);

    @Test
    void testIsRealNonZero_1_oe() {
        // act/assert
        Assertions.assertTrue(Vectors.isRealNonZero(1e-20));
    }

    @Test
    void testIsRealNonZero_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertTrue(Vectors.isRealNonZero(1e20));
    }

    @Test
    void testIsRealNonZero_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(Vectors.isRealNonZero(-1e-20));
    }

    @Test
    void testIsRealNonZero_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(Vectors.isRealNonZero(-1e20));
    }

    @Test
    void testIsRealNonZero_5_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(Vectors.isRealNonZero(0.0));
    }

    @Test
    void testIsRealNonZero_6_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(Vectors.isRealNonZero(-0.0));
    }

    @Test
    void testIsRealNonZero_7_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(Vectors.isRealNonZero(Double.NaN));
    }

    @Test
    void testIsRealNonZero_8_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(Vectors.isRealNonZero(Double.POSITIVE_INFINITY));
    }

    @Test
    void testIsRealNonZero_9_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(Vectors.isRealNonZero(Double.NEGATIVE_INFINITY));
    }

    @Test
    void testCheckedNorm_normArg_1_oe() {
        // act/assert
        Assertions.assertEquals(1.0, Vectors.checkedNorm(1.0), EPS);
    }

    @Test
    void testCheckedNorm_normArg_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertEquals(23.12, Vectors.checkedNorm(23.12), EPS);
    }

    @Test
    void testCheckedNorm_normArg_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(2e-12, Vectors.checkedNorm(2e-12), EPS);
    }

    @Test
    void testCheckedNorm_normArg_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-1.0, Vectors.checkedNorm(-1.0), EPS);
    }

    @Test
    void testCheckedNorm_normArg_5_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(-23.12, Vectors.checkedNorm(-23.12), EPS);
    }

    @Test
    void testCheckedNorm_normArg_6_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-2e-12, Vectors.checkedNorm(-2e-12), EPS);
    }

    @Test
    void testCheckedNorm_vectorArg_1_oe() {
        // act/assert
        Assertions.assertEquals(1.0, Vectors.checkedNorm(Vector1D.of(1.0)), EPS);
    }

    @Test
    void testCheckedNorm_vectorArg_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertEquals(23.12, Vectors.checkedNorm(Vector1D.of(23.12)), EPS);
    }

    @Test
    void testCheckedNorm_vectorArg_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(2e-12, Vectors.checkedNorm(Vector1D.of(2e-12)), EPS);
    }

    @Test
    void testCheckedNorm_vectorArg_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1.0, Vectors.checkedNorm(Vector1D.of(-1.0)), EPS);
    }

    @Test
    void testCheckedNorm_vectorArg_5_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(23.12, Vectors.checkedNorm(Vector1D.of(-23.12)), EPS);
    }

    @Test
    void testCheckedNorm_vectorArg_6_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(2e-12, Vectors.checkedNorm(Vector1D.of(-2e-12)), EPS);
    }

    @Test
    void testNorm_oneD_1_oe() {
        // act/assert
        Assertions.assertEquals(0.0, Vectors.norm(0.0), EPS);
    }

    @Test
    void testNorm_oneD_2_oe() {
        // act/assert
        // removed other assertion

        Assertions.assertEquals(2.0, Vectors.norm(-2.0), EPS);
    }

    @Test
    void testNorm_oneD_3_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1.0, Vectors.norm(-1.0), EPS);
    }

    @Test
    void testNorm_oneD_4_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1.0, Vectors.norm(1.0), EPS);
    }

    @Test
    void testNorm_oneD_5_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(2.0, Vectors.norm(2.0), EPS);
    }

    @Test
    void testNorm_twoD_1_oe() {
        // act/assert
        Assertions.assertEquals(0.0, Vectors.norm(0.0, 0.0), EPS);
    }

    @Test
    void testNorm_twoD_2_oe() {
        // act/assert
        // removed other assertion

        Assertions.assertEquals(Math.sqrt(5.0), Vectors.norm(1.0, 2.0), EPS);
    }

    @Test
    void testNorm_twoD_3_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(5.0, Vectors.norm(3.0, -4.0), EPS);
    }

    @Test
    void testNorm_twoD_4_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.sqrt(61.0), Vectors.norm(-5.0, 6.0), EPS);
    }

    @Test
    void testNorm_twoD_5_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.sqrt(130.0), Vectors.norm(-7.0, -9.0), EPS);
    }

    @Test
    void testNorm_threeD_1_oe() {
        // act/assert
        Assertions.assertEquals(0.0, Vectors.norm(0.0, 0.0, 0.0), EPS);
    }

    @Test
    void testNorm_threeD_2_oe() {
        // act/assert
        // removed other assertion

        Assertions.assertEquals(Math.sqrt(14.0), Vectors.norm(1.0, 2.0, 3.0), EPS);
    }

    @Test
    void testNorm_threeD_3_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(Math.sqrt(77.0), Vectors.norm(4.0, 5.0, -6.0), EPS);
    }

    @Test
    void testNorm_threeD_4_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.sqrt(194.0), Vectors.norm(7.0, -8.0, 9.0), EPS);
    }

    @Test
    void testNorm_threeD_5_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.sqrt(365.0), Vectors.norm(10.0, -11.0, -12.0), EPS);
    }

    @Test
    void testNorm_threeD_6_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.sqrt(590.0), Vectors.norm(-13.0, 14.0, 15.0), EPS);
    }

    @Test
    void testNorm_threeD_7_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.sqrt(869.0), Vectors.norm(-16.0, 17.0, -18.0), EPS);
    }

    @Test
    void testNorm_threeD_8_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.sqrt(1202.0), Vectors.norm(-19.0, -20.0, 21.0), EPS);
    }

    @Test
    void testNorm_threeD_9_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.sqrt(1589.0), Vectors.norm(-22.0, -23.0, -24.0), EPS);
    }

    @Test
    void testNormSq_oneD_1_oe() {
        // act/assert
        Assertions.assertEquals(0.0, Vectors.normSq(0.0), EPS);
    }

    @Test
    void testNormSq_oneD_2_oe() {
        // act/assert
        // removed other assertion

        Assertions.assertEquals(9.0, Vectors.normSq(-3.0), EPS);
    }

    @Test
    void testNormSq_oneD_3_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1.0, Vectors.normSq(-1.0), EPS);
    }

    @Test
    void testNormSq_oneD_4_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1.0, Vectors.normSq(1.0), EPS);
    }

    @Test
    void testNormSq_oneD_5_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(9.0, Vectors.normSq(3.0), EPS);
    }

    @Test
    void testNormSq_twoD_1_oe() {
        // act/assert
        Assertions.assertEquals(0.0, Vectors.normSq(0.0, 0.0), EPS);
    }

    @Test
    void testNormSq_twoD_2_oe() {
        // act/assert
        // removed other assertion

        Assertions.assertEquals(5.0, Vectors.normSq(1.0, 2.0), EPS);
    }

    @Test
    void testNormSq_twoD_3_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(25.0, Vectors.normSq(3.0, -4.0), EPS);
    }

    @Test
    void testNormSq_twoD_4_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(61.0, Vectors.normSq(-5.0, 6.0), EPS);
    }

    @Test
    void testNormSq_twoD_5_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(130.0, Vectors.normSq(-7.0, -9.0), EPS);
    }

    @Test
    void testNormSq_threeD_1_oe() {
        // act/assert
        Assertions.assertEquals(0.0, Vectors.normSq(0.0, 0.0, 0.0), EPS);
    }

    @Test
    void testNormSq_threeD_2_oe() {
        // act/assert
        // removed other assertion

        Assertions.assertEquals(14.0, Vectors.normSq(1.0, 2.0, 3.0), EPS);
    }

    @Test
    void testNormSq_threeD_3_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(77.0, Vectors.normSq(4.0, 5.0, -6.0), EPS);
    }

    @Test
    void testNormSq_threeD_4_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(194.0, Vectors.normSq(7.0, -8.0, 9.0), EPS);
    }

    @Test
    void testNormSq_threeD_5_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(365.0, Vectors.normSq(10.0, -11.0, -12.0), EPS);
    }

    @Test
    void testNormSq_threeD_6_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(590.0, Vectors.normSq(-13.0, 14.0, 15.0), EPS);
    }

    @Test
    void testNormSq_threeD_7_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(869.0, Vectors.normSq(-16.0, 17.0, -18.0), EPS);
    }

    @Test
    void testNormSq_threeD_8_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1202.0, Vectors.normSq(-19.0, -20.0, 21.0), EPS);
    }

    @Test
    void testNormSq_threeD_9_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1589.0, Vectors.normSq(-22.0, -23.0, -24.0), EPS);
    }

@Test
    void testCheckedNorm_normArg_7_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> Vectors.checkedNorm(0.0), IllegalArgumentException.class, "Illegal norm: 0.0");
    }

@Test
    void testCheckedNorm_normArg_8_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        GeometryTestUtils.assertThrowsWithMessage(() -> Vectors.checkedNorm(Double.NaN), IllegalArgumentException.class, "Illegal norm: NaN");
    }

@Test
    void testCheckedNorm_normArg_9_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        GeometryTestUtils.assertThrowsWithMessage(() -> Vectors.checkedNorm(Double.POSITIVE_INFINITY), IllegalArgumentException.class, "Illegal norm: Infinity");
    }

@Test
    void testCheckedNorm_normArg_10_oe() {
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
        GeometryTestUtils.assertThrowsWithMessage(() -> Vectors.checkedNorm(Double.NEGATIVE_INFINITY), IllegalArgumentException.class, "Illegal norm: -Infinity");
    }

@Test
    void testCheckedNorm_vectorArg_7_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertThrowsWithMessage(() -> Vectors.checkedNorm(Vector3D.ZERO), IllegalArgumentException.class, "Illegal norm: 0.0");
    }

@Test
    void testCheckedNorm_vectorArg_8_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        GeometryTestUtils.assertThrowsWithMessage(() -> Vectors.checkedNorm(Vector3D.NaN), IllegalArgumentException.class, "Illegal norm: NaN");
    }

@Test
    void testCheckedNorm_vectorArg_9_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        GeometryTestUtils.assertThrowsWithMessage(() -> Vectors.checkedNorm(Vector3D.POSITIVE_INFINITY), IllegalArgumentException.class, "Illegal norm: Infinity");
    }

@Test
    void testCheckedNorm_vectorArg_10_oe() {
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
        GeometryTestUtils.assertThrowsWithMessage(() -> Vectors.checkedNorm(Vector3D.NEGATIVE_INFINITY), IllegalArgumentException.class, "Illegal norm: Infinity");
    }

}
