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
    void testDefaultConstructor() {
        // assert
        Assertions.assertNotNull(root);
        Assertions.assertNull(root.getParent());

        PartitionTestUtils.assertIsLeafNode(root);
        Assertions.assertFalse(root.isPlus());
        Assertions.assertFalse(root.isMinus());

        Assertions.assertSame(tree, root.getTree());

        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
    }

    @Test
    void testParameterizedConstructor_true() {
        // act
        tree = new TestRegionBSPTree(true);
        root = tree.getRoot();

        // assert
        Assertions.assertNotNull(root);
        Assertions.assertNull(root.getParent());

        PartitionTestUtils.assertIsLeafNode(root);
        Assertions.assertFalse(root.isPlus());
        Assertions.assertFalse(root.isMinus());

        Assertions.assertSame(tree, root.getTree());

        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
    }

    @Test
    void testParameterizedConstructor_false() {
        // act
        tree = new TestRegionBSPTree(false);
        root = tree.getRoot();

        // assert
        Assertions.assertNotNull(root);
        Assertions.assertNull(root.getParent());

        PartitionTestUtils.assertIsLeafNode(root);
        Assertions.assertFalse(root.isPlus());
        Assertions.assertFalse(root.isMinus());

        Assertions.assertSame(tree, root.getTree());

        Assertions.assertEquals(RegionLocation.OUTSIDE, root.getLocation());
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
    void testGetLocation_emptyRoot() {
        // act/assert
        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
    }

    @Test
    void testGetLocation_singleCut() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act/assert
        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
        Assertions.assertFalse(root.isInside());
        Assertions.assertFalse(root.isOutside());

        final TestRegionNode minus = root.getMinus();
        Assertions.assertEquals(RegionLocation.INSIDE, minus.getLocation());
        Assertions.assertTrue(minus.isInside());
        Assertions.assertFalse(minus.isOutside());

        final TestRegionNode plus = root.getPlus();
        Assertions.assertEquals(RegionLocation.OUTSIDE, plus.getLocation());
        Assertions.assertFalse(plus.isInside());
        Assertions.assertTrue(plus.isOutside());
    }

    @Test
    void testGetLocation_multipleCuts() {
        // arrange
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, -1))));

        // act/assert
        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());

        final TestRegionNode plus = root.getPlus();
        Assertions.assertEquals(RegionLocation.OUTSIDE, plus.getLocation());

        final TestRegionNode plusPlus = plus.getPlus();
        Assertions.assertEquals(RegionLocation.OUTSIDE, plusPlus.getLocation());

        final TestRegionNode plusMinus = plus.getMinus();
        Assertions.assertEquals(RegionLocation.INSIDE, plusMinus.getLocation());

        final TestRegionNode minus = root.getMinus();
        Assertions.assertEquals(RegionLocation.INSIDE, minus.getLocation());

        final TestRegionNode minusPlus = minus.getPlus();
        Assertions.assertEquals(RegionLocation.OUTSIDE, minusPlus.getLocation());

        final TestRegionNode minusMinus = minus.getMinus();
        Assertions.assertEquals(RegionLocation.INSIDE, minusMinus.getLocation());
    }

    @Test
    void testSetLocation() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span());

        final TestRegionNode node = tree.getRoot().getMinus();

        // act
        node.setLocation(RegionLocation.OUTSIDE);

        // assert
        Assertions.assertEquals(RegionLocation.OUTSIDE, node.getLocation());
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testSetLocation_invalidatesRegionProperties() {
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
    void testSetLocation_noChange_doesNotInvalidateTree() {
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
    void testSetLocation_invalidArgs() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> root.setLocation(null),
                IllegalArgumentException.class, "Invalid node location: null");
        GeometryTestUtils.assertThrowsWithMessage(() -> root.setLocation(RegionLocation.BOUNDARY),
                IllegalArgumentException.class, "Invalid node location: BOUNDARY");
    }

    @Test
    void testCondense() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);
        tree.insert(TestLine.X_AXIS.span(), RegionCutRule.INHERIT);

        // act
        final boolean result = tree.condense();

        // assert
        Assertions.assertTrue(result);

        Assertions.assertEquals(3, tree.count());
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getLocation());
        Assertions.assertEquals(RegionLocation.INSIDE, tree.getRoot().getMinus().getLocation());
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getPlus().getLocation());
    }

    @Test
    void testCondense_alreadyCondensed() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);

        // act
        final boolean result = tree.condense();

        // assert
        Assertions.assertFalse(result);

        Assertions.assertEquals(3, tree.count());
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getLocation());
        Assertions.assertEquals(RegionLocation.INSIDE, tree.getRoot().getMinus().getLocation());
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getPlus().getLocation());
    }

    @Test
    void testCondense_invalidatesTreeWhenChanged() {
        // arrange
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);
        tree.insert(TestLine.X_AXIS.span(), RegionCutRule.INHERIT);

        final RegionSizeProperties<TestPoint2D> prevProps = tree.getRegionSizeProperties();

        // act
        final boolean result = tree.condense();

        // assert
        Assertions.assertTrue(result);

        Assertions.assertNotSame(prevProps, tree.getRegionSizeProperties());
    }

    @Test
    void testCondense_doesNotInvalidateTreeWhenNotChanged() {
        // arrange
        tree = emptyTree();

        final RegionSizeProperties<TestPoint2D> prevProps = tree.getRegionSizeProperties();

        // act
        final boolean result = tree.condense();

        // assert
        Assertions.assertFalse(result);

        Assertions.assertSame(prevProps, tree.getRegionSizeProperties());
    }

    @Test
    void testCut_nodeMethod() {
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
    void testBoundaries_fullAndEmpty() {
        // act/assert
        tree.setFull();
        Assertions.assertFalse(tree.boundaries().iterator().hasNext());

        tree.setEmpty();
        Assertions.assertFalse(tree.boundaries().iterator().hasNext());
    }

    @Test
    void testBoundaries_finite() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        Assertions.assertEquals(4, segments.size());

        assertContainsSegment(segments, new TestPoint2D(0, 0), new TestPoint2D(1, 0));
        assertContainsSegment(segments, new TestPoint2D(1, 0), new TestPoint2D(1, 1));
        assertContainsSegment(segments, new TestPoint2D(1, 1), new TestPoint2D(0, 1));
        assertContainsSegment(segments, new TestPoint2D(0, 1), new TestPoint2D(0, 0));
    }

    @Test
    void testBoundaries_finite_inverted() {
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

        assertContainsSegment(segments, new TestPoint2D(0, 0), new TestPoint2D(0, 1));
        assertContainsSegment(segments, new TestPoint2D(0, 1), new TestPoint2D(1, 1));
        assertContainsSegment(segments, new TestPoint2D(1, 1), new TestPoint2D(1, 0));
        assertContainsSegment(segments, new TestPoint2D(1, 0), new TestPoint2D(0, 0));
    }

    @Test
    void testGetBoundaries_fullAndEmpty() {
        // act/assert
        tree.setFull();
        Assertions.assertEquals(0, tree.getBoundaries().size());

        tree.setEmpty();
        Assertions.assertEquals(0, tree.getBoundaries().size());
    }

    @Test
    void testGetBoundaries_finite() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        // act
        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }

        // assert
        Assertions.assertEquals(4, segments.size());

        assertContainsSegment(segments, new TestPoint2D(0, 0), new TestPoint2D(1, 0));
        assertContainsSegment(segments, new TestPoint2D(1, 0), new TestPoint2D(1, 1));
        assertContainsSegment(segments, new TestPoint2D(1, 1), new TestPoint2D(0, 1));
        assertContainsSegment(segments, new TestPoint2D(0, 1), new TestPoint2D(0, 0));
    }

    @Test
    void testGetBoundaries_finite_inverted() {
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

        assertContainsSegment(segments, new TestPoint2D(0, 0), new TestPoint2D(0, 1));
        assertContainsSegment(segments, new TestPoint2D(0, 1), new TestPoint2D(1, 1));
        assertContainsSegment(segments, new TestPoint2D(1, 1), new TestPoint2D(1, 0));
        assertContainsSegment(segments, new TestPoint2D(1, 0), new TestPoint2D(0, 0));
    }

    @Test
    void testClassify() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(3, 1)));
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-3, -1)));

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-3, 1)));
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(3, -1)));

        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 5)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, -5)));

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(5, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(3, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(2, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(1, 0)));
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(0, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-1, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-2, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-3, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, 0)));
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-5, 0)));
    }

    @Test
    void testClassify_emptyTree() {
        // act/assert
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testClassify_NaN() {
        // act/assert
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(0, Double.NaN)));
    }

    @Test
    void testContains() {
        // arrange
        insertSkewedBowtie(tree);

        // act/assert
        Assertions.assertTrue(tree.contains(new TestPoint2D(3, 1)));
        Assertions.assertTrue(tree.contains(new TestPoint2D(-3, -1)));

        Assertions.assertFalse(tree.contains(new TestPoint2D(-3, 1)));
        Assertions.assertFalse(tree.contains(new TestPoint2D(3, -1)));

        Assertions.assertTrue(tree.contains(new TestPoint2D(4, 5)));
        Assertions.assertTrue(tree.contains(new TestPoint2D(-4, -5)));

        Assertions.assertFalse(tree.contains(new TestPoint2D(5, 0)));

        Assertions.assertTrue(tree.contains(new TestPoint2D(4, 0)));
        Assertions.assertTrue(tree.contains(new TestPoint2D(3, 0)));
        Assertions.assertTrue(tree.contains(new TestPoint2D(2, 0)));
        Assertions.assertTrue(tree.contains(new TestPoint2D(1, 0)));
        Assertions.assertTrue(tree.contains(new TestPoint2D(0, 0)));
        Assertions.assertTrue(tree.contains(new TestPoint2D(-1, 0)));
        Assertions.assertTrue(tree.contains(new TestPoint2D(-2, 0)));
        Assertions.assertTrue(tree.contains(new TestPoint2D(-3, 0)));
        Assertions.assertTrue(tree.contains(new TestPoint2D(-4, 0)));

        Assertions.assertFalse(tree.contains(new TestPoint2D(-5, 0)));
    }

    @Test
    void testSetFull() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.setFull();

        // assert
        Assertions.assertTrue(tree.isFull());
        Assertions.assertFalse(tree.isEmpty());

        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(TestPoint2D.ZERO));
        Assertions.assertTrue(tree.contains(TestPoint2D.ZERO));
    }

    @Test
    void testSetEmpty() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.setEmpty();

        // assert
        Assertions.assertFalse(tree.isFull());
        Assertions.assertTrue(tree.isEmpty());

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(TestPoint2D.ZERO));
        Assertions.assertFalse(tree.contains(TestPoint2D.ZERO));
    }

    @Test
    void testGetRegionSizeProperties_cachesValueBasedOnVersion() {
        // act
        final RegionSizeProperties<TestPoint2D> first = tree.getRegionSizeProperties();
        final RegionSizeProperties<TestPoint2D> second = tree.getRegionSizeProperties();
        tree.getRoot().cut(TestLine.X_AXIS);
        final RegionSizeProperties<TestPoint2D> third = tree.getRegionSizeProperties();

        // assert
        Assertions.assertSame(first, second);
        Assertions.assertNotSame(second, third);

        Assertions.assertEquals(1234, first.getSize(), PartitionTestUtils.EPS);
        PartitionTestUtils.assertPointsEqual(new TestPoint2D(12, 34), first.getCentroid());
    }

    @Test
    void testGetSize() {
        // act/assert
        // make sure our stub value is pulled
        Assertions.assertEquals(1234, tree.getSize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetCentroid() {
        // act/assert
        // make sure our stub value is pulled
        PartitionTestUtils.assertPointsEqual(new TestPoint2D(12, 34), tree.getCentroid());
    }

    @Test
    void testGetBoundarySize_fullAndEmpty() {
        // act/assert
        Assertions.assertEquals(0.0, fullTree().getBoundarySize(), PartitionTestUtils.EPS);
        Assertions.assertEquals(0.0, emptyTree().getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_infinite() {
        // arrange
        final TestRegionBSPTree halfPos = new TestRegionBSPTree(true);
        halfPos.getRoot().cut(TestLine.X_AXIS);

        final TestRegionBSPTree halfPosComplement = new TestRegionBSPTree(true);
        halfPosComplement.complement(halfPos);

        // act/assert
        Assertions.assertEquals(Double.POSITIVE_INFINITY, halfPos.getBoundarySize(), PartitionTestUtils.EPS);
        Assertions.assertEquals(Double.POSITIVE_INFINITY, halfPosComplement.getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_alignedCuts() {
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
    void testGetBoundarySize_box() {
        // arrange
        insertBox(tree, new TestPoint2D(2, 2), new TestPoint2D(4, 1));

        // act/assert
        Assertions.assertEquals(6.0, tree.getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_boxComplement() {
        // arrange
        insertBox(tree, new TestPoint2D(2, 2), new TestPoint2D(4, 1));
        tree.complement();

        // act/assert
        Assertions.assertEquals(6.0, tree.getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_recomputesAfterChange() {
        // arrange
        insertBox(tree, new TestPoint2D(2, 2), new TestPoint2D(4, 1));

        // act
        final double first = tree.getBoundarySize();
        tree.insert(new TestLineSegment(new TestPoint2D(3, 1), new TestPoint2D(3, 2)));

        final double second = tree.getBoundarySize();
        final double third = tree.getBoundarySize();

        // assert
        Assertions.assertEquals(6.0, first, PartitionTestUtils.EPS);
        Assertions.assertEquals(4.0, second, PartitionTestUtils.EPS);
        Assertions.assertEquals(4.0, third, PartitionTestUtils.EPS);
    }

    @Test
    void testGetCutBoundary_emptyTree() {
        // act
        final RegionCutBoundary<TestPoint2D> boundary = root.getCutBoundary();

        // assert
        Assertions.assertNull(boundary);
    }

    @Test
    void testGetCutBoundary_singleCut() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));

        // act
        final RegionCutBoundary<TestPoint2D> boundary = root.getCutBoundary();

        // assert
        Assertions.assertTrue(boundary.getInsideFacing().isEmpty());

        assertCutBoundarySegment(boundary.getOutsideFacing(),
                new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0), new TestPoint2D(Double.POSITIVE_INFINITY, 0.0));
    }

    @Test
    void testGetCutBoundary_singleCut_leafNode() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));

        // act
        final RegionCutBoundary<TestPoint2D> boundary = root.getMinus().getCutBoundary();

        // assert
        Assertions.assertNull(boundary);
    }

    @Test
    void testGetCutBoundary_singleCorner() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // act/assert
        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();

        Assertions.assertTrue(rootBoundary.getInsideFacing().isEmpty());
        assertCutBoundarySegment(rootBoundary.getOutsideFacing(),
                new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0), TestPoint2D.ZERO);

        final RegionCutBoundary<TestPoint2D> childBoundary = tree.getRoot().getMinus().getCutBoundary();
        Assertions.assertTrue(childBoundary.getInsideFacing().isEmpty());
        assertCutBoundarySegment(childBoundary.getOutsideFacing(),
                TestPoint2D.ZERO, new TestPoint2D(0.0, Double.POSITIVE_INFINITY));
    }

    @Test
    void testGetCutBoundary_leafNode() {
        // arrange
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // act/assert
        Assertions.assertNull(root.getPlus().getCutBoundary());
        Assertions.assertNull(root.getMinus().getMinus().getCutHyperplane());
        Assertions.assertNull(root.getMinus().getPlus().getCutHyperplane());
    }

    @Test
    void testFullEmpty_fullTree() {
        // act/assert
        Assertions.assertTrue(tree.isFull());
        Assertions.assertFalse(tree.isEmpty());
        Assertions.assertEquals(RegionLocation.INSIDE, tree.getRoot().getLocation());
    }

    @Test
    void testFullEmpty_emptyTree() {
        // arrange
        tree.complement();

        // act/assert
        Assertions.assertFalse(tree.isFull());
        Assertions.assertTrue(tree.isEmpty());
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getLocation());
    }

    @Test
    void testTransform_noCuts() {
        // arrange
        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), p.getY() + 2));

        // act
        tree.transform(t);

        // assert
        Assertions.assertTrue(tree.isFull());
        Assertions.assertFalse(tree.isEmpty());

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, TestPoint2D.ZERO);
    }

    @Test
    void testTransform_singleCut() {
        // arrange
        tree.getRoot().insertCut(TestLine.X_AXIS);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), p.getY() + 2));

        // act
        tree.transform(t);

        // assert
        Assertions.assertFalse(tree.isFull());
        Assertions.assertFalse(tree.isEmpty());

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                new TestPoint2D(0, -1), TestPoint2D.ZERO, new TestPoint2D(0, 1));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY, new TestPoint2D(0, 2));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE,
                new TestPoint2D(0, 3), new TestPoint2D(0, 4));
    }

    @Test
    void testTransform_multipleCuts() {
        // arrange
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        // act
        tree.transform(t);

        // assert
        Assertions.assertFalse(tree.isFull());
        Assertions.assertFalse(tree.isEmpty());

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE,
                new TestPoint2D(0, 5), new TestPoint2D(-1, 4), new TestPoint2D(1, 6));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY,
                new TestPoint2D(-2, 4), new TestPoint2D(2, 6));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                new TestPoint2D(-3, 5), new TestPoint2D(3, 5));
    }

    @Test
    void testTransform_xAxisReflection() {
        // arrange
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), p.getY()));

        // act
        tree.transform(t);

        // assert
        Assertions.assertFalse(tree.isFull());
        Assertions.assertFalse(tree.isEmpty());

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE,
                TestPoint2D.ZERO, new TestPoint2D(-1, 1), new TestPoint2D(1, -1));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY,
                new TestPoint2D(0, 1), new TestPoint2D(0, -1),
                new TestPoint2D(-4, 0), new TestPoint2D(4, 0));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                new TestPoint2D(1, 1), new TestPoint2D(-1, -1));
    }

    @Test
    void testTransform_yAxisReflection() {
        // arrange
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), -p.getY()));

        // act
        tree.transform(t);

        // assert
        Assertions.assertFalse(tree.isFull());
        Assertions.assertFalse(tree.isEmpty());

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE,
                TestPoint2D.ZERO, new TestPoint2D(1, -1), new TestPoint2D(-1, 1));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY,
                new TestPoint2D(0, 1), new TestPoint2D(0, -1),
                new TestPoint2D(-4, 0), new TestPoint2D(4, 0));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                new TestPoint2D(-1, -1), new TestPoint2D(1, 1));
    }

    @Test
    void testTransform_xAndYAxisReflection() {
        // arrange
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), -p.getY()));

        // act
        tree.transform(t);

        // assert
        Assertions.assertFalse(tree.isFull());
        Assertions.assertFalse(tree.isEmpty());

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE,
                TestPoint2D.ZERO, new TestPoint2D(1, 1), new TestPoint2D(-1, -1));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY,
                new TestPoint2D(0, 1), new TestPoint2D(0, -1),
                new TestPoint2D(-4, 0), new TestPoint2D(4, 0));

        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE,
                new TestPoint2D(-1, 1), new TestPoint2D(1, -1));
    }

    @Test
    void testTransform_resetsCutBoundary() {
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

        assertCutBoundarySegment(origBoundary.getOutsideFacing(), new TestPoint2D(4, 5), new TestPoint2D(-1, 0));

        assertCutBoundarySegment(resultBoundary.getOutsideFacing(), new TestPoint2D(2, 10), new TestPoint2D(-0.5, 5));
    }

    @Test
    void testComplement_rootOnly() {
        // act
        tree.complement();

        // assert
        Assertions.assertTrue(tree.isEmpty());
        Assertions.assertFalse(tree.isFull());

        Assertions.assertEquals(RegionLocation.OUTSIDE, root.getLocation());
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplement_singleCut() {
        // arrange
        root.insertCut(TestLine.X_AXIS);

        // act
        tree.complement();

        // assert
        Assertions.assertFalse(tree.isEmpty());
        Assertions.assertFalse(tree.isFull());

        Assertions.assertEquals(RegionLocation.OUTSIDE, root.getMinus().getLocation());
        Assertions.assertEquals(RegionLocation.INSIDE, root.getPlus().getLocation());

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(0, 1)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(TestPoint2D.ZERO));
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(0, -1)));
    }

    @Test
    void testComplement_skewedBowtie() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();

        // assert
        Assertions.assertFalse(tree.isEmpty());
        Assertions.assertFalse(tree.isFull());

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(3, 1)));
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-3, -1)));

        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-3, 1)));
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(3, -1)));

        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 5)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, -5)));

        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(5, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(3, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(2, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(1, 0)));
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(0, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-1, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-2, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-3, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, 0)));
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-5, 0)));
    }

    @Test
    void testComplement_addCutAfterComplement() {
        // arrange
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        tree.complement();

        // act
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        // assert
        Assertions.assertFalse(tree.isEmpty());
        Assertions.assertFalse(tree.isFull());

        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(TestPoint2D.ZERO));

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(1, 1)));
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-1, 1)));
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(1, -1)));
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-1, -1)));
    }

    @Test
    void testComplement_clearCutAfterComplement() {
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
        Assertions.assertFalse(tree.isFull());

        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(TestPoint2D.ZERO));

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(1, 1)));
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-1, 1)));
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(1, -1)));
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-1, -1)));
    }

    @Test
    void testComplement_clearRootAfterComplement() {
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
        Assertions.assertFalse(tree.isFull());

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplement_isReversible_root() {
        // act
        tree.complement();
        tree.complement();

        // assert
        Assertions.assertFalse(tree.isEmpty());
        Assertions.assertTrue(tree.isFull());

        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplement_isReversible_skewedBowtie() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        tree.complement();
        tree.complement();

        // assert
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(3, 1)));
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-3, -1)));

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-3, 1)));
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(3, -1)));

        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 5)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, -5)));

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(5, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(3, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(2, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(1, 0)));
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(0, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-1, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-2, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-3, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, 0)));
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-5, 0)));
    }

    @Test
    void testComplement_getCutBoundary() {
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
        Assertions.assertFalse(xAxisBoundary.getInsideFacing().isEmpty());

        final List<HyperplaneConvexSubset<TestPoint2D>> xAxisInsideFacing = xAxisBoundary.getInsideFacing();
        Assertions.assertEquals(1, xAxisInsideFacing.size());

        final TestLineSegment xAxisSeg = (TestLineSegment) xAxisInsideFacing.get(0);
        PartitionTestUtils.assertPointsEqual(new TestPoint2D(Double.NEGATIVE_INFINITY, 0), xAxisSeg.getStartPoint());
        PartitionTestUtils.assertPointsEqual(TestPoint2D.ZERO, xAxisSeg.getEndPoint());

        Assertions.assertTrue(yAxisBoundary.getOutsideFacing().isEmpty());
        Assertions.assertFalse(yAxisBoundary.getInsideFacing().isEmpty());

        final List<HyperplaneConvexSubset<TestPoint2D>> yAxisInsideFacing = yAxisBoundary.getInsideFacing();
        Assertions.assertEquals(1, yAxisInsideFacing.size());

        final TestLineSegment yAxisSeg = (TestLineSegment) yAxisInsideFacing.get(0);
        PartitionTestUtils.assertPointsEqual(TestPoint2D.ZERO, yAxisSeg.getStartPoint());
        PartitionTestUtils.assertPointsEqual(new TestPoint2D(0, Double.POSITIVE_INFINITY), yAxisSeg.getEndPoint());
    }

    @Test
    void testComplementOf_rootOnly() {
        // arrange
        final TestRegionBSPTree other = fullTree();
        insertSkewedBowtie(other);

        // act
        other.complement(tree);

        // assert
        Assertions.assertFalse(tree.isEmpty());
        Assertions.assertTrue(tree.isFull());

        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(TestPoint2D.ZERO));

        Assertions.assertTrue(other.isEmpty());
        Assertions.assertFalse(other.isFull());

        Assertions.assertEquals(RegionLocation.OUTSIDE, other.getRoot().getLocation());
        Assertions.assertEquals(RegionLocation.OUTSIDE, other.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplementOf_skewedBowtie() {
        // arrange
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        // act
        other.complement(tree);

        // assert
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(3, 1)));
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-3, -1)));

        Assertions.assertFalse(other.isEmpty());
        Assertions.assertFalse(other.isFull());

        Assertions.assertEquals(RegionLocation.OUTSIDE, other.classify(new TestPoint2D(3, 1)));
        Assertions.assertEquals(RegionLocation.OUTSIDE, other.classify(new TestPoint2D(-3, -1)));

        Assertions.assertEquals(RegionLocation.INSIDE, other.classify(new TestPoint2D(-3, 1)));
        Assertions.assertEquals(RegionLocation.INSIDE, other.classify(new TestPoint2D(3, -1)));

        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(4, 5)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(-4, -5)));

        Assertions.assertEquals(RegionLocation.INSIDE, other.classify(new TestPoint2D(5, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(4, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(3, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(2, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(1, 0)));
        Assertions.assertEquals(RegionLocation.OUTSIDE, other.classify(new TestPoint2D(0, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(-1, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(-2, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(-3, 0)));
        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(-4, 0)));
        Assertions.assertEquals(RegionLocation.INSIDE, other.classify(new TestPoint2D(-5, 0)));
    }

    @Test
    void testCopy() {
        // arrange
        insertSkewedBowtie(tree);

        // act
        final TestRegionBSPTree copy = fullTree();
        copy.copy(tree);

        // assert
        Assertions.assertNotSame(tree, copy);
        Assertions.assertEquals(tree.count(), copy.count());

        final List<RegionLocation> origLocations = new ArrayList<>();
        tree.nodes().forEach(n -> origLocations.add(n.getLocation()));

        final List<RegionLocation> copyLocations = new ArrayList<>();
        copy.nodes().forEach(n -> copyLocations.add(n.getLocation()));

        Assertions.assertEquals(origLocations, copyLocations);
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
    void testProject_emptyAndFull() {
        // arrange
        final TestRegionBSPTree full = fullTree();
        final TestRegionBSPTree empty = emptyTree();

        // act/assert
        Assertions.assertNull(full.project(new TestPoint2D(0, 0)));
        Assertions.assertNull(empty.project(new TestPoint2D(-1, 1)));
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

    @Test
    void testSplit_empty() {
        // arrange
        tree = emptyTree();

        // act
        final Split<TestRegionBSPTree> split = tree.split(TestLine.X_AXIS);

        // assert
        Assertions.assertEquals(SplitLocation.NEITHER, split.getLocation());

        Assertions.assertNull(split.getMinus());
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_full() {
        // arrange
        tree = fullTree();

        // act
        final Split<TestRegionBSPTree> split = tree.split(TestLine.X_AXIS);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());

        final TestRegionBSPTree minus = split.getMinus();
        PartitionTestUtils.assertPointLocations(minus, RegionLocation.INSIDE,
                new TestPoint2D(-1, 1), new TestPoint2D(0, 1), new TestPoint2D(1, 1));
        PartitionTestUtils.assertPointLocations(minus, RegionLocation.BOUNDARY,
                new TestPoint2D(-1, 0), new TestPoint2D(0, 0), new TestPoint2D(1, 0));
        PartitionTestUtils.assertPointLocations(minus, RegionLocation.OUTSIDE,
                new TestPoint2D(-1, -1), new TestPoint2D(0, -1), new TestPoint2D(1, -1));

        final TestRegionBSPTree plus = split.getPlus();
        PartitionTestUtils.assertPointLocations(plus, RegionLocation.OUTSIDE,
                new TestPoint2D(-1, 1), new TestPoint2D(0, 1), new TestPoint2D(1, 1));
        PartitionTestUtils.assertPointLocations(plus, RegionLocation.BOUNDARY,
                new TestPoint2D(-1, 0), new TestPoint2D(0, 0), new TestPoint2D(1, 0));
        PartitionTestUtils.assertPointLocations(plus, RegionLocation.INSIDE,
                new TestPoint2D(-1, -1), new TestPoint2D(0, -1), new TestPoint2D(1, -1));
    }

    @Test
    void testSplit_halfSpace() {
        // arrange
        tree.getRoot().insertCut(TestLine.X_AXIS);

        final TestLine splitter = TestLine.Y_AXIS;

        // act
        final Split<TestRegionBSPTree> split = tree.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());

        final TestRegionBSPTree minus = split.getMinus();
        PartitionTestUtils.assertPointLocations(minus, RegionLocation.INSIDE, new TestPoint2D(-1, 1));
        PartitionTestUtils.assertPointLocations(minus, RegionLocation.OUTSIDE,
                new TestPoint2D(1, 1), new TestPoint2D(-1, -1), new TestPoint2D(1, -1));

        final TestRegionBSPTree plus = split.getPlus();
        PartitionTestUtils.assertPointLocations(plus, RegionLocation.INSIDE, new TestPoint2D(1, 1));
        PartitionTestUtils.assertPointLocations(plus, RegionLocation.OUTSIDE,
                new TestPoint2D(-1, 1), new TestPoint2D(-1, -1), new TestPoint2D(1, -1));
    }

    @Test
    void testSplit_box() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, 1));

        // act
        final Split<TestRegionBSPTree> split = tree.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());

        final TestRegionBSPTree minus = split.getMinus();
        PartitionTestUtils.assertPointLocations(minus, RegionLocation.INSIDE, new TestPoint2D(0.25, 0.25));
        PartitionTestUtils.assertPointLocations(minus, RegionLocation.BOUNDARY,
                new TestPoint2D(0.5, 0), new TestPoint2D(0, 0.5));
        PartitionTestUtils.assertPointLocations(minus, RegionLocation.OUTSIDE,
                new TestPoint2D(1, 0.5), new TestPoint2D(0.5, 1), new TestPoint2D(0.75, 0.75));

        final TestRegionBSPTree plus = split.getPlus();
        PartitionTestUtils.assertPointLocations(plus, RegionLocation.INSIDE, new TestPoint2D(0.75, 0.75));
        PartitionTestUtils.assertPointLocations(plus, RegionLocation.OUTSIDE,
                new TestPoint2D(0.5, 0), new TestPoint2D(0, 0.5), new TestPoint2D(0.25, 0.25));
        PartitionTestUtils.assertPointLocations(plus, RegionLocation.BOUNDARY,
                new TestPoint2D(1, 0.5), new TestPoint2D(0.5, 1));
    }

    @Test
    void testSplit_box_onMinusOnly() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(2, 0), new TestPoint2D(1, 1));

        // act
        final Split<TestRegionBSPTree> split = tree.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());

        final TestRegionBSPTree minus = split.getMinus();
        PartitionTestUtils.assertPointLocations(minus, RegionLocation.INSIDE, new TestPoint2D(0.5, 0.5));
        PartitionTestUtils.assertPointLocations(minus, RegionLocation.BOUNDARY,
                new TestPoint2D(0.5, 0), new TestPoint2D(0, 0.5),
                new TestPoint2D(1, 0.5), new TestPoint2D(0.5, 1));

        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_box_onPlusOnly() {
        // arrange
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(-1, 1));

        // act
        final Split<TestRegionBSPTree> split = tree.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());

        Assertions.assertNull(split.getMinus());

        final TestRegionBSPTree plus = split.getPlus();
        PartitionTestUtils.assertPointLocations(plus, RegionLocation.INSIDE, new TestPoint2D(0.5, 0.5));
        PartitionTestUtils.assertPointLocations(plus, RegionLocation.BOUNDARY,
                new TestPoint2D(0.5, 0), new TestPoint2D(0, 0.5),
                new TestPoint2D(1, 0.5), new TestPoint2D(0.5, 1));
    }

    @Test
    void testToString() {
        // arrange
        tree.getRoot().cut(TestLine.X_AXIS);

        // act
        final String str = tree.toString();

        // assert
        Assertions.assertEquals("TestRegionBSPTree[count= 3, height= 1]", str);
        Assertions.assertTrue(tree.getRoot().toString().contains("TestRegionNode"));
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
        Assertions.assertNotNull(root);
    }

    @Test
    void testDefaultConstructor_2_oe() {
        Assertions.assertNull(root.getParent());
    }

    @Test
    void testDefaultConstructor_4_oe() {

        Assertions.assertFalse(root.isPlus());
    }

    @Test
    void testDefaultConstructor_5_oe() {

        Assertions.assertFalse(root.isMinus());
    }

    @Test
    void testDefaultConstructor_6_oe() {


        Assertions.assertSame(tree, root.getTree());
    }

    @Test
    void testDefaultConstructor_7_oe() {



        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
    }

    @Test
    void testParameterizedConstructor_true_1_oe() {
        tree = new TestRegionBSPTree(true);
        root = tree.getRoot();

        Assertions.assertNotNull(root);
    }

    @Test
    void testParameterizedConstructor_true_2_oe() {
        tree = new TestRegionBSPTree(true);
        root = tree.getRoot();

        Assertions.assertNull(root.getParent());
    }

    @Test
    void testParameterizedConstructor_true_4_oe() {
        tree = new TestRegionBSPTree(true);
        root = tree.getRoot();


        Assertions.assertFalse(root.isPlus());
    }

    @Test
    void testParameterizedConstructor_true_5_oe() {
        tree = new TestRegionBSPTree(true);
        root = tree.getRoot();


        Assertions.assertFalse(root.isMinus());
    }

    @Test
    void testParameterizedConstructor_true_6_oe() {
        tree = new TestRegionBSPTree(true);
        root = tree.getRoot();



        Assertions.assertSame(tree, root.getTree());
    }

    @Test
    void testParameterizedConstructor_true_7_oe() {
        tree = new TestRegionBSPTree(true);
        root = tree.getRoot();




        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
    }

    @Test
    void testParameterizedConstructor_false_1_oe() {
        tree = new TestRegionBSPTree(false);
        root = tree.getRoot();

        Assertions.assertNotNull(root);
    }

    @Test
    void testParameterizedConstructor_false_2_oe() {
        tree = new TestRegionBSPTree(false);
        root = tree.getRoot();

        Assertions.assertNull(root.getParent());
    }

    @Test
    void testParameterizedConstructor_false_4_oe() {
        tree = new TestRegionBSPTree(false);
        root = tree.getRoot();


        Assertions.assertFalse(root.isPlus());
    }

    @Test
    void testParameterizedConstructor_false_5_oe() {
        tree = new TestRegionBSPTree(false);
        root = tree.getRoot();


        Assertions.assertFalse(root.isMinus());
    }

    @Test
    void testParameterizedConstructor_false_6_oe() {
        tree = new TestRegionBSPTree(false);
        root = tree.getRoot();



        Assertions.assertSame(tree, root.getTree());
    }

    @Test
    void testParameterizedConstructor_false_7_oe() {
        tree = new TestRegionBSPTree(false);
        root = tree.getRoot();




        Assertions.assertEquals(RegionLocation.OUTSIDE, root.getLocation());
    }

    @Test
    void testGetLocation_emptyRoot_1_oe() {
        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
    }

    @Test
    void testGetLocation_singleCut_1_oe() {
        root.insertCut(TestLine.X_AXIS);

        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
    }

    @Test
    void testGetLocation_singleCut_2_oe() {
        root.insertCut(TestLine.X_AXIS);

        Assertions.assertFalse(root.isInside());
    }

    @Test
    void testGetLocation_singleCut_3_oe() {
        root.insertCut(TestLine.X_AXIS);

        Assertions.assertFalse(root.isOutside());
    }

    @Test
    void testGetLocation_singleCut_4_oe() {
        root.insertCut(TestLine.X_AXIS);


        final TestRegionNode minus = root.getMinus();
        Assertions.assertEquals(RegionLocation.INSIDE, minus.getLocation());
    }

    @Test
    void testGetLocation_singleCut_5_oe() {
        root.insertCut(TestLine.X_AXIS);


        final TestRegionNode minus = root.getMinus();
        Assertions.assertTrue(minus.isInside());
    }

    @Test
    void testGetLocation_singleCut_6_oe() {
        root.insertCut(TestLine.X_AXIS);


        final TestRegionNode minus = root.getMinus();
        Assertions.assertFalse(minus.isOutside());
    }

    @Test
    void testGetLocation_singleCut_7_oe() {
        root.insertCut(TestLine.X_AXIS);


        final TestRegionNode minus = root.getMinus();

        final TestRegionNode plus = root.getPlus();
        Assertions.assertEquals(RegionLocation.OUTSIDE, plus.getLocation());
    }

    @Test
    void testGetLocation_singleCut_8_oe() {
        root.insertCut(TestLine.X_AXIS);


        final TestRegionNode minus = root.getMinus();

        final TestRegionNode plus = root.getPlus();
        Assertions.assertFalse(plus.isInside());
    }

    @Test
    void testGetLocation_singleCut_9_oe() {
        root.insertCut(TestLine.X_AXIS);


        final TestRegionNode minus = root.getMinus();

        final TestRegionNode plus = root.getPlus();
        Assertions.assertTrue(plus.isOutside());
    }

    @Test
    void testGetLocation_multipleCuts_1_oe() {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, -1))));

        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
    }

    @Test
    void testGetLocation_multipleCuts_2_oe() {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, -1))));


        final TestRegionNode plus = root.getPlus();
        Assertions.assertEquals(RegionLocation.OUTSIDE, plus.getLocation());
    }

    @Test
    void testGetLocation_multipleCuts_3_oe() {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, -1))));


        final TestRegionNode plus = root.getPlus();

        final TestRegionNode plusPlus = plus.getPlus();
        Assertions.assertEquals(RegionLocation.OUTSIDE, plusPlus.getLocation());
    }

    @Test
    void testGetLocation_multipleCuts_4_oe() {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, -1))));


        final TestRegionNode plus = root.getPlus();

        final TestRegionNode plusPlus = plus.getPlus();

        final TestRegionNode plusMinus = plus.getMinus();
        Assertions.assertEquals(RegionLocation.INSIDE, plusMinus.getLocation());
    }

    @Test
    void testGetLocation_multipleCuts_5_oe() {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, -1))));


        final TestRegionNode plus = root.getPlus();

        final TestRegionNode plusPlus = plus.getPlus();

        final TestRegionNode plusMinus = plus.getMinus();

        final TestRegionNode minus = root.getMinus();
        Assertions.assertEquals(RegionLocation.INSIDE, minus.getLocation());
    }

    @Test
    void testGetLocation_multipleCuts_6_oe() {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, -1))));


        final TestRegionNode plus = root.getPlus();

        final TestRegionNode plusPlus = plus.getPlus();

        final TestRegionNode plusMinus = plus.getMinus();

        final TestRegionNode minus = root.getMinus();

        final TestRegionNode minusPlus = minus.getPlus();
        Assertions.assertEquals(RegionLocation.OUTSIDE, minusPlus.getLocation());
    }

    @Test
    void testGetLocation_multipleCuts_7_oe() {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, -1))));


        final TestRegionNode plus = root.getPlus();

        final TestRegionNode plusPlus = plus.getPlus();

        final TestRegionNode plusMinus = plus.getMinus();

        final TestRegionNode minus = root.getMinus();

        final TestRegionNode minusPlus = minus.getPlus();

        final TestRegionNode minusMinus = minus.getMinus();
        Assertions.assertEquals(RegionLocation.INSIDE, minusMinus.getLocation());
    }

    @Test
    void testSetLocation_1_oe() {
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span());

        final TestRegionNode node = tree.getRoot().getMinus();

        node.setLocation(RegionLocation.OUTSIDE);

        Assertions.assertEquals(RegionLocation.OUTSIDE, node.getLocation());
    }

    @Test
    void testSetLocation_2_oe() {
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span());

        final TestRegionNode node = tree.getRoot().getMinus();

        node.setLocation(RegionLocation.OUTSIDE);

        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testSetLocation_invalidatesRegionProperties_1_oe() {
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span());

        final TestRegionNode node = tree.getRoot().getMinus();

        final RegionSizeProperties<TestPoint2D> prevProps = tree.getRegionSizeProperties();

        node.setLocation(RegionLocation.OUTSIDE);

        Assertions.assertNotSame(prevProps, tree.getRegionSizeProperties());
    }

    @Test
    void testSetLocation_noChange_doesNotInvalidateTree_1_oe() {
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span());

        final TestRegionNode node = tree.getRoot().getMinus();

        final RegionSizeProperties<TestPoint2D> prevProps = tree.getRegionSizeProperties();

        node.setLocation(RegionLocation.INSIDE);

        Assertions.assertSame(prevProps, tree.getRegionSizeProperties());
    }

    @Test
    void testCondense_1_oe() {
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);
        tree.insert(TestLine.X_AXIS.span(), RegionCutRule.INHERIT);

        final boolean result = tree.condense();

        Assertions.assertTrue(result);
    }

    @Test
    void testCondense_2_oe() {
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);
        tree.insert(TestLine.X_AXIS.span(), RegionCutRule.INHERIT);

        final boolean result = tree.condense();


        Assertions.assertEquals(3, tree.count());
    }

    @Test
    void testCondense_3_oe() {
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);
        tree.insert(TestLine.X_AXIS.span(), RegionCutRule.INHERIT);

        final boolean result = tree.condense();


        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getLocation());
    }

    @Test
    void testCondense_4_oe() {
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);
        tree.insert(TestLine.X_AXIS.span(), RegionCutRule.INHERIT);

        final boolean result = tree.condense();


        Assertions.assertEquals(RegionLocation.INSIDE, tree.getRoot().getMinus().getLocation());
    }

    @Test
    void testCondense_5_oe() {
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);
        tree.insert(TestLine.X_AXIS.span(), RegionCutRule.INHERIT);

        final boolean result = tree.condense();


        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getPlus().getLocation());
    }

    @Test
    void testCondense_alreadyCondensed_1_oe() {
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);

        final boolean result = tree.condense();

        Assertions.assertFalse(result);
    }

    @Test
    void testCondense_alreadyCondensed_2_oe() {
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);

        final boolean result = tree.condense();


        Assertions.assertEquals(3, tree.count());
    }

    @Test
    void testCondense_alreadyCondensed_3_oe() {
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);

        final boolean result = tree.condense();


        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getLocation());
    }

    @Test
    void testCondense_alreadyCondensed_4_oe() {
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);

        final boolean result = tree.condense();


        Assertions.assertEquals(RegionLocation.INSIDE, tree.getRoot().getMinus().getLocation());
    }

    @Test
    void testCondense_alreadyCondensed_5_oe() {
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);

        final boolean result = tree.condense();


        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getPlus().getLocation());
    }

    @Test
    void testCondense_invalidatesTreeWhenChanged_1_oe() {
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);
        tree.insert(TestLine.X_AXIS.span(), RegionCutRule.INHERIT);

        final RegionSizeProperties<TestPoint2D> prevProps = tree.getRegionSizeProperties();

        final boolean result = tree.condense();

        Assertions.assertTrue(result);
    }

    @Test
    void testCondense_invalidatesTreeWhenChanged_2_oe() {
        tree = emptyTree();
        tree.insert(TestLine.Y_AXIS.span(), RegionCutRule.MINUS_INSIDE);
        tree.insert(TestLine.X_AXIS.span(), RegionCutRule.INHERIT);

        final RegionSizeProperties<TestPoint2D> prevProps = tree.getRegionSizeProperties();

        final boolean result = tree.condense();


        Assertions.assertNotSame(prevProps, tree.getRegionSizeProperties());
    }

    @Test
    void testCondense_doesNotInvalidateTreeWhenNotChanged_1_oe() {
        tree = emptyTree();

        final RegionSizeProperties<TestPoint2D> prevProps = tree.getRegionSizeProperties();

        final boolean result = tree.condense();

        Assertions.assertFalse(result);
    }

    @Test
    void testCondense_doesNotInvalidateTreeWhenNotChanged_2_oe() {
        tree = emptyTree();

        final RegionSizeProperties<TestPoint2D> prevProps = tree.getRegionSizeProperties();

        final boolean result = tree.condense();


        Assertions.assertSame(prevProps, tree.getRegionSizeProperties());
    }

    @Test
    void testCut_nodeMethod_1_oe() {
        tree = emptyTree();

        tree.getRoot().cut(TestLine.X_AXIS, RegionCutRule.PLUS_INSIDE)
            .getPlus()
                .cut(TestLine.Y_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus()
                    .cut(new TestLine(TestPoint2D.ZERO, new TestPoint2D(-1, -1)), RegionCutRule.INHERIT);

        TestRegionNode node = tree.getRoot();
        Assertions.assertEquals(RegionLocation.OUTSIDE, node.getLocation());
    }

    @Test
    void testCut_nodeMethod_2_oe() {
        tree = emptyTree();

        tree.getRoot().cut(TestLine.X_AXIS, RegionCutRule.PLUS_INSIDE)
            .getPlus()
                .cut(TestLine.Y_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus()
                    .cut(new TestLine(TestPoint2D.ZERO, new TestPoint2D(-1, -1)), RegionCutRule.INHERIT);

        TestRegionNode node = tree.getRoot();

        Assertions.assertEquals(RegionLocation.OUTSIDE, node.getMinus().getLocation());
    }

    @Test
    void testCut_nodeMethod_3_oe() {
        tree = emptyTree();

        tree.getRoot().cut(TestLine.X_AXIS, RegionCutRule.PLUS_INSIDE)
            .getPlus()
                .cut(TestLine.Y_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus()
                    .cut(new TestLine(TestPoint2D.ZERO, new TestPoint2D(-1, -1)), RegionCutRule.INHERIT);

        TestRegionNode node = tree.getRoot();

        Assertions.assertEquals(RegionLocation.INSIDE, node.getPlus().getLocation());
    }

    @Test
    void testCut_nodeMethod_4_oe() {
        tree = emptyTree();

        tree.getRoot().cut(TestLine.X_AXIS, RegionCutRule.PLUS_INSIDE)
            .getPlus()
                .cut(TestLine.Y_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus()
                    .cut(new TestLine(TestPoint2D.ZERO, new TestPoint2D(-1, -1)), RegionCutRule.INHERIT);

        TestRegionNode node = tree.getRoot();


        node = node.getPlus();
        Assertions.assertEquals(RegionLocation.INSIDE, node.getMinus().getLocation());
    }

    @Test
    void testCut_nodeMethod_5_oe() {
        tree = emptyTree();

        tree.getRoot().cut(TestLine.X_AXIS, RegionCutRule.PLUS_INSIDE)
            .getPlus()
                .cut(TestLine.Y_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus()
                    .cut(new TestLine(TestPoint2D.ZERO, new TestPoint2D(-1, -1)), RegionCutRule.INHERIT);

        TestRegionNode node = tree.getRoot();


        node = node.getPlus();
        Assertions.assertEquals(RegionLocation.OUTSIDE, node.getPlus().getLocation());
    }

    @Test
    void testCut_nodeMethod_6_oe() {
        tree = emptyTree();

        tree.getRoot().cut(TestLine.X_AXIS, RegionCutRule.PLUS_INSIDE)
            .getPlus()
                .cut(TestLine.Y_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus()
                    .cut(new TestLine(TestPoint2D.ZERO, new TestPoint2D(-1, -1)), RegionCutRule.INHERIT);

        TestRegionNode node = tree.getRoot();


        node = node.getPlus();

        node = node.getMinus();
        Assertions.assertEquals(RegionLocation.INSIDE, node.getMinus().getLocation());
    }

    @Test
    void testCut_nodeMethod_7_oe() {
        tree = emptyTree();

        tree.getRoot().cut(TestLine.X_AXIS, RegionCutRule.PLUS_INSIDE)
            .getPlus()
                .cut(TestLine.Y_AXIS, RegionCutRule.MINUS_INSIDE)
                .getMinus()
                    .cut(new TestLine(TestPoint2D.ZERO, new TestPoint2D(-1, -1)), RegionCutRule.INHERIT);

        TestRegionNode node = tree.getRoot();


        node = node.getPlus();

        node = node.getMinus();
        Assertions.assertEquals(RegionLocation.INSIDE, node.getPlus().getLocation());
    }

    @Test
    void testBoundaries_fullAndEmpty_1_oe() {
        tree.setFull();
        Assertions.assertFalse(tree.boundaries().iterator().hasNext());
    }

    @Test
    void testBoundaries_fullAndEmpty_2_oe() {
        tree.setFull();

        tree.setEmpty();
        Assertions.assertFalse(tree.boundaries().iterator().hasNext());
    }

    @Test
    void testBoundaries_finite_1_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }

        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testBoundaries_finite_inverted_1_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }

        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testGetBoundaries_fullAndEmpty_1_oe() {
        tree.setFull();
        Assertions.assertEquals(0, tree.getBoundaries().size());
    }

    @Test
    void testGetBoundaries_fullAndEmpty_2_oe() {
        tree.setFull();

        tree.setEmpty();
        Assertions.assertEquals(0, tree.getBoundaries().size());
    }

    @Test
    void testGetBoundaries_finite_1_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }

        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testGetBoundaries_finite_inverted_1_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }

        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testClassify_1_oe() {
        insertSkewedBowtie(tree);

        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(3, 1)));
    }

    @Test
    void testClassify_2_oe() {
        insertSkewedBowtie(tree);

        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-3, -1)));
    }

    @Test
    void testClassify_3_oe() {
        insertSkewedBowtie(tree);


        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-3, 1)));
    }

    @Test
    void testClassify_4_oe() {
        insertSkewedBowtie(tree);


        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(3, -1)));
    }

    @Test
    void testClassify_5_oe() {
        insertSkewedBowtie(tree);



        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 5)));
    }

    @Test
    void testClassify_6_oe() {
        insertSkewedBowtie(tree);



        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, -5)));
    }

    @Test
    void testClassify_7_oe() {
        insertSkewedBowtie(tree);




        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(5, 0)));
    }

    @Test
    void testClassify_8_oe() {
        insertSkewedBowtie(tree);




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 0)));
    }

    @Test
    void testClassify_9_oe() {
        insertSkewedBowtie(tree);




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(3, 0)));
    }

    @Test
    void testClassify_10_oe() {
        insertSkewedBowtie(tree);




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(2, 0)));
    }

    @Test
    void testClassify_11_oe() {
        insertSkewedBowtie(tree);




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(1, 0)));
    }

    @Test
    void testClassify_12_oe() {
        insertSkewedBowtie(tree);




        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(0, 0)));
    }

    @Test
    void testClassify_13_oe() {
        insertSkewedBowtie(tree);




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-1, 0)));
    }

    @Test
    void testClassify_14_oe() {
        insertSkewedBowtie(tree);




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-2, 0)));
    }

    @Test
    void testClassify_15_oe() {
        insertSkewedBowtie(tree);




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-3, 0)));
    }

    @Test
    void testClassify_16_oe() {
        insertSkewedBowtie(tree);




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, 0)));
    }

    @Test
    void testClassify_17_oe() {
        insertSkewedBowtie(tree);




        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-5, 0)));
    }

    @Test
    void testClassify_emptyTree_1_oe() {
        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testClassify_NaN_1_oe() {
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(0, Double.NaN)));
    }

    @Test
    void testContains_1_oe() {
        insertSkewedBowtie(tree);

        Assertions.assertTrue(tree.contains(new TestPoint2D(3, 1)));
    }

    @Test
    void testContains_2_oe() {
        insertSkewedBowtie(tree);

        Assertions.assertTrue(tree.contains(new TestPoint2D(-3, -1)));
    }

    @Test
    void testContains_3_oe() {
        insertSkewedBowtie(tree);


        Assertions.assertFalse(tree.contains(new TestPoint2D(-3, 1)));
    }

    @Test
    void testContains_4_oe() {
        insertSkewedBowtie(tree);


        Assertions.assertFalse(tree.contains(new TestPoint2D(3, -1)));
    }

    @Test
    void testContains_5_oe() {
        insertSkewedBowtie(tree);



        Assertions.assertTrue(tree.contains(new TestPoint2D(4, 5)));
    }

    @Test
    void testContains_6_oe() {
        insertSkewedBowtie(tree);



        Assertions.assertTrue(tree.contains(new TestPoint2D(-4, -5)));
    }

    @Test
    void testContains_7_oe() {
        insertSkewedBowtie(tree);




        Assertions.assertFalse(tree.contains(new TestPoint2D(5, 0)));
    }

    @Test
    void testContains_8_oe() {
        insertSkewedBowtie(tree);





        Assertions.assertTrue(tree.contains(new TestPoint2D(4, 0)));
    }

    @Test
    void testContains_9_oe() {
        insertSkewedBowtie(tree);





        Assertions.assertTrue(tree.contains(new TestPoint2D(3, 0)));
    }

    @Test
    void testContains_10_oe() {
        insertSkewedBowtie(tree);





        Assertions.assertTrue(tree.contains(new TestPoint2D(2, 0)));
    }

    @Test
    void testContains_11_oe() {
        insertSkewedBowtie(tree);





        Assertions.assertTrue(tree.contains(new TestPoint2D(1, 0)));
    }

    @Test
    void testContains_12_oe() {
        insertSkewedBowtie(tree);





        Assertions.assertTrue(tree.contains(new TestPoint2D(0, 0)));
    }

    @Test
    void testContains_13_oe() {
        insertSkewedBowtie(tree);





        Assertions.assertTrue(tree.contains(new TestPoint2D(-1, 0)));
    }

    @Test
    void testContains_14_oe() {
        insertSkewedBowtie(tree);





        Assertions.assertTrue(tree.contains(new TestPoint2D(-2, 0)));
    }

    @Test
    void testContains_15_oe() {
        insertSkewedBowtie(tree);





        Assertions.assertTrue(tree.contains(new TestPoint2D(-3, 0)));
    }

    @Test
    void testContains_16_oe() {
        insertSkewedBowtie(tree);





        Assertions.assertTrue(tree.contains(new TestPoint2D(-4, 0)));
    }

    @Test
    void testContains_17_oe() {
        insertSkewedBowtie(tree);






        Assertions.assertFalse(tree.contains(new TestPoint2D(-5, 0)));
    }

    @Test
    void testSetFull_1_oe() {
        insertSkewedBowtie(tree);

        tree.setFull();

        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testSetFull_2_oe() {
        insertSkewedBowtie(tree);

        tree.setFull();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testSetFull_3_oe() {
        insertSkewedBowtie(tree);

        tree.setFull();


        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testSetFull_4_oe() {
        insertSkewedBowtie(tree);

        tree.setFull();


        Assertions.assertTrue(tree.contains(TestPoint2D.ZERO));
    }

    @Test
    void testSetEmpty_1_oe() {
        insertSkewedBowtie(tree);

        tree.setEmpty();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testSetEmpty_2_oe() {
        insertSkewedBowtie(tree);

        tree.setEmpty();

        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testSetEmpty_3_oe() {
        insertSkewedBowtie(tree);

        tree.setEmpty();


        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testSetEmpty_4_oe() {
        insertSkewedBowtie(tree);

        tree.setEmpty();


        Assertions.assertFalse(tree.contains(TestPoint2D.ZERO));
    }

    @Test
    void testGetRegionSizeProperties_cachesValueBasedOnVersion_1_oe() {
        final RegionSizeProperties<TestPoint2D> first = tree.getRegionSizeProperties();
        final RegionSizeProperties<TestPoint2D> second = tree.getRegionSizeProperties();
        tree.getRoot().cut(TestLine.X_AXIS);
        final RegionSizeProperties<TestPoint2D> third = tree.getRegionSizeProperties();

        Assertions.assertSame(first, second);
    }

    @Test
    void testGetRegionSizeProperties_cachesValueBasedOnVersion_2_oe() {
        final RegionSizeProperties<TestPoint2D> first = tree.getRegionSizeProperties();
        final RegionSizeProperties<TestPoint2D> second = tree.getRegionSizeProperties();
        tree.getRoot().cut(TestLine.X_AXIS);
        final RegionSizeProperties<TestPoint2D> third = tree.getRegionSizeProperties();

        Assertions.assertNotSame(second, third);
    }

    @Test
    void testGetRegionSizeProperties_cachesValueBasedOnVersion_3_oe() {
        final RegionSizeProperties<TestPoint2D> first = tree.getRegionSizeProperties();
        final RegionSizeProperties<TestPoint2D> second = tree.getRegionSizeProperties();
        tree.getRoot().cut(TestLine.X_AXIS);
        final RegionSizeProperties<TestPoint2D> third = tree.getRegionSizeProperties();


        Assertions.assertEquals(1234, first.getSize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetSize_1_oe() {
        Assertions.assertEquals(1234, tree.getSize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_fullAndEmpty_1_oe() {
        Assertions.assertEquals(0.0, fullTree().getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_fullAndEmpty_2_oe() {
        Assertions.assertEquals(0.0, emptyTree().getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_infinite_1_oe() {
        final TestRegionBSPTree halfPos = new TestRegionBSPTree(true);
        halfPos.getRoot().cut(TestLine.X_AXIS);

        final TestRegionBSPTree halfPosComplement = new TestRegionBSPTree(true);
        halfPosComplement.complement(halfPos);

        Assertions.assertEquals(Double.POSITIVE_INFINITY, halfPos.getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_infinite_2_oe() {
        final TestRegionBSPTree halfPos = new TestRegionBSPTree(true);
        halfPos.getRoot().cut(TestLine.X_AXIS);

        final TestRegionBSPTree halfPosComplement = new TestRegionBSPTree(true);
        halfPosComplement.complement(halfPos);

        Assertions.assertEquals(Double.POSITIVE_INFINITY, halfPosComplement.getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_alignedCuts_1_oe() {
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

        Assertions.assertEquals(6, tree.getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_box_1_oe() {
        insertBox(tree, new TestPoint2D(2, 2), new TestPoint2D(4, 1));

        Assertions.assertEquals(6.0, tree.getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_boxComplement_1_oe() {
        insertBox(tree, new TestPoint2D(2, 2), new TestPoint2D(4, 1));
        tree.complement();

        Assertions.assertEquals(6.0, tree.getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_recomputesAfterChange_1_oe() {
        insertBox(tree, new TestPoint2D(2, 2), new TestPoint2D(4, 1));

        final double first = tree.getBoundarySize();
        tree.insert(new TestLineSegment(new TestPoint2D(3, 1), new TestPoint2D(3, 2)));

        final double second = tree.getBoundarySize();
        final double third = tree.getBoundarySize();

        Assertions.assertEquals(6.0, first, PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_recomputesAfterChange_2_oe() {
        insertBox(tree, new TestPoint2D(2, 2), new TestPoint2D(4, 1));

        final double first = tree.getBoundarySize();
        tree.insert(new TestLineSegment(new TestPoint2D(3, 1), new TestPoint2D(3, 2)));

        final double second = tree.getBoundarySize();
        final double third = tree.getBoundarySize();

        Assertions.assertEquals(4.0, second, PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_recomputesAfterChange_3_oe() {
        insertBox(tree, new TestPoint2D(2, 2), new TestPoint2D(4, 1));

        final double first = tree.getBoundarySize();
        tree.insert(new TestLineSegment(new TestPoint2D(3, 1), new TestPoint2D(3, 2)));

        final double second = tree.getBoundarySize();
        final double third = tree.getBoundarySize();

        Assertions.assertEquals(4.0, third, PartitionTestUtils.EPS);
    }

    @Test
    void testGetCutBoundary_emptyTree_1_oe() {
        final RegionCutBoundary<TestPoint2D> boundary = root.getCutBoundary();

        Assertions.assertNull(boundary);
    }

    @Test
    void testGetCutBoundary_singleCut_1_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));

        final RegionCutBoundary<TestPoint2D> boundary = root.getCutBoundary();

        Assertions.assertTrue(boundary.getInsideFacing().isEmpty());
    }

    @Test
    void testGetCutBoundary_singleCut_leafNode_1_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));

        final RegionCutBoundary<TestPoint2D> boundary = root.getMinus().getCutBoundary();

        Assertions.assertNull(boundary);
    }

    @Test
    void testGetCutBoundary_singleCorner_1_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();

        Assertions.assertTrue(rootBoundary.getInsideFacing().isEmpty());
    }

    @Test
    void testGetCutBoundary_singleCorner_3_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();


        final RegionCutBoundary<TestPoint2D> childBoundary = tree.getRoot().getMinus().getCutBoundary();
        Assertions.assertTrue(childBoundary.getInsideFacing().isEmpty());
    }

    @Test
    void testGetCutBoundary_leafNode_1_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        Assertions.assertNull(root.getPlus().getCutBoundary());
    }

    @Test
    void testGetCutBoundary_leafNode_2_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        Assertions.assertNull(root.getMinus().getMinus().getCutHyperplane());
    }

    @Test
    void testGetCutBoundary_leafNode_3_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        Assertions.assertNull(root.getMinus().getPlus().getCutHyperplane());
    }

    @Test
    void testFullEmpty_fullTree_1_oe() {
        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testFullEmpty_fullTree_2_oe() {
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testFullEmpty_fullTree_3_oe() {
        Assertions.assertEquals(RegionLocation.INSIDE, tree.getRoot().getLocation());
    }

    @Test
    void testFullEmpty_emptyTree_1_oe() {
        tree.complement();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testFullEmpty_emptyTree_2_oe() {
        tree.complement();

        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testFullEmpty_emptyTree_3_oe() {
        tree.complement();

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getLocation());
    }

    @Test
    void testTransform_noCuts_1_oe() {
        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), p.getY() + 2));

        tree.transform(t);

        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testTransform_noCuts_2_oe() {
        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), p.getY() + 2));

        tree.transform(t);

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTransform_singleCut_1_oe() {
        tree.getRoot().insertCut(TestLine.X_AXIS);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), p.getY() + 2));

        tree.transform(t);

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testTransform_singleCut_2_oe() {
        tree.getRoot().insertCut(TestLine.X_AXIS);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), p.getY() + 2));

        tree.transform(t);

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTransform_multipleCuts_1_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        tree.transform(t);

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testTransform_multipleCuts_2_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        tree.transform(t);

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTransform_xAxisReflection_1_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), p.getY()));

        tree.transform(t);

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testTransform_xAxisReflection_2_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), p.getY()));

        tree.transform(t);

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTransform_yAxisReflection_1_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), -p.getY()));

        tree.transform(t);

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testTransform_yAxisReflection_2_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), -p.getY()));

        tree.transform(t);

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTransform_xAndYAxisReflection_1_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), -p.getY()));

        tree.transform(t);

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testTransform_xAndYAxisReflection_2_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), -p.getY()));

        tree.transform(t);

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTransform_resetsCutBoundary_1_oe() {
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();

        Assertions.assertNotSame(origBoundary, resultBoundary);
    }

    @Test
    void testComplement_rootOnly_1_oe() {
        tree.complement();

        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testComplement_rootOnly_2_oe() {
        tree.complement();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testComplement_rootOnly_3_oe() {
        tree.complement();


        Assertions.assertEquals(RegionLocation.OUTSIDE, root.getLocation());
    }

    @Test
    void testComplement_rootOnly_4_oe() {
        tree.complement();


        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplement_singleCut_1_oe() {
        root.insertCut(TestLine.X_AXIS);

        tree.complement();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testComplement_singleCut_2_oe() {
        root.insertCut(TestLine.X_AXIS);

        tree.complement();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testComplement_singleCut_3_oe() {
        root.insertCut(TestLine.X_AXIS);

        tree.complement();


        Assertions.assertEquals(RegionLocation.OUTSIDE, root.getMinus().getLocation());
    }

    @Test
    void testComplement_singleCut_4_oe() {
        root.insertCut(TestLine.X_AXIS);

        tree.complement();


        Assertions.assertEquals(RegionLocation.INSIDE, root.getPlus().getLocation());
    }

    @Test
    void testComplement_singleCut_5_oe() {
        root.insertCut(TestLine.X_AXIS);

        tree.complement();



        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(0, 1)));
    }

    @Test
    void testComplement_singleCut_6_oe() {
        root.insertCut(TestLine.X_AXIS);

        tree.complement();



        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplement_singleCut_7_oe() {
        root.insertCut(TestLine.X_AXIS);

        tree.complement();



        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(0, -1)));
    }

    @Test
    void testComplement_skewedBowtie_1_oe() {
        insertSkewedBowtie(tree);

        tree.complement();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testComplement_skewedBowtie_2_oe() {
        insertSkewedBowtie(tree);

        tree.complement();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testComplement_skewedBowtie_3_oe() {
        insertSkewedBowtie(tree);

        tree.complement();


        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(3, 1)));
    }

    @Test
    void testComplement_skewedBowtie_4_oe() {
        insertSkewedBowtie(tree);

        tree.complement();


        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-3, -1)));
    }

    @Test
    void testComplement_skewedBowtie_5_oe() {
        insertSkewedBowtie(tree);

        tree.complement();



        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-3, 1)));
    }

    @Test
    void testComplement_skewedBowtie_6_oe() {
        insertSkewedBowtie(tree);

        tree.complement();



        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(3, -1)));
    }

    @Test
    void testComplement_skewedBowtie_7_oe() {
        insertSkewedBowtie(tree);

        tree.complement();




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 5)));
    }

    @Test
    void testComplement_skewedBowtie_8_oe() {
        insertSkewedBowtie(tree);

        tree.complement();




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, -5)));
    }

    @Test
    void testComplement_skewedBowtie_9_oe() {
        insertSkewedBowtie(tree);

        tree.complement();





        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(5, 0)));
    }

    @Test
    void testComplement_skewedBowtie_10_oe() {
        insertSkewedBowtie(tree);

        tree.complement();





        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 0)));
    }

    @Test
    void testComplement_skewedBowtie_11_oe() {
        insertSkewedBowtie(tree);

        tree.complement();





        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(3, 0)));
    }

    @Test
    void testComplement_skewedBowtie_12_oe() {
        insertSkewedBowtie(tree);

        tree.complement();





        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(2, 0)));
    }

    @Test
    void testComplement_skewedBowtie_13_oe() {
        insertSkewedBowtie(tree);

        tree.complement();





        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(1, 0)));
    }

    @Test
    void testComplement_skewedBowtie_14_oe() {
        insertSkewedBowtie(tree);

        tree.complement();





        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(0, 0)));
    }

    @Test
    void testComplement_skewedBowtie_15_oe() {
        insertSkewedBowtie(tree);

        tree.complement();





        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-1, 0)));
    }

    @Test
    void testComplement_skewedBowtie_16_oe() {
        insertSkewedBowtie(tree);

        tree.complement();





        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-2, 0)));
    }

    @Test
    void testComplement_skewedBowtie_17_oe() {
        insertSkewedBowtie(tree);

        tree.complement();





        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-3, 0)));
    }

    @Test
    void testComplement_skewedBowtie_18_oe() {
        insertSkewedBowtie(tree);

        tree.complement();





        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, 0)));
    }

    @Test
    void testComplement_skewedBowtie_19_oe() {
        insertSkewedBowtie(tree);

        tree.complement();





        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-5, 0)));
    }

    @Test
    void testComplement_addCutAfterComplement_1_oe() {
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        tree.complement();

        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testComplement_addCutAfterComplement_2_oe() {
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        tree.complement();

        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testComplement_addCutAfterComplement_3_oe() {
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        tree.complement();

        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));


        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplement_addCutAfterComplement_4_oe() {
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        tree.complement();

        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));



        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(1, 1)));
    }

    @Test
    void testComplement_addCutAfterComplement_5_oe() {
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        tree.complement();

        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));



        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-1, 1)));
    }

    @Test
    void testComplement_addCutAfterComplement_6_oe() {
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        tree.complement();

        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));



        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(1, -1)));
    }

    @Test
    void testComplement_addCutAfterComplement_7_oe() {
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        tree.complement();

        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));



        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-1, -1)));
    }

    @Test
    void testComplement_clearCutAfterComplement_1_oe() {
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        root.getMinus().clearCut();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testComplement_clearCutAfterComplement_2_oe() {
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        root.getMinus().clearCut();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testComplement_clearCutAfterComplement_3_oe() {
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        root.getMinus().clearCut();


        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplement_clearCutAfterComplement_4_oe() {
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        root.getMinus().clearCut();



        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(1, 1)));
    }

    @Test
    void testComplement_clearCutAfterComplement_5_oe() {
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        root.getMinus().clearCut();



        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-1, 1)));
    }

    @Test
    void testComplement_clearCutAfterComplement_6_oe() {
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        root.getMinus().clearCut();



        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(1, -1)));
    }

    @Test
    void testComplement_clearCutAfterComplement_7_oe() {
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        root.getMinus().clearCut();



        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-1, -1)));
    }

    @Test
    void testComplement_clearRootAfterComplement_1_oe() {
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        root.clearCut();

        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testComplement_clearRootAfterComplement_2_oe() {
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        root.clearCut();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testComplement_clearRootAfterComplement_3_oe() {
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))
                ));
        tree.complement();

        root.clearCut();


        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplement_isReversible_root_1_oe() {
        tree.complement();
        tree.complement();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testComplement_isReversible_root_2_oe() {
        tree.complement();
        tree.complement();

        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testComplement_isReversible_root_3_oe() {
        tree.complement();
        tree.complement();


        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
    }

    @Test
    void testComplement_isReversible_root_4_oe() {
        tree.complement();
        tree.complement();


        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_1_oe() {
        insertSkewedBowtie(tree);

        tree.complement();
        tree.complement();

        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(3, 1)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_2_oe() {
        insertSkewedBowtie(tree);

        tree.complement();
        tree.complement();

        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-3, -1)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_3_oe() {
        insertSkewedBowtie(tree);

        tree.complement();
        tree.complement();


        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-3, 1)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_4_oe() {
        insertSkewedBowtie(tree);

        tree.complement();
        tree.complement();


        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(3, -1)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_5_oe() {
        insertSkewedBowtie(tree);

        tree.complement();
        tree.complement();



        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 5)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_6_oe() {
        insertSkewedBowtie(tree);

        tree.complement();
        tree.complement();



        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, -5)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_7_oe() {
        insertSkewedBowtie(tree);

        tree.complement();
        tree.complement();




        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(5, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_8_oe() {
        insertSkewedBowtie(tree);

        tree.complement();
        tree.complement();




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(4, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_9_oe() {
        insertSkewedBowtie(tree);

        tree.complement();
        tree.complement();




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(3, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_10_oe() {
        insertSkewedBowtie(tree);

        tree.complement();
        tree.complement();




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(2, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_11_oe() {
        insertSkewedBowtie(tree);

        tree.complement();
        tree.complement();




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(1, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_12_oe() {
        insertSkewedBowtie(tree);

        tree.complement();
        tree.complement();




        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(0, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_13_oe() {
        insertSkewedBowtie(tree);

        tree.complement();
        tree.complement();




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-1, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_14_oe() {
        insertSkewedBowtie(tree);

        tree.complement();
        tree.complement();




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-2, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_15_oe() {
        insertSkewedBowtie(tree);

        tree.complement();
        tree.complement();




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-3, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_16_oe() {
        insertSkewedBowtie(tree);

        tree.complement();
        tree.complement();




        Assertions.assertEquals(RegionLocation.BOUNDARY, tree.classify(new TestPoint2D(-4, 0)));
    }

    @Test
    void testComplement_isReversible_skewedBowtie_17_oe() {
        insertSkewedBowtie(tree);

        tree.complement();
        tree.complement();




        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(new TestPoint2D(-5, 0)));
    }

    @Test
    void testComplement_getCutBoundary_1_oe() {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))));
        tree.complement();

        final RegionCutBoundary<TestPoint2D> xAxisBoundary = root.getCutBoundary();
        final RegionCutBoundary<TestPoint2D> yAxisBoundary = root.getMinus().getCutBoundary();

        Assertions.assertTrue(xAxisBoundary.getOutsideFacing().isEmpty());
    }

    @Test
    void testComplement_getCutBoundary_2_oe() {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))));
        tree.complement();

        final RegionCutBoundary<TestPoint2D> xAxisBoundary = root.getCutBoundary();
        final RegionCutBoundary<TestPoint2D> yAxisBoundary = root.getMinus().getCutBoundary();

        Assertions.assertFalse(xAxisBoundary.getInsideFacing().isEmpty());
    }

    @Test
    void testComplement_getCutBoundary_3_oe() {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))));
        tree.complement();

        final RegionCutBoundary<TestPoint2D> xAxisBoundary = root.getCutBoundary();
        final RegionCutBoundary<TestPoint2D> yAxisBoundary = root.getMinus().getCutBoundary();


        final List<HyperplaneConvexSubset<TestPoint2D>> xAxisInsideFacing = xAxisBoundary.getInsideFacing();
        Assertions.assertEquals(1, xAxisInsideFacing.size());
    }

    @Test
    void testComplement_getCutBoundary_6_oe() {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))));
        tree.complement();

        final RegionCutBoundary<TestPoint2D> xAxisBoundary = root.getCutBoundary();
        final RegionCutBoundary<TestPoint2D> yAxisBoundary = root.getMinus().getCutBoundary();


        final List<HyperplaneConvexSubset<TestPoint2D>> xAxisInsideFacing = xAxisBoundary.getInsideFacing();

        final TestLineSegment xAxisSeg = (TestLineSegment) xAxisInsideFacing.get(0);

        Assertions.assertTrue(yAxisBoundary.getOutsideFacing().isEmpty());
    }

    @Test
    void testComplement_getCutBoundary_7_oe() {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))));
        tree.complement();

        final RegionCutBoundary<TestPoint2D> xAxisBoundary = root.getCutBoundary();
        final RegionCutBoundary<TestPoint2D> yAxisBoundary = root.getMinus().getCutBoundary();


        final List<HyperplaneConvexSubset<TestPoint2D>> xAxisInsideFacing = xAxisBoundary.getInsideFacing();

        final TestLineSegment xAxisSeg = (TestLineSegment) xAxisInsideFacing.get(0);

        Assertions.assertFalse(yAxisBoundary.getInsideFacing().isEmpty());
    }

    @Test
    void testComplement_getCutBoundary_8_oe() {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))));
        tree.complement();

        final RegionCutBoundary<TestPoint2D> xAxisBoundary = root.getCutBoundary();
        final RegionCutBoundary<TestPoint2D> yAxisBoundary = root.getMinus().getCutBoundary();


        final List<HyperplaneConvexSubset<TestPoint2D>> xAxisInsideFacing = xAxisBoundary.getInsideFacing();

        final TestLineSegment xAxisSeg = (TestLineSegment) xAxisInsideFacing.get(0);


        final List<HyperplaneConvexSubset<TestPoint2D>> yAxisInsideFacing = yAxisBoundary.getInsideFacing();
        Assertions.assertEquals(1, yAxisInsideFacing.size());
    }

    @Test
    void testComplementOf_rootOnly_1_oe() {
        final TestRegionBSPTree other = fullTree();
        insertSkewedBowtie(other);

        other.complement(tree);

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testComplementOf_rootOnly_2_oe() {
        final TestRegionBSPTree other = fullTree();
        insertSkewedBowtie(other);

        other.complement(tree);

        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testComplementOf_rootOnly_3_oe() {
        final TestRegionBSPTree other = fullTree();
        insertSkewedBowtie(other);

        other.complement(tree);


        Assertions.assertEquals(RegionLocation.INSIDE, root.getLocation());
    }

    @Test
    void testComplementOf_rootOnly_4_oe() {
        final TestRegionBSPTree other = fullTree();
        insertSkewedBowtie(other);

        other.complement(tree);


        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplementOf_rootOnly_5_oe() {
        final TestRegionBSPTree other = fullTree();
        insertSkewedBowtie(other);

        other.complement(tree);



        Assertions.assertTrue(other.isEmpty());
    }

    @Test
    void testComplementOf_rootOnly_6_oe() {
        final TestRegionBSPTree other = fullTree();
        insertSkewedBowtie(other);

        other.complement(tree);



        Assertions.assertFalse(other.isFull());
    }

    @Test
    void testComplementOf_rootOnly_7_oe() {
        final TestRegionBSPTree other = fullTree();
        insertSkewedBowtie(other);

        other.complement(tree);




        Assertions.assertEquals(RegionLocation.OUTSIDE, other.getRoot().getLocation());
    }

    @Test
    void testComplementOf_rootOnly_8_oe() {
        final TestRegionBSPTree other = fullTree();
        insertSkewedBowtie(other);

        other.complement(tree);




        Assertions.assertEquals(RegionLocation.OUTSIDE, other.classify(TestPoint2D.ZERO));
    }

    @Test
    void testComplementOf_skewedBowtie_1_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);

        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(3, 1)));
    }

    @Test
    void testComplementOf_skewedBowtie_2_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);

        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(new TestPoint2D(-3, -1)));
    }

    @Test
    void testComplementOf_skewedBowtie_3_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);


        Assertions.assertFalse(other.isEmpty());
    }

    @Test
    void testComplementOf_skewedBowtie_4_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);


        Assertions.assertFalse(other.isFull());
    }

    @Test
    void testComplementOf_skewedBowtie_5_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);



        Assertions.assertEquals(RegionLocation.OUTSIDE, other.classify(new TestPoint2D(3, 1)));
    }

    @Test
    void testComplementOf_skewedBowtie_6_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);



        Assertions.assertEquals(RegionLocation.OUTSIDE, other.classify(new TestPoint2D(-3, -1)));
    }

    @Test
    void testComplementOf_skewedBowtie_7_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);




        Assertions.assertEquals(RegionLocation.INSIDE, other.classify(new TestPoint2D(-3, 1)));
    }

    @Test
    void testComplementOf_skewedBowtie_8_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);




        Assertions.assertEquals(RegionLocation.INSIDE, other.classify(new TestPoint2D(3, -1)));
    }

    @Test
    void testComplementOf_skewedBowtie_9_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);





        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(4, 5)));
    }

    @Test
    void testComplementOf_skewedBowtie_10_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);





        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(-4, -5)));
    }

    @Test
    void testComplementOf_skewedBowtie_11_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);






        Assertions.assertEquals(RegionLocation.INSIDE, other.classify(new TestPoint2D(5, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_12_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);






        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(4, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_13_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);






        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(3, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_14_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);






        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(2, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_15_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);






        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(1, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_16_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);






        Assertions.assertEquals(RegionLocation.OUTSIDE, other.classify(new TestPoint2D(0, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_17_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);






        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(-1, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_18_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);






        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(-2, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_19_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);






        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(-3, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_20_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);






        Assertions.assertEquals(RegionLocation.BOUNDARY, other.classify(new TestPoint2D(-4, 0)));
    }

    @Test
    void testComplementOf_skewedBowtie_21_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree other = fullTree();

        other.complement(tree);






        Assertions.assertEquals(RegionLocation.INSIDE, other.classify(new TestPoint2D(-5, 0)));
    }

    @Test
    void testCopy_1_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree copy = fullTree();
        copy.copy(tree);

        Assertions.assertNotSame(tree, copy);
    }

    @Test
    void testCopy_2_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree copy = fullTree();
        copy.copy(tree);

        Assertions.assertEquals(tree.count(), copy.count());
    }

    @Test
    void testCopy_3_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree copy = fullTree();
        copy.copy(tree);


        final List<RegionLocation> origLocations = new ArrayList<>();
        tree.nodes().forEach(n -> origLocations.add(n.getLocation()));

        final List<RegionLocation> copyLocations = new ArrayList<>();
        copy.nodes().forEach(n -> copyLocations.add(n.getLocation()));

        Assertions.assertEquals(origLocations, copyLocations);
    }

    @Test
    void testProject_emptyAndFull_1_oe() {
        final TestRegionBSPTree full = fullTree();
        final TestRegionBSPTree empty = emptyTree();

        Assertions.assertNull(full.project(new TestPoint2D(0, 0)));
    }

    @Test
    void testProject_emptyAndFull_2_oe() {
        final TestRegionBSPTree full = fullTree();
        final TestRegionBSPTree empty = emptyTree();

        Assertions.assertNull(empty.project(new TestPoint2D(-1, 1)));
    }

    @Test
    void testSplit_empty_1_oe() {
        tree = emptyTree();

        final Split<TestRegionBSPTree> split = tree.split(TestLine.X_AXIS);

        Assertions.assertEquals(SplitLocation.NEITHER, split.getLocation());
    }

    @Test
    void testSplit_empty_2_oe() {
        tree = emptyTree();

        final Split<TestRegionBSPTree> split = tree.split(TestLine.X_AXIS);


        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_empty_3_oe() {
        tree = emptyTree();

        final Split<TestRegionBSPTree> split = tree.split(TestLine.X_AXIS);


        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_full_1_oe() {
        tree = fullTree();

        final Split<TestRegionBSPTree> split = tree.split(TestLine.X_AXIS);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_halfSpace_1_oe() {
        tree.getRoot().insertCut(TestLine.X_AXIS);

        final TestLine splitter = TestLine.Y_AXIS;

        final Split<TestRegionBSPTree> split = tree.split(splitter);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_box_1_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, 1));

        final Split<TestRegionBSPTree> split = tree.split(splitter);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_box_onMinusOnly_1_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(2, 0), new TestPoint2D(1, 1));

        final Split<TestRegionBSPTree> split = tree.split(splitter);

        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_box_onMinusOnly_4_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(2, 0), new TestPoint2D(1, 1));

        final Split<TestRegionBSPTree> split = tree.split(splitter);


        final TestRegionBSPTree minus = split.getMinus();

        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_box_onPlusOnly_1_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(-1, 1));

        final Split<TestRegionBSPTree> split = tree.split(splitter);

        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_box_onPlusOnly_2_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(-1, 1));

        final Split<TestRegionBSPTree> split = tree.split(splitter);


        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testToString_1_oe() {
        tree.getRoot().cut(TestLine.X_AXIS);

        final String str = tree.toString();

        Assertions.assertEquals("TestRegionBSPTree[count= 3, height= 1]", str);
    }

    @Test
    void testToString_2_oe() {
        tree.getRoot().cut(TestLine.X_AXIS);

        final String str = tree.toString();

        Assertions.assertTrue(tree.getRoot().toString().contains("TestRegionNode"));
    }

    @Test
    void testBoundaries_finite_2_oe_1_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }


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
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }


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
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }


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
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }


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
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }


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
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }


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
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }


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
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.boundaries()) {
            segments.add((TestLineSegment) sub);
        }


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
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }


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
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }


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
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }


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
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }


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
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }


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
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }


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
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }


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
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));
        tree.complement();

        final List<TestLineSegment> segments = new ArrayList<>();
        for (final HyperplaneConvexSubset<TestPoint2D> sub : tree.getBoundaries()) {
            segments.add((TestLineSegment) sub);
        }


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
        tree.insert(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));

        final RegionCutBoundary<TestPoint2D> boundary = root.getCutBoundary();


                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = boundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0);
        final TestPoint2D end0 = new TestPoint2D(Double.POSITIVE_INFINITY, 0.0);
        Assertions.assertFalse(boundaries0.isEmpty(), "Expected boundary to not be empty");
    }

    @Test
    void testGetCutBoundary_singleCut_2_oe_2_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));

        final RegionCutBoundary<TestPoint2D> boundary = root.getCutBoundary();


                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = boundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0);
        final TestPoint2D end0 = new TestPoint2D(Double.POSITIVE_INFINITY, 0.0);
        
                Assertions.assertEquals(1, boundaries0.size());
    }

    @Test
    void testGetCutBoundary_singleCut_2_oe_3_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));

        final RegionCutBoundary<TestPoint2D> boundary = root.getCutBoundary();


                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = boundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0);
        final TestPoint2D end0 = new TestPoint2D(Double.POSITIVE_INFINITY, 0.0);
        
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                PartitionTestUtils.assertPointsEqual(start0, segment0.getStartPoint());
    }

    @Test
    void testGetCutBoundary_singleCut_2_oe_4_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));

        final RegionCutBoundary<TestPoint2D> boundary = root.getCutBoundary();


                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = boundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0);
        final TestPoint2D end0 = new TestPoint2D(Double.POSITIVE_INFINITY, 0.0);
        
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                PartitionTestUtils.assertPointsEqual(end0, segment0.getEndPoint());
    }

    @Test
    void testGetCutBoundary_singleCorner_2_oe_1_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();

                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = rootBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0);
        final TestPoint2D end0 = TestPoint2D.ZERO;
        Assertions.assertFalse(boundaries0.isEmpty(), "Expected boundary to not be empty");
    }

    @Test
    void testGetCutBoundary_singleCorner_2_oe_2_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();

                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = rootBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0);
        final TestPoint2D end0 = TestPoint2D.ZERO;
        
                Assertions.assertEquals(1, boundaries0.size());
    }

    @Test
    void testGetCutBoundary_singleCorner_2_oe_3_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();

                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = rootBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0);
        final TestPoint2D end0 = TestPoint2D.ZERO;
        
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                PartitionTestUtils.assertPointsEqual(start0, segment0.getStartPoint());
    }

    @Test
    void testGetCutBoundary_singleCorner_2_oe_4_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();

                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = rootBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(Double.NEGATIVE_INFINITY, 0.0);
        final TestPoint2D end0 = TestPoint2D.ZERO;
        
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                PartitionTestUtils.assertPointsEqual(end0, segment0.getEndPoint());
    }

    @Test
    void testGetCutBoundary_singleCorner_4_oe_1_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();


        final RegionCutBoundary<TestPoint2D> childBoundary = tree.getRoot().getMinus().getCutBoundary();
                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = childBoundary.getOutsideFacing();
        final TestPoint2D start0 = TestPoint2D.ZERO;
        final TestPoint2D end0 = new TestPoint2D(0.0, Double.POSITIVE_INFINITY);
        Assertions.assertFalse(boundaries0.isEmpty(), "Expected boundary to not be empty");
    }

    @Test
    void testGetCutBoundary_singleCorner_4_oe_2_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();


        final RegionCutBoundary<TestPoint2D> childBoundary = tree.getRoot().getMinus().getCutBoundary();
                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = childBoundary.getOutsideFacing();
        final TestPoint2D start0 = TestPoint2D.ZERO;
        final TestPoint2D end0 = new TestPoint2D(0.0, Double.POSITIVE_INFINITY);
        
                Assertions.assertEquals(1, boundaries0.size());
    }

    @Test
    void testGetCutBoundary_singleCorner_4_oe_3_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();


        final RegionCutBoundary<TestPoint2D> childBoundary = tree.getRoot().getMinus().getCutBoundary();
                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = childBoundary.getOutsideFacing();
        final TestPoint2D start0 = TestPoint2D.ZERO;
        final TestPoint2D end0 = new TestPoint2D(0.0, Double.POSITIVE_INFINITY);
        
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                PartitionTestUtils.assertPointsEqual(start0, segment0.getStartPoint());
    }

    @Test
    void testGetCutBoundary_singleCorner_4_oe_4_oe() {
        tree.insert(new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)));
        tree.insert(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1)));

        final RegionCutBoundary<TestPoint2D> rootBoundary = root.getCutBoundary();


        final RegionCutBoundary<TestPoint2D> childBoundary = tree.getRoot().getMinus().getCutBoundary();
                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = childBoundary.getOutsideFacing();
        final TestPoint2D start0 = TestPoint2D.ZERO;
        final TestPoint2D end0 = new TestPoint2D(0.0, Double.POSITIVE_INFINITY);
        
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                PartitionTestUtils.assertPointsEqual(end0, segment0.getEndPoint());
    }

    @Test
    void testTransform_resetsCutBoundary_2_oe_1_oe() {
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();


                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = origBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(4, 5);
        final TestPoint2D end0 = new TestPoint2D(-1, 0);
        Assertions.assertFalse(boundaries0.isEmpty(), "Expected boundary to not be empty");
    }

    @Test
    void testTransform_resetsCutBoundary_2_oe_2_oe() {
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();


                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = origBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(4, 5);
        final TestPoint2D end0 = new TestPoint2D(-1, 0);
        
                Assertions.assertEquals(1, boundaries0.size());
    }

    @Test
    void testTransform_resetsCutBoundary_2_oe_3_oe() {
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();


                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = origBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(4, 5);
        final TestPoint2D end0 = new TestPoint2D(-1, 0);
        
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                PartitionTestUtils.assertPointsEqual(start0, segment0.getStartPoint());
    }

    @Test
    void testTransform_resetsCutBoundary_2_oe_4_oe() {
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();


                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = origBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(4, 5);
        final TestPoint2D end0 = new TestPoint2D(-1, 0);
        
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                PartitionTestUtils.assertPointsEqual(end0, segment0.getEndPoint());
    }

    @Test
    void testTransform_resetsCutBoundary_3_oe_1_oe() {
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();



                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = resultBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(2, 10);
        final TestPoint2D end0 = new TestPoint2D(-0.5, 5);
        Assertions.assertFalse(boundaries0.isEmpty(), "Expected boundary to not be empty");
    }

    @Test
    void testTransform_resetsCutBoundary_3_oe_2_oe() {
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();



                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = resultBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(2, 10);
        final TestPoint2D end0 = new TestPoint2D(-0.5, 5);
        
                Assertions.assertEquals(1, boundaries0.size());
    }

    @Test
    void testTransform_resetsCutBoundary_3_oe_3_oe() {
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();



                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = resultBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(2, 10);
        final TestPoint2D end0 = new TestPoint2D(-0.5, 5);
        
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                PartitionTestUtils.assertPointsEqual(start0, segment0.getStartPoint());
    }

    @Test
    void testTransform_resetsCutBoundary_3_oe_4_oe() {
        insertSkewedBowtie(tree);

        final TestRegionNode node = tree.findNode(new TestPoint2D(1, 1)).getParent();


        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        final RegionCutBoundary<TestPoint2D> origBoundary = node.getCutBoundary();

        tree.transform(t);

        final RegionCutBoundary<TestPoint2D> resultBoundary = node.getCutBoundary();



                final List<HyperplaneConvexSubset<TestPoint2D>> boundaries0 = resultBoundary.getOutsideFacing();
        final TestPoint2D start0 = new TestPoint2D(2, 10);
        final TestPoint2D end0 = new TestPoint2D(-0.5, 5);
        
        
                final TestLineSegment segment0 = (TestLineSegment) boundaries0.get(0);
                PartitionTestUtils.assertPointsEqual(end0, segment0.getEndPoint());
    }

