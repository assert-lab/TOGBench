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

@Test
    void testConstructor_default_1_oe() {
        // act
        final RegionBSPTree1S tree = new RegionBSPTree1S();

        // assert
        Assertions.assertFalse(tree.isFull());
    }

@Test
    void testConstructor_default_2_oe() {
        // act
        final RegionBSPTree1S tree = new RegionBSPTree1S();

        // assert
        // removed other assertion
        Assertions.assertTrue(tree.isEmpty());
    }

@Test
    void testConstructor_default_3_oe() {
        // act
        final RegionBSPTree1S tree = new RegionBSPTree1S();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, tree.getSize(), TEST_EPS);
    }

@Test
    void testConstructor_default_4_oe() {
        // act
        final RegionBSPTree1S tree = new RegionBSPTree1S();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0, tree.getBoundarySize(), TEST_EPS);
    }

@Test
    void testConstructor_default_5_oe() {
        // act
        final RegionBSPTree1S tree = new RegionBSPTree1S();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNull(tree.getCentroid());
    }

@Test
    void testConstructor_true_1_oe() {
        // act
        final RegionBSPTree1S tree = new RegionBSPTree1S(true);

        // assert
        Assertions.assertTrue(tree.isFull());
    }

@Test
    void testConstructor_true_2_oe() {
        // act
        final RegionBSPTree1S tree = new RegionBSPTree1S(true);

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

@Test
    void testConstructor_true_3_oe() {
        // act
        final RegionBSPTree1S tree = new RegionBSPTree1S(true);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Angle.TWO_PI, tree.getSize(), TEST_EPS);
    }

@Test
    void testConstructor_true_4_oe() {
        // act
        final RegionBSPTree1S tree = new RegionBSPTree1S(true);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0, tree.getBoundarySize(), TEST_EPS);
    }

@Test
    void testConstructor_true_5_oe() {
        // act
        final RegionBSPTree1S tree = new RegionBSPTree1S(true);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNull(tree.getCentroid());
    }

@Test
    void testConstructor_false_1_oe() {
        // act
        final RegionBSPTree1S tree = new RegionBSPTree1S(false);

        // assert
        Assertions.assertFalse(tree.isFull());
    }

@Test
    void testConstructor_false_2_oe() {
        // act
        final RegionBSPTree1S tree = new RegionBSPTree1S(false);

        // assert
        // removed other assertion
        Assertions.assertTrue(tree.isEmpty());
    }

@Test
    void testConstructor_false_3_oe() {
        // act
        final RegionBSPTree1S tree = new RegionBSPTree1S(false);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, tree.getSize(), TEST_EPS);
    }

@Test
    void testConstructor_false_4_oe() {
        // act
        final RegionBSPTree1S tree = new RegionBSPTree1S(false);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0, tree.getBoundarySize(), TEST_EPS);
    }

@Test
    void testConstructor_false_5_oe() {
        // act
        final RegionBSPTree1S tree = new RegionBSPTree1S(false);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNull(tree.getCentroid());
    }

@Test
    void testFull_1_oe() {
        // act
        final RegionBSPTree1S tree = RegionBSPTree1S.full();

        // assert
        Assertions.assertTrue(tree.isFull());
    }

@Test
    void testFull_2_oe() {
        // act
        final RegionBSPTree1S tree = RegionBSPTree1S.full();

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

@Test
    void testFull_3_oe() {
        // act
        final RegionBSPTree1S tree = RegionBSPTree1S.full();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Angle.TWO_PI, tree.getSize(), TEST_EPS);
    }

@Test
    void testFull_4_oe() {
        // act
        final RegionBSPTree1S tree = RegionBSPTree1S.full();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0, tree.getBoundarySize(), TEST_EPS);
    }

@Test
    void testFull_5_oe() {
        // act
        final RegionBSPTree1S tree = RegionBSPTree1S.full();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNull(tree.getCentroid());
    }

@Test
    void testEmpty_1_oe() {
        // act
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();

        // assert
        Assertions.assertFalse(tree.isFull());
    }

@Test
    void testEmpty_2_oe() {
        // act
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();

        // assert
        // removed other assertion
        Assertions.assertTrue(tree.isEmpty());
    }

@Test
    void testEmpty_3_oe() {
        // act
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, tree.getSize(), TEST_EPS);
    }

@Test
    void testEmpty_4_oe() {
        // act
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0, tree.getBoundarySize(), TEST_EPS);
    }

@Test
    void testEmpty_5_oe() {
        // act
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNull(tree.getCentroid());
    }

@Test
    void testCopy_1_oe() {
        // arrange
        final RegionBSPTree1S orig = RegionBSPTree1S.fromInterval(AngularInterval.of(0, Math.PI, TEST_PRECISION));

        // act
        final RegionBSPTree1S copy = orig.copy();

        // assert
        Assertions.assertNotSame(orig, copy);
    }

