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

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.spherical.SphericalTestUtils;
import org.apache.commons.numbers.angle.Angle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Transform1STest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Point1S ZERO = Point1S.ZERO;

    private static final Point1S HALF_PI = Point1S.of(Angle.PI_OVER_TWO);

    private static final Point1S PI = Point1S.of(Math.PI);

    private static final Point1S MINUS_HALF_PI = Point1S.of(-Angle.PI_OVER_TWO);

    @Test
    void testToString() {
        // arrange
        final Transform1S t = Transform1S.identity().negate().rotate(1);

        // act
        final String str = t.toString();

        // assert
        GeometryTestUtils.assertContains("Transform1S", str);
        GeometryTestUtils.assertContains("negate= true", str);
        GeometryTestUtils.assertContains("rotate= 1", str);
    }

    private static void checkInverse(final Transform1S t) {
        final Transform1S inv = t.inverse();

        for (double x = -Angle.TWO_PI; x <= 2 * Angle.TWO_PI; x += 0.2) {
            final Point1S pt = Point1S.of(x);

            SphericalTestUtils.assertPointsEqual(pt, inv.apply(t.apply(pt)), TEST_EPS);
            SphericalTestUtils.assertPointsEqual(pt, t.apply(inv.apply(pt)), TEST_EPS);
        }
    }

    @Test
    void testIdentity_1_oe() {
        // act
        final Transform1S t = Transform1S.identity();

        // assert
        Assertions.assertTrue(t.preservesOrientation());
    }

    @Test
    void testIdentity_2_oe() {
        // act
        final Transform1S t = Transform1S.identity();

        // assert
        // removed other assertion
        Assertions.assertFalse(t.isNegation());
    }

    @Test
    void testIdentity_3_oe() {
        // act
        final Transform1S t = Transform1S.identity();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, t.getRotation(), TEST_EPS);
    }

    @Test
    void testRotate_positive_1_oe() {
        // arrange
        final Transform1S t = Transform1S.createRotation(Angle.PI_OVER_TWO);

        // act/assert
        Assertions.assertTrue(t.preservesOrientation());
    }

    @Test
    void testRotate_positive_2_oe() {
        // arrange
        final Transform1S t = Transform1S.createRotation(Angle.PI_OVER_TWO);

        // act/assert
        // removed other assertion
        Assertions.assertFalse(t.isNegation());
    }

    @Test
    void testRotate_positive_3_oe() {
        // arrange
        final Transform1S t = Transform1S.createRotation(Angle.PI_OVER_TWO);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, t.getRotation(), TEST_EPS);
    }

    @Test
    void testRotate_negative_1_oe() {
        // arrange
        final Transform1S t = Transform1S.createRotation(-Angle.PI_OVER_TWO);

        // act/assert
        Assertions.assertTrue(t.preservesOrientation());
    }

    @Test
    void testRotate_negative_2_oe() {
        // arrange
        final Transform1S t = Transform1S.createRotation(-Angle.PI_OVER_TWO);

        // act/assert
        // removed other assertion
        Assertions.assertFalse(t.isNegation());
    }

    @Test
    void testRotate_negative_3_oe() {
        // arrange
        final Transform1S t = Transform1S.createRotation(-Angle.PI_OVER_TWO);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-Angle.PI_OVER_TWO, t.getRotation(), TEST_EPS);
    }

    @Test
    void testNegate_1_oe() {
        // arrange
        final Transform1S t = Transform1S.createNegation();

        // act/assert
        Assertions.assertFalse(t.preservesOrientation());
    }

    @Test
    void testNegate_2_oe() {
        // arrange
        final Transform1S t = Transform1S.createNegation();

        // act/assert
        // removed other assertion
        Assertions.assertTrue(t.isNegation());
    }

    @Test
    void testNegate_3_oe() {
        // arrange
        final Transform1S t = Transform1S.createNegation();

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, t.getRotation(), TEST_EPS);
    }

    @Test
    void testNegateThenRotate_1_oe() {
        // arrange
        final Transform1S t = Transform1S.createNegation().rotate(Angle.PI_OVER_TWO);

        // act/assert
        Assertions.assertFalse(t.preservesOrientation());
    }

    @Test
    void testNegateThenRotate_2_oe() {
        // arrange
        final Transform1S t = Transform1S.createNegation().rotate(Angle.PI_OVER_TWO);

        // act/assert
        // removed other assertion
        Assertions.assertTrue(t.isNegation());
    }

    @Test
    void testNegateThenRotate_3_oe() {
        // arrange
        final Transform1S t = Transform1S.createNegation().rotate(Angle.PI_OVER_TWO);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, t.getRotation(), TEST_EPS);
    }

    @Test
    void testRotateThenNegate_1_oe() {
        // arrange
        final Transform1S t = Transform1S.createRotation(Angle.PI_OVER_TWO).negate();

        // act/assert
        Assertions.assertFalse(t.preservesOrientation());
    }

    @Test
    void testRotateThenNegate_2_oe() {
        // arrange
        final Transform1S t = Transform1S.createRotation(Angle.PI_OVER_TWO).negate();

        // act/assert
        // removed other assertion
        Assertions.assertTrue(t.isNegation());
    }

    @Test
    void testRotateThenNegate_3_oe() {
        // arrange
        final Transform1S t = Transform1S.createRotation(Angle.PI_OVER_TWO).negate();

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-Angle.PI_OVER_TWO, t.getRotation(), TEST_EPS);
    }

    @Test
    void testMultiply_1_oe() {
        // arrange
        final Transform1S neg = Transform1S.identity().negate();
        final Transform1S rot = Transform1S.identity().rotate(Angle.PI_OVER_TWO);

        // act
        final Transform1S t = rot.multiply(neg);

        // assert
        Assertions.assertFalse(t.preservesOrientation());
    }

    @Test
    void testMultiply_2_oe() {
        // arrange
        final Transform1S neg = Transform1S.identity().negate();
        final Transform1S rot = Transform1S.identity().rotate(Angle.PI_OVER_TWO);

        // act
        final Transform1S t = rot.multiply(neg);

        // assert
        // removed other assertion
        Assertions.assertTrue(t.isNegation());
    }

    @Test
    void testMultiply_3_oe() {
        // arrange
        final Transform1S neg = Transform1S.identity().negate();
        final Transform1S rot = Transform1S.identity().rotate(Angle.PI_OVER_TWO);

        // act
        final Transform1S t = rot.multiply(neg);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, t.getRotation(), TEST_EPS);
    }

    @Test
    void testPreultiply_1_oe() {
        // arrange
        final Transform1S neg = Transform1S.identity().negate();
        final Transform1S rot = Transform1S.identity().rotate(Angle.PI_OVER_TWO);

        // act
        final Transform1S t = neg.premultiply(rot);

        // assert
        Assertions.assertFalse(t.preservesOrientation());
    }

    @Test
    void testPreultiply_2_oe() {
        // arrange
        final Transform1S neg = Transform1S.identity().negate();
        final Transform1S rot = Transform1S.identity().rotate(Angle.PI_OVER_TWO);

        // act
        final Transform1S t = neg.premultiply(rot);

        // assert
        // removed other assertion
        Assertions.assertTrue(t.isNegation());
    }

    @Test
    void testPreultiply_3_oe() {
        // arrange
        final Transform1S neg = Transform1S.identity().negate();
        final Transform1S rot = Transform1S.identity().rotate(Angle.PI_OVER_TWO);

        // act
        final Transform1S t = neg.premultiply(rot);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, t.getRotation(), TEST_EPS);
    }

    @Test
    void testHashCode_1_oe() {
        // arrange
        final Transform1S a = Transform1S.identity().negate().rotate(Angle.PI_OVER_TWO);
        final Transform1S b = Transform1S.identity().rotate(Angle.PI_OVER_TWO);
        final Transform1S c = Transform1S.identity().negate().rotate(-Angle.PI_OVER_TWO);
        final Transform1S d = Transform1S.identity().negate().rotate(Angle.PI_OVER_TWO);

        // act
        final int hash = a.hashCode();

        // assert
        Assertions.assertEquals(hash, a.hashCode());
    }

    @Test
    void testHashCode_2_oe() {
        // arrange
        final Transform1S a = Transform1S.identity().negate().rotate(Angle.PI_OVER_TWO);
        final Transform1S b = Transform1S.identity().rotate(Angle.PI_OVER_TWO);
        final Transform1S c = Transform1S.identity().negate().rotate(-Angle.PI_OVER_TWO);
        final Transform1S d = Transform1S.identity().negate().rotate(Angle.PI_OVER_TWO);

        // act
        final int hash = a.hashCode();

        // assert
        // removed other assertion

        Assertions.assertNotEquals(hash, b.hashCode());
    }

    @Test
    void testHashCode_3_oe() {
        // arrange
        final Transform1S a = Transform1S.identity().negate().rotate(Angle.PI_OVER_TWO);
        final Transform1S b = Transform1S.identity().rotate(Angle.PI_OVER_TWO);
        final Transform1S c = Transform1S.identity().negate().rotate(-Angle.PI_OVER_TWO);
        final Transform1S d = Transform1S.identity().negate().rotate(Angle.PI_OVER_TWO);

        // act
        final int hash = a.hashCode();

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNotEquals(hash, c.hashCode());
    }

    @Test
    void testHashCode_4_oe() {
        // arrange
        final Transform1S a = Transform1S.identity().negate().rotate(Angle.PI_OVER_TWO);
        final Transform1S b = Transform1S.identity().rotate(Angle.PI_OVER_TWO);
        final Transform1S c = Transform1S.identity().negate().rotate(-Angle.PI_OVER_TWO);
        final Transform1S d = Transform1S.identity().negate().rotate(Angle.PI_OVER_TWO);

        // act
        final int hash = a.hashCode();

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(hash, d.hashCode());
    }

    @Test
    void testEquals_2_oe() {
        // arrange
        final Transform1S a = Transform1S.identity().negate().rotate(Angle.PI_OVER_TWO);
        final Transform1S b = Transform1S.identity().rotate(Angle.PI_OVER_TWO);
        final Transform1S c = Transform1S.identity().negate().rotate(-Angle.PI_OVER_TWO);
        final Transform1S d = Transform1S.identity().negate().rotate(Angle.PI_OVER_TWO);

        // act/assert
        // removed other assertion

        Assertions.assertNotEquals(a, b);
    }

    @Test
    void testEquals_3_oe() {
        // arrange
        final Transform1S a = Transform1S.identity().negate().rotate(Angle.PI_OVER_TWO);
        final Transform1S b = Transform1S.identity().rotate(Angle.PI_OVER_TWO);
        final Transform1S c = Transform1S.identity().negate().rotate(-Angle.PI_OVER_TWO);
        final Transform1S d = Transform1S.identity().negate().rotate(Angle.PI_OVER_TWO);

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNotEquals(a, c);
    }

    @Test
    void testEquals_4_oe() {
        // arrange
        final Transform1S a = Transform1S.identity().negate().rotate(Angle.PI_OVER_TWO);
        final Transform1S b = Transform1S.identity().rotate(Angle.PI_OVER_TWO);
        final Transform1S c = Transform1S.identity().negate().rotate(-Angle.PI_OVER_TWO);
        final Transform1S d = Transform1S.identity().negate().rotate(Angle.PI_OVER_TWO);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(a, d);
    }

    @Test
    void testEquals_5_oe() {
        // arrange
        final Transform1S a = Transform1S.identity().negate().rotate(Angle.PI_OVER_TWO);
        final Transform1S b = Transform1S.identity().rotate(Angle.PI_OVER_TWO);
        final Transform1S c = Transform1S.identity().negate().rotate(-Angle.PI_OVER_TWO);
        final Transform1S d = Transform1S.identity().negate().rotate(Angle.PI_OVER_TWO);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(d, a);
    }

}
