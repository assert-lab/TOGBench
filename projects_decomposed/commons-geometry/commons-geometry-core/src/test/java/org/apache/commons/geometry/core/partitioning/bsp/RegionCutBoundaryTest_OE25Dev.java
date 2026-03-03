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
import java.util.Collections;
import java.util.List;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.partitioning.HyperplaneConvexSubset;
import org.apache.commons.geometry.core.partitioning.test.PartitionTestUtils;
import org.apache.commons.geometry.core.partitioning.test.TestLine;
import org.apache.commons.geometry.core.partitioning.test.TestLineSegment;
import org.apache.commons.geometry.core.partitioning.test.TestPoint2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegionCutBoundaryTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    @Test
    void testGetSize_infinite() {
        // act
        final TestLine line = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0));
        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(
                Collections.singletonList(new TestLineSegment(1, Double.POSITIVE_INFINITY, line)),
                Collections.singletonList(new TestLineSegment(Double.NEGATIVE_INFINITY, -1, line)));

        // assert
        GeometryTestUtils.assertPositiveInfinity(boundary.getSize());
    }

    @Test
    void testProperties_1_oe() {
        final List<HyperplaneConvexSubset<TestPoint2D>> insideFacing =
                Collections.singletonList(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        final List<HyperplaneConvexSubset<TestPoint2D>> outsideFacing =
                Collections.singletonList(new TestLineSegment(new TestPoint2D(-1, 0), TestPoint2D.ZERO));

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(insideFacing, outsideFacing);

        Assertions.assertNotSame(insideFacing, boundary.getInsideFacing());
    }

    @Test
    void testProperties_2_oe() {
        final List<HyperplaneConvexSubset<TestPoint2D>> insideFacing =
                Collections.singletonList(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        final List<HyperplaneConvexSubset<TestPoint2D>> outsideFacing =
                Collections.singletonList(new TestLineSegment(new TestPoint2D(-1, 0), TestPoint2D.ZERO));

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(insideFacing, outsideFacing);

        Assertions.assertEquals(insideFacing, boundary.getInsideFacing());
    }

    @Test
    void testProperties_3_oe() {
        final List<HyperplaneConvexSubset<TestPoint2D>> insideFacing =
                Collections.singletonList(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        final List<HyperplaneConvexSubset<TestPoint2D>> outsideFacing =
                Collections.singletonList(new TestLineSegment(new TestPoint2D(-1, 0), TestPoint2D.ZERO));

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(insideFacing, outsideFacing);


        Assertions.assertNotSame(outsideFacing, boundary.getOutsideFacing());
    }

    @Test
    void testProperties_4_oe() {
        final List<HyperplaneConvexSubset<TestPoint2D>> insideFacing =
                Collections.singletonList(new TestLineSegment(TestPoint2D.ZERO, new TestPoint2D(1, 0)));
        final List<HyperplaneConvexSubset<TestPoint2D>> outsideFacing =
                Collections.singletonList(new TestLineSegment(new TestPoint2D(-1, 0), TestPoint2D.ZERO));

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(insideFacing, outsideFacing);


        Assertions.assertEquals(outsideFacing, boundary.getOutsideFacing());
    }

    @Test
    void testProperties_nullLists_1_oe() {
        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(null, null);

        Assertions.assertEquals(0, boundary.getInsideFacing().size());
    }

    @Test
    void testProperties_nullLists_2_oe() {
        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(null, null);

        Assertions.assertEquals(0, boundary.getOutsideFacing().size());
    }

    @Test
    void testGetSize_noSize_1_oe() {
        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(null, null);

        Assertions.assertEquals(0, boundary.getSize(), TEST_EPS);
    }

    @Test
    void testGetSize_finite_1_oe() {
        final TestLine line = new TestLine(new TestPoint2D(0, 0), new TestPoint2D(1, 0));
        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(
                Arrays.asList(new TestLineSegment(1, 2, line), new TestLineSegment(3, 4, line)),
                Collections.singletonList(new TestLineSegment(-3, -1, line)));

        Assertions.assertEquals(4, boundary.getSize(), TEST_EPS);
    }

    @Test
    void testClosest_nullInsideAndOutsideFacing_1_oe() {
        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(null, null);

        Assertions.assertNull(boundary.closest(TestPoint2D.ZERO));
    }

    @Test
    void testClosest_nullInsideAndOutsideFacing_2_oe() {
        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(null, null);

        Assertions.assertNull(boundary.closest(new TestPoint2D(1, 1)));
    }

    @Test
    void testContains_1_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;
        final TestPoint2D c = new TestPoint2D(1, 0);

        final TestLineSegment insideFacing = new TestLineSegment(a, b);
        final TestLineSegment outsideFacing = new TestLineSegment(b, c);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing),
                Collections.singletonList(outsideFacing));

        Assertions.assertFalse(boundary.contains(new TestPoint2D(-2, 0)));
    }

    @Test
    void testContains_2_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;
        final TestPoint2D c = new TestPoint2D(1, 0);

        final TestLineSegment insideFacing = new TestLineSegment(a, b);
        final TestLineSegment outsideFacing = new TestLineSegment(b, c);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing),
                Collections.singletonList(outsideFacing));


        Assertions.assertTrue(boundary.contains(new TestPoint2D(-1, 0)));
    }

    @Test
    void testContains_3_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;
        final TestPoint2D c = new TestPoint2D(1, 0);

        final TestLineSegment insideFacing = new TestLineSegment(a, b);
        final TestLineSegment outsideFacing = new TestLineSegment(b, c);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing),
                Collections.singletonList(outsideFacing));


        Assertions.assertTrue(boundary.contains(new TestPoint2D(-0.5, 0)));
    }

    @Test
    void testContains_4_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;
        final TestPoint2D c = new TestPoint2D(1, 0);

        final TestLineSegment insideFacing = new TestLineSegment(a, b);
        final TestLineSegment outsideFacing = new TestLineSegment(b, c);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing),
                Collections.singletonList(outsideFacing));


        Assertions.assertTrue(boundary.contains(new TestPoint2D(0, 0)));
    }

    @Test
    void testContains_5_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;
        final TestPoint2D c = new TestPoint2D(1, 0);

        final TestLineSegment insideFacing = new TestLineSegment(a, b);
        final TestLineSegment outsideFacing = new TestLineSegment(b, c);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing),
                Collections.singletonList(outsideFacing));


        Assertions.assertTrue(boundary.contains(new TestPoint2D(0.5, 0)));
    }

    @Test
    void testContains_6_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;
        final TestPoint2D c = new TestPoint2D(1, 0);

        final TestLineSegment insideFacing = new TestLineSegment(a, b);
        final TestLineSegment outsideFacing = new TestLineSegment(b, c);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing),
                Collections.singletonList(outsideFacing));


        Assertions.assertTrue(boundary.contains(new TestPoint2D(1, 0)));
    }

    @Test
    void testContains_7_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;
        final TestPoint2D c = new TestPoint2D(1, 0);

        final TestLineSegment insideFacing = new TestLineSegment(a, b);
        final TestLineSegment outsideFacing = new TestLineSegment(b, c);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing),
                Collections.singletonList(outsideFacing));



        Assertions.assertFalse(boundary.contains(new TestPoint2D(2, 0)));
    }

    @Test
    void testContains_8_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;
        final TestPoint2D c = new TestPoint2D(1, 0);

        final TestLineSegment insideFacing = new TestLineSegment(a, b);
        final TestLineSegment outsideFacing = new TestLineSegment(b, c);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing),
                Collections.singletonList(outsideFacing));




        Assertions.assertFalse(boundary.contains(new TestPoint2D(-1, 1)));
    }

    @Test
    void testContains_9_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;
        final TestPoint2D c = new TestPoint2D(1, 0);

        final TestLineSegment insideFacing = new TestLineSegment(a, b);
        final TestLineSegment outsideFacing = new TestLineSegment(b, c);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing),
                Collections.singletonList(outsideFacing));




        Assertions.assertFalse(boundary.contains(new TestPoint2D(0, -1)));
    }

    @Test
    void testContains_10_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;
        final TestPoint2D c = new TestPoint2D(1, 0);

        final TestLineSegment insideFacing = new TestLineSegment(a, b);
        final TestLineSegment outsideFacing = new TestLineSegment(b, c);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing),
                Collections.singletonList(outsideFacing));




        Assertions.assertFalse(boundary.contains(new TestPoint2D(1, 1)));
    }

    @Test
    void testContains_nullHyperplaneSubsets_1_oe() {
        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(null, null);

        Assertions.assertFalse(boundary.contains(new TestPoint2D(-1, 0)));
    }

    @Test
    void testContains_nullHyperplaneSubsets_2_oe() {
        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(null, null);

        Assertions.assertFalse(boundary.contains(new TestPoint2D(0, 0)));
    }

    @Test
    void testContains_nullHyperplaneSubsets_3_oe() {
        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(null, null);

        Assertions.assertFalse(boundary.contains(new TestPoint2D(1, 0)));
    }

