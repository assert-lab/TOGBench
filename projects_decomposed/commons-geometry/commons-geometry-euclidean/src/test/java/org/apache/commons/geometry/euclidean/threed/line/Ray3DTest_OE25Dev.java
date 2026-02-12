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


}
