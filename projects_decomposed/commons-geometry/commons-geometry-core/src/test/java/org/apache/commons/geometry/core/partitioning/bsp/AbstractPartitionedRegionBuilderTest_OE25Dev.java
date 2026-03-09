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
package org.apache.commons.geometry.core.partitioning.bsp;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.HyperplaneConvexSubset;
import org.apache.commons.geometry.core.partitioning.test.PartitionTestUtils;
import org.apache.commons.geometry.core.partitioning.test.TestLine;
import org.apache.commons.geometry.core.partitioning.test.TestLineSegment;
import org.apache.commons.geometry.core.partitioning.test.TestPoint2D;
import org.apache.commons.geometry.core.partitioning.test.TestRegionBSPTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AbstractPartitionedRegionBuilderTest_OE25Dev {

    @Test
    void testCtor_invalidTree() {
        // arrange
        final TestRegionBSPTree tree = new TestRegionBSPTree(true);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            new TestRegionBuilder(tree);
        }, IllegalArgumentException.class, "Tree must be empty");
    }

    @Test
    void testBuildRegion_empty() {
        // arrange
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        // act
        final TestRegionBSPTree tree = builder.build();

        // assert
        Assertions.assertTrue(tree.isEmpty());
        Assertions.assertEquals(1, tree.count());
        Assertions.assertEquals(0, tree.height());
    }

    @Test
    void testInsertPartition_cannotInsertAfterBoundary() {
        // arrange
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());
        }, IllegalStateException.class, "Cannot insert partitions after boundaries have been inserted");
    }

    @Test
    void testBuildRegion_noPartitions_halfSpace() {
        // arrange
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        // act
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        // assert
        Assertions.assertFalse(tree.isEmpty());
        Assertions.assertFalse(tree.isFull());

        Assertions.assertEquals(3, tree.count());
        Assertions.assertEquals(1, tree.height());

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE,
                new TestPoint2D(-5, 1), new TestPoint2D(0, 1), new TestPoint2D(5, 1));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY,
                new TestPoint2D(-5, 0), new TestPoint2D(0, 0), new TestPoint2D(5, 0));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                new TestPoint2D(-5, -1), new TestPoint2D(0, -1), new TestPoint2D(5, -1));
    }

    @Test
    void testBuildRegion_boundaryOnPartition_sameOrientation() {
     // arrange
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        // act
        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        // assert
        Assertions.assertFalse(tree.isEmpty());
        Assertions.assertFalse(tree.isFull());

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE,
                new TestPoint2D(-5, 1), new TestPoint2D(0, 1), new TestPoint2D(5, 1));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY,
                new TestPoint2D(-5, 0), new TestPoint2D(0, 0), new TestPoint2D(5, 0));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                new TestPoint2D(-5, -1), new TestPoint2D(0, -1), new TestPoint2D(5, -1));
    }

    @Test
    void testBuildRegion_boundaryOnPartition_oppositeOrientation() {
     // arrange
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        // act
        builder.insertPartition(new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        // assert
        Assertions.assertFalse(tree.isEmpty());
        Assertions.assertFalse(tree.isFull());

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE,
                new TestPoint2D(-5, 1), new TestPoint2D(0, 1), new TestPoint2D(5, 1));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY,
                new TestPoint2D(-5, 0), new TestPoint2D(0, 0), new TestPoint2D(5, 0));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                new TestPoint2D(-5, -1), new TestPoint2D(0, -1), new TestPoint2D(5, -1));
    }

    @Test
    void testBuildRegion_boundaryOnPartition_multipleBoundaries_sameOrientation() {
     // arrange
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        // act
        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        // assert
        Assertions.assertFalse(tree.isEmpty());
        Assertions.assertFalse(tree.isFull());

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, new TestPoint2D(5, 1));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY,
                new TestPoint2D(0, 5), new TestPoint2D(0, 0), new TestPoint2D(5, 0));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                new TestPoint2D(-5, 1), new TestPoint2D(-5, -1), new TestPoint2D(0, -1), new TestPoint2D(5, -1));
    }

    @Test
    void testBuildRegion_boundaryOnPartition_multipleBoundaries_oppositeOrientation() {
     // arrange
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        // act
        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(-1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        // assert
        Assertions.assertFalse(tree.isEmpty());
        Assertions.assertFalse(tree.isFull());

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, new TestPoint2D(5, 1));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY,
                new TestPoint2D(0, 5), new TestPoint2D(0, 0), new TestPoint2D(5, 0));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                new TestPoint2D(-5, 1), new TestPoint2D(-5, -1), new TestPoint2D(0, -1), new TestPoint2D(5, -1));
    }

    @Test
    void testBuildRegion_multipleBoundariesOnPartition() {
        // arrange
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        // act
        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(-1, 0)));

        final TestRegionBSPTree tree = builder.build();

        // assert
        Assertions.assertFalse(tree.isEmpty());
        Assertions.assertFalse(tree.isFull());

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE,
                new TestPoint2D(1, 1), new TestPoint2D(-1, -1));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY,
                new TestPoint2D(1, 0), new TestPoint2D(-1, 0), new TestPoint2D(0, 1), new TestPoint2D(0, -1));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                new TestPoint2D(-1, 1), new TestPoint2D(1, -1));
    }

    @Test
    void testBuildRegion_grid_halfSpace_boundaryOnPartition() {
        // arrange
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        // act
        insertGridRecursive(-2, 2, 5, builder);

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        // assert
        Assertions.assertFalse(tree.isEmpty());
        Assertions.assertFalse(tree.isFull());

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE,
                new TestPoint2D(-5, 1), new TestPoint2D(0, 1), new TestPoint2D(5, 1));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY,
                new TestPoint2D(-5, 0), new TestPoint2D(0, 0), new TestPoint2D(5, 0));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                new TestPoint2D(-5, -1), new TestPoint2D(0, -1), new TestPoint2D(5, -1));
    }

    @Test
    void testBuildRegion_boundariesOnPartitionPropagateInsideCorrectly() {
        // arrange
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        // act
        builder.insertPartition(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        builder.insertPartition(new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)));

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        // assert
        Assertions.assertFalse(tree.isEmpty());
        Assertions.assertFalse(tree.isFull());

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE,
                new TestPoint2D(2, 2), new TestPoint2D(5, 5));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY,
                new TestPoint2D(1, 0), new TestPoint2D(1, 10), new TestPoint2D(10, 0));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                new TestPoint2D(-1, 1), new TestPoint2D(-10, 10),
                new TestPoint2D(-1, -1), new TestPoint2D(1, -1));
    }

    @Test
    void testBuildRegion_grid_cube() {
        // arrange
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(-1, -1), new TestPoint2D(1, -1)),
                new TestLineSegment(new TestPoint2D(1, -1), new TestPoint2D(1, 1)),
                new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(-1, 1)),
                new TestLineSegment(new TestPoint2D(-1, 1), new TestPoint2D(-1, -1))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            // act
            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();

            // assert
            Assertions.assertFalse(tree.isEmpty());
            Assertions.assertFalse(tree.isFull());

            PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE,
                    new TestPoint2D(0, 0),
                    new TestPoint2D(-0.5, -0.5), new TestPoint2D(0.5, -0.5),
                    new TestPoint2D(0.5, 0.5), new TestPoint2D(-0.5, 0.5));

            PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY,
                    new TestPoint2D(-1, -1), new TestPoint2D(1, -1), new TestPoint2D(1, 1), new TestPoint2D(-1, 1),
                    new TestPoint2D(-1, 0), new TestPoint2D(1, 0), new TestPoint2D(0, 1), new TestPoint2D(0, -1));

            PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                    new TestPoint2D(-2, -2), new TestPoint2D(2, -2), new TestPoint2D(2, 2), new TestPoint2D(-2, 2),
                    new TestPoint2D(-2, 0), new TestPoint2D(2, 0), new TestPoint2D(0, 2), new TestPoint2D(0, -2));
        }
    }

    @Test
    void testBuildRegion_grid_diamond() {
        // arrange
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(-1, 0)),
                new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(0, -1)),
                new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(1, 0)),
                new TestLineSegment(new TestPoint2D(1, 0), new TestPoint2D(0, 1))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            // act
            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();

            // assert
            Assertions.assertFalse(tree.isEmpty());
            Assertions.assertFalse(tree.isFull());

            PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE,
                    new TestPoint2D(0, 0),
                    new TestPoint2D(-0.25, -0.25), new TestPoint2D(0.25, -0.25),
                    new TestPoint2D(0.25, 0.25), new TestPoint2D(-0.25, 0.25));

            PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY,
                    new TestPoint2D(-0.5, 0.5), new TestPoint2D(-0.5, -0.5), new TestPoint2D(0.5, -0.5), new TestPoint2D(0.5, 0.5),
                    new TestPoint2D(-1, 0), new TestPoint2D(1, 0), new TestPoint2D(0, 1), new TestPoint2D(0, -1));

            PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                    new TestPoint2D(-2, -2), new TestPoint2D(2, -2), new TestPoint2D(2, 2), new TestPoint2D(-2, 2),
                    new TestPoint2D(-2, 0), new TestPoint2D(2, 0), new TestPoint2D(0, 2), new TestPoint2D(0, -2));
        }
    }

    @Test
    void testBuildRegion_grid_horseshoe() {
        // arrange
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(1, 0), new TestPoint2D(1, 1)),
                new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(3, 1)),
                new TestLineSegment(new TestPoint2D(3, 1), new TestPoint2D(3, 2)),
                new TestLineSegment(new TestPoint2D(3, 2), new TestPoint2D(-1, 2)),
                new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-1, -1)),
                new TestLineSegment(new TestPoint2D(-1, -1), new TestPoint2D(3, -1)),
                new TestLineSegment(new TestPoint2D(3, -1), new TestPoint2D(3, 0)),
                new TestLineSegment(new TestPoint2D(3, 0), new TestPoint2D(1, 0))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            // act
            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();

            // assert
            Assertions.assertFalse(tree.isEmpty());
            Assertions.assertFalse(tree.isFull());

            PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE,
                    new TestPoint2D(0, 0),
                    new TestPoint2D(0, 1.5), new TestPoint2D(2, 1.5),
                    new TestPoint2D(0, -0.5), new TestPoint2D(2, -0.5));

            PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY,
                    new TestPoint2D(1, 0), new TestPoint2D(1, 1), new TestPoint2D(3, 1), new TestPoint2D(3, 2),
                    new TestPoint2D(-1, 2), new TestPoint2D(-1, -1), new TestPoint2D(3, -1), new TestPoint2D(3, 0),
                    new TestPoint2D(1, 0.5), new TestPoint2D(2, 1), new TestPoint2D(3, 1.5), new TestPoint2D(1, 2),
                    new TestPoint2D(-1, 0.5), new TestPoint2D(3, -0.5), new TestPoint2D(2, 0));

            PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                    new TestPoint2D(2, 0.5), new TestPoint2D(4, 0.5), new TestPoint2D(4, 0), new TestPoint2D(4, 1.5),
                    new TestPoint2D(1, 4), new TestPoint2D(1, -4), new TestPoint2D(-4, 0.5));
        }
    }

    private static void insertGridRecursive(final double min, final double max, final int count, final TestRegionBuilder builder) {
        if (count > 0) {
            final double center = (0.5 * (max - min)) + min;

            builder.insertPartition(
                    new TestLine(new TestPoint2D(center, center), new TestPoint2D(center + 1, center)).span());

            builder.insertPartition(
                    new TestLine(new TestPoint2D(center, center), new TestPoint2D(center, center + 1)).span());

            insertGridRecursive(min, center, count - 1, builder);
            insertGridRecursive(center, max, count - 1, builder);
        }
    }

    private static class TestRegionBuilder
        extends AbstractPartitionedRegionBuilder<TestPoint2D, TestRegionBSPTree.TestRegionNode> {

        TestRegionBuilder(final TestRegionBSPTree tree) {
            super(tree);
        }

        public TestRegionBSPTree build() {
            return (TestRegionBSPTree) buildInternal();
        }

        public void insertPartition(final HyperplaneConvexSubset<TestPoint2D> partition) {
            insertPartitionInternal(partition);
        }

        public void insertBoundary(final HyperplaneConvexSubset<TestPoint2D> boundary) {
            insertBoundaryInternal(boundary);
        }
    }

    @Test
    void testBuildRegion_empty_1_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        final TestRegionBSPTree tree = builder.build();

        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testBuildRegion_empty_2_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        final TestRegionBSPTree tree = builder.build();

        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testBuildRegion_empty_3_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        final TestRegionBSPTree tree = builder.build();

        Assertions.assertEquals(0, tree.height());
    }

    @Test
    void testBuildRegion_noPartitions_halfSpace_1_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testBuildRegion_noPartitions_halfSpace_2_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testBuildRegion_noPartitions_halfSpace_3_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();


        Assertions.assertEquals(3, tree.count());
    }

    @Test
    void testBuildRegion_noPartitions_halfSpace_4_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();


        Assertions.assertEquals(1, tree.height());
    }

    @Test
    void testBuildRegion_boundaryOnPartition_sameOrientation_1_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testBuildRegion_boundaryOnPartition_sameOrientation_2_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testBuildRegion_boundaryOnPartition_oppositeOrientation_1_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testBuildRegion_boundaryOnPartition_oppositeOrientation_2_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testBuildRegion_boundaryOnPartition_multipleBoundaries_sameOrientation_1_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testBuildRegion_boundaryOnPartition_multipleBoundaries_sameOrientation_2_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testBuildRegion_boundaryOnPartition_multipleBoundaries_oppositeOrientation_1_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(-1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testBuildRegion_boundaryOnPartition_multipleBoundaries_oppositeOrientation_2_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(-1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testBuildRegion_multipleBoundariesOnPartition_1_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(-1, 0)));

        final TestRegionBSPTree tree = builder.build();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testBuildRegion_multipleBoundariesOnPartition_2_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(-1, 0)));

        final TestRegionBSPTree tree = builder.build();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testBuildRegion_grid_halfSpace_boundaryOnPartition_1_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        insertGridRecursive(-2, 2, 5, builder);

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testBuildRegion_grid_halfSpace_boundaryOnPartition_2_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        insertGridRecursive(-2, 2, 5, builder);

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testBuildRegion_boundariesOnPartitionPropagateInsideCorrectly_1_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        builder.insertPartition(new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)));

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testBuildRegion_boundariesOnPartitionPropagateInsideCorrectly_2_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        builder.insertPartition(new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)));

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testBuildRegion_grid_cube_1_oe() {
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(-1, -1), new TestPoint2D(1, -1)),
                new TestLineSegment(new TestPoint2D(1, -1), new TestPoint2D(1, 1)),
                new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(-1, 1)),
                new TestLineSegment(new TestPoint2D(-1, 1), new TestPoint2D(-1, -1))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();

            Assertions.assertFalse(tree.isEmpty());
    }
    }

    @Test
    void testBuildRegion_grid_cube_2_oe() {
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(-1, -1), new TestPoint2D(1, -1)),
                new TestLineSegment(new TestPoint2D(1, -1), new TestPoint2D(1, 1)),
                new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(-1, 1)),
                new TestLineSegment(new TestPoint2D(-1, 1), new TestPoint2D(-1, -1))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();

            Assertions.assertFalse(tree.isFull());
    }
    }

    @Test
    void testBuildRegion_grid_diamond_1_oe() {
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(-1, 0)),
                new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(0, -1)),
                new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(1, 0)),
                new TestLineSegment(new TestPoint2D(1, 0), new TestPoint2D(0, 1))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();

            Assertions.assertFalse(tree.isEmpty());
    }
    }

    @Test
    void testBuildRegion_grid_diamond_2_oe() {
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(-1, 0)),
                new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(0, -1)),
                new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(1, 0)),
                new TestLineSegment(new TestPoint2D(1, 0), new TestPoint2D(0, 1))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();

            Assertions.assertFalse(tree.isFull());
    }
    }

    @Test
    void testBuildRegion_grid_horseshoe_1_oe() {
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(1, 0), new TestPoint2D(1, 1)),
                new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(3, 1)),
                new TestLineSegment(new TestPoint2D(3, 1), new TestPoint2D(3, 2)),
                new TestLineSegment(new TestPoint2D(3, 2), new TestPoint2D(-1, 2)),
                new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-1, -1)),
                new TestLineSegment(new TestPoint2D(-1, -1), new TestPoint2D(3, -1)),
                new TestLineSegment(new TestPoint2D(3, -1), new TestPoint2D(3, 0)),
                new TestLineSegment(new TestPoint2D(3, 0), new TestPoint2D(1, 0))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();

            Assertions.assertFalse(tree.isEmpty());
    }
    }

    @Test
    void testBuildRegion_grid_horseshoe_2_oe() {
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(1, 0), new TestPoint2D(1, 1)),
                new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(3, 1)),
                new TestLineSegment(new TestPoint2D(3, 1), new TestPoint2D(3, 2)),
                new TestLineSegment(new TestPoint2D(3, 2), new TestPoint2D(-1, 2)),
                new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-1, -1)),
                new TestLineSegment(new TestPoint2D(-1, -1), new TestPoint2D(3, -1)),
                new TestLineSegment(new TestPoint2D(3, -1), new TestPoint2D(3, 0)),
                new TestLineSegment(new TestPoint2D(3, 0), new TestPoint2D(1, 0))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();

            Assertions.assertFalse(tree.isFull());
    }
    }

