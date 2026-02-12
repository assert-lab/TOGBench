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
package org.apache.commons.geometry.euclidean.threed;

import java.util.regex.Pattern;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.numbers.angle.Angle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SphericalCoordinatesTest_OE25Dev {

    private static final double EPS = 1e-10;

    private static final double THREE_PI_OVER_TWO = 3 * Math.PI / 2;

    private static final double QUARTER_PI = 0.25 * Math.PI;
    private static final double MINUS_QUARTER_PI = -0.25 * Math.PI;
    private static final double THREE_QUARTER_PI = 0.75 * Math.PI;
    private static final double MINUS_THREE_QUARTER_PI = -0.75 * Math.PI;

    @Test
    void testOf() {
        // act/assert
        checkSpherical(SphericalCoordinates.of(0, 0, 0), 0, 0, 0);
        checkSpherical(SphericalCoordinates.of(0.1, 0.2, 0.3), 0.1, 0.2, 0.3);

        checkSpherical(SphericalCoordinates.of(1, Angle.PI_OVER_TWO, Math.PI),
                1, Angle.PI_OVER_TWO, Math.PI);
        checkSpherical(SphericalCoordinates.of(1, -Angle.PI_OVER_TWO, Angle.PI_OVER_TWO),
                1, THREE_PI_OVER_TWO, Angle.PI_OVER_TWO);
    }

    @Test
    void testOf_normalizesAzimuthAngle() {
        // act/assert
        checkSpherical(SphericalCoordinates.of(2, Angle.TWO_PI, 0), 2, 0, 0);
        checkSpherical(SphericalCoordinates.of(2, Angle.PI_OVER_TWO + Angle.TWO_PI, 0), 2, Angle.PI_OVER_TWO, 0);
        checkSpherical(SphericalCoordinates.of(2, -Math.PI, 0), 2, Math.PI, 0);
        checkSpherical(SphericalCoordinates.of(2, THREE_PI_OVER_TWO, 0), 2, THREE_PI_OVER_TWO, 0);
    }

    @Test
    void testOf_normalizesPolarAngle() {
        // act/assert
        checkSpherical(SphericalCoordinates.of(1, 0, 0), 1, 0, 0);

        checkSpherical(SphericalCoordinates.of(1, 0, QUARTER_PI), 1, 0, QUARTER_PI);
        checkSpherical(SphericalCoordinates.of(1, 0, MINUS_QUARTER_PI), 1, 0, QUARTER_PI);

        checkSpherical(SphericalCoordinates.of(1, 0, Angle.PI_OVER_TWO), 1, 0, Angle.PI_OVER_TWO);
        checkSpherical(SphericalCoordinates.of(1, 0, -Angle.PI_OVER_TWO), 1, 0, Angle.PI_OVER_TWO);

        checkSpherical(SphericalCoordinates.of(1, 0, THREE_QUARTER_PI), 1, 0, THREE_QUARTER_PI);
        checkSpherical(SphericalCoordinates.of(1, 0, MINUS_THREE_QUARTER_PI), 1, 0, THREE_QUARTER_PI);

        checkSpherical(SphericalCoordinates.of(1, 0, Angle.TWO_PI), 1, 0, 0);
        checkSpherical(SphericalCoordinates.of(1, 0, -Angle.TWO_PI), 1, 0, 0);
    }

    @Test
    void testOf_angleWrapAround() {
        // act/assert
        checkOfWithAngleWrapAround(1, 0, 0);
        checkOfWithAngleWrapAround(1, QUARTER_PI, QUARTER_PI);
        checkOfWithAngleWrapAround(1, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO);
        checkOfWithAngleWrapAround(1, THREE_QUARTER_PI, THREE_QUARTER_PI);
        checkOfWithAngleWrapAround(1, Math.PI, Math.PI);
    }

    private void checkOfWithAngleWrapAround(final double radius, final double azimuth, final double polar) {
        for (int i = -4; i <= 4; ++i) {
            checkSpherical(
                    SphericalCoordinates.of(radius, azimuth + (i * Angle.TWO_PI), polar + (-i * Angle.TWO_PI)),
                    radius, azimuth, polar);
        }
    }

    @Test
    void testOf_negativeRadius() {
        // act/assert
        checkSpherical(SphericalCoordinates.of(-2, 0, 0), 2, Math.PI, Math.PI);
        checkSpherical(SphericalCoordinates.of(-2, Math.PI, Math.PI), 2, 0, 0);

        checkSpherical(SphericalCoordinates.of(-3, Angle.PI_OVER_TWO, QUARTER_PI), 3, THREE_PI_OVER_TWO, THREE_QUARTER_PI);
        checkSpherical(SphericalCoordinates.of(-3, -Angle.PI_OVER_TWO, THREE_QUARTER_PI), 3, Angle.PI_OVER_TWO, QUARTER_PI);

        checkSpherical(SphericalCoordinates.of(-4, QUARTER_PI, Angle.PI_OVER_TWO), 4, Math.PI + QUARTER_PI, Angle.PI_OVER_TWO);
        checkSpherical(SphericalCoordinates.of(-4, MINUS_THREE_QUARTER_PI, Angle.PI_OVER_TWO), 4, QUARTER_PI, Angle.PI_OVER_TWO);
    }

    @Test
    void testOf_NaNAndInfinite() {
        // act/assert
        checkSpherical(SphericalCoordinates.of(Double.NaN, Double.NaN, Double.NaN),
                Double.NaN, Double.NaN, Double.NaN);
        checkSpherical(SphericalCoordinates.of(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY),
                Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        checkSpherical(SphericalCoordinates.of(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY),
                Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    }

    @Test
    void testFromCartesian_coordinates() {
        // arrange
        final double sqrt3 = Math.sqrt(3);

        // act/assert
        checkSpherical(SphericalCoordinates.fromCartesian(0, 0, 0), 0, 0, 0);

        checkSpherical(SphericalCoordinates.fromCartesian(0.1, 0, 0), 0.1, 0, Angle.PI_OVER_TWO);
        checkSpherical(SphericalCoordinates.fromCartesian(-0.1, 0, 0), 0.1, Math.PI, Angle.PI_OVER_TWO);

        checkSpherical(SphericalCoordinates.fromCartesian(0, 0.1, 0), 0.1, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO);
        checkSpherical(SphericalCoordinates.fromCartesian(0, -0.1, 0), 0.1, THREE_PI_OVER_TWO, Angle.PI_OVER_TWO);

        checkSpherical(SphericalCoordinates.fromCartesian(0, 0, 0.1), 0.1, 0, 0);
        checkSpherical(SphericalCoordinates.fromCartesian(0, 0, -0.1), 0.1, 0, Math.PI);

        checkSpherical(SphericalCoordinates.fromCartesian(1, 1, 1), sqrt3, QUARTER_PI, Math.acos(1 / sqrt3));
        checkSpherical(SphericalCoordinates.fromCartesian(-1, -1, -1), sqrt3, 1.25 * Math.PI, Math.acos(-1 / sqrt3));
    }

    @Test
    void testFromCartesian_vector() {
        // arrange
        final double sqrt3 = Math.sqrt(3);

        // act/assert
        checkSpherical(SphericalCoordinates.fromCartesian(Vector3D.of(0, 0, 0)), 0, 0, 0);

        checkSpherical(SphericalCoordinates.fromCartesian(Vector3D.of(0.1, 0, 0)), 0.1, 0, Angle.PI_OVER_TWO);
        checkSpherical(SphericalCoordinates.fromCartesian(Vector3D.of(-0.1, 0, 0)), 0.1, Math.PI, Angle.PI_OVER_TWO);

        checkSpherical(SphericalCoordinates.fromCartesian(Vector3D.of(0, 0.1, 0)), 0.1, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO);
        checkSpherical(SphericalCoordinates.fromCartesian(Vector3D.of(0, -0.1, 0)), 0.1, THREE_PI_OVER_TWO, Angle.PI_OVER_TWO);

        checkSpherical(SphericalCoordinates.fromCartesian(Vector3D.of(0, 0, 0.1)), 0.1, 0, 0);
        checkSpherical(SphericalCoordinates.fromCartesian(Vector3D.of(0, 0, -0.1)), 0.1, 0, Math.PI);

        checkSpherical(SphericalCoordinates.fromCartesian(Vector3D.of(1, 1, 1)), sqrt3, QUARTER_PI, Math.acos(1 / sqrt3));
        checkSpherical(SphericalCoordinates.fromCartesian(Vector3D.of(-1, -1, -1)), sqrt3, 1.25 * Math.PI, Math.acos(-1 / sqrt3));
    }

    @Test
    void testToVector() {
        // arrange
        final double sqrt3 = Math.sqrt(3);

        // act/assert
        checkVector(SphericalCoordinates.of(0, 0, 0).toVector(), 0, 0, 0);

        checkVector(SphericalCoordinates.of(1, 0, Angle.PI_OVER_TWO).toVector(), 1, 0, 0);
        checkVector(SphericalCoordinates.of(1, Math.PI, Angle.PI_OVER_TWO).toVector(), -1, 0, 0);

        checkVector(SphericalCoordinates.of(2, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO).toVector(), 0, 2, 0);
        checkVector(SphericalCoordinates.of(2, -Angle.PI_OVER_TWO, Angle.PI_OVER_TWO).toVector(), 0, -2, 0);

        checkVector(SphericalCoordinates.of(3, 0, 0).toVector(), 0, 0, 3);
        checkVector(SphericalCoordinates.of(3, 0, Math.PI).toVector(), 0, 0, -3);

        checkVector(SphericalCoordinates.of(sqrt3, QUARTER_PI, Math.acos(1 / sqrt3)).toVector(), 1, 1, 1);
        checkVector(SphericalCoordinates.of(sqrt3, MINUS_THREE_QUARTER_PI, Math.acos(-1 / sqrt3)).toVector(), -1, -1, -1);
    }

    @Test
    void testToCartesian_static() {
        // arrange
        final double sqrt3 = Math.sqrt(3);

        // act/assert
        checkVector(SphericalCoordinates.toCartesian(0, 0, 0), 0, 0, 0);

        checkVector(SphericalCoordinates.toCartesian(1, 0, Angle.PI_OVER_TWO), 1, 0, 0);
        checkVector(SphericalCoordinates.toCartesian(1, Math.PI, Angle.PI_OVER_TWO), -1, 0, 0);

        checkVector(SphericalCoordinates.toCartesian(2, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO), 0, 2, 0);
        checkVector(SphericalCoordinates.toCartesian(2, -Angle.PI_OVER_TWO, Angle.PI_OVER_TWO), 0, -2, 0);

        checkVector(SphericalCoordinates.toCartesian(3, 0, 0), 0, 0, 3);
        checkVector(SphericalCoordinates.toCartesian(3, 0, Math.PI), 0, 0, -3);

        checkVector(SphericalCoordinates.toCartesian(Math.sqrt(3), QUARTER_PI, Math.acos(1 / sqrt3)), 1, 1, 1);
        checkVector(SphericalCoordinates.toCartesian(Math.sqrt(3), MINUS_THREE_QUARTER_PI, Math.acos(-1 / sqrt3)), -1, -1, -1);
    }

    @Test
    void testParse() {
        // act/assert
        checkSpherical(SphericalCoordinates.parse("(1, 2, 3)"), 1, 2, 3);
        checkSpherical(SphericalCoordinates.parse("(  -2.0 , 1 , -5e-1)"), 2, 1 + Math.PI, Math.PI - 0.5);
        checkSpherical(SphericalCoordinates.parse("(NaN,Infinity,-Infinity)"), Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
    }

    private void checkSpherical(final SphericalCoordinates c, final double radius, final double azimuth, final double polar) {
        Assertions.assertEquals(radius, c.getRadius(), EPS);
        Assertions.assertEquals(azimuth, c.getAzimuth(), EPS);
        Assertions.assertEquals(polar, c.getPolar(), EPS);
    }

    private void checkVector(final Vector3D v, final double x, final double y, final double z) {
        Assertions.assertEquals(x, v.getX(), EPS);
        Assertions.assertEquals(y, v.getY(), EPS);
        Assertions.assertEquals(z, v.getZ(), EPS);
    }

    @Test
    void testParse_failure_1_oe() {
        // act/assert
        try {
    SphericalCoordinates.parse("abc");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
