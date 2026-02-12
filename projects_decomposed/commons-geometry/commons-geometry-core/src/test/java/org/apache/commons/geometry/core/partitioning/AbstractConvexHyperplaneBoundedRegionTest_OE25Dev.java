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
package org.apache.commons.geometry.core.partitioning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.Region;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.Transform;
import org.apache.commons.geometry.core.partitioning.test.PartitionTestUtils;
import org.apache.commons.geometry.core.partitioning.test.TestLine;
import org.apache.commons.geometry.core.partitioning.test.TestLineSegment;
import org.apache.commons.geometry.core.partitioning.test.TestPoint2D;
import org.apache.commons.geometry.core.partitioning.test.TestTransform2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AbstractConvexHyperplaneBoundedRegionTest_OE25Dev {

    @Test
    void testClassify() {
        // arrange
        final TestPoint2D p1 = new TestPoint2D(1, 0);
        final TestPoint2D p2 = new TestPoint2D(2, 0);
        final TestPoint2D p3 = new TestPoint2D(1, 1);

        final StubRegion full = new StubRegion(Collections.emptyList());
        final StubRegion halfSpace = new StubRegion(Collections.singletonList(TestLine.X_AXIS.span()));
        final StubRegion triangle = new StubRegion(Arrays.asList(
                new TestLineSegment(p1, p2),
                new TestLineSegment(p2, p3),
                new TestLineSegment(p3, p1)
            ));

        // act/assert
        checkClassify(full, RegionLocation.INSIDE, TestPoint2D.ZERO, p1, p2, p3);

        checkClassify(halfSpace, RegionLocation.INSIDE, new TestPoint2D(0, 1));
        checkClassify(halfSpace, RegionLocation.OUTSIDE, new TestPoint2D(0, -1));
        checkClassify(halfSpace, RegionLocation.BOUNDARY,
                new TestPoint2D(-1, 0), new TestPoint2D(0, 0), new TestPoint2D(1, 0));

        checkClassify(triangle, RegionLocation.INSIDE, new TestPoint2D(1.25, 0.25));
        checkClassify(triangle, RegionLocation.OUTSIDE, new TestPoint2D(-1, 0), new TestPoint2D(0, 0), new TestPoint2D(3, 0));
        checkClassify(triangle, RegionLocation.BOUNDARY, p1, p2, p3);
    }

    // The following tests are designed to check the situation where there are
    // inconsistencies between how a splitter splits a set of boundaries and how
    // the boundaries split the splitter. For example, no portion of the splitter
    // may lie inside the region (on the minus sides of all boundaries), but some
    // of the boundaries may be determined to lie on both sides of the splitter.
    // One potential cause of this situation is accumulated floating point errors.

    private static void checkClassify(final Region<TestPoint2D> region, final RegionLocation loc, final TestPoint2D... pts) {
        for (final TestPoint2D pt : pts) {
            Assertions.assertEquals(loc, region.classify(pt), "Unexpected location for point " + pt);
        }
    }

    private static final class StubRegion extends AbstractConvexHyperplaneBoundedRegion<TestPoint2D, TestLineSegment> {

        private static final StubRegion FULL = new StubRegion(Collections.emptyList());

        StubRegion(final List<TestLineSegment> boundaries) {
            super(boundaries);
        }

        public StubRegion transform(final Transform<TestPoint2D> transform) {
            return transformInternal(transform, this, TestLineSegment.class, StubRegion::new);
        }

        @Override
        public Split<StubRegion> split(final Hyperplane<TestPoint2D> splitter) {
            return splitInternal(splitter, this, TestLineSegment.class, StubRegion::new);
        }

        @Override
        public TestLineSegment trim(final HyperplaneConvexSubset<TestPoint2D> subset) {
            return (TestLineSegment) super.trim(subset);
        }

        @Override
        public double getSize() {
            throw new UnsupportedOperationException();
        }

        @Override
        public TestPoint2D getCentroid() {
            throw new UnsupportedOperationException();
        }

        public static StubRegion fromBounds(final Iterable<TestLine> boundingLines) {
            final List<TestLineSegment> segments = new ConvexRegionBoundaryBuilder<>(TestLineSegment.class)
                    .build(boundingLines);
            return segments.isEmpty() ? FULL : new StubRegion(segments);
        }
    }

    @Test
    void testBoundaries_areUnmodifiable_1_oe() {
        // arrange
        final StubRegion region = new StubRegion(new ArrayList<>());
        final List<TestLineSegment> boundaries = region.getBoundaries();
        final TestLineSegment span = TestLine.X_AXIS.span();


        // act/assert
        try {
    boundaries.add(span);
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    void testConvexRegionBoundaryBuilder_1_oe() {
        // arrange
        final List<TestLine> opposites = Arrays.asList(TestLine.X_AXIS, TestLine.X_AXIS.reverse());
        final List<TestLine> nonConvex = Arrays.asList(
                TestLine.X_AXIS,
                TestLine.Y_AXIS,
                new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, -1)),
                new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, -2)));

        // act/assert
        try {
    StubRegion.fromBounds(opposites);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvexRegionBoundaryBuilder_2_oe() {
        // arrange
        final List<TestLine> opposites = Arrays.asList(TestLine.X_AXIS, TestLine.X_AXIS.reverse());
        final List<TestLine> nonConvex = Arrays.asList(
                TestLine.X_AXIS,
                TestLine.Y_AXIS,
                new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, -1)),
                new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, -2)));

        // act/assert
        // removed other assertion
        try {
    StubRegion.fromBounds(nonConvex);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
