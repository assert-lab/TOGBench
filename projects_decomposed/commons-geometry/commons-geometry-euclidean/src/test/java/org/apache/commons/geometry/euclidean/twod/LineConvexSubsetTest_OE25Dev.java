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
package org.apache.commons.geometry.euclidean.twod;

import java.util.List;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.partitioning.HyperplaneLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.oned.Interval;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LineConvexSubsetTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testFromInterval_doubleArgs_invalid() {
        // arrange
        final Line line = Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION);

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines.subsetFromInterval(line, 0, Double.NaN);
        }, IllegalArgumentException.class, "Invalid line subset interval: 0.0, NaN");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines.subsetFromInterval(line, Double.NaN, 0.0);
        }, IllegalArgumentException.class, "Invalid line subset interval: NaN, 0.0");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines.subsetFromInterval(line, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        }, IllegalArgumentException.class, "Invalid line subset interval: Infinity, Infinity");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines.subsetFromInterval(line, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        }, IllegalArgumentException.class, "Invalid line subset interval: -Infinity, -Infinity");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines.subsetFromInterval(line, Double.POSITIVE_INFINITY, Double.NaN);
        }, IllegalArgumentException.class, "Invalid line subset interval: Infinity, NaN");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines.subsetFromInterval(line, Double.NaN, Double.NEGATIVE_INFINITY);
        }, IllegalArgumentException.class, "Invalid line subset interval: NaN, -Infinity");
    }

    private static void checkInterval(final Interval expected, final Interval actual) {
        Assertions.assertEquals(expected.getMin(), actual.getMin(), TEST_EPS);
        Assertions.assertEquals(expected.getMax(), actual.getMax(), TEST_EPS);
    }

    private static void checkFinite(final LineConvexSubset segment, final Vector2D start, final Vector2D end) {
        checkFinite(segment, start, end, TEST_PRECISION);
    }

    private static void checkFinite(final LineConvexSubset segment, final Vector2D start, final Vector2D end, final Precision.DoubleEquivalence precision) {
        Assertions.assertFalse(segment.isInfinite());

        EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);

        final Line line = segment.getLine();
        Assertions.assertEquals(HyperplaneLocation.ON, line.classify(segment.getStartPoint()));
        Assertions.assertEquals(HyperplaneLocation.ON, line.classify(segment.getEndPoint()));

        Assertions.assertEquals(line.toSubspace(segment.getStartPoint()).getX(), segment.getSubspaceStart(), TEST_EPS);
        Assertions.assertEquals(line.toSubspace(segment.getEndPoint()).getX(), segment.getSubspaceEnd(), TEST_EPS);

        Assertions.assertSame(precision, segment.getPrecision());
        Assertions.assertSame(precision, line.getPrecision());
    }

    private static void checkInfinite(final LineConvexSubset segment, final Line line, final Vector2D start, final Vector2D end) {
        checkInfinite(segment, line, start, end, TEST_PRECISION);
    }

    private static void checkInfinite(final LineConvexSubset segment, final Line line, final Vector2D start, final Vector2D end,
                                      final Precision.DoubleEquivalence precision) {

        Assertions.assertTrue(segment.isInfinite());

        Assertions.assertEquals(line, segment.getLine());

        if (start == null) {
            Assertions.assertNull(segment.getStartPoint());
        } else {
            EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
            Assertions.assertEquals(line.toSubspace(segment.getStartPoint()).getX(), segment.getSubspaceStart(), TEST_EPS);
        }

        if (end == null) {
            Assertions.assertNull(segment.getEndPoint());
        } else {
            EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
            Assertions.assertEquals(line.toSubspace(segment.getEndPoint()).getX(), segment.getSubspaceEnd(), TEST_EPS);
        }

        Assertions.assertSame(precision, segment.getPrecision());
        Assertions.assertSame(precision, line.getPrecision());
    }


}
