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
    void testSubsetFromConvexArea_1_oe() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 0),
                    Vector2D.of(3, 0),
                    Vector2D.of(3, 1),
                    Vector2D.of(1, 1)
                ), TEST_PRECISION);

        // act
        final PlaneConvexSubset sp = Planes.subsetFromConvexArea(plane, area);

        // assert
        Assertions.assertFalse(sp.isFull());
    }

@Test
    void testSubsetFromConvexArea_2_oe() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 0),
                    Vector2D.of(3, 0),
                    Vector2D.of(3, 1),
                    Vector2D.of(1, 1)
                ), TEST_PRECISION);

        // act
        final PlaneConvexSubset sp = Planes.subsetFromConvexArea(plane, area);

        // assert
        // removed other assertion
        Assertions.assertFalse(sp.isEmpty());
    }

@Test
    void testSubsetFromConvexArea_3_oe() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 0),
                    Vector2D.of(3, 0),
                    Vector2D.of(3, 1),
                    Vector2D.of(1, 1)
                ), TEST_PRECISION);

        // act
        final PlaneConvexSubset sp = Planes.subsetFromConvexArea(plane, area);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(sp.isFinite());
    }

@Test
    void testSubsetFromConvexArea_4_oe() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 0),
                    Vector2D.of(3, 0),
                    Vector2D.of(3, 1),
                    Vector2D.of(1, 1)
                ), TEST_PRECISION);

        // act
        final PlaneConvexSubset sp = Planes.subsetFromConvexArea(plane, area);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(2, sp.getSize(), TEST_EPS);
    }

@Test
    void testSubsetFromConvexArea_5_oe() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 0),
                    Vector2D.of(3, 0),
                    Vector2D.of(3, 1),
                    Vector2D.of(1, 1)
                ), TEST_PRECISION);

        // act
        final PlaneConvexSubset sp = Planes.subsetFromConvexArea(plane, area);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertSame(plane, sp.getPlane());
    }

@Test
    void testSubsetFromConvexArea_6_oe() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 0),
                    Vector2D.of(3, 0),
                    Vector2D.of(3, 1),
                    Vector2D.of(1, 1)
                ), TEST_PRECISION);

        // act
        final PlaneConvexSubset sp = Planes.subsetFromConvexArea(plane, area);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertSame(plane, sp.getHyperplane());
    }

@Test
    void testConvexPolygonFromVertices_1_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 0, 0);
        final Vector3D p1 = Vector3D.of(1, 1, 0);
        final Vector3D p2 = Vector3D.of(1, 1, 2);

        // act
        final PlaneConvexSubset sp = Planes.convexPolygonFromVertices(Arrays.asList(p0, p1, p2), TEST_PRECISION);

        // assert
        Assertions.assertTrue(sp instanceof Triangle3D);
    }

@Test
    void testConvexPolygonFromVertices_2_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 0, 0);
        final Vector3D p1 = Vector3D.of(1, 1, 0);
        final Vector3D p2 = Vector3D.of(1, 1, 2);

        // act
        final PlaneConvexSubset sp = Planes.convexPolygonFromVertices(Arrays.asList(p0, p1, p2), TEST_PRECISION);

        // assert
        // removed other assertion

        Assertions.assertFalse(sp.isFull());
    }

@Test
    void testConvexPolygonFromVertices_3_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 0, 0);
        final Vector3D p1 = Vector3D.of(1, 1, 0);
        final Vector3D p2 = Vector3D.of(1, 1, 2);

        // act
        final PlaneConvexSubset sp = Planes.convexPolygonFromVertices(Arrays.asList(p0, p1, p2), TEST_PRECISION);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(sp.isEmpty());
    }

@Test
    void testConvexPolygonFromVertices_4_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 0, 0);
        final Vector3D p1 = Vector3D.of(1, 1, 0);
        final Vector3D p2 = Vector3D.of(1, 1, 2);

        // act
        final PlaneConvexSubset sp = Planes.convexPolygonFromVertices(Arrays.asList(p0, p1, p2), TEST_PRECISION);

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(sp.isFinite());
    }

@Test
    void testConvexPolygonFromVertices_5_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 0, 0);
        final Vector3D p1 = Vector3D.of(1, 1, 0);
        final Vector3D p2 = Vector3D.of(1, 1, 2);

        // act
        final PlaneConvexSubset sp = Planes.convexPolygonFromVertices(Arrays.asList(p0, p1, p2), TEST_PRECISION);

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(3, sp.getVertices().size());
    }

@Test
    void testConvexPolygonFromVertices_7_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 0, 0);
        final Vector3D p1 = Vector3D.of(1, 1, 0);
        final Vector3D p2 = Vector3D.of(1, 1, 2);

        // act
        final PlaneConvexSubset sp = Planes.convexPolygonFromVertices(Arrays.asList(p0, p1, p2), TEST_PRECISION);

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, sp.getSize(), TEST_EPS);
    }

@Test
    void testConvexPolygonFromVertices_duplicatePoints_1_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 0, 0);
        final Vector3D p1 = Vector3D.of(1, 1, 0);
        final Vector3D p2 = Vector3D.of(1, 1, 2);
        final Vector3D p3 = Vector3D.of(1, 0, 2);

        // act
        final PlaneConvexSubset sp = Planes.convexPolygonFromVertices(Arrays.asList(
                    p0,
                    Vector3D.of(1, 1e-15, 0),
                    p1,
                    p2,
                    p3,
                    Vector3D.of(1, 1e-15, 2),
                    Vector3D.of(1, 0, 1e-15)
                ), TEST_PRECISION);

        // assert
        Assertions.assertTrue(sp instanceof VertexListConvexPolygon3D);
    }

