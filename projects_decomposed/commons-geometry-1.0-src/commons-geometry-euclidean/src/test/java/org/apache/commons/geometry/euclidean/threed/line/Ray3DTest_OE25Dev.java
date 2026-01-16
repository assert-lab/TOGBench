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
package org.apache.commons.geometry.euclidean.threed.line;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.oned.Interval;
import org.apache.commons.geometry.euclidean.threed.AffineTransformMatrix3D;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.euclidean.threed.rotation.QuaternionRotation;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Ray3DTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testFromPointAndDirection_invalidArgs() {
        // arrange
        final Vector3D pt = Vector3D.of(0, 2, 4);
        final Vector3D dir = Vector3D.of(1e-11, 0, 0);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.rayFromPointAndDirection(pt, dir, TEST_PRECISION);
        }, IllegalArgumentException.class, "Line direction cannot be zero");
    }

    @Test
    void testFromPoint_invalidArgs() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.rayFromPoint(line, Vector3D.NaN);
        }, IllegalArgumentException.class, "Invalid ray start location: NaN");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.rayFromPoint(line, Vector3D.NEGATIVE_INFINITY);
        }, IllegalArgumentException.class, "Invalid ray start location: NaN");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.rayFromPoint(line, Vector3D.POSITIVE_INFINITY);
        }, IllegalArgumentException.class, "Invalid ray start location: NaN");
    }

    @Test
    void testToString() {
        // arrange
        final Ray3D ray = Lines3D.rayFromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final String str = ray.toString();

        // assert
        GeometryTestUtils.assertContains("Ray3D[startPoint= (0", str);
        GeometryTestUtils.assertContains(", direction= (1", str);
    }

    @Test
    void testFromPointAndDirection_1_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 1, 2);

        // act
        final Ray3D ray = Lines3D.rayFromPointAndDirection(pt, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // assert
        Assertions.assertTrue(ray.isInfinite());
    }

    @Test
    void testFromPointAndDirection_2_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 1, 2);

        // act
        final Ray3D ray = Lines3D.rayFromPointAndDirection(pt, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // assert
        // removed other assertion
        Assertions.assertFalse(ray.isFinite());
    }

    @Test
    void testFromPointAndDirection_3_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 1, 2);

        // act
        final Ray3D ray = Lines3D.rayFromPointAndDirection(pt, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        EuclideanTestUtils.assertCoordinatesEqual(pt, ray.getStartPoint(), TEST_EPS);
        Assertions.assertNull(ray.getEndPoint());
    }

    @Test
    void testFromPointAndDirection_4_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 1, 2);

        // act
        final Ray3D ray = Lines3D.rayFromPointAndDirection(pt, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        EuclideanTestUtils.assertCoordinatesEqual(pt, ray.getStartPoint(), TEST_EPS);
        // removed other assertion

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.PLUS_Z, ray.getDirection(), TEST_EPS);

        Assertions.assertEquals(2, ray.getSubspaceStart(), TEST_EPS);
    }

    @Test
    void testFromPointAndDirection_5_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 1, 2);

        // act
        final Ray3D ray = Lines3D.rayFromPointAndDirection(pt, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        EuclideanTestUtils.assertCoordinatesEqual(pt, ray.getStartPoint(), TEST_EPS);
        // removed other assertion

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.PLUS_Z, ray.getDirection(), TEST_EPS);

        // removed other assertion
        GeometryTestUtils.assertPositiveInfinity(ray.getSubspaceEnd());

        GeometryTestUtils.assertPositiveInfinity(ray.getSize());

        Assertions.assertNull(ray.getCentroid());
    }

    @Test
    void testFromPointAndDirection_6_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 1, 2);

        // act
        final Ray3D ray = Lines3D.rayFromPointAndDirection(pt, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        EuclideanTestUtils.assertCoordinatesEqual(pt, ray.getStartPoint(), TEST_EPS);
        // removed other assertion

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.PLUS_Z, ray.getDirection(), TEST_EPS);

        // removed other assertion
        GeometryTestUtils.assertPositiveInfinity(ray.getSubspaceEnd());

        GeometryTestUtils.assertPositiveInfinity(ray.getSize());

        // removed other assertion
        Assertions.assertNull(ray.getBounds());
    }

    @Test
    void testFromPoint_1_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(-2, -1, 2);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Ray3D ray = Lines3D.rayFromPoint(line, pt);

        // assert
        Assertions.assertTrue(ray.isInfinite());
    }

    @Test
    void testFromPoint_2_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(-2, -1, 2);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Ray3D ray = Lines3D.rayFromPoint(line, pt);

        // assert
        // removed other assertion
        Assertions.assertFalse(ray.isFinite());
    }

    @Test
    void testFromPoint_3_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(-2, -1, 2);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Ray3D ray = Lines3D.rayFromPoint(line, pt);

        // assert
        // removed other assertion
        // removed other assertion

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, -1, 2), ray.getStartPoint(), TEST_EPS);
        Assertions.assertNull(ray.getEndPoint());
    }

    @Test
    void testFromPoint_4_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(-2, -1, 2);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Ray3D ray = Lines3D.rayFromPoint(line, pt);

        // assert
        // removed other assertion
        // removed other assertion

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, -1, 2), ray.getStartPoint(), TEST_EPS);
        // removed other assertion

        Assertions.assertEquals(-1, ray.getSubspaceStart(), TEST_EPS);
    }

    @Test
    void testFromPoint_5_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(-2, -1, 2);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Ray3D ray = Lines3D.rayFromPoint(line, pt);

        // assert
        // removed other assertion
        // removed other assertion

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, -1, 2), ray.getStartPoint(), TEST_EPS);
        // removed other assertion

        // removed other assertion
        GeometryTestUtils.assertPositiveInfinity(ray.getSubspaceEnd());

        GeometryTestUtils.assertPositiveInfinity(ray.getSize());

        Assertions.assertNull(ray.getCentroid());
    }

    @Test
    void testFromPoint_6_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(-2, -1, 2);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Ray3D ray = Lines3D.rayFromPoint(line, pt);

        // assert
        // removed other assertion
        // removed other assertion

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, -1, 2), ray.getStartPoint(), TEST_EPS);
        // removed other assertion

        // removed other assertion
        GeometryTestUtils.assertPositiveInfinity(ray.getSubspaceEnd());

        GeometryTestUtils.assertPositiveInfinity(ray.getSize());

        // removed other assertion
        Assertions.assertNull(ray.getBounds());
    }

    @Test
    void testFromLocation_1_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Ray3D ray = Lines3D.rayFromLocation(line, -1);

        // assert
        Assertions.assertTrue(ray.isInfinite());
    }

    @Test
    void testFromLocation_2_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Ray3D ray = Lines3D.rayFromLocation(line, -1);

        // assert
        // removed other assertion
        Assertions.assertFalse(ray.isFinite());
    }

    @Test
    void testFromLocation_3_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Ray3D ray = Lines3D.rayFromLocation(line, -1);

        // assert
        // removed other assertion
        // removed other assertion

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-1, 0, -1), ray.getStartPoint(), TEST_EPS);
        Assertions.assertNull(ray.getEndPoint());
    }

    @Test
    void testFromLocation_4_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Ray3D ray = Lines3D.rayFromLocation(line, -1);

        // assert
        // removed other assertion
        // removed other assertion

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-1, 0, -1), ray.getStartPoint(), TEST_EPS);
        // removed other assertion

        Assertions.assertEquals(-1, ray.getSubspaceStart(), TEST_EPS);
    }

    @Test
    void testFromLocation_5_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Ray3D ray = Lines3D.rayFromLocation(line, -1);

        // assert
        // removed other assertion
        // removed other assertion

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-1, 0, -1), ray.getStartPoint(), TEST_EPS);
        // removed other assertion

        // removed other assertion
        GeometryTestUtils.assertPositiveInfinity(ray.getSubspaceEnd());

        GeometryTestUtils.assertPositiveInfinity(ray.getSize());

        Assertions.assertNull(ray.getCentroid());
    }

    @Test
    void testFromLocation_6_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Ray3D ray = Lines3D.rayFromLocation(line, -1);

        // assert
        // removed other assertion
        // removed other assertion

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-1, 0, -1), ray.getStartPoint(), TEST_EPS);
        // removed other assertion

        // removed other assertion
        GeometryTestUtils.assertPositiveInfinity(ray.getSubspaceEnd());

        GeometryTestUtils.assertPositiveInfinity(ray.getSize());

        // removed other assertion
        Assertions.assertNull(ray.getBounds());
    }

    @Test
    void testTransform_1_oe() {
        // arrange
        final AffineTransformMatrix3D t = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, 0.5 * Math.PI)
                .toMatrix()
                .translate(Vector3D.Unit.PLUS_Y);

        final Ray3D ray = Lines3D.rayFromPointAndDirection(Vector3D.of(1, 0, 0), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Ray3D result = ray.transform(t);

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(0, 1, -1), result.getStartPoint(), TEST_EPS);
        Assertions.assertNull(result.getEndPoint());
    }

    @Test
    void testTransform_reflection_1_oe() {
        // arrange
        final AffineTransformMatrix3D t = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, 0.5 * Math.PI)
                .toMatrix()
                .translate(Vector3D.Unit.PLUS_Y)
                .scale(1, 1, -2);

        final Ray3D ray = Lines3D.rayFromPointAndDirection(Vector3D.of(1, 0, 0), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Ray3D result = ray.transform(t);

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(0, 1, 2), result.getStartPoint(), TEST_EPS);
        Assertions.assertNull(result.getEndPoint());
    }

    @Test
    void testContains_1_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final Ray3D ray = Lines3D.rayFromPointAndDirection(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        Assertions.assertFalse(ray.contains(Vector3D.of(2, 2, 2)));
    }

    @Test
    void testContains_2_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final Ray3D ray = Lines3D.rayFromPointAndDirection(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion
        Assertions.assertFalse(ray.contains(Vector3D.of(0.9, 1, 1)));
    }

    @Test
    void testContains_3_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final Ray3D ray = Lines3D.rayFromPointAndDirection(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(ray.contains(Vector3D.of(-1, 1, 1)));
    }

    @Test
    void testContains_4_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final Ray3D ray = Lines3D.rayFromPointAndDirection(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(ray.contains(p0));
    }

    @Test
    void testContains_5_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final Ray3D ray = Lines3D.rayFromPointAndDirection(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(ray.contains(p0.subtract(delta)));
    }

    @Test
    void testContains_6_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final Ray3D ray = Lines3D.rayFromPointAndDirection(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(ray.contains(Vector3D.of(1000, 1, 1)));
    }

    @Test
    void testGetInterval_1_oe() {
        // arrange
        final Ray3D ray = Lines3D.rayFromPointAndDirection(Vector3D.of(2, -1, 3), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Interval interval = ray.getInterval();

        // assert
        Assertions.assertEquals(-1, interval.getMin(), TEST_EPS);
    }

    @Test
    void testGetInterval_2_oe() {
        // arrange
        final Ray3D ray = Lines3D.rayFromPointAndDirection(Vector3D.of(2, -1, 3), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Interval interval = ray.getInterval();

        // assert
        // removed other assertion
        GeometryTestUtils.assertPositiveInfinity(interval.getMax());

        Assertions.assertSame(ray.getLine().getPrecision(), interval.getMinBoundary().getPrecision());
    }

}