@Test
    void testFromInterval_full_1_oe() {
        // act
        final RegionBSPTree1S tree = RegionBSPTree1S.fromInterval(AngularInterval.full());

        // assert
        Assertions.assertTrue(tree.isFull());
    }

@Test
    void testFromInterval_nonFull_1_oe() {
        for (double theta = 0.0; theta <= Angle.TWO_PI; theta += 0.2) {
            // arrange
            final double max = theta + Angle.PI_OVER_TWO;

            // act
            final RegionBSPTree1S tree = RegionBSPTree1S.fromInterval(AngularInterval.of(theta, max, TEST_PRECISION));

            checkSingleInterval(tree, theta, max);

            Assertions.assertEquals(Angle.PI_OVER_TWO, tree.getSize(), TEST_EPS);
    }
    }

@Test
    void testFromInterval_nonFull_2_oe() {
        for (double theta = 0.0; theta <= Angle.TWO_PI; theta += 0.2) {
            // arrange
            final double max = theta + Angle.PI_OVER_TWO;

            // act
            final RegionBSPTree1S tree = RegionBSPTree1S.fromInterval(AngularInterval.of(theta, max, TEST_PRECISION));

            checkSingleInterval(tree, theta, max);

            // removed other assertion
            Assertions.assertEquals(0, tree.getBoundarySize(), TEST_EPS);
    }
    }

@Test
    void testFromInterval_nonFull_3_oe() {
        for (double theta = 0.0; theta <= Angle.TWO_PI; theta += 0.2) {
            // arrange
            final double max = theta + Angle.PI_OVER_TWO;

            // act
            final RegionBSPTree1S tree = RegionBSPTree1S.fromInterval(AngularInterval.of(theta, max, TEST_PRECISION));

            checkSingleInterval(tree, theta, max);

            // removed other assertion
            // removed other assertion
            Assertions.assertEquals(Angle.Rad.WITHIN_0_AND_2PI.applyAsDouble(theta +(0.25 * Math.PI)),tree.getCentroid().getNormalizedAzimuth(),TEST_EPS);
    }
    }

@Test
    void testToIntervals_full_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.full();

        // act
        final List<AngularInterval> intervals = tree.toIntervals();

        // assert
        Assertions.assertEquals(1, intervals.size());
    }

@Test
    void testToIntervals_full_2_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.full();

        // act
        final List<AngularInterval> intervals = tree.toIntervals();

        // assert
        // removed other assertion

        final AngularInterval interval = intervals.get(0);
        Assertions.assertTrue(interval.isFull());
    }

@Test
    void testToIntervals_empty_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();

        // act
        final List<AngularInterval> intervals = tree.toIntervals();

        // assert
        Assertions.assertEquals(0, intervals.size());
    }

@Test
    void testToIntervals_wrapAround_joinedIntervalsOnPositiveSide_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(0.25 * Math.PI, Angle.PI_OVER_TWO, TEST_PRECISION));
        tree.add(AngularInterval.of(1.5 * Math.PI, 0.25 * Math.PI, TEST_PRECISION));

        // act
        final List<AngularInterval> intervals = tree.toIntervals();

        // assert
        Assertions.assertEquals(1, intervals.size());
    }

@Test
    void testToIntervals_wrapAround_joinedIntervalsOnNegativeSide_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(1.75 * Math.PI, Angle.PI_OVER_TWO, TEST_PRECISION));
        tree.add(AngularInterval.of(1.5 * Math.PI, 1.75 * Math.PI, TEST_PRECISION));

        // act
        final List<AngularInterval> intervals = tree.toIntervals();

        // assert
        Assertions.assertEquals(1, intervals.size());
    }

@Test
    void testToIntervals_multipleIntervals_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI - 0.5, Math.PI, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI, Math.PI + 0.5, TEST_PRECISION));

        // act
        final List<AngularInterval> intervals = tree.toIntervals();

        // assert
        Assertions.assertEquals(2, intervals.size());
    }

@Test
    void testToIntervals_multipleIntervals_complement_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI - 0.5, Math.PI, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI, Math.PI + 0.5, TEST_PRECISION));

        tree.complement();

        // act
        final List<AngularInterval> intervals = tree.toIntervals();

        // assert
        Assertions.assertEquals(2, intervals.size());
    }

@Test
    void testSplit_empty_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();

        // act/assert
        Assertions.assertEquals(SplitLocation.NEITHER,tree.split(CutAngles.createPositiveFacing(0,TEST_PRECISION)).getLocation());
    }

@Test
    void testSplit_empty_2_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();

        // act/assert
        // removed other assertion
        Assertions.assertEquals(SplitLocation.NEITHER,tree.split(CutAngles.createNegativeFacing(Angle.PI_OVER_TWO,TEST_PRECISION)).getLocation());
    }

@Test
    void testSplit_empty_3_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(SplitLocation.NEITHER,tree.split(CutAngles.createPositiveFacing(Math.PI,TEST_PRECISION)).getLocation());
    }