@Test
    void testConvexPolygonFromVertices_duplicatePoints_2_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 0, 0);
        final Vector3D p1 = Vector3D.of(1, 1, 0);
        final Vector3D p2 = Vector3D.of(1, 1, 2);
        final Vector3D p3 = Vector3D.of(1, 0, 2);

        // act
        final PlaneConvexSubset sp = Planes.convexPolygonFromVertices(Arrays.asList(
                    p0,
                    Vector3D.of(1, 1e-15, 0),
                    p1,
                    p2,
                    p3,
                    Vector3D.of(1, 1e-15, 2),
                    Vector3D.of(1, 0, 1e-15)
                ), TEST_PRECISION);

        // assert
        // removed other assertion

        Assertions.assertFalse(sp.isFull());
    }

@Test
    void testConvexPolygonFromVertices_duplicatePoints_3_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 0, 0);
        final Vector3D p1 = Vector3D.of(1, 1, 0);
        final Vector3D p2 = Vector3D.of(1, 1, 2);
        final Vector3D p3 = Vector3D.of(1, 0, 2);

        // act
        final PlaneConvexSubset sp = Planes.convexPolygonFromVertices(Arrays.asList(
                    p0,
                    Vector3D.of(1, 1e-15, 0),
                    p1,
                    p2,
                    p3,
                    Vector3D.of(1, 1e-15, 2),
                    Vector3D.of(1, 0, 1e-15)
                ), TEST_PRECISION);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(sp.isEmpty());
    }

@Test
    void testConvexPolygonFromVertices_duplicatePoints_4_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 0, 0);
        final Vector3D p1 = Vector3D.of(1, 1, 0);
        final Vector3D p2 = Vector3D.of(1, 1, 2);
        final Vector3D p3 = Vector3D.of(1, 0, 2);

        // act
        final PlaneConvexSubset sp = Planes.convexPolygonFromVertices(Arrays.asList(
                    p0,
                    Vector3D.of(1, 1e-15, 0),
                    p1,
                    p2,
                    p3,
                    Vector3D.of(1, 1e-15, 2),
                    Vector3D.of(1, 0, 1e-15)
                ), TEST_PRECISION);

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(sp.isFinite());
    }

@Test
    void testConvexPolygonFromVertices_duplicatePoints_5_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 0, 0);
        final Vector3D p1 = Vector3D.of(1, 1, 0);
        final Vector3D p2 = Vector3D.of(1, 1, 2);
        final Vector3D p3 = Vector3D.of(1, 0, 2);

        // act
        final PlaneConvexSubset sp = Planes.convexPolygonFromVertices(Arrays.asList(
                    p0,
                    Vector3D.of(1, 1e-15, 0),
                    p1,
                    p2,
                    p3,
                    Vector3D.of(1, 1e-15, 2),
                    Vector3D.of(1, 0, 1e-15)
                ), TEST_PRECISION);

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(4, sp.getVertices().size());
    }

@Test
    void testConvexPolygonFromVertices_duplicatePoints_7_oe() {
        // arrange
        final Vector3D p0 = Vector3D.of(1, 0, 0);
        final Vector3D p1 = Vector3D.of(1, 1, 0);
        final Vector3D p2 = Vector3D.of(1, 1, 2);
        final Vector3D p3 = Vector3D.of(1, 0, 2);

        // act
        final PlaneConvexSubset sp = Planes.convexPolygonFromVertices(Arrays.asList(
                    p0,
                    Vector3D.of(1, 1e-15, 0),
                    p1,
                    p2,
                    p3,
                    Vector3D.of(1, 1e-15, 2),
                    Vector3D.of(1, 0, 1e-15)
                ), TEST_PRECISION);

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(2, sp.getSize(), TEST_EPS);
    }

@Test
    void testTriangleFromVertices_1_oe() {
        // act
        final Triangle3D tri = Planes.triangleFromVertices(
                Vector3D.of(1, 1, 1),
                Vector3D.of(2, 1, 1),
                Vector3D.of(2, 1, 2), TEST_PRECISION);

        // assert
        Assertions.assertEquals(0.5, tri.getSize(), TEST_EPS);
    }

@Test
    void testIndexedTriangles_singleTriangle_noFaces_1_oe() {
        // act
        final List<Triangle3D> tris = Planes.indexedTriangles(new Vector3D[0], new int[0][], TEST_PRECISION);

        // assert
        Assertions.assertEquals(0, tris.size());
    }

@Test
    void testIndexedTriangles_singleTriangle_1_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(1, 1, 0)
        };

        final int[][] faceIndices = {
            {0, 2, 1}
        };

        // act
        final List<Triangle3D> tris = Planes.indexedTriangles(Arrays.asList(vertices), faceIndices, TEST_PRECISION);

        // assert
        Assertions.assertEquals(1, tris.size());
    }

