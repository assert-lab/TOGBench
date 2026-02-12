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
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.oned.Interval;
import org.apache.commons.geometry.euclidean.oned.RegionBSPTree1D;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmbeddedTreeLineSubsetTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static final Line DEFAULT_TEST_LINE =
            Lines.fromPointAndDirection(Vector2D.of(0, 1), Vector2D.Unit.PLUS_X, TEST_PRECISION);

    @Test
    void testGetBounds_hasBounds() {
        // arrange
        final Line line = Lines.fromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION);

        final EmbeddedTreeLineSubset subset = new EmbeddedTreeLineSubset(line, false);

        final double sqrt2 = Math.sqrt(2);
        subset.getSubspaceRegion().add(Interval.of(-2 * sqrt2, -sqrt2, TEST_PRECISION));
        subset.getSubspaceRegion().add(Interval.of(0, sqrt2, TEST_PRECISION));

        // act
        final Bounds2D bounds = subset.getBounds();

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-2, -2), bounds.getMin(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 1), bounds.getMax(), TEST_EPS);
    }

    @Test
    void testClosest() {
        // arrange
        final RegionBSPTree1D subRegion = RegionBSPTree1D.empty();
        subRegion.add(Interval.of(0, 2, TEST_PRECISION));
        subRegion.add(Interval.of(3, 4, TEST_PRECISION));

        final Line line = Lines.fromPointAndAngle(Vector2D.of(0, 2), 0.0, TEST_PRECISION);
        final EmbeddedTreeLineSubset subset = new EmbeddedTreeLineSubset(line, subRegion);

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, 2), subset.closest(Vector2D.of(0, 1)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, 2), subset.closest(Vector2D.of(0, 3)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 2), subset.closest(Vector2D.of(1, 1)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 2), subset.closest(Vector2D.of(1, 3)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2, 2), subset.closest(Vector2D.of(2, 1)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2, 2), subset.closest(Vector2D.of(2, 3)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2, 2), subset.closest(Vector2D.of(2.4, 1)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2, 2), subset.closest(Vector2D.of(2.4, 3)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(3, 2), subset.closest(Vector2D.of(2.6, 1)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(3, 2), subset.closest(Vector2D.of(2.6, 3)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(4, 2), subset.closest(Vector2D.of(5, 1)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(4, 2), subset.closest(Vector2D.of(5, 3)), TEST_EPS);
    }

    @Test
    void testClassify() {
        // arrange
        final RegionBSPTree1D subRegion = RegionBSPTree1D.empty();
        subRegion.add(Interval.of(0, 2, TEST_PRECISION));
        subRegion.add(Interval.of(3, 4, TEST_PRECISION));

        final Line line = Lines.fromPointAndAngle(Vector2D.of(0, 2), 0.0, TEST_PRECISION);
        final EmbeddedTreeLineSubset subset = new EmbeddedTreeLineSubset(line, subRegion);

        // act/assert
        EuclideanTestUtils.assertRegionLocation(subset, RegionLocation.INSIDE,
                Vector2D.of(1, 2), Vector2D.of(3.5, 2));

        EuclideanTestUtils.assertRegionLocation(subset, RegionLocation.BOUNDARY,
                Vector2D.of(0, 2), Vector2D.of(2, 2), Vector2D.of(3, 2), Vector2D.of(4, 2));

        EuclideanTestUtils.assertRegionLocation(subset, RegionLocation.OUTSIDE,
                Vector2D.of(-1, 2), Vector2D.of(2.5, 2), Vector2D.of(5, 2),
                Vector2D.of(1, 3), Vector2D.of(3.5, 1));
    }

    private static void checkFiniteSegment(final LineConvexSubset segment, final Vector2D start, final Vector2D end) {
        Assertions.assertFalse(segment.isInfinite());

        EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testAdd_argumentsFromDifferentLine_1_oe() {
        // arrange
        final Line line = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line otherLine = Lines.fromPointAndAngle(Vector2D.of(0, 1), 1e-2, TEST_PRECISION);

        final EmbeddedTreeLineSubset subset = new EmbeddedTreeLineSubset(line);

        // act/assert
        try {
    subset.add(Lines.subsetFromInterval(otherLine, 0, 1));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAdd_argumentsFromDifferentLine_2_oe() {
        // arrange
        final Line line = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line otherLine = Lines.fromPointAndAngle(Vector2D.of(0, 1), 1e-2, TEST_PRECISION);

        final EmbeddedTreeLineSubset subset = new EmbeddedTreeLineSubset(line);

        // act/assert
        // removed other assertion
        try {
    subset.add(new EmbeddedTreeLineSubset(otherLine));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