@Test
    void testSplit_empty_4_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(SplitLocation.NEITHER,tree.split(CutAngles.createNegativeFacing(-Angle.PI_OVER_TWO,TEST_PRECISION)).getLocation());
    }

@Test
    void testSplit_empty_5_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(SplitLocation.NEITHER,tree.split(CutAngles.createPositiveFacing(Angle.TWO_PI,TEST_PRECISION)).getLocation());
    }

@Test
    void testSplit_singleIntervalSplitIntoTwoIntervalsOnSameSide_1_oe() {
        // arrange
        final RegionBSPTree1S tree = AngularInterval.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, TEST_PRECISION).toTree();

        final CutAngle cut = CutAngles.createPositiveFacing(0, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.split(cut);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

@Test
    void testSplit_singleIntervalSplitIntoTwoIntervalsOnSameSide_2_oe() {
        // arrange
        final RegionBSPTree1S tree = AngularInterval.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, TEST_PRECISION).toTree();

        final CutAngle cut = CutAngles.createPositiveFacing(0, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.split(cut);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        Assertions.assertNull(minus);
    }

@Test
    void testSplit_singleIntervalSplitIntoTwoIntervalsOnSameSide_3_oe() {
        // arrange
        final RegionBSPTree1S tree = AngularInterval.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, TEST_PRECISION).toTree();

        final CutAngle cut = CutAngles.createPositiveFacing(0, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.split(cut);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        // removed other assertion

        final RegionBSPTree1S plus = split.getPlus();
        final List<AngularInterval> plusIntervals = plus.toIntervals();
        Assertions.assertEquals(1, plusIntervals.size());
    }

@Test
    void testSplit_multipleRegions_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(Angle.TWO_PI - 1, Angle.PI_OVER_TWO, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI, -Angle.PI_OVER_TWO, TEST_PRECISION));

        final CutAngle cut = CutAngles.createNegativeFacing(1, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.split(cut);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

@Test
    void testSplit_multipleRegions_2_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(Angle.TWO_PI - 1, Angle.PI_OVER_TWO, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI, -Angle.PI_OVER_TWO, TEST_PRECISION));

        final CutAngle cut = CutAngles.createNegativeFacing(1, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.split(cut);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        final List<AngularInterval> minusIntervals = minus.toIntervals();
        Assertions.assertEquals(3, minusIntervals.size());
    }

@Test
    void testSplit_multipleRegions_3_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(Angle.TWO_PI - 1, Angle.PI_OVER_TWO, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI, -Angle.PI_OVER_TWO, TEST_PRECISION));

        final CutAngle cut = CutAngles.createNegativeFacing(1, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.split(cut);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        final List<AngularInterval> minusIntervals = minus.toIntervals();
        // removed other assertion
        checkInterval(minusIntervals.get(0), 1, Angle.PI_OVER_TWO);
        checkInterval(minusIntervals.get(1), Math.PI, -Angle.PI_OVER_TWO);
        checkInterval(minusIntervals.get(2), Angle.TWO_PI - 1, 0);

        final RegionBSPTree1S plus = split.getPlus();
        final List<AngularInterval> plusIntervals = plus.toIntervals();
        Assertions.assertEquals(1, plusIntervals.size());
    }

