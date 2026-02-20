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
package org.apache.commons.geometry.euclidean.threed.rotation;

import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.internal.SimpleTupleFormat;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.threed.AffineTransformMatrix3D;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.apache.commons.numbers.quaternion.Quaternion;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class QuaternionRotationTest_OE25Dev {

    private static final double EPS = 1e-12;

    // use non-normalized axes to ensure that the axis is normalized
    private static final Vector3D PLUS_X_DIR = Vector3D.of(2, 0, 0);
    private static final Vector3D MINUS_X_DIR = Vector3D.of(-2, 0, 0);

    private static final Vector3D PLUS_Y_DIR = Vector3D.of(0, 3, 0);
    private static final Vector3D MINUS_Y_DIR = Vector3D.of(0, -3, 0);

    private static final Vector3D PLUS_Z_DIR = Vector3D.of(0, 0, 4);
    private static final Vector3D MINUS_Z_DIR = Vector3D.of(0, 0, -4);

    private static final Vector3D PLUS_DIAGONAL = Vector3D.of(1, 1, 1);
    private static final Vector3D MINUS_DIAGONAL = Vector3D.of(-1, -1, -1);

    private static final double TWO_THIRDS_PI = 2.0 * Math.PI / 3.0;
    private static final double MINUS_TWO_THIRDS_PI = -TWO_THIRDS_PI;

    @Test
    void testOf_quaternion() {
        // act/assert
        checkQuaternion(QuaternionRotation.of(Quaternion.of(1, 0, 0, 0)), 1, 0, 0, 0);
        checkQuaternion(QuaternionRotation.of(Quaternion.of(-1, 0, 0, 0)), 1, 0, 0, 0);
        checkQuaternion(QuaternionRotation.of(Quaternion.of(0, 1, 0, 0)), 0, 1, 0, 0);
        checkQuaternion(QuaternionRotation.of(Quaternion.of(0, 0, 1, 0)), 0, 0, 1, 0);
        checkQuaternion(QuaternionRotation.of(Quaternion.of(0, 0, 0, 1)), 0, 0, 0, 1);

        checkQuaternion(QuaternionRotation.of(Quaternion.of(1, 1, 1, 1)), 0.5, 0.5, 0.5, 0.5);
        checkQuaternion(QuaternionRotation.of(Quaternion.of(-1, -1, -1, -1)), 0.5, 0.5, 0.5, 0.5);
    }

    @Test
    void testOf_components() {
        // act/assert
        checkQuaternion(QuaternionRotation.of(1, 0, 0, 0), 1, 0, 0, 0);
        checkQuaternion(QuaternionRotation.of(-1, 0, 0, 0), 1, 0, 0, 0);
        checkQuaternion(QuaternionRotation.of(0, 1, 0, 0), 0, 1, 0, 0);
        checkQuaternion(QuaternionRotation.of(0, 0, 1, 0), 0, 0, 1, 0);
        checkQuaternion(QuaternionRotation.of(0, 0, 0, 1), 0, 0, 0, 1);

        checkQuaternion(QuaternionRotation.of(1, 1, 1, 1), 0.5, 0.5, 0.5, 0.5);
        checkQuaternion(QuaternionRotation.of(-1, -1, -1, -1), 0.5, 0.5, 0.5, 0.5);
    }

    @Test
    void testIdentity_axis() {
        // arrange
        final QuaternionRotation q = QuaternionRotation.identity();

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.PLUS_X, q.getAxis(), EPS);
    }

    @Test
    void testGetAxis() {
        // act/assert
        checkVector(QuaternionRotation.of(0, 1, 0, 0).getAxis(), 1, 0, 0);
        checkVector(QuaternionRotation.of(0, -1, 0, 0).getAxis(), -1, 0, 0);

        checkVector(QuaternionRotation.of(0, 0, 1, 0).getAxis(), 0, 1, 0);
        checkVector(QuaternionRotation.of(0, 0, -1, 0).getAxis(), 0, -1, 0);

        checkVector(QuaternionRotation.of(0, 0, 0, 1).getAxis(), 0, 0, 1);
        checkVector(QuaternionRotation.of(0, 0, 0, -1).getAxis(), 0, 0, -1);
    }

    @Test
    void testGetAxis_noAxis() {
        // arrange
        final QuaternionRotation rot = QuaternionRotation.of(1, 0, 0, 0);

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.PLUS_X, rot.getAxis(), EPS);
    }

    @Test
    void testGetAxis_matchesAxisAngleConstruction() {
        EuclideanTestUtils.permuteSkipZero(-5, 5, 1, (x, y, z) -> {
            // arrange
            final Vector3D vec = Vector3D.of(x, y, z);
            final Vector3D norm = vec.normalize();

            // act/assert

            // positive angle results in the axis being the normalized input axis
            EuclideanTestUtils.assertCoordinatesEqual(norm,
                    QuaternionRotation.fromAxisAngle(vec, Angle.PI_OVER_TWO).getAxis(), EPS);

            // negative angle results in the axis being the negated normalized input axis
            EuclideanTestUtils.assertCoordinatesEqual(norm,
                    QuaternionRotation.fromAxisAngle(vec.negate(), -Angle.PI_OVER_TWO).getAxis(), EPS);
        });
    }

    @Test
    void testApplyVector() {
        // arrange
        final QuaternionRotation q = QuaternionRotation.fromAxisAngle(Vector3D.of(1, 1, 1), Angle.PI_OVER_TWO);

        EuclideanTestUtils.permute(-2, 2, 0.2, (x, y, z) -> {
            final Vector3D input = Vector3D.of(x, y, z);

            // act
            final Vector3D pt = q.apply(input);
            final Vector3D vec = q.applyVector(input);

            EuclideanTestUtils.assertCoordinatesEqual(pt, vec, EPS);
        });
    }

    @Test
    void testInverse_undoesOriginalRotation() {
        EuclideanTestUtils.permuteSkipZero(-5, 5, 1, (x, y, z) -> {
            // arrange
            final Vector3D vec = Vector3D.of(x, y, z);

            final QuaternionRotation rot = QuaternionRotation.fromAxisAngle(vec, 0.75 * Math.PI);
            final QuaternionRotation neg = rot.inverse();

            // act/assert
            EuclideanTestUtils.assertCoordinatesEqual(PLUS_DIAGONAL, neg.apply(rot.apply(PLUS_DIAGONAL)), EPS);
            EuclideanTestUtils.assertCoordinatesEqual(PLUS_DIAGONAL, rot.apply(neg.apply(PLUS_DIAGONAL)), EPS);
        });
    }

    @Test
    void testSlerp_simple() {
        // arrange
        final QuaternionRotation q0 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Z, 0.0);
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Z, Math.PI);
        final DoubleFunction<QuaternionRotation> fn = q0.slerp(q1);
        final Vector3D v = Vector3D.of(2, 0, 1);

        final double sqrt2 = Math.sqrt(2);

        // act
        checkVector(fn.apply(0).apply(v), 2, 0, 1);
        checkVector(fn.apply(0.25).apply(v), sqrt2, sqrt2, 1);
        checkVector(fn.apply(0.5).apply(v), 0, 2, 1);
        checkVector(fn.apply(0.75).apply(v), -sqrt2, sqrt2, 1);
        checkVector(fn.apply(1).apply(v), -2, 0, 1);
    }

    @Test
    void testSlerp_multipleCombinations() {
        // arrange
        final QuaternionRotation[] rotations = {
                QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, 0.0),
                QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, Angle.PI_OVER_TWO),
                QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, Math.PI),

                QuaternionRotation.fromAxisAngle(Vector3D.Unit.MINUS_X, 0.0),
                QuaternionRotation.fromAxisAngle(Vector3D.Unit.MINUS_X, Angle.PI_OVER_TWO),
                QuaternionRotation.fromAxisAngle(Vector3D.Unit.MINUS_X, Math.PI),

                QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, 0.0),
                QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, Angle.PI_OVER_TWO),
                QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, Math.PI),

                QuaternionRotation.fromAxisAngle(Vector3D.Unit.MINUS_Y, 0.0),
                QuaternionRotation.fromAxisAngle(Vector3D.Unit.MINUS_Y, Angle.PI_OVER_TWO),
                QuaternionRotation.fromAxisAngle(Vector3D.Unit.MINUS_Y, Math.PI),

                QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Z, 0.0),
                QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Z, Angle.PI_OVER_TWO),
                QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Z, Math.PI),

                QuaternionRotation.fromAxisAngle(Vector3D.Unit.MINUS_Z, 0.0),
                QuaternionRotation.fromAxisAngle(Vector3D.Unit.MINUS_Z, Angle.PI_OVER_TWO),
                QuaternionRotation.fromAxisAngle(Vector3D.Unit.MINUS_Z, Math.PI),
        };

        // act/assert
        // test each rotation against all of the others (including itself)
        for (final QuaternionRotation quaternionRotation : rotations) {
            for (final QuaternionRotation rotation : rotations) {
                checkSlerpCombination(quaternionRotation, rotation);
            }
        }
    }

    private void checkSlerpCombination(final QuaternionRotation start, final QuaternionRotation end) {
        final DoubleFunction<QuaternionRotation> slerp = start.slerp(end);
        final Vector3D vec = Vector3D.of(1, 1, 1).normalize();

        final Vector3D startVec = start.apply(vec);
        final Vector3D endVec = end.apply(vec);

        // check start and end values
        EuclideanTestUtils.assertCoordinatesEqual(startVec, slerp.apply(0).apply(vec), EPS);
        EuclideanTestUtils.assertCoordinatesEqual(endVec, slerp.apply(1).apply(vec), EPS);

        // check intermediate values
        double prevAngle = -1;
        final int numSteps = 100;
        final double delta = 1d / numSteps;
        for (int step = 0; step <= numSteps; step++) {
            final double t = step * delta;
            final QuaternionRotation result = slerp.apply(t);

            final Vector3D slerpVec = result.apply(vec);
            Assertions.assertEquals(1, slerpVec.norm(), EPS);

            // make sure that we're steadily progressing to the end angle
            final double angle = slerpVec.angle(startVec);
            Assertions.assertTrue(Precision.compareTo(angle,prevAngle,EPS)>= 0,"Expected slerp angle to continuously increase;previous angle was " + prevAngle + " and new angle is " + angle);

            prevAngle = angle;
        }
    }

    @Test
    void testSlerp_tOutsideOfZeroToOne_apply() {
        // arrange
        final Vector3D vec = Vector3D.Unit.PLUS_X;

        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Z, 0.25 * Math.PI);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Z, 0.75 * Math.PI);

        // act/assert
        final DoubleFunction<QuaternionRotation> slerp12 = q1.slerp(q2);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.PLUS_X, slerp12.apply(-4.5).apply(vec), EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.PLUS_X, slerp12.apply(-0.5).apply(vec), EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.MINUS_X, slerp12.apply(1.5).apply(vec), EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.MINUS_X, slerp12.apply(5.5).apply(vec), EPS);

        final DoubleFunction<QuaternionRotation> slerp21 = q2.slerp(q1);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.MINUS_X, slerp21.apply(-4.5).apply(vec), EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.MINUS_X, slerp21.apply(-0.5).apply(vec), EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.PLUS_X, slerp21.apply(1.5).apply(vec), EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.Unit.PLUS_X, slerp21.apply(5.5).apply(vec), EPS);
    }

    @Test
    void testAxisAngleSequenceConversion_relative() {
        for (final AxisSequence axes : AxisSequence.values()) {
            checkAxisAngleSequenceToQuaternionRoundtrip(AxisReferenceFrame.RELATIVE, axes);
            checkQuaternionToAxisAngleSequenceRoundtrip(AxisReferenceFrame.RELATIVE, axes);
        }
    }

    @Test
    void testAxisAngleSequenceConversion_absolute() {
        for (final AxisSequence axes : AxisSequence.values()) {
            checkAxisAngleSequenceToQuaternionRoundtrip(AxisReferenceFrame.ABSOLUTE, axes);
            checkQuaternionToAxisAngleSequenceRoundtrip(AxisReferenceFrame.ABSOLUTE, axes);
        }
    }

    private void checkAxisAngleSequenceToQuaternionRoundtrip(final AxisReferenceFrame frame, final AxisSequence axes) {
        final double step = 0.3;
        final double angle2Start = axes.getType() == AxisSequenceType.EULER ? 0.0 + 0.1 : -Angle.PI_OVER_TWO + 0.1;
        final double angle2Stop = angle2Start + Math.PI;

        for (double angle1 = 0.0; angle1 <= Angle.TWO_PI; angle1 += step) {
            for (double angle2 = angle2Start; angle2 < angle2Stop; angle2 += step) {
                for (double angle3 = 0.0; angle3 <= Angle.TWO_PI; angle3 += 0.3) {
                    // arrange
                    final AxisAngleSequence angles = new AxisAngleSequence(frame, axes, angle1, angle2, angle3);

                    // act
                    final QuaternionRotation q = QuaternionRotation.fromAxisAngleSequence(angles);
                    final AxisAngleSequence result = q.toAxisAngleSequence(frame, axes);

                    // assert
                    Assertions.assertEquals(frame, result.getReferenceFrame());
                    Assertions.assertEquals(axes, result.getAxisSequence());

                    assertRadiansEquals(angle1, result.getAngle1());
                    assertRadiansEquals(angle2, result.getAngle2());
                    assertRadiansEquals(angle3, result.getAngle3());
                }
            }
        }
    }

    private void checkQuaternionToAxisAngleSequenceRoundtrip(final AxisReferenceFrame frame, final AxisSequence axes) {
        final double step = 0.1;

        EuclideanTestUtils.permuteSkipZero(-1, 1, 0.5, (x, y, z) -> {
            final Vector3D axis = Vector3D.of(x, y, z);

            for (double angle = -Angle.TWO_PI; angle <= Angle.TWO_PI; angle += step) {
                // arrange
                final QuaternionRotation q = QuaternionRotation.fromAxisAngle(axis, angle);

                // act
                final AxisAngleSequence seq = q.toAxisAngleSequence(frame, axes);
                final QuaternionRotation result = QuaternionRotation.fromAxisAngleSequence(seq);

                // assert
                checkQuaternion(result, q.getQuaternion().getW(), q.getQuaternion().getX(), q.getQuaternion().getY(), q.getQuaternion().getZ());
            }
        });
    }

    private List<AxisSequence> getAxes(final AxisSequenceType type) {
        return Stream.of(AxisSequence.values())
                .filter(a -> type.equals(a.getType()))
                .collect(Collectors.toList());
    }

    @Test
    void testCreateVectorRotation_permute() {
        EuclideanTestUtils.permuteSkipZero(-5, 5, 0.1, (x, y, z) -> {
            // arrange
            final Vector3D u1 = Vector3D.of(x, y, z);
            final Vector3D u2 = PLUS_DIAGONAL;

            // act
            final QuaternionRotation q = QuaternionRotation.createVectorRotation(u1, u2);

            // assert
            Assertions.assertEquals(0.0, q.apply(u1).angle(u2), EPS);
            Assertions.assertEquals(0.0, q.inverse().apply(u2).angle(u1), EPS);

            final double angle = q.getAngle();
            Assertions.assertTrue(angle >= 0.0);
            Assertions.assertTrue(angle <= Math.PI);
        });
    }

    @Test
    void testCreateBasisRotation_permute() {
        // arrange
        final Vector3D u1 = Vector3D.of(1, 2, 3);
        final Vector3D u2 = Vector3D.of(0, 4, 0);

        final Vector3D u1Dir = u1.normalize();
        final Vector3D u2Dir = u1Dir.orthogonal(u2);

        EuclideanTestUtils.permuteSkipZero(-5, 5, 0.2, (x, y, z) -> {
            final Vector3D v1 = Vector3D.of(x, y, z);
            final Vector3D v2 = v1.orthogonal();

            final Vector3D v1Dir = v1.normalize();
            final Vector3D v2Dir = v2.normalize();

            // act
            final QuaternionRotation q = QuaternionRotation.createBasisRotation(u1, u2, v1, v2);
            final QuaternionRotation qInv = q.inverse();

            // assert
            EuclideanTestUtils.assertCoordinatesEqual(v1Dir, q.apply(u1Dir), EPS);
            EuclideanTestUtils.assertCoordinatesEqual(v2Dir, q.apply(u2Dir), EPS);

            EuclideanTestUtils.assertCoordinatesEqual(u1Dir, qInv.apply(v1Dir), EPS);
            EuclideanTestUtils.assertCoordinatesEqual(u2Dir, qInv.apply(v2Dir), EPS);

            final double angle = q.getAngle();
            Assertions.assertTrue(angle >= 0.0);
            Assertions.assertTrue(angle <= Math.PI);

            final Vector3D transformedX = q.apply(Vector3D.Unit.PLUS_X);
            final Vector3D transformedY = q.apply(Vector3D.Unit.PLUS_Y);
            final Vector3D transformedZ = q.apply(Vector3D.Unit.PLUS_Z);

            Assertions.assertEquals(1.0, transformedX.norm(), EPS);
            Assertions.assertEquals(1.0, transformedY.norm(), EPS);
            Assertions.assertEquals(1.0, transformedZ.norm(), EPS);

            Assertions.assertEquals(0.0, transformedX.dot(transformedY), EPS);
            Assertions.assertEquals(0.0, transformedX.dot(transformedZ), EPS);
            Assertions.assertEquals(0.0, transformedY.dot(transformedZ), EPS);

            EuclideanTestUtils.assertCoordinatesEqual(transformedZ.normalize(),
                    transformedX.normalize().cross(transformedY.normalize()), EPS);

            Assertions.assertEquals(1.0, q.getQuaternion().norm(), EPS);
        });
    }

    @Test
    void testFromEulerAngles_relative() {

        // --- act/assert

        // XYZ
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_X_HALF_PI, AxisSequence.XYZ, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.XYZ, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.XYZ, 0, 0, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.XYZ, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, 0);

        // XZY
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_X_HALF_PI, AxisSequence.XZY, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.XZY, 0, 0, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.XZY, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.XZY, Angle.PI_OVER_TWO, 0, Angle.PI_OVER_TWO);

        // YXZ
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_X_HALF_PI, AxisSequence.YXZ, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.YXZ, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.YXZ, 0, 0, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.YXZ, Angle.PI_OVER_TWO, 0, Angle.PI_OVER_TWO);

        // YZX
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_X_HALF_PI, AxisSequence.YZX, 0, 0, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.YZX, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.YZX, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.YZX, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, 0);

        // ZXY
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_X_HALF_PI, AxisSequence.YZX, 0, 0, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.YZX, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.YZX, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.YZX, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, 0);

        // ZYX
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_X_HALF_PI, AxisSequence.ZYX, 0, 0, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.ZYX, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.ZYX, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.ZYX, Angle.PI_OVER_TWO, 0, Angle.PI_OVER_TWO);

        // XYX
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_X_HALF_PI, AxisSequence.XYX, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.XYX, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.XYX, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.XYX, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, 0);

        // XZX
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_X_HALF_PI, AxisSequence.XZX, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.XZX, -Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.XZX, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.XZX, 0, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO);

        // YXY
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_X_HALF_PI, AxisSequence.YXY, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.YXY, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.YXY, -Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.YXY, 0, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO);

        // YZY
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_X_HALF_PI, AxisSequence.YZY, -Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.YZY, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.YZY, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.YZY, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, 0);

        // ZXZ
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_X_HALF_PI, AxisSequence.ZXZ, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.ZXZ, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.ZXZ, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.ZXZ, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, 0);

        // ZYZ
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_X_HALF_PI, AxisSequence.ZYZ, Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.ZYZ, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.ZYZ, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceRelative(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.ZYZ, 0, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO);
    }

    /** Helper method for verifying that a relative euler angles instance constructed with the given arguments
     * is correctly converted to a QuaternionRotation that matches the given operator.
     * @param rotation
     * @param axes
     * @param angle1
     * @param angle2
     * @param angle3
     */
    private void checkFromAxisAngleSequenceRelative(final UnaryOperator<Vector3D> rotation, final AxisSequence axes, final double angle1, final double angle2, final double angle3) {
        final AxisAngleSequence angles = AxisAngleSequence.createRelative(axes, angle1, angle2, angle3);

        assertRotationEquals(rotation, QuaternionRotation.fromAxisAngleSequence(angles));
    }

    @Test
    void testFromEulerAngles_absolute() {

        // --- act/assert

        // XYZ
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_X_HALF_PI, AxisSequence.XYZ, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.XYZ, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.XYZ, 0, 0, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.XYZ, Angle.PI_OVER_TWO, 0, Angle.PI_OVER_TWO);

        // XZY
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_X_HALF_PI, AxisSequence.XZY, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.XZY, 0, 0, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.XZY, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.XZY, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, 0);

        // YXZ
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_X_HALF_PI, AxisSequence.YXZ, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.YXZ, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.YXZ, 0, 0, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.YXZ, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, 0);

        // YZX
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_X_HALF_PI, AxisSequence.YZX, 0, 0, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.YZX, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.YZX, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.YZX, Angle.PI_OVER_TWO, 0, Angle.PI_OVER_TWO);

        // ZXY
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_X_HALF_PI, AxisSequence.YZX, 0, 0, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.YZX, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.YZX, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.YZX, Angle.PI_OVER_TWO, 0, Angle.PI_OVER_TWO);

        // ZYX
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_X_HALF_PI, AxisSequence.ZYX, 0, 0, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.ZYX, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.ZYX, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.ZYX, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, 0);

        // XYX
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_X_HALF_PI, AxisSequence.XYX, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.XYX, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.XYX, Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.XYX, 0, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO);

        // XZX
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_X_HALF_PI, AxisSequence.XZX, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.XZX, -Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.XZX, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.XZX, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, 0);

        // YXY
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_X_HALF_PI, AxisSequence.YXY, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.YXY, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.YXY, -Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.YXY, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, 0);

        // YZY
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_X_HALF_PI, AxisSequence.YZY, -Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.YZY, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.YZY, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.YZY, 0, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO);

        // ZXZ
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_X_HALF_PI, AxisSequence.ZXZ, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.ZXZ, -Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.ZXZ, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.ZXZ, 0, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO);

        // ZYZ
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_X_HALF_PI, AxisSequence.ZYZ, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, -Angle.PI_OVER_TWO);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Y_HALF_PI, AxisSequence.ZYZ, 0, Angle.PI_OVER_TWO, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_Z_HALF_PI, AxisSequence.ZYZ, Angle.PI_OVER_TWO, 0, 0);
        checkFromAxisAngleSequenceAbsolute(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, AxisSequence.ZYZ, Angle.PI_OVER_TWO, Angle.PI_OVER_TWO, 0);
    }

    /** Helper method for verifying that an absolute euler angles instance constructed with the given arguments
     * is correctly converted to a QuaternionRotation that matches the given operator.
     * @param rotation
     * @param axes
     * @param angle1
     * @param angle2
     * @param angle3
     */
    private void checkFromAxisAngleSequenceAbsolute(final UnaryOperator<Vector3D> rotation, final AxisSequence axes, final double angle1, final double angle2, final double angle3) {
        final AxisAngleSequence angles = AxisAngleSequence.createAbsolute(axes, angle1, angle2, angle3);

        assertRotationEquals(rotation, QuaternionRotation.fromAxisAngleSequence(angles));
    }

    private static void checkQuaternion(final QuaternionRotation qrot, final double w, final double x, final double y, final double z) {
        final String msg = "Expected" +
                " quaternion to equal " + SimpleTupleFormat.getDefault().format(w, x, y, z) + " but was " + qrot;

        Assertions.assertEquals(w, qrot.getQuaternion().getW(), EPS, msg);
        Assertions.assertEquals(x, qrot.getQuaternion().getX(), EPS, msg);
        Assertions.assertEquals(y, qrot.getQuaternion().getY(), EPS, msg);
        Assertions.assertEquals(z, qrot.getQuaternion().getZ(), EPS, msg);

        final Quaternion q = qrot.getQuaternion();
        Assertions.assertEquals(w, q.getW(), EPS, msg);
        Assertions.assertEquals(x, q.getX(), EPS, msg);
        Assertions.assertEquals(y, q.getY(), EPS, msg);
        Assertions.assertEquals(z, q.getZ(), EPS, msg);

        Assertions.assertTrue(qrot.preservesOrientation());
    }

    private static void checkVector(final Vector3D v, final double x, final double y, final double z) {
        final String msg = "Expected vector to equal " + SimpleTupleFormat.getDefault().format(x, y, z) + " but was " + v;

        Assertions.assertEquals(x, v.getX(), EPS, msg);
        Assertions.assertEquals(y, v.getY(), EPS, msg);
        Assertions.assertEquals(z, v.getZ(), EPS, msg);
    }

    /** Assert that the two given radian values are equivalent.
     * @param expected
     * @param actual
     */
    private static void assertRadiansEquals(final double expected, final double actual) {
        final double diff = Angle.Rad.WITHIN_MINUS_PI_AND_PI.applyAsDouble(expected - actual);
        final String msg = "Expected " + actual + " radians to be equivalent to " + expected + " radians; difference is " + diff;

        Assertions.assertTrue(Math.abs(diff) < 1e-6, msg);
    }

    /**
     * Assert that {@code rotation} returns the same outputs as {@code expected} for a range of vector inputs.
     * @param expected
     * @param rotation
     */
    private static void assertRotationEquals(final UnaryOperator<Vector3D> expected, final QuaternionRotation rotation) {
        assertFnEquals(expected, rotation);
    }

    /**
     * Assert that {@code transform} returns the same outputs as {@code expected} for a range of vector inputs.
     * @param expected
     * @param transform
     */
    private static void assertTransformEquals(final UnaryOperator<Vector3D> expected, final AffineTransformMatrix3D transform) {
        assertFnEquals(expected, transform);
    }

    /**
     * Assert that {@code actual} returns the same output as {@code expected} for a range of inputs.
     * @param expectedFn
     * @param actualFn
     */
    private static void assertFnEquals(final UnaryOperator<Vector3D> expectedFn, final UnaryOperator<Vector3D> actualFn) {
        EuclideanTestUtils.permute(-2, 2, 0.25, (x, y, z) -> {
            final Vector3D input = Vector3D.of(x, y, z);

            final Vector3D expected = expectedFn.apply(input);
            final Vector3D actual = actualFn.apply(input);

            final String msg = "Expected vector " + input + " to be transformed to " + expected + " but was " + actual;

            Assertions.assertEquals(expected.getX(), actual.getX(), EPS, msg);
            Assertions.assertEquals(expected.getY(), actual.getY(), EPS, msg);
            Assertions.assertEquals(expected.getZ(), actual.getZ(), EPS, msg);
        });
    }

    @Test
    void testOf_quaternion_illegalNorm_1_oe() {
        // act/assert
        try {
    QuaternionRotation.of(Quaternion.of(0, 0, 0, 0));
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testOf_quaternion_illegalNorm_2_oe() {
        // act/assert
        // removed other assertion
        try {
    QuaternionRotation.of(Quaternion.of(1, 1, 1, Double.NaN));
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testOf_quaternion_illegalNorm_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    QuaternionRotation.of(Quaternion.of(1, 1, Double.POSITIVE_INFINITY, 1));
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testOf_quaternion_illegalNorm_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    QuaternionRotation.of(Quaternion.of(1, Double.NEGATIVE_INFINITY, 1, 1));
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testOf_quaternion_illegalNorm_5_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    QuaternionRotation.of(Quaternion.of(Double.NaN, 1, 1, 1));
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testOf_components_illegalNorm_1_oe() {
        // act/assert
        try {
    QuaternionRotation.of(0, 0, 0, 0);
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testOf_components_illegalNorm_2_oe() {
        // act/assert
        // removed other assertion
        try {
    QuaternionRotation.of(1, 1, 1, Double.NaN);
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testOf_components_illegalNorm_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    QuaternionRotation.of(1, 1, Double.POSITIVE_INFINITY, 1);
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testOf_components_illegalNorm_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    QuaternionRotation.of(1, Double.NEGATIVE_INFINITY, 1, 1);
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testOf_components_illegalNorm_5_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    QuaternionRotation.of(Double.NaN, 1, 1, 1);
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testGetAngle_1_oe() {
        // act/assert
        Assertions.assertEquals(0.0, QuaternionRotation.of(1, 0, 0, 0).getAngle(), EPS);
    }

    @Test
    void testGetAngle_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertEquals(0.0, QuaternionRotation.of(-1, 0, 0, 0).getAngle(), EPS);
    }

    @Test
    void testGetAngle_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Angle.PI_OVER_TWO, QuaternionRotation.of(1, 0, 0, 1).getAngle(), EPS);
    }

    @Test
    void testGetAngle_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, QuaternionRotation.of(-1, 0, 0, -1).getAngle(), EPS);
    }

    @Test
    void testGetAngle_5_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Math.PI  * 2.0 / 3.0, QuaternionRotation.of(1, 1, 1, 1).getAngle(), EPS);
    }

    @Test
    void testGetAngle_6_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertEquals(Math.PI, QuaternionRotation.of(0, 0, 0, 1).getAngle(), EPS);
    }

    @Test
    void testGetAngle_matchesAxisAngleConstruction_1_oe() {
        for (double theta = -2 * Math.PI; theta <= 2 * Math.PI; theta += 0.1) {
            // arrange
            final QuaternionRotation rot = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, theta);

            // act
            final double angle = rot.getAngle();

            // assert
            // make sure that we're in the [0, pi] range
            Assertions.assertTrue(angle >= 0.0);
    }
    }

    @Test
    void testGetAngle_matchesAxisAngleConstruction_2_oe() {
        for (double theta = -2 * Math.PI; theta <= 2 * Math.PI; theta += 0.1) {
            // arrange
            final QuaternionRotation rot = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, theta);

            // act
            final double angle = rot.getAngle();

            // assert
            // make sure that we're in the [0, pi] range
            // removed other assertion
            Assertions.assertTrue(angle <= Math.PI);
    }
    }

    @Test
    void testGetAngle_matchesAxisAngleConstruction_3_oe() {
        for (double theta = -2 * Math.PI; theta <= 2 * Math.PI; theta += 0.1) {
            // arrange
            final QuaternionRotation rot = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, theta);

            // act
            final double angle = rot.getAngle();

            // assert
            // make sure that we're in the [0, pi] range
            // removed other assertion
            // removed other assertion

            double expected = Angle.Rad.WITHIN_MINUS_PI_AND_PI.applyAsDouble(theta);
            if (PLUS_DIAGONAL.dot(rot.getAxis()) < 0) {
                // if the axis ended up being flipped, then negate the expected angle
                expected *= -1;
            }

            Assertions.assertEquals(expected, angle, EPS);
    }
    }

    @Test
    void testFromAxisAngle_invalidAxisNorm_1_oe() {
        // act/assert
        try {
    QuaternionRotation.fromAxisAngle(Vector3D.ZERO, Angle.PI_OVER_TWO);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromAxisAngle_invalidAxisNorm_2_oe() {
        // act/assert
        // removed other assertion
        try {
    QuaternionRotation.fromAxisAngle(Vector3D.NaN, Angle.PI_OVER_TWO);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromAxisAngle_invalidAxisNorm_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    QuaternionRotation.fromAxisAngle(Vector3D.POSITIVE_INFINITY, Angle.PI_OVER_TWO);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromAxisAngle_invalidAxisNorm_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    QuaternionRotation.fromAxisAngle(Vector3D.NEGATIVE_INFINITY, Angle.PI_OVER_TWO);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testInverse_1_oe() {
        // arrange
        final QuaternionRotation rot = QuaternionRotation.of(0.5, 0.5, 0.5, 0.5);

        // act
        final QuaternionRotation neg = rot.inverse();

        // assert
        Assertions.assertEquals(-0.5, neg.getQuaternion().getX(), EPS);
    }

    @Test
    void testInverse_2_oe() {
        // arrange
        final QuaternionRotation rot = QuaternionRotation.of(0.5, 0.5, 0.5, 0.5);

        // act
        final QuaternionRotation neg = rot.inverse();

        // assert
        // removed other assertion
        Assertions.assertEquals(-0.5, neg.getQuaternion().getY(), EPS);
    }

    @Test
    void testInverse_3_oe() {
        // arrange
        final QuaternionRotation rot = QuaternionRotation.of(0.5, 0.5, 0.5, 0.5);

        // act
        final QuaternionRotation neg = rot.inverse();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-0.5, neg.getQuaternion().getZ(), EPS);
    }

    @Test
    void testInverse_4_oe() {
        // arrange
        final QuaternionRotation rot = QuaternionRotation.of(0.5, 0.5, 0.5, 0.5);

        // act
        final QuaternionRotation neg = rot.inverse();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.5, neg.getQuaternion().getW(), EPS);
    }

    @Test
    void testMultiply_sameAxis_simple_2_oe() {
        // arrange
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, 0.1 * Math.PI);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, 0.4 * Math.PI);

        // act
        final QuaternionRotation result = q1.multiply(q2);

        // assert
        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, result.getAngle(), EPS);
    }

    @Test
    void testMultiply_sameAxis_multiple_2_oe() {
        // arrange
        final double oneThird = 1.0 / 3.0;
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, 0.1 * Math.PI);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, oneThird * Math.PI);
        final QuaternionRotation q3 = QuaternionRotation.fromAxisAngle(MINUS_DIAGONAL, 0.4 * Math.PI);
        final QuaternionRotation q4 = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, 0.3 * Math.PI);
        final QuaternionRotation q5 = QuaternionRotation.fromAxisAngle(MINUS_DIAGONAL, -oneThird * Math.PI);

        // act
        final QuaternionRotation result = q1.multiply(q2).multiply(q3).multiply(q4).multiply(q5);

        // assert
        // removed other assertion
        Assertions.assertEquals(2.0 * Math.PI / 3.0, result.getAngle(), EPS);
    }

    @Test
    void testMultiply_differentAxes_2_oe() {
        // arrange
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, Angle.PI_OVER_TWO);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, Angle.PI_OVER_TWO);

        // act
        final QuaternionRotation result = q1.multiply(q2);

        // assert
        // removed other assertion
        Assertions.assertEquals(2.0 * Math.PI / 3.0, result.getAngle(), EPS);
    }

    @Test
    void testMultiply_numericalStability_1_oe() {
        // arrange
        final int slices = 1024;
        final double delta = (8.0 * Math.PI / 3.0) / slices;

        QuaternionRotation q = QuaternionRotation.identity();

        final UniformRandomProvider rand = RandomSource.create(RandomSource.JDK, 2L);

        // act
        for (int i = 0; i < slices; ++i) {
            final double angle = rand.nextDouble();
            final QuaternionRotation forward = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, angle);
            final QuaternionRotation backward = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, delta - angle);

            q = q.multiply(forward).multiply(backward);
        }

        // assert
        Assertions.assertTrue(q.getQuaternion().getW() > 0);
    }

    @Test
    void testMultiply_numericalStability_2_oe() {
        // arrange
        final int slices = 1024;
        final double delta = (8.0 * Math.PI / 3.0) / slices;

        QuaternionRotation q = QuaternionRotation.identity();

        final UniformRandomProvider rand = RandomSource.create(RandomSource.JDK, 2L);

        // act
        for (int i = 0; i < slices; ++i) {
            final double angle = rand.nextDouble();
            final QuaternionRotation forward = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, angle);
            final QuaternionRotation backward = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, delta - angle);

            q = q.multiply(forward).multiply(backward);
        }

        // assert
        // removed other assertion
        Assertions.assertEquals(1.0, q.getQuaternion().norm(), EPS);
    }

    @Test
    void testPremultiply_sameAxis_simple_2_oe() {
        // arrange
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, 0.1 * Math.PI);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, 0.4 * Math.PI);

        // act
        final QuaternionRotation result = q1.premultiply(q2);

        // assert
        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, result.getAngle(), EPS);
    }

    @Test
    void testPremultiply_sameAxis_multiple_2_oe() {
        // arrange
        final double oneThird = 1.0 / 3.0;
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, 0.1 * Math.PI);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, oneThird * Math.PI);
        final QuaternionRotation q3 = QuaternionRotation.fromAxisAngle(MINUS_DIAGONAL, 0.4 * Math.PI);
        final QuaternionRotation q4 = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, 0.3 * Math.PI);
        final QuaternionRotation q5 = QuaternionRotation.fromAxisAngle(MINUS_DIAGONAL, -oneThird * Math.PI);

        // act
        final QuaternionRotation result = q1.premultiply(q2).premultiply(q3).premultiply(q4).premultiply(q5);

        // assert
        // removed other assertion
        Assertions.assertEquals(2.0 * Math.PI / 3.0, result.getAngle(), EPS);
    }

    @Test
    void testPremultiply_differentAxes_2_oe() {
        // arrange
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, Angle.PI_OVER_TWO);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, Angle.PI_OVER_TWO);

        // act
        final QuaternionRotation result = q2.premultiply(q1);

        // assert
        // removed other assertion
        Assertions.assertEquals(2.0 * Math.PI / 3.0, result.getAngle(), EPS);
    }

    @Test
    void testSlerp_followsShortestPath_3_oe() {
        // arrange
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Z, 0.75 * Math.PI);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Z, -0.75 * Math.PI);

        // act
        final QuaternionRotation result = q1.slerp(q2).apply(0.5);

        // assert
        // the slerp should have followed the path around the pi coordinate of the circle rather than
        // the one through the zero coordinate
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(Math.PI, result.getAngle(), EPS);
    }

    @Test
    void testSlerp_inputQuaternionsHaveMinusOneDotProduct_2_oe() {
        // arrange
        final QuaternionRotation q1 = QuaternionRotation.of(1, 0, 0, 1); // pi/2 around +z
        final QuaternionRotation q2 = QuaternionRotation.of(-1, 0, 0, -1); // 3pi/2 around -z

        // act
        final QuaternionRotation result = q1.slerp(q2).apply(0.5);

        // assert
        // removed other assertion

        Assertions.assertEquals(Angle.PI_OVER_TWO, result.getAngle(), EPS);
    }

    @Test
    void testSlerp_outputQuaternionIsNormalizedForAllT_1_oe() {
        // arrange
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Z, 0.25 * Math.PI);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Z, 0.75 * Math.PI);

        final int numSteps = 200;
        final double delta = 1d / numSteps;
        for (int step = 0; step <= numSteps; step++) {
            final double t = -10 + step * delta;

            // act
            final QuaternionRotation result = q1.slerp(q2).apply(t);

            // assert
            Assertions.assertEquals(1.0, result.getQuaternion().norm(), EPS);
    }
    }

    @Test
    void testAxisAngleSequenceConversion_relative_eulerSingularities_1_oe() {
        // arrange
        final double[] eulerSingularities = {
            0.0,
            Math.PI
        };

        final double angle1 = 0.1;
        final double angle2 = 0.3;

        final AxisReferenceFrame frame = AxisReferenceFrame.RELATIVE;

        for (final AxisSequence axes : getAxes(AxisSequenceType.EULER)) {
            for (final double singularityAngle : eulerSingularities) {

                final AxisAngleSequence inputSeq = new AxisAngleSequence(frame, axes, angle1, singularityAngle, angle2);
                final QuaternionRotation inputQuat = QuaternionRotation.fromAxisAngleSequence(inputSeq);

                // act
                final AxisAngleSequence resultSeq = inputQuat.toAxisAngleSequence(frame, axes);
                final QuaternionRotation resultQuat = QuaternionRotation.fromAxisAngleSequence(resultSeq);

                // assert
                Assertions.assertEquals(frame, resultSeq.getReferenceFrame());
    }
    }
    }

    @Test
    void testAxisAngleSequenceConversion_relative_eulerSingularities_2_oe() {
        // arrange
        final double[] eulerSingularities = {
            0.0,
            Math.PI
        };

        final double angle1 = 0.1;
        final double angle2 = 0.3;

        final AxisReferenceFrame frame = AxisReferenceFrame.RELATIVE;

        for (final AxisSequence axes : getAxes(AxisSequenceType.EULER)) {
            for (final double singularityAngle : eulerSingularities) {

                final AxisAngleSequence inputSeq = new AxisAngleSequence(frame, axes, angle1, singularityAngle, angle2);
                final QuaternionRotation inputQuat = QuaternionRotation.fromAxisAngleSequence(inputSeq);

                // act
                final AxisAngleSequence resultSeq = inputQuat.toAxisAngleSequence(frame, axes);
                final QuaternionRotation resultQuat = QuaternionRotation.fromAxisAngleSequence(resultSeq);

                // assert
                // removed other assertion
                Assertions.assertEquals(axes, resultSeq.getAxisSequence());
    }
    }
    }

    @Test
    void testAxisAngleSequenceConversion_absolute_eulerSingularities_1_oe() {
        // arrange
        final double[] eulerSingularities = {
            0.0,
            Math.PI
        };

        final double angle1 = 0.1;
        final double angle2 = 0.3;

        final AxisReferenceFrame frame = AxisReferenceFrame.ABSOLUTE;

        for (final AxisSequence axes : getAxes(AxisSequenceType.EULER)) {
            for (final double singularityAngle : eulerSingularities) {

                final AxisAngleSequence inputSeq = new AxisAngleSequence(frame, axes, angle1, singularityAngle, angle2);
                final QuaternionRotation inputQuat = QuaternionRotation.fromAxisAngleSequence(inputSeq);

                // act
                final AxisAngleSequence resultSeq = inputQuat.toAxisAngleSequence(frame, axes);
                final QuaternionRotation resultQuat = QuaternionRotation.fromAxisAngleSequence(resultSeq);

                // assert
                Assertions.assertEquals(frame, resultSeq.getReferenceFrame());
    }
    }
    }

    @Test
    void testAxisAngleSequenceConversion_absolute_eulerSingularities_2_oe() {
        // arrange
        final double[] eulerSingularities = {
            0.0,
            Math.PI
        };

        final double angle1 = 0.1;
        final double angle2 = 0.3;

        final AxisReferenceFrame frame = AxisReferenceFrame.ABSOLUTE;

        for (final AxisSequence axes : getAxes(AxisSequenceType.EULER)) {
            for (final double singularityAngle : eulerSingularities) {

                final AxisAngleSequence inputSeq = new AxisAngleSequence(frame, axes, angle1, singularityAngle, angle2);
                final QuaternionRotation inputQuat = QuaternionRotation.fromAxisAngleSequence(inputSeq);

                // act
                final AxisAngleSequence resultSeq = inputQuat.toAxisAngleSequence(frame, axes);
                final QuaternionRotation resultQuat = QuaternionRotation.fromAxisAngleSequence(resultSeq);

                // assert
                // removed other assertion
                Assertions.assertEquals(axes, resultSeq.getAxisSequence());
    }
    }
    }

    @Test
    void testAxisAngleSequenceConversion_relative_taitBryanSingularities_1_oe() {
        // arrange
        final double[] taitBryanSingularities = {
            -Angle.PI_OVER_TWO,
            Angle.PI_OVER_TWO
        };

        final double angle1 = 0.1;
        final double angle2 = 0.3;

        final AxisReferenceFrame frame = AxisReferenceFrame.RELATIVE;

        for (final AxisSequence axes : getAxes(AxisSequenceType.TAIT_BRYAN)) {
            for (final double singularityAngle : taitBryanSingularities) {

                final AxisAngleSequence inputSeq = new AxisAngleSequence(frame, axes, angle1, singularityAngle, angle2);
                final QuaternionRotation inputQuat = QuaternionRotation.fromAxisAngleSequence(inputSeq);

                // act
                final AxisAngleSequence resultSeq = inputQuat.toAxisAngleSequence(frame, axes);
                final QuaternionRotation resultQuat = QuaternionRotation.fromAxisAngleSequence(resultSeq);

                // assert
                Assertions.assertEquals(frame, resultSeq.getReferenceFrame());
    }
    }
    }

    @Test
    void testAxisAngleSequenceConversion_relative_taitBryanSingularities_2_oe() {
        // arrange
        final double[] taitBryanSingularities = {
            -Angle.PI_OVER_TWO,
            Angle.PI_OVER_TWO
        };

        final double angle1 = 0.1;
        final double angle2 = 0.3;

        final AxisReferenceFrame frame = AxisReferenceFrame.RELATIVE;

        for (final AxisSequence axes : getAxes(AxisSequenceType.TAIT_BRYAN)) {
            for (final double singularityAngle : taitBryanSingularities) {

                final AxisAngleSequence inputSeq = new AxisAngleSequence(frame, axes, angle1, singularityAngle, angle2);
                final QuaternionRotation inputQuat = QuaternionRotation.fromAxisAngleSequence(inputSeq);

                // act
                final AxisAngleSequence resultSeq = inputQuat.toAxisAngleSequence(frame, axes);
                final QuaternionRotation resultQuat = QuaternionRotation.fromAxisAngleSequence(resultSeq);

                // assert
                // removed other assertion
                Assertions.assertEquals(axes, resultSeq.getAxisSequence());
    }
    }
    }

    @Test
    void testAxisAngleSequenceConversion_absolute_taitBryanSingularities_1_oe() {
        // arrange
        final double[] taitBryanSingularities = {
            -Angle.PI_OVER_TWO,
            Angle.PI_OVER_TWO
        };

        final double angle1 = 0.1;
        final double angle2 = 0.3;

        final AxisReferenceFrame frame = AxisReferenceFrame.ABSOLUTE;

        for (final AxisSequence axes : getAxes(AxisSequenceType.TAIT_BRYAN)) {
            for (final double singularityAngle : taitBryanSingularities) {

                final AxisAngleSequence inputSeq = new AxisAngleSequence(frame, axes, angle1, singularityAngle, angle2);
                final QuaternionRotation inputQuat = QuaternionRotation.fromAxisAngleSequence(inputSeq);

                // act
                final AxisAngleSequence resultSeq = inputQuat.toAxisAngleSequence(frame, axes);
                final QuaternionRotation resultQuat = QuaternionRotation.fromAxisAngleSequence(resultSeq);

                // assert
                Assertions.assertEquals(frame, resultSeq.getReferenceFrame());
    }
    }
    }

    @Test
    void testAxisAngleSequenceConversion_absolute_taitBryanSingularities_2_oe() {
        // arrange
        final double[] taitBryanSingularities = {
            -Angle.PI_OVER_TWO,
            Angle.PI_OVER_TWO
        };

        final double angle1 = 0.1;
        final double angle2 = 0.3;

        final AxisReferenceFrame frame = AxisReferenceFrame.ABSOLUTE;

        for (final AxisSequence axes : getAxes(AxisSequenceType.TAIT_BRYAN)) {
            for (final double singularityAngle : taitBryanSingularities) {

                final AxisAngleSequence inputSeq = new AxisAngleSequence(frame, axes, angle1, singularityAngle, angle2);
                final QuaternionRotation inputQuat = QuaternionRotation.fromAxisAngleSequence(inputSeq);

                // act
                final AxisAngleSequence resultSeq = inputQuat.toAxisAngleSequence(frame, axes);
                final QuaternionRotation resultQuat = QuaternionRotation.fromAxisAngleSequence(resultSeq);

                // assert
                // removed other assertion
                Assertions.assertEquals(axes, resultSeq.getAxisSequence());
    }
    }
    }

    @Test
    void testToAxisAngleSequence_invalidArgs_1_oe() {
        // arrange
        final QuaternionRotation q = QuaternionRotation.identity();

        // act/assert
        try {
    q.toAxisAngleSequence(null, AxisSequence.XYZ);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testToAxisAngleSequence_invalidArgs_2_oe() {
        // arrange
        final QuaternionRotation q = QuaternionRotation.identity();

        // act/assert
        // removed other assertion
        try {
    q.toAxisAngleSequence(AxisReferenceFrame.ABSOLUTE, null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testToRelativeAxisAngleSequence_1_oe() {
        // arrange
        final QuaternionRotation q = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, TWO_THIRDS_PI);

        // act
        final AxisAngleSequence seq = q.toRelativeAxisAngleSequence(AxisSequence.YZX);

        // assert
        Assertions.assertEquals(AxisReferenceFrame.RELATIVE, seq.getReferenceFrame());
    }

    @Test
    void testToRelativeAxisAngleSequence_2_oe() {
        // arrange
        final QuaternionRotation q = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, TWO_THIRDS_PI);

        // act
        final AxisAngleSequence seq = q.toRelativeAxisAngleSequence(AxisSequence.YZX);

        // assert
        // removed other assertion
        Assertions.assertEquals(AxisSequence.YZX, seq.getAxisSequence());
    }

    @Test
    void testToRelativeAxisAngleSequence_3_oe() {
        // arrange
        final QuaternionRotation q = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, TWO_THIRDS_PI);

        // act
        final AxisAngleSequence seq = q.toRelativeAxisAngleSequence(AxisSequence.YZX);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, seq.getAngle1(), EPS);
    }

    @Test
    void testToRelativeAxisAngleSequence_4_oe() {
        // arrange
        final QuaternionRotation q = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, TWO_THIRDS_PI);

        // act
        final AxisAngleSequence seq = q.toRelativeAxisAngleSequence(AxisSequence.YZX);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, seq.getAngle2(), EPS);
    }

    @Test
    void testToRelativeAxisAngleSequence_5_oe() {
        // arrange
        final QuaternionRotation q = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, TWO_THIRDS_PI);

        // act
        final AxisAngleSequence seq = q.toRelativeAxisAngleSequence(AxisSequence.YZX);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, seq.getAngle3(), EPS);
    }

    @Test
    void testToAbsoluteAxisAngleSequence_1_oe() {
        // arrange
        final QuaternionRotation q = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, TWO_THIRDS_PI);

        // act
        final AxisAngleSequence seq = q.toAbsoluteAxisAngleSequence(AxisSequence.YZX);

        // assert
        Assertions.assertEquals(AxisReferenceFrame.ABSOLUTE, seq.getReferenceFrame());
    }

    @Test
    void testToAbsoluteAxisAngleSequence_2_oe() {
        // arrange
        final QuaternionRotation q = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, TWO_THIRDS_PI);

        // act
        final AxisAngleSequence seq = q.toAbsoluteAxisAngleSequence(AxisSequence.YZX);

        // assert
        // removed other assertion
        Assertions.assertEquals(AxisSequence.YZX, seq.getAxisSequence());
    }

    @Test
    void testToAbsoluteAxisAngleSequence_3_oe() {
        // arrange
        final QuaternionRotation q = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, TWO_THIRDS_PI);

        // act
        final AxisAngleSequence seq = q.toAbsoluteAxisAngleSequence(AxisSequence.YZX);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, seq.getAngle1(), EPS);
    }

    @Test
    void testToAbsoluteAxisAngleSequence_4_oe() {
        // arrange
        final QuaternionRotation q = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, TWO_THIRDS_PI);

        // act
        final AxisAngleSequence seq = q.toAbsoluteAxisAngleSequence(AxisSequence.YZX);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, seq.getAngle2(), EPS);
    }

    @Test
    void testToAbsoluteAxisAngleSequence_5_oe() {
        // arrange
        final QuaternionRotation q = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, TWO_THIRDS_PI);

        // act
        final AxisAngleSequence seq = q.toAbsoluteAxisAngleSequence(AxisSequence.YZX);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, seq.getAngle3(), EPS);
    }

    @Test
    void testHashCode_1_oe() {
        // arrange
        final double delta = 100 * Precision.EPSILON;
        final QuaternionRotation q1 = QuaternionRotation.of(1, 2, 3, 4);
        final QuaternionRotation q2 = QuaternionRotation.of(1, 2, 3, 4);

        // act/assert
        Assertions.assertEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    void testHashCode_2_oe() {
        // arrange
        final double delta = 100 * Precision.EPSILON;
        final QuaternionRotation q1 = QuaternionRotation.of(1, 2, 3, 4);
        final QuaternionRotation q2 = QuaternionRotation.of(1, 2, 3, 4);

        // act/assert
        // removed other assertion

        Assertions.assertNotEquals(q1.hashCode(), QuaternionRotation.of(1 + delta, 2, 3, 4).hashCode());
    }

    @Test
    void testHashCode_3_oe() {
        // arrange
        final double delta = 100 * Precision.EPSILON;
        final QuaternionRotation q1 = QuaternionRotation.of(1, 2, 3, 4);
        final QuaternionRotation q2 = QuaternionRotation.of(1, 2, 3, 4);

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNotEquals(q1.hashCode(), QuaternionRotation.of(1, 2 + delta, 3, 4).hashCode());
    }

    @Test
    void testHashCode_4_oe() {
        // arrange
        final double delta = 100 * Precision.EPSILON;
        final QuaternionRotation q1 = QuaternionRotation.of(1, 2, 3, 4);
        final QuaternionRotation q2 = QuaternionRotation.of(1, 2, 3, 4);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNotEquals(q1.hashCode(), QuaternionRotation.of(1, 2, 3 + delta, 4).hashCode());
    }

    @Test
    void testHashCode_5_oe() {
        // arrange
        final double delta = 100 * Precision.EPSILON;
        final QuaternionRotation q1 = QuaternionRotation.of(1, 2, 3, 4);
        final QuaternionRotation q2 = QuaternionRotation.of(1, 2, 3, 4);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNotEquals(q1.hashCode(), QuaternionRotation.of(1, 2, 3, 4 + delta).hashCode());
    }

    @Test
    void testEquals_2_oe() {
        // arrange
        final double delta = 100 * Precision.EPSILON;
        final QuaternionRotation q1 = QuaternionRotation.of(1, 2, 3, 4);
        final QuaternionRotation q2 = QuaternionRotation.of(1, 2, 3, 4);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(q1, q2);
    }

    @Test
    void testEquals_3_oe() {
        // arrange
        final double delta = 100 * Precision.EPSILON;
        final QuaternionRotation q1 = QuaternionRotation.of(1, 2, 3, 4);
        final QuaternionRotation q2 = QuaternionRotation.of(1, 2, 3, 4);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertNotEquals(q1, QuaternionRotation.of(-1, -2, -3, 4));
    }

    @Test
    void testEquals_4_oe() {
        // arrange
        final double delta = 100 * Precision.EPSILON;
        final QuaternionRotation q1 = QuaternionRotation.of(1, 2, 3, 4);
        final QuaternionRotation q2 = QuaternionRotation.of(1, 2, 3, 4);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNotEquals(q1, QuaternionRotation.of(1, 2, 3, -4));
    }

    @Test
    void testEquals_5_oe() {
        // arrange
        final double delta = 100 * Precision.EPSILON;
        final QuaternionRotation q1 = QuaternionRotation.of(1, 2, 3, 4);
        final QuaternionRotation q2 = QuaternionRotation.of(1, 2, 3, 4);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertNotEquals(q1, QuaternionRotation.of(1 + delta, 2, 3, 4));
    }

    @Test
    void testEquals_6_oe() {
        // arrange
        final double delta = 100 * Precision.EPSILON;
        final QuaternionRotation q1 = QuaternionRotation.of(1, 2, 3, 4);
        final QuaternionRotation q2 = QuaternionRotation.of(1, 2, 3, 4);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNotEquals(q1, QuaternionRotation.of(1, 2 + delta, 3, 4));
    }

    @Test
    void testEquals_7_oe() {
        // arrange
        final double delta = 100 * Precision.EPSILON;
        final QuaternionRotation q1 = QuaternionRotation.of(1, 2, 3, 4);
        final QuaternionRotation q2 = QuaternionRotation.of(1, 2, 3, 4);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNotEquals(q1, QuaternionRotation.of(1, 2, 3 + delta, 4));
    }

    @Test
    void testEquals_8_oe() {
        // arrange
        final double delta = 100 * Precision.EPSILON;
        final QuaternionRotation q1 = QuaternionRotation.of(1, 2, 3, 4);
        final QuaternionRotation q2 = QuaternionRotation.of(1, 2, 3, 4);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNotEquals(q1, QuaternionRotation.of(1, 2, 3, 4 + delta));
    }

    @Test
    void testToString_1_oe() {
        // arrange
        final QuaternionRotation q = QuaternionRotation.of(1, 2, 3, 4);
        final Quaternion qField = q.getQuaternion();

        // assert
        Assertions.assertEquals(qField.toString(), q.toString());
    }

    @Test
    void testCreateVectorRotation_simple_2_oe() {
        // arrange
        final Vector3D u1 = Vector3D.Unit.PLUS_X;
        final Vector3D u2 = Vector3D.Unit.PLUS_Y;

        // act
        final QuaternionRotation q = QuaternionRotation.createVectorRotation(u1, u2);

        // assert
        final double val = Math.sqrt(2) * 0.5;

        checkQuaternion(q, val, 0, 0, val);

        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, q.getAngle(), EPS);
    }

    @Test
    void testCreateVectorRotation_identity_2_oe() {
        // arrange
        final Vector3D u1 = Vector3D.of(0, 2, 0);

        // act
        final QuaternionRotation q = QuaternionRotation.createVectorRotation(u1, u1);

        // assert
        checkQuaternion(q, 1, 0, 0, 0);

        // removed other assertion
        Assertions.assertEquals(0.0, q.getAngle(), EPS);
    }

    @Test
    void testCreateVectorRotation_parallel_2_oe() {
        // arrange
        final Vector3D u1 = Vector3D.of(0, 2, 0);
        final Vector3D u2 = Vector3D.of(0, 3, 0);

        // act
        final QuaternionRotation q = QuaternionRotation.createVectorRotation(u1, u2);

        // assert
        checkQuaternion(q, 1, 0, 0, 0);

        // removed other assertion
        Assertions.assertEquals(0.0, q.getAngle(), EPS);
    }

    @Test
    void testCreateVectorRotation_antiparallel_1_oe() {
        // arrange
        final Vector3D u1 = Vector3D.of(0, 2, 0);
        final Vector3D u2 = Vector3D.of(0, -3, 0);

        // act
        final QuaternionRotation q = QuaternionRotation.createVectorRotation(u1, u2);

        // assert
        final Vector3D axis = q.getAxis();
        Assertions.assertEquals(0.0, axis.dot(u1), EPS);
    }

    @Test
    void testCreateVectorRotation_antiparallel_2_oe() {
        // arrange
        final Vector3D u1 = Vector3D.of(0, 2, 0);
        final Vector3D u2 = Vector3D.of(0, -3, 0);

        // act
        final QuaternionRotation q = QuaternionRotation.createVectorRotation(u1, u2);

        // assert
        final Vector3D axis = q.getAxis();
        // removed other assertion
        Assertions.assertEquals(0.0, axis.dot(u2), EPS);
    }

    @Test
    void testCreateVectorRotation_antiparallel_3_oe() {
        // arrange
        final Vector3D u1 = Vector3D.of(0, 2, 0);
        final Vector3D u2 = Vector3D.of(0, -3, 0);

        // act
        final QuaternionRotation q = QuaternionRotation.createVectorRotation(u1, u2);

        // assert
        final Vector3D axis = q.getAxis();
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.PI, q.getAngle(), EPS);
    }

    @Test
    void testCreateVectorRotation_invalidArgs_1_oe() {
        // act/assert
        try {
    QuaternionRotation.createVectorRotation(Vector3D.ZERO, Vector3D.Unit.PLUS_X);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testCreateVectorRotation_invalidArgs_2_oe() {
        // act/assert
        // removed other assertion
        try {
    QuaternionRotation.createVectorRotation(Vector3D.Unit.PLUS_X, Vector3D.ZERO);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testCreateVectorRotation_invalidArgs_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    QuaternionRotation.createVectorRotation(Vector3D.NaN, Vector3D.Unit.PLUS_X);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testCreateVectorRotation_invalidArgs_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    QuaternionRotation.createVectorRotation(Vector3D.Unit.PLUS_X, Vector3D.POSITIVE_INFINITY);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testCreateVectorRotation_invalidArgs_5_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    QuaternionRotation.createVectorRotation(Vector3D.Unit.PLUS_X, Vector3D.NEGATIVE_INFINITY);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testCreateBasisRotation_invalidArgs_1_oe() {
        // act/assert
        try {
    QuaternionRotation.createBasisRotation( Vector3D.ZERO, Vector3D.Unit.PLUS_Y, Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_X);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testCreateBasisRotation_invalidArgs_2_oe() {
        // act/assert
        // removed other assertion
        try {
    QuaternionRotation.createBasisRotation( Vector3D.Unit.PLUS_X, Vector3D.NaN, Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_X);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testCreateBasisRotation_invalidArgs_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    QuaternionRotation.createBasisRotation( Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, Vector3D.POSITIVE_INFINITY, Vector3D.Unit.MINUS_X);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testCreateBasisRotation_invalidArgs_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    QuaternionRotation.createBasisRotation( Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, Vector3D.Unit.PLUS_Y, Vector3D.NEGATIVE_INFINITY);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testCreateBasisRotation_invalidArgs_5_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    QuaternionRotation.createBasisRotation( Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_X);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testCreateBasisRotation_invalidArgs_6_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    QuaternionRotation.createBasisRotation( Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_Y);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testToMatrix_1_oe_1_oe() {
        // act/assert
        // --- x axes
                final UnaryOperator<Vector3D> expected0 = StandardRotations.IDENTITY;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(PLUS_X_DIR, 0.0).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_2_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

                final UnaryOperator<Vector3D> expected0 = StandardRotations.PLUS_X_HALF_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(PLUS_X_DIR, Angle.PI_OVER_TWO).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_3_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
                final UnaryOperator<Vector3D> expected0 = StandardRotations.PLUS_X_HALF_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(MINUS_X_DIR, -Angle.PI_OVER_TWO).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_4_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final UnaryOperator<Vector3D> expected0 = StandardRotations.MINUS_X_HALF_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(MINUS_X_DIR, Angle.PI_OVER_TWO).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_5_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final UnaryOperator<Vector3D> expected0 = StandardRotations.MINUS_X_HALF_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(PLUS_X_DIR, -Angle.PI_OVER_TWO).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_6_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final UnaryOperator<Vector3D> expected0 = StandardRotations.X_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(PLUS_X_DIR, Math.PI).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_7_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final UnaryOperator<Vector3D> expected0 = StandardRotations.X_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(MINUS_X_DIR, Math.PI).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_8_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
                final UnaryOperator<Vector3D> expected0 = StandardRotations.IDENTITY;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(PLUS_Y_DIR, 0.0).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_9_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

                final UnaryOperator<Vector3D> expected0 = StandardRotations.PLUS_Y_HALF_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(PLUS_Y_DIR, Angle.PI_OVER_TWO).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_10_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
                final UnaryOperator<Vector3D> expected0 = StandardRotations.PLUS_Y_HALF_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(MINUS_Y_DIR, -Angle.PI_OVER_TWO).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_11_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final UnaryOperator<Vector3D> expected0 = StandardRotations.MINUS_Y_HALF_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(MINUS_Y_DIR, Angle.PI_OVER_TWO).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_12_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final UnaryOperator<Vector3D> expected0 = StandardRotations.MINUS_Y_HALF_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(PLUS_Y_DIR, -Angle.PI_OVER_TWO).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_13_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final UnaryOperator<Vector3D> expected0 = StandardRotations.Y_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(PLUS_Y_DIR, Math.PI).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_14_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final UnaryOperator<Vector3D> expected0 = StandardRotations.Y_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(MINUS_Y_DIR, Math.PI).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_15_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
                final UnaryOperator<Vector3D> expected0 = StandardRotations.IDENTITY;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(PLUS_Z_DIR, 0.0).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_16_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

                final UnaryOperator<Vector3D> expected0 = StandardRotations.PLUS_Z_HALF_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(PLUS_Z_DIR, Angle.PI_OVER_TWO).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_17_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
                final UnaryOperator<Vector3D> expected0 = StandardRotations.PLUS_Z_HALF_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(MINUS_Z_DIR, -Angle.PI_OVER_TWO).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_18_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final UnaryOperator<Vector3D> expected0 = StandardRotations.MINUS_Z_HALF_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(MINUS_Z_DIR, Angle.PI_OVER_TWO).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_19_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final UnaryOperator<Vector3D> expected0 = StandardRotations.MINUS_Z_HALF_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(PLUS_Z_DIR, -Angle.PI_OVER_TWO).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_20_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

                final UnaryOperator<Vector3D> expected0 = StandardRotations.Z_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(PLUS_Z_DIR, Math.PI).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_21_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
                final UnaryOperator<Vector3D> expected0 = StandardRotations.Z_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(MINUS_Z_DIR, Math.PI).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_22_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- diagonal
                final UnaryOperator<Vector3D> expected0 = StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, TWO_THIRDS_PI).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_23_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- diagonal
        // removed other assertion
                final UnaryOperator<Vector3D> expected0 = StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(MINUS_DIAGONAL, MINUS_TWO_THIRDS_PI).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_24_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- diagonal
        // removed other assertion
        // removed other assertion

                final UnaryOperator<Vector3D> expected0 = StandardRotations.MINUS_DIAGONAL_TWO_THIRDS_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(MINUS_DIAGONAL, TWO_THIRDS_PI).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testToMatrix_25_oe_1_oe() {
        // act/assert
        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- diagonal
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final UnaryOperator<Vector3D> expected0 = StandardRotations.MINUS_DIAGONAL_TWO_THIRDS_PI;
        final AffineTransformMatrix3D transform0 = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, MINUS_TWO_THIRDS_PI).toMatrix();
        assertFnEquals(expected0, transform0);
    }

    @Test
    void testAxisAngleSequenceConversion_relative_eulerSingularities_3_oe_1_oe() {
        // arrange
        final double[] eulerSingularities = {
            0.0,
            Math.PI
        };

        final double angle1 = 0.1;
        final double angle2 = 0.3;

        final AxisReferenceFrame frame = AxisReferenceFrame.RELATIVE;

        for (final AxisSequence axes : getAxes(AxisSequenceType.EULER)) {
            for (final double singularityAngle : eulerSingularities) {

                final AxisAngleSequence inputSeq = new AxisAngleSequence(frame, axes, angle1, singularityAngle, angle2);
                final QuaternionRotation inputQuat = QuaternionRotation.fromAxisAngleSequence(inputSeq);

                // act
                final AxisAngleSequence resultSeq = inputQuat.toAxisAngleSequence(frame, axes);
                final QuaternionRotation resultQuat = QuaternionRotation.fromAxisAngleSequence(resultSeq);

                // assert
                // removed other assertion
                // removed other assertion

                                final double expected0 = singularityAngle;
                final double actual0 = resultSeq.getAngle2();
                final double diff0 = Angle.Rad.WITHIN_MINUS_PI_AND_PI.applyAsDouble(expected0 - actual0);
                        final String msg0 = "Expected " + actual0 + " radians to be equivalent to " + expected0 + " radians; difference is " + diff0;
                
                        Assertions.assertTrue(Math.abs(diff0) < 1e-6, msg0);
    }
    }
    }

    @Test
    void testAxisAngleSequenceConversion_relative_eulerSingularities_4_oe_1_oe() {
        // arrange
        final double[] eulerSingularities = {
            0.0,
            Math.PI
        };

        final double angle1 = 0.1;
        final double angle2 = 0.3;

        final AxisReferenceFrame frame = AxisReferenceFrame.RELATIVE;

        for (final AxisSequence axes : getAxes(AxisSequenceType.EULER)) {
            for (final double singularityAngle : eulerSingularities) {

                final AxisAngleSequence inputSeq = new AxisAngleSequence(frame, axes, angle1, singularityAngle, angle2);
                final QuaternionRotation inputQuat = QuaternionRotation.fromAxisAngleSequence(inputSeq);

                // act
                final AxisAngleSequence resultSeq = inputQuat.toAxisAngleSequence(frame, axes);
                final QuaternionRotation resultQuat = QuaternionRotation.fromAxisAngleSequence(resultSeq);

                // assert
                // removed other assertion
                // removed other assertion

                // removed other assertion
                                final double expected0 = 0.0;
                final double actual0 = resultSeq.getAngle3();
                final double diff0 = Angle.Rad.WITHIN_MINUS_PI_AND_PI.applyAsDouble(expected0 - actual0);
                        final String msg0 = "Expected " + actual0 + " radians to be equivalent to " + expected0 + " radians; difference is " + diff0;
                
                        Assertions.assertTrue(Math.abs(diff0) < 1e-6, msg0);
    }
    }
    }

    @Test
    void testAxisAngleSequenceConversion_absolute_eulerSingularities_3_oe_1_oe() {
        // arrange
        final double[] eulerSingularities = {
            0.0,
            Math.PI
        };

        final double angle1 = 0.1;
        final double angle2 = 0.3;

        final AxisReferenceFrame frame = AxisReferenceFrame.ABSOLUTE;

        for (final AxisSequence axes : getAxes(AxisSequenceType.EULER)) {
            for (final double singularityAngle : eulerSingularities) {

                final AxisAngleSequence inputSeq = new AxisAngleSequence(frame, axes, angle1, singularityAngle, angle2);
                final QuaternionRotation inputQuat = QuaternionRotation.fromAxisAngleSequence(inputSeq);

                // act
                final AxisAngleSequence resultSeq = inputQuat.toAxisAngleSequence(frame, axes);
                final QuaternionRotation resultQuat = QuaternionRotation.fromAxisAngleSequence(resultSeq);

                // assert
                // removed other assertion
                // removed other assertion

                                final double expected0 = 0.0;
                final double actual0 = resultSeq.getAngle1();
                final double diff0 = Angle.Rad.WITHIN_MINUS_PI_AND_PI.applyAsDouble(expected0 - actual0);
                        final String msg0 = "Expected " + actual0 + " radians to be equivalent to " + expected0 + " radians; difference is " + diff0;
                
                        Assertions.assertTrue(Math.abs(diff0) < 1e-6, msg0);
    }
    }
    }

    @Test
    void testAxisAngleSequenceConversion_absolute_eulerSingularities_4_oe_1_oe() {
        // arrange
        final double[] eulerSingularities = {
            0.0,
            Math.PI
        };

        final double angle1 = 0.1;
        final double angle2 = 0.3;

        final AxisReferenceFrame frame = AxisReferenceFrame.ABSOLUTE;

        for (final AxisSequence axes : getAxes(AxisSequenceType.EULER)) {
            for (final double singularityAngle : eulerSingularities) {

                final AxisAngleSequence inputSeq = new AxisAngleSequence(frame, axes, angle1, singularityAngle, angle2);
                final QuaternionRotation inputQuat = QuaternionRotation.fromAxisAngleSequence(inputSeq);

                // act
                final AxisAngleSequence resultSeq = inputQuat.toAxisAngleSequence(frame, axes);
                final QuaternionRotation resultQuat = QuaternionRotation.fromAxisAngleSequence(resultSeq);

                // assert
                // removed other assertion
                // removed other assertion

                // removed other assertion
                                final double expected0 = singularityAngle;
                final double actual0 = resultSeq.getAngle2();
                final double diff0 = Angle.Rad.WITHIN_MINUS_PI_AND_PI.applyAsDouble(expected0 - actual0);
                        final String msg0 = "Expected " + actual0 + " radians to be equivalent to " + expected0 + " radians; difference is " + diff0;
                
                        Assertions.assertTrue(Math.abs(diff0) < 1e-6, msg0);
    }
    }
    }

    @Test
    void testAxisAngleSequenceConversion_relative_taitBryanSingularities_3_oe_1_oe() {
        // arrange
        final double[] taitBryanSingularities = {
            -Angle.PI_OVER_TWO,
            Angle.PI_OVER_TWO
        };

        final double angle1 = 0.1;
        final double angle2 = 0.3;

        final AxisReferenceFrame frame = AxisReferenceFrame.RELATIVE;

        for (final AxisSequence axes : getAxes(AxisSequenceType.TAIT_BRYAN)) {
            for (final double singularityAngle : taitBryanSingularities) {

                final AxisAngleSequence inputSeq = new AxisAngleSequence(frame, axes, angle1, singularityAngle, angle2);
                final QuaternionRotation inputQuat = QuaternionRotation.fromAxisAngleSequence(inputSeq);

                // act
                final AxisAngleSequence resultSeq = inputQuat.toAxisAngleSequence(frame, axes);
                final QuaternionRotation resultQuat = QuaternionRotation.fromAxisAngleSequence(resultSeq);

                // assert
                // removed other assertion
                // removed other assertion

                                final double expected0 = singularityAngle;
                final double actual0 = resultSeq.getAngle2();
                final double diff0 = Angle.Rad.WITHIN_MINUS_PI_AND_PI.applyAsDouble(expected0 - actual0);
                        final String msg0 = "Expected " + actual0 + " radians to be equivalent to " + expected0 + " radians; difference is " + diff0;
                
                        Assertions.assertTrue(Math.abs(diff0) < 1e-6, msg0);
    }
    }
    }

    @Test
    void testAxisAngleSequenceConversion_relative_taitBryanSingularities_4_oe_1_oe() {
        // arrange
        final double[] taitBryanSingularities = {
            -Angle.PI_OVER_TWO,
            Angle.PI_OVER_TWO
        };

        final double angle1 = 0.1;
        final double angle2 = 0.3;

        final AxisReferenceFrame frame = AxisReferenceFrame.RELATIVE;

        for (final AxisSequence axes : getAxes(AxisSequenceType.TAIT_BRYAN)) {
            for (final double singularityAngle : taitBryanSingularities) {

                final AxisAngleSequence inputSeq = new AxisAngleSequence(frame, axes, angle1, singularityAngle, angle2);
                final QuaternionRotation inputQuat = QuaternionRotation.fromAxisAngleSequence(inputSeq);

                // act
                final AxisAngleSequence resultSeq = inputQuat.toAxisAngleSequence(frame, axes);
                final QuaternionRotation resultQuat = QuaternionRotation.fromAxisAngleSequence(resultSeq);

                // assert
                // removed other assertion
                // removed other assertion

                // removed other assertion
                                final double expected0 = 0.0;
                final double actual0 = resultSeq.getAngle3();
                final double diff0 = Angle.Rad.WITHIN_MINUS_PI_AND_PI.applyAsDouble(expected0 - actual0);
                        final String msg0 = "Expected " + actual0 + " radians to be equivalent to " + expected0 + " radians; difference is " + diff0;
                
                        Assertions.assertTrue(Math.abs(diff0) < 1e-6, msg0);
    }
    }
    }

    @Test
    void testAxisAngleSequenceConversion_absolute_taitBryanSingularities_3_oe_1_oe() {
        // arrange
        final double[] taitBryanSingularities = {
            -Angle.PI_OVER_TWO,
            Angle.PI_OVER_TWO
        };

        final double angle1 = 0.1;
        final double angle2 = 0.3;

        final AxisReferenceFrame frame = AxisReferenceFrame.ABSOLUTE;

        for (final AxisSequence axes : getAxes(AxisSequenceType.TAIT_BRYAN)) {
            for (final double singularityAngle : taitBryanSingularities) {

                final AxisAngleSequence inputSeq = new AxisAngleSequence(frame, axes, angle1, singularityAngle, angle2);
                final QuaternionRotation inputQuat = QuaternionRotation.fromAxisAngleSequence(inputSeq);

                // act
                final AxisAngleSequence resultSeq = inputQuat.toAxisAngleSequence(frame, axes);
                final QuaternionRotation resultQuat = QuaternionRotation.fromAxisAngleSequence(resultSeq);

                // assert
                // removed other assertion
                // removed other assertion

                                final double expected0 = 0.0;
                final double actual0 = resultSeq.getAngle1();
                final double diff0 = Angle.Rad.WITHIN_MINUS_PI_AND_PI.applyAsDouble(expected0 - actual0);
                        final String msg0 = "Expected " + actual0 + " radians to be equivalent to " + expected0 + " radians; difference is " + diff0;
                
                        Assertions.assertTrue(Math.abs(diff0) < 1e-6, msg0);
    }
    }
    }

    @Test
    void testAxisAngleSequenceConversion_absolute_taitBryanSingularities_4_oe_1_oe() {
        // arrange
        final double[] taitBryanSingularities = {
            -Angle.PI_OVER_TWO,
            Angle.PI_OVER_TWO
        };

        final double angle1 = 0.1;
        final double angle2 = 0.3;

        final AxisReferenceFrame frame = AxisReferenceFrame.ABSOLUTE;

        for (final AxisSequence axes : getAxes(AxisSequenceType.TAIT_BRYAN)) {
            for (final double singularityAngle : taitBryanSingularities) {

                final AxisAngleSequence inputSeq = new AxisAngleSequence(frame, axes, angle1, singularityAngle, angle2);
                final QuaternionRotation inputQuat = QuaternionRotation.fromAxisAngleSequence(inputSeq);

                // act
                final AxisAngleSequence resultSeq = inputQuat.toAxisAngleSequence(frame, axes);
                final QuaternionRotation resultQuat = QuaternionRotation.fromAxisAngleSequence(resultSeq);

                // assert
                // removed other assertion
                // removed other assertion

                // removed other assertion
                                final double expected0 = singularityAngle;
                final double actual0 = resultSeq.getAngle2();
                final double diff0 = Angle.Rad.WITHIN_MINUS_PI_AND_PI.applyAsDouble(expected0 - actual0);
                        final String msg0 = "Expected " + actual0 + " radians to be equivalent to " + expected0 + " radians; difference is " + diff0;
                
                        Assertions.assertTrue(Math.abs(diff0) < 1e-6, msg0);
    }
    }
    }

@Test
    void testIdentity_1_oe() {
        // act
        final QuaternionRotation q = QuaternionRotation.identity();

        // assert
        assertRotationEquals(StandardRotations.IDENTITY, q);
    }

@Test
    void testFromAxisAngle_apply_1_oe() {
        // act/assert

        // --- x axes
        assertRotationEquals(StandardRotations.IDENTITY, QuaternionRotation.fromAxisAngle(PLUS_X_DIR, 0.0));
    }

@Test
    void testFromAxisAngle_apply_2_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        assertRotationEquals(StandardRotations.PLUS_X_HALF_PI, QuaternionRotation.fromAxisAngle(PLUS_X_DIR, Angle.PI_OVER_TWO));
    }

@Test
    void testFromAxisAngle_apply_3_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.PLUS_X_HALF_PI, QuaternionRotation.fromAxisAngle(MINUS_X_DIR, -Angle.PI_OVER_TWO));
    }

@Test
    void testFromAxisAngle_apply_4_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.MINUS_X_HALF_PI, QuaternionRotation.fromAxisAngle(MINUS_X_DIR, Angle.PI_OVER_TWO));
    }

@Test
    void testFromAxisAngle_apply_5_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.MINUS_X_HALF_PI, QuaternionRotation.fromAxisAngle(PLUS_X_DIR, -Angle.PI_OVER_TWO));
    }