@Test
    void testSetLocation_invalidArgs_1_oe() {
        GeometryTestUtils.assertThrowsWithMessage(() -> root.setLocation(null), IllegalArgumentException.class, "Invalid node location: null");
    }

@Test
    void testSetLocation_invalidArgs_2_oe() {
        GeometryTestUtils.assertThrowsWithMessage(() -> root.setLocation(RegionLocation.BOUNDARY), IllegalArgumentException.class, "Invalid node location: BOUNDARY");
    }

@Test
    void testGetRegionSizeProperties_cachesValueBasedOnVersion_4_oe() {
        final RegionSizeProperties<TestPoint2D> first = tree.getRegionSizeProperties();
        final RegionSizeProperties<TestPoint2D> second = tree.getRegionSizeProperties();
        tree.getRoot().cut(TestLine.X_AXIS);
        final RegionSizeProperties<TestPoint2D> third = tree.getRegionSizeProperties();


        PartitionTestUtils.assertPointsEqual(new TestPoint2D(12, 34), first.getCentroid());
    }

@Test
    void testGetCentroid_1_oe() {
        PartitionTestUtils.assertPointsEqual(new TestPoint2D(12, 34), tree.getCentroid());
    }

@Test
    void testTransform_singleCut_3_oe() {
        tree.getRoot().insertCut(TestLine.X_AXIS);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), p.getY() + 2));

        tree.transform(t);


        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(0, -1), TestPoint2D.ZERO, new TestPoint2D(0, 1));
    }

