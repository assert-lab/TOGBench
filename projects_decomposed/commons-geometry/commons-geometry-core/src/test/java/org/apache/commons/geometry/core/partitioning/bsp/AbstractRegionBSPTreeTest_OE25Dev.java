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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.Transform;
import org.apache.commons.geometry.core.partitioning.BoundarySource;
import org.apache.commons.geometry.core.partitioning.HyperplaneConvexSubset;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.core.partitioning.bsp.AbstractRegionBSPTree.RegionSizeProperties;
import org.apache.commons.geometry.core.partitioning.test.PartitionTestUtils;
import org.apache.commons.geometry.core.partitioning.test.TestLine;
import org.apache.commons.geometry.core.partitioning.test.TestLineSegment;
import org.apache.commons.geometry.core.partitioning.test.TestLineSegmentCollection;
import org.apache.commons.geometry.core.partitioning.test.TestPoint2D;
import org.apache.commons.geometry.core.partitioning.test.TestRegionBSPTree;
import org.apache.commons.geometry.core.partitioning.test.TestRegionBSPTree.TestRegionNode;
import org.apache.commons.geometry.core.partitioning.test.TestTransform2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AbstractRegionBSPTreeTest_OE25Dev {

    private TestRegionBSPTree tree;

    private TestRegionNode root;

    @BeforeEach
    public void setup() {
        tree = new TestRegionBSPTree();
        root = tree.getRoot();
    }

    @Test
    void testInsert_hyperplaneSubsets_mixedCutRules() {
        // act/assert
        checkMixedCutRuleInsertion(segs -> {
            tree.insert(new TestLineSegmentCollection(Collections.singletonList(segs[0])), RegionCutRule.PLUS_INSIDE);
            tree.insert(new TestLineSegmentCollection(Collections.singletonList(segs[1]))); // default rule
            tree.insert(new TestLineSegmentCollection(Collections.singletonList(segs[2])), RegionCutRule.PLUS_INSIDE);
            tree.insert(new TestLineSegmentCollection(Collections.singletonList(segs[3])), RegionCutRule.MINUS_INSIDE);
            tree.insert(new TestLineSegmentCollection(Collections.singletonList(segs[4])), RegionCutRule.INHERIT);
        });

    }

    @Test
    void testInsert_hyperplaneConvexSubsets_mixedCutRules() {
        // act/assert
        checkMixedCutRuleInsertion(segs -> {
            tree.insert(segs[0], RegionCutRule.PLUS_INSIDE);
            tree.insert(segs[1]); // default rule
            tree.insert(segs[2], RegionCutRule.PLUS_INSIDE);
            tree.insert(segs[3], RegionCutRule.MINUS_INSIDE);
            tree.insert(segs[4], RegionCutRule.INHERIT);
        });
    }

    @Test
    void testInsert_hyperplaneConvexSubsetList_mixedCutRules() {
        // act/assert
        checkMixedCutRuleInsertion(segs -> {
            tree.insert(Collections.singletonList(segs[0]), RegionCutRule.PLUS_INSIDE);
            tree.insert(Collections.singletonList(segs[1])); // default rule
            tree.insert(Collections.singletonList(segs[2]), RegionCutRule.PLUS_INSIDE);
            tree.insert(Collections.singletonList(segs[3]), RegionCutRule.MINUS_INSIDE);
            tree.insert(Collections.singletonList(segs[4]), RegionCutRule.INHERIT);
        });
    }

    @Test
    void testInsert_boundarySource_mixedCutRules() {
        // arrange
        final Function<TestLineSegment, BoundarySource<TestLineSegment>> factory = seg -> () -> Stream.of(seg);

        // act/assert
        checkMixedCutRuleInsertion(segs -> {
            tree.insert(factory.apply(segs[0]), RegionCutRule.PLUS_INSIDE);
            tree.insert(factory.apply(segs[1])); // default rule
            tree.insert(factory.apply(segs[2]), RegionCutRule.PLUS_INSIDE);
            tree.insert(factory.apply(segs[3]), RegionCutRule.MINUS_INSIDE);
            tree.insert(factory.apply(segs[4]), RegionCutRule.INHERIT);
        });
    }

    /** Helper function to check the insertion of hyperplane subsets using different region cut rules.
     * @param fn
     */
    private void checkMixedCutRuleInsertion(final Consumer<TestLineSegment[]> fn) {
        // arrange
        final TestLineSegment bottom = new TestLineSegment(new TestPoint2D(1, 0), new TestPoint2D(0, 0));
        final TestLineSegment right = new TestLineSegment(new TestPoint2D(1, 0), new TestPoint2D(1, 1));
        final TestLineSegment top = new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(1, 1));
        final TestLineSegment left = new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(0, 0));
        final TestLineSegment diag = new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 1));

        tree = emptyTree();

        // act
        fn.accept(new TestLineSegment[] {
            bottom,
            right,
            top,
            left,
            diag
        });

        // assert
        TestRegionNode node = tree.getRoot();
        Assertions.assertEquals(RegionLocation.OUTSIDE, node.getLocation());

        Assertions.assertEquals(RegionLocation.OUTSIDE, node.getMinus().getLocation());
        Assertions.assertEquals(RegionLocation.INSIDE, node.getPlus().getLocation());

        node = node.getPlus();
        Assertions.assertEquals(RegionLocation.INSIDE, node.getMinus().getLocation());
        Assertions.assertEquals(RegionLocation.OUTSIDE, node.getPlus().getLocation());

        node = node.getMinus();
        Assertions.assertEquals(RegionLocation.OUTSIDE, node.getMinus().getLocation());
        Assertions.assertEquals(RegionLocation.INSIDE, node.getPlus().getLocation());

        node = node.getPlus();
        Assertions.assertEquals(RegionLocation.INSIDE, node.getMinus().getLocation());
        Assertions.assertEquals(RegionLocation.OUTSIDE, node.getPlus().getLocation());

        node = node.getMinus();
        Assertions.assertEquals(RegionLocation.INSIDE, node.getMinus().getLocation());
        Assertions.assertEquals(RegionLocation.INSIDE, node.getPlus().getLocation());
    }

    @Test
    void testSetLocation_invalidArgs() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> root.setLocation(null),
                IllegalArgumentException.class, "Invalid node location: null");
        GeometryTestUtils.assertThrowsWithMessage(() -> root.setLocation(RegionLocation.BOUNDARY),
                IllegalArgumentException.class, "Invalid node location: BOUNDARY");
    }

    @Test
    void testGetCentroid() {
        // act/assert
        // make sure our stub value is pulled
        PartitionTestUtils.assertPointsEqual(new TestPoint2D(12, 34), tree.getCentroid());
    }

    @Test
    void testExtract() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree result = fullTree();

        final TestPoint2D pt = new TestPoint2D(2, 2);

        // act
        result.extract(tree.findNode(pt));

        // assert
        PartitionTestUtils.assertPointLocations(result, RegionLocation.INSIDE,
                new TestPoint2D(0, 0.5), new TestPoint2D(2, 2));
        PartitionTestUtils.assertPointLocations(result, RegionLocation.OUTSIDE,
                new TestPoint2D(-2, 2),
                new TestPoint2D(-2, -2), new TestPoint2D(0, -0.5), new TestPoint2D(-2, 2));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE,
                new TestPoint2D(0, 0.5), new TestPoint2D(2, 2),
                new TestPoint2D(-2, -2), new TestPoint2D(0, -0.5));
        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                new TestPoint2D(2, -2), new TestPoint2D(-2, 2));
    }

    @Test
    void testExtract_complementedTree() {
        // arrange
        insertSkewedBowtie(tree);
        tree.complement();

        final TestRegionBSPTree result = fullTree();

        final TestPoint2D pt = new TestPoint2D(2, 2);

        // act
        result.extract(tree.findNode(pt));

        // assert
        PartitionTestUtils.assertPointLocations(result, RegionLocation.OUTSIDE,
                new TestPoint2D(0, 0.5), new TestPoint2D(2, 2));
        PartitionTestUtils.assertPointLocations(result, RegionLocation.INSIDE,
                new TestPoint2D(-2, 2),
                new TestPoint2D(-2, -2), new TestPoint2D(0, -0.5), new TestPoint2D(-2, 2));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                new TestPoint2D(0, 0.5), new TestPoint2D(2, 2),
                new TestPoint2D(-2, -2), new TestPoint2D(0, -0.5));
        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE,
                new TestPoint2D(2, -2), new TestPoint2D(-2, 2));
    }

    @Test
    void testProject_halfSpace() {
        // arrange
        tree.getRoot().cut(TestLine.X_AXIS);

        // act/assert
        PartitionTestUtils.assertPointsEqual(TestPoint2D.ZERO, tree.project(TestPoint2D.ZERO));

        PartitionTestUtils.assertPointsEqual(TestPoint2D.ZERO, tree.project(new TestPoint2D(0, 7)));
        PartitionTestUtils.assertPointsEqual(TestPoint2D.ZERO, tree.project(new TestPoint2D(0, -7)));

        PartitionTestUtils.assertPointsEqual(new TestPoint2D(4, 0), tree.project(new TestPoint2D(4, 10)));
        PartitionTestUtils.assertPointsEqual(new TestPoint2D(-5, 0), tree.project(new TestPoint2D(-5, -2)));
    }

    @Test
    void testProject_box() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        // act/assert
        PartitionTestUtils.assertPointsEqual(TestPoint2D.ZERO, tree.project(TestPoint2D.ZERO));
        PartitionTestUtils.assertPointsEqual(TestPoint2D.ZERO, tree.project(new TestPoint2D(-1, -4)));

        PartitionTestUtils.assertPointsEqual(new TestPoint2D(1, 1), tree.project(new TestPoint2D(2, 9)));

        PartitionTestUtils.assertPointsEqual(new TestPoint2D(0.5, 1), tree.project(new TestPoint2D(0.5, 3)));
    }

    private static void insertBox(final TestRegionBSPTree tree, final TestPoint2D upperLeft,
            final TestPoint2D lowerRight) {
        final TestPoint2D upperRight = new TestPoint2D(lowerRight.getX(), upperLeft.getY());
        final TestPoint2D lowerLeft = new TestPoint2D(upperLeft.getX(), lowerRight.getY());

        tree.insert(Arrays.asList(
                    new TestLineSegment(lowerRight, upperRight),
                    new TestLineSegment(upperRight, upperLeft),
                    new TestLineSegment(upperLeft, lowerLeft),
                    new TestLineSegment(lowerLeft, lowerRight)
                ));
    }

    private static void insertSkewedBowtie(final TestRegionBSPTree tree) {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),

                new TestLineSegment(new TestPoint2D(4, 0), new TestPoint2D(4, 1)),
                new TestLineSegment(new TestPoint2D(-4, 0), new TestPoint2D(-4, -1)),

                new TestLineSegment(new TestPoint2D(4, 5), new TestPoint2D(-1, 0)),
                new TestLineSegment(new TestPoint2D(-4, -5), new TestPoint2D(1, 0))));
    }

    private static void assertCutBoundarySegment(final List<HyperplaneConvexSubset<TestPoint2D>> boundaries,
            final TestPoint2D start, final TestPoint2D end) {
        Assertions.assertFalse(boundaries.isEmpty(), "Expected boundary to not be empty");

        Assertions.assertEquals(1, boundaries.size());

        final TestLineSegment segment = (TestLineSegment) boundaries.get(0);
        PartitionTestUtils.assertPointsEqual(start, segment.getStartPoint());
        PartitionTestUtils.assertPointsEqual(end, segment.getEndPoint());
    }

    private static void assertContainsSegment(final List<TestLineSegment> boundaries, final TestPoint2D start,
            final TestPoint2D end) {
        boolean found = false;
        for (final TestLineSegment seg : boundaries) {
            final TestPoint2D startPt = seg.getStartPoint();
            final TestPoint2D endPt = seg.getEndPoint();

            if (PartitionTestUtils.PRECISION.eq(start.getX(), startPt.getX()) &&
                    PartitionTestUtils.PRECISION.eq(start.getY(), startPt.getY()) &&
                    PartitionTestUtils.PRECISION.eq(end.getX(), endPt.getX()) &&
                    PartitionTestUtils.PRECISION.eq(end.getY(), endPt.getY())) {
                found = true;
                break;
            }
        }

        Assertions.assertTrue(found, "Expected to find segment start= " + start + ", end= " + end);
    }

    private static TestRegionBSPTree emptyTree() {
        return new TestRegionBSPTree(false);
    }

    private static TestRegionBSPTree fullTree() {
        return new TestRegionBSPTree(true);
    }


}