@Test
    void testFromAxisAngle_apply_6_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.X_PI, QuaternionRotation.fromAxisAngle(PLUS_X_DIR, Math.PI));
    }

@Test
    void testFromAxisAngle_apply_7_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.X_PI, QuaternionRotation.fromAxisAngle(MINUS_X_DIR, Math.PI));
    }

@Test
    void testFromAxisAngle_apply_8_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        assertRotationEquals(StandardRotations.IDENTITY, QuaternionRotation.fromAxisAngle(PLUS_Y_DIR, 0.0));
    }

@Test
    void testFromAxisAngle_apply_9_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        assertRotationEquals(StandardRotations.PLUS_Y_HALF_PI, QuaternionRotation.fromAxisAngle(PLUS_Y_DIR, Angle.PI_OVER_TWO));
    }

@Test
    void testFromAxisAngle_apply_10_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.PLUS_Y_HALF_PI, QuaternionRotation.fromAxisAngle(MINUS_Y_DIR, -Angle.PI_OVER_TWO));
    }

@Test
    void testFromAxisAngle_apply_11_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.MINUS_Y_HALF_PI, QuaternionRotation.fromAxisAngle(MINUS_Y_DIR, Angle.PI_OVER_TWO));
    }

@Test
    void testFromAxisAngle_apply_12_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.MINUS_Y_HALF_PI, QuaternionRotation.fromAxisAngle(PLUS_Y_DIR, -Angle.PI_OVER_TWO));
    }

