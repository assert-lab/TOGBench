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
package org.apache.commons.numbers.rootfinder;

import java.util.function.DoubleUnaryOperator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test cases for the {@link BrentSolver} class.
 */
class BrentSolverTest_OE25Dev {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1e-6;
    private static final double DEFAULT_RELATIVE_ACCURACY = 1e-14;
    private static final double DEFAULT_FUNCTION_ACCURACY = 1e-15;

    @Test
    void testSinZero_1_oe() {
        final DoubleUnaryOperator func = new Sin();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 3, 4);
        Assertions.assertEquals(Math.PI, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testSinZero_2_oe() {
        final DoubleUnaryOperator func = new Sin();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 3, 4);
        Assertions.assertTrue(f.getCallsCount() <= 7);
    }

    @Test
    void testSinZero_3_oe() {
        final DoubleUnaryOperator func = new Sin();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 3, 4);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 1, 4);
        Assertions.assertEquals(Math.PI, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testSinZero_4_oe() {
        final DoubleUnaryOperator func = new Sin();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 3, 4);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 1, 4);
        Assertions.assertTrue(f.getCallsCount() <= 8);
    }

    @Test
    void testQuinticZero_1_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);
        Assertions.assertEquals(0, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testQuinticZero_2_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);
        Assertions.assertTrue(f.getCallsCount() <= 3);
    }

    @Test
    void testQuinticZero_3_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);
        Assertions.assertEquals(0, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testQuinticZero_4_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);
        Assertions.assertTrue(f.getCallsCount() <= 7);
    }

    @Test
    void testQuinticZero_5_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.3, 0.45);
        Assertions.assertEquals(0, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testQuinticZero_6_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.3, 0.45);
        Assertions.assertTrue(f.getCallsCount() <= 8);
    }

    @Test
    void testQuinticZero_7_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.3, 0.45);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.3, 0.7);
        Assertions.assertEquals(0.5, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testQuinticZero_8_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.3, 0.45);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.3, 0.7);
        Assertions.assertTrue(f.getCallsCount() <= 9);
    }

    @Test
    void testQuinticZero_9_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.3, 0.45);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.3, 0.7);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.2, 0.6);
        Assertions.assertEquals(0.5, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testQuinticZero_10_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.3, 0.45);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.3, 0.7);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.2, 0.6);
        Assertions.assertTrue(f.getCallsCount() <= 10);
    }

    @Test
    void testQuinticZero_11_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.3, 0.45);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.3, 0.7);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.2, 0.6);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.05, 0.95);
        Assertions.assertEquals(0.5, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testQuinticZero_12_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.3, 0.45);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.3, 0.7);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.2, 0.6);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.05, 0.95);
        Assertions.assertTrue(f.getCallsCount() <= 11);
    }

    @Test
    void testQuinticZero_13_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.3, 0.45);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.3, 0.7);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.2, 0.6);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.05, 0.95);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.85, 1.25);
        Assertions.assertEquals(1.0, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testQuinticZero_14_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.3, 0.45);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.3, 0.7);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.2, 0.6);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.05, 0.95);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.85, 1.25);
        Assertions.assertTrue(f.getCallsCount() <= 11);
    }

    @Test
    void testQuinticZero_15_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.3, 0.45);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.3, 0.7);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.2, 0.6);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.05, 0.95);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.85, 1.25);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.8, 1.2);
        Assertions.assertEquals(1.0, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testQuinticZero_16_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.3, 0.45);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.3, 0.7);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.2, 0.6);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.05, 0.95);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.85, 1.25);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.8, 1.2);
        Assertions.assertTrue(f.getCallsCount() <= 11);
    }

    @Test
    void testQuinticZero_17_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.3, 0.45);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.3, 0.7);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.2, 0.6);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.05, 0.95);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.85, 1.25);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.8, 1.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.85, 1.75);
        Assertions.assertEquals(1.0, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testQuinticZero_18_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.3, 0.45);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.3, 0.7);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.2, 0.6);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.05, 0.95);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.85, 1.25);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.8, 1.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.85, 1.75);
        Assertions.assertTrue(f.getCallsCount() <= 13);
    }

    @Test
    void testQuinticZero_19_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.3, 0.45);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.3, 0.7);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.2, 0.6);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.05, 0.95);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.85, 1.25);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.8, 1.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.85, 1.75);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.55, 1.45);
        Assertions.assertEquals(1.0, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testQuinticZero_20_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.2, 0.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.1, 0.3);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, -0.3, 0.45);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.3, 0.7);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.2, 0.6);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.05, 0.95);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.85, 1.25);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.8, 1.2);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.85, 1.75);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.55, 1.45);
        Assertions.assertTrue(f.getCallsCount() <= 10);
    }

    @Test
    void testTooManyCalls_1_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        final MonitoredFunction f = new MonitoredFunction(func);
        final double result = solver.findRoot(f, 0.85, 5);
        Assertions.assertEquals(1.0, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testTooManyCalls_2_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        final MonitoredFunction f = new MonitoredFunction(func);
        final double result = solver.findRoot(f, 0.85, 5);
        Assertions.assertTrue(f.getCallsCount() <= 15);
    }

    @Test
    void testTooManyCalls_3_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        final MonitoredFunction f = new MonitoredFunction(func);
        final double result = solver.findRoot(f, 0.85, 5);

        final MonitoredFunction f2 = new MonitoredFunction(func, 10);
        try {
    solver.findRoot(f2, 0.85, 5);
    fail("IllegalStateException: Expected too many calls condition");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testRootEndpoints_1_oe() {
        final DoubleUnaryOperator f = new Sin();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result = solver.findRoot(f, Math.PI, 4);
        Assertions.assertEquals(Math.PI, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testRootEndpoints_2_oe() {
        final DoubleUnaryOperator f = new Sin();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result = solver.findRoot(f, Math.PI, 4);

        result = solver.findRoot(f, 3, Math.PI);
        Assertions.assertEquals(Math.PI, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testRootEndpoints_3_oe() {
        final DoubleUnaryOperator f = new Sin();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result = solver.findRoot(f, Math.PI, 4);

        result = solver.findRoot(f, 3, Math.PI);

        result = solver.findRoot(f, Math.PI, 3.5, 4);
        Assertions.assertEquals(Math.PI, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testRootEndpoints_4_oe() {
        final DoubleUnaryOperator f = new Sin();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        double result = solver.findRoot(f, Math.PI, 4);

        result = solver.findRoot(f, 3, Math.PI);

        result = solver.findRoot(f, Math.PI, 3.5, 4);

        result = solver.findRoot(f, 3, 3.07, Math.PI);
        Assertions.assertEquals(Math.PI, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testBadEndpoints_2_oe() {
        final DoubleUnaryOperator f = new Sin();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);
        try {  // Bad interval.
            solver.findRoot(f, 1, -1);
        } catch (SolverException ex) {
            Assertions.assertNotEquals(-1, ex.getMessage().indexOf(" > "));
    }
    }

    @Test
    void testBadEndpoints_4_oe() {
        final DoubleUnaryOperator f = new Sin();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);
        try {  // Bad interval.
            solver.findRoot(f, 1, -1);
        } catch (SolverException ex) {
        }
        try {  // No bracketing.
            solver.findRoot(f, 1, 1.5);
        } catch (SolverException ex) {
            Assertions.assertNotEquals(-1, ex.getMessage().indexOf("No bracketing"));
    }
    }

    @Test
    void testBadEndpoints_6_oe() {
        final DoubleUnaryOperator f = new Sin();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);
        try {  // Bad interval.
            solver.findRoot(f, 1, -1);
        } catch (SolverException ex) {
        }
        try {  // No bracketing.
            solver.findRoot(f, 1, 1.5);
        } catch (SolverException ex) {
        }
        try {  // No bracketing.
            solver.findRoot(f, 1, 1.2, 1.5);
        } catch (SolverException ex) {
            Assertions.assertNotEquals(-1, ex.getMessage().indexOf("No bracketing"));
    }
    }

    @Test
    void testBadInitialGuess_2_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        try {
            double result = solver.findRoot(func, 0.0, 7.0, 0.6);
        } catch (SolverException ex) {
            Assertions.assertNotEquals(-1, ex.getMessage().indexOf("out of range"));
    }
    }

    @Test
    void testInitialGuess_1_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);
        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 7.0);
        Assertions.assertEquals(1.0, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testInitialGuess_2_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);
        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 7.0);
        final int referenceCallsCount = f.getCallsCount();
        Assertions.assertTrue(referenceCallsCount >= 13);
    }

    @Test
    void testInitialGuess_3_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);
        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 7.0);
        final int referenceCallsCount = f.getCallsCount();

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 0.61, 7.0);
        Assertions.assertEquals(1.0, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testInitialGuess_4_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);
        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 7.0);
        final int referenceCallsCount = f.getCallsCount();

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 0.61, 7.0);
        Assertions.assertTrue(f.getCallsCount() > referenceCallsCount);
    }

    @Test
    void testInitialGuess_5_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);
        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 7.0);
        final int referenceCallsCount = f.getCallsCount();

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 0.61, 7.0);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 0.9999990001, 7.0);
        Assertions.assertEquals(1.0, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testInitialGuess_6_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);
        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 7.0);
        final int referenceCallsCount = f.getCallsCount();

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 0.61, 7.0);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 0.9999990001, 7.0);
        Assertions.assertTrue(f.getCallsCount() < referenceCallsCount);
    }

    @Test
    void testInitialGuess_7_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);
        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 7.0);
        final int referenceCallsCount = f.getCallsCount();

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 0.61, 7.0);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 0.9999990001, 7.0);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 1.0, 7.0);
        Assertions.assertEquals(1.0, result, DEFAULT_ABSOLUTE_ACCURACY);
    }

    @Test
    void testInitialGuess_8_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);
        double result;
        MonitoredFunction f;

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 7.0);
        final int referenceCallsCount = f.getCallsCount();

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 0.61, 7.0);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 0.9999990001, 7.0);

        f = new MonitoredFunction(func);
        result = solver.findRoot(f, 0.6, 1.0, 7.0);
        Assertions.assertEquals(1, f.getCallsCount());
    }

}
