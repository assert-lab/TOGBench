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
package org.apache.commons.geometry.spherical.oned;

import java.util.List;

import org.apache.commons.geometry.core.Region;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegionBSPTree1STest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static final Transform1S HALF_PI_PLUS_AZ = Transform1S.createRotation(Angle.PI_OVER_TWO);

    private static final Transform1S PI_MINUS_AZ = Transform1S.createNegation().rotate(Math.PI);

    @Test
    void testClassify_full() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.full();

        // act/assert
        for (double az = -Angle.TWO_PI; az <= 2 * Angle.TWO_PI; az += 0.2) {
            checkClassify(tree, RegionLocation.INSIDE, az);
        }
    }

    @Test
    void testClassify_empty() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();

        // act/assert
        for (double az = -Angle.TWO_PI; az <= 2 * Angle.TWO_PI; az += 0.2) {
            checkClassify(tree, RegionLocation.OUTSIDE, az);
        }
    }

    @Test
    void testClassify() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.fromInterval(
                AngularInterval.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, TEST_PRECISION));

        // act/assert
        checkClassify(tree, RegionLocation.BOUNDARY,
                -Angle.PI_OVER_TWO, Angle.PI_OVER_TWO,
                -Angle.PI_OVER_TWO - Angle.TWO_PI, Angle.PI_OVER_TWO + Angle.TWO_PI);
        checkClassify(tree, RegionLocation.INSIDE,
                0.0, 0.5, -0.5,
                Angle.TWO_PI, 0.5 + Angle.TWO_PI, -0.5 - Angle.TWO_PI);
        checkClassify(tree, RegionLocation.OUTSIDE,
                Math.PI, Math.PI + 0.5, Math.PI - 0.5,
                Math.PI + Angle.TWO_PI, Math.PI + 0.5 + Angle.TWO_PI,
                Math.PI - 0.5 + Angle.TWO_PI);
    }

    @Test
    void testToIntervals_singleCut() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();

        for (double theta = 0; theta <= Angle.TWO_PI; theta += 0.2) {
            // act/assert
            tree.setEmpty();
            tree.getRoot().cut(CutAngles.createPositiveFacing(theta, TEST_PRECISION));

            checkSingleInterval(tree, 0, theta);

            tree.setEmpty();
            tree.getRoot().cut(CutAngles.createNegativeFacing(theta, TEST_PRECISION));

            checkSingleInterval(tree, theta, Angle.TWO_PI);
        }
    }

    @Test
    void testSplit_full() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.full();

        // act/assert
        checkSimpleSplit(
            tree.split(CutAngles.createPositiveFacing(1e-6, TEST_PRECISION)),
            AngularInterval.of(0, 1e-6, TEST_PRECISION),
            AngularInterval.of(1e-6, Angle.TWO_PI, TEST_PRECISION)
        );
        checkSimpleSplit(
            tree.split(CutAngles.createNegativeFacing(Angle.PI_OVER_TWO, TEST_PRECISION)),
            AngularInterval.of(Angle.PI_OVER_TWO, Angle.TWO_PI, TEST_PRECISION),
            AngularInterval.of(0, Angle.PI_OVER_TWO, TEST_PRECISION)
        );
        checkSimpleSplit(
            tree.split(CutAngles.createPositiveFacing(Math.PI, TEST_PRECISION)),
            AngularInterval.of(0, Math.PI, TEST_PRECISION),
            AngularInterval.of(Math.PI, Angle.TWO_PI, TEST_PRECISION)
        );
        checkSimpleSplit(
            tree.split(CutAngles.createNegativeFacing(-Angle.PI_OVER_TWO, TEST_PRECISION)),
            AngularInterval.of(-Angle.PI_OVER_TWO, Angle.TWO_PI, TEST_PRECISION),
            AngularInterval.of(0, -Angle.PI_OVER_TWO, TEST_PRECISION)
        );
        checkSimpleSplit(
            tree.split(CutAngles.createPositiveFacing(Angle.TWO_PI - 1e-6, TEST_PRECISION)),
            AngularInterval.of(0, Angle.TWO_PI - 1e-6, TEST_PRECISION),
            AngularInterval.of(Angle.TWO_PI - 1e-6, Angle.TWO_PI, TEST_PRECISION)
        );
    }

    @Test
    void testSplit_full_cutEquivalentToZero() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.full();

        final AngularInterval twoPi = AngularInterval.of(0, Angle.TWO_PI, TEST_PRECISION);

        // act/assert
        checkSimpleSplit(
            tree.split(CutAngles.createPositiveFacing(0, TEST_PRECISION)),
            null,
            twoPi
        );
        checkSimpleSplit(
            tree.split(CutAngles.createNegativeFacing(0, TEST_PRECISION)),
            twoPi,
            null
        );

        checkSimpleSplit(
            tree.split(CutAngles.createPositiveFacing(Angle.TWO_PI - 1e-18, TEST_PRECISION)),
            null,
            twoPi
        );
        checkSimpleSplit(
            tree.split(CutAngles.createNegativeFacing(Angle.TWO_PI - 1e-18, TEST_PRECISION)),
            twoPi,
            null
        );
    }

    @Test
    void testSplit_singleInterval() {
        // arrange
        final AngularInterval interval = AngularInterval.of(Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, TEST_PRECISION);
        final RegionBSPTree1S tree = interval.toTree();

        // act
        checkSimpleSplit(
            tree.split(CutAngles.createNegativeFacing(0, TEST_PRECISION)),
            interval,
            null
        );
        checkSimpleSplit(
            tree.split(CutAngles.createNegativeFacing(-Angle.TWO_PI, TEST_PRECISION)),
            interval,
            null
        );

        checkSimpleSplit(
            tree.split(CutAngles.createPositiveFacing(Angle.TWO_PI + Angle.PI_OVER_TWO, TEST_PRECISION)),
            null,
            interval
        );
        checkSimpleSplit(
            tree.split(CutAngles.createPositiveFacing(1.5 * Math.PI, TEST_PRECISION)),
            interval,
            null
        );

        checkSimpleSplit(
            tree.split(CutAngles.createNegativeFacing(Math.PI, TEST_PRECISION)),
            AngularInterval.of(Math.PI, -Angle.PI_OVER_TWO, TEST_PRECISION),
            AngularInterval.of(Angle.PI_OVER_TWO, Math.PI, TEST_PRECISION)
        );
    }

    private static void checkSimpleSplit(final Split<RegionBSPTree1S> split, final AngularInterval minusInterval,
                                         final AngularInterval plusInterval) {

        final RegionBSPTree1S minus = split.getMinus();
        if (minusInterval != null) {
            Assertions.assertNotNull(minus, "Expected minus region to not be null");
            checkSingleInterval(minus, minusInterval.getMin(), minusInterval.getMax());
        } else {
            Assertions.assertNull(minus, "Expected minus region to be null");
        }

        final RegionBSPTree1S plus = split.getPlus();
        if (plusInterval != null) {
            Assertions.assertNotNull(plus, "Expected plus region to not be null");
            checkSingleInterval(plus, plusInterval.getMin(), plusInterval.getMax());
        } else {
            Assertions.assertNull(plus, "Expected plus region to be null");
        }
    }

    private static void checkSingleInterval(final RegionBSPTree1S tree, final double min, final double max) {
        final List<AngularInterval> intervals = tree.toIntervals();

        Assertions.assertEquals(1, intervals.size(), "Expected a single interval in the tree");

        checkInterval(intervals.get(0), min, max);
    }

    private static void checkInterval(final AngularInterval interval, final double min, final double max) {
        final double normalizedMin = Angle.Rad.WITHIN_0_AND_2PI.applyAsDouble(min);
        final double normalizedMax = Angle.Rad.WITHIN_0_AND_2PI.applyAsDouble(max);

        if (TEST_PRECISION.eq(normalizedMin, normalizedMax)) {
            Assertions.assertTrue(interval.isFull());
        } else {
            Assertions.assertEquals(normalizedMin,interval.getMinBoundary().getPoint().getNormalizedAzimuth(),TEST_EPS);
            Assertions.assertEquals(normalizedMax,interval.getMaxBoundary().getPoint().getNormalizedAzimuth(),TEST_EPS);
        }
    }

    private static void checkClassify(final Region<Point1S> region, final RegionLocation loc, final double... pts) {
        for (final double pt : pts) {
            Assertions.assertEquals(loc, region.classify(Point1S.of(pt)), "Unexpected location for point " + pt);
        }
    }


}
