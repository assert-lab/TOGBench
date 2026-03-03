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

import static org.junit.jupiter.api.Assertions.fail;

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
    void testCtor_booleanArg_true_1_oe() {
        final RegionBSPTree2D tree = new RegionBSPTree2D(true);

        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testCtor_booleanArg_true_2_oe() {
        final RegionBSPTree2D tree = new RegionBSPTree2D(true);

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testCtor_booleanArg_true_3_oe() {
        final RegionBSPTree2D tree = new RegionBSPTree2D(true);

        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testCtor_booleanArg_false_1_oe() {
        final RegionBSPTree2D tree = new RegionBSPTree2D(false);

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testCtor_booleanArg_false_2_oe() {
        final RegionBSPTree2D tree = new RegionBSPTree2D(false);

        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testCtor_booleanArg_false_3_oe() {
        final RegionBSPTree2D tree = new RegionBSPTree2D(false);

        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testCtor_default_1_oe() {
        final RegionBSPTree2D tree = new RegionBSPTree2D();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testCtor_default_2_oe() {
        final RegionBSPTree2D tree = new RegionBSPTree2D();

        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testCtor_default_3_oe() {
        final RegionBSPTree2D tree = new RegionBSPTree2D();

        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testFull_factoryMethod_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testFull_factoryMethod_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testFull_factoryMethod_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testEmpty_factoryMethod_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testEmpty_factoryMethod_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testEmpty_factoryMethod_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testPartitionedRegionBuilder_halfSpace_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.partitionedRegionBuilder()
                .insertPartition(
                    Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION))
                .insertBoundary(
                    Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.MINUS_X, TEST_PRECISION).span())
                .build();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testPartitionedRegionBuilder_halfSpace_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.partitionedRegionBuilder()
                .insertPartition(
                    Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION))
                .insertBoundary(
                    Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.MINUS_X, TEST_PRECISION).span())
                .build();

        Assertions.assertTrue(tree.isInfinite());
    }

    @Test
    void testCopy_1_oe() {
        final RegionBSPTree2D tree = new RegionBSPTree2D(true);
        tree.getRoot().cut(Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION));

        final RegionBSPTree2D copy = tree.copy();

        Assertions.assertNotSame(tree, copy);
    }

    @Test
    void testCopy_2_oe() {
        final RegionBSPTree2D tree = new RegionBSPTree2D(true);
        tree.getRoot().cut(Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION));

        final RegionBSPTree2D copy = tree.copy();

        Assertions.assertEquals(3, copy.count());
    }

    @Test
    void testBoundaries_1_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION)
                .toTree();

        final List<LineConvexSubset> segments = new ArrayList<>();
        tree.boundaries().forEach(segments::add);

        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testGetBoundaries_1_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION)
                .toTree();

        final List<LineConvexSubset> segments = tree.getBoundaries();

        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testBoundaryStream_1_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION)
                .toTree();

        final List<LineConvexSubset> segments = tree.boundaryStream().collect(Collectors.toList());

        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testBoundaryStream_noBoundaries_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        final List<LineConvexSubset> segments = tree.boundaryStream().collect(Collectors.toList());

        Assertions.assertEquals(0, segments.size());
    }

    @Test
    void testGetBounds_noBounds_1_oe() {
        Assertions.assertNull(RegionBSPTree2D.empty().getBounds());
    }

    @Test
    void testGetBounds_noBounds_2_oe() {
        Assertions.assertNull(RegionBSPTree2D.full().getBounds());
    }

    @Test
    void testGetBounds_noBounds_3_oe() {

        final RegionBSPTree2D halfFull = RegionBSPTree2D.empty();
        halfFull.getRoot().insertCut(Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION));
        Assertions.assertNull(halfFull.getBounds());
    }

    @Test
    void testGetBoundaryPaths_cachesResult_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));

        final List<LinePath> a = tree.getBoundaryPaths();
        final List<LinePath> b = tree.getBoundaryPaths();

        Assertions.assertSame(a, b);
    }

    @Test
    void testGetBoundaryPaths_recomputesResultOnChange_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));

        final List<LinePath> a = tree.getBoundaryPaths();
        tree.insert(Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION));
        final List<LinePath> b = tree.getBoundaryPaths();

        Assertions.assertNotSame(a, b);
    }

    @Test
    void testGetBoundaryPaths_isUnmodifiable_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));

        try {
    tree.getBoundaryPaths().add(LinePath.builder(null).build());
    fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    void testAdd_convexArea_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        tree.add(ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO, Vector2D.of(2, 0),
                    Vector2D.of(2, 2), Vector2D.of(0, 2)
                ), TEST_PRECISION));
        tree.add(ConvexArea.convexPolygonFromVertices(Arrays.asList(
                Vector2D.of(1, 1), Vector2D.of(3, 1),
                Vector2D.of(3, 3), Vector2D.of(1, 3)
            ), TEST_PRECISION));

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testAdd_convexArea_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        tree.add(ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO, Vector2D.of(2, 0),
                    Vector2D.of(2, 2), Vector2D.of(0, 2)
                ), TEST_PRECISION));
        tree.add(ConvexArea.convexPolygonFromVertices(Arrays.asList(
                Vector2D.of(1, 1), Vector2D.of(3, 1),
                Vector2D.of(3, 3), Vector2D.of(1, 3)
            ), TEST_PRECISION));

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testAdd_convexArea_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        tree.add(ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO, Vector2D.of(2, 0),
                    Vector2D.of(2, 2), Vector2D.of(0, 2)
                ), TEST_PRECISION));
        tree.add(ConvexArea.convexPolygonFromVertices(Arrays.asList(
                Vector2D.of(1, 1), Vector2D.of(3, 1),
                Vector2D.of(3, 3), Vector2D.of(1, 3)
            ), TEST_PRECISION));


        Assertions.assertEquals(7, tree.getSize(), TEST_EPS);
    }

    @Test
    void testAdd_convexArea_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        tree.add(ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO, Vector2D.of(2, 0),
                    Vector2D.of(2, 2), Vector2D.of(0, 2)
                ), TEST_PRECISION));
        tree.add(ConvexArea.convexPolygonFromVertices(Arrays.asList(
                Vector2D.of(1, 1), Vector2D.of(3, 1),
                Vector2D.of(3, 3), Vector2D.of(1, 3)
            ), TEST_PRECISION));


        Assertions.assertEquals(12, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testToConvex_full_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        final List<ConvexArea> result = tree.toConvex();

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testToConvex_full_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        final List<ConvexArea> result = tree.toConvex();

        Assertions.assertTrue(result.get(0).isFull());
    }

    @Test
    void testToConvex_empty_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        final List<ConvexArea> result = tree.toConvex();

        Assertions.assertEquals(0, result.size());
    }

    @Test
    void testToConvex_halfSpace_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().insertCut(Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION));

        final List<ConvexArea> result = tree.toConvex();

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testToConvex_halfSpace_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().insertCut(Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION));

        final List<ConvexArea> result = tree.toConvex();


        final ConvexArea area = result.get(0);
        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testToConvex_halfSpace_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().insertCut(Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION));

        final List<ConvexArea> result = tree.toConvex();


        final ConvexArea area = result.get(0);
        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testToConvex_quadrantComplement_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION))
            .getPlus().cut(Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION));

        tree.complement();

        final List<ConvexArea> result = tree.toConvex();

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testToConvex_quadrantComplement_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION))
            .getPlus().cut(Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION));

        tree.complement();

        final List<ConvexArea> result = tree.toConvex();


        final ConvexArea area = result.get(0);
        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testToConvex_quadrantComplement_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION))
            .getPlus().cut(Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION));

        tree.complement();

        final List<ConvexArea> result = tree.toConvex();


        final ConvexArea area = result.get(0);
        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testToConvex_square_1_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION).toTree();

        final List<ConvexArea> result = tree.toConvex();

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testToConvex_square_2_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION).toTree();

        final List<ConvexArea> result = tree.toConvex();


        final ConvexArea area = result.get(0);
        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testToConvex_square_3_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION).toTree();

        final List<ConvexArea> result = tree.toConvex();


        final ConvexArea area = result.get(0);
        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testToConvex_square_4_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION).toTree();

        final List<ConvexArea> result = tree.toConvex();


        final ConvexArea area = result.get(0);

        Assertions.assertEquals(1, area.getSize(), TEST_EPS);
    }

    @Test
    void testToConvex_multipleConvexAreas_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(Arrays.asList(
                    Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION),

                    Lines.segmentFromPoints(Vector2D.of(1, 1), Vector2D.of(0, 1), TEST_PRECISION),
                    Lines.segmentFromPoints(Vector2D.of(0, 1), Vector2D.ZERO, TEST_PRECISION),

                    Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION),
                    Lines.segmentFromPoints(Vector2D.of(1, 0), Vector2D.of(1, 1), TEST_PRECISION)
                ));

        final List<ConvexArea> result = tree.toConvex();

        result.sort((a, b) ->
                Vector2D.COORDINATE_ASCENDING_ORDER.compare(a.getCentroid(), b.getCentroid()));

        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testToConvex_multipleConvexAreas_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(Arrays.asList(
                    Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION),

                    Lines.segmentFromPoints(Vector2D.of(1, 1), Vector2D.of(0, 1), TEST_PRECISION),
                    Lines.segmentFromPoints(Vector2D.of(0, 1), Vector2D.ZERO, TEST_PRECISION),

                    Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION),
                    Lines.segmentFromPoints(Vector2D.of(1, 0), Vector2D.of(1, 1), TEST_PRECISION)
                ));

        final List<ConvexArea> result = tree.toConvex();

        result.sort((a, b) ->
                Vector2D.COORDINATE_ASCENDING_ORDER.compare(a.getCentroid(), b.getCentroid()));


        final ConvexArea firstArea = result.get(0);
        Assertions.assertFalse(firstArea.isFull());
    }

    @Test
    void testToConvex_multipleConvexAreas_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(Arrays.asList(
                    Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION),

                    Lines.segmentFromPoints(Vector2D.of(1, 1), Vector2D.of(0, 1), TEST_PRECISION),
                    Lines.segmentFromPoints(Vector2D.of(0, 1), Vector2D.ZERO, TEST_PRECISION),

                    Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION),
                    Lines.segmentFromPoints(Vector2D.of(1, 0), Vector2D.of(1, 1), TEST_PRECISION)
                ));

        final List<ConvexArea> result = tree.toConvex();

        result.sort((a, b) ->
                Vector2D.COORDINATE_ASCENDING_ORDER.compare(a.getCentroid(), b.getCentroid()));


        final ConvexArea firstArea = result.get(0);
        Assertions.assertFalse(firstArea.isEmpty());
    }

    @Test
    void testToConvex_multipleConvexAreas_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(Arrays.asList(
                    Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION),

                    Lines.segmentFromPoints(Vector2D.of(1, 1), Vector2D.of(0, 1), TEST_PRECISION),
                    Lines.segmentFromPoints(Vector2D.of(0, 1), Vector2D.ZERO, TEST_PRECISION),

                    Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION),
                    Lines.segmentFromPoints(Vector2D.of(1, 0), Vector2D.of(1, 1), TEST_PRECISION)
                ));

        final List<ConvexArea> result = tree.toConvex();

        result.sort((a, b) ->
                Vector2D.COORDINATE_ASCENDING_ORDER.compare(a.getCentroid(), b.getCentroid()));


        final ConvexArea firstArea = result.get(0);

        Assertions.assertEquals(0.5, firstArea.getSize(), TEST_EPS);
    }

    @Test
    void testToConvex_multipleConvexAreas_6_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(Arrays.asList(
                    Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION),

                    Lines.segmentFromPoints(Vector2D.of(1, 1), Vector2D.of(0, 1), TEST_PRECISION),
                    Lines.segmentFromPoints(Vector2D.of(0, 1), Vector2D.ZERO, TEST_PRECISION),

                    Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION),
                    Lines.segmentFromPoints(Vector2D.of(1, 0), Vector2D.of(1, 1), TEST_PRECISION)
                ));

        final List<ConvexArea> result = tree.toConvex();

        result.sort((a, b) ->
                Vector2D.COORDINATE_ASCENDING_ORDER.compare(a.getCentroid(), b.getCentroid()));


        final ConvexArea firstArea = result.get(0);


        checkClassify(firstArea, RegionLocation.INSIDE, Vector2D.of(1.0 / 3.0, 2.0 / 3.0));
        checkClassify(firstArea, RegionLocation.BOUNDARY, Vector2D.ZERO, Vector2D.of(1, 1), Vector2D.of(0.5, 0.5));
        checkClassify(firstArea, RegionLocation.OUTSIDE,
                Vector2D.of(0.25, -1), Vector2D.of(0.25, 2),
                Vector2D.of(-1, 0.5), Vector2D.of(0.75, 0.5));

        final ConvexArea secondArea = result.get(1);
        Assertions.assertFalse(secondArea.isFull());
    }

    @Test
    void testToConvex_multipleConvexAreas_7_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(Arrays.asList(
                    Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION),

                    Lines.segmentFromPoints(Vector2D.of(1, 1), Vector2D.of(0, 1), TEST_PRECISION),
                    Lines.segmentFromPoints(Vector2D.of(0, 1), Vector2D.ZERO, TEST_PRECISION),

                    Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION),
                    Lines.segmentFromPoints(Vector2D.of(1, 0), Vector2D.of(1, 1), TEST_PRECISION)
                ));

        final List<ConvexArea> result = tree.toConvex();

        result.sort((a, b) ->
                Vector2D.COORDINATE_ASCENDING_ORDER.compare(a.getCentroid(), b.getCentroid()));


        final ConvexArea firstArea = result.get(0);


        checkClassify(firstArea, RegionLocation.INSIDE, Vector2D.of(1.0 / 3.0, 2.0 / 3.0));
        checkClassify(firstArea, RegionLocation.BOUNDARY, Vector2D.ZERO, Vector2D.of(1, 1), Vector2D.of(0.5, 0.5));
        checkClassify(firstArea, RegionLocation.OUTSIDE,
                Vector2D.of(0.25, -1), Vector2D.of(0.25, 2),
                Vector2D.of(-1, 0.5), Vector2D.of(0.75, 0.5));

        final ConvexArea secondArea = result.get(1);
        Assertions.assertFalse(secondArea.isEmpty());
    }

    @Test
    void testToConvex_multipleConvexAreas_8_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(Arrays.asList(
                    Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION),

                    Lines.segmentFromPoints(Vector2D.of(1, 1), Vector2D.of(0, 1), TEST_PRECISION),
                    Lines.segmentFromPoints(Vector2D.of(0, 1), Vector2D.ZERO, TEST_PRECISION),

                    Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION),
                    Lines.segmentFromPoints(Vector2D.of(1, 0), Vector2D.of(1, 1), TEST_PRECISION)
                ));

        final List<ConvexArea> result = tree.toConvex();

        result.sort((a, b) ->
                Vector2D.COORDINATE_ASCENDING_ORDER.compare(a.getCentroid(), b.getCentroid()));


        final ConvexArea firstArea = result.get(0);


        checkClassify(firstArea, RegionLocation.INSIDE, Vector2D.of(1.0 / 3.0, 2.0 / 3.0));
        checkClassify(firstArea, RegionLocation.BOUNDARY, Vector2D.ZERO, Vector2D.of(1, 1), Vector2D.of(0.5, 0.5));
        checkClassify(firstArea, RegionLocation.OUTSIDE,
                Vector2D.of(0.25, -1), Vector2D.of(0.25, 2),
                Vector2D.of(-1, 0.5), Vector2D.of(0.75, 0.5));

        final ConvexArea secondArea = result.get(1);

        Assertions.assertEquals(0.5, secondArea.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_full_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(1, 0), 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_full_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(1, 0), 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);


        checkClassify(split.getMinus(), RegionLocation.INSIDE, Vector2D.of(0, 1));
        checkClassify(split.getMinus(), RegionLocation.OUTSIDE, Vector2D.of(1, -1));

        final List<LinePath> minusBoundaryList = split.getMinus().getBoundaryPaths();
        Assertions.assertEquals(1, minusBoundaryList.size());
    }

    @Test
    void testSplit_full_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(1, 0), 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);


        checkClassify(split.getMinus(), RegionLocation.INSIDE, Vector2D.of(0, 1));
        checkClassify(split.getMinus(), RegionLocation.OUTSIDE, Vector2D.of(1, -1));

        final List<LinePath> minusBoundaryList = split.getMinus().getBoundaryPaths();

        final LinePath minusBoundary = minusBoundaryList.get(0);
        Assertions.assertEquals(1, minusBoundary.getElements().size());
    }

    @Test
    void testSplit_full_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(1, 0), 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);


        checkClassify(split.getMinus(), RegionLocation.INSIDE, Vector2D.of(0, 1));
        checkClassify(split.getMinus(), RegionLocation.OUTSIDE, Vector2D.of(1, -1));

        final List<LinePath> minusBoundaryList = split.getMinus().getBoundaryPaths();

        final LinePath minusBoundary = minusBoundaryList.get(0);
        Assertions.assertTrue(minusBoundary.isInfinite());
    }

    @Test
    void testSplit_full_5_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(1, 0), 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);


        checkClassify(split.getMinus(), RegionLocation.INSIDE, Vector2D.of(0, 1));
        checkClassify(split.getMinus(), RegionLocation.OUTSIDE, Vector2D.of(1, -1));

        final List<LinePath> minusBoundaryList = split.getMinus().getBoundaryPaths();

        final LinePath minusBoundary = minusBoundaryList.get(0);
        Assertions.assertSame(splitter, minusBoundary.getStart().getLine());
    }

    @Test
    void testSplit_full_6_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(1, 0), 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);


        checkClassify(split.getMinus(), RegionLocation.INSIDE, Vector2D.of(0, 1));
        checkClassify(split.getMinus(), RegionLocation.OUTSIDE, Vector2D.of(1, -1));

        final List<LinePath> minusBoundaryList = split.getMinus().getBoundaryPaths();

        final LinePath minusBoundary = minusBoundaryList.get(0);

        checkClassify(split.getPlus(), RegionLocation.OUTSIDE, Vector2D.of(0, 1));
        checkClassify(split.getPlus(), RegionLocation.INSIDE, Vector2D.of(1, -1));

        final List<LinePath> plusBoundaryList = split.getPlus().getBoundaryPaths();
        Assertions.assertEquals(1, plusBoundaryList.size());
    }

    @Test
    void testSplit_full_7_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(1, 0), 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);


        checkClassify(split.getMinus(), RegionLocation.INSIDE, Vector2D.of(0, 1));
        checkClassify(split.getMinus(), RegionLocation.OUTSIDE, Vector2D.of(1, -1));

        final List<LinePath> minusBoundaryList = split.getMinus().getBoundaryPaths();

        final LinePath minusBoundary = minusBoundaryList.get(0);

        checkClassify(split.getPlus(), RegionLocation.OUTSIDE, Vector2D.of(0, 1));
        checkClassify(split.getPlus(), RegionLocation.INSIDE, Vector2D.of(1, -1));

        final List<LinePath> plusBoundaryList = split.getPlus().getBoundaryPaths();

        final LinePath plusBoundary = minusBoundaryList.get(0);
        Assertions.assertEquals(1, plusBoundary.getElements().size());
    }

    @Test
    void testSplit_full_8_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(1, 0), 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);


        checkClassify(split.getMinus(), RegionLocation.INSIDE, Vector2D.of(0, 1));
        checkClassify(split.getMinus(), RegionLocation.OUTSIDE, Vector2D.of(1, -1));

        final List<LinePath> minusBoundaryList = split.getMinus().getBoundaryPaths();

        final LinePath minusBoundary = minusBoundaryList.get(0);

        checkClassify(split.getPlus(), RegionLocation.OUTSIDE, Vector2D.of(0, 1));
        checkClassify(split.getPlus(), RegionLocation.INSIDE, Vector2D.of(1, -1));

        final List<LinePath> plusBoundaryList = split.getPlus().getBoundaryPaths();

        final LinePath plusBoundary = minusBoundaryList.get(0);
        Assertions.assertTrue(plusBoundary.isInfinite());
    }

    @Test
    void testSplit_full_9_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(1, 0), 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);


        checkClassify(split.getMinus(), RegionLocation.INSIDE, Vector2D.of(0, 1));
        checkClassify(split.getMinus(), RegionLocation.OUTSIDE, Vector2D.of(1, -1));

        final List<LinePath> minusBoundaryList = split.getMinus().getBoundaryPaths();

        final LinePath minusBoundary = minusBoundaryList.get(0);

        checkClassify(split.getPlus(), RegionLocation.OUTSIDE, Vector2D.of(0, 1));
        checkClassify(split.getPlus(), RegionLocation.INSIDE, Vector2D.of(1, -1));

        final List<LinePath> plusBoundaryList = split.getPlus().getBoundaryPaths();

        final LinePath plusBoundary = minusBoundaryList.get(0);
        Assertions.assertSame(splitter, plusBoundary.getStart().getLine());
    }

    @Test
    void testSplit_empty_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(1, 0), 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);

        Assertions.assertEquals(SplitLocation.NEITHER, split.getLocation());
    }

    @Test
    void testSplit_empty_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(1, 0), 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);


        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_empty_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(1, 0), 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);


        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_bothSides_1_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(2, 1), TEST_PRECISION)
                .toTree();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_bothSides_2_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(2, 1), TEST_PRECISION)
                .toTree();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);


        final List<LinePath> minusPath = split.getMinus().getBoundaryPaths();
        Assertions.assertEquals(1, minusPath.size());
    }

    @Test
    void testSplit_bothSides_3_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(2, 1), TEST_PRECISION)
                .toTree();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);


        final List<LinePath> minusPath = split.getMinus().getBoundaryPaths();
        checkVertices(minusPath.get(0), Vector2D.ZERO, Vector2D.of(1, 1),
                Vector2D.of(0, 1), Vector2D.ZERO);

        final List<LinePath> plusPath = split.getPlus().getBoundaryPaths();
        Assertions.assertEquals(1, plusPath.size());
    }

    @Test
    void testSplit_plusSideOnly_1_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(2, 1), TEST_PRECISION)
                .toTree();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);

        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_plusSideOnly_2_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(2, 1), TEST_PRECISION)
                .toTree();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);


        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_plusSideOnly_3_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(2, 1), TEST_PRECISION)
                .toTree();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.25 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree2D> split = tree.split(splitter);



        final List<LinePath> plusPath = split.getPlus().getBoundaryPaths();
        Assertions.assertEquals(1, plusPath.size());
    }

    @Test
    void testSplit_minusSideOnly_1_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(2, 1), TEST_PRECISION)
                .toTree();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.25 * Math.PI, TEST_PRECISION)
                .reverse();

        final Split<RegionBSPTree2D> split = tree.split(splitter);

        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_minusSideOnly_2_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(2, 1), TEST_PRECISION)
                .toTree();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.25 * Math.PI, TEST_PRECISION)
                .reverse();

        final Split<RegionBSPTree2D> split = tree.split(splitter);


        final List<LinePath> minusPath = split.getMinus().getBoundaryPaths();
        Assertions.assertEquals(1, minusPath.size());
    }

    @Test
    void testSplit_minusSideOnly_3_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(2, 1), TEST_PRECISION)
                .toTree();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.25 * Math.PI, TEST_PRECISION)
                .reverse();

        final Split<RegionBSPTree2D> split = tree.split(splitter);


        final List<LinePath> minusPath = split.getMinus().getBoundaryPaths();
        checkVertices(minusPath.get(0), Vector2D.ZERO, Vector2D.of(2, 0),
                Vector2D.of(2, 1), Vector2D.of(0, 1), Vector2D.ZERO);

        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testGeometricProperties_full_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();

        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testGeometricProperties_full_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();


        Assertions.assertEquals(0, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_full_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();



        Assertions.assertEquals(0, tree.getBoundaries().size());
    }

    @Test
    void testGeometricProperties_full_5_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();



        Assertions.assertEquals(0, tree.getBoundaryPaths().size());
    }

    @Test
    void testGeometricProperties_empty_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        Assertions.assertEquals(0, tree.getSize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_empty_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testGeometricProperties_empty_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();


        Assertions.assertEquals(0, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_empty_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();



        Assertions.assertEquals(0, tree.getBoundaries().size());
    }

    @Test
    void testGeometricProperties_empty_5_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();



        Assertions.assertEquals(0, tree.getBoundaryPaths().size());
    }

    @Test
    void testGeometricProperties_halfSpace_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);

        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testGeometricProperties_halfSpace_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);



        final List<LineConvexSubset> segments = tree.getBoundaries();
        Assertions.assertEquals(1, segments.size());
    }

    @Test
    void testGeometricProperties_halfSpace_5_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);
        Assertions.assertSame(X_AXIS, segment.getLine());
    }

    @Test
    void testGeometricProperties_halfSpace_6_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);
        Assertions.assertNull(segment.getStartPoint());
    }

    @Test
    void testGeometricProperties_halfSpace_7_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);
        Assertions.assertNull(segment.getEndPoint());
    }

    @Test
    void testGeometricProperties_halfSpace_8_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);

        final List<LinePath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testGeometricProperties_halfSpace_9_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
        Assertions.assertEquals(1, path.getElements().size());
    }

    @Test
    void testGeometricProperties_complementedHalfSpace_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);

        tree.complement();

        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testGeometricProperties_complementedHalfSpace_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = tree.getBoundaries();
        Assertions.assertEquals(1, segments.size());
    }

    @Test
    void testGeometricProperties_complementedHalfSpace_5_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);
        Assertions.assertEquals(X_AXIS.reverse(), segment.getLine());
    }

    @Test
    void testGeometricProperties_complementedHalfSpace_6_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);
        Assertions.assertNull(segment.getStartPoint());
    }

    @Test
    void testGeometricProperties_complementedHalfSpace_7_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);
        Assertions.assertNull(segment.getEndPoint());
    }

    @Test
    void testGeometricProperties_complementedHalfSpace_8_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);

        final List<LinePath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testGeometricProperties_complementedHalfSpace_9_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
        Assertions.assertEquals(1, path.getElements().size());
    }

    @Test
    void testGeometricProperties_quadrant_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testGeometricProperties_quadrant_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());
        Assertions.assertEquals(2, segments.size());
    }

    @Test
    void testGeometricProperties_quadrant_6_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);
        Assertions.assertNull(firstSegment.getEndPoint());
    }

    @Test
    void testGeometricProperties_quadrant_7_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);
        Assertions.assertSame(Y_AXIS, firstSegment.getLine());
    }

    @Test
    void testGeometricProperties_quadrant_8_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);
        Assertions.assertNull(secondSegment.getStartPoint());
    }

    @Test
    void testGeometricProperties_quadrant_10_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);
        Assertions.assertSame(X_AXIS, secondSegment.getLine());
    }

    @Test
    void testGeometricProperties_quadrant_11_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testGeometricProperties_quadrant_12_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
        Assertions.assertEquals(2, path.getElements().size());
    }

    @Test
    void testGeometricProperties_mixedCutRule_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        tree.getRoot().cut(Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                RegionCutRule.INHERIT);

        tree.getRoot()
            .getPlus().cut(X_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus().cut(Lines.fromPointAndAngle(Vector2D.of(1, 0), 0.5 * Math.PI, TEST_PRECISION));

        tree.getRoot()
            .getMinus().cut(Lines.fromPointAndAngle(Vector2D.ZERO, 0.5 * Math.PI, TEST_PRECISION), RegionCutRule.PLUS_INSIDE)
                .getPlus().cut(Lines.fromPointAndAngle(Vector2D.of(1, 1), Math.PI, TEST_PRECISION))
                    .getMinus().cut(Lines.fromPointAndAngle(Vector2D.of(0.5, 0.5), 0.75 * Math.PI, TEST_PRECISION), RegionCutRule.INHERIT);

        Assertions.assertEquals(1, tree.getSize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_mixedCutRule_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        tree.getRoot().cut(Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                RegionCutRule.INHERIT);

        tree.getRoot()
            .getPlus().cut(X_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus().cut(Lines.fromPointAndAngle(Vector2D.of(1, 0), 0.5 * Math.PI, TEST_PRECISION));

        tree.getRoot()
            .getMinus().cut(Lines.fromPointAndAngle(Vector2D.ZERO, 0.5 * Math.PI, TEST_PRECISION), RegionCutRule.PLUS_INSIDE)
                .getPlus().cut(Lines.fromPointAndAngle(Vector2D.of(1, 1), Math.PI, TEST_PRECISION))
                    .getMinus().cut(Lines.fromPointAndAngle(Vector2D.of(0.5, 0.5), 0.75 * Math.PI, TEST_PRECISION), RegionCutRule.INHERIT);


        Assertions.assertEquals(4, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_mixedCutRule_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        tree.getRoot().cut(Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                RegionCutRule.INHERIT);

        tree.getRoot()
            .getPlus().cut(X_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus().cut(Lines.fromPointAndAngle(Vector2D.of(1, 0), 0.5 * Math.PI, TEST_PRECISION));

        tree.getRoot()
            .getMinus().cut(Lines.fromPointAndAngle(Vector2D.ZERO, 0.5 * Math.PI, TEST_PRECISION), RegionCutRule.PLUS_INSIDE)
                .getPlus().cut(Lines.fromPointAndAngle(Vector2D.of(1, 1), Math.PI, TEST_PRECISION))
                    .getMinus().cut(Lines.fromPointAndAngle(Vector2D.of(0.5, 0.5), 0.75 * Math.PI, TEST_PRECISION), RegionCutRule.INHERIT);



        final List<LinePath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testGeometricProperties_mixedCutRule_5_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        tree.getRoot().cut(Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                RegionCutRule.INHERIT);

        tree.getRoot()
            .getPlus().cut(X_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus().cut(Lines.fromPointAndAngle(Vector2D.of(1, 0), 0.5 * Math.PI, TEST_PRECISION));

        tree.getRoot()
            .getMinus().cut(Lines.fromPointAndAngle(Vector2D.ZERO, 0.5 * Math.PI, TEST_PRECISION), RegionCutRule.PLUS_INSIDE)
                .getPlus().cut(Lines.fromPointAndAngle(Vector2D.of(1, 1), Math.PI, TEST_PRECISION))
                    .getMinus().cut(Lines.fromPointAndAngle(Vector2D.of(0.5, 0.5), 0.75 * Math.PI, TEST_PRECISION), RegionCutRule.INHERIT);



        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
        Assertions.assertEquals(4, path.getElements().size());
    }

    @Test
    void testGeometricProperties_mixedCutRule_6_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();

        tree.getRoot().cut(Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                RegionCutRule.INHERIT);

        tree.getRoot()
            .getPlus().cut(X_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus().cut(Lines.fromPointAndAngle(Vector2D.of(1, 0), 0.5 * Math.PI, TEST_PRECISION));

        tree.getRoot()
            .getMinus().cut(Lines.fromPointAndAngle(Vector2D.ZERO, 0.5 * Math.PI, TEST_PRECISION), RegionCutRule.PLUS_INSIDE)
                .getPlus().cut(Lines.fromPointAndAngle(Vector2D.of(1, 1), Math.PI, TEST_PRECISION))
                    .getMinus().cut(Lines.fromPointAndAngle(Vector2D.of(0.5, 0.5), 0.75 * Math.PI, TEST_PRECISION), RegionCutRule.INHERIT);



        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);

        final List<Vector2D> vertices = path.getVertexSequence();
        Assertions.assertEquals(5, vertices.size());
    }

    @Test
    void testGeometricProperties_complementedQuadrant_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();

        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testGeometricProperties_complementedQuadrant_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());
        Assertions.assertEquals(2, segments.size());
    }

    @Test
    void testGeometricProperties_complementedQuadrant_6_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);
        Assertions.assertNull(firstSegment.getEndPoint());
    }

    @Test
    void testGeometricProperties_complementedQuadrant_7_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);
        Assertions.assertEquals(X_AXIS.reverse(), firstSegment.getLine());
    }

    @Test
    void testGeometricProperties_complementedQuadrant_8_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);
        Assertions.assertNull(secondSegment.getStartPoint());
    }

    @Test
    void testGeometricProperties_complementedQuadrant_10_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);
        Assertions.assertEquals(Y_AXIS.reverse(), secondSegment.getLine());
    }

    @Test
    void testGeometricProperties_complementedQuadrant_11_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testGeometricProperties_complementedQuadrant_12_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
        Assertions.assertEquals(2, path.getElements().size());
    }

    @Test
    void testGeometricProperties_closedRegion_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1))
                .close());

        Assertions.assertEquals(0.5, tree.getSize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_closedRegion_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1))
                .close());


        Assertions.assertEquals(1.0 + Math.sqrt(2) + Math.sqrt(5), tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_closedRegion_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1))
                .close());



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());
        segments.sort(SEGMENT_COMPARATOR);

        Assertions.assertEquals(3, segments.size());
    }

    @Test
    void testGeometricProperties_closedRegion_5_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1))
                .close());



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());
        segments.sort(SEGMENT_COMPARATOR);


        checkFiniteSegment(segments.get(0), Vector2D.ZERO, Vector2D.of(1, 0));
        checkFiniteSegment(segments.get(1), Vector2D.of(1, 0), Vector2D.of(2, 1));
        checkFiniteSegment(segments.get(2), Vector2D.of(2, 1), Vector2D.ZERO);

        final List<LinePath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testGeometricProperties_complementedClosedRegion_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1))
                .close());

        tree.complement();

        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testGeometricProperties_complementedClosedRegion_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1))
                .close());

        tree.complement();


        Assertions.assertEquals(1.0 + Math.sqrt(2) + Math.sqrt(5), tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_complementedClosedRegion_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1))
                .close());

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());
        segments.sort(SEGMENT_COMPARATOR);

        Assertions.assertEquals(3, segments.size());
    }

    @Test
    void testGeometricProperties_complementedClosedRegion_5_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.insert(LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1))
                .close());

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());
        segments.sort(SEGMENT_COMPARATOR);


        checkFiniteSegment(segments.get(0), Vector2D.ZERO, Vector2D.of(2, 1));
        checkFiniteSegment(segments.get(1), Vector2D.of(1, 0), Vector2D.ZERO);
        checkFiniteSegment(segments.get(2), Vector2D.of(2, 1), Vector2D.of(1, 0));

        final List<LinePath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testGeometricProperties_regionWithHole_1_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(3, 3), TEST_PRECISION)
                .toTree();
        final RegionBSPTree2D inner = Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION)
                .toTree();

        tree.difference(inner);

        Assertions.assertEquals(8, tree.getSize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_regionWithHole_3_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(3, 3), TEST_PRECISION)
                .toTree();
        final RegionBSPTree2D inner = Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION)
                .toTree();

        tree.difference(inner);


        Assertions.assertEquals(16, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_regionWithHole_4_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(3, 3), TEST_PRECISION)
                .toTree();
        final RegionBSPTree2D inner = Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION)
                .toTree();

        tree.difference(inner);



        final List<LinePath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(2, paths.size());
    }

    @Test
    void testGeometricProperties_complementedRegionWithHole_2_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(3, 3), TEST_PRECISION)
                .toTree();
        final RegionBSPTree2D inner = Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION)
                .toTree();

        tree.difference(inner);

        tree.complement();

        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testGeometricProperties_complementedRegionWithHole_3_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(3, 3), TEST_PRECISION)
                .toTree();
        final RegionBSPTree2D inner = Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION)
                .toTree();

        tree.difference(inner);

        tree.complement();


        Assertions.assertEquals(16, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_complementedRegionWithHole_4_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(3, 3), TEST_PRECISION)
                .toTree();
        final RegionBSPTree2D inner = Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION)
                .toTree();

        tree.difference(inner);

        tree.complement();



        final List<LinePath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(2, paths.size());
    }

    @Test
    void testFrom_boundaries_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.from(Arrays.asList(
                    Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION).span(),
                    Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION)
                        .rayFrom(Vector2D.ZERO)
                ));

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testFrom_boundaries_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.from(Arrays.asList(
                    Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION).span(),
                    Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION)
                        .rayFrom(Vector2D.ZERO)
                ));

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testFrom_boundaries_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.from(Arrays.asList(
                    Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION).span(),
                    Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION)
                        .rayFrom(Vector2D.ZERO)
                ));


        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getLocation());
    }

    @Test
    void testFrom_boundaries_fullIsTrue_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.from(Arrays.asList(
                    Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION).span(),
                    Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION)
                        .rayFrom(Vector2D.ZERO)
                ), true);

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testFrom_boundaries_fullIsTrue_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.from(Arrays.asList(
                    Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION).span(),
                    Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION)
                        .rayFrom(Vector2D.ZERO)
                ), true);

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testFrom_boundaries_fullIsTrue_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.from(Arrays.asList(
                    Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION).span(),
                    Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION)
                        .rayFrom(Vector2D.ZERO)
                ), true);


        Assertions.assertEquals(RegionLocation.INSIDE, tree.getRoot().getLocation());
    }

    @Test
    void testFrom_boundaries_noBoundaries_1_oe() {
        Assertions.assertTrue(RegionBSPTree2D.from(Collections.emptyList()).isEmpty());
    }

    @Test
    void testFrom_boundaries_noBoundaries_2_oe() {
        Assertions.assertTrue(RegionBSPTree2D.from(Collections.emptyList(), true).isFull());
    }

    @Test
    void testFrom_boundaries_noBoundaries_3_oe() {
        Assertions.assertTrue(RegionBSPTree2D.from(Collections.emptyList(), false).isEmpty());
    }

    @Test
    void testToList_1_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION).toTree();

        final BoundaryList2D list = tree.toList();

        Assertions.assertEquals(4, list.toList().count());
    }

    @Test
    void testToList_2_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION).toTree();

        final BoundaryList2D list = tree.toList();

        Assertions.assertEquals(1, list.toTree().getSize(), TEST_EPS);
    }

    @Test
    void testToList_fullAndEmpty_1_oe() {
        Assertions.assertEquals(0, RegionBSPTree2D.full().toList().count());
    }

    @Test
    void testToList_fullAndEmpty_2_oe() {
        Assertions.assertEquals(0, RegionBSPTree2D.empty().toList().count());
    }

    @Test
    void testToTree_returnsSameInstance_1_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 2), TEST_PRECISION).toTree();

        Assertions.assertSame(tree, tree.toTree());
    }

    @Test
    void testProject_fullAndEmpty_1_oe() {
        Assertions.assertNull(RegionBSPTree2D.full().project(Vector2D.ZERO));
    }

    @Test
    void testProject_fullAndEmpty_2_oe() {
        Assertions.assertNull(RegionBSPTree2D.empty().project(Vector2D.of(1, 2)));
    }

    @Test
    void testTransform_1_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(3, 2), TEST_PRECISION)
                .toTree();

        final AffineTransformMatrix2D transform = AffineTransformMatrix2D.createScale(0.5, 2)
                .rotate(Angle.PI_OVER_TWO)
                .translate(Vector2D.of(0, -1));

        tree.transform(transform);

        final List<LinePath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testTransform_2_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(3, 2), TEST_PRECISION)
                .toTree();

        final AffineTransformMatrix2D transform = AffineTransformMatrix2D.createScale(0.5, 2)
                .rotate(Angle.PI_OVER_TWO)
                .translate(Vector2D.of(0, -1));

        tree.transform(transform);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
        Assertions.assertEquals(4, path.getElements().size());
    }

    @Test
    void testTransform_halfSpace_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().insertCut(Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION));

        final AffineTransformMatrix2D transform = AffineTransformMatrix2D.createScale(0.5, 2)
                .rotate(Angle.PI_OVER_TWO)
                .translate(Vector2D.of(1, 0));

        tree.transform(transform);

        final List<LinePath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testTransform_halfSpace_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().insertCut(Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION));

        final AffineTransformMatrix2D transform = AffineTransformMatrix2D.createScale(0.5, 2)
                .rotate(Angle.PI_OVER_TWO)
                .translate(Vector2D.of(1, 0));

        tree.transform(transform);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
        Assertions.assertEquals(1, path.getElements().size());
    }

    @Test
    void testTransform_halfSpace_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().insertCut(Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION));

        final AffineTransformMatrix2D transform = AffineTransformMatrix2D.createScale(0.5, 2)
                .rotate(Angle.PI_OVER_TWO)
                .translate(Vector2D.of(1, 0));

        tree.transform(transform);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
        final LineConvexSubset segment = path.getStart();
        Assertions.assertNull(segment.getStartPoint());
    }

    @Test
    void testTransform_halfSpace_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().insertCut(Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION));

        final AffineTransformMatrix2D transform = AffineTransformMatrix2D.createScale(0.5, 2)
                .rotate(Angle.PI_OVER_TWO)
                .translate(Vector2D.of(1, 0));

        tree.transform(transform);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
        final LineConvexSubset segment = path.getStart();
        Assertions.assertNull(segment.getEndPoint());
    }

    @Test
    void testTransform_halfSpace_5_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().insertCut(Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION));

        final AffineTransformMatrix2D transform = AffineTransformMatrix2D.createScale(0.5, 2)
                .rotate(Angle.PI_OVER_TWO)
                .translate(Vector2D.of(1, 0));

        tree.transform(transform);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
        final LineConvexSubset segment = path.getStart();

        final Line expectedLine = Lines.fromPointAndAngle(Vector2D.of(-1, 0), Angle.PI_OVER_TWO, TEST_PRECISION);
        Assertions.assertTrue(expectedLine.eq(segment.getLine(), expectedLine.getPrecision()));
    }

    @Test
    void testTransform_fullAndEmpty_1_oe() {
        final RegionBSPTree2D full = RegionBSPTree2D.full();
        final RegionBSPTree2D empty = RegionBSPTree2D.empty();

        final AffineTransformMatrix2D transform = AffineTransformMatrix2D.createRotation(Angle.PI_OVER_TWO);

        full.transform(transform);
        empty.transform(transform);

        Assertions.assertTrue(full.isFull());
    }

    @Test
    void testTransform_fullAndEmpty_2_oe() {
        final RegionBSPTree2D full = RegionBSPTree2D.full();
        final RegionBSPTree2D empty = RegionBSPTree2D.empty();

        final AffineTransformMatrix2D transform = AffineTransformMatrix2D.createRotation(Angle.PI_OVER_TWO);

        full.transform(transform);
        empty.transform(transform);

        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    void testTransform_reflection_1_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).toTree();

        final AffineTransformMatrix2D transform = AffineTransformMatrix2D.from(v -> Vector2D.of(-v.getX(), v.getY()));

        tree.transform(transform);

        final List<LinePath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testTransform_reflection_2_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).toTree();

        final AffineTransformMatrix2D transform = AffineTransformMatrix2D.from(v -> Vector2D.of(-v.getX(), v.getY()));

        tree.transform(transform);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
        Assertions.assertEquals(4, path.getElements().size());
    }

    @Test
    void testTransform_doubleReflection_1_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(
                    Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).toTree();

        final AffineTransformMatrix2D transform = AffineTransformMatrix2D.from(Vector2D::negate);

        tree.transform(transform);

        final List<LinePath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testTransform_doubleReflection_2_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(
                    Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).toTree();

        final AffineTransformMatrix2D transform = AffineTransformMatrix2D.from(Vector2D::negate);

        tree.transform(transform);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
        Assertions.assertEquals(4, path.getElements().size());
    }

    @Test
    void testBooleanOperations_1_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(3, 3), TEST_PRECISION).toTree();
        RegionBSPTree2D temp;

        temp = Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).toTree();
        temp.complement();
        tree.intersection(temp);

        temp = Parallelogram.axisAligned(Vector2D.of(3, 0), Vector2D.of(6, 3), TEST_PRECISION).toTree();
        tree.union(temp);

        temp = Parallelogram.axisAligned(Vector2D.of(2, 1), Vector2D.of(5, 2), TEST_PRECISION).toTree();
        tree.difference(temp);

        temp.setFull();
        tree.xor(temp);

        final List<LinePath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(2, paths.size());
    }

    @Test
    void testGeometricProperties_halfSpace_10_oe_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = segment;
        final LineConvexSubset actual0 = path.getStart();
        Assertions.assertEquals(expected0.getLine(), actual0.getLine());
    }

    @Test
    void testGeometricProperties_halfSpace_10_oe_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = segment;
        final LineConvexSubset actual0 = path.getStart();
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                    EuclideanTestUtils.assertCoordinatesEqual(expectedStart0, actual0.getStartPoint(), TEST_EPS);
    }
    }

    @Test
    void testGeometricProperties_halfSpace_10_oe_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = segment;
        final LineConvexSubset actual0 = path.getStart();
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                    Assertions.assertNull(actual0.getStartPoint());
    }
    }

    @Test
    void testGeometricProperties_halfSpace_10_oe_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = segment;
        final LineConvexSubset actual0 = path.getStart();
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                }
        
                if (expectedEnd0 != null) {
                    EuclideanTestUtils.assertCoordinatesEqual(expectedEnd0, actual0.getEndPoint(), TEST_EPS);
    }
    }

    @Test
    void testGeometricProperties_halfSpace_10_oe_5_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = segment;
        final LineConvexSubset actual0 = path.getStart();
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                }
        
                if (expectedEnd0 != null) {
                } else {
                    Assertions.assertNull(actual0.getEndPoint());
    }
    }

    @Test
    void testGeometricProperties_complementedHalfSpace_10_oe_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = segment;
        final LineConvexSubset actual0 = path.getElements().get(0);
        Assertions.assertEquals(expected0.getLine(), actual0.getLine());
    }

    @Test
    void testGeometricProperties_complementedHalfSpace_10_oe_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = segment;
        final LineConvexSubset actual0 = path.getElements().get(0);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                    EuclideanTestUtils.assertCoordinatesEqual(expectedStart0, actual0.getStartPoint(), TEST_EPS);
    }
    }

    @Test
    void testGeometricProperties_complementedHalfSpace_10_oe_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = segment;
        final LineConvexSubset actual0 = path.getElements().get(0);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                    Assertions.assertNull(actual0.getStartPoint());
    }
    }

    @Test
    void testGeometricProperties_complementedHalfSpace_10_oe_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = segment;
        final LineConvexSubset actual0 = path.getElements().get(0);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                }
        
                if (expectedEnd0 != null) {
                    EuclideanTestUtils.assertCoordinatesEqual(expectedEnd0, actual0.getEndPoint(), TEST_EPS);
    }
    }

    @Test
    void testGeometricProperties_complementedHalfSpace_10_oe_5_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.full();
        tree.getRoot().cut(X_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = tree.getBoundaries();

        final LineConvexSubset segment = segments.get(0);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = segment;
        final LineConvexSubset actual0 = path.getElements().get(0);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                }
        
                if (expectedEnd0 != null) {
                } else {
                    Assertions.assertNull(actual0.getEndPoint());
    }
    }

    @Test
    void testGeometricProperties_quadrant_13_oe_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = secondSegment;
        final LineConvexSubset actual0 = path.getElements().get(0);
        Assertions.assertEquals(expected0.getLine(), actual0.getLine());
    }

    @Test
    void testGeometricProperties_quadrant_13_oe_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = secondSegment;
        final LineConvexSubset actual0 = path.getElements().get(0);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                    EuclideanTestUtils.assertCoordinatesEqual(expectedStart0, actual0.getStartPoint(), TEST_EPS);
    }
    }

    @Test
    void testGeometricProperties_quadrant_13_oe_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = secondSegment;
        final LineConvexSubset actual0 = path.getElements().get(0);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                    Assertions.assertNull(actual0.getStartPoint());
    }
    }

    @Test
    void testGeometricProperties_quadrant_13_oe_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = secondSegment;
        final LineConvexSubset actual0 = path.getElements().get(0);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                }
        
                if (expectedEnd0 != null) {
                    EuclideanTestUtils.assertCoordinatesEqual(expectedEnd0, actual0.getEndPoint(), TEST_EPS);
    }
    }

    @Test
    void testGeometricProperties_quadrant_13_oe_5_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = secondSegment;
        final LineConvexSubset actual0 = path.getElements().get(0);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                }
        
                if (expectedEnd0 != null) {
                } else {
                    Assertions.assertNull(actual0.getEndPoint());
    }
    }

    @Test
    void testGeometricProperties_quadrant_14_oe_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = firstSegment;
        final LineConvexSubset actual0 = path.getElements().get(1);
        Assertions.assertEquals(expected0.getLine(), actual0.getLine());
    }

    @Test
    void testGeometricProperties_quadrant_14_oe_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = firstSegment;
        final LineConvexSubset actual0 = path.getElements().get(1);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                    EuclideanTestUtils.assertCoordinatesEqual(expectedStart0, actual0.getStartPoint(), TEST_EPS);
    }
    }

    @Test
    void testGeometricProperties_quadrant_14_oe_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = firstSegment;
        final LineConvexSubset actual0 = path.getElements().get(1);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                    Assertions.assertNull(actual0.getStartPoint());
    }
    }

    @Test
    void testGeometricProperties_quadrant_14_oe_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = firstSegment;
        final LineConvexSubset actual0 = path.getElements().get(1);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                }
        
                if (expectedEnd0 != null) {
                    EuclideanTestUtils.assertCoordinatesEqual(expectedEnd0, actual0.getEndPoint(), TEST_EPS);
    }
    }

    @Test
    void testGeometricProperties_quadrant_14_oe_5_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = firstSegment;
        final LineConvexSubset actual0 = path.getElements().get(1);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                }
        
                if (expectedEnd0 != null) {
                } else {
                    Assertions.assertNull(actual0.getEndPoint());
    }
    }

    @Test
    void testGeometricProperties_complementedQuadrant_13_oe_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = secondSegment;
        final LineConvexSubset actual0 = path.getElements().get(0);
        Assertions.assertEquals(expected0.getLine(), actual0.getLine());
    }

    @Test
    void testGeometricProperties_complementedQuadrant_13_oe_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = secondSegment;
        final LineConvexSubset actual0 = path.getElements().get(0);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                    EuclideanTestUtils.assertCoordinatesEqual(expectedStart0, actual0.getStartPoint(), TEST_EPS);
    }
    }

    @Test
    void testGeometricProperties_complementedQuadrant_13_oe_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = secondSegment;
        final LineConvexSubset actual0 = path.getElements().get(0);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                    Assertions.assertNull(actual0.getStartPoint());
    }
    }

    @Test
    void testGeometricProperties_complementedQuadrant_13_oe_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = secondSegment;
        final LineConvexSubset actual0 = path.getElements().get(0);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                }
        
                if (expectedEnd0 != null) {
                    EuclideanTestUtils.assertCoordinatesEqual(expectedEnd0, actual0.getEndPoint(), TEST_EPS);
    }
    }

    @Test
    void testGeometricProperties_complementedQuadrant_13_oe_5_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = secondSegment;
        final LineConvexSubset actual0 = path.getElements().get(0);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                }
        
                if (expectedEnd0 != null) {
                } else {
                    Assertions.assertNull(actual0.getEndPoint());
    }
    }

    @Test
    void testGeometricProperties_complementedQuadrant_14_oe_1_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = firstSegment;
        final LineConvexSubset actual0 = path.getElements().get(1);
        Assertions.assertEquals(expected0.getLine(), actual0.getLine());
    }

    @Test
    void testGeometricProperties_complementedQuadrant_14_oe_2_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = firstSegment;
        final LineConvexSubset actual0 = path.getElements().get(1);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                    EuclideanTestUtils.assertCoordinatesEqual(expectedStart0, actual0.getStartPoint(), TEST_EPS);
    }
    }

    @Test
    void testGeometricProperties_complementedQuadrant_14_oe_3_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = firstSegment;
        final LineConvexSubset actual0 = path.getElements().get(1);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                    Assertions.assertNull(actual0.getStartPoint());
    }
    }

    @Test
    void testGeometricProperties_complementedQuadrant_14_oe_4_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = firstSegment;
        final LineConvexSubset actual0 = path.getElements().get(1);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                }
        
                if (expectedEnd0 != null) {
                    EuclideanTestUtils.assertCoordinatesEqual(expectedEnd0, actual0.getEndPoint(), TEST_EPS);
    }
    }

    @Test
    void testGeometricProperties_complementedQuadrant_14_oe_5_oe() {
        final RegionBSPTree2D tree = RegionBSPTree2D.empty();
        tree.getRoot().cut(X_AXIS)
            .getMinus().cut(Y_AXIS);

        tree.complement();



        final List<LineConvexSubset> segments = new ArrayList<>(tree.getBoundaries());

        segments.sort(SEGMENT_COMPARATOR);

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);

        final List<LinePath> paths = tree.getBoundaryPaths();

        final LinePath path = paths.get(0);
                final LineConvexSubset expected0 = firstSegment;
        final LineConvexSubset actual0 = path.getElements().get(1);
        
                final Vector2D expectedStart0 = expected0.getStartPoint();
                final Vector2D expectedEnd0 = expected0.getEndPoint();
        
                if (expectedStart0 != null) {
                } else {
                }
        
                if (expectedEnd0 != null) {
                } else {
                    Assertions.assertNull(actual0.getEndPoint());
    }
    }

