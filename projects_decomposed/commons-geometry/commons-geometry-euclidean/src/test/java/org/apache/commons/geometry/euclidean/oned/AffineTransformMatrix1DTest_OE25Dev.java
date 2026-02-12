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
package org.apache.commons.geometry.euclidean.oned;

import java.util.function.UnaryOperator;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AffineTransformMatrix1DTest_OE25Dev {

    private static final double EPS = 1e-12;

    @Test
    void testOf_invalidDimensions() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> AffineTransformMatrix1D.of(1),
                IllegalArgumentException.class, "Dimension mismatch: 1 != 2");
    }

    @Test
    void testApply_identity() {
        // arrange
        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.identity();

        // act/assert
        runWithCoordinates(x -> {
            final Vector1D v = Vector1D.of(x);

            EuclideanTestUtils.assertCoordinatesEqual(v, transform.apply(v), EPS);
        });
    }

    @Test
    void testApply_translate() {
        // arrange
        final Vector1D translation = Vector1D.of(-Math.PI);

        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.identity()
                .translate(translation);

        // act/assert
        runWithCoordinates(x -> {
            final Vector1D vec = Vector1D.of(x);

            final Vector1D expectedVec = vec.add(translation);

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.apply(vec), EPS);
        });
    }

    @Test
    void testApply_scale() {
        // arrange
        final Vector1D factor = Vector1D.of(2.0);

        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.identity()
                .scale(factor);

        // act/assert
        runWithCoordinates(x -> {
            final Vector1D vec = Vector1D.of(x);

            final Vector1D expectedVec = Vector1D.of(factor.getX() * x);

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.apply(vec), EPS);
        });
    }

    @Test
    void testApply_translateThenScale() {
        // arrange
        final Vector1D translation = Vector1D.of(-2.0);
        final Vector1D scale = Vector1D.of(5.0);

        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.identity()
                .translate(translation)
                .scale(scale);

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector1D.of(-5), transform.apply(Vector1D.of(1)), EPS);

        runWithCoordinates(x -> {
            final Vector1D vec = Vector1D.of(x);

            final Vector1D expectedVec = Vector1D.of(
                        (x + translation.getX()) * scale.getX()
                    );

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.apply(vec), EPS);
        });
    }

    @Test
    void testApply_scaleThenTranslate() {
        // arrange
        final Vector1D scale = Vector1D.of(5.0);
        final Vector1D translation = Vector1D.of(-2.0);

        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.identity()
                .scale(scale)
                .translate(translation);

        // act/assert
        runWithCoordinates(x -> {
            final Vector1D vec = Vector1D.of(x);

            final Vector1D expectedVec = Vector1D.of(
                        (x * scale.getX()) + translation.getX()
                    );

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.apply(vec), EPS);
        });
    }

    @Test
    void testApplyX() {
        // arrange
        final Vector1D translation = Vector1D.of(-2.0);
        final Vector1D scale = Vector1D.of(5.0);

        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.identity()
                .translate(translation)
                .scale(scale);

        // act/assert
        runWithCoordinates(x -> {
            final double expected = (x + translation.getX()) * scale.getX();

            Assertions.assertEquals(expected, transform.applyX(x), EPS);
        });
    }

    @Test
    void testApplyVector_identity() {
        // arrange
        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.identity();

        // act/assert
        runWithCoordinates(x -> {
            final Vector1D v = Vector1D.of(x);

            EuclideanTestUtils.assertCoordinatesEqual(v, transform.applyVector(v), EPS);
        });
    }

    @Test
    void testApplyVector_translate() {
        // arrange
        final Vector1D translation = Vector1D.of(-Math.PI);

        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.identity()
                .translate(translation);

        // act/assert
        runWithCoordinates(x -> {
            final Vector1D vec = Vector1D.of(x);

            EuclideanTestUtils.assertCoordinatesEqual(vec, transform.applyVector(vec), EPS);
        });
    }

    @Test
    void testApplyVector_scale() {
        // arrange
        final Vector1D factor = Vector1D.of(2.0);

        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.identity()
                .scale(factor);

        // act/assert
        runWithCoordinates(x -> {
            final Vector1D vec = Vector1D.of(x);

            final Vector1D expectedVec = Vector1D.of(factor.getX() * x);

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.applyVector(vec), EPS);
        });
    }

    @Test
    void testApplyVector_representsDisplacement() {
        // arrange
        final Vector1D p1 = Vector1D.of(Math.PI);

        final Vector1D translation = Vector1D.of(-2.0);
        final Vector1D scale = Vector1D.of(5.0);

        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.identity()
                .translate(translation)
                .scale(scale);

        // act/assert
        runWithCoordinates(x -> {
            final Vector1D p2 = Vector1D.of(x);
            final Vector1D input = p1.subtract(p2);

            final Vector1D expectedVec = transform.apply(p1).subtract(transform.apply(p2));

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.applyVector(input), EPS);
        });
    }

    @Test
    void testApplyVectorX() {
        // arrange
        final Vector1D p1 = Vector1D.of(Math.PI);

        final Vector1D translation = Vector1D.of(-2.0);
        final Vector1D scale = Vector1D.of(5.0);

        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.identity()
                .translate(translation)
                .scale(scale);

        // act/assert
        runWithCoordinates(x -> {
            final Vector1D p2 = p1.add(Vector1D.of(x));

            final double expected = transform.apply(p1).vectorTo(transform.apply(p2)).getX();

            Assertions.assertEquals(expected, transform.applyVectorX(x), EPS);
        });
    }

    @Test
    void testApplyDirection_identity() {
        // arrange
        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.identity();

        // act/assert
        runWithCoordinates(x -> {
            final Vector1D v = Vector1D.of(x);

            EuclideanTestUtils.assertCoordinatesEqual(v.normalize(), transform.applyDirection(v), EPS);
        }, true);
    }

    @Test
    void testApplyDirection_translate() {
        // arrange
        final Vector1D translation = Vector1D.of(-Math.PI);

        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.identity()
                .translate(translation);

        // act/assert
        runWithCoordinates(x -> {
            final Vector1D vec = Vector1D.of(x);

            EuclideanTestUtils.assertCoordinatesEqual(vec.normalize(), transform.applyDirection(vec), EPS);
        }, true);
    }

    @Test
    void testApplyDirection_scale() {
        // arrange
        final Vector1D factor = Vector1D.of(2.0);

        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.identity()
                .scale(factor);

        // act/assert
        runWithCoordinates(x -> {
            final Vector1D vec = Vector1D.of(x);

            final Vector1D expectedVec = Vector1D.of(factor.getX() * x).normalize();

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.applyDirection(vec), EPS);
        }, true);
    }

    @Test
    void testApplyDirection_representsNormalizedDisplacement() {
        // arrange
        final Vector1D p1 = Vector1D.of(Math.PI);

        final Vector1D translation = Vector1D.of(-2.0);
        final Vector1D scale = Vector1D.of(5.0);

        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.identity()
                .translate(translation)
                .scale(scale);

        // act/assert
        runWithCoordinates(x -> {
            final Vector1D p2 = Vector1D.of(x);
            final Vector1D input = p1.subtract(p2);

            final Vector1D expectedVec = transform.apply(p1).subtract(transform.apply(p2)).normalize();

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.applyDirection(input), EPS);
        });
    }

    @Test
    void testMultiply_combinesTransformOperations() {
        // arrange
        final Vector1D translation1 = Vector1D.of(1);
        final double scale = 2.0;
        final Vector1D translation2 = Vector1D.of(4);

        final AffineTransformMatrix1D a = AffineTransformMatrix1D.createTranslation(translation1);
        final AffineTransformMatrix1D b = AffineTransformMatrix1D.createScale(scale);
        final AffineTransformMatrix1D c = AffineTransformMatrix1D.identity();
        final AffineTransformMatrix1D d = AffineTransformMatrix1D.createTranslation(translation2);

        // act
        final AffineTransformMatrix1D transform = d.multiply(c).multiply(b).multiply(a);

        // assert
        runWithCoordinates(x -> {
            final Vector1D vec = Vector1D.of(x);

            final Vector1D expectedVec = vec
                    .add(translation1)
                    .multiply(scale)
                    .add(translation2);

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.apply(vec), EPS);
        });
    }

    @Test
    void testPremultiply_combinesTransformOperations() {
        // arrange
        final Vector1D translation1 = Vector1D.of(1);
        final double scale = 2.0;
        final Vector1D translation2 = Vector1D.of(4);

        final AffineTransformMatrix1D a = AffineTransformMatrix1D.createTranslation(translation1);
        final AffineTransformMatrix1D b = AffineTransformMatrix1D.createScale(scale);
        final AffineTransformMatrix1D c = AffineTransformMatrix1D.identity();
        final AffineTransformMatrix1D d = AffineTransformMatrix1D.createTranslation(translation2);

        // act
        final AffineTransformMatrix1D transform = a.premultiply(b).premultiply(c).premultiply(d);

        // assert
        runWithCoordinates(x -> {
            final Vector1D vec = Vector1D.of(x);

            final Vector1D expectedVec = vec
                    .add(translation1)
                    .multiply(scale)
                    .add(translation2);

            EuclideanTestUtils.assertCoordinatesEqual(expectedVec, transform.apply(vec), EPS);
        });
    }

    @Test
    void testNormalTransform() {
        // act/assert
        checkNormalTransform(AffineTransformMatrix1D.identity());

        checkNormalTransform(AffineTransformMatrix1D.createTranslation(4));
        checkNormalTransform(AffineTransformMatrix1D.createTranslation(-4));

        checkNormalTransform(AffineTransformMatrix1D.createScale(2));
        checkNormalTransform(AffineTransformMatrix1D.createScale(-2));

        checkNormalTransform(AffineTransformMatrix1D.createScale(2).translate(3));
        checkNormalTransform(AffineTransformMatrix1D.createScale(2).translate(-3));
        checkNormalTransform(AffineTransformMatrix1D.createTranslation(2).scale(-3));
        checkNormalTransform(AffineTransformMatrix1D.createTranslation(-4).scale(-1));
    }

    private void checkNormalTransform(final AffineTransformMatrix1D transform) {
        final AffineTransformMatrix1D normalTransform = transform.normalTransform();

        final Vector1D expectedPlus = transform.apply(Vector1D.Unit.PLUS)
                .subtract(transform.apply(Vector1D.ZERO))
                .normalize();

        final Vector1D expectedMinus = transform.apply(Vector1D.Unit.MINUS)
                .subtract(transform.apply(Vector1D.ZERO))
                .normalize();

        EuclideanTestUtils.assertCoordinatesEqual(expectedPlus,
                normalTransform.apply(Vector1D.Unit.PLUS).normalize(), EPS);
        EuclideanTestUtils.assertCoordinatesEqual(expectedMinus,
                normalTransform.apply(Vector1D.Unit.MINUS).normalize(), EPS);
    }

    @Test
    void testInverse_undoesOriginalTransform_translationAndScale() {
        // arrange
        final Vector1D v1 = Vector1D.ZERO;
        final Vector1D v2 = Vector1D.Unit.PLUS;
        final Vector1D v3 = Vector1D.of(1.5);
        final Vector1D v4 = Vector1D.of(-2);

        // act/assert
        runWithCoordinates(x -> {
            final AffineTransformMatrix1D transform = AffineTransformMatrix1D
                        .createTranslation(x)
                        .scale(2)
                        .translate(x / 3);

            final AffineTransformMatrix1D inverse = transform.inverse();

            EuclideanTestUtils.assertCoordinatesEqual(v1, inverse.apply(transform.apply(v1)), EPS);
            EuclideanTestUtils.assertCoordinatesEqual(v2, inverse.apply(transform.apply(v2)), EPS);
            EuclideanTestUtils.assertCoordinatesEqual(v3, inverse.apply(transform.apply(v3)), EPS);
            EuclideanTestUtils.assertCoordinatesEqual(v4, inverse.apply(transform.apply(v4)), EPS);
        });
    }

    @Test
    void testInverse_nonInvertible() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            AffineTransformMatrix1D.of(0, 0).inverse();
        }, IllegalStateException.class, "Matrix is not invertible; matrix determinant is 0.0");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            AffineTransformMatrix1D.of(Double.NaN, 0).inverse();
        }, IllegalStateException.class, "Matrix is not invertible; matrix determinant is NaN");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            AffineTransformMatrix1D.of(Double.NEGATIVE_INFINITY, 0.0).inverse();
        }, IllegalStateException.class, "Matrix is not invertible; matrix determinant is -Infinity");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            AffineTransformMatrix1D.of(Double.POSITIVE_INFINITY, 0).inverse();
        }, IllegalStateException.class, "Matrix is not invertible; matrix determinant is Infinity");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            AffineTransformMatrix1D.of(1, Double.NaN).inverse();
        }, IllegalStateException.class, "Matrix is not invertible; invalid matrix element: NaN");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            AffineTransformMatrix1D.of(1, Double.NEGATIVE_INFINITY).inverse();
        }, IllegalStateException.class, "Matrix is not invertible; invalid matrix element: -Infinity");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            AffineTransformMatrix1D.of(1, Double.POSITIVE_INFINITY).inverse();
        }, IllegalStateException.class, "Matrix is not invertible; invalid matrix element: Infinity");
    }

    @FunctionalInterface
    private interface Coordinate1DTest {

        void run(double x);
    }

    private static void runWithCoordinates(final Coordinate1DTest test) {
        runWithCoordinates(test, false);
    }

    private static void runWithCoordinates(final Coordinate1DTest test, final boolean skipZero) {
        runWithCoordinates(test, -1e-2, 1e-2, 5e-3, skipZero);
        runWithCoordinates(test, -1e2, 1e2, 5, skipZero);
    }

    private static void runWithCoordinates(final Coordinate1DTest test, final double min, final double max, final double step, final boolean skipZero) {
        for (double x = min; x <= max; x += step) {
            if (!skipZero || x != 0.0) {
                test.run(x);
            }
        }
    }

    @Test
    void testFrom_invalidFunction_1_oe() {
        // act/assert
        try {
    AffineTransformMatrix1D.from(v -> v.multiply(0));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testApplyDirection_illegalNorm_1_oe() {
        // act/assert
        try {
    AffineTransformMatrix1D.createScale(0).applyDirection(Vector1D.Unit.PLUS);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testApplyDirection_illegalNorm_2_oe() {
        // act/assert
        // removed other assertion
        try {
    AffineTransformMatrix1D.createScale(2).applyDirection(Vector1D.ZERO);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testNormalTransform_nonInvertible_1_oe() {
        // act/assert
        try {
    AffineTransformMatrix1D.createScale(0).normalTransform();
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

}