@Test
    void testIndexedTriangles_multipleTriangles_1_oe() {
        // arrange
        // define a square pyramind
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(1, 1, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0.5, 0.5, 4)
        };

        final int[][] faceIndices = {
            {0, 2, 1},
            {0, 3, 2},
            {0, 1, 4},
            {1, 2, 4},
            {2, 3, 4},
            {3, 0, 4}
        };

        // act
        final List<Triangle3D> tris = Planes.indexedTriangles(vertices, faceIndices, TEST_PRECISION);

        // assert
        Assertions.assertEquals(6, tris.size());
    }

@Test
    void testIndexedTriangles_multipleTriangles_2_oe() {
        // arrange
        // define a square pyramind
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(1, 1, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0.5, 0.5, 4)
        };

        final int[][] faceIndices = {
            {0, 2, 1},
            {0, 3, 2},
            {0, 1, 4},
            {1, 2, 4},
            {2, 3, 4},
            {3, 0, 4}
        };

        // act
        final List<Triangle3D> tris = Planes.indexedTriangles(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(tris);
        Assertions.assertEquals(4 / 3.0, tree.getSize(), TEST_EPS);
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

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> Planes.indexedTriangles(vertices, new int[][] { {0, 1, 10} }, TEST_PRECISION));
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
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> Planes.indexedTriangles(new ArrayList<>(Arrays.asList(vertices)), new int[][] { {0, 1, 10} }, TEST_PRECISION));
    }

@Test
    void testIndexedConvexPolygons_singleTriangle_noFaces_1_oe() {
        // act
        final List<ConvexPolygon3D> polys = Planes.indexedConvexPolygons(new Vector3D[0], new int[0][], TEST_PRECISION);

        // assert
        Assertions.assertEquals(0, polys.size());
    }

@Test
    void testIndexedConvexPolygons_singleSquare_1_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(1, 1, 0),
            Vector3D.of(0, 1, 0)
        };

        final int[][] faceIndices = {
            {0, 3, 2, 1}
        };

        // act
        final List<ConvexPolygon3D> polys = Planes.indexedConvexPolygons(Arrays.asList(vertices), faceIndices,
                TEST_PRECISION);

        // assert
        Assertions.assertEquals(1, polys.size());
    }

@Test
    void testIndexedConvexPolygons_mixedPolygons_1_oe() {
        // arrange
        // define a square pyramind
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(1, 1, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0.5, 0.5, 4)
        };

        final int[][] faceIndices = {
            {0, 3, 2, 1},
            {0, 1, 4},
            {1, 2, 4},
            {2, 3, 4},
            {3, 0, 4}
        };

        // act
        final List<ConvexPolygon3D> polys = Planes.indexedConvexPolygons(vertices, faceIndices, TEST_PRECISION);

        // assert
        Assertions.assertEquals(5, polys.size());
    }

@Test
    void testIndexedConvexPolygons_mixedPolygons_2_oe() {
        // arrange
        // define a square pyramind
        final Vector3D[] vertices = {
            Vector3D.ZERO,
            Vector3D.of(1, 0, 0),
            Vector3D.of(1, 1, 0),
            Vector3D.of(0, 1, 0),
            Vector3D.of(0.5, 0.5, 4)
        };

        final int[][] faceIndices = {
            {0, 3, 2, 1},
            {0, 1, 4},
            {1, 2, 4},
            {2, 3, 4},
            {3, 0, 4}
        };

        // act
        final List<ConvexPolygon3D> polys = Planes.indexedConvexPolygons(vertices, faceIndices, TEST_PRECISION);

        // assert
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(polys);
        Assertions.assertEquals(4 / 3.0, tree.getSize(), TEST_EPS);
    }

@Test
    void testIndexedConvexPolygons_cube_1_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.of(-0.5, -0.5, -0.5),
            Vector3D.of(0.5, -0.5, -0.5),
            Vector3D.of(0.5, 0.5, -0.5),
            Vector3D.of(-0.5, 0.5, -0.5),

            Vector3D.of(-0.5, -0.5, 0.5),
            Vector3D.of(0.5, -0.5, 0.5),
            Vector3D.of(0.5, 0.5, 0.5),
            Vector3D.of(-0.5, 0.5, 0.5)
        };

        final int[][] faceIndices = {
            {0, 4, 7, 3},
            {1, 2, 6, 5},
            {0, 1, 5, 4},
            {3, 7, 6, 2},
            {0, 3, 2, 1},
            {4, 5, 6, 7}
        };

        // act
        final List<ConvexPolygon3D> polys = Planes.indexedConvexPolygons(Arrays.asList(vertices), faceIndices,
                TEST_PRECISION);

        // assert
        Assertions.assertEquals(6, polys.size());
    }

@Test
    void testIndexedConvexPolygons_cube_2_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.of(-0.5, -0.5, -0.5),
            Vector3D.of(0.5, -0.5, -0.5),
            Vector3D.of(0.5, 0.5, -0.5),
            Vector3D.of(-0.5, 0.5, -0.5),

            Vector3D.of(-0.5, -0.5, 0.5),
            Vector3D.of(0.5, -0.5, 0.5),
            Vector3D.of(0.5, 0.5, 0.5),
            Vector3D.of(-0.5, 0.5, 0.5)
        };

        final int[][] faceIndices = {
            {0, 4, 7, 3},
            {1, 2, 6, 5},
            {0, 1, 5, 4},
            {3, 7, 6, 2},
            {0, 3, 2, 1},
            {4, 5, 6, 7}
        };

        // act
        final List<ConvexPolygon3D> polys = Planes.indexedConvexPolygons(Arrays.asList(vertices), faceIndices,
                TEST_PRECISION);

        // assert
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(polys);
        Assertions.assertEquals(1.0, tree.getSize(), TEST_EPS);
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

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> Planes.indexedConvexPolygons(vertices, new int[][] { {0, 1, 10} }, TEST_PRECISION));
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
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> Planes.indexedConvexPolygons(new ArrayList<>(Arrays.asList(vertices)), new int[][] { {0, 1, 10} }, TEST_PRECISION));
    }

