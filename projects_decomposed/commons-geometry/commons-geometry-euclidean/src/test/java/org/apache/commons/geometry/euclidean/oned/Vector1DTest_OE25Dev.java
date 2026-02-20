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

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class Vector1DTest_OE25Dev {

    private static final double TEST_TOLERANCE = 1e-15;

    @Test
    void testConstants() {
        // act/assert
        checkVector(Vector1D.ZERO, 0.0);
        checkVector(Vector1D.Unit.PLUS, 1.0);
        checkVector(Vector1D.Unit.MINUS, -1.0);
        checkVector(Vector1D.NaN, Double.NaN);
        checkVector(Vector1D.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        checkVector(Vector1D.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    @Test
    void testZero() {
        // act
        final Vector1D zero = Vector1D.of(1).getZero();

        // assert
        checkVector(zero, 0.0);
        checkVector(Vector1D.Unit.PLUS.add(zero), 1.0);
    }

    @Test
    void testWithNorm() {
        // act/assert
        checkVector(Vector1D.Unit.PLUS.withNorm(0.0), 0.0);

        checkVector(Vector1D.of(0.5).withNorm(2.0), 2.0);
        checkVector(Vector1D.of(5).withNorm(3.0), 3.0);

        checkVector(Vector1D.of(-0.5).withNorm(2.0), -2.0);
        checkVector(Vector1D.of(-5).withNorm(3.0), -3.0);
    }

    @Test
    void testAdd() {
        // arrange
        final Vector1D v1 = Vector1D.of(1);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(3);

        // act/assert
        checkVector(v1.add(v1), 2);
        checkVector(v1.add(v2), -2);
        checkVector(v2.add(v1), -2);
        checkVector(v2.add(v3), 0);
    }

    @Test
    void testAdd_scaled() {
        // arrange
        final Vector1D v1 = Vector1D.of(1);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(3);

        // act/assert
        checkVector(v1.add(1, v1), 2);
        checkVector(v1.add(0.5, v1), 1.5);
        checkVector(v1.add(-1, v1), 0);

        checkVector(v1.add(0, v2), 1);
        checkVector(v2.add(3, v1), 0);
        checkVector(v2.add(2, v3), 3);
    }

    @Test
    void testSubtract() {
        // arrange
        final Vector1D v1 = Vector1D.of(1);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(3);

        // act/assert
        checkVector(v1.subtract(v1), 0);
        checkVector(v1.subtract(v2), 4);
        checkVector(v2.subtract(v1), -4);
        checkVector(v2.subtract(v3), -6);
    }

    @Test
    void testSubtract_scaled() {
        // arrange
        final Vector1D v1 = Vector1D.of(1);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(3);

        // act/assert
        checkVector(v1.subtract(1, v1), 0);
        checkVector(v1.subtract(0.5, v1), 0.5);
        checkVector(v1.subtract(-1, v1), 2);

        checkVector(v1.subtract(0, v2), 1);
        checkVector(v2.subtract(3, v1), -6);
        checkVector(v2.subtract(2, v3), -9);
    }

    @Test
    void testNormalize() {
        // act/assert
        checkVector(Vector1D.of(1).normalize(), 1);
        checkVector(Vector1D.of(-1).normalize(), -1);
        checkVector(Vector1D.of(5).normalize(), 1);
        checkVector(Vector1D.of(-5).normalize(), -1);

        checkVector(Vector1D.of(Double.MIN_VALUE).normalize(), 1);
        checkVector(Vector1D.of(-Double.MIN_VALUE).normalize(), -1);

        checkVector(Vector1D.of(Double.MAX_VALUE).normalize(), 1);
        checkVector(Vector1D.of(-Double.MAX_VALUE).normalize(), -1);
    }

    @Test
    void testNegate() {
        // act/assert
        checkVector(Vector1D.of(0.1).negate(), -0.1);
        checkVector(Vector1D.of(-0.1).negate(), 0.1);
    }

    @Test
    void testNegate_unitVectors() {
        // arrange
        final Vector1D v1 = Vector1D.of(0.1).normalize();
        final Vector1D v2 = Vector1D.of(-0.1).normalize();

        // act/assert
        checkVector(v1.negate(), -1);
        checkVector(v2.negate(), 1);
    }

    @Test
    void testScalarMultiply() {
        // act/assert
        checkVector(Vector1D.of(1).multiply(3), 3);
        checkVector(Vector1D.of(1).multiply(-3), -3);

        checkVector(Vector1D.of(1.5).multiply(7), 10.5);
        checkVector(Vector1D.of(-1.5).multiply(7), -10.5);
    }

    @Test
    void testVectorTo() {
        // arrange
        final Vector1D v1 = Vector1D.of(1);
        final Vector1D v2 = Vector1D.of(-4);
        final Vector1D v3 = Vector1D.of(10);

        // act/assert
        checkVector(v1.vectorTo(v1), 0.0);
        checkVector(v2.vectorTo(v2), 0.0);
        checkVector(v3.vectorTo(v3), 0.0);

        checkVector(v1.vectorTo(v2), -5.0);
        checkVector(v2.vectorTo(v1), 5.0);

        checkVector(v1.vectorTo(v3), 9.0);
        checkVector(v3.vectorTo(v1), -9.0);

        checkVector(v2.vectorTo(v3), 14.0);
        checkVector(v3.vectorTo(v2), -14.0);
    }

    @Test
    void testDirectionTo() {
        // act/assert
        final Vector1D v1 = Vector1D.of(1);
        final Vector1D v2 = Vector1D.of(5);
        final Vector1D v3 = Vector1D.of(-2);

        // act/assert
        checkVector(v1.directionTo(v2), 1);
        checkVector(v2.directionTo(v1), -1);

        checkVector(v1.directionTo(v3), -1);
        checkVector(v3.directionTo(v1), 1);
    }

    @Test
    void testLerp() {
        // arrange
        final Vector1D v1 = Vector1D.of(1);
        final Vector1D v2 = Vector1D.of(-4);
        final Vector1D v3 = Vector1D.of(10);

        // act/assert
        checkVector(v1.lerp(v1, 0), 1);
        checkVector(v1.lerp(v1, 1), 1);

        checkVector(v1.lerp(v2, -0.25), 2.25);
        checkVector(v1.lerp(v2, 0), 1);
        checkVector(v1.lerp(v2, 0.25), -0.25);
        checkVector(v1.lerp(v2, 0.5), -1.5);
        checkVector(v1.lerp(v2, 0.75), -2.75);
        checkVector(v1.lerp(v2, 1), -4);
        checkVector(v1.lerp(v2, 1.25), -5.25);

        checkVector(v1.lerp(v3, 0), 1);
        checkVector(v1.lerp(v3, 0.25), 3.25);
        checkVector(v1.lerp(v3, 0.5), 5.5);
        checkVector(v1.lerp(v3, 0.75), 7.75);
        checkVector(v1.lerp(v3, 1), 10);
    }

    @Test
    void testTransform() {
        // arrange
        final AffineTransformMatrix1D transform = AffineTransformMatrix1D.identity()
                .scale(2)
                .translate(1);

        final Vector1D v1 = Vector1D.of(1);
        final Vector1D v2 = Vector1D.of(-4);

        // act/assert
        checkVector(v1.transform(transform), 3);
        checkVector(v2.transform(transform), -7);
    }

    @Test
    void testParse() {
        // act/assert
        checkVector(Vector1D.parse("(1)"), 1);
        checkVector(Vector1D.parse("(-1)"), -1);

        checkVector(Vector1D.parse("(0.01)"), 1e-2);
        checkVector(Vector1D.parse("(-1e-3)"), -1e-3);

        checkVector(Vector1D.parse("(NaN)"), Double.NaN);

        checkVector(Vector1D.parse(Vector1D.ZERO.toString()), 0);
        checkVector(Vector1D.parse(Vector1D.Unit.PLUS.toString()), 1);
    }

    @Test
    void testOf() {
        // act/assert
        checkVector(Vector1D.of(0), 0.0);
        checkVector(Vector1D.of(-1), -1.0);
        checkVector(Vector1D.of(1), 1.0);
        checkVector(Vector1D.of(Math.PI), Math.PI);
        checkVector(Vector1D.of(Double.NaN), Double.NaN);
        checkVector(Vector1D.of(Double.NEGATIVE_INFINITY), Double.NEGATIVE_INFINITY);
        checkVector(Vector1D.of(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
    }

    @Test
    void testUnitFrom_coordinates() {
        // act/assert
        checkVector(Vector1D.Unit.from(2.0), 1);
        checkVector(Vector1D.Unit.from(-4.0), -1);
    }

    @Test
    void testSum_factoryMethods() {
        // act/assert
        checkVector(Vector1D.Sum.create().get(), 0);
        checkVector(Vector1D.Sum.of(Vector1D.of(2)).get(), 2);
        checkVector(Vector1D.Sum.of(
                Vector1D.of(-2),
                Vector1D.Unit.PLUS).get(), -1);
    }

    @Test
    void testSum_instanceMethods() {
        // arrange
        final Vector1D p1 = Vector1D.of(-1);
        final Vector1D p2 = Vector1D.of(4);

        // act/assert
        checkVector(Vector1D.Sum.create()
                .add(p1)
                .addScaled(0.5, p2)
                .get(), 1);
    }

    @Test
    void testSum_accept() {
        // arrange
        final Vector1D p1 = Vector1D.of(2);
        final Vector1D p2 = Vector1D.of(-3);

        final List<Vector1D.Unit> units = Arrays.asList(
                Vector1D.Unit.MINUS);

        final Vector1D.Sum s = Vector1D.Sum.create();

        // act/assert
        Arrays.asList(p1, Vector1D.ZERO, p2).forEach(s);
        units.forEach(s);

        // assert
        checkVector(s.get(), -2);
    }

    private void checkVector(final Vector1D v, final double x) {
        Assertions.assertEquals(x, v.getX(), TEST_TOLERANCE);
    }

    @Test
    void testConstants_normalize_1_oe() {
        // act/assert
        try {
    Vector1D.ZERO.normalize();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConstants_normalize_2_oe() {
        // act/assert
        // removed other assertion
        try {
    Vector1D.NaN.normalize();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConstants_normalize_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    Vector1D.POSITIVE_INFINITY.normalize();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConstants_normalize_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Vector1D.NEGATIVE_INFINITY.normalize();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testConstants_normalize_5_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(Vector1D.Unit.PLUS, Vector1D.Unit.PLUS.normalize());
    }

    @Test
    void testConstants_normalize_6_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(Vector1D.Unit.MINUS, Vector1D.Unit.MINUS.normalize());
    }

    @Test
    void testCoordinateAscendingOrderComparator_1_oe() {
        // arrange
        final Comparator<Vector1D> cmp = Vector1D.COORDINATE_ASCENDING_ORDER;

        // act/assert
        Assertions.assertEquals(0, cmp.compare(Vector1D.of(1), Vector1D.of(1)));
    }

    @Test
    void testCoordinateAscendingOrderComparator_2_oe() {
        // arrange
        final Comparator<Vector1D> cmp = Vector1D.COORDINATE_ASCENDING_ORDER;

        // act/assert
        // removed other assertion
        Assertions.assertEquals(1, cmp.compare(Vector1D.of(2), Vector1D.of(1)));
    }

    @Test
    void testCoordinateAscendingOrderComparator_3_oe() {
        // arrange
        final Comparator<Vector1D> cmp = Vector1D.COORDINATE_ASCENDING_ORDER;

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-1, cmp.compare(Vector1D.of(0), Vector1D.of(1)));
    }

    @Test
    void testCoordinateAscendingOrderComparator_4_oe() {
        // arrange
        final Comparator<Vector1D> cmp = Vector1D.COORDINATE_ASCENDING_ORDER;

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, cmp.compare(Vector1D.of(0), Vector1D.of(0)));
    }

    @Test
    void testCoordinateAscendingOrderComparator_5_oe() {
        // arrange
        final Comparator<Vector1D> cmp = Vector1D.COORDINATE_ASCENDING_ORDER;

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, cmp.compare(Vector1D.of(1e-15), Vector1D.of(0)));
    }

    @Test
    void testCoordinateAscendingOrderComparator_6_oe() {
        // arrange
        final Comparator<Vector1D> cmp = Vector1D.COORDINATE_ASCENDING_ORDER;

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-1, cmp.compare(Vector1D.of(-1e-15), Vector1D.of(0)));
    }

    @Test
    void testCoordinateAscendingOrderComparator_7_oe() {
        // arrange
        final Comparator<Vector1D> cmp = Vector1D.COORDINATE_ASCENDING_ORDER;

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-1, cmp.compare(Vector1D.of(1), null));
    }

    @Test
    void testCoordinateAscendingOrderComparator_8_oe() {
        // arrange
        final Comparator<Vector1D> cmp = Vector1D.COORDINATE_ASCENDING_ORDER;

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, cmp.compare(null, Vector1D.of(1)));
    }

    @Test
    void testCoordinateAscendingOrderComparator_9_oe() {
        // arrange
        final Comparator<Vector1D> cmp = Vector1D.COORDINATE_ASCENDING_ORDER;

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, cmp.compare(null, null));
    }

    @Test
    void testCoordinates_1_oe() {
        // act/assert
        Assertions.assertEquals(-1, Vector1D.of(-1).getX(), 0.0);
    }

    @Test
    void testCoordinates_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertEquals(0, Vector1D.of(0).getX(), 0.0);
    }

    @Test
    void testCoordinates_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, Vector1D.of(1).getX(), 0.0);
    }

    @Test
    void testDimension_1_oe() {
        // arrange
        final Vector1D v = Vector1D.of(2);

        // act/assert
        Assertions.assertEquals(1, v.getDimension());
    }

    @Test
    void testNaN_1_oe() {
        // act/assert
        Assertions.assertTrue(Vector1D.of(Double.NaN).isNaN());
    }

    @Test
    void testNaN_2_oe() {
        // act/assert
        // removed other assertion

        Assertions.assertFalse(Vector1D.of(1).isNaN());
    }

    @Test
    void testNaN_3_oe() {
        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(Vector1D.of(Double.NEGATIVE_INFINITY).isNaN());
    }

    @Test
    void testInfinite_1_oe() {
        // act/assert
        Assertions.assertTrue(Vector1D.of(Double.NEGATIVE_INFINITY).isInfinite());
    }

    @Test
    void testInfinite_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertTrue(Vector1D.of(Double.POSITIVE_INFINITY).isInfinite());
    }

    @Test
    void testInfinite_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(Vector1D.of(1).isInfinite());
    }

    @Test
    void testInfinite_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(Vector1D.of(Double.NaN).isInfinite());
    }

    @Test
    void testFinite_1_oe() {
        // act/assert
        Assertions.assertTrue(Vector1D.ZERO.isFinite());
    }

    @Test
    void testFinite_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertTrue(Vector1D.of(1).isFinite());
    }

    @Test
    void testFinite_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(Vector1D.of(Double.NEGATIVE_INFINITY).isFinite());
    }

    @Test
    void testFinite_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(Vector1D.of(Double.POSITIVE_INFINITY).isFinite());
    }

    @Test
    void testFinite_5_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(Vector1D.of(Double.NaN).isFinite());
    }

    @Test
    void testNorm_1_oe() {
        // act/assert
        Assertions.assertEquals(0.0, Vector1D.ZERO.norm(), TEST_TOLERANCE);
    }

    @Test
    void testNorm_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertEquals(3.0, Vector1D.of(3).norm(), TEST_TOLERANCE);
    }

    @Test
    void testNorm_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(3.0, Vector1D.of(-3).norm(), TEST_TOLERANCE);
    }

    @Test
    void testNorm_unitVectors_1_oe() {
        // arrange
        final Vector1D v = Vector1D.of(2.0).normalize();

        // act/assert
        Assertions.assertEquals(1.0, v.norm(), 0.0);
    }

    @Test
    void testNormSq_1_oe() {
        // act/assert
        Assertions.assertEquals(0.0, Vector1D.of(0).normSq(), TEST_TOLERANCE);
    }

    @Test
    void testNormSq_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertEquals(9.0, Vector1D.of(3).normSq(), TEST_TOLERANCE);
    }

    @Test
    void testNormSq_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(9.0, Vector1D.of(-3).normSq(), TEST_TOLERANCE);
    }

    @Test
    void testNormSq_unitVectors_1_oe() {
        // arrange
        final Vector1D v = Vector1D.of(2.0).normalize();

        // act/assert
        Assertions.assertEquals(1.0, v.normSq(), 0.0);
    }

    @Test
    void testWithNorm_illegalNorm_1_oe() {
        // act/assert
        try {
    Vector1D.ZERO.withNorm(2.0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testWithNorm_illegalNorm_2_oe() {
        // act/assert
        // removed other assertion
        try {
    Vector1D.NaN.withNorm(2.0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testWithNorm_illegalNorm_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    Vector1D.POSITIVE_INFINITY.withNorm(2.0);
    fail("IllegalArgumentException");
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
    Vector1D.NEGATIVE_INFINITY.withNorm(2.0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testWithNorm_unitVectors_1_oe() {
        // arrange
        final Vector1D v = Vector1D.of(2.0).normalize();

        // act/assert
        checkVector(Vector1D.Unit.PLUS.withNorm(2.5), 2.5);
        checkVector(Vector1D.Unit.MINUS.withNorm(3.14), -3.14);

        for (double mag = -10.0; mag <= 10.0; ++mag) {
            Assertions.assertEquals(Math.abs(mag), v.withNorm(mag).norm(), TEST_TOLERANCE);
    }
    }

    @Test
    void testNormalize_isIdempotent_1_oe() {
        // arrange
        final Vector1D v = Vector1D.of(2).normalize();

        // act/assert
        Assertions.assertSame(v, v.normalize());
    }

    @Test
    void testNormalizeOrNull_1_oe() {
        // act/assert
        checkVector(Vector1D.of(100).normalizeOrNull(), 1);
        checkVector(Vector1D.of(-100).normalizeOrNull(), -1);

        checkVector(Vector1D.of(2).normalizeOrNull(), 1);
        checkVector(Vector1D.of(-2).normalizeOrNull(), -1);

        checkVector(Vector1D.of(Double.MIN_VALUE).normalizeOrNull(), 1);
        checkVector(Vector1D.of(-Double.MIN_VALUE).normalizeOrNull(), -1);

        checkVector(Vector1D.of(Double.MAX_VALUE).normalizeOrNull(), 1);
        checkVector(Vector1D.of(-Double.MAX_VALUE).normalizeOrNull(), -1);

        Assertions.assertNull(Vector1D.ZERO.normalizeOrNull());
    }

    @Test
    void testNormalizeOrNull_2_oe() {
        // act/assert
        checkVector(Vector1D.of(100).normalizeOrNull(), 1);
        checkVector(Vector1D.of(-100).normalizeOrNull(), -1);

        checkVector(Vector1D.of(2).normalizeOrNull(), 1);
        checkVector(Vector1D.of(-2).normalizeOrNull(), -1);

        checkVector(Vector1D.of(Double.MIN_VALUE).normalizeOrNull(), 1);
        checkVector(Vector1D.of(-Double.MIN_VALUE).normalizeOrNull(), -1);

        checkVector(Vector1D.of(Double.MAX_VALUE).normalizeOrNull(), 1);
        checkVector(Vector1D.of(-Double.MAX_VALUE).normalizeOrNull(), -1);

        // removed other assertion
        Assertions.assertNull(Vector1D.NaN.normalizeOrNull());
    }

    @Test
    void testNormalizeOrNull_3_oe() {
        // act/assert
        checkVector(Vector1D.of(100).normalizeOrNull(), 1);
        checkVector(Vector1D.of(-100).normalizeOrNull(), -1);

        checkVector(Vector1D.of(2).normalizeOrNull(), 1);
        checkVector(Vector1D.of(-2).normalizeOrNull(), -1);

        checkVector(Vector1D.of(Double.MIN_VALUE).normalizeOrNull(), 1);
        checkVector(Vector1D.of(-Double.MIN_VALUE).normalizeOrNull(), -1);

        checkVector(Vector1D.of(Double.MAX_VALUE).normalizeOrNull(), 1);
        checkVector(Vector1D.of(-Double.MAX_VALUE).normalizeOrNull(), -1);

        // removed other assertion
        // removed other assertion
        Assertions.assertNull(Vector1D.POSITIVE_INFINITY.normalizeOrNull());
    }

    @Test
    void testNormalizeOrNull_4_oe() {
        // act/assert
        checkVector(Vector1D.of(100).normalizeOrNull(), 1);
        checkVector(Vector1D.of(-100).normalizeOrNull(), -1);

        checkVector(Vector1D.of(2).normalizeOrNull(), 1);
        checkVector(Vector1D.of(-2).normalizeOrNull(), -1);

        checkVector(Vector1D.of(Double.MIN_VALUE).normalizeOrNull(), 1);
        checkVector(Vector1D.of(-Double.MIN_VALUE).normalizeOrNull(), -1);

        checkVector(Vector1D.of(Double.MAX_VALUE).normalizeOrNull(), 1);
        checkVector(Vector1D.of(-Double.MAX_VALUE).normalizeOrNull(), -1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(Vector1D.NEGATIVE_INFINITY.normalizeOrNull());
    }

    @Test
    void testNormalizeOrNull_isIdempotent_1_oe() {
        // arrange
        final Vector1D v = Vector1D.of(2).normalizeOrNull();

        // act/assert
        Assertions.assertSame(v, v.normalizeOrNull());
    }

    @Test
    void testDistance_1_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(1);
        final Vector1D v2 = Vector1D.of(-4);

        // act/assert
        Assertions.assertEquals(0.0, v1.distance(v1), TEST_TOLERANCE);
    }

    @Test
    void testDistance_2_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(1);
        final Vector1D v2 = Vector1D.of(-4);

        // act/assert
        // removed other assertion

        Assertions.assertEquals(5.0, v1.distance(v2), TEST_TOLERANCE);
    }

    @Test
    void testDistance_3_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(1);
        final Vector1D v2 = Vector1D.of(-4);

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(5.0, v2.distance(v1), TEST_TOLERANCE);
    }

    @Test
    void testDistance_4_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(1);
        final Vector1D v2 = Vector1D.of(-4);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(v1.subtract(v2).norm(), v1.distance(v2), TEST_TOLERANCE);
    }

    @Test
    void testDistance_5_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(1);
        final Vector1D v2 = Vector1D.of(-4);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0.0, Vector1D.of(-1).distance(Vector1D.of(-1)), TEST_TOLERANCE);
    }

    @Test
    void testDistanceSq_1_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(1);
        final Vector1D v2 = Vector1D.of(-4);

        // act/assert
        Assertions.assertEquals(0.0, Vector1D.of(-1).distanceSq(Vector1D.of(-1)), TEST_TOLERANCE);
    }

    @Test
    void testDistanceSq_2_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(1);
        final Vector1D v2 = Vector1D.of(-4);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(25.0, v1.distanceSq(v2), TEST_TOLERANCE);
    }

    @Test
    void testDistanceSq_3_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(1);
        final Vector1D v2 = Vector1D.of(-4);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(25.0, v2.distanceSq(v1), TEST_TOLERANCE);
    }

    @Test
    void testDotProduct_1_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(3);

        // act/assert
        Assertions.assertEquals(-6.0, v1.dot(v2), TEST_TOLERANCE);
    }

    @Test
    void testDotProduct_2_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(3);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(-6.0, v2.dot(v1), TEST_TOLERANCE);
    }

    @Test
    void testDotProduct_3_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(3);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(6.0, v1.dot(v3), TEST_TOLERANCE);
    }

    @Test
    void testDotProduct_4_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(3);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(6.0, v3.dot(v1), TEST_TOLERANCE);
    }

    @Test
    void testAngle_1_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(4);
        final Vector1D v4 = Vector1D.of(-5);

        // act/assert
        Assertions.assertEquals(0.0, v1.angle(v1), TEST_TOLERANCE);
    }

    @Test
    void testAngle_2_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(4);
        final Vector1D v4 = Vector1D.of(-5);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(Math.PI, v1.angle(v2), TEST_TOLERANCE);
    }

    @Test
    void testAngle_3_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(4);
        final Vector1D v4 = Vector1D.of(-5);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0, v1.angle(v3), TEST_TOLERANCE);
    }

    @Test
    void testAngle_4_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(4);
        final Vector1D v4 = Vector1D.of(-5);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.PI, v1.angle(v4), TEST_TOLERANCE);
    }

    @Test
    void testAngle_5_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(4);
        final Vector1D v4 = Vector1D.of(-5);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Math.PI, v2.angle(v1), TEST_TOLERANCE);
    }

    @Test
    void testAngle_6_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(4);
        final Vector1D v4 = Vector1D.of(-5);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0.0, v2.angle(v2), TEST_TOLERANCE);
    }

    @Test
    void testAngle_7_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(4);
        final Vector1D v4 = Vector1D.of(-5);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.PI, v2.angle(v3), TEST_TOLERANCE);
    }

    @Test
    void testAngle_8_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(4);
        final Vector1D v4 = Vector1D.of(-5);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0, v2.angle(v4), TEST_TOLERANCE);
    }

    @Test
    void testAngle_9_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(4);
        final Vector1D v4 = Vector1D.of(-5);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0.0, v3.angle(v1), TEST_TOLERANCE);
    }

    @Test
    void testAngle_10_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(4);
        final Vector1D v4 = Vector1D.of(-5);

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
        Assertions.assertEquals(Math.PI, v3.angle(v2), TEST_TOLERANCE);
    }

    @Test
    void testAngle_11_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(4);
        final Vector1D v4 = Vector1D.of(-5);

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
        Assertions.assertEquals(0.0, v3.angle(v3), TEST_TOLERANCE);
    }

    @Test
    void testAngle_12_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(4);
        final Vector1D v4 = Vector1D.of(-5);

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
        Assertions.assertEquals(Math.PI, v3.angle(v4), TEST_TOLERANCE);
    }

    @Test
    void testAngle_13_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(4);
        final Vector1D v4 = Vector1D.of(-5);

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
        // removed other assertion

        Assertions.assertEquals(Math.PI, v4.angle(v1), TEST_TOLERANCE);
    }

    @Test
    void testAngle_14_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(4);
        final Vector1D v4 = Vector1D.of(-5);

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
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0.0, v4.angle(v2), TEST_TOLERANCE);
    }

    @Test
    void testAngle_15_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(4);
        final Vector1D v4 = Vector1D.of(-5);

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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.PI, v4.angle(v3), TEST_TOLERANCE);
    }

    @Test
    void testAngle_16_oe() {
        // arrange
        final Vector1D v1 = Vector1D.of(2);
        final Vector1D v2 = Vector1D.of(-3);
        final Vector1D v3 = Vector1D.of(4);
        final Vector1D v4 = Vector1D.of(-5);

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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0, v4.angle(v4), TEST_TOLERANCE);
    }

    @Test
    void testAngle_illegalNorm_1_oe() {
        // arrange
        final Vector1D v = Vector1D.of(1.0);

        // act/assert
        try {
    Vector1D.ZERO.angle(v);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAngle_illegalNorm_2_oe() {
        // arrange
        final Vector1D v = Vector1D.of(1.0);

        // act/assert
        // removed other assertion
        try {
    Vector1D.NaN.angle(v);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAngle_illegalNorm_3_oe() {
        // arrange
        final Vector1D v = Vector1D.of(1.0);

        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    Vector1D.POSITIVE_INFINITY.angle(v);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAngle_illegalNorm_4_oe() {
        // arrange
        final Vector1D v = Vector1D.of(1.0);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Vector1D.NEGATIVE_INFINITY.angle(v);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAngle_illegalNorm_5_oe() {
        // arrange
        final Vector1D v = Vector1D.of(1.0);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    v.angle(Vector1D.ZERO);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAngle_illegalNorm_6_oe() {
        // arrange
        final Vector1D v = Vector1D.of(1.0);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    v.angle(Vector1D.NaN);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAngle_illegalNorm_7_oe() {
        // arrange
        final Vector1D v = Vector1D.of(1.0);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    v.angle(Vector1D.POSITIVE_INFINITY);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAngle_illegalNorm_8_oe() {
        // arrange
        final Vector1D v = Vector1D.of(1.0);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    v.angle(Vector1D.NEGATIVE_INFINITY);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testDirectionTo_illegalNorm_1_oe() {
        // arrange
        final Vector1D v = Vector1D.of(2);

        // act/assert
        try {
    Vector1D.ZERO.directionTo(Vector1D.ZERO);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testDirectionTo_illegalNorm_2_oe() {
        // arrange
        final Vector1D v = Vector1D.of(2);

        // act/assert
        // removed other assertion
        try {
    v.directionTo(v);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testDirectionTo_illegalNorm_3_oe() {
        // arrange
        final Vector1D v = Vector1D.of(2);

        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    v.directionTo(Vector1D.NaN);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testDirectionTo_illegalNorm_4_oe() {
        // arrange
        final Vector1D v = Vector1D.of(2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Vector1D.NEGATIVE_INFINITY.directionTo(v);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testDirectionTo_illegalNorm_5_oe() {
        // arrange
        final Vector1D v = Vector1D.of(2);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    v.directionTo(Vector1D.POSITIVE_INFINITY);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPrecisionEquals_1_oe() {
        // arrange
        final Precision.DoubleEquivalence smallEps = Precision.doubleEquivalenceOfEpsilon(1e-6);
        final Precision.DoubleEquivalence largeEps = Precision.doubleEquivalenceOfEpsilon(1e-1);

        final Vector1D vec = Vector1D.of(1);

        // act/assert
        Assertions.assertTrue(vec.eq(vec, smallEps));
    }

    @Test
    void testPrecisionEquals_2_oe() {
        // arrange
        final Precision.DoubleEquivalence smallEps = Precision.doubleEquivalenceOfEpsilon(1e-6);
        final Precision.DoubleEquivalence largeEps = Precision.doubleEquivalenceOfEpsilon(1e-1);

        final Vector1D vec = Vector1D.of(1);

        // act/assert
        // removed other assertion
        Assertions.assertTrue(vec.eq(vec, largeEps));
    }

    @Test
    void testPrecisionEquals_3_oe() {
        // arrange
        final Precision.DoubleEquivalence smallEps = Precision.doubleEquivalenceOfEpsilon(1e-6);
        final Precision.DoubleEquivalence largeEps = Precision.doubleEquivalenceOfEpsilon(1e-1);

        final Vector1D vec = Vector1D.of(1);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(vec.eq(Vector1D.of(1.0000007), smallEps));
    }

    @Test
    void testPrecisionEquals_4_oe() {
        // arrange
        final Precision.DoubleEquivalence smallEps = Precision.doubleEquivalenceOfEpsilon(1e-6);
        final Precision.DoubleEquivalence largeEps = Precision.doubleEquivalenceOfEpsilon(1e-1);

        final Vector1D vec = Vector1D.of(1);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(vec.eq(Vector1D.of(1.0000007), largeEps));
    }

    @Test
    void testPrecisionEquals_5_oe() {
        // arrange
        final Precision.DoubleEquivalence smallEps = Precision.doubleEquivalenceOfEpsilon(1e-6);
        final Precision.DoubleEquivalence largeEps = Precision.doubleEquivalenceOfEpsilon(1e-1);

        final Vector1D vec = Vector1D.of(1);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(vec.eq(Vector1D.of(1.004), smallEps));
    }

    @Test
    void testPrecisionEquals_6_oe() {
        // arrange
        final Precision.DoubleEquivalence smallEps = Precision.doubleEquivalenceOfEpsilon(1e-6);
        final Precision.DoubleEquivalence largeEps = Precision.doubleEquivalenceOfEpsilon(1e-1);

        final Vector1D vec = Vector1D.of(1);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(vec.eq(Vector1D.of(1.004), largeEps));
    }

    @Test
    void testPrecisionEquals_7_oe() {
        // arrange
        final Precision.DoubleEquivalence smallEps = Precision.doubleEquivalenceOfEpsilon(1e-6);
        final Precision.DoubleEquivalence largeEps = Precision.doubleEquivalenceOfEpsilon(1e-1);

        final Vector1D vec = Vector1D.of(1);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(vec.eq(Vector1D.of(2), smallEps));
    }

    @Test
    void testPrecisionEquals_8_oe() {
        // arrange
        final Precision.DoubleEquivalence smallEps = Precision.doubleEquivalenceOfEpsilon(1e-6);
        final Precision.DoubleEquivalence largeEps = Precision.doubleEquivalenceOfEpsilon(1e-1);

        final Vector1D vec = Vector1D.of(1);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(vec.eq(Vector1D.of(-2), largeEps));
    }

    @Test
    void testIsZero_1_oe() {
        // arrange
        final Precision.DoubleEquivalence smallEps = Precision.doubleEquivalenceOfEpsilon(1e-6);
        final Precision.DoubleEquivalence largeEps = Precision.doubleEquivalenceOfEpsilon(1e-1);

        // act/assert
        Assertions.assertTrue(Vector1D.of(0.0).isZero(smallEps));
    }

    @Test
    void testIsZero_2_oe() {
        // arrange
        final Precision.DoubleEquivalence smallEps = Precision.doubleEquivalenceOfEpsilon(1e-6);
        final Precision.DoubleEquivalence largeEps = Precision.doubleEquivalenceOfEpsilon(1e-1);

        // act/assert
        // removed other assertion
        Assertions.assertTrue(Vector1D.of(-0.0).isZero(largeEps));
    }

    @Test
    void testIsZero_3_oe() {
        // arrange
        final Precision.DoubleEquivalence smallEps = Precision.doubleEquivalenceOfEpsilon(1e-6);
        final Precision.DoubleEquivalence largeEps = Precision.doubleEquivalenceOfEpsilon(1e-1);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(Vector1D.of(1e-7).isZero(smallEps));
    }

    @Test
    void testIsZero_4_oe() {
        // arrange
        final Precision.DoubleEquivalence smallEps = Precision.doubleEquivalenceOfEpsilon(1e-6);
        final Precision.DoubleEquivalence largeEps = Precision.doubleEquivalenceOfEpsilon(1e-1);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(Vector1D.of(-1e-7).isZero(largeEps));
    }

    @Test
    void testIsZero_5_oe() {
        // arrange
        final Precision.DoubleEquivalence smallEps = Precision.doubleEquivalenceOfEpsilon(1e-6);
        final Precision.DoubleEquivalence largeEps = Precision.doubleEquivalenceOfEpsilon(1e-1);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(Vector1D.of(1e-2).isZero(smallEps));
    }

    @Test
    void testIsZero_6_oe() {
        // arrange
        final Precision.DoubleEquivalence smallEps = Precision.doubleEquivalenceOfEpsilon(1e-6);
        final Precision.DoubleEquivalence largeEps = Precision.doubleEquivalenceOfEpsilon(1e-1);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(Vector1D.of(-1e-2).isZero(largeEps));
    }

    @Test
    void testIsZero_7_oe() {
        // arrange
        final Precision.DoubleEquivalence smallEps = Precision.doubleEquivalenceOfEpsilon(1e-6);
        final Precision.DoubleEquivalence largeEps = Precision.doubleEquivalenceOfEpsilon(1e-1);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(Vector1D.of(0.2).isZero(smallEps));
    }

    @Test
    void testIsZero_8_oe() {
        // arrange
        final Precision.DoubleEquivalence smallEps = Precision.doubleEquivalenceOfEpsilon(1e-6);
        final Precision.DoubleEquivalence largeEps = Precision.doubleEquivalenceOfEpsilon(1e-1);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(Vector1D.of(-0.2).isZero(largeEps));
    }

    @Test
    void testHashCode_1_oe() {
        // arrange
        final Vector1D u = Vector1D.of(1);
        final Vector1D v = Vector1D.of(1 + 10 * Precision.EPSILON);
        final Vector1D w = Vector1D.of(1);

        // act/assert
        Assertions.assertTrue(u.hashCode() != v.hashCode());
    }

    @Test
    void testHashCode_2_oe() {
        // arrange
        final Vector1D u = Vector1D.of(1);
        final Vector1D v = Vector1D.of(1 + 10 * Precision.EPSILON);
        final Vector1D w = Vector1D.of(1);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(u.hashCode(), w.hashCode());
    }

    @Test
    void testHashCode_3_oe() {
        // arrange
        final Vector1D u = Vector1D.of(1);
        final Vector1D v = Vector1D.of(1 + 10 * Precision.EPSILON);
        final Vector1D w = Vector1D.of(1);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Vector1D.of(Double.NaN).hashCode(), Vector1D.NaN.hashCode());
    }

    @Test
    void testHashCode_4_oe() {
        // arrange
        final Vector1D u = Vector1D.of(1);
        final Vector1D v = Vector1D.of(1 + 10 * Precision.EPSILON);
        final Vector1D w = Vector1D.of(1);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(Vector1D.of(Double.NaN).hashCode(), Vector1D.of(Double.NaN).hashCode());
    }

    @Test
    void testEquals_2_oe() {
        // arrange
        final Vector1D u1 = Vector1D.of(1);
        final Vector1D u2 = Vector1D.of(1);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(u1, u2);
    }

    @Test
    void testEquals_3_oe() {
        // arrange
        final Vector1D u1 = Vector1D.of(1);
        final Vector1D u2 = Vector1D.of(1);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertNotEquals(u1, Vector1D.of(-1));
    }

    @Test
    void testEquals_4_oe() {
        // arrange
        final Vector1D u1 = Vector1D.of(1);
        final Vector1D u2 = Vector1D.of(1);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNotEquals(u1, Vector1D.of(1 + 10 * Precision.EPSILON));
    }

    @Test
    void testEquals_5_oe() {
        // arrange
        final Vector1D u1 = Vector1D.of(1);
        final Vector1D u2 = Vector1D.of(1);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Vector1D.of(Double.NaN), Vector1D.of(Double.NaN));
    }

    @Test
    void testEquals_6_oe() {
        // arrange
        final Vector1D u1 = Vector1D.of(1);
        final Vector1D u2 = Vector1D.of(1);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(Vector1D.of(Double.POSITIVE_INFINITY), Vector1D.of(Double.POSITIVE_INFINITY));
    }

    @Test
    void testEquals_7_oe() {
        // arrange
        final Vector1D u1 = Vector1D.of(1);
        final Vector1D u2 = Vector1D.of(1);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Vector1D.of(Double.NEGATIVE_INFINITY), Vector1D.of(Double.NEGATIVE_INFINITY));
    }

    @Test
    void testEqualsAndHashCode_signedZeroConsistency_1_oe() {
        // arrange
        final Vector1D a = Vector1D.of(0.0);
        final Vector1D b = Vector1D.of(-0.0);
        final Vector1D c = Vector1D.of(0.0);
        final Vector1D d = Vector1D.of(-0.0);

        // act/assert
        Assertions.assertFalse(a.equals(b));
    }

    @Test
    void testEqualsAndHashCode_signedZeroConsistency_2_oe() {
        // arrange
        final Vector1D a = Vector1D.of(0.0);
        final Vector1D b = Vector1D.of(-0.0);
        final Vector1D c = Vector1D.of(0.0);
        final Vector1D d = Vector1D.of(-0.0);

        // act/assert
        // removed other assertion
        Assertions.assertNotEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void testEqualsAndHashCode_signedZeroConsistency_3_oe() {
        // arrange
        final Vector1D a = Vector1D.of(0.0);
        final Vector1D b = Vector1D.of(-0.0);
        final Vector1D c = Vector1D.of(0.0);
        final Vector1D d = Vector1D.of(-0.0);

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(a.equals(c));
    }

    @Test
    void testEqualsAndHashCode_signedZeroConsistency_4_oe() {
        // arrange
        final Vector1D a = Vector1D.of(0.0);
        final Vector1D b = Vector1D.of(-0.0);
        final Vector1D c = Vector1D.of(0.0);
        final Vector1D d = Vector1D.of(-0.0);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(a.hashCode(), c.hashCode());
    }

    @Test
    void testEqualsAndHashCode_signedZeroConsistency_5_oe() {
        // arrange
        final Vector1D a = Vector1D.of(0.0);
        final Vector1D b = Vector1D.of(-0.0);
        final Vector1D c = Vector1D.of(0.0);
        final Vector1D d = Vector1D.of(-0.0);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(b.equals(d));
    }

    @Test
    void testEqualsAndHashCode_signedZeroConsistency_6_oe() {
        // arrange
        final Vector1D a = Vector1D.of(0.0);
        final Vector1D b = Vector1D.of(-0.0);
        final Vector1D c = Vector1D.of(0.0);
        final Vector1D d = Vector1D.of(-0.0);

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(b.hashCode(), d.hashCode());
    }

    @Test
    void testToString_1_oe() {
        // arrange
        final Vector1D v = Vector1D.of(3);
        final Pattern pattern = Pattern.compile("\\(3.{0,2}\\)");

        // act
        final String str = v.toString();

        // assert
        Assertions.assertTrue(pattern.matcher(str).matches(), "Expected string " + str + " to match regex " + pattern);
    }

    @Test
    void testParse_failure_1_oe() {
        // act/assert
        try {
    Vector1D.parse("abc");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testUnitFrom_vector_1_oe() {
        // arrange
        final Vector1D vec = Vector1D.of(2);
        final Vector1D unitVec = Vector1D.Unit.from(2);

        // act/assert
        checkVector(Vector1D.Unit.from(vec), 1);
        Assertions.assertSame(unitVec, Vector1D.Unit.from(unitVec));
    }

    @Test
    void testUnitFrom_illegalNorm_1_oe() {
        try {
    Vector1D.Unit.from(0.0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testUnitFrom_illegalNorm_2_oe() {
        // removed other assertion
        try {
    Vector1D.Unit.from(Double.NaN);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testUnitFrom_illegalNorm_3_oe() {
        // removed other assertion
        // removed other assertion
        try {
    Vector1D.Unit.from(Double.NEGATIVE_INFINITY);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testUnitFrom_illegalNorm_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Vector1D.Unit.from(Double.POSITIVE_INFINITY);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testUnitFactoryOptimization_1_oe() {
        // An already normalized vector will avoid unnecessary creation.
        final Vector1D v = Vector1D.of(3).normalize();
        Assertions.assertSame(v, v.normalize());
    }

@Test
    void testNormalize_illegalNorm_1_oe() {
        // arrange
        final Pattern illegalNorm = Pattern.compile("^Illegal norm: (0\\.0|-?Infinity|NaN)");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(Vector1D.ZERO::normalize, IllegalArgumentException.class, illegalNorm);
    }

@Test
    void testNormalize_illegalNorm_2_oe() {
        // arrange
        final Pattern illegalNorm = Pattern.compile("^Illegal norm: (0\\.0|-?Infinity|NaN)");

        // act/assert
        // removed other assertion
        GeometryTestUtils.assertThrowsWithMessage(Vector1D.NaN::normalize, IllegalArgumentException.class, illegalNorm);
    }

@Test
    void testNormalize_illegalNorm_3_oe() {
        // arrange
        final Pattern illegalNorm = Pattern.compile("^Illegal norm: (0\\.0|-?Infinity|NaN)");

        // act/assert
        // removed other assertion
        // removed other assertion
        GeometryTestUtils.assertThrowsWithMessage(Vector1D.POSITIVE_INFINITY::normalize, IllegalArgumentException.class, illegalNorm);
    }

@Test
    void testNormalize_illegalNorm_4_oe() {
        // arrange
        final Pattern illegalNorm = Pattern.compile("^Illegal norm: (0\\.0|-?Infinity|NaN)");

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        GeometryTestUtils.assertThrowsWithMessage(Vector1D.NEGATIVE_INFINITY::normalize, IllegalArgumentException.class, illegalNorm);
    }

}
