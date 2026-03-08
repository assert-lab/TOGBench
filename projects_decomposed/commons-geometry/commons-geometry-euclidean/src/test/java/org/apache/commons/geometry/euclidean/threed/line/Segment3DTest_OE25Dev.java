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
import org.apache.commons.geometry.euclidean.threed.Bounds3D;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.euclidean.threed.rotation.QuaternionRotation;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class Segment3DTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testTransform() {
        // arrange
        final AffineTransformMatrix3D t = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, 0.5 * Math.PI)
                .toMatrix()
                .translate(Vector3D.Unit.PLUS_Y);

        final Segment3D seg = Lines3D.segmentFromPoints(Vector3D.of(1, 0, 0), Vector3D.of(2, 0, 0), TEST_PRECISION);

        // act
        final Segment3D result = seg.transform(t);

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(0, 1, -1), result.getStartPoint(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(0, 1, -2), result.getEndPoint(), TEST_EPS);
    }

    @Test
    void testTransform_reflection() {
        // arrange
        final AffineTransformMatrix3D t = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, 0.5 * Math.PI)
                .toMatrix()
                .translate(Vector3D.Unit.PLUS_Y)
                .scale(1, 1, -2);

        final Segment3D seg = Lines3D.segmentFromPoints(Vector3D.of(1, 0, 0), Vector3D.of(2, 0, 0), TEST_PRECISION);

        // act
        final Segment3D result = seg.transform(t);

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(0, 1, 2), result.getStartPoint(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(0, 1, 4), result.getEndPoint(), TEST_EPS);
    }

    @Test
    void testToString() {
        // arrange
        final Segment3D seg = Lines3D.segmentFromPoints(Vector3D.ZERO, Vector3D.of(1, 0, 0), TEST_PRECISION);

        // act
        final String str = seg.toString();

        // assert
        GeometryTestUtils.assertContains("Segment3D[startPoint= (0", str);
        GeometryTestUtils.assertContains(", endPoint= (1", str);
    }

    @Test
    void testFromPoints_1_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(1, 1, 2);
        final Vector3D p2 = Vector3D.of(1, 3, 2);

        // act
        final Segment3D seg = Lines3D.segmentFromPoints(p1, p2, TEST_PRECISION);

        // assert
        Assertions.assertFalse(seg.isInfinite());
    }

    @Test
    void testFromPoints_2_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(1, 1, 2);
        final Vector3D p2 = Vector3D.of(1, 3, 2);

        // act
        final Segment3D seg = Lines3D.segmentFromPoints(p1, p2, TEST_PRECISION);

        // assert
        // removed other assertion
        Assertions.assertTrue(seg.isFinite());
    }

    @Test
    void testFromPoints_5_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(1, 1, 2);
        final Vector3D p2 = Vector3D.of(1, 3, 2);

        // act
        final Segment3D seg = Lines3D.segmentFromPoints(p1, p2, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, seg.getSubspaceStart(), TEST_EPS);
    }

    @Test
    void testFromPoints_6_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(1, 1, 2);
        final Vector3D p2 = Vector3D.of(1, 3, 2);

        // act
        final Segment3D seg = Lines3D.segmentFromPoints(p1, p2, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(3, seg.getSubspaceEnd(), TEST_EPS);
    }

    @Test
    void testFromPoints_7_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(1, 1, 2);
        final Vector3D p2 = Vector3D.of(1, 3, 2);

        // act
        final Segment3D seg = Lines3D.segmentFromPoints(p1, p2, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(2, seg.getSize(), TEST_EPS);
    }

    @Test
    void testFromPoints_givenLine_1_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(-1, -1, 2);
        final Vector3D p2 = Vector3D.of(3, 3, 3);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromPoints(line, p2, p1); // reverse location order

        // assert
        Assertions.assertFalse(seg.isInfinite());
    }

    @Test
    void testFromPoints_givenLine_2_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(-1, -1, 2);
        final Vector3D p2 = Vector3D.of(3, 3, 3);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromPoints(line, p2, p1); // reverse location order

        // assert
        // removed other assertion
        Assertions.assertTrue(seg.isFinite());
    }

    @Test
    void testFromPoints_givenLine_5_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(-1, -1, 2);
        final Vector3D p2 = Vector3D.of(3, 3, 3);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromPoints(line, p2, p1); // reverse location order

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-1, seg.getSubspaceStart(), TEST_EPS);
    }

    @Test
    void testFromPoints_givenLine_6_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(-1, -1, 2);
        final Vector3D p2 = Vector3D.of(3, 3, 3);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromPoints(line, p2, p1); // reverse location order

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(3, seg.getSubspaceEnd(), TEST_EPS);
    }

    @Test
    void testFromPoints_givenLine_7_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(-1, -1, 2);
        final Vector3D p2 = Vector3D.of(3, 3, 3);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 2), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromPoints(line, p2, p1); // reverse location order

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(4, seg.getSize(), TEST_EPS);
    }

    @Test
    void testFromPoints_givenLine_singlePoint_1_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(-1, 2, 0);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 0), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromPoints(line, p1, p1);

        // assert
        Assertions.assertFalse(seg.isInfinite());
    }

    @Test
    void testFromPoints_givenLine_singlePoint_2_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(-1, 2, 0);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 0), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromPoints(line, p1, p1);

        // assert
        // removed other assertion
        Assertions.assertTrue(seg.isFinite());
    }

    @Test
    void testFromPoints_givenLine_singlePoint_5_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(-1, 2, 0);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 0), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromPoints(line, p1, p1);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(2, seg.getSubspaceStart(), TEST_EPS);
    }

    @Test
    void testFromPoints_givenLine_singlePoint_6_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(-1, 2, 0);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 0), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromPoints(line, p1, p1);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(2, seg.getSubspaceEnd(), TEST_EPS);
    }

    @Test
    void testFromPoints_givenLine_singlePoint_7_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(-1, 2, 0);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 0), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromPoints(line, p1, p1);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, seg.getSize(), TEST_EPS);
    }

    @Test
    void testFromLocations_1_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromLocations(line, -1, 2);

        // assert
        Assertions.assertFalse(seg.isInfinite());
    }

    @Test
    void testFromLocations_2_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromLocations(line, -1, 2);

        // assert
        // removed other assertion
        Assertions.assertTrue(seg.isFinite());
    }

    @Test
    void testFromLocations_5_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromLocations(line, -1, 2);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-1, seg.getSubspaceStart(), TEST_EPS);
    }

    @Test
    void testFromLocations_6_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromLocations(line, -1, 2);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(2, seg.getSubspaceEnd(), TEST_EPS);
    }

    @Test
    void testFromLocations_7_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromLocations(line, -1, 2);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(3, seg.getSize(), TEST_EPS);
    }

    @Test
    void testFromLocations_reversedLocationOrder_1_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 1), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromLocations(line, 2, -1);

        // assert
        Assertions.assertFalse(seg.isInfinite());
    }

    @Test
    void testFromLocations_reversedLocationOrder_2_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 1), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromLocations(line, 2, -1);

        // assert
        // removed other assertion
        Assertions.assertTrue(seg.isFinite());
    }

    @Test
    void testFromLocations_reversedLocationOrder_5_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 1), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromLocations(line, 2, -1);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-1, seg.getSubspaceStart(), TEST_EPS);
    }

    @Test
    void testFromLocations_reversedLocationOrder_6_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 1), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromLocations(line, 2, -1);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(2, seg.getSubspaceEnd(), TEST_EPS);
    }

    @Test
    void testFromLocations_reversedLocationOrder_7_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 1), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromLocations(line, 2, -1);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(3, seg.getSize(), TEST_EPS);
    }

    @Test
    void testFromLocations_singlePoint_1_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromLocations(line, 1, 1);

        // assert
        Assertions.assertFalse(seg.isInfinite());
    }

    @Test
    void testFromLocations_singlePoint_2_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromLocations(line, 1, 1);

        // assert
        // removed other assertion
        Assertions.assertTrue(seg.isFinite());
    }

    @Test
    void testFromLocations_singlePoint_5_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromLocations(line, 1, 1);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, seg.getSubspaceStart(), TEST_EPS);
    }

    @Test
    void testFromLocations_singlePoint_6_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromLocations(line, 1, 1);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, seg.getSubspaceEnd(), TEST_EPS);
    }

    @Test
    void testFromLocations_singlePoint_7_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 0, 0), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act
        final Segment3D seg = Lines3D.segmentFromLocations(line, 1, 1);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, seg.getSize(), TEST_EPS);
    }

    @Test
    void testContains_1_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);
        final Vector3D p1 = Vector3D.of(3, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final Segment3D seg = Lines3D.segmentFromPoints(Vector3D.of(1, 1, 1), Vector3D.of(3, 1, 1), TEST_PRECISION);

        // act/assert
        Assertions.assertFalse(seg.contains(Vector3D.of(2, 2, 2)));
    }

    @Test
    void testContains_2_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);
        final Vector3D p1 = Vector3D.of(3, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final Segment3D seg = Lines3D.segmentFromPoints(Vector3D.of(1, 1, 1), Vector3D.of(3, 1, 1), TEST_PRECISION);

        // act/assert
        // removed other assertion
        Assertions.assertFalse(seg.contains(Vector3D.of(0.9, 1, 1)));
    }

    @Test
    void testContains_3_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);
        final Vector3D p1 = Vector3D.of(3, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final Segment3D seg = Lines3D.segmentFromPoints(Vector3D.of(1, 1, 1), Vector3D.of(3, 1, 1), TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(seg.contains(Vector3D.of(3.1, 1, 1)));
    }

    @Test
    void testContains_4_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);
        final Vector3D p1 = Vector3D.of(3, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final Segment3D seg = Lines3D.segmentFromPoints(Vector3D.of(1, 1, 1), Vector3D.of(3, 1, 1), TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(seg.contains(p0));
    }

    @Test
    void testContains_5_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);
        final Vector3D p1 = Vector3D.of(3, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final Segment3D seg = Lines3D.segmentFromPoints(Vector3D.of(1, 1, 1), Vector3D.of(3, 1, 1), TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(seg.contains(p1));
    }

    @Test
    void testContains_6_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);
        final Vector3D p1 = Vector3D.of(3, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final Segment3D seg = Lines3D.segmentFromPoints(Vector3D.of(1, 1, 1), Vector3D.of(3, 1, 1), TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(seg.contains(p0.subtract(delta)));
    }

    @Test
    void testContains_7_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);
        final Vector3D p1 = Vector3D.of(3, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final Segment3D seg = Lines3D.segmentFromPoints(Vector3D.of(1, 1, 1), Vector3D.of(3, 1, 1), TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(seg.contains(p1.add(delta)));
    }

    @Test
    void testContains_8_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 1, 1);
        final Vector3D p1 = Vector3D.of(3, 1, 1);

        final Vector3D delta = Vector3D.of(1e-12, 1e-12, 1e-12);

        final Segment3D seg = Lines3D.segmentFromPoints(Vector3D.of(1, 1, 1), Vector3D.of(3, 1, 1), TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(seg.contains(p0.lerp(p1, 0.5)));
    }

    @Test
    void testGetInterval_1_oe() {
        // arrange
        final Segment3D seg = Lines3D.segmentFromPoints(Vector3D.of(2, -1, 3), Vector3D.of(2, 2, 3), TEST_PRECISION);

        // act
        final Interval interval = seg.getInterval();

        // assert
        Assertions.assertEquals(-1, interval.getMin(), TEST_EPS);
    }

    @Test
    void testGetInterval_2_oe() {
        // arrange
        final Segment3D seg = Lines3D.segmentFromPoints(Vector3D.of(2, -1, 3), Vector3D.of(2, 2, 3), TEST_PRECISION);

        // act
        final Interval interval = seg.getInterval();

        // assert
        // removed other assertion
        Assertions.assertEquals(2, interval.getMax(), TEST_EPS);
    }

    @Test
    void testGetInterval_3_oe() {
        // arrange
        final Segment3D seg = Lines3D.segmentFromPoints(Vector3D.of(2, -1, 3), Vector3D.of(2, 2, 3), TEST_PRECISION);

        // act
        final Interval interval = seg.getInterval();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(seg.getLine().getPrecision(), interval.getMinBoundary().getPrecision());
    }

    @Test
    void testGetInterval_singlePoint_1_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final Segment3D seg = Lines3D.segmentFromLocations(line, 1, 1);

        // act
        final Interval interval = seg.getInterval();

        // assert
        Assertions.assertEquals(1, interval.getMin(), TEST_EPS);
    }

    @Test
    void testGetInterval_singlePoint_2_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final Segment3D seg = Lines3D.segmentFromLocations(line, 1, 1);

        // act
        final Interval interval = seg.getInterval();

        // assert
        // removed other assertion
        Assertions.assertEquals(1, interval.getMax(), TEST_EPS);
    }

    @Test
    void testGetInterval_singlePoint_3_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final Segment3D seg = Lines3D.segmentFromLocations(line, 1, 1);

        // act
        final Interval interval = seg.getInterval();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, interval.getSize(), TEST_EPS);
    }

    @Test
    void testGetInterval_singlePoint_4_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final Segment3D seg = Lines3D.segmentFromLocations(line, 1, 1);

        // act
        final Interval interval = seg.getInterval();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(seg.getLine().getPrecision(), interval.getMinBoundary().getPrecision());
    }

