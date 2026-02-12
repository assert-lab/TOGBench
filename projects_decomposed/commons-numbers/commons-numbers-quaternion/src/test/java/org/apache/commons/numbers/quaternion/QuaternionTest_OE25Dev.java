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
    void testWrongDimension_1_oe() {
        try {
    Quaternion.of(new double[] {1, 2});
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    final void testNormalizeFail_zero_1_oe() {
        final Quaternion q = Quaternion.of(0, 0, 0, 0);
        Assertions.assertThrows(IllegalStateException.class, q::normalize );
    }

    @Test
    final void testNormalizeFail_nan_1_oe() {
        final Quaternion q = Quaternion.of(0, 0, 0, Double.NaN);
        Assertions.assertThrows(IllegalStateException.class, q::normalize );
    }

    @Test
    final void testNormalizeFail_positiveInfinity_1_oe() {
        final Quaternion q = Quaternion.of(0, 0, Double.POSITIVE_INFINITY, 0);
        Assertions.assertThrows(IllegalStateException.class, q::normalize );
    }

    @Test
    final void testNormalizeFail_negativeInfinity_1_oe() {
        final Quaternion q = Quaternion.of(0, Double.NEGATIVE_INFINITY, 0, 0);
        Assertions.assertThrows(IllegalStateException.class, q::normalize );
    }

    @Test
    void testInverse_zeroNorm_1_oe() {
        Quaternion q = Quaternion.of(0, 0, 0, 0);
        Assertions.assertThrows(IllegalStateException.class, q::inverse );
    }

    @Test
    void testInverse_nanNorm_1_oe() {
        Quaternion q = Quaternion.of(Double.NaN, 0, 0, 0);
        Assertions.assertThrows(IllegalStateException.class, q::inverse );
    }

    @Test
    void testInverse_positiveInfinityNorm_1_oe() {
        Quaternion q = Quaternion.of(0, Double.POSITIVE_INFINITY, 0, 0);
        Assertions.assertThrows(IllegalStateException.class, q::inverse );
    }

    @Test
    void testInverse_negativeInfinityNorm_1_oe() {
        Quaternion q = Quaternion.of(0, 0, Double.NEGATIVE_INFINITY, 0);
        Assertions.assertThrows(IllegalStateException.class, q::inverse );
    }

    @Test
    final void testParseMissingStart_1_oe() {
        try {
    Quaternion.parse("1.0 2.0 3.0 4.0]");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    final void testParseMissingEnd_1_oe() {
        try {
    Quaternion.parse("[1.0 2.0 3.0 4.0");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    final void testParseMissingPart_1_oe() {
        try {
    Quaternion.parse("[1.0 2.0 3.0 ]");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    final void testParseInvalidScalar_1_oe() {
        try {
    Quaternion.parse("[1.x 2.0 3.0 4.0]");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    final void testParseInvalidI_1_oe() {
        try {
    Quaternion.parse("[1.0 2.0x 3.0 4.0]");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    final void testParseInvalidJ_1_oe() {
        try {
    Quaternion.parse("[1.0 2.0 3.0x 4.0]");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    final void testParseInvalidK_1_oe() {
        try {
    Quaternion.parse("[1.0 2.0 3.0 4.0x]");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