@Test
    void testSplitDiameter_full_1_oe() {
        // arrange
        final RegionBSPTree1S full = RegionBSPTree1S.full();
        final CutAngle splitter = CutAngles.createPositiveFacing(Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = full.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

@Test
    void testSplitDiameter_full_2_oe() {
        // arrange
        final RegionBSPTree1S full = RegionBSPTree1S.full();
        final CutAngle splitter = CutAngles.createPositiveFacing(Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = full.splitDiameter(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        final List<AngularInterval> minusIntervals = minus.toIntervals();
        Assertions.assertEquals(1, minusIntervals.size());
    }

@Test
    void testSplitDiameter_full_3_oe() {
        // arrange
        final RegionBSPTree1S full = RegionBSPTree1S.full();
        final CutAngle splitter = CutAngles.createPositiveFacing(Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = full.splitDiameter(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        final List<AngularInterval> minusIntervals = minus.toIntervals();
        // removed other assertion
        checkInterval(minusIntervals.get(0), 1.5 * Math.PI, 2.5 * Math.PI);

        final RegionBSPTree1S plus = split.getPlus();
        final List<AngularInterval> plusIntervals = plus.toIntervals();
        Assertions.assertEquals(1, plusIntervals.size());
    }

@Test
    void testSplitDiameter_empty_1_oe() {
        // arrange
        final RegionBSPTree1S empty = RegionBSPTree1S.empty();
        final CutAngle splitter = CutAngles.createPositiveFacing(Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = empty.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.NEITHER, split.getLocation());
    }

@Test
    void testSplitDiameter_empty_2_oe() {
        // arrange
        final RegionBSPTree1S empty = RegionBSPTree1S.empty();
        final CutAngle splitter = CutAngles.createPositiveFacing(Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = empty.splitDiameter(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        Assertions.assertNull(minus);
    }

@Test
    void testSplitDiameter_empty_3_oe() {
        // arrange
        final RegionBSPTree1S empty = RegionBSPTree1S.empty();
        final CutAngle splitter = CutAngles.createPositiveFacing(Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = empty.splitDiameter(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        // removed other assertion

        final RegionBSPTree1S plus = split.getPlus();
        Assertions.assertNull(plus);
    }

@Test
    void testSplitDiameter_minus_zeroOnMinusSide_1_oe() {
        // arrange
        final RegionBSPTree1S tree = AngularInterval.of(0, 1, TEST_PRECISION).toTree();
        final CutAngle splitter = CutAngles.createPositiveFacing(1, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

@Test
    void testSplitDiameter_minus_zeroOnMinusSide_2_oe() {
        // arrange
        final RegionBSPTree1S tree = AngularInterval.of(0, 1, TEST_PRECISION).toTree();
        final CutAngle splitter = CutAngles.createPositiveFacing(1, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        final List<AngularInterval> minusIntervals = minus.toIntervals();
        Assertions.assertEquals(1, minusIntervals.size());
    }

@Test
    void testSplitDiameter_minus_zeroOnMinusSide_3_oe() {
        // arrange
        final RegionBSPTree1S tree = AngularInterval.of(0, 1, TEST_PRECISION).toTree();
        final CutAngle splitter = CutAngles.createPositiveFacing(1, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        final List<AngularInterval> minusIntervals = minus.toIntervals();
        // removed other assertion
        checkInterval(minusIntervals.get(0), 0, 1);

        final RegionBSPTree1S plus = split.getPlus();
        Assertions.assertNull(plus);
    }

@Test
    void testSplitDiameter_minus_zeroOnPlusSide_1_oe() {
        // arrange
        final RegionBSPTree1S tree = AngularInterval.of(1, 2, TEST_PRECISION).toTree();
        final CutAngle splitter = CutAngles.createNegativeFacing(0, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

@Test
    void testSplitDiameter_minus_zeroOnPlusSide_2_oe() {
        // arrange
        final RegionBSPTree1S tree = AngularInterval.of(1, 2, TEST_PRECISION).toTree();
        final CutAngle splitter = CutAngles.createNegativeFacing(0, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        final List<AngularInterval> minusIntervals = minus.toIntervals();
        Assertions.assertEquals(1, minusIntervals.size());
    }

@Test
    void testSplitDiameter_minus_zeroOnPlusSide_3_oe() {
        // arrange
        final RegionBSPTree1S tree = AngularInterval.of(1, 2, TEST_PRECISION).toTree();
        final CutAngle splitter = CutAngles.createNegativeFacing(0, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        final List<AngularInterval> minusIntervals = minus.toIntervals();
        // removed other assertion
        checkInterval(minusIntervals.get(0), 1, 2);

        final RegionBSPTree1S plus = split.getPlus();
        Assertions.assertNull(plus);
    }

@Test
    void testSplitDiameter_plus_zeroOnMinusSide_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(1, 1.1, TEST_PRECISION));
        tree.add(AngularInterval.of(2, 2.1, TEST_PRECISION));

        final CutAngle splitter = CutAngles.createPositiveFacing(1, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

@Test
    void testSplitDiameter_plus_zeroOnMinusSide_2_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(1, 1.1, TEST_PRECISION));
        tree.add(AngularInterval.of(2, 2.1, TEST_PRECISION));

        final CutAngle splitter = CutAngles.createPositiveFacing(1, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        Assertions.assertNull(minus);
    }

@Test
    void testSplitDiameter_plus_zeroOnMinusSide_3_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(1, 1.1, TEST_PRECISION));
        tree.add(AngularInterval.of(2, 2.1, TEST_PRECISION));

        final CutAngle splitter = CutAngles.createPositiveFacing(1, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        // removed other assertion

        final RegionBSPTree1S plus = split.getPlus();
        final List<AngularInterval> plusIntervals = plus.toIntervals();
        Assertions.assertEquals(2, plusIntervals.size());
    }

@Test
    void testSplitDiameter_plus_zeroOnPlusSide_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(1, 1.1, TEST_PRECISION));
        tree.add(AngularInterval.of(2, 2.1, TEST_PRECISION));

        final CutAngle splitter = CutAngles.createNegativeFacing(Math.PI - 1, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

@Test
    void testSplitDiameter_plus_zeroOnPlusSide_2_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(1, 1.1, TEST_PRECISION));
        tree.add(AngularInterval.of(2, 2.1, TEST_PRECISION));

        final CutAngle splitter = CutAngles.createNegativeFacing(Math.PI - 1, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        Assertions.assertNull(minus);
    }

@Test
    void testSplitDiameter_plus_zeroOnPlusSide_3_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(1, 1.1, TEST_PRECISION));
        tree.add(AngularInterval.of(2, 2.1, TEST_PRECISION));

        final CutAngle splitter = CutAngles.createNegativeFacing(Math.PI - 1, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        // removed other assertion

        final RegionBSPTree1S plus = split.getPlus();
        final List<AngularInterval> plusIntervals = plus.toIntervals();
        Assertions.assertEquals(2, plusIntervals.size());
    }

@Test
    void testSplitDiameter_both_zeroOnMinusSide_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(1, 1.1, TEST_PRECISION));
        tree.add(AngularInterval.of(2, 3, TEST_PRECISION));

        final CutAngle splitter = CutAngles.createPositiveFacing(2.5, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

@Test
    void testSplitDiameter_both_zeroOnMinusSide_2_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(1, 1.1, TEST_PRECISION));
        tree.add(AngularInterval.of(2, 3, TEST_PRECISION));

        final CutAngle splitter = CutAngles.createPositiveFacing(2.5, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        final List<AngularInterval> plusIntervals = minus.toIntervals();
        Assertions.assertEquals(2, plusIntervals.size());
    }

@Test
    void testSplitDiameter_both_zeroOnMinusSide_3_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(1, 1.1, TEST_PRECISION));
        tree.add(AngularInterval.of(2, 3, TEST_PRECISION));

        final CutAngle splitter = CutAngles.createPositiveFacing(2.5, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        final List<AngularInterval> plusIntervals = minus.toIntervals();
        // removed other assertion
        checkInterval(plusIntervals.get(0), 1, 1.1);
        checkInterval(plusIntervals.get(1), 2, 2.5);

        final RegionBSPTree1S plus = split.getPlus();
        final List<AngularInterval> minusIntervals = plus.toIntervals();
        Assertions.assertEquals(1, minusIntervals.size());
    }

@Test
    void testSplitDiameter_both_zeroOnPlusSide_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(1, 1.1, TEST_PRECISION));
        tree.add(AngularInterval.of(2, 3, TEST_PRECISION));

        final CutAngle splitter = CutAngles.createNegativeFacing(2.5, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

@Test
    void testSplitDiameter_both_zeroOnPlusSide_2_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(1, 1.1, TEST_PRECISION));
        tree.add(AngularInterval.of(2, 3, TEST_PRECISION));

        final CutAngle splitter = CutAngles.createNegativeFacing(2.5, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        final List<AngularInterval> minusIntervals = minus.toIntervals();
        Assertions.assertEquals(1, minusIntervals.size());
    }

@Test
    void testSplitDiameter_both_zeroOnPlusSide_3_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(1, 1.1, TEST_PRECISION));
        tree.add(AngularInterval.of(2, 3, TEST_PRECISION));

        final CutAngle splitter = CutAngles.createNegativeFacing(2.5, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = tree.splitDiameter(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree1S minus = split.getMinus();
        final List<AngularInterval> minusIntervals = minus.toIntervals();
        // removed other assertion
        checkInterval(minusIntervals.get(0), 2.5, 3);

        final RegionBSPTree1S plus = split.getPlus();
        final List<AngularInterval> plusIntervals = plus.toIntervals();
        Assertions.assertEquals(2, plusIntervals.size());
    }

@Test
    void testRegionProperties_singleInterval_wrapsZero_1_oe() {
        // arrange
        final RegionBSPTree1S tree = AngularInterval.of(-Angle.PI_OVER_TWO, Math.PI,
                TEST_PRECISION).toTree();

        // act/assert
        Assertions.assertEquals(1.5 * Math.PI, tree.getSize(), TEST_EPS);
    }

@Test
    void testRegionProperties_singleInterval_wrapsZero_2_oe() {
        // arrange
        final RegionBSPTree1S tree = AngularInterval.of(-Angle.PI_OVER_TWO, Math.PI,
                TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        Assertions.assertEquals(0, tree.getBoundarySize(), TEST_EPS);
    }

@Test
    void testRegionProperties_singleInterval_wrapsZero_3_oe() {
        // arrange
        final RegionBSPTree1S tree = AngularInterval.of(-Angle.PI_OVER_TWO, Math.PI,
                TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.25 * Math.PI, tree.getCentroid().getAzimuth(), TEST_EPS);
    }

@Test
    void testRegionProperties_singleInterval_doesNotWrap_1_oe() {
        // arrange
        final RegionBSPTree1S tree = AngularInterval.of(Angle.PI_OVER_TWO, Angle.TWO_PI,
                TEST_PRECISION).toTree();

        // act/assert
        Assertions.assertEquals(1.5 * Math.PI, tree.getSize(), TEST_EPS);
    }

@Test
    void testRegionProperties_singleInterval_doesNotWrap_2_oe() {
        // arrange
        final RegionBSPTree1S tree = AngularInterval.of(Angle.PI_OVER_TWO, Angle.TWO_PI,
                TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        Assertions.assertEquals(0, tree.getBoundarySize(), TEST_EPS);
    }

@Test
    void testRegionProperties_singleInterval_doesNotWrap_3_oe() {
        // arrange
        final RegionBSPTree1S tree = AngularInterval.of(Angle.PI_OVER_TWO, Angle.TWO_PI,
                TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1.25 * Math.PI, tree.getCentroid().getAzimuth(), TEST_EPS);
    }

@Test
    void testRegionProperties_multipleIntervals_sameSize_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(0, 0.1, TEST_PRECISION));
        tree.add(AngularInterval.of(0.2, 0.3, TEST_PRECISION));

        // act/assert
        Assertions.assertEquals(0.2, tree.getSize(), TEST_EPS);
    }

@Test
    void testRegionProperties_multipleIntervals_sameSize_2_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(0, 0.1, TEST_PRECISION));
        tree.add(AngularInterval.of(0.2, 0.3, TEST_PRECISION));

        // act/assert
        // removed other assertion
        Assertions.assertEquals(0, tree.getBoundarySize(), TEST_EPS);
    }

@Test
    void testRegionProperties_multipleIntervals_sameSize_3_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(0, 0.1, TEST_PRECISION));
        tree.add(AngularInterval.of(0.2, 0.3, TEST_PRECISION));

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.15, tree.getCentroid().getAzimuth(), TEST_EPS);
    }

@Test
    void testRegionProperties_multipleIntervals_differentSizes_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(0, 0.2, TEST_PRECISION));
        tree.add(AngularInterval.of(0.3, 0.7, TEST_PRECISION));

        // act/assert
        Assertions.assertEquals(0.6, tree.getSize(), TEST_EPS);
    }

@Test
    void testRegionProperties_multipleIntervals_differentSizes_2_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(0, 0.2, TEST_PRECISION));
        tree.add(AngularInterval.of(0.3, 0.7, TEST_PRECISION));

        // act/assert
        // removed other assertion
        Assertions.assertEquals(0, tree.getBoundarySize(), TEST_EPS);
    }

@Test
    void testRegionProperties_multipleIntervals_differentSizes_3_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(0, 0.2, TEST_PRECISION));
        tree.add(AngularInterval.of(0.3, 0.7, TEST_PRECISION));

        // act/assert
        // removed other assertion
        // removed other assertion

        final Vector2D centroidVector = Point1S.of(0.1).getVector().withNorm(0.2)
                .add(Point1S.of(0.5).getVector().withNorm(0.4));
        Assertions.assertEquals(Point1S.from(centroidVector).getAzimuth(), tree.getCentroid().getAzimuth(), TEST_EPS);
    }

