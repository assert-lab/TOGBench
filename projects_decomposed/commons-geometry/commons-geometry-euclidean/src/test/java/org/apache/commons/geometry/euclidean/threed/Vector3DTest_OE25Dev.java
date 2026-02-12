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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Vector3DTest_OE25Dev {

    private static final double EPS = 1e-15;

    @Test
    void testConstants() {
        // act/assert
        checkVector(Vector3D.ZERO, 0, 0, 0);

        checkVector(Vector3D.Unit.PLUS_X, 1, 0, 0);
        checkVector(Vector3D.Unit.MINUS_X, -1, 0, 0);

        checkVector(Vector3D.Unit.PLUS_Y, 0, 1, 0);
        checkVector(Vector3D.Unit.MINUS_Y, 0, -1, 0);

        checkVector(Vector3D.Unit.PLUS_Z, 0, 0, 1);
        checkVector(Vector3D.Unit.MINUS_Z, 0, 0, -1);

        checkVector(Vector3D.NaN, Double.NaN, Double.NaN, Double.NaN);
        checkVector(Vector3D.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        checkVector(Vector3D.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    }

    @Test
    void testAdd() {
        // arrange
        final Vector3D v1 = Vector3D.of(1, 2, 3);
        final Vector3D v2 = Vector3D.of(-4, -5, -6);
        final Vector3D v3 = Vector3D.of(7, 8, 9);

        // act/assert
        checkVector(v1.add(v1), 2, 4, 6);

        checkVector(v1.add(v2), -3, -3, -3);
        checkVector(v2.add(v1), -3, -3, -3);

        checkVector(v1.add(v3), 8, 10, 12);
        checkVector(v3.add(v1), 8, 10, 12);
    }

    @Test
    void testAdd_scaled() {
        // arrange
        final Vector3D v1 = Vector3D.of(1, 2, 3);
        final Vector3D v2 = Vector3D.of(-4, -5, -6);
        final Vector3D v3 = Vector3D.of(7, 8, 9);

        // act/assert
        checkVector(v1.add(0, v1), 1, 2, 3);
        checkVector(v1.add(0.5, v1), 1.5, 3, 4.5);
        checkVector(v1.add(1, v1), 2, 4, 6);

        checkVector(v1.add(2, v2), -7, -8, -9);
        checkVector(v2.add(2, v1), -2, -1, -0);

        checkVector(v1.add(-2, v3), -13, -14, -15);
        checkVector(v3.add(-2, v1), 5, 4, 3);
    }

    @Test
    void testSubtract() {
        // arrange
        final Vector3D v1 = Vector3D.of(1, 2, 3);
        final Vector3D v2 = Vector3D.of(-4, -5, -6);
        final Vector3D v3 = Vector3D.of(7, 8, 9);

        // act/assert
        checkVector(v1.subtract(v1), 0, 0, 0);

        checkVector(v1.subtract(v2), 5, 7, 9);
        checkVector(v2.subtract(v1), -5, -7, -9);

        checkVector(v1.subtract(v3), -6, -6, -6);
        checkVector(v3.subtract(v1), 6, 6, 6);
    }

    @Test
    void testSubtract_scaled() {
        // arrange
        final Vector3D v1 = Vector3D.of(1, 2, 3);
        final Vector3D v2 = Vector3D.of(-4, -5, -6);
        final Vector3D v3 = Vector3D.of(7, 8, 9);

        // act/assert
        checkVector(v1.subtract(0, v1), 1, 2, 3);
        checkVector(v1.subtract(0.5, v1), 0.5, 1, 1.5);
        checkVector(v1.subtract(1, v1), 0, 0, 0);

        checkVector(v1.subtract(2, v2), 9, 12, 15);
        checkVector(v2.subtract(2, v1), -6, -9, -12);

        checkVector(v1.subtract(-2, v3), 15, 18, 21);
        checkVector(v3.subtract(-2, v1), 9, 12, 15);
    }

    @Test
    void testNegate() {
        // act/assert
        checkVector(Vector3D.of(0.1, 2.5, 1.3).negate(), -0.1, -2.5, -1.3);
        checkVector(Vector3D.of(-0.1, -2.5, -1.3).negate(), 0.1, 2.5, 1.3);
    }

    @Test
    void testNegate_unitVectors() {
        // arrange
        final Vector3D v1 = Vector3D.of(1.0, 2.0, 3.0).normalize();
        final Vector3D v2 = Vector3D.of(-2.0, -4.0, -3.0).normalize();

        // act/assert
        checkVector(v1.negate(), -1.0 / Math.sqrt(14.0), -Math.sqrt(2.0 / 7.0), -3.0 / Math.sqrt(14.0));
        checkVector(v2.negate(), 2.0 / Math.sqrt(29.0), 4.0 / Math.sqrt(29.0), 3.0 / Math.sqrt(29.0));
    }

    @Test
    void testNormalize_illegalNorm() {
        // arrange
        final Pattern illegalNorm = Pattern.compile("^Illegal norm: (0\\.0|-?Infinity|NaN)");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(Vector3D.ZERO::normalize,
                IllegalArgumentException.class, illegalNorm);
        GeometryTestUtils.assertThrowsWithMessage(Vector3D.NaN::normalize,
                IllegalArgumentException.class, illegalNorm);
        GeometryTestUtils.assertThrowsWithMessage(Vector3D.POSITIVE_INFINITY::normalize,
                IllegalArgumentException.class, illegalNorm);
        GeometryTestUtils.assertThrowsWithMessage(Vector3D.NEGATIVE_INFINITY::normalize,
                IllegalArgumentException.class, illegalNorm);
    }

    @Test
    void testOrthogonal_givenDirection() {
        // arrange
        final double invSqrt2 = 1.0 / Math.sqrt(2.0);

        // act/assert
        checkVector(Vector3D.Unit.PLUS_X.orthogonal(Vector3D.of(-1.0, 0.1, 0.0)), 0.0, 1.0, 0.0);
        checkVector(Vector3D.Unit.PLUS_Y.orthogonal(Vector3D.of(2.0, 2.0, 2.0)), invSqrt2, 0.0, invSqrt2);
        checkVector(Vector3D.Unit.PLUS_Z.orthogonal(Vector3D.of(3.0, 3.0, -3.0)), invSqrt2, invSqrt2, 0.0);

        checkVector(Vector3D.of(invSqrt2, invSqrt2, 0.0).orthogonal(Vector3D.of(1.0, 1.0, 0.2)), 0.0, 0.0, 1.0);
    }

    @Test
    void testCrossProduct() {
        // act/assert
        checkVector(Vector3D.Unit.PLUS_X.cross(Vector3D.Unit.PLUS_Y), 0, 0, 1);
        checkVector(Vector3D.Unit.PLUS_X.cross(Vector3D.Unit.MINUS_Y), 0, 0, -1);

        checkVector(Vector3D.Unit.MINUS_X.cross(Vector3D.Unit.MINUS_Y), 0, 0, 1);
        checkVector(Vector3D.Unit.MINUS_X.cross(Vector3D.Unit.PLUS_Y), 0, 0, -1);

        checkVector(Vector3D.of(2, 1, -4).cross(Vector3D.of(3, 1, -1)), 3, -10, -1);

        final double invSqrt6 = 1 / Math.sqrt(6);
        checkVector(Vector3D.of(1, 1, 1).cross(Vector3D.of(-1, 0, 1)).normalize(), invSqrt6, -2 * invSqrt6, invSqrt6);
    }

    @Test
    void testCrossProduct_cancellation() {
        // act/assert
        final Vector3D v1 = Vector3D.of(9070467121.0, 4535233560.0, 1);
        final Vector3D v2 = Vector3D.of(9070467123.0, 4535233561.0, 1);
        checkVector(v1.cross(v2), -1, 2, 1);

        final double scale    = Math.scalb(1.0, 100);
        final Vector3D big1   = v1.multiply(scale);
        final Vector3D small2 = v2.multiply(1 / scale);
        checkVector(big1.cross(small2), -1, 2, 1);
    }

    @Test
    void testScalarMultiply() {
        // arrange
        final Vector3D v1 = Vector3D.of(2, 3, 4);
        final Vector3D v2 = Vector3D.of(-2, -3, -4);

        // act/assert
        checkVector(v1.multiply(0), 0, 0, 0);
        checkVector(v1.multiply(0.5), 1, 1.5, 2);
        checkVector(v1.multiply(1), 2, 3, 4);
        checkVector(v1.multiply(2), 4, 6, 8);
        checkVector(v1.multiply(-2), -4, -6, -8);

        checkVector(v2.multiply(0), 0, 0, 0);
        checkVector(v2.multiply(0.5), -1, -1.5, -2);
        checkVector(v2.multiply(1), -2, -3, -4);
        checkVector(v2.multiply(2), -4, -6, -8);
        checkVector(v2.multiply(-2), 4, 6, 8);
    }

    @Test
    void testProject() {
        // arrange
        final Vector3D v1 = Vector3D.of(2.0, 3.0, 4.0);
        final Vector3D v2 = Vector3D.of(-5.0, -6.0, -7.0);

        // act/assert
        checkVector(Vector3D.ZERO.project(Vector3D.Unit.PLUS_X), 0.0, 0.0, 0.0);

        checkVector(v1.project(Vector3D.Unit.PLUS_X), 2.0, 0.0, 0.0);
        checkVector(v1.project(Vector3D.Unit.MINUS_X), 2.0, 0.0, 0.0);
        checkVector(v1.project(Vector3D.Unit.PLUS_Y), 0.0, 3.0, 0.0);
        checkVector(v1.project(Vector3D.Unit.MINUS_Y), 0.0, 3.0, 0.0);
        checkVector(v1.project(Vector3D.Unit.PLUS_Z), 0.0, 0.0, 4.0);
        checkVector(v1.project(Vector3D.Unit.MINUS_Z), 0.0, 0.0, 4.0);

        checkVector(v2.project(Vector3D.Unit.PLUS_X), -5.0, 0.0, 0.0);
        checkVector(v2.project(Vector3D.Unit.MINUS_X), -5.0, 0.0, 0.0);
        checkVector(v2.project(Vector3D.Unit.PLUS_Y), 0.0, -6.0, 0.0);
        checkVector(v2.project(Vector3D.Unit.MINUS_Y), 0.0, -6.0, 0.0);
        checkVector(v2.project(Vector3D.Unit.PLUS_Z), 0.0, 0.0, -7.0);
        checkVector(v2.project(Vector3D.Unit.MINUS_Z), 0.0, 0.0, -7.0);

        checkVector(v1.project(Vector3D.of(1.0, 1.0, 1.0)), 3.0, 3.0, 3.0);
        checkVector(v1.project(Vector3D.of(-1.0, -1.0, -1.0)), 3.0, 3.0, 3.0);

        checkVector(v2.project(Vector3D.of(1.0, 1.0, 1.0)), -6.0, -6.0, -6.0);
        checkVector(v2.project(Vector3D.of(-1.0, -1.0, -1.0)), -6.0, -6.0, -6.0);
    }

    @Test
    void testReject() {
        // arrange
        final Vector3D v1 = Vector3D.of(2.0, 3.0, 4.0);
        final Vector3D v2 = Vector3D.of(-5.0, -6.0, -7.0);

        // act/assert
        checkVector(Vector3D.ZERO.reject(Vector3D.Unit.PLUS_X), 0.0, 0.0, 0.0);

        checkVector(v1.reject(Vector3D.Unit.PLUS_X), 0.0, 3.0, 4.0);
        checkVector(v1.reject(Vector3D.Unit.MINUS_X), 0.0, 3.0, 4.0);
        checkVector(v1.reject(Vector3D.Unit.PLUS_Y), 2.0, 0.0, 4.0);
        checkVector(v1.reject(Vector3D.Unit.MINUS_Y), 2.0, 0.0, 4.0);
        checkVector(v1.reject(Vector3D.Unit.PLUS_Z), 2.0, 3.0, 0.0);
        checkVector(v1.reject(Vector3D.Unit.MINUS_Z), 2.0, 3.0, 0.0);

        checkVector(v2.reject(Vector3D.Unit.PLUS_X), 0.0, -6.0, -7.0);
        checkVector(v2.reject(Vector3D.Unit.MINUS_X), 0.0, -6.0, -7.0);
        checkVector(v2.reject(Vector3D.Unit.PLUS_Y), -5.0, 0.0, -7.0);
        checkVector(v2.reject(Vector3D.Unit.MINUS_Y), -5.0, 0.0, -7.0);
        checkVector(v2.reject(Vector3D.Unit.PLUS_Z), -5.0, -6.0, 0.0);
        checkVector(v2.reject(Vector3D.Unit.MINUS_Z), -5.0, -6.0, 0.0);

        checkVector(v1.reject(Vector3D.of(1.0, 1.0, 1.0)), -1.0, 0.0, 1.0);
        checkVector(v1.reject(Vector3D.of(-1.0, -1.0, -1.0)), -1.0, 0.0, 1.0);

        checkVector(v2.reject(Vector3D.of(1.0, 1.0, 1.0)), 1.0, 0.0, -1.0);
        checkVector(v2.reject(Vector3D.of(-1.0, -1.0, -1.0)), 1.0, 0.0, -1.0);
    }

    @Test
    void testProjectAndReject_areComplementary() {
        // arrange
        final double eps = 1e-12;

        // act/assert
        checkProjectAndRejectFullSphere(Vector3D.of(1.0, 0.0, 0.0), 1.0, eps);
        checkProjectAndRejectFullSphere(Vector3D.of(0.0, 1.0, 0.0), 2.0, eps);
        checkProjectAndRejectFullSphere(Vector3D.of(0.0, 0.0, 1.0), 2.0, eps);
        checkProjectAndRejectFullSphere(Vector3D.of(1.0, 1.0, 1.0), 3.0, eps);

        checkProjectAndRejectFullSphere(Vector3D.of(-2.0, 0.0, 0.0), 1.0, eps);
        checkProjectAndRejectFullSphere(Vector3D.of(0.0, -2.0, 0.0), 2.0, eps);
        checkProjectAndRejectFullSphere(Vector3D.of(0.0, 0.0, -2.0), 2.0, eps);
        checkProjectAndRejectFullSphere(Vector3D.of(-2.0, -2.0, -2.0), 3.0, eps);
    }

    private void checkProjectAndRejectFullSphere(final Vector3D vec, final double baseMag, final double eps) {
        for (double polar = 0.0; polar <= Math.PI; polar += 0.5) {
            for (double azimuth = 0.0; azimuth <= Angle.TWO_PI; azimuth += 0.5) {
                final Vector3D base = SphericalCoordinates.toCartesian(baseMag, azimuth, polar);

                final Vector3D proj = vec.project(base);
                final Vector3D rej = vec.reject(base);

                // ensure that the projection and rejection sum to the original vector
                EuclideanTestUtils.assertCoordinatesEqual(vec, proj.add(rej), eps);

                final double angle = base.angle(vec);

                // check the angle between the projection and the base; this will
                // be undefined when the angle between the original vector and the
                // base is pi/2 (which means that the projection is the zero vector)
                if (angle < Angle.PI_OVER_TWO) {
                    Assertions.assertEquals(0.0, proj.angle(base), eps);
                } else if (angle > Angle.PI_OVER_TWO) {
                    Assertions.assertEquals(Math.PI, proj.angle(base), eps);
                }

                // check the angle between the rejection and the base; this should
                // always be pi/2 except for when the angle between the original vector
                // and the base is 0 or pi, in which case the rejection is the zero vector.
                if (angle > 0.0 && angle < Math.PI) {
                    Assertions.assertEquals(Angle.PI_OVER_TWO, rej.angle(base), eps);
                }
            }
        }
    }

    @Test
    void testVectorTo() {
        // act/assert
        final Vector3D p1 = Vector3D.of(1, 2, 3);
        final Vector3D p2 = Vector3D.of(4, 5, 6);
        final Vector3D p3 = Vector3D.of(-7, -8, -9);

        // act/assert
        checkVector(p1.vectorTo(p1), 0, 0, 0);
        checkVector(p2.vectorTo(p2), 0, 0, 0);
        checkVector(p3.vectorTo(p3), 0, 0, 0);

        checkVector(p1.vectorTo(p2), 3, 3, 3);
        checkVector(p2.vectorTo(p1), -3, -3, -3);

        checkVector(p1.vectorTo(p3), -8, -10, -12);
        checkVector(p3.vectorTo(p1), 8, 10, 12);
    }

    @Test
    void testDirectionTo() {
        // act/assert
        final double invSqrt3 = 1.0 / Math.sqrt(3);

        final Vector3D p1 = Vector3D.of(1, 1, 1);
        final Vector3D p2 = Vector3D.of(1, 5, 1);
        final Vector3D p3 = Vector3D.of(-2, -2, -2);

        // act/assert
        checkVector(p1.directionTo(p2), 0, 1, 0);
        checkVector(p2.directionTo(p1), 0, -1, 0);

        checkVector(p1.directionTo(p3), -invSqrt3, -invSqrt3, -invSqrt3);
        checkVector(p3.directionTo(p1), invSqrt3, invSqrt3, invSqrt3);
    }

    @Test
    void testLerp() {
        // arrange
        final Vector3D v1 = Vector3D.of(1, -5, 2);
        final Vector3D v2 = Vector3D.of(-4, 0, 2);
        final Vector3D v3 = Vector3D.of(10, -4, 0);

        // act/assert
        checkVector(v1.lerp(v1, 0), 1, -5, 2);
        checkVector(v1.lerp(v1, 1), 1, -5, 2);

        checkVector(v1.lerp(v2, -0.25), 2.25, -6.25, 2);
        checkVector(v1.lerp(v2, 0), 1, -5, 2);
        checkVector(v1.lerp(v2, 0.25), -0.25, -3.75, 2);
        checkVector(v1.lerp(v2, 0.5), -1.5, -2.5, 2);
        checkVector(v1.lerp(v2, 0.75), -2.75, -1.25, 2);
        checkVector(v1.lerp(v2, 1), -4, 0, 2);
        checkVector(v1.lerp(v2, 1.25), -5.25, 1.25, 2);

        checkVector(v1.lerp(v3, 0), 1, -5, 2);
        checkVector(v1.lerp(v3, 0.25), 3.25, -4.75, 1.5);
        checkVector(v1.lerp(v3, 0.5), 5.5, -4.5, 1);
        checkVector(v1.lerp(v3, 0.75), 7.75, -4.25, 0.5);
        checkVector(v1.lerp(v3, 1), 10, -4, 0);
    }

    @Test
    void testTransform() {
        // arrange
        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity()
                .scale(2)
                .translate(1, 2, 3);

        final Vector3D v1 = Vector3D.of(1, 2, 3);
        final Vector3D v2 = Vector3D.of(-4, -5, -6);

        // act/assert
        checkVector(v1.transform(transform), 3, 6, 9);
        checkVector(v2.transform(transform), -7, -8, -9);
    }

    @Test
    void testParse() {
        // act/assert
        checkVector(Vector3D.parse("(1, 2, 3)"), 1, 2, 3);
        checkVector(Vector3D.parse("(-1, -2, -3)"), -1, -2, -3);

        checkVector(Vector3D.parse("(0.01, -1e-3, 0)"), 1e-2, -1e-3, 0);

        checkVector(Vector3D.parse("(NaN, -Infinity, Infinity)"), Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

        checkVector(Vector3D.parse(Vector3D.ZERO.toString()), 0, 0, 0);
        checkVector(Vector3D.parse(Vector3D.Unit.MINUS_X.toString()), -1, 0, 0);
    }

    @Test
    void testOf() {
        // act/assert
        checkVector(Vector3D.of(1, 2, 3), 1, 2, 3);
        checkVector(Vector3D.of(-1, -2, -3), -1, -2, -3);
        checkVector(Vector3D.of(Math.PI, Double.NaN, Double.POSITIVE_INFINITY),
                Math.PI, Double.NaN, Double.POSITIVE_INFINITY);
        checkVector(Vector3D.of(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Math.E),
                Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Math.E);
    }

    @Test
    void testOf_arrayArg() {
        // act/assert
        checkVector(Vector3D.of(new double[] {1, 2, 3}), 1, 2, 3);
        checkVector(Vector3D.of(new double[] {-1, -2, -3}), -1, -2, -3);
        checkVector(Vector3D.of(new double[] {Math.PI, Double.NaN, Double.POSITIVE_INFINITY}),
                Math.PI, Double.NaN, Double.POSITIVE_INFINITY);
        checkVector(Vector3D.of(new double[] {Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Math.E}),
                Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Math.E);
    }

    @Test
    void testUnitFrom_coordinates() {
        // arrange
        final double invSqrt3 = 1.0 / Math.sqrt(3.0);

        // act/assert
        checkVector(Vector3D.Unit.from(2.0, -2.0, 2.0), invSqrt3, -invSqrt3, invSqrt3);
        checkVector(Vector3D.Unit.from(-4.0, 4.0, -4.0), -invSqrt3, invSqrt3, -invSqrt3);
    }

    @Test
    void testMax() {
        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-100, 1, 100),
                Vector3D.max(Collections.singletonList(Vector3D.of(-100, 1, 100))), EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(0, 1, 100),
                Vector3D.max(Arrays.asList(Vector3D.of(-100, 1, 100), Vector3D.of(0, 1, 0))), EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-1, 0, 2),
                Vector3D.max(Vector3D.of(-2, 0, 0), Vector3D.of(-1, -5, 1), Vector3D.of(-10, -10, 2)), EPS);
    }

    @Test
    void testMax_noPointsGiven() {
        // arrange
        final String msg = "Cannot compute vector max: no vectors given";

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Vector3D.max(new ArrayList<>());
        }, IllegalArgumentException.class, msg);
    }

    @Test
    void testMin() {
        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-100, 1, 100),
                Vector3D.min(Collections.singletonList(Vector3D.of(-100, 1, 100))), EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-100, 1, 0),
                Vector3D.min(Arrays.asList(Vector3D.of(-100, 1, 100), Vector3D.of(0, 1, 0))), EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-10, -10, 0),
                Vector3D.min(Vector3D.of(-2, 0, 0), Vector3D.of(-1, -5, 1), Vector3D.of(-10, -10, 2)), EPS);
    }

    @Test
    void testMin_noPointsGiven() {
        // arrange
        final String msg = "Cannot compute vector min: no vectors given";

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Vector3D.min(new ArrayList<>());
        }, IllegalArgumentException.class, msg);
    }

    @Test
    void testCentroid() {
        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, 2, 3),
                Vector3D.centroid(Vector3D.of(1, 2, 3)), EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(2.5, 3.5, 4.5),
                Vector3D.centroid(Vector3D.of(1, 2, 3), Vector3D.of(2, 3, 4),
                        Vector3D.of(3, 4, 5), Vector3D.of(4, 5, 6)), EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, 2, 3),
                Vector3D.centroid(Collections.singletonList(Vector3D.of(1, 2, 3))), EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(0.5, 1, 1.5),
                Vector3D.centroid(Arrays.asList(Vector3D.of(1, 2, 3), Vector3D.of(1, 2, 3),
                        Vector3D.ZERO, Vector3D.ZERO)), EPS);
    }

    @Test
    void testCentroid_noPointsGiven() {
        // arrange
        final String msg = "Cannot compute centroid: no points given";

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Vector3D.centroid(new ArrayList<>());
        }, IllegalArgumentException.class, msg);
    }

    @Test
    void testSum_factoryMethods() {
        // act/assert
        checkVector(Vector3D.Sum.create().get(), 0, 0, 0);
        checkVector(Vector3D.Sum.of(Vector3D.of(1, 2, 3)).get(), 1, 2, 3);
        checkVector(Vector3D.Sum.of(
                Vector3D.of(1, 2, 3),
                Vector3D.Unit.PLUS_X,
                Vector3D.Unit.PLUS_Y,
                Vector3D.Unit.PLUS_Z).get(), 2, 3, 4);
    }

    @Test
    void testSum_instanceMethods() {
        // arrange
        final Vector3D p1 = Vector3D.of(1, 2, 3);
        final Vector3D p2 = Vector3D.of(4, 6, 8);

        // act/assert
        checkVector(Vector3D.Sum.create()
                .add(p1)
                .addScaled(0.5, p2)
                .get(), 3, 5, 7);
    }

    @Test
    void testSum_accept() {
        // arrange
        final Vector3D p1 = Vector3D.of(1, 2, -3);
        final Vector3D p2 = Vector3D.of(3, -6, 8);

        final List<Vector3D.Unit> units = Arrays.asList(
                Vector3D.Unit.PLUS_X,
                Vector3D.Unit.PLUS_Y,
                Vector3D.Unit.PLUS_Z);

        final Vector3D.Sum s = Vector3D.Sum.create();

        // act/assert
        Arrays.asList(p1, Vector3D.ZERO, p2).forEach(s);
        units.forEach(s);

        // assert
        checkVector(s.get(), 5, -3, 6);
    }

    private void checkVector(final Vector3D v, final double x, final double y, final double z) {
        Assertions.assertEquals(x, v.getX(), EPS);
        Assertions.assertEquals(y, v.getY(), EPS);
        Assertions.assertEquals(z, v.getZ(), EPS);
    }

    @Test
    void testConstants_normalize_1_oe() {
        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, Vector3D.ZERO::normalize);
    }

    @Test
    void testConstants_normalize_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, Vector3D.NaN::normalize);
    }

    @Test
    void testConstants_normalize_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, Vector3D.POSITIVE_INFINITY::normalize);
    }

    @Test
    void testConstants_normalize_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, Vector3D.NEGATIVE_INFINITY::normalize);
    }

    @Test
    void testWithNorm_illegalNorm_1_oe() {
        // act/assert
        try {
    Vector3D.ZERO.withNorm(2.0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testWithNorm_illegalNorm_2_oe() {
        // act/assert
        // removed other assertion
        try {
    Vector3D.NaN.withNorm(2.0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testWithNorm_illegalNorm_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    Vector3D.POSITIVE_INFINITY.withNorm(2.0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testWithNorm_illegalNorm_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Vector3D.NEGATIVE_INFINITY.withNorm(2.0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOrthogonal_illegalNorm_1_oe() {
        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, Vector3D.ZERO::orthogonal);
    }

    @Test
    void testOrthogonal_illegalNorm_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, Vector3D.NaN::orthogonal);
    }

    @Test
    void testOrthogonal_illegalNorm_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, Vector3D.POSITIVE_INFINITY::orthogonal);
    }

    @Test
    void testOrthogonal_illegalNorm_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, Vector3D.NEGATIVE_INFINITY::orthogonal);
    }

    @Test
    void testOrthogonal_givenDirection_illegalNorm_1_oe() {
        // act/assert

        try {
    Vector3D.ZERO.orthogonal(Vector3D.Unit.PLUS_X);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOrthogonal_givenDirection_illegalNorm_2_oe() {
        // act/assert

        // removed other assertion
        try {
    Vector3D.NaN.orthogonal(Vector3D.Unit.PLUS_X);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOrthogonal_givenDirection_illegalNorm_3_oe() {
        // act/assert

        // removed other assertion
        // removed other assertion
        try {
    Vector3D.POSITIVE_INFINITY.orthogonal(Vector3D.Unit.PLUS_X);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOrthogonal_givenDirection_illegalNorm_4_oe() {
        // act/assert

        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Vector3D.NEGATIVE_INFINITY.orthogonal(Vector3D.Unit.PLUS_X);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOrthogonal_givenDirection_illegalNorm_5_oe() {
        // act/assert

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Vector3D.Unit.PLUS_X.orthogonal(Vector3D.ZERO);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOrthogonal_givenDirection_illegalNorm_6_oe() {
        // act/assert

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Vector3D.Unit.PLUS_X.orthogonal(Vector3D.NaN);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOrthogonal_givenDirection_illegalNorm_7_oe() {
        // act/assert

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Vector3D.Unit.PLUS_X.orthogonal(Vector3D.POSITIVE_INFINITY);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOrthogonal_givenDirection_illegalNorm_8_oe() {
        // act/assert

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Vector3D.Unit.PLUS_X.orthogonal(Vector3D.NEGATIVE_INFINITY);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOrthogonal_givenDirection_directionIsCollinear_1_oe() {
        // act/assert
        try {
    Vector3D.Unit.PLUS_X.orthogonal(Vector3D.Unit.PLUS_X);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOrthogonal_givenDirection_directionIsCollinear_2_oe() {
        // act/assert
        // removed other assertion
        try {
    Vector3D.Unit.PLUS_X.orthogonal(Vector3D.Unit.MINUS_X);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOrthogonal_givenDirection_directionIsCollinear_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    Vector3D.of(1.0, 1.0, 1.0).orthogonal(Vector3D.of(2.0, 2.0, 2.0));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOrthogonal_givenDirection_directionIsCollinear_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Vector3D.of(-1.01, -1.01, -1.01).orthogonal(Vector3D.of(20.1, 20.1, 20.1));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAngle_illegalNorm_1_oe() {
        // arrange
        final Vector3D v = Vector3D.of(1.0, 1.0, 1.0);

        // act/assert

        try {
    Vector3D.ZERO.angle(v);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAngle_illegalNorm_2_oe() {
        // arrange
        final Vector3D v = Vector3D.of(1.0, 1.0, 1.0);

        // act/assert

        // removed other assertion
        try {
    Vector3D.NaN.angle(v);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAngle_illegalNorm_3_oe() {
        // arrange
        final Vector3D v = Vector3D.of(1.0, 1.0, 1.0);

        // act/assert

        // removed other assertion
        // removed other assertion
        try {
    Vector3D.POSITIVE_INFINITY.angle(v);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAngle_illegalNorm_4_oe() {
        // arrange
        final Vector3D v = Vector3D.of(1.0, 1.0, 1.0);

        // act/assert

        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Vector3D.NEGATIVE_INFINITY.angle(v);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAngle_illegalNorm_5_oe() {
        // arrange
        final Vector3D v = Vector3D.of(1.0, 1.0, 1.0);

        // act/assert

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    v.angle(Vector3D.ZERO);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAngle_illegalNorm_6_oe() {
        // arrange
        final Vector3D v = Vector3D.of(1.0, 1.0, 1.0);

        // act/assert

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    v.angle(Vector3D.NaN);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAngle_illegalNorm_7_oe() {
        // arrange
        final Vector3D v = Vector3D.of(1.0, 1.0, 1.0);

        // act/assert

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    v.angle(Vector3D.POSITIVE_INFINITY);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAngle_illegalNorm_8_oe() {
        // arrange
        final Vector3D v = Vector3D.of(1.0, 1.0, 1.0);

        // act/assert

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    v.angle(Vector3D.NEGATIVE_INFINITY);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testProject_baseHasIllegalNorm_1_oe() {
        // arrange
        final Vector3D v = Vector3D.of(1.0, 1.0, 1.0);

        // act/assert
        try {
    v.project(Vector3D.ZERO);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testProject_baseHasIllegalNorm_2_oe() {
        // arrange
        final Vector3D v = Vector3D.of(1.0, 1.0, 1.0);

        // act/assert
        // removed other assertion
        try {
    v.project(Vector3D.NaN);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testProject_baseHasIllegalNorm_3_oe() {
        // arrange
        final Vector3D v = Vector3D.of(1.0, 1.0, 1.0);

        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    v.project(Vector3D.POSITIVE_INFINITY);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testProject_baseHasIllegalNorm_4_oe() {
        // arrange
        final Vector3D v = Vector3D.of(1.0, 1.0, 1.0);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    v.project(Vector3D.NEGATIVE_INFINITY);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testReject_baseHasIllegalNorm_1_oe() {
        // arrange
        final Vector3D v = Vector3D.of(1.0, 1.0, 1.0);

        // act/assert

        try {
    v.reject(Vector3D.ZERO);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testReject_baseHasIllegalNorm_2_oe() {
        // arrange
        final Vector3D v = Vector3D.of(1.0, 1.0, 1.0);

        // act/assert

        // removed other assertion
        try {
    v.reject(Vector3D.NaN);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testReject_baseHasIllegalNorm_3_oe() {
        // arrange
        final Vector3D v = Vector3D.of(1.0, 1.0, 1.0);

        // act/assert

        // removed other assertion
        // removed other assertion
        try {
    v.reject(Vector3D.POSITIVE_INFINITY);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testReject_baseHasIllegalNorm_4_oe() {
        // arrange
        final Vector3D v = Vector3D.of(1.0, 1.0, 1.0);

        // act/assert

        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    v.reject(Vector3D.NEGATIVE_INFINITY);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testDirectionTo_illegalNorm_1_oe() {
        // arrange
        final Vector3D p = Vector3D.of(1, 2, 3);

        // act/assert
        try {
    Vector3D.ZERO.directionTo(Vector3D.ZERO);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testDirectionTo_illegalNorm_2_oe() {
        // arrange
        final Vector3D p = Vector3D.of(1, 2, 3);

        // act/assert
        // removed other assertion
        try {
    p.directionTo(p);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testDirectionTo_illegalNorm_3_oe() {
        // arrange
        final Vector3D p = Vector3D.of(1, 2, 3);

        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    Vector3D.NEGATIVE_INFINITY.directionTo(p);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testDirectionTo_illegalNorm_4_oe() {
        // arrange
        final Vector3D p = Vector3D.of(1, 2, 3);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    p.directionTo(Vector3D.POSITIVE_INFINITY);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testParse_failure_1_oe() {
        // act/assert
        try {
    Vector3D.parse("abc");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testOf_arrayArg_invalidDimensions_1_oe() {
        // act/assert
        try {
    Vector3D.of(new double[] {0.0, 0.0});
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testUnitFrom_static_illegalNorm_1_oe() {
        try {
    Vector3D.Unit.from(0.0, 0.0, 0.0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testUnitFrom_static_illegalNorm_2_oe() {
        // removed other assertion
        try {
    Vector3D.Unit.from(Double.NaN, 1.0, 1.0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testUnitFrom_static_illegalNorm_3_oe() {
        // removed other assertion
        // removed other assertion
        try {
    Vector3D.Unit.from(1.0, Double.NEGATIVE_INFINITY, 1.0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testUnitFrom_static_illegalNorm_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Vector3D.Unit.from(1.0, 1.0, Double.POSITIVE_INFINITY);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
