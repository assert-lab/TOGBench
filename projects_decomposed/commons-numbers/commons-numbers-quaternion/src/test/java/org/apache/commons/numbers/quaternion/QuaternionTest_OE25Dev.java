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

import static org.junit.jupiter.api.Assertions.fail;

class QuaternionTest_OE25Dev {
    /** Epsilon for double comparison. */
    private static final double EPS = Math.ulp(1d);
    /** Epsilon for double comparison. */
    private static final double COMPARISON_EPS = 1e-14;

    /* TODO remove dependency on Vector3D
    @Test
    final void testProductQuaternionQuaternion() {

        // Case : analytic test case

        final Quaternion qA = Quaternion.of(1, 0.5, -3, 4);
        final Quaternion qB = Quaternion.of(6, 2, 1, -9);
        final Quaternion qResult = Quaternion.multiply(qA, qB);

        Assert.assertEquals(44, qResult.getW(), EPS);
        Assert.assertEquals(28, qResult.getX(), EPS);
        Assert.assertEquals(-4.5, qResult.getY(), EPS);
        Assert.assertEquals(21.5, qResult.getZ(), EPS);

        // comparison with the result given by the formula :
        // qResult = (scalarA * scalarB - vectorA . vectorB) + (scalarA * vectorB + scalarB * vectorA + vectorA ^
        // vectorB)

        final Vector3D vectorA = new Vector3D(qA.getVectorPart());
        final Vector3D vectorB = new Vector3D(qB.getVectorPart());
        final Vector3D vectorResult = new Vector3D(qResult.getVectorPart());

        final double scalarPartRef = qA.getScalarPart() * qB.getScalarPart() - Vector3D.dotProduct(vectorA, vectorB);

        Assert.assertEquals(scalarPartRef, qResult.getScalarPart(), EPS);

        final Vector3D vectorPartRef = ((vectorA.scalarMultiply(qB.getScalarPart())).add(vectorB.scalarMultiply(qA
                .getScalarPart()))).add(Vector3D.crossProduct(vectorA, vectorB));
        final double norm = (vectorResult.subtract(vectorPartRef)).norm();

        Assert.assertEquals(0, norm, EPS);

        // Conjugate of the product of two quaternions and product of their conjugates :
        // Conj(qA * qB) = Conj(qB) * Conj(qA)

        final Quaternion conjugateOfProduct = qB.getConjugate().multiply(qA.getConjugate());
        final Quaternion productOfConjugate = (qA.multiply(qB)).getConjugate();

        Assert.assertEquals(conjugateOfProduct.getW(), productOfConjugate.getW(), EPS);
        Assert.assertEquals(conjugateOfProduct.getX(), productOfConjugate.getX(), EPS);
        Assert.assertEquals(conjugateOfProduct.getY(), productOfConjugate.getY(), EPS);
        Assert.assertEquals(conjugateOfProduct.getZ(), productOfConjugate.getZ(), EPS);
    }
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
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),rot.getAxis(RotationConvention.VECTOR_OPERATOR).getX(),COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),rot.getAxis(RotationConvention.VECTOR_OPERATOR).getY(),COMPARISON_EPS);
            Assert.assertEquals(rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),rot.getAxis(RotationConvention.VECTOR_OPERATOR).getZ(),COMPARISON_EPS);
        }
    }
*/

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
        Assertions.assertSame(Quaternion.ONE, Quaternion.ONE.normalize());
    }

    @Test
    void testUnitQuaternions_3_oe() {

        Assertions.assertEquals(1, Quaternion.I.norm());
    }

    @Test
    void testUnitQuaternions_4_oe() {

        Assertions.assertSame(Quaternion.I, Quaternion.I.normalize());
    }

    @Test
    void testUnitQuaternions_5_oe() {


        Assertions.assertEquals(1, Quaternion.J.norm());
    }

    @Test
    void testUnitQuaternions_6_oe() {


        Assertions.assertSame(Quaternion.J, Quaternion.J.normalize());
    }

    @Test
    void testUnitQuaternions_7_oe() {



        Assertions.assertEquals(1, Quaternion.K.norm());
    }

    @Test
    void testUnitQuaternions_8_oe() {



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

        Assertions.assertEquals(q1, q.getX());
    }

    @Test
    final void testAccessors1_3_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        Assertions.assertEquals(q2, q.getY());
    }

    @Test
    final void testAccessors1_4_oe() {
        final double q0 = 2;
        final double q1 = 5.4;
        final double q2 = 17;
        final double q3 = 0.0005;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

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

        Assertions.assertEquals(q3, vP[2]);
    }

    @Test
    void testWrongDimension_1_oe() {
        try {
    Quaternion.of(new double[] {1, 2});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
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

        Assertions.assertEquals(-q3, qConjugate.getZ());
    }

    @Test
    final void testDotProductQuaternionQuaternion_1_oe() {
        final double expected = -6.;
        final Quaternion q1 = Quaternion.of(1, 2, 2, 1);
        final Quaternion q2 = Quaternion.of(3, -2, -1, -3);

        final double actual1 = Quaternion.dot(q1, q2);
        final double actual2 = q1.dot(q2);

        Assertions.assertEquals(expected, actual1, EPS);
    }

    @Test
    final void testDotProductQuaternionQuaternion_2_oe() {
        final double expected = -6.;
        final Quaternion q1 = Quaternion.of(1, 2, 2, 1);
        final Quaternion q2 = Quaternion.of(3, -2, -1, -3);

        final double actual1 = Quaternion.dot(q1, q2);
        final double actual2 = q1.dot(q2);

        Assertions.assertEquals(expected, actual2, EPS);
    }

    @Test
    final void testScalarMultiplyDouble_1_oe() {
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        Assertions.assertEquals(w, q.getW(), COMPARISON_EPS);
    }

    @Test
    final void testScalarMultiplyDouble_2_oe() {
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        Assertions.assertEquals(x, q.getX(), COMPARISON_EPS);
    }

    @Test
    final void testScalarMultiplyDouble_3_oe() {
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        Assertions.assertEquals(y, q.getY(), COMPARISON_EPS);
    }

    @Test
    final void testScalarMultiplyDouble_4_oe() {
        final double w = 1.6;
        final double x = -4.8;
        final double y = 11.20;
        final double z = 2.56;
        final Quaternion q1 = Quaternion.of(0.5, -1.5, 3.5, 0.8);
        final double a = 3.2;

        final Quaternion q = q1.multiply(a);

        Assertions.assertEquals(z, q.getZ(), COMPARISON_EPS);
    }

    @Test
    final void testAddQuaternionQuaternion_1_oe() {
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        Assertions.assertEquals(w, qa.getW(), EPS);
    }

    @Test
    final void testAddQuaternionQuaternion_2_oe() {
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        Assertions.assertEquals(x, qa.getX(), EPS);
    }

    @Test
    final void testAddQuaternionQuaternion_3_oe() {
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        Assertions.assertEquals(y, qa.getY(), EPS);
    }

    @Test
    final void testAddQuaternionQuaternion_4_oe() {
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);

        Assertions.assertEquals(z, qa.getZ(), EPS);
    }

    @Test
    final void testAddQuaternionQuaternion_5_oe() {
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);


        Assertions.assertEquals(w, qb.getW(), EPS);
    }

    @Test
    final void testAddQuaternionQuaternion_6_oe() {
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);


        Assertions.assertEquals(x, qb.getX(), EPS);
    }

    @Test
    final void testAddQuaternionQuaternion_7_oe() {
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);


        Assertions.assertEquals(y, qb.getY(), EPS);
    }

    @Test
    final void testAddQuaternionQuaternion_8_oe() {
        final double w = 4;
        final double x = -1;
        final double y = 2;
        final double z = -4;
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.add(q1, q2);
        final Quaternion qb = q1.add(q2);


        Assertions.assertEquals(z, qb.getZ(), EPS);
    }

    @Test
    final void testSubtractQuaternionQuaternion_1_oe() {
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        Assertions.assertEquals(w, qa.getW(), EPS);
    }

    @Test
    final void testSubtractQuaternionQuaternion_2_oe() {
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        Assertions.assertEquals(x, qa.getX(), EPS);
    }

    @Test
    final void testSubtractQuaternionQuaternion_3_oe() {
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        Assertions.assertEquals(y, qa.getY(), EPS);
    }

    @Test
    final void testSubtractQuaternionQuaternion_4_oe() {
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);

        Assertions.assertEquals(z, qa.getZ(), EPS);
    }

    @Test
    final void testSubtractQuaternionQuaternion_5_oe() {
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);


        Assertions.assertEquals(w, qb.getW(), EPS);
    }

    @Test
    final void testSubtractQuaternionQuaternion_6_oe() {
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);


        Assertions.assertEquals(x, qb.getX(), EPS);
    }

    @Test
    final void testSubtractQuaternionQuaternion_7_oe() {
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);


        Assertions.assertEquals(y, qb.getY(), EPS);
    }

    @Test
    final void testSubtractQuaternionQuaternion_8_oe() {
        final double w = -2.;
        final double x = 5.;
        final double y = -6.;
        final double z = 2.;
        final Quaternion q1 = Quaternion.of(1., 2., -2., -1.);
        final Quaternion q2 = Quaternion.of(3., -3., 4., -3.);

        final Quaternion qa = Quaternion.subtract(q1, q2);
        final Quaternion qb = q1.subtract(q2);


        Assertions.assertEquals(z, qb.getZ(), EPS);
    }

    @Test
    final void testNorm_1_oe() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();

        Assertions.assertEquals(Math.sqrt(30), norm);
    }

    @Test
    final void testNorm_2_oe() {

        final double q0 = 2;
        final double q1 = 1;
        final double q2 = -4;
        final double q3 = 3;
        final Quaternion q = Quaternion.of(q0, q1, q2, q3);

        final double norm = q.norm();


        final double normSquareRef = Quaternion.multiply(q, q.conjugate()).getScalarPart();
        Assertions.assertEquals(Math.sqrt(normSquareRef), norm);
    }

    @Test
    final void testNormalize_1_oe() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        Assertions.assertEquals(2.0 / 5.0, versor.getW());
    }

    @Test
    final void testNormalize_2_oe() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        Assertions.assertEquals(1.0 / 5.0, versor.getX());
    }

    @Test
    final void testNormalize_3_oe() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        Assertions.assertEquals(-4.0 / 5.0, versor.getY());
    }

    @Test
    final void testNormalize_4_oe() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();

        Assertions.assertEquals(-2.0 / 5.0, versor.getZ());
    }

    @Test
    final void testNormalize_5_oe() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();


        Assertions.assertEquals(1, versor.norm());
    }

    @Test
    final void testNormalize_6_oe() {

        final Quaternion q = Quaternion.of(2, 1, -4, -2);

        final Quaternion versor = q.normalize();



        Assertions.assertSame(versor.normalize(), versor);
    }

    @Test
    final void testNormalizeFail_zero_1_oe() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        try {
    q.normalize();
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    final void testNormalizeFail_nan_1_oe() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        try {
    q.normalize();
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    final void testNormalizeFail_positiveInfinity_1_oe() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        try {
    q.normalize();
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    final void testNormalizeFail_negativeInfinity_1_oe() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        try {
    q.normalize();
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    final void testObjectEquals_1_oe() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);
        Assertions.assertEquals(q1, q1);
    }

    @Test
    final void testObjectEquals_2_oe() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);

        final Quaternion q2 = Quaternion.of(one, one, one, one);
        Assertions.assertEquals(q2, q1);
    }

    @Test
    final void testObjectEquals_3_oe() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);

        final Quaternion q2 = Quaternion.of(one, one, one, one);

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);
        Assertions.assertNotEquals(q3, q1);
    }

    @Test
    final void testObjectEquals_4_oe() {
        final double one = 1;
        final Quaternion q1 = Quaternion.of(one, one, one, one);

        final Quaternion q2 = Quaternion.of(one, one, one, one);

        final Quaternion q3 = Quaternion.of(one, Math.nextUp(one), one, one);

        Assertions.assertNotEquals(q3, "bar");
    }

    @Test
    void testHashCode_1_oe() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());
    }

    @Test
    void testHashCode_2_oe() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());
    }

    @Test
    void testHashCode_3_oe() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);

        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());
    }

    @Test
    void testHashCode_4_oe() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);

        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);
        Assertions.assertNotEquals(x, y, msg);
    }

    @Test
    void testHashCode_5_oe() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);

        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());
    }

    @Test
    void testHashCode_6_oe() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);

        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);
        Assertions.assertNotEquals(x, y, msg);
    }

    @Test
    void testHashCode_7_oe() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);

        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());
    }

    @Test
    void testHashCode_8_oe() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);

        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);
        Assertions.assertNotEquals(x, y, msg);
    }

    @Test
    void testHashCode_9_oe() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);

        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        Assertions.assertNotEquals(x.hashCode(), y.hashCode());
    }

    @Test
    void testHashCode_10_oe() {
        Quaternion x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        Quaternion y = Quaternion.of(0.0, 0.0 + Double.MIN_VALUE, 0.0, 0.0);
        y = Quaternion.of(0.0 + Double.MIN_VALUE, 0.0, 0.0, 0.0);

        final String msg = "'equals' not compatible with 'hashCode'";

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(-0.0, 0.0, 0.0, 0.0);

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, -0.0, 0.0, 0.0);

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, -0.0, 0.0);

        x = Quaternion.of(0.0, 0.0, 0.0, 0.0);
        y = Quaternion.of(0.0, 0.0, 0.0, -0.0);
        Assertions.assertNotEquals(x, y, msg);
    }

    @Test
    final void testQuaternionEquals_1_oe() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        Assertions.assertFalse(q1.equals(q2, 0.9 * inc));
    }

    @Test
    final void testQuaternionEquals_2_oe() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        Assertions.assertFalse(q1.equals(q3, 0.9 * inc));
    }

    @Test
    final void testQuaternionEquals_3_oe() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        Assertions.assertFalse(q1.equals(q4, 0.9 * inc));
    }

    @Test
    final void testQuaternionEquals_4_oe() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);

        Assertions.assertFalse(q1.equals(q5, 0.9 * inc));
    }

    @Test
    final void testQuaternionEquals_5_oe() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);


        Assertions.assertTrue(q1.equals(q2, 1.1 * inc));
    }

    @Test
    final void testQuaternionEquals_6_oe() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);


        Assertions.assertTrue(q1.equals(q3, 1.1 * inc));
    }

    @Test
    final void testQuaternionEquals_7_oe() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);


        Assertions.assertTrue(q1.equals(q4, 1.1 * inc));
    }

    @Test
    final void testQuaternionEquals_8_oe() {
        final double inc = 1e-5;
        final Quaternion q1 = Quaternion.of(2, 1, -4, -2);
        final Quaternion q2 = Quaternion.of(q1.getW() + inc, q1.getX(), q1.getY(), q1.getZ());
        final Quaternion q3 = Quaternion.of(q1.getW(), q1.getX() + inc, q1.getY(), q1.getZ());
        final Quaternion q4 = Quaternion.of(q1.getW(), q1.getX(), q1.getY() + inc, q1.getZ());
        final Quaternion q5 = Quaternion.of(q1.getW(), q1.getX(), q1.getY(), q1.getZ() + inc);


        Assertions.assertTrue(q1.equals(q5, 1.1 * inc));
    }

    @Test
    final void testQuaternionEquals2_1_oe() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        Assertions.assertTrue(q1.equals(q2, 10 * gap));
    }

    @Test
    final void testQuaternionEquals2_2_oe() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        Assertions.assertFalse(q1.equals(q2, gap));
    }

    @Test
    final void testQuaternionEquals2_3_oe() {
        final Quaternion q1 = Quaternion.of(1, 4, 2, 3);
        final double gap = 1e-5;
        final Quaternion q2 = Quaternion.of(1 + gap, 4 + gap, 2 + gap, 3 + gap);

        Assertions.assertFalse(q1.equals(q2, gap / 10));
    }

    @Test
    final void testIsUnit_1_oe() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
            Assertions.assertTrue(q2.isUnit(COMPARISON_EPS));
    }
    }

    @Test
    final void testIsUnit_2_oe() {
        final Random r = new Random(48);
        final int numberOfTrials = 1000;
        for (int i = 0; i < numberOfTrials; i++) {
            final Quaternion q1 = Quaternion.of(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
            final Quaternion q2 = q1.normalize();
        }

        final Quaternion q = Quaternion.of(1, 1, 1, 1);
        Assertions.assertFalse(q.isUnit(COMPARISON_EPS));
    }

    @Test
    final void testIsPure_1_oe() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);
        Assertions.assertTrue(q1.isPure(EPS));
    }

    @Test
    final void testIsPure_2_oe() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);
        Assertions.assertTrue(q2.isPure(EPS));
    }

    @Test
    final void testIsPure_3_oe() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);
        Assertions.assertFalse(q3.isPure(EPS));
    }

    @Test
    final void testIsPure_4_oe() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);
        Assertions.assertTrue(q4.isPure(0));
    }

    @Test
    final void testIsPure_5_oe() {
        final Quaternion q1 = Quaternion.of(0, 5, 4, 8);

        final Quaternion q2 = Quaternion.of(0 - EPS, 5, 4, 8);

        final Quaternion q3 = Quaternion.of(0 - 1.1 * EPS, 5, 4, 8);

        final Random r = new Random(48);
        final double[] v = {r.nextDouble(), r.nextDouble(), r.nextDouble()};
        final Quaternion q4 = Quaternion.of(v);

        final Quaternion q5 = Quaternion.of(0, v);
        Assertions.assertTrue(q5.isPure(0));
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive_1_oe() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);
        assertEquals(q, expected, EPS);
    }

    @Test
    final void testPositivePolarFormWhenScalarPositive_2_oe() {
        Quaternion q = Quaternion.of(3, -3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, -0.5, 0.5);

        Assertions.assertSame(q.positivePolarForm(), q);
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative_1_oe() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);
        assertEquals(q, expected, EPS);
    }

    @Test
    final void testPositivePolarFormWhenScalarNegative_2_oe() {
        Quaternion q = Quaternion.of(-3, 3, -3, 3).positivePolarForm();
        Quaternion expected = Quaternion.of(0.5, -0.5, 0.5, -0.5);

        Assertions.assertSame(q.positivePolarForm(), q);
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized_1_oe() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        Assertions.assertTrue(q.getW() >= 0);
    }

    @Test
    final void testPositivePolarFormWhenScalarPositiveAndNormalized_2_oe() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().positivePolarForm();

        Assertions.assertSame(q.positivePolarForm(), q);
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized_1_oe() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        Assertions.assertTrue(q.getW() >= 0);
    }

    @Test
    final void testPositivePolarFormWhenScalarNegativeAndNormalized_2_oe() {
        Quaternion q = Quaternion.of(123, 45, 67, 89).normalize().negate().positivePolarForm();

        Assertions.assertSame(q.positivePolarForm(), q);
    }

    @Test
    void testNegate_1_oe() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        Assertions.assertEquals(-a, qNeg.getW());
    }

    @Test
    void testNegate_2_oe() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        Assertions.assertEquals(-b, qNeg.getX());
    }

    @Test
    void testNegate_3_oe() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        Assertions.assertEquals(-c, qNeg.getY());
    }

    @Test
    void testNegate_4_oe() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();
        Assertions.assertEquals(-d, qNeg.getZ());
    }

    @Test
    void testNegate_5_oe() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d);
        final Quaternion qNeg = q.negate();

        Assertions.assertTrue(q.equals(qNeg.negate(), 0d));
    }

    @Test
    void testNegateNormalized_1_oe() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).normalize();
        final Quaternion qNeg = q.negate();
        Assertions.assertTrue(q.equals(qNeg.negate(), 0d));
    }

    @Test
    void testNegatePositivePolarForm_1_oe() {
        final double a = -1;
        final double b = 2;
        final double c = -3;
        final double d = 4;
        final Quaternion q = Quaternion.of(a, b, c, d).positivePolarForm();
        final Quaternion qNeg = q.negate();
        Assertions.assertTrue(q.equals(qNeg.negate(), 0d));
    }

    @Test
    final void testInverse_1_oe() {
        final Quaternion q = Quaternion.of(1.5, 4, 2, -2.5);

        final Quaternion inverseQ = q.inverse();
        Assertions.assertEquals(1.5 / 28.5, inverseQ.getW());
    }

    @Test
    final void testInverse_2_oe() {
        final Quaternion q = Quaternion.of(1.5, 4, 2, -2.5);

        final Quaternion inverseQ = q.inverse();
        Assertions.assertEquals(-4.0 / 28.5, inverseQ.getX());
    }

    @Test
    final void testInverse_3_oe() {
        final Quaternion q = Quaternion.of(1.5, 4, 2, -2.5);

        final Quaternion inverseQ = q.inverse();
        Assertions.assertEquals(-2.0 / 28.5, inverseQ.getY());
    }

    @Test
    final void testInverse_4_oe() {
        final Quaternion q = Quaternion.of(1.5, 4, 2, -2.5);

        final Quaternion inverseQ = q.inverse();
        Assertions.assertEquals(2.5 / 28.5, inverseQ.getZ());
    }

    @Test
    final void testInverse_5_oe() {
        final Quaternion q = Quaternion.of(1.5, 4, 2, -2.5);

        final Quaternion inverseQ = q.inverse();

        final Quaternion product = Quaternion.multiply(inverseQ, q);
        Assertions.assertEquals(1, product.getW(), EPS);
    }

    @Test
    final void testInverse_6_oe() {
        final Quaternion q = Quaternion.of(1.5, 4, 2, -2.5);

        final Quaternion inverseQ = q.inverse();

        final Quaternion product = Quaternion.multiply(inverseQ, q);
        Assertions.assertEquals(0, product.getX(), EPS);
    }

    @Test
    final void testInverse_7_oe() {
        final Quaternion q = Quaternion.of(1.5, 4, 2, -2.5);

        final Quaternion inverseQ = q.inverse();

        final Quaternion product = Quaternion.multiply(inverseQ, q);
        Assertions.assertEquals(0, product.getY(), EPS);
    }

    @Test
    final void testInverse_8_oe() {
        final Quaternion q = Quaternion.of(1.5, 4, 2, -2.5);

        final Quaternion inverseQ = q.inverse();

        final Quaternion product = Quaternion.multiply(inverseQ, q);
        Assertions.assertEquals(0, product.getZ(), EPS);
    }

    @Test
    void testInverse_zeroNorm_1_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        try {
    q.inverse();
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testInverse_nanNorm_1_oe() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        try {
    q.inverse();
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testInverse_positiveInfinityNorm_1_oe() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        try {
    q.inverse();
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testInverse_negativeInfinityNorm_1_oe() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        try {
    q.inverse();
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testInverseNormalized_1_oe() {
        final Quaternion invQ = Quaternion.of(-1.2, 3.4, -5.6, -7.8).normalize().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        Assertions.assertTrue(Quaternion.ONE.equals(result, EPS), result.toString());
    }

    @Test
    void testInversePositivePolarForm_1_oe() {
        final Quaternion invQ = Quaternion.of(1.2, -3.4, 5.6, -7.8).positivePolarForm().inverse();
        final Quaternion q = invQ.inverse();
        final Quaternion result = q.multiply(invQ);
        Assertions.assertTrue(Quaternion.ONE.equals(result, EPS), result.toString());
    }

    @Test
    final void testMultiply_1_oe() {
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
    final void testParseFromToString_1_oe() {
        final Quaternion q = Quaternion.of(1.1, 2.2, 3.3, 4.4);
        Quaternion parsed = Quaternion.parse(q.toString());
        assertEquals(parsed, q, EPS);
    }

    @Test
    final void testParseSpecials_1_oe() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        Assertions.assertEquals(1e-5, parsed.getW(), EPS);
    }

    @Test
    final void testParseSpecials_2_oe() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        Assertions.assertTrue(Double.isInfinite(parsed.getX()));
    }

    @Test
    final void testParseSpecials_3_oe() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        Assertions.assertTrue(Double.isNaN(parsed.getY()));
    }

    @Test
    final void testParseSpecials_4_oe() {
        Quaternion parsed = Quaternion.parse("[1e-5 Infinity NaN -0xa.cp0]");
        Assertions.assertEquals(-0xa.cp0, parsed.getZ(), EPS);
    }

    @Test
    final void testParseMissingStart_1_oe() {
        try {
    Quaternion.parse("1.0 2.0 3.0 4.0]");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    final void testParseMissingEnd_1_oe() {
        try {
    Quaternion.parse("[1.0 2.0 3.0 4.0");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    final void testParseMissingPart_1_oe() {
        try {
    Quaternion.parse("[1.0 2.0 3.0 ]");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    final void testParseInvalidScalar_1_oe() {
        try {
    Quaternion.parse("[1.x 2.0 3.0 4.0]");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    final void testParseInvalidI_1_oe() {
        try {
    Quaternion.parse("[1.0 2.0x 3.0 4.0]");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    final void testParseInvalidJ_1_oe() {
        try {
    Quaternion.parse("[1.0 2.0 3.0x 4.0]");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    final void testParseInvalidK_1_oe() {
        try {
    Quaternion.parse("[1.0 2.0 3.0 4.0x]");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    final void testToString_1_oe() {
        final Quaternion q = Quaternion.of(1, 2, 3, 4);
        Assertions.assertEquals("[1.0 2.0 3.0 4.0]", q.toString());
    }

}
