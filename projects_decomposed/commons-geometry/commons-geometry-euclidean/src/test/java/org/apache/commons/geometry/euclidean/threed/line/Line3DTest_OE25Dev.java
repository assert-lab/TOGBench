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
import org.apache.commons.geometry.core.Transform;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.oned.Vector1D;
import org.apache.commons.geometry.euclidean.threed.AffineTransformMatrix3D;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.euclidean.threed.rotation.QuaternionRotation;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Line3DTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testFromPointAndDirection_illegalDirectionNorm() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.ZERO, TEST_PRECISION);
        }, IllegalArgumentException.class, "Line direction cannot be zero");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.of(1e-12, 1e-12, 1e-12), TEST_PRECISION);
        }, IllegalArgumentException.class, "Line direction cannot be zero");
    }

    @Test
    void testPointAt() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0, 0, -1), Vector3D.of(4, 3, 0), TEST_PRECISION);

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(line.getOrigin(), line.pointAt(0.0), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(4, 3, -1), line.pointAt(5.0), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-4, -3, -1), line.pointAt(-5.0), TEST_EPS);
    }

    @Test
    void testToSpace() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0, 0, -1), Vector3D.of(4, 3, 0), TEST_PRECISION);

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(line.getOrigin(), line.toSpace(Vector1D.of(0.0)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(4, 3, -1), line.toSpace(Vector1D.of(5.0)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-4, -3, -1), line.toSpace(Vector1D.of(-5.0)), TEST_EPS);
    }

    @Test
    void testReverse() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.of(1653345.6696423641, 6170370.041579291, 90000),
                             Vector3D.of(1650757.5050732433, 6160710.879908984, 0.9),
                             TEST_PRECISION);
        final Vector3D expected = line.getDirection().negate();

        // act
        final Line3D reversed = line.reverse();

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(expected, reversed.getDirection(), TEST_EPS);
    }

    @Test
    void testSpan_toString() {
        // arrange
        final LineConvexSubset3D span = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION)
                .span();

        // act
        final String str = span.toString();

        // assert
        GeometryTestUtils.assertContains("LineSpanningSubset3D[origin= (0", str);
        GeometryTestUtils.assertContains(", direction= (1", str);
    }

    @Test
    void testFromPoints_pointsTooClose_1_oe() {
        // act/assert
        try {
    Lines3D.fromPoints(Vector3D.of(1, 1, 1), Vector3D.of(1, 1, 1 + 1e-16), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