@Test
    void testRegionProperties_equalAndOppositeIntervals_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(-1, 1, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI - 1, Math.PI + 1, TEST_PRECISION));

        // act/assert
        Assertions.assertEquals(4, tree.getSize(), TEST_EPS);
    }

@Test
    void testRegionProperties_equalAndOppositeIntervals_2_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(-1, 1, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI - 1, Math.PI + 1, TEST_PRECISION));

        // act/assert
        // removed other assertion
        Assertions.assertEquals(0, tree.getBoundarySize(), TEST_EPS);
    }

@Test
    void testRegionProperties_equalAndOppositeIntervals_3_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(-1, 1, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI - 1, Math.PI + 1, TEST_PRECISION));

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(tree.getCentroid()); // no unique centroid exists;
    }

@Test
    void testTransform_fullAndEmpty_1_oe() {
        // arrange
        final RegionBSPTree1S full = RegionBSPTree1S.full();
        final RegionBSPTree1S empty = RegionBSPTree1S.empty();

        // act
        full.transform(PI_MINUS_AZ);
        empty.transform(HALF_PI_PLUS_AZ);

        // assert
        Assertions.assertTrue(full.isFull());
    }

@Test
    void testTransform_fullAndEmpty_2_oe() {
        // arrange
        final RegionBSPTree1S full = RegionBSPTree1S.full();
        final RegionBSPTree1S empty = RegionBSPTree1S.empty();

        // act
        full.transform(PI_MINUS_AZ);
        empty.transform(HALF_PI_PLUS_AZ);

        // assert
        // removed other assertion
        Assertions.assertFalse(full.isEmpty());
    }