@Test
    void testCtor_invalidTree_1_oe() {
        final TestRegionBSPTree tree = new TestRegionBSPTree(true);

        GeometryTestUtils.assertThrowsWithMessage(() -> { new TestRegionBuilder(tree); }, IllegalArgumentException.class, "Tree must be empty");
    }

@Test
    void testInsertPartition_cannotInsertAfterBoundary_1_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));

        GeometryTestUtils.assertThrowsWithMessage(() -> { builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span()); }, IllegalStateException.class, "Cannot insert partitions after boundaries have been inserted");
    }

@Test
    void testBuildRegion_noPartitions_halfSpace_5_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();



        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, new TestPoint2D(-5, 1), new TestPoint2D(0, 1), new TestPoint2D(5, 1));
    }

@Test
    void testBuildRegion_noPartitions_halfSpace_6_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();




        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY, new TestPoint2D(-5, 0), new TestPoint2D(0, 0), new TestPoint2D(5, 0));
    }

@Test
    void testBuildRegion_noPartitions_halfSpace_7_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();





        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(-5, -1), new TestPoint2D(0, -1), new TestPoint2D(5, -1));
    }

@Test
    void testBuildRegion_boundaryOnPartition_sameOrientation_3_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();


        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, new TestPoint2D(-5, 1), new TestPoint2D(0, 1), new TestPoint2D(5, 1));
    }