@Test
    void testTransform_singleCut_5_oe() {
        tree.getRoot().insertCut(TestLine.X_AXIS);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), p.getY() + 2));

        tree.transform(t);




        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, new TestPoint2D(0, 3), new TestPoint2D(0, 4));
    }

@Test
    void testTransform_multipleCuts_3_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        tree.transform(t);


        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, new TestPoint2D(0, 5), new TestPoint2D(-1, 4), new TestPoint2D(1, 6));
    }

@Test
    void testTransform_multipleCuts_4_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        tree.transform(t);



        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY, new TestPoint2D(-2, 4), new TestPoint2D(2, 6));
    }

@Test
    void testTransform_multipleCuts_5_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 5));

        tree.transform(t);




        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(-3, 5), new TestPoint2D(3, 5));
    }

@Test
    void testTransform_xAxisReflection_3_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), p.getY()));

        tree.transform(t);


        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, TestPoint2D.ZERO, new TestPoint2D(-1, 1), new TestPoint2D(1, -1));
    }

@Test
    void testTransform_xAxisReflection_4_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), p.getY()));

        tree.transform(t);



        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY, new TestPoint2D(0, 1), new TestPoint2D(0, -1), new TestPoint2D(-4, 0), new TestPoint2D(4, 0));
    }

