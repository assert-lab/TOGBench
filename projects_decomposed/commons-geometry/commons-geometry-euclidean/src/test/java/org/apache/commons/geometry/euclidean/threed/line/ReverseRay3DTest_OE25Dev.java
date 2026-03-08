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

import static org.junit.jupiter.api.Assertions.fail;

class ReverseRay3DTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testFromPointAndDirection() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 1, 2);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(pt, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // assert
        Assertions.assertTrue(revRay.isInfinite());
        Assertions.assertFalse(revRay.isFinite());

        Assertions.assertNull(revRay.getStartPoint());
        EuclideanTestUtils.assertCoordinatesEqual(pt, revRay.getEndPoint(), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.PLUS_Z, revRay.getLine().getDirection(), TEST_EPS);

        GeometryTestUtils.assertNegativeInfinity(revRay.getSubspaceStart());
        Assertions.assertEquals(2, revRay.getSubspaceEnd(), TEST_EPS);

        GeometryTestUtils.assertPositiveInfinity(revRay.getSize());

        Assertions.assertNull(revRay.getCentroid());
        Assertions.assertNull(revRay.getBounds());
    }

    @Test
    void testFromPointAndDirection_invalidArgs() {
        // arrange
        final Vector3D pt = Vector3D.of(0, 2, 4);
        final Vector3D dir = Vector3D.of(1e-11, 0, 0);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.reverseRayFromPointAndDirection(pt, dir, TEST_PRECISION);
        }, IllegalArgumentException.class, "Line direction cannot be zero");
    }

    @Test
    void testFromPoint() {
        // arrange
        final Vector3D pt = Vector3D.of(-2, -1, 2);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromPoint(line, pt);

        // assert
        Assertions.assertTrue(revRay.isInfinite());
        Assertions.assertFalse(revRay.isFinite());

        Assertions.assertNull(revRay.getStartPoint());
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, -1, 2), revRay.getEndPoint(), TEST_EPS);

        GeometryTestUtils.assertNegativeInfinity(revRay.getSubspaceStart());
        Assertions.assertEquals(-1, revRay.getSubspaceEnd(), TEST_EPS);

        GeometryTestUtils.assertPositiveInfinity(revRay.getSize());

        Assertions.assertNull(revRay.getCentroid());
        Assertions.assertNull(revRay.getBounds());
    }

    @Test
    void testFromPoint_invalidArgs() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.reverseRayFromPoint(line, Vector3D.NaN);
        }, IllegalArgumentException.class, "Invalid reverse ray end location: NaN");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.reverseRayFromPoint(line, Vector3D.NEGATIVE_INFINITY);
        }, IllegalArgumentException.class, "Invalid reverse ray end location: NaN");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.reverseRayFromPoint(line, Vector3D.POSITIVE_INFINITY);
        }, IllegalArgumentException.class, "Invalid reverse ray end location: NaN");
    }

    @Test
    void testFromLocation() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromLocation(line, -1);

        // assert
        Assertions.assertTrue(revRay.isInfinite());
        Assertions.assertFalse(revRay.isFinite());

        Assertions.assertNull(revRay.getStartPoint());
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-1, 0, -1), revRay.getEndPoint(), TEST_EPS);

        GeometryTestUtils.assertNegativeInfinity(revRay.getSubspaceStart());
        Assertions.assertEquals(-1, revRay.getSubspaceEnd(), TEST_EPS);

        GeometryTestUtils.assertPositiveInfinity(revRay.getSize());

        Assertions.assertNull(revRay.getCentroid());
        Assertions.assertNull(revRay.getBounds());
    }

    @Test
    void testTransform() {
        // arrange
        final AffineTransformMatrix3D t = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, 0.5 * Math.PI)
                .toMatrix()
                .translate(Vector3D.Unit.PLUS_Y);

        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(Vector3D.of(1, 0, 0), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final ReverseRay3D result = revRay.transform(t);

        // assert
        Assertions.assertNull(result.getStartPoint());
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(0, 1, -1), result.getEndPoint(), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.MINUS_Z, result.getLine().getDirection(), TEST_EPS);
    }

    @Test
    void testTransform_reflection() {
        // arrange
        final AffineTransformMatrix3D t = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, 0.5 * Math.PI)
                .toMatrix()
                .translate(Vector3D.Unit.PLUS_Y)
                .scale(1, 1, -2);

        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(Vector3D.of(1, 0, 0), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final ReverseRay3D result = revRay.transform(t);

        // assert
        Assertions.assertNull(result.getStartPoint());
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(0, 1, 2), result.getEndPoint(), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.PLUS_Z, result.getLine().getDirection(), TEST_EPS);
    }

    @Test
    void testContains() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        Assertions.assertFalse(revRay.contains(Vector3D.of(2, 2, 2)));
        Assertions.assertFalse(revRay.contains(Vector3D.of(1.1, 1, 1)));
        Assertions.assertFalse(revRay.contains(Vector3D.of(100, 1, 1)));

        Assertions.assertTrue(revRay.contains(p0));
        Assertions.assertTrue(revRay.contains(p0.add(delta)));

        Assertions.assertTrue(revRay.contains(Vector3D.of(-1000, 1, 1)));
    }

    @Test
    void testGetInterval() {
        // arrange
        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(Vector3D.of(2, -1, 3), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Interval interval = revRay.getInterval();

        // assert
        GeometryTestUtils.assertNegativeInfinity(interval.getMin());
        Assertions.assertEquals(-1, interval.getMax(), TEST_EPS);

        Assertions.assertSame(revRay.getLine().getPrecision(), interval.getMaxBoundary().getPrecision());
    }

    @Test
    void testToString() {
        // arrange
        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final String str = revRay.toString();

        // assert
        GeometryTestUtils.assertContains("ReverseRay3D[direction= (1", str);
        GeometryTestUtils.assertContains(", endPoint= (0", str);
    }

    @Test
    void testFromPointAndDirection_1_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 1, 2);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(pt, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // assert
        Assertions.assertTrue(revRay.isInfinite());
    }

    @Test
    void testFromPointAndDirection_2_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 1, 2);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(pt, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // assert
        // removed other assertion
        Assertions.assertFalse(revRay.isFinite());
    }

    @Test
    void testFromPointAndDirection_3_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 1, 2);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(pt, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertNull(revRay.getStartPoint());
    }

    @Test
    void testFromPointAndDirection_7_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 1, 2);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(pt, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(2, revRay.getSubspaceEnd(), TEST_EPS);
    }

    @Test
    void testFromPointAndDirection_9_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 1, 2);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(pt, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertNull(revRay.getCentroid());
    }

    @Test
    void testFromPointAndDirection_10_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 1, 2);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(pt, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertNull(revRay.getBounds());
    }

    @Test
    void testFromPoint_1_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(-2, -1, 2);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromPoint(line, pt);

        // assert
        Assertions.assertTrue(revRay.isInfinite());
    }

    @Test
    void testFromPoint_2_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(-2, -1, 2);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromPoint(line, pt);

        // assert
        // removed other assertion
        Assertions.assertFalse(revRay.isFinite());
    }

    @Test
    void testFromPoint_3_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(-2, -1, 2);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromPoint(line, pt);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertNull(revRay.getStartPoint());
    }

    @Test
    void testFromPoint_6_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(-2, -1, 2);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromPoint(line, pt);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(-1, revRay.getSubspaceEnd(), TEST_EPS);
    }

    @Test
    void testFromPoint_8_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(-2, -1, 2);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromPoint(line, pt);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertNull(revRay.getCentroid());
    }

    @Test
    void testFromPoint_9_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(-2, -1, 2);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromPoint(line, pt);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertNull(revRay.getBounds());
    }

    @Test
    void testFromLocation_1_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromLocation(line, -1);

        // assert
        Assertions.assertTrue(revRay.isInfinite());
    }

    @Test
    void testFromLocation_2_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromLocation(line, -1);

        // assert
        // removed other assertion
        Assertions.assertFalse(revRay.isFinite());
    }

    @Test
    void testFromLocation_3_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromLocation(line, -1);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertNull(revRay.getStartPoint());
    }

    @Test
    void testFromLocation_6_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromLocation(line, -1);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(-1, revRay.getSubspaceEnd(), TEST_EPS);
    }

    @Test
    void testFromLocation_8_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromLocation(line, -1);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertNull(revRay.getCentroid());
    }

    @Test
    void testFromLocation_9_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final ReverseRay3D revRay = Lines3D.reverseRayFromLocation(line, -1);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertNull(revRay.getBounds());
    }

    @Test
    void testTransform_1_oe() {
        // arrange
        final AffineTransformMatrix3D t = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, 0.5 * Math.PI)
                .toMatrix()
                .translate(Vector3D.Unit.PLUS_Y);

        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(Vector3D.of(1, 0, 0), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final ReverseRay3D result = revRay.transform(t);

        // assert
        Assertions.assertNull(result.getStartPoint());
    }

    @Test
    void testTransform_reflection_1_oe() {
        // arrange
        final AffineTransformMatrix3D t = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, 0.5 * Math.PI)
                .toMatrix()
                .translate(Vector3D.Unit.PLUS_Y)
                .scale(1, 1, -2);

        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(Vector3D.of(1, 0, 0), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final ReverseRay3D result = revRay.transform(t);

        // assert
        Assertions.assertNull(result.getStartPoint());
    }

    @Test
    void testContains_1_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        Assertions.assertFalse(revRay.contains(Vector3D.of(2, 2, 2)));
    }

    @Test
    void testContains_2_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion
        Assertions.assertFalse(revRay.contains(Vector3D.of(1.1, 1, 1)));
    }

    @Test
    void testContains_3_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(revRay.contains(Vector3D.of(100, 1, 1)));
    }

    @Test
    void testContains_4_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(revRay.contains(p0));
    }

    @Test
    void testContains_5_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(revRay.contains(p0.add(delta)));
    }

    @Test
    void testContains_6_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(revRay.contains(Vector3D.of(-1000, 1, 1)));
    }

    @Test
    void testGetInterval_2_oe() {
        // arrange
        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(Vector3D.of(2, -1, 3), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Interval interval = revRay.getInterval();

        // assert
        // removed other assertion
        Assertions.assertEquals(-1, interval.getMax(), TEST_EPS);
    }

    @Test
    void testGetInterval_3_oe() {
        // arrange
        final ReverseRay3D revRay = Lines3D.reverseRayFromPointAndDirection(Vector3D.of(2, -1, 3), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Interval interval = revRay.getInterval();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(revRay.getLine().getPrecision(), interval.getMaxBoundary().getPrecision());
    }

@Test
    void testFromPointAndDirection_invalidArgs_1_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(0, 2, 4);
        final Vector3D dir = Vector3D.of(1e-11, 0, 0);

        // act/assert
        try {
     Lines3D.reverseRayFromPointAndDirection(pt, dir, TEST_PRECISION);
    fail("Expected IllegalArgumentException with message: " + "Line direction cannot be zero");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromPoint_invalidArgs_1_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        try {
     Lines3D.reverseRayFromPoint(line, Vector3D.NaN);
    fail("Expected IllegalArgumentException with message: " + "Invalid reverse ray end location: NaN");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromPoint_invalidArgs_2_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion

        try {
     Lines3D.reverseRayFromPoint(line, Vector3D.NEGATIVE_INFINITY);
    fail("Expected IllegalArgumentException with message: " + "Invalid reverse ray end location: NaN");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromPoint_invalidArgs_3_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion

        try {
     Lines3D.reverseRayFromPoint(line, Vector3D.POSITIVE_INFINITY);
    fail("Expected IllegalArgumentException with message: " + "Invalid reverse ray end location: NaN");
} catch (IllegalArgumentException e) {
}
    }

}