@Test
    void testBuildRegion_boundaryOnPartition_sameOrientation_4_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();



        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY, new TestPoint2D(-5, 0), new TestPoint2D(0, 0), new TestPoint2D(5, 0));
    }

@Test
    void testBuildRegion_boundaryOnPartition_sameOrientation_5_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();




        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(-5, -1), new TestPoint2D(0, -1), new TestPoint2D(5, -1));
    }

@Test
    void testBuildRegion_boundaryOnPartition_oppositeOrientation_3_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();


        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, new TestPoint2D(-5, 1), new TestPoint2D(0, 1), new TestPoint2D(5, 1));
    }

@Test
    void testBuildRegion_boundaryOnPartition_oppositeOrientation_4_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();



        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY, new TestPoint2D(-5, 0), new TestPoint2D(0, 0), new TestPoint2D(5, 0));
    }

@Test
    void testBuildRegion_boundaryOnPartition_oppositeOrientation_5_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();




        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(-5, -1), new TestPoint2D(0, -1), new TestPoint2D(5, -1));
    }

@Test
    void testBuildRegion_boundaryOnPartition_multipleBoundaries_sameOrientation_4_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();



        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY, new TestPoint2D(0, 5), new TestPoint2D(0, 0), new TestPoint2D(5, 0));
    }