@Test
    void testClosest_1_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;
        final TestPoint2D c = new TestPoint2D(1, 0);

        final TestLineSegment insideFacing = new TestLineSegment(a, b);
        final TestLineSegment outsideFacing = new TestLineSegment(b, c);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing),
                Collections.singletonList(outsideFacing));

        PartitionTestUtils.assertPointsEqual(a, boundary.closest(new TestPoint2D(-2, 1)));
    }

@Test
    void testClosest_2_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;
        final TestPoint2D c = new TestPoint2D(1, 0);

        final TestLineSegment insideFacing = new TestLineSegment(a, b);
        final TestLineSegment outsideFacing = new TestLineSegment(b, c);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing),
                Collections.singletonList(outsideFacing));

        PartitionTestUtils.assertPointsEqual(new TestPoint2D(-0.5, 0), boundary.closest(new TestPoint2D(-0.5, -1)));
    }

@Test
    void testClosest_3_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;
        final TestPoint2D c = new TestPoint2D(1, 0);

        final TestLineSegment insideFacing = new TestLineSegment(a, b);
        final TestLineSegment outsideFacing = new TestLineSegment(b, c);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing),
                Collections.singletonList(outsideFacing));

        PartitionTestUtils.assertPointsEqual(b, boundary.closest(TestPoint2D.ZERO));
    }