@Test
    void testConvexPolygonToTriangleFan_threeVertices_1_oe() {
        // arrange
        final Plane plane = Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 1, 0);

        // act
        final List<Triangle3D> tris = Planes.convexPolygonToTriangleFan(plane, Arrays.asList(p1, p2, p3));

        // assert
        Assertions.assertEquals(1, tris.size());
    }

@Test
    void testConvexPolygonToTriangleFan_threeVertices_2_oe() {
        // arrange
        final Plane plane = Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, 0, 0);
        final Vector3D p3 = Vector3D.of(0, 1, 0);

        // act
        final List<Triangle3D> tris = Planes.convexPolygonToTriangleFan(plane, Arrays.asList(p1, p2, p3));

        // assert
        // removed other assertion

        final Triangle3D a = tris.get(0);
        Assertions.assertSame(plane, a.getPlane());
    }

@Test
    void testConvexPolygonToTriangleFan_fourVertices_1_oe() {
        // arrange
        final Plane plane = Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, 0, 0);
        final Vector3D p3 = Vector3D.of(1, 1, 0);
        final Vector3D p4 = Vector3D.of(0, 1, 0);

        // act
        final List<Triangle3D> tris = Planes.convexPolygonToTriangleFan(plane, Arrays.asList(p1, p2, p3, p4));

        // assert
        Assertions.assertEquals(2, tris.size());
    }

@Test
    void testConvexPolygonToTriangleFan_fourVertices_2_oe() {
        // arrange
        final Plane plane = Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, 0, 0);
        final Vector3D p3 = Vector3D.of(1, 1, 0);
        final Vector3D p4 = Vector3D.of(0, 1, 0);

        // act
        final List<Triangle3D> tris = Planes.convexPolygonToTriangleFan(plane, Arrays.asList(p1, p2, p3, p4));

        // assert
        // removed other assertion

        final Triangle3D a = tris.get(0);
        Assertions.assertSame(plane, a.getPlane());
    }

@Test
    void testConvexPolygonToTriangleFan_fourVertices_4_oe() {
        // arrange
        final Plane plane = Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, 0, 0);
        final Vector3D p3 = Vector3D.of(1, 1, 0);
        final Vector3D p4 = Vector3D.of(0, 1, 0);

        // act
        final List<Triangle3D> tris = Planes.convexPolygonToTriangleFan(plane, Arrays.asList(p1, p2, p3, p4));

        // assert
        // removed other assertion

        final Triangle3D a = tris.get(0);
        // removed other assertion
        // removed other assertion

        final Triangle3D b = tris.get(1);
        Assertions.assertSame(plane, b.getPlane());
    }

@Test
    void testConvexPolygonToTriangleFan_sixVertices_1_oe() {
        // arrange
        final Plane plane = Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, -1, 0);
        final Vector3D p3 = Vector3D.of(1.5, -1, 0);
        final Vector3D p4 = Vector3D.of(5, 0, 0);
        final Vector3D p5 = Vector3D.of(3, 1, 0);
        final Vector3D p6 = Vector3D.of(0.5, 1, 0);

        // act
        final List<Triangle3D> tris = Planes.convexPolygonToTriangleFan(plane, Arrays.asList(p1, p2, p3, p4, p5, p6));

        // assert
        Assertions.assertEquals(4, tris.size());
    }

@Test
    void testConvexPolygonToTriangleFan_sixVertices_2_oe() {
        // arrange
        final Plane plane = Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, -1, 0);
        final Vector3D p3 = Vector3D.of(1.5, -1, 0);
        final Vector3D p4 = Vector3D.of(5, 0, 0);
        final Vector3D p5 = Vector3D.of(3, 1, 0);
        final Vector3D p6 = Vector3D.of(0.5, 1, 0);

        // act
        final List<Triangle3D> tris = Planes.convexPolygonToTriangleFan(plane, Arrays.asList(p1, p2, p3, p4, p5, p6));

        // assert
        // removed other assertion

        final Triangle3D a = tris.get(0);
        Assertions.assertSame(plane, a.getPlane());
    }

@Test
    void testConvexPolygonToTriangleFan_sixVertices_4_oe() {
        // arrange
        final Plane plane = Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, -1, 0);
        final Vector3D p3 = Vector3D.of(1.5, -1, 0);
        final Vector3D p4 = Vector3D.of(5, 0, 0);
        final Vector3D p5 = Vector3D.of(3, 1, 0);
        final Vector3D p6 = Vector3D.of(0.5, 1, 0);

        // act
        final List<Triangle3D> tris = Planes.convexPolygonToTriangleFan(plane, Arrays.asList(p1, p2, p3, p4, p5, p6));

        // assert
        // removed other assertion

        final Triangle3D a = tris.get(0);
        // removed other assertion
        // removed other assertion

        final Triangle3D b = tris.get(1);
        Assertions.assertSame(plane, b.getPlane());
    }

