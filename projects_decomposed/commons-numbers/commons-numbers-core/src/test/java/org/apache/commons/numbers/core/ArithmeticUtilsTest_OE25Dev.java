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

import java.util.Arrays;
import java.math.BigInteger;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for the {@link ArithmeticUtils} class.
 *
 */
class ArithmeticUtilsTest_OE25Dev {

    /**
     * Testing helper method.
     * @return an array of int numbers containing corner cases:<ul>
     * <li>values near the beginning of int range,</li>
     * <li>values near the end of int range,</li>
     * <li>values near zero</li>
     * <li>and some randomly distributed values.</li>
     * </ul>
     */
    private static int[] getIntSpecialCases() {
        int[] ints = new int[100];
        int i = 0;
        ints[i++] = Integer.MAX_VALUE;
        ints[i++] = Integer.MAX_VALUE - 1;
        ints[i++] = 100;
        ints[i++] = 101;
        ints[i++] = 102;
        ints[i++] = 300;
        ints[i++] = 567;
        for (int j = 0; j < 20; j++) {
            ints[i++] = j;
        }
        for (int j = i - 1; j >= 0; j--) {
            ints[i++] = ints[j] > 0 ? -ints[j] : Integer.MIN_VALUE;
        }
        java.util.Random r = new java.util.Random(System.nanoTime());
        for (; i < ints.length;) {
            ints[i++] = r.nextInt();
        }
        return ints;
    }

    /**
     * Testing helper method.
     * @return an array of long numbers containing corner cases:<ul>
     * <li>values near the beginning of long range,</li>
     * <li>values near the end of long range,</li>
     * <li>values near the beginning of int range,</li>
     * <li>values near the end of int range,</li>
     * <li>values near zero</li>
     * <li>and some randomly distributed values.</li>
     * </ul>
     */
    private static long[] getLongSpecialCases() {
        long[] longs = new long[100];
        int i = 0;
        longs[i++] = Long.MAX_VALUE;
        longs[i++] = Long.MAX_VALUE - 1L;
        longs[i++] = (long) Integer.MAX_VALUE + 1L;
        longs[i++] = Integer.MAX_VALUE;
        longs[i++] = Integer.MAX_VALUE - 1;
        longs[i++] = 100L;
        longs[i++] = 101L;
        longs[i++] = 102L;
        longs[i++] = 300L;
        longs[i++] = 567L;
        for (int j = 0; j < 20; j++) {
            longs[i++] = j;
        }
        for (int j = i - 1; j >= 0; j--) {
            longs[i++] = longs[j] > 0L ? -longs[j] : Long.MIN_VALUE;
        }
        java.util.Random r = new java.util.Random(System.nanoTime());
        for (; i < longs.length;) {
            longs[i++] = r.nextLong();
        }
        return longs;
    }

    private static long toUnsignedLong(int number) {
        return number < 0 ? 0x100000000L + (long)number : (long)number;
    }

    private static int remainderUnsignedExpected(int dividend, int divisor) {
        return (int)remainderUnsignedExpected(toUnsignedLong(dividend), toUnsignedLong(divisor));
    }

    private static int divideUnsignedExpected(int dividend, int divisor) {
        return (int)divideUnsignedExpected(toUnsignedLong(dividend), toUnsignedLong(divisor));
    }

    private static BigInteger toUnsignedBigInteger(long number) {
        return number < 0L ? BigInteger.ONE.shiftLeft(64).add(BigInteger.valueOf(number)) : BigInteger.valueOf(number);
    }

    private static long remainderUnsignedExpected(long dividend, long divisor) {
        return toUnsignedBigInteger(dividend).remainder(toUnsignedBigInteger(divisor)).longValue();
    }

    private static long divideUnsignedExpected(long dividend, long divisor) {
        return toUnsignedBigInteger(dividend).divide(toUnsignedBigInteger(divisor)).longValue();
    }

    @Test
    void testPow_18_oe() {

        // removed other assertion
        // removed other assertion
        try {
            ArithmeticUtils.pow(21, -7);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // expected behavior
        }

        // removed other assertion
        // removed other assertion
        try {
            ArithmeticUtils.pow(21, -7);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // expected behavior
        }

        // removed other assertion
        // removed other assertion
        try {
            ArithmeticUtils.pow(21L, -7);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // expected behavior
        }

        BigInteger twentyOne = BigInteger.valueOf(21L);
        // removed other assertion
        // removed other assertion
        try {
            ArithmeticUtils.pow(twentyOne, -7);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // expected behavior
        }

        // removed other assertion
        // removed other assertion
        try {
            ArithmeticUtils.pow(twentyOne, -7L);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // expected behavior
        }

        // removed other assertion
        // removed other assertion
        try {
    ArithmeticUtils.pow(twentyOne, BigInteger.valueOf(-7L));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPowIntOverflow_1_oe() {
        try {
    ArithmeticUtils.pow(21, 8);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testPowNegativeIntOverflow_1_oe() {
        try {
    ArithmeticUtils.pow(-21, 8);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testPowLongOverflow_1_oe() {
        try {
    ArithmeticUtils.pow(21, 15);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testPowNegativeLongOverflow_1_oe() {
        try {
    ArithmeticUtils.pow(-21L, 15);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testPowEdgeCases_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        for (int i = 20; i <= 35; i++) {
            final int ti = i;
            try {
    ArithmeticUtils.pow(3, ti);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testPowEdgeCases_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        for (int i = 20; i <= 35; i++) {
            final int ti = i;
            // removed other assertion
        }
        for (int i = 40; i <= 70; i++) {
            final int ti = i;
            try {
    ArithmeticUtils.pow(3L, ti);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testRemainderUnsignedIntSpecialCases_1_oe() {
        int[] ints = getIntSpecialCases();
        for (int dividend : ints) {
            for (int divisor : ints) {
                if (divisor == 0) {
                    try {
    ArithmeticUtils.remainderUnsigned(dividend, divisor);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }
    }
    }

    @Test
    void testDivideUnsignedIntSpecialCases_1_oe() {
        int[] ints = getIntSpecialCases();
        for (int dividend : ints) {
            for (int divisor : ints) {
                if (divisor == 0) {
                    try {
    ArithmeticUtils.divideUnsigned(dividend, divisor);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }
    }
    }

    @Test
    void testDivideUnsignedLongSpecialCases_1_oe() {
        long[] longs = getLongSpecialCases();
        for (long dividend : longs) {
            for (long divisor : longs) {
                if (divisor == 0L) {
                    try {
    ArithmeticUtils.divideUnsigned(dividend, divisor);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }
    }
    }

}
