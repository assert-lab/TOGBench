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
package org.apache.commons.rng.sampling.distribution;

import org.apache.commons.math3.special.Gamma;
import org.apache.commons.rng.sampling.distribution.InternalUtils.FactorialLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for the {@link InternalUtils}.
 */
class InternalUtilsTest_OE25Dev {
    /** The maximum value for n! that is representable as a long. */
    private static final int MAX_REPRESENTABLE = 20;

    @Test
    void testFactorialThrowsWhenNegative_1_oe() {
        try {
    InternalUtils.factorial(-1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    void testFactorialThrowsWhenNotRepresentableAsLong_1_oe() {
        try {
    InternalUtils.factorial(MAX_REPRESENTABLE + 1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    void testLogFactorialThrowsWhenNegative_1_oe() {
        try {
    FactorialLog.create().value(-1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    void testLogFactorialWithCacheThrowsWhenNegative_1_oe() {
        try {
    FactorialLog.create().withCache(-1);
    org.junit.jupiter.api.Assertions.fail("NegativeArraySizeException");
} catch (NegativeArraySizeException e) {
}
    }

}
