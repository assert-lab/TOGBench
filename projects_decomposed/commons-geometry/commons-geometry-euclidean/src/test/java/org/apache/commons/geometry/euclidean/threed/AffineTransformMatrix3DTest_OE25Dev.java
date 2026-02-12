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

import java.util.function.UnaryOperator;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils.PermuteCallback3D;
import org.apache.commons.geometry.euclidean.threed.rotation.QuaternionRotation;
import org.apache.commons.geometry.euclidean.threed.rotation.StandardRotations;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AffineTransformMatrix3DTest_OE25Dev {

    private static final double EPS = 1e-12;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(EPS);

    @Test
    void testOf_invalidDimensions() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> AffineTransformMatrix3D.of(1, 2),
                IllegalArgumentException.class, "Dimension mismatch: 2 != 12");
    }

    @Test
    void testApply_identity() {
        // arrange
        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity();

        // act/assert
        runWithCoordinates((x, y, z) -> {
            final Vector3D v = Vector3D.of(x, y, z);

            EuclideanTestUtils.assertCoordinatesEqual(v, transform.apply(v), EPS);
        });
    }

    @Test
    void testApply_translate() {
        // arrange
        final Vector3D translation = Vector3D.of(1.1, -Math.PI, 5.5);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity()
                .translate(translation);

        // act/assert
        runWithCoordinates((x, y, z) -> {
            final Vector3D vec = Vector3D.of(x, y, z);

            final Vector3D expectedVec = vec.add(translation);

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.apply(vec), EPS);
        });
    }

    @Test
    void testApply_scale() {
        // arrange
        final Vector3D factors = Vector3D.of(2.0, -3.0, 4.0);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity()
                .scale(factors);

        // act/assert
        runWithCoordinates((x, y, z) -> {
            final Vector3D vec = Vector3D.of(x, y, z);

            final Vector3D expectedVec = Vector3D.of(factors.getX() * x, factors.getY() * y, factors.getZ() * z);

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.apply(vec), EPS);
        });
    }

    @Test
    void testApply_translateThenScale() {
        // arrange
        final Vector3D translation = Vector3D.of(-2.0, -3.0, -4.0);
        final Vector3D scale = Vector3D.of(5.0, 6.0, 7.0);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity()
                .translate(translation)
                .scale(scale);

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-5, -12, -21), transform.apply(Vector3D.of(1, 1, 1)), EPS);

        runWithCoordinates((x, y, z) -> {
            final Vector3D vec = Vector3D.of(x, y, z);

            final Vector3D expectedVec = Vector3D.of(
                        (x + translation.getX()) * scale.getX(),
                        (y + translation.getY()) * scale.getY(),
                        (z + translation.getZ()) * scale.getZ()
                    );

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.apply(vec), EPS);
        });
    }

    @Test
    void testApply_scaleThenTranslate() {
        // arrange
        final Vector3D scale = Vector3D.of(5.0, 6.0, 7.0);
        final Vector3D translation = Vector3D.of(-2.0, -3.0, -4.0);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity()
                .scale(scale)
                .translate(translation);

        // act/assert
        runWithCoordinates((x, y, z) -> {
            final Vector3D vec = Vector3D.of(x, y, z);

            final Vector3D expectedVec = Vector3D.of(
                        (x * scale.getX()) + translation.getX(),
                        (y * scale.getY()) + translation.getY(),
                        (z * scale.getZ()) + translation.getZ()
                    );

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.apply(vec), EPS);
        });
    }

    @Test
    void testApply_rotate() {
        // arrange
        final QuaternionRotation rotation = QuaternionRotation.fromAxisAngle(Vector3D.of(1, 1, 1), 2.0 * Math.PI / 3.0);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity().rotate(rotation);

        // act/assert
        runWithCoordinates((x, y, z) -> {
            final Vector3D vec = Vector3D.of(x, y, z);

            final Vector3D expectedVec = StandardRotations.PLUS_DIAGONAL_TWO_THIRDS_PI.apply(vec);

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.apply(vec), EPS);
        });
    }

    @Test
    void testApply_rotate_aroundCenter() {
        // arrange
        final double scaleFactor = 2;
        final Vector3D center = Vector3D.of(3, -4, 5);
        final QuaternionRotation rotation = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Z, Angle.PI_OVER_TWO);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity()
                .scale(scaleFactor)
                .rotate(center, rotation);

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(3, -3, 2), transform.apply(Vector3D.of(2, -2, 1)), EPS);

        runWithCoordinates((x, y, z) -> {
            final Vector3D vec = Vector3D.of(x, y, z);

            final Vector3D expectedVec = StandardRotations.PLUS_Z_HALF_PI.apply(vec.multiply(scaleFactor).subtract(center)).add(center);

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.apply(vec), EPS);
        });
    }

    @Test
    void testApplyXYZ() {
        // arrange
        final double scaleFactor = 2;
        final Vector3D center = Vector3D.of(3, -4, 5);
        final QuaternionRotation rotation = QuaternionRotation.fromAxisAngle(Vector3D.of(0.5, 1, 1), 2 * Math.PI / 3);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity()
                .scale(scaleFactor)
                .rotate(center, rotation);

        // act/assert
        runWithCoordinates((x, y, z) -> {
            final Vector3D vec = Vector3D.of(x, y, z);
            final Vector3D expectedVec = rotation.apply(vec.multiply(scaleFactor).subtract(center)).add(center);

            Assertions.assertEquals(expectedVec.getX(), transform.applyX(x, y, z), EPS);
            Assertions.assertEquals(expectedVec.getY(), transform.applyY(x, y, z), EPS);
            Assertions.assertEquals(expectedVec.getZ(), transform.applyZ(x, y, z), EPS);
        });
    }

    @Test
    void testApplyVector_identity() {
        // arrange
        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity();

        // act/assert
        runWithCoordinates((x, y, z) -> {
            final Vector3D v = Vector3D.of(x, y, z);

            EuclideanTestUtils.assertCoordinatesEqual(v, transform.applyVector(v), EPS);
        });
    }

    @Test
    void testApplyVector_translate() {
        // arrange
        final Vector3D translation = Vector3D.of(1.1, -Math.PI, 5.5);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity()
                .translate(translation);

        // act/assert
        runWithCoordinates((x, y, z) -> {
            final Vector3D vec = Vector3D.of(x, y, z);

            EuclideanTestUtils.assertCoordinatesEqual(vec, transform.applyVector(vec), EPS);
        });
    }

    @Test
    void testApplyVector_scale() {
        // arrange
        final Vector3D factors = Vector3D.of(2.0, -3.0, 4.0);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity()
                .scale(factors);

        // act/assert
        runWithCoordinates((x, y, z) -> {
            final Vector3D vec = Vector3D.of(x, y, z);

            final Vector3D expectedVec = Vector3D.of(factors.getX() * x, factors.getY() * y, factors.getZ() * z);

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.applyVector(vec), EPS);
        });
    }

    @Test
    void testApplyVector_representsDisplacement() {
        // arrange
        final Vector3D p1 = Vector3D.of(1, 2, 3);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity()
                .scale(1.5)
                .translate(4, 6, 5)
                .rotate(QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Z, Angle.PI_OVER_TWO));

        // act/assert
        runWithCoordinates((x, y, z) -> {
            final Vector3D p2 = Vector3D.of(x, y, z);
            final Vector3D input = p1.subtract(p2);

            final Vector3D expected = transform.apply(p1).subtract(transform.apply(p2));

            EuclideanTestUtils.assertCoordinatesEqual(expected, transform.applyVector(input), EPS);
        });
    }

    @Test
    void testApplyVectorXYZ() {
        // arrange
        final Vector3D p1 = Vector3D.of(1, 2, 3);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity()
                .scale(1.5)
                .translate(4, 6, 5)
                .rotate(QuaternionRotation.fromAxisAngle(Vector3D.of(0.5, 1, 1), Angle.PI_OVER_TWO));

        // act/assert
        runWithCoordinates((x, y, z) -> {
            final Vector3D p2 = p1.add(Vector3D.of(x, y, z));

            final Vector3D expected = transform.apply(p1).vectorTo(transform.apply(p2));

            Assertions.assertEquals(expected.getX(), transform.applyVectorX(x, y, z), EPS);
            Assertions.assertEquals(expected.getY(), transform.applyVectorY(x, y, z), EPS);
            Assertions.assertEquals(expected.getZ(), transform.applyVectorZ(x, y, z), EPS);
        });
    }

    @Test
    void testApplyDirection_identity() {
        // arrange
        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity();

        // act/assert
        EuclideanTestUtils.permuteSkipZero(-5, 5, 0.5, (x, y, z) -> {
            final Vector3D v = Vector3D.of(x, y, z);

            EuclideanTestUtils.assertCoordinatesEqual(v.normalize(), transform.applyDirection(v), EPS);
        });
    }

    @Test
    void testApplyDirection_translate() {
        // arrange
        final Vector3D translation = Vector3D.of(1.1, -Math.PI, 5.5);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity()
                .translate(translation);

        // act/assert
        EuclideanTestUtils.permuteSkipZero(-5, 5, 0.5, (x, y, z) -> {
            final Vector3D vec = Vector3D.of(x, y, z);

            EuclideanTestUtils.assertCoordinatesEqual(vec.normalize(), transform.applyDirection(vec), EPS);
        });
    }

    @Test
    void testApplyDirection_scale() {
        // arrange
        final Vector3D factors = Vector3D.of(2.0, -3.0, 4.0);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity()
                .scale(factors);

        // act/assert
        EuclideanTestUtils.permuteSkipZero(-5, 5, 0.5, (x, y, z) -> {
            final Vector3D vec = Vector3D.of(x, y, z);

            final Vector3D expectedVec = Vector3D.of(factors.getX() * x, factors.getY() * y, factors.getZ() * z).normalize();

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.applyDirection(vec), EPS);
        });
    }

    @Test
    void testApplyDirection_representsNormalizedDisplacement() {
        // arrange
        final Vector3D p1 = Vector3D.of(1, 2, 3);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.identity()
                .scale(1.5)
                .translate(4, 6, 5)
                .rotate(QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Z, Angle.PI_OVER_TWO));

        // act/assert
        runWithCoordinates((x, y, z) -> {
            final Vector3D p2 = Vector3D.of(x, y, z);
            final Vector3D input = p1.subtract(p2);

            final Vector3D expected = transform.apply(p1).subtract(transform.apply(p2)).normalize();

            EuclideanTestUtils.assertCoordinatesEqual(expected, transform.applyDirection(input), EPS);
        });
    }

    @Test
    void testMultiply_combinesTransformOperations() {
        // arrange
        final Vector3D translation1 = Vector3D.of(1, 2, 3);
        final double scale = 2.0;
        final Vector3D translation2 = Vector3D.of(4, 5, 6);

        final AffineTransformMatrix3D a = AffineTransformMatrix3D.createTranslation(translation1);
        final AffineTransformMatrix3D b = AffineTransformMatrix3D.createScale(scale);
        final AffineTransformMatrix3D c = AffineTransformMatrix3D.identity();
        final AffineTransformMatrix3D d = AffineTransformMatrix3D.createTranslation(translation2);

        // act
        final AffineTransformMatrix3D transform = d.multiply(c).multiply(b).multiply(a);

        // assert
        runWithCoordinates((x, y, z) -> {
            final Vector3D vec = Vector3D.of(x, y, z);

            final Vector3D expectedVec = vec
                    .add(translation1)
                    .multiply(scale)
                    .add(translation2);

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.apply(vec), EPS);
        });
    }

    @Test
    void testPremultiply_combinesTransformOperations() {
        // arrange
        final Vector3D translation1 = Vector3D.of(1, 2, 3);
        final double scale = 2.0;
        final Vector3D translation2 = Vector3D.of(4, 5, 6);

        final AffineTransformMatrix3D a = AffineTransformMatrix3D.createTranslation(translation1);
        final AffineTransformMatrix3D b = AffineTransformMatrix3D.createScale(scale);
        final AffineTransformMatrix3D c = AffineTransformMatrix3D.identity();
        final AffineTransformMatrix3D d = AffineTransformMatrix3D.createTranslation(translation2);

        // act
        final AffineTransformMatrix3D transform = a.premultiply(b).premultiply(c).premultiply(d);

        // assert
        runWithCoordinates((x, y, z) -> {
            final Vector3D vec = Vector3D.of(x, y, z);

            final Vector3D expectedVec = vec
                    .add(translation1)
                    .multiply(scale)
                    .add(translation2);

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.apply(vec), EPS);
        });
    }

    @Test
    void testInverse_undoesOriginalTransform() {
        // arrange
        final Vector3D v1 = Vector3D.ZERO;
        final Vector3D v2 = Vector3D.Unit.PLUS_X;
        final Vector3D v3 = Vector3D.of(1, 1, 1);
        final Vector3D v4 = Vector3D.of(-2, 3, 4);

        final Vector3D center = Vector3D.of(1, 2, 3);
        final QuaternionRotation rotation = QuaternionRotation.fromAxisAngle(Vector3D.of(1, 2, 3), 0.25);

        // act/assert
        runWithCoordinates((x, y, z) -> {
            final AffineTransformMatrix3D transform = AffineTransformMatrix3D
                        .createTranslation(x, y, z)
                        .scale(2, 3, 4)
                        .rotate(center, rotation)
                        .translate(x / 3, y / 3, z / 3);

            final AffineTransformMatrix3D inverse = transform.inverse();

            EuclideanTestUtils.assertCoordinatesEqual(v1, inverse.apply(transform.apply(v1)), EPS);
            EuclideanTestUtils.assertCoordinatesEqual(v2, inverse.apply(transform.apply(v2)), EPS);
            EuclideanTestUtils.assertCoordinatesEqual(v3, inverse.apply(transform.apply(v3)), EPS);
            EuclideanTestUtils.assertCoordinatesEqual(v4, inverse.apply(transform.apply(v4)), EPS);
        });
    }

    @Test
    void testInverse_nonInvertible() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> AffineTransformMatrix3D.of(
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0).inverse(), IllegalStateException.class, "Matrix is not invertible; matrix determinant is 0.0");

        GeometryTestUtils.assertThrowsWithMessage(() -> AffineTransformMatrix3D.of(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, Double.NaN, 0).inverse(), IllegalStateException.class, "Matrix is not invertible; matrix determinant is NaN");

        GeometryTestUtils.assertThrowsWithMessage(() -> AffineTransformMatrix3D.of(
                1, 0, 0, 0,
                0, Double.NEGATIVE_INFINITY, 0, 0,
                0, 0, 1, 0).inverse(), IllegalStateException.class, "Matrix is not invertible; matrix determinant is NaN");

        GeometryTestUtils.assertThrowsWithMessage(() -> AffineTransformMatrix3D.of(
                Double.POSITIVE_INFINITY, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0).inverse(), IllegalStateException.class, "Matrix is not invertible; matrix determinant is NaN");

        GeometryTestUtils.assertThrowsWithMessage(() -> AffineTransformMatrix3D.of(
                1, 0, 0, Double.NaN,
                0, 1, 0, 0,
                0, 0, 1, 0).inverse(), IllegalStateException.class, "Matrix is not invertible; invalid matrix element: NaN");

        GeometryTestUtils.assertThrowsWithMessage(() -> AffineTransformMatrix3D.of(
                1, 0, 0, 0,
                0, 1, 0, Double.POSITIVE_INFINITY,
                0, 0, 1, 0).inverse(), IllegalStateException.class, "Matrix is not invertible; invalid matrix element: Infinity");

        GeometryTestUtils.assertThrowsWithMessage(() -> AffineTransformMatrix3D.of(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, Double.NEGATIVE_INFINITY).inverse(), IllegalStateException.class, "Matrix is not invertible; invalid matrix element: -Infinity");
    }

    @Test
    void testNormalTransform() {
        // act/assert
        checkNormalTransform(AffineTransformMatrix3D.identity());

        checkNormalTransform(AffineTransformMatrix3D.createTranslation(2, 3, 4));
        checkNormalTransform(AffineTransformMatrix3D.createTranslation(-3, -4, -5));

        checkNormalTransform(AffineTransformMatrix3D.createScale(2, 5, 0.5));
        checkNormalTransform(AffineTransformMatrix3D.createScale(-3, 4, 2));
        checkNormalTransform(AffineTransformMatrix3D.createScale(-0.1, -0.5, 0.8));
        checkNormalTransform(AffineTransformMatrix3D.createScale(-2, -5, -8));

        final QuaternionRotation rotA = QuaternionRotation.fromAxisAngle(Vector3D.of(2, 3, 4), 0.75 * Math.PI);
        final QuaternionRotation rotB = QuaternionRotation.fromAxisAngle(Vector3D.of(-1, 1, -1), 1.75 * Math.PI);

        checkNormalTransform(AffineTransformMatrix3D.createRotation(Vector3D.of(1, 1, 1), rotA));
        checkNormalTransform(AffineTransformMatrix3D.createRotation(Vector3D.of(-1, -1, -1), rotB));

        checkNormalTransform(AffineTransformMatrix3D.createTranslation(2, 3, 4)
                .scale(7, 5, 4)
                .rotate(rotA));
        checkNormalTransform(AffineTransformMatrix3D.createRotation(Vector3D.ZERO, rotB)
                .translate(7, 5, 4)
                .rotate(rotA)
                .scale(2, 3, 0.5));
    }

    private void checkNormalTransform(final AffineTransformMatrix3D transform) {
        final AffineTransformMatrix3D normalTransform = transform.normalTransform();

        final Vector3D p1 = Vector3D.of(-0.25, 0.75, 0.5);
        final Vector3D p2 = Vector3D.of(0.5, -0.75, 0.25);

        final Vector3D t1 = transform.apply(p1);
        final Vector3D t2 = transform.apply(p2);

        EuclideanTestUtils.permute(-10, 10, 1, (x, y, z) -> {
            final Vector3D p3 = Vector3D.of(x, y, z);
            final Vector3D n = Planes.fromPoints(p1, p2, p3, TEST_PRECISION).getNormal();

            final Vector3D t3 = transform.apply(p3);

            final Plane tPlane = transform.preservesOrientation() ?
                    Planes.fromPoints(t1, t2, t3, TEST_PRECISION) :
                    Planes.fromPoints(t1, t3, t2, TEST_PRECISION);
            final Vector3D expected = tPlane.getNormal();

            final Vector3D actual = normalTransform.apply(n).normalize();

            EuclideanTestUtils.assertCoordinatesEqual(expected, actual, EPS);
        });
    }

    @Test
    void testToString() {
        // arrange
        final AffineTransformMatrix3D a = AffineTransformMatrix3D.of(
                    1, 2, 3, 4,
                    5, 6, 7, 8,
                    9, 10, 11, 12
                );

        // act
        final String result = a.toString();

        // assert
        Assertions.assertEquals("[ 1.0,2.0,3.0,4.0;" + "5.0,6.0,7.0,8.0;" + "9.0,10.0,11.0,12.0 ]",result);
    }

    /**
     * Run the given test callback with a wide range of (x, y, z) inputs.
     * @param test
     */
    private static void runWithCoordinates(final PermuteCallback3D test) {
        EuclideanTestUtils.permute(-1e-2, 1e-2, 5e-3, test);
        EuclideanTestUtils.permute(-1e2, 1e2, 5, test);
    }

    @Test
    void testFrom_invalidFunction_1_oe() {
        // act/assert
        try {
    AffineTransformMatrix3D.from(v -> v.multiply(0));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testApplyDirection_illegalNorm_1_oe() {
        // act/assert
        try {
    AffineTransformMatrix3D.createScale(1, 0, 1).applyDirection(Vector3D.Unit.PLUS_Y);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testApplyDirection_illegalNorm_2_oe() {
        // act/assert
        // removed other assertion
        try {
    AffineTransformMatrix3D.createScale(2).applyDirection(Vector3D.ZERO);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testNormalTransform_nonInvertible_1_oe() {
        // act/assert
        try {
    AffineTransformMatrix3D.createScale(0).normalTransform();
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

}
