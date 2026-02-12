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
package org.apache.commons.numbers.gamma;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link RegularizedBeta}.
 */
class RegularizedBetaTest_OE25Dev {
    @Test
    void testRegularizedBetaNanPositivePositive() {
        testRegularizedBeta(Double.NaN, Double.NaN, 1.0, 1.0);
    }

    @Test
    void testRegularizedBetaPositiveNanPositive() {
        testRegularizedBeta(Double.NaN, 0.5, Double.NaN, 1.0);
    }

    @Test
    void testRegularizedBetaPositivePositiveNan() {
        testRegularizedBeta(Double.NaN, 0.5, 1.0, Double.NaN);
    }

    @Test
    void testRegularizedBetaNegativePositivePositive() {
        testRegularizedBeta(Double.NaN, -0.5, 1.0, 2.0);
    }

    @Test
    void testRegularizedBetaPositiveNegativePositive() {
        testRegularizedBeta(Double.NaN, 0.5, -1.0, 2.0);
    }

    @Test
    void testRegularizedBetaPositivePositiveNegative() {
        testRegularizedBeta(Double.NaN, 0.5, 1.0, -2.0);
    }

    @Test
    void testRegularizedBetaZeroPositivePositive() {
        testRegularizedBeta(0.0, 0.0, 1.0, 2.0);
    }

    @Test
    void testRegularizedBetaPositiveZeroPositive() {
        testRegularizedBeta(Double.NaN, 0.5, 0.0, 2.0);
    }

    @Test
    void testRegularizedBetaPositivePositiveZero() {
        testRegularizedBeta(Double.NaN, 0.5, 1.0, 0.0);
    }


    @Test
    void testRegularizedBetaAboveOnePositivePositive() {
        testRegularizedBeta(Double.NaN, 1.5, 1.0, 2.0);
    }

    @Test
    void testRegularizedBetaPositivePositivePositive() {
        testRegularizedBeta(0.75, 0.5, 1.0, 2.0);
    }

    private void testRegularizedBeta(double expected,
                                     double x,
                                     double a,
                                     double b) {
        final double actual = RegularizedBeta.value(x, a, b);
        Assertions.assertEquals(expected, actual, 1e-15);
    }


}
