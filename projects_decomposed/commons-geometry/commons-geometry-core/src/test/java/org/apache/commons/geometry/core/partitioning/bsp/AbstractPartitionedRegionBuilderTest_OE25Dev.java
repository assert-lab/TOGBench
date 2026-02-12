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
    void testInsertPartition_cannotInsertAfterBoundary() {
        // arrange
        final TestRegionBuilder builder = new TestRegionBuilder(new TestRegionBSPTree(false));

        builder.insertBoundary(new TestLineSegment(new TestPoint2D(0, 0), new TestPoint2D(1, 0)));

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.insertPartition(new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0)).span());
        }, IllegalStateException.class, "Cannot insert partitions after boundaries have been inserted");
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


}
