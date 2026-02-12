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
package org.apache.commons.geometry.euclidean.threed;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.Transform;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.threed.line.Lines3D;
import org.apache.commons.geometry.euclidean.twod.ConvexArea;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConvexVolumeTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testTriangleStream_infinite() {
        // arrange
        final Pattern pattern = Pattern.compile("^Cannot convert infinite plane subset to triangles: .*");

        final ConvexVolume half = ConvexVolume.fromBounds(
                Planes.fromNormal(Vector3D.Unit.MINUS_X, TEST_PRECISION)
            );

        final ConvexVolume quadrant = ConvexVolume.fromBounds(
                    Planes.fromNormal(Vector3D.Unit.MINUS_X, TEST_PRECISION),
                    Planes.fromNormal(Vector3D.Unit.MINUS_Y, TEST_PRECISION),
                    Planes.fromNormal(Vector3D.Unit.MINUS_Z, TEST_PRECISION)
                );

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            half.triangleStream().collect(Collectors.toList());
        }, IllegalStateException.class, pattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            quadrant.triangleStream().collect(Collectors.toList());
        }, IllegalStateException.class, pattern);
    }

    @Test
    void testGetBounds_hasBounds() {
        // arrange
        final ConvexVolume vol = rect(Vector3D.of(1, 1, 1), 0.5, 1, 2);

        // act
        final Bounds3D bounds = vol.getBounds();

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(0.5, 0, -1), bounds.getMin(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1.5, 2, 3), bounds.getMax(), TEST_EPS);
    }

    @Test
    void testLinecast_full() {
        // arrange
        final ConvexVolume volume = ConvexVolume.full();

        // act/assert
        LinecastChecker3D.with(volume)
            .expectNothing()
            .whenGiven(Lines3D.fromPoints(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION));

        LinecastChecker3D.with(volume)
            .expectNothing()
            .whenGiven(Lines3D.segmentFromPoints(Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_X, TEST_PRECISION));
    }

    @Test
    void testLinecast() {
        // arrange
        final ConvexVolume volume = rect(Vector3D.of(0.5, 0.5, 0.5), 0.5, 0.5, 0.5);

        // act/assert
        LinecastChecker3D.with(volume)
            .expectNothing()
            .whenGiven(Lines3D.fromPoints(Vector3D.of(0, 5, 5), Vector3D.of(1, 5, 5), TEST_PRECISION));

        LinecastChecker3D.with(volume)
            .expect(Vector3D.ZERO, Vector3D.Unit.MINUS_X)
            .and(Vector3D.ZERO, Vector3D.Unit.MINUS_Y)
            .and(Vector3D.ZERO, Vector3D.Unit.MINUS_Z)
            .and(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Z)
            .and(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Y)
            .and(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X)
            .whenGiven(Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(1, 1, 1), TEST_PRECISION));

        LinecastChecker3D.with(volume)
            .expect(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Z)
            .and(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Y)
            .and(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X)
            .whenGiven(Lines3D.segmentFromPoints(Vector3D.of(0.5, 0.5, 0.5), Vector3D.of(1, 1, 1), TEST_PRECISION));
    }

    private static ConvexVolume rect(final Vector3D center, final double xDelta, final double yDelta, final double zDelta) {
        final List<Plane> planes = Arrays.asList(
                    Planes.fromPointAndNormal(center.add(Vector3D.of(xDelta, 0, 0)), Vector3D.Unit.PLUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(center.add(Vector3D.of(-xDelta, 0, 0)), Vector3D.Unit.MINUS_X, TEST_PRECISION),

                    Planes.fromPointAndNormal(center.add(Vector3D.of(0, yDelta, 0)), Vector3D.Unit.PLUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(center.add(Vector3D.of(0, -yDelta, 0)), Vector3D.Unit.MINUS_Y, TEST_PRECISION),

                    Planes.fromPointAndNormal(center.add(Vector3D.of(0, 0, zDelta)), Vector3D.Unit.PLUS_Z, TEST_PRECISION),
                    Planes.fromPointAndNormal(center.add(Vector3D.of(0, 0, -zDelta)), Vector3D.Unit.MINUS_Z, TEST_PRECISION)
                );

        return ConvexVolume.fromBounds(planes);
    }


}
