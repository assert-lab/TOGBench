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
package org.apache.commons.numbers.quaternion;

import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class QuaternionTest_OE25Dev {
    /** Epsilon for double comparison. */
    private static final double EPS = Math.ulp(1d);
    /** Epsilon for double comparison. */
    private static final double COMPARISON_EPS = 1e-14;

    /* TODO remove dependency on Vector3D
    */
    /* TODO remove dependency on Vector3D
    @Test
    final void testProductQuaternionVector() {

        // Case : Product between a vector and a quaternion : QxV

        final Quaternion quaternion = Quaternion.of(4, 7, -1, 2);
        final double[] vector = {2.0, 1.0, 3.0};
        final Quaternion qResultQxV = Quaternion.multiply(quaternion, Quaternion.of(vector));

        Assert.assertEquals(-19, qResultQxV.getW(), EPS);
        Assert.assertEquals(3, qResultQxV.getX(), EPS);
        Assert.assertEquals(-13, qResultQxV.getY(), EPS);
        Assert.assertEquals(21, qResultQxV.getZ(), EPS);

        // comparison with the result given by the formula :
        // qResult = (- vectorQ . vector) + (scalarQ * vector + vectorQ ^ vector)

        final double[] vectorQ = quaternion.getVectorPart();
        final double[] vectorResultQxV = qResultQxV.getVectorPart();

        final double scalarPartRefQxV = -Vector3D.dotProduct(new Vector3D(vectorQ), new Vector3D(vector));
        Assert.assertEquals(scalarPartRefQxV, qResultQxV.getScalarPart(), EPS);

        final Vector3D vectorPartRefQxV = (new Vector3D(vector).scalarMultiply(quaternion.getScalarPart())).add(Vector3D
                .crossProduct(new Vector3D(vectorQ), new Vector3D(vector)));
        final double normQxV = (new Vector3D(vectorResultQxV).subtract(vectorPartRefQxV)).norm();
        Assert.assertEquals(0, normQxV, EPS);

        // Case : Product between a vector and a quaternion : VxQ

        final Quaternion qResultVxQ = Quaternion.multiply(Quaternion.of(vector), quaternion);

        Assert.assertEquals(-19, qResultVxQ.getW(), EPS);
        Assert.assertEquals(13, qResultVxQ.getX(), EPS);
        Assert.assertEquals(21, qResultVxQ.getY(), EPS);
        Assert.assertEquals(3, qResultVxQ.getZ(), EPS);

        final double[] vectorResultVxQ = qResultVxQ.getVectorPart();

        // comparison with the result given by the formula :
        // qResult = (- vector . vectorQ) + (scalarQ * vector + vector ^ vectorQ)

        final double scalarPartRefVxQ = -Vector3D.dotProduct(new Vector3D(vectorQ), new Vector3D(vector));
        Assert.assertEquals(scalarPartRefVxQ, qResultVxQ.getScalarPart(), EPS);

        final Vector3D vectorPartRefVxQ = (new Vector3D(vector).scalarMultiply(quaternion.getScalarPart())).add(Vector3D
                .crossProduct(new Vector3D(vector), new Vector3D(vectorQ)));
        final double normVxQ = (new Vector3D(vectorResultVxQ).subtract(vectorPartRefVxQ)).norm();
        Assert.assertEquals(0, normVxQ, EPS);
    }
    */
    @Test
    final void testDotProductQuaternionQuaternion() {
        // expected output
        final double expected = -6.;
        // inputs
        final Quaternion q1 = Quaternion.of(1, 2, 2, 1);
        final Quaternion q2 = Quaternion.of(3, -2, -1, -3);

        final double actual1 = Quaternion.dot(q1, q2);
        final double actual2 = q1.dot(q2);

        Assertions.assertEquals(expected, actual1, EPS);
        Assertions.assertEquals(expected, actual2, EPS);
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        Assertions.assertEquals(w, qa.getW(), EPS);
        Assertions.assertEquals(x, qa.getX(), EPS);
        Assertions.assertEquals(y, qa.getY(), EPS);
        Assertions.assertEquals(z, qa.getZ(), EPS);

        Assertions.assertEquals(w, qb.getW(), EPS);
        Assertions.assertEquals(x, qb.getX(), EPS);
        Assertions.assertEquals(y, qb.getY(), EPS);
        Assertions.assertEquals(z, qb.getZ(), EPS);
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        Assertions.assertEquals(w, qa.getW(), EPS);
        Assertions.assertEquals(x, qa.getX(), EPS);
        Assertions.assertEquals(y, qa.getY(), EPS);
        Assertions.assertEquals(z, qa.getZ(), EPS);

        Assertions.assertEquals(w, qb.getW(), EPS);
        Assertions.assertEquals(x, qb.getX(), EPS);
        Assertions.assertEquals(y, qb.getY(), EPS);
        Assertions.assertEquals(z, qb.getZ(), EPS);
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        Assertions.assertEquals(Math.sqrt(30), norm);

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        Assertions.assertEquals(Math.sqrt(normSquareRef), norm);
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        Assertions.assertEquals(2.0 / 5.0, versor.getW());
        Assertions.assertEquals(1.0 / 5.0, versor.getX());
        Assertions.assertEquals(-4.0 / 5.0, versor.getY());
        Assertions.assertEquals(-2.0 / 5.0, versor.getZ());

        Assertions.assertEquals(1, versor.norm());

        Assertions.assertSame(versor.normalize(), versor);
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        Assertions.assertThrows(IllegalStateException.class,
                q::normalize
        );
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        Assertions.assertThrows(IllegalStateException.class,
                q::normalize
        );

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        Assertions.assertThrows(IllegalStateException.class,
                q::normalize
        );
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        Assertions.assertThrows(IllegalStateException.class,
                q::normalize
        );
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        Assertions.assertEquals(q1, q1);

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        Assertions.assertEquals(q2, q1);

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        Assertions.assertNotEquals(q3, q1);

        Assertions.assertNotEquals(q3, "bar");
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());
        Assertions.assertNotEquals(x, y, msg);

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());
        Assertions.assertNotEquals(x, y, msg);

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());
        Assertions.assertNotEquals(x, y, msg);

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());
        Assertions.assertNotEquals(x, y, msg);
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        Assertions.assertFalse(q1.equals(q2, 0.9 * inc));
        Assertions.assertFalse(q1.equals(q3, 0.9 * inc));
        Assertions.assertFalse(q1.equals(q4, 0.9 * inc));
        Assertions.assertFalse(q1.equals(q5, 0.9 * inc));

        Assertions.assertTrue(q1.equals(q2, 1.1 * inc));
        Assertions.assertTrue(q1.equals(q3, 1.1 * inc));
        Assertions.assertTrue(q1.equals(q4, 1.1 * inc));
        Assertions.assertTrue(q1.equals(q5, 1.1 * inc));
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        Assertions.assertTrue(q1.equals(q2, 10 * gap));
        Assertions.assertFalse(q1.equals(q2, gap));
        Assertions.assertFalse(q1.equals(q2, gap / 10));
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            Assertions.assertTrue(q2.isUnit(COMPARISON_EPS));
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        Assertions.assertFalse(q.isUnit(COMPARISON_EPS));
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        Assertions.assertTrue(q1.isPure(EPS));

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        Assertions.assertTrue(q2.isPure(EPS));

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        Assertions.assertFalse(q3.isPure(EPS));

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        Assertions.assertTrue(q4.isPure(0));

        final Quaternion q5 = Quaternion.of(0, v);
        Assertions.assertTrue(q5.isPure(0));
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        assertEquals(q, expected, EPS);

        Assertions.assertSame(q.positivePolarForm(), q);
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        assertEquals(q, expected, EPS);

        Assertions.assertSame(q.positivePolarForm(), q);
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        Assertions.assertTrue(q.getW() >= 0);
        Assertions.assertSame(q.positivePolarForm(), q);
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        Assertions.assertTrue(q.getW() >= 0);
        Assertions.assertSame(q.positivePolarForm(), q);
    }

    @Test
    void testNegate() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        Assertions.assertEquals(-a, qNeg.getW());
        Assertions.assertEquals(-b, qNeg.getX());
        Assertions.assertEquals(-c, qNeg.getY());
        Assertions.assertEquals(-d, qNeg.getZ());

        Assertions.assertTrue(q.equals(qNeg.negate(), 0d));
    }

    @Test
    void testNegateNormalized() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).normalize();
        final Quaternion qNeg = q.negate();
        Assertions.assertTrue(q.equals(qNeg.negate(), 0d));
    }

    @Test
    void testNegatePositivePolarForm() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).positivePolarForm();
        final Quaternion qNeg = q.negate();
        Assertions.assertTrue(q.equals(qNeg.negate(), 0d));
    }

    /* TODO remove dependency on Rotation
    @Test
    final void testPolarForm() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q = Quaternion.of(2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5),
                                                2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5));
            final Quaternion qP = q.positivePolarForm();

            Assert.assertTrue(qP.isUnit(COMPARISON_EPS));
            Assert.assertTrue(qP.getW() >= 0);

            final Rotation rot = new Rotation(q.getW(), q.getX(), q.getY(), q.getZ(), true);
            final Rotation rotP = new Rotation(qP.getW(), qP.getX(), qP.getY(), qP.getZ(), true);

            Assert.assertEquals(rot.getAngle(), rotP.getAngle(), COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                COMPARISON_EPS);
        }
    }
*/
    @Test
    final void testInverse() {
        final Quaternion q = Quaternion.of(1.5, 4, 2, -2.5);

        final Quaternion inverseQ = q.inverse();
        Assertions.assertEquals(1.5 / 28.5, inverseQ.getW());
        Assertions.assertEquals(-4.0 / 28.5, inverseQ.getX());
        Assertions.assertEquals(-2.0 / 28.5, inverseQ.getY());
        Assertions.assertEquals(2.5 / 28.5, inverseQ.getZ());

        final Quaternion product = Quaternion.multiply(inverseQ, q);
        Assertions.assertEquals(1, product.getW(), EPS);
        Assertions.assertEquals(0, product.getX(), EPS);
        Assertions.assertEquals(0, product.getY(), EPS);
        Assertions.assertEquals(0, product.getZ(), EPS);

        final Quaternion qNul = Quaternion.of(0, 0, 0, 0);
        try {
            final Quaternion inverseQNul = qNul.inverse();
            Assertions.fail("expecting ZeroException but got : " + inverseQNul);
        } catch (IllegalStateException ex) {
            // expected
        }
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        Assertions.assertThrows(IllegalStateException.class,
                q::inverse
        );
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        Assertions.assertThrows(IllegalStateException.class,
                q::inverse
        );
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        Assertions.assertThrows(IllegalStateException.class,
                q::inverse
        );
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        Assertions.assertTrue(Quaternion.ONE.equals(result, EPS), result.toString());
    }

    @Test
    void testInversePositivePolarForm() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        Assertions.assertTrue(Quaternion.ONE.equals(result, EPS), result.toString());
    }

    @Test
    final void testMultiply() {
        final Quaternion q1 = Quaternion.of(1, 2, 3, 4);
        final Quaternion q2 = Quaternion.of(4, 3, 2, 1);
        final Quaternion actual = q1.multiply(q2);
        final double w = 1 * 4 - 2 * 3 - 3 * 2 - 4 * 1;
        final double x = 1 * 3 + 2 * 4 + 3 * 1 - 4 * 2;
        final double y = 1 * 2 - 2 * 1 + 3 * 4 + 4 * 3;
        final double z = 1 * 1 + 2 * 2 - 3 * 3 + 4 * 4;
        final Quaternion expected = Quaternion.of(w, x, y, z);
        assertEquals(actual, expected, EPS);
    }

    @Test
    final void testParseFromToString() {
        final Quaternion q = Quaternion.of(1.1, 2.2, 3.3, 4.4);
        Quaternion parsed = Quaternion.parse(q.toString());
        assertEquals(parsed, q, EPS);
    }

    @Test
    final void testParseSpecials() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        Assertions.assertEquals(1e-5, parsed.getW(), EPS);
        Assertions.assertTrue(Double.isInfinite(parsed.getX()));
        Assertions.assertTrue(Double.isNaN(parsed.getY()));
        Assertions.assertEquals(-0xa.cp0, parsed.getZ(), EPS);
    }

    @Test
    final void testParseMissingStart() {
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> Quaternion.parse("1.0 2.0 3.0 4.0]")
        );
    }

    @Test
    final void testParseMissingEnd() {
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> Quaternion.parse("[1.0 2.0 3.0 4.0")
        );
    }

    @Test
    final void testParseMissingPart() {
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> Quaternion.parse("[1.0 2.0 3.0 ]")
        );
    }

    @Test
    final void testParseInvalidScalar() {
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> Quaternion.parse("[1.x 2.0 3.0 4.0]")
        );
    }

    @Test
    final void testParseInvalidI() {
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> Quaternion.parse("[1.0 2.0x 3.0 4.0]")
        );
    }

    @Test
    final void testParseInvalidJ() {
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> Quaternion.parse("[1.0 2.0 3.0x 4.0]")
        );
    }

    @Test
    final void testParseInvalidK() {
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> Quaternion.parse("[1.0 2.0 3.0 4.0x]")
        );
    }

    @Test
    final void testToString() {
        final Quaternion q = Quaternion.of(1, 2, 3, 4);
        Assertions.assertEquals("[1.0 2.0 3.0 4.0]", q.toString());
    }

    /**
     * Assert that two quaternions are equal within tolerance
     * @param actual
     * @param expected
     * @param tolerance
     */
    private void assertEquals(Quaternion actual, Quaternion expected, double tolerance) {
        Assertions.assertTrue(actual.equals(expected, tolerance), "expecting " + expected + " but got " + actual);
    }

    @Test
    void testZeroQuaternion_1_oe() {
        Assertions.assertEquals(0, Quaternion.ZERO.norm());
    }

    @Test
    void testUnitQuaternions_1_oe() {
        Assertions.assertEquals(1, Quaternion.ONE.norm());
    }

    @Test
    void testUnitQuaternions_2_oe() {
        // removed other assertion
        Assertions.assertSame(Quaternion.ONE, Quaternion.ONE.normalize());
    }

    @Test
    void testUnitQuaternions_3_oe() {
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, Quaternion.I.norm());
    }

    @Test
    void testUnitQuaternions_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(Quaternion.I, Quaternion.I.normalize());
    }

    @Test
    void testUnitQuaternions_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, Quaternion.J.norm());
    }

    @Test
    void testUnitQuaternions_6_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(Quaternion.J, Quaternion.J.normalize());
    }

    @Test
    void testUnitQuaternions_7_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, Quaternion.K.norm());
    }

    @Test
    void testUnitQuaternions_8_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(Quaternion.K, Quaternion.K.normalize());
    }

    @Test
    final void testAccessors1_1_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        Assertions.assertEquals(q0, q.getW());
    }

    @Test
    final void testAccessors1_2_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        // removed other assertion
        Assertions.assertEquals(q1, q.getX());
    }

    @Test
    final void testAccessors1_3_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(q2, q.getY());
    }

    @Test
    final void testAccessors1_4_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(q3, q.getZ());
    }

    @Test
    final void testAccessors2_1_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double sP = q.getScalarPart();
        final double[] vP = q.getVectorPart();

        Assertions.assertEquals(q0, sP);
    }

    @Test
    final void testAccessors2_2_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double sP = q.getScalarPart();
        final double[] vP = q.getVectorPart();

        // removed other assertion
        Assertions.assertEquals(q1, vP[0]);
    }

    @Test
    final void testAccessors2_3_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double sP = q.getScalarPart();
        final double[] vP = q.getVectorPart();

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(q2, vP[1]);
    }

    @Test
    final void testAccessors2_4_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double sP = q.getScalarPart();
        final double[] vP = q.getVectorPart();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(q3, vP[2]);
    }

    @Test
    final void testAccessors3_1_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, new double[] {q1, q2, q3});

        final double sP = q.getScalarPart();
        final double[] vP = q.getVectorPart();

        Assertions.assertEquals(q0, sP);
    }

    @Test
    final void testAccessors3_2_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, new double[] {q1, q2, q3});

        final double sP = q.getScalarPart();
        final double[] vP = q.getVectorPart();

        // removed other assertion
        Assertions.assertEquals(q1, vP[0]);
    }

    @Test
    final void testAccessors3_3_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, new double[] {q1, q2, q3});

        final double sP = q.getScalarPart();
        final double[] vP = q.getVectorPart();

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(q2, vP[1]);
    }

    @Test
    final void testAccessors3_4_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, new double[] {q1, q2, q3});

        final double sP = q.getScalarPart();
        final double[] vP = q.getVectorPart();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(q3, vP[2]);
    }

    @Test
    void testWrongDimension_1_oe() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Quaternion.of(new double[] {1, 2}) );
    }

    @Test
    final void testConjugate_1_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final Quaternion qConjugate = q.conjugate();

        Assertions.assertEquals(q0, qConjugate.getW());
    }

    @Test
    final void testConjugate_2_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final Quaternion qConjugate = q.conjugate();

        // removed other assertion
        Assertions.assertEquals(-q1, qConjugate.getX());
    }

    @Test
    final void testConjugate_3_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final Quaternion qConjugate = q.conjugate();

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-q2, qConjugate.getY());
    }

    @Test
    final void testConjugate_4_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final Quaternion qConjugate = q.conjugate();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-q3, qConjugate.getZ());
    }

    @Test
    final void testProductQuaternionQuaternion_1_oe() {

        // Case : analytic test case

        final Quaternion qA = Quaternion.of(1, 0.5, -3, 4);
        final Quaternion qB = Quaternion.of(6, 2, 1, -9);
        final Quaternion qResult = Quaternion.multiply(qA, qB);

        Assert.assertEquals(44, qResult.getW(), EPS);
    }

    @Test
    final void testProductQuaternionQuaternion_2_oe() {

        // Case : analytic test case

        final Quaternion qA = Quaternion.of(1, 0.5, -3, 4);
        final Quaternion qB = Quaternion.of(6, 2, 1, -9);
        final Quaternion qResult = Quaternion.multiply(qA, qB);

        // removed other assertion
        Assert.assertEquals(28, qResult.getX(), EPS);
    }

    @Test
    final void testProductQuaternionQuaternion_3_oe() {

        // Case : analytic test case

        final Quaternion qA = Quaternion.of(1, 0.5, -3, 4);
        final Quaternion qB = Quaternion.of(6, 2, 1, -9);
        final Quaternion qResult = Quaternion.multiply(qA, qB);

        // removed other assertion
        // removed other assertion
        Assert.assertEquals(-4.5, qResult.getY(), EPS);
    }

    @Test
    final void testProductQuaternionQuaternion_4_oe() {

        // Case : analytic test case

        final Quaternion qA = Quaternion.of(1, 0.5, -3, 4);
        final Quaternion qB = Quaternion.of(6, 2, 1, -9);
        final Quaternion qResult = Quaternion.multiply(qA, qB);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals(21.5, qResult.getZ(), EPS);
    }

    @Test
    final void testProductQuaternionQuaternion_5_oe() {

        // Case : analytic test case

        final Quaternion qA = Quaternion.of(1, 0.5, -3, 4);
        final Quaternion qB = Quaternion.of(6, 2, 1, -9);
        final Quaternion qResult = Quaternion.multiply(qA, qB);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // comparison with the result given by the formula :
        // qResult = (scalarA * scalarB - vectorA . vectorB) + (scalarA * vectorB + scalarB * vectorA + vectorA ^
        // vectorB)

        final Vector3D vectorA = new Vector3D(qA.getVectorPart());
        final Vector3D vectorB = new Vector3D(qB.getVectorPart());
        final Vector3D vectorResult = new Vector3D(qResult.getVectorPart());

        final double scalarPartRef = qA.getScalarPart() * qB.getScalarPart() - Vector3D.dotProduct(vectorA, vectorB);

        Assert.assertEquals(scalarPartRef, qResult.getScalarPart(), EPS);
    }

    @Test
    final void testProductQuaternionQuaternion_6_oe() {

        // Case : analytic test case

        final Quaternion qA = Quaternion.of(1, 0.5, -3, 4);
        final Quaternion qB = Quaternion.of(6, 2, 1, -9);
        final Quaternion qResult = Quaternion.multiply(qA, qB);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // comparison with the result given by the formula :
        // qResult = (scalarA * scalarB - vectorA . vectorB) + (scalarA * vectorB + scalarB * vectorA + vectorA ^
        // vectorB)

        final Vector3D vectorA = new Vector3D(qA.getVectorPart());
        final Vector3D vectorB = new Vector3D(qB.getVectorPart());
        final Vector3D vectorResult = new Vector3D(qResult.getVectorPart());

        final double scalarPartRef = qA.getScalarPart() * qB.getScalarPart() - Vector3D.dotProduct(vectorA, vectorB);

        // removed other assertion

        final Vector3D vectorPartRef = ((vectorA.scalarMultiply(qB.getScalarPart())).add(vectorB.scalarMultiply(qA
                .getScalarPart()))).add(Vector3D.crossProduct(vectorA, vectorB));
        final double norm = (vectorResult.subtract(vectorPartRef)).norm();

        Assert.assertEquals(0, norm, EPS);
    }

    @Test
    final void testProductQuaternionQuaternion_7_oe() {

        // Case : analytic test case

        final Quaternion qA = Quaternion.of(1, 0.5, -3, 4);
        final Quaternion qB = Quaternion.of(6, 2, 1, -9);
        final Quaternion qResult = Quaternion.multiply(qA, qB);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // comparison with the result given by the formula :
        // qResult = (scalarA * scalarB - vectorA . vectorB) + (scalarA * vectorB + scalarB * vectorA + vectorA ^
        // vectorB)

        final Vector3D vectorA = new Vector3D(qA.getVectorPart());
        final Vector3D vectorB = new Vector3D(qB.getVectorPart());
        final Vector3D vectorResult = new Vector3D(qResult.getVectorPart());

        final double scalarPartRef = qA.getScalarPart() * qB.getScalarPart() - Vector3D.dotProduct(vectorA, vectorB);

        // removed other assertion

        final Vector3D vectorPartRef = ((vectorA.scalarMultiply(qB.getScalarPart())).add(vectorB.scalarMultiply(qA
                .getScalarPart()))).add(Vector3D.crossProduct(vectorA, vectorB));
        final double norm = (vectorResult.subtract(vectorPartRef)).norm();

        // removed other assertion

        // Conjugate of the product of two quaternions and product of their conjugates :
        // Conj(qA * qB) = Conj(qB) * Conj(qA)

        final Quaternion conjugateOfProduct = qB.getConjugate().multiply(qA.getConjugate());
        final Quaternion productOfConjugate = (qA.multiply(qB)).getConjugate();

        Assert.assertEquals(conjugateOfProduct.getW(), productOfConjugate.getW(), EPS);
    }

    @Test
    final void testProductQuaternionQuaternion_8_oe() {

        // Case : analytic test case

        final Quaternion qA = Quaternion.of(1, 0.5, -3, 4);
        final Quaternion qB = Quaternion.of(6, 2, 1, -9);
        final Quaternion qResult = Quaternion.multiply(qA, qB);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // comparison with the result given by the formula :
        // qResult = (scalarA * scalarB - vectorA . vectorB) + (scalarA * vectorB + scalarB * vectorA + vectorA ^
        // vectorB)

        final Vector3D vectorA = new Vector3D(qA.getVectorPart());
        final Vector3D vectorB = new Vector3D(qB.getVectorPart());
        final Vector3D vectorResult = new Vector3D(qResult.getVectorPart());

        final double scalarPartRef = qA.getScalarPart() * qB.getScalarPart() - Vector3D.dotProduct(vectorA, vectorB);

        // removed other assertion

        final Vector3D vectorPartRef = ((vectorA.scalarMultiply(qB.getScalarPart())).add(vectorB.scalarMultiply(qA
                .getScalarPart()))).add(Vector3D.crossProduct(vectorA, vectorB));
        final double norm = (vectorResult.subtract(vectorPartRef)).norm();

        // removed other assertion

        // Conjugate of the product of two quaternions and product of their conjugates :
        // Conj(qA * qB) = Conj(qB) * Conj(qA)

        final Quaternion conjugateOfProduct = qB.getConjugate().multiply(qA.getConjugate());
        final Quaternion productOfConjugate = (qA.multiply(qB)).getConjugate();

        // removed other assertion
        Assert.assertEquals(conjugateOfProduct.getX(), productOfConjugate.getX(), EPS);
    }

    @Test
    final void testProductQuaternionQuaternion_9_oe() {

        // Case : analytic test case

        final Quaternion qA = Quaternion.of(1, 0.5, -3, 4);
        final Quaternion qB = Quaternion.of(6, 2, 1, -9);
        final Quaternion qResult = Quaternion.multiply(qA, qB);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // comparison with the result given by the formula :
        // qResult = (scalarA * scalarB - vectorA . vectorB) + (scalarA * vectorB + scalarB * vectorA + vectorA ^
        // vectorB)

        final Vector3D vectorA = new Vector3D(qA.getVectorPart());
        final Vector3D vectorB = new Vector3D(qB.getVectorPart());
        final Vector3D vectorResult = new Vector3D(qResult.getVectorPart());

        final double scalarPartRef = qA.getScalarPart() * qB.getScalarPart() - Vector3D.dotProduct(vectorA, vectorB);

        // removed other assertion

        final Vector3D vectorPartRef = ((vectorA.scalarMultiply(qB.getScalarPart())).add(vectorB.scalarMultiply(qA
                .getScalarPart()))).add(Vector3D.crossProduct(vectorA, vectorB));
        final double norm = (vectorResult.subtract(vectorPartRef)).norm();

        // removed other assertion

        // Conjugate of the product of two quaternions and product of their conjugates :
        // Conj(qA * qB) = Conj(qB) * Conj(qA)

        final Quaternion conjugateOfProduct = qB.getConjugate().multiply(qA.getConjugate());
        final Quaternion productOfConjugate = (qA.multiply(qB)).getConjugate();

        // removed other assertion
        // removed other assertion
        Assert.assertEquals(conjugateOfProduct.getY(), productOfConjugate.getY(), EPS);
    }

    @Test
    final void testProductQuaternionQuaternion_10_oe() {

        // Case : analytic test case

        final Quaternion qA = Quaternion.of(1, 0.5, -3, 4);
        final Quaternion qB = Quaternion.of(6, 2, 1, -9);
        final Quaternion qResult = Quaternion.multiply(qA, qB);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // comparison with the result given by the formula :
        // qResult = (scalarA * scalarB - vectorA . vectorB) + (scalarA * vectorB + scalarB * vectorA + vectorA ^
        // vectorB)

        final Vector3D vectorA = new Vector3D(qA.getVectorPart());
        final Vector3D vectorB = new Vector3D(qB.getVectorPart());
        final Vector3D vectorResult = new Vector3D(qResult.getVectorPart());

        final double scalarPartRef = qA.getScalarPart() * qB.getScalarPart() - Vector3D.dotProduct(vectorA, vectorB);

        // removed other assertion

        final Vector3D vectorPartRef = ((vectorA.scalarMultiply(qB.getScalarPart())).add(vectorB.scalarMultiply(qA
                .getScalarPart()))).add(Vector3D.crossProduct(vectorA, vectorB));
        final double norm = (vectorResult.subtract(vectorPartRef)).norm();

        // removed other assertion

        // Conjugate of the product of two quaternions and product of their conjugates :
        // Conj(qA * qB) = Conj(qB) * Conj(qA)

        final Quaternion conjugateOfProduct = qB.getConjugate().multiply(qA.getConjugate());
        final Quaternion productOfConjugate = (qA.multiply(qB)).getConjugate();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals(conjugateOfProduct.getZ(), productOfConjugate.getZ(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_1_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        Assertions.assertEquals(w, q.getW(), COMPARISON_EPS);
    }

    @Test
    final void testScalarMultiplyDouble_2_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        Assertions.assertEquals(x, q.getX(), COMPARISON_EPS);
    }

    @Test
    final void testScalarMultiplyDouble_3_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(y, q.getY(), COMPARISON_EPS);
    }

    @Test
    final void testScalarMultiplyDouble_4_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(z, q.getZ(), COMPARISON_EPS);
    }

    @Test
    final void testScalarMultiplyDouble_5_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        Assertions.assertEquals(w, qa.getW(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_6_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        Assertions.assertEquals(x, qa.getX(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_7_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(y, qa.getY(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_8_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(z, qa.getZ(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_9_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(w, qb.getW(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_10_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(x, qb.getX(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_11_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(y, qb.getY(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_12_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(z, qb.getZ(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_13_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        Assertions.assertEquals(w, qa.getW(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_14_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        Assertions.assertEquals(x, qa.getX(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_15_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(y, qa.getY(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_16_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(z, qa.getZ(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_17_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(w, qb.getW(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_18_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(x, qb.getX(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_19_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(y, qb.getY(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_20_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(z, qb.getZ(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_21_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        Assertions.assertEquals(Math.sqrt(30), norm);
    }

    @Test
    final void testScalarMultiplyDouble_22_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        Assertions.assertEquals(Math.sqrt(normSquareRef), norm);
    }

    @Test
    final void testScalarMultiplyDouble_23_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        Assertions.assertEquals(2.0 / 5.0, versor.getW());
    }

    @Test
    final void testScalarMultiplyDouble_24_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        Assertions.assertEquals(1.0 / 5.0, versor.getX());
    }

    @Test
    final void testScalarMultiplyDouble_25_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-4.0 / 5.0, versor.getY());
    }

    @Test
    final void testScalarMultiplyDouble_26_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-2.0 / 5.0, versor.getZ());
    }

    @Test
    final void testScalarMultiplyDouble_27_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, versor.norm());
    }

    @Test
    final void testScalarMultiplyDouble_28_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertSame(versor.normalize(), versor);
    }

    @Test
    final void testScalarMultiplyDouble_29_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        Assertions.assertThrows(IllegalStateException.class, q::normalize );
    }

    @Test
    final void testScalarMultiplyDouble_30_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        Assertions.assertThrows(IllegalStateException.class, q::normalize );
    }

    @Test
    final void testScalarMultiplyDouble_31_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        Assertions.assertThrows(IllegalStateException.class, q::normalize );
    }

    @Test
    final void testScalarMultiplyDouble_32_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        Assertions.assertThrows(IllegalStateException.class, q::normalize );
    }

    @Test
    final void testScalarMultiplyDouble_33_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        Assertions.assertEquals(q1, q1);
    }

    @Test
    final void testScalarMultiplyDouble_34_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        Assertions.assertEquals(q2, q1);
    }

    @Test
    final void testScalarMultiplyDouble_35_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        Assertions.assertNotEquals(q3, q1);
    }

    @Test
    final void testScalarMultiplyDouble_36_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        Assertions.assertNotEquals(q3, "bar");
    }

    @Test
    final void testScalarMultiplyDouble_37_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());
    }

    @Test
    final void testScalarMultiplyDouble_38_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());
    }

    @Test
    final void testScalarMultiplyDouble_39_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());
    }

    @Test
    final void testScalarMultiplyDouble_40_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        Assertions.assertNotEquals(x, y, msg);
    }

    @Test
    final void testScalarMultiplyDouble_41_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());
    }

    @Test
    final void testScalarMultiplyDouble_42_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        Assertions.assertNotEquals(x, y, msg);
    }

    @Test
    final void testScalarMultiplyDouble_43_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());
    }

    @Test
    final void testScalarMultiplyDouble_44_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        Assertions.assertNotEquals(x, y, msg);
    }

    @Test
    final void testScalarMultiplyDouble_45_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());
    }

    @Test
    final void testScalarMultiplyDouble_46_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        Assertions.assertNotEquals(x, y, msg);
    }

    @Test
    final void testScalarMultiplyDouble_47_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        Assertions.assertFalse(q1.equals(q2, 0.9 * inc));
    }

    @Test
    final void testScalarMultiplyDouble_48_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        Assertions.assertFalse(q1.equals(q3, 0.9 * inc));
    }

    @Test
    final void testScalarMultiplyDouble_49_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(q1.equals(q4, 0.9 * inc));
    }

    @Test
    final void testScalarMultiplyDouble_50_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(q1.equals(q5, 0.9 * inc));
    }

    @Test
    final void testScalarMultiplyDouble_51_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(q1.equals(q2, 1.1 * inc));
    }

    @Test
    final void testScalarMultiplyDouble_52_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(q1.equals(q3, 1.1 * inc));
    }

    @Test
    final void testScalarMultiplyDouble_53_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(q1.equals(q4, 1.1 * inc));
    }

    @Test
    final void testScalarMultiplyDouble_54_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(q1.equals(q5, 1.1 * inc));
    }

    @Test
    final void testScalarMultiplyDouble_55_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        Assertions.assertTrue(q1.equals(q2, 10 * gap));
    }

    @Test
    final void testScalarMultiplyDouble_56_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        Assertions.assertFalse(q1.equals(q2, gap));
    }

    @Test
    final void testScalarMultiplyDouble_57_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(q1.equals(q2, gap / 10));
    }

    @Test
    final void testScalarMultiplyDouble_58_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            Assertions.assertTrue(q2.isUnit(COMPARISON_EPS));
    }
    }

    @Test
    final void testScalarMultiplyDouble_59_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        Assertions.assertFalse(q.isUnit(COMPARISON_EPS));
    }

    @Test
    final void testScalarMultiplyDouble_60_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        Assertions.assertTrue(q1.isPure(EPS));
    }

    @Test
    final void testScalarMultiplyDouble_61_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        Assertions.assertTrue(q2.isPure(EPS));
    }

    @Test
    final void testScalarMultiplyDouble_62_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        Assertions.assertFalse(q3.isPure(EPS));
    }

    @Test
    final void testScalarMultiplyDouble_63_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        Assertions.assertTrue(q4.isPure(0));
    }

    @Test
    final void testScalarMultiplyDouble_64_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        Assertions.assertTrue(q5.isPure(0));
    }

    @Test
    final void testScalarMultiplyDouble_65_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        assertEquals(q, expected, EPS);
    }

    @Test
    final void testScalarMultiplyDouble_66_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        Assertions.assertSame(q.positivePolarForm(), q);
    }

    @Test
    final void testScalarMultiplyDouble_67_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        assertEquals(q, expected, EPS);
    }

    @Test
    final void testScalarMultiplyDouble_68_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        Assertions.assertSame(q.positivePolarForm(), q);
    }

    @Test
    final void testScalarMultiplyDouble_69_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        Assertions.assertTrue(q.getW() >= 0);
    }

    @Test
    final void testScalarMultiplyDouble_70_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        Assertions.assertSame(q.positivePolarForm(), q);
    }

    @Test
    final void testScalarMultiplyDouble_71_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        Assertions.assertTrue(q.getW() >= 0);
    }

    @Test
    final void testScalarMultiplyDouble_72_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        // removed other assertion
        Assertions.assertSame(q.positivePolarForm(), q);
    }

    @Test
    final void testScalarMultiplyDouble_73_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    void testNegate() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        Assertions.assertEquals(-a, qNeg.getW());
    }

    @Test
    final void testScalarMultiplyDouble_74_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    void testNegate() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        // removed other assertion
        Assertions.assertEquals(-b, qNeg.getX());
    }

    @Test
    final void testScalarMultiplyDouble_75_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    void testNegate() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-c, qNeg.getY());
    }

    @Test
    final void testScalarMultiplyDouble_76_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    void testNegate() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-d, qNeg.getZ());
    }

    @Test
    final void testScalarMultiplyDouble_77_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    void testNegate() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(q.equals(qNeg.negate(), 0d));
    }

    @Test
    final void testScalarMultiplyDouble_78_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    void testNegate() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testNegateNormalized() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).normalize();
        final Quaternion qNeg = q.negate();
        Assertions.assertTrue(q.equals(qNeg.negate(), 0d));
    }

    @Test
    final void testScalarMultiplyDouble_79_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    void testNegate() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testNegateNormalized() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).normalize();
        final Quaternion qNeg = q.negate();
        // removed other assertion
    }

    @Test
    void testNegatePositivePolarForm() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).positivePolarForm();
        final Quaternion qNeg = q.negate();
        Assertions.assertTrue(q.equals(qNeg.negate(), 0d));
    }

    @Test
    final void testScalarMultiplyDouble_80_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    void testNegate() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testNegateNormalized() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).normalize();
        final Quaternion qNeg = q.negate();
        // removed other assertion
    }

    @Test
    void testNegatePositivePolarForm() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).positivePolarForm();
        final Quaternion qNeg = q.negate();
        // removed other assertion
    }

    /* TODO remove dependency on Rotation
    @Test
    final void testPolarForm() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q = Quaternion.of(2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5),
                                                2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5));
            final Quaternion qP = q.positivePolarForm();

            Assert.assertTrue(qP.isUnit(COMPARISON_EPS));
            Assert.assertTrue(qP.getW() >= 0);

            final Rotation rot = new Rotation(q.getW(), q.getX(), q.getY(), q.getZ(), true);
            final Rotation rotP = new Rotation(qP.getW(), qP.getX(), qP.getY(), qP.getZ(), true);

            Assert.assertEquals(rot.getAngle(), rotP.getAngle(), COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                COMPARISON_EPS);
        }
    }
*/
    @Test
    final void testInverse() {
        final Quaternion q = Quaternion.of(1.5, 4, 2, -2.5);

        final Quaternion inverseQ = q.inverse();
        Assertions.assertEquals(1.5 / 28.5, inverseQ.getW());
    }

    @Test
    final void testScalarMultiplyDouble_81_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    void testNegate() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testNegateNormalized() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).normalize();
        final Quaternion qNeg = q.negate();
        // removed other assertion
    }

    @Test
    void testNegatePositivePolarForm() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).positivePolarForm();
        final Quaternion qNeg = q.negate();
        // removed other assertion
    }

    /* TODO remove dependency on Rotation
    @Test
    final void testPolarForm() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q = Quaternion.of(2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5),
                                                2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5));
            final Quaternion qP = q.positivePolarForm();

            Assert.assertTrue(qP.isUnit(COMPARISON_EPS));
            Assert.assertTrue(qP.getW() >= 0);

            final Rotation rot = new Rotation(q.getW(), q.getX(), q.getY(), q.getZ(), true);
            final Rotation rotP = new Rotation(qP.getW(), qP.getX(), qP.getY(), qP.getZ(), true);

            Assert.assertEquals(rot.getAngle(), rotP.getAngle(), COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                COMPARISON_EPS);
        }
    }
*/
    @Test
    final void testInverse() {
        final Quaternion q = Quaternion.of(1.5, 4, 2, -2.5);

        final Quaternion inverseQ = q.inverse();
        // removed other assertion
        Assertions.assertEquals(-4.0 / 28.5, inverseQ.getX());
    }

    @Test
    final void testScalarMultiplyDouble_82_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    void testNegate() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testNegateNormalized() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).normalize();
        final Quaternion qNeg = q.negate();
        // removed other assertion
    }

    @Test
    void testNegatePositivePolarForm() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).positivePolarForm();
        final Quaternion qNeg = q.negate();
        // removed other assertion
    }

    /* TODO remove dependency on Rotation
    @Test
    final void testPolarForm() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q = Quaternion.of(2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5),
                                                2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5));
            final Quaternion qP = q.positivePolarForm();

            Assert.assertTrue(qP.isUnit(COMPARISON_EPS));
            Assert.assertTrue(qP.getW() >= 0);

            final Rotation rot = new Rotation(q.getW(), q.getX(), q.getY(), q.getZ(), true);
            final Rotation rotP = new Rotation(qP.getW(), qP.getX(), qP.getY(), qP.getZ(), true);

            Assert.assertEquals(rot.getAngle(), rotP.getAngle(), COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                COMPARISON_EPS);
        }
    }
*/
    @Test
    final void testInverse() {
        final Quaternion q = Quaternion.of(1.5, 4, 2, -2.5);

        final Quaternion inverseQ = q.inverse();
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-2.0 / 28.5, inverseQ.getY());
    }

    @Test
    final void testScalarMultiplyDouble_83_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    void testNegate() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testNegateNormalized() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).normalize();
        final Quaternion qNeg = q.negate();
        // removed other assertion
    }

    @Test
    void testNegatePositivePolarForm() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).positivePolarForm();
        final Quaternion qNeg = q.negate();
        // removed other assertion
    }

    /* TODO remove dependency on Rotation
    @Test
    final void testPolarForm() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q = Quaternion.of(2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5),
                                                2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5));
            final Quaternion qP = q.positivePolarForm();

            Assert.assertTrue(qP.isUnit(COMPARISON_EPS));
            Assert.assertTrue(qP.getW() >= 0);

            final Rotation rot = new Rotation(q.getW(), q.getX(), q.getY(), q.getZ(), true);
            final Rotation rotP = new Rotation(qP.getW(), qP.getX(), qP.getY(), qP.getZ(), true);

            Assert.assertEquals(rot.getAngle(), rotP.getAngle(), COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                COMPARISON_EPS);
        }
    }
*/
    @Test
    final void testInverse() {
        final Quaternion q = Quaternion.of(1.5, 4, 2, -2.5);

        final Quaternion inverseQ = q.inverse();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(2.5 / 28.5, inverseQ.getZ());
    }

    @Test
    final void testScalarMultiplyDouble_84_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    void testNegate() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testNegateNormalized() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).normalize();
        final Quaternion qNeg = q.negate();
        // removed other assertion
    }

    @Test
    void testNegatePositivePolarForm() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).positivePolarForm();
        final Quaternion qNeg = q.negate();
        // removed other assertion
    }

    /* TODO remove dependency on Rotation
    @Test
    final void testPolarForm() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q = Quaternion.of(2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5),
                                                2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5));
            final Quaternion qP = q.positivePolarForm();

            Assert.assertTrue(qP.isUnit(COMPARISON_EPS));
            Assert.assertTrue(qP.getW() >= 0);

            final Rotation rot = new Rotation(q.getW(), q.getX(), q.getY(), q.getZ(), true);
            final Rotation rotP = new Rotation(qP.getW(), qP.getX(), qP.getY(), qP.getZ(), true);

            Assert.assertEquals(rot.getAngle(), rotP.getAngle(), COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                COMPARISON_EPS);
        }
    }
*/
    @Test
    final void testInverse() {
        final Quaternion q = Quaternion.of(1.5, 4, 2, -2.5);

        final Quaternion inverseQ = q.inverse();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Quaternion product = Quaternion.multiply(inverseQ, q);
        Assertions.assertEquals(1, product.getW(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_85_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    void testNegate() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testNegateNormalized() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).normalize();
        final Quaternion qNeg = q.negate();
        // removed other assertion
    }

    @Test
    void testNegatePositivePolarForm() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).positivePolarForm();
        final Quaternion qNeg = q.negate();
        // removed other assertion
    }

    /* TODO remove dependency on Rotation
    @Test
    final void testPolarForm() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q = Quaternion.of(2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5),
                                                2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5));
            final Quaternion qP = q.positivePolarForm();

            Assert.assertTrue(qP.isUnit(COMPARISON_EPS));
            Assert.assertTrue(qP.getW() >= 0);

            final Rotation rot = new Rotation(q.getW(), q.getX(), q.getY(), q.getZ(), true);
            final Rotation rotP = new Rotation(qP.getW(), qP.getX(), qP.getY(), qP.getZ(), true);

            Assert.assertEquals(rot.getAngle(), rotP.getAngle(), COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                COMPARISON_EPS);
        }
    }
*/
    @Test
    final void testInverse() {
        final Quaternion q = Quaternion.of(1.5, 4, 2, -2.5);

        final Quaternion inverseQ = q.inverse();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Quaternion product = Quaternion.multiply(inverseQ, q);
        // removed other assertion
        Assertions.assertEquals(0, product.getX(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_86_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    void testNegate() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testNegateNormalized() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).normalize();
        final Quaternion qNeg = q.negate();
        // removed other assertion
    }

    @Test
    void testNegatePositivePolarForm() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).positivePolarForm();
        final Quaternion qNeg = q.negate();
        // removed other assertion
    }

    /* TODO remove dependency on Rotation
    @Test
    final void testPolarForm() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q = Quaternion.of(2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5),
                                                2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5));
            final Quaternion qP = q.positivePolarForm();

            Assert.assertTrue(qP.isUnit(COMPARISON_EPS));
            Assert.assertTrue(qP.getW() >= 0);

            final Rotation rot = new Rotation(q.getW(), q.getX(), q.getY(), q.getZ(), true);
            final Rotation rotP = new Rotation(qP.getW(), qP.getX(), qP.getY(), qP.getZ(), true);

            Assert.assertEquals(rot.getAngle(), rotP.getAngle(), COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                COMPARISON_EPS);
        }
    }
*/
    @Test
    final void testInverse() {
        final Quaternion q = Quaternion.of(1.5, 4, 2, -2.5);

        final Quaternion inverseQ = q.inverse();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Quaternion product = Quaternion.multiply(inverseQ, q);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, product.getY(), EPS);
    }

    @Test
    final void testScalarMultiplyDouble_87_oe() {
        // expected outputs
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        // inputs
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testAddQuaternionQuaternion() {
        // expected outputs
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testSubtractQuaternionQuaternion() {
        // expected outputs
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        // inputs
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testNorm() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        // removed other assertion

        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        // removed other assertion
    }

    @Test
    final void testNormalize() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testNormalizeFail_zero() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_nan() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        // removed other assertion

    }

    @Test
    final void testNormalizeFail_positiveInfinity() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    final void testNormalizeFail_negativeInfinity() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    final void testObjectEquals() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testHashCode() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        // removed other assertion
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        // removed other assertion

        // "equals" and "hashCode" must be compatible: if two objects have
        // different hash codes, "equals" must return false.
        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        // removed other assertion
        // removed other assertion

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testQuaternionEquals2() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testIsUnit() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            // removed other assertion
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        // removed other assertion
    }

    @Test
    final void testIsPure() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        // removed other assertion

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        // removed other assertion

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        // removed other assertion

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        // removed other assertion

        final Quaternion q5 = Quaternion.of(0, v);
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        // removed other assertion

        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        // removed other assertion
        // removed other assertion
    }

    @Test
    void testNegate() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
    }

    @Test
    void testNegateNormalized() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).normalize();
        final Quaternion qNeg = q.negate();
        // removed other assertion
    }

    @Test
    void testNegatePositivePolarForm() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).positivePolarForm();
        final Quaternion qNeg = q.negate();
        // removed other assertion
    }

    /* TODO remove dependency on Rotation
    @Test
    final void testPolarForm() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q = Quaternion.of(2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5),
                                                2 * (r.nextDouble() - 0.5), 2 * (r.nextDouble() - 0.5));
            final Quaternion qP = q.positivePolarForm();

            Assert.assertTrue(qP.isUnit(COMPARISON_EPS));
            Assert.assertTrue(qP.getW() >= 0);

            final Rotation rot = new Rotation(q.getW(), q.getX(), q.getY(), q.getZ(), true);
            final Rotation rotP = new Rotation(qP.getW(), qP.getX(), qP.getY(), qP.getZ(), true);

            Assert.assertEquals(rot.getAngle(), rotP.getAngle(), COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),
                                COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),
                                COMPARISON_EPS);
        }
    }
*/
    @Test
    final void testInverse() {
        final Quaternion q = Quaternion.of(1.5, 4, 2, -2.5);

        final Quaternion inverseQ = q.inverse();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Quaternion product = Quaternion.multiply(inverseQ, q);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, product.getZ(), EPS);
    }

    @Test
    void testInverse_zeroNorm_1_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        Assertions.assertThrows(IllegalStateException.class, q::inverse );
    }

    @Test
    void testInverse_zeroNorm_2_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        Assertions.assertThrows(IllegalStateException.class, q::inverse );
    }

    @Test
    void testInverse_zeroNorm_3_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        Assertions.assertThrows(IllegalStateException.class, q::inverse );
    }

    @Test
    void testInverse_zeroNorm_4_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        Assertions.assertThrows(IllegalStateException.class, q::inverse );
    }

    @Test
    void testInverse_zeroNorm_5_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        Assertions.assertTrue(Quaternion.ONE.equals(result, EPS), result.toString());
    }

    @Test
    void testInverse_zeroNorm_6_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    void testInversePositivePolarForm() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        Assertions.assertTrue(Quaternion.ONE.equals(result, EPS), result.toString());
    }

    @Test
    void testInverse_zeroNorm_7_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    void testInversePositivePolarForm() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    final void testMultiply() {
        final Quaternion q1 = Quaternion.of(1, 2, 3, 4);
        final Quaternion q2 = Quaternion.of(4, 3, 2, 1);
        final Quaternion actual = q1.multiply(q2);
        final double w = 1 * 4 - 2 * 3 - 3 * 2 - 4 * 1;
        final double x = 1 * 3 + 2 * 4 + 3 * 1 - 4 * 2;
        final double y = 1 * 2 - 2 * 1 + 3 * 4 + 4 * 3;
        final double z = 1 * 1 + 2 * 2 - 3 * 3 + 4 * 4;
        final Quaternion expected = Quaternion.of(w, x, y, z);
        assertEquals(actual, expected, EPS);
    }

    @Test
    void testInverse_zeroNorm_8_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    void testInversePositivePolarForm() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    final void testMultiply() {
        final Quaternion q1 = Quaternion.of(1, 2, 3, 4);
        final Quaternion q2 = Quaternion.of(4, 3, 2, 1);
        final Quaternion actual = q1.multiply(q2);
        final double w = 1 * 4 - 2 * 3 - 3 * 2 - 4 * 1;
        final double x = 1 * 3 + 2 * 4 + 3 * 1 - 4 * 2;
        final double y = 1 * 2 - 2 * 1 + 3 * 4 + 4 * 3;
        final double z = 1 * 1 + 2 * 2 - 3 * 3 + 4 * 4;
        final Quaternion expected = Quaternion.of(w, x, y, z);
        // removed other assertion
    }

    @Test
    final void testParseFromToString() {
        final Quaternion q = Quaternion.of(1.1, 2.2, 3.3, 4.4);
        Quaternion parsed = Quaternion.parse(q.toString());
        assertEquals(parsed, q, EPS);
    }

    @Test
    void testInverse_zeroNorm_9_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    void testInversePositivePolarForm() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    final void testMultiply() {
        final Quaternion q1 = Quaternion.of(1, 2, 3, 4);
        final Quaternion q2 = Quaternion.of(4, 3, 2, 1);
        final Quaternion actual = q1.multiply(q2);
        final double w = 1 * 4 - 2 * 3 - 3 * 2 - 4 * 1;
        final double x = 1 * 3 + 2 * 4 + 3 * 1 - 4 * 2;
        final double y = 1 * 2 - 2 * 1 + 3 * 4 + 4 * 3;
        final double z = 1 * 1 + 2 * 2 - 3 * 3 + 4 * 4;
        final Quaternion expected = Quaternion.of(w, x, y, z);
        // removed other assertion
    }

    @Test
    final void testParseFromToString() {
        final Quaternion q = Quaternion.of(1.1, 2.2, 3.3, 4.4);
        Quaternion parsed = Quaternion.parse(q.toString());
        // removed other assertion
    }

    @Test
    final void testParseSpecials() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        Assertions.assertEquals(1e-5, parsed.getW(), EPS);
    }

    @Test
    void testInverse_zeroNorm_10_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    void testInversePositivePolarForm() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    final void testMultiply() {
        final Quaternion q1 = Quaternion.of(1, 2, 3, 4);
        final Quaternion q2 = Quaternion.of(4, 3, 2, 1);
        final Quaternion actual = q1.multiply(q2);
        final double w = 1 * 4 - 2 * 3 - 3 * 2 - 4 * 1;
        final double x = 1 * 3 + 2 * 4 + 3 * 1 - 4 * 2;
        final double y = 1 * 2 - 2 * 1 + 3 * 4 + 4 * 3;
        final double z = 1 * 1 + 2 * 2 - 3 * 3 + 4 * 4;
        final Quaternion expected = Quaternion.of(w, x, y, z);
        // removed other assertion
    }

    @Test
    final void testParseFromToString() {
        final Quaternion q = Quaternion.of(1.1, 2.2, 3.3, 4.4);
        Quaternion parsed = Quaternion.parse(q.toString());
        // removed other assertion
    }

    @Test
    final void testParseSpecials() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        // removed other assertion
        Assertions.assertTrue(Double.isInfinite(parsed.getX()));
    }

    @Test
    void testInverse_zeroNorm_11_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    void testInversePositivePolarForm() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    final void testMultiply() {
        final Quaternion q1 = Quaternion.of(1, 2, 3, 4);
        final Quaternion q2 = Quaternion.of(4, 3, 2, 1);
        final Quaternion actual = q1.multiply(q2);
        final double w = 1 * 4 - 2 * 3 - 3 * 2 - 4 * 1;
        final double x = 1 * 3 + 2 * 4 + 3 * 1 - 4 * 2;
        final double y = 1 * 2 - 2 * 1 + 3 * 4 + 4 * 3;
        final double z = 1 * 1 + 2 * 2 - 3 * 3 + 4 * 4;
        final Quaternion expected = Quaternion.of(w, x, y, z);
        // removed other assertion
    }

    @Test
    final void testParseFromToString() {
        final Quaternion q = Quaternion.of(1.1, 2.2, 3.3, 4.4);
        Quaternion parsed = Quaternion.parse(q.toString());
        // removed other assertion
    }

    @Test
    final void testParseSpecials() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(Double.isNaN(parsed.getY()));
    }

    @Test
    void testInverse_zeroNorm_12_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    void testInversePositivePolarForm() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    final void testMultiply() {
        final Quaternion q1 = Quaternion.of(1, 2, 3, 4);
        final Quaternion q2 = Quaternion.of(4, 3, 2, 1);
        final Quaternion actual = q1.multiply(q2);
        final double w = 1 * 4 - 2 * 3 - 3 * 2 - 4 * 1;
        final double x = 1 * 3 + 2 * 4 + 3 * 1 - 4 * 2;
        final double y = 1 * 2 - 2 * 1 + 3 * 4 + 4 * 3;
        final double z = 1 * 1 + 2 * 2 - 3 * 3 + 4 * 4;
        final Quaternion expected = Quaternion.of(w, x, y, z);
        // removed other assertion
    }

    @Test
    final void testParseFromToString() {
        final Quaternion q = Quaternion.of(1.1, 2.2, 3.3, 4.4);
        Quaternion parsed = Quaternion.parse(q.toString());
        // removed other assertion
    }

    @Test
    final void testParseSpecials() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-0xa.cp0, parsed.getZ(), EPS);
    }

    @Test
    void testInverse_zeroNorm_13_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    void testInversePositivePolarForm() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    final void testMultiply() {
        final Quaternion q1 = Quaternion.of(1, 2, 3, 4);
        final Quaternion q2 = Quaternion.of(4, 3, 2, 1);
        final Quaternion actual = q1.multiply(q2);
        final double w = 1 * 4 - 2 * 3 - 3 * 2 - 4 * 1;
        final double x = 1 * 3 + 2 * 4 + 3 * 1 - 4 * 2;
        final double y = 1 * 2 - 2 * 1 + 3 * 4 + 4 * 3;
        final double z = 1 * 1 + 2 * 2 - 3 * 3 + 4 * 4;
        final Quaternion expected = Quaternion.of(w, x, y, z);
        // removed other assertion
    }

    @Test
    final void testParseFromToString() {
        final Quaternion q = Quaternion.of(1.1, 2.2, 3.3, 4.4);
        Quaternion parsed = Quaternion.parse(q.toString());
        // removed other assertion
    }

    @Test
    final void testParseSpecials() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testParseMissingStart() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Quaternion.parse("1.0 2.0 3.0 4.0]") );
    }

    @Test
    void testInverse_zeroNorm_14_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    void testInversePositivePolarForm() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    final void testMultiply() {
        final Quaternion q1 = Quaternion.of(1, 2, 3, 4);
        final Quaternion q2 = Quaternion.of(4, 3, 2, 1);
        final Quaternion actual = q1.multiply(q2);
        final double w = 1 * 4 - 2 * 3 - 3 * 2 - 4 * 1;
        final double x = 1 * 3 + 2 * 4 + 3 * 1 - 4 * 2;
        final double y = 1 * 2 - 2 * 1 + 3 * 4 + 4 * 3;
        final double z = 1 * 1 + 2 * 2 - 3 * 3 + 4 * 4;
        final Quaternion expected = Quaternion.of(w, x, y, z);
        // removed other assertion
    }

    @Test
    final void testParseFromToString() {
        final Quaternion q = Quaternion.of(1.1, 2.2, 3.3, 4.4);
        Quaternion parsed = Quaternion.parse(q.toString());
        // removed other assertion
    }

    @Test
    final void testParseSpecials() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testParseMissingStart() {
        // removed other assertion
    }

    @Test
    final void testParseMissingEnd() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Quaternion.parse("[1.0 2.0 3.0 4.0") );
    }

    @Test
    void testInverse_zeroNorm_15_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    void testInversePositivePolarForm() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    final void testMultiply() {
        final Quaternion q1 = Quaternion.of(1, 2, 3, 4);
        final Quaternion q2 = Quaternion.of(4, 3, 2, 1);
        final Quaternion actual = q1.multiply(q2);
        final double w = 1 * 4 - 2 * 3 - 3 * 2 - 4 * 1;
        final double x = 1 * 3 + 2 * 4 + 3 * 1 - 4 * 2;
        final double y = 1 * 2 - 2 * 1 + 3 * 4 + 4 * 3;
        final double z = 1 * 1 + 2 * 2 - 3 * 3 + 4 * 4;
        final Quaternion expected = Quaternion.of(w, x, y, z);
        // removed other assertion
    }

    @Test
    final void testParseFromToString() {
        final Quaternion q = Quaternion.of(1.1, 2.2, 3.3, 4.4);
        Quaternion parsed = Quaternion.parse(q.toString());
        // removed other assertion
    }

    @Test
    final void testParseSpecials() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testParseMissingStart() {
        // removed other assertion
    }

    @Test
    final void testParseMissingEnd() {
        // removed other assertion
    }

    @Test
    final void testParseMissingPart() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Quaternion.parse("[1.0 2.0 3.0 ]") );
    }

    @Test
    void testInverse_zeroNorm_16_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    void testInversePositivePolarForm() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    final void testMultiply() {
        final Quaternion q1 = Quaternion.of(1, 2, 3, 4);
        final Quaternion q2 = Quaternion.of(4, 3, 2, 1);
        final Quaternion actual = q1.multiply(q2);
        final double w = 1 * 4 - 2 * 3 - 3 * 2 - 4 * 1;
        final double x = 1 * 3 + 2 * 4 + 3 * 1 - 4 * 2;
        final double y = 1 * 2 - 2 * 1 + 3 * 4 + 4 * 3;
        final double z = 1 * 1 + 2 * 2 - 3 * 3 + 4 * 4;
        final Quaternion expected = Quaternion.of(w, x, y, z);
        // removed other assertion
    }

    @Test
    final void testParseFromToString() {
        final Quaternion q = Quaternion.of(1.1, 2.2, 3.3, 4.4);
        Quaternion parsed = Quaternion.parse(q.toString());
        // removed other assertion
    }

    @Test
    final void testParseSpecials() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testParseMissingStart() {
        // removed other assertion
    }

    @Test
    final void testParseMissingEnd() {
        // removed other assertion
    }

    @Test
    final void testParseMissingPart() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidScalar() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Quaternion.parse("[1.x 2.0 3.0 4.0]") );
    }

    @Test
    void testInverse_zeroNorm_17_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    void testInversePositivePolarForm() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    final void testMultiply() {
        final Quaternion q1 = Quaternion.of(1, 2, 3, 4);
        final Quaternion q2 = Quaternion.of(4, 3, 2, 1);
        final Quaternion actual = q1.multiply(q2);
        final double w = 1 * 4 - 2 * 3 - 3 * 2 - 4 * 1;
        final double x = 1 * 3 + 2 * 4 + 3 * 1 - 4 * 2;
        final double y = 1 * 2 - 2 * 1 + 3 * 4 + 4 * 3;
        final double z = 1 * 1 + 2 * 2 - 3 * 3 + 4 * 4;
        final Quaternion expected = Quaternion.of(w, x, y, z);
        // removed other assertion
    }

    @Test
    final void testParseFromToString() {
        final Quaternion q = Quaternion.of(1.1, 2.2, 3.3, 4.4);
        Quaternion parsed = Quaternion.parse(q.toString());
        // removed other assertion
    }

    @Test
    final void testParseSpecials() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testParseMissingStart() {
        // removed other assertion
    }

    @Test
    final void testParseMissingEnd() {
        // removed other assertion
    }

    @Test
    final void testParseMissingPart() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidScalar() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidI() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Quaternion.parse("[1.0 2.0x 3.0 4.0]") );
    }

    @Test
    void testInverse_zeroNorm_18_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    void testInversePositivePolarForm() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    final void testMultiply() {
        final Quaternion q1 = Quaternion.of(1, 2, 3, 4);
        final Quaternion q2 = Quaternion.of(4, 3, 2, 1);
        final Quaternion actual = q1.multiply(q2);
        final double w = 1 * 4 - 2 * 3 - 3 * 2 - 4 * 1;
        final double x = 1 * 3 + 2 * 4 + 3 * 1 - 4 * 2;
        final double y = 1 * 2 - 2 * 1 + 3 * 4 + 4 * 3;
        final double z = 1 * 1 + 2 * 2 - 3 * 3 + 4 * 4;
        final Quaternion expected = Quaternion.of(w, x, y, z);
        // removed other assertion
    }

    @Test
    final void testParseFromToString() {
        final Quaternion q = Quaternion.of(1.1, 2.2, 3.3, 4.4);
        Quaternion parsed = Quaternion.parse(q.toString());
        // removed other assertion
    }

    @Test
    final void testParseSpecials() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testParseMissingStart() {
        // removed other assertion
    }

    @Test
    final void testParseMissingEnd() {
        // removed other assertion
    }

    @Test
    final void testParseMissingPart() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidScalar() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidI() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidJ() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Quaternion.parse("[1.0 2.0 3.0x 4.0]") );
    }

    @Test
    void testInverse_zeroNorm_19_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    void testInversePositivePolarForm() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    final void testMultiply() {
        final Quaternion q1 = Quaternion.of(1, 2, 3, 4);
        final Quaternion q2 = Quaternion.of(4, 3, 2, 1);
        final Quaternion actual = q1.multiply(q2);
        final double w = 1 * 4 - 2 * 3 - 3 * 2 - 4 * 1;
        final double x = 1 * 3 + 2 * 4 + 3 * 1 - 4 * 2;
        final double y = 1 * 2 - 2 * 1 + 3 * 4 + 4 * 3;
        final double z = 1 * 1 + 2 * 2 - 3 * 3 + 4 * 4;
        final Quaternion expected = Quaternion.of(w, x, y, z);
        // removed other assertion
    }

    @Test
    final void testParseFromToString() {
        final Quaternion q = Quaternion.of(1.1, 2.2, 3.3, 4.4);
        Quaternion parsed = Quaternion.parse(q.toString());
        // removed other assertion
    }

    @Test
    final void testParseSpecials() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testParseMissingStart() {
        // removed other assertion
    }

    @Test
    final void testParseMissingEnd() {
        // removed other assertion
    }

    @Test
    final void testParseMissingPart() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidScalar() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidI() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidJ() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidK() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Quaternion.parse("[1.0 2.0 3.0 4.0x]") );
    }

    @Test
    void testInverse_zeroNorm_20_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    void testInversePositivePolarForm() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    final void testMultiply() {
        final Quaternion q1 = Quaternion.of(1, 2, 3, 4);
        final Quaternion q2 = Quaternion.of(4, 3, 2, 1);
        final Quaternion actual = q1.multiply(q2);
        final double w = 1 * 4 - 2 * 3 - 3 * 2 - 4 * 1;
        final double x = 1 * 3 + 2 * 4 + 3 * 1 - 4 * 2;
        final double y = 1 * 2 - 2 * 1 + 3 * 4 + 4 * 3;
        final double z = 1 * 1 + 2 * 2 - 3 * 3 + 4 * 4;
        final Quaternion expected = Quaternion.of(w, x, y, z);
        // removed other assertion
    }

    @Test
    final void testParseFromToString() {
        final Quaternion q = Quaternion.of(1.1, 2.2, 3.3, 4.4);
        Quaternion parsed = Quaternion.parse(q.toString());
        // removed other assertion
    }

    @Test
    final void testParseSpecials() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testParseMissingStart() {
        // removed other assertion
    }

    @Test
    final void testParseMissingEnd() {
        // removed other assertion
    }

    @Test
    final void testParseMissingPart() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidScalar() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidI() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidJ() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidK() {
        // removed other assertion
    }

    @Test
    final void testToString() {
        final Quaternion q = Quaternion.of(1, 2, 3, 4);
        Assertions.assertEquals("[1.0 2.0 3.0 4.0]", q.toString());
    }

    @Test
    void testInverse_zeroNorm_21_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_nanNorm() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_positiveInfinityNorm() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        // removed other assertion
    }

    @Test
    void testInverse_negativeInfinityNorm() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        // removed other assertion
    }

    @Test
    void testInverseNormalized() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    void testInversePositivePolarForm() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        // removed other assertion
    }

    @Test
    final void testMultiply() {
        final Quaternion q1 = Quaternion.of(1, 2, 3, 4);
        final Quaternion q2 = Quaternion.of(4, 3, 2, 1);
        final Quaternion actual = q1.multiply(q2);
        final double w = 1 * 4 - 2 * 3 - 3 * 2 - 4 * 1;
        final double x = 1 * 3 + 2 * 4 + 3 * 1 - 4 * 2;
        final double y = 1 * 2 - 2 * 1 + 3 * 4 + 4 * 3;
        final double z = 1 * 1 + 2 * 2 - 3 * 3 + 4 * 4;
        final Quaternion expected = Quaternion.of(w, x, y, z);
        // removed other assertion
    }

    @Test
    final void testParseFromToString() {
        final Quaternion q = Quaternion.of(1.1, 2.2, 3.3, 4.4);
        Quaternion parsed = Quaternion.parse(q.toString());
        // removed other assertion
    }

    @Test
    final void testParseSpecials() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
    }

    @Test
    final void testParseMissingStart() {
        // removed other assertion
    }

    @Test
    final void testParseMissingEnd() {
        // removed other assertion
    }

    @Test
    final void testParseMissingPart() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidScalar() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidI() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidJ() {
        // removed other assertion
    }

    @Test
    final void testParseInvalidK() {
        // removed other assertion
    }

    @Test
    final void testToString() {
        final Quaternion q = Quaternion.of(1, 2, 3, 4);
        // removed other assertion
    }

    /**
     * Assert that two quaternions are equal within tolerance
     * @param actual
     * @param expected
     * @param tolerance
     */
    private void assertEquals(Quaternion actual, Quaternion expected, double tolerance) { Assertions.assertTrue(actual.equals(expected, tolerance), "expecting " + expected + " but got " + actual);
    }

}
