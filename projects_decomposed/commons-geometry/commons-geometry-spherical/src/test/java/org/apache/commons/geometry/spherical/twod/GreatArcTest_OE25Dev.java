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

import java.util.List;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.spherical.SphericalTestUtils;
import org.apache.commons.geometry.spherical.oned.AngularInterval;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GreatArcTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testFromPoints_usesShortestPath() {
        // act/assert
        SphericalTestUtils.assertVectorsEqual(
                Vector3D.Unit.MINUS_Y,
                GreatCircles.arcFromPoints(
                        Point2S.PLUS_I,
                        Point2S.of(Math.PI, Angle.PI_OVER_TWO - 1e-5),
                        TEST_PRECISION).getCircle().getPole(), TEST_EPS);

        SphericalTestUtils.assertVectorsEqual(
                Vector3D.Unit.PLUS_Y,
                GreatCircles.arcFromPoints(
                        Point2S.PLUS_I,
                        Point2S.of(Math.PI, Angle.PI_OVER_TWO + 1e-5),
                        TEST_PRECISION).getCircle().getPole(), TEST_EPS);
    }

    @Test
    void testGetMidPoint() {
        // arrange
        final GreatArc arc = GreatCircles.arcFromInterval(
                GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION),
                AngularInterval.Convex.of(0.8 * Math.PI, 0.9 * Math.PI, TEST_PRECISION));

        // act/assert
        SphericalTestUtils.assertPointsEqual(Point2S.of(0.85 * Math.PI, 0.5 * Math.PI), arc.getMidPoint(), TEST_EPS);
    }

    @Test
    void testReverse() {
        // arrange
        final GreatArc arc = GreatCircles.arcFromInterval(
                GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.MINUS_I, TEST_PRECISION),
                AngularInterval.Convex.of(Angle.PI_OVER_TWO, Math.PI, TEST_PRECISION));

        // act
        final GreatArc result = arc.reverse();

        // assert
        checkGreatCircle(result.getCircle(), Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_Y);

        checkArc(result, Point2S.MINUS_J, Point2S.MINUS_I);
    }

    @Test
    void testTransform() {
        // arrange
        final GreatArc arc = GreatCircles.fromPoints(Point2S.PLUS_K, Point2S.MINUS_I, TEST_PRECISION)
                .arc(Math.PI, -Angle.PI_OVER_TWO);

        final Transform2S t = Transform2S.createRotation(Point2S.PLUS_I, Angle.PI_OVER_TWO)
                .reflect(Point2S.of(-0.25 * Math.PI,  Angle.PI_OVER_TWO));

        // act
        final GreatArc result = arc.transform(t);

        // assert
        checkArc(result, Point2S.PLUS_I, Point2S.PLUS_J);
    }

    @Test
    void testToString_full() {
        // arrange
        final GreatArc arc = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION).span();

        // act
        final String str = arc.toString();

        // assert
        GeometryTestUtils.assertContains("GreatArc[", str);
        GeometryTestUtils.assertContains("full= true", str);
        GeometryTestUtils.assertContains("circle= GreatCircle[", str);
    }

    @Test
    void testToString_notFull() {
        // arrange
        final GreatArc arc = GreatCircles.arcFromInterval(
                GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION),
                AngularInterval.Convex.of(1, 2, TEST_PRECISION));

        // act
        final String str = arc.toString();

        // assert
        GeometryTestUtils.assertContains("GreatArc[", str);
        GeometryTestUtils.assertContains("start= (", str);
        GeometryTestUtils.assertContains("end= (", str);
    }

    private static void checkClassify(final GreatArc arc, final RegionLocation loc, final Point2S... pts) {
        for (final Point2S pt : pts) {
            Assertions.assertEquals(loc, arc.classify(pt), "Unexpected location for point " + pt);
        }
    }

    private static void checkArc(final GreatArc arc, final Point2S start, final Point2S end) {
        SphericalTestUtils.assertPointsEq(start, arc.getStartPoint(), TEST_EPS);
        SphericalTestUtils.assertPointsEq(end, arc.getEndPoint(), TEST_EPS);

        checkClassify(arc, RegionLocation.BOUNDARY, start, end);

        final Point2S mid = arc.getCircle().toSpace(arc.getInterval().getMidPoint());

        checkClassify(arc, RegionLocation.INSIDE, mid);
        checkClassify(arc, RegionLocation.OUTSIDE, mid.antipodal());

        Assertions.assertEquals(start.distance(end), arc.getSize(), TEST_EPS);
        SphericalTestUtils.assertPointsEq(mid, arc.getCentroid(), TEST_EPS);
    }

    private static void checkGreatCircle(final GreatCircle circle, final Vector3D pole, final Vector3D x) {
        SphericalTestUtils.assertVectorsEqual(pole, circle.getPole(), TEST_EPS);
        SphericalTestUtils.assertVectorsEqual(x, circle.getU(), TEST_EPS);
        SphericalTestUtils.assertVectorsEqual(pole.cross(x), circle.getV(), TEST_EPS);
    }

    @Test
    void testFromPoints_invalidPoints_1_oe() {
        // act/assert
        try {
    GreatCircles.arcFromPoints(Point2S.PLUS_I, Point2S.of(1e-12, Angle.PI_OVER_TWO), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromPoints_invalidPoints_2_oe() {
        // act/assert
        // removed other assertion
        try {
    GreatCircles.arcFromPoints(Point2S.PLUS_I, Point2S.MINUS_I, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
