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

    @Test
    void testDefaultConstructor_1_oe() {
        // assert
        Assertions.assertNotNull(root);
    }

    @Test
    void testDefaultConstructor_2_oe() {
        // assert
        // removed other assertion
        Assertions.assertNull(root.getParent());
    }

    @Test
    void testDefaultConstructor_4_oe() {
        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(root.isPlus());
    }

    @Test
    void testDefaultConstructor_5_oe() {
        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(root.isMinus());
    }

    @Test
    void testDefaultConstructor_6_oe() {
        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(tree, root.getTree());
    }

    @Test
    void testDefaultConstructor_7_oe() {
        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
    }

    @Test
    void testParameterizedConstructor_true_1_oe() {
        // act
        tree = new TestRegionBSPTree(true);
        root = tree.getRoot();

        // assert
        Assertions.assertNotNull(root);
    }

    @Test
    void testParameterizedConstructor_true_2_oe() {
        // act
        tree = new TestRegionBSPTree(true);
        root = tree.getRoot();

        // assert
        // removed other assertion
        Assertions.assertNull(root.getParent());
    }

    @Test
    void testParameterizedConstructor_true_4_oe() {
        // act
        tree = new TestRegionBSPTree(true);
        root = tree.getRoot();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(root.isPlus());
    }

    @Test
    void testParameterizedConstructor_true_5_oe() {
        // act
        tree = new TestRegionBSPTree(true);
        root = tree.getRoot();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(root.isMinus());
    }

    @Test
    void testParameterizedConstructor_true_6_oe() {
        // act
        tree = new TestRegionBSPTree(true);
        root = tree.getRoot();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(tree, root.getTree());
    }

    @Test
    void testParameterizedConstructor_true_7_oe() {
        // act
        tree = new TestRegionBSPTree(true);
        root = tree.getRoot();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
    }

    @Test
    void testParameterizedConstructor_false_1_oe() {
        // act
        tree = new TestRegionBSPTree(false);
        root = tree.getRoot();

        // assert
        Assertions.assertNotNull(root);
    }

    @Test
    void testParameterizedConstructor_false_2_oe() {
        // act
        tree = new TestRegionBSPTree(false);
        root = tree.getRoot();

        // assert
        // removed other assertion
        Assertions.assertNull(root.getParent());
    }

    @Test
    void testParameterizedConstructor_false_4_oe() {
        // act
        tree = new TestRegionBSPTree(false);
        root = tree.getRoot();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(root.isPlus());
    }

    @Test
    void testParameterizedConstructor_false_5_oe() {
        // act
        tree = new TestRegionBSPTree(false);
        root = tree.getRoot();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(root.isMinus());
    }

    @Test
    void testParameterizedConstructor_false_6_oe() {
        // act
        tree = new TestRegionBSPTree(false);
        root = tree.getRoot();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(tree, root.getTree());
    }

    @Test
    void testParameterizedConstructor_false_7_oe() {
        // act
        tree = new TestRegionBSPTree(false);
        root = tree.getRoot();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, root.getLocation());
    }

    @Test
    void testGetLocation_emptyRoot_1_oe() {
        // act/assert
        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
    }

    @Test
    void testGetLocation_singleCut_1_oe() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act/assert
        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
    }

    @Test
    void testGetLocation_singleCut_2_oe() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act/assert
        // removed other assertion
        Assertions.assertFalse(root.isInside());
    }

    @Test
    void testGetLocation_singleCut_3_oe() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(root.isOutside());
    }

    @Test
    void testGetLocation_singleCut_4_oe() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TestRegionNode minus = root.getMinus();
        Assertions.assertEquals(RegionLocation.INSIDE, minus.getLocation());
    }

    @Test
    void testGetLocation_singleCut_5_oe() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TestRegionNode minus = root.getMinus();
        // removed other assertion
        Assertions.assertTrue(minus.isInside());
    }

    @Test
    void testGetLocation_singleCut_6_oe() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TestRegionNode minus = root.getMinus();
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(minus.isOutside());
    }

    @Test
    void testGetLocation_singleCut_7_oe() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TestRegionNode minus = root.getMinus();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TestRegionNode plus = root.getPlus();
        Assertions.assertEquals(RegionLocation.OUTSIDE, plus.getLocation());
    }

    @Test
    void testGetLocation_singleCut_8_oe() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TestRegionNode minus = root.getMinus();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TestRegionNode plus = root.getPlus();
        // removed other assertion
        Assertions.assertFalse(plus.isInside());
    }

    @Test
    void testGetLocation_singleCut_9_oe() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TestRegionNode minus = root.getMinus();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final TestRegionNode plus = root.getPlus();
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(plus.isOutside());
    }

    @Test
    void testGetLocation_multipleCuts_1_oe() {
        // arrange
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, -1))));

        // act/assert
        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
    }

    @Test
    void testGetLocation_multipleCuts_2_oe() {
        // arrange
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, -1))));

        // act/assert
        // removed other assertion

        final TestRegionNode plus = root.getPlus();
        Assertions.assertEquals(RegionLocation.OUTSIDE, plus.getLocation());
    }

    @Test
    void testGetLocation_multipleCuts_3_oe() {
        // arrange
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, -1))));

        // act/assert
        // removed other assertion

        final TestRegionNode plus = root.getPlus();
        // removed other assertion

        final TestRegionNode plusPlus = plus.getPlus();
        Assertions.assertEquals(RegionLocation.OUTSIDE, plusPlus.getLocation());
    }

    @Test
    void testGetLocation_multipleCuts_4_oe() {
        // arrange
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, -1))));

        // act/assert
        // removed other assertion

        final TestRegionNode plus = root.getPlus();
        // removed other assertion

        final TestRegionNode plusPlus = plus.getPlus();
        // removed other assertion

        final TestRegionNode plusMinus = plus.getMinus();
        Assertions.assertEquals(RegionLocation.INSIDE, plusMinus.getLocation());
    }

    @Test
    void testGetLocation_multipleCuts_5_oe() {
        // arrange
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, -1))));

        // act/assert
        // removed other assertion

        final TestRegionNode plus = root.getPlus();
        // removed other assertion

        final TestRegionNode plusPlus = plus.getPlus();
        // removed other assertion

        final TestRegionNode plusMinus = plus.getMinus();
        // removed other assertion

        final TestRegionNode minus = root.getMinus();
        Assertions.assertEquals(RegionLocation.INSIDE, minus.getLocation());
    }

    @Test
    void testGetLocation_multipleCuts_6_oe() {
        // arrange
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, -1))));

        // act/assert
        // removed other assertion

        final TestRegionNode plus = root.getPlus();
        // removed other assertion

        final TestRegionNode plusPlus = plus.getPlus();
        // removed other assertion

        final TestRegionNode plusMinus = plus.getMinus();
        // removed other assertion

        final TestRegionNode minus = root.getMinus();
        // removed other assertion

        final TestRegionNode minusPlus = minus.getPlus();
        Assertions.assertEquals(RegionLocation.OUTSIDE, minusPlus.getLocation());
    }

    @Test
    void testGetLocation_multipleCuts_7_oe() {
        // arrange
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, -1))));

        // act/assert
        // removed other assertion

        final TestRegionNode plus = root.getPlus();
        // removed other assertion

        final TestRegionNode plusPlus = plus.getPlus();
        // removed other assertion

        final TestRegionNode plusMinus = plus.getMinus();
        // removed other assertion

        final TestRegionNode minus = root.getMinus();
        // removed other assertion

        final TestRegionNode minusPlus = minus.getPlus();
        // removed other assertion

        final TestRegionNode minusMinus = minus.getMinus();
        Assertions.assertEquals(RegionLocation.INSIDE, minusMinus.getLocation());
    }

    @Test
    void testSetLocation_1_oe() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span());

        final TestRegionNode node = tree.getRoot().getMinus();

        // act
        node.setLocation(RegionLocation.OUTSIDE);

        // assert
        Assertions.assertEquals(RegionLocation.OUTSIDE, node.getLocation());
    }

    @Test
    void testSetLocation_2_oe() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span());

        final TestRegionNode node = tree.getRoot().getMinus();

        // act
        node.setLocation(RegionLocation.OUTSIDE);

        // assert
        // removed other assertion
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testSetLocation_invalidatesRegionProperties_1_oe() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span());

        final TestRegionNode node = tree.getRoot().getMinus();

        final RegionSizeProperties<TestPoint2D> prevProps = tree.getRegionSizeProperties();

        // act
        node.setLocation(RegionLocation.OUTSIDE);

        // assert
        Assertions.assertNotSame(prevProps, tree.getRegionSizeProperties());
    }

    @Test
    void testSetLocation_noChange_doesNotInvalidateTree_1_oe() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span());

        final TestRegionNode node = tree.getRoot().getMinus();

        final RegionSizeProperties<TestPoint2D> prevProps = tree.getRegionSizeProperties();

        // act
        node.setLocation(RegionLocation.INSIDE);

        // assert
        Assertions.assertSame(prevProps, tree.getRegionSizeProperties());
    }

    @Test
    void testCondense_1_oe() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);
        tree.insert(TestLine.X_AXIS.span(), RegionCutRule.INHERIT);

        // act
        final boolean result = tree.condense();

        // assert
        Assertions.assertTrue(result);
    }

    @Test
    void testCondense_2_oe() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);
        tree.insert(TestLine.X_AXIS.span(), RegionCutRule.INHERIT);

        // act
        final boolean result = tree.condense();

        // assert
        // removed other assertion

        Assertions.assertEquals(3, tree.count());
    }

    @Test
    void testCondense_3_oe() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);
        tree.insert(TestLine.X_AXIS.span(), RegionCutRule.INHERIT);

        // act
        final boolean result = tree.condense();

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getLocation());
    }

    @Test
    void testCondense_4_oe() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);
        tree.insert(TestLine.X_AXIS.span(), RegionCutRule.INHERIT);

        // act
        final boolean result = tree.condense();

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, tree.getRoot().getMinus().getLocation());
    }

    @Test
    void testCondense_5_oe() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);
        tree.insert(TestLine.X_AXIS.span(), RegionCutRule.INHERIT);

        // act
        final boolean result = tree.condense();

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getPlus().getLocation());
    }

    @Test
    void testCondense_alreadyCondensed_1_oe() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);

        // act
        final boolean result = tree.condense();

        // assert
        Assertions.assertFalse(result);
    }

    @Test
    void testCondense_alreadyCondensed_2_oe() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);

        // act
        final boolean result = tree.condense();

        // assert
        // removed other assertion

        Assertions.assertEquals(3, tree.count());
    }

    @Test
    void testCondense_alreadyCondensed_3_oe() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);

        // act
        final boolean result = tree.condense();

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getLocation());
    }

    @Test
    void testCondense_alreadyCondensed_4_oe() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);

        // act
        final boolean result = tree.condense();

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, tree.getRoot().getMinus().getLocation());
    }

    @Test
    void testCondense_alreadyCondensed_5_oe() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);

        // act
        final boolean result = tree.condense();

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getPlus().getLocation());
    }

    @Test
    void testCondense_invalidatesTreeWhenChanged_1_oe() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);
        tree.insert(TestLine.X_AXIS.span(), RegionCutRule.INHERIT);

        final RegionSizeProperties<TestPoint2D> prevProps = tree.getRegionSizeProperties();

        // act
        final boolean result = tree.condense();

        // assert
        Assertions.assertTrue(result);
    }

    @Test
    void testCondense_invalidatesTreeWhenChanged_2_oe() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);
        tree.insert(TestLine.X_AXIS.span(), RegionCutRule.INHERIT);

        final RegionSizeProperties<TestPoint2D> prevProps = tree.getRegionSizeProperties();

        // act
        final boolean result = tree.condense();

        // assert
        // removed other assertion

        Assertions.assertNotSame(prevProps, tree.getRegionSizeProperties());
    }

    @Test
    void testCondense_doesNotInvalidateTreeWhenNotChanged_1_oe() {
        // arrange
        tree = emptyTree();

        final RegionSizeProperties<TestPoint2D> prevProps = tree.getRegionSizeProperties();

        // act
        final boolean result = tree.condense();

        // assert
        Assertions.assertFalse(result);
    }

    @Test
    void testCondense_doesNotInvalidateTreeWhenNotChanged_2_oe() {
        // arrange
        tree = emptyTree();

        final RegionSizeProperties<TestPoint2D> prevProps = tree.getRegionSizeProperties();

        // act
        final boolean result = tree.condense();

        // assert
        // removed other assertion

        Assertions.assertSame(prevProps, tree.getRegionSizeProperties());
    }

    @Test
    void testCut_nodeMethod_1_oe() {
        // arrange
        tree = emptyTree();

        // act
        tree.getRoot().cut(TestLine.X_AXIS, RegionCutRule.PLUS_INSIDE)
            .getPlus()
                .cut(TestLine.Y_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus()
                    .cut(new TestLine(TestPoint2D.ZERO, new TestPoint2D(-1, -1)), RegionCutRule.INHERIT);

        // assert
        TestRegionNode node = tree.getRoot();
        Assertions.assertEquals(RegionLocation.OUTSIDE, node.getLocation());
    }

    @Test
    void testCut_nodeMethod_2_oe() {
        // arrange
        tree = emptyTree();

        // act
        tree.getRoot().cut(TestLine.X_AXIS, RegionCutRule.PLUS_INSIDE)
            .getPlus()
                .cut(TestLine.Y_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus()
                    .cut(new TestLine(TestPoint2D.ZERO, new TestPoint2D(-1, -1)), RegionCutRule.INHERIT);

        // assert
        TestRegionNode node = tree.getRoot();
        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, node.getMinus().getLocation());
    }

    @Test
    void testCut_nodeMethod_3_oe() {
        // arrange
        tree = emptyTree();

        // act
        tree.getRoot().cut(TestLine.X_AXIS, RegionCutRule.PLUS_INSIDE)
            .getPlus()
                .cut(TestLine.Y_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus()
                    .cut(new TestLine(TestPoint2D.ZERO, new TestPoint2D(-1, -1)), RegionCutRule.INHERIT);

        // assert
        TestRegionNode node = tree.getRoot();
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, node.getPlus().getLocation());
    }

    @Test
    void testCut_nodeMethod_4_oe() {
        // arrange
        tree = emptyTree();

        // act
        tree.getRoot().cut(TestLine.X_AXIS, RegionCutRule.PLUS_INSIDE)
            .getPlus()
                .cut(TestLine.Y_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus()
                    .cut(new TestLine(TestPoint2D.ZERO, new TestPoint2D(-1, -1)), RegionCutRule.INHERIT);

        // assert
        TestRegionNode node = tree.getRoot();
        // removed other assertion

        // removed other assertion
        // removed other assertion

        node = node.getPlus();
        Assertions.assertEquals(RegionLocation.INSIDE, node.getMinus().getLocation());
    }

    @Test
    void testCut_nodeMethod_5_oe() {
        // arrange
        tree = emptyTree();

        // act
        tree.getRoot().cut(TestLine.X_AXIS, RegionCutRule.PLUS_INSIDE)
            .getPlus()
                .cut(TestLine.Y_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus()
                    .cut(new TestLine(TestPoint2D.ZERO, new TestPoint2D(-1, -1)), RegionCutRule.INHERIT);

        // assert
        TestRegionNode node = tree.getRoot();
        // removed other assertion

        // removed other assertion
        // removed other assertion

        node = node.getPlus();
        // removed other assertion
        Assertions.assertEquals(RegionLocation.OUTSIDE, node.getPlus().getLocation());
    }

    @Test
    void testCut_nodeMethod_6_oe() {
        // arrange
        tree = emptyTree();

        // act
        tree.getRoot().cut(TestLine.X_AXIS, RegionCutRule.PLUS_INSIDE)
            .getPlus()
                .cut(TestLine.Y_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus()
                    .cut(new TestLine(TestPoint2D.ZERO, new TestPoint2D(-1, -1)), RegionCutRule.INHERIT);

        // assert
        TestRegionNode node = tree.getRoot();
        // removed other assertion

        // removed other assertion
        // removed other assertion

        node = node.getPlus();
        // removed other assertion
        // removed other assertion

        node = node.getMinus();
        Assertions.assertEquals(RegionLocation.INSIDE, node.getMinus().getLocation());
    }

    @Test
    void testCut_nodeMethod_7_oe() {
        // arrange
        tree = emptyTree();

        // act
        tree.getRoot().cut(TestLine.X_AXIS, RegionCutRule.PLUS_INSIDE)
            .getPlus()
                .cut(TestLine.Y_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus()
                    .cut(new TestLine(TestPoint2D.ZERO, new TestPoint2D(-1, -1)), RegionCutRule.INHERIT);

        // assert
        TestRegionNode node = tree.getRoot();
        // removed other assertion

        // removed other assertion
        // removed other assertion

        node = node.getPlus();
        // removed other assertion
        // removed other assertion

        node = node.getMinus();
        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, node.getPlus().getLocation());
    }

    @Test
    void testBoundaries_fullAndEmpty_1_oe() {
        // act/assert
        tree.setFull();
        Assertions.assertFalse(tree.boundaries().iterator().hasNext());
    }

    @Test
    void testBoundaries_fullAndEmpty_2_oe() {
        // act/assert
        tree.setFull();
        // removed other assertion

        tree.setEmpty();
        Assertions.assertFalse(tree.boundaries().iterator().hasNext());
    }

    @Test
    void testBoundaries_finite_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testBoundaries_finite_inverted_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testGetBoundaries_fullAndEmpty_1_oe() {
        // act/assert
        tree.setFull();
        Assertions.assertEquals(0, tree.getBoundaries().size());
    }

    @Test
    void testGetBoundaries_fullAndEmpty_2_oe() {
        // act/assert
        tree.setFull();
        // removed other assertion

        tree.setEmpty();
        Assertions.assertEquals(0, tree.getBoundaries().size());
    }

    @Test
    void testGetBoundaries_finite_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testGetBoundaries_finite_inverted_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testClassify_1_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(3, 1)));
    }

    @Test
    void testClassify_2_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-3, -1)));
    }

    @Test
    void testClassify_3_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-3, 1)));
    }

    @Test
    void testClassify_4_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(3, -1)));
    }

    @Test
    void testClassify_5_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 5)));
    }

    @Test
    void testClassify_6_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, -5)));
    }

    @Test
    void testClassify_7_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(5, 0)));
    }

    @Test
    void testClassify_8_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 0)));
    }

    @Test
    void testClassify_9_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(3, 0)));
    }

    @Test
    void testClassify_10_oe() {
        // arrange
        insertSkewedBowtie(tree);

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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(2, 0)));
    }

    @Test
    void testClassify_11_oe() {
        // arrange
        insertSkewedBowtie(tree);

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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(1, 0)));
    }

    @Test
    void testClassify_12_oe() {
        // arrange
        insertSkewedBowtie(tree);

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
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(0, 0)));
    }

    @Test
    void testClassify_13_oe() {
        // arrange
        insertSkewedBowtie(tree);

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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-1, 0)));
    }

    @Test
    void testClassify_14_oe() {
        // arrange
        insertSkewedBowtie(tree);

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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-2, 0)));
    }

    @Test
    void testClassify_15_oe() {
        // arrange
        insertSkewedBowtie(tree);

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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-3, 0)));
    }

    @Test
    void testClassify_16_oe() {
        // arrange
        insertSkewedBowtie(tree);

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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, 0)));
    }

    @Test
    void testClassify_17_oe() {
        // arrange
        insertSkewedBowtie(tree);

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
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-5, 0)));
    }

    @Test
    void testClassify_emptyTree_1_oe() {
        // act/assert
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testClassify_NaN_1_oe() {
        // act/assert
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(0, Double.NaN)));
    }

    @Test
    void testContains_1_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        Assertions.assertTrue(tree.contains(new TestPoint2D(3, 1)));
    }

    @Test
    void testContains_2_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        // removed other assertion
        Assertions.assertTrue(tree.contains(new TestPoint2D(-3, -1)));
    }

    @Test
    void testContains_3_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(tree.contains(new TestPoint2D(-3, 1)));
    }

    @Test
    void testContains_4_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(tree.contains(new TestPoint2D(3, -1)));
    }

    @Test
    void testContains_5_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(tree.contains(new TestPoint2D(4, 5)));
    }

    @Test
    void testContains_6_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(tree.contains(new TestPoint2D(-4, -5)));
    }

    @Test
    void testContains_7_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(tree.contains(new TestPoint2D(5, 0)));
    }

    @Test
    void testContains_8_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertTrue(tree.contains(new TestPoint2D(4, 0)));
    }

    @Test
    void testContains_9_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(tree.contains(new TestPoint2D(3, 0)));
    }

    @Test
    void testContains_10_oe() {
        // arrange
        insertSkewedBowtie(tree);

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
        Assertions.assertTrue(tree.contains(new TestPoint2D(2, 0)));
    }

    @Test
    void testContains_11_oe() {
        // arrange
        insertSkewedBowtie(tree);

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
        Assertions.assertTrue(tree.contains(new TestPoint2D(1, 0)));
    }

    @Test
    void testContains_12_oe() {
        // arrange
        insertSkewedBowtie(tree);

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
        Assertions.assertTrue(tree.contains(new TestPoint2D(0, 0)));
    }

    @Test
    void testContains_13_oe() {
        // arrange
        insertSkewedBowtie(tree);

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
        Assertions.assertTrue(tree.contains(new TestPoint2D(-1, 0)));
    }

    @Test
    void testContains_14_oe() {
        // arrange
        insertSkewedBowtie(tree);

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
        Assertions.assertTrue(tree.contains(new TestPoint2D(-2, 0)));
    }

    @Test
    void testContains_15_oe() {
        // arrange
        insertSkewedBowtie(tree);

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
        Assertions.assertTrue(tree.contains(new TestPoint2D(-3, 0)));
    }

    @Test
    void testContains_16_oe() {
        // arrange
        insertSkewedBowtie(tree);

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
        Assertions.assertTrue(tree.contains(new TestPoint2D(-4, 0)));
    }

    @Test
    void testContains_17_oe() {
        // arrange
        insertSkewedBowtie(tree);

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

        Assertions.assertFalse(tree.contains(new TestPoint2D(-5, 0)));
    }

    @Test
    void testSetFull_1_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.setFull();

        // assert
        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testSetFull_2_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.setFull();

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testSetFull_3_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.setFull();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testSetFull_4_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.setFull();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(tree.contains(TestPoint2D.ZERO));
    }

    @Test
    void testSetEmpty_1_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.setEmpty();

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testSetEmpty_2_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.setEmpty();

        // assert
        // removed other assertion
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testSetEmpty_3_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.setEmpty();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testSetEmpty_4_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.setEmpty();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(tree.contains(TestPoint2D.ZERO));
    }

    @Test
    void testGetRegionSizeProperties_cachesValueBasedOnVersion_1_oe() {
        // act
        final RegionSizeProperties<TestPoint2D> first = tree.getRegionSizeProperties();
        final RegionSizeProperties<TestPoint2D> second = tree.getRegionSizeProperties();
        tree.getRoot().cut(TestLine.X_AXIS);
        final RegionSizeProperties<TestPoint2D> third = tree.getRegionSizeProperties();

        // assert
        Assertions.assertSame(first, second);
    }

    @Test
    void testGetRegionSizeProperties_cachesValueBasedOnVersion_2_oe() {
        // act
        final RegionSizeProperties<TestPoint2D> first = tree.getRegionSizeProperties();
        final RegionSizeProperties<TestPoint2D> second = tree.getRegionSizeProperties();
        tree.getRoot().cut(TestLine.X_AXIS);
        final RegionSizeProperties<TestPoint2D> third = tree.getRegionSizeProperties();

        // assert
        // removed other assertion
        Assertions.assertNotSame(second, third);
    }

    @Test
    void testGetRegionSizeProperties_cachesValueBasedOnVersion_3_oe() {
        // act
        final RegionSizeProperties<TestPoint2D> first = tree.getRegionSizeProperties();
        final RegionSizeProperties<TestPoint2D> second = tree.getRegionSizeProperties();
        tree.getRoot().cut(TestLine.X_AXIS);
        final RegionSizeProperties<TestPoint2D> third = tree.getRegionSizeProperties();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1234, first.getSize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetSize_1_oe() {
        // act/assert
        // make sure our stub value is pulled
        Assertions.assertEquals(1234, tree.getSize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_fullAndEmpty_1_oe() {
        // act/assert
        Assertions.assertEquals(0.0, fullTree().getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_fullAndEmpty_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertEquals(0.0, emptyTree().getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_infinite_1_oe() {
        // arrange
        final TestRegionBSPTree halfPos = new TestRegionBSPTree(true);
        halfPos.getRoot().cut(TestLine.X_AXIS);

        final TestRegionBSPTree halfPosComplement = new TestRegionBSPTree(true);
        halfPosComplement.complement(halfPos);

        // act/assert
        Assertions.assertEquals(Double.POSITIVE_INFINITY, halfPos.getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_infinite_2_oe() {
        // arrange
        final TestRegionBSPTree halfPos = new TestRegionBSPTree(true);
        halfPos.getRoot().cut(TestLine.X_AXIS);

        final TestRegionBSPTree halfPosComplement = new TestRegionBSPTree(true);
        halfPosComplement.complement(halfPos);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(Double.POSITIVE_INFINITY, halfPosComplement.getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_alignedCuts_1_oe() {
        // arrange
        final TestPoint2D p0 = TestPoint2D.ZERO;
        final TestPoint2D p1 = new TestPoint2D(0, 3);

        TestRegionNode node = tree.getRoot();

        tree.cutNode(node, new TestLineSegment(p0, p1));
        node = node.getMinus();

        tree.cutNode(node, new TestLineSegment(0, 0, new TestLine(p1, new TestPoint2D(-1, 3))));
        node = node.getMinus();

        tree.cutNode(node, new TestLineSegment(p1, p0));
        node = node.getMinus();

        tree.cutNode(node, new TestLineSegment(0, 0, new TestLine(p0, new TestPoint2D(1, 3))));

        // act/assert
        Assertions.assertEquals(6, tree.getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_box_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(2, 2), new TestPoint2D(4, 1));

        // act/assert
        Assertions.assertEquals(6.0, tree.getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_boxComplement_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(2, 2), new TestPoint2D(4, 1));
        tree.complement();

        // act/assert
        Assertions.assertEquals(6.0, tree.getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_recomputesAfterChange_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(2, 2), new TestPoint2D(4, 1));

        // act
        final double first = tree.getBoundarySize();
        tree.insert(new TestLineSegment(new TestPoint2D(3, 1), new TestPoint2D(3, 2)));

        final double second = tree.getBoundarySize();
        final double third = tree.getBoundarySize();

        // assert
        Assertions.assertEquals(6.0, first, PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_recomputesAfterChange_2_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(2, 2), new TestPoint2D(4, 1));

        // act
        final double first = tree.getBoundarySize();
        tree.insert(new TestLineSegment(new TestPoint2D(3, 1), new TestPoint2D(3, 2)));

        final double second = tree.getBoundarySize();
        final double third = tree.getBoundarySize();

        // assert
        // removed other assertion
        Assertions.assertEquals(4.0, second, PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_recomputesAfterChange_3_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(2, 2), new TestPoint2D(4, 1));

        // act
        final double first = tree.getBoundarySize();
        tree.insert(new TestLineSegment(new TestPoint2D(3, 1), new TestPoint2D(3, 2)));

        final double second = tree.getBoundarySize();
        final double third = tree.getBoundarySize();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(4.0, third, PartitionTestUtils.EPS);
    }

    @Test
    void testGetCutBoundary_emptyTree_1_oe() {
        // act
        final RegionCutBoundary<TestPoint2D> boundary = root.getCutBoundary();

        // assert
        Assertions.assertNull(boundary);
    }

    @Test
    void testGetCutBoundary_singleCut_1_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));

        // act
        final RegionCutBoundary<TestPoint2D> boundary = root.getCutBoundary();

        // assert
        Assertions.assertTrue(boundary.getInsideFacing().isEmpty());
    }

    @Test
    void testGetCutBoundary_singleCut_leafNode_1_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));

        // act
        final RegionCutBoundary<TestPoint2D> boundary = root.getMinus().getCutBoundary();

        // assert
        Assertions.assertNull(boundary);
    }

    @Test
    void testGetCutBoundary_singleCorner_1_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // act/assert
        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();

        Assertions.assertTrue(rootBoundary.getInsideFacing().isEmpty());
    }

    @Test
    void testGetCutBoundary_singleCorner_3_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // act/assert
        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();

        // removed other assertion
        // removed other assertion

        final RegionCutBoundary<TestPoint2D> childBoundary = tree.getRoot().getMinus().getCutBoundary();
        Assertions.assertTrue(childBoundary.getInsideFacing().isEmpty());
    }

    @Test
    void testGetCutBoundary_leafNode_1_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // act/assert
        Assertions.assertNull(root.getPlus().getCutBoundary());
    }

    @Test
    void testGetCutBoundary_leafNode_2_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // act/assert
        // removed other assertion
        Assertions.assertNull(root.getMinus().getMinus().getCutHyperplane());
    }

    @Test
    void testGetCutBoundary_leafNode_3_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(root.getMinus().getPlus().getCutHyperplane());
    }

    @Test
    void testFullEmpty_fullTree_1_oe() {
        // act/assert
        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testFullEmpty_fullTree_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testFullEmpty_fullTree_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, tree.getRoot().getLocation());
    }

    @Test
    void testFullEmpty_emptyTree_1_oe() {
        // arrange
        tree.complement();

        // act/assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testFullEmpty_emptyTree_2_oe() {
        // arrange
        tree.complement();

        // act/assert
        // removed other assertion
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testFullEmpty_emptyTree_3_oe() {
        // arrange
        tree.complement();

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getLocation());
    }

    @Test
    void testTransform_noCuts_1_oe() {
        // arrange
        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), p.getY() + 2));

        // act
        tree.transform(t);

        // assert
        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testTransform_noCuts_2_oe() {
        // arrange
        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), p.getY() + 2));

        // act
        tree.transform(t);

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTransform_singleCut_1_oe() {
        // arrange
        tree.getRoot().insertCut(TestLine.X_AXIS);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), p.getY() + 2));

        // act
        tree.transform(t);

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testTransform_singleCut_2_oe() {
        // arrange
        tree.getRoot().insertCut(TestLine.X_AXIS);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), p.getY() + 2));

        // act
        tree.transform(t);

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTransform_multipleCuts_1_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        // act
        tree.transform(t);

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testTransform_multipleCuts_2_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        // act
        tree.transform(t);

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTransform_xAxisReflection_1_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), p.getY()));

        // act
        tree.transform(t);

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testTransform_xAxisReflection_2_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), p.getY()));

        // act
        tree.transform(t);

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTransform_yAxisReflection_1_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), -p.getY()));

        // act
        tree.transform(t);

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testTransform_yAxisReflection_2_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), -p.getY()));

        // act
        tree.transform(t);

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTransform_xAndYAxisReflection_1_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), -p.getY()));

        // act
        tree.transform(t);

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testTransform_xAndYAxisReflection_2_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), -p.getY()));

        // act
        tree.transform(t);

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTransform_resetsCutBoundary_1_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        // act
        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();

        // assert
        Assertions.assertNotSame(origBoundary, resultBoundary);
    }

    @Test
    void testComplement_rootOnly_1_oe() {
        // act
        tree.complement();

        // assert
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testComplement_rootOnly_2_oe() {
        // act
        tree.complement();

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testComplement_rootOnly_3_oe() {
        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, root.getLocation());
    }

    @Test
    void testComplement_rootOnly_4_oe() {
        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplement_singleCut_1_oe() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act
        tree.complement();

        // assert
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testComplement_singleCut_2_oe() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act
        tree.complement();

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testComplement_singleCut_3_oe() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, root.getMinus().getLocation());
    }

    @Test
    void testComplement_singleCut_4_oe() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, root.getPlus().getLocation());
    }

    @Test
    void testComplement_singleCut_5_oe() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(0, 1)));
    }

    @Test
    void testComplement_singleCut_6_oe() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplement_singleCut_7_oe() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(0, -1)));
    }

    @Test
    void testComplement_skewedBowtie_1_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testComplement_skewedBowtie_2_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testComplement_skewedBowtie_3_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(3, 1)));
    }

    @Test
    void testComplement_skewedBowtie_4_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-3, -1)));
    }

    @Test
    void testComplement_skewedBowtie_5_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-3, 1)));
    }

    @Test
    void testComplement_skewedBowtie_6_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(3, -1)));
    }

    @Test
    void testComplement_skewedBowtie_7_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 5)));
    }

    @Test
    void testComplement_skewedBowtie_8_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, -5)));
    }

    @Test
    void testComplement_skewedBowtie_9_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(5, 0)));
    }

    @Test
    void testComplement_skewedBowtie_10_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 0)));
    }

    @Test
    void testComplement_skewedBowtie_11_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(3, 0)));
    }

    @Test
    void testComplement_skewedBowtie_12_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(2, 0)));
    }

    @Test
    void testComplement_skewedBowtie_13_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(1, 0)));
    }

    @Test
    void testComplement_skewedBowtie_14_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
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
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(0, 0)));
    }

    @Test
    void testComplement_skewedBowtie_15_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-1, 0)));
    }

    @Test
    void testComplement_skewedBowtie_16_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-2, 0)));
    }

    @Test
    void testComplement_skewedBowtie_17_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-3, 0)));
    }

    @Test
    void testComplement_skewedBowtie_18_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, 0)));
    }

    @Test
    void testComplement_skewedBowtie_19_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
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
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-5, 0)));
    }

    @Test
    void testComplement_addCutAfterComplement_1_oe() {
        // arrange
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        tree.complement();

        // act
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // assert
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testComplement_addCutAfterComplement_2_oe() {
        // arrange
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        tree.complement();

        // act
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testComplement_addCutAfterComplement_3_oe() {
        // arrange
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        tree.complement();

        // act
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplement_addCutAfterComplement_4_oe() {
        // arrange
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        tree.complement();

        // act
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(1, 1)));
    }

    @Test
    void testComplement_addCutAfterComplement_5_oe() {
        // arrange
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        tree.complement();

        // act
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-1, 1)));
    }

    @Test
    void testComplement_addCutAfterComplement_6_oe() {
        // arrange
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        tree.complement();

        // act
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(1, -1)));
    }

    @Test
    void testComplement_addCutAfterComplement_7_oe() {
        // arrange
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        tree.complement();

        // act
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-1, -1)));
    }

    @Test
    void testComplement_clearCutAfterComplement_1_oe() {
        // arrange
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        // act
        root.getMinus().clearCut();

        // assert
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testComplement_clearCutAfterComplement_2_oe() {
        // arrange
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        // act
        root.getMinus().clearCut();

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testComplement_clearCutAfterComplement_3_oe() {
        // arrange
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        // act
        root.getMinus().clearCut();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplement_clearCutAfterComplement_4_oe() {
        // arrange
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        // act
        root.getMinus().clearCut();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(1, 1)));
    }

    @Test
    void testComplement_clearCutAfterComplement_5_oe() {
        // arrange
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        // act
        root.getMinus().clearCut();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-1, 1)));
    }

    @Test
    void testComplement_clearCutAfterComplement_6_oe() {
        // arrange
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        // act
        root.getMinus().clearCut();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(1, -1)));
    }

    @Test
    void testComplement_clearCutAfterComplement_7_oe() {
        // arrange
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        // act
        root.getMinus().clearCut();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-1, -1)));
    }

    @Test
    void testComplement_clearRootAfterComplement_1_oe() {
        // arrange
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        // act
        root.clearCut();

        // assert
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testComplement_clearRootAfterComplement_2_oe() {
        // arrange
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        // act
        root.clearCut();

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testComplement_clearRootAfterComplement_3_oe() {
        // arrange
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        // act
        root.clearCut();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplement_isReversible_root_1_oe() {
        // act
        tree.complement();
        tree.complement();

        // assert
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testComplement_isReversible_root_2_oe() {
        // act
        tree.complement();
        tree.complement();

        // assert
        // removed other assertion
        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testComplement_isReversible_root_3_oe() {
        // act
        tree.complement();
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
    }

    @Test
    void testComplement_isReversible_root_4_oe() {
        // act
        tree.complement();
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_1_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(3, 1)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_2_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-3, -1)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_3_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-3, 1)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_4_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(3, -1)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_5_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 5)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_6_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, -5)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_7_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(5, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_8_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_9_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(3, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_10_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(2, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_11_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(1, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_12_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
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
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(0, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_13_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-1, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_14_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-2, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_15_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-3, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_16_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_17_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
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
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-5, 0)));
    }

    @Test
    void testComplement_getCutBoundary_1_oe() {
        // arrange
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))));
        tree.complement();

        // act
        final RegionCutBoundary<TestPoint2D> xAxisBoundary = root.getCutBoundary();
        final RegionCutBoundary<TestPoint2D> yAxisBoundary = root.getMinus().getCutBoundary();

        // assert
        Assertions.assertTrue(xAxisBoundary.getOutsideFacing().isEmpty());
    }

    @Test
    void testComplement_getCutBoundary_2_oe() {
        // arrange
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))));
        tree.complement();

        // act
        final RegionCutBoundary<TestPoint2D> xAxisBoundary = root.getCutBoundary();
        final RegionCutBoundary<TestPoint2D> yAxisBoundary = root.getMinus().getCutBoundary();

        // assert
        // removed other assertion
        Assertions.assertFalse(xAxisBoundary.getInsideFacing().isEmpty());
    }

    @Test
    void testComplement_getCutBoundary_3_oe() {
        // arrange
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))));
        tree.complement();

        // act
        final RegionCutBoundary<TestPoint2D> xAxisBoundary = root.getCutBoundary();
        final RegionCutBoundary<TestPoint2D> yAxisBoundary = root.getMinus().getCutBoundary();

        // assert
        // removed other assertion
        // removed other assertion

        final List<HyperplaneConvexSubset<TestPoint2D>> xAxisInsideFacing = xAxisBoundary.getInsideFacing();
        Assertions.assertEquals(1, xAxisInsideFacing.size());
    }

    @Test
    void testComplement_getCutBoundary_6_oe() {
        // arrange
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))));
        tree.complement();

        // act
        final RegionCutBoundary<TestPoint2D> xAxisBoundary = root.getCutBoundary();
        final RegionCutBoundary<TestPoint2D> yAxisBoundary = root.getMinus().getCutBoundary();

        // assert
        // removed other assertion
        // removed other assertion

        final List<HyperplaneConvexSubset<TestPoint2D>> xAxisInsideFacing = xAxisBoundary.getInsideFacing();
        // removed other assertion

        final TestLineSegment xAxisSeg = (TestLineSegment) xAxisInsideFacing.get(0);
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(yAxisBoundary.getOutsideFacing().isEmpty());
    }

    @Test
    void testComplement_getCutBoundary_7_oe() {
        // arrange
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))));
        tree.complement();

        // act
        final RegionCutBoundary<TestPoint2D> xAxisBoundary = root.getCutBoundary();
        final RegionCutBoundary<TestPoint2D> yAxisBoundary = root.getMinus().getCutBoundary();

        // assert
        // removed other assertion
        // removed other assertion

        final List<HyperplaneConvexSubset<TestPoint2D>> xAxisInsideFacing = xAxisBoundary.getInsideFacing();
        // removed other assertion

        final TestLineSegment xAxisSeg = (TestLineSegment) xAxisInsideFacing.get(0);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(yAxisBoundary.getInsideFacing().isEmpty());
    }

    @Test
    void testComplement_getCutBoundary_8_oe() {
        // arrange
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))));
        tree.complement();

        // act
        final RegionCutBoundary<TestPoint2D> xAxisBoundary = root.getCutBoundary();
        final RegionCutBoundary<TestPoint2D> yAxisBoundary = root.getMinus().getCutBoundary();

        // assert
        // removed other assertion
        // removed other assertion

        final List<HyperplaneConvexSubset<TestPoint2D>> xAxisInsideFacing = xAxisBoundary.getInsideFacing();
        // removed other assertion

        final TestLineSegment xAxisSeg = (TestLineSegment) xAxisInsideFacing.get(0);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<HyperplaneConvexSubset<TestPoint2D>> yAxisInsideFacing = yAxisBoundary.getInsideFacing();
        Assertions.assertEquals(1, yAxisInsideFacing.size());
    }

    @Test
    void testComplementOf_rootOnly_1_oe() {
        // arrange
        final TestRegionBSPTree other = fullTree();
        insertSkewedBowtie(other);

        // act
        other.complement(tree);

        // assert
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testComplementOf_rootOnly_2_oe() {
        // arrange
        final TestRegionBSPTree other = fullTree();
        insertSkewedBowtie(other);

        // act
        other.complement(tree);

        // assert
        // removed other assertion
        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testComplementOf_rootOnly_3_oe() {
        // arrange
        final TestRegionBSPTree other = fullTree();
        insertSkewedBowtie(other);

        // act
        other.complement(tree);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
    }

    @Test
    void testComplementOf_rootOnly_4_oe() {
        // arrange
        final TestRegionBSPTree other = fullTree();
        insertSkewedBowtie(other);

        // act
        other.complement(tree);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplementOf_rootOnly_5_oe() {
        // arrange
        final TestRegionBSPTree other = fullTree();
        insertSkewedBowtie(other);

        // act
        other.complement(tree);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(other.isEmpty());
    }

    @Test
    void testComplementOf_rootOnly_6_oe() {
        // arrange
        final TestRegionBSPTree other = fullTree();
        insertSkewedBowtie(other);

        // act
        other.complement(tree);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(other.isFull());
    }

    @Test
    void testComplementOf_rootOnly_7_oe() {
        // arrange
        final TestRegionBSPTree other = fullTree();
        insertSkewedBowtie(other);

        // act
        other.complement(tree);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, other.getRoot().getLocation());
    }

    @Test
    void testComplementOf_rootOnly_8_oe() {
        // arrange
        final TestRegionBSPTree other = fullTree();
        insertSkewedBowtie(other);

        // act
        other.complement(tree);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.OUTSIDE, other.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplementOf_skewedBowtie_1_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(3, 1)));
    }

    @Test
    void testComplementOf_skewedBowtie_2_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-3, -1)));
    }

    @Test
    void testComplementOf_skewedBowtie_3_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(other.isEmpty());
    }

    @Test
    void testComplementOf_skewedBowtie_4_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(other.isFull());
    }

    @Test
    void testComplementOf_skewedBowtie_5_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, other.classify(new TestPoint2D(3, 1)));
    }

    @Test
    void testComplementOf_skewedBowtie_6_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.OUTSIDE, other.classify(new TestPoint2D(-3, -1)));
    }

    @Test
    void testComplementOf_skewedBowtie_7_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.INSIDE, other.classify(new TestPoint2D(-3, 1)));
    }

    @Test
    void testComplementOf_skewedBowtie_8_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, other.classify(new TestPoint2D(3, -1)));
    }

    @Test
    void testComplementOf_skewedBowtie_9_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(4, 5)));
    }

    @Test
    void testComplementOf_skewedBowtie_10_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(-4, -5)));
    }

    @Test
    void testComplementOf_skewedBowtie_11_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
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

        Assertions.assertEquals(RegionLocation.INSIDE, other.classify(new TestPoint2D(5, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_12_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(4, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_13_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(3, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_14_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(2, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_15_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(1, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_16_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
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
        Assertions.assertEquals(RegionLocation.OUTSIDE, other.classify(new TestPoint2D(0, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_17_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(-1, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_18_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(-2, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_19_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(-3, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_20_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
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
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(-4, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_21_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
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
        Assertions.assertEquals(RegionLocation.INSIDE, other.classify(new TestPoint2D(-5, 0)));
    }

    @Test
    void testCopy_1_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        final TestRegionBSPTree copy = fullTree();
        copy.copy(tree);

        // assert
        Assertions.assertNotSame(tree, copy);
    }

    @Test
    void testCopy_2_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        final TestRegionBSPTree copy = fullTree();
        copy.copy(tree);

        // assert
        // removed other assertion
        Assertions.assertEquals(tree.count(), copy.count());
    }

    @Test
    void testCopy_3_oe() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        final TestRegionBSPTree copy = fullTree();
        copy.copy(tree);

        // assert
        // removed other assertion
        // removed other assertion

        final List<RegionLocation> origLocations = new ArrayList<>();
        tree.nodes().forEach(n -> origLocations.add(n.getLocation()));

        final List<RegionLocation> copyLocations = new ArrayList<>();
        copy.nodes().forEach(n -> copyLocations.add(n.getLocation()));

        Assertions.assertEquals(origLocations, copyLocations);
    }

    @Test
    void testProject_emptyAndFull_1_oe() {
        // arrange
        final TestRegionBSPTree full = fullTree();
        final TestRegionBSPTree empty = emptyTree();

        // act/assert
        Assertions.assertNull(full.project(new TestPoint2D(0, 0)));
    }

    @Test
    void testProject_emptyAndFull_2_oe() {
        // arrange
        final TestRegionBSPTree full = fullTree();
        final TestRegionBSPTree empty = emptyTree();

        // act/assert
        // removed other assertion
        Assertions.assertNull(empty.project(new TestPoint2D(-1, 1)));
    }

    @Test
    void testSplit_empty_1_oe() {
        // arrange
        tree = emptyTree();

        // act
        final Split<TestRegionBSPTree> split = tree.split(TestLine.X_AXIS);

        // assert
        Assertions.assertEquals(SplitLocation.NEITHER, split.getLocation());
    }

    @Test
    void testSplit_empty_2_oe() {
        // arrange
        tree = emptyTree();

        // act
        final Split<TestRegionBSPTree> split = tree.split(TestLine.X_AXIS);

        // assert
        // removed other assertion

        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_empty_3_oe() {
        // arrange
        tree = emptyTree();

        // act
        final Split<TestRegionBSPTree> split = tree.split(TestLine.X_AXIS);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_full_1_oe() {
        // arrange
        tree = fullTree();

        // act
        final Split<TestRegionBSPTree> split = tree.split(TestLine.X_AXIS);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_halfSpace_1_oe() {
        // arrange
        tree.getRoot().insertCut(TestLine.X_AXIS);

        final TestLine splitter = TestLine.Y_AXIS;

        // act
        final Split<TestRegionBSPTree> split = tree.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_box_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, 1));

        // act
        final Split<TestRegionBSPTree> split = tree.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_box_onMinusOnly_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(2, 0), new TestPoint2D(1, 1));

        // act
        final Split<TestRegionBSPTree> split = tree.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_box_onMinusOnly_4_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(2, 0), new TestPoint2D(1, 1));

        // act
        final Split<TestRegionBSPTree> split = tree.split(splitter);

        // assert
        // removed other assertion

        final TestRegionBSPTree minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_box_onPlusOnly_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(-1, 1));

        // act
        final Split<TestRegionBSPTree> split = tree.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_box_onPlusOnly_2_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(-1, 1));

        // act
        final Split<TestRegionBSPTree> split = tree.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testToString_1_oe() {
        // arrange
        tree.getRoot().cut(TestLine.X_AXIS);

        // act
        final String str = tree.toString();

        // assert
        Assertions.assertEquals("TestRegionBSPTree[count= 3, height= 1]", str);
    }

    @Test
    void testToString_2_oe() {
        // arrange
        tree.getRoot().cut(TestLine.X_AXIS);

        // act
        final String str = tree.toString();

        // assert
        // removed other assertion
        Assertions.assertTrue(tree.getRoot().toString().contains("TestRegionNode"));
    }

    @Test
    void testBoundaries_finite_2_oe_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        // removed other assertion

                final List<TestLineSegment> boundaries0 = segments;
        final TestPoint2D start0 = new TestPoint2D(0, 0);
        final TestPoint2D end0 = new TestPoint2D(1, 0);
        boolean found0 = false;
                for (final TestLineSegment seg0 : boundaries0) {
                    final TestPoint2D startPt0 = seg0.getStartPoint();
                    final TestPoint2D endPt0 = seg0.getEndPoint();
        
                    if (PartitionTestUtils.PRECISION.eq(start0.getX(), startPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(start0.getY(), startPt0.getY()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getX(), endPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getY(), endPt0.getY())) {
                        found0 = true;
                        break;
                    }
                }
        
                Assertions.assertTrue(found0, "Expected to find segment start0= " + start0 + ", end0= " + end0);
    }

    @Test
    void testBoundaries_finite_3_oe_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        // removed other assertion

        // removed other assertion
                final List<TestLineSegment> boundaries0 = segments;
        final TestPoint2D start0 = new TestPoint2D(1, 0);
        final TestPoint2D end0 = new TestPoint2D(1, 1);
        boolean found0 = false;
                for (final TestLineSegment seg0 : boundaries0) {
                    final TestPoint2D startPt0 = seg0.getStartPoint();
                    final TestPoint2D endPt0 = seg0.getEndPoint();
        
                    if (PartitionTestUtils.PRECISION.eq(start0.getX(), startPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(start0.getY(), startPt0.getY()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getX(), endPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getY(), endPt0.getY())) {
                        found0 = true;
                        break;
                    }
                }
        
                Assertions.assertTrue(found0, "Expected to find segment start0= " + start0 + ", end0= " + end0);
    }

    @Test
    void testBoundaries_finite_4_oe_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final List<TestLineSegment> boundaries0 = segments;
        final TestPoint2D start0 = new TestPoint2D(1, 1);
        final TestPoint2D end0 = new TestPoint2D(0, 1);
        boolean found0 = false;
                for (final TestLineSegment seg0 : boundaries0) {
                    final TestPoint2D startPt0 = seg0.getStartPoint();
                    final TestPoint2D endPt0 = seg0.getEndPoint();
        
                    if (PartitionTestUtils.PRECISION.eq(start0.getX(), startPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(start0.getY(), startPt0.getY()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getX(), endPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getY(), endPt0.getY())) {
                        found0 = true;
                        break;
                    }
                }
        
                Assertions.assertTrue(found0, "Expected to find segment start0= " + start0 + ", end0= " + end0);
    }

    @Test
    void testBoundaries_finite_5_oe_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final List<TestLineSegment> boundaries0 = segments;
        final TestPoint2D start0 = new TestPoint2D(0, 1);
        final TestPoint2D end0 = new TestPoint2D(0, 0);
        boolean found0 = false;
                for (final TestLineSegment seg0 : boundaries0) {
                    final TestPoint2D startPt0 = seg0.getStartPoint();
                    final TestPoint2D endPt0 = seg0.getEndPoint();
        
                    if (PartitionTestUtils.PRECISION.eq(start0.getX(), startPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(start0.getY(), startPt0.getY()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getX(), endPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getY(), endPt0.getY())) {
                        found0 = true;
                        break;
                    }
                }
        
                Assertions.assertTrue(found0, "Expected to find segment start0= " + start0 + ", end0= " + end0);
    }

    @Test
    void testBoundaries_finite_inverted_2_oe_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        // removed other assertion

                final List<TestLineSegment> boundaries0 = segments;
        final TestPoint2D start0 = new TestPoint2D(0, 0);
        final TestPoint2D end0 = new TestPoint2D(0, 1);
        boolean found0 = false;
                for (final TestLineSegment seg0 : boundaries0) {
                    final TestPoint2D startPt0 = seg0.getStartPoint();
                    final TestPoint2D endPt0 = seg0.getEndPoint();
        
                    if (PartitionTestUtils.PRECISION.eq(start0.getX(), startPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(start0.getY(), startPt0.getY()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getX(), endPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getY(), endPt0.getY())) {
                        found0 = true;
                        break;
                    }
                }
        
                Assertions.assertTrue(found0, "Expected to find segment start0= " + start0 + ", end0= " + end0);
    }

    @Test
    void testBoundaries_finite_inverted_3_oe_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        // removed other assertion

        // removed other assertion
                final List<TestLineSegment> boundaries0 = segments;
        final TestPoint2D start0 = new TestPoint2D(0, 1);
        final TestPoint2D end0 = new TestPoint2D(1, 1);
        boolean found0 = false;
                for (final TestLineSegment seg0 : boundaries0) {
                    final TestPoint2D startPt0 = seg0.getStartPoint();
                    final TestPoint2D endPt0 = seg0.getEndPoint();
        
                    if (PartitionTestUtils.PRECISION.eq(start0.getX(), startPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(start0.getY(), startPt0.getY()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getX(), endPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getY(), endPt0.getY())) {
                        found0 = true;
                        break;
                    }
                }
        
                Assertions.assertTrue(found0, "Expected to find segment start0= " + start0 + ", end0= " + end0);
    }

    @Test
    void testBoundaries_finite_inverted_4_oe_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final List<TestLineSegment> boundaries0 = segments;
        final TestPoint2D start0 = new TestPoint2D(1, 1);
        final TestPoint2D end0 = new TestPoint2D(1, 0);
        boolean found0 = false;
                for (final TestLineSegment seg0 : boundaries0) {
                    final TestPoint2D startPt0 = seg0.getStartPoint();
                    final TestPoint2D endPt0 = seg0.getEndPoint();
        
                    if (PartitionTestUtils.PRECISION.eq(start0.getX(), startPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(start0.getY(), startPt0.getY()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getX(), endPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getY(), endPt0.getY())) {
                        found0 = true;
                        break;
                    }
                }
        
                Assertions.assertTrue(found0, "Expected to find segment start0= " + start0 + ", end0= " + end0);
    }

    @Test
    void testBoundaries_finite_inverted_5_oe_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final List<TestLineSegment> boundaries0 = segments;
        final TestPoint2D start0 = new TestPoint2D(1, 0);
        final TestPoint2D end0 = new TestPoint2D(0, 0);
        boolean found0 = false;
                for (final TestLineSegment seg0 : boundaries0) {
                    final TestPoint2D startPt0 = seg0.getStartPoint();
                    final TestPoint2D endPt0 = seg0.getEndPoint();
        
                    if (PartitionTestUtils.PRECISION.eq(start0.getX(), startPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(start0.getY(), startPt0.getY()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getX(), endPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getY(), endPt0.getY())) {
                        found0 = true;
                        break;
                    }
                }
        
                Assertions.assertTrue(found0, "Expected to find segment start0= " + start0 + ", end0= " + end0);
    }

    @Test
    void testGetBoundaries_finite_2_oe_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        // removed other assertion

                final List<TestLineSegment> boundaries0 = segments;
        final TestPoint2D start0 = new TestPoint2D(0, 0);
        final TestPoint2D end0 = new TestPoint2D(1, 0);
        boolean found0 = false;
                for (final TestLineSegment seg0 : boundaries0) {
                    final TestPoint2D startPt0 = seg0.getStartPoint();
                    final TestPoint2D endPt0 = seg0.getEndPoint();
        
                    if (PartitionTestUtils.PRECISION.eq(start0.getX(), startPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(start0.getY(), startPt0.getY()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getX(), endPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getY(), endPt0.getY())) {
                        found0 = true;
                        break;
                    }
                }
        
                Assertions.assertTrue(found0, "Expected to find segment start0= " + start0 + ", end0= " + end0);
    }

    @Test
    void testGetBoundaries_finite_3_oe_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        // removed other assertion

        // removed other assertion
                final List<TestLineSegment> boundaries0 = segments;
        final TestPoint2D start0 = new TestPoint2D(1, 0);
        final TestPoint2D end0 = new TestPoint2D(1, 1);
        boolean found0 = false;
                for (final TestLineSegment seg0 : boundaries0) {
                    final TestPoint2D startPt0 = seg0.getStartPoint();
                    final TestPoint2D endPt0 = seg0.getEndPoint();
        
                    if (PartitionTestUtils.PRECISION.eq(start0.getX(), startPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(start0.getY(), startPt0.getY()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getX(), endPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getY(), endPt0.getY())) {
                        found0 = true;
                        break;
                    }
                }
        
                Assertions.assertTrue(found0, "Expected to find segment start0= " + start0 + ", end0= " + end0);
    }

    @Test
    void testGetBoundaries_finite_4_oe_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final List<TestLineSegment> boundaries0 = segments;
        final TestPoint2D start0 = new TestPoint2D(1, 1);
        final TestPoint2D end0 = new TestPoint2D(0, 1);
        boolean found0 = false;
                for (final TestLineSegment seg0 : boundaries0) {
                    final TestPoint2D startPt0 = seg0.getStartPoint();
                    final TestPoint2D endPt0 = seg0.getEndPoint();
        
                    if (PartitionTestUtils.PRECISION.eq(start0.getX(), startPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(start0.getY(), startPt0.getY()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getX(), endPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getY(), endPt0.getY())) {
                        found0 = true;
                        break;
                    }
                }
        
                Assertions.assertTrue(found0, "Expected to find segment start0= " + start0 + ", end0= " + end0);
    }

    @Test
    void testGetBoundaries_finite_5_oe_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final List<TestLineSegment> boundaries0 = segments;
        final TestPoint2D start0 = new TestPoint2D(0, 1);
        final TestPoint2D end0 = new TestPoint2D(0, 0);
        boolean found0 = false;
                for (final TestLineSegment seg0 : boundaries0) {
                    final TestPoint2D startPt0 = seg0.getStartPoint();
                    final TestPoint2D endPt0 = seg0.getEndPoint();
        
                    if (PartitionTestUtils.PRECISION.eq(start0.getX(), startPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(start0.getY(), startPt0.getY()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getX(), endPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getY(), endPt0.getY())) {
                        found0 = true;
                        break;
                    }
                }
        
                Assertions.assertTrue(found0, "Expected to find segment start0= " + start0 + ", end0= " + end0);
    }

    @Test
    void testGetBoundaries_finite_inverted_2_oe_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        // removed other assertion

                final List<TestLineSegment> boundaries0 = segments;
        final TestPoint2D start0 = new TestPoint2D(0, 0);
        final TestPoint2D end0 = new TestPoint2D(0, 1);
        boolean found0 = false;
                for (final TestLineSegment seg0 : boundaries0) {
                    final TestPoint2D startPt0 = seg0.getStartPoint();
                    final TestPoint2D endPt0 = seg0.getEndPoint();
        
                    if (PartitionTestUtils.PRECISION.eq(start0.getX(), startPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(start0.getY(), startPt0.getY()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getX(), endPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getY(), endPt0.getY())) {
                        found0 = true;
                        break;
                    }
                }
        
                Assertions.assertTrue(found0, "Expected to find segment start0= " + start0 + ", end0= " + end0);
    }

    @Test
    void testGetBoundaries_finite_inverted_3_oe_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        // removed other assertion

        // removed other assertion
                final List<TestLineSegment> boundaries0 = segments;
        final TestPoint2D start0 = new TestPoint2D(0, 1);
        final TestPoint2D end0 = new TestPoint2D(1, 1);
        boolean found0 = false;
                for (final TestLineSegment seg0 : boundaries0) {
                    final TestPoint2D startPt0 = seg0.getStartPoint();
                    final TestPoint2D endPt0 = seg0.getEndPoint();
        
                    if (PartitionTestUtils.PRECISION.eq(start0.getX(), startPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(start0.getY(), startPt0.getY()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getX(), endPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getY(), endPt0.getY())) {
                        found0 = true;
                        break;
                    }
                }
        
                Assertions.assertTrue(found0, "Expected to find segment start0= " + start0 + ", end0= " + end0);
    }

    @Test
    void testGetBoundaries_finite_inverted_4_oe_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final List<TestLineSegment> boundaries0 = segments;
        final TestPoint2D start0 = new TestPoint2D(1, 1);
        final TestPoint2D end0 = new TestPoint2D(1, 0);
        boolean found0 = false;
                for (final TestLineSegment seg0 : boundaries0) {
                    final TestPoint2D startPt0 = seg0.getStartPoint();
                    final TestPoint2D endPt0 = seg0.getEndPoint();
        
                    if (PartitionTestUtils.PRECISION.eq(start0.getX(), startPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(start0.getY(), startPt0.getY()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getX(), endPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getY(), endPt0.getY())) {
                        found0 = true;
                        break;
                    }
                }
        
                Assertions.assertTrue(found0, "Expected to find segment start0= " + start0 + ", end0= " + end0);
    }

    @Test
    void testGetBoundaries_finite_inverted_5_oe_1_oe() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final List<TestLineSegment> boundaries0 = segments;
        final TestPoint2D start0 = new TestPoint2D(1, 0);
        final TestPoint2D end0 = new TestPoint2D(0, 0);
        boolean found0 = false;
                for (final TestLineSegment seg0 : boundaries0) {
                    final TestPoint2D startPt0 = seg0.getStartPoint();
                    final TestPoint2D endPt0 = seg0.getEndPoint();
        
                    if (PartitionTestUtils.PRECISION.eq(start0.getX(), startPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(start0.getY(), startPt0.getY()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getX(), endPt0.getX()) &&
                            PartitionTestUtils.PRECISION.eq(end0.getY(), endPt0.getY())) {
                        found0 = true;
                        break;
                    }
                }
        
                Assertions.assertTrue(found0, "Expected to find segment start0= " + start0 + ", end0= " + end0);
    }

    @Test
    void testGetCutBoundary_singleCut_2_oe_1_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));

        // act
        final RegionCutBoundary<TestPoint2D> boundary = root.getCutBoundary();

        // assert
        // removed other assertion

                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = boundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0);
        final TestPoint2D end0 = new TestPoint2D(Double.POSITIVE_INFINITY, 0.0);
        Assertions.assertFalse(boundaries0.isEmpty(), "Expected boundary to not be empty");
    }

    @Test
    void testGetCutBoundary_singleCut_2_oe_2_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));

        // act
        final RegionCutBoundary<TestPoint2D> boundary = root.getCutBoundary();

        // assert
        // removed other assertion

                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = boundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0);
        final TestPoint2D end0 = new TestPoint2D(Double.POSITIVE_INFINITY, 0.0);
        // removed other assertion
        
                Assertions.assertEquals(1, boundaries0.size());
    }

    @Test
    void testGetCutBoundary_singleCut_2_oe_3_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));

        // act
        final RegionCutBoundary<TestPoint2D> boundary = root.getCutBoundary();

        // assert
        // removed other assertion

                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = boundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0);
        final TestPoint2D end0 = new TestPoint2D(Double.POSITIVE_INFINITY, 0.0);
        // removed other assertion
        
                // removed other assertion
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                PartitionTestUtils.assertPointsEqual(start0, segment0.getStartPoint());
    }

    @Test
    void testGetCutBoundary_singleCut_2_oe_4_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));

        // act
        final RegionCutBoundary<TestPoint2D> boundary = root.getCutBoundary();

        // assert
        // removed other assertion

                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = boundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0);
        final TestPoint2D end0 = new TestPoint2D(Double.POSITIVE_INFINITY, 0.0);
        // removed other assertion
        
                // removed other assertion
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                // removed other assertion
                PartitionTestUtils.assertPointsEqual(end0, segment0.getEndPoint());
    }

    @Test
    void testGetCutBoundary_singleCorner_2_oe_1_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // act/assert
        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();

        // removed other assertion
                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = rootBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0);
        final TestPoint2D end0 = TestPoint2D.ZERO;
        Assertions.assertFalse(boundaries0.isEmpty(), "Expected boundary to not be empty");
    }

    @Test
    void testGetCutBoundary_singleCorner_2_oe_2_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // act/assert
        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();

        // removed other assertion
                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = rootBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0);
        final TestPoint2D end0 = TestPoint2D.ZERO;
        // removed other assertion
        
                Assertions.assertEquals(1, boundaries0.size());
    }

    @Test
    void testGetCutBoundary_singleCorner_2_oe_3_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // act/assert
        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();

        // removed other assertion
                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = rootBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0);
        final TestPoint2D end0 = TestPoint2D.ZERO;
        // removed other assertion
        
                // removed other assertion
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                PartitionTestUtils.assertPointsEqual(start0, segment0.getStartPoint());
    }

    @Test
    void testGetCutBoundary_singleCorner_2_oe_4_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // act/assert
        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();

        // removed other assertion
                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = rootBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0);
        final TestPoint2D end0 = TestPoint2D.ZERO;
        // removed other assertion
        
                // removed other assertion
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                // removed other assertion
                PartitionTestUtils.assertPointsEqual(end0, segment0.getEndPoint());
    }

    @Test
    void testGetCutBoundary_singleCorner_4_oe_1_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // act/assert
        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();

        // removed other assertion
        // removed other assertion

        final RegionCutBoundary<TestPoint2D> childBoundary = tree.getRoot().getMinus().getCutBoundary();
        // removed other assertion
                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = childBoundary.getOutsideFacing();
        final TestPoint2D start0 = TestPoint2D.ZERO;
        final TestPoint2D end0 = new TestPoint2D(0.0, Double.POSITIVE_INFINITY);
        Assertions.assertFalse(boundaries0.isEmpty(), "Expected boundary to not be empty");
    }

    @Test
    void testGetCutBoundary_singleCorner_4_oe_2_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // act/assert
        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();

        // removed other assertion
        // removed other assertion

        final RegionCutBoundary<TestPoint2D> childBoundary = tree.getRoot().getMinus().getCutBoundary();
        // removed other assertion
                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = childBoundary.getOutsideFacing();
        final TestPoint2D start0 = TestPoint2D.ZERO;
        final TestPoint2D end0 = new TestPoint2D(0.0, Double.POSITIVE_INFINITY);
        // removed other assertion
        
                Assertions.assertEquals(1, boundaries0.size());
    }

    @Test
    void testGetCutBoundary_singleCorner_4_oe_3_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // act/assert
        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();

        // removed other assertion
        // removed other assertion

        final RegionCutBoundary<TestPoint2D> childBoundary = tree.getRoot().getMinus().getCutBoundary();
        // removed other assertion
                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = childBoundary.getOutsideFacing();
        final TestPoint2D start0 = TestPoint2D.ZERO;
        final TestPoint2D end0 = new TestPoint2D(0.0, Double.POSITIVE_INFINITY);
        // removed other assertion
        
                // removed other assertion
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                PartitionTestUtils.assertPointsEqual(start0, segment0.getStartPoint());
    }

    @Test
    void testGetCutBoundary_singleCorner_4_oe_4_oe() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // act/assert
        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();

        // removed other assertion
        // removed other assertion

        final RegionCutBoundary<TestPoint2D> childBoundary = tree.getRoot().getMinus().getCutBoundary();
        // removed other assertion
                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = childBoundary.getOutsideFacing();
        final TestPoint2D start0 = TestPoint2D.ZERO;
        final TestPoint2D end0 = new TestPoint2D(0.0, Double.POSITIVE_INFINITY);
        // removed other assertion
        
                // removed other assertion
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                // removed other assertion
                PartitionTestUtils.assertPointsEqual(end0, segment0.getEndPoint());
    }

    @Test
    void testTransform_resetsCutBoundary_2_oe_1_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        // act
        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();

        // assert
        // removed other assertion

                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = origBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(4, 5);
        final TestPoint2D end0 = new TestPoint2D(-1, 0);
        Assertions.assertFalse(boundaries0.isEmpty(), "Expected boundary to not be empty");
    }

    @Test
    void testTransform_resetsCutBoundary_2_oe_2_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        // act
        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();

        // assert
        // removed other assertion

                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = origBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(4, 5);
        final TestPoint2D end0 = new TestPoint2D(-1, 0);
        // removed other assertion
        
                Assertions.assertEquals(1, boundaries0.size());
    }

    @Test
    void testTransform_resetsCutBoundary_2_oe_3_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        // act
        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();

        // assert
        // removed other assertion

                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = origBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(4, 5);
        final TestPoint2D end0 = new TestPoint2D(-1, 0);
        // removed other assertion
        
                // removed other assertion
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                PartitionTestUtils.assertPointsEqual(start0, segment0.getStartPoint());
    }

    @Test
    void testTransform_resetsCutBoundary_2_oe_4_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        // act
        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();

        // assert
        // removed other assertion

                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = origBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(4, 5);
        final TestPoint2D end0 = new TestPoint2D(-1, 0);
        // removed other assertion
        
                // removed other assertion
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                // removed other assertion
                PartitionTestUtils.assertPointsEqual(end0, segment0.getEndPoint());
    }

    @Test
    void testTransform_resetsCutBoundary_3_oe_1_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        // act
        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();

        // assert
        // removed other assertion

        // removed other assertion

                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = resultBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(2, 10);
        final TestPoint2D end0 = new TestPoint2D(-0.5, 5);
        Assertions.assertFalse(boundaries0.isEmpty(), "Expected boundary to not be empty");
    }

    @Test
    void testTransform_resetsCutBoundary_3_oe_2_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        // act
        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();

        // assert
        // removed other assertion

        // removed other assertion

                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = resultBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(2, 10);
        final TestPoint2D end0 = new TestPoint2D(-0.5, 5);
        // removed other assertion
        
                Assertions.assertEquals(1, boundaries0.size());
    }

    @Test
    void testTransform_resetsCutBoundary_3_oe_3_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        // act
        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();

        // assert
        // removed other assertion

        // removed other assertion

                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = resultBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(2, 10);
        final TestPoint2D end0 = new TestPoint2D(-0.5, 5);
        // removed other assertion
        
                // removed other assertion
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                PartitionTestUtils.assertPointsEqual(start0, segment0.getStartPoint());
    }

    @Test
    void testTransform_resetsCutBoundary_3_oe_4_oe() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        // act
        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();

        // assert
        // removed other assertion

        // removed other assertion

                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = resultBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(2, 10);
        final TestPoint2D end0 = new TestPoint2D(-0.5, 5);
        // removed other assertion
        
                // removed other assertion
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                // removed other assertion
                PartitionTestUtils.assertPointsEqual(end0, segment0.getEndPoint());
    }

}
