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

/**
 * Test cases for the {@link LogBinomialCoefficient} class.
 */
class LogBinomialCoefficientTest_OE25Dev {
    /** Verify that b(0,0) = 1 */

    /**
     * Tests correctness for large n and sharpness of upper bound in API doc
     * JIRA: MATH-241
     */

    @Test
    void testBinomialCoefficientFail1_1_oe() {
        try {
    LogBinomialCoefficient.value(4, 5);
    org.junit.jupiter.api.Assertions.fail("CombinatoricsException");
} catch (CombinatoricsException e) {
}
    }

    @Test
    void testBinomialCoefficientFail2_1_oe() {
        try {
    LogBinomialCoefficient.value(-1, -2);
    org.junit.jupiter.api.Assertions.fail("CombinatoricsException");
} catch (CombinatoricsException e) {
}
    }

}
