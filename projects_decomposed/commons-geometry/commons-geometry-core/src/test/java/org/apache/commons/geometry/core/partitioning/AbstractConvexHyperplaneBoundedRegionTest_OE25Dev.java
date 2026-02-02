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
        Assertions.assertThrows(UnsupportedOperationException.class, () ->  boundaries.add(span));
    }

    @Test
    void testFull_1_oe() {
        // act
        final StubRegion region = new StubRegion(Collections.emptyList());

        // assert
        Assertions.assertTrue(region.isFull());
    }

    @Test
    void testFull_2_oe() {
        // act
        final StubRegion region = new StubRegion(Collections.emptyList());

        // assert
        // removed other assertion
        Assertions.assertFalse(region.isEmpty());
    }

    @Test
    void testGetBoundarySize_1_oe() {
        // arrange
        final TestPoint2D p1 = new TestPoint2D(1, 0);
        final TestPoint2D p2 = new TestPoint2D(2, 0);
        final TestPoint2D p3 = new TestPoint2D(1, 1);

        // act/assert
        Assertions.assertEquals(0, new StubRegion(Collections.emptyList()).getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testGetBoundarySize_3_oe() {
        // arrange
        final TestPoint2D p1 = new TestPoint2D(1, 0);
        final TestPoint2D p2 = new TestPoint2D(2, 0);
        final TestPoint2D p3 = new TestPoint2D(1, 1);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(2 + Math.sqrt(2), new StubRegion(Arrays.asList( new TestLineSegment(p1, p2), new TestLineSegment(p2, p3), new TestLineSegment(p3, p1) )).getBoundarySize(), PartitionTestUtils.EPS);
    }

    @Test
    void testProject_1_oe() {
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
        Assertions.assertNull(full.project(TestPoint2D.ZERO));
    }

    @Test
    void testProject_2_oe() {
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
        // removed other assertion
        Assertions.assertNull(full.project(new TestPoint2D(1, 1)));
    }

    @Test
    void testTrim_1_oe() {
        // arrange
        final TestPoint2D p1 = new TestPoint2D(1, 0);
        final TestPoint2D p2 = new TestPoint2D(2, 0);
        final TestPoint2D p3 = new TestPoint2D(2, 1);
        final TestPoint2D p4 = new TestPoint2D(1, 1);

        final StubRegion full = new StubRegion(Collections.emptyList());
        final StubRegion halfSpace = new StubRegion(Collections.singletonList(TestLine.Y_AXIS.span()));
        final StubRegion square = new StubRegion(Arrays.asList(
                new TestLineSegment(p1, p2),
                new TestLineSegment(p2, p3),
                new TestLineSegment(p3, p4),
                new TestLineSegment(p4, p1)
            ));

        final TestLineSegment segment = new TestLineSegment(new TestPoint2D(-1, 0.5), new TestPoint2D(4, 0.5));

        // act/assert
        Assertions.assertSame(segment, full.trim(segment));
    }

    @Test
    void testSplit_full_1_oe() {
        // arrange
        final StubRegion region = new StubRegion(Collections.emptyList());

        final TestLine splitter = TestLine.X_AXIS;

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_full_2_oe() {
        // arrange
        final StubRegion region = new StubRegion(Collections.emptyList());

        final TestLine splitter = TestLine.X_AXIS;

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion

        final StubRegion minus = split.getMinus();
        Assertions.assertEquals(1, minus.getBoundaries().size());
    }

    @Test
    void testSplit_full_3_oe() {
        // arrange
        final StubRegion region = new StubRegion(Collections.emptyList());

        final TestLine splitter = TestLine.X_AXIS;

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion

        final StubRegion minus = split.getMinus();
        // removed other assertion
        checkClassify(minus, RegionLocation.INSIDE, new TestPoint2D(0, 1));
        checkClassify(minus, RegionLocation.BOUNDARY, new TestPoint2D(0, 0));
        checkClassify(minus, RegionLocation.OUTSIDE, new TestPoint2D(0, -1));

        final StubRegion plus = split.getPlus();
        Assertions.assertEquals(1, plus.getBoundaries().size());
    }

    @Test
    void testSplit_parallel_splitterIsOutside_plusOnly_1_oe() {
     // arrange
        final StubRegion region = new StubRegion(
                Collections.singletonList(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(1, 1))));

        final TestLine splitter = TestLine.X_AXIS.reverse();

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_parallel_splitterIsOutside_plusOnly_2_oe() {
     // arrange
        final StubRegion region = new StubRegion(
                Collections.singletonList(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(1, 1))));

        final TestLine splitter = TestLine.X_AXIS.reverse();

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_parallel_splitterIsOutside_plusOnly_3_oe() {
     // arrange
        final StubRegion region = new StubRegion(
                Collections.singletonList(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(1, 1))));

        final TestLine splitter = TestLine.X_AXIS.reverse();

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(region, split.getPlus());
    }

    @Test
    void testSplit_parallel_splitterIsOutside_minusOnly_1_oe() {
     // arrange
        final StubRegion region = new StubRegion(
                Collections.singletonList(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(1, 1))));

        final TestLine splitter = TestLine.X_AXIS;

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_parallel_splitterIsOutside_minusOnly_2_oe() {
     // arrange
        final StubRegion region = new StubRegion(
                Collections.singletonList(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(1, 1))));

        final TestLine splitter = TestLine.X_AXIS;

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertSame(region, split.getMinus());
    }

    @Test
    void testSplit_parallel_splitterIsOutside_minusOnly_3_oe() {
     // arrange
        final StubRegion region = new StubRegion(
                Collections.singletonList(new TestLineSegment(new TestPoint2D(0, 1), new TestPoint2D(1, 1))));

        final TestLine splitter = TestLine.X_AXIS;

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_parallel_splitterIsInside_1_oe() {
     // arrange
        final StubRegion region = new StubRegion(
                Collections.singletonList(new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(0, 1))));

        final TestLine splitter = TestLine.X_AXIS;

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_parallel_splitterIsInside_2_oe() {
     // arrange
        final StubRegion region = new StubRegion(
                Collections.singletonList(new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(0, 1))));

        final TestLine splitter = TestLine.X_AXIS;

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion

        final TestPoint2D p1 = new TestPoint2D(0, 1.5);
        final TestPoint2D p2 = new TestPoint2D(0, 0.5);
        final TestPoint2D p3 = new TestPoint2D(0, -0.5);

        final StubRegion minus = split.getMinus();
        Assertions.assertEquals(2, minus.getBoundaries().size());
    }

    @Test
    void testSplit_parallel_splitterIsInside_3_oe() {
     // arrange
        final StubRegion region = new StubRegion(
                Collections.singletonList(new TestLineSegment(new TestPoint2D(1, 1), new TestPoint2D(0, 1))));

        final TestLine splitter = TestLine.X_AXIS;

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion

        final TestPoint2D p1 = new TestPoint2D(0, 1.5);
        final TestPoint2D p2 = new TestPoint2D(0, 0.5);
        final TestPoint2D p3 = new TestPoint2D(0, -0.5);

        final StubRegion minus = split.getMinus();
        // removed other assertion
        checkClassify(minus, RegionLocation.INSIDE, p2);
        checkClassify(minus, RegionLocation.OUTSIDE, p1, p3);

        final StubRegion plus = split.getPlus();
        Assertions.assertEquals(1, plus.getBoundaries().size());
    }

    @Test
    void testSplit_coincident_sameOrientation_1_oe() {
     // arrange
        final StubRegion region = new StubRegion(Collections.singletonList(TestLine.X_AXIS.span()));

        final TestLine splitter = TestLine.X_AXIS;

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_coincident_sameOrientation_2_oe() {
     // arrange
        final StubRegion region = new StubRegion(Collections.singletonList(TestLine.X_AXIS.span()));

        final TestLine splitter = TestLine.X_AXIS;

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertSame(region, split.getMinus());
    }

    @Test
    void testSplit_coincident_sameOrientation_3_oe() {
     // arrange
        final StubRegion region = new StubRegion(Collections.singletonList(TestLine.X_AXIS.span()));

        final TestLine splitter = TestLine.X_AXIS;

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_coincident_oppositeOrientation_1_oe() {
     // arrange
        final StubRegion region = new StubRegion(Collections.singletonList(TestLine.X_AXIS.span()));

        final TestLine splitter = TestLine.X_AXIS.reverse();

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_coincident_oppositeOrientation_2_oe() {
     // arrange
        final StubRegion region = new StubRegion(Collections.singletonList(TestLine.X_AXIS.span()));

        final TestLine splitter = TestLine.X_AXIS.reverse();

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_coincident_oppositeOrientation_3_oe() {
     // arrange
        final StubRegion region = new StubRegion(Collections.singletonList(TestLine.X_AXIS.span()));

        final TestLine splitter = TestLine.X_AXIS.reverse();

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(region, split.getPlus());
    }

    @Test
    void testSplit_finite_both_1_oe() {
        // arrange
        final TestPoint2D p1 = new TestPoint2D(1, -0.5);
        final TestPoint2D p2 = new TestPoint2D(2, -0.5);
        final TestPoint2D p3 = new TestPoint2D(2, 0.5);
        final TestPoint2D p4 = new TestPoint2D(1, 0.5);

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(p1, p2),
                    new TestLineSegment(p2, p3),
                    new TestLineSegment(p3, p4),
                    new TestLineSegment(p4, p1)
                ));

        final TestLine splitter = TestLine.X_AXIS;

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_finite_both_2_oe() {
        // arrange
        final TestPoint2D p1 = new TestPoint2D(1, -0.5);
        final TestPoint2D p2 = new TestPoint2D(2, -0.5);
        final TestPoint2D p3 = new TestPoint2D(2, 0.5);
        final TestPoint2D p4 = new TestPoint2D(1, 0.5);

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(p1, p2),
                    new TestLineSegment(p2, p3),
                    new TestLineSegment(p3, p4),
                    new TestLineSegment(p4, p1)
                ));

        final TestLine splitter = TestLine.X_AXIS;

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion

        final StubRegion minus = split.getMinus();
        Assertions.assertEquals(4, minus.getBoundaries().size());
    }

    @Test
    void testSplit_finite_both_3_oe() {
        // arrange
        final TestPoint2D p1 = new TestPoint2D(1, -0.5);
        final TestPoint2D p2 = new TestPoint2D(2, -0.5);
        final TestPoint2D p3 = new TestPoint2D(2, 0.5);
        final TestPoint2D p4 = new TestPoint2D(1, 0.5);

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(p1, p2),
                    new TestLineSegment(p2, p3),
                    new TestLineSegment(p3, p4),
                    new TestLineSegment(p4, p1)
                ));

        final TestLine splitter = TestLine.X_AXIS;

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion

        final StubRegion minus = split.getMinus();
        // removed other assertion
        checkClassify(minus, RegionLocation.INSIDE, new TestPoint2D(1.5, 0.25));
        checkClassify(minus, RegionLocation.BOUNDARY, new TestPoint2D(1.5, 0));
        checkClassify(minus, RegionLocation.OUTSIDE, new TestPoint2D(1.5, -0.25));

        final StubRegion plus = split.getPlus();
        Assertions.assertEquals(4, plus.getBoundaries().size());
    }

    @Test
    void testSplit_inconsistentBoundarySplitLocations_minus_2_oe() {
        // arrange
        final TestLine a = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 1));
        final TestLine b = new TestLine(new TestPoint2D(-1, 1), new TestPoint2D(0, 0));

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(-1e-8, Double.POSITIVE_INFINITY, a),
                    new TestLineSegment(Double.NEGATIVE_INFINITY, 1e-8, b)
                ));

        final List<TestLineSegment> segments = region.getBoundaries();
        // removed other assertion

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0));

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_inconsistentBoundarySplitLocations_minus_3_oe() {
        // arrange
        final TestLine a = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 1));
        final TestLine b = new TestLine(new TestPoint2D(-1, 1), new TestPoint2D(0, 0));

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(-1e-8, Double.POSITIVE_INFINITY, a),
                    new TestLineSegment(Double.NEGATIVE_INFINITY, 1e-8, b)
                ));

        final List<TestLineSegment> segments = region.getBoundaries();
        // removed other assertion

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0));

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion
        Assertions.assertSame(region, split.getMinus());
    }

    @Test
    void testSplit_inconsistentBoundarySplitLocations_minus_4_oe() {
        // arrange
        final TestLine a = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 1));
        final TestLine b = new TestLine(new TestPoint2D(-1, 1), new TestPoint2D(0, 0));

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(-1e-8, Double.POSITIVE_INFINITY, a),
                    new TestLineSegment(Double.NEGATIVE_INFINITY, 1e-8, b)
                ));

        final List<TestLineSegment> segments = region.getBoundaries();
        // removed other assertion

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0));

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_inconsistentBoundarySplitLocations_plus_2_oe() {
        // arrange
        final TestLine a = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 1));
        final TestLine b = new TestLine(new TestPoint2D(-1, 1), new TestPoint2D(0, 0));

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(-1e-8, Double.POSITIVE_INFINITY, a),
                    new TestLineSegment(Double.NEGATIVE_INFINITY, 1e-8, b)
                ));

        final List<TestLineSegment> segments = region.getBoundaries();
        // removed other assertion

        final TestLine splitter = new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, 0));

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_inconsistentBoundarySplitLocations_plus_3_oe() {
        // arrange
        final TestLine a = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 1));
        final TestLine b = new TestLine(new TestPoint2D(-1, 1), new TestPoint2D(0, 0));

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(-1e-8, Double.POSITIVE_INFINITY, a),
                    new TestLineSegment(Double.NEGATIVE_INFINITY, 1e-8, b)
                ));

        final List<TestLineSegment> segments = region.getBoundaries();
        // removed other assertion

        final TestLine splitter = new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, 0));

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion
        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_inconsistentBoundarySplitLocations_plus_4_oe() {
        // arrange
        final TestLine a = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 1));
        final TestLine b = new TestLine(new TestPoint2D(-1, 1), new TestPoint2D(0, 0));

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(-1e-8, Double.POSITIVE_INFINITY, a),
                    new TestLineSegment(Double.NEGATIVE_INFINITY, 1e-8, b)
                ));

        final List<TestLineSegment> segments = region.getBoundaries();
        // removed other assertion

        final TestLine splitter = new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, 0));

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(region, split.getPlus());
    }

    @Test
    void testSplit_inconsistentBoundarySplitLocations_trimmedNotNull_minus_2_oe() {
        // arrange
        final TestLine a = new TestLine(new TestPoint2D(1e-8, 0), new TestPoint2D(1, 1));
        final TestLine b = new TestLine(new TestPoint2D(-1, 1), new TestPoint2D(-1e-8, 0));

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(1e-8, Double.POSITIVE_INFINITY, a),
                    new TestLineSegment(Double.NEGATIVE_INFINITY, -1e-8, b)
                ));

        final List<TestLineSegment> segments = region.getBoundaries();
        // removed other assertion

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0));

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_inconsistentBoundarySplitLocations_trimmedNotNull_minus_3_oe() {
        // arrange
        final TestLine a = new TestLine(new TestPoint2D(1e-8, 0), new TestPoint2D(1, 1));
        final TestLine b = new TestLine(new TestPoint2D(-1, 1), new TestPoint2D(-1e-8, 0));

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(1e-8, Double.POSITIVE_INFINITY, a),
                    new TestLineSegment(Double.NEGATIVE_INFINITY, -1e-8, b)
                ));

        final List<TestLineSegment> segments = region.getBoundaries();
        // removed other assertion

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0));

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion
        Assertions.assertSame(region, split.getMinus());
    }

    @Test
    void testSplit_inconsistentBoundarySplitLocations_trimmedNotNull_minus_4_oe() {
        // arrange
        final TestLine a = new TestLine(new TestPoint2D(1e-8, 0), new TestPoint2D(1, 1));
        final TestLine b = new TestLine(new TestPoint2D(-1, 1), new TestPoint2D(-1e-8, 0));

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(1e-8, Double.POSITIVE_INFINITY, a),
                    new TestLineSegment(Double.NEGATIVE_INFINITY, -1e-8, b)
                ));

        final List<TestLineSegment> segments = region.getBoundaries();
        // removed other assertion

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0));

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_inconsistentBoundarySplitLocations_trimmedNotNull_plus_2_oe() {
        // arrange
        final TestLine a = new TestLine(new TestPoint2D(1e-8, 0), new TestPoint2D(1, 1));
        final TestLine b = new TestLine(new TestPoint2D(-1, 1), new TestPoint2D(-1e-8, 0));

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(1e-8, Double.POSITIVE_INFINITY, a),
                    new TestLineSegment(Double.NEGATIVE_INFINITY, -1e-8, b)
                ));

        final List<TestLineSegment> segments = region.getBoundaries();
        // removed other assertion

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(-1, 0));

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_inconsistentBoundarySplitLocations_trimmedNotNull_plus_3_oe() {
        // arrange
        final TestLine a = new TestLine(new TestPoint2D(1e-8, 0), new TestPoint2D(1, 1));
        final TestLine b = new TestLine(new TestPoint2D(-1, 1), new TestPoint2D(-1e-8, 0));

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(1e-8, Double.POSITIVE_INFINITY, a),
                    new TestLineSegment(Double.NEGATIVE_INFINITY, -1e-8, b)
                ));

        final List<TestLineSegment> segments = region.getBoundaries();
        // removed other assertion

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(-1, 0));

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion
        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_inconsistentBoundarySplitLocations_trimmedNotNull_plus_4_oe() {
        // arrange
        final TestLine a = new TestLine(new TestPoint2D(1e-8, 0), new TestPoint2D(1, 1));
        final TestLine b = new TestLine(new TestPoint2D(-1, 1), new TestPoint2D(-1e-8, 0));

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(1e-8, Double.POSITIVE_INFINITY, a),
                    new TestLineSegment(Double.NEGATIVE_INFINITY, -1e-8, b)
                ));

        final List<TestLineSegment> segments = region.getBoundaries();
        // removed other assertion

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(-1, 0));

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(region, split.getPlus());
    }

    @Test
    void testSplit_inconsistentBoundarySplitLocations_trimmedNotNull_neither_2_oe() {
        // arrange
        final TestLine a = new TestLine(new TestPoint2D(1e-8, 0), new TestPoint2D(1, 1));
        final TestLine b = new TestLine(new TestPoint2D(-1, 1), new TestPoint2D(-1e-8, 0));

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(0, 0, a),
                    new TestLineSegment(0, 0, b)
                ));

        final List<TestLineSegment> segments = region.getBoundaries();
        // removed other assertion

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0));

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.NEITHER, split.getLocation());
    }

    @Test
    void testSplit_inconsistentBoundarySplitLocations_trimmedNotNull_neither_3_oe() {
        // arrange
        final TestLine a = new TestLine(new TestPoint2D(1e-8, 0), new TestPoint2D(1, 1));
        final TestLine b = new TestLine(new TestPoint2D(-1, 1), new TestPoint2D(-1e-8, 0));

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(0, 0, a),
                    new TestLineSegment(0, 0, b)
                ));

        final List<TestLineSegment> segments = region.getBoundaries();
        // removed other assertion

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0));

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion
        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_inconsistentBoundarySplitLocations_trimmedNotNull_neither_4_oe() {
        // arrange
        final TestLine a = new TestLine(new TestPoint2D(1e-8, 0), new TestPoint2D(1, 1));
        final TestLine b = new TestLine(new TestPoint2D(-1, 1), new TestPoint2D(-1e-8, 0));

        final StubRegion region = new StubRegion(Arrays.asList(
                    new TestLineSegment(0, 0, a),
                    new TestLineSegment(0, 0, b)
                ));

        final List<TestLineSegment> segments = region.getBoundaries();
        // removed other assertion

        final TestLine splitter = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0));

        // act
        final Split<StubRegion> split = region.split(splitter);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testTransform_full_1_oe() {
        // arrange
        final StubRegion region = new StubRegion(Collections.emptyList());

        final Transform<TestPoint2D> transform = new TestTransform2D(p -> new TestPoint2D(p.getX() + 1, p.getY() + 2));

        // act
        final StubRegion transformed = region.transform(transform);

        // assert
        Assertions.assertTrue(transformed.isFull());
    }

    @Test
    void testTransform_full_2_oe() {
        // arrange
        final StubRegion region = new StubRegion(Collections.emptyList());

        final Transform<TestPoint2D> transform = new TestTransform2D(p -> new TestPoint2D(p.getX() + 1, p.getY() + 2));

        // act
        final StubRegion transformed = region.transform(transform);

        // assert
        // removed other assertion
        Assertions.assertFalse(transformed.isEmpty());
    }

    @Test
    void testTransform_infinite_1_oe() {
        // arrange
        final TestLine line = TestLine.Y_AXIS;

        final StubRegion region = new StubRegion(Collections.singletonList(line.span()));

        final Transform<TestPoint2D> transform = new TestTransform2D(p -> new TestPoint2D(p.getX() + 1, p.getY() + 2));

        // act
        final StubRegion transformed = region.transform(transform);

        // assert
        final List<TestLineSegment> boundaries = transformed.getBoundaries();

        Assertions.assertEquals(1, boundaries.size());
    }

    @Test
    void testTransform_infinite_3_oe() {
        // arrange
        final TestLine line = TestLine.Y_AXIS;

        final StubRegion region = new StubRegion(Collections.singletonList(line.span()));

        final Transform<TestPoint2D> transform = new TestTransform2D(p -> new TestPoint2D(p.getX() + 1, p.getY() + 2));

        // act
        final StubRegion transformed = region.transform(transform);

        // assert
        final List<TestLineSegment> boundaries = transformed.getBoundaries();

        // removed other assertion

        final TestLineSegment a = boundaries.get(0);
        final TestLine aLine = a.getHyperplane();
        // removed other assertion
        Assertions.assertEquals(0.0, aLine.getDirectionX(), PartitionTestUtils.EPS);
    }

    @Test
    void testTransform_infinite_4_oe() {
        // arrange
        final TestLine line = TestLine.Y_AXIS;

        final StubRegion region = new StubRegion(Collections.singletonList(line.span()));

        final Transform<TestPoint2D> transform = new TestTransform2D(p -> new TestPoint2D(p.getX() + 1, p.getY() + 2));

        // act
        final StubRegion transformed = region.transform(transform);

        // assert
        final List<TestLineSegment> boundaries = transformed.getBoundaries();

        // removed other assertion

        final TestLineSegment a = boundaries.get(0);
        final TestLine aLine = a.getHyperplane();
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1.0, aLine.getDirectionY(), PartitionTestUtils.EPS);
    }

    @Test
    void testTransform_finite_1_oe() {
        // arrange
        final TestPoint2D p1 = new TestPoint2D(1, 0);
        final TestPoint2D p2 = new TestPoint2D(2, 0);
        final TestPoint2D p3 = new TestPoint2D(1, 1);

        final StubRegion region = new StubRegion(Arrays.asList(
                new TestLineSegment(p1, p2),
                new TestLineSegment(p2, p3),
                new TestLineSegment(p3, p1)
            ));

        final Transform<TestPoint2D> transform = new TestTransform2D(p -> new TestPoint2D(p.getX() + 1, p.getY() + 2));

        // act
        final StubRegion transformed = region.transform(transform);

        // assert
        final List<TestLineSegment> boundaries = transformed.getBoundaries();

        Assertions.assertEquals(3, boundaries.size());
    }

    @Test
    void testTransform_reflection_1_oe() {
        // arrange
        final TestPoint2D p1 = new TestPoint2D(1, 0);
        final TestPoint2D p2 = new TestPoint2D(2, 0);
        final TestPoint2D p3 = new TestPoint2D(1, 1);

        final StubRegion region = new StubRegion(Arrays.asList(
                new TestLineSegment(p1, p2),
                new TestLineSegment(p2, p3),
                new TestLineSegment(p3, p1)
            ));

        final Transform<TestPoint2D> transform = new TestTransform2D(p -> new TestPoint2D(-p.getX(), p.getY()));

        // act
        final StubRegion transformed = region.transform(transform);

        // assert
        final List<TestLineSegment> boundaries = transformed.getBoundaries();

        Assertions.assertEquals(3, boundaries.size());
    }

    @Test
    void testConvexRegionBoundaryBuilder_full_1_oe() {
        // act
        final StubRegion region = StubRegion.fromBounds(Collections.emptyList());

        // assert
        Assertions.assertSame(StubRegion.FULL, region);
    }

    @Test
    void testConvexRegionBoundaryBuilder_singleLine_1_oe() {
        // act
        final StubRegion region = StubRegion.fromBounds(Collections.singletonList(TestLine.Y_AXIS));

        // assert
        Assertions.assertEquals(1, region.getBoundaries().size());
    }

    @Test
    void testConvexRegionBoundaryBuilder_multipleLines_1_oe() {
        // act
        final StubRegion region = StubRegion.fromBounds(Arrays.asList(
                    TestLine.X_AXIS,
                    new TestLine(new TestPoint2D(1, 0), new TestPoint2D(0, 1)),
                    TestLine.Y_AXIS.reverse()
                ));

        // assert
        Assertions.assertEquals(3, region.getBoundaries().size());
    }

    @Test
    void testConvexRegionBoundaryBuilder_duplicateLines_1_oe() {
        // act
        final StubRegion region = StubRegion.fromBounds(Arrays.asList(
                TestLine.Y_AXIS,
                TestLine.Y_AXIS,
                new TestLine(new TestPoint2D(0, 0), new TestPoint2D(0, 1)),
                TestLine.Y_AXIS));

        // assert
        Assertions.assertEquals(1, region.getBoundaries().size());
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> StubRegion.fromBounds(opposites));
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> StubRegion.fromBounds(nonConvex));
    }

    @Test
    void testToString_1_oe() {
        // arrange
        final StubRegion region = new StubRegion(Collections.emptyList());

        // act
        final String str = region.toString();

        // assert
        Assertions.assertEquals("StubRegion[boundaries= []]", str);
    }

}
