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
import org.apache.commons.geometry.core.partitioning.HyperplaneSubset;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.spherical.SphericalTestUtils;
import org.apache.commons.geometry.spherical.oned.AngularInterval;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.apache.commons.geometry.spherical.oned.RegionBSPTree1S;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmbeddedTreeSubGreatCircleTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static final GreatCircle XY_CIRCLE = GreatCircles.fromPoleAndU(
            Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

    @Test
    void testToSubspace() {
        // arrange
        final EmbeddedTreeGreatCircleSubset sub = new EmbeddedTreeGreatCircleSubset(XY_CIRCLE);

        // act/assert
        SphericalTestUtils.assertPointsEqual(Point1S.of(1), sub.toSubspace(Point2S.of(1, 0.5)), TEST_EPS);
        SphericalTestUtils.assertPointsEqual(Point1S.of(1), sub.toSubspace(Point2S.of(1, 0.75)), TEST_EPS);
    }

    @Test
    void testToSpace() {
        // arrange
        final EmbeddedTreeGreatCircleSubset sub = new EmbeddedTreeGreatCircleSubset(XY_CIRCLE);

        // act/assert
        SphericalTestUtils.assertPointsEqual(Point2S.of(0, 0.5 * Math.PI), sub.toSpace(Point1S.of(0)), TEST_EPS);
        SphericalTestUtils.assertPointsEqual(Point2S.of(1, 0.5 * Math.PI), sub.toSpace(Point1S.of(1)), TEST_EPS);
    }

    @Test
    void testClosest() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.fromInterval(AngularInterval.of(1, 2, TEST_PRECISION));
        final EmbeddedTreeGreatCircleSubset sub = new EmbeddedTreeGreatCircleSubset(XY_CIRCLE, tree);

        final double halfPi = 0.5 * Math.PI;
        final double above = halfPi - 0.1;
        final double below = halfPi + 0.1;

        // act/assert
        SphericalTestUtils.assertPointsEq(Point2S.of(1, halfPi), sub.closest(Point2S.of(0, above)), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.of(1, halfPi), sub.closest(Point2S.of(0, below)), TEST_EPS);

        SphericalTestUtils.assertPointsEq(Point2S.of(1, halfPi), sub.closest(Point2S.of(1, above)), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.of(1, halfPi), sub.closest(Point2S.of(1, below)), TEST_EPS);

        SphericalTestUtils.assertPointsEq(Point2S.of(1.5, halfPi), sub.closest(Point2S.of(1.5, above)), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.of(1.5, halfPi), sub.closest(Point2S.of(1.5, below)), TEST_EPS);

        SphericalTestUtils.assertPointsEq(Point2S.of(2, halfPi), sub.closest(Point2S.of(2, above)), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.of(2, halfPi), sub.closest(Point2S.of(2, below)), TEST_EPS);

        SphericalTestUtils.assertPointsEq(Point2S.of(2, halfPi), sub.closest(Point2S.of(3, above)), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.of(2, halfPi), sub.closest(Point2S.of(3, below)), TEST_EPS);
    }

    @Test
    void testToString() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final EmbeddedTreeGreatCircleSubset sub = new EmbeddedTreeGreatCircleSubset(circle);

        // act
        final String str = sub.toString();

        // assert
        GeometryTestUtils.assertContains("EmbeddedTreeGreatCircleSubset[", str);
        GeometryTestUtils.assertContains("circle= GreatCircle[", str);
        GeometryTestUtils.assertContains("region= RegionBSPTree1S[", str);
    }

    private static void checkClassify(final HyperplaneSubset<Point2S> sub, final RegionLocation loc, final Point2S... pts) {
        for (final Point2S pt : pts) {
            Assertions.assertEquals(loc, sub.classify(pt), "Unexpected location for point " + pt);
        }
    }

    private static void checkArc(final GreatArc arc, final Point2S start, final Point2S end) {
        SphericalTestUtils.assertPointsEq(start, arc.getStartPoint(), TEST_EPS);
        SphericalTestUtils.assertPointsEq(end, arc.getEndPoint(), TEST_EPS);
    }

    @Test
    void testAdd_arc_differentCircle_1_oe() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoints(Point2S.MINUS_K, Point2S.MINUS_J, TEST_PRECISION);
        final GreatCircle otherCircle = GreatCircles.fromPoints(Point2S.MINUS_K,
                Point2S.of((1.5 * Math.PI) - 1e-2, Angle.PI_OVER_TWO), TEST_PRECISION);

        final EmbeddedTreeGreatCircleSubset sub = new EmbeddedTreeGreatCircleSubset(circle);
        // act/assert
        try {
    sub.add(otherCircle.arc(Point2S.PLUS_J, Point2S.of(1.5 * Math.PI, 0.75 * Math.PI)));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAdd_subGreatCircle_otherCircle_1_oe() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoints(Point2S.MINUS_K, Point2S.MINUS_J, TEST_PRECISION);
        final GreatCircle otherCircle = GreatCircles.fromPoints(Point2S.MINUS_K, Point2S.of((1.5 * Math.PI) - 1e-5, Angle.PI_OVER_TWO), TEST_PRECISION);

        final EmbeddedTreeGreatCircleSubset sub = new EmbeddedTreeGreatCircleSubset(circle);

        // act/assert
        try {
    sub.add(new EmbeddedTreeGreatCircleSubset(otherCircle, RegionBSPTree1S.full()));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