@Test
    void testFromPoints_invalidArgs_1_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(0, 2, 4);
        final Vector3D p2 = Vector3D.of(1e-17, 2, 4);

        // act/assert
        try {
     Lines3D.segmentFromPoints(p1, p1, TEST_PRECISION);
    fail("Expected IllegalArgumentException with message: " + "Line direction cannot be zero");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromPoints_invalidArgs_2_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(0, 2, 4);
        final Vector3D p2 = Vector3D.of(1e-17, 2, 4);

        // act/assert
        // removed other assertion

        try {
     Lines3D.segmentFromPoints(p1, p2, TEST_PRECISION);
    fail("Expected IllegalArgumentException with message: " + "Line direction cannot be zero");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromPoints_givenLine_invalidArgs_1_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 0, 0);
        final Vector3D p1 = Vector3D.of(2, 0, 0);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        try {
     Lines3D.segmentFromPoints(line, Vector3D.NaN, p1);
    fail("Expected IllegalArgumentException with message: " + "Invalid line segment locations: NaN, 2.0");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromPoints_givenLine_invalidArgs_2_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 0, 0);
        final Vector3D p1 = Vector3D.of(2, 0, 0);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion

        try {
     Lines3D.segmentFromPoints(line, p0, Vector3D.NaN);
    fail("Expected IllegalArgumentException with message: " + "Invalid line segment locations: 1.0, NaN");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromPoints_givenLine_invalidArgs_3_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 0, 0);
        final Vector3D p1 = Vector3D.of(2, 0, 0);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion

        try {
     Lines3D.segmentFromPoints(line, Vector3D.NEGATIVE_INFINITY, p1);
    fail("Expected IllegalArgumentException with message: " + "Invalid line segment locations: NaN, 2.0");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromPoints_givenLine_invalidArgs_4_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 0, 0);
        final Vector3D p1 = Vector3D.of(2, 0, 0);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
     Lines3D.segmentFromPoints(line, p0, Vector3D.POSITIVE_INFINITY);
    fail("Expected IllegalArgumentException with message: " + "Invalid line segment locations: 1.0, NaN");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromLocations_invalidArgs_1_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION);

        // act/assert
        try {
     Lines3D.segmentFromLocations(line, Double.NaN, 2);
    fail("Expected IllegalArgumentException with message: " + "Invalid line segment locations: NaN, 2.0");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromLocations_invalidArgs_2_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION);

        // act/assert
        // removed other assertion

        try {
     Lines3D.segmentFromLocations(line, 1, Double.NaN);
    fail("Expected IllegalArgumentException with message: " + "Invalid line segment locations: 1.0, NaN");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromLocations_invalidArgs_3_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion

        try {
     Lines3D.segmentFromLocations(line, Double.NEGATIVE_INFINITY, 2);
    fail("Expected IllegalArgumentException with message: " + "Invalid line segment locations: -Infinity, 2.0");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testFromLocations_invalidArgs_4_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
     Lines3D.segmentFromLocations(line, 1, Double.POSITIVE_INFINITY);
    fail("Expected IllegalArgumentException with message: " + "Invalid line segment locations: 1.0, Infinity");
} catch (IllegalArgumentException e) {
}
    }

}
