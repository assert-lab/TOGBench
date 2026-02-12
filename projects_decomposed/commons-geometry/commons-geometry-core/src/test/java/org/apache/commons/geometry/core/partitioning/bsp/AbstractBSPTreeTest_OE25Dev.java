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


}
