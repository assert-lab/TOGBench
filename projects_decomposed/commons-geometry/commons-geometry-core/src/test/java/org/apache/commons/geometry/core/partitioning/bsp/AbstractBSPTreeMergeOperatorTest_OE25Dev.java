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


}
