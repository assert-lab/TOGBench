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
package org.apache.commons.geometry.euclidean.oned;

import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntervalTest_OE25Dev {

    private static final double TEST_EPS = 1e-15;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testOf_doubles() {
        // act/assert
        checkInterval(Interval.of(0, 0, TEST_PRECISION), 0, 0);

        checkInterval(Interval.of(1, 2, TEST_PRECISION), 1, 2);
        checkInterval(Interval.of(2, 1, TEST_PRECISION), 1, 2);

        checkInterval(Interval.of(-2, -1, TEST_PRECISION), -2, -1);
        checkInterval(Interval.of(-1, -2, TEST_PRECISION), -2, -1);

        checkInterval(Interval.of(1, Double.POSITIVE_INFINITY, TEST_PRECISION),
                1, Double.POSITIVE_INFINITY);
        checkInterval(Interval.of(Double.POSITIVE_INFINITY, 1, TEST_PRECISION),
                1, Double.POSITIVE_INFINITY);

        checkInterval(Interval.of(Double.NEGATIVE_INFINITY, 1, TEST_PRECISION),
                Double.NEGATIVE_INFINITY, 1);
        checkInterval(Interval.of(1, Double.NEGATIVE_INFINITY, TEST_PRECISION),
                Double.NEGATIVE_INFINITY, 1);

        checkInterval(Interval.of(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, TEST_PRECISION),
                Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

        checkInterval(Interval.of(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, TEST_PRECISION),
                Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    @Test
    void testOf_points() {
        // act/assert
        checkInterval(Interval.of(Vector1D.of(1), Vector1D.of(2), TEST_PRECISION), 1, 2);
        checkInterval(Interval.of(Vector1D.of(Double.POSITIVE_INFINITY), Vector1D.of(Double.NEGATIVE_INFINITY), TEST_PRECISION),
                Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    @Test
    void testPoint() {
        // act/assert
        checkInterval(Interval.point(0, TEST_PRECISION), 0, 0);
        checkInterval(Interval.point(1, TEST_PRECISION), 1, 1);
        checkInterval(Interval.point(-1, TEST_PRECISION), -1, -1);
    }

    @Test
    void testMin() {
        // act/assert
        checkInterval(Interval.min(Double.NEGATIVE_INFINITY, TEST_PRECISION),
                Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

        checkInterval(Interval.min(0, TEST_PRECISION), 0, Double.POSITIVE_INFINITY);
        checkInterval(Interval.min(1, TEST_PRECISION), 1, Double.POSITIVE_INFINITY);
        checkInterval(Interval.min(-1, TEST_PRECISION), -1, Double.POSITIVE_INFINITY);
    }

    @Test
    void testMax() {
        // act/assert
        checkInterval(Interval.max(Double.POSITIVE_INFINITY, TEST_PRECISION),
                Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

        checkInterval(Interval.max(0, TEST_PRECISION), Double.NEGATIVE_INFINITY, 0);
        checkInterval(Interval.max(1, TEST_PRECISION), Double.NEGATIVE_INFINITY, 1);
        checkInterval(Interval.max(-1, TEST_PRECISION), Double.NEGATIVE_INFINITY, -1);
    }

    @Test
    void testClassify_finite() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);
        final Interval interval = Interval.of(-1, 1, precision);

        // act/assert
        checkClassify(interval, RegionLocation.OUTSIDE,
                Double.NEGATIVE_INFINITY, -2, -1.1,
                1.1, 2, Double.POSITIVE_INFINITY);

        checkClassify(interval, RegionLocation.BOUNDARY,
                -1.001, -1, -0.999,
                0.999, 1, 1.001);

        checkClassify(interval, RegionLocation.INSIDE, -0.9, 0, 0.9);

        checkClassify(interval, RegionLocation.OUTSIDE, Double.NaN);
    }

    @Test
    void testClassify_singlePoint() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);
        final Interval interval = Interval.of(1, 1, precision);

        // act/assert
        checkClassify(interval, RegionLocation.OUTSIDE,
                Double.NEGATIVE_INFINITY, 0, 0.9, 1.1, 2, Double.POSITIVE_INFINITY);

        checkClassify(interval, RegionLocation.BOUNDARY,
                0.999, 1, 1.0001);

        checkClassify(interval, RegionLocation.OUTSIDE, Double.NaN);
    }

    @Test
    void testClassify_maxInfinite() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);
        final Interval interval = Interval.of(-1, Double.POSITIVE_INFINITY, precision);

        // act/assert
        checkClassify(interval, RegionLocation.OUTSIDE,
                Double.NEGATIVE_INFINITY, -2, -1.1);

        checkClassify(interval, RegionLocation.BOUNDARY,
                -1.001, -1, -0.999);

        checkClassify(interval, RegionLocation.INSIDE,
                -0.9, 0, 1.0, Double.POSITIVE_INFINITY);

        checkClassify(interval, RegionLocation.OUTSIDE, Double.NaN);
    }

    @Test
    void testClassify_minInfinite() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);
        final Interval interval = Interval.of(Double.NEGATIVE_INFINITY, 1, precision);

        // act/assert
        checkClassify(interval, RegionLocation.INSIDE,
                Double.NEGATIVE_INFINITY, 0, 0.9);

        checkClassify(interval, RegionLocation.BOUNDARY,
                0.999, 1, 1.001);

        checkClassify(interval, RegionLocation.OUTSIDE,
                1.1, 2, Double.POSITIVE_INFINITY);

        checkClassify(interval, RegionLocation.OUTSIDE, Double.NaN);
    }

    @Test
    void testClassify_minMaxInfinite() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);
        final Interval interval = Interval.of(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, precision);

        // act/assert
        checkClassify(interval, RegionLocation.INSIDE,
                Double.NEGATIVE_INFINITY, -1, 0, 1, Double.POSITIVE_INFINITY);

        checkClassify(interval, RegionLocation.OUTSIDE, Double.NaN);
    }

    @Test
    void testContains_finite() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);
        final Interval interval = Interval.of(-1, 1, precision);

        // act/assert
        checkContains(interval, true,
                -1.001, -1, -0.999,
                0.999, 1, 1.001,

                -0.9, 0, 0.9);

        checkContains(interval, false,
                Double.NEGATIVE_INFINITY, -2, -1.1,
                1.1, 2, Double.POSITIVE_INFINITY);

        checkContains(interval, false, Double.NaN);
    }

    @Test
    void testProjectToBoundary_singlePoint() {
        // arrange
        final Interval interval = Interval.point(1, TEST_PRECISION);

        // act/assert
        checkBoundaryProjection(interval, -1, 1);
        checkBoundaryProjection(interval, 0, 1);

        checkBoundaryProjection(interval, 1, 1);

        checkBoundaryProjection(interval, 2, 1);
        checkBoundaryProjection(interval, 3, 1);

        checkBoundaryProjection(interval, Double.NEGATIVE_INFINITY, 1);
        checkBoundaryProjection(interval, Double.POSITIVE_INFINITY, 1);
    }

    @Test
    void testProjectToBoundary_closedInterval() {
        // arrange
        final Interval interval = Interval.of(1, 3, TEST_PRECISION);

        // act/assert
        checkBoundaryProjection(interval, -1, 1);
        checkBoundaryProjection(interval, 0, 1);
        checkBoundaryProjection(interval, 1, 1);

        checkBoundaryProjection(interval, 1.9, 1);
        checkBoundaryProjection(interval, 2, 1);
        checkBoundaryProjection(interval, 2.1, 3);

        checkBoundaryProjection(interval, 3, 3);
        checkBoundaryProjection(interval, 4, 3);
        checkBoundaryProjection(interval, 5, 3);

        checkBoundaryProjection(interval, Double.NEGATIVE_INFINITY, 1);
        checkBoundaryProjection(interval, Double.POSITIVE_INFINITY, 3);
    }

    @Test
    void testProjectToBoundary_noMinBoundary() {
        // arrange
        final Interval interval = Interval.of(Double.NEGATIVE_INFINITY, 1, TEST_PRECISION);

        // act/assert
        checkBoundaryProjection(interval, -1, 1);
        checkBoundaryProjection(interval, 0, 1);
        checkBoundaryProjection(interval, 1, 1);
        checkBoundaryProjection(interval, 2, 1);
        checkBoundaryProjection(interval, 3, 1);

        checkBoundaryProjection(interval, Double.NEGATIVE_INFINITY, 1);
        checkBoundaryProjection(interval, Double.POSITIVE_INFINITY, 1);
    }

    @Test
    void testProjectToBoundary_noMaxBoundary() {
        // arrange
        final Interval interval = Interval.of(1, Double.POSITIVE_INFINITY, TEST_PRECISION);

        // act/assert
        checkBoundaryProjection(interval, -1, 1);
        checkBoundaryProjection(interval, 0, 1);
        checkBoundaryProjection(interval, 1, 1);
        checkBoundaryProjection(interval, 2, 1);
        checkBoundaryProjection(interval, 3, 1);

        checkBoundaryProjection(interval, Double.NEGATIVE_INFINITY, 1);
        checkBoundaryProjection(interval, Double.POSITIVE_INFINITY, 1);
    }

    @Test
    void testTransform() {
        // arrange
        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.createScale(2);

        // act/assert
        checkInterval(Interval.of(-1, 2, TEST_PRECISION).transform(transform), -2, 4);

        checkInterval(Interval.of(Double.NEGATIVE_INFINITY, 2, TEST_PRECISION).transform(transform),
                Double.NEGATIVE_INFINITY, 4);

        checkInterval(Interval.of(-1, Double.POSITIVE_INFINITY, TEST_PRECISION).transform(transform), -2,
                Double.POSITIVE_INFINITY);

        checkInterval(Interval.of(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, TEST_PRECISION).transform(transform),
                Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    @Test
    void testTransform_reflection() {
        // arrange
        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.createScale(-1);

        // act/assert
        checkInterval(Interval.of(-1, 2, TEST_PRECISION).transform(transform), -2, 1);

        checkInterval(Interval.of(Double.NEGATIVE_INFINITY, 2, TEST_PRECISION).transform(transform),
                -2, Double.POSITIVE_INFINITY);

        checkInterval(Interval.of(-1, Double.POSITIVE_INFINITY, TEST_PRECISION).transform(transform),
                Double.NEGATIVE_INFINITY, 1);
    }

    private static void checkContains(final Interval interval, final boolean contains, final double... points) {
        for (final double x : points) {
            final String msg = "Unexpected contains status for point " + x;

            Assertions.assertEquals(contains, interval.contains(x), msg);
            Assertions.assertEquals(contains, interval.contains(Vector1D.of(x)), msg);
        }
    }

    private static void checkClassify(final Interval interval, final RegionLocation loc, final double... points) {
        for (final double x : points) {
            final String msg = "Unexpected location for point " + x;

            Assertions.assertEquals(loc, interval.classify(x), msg);
            Assertions.assertEquals(loc, interval.classify(Vector1D.of(x)), msg);
        }
    }

    private static void checkClassify(final RegionBSPTree1D tree, final RegionLocation loc, final double... points) {
        for (final double x : points) {
            final String msg = "Unexpected location for point " + x;

            Assertions.assertEquals(loc, tree.classify(x), msg);
            Assertions.assertEquals(loc, tree.classify(Vector1D.of(x)), msg);
        }
    }

    private static void checkBoundaryProjection(final Interval interval, final double location, final double projectedLocation) {
        final Vector1D pt = Vector1D.of(location);

        final Vector1D proj = interval.project(pt);

        Assertions.assertEquals(projectedLocation, proj.getX(), TEST_EPS);
    }

    /** Check that the given interval matches the arguments and is internally consistent.
     * @param interval
     * @param min
     * @param max
     */
    private static void checkInterval(final Interval interval, final double min, final double max) {
        checkInterval(interval, min, max, TEST_PRECISION);
    }

    /** Check that the given interval matches the arguments and is internally consistent.
     * @param interval
     * @param min
     * @param max
     * @param precision
     */
    private static void checkInterval(final Interval interval, final double min, final double max, final Precision.DoubleEquivalence precision) {
        Assertions.assertEquals(min, interval.getMin(), TEST_EPS);
        Assertions.assertEquals(max, interval.getMax(), TEST_EPS);

        final boolean finiteMin = Double.isFinite(min);
        final boolean finiteMax = Double.isFinite(max);

        Assertions.assertEquals(finiteMin, interval.hasMinBoundary());
        Assertions.assertEquals(finiteMax, interval.hasMaxBoundary());

        if (finiteMin) {
            Assertions.assertEquals(min, interval.getMinBoundary().getLocation(), TEST_EPS);
        } else {
            Assertions.assertNull(interval.getMinBoundary());
        }

        if (finiteMax) {
            Assertions.assertEquals(max, interval.getMaxBoundary().getLocation(), TEST_EPS);
        } else {
            Assertions.assertNull(interval.getMaxBoundary());
        }

        Assertions.assertFalse(interval.isEmpty()); // always false
    }

    @Test
    void testOf_doubles_invalidIntervals_1_oe() {

        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.of(1, Double.NaN, TEST_PRECISION));
    }

    @Test
    void testOf_doubles_invalidIntervals_2_oe() {

        // act/assert
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.of(Double.NaN, 1, TEST_PRECISION));
    }

    @Test
    void testOf_doubles_invalidIntervals_3_oe() {

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.of(Double.NaN, Double.NaN, TEST_PRECISION));
    }

    @Test
    void testOf_doubles_invalidIntervals_4_oe() {

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.of(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, TEST_PRECISION));
    }

    @Test
    void testOf_doubles_invalidIntervals_5_oe() {

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.of(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, TEST_PRECISION));
    }

    @Test
    void testOf_points_invalidIntervals_1_oe() {

        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.of(Vector1D.of(1), Vector1D.of(Double.NaN), TEST_PRECISION));
    }

    @Test
    void testOf_points_invalidIntervals_2_oe() {

        // act/assert
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.of(Vector1D.of(Double.POSITIVE_INFINITY), Vector1D.of(Double.POSITIVE_INFINITY), TEST_PRECISION));
    }

    @Test
    void testOf_hyperplanes_1_oe() {
        // act/assert
        Assertions.assertSame(Interval.full(), Interval.of(null, null));
    }

    @Test
    void testOf_hyperplanes_invalidArgs_1_oe() {
        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.of( OrientedPoints.fromLocationAndDirection(1, false, TEST_PRECISION), OrientedPoints.fromLocationAndDirection(1, false, TEST_PRECISION)));
    }

    @Test
    void testOf_hyperplanes_invalidArgs_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.of( OrientedPoints.fromLocationAndDirection(2, false, TEST_PRECISION), OrientedPoints.fromLocationAndDirection(1, true, TEST_PRECISION)));
    }

    @Test
    void testOf_hyperplanes_invalidArgs_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.of( OrientedPoints.fromLocationAndDirection(Double.POSITIVE_INFINITY, false, TEST_PRECISION), OrientedPoints.fromLocationAndDirection(Double.POSITIVE_INFINITY, true, TEST_PRECISION)));
    }

    @Test
    void testOf_hyperplanes_invalidArgs_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.of( OrientedPoints.fromLocationAndDirection(Double.NaN, false, TEST_PRECISION), OrientedPoints.fromLocationAndDirection(1, true, TEST_PRECISION)));
    }

    @Test
    void testOf_hyperplanes_invalidArgs_5_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.of( OrientedPoints.fromLocationAndDirection(1, false, TEST_PRECISION), OrientedPoints.fromLocationAndDirection(Double.NaN, true, TEST_PRECISION)));
    }

    @Test
    void testOf_hyperplanes_invalidArgs_6_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.of( OrientedPoints.fromLocationAndDirection(Double.NaN, false, TEST_PRECISION), OrientedPoints.fromLocationAndDirection(Double.NaN, true, TEST_PRECISION)));
    }

    @Test
    void testOf_hyperplanes_invalidArgs_7_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.of( null, OrientedPoints.fromLocationAndDirection(Double.NaN, true, TEST_PRECISION)));
    }

    @Test
    void testPoint_invalidArgs_1_oe() {
        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.point(Double.NEGATIVE_INFINITY, TEST_PRECISION));
    }

    @Test
    void testPoint_invalidArgs_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.point(Double.POSITIVE_INFINITY, TEST_PRECISION));
    }

    @Test
    void testPoint_invalidArgs_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.point(Double.NaN, TEST_PRECISION));
    }

    @Test
    void testMin_invalidArgs_1_oe() {
        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.min(Double.POSITIVE_INFINITY, TEST_PRECISION));
    }

    @Test
    void testMin_invalidArgs_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.min(Double.NaN, TEST_PRECISION));
    }

    @Test
    void testMax_invalidArgs_1_oe() {
        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.max(Double.NEGATIVE_INFINITY, TEST_PRECISION));
    }

    @Test
    void testMax_invalidArgs_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, () -> Interval.max(Double.NaN, TEST_PRECISION));
    }

    @Test
    void testIsInfinite_1_oe() {
        // act/assert
        Assertions.assertFalse(Interval.of(1, 2, TEST_PRECISION).isInfinite());
    }

    @Test
    void testIsInfinite_2_oe() {
        // act/assert
        // removed other assertion

        Assertions.assertTrue(Interval.of(Double.NEGATIVE_INFINITY, 2, TEST_PRECISION).isInfinite());
    }

    @Test
    void testIsInfinite_3_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(Interval.of(2, Double.POSITIVE_INFINITY, TEST_PRECISION).isInfinite());
    }

    @Test
    void testIsInfinite_4_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(Interval.of(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, TEST_PRECISION).isInfinite());
    }

    @Test
    void testIsFinite_1_oe() {
        // act/assert
        Assertions.assertTrue(Interval.of(1, 2, TEST_PRECISION).isFinite());
    }

    @Test
    void testIsFinite_2_oe() {
        // act/assert
        // removed other assertion

        Assertions.assertFalse(Interval.of(Double.NEGATIVE_INFINITY, 2, TEST_PRECISION).isFinite());
    }

    @Test
    void testIsFinite_3_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(Interval.of(2, Double.POSITIVE_INFINITY, TEST_PRECISION).isFinite());
    }

    @Test
    void testIsFinite_4_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(Interval.of(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, TEST_PRECISION).isFinite());
    }

    @Test
    void testIsFull_1_oe() {
        // act/assert
        Assertions.assertFalse(Interval.of(1, 1, TEST_PRECISION).isFull());
    }

    @Test
    void testIsFull_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertFalse(Interval.of(-2, 2, TEST_PRECISION).isFull());
    }

    @Test
    void testIsFull_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(Interval.of(1, Double.POSITIVE_INFINITY, TEST_PRECISION).isFull());
    }

    @Test
    void testIsFull_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(Interval.of(Double.NEGATIVE_INFINITY, 1, TEST_PRECISION).isFull());
    }

    @Test
    void testIsFull_5_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(Interval.of(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, TEST_PRECISION).isFull());
    }

    @Test
    void testGetSize_1_oe() {
        // act/assert
        Assertions.assertEquals(0, Interval.of(1, 1, TEST_PRECISION).getSize(), TEST_EPS);
    }

    @Test
    void testGetSize_2_oe() {
        // act/assert
        // removed other assertion

        Assertions.assertEquals(4, Interval.of(-2, 2, TEST_PRECISION).getSize(), TEST_EPS);
    }

    @Test
    void testGetSize_3_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(5, Interval.of(2, -3, TEST_PRECISION).getSize(), TEST_EPS);
    }

    @Test
    void testGetSize_4_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Double.POSITIVE_INFINITY,Interval.of(1,Double.POSITIVE_INFINITY,TEST_PRECISION).getSize(),TEST_EPS);
    }

    @Test
    void testGetSize_5_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(Double.POSITIVE_INFINITY,Interval.of(Double.NEGATIVE_INFINITY,1,TEST_PRECISION).getSize(),TEST_EPS);
    }

    @Test
    void testGetSize_6_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Double.POSITIVE_INFINITY,Interval.of(Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,TEST_PRECISION).getSize(),TEST_EPS);
    }

    @Test
    void testGetBoundarySize_1_oe() {
        // act/assert
        Assertions.assertEquals(0, Interval.of(1, 1, TEST_PRECISION).getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGetBoundarySize_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertEquals(0, Interval.of(-2, 5, TEST_PRECISION).getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGetBoundarySize_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, Interval.full().getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGetCentroid_5_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertNull(Interval.of(1, Double.POSITIVE_INFINITY, TEST_PRECISION).getCentroid());
    }

    @Test
    void testGetCentroid_6_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(Interval.of(Double.NEGATIVE_INFINITY, 1, TEST_PRECISION).getCentroid());
    }

    @Test
    void testGetCentroid_7_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNull(Interval.of(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, TEST_PRECISION).getCentroid());
    }

    @Test
    void checkToTree_finite_1_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);
        final Interval interval = Interval.of(-1, 1, precision);

        // act
        final RegionBSPTree1D tree = interval.toTree();

        // assert
        Assertions.assertEquals(5, tree.count());
    }

    @Test
    void checkToTree_singlePoint_1_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);
        final Interval interval = Interval.of(1, 1, precision);

        // act
        final RegionBSPTree1D tree = interval.toTree();

        // assert
        Assertions.assertEquals(5, tree.count());
    }

    @Test
    void checkToTree_maxInfinite_1_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);
        final Interval interval = Interval.of(-1, Double.POSITIVE_INFINITY, precision);

        // act
        final RegionBSPTree1D tree = interval.toTree();

        // assert
        Assertions.assertEquals(3, tree.count());
    }

    @Test
    void checkToTree_minInfinite_1_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);
        final Interval interval = Interval.of(Double.NEGATIVE_INFINITY, 1, precision);

        // act
        final RegionBSPTree1D tree = interval.toTree();

        // assert
        Assertions.assertEquals(3, tree.count());
    }

    @Test
    void checkToTree_minMaxInfinite_1_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);
        final Interval interval = Interval.of(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, precision);

        // act
        final RegionBSPTree1D tree = interval.toTree();

        // assert
        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testProjectToBoundary_full_1_oe() {
        // arrange
        final Interval full = Interval.full();


        // act/assert
        Assertions.assertNull(full.project(Vector1D.of(Double.NEGATIVE_INFINITY)));
    }

    @Test
    void testProjectToBoundary_full_2_oe() {
        // arrange
        final Interval full = Interval.full();


        // act/assert
        // removed other assertion
        Assertions.assertNull(full.project(Vector1D.of(0)));
    }

    @Test
    void testProjectToBoundary_full_3_oe() {
        // arrange
        final Interval full = Interval.full();


        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(full.project(Vector1D.of(Double.POSITIVE_INFINITY)));
    }

    @Test
    void testSplit_full_positiveFacingSplitter_1_oe() {
        // arrange
        final Interval interval = Interval.full();
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(1), true, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_full_negativeFacingSplitter_1_oe() {
        // arrange
        final Interval interval = Interval.full();
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(1), true, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_halfSpace_positiveFacingSplitter_1_oe() {
        // arrange
        final Interval interval = Interval.min(-1, TEST_PRECISION);
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(1), false, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_halfSpace_negativeFacingSplitter_1_oe() {
        // arrange
        final Interval interval = Interval.min(-1, TEST_PRECISION);
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(1), false, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_splitterBelowInterval_1_oe() {
        // arrange
        final Interval interval = Interval.of(5, 10, TEST_PRECISION);
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(1), true, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_splitterBelowInterval_2_oe() {
        // arrange
        final Interval interval = Interval.of(5, 10, TEST_PRECISION);
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(1), true, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertSame(interval, split.getPlus());
    }

    @Test
    void testSplit_splitterOnMinBoundary_1_oe() {
        // arrange
        final Interval interval = Interval.of(5, 10, TEST_PRECISION);
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(5), false, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_splitterOnMinBoundary_2_oe() {
        // arrange
        final Interval interval = Interval.of(5, 10, TEST_PRECISION);
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(5), false, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertSame(interval, split.getMinus());
    }

    @Test
    void testSplit_splitterAboveInterval_1_oe() {
        // arrange
        final Interval interval = Interval.of(5, 10, TEST_PRECISION);
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(11), true, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_splitterAboveInterval_2_oe() {
        // arrange
        final Interval interval = Interval.of(5, 10, TEST_PRECISION);
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(11), true, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertSame(interval, split.getMinus());
    }

    @Test
    void testSplit_splitterOnMaxBoundary_1_oe() {
        // arrange
        final Interval interval = Interval.of(5, 10, TEST_PRECISION);
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(10), false, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_splitterOnMaxBoundary_2_oe() {
        // arrange
        final Interval interval = Interval.of(5, 10, TEST_PRECISION);
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(10), false, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertSame(interval, split.getPlus());
    }

    @Test
    void testSplit_point_minusOnly_1_oe() {
        // arrange
        final Interval interval = Interval.point(2, TEST_PRECISION);
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(1), false, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_point_minusOnly_2_oe() {
        // arrange
        final Interval interval = Interval.point(2, TEST_PRECISION);
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(1), false, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        // removed other assertion

        checkInterval(split.getMinus(), 2, 2);
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_point_plusOnly_1_oe() {
        // arrange
        final Interval interval = Interval.point(2, TEST_PRECISION);
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(1), true, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_point_plusOnly_2_oe() {
        // arrange
        final Interval interval = Interval.point(2, TEST_PRECISION);
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(1), true, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_point_onPoint_1_oe() {
        // arrange
        final Interval interval = Interval.point(1, TEST_PRECISION);
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(1), true, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.NEITHER, split.getLocation());
    }

    @Test
    void testSplit_point_onPoint_2_oe() {
        // arrange
        final Interval interval = Interval.point(1, TEST_PRECISION);
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(1), true, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_point_onPoint_3_oe() {
        // arrange
        final Interval interval = Interval.point(1, TEST_PRECISION);
        final OrientedPoint splitter = OrientedPoints.fromPointAndDirection(
                Vector1D.of(1), true, TEST_PRECISION);

        // act
        final Split<Interval> split = interval.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testToString_1_oe() {
        // arrange
        final Interval interval = Interval.of(2, 1, TEST_PRECISION);

        // act
        final String str = interval.toString();

        // assert
        Assertions.assertTrue(str.contains("Interval"));
    }

    @Test
    void testToString_2_oe() {
        // arrange
        final Interval interval = Interval.of(2, 1, TEST_PRECISION);

        // act
        final String str = interval.toString();

        // assert
        // removed other assertion
        Assertions.assertTrue(str.contains("min= 1.0"));
    }

    @Test
    void testToString_3_oe() {
        // arrange
        final Interval interval = Interval.of(2, 1, TEST_PRECISION);

        // act
        final String str = interval.toString();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(str.contains("max= 2.0"));
    }

    @Test
    void testFull_1_oe() {
        // act
        final Interval full = Interval.full();

        // assert
        Assertions.assertTrue(full.isFull());
    }

    @Test
    void testFull_2_oe() {
        // act
        final Interval full = Interval.full();

        // assert
        // removed other assertion
        Assertions.assertFalse(full.isEmpty());
    }

    @Test
    void testFull_3_oe() {
        // act
        final Interval full = Interval.full();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(full.hasMinBoundary());
    }

    @Test
    void testFull_4_oe() {
        // act
        final Interval full = Interval.full();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(full.hasMaxBoundary());
    }

    @Test
    void testFull_5_oe() {
        // act
        final Interval full = Interval.full();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(full.isInfinite());
    }

    @Test
    void testFull_6_oe() {
        // act
        final Interval full = Interval.full();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.INSIDE, full.classify(Double.NEGATIVE_INFINITY));
    }

    @Test
    void testFull_7_oe() {
        // act
        final Interval full = Interval.full();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(RegionLocation.INSIDE, full.classify(Double.POSITIVE_INFINITY));
    }

}