@Test
    void testFromAxisAngle_apply_13_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.Y_PI, QuaternionRotation.fromAxisAngle(PLUS_Y_DIR, Math.PI));
    }

@Test
    void testFromAxisAngle_apply_14_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.Y_PI, QuaternionRotation.fromAxisAngle(MINUS_Y_DIR, Math.PI));
    }

@Test
    void testFromAxisAngle_apply_15_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        assertRotationEquals(StandardRotations.IDENTITY, QuaternionRotation.fromAxisAngle(PLUS_Z_DIR, 0.0));
    }

@Test
    void testFromAxisAngle_apply_16_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        assertRotationEquals(StandardRotations.PLUS_Z_HALF_PI, QuaternionRotation.fromAxisAngle(PLUS_Z_DIR, Angle.PI_OVER_TWO));
    }

@Test
    void testFromAxisAngle_apply_17_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.PLUS_Z_HALF_PI, QuaternionRotation.fromAxisAngle(MINUS_Z_DIR, -Angle.PI_OVER_TWO));
    }

@Test
    void testFromAxisAngle_apply_18_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.MINUS_Z_HALF_PI, QuaternionRotation.fromAxisAngle(MINUS_Z_DIR, Angle.PI_OVER_TWO));
    }

@Test
    void testFromAxisAngle_apply_19_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.MINUS_Z_HALF_PI, QuaternionRotation.fromAxisAngle(PLUS_Z_DIR, -Angle.PI_OVER_TWO));
    }