@Test
    void testPartitionedRegionBuilder_insertPartitionAfterBoundary_1_oe() {
        final PartitionedRegionBuilder2D builder = RegionBSPTree2D.partitionedRegionBuilder();
        builder.insertBoundary(Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION));

        final Line partition = Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION);

        final String msg = "Cannot insert partitions after boundaries have been inserted";

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.insertPartition(partition); }, IllegalStateException.class, msg);
    }

@Test
    void testPartitionedRegionBuilder_insertPartitionAfterBoundary_2_oe() {
        final PartitionedRegionBuilder2D builder = RegionBSPTree2D.partitionedRegionBuilder();
        builder.insertBoundary(Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION));

        final Line partition = Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION);

        final String msg = "Cannot insert partitions after boundaries have been inserted";


        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.insertPartition(partition.span()); }, IllegalStateException.class, msg);
    }

@Test
    void testPartitionedRegionBuilder_insertPartitionAfterBoundary_3_oe() {
        final PartitionedRegionBuilder2D builder = RegionBSPTree2D.partitionedRegionBuilder();
        builder.insertBoundary(Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION));

        final Line partition = Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION);

        final String msg = "Cannot insert partitions after boundaries have been inserted";



        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.insertAxisAlignedPartitions(Vector2D.ZERO, TEST_PRECISION); }, IllegalStateException.class, msg);
    }

@Test
    void testPartitionedRegionBuilder_insertPartitionAfterBoundary_4_oe() {
        final PartitionedRegionBuilder2D builder = RegionBSPTree2D.partitionedRegionBuilder();
        builder.insertBoundary(Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION));

        final Line partition = Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION);

        final String msg = "Cannot insert partitions after boundaries have been inserted";




        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.insertAxisAlignedGrid(Bounds2D.from(Vector2D.ZERO, Vector2D.of(1, 1)), 1, TEST_PRECISION); }, IllegalStateException.class, msg);
    }

}
