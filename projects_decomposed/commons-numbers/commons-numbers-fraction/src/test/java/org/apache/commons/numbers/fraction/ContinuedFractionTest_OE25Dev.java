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
package org.apache.commons.numbers.fraction;

import java.util.Locale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link ContinuedFraction}.
 */
class ContinuedFractionTest_OE25Dev {

    private static void assertExceptionMessageContains(Throwable t, String text) {
        Assertions.assertTrue(t.getMessage().toLowerCase(Locale.ROOT).contains(text),()-> "Missing '" + text + "' from exception message: " + t.getMessage());
    }

    // NUMBERS-46

    // NUMBERS-46

    @Test
    void testMaxIterationsThrows_1_oe() throws Exception {
        ContinuedFraction cf = new ContinuedFraction() {
            @Override
            public double getA(int n, double x) {
                return 1;
            }

            @Override
            public double getB(int n, double x) {
                return 1;
            }
        };

        final double eps = 1e-8;
        final int maxIterations = 3;
        final Throwable t = Assertions.assertThrows(FractionException.class, () -> cf.evaluate(0, eps, maxIterations));
    }

    @Test
    void testNaNThrows_1_oe() throws Exception {
        // Create a NaN during the iteration
        ContinuedFraction cf = new ContinuedFraction() {
            @Override
            public double getA(int n, double x) {
                return 1;
            }

            @Override
            public double getB(int n, double x) {
                return n == 0 ? 1 : Double.NaN;
            }
        };

        final double eps = 1e-8;
        final Throwable t = Assertions.assertThrows(FractionException.class, () -> cf.evaluate(0, eps, 5));
    }

    @Test
    void testInfThrows_1_oe() throws Exception {
        // Create an infinity during the iteration:
        // a / cPrev  => a_1 / b_0 => Double.MAX_VALUE / 0.5
        ContinuedFraction cf = new ContinuedFraction() {
            @Override
            public double getA(int n, double x) {
                return n == 0 ? 1 : Double.MAX_VALUE;
            }

            @Override
            public double getB(int n, double x) {
                return 0.5;
            }
        };

        final double eps = 1e-8;
        final Throwable t = Assertions.assertThrows(FractionException.class, () -> cf.evaluate(0, eps, 5));
    }

}