@Test
    void testFromAxisAngle_apply_20_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.Z_PI, QuaternionRotation.fromAxisAngle(PLUS_Z_DIR, Math.PI));
    }

@Test
    void testFromAxisAngle_apply_21_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.Z_PI, QuaternionRotation.fromAxisAngle(MINUS_Z_DIR, Math.PI));
    }

@Test
    void testFromAxisAngle_apply_22_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- diagonal
        assertRotationEquals(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, TWO_THIRDS_PI));
    }

@Test
    void testFromAxisAngle_apply_23_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- diagonal
        // removed other assertion
        assertRotationEquals(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, QuaternionRotation.fromAxisAngle(MINUS_DIAGONAL, MINUS_TWO_THIRDS_PI));
    }

@Test
    void testFromAxisAngle_apply_24_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- diagonal
        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.MINUS_DIAGONAL_TWO_THIRDS_PI, QuaternionRotation.fromAxisAngle(MINUS_DIAGONAL, TWO_THIRDS_PI));
    }

@Test
    void testFromAxisAngle_apply_25_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- diagonal
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.MINUS_DIAGONAL_TWO_THIRDS_PI, QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, MINUS_TWO_THIRDS_PI));
    }

@Test
    void testFromAxisAngle_invalidAngle_1_oe() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, Double.NaN), IllegalArgumentException.class, "Invalid angle: NaN");
    }

