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

/**
 * Test cases for the {@link BrentSolver} class.
 */
class BrentSolverTest_OE25Dev {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1e-6;
    private static final double DEFAULT_RELATIVE_ACCURACY = 1e-14;
    private static final double DEFAULT_FUNCTION_ACCURACY = 1e-15;

    @Test
    void testTooManyCalls_3_oe() {
        final DoubleUnaryOperator func = new QuinticFunction();
        final BrentSolver solver = new BrentSolver(DEFAULT_ABSOLUTE_ACCURACY,
                                                   DEFAULT_RELATIVE_ACCURACY,
                                                   DEFAULT_FUNCTION_ACCURACY);

        // Very large bracket around 1 for testing fast growth behavior.
        final MonitoredFunction f = new MonitoredFunction(func);
        final double result = solver.findRoot(f, 0.85, 5);
        // removed other assertion
        // removed other assertion

        final MonitoredFunction f2 = new MonitoredFunction(func, 10);
        final IllegalStateException ex = Assertions.assertThrows(IllegalStateException.class, () -> solver.findRoot(f2, 0.85, 5), "Expected too many calls condition");
    }

}
