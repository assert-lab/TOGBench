/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link RandomUtils}
 */
public class RandomUtilsTest_OE25Dev {

    /**
     * For comparing doubles and floats
     */
    private static final double DELTA = 1e-5;

    /**
     * Tests next boolean
     */

    /**
     * Tests a zero byte array length.
     */

    /**
     * Tests random byte array.
     */

    /**
     * Test next int range with minimal range.
     */

    /**
     * Tests next int range.
     */

    /**
     * Tests next int range, random result.
     */

    /**
     * Test next double range with minimal range.
     */

    /**
     * Test next float range with minimal range.
     */

    /**
     * Tests next double range.
     */

    /**
     * Tests next double range, random result.
     */

    /**
     * Tests next float range.
     */

    /**
     * Tests next float range, random result.
     */

    /**
     * Test next long range with minimal range.
     */

    /**
     * Tests next long range.
     */

    /**
     * Tests next long range, random result.
     */

    /**
     * Tests extreme range.
     */

    /**
     * Tests extreme range.
     */

    /**
     * Tests extreme range.
     */

    /**
     * Tests extreme range.
     */

    /**
     * Test a large value for long. A previous implementation using
     * {@link RandomUtils#nextDouble(double, double)} could generate a value equal
     * to the upper limit.
     *
     * <pre>
     * return (long) nextDouble(startInclusive, endExclusive);
     * </pre>
     *
     * <p>See LANG-1592.</p>
     */

    @Test
    public void testNextBytesNegative_1_oe() throws Exception {
        try {
    RandomUtils.nextBytes(-1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNextIntNegative_1_oe() throws Exception {
        try {
    RandomUtils.nextInt(-1, 1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNextLongNegative_1_oe() throws Exception {
        try {
    RandomUtils.nextLong(-1, 1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNextDoubleNegative_1_oe() throws Exception {
        try {
    RandomUtils.nextDouble(-1, 1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNextFloatNegative_1_oe() throws Exception {
        try {
    RandomUtils.nextFloat(-1, 1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNextIntLowerGreaterUpper_1_oe() throws Exception {
        try {
    RandomUtils.nextInt(2, 1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNextLongLowerGreaterUpper_1_oe() throws Exception {
        try {
    RandomUtils.nextLong(2, 1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNextDoubleLowerGreaterUpper_1_oe() throws Exception {
        try {
    RandomUtils.nextDouble(2, 1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNextFloatLowerGreaterUpper_1_oe() throws Exception {
        try {
    RandomUtils.nextFloat(2, 1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