@Test
    void testTransform_fullAndEmpty_3_oe() {
        // arrange
        final RegionBSPTree1S full = RegionBSPTree1S.full();
        final RegionBSPTree1S empty = RegionBSPTree1S.empty();

        // act
        full.transform(PI_MINUS_AZ);
        empty.transform(HALF_PI_PLUS_AZ);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(empty.isFull());
    }

@Test
    void testTransform_fullAndEmpty_4_oe() {
        // arrange
        final RegionBSPTree1S full = RegionBSPTree1S.full();
        final RegionBSPTree1S empty = RegionBSPTree1S.empty();

        // act
        full.transform(PI_MINUS_AZ);
        empty.transform(HALF_PI_PLUS_AZ);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(empty.isEmpty());
    }

@Test
    void testTransform_halfPiPlusAz_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(-1, 1, TEST_PRECISION));
        tree.add(AngularInterval.of(2, 3, TEST_PRECISION));

        // act
        tree.transform(HALF_PI_PLUS_AZ);

        // assert
        Assertions.assertEquals(3, tree.getSize(), TEST_EPS);
    }

@Test
    void testTransform_halfPiPlusAz_2_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(-1, 1, TEST_PRECISION));
        tree.add(AngularInterval.of(2, 3, TEST_PRECISION));

        // act
        tree.transform(HALF_PI_PLUS_AZ);

        // assert
        // removed other assertion

        final List<AngularInterval> intervals = tree.toIntervals();

        Assertions.assertEquals(2, intervals.size());
    }

