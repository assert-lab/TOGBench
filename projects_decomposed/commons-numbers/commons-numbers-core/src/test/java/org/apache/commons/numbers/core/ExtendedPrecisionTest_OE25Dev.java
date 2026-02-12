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

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for the {@link ExtendedPrecision} class.
 */
class ExtendedPrecisionTest_OE25Dev {

    /**
     * Test {@link ExtendedPrecision#productLow(double, double, double)} computes the same
     * result as JDK 9 Math.fma(x, y, -x * y) for edge cases.
     */

    private static void assertProductLow(double expected, double x, double y) {
        // Requires a delta of 0.0 to assert -0.0 == 0.0
        Assertions.assertEquals(expected, ExtendedPrecision.productLow(x, y, x * y), 0.0);
    }

    /**
     * This demonstrates splitting a sub normal number with no information in the upper 26 bits
     * of the mantissa.
     */

    private static void assertSquareLowUnscaled(final double expected, final double x) {
        Assertions.assertEquals(expected, ExtendedPrecision.squareLowUnscaled(x, x * x));
    }


}
