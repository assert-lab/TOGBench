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

class Segment3DTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testFromPoints_invalidArgs() {
        // arrange
        final Vector3D p1 = Vector3D.of(0, 2, 4);
        final Vector3D p2 = Vector3D.of(1e-17, 2, 4);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.segmentFromPoints(p1, p1, TEST_PRECISION);
        }, IllegalArgumentException.class, "Line direction cannot be zero");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.segmentFromPoints(p1, p2, TEST_PRECISION);
        }, IllegalArgumentException.class, "Line direction cannot be zero");
    }

    @Test
    void testFromPoints_givenLine_invalidArgs() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 0, 0);
        final Vector3D p1 = Vector3D.of(2, 0, 0);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.segmentFromPoints(line, Vector3D.NaN, p1);
        }, IllegalArgumentException.class, "Invalid line segment locations: NaN, 2.0");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.segmentFromPoints(line, p0, Vector3D.NaN);
        }, IllegalArgumentException.class, "Invalid line segment locations: 1.0, NaN");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.segmentFromPoints(line, Vector3D.NEGATIVE_INFINITY, p1);
        }, IllegalArgumentException.class, "Invalid line segment locations: NaN, 2.0");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.segmentFromPoints(line, p0, Vector3D.POSITIVE_INFINITY);
        }, IllegalArgumentException.class, "Invalid line segment locations: 1.0, NaN");
    }

    @Test
    void testFromLocations_invalidArgs() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.segmentFromLocations(line, Double.NaN, 2);
        }, IllegalArgumentException.class, "Invalid line segment locations: NaN, 2.0");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.segmentFromLocations(line, 1, Double.NaN);
        }, IllegalArgumentException.class, "Invalid line segment locations: 1.0, NaN");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.segmentFromLocations(line, Double.NEGATIVE_INFINITY, 2);
        }, IllegalArgumentException.class, "Invalid line segment locations: -Infinity, 2.0");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.segmentFromLocations(line, 1, Double.POSITIVE_INFINITY);
        }, IllegalArgumentException.class, "Invalid line segment locations: 1.0, Infinity");
    }

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


}
