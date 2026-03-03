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

import java.util.stream.StreamSupport;

import org.apache.commons.geometry.core.partitioning.test.AttributeBSPTree;
import org.apache.commons.geometry.core.partitioning.test.AttributeBSPTree.AttributeNode;
import org.apache.commons.geometry.core.partitioning.test.PartitionTestUtils;
import org.apache.commons.geometry.core.partitioning.test.TestLine;
import org.apache.commons.geometry.core.partitioning.test.TestPoint2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AbstractBSPTreeMergeOperatorTest_OE25Dev {

    private static class TestMergeOperator extends AbstractBSPTreeMergeOperator<TestPoint2D, AttributeNode<TestPoint2D, String>> {

        /** Perform the test merge operation with the given arguments.
         * @param input1
         * @param input2
         * @param output
         */
        public void apply(final AttributeBSPTree<TestPoint2D, String> input1, final AttributeBSPTree<TestPoint2D, String> input2,
                          final AttributeBSPTree<TestPoint2D, String> output) {
            performMerge(input1, input2, output);
        }

        /** {@inheritDoc} */
        @Override
        protected AttributeNode<TestPoint2D, String> mergeLeaf(final AttributeNode<TestPoint2D, String> node1,
                                                               final AttributeNode<TestPoint2D, String> node2) {

            final AttributeNode<TestPoint2D, String> leaf = node1.isLeaf() ? node1 : node2;
            final AttributeNode<TestPoint2D, String> subtree = node1.isInternal() ? node1 : node2;

            final String attr = leaf.getAttribute();

            final AttributeNode<TestPoint2D, String> output = outputSubtree(subtree);
            StreamSupport.stream(output.nodes().spliterator(), false)
                .filter(BSPTree.Node::isLeaf)
                .forEach(n -> n.setAttribute(attr + n.getAttribute()));

            return output;
        }
    }

    @Test
    void testMerge_singleNodeTreeWithSingleNodeTree_1_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(1, a.count());
    }

    @Test
    void testMerge_singleNodeTreeWithSingleNodeTree_2_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(1, b.count());
    }

    @Test
    void testMerge_singleNodeTreeWithSingleNodeTree_3_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(1, c.count());
    }

    @Test
    void testMerge_singleNodeTreeWithSingleNodeTree_4_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("A", a.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_singleNodeTreeWithSingleNodeTree_5_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("A", a.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_singleNodeTreeWithSingleNodeTree_6_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("B", b.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_singleNodeTreeWithSingleNodeTree_7_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("B", b.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_singleNodeTreeWithSingleNodeTree_8_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("AB", c.findNode(new TestPoint2D(1, 1)).getAttribute());
    }

    @Test
    void testMerge_singleNodeTreeWithSingleNodeTree_9_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("AB", c.findNode(new TestPoint2D(-1, 1)).getAttribute());
    }

    @Test
    void testMerge_singleNodeTreeWithSingleNodeTree_10_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("AB", c.findNode(new TestPoint2D(-1, -1)).getAttribute());
    }

    @Test
    void testMerge_singleNodeTreeWithSingleNodeTree_11_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("AB", c.findNode(new TestPoint2D(1, -1)).getAttribute());
    }

    @Test
    void testMerge_singleNodeTreeWithMultiNodeTree_1_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, a.count());
    }

    @Test
    void testMerge_singleNodeTreeWithMultiNodeTree_2_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(1, b.count());
    }

    @Test
    void testMerge_singleNodeTreeWithMultiNodeTree_3_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, c.count());
    }

    @Test
    void testMerge_singleNodeTreeWithMultiNodeTree_4_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("a", a.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_singleNodeTreeWithMultiNodeTree_5_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("A", a.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_singleNodeTreeWithMultiNodeTree_6_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("B", b.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_singleNodeTreeWithMultiNodeTree_7_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("B", b.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_singleNodeTreeWithMultiNodeTree_8_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("Ba", c.findNode(new TestPoint2D(1, 1)).getAttribute());
    }

    @Test
    void testMerge_singleNodeTreeWithMultiNodeTree_9_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("Ba", c.findNode(new TestPoint2D(-1, 1)).getAttribute());
    }

    @Test
    void testMerge_singleNodeTreeWithMultiNodeTree_10_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("BA", c.findNode(new TestPoint2D(-1, -1)).getAttribute());
    }

    @Test
    void testMerge_singleNodeTreeWithMultiNodeTree_11_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().setAttribute("B");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("BA", c.findNode(new TestPoint2D(1, -1)).getAttribute());
    }

    @Test
    void testMerge_multiNodeTreeWithSingleNodeTree_1_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(1, a.count());
    }

    @Test
    void testMerge_multiNodeTreeWithSingleNodeTree_2_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, b.count());
    }

    @Test
    void testMerge_multiNodeTreeWithSingleNodeTree_3_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, c.count());
    }

    @Test
    void testMerge_multiNodeTreeWithSingleNodeTree_4_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("A", a.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_multiNodeTreeWithSingleNodeTree_5_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("A", a.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_multiNodeTreeWithSingleNodeTree_6_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("b", b.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_multiNodeTreeWithSingleNodeTree_7_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("B", b.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_multiNodeTreeWithSingleNodeTree_8_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("Ab", c.findNode(new TestPoint2D(1, 1)).getAttribute());
    }

    @Test
    void testMerge_multiNodeTreeWithSingleNodeTree_9_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("Ab", c.findNode(new TestPoint2D(-1, 1)).getAttribute());
    }

    @Test
    void testMerge_multiNodeTreeWithSingleNodeTree_10_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("AB", c.findNode(new TestPoint2D(-1, -1)).getAttribute());
    }

    @Test
    void testMerge_multiNodeTreeWithSingleNodeTree_11_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().setAttribute("A");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("AB", c.findNode(new TestPoint2D(1, -1)).getAttribute());
    }

    @Test
    void testMerge_cutsIntersect_1_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, a.count());
    }

    @Test
    void testMerge_cutsIntersect_2_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, b.count());
    }

    @Test
    void testMerge_cutsIntersect_3_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(7, c.count());
    }

    @Test
    void testMerge_cutsIntersect_4_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("a", a.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_cutsIntersect_5_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("A", a.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_cutsIntersect_6_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("B", b.findNode(new TestPoint2D(1, 0)).getAttribute());
    }

    @Test
    void testMerge_cutsIntersect_7_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("b", b.findNode(new TestPoint2D(-1, 0)).getAttribute());
    }

    @Test
    void testMerge_cutsIntersect_8_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("aB", c.findNode(new TestPoint2D(1, 1)).getAttribute());
    }

    @Test
    void testMerge_cutsIntersect_9_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("ab", c.findNode(new TestPoint2D(-1, 1)).getAttribute());
    }

    @Test
    void testMerge_cutsIntersect_10_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("Ab", c.findNode(new TestPoint2D(-1, -1)).getAttribute());
    }

    @Test
    void testMerge_cutsIntersect_11_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("AB", c.findNode(new TestPoint2D(1, -1)).getAttribute());
    }

    @Test
    void testMerge_cutsParallel_1_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, a.count());
    }

    @Test
    void testMerge_cutsParallel_2_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, b.count());
    }

    @Test
    void testMerge_cutsParallel_3_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, c.count());
    }

    @Test
    void testMerge_cutsParallel_4_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("a", a.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_cutsParallel_5_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("A", a.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_cutsParallel_6_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("b", b.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_cutsParallel_7_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("B", b.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_cutsParallel_8_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("ab", c.findNode(new TestPoint2D(1, 1)).getAttribute());
    }

    @Test
    void testMerge_cutsParallel_9_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("ab", c.findNode(new TestPoint2D(-1, 1)).getAttribute());
    }

    @Test
    void testMerge_cutsParallel_10_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("AB", c.findNode(new TestPoint2D(-1, -1)).getAttribute());
    }

    @Test
    void testMerge_cutsParallel_11_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("AB", c.findNode(new TestPoint2D(1, -1)).getAttribute());
    }

    @Test
    void testMerge_cutsAntiParallel_1_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 0), TestPoint2D.ZERO))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, a.count());
    }

    @Test
    void testMerge_cutsAntiParallel_2_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 0), TestPoint2D.ZERO))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, b.count());
    }

    @Test
    void testMerge_cutsAntiParallel_3_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 0), TestPoint2D.ZERO))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, c.count());
    }

    @Test
    void testMerge_cutsAntiParallel_4_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 0), TestPoint2D.ZERO))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("a", a.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_cutsAntiParallel_5_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 0), TestPoint2D.ZERO))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("A", a.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_cutsAntiParallel_6_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 0), TestPoint2D.ZERO))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("B", b.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_cutsAntiParallel_7_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 0), TestPoint2D.ZERO))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("b", b.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_cutsAntiParallel_8_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 0), TestPoint2D.ZERO))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("aB", c.findNode(new TestPoint2D(1, 1)).getAttribute());
    }

    @Test
    void testMerge_cutsAntiParallel_9_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 0), TestPoint2D.ZERO))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("aB", c.findNode(new TestPoint2D(-1, 1)).getAttribute());
    }

    @Test
    void testMerge_cutsAntiParallel_10_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 0), TestPoint2D.ZERO))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("Ab", c.findNode(new TestPoint2D(-1, -1)).getAttribute());
    }

    @Test
    void testMerge_cutsAntiParallel_11_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 0), TestPoint2D.ZERO))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("Ab", c.findNode(new TestPoint2D(1, -1)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_parallel_1_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, -2), new TestPoint2D(1, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, a.count());
    }

    @Test
    void testMerge_cutOnPlusSide_parallel_2_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, -2), new TestPoint2D(1, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, b.count());
    }

    @Test
    void testMerge_cutOnPlusSide_parallel_3_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, -2), new TestPoint2D(1, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(5, c.count());
    }

    @Test
    void testMerge_cutOnPlusSide_parallel_4_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, -2), new TestPoint2D(1, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("a", a.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_parallel_5_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, -2), new TestPoint2D(1, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("A", a.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_parallel_6_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, -2), new TestPoint2D(1, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("b", b.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_parallel_7_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, -2), new TestPoint2D(1, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("B", b.findNode(new TestPoint2D(0, -3)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_parallel_8_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, -2), new TestPoint2D(1, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("ab", c.findNode(new TestPoint2D(1, 1)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_parallel_9_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, -2), new TestPoint2D(1, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("ab", c.findNode(new TestPoint2D(-1, 1)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_parallel_10_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, -2), new TestPoint2D(1, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("Ab", c.findNode(new TestPoint2D(-1, -1)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_parallel_11_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, -2), new TestPoint2D(1, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("Ab", c.findNode(new TestPoint2D(1, -1)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_parallel_12_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, -2), new TestPoint2D(1, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);





        Assertions.assertEquals("AB", c.findNode(new TestPoint2D(-1, -3)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_parallel_13_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, -2), new TestPoint2D(1, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);





        Assertions.assertEquals("AB", c.findNode(new TestPoint2D(1, -3)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_antiParallel_1_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, -2), new TestPoint2D(0, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, a.count());
    }

    @Test
    void testMerge_cutOnPlusSide_antiParallel_2_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, -2), new TestPoint2D(0, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, b.count());
    }

    @Test
    void testMerge_cutOnPlusSide_antiParallel_3_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, -2), new TestPoint2D(0, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(5, c.count());
    }

    @Test
    void testMerge_cutOnPlusSide_antiParallel_4_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, -2), new TestPoint2D(0, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("a", a.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_antiParallel_5_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, -2), new TestPoint2D(0, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("A", a.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_antiParallel_6_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, -2), new TestPoint2D(0, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("B", b.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_antiParallel_7_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, -2), new TestPoint2D(0, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("b", b.findNode(new TestPoint2D(0, -3)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_antiParallel_8_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, -2), new TestPoint2D(0, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("aB", c.findNode(new TestPoint2D(1, 1)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_antiParallel_9_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, -2), new TestPoint2D(0, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("aB", c.findNode(new TestPoint2D(-1, 1)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_antiParallel_10_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, -2), new TestPoint2D(0, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("AB", c.findNode(new TestPoint2D(-1, -1)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_antiParallel_11_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, -2), new TestPoint2D(0, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("AB", c.findNode(new TestPoint2D(1, -1)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_antiParallel_12_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, -2), new TestPoint2D(0, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);





        Assertions.assertEquals("Ab", c.findNode(new TestPoint2D(-1, -3)).getAttribute());
    }

    @Test
    void testMerge_cutOnPlusSide_antiParallel_13_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, -2), new TestPoint2D(0, -2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);





        Assertions.assertEquals("Ab", c.findNode(new TestPoint2D(1, -3)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_parallel_1_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, 2), new TestPoint2D(1, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, a.count());
    }

    @Test
    void testMerge_cutOnMinusSide_parallel_2_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, 2), new TestPoint2D(1, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, b.count());
    }

    @Test
    void testMerge_cutOnMinusSide_parallel_3_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, 2), new TestPoint2D(1, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(5, c.count());
    }

    @Test
    void testMerge_cutOnMinusSide_parallel_4_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, 2), new TestPoint2D(1, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("a", a.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_parallel_5_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, 2), new TestPoint2D(1, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("A", a.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_parallel_6_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, 2), new TestPoint2D(1, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("B", b.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_parallel_7_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, 2), new TestPoint2D(1, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("b", b.findNode(new TestPoint2D(0, 3)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_parallel_8_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, 2), new TestPoint2D(1, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("aB", c.findNode(new TestPoint2D(1, 1)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_parallel_9_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, 2), new TestPoint2D(1, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("aB", c.findNode(new TestPoint2D(-1, 1)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_parallel_10_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, 2), new TestPoint2D(1, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("AB", c.findNode(new TestPoint2D(-1, -1)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_parallel_11_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, 2), new TestPoint2D(1, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("AB", c.findNode(new TestPoint2D(1, -1)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_parallel_12_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, 2), new TestPoint2D(1, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);





        Assertions.assertEquals("ab", c.findNode(new TestPoint2D(-1, 3)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_parallel_13_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(0, 2), new TestPoint2D(1, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);





        Assertions.assertEquals("ab", c.findNode(new TestPoint2D(1, 3)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_antiParallel_1_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 2), new TestPoint2D(0, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, a.count());
    }

    @Test
    void testMerge_cutOnMinusSide_antiParallel_2_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 2), new TestPoint2D(0, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(3, b.count());
    }

    @Test
    void testMerge_cutOnMinusSide_antiParallel_3_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 2), new TestPoint2D(0, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);

        Assertions.assertEquals(5, c.count());
    }

    @Test
    void testMerge_cutOnMinusSide_antiParallel_4_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 2), new TestPoint2D(0, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("a", a.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_antiParallel_5_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 2), new TestPoint2D(0, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);


        Assertions.assertEquals("A", a.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_antiParallel_6_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 2), new TestPoint2D(0, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("b", b.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_antiParallel_7_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 2), new TestPoint2D(0, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);



        Assertions.assertEquals("B", b.findNode(new TestPoint2D(0, 3)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_antiParallel_8_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 2), new TestPoint2D(0, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("ab", c.findNode(new TestPoint2D(1, 1)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_antiParallel_9_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 2), new TestPoint2D(0, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("ab", c.findNode(new TestPoint2D(-1, 1)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_antiParallel_10_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 2), new TestPoint2D(0, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("Ab", c.findNode(new TestPoint2D(-1, -1)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_antiParallel_11_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 2), new TestPoint2D(0, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);




        Assertions.assertEquals("Ab", c.findNode(new TestPoint2D(1, -1)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_antiParallel_12_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 2), new TestPoint2D(0, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);





        Assertions.assertEquals("aB", c.findNode(new TestPoint2D(-1, 3)).getAttribute());
    }

    @Test
    void testMerge_cutOnMinusSide_antiParallel_13_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(new TestLine(new TestPoint2D(1, 2), new TestPoint2D(0, 2)))
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final AttributeBSPTree<TestPoint2D, String> c = new AttributeBSPTree<>();

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, c);





        Assertions.assertEquals("aB", c.findNode(new TestPoint2D(1, 3)).getAttribute());
    }

    @Test
    void testMerge_outputIsFirstInput_1_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, a);

        Assertions.assertEquals(7, a.count());
    }

    @Test
    void testMerge_outputIsFirstInput_2_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, a);

        Assertions.assertEquals(3, b.count());
    }

    @Test
    void testMerge_outputIsFirstInput_3_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, a);


        Assertions.assertEquals("B", b.findNode(new TestPoint2D(1, 0)).getAttribute());
    }

    @Test
    void testMerge_outputIsFirstInput_4_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, a);


        Assertions.assertEquals("b", b.findNode(new TestPoint2D(-1, 0)).getAttribute());
    }

    @Test
    void testMerge_outputIsFirstInput_5_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, a);



        Assertions.assertEquals("aB", a.findNode(new TestPoint2D(1, 1)).getAttribute());
    }

    @Test
    void testMerge_outputIsFirstInput_6_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, a);



        Assertions.assertEquals("ab", a.findNode(new TestPoint2D(-1, 1)).getAttribute());
    }

    @Test
    void testMerge_outputIsFirstInput_7_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, a);



        Assertions.assertEquals("Ab", a.findNode(new TestPoint2D(-1, -1)).getAttribute());
    }

    @Test
    void testMerge_outputIsFirstInput_8_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, a);



        Assertions.assertEquals("AB", a.findNode(new TestPoint2D(1, -1)).getAttribute());
    }

    @Test
    void testMerge_outputIsSecondInput_1_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, b);

        Assertions.assertEquals(3, a.count());
    }

    @Test
    void testMerge_outputIsSecondInput_2_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, b);

        Assertions.assertEquals(7, b.count());
    }

    @Test
    void testMerge_outputIsSecondInput_3_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, b);


        Assertions.assertEquals("a", a.findNode(new TestPoint2D(0, 1)).getAttribute());
    }

    @Test
    void testMerge_outputIsSecondInput_4_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, b);


        Assertions.assertEquals("A", a.findNode(new TestPoint2D(0, -1)).getAttribute());
    }

    @Test
    void testMerge_outputIsSecondInput_5_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, b);



        Assertions.assertEquals("aB", b.findNode(new TestPoint2D(1, 1)).getAttribute());
    }

    @Test
    void testMerge_outputIsSecondInput_6_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, b);



        Assertions.assertEquals("ab", b.findNode(new TestPoint2D(-1, 1)).getAttribute());
    }

    @Test
    void testMerge_outputIsSecondInput_7_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, b);



        Assertions.assertEquals("Ab", b.findNode(new TestPoint2D(-1, -1)).getAttribute());
    }

    @Test
    void testMerge_outputIsSecondInput_8_oe() {
        final AttributeBSPTree<TestPoint2D, String> a = new AttributeBSPTree<>();
        a.getRoot().cut(TestLine.X_AXIS)
            .getPlus().attr("A")
            .getParent()
            .getMinus().attr("a");

        final AttributeBSPTree<TestPoint2D, String> b = new AttributeBSPTree<>();
        b.getRoot().cut(TestLine.Y_AXIS)
            .getPlus().attr("B")
            .getParent()
            .getMinus().attr("b");

        final TestMergeOperator mergeOp = new TestMergeOperator();

        mergeOp.apply(a, b, b);



        Assertions.assertEquals("AB", b.findNode(new TestPoint2D(1, -1)).getAttribute());
    }

}