@Test
    void testConvexPolygonToTriangleFan_sixVertices_6_oe() {
        // arrange
        final Plane plane = Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, -1, 0);
        final Vector3D p3 = Vector3D.of(1.5, -1, 0);
        final Vector3D p4 = Vector3D.of(5, 0, 0);
        final Vector3D p5 = Vector3D.of(3, 1, 0);
        final Vector3D p6 = Vector3D.of(0.5, 1, 0);

        // act
        final List<Triangle3D> tris = Planes.convexPolygonToTriangleFan(plane, Arrays.asList(p1, p2, p3, p4, p5, p6));

        // assert
        // removed other assertion

        final Triangle3D a = tris.get(0);
        // removed other assertion
        // removed other assertion

        final Triangle3D b = tris.get(1);
        // removed other assertion
        // removed other assertion

        final Triangle3D c = tris.get(2);
        Assertions.assertSame(plane, c.getPlane());
    }

@Test
    void testConvexPolygonToTriangleFan_sixVertices_8_oe() {
        // arrange
        final Plane plane = Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final Vector3D p1 = Vector3D.ZERO;
        final Vector3D p2 = Vector3D.of(1, -1, 0);
        final Vector3D p3 = Vector3D.of(1.5, -1, 0);
        final Vector3D p4 = Vector3D.of(5, 0, 0);
        final Vector3D p5 = Vector3D.of(3, 1, 0);
        final Vector3D p6 = Vector3D.of(0.5, 1, 0);

        // act
        final List<Triangle3D> tris = Planes.convexPolygonToTriangleFan(plane, Arrays.asList(p1, p2, p3, p4, p5, p6));

        // assert
        // removed other assertion

        final Triangle3D a = tris.get(0);
        // removed other assertion
        // removed other assertion

        final Triangle3D b = tris.get(1);
        // removed other assertion
        // removed other assertion

        final Triangle3D c = tris.get(2);
        // removed other assertion
        // removed other assertion

        final Triangle3D d = tris.get(3);
        Assertions.assertSame(plane, d.getPlane());
    }

@Test
    void testExtrudeVertexLoop_convex_1_oe() {
        // arrange
        final List<Vector2D> vertices = Arrays.asList(
                Vector2D.of(2, 1),
                Vector2D.of(3, 1),
                Vector2D.of(2, 3)
            );

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(1, 0, 1);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrudeVertexLoop(vertices, plane, extrusionVector, TEST_PRECISION);

        // assert
        Assertions.assertEquals(5, boundaries.size());
    }

@Test
    void testExtrudeVertexLoop_convex_2_oe() {
        // arrange
        final List<Vector2D> vertices = Arrays.asList(
                Vector2D.of(2, 1),
                Vector2D.of(3, 1),
                Vector2D.of(2, 3)
            );

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(1, 0, 1);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrudeVertexLoop(vertices, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(boundaries);

        Assertions.assertEquals(1, tree.getSize(), TEST_EPS);
    }

@Test
    void testExtrudeVertexLoop_nonConvex_1_oe() {
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

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrudeVertexLoop(vertices, plane, extrusionVector, TEST_PRECISION);

        // assert
        Assertions.assertEquals(14, boundaries.size());
    }

@Test
    void testExtrudeVertexLoop_nonConvex_2_oe() {
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

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrudeVertexLoop(vertices, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(boundaries);

        Assertions.assertEquals(16, tree.getSize(), TEST_EPS);
    }

@Test
    void testExtrudeVertexLoop_noVertices_1_oe() {
        // arrange
        final List<Vector2D> vertices = new ArrayList<>();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrudeVertexLoop(vertices, plane, extrusionVector, TEST_PRECISION);

        // assert
        Assertions.assertEquals(0, boundaries.size());
    }

@Test
    void testExtrudeVertexLoop_twoVertices_producesInfiniteRegion_1_oe() {
        // arrange
        final List<Vector2D> vertices = Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 1));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrudeVertexLoop(vertices, plane, extrusionVector, TEST_PRECISION);

        // assert
        Assertions.assertEquals(3, boundaries.size());
    }

@Test
    void testExtrudeVertexLoop_twoVertices_producesInfiniteRegion_2_oe() {
        // arrange
        final List<Vector2D> vertices = Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 1));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrudeVertexLoop(vertices, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        Assertions.assertTrue(bottom.isInfinite());
    }

@Test
    void testExtrudeVertexLoop_twoVertices_producesInfiniteRegion_3_oe() {
        // arrange
        final List<Vector2D> vertices = Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 1));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrudeVertexLoop(vertices, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        Assertions.assertTrue(bottom.getPlane().contains(Vector3D.of(0, 0, -1)));
    }

@Test
    void testExtrudeVertexLoop_twoVertices_producesInfiniteRegion_5_oe() {
        // arrange
        final List<Vector2D> vertices = Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 1));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrudeVertexLoop(vertices, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        Assertions.assertTrue(top.isInfinite());
    }

@Test
    void testExtrudeVertexLoop_twoVertices_producesInfiniteRegion_6_oe() {
        // arrange
        final List<Vector2D> vertices = Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 1));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrudeVertexLoop(vertices, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        // removed other assertion
        Assertions.assertTrue(top.getPlane().contains(Vector3D.of(0, 0, 1)));
    }

@Test
    void testExtrudeVertexLoop_twoVertices_producesInfiniteRegion_8_oe() {
        // arrange
        final List<Vector2D> vertices = Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 1));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrudeVertexLoop(vertices, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset side = boundaries.get(2);
        Assertions.assertTrue(side.isInfinite());
    }

