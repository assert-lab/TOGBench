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

import static org.junit.jupiter.api.Assertions.fail;

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
    fail("IllegalArgumentException");
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
    fail("IllegalArgumentException");
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
    fail("IllegalArgumentException");
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
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOffset_point_1_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        for (double polar = -Angle.PI_OVER_TWO; polar <= Angle.PI_OVER_TWO; polar += 0.1) {
            Assertions.assertEquals(0, circle.offset(Point2S.of(Angle.PI_OVER_TWO, polar)), TEST_EPS);
    }
    }

    @Test
    void testOffset_point_2_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        for (double polar = -Angle.PI_OVER_TWO; polar <= Angle.PI_OVER_TWO; polar += 0.1) {
            // removed other assertion
            Assertions.assertEquals(0, circle.offset(Point2S.of(-Angle.PI_OVER_TWO, polar)), TEST_EPS);
    }
    }

    @Test
    void testOffset_point_3_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        for (double polar = -Angle.PI_OVER_TWO; polar <= Angle.PI_OVER_TWO; polar += 0.1) {
            // removed other assertion
            // removed other assertion
        }

        // +1/-1
        Assertions.assertEquals(-1, circle.offset(Point2S.of(Angle.PI_OVER_TWO + 1, Angle.PI_OVER_TWO)), TEST_EPS);
    }

    @Test
    void testOffset_point_4_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        for (double polar = -Angle.PI_OVER_TWO; polar <= Angle.PI_OVER_TWO; polar += 0.1) {
            // removed other assertion
            // removed other assertion
        }

        // +1/-1
        // removed other assertion
        Assertions.assertEquals(1, circle.offset(Point2S.of(-Angle.PI_OVER_TWO + 1, Angle.PI_OVER_TWO)), TEST_EPS);
    }

    @Test
    void testOffset_point_5_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        for (double polar = -Angle.PI_OVER_TWO; polar <= Angle.PI_OVER_TWO; polar += 0.1) {
            // removed other assertion
            // removed other assertion
        }

        // +1/-1
        // removed other assertion
        // removed other assertion

        // poles
        Assertions.assertEquals(-Angle.PI_OVER_TWO, circle.offset(Point2S.of(Math.PI, Angle.PI_OVER_TWO)), TEST_EPS);
    }

    @Test
    void testOffset_point_6_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        for (double polar = -Angle.PI_OVER_TWO; polar <= Angle.PI_OVER_TWO; polar += 0.1) {
            // removed other assertion
            // removed other assertion
        }

        // +1/-1
        // removed other assertion
        // removed other assertion

        // poles
        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, circle.offset(Point2S.of(0.0, Angle.PI_OVER_TWO)), TEST_EPS);
    }

    @Test
    void testOffset_vector_1_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        Assertions.assertEquals(0, circle.offset(Vector3D.of(0, 1, 0)), TEST_EPS);
    }

    @Test
    void testOffset_vector_2_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        Assertions.assertEquals(0, circle.offset(Vector3D.of(0, 0, 1)), TEST_EPS);
    }

    @Test
    void testOffset_vector_3_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, circle.offset(Vector3D.of(0, -1, 0)), TEST_EPS);
    }

    @Test
    void testOffset_vector_4_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, circle.offset(Vector3D.of(0, 0, -1)), TEST_EPS);
    }

    @Test
    void testOffset_vector_5_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        Assertions.assertEquals(-0.25 * Math.PI, circle.offset(Vector3D.of(-1, 1, 0)), TEST_EPS);
    }

    @Test
    void testOffset_vector_6_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        Assertions.assertEquals(-0.25 * Math.PI, circle.offset(Vector3D.of(-1, 0, 1)), TEST_EPS);
    }

    @Test
    void testOffset_vector_7_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-0.25 * Math.PI, circle.offset(Vector3D.of(-1, -1, 0)), TEST_EPS);
    }

    @Test
    void testOffset_vector_8_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-0.25 * Math.PI, circle.offset(Vector3D.of(-1, 0, -1)), TEST_EPS);
    }

    @Test
    void testOffset_vector_9_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0.25 * Math.PI, circle.offset(Vector3D.of(1, 1, 0)), TEST_EPS);
    }

    @Test
    void testOffset_vector_10_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0.25 * Math.PI, circle.offset(Vector3D.of(1, 0, 1)), TEST_EPS);
    }

    @Test
    void testOffset_vector_11_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.25 * Math.PI, circle.offset(Vector3D.of(1, -1, 0)), TEST_EPS);
    }

    @Test
    void testOffset_vector_12_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.25 * Math.PI, circle.offset(Vector3D.of(1, 0, -1)), TEST_EPS);
    }

    @Test
    void testOffset_vector_13_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // poles
        Assertions.assertEquals(-Angle.PI_OVER_TWO, circle.offset(Vector3D.Unit.MINUS_X), TEST_EPS);
    }

    @Test
    void testOffset_vector_14_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // poles
        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, circle.offset(Vector3D.Unit.PLUS_X), TEST_EPS);
    }

    @Test
    void testAzimuth_point_1_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        Assertions.assertEquals(Angle.PI_OVER_TWO, circle.azimuth(Point2S.from(Vector3D.of(0, 1, 0))), TEST_EPS);
    }

    @Test
    void testAzimuth_point_2_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        Assertions.assertEquals(0.0, circle.azimuth(Point2S.from(Vector3D.of(0, 0, 1))), TEST_EPS);
    }

    @Test
    void testAzimuth_point_3_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1.5 * Math.PI, circle.azimuth(Point2S.from(Vector3D.of(0, -1, 0))), TEST_EPS);
    }

    @Test
    void testAzimuth_point_4_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.PI, circle.azimuth(Point2S.from(Vector3D.of(0, 0, -1))), TEST_EPS);
    }

    @Test
    void testAzimuth_point_5_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        Assertions.assertEquals(Angle.PI_OVER_TWO, circle.azimuth(Point2S.from(Vector3D.of(-1, 1, 0))), TEST_EPS);
    }

    @Test
    void testAzimuth_point_6_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        Assertions.assertEquals(0.0, circle.azimuth(Point2S.from(Vector3D.of(-1, 0, 1))), TEST_EPS);
    }

    @Test
    void testAzimuth_point_7_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1.5 * Math.PI, circle.azimuth(Point2S.from(Vector3D.of(-1, -1, 0))), TEST_EPS);
    }

    @Test
    void testAzimuth_point_8_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.PI, circle.azimuth(Point2S.from(Vector3D.of(-1, 0, -1))), TEST_EPS);
    }

    @Test
    void testAzimuth_point_9_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Angle.PI_OVER_TWO, circle.azimuth(Point2S.from(Vector3D.of(1, 1, 0))), TEST_EPS);
    }

    @Test
    void testAzimuth_point_10_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0.0, circle.azimuth(Point2S.from(Vector3D.of(1, 0, 1))), TEST_EPS);
    }

    @Test
    void testAzimuth_point_11_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1.5 * Math.PI, circle.azimuth(Point2S.from(Vector3D.of(1, -1, 0))), TEST_EPS);
    }

    @Test
    void testAzimuth_point_12_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.PI, circle.azimuth(Point2S.from(Vector3D.of(1, 0, -1))), TEST_EPS);
    }

    @Test
    void testAzimuth_point_13_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // poles
        Assertions.assertEquals(0, circle.azimuth(Point2S.from(Vector3D.Unit.MINUS_X)), TEST_EPS);
    }

    @Test
    void testAzimuth_point_14_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // poles
        // removed other assertion
        Assertions.assertEquals(0, circle.azimuth(Point2S.from(Vector3D.Unit.PLUS_X)), TEST_EPS);
    }

    @Test
    void testAzimuth_vector_1_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        Assertions.assertEquals(Angle.PI_OVER_TWO, circle.azimuth(Vector3D.of(0, 1, 0)), TEST_EPS);
    }

    @Test
    void testAzimuth_vector_2_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        Assertions.assertEquals(0.0, circle.azimuth(Vector3D.of(0, 0, 1)), TEST_EPS);
    }

    @Test
    void testAzimuth_vector_3_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1.5 * Math.PI, circle.azimuth(Vector3D.of(0, -1, 0)), TEST_EPS);
    }

    @Test
    void testAzimuth_vector_4_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.PI, circle.azimuth(Vector3D.of(0, 0, -1)), TEST_EPS);
    }

    @Test
    void testAzimuth_vector_5_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        Assertions.assertEquals(Angle.PI_OVER_TWO, circle.azimuth(Vector3D.of(-1, 1, 0)), TEST_EPS);
    }

    @Test
    void testAzimuth_vector_6_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        Assertions.assertEquals(0.0, circle.azimuth(Vector3D.of(-1, 0, 1)), TEST_EPS);
    }

    @Test
    void testAzimuth_vector_7_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1.5 * Math.PI, circle.azimuth(Vector3D.of(-1, -1, 0)), TEST_EPS);
    }

    @Test
    void testAzimuth_vector_8_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.PI, circle.azimuth(Vector3D.of(-1, 0, -1)), TEST_EPS);
    }

    @Test
    void testAzimuth_vector_9_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Angle.PI_OVER_TWO, circle.azimuth(Vector3D.of(1, 1, 0)), TEST_EPS);
    }

    @Test
    void testAzimuth_vector_10_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0.0, circle.azimuth(Vector3D.of(1, 0, 1)), TEST_EPS);
    }

    @Test
    void testAzimuth_vector_11_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1.5 * Math.PI, circle.azimuth(Vector3D.of(1, -1, 0)), TEST_EPS);
    }

    @Test
    void testAzimuth_vector_12_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.PI, circle.azimuth(Vector3D.of(1, 0, -1)), TEST_EPS);
    }

    @Test
    void testAzimuth_vector_13_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // poles
        Assertions.assertEquals(0, circle.azimuth(Vector3D.Unit.MINUS_X), TEST_EPS);
    }

    @Test
    void testAzimuth_vector_14_oe() {
        // --- arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(
                Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // --- act/assert

        // on circle
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // +1/-1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // poles
        // removed other assertion
        Assertions.assertEquals(0, circle.azimuth(Vector3D.Unit.PLUS_X), TEST_EPS);
    }

    @Test
    void testTransform_rotateAroundPole_1_oe() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoints(
                Point2S.of(0, Angle.PI_OVER_TWO),
                Point2S.of(1, Angle.PI_OVER_TWO),
                TEST_PRECISION);

        final Transform2S t = Transform2S.createRotation(circle.getPolePoint(), 0.25 * Math.PI);

        // act
        final GreatCircle result = circle.transform(t);

        // assert
        Assertions.assertNotSame(circle, result);
    }

    @Test
    void testTransform_rotateAroundNonPole_1_oe() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoints(
                Point2S.of(0, Angle.PI_OVER_TWO),
                Point2S.of(1, Angle.PI_OVER_TWO),
                TEST_PRECISION);

        final Transform2S t = Transform2S.createRotation(Point2S.of(0, Angle.PI_OVER_TWO), Angle.PI_OVER_TWO);

        // act
        final GreatCircle result = circle.transform(t);

        // assert
        Assertions.assertNotSame(circle, result);
    }

    @Test
    void testTransform_piMinusAzimuth_1_oe() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoints(
                Point2S.of(0, Angle.PI_OVER_TWO),
                Point2S.of(1, Angle.PI_OVER_TWO),
                TEST_PRECISION);

        final Transform2S t = Transform2S.createReflection(Point2S.PLUS_J)
                .rotate(Point2S.PLUS_K, Math.PI);

        // act
        final GreatCircle result = circle.transform(t);

        // assert
        Assertions.assertNotSame(circle, result);
    }

    @Test
    void testSimilarOrientation_1_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPole(Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPole(Vector3D.Unit.MINUS_Z, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPole(Vector3D.Unit.from(1, 1, -1), TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPole(Vector3D.Unit.from(1, 1, 1), TEST_PRECISION);

        // act/assert
        Assertions.assertTrue(a.similarOrientation(a));
    }

    @Test
    void testSimilarOrientation_2_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPole(Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPole(Vector3D.Unit.MINUS_Z, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPole(Vector3D.Unit.from(1, 1, -1), TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPole(Vector3D.Unit.from(1, 1, 1), TEST_PRECISION);

        // act/assert
        // removed other assertion

        Assertions.assertFalse(a.similarOrientation(b));
    }

    @Test
    void testSimilarOrientation_3_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPole(Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPole(Vector3D.Unit.MINUS_Z, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPole(Vector3D.Unit.from(1, 1, -1), TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPole(Vector3D.Unit.from(1, 1, 1), TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(a.similarOrientation(c));
    }

    @Test
    void testSimilarOrientation_4_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPole(Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPole(Vector3D.Unit.MINUS_Z, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPole(Vector3D.Unit.from(1, 1, -1), TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPole(Vector3D.Unit.from(1, 1, 1), TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(a.similarOrientation(d));
    }

    @Test
    void testSimilarOrientation_5_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPole(Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPole(Vector3D.Unit.MINUS_Z, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPole(Vector3D.Unit.from(1, 1, -1), TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPole(Vector3D.Unit.from(1, 1, 1), TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(a.similarOrientation(e));
    }

    @Test
    void testSpan_1_oe() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final GreatArc span = circle.span();

        // assert
        Assertions.assertSame(circle, span.getCircle());
    }

    @Test
    void testSpan_2_oe() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final GreatArc span = circle.span();

        // assert
        // removed other assertion
        Assertions.assertTrue(span.getInterval().isFull());
    }

    @Test
    void testSpan_3_oe() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final GreatArc span = circle.span();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertNull(span.getStartPoint());
    }

    @Test
    void testSpan_4_oe() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final GreatArc span = circle.span();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(span.getEndPoint());
    }

    @Test
    void testArc_points_2s_1_oe() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act/assert
        checkArc(circle.arc(Point2S.of(1, Angle.PI_OVER_TWO), Point2S.of(0, 1)),
                Point2S.of(Angle.PI_OVER_TWO, Angle.PI_OVER_TWO), Point2S.of(0, 0));

        Assertions.assertTrue(circle.arc(Point2S.PLUS_I, Point2S.PLUS_I).isFull());
    }

    @Test
    void testArc_points_1s_1_oe() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act/assert
        checkArc(circle.arc(Point1S.of(Math.PI), Point1S.of(1.5 * Math.PI)),
                Point2S.of(0, Math.PI), Point2S.of(Angle.PI_OVER_TWO, Angle.PI_OVER_TWO));

        Assertions.assertTrue(circle.arc(Point1S.of(1), Point1S.of(1)).isFull());
    }

    @Test
    void testArc_azimuths_1_oe() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act/assert
        checkArc(circle.arc(Math.PI, 1.5 * Math.PI),
                Point2S.of(0, Math.PI), Point2S.of(Angle.PI_OVER_TWO, Angle.PI_OVER_TWO));

        Assertions.assertTrue(circle.arc(1, 1).isFull());
    }

    @Test
    void testArc_interval_1_oe() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final AngularInterval.Convex interval = AngularInterval.Convex.of(1, 2, TEST_PRECISION);

        // act
        final GreatArc arc = circle.arc(interval);

        // assert
        Assertions.assertSame(circle, arc.getCircle());
    }

    @Test
    void testArc_interval_2_oe() {
        // arrange
        final GreatCircle circle = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final AngularInterval.Convex interval = AngularInterval.Convex.of(1, 2, TEST_PRECISION);

        // act
        final GreatArc arc = circle.arc(interval);

        // assert
        // removed other assertion
        Assertions.assertSame(interval, arc.getInterval());
    }

    @Test
    void testIntersection_parallel_1_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final GreatCircle a = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, precision);
        final GreatCircle b = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, precision);
        final GreatCircle c = GreatCircles.fromPole(Vector3D.Unit.of(1, 1e-4, 1e-4), precision);
        final GreatCircle d = GreatCircles.fromPole(Vector3D.Unit.MINUS_X, precision);
        final GreatCircle e = GreatCircles.fromPole(Vector3D.Unit.of(-1, 1e-4, 1e-4), precision);

        // act/assert
        Assertions.assertNull(a.intersection(b));
    }

    @Test
    void testIntersection_parallel_2_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final GreatCircle a = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, precision);
        final GreatCircle b = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, precision);
        final GreatCircle c = GreatCircles.fromPole(Vector3D.Unit.of(1, 1e-4, 1e-4), precision);
        final GreatCircle d = GreatCircles.fromPole(Vector3D.Unit.MINUS_X, precision);
        final GreatCircle e = GreatCircles.fromPole(Vector3D.Unit.of(-1, 1e-4, 1e-4), precision);

        // act/assert
        // removed other assertion
        Assertions.assertNull(a.intersection(c));
    }

    @Test
    void testIntersection_parallel_3_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final GreatCircle a = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, precision);
        final GreatCircle b = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, precision);
        final GreatCircle c = GreatCircles.fromPole(Vector3D.Unit.of(1, 1e-4, 1e-4), precision);
        final GreatCircle d = GreatCircles.fromPole(Vector3D.Unit.MINUS_X, precision);
        final GreatCircle e = GreatCircles.fromPole(Vector3D.Unit.of(-1, 1e-4, 1e-4), precision);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(a.intersection(d));
    }

    @Test
    void testIntersection_parallel_4_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final GreatCircle a = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, precision);
        final GreatCircle b = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, precision);
        final GreatCircle c = GreatCircles.fromPole(Vector3D.Unit.of(1, 1e-4, 1e-4), precision);
        final GreatCircle d = GreatCircles.fromPole(Vector3D.Unit.MINUS_X, precision);
        final GreatCircle e = GreatCircles.fromPole(Vector3D.Unit.of(-1, 1e-4, 1e-4), precision);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(a.intersection(e));
    }

    @Test
    void testAngle_withoutReferencePoint_1_oe() {
     // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        Assertions.assertEquals(0, a.angle(a), TEST_EPS);
    }

    @Test
    void testAngle_withoutReferencePoint_2_oe() {
     // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(Math.PI, a.angle(b), TEST_EPS);
    }

    @Test
    void testAngle_withoutReferencePoint_3_oe() {
     // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Angle.PI_OVER_TWO, a.angle(c), TEST_EPS);
    }

    @Test
    void testAngle_withoutReferencePoint_4_oe() {
     // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, c.angle(a), TEST_EPS);
    }

    @Test
    void testAngle_withoutReferencePoint_5_oe() {
     // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Angle.PI_OVER_TWO, a.angle(d), TEST_EPS);
    }

    @Test
    void testAngle_withoutReferencePoint_6_oe() {
     // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, d.angle(a), TEST_EPS);
    }

    @Test
    void testAngle_withoutReferencePoint_7_oe() {
     // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0.25 * Math.PI, a.angle(e), TEST_EPS);
    }

    @Test
    void testAngle_withoutReferencePoint_8_oe() {
     // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0.25 * Math.PI, e.angle(a), TEST_EPS);
    }

    @Test
    void testAngle_withoutReferencePoint_9_oe() {
     // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0.75 * Math.PI, a.angle(f), TEST_EPS);
    }

    @Test
    void testAngle_withoutReferencePoint_10_oe() {
     // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

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
        Assertions.assertEquals(0.75 * Math.PI, f.angle(a), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_1_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        Assertions.assertEquals(0, a.angle(a, Point2S.PLUS_J), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_2_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(0, a.angle(a, Point2S.MINUS_J), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_3_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-Math.PI, a.angle(b, Point2S.PLUS_J), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_4_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(-Math.PI, a.angle(b, Point2S.MINUS_J), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_5_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Angle.PI_OVER_TWO, a.angle(c, Point2S.PLUS_I), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_6_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(-Angle.PI_OVER_TWO, a.angle(c, Point2S.MINUS_I), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_7_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-Angle.PI_OVER_TWO, c.angle(a, Point2S.PLUS_I), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_8_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, c.angle(a, Point2S.MINUS_I), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_9_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Angle.PI_OVER_TWO, a.angle(d, Point2S.PLUS_J), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_10_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

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
        Assertions.assertEquals(-Angle.PI_OVER_TWO, a.angle(d, Point2S.MINUS_J), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_11_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

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
        // removed other assertion

        Assertions.assertEquals(-Angle.PI_OVER_TWO, d.angle(a, Point2S.PLUS_J), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_12_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

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
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, d.angle(a, Point2S.MINUS_J), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_13_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0.25 * Math.PI, a.angle(e, Point2S.PLUS_J), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_14_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(-0.25 * Math.PI, a.angle(e, Point2S.MINUS_J), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_15_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-0.25 * Math.PI, e.angle(a, Point2S.PLUS_J), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_16_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0.25 * Math.PI, e.angle(a, Point2S.MINUS_J), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_17_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0.75 * Math.PI, a.angle(f, Point2S.PLUS_J), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_18_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(-0.75 * Math.PI, a.angle(f, Point2S.MINUS_J), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_19_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-0.75 * Math.PI, f.angle(a, Point2S.PLUS_J), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_20_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);
        final GreatCircle e = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        final GreatCircle f = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, -1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0.75 * Math.PI, f.angle(a, Point2S.MINUS_J), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_pointEquidistanceFromIntersections_1_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        Assertions.assertEquals(-0.25 * Math.PI, a.angle(b, Point2S.PLUS_I), TEST_EPS);
    }

    @Test
    void testAngle_withReferencePoint_pointEquidistanceFromIntersections_2_oe() {
        // arrange
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoleAndU(
                Vector3D.Unit.of(1, 0, 1),
                Vector3D.Unit.PLUS_Y,
                TEST_PRECISION);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(-0.25 * Math.PI, a.angle(b, Point2S.MINUS_I), TEST_EPS);
    }

    @Test
    void testEq_1_oe() {
        // arrange
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X, precision);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, precision);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.of(1e-6, 0, 1), Vector3D.Unit.PLUS_X, precision);
        final GreatCircle f = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.of(1, 1e-6, 0), precision);
        final GreatCircle g = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X,
                Precision.doubleEquivalenceOfEpsilon(eps));

        // act/assert
        Assertions.assertTrue(a.eq(a, precision));
    }

    @Test
    void testEq_2_oe() {
        // arrange
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X, precision);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, precision);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.of(1e-6, 0, 1), Vector3D.Unit.PLUS_X, precision);
        final GreatCircle f = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.of(1, 1e-6, 0), precision);
        final GreatCircle g = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X,
                Precision.doubleEquivalenceOfEpsilon(eps));

        // act/assert
        // removed other assertion

        Assertions.assertFalse(a.eq(b, precision));
    }

    @Test
    void testEq_3_oe() {
        // arrange
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X, precision);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, precision);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.of(1e-6, 0, 1), Vector3D.Unit.PLUS_X, precision);
        final GreatCircle f = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.of(1, 1e-6, 0), precision);
        final GreatCircle g = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X,
                Precision.doubleEquivalenceOfEpsilon(eps));

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(a.eq(c, precision));
    }

    @Test
    void testEq_4_oe() {
        // arrange
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X, precision);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, precision);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.of(1e-6, 0, 1), Vector3D.Unit.PLUS_X, precision);
        final GreatCircle f = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.of(1, 1e-6, 0), precision);
        final GreatCircle g = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X,
                Precision.doubleEquivalenceOfEpsilon(eps));

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(a.eq(d, precision));
    }

    @Test
    void testEq_5_oe() {
        // arrange
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X, precision);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, precision);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.of(1e-6, 0, 1), Vector3D.Unit.PLUS_X, precision);
        final GreatCircle f = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.of(1, 1e-6, 0), precision);
        final GreatCircle g = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X,
                Precision.doubleEquivalenceOfEpsilon(eps));

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(a.eq(e, precision));
    }

    @Test
    void testEq_6_oe() {
        // arrange
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X, precision);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, precision);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.of(1e-6, 0, 1), Vector3D.Unit.PLUS_X, precision);
        final GreatCircle f = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.of(1, 1e-6, 0), precision);
        final GreatCircle g = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X,
                Precision.doubleEquivalenceOfEpsilon(eps));

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(e.eq(a, precision));
    }

    @Test
    void testEq_7_oe() {
        // arrange
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X, precision);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, precision);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.of(1e-6, 0, 1), Vector3D.Unit.PLUS_X, precision);
        final GreatCircle f = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.of(1, 1e-6, 0), precision);
        final GreatCircle g = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X,
                Precision.doubleEquivalenceOfEpsilon(eps));

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(a.eq(f, precision));
    }

    @Test
    void testEq_8_oe() {
        // arrange
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X, precision);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, precision);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.of(1e-6, 0, 1), Vector3D.Unit.PLUS_X, precision);
        final GreatCircle f = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.of(1, 1e-6, 0), precision);
        final GreatCircle g = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X,
                Precision.doubleEquivalenceOfEpsilon(eps));

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(f.eq(a, precision));
    }

    @Test
    void testEq_9_oe() {
        // arrange
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X, precision);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, precision);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.of(1e-6, 0, 1), Vector3D.Unit.PLUS_X, precision);
        final GreatCircle f = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.of(1, 1e-6, 0), precision);
        final GreatCircle g = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X,
                Precision.doubleEquivalenceOfEpsilon(eps));

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(g.eq(e, precision));
    }

    @Test
    void testEq_10_oe() {
        // arrange
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X, precision);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, precision);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.of(1e-6, 0, 1), Vector3D.Unit.PLUS_X, precision);
        final GreatCircle f = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.of(1, 1e-6, 0), precision);
        final GreatCircle g = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X,
                Precision.doubleEquivalenceOfEpsilon(eps));

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
        Assertions.assertTrue(e.eq(g, precision));
    }

    @Test
    void testHashCode_1_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.of(0, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final int hash = a.hashCode();

        // act/assert
        Assertions.assertEquals(hash, a.hashCode());
    }

    @Test
    void testHashCode_2_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.of(0, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final int hash = a.hashCode();

        // act/assert
        // removed other assertion

        Assertions.assertNotEquals(hash, b.hashCode());
    }

    @Test
    void testHashCode_3_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.of(0, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final int hash = a.hashCode();

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNotEquals(hash, c.hashCode());
    }

    @Test
    void testHashCode_4_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.of(0, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final int hash = a.hashCode();

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNotEquals(hash, d.hashCode());
    }

    @Test
    void testHashCode_5_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.of(0, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final int hash = a.hashCode();

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(hash, e.hashCode());
    }

    @Test
    void testEquals_2_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion

        Assertions.assertNotEquals(a, b);
    }

    @Test
    void testEquals_3_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNotEquals(a, c);
    }

    @Test
    void testEquals_4_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNotEquals(a, d);
    }

    @Test
    void testEquals_5_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(a, e);
    }

    @Test
    void testEquals_6_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final GreatCircle a = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        final GreatCircle b = GreatCircles.fromPoleAndU(Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle d = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, precision);

        final GreatCircle e = GreatCircles.fromPoleAndU(Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(e, a);
    }

}
