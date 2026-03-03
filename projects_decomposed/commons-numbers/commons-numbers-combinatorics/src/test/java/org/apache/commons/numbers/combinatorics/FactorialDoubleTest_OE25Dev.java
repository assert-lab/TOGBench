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
package org.apache.commons.numbers.combinatorics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test cases for the {@link FactorialDouble} class.
 */
class FactorialDoubleTest_OE25Dev {

    /**
     * Direct multiplication implementation.
     */
    private double factorialDirect(int n) {
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    @Test
    void testFactorialZero_1_oe() {
        Assertions.assertEquals(1, FactorialDouble.create().value(0), "0!");
    }

    @Test
    void testFactorialDirect_1_oe() {
        for (int i = 1; i < 21; i++) {
            Assertions.assertEquals(factorialDirect(i),FactorialDouble.create().value(i),i + "!");
    }
    }

    @Test
    void testLargestFactorialDouble_1_oe() {
        final int n = 170;
        Assertions.assertNotEquals(Double.POSITIVE_INFINITY,FactorialDouble.create().value(n),()-> n + "!");
    }

    @Test
    void testFactorialDoubleTooLarge_1_oe() {
        final int n = 171;
        Assertions.assertEquals(Double.POSITIVE_INFINITY,FactorialDouble.create().value(n),()-> n + "!");
    }

    @Test
    void testNonPositiveArgumentWithCache_1_oe() {
        try {
    FactorialDouble.create().withCache(-1);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testNonPositiveArgument_1_oe() {
        try {
    FactorialDouble.create().value(-1);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testCompareDirectWithoutCache_1_oe() {

        final int max = 100;
        final FactorialDouble f = FactorialDouble.create();

        for (int i = 0; i < max; i++) {
            final double expected = factorialDirect(i);
            Assertions.assertEquals(expected,f.value(i),100 * Math.ulp(expected),i + "! ");
    }
    }

    @Test
    void testCompareDirectWithCache_1_oe() {
        final int max = 100;
        final FactorialDouble f = FactorialDouble.create().withCache(max);

        for (int i = 0; i < max; i++) {
            final double expected = factorialDirect(i);
            Assertions.assertEquals(expected,f.value(i),100 * Math.ulp(expected),i + "! ");
    }
    }

    @Test
    void testCacheIncrease_1_oe() {
        final int max = 100;
        final FactorialDouble f1 = FactorialDouble.create().withCache(max);
        final FactorialDouble f2 = f1.withCache(2 * max);

        final int val = max + max / 2;
        Assertions.assertEquals(f1.value(val), f2.value(val));
    }

    @Test
    void testZeroCache_1_oe() {
        final FactorialDouble f = FactorialDouble.create().withCache(0);
        Assertions.assertEquals(1, f.value(0));
    }

    @Test
    void testZeroCache_2_oe() {
        final FactorialDouble f = FactorialDouble.create().withCache(0);
        Assertions.assertEquals(1, f.value(1));
    }

    @Test
    void testUselessCache_1_oe() {
        Assertions.assertDoesNotThrow(() -> { LogFactorial.create().withCache(1); LogFactorial.create().withCache(2); });
    }

    @Test
    void testCacheDecrease_1_oe() {
        final int max = 100;
        final FactorialDouble f1 = FactorialDouble.create().withCache(max);
        final FactorialDouble f2 = f1.withCache(max / 2);

        final int val = max / 4;
        Assertions.assertEquals(f1.value(val), f2.value(val));
    }

}