@Test
    void testBuildRegion_boundaryOnPartition_multipleBoundaries_sameOrientation_5_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();




        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(-5, 1), new TestPoint2D(-5, -1), new TestPoint2D(0, -1), new TestPoint2D(5, -1));
    }

@Test
    void testBuildRegion_boundaryOnPartition_multipleBoundaries_oppositeOrientation_4_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(-1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();



        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY, new TestPoint2D(0, 5), new TestPoint2D(0, 0), new TestPoint2D(5, 0));
    }

@Test
    void testBuildRegion_boundaryOnPartition_multipleBoundaries_oppositeOrientation_5_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(-1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();




        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(-5, 1), new TestPoint2D(-5, -1), new TestPoint2D(0, -1), new TestPoint2D(5, -1));
    }

@Test
    void testBuildRegion_multipleBoundariesOnPartition_3_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(-1, 0)));

        final TestRegionBSPTree tree = builder.build();


        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, new TestPoint2D(1, 1), new TestPoint2D(-1, -1));
    }

@Test
    void testBuildRegion_multipleBoundariesOnPartition_4_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(-1, 0)));

        final TestRegionBSPTree tree = builder.build();



        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY, new TestPoint2D(1, 0), new TestPoint2D(-1, 0), new TestPoint2D(0, 1), new TestPoint2D(0, -1));
    }

