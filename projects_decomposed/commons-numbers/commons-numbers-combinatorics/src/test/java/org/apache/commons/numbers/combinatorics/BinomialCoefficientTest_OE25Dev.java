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

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for the {@link BinomialCoefficient} class.
 */
class BinomialCoefficientTest_OE25Dev {
    /** Cached binomial coefficients. */
    private static final List<Map<Integer, Long>> binomialCache = new ArrayList<>();

    /** Verify that b(0,0) = 1 */

    /**
     * Tests correctness for large n and sharpness of upper bound in API doc
     * JIRA: MATH-241
     */

    @Test
    void testCheckBinomial3() {
        // OK (no exception thrown)
        BinomialCoefficient.checkBinomial(5, 4);
    }

    /**
     * Exact (caching) recursive implementation to test against.
     */
    static long binomialCoefficient(int n, int k) {
        if (binomialCache.size() > n) {
            final Long cachedResult = binomialCache.get(n).get(Integer.valueOf(k));
            if (cachedResult != null) {
                return cachedResult.longValue();
            }
        }
        long result = -1;
        if ((n == k) || (k == 0)) {
            result = 1;
        } else if ((k == 1) || (k == n - 1)) {
            result = n;
        } else {
            // Reduce stack depth for larger values of n.
            if (k < n - 100) {
                binomialCoefficient(n - 100, k);
            }
            if (k > 100) {
                binomialCoefficient(n - 100, k - 100);
            }
            result = Math.addExact(binomialCoefficient(n - 1, k - 1),
                                                 binomialCoefficient(n - 1, k));
        }
        if (result == -1) {
            throw new IllegalArgumentException();
        }
        for (int i = binomialCache.size(); i < n + 1; i++) {
            binomialCache.add(new HashMap<Integer, Long>());
        }
        binomialCache.get(n).put(Integer.valueOf(k), Long.valueOf(result));
        return result;
    }

    @Test
    void testBinomialCoefficientKLargerThanN_1_oe() {
        try {
    BinomialCoefficient.value(4, 5);
    org.junit.jupiter.api.Assertions.fail("CombinatoricsException");
} catch (CombinatoricsException e) {
}
    }

    @Test
    void testBinomialCoefficientNegativeN_1_oe() {
        try {
    BinomialCoefficient.value(-1, 1);
    org.junit.jupiter.api.Assertions.fail("CombinatoricsException");
} catch (CombinatoricsException e) {
}
    }

    @Test
    void testBinomialCoefficientNegativeK_1_oe() {
        try {
    BinomialCoefficient.value(10, -1);
    org.junit.jupiter.api.Assertions.fail("CombinatoricsException");
} catch (CombinatoricsException e) {
}
    }

    @Test
    void testBinomialCoefficientNAbove66ResultOverflow_1_oe() {
        try {
    BinomialCoefficient.value(67, 30);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void checkNLessThanOne_1_oe() {
        try {
    BinomialCoefficient.checkBinomial(-1, -2);
    org.junit.jupiter.api.Assertions.fail("CombinatoricsException");
} catch (CombinatoricsException e) {
}
    }

    @Test
    void checkKGreaterThanN_1_oe() {
        try {
    BinomialCoefficient.checkBinomial(4, 5);
    org.junit.jupiter.api.Assertions.fail("CombinatoricsException");
} catch (CombinatoricsException e) {
}
    }

}
