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

import java.util.regex.Pattern;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.partitioning.HyperplaneLocation;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.spherical.SphericalTestUtils;
import org.apache.commons.geometry.spherical.oned.AngularInterval;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GreatCircleTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static final Vector3D.Unit X = Vector3D.Unit.PLUS_X;
    private static final Vector3D.Unit Y = Vector3D.Unit.PLUS_Y;
    private static final Vector3D.Unit Z = Vector3D.Unit.PLUS_Z;

    @Test
    void testFromPole() {
        // act/assert
        checkGreatCircle(GreatCircles.fromPole(X, TEST_PRECISION), X, Z);
        checkGreatCircle(GreatCircles.fromPole(Y, TEST_PRECISION), Y, Z.negate());
        checkGreatCircle(GreatCircles.fromPole(Z, TEST_PRECISION), Z, Y);
    }

    @Test
    void testFromPoleAndXAxis() {
        // act/assert
        checkGreatCircle(GreatCircles.fromPoleAndU(X, Y, TEST_PRECISION), X, Y);
        checkGreatCircle(GreatCircles.fromPoleAndU(X, Z, TEST_PRECISION), X, Z);
        checkGreatCircle(GreatCircles.fromPoleAndU(Y, Z, TEST_PRECISION), Y, Z);
    }

    @Test
    void testFromPoints() {
        // act/assert
        checkGreatCircle(GreatCircles.fromPoints(
                    Point2S.of(0, Angle.PI_OVER_TWO),
                    Point2S.of(Angle.PI_OVER_TWO, Angle.PI_OVER_TWO),
                    TEST_PRECISION),
                Z, X);

        checkGreatCircle(GreatCircles.fromPoints(
                Point2S.of(0, Angle.PI_OVER_TWO),
                Point2S.of(-0.1 * Math.PI, Angle.PI_OVER_TWO),
                TEST_PRECISION),
            Z.negate(), X);

        checkGreatCircle(GreatCircles.fromPoints(
                Point2S.of(0, Angle.PI_OVER_TWO),
                Point2S.of(1.5 * Math.PI, Angle.PI_OVER_TWO),
                TEST_PRECISION),
            Z.negate(), X);

        checkGreatCircle(GreatCircles.fromPoints(
                Point2S.of(0, 0),
                Point2S.of(0, Angle.PI_OVER_TWO),
                TEST_PRECISION),
            Y, Z);
    }

    @Test
    void testVectorAt() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act/assert
        SphericalTestUtils.assertVectorsEqual(Vector3D.Unit.PLUS_Z, circle.vectorAt(0.0), TEST_EPS);
        SphericalTestUtils.assertVectorsEqual(Vector3D.Unit.PLUS_Y, circle.vectorAt(Angle.PI_OVER_TWO), TEST_EPS);
        SphericalTestUtils.assertVectorsEqual(Vector3D.Unit.MINUS_Z, circle.vectorAt(Math.PI), TEST_EPS);
        SphericalTestUtils.assertVectorsEqual(Vector3D.Unit.MINUS_Y, circle.vectorAt(-Angle.PI_OVER_TWO), TEST_EPS);
        SphericalTestUtils.assertVectorsEqual(Vector3D.Unit.PLUS_Z, circle.vectorAt(Angle.TWO_PI), TEST_EPS);
    }

    @Test
    void testProject() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act/assert
        SphericalTestUtils.assertPointsEqual(Point2S.of(Angle.PI_OVER_TWO, Angle.PI_OVER_TWO),
                circle.project(Point2S.of(Angle.PI_OVER_TWO, Angle.PI_OVER_TWO)), TEST_EPS);
        SphericalTestUtils.assertPointsEqual(Point2S.of(Angle.PI_OVER_TWO, Angle.PI_OVER_TWO),
                circle.project(Point2S.of(Angle.PI_OVER_TWO + 1, Angle.PI_OVER_TWO)), TEST_EPS);
        SphericalTestUtils.assertPointsEqual(Point2S.of(Angle.PI_OVER_TWO, Angle.PI_OVER_TWO),
                circle.project(Point2S.of(Angle.PI_OVER_TWO - 1, Angle.PI_OVER_TWO)), TEST_EPS);

        SphericalTestUtils.assertPointsEqual(Point2S.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO),
                circle.project(Point2S.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO)), TEST_EPS);
        SphericalTestUtils.assertPointsEqual(Point2S.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO),
                circle.project(Point2S.of(-Angle.PI_OVER_TWO + 1, Angle.PI_OVER_TWO)), TEST_EPS);
        SphericalTestUtils.assertPointsEqual(Point2S.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO),
                circle.project(Point2S.of(-Angle.PI_OVER_TWO - 1, Angle.PI_OVER_TWO)), TEST_EPS);
    }

    @Test
    void testProject_poles() {
        // arrange
        final GreatCircle minusXCircle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final GreatCircle plusZCircle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_Y, TEST_PRECISION);

        // act
        SphericalTestUtils.assertPointsEqual(Point2S.of(0.0, 0.0),
                minusXCircle.project(Point2S.from(Vector3D.Unit.MINUS_X)), TEST_EPS);
        SphericalTestUtils.assertPointsEqual(Point2S.of(0.0, 0.0),
                minusXCircle.project(Point2S.from(Vector3D.Unit.PLUS_X)), TEST_EPS);

        SphericalTestUtils.assertPointsEqual(Point2S.of(1.5 * Math.PI, Angle.PI_OVER_TWO),
                plusZCircle.project(Point2S.from(Vector3D.Unit.PLUS_Z)), TEST_EPS);
        SphericalTestUtils.assertPointsEqual(Point2S.of(1.5 * Math.PI, Angle.PI_OVER_TWO),
                plusZCircle.project(Point2S.from(Vector3D.Unit.MINUS_Z)), TEST_EPS);
    }

    @Test
    void testReverse() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final GreatCircle reverse = circle.reverse();

        // assert
        checkGreatCircle(reverse, Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X);
    }

    @Test
    void testIntersection() {
        // arrange
        final GreatCircle a = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPole(Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPole(Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act/assert
        SphericalTestUtils.assertVectorsEqual(Vector3D.Unit.PLUS_Z,
                a.intersection(b).getVector(), TEST_EPS);
        SphericalTestUtils.assertVectorsEqual(Vector3D.Unit.MINUS_Z,
                b.intersection(a).getVector(), TEST_EPS);

        SphericalTestUtils.assertVectorsEqual(Vector3D.Unit.PLUS_X,
                b.intersection(c).getVector(), TEST_EPS);
        SphericalTestUtils.assertVectorsEqual(Vector3D.Unit.MINUS_X,
                c.intersection(b).getVector(), TEST_EPS);
    }

    @Test
    void testToSubspace() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_Z, TEST_PRECISION);

        // act/assert
        SphericalTestUtils.assertPointsEqual(Point1S.ZERO,
                circle.toSubspace(Point2S.from(Vector3D.Unit.MINUS_Z)), TEST_EPS);

        SphericalTestUtils.assertPointsEqual(Point1S.of(0.25 * Math.PI),
                circle.toSubspace(Point2S.from(Vector3D.of(-1, -1, -1))), TEST_EPS);
        SphericalTestUtils.assertPointsEqual(Point1S.of(0.75 * Math.PI),
                circle.toSubspace(Point2S.from(Vector3D.of(-1, 1, 1))), TEST_EPS);
        SphericalTestUtils.assertPointsEqual(Point1S.of(1.25 * Math.PI),
                circle.toSubspace(Point2S.from(Vector3D.of(1, -1, 1))), TEST_EPS);
        SphericalTestUtils.assertPointsEqual(Point1S.of(1.75 * Math.PI),
                circle.toSubspace(Point2S.from(Vector3D.of(1, 1, -1))), TEST_EPS);

        SphericalTestUtils.assertPointsEqual(Point1S.ZERO,
                circle.toSubspace(Point2S.from(Vector3D.Unit.PLUS_Y)), TEST_EPS);
        SphericalTestUtils.assertPointsEqual(Point1S.ZERO,
                circle.toSubspace(Point2S.from(Vector3D.Unit.MINUS_Y)), TEST_EPS);
    }

    @Test
    void testToSpace() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_Z, TEST_PRECISION);

        // act/assert
        SphericalTestUtils.assertPointsEqual(Point2S.from(Vector3D.Unit.MINUS_Z),
                circle.toSpace(Point1S.ZERO), TEST_EPS);

        SphericalTestUtils.assertPointsEqual(Point2S.from(Vector3D.of(-1, 0, -1)),
                circle.toSpace(Point1S.of(0.25 * Math.PI)), TEST_EPS);
        SphericalTestUtils.assertPointsEqual(Point2S.from(Vector3D.of(-1, 0, 1)),
                circle.toSpace(Point1S.of(0.75 * Math.PI)), TEST_EPS);
        SphericalTestUtils.assertPointsEqual(Point2S.from(Vector3D.of(1, 0, 1)),
                circle.toSpace(Point1S.of(1.25 * Math.PI)), TEST_EPS);
        SphericalTestUtils.assertPointsEqual(Point2S.from(Vector3D.of(1, 0, -1)),
                circle.toSpace(Point1S.of(1.75 * Math.PI)), TEST_EPS);
    }

    @Test
    void testToString() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final String str = circle.toString();

        // assert
        GeometryTestUtils.assertContains("GreatCircle[", str);
        GeometryTestUtils.assertContains("pole= (0.0, 0.0, 1.0)", str);
        GeometryTestUtils.assertContains("u= (1.0, 0.0, 0.0)", str);
        GeometryTestUtils.assertContains("v= (0.0, 1.0, 0.0)", str);
    }

    private static void checkGreatCircle(final GreatCircle circle, final Vector3D pole, final Vector3D u) {
        SphericalTestUtils.assertVectorsEqual(pole, circle.getPole(), TEST_EPS);
        SphericalTestUtils.assertVectorsEqual(pole, circle.getW(), TEST_EPS);
        SphericalTestUtils.assertVectorsEqual(u, circle.getU(), TEST_EPS);
        SphericalTestUtils.assertVectorsEqual(pole.cross(u), circle.getV(), TEST_EPS);

        final Point2S plusPolePt = Point2S.from(circle.getPole());
        final Point2S minusPolePt = Point2S.from(circle.getPole().negate());
        final Point2S origin = Point2S.from(circle.getU());

        SphericalTestUtils.assertPointsEqual(plusPolePt, circle.getPolePoint(), TEST_EPS);

        Assertions.assertFalse(circle.contains(plusPolePt));
        Assertions.assertFalse(circle.contains(minusPolePt));
        Assertions.assertTrue(circle.contains(origin));

        Assertions.assertEquals(HyperplaneLocation.MINUS, circle.classify(plusPolePt));
        Assertions.assertEquals(HyperplaneLocation.PLUS, circle.classify(minusPolePt));
        Assertions.assertEquals(HyperplaneLocation.ON, circle.classify(origin));
    }

    private static void checkArc(final GreatArc arc, final Point2S start, final Point2S end) {
        SphericalTestUtils.assertPointsEq(start, arc.getStartPoint(), TEST_EPS);
        SphericalTestUtils.assertPointsEq(end, arc.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromPoints_invalidPoints_4_oe() {
        // arrange
        final Point2S p1 = Point2S.of(0, Angle.PI_OVER_TWO);
        final Point2S p2 = Point2S.of(Math.PI, Angle.PI_OVER_TWO);

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    GreatCircles.fromPoints(p1, Point2S.NaN, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromPoints_invalidPoints_5_oe() {
        // arrange
        final Point2S p1 = Point2S.of(0, Angle.PI_OVER_TWO);
        final Point2S p2 = Point2S.of(Math.PI, Angle.PI_OVER_TWO);

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        try {
    GreatCircles.fromPoints(Point2S.NaN, p2, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromPoints_invalidPoints_6_oe() {
        // arrange
        final Point2S p1 = Point2S.of(0, Angle.PI_OVER_TWO);
        final Point2S p2 = Point2S.of(Math.PI, Angle.PI_OVER_TWO);

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        try {
    GreatCircles.fromPoints(p1, Point2S.of(Double.POSITIVE_INFINITY, Angle.PI_OVER_TWO), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromPoints_invalidPoints_7_oe() {
        // arrange
        final Point2S p1 = Point2S.of(0, Angle.PI_OVER_TWO);
        final Point2S p2 = Point2S.of(Math.PI, Angle.PI_OVER_TWO);

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    GreatCircles.fromPoints(Point2S.of(Double.POSITIVE_INFINITY, Angle.PI_OVER_TWO), p2, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
