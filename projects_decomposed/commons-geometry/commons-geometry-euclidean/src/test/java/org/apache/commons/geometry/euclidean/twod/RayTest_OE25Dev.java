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
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.oned.Interval;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class RayTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testTransform() {
        // arrange
        final AffineTransformMatrix2D t = AffineTransformMatrix2D.createRotation(-0.5 * Math.PI)
                .translate(Vector2D.Unit.PLUS_X);

        final Ray ray = Lines.rayFromPointAndDirection(Vector2D.of(1, 0), Vector2D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Ray result = ray.transform(t);

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, -1), result.getStartPoint(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.MINUS_Y, result.getDirection(), TEST_EPS);
    }

    @Test
    void testTransform_reflection() {
        // arrange
        final AffineTransformMatrix2D t = AffineTransformMatrix2D.createRotation(0.5 * Math.PI)
                .translate(Vector2D.Unit.PLUS_X)
                .scale(1, -1);

        final Ray ray = Lines.rayFromPointAndDirection(Vector2D.of(2, 3), Vector2D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Ray result = ray.transform(t);

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-2, -2), result.getStartPoint(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.MINUS_Y, result.getDirection(), TEST_EPS);
    }

    @Test
    void testReverse() {
        // arrange
        final Vector2D start = Vector2D.of(1, 2);

        EuclideanTestUtils.permuteSkipZero(-4, 4, 1, (x, y) -> {
            final Vector2D dir = Vector2D.of(x, y);

            final Ray ray = Lines.rayFromPointAndDirection(start, dir, TEST_PRECISION);

            // act
            final ReverseRay rev = ray.reverse();

            // assert
            EuclideanTestUtils.assertCoordinatesEqual(ray.getLine().getOrigin(), rev.getLine().getOrigin(), TEST_EPS);
            Assertions.assertEquals(-1, ray.getLine().getDirection().dot(rev.getLine().getDirection()), TEST_EPS);

            EuclideanTestUtils.assertCoordinatesEqual(ray.getStartPoint(), rev.getEndPoint(), TEST_EPS);
        });
    }

    @Test
    void testClosest() {
        // arrange
        final Vector2D p1 = Vector2D.of(0, -1);
        final Vector2D p2 = Vector2D.of(0, 1);
        final Ray ray = Lines.rayFromPointAndDirection(p1, p1.directionTo(p2), TEST_PRECISION);

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(p1, ray.closest(p1), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(p1, ray.closest(Vector2D.of(0, -2)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(p1, ray.closest(Vector2D.of(2, -2)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(p1, ray.closest(Vector2D.of(-1, -1)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(p2, ray.closest(p2), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, 2), ray.closest(Vector2D.of(0, 2)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, 2), ray.closest(Vector2D.of(-2, 2)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, 1), ray.closest(Vector2D.of(-1, 1)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO, ray.closest(Vector2D.ZERO), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, 0.5), ray.closest(Vector2D.of(1, 0.5)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, -0.5), ray.closest(Vector2D.of(-2, -0.5)), TEST_EPS);
    }

    @Test
    void testSplit() {
        // --- arrange
        final Vector2D p0 = Vector2D.of(1, 1);
        final Vector2D p1 = Vector2D.of(3, 1);
        final Vector2D low = Vector2D.of(0, 1);

        final Vector2D delta = Vector2D.of(1e-11, 1e-11);

        final Ray ray = Lines.rayFromPointAndDirection(p0, Vector2D.Unit.PLUS_X, TEST_PRECISION);

        // --- act

        // parallel
        checkSplit(ray.split(Lines.fromPointAndAngle(Vector2D.of(2, 2), 0, TEST_PRECISION)),
                null, null,
                p0, null);
        checkSplit(ray.split(Lines.fromPointAndAngle(Vector2D.of(2, 2), Math.PI, TEST_PRECISION)),
                p0, null,
                null, null);

        // coincident
        checkSplit(ray.split(Lines.fromPointAndAngle(p0.add(delta), 1e-20, TEST_PRECISION)),
                null, null,
                null, null);

        // through point on ray
        checkSplit(ray.split(Lines.fromPointAndAngle(p1, 1, TEST_PRECISION)),
                p0, p1,
                p1, null);
        checkSplit(ray.split(Lines.fromPointAndAngle(p1, -1, TEST_PRECISION)),
                p1, null,
                p0, p1);

        // through start point
        checkSplit(ray.split(Lines.fromPointAndAngle(p0.subtract(delta), 1, TEST_PRECISION)),
                null, null,
                p0, null);
        checkSplit(ray.split(Lines.fromPointAndAngle(p0.add(delta), -1, TEST_PRECISION)),
                p0, null,
                null, null);

        // intersection below minus
        checkSplit(ray.split(Lines.fromPointAndAngle(low, 1, TEST_PRECISION)),
                null, null,
                p0, null);
        checkSplit(ray.split(Lines.fromPointAndAngle(low, -1, TEST_PRECISION)),
                p0, null,
                null, null);
    }

    @Test
    void testToString() {
        // arrange
        final Ray ray = Lines.rayFromPointAndDirection(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION);

        // act
        final String str = ray.toString();

        // assert
        GeometryTestUtils.assertContains("Ray[startPoint= (0", str);
        GeometryTestUtils.assertContains(", direction= (1", str);
    }

    private static void checkSplit(final Split<LineConvexSubset> split, final Vector2D minusStart, final Vector2D minusEnd,
                                   final Vector2D plusStart, final Vector2D plusEnd) {

        final LineConvexSubset minus = split.getMinus();
        if (minusStart == null && minusEnd == null) {
            Assertions.assertNull(minus);
        } else {
            checkPoint(minusStart, minus.getStartPoint());
            checkPoint(minusEnd, minus.getEndPoint());
        }


        final LineConvexSubset plus = split.getPlus();
        if (plusStart == null && plusEnd == null) {
            Assertions.assertNull(plus);
        } else {
            checkPoint(plusStart, plus.getStartPoint());
            checkPoint(plusEnd, plus.getEndPoint());
        }
    }

    private static void checkPoint(final Vector2D expected, final Vector2D pt) {
        if (expected == null) {
            Assertions.assertNull(pt);
        } else {
            EuclideanTestUtils.assertCoordinatesEqual(expected, pt, TEST_EPS);
        }
    }

    @Test
    void testFromPointAndDirection_1_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 2);
        final Vector2D p1 = Vector2D.of(2, 2);

        // act
        final Ray ray = Lines.rayFromPointAndDirection(p0, p0.vectorTo(p1), TEST_PRECISION);

        // assert
        Assertions.assertFalse(ray.isFull());
    }

    @Test
    void testFromPointAndDirection_2_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 2);
        final Vector2D p1 = Vector2D.of(2, 2);

        // act
        final Ray ray = Lines.rayFromPointAndDirection(p0, p0.vectorTo(p1), TEST_PRECISION);

        // assert
        // removed other assertion
        Assertions.assertFalse(ray.isEmpty());
    }

    @Test
    void testFromPointAndDirection_3_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 2);
        final Vector2D p1 = Vector2D.of(2, 2);

        // act
        final Ray ray = Lines.rayFromPointAndDirection(p0, p0.vectorTo(p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(ray.isInfinite());
    }

    @Test
    void testFromPointAndDirection_4_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 2);
        final Vector2D p1 = Vector2D.of(2, 2);

        // act
        final Ray ray = Lines.rayFromPointAndDirection(p0, p0.vectorTo(p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(ray.isFinite());
    }

    @Test
    void testFromPointAndDirection_6_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 2);
        final Vector2D p1 = Vector2D.of(2, 2);

        // act
        final Ray ray = Lines.rayFromPointAndDirection(p0, p0.vectorTo(p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(ray.getEndPoint());
    }

    @Test
    void testFromPointAndDirection_7_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 2);
        final Vector2D p1 = Vector2D.of(2, 2);

        // act
        final Ray ray = Lines.rayFromPointAndDirection(p0, p0.vectorTo(p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, ray.getSubspaceStart(), TEST_EPS);
    }

    @Test
    void testFromPointAndDirection_10_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 2);
        final Vector2D p1 = Vector2D.of(2, 2);

        // act
        final Ray ray = Lines.rayFromPointAndDirection(p0, p0.vectorTo(p1), TEST_PRECISION);

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
        Assertions.assertNull(ray.getCentroid());
    }

    @Test
    void testFromPointAndDirection_11_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 2);
        final Vector2D p1 = Vector2D.of(2, 2);

        // act
        final Ray ray = Lines.rayFromPointAndDirection(p0, p0.vectorTo(p1), TEST_PRECISION);

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
        // removed other assertion
        Assertions.assertNull(ray.getBounds());
    }

    @Test
    void testFromPoint_1_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 1);
        final Vector2D p1 = Vector2D.of(1, 2);
        final Vector2D p3 = Vector2D.of(3, 3);

        final Line line = Lines.fromPoints(p0, p1, TEST_PRECISION);

        // act
        final Ray ray = Lines.rayFromPoint(line, p3);

        // assert
        Assertions.assertFalse(ray.isFull());
    }

    @Test
    void testFromPoint_2_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 1);
        final Vector2D p1 = Vector2D.of(1, 2);
        final Vector2D p3 = Vector2D.of(3, 3);

        final Line line = Lines.fromPoints(p0, p1, TEST_PRECISION);

        // act
        final Ray ray = Lines.rayFromPoint(line, p3);

        // assert
        // removed other assertion
        Assertions.assertFalse(ray.isEmpty());
    }

    @Test
    void testFromPoint_3_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 1);
        final Vector2D p1 = Vector2D.of(1, 2);
        final Vector2D p3 = Vector2D.of(3, 3);

        final Line line = Lines.fromPoints(p0, p1, TEST_PRECISION);

        // act
        final Ray ray = Lines.rayFromPoint(line, p3);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(ray.isInfinite());
    }

    @Test
    void testFromPoint_4_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 1);
        final Vector2D p1 = Vector2D.of(1, 2);
        final Vector2D p3 = Vector2D.of(3, 3);

        final Line line = Lines.fromPoints(p0, p1, TEST_PRECISION);

        // act
        final Ray ray = Lines.rayFromPoint(line, p3);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(ray.isFinite());
    }

    @Test
    void testFromPoint_6_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 1);
        final Vector2D p1 = Vector2D.of(1, 2);
        final Vector2D p3 = Vector2D.of(3, 3);

        final Line line = Lines.fromPoints(p0, p1, TEST_PRECISION);

        // act
        final Ray ray = Lines.rayFromPoint(line, p3);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(ray.getEndPoint());
    }

    @Test
    void testFromPoint_7_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 1);
        final Vector2D p1 = Vector2D.of(1, 2);
        final Vector2D p3 = Vector2D.of(3, 3);

        final Line line = Lines.fromPoints(p0, p1, TEST_PRECISION);

        // act
        final Ray ray = Lines.rayFromPoint(line, p3);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(3, ray.getSubspaceStart(), TEST_EPS);
    }

    @Test
    void testFromPoint_10_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 1);
        final Vector2D p1 = Vector2D.of(1, 2);
        final Vector2D p3 = Vector2D.of(3, 3);

        final Line line = Lines.fromPoints(p0, p1, TEST_PRECISION);

        // act
        final Ray ray = Lines.rayFromPoint(line, p3);

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
        Assertions.assertNull(ray.getCentroid());
    }

    @Test
    void testFromPoint_11_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 1);
        final Vector2D p1 = Vector2D.of(1, 2);
        final Vector2D p3 = Vector2D.of(3, 3);

        final Line line = Lines.fromPoints(p0, p1, TEST_PRECISION);

        // act
        final Ray ray = Lines.rayFromPoint(line, p3);

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
        // removed other assertion
        Assertions.assertNull(ray.getBounds());
    }

    @Test
    void testFromLocation_1_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 1);
        final Vector2D p1 = Vector2D.of(1, 2);

        final Line line = Lines.fromPoints(p0, p1, TEST_PRECISION);

        // act
        final Ray ray = Lines.rayFromLocation(line, -2);

        // assert
        Assertions.assertFalse(ray.isFull());
    }

    @Test
    void testFromLocation_2_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 1);
        final Vector2D p1 = Vector2D.of(1, 2);

        final Line line = Lines.fromPoints(p0, p1, TEST_PRECISION);

        // act
        final Ray ray = Lines.rayFromLocation(line, -2);

        // assert
        // removed other assertion
        Assertions.assertFalse(ray.isEmpty());
    }

    @Test
    void testFromLocation_3_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 1);
        final Vector2D p1 = Vector2D.of(1, 2);

        final Line line = Lines.fromPoints(p0, p1, TEST_PRECISION);

        // act
        final Ray ray = Lines.rayFromLocation(line, -2);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(ray.isInfinite());
    }

    @Test
    void testFromLocation_4_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 1);
        final Vector2D p1 = Vector2D.of(1, 2);

        final Line line = Lines.fromPoints(p0, p1, TEST_PRECISION);

        // act
        final Ray ray = Lines.rayFromLocation(line, -2);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(ray.isFinite());
    }

    @Test
    void testFromLocation_6_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 1);
        final Vector2D p1 = Vector2D.of(1, 2);

        final Line line = Lines.fromPoints(p0, p1, TEST_PRECISION);

        // act
        final Ray ray = Lines.rayFromLocation(line, -2);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(ray.getEndPoint());
    }

    @Test
    void testFromLocation_7_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 1);
        final Vector2D p1 = Vector2D.of(1, 2);

        final Line line = Lines.fromPoints(p0, p1, TEST_PRECISION);

        // act
        final Ray ray = Lines.rayFromLocation(line, -2);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-2, ray.getSubspaceStart(), TEST_EPS);
    }

    @Test
    void testFromLocation_10_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 1);
        final Vector2D p1 = Vector2D.of(1, 2);

        final Line line = Lines.fromPoints(p0, p1, TEST_PRECISION);

        // act
        final Ray ray = Lines.rayFromLocation(line, -2);

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
        Assertions.assertNull(ray.getCentroid());
    }

    @Test
    void testFromLocation_11_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 1);
        final Vector2D p1 = Vector2D.of(1, 2);

        final Line line = Lines.fromPoints(p0, p1, TEST_PRECISION);

        // act
        final Ray ray = Lines.rayFromLocation(line, -2);

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
        // removed other assertion
        Assertions.assertNull(ray.getBounds());
    }

    @Test
    void testSplit_smallAngle_pointOnSplitter_1_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-5);

        final Ray ray = Lines.rayFromPointAndDirection(Vector2D.of(1, 1e-6), Vector2D.of(-1, -1e-2), precision);

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0, precision);

        // act
        final Split<LineConvexSubset> split = ray.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_smallAngle_pointOnSplitter_2_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-5);

        final Ray ray = Lines.rayFromPointAndDirection(Vector2D.of(1, 1e-6), Vector2D.of(-1, -1e-2), precision);

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0, precision);

        // act
        final Split<LineConvexSubset> split = ray.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_smallAngle_pointOnSplitter_3_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-5);

        final Ray ray = Lines.rayFromPointAndDirection(Vector2D.of(1, 1e-6), Vector2D.of(-1, -1e-2), precision);

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0, precision);

        // act
        final Split<LineConvexSubset> split = ray.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(ray, split.getPlus());
    }

    @Test
    void testGetInterval_1_oe() {
        // arrange
        final Ray ray = Lines.rayFromPointAndDirection(Vector2D.of(2, -1), Vector2D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Interval interval = ray.getInterval();

        // assert
        Assertions.assertEquals(2, interval.getMin(), TEST_EPS);
    }

    @Test
    void testGetInterval_3_oe() {
        // arrange
        final Ray ray = Lines.rayFromPointAndDirection(Vector2D.of(2, -1), Vector2D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Interval interval = ray.getInterval();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(ray.getLine().getPrecision(), interval.getMinBoundary().getPrecision());
    }

@Test
    void testFromPointAndDirection_invalidArgs_1_oe() {
        // arrange
        final Vector2D p = Vector2D.of(0, 2);
        final Vector2D d = Vector2D.of(1e-17, -1e-12);

        // act/assert
        try {
     Lines.rayFromPointAndDirection(p, d, TEST_PRECISION);
    fail("Expected IllegalArgumentException with message: " + "Line direction cannot be zero");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromPoint_invalidArgs_1_oe() {
        // arrange
        final Vector2D p = Vector2D.of(0, 2);
        final Vector2D d = Vector2D.of(1, 1);
        final Line line = Lines.fromPointAndDirection(p, d, TEST_PRECISION);

        // act/assert
        try {
     Lines.rayFromPoint(line, Vector2D.NaN);
    fail("Expected IllegalArgumentException with message: " + "Invalid ray start point: (NaN, NaN)");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromPoint_invalidArgs_2_oe() {
        // arrange
        final Vector2D p = Vector2D.of(0, 2);
        final Vector2D d = Vector2D.of(1, 1);
        final Line line = Lines.fromPointAndDirection(p, d, TEST_PRECISION);

        // act/assert
        // removed other assertion

        try {
     Lines.rayFromPoint(line, Vector2D.POSITIVE_INFINITY);
    fail("Expected IllegalArgumentException with message: " + "Invalid ray start point: (Infinity, Infinity)");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromPoint_invalidArgs_3_oe() {
        // arrange
        final Vector2D p = Vector2D.of(0, 2);
        final Vector2D d = Vector2D.of(1, 1);
        final Line line = Lines.fromPointAndDirection(p, d, TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion

        try {
     Lines.rayFromPoint(line, Vector2D.NEGATIVE_INFINITY);
    fail("Expected IllegalArgumentException with message: " + "Invalid ray start point: (-Infinity, -Infinity)");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromLocation_invalidArgs_1_oe() {
        // arrange
        final Vector2D p = Vector2D.of(0, 2);
        final Vector2D d = Vector2D.of(1, 1);
        final Line line = Lines.fromPointAndDirection(p, d, TEST_PRECISION);

        // act/assert
        try {
     Lines.rayFromLocation(line, Double.NaN);
    fail("Expected IllegalArgumentException with message: " + "Invalid ray start location: NaN");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromLocation_invalidArgs_2_oe() {
        // arrange
        final Vector2D p = Vector2D.of(0, 2);
        final Vector2D d = Vector2D.of(1, 1);
        final Line line = Lines.fromPointAndDirection(p, d, TEST_PRECISION);

        // act/assert
        // removed other assertion

        try {
     Lines.rayFromLocation(line, Double.POSITIVE_INFINITY);
    fail("Expected IllegalArgumentException with message: " + "Invalid ray start location: Infinity");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromLocation_invalidArgs_3_oe() {
        // arrange
        final Vector2D p = Vector2D.of(0, 2);
        final Vector2D d = Vector2D.of(1, 1);
        final Line line = Lines.fromPointAndDirection(p, d, TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion

        try {
     Lines.rayFromLocation(line, Double.NEGATIVE_INFINITY);
    fail("Expected IllegalArgumentException with message: " + "Invalid ray start location: -Infinity");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testClassify_1_oe() {
        // arrange
        final Ray ray = Lines.rayFromPointAndDirection(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        EuclideanTestUtils.assertRegionLocation(ray, RegionLocation.OUTSIDE, Vector2D.of(2, 2), Vector2D.of(2, 0), Vector2D.of(-5, 1), Vector2D.of(0, 1));
    }

@Test
    void testClassify_2_oe() {
        // arrange
        final Ray ray = Lines.rayFromPointAndDirection(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion

        EuclideanTestUtils.assertRegionLocation(ray, RegionLocation.BOUNDARY, Vector2D.of(1, 1), Vector2D.of(1 + 1e-16, 1));
    }

@Test
    void testClassify_3_oe() {
        // arrange
        final Ray ray = Lines.rayFromPointAndDirection(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion

        EuclideanTestUtils.assertRegionLocation(ray, RegionLocation.INSIDE, Vector2D.of(2, 1), Vector2D.of(5, 1 + 1e-16));
    }

}
