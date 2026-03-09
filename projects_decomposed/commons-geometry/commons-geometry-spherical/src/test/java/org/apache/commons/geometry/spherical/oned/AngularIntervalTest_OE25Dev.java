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
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class AngularIntervalTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testOf_doubles() {
        // act/assert
        checkInterval(AngularInterval.of(0, 1, TEST_PRECISION), 0, 1);
        checkInterval(AngularInterval.of(1, 0, TEST_PRECISION), 1, Angle.TWO_PI);
        checkInterval(AngularInterval.of(-2, -1.5, TEST_PRECISION), -2, -1.5);
        checkInterval(AngularInterval.of(-2, -2.5, TEST_PRECISION), -2, Angle.TWO_PI - 2.5);

        checkFull(AngularInterval.of(1, 1, TEST_PRECISION));
        checkFull(AngularInterval.of(0, 1e-11, TEST_PRECISION));
        checkFull(AngularInterval.of(0, -1e-11, TEST_PRECISION));
        checkFull(AngularInterval.of(0, Angle.TWO_PI, TEST_PRECISION));
    }

    @Test
    void testOf_doubles_invalidArgs() {
        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.of(Double.NEGATIVE_INFINITY, 0, TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.of(0, Double.POSITIVE_INFINITY, TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.of(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.of(Double.NaN, 0, TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.of(0, Double.NaN, TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.of(Double.NaN, Double.NaN, TEST_PRECISION));
    }

    @Test
    void testOf_points() {
        // act/assert
        checkInterval(AngularInterval.of(Point1S.of(0), Point1S.of(1), TEST_PRECISION), 0, 1);
        checkInterval(AngularInterval.of(Point1S.of(1), Point1S.of(0), TEST_PRECISION), 1, Angle.TWO_PI);
        checkInterval(AngularInterval.of(Point1S.of(-2), Point1S.of(-1.5), TEST_PRECISION), -2, -1.5);
        checkInterval(AngularInterval.of(Point1S.of(-2), Point1S.of(-2.5), TEST_PRECISION), -2, Angle.TWO_PI - 2.5);

        checkFull(AngularInterval.of(Point1S.of(1), Point1S.of(1), TEST_PRECISION));
        checkFull(AngularInterval.of(Point1S.of(0), Point1S.of(1e-11), TEST_PRECISION));
        checkFull(AngularInterval.of(Point1S.of(0), Point1S.of(-1e-11), TEST_PRECISION));
    }

    @Test
    void testOf_points_invalidArgs() {
        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.of(Point1S.of(Double.NEGATIVE_INFINITY), Point1S.ZERO, TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.of(Point1S.ZERO, Point1S.of(Double.POSITIVE_INFINITY), TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.of(Point1S.of(Double.POSITIVE_INFINITY), Point1S.of(Double.NEGATIVE_INFINITY), TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.of(Point1S.NaN, Point1S.ZERO, TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.of(Point1S.ZERO, Point1S.NaN, TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.of(Point1S.NaN, Point1S.NaN, TEST_PRECISION));
    }

    @Test
    void testOf_orientedPoints() {
        // arrange
        final Precision.DoubleEquivalence precisionA = Precision.doubleEquivalenceOfEpsilon(1e-3);
        final Precision.DoubleEquivalence precisionB = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final CutAngle zeroPos = CutAngles.createPositiveFacing(Point1S.ZERO, precisionA);
        final CutAngle zeroNeg = CutAngles.createNegativeFacing(Point1S.ZERO, precisionA);

        final CutAngle piPos = CutAngles.createPositiveFacing(Point1S.PI, precisionA);
        final CutAngle piNeg = CutAngles.createNegativeFacing(Point1S.PI, precisionA);

        final CutAngle almostPiPos = CutAngles.createPositiveFacing(Point1S.of(Math.PI + 5e-3), precisionB);

        // act/assert
        checkInterval(AngularInterval.of(zeroNeg, piPos), 0, Math.PI);
        checkInterval(AngularInterval.of(zeroPos, piNeg), Math.PI, Angle.TWO_PI);

        checkFull(AngularInterval.of(zeroPos, zeroNeg));
        checkFull(AngularInterval.of(zeroPos, piPos));
        checkFull(AngularInterval.of(piNeg, zeroNeg));

        checkFull(AngularInterval.of(almostPiPos, piNeg));
        checkFull(AngularInterval.of(piNeg, almostPiPos));
    }

    @Test
    void testOf_orientedPoints_invalidArgs() {
        // arrange
        final CutAngle pt = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);
        final CutAngle nan = CutAngles.createPositiveFacing(Point1S.NaN, TEST_PRECISION);

        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.of(pt, nan));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.of(nan, pt));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.of(nan, nan));
    }

    @Test
    void testFull() {
        // act
        final AngularInterval.Convex interval = AngularInterval.full();

        // assert
        checkFull(interval);
    }

    @Test
    void testClassify_full() {
        // arrange
        final AngularInterval interval = AngularInterval.full();

        // act/assert
        for (double a = -2 * Math.PI; a >= 4 * Math.PI; a += 0.5) {
            checkClassify(interval, RegionLocation.INSIDE, Point1S.of(a));
        }
    }

    @Test
    void testClassify_almostFull() {
        // arrange
        final AngularInterval interval = AngularInterval.of(1 + 2e-10, 1, TEST_PRECISION);

        // act/assert
        checkClassify(interval, RegionLocation.BOUNDARY,
                Point1S.of(1 + 2e-10), Point1S.of(1 + 6e-11), Point1S.of(1));

        checkClassify(interval, RegionLocation.INSIDE, Point1S.of(1 + 6e-11 + Math.PI));

        for (double a = 1 + 1e-9; a >= 1 - 1e-9 + Angle.TWO_PI; a += 0.5) {
            checkClassify(interval, RegionLocation.INSIDE, Point1S.of(a));
        }
    }

    @Test
    void testClassify_sizeableGap() {
        // arrange
        final AngularInterval interval = AngularInterval.of(0.25, -0.25, TEST_PRECISION);

        // act/assert
        checkClassify(interval, RegionLocation.OUTSIDE,
                Point1S.ZERO, Point1S.of(-0.2), Point1S.of(0.2));
        checkClassify(interval, RegionLocation.BOUNDARY,
                Point1S.of(-0.25), Point1S.of(0.2499999999999));
        checkClassify(interval, RegionLocation.INSIDE,
                Point1S.of(1), Point1S.PI, Point1S.of(-1));
    }

    @Test
    void testClassify_halfPi() {
        // arrange
        final AngularInterval interval = AngularInterval.of(Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, TEST_PRECISION);

        // act/assert
        checkClassify(interval, RegionLocation.OUTSIDE,
                Point1S.ZERO, Point1S.of(Angle.PI_OVER_TWO - 0.1), Point1S.of(-Angle.PI_OVER_TWO + 0.1));
        checkClassify(interval, RegionLocation.BOUNDARY,
                Point1S.of(Angle.PI_OVER_TWO), Point1S.of(1.5 * Math.PI));
        checkClassify(interval, RegionLocation.INSIDE,
                Point1S.PI, Point1S.of(Angle.PI_OVER_TWO + 0.1), Point1S.of(-Angle.PI_OVER_TWO - 0.1));
    }

    @Test
    void testClassify_almostEmpty() {
        // arrange
        final AngularInterval interval = AngularInterval.of(1, 1 + 2e-10, TEST_PRECISION);

        // act/assert
        checkClassify(interval, RegionLocation.BOUNDARY,
                Point1S.of(1 + 2e-10), Point1S.of(1 + 6e-11), Point1S.of(1));

        checkClassify(interval, RegionLocation.OUTSIDE, Point1S.of(1 + 6e-11 + Math.PI));

        for (double a = 1 + 1e-9; a >= 1 - 1e-9 + Angle.TWO_PI; a += 0.5) {
            checkClassify(interval, RegionLocation.OUTSIDE, Point1S.of(a));
        }
    }

    @Test
    void testProject_full() {
        // arrange
        final AngularInterval interval = AngularInterval.full();

        // act/assert
        Assertions.assertNull(interval.project(Point1S.ZERO));
        Assertions.assertNull(interval.project(Point1S.PI));
    }

    @Test
    void testProject() {
        // arrange
        final AngularInterval interval = AngularInterval.of(1, 2, TEST_PRECISION);

        // act/assert
        Assertions.assertEquals(1, interval.project(Point1S.ZERO).getAzimuth(), TEST_EPS);
        Assertions.assertEquals(1, interval.project(Point1S.of(1)).getAzimuth(), TEST_EPS);
        Assertions.assertEquals(1, interval.project(Point1S.of(1.5)).getAzimuth(), TEST_EPS);

        Assertions.assertEquals(2, interval.project(Point1S.of(2)).getAzimuth(), TEST_EPS);
        Assertions.assertEquals(2, interval.project(Point1S.PI).getAzimuth(), TEST_EPS);
        Assertions.assertEquals(2, interval.project(Point1S.of(1.4 + Math.PI)).getAzimuth(), TEST_EPS);

        Assertions.assertEquals(1, interval.project(Point1S.of(1.5 + Math.PI)).getAzimuth(), TEST_EPS);
        Assertions.assertEquals(1, interval.project(Point1S.of(1.6 + Math.PI)).getAzimuth(), TEST_EPS);
    }

    @Test
    void testTransform_full() {
        // arrange
        final AngularInterval interval = AngularInterval.full();

        final Transform1S rotate = Transform1S.createRotation(Angle.PI_OVER_TWO);
        final Transform1S invert = Transform1S.createNegation().rotate(Angle.PI_OVER_TWO);

        // act/assert
        checkFull(interval.transform(rotate));
        checkFull(interval.transform(invert));
    }

    @Test
    void testTransform() {
        // arrange
        final AngularInterval interval = AngularInterval.of(Angle.PI_OVER_TWO, Math.PI, TEST_PRECISION);

        final Transform1S rotate = Transform1S.createRotation(Angle.PI_OVER_TWO);
        final Transform1S invert = Transform1S.createNegation().rotate(Angle.PI_OVER_TWO);

        // act/assert
        checkInterval(interval.transform(rotate), Math.PI, 1.5 * Math.PI);
        checkInterval(interval.transform(invert), -0.5 * Math.PI, 0.0);
    }

    @Test
    void testWrapsZero() {
        // act/assert
        Assertions.assertFalse(AngularInterval.full().wrapsZero());
        Assertions.assertFalse(AngularInterval.of(0, Angle.PI_OVER_TWO, TEST_PRECISION).wrapsZero());
        Assertions.assertFalse(AngularInterval.of(Angle.PI_OVER_TWO, Math.PI, TEST_PRECISION).wrapsZero());
        Assertions.assertFalse(AngularInterval.of(Math.PI, 1.5 * Math.PI, TEST_PRECISION).wrapsZero());
        Assertions.assertFalse(AngularInterval.of(1.5 * Math.PI, Angle.TWO_PI - 1e-5, TEST_PRECISION).wrapsZero());

        Assertions.assertTrue(AngularInterval.of(1.5 * Math.PI, Angle.TWO_PI, TEST_PRECISION).wrapsZero());
        Assertions.assertTrue(AngularInterval.of(1.5 * Math.PI, 2.5 * Math.PI, TEST_PRECISION).wrapsZero());
        Assertions.assertTrue(AngularInterval.of(-2.5 * Math.PI, -1.5 * Math.PI, TEST_PRECISION).wrapsZero());
    }

    @Test
    void testToTree_full() {
        // arrange
        final AngularInterval interval = AngularInterval.full();

        // act
        final RegionBSPTree1S tree = interval.toTree();

        // assert
        Assertions.assertTrue(tree.isFull());
        Assertions.assertFalse(tree.isEmpty());

        checkClassify(tree, RegionLocation.INSIDE,
                Point1S.ZERO, Point1S.of(Angle.PI_OVER_TWO),
                Point1S.PI, Point1S.of(-Angle.PI_OVER_TWO));
    }

    @Test
    void testToTree_intervalEqualToPi() {
        // arrange
        final AngularInterval interval = AngularInterval.of(0.0, Math.PI, TEST_PRECISION);

        // act
        final RegionBSPTree1S tree = interval.toTree();

        // assert
        Assertions.assertFalse(tree.isFull());
        Assertions.assertFalse(tree.isEmpty());

        checkClassify(tree, RegionLocation.BOUNDARY,
                Point1S.ZERO, Point1S.PI);

        checkClassify(tree, RegionLocation.INSIDE,
                Point1S.of(1e-4), Point1S.of(0.25 * Math.PI),
                Point1S.of(-1.25 * Math.PI), Point1S.of(Math.PI - 1e-4));

        checkClassify(tree, RegionLocation.OUTSIDE,
                Point1S.of(-1e-4), Point1S.of(-0.25 * Math.PI),
                Point1S.of(1.25 * Math.PI), Point1S.of(-Math.PI + 1e-4));
    }

    @Test
    void testToTree_intervalLessThanPi() {
        // arrange
        final AngularInterval interval = AngularInterval.of(Angle.PI_OVER_TWO, Math.PI, TEST_PRECISION);

        // act
        final RegionBSPTree1S tree = interval.toTree();

        // assert
        Assertions.assertFalse(tree.isFull());
        Assertions.assertFalse(tree.isEmpty());

        checkClassify(tree, RegionLocation.BOUNDARY,
                Point1S.of(Angle.PI_OVER_TWO), Point1S.PI);

        checkClassify(tree, RegionLocation.INSIDE,
                Point1S.of(0.51 * Math.PI), Point1S.of(0.75 * Math.PI),
                Point1S.of(0.99 * Math.PI));

        checkClassify(tree, RegionLocation.OUTSIDE,
                Point1S.ZERO, Point1S.of(0.25 * Math.PI),
                Point1S.of(1.25 * Math.PI), Point1S.of(1.75 * Math.PI));
    }

    @Test
    void testToTree_intervalGreaterThanPi() {
        // arrange
        final AngularInterval interval = AngularInterval.of(Math.PI, Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final RegionBSPTree1S tree = interval.toTree();

        // assert
        Assertions.assertFalse(tree.isFull());
        Assertions.assertFalse(tree.isEmpty());

        checkClassify(tree, RegionLocation.BOUNDARY,
                Point1S.of(Angle.PI_OVER_TWO), Point1S.PI);

        checkClassify(tree, RegionLocation.INSIDE,
                Point1S.ZERO, Point1S.of(0.25 * Math.PI),
                Point1S.of(1.25 * Math.PI), Point1S.of(1.75 * Math.PI));

        checkClassify(tree, RegionLocation.OUTSIDE,
                Point1S.of(0.51 * Math.PI), Point1S.of(0.75 * Math.PI),
                Point1S.of(0.99 * Math.PI));
    }

    @Test
    void testToConvex_lessThanPi() {
        // arrange
        final AngularInterval interval = AngularInterval.of(0, Angle.PI_OVER_TWO, TEST_PRECISION);

        //act
        final List<AngularInterval.Convex> result = interval.toConvex();

        // assert
        Assertions.assertEquals(1, result.size());
        checkInterval(interval, 0, Angle.PI_OVER_TWO);
    }

    @Test
    void testToConvex_equalToPi() {
        // arrange
        final AngularInterval interval = AngularInterval.of(Math.PI, Angle.TWO_PI, TEST_PRECISION);

        //act
        final List<AngularInterval.Convex> result = interval.toConvex();

        // assert
        Assertions.assertEquals(1, result.size());
        checkInterval(interval, Math.PI, Angle.TWO_PI);
    }

    @Test
    void testToConvex_overPi() {
        // arrange
        final AngularInterval interval = AngularInterval.of(Math.PI, Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final List<AngularInterval.Convex> result = interval.toConvex();

        // assert
        Assertions.assertEquals(2, result.size());
        checkInterval(result.get(0), Math.PI, 1.75 * Math.PI);
        checkInterval(result.get(1), 1.75 * Math.PI, 2.5 * Math.PI);
    }

    @Test
    void testToConvex_overPi_splitAtZero() {
        // arrange
        final AngularInterval interval = AngularInterval.of(1.25 * Math.PI, 2.75 * Math.PI, TEST_PRECISION);

        // act
        final List<AngularInterval.Convex> result = interval.toConvex();

        // assert
        Assertions.assertEquals(2, result.size());
        checkInterval(result.get(0), 1.25 * Math.PI, Angle.TWO_PI);
        checkInterval(result.get(1), Angle.TWO_PI, 2.75 * Math.PI);
    }

    @Test
    void testSplit_full() {
        // arrange
        final AngularInterval interval = AngularInterval.full();
        final CutAngle pt = CutAngles.createNegativeFacing(Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = interval.split(pt);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());

        final RegionBSPTree1S minus = split.getMinus();
        checkClassify(minus, RegionLocation.BOUNDARY, Point1S.of(Angle.PI_OVER_TWO));
        checkClassify(minus, RegionLocation.INSIDE,
                Point1S.PI, Point1S.of(-Angle.PI_OVER_TWO), Point1S.of(-0.25 * Math.PI));
        checkClassify(minus, RegionLocation.OUTSIDE,
                Point1S.ZERO, Point1S.of(0.25 * Math.PI));

        final RegionBSPTree1S plus = split.getPlus();
        checkClassify(plus, RegionLocation.BOUNDARY, Point1S.of(Angle.PI_OVER_TWO));
        checkClassify(plus, RegionLocation.INSIDE,
                Point1S.ZERO, Point1S.of(0.25 * Math.PI));
        checkClassify(plus, RegionLocation.OUTSIDE,
                Point1S.PI, Point1S.of(-Angle.PI_OVER_TWO), Point1S.of(-0.25 * Math.PI));
    }

    @Test
    void testSplit_interval_both() {
        // arrange
        final AngularInterval interval = AngularInterval.of(Angle.PI_OVER_TWO, Math.PI, TEST_PRECISION);
        final CutAngle cut = CutAngles.createNegativeFacing(0.75 * Math.PI, TEST_PRECISION);

        // act
        final Split<RegionBSPTree1S> split = interval.split(cut);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());

        final RegionBSPTree1S minus = split.getMinus();
        checkClassify(minus, RegionLocation.BOUNDARY, Point1S.of(Math.PI), cut.getPoint());
        checkClassify(minus, RegionLocation.INSIDE, Point1S.of(0.8 * Math.PI));
        checkClassify(minus, RegionLocation.OUTSIDE,
                Point1S.ZERO, Point1S.of(Angle.TWO_PI), Point1S.of(-Angle.PI_OVER_TWO),
                Point1S.of(0.7 * Math.PI));

        final RegionBSPTree1S plus = split.getPlus();
        checkClassify(plus, RegionLocation.BOUNDARY, Point1S.of(Angle.PI_OVER_TWO), cut.getPoint());
        checkClassify(plus, RegionLocation.INSIDE, Point1S.of(0.6 * Math.PI));
        checkClassify(plus, RegionLocation.OUTSIDE,
                Point1S.ZERO, Point1S.of(Angle.TWO_PI), Point1S.of(-Angle.PI_OVER_TWO),
                Point1S.of(0.8 * Math.PI));
    }

    @Test
    void testToString() {
        // arrange
        final AngularInterval interval = AngularInterval.of(1, 2, TEST_PRECISION);

        // act
        final String str = interval.toString();

        // assert
        Assertions.assertTrue(str.contains("AngularInterval"));
        Assertions.assertTrue(str.contains("min= 1.0"));
        Assertions.assertTrue(str.contains("max= 2.0"));
    }

    @Test
    void testConvex_of_doubles() {
        // act/assert
        checkInterval(AngularInterval.Convex.of(0, 1, TEST_PRECISION), 0, 1);
        checkInterval(AngularInterval.Convex.of(0, Math.PI, TEST_PRECISION), 0, Math.PI);
        checkInterval(AngularInterval.Convex.of(Math.PI + 2, 1, TEST_PRECISION), Math.PI + 2, Angle.TWO_PI + 1);
        checkInterval(AngularInterval.Convex.of(-2, -1.5, TEST_PRECISION), -2, -1.5);

        checkFull(AngularInterval.Convex.of(1, 1, TEST_PRECISION));
        checkFull(AngularInterval.Convex.of(0, 1e-11, TEST_PRECISION));
        checkFull(AngularInterval.Convex.of(0, -1e-11, TEST_PRECISION));
        checkFull(AngularInterval.Convex.of(0, Angle.TWO_PI, TEST_PRECISION));
    }

    @Test
    void testConvex_of_doubles_invalidArgs() {
        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.Convex.of(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.Convex.of(0, Math.PI + 1e-1, TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.Convex.of(Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO + 1, TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.Convex.of(0, -0.5, TEST_PRECISION));
    }

    @Test
    void testConvex_of_points() {
        // act/assert
        checkInterval(AngularInterval.Convex.of(Point1S.of(0), Point1S.of(1), TEST_PRECISION), 0, 1);
        checkInterval(AngularInterval.Convex.of(Point1S.of(0), Point1S.of(Math.PI), TEST_PRECISION),
                0, Math.PI);
        checkInterval(AngularInterval.Convex.of(Point1S.of(Math.PI + 2), Point1S.of(1), TEST_PRECISION),
                Math.PI + 2, Angle.TWO_PI + 1);
        checkInterval(AngularInterval.Convex.of(Point1S.of(-2), Point1S.of(-1.5), TEST_PRECISION), -2, -1.5);

        checkFull(AngularInterval.Convex.of(Point1S.of(1), Point1S.of(1), TEST_PRECISION));
        checkFull(AngularInterval.Convex.of(Point1S.of(0), Point1S.of(1e-11), TEST_PRECISION));
        checkFull(AngularInterval.Convex.of(Point1S.of(0), Point1S.of(-1e-11), TEST_PRECISION));
        checkFull(AngularInterval.Convex.of(Point1S.of(0), Point1S.of(Angle.TWO_PI), TEST_PRECISION));
    }

    @Test
    void testConvex_of_points_invalidArgs() {
        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.Convex.of(Point1S.of(Double.NEGATIVE_INFINITY),
                Point1S.of(Double.POSITIVE_INFINITY), TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.Convex.of(Point1S.of(0), Point1S.of(Math.PI + 1e-1), TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.Convex.of(Point1S.of(Angle.PI_OVER_TWO),
                Point1S.of(-Angle.PI_OVER_TWO + 1), TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.Convex.of(Point1S.of(0), Point1S.of(-0.5), TEST_PRECISION));
    }

    @Test
    void testConvex_of_cutAngles() {
        // arrange
        final Precision.DoubleEquivalence precisionA = Precision.doubleEquivalenceOfEpsilon(1e-3);
        final Precision.DoubleEquivalence precisionB = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final CutAngle zeroPos = CutAngles.createPositiveFacing(Point1S.ZERO, precisionA);
        final CutAngle zeroNeg = CutAngles.createNegativeFacing(Point1S.ZERO, precisionA);

        final CutAngle piPos = CutAngles.createPositiveFacing(Point1S.PI, precisionA);
        final CutAngle piNeg = CutAngles.createNegativeFacing(Point1S.PI, precisionA);

        final CutAngle almostPiPos = CutAngles.createPositiveFacing(Point1S.of(Math.PI + 5e-3), precisionB);

        // act/assert
        checkInterval(AngularInterval.Convex.of(zeroNeg, piPos), 0, Math.PI);
        checkInterval(AngularInterval.Convex.of(zeroPos, piNeg), Math.PI, Angle.TWO_PI);

        checkFull(AngularInterval.Convex.of(zeroPos, zeroNeg));
        checkFull(AngularInterval.Convex.of(zeroPos, piPos));
        checkFull(AngularInterval.Convex.of(piNeg, zeroNeg));

        checkFull(AngularInterval.Convex.of(almostPiPos, piNeg));
        checkFull(AngularInterval.Convex.of(piNeg, almostPiPos));
    }

    @Test
    void testConvex_of_cutAngles_invalidArgs() {
        // arrange
        final CutAngle pt = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);
        final CutAngle nan = CutAngles.createPositiveFacing(Point1S.NaN, TEST_PRECISION);

        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.Convex.of(pt, nan));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.Convex.of(nan, pt));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.Convex.of(nan, nan));
        Assertions.assertThrows(IllegalArgumentException.class, () -> AngularInterval.Convex.of(
                CutAngles.createNegativeFacing(1, TEST_PRECISION),
                CutAngles.createPositiveFacing(0.5, TEST_PRECISION)));
    }

    @Test
    void testConvex_toConvex() {
        // arrange
        final AngularInterval.Convex full = AngularInterval.full();
        final AngularInterval.Convex interval = AngularInterval.Convex.of(0, 1, TEST_PRECISION);

        List<AngularInterval.Convex> result;

        // act/assert
        result = full.toConvex();
        Assertions.assertEquals(1, result.size());
        Assertions.assertSame(full, result.get(0));

        result = interval.toConvex();
        Assertions.assertEquals(1, result.size());
        Assertions.assertSame(interval, result.get(0));
    }

    @Test
    void testSplitDiameter_full() {
        // arrange
        final AngularInterval.Convex full = AngularInterval.full();
        final CutAngle splitter = CutAngles.createPositiveFacing(Point1S.of(Angle.PI_OVER_TWO), TEST_PRECISION);

        // act
        final Split<AngularInterval.Convex> split = full.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());

        checkInterval(split.getMinus(), 1.5 * Math.PI, 2.5 * Math.PI);
        checkInterval(split.getPlus(), 0.5 * Math.PI, 1.5 * Math.PI);
    }

    @Test
    void testSplitDiameter_full_splitOnZero() {
        // arrange
        final AngularInterval.Convex full = AngularInterval.full();
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);

        // act
        final Split<AngularInterval.Convex> split = full.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());

        checkInterval(split.getMinus(), 0, Math.PI);
        checkInterval(split.getPlus(), Math.PI, Angle.TWO_PI);
    }

    @Test
    void testSplitDiameter_minus() {
        // arrange
        final AngularInterval.Convex interval = AngularInterval.Convex.of(0.1, Angle.PI_OVER_TWO, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);

        // act
        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());

        Assertions.assertSame(interval, split.getMinus());
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplitDiameter_plus() {
        // arrange
        final AngularInterval.Convex interval = AngularInterval.Convex.of(-0.4 * Math.PI, 0.4 * Math.PI, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.of(Angle.PI_OVER_TWO), TEST_PRECISION);

        // act
        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());

        Assertions.assertNull(split.getMinus());
        Assertions.assertSame(interval, split.getPlus());
    }

    @Test
    void testSplitDiameter_both_negativeFacingSplitter() {
        // arrange
        final AngularInterval.Convex interval = AngularInterval.Convex.of(Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.of(Math.PI), TEST_PRECISION);

        // act
        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());

        checkInterval(split.getMinus(), Math.PI, 1.5 * Math.PI);
        checkInterval(split.getPlus(), Angle.PI_OVER_TWO, Math.PI);
    }

    @Test
    void testSplitDiameter_both_positiveFacingSplitter() {
        // arrange
        final AngularInterval.Convex interval = AngularInterval.Convex.of(Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createPositiveFacing(Point1S.of(Math.PI), TEST_PRECISION);

        // act
        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());

        checkInterval(split.getMinus(), Angle.PI_OVER_TWO, Math.PI);
        checkInterval(split.getPlus(), Math.PI, 1.5 * Math.PI);
    }

    @Test
    void testSplitDiameter_both_antipodal_negativeFacingSplitter() {
        // arrange
        final AngularInterval.Convex interval = AngularInterval.Convex.of(Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);

        // act
        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());

        checkInterval(split.getMinus(), Angle.PI_OVER_TWO, Math.PI);
        checkInterval(split.getPlus(), Math.PI, 1.5 * Math.PI);
    }

    @Test
    void testSplitDiameter_both_antipodal_positiveFacingSplitter() {
        // arrange
        final AngularInterval.Convex interval = AngularInterval.Convex.of(Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createPositiveFacing(Point1S.ZERO, TEST_PRECISION);

        // act
        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());

        checkInterval(split.getMinus(), Math.PI, 1.5 * Math.PI);
        checkInterval(split.getPlus(), Angle.PI_OVER_TWO, Math.PI);
    }

    @Test
    void testSplitDiameter_splitOnBoundary_negativeFacing() {
        // arrange
        final AngularInterval.Convex interval = AngularInterval.Convex.of(Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.of(Angle.PI_OVER_TWO), TEST_PRECISION);

        // act
        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());

        Assertions.assertSame(interval, split.getMinus());
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplitDiameter_splitOnBoundary_positiveFacing() {
        // arrange
        final AngularInterval.Convex interval = AngularInterval.Convex.of(0, Math.PI, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createPositiveFacing(Point1S.of(Math.PI), TEST_PRECISION);

        // act
        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());

        Assertions.assertSame(interval, split.getMinus());
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testConvex_transform() {
        // arrange
        final AngularInterval.Convex interval = AngularInterval.Convex.of(Angle.PI_OVER_TWO, Math.PI, TEST_PRECISION);

        final Transform1S rotate = Transform1S.createRotation(Angle.PI_OVER_TWO);
        final Transform1S invert = Transform1S.createNegation().rotate(Angle.PI_OVER_TWO);

        // act/assert
        checkInterval(interval.transform(rotate), Math.PI, 1.5 * Math.PI);
        checkInterval(interval.transform(invert), -0.5 * Math.PI, 0.0);
    }

    private static void checkFull(final AngularInterval interval) {
        Assertions.assertTrue(interval.isFull());
        Assertions.assertFalse(interval.isEmpty());

        Assertions.assertNull(interval.getMinBoundary());
        Assertions.assertEquals(0, interval.getMin(), TEST_EPS);
        Assertions.assertNull(interval.getMaxBoundary());
        Assertions.assertEquals(Angle.TWO_PI, interval.getMax(), TEST_EPS);

        Assertions.assertNull(interval.getCentroid());
        Assertions.assertNull(interval.getMidPoint());

        Assertions.assertEquals(Angle.TWO_PI, interval.getSize(), TEST_EPS);
        Assertions.assertEquals(0, interval.getBoundarySize(), TEST_EPS);

        checkClassify(interval, RegionLocation.INSIDE, Point1S.ZERO, Point1S.of(Math.PI));
    }

    private static void checkInterval(final AngularInterval interval, final double min, final double max) {

        Assertions.assertFalse(interval.isFull());
        Assertions.assertFalse(interval.isEmpty());

        final CutAngle minBoundary = interval.getMinBoundary();
        Assertions.assertEquals(min, minBoundary.getAzimuth(), TEST_EPS);
        Assertions.assertFalse(minBoundary.isPositiveFacing());

        final CutAngle maxBoundary = interval.getMaxBoundary();
        Assertions.assertEquals(max, maxBoundary.getAzimuth(), TEST_EPS);
        Assertions.assertTrue(maxBoundary.isPositiveFacing());

        Assertions.assertEquals(min, interval.getMin(), TEST_EPS);
        Assertions.assertEquals(max, interval.getMax(), TEST_EPS);

        Assertions.assertEquals(0.5 * (max + min), interval.getMidPoint().getAzimuth(), TEST_EPS);
        Assertions.assertSame(interval.getMidPoint(), interval.getCentroid());

        Assertions.assertEquals(0, interval.getBoundarySize(), TEST_EPS);
        Assertions.assertEquals(max - min, interval.getSize(), TEST_EPS);

        checkClassify(interval, RegionLocation.INSIDE, interval.getMidPoint());
        checkClassify(interval, RegionLocation.BOUNDARY,
                interval.getMinBoundary().getPoint(), interval.getMaxBoundary().getPoint());
        checkClassify(interval, RegionLocation.OUTSIDE, Point1S.of(interval.getMidPoint().getAzimuth() + Math.PI));
    }

    private static void checkClassify(final Region<Point1S> region, final RegionLocation loc, final Point1S... pts) {
        for (final Point1S pt : pts) {
            Assertions.assertEquals(loc, region.classify(pt), "Unexpected location for point " + pt);
        }
    }

    @Test
    void testOf_doubles_invalidArgs_1_oe() {
        try {
    AngularInterval.of(Double.NEGATIVE_INFINITY, 0, TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_doubles_invalidArgs_2_oe() {
        try {
    AngularInterval.of(0, Double.POSITIVE_INFINITY, TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_doubles_invalidArgs_3_oe() {
        try {
    AngularInterval.of(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_doubles_invalidArgs_4_oe() {
        try {
    AngularInterval.of(Double.NaN, 0, TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_doubles_invalidArgs_5_oe() {
        try {
    AngularInterval.of(0, Double.NaN, TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_doubles_invalidArgs_6_oe() {
        try {
    AngularInterval.of(Double.NaN, Double.NaN, TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_points_invalidArgs_1_oe() {
        try {
    AngularInterval.of(Point1S.of(Double.NEGATIVE_INFINITY), Point1S.ZERO, TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_points_invalidArgs_2_oe() {
        try {
    AngularInterval.of(Point1S.ZERO, Point1S.of(Double.POSITIVE_INFINITY), TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_points_invalidArgs_3_oe() {
        try {
    AngularInterval.of(Point1S.of(Double.POSITIVE_INFINITY), Point1S.of(Double.NEGATIVE_INFINITY), TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_points_invalidArgs_4_oe() {
        try {
    AngularInterval.of(Point1S.NaN, Point1S.ZERO, TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_points_invalidArgs_5_oe() {
        try {
    AngularInterval.of(Point1S.ZERO, Point1S.NaN, TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_points_invalidArgs_6_oe() {
        try {
    AngularInterval.of(Point1S.NaN, Point1S.NaN, TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_orientedPoints_invalidArgs_1_oe() {
        final CutAngle pt = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);
        final CutAngle nan = CutAngles.createPositiveFacing(Point1S.NaN, TEST_PRECISION);

        try {
    AngularInterval.of(pt, nan);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_orientedPoints_invalidArgs_2_oe() {
        final CutAngle pt = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);
        final CutAngle nan = CutAngles.createPositiveFacing(Point1S.NaN, TEST_PRECISION);

        try {
    AngularInterval.of(nan, pt);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_orientedPoints_invalidArgs_3_oe() {
        final CutAngle pt = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);
        final CutAngle nan = CutAngles.createPositiveFacing(Point1S.NaN, TEST_PRECISION);

        try {
    AngularInterval.of(nan, nan);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testProject_full_1_oe() {
        final AngularInterval interval = AngularInterval.full();

        Assertions.assertNull(interval.project(Point1S.ZERO));
    }

    @Test
    void testProject_full_2_oe() {
        final AngularInterval interval = AngularInterval.full();

        Assertions.assertNull(interval.project(Point1S.PI));
    }

    @Test
    void testProject_1_oe() {
        final AngularInterval interval = AngularInterval.of(1, 2, TEST_PRECISION);

        Assertions.assertEquals(1, interval.project(Point1S.ZERO).getAzimuth(), TEST_EPS);
    }

    @Test
    void testProject_2_oe() {
        final AngularInterval interval = AngularInterval.of(1, 2, TEST_PRECISION);

        Assertions.assertEquals(1, interval.project(Point1S.of(1)).getAzimuth(), TEST_EPS);
    }

    @Test
    void testProject_3_oe() {
        final AngularInterval interval = AngularInterval.of(1, 2, TEST_PRECISION);

        Assertions.assertEquals(1, interval.project(Point1S.of(1.5)).getAzimuth(), TEST_EPS);
    }

    @Test
    void testProject_4_oe() {
        final AngularInterval interval = AngularInterval.of(1, 2, TEST_PRECISION);


        Assertions.assertEquals(2, interval.project(Point1S.of(2)).getAzimuth(), TEST_EPS);
    }

    @Test
    void testProject_5_oe() {
        final AngularInterval interval = AngularInterval.of(1, 2, TEST_PRECISION);


        Assertions.assertEquals(2, interval.project(Point1S.PI).getAzimuth(), TEST_EPS);
    }

    @Test
    void testProject_6_oe() {
        final AngularInterval interval = AngularInterval.of(1, 2, TEST_PRECISION);


        Assertions.assertEquals(2, interval.project(Point1S.of(1.4 + Math.PI)).getAzimuth(), TEST_EPS);
    }

    @Test
    void testProject_7_oe() {
        final AngularInterval interval = AngularInterval.of(1, 2, TEST_PRECISION);



        Assertions.assertEquals(1, interval.project(Point1S.of(1.5 + Math.PI)).getAzimuth(), TEST_EPS);
    }

    @Test
    void testProject_8_oe() {
        final AngularInterval interval = AngularInterval.of(1, 2, TEST_PRECISION);



        Assertions.assertEquals(1, interval.project(Point1S.of(1.6 + Math.PI)).getAzimuth(), TEST_EPS);
    }

    @Test
    void testWrapsZero_1_oe() {
        Assertions.assertFalse(AngularInterval.full().wrapsZero());
    }

    @Test
    void testWrapsZero_2_oe() {
        Assertions.assertFalse(AngularInterval.of(0, Angle.PI_OVER_TWO, TEST_PRECISION).wrapsZero());
    }

    @Test
    void testWrapsZero_3_oe() {
        Assertions.assertFalse(AngularInterval.of(Angle.PI_OVER_TWO, Math.PI, TEST_PRECISION).wrapsZero());
    }

    @Test
    void testWrapsZero_4_oe() {
        Assertions.assertFalse(AngularInterval.of(Math.PI, 1.5 * Math.PI, TEST_PRECISION).wrapsZero());
    }

    @Test
    void testWrapsZero_5_oe() {
        Assertions.assertFalse(AngularInterval.of(1.5 * Math.PI, Angle.TWO_PI - 1e-5, TEST_PRECISION).wrapsZero());
    }

    @Test
    void testWrapsZero_6_oe() {

        Assertions.assertTrue(AngularInterval.of(1.5 * Math.PI, Angle.TWO_PI, TEST_PRECISION).wrapsZero());
    }

    @Test
    void testWrapsZero_7_oe() {

        Assertions.assertTrue(AngularInterval.of(1.5 * Math.PI, 2.5 * Math.PI, TEST_PRECISION).wrapsZero());
    }

    @Test
    void testWrapsZero_8_oe() {

        Assertions.assertTrue(AngularInterval.of(-2.5 * Math.PI, -1.5 * Math.PI, TEST_PRECISION).wrapsZero());
    }

    @Test
    void testToTree_full_1_oe() {
        final AngularInterval interval = AngularInterval.full();

        final RegionBSPTree1S tree = interval.toTree();

        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testToTree_full_2_oe() {
        final AngularInterval interval = AngularInterval.full();

        final RegionBSPTree1S tree = interval.toTree();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testToTree_intervalEqualToPi_1_oe() {
        final AngularInterval interval = AngularInterval.of(0.0, Math.PI, TEST_PRECISION);

        final RegionBSPTree1S tree = interval.toTree();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testToTree_intervalEqualToPi_2_oe() {
        final AngularInterval interval = AngularInterval.of(0.0, Math.PI, TEST_PRECISION);

        final RegionBSPTree1S tree = interval.toTree();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testToTree_intervalLessThanPi_1_oe() {
        final AngularInterval interval = AngularInterval.of(Angle.PI_OVER_TWO, Math.PI, TEST_PRECISION);

        final RegionBSPTree1S tree = interval.toTree();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testToTree_intervalLessThanPi_2_oe() {
        final AngularInterval interval = AngularInterval.of(Angle.PI_OVER_TWO, Math.PI, TEST_PRECISION);

        final RegionBSPTree1S tree = interval.toTree();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testToTree_intervalGreaterThanPi_1_oe() {
        final AngularInterval interval = AngularInterval.of(Math.PI, Angle.PI_OVER_TWO, TEST_PRECISION);

        final RegionBSPTree1S tree = interval.toTree();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testToTree_intervalGreaterThanPi_2_oe() {
        final AngularInterval interval = AngularInterval.of(Math.PI, Angle.PI_OVER_TWO, TEST_PRECISION);

        final RegionBSPTree1S tree = interval.toTree();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testToConvex_lessThanPi_1_oe() {
        final AngularInterval interval = AngularInterval.of(0, Angle.PI_OVER_TWO, TEST_PRECISION);

        final List<AngularInterval.Convex> result = interval.toConvex();

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testToConvex_equalToPi_1_oe() {
        final AngularInterval interval = AngularInterval.of(Math.PI, Angle.TWO_PI, TEST_PRECISION);

        final List<AngularInterval.Convex> result = interval.toConvex();

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testToConvex_overPi_1_oe() {
        final AngularInterval interval = AngularInterval.of(Math.PI, Angle.PI_OVER_TWO, TEST_PRECISION);

        final List<AngularInterval.Convex> result = interval.toConvex();

        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testToConvex_overPi_splitAtZero_1_oe() {
        final AngularInterval interval = AngularInterval.of(1.25 * Math.PI, 2.75 * Math.PI, TEST_PRECISION);

        final List<AngularInterval.Convex> result = interval.toConvex();

        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testSplit_full_1_oe() {
        final AngularInterval interval = AngularInterval.full();
        final CutAngle pt = CutAngles.createNegativeFacing(Angle.PI_OVER_TWO, TEST_PRECISION);

        final Split<RegionBSPTree1S> split = interval.split(pt);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_interval_both_1_oe() {
        final AngularInterval interval = AngularInterval.of(Angle.PI_OVER_TWO, Math.PI, TEST_PRECISION);
        final CutAngle cut = CutAngles.createNegativeFacing(0.75 * Math.PI, TEST_PRECISION);

        final Split<RegionBSPTree1S> split = interval.split(cut);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testToString_1_oe() {
        final AngularInterval interval = AngularInterval.of(1, 2, TEST_PRECISION);

        final String str = interval.toString();

        Assertions.assertTrue(str.contains("AngularInterval"));
    }

    @Test
    void testToString_2_oe() {
        final AngularInterval interval = AngularInterval.of(1, 2, TEST_PRECISION);

        final String str = interval.toString();

        Assertions.assertTrue(str.contains("min= 1.0"));
    }

    @Test
    void testToString_3_oe() {
        final AngularInterval interval = AngularInterval.of(1, 2, TEST_PRECISION);

        final String str = interval.toString();

        Assertions.assertTrue(str.contains("max= 2.0"));
    }

    @Test
    void testConvex_of_doubles_invalidArgs_1_oe() {
        try {
    AngularInterval.Convex.of(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_doubles_invalidArgs_2_oe() {
        try {
    AngularInterval.Convex.of(0, Math.PI + 1e-1, TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_doubles_invalidArgs_3_oe() {
        try {
    AngularInterval.Convex.of(Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO + 1, TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_doubles_invalidArgs_4_oe() {
        try {
    AngularInterval.Convex.of(0, -0.5, TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_points_invalidArgs_1_oe() {
        try {
    AngularInterval.Convex.of(Point1S.of(Double.NEGATIVE_INFINITY), Point1S.of(Double.POSITIVE_INFINITY), TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_points_invalidArgs_2_oe() {
        try {
    AngularInterval.Convex.of(Point1S.of(0), Point1S.of(Math.PI + 1e-1), TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_points_invalidArgs_3_oe() {
        try {
    AngularInterval.Convex.of(Point1S.of(Angle.PI_OVER_TWO), Point1S.of(-Angle.PI_OVER_TWO + 1), TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_points_invalidArgs_4_oe() {
        try {
    AngularInterval.Convex.of(Point1S.of(0), Point1S.of(-0.5), TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_cutAngles_invalidArgs_1_oe() {
        final CutAngle pt = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);
        final CutAngle nan = CutAngles.createPositiveFacing(Point1S.NaN, TEST_PRECISION);

        try {
    AngularInterval.Convex.of(pt, nan);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_cutAngles_invalidArgs_2_oe() {
        final CutAngle pt = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);
        final CutAngle nan = CutAngles.createPositiveFacing(Point1S.NaN, TEST_PRECISION);

        try {
    AngularInterval.Convex.of(nan, pt);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_cutAngles_invalidArgs_3_oe() {
        final CutAngle pt = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);
        final CutAngle nan = CutAngles.createPositiveFacing(Point1S.NaN, TEST_PRECISION);

        try {
    AngularInterval.Convex.of(nan, nan);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_cutAngles_invalidArgs_4_oe() {
        final CutAngle pt = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);
        final CutAngle nan = CutAngles.createPositiveFacing(Point1S.NaN, TEST_PRECISION);

        try {
    AngularInterval.Convex.of( CutAngles.createNegativeFacing(1, TEST_PRECISION), CutAngles.createPositiveFacing(0.5, TEST_PRECISION));
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_toConvex_1_oe() {
        final AngularInterval.Convex full = AngularInterval.full();
        final AngularInterval.Convex interval = AngularInterval.Convex.of(0, 1, TEST_PRECISION);

        List<AngularInterval.Convex> result;

        result = full.toConvex();
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testConvex_toConvex_2_oe() {
        final AngularInterval.Convex full = AngularInterval.full();
        final AngularInterval.Convex interval = AngularInterval.Convex.of(0, 1, TEST_PRECISION);

        List<AngularInterval.Convex> result;

        result = full.toConvex();
        Assertions.assertSame(full, result.get(0));
    }

    @Test
    void testConvex_toConvex_3_oe() {
        final AngularInterval.Convex full = AngularInterval.full();
        final AngularInterval.Convex interval = AngularInterval.Convex.of(0, 1, TEST_PRECISION);

        List<AngularInterval.Convex> result;

        result = full.toConvex();

        result = interval.toConvex();
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testConvex_toConvex_4_oe() {
        final AngularInterval.Convex full = AngularInterval.full();
        final AngularInterval.Convex interval = AngularInterval.Convex.of(0, 1, TEST_PRECISION);

        List<AngularInterval.Convex> result;

        result = full.toConvex();

        result = interval.toConvex();
        Assertions.assertSame(interval, result.get(0));
    }

    @Test
    void testSplitDiameter_full_1_oe() {
        final AngularInterval.Convex full = AngularInterval.full();
        final CutAngle splitter = CutAngles.createPositiveFacing(Point1S.of(Angle.PI_OVER_TWO), TEST_PRECISION);

        final Split<AngularInterval.Convex> split = full.splitDiameter(splitter);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplitDiameter_full_splitOnZero_1_oe() {
        final AngularInterval.Convex full = AngularInterval.full();
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);

        final Split<AngularInterval.Convex> split = full.splitDiameter(splitter);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplitDiameter_minus_1_oe() {
        final AngularInterval.Convex interval = AngularInterval.Convex.of(0.1, Angle.PI_OVER_TWO, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);

        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);

        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplitDiameter_minus_2_oe() {
        final AngularInterval.Convex interval = AngularInterval.Convex.of(0.1, Angle.PI_OVER_TWO, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);

        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);


        Assertions.assertSame(interval, split.getMinus());
    }

    @Test
    void testSplitDiameter_minus_3_oe() {
        final AngularInterval.Convex interval = AngularInterval.Convex.of(0.1, Angle.PI_OVER_TWO, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);

        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);


        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplitDiameter_plus_1_oe() {
        final AngularInterval.Convex interval = AngularInterval.Convex.of(-0.4 * Math.PI, 0.4 * Math.PI, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.of(Angle.PI_OVER_TWO), TEST_PRECISION);

        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);

        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplitDiameter_plus_2_oe() {
        final AngularInterval.Convex interval = AngularInterval.Convex.of(-0.4 * Math.PI, 0.4 * Math.PI, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.of(Angle.PI_OVER_TWO), TEST_PRECISION);

        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);


        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplitDiameter_plus_3_oe() {
        final AngularInterval.Convex interval = AngularInterval.Convex.of(-0.4 * Math.PI, 0.4 * Math.PI, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.of(Angle.PI_OVER_TWO), TEST_PRECISION);

        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);


        Assertions.assertSame(interval, split.getPlus());
    }

    @Test
    void testSplitDiameter_both_negativeFacingSplitter_1_oe() {
        final AngularInterval.Convex interval = AngularInterval.Convex.of(Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.of(Math.PI), TEST_PRECISION);

        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplitDiameter_both_positiveFacingSplitter_1_oe() {
        final AngularInterval.Convex interval = AngularInterval.Convex.of(Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createPositiveFacing(Point1S.of(Math.PI), TEST_PRECISION);

        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplitDiameter_both_antipodal_negativeFacingSplitter_1_oe() {
        final AngularInterval.Convex interval = AngularInterval.Convex.of(Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);

        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplitDiameter_both_antipodal_positiveFacingSplitter_1_oe() {
        final AngularInterval.Convex interval = AngularInterval.Convex.of(Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createPositiveFacing(Point1S.ZERO, TEST_PRECISION);

        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplitDiameter_splitOnBoundary_negativeFacing_1_oe() {
        final AngularInterval.Convex interval = AngularInterval.Convex.of(Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.of(Angle.PI_OVER_TWO), TEST_PRECISION);

        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);

        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplitDiameter_splitOnBoundary_negativeFacing_2_oe() {
        final AngularInterval.Convex interval = AngularInterval.Convex.of(Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.of(Angle.PI_OVER_TWO), TEST_PRECISION);

        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);


        Assertions.assertSame(interval, split.getMinus());
    }

    @Test
    void testSplitDiameter_splitOnBoundary_negativeFacing_3_oe() {
        final AngularInterval.Convex interval = AngularInterval.Convex.of(Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createNegativeFacing(Point1S.of(Angle.PI_OVER_TWO), TEST_PRECISION);

        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);


        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplitDiameter_splitOnBoundary_positiveFacing_1_oe() {
        final AngularInterval.Convex interval = AngularInterval.Convex.of(0, Math.PI, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createPositiveFacing(Point1S.of(Math.PI), TEST_PRECISION);

        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);

        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplitDiameter_splitOnBoundary_positiveFacing_2_oe() {
        final AngularInterval.Convex interval = AngularInterval.Convex.of(0, Math.PI, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createPositiveFacing(Point1S.of(Math.PI), TEST_PRECISION);

        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);


        Assertions.assertSame(interval, split.getMinus());
    }

    @Test
    void testSplitDiameter_splitOnBoundary_positiveFacing_3_oe() {
        final AngularInterval.Convex interval = AngularInterval.Convex.of(0, Math.PI, TEST_PRECISION);
        final CutAngle splitter = CutAngles.createPositiveFacing(Point1S.of(Math.PI), TEST_PRECISION);

        final Split<AngularInterval.Convex> split = interval.splitDiameter(splitter);


        Assertions.assertNull(split.getPlus());
    }

}
