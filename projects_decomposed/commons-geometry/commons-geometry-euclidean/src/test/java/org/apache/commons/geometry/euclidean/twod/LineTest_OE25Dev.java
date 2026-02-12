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
package org.apache.commons.geometry.euclidean.twod;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.oned.AffineTransformMatrix1D;
import org.apache.commons.geometry.euclidean.oned.Vector1D;
import org.apache.commons.geometry.euclidean.twod.Line.SubspaceTransform;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LineTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testFromPoints() {
        // act/assert
        checkLine(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION),
                Vector2D.ZERO, Vector2D.Unit.PLUS_X);
        checkLine(Lines.fromPoints(Vector2D.ZERO, Vector2D.of(100, 0), TEST_PRECISION),
                Vector2D.ZERO, Vector2D.Unit.PLUS_X);
        checkLine(Lines.fromPoints(Vector2D.of(100, 0), Vector2D.ZERO, TEST_PRECISION),
                Vector2D.ZERO, Vector2D.Unit.MINUS_X);
        checkLine(Lines.fromPoints(Vector2D.of(-100, 0), Vector2D.of(100, 0), TEST_PRECISION),
                Vector2D.ZERO, Vector2D.Unit.PLUS_X);

        checkLine(Lines.fromPoints(Vector2D.of(-2, 0), Vector2D.of(0, 2), TEST_PRECISION),
                Vector2D.of(-1, 1), Vector2D.of(1, 1).normalize());
        checkLine(Lines.fromPoints(Vector2D.of(0, 2), Vector2D.of(-2, 0), TEST_PRECISION),
                Vector2D.of(-1, 1), Vector2D.of(-1, -1).normalize());
    }

    @Test
    void testFromPoints_pointsTooClose() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> Lines.fromPoints(Vector2D.Unit.PLUS_X, Vector2D.Unit.PLUS_X, TEST_PRECISION),
                IllegalArgumentException.class, "Line direction cannot be zero");
        GeometryTestUtils.assertThrowsWithMessage(() -> Lines.fromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(1 + 1e-11, 1e-11), TEST_PRECISION),
                IllegalArgumentException.class, "Line direction cannot be zero");
    }

    @Test
    void testFromPointAndDirection() {
        // act/assert
        checkLine(Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION),
                Vector2D.ZERO, Vector2D.Unit.PLUS_X);
        checkLine(Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.of(100, 0), TEST_PRECISION),
                Vector2D.ZERO, Vector2D.Unit.PLUS_X);
        checkLine(Lines.fromPointAndDirection(Vector2D.of(-100, 0), Vector2D.of(100, 0), TEST_PRECISION),
                Vector2D.ZERO, Vector2D.Unit.PLUS_X);

        checkLine(Lines.fromPointAndDirection(Vector2D.of(-2, 0), Vector2D.of(1, 1), TEST_PRECISION),
                Vector2D.of(-1, 1), Vector2D.of(1, 1).normalize());
        checkLine(Lines.fromPointAndDirection(Vector2D.of(0, 2), Vector2D.of(-1, -1), TEST_PRECISION),
                Vector2D.of(-1, 1), Vector2D.of(-1, -1).normalize());
    }

    @Test
    void testFromPointAndDirection_directionIsZero() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> Lines.fromPointAndDirection(Vector2D.Unit.PLUS_X, Vector2D.ZERO, TEST_PRECISION),
                IllegalArgumentException.class, "Line direction cannot be zero");
        GeometryTestUtils.assertThrowsWithMessage(() -> Lines.fromPointAndDirection(Vector2D.Unit.PLUS_X, Vector2D.of(1e-11, -1e-12), TEST_PRECISION),
                IllegalArgumentException.class, "Line direction cannot be zero");
    }

    @Test
    void testFromPointAndAngle() {
        // act/assert
        checkLine(Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION),
                Vector2D.ZERO, Vector2D.Unit.PLUS_X);
        checkLine(Lines.fromPointAndAngle(Vector2D.of(1, 1), Angle.PI_OVER_TWO, TEST_PRECISION),
                Vector2D.of(1, 0), Vector2D.Unit.PLUS_Y);
        checkLine(Lines.fromPointAndAngle(Vector2D.of(-1, -1), Math.PI, TEST_PRECISION),
                Vector2D.of(0, -1), Vector2D.Unit.MINUS_X);
        checkLine(Lines.fromPointAndAngle(Vector2D.of(1, -1), -Angle.PI_OVER_TWO, TEST_PRECISION),
                Vector2D.of(1, 0), Vector2D.Unit.MINUS_Y);
        checkLine(Lines.fromPointAndAngle(Vector2D.of(-1, 1), Angle.TWO_PI, TEST_PRECISION),
                Vector2D.of(0, 1), Vector2D.Unit.PLUS_X);
    }

    @Test
    void testGetDirection() {
        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.PLUS_X,
                Lines.fromPoints(Vector2D.of(0, 0), Vector2D.of(1, 0), TEST_PRECISION).getDirection(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.MINUS_Y,
                Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(0, -1), TEST_PRECISION).getDirection(), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.MINUS_X,
                Lines.fromPoints(Vector2D.of(2, 2), Vector2D.of(1, 2), TEST_PRECISION).getDirection(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.PLUS_X,
                Lines.fromPoints(Vector2D.of(10, -2), Vector2D.of(10.1, -2), TEST_PRECISION).getDirection(), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.MINUS_Y,
                Lines.fromPoints(Vector2D.of(3, 2), Vector2D.of(3, 1), TEST_PRECISION).getDirection(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.PLUS_Y,
                Lines.fromPoints(Vector2D.of(-3, 10), Vector2D.of(-3, 10.1), TEST_PRECISION).getDirection(), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, -1).normalize(),
                Lines.fromPoints(Vector2D.of(0, 2), Vector2D.of(2, 0), TEST_PRECISION).getDirection(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-1, 1).normalize(),
                Lines.fromPoints(Vector2D.of(2, 0), Vector2D.of(0, 2), TEST_PRECISION).getDirection(), TEST_EPS);
    }

    @Test
    void testGetOffsetDirection() {
        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.MINUS_Y,
                Lines.fromPoints(Vector2D.of(0, 0), Vector2D.of(1, 0), TEST_PRECISION).getOffsetDirection(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.MINUS_X,
                Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(0, -1), TEST_PRECISION).getOffsetDirection(), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.PLUS_Y,
                Lines.fromPoints(Vector2D.of(2, 2), Vector2D.of(1, 2), TEST_PRECISION).getOffsetDirection(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.MINUS_Y,
                Lines.fromPoints(Vector2D.of(10, -2), Vector2D.of(10.1, -2), TEST_PRECISION).getOffsetDirection(), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.MINUS_X,
                Lines.fromPoints(Vector2D.of(3, 2), Vector2D.of(3, 1), TEST_PRECISION).getOffsetDirection(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.PLUS_X,
                Lines.fromPoints(Vector2D.of(-3, 10), Vector2D.of(-3, 10.1), TEST_PRECISION).getOffsetDirection(), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-1, -1).normalize(),
                Lines.fromPoints(Vector2D.of(0, 2), Vector2D.of(2, 0), TEST_PRECISION).getOffsetDirection(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 1).normalize(),
                Lines.fromPoints(Vector2D.of(2, 0), Vector2D.of(0, 2), TEST_PRECISION).getOffsetDirection(), TEST_EPS);
    }

    @Test
    void testGetOrigin() {
        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO,
                Lines.fromPoints(Vector2D.of(0, 0), Vector2D.of(1, 0), TEST_PRECISION).getOrigin(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO,
                Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(0, -1), TEST_PRECISION).getOrigin(), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, 2),
                Lines.fromPoints(Vector2D.of(2, 2), Vector2D.of(3, 2), TEST_PRECISION).getOrigin(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, -2),
                Lines.fromPoints(Vector2D.of(10, -2), Vector2D.of(10.1, -2), TEST_PRECISION).getOrigin(), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(3, 0),
                Lines.fromPoints(Vector2D.of(3, 2), Vector2D.of(3, 1), TEST_PRECISION).getOrigin(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-3, 0),
                Lines.fromPoints(Vector2D.of(-3, 10), Vector2D.of(-3, 10.1), TEST_PRECISION).getOrigin(), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 1),
                Lines.fromPoints(Vector2D.of(0, 2), Vector2D.of(2, 0), TEST_PRECISION).getOrigin(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 1),
                Lines.fromPoints(Vector2D.of(2, 0), Vector2D.of(0, 2), TEST_PRECISION).getOrigin(), TEST_EPS);
    }

    @Test
    void testToSpace_throughOrigin() {
        // arrange
        final double invSqrt2 = 1 / Math.sqrt(2);
        final Vector2D dir = Vector2D.of(invSqrt2, invSqrt2);

        final Line line = Lines.fromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION);

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO, line.toSpace(Vector1D.of(0)), TEST_EPS);

        for (int i = 0; i < 100; ++i) {
            EuclideanTestUtils.assertCoordinatesEqual(dir.multiply(i), line.toSpace(Vector1D.of(i)), TEST_EPS);
            EuclideanTestUtils.assertCoordinatesEqual(dir.multiply(-i), line.toSpace(Vector1D.of(-i)), TEST_EPS);
        }
    }

    @Test
    void testToSpace_offsetFromOrigin() {
        // arrange
        final double angle = Math.PI / 6;
        final double cos = Math.cos(angle);
        final double sin = Math.sin(angle);
        final Vector2D pt = Vector2D.of(-5, 0);

        final double h = Math.abs(pt.getX()) * cos;
        final double d = h * cos;
        final Vector2D origin = Vector2D.of(
                    pt.getX() + d,
                    h * sin
                );
        final Vector2D dir = Vector2D.of(cos, sin);

        final Line line = Lines.fromPointAndAngle(pt, angle, TEST_PRECISION);

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(origin, line.toSpace(Vector1D.of(0)), TEST_EPS);

        for (int i = 0; i < 100; ++i) {
            EuclideanTestUtils.assertCoordinatesEqual(origin.add(dir.multiply(i)), line.toSpace(Vector1D.of(i)), TEST_EPS);
            EuclideanTestUtils.assertCoordinatesEqual(origin.add(dir.multiply(-i)), line.toSpace(Vector1D.of(-i)), TEST_EPS);
        }
    }

    @Test
    void testIntersection() {
        // arrange
        final Line a = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final Line b = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION);
        final Line c = Lines.fromPointAndDirection(Vector2D.of(0, 2), Vector2D.of(2, 1), TEST_PRECISION);
        final Line d = Lines.fromPointAndDirection(Vector2D.of(0, -1), Vector2D.of(2, -1), TEST_PRECISION);

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO, a.intersection(b), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO, b.intersection(a), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-4, 0), a.intersection(c), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-4, 0), c.intersection(a), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-2, 0), a.intersection(d), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-2, 0), d.intersection(a), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, 2), b.intersection(c), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, 2), c.intersection(b), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, -1), b.intersection(d), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, -1), d.intersection(b), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-3, 0.5), c.intersection(d), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-3, 0.5), d.intersection(c), TEST_EPS);
    }

    @Test
    void testProject() {
        // --- arrange
        final Line xAxis = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final Line yAxis = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION);

        final double diagonalYIntercept = 1;
        final Vector2D diagonalDir = Vector2D.of(1, 2);
        final Line diagonal = Lines.fromPointAndDirection(Vector2D.of(0, diagonalYIntercept), diagonalDir, TEST_PRECISION);

        EuclideanTestUtils.permute(-5, 5, 0.5, (x, y) -> {
            final Vector2D pt = Vector2D.of(x, y);

            // --- act/assert
            EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(x, 0), xAxis.project(pt), TEST_EPS);
            EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, y), yAxis.project(pt), TEST_EPS);

            final Vector2D diagonalPt = diagonal.project(pt);
            Assertions.assertTrue(diagonal.contains(diagonalPt));
            Assertions.assertEquals(diagonal.distance(pt), pt.distance(diagonalPt), TEST_EPS);

            // check that y = mx + b is true
            Assertions.assertEquals(diagonalPt.getY(),(diagonalDir.getY()* diagonalPt.getX()/ diagonalDir.getX())+ diagonalYIntercept,TEST_EPS);
        });
    }

    @Test
    void testOffset_point_permute() {
        // arrange
        final Line line = Lines.fromPoints(Vector2D.of(-1, 0), Vector2D.of(0, 2), TEST_PRECISION);
        final Vector2D lineOrigin = line.getOrigin();

        EuclideanTestUtils.permute(-5, 5, 0.5, (x, y) -> {
            final Vector2D pt = Vector2D.of(x, y);

            // act
            final double offset = line.offset(pt);

            // arrange
            final Vector2D vec = lineOrigin.vectorTo(pt).reject(line.getDirection());
            final double dot = vec.dot(line.getOffsetDirection());
            final double expected = Math.signum(dot) * vec.norm();

            Assertions.assertEquals(expected, offset, TEST_EPS);
        });
    }

    @Test
    void testPointAt() {
        // arrange
        final Vector2D origin = Vector2D.of(-1, 1);
        final double d = Math.sqrt(2);
        final Line line = Lines.fromPointAndDirection(origin, Vector2D.of(1, 1), TEST_PRECISION);

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(origin, line.pointAt(0, 0), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO, line.pointAt(0, d), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-2, 2), line.pointAt(0, -d), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-2, 0), line.pointAt(-d, 0), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, 2), line.pointAt(d, 0), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 1), line.pointAt(d, d), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-3, 1), line.pointAt(-d, -d), TEST_EPS);
    }

    @Test
    void testDistance_point_permute() {
        // arrange
        final Line line = Lines.fromPoints(Vector2D.of(-1, 0), Vector2D.of(0, 2), TEST_PRECISION);
        final Vector2D lineOrigin = line.getOrigin();

        EuclideanTestUtils.permute(-5, 5, 0.5, (x, y) -> {
            final Vector2D pt = Vector2D.of(x, y);

            // act
            final double dist = line.distance(pt);

            // arrange
            final Vector2D vec = lineOrigin.vectorTo(pt).reject(line.getDirection());
            Assertions.assertEquals(vec.norm(), dist, TEST_EPS);
        });
    }

    @Test
    void testTransform_collapsedPoints() {
        // arrange
        final AffineTransformMatrix2D scaleCollapse = AffineTransformMatrix2D.createScale(0, 1);
        final Line line = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            line.transform(scaleCollapse);
        }, IllegalArgumentException.class, "Line direction cannot be zero");
    }

    @Test
    void testSubspaceTransform() {
        // arrange
        final Line line = Lines.fromPoints(Vector2D.of(1, 0), Vector2D.of(1, 1), TEST_PRECISION);

        // act/assert
        checkSubspaceTransform(line.subspaceTransform(AffineTransformMatrix2D.createScale(2, 3)),
                Vector2D.of(2, 0), Vector2D.Unit.PLUS_Y,
                Vector2D.of(2, 0), Vector2D.of(2, 3));

        checkSubspaceTransform(line.subspaceTransform(AffineTransformMatrix2D.createTranslation(2, 3)),
                Vector2D.of(3, 0), Vector2D.Unit.PLUS_Y,
                Vector2D.of(3, 3), Vector2D.of(3, 4));

        checkSubspaceTransform(line.subspaceTransform(AffineTransformMatrix2D.createRotation(Angle.PI_OVER_TWO)),
                Vector2D.of(0, 1), Vector2D.Unit.MINUS_X,
                Vector2D.of(0, 1), Vector2D.of(-1, 1));
    }

    private void checkSubspaceTransform(final SubspaceTransform st, final Vector2D origin, final Vector2D dir, final Vector2D tZero, final Vector2D tOne) {

        final Line line = st.getLine();
        final AffineTransformMatrix1D transform = st.getTransform();

        checkLine(line, origin, dir);

        EuclideanTestUtils.assertCoordinatesEqual(tZero, line.toSpace(transform.apply(Vector1D.ZERO)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(tOne, line.toSpace(transform.apply(Vector1D.Unit.PLUS)), TEST_EPS);
    }

    @Test
    void testSubspaceTransform_transformsPointsCorrectly() {
        // arrange
        final Line line = Lines.fromPointAndDirection(Vector2D.of(1, 0), Vector2D.of(1, 1), TEST_PRECISION);

        EuclideanTestUtils.permuteSkipZero(-2, 2, 0.5, (a, b) -> {
            // create a somewhat complicate transform to try to hit all of the edge cases
            final AffineTransformMatrix2D transform = AffineTransformMatrix2D.createTranslation(Vector2D.of(a, b))
                    .rotate(a * b)
                    .scale(0.1, 4);

            // act
            final SubspaceTransform st = line.subspaceTransform(transform);

            // assert
            for (double x = -5.0; x <= 5.0; x += 1) {
                final Vector1D subPt = Vector1D.of(x);
                final Vector2D expected = transform.apply(line.toSpace(subPt));
                final Vector2D actual = st.getLine().toSpace(
                        st.getTransform().apply(subPt));

                EuclideanTestUtils.assertCoordinatesEqual(expected, actual, TEST_EPS);
            }
        });
    }

    /**
     * Check that the line has the given defining properties.
     * @param line
     * @param origin
     * @param dir
     */
    private void checkLine(final Line line, final Vector2D origin, final Vector2D dir) {
        EuclideanTestUtils.assertCoordinatesEqual(origin, line.getOrigin(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(dir, line.getDirection(), TEST_EPS);
    }


}
