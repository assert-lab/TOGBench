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
package org.apache.commons.geometry.spherical.twod;


import java.util.Comparator;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.spherical.SphericalTestUtils;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class Point2STest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    @Test
    void testFrom_vector() {
        // arrange
        final double quarterPi = 0.25 * Math.PI;

        // act/assert
        checkPoint(Point2S.from(Vector3D.of(1, 1, 0)), quarterPi, Angle.PI_OVER_TWO);
        checkPoint(Point2S.from(Vector3D.of(1, 0, 1)), 0, quarterPi);
        checkPoint(Point2S.from(Vector3D.of(0, 1, 1)), Angle.PI_OVER_TWO, quarterPi);

        checkPoint(Point2S.from(Vector3D.of(1, -1, 0)), Angle.TWO_PI - quarterPi, Angle.PI_OVER_TWO);
        checkPoint(Point2S.from(Vector3D.of(-1, 0, -1)), Math.PI, Math.PI - quarterPi);
        checkPoint(Point2S.from(Vector3D.of(0, -1, -1)), Angle.TWO_PI - Angle.PI_OVER_TWO, Math.PI - quarterPi);
    }

    @Test
    void testSlerp_alongEquator() {
        // arrange
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;

        // act/assert
        SphericalTestUtils.assertPointsEq(p1, p1.slerp(p2, 0), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.of(0.25 * Angle.PI_OVER_TWO, Angle.PI_OVER_TWO), p1.slerp(p2, 0.25), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.of(0.5 * Angle.PI_OVER_TWO, Angle.PI_OVER_TWO), p1.slerp(p2, 0.5), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.of(0.75 * Angle.PI_OVER_TWO, Angle.PI_OVER_TWO), p1.slerp(p2, 0.75), TEST_EPS);
        SphericalTestUtils.assertPointsEq(p2, p1.slerp(p2, 1), TEST_EPS);

        SphericalTestUtils.assertPointsEq(p2, p2.slerp(p1, 0), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.of(0.75 * Angle.PI_OVER_TWO, Angle.PI_OVER_TWO), p2.slerp(p1, 0.25), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.of(0.5 * Angle.PI_OVER_TWO, Angle.PI_OVER_TWO), p2.slerp(p1, 0.5), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.of(0.25 * Angle.PI_OVER_TWO, Angle.PI_OVER_TWO), p2.slerp(p1, 0.75), TEST_EPS);
        SphericalTestUtils.assertPointsEq(p1, p2.slerp(p1, 1), TEST_EPS);

        SphericalTestUtils.assertPointsEq(Point2S.MINUS_I, p1.slerp(p2, 2), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.MINUS_J, p1.slerp(p2, -1), TEST_EPS);
    }

    @Test
    void testSlerp_alongMeridian() {
        // arrange
        final Point2S p1 = Point2S.PLUS_J;
        final Point2S p2 = Point2S.PLUS_K;

        // act/assert
        SphericalTestUtils.assertPointsEq(p1, p1.slerp(p2, 0), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.of(Angle.PI_OVER_TWO, 0.75 * Angle.PI_OVER_TWO), p1.slerp(p2, 0.25), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.of(Angle.PI_OVER_TWO, 0.5 * Angle.PI_OVER_TWO), p1.slerp(p2, 0.5), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.of(Angle.PI_OVER_TWO, 0.25 * Angle.PI_OVER_TWO), p1.slerp(p2, 0.75), TEST_EPS);
        SphericalTestUtils.assertPointsEq(p2, p1.slerp(p2, 1), TEST_EPS);

        SphericalTestUtils.assertPointsEq(p2, p2.slerp(p1, 0), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.of(Angle.PI_OVER_TWO, 0.25 * Angle.PI_OVER_TWO), p2.slerp(p1, 0.25), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.of(Angle.PI_OVER_TWO, 0.5 * Angle.PI_OVER_TWO), p2.slerp(p1, 0.5), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.of(Angle.PI_OVER_TWO, 0.75 * Angle.PI_OVER_TWO), p2.slerp(p1, 0.75), TEST_EPS);
        SphericalTestUtils.assertPointsEq(p1, p2.slerp(p1, 1), TEST_EPS);

        SphericalTestUtils.assertPointsEq(Point2S.MINUS_J, p1.slerp(p2, 2), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.MINUS_K, p1.slerp(p2, -1), TEST_EPS);
    }

    @Test
    void testSlerp_samePoint() {
        // arrange
        final Point2S p1 = Point2S.PLUS_I;

        // act/assert
        SphericalTestUtils.assertPointsEq(p1, p1.slerp(p1, 0), TEST_EPS);
        SphericalTestUtils.assertPointsEq(p1, p1.slerp(p1, 0.5), TEST_EPS);
        SphericalTestUtils.assertPointsEq(p1, p1.slerp(p1, 1), TEST_EPS);
    }

    @Test
    void testParse() {
        // act/assert
        checkPoint(Point2S.parse("(0,0)"), 0.0, 0.0);
        checkPoint(Point2S.parse("(1,2)"), 1.0, 2.0);
    }

    private static void checkPoint(final Point2S p, final double az, final double polar) {
        final String msg = "Expected (" + az + "," + polar + ") but was " + p;

        Assertions.assertEquals(az, p.getAzimuth(), TEST_EPS, msg);
        Assertions.assertEquals(polar, p.getPolar(), TEST_EPS, msg);
    }

    @Test
    void testParse_failure_1_oe() {
        // act/assert
        try {
    Point2S.parse("abc");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