@Test
    void testExtrudeVertexLoop_twoVertices_producesInfiniteRegion_9_oe() {
        // arrange
        final List<Vector2D> vertices = Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 1));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrudeVertexLoop(vertices, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset side = boundaries.get(2);
        // removed other assertion
        Assertions.assertTrue(side.getPlane().contains(Vector3D.ZERO));
    }

@Test
    void testExtrudeVertexLoop_twoVertices_producesInfiniteRegion_11_oe() {
        // arrange
        final List<Vector2D> vertices = Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 1));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrudeVertexLoop(vertices, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset side = boundaries.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(boundaries);
        Assertions.assertFalse(tree.isFull());
    }

@Test
    void testExtrudeVertexLoop_twoVertices_producesInfiniteRegion_12_oe() {
        // arrange
        final List<Vector2D> vertices = Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 1));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrudeVertexLoop(vertices, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset side = boundaries.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(boundaries);
        // removed other assertion
        Assertions.assertTrue(tree.isInfinite());
    }

@Test
    void testExtrudeVertexLoop_invalidVertexList_1_oe() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act/assert
        Assertions.assertThrows(IllegalStateException.class, () -> Planes.extrudeVertexLoop(Collections.singletonList(Vector2D.ZERO), plane, extrusionVector, TEST_PRECISION));
    }

@Test
    void testExtrudeVertexLoop_invalidVertexList_2_oe() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act/assert
        // removed other assertion
        Assertions.assertThrows(IllegalStateException.class, () -> Planes.extrudeVertexLoop(Arrays.asList(Vector2D.ZERO, Vector2D.of(0, 1e-16)), plane, extrusionVector, TEST_PRECISION));
    }

@Test
    void testExtrude_vertexLoop_clockwiseWinding_1_oe() {
        // arrange
        final List<Vector2D> vertices = Arrays.asList(
            Vector2D.of(0, 1),
            Vector2D.of(1, 0),
            Vector2D.of(0, -1),
            Vector2D.of(-1, 0));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrudeVertexLoop(vertices, plane, extrusionVector, TEST_PRECISION);

        // assert
        final RegionBSPTree3D resultTree = RegionBSPTree3D.from(boundaries);

        Assertions.assertTrue(resultTree.isInfinite());
    }

@Test
    void testExtrude_linePath_emptyPath_1_oe() {
        // arrange
        final LinePath path = LinePath.empty();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        Assertions.assertEquals(0, boundaries.size());
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnMinus_1_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        Assertions.assertEquals(3, boundaries.size());
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnMinus_2_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(0);
        Assertions.assertTrue(top.isInfinite());
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnMinus_3_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(0);
        // removed other assertion
        Assertions.assertTrue(top.getPlane().contains(Vector3D.of(0, 0, 1)));
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnMinus_5_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(1);
        Assertions.assertTrue(bottom.isInfinite());
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnMinus_6_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(1);
        // removed other assertion
        Assertions.assertTrue(bottom.getPlane().contains(Vector3D.of(0, 0, -1)));
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnMinus_8_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset side = boundaries.get(2);
        Assertions.assertTrue(side.isInfinite());
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnMinus_9_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset side = boundaries.get(2);
        // removed other assertion
        Assertions.assertTrue(side.getPlane().contains(Vector3D.ZERO));
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnMinus_11_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset side = boundaries.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(boundaries);
        Assertions.assertFalse(tree.isFull());
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnMinus_12_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset side = boundaries.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(boundaries);
        // removed other assertion
        Assertions.assertTrue(tree.isInfinite());
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnPlus_1_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        Assertions.assertEquals(3, boundaries.size());
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnPlus_2_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        Assertions.assertTrue(bottom.isInfinite());
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnPlus_3_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        Assertions.assertTrue(bottom.getPlane().contains(Vector3D.of(0, 0, -1)));
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnPlus_5_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        Assertions.assertTrue(top.isInfinite());
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnPlus_6_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        // removed other assertion
        Assertions.assertTrue(top.getPlane().contains(Vector3D.of(0, 0, 1)));
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnPlus_8_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset side = boundaries.get(2);
        Assertions.assertTrue(side.isInfinite());
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnPlus_9_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset side = boundaries.get(2);
        // removed other assertion
        Assertions.assertTrue(side.getPlane().contains(Vector3D.ZERO));
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnPlus_11_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset side = boundaries.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(boundaries);
        Assertions.assertFalse(tree.isFull());
    }

@Test
    void testExtrude_linePath_singleSegment_producesInfiniteRegion_extrudingOnPlus_12_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.of(1, 1))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset side = boundaries.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(boundaries);
        // removed other assertion
        Assertions.assertTrue(tree.isInfinite());
    }

@Test
    void testExtrude_linePath_singleSpan_producesInfiniteRegion_1_oe() {
        // arrange
        final LinePath path = LinePath.from(Lines.fromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION).span());

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        Assertions.assertEquals(3, boundaries.size());
    }

@Test
    void testExtrude_linePath_singleSpan_producesInfiniteRegion_2_oe() {
        // arrange
        final LinePath path = LinePath.from(Lines.fromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION).span());

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        Assertions.assertTrue(bottom.isInfinite());
    }

