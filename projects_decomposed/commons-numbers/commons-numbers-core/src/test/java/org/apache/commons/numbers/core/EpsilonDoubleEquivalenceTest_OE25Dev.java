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
package org.apache.commons.numbers.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Precision.DoubleEquivalence} instances created with
 * {@link Precision#doubleEquivalenceOfEpsilon(double)}.
 */
class EpsilonDoubleEquivalenceTest_OE25Dev {

    /**
     * Increments the given double value {@code count} number of times
     * using {@link Math#nextUp(double)}.
     * @param n
     * @param count
     * @return
     */
    private static double nextUp(final double n, final int count) {
        double result = n;
        for (int i = 0; i < count; ++i) {
            result = Math.nextUp(result);
        }

        return result;
    }

    /**
     * Decrements the given double value {@code count} number of times
     * using {@link Math#nextDown(double)}.
     * @param n
     * @param count
     * @return
     */
    private static double nextDown(final double n, final int count) {
        double result = n;
        for (int i = 0; i < count; ++i) {
            result = Math.nextDown(result);
        }

        return result;
    }

    @Test
    void testInvalidEpsilonValues_1_oe() {
        // act/assert
        try {
    Precision.doubleEquivalenceOfEpsilon(-1d);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testInvalidEpsilonValues_2_oe() {
        // act/assert
        // removed other assertion

        String msg;

        try {
    Precision.doubleEquivalenceOfEpsilon(Double.NaN);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testInvalidEpsilonValues_4_oe() {
        // act/assert
        // removed other assertion

        String msg;

        // removed other assertion
        // removed other assertion

        try {
    Precision.doubleEquivalenceOfEpsilon(Double.POSITIVE_INFINITY);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testInvalidEpsilonValues_6_oe() {
        // act/assert
        // removed other assertion

        String msg;

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        try {
    Precision.doubleEquivalenceOfEpsilon(Double.NEGATIVE_INFINITY);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
