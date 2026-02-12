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
package org.apache.commons.geometry.euclidean.twod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.Region;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.core.partitioning.bsp.RegionCutRule;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.twod.RegionBSPTree2D.PartitionedRegionBuilder2D;
import org.apache.commons.geometry.euclidean.twod.RegionBSPTree2D.RegionNode2D;
import org.apache.commons.geometry.euclidean.twod.path.LinePath;
import org.apache.commons.geometry.euclidean.twod.shape.Parallelogram;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegionBSPTree2DTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static final Comparator<LineConvexSubset> SEGMENT_COMPARATOR =
        (a, b) -> Vector2D.COORDINATE_ASCENDING_ORDER.compare(a.getStartPoint(), b.getStartPoint());

    private static final Line X_AXIS = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);

    private static final Line Y_AXIS = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION);

    @Test
    void testPartitionedRegionBuilder_square() {
        // arrange
        final Parallelogram square = Parallelogram.unitSquare(TEST_PRECISION);
        final List<LineConvexSubset> boundaries = square.getBoundaries();

        final Vector2D lowerBound = Vector2D.of(-2, -2);

        final int maxUpper = 5;
        final int maxLevel = 4;

        // act/assert
        Bounds2D bounds;
        for (int u = 0; u <= maxUpper; ++u) {
            for (int level = 0; level <= maxLevel; ++level) {
                bounds = Bounds2D.from(lowerBound, Vector2D.of(u, u));

                checkFinitePartitionedRegion(bounds, level, square);
                checkFinitePartitionedRegion(bounds, level, boundaries);
            }
        }
    }

    @Test
    void testPartitionedRegionBuilder_nonConvex() {
        // arrange
        final RegionBSPTree2D src = Parallelogram.unitSquare(TEST_PRECISION).toTree();
        src.union(Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION).toTree());

        final List<LineConvexSubset> boundaries = src.getBoundaries();

        final Vector2D lowerBound = Vector2D.of(-2, -2);

        final int maxUpper = 5;
        final int maxLevel = 4;

        // act/assert
        Bounds2D bounds;
        for (int u = 0; u <= maxUpper; ++u) {
            for (int level = 0; level <= maxLevel; ++level) {
                bounds = Bounds2D.from(lowerBound, Vector2D.of(u, u));

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
    private void checkFinitePartitionedRegion(final Bounds2D bounds, final int level, final BoundarySource2D src) {
        // arrange
        final String msg = "Partitioned region check failed with bounds= " + bounds + " and level= " + level;

        final RegionBSPTree2D standard = RegionBSPTree2D.from(src.boundaryStream().collect(Collectors.toList()));

        // act
        final RegionBSPTree2D partitioned = RegionBSPTree2D.partitionedRegionBuilder()
                .insertAxisAlignedGrid(bounds, level, TEST_PRECISION)
                .insertBoundaries(src)
                .build();

        // assert
        Assertions.assertEquals(standard.getSize(), partitioned.getSize(), TEST_EPS, msg);
        Assertions.assertEquals(standard.getBoundarySize(), partitioned.getBoundarySize(), TEST_EPS, msg);
        EuclideanTestUtils.assertCoordinatesEqual(standard.getCentroid(), partitioned.getCentroid(), TEST_EPS);

        final RegionBSPTree2D diff = RegionBSPTree2D.empty();
        diff.difference(partitioned, standard);
        Assertions.assertTrue(diff.isEmpty(), msg);
    }

    /** Check that a partitioned BSP tree behaves the same as a non-partitioned tree when
     * constructed with the given boundaries.
     * @param bounds
     * @param level
     * @param boundaries
     */
    private void checkFinitePartitionedRegion(final Bounds2D bounds, final int level,
                                              final List<? extends LineConvexSubset> boundaries) {
        // arrange
        final String msg = "Partitioned region check failed with bounds= " + bounds + " and level= " + level;

        final RegionBSPTree2D standard = RegionBSPTree2D.from(boundaries);

        // act
        final RegionBSPTree2D partitioned = RegionBSPTree2D.partitionedRegionBuilder()
                .insertAxisAlignedGrid(bounds, level, TEST_PRECISION)
                .insertBoundaries(boundaries)
                .build();

        // assert
        Assertions.assertEquals(standard.getSize(), partitioned.getSize(), TEST_EPS, msg);
        Assertions.assertEquals(standard.getBoundarySize(), partitioned.getBoundarySize(), TEST_EPS, msg);
        EuclideanTestUtils.assertCoordinatesEqual(standard.getCentroid(), partitioned.getCentroid(), TEST_EPS);

        final RegionBSPTree2D diff = RegionBSPTree2D.empty();
        diff.difference(partitioned, standard);
        Assertions.assertTrue(diff.isEmpty(), msg);
    }

    @Test
    void testPartitionedRegionBuilder_insertPartitionAfterBoundary() {
        // arrange
        final PartitionedRegionBuilder2D builder = RegionBSPTree2D.partitionedRegionBuilder();
        builder.insertBoundary(Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION));

        final Line partition = Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION);

        final String msg = "Cannot insert partitions after boundaries have been inserted";

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.insertPartition(partition);
        }, IllegalStateException.class, msg);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.insertPartition(partition.span());
        }, IllegalStateException.class, msg);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.insertAxisAlignedPartitions(Vector2D.ZERO, TEST_PRECISION);
        }, IllegalStateException.class, msg);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.insertAxisAlignedGrid(Bounds2D.from(Vector2D.ZERO, Vector2D.of(1, 1)), 1, TEST_PRECISION);
        }, IllegalStateException.class, msg);
    }

    @Test
    void testGetBounds_hasBounds() {
        // arrange
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.of(2, 3), Vector2D.of(5, 8), TEST_PRECISION)
                .toTree();

        // act
        final Bounds2D bounds = tree.getBounds();

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2, 3), bounds.getMin(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(5, 8), bounds.getMax(), TEST_EPS);
    }

    @Test
    void testGetNodeRegion() {
        // arrange
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        final RegionNode2D root = tree.getRoot();
        root.cut(Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION));

        final RegionNode2D minus = root.getMinus();
        minus.cut(Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION));

        final Vector2D origin = Vector2D.ZERO;

        final Vector2D a = Vector2D.of(1, 0);
        final Vector2D b = Vector2D.of(1, 1);
        final Vector2D c = Vector2D.of(0, 1);
        final Vector2D d = Vector2D.of(-1, 1);
        final Vector2D e = Vector2D.of(-1, 0);
        final Vector2D f = Vector2D.of(-1, -1);
        final Vector2D g = Vector2D.of(0, -1);
        final Vector2D h = Vector2D.of(1, -1);

        // act/assert
        checkConvexArea(root.getNodeRegion(), Arrays.asList(origin, a, b, c, d, e, f, g, h), Collections.emptyList());

        checkConvexArea(minus.getNodeRegion(), Arrays.asList(b, c, d), Arrays.asList(f, g, h));
        checkConvexArea(root.getPlus().getNodeRegion(), Arrays.asList(f, g, h), Arrays.asList(b, c, d));

        checkConvexArea(minus.getMinus().getNodeRegion(), Collections.singletonList(d), Arrays.asList(a, b, f, g, h));
        checkConvexArea(minus.getPlus().getNodeRegion(), Collections.singletonList(b), Arrays.asList(d, e, f, g, h));
    }

    @Test
    void testProject_halfSpace() {
        // arrange
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO, tree.project(Vector2D.ZERO), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-1, 0), tree.project(Vector2D.of(-1, 0)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2, 0),
                tree.project(Vector2D.of(2, -1)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-3, 0),
                tree.project(Vector2D.of(-3, 1)), TEST_EPS);
    }

    @Test
    void testProject_rect() {
        // arrange
        final RegionBSPTree2D tree = Parallelogram.axisAligned(
                    Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).toTree();

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 1), tree.project(Vector2D.ZERO), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 1), tree.project(Vector2D.of(1, 0)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1.5, 1), tree.project(Vector2D.of(1.5, 0)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2, 1), tree.project(Vector2D.of(2, 0)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2, 1), tree.project(Vector2D.of(3, 0)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 2), tree.project(Vector2D.of(1, 3)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 2), tree.project(Vector2D.of(1, 3)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1.5, 2), tree.project(Vector2D.of(1.5, 3)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2, 2), tree.project(Vector2D.of(2, 3)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2, 2), tree.project(Vector2D.of(3, 3)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 1.5), tree.project(Vector2D.of(0, 1.5)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 1.5), tree.project(Vector2D.of(1.5, 1.5)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2, 1.5), tree.project(Vector2D.of(3, 1.5)), TEST_EPS);
    }

    @Test
    void testLinecast_empty() {
        // arrange
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        // act/assert
        LinecastChecker2D.with(tree)
            .expectNothing()
            .whenGiven(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));

        LinecastChecker2D.with(tree)
            .expectNothing()
            .whenGiven(Lines.segmentFromPoints(Vector2D.Unit.MINUS_X, Vector2D.Unit.PLUS_X, TEST_PRECISION));
    }

    @Test
    void testLinecast_full() {
        // arrange
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        // act/assert
        LinecastChecker2D.with(tree)
            .expectNothing()
            .whenGiven(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));

        LinecastChecker2D.with(tree)
            .expectNothing()
            .whenGiven(Lines.segmentFromPoints(Vector2D.Unit.MINUS_X, Vector2D.Unit.PLUS_X, TEST_PRECISION));
    }

    @Test
    void testLinecast() {
        // arrange
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION)
                .toTree();

        // act/assert
        LinecastChecker2D.with(tree)
            .expectNothing()
            .whenGiven(Lines.fromPoints(Vector2D.of(0, 5), Vector2D.of(1, 6), TEST_PRECISION));

        LinecastChecker2D.with(tree)
            .expect(Vector2D.ZERO, Vector2D.Unit.MINUS_X)
            .and(Vector2D.ZERO, Vector2D.Unit.MINUS_Y)
            .and(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y)
            .and(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X)
            .whenGiven(Lines.fromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION));

        LinecastChecker2D.with(tree)
            .expect(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y)
            .and(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X)
            .whenGiven(Lines.segmentFromPoints(Vector2D.of(0.5, 0.5), Vector2D.of(1, 1), TEST_PRECISION));
    }

    @Test
    void testLinecast_complementedTree() {
        // arrange
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION)
                .toTree();

        tree.complement();

        // act/assert
        LinecastChecker2D.with(tree)
            .expectNothing()
            .whenGiven(Lines.fromPoints(Vector2D.of(0, 5), Vector2D.of(1, 6), TEST_PRECISION));

        LinecastChecker2D.with(tree)
            .expect(Vector2D.ZERO, Vector2D.Unit.PLUS_Y)
            .and(Vector2D.ZERO, Vector2D.Unit.PLUS_X)
            .and(Vector2D.of(1, 1), Vector2D.Unit.MINUS_X)
            .and(Vector2D.of(1, 1), Vector2D.Unit.MINUS_Y)
            .whenGiven(Lines.fromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION));

        LinecastChecker2D.with(tree)
            .expect(Vector2D.of(1, 1), Vector2D.Unit.MINUS_X)
            .and(Vector2D.of(1, 1), Vector2D.Unit.MINUS_Y)
            .whenGiven(Lines.segmentFromPoints(Vector2D.of(0.5, 0.5), Vector2D.of(1, 1), TEST_PRECISION));
    }

    @Test
    void testLinecast_complexRegion() {
        // arrange
        final RegionBSPTree2D a = LinePath.fromVertexLoop(Arrays.asList(
                    Vector2D.ZERO, Vector2D.of(0, 1),
                    Vector2D.of(0.5, 1), Vector2D.of(0.5, 0)
                ), TEST_PRECISION).toTree();
        a.complement();

        final RegionBSPTree2D b = LinePath.fromVertexLoop(Arrays.asList(
                Vector2D.of(0.5, 0), Vector2D.of(0.5, 1),
                Vector2D.of(1, 1), Vector2D.of(1, 0)
            ), TEST_PRECISION).toTree();
        b.complement();

        final RegionBSPTree2D c = LinePath.fromVertexLoop(Arrays.asList(
                Vector2D.of(0.5, 0.5), Vector2D.of(1.5, 0.5),
                Vector2D.of(1.5, 1.5), Vector2D.of(0.5, 1.5)
            ), TEST_PRECISION).toTree();

        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.union(a, b);
        tree.union(c);

        // act/assert
        LinecastChecker2D.with(tree)
            .expect(Vector2D.of(1.5, 1.5), Vector2D.Unit.PLUS_Y)
            .and(Vector2D.of(1.5, 1.5), Vector2D.Unit.PLUS_X)
            .whenGiven(Lines.segmentFromPoints(Vector2D.of(0.25, 0.25), Vector2D.of(2, 2), TEST_PRECISION));
    }

    @Test
    void testLinecast_removesDuplicatePoints() {
        // arrange
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION).span());
        tree.insert(Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION).span());

        // act/assert
        LinecastChecker2D.with(tree)
            .expect(Vector2D.ZERO, Vector2D.Unit.MINUS_Y)
            .whenGiven(Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(-1, -1), TEST_PRECISION));

        LinecastChecker2D.with(tree)
            .expect(Vector2D.ZERO, Vector2D.Unit.MINUS_Y)
            .whenGiven(Lines.segmentFromPoints(Vector2D.of(1, 1), Vector2D.of(-1, -1), TEST_PRECISION));
    }

    private static void assertSegmentsEqual(final LineConvexSubset expected, final LineConvexSubset actual) {
        Assertions.assertEquals(expected.getLine(), actual.getLine());

        final Vector2D expectedStart = expected.getStartPoint();
        final Vector2D expectedEnd = expected.getEndPoint();

        if (expectedStart != null) {
            EuclideanTestUtils.assertCoordinatesEqual(expectedStart, actual.getStartPoint(), TEST_EPS);
        } else {
            Assertions.assertNull(actual.getStartPoint());
        }

        if (expectedEnd != null) {
            EuclideanTestUtils.assertCoordinatesEqual(expectedEnd, actual.getEndPoint(), TEST_EPS);
        } else {
            Assertions.assertNull(actual.getEndPoint());
        }
    }

    private static void checkFiniteSegment(final LineConvexSubset segment, final Vector2D start, final Vector2D end) {
        EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    private static void checkClassify(final Region<Vector2D> region, final RegionLocation loc, final Vector2D... points) {
        for (final Vector2D point : points) {
            final String msg = "Unexpected location for point " + point;

            Assertions.assertEquals(loc, region.classify(point), msg);
        }
    }

    private static void checkConvexArea(final ConvexArea area, final List<Vector2D> inside, final List<Vector2D> outside) {
        checkClassify(area, RegionLocation.INSIDE, inside.toArray(new Vector2D[0]));
        checkClassify(area, RegionLocation.OUTSIDE, outside.toArray(new Vector2D[0]));
    }

    /** Assert that the given path is finite and contains the given vertices.
     * @param path
     * @param vertices
     */
    private static void checkVertices(final LinePath path, final Vector2D... vertices) {
        Assertions.assertTrue(path.isFinite(), "Line segment path is not finite");

        final List<Vector2D> actual = path.getVertexSequence();

        Assertions.assertEquals(vertices.length, actual.size(), "Vertex lists have different lengths");

        for (int i  = 0; i < vertices.length; ++i) {
            EuclideanTestUtils.assertCoordinatesEqual(vertices[i], actual.get(i), TEST_EPS);
        }
    }

    @Test
    void testGetBoundaryPaths_isUnmodifiable_1_oe() {
        // arrange
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));

        // act/assert
        try {
    tree.getBoundaryPaths().add(LinePath.builder(null).build());
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

}