@Test
    void testTransform_xAxisReflection_5_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), p.getY()));

        tree.transform(t);




        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(1, 1), new TestPoint2D(-1, -1));
    }

@Test
    void testTransform_yAxisReflection_3_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), -p.getY()));

        tree.transform(t);


        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, TestPoint2D.ZERO, new TestPoint2D(1, -1), new TestPoint2D(-1, 1));
    }

@Test
    void testTransform_yAxisReflection_4_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), -p.getY()));

        tree.transform(t);



        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY, new TestPoint2D(0, 1), new TestPoint2D(0, -1), new TestPoint2D(-4, 0), new TestPoint2D(4, 0));
    }

@Test
    void testTransform_yAxisReflection_5_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), -p.getY()));

        tree.transform(t);




        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(-1, -1), new TestPoint2D(1, 1));
    }

@Test
    void testTransform_xAndYAxisReflection_3_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), -p.getY()));

        tree.transform(t);


        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, TestPoint2D.ZERO, new TestPoint2D(1, 1), new TestPoint2D(-1, -1));
    }

@Test
    void testTransform_xAndYAxisReflection_4_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), -p.getY()));

        tree.transform(t);



        PartitionTestUtils.assertPointLocations(tree, RegionLocation.BOUNDARY, new TestPoint2D(0, 1), new TestPoint2D(0, -1), new TestPoint2D(-4, 0), new TestPoint2D(4, 0));
    }