@Test
    void testFromAxisAngle_invalidAngle_2_oe() {
        // act/assert
        // removed other assertion
        GeometryTestUtils.assertThrowsWithMessage(() -> QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, Double.POSITIVE_INFINITY), IllegalArgumentException.class, "Invalid angle: Infinity");
    }

@Test
    void testFromAxisAngle_invalidAngle_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        GeometryTestUtils.assertThrowsWithMessage(() -> QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, Double.NEGATIVE_INFINITY), IllegalArgumentException.class, "Invalid angle: -Infinity");
    }

@Test
    void testInverse_apply_1_oe() {
        // act/assert

        // --- x axes
        assertRotationEquals(StandardRotations.IDENTITY, QuaternionRotation.fromAxisAngle(PLUS_X_DIR, 0.0).inverse());
    }

@Test
    void testInverse_apply_2_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        assertRotationEquals(StandardRotations.PLUS_X_HALF_PI, QuaternionRotation.fromAxisAngle(PLUS_X_DIR, -Angle.PI_OVER_TWO).inverse());
    }

@Test
    void testInverse_apply_3_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.PLUS_X_HALF_PI, QuaternionRotation.fromAxisAngle(MINUS_X_DIR, Angle.PI_OVER_TWO).inverse());
    }

@Test
    void testInverse_apply_4_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.MINUS_X_HALF_PI, QuaternionRotation.fromAxisAngle(MINUS_X_DIR, -Angle.PI_OVER_TWO).inverse());
    }

