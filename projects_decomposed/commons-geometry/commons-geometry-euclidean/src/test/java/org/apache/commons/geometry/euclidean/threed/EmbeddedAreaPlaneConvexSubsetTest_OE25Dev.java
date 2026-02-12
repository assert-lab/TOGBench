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

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.threed.rotation.QuaternionRotation;
import org.apache.commons.geometry.euclidean.twod.ConvexArea;
import org.apache.commons.geometry.euclidean.twod.Lines;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.euclidean.twod.path.LinePath;
import org.apache.commons.geometry.euclidean.twod.shape.Parallelogram;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmbeddedAreaPlaneConvexSubsetTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static final EmbeddingPlane XY_PLANE_Z1 = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
            Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);

    @Test
    void testSpaceConversion() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(1, 0, 0),
                Vector3D.Unit.PLUS_Y, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(plane, ConvexArea.full());

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 2), ps.toSubspace(Vector3D.of(-5, 1, 2)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, -2, 4), ps.toSpace(Vector2D.of(-2, 4)), TEST_EPS);
    }
    @Test
    void testToTriangles_infinite() {
        // arrange
        final Pattern pattern = Pattern.compile("^Cannot convert infinite plane subset to triangles: .*");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, ConvexArea.full()).toTriangles();
        }, IllegalStateException.class, pattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            final ConvexArea area = ConvexArea.fromBounds(Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION));
            final EmbeddedAreaPlaneConvexSubset halfSpace = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area);

            halfSpace.toTriangles();
        }, IllegalStateException.class, pattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            final ConvexArea area = ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.ZERO, 0.5 * Math.PI, TEST_PRECISION));

            final EmbeddedAreaPlaneConvexSubset halfSpaceWithVertices = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area);

            halfSpaceWithVertices.toTriangles();
        }, IllegalStateException.class, pattern);
    }

    @Test
    void testClassify() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                Parallelogram.builder(TEST_PRECISION)
                    .setPosition(Vector2D.of(2, 3))
                    .setScale(2, 2)
                    .build());

        // act/assert
        checkPoints(ps, RegionLocation.INSIDE, Vector3D.of(2, 3, 1));
        checkPoints(ps, RegionLocation.BOUNDARY,
                Vector3D.of(1, 3, 1), Vector3D.of(3, 3, 1),
                Vector3D.of(2, 2, 1), Vector3D.of(2, 4, 1));
        checkPoints(ps, RegionLocation.OUTSIDE,
                Vector3D.of(2, 3, 0), Vector3D.of(2, 3, 2),
                Vector3D.of(0, 3, 1), Vector3D.of(4, 3, 1),
                Vector3D.of(2, 1, 1), Vector3D.of(2, 5, 1));
    }

    @Test
    void testClosest() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                Parallelogram.builder(TEST_PRECISION)
                    .setPosition(Vector2D.of(2, 3))
                    .setScale(2, 2)
                    .build());

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(2, 3, 1), ps.closest(Vector3D.of(2, 3, 1)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(2, 3, 1), ps.closest(Vector3D.of(2, 3, 100)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, 2, 1),
                ps.closest(Vector3D.of(-100, -100, -100)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(3, 3.5, 1),
                ps.closest(Vector3D.of(100, 3.5, 100)), TEST_EPS);
    }

    @Test
    void testGetBounds_hasBounds() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_X, TEST_PRECISION);

        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(plane,
                ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1), Vector2D.of(1, 2)
                ), TEST_PRECISION));

        // act
        final Bounds3D bounds = ps.getBounds();

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-2, 1, 1), bounds.getMin(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-1, 2, 1), bounds.getMax(), TEST_EPS);
    }

    @Test
    void testToString() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                ConvexArea.convexPolygonFromVertices(Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1)),
                        TEST_PRECISION));

        // act
        final String str = ps.toString();

        // assert
        GeometryTestUtils.assertContains("EmbeddedAreaPlaneConvexSubset[plane= EmbeddingPlane[", str);
        GeometryTestUtils.assertContains("subspaceRegion= ConvexArea[", str);
    }

    private static void checkPoints(final EmbeddedAreaPlaneConvexSubset ps, final RegionLocation loc, final Vector3D... pts) {
        for (final Vector3D pt : pts) {
            Assertions.assertEquals(loc, ps.classify(pt), "Unexpected location for point " + pt);
        }
    }


}