@Test
    void testTransform_xAndYAxisReflection_5_oe() {
        insertSkewedBowtie(tree);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), -p.getY()));

        tree.transform(t);




        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(-1, 1), new TestPoint2D(1, -1));
    }

@Test
    void testComplement_getCutBoundary_4_oe() {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))));
        tree.complement();

        final RegionCutBoundary<TestPoint2D> xAxisBoundary = root.getCutBoundary();
        final RegionCutBoundary<TestPoint2D> yAxisBoundary = root.getMinus().getCutBoundary();


        final List<HyperplaneConvexSubset<TestPoint2D>> xAxisInsideFacing = xAxisBoundary.getInsideFacing();

        final TestLineSegment xAxisSeg = (TestLineSegment) xAxisInsideFacing.get(0);
        PartitionTestUtils.assertPointsEqual(new TestPoint2D(Double.NEGATIVE_INFINITY, 0), xAxisSeg.getStartPoint());
    }

@Test
    void testComplement_getCutBoundary_5_oe() {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))));
        tree.complement();

        final RegionCutBoundary<TestPoint2D> xAxisBoundary = root.getCutBoundary();
        final RegionCutBoundary<TestPoint2D> yAxisBoundary = root.getMinus().getCutBoundary();


        final List<HyperplaneConvexSubset<TestPoint2D>> xAxisInsideFacing = xAxisBoundary.getInsideFacing();

        final TestLineSegment xAxisSeg = (TestLineSegment) xAxisInsideFacing.get(0);
        PartitionTestUtils.assertPointsEqual(TestPoint2D.ZERO, xAxisSeg.getEndPoint());
    }

