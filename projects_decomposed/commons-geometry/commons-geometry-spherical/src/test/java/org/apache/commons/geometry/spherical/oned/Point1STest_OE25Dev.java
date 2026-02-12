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

import java.util.Comparator;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Point1STest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    @Test
    void testOf() {
        // act/assert
        checkPoint(Point1S.of(0), 0, 0);
        checkPoint(Point1S.of(1), 1, 1);
        checkPoint(Point1S.of(-1), -1, Angle.TWO_PI - 1);

        checkPoint(Point1S.of(Angle.Deg.of(90)), Angle.PI_OVER_TWO, Angle.PI_OVER_TWO);
        checkPoint(Point1S.of(Angle.Turn.of(0.5)), Math.PI, Math.PI);
        checkPoint(Point1S.of(-Angle.PI_OVER_TWO), -Angle.PI_OVER_TWO, 1.5 * Math.PI);

        final double base = Angle.PI_OVER_TWO;
        for (int k = -3; k <= 3; ++k) {
            final double az = base + (k * Angle.TWO_PI);
            checkPoint(Point1S.of(az), az, base);
        }
    }

    @Test
    void testFrom_vector() {
        // act/assert
        checkPoint(Point1S.from(Vector2D.of(2, 0)), 0.0);
        checkPoint(Point1S.from(Vector2D.of(0, 0.1)), Angle.PI_OVER_TWO);
        checkPoint(Point1S.from(Vector2D.of(-0.5, 0)), Math.PI);
        checkPoint(Point1S.from(Vector2D.of(0, -100)), 1.5 * Math.PI);
    }

    @Test
    void testFrom_polar() {
        // act/assert
        checkPoint(Point1S.from(PolarCoordinates.of(100, 0)), 0.0);
        checkPoint(Point1S.from(PolarCoordinates.of(1, Angle.PI_OVER_TWO)), Angle.PI_OVER_TWO);
        checkPoint(Point1S.from(PolarCoordinates.of(0.5, Math.PI)), Math.PI);
        checkPoint(Point1S.from(PolarCoordinates.of(1e-4, -Angle.PI_OVER_TWO)), 1.5 * Math.PI);
    }

    @Test
    void testFrom_polar_invalidAzimuths() {
        // act/assert
        checkPoint(Point1S.from(PolarCoordinates.of(100, Double.POSITIVE_INFINITY)), Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        checkPoint(Point1S.from(PolarCoordinates.of(100, Double.NEGATIVE_INFINITY)), Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        checkPoint(Point1S.from(PolarCoordinates.of(100, Double.NaN)), Double.NaN, Double.NaN);
    }

    @Test
    void testAbove() {
        // arrange
        final Point1S p1 = Point1S.ZERO;
        final Point1S p2 = Point1S.of(Angle.Deg.of(90));
        final Point1S p3 = Point1S.PI;
        final Point1S p4 = Point1S.of(Angle.Deg.of(-90));
        final Point1S p5 = Point1S.of(Angle.TWO_PI);

        // act/assert
        checkPoint(p1.above(p1), 0);
        checkPoint(p2.above(p1), Angle.PI_OVER_TWO);
        checkPoint(p3.above(p1), Math.PI);
        checkPoint(p4.above(p1), 1.5 * Math.PI);
        checkPoint(p5.above(p1), 0);

        checkPoint(p1.above(p3), Angle.TWO_PI);
        checkPoint(p2.above(p3), 2.5 * Math.PI);
        checkPoint(p3.above(p3), Math.PI);
        checkPoint(p4.above(p3), 1.5 * Math.PI);
        checkPoint(p5.above(p3), Angle.TWO_PI);
    }

    @Test
    void testParse() {
        // act/assert
        checkPoint(Point1S.parse("(0)"), 0.0);
        checkPoint(Point1S.parse("(1)"), 1.0);
    }

    private static void checkPoint(final Point1S pt, final double az) {
        checkPoint(pt, az, Angle.Rad.WITHIN_0_AND_2PI.applyAsDouble(az));
    }

    private static void checkPoint(final Point1S pt, final double az, final double normAz) {
        Assertions.assertEquals(az, pt.getAzimuth(), TEST_EPS);
        Assertions.assertEquals(normAz, pt.getNormalizedAzimuth(), TEST_EPS);

        Assertions.assertEquals(1, pt.getDimension());

        Assertions.assertEquals(Double.isFinite(az), pt.isFinite());
        Assertions.assertEquals(Double.isInfinite(az), pt.isInfinite());

        final Vector2D vec = pt.getVector();
        if (pt.isFinite()) {
            Assertions.assertEquals(1, vec.norm(), TEST_EPS);
            Assertions.assertEquals(normAz, PolarCoordinates.fromCartesian(vec).getAzimuth(), TEST_EPS);
        } else {
            Assertions.assertNull(vec);
        }
    }

    @Test
    void testAbove_nonFinite_1_oe() {
        // act/assert
        try {
    Point1S.of(Double.POSITIVE_INFINITY).above(Point1S.ZERO);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAbove_nonFinite_2_oe() {
        // act/assert
        // removed other assertion
        try {
    Point1S.of(Double.NEGATIVE_INFINITY).above(Point1S.ZERO);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAbove_nonFinite_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    Point1S.of(Double.NaN).above(Point1S.ZERO);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testParse_failure_1_oe() {
        // act/assert
        try {
    Point1S.parse("abc");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
