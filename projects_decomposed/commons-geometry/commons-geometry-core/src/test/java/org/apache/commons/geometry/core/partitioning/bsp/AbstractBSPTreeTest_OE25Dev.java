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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.geometry.core.Transform;
import org.apache.commons.geometry.core.partitioning.BoundarySource;
import org.apache.commons.geometry.core.partitioning.bsp.BSPTree.FindNodeCutRule;
import org.apache.commons.geometry.core.partitioning.test.PartitionTestUtils;
import org.apache.commons.geometry.core.partitioning.test.TestBSPTree;
import org.apache.commons.geometry.core.partitioning.test.TestBSPTree.TestNode;
import org.apache.commons.geometry.core.partitioning.test.TestLine;
import org.apache.commons.geometry.core.partitioning.test.TestLineSegment;
import org.apache.commons.geometry.core.partitioning.test.TestLineSegmentCollection;
import org.apache.commons.geometry.core.partitioning.test.TestPoint2D;
import org.apache.commons.geometry.core.partitioning.test.TestTransform2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class AbstractBSPTreeTest_OE25Dev {


    @Test
    void testNodesIterable_iteratorThrowsNoSuchElementExceptionAtEnd() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final Iterator<TestNode> it = tree.nodes().iterator();
        it.next();

        // act
        try {
            it.next();
            Assertions.fail("Operation should have thrown an exception");
        } catch (final NoSuchElementException exc) {
            // expected
        }
    }

    private void assertNodesCopiedRecursive(final TestNode orig, final TestNode copy) {
        Assertions.assertNotSame(orig, copy);

        Assertions.assertEquals(orig.getCut(), copy.getCut());

        if (orig.isLeaf()) {
            Assertions.assertNull(copy.getMinus());
            Assertions.assertNull(copy.getPlus());
        } else {
            Assertions.assertNotSame(orig.getMinus(), copy.getMinus());
            Assertions.assertNotSame(orig.getPlus(), copy.getPlus());

            assertNodesCopiedRecursive(orig.getMinus(), copy.getMinus());
            assertNodesCopiedRecursive(orig.getPlus(), copy.getPlus());
        }

        Assertions.assertEquals(orig.depth(), copy.depth());
        Assertions.assertEquals(orig.count(), copy.count());
    }

    private static List<TestLineSegment> getLineSegments(final TestBSPTree tree) {
        return StreamSupport.stream(tree.nodes().spliterator(), false)
            .filter(BSPTree.Node::isInternal)
            .map(n -> (TestLineSegment) n.getCut())
            .collect(Collectors.toList());
    }

    /** Create a map of points to the nodes that they resolve to in the
     * given tree.
     */
    private static Map<TestPoint2D, TestNode> createPointNodeMap(final TestBSPTree tree, final int min, final int max) {
        final Map<TestPoint2D, TestNode> map = new HashMap<>();

        for (int x = min; x <= max; ++x) {
            for (int y = min; y <= max; ++y) {
                final TestPoint2D pt = new TestPoint2D(x, y);
                final TestNode node = tree.findNode(pt, FindNodeCutRule.NODE);

                map.put(pt, node);
            }
        }

        return map;
    }

    /** Check that transformed points resolve to the same tree nodes that were found when the original
     * points were resolved in the untransformed tree.
     * @param transformedTree
     * @param transform
     * @param pointNodeMap
     */
    private static void checkTransformedPointNodeMap(final TestBSPTree transformedTree, final Transform<TestPoint2D> transform,
                                                     final Map<TestPoint2D, TestNode> pointNodeMap) {

        for (final Map.Entry<TestPoint2D, TestNode> entry : pointNodeMap.entrySet()) {
            final TestNode expectedNode = entry.getValue();
            final TestPoint2D transformedPt = transform.apply(entry.getKey());

            final String msg = "Expected transformed point " + transformedPt + " to resolve to node " + expectedNode;
            Assertions.assertSame(expectedNode, transformedTree.findNode(transformedPt, FindNodeCutRule.NODE), msg);
        }
    }

    private static class TestVisitor implements BSPTreeVisitor<TestPoint2D, TestNode> {

        private final Order order;

        private TestNode terminationNode;

        private final List<TestNode> visited = new ArrayList<>();

        TestVisitor(final Order order) {
            this.order = order;
        }

        public TestVisitor withTerminationNode(final TestNode newTerminationNode) {
            this.terminationNode = newTerminationNode;
            return this;
        }

        @Override
        public Result visit(final TestNode node) {
            visited.add(node);
            return (terminationNode == node) ?
                    Result.TERMINATE :
                    Result.CONTINUE;
        }

        @Override
        public Order visitOrder(final TestNode node) {
            return order;
        }

        public List<TestNode> getVisited() {
            return visited;
        }
    }

    @Test
    void testInitialization_1_oe() {
        // act
        final TestBSPTree tree = new TestBSPTree();

        // assert
        final TestNode root = tree.getRoot();

        Assertions.assertNotNull(root);
    }

    @Test
    void testInitialization_2_oe() {
        // act
        final TestBSPTree tree = new TestBSPTree();

        // assert
        final TestNode root = tree.getRoot();

        // removed other assertion
        Assertions.assertNull(root.getParent());
    }

    @Test
    void testInitialization_4_oe() {
        // act
        final TestBSPTree tree = new TestBSPTree();

        // assert
        final TestNode root = tree.getRoot();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(root.isPlus());
    }

    @Test
    void testInitialization_5_oe() {
        // act
        final TestBSPTree tree = new TestBSPTree();

        // assert
        final TestNode root = tree.getRoot();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(root.isMinus());
    }

    @Test
    void testInitialization_6_oe() {
        // act
        final TestBSPTree tree = new TestBSPTree();

        // assert
        final TestNode root = tree.getRoot();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(tree, root.getTree());
    }

    @Test
    void testNodeStateGetters_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS);

        final TestNode plus = root.getPlus();
        final TestNode minus = root.getMinus();

        // act/assert
        Assertions.assertFalse(root.isLeaf());
    }

    @Test
    void testNodeStateGetters_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS);

        final TestNode plus = root.getPlus();
        final TestNode minus = root.getMinus();

        // act/assert
        // removed other assertion
        Assertions.assertTrue(root.isInternal());
    }

    @Test
    void testNodeStateGetters_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS);

        final TestNode plus = root.getPlus();
        final TestNode minus = root.getMinus();

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(root.isPlus());
    }

    @Test
    void testNodeStateGetters_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS);

        final TestNode plus = root.getPlus();
        final TestNode minus = root.getMinus();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(root.isMinus());
    }

    @Test
    void testNodeStateGetters_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS);

        final TestNode plus = root.getPlus();
        final TestNode minus = root.getMinus();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(plus.isLeaf());
    }

    @Test
    void testNodeStateGetters_6_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS);

        final TestNode plus = root.getPlus();
        final TestNode minus = root.getMinus();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(plus.isInternal());
    }

    @Test
    void testNodeStateGetters_7_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS);

        final TestNode plus = root.getPlus();
        final TestNode minus = root.getMinus();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(plus.isPlus());
    }

    @Test
    void testNodeStateGetters_8_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS);

        final TestNode plus = root.getPlus();
        final TestNode minus = root.getMinus();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(plus.isMinus());
    }

    @Test
    void testNodeStateGetters_9_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS);

        final TestNode plus = root.getPlus();
        final TestNode minus = root.getMinus();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(minus.isLeaf());
    }

    @Test
    void testNodeStateGetters_10_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS);

        final TestNode plus = root.getPlus();
        final TestNode minus = root.getMinus();

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
        Assertions.assertFalse(minus.isInternal());
    }

    @Test
    void testNodeStateGetters_11_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS);

        final TestNode plus = root.getPlus();
        final TestNode minus = root.getMinus();

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
        Assertions.assertFalse(minus.isPlus());
    }

    @Test
    void testNodeStateGetters_12_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS);

        final TestNode plus = root.getPlus();
        final TestNode minus = root.getMinus();

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
        Assertions.assertTrue(minus.isMinus());
    }

    @Test
    void testInsertCut_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final TestLine line = TestLine.X_AXIS;

        // act
        final boolean result = tree.getRoot().insertCut(line);

        // assert
        Assertions.assertTrue(result);
    }

    @Test
    void testInsertCut_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final TestLine line = TestLine.X_AXIS;

        // act
        final boolean result = tree.getRoot().insertCut(line);

        // assert
        // removed other assertion

        final TestNode root = tree.getRoot();
        // removed other assertion

        Assertions.assertSame(line, root.getCut().getHyperplane());
    }

    @Test
    void testInsertCut_fitsCutterToCell_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS)
                .getPlus();

        // act
        final boolean result = node.insertCut(new TestLine(0.5, 1.5, 1.5, 0.5));

        // assert
        Assertions.assertTrue(result);
    }

    @Test
    void testInsertCut_doesNotPassThroughCell_intersects_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
            .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus();

        // act
        final boolean result = node.insertCut(new TestLine(-2, 0, 0, -2));

        // assert
        Assertions.assertFalse(result);
    }

    @Test
    void testInsertCut_doesNotPassThroughCell_parallel_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
            .cut(TestLine.X_AXIS)
                .getMinus();

        // act
        final boolean result = node.insertCut(new TestLine(0, -1, 1, -1));

        // assert
        Assertions.assertFalse(result);
    }

    @Test
    void testInsertCut_doesNotPassThroughCell_removesExistingChildren_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
            .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        // act
        final boolean result = node.insertCut(new TestLine(-2, 0, 0, -2));

        // assert
        Assertions.assertFalse(result);
    }

    @Test
    void testInsertCut_cutExistsInTree_sameOrientation_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
                .cut(TestLine.X_AXIS)
                    .getMinus()
                        .cut(TestLine.Y_AXIS)
                        .getPlus()
                            .cut(new TestLine(0, 2, 2, 0));

        // act
        final boolean result = node.insertCut(new TestLine(0, 2, 0, 3));

        // assert
        Assertions.assertFalse(result);
    }

    @Test
    void testInsertCut_cutExistsInTree_oppositeOrientation_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
                .cut(TestLine.X_AXIS)
                    .getMinus()
                        .cut(TestLine.Y_AXIS)
                        .getPlus()
                            .cut(new TestLine(0, 2, 2, 0));

        // act
        final boolean result = node.insertCut(new TestLine(0, 3, 0, 2));

        // assert
        Assertions.assertTrue(result);
    }

    @Test
    void testInsertCut_createRegionWithThicknessOfHyperplane_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
                .cut(TestLine.X_AXIS)
                    .getMinus();

        // act
        final boolean result = node.insertCut(new TestLine(0, 0, -1, 0));

        // assert
        Assertions.assertTrue(result);
    }

    @Test
    void testInsertCut_createRegionWithThicknessOfHyperplane_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
                .cut(TestLine.X_AXIS)
                    .getMinus();

        // act
        final boolean result = node.insertCut(new TestLine(0, 0, -1, 0));

        // assert
        // removed other assertion

        Assertions.assertSame(tree.getRoot().getPlus(), tree.findNode(new TestPoint2D(0, -1e-2)));
    }

    @Test
    void testInsertCut_createRegionWithThicknessOfHyperplane_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
                .cut(TestLine.X_AXIS)
                    .getMinus();

        // act
        final boolean result = node.insertCut(new TestLine(0, 0, -1, 0));

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(node.getMinus(), tree.findNode(new TestPoint2D(0, 0)));
    }

    @Test
    void testInsertCut_createRegionWithThicknessOfHyperplane_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
                .cut(TestLine.X_AXIS)
                    .getMinus();

        // act
        final boolean result = node.insertCut(new TestLine(0, 0, -1, 0));

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertSame(node.getPlus(), tree.findNode(new TestPoint2D(0, 1e-2)));
    }

    @Test
    void testClearCut_cutExists_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
            .cut(TestLine.X_AXIS)
                .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final boolean result = node.clearCut();

        // assert
        Assertions.assertTrue(result);
    }

    @Test
    void testClearCut_cutExists_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
            .cut(TestLine.X_AXIS)
                .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final boolean result = node.clearCut();

        // assert
        // removed other assertion
        Assertions.assertTrue(node.isLeaf());
    }

    @Test
    void testClearCut_cutExists_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
            .cut(TestLine.X_AXIS)
                .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final boolean result = node.clearCut();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(node.getPlus());
    }

    @Test
    void testClearCut_cutExists_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
            .cut(TestLine.X_AXIS)
                .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final boolean result = node.clearCut();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(node.getMinus());
    }

    @Test
    void testClearCut_cutDoesNotExist_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
            .cut(TestLine.X_AXIS)
                .getMinus()
                .cut(TestLine.Y_AXIS)
                .getMinus();

        // act
        final boolean result = node.clearCut();

        // assert
        Assertions.assertFalse(result);
    }

    @Test
    void testClearCut_cutDoesNotExist_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
            .cut(TestLine.X_AXIS)
                .getMinus()
                .cut(TestLine.Y_AXIS)
                .getMinus();

        // act
        final boolean result = node.clearCut();

        // assert
        // removed other assertion
        Assertions.assertTrue(node.isLeaf());
    }

    @Test
    void testClearCut_cutDoesNotExist_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
            .cut(TestLine.X_AXIS)
                .getMinus()
                .cut(TestLine.Y_AXIS)
                .getMinus();

        // act
        final boolean result = node.clearCut();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(node.getPlus());
    }

    @Test
    void testClearCut_cutDoesNotExist_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
            .cut(TestLine.X_AXIS)
                .getMinus()
                .cut(TestLine.Y_AXIS)
                .getMinus();

        // act
        final boolean result = node.clearCut();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(node.getMinus());
    }

    @Test
    void testClearCut_root_fullTree_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
            .cut(TestLine.X_AXIS)
                .getMinus()
                .cut(TestLine.Y_AXIS)
                .getMinus();

        // act
        final boolean result = tree.getRoot().clearCut();

        // assert
        Assertions.assertTrue(result);
    }

    @Test
    void testClearCut_root_fullTree_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
            .cut(TestLine.X_AXIS)
                .getMinus()
                .cut(TestLine.Y_AXIS)
                .getMinus();

        // act
        final boolean result = tree.getRoot().clearCut();

        // assert
        // removed other assertion
        Assertions.assertTrue(node.isLeaf());
    }

    @Test
    void testClearCut_root_fullTree_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
            .cut(TestLine.X_AXIS)
                .getMinus()
                .cut(TestLine.Y_AXIS)
                .getMinus();

        // act
        final boolean result = tree.getRoot().clearCut();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(node.getPlus());
    }

    @Test
    void testClearCut_root_fullTree_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
            .cut(TestLine.X_AXIS)
                .getMinus()
                .cut(TestLine.Y_AXIS)
                .getMinus();

        // act
        final boolean result = tree.getRoot().clearCut();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(node.getMinus());
    }

    @Test
    void testClearCut_root_fullTree_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode node = tree.getRoot()
            .cut(TestLine.X_AXIS)
                .getMinus()
                .cut(TestLine.Y_AXIS)
                .getMinus();

        // act
        final boolean result = tree.getRoot().clearCut();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testClearCut_root_emptyTree_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final TestNode node = tree.getRoot();

        // act
        final boolean result = node.clearCut();

        // assert
        Assertions.assertFalse(result);
    }

    @Test
    void testClearCut_root_emptyTree_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final TestNode node = tree.getRoot();

        // act
        final boolean result = node.clearCut();

        // assert
        // removed other assertion
        Assertions.assertTrue(node.isLeaf());
    }

    @Test
    void testClearCut_root_emptyTree_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final TestNode node = tree.getRoot();

        // act
        final boolean result = node.clearCut();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(node.getPlus());
    }

    @Test
    void testClearCut_root_emptyTree_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final TestNode node = tree.getRoot();

        // act
        final boolean result = node.clearCut();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(node.getMinus());
    }

    @Test
    void testClearCut_root_emptyTree_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final TestNode node = tree.getRoot();

        // act
        final boolean result = node.clearCut();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testFindNode_emptyTree_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final TestNode root = tree.getRoot();

        final List<TestPoint2D> testPoints = Arrays.asList(
                    new TestPoint2D(0, 0),
                    new TestPoint2D(1, 0),
                    new TestPoint2D(1, 1),
                    new TestPoint2D(0, 1),
                    new TestPoint2D(-1, 1),
                    new TestPoint2D(-1, 0),
                    new TestPoint2D(-1, -1),
                    new TestPoint2D(0, -1),
                    new TestPoint2D(1, -1)
                );

        // act/assert
        for (final TestPoint2D pt : testPoints) {
            Assertions.assertSame(root, tree.findNode(pt));
    }
    }

    @Test
    void testFindNode_emptyTree_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final TestNode root = tree.getRoot();

        final List<TestPoint2D> testPoints = Arrays.asList(
                    new TestPoint2D(0, 0),
                    new TestPoint2D(1, 0),
                    new TestPoint2D(1, 1),
                    new TestPoint2D(0, 1),
                    new TestPoint2D(-1, 1),
                    new TestPoint2D(-1, 0),
                    new TestPoint2D(-1, -1),
                    new TestPoint2D(0, -1),
                    new TestPoint2D(1, -1)
                );

        // act/assert
        for (final TestPoint2D pt : testPoints) {
            // removed other assertion
        }

        for (final TestPoint2D pt : testPoints) {
            Assertions.assertSame(root, tree.findNode(pt, FindNodeCutRule.NODE));
    }
    }

    @Test
    void testFindNode_emptyTree_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final TestNode root = tree.getRoot();

        final List<TestPoint2D> testPoints = Arrays.asList(
                    new TestPoint2D(0, 0),
                    new TestPoint2D(1, 0),
                    new TestPoint2D(1, 1),
                    new TestPoint2D(0, 1),
                    new TestPoint2D(-1, 1),
                    new TestPoint2D(-1, 0),
                    new TestPoint2D(-1, -1),
                    new TestPoint2D(0, -1),
                    new TestPoint2D(1, -1)
                );

        // act/assert
        for (final TestPoint2D pt : testPoints) {
            // removed other assertion
        }

        for (final TestPoint2D pt : testPoints) {
            // removed other assertion
        }

        for (final TestPoint2D pt : testPoints) {
            Assertions.assertSame(root, tree.findNode(pt, FindNodeCutRule.MINUS));
    }
    }

    @Test
    void testFindNode_emptyTree_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final TestNode root = tree.getRoot();

        final List<TestPoint2D> testPoints = Arrays.asList(
                    new TestPoint2D(0, 0),
                    new TestPoint2D(1, 0),
                    new TestPoint2D(1, 1),
                    new TestPoint2D(0, 1),
                    new TestPoint2D(-1, 1),
                    new TestPoint2D(-1, 0),
                    new TestPoint2D(-1, -1),
                    new TestPoint2D(0, -1),
                    new TestPoint2D(1, -1)
                );

        // act/assert
        for (final TestPoint2D pt : testPoints) {
            // removed other assertion
        }

        for (final TestPoint2D pt : testPoints) {
            // removed other assertion
        }

        for (final TestPoint2D pt : testPoints) {
            // removed other assertion
        }

        for (final TestPoint2D pt : testPoints) {
            Assertions.assertSame(root, tree.findNode(pt, FindNodeCutRule.PLUS));
    }
    }

    @Test
    void testFindNode_singleArg_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        // act/assert
        Assertions.assertSame(minusXPlusY, tree.findNode(new TestPoint2D(0, 0)));
    }

    @Test
    void testFindNode_singleArg_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        // act/assert
        // removed other assertion

        Assertions.assertSame(underDiagonal, tree.findNode(new TestPoint2D(1, 0)));
    }

    @Test
    void testFindNode_singleArg_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(aboveDiagonal, tree.findNode(new TestPoint2D(1, 1)));
    }

    @Test
    void testFindNode_singleArg_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusXPlusY, tree.findNode(new TestPoint2D(0, 1)));
    }

    @Test
    void testFindNode_singleArg_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusXPlusY, tree.findNode(new TestPoint2D(-1, 1)));
    }

    @Test
    void testFindNode_singleArg_6_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusXPlusY, tree.findNode(new TestPoint2D(-1, 0)));
    }

    @Test
    void testFindNode_singleArg_7_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusY, tree.findNode(new TestPoint2D(-1, -1)));
    }

    @Test
    void testFindNode_singleArg_8_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusY, tree.findNode(new TestPoint2D(0, -1)));
    }

    @Test
    void testFindNode_singleArg_9_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusY, tree.findNode(new TestPoint2D(1, -1)));
    }

    @Test
    void testFindNode_singleArg_10_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

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

        Assertions.assertSame(underDiagonal, tree.findNode(new TestPoint2D(0.5, 0.5)));
    }

    @Test
    void testFindNode_singleArg_11_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

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
        Assertions.assertSame(aboveDiagonal, tree.findNode(new TestPoint2D(3, 3)));
    }

    @Test
    void testFindNode_nodeCutBehavior_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.NODE;

        // act/assert
        Assertions.assertSame(root, tree.findNode(new TestPoint2D(0, 0), cutBehavior));
    }

    @Test
    void testFindNode_nodeCutBehavior_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.NODE;

        // act/assert
        // removed other assertion

        Assertions.assertSame(root, tree.findNode(new TestPoint2D(1, 0), cutBehavior));
    }

    @Test
    void testFindNode_nodeCutBehavior_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.NODE;

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(diagonalCut, tree.findNode(new TestPoint2D(1, 1), cutBehavior));
    }

    @Test
    void testFindNode_nodeCutBehavior_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.NODE;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertSame(yCut, tree.findNode(new TestPoint2D(0, 1), cutBehavior));
    }

    @Test
    void testFindNode_nodeCutBehavior_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.NODE;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusXPlusY, tree.findNode(new TestPoint2D(-1, 1), cutBehavior));
    }

    @Test
    void testFindNode_nodeCutBehavior_6_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.NODE;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(root, tree.findNode(new TestPoint2D(-1, 0), cutBehavior));
    }

    @Test
    void testFindNode_nodeCutBehavior_7_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.NODE;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusY, tree.findNode(new TestPoint2D(-1, -1), cutBehavior));
    }

    @Test
    void testFindNode_nodeCutBehavior_8_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.NODE;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusY, tree.findNode(new TestPoint2D(0, -1), cutBehavior));
    }

    @Test
    void testFindNode_nodeCutBehavior_9_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.NODE;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusY, tree.findNode(new TestPoint2D(1, -1), cutBehavior));
    }

    @Test
    void testFindNode_nodeCutBehavior_10_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.NODE;

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

        Assertions.assertSame(underDiagonal, tree.findNode(new TestPoint2D(0.5, 0.5), cutBehavior));
    }

    @Test
    void testFindNode_nodeCutBehavior_11_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.NODE;

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
        Assertions.assertSame(aboveDiagonal, tree.findNode(new TestPoint2D(3, 3), cutBehavior));
    }

    @Test
    void testFindNode_minusCutBehavior_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.MINUS;

        // act/assert
        Assertions.assertSame(minusXPlusY, tree.findNode(new TestPoint2D(0, 0), cutBehavior));
    }

    @Test
    void testFindNode_minusCutBehavior_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.MINUS;

        // act/assert
        // removed other assertion

        Assertions.assertSame(underDiagonal, tree.findNode(new TestPoint2D(1, 0), cutBehavior));
    }

    @Test
    void testFindNode_minusCutBehavior_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.MINUS;

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(aboveDiagonal, tree.findNode(new TestPoint2D(1, 1), cutBehavior));
    }

    @Test
    void testFindNode_minusCutBehavior_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.MINUS;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusXPlusY, tree.findNode(new TestPoint2D(0, 1), cutBehavior));
    }

    @Test
    void testFindNode_minusCutBehavior_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.MINUS;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusXPlusY, tree.findNode(new TestPoint2D(-1, 1), cutBehavior));
    }

    @Test
    void testFindNode_minusCutBehavior_6_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.MINUS;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusXPlusY, tree.findNode(new TestPoint2D(-1, 0), cutBehavior));
    }

    @Test
    void testFindNode_minusCutBehavior_7_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.MINUS;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusY, tree.findNode(new TestPoint2D(-1, -1), cutBehavior));
    }

    @Test
    void testFindNode_minusCutBehavior_8_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.MINUS;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusY, tree.findNode(new TestPoint2D(0, -1), cutBehavior));
    }

    @Test
    void testFindNode_minusCutBehavior_9_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.MINUS;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusY, tree.findNode(new TestPoint2D(1, -1), cutBehavior));
    }

    @Test
    void testFindNode_minusCutBehavior_10_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.MINUS;

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

        Assertions.assertSame(underDiagonal, tree.findNode(new TestPoint2D(0.5, 0.5), cutBehavior));
    }

    @Test
    void testFindNode_minusCutBehavior_11_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.MINUS;

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
        Assertions.assertSame(aboveDiagonal, tree.findNode(new TestPoint2D(3, 3), cutBehavior));
    }

    @Test
    void testFindNode_plusCutBehavior_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.PLUS;

        // act/assert
        Assertions.assertSame(minusY, tree.findNode(new TestPoint2D(0, 0), cutBehavior));
    }

    @Test
    void testFindNode_plusCutBehavior_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.PLUS;

        // act/assert
        // removed other assertion

        Assertions.assertSame(minusY, tree.findNode(new TestPoint2D(1, 0), cutBehavior));
    }

    @Test
    void testFindNode_plusCutBehavior_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.PLUS;

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(underDiagonal, tree.findNode(new TestPoint2D(1, 1), cutBehavior));
    }

    @Test
    void testFindNode_plusCutBehavior_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.PLUS;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertSame(underDiagonal, tree.findNode(new TestPoint2D(0, 1), cutBehavior));
    }

    @Test
    void testFindNode_plusCutBehavior_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.PLUS;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusXPlusY, tree.findNode(new TestPoint2D(-1, 1), cutBehavior));
    }

    @Test
    void testFindNode_plusCutBehavior_6_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.PLUS;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusY, tree.findNode(new TestPoint2D(-1, 0), cutBehavior));
    }

    @Test
    void testFindNode_plusCutBehavior_7_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.PLUS;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusY, tree.findNode(new TestPoint2D(-1, -1), cutBehavior));
    }

    @Test
    void testFindNode_plusCutBehavior_8_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.PLUS;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusY, tree.findNode(new TestPoint2D(0, -1), cutBehavior));
    }

    @Test
    void testFindNode_plusCutBehavior_9_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.PLUS;

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(minusY, tree.findNode(new TestPoint2D(1, -1), cutBehavior));
    }

    @Test
    void testFindNode_plusCutBehavior_10_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.PLUS;

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

        Assertions.assertSame(underDiagonal, tree.findNode(new TestPoint2D(0.5, 0.5), cutBehavior));
    }

    @Test
    void testFindNode_plusCutBehavior_11_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        tree.getRoot()
                .cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                    .getPlus()
                        .cut(new TestLine(0, 2, 2, 0));

        final TestNode root = tree.getRoot();
        final TestNode minusY = root.getPlus();

        final TestNode yCut = root.getMinus();
        final TestNode minusXPlusY = yCut.getMinus();

        final TestNode diagonalCut = yCut.getPlus();
        final TestNode underDiagonal = diagonalCut.getPlus();
        final TestNode aboveDiagonal = diagonalCut.getMinus();

        final FindNodeCutRule cutBehavior = FindNodeCutRule.PLUS;

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
        Assertions.assertSame(aboveDiagonal, tree.findNode(new TestPoint2D(3, 3), cutBehavior));
    }

    @Test
    void testInsert_convex_emptyTree_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act
        tree.insert(new TestLineSegment(1, 0, 1, 1));

        // assert
        final TestNode root = tree.getRoot();
        Assertions.assertFalse(root.isLeaf());
    }

    @Test
    void testInsert_convex_emptyTree_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act
        tree.insert(new TestLineSegment(1, 0, 1, 1));

        // assert
        final TestNode root = tree.getRoot();
        // removed other assertion
        Assertions.assertTrue(root.getMinus().isLeaf());
    }

    @Test
    void testInsert_convex_emptyTree_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act
        tree.insert(new TestLineSegment(1, 0, 1, 1));

        // assert
        final TestNode root = tree.getRoot();
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(root.getPlus().isLeaf());
    }

    @Test
    void testInsert_convex_noSplit_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        tree.insert(new TestLineSegment(0.5, 1.5, 1.5, 0.5));

        // assert
        final TestNode root = tree.getRoot();
        Assertions.assertFalse(root.isLeaf());
    }

    @Test
    void testInsert_convex_noSplit_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        tree.insert(new TestLineSegment(0.5, 1.5, 1.5, 0.5));

        // assert
        final TestNode root = tree.getRoot();
        // removed other assertion

        final TestNode node = tree.findNode(new TestPoint2D(0.5, 0.5));
        final TestLineSegment seg = (TestLineSegment) node.getParent().getCut();

        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(tree.getRoot().getPlus().isLeaf());
    }

    @Test
    void testInsert_convex_noSplit_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        tree.insert(new TestLineSegment(0.5, 1.5, 1.5, 0.5));

        // assert
        final TestNode root = tree.getRoot();
        // removed other assertion

        final TestNode node = tree.findNode(new TestPoint2D(0.5, 0.5));
        final TestLineSegment seg = (TestLineSegment) node.getParent().getCut();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(tree.getRoot().getMinus().getMinus().isLeaf());
    }

    @Test
    void testInsert_convex_split_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        tree.insert(new TestLineSegment(-0.5, 2.5, 2.5, -0.5));

        // assert
        final TestNode root = tree.getRoot();
        Assertions.assertFalse(root.isLeaf());
    }

    @Test
    void testInsert_convexList_convexRegion_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestLineSegment a = new TestLineSegment(0, 0, 1, 0);
        final TestLineSegment b = new TestLineSegment(1, 0, 0, 1);
        final TestLineSegment c = new TestLineSegment(0, 1, 0, 0);

        // act
        tree.insert(Arrays.asList(a, b, c));

        // assert
        final List<TestLineSegment> segments = getLineSegments(tree);

        Assertions.assertEquals(3, segments.size());
    }

    @Test
    void testInsert_convexList_concaveRegion_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestLineSegment a = new TestLineSegment(-1, -1, 1, -1);
        final TestLineSegment b = new TestLineSegment(1, -1, 0, 0);
        final TestLineSegment c = new TestLineSegment(0, 0, 1, 1);
        final TestLineSegment d = new TestLineSegment(1, 1, -1, 1);
        final TestLineSegment e = new TestLineSegment(-1, 1, -1, -1);

        // act
        tree.insert(Arrays.asList(a, b, c, d, e));

        // assert
        final List<TestLineSegment> segments = getLineSegments(tree);

        Assertions.assertEquals(5, segments.size());
    }

    @Test
    void testInsert_hyperplaneSubset_concaveRegion_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestLineSegment a = new TestLineSegment(-1, -1, 1, -1);
        final TestLineSegment b = new TestLineSegment(1, -1, 0, 0);
        final TestLineSegment c = new TestLineSegment(0, 0, 1, 1);
        final TestLineSegment d = new TestLineSegment(1, 1, -1, 1);
        final TestLineSegment e = new TestLineSegment(-1, 1, -1, -1);

        final TestLineSegmentCollection coll = new TestLineSegmentCollection(
                Arrays.asList(a, b, c, d, e));

        // act
        tree.insert(coll);

        // assert
        final List<TestLineSegment> segments = getLineSegments(tree);

        Assertions.assertEquals(5, segments.size());
    }

    @Test
    void testInsert_boundarySource_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestLineSegment a = new TestLineSegment(-1, -1, 1, -1);
        final TestLineSegment b = new TestLineSegment(1, -1, 0, 0);
        final TestLineSegment c = new TestLineSegment(0, 0, 1, 1);
        final TestLineSegment d = new TestLineSegment(1, 1, -1, 1);
        final TestLineSegment e = new TestLineSegment(-1, 1, -1, -1);

        final BoundarySource<TestLineSegment> src = () -> Stream.of(a, b, c, d, e);

        // act
        tree.insert(src);

        // assert
        final List<TestLineSegment> segments = getLineSegments(tree);

        Assertions.assertEquals(5, segments.size());
    }

    @Test
    void testInsert_boundarySource_emptySource_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final BoundarySource<TestLineSegment> src = Stream::empty;

        // act
        tree.insert(src);

        // assert
        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testCount_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testCount_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        Assertions.assertEquals(1, tree.getRoot().count());
    }

    @Test
    void testCount_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        Assertions.assertEquals(1, tree.getRoot().getMinus().count());
    }

    @Test
    void testCount_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        Assertions.assertEquals(1, tree.getRoot().getPlus().count());
    }

    @Test
    void testCount_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(3, tree.count());
    }

    @Test
    void testCount_6_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        Assertions.assertEquals(1, tree.getRoot().getMinus().count());
    }

    @Test
    void testCount_7_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        Assertions.assertEquals(3, tree.getRoot().getPlus().count());
    }

    @Test
    void testCount_8_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(5, tree.count());
    }

    @Test
    void testCount_9_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(TestLine.Y_AXIS);
        Assertions.assertEquals(3, tree.getRoot().getMinus().count());
    }

    @Test
    void testCount_10_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        Assertions.assertEquals(3, tree.getRoot().getPlus().count());
    }

    @Test
    void testCount_11_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(7, tree.count());
    }

    @Test
    void testCount_12_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(new TestLine(new TestPoint2D(-1, -1), new TestPoint2D(1, -1)));
        Assertions.assertEquals(1, tree.getRoot().getMinus().count());
    }

    @Test
    void testCount_13_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(new TestLine(new TestPoint2D(-1, -1), new TestPoint2D(1, -1)));
        // removed other assertion
        Assertions.assertEquals(3, tree.getRoot().getPlus().count());
    }

    @Test
    void testCount_14_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(new TestLine(new TestPoint2D(-1, -1), new TestPoint2D(1, -1)));
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(5, tree.count());
    }

    @Test
    void testHeight_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        Assertions.assertEquals(0, tree.height());
    }

    @Test
    void testHeight_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        Assertions.assertEquals(0, tree.getRoot().height());
    }

    @Test
    void testHeight_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        Assertions.assertEquals(0, tree.getRoot().getMinus().height());
    }

    @Test
    void testHeight_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        Assertions.assertEquals(0, tree.getRoot().getPlus().height());
    }

    @Test
    void testHeight_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, tree.height());
    }

    @Test
    void testHeight_6_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        Assertions.assertEquals(0, tree.getRoot().getMinus().height());
    }

    @Test
    void testHeight_7_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        Assertions.assertEquals(1, tree.getRoot().getPlus().height());
    }

    @Test
    void testHeight_8_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(2, tree.height());
    }

    @Test
    void testHeight_9_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(TestLine.Y_AXIS);
        Assertions.assertEquals(1, tree.getRoot().getMinus().height());
    }

    @Test
    void testHeight_10_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        Assertions.assertEquals(1, tree.getRoot().getPlus().height());
    }

    @Test
    void testHeight_11_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(2, tree.height());
    }

    @Test
    void testHeight_12_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().clearCut();
        Assertions.assertEquals(0, tree.getRoot().getMinus().height());
    }

    @Test
    void testHeight_13_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().clearCut();
        // removed other assertion
        Assertions.assertEquals(1, tree.getRoot().getPlus().height());
    }

    @Test
    void testHeight_14_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().clearCut();
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(2, tree.height());
    }

    @Test
    void testHeight_15_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().clearCut();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().getPlus()
            .insertCut(new TestLine(new TestPoint2D(0, -1), new TestPoint2D(1, -1)));

        Assertions.assertEquals(0, tree.getRoot().getMinus().height());
    }

    @Test
    void testHeight_16_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().clearCut();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().getPlus()
            .insertCut(new TestLine(new TestPoint2D(0, -1), new TestPoint2D(1, -1)));

        // removed other assertion
        Assertions.assertEquals(2, tree.getRoot().getPlus().height());
    }

    @Test
    void testHeight_17_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act/assert
        // removed other assertion
        // removed other assertion

        tree.getRoot().insertCut(TestLine.X_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().insertCut(TestLine.Y_AXIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getMinus().clearCut();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tree.getRoot().getPlus().getPlus()
            .insertCut(new TestLine(new TestPoint2D(0, -1), new TestPoint2D(1, -1)));

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(3, tree.height());
    }

    @Test
    void testDepth_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS);

        // act/assert
        Assertions.assertEquals(0, root.depth());
    }

    @Test
    void testDepth_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS);

        // act/assert
        // removed other assertion

        Assertions.assertEquals(1, root.getPlus().depth());
    }

    @Test
    void testDepth_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS);

        // act/assert
        // removed other assertion

        // removed other assertion

        Assertions.assertEquals(1, root.getMinus().depth());
    }

    @Test
    void testDepth_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS);

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(2, root.getMinus().getPlus().depth());
    }

    @Test
    void testDepth_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS);

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(2, root.getMinus().getMinus().depth());
    }

    @Test
    void testDepth_detachedNodes_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode detached = new TestNode(tree);
        detached.cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act/assert
        Assertions.assertEquals(-1, detached.depth());
    }

    @Test
    void testDepth_detachedNodes_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode detached = new TestNode(tree);
        detached.cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act/assert
        // removed other assertion

        Assertions.assertEquals(-1, detached.getPlus().depth());
    }

    @Test
    void testDepth_detachedNodes_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode detached = new TestNode(tree);
        detached.cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act/assert
        // removed other assertion

        // removed other assertion

        Assertions.assertEquals(-1, detached.getMinus().depth());
    }

    @Test
    void testDepth_detachedNodes_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode detached = new TestNode(tree);
        detached.cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(-1, detached.getMinus().getPlus().depth());
    }

    @Test
    void testDepth_detachedNodes_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode detached = new TestNode(tree);
        detached.cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act/assert
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-1, detached.getMinus().getMinus().depth());
    }

    @Test
    void testVisit_defaultOrder_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode minus = root.getMinus();
        final TestNode plus = root.getPlus();
        final TestNode minusMinus = minus.getMinus();
        final TestNode minusPlus = minus.getPlus();

        final List<TestNode> nodes = new ArrayList<>();

        // act
        tree.accept(node -> {
            nodes.add(node);
            return BSPTreeVisitor.Result.CONTINUE;
        });

        // assert
        Assertions.assertEquals(Arrays.asList(root,minus,minusMinus,minusPlus,plus),nodes);
    }

    @Test
    void testVisit_specifiedOrder_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode minus = root.getMinus();
        final TestNode plus = root.getPlus();
        final TestNode minusMinus = minus.getMinus();
        final TestNode minusPlus = minus.getPlus();

        // act/assert
        final TestVisitor plusMinusNode = new TestVisitor(BSPTreeVisitor.Order.PLUS_MINUS_NODE);
        tree.accept(plusMinusNode);
        Assertions.assertEquals(Arrays.asList(plus,minusPlus,minusMinus,minus,root),plusMinusNode.getVisited());
    }

    @Test
    void testVisit_specifiedOrder_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode minus = root.getMinus();
        final TestNode plus = root.getPlus();
        final TestNode minusMinus = minus.getMinus();
        final TestNode minusPlus = minus.getPlus();

        // act/assert
        final TestVisitor plusMinusNode = new TestVisitor(BSPTreeVisitor.Order.PLUS_MINUS_NODE);
        tree.accept(plusMinusNode);
        // removed other assertion

        final TestVisitor plusNodeMinus = new TestVisitor(BSPTreeVisitor.Order.PLUS_NODE_MINUS);
        tree.accept(plusNodeMinus);
        Assertions.assertEquals(Arrays.asList(plus,root,minusPlus,minus,minusMinus),plusNodeMinus.getVisited());
    }

    @Test
    void testVisit_specifiedOrder_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode minus = root.getMinus();
        final TestNode plus = root.getPlus();
        final TestNode minusMinus = minus.getMinus();
        final TestNode minusPlus = minus.getPlus();

        // act/assert
        final TestVisitor plusMinusNode = new TestVisitor(BSPTreeVisitor.Order.PLUS_MINUS_NODE);
        tree.accept(plusMinusNode);
        // removed other assertion

        final TestVisitor plusNodeMinus = new TestVisitor(BSPTreeVisitor.Order.PLUS_NODE_MINUS);
        tree.accept(plusNodeMinus);
        // removed other assertion

        final TestVisitor minusPlusNode = new TestVisitor(BSPTreeVisitor.Order.MINUS_PLUS_NODE);
        tree.accept(minusPlusNode);
        Assertions.assertEquals(Arrays.asList(minusMinus,minusPlus,minus,plus,root),minusPlusNode.getVisited());
    }

    @Test
    void testVisit_specifiedOrder_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode minus = root.getMinus();
        final TestNode plus = root.getPlus();
        final TestNode minusMinus = minus.getMinus();
        final TestNode minusPlus = minus.getPlus();

        // act/assert
        final TestVisitor plusMinusNode = new TestVisitor(BSPTreeVisitor.Order.PLUS_MINUS_NODE);
        tree.accept(plusMinusNode);
        // removed other assertion

        final TestVisitor plusNodeMinus = new TestVisitor(BSPTreeVisitor.Order.PLUS_NODE_MINUS);
        tree.accept(plusNodeMinus);
        // removed other assertion

        final TestVisitor minusPlusNode = new TestVisitor(BSPTreeVisitor.Order.MINUS_PLUS_NODE);
        tree.accept(minusPlusNode);
        // removed other assertion

        final TestVisitor minusNodePlus = new TestVisitor(BSPTreeVisitor.Order.MINUS_NODE_PLUS);
        tree.accept(minusNodePlus);
        Assertions.assertEquals(Arrays.asList(minusMinus,minus,minusPlus,root,plus),minusNodePlus.getVisited());
    }

    @Test
    void testVisit_specifiedOrder_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode minus = root.getMinus();
        final TestNode plus = root.getPlus();
        final TestNode minusMinus = minus.getMinus();
        final TestNode minusPlus = minus.getPlus();

        // act/assert
        final TestVisitor plusMinusNode = new TestVisitor(BSPTreeVisitor.Order.PLUS_MINUS_NODE);
        tree.accept(plusMinusNode);
        // removed other assertion

        final TestVisitor plusNodeMinus = new TestVisitor(BSPTreeVisitor.Order.PLUS_NODE_MINUS);
        tree.accept(plusNodeMinus);
        // removed other assertion

        final TestVisitor minusPlusNode = new TestVisitor(BSPTreeVisitor.Order.MINUS_PLUS_NODE);
        tree.accept(minusPlusNode);
        // removed other assertion

        final TestVisitor minusNodePlus = new TestVisitor(BSPTreeVisitor.Order.MINUS_NODE_PLUS);
        tree.accept(minusNodePlus);
        // removed other assertion

        final TestVisitor nodeMinusPlus = new TestVisitor(BSPTreeVisitor.Order.NODE_MINUS_PLUS);
        tree.accept(nodeMinusPlus);
        Assertions.assertEquals(Arrays.asList(root,minus,minusMinus,minusPlus,plus),nodeMinusPlus.getVisited());
    }

    @Test
    void testVisit_specifiedOrder_6_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode minus = root.getMinus();
        final TestNode plus = root.getPlus();
        final TestNode minusMinus = minus.getMinus();
        final TestNode minusPlus = minus.getPlus();

        // act/assert
        final TestVisitor plusMinusNode = new TestVisitor(BSPTreeVisitor.Order.PLUS_MINUS_NODE);
        tree.accept(plusMinusNode);
        // removed other assertion

        final TestVisitor plusNodeMinus = new TestVisitor(BSPTreeVisitor.Order.PLUS_NODE_MINUS);
        tree.accept(plusNodeMinus);
        // removed other assertion

        final TestVisitor minusPlusNode = new TestVisitor(BSPTreeVisitor.Order.MINUS_PLUS_NODE);
        tree.accept(minusPlusNode);
        // removed other assertion

        final TestVisitor minusNodePlus = new TestVisitor(BSPTreeVisitor.Order.MINUS_NODE_PLUS);
        tree.accept(minusNodePlus);
        // removed other assertion

        final TestVisitor nodeMinusPlus = new TestVisitor(BSPTreeVisitor.Order.NODE_MINUS_PLUS);
        tree.accept(nodeMinusPlus);
        // removed other assertion

        final TestVisitor nodePlusMinus = new TestVisitor(BSPTreeVisitor.Order.NODE_PLUS_MINUS);
        tree.accept(nodePlusMinus);
        Assertions.assertEquals(Arrays.asList(root,plus,minus,minusPlus,minusMinus),nodePlusMinus.getVisited());
    }

    @Test
    void testVisit_nullVisitOrderSkipsSubtree_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode plus = root.getPlus();
        final TestNode minus = root.getMinus();

        final TestVisitor visitor = new TestVisitor(BSPTreeVisitor.Order.NODE_MINUS_PLUS) {
            @Override
            public Order visitOrder(final TestNode node) {
                if (node == minus) {
                    return null;
                }
                return super.visitOrder(node);
            }
        };

        // act
        tree.accept(visitor);

        // assert
        Assertions.assertEquals(Arrays.asList(root,plus),visitor.getVisited());
    }

    @Test
    void testVisit_noneVisitOrderSkipsSubtree_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode plus = root.getPlus();
        final TestNode minus = root.getMinus();

        final TestVisitor visitor = new TestVisitor(BSPTreeVisitor.Order.NODE_MINUS_PLUS) {
            @Override
            public Order visitOrder(final TestNode node) {
                if (node == minus) {
                    return Order.NONE;
                }
                return super.visitOrder(node);
            }
        };

        // act
        tree.accept(visitor);

        // assert
        Assertions.assertEquals(Arrays.asList(root,plus),visitor.getVisited());
    }

    @Test
    void testVisit_visitorReturnsNull_terminatesEarly_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode minus = root.getMinus();
        final TestNode minusMinus = minus.getMinus();
        final TestNode minusPlus = minus.getPlus();

        final TestVisitor visitor = new TestVisitor(BSPTreeVisitor.Order.MINUS_PLUS_NODE) {
            @Override
            public Result visit(final TestNode node) {
                super.visit(node);

                if (node == minus) {
                    return null;
                }
                return Result.CONTINUE;
            }
        };

        // act
        tree.accept(visitor);

        // assert
        Assertions.assertEquals(Arrays.asList(minusMinus,minusPlus,minus),visitor.getVisited());
    }

    @Test
    void testVisit_visitorReturnsTerminate_terminatesEarly_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode minus = root.getMinus();
        final TestNode minusMinus = minus.getMinus();
        final TestNode minusPlus = minus.getPlus();

        final TestVisitor visitor = new TestVisitor(BSPTreeVisitor.Order.MINUS_PLUS_NODE) {
            @Override
            public Result visit(final TestNode node) {
                super.visit(node);

                if (node == minus) {
                    return Result.TERMINATE;
                }
                return Result.CONTINUE;
            }
        };

        // act
        tree.accept(visitor);

        // assert
        Assertions.assertEquals(Arrays.asList(minusMinus,minusPlus,minus),visitor.getVisited());
    }

    @Test
    void testVisit_earlyTerminationPermutations_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode minus = root.getMinus();
        final TestNode plus = root.getPlus();
        final TestNode minusMinus = minus.getMinus();
        final TestNode minusPlus = minus.getPlus();

        // act/assert
        final TestVisitor plusMinusNode = new TestVisitor(BSPTreeVisitor.Order.PLUS_MINUS_NODE).withTerminationNode(minus);
        tree.accept(plusMinusNode);
        Assertions.assertEquals(Arrays.asList(plus,minusPlus,minusMinus,minus),plusMinusNode.getVisited());
    }

    @Test
    void testVisit_earlyTerminationPermutations_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode minus = root.getMinus();
        final TestNode plus = root.getPlus();
        final TestNode minusMinus = minus.getMinus();
        final TestNode minusPlus = minus.getPlus();

        // act/assert
        final TestVisitor plusMinusNode = new TestVisitor(BSPTreeVisitor.Order.PLUS_MINUS_NODE).withTerminationNode(minus);
        tree.accept(plusMinusNode);
        // removed other assertion

        final TestVisitor plusNodeMinus = new TestVisitor(BSPTreeVisitor.Order.PLUS_NODE_MINUS).withTerminationNode(minus);
        tree.accept(plusNodeMinus);
        Assertions.assertEquals(Arrays.asList(plus,root,minusPlus,minus),plusNodeMinus.getVisited());
    }

    @Test
    void testVisit_earlyTerminationPermutations_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode minus = root.getMinus();
        final TestNode plus = root.getPlus();
        final TestNode minusMinus = minus.getMinus();
        final TestNode minusPlus = minus.getPlus();

        // act/assert
        final TestVisitor plusMinusNode = new TestVisitor(BSPTreeVisitor.Order.PLUS_MINUS_NODE).withTerminationNode(minus);
        tree.accept(plusMinusNode);
        // removed other assertion

        final TestVisitor plusNodeMinus = new TestVisitor(BSPTreeVisitor.Order.PLUS_NODE_MINUS).withTerminationNode(minus);
        tree.accept(plusNodeMinus);
        // removed other assertion

        final TestVisitor minusPlusNode = new TestVisitor(BSPTreeVisitor.Order.MINUS_PLUS_NODE).withTerminationNode(minus);
        tree.accept(minusPlusNode);
        Assertions.assertEquals(Arrays.asList(minusMinus,minusPlus,minus),minusPlusNode.getVisited());
    }

    @Test
    void testVisit_earlyTerminationPermutations_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode minus = root.getMinus();
        final TestNode plus = root.getPlus();
        final TestNode minusMinus = minus.getMinus();
        final TestNode minusPlus = minus.getPlus();

        // act/assert
        final TestVisitor plusMinusNode = new TestVisitor(BSPTreeVisitor.Order.PLUS_MINUS_NODE).withTerminationNode(minus);
        tree.accept(plusMinusNode);
        // removed other assertion

        final TestVisitor plusNodeMinus = new TestVisitor(BSPTreeVisitor.Order.PLUS_NODE_MINUS).withTerminationNode(minus);
        tree.accept(plusNodeMinus);
        // removed other assertion

        final TestVisitor minusPlusNode = new TestVisitor(BSPTreeVisitor.Order.MINUS_PLUS_NODE).withTerminationNode(minus);
        tree.accept(minusPlusNode);
        // removed other assertion

        final TestVisitor minusNodePlus = new TestVisitor(BSPTreeVisitor.Order.MINUS_NODE_PLUS).withTerminationNode(minus);
        tree.accept(minusNodePlus);
        Assertions.assertEquals(Arrays.asList(minusMinus,minus),minusNodePlus.getVisited());
    }

    @Test
    void testVisit_earlyTerminationPermutations_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode minus = root.getMinus();
        final TestNode plus = root.getPlus();
        final TestNode minusMinus = minus.getMinus();
        final TestNode minusPlus = minus.getPlus();

        // act/assert
        final TestVisitor plusMinusNode = new TestVisitor(BSPTreeVisitor.Order.PLUS_MINUS_NODE).withTerminationNode(minus);
        tree.accept(plusMinusNode);
        // removed other assertion

        final TestVisitor plusNodeMinus = new TestVisitor(BSPTreeVisitor.Order.PLUS_NODE_MINUS).withTerminationNode(minus);
        tree.accept(plusNodeMinus);
        // removed other assertion

        final TestVisitor minusPlusNode = new TestVisitor(BSPTreeVisitor.Order.MINUS_PLUS_NODE).withTerminationNode(minus);
        tree.accept(minusPlusNode);
        // removed other assertion

        final TestVisitor minusNodePlus = new TestVisitor(BSPTreeVisitor.Order.MINUS_NODE_PLUS).withTerminationNode(minus);
        tree.accept(minusNodePlus);
        // removed other assertion

        final TestVisitor nodeMinusPlus = new TestVisitor(BSPTreeVisitor.Order.NODE_MINUS_PLUS).withTerminationNode(minus);
        tree.accept(nodeMinusPlus);
        Assertions.assertEquals(Arrays.asList(root,minus),nodeMinusPlus.getVisited());
    }

    @Test
    void testVisit_earlyTerminationPermutations_6_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode minus = root.getMinus();
        final TestNode plus = root.getPlus();
        final TestNode minusMinus = minus.getMinus();
        final TestNode minusPlus = minus.getPlus();

        // act/assert
        final TestVisitor plusMinusNode = new TestVisitor(BSPTreeVisitor.Order.PLUS_MINUS_NODE).withTerminationNode(minus);
        tree.accept(plusMinusNode);
        // removed other assertion

        final TestVisitor plusNodeMinus = new TestVisitor(BSPTreeVisitor.Order.PLUS_NODE_MINUS).withTerminationNode(minus);
        tree.accept(plusNodeMinus);
        // removed other assertion

        final TestVisitor minusPlusNode = new TestVisitor(BSPTreeVisitor.Order.MINUS_PLUS_NODE).withTerminationNode(minus);
        tree.accept(minusPlusNode);
        // removed other assertion

        final TestVisitor minusNodePlus = new TestVisitor(BSPTreeVisitor.Order.MINUS_NODE_PLUS).withTerminationNode(minus);
        tree.accept(minusNodePlus);
        // removed other assertion

        final TestVisitor nodeMinusPlus = new TestVisitor(BSPTreeVisitor.Order.NODE_MINUS_PLUS).withTerminationNode(minus);
        tree.accept(nodeMinusPlus);
        // removed other assertion

        final TestVisitor nodePlusMinus = new TestVisitor(BSPTreeVisitor.Order.NODE_PLUS_MINUS).withTerminationNode(minus);
        tree.accept(nodePlusMinus);
        Assertions.assertEquals(Arrays.asList(root,plus,minus),nodePlusMinus.getVisited());
    }

    @Test
    void testVisit_visitNode_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        final TestNode root = tree.getRoot();
        final TestNode minus = root.getMinus();
        final TestNode minusMinus = minus.getMinus();
        final TestNode minusPlus = minus.getPlus();

        final List<TestNode> nodes = new ArrayList<>();

        // act
        minus.accept(node -> {
            nodes.add(node);
            return BSPTreeVisitor.Result.CONTINUE;
        });

        // assert
        Assertions.assertEquals(Arrays.asList(minus,minusMinus,minusPlus),nodes);
    }

    @Test
    void testNodesIterable_emptyTree_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final List<TestNode> nodes = new ArrayList<>();

        // act
        for (final TestNode node : tree.nodes()) {
            nodes.add(node);
        }

        // assert
        Assertions.assertEquals(1, nodes.size());
    }

    @Test
    void testNodesIterable_emptyTree_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final List<TestNode> nodes = new ArrayList<>();

        // act
        for (final TestNode node : tree.nodes()) {
            nodes.add(node);
        }

        // assert
        // removed other assertion
        Assertions.assertSame(tree.getRoot(), nodes.get(0));
    }

    @Test
    void testNodesIterable_multipleNodes_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                 .getParent()
                 .getPlus()
                     .cut(TestLine.Y_AXIS);

        final List<TestNode> nodes = new ArrayList<>();

        // act
        for (final TestNode node : tree.nodes()) {
            nodes.add(node);
        }

        // assert
        Assertions.assertEquals(7, nodes.size());
    }

    @Test
    void testNodesIterable_multipleNodes_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                 .getParent()
                 .getPlus()
                     .cut(TestLine.Y_AXIS);

        final List<TestNode> nodes = new ArrayList<>();

        // act
        for (final TestNode node : tree.nodes()) {
            nodes.add(node);
        }

        // assert
        // removed other assertion
        Assertions.assertSame(root, nodes.get(0));
    }

    @Test
    void testNodesIterable_multipleNodes_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                 .getParent()
                 .getPlus()
                     .cut(TestLine.Y_AXIS);

        final List<TestNode> nodes = new ArrayList<>();

        // act
        for (final TestNode node : tree.nodes()) {
            nodes.add(node);
        }

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(root.getMinus(), nodes.get(1));
    }

    @Test
    void testNodesIterable_multipleNodes_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                 .getParent()
                 .getPlus()
                     .cut(TestLine.Y_AXIS);

        final List<TestNode> nodes = new ArrayList<>();

        // act
        for (final TestNode node : tree.nodes()) {
            nodes.add(node);
        }

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(root.getMinus().getMinus(), nodes.get(2));
    }

    @Test
    void testNodesIterable_multipleNodes_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                 .getParent()
                 .getPlus()
                     .cut(TestLine.Y_AXIS);

        final List<TestNode> nodes = new ArrayList<>();

        // act
        for (final TestNode node : tree.nodes()) {
            nodes.add(node);
        }

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertSame(root.getMinus().getPlus(), nodes.get(3));
    }

    @Test
    void testNodesIterable_multipleNodes_6_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                 .getParent()
                 .getPlus()
                     .cut(TestLine.Y_AXIS);

        final List<TestNode> nodes = new ArrayList<>();

        // act
        for (final TestNode node : tree.nodes()) {
            nodes.add(node);
        }

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(root.getPlus(), nodes.get(4));
    }

    @Test
    void testNodesIterable_multipleNodes_7_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                 .getParent()
                 .getPlus()
                     .cut(TestLine.Y_AXIS);

        final List<TestNode> nodes = new ArrayList<>();

        // act
        for (final TestNode node : tree.nodes()) {
            nodes.add(node);
        }

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(root.getPlus().getMinus(), nodes.get(5));
    }

    @Test
    void testNodesIterable_multipleNodes_8_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestNode root = tree.getRoot();
        root.cut(TestLine.X_AXIS)
                .getMinus()
                    .cut(TestLine.Y_AXIS)
                 .getParent()
                 .getPlus()
                     .cut(TestLine.Y_AXIS);

        final List<TestNode> nodes = new ArrayList<>();

        // act
        for (final TestNode node : tree.nodes()) {
            nodes.add(node);
        }

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertSame(root.getPlus().getPlus(), nodes.get(6));
    }

    @Test
    void testSubtreeNodesIterable_singleNodeSubtree_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final TestNode node = tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS)
                .getMinus();

        final List<TestNode> nodes = new ArrayList<>();
        // act
        for (final TestNode n : node.nodes()) {
            nodes.add(n);
        }

        // assert
        Assertions.assertEquals(1, nodes.size());
    }

    @Test
    void testSubtreeNodesIterable_singleNodeSubtree_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final TestNode node = tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS)
                .getMinus();

        final List<TestNode> nodes = new ArrayList<>();
        // act
        for (final TestNode n : node.nodes()) {
            nodes.add(n);
        }

        // assert
        // removed other assertion
        Assertions.assertSame(node, nodes.get(0));
    }

    @Test
    void testSubtreeNodesIterable_multipleNodeSubtree_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final TestNode node = tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final List<TestNode> nodes = new ArrayList<>();
        // act
        for (final TestNode n : node.nodes()) {
            nodes.add(n);
        }

        // assert
        Assertions.assertEquals(3, nodes.size());
    }

    @Test
    void testSubtreeNodesIterable_multipleNodeSubtree_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final TestNode node = tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final List<TestNode> nodes = new ArrayList<>();
        // act
        for (final TestNode n : node.nodes()) {
            nodes.add(n);
        }

        // assert
        // removed other assertion
        Assertions.assertSame(node, nodes.get(0));
    }

    @Test
    void testSubtreeNodesIterable_multipleNodeSubtree_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final TestNode node = tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final List<TestNode> nodes = new ArrayList<>();
        // act
        for (final TestNode n : node.nodes()) {
            nodes.add(n);
        }

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(node.getMinus(), nodes.get(1));
    }

    @Test
    void testSubtreeNodesIterable_multipleNodeSubtree_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        final TestNode node = tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final List<TestNode> nodes = new ArrayList<>();
        // act
        for (final TestNode n : node.nodes()) {
            nodes.add(n);
        }

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(node.getPlus(), nodes.get(2));
    }

    @Test
    void testNodeTrim_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.Y_AXIS)
            .getPlus()
                .cut(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 1)))
                .getPlus()
                    .cut(new TestLine(new TestPoint2D(1.5, 1.5), new TestPoint2D(2, 1)));

        final TestNode root = tree.getRoot();
        final TestNode plus = root.getPlus();
        final TestNode plusMinus = plus.getMinus();
        final TestNode plusPlusPlus = plus.getPlus().getPlus();

        final TestLineSegment xAxisSeg = TestLine.X_AXIS.span();
        final TestLineSegment shortSeg = new TestLineSegment(new TestPoint2D(2, 0), new TestPoint2D(2, 2));

        // act/assert
        Assertions.assertSame(xAxisSeg, root.trim(xAxisSeg));
    }

    @Test
    void testNodeTrim_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.Y_AXIS)
            .getPlus()
                .cut(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 1)))
                .getPlus()
                    .cut(new TestLine(new TestPoint2D(1.5, 1.5), new TestPoint2D(2, 1)));

        final TestNode root = tree.getRoot();
        final TestNode plus = root.getPlus();
        final TestNode plusMinus = plus.getMinus();
        final TestNode plusPlusPlus = plus.getPlus().getPlus();

        final TestLineSegment xAxisSeg = TestLine.X_AXIS.span();
        final TestLineSegment shortSeg = new TestLineSegment(new TestPoint2D(2, 0), new TestPoint2D(2, 2));

        // act/assert
        // removed other assertion
        Assertions.assertSame(shortSeg, root.trim(shortSeg));
    }

    @Test
    void testNodeTrim_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.Y_AXIS)
            .getPlus()
                .cut(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 1)))
                .getPlus()
                    .cut(new TestLine(new TestPoint2D(1.5, 1.5), new TestPoint2D(2, 1)));

        final TestNode root = tree.getRoot();
        final TestNode plus = root.getPlus();
        final TestNode plusMinus = plus.getMinus();
        final TestNode plusPlusPlus = plus.getPlus().getPlus();

        final TestLineSegment xAxisSeg = TestLine.X_AXIS.span();
        final TestLineSegment shortSeg = new TestLineSegment(new TestPoint2D(2, 0), new TestPoint2D(2, 2));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(shortSeg, plus.trim(shortSeg));
    }

    @Test
    void testNodeTrim_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.Y_AXIS)
            .getPlus()
                .cut(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 1)))
                .getPlus()
                    .cut(new TestLine(new TestPoint2D(1.5, 1.5), new TestPoint2D(2, 1)));

        final TestNode root = tree.getRoot();
        final TestNode plus = root.getPlus();
        final TestNode plusMinus = plus.getMinus();
        final TestNode plusPlusPlus = plus.getPlus().getPlus();

        final TestLineSegment xAxisSeg = TestLine.X_AXIS.span();
        final TestLineSegment shortSeg = new TestLineSegment(new TestPoint2D(2, 0), new TestPoint2D(2, 2));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertNull(plusMinus.trim(xAxisSeg));
    }

    @Test
    void testNodeTrim_6_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.Y_AXIS)
            .getPlus()
                .cut(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 1)))
                .getPlus()
                    .cut(new TestLine(new TestPoint2D(1.5, 1.5), new TestPoint2D(2, 1)));

        final TestNode root = tree.getRoot();
        final TestNode plus = root.getPlus();
        final TestNode plusMinus = plus.getMinus();
        final TestNode plusPlusPlus = plus.getPlus().getPlus();

        final TestLineSegment xAxisSeg = TestLine.X_AXIS.span();
        final TestLineSegment shortSeg = new TestLineSegment(new TestPoint2D(2, 0), new TestPoint2D(2, 2));

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(plusMinus.trim(shortSeg));
    }

    @Test
    void testCopy_rootOnly_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act
        final TestBSPTree copy = new TestBSPTree();
        copy.copy(tree);

        // assert
        Assertions.assertNotSame(tree, copy);
    }

    @Test
    void testCopy_rootOnly_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act
        final TestBSPTree copy = new TestBSPTree();
        copy.copy(tree);

        // assert
        // removed other assertion
        Assertions.assertNotSame(tree.getRoot(), copy.getRoot());
    }

    @Test
    void testCopy_rootOnly_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act
        final TestBSPTree copy = new TestBSPTree();
        copy.copy(tree);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(tree.count(), copy.count());
    }

    @Test
    void testCopy_withCuts_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final TestBSPTree copy = new TestBSPTree();
        copy.copy(tree);

        // assert
        Assertions.assertNotSame(tree, copy);
    }

    @Test
    void testCopy_withCuts_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final TestBSPTree copy = new TestBSPTree();
        copy.copy(tree);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(tree.count(), copy.count());
    }

    @Test
    void testCopy_changesToOneTreeDoNotAffectCopy_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final TestBSPTree copy = new TestBSPTree();
        copy.copy(tree);
        tree.getRoot().clearCut();

        // assert
        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testCopy_changesToOneTreeDoNotAffectCopy_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final TestBSPTree copy = new TestBSPTree();
        copy.copy(tree);
        tree.getRoot().clearCut();

        // assert
        // removed other assertion
        Assertions.assertEquals(5, copy.count());
    }

    @Test
    void testCopy_instancePassedAsArgument_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        tree.copy(tree);

        // assert
        Assertions.assertEquals(5, tree.count());
    }

    @Test
    void testExtract_singleNodeTree_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestBSPTree result = new TestBSPTree();
        result.getRoot().insertCut(TestLine.X_AXIS);

        // act
        result.extract(tree.getRoot());

        // assert
        Assertions.assertNotSame(tree.getRoot(), result.getRoot());
    }

    @Test
    void testExtract_singleNodeTree_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestBSPTree result = new TestBSPTree();
        result.getRoot().insertCut(TestLine.X_AXIS);

        // act
        result.extract(tree.getRoot());

        // assert
        // removed other assertion
        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testExtract_singleNodeTree_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestBSPTree result = new TestBSPTree();
        result.getRoot().insertCut(TestLine.X_AXIS);

        // act
        result.extract(tree.getRoot());

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, result.count());
    }

    @Test
    void testExtract_clearsExistingNodesInCallingTree_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestBSPTree result = new TestBSPTree();
        result.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        // act
        result.extract(tree.getRoot());

        // assert
        Assertions.assertNotSame(tree.getRoot(), result.getRoot());
    }

    @Test
    void testExtract_clearsExistingNodesInCallingTree_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestBSPTree result = new TestBSPTree();
        result.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        // act
        result.extract(tree.getRoot());

        // assert
        // removed other assertion
        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testExtract_clearsExistingNodesInCallingTree_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final TestBSPTree result = new TestBSPTree();
        result.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        // act
        result.extract(tree.getRoot());

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, result.count());
    }

    @Test
    void testExtract_internalNode_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)),
                    new TestLineSegment(new TestPoint2D(1, 2), new TestPoint2D(2, 1)),
                    new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-2, 1)),
                    new TestLineSegment(new TestPoint2D(0, -2), new TestPoint2D(1, -2))
                ));

        final TestBSPTree result = new TestBSPTree();

        // act
        result.extract(tree.getRoot().getPlus());

        // assert
        Assertions.assertEquals(7, result.count());
    }

    @Test
    void testExtract_internalNode_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)),
                    new TestLineSegment(new TestPoint2D(1, 2), new TestPoint2D(2, 1)),
                    new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-2, 1)),
                    new TestLineSegment(new TestPoint2D(0, -2), new TestPoint2D(1, -2))
                ));

        final TestBSPTree result = new TestBSPTree();

        // act
        result.extract(tree.getRoot().getPlus());

        // assert
        // removed other assertion

        final List<TestLineSegment> resultSegments = getLineSegments(result);
        Assertions.assertEquals(3, resultSegments.size());
    }

    @Test
    void testExtract_internalNode_6_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)),
                    new TestLineSegment(new TestPoint2D(1, 2), new TestPoint2D(2, 1)),
                    new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-2, 1)),
                    new TestLineSegment(new TestPoint2D(0, -2), new TestPoint2D(1, -2))
                ));

        final TestBSPTree result = new TestBSPTree();

        // act
        result.extract(tree.getRoot().getPlus());

        // assert
        // removed other assertion

        final List<TestLineSegment> resultSegments = getLineSegments(result);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(13, tree.count());
    }

    @Test
    void testExtract_internalNode_7_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)),
                    new TestLineSegment(new TestPoint2D(1, 2), new TestPoint2D(2, 1)),
                    new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-2, 1)),
                    new TestLineSegment(new TestPoint2D(0, -2), new TestPoint2D(1, -2))
                ));

        final TestBSPTree result = new TestBSPTree();

        // act
        result.extract(tree.getRoot().getPlus());

        // assert
        // removed other assertion

        final List<TestLineSegment> resultSegments = getLineSegments(result);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TestLineSegment> inputSegment = getLineSegments(tree);
        Assertions.assertEquals(6, inputSegment.size());
    }

    @Test
    void testExtract_leafNode_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)),
                    new TestLineSegment(new TestPoint2D(1, 2), new TestPoint2D(2, 1)),
                    new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-2, 1)),
                    new TestLineSegment(new TestPoint2D(0, -2), new TestPoint2D(1, -2))
                ));

        final TestPoint2D pt = new TestPoint2D(1, 1);

        final TestNode node = tree.findNode(pt);
        final TestBSPTree result = new TestBSPTree();

        // act
        result.extract(node);

        // assert
        final TestNode resultNode = result.findNode(pt);
        Assertions.assertNotNull(resultNode);
    }

    @Test
    void testExtract_leafNode_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)),
                    new TestLineSegment(new TestPoint2D(1, 2), new TestPoint2D(2, 1)),
                    new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-2, 1)),
                    new TestLineSegment(new TestPoint2D(0, -2), new TestPoint2D(1, -2))
                ));

        final TestPoint2D pt = new TestPoint2D(1, 1);

        final TestNode node = tree.findNode(pt);
        final TestBSPTree result = new TestBSPTree();

        // act
        result.extract(node);

        // assert
        final TestNode resultNode = result.findNode(pt);
        // removed other assertion
        Assertions.assertNotSame(node, resultNode);
    }

    @Test
    void testExtract_leafNode_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)),
                    new TestLineSegment(new TestPoint2D(1, 2), new TestPoint2D(2, 1)),
                    new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-2, 1)),
                    new TestLineSegment(new TestPoint2D(0, -2), new TestPoint2D(1, -2))
                ));

        final TestPoint2D pt = new TestPoint2D(1, 1);

        final TestNode node = tree.findNode(pt);
        final TestBSPTree result = new TestBSPTree();

        // act
        result.extract(node);

        // assert
        final TestNode resultNode = result.findNode(pt);
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(7, result.count());
    }

    @Test
    void testExtract_leafNode_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)),
                    new TestLineSegment(new TestPoint2D(1, 2), new TestPoint2D(2, 1)),
                    new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-2, 1)),
                    new TestLineSegment(new TestPoint2D(0, -2), new TestPoint2D(1, -2))
                ));

        final TestPoint2D pt = new TestPoint2D(1, 1);

        final TestNode node = tree.findNode(pt);
        final TestBSPTree result = new TestBSPTree();

        // act
        result.extract(node);

        // assert
        final TestNode resultNode = result.findNode(pt);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TestLineSegment> resultSegments = getLineSegments(result);
        Assertions.assertEquals(3, resultSegments.size());
    }

    @Test
    void testExtract_leafNode_8_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)),
                    new TestLineSegment(new TestPoint2D(1, 2), new TestPoint2D(2, 1)),
                    new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-2, 1)),
                    new TestLineSegment(new TestPoint2D(0, -2), new TestPoint2D(1, -2))
                ));

        final TestPoint2D pt = new TestPoint2D(1, 1);

        final TestNode node = tree.findNode(pt);
        final TestBSPTree result = new TestBSPTree();

        // act
        result.extract(node);

        // assert
        final TestNode resultNode = result.findNode(pt);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TestLineSegment> resultSegments = getLineSegments(result);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(13, tree.count());
    }

    @Test
    void testExtract_leafNode_9_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)),
                    new TestLineSegment(new TestPoint2D(1, 2), new TestPoint2D(2, 1)),
                    new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-2, 1)),
                    new TestLineSegment(new TestPoint2D(0, -2), new TestPoint2D(1, -2))
                ));

        final TestPoint2D pt = new TestPoint2D(1, 1);

        final TestNode node = tree.findNode(pt);
        final TestBSPTree result = new TestBSPTree();

        // act
        result.extract(node);

        // assert
        final TestNode resultNode = result.findNode(pt);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TestLineSegment> resultSegments = getLineSegments(result);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TestLineSegment> inputSegment = getLineSegments(tree);
        Assertions.assertEquals(6, inputSegment.size());
    }

    @Test
    void testExtract_extractFromSameTree_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)),
                    new TestLineSegment(new TestPoint2D(1, 2), new TestPoint2D(2, 1)),
                    new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-2, 1)),
                    new TestLineSegment(new TestPoint2D(0, -2), new TestPoint2D(1, -2))
                ));

        final TestPoint2D pt = new TestPoint2D(1, 1);

        final TestNode node = tree.findNode(pt);

        // act
        tree.extract(node);

        // assert
        final TestNode resultNode = tree.findNode(pt);
        Assertions.assertNotNull(resultNode);
    }

    @Test
    void testExtract_extractFromSameTree_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)),
                    new TestLineSegment(new TestPoint2D(1, 2), new TestPoint2D(2, 1)),
                    new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-2, 1)),
                    new TestLineSegment(new TestPoint2D(0, -2), new TestPoint2D(1, -2))
                ));

        final TestPoint2D pt = new TestPoint2D(1, 1);

        final TestNode node = tree.findNode(pt);

        // act
        tree.extract(node);

        // assert
        final TestNode resultNode = tree.findNode(pt);
        // removed other assertion
        Assertions.assertSame(node, resultNode);
    }

    @Test
    void testExtract_extractFromSameTree_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)),
                    new TestLineSegment(new TestPoint2D(1, 2), new TestPoint2D(2, 1)),
                    new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-2, 1)),
                    new TestLineSegment(new TestPoint2D(0, -2), new TestPoint2D(1, -2))
                ));

        final TestPoint2D pt = new TestPoint2D(1, 1);

        final TestNode node = tree.findNode(pt);

        // act
        tree.extract(node);

        // assert
        final TestNode resultNode = tree.findNode(pt);
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(7, tree.count());
    }

    @Test
    void testExtract_extractFromSameTree_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)),
                    new TestLineSegment(new TestPoint2D(1, 2), new TestPoint2D(2, 1)),
                    new TestLineSegment(new TestPoint2D(-1, 2), new TestPoint2D(-2, 1)),
                    new TestLineSegment(new TestPoint2D(0, -2), new TestPoint2D(1, -2))
                ));

        final TestPoint2D pt = new TestPoint2D(1, 1);

        final TestNode node = tree.findNode(pt);

        // act
        tree.extract(node);

        // assert
        final TestNode resultNode = tree.findNode(pt);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<TestLineSegment> resultSegments = getLineSegments(tree);
        Assertions.assertEquals(3, resultSegments.size());
    }

    @Test
    void testTransform_singleNodeTree_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), p.getY() + 2));

        // act
        tree.transform(t);

        // assert
        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testTransform_singleNodeTree_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), p.getY() + 2));

        // act
        tree.transform(t);

        // assert
        // removed other assertion
        Assertions.assertTrue(tree.getRoot().isLeaf());
    }

    @Test
    void testTransform_singleCut_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().insertCut(TestLine.X_AXIS);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), p.getY() + 2));

        // act
        tree.transform(t);

        // assert
        Assertions.assertEquals(3, tree.count());
    }

    @Test
    void testTransform_singleCut_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().insertCut(TestLine.X_AXIS);

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), p.getY() + 2));

        // act
        tree.transform(t);

        // assert
        // removed other assertion

        final List<TestLineSegment> segments = getLineSegments(tree);
        Assertions.assertEquals(1, segments.size());
    }

    @Test
    void testTransform_multipleCuts_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(-1, -1), new TestPoint2D(1, 1)),
                    new TestLineSegment(new TestPoint2D(3, 1), new TestPoint2D(3, 2))
                ));

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 2));

        // act
        tree.transform(t);

        // assert
        Assertions.assertEquals(9, tree.count());
    }

    @Test
    void testTransform_multipleCuts_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(-1, -1), new TestPoint2D(1, 1)),
                    new TestLineSegment(new TestPoint2D(3, 1), new TestPoint2D(3, 2))
                ));

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(0.5 * p.getX(), p.getY() + 2));

        // act
        tree.transform(t);

        // assert
        // removed other assertion

        final List<TestLineSegment> segments = getLineSegments(tree);
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testTransform_xAxisReflection_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)),
                    new TestLineSegment(new TestPoint2D(0, 3), new TestPoint2D(3, 0))
                ));

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), p.getY()));

        final Map<TestPoint2D, TestNode> pointNodeMap = createPointNodeMap(tree, -5, 5);

        // act
        tree.transform(t);

        // assert
        checkTransformedPointNodeMap(tree, t, pointNodeMap);

        final List<TestLineSegment> segments = getLineSegments(tree);
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testTransform_yAxisReflection_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)),
                    new TestLineSegment(new TestPoint2D(0, 3), new TestPoint2D(3, 0))
                ));

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(p.getX(), -p.getY()));

        final Map<TestPoint2D, TestNode> pointNodeMap = createPointNodeMap(tree, -5, 5);

        // act
        tree.transform(t);

        // assert
        checkTransformedPointNodeMap(tree, t, pointNodeMap);

        final List<TestLineSegment> segments = getLineSegments(tree);
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testTransform_xAndYAxisReflection_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.insert(Arrays.asList(
                    new TestLineSegment(new TestPoint2D(-1, 0), new TestPoint2D(1, 0)),
                    new TestLineSegment(new TestPoint2D(0, -1), new TestPoint2D(0, 1)),
                    new TestLineSegment(new TestPoint2D(0, 3), new TestPoint2D(3, 0))
                ));

        final Transform<TestPoint2D> t = new TestTransform2D(p -> new TestPoint2D(-p.getX(), -p.getY()));

        final Map<TestPoint2D, TestNode> pointNodeMap = createPointNodeMap(tree, -5, 5);

        // act
        tree.transform(t);

        // assert
        checkTransformedPointNodeMap(tree, t, pointNodeMap);

        final List<TestLineSegment> segments = getLineSegments(tree);
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testTreeString_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        // act
        final String str = tree.treeString();

        // assert
        final String[] lines = str.split("\n");
        Assertions.assertEquals(5, lines.length);
    }

    @Test
    void testTreeString_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        // act
        final String str = tree.treeString();

        // assert
        final String[] lines = str.split("\n");
        // removed other assertion
        Assertions.assertTrue(lines[0].startsWith("TestNode[cut= TestLineSegment"));
    }

    @Test
    void testTreeString_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        // act
        final String str = tree.treeString();

        // assert
        final String[] lines = str.split("\n");
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(lines[1].startsWith("    [-] TestNode[cut= TestLineSegment"));
    }

    @Test
    void testTreeString_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        // act
        final String str = tree.treeString();

        // assert
        final String[] lines = str.split("\n");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals("        [-] TestNode[cut= null]", lines[2]);
    }

    @Test
    void testTreeString_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        // act
        final String str = tree.treeString();

        // assert
        final String[] lines = str.split("\n");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals("        [+] TestNode[cut= null]", lines[3]);
    }

    @Test
    void testTreeString_6_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS);

        // act
        final String str = tree.treeString();

        // assert
        final String[] lines = str.split("\n");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals("    [+] TestNode[cut= null]", lines[4]);
    }

    @Test
    void testTreeString_emptyTree_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();

        // act
        final String str = tree.treeString();

        // assert
        Assertions.assertEquals("TestNode[cut= null]", str);
    }

    @Test
    void testTreeString_reachesMaxDepth_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS)
            .getMinus().cut(new TestLine(new TestPoint2D(-2, 1), new TestPoint2D(0, 1)));

        // act
        final String str = tree.treeString(1);

        // assert
        final String[] lines = str.split("\n");
        Assertions.assertEquals(4, lines.length);
    }

    @Test
    void testTreeString_reachesMaxDepth_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS)
            .getMinus().cut(new TestLine(new TestPoint2D(-2, 1), new TestPoint2D(0, 1)));

        // act
        final String str = tree.treeString(1);

        // assert
        final String[] lines = str.split("\n");
        // removed other assertion
        Assertions.assertTrue(lines[0].startsWith("TestNode[cut= TestLineSegment"));
    }

    @Test
    void testTreeString_reachesMaxDepth_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS)
            .getMinus().cut(new TestLine(new TestPoint2D(-2, 1), new TestPoint2D(0, 1)));

        // act
        final String str = tree.treeString(1);

        // assert
        final String[] lines = str.split("\n");
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(lines[1].startsWith("    [-] TestNode[cut= TestLineSegment"));
    }

    @Test
    void testTreeString_reachesMaxDepth_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS)
            .getMinus().cut(new TestLine(new TestPoint2D(-2, 1), new TestPoint2D(0, 1)));

        // act
        final String str = tree.treeString(1);

        // assert
        final String[] lines = str.split("\n");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals("        ...", lines[2]);
    }

    @Test
    void testTreeString_reachesMaxDepth_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS)
            .getMinus().cut(new TestLine(new TestPoint2D(-2, 1), new TestPoint2D(0, 1)));

        // act
        final String str = tree.treeString(1);

        // assert
        final String[] lines = str.split("\n");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals("    [+] TestNode[cut= null]", lines[3]);
    }

    @Test
    void testTreeString_zeroMaxDepth_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS)
            .getMinus().cut(new TestLine(new TestPoint2D(-2, 1), new TestPoint2D(0, 1)));

        // act
        final String str = tree.treeString(0);

        // assert
        final String[] lines = str.split("\n");
        Assertions.assertEquals(2, lines.length);
    }

    @Test
    void testTreeString_zeroMaxDepth_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS)
            .getMinus().cut(new TestLine(new TestPoint2D(-2, 1), new TestPoint2D(0, 1)));

        // act
        final String str = tree.treeString(0);

        // assert
        final String[] lines = str.split("\n");
        // removed other assertion
        Assertions.assertTrue(lines[0].startsWith("TestNode[cut= TestLineSegment"));
    }

    @Test
    void testTreeString_zeroMaxDepth_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS)
            .getMinus().cut(new TestLine(new TestPoint2D(-2, 1), new TestPoint2D(0, 1)));

        // act
        final String str = tree.treeString(0);

        // assert
        final String[] lines = str.split("\n");
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(lines[1].startsWith("    ..."));
    }

    @Test
    void testTreeString_negativeMaxDepth_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS)
            .getMinus().cut(TestLine.Y_AXIS)
            .getMinus().cut(new TestLine(new TestPoint2D(-2, 1), new TestPoint2D(0, 1)));

        // act
        final String str = tree.treeString(-1);

        // assert
        Assertions.assertEquals("", str);
    }

    @Test
    void testToString_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().insertCut(TestLine.Y_AXIS);

        // act
        final String str = tree.toString();

        // assert
        final String msg = "Unexpected toString() representation: " + str;

        Assertions.assertTrue(str.contains("TestBSPTree"), msg);
    }

    @Test
    void testToString_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().insertCut(TestLine.Y_AXIS);

        // act
        final String str = tree.toString();

        // assert
        final String msg = "Unexpected toString() representation: " + str;

        // removed other assertion
        Assertions.assertTrue(str.contains("count= 3"), msg);
    }

    @Test
    void testToString_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().insertCut(TestLine.Y_AXIS);

        // act
        final String str = tree.toString();

        // assert
        final String msg = "Unexpected toString() representation: " + str;

        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(str.contains("height= 1"), msg);
    }

    @Test
    void testNodeToString_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS);

        // act
        final String str = tree.getRoot().toString();

        // assert
        Assertions.assertTrue(str.contains("TestNode"));
    }

    @Test
    void testNodeToString_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot().cut(TestLine.X_AXIS);

        // act
        final String str = tree.getRoot().toString();

        // assert
        // removed other assertion
        Assertions.assertTrue(str.contains("cut= TestLineSegment"));
    }

    @Test
    void testSplitIntoTree_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree minus = new TestBSPTree();
        final TestBSPTree plus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, minus, plus);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        Assertions.assertEquals(5, tree.count());
    }

    @Test
    void testSplitIntoTree_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree minus = new TestBSPTree();
        final TestBSPTree plus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, minus, plus);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        // removed other assertion
        Assertions.assertEquals(2, tree.height());
    }

    @Test
    void testSplitIntoTree_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree minus = new TestBSPTree();
        final TestBSPTree plus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, minus, plus);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(5, minus.count());
    }

    @Test
    void testSplitIntoTree_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree minus = new TestBSPTree();
        final TestBSPTree plus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, minus, plus);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(2, minus.height());
    }

    @Test
    void testSplitIntoTree_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree minus = new TestBSPTree();
        final TestBSPTree plus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, minus, plus);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<TestLineSegment> minusSegments = getLineSegments(minus);
        Assertions.assertEquals(2, minusSegments.size());
    }

    @Test
    void testSplitIntoTree_8_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree minus = new TestBSPTree();
        final TestBSPTree plus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, minus, plus);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<TestLineSegment> minusSegments = getLineSegments(minus);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(7, plus.count());
    }

    @Test
    void testSplitIntoTree_9_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree minus = new TestBSPTree();
        final TestBSPTree plus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, minus, plus);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<TestLineSegment> minusSegments = getLineSegments(minus);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(3, plus.height());
    }

    @Test
    void testSplitIntoTree_10_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree minus = new TestBSPTree();
        final TestBSPTree plus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, minus, plus);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<TestLineSegment> minusSegments = getLineSegments(minus);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<TestLineSegment> plusSegments = getLineSegments(plus);
        Assertions.assertEquals(3, plusSegments.size());
    }

    @Test
    void testSplitIntoTree_minusOnly_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree minus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, minus, null);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        Assertions.assertEquals(5, tree.count());
    }

    @Test
    void testSplitIntoTree_minusOnly_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree minus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, minus, null);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        // removed other assertion
        Assertions.assertEquals(2, tree.height());
    }

    @Test
    void testSplitIntoTree_minusOnly_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree minus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, minus, null);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(5, minus.count());
    }

    @Test
    void testSplitIntoTree_minusOnly_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree minus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, minus, null);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(2, minus.height());
    }

    @Test
    void testSplitIntoTree_minusOnly_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree minus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, minus, null);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<TestLineSegment> minusSegments = getLineSegments(minus);
        Assertions.assertEquals(2, minusSegments.size());
    }

    @Test
    void testSplitIntoTree_plusOnly_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree plus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, null, plus);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        Assertions.assertEquals(5, tree.count());
    }

    @Test
    void testSplitIntoTree_plusOnly_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree plus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, null, plus);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        // removed other assertion
        Assertions.assertEquals(2, tree.height());
    }

    @Test
    void testSplitIntoTree_plusOnly_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree plus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, null, plus);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(7, plus.count());
    }

    @Test
    void testSplitIntoTree_plusOnly_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree plus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, null, plus);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(3, plus.height());
    }

    @Test
    void testSplitIntoTree_plusOnly_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        final TestBSPTree plus = new TestBSPTree();

        final TestLine splitter = new TestLine(new TestPoint2D(0,  0), new TestPoint2D(-1, 1));

        // act
        tree.splitIntoTrees(splitter, null, plus);

        // assert
        final TestLineSegment splitSegment = new TestLineSegment(Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY, splitter);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<TestLineSegment> plusSegments = getLineSegments(plus);
        Assertions.assertEquals(3, plusSegments.size());
    }

    @Test
    void testCopy_withCuts_2_oe_1_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final TestBSPTree copy = new TestBSPTree();
        copy.copy(tree);

        // assert
        // removed other assertion
                final TestNode orig0 = tree.getRoot();
        final TestNode copy0 = copy.getRoot();
        Assertions.assertNotSame(orig0, copy0);
    }

    @Test
    void testCopy_withCuts_2_oe_2_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final TestBSPTree copy = new TestBSPTree();
        copy.copy(tree);

        // assert
        // removed other assertion
                final TestNode orig0 = tree.getRoot();
        final TestNode copy0 = copy.getRoot();
        // removed other assertion
        
                Assertions.assertEquals(orig0.getCut(), copy0.getCut());
    }

    @Test
    void testCopy_withCuts_2_oe_3_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final TestBSPTree copy = new TestBSPTree();
        copy.copy(tree);

        // assert
        // removed other assertion
                final TestNode orig0 = tree.getRoot();
        final TestNode copy0 = copy.getRoot();
        // removed other assertion
        
                // removed other assertion
        
                if (orig0.isLeaf()) {
                    Assertions.assertNull(copy0.getMinus());
    }
    }

    @Test
    void testCopy_withCuts_2_oe_4_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final TestBSPTree copy = new TestBSPTree();
        copy.copy(tree);

        // assert
        // removed other assertion
                final TestNode orig0 = tree.getRoot();
        final TestNode copy0 = copy.getRoot();
        // removed other assertion
        
                // removed other assertion
        
                if (orig0.isLeaf()) {
                    // removed other assertion
                    Assertions.assertNull(copy0.getPlus());
    }
    }

    @Test
    void testCopy_withCuts_2_oe_5_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final TestBSPTree copy = new TestBSPTree();
        copy.copy(tree);

        // assert
        // removed other assertion
                final TestNode orig0 = tree.getRoot();
        final TestNode copy0 = copy.getRoot();
        // removed other assertion
        
                // removed other assertion
        
                if (orig0.isLeaf()) {
                    // removed other assertion
                    // removed other assertion
                } else {
                    Assertions.assertNotSame(orig0.getMinus(), copy0.getMinus());
    }
    }

    @Test
    void testCopy_withCuts_2_oe_6_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final TestBSPTree copy = new TestBSPTree();
        copy.copy(tree);

        // assert
        // removed other assertion
                final TestNode orig0 = tree.getRoot();
        final TestNode copy0 = copy.getRoot();
        // removed other assertion
        
                // removed other assertion
        
                if (orig0.isLeaf()) {
                    // removed other assertion
                    // removed other assertion
                } else {
                    // removed other assertion
                    Assertions.assertNotSame(orig0.getPlus(), copy0.getPlus());
    }
    }

    @Test
    void testCopy_withCuts_2_oe_7_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final TestBSPTree copy = new TestBSPTree();
        copy.copy(tree);

        // assert
        // removed other assertion
                final TestNode orig0 = tree.getRoot();
        final TestNode copy0 = copy.getRoot();
        // removed other assertion
        
                // removed other assertion
        
                if (orig0.isLeaf()) {
                    // removed other assertion
                    // removed other assertion
                } else {
                    // removed other assertion
                    // removed other assertion
        
                    assertNodesCopiedRecursive(orig0.getMinus(), copy0.getMinus());
    }
    }

    @Test
    void testCopy_withCuts_2_oe_8_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final TestBSPTree copy = new TestBSPTree();
        copy.copy(tree);

        // assert
        // removed other assertion
                final TestNode orig0 = tree.getRoot();
        final TestNode copy0 = copy.getRoot();
        // removed other assertion
        
                // removed other assertion
        
                if (orig0.isLeaf()) {
                    // removed other assertion
                    // removed other assertion
                } else {
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    assertNodesCopiedRecursive(orig0.getPlus(), copy0.getPlus());
    }
    }

    @Test
    void testCopy_withCuts_2_oe_9_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final TestBSPTree copy = new TestBSPTree();
        copy.copy(tree);

        // assert
        // removed other assertion
                final TestNode orig0 = tree.getRoot();
        final TestNode copy0 = copy.getRoot();
        // removed other assertion
        
                // removed other assertion
        
                if (orig0.isLeaf()) {
                    // removed other assertion
                    // removed other assertion
                } else {
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    // removed other assertion
                }
        
                Assertions.assertEquals(orig0.depth(), copy0.depth());
    }

    @Test
    void testCopy_withCuts_2_oe_10_oe() {
        // arrange
        final TestBSPTree tree = new TestBSPTree();
        tree.getRoot()
            .cut(TestLine.X_AXIS)
            .getMinus()
                .cut(TestLine.Y_AXIS);

        // act
        final TestBSPTree copy = new TestBSPTree();
        copy.copy(tree);

        // assert
        // removed other assertion
                final TestNode orig0 = tree.getRoot();
        final TestNode copy0 = copy.getRoot();
        // removed other assertion
        
                // removed other assertion
        
                if (orig0.isLeaf()) {
                    // removed other assertion
                    // removed other assertion
                } else {
                    // removed other assertion
                    // removed other assertion
        
                    // removed other assertion
                    // removed other assertion
                }
        
                // removed other assertion
                Assertions.assertEquals(orig0.count(), copy0.count());
    }

}