@Test
    void testTransform_piMinusAz_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(-1, 1, TEST_PRECISION));
        tree.add(AngularInterval.of(2, 3, TEST_PRECISION));

        // act
        tree.transform(PI_MINUS_AZ);

        // assert
        Assertions.assertEquals(3, tree.getSize(), TEST_EPS);
    }

@Test
    void testTransform_piMinusAz_2_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(-1, 1, TEST_PRECISION));
        tree.add(AngularInterval.of(2, 3, TEST_PRECISION));

        // act
        tree.transform(PI_MINUS_AZ);

        // assert
        // removed other assertion

        final List<AngularInterval> intervals = tree.toIntervals();

        Assertions.assertEquals(2, intervals.size());
    }

@Test
    void testProject_fullAndEmpty_1_oe() {
        // arrange
        final RegionBSPTree1S full = RegionBSPTree1S.full();
        final RegionBSPTree1S empty = RegionBSPTree1S.empty();

        // act/assert
        Assertions.assertNull(full.project(Point1S.ZERO));
    }

@Test
    void testProject_fullAndEmpty_2_oe() {
        // arrange
        final RegionBSPTree1S full = RegionBSPTree1S.full();
        final RegionBSPTree1S empty = RegionBSPTree1S.empty();

        // act/assert
        // removed other assertion
        Assertions.assertNull(full.project(Point1S.PI));
    }

@Test
    void testProject_fullAndEmpty_3_oe() {
        // arrange
        final RegionBSPTree1S full = RegionBSPTree1S.full();
        final RegionBSPTree1S empty = RegionBSPTree1S.empty();

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertNull(empty.project(Point1S.ZERO));
    }

@Test
    void testProject_fullAndEmpty_4_oe() {
        // arrange
        final RegionBSPTree1S full = RegionBSPTree1S.full();
        final RegionBSPTree1S empty = RegionBSPTree1S.empty();

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(empty.project(Point1S.PI));
    }

@Test
    void testProject_withIntervals_1_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI - 1, Math.PI + 1, TEST_PRECISION));

        // act/assert
        Assertions.assertEquals(-Angle.PI_OVER_TWO,tree.project(Point1S.of(-Angle.PI_OVER_TWO - 0.1)).getAzimuth(),TEST_EPS);
    }

@Test
    void testProject_withIntervals_2_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI - 1, Math.PI + 1, TEST_PRECISION));

        // act/assert
        // removed other assertion
        Assertions.assertEquals(-Angle.PI_OVER_TWO,tree.project(Point1S.of(-Angle.PI_OVER_TWO)).getAzimuth(),TEST_EPS);
    }

@Test
    void testProject_withIntervals_3_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI - 1, Math.PI + 1, TEST_PRECISION));

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-Angle.PI_OVER_TWO,tree.project(Point1S.of(-Angle.PI_OVER_TWO + 0.1)).getAzimuth(),TEST_EPS);
    }

@Test
    void testProject_withIntervals_4_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI - 1, Math.PI + 1, TEST_PRECISION));

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-Angle.PI_OVER_TWO, tree.project(Point1S.of(-0.1)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_withIntervals_5_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI - 1, Math.PI + 1, TEST_PRECISION));

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, tree.project(Point1S.ZERO).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_withIntervals_6_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI - 1, Math.PI + 1, TEST_PRECISION));

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, tree.project(Point1S.of(0.1)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_withIntervals_7_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI - 1, Math.PI + 1, TEST_PRECISION));

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Math.PI - 1,tree.project(Point1S.of(Math.PI - 0.5)).getAzimuth(),TEST_EPS);
    }

@Test
    void testProject_withIntervals_8_oe() {
        // arrange
        final RegionBSPTree1S tree = RegionBSPTree1S.empty();
        tree.add(AngularInterval.of(-Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, TEST_PRECISION));
        tree.add(AngularInterval.of(Math.PI - 1, Math.PI + 1, TEST_PRECISION));

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(Math.PI + 1,tree.project(Point1S.of(Math.PI + 0.5)).getAzimuth(),TEST_EPS);
    }