@Test
    void testBuildRegion_multipleBoundariesOnPartition_5_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(-1, 0)));

        final TestRegionBSPTree tree = builder.build();




        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(-1, 1), new TestPoint2D(1, -1));
    }

@Test
    void testBuildRegion_grid_halfSpace_boundaryOnPartition_3_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        insertGridRecursive(-2, 2, 5, builder);

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();


        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, new TestPoint2D(-5, 1), new TestPoint2D(0, 1), new TestPoint2D(5, 1));
    }

@Test
    void testBuildRegion_grid_halfSpace_boundaryOnPartition_4_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        insertGridRecursive(-2, 2, 5, builder);

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();



        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY, new TestPoint2D(-5, 0), new TestPoint2D(0, 0), new TestPoint2D(5, 0));
    }

@Test
    void testBuildRegion_grid_halfSpace_boundaryOnPartition_5_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        insertGridRecursive(-2, 2, 5, builder);

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();




        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(-5, -1), new TestPoint2D(0, -1), new TestPoint2D(5, -1));
    }

@Test
    void testBuildRegion_boundariesOnPartitionPropagateInsideCorrectly_3_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        builder.insertPartition(new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)));

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();


        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, new TestPoint2D(2, 2), new TestPoint2D(5, 5));
    }

@Test
    void testBuildRegion_boundariesOnPartitionPropagateInsideCorrectly_4_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        builder.insertPartition(new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)));

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();



        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY, new TestPoint2D(1, 0), new TestPoint2D(1, 10), new TestPoint2D(10, 0));
    }

