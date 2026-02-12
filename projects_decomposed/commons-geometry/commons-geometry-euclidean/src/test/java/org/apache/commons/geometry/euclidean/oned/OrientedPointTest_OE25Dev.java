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

import java.util.List;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.Transform;
import org.apache.commons.geometry.core.partitioning.HyperplaneConvexSubset;
import org.apache.commons.geometry.core.partitioning.HyperplaneLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrientedPointTest_OE25Dev {

    private static final double TEST_EPS = 1e-15;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testGetDirection() {
        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector1D.Unit.PLUS,
                OrientedPoints.fromPointAndDirection(Vector1D.of(2.0), true, TEST_PRECISION).getDirection(),
                TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector1D.Unit.MINUS,
                OrientedPoints.fromPointAndDirection(Vector1D.of(2.0), false, TEST_PRECISION).getDirection(),
                TEST_EPS);
    }

    @Test
    void testTransform_zeroScale() {
        // arrange
        final AffineTransformMatrix1D zeroScale = AffineTransformMatrix1D.createScale(0.0);

        final OrientedPoint pt = OrientedPoints.createPositiveFacing(Vector1D.of(2.0), TEST_PRECISION);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(
            () -> pt.transform(zeroScale),
            IllegalArgumentException.class, "Oriented point direction cannot be zero");
    }

    @Test
    void testOffset_infinityArguments() {
        // arrange
        final OrientedPoint pt = OrientedPoints.fromPointAndDirection(Vector1D.of(-2.0), true, TEST_PRECISION);

        // act/assert
        GeometryTestUtils.assertPositiveInfinity(pt.offset(Vector1D.of(Double.POSITIVE_INFINITY)));
        GeometryTestUtils.assertNegativeInfinity(pt.offset(Vector1D.of(Double.NEGATIVE_INFINITY)));
    }

    @Test
    void testClassify() {
        // arrange
        final Precision.DoubleEquivalence smallPrecision = Precision.doubleEquivalenceOfEpsilon(1e-10);
        final Precision.DoubleEquivalence largePrecision = Precision.doubleEquivalenceOfEpsilon(1e-1);

        final OrientedPoint smallPosFacing = OrientedPoints.fromLocationAndDirection(1.0, true, smallPrecision);
        final OrientedPoint largeNegFacing = OrientedPoints.fromLocationAndDirection(1.0, false, largePrecision);

        // act/assert
        assertClassify(HyperplaneLocation.MINUS, smallPosFacing,
                Double.NEGATIVE_INFINITY, -10, 0, 0.9, 0.99999, 1 - 1e-9);
        assertClassify(HyperplaneLocation.ON, smallPosFacing,
                1 - 1e-11, 1, 1 + 1e-11);
        assertClassify(HyperplaneLocation.PLUS, smallPosFacing,
                1 + 1e-9, 2, 10, Double.POSITIVE_INFINITY);

        assertClassify(HyperplaneLocation.PLUS, largeNegFacing,
                Double.NEGATIVE_INFINITY, -10, 0, 0.89);
        assertClassify(HyperplaneLocation.ON, largeNegFacing,
                0.91, 0.9999, 1, 1.001, 1.09);
        assertClassify(HyperplaneLocation.MINUS, largeNegFacing,
                1.11, 2, 10, Double.POSITIVE_INFINITY);
    }

    @Test
    void testFromPointAndDirection_invalidDirection() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(0.1);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(
            () -> OrientedPoints.fromPointAndDirection(Vector1D.of(2.0), Vector1D.of(0.09), precision),
            IllegalArgumentException.class, "Oriented point direction cannot be zero");
        GeometryTestUtils.assertThrowsWithMessage(
            () -> OrientedPoints.fromPointAndDirection(Vector1D.of(2.0), Vector1D.of(-0.09), precision),
            IllegalArgumentException.class, "Oriented point direction cannot be zero");
    }

    @Test
    void testSubset_split() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final OrientedPoint pt = OrientedPoints.createPositiveFacing(-1.5, precision);
        final HyperplaneConvexSubset<Vector1D> sub = pt.span();

        // act/assert
        checkSplit(sub, OrientedPoints.createPositiveFacing(1.0, precision), true, false);
        checkSplit(sub, OrientedPoints.createPositiveFacing(-1.5 + 1e-2, precision), true, false);

        checkSplit(sub, OrientedPoints.createNegativeFacing(1.0, precision), false, true);
        checkSplit(sub, OrientedPoints.createNegativeFacing(-1.5 + 1e-2, precision), false, true);

        checkSplit(sub, OrientedPoints.createNegativeFacing(-1.5, precision), false, false);
        checkSplit(sub, OrientedPoints.createNegativeFacing(-1.5 + 1e-4, precision), false, false);
        checkSplit(sub, OrientedPoints.createNegativeFacing(-1.5 - 1e-4, precision), false, false);
    }

    private void checkSplit(final HyperplaneConvexSubset<Vector1D> sub, final OrientedPoint splitter, final boolean minus, final boolean plus) {
        final Split<? extends HyperplaneConvexSubset<Vector1D>> split = sub.split(splitter);

        Assertions.assertSame(minus ? sub : null, split.getMinus());
        Assertions.assertSame(plus ? sub : null, split.getPlus());
    }

    @Test
    void testSubset_closestContained() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-1);
        final OrientedPoint pt = OrientedPoints.createPositiveFacing(1, precision);
        final HyperplaneConvexSubset<Vector1D> sub = pt.span();

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector1D.of(1), sub.closest(Vector1D.NEGATIVE_INFINITY), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector1D.of(1), sub.closest(Vector1D.of(0)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector1D.of(1), sub.closest(Vector1D.of(1)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector1D.of(1), sub.closest(Vector1D.of(2)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector1D.of(1), sub.closest(Vector1D.POSITIVE_INFINITY), TEST_EPS);
    }

    private static void assertOrientedPoint(final OrientedPoint pt, final double location, final boolean positiveFacing,
                                            final Precision.DoubleEquivalence precision) {
        Assertions.assertEquals(location, pt.getPoint().getX(), TEST_EPS);
        Assertions.assertEquals(location, pt.getLocation(), TEST_EPS);
        Assertions.assertEquals(positiveFacing ? 1.0 : -1.0, pt.getDirection().getX(), TEST_EPS);
        Assertions.assertEquals(positiveFacing, pt.isPositiveFacing());
        Assertions.assertSame(precision, pt.getPrecision());
    }

    private static void assertClassify(final HyperplaneLocation expected, final OrientedPoint pt, final double... locations) {
        for (final double location : locations) {
            final String msg = "Unexpected classification for location " + location;

            Assertions.assertEquals(expected, pt.classify(location), msg);
            Assertions.assertEquals(expected, pt.classify(Vector1D.of(location)), msg);
        }
    }


}