@Test
    void testInverse_apply_5_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.MINUS_X_HALF_PI, QuaternionRotation.fromAxisAngle(PLUS_X_DIR, Angle.PI_OVER_TWO).inverse());
    }

@Test
    void testInverse_apply_6_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.X_PI, QuaternionRotation.fromAxisAngle(PLUS_X_DIR, Math.PI).inverse());
    }

@Test
    void testInverse_apply_7_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.X_PI, QuaternionRotation.fromAxisAngle(MINUS_X_DIR, Math.PI).inverse());
    }

@Test
    void testInverse_apply_8_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        assertRotationEquals(StandardRotations.IDENTITY, QuaternionRotation.fromAxisAngle(PLUS_Y_DIR, 0.0).inverse());
    }

@Test
    void testInverse_apply_9_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        assertRotationEquals(StandardRotations.PLUS_Y_HALF_PI, QuaternionRotation.fromAxisAngle(PLUS_Y_DIR, -Angle.PI_OVER_TWO).inverse());
    }

@Test
    void testInverse_apply_10_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.PLUS_Y_HALF_PI, QuaternionRotation.fromAxisAngle(MINUS_Y_DIR, Angle.PI_OVER_TWO).inverse());
    }

@Test
    void testInverse_apply_11_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.MINUS_Y_HALF_PI, QuaternionRotation.fromAxisAngle(MINUS_Y_DIR, -Angle.PI_OVER_TWO).inverse());
    }