@Test
    void testComplement_getCutBoundary_9_oe() {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))));
        tree.complement();

        final RegionCutBoundary<TestPoint2D> xAxisBoundary = root.getCutBoundary();
        final RegionCutBoundary<TestPoint2D> yAxisBoundary = root.getMinus().getCutBoundary();


        final List<HyperplaneConvexSubset<TestPoint2D>> xAxisInsideFacing = xAxisBoundary.getInsideFacing();

        final TestLineSegment xAxisSeg = (TestLineSegment) xAxisInsideFacing.get(0);


        final List<HyperplaneConvexSubset<TestPoint2D>> yAxisInsideFacing = yAxisBoundary.getInsideFacing();

        final TestLineSegment yAxisSeg = (TestLineSegment) yAxisInsideFacing.get(0);
        PartitionTestUtils.assertPointsEqual(TestPoint2D.ZERO, yAxisSeg.getStartPoint());
    }

@Test
    void testComplement_getCutBoundary_10_oe() {
        tree.insert(Arrays.asList(
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(0, 1))));
        tree.complement();

        final RegionCutBoundary<TestPoint2D> xAxisBoundary = root.getCutBoundary();
        final RegionCutBoundary<TestPoint2D> yAxisBoundary = root.getMinus().getCutBoundary();


        final List<HyperplaneConvexSubset<TestPoint2D>> xAxisInsideFacing = xAxisBoundary.getInsideFacing();

        final TestLineSegment xAxisSeg = (TestLineSegment) xAxisInsideFacing.get(0);


        final List<HyperplaneConvexSubset<TestPoint2D>> yAxisInsideFacing = yAxisBoundary.getInsideFacing();

        final TestLineSegment yAxisSeg = (TestLineSegment) yAxisInsideFacing.get(0);
        PartitionTestUtils.assertPointsEqual(new TestPoint2D(0, Double.POSITIVE_INFINITY), yAxisSeg.getEndPoint());
    }

