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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.core.partitioning.bsp.RegionCutRule;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.threed.RegionBSPTree3D.PartitionedRegionBuilder3D;
import org.apache.commons.geometry.euclidean.threed.RegionBSPTree3D.RegionNode3D;
import org.apache.commons.geometry.euclidean.threed.line.Line3D;
import org.apache.commons.geometry.euclidean.threed.line.LinecastPoint3D;
import org.apache.commons.geometry.euclidean.threed.line.Lines3D;
import org.apache.commons.geometry.euclidean.threed.mesh.TriangleMesh;
import org.apache.commons.geometry.euclidean.threed.shape.Parallelepiped;
import org.apache.commons.geometry.euclidean.twod.path.LinePath;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegionBSPTree3DTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testPartitionedRegionBuilder_cube() {
        // arrange
        final Parallelepiped cube = Parallelepiped.unitCube(TEST_PRECISION);
        final List<PlaneConvexSubset> boundaries = cube.getBoundaries();

        final Vector3D lowerBound = Vector3D.of(-2, -2, -2);

        final int maxUpper = 5;
        final int maxLevel = 4;

        // act/assert
        Bounds3D bounds;
        for (int u = 0; u <= maxUpper; ++u) {
            for (int level = 0; level <= maxLevel; ++level) {
                bounds = Bounds3D.from(lowerBound, Vector3D.of(u, u, u));

                checkFinitePartitionedRegion(bounds, level, cube);
                checkFinitePartitionedRegion(bounds, level, boundaries);
            }
        }
    }

    @Test
    void testPartitionedRegionBuilder_nonConvex() {
        // arrange
        final RegionBSPTree3D src = Parallelepiped.unitCube(TEST_PRECISION).toTree();
        src.union(Parallelepiped.axisAligned(Vector3D.ZERO, Vector3D.of(1, 1, 1), TEST_PRECISION).toTree());

        final List<PlaneConvexSubset> boundaries = src.getBoundaries();

        final Vector3D lowerBound = Vector3D.of(-2, -2, -2);

        final int maxUpper = 5;
        final int maxLevel = 4;

        // act/assert
        Bounds3D bounds;
        for (int u = 0; u <= maxUpper; ++u) {
            for (int level = 0; level <= maxLevel; ++level) {
                bounds = Bounds3D.from(lowerBound, Vector3D.of(u, u, u));

                checkFinitePartitionedRegion(bounds, level, src);
                checkFinitePartitionedRegion(bounds, level, boundaries);
            }
        }
    }

    /** Check that a partitioned BSP tree behaves the same as a non-partitioned tree when
     * constructed with the given boundary source.
     * @param bounds
     * @param level
     * @param src
     */
    private void checkFinitePartitionedRegion(final Bounds3D bounds, final int level, final BoundarySource3D src) {
        // arrange
        final String msg = "Partitioned region check failed with bounds= " + bounds + " and level= " + level;

        final RegionBSPTree3D standard = RegionBSPTree3D.from(src.boundaryStream().collect(Collectors.toList()));

        // act
        final RegionBSPTree3D partitioned = RegionBSPTree3D.partitionedRegionBuilder()
                .insertAxisAlignedGrid(bounds, level, TEST_PRECISION)
                .insertBoundaries(src)
                .build();

        // assert
        Assertions.assertEquals(standard.getSize(), partitioned.getSize(), TEST_EPS, msg);
        Assertions.assertEquals(standard.getBoundarySize(), partitioned.getBoundarySize(), TEST_EPS, msg);
        EuclideanTestUtils.assertCoordinatesEqual(standard.getCentroid(), partitioned.getCentroid(), TEST_EPS);

        final RegionBSPTree3D diff = RegionBSPTree3D.empty();
        diff.difference(partitioned, standard);
        Assertions.assertTrue(diff.isEmpty(), msg);
    }

    /** Check that a partitioned BSP tree behaves the same as a non-partitioned tree when
     * constructed with the given boundaries.
     * @param bounds
     * @param level
     * @param boundaries
     */
    private void checkFinitePartitionedRegion(final Bounds3D bounds, final int level,
                                              final List<? extends PlaneConvexSubset> boundaries) {
        // arrange
        final String msg = "Partitioned region check failed with bounds= " + bounds + " and level= " + level;

        final RegionBSPTree3D standard = RegionBSPTree3D.from(boundaries);

        // act
        final RegionBSPTree3D partitioned = RegionBSPTree3D.partitionedRegionBuilder()
                .insertAxisAlignedGrid(bounds, level, TEST_PRECISION)
                .insertBoundaries(boundaries)
                .build();

        // assert
        Assertions.assertEquals(standard.getSize(), partitioned.getSize(), TEST_EPS, msg);
        Assertions.assertEquals(standard.getBoundarySize(), partitioned.getBoundarySize(), TEST_EPS, msg);
        EuclideanTestUtils.assertCoordinatesEqual(standard.getCentroid(), partitioned.getCentroid(), TEST_EPS);

        final RegionBSPTree3D diff = RegionBSPTree3D.empty();
        diff.difference(partitioned, standard);
        Assertions.assertTrue(diff.isEmpty(), msg);
    }

    @Test
    void testPartitionedRegionBuilder_insertPartitionAfterBoundary() {
        // arrange
        final PartitionedRegionBuilder3D builder = RegionBSPTree3D.partitionedRegionBuilder();
        builder.insertBoundary(Planes.triangleFromVertices(
                Vector3D.ZERO, Vector3D.of(1, 0, 0), Vector3D.of(0, 1, 0), TEST_PRECISION));

        final Plane partition = Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        final String msg = "Cannot insert partitions after boundaries have been inserted";

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.insertPartition(partition);
        }, IllegalStateException.class, msg);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.insertPartition(partition.span());
        }, IllegalStateException.class, msg);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.insertAxisAlignedPartitions(Vector3D.ZERO, TEST_PRECISION);
        }, IllegalStateException.class, msg);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.insertAxisAlignedGrid(Bounds3D.from(Vector3D.ZERO, Vector3D.of(1, 1, 1)), 1, TEST_PRECISION);
        }, IllegalStateException.class, msg);
    }

    @Test
    void testGetBounds_hasBounds() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.ZERO, Vector3D.of(1, 1, 1));

        // act
        final Bounds3D bounds = tree.getBounds();

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.ZERO, bounds.getMin(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, 1, 1), bounds.getMax(), TEST_EPS);
    }

    @Test
    void testLinecast_empty() {
        // arrange
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();

        // act/assert
        LinecastChecker3D.with(tree)
            .expectNothing()
            .whenGiven(Lines3D.fromPoints(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION));

        LinecastChecker3D.with(tree)
            .expectNothing()
            .whenGiven(Lines3D.segmentFromPoints(Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_X, TEST_PRECISION));
    }

    @Test
    void testLinecast_full() {
        // arrange
        final RegionBSPTree3D tree = RegionBSPTree3D.full();

        // act/assert
        LinecastChecker3D.with(tree)
            .expectNothing()
            .whenGiven(Lines3D.fromPoints(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION));

        LinecastChecker3D.with(tree)
            .expectNothing()
            .whenGiven(Lines3D.segmentFromPoints(Vector3D.Unit.MINUS_X, Vector3D.Unit.PLUS_X, TEST_PRECISION));
    }

    @Test
    void testLinecast() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.ZERO, Vector3D.of(1, 1, 1));

        // act/assert
        LinecastChecker3D.with(tree)
            .expectNothing()
            .whenGiven(Lines3D.fromPoints(Vector3D.of(0, 5, 5), Vector3D.of(1, 6, 6), TEST_PRECISION));

        final Vector3D corner = Vector3D.of(1, 1, 1);

        LinecastChecker3D.with(tree)
            .expect(Vector3D.ZERO, Vector3D.Unit.MINUS_X)
            .and(Vector3D.ZERO, Vector3D.Unit.MINUS_Y)
            .and(Vector3D.ZERO, Vector3D.Unit.MINUS_Z)
            .and(corner, Vector3D.Unit.PLUS_Z)
            .and(corner, Vector3D.Unit.PLUS_Y)
            .and(corner, Vector3D.Unit.PLUS_X)
            .whenGiven(Lines3D.fromPoints(Vector3D.ZERO, corner, TEST_PRECISION));

        LinecastChecker3D.with(tree)
            .expect(corner, Vector3D.Unit.PLUS_Z)
            .and(corner, Vector3D.Unit.PLUS_Y)
            .and(corner, Vector3D.Unit.PLUS_X)
            .whenGiven(Lines3D.segmentFromPoints(Vector3D.of(0.5, 0.5, 0.5), corner, TEST_PRECISION));
    }

    @Test
    void testLinecast_complementedTree() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.ZERO, Vector3D.of(1, 1, 1));

        tree.complement();

        // act/assert
        LinecastChecker3D.with(tree)
            .expectNothing()
            .whenGiven(Lines3D.fromPoints(Vector3D.of(0, 5, 5), Vector3D.of(1, 6, 6), TEST_PRECISION));

        final Vector3D corner = Vector3D.of(1, 1, 1);

        LinecastChecker3D.with(tree)
            .expect(Vector3D.ZERO, Vector3D.Unit.PLUS_Z)
            .and(Vector3D.ZERO, Vector3D.Unit.PLUS_Y)
            .and(Vector3D.ZERO, Vector3D.Unit.PLUS_X)
            .and(corner, Vector3D.Unit.MINUS_X)
            .and(corner, Vector3D.Unit.MINUS_Y)
            .and(corner, Vector3D.Unit.MINUS_Z)
            .whenGiven(Lines3D.fromPoints(Vector3D.ZERO, corner, TEST_PRECISION));

        LinecastChecker3D.with(tree)
            .expect(corner, Vector3D.Unit.MINUS_X)
            .and(corner, Vector3D.Unit.MINUS_Y)
            .and(corner, Vector3D.Unit.MINUS_Z)
            .whenGiven(Lines3D.segmentFromPoints(Vector3D.of(0.5, 0.5, 0.5), corner, TEST_PRECISION));
    }

    @Test
    void testLinecast_complexRegion() {
        // arrange
        final RegionBSPTree3D a = RegionBSPTree3D.empty();
        Parallelepiped.axisAligned(Vector3D.ZERO, Vector3D.of(0.5, 1, 1), TEST_PRECISION).boundaryStream()
            .map(PlaneConvexSubset::reverse)
            .forEach(a::insert);
        a.complement();

        final RegionBSPTree3D b = RegionBSPTree3D.empty();
        Parallelepiped.axisAligned(Vector3D.of(0.5, 0, 0), Vector3D.of(1, 1, 1), TEST_PRECISION).boundaryStream()
            .map(PlaneConvexSubset::reverse)
            .forEach(b::insert);
        b.complement();

        final RegionBSPTree3D c = createRect(Vector3D.of(0.5, 0.5, 0.5), Vector3D.of(1.5, 1.5, 1.5));

        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(a, b);
        tree.union(c);

        // act/assert
        final Vector3D corner = Vector3D.of(1.5, 1.5, 1.5);

        LinecastChecker3D.with(tree)
            .expect(corner, Vector3D.Unit.PLUS_Z)
            .and(corner, Vector3D.Unit.PLUS_Y)
            .and(corner, Vector3D.Unit.PLUS_X)
            .whenGiven(Lines3D.segmentFromPoints(Vector3D.of(0.25, 0.25, 0.25), Vector3D.of(2, 2, 2), TEST_PRECISION));
    }

    @Test
    void testLinecast_removesDuplicatePoints() {
        // arrange
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.insert(Planes.fromNormal(Vector3D.Unit.PLUS_X, TEST_PRECISION).span());
        tree.insert(Planes.fromNormal(Vector3D.Unit.PLUS_Y, TEST_PRECISION).span());

        // act/assert
        LinecastChecker3D.with(tree)
            .expect(Vector3D.ZERO, Vector3D.Unit.PLUS_Y)
            .whenGiven(Lines3D.fromPoints(Vector3D.of(1, 1, 1), Vector3D.of(-1, -1, -1), TEST_PRECISION));

        LinecastChecker3D.with(tree)
        .expect(Vector3D.ZERO, Vector3D.Unit.PLUS_Y)
            .whenGiven(Lines3D.segmentFromPoints(Vector3D.of(1, 1, 1), Vector3D.of(-1, -1, -1), TEST_PRECISION));
    }

    // issue GEOMETRY-38

    // Issue GEOMETRY-43

    @Test
    void testLinecastFirst_rayPointOnFace() {
        // arrange
        final Vector3D lowerCorner = Vector3D.ZERO;
        final Vector3D upperCorner = Vector3D.of(1, 1, 1);

        final RegionBSPTree3D tree = createRect(lowerCorner, upperCorner);

        final Vector3D pt = Vector3D.of(0.5, 0.5, 0);
        final Line3D intoBoxLine = Lines3D.fromPoints(pt, pt.add(Vector3D.Unit.PLUS_Z), TEST_PRECISION);
        final Line3D outOfBoxLine = Lines3D.fromPoints(pt, pt.add(Vector3D.Unit.MINUS_Z), TEST_PRECISION);

        // act/assert
        final LinecastPoint3D intoBoxResult = tree.linecastFirst(intoBoxLine.rayFrom(pt));
        EuclideanTestUtils.assertCoordinatesEqual(pt, intoBoxResult.getPoint(), TEST_EPS);

        final LinecastPoint3D outOfBoxResult = tree.linecastFirst(outOfBoxLine.rayFrom(pt));
        EuclideanTestUtils.assertCoordinatesEqual(pt, outOfBoxResult.getPoint(), TEST_EPS);
    }

    @Test
    void testLinecastFirst_rayPointOnVertex() {
        // arrange
        final Vector3D lowerCorner = Vector3D.ZERO;
        final Vector3D upperCorner = Vector3D.of(1, 1, 1);

        final RegionBSPTree3D tree = createRect(lowerCorner, upperCorner);

        final Line3D intoBoxLine = Lines3D.fromPoints(lowerCorner, upperCorner, TEST_PRECISION);
        final Line3D outOfBoxLine = intoBoxLine.reverse();

        // act/assert
        final LinecastPoint3D intoBoxResult = tree.linecastFirst(intoBoxLine.rayFrom(lowerCorner));
        EuclideanTestUtils.assertCoordinatesEqual(lowerCorner, intoBoxResult.getPoint(), TEST_EPS);

        final LinecastPoint3D outOfBoxResult = tree.linecastFirst(outOfBoxLine.rayFrom(lowerCorner));
        EuclideanTestUtils.assertCoordinatesEqual(lowerCorner, outOfBoxResult.getPoint(), TEST_EPS);
    }

    @Test
    void testProjectToBoundary() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.ZERO, Vector3D.of(1, 1, 1));

        // act/assert
        checkProject(tree, Vector3D.of(0.5, 0.5, 0.5), Vector3D.of(0, 0.5, 0.5));
        checkProject(tree, Vector3D.of(0.4, 0.5, 0.5), Vector3D.of(0, 0.5, 0.5));
        checkProject(tree, Vector3D.of(1.5, 0.5, 0.5), Vector3D.of(1, 0.5, 0.5));
        checkProject(tree, Vector3D.of(2, 2, 2), Vector3D.of(1, 1, 1));
    }

    @Test
    void testProjectToBoundary_invertedRegion() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.ZERO, Vector3D.of(1, 1, 1));

        tree.complement();

        // act/assert
        checkProject(tree, Vector3D.of(0.4, 0.5, 0.5), Vector3D.of(0, 0.5, 0.5));
        checkProject(tree, Vector3D.of(1.5, 0.5, 0.5), Vector3D.of(1, 0.5, 0.5));
        checkProject(tree, Vector3D.of(2, 2, 2), Vector3D.of(1, 1, 1));
    }

    private void checkProject(final RegionBSPTree3D tree, final Vector3D toProject, final Vector3D expectedPoint) {
        final Vector3D proj = tree.project(toProject);

        EuclideanTestUtils.assertCoordinatesEqual(expectedPoint, proj, TEST_EPS);
    }

    // GEOMETRY-59

    private static List<PlaneConvexSubset> indexedFacetsToBoundaries(final Vector3D[] vertices, final int[][] facets) {
        final List<PlaneConvexSubset> boundaries = new ArrayList<>();

        final List<Vector3D> vertexList = new ArrayList<>();

        for (final int[] indices : facets) {
            for (final int index : indices) {
                vertexList.add(vertices[index]);
            }

            // insert into an embedded tree and convert to convex polygons so that we can support
            // non-convex facet boundaries
            final EmbeddingPlane plane = Planes.fromPoints(vertexList, TEST_PRECISION).getEmbedding();

            final LinePath subPath = LinePath.builder(TEST_PRECISION)
                    .appendVertices(plane.toSubspace(vertexList))
                    .close();
            final EmbeddedTreePlaneSubset subset = new EmbeddedTreePlaneSubset(plane, subPath.toTree());

            boundaries.addAll(subset.toConvex());

            vertexList.clear();
        }

        return boundaries;
    }

    private static RegionBSPTree3D createRect(final Vector3D a, final Vector3D b) {
        return createRect(a, b, TEST_PRECISION);
    }

    private static RegionBSPTree3D createRect(final Vector3D a, final Vector3D b, final Precision.DoubleEquivalence precision) {
        return Parallelepiped.axisAligned(a, b, precision).toTree();
    }

    private static RegionBSPTree3D createSphere(final Vector3D center, final double radius, final int stacks, final int slices) {

        final List<Plane> planes = new ArrayList<>();

        // add top and bottom planes (+/- z)
        final Vector3D topZ = Vector3D.of(center.getX(), center.getY(), center.getZ() + radius);
        final Vector3D bottomZ = Vector3D.of(center.getX(), center.getY(), center.getZ() - radius);

        planes.add(Planes.fromPointAndNormal(topZ, Vector3D.Unit.PLUS_Z, TEST_PRECISION));
        planes.add(Planes.fromPointAndNormal(bottomZ, Vector3D.Unit.MINUS_Z, TEST_PRECISION));

        // add the side planes
        final double vDelta = Math.PI / stacks;
        final double hDelta = Math.PI * 2 / slices;

        final double adjustedRadius = (radius + (radius * Math.cos(vDelta * 0.5))) / 2.0;

        double vAngle;
        double hAngle;
        double stackRadius;
        double stackHeight;
        double x;
        double y;
        Vector3D pt;
        Vector3D norm;

        vAngle = -0.5 * vDelta;
        for (int v = 0; v < stacks; ++v) {
            vAngle += vDelta;

            stackRadius = Math.sin(vAngle) * adjustedRadius;
            stackHeight = Math.cos(vAngle) * adjustedRadius;

            hAngle = -0.5 * hDelta;
            for (int h = 0; h < slices; ++h) {
                hAngle += hDelta;

                x = Math.cos(hAngle) * stackRadius;
                y = Math.sin(hAngle) * stackRadius;

                norm = Vector3D.of(x, y, stackHeight).normalize();
                pt = center.add(norm.multiply(adjustedRadius));

                planes.add(Planes.fromPointAndNormal(pt, norm, TEST_PRECISION));
            }
        }

        final RegionBSPTree3D tree = RegionBSPTree3D.full();
        RegionNode3D node = tree.getRoot();

        for (final Plane plane : planes) {
            node = node.cut(plane).getMinus();
        }

        return tree;
    }

    private static double cubeVolume(final double size) {
        return size * size * size;
    }

    private static double cubeSurface(final double size) {
        return 6.0 * size * size;
    }

    private static double sphereVolume(final double radius) {
        return 4.0 * Math.PI * radius * radius * radius / 3.0;
    }

    private static double sphereSurface(final double radius) {
        return 4.0 * Math.PI * radius * radius;
    }

    private static double circleSurface(final double radius) {
        return Math.PI * radius * radius;
    }

    @Test
    void testToTriangleMesh_infiniteBoundary_1_oe() {
        // arrange
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.getRoot().insertCut(Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION));

        // act/assert
        try {
    tree.toTriangleMesh(TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

}
