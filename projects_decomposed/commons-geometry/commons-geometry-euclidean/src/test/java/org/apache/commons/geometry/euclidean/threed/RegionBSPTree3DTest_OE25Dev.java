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

import static org.junit.jupiter.api.Assertions.fail;

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
    void testCtor_default_1_oe() {
        // act
        final RegionBSPTree3D tree = new RegionBSPTree3D();

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testCtor_default_2_oe() {
        // act
        final RegionBSPTree3D tree = new RegionBSPTree3D();

        // assert
        // removed other assertion
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testCtor_boolean_1_oe() {
        // act
        final RegionBSPTree3D a = new RegionBSPTree3D(true);
        final RegionBSPTree3D b = new RegionBSPTree3D(false);

        // assert
        Assertions.assertTrue(a.isFull());
    }

    @Test
    void testCtor_boolean_2_oe() {
        // act
        final RegionBSPTree3D a = new RegionBSPTree3D(true);
        final RegionBSPTree3D b = new RegionBSPTree3D(false);

        // assert
        // removed other assertion
        Assertions.assertFalse(a.isEmpty());
    }

    @Test
    void testCtor_boolean_3_oe() {
        // act
        final RegionBSPTree3D a = new RegionBSPTree3D(true);
        final RegionBSPTree3D b = new RegionBSPTree3D(false);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(b.isFull());
    }

    @Test
    void testCtor_boolean_4_oe() {
        // act
        final RegionBSPTree3D a = new RegionBSPTree3D(true);
        final RegionBSPTree3D b = new RegionBSPTree3D(false);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(b.isEmpty());
    }

    @Test
    void testEmpty_1_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testEmpty_2_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();

        // assert
        // removed other assertion
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testEmpty_3_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testEmpty_4_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0.0, tree.getSize(), TEST_EPS);
    }

    @Test
    void testEmpty_5_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFull_1_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.full();

        // assert
        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testFull_2_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.full();

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testFull_3_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.full();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testFull_5_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.full();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testPartitionedRegionBuilder_halfSpace_1_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.partitionedRegionBuilder()
                .insertPartition(
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.PLUS_Z, TEST_PRECISION))
                .insertBoundary(
                        Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION).span())
                .build();

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testPartitionedRegionBuilder_halfSpace_2_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.partitionedRegionBuilder()
                .insertPartition(
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.PLUS_Z, TEST_PRECISION))
                .insertBoundary(
                        Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION).span())
                .build();

        // assert
        // removed other assertion
        Assertions.assertTrue(tree.isInfinite());
    }

    @Test
    void testCopy_1_oe() {
        // arrange
        final RegionBSPTree3D tree = new RegionBSPTree3D(true);
        tree.getRoot().cut(Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION));

        // act
        final RegionBSPTree3D copy = tree.copy();

        // assert
        Assertions.assertNotSame(tree, copy);
    }

    @Test
    void testCopy_2_oe() {
        // arrange
        final RegionBSPTree3D tree = new RegionBSPTree3D(true);
        tree.getRoot().cut(Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION));

        // act
        final RegionBSPTree3D copy = tree.copy();

        // assert
        // removed other assertion
        Assertions.assertEquals(3, copy.count());
    }

    @Test
    void testBoundaries_1_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.ZERO, Vector3D.of(1, 1, 1));

        // act
        final List<PlaneConvexSubset> facets = new ArrayList<>();
        tree.boundaries().forEach(facets::add);

        // assert
        Assertions.assertEquals(6, facets.size());
    }

    @Test
    void testGetBoundaries_1_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.ZERO, Vector3D.of(1, 1, 1));

        // act
        final List<PlaneConvexSubset> facets = tree.getBoundaries();

        // assert
        Assertions.assertEquals(6, facets.size());
    }

    @Test
    void testBoundaryStream_1_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.ZERO, Vector3D.of(1, 1, 1));

        // act
        final List<PlaneConvexSubset> facets = tree.boundaryStream().collect(Collectors.toList());

        // assert
        Assertions.assertEquals(6, facets.size());
    }

    @Test
    void testBoundaryStream_noBoundaries_1_oe() {
        // arrange
        final RegionBSPTree3D tree = RegionBSPTree3D.full();

        // act
        final List<PlaneConvexSubset> facets = tree.boundaryStream().collect(Collectors.toList());

        // assert
        Assertions.assertEquals(0, facets.size());
    }

    @Test
    void testTriangleStream_noBoundaries_1_oe() {
        // arrange
        final RegionBSPTree3D full = RegionBSPTree3D.full();
        final RegionBSPTree3D empty = RegionBSPTree3D.empty();

        // act/assert
        Assertions.assertEquals(0, full.triangleStream().count());
    }

    @Test
    void testTriangleStream_noBoundaries_2_oe() {
        // arrange
        final RegionBSPTree3D full = RegionBSPTree3D.full();
        final RegionBSPTree3D empty = RegionBSPTree3D.empty();

        // act/assert
        // removed other assertion
        Assertions.assertEquals(0, empty.triangleStream().count());
    }

    @Test
    void testTriangleStream_1_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.ZERO, Vector3D.of(1, 1, 1));

        // act
        final List<Triangle3D> tris = tree.triangleStream().collect(Collectors.toList());

        // assert
        Assertions.assertEquals(12, tris.size());
    }

    @Test
    void testTriangleStream_roundTrip_1_oe() {
        // arrange
        final RegionBSPTree3D a = createRect(Vector3D.ZERO, Vector3D.of(1, 1, 1));
        final RegionBSPTree3D b = createRect(Vector3D.of(0.5, 0.5, 0.5), Vector3D.of(1.5, 1.5, 1.5));

        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(a);
        tree.union(b);

        // act
        final List<Triangle3D> tris = tree.triangleStream().collect(Collectors.toList());
        final RegionBSPTree3D result = RegionBSPTree3D.from(tris);

        // assert
        Assertions.assertEquals(15.0 / 8.0, result.getSize(), TEST_EPS);
    }

    @Test
    void testToTriangleMesh_1_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.ZERO, Vector3D.of(1, 1, 1));

        // act
        final TriangleMesh mesh = tree.toTriangleMesh(TEST_PRECISION);

        // assert
        Assertions.assertEquals(8, mesh.getVertexCount());
    }

    @Test
    void testToTriangleMesh_2_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.ZERO, Vector3D.of(1, 1, 1));

        // act
        final TriangleMesh mesh = tree.toTriangleMesh(TEST_PRECISION);

        // assert
        // removed other assertion
        Assertions.assertEquals(12, mesh.getFaceCount());
    }

    @Test
    void testToTriangleMesh_5_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.ZERO, Vector3D.of(1, 1, 1));

        // act
        final TriangleMesh mesh = tree.toTriangleMesh(TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        final Bounds3D bounds = mesh.getBounds();
        // removed other assertion
        // removed other assertion

        final RegionBSPTree3D otherTree = mesh.toTree();
        Assertions.assertEquals(1, otherTree.getSize(), TEST_EPS);
    }

    @Test
    void testToTriangleMesh_6_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.ZERO, Vector3D.of(1, 1, 1));

        // act
        final TriangleMesh mesh = tree.toTriangleMesh(TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        final Bounds3D bounds = mesh.getBounds();
        // removed other assertion
        // removed other assertion

        final RegionBSPTree3D otherTree = mesh.toTree();
        // removed other assertion
        Assertions.assertEquals(6, otherTree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testToTriangleMesh_empty_1_oe() {
        // arrange
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();

        // act
        final TriangleMesh mesh = tree.toTriangleMesh(TEST_PRECISION);

        // assert
        // no boundaries
        Assertions.assertEquals(0, mesh.getVertexCount());
    }

    @Test
    void testToTriangleMesh_empty_2_oe() {
        // arrange
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();

        // act
        final TriangleMesh mesh = tree.toTriangleMesh(TEST_PRECISION);

        // assert
        // no boundaries
        // removed other assertion
        Assertions.assertEquals(0, mesh.getFaceCount());
    }

    @Test
    void testToTriangleMesh_full_1_oe() {
        // arrange
        final RegionBSPTree3D tree = RegionBSPTree3D.full();

        // act
        final TriangleMesh mesh = tree.toTriangleMesh(TEST_PRECISION);

        // assert
        // no boundaries
        Assertions.assertEquals(0, mesh.getVertexCount());
    }

    @Test
    void testToTriangleMesh_full_2_oe() {
        // arrange
        final RegionBSPTree3D tree = RegionBSPTree3D.full();

        // act
        final TriangleMesh mesh = tree.toTriangleMesh(TEST_PRECISION);

        // assert
        // no boundaries
        // removed other assertion
        Assertions.assertEquals(0, mesh.getFaceCount());
    }

    @Test
    void testToTriangleMesh_infiniteBoundary_1_oe() {
        // arrange
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.getRoot().insertCut(Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION));

        // act/assert
        try {
    tree.toTriangleMesh(TEST_PRECISION);
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testGetBounds_noBounds_1_oe() {
        // act/assert
        Assertions.assertNull(RegionBSPTree3D.empty().getBounds());
    }

    @Test
    void testGetBounds_noBounds_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertNull(RegionBSPTree3D.full().getBounds());
    }

    @Test
    void testGetBounds_noBounds_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        final RegionBSPTree3D halfFull = RegionBSPTree3D.empty();
        halfFull.getRoot().insertCut(Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.PLUS_Z, TEST_PRECISION));
        Assertions.assertNull(halfFull.getBounds());
    }

    @Test
    void testToList_1_oe() {
        // arrange
        final RegionBSPTree3D tree = Parallelepiped.axisAligned(
                Vector3D.ZERO, Vector3D.of(1, 3, 3), TEST_PRECISION).toTree();

        // act
        final BoundaryList3D list = tree.toList();

        // assert
        Assertions.assertEquals(6, list.count());
    }

    @Test
    void testToList_2_oe() {
        // arrange
        final RegionBSPTree3D tree = Parallelepiped.axisAligned(
                Vector3D.ZERO, Vector3D.of(1, 3, 3), TEST_PRECISION).toTree();

        // act
        final BoundaryList3D list = tree.toList();

        // assert
        // removed other assertion
        Assertions.assertEquals(9, list.toTree().getSize());
    }

    @Test
    void testToList_fullAndEmpty_1_oe() {
        // act/assert
        Assertions.assertEquals(0, RegionBSPTree3D.full().toList().count());
    }

    @Test
    void testToList_fullAndEmpty_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertEquals(0, RegionBSPTree3D.empty().toList().count());
    }

    @Test
    void testToTree_returnsSameInstance_1_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.ZERO, Vector3D.of(1, 2, 1));

        // act/assert
        Assertions.assertSame(tree, tree.toTree());
    }

    @Test
    void testHalfSpace_1_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.insert(Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.PLUS_Y, TEST_PRECISION).span());

        // assert
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testHalfSpace_2_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.insert(Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.PLUS_Y, TEST_PRECISION).span());

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testHalfSpace_5_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.insert(Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.PLUS_Y, TEST_PRECISION).span());

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testGeometricProperties_mixedCutRules_1_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();

        final Vector3D min = Vector3D.ZERO;
        final Vector3D max = Vector3D.of(1, 1, 1);

        final Plane top = Planes.fromPointAndNormal(max, Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final Plane bottom = Planes.fromPointAndNormal(min, Vector3D.Unit.MINUS_Z, TEST_PRECISION);
        final Plane left = Planes.fromPointAndNormal(min, Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final Plane right = Planes.fromPointAndNormal(max, Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final Plane front = Planes.fromPointAndNormal(min, Vector3D.Unit.MINUS_Y, TEST_PRECISION);
        final Plane back = Planes.fromPointAndNormal(max, Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        final Plane diag = Planes.fromPointAndNormal(Vector3D.of(0.5, 0.5, 0.5), Vector3D.of(0.5, -0.5, 0), TEST_PRECISION);
        final Plane midCut = Planes.fromPointAndNormal(Vector3D.of(0.5, 0.5, 0.5), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        tree.getRoot()
            .cut(diag, RegionCutRule.INHERIT);

        tree.getRoot()
            .getMinus().cut(top)
            .getMinus().cut(bottom.reverse(), RegionCutRule.PLUS_INSIDE)
            .getPlus().cut(left, RegionCutRule.MINUS_INSIDE)
            .getMinus().cut(back.reverse(), RegionCutRule.PLUS_INSIDE)
            .getPlus().cut(midCut, RegionCutRule.INHERIT);

        tree.getRoot()
            .getPlus().cut(top.reverse(), RegionCutRule.PLUS_INSIDE)
            .getPlus().cut(bottom)
            .getMinus().cut(right, RegionCutRule.MINUS_INSIDE)
            .getMinus().cut(front.reverse(), RegionCutRule.PLUS_INSIDE)
            .getPlus().cut(midCut, RegionCutRule.INHERIT);

        // assert
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testGeometricProperties_mixedCutRules_2_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();

        final Vector3D min = Vector3D.ZERO;
        final Vector3D max = Vector3D.of(1, 1, 1);

        final Plane top = Planes.fromPointAndNormal(max, Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final Plane bottom = Planes.fromPointAndNormal(min, Vector3D.Unit.MINUS_Z, TEST_PRECISION);
        final Plane left = Planes.fromPointAndNormal(min, Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final Plane right = Planes.fromPointAndNormal(max, Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final Plane front = Planes.fromPointAndNormal(min, Vector3D.Unit.MINUS_Y, TEST_PRECISION);
        final Plane back = Planes.fromPointAndNormal(max, Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        final Plane diag = Planes.fromPointAndNormal(Vector3D.of(0.5, 0.5, 0.5), Vector3D.of(0.5, -0.5, 0), TEST_PRECISION);
        final Plane midCut = Planes.fromPointAndNormal(Vector3D.of(0.5, 0.5, 0.5), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        tree.getRoot()
            .cut(diag, RegionCutRule.INHERIT);

        tree.getRoot()
            .getMinus().cut(top)
            .getMinus().cut(bottom.reverse(), RegionCutRule.PLUS_INSIDE)
            .getPlus().cut(left, RegionCutRule.MINUS_INSIDE)
            .getMinus().cut(back.reverse(), RegionCutRule.PLUS_INSIDE)
            .getPlus().cut(midCut, RegionCutRule.INHERIT);

        tree.getRoot()
            .getPlus().cut(top.reverse(), RegionCutRule.PLUS_INSIDE)
            .getPlus().cut(bottom)
            .getMinus().cut(right, RegionCutRule.MINUS_INSIDE)
            .getMinus().cut(front.reverse(), RegionCutRule.PLUS_INSIDE)
            .getPlus().cut(midCut, RegionCutRule.INHERIT);

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testGeometricProperties_mixedCutRules_3_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();

        final Vector3D min = Vector3D.ZERO;
        final Vector3D max = Vector3D.of(1, 1, 1);

        final Plane top = Planes.fromPointAndNormal(max, Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final Plane bottom = Planes.fromPointAndNormal(min, Vector3D.Unit.MINUS_Z, TEST_PRECISION);
        final Plane left = Planes.fromPointAndNormal(min, Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final Plane right = Planes.fromPointAndNormal(max, Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final Plane front = Planes.fromPointAndNormal(min, Vector3D.Unit.MINUS_Y, TEST_PRECISION);
        final Plane back = Planes.fromPointAndNormal(max, Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        final Plane diag = Planes.fromPointAndNormal(Vector3D.of(0.5, 0.5, 0.5), Vector3D.of(0.5, -0.5, 0), TEST_PRECISION);
        final Plane midCut = Planes.fromPointAndNormal(Vector3D.of(0.5, 0.5, 0.5), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        tree.getRoot()
            .cut(diag, RegionCutRule.INHERIT);

        tree.getRoot()
            .getMinus().cut(top)
            .getMinus().cut(bottom.reverse(), RegionCutRule.PLUS_INSIDE)
            .getPlus().cut(left, RegionCutRule.MINUS_INSIDE)
            .getMinus().cut(back.reverse(), RegionCutRule.PLUS_INSIDE)
            .getPlus().cut(midCut, RegionCutRule.INHERIT);

        tree.getRoot()
            .getPlus().cut(top.reverse(), RegionCutRule.PLUS_INSIDE)
            .getPlus().cut(bottom)
            .getMinus().cut(right, RegionCutRule.MINUS_INSIDE)
            .getMinus().cut(front.reverse(), RegionCutRule.PLUS_INSIDE)
            .getPlus().cut(midCut, RegionCutRule.INHERIT);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, tree.getSize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_mixedCutRules_4_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();

        final Vector3D min = Vector3D.ZERO;
        final Vector3D max = Vector3D.of(1, 1, 1);

        final Plane top = Planes.fromPointAndNormal(max, Vector3D.Unit.PLUS_Z, TEST_PRECISION);
        final Plane bottom = Planes.fromPointAndNormal(min, Vector3D.Unit.MINUS_Z, TEST_PRECISION);
        final Plane left = Planes.fromPointAndNormal(min, Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final Plane right = Planes.fromPointAndNormal(max, Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final Plane front = Planes.fromPointAndNormal(min, Vector3D.Unit.MINUS_Y, TEST_PRECISION);
        final Plane back = Planes.fromPointAndNormal(max, Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        final Plane diag = Planes.fromPointAndNormal(Vector3D.of(0.5, 0.5, 0.5), Vector3D.of(0.5, -0.5, 0), TEST_PRECISION);
        final Plane midCut = Planes.fromPointAndNormal(Vector3D.of(0.5, 0.5, 0.5), Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        tree.getRoot()
            .cut(diag, RegionCutRule.INHERIT);

        tree.getRoot()
            .getMinus().cut(top)
            .getMinus().cut(bottom.reverse(), RegionCutRule.PLUS_INSIDE)
            .getPlus().cut(left, RegionCutRule.MINUS_INSIDE)
            .getMinus().cut(back.reverse(), RegionCutRule.PLUS_INSIDE)
            .getPlus().cut(midCut, RegionCutRule.INHERIT);

        tree.getRoot()
            .getPlus().cut(top.reverse(), RegionCutRule.PLUS_INSIDE)
            .getPlus().cut(bottom)
            .getMinus().cut(right, RegionCutRule.MINUS_INSIDE)
            .getMinus().cut(front.reverse(), RegionCutRule.PLUS_INSIDE)
            .getPlus().cut(midCut, RegionCutRule.INHERIT);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(6, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFrom_boundaries_1_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.from(Arrays.asList(
                    Planes.convexPolygonFromVertices(Arrays.asList(
                            Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y), TEST_PRECISION),
                    Planes.convexPolygonFromVertices(Arrays.asList(
                            Vector3D.ZERO, Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X), TEST_PRECISION)
                ));

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testFrom_boundaries_2_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.from(Arrays.asList(
                    Planes.convexPolygonFromVertices(Arrays.asList(
                            Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y), TEST_PRECISION),
                    Planes.convexPolygonFromVertices(Arrays.asList(
                            Vector3D.ZERO, Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X), TEST_PRECISION)
                ));

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testFrom_boundaries_3_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.from(Arrays.asList(
                    Planes.convexPolygonFromVertices(Arrays.asList(
                            Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y), TEST_PRECISION),
                    Planes.convexPolygonFromVertices(Arrays.asList(
                            Vector3D.ZERO, Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X), TEST_PRECISION)
                ));

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getLocation());
    }

    @Test
    void testFrom_boundaries_fullIsTrue_1_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.from(Arrays.asList(
                    Planes.convexPolygonFromVertices(Arrays.asList(
                            Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y), TEST_PRECISION),
                    Planes.convexPolygonFromVertices(Arrays.asList(
                            Vector3D.ZERO, Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X), TEST_PRECISION)
                ), true);

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testFrom_boundaries_fullIsTrue_2_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.from(Arrays.asList(
                    Planes.convexPolygonFromVertices(Arrays.asList(
                            Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y), TEST_PRECISION),
                    Planes.convexPolygonFromVertices(Arrays.asList(
                            Vector3D.ZERO, Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X), TEST_PRECISION)
                ), true);

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testFrom_boundaries_fullIsTrue_3_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.from(Arrays.asList(
                    Planes.convexPolygonFromVertices(Arrays.asList(
                            Vector3D.ZERO, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y), TEST_PRECISION),
                    Planes.convexPolygonFromVertices(Arrays.asList(
                            Vector3D.ZERO, Vector3D.Unit.MINUS_Z, Vector3D.Unit.PLUS_X), TEST_PRECISION)
                ), true);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.INSIDE, tree.getRoot().getLocation());
    }

    @Test
    void testFrom_boundaries_noBoundaries_1_oe() {
        // act/assert
        Assertions.assertTrue(RegionBSPTree3D.from(Collections.emptyList()).isEmpty());
    }

    @Test
    void testFrom_boundaries_noBoundaries_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertTrue(RegionBSPTree3D.from(Collections.emptyList(), true).isFull());
    }

    @Test
    void testFrom_boundaries_noBoundaries_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(RegionBSPTree3D.from(Collections.emptyList(), false).isEmpty());
    }

    @Test
    void testFromConvexVolume_full_1_oe() {
        // arrange
        final ConvexVolume volume = ConvexVolume.full();

        // act
        final RegionBSPTree3D tree = volume.toTree();
        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testFromConvexVolume_full_2_oe() {
        // arrange
        final ConvexVolume volume = ConvexVolume.full();

        // act
        final RegionBSPTree3D tree = volume.toTree();
        // removed other assertion

        // assert
        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testFromConvexVolume_infinite_3_oe() {
        // arrange
        final ConvexVolume volume = ConvexVolume.fromBounds(Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION));

        // act
        final RegionBSPTree3D tree = volume.toTree();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testFromConvexVolume_finite_1_oe() {
        // arrange
        final ConvexVolume volume = ConvexVolume.fromBounds(
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION),

                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Z, TEST_PRECISION)
                );

        // act
        final RegionBSPTree3D tree = volume.toTree();

        // assert
        Assertions.assertEquals(1, tree.getSize(), TEST_EPS);
    }

    @Test
    void testFromConvexVolume_finite_2_oe() {
        // arrange
        final ConvexVolume volume = ConvexVolume.fromBounds(
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION),

                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_X, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Y, TEST_PRECISION),
                    Planes.fromPointAndNormal(Vector3D.of(1, 1, 1), Vector3D.Unit.PLUS_Z, TEST_PRECISION)
                );

        // act
        final RegionBSPTree3D tree = volume.toTree();

        // assert
        // removed other assertion
        Assertions.assertEquals(6, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testLinecastFirst_multipleDirections_4_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(-1, -1, -1), Vector3D.of(1, 1, 1));

        final Line3D xPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(1, 0, 0), TEST_PRECISION);
        final Line3D xMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(-1, 0, 0), TEST_PRECISION);

        final Line3D yPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 1, 0), TEST_PRECISION);
        final Line3D yMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, -1, 0), TEST_PRECISION);

        final Line3D zPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 0, 1), TEST_PRECISION);
        final Line3D zMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 0, -1), TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(tree.linecastFirst(xPlus.rayFrom(Vector3D.of(1.1, 0, 0))));
    }

    @Test
    void testLinecastFirst_multipleDirections_8_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(-1, -1, -1), Vector3D.of(1, 1, 1));

        final Line3D xPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(1, 0, 0), TEST_PRECISION);
        final Line3D xMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(-1, 0, 0), TEST_PRECISION);

        final Line3D yPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 1, 0), TEST_PRECISION);
        final Line3D yMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, -1, 0), TEST_PRECISION);

        final Line3D zPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 0, 1), TEST_PRECISION);
        final Line3D zMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 0, -1), TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(tree.linecastFirst(xMinus.rayFrom(Vector3D.of(-1.1, 0, 0))));
    }

    @Test
    void testLinecastFirst_multipleDirections_12_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(-1, -1, -1), Vector3D.of(1, 1, 1));

        final Line3D xPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(1, 0, 0), TEST_PRECISION);
        final Line3D xMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(-1, 0, 0), TEST_PRECISION);

        final Line3D yPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 1, 0), TEST_PRECISION);
        final Line3D yMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, -1, 0), TEST_PRECISION);

        final Line3D zPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 0, 1), TEST_PRECISION);
        final Line3D zMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 0, -1), TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(tree.linecastFirst(yPlus.rayFrom(Vector3D.of(0, 1.1, 0))));
    }

    @Test
    void testLinecastFirst_multipleDirections_16_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(-1, -1, -1), Vector3D.of(1, 1, 1));

        final Line3D xPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(1, 0, 0), TEST_PRECISION);
        final Line3D xMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(-1, 0, 0), TEST_PRECISION);

        final Line3D yPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 1, 0), TEST_PRECISION);
        final Line3D yMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, -1, 0), TEST_PRECISION);

        final Line3D zPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 0, 1), TEST_PRECISION);
        final Line3D zMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 0, -1), TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(tree.linecastFirst(yMinus.rayFrom(Vector3D.of(0, -1.1, 0))));
    }

    @Test
    void testLinecastFirst_multipleDirections_20_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(-1, -1, -1), Vector3D.of(1, 1, 1));

        final Line3D xPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(1, 0, 0), TEST_PRECISION);
        final Line3D xMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(-1, 0, 0), TEST_PRECISION);

        final Line3D yPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 1, 0), TEST_PRECISION);
        final Line3D yMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, -1, 0), TEST_PRECISION);

        final Line3D zPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 0, 1), TEST_PRECISION);
        final Line3D zMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 0, -1), TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(tree.linecastFirst(zPlus.rayFrom(Vector3D.of(0, 0, 1.1))));
    }

    @Test
    void testLinecastFirst_multipleDirections_24_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(-1, -1, -1), Vector3D.of(1, 1, 1));

        final Line3D xPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(1, 0, 0), TEST_PRECISION);
        final Line3D xMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(-1, 0, 0), TEST_PRECISION);

        final Line3D yPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 1, 0), TEST_PRECISION);
        final Line3D yMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, -1, 0), TEST_PRECISION);

        final Line3D zPlus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 0, 1), TEST_PRECISION);
        final Line3D zMinus = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.of(0, 0, -1), TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(tree.linecastFirst(zMinus.rayFrom(Vector3D.of(0, 0, -1.1))));
    }

    @Test
    void testLinecastFirst_linePassesThroughVertex_1_oe() {
        // arrange
        final Vector3D lowerCorner = Vector3D.ZERO;
        final Vector3D upperCorner = Vector3D.of(1, 1, 1);
        final Vector3D center = lowerCorner.lerp(upperCorner, 0.5);

        final RegionBSPTree3D tree = createRect(lowerCorner, upperCorner);

        final Line3D upDiagonal = Lines3D.fromPoints(lowerCorner, upperCorner, TEST_PRECISION);
        final Line3D downDiagonal = upDiagonal.reverse();

        // act/assert
        final LinecastPoint3D upFromOutsideResult = tree.linecastFirst(upDiagonal.rayFrom(Vector3D.of(-1, -1, -1)));
        Assertions.assertNotNull(upFromOutsideResult);
    }

    @Test
    void testLinecastFirst_linePassesThroughVertex_3_oe() {
        // arrange
        final Vector3D lowerCorner = Vector3D.ZERO;
        final Vector3D upperCorner = Vector3D.of(1, 1, 1);
        final Vector3D center = lowerCorner.lerp(upperCorner, 0.5);

        final RegionBSPTree3D tree = createRect(lowerCorner, upperCorner);

        final Line3D upDiagonal = Lines3D.fromPoints(lowerCorner, upperCorner, TEST_PRECISION);
        final Line3D downDiagonal = upDiagonal.reverse();

        // act/assert
        final LinecastPoint3D upFromOutsideResult = tree.linecastFirst(upDiagonal.rayFrom(Vector3D.of(-1, -1, -1)));
        // removed other assertion
        // removed other assertion

        final LinecastPoint3D upFromCenterResult = tree.linecastFirst(upDiagonal.rayFrom(center));
        Assertions.assertNotNull(upFromCenterResult);
    }

    @Test
    void testLinecastFirst_linePassesThroughVertex_5_oe() {
        // arrange
        final Vector3D lowerCorner = Vector3D.ZERO;
        final Vector3D upperCorner = Vector3D.of(1, 1, 1);
        final Vector3D center = lowerCorner.lerp(upperCorner, 0.5);

        final RegionBSPTree3D tree = createRect(lowerCorner, upperCorner);

        final Line3D upDiagonal = Lines3D.fromPoints(lowerCorner, upperCorner, TEST_PRECISION);
        final Line3D downDiagonal = upDiagonal.reverse();

        // act/assert
        final LinecastPoint3D upFromOutsideResult = tree.linecastFirst(upDiagonal.rayFrom(Vector3D.of(-1, -1, -1)));
        // removed other assertion
        // removed other assertion

        final LinecastPoint3D upFromCenterResult = tree.linecastFirst(upDiagonal.rayFrom(center));
        // removed other assertion
        // removed other assertion

        final LinecastPoint3D downFromOutsideResult = tree.linecastFirst(downDiagonal.rayFrom(Vector3D.of(2, 2, 2)));
        Assertions.assertNotNull(downFromOutsideResult);
    }

    @Test
    void testLinecastFirst_linePassesThroughVertex_7_oe() {
        // arrange
        final Vector3D lowerCorner = Vector3D.ZERO;
        final Vector3D upperCorner = Vector3D.of(1, 1, 1);
        final Vector3D center = lowerCorner.lerp(upperCorner, 0.5);

        final RegionBSPTree3D tree = createRect(lowerCorner, upperCorner);

        final Line3D upDiagonal = Lines3D.fromPoints(lowerCorner, upperCorner, TEST_PRECISION);
        final Line3D downDiagonal = upDiagonal.reverse();

        // act/assert
        final LinecastPoint3D upFromOutsideResult = tree.linecastFirst(upDiagonal.rayFrom(Vector3D.of(-1, -1, -1)));
        // removed other assertion
        // removed other assertion

        final LinecastPoint3D upFromCenterResult = tree.linecastFirst(upDiagonal.rayFrom(center));
        // removed other assertion
        // removed other assertion

        final LinecastPoint3D downFromOutsideResult = tree.linecastFirst(downDiagonal.rayFrom(Vector3D.of(2, 2, 2)));
        // removed other assertion
        // removed other assertion

        final LinecastPoint3D downFromCenterResult = tree.linecastFirst(downDiagonal.rayFrom(center));
        Assertions.assertNotNull(downFromCenterResult);
    }

    @Test
    void testLinecastFirst_lineParallelToFace_1_oe() {
        // arrange - setup box
        final Vector3D lowerCorner = Vector3D.ZERO;
        final Vector3D upperCorner = Vector3D.of(1, 1, 1);

        final RegionBSPTree3D tree = createRect(lowerCorner, upperCorner);

        final Vector3D firstPointOnLine = Vector3D.of(0.5, -1.0, 0);
        final Vector3D secondPointOnLine = Vector3D.of(0.5, 2.0, 0);
        final Line3D bottomLine = Lines3D.fromPoints(firstPointOnLine, secondPointOnLine, TEST_PRECISION);

        final Vector3D expectedIntersection1 = Vector3D.of(0.5, 0, 0.0);
        final Vector3D expectedIntersection2 = Vector3D.of(0.5, 1.0, 0.0);

        // act/assert
        LinecastPoint3D bottom = tree.linecastFirst(bottomLine.rayFrom(firstPointOnLine));
        Assertions.assertNotNull(bottom);
    }

    @Test
    void testLinecastFirst_lineParallelToFace_3_oe() {
        // arrange - setup box
        final Vector3D lowerCorner = Vector3D.ZERO;
        final Vector3D upperCorner = Vector3D.of(1, 1, 1);

        final RegionBSPTree3D tree = createRect(lowerCorner, upperCorner);

        final Vector3D firstPointOnLine = Vector3D.of(0.5, -1.0, 0);
        final Vector3D secondPointOnLine = Vector3D.of(0.5, 2.0, 0);
        final Line3D bottomLine = Lines3D.fromPoints(firstPointOnLine, secondPointOnLine, TEST_PRECISION);

        final Vector3D expectedIntersection1 = Vector3D.of(0.5, 0, 0.0);
        final Vector3D expectedIntersection2 = Vector3D.of(0.5, 1.0, 0.0);

        // act/assert
        LinecastPoint3D bottom = tree.linecastFirst(bottomLine.rayFrom(firstPointOnLine));
        // removed other assertion
        // removed other assertion

        bottom = tree.linecastFirst(bottomLine.rayFrom(Vector3D.of(0.5, 0.1, 0.0)));
        Assertions.assertNotNull(bottom);
    }

    @Test
    void testLinecastFirst_lineParallelToFace_4_oe() {
        // arrange - setup box
        final Vector3D lowerCorner = Vector3D.ZERO;
        final Vector3D upperCorner = Vector3D.of(1, 1, 1);

        final RegionBSPTree3D tree = createRect(lowerCorner, upperCorner);

        final Vector3D firstPointOnLine = Vector3D.of(0.5, -1.0, 0);
        final Vector3D secondPointOnLine = Vector3D.of(0.5, 2.0, 0);
        final Line3D bottomLine = Lines3D.fromPoints(firstPointOnLine, secondPointOnLine, TEST_PRECISION);

        final Vector3D expectedIntersection1 = Vector3D.of(0.5, 0, 0.0);
        final Vector3D expectedIntersection2 = Vector3D.of(0.5, 1.0, 0.0);

        // act/assert
        LinecastPoint3D bottom = tree.linecastFirst(bottomLine.rayFrom(firstPointOnLine));
        // removed other assertion
        // removed other assertion

        bottom = tree.linecastFirst(bottomLine.rayFrom(Vector3D.of(0.5, 0.1, 0.0)));
        // removed other assertion
        final Vector3D intersection = bottom.getPoint();
        Assertions.assertNotNull(intersection);
    }

    @Test
    void testLinecastFirst_onlyReturnsPointsWithinSegment_7_oe() {
        // arrange
        final Vector3D lowerCorner = Vector3D.ZERO;
        final Vector3D upperCorner = Vector3D.of(1, 1, 1);

        final RegionBSPTree3D tree = createRect(lowerCorner, upperCorner);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0.5, 0.5, 0.5), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertNull(tree.linecastFirst(line.segment(Vector3D.of(-2, 0.5, 0.5), Vector3D.of(-1, 0.5, 0.5))));
    }

    @Test
    void testLinecastFirst_onlyReturnsPointsWithinSegment_8_oe() {
        // arrange
        final Vector3D lowerCorner = Vector3D.ZERO;
        final Vector3D upperCorner = Vector3D.of(1, 1, 1);

        final RegionBSPTree3D tree = createRect(lowerCorner, upperCorner);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0.5, 0.5, 0.5), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(tree.linecastFirst(line.segment(Vector3D.of(-2, 0.5, 0.5), Vector3D.of(-1, 0.5, 0.5))));
    }

    @Test
    void testLinecastFirst_onlyReturnsPointsWithinSegment_9_oe() {
        // arrange
        final Vector3D lowerCorner = Vector3D.ZERO;
        final Vector3D upperCorner = Vector3D.of(1, 1, 1);

        final RegionBSPTree3D tree = createRect(lowerCorner, upperCorner);

        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0.5, 0.5, 0.5), Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNull(tree.linecastFirst(line.segment(Vector3D.of(0.25, 0.5, 0.5), Vector3D.of(0.75, 0.5, 0.5))));
    }

    @Test
    void testInvertedRegion_1_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5));

        // act
        tree.complement();

        // assert
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testInvertedRegion_2_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5));

        // act
        tree.complement();

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testInvertedRegion_4_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5));

        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(6, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testInvertedRegion_5_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5));

        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testUnitBox_1_oe() {
        // act
        final RegionBSPTree3D tree = createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5));

        // assert
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testUnitBox_2_oe() {
        // act
        final RegionBSPTree3D tree = createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5));

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testUnitBox_3_oe() {
        // act
        final RegionBSPTree3D tree = createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5));

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1.0, tree.getSize(), TEST_EPS);
    }

    @Test
    void testUnitBox_4_oe() {
        // act
        final RegionBSPTree3D tree = createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5));

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(6.0, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testTwoBoxes_disjoint_1_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5)));
        tree.union(createRect(Vector3D.of(1.5, -0.5, -0.5), Vector3D.of(2.5, 0.5, 0.5)));

        // assert
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTwoBoxes_disjoint_2_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5)));
        tree.union(createRect(Vector3D.of(1.5, -0.5, -0.5), Vector3D.of(2.5, 0.5, 0.5)));

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testTwoBoxes_disjoint_3_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5)));
        tree.union(createRect(Vector3D.of(1.5, -0.5, -0.5), Vector3D.of(2.5, 0.5, 0.5)));

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(2.0, tree.getSize(), TEST_EPS);
    }

    @Test
    void testTwoBoxes_disjoint_4_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5)));
        tree.union(createRect(Vector3D.of(1.5, -0.5, -0.5), Vector3D.of(2.5, 0.5, 0.5)));

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(12.0, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testTwoBoxes_sharedSide_1_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5)));
        tree.union(createRect(Vector3D.of(0.5, -0.5, -0.5), Vector3D.of(1.5, 0.5, 0.5)));

        // assert
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTwoBoxes_sharedSide_2_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5)));
        tree.union(createRect(Vector3D.of(0.5, -0.5, -0.5), Vector3D.of(1.5, 0.5, 0.5)));

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testTwoBoxes_sharedSide_3_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5)));
        tree.union(createRect(Vector3D.of(0.5, -0.5, -0.5), Vector3D.of(1.5, 0.5, 0.5)));

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(2.0, tree.getSize(), TEST_EPS);
    }

    @Test
    void testTwoBoxes_sharedSide_4_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5)));
        tree.union(createRect(Vector3D.of(0.5, -0.5, -0.5), Vector3D.of(1.5, 0.5, 0.5)));

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(10.0, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testTwoBoxes_separationLessThanTolerance_1_oe() {
        // arrange
        final double eps = 1e-6;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5), precision));
        tree.union(createRect(Vector3D.of(0.5 + 1e-7, -0.5, -0.5), Vector3D.of(1.5 + 1e-7, 0.5, 0.5), precision));

        // assert
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTwoBoxes_separationLessThanTolerance_2_oe() {
        // arrange
        final double eps = 1e-6;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5), precision));
        tree.union(createRect(Vector3D.of(0.5 + 1e-7, -0.5, -0.5), Vector3D.of(1.5 + 1e-7, 0.5, 0.5), precision));

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testTwoBoxes_separationLessThanTolerance_3_oe() {
        // arrange
        final double eps = 1e-6;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5), precision));
        tree.union(createRect(Vector3D.of(0.5 + 1e-7, -0.5, -0.5), Vector3D.of(1.5 + 1e-7, 0.5, 0.5), precision));

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(2.0, tree.getSize(), eps);
    }

    @Test
    void testTwoBoxes_separationLessThanTolerance_4_oe() {
        // arrange
        final double eps = 1e-6;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5), precision));
        tree.union(createRect(Vector3D.of(0.5 + 1e-7, -0.5, -0.5), Vector3D.of(1.5 + 1e-7, 0.5, 0.5), precision));

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(10.0, tree.getBoundarySize(), eps);
    }

    @Test
    void testTwoBoxes_sharedEdge_1_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5)));
        tree.union(createRect(Vector3D.of(0.5, 0.5, -0.5), Vector3D.of(1.5, 1.5, 0.5)));

        // assert
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTwoBoxes_sharedEdge_2_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5)));
        tree.union(createRect(Vector3D.of(0.5, 0.5, -0.5), Vector3D.of(1.5, 1.5, 0.5)));

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testTwoBoxes_sharedEdge_3_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5)));
        tree.union(createRect(Vector3D.of(0.5, 0.5, -0.5), Vector3D.of(1.5, 1.5, 0.5)));

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(2.0, tree.getSize(), TEST_EPS);
    }

    @Test
    void testTwoBoxes_sharedEdge_4_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5)));
        tree.union(createRect(Vector3D.of(0.5, 0.5, -0.5), Vector3D.of(1.5, 1.5, 0.5)));

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(12.0, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testTwoBoxes_sharedPoint_1_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5)));
        tree.union(createRect(Vector3D.of(0.5, 0.5, 0.5), Vector3D.of(1.5, 1.5, 1.5)));

        // assert
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTwoBoxes_sharedPoint_2_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5)));
        tree.union(createRect(Vector3D.of(0.5, 0.5, 0.5), Vector3D.of(1.5, 1.5, 1.5)));

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testTwoBoxes_sharedPoint_3_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5)));
        tree.union(createRect(Vector3D.of(0.5, 0.5, 0.5), Vector3D.of(1.5, 1.5, 1.5)));

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(2.0, tree.getSize(), TEST_EPS);
    }

    @Test
    void testTwoBoxes_sharedPoint_4_oe() {
        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.empty();
        tree.union(createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5)));
        tree.union(createRect(Vector3D.of(0.5, 0.5, 0.5), Vector3D.of(1.5, 1.5, 1.5)));

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(12.0, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testTetrahedron_1_oe() {
        // arrange
        final Vector3D vertex1 = Vector3D.of(1, 2, 3);
        final Vector3D vertex2 = Vector3D.of(2, 2, 4);
        final Vector3D vertex3 = Vector3D.of(2, 3, 3);
        final Vector3D vertex4 = Vector3D.of(1, 3, 4);

        final List<PlaneConvexSubset> boundaries = Arrays.asList(
                Planes.convexPolygonFromVertices(Arrays.asList(vertex3, vertex2, vertex1), TEST_PRECISION),
                Planes.convexPolygonFromVertices(Arrays.asList(vertex2, vertex3, vertex4), TEST_PRECISION),
                Planes.convexPolygonFromVertices(Arrays.asList(vertex4, vertex3, vertex1), TEST_PRECISION),
                Planes.convexPolygonFromVertices(Arrays.asList(vertex1, vertex2, vertex4), TEST_PRECISION)
            );

        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.full();
        tree.insert(boundaries);

        // assert
        Assertions.assertEquals(1.0 / 3.0, tree.getSize(), TEST_EPS);
    }

    @Test
    void testTetrahedron_2_oe() {
        // arrange
        final Vector3D vertex1 = Vector3D.of(1, 2, 3);
        final Vector3D vertex2 = Vector3D.of(2, 2, 4);
        final Vector3D vertex3 = Vector3D.of(2, 3, 3);
        final Vector3D vertex4 = Vector3D.of(1, 3, 4);

        final List<PlaneConvexSubset> boundaries = Arrays.asList(
                Planes.convexPolygonFromVertices(Arrays.asList(vertex3, vertex2, vertex1), TEST_PRECISION),
                Planes.convexPolygonFromVertices(Arrays.asList(vertex2, vertex3, vertex4), TEST_PRECISION),
                Planes.convexPolygonFromVertices(Arrays.asList(vertex4, vertex3, vertex1), TEST_PRECISION),
                Planes.convexPolygonFromVertices(Arrays.asList(vertex1, vertex2, vertex4), TEST_PRECISION)
            );

        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.full();
        tree.insert(boundaries);

        // assert
        // removed other assertion
        Assertions.assertEquals(2.0 * Math.sqrt(3.0), tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testSphere_1_oe() {
        // arrange
        // (use a high tolerance value here since the sphere is only an approximation)
        final double approximationTolerance = 0.2;
        final double radius = 1.0;

        // act
        final RegionBSPTree3D tree = createSphere(Vector3D.of(1, 2, 3), radius, 8, 16);

        // assert
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testSphere_2_oe() {
        // arrange
        // (use a high tolerance value here since the sphere is only an approximation)
        final double approximationTolerance = 0.2;
        final double radius = 1.0;

        // act
        final RegionBSPTree3D tree = createSphere(Vector3D.of(1, 2, 3), radius, 8, 16);

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testSphere_3_oe() {
        // arrange
        // (use a high tolerance value here since the sphere is only an approximation)
        final double approximationTolerance = 0.2;
        final double radius = 1.0;

        // act
        final RegionBSPTree3D tree = createSphere(Vector3D.of(1, 2, 3), radius, 8, 16);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(sphereVolume(radius), tree.getSize(), approximationTolerance);
    }

    @Test
    void testSphere_4_oe() {
        // arrange
        // (use a high tolerance value here since the sphere is only an approximation)
        final double approximationTolerance = 0.2;
        final double radius = 1.0;

        // act
        final RegionBSPTree3D tree = createSphere(Vector3D.of(1, 2, 3), radius, 8, 16);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(sphereSurface(radius), tree.getBoundarySize(), approximationTolerance);
    }

    @Test
    void testBoolean_union_1_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphere = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.union(box, sphere);

        // assert
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void testBoolean_union_2_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphere = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.union(box, sphere);

        // assert
        // removed other assertion
        Assertions.assertFalse(result.isFull());
    }

    @Test
    void testBoolean_union_3_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphere = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.union(box, sphere);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(cubeVolume(size)+(sphereVolume(radius)* 0.5),result.getSize(),tolerance);
    }

    @Test
    void testBoolean_union_4_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphere = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.union(box, sphere);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(cubeSurface(size)- circleSurface(radius)+(0.5 * sphereSurface(radius)),result.getBoundarySize(),tolerance);
    }

    @Test
    void testUnion_self_1_oe() {
        // arrange
        final double tolerance = 0.2;
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);

        final RegionBSPTree3D copy = RegionBSPTree3D.empty();
        copy.copy(sphere);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.union(sphere, copy);

        // assert
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void testUnion_self_2_oe() {
        // arrange
        final double tolerance = 0.2;
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);

        final RegionBSPTree3D copy = RegionBSPTree3D.empty();
        copy.copy(sphere);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.union(sphere, copy);

        // assert
        // removed other assertion
        Assertions.assertFalse(result.isFull());
    }

    @Test
    void testUnion_self_3_oe() {
        // arrange
        final double tolerance = 0.2;
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);

        final RegionBSPTree3D copy = RegionBSPTree3D.empty();
        copy.copy(sphere);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.union(sphere, copy);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(sphereVolume(radius), result.getSize(), tolerance);
    }

    @Test
    void testUnion_self_4_oe() {
        // arrange
        final double tolerance = 0.2;
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);

        final RegionBSPTree3D copy = RegionBSPTree3D.empty();
        copy.copy(sphere);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.union(sphere, copy);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(sphereSurface(radius), result.getBoundarySize(), tolerance);
    }

    @Test
    void testBoolean_intersection_1_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphere = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.intersection(box, sphere);

        // assert
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void testBoolean_intersection_2_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphere = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.intersection(box, sphere);

        // assert
        // removed other assertion
        Assertions.assertFalse(result.isFull());
    }

    @Test
    void testBoolean_intersection_3_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphere = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.intersection(box, sphere);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(sphereVolume(radius) * 0.5, result.getSize(), tolerance);
    }

    @Test
    void testBoolean_intersection_4_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphere = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.intersection(box, sphere);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(circleSurface(radius)+(0.5 * sphereSurface(radius)),result.getBoundarySize(),tolerance);
    }

    @Test
    void testIntersection_self_1_oe() {
        // arrange
        final double tolerance = 0.2;
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);
        final RegionBSPTree3D copy = RegionBSPTree3D.empty();
        copy.copy(sphere);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.intersection(sphere, copy);

        // assert
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void testIntersection_self_2_oe() {
        // arrange
        final double tolerance = 0.2;
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);
        final RegionBSPTree3D copy = RegionBSPTree3D.empty();
        copy.copy(sphere);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.intersection(sphere, copy);

        // assert
        // removed other assertion
        Assertions.assertFalse(result.isFull());
    }

    @Test
    void testIntersection_self_3_oe() {
        // arrange
        final double tolerance = 0.2;
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);
        final RegionBSPTree3D copy = RegionBSPTree3D.empty();
        copy.copy(sphere);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.intersection(sphere, copy);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(sphereVolume(radius), result.getSize(), tolerance);
    }

    @Test
    void testIntersection_self_4_oe() {
        // arrange
        final double tolerance = 0.2;
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);
        final RegionBSPTree3D copy = RegionBSPTree3D.empty();
        copy.copy(sphere);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.intersection(sphere, copy);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(sphereSurface(radius), result.getBoundarySize(), tolerance);
    }

    @Test
    void testBoolean_xor_twoCubes_1_oe() throws IOException {
        // arrange
        final double size = 1.0;
        final RegionBSPTree3D box1 = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D box2 = createRect(Vector3D.of(0.5, 0.5, 0.5), Vector3D.of(0.5 + size, 0.5 + size, 0.5 + size));

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.xor(box1, box2);

        // assert
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void testBoolean_xor_twoCubes_2_oe() throws IOException {
        // arrange
        final double size = 1.0;
        final RegionBSPTree3D box1 = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D box2 = createRect(Vector3D.of(0.5, 0.5, 0.5), Vector3D.of(0.5 + size, 0.5 + size, 0.5 + size));

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.xor(box1, box2);

        // assert
        // removed other assertion
        Assertions.assertFalse(result.isFull());
    }

    @Test
    void testBoolean_xor_twoCubes_3_oe() throws IOException {
        // arrange
        final double size = 1.0;
        final RegionBSPTree3D box1 = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D box2 = createRect(Vector3D.of(0.5, 0.5, 0.5), Vector3D.of(0.5 + size, 0.5 + size, 0.5 + size));

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.xor(box1, box2);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals((2 * cubeVolume(size)) - (2 * cubeVolume(size * 0.5)), result.getSize(), TEST_EPS);
    }

    @Test
    void testBoolean_xor_twoCubes_4_oe() throws IOException {
        // arrange
        final double size = 1.0;
        final RegionBSPTree3D box1 = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D box2 = createRect(Vector3D.of(0.5, 0.5, 0.5), Vector3D.of(0.5 + size, 0.5 + size, 0.5 + size));

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.xor(box1, box2);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(2 * cubeSurface(size), result.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testBoolean_xor_cubeAndSphere_1_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphere = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.xor(box, sphere);

        // assert
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void testBoolean_xor_cubeAndSphere_2_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphere = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.xor(box, sphere);

        // assert
        // removed other assertion
        Assertions.assertFalse(result.isFull());
    }

    @Test
    void testBoolean_xor_cubeAndSphere_3_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphere = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.xor(box, sphere);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(cubeVolume(size), result.getSize(), tolerance);
    }

    @Test
    void testBoolean_xor_cubeAndSphere_4_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphere = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.xor(box, sphere);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(cubeSurface(size)+(sphereSurface(radius)),result.getBoundarySize(),tolerance);
    }

    @Test
    void testXor_self_1_oe() {
        // arrange
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);
        final RegionBSPTree3D copy = RegionBSPTree3D.empty();
        copy.copy(sphere);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.xor(sphere, copy);

        // assert
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testXor_self_2_oe() {
        // arrange
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);
        final RegionBSPTree3D copy = RegionBSPTree3D.empty();
        copy.copy(sphere);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.xor(sphere, copy);

        // assert
        // removed other assertion
        Assertions.assertFalse(result.isFull());
    }

    @Test
    void testXor_self_3_oe() {
        // arrange
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);
        final RegionBSPTree3D copy = RegionBSPTree3D.empty();
        copy.copy(sphere);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.xor(sphere, copy);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0.0, result.getSize(), TEST_EPS);
    }

    @Test
    void testXor_self_4_oe() {
        // arrange
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);
        final RegionBSPTree3D copy = RegionBSPTree3D.empty();
        copy.copy(sphere);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.xor(sphere, copy);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0.0, result.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testXor_self_5_oe() {
        // arrange
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);
        final RegionBSPTree3D copy = RegionBSPTree3D.empty();
        copy.copy(sphere);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.xor(sphere, copy);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNull(result.getCentroid());
    }

    @Test
    void testBoolean_difference_1_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphere = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.difference(box, sphere);

        // assert
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void testBoolean_difference_2_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphere = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.difference(box, sphere);

        // assert
        // removed other assertion
        Assertions.assertFalse(result.isFull());
    }

    @Test
    void testBoolean_difference_3_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphere = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.difference(box, sphere);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(cubeVolume(size) - (sphereVolume(radius) * 0.5), result.getSize(), tolerance);
    }

    @Test
    void testBoolean_difference_4_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphere = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.difference(box, sphere);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(cubeSurface(size)- circleSurface(radius)+(0.5 * sphereSurface(radius)),result.getBoundarySize(),tolerance);
    }

    @Test
    void testDifference_self_1_oe() {
        // arrange
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);
        final RegionBSPTree3D copy = sphere.copy();

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.difference(sphere, copy);

        // assert
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testDifference_self_2_oe() {
        // arrange
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);
        final RegionBSPTree3D copy = sphere.copy();

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.difference(sphere, copy);

        // assert
        // removed other assertion
        Assertions.assertFalse(result.isFull());
    }

    @Test
    void testDifference_self_3_oe() {
        // arrange
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);
        final RegionBSPTree3D copy = sphere.copy();

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.difference(sphere, copy);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0.0, result.getSize(), TEST_EPS);
    }

    @Test
    void testDifference_self_4_oe() {
        // arrange
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);
        final RegionBSPTree3D copy = sphere.copy();

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.difference(sphere, copy);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0.0, result.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testDifference_self_5_oe() {
        // arrange
        final double radius = 1.0;

        final RegionBSPTree3D sphere = createSphere(Vector3D.ZERO, radius, 8, 16);
        final RegionBSPTree3D copy = sphere.copy();

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.difference(sphere, copy);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNull(result.getCentroid());
    }

    @Test
    void testBoolean_multiple_1_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphereToAdd = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);
        final RegionBSPTree3D sphereToRemove1 = createSphere(Vector3D.of(size * 0.5, 0, size * 0.5), radius, 8, 16);
        final RegionBSPTree3D sphereToRemove2 = createSphere(Vector3D.of(size * 0.5, 1, size * 0.5), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.union(box, sphereToAdd);
        result.difference(sphereToRemove1);
        result.difference(sphereToRemove2);

        // assert
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void testBoolean_multiple_2_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphereToAdd = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);
        final RegionBSPTree3D sphereToRemove1 = createSphere(Vector3D.of(size * 0.5, 0, size * 0.5), radius, 8, 16);
        final RegionBSPTree3D sphereToRemove2 = createSphere(Vector3D.of(size * 0.5, 1, size * 0.5), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.union(box, sphereToAdd);
        result.difference(sphereToRemove1);
        result.difference(sphereToRemove2);

        // assert
        // removed other assertion
        Assertions.assertFalse(result.isFull());
    }

    @Test
    void testBoolean_multiple_3_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphereToAdd = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);
        final RegionBSPTree3D sphereToRemove1 = createSphere(Vector3D.of(size * 0.5, 0, size * 0.5), radius, 8, 16);
        final RegionBSPTree3D sphereToRemove2 = createSphere(Vector3D.of(size * 0.5, 1, size * 0.5), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.union(box, sphereToAdd);
        result.difference(sphereToRemove1);
        result.difference(sphereToRemove2);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(cubeVolume(size)-(sphereVolume(radius)* 0.5),result.getSize(),tolerance);
    }

    @Test
    void testBoolean_multiple_4_oe() throws IOException {
        // arrange
        final double tolerance = 0.05;
        final double size = 1.0;
        final double radius = size * 0.5;
        final RegionBSPTree3D box = createRect(Vector3D.ZERO, Vector3D.of(size, size, size));
        final RegionBSPTree3D sphereToAdd = createSphere(Vector3D.of(size * 0.5, size * 0.5, size), radius, 8, 16);
        final RegionBSPTree3D sphereToRemove1 = createSphere(Vector3D.of(size * 0.5, 0, size * 0.5), radius, 8, 16);
        final RegionBSPTree3D sphereToRemove2 = createSphere(Vector3D.of(size * 0.5, 1, size * 0.5), radius, 8, 16);

        // act
        final RegionBSPTree3D result = RegionBSPTree3D.empty();
        result.union(box, sphereToAdd);
        result.difference(sphereToRemove1);
        result.difference(sphereToRemove2);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(cubeSurface(size)-(3.0 * circleSurface(radius))+(1.5 * sphereSurface(radius)),result.getBoundarySize(),tolerance);
    }

    @Test
    void testToConvex_empty_1_oe() {
        // act
        final List<ConvexVolume> result = RegionBSPTree3D.empty().toConvex();

        // assert
        Assertions.assertEquals(0, result.size());
    }

    @Test
    void testToConvex_singleBox_1_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(1, 2, 3), Vector3D.of(2, 3, 4));

        // act
        final List<ConvexVolume> result = tree.toConvex();

        // assert
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testToConvex_singleBox_2_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(1, 2, 3), Vector3D.of(2, 3, 4));

        // act
        final List<ConvexVolume> result = tree.toConvex();

        // assert
        // removed other assertion

        final ConvexVolume vol = result.get(0);
        Assertions.assertEquals(1, vol.getSize(), TEST_EPS);
    }

    @Test
    void testToConvex_multipleBoxes_1_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(4, 5, 6), Vector3D.of(5, 6, 7));
        tree.union(createRect(Vector3D.ZERO, Vector3D.of(2, 1, 1)));

        // act
        final List<ConvexVolume> result = tree.toConvex();

        // assert
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testToConvex_multipleBoxes_2_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(4, 5, 6), Vector3D.of(5, 6, 7));
        tree.union(createRect(Vector3D.ZERO, Vector3D.of(2, 1, 1)));

        // act
        final List<ConvexVolume> result = tree.toConvex();

        // assert
        // removed other assertion

        final boolean smallFirst = result.get(0).getSize() < result.get(1).getSize();

        final ConvexVolume small = smallFirst ? result.get(0) : result.get(1);
        final ConvexVolume large = smallFirst ? result.get(1) : result.get(0);

        Assertions.assertEquals(1, small.getSize(), TEST_EPS);
    }

    @Test
    void testToConvex_multipleBoxes_4_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(4, 5, 6), Vector3D.of(5, 6, 7));
        tree.union(createRect(Vector3D.ZERO, Vector3D.of(2, 1, 1)));

        // act
        final List<ConvexVolume> result = tree.toConvex();

        // assert
        // removed other assertion

        final boolean smallFirst = result.get(0).getSize() < result.get(1).getSize();

        final ConvexVolume small = smallFirst ? result.get(0) : result.get(1);
        final ConvexVolume large = smallFirst ? result.get(1) : result.get(0);

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(2, large.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_1_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5));

        final Plane splitter = Planes.fromNormal(Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Split<RegionBSPTree3D> split = tree.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_2_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5));

        final Plane splitter = Planes.fromNormal(Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Split<RegionBSPTree3D> split = tree.split(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree3D minus = split.getMinus();
        Assertions.assertEquals(0.5, minus.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_4_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.of(-0.5, -0.5, -0.5), Vector3D.of(0.5, 0.5, 0.5));

        final Plane splitter = Planes.fromNormal(Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Split<RegionBSPTree3D> split = tree.split(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree3D minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        final RegionBSPTree3D plus = split.getPlus();
        Assertions.assertEquals(0.5, plus.getSize(), TEST_EPS);
    }

    @Test
    void testGetNodeRegion_2_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.ZERO, Vector3D.of(1, 1, 1));

        // act/assert
        final ConvexVolume rootVol = tree.getRoot().getNodeRegion();
        // removed other assertion
        Assertions.assertNull(rootVol.getCentroid());
    }

    @Test
    void testGetNodeRegion_4_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.ZERO, Vector3D.of(1, 1, 1));

        // act/assert
        final ConvexVolume rootVol = tree.getRoot().getNodeRegion();
        // removed other assertion
        // removed other assertion

        final ConvexVolume plusVol = tree.getRoot().getPlus().getNodeRegion();
        // removed other assertion
        Assertions.assertNull(plusVol.getCentroid());
    }

    @Test
    void testGetNodeRegion_5_oe() {
        // arrange
        final RegionBSPTree3D tree = createRect(Vector3D.ZERO, Vector3D.of(1, 1, 1));

        // act/assert
        final ConvexVolume rootVol = tree.getRoot().getNodeRegion();
        // removed other assertion
        // removed other assertion

        final ConvexVolume plusVol = tree.getRoot().getPlus().getNodeRegion();
        // removed other assertion
        // removed other assertion

        final ConvexVolume centerVol = tree.findNode(Vector3D.of(0.5, 0.5, 0.5)).getNodeRegion();
        Assertions.assertEquals(1, centerVol.getSize(), TEST_EPS);
    }

    @Test
    void testSlightlyConcavePrism_1_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.of(0, 0, 0),
            Vector3D.of(2, 1e-7, 0),
            Vector3D.of(4, 0, 0),
            Vector3D.of(2, 2, 0),
            Vector3D.of(0, 0, 2),
            Vector3D.of(2, 1e-7, 2),
            Vector3D.of(4, 0, 2),
            Vector3D.of(2, 2, 2)
        };

        final int[][] facets = {
            {4, 5, 6, 7},
            {3, 2, 1, 0},
            {0, 1, 5, 4},
            {1, 2, 6, 5},
            {2, 3, 7, 6},
            {3, 0, 4, 7}
        };

        final List<PlaneConvexSubset> faces = indexedFacetsToBoundaries(vertices, facets);

        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.full();
        tree.insert(faces);

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testSlightlyConcavePrism_2_oe() {
        // arrange
        final Vector3D[] vertices = {
            Vector3D.of(0, 0, 0),
            Vector3D.of(2, 1e-7, 0),
            Vector3D.of(4, 0, 0),
            Vector3D.of(2, 2, 0),
            Vector3D.of(0, 0, 2),
            Vector3D.of(2, 1e-7, 2),
            Vector3D.of(4, 0, 2),
            Vector3D.of(2, 2, 2)
        };

        final int[][] facets = {
            {4, 5, 6, 7},
            {3, 2, 1, 0},
            {0, 1, 5, 4},
            {1, 2, 6, 5},
            {2, 3, 7, 6},
            {3, 0, 4, 7}
        };

        final List<PlaneConvexSubset> faces = indexedFacetsToBoundaries(vertices, facets);

        // act
        final RegionBSPTree3D tree = RegionBSPTree3D.full();
        tree.insert(faces);

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

}