@Test
    void testExtract_1_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree result = fullTree();

        final TestPoint2D pt = new TestPoint2D(2, 2);

        result.extract(tree.findNode(pt));

        PartitionTestUtils.assertPointLocations(result, RegionLocation.INSIDE, new TestPoint2D(0, 0.5), new TestPoint2D(2, 2));
    }

@Test
    void testExtract_2_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree result = fullTree();

        final TestPoint2D pt = new TestPoint2D(2, 2);

        result.extract(tree.findNode(pt));

        PartitionTestUtils.assertPointLocations(result, RegionLocation.OUTSIDE, new TestPoint2D(-2, 2), new TestPoint2D(-2, -2), new TestPoint2D(0, -0.5), new TestPoint2D(-2, 2));
    }

@Test
    void testExtract_3_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree result = fullTree();

        final TestPoint2D pt = new TestPoint2D(2, 2);

        result.extract(tree.findNode(pt));


        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, new TestPoint2D(0, 0.5), new TestPoint2D(2, 2), new TestPoint2D(-2, -2), new TestPoint2D(0, -0.5));
    }

@Test
    void testExtract_4_oe() {
        insertSkewedBowtie(tree);

        final TestRegionBSPTree result = fullTree();

        final TestPoint2D pt = new TestPoint2D(2, 2);

        result.extract(tree.findNode(pt));


        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(2, -2), new TestPoint2D(-2, 2));
    }

