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
    void testNormalize_illegalNorm() {
        // arrange
        final Pattern illegalNorm = Pattern.compile("^Illegal norm: (0\\.0|-?Infinity|NaN)");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(Vector1D.ZERO::normalize,
                IllegalArgumentException.class, illegalNorm);
        GeometryTestUtils.assertThrowsWithMessage(Vector1D.NaN::normalize,
                IllegalArgumentException.class, illegalNorm);
        GeometryTestUtils.assertThrowsWithMessage(Vector1D.POSITIVE_INFINITY::normalize,
                IllegalArgumentException.class, illegalNorm);
        GeometryTestUtils.assertThrowsWithMessage(Vector1D.NEGATIVE_INFINITY::normalize,
                IllegalArgumentException.class, illegalNorm);
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
        Assertions.assertThrows(IllegalArgumentException.class, Vector1D.ZERO::normalize);
    }

    @Test
    void testConstants_normalize_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, Vector1D.NaN::normalize);
    }

    @Test
    void testConstants_normalize_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, Vector1D.POSITIVE_INFINITY::normalize);
    }

    @Test
    void testConstants_normalize_4_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, Vector1D.NEGATIVE_INFINITY::normalize);
    }

    @Test
    void testWithNorm_illegalNorm_1_oe() {
        // act/assert
        try {
    Vector1D.ZERO.withNorm(2.0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testWithNorm_illegalNorm_2_oe() {
        // act/assert
        // removed other assertion
        try {
    Vector1D.NaN.withNorm(2.0);
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
    Vector1D.POSITIVE_INFINITY.withNorm(2.0);
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
    Vector1D.NEGATIVE_INFINITY.withNorm(2.0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAngle_illegalNorm_1_oe() {
        // arrange
        final Vector1D v = Vector1D.of(1.0);

        // act/assert
        try {
    Vector1D.ZERO.angle(v);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testParse_failure_1_oe() {
        // act/assert
        try {
    Vector1D.parse("abc");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testUnitFrom_illegalNorm_1_oe() {
        try {
    Vector1D.Unit.from(0.0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testUnitFrom_illegalNorm_2_oe() {
        // removed other assertion
        try {
    Vector1D.Unit.from(Double.NaN);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testUnitFrom_illegalNorm_3_oe() {
        // removed other assertion
        // removed other assertion
        try {
    Vector1D.Unit.from(Double.NEGATIVE_INFINITY);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