@Test
    void testInverse_apply_12_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.MINUS_Y_HALF_PI, QuaternionRotation.fromAxisAngle(PLUS_Y_DIR, Angle.PI_OVER_TWO).inverse());
    }

@Test
    void testInverse_apply_13_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.Y_PI, QuaternionRotation.fromAxisAngle(PLUS_Y_DIR, Math.PI).inverse());
    }

@Test
    void testInverse_apply_14_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.Y_PI, QuaternionRotation.fromAxisAngle(MINUS_Y_DIR, Math.PI).inverse());
    }

@Test
    void testInverse_apply_15_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        assertRotationEquals(StandardRotations.IDENTITY, QuaternionRotation.fromAxisAngle(PLUS_Z_DIR, 0.0).inverse());
    }

@Test
    void testInverse_apply_16_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        assertRotationEquals(StandardRotations.PLUS_Z_HALF_PI, QuaternionRotation.fromAxisAngle(PLUS_Z_DIR, -Angle.PI_OVER_TWO).inverse());
    }

@Test
    void testInverse_apply_17_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.PLUS_Z_HALF_PI, QuaternionRotation.fromAxisAngle(MINUS_Z_DIR, Angle.PI_OVER_TWO).inverse());
    }

@Test
    void testInverse_apply_18_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.MINUS_Z_HALF_PI, QuaternionRotation.fromAxisAngle(MINUS_Z_DIR, -Angle.PI_OVER_TWO).inverse());
    }