@Test
    void testExtrude_linePath_singleSpan_producesInfiniteRegion_3_oe() {
        // arrange
        final LinePath path = LinePath.from(Lines.fromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION).span());

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        Assertions.assertTrue(bottom.getPlane().contains(Vector3D.of(0, 0, -1)));
    }

@Test
    void testExtrude_linePath_singleSpan_producesInfiniteRegion_5_oe() {
        // arrange
        final LinePath path = LinePath.from(Lines.fromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION).span());

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        Assertions.assertTrue(top.isInfinite());
    }

@Test
    void testExtrude_linePath_singleSpan_producesInfiniteRegion_6_oe() {
        // arrange
        final LinePath path = LinePath.from(Lines.fromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION).span());

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        // removed other assertion
        Assertions.assertTrue(top.getPlane().contains(Vector3D.of(0, 0, 1)));
    }

@Test
    void testExtrude_linePath_singleSpan_producesInfiniteRegion_8_oe() {
        // arrange
        final LinePath path = LinePath.from(Lines.fromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION).span());

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset side = boundaries.get(2);
        Assertions.assertTrue(side.isInfinite());
    }

@Test
    void testExtrude_linePath_singleSpan_producesInfiniteRegion_9_oe() {
        // arrange
        final LinePath path = LinePath.from(Lines.fromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION).span());

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset side = boundaries.get(2);
        // removed other assertion
        Assertions.assertTrue(side.getPlane().contains(Vector3D.ZERO));
    }

@Test
    void testExtrude_linePath_singleSpan_producesInfiniteRegion_11_oe() {
        // arrange
        final LinePath path = LinePath.from(Lines.fromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION).span());

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset side = boundaries.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(boundaries);
        Assertions.assertFalse(tree.isFull());
    }

@Test
    void testExtrude_linePath_singleSpan_producesInfiniteRegion_12_oe() {
        // arrange
        final LinePath path = LinePath.from(Lines.fromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION).span());

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final PlaneConvexSubset bottom = boundaries.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset top = boundaries.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset side = boundaries.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(boundaries);
        // removed other assertion
        Assertions.assertTrue(tree.isInfinite());
    }

@Test
    void testExtrude_linePath_intersectingInfiniteLines_extrudingOnPlus_1_oe() {
        // arrange
        final Vector2D intersectionPt = Vector2D.of(1, 0);

        final LinePath path = LinePath.from(
                Lines.fromPointAndAngle(intersectionPt, 0, TEST_PRECISION).reverseRayTo(intersectionPt),
                Lines.fromPointAndAngle(intersectionPt, Angle.PI_OVER_TWO, TEST_PRECISION)
                    .rayFrom(intersectionPt));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        Assertions.assertEquals(4, boundaries.size());
    }

@Test
    void testExtrude_linePath_intersectingInfiniteLines_extrudingOnPlus_2_oe() {
        // arrange
        final Vector2D intersectionPt = Vector2D.of(1, 0);

        final LinePath path = LinePath.from(
                Lines.fromPointAndAngle(intersectionPt, 0, TEST_PRECISION).reverseRayTo(intersectionPt),
                Lines.fromPointAndAngle(intersectionPt, Angle.PI_OVER_TWO, TEST_PRECISION)
                    .rayFrom(intersectionPt));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(boundaries);
        Assertions.assertFalse(tree.isFull());
    }

@Test
    void testExtrude_linePath_intersectingInfiniteLines_extrudingOnPlus_3_oe() {
        // arrange
        final Vector2D intersectionPt = Vector2D.of(1, 0);

        final LinePath path = LinePath.from(
                Lines.fromPointAndAngle(intersectionPt, 0, TEST_PRECISION).reverseRayTo(intersectionPt),
                Lines.fromPointAndAngle(intersectionPt, Angle.PI_OVER_TWO, TEST_PRECISION)
                    .rayFrom(intersectionPt));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(boundaries);
        // removed other assertion
        Assertions.assertTrue(tree.isInfinite());
    }

@Test
    void testExtrude_linePath_intersectingInfiniteLines_extrudingOnMinus_1_oe() {
        // arrange
        final Vector2D intersectionPt = Vector2D.of(1, 0);

        final LinePath path = LinePath.from(
                Lines.fromPointAndAngle(intersectionPt, 0, TEST_PRECISION).reverseRayTo(intersectionPt),
                Lines.fromPointAndAngle(intersectionPt, Angle.PI_OVER_TWO, TEST_PRECISION)
                    .rayFrom(intersectionPt));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        Assertions.assertEquals(4, boundaries.size());
    }

@Test
    void testExtrude_linePath_intersectingInfiniteLines_extrudingOnMinus_2_oe() {
        // arrange
        final Vector2D intersectionPt = Vector2D.of(1, 0);

        final LinePath path = LinePath.from(
                Lines.fromPointAndAngle(intersectionPt, 0, TEST_PRECISION).reverseRayTo(intersectionPt),
                Lines.fromPointAndAngle(intersectionPt, Angle.PI_OVER_TWO, TEST_PRECISION)
                    .rayFrom(intersectionPt));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(boundaries);
        Assertions.assertFalse(tree.isFull());
    }

@Test
    void testExtrude_linePath_intersectingInfiniteLines_extrudingOnMinus_3_oe() {
        // arrange
        final Vector2D intersectionPt = Vector2D.of(1, 0);

        final LinePath path = LinePath.from(
                Lines.fromPointAndAngle(intersectionPt, 0, TEST_PRECISION).reverseRayTo(intersectionPt),
                Lines.fromPointAndAngle(intersectionPt, Angle.PI_OVER_TWO, TEST_PRECISION)
                    .rayFrom(intersectionPt));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(boundaries);
        // removed other assertion
        Assertions.assertTrue(tree.isInfinite());
    }

