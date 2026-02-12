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
import org.apache.commons.geometry.core.Transform;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.threed.rotation.QuaternionRotation;
import org.apache.commons.geometry.euclidean.twod.ConvexArea;
import org.apache.commons.geometry.euclidean.twod.Lines;
import org.apache.commons.geometry.euclidean.twod.RegionBSPTree2D;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.euclidean.twod.shape.Parallelogram;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmbeddedTreePlaneSubsetTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static final EmbeddingPlane XY_PLANE = Planes.fromPointAndPlaneVectors(Vector3D.ZERO,
            Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);

    @Test
    void testSpaceConversion() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(1, 0, 0),
                Vector3D.Unit.PLUS_Y, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        final EmbeddedTreePlaneSubset ps = new EmbeddedTreePlaneSubset(plane, true);

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
            new EmbeddedTreePlaneSubset(XY_PLANE, true).toTriangles();
        }, IllegalStateException.class, pattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            final EmbeddedTreePlaneSubset halfSpace = new EmbeddedTreePlaneSubset(XY_PLANE, false);
            halfSpace.getSubspaceRegion().getRoot()
                .insertCut(Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION));

            halfSpace.toTriangles();
        }, IllegalStateException.class, pattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            final RegionBSPTree2D tree = RegionBSPTree2D.empty();
            tree.insert(Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION));
            tree.insert(Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(0, 1), TEST_PRECISION));

            final EmbeddedTreePlaneSubset halfSpaceWithVertices = new EmbeddedTreePlaneSubset(XY_PLANE, tree);

            halfSpaceWithVertices.toTriangles();
        }, IllegalStateException.class, pattern);
    }

    @Test
    void testGetBounds_hasBounds() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_X, TEST_PRECISION);

        final EmbeddedTreePlaneSubset ps = new EmbeddedTreePlaneSubset(plane, false);
        ps.getSubspaceRegion().add(ConvexArea.convexPolygonFromVertices(Arrays.asList(
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
        final EmbeddedTreePlaneSubset ps = new EmbeddedTreePlaneSubset(
                Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION).getEmbedding());

        // act
        final String str = ps.toString();

        // assert
        GeometryTestUtils.assertContains("EmbeddedTreePlaneSubset[plane= EmbeddingPlane[", str);
        GeometryTestUtils.assertContains("subspaceRegion= RegionBSPTree2D[", str);
    }

    private static void checkPoints(final EmbeddedTreePlaneSubset ps, final RegionLocation loc, final Vector3D... pts) {
        for (final Vector3D pt : pts) {
            Assertions.assertEquals(loc, ps.classify(pt), "Unexpected location for point " + pt);
        }
    }

    @Test
    void testAddMethods_validatesPlane_1_oe() {
        // arrange
        final EmbeddedTreePlaneSubset ps = new EmbeddedTreePlaneSubset(XY_PLANE, false);

        // act/assert
        try {
    ps.add(Planes.subsetFromConvexArea( Planes.fromPointAndPlaneVectors(Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.MINUS_Z, TEST_PRECISION), ConvexArea.full()));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAddMethods_validatesPlane_2_oe() {
        // arrange
        final EmbeddedTreePlaneSubset ps = new EmbeddedTreePlaneSubset(XY_PLANE, false);

        // act/assert
        // removed other assertion
        try {
    ps.add(new EmbeddedTreePlaneSubset( Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1), Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION), false));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
