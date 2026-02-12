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
        // act/assert
        try {
    AngularInterval.of(Double.NEGATIVE_INFINITY, 0, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_doubles_invalidArgs_2_oe() {
        // act/assert
        // removed other assertion
        try {
    AngularInterval.of(0, Double.POSITIVE_INFINITY, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_doubles_invalidArgs_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    AngularInterval.of(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_doubles_invalidArgs_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    AngularInterval.of(Double.NaN, 0, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_doubles_invalidArgs_5_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    AngularInterval.of(0, Double.NaN, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_doubles_invalidArgs_6_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    AngularInterval.of(Double.NaN, Double.NaN, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_points_invalidArgs_1_oe() {
        // act/assert
        try {
    AngularInterval.of(Point1S.of(Double.NEGATIVE_INFINITY), Point1S.ZERO, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_points_invalidArgs_2_oe() {
        // act/assert
        // removed other assertion
        try {
    AngularInterval.of(Point1S.ZERO, Point1S.of(Double.POSITIVE_INFINITY), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_points_invalidArgs_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    AngularInterval.of(Point1S.of(Double.POSITIVE_INFINITY), Point1S.of(Double.NEGATIVE_INFINITY), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_points_invalidArgs_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    AngularInterval.of(Point1S.NaN, Point1S.ZERO, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_points_invalidArgs_5_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    AngularInterval.of(Point1S.ZERO, Point1S.NaN, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_points_invalidArgs_6_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    AngularInterval.of(Point1S.NaN, Point1S.NaN, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_orientedPoints_invalidArgs_1_oe() {
        // arrange
        final CutAngle pt = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);
        final CutAngle nan = CutAngles.createPositiveFacing(Point1S.NaN, TEST_PRECISION);

        // act/assert
        try {
    AngularInterval.of(pt, nan);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_orientedPoints_invalidArgs_2_oe() {
        // arrange
        final CutAngle pt = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);
        final CutAngle nan = CutAngles.createPositiveFacing(Point1S.NaN, TEST_PRECISION);

        // act/assert
        // removed other assertion
        try {
    AngularInterval.of(nan, pt);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_orientedPoints_invalidArgs_3_oe() {
        // arrange
        final CutAngle pt = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);
        final CutAngle nan = CutAngles.createPositiveFacing(Point1S.NaN, TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    AngularInterval.of(nan, nan);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_doubles_invalidArgs_1_oe() {
        // act/assert
        try {
    AngularInterval.Convex.of(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_doubles_invalidArgs_2_oe() {
        // act/assert
        // removed other assertion
        try {
    AngularInterval.Convex.of(0, Math.PI + 1e-1, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_doubles_invalidArgs_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    AngularInterval.Convex.of(Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO + 1, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_doubles_invalidArgs_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    AngularInterval.Convex.of(0, -0.5, TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_points_invalidArgs_1_oe() {
        // act/assert
        try {
    AngularInterval.Convex.of(Point1S.of(Double.NEGATIVE_INFINITY), Point1S.of(Double.POSITIVE_INFINITY), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_points_invalidArgs_2_oe() {
        // act/assert
        // removed other assertion
        try {
    AngularInterval.Convex.of(Point1S.of(0), Point1S.of(Math.PI + 1e-1), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_points_invalidArgs_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    AngularInterval.Convex.of(Point1S.of(Angle.PI_OVER_TWO), Point1S.of(-Angle.PI_OVER_TWO + 1), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_points_invalidArgs_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    AngularInterval.Convex.of(Point1S.of(0), Point1S.of(-0.5), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_cutAngles_invalidArgs_1_oe() {
        // arrange
        final CutAngle pt = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);
        final CutAngle nan = CutAngles.createPositiveFacing(Point1S.NaN, TEST_PRECISION);

        // act/assert
        try {
    AngularInterval.Convex.of(pt, nan);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_cutAngles_invalidArgs_2_oe() {
        // arrange
        final CutAngle pt = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);
        final CutAngle nan = CutAngles.createPositiveFacing(Point1S.NaN, TEST_PRECISION);

        // act/assert
        // removed other assertion
        try {
    AngularInterval.Convex.of(nan, pt);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_cutAngles_invalidArgs_3_oe() {
        // arrange
        final CutAngle pt = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);
        final CutAngle nan = CutAngles.createPositiveFacing(Point1S.NaN, TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    AngularInterval.Convex.of(nan, nan);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConvex_of_cutAngles_invalidArgs_4_oe() {
        // arrange
        final CutAngle pt = CutAngles.createNegativeFacing(Point1S.ZERO, TEST_PRECISION);
        final CutAngle nan = CutAngles.createPositiveFacing(Point1S.NaN, TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    AngularInterval.Convex.of( CutAngles.createNegativeFacing(1, TEST_PRECISION), CutAngles.createPositiveFacing(0.5, TEST_PRECISION));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