@Test
    void testExtrude_linePath_infiniteNonConvex_1_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.of(1, -5))
                .append(Vector2D.of(1, 1))
                .append(Vector2D.of(0, 0))
                .append(Vector2D.of(-1, 1))
                .append(Vector2D.of(-1, -5))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        Assertions.assertEquals(8, boundaries.size());
    }

@Test
    void testExtrude_linePath_infiniteNonConvex_2_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.of(1, -5))
                .append(Vector2D.of(1, 1))
                .append(Vector2D.of(0, 0))
                .append(Vector2D.of(-1, 1))
                .append(Vector2D.of(-1, -5))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(boundaries);
        Assertions.assertFalse(tree.isFull());
    }

@Test
    void testExtrude_linePath_infiniteNonConvex_3_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.of(1, -5))
                .append(Vector2D.of(1, 1))
                .append(Vector2D.of(0, 0))
                .append(Vector2D.of(-1, 1))
                .append(Vector2D.of(-1, -5))
                .build();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final RegionBSPTree3D tree = RegionBSPTree3D.from(boundaries);
        // removed other assertion
        Assertions.assertTrue(tree.isInfinite());
    }

@Test
    void testExtrude_linePath_clockwiseWinding_1_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.of(0, 1))
                .append(Vector2D.of(1, 0))
                .append(Vector2D.of(0, -1))
                .append(Vector2D.of(-1, 0))
                .close();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(path, plane, extrusionVector, TEST_PRECISION);

        // assert
        final RegionBSPTree3D resultTree = RegionBSPTree3D.from(boundaries);

        Assertions.assertTrue(resultTree.isInfinite());
    }

@Test
    void testExtrude_region_empty_1_oe() {
        // arrange
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(tree, plane, extrusionVector, TEST_PRECISION);

        // assert
        Assertions.assertEquals(0, boundaries.size());
    }

@Test
    void testExtrude_region_full_1_oe() {
        // arrange
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(tree, plane, extrusionVector, TEST_PRECISION);

        // assert
        Assertions.assertEquals(2, boundaries.size());
    }

@Test
    void testExtrude_region_full_2_oe() {
        // arrange
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(tree, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        Assertions.assertTrue(boundaries.get(0).isFull());
    }

@Test
    void testExtrude_region_full_3_oe() {
        // arrange
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(tree, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(boundaries.get(1).isFull());
    }

@Test
    void testExtrude_region_disjointRegions_1_oe() {
        // arrange
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION));
        tree.insert(Parallelogram.axisAligned(Vector2D.of(2, 2), Vector2D.of(3, 3), TEST_PRECISION));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(tree, plane, extrusionVector, TEST_PRECISION);

        // assert
        Assertions.assertEquals(12, boundaries.size());
    }

@Test
    void testExtrude_region_disjointRegions_2_oe() {
        // arrange
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION));
        tree.insert(Parallelogram.axisAligned(Vector2D.of(2, 2), Vector2D.of(3, 3), TEST_PRECISION));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(tree, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final RegionBSPTree3D resultTree = RegionBSPTree3D.from(boundaries);

        Assertions.assertEquals(4, resultTree.getSize(), TEST_EPS);
    }

@Test
    void testExtrude_region_disjointRegions_3_oe() {
        // arrange
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION));
        tree.insert(Parallelogram.axisAligned(Vector2D.of(2, 2), Vector2D.of(3, 3), TEST_PRECISION));

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, -2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(tree, plane, extrusionVector, TEST_PRECISION);

        // assert
        // removed other assertion

        final RegionBSPTree3D resultTree = RegionBSPTree3D.from(boundaries);

        // removed other assertion
        Assertions.assertEquals(20, resultTree.getBoundarySize(), TEST_EPS);
    }

@Test
    void testExtrude_region_starWithCutout_1_oe() {
        // arrange
        // NOTE: this is pretty messed-up looking star :-)
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(LinePath.builder(TEST_PRECISION)
                .append(Vector2D.of(0, 4))
                .append(Vector2D.of(-1.5, 1))
                .append(Vector2D.of(-4, 1))
                .append(Vector2D.of(-2, -1))
                .append(Vector2D.of(-3, -4))
                .append(Vector2D.of(0, -2))
                .append(Vector2D.of(3, -4))
                .append(Vector2D.of(2, -1))
                .append(Vector2D.of(4, 1))
                .append(Vector2D.of(1.5, 1))
                .close());
        tree.insert(LinePath.builder(TEST_PRECISION)
                .append(Vector2D.of(0, 1))
                .append(Vector2D.of(1, 0))
                .append(Vector2D.of(0, -1))
                .append(Vector2D.of(-1, 0))
                .close());

        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, -1),
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final Vector3D extrusionVector = Vector3D.of(0, 0, 2);

        // act
        final List<PlaneConvexSubset> boundaries = Planes.extrude(tree, plane, extrusionVector, TEST_PRECISION);

        // assert
        final RegionBSPTree3D resultTree = RegionBSPTree3D.from(boundaries);

        Assertions.assertTrue(resultTree.isFinite());
    }

}