@Test
    void testClosest_4_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;
        final TestPoint2D c = new TestPoint2D(1, 0);

        final TestLineSegment insideFacing = new TestLineSegment(a, b);
        final TestLineSegment outsideFacing = new TestLineSegment(b, c);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing),
                Collections.singletonList(outsideFacing));

        PartitionTestUtils.assertPointsEqual(b, boundary.closest(new TestPoint2D(0, 2)));
    }

@Test
    void testClosest_5_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;
        final TestPoint2D c = new TestPoint2D(1, 0);

        final TestLineSegment insideFacing = new TestLineSegment(a, b);
        final TestLineSegment outsideFacing = new TestLineSegment(b, c);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing),
                Collections.singletonList(outsideFacing));

        PartitionTestUtils.assertPointsEqual(new TestPoint2D(0.5, 0), boundary.closest(new TestPoint2D(0.5, 3)));
    }

@Test
    void testClosest_6_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;
        final TestPoint2D c = new TestPoint2D(1, 0);

        final TestLineSegment insideFacing = new TestLineSegment(a, b);
        final TestLineSegment outsideFacing = new TestLineSegment(b, c);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing),
                Collections.singletonList(outsideFacing));

        PartitionTestUtils.assertPointsEqual(c, boundary.closest(new TestPoint2D(1, -4)));
    }

@Test
    void testClosest_7_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;
        final TestPoint2D c = new TestPoint2D(1, 0);

        final TestLineSegment insideFacing = new TestLineSegment(a, b);
        final TestLineSegment outsideFacing = new TestLineSegment(b, c);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing),
                Collections.singletonList(outsideFacing));

        PartitionTestUtils.assertPointsEqual(c, boundary.closest(new TestPoint2D(3, -5)));
    }

@Test
    void testClosest_nullInsideFacing_1_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;

        final TestLineSegment outsideFacing = new TestLineSegment(a, b);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(null, Collections.singletonList(outsideFacing));

        PartitionTestUtils.assertPointsEqual(a, boundary.closest(new TestPoint2D(-2, 1)));
    }

@Test
    void testClosest_nullInsideFacing_2_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;

        final TestLineSegment outsideFacing = new TestLineSegment(a, b);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(null, Collections.singletonList(outsideFacing));

        PartitionTestUtils.assertPointsEqual(new TestPoint2D(-0.5, 0), boundary.closest(new TestPoint2D(-0.5, -1)));
    }

@Test
    void testClosest_nullInsideFacing_3_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;

        final TestLineSegment outsideFacing = new TestLineSegment(a, b);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(null, Collections.singletonList(outsideFacing));

        PartitionTestUtils.assertPointsEqual(b, boundary.closest(TestPoint2D.ZERO));
    }

@Test
    void testClosest_nullInsideFacing_4_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;

        final TestLineSegment outsideFacing = new TestLineSegment(a, b);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(null, Collections.singletonList(outsideFacing));

        PartitionTestUtils.assertPointsEqual(b, boundary.closest(new TestPoint2D(1, 2)));
    }

@Test
    void testClosest_nullOutsideFacing_1_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;

        final TestLineSegment insideFacing = new TestLineSegment(a, b);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing), null);

        PartitionTestUtils.assertPointsEqual(a, boundary.closest(new TestPoint2D(-2, 1)));
    }

@Test
    void testClosest_nullOutsideFacing_2_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;

        final TestLineSegment insideFacing = new TestLineSegment(a, b);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing), null);

        PartitionTestUtils.assertPointsEqual(new TestPoint2D(-0.5, 0), boundary.closest(new TestPoint2D(-0.5, -1)));
    }

@Test
    void testClosest_nullOutsideFacing_3_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;

        final TestLineSegment insideFacing = new TestLineSegment(a, b);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing), null);

        PartitionTestUtils.assertPointsEqual(b, boundary.closest(TestPoint2D.ZERO));
    }

@Test
    void testClosest_nullOutsideFacing_4_oe() {
        final TestPoint2D a = new TestPoint2D(-1, 0);
        final TestPoint2D b = TestPoint2D.ZERO;

        final TestLineSegment insideFacing = new TestLineSegment(a, b);

        final RegionCutBoundary<TestPoint2D> boundary = new RegionCutBoundary<>(Collections.singletonList(insideFacing), null);

        PartitionTestUtils.assertPointsEqual(b, boundary.closest(new TestPoint2D(1, 2)));
    }

}