@Test
    void testProject_equidistant_1_oe() {
        // arrange
        final RegionBSPTree1S tree = AngularInterval.of(1, 2, TEST_PRECISION).toTree();
        final RegionBSPTree1S treeComplement = tree.copy();
        treeComplement.complement();

        // act/assert
        Assertions.assertEquals(1, tree.project(Point1S.of(1.5)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_equidistant_2_oe() {
        // arrange
        final RegionBSPTree1S tree = AngularInterval.of(1, 2, TEST_PRECISION).toTree();
        final RegionBSPTree1S treeComplement = tree.copy();
        treeComplement.complement();

        // act/assert
        // removed other assertion
        Assertions.assertEquals(1, treeComplement.project(Point1S.of(1.5)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMinSide_1_oe() {
        // arrange
        final double start = -1;
        final double end = 0.5;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        Assertions.assertEquals(end, tree.project(Point1S.of(-1.5 * Math.PI)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMinSide_2_oe() {
        // arrange
        final double start = -1;
        final double end = 0.5;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        Assertions.assertEquals(start, tree.project(Point1S.of(-Math.PI)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMinSide_3_oe() {
        // arrange
        final double start = -1;
        final double end = 0.5;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(start, tree.project(Point1S.of(-0.5 * Math.PI)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMinSide_4_oe() {
        // arrange
        final double start = -1;
        final double end = 0.5;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(start, tree.project(Point1S.of(-1)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMinSide_5_oe() {
        // arrange
        final double start = -1;
        final double end = 0.5;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(start, tree.project(Point1S.of(-0.5)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMinSide_6_oe() {
        // arrange
        final double start = -1;
        final double end = 0.5;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(end, tree.project(Point1S.of(-0.25)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMinSide_7_oe() {
        // arrange
        final double start = -1;
        final double end = 0.5;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(end, tree.project(Point1S.of(-0.1)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMinSide_8_oe() {
        // arrange
        final double start = -1;
        final double end = 0.5;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(end, tree.project(Point1S.ZERO).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMinSide_9_oe() {
        // arrange
        final double start = -1;
        final double end = 0.5;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(end, tree.project(Point1S.of(0.1)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMinSide_10_oe() {
        // arrange
        final double start = -1;
        final double end = 0.5;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(end, tree.project(Point1S.of(0.25)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMinSide_11_oe() {
        // arrange
        final double start = -1;
        final double end = 0.5;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(end, tree.project(Point1S.of(0.5)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMinSide_12_oe() {
        // arrange
        final double start = -1;
        final double end = 0.5;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(end, tree.project(Point1S.of(0.75)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMaxSide_1_oe() {
        // arrange
        final double start = -0.5;
        final double end = 1;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        Assertions.assertEquals(end, tree.project(Point1S.of(-1.5 * Math.PI)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMaxSide_2_oe() {
        // arrange
        final double start = -0.5;
        final double end = 1;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        Assertions.assertEquals(end, tree.project(Point1S.of(-Math.PI)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMaxSide_3_oe() {
        // arrange
        final double start = -0.5;
        final double end = 1;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(start, tree.project(Point1S.of(-0.5 * Math.PI)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMaxSide_4_oe() {
        // arrange
        final double start = -0.5;
        final double end = 1;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(start, tree.project(Point1S.of(-1)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMaxSide_5_oe() {
        // arrange
        final double start = -0.5;
        final double end = 1;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(start, tree.project(Point1S.of(-0.5)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMaxSide_6_oe() {
        // arrange
        final double start = -0.5;
        final double end = 1;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(start, tree.project(Point1S.of(-0.25)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMaxSide_7_oe() {
        // arrange
        final double start = -0.5;
        final double end = 1;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(start, tree.project(Point1S.of(-0.1)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMaxSide_8_oe() {
        // arrange
        final double start = -0.5;
        final double end = 1;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(start, tree.project(Point1S.ZERO).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMaxSide_9_oe() {
        // arrange
        final double start = -0.5;
        final double end = 1;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(start, tree.project(Point1S.of(0.1)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMaxSide_10_oe() {
        // arrange
        final double start = -0.5;
        final double end = 1;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(end, tree.project(Point1S.of(0.25)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMaxSide_11_oe() {
        // arrange
        final double start = -0.5;
        final double end = 1;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(end, tree.project(Point1S.of(0.5)).getAzimuth(), TEST_EPS);
    }

@Test
    void testProject_intervalAroundZero_closerOnMaxSide_12_oe() {
        // arrange
        final double start = -0.5;
        final double end = 1;
        final RegionBSPTree1S tree = AngularInterval.of(start, end, TEST_PRECISION).toTree();

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(end, tree.project(Point1S.of(0.75)).getAzimuth(), TEST_EPS);
    }

}