@Test
    void testExtract_complementedTree_1_oe() {
        insertSkewedBowtie(tree);
        tree.complement();

        final TestRegionBSPTree result = fullTree();

        final TestPoint2D pt = new TestPoint2D(2, 2);

        result.extract(tree.findNode(pt));

        PartitionTestUtils.assertPointLocations(result, RegionLocation.OUTSIDE, new TestPoint2D(0, 0.5), new TestPoint2D(2, 2));
    }

@Test
    void testExtract_complementedTree_2_oe() {
        insertSkewedBowtie(tree);
        tree.complement();

        final TestRegionBSPTree result = fullTree();

        final TestPoint2D pt = new TestPoint2D(2, 2);

        result.extract(tree.findNode(pt));

        PartitionTestUtils.assertPointLocations(result, RegionLocation.INSIDE, new TestPoint2D(-2, 2), new TestPoint2D(-2, -2), new TestPoint2D(0, -0.5), new TestPoint2D(-2, 2));
    }

@Test
    void testExtract_complementedTree_3_oe() {
        insertSkewedBowtie(tree);
        tree.complement();

        final TestRegionBSPTree result = fullTree();

        final TestPoint2D pt = new TestPoint2D(2, 2);

        result.extract(tree.findNode(pt));


        PartitionTestUtils.assertPointLocations(tree, RegionLocation.OUTSIDE, new TestPoint2D(0, 0.5), new TestPoint2D(2, 2), new TestPoint2D(-2, -2), new TestPoint2D(0, -0.5));
    }

@Test
    void testExtract_complementedTree_4_oe() {
        insertSkewedBowtie(tree);
        tree.complement();

        final TestRegionBSPTree result = fullTree();

        final TestPoint2D pt = new TestPoint2D(2, 2);

        result.extract(tree.findNode(pt));


        PartitionTestUtils.assertPointLocations(tree, RegionLocation.INSIDE, new TestPoint2D(2, -2), new TestPoint2D(-2, 2));
    }

@Test
    void testProject_halfSpace_1_oe() {
        tree.getRoot().cut(TestLine.X_AXIS);

        PartitionTestUtils.assertPointsEqual(TestPoint2D.ZERO, tree.project(TestPoint2D.ZERO));
    }

@Test
    void testProject_halfSpace_2_oe() {
        tree.getRoot().cut(TestLine.X_AXIS);


        PartitionTestUtils.assertPointsEqual(TestPoint2D.ZERO, tree.project(new TestPoint2D(0, 7)));
    }

@Test
    void testProject_halfSpace_3_oe() {
        tree.getRoot().cut(TestLine.X_AXIS);


        PartitionTestUtils.assertPointsEqual(TestPoint2D.ZERO, tree.project(new TestPoint2D(0, -7)));
    }

@Test
    void testProject_halfSpace_4_oe() {
        tree.getRoot().cut(TestLine.X_AXIS);



        PartitionTestUtils.assertPointsEqual(new TestPoint2D(4, 0), tree.project(new TestPoint2D(4, 10)));
    }

@Test
    void testProject_halfSpace_5_oe() {
        tree.getRoot().cut(TestLine.X_AXIS);



        PartitionTestUtils.assertPointsEqual(new TestPoint2D(-5, 0), tree.project(new TestPoint2D(-5, -2)));
    }

@Test
    void testProject_box_1_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        PartitionTestUtils.assertPointsEqual(TestPoint2D.ZERO, tree.project(TestPoint2D.ZERO));
    }

@Test
    void testProject_box_2_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        PartitionTestUtils.assertPointsEqual(TestPoint2D.ZERO, tree.project(new TestPoint2D(-1, -4)));
    }

@Test
    void testProject_box_3_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));


        PartitionTestUtils.assertPointsEqual(new TestPoint2D(1, 1), tree.project(new TestPoint2D(2, 9)));
    }

@Test
    void testProject_box_4_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));



        PartitionTestUtils.assertPointsEqual(new TestPoint2D(0.5, 1), tree.project(new TestPoint2D(0.5, 3)));
    }

@Test
    void testSplit_full_2_oe() {
        tree = fullTree();

        final Split<TestRegionBSPTree> split = tree.split(TestLine.X_AXIS);


        final TestRegionBSPTree minus = split.getMinus();
        PartitionTestUtils.assertPointLocations(minus, RegionLocation.INSIDE, new TestPoint2D(-1, 1), new TestPoint2D(0, 1), new TestPoint2D(1, 1));
    }

@Test
    void testSplit_full_3_oe() {
        tree = fullTree();

        final Split<TestRegionBSPTree> split = tree.split(TestLine.X_AXIS);


        final TestRegionBSPTree minus = split.getMinus();
        PartitionTestUtils.assertPointLocations(minus, RegionLocation.BOUNDARY, new TestPoint2D(-1, 0), new TestPoint2D(0, 0), new TestPoint2D(1, 0));
    }

@Test
    void testSplit_full_4_oe() {
        tree = fullTree();

        final Split<TestRegionBSPTree> split = tree.split(TestLine.X_AXIS);


        final TestRegionBSPTree minus = split.getMinus();
        PartitionTestUtils.assertPointLocations(minus, RegionLocation.OUTSIDE, new TestPoint2D(-1, -1), new TestPoint2D(0, -1), new TestPoint2D(1, -1));
    }

@Test
    void testSplit_full_5_oe() {
        tree = fullTree();

        final Split<TestRegionBSPTree> split = tree.split(TestLine.X_AXIS);


        final TestRegionBSPTree minus = split.getMinus();

        final TestRegionBSPTree plus = split.getPlus();
        PartitionTestUtils.assertPointLocations(plus, RegionLocation.OUTSIDE, new TestPoint2D(-1, 1), new TestPoint2D(0, 1), new TestPoint2D(1, 1));
    }

@Test
    void testSplit_full_6_oe() {
        tree = fullTree();

        final Split<TestRegionBSPTree> split = tree.split(TestLine.X_AXIS);


        final TestRegionBSPTree minus = split.getMinus();

        final TestRegionBSPTree plus = split.getPlus();
        PartitionTestUtils.assertPointLocations(plus, RegionLocation.BOUNDARY, new TestPoint2D(-1, 0), new TestPoint2D(0, 0), new TestPoint2D(1, 0));
    }

@Test
    void testSplit_full_7_oe() {
        tree = fullTree();

        final Split<TestRegionBSPTree> split = tree.split(TestLine.X_AXIS);


        final TestRegionBSPTree minus = split.getMinus();

        final TestRegionBSPTree plus = split.getPlus();
        PartitionTestUtils.assertPointLocations(plus, RegionLocation.INSIDE, new TestPoint2D(-1, -1), new TestPoint2D(0, -1), new TestPoint2D(1, -1));
    }

@Test
    void testSplit_halfSpace_3_oe() {
        tree.getRoot().insertCut(TestLine.X_AXIS);

        final TestLine splitter = TestLine.Y_AXIS;

        final Split<TestRegionBSPTree> split = tree.split(splitter);


        final TestRegionBSPTree minus = split.getMinus();
        PartitionTestUtils.assertPointLocations(minus, RegionLocation.OUTSIDE, new TestPoint2D(1, 1), new TestPoint2D(-1, -1), new TestPoint2D(1, -1));
    }

@Test
    void testSplit_halfSpace_5_oe() {
        tree.getRoot().insertCut(TestLine.X_AXIS);

        final TestLine splitter = TestLine.Y_AXIS;

        final Split<TestRegionBSPTree> split = tree.split(splitter);


        final TestRegionBSPTree minus = split.getMinus();

        final TestRegionBSPTree plus = split.getPlus();
        PartitionTestUtils.assertPointLocations(plus, RegionLocation.OUTSIDE, new TestPoint2D(-1, 1), new TestPoint2D(-1, -1), new TestPoint2D(1, -1));
    }

@Test
    void testSplit_box_3_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, 1));

        final Split<TestRegionBSPTree> split = tree.split(splitter);


        final TestRegionBSPTree minus = split.getMinus();
        PartitionTestUtils.assertPointLocations(minus, RegionLocation.BOUNDARY, new TestPoint2D(0.5, 0), new TestPoint2D(0, 0.5));
    }

@Test
    void testSplit_box_4_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, 1));

        final Split<TestRegionBSPTree> split = tree.split(splitter);


        final TestRegionBSPTree minus = split.getMinus();
        PartitionTestUtils.assertPointLocations(minus, RegionLocation.OUTSIDE, new TestPoint2D(1, 0.5), new TestPoint2D(0.5, 1), new TestPoint2D(0.75, 0.75));
    }

@Test
    void testSplit_box_6_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, 1));

        final Split<TestRegionBSPTree> split = tree.split(splitter);


        final TestRegionBSPTree minus = split.getMinus();

        final TestRegionBSPTree plus = split.getPlus();
        PartitionTestUtils.assertPointLocations(plus, RegionLocation.OUTSIDE, new TestPoint2D(0.5, 0), new TestPoint2D(0, 0.5), new TestPoint2D(0.25, 0.25));
    }

@Test
    void testSplit_box_7_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, 1));

        final Split<TestRegionBSPTree> split = tree.split(splitter);


        final TestRegionBSPTree minus = split.getMinus();

        final TestRegionBSPTree plus = split.getPlus();
        PartitionTestUtils.assertPointLocations(plus, RegionLocation.BOUNDARY, new TestPoint2D(1, 0.5), new TestPoint2D(0.5, 1));
    }

@Test
    void testSplit_box_onMinusOnly_3_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(2, 0), new TestPoint2D(1, 1));

        final Split<TestRegionBSPTree> split = tree.split(splitter);


        final TestRegionBSPTree minus = split.getMinus();
        PartitionTestUtils.assertPointLocations(minus, RegionLocation.BOUNDARY, new TestPoint2D(0.5, 0), new TestPoint2D(0, 0.5), new TestPoint2D(1, 0.5), new TestPoint2D(0.5, 1));
    }

@Test
    void testSplit_box_onPlusOnly_4_oe() {
        insertBox(tree, new TestPoint2D(0, 1), new TestPoint2D(1, 0));

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(-1, 1));

        final Split<TestRegionBSPTree> split = tree.split(splitter);



        final TestRegionBSPTree plus = split.getPlus();
        PartitionTestUtils.assertPointLocations(plus, RegionLocation.BOUNDARY, new TestPoint2D(0.5, 0), new TestPoint2D(0, 0.5), new TestPoint2D(1, 0.5), new TestPoint2D(0.5, 1));
    }

}