@Test
    void testBuildRegion_boundariesOnPartitionPropagateInsideCorrectly_5_oe() {
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertPartition(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        builder.insertPartition(new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)));

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));
        builder.insertBoundary(new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(1, 0)));
        final TestRegionBSPTree tree = builder.build();




        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(-1, 1), new TestPoint2D(-10, 10), new TestPoint2D(-1, -1), new TestPoint2D(1, -1));
    }

@Test
    void testBuildRegion_grid_cube_3_oe() {
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(-1, -1), new TestPoint2D(1, -1)),
                new TestLineSegment(new TestPoint2D(1, -1), new TestPoint2D(1, 1)),
                new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(-1, 1)),
                new TestLineSegment(new TestPoint2D(-1, 1), new TestPoint2D(-1, -1))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();


            PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, new TestPoint2D(0, 0), new TestPoint2D(-0.5, -0.5), new TestPoint2D(0.5, -0.5), new TestPoint2D(0.5, 0.5), new TestPoint2D(-0.5, 0.5));
    }
    }

@Test
    void testBuildRegion_grid_cube_4_oe() {
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(-1, -1), new TestPoint2D(1, -1)),
                new TestLineSegment(new TestPoint2D(1, -1), new TestPoint2D(1, 1)),
                new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(-1, 1)),
                new TestLineSegment(new TestPoint2D(-1, 1), new TestPoint2D(-1, -1))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();



            PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY, new TestPoint2D(-1, -1), new TestPoint2D(1, -1), new TestPoint2D(1, 1), new TestPoint2D(-1, 1), new TestPoint2D(-1, 0), new TestPoint2D(1, 0), new TestPoint2D(0, 1), new TestPoint2D(0, -1));
    }
    }

