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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.twod.ConvexArea;
import org.apache.commons.geometry.euclidean.twod.Line;
import org.apache.commons.geometry.euclidean.twod.LineConvexSubset;
import org.apache.commons.geometry.euclidean.twod.Lines;
import org.apache.commons.geometry.euclidean.twod.RegionBSPTree2D;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.euclidean.twod.path.LinePath;
import org.apache.commons.geometry.euclidean.twod.shape.Parallelogram;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlanesTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testConvexPolygonFromVertices_nonPlanar() {
        // arrange
        final Pattern nonPlanarPattern = Pattern.compile("Points do not define a plane.*");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.convexPolygonFromVertices(Collections.emptyList(), TEST_PRECISION);
        }, IllegalArgumentException.class, nonPlanarPattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.convexPolygonFromVertices(Collections.singletonList(Vector3D.ZERO), TEST_PRECISION);
        }, IllegalArgumentException.class, nonPlanarPattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.convexPolygonFromVertices(Arrays.asList(Vector3D.ZERO, Vector3D.of(1, 0, 0)), TEST_PRECISION);
        }, IllegalArgumentException.class, nonPlanarPattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.convexPolygonFromVertices(
                    Arrays.asList(Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(1, 1e-15, 0)), TEST_PRECISION);
        }, IllegalArgumentException.class, nonPlanarPattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.convexPolygonFromVertices(Arrays.asList(
                        Vector3D.ZERO,
                        Vector3D.of(1, 0, 1),
                        Vector3D.of(1, 1, 0),
                        Vector3D.of(0, 1, 1)
                    ), TEST_PRECISION);
        }, IllegalArgumentException.class, nonPlanarPattern);
    }

    @Test
    void testConvexPolygonFromVertices_nonConvex() {
        // arrange
        final Pattern nonConvexPattern = Pattern.compile("Points do not define a convex region.*");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.convexPolygonFromVertices(Arrays.asList(
                        Vector3D.ZERO,
                        Vector3D.of(2, 0, 0),
                        Vector3D.of(2, 2, 0),
                        Vector3D.of(1, 1, 0),
                        Vector3D.of(1.5, 1, 0)
                    ), TEST_PRECISION);
        }, IllegalArgumentException.class, nonConvexPattern);
    }

    @Test
    void testTriangleFromVertices_degenerateTriangles() {
        // arrange
        final Pattern msg = Pattern.compile("^Points do not define a plane.*");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.triangleFromVertices(
                        Vector3D.ZERO,
                        Vector3D.of(1e-11, 0, 0),
                        Vector3D.of(0, 1e-11, 0),
                        TEST_PRECISION);
        }, IllegalArgumentException.class, msg);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.triangleFromVertices(
                        Vector3D.ZERO,
                        Vector3D.of(1, 0, 0),
                        Vector3D.of(2, 0, 0),
                        TEST_PRECISION);
        }, IllegalArgumentException.class, msg);
    }

    @Test
    void testConvexPolygonToTriangleFan_notEnoughVertices() {
        // arrange
        final String baseMsg = "Cannot create triangle fan: 3 or more vertices are required but found only ";
        final Plane plane = Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.convexPolygonToTriangleFan(plane, Collections.emptyList());
        }, IllegalArgumentException.class, baseMsg + "0");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.convexPolygonToTriangleFan(plane, Collections.singletonList(Vector3D.ZERO));
        }, IllegalArgumentException.class, baseMsg + "1");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.convexPolygonToTriangleFan(plane, Arrays.asList(Vector3D.ZERO, Vector3D.of(1, 0, 0)));
        }, IllegalArgumentException.class, baseMsg + "2");
    }

    @Test
    void testExtrudeVertexLoop_regionsConsistentBetweenExtrusionPlanes() {
        // arrange
        final List<Vector2D> vertices = Arrays.asList(
                Vector2D.of(1, 2),
                Vector2D.of(1, -2),
                Vector2D.of(4, -2),
                Vector2D.of(4, -1),
                Vector2D.of(2, -1),
                Vector2D.of(2, 1),
                Vector2D.of(4, 1),
                Vector2D.of(4, 2),
                Vector2D.of(1, 2)
            );

        final RegionBSPTree2D subspaceTree = LinePath.fromVertexLoop(vertices, TEST_PRECISION).toTree();

        final double subspaceSize = subspaceTree.getSize();
        final Vector2D subspaceCentroid = subspaceTree.getCentroid();

        final double extrusionLength = 2;
        final double expectedSize = subspaceSize * extrusionLength;

        final Vector3D planePt = Vector3D.of(-1, 2, -3);

        EuclideanTestUtils.permuteSkipZero(-2, 2, 1, (x, y, z) -> {
            final Vector3D normal = Vector3D.of(x, y, z);
            final EmbeddingPlane plane = Planes.fromPointAndNormal(planePt, normal, TEST_PRECISION).getEmbedding();

            final Vector3D baseCentroid = plane.toSpace(subspaceCentroid);

            final Vector3D plusExtrusionVector = normal.withNorm(extrusionLength);
            final Vector3D minusExtrusionVector = plusExtrusionVector.negate();

            // act
            final RegionBSPTree3D extrudePlus = RegionBSPTree3D.from(
                    Planes.extrudeVertexLoop(vertices, plane, plusExtrusionVector, TEST_PRECISION));
            final RegionBSPTree3D extrudeMinus = RegionBSPTree3D.from(
                    Planes.extrudeVertexLoop(vertices, plane, minusExtrusionVector, TEST_PRECISION));

            // assert
            Assertions.assertEquals(expectedSize, extrudePlus.getSize(), TEST_EPS);
            EuclideanTestUtils.assertCoordinatesEqual(baseCentroid.add(plusExtrusionVector.multiply(0.5)),
                    extrudePlus.getCentroid(), TEST_EPS);

            Assertions.assertEquals(expectedSize, extrudeMinus.getSize(), TEST_EPS);
            EuclideanTestUtils.assertCoordinatesEqual(baseCentroid.add(minusExtrusionVector.multiply(0.5)),
                    extrudeMinus.getCentroid(), TEST_EPS);
        });
    }

    @Test
    void testExtrude_invalidExtrusionVector() {
        // arrange
        final List<Vector2D> vertices = new ArrayList<>();
        final LinePath path = LinePath.empty();
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        final Pattern errorPattern = Pattern.compile("^Extrusion vector produces regions of zero size.*");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.extrudeVertexLoop(vertices, plane, Vector3D.of(1e-16, 0, 0), TEST_PRECISION);
        }, IllegalArgumentException.class, errorPattern);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.extrudeVertexLoop(vertices, plane, Vector3D.of(4, 1e-16, 0), TEST_PRECISION);
        }, IllegalArgumentException.class, errorPattern);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.extrudeVertexLoop(vertices, plane, Vector3D.of(1e-16, 5, 0), TEST_PRECISION);
        }, IllegalArgumentException.class, errorPattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.extrude(path, plane, Vector3D.of(1e-16, 0, 0), TEST_PRECISION);
        }, IllegalArgumentException.class, errorPattern);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.extrude(path, plane, Vector3D.of(4, 1e-16, 0), TEST_PRECISION);
        }, IllegalArgumentException.class, errorPattern);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.extrude(path, plane, Vector3D.of(1e-16, 5, 0), TEST_PRECISION);
        }, IllegalArgumentException.class, errorPattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.extrude(tree, plane, Vector3D.of(1e-16, 0, 0), TEST_PRECISION);
        }, IllegalArgumentException.class, errorPattern);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.extrude(tree, plane, Vector3D.of(4, 1e-16, 0), TEST_PRECISION);
        }, IllegalArgumentException.class, errorPattern);
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Planes.extrude(tree, plane, Vector3D.of(1e-16, 5, 0), TEST_PRECISION);
        }, IllegalArgumentException.class, errorPattern);
    }

    private static void checkPlane(final Plane plane, final Vector3D origin, Vector3D u, Vector3D v) {
        u = u.normalize();
        v = v.normalize();
        final Vector3D w = u.cross(v);

        EuclideanTestUtils.assertCoordinatesEqual(origin, plane.getOrigin(), TEST_EPS);
        Assertions.assertTrue(plane.contains(origin));

        EuclideanTestUtils.assertCoordinatesEqual(w, plane.getNormal(), TEST_EPS);
        Assertions.assertEquals(1.0, plane.getNormal().norm(), TEST_EPS);

        final double offset = plane.getOriginOffset();
        Assertions.assertEquals(Vector3D.ZERO.distance(plane.getOrigin()), Math.abs(offset), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(origin, plane.getNormal().multiply(-offset), TEST_EPS);
    }

    private static void checkPoints(final PlaneConvexSubset sp, final RegionLocation loc, final Vector3D... pts) {
        for (final Vector3D pt : pts) {
            Assertions.assertEquals(loc, sp.classify(pt), "Unexpected location for point " + pt);
        }
    }

    private static void assertConvexAreasEqual(final ConvexArea a, final ConvexArea b) {
        final List<LineConvexSubset> aBoundaries = new ArrayList<>(a.getBoundaries());
        final List<LineConvexSubset> bBoundaries = new ArrayList<>(b.getBoundaries());

        Assertions.assertEquals(aBoundaries.size(), bBoundaries.size());

        for (final LineConvexSubset aBoundary : aBoundaries) {
            if (!hasEquivalentSubLine(aBoundary, bBoundaries)) {
                Assertions.fail("Failed to find equivalent subline for " + aBoundary);
            }
        }
    }

    private static boolean hasEquivalentSubLine(final LineConvexSubset target, final Collection<? extends LineConvexSubset> subsets) {
        final Line line = target.getLine();
        final double start = target.getSubspaceStart();
        final double end = target.getSubspaceEnd();

        for (final LineConvexSubset subset : subsets) {
            if (line.eq(subset.getLine(), TEST_PRECISION) &&
                    TEST_PRECISION.eq(start, subset.getSubspaceStart()) &&
                    TEST_PRECISION.eq(end, subset.getSubspaceEnd())) {
                return true;
            }
        }

        return false;
    }

    @Test
    void testIndexedTriangles_invalidArgs_4_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(1, 1, 0),
            Vector3D.of(2, 0, 0)
        };

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    Planes.indexedTriangles(vertices, new int[][] { {0, 1, 10} }, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    void testIndexedTriangles_invalidArgs_5_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(1, 1, 0),
            Vector3D.of(2, 0, 0)
        };

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        try {
    Planes.indexedTriangles(new ArrayList<>(Arrays.asList(vertices)), new int[][] { {0, 1, 10} }, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    void testIndexedConvexPolygons_invalidArgs_3_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(1, 1, 0),
            Vector3D.of(2, 0, 0)
        };

        // act/assert
        // removed other assertion

        // removed other assertion

        try {
    Planes.indexedConvexPolygons(vertices, new int[][] { {0, 1, 10} }, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    void testIndexedConvexPolygons_invalidArgs_4_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(1, 1, 0),
            Vector3D.of(2, 0, 0)
        };

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion
        try {
    Planes.indexedConvexPolygons(new ArrayList<>(Arrays.asList(vertices)), new int[][] { {0, 1, 10} }, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    void testExtrudeVertexLoop_invalidVertexList_1_oe() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act/assert
        try {
    Planes.extrudeVertexLoop(Collections.singletonList(Vector2D.ZERO), plane, extrusionVector, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testExtrudeVertexLoop_invalidVertexList_2_oe() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act/assert
        // removed other assertion
        try {
    Planes.extrudeVertexLoop(Arrays.asList(Vector2D.ZERO, Vector2D.of(0, 1e-16)), plane, extrusionVector, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

}
