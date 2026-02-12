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

import org.apache.commons.numbers.core.Precision;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SlerpTest_OE25Dev {

    private static final double EPS = 1e-7;

    private static final double SQRT_2 = Math.sqrt(2.0);
    private static final double INV_SQRT_2 = 1.0 / SQRT_2;

    @Test
    void testVectorTransform_multipleCombinations() {
        // arrange
        Quaternion[] quaternions = {
                // +x axis
                Quaternion.of(1, 0, 0, 0), // 0 pi
                Quaternion.of(INV_SQRT_2, INV_SQRT_2, 0, 0), // pi/2
                Quaternion.of(0, 1, 0, 0), // pi

                // -x axis
                Quaternion.of(1, 0, 0, 0), // 0 pi
                Quaternion.of(INV_SQRT_2, -INV_SQRT_2, 0, 0), // pi/2
                Quaternion.of(0, -1, 0, 0), // pi

                // +y axis
                Quaternion.of(1, 0, 0, 0), // 0 pi
                Quaternion.of(INV_SQRT_2, 0, INV_SQRT_2, 0), // pi/2
                Quaternion.of(0, 0, 1, 0), // pi

                // -y axis
                Quaternion.of(1, 0, 0, 0), // 0 pi
                Quaternion.of(INV_SQRT_2, 0, -INV_SQRT_2, 0), // pi/2
                Quaternion.of(0, 0, -1, 0), // pi

                // +z axis
                Quaternion.of(1, 0, 0, 0), // 0 pi
                Quaternion.of(INV_SQRT_2, 0, 0, INV_SQRT_2), // pi/2
                Quaternion.of(0, 0, 0, 1), // pi

                // -z axis
                Quaternion.of(1, 0, 0, 0), // 0 pi
                Quaternion.of(INV_SQRT_2, 0, 0, -INV_SQRT_2), // pi/2
                Quaternion.of(0, 0, 0, -1) // pi
        };

        // act/assert
        // test each quaternion against all of the others (including itself)
        for (int i = 0; i < quaternions.length; ++i) {
            for (int j = 0; j < quaternions.length; ++j) {
                checkSlerpCombination(quaternions[i], quaternions[j]);
            }
        }
    }

    private void checkSlerpCombination(Quaternion start, Quaternion end) {
        Slerp slerp = new Slerp(start, end);

        double[] vec = {1, 2, 3};
        double vecNorm = norm(vec);

        double[] startVec = transformVector(start, vec);
        double[] endVec = transformVector(end, vec);

        // check start and end values
        Assertions.assertArrayEquals(startVec, transformVector(slerp.apply(0), vec), EPS);
        Assertions.assertArrayEquals(endVec, transformVector(slerp.apply(1), vec), EPS);

        // check intermediate values
        double prevAngle = -1;
        final int numSteps = 100;
        final double delta = 1.0 / numSteps;
        for (int step = 0; step <= numSteps; ++step) {
            final double t = step * delta;
            Quaternion result = slerp.apply(t);

            double[] slerpVec = transformVector(result, vec);

            // the transformation should not effect the vector magnitude
            Assertions.assertEquals(vecNorm, norm(slerpVec), EPS);

            // make sure that we're steadily progressing to the end angle
            double angle = angle(slerpVec, startVec);
            Assertions.assertTrue(Precision.compareTo(angle,prevAngle,EPS)>= 0,"Expected slerp angle to continuously increase;previous angle was " + prevAngle + " and new angle is " + angle);
        }
    }

    /**
     * Create a quaterion representing a rotation around the +z axis.
     * @param theta
     * @return
     */
    private static Quaternion createZRotation(final double theta) {
        double halfAngle = theta * 0.5;

        return Quaternion.of(Math.cos(halfAngle), 0, 0, Math.sin(halfAngle));
    }

    /**
     * Compute the norm of a vector.
     * @param vec
     * @return
     */
    private static double norm(double[] vec) {
        double sum = 0.0;
        for (int i = 0; i < vec.length; ++i) {
            sum += vec[i] * vec[i];
        }
        return Math.sqrt(sum);
    }

    /**
     * Compute the angle between two vectors.
     * @param a
     * @param b
     * @return
     */
    private static double angle(double[] a, double[] b) {
        double cos = dot(a, b) / (norm(a) * norm(b));
        return Math.acos(cos);
    }

    /**
     * Compute the dot product of two vectors. The arrays are assumed to
     * have the same length.
     * @param a
     * @param b
     * @return
     */
    private static double dot(double[] a, double[] b) {
        double result = 0.0;
        for (int i = 0; i < a.length; ++i) {
            result += a[i] * b[i];
        }
        return result;
    }

    /**
     * Tranform the vector by assigning its components to the vectorial part of a quaternion
     * and then multiplying it on the right by the quaternion and the left by the quaternion's
     * conjugate (inverse).
     * @param q the quaternion instance
     * @param vec the 3D vector to transform
     * @return the transformed 3D vector
     */
    private static double[] transformVector(Quaternion q, double[] vec) {
        Quaternion qVec = Quaternion.of(0, vec[0], vec[1], vec[2]);
        Quaternion qConj = q.conjugate();

        Quaternion result = q.multiply(qVec).multiply(qConj);

        return new double[] {result.getX(), result.getY(), result.getZ()};
    }

    /**
     * Assert that the given quaternions are equal.
     * @param expected
     * @param actual
     */
    private static void assertQuaternion(Quaternion expected, Quaternion actual) {
        String msg = "Expected quaternion to equal " + expected + " but was " + actual;

        Assertions.assertEquals(expected.getW(), actual.getW(), EPS, msg);
        Assertions.assertEquals(expected.getX(), actual.getX(), EPS, msg);
        Assertions.assertEquals(expected.getY(), actual.getY(), EPS, msg);
        Assertions.assertEquals(expected.getZ(), actual.getZ(), EPS, msg);
    }


}