@Test
    void testBuildRegion_grid_cube_5_oe() {
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(-1, -1), new TestPoint2D(1, -1)),
                new TestLineSegment(new TestPoint2D(1, -1), new TestPoint2D(1, 1)),
                new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(-1, 1)),
                new TestLineSegment(new TestPoint2D(-1, 1), new TestPoint2D(-1, -1))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();




            PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(-2, -2), new TestPoint2D(2, -2), new TestPoint2D(2, 2), new TestPoint2D(-2, 2), new TestPoint2D(-2, 0), new TestPoint2D(2, 0), new TestPoint2D(0, 2), new TestPoint2D(0, -2));
    }
    }

@Test
    void testBuildRegion_grid_diamond_3_oe() {
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(-1, 0)),
                new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(0, -1)),
                new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(1, 0)),
                new TestLineSegment(new TestPoint2D(1, 0), new TestPoint2D(0, 1))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();


            PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, new TestPoint2D(0, 0), new TestPoint2D(-0.25, -0.25), new TestPoint2D(0.25, -0.25), new TestPoint2D(0.25, 0.25), new TestPoint2D(-0.25, 0.25));
    }
    }

@Test
    void testBuildRegion_grid_diamond_4_oe() {
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(-1, 0)),
                new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(0, -1)),
                new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(1, 0)),
                new TestLineSegment(new TestPoint2D(1, 0), new TestPoint2D(0, 1))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();



            PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY, new TestPoint2D(-0.5, 0.5), new TestPoint2D(-0.5, -0.5), new TestPoint2D(0.5, -0.5), new TestPoint2D(0.5, 0.5), new TestPoint2D(-1, 0), new TestPoint2D(1, 0), new TestPoint2D(0, 1), new TestPoint2D(0, -1));
    }
    }