@Test
    void testInverse_apply_19_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.MINUS_Z_HALF_PI, QuaternionRotation.fromAxisAngle(PLUS_Z_DIR, Angle.PI_OVER_TWO).inverse());
    }

@Test
    void testInverse_apply_20_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.Z_PI, QuaternionRotation.fromAxisAngle(PLUS_Z_DIR, Math.PI).inverse());
    }

@Test
    void testInverse_apply_21_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.Z_PI, QuaternionRotation.fromAxisAngle(MINUS_Z_DIR, Math.PI).inverse());
    }

@Test
    void testInverse_apply_22_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- diagonal
        assertRotationEquals(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, MINUS_TWO_THIRDS_PI).inverse());
    }

@Test
    void testInverse_apply_23_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- diagonal
        // removed other assertion
        assertRotationEquals(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, QuaternionRotation.fromAxisAngle(MINUS_DIAGONAL, TWO_THIRDS_PI).inverse());
    }

@Test
    void testInverse_apply_24_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- diagonal
        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.MINUS_DIAGONAL_TWO_THIRDS_PI, QuaternionRotation.fromAxisAngle(MINUS_DIAGONAL, MINUS_TWO_THIRDS_PI).inverse());
    }

@Test
    void testInverse_apply_25_oe() {
        // act/assert

        // --- x axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- y axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- z axes
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // --- diagonal
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.MINUS_DIAGONAL_TWO_THIRDS_PI, QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, TWO_THIRDS_PI).inverse());
    }

@Test
    void testMultiply_sameAxis_simple_3_oe() {
        // arrange
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, 0.1 * Math.PI);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, 0.4 * Math.PI);

        // act
        final QuaternionRotation result = q1.multiply(q2);

        // assert
        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.PLUS_X_HALF_PI, result);
    }

@Test
    void testMultiply_sameAxis_multiple_3_oe() {
        // arrange
        final double oneThird = 1.0 / 3.0;
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, 0.1 * Math.PI);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, oneThird * Math.PI);
        final QuaternionRotation q3 = QuaternionRotation.fromAxisAngle(MINUS_DIAGONAL, 0.4 * Math.PI);
        final QuaternionRotation q4 = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, 0.3 * Math.PI);
        final QuaternionRotation q5 = QuaternionRotation.fromAxisAngle(MINUS_DIAGONAL, -oneThird * Math.PI);

        // act
        final QuaternionRotation result = q1.multiply(q2).multiply(q3).multiply(q4).multiply(q5);

        // assert
        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, result);
    }

@Test
    void testMultiply_differentAxes_3_oe() {
        // arrange
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, Angle.PI_OVER_TWO);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, Angle.PI_OVER_TWO);

        // act
        final QuaternionRotation result = q1.multiply(q2);

        // assert
        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, result);
    }

@Test
    void testMultiply_differentAxes_4_oe() {
        // arrange
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, Angle.PI_OVER_TWO);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, Angle.PI_OVER_TWO);

        // act
        final QuaternionRotation result = q1.multiply(q2);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertRotationEquals(v -> { final Vector3D temp = StandardRotations.PLUS_Y_HALF_PI.apply(v); return StandardRotations.PLUS_X_HALF_PI.apply(temp); }, result);
    }

@Test
    void testMultiply_orderOfOperations_1_oe() {
        // arrange
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, Angle.PI_OVER_TWO);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, Math.PI);
        final QuaternionRotation q3 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.MINUS_Z, Angle.PI_OVER_TWO);

        // act
        final QuaternionRotation result = q3.multiply(q2).multiply(q1);

        // assert
        assertRotationEquals(v -> { Vector3D temp = StandardRotations.PLUS_X_HALF_PI.apply(v); temp = StandardRotations.Y_PI.apply(temp); return StandardRotations.MINUS_Z_HALF_PI.apply(temp); }, result);
    }

@Test
    void testMultiply_numericalStability_3_oe() {
        // arrange
        final int slices = 1024;
        final double delta = (8.0 * Math.PI / 3.0) / slices;

        QuaternionRotation q = QuaternionRotation.identity();

        final UniformRandomProvider rand = RandomSource.create(RandomSource.JDK, 2L);

        // act
        for (int i = 0; i < slices; ++i) {
            final double angle = rand.nextDouble();
            final QuaternionRotation forward = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, angle);
            final QuaternionRotation backward = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, delta - angle);

            q = q.multiply(forward).multiply(backward);
        }

        // assert
        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, q);
    }

@Test
    void testPremultiply_sameAxis_simple_3_oe() {
        // arrange
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, 0.1 * Math.PI);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, 0.4 * Math.PI);

        // act
        final QuaternionRotation result = q1.premultiply(q2);

        // assert
        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.PLUS_X_HALF_PI, result);
    }

@Test
    void testPremultiply_sameAxis_multiple_3_oe() {
        // arrange
        final double oneThird = 1.0 / 3.0;
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, 0.1 * Math.PI);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, oneThird * Math.PI);
        final QuaternionRotation q3 = QuaternionRotation.fromAxisAngle(MINUS_DIAGONAL, 0.4 * Math.PI);
        final QuaternionRotation q4 = QuaternionRotation.fromAxisAngle(PLUS_DIAGONAL, 0.3 * Math.PI);
        final QuaternionRotation q5 = QuaternionRotation.fromAxisAngle(MINUS_DIAGONAL, -oneThird * Math.PI);

        // act
        final QuaternionRotation result = q1.premultiply(q2).premultiply(q3).premultiply(q4).premultiply(q5);

        // assert
        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, result);
    }

@Test
    void testPremultiply_differentAxes_3_oe() {
        // arrange
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, Angle.PI_OVER_TWO);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, Angle.PI_OVER_TWO);

        // act
        final QuaternionRotation result = q2.premultiply(q1);

        // assert
        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, result);
    }

@Test
    void testPremultiply_differentAxes_4_oe() {
        // arrange
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, Angle.PI_OVER_TWO);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, Angle.PI_OVER_TWO);

        // act
        final QuaternionRotation result = q2.premultiply(q1);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertRotationEquals(v -> { final Vector3D temp = StandardRotations.PLUS_Y_HALF_PI.apply(v); return StandardRotations.PLUS_X_HALF_PI.apply(temp); }, result);
    }

@Test
    void testPremultiply_orderOfOperations_1_oe() {
        // arrange
        final QuaternionRotation q1 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_X, Angle.PI_OVER_TWO);
        final QuaternionRotation q2 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, Math.PI);
        final QuaternionRotation q3 = QuaternionRotation.fromAxisAngle(Vector3D.Unit.MINUS_Z, Angle.PI_OVER_TWO);

        // act
        final QuaternionRotation result = q1.premultiply(q2).premultiply(q3);

        // assert
        assertRotationEquals(v -> { Vector3D temp = StandardRotations.PLUS_X_HALF_PI.apply(v); temp = StandardRotations.Y_PI.apply(temp); return StandardRotations.MINUS_Z_HALF_PI.apply(temp); }, result);
    }

@Test
    void testCreateBasisRotation_simple_5_oe() {
        // arrange
        final Vector3D u1 = Vector3D.Unit.PLUS_X;
        final Vector3D u2 = Vector3D.Unit.PLUS_Y;

        final Vector3D v1 = Vector3D.Unit.PLUS_Y;
        final Vector3D v2 = Vector3D.Unit.MINUS_X;

        // act
        final QuaternionRotation q = QuaternionRotation.createBasisRotation(u1, u2, v1, v2);

        // assert
        final QuaternionRotation qInv = q.inverse();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.PLUS_Z_HALF_PI, q);
    }

@Test
    void testCreateBasisRotation_diagonalAxis_5_oe() {
        // arrange
        final Vector3D u1 = Vector3D.Unit.PLUS_X;
        final Vector3D u2 = Vector3D.Unit.PLUS_Y;

        final Vector3D v1 = Vector3D.Unit.PLUS_Y;
        final Vector3D v2 = Vector3D.Unit.PLUS_Z;

        // act
        final QuaternionRotation q = QuaternionRotation.createBasisRotation(u1, u2, v1, v2);

        // assert
        final QuaternionRotation qInv = q.inverse();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI, q);
    }

@Test
    void testCreateBasisRotation_diagonalAxis_6_oe() {
        // arrange
        final Vector3D u1 = Vector3D.Unit.PLUS_X;
        final Vector3D u2 = Vector3D.Unit.PLUS_Y;

        final Vector3D v1 = Vector3D.Unit.PLUS_Y;
        final Vector3D v2 = Vector3D.Unit.PLUS_Z;

        // act
        final QuaternionRotation q = QuaternionRotation.createBasisRotation(u1, u2, v1, v2);

        // assert
        final QuaternionRotation qInv = q.inverse();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertRotationEquals(StandardRotations.MINUS_DIAGONAL_TWO_THIRDS_PI, q.inverse());
    }

@Test
    void testCreateBasisRotation_identity_5_oe() {
        // arrange
        final Vector3D u1 = Vector3D.Unit.PLUS_X;
        final Vector3D u2 = Vector3D.Unit.PLUS_Y;

        // act
        final QuaternionRotation q = QuaternionRotation.createBasisRotation(u1, u2, u1, u2);

        // assert
        final QuaternionRotation qInv = q.inverse();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.IDENTITY, q);
    }

@Test
    void testCreateBasisRotation_equivalentBases_5_oe() {
        // arrange
        final Vector3D u1 = Vector3D.of(2, 0, 0);
        final Vector3D u2 = Vector3D.of(0, 3, 0);

        final Vector3D v1 = Vector3D.of(4, 0, 0);
        final Vector3D v2 = Vector3D.of(0, 5, 0);

        // act
        final QuaternionRotation q = QuaternionRotation.createBasisRotation(u1, u2, v1, v2);

        // assert
        final QuaternionRotation qInv = q.inverse();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.IDENTITY, q);
    }

@Test
    void testCreateBasisRotation_nonOrthogonalVectors_5_oe() {
        // arrange
        final Vector3D u1 = Vector3D.of(2, 0, 0);
        final Vector3D u2 = Vector3D.of(1, 0.5, 0);

        final Vector3D v1 = Vector3D.of(0, 1.5, 0);
        final Vector3D v2 = Vector3D.of(-1, 1.5, 0);

        // act
        final QuaternionRotation q = QuaternionRotation.createBasisRotation(u1, u2, v1, v2);

        // assert
        final QuaternionRotation qInv = q.inverse();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertRotationEquals(StandardRotations.PLUS_Z_HALF_PI, q);
    }

@Test
    void testFromEulerAngles_identity_1_oe() {
        for (final AxisSequence axes : AxisSequence.values()) {

            // act/assert
            assertRotationEquals(StandardRotations.IDENTITY, QuaternionRotation.fromAxisAngleSequence(AxisAngleSequence.createRelative(axes, 0, 0, 0)));
    }
    }

@Test
    void testFromEulerAngles_identity_2_oe() {
        for (final AxisSequence axes : AxisSequence.values()) {

            // act/assert
            // removed other assertion
            assertRotationEquals(StandardRotations.IDENTITY, QuaternionRotation.fromAxisAngleSequence(AxisAngleSequence.createRelative(axes, Angle.TWO_PI, Angle.TWO_PI, Angle.TWO_PI)));
    }
    }

@Test
    void testFromEulerAngles_identity_3_oe() {
        for (final AxisSequence axes : AxisSequence.values()) {

            // act/assert
            // removed other assertion
            // removed other assertion

            assertRotationEquals(StandardRotations.IDENTITY, QuaternionRotation.fromAxisAngleSequence(AxisAngleSequence.createAbsolute(axes, 0, 0, 0)));
    }
    }

@Test
    void testFromEulerAngles_identity_4_oe() {
        for (final AxisSequence axes : AxisSequence.values()) {

            // act/assert
            // removed other assertion
            // removed other assertion

            // removed other assertion
            assertRotationEquals(StandardRotations.IDENTITY, QuaternionRotation.fromAxisAngleSequence(AxisAngleSequence.createAbsolute(axes, Angle.TWO_PI, Angle.TWO_PI, Angle.TWO_PI)));
    }
    }

}