@Test
    void testBuildRegion_grid_diamond_5_oe() {
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(-1, 0)),
                new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(0, -1)),
                new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(1, 0)),
                new TestLineSegment(new TestPoint2D(1, 0), new TestPoint2D(0, 1))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();




            PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(-2, -2), new TestPoint2D(2, -2), new TestPoint2D(2, 2), new TestPoint2D(-2, 2), new TestPoint2D(-2, 0), new TestPoint2D(2, 0), new TestPoint2D(0, 2), new TestPoint2D(0, -2));
    }
    }

@Test
    void testBuildRegion_grid_horseshoe_3_oe() {
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(1, 0), new TestPoint2D(1, 1)),
                new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(3, 1)),
                new TestLineSegment(new TestPoint2D(3, 1), new TestPoint2D(3, 2)),
                new TestLineSegment(new TestPoint2D(3, 2), new TestPoint2D(-1, 2)),
                new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-1, -1)),
                new TestLineSegment(new TestPoint2D(-1, -1), new TestPoint2D(3, -1)),
                new TestLineSegment(new TestPoint2D(3, -1), new TestPoint2D(3, 0)),
                new TestLineSegment(new TestPoint2D(3, 0), new TestPoint2D(1, 0))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();


            PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, new TestPoint2D(0, 0), new TestPoint2D(0, 1.5), new TestPoint2D(2, 1.5), new TestPoint2D(0, -0.5), new TestPoint2D(2, -0.5));
    }
    }

@Test
    void testBuildRegion_grid_horseshoe_4_oe() {
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(1, 0), new TestPoint2D(1, 1)),
                new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(3, 1)),
                new TestLineSegment(new TestPoint2D(3, 1), new TestPoint2D(3, 2)),
                new TestLineSegment(new TestPoint2D(3, 2), new TestPoint2D(-1, 2)),
                new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-1, -1)),
                new TestLineSegment(new TestPoint2D(-1, -1), new TestPoint2D(3, -1)),
                new TestLineSegment(new TestPoint2D(3, -1), new TestPoint2D(3, 0)),
                new TestLineSegment(new TestPoint2D(3, 0), new TestPoint2D(1, 0))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();



            PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY, new TestPoint2D(1, 0), new TestPoint2D(1, 1), new TestPoint2D(3, 1), new TestPoint2D(3, 2), new TestPoint2D(-1, 2), new TestPoint2D(-1, -1), new TestPoint2D(3, -1), new TestPoint2D(3, 0), new TestPoint2D(1, 0.5), new TestPoint2D(2, 1), new TestPoint2D(3, 1.5), new TestPoint2D(1, 2), new TestPoint2D(-1, 0.5), new TestPoint2D(3, -0.5), new TestPoint2D(2, 0));
    }
    }

@Test
    void testBuildRegion_grid_horseshoe_5_oe() {
        final int maxCount = 5;

        final List<TestLineSegment> boundaries = Arrays.asList(
                new TestLineSegment(new TestPoint2D(1, 0), new TestPoint2D(1, 1)),
                new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(3, 1)),
                new TestLineSegment(new TestPoint2D(3, 1), new TestPoint2D(3, 2)),
                new TestLineSegment(new TestPoint2D(3, 2), new TestPoint2D(-1, 2)),
                new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-1, -1)),
                new TestLineSegment(new TestPoint2D(-1, -1), new TestPoint2D(3, -1)),
                new TestLineSegment(new TestPoint2D(3, -1), new TestPoint2D(3, 0)),
                new TestLineSegment(new TestPoint2D(3, 0), new TestPoint2D(1, 0))
            );

        for (int c = 0; c <= maxCount; ++c) {
            final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

            insertGridRecursive(-2, 2, c, builder);

            for (final TestLineSegment boundary : boundaries) {
                builder.insertBoundary(boundary);
            }

            final TestRegionBSPTree tree = builder.build();




            PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(2, 0.5), new TestPoint2D(4, 0.5), new TestPoint2D(4, 0), new TestPoint2D(4, 1.5), new TestPoint2D(1, 4), new TestPoint2D(1, -4), new TestPoint2D(-4, 0.5));
    }
    }

}
