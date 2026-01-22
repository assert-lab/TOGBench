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
    void testGcd_1_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        Assertions.assertEquals(0, ArithmeticUtils.gcd(0, 0));
    }

    @Test
    void testGcd_2_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion

        Assertions.assertEquals(b, ArithmeticUtils.gcd(0, b));
    }

    @Test
    void testGcd_3_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(a, ArithmeticUtils.gcd(a, 0));
    }

    @Test
    void testGcd_4_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(b, ArithmeticUtils.gcd(0, -b));
    }

    @Test
    void testGcd_5_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(a, ArithmeticUtils.gcd(-a, 0));
    }

    @Test
    void testGcd_6_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(10, ArithmeticUtils.gcd(a, b));
    }

    @Test
    void testGcd_7_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(10, ArithmeticUtils.gcd(-a, b));
    }

    @Test
    void testGcd_8_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(10, ArithmeticUtils.gcd(a, -b));
    }

    @Test
    void testGcd_9_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(10, ArithmeticUtils.gcd(-a, -b));
    }

    @Test
    void testGcd_10_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, ArithmeticUtils.gcd(a, c));
    }

    @Test
    void testGcd_11_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, ArithmeticUtils.gcd(-a, c));
    }

    @Test
    void testGcd_12_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, ArithmeticUtils.gcd(a, -c));
    }

    @Test
    void testGcd_13_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, ArithmeticUtils.gcd(-a, -c));
    }

    @Test
    void testGcd_14_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(3 * (1 << 15), ArithmeticUtils.gcd(3 * (1 << 20), 9 * (1 << 15)));
    }

    @Test
    void testGcd_15_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertEquals(Integer.MAX_VALUE, ArithmeticUtils.gcd(Integer.MAX_VALUE, 0));
    }

    @Test
    void testGcd_16_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(Integer.MAX_VALUE, ArithmeticUtils.gcd(-Integer.MAX_VALUE, 0));
    }

    @Test
    void testGcd_17_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1 << 30, ArithmeticUtils.gcd(1 << 30, -Integer.MIN_VALUE));
    }

    @Test
    void testGcdConsistency_1_oe() {
        // Use Integer to prevent varargs vs array issue with Arrays.asList
        Integer[] primeList = {19, 23, 53, 67, 73, 79, 101, 103, 111, 131};

        for (int i = 0; i < 20; i++) {
            Collections.shuffle(Arrays.asList(primeList));
            int p1 = primeList[0];
            int p2 = primeList[1];
            int p3 = primeList[2];
            int p4 = primeList[3];
            int i1 = p1 * p2 * p3;
            int i2 = p1 * p2 * p4;
            int gcd = p1 * p2;
            Assertions.assertEquals(gcd, ArithmeticUtils.gcd(i1, i2));
    }
    }

    @Test
    void testGcdConsistency_2_oe() {
        // Use Integer to prevent varargs vs array issue with Arrays.asList
        Integer[] primeList = {19, 23, 53, 67, 73, 79, 101, 103, 111, 131};

        for (int i = 0; i < 20; i++) {
            Collections.shuffle(Arrays.asList(primeList));
            int p1 = primeList[0];
            int p2 = primeList[1];
            int p3 = primeList[2];
            int p4 = primeList[3];
            int i1 = p1 * p2 * p3;
            int i2 = p1 * p2 * p4;
            int gcd = p1 * p2;
            // removed other assertion
            long l1 = i1;
            long l2 = i2;
            Assertions.assertEquals(gcd, ArithmeticUtils.gcd(l1, l2));
    }
    }

    @Test
    void  testGcdLong_1_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        Assertions.assertEquals(0, ArithmeticUtils.gcd(0L, 0));
    }

    @Test
    void  testGcdLong_2_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion

        Assertions.assertEquals(b, ArithmeticUtils.gcd(0, b));
    }

    @Test
    void  testGcdLong_3_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(a, ArithmeticUtils.gcd(a, 0));
    }

    @Test
    void  testGcdLong_4_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(b, ArithmeticUtils.gcd(0, -b));
    }

    @Test
    void  testGcdLong_5_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(a, ArithmeticUtils.gcd(-a, 0));
    }

    @Test
    void  testGcdLong_6_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(10, ArithmeticUtils.gcd(a, b));
    }

    @Test
    void  testGcdLong_7_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(10, ArithmeticUtils.gcd(-a, b));
    }

    @Test
    void  testGcdLong_8_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(10, ArithmeticUtils.gcd(a, -b));
    }

    @Test
    void  testGcdLong_9_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(10, ArithmeticUtils.gcd(-a, -b));
    }

    @Test
    void  testGcdLong_10_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, ArithmeticUtils.gcd(a, c));
    }

    @Test
    void  testGcdLong_11_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, ArithmeticUtils.gcd(-a, c));
    }

    @Test
    void  testGcdLong_12_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, ArithmeticUtils.gcd(a, -c));
    }

    @Test
    void  testGcdLong_13_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, ArithmeticUtils.gcd(-a, -c));
    }

    @Test
    void  testGcdLong_14_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(3L * (1L << 45), ArithmeticUtils.gcd(3L * (1L << 50), 9L * (1L << 45)));
    }

    @Test
    void  testGcdLong_15_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertEquals(1L << 45, ArithmeticUtils.gcd(1L << 45, Long.MIN_VALUE));
    }

    @Test
    void  testGcdLong_16_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        Assertions.assertEquals(Long.MAX_VALUE, ArithmeticUtils.gcd(Long.MAX_VALUE, 0L));
    }

    @Test
    void  testGcdLong_17_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(Long.MAX_VALUE, ArithmeticUtils.gcd(-Long.MAX_VALUE, 0L));
    }

    @Test
    void  testGcdLong_18_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, ArithmeticUtils.gcd(60247241209L, 153092023L));
    }

    @Test
    void testLcm_1_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        Assertions.assertEquals(0, ArithmeticUtils.lcm(0, b));
    }

    @Test
    void testLcm_2_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion
        Assertions.assertEquals(0, ArithmeticUtils.lcm(a, 0));
    }

    @Test
    void testLcm_3_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(b, ArithmeticUtils.lcm(1, b));
    }

    @Test
    void testLcm_4_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(a, ArithmeticUtils.lcm(a, 1));
    }

    @Test
    void testLcm_5_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(150, ArithmeticUtils.lcm(a, b));
    }

    @Test
    void testLcm_6_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(150, ArithmeticUtils.lcm(-a, b));
    }

    @Test
    void testLcm_7_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(150, ArithmeticUtils.lcm(a, -b));
    }

    @Test
    void testLcm_8_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(150, ArithmeticUtils.lcm(-a, -b));
    }

    @Test
    void testLcm_9_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(2310, ArithmeticUtils.lcm(a, c));
    }

    @Test
    void testLcm_10_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Assert that no intermediate value overflows:
        // The naive implementation of lcm(a,b) would be (a*b)/gcd(a,b)
        Assertions.assertEquals((1 << 20) * 15, ArithmeticUtils.lcm((1 << 20) * 3, (1 << 20) * 5));
    }

    @Test
    void testLcm_11_oe() {
        int a = 30;
        int b = 50;
        int c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Assert that no intermediate value overflows:
        // The naive implementation of lcm(a,b) would be (a*b)/gcd(a,b)
        // removed other assertion

        // Special case
        Assertions.assertEquals(0, ArithmeticUtils.lcm(0, 0));
    }

    @Test
    void testLcmLong_1_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        Assertions.assertEquals(0, ArithmeticUtils.lcm(0, b));
    }

    @Test
    void testLcmLong_2_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion
        Assertions.assertEquals(0, ArithmeticUtils.lcm(a, 0));
    }

    @Test
    void testLcmLong_3_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(b, ArithmeticUtils.lcm(1, b));
    }

    @Test
    void testLcmLong_4_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(a, ArithmeticUtils.lcm(a, 1));
    }

    @Test
    void testLcmLong_5_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(150, ArithmeticUtils.lcm(a, b));
    }

    @Test
    void testLcmLong_6_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(150, ArithmeticUtils.lcm(-a, b));
    }

    @Test
    void testLcmLong_7_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(150, ArithmeticUtils.lcm(a, -b));
    }

    @Test
    void testLcmLong_8_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(150, ArithmeticUtils.lcm(-a, -b));
    }

    @Test
    void testLcmLong_9_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(2310, ArithmeticUtils.lcm(a, c));
    }

    @Test
    void testLcmLong_10_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Long.MAX_VALUE, ArithmeticUtils.lcm(60247241209L, 153092023L));
    }

    @Test
    void testLcmLong_11_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // Assert that no intermediate value overflows:
        // The naive implementation of lcm(a,b) would be (a*b)/gcd(a,b)
        Assertions.assertEquals((1L << 50) * 15, ArithmeticUtils.lcm((1L << 45) * 3, (1L << 50) * 5));
    }

    @Test
    void testLcmLong_12_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // Assert that no intermediate value overflows:
        // The naive implementation of lcm(a,b) would be (a*b)/gcd(a,b)
        // removed other assertion

        // Special case
        Assertions.assertEquals(0L, ArithmeticUtils.lcm(0L, 0L));
    }

    @Test
    void testLcmLong_15_oe() {
        long a = 30;
        long b = 50;
        long c = 77;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // Assert that no intermediate value overflows:
        // The naive implementation of lcm(a,b) would be (a*b)/gcd(a,b)
        // removed other assertion

        // Special case
        // removed other assertion

        try {
            // lcm == abs(MIN_VALUE) cannot be represented as a nonnegative int
            ArithmeticUtils.lcm(Long.MIN_VALUE, 1);
            // removed other assertion
        } catch (ArithmeticException expected) {
            // expected
        }

        try {
            // lcm == abs(MIN_VALUE) cannot be represented as a nonnegative int
            ArithmeticUtils.lcm(Long.MIN_VALUE, 1 << 20);
            // removed other assertion
        } catch (ArithmeticException expected) {
            // expected
        }

        Assertions.assertEquals((long) Integer.MAX_VALUE * (Integer.MAX_VALUE - 1), ArithmeticUtils.lcm((long)Integer.MAX_VALUE, Integer.MAX_VALUE - 1));
    }

    @Test
    void testPow_1_oe() {

        Assertions.assertEquals(1801088541, ArithmeticUtils.pow(21, 7));
    }

    @Test
    void testPow_2_oe() {

        // removed other assertion
        Assertions.assertEquals(1, ArithmeticUtils.pow(21, 0));
    }

    @Test
    void testPow_4_oe() {

        // removed other assertion
        // removed other assertion
        try {
            ArithmeticUtils.pow(21, -7);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // expected behavior
        }

        Assertions.assertEquals(1801088541, ArithmeticUtils.pow(21, 7));
    }

    @Test
    void testPow_5_oe() {

        // removed other assertion
        // removed other assertion
        try {
            ArithmeticUtils.pow(21, -7);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // expected behavior
        }

        // removed other assertion
        Assertions.assertEquals(1, ArithmeticUtils.pow(21, 0));
    }

    @Test
    void testPow_7_oe() {

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

        Assertions.assertEquals(1801088541L, ArithmeticUtils.pow(21L, 7));
    }

    @Test
    void testPow_8_oe() {

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
        Assertions.assertEquals(1L, ArithmeticUtils.pow(21L, 0));
    }

    @Test
    void testPow_10_oe() {

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
        Assertions.assertEquals(BigInteger.valueOf(1801088541L), ArithmeticUtils.pow(twentyOne, 7));
    }

    @Test
    void testPow_11_oe() {

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
        Assertions.assertEquals(BigInteger.ONE, ArithmeticUtils.pow(twentyOne, 0));
    }

    @Test
    void testPow_13_oe() {

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

        Assertions.assertEquals(BigInteger.valueOf(1801088541L), ArithmeticUtils.pow(twentyOne, 7L));
    }

    @Test
    void testPow_14_oe() {

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
        Assertions.assertEquals(BigInteger.ONE, ArithmeticUtils.pow(twentyOne, 0L));
    }

    @Test
    void testPow_16_oe() {

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

        Assertions.assertEquals(BigInteger.valueOf(1801088541L), ArithmeticUtils.pow(twentyOne, BigInteger.valueOf(7L)));
    }

    @Test
    void testPow_17_oe() {

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
        Assertions.assertEquals(BigInteger.ONE, ArithmeticUtils.pow(twentyOne, BigInteger.ZERO));
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArithmeticUtils.pow(twentyOne, BigInteger.valueOf(-7L)));
    }

    @Test
    void testPow_19_oe() {

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
        // removed other assertion

        BigInteger bigOne =
            new BigInteger("1543786922199448028351389769265814882661837148" +
                           "4763915343722775611762713982220306372888519211" +
                           "560905579993523402015636025177602059044911261");
        Assertions.assertEquals(bigOne, ArithmeticUtils.pow(twentyOne, 103));
    }

    @Test
    void testPow_20_oe() {

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
        // removed other assertion

        BigInteger bigOne =
            new BigInteger("1543786922199448028351389769265814882661837148" +
                           "4763915343722775611762713982220306372888519211" +
                           "560905579993523402015636025177602059044911261");
        // removed other assertion
        Assertions.assertEquals(bigOne, ArithmeticUtils.pow(twentyOne, 103L));
    }

    @Test
    void testPow_21_oe() {

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
        // removed other assertion

        BigInteger bigOne =
            new BigInteger("1543786922199448028351389769265814882661837148" +
                           "4763915343722775611762713982220306372888519211" +
                           "560905579993523402015636025177602059044911261");
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(bigOne, ArithmeticUtils.pow(twentyOne, BigInteger.valueOf(103L)));
    }

    @Test
    void testPowIntOverflow_1_oe() {
        Assertions.assertThrows(ArithmeticException.class, () -> ArithmeticUtils.pow(21, 8) );
    }

    @Test
    void testPowInt_1_oe() {
        final int base = 21;

        Assertions.assertEquals(85766121L, ArithmeticUtils.pow(base, 6));
    }

    @Test
    void testPowInt_2_oe() {
        final int base = 21;

        // removed other assertion
        Assertions.assertEquals(1801088541L, ArithmeticUtils.pow(base, 7));
    }

    @Test
    void testPowNegativeIntOverflow_1_oe() {
        Assertions.assertThrows(ArithmeticException.class, () -> ArithmeticUtils.pow(-21, 8) );
    }

    @Test
    void testPowNegativeInt_1_oe() {
        final int base = -21;

        Assertions.assertEquals(85766121, ArithmeticUtils.pow(base, 6));
    }

    @Test
    void testPowNegativeInt_2_oe() {
        final int base = -21;

        // removed other assertion
        Assertions.assertEquals(-1801088541, ArithmeticUtils.pow(base, 7));
    }

    @Test
    void testPowMinusOneInt_1_oe() {
        final int base = -1;
        for (int i = 0; i < 100; i++) {
            final int pow = ArithmeticUtils.pow(base, i);
            Assertions.assertEquals(i % 2 == 0 ? 1 : -1, pow, "i: " + i);
    }
    }

    @Test
    void testPowOneInt_1_oe() {
        final int base = 1;
        for (int i = 0; i < 100; i++) {
            final int pow = ArithmeticUtils.pow(base, i);
            Assertions.assertEquals(1, pow, "i: " + i);
    }
    }

    @Test
    void testPowLongOverflow_1_oe() {
        Assertions.assertThrows(ArithmeticException.class, () -> ArithmeticUtils.pow(21, 15) );
    }

    @Test
    void testPowLong_1_oe() {
        final long base = 21;

        Assertions.assertEquals(154472377739119461L, ArithmeticUtils.pow(base, 13));
    }

    @Test
    void testPowLong_2_oe() {
        final long base = 21;

        // removed other assertion
        Assertions.assertEquals(3243919932521508681L, ArithmeticUtils.pow(base, 14));
    }

    @Test
    void testPowNegativeLongOverflow_1_oe() {
        Assertions.assertThrows(ArithmeticException.class, () -> ArithmeticUtils.pow(-21L, 15) );
    }

    @Test
    void testPowNegativeLong_1_oe() {
        final long base = -21;

        Assertions.assertEquals(-154472377739119461L, ArithmeticUtils.pow(base, 13));
    }

    @Test
    void testPowNegativeLong_2_oe() {
        final long base = -21;

        // removed other assertion
        Assertions.assertEquals(3243919932521508681L, ArithmeticUtils.pow(base, 14));
    }

    @Test
    void testPowMinusOneLong_1_oe() {
        final long base = -1;
        for (int i = 0; i < 100; i++) {
            final long pow = ArithmeticUtils.pow(base, i);
            Assertions.assertEquals(i % 2 == 0 ? 1 : -1, pow, "i: " + i);
    }
    }

    @Test
    void testPowOneLong_1_oe() {
        final long base = 1;
        for (int i = 0; i < 100; i++) {
            final long pow = ArithmeticUtils.pow(base, i);
            Assertions.assertEquals(1, pow, "i: " + i);
    }
    }

    @Test
    void testPowEdgeCases_1_oe() {
        Assertions.assertEquals(0, ArithmeticUtils.pow(0, 2));
    }

    @Test
    void testPowEdgeCases_2_oe() {
        // removed other assertion
        Assertions.assertEquals(0L, ArithmeticUtils.pow(0L, 2));
    }

    @Test
    void testPowEdgeCases_3_oe() {
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, ArithmeticUtils.pow(0, 1));
    }

    @Test
    void testPowEdgeCases_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0L, ArithmeticUtils.pow(0L, 1));
    }

    @Test
    void testPowEdgeCases_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, ArithmeticUtils.pow(0, 0));
    }

    @Test
    void testPowEdgeCases_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1L, ArithmeticUtils.pow(0L, 0));
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
            Assertions.assertThrows(ArithmeticException.class, () -> ArithmeticUtils.pow(3, ti));
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
            Assertions.assertThrows(ArithmeticException.class, () -> ArithmeticUtils.pow(3L, ti));
    }
    }

    @Test
    void testIsPowerOfTwo_1_oe() {
        final int n = 1025;
        final boolean[] expected = new boolean[n];
        Arrays.fill(expected, false);
        for (int i = 1; i < expected.length; i *= 2) {
            expected[i] = true;
        }
        for (int i = 0; i < expected.length; i++) {
            final boolean actual = ArithmeticUtils.isPowerOfTwo(i);
            Assertions.assertEquals(expected[i], actual, Integer.toString(i));
    }
    }

    @Test
    void testRemainderUnsignedInt_1_oe() {
        Assertions.assertEquals(36, ArithmeticUtils.remainderUnsigned(-2147479015, 63));
    }

    @Test
    void testRemainderUnsignedInt_2_oe() {
        // removed other assertion
        Assertions.assertEquals(6, ArithmeticUtils.remainderUnsigned(-2147479015, 25));
    }

    @Test
    void testRemainderUnsignedIntSpecialCases_1_oe() {
        int[] ints = getIntSpecialCases();
        for (int dividend : ints) {
            for (int divisor : ints) {
                if (divisor == 0) {
                    Assertions.assertThrows(ArithmeticException.class, () -> ArithmeticUtils.remainderUnsigned(dividend, divisor) );
    }
    }
    }
    }

    @Test
    void testRemainderUnsignedIntSpecialCases_2_oe() {
        int[] ints = getIntSpecialCases();
        for (int dividend : ints) {
            for (int divisor : ints) {
                if (divisor == 0) {
                    // removed other assertion
                } else {
                    Assertions.assertEquals(remainderUnsignedExpected(dividend, divisor), ArithmeticUtils.remainderUnsigned(dividend, divisor));
    }
    }
    }
    }

    @Test
    void testRemainderUnsignedLong_1_oe() {
        Assertions.assertEquals(48L, ArithmeticUtils.remainderUnsigned(-2147479015L, 63L));
    }

    @Test
    void testRemainderUnsignedLongSpecialCases_2_oe() {
        long[] longs = getLongSpecialCases();
        for (long dividend : longs) {
            for (long divisor : longs) {
                if (divisor == 0L) {
                    try {
                        ArithmeticUtils.remainderUnsigned(dividend, divisor);
                        // removed other assertion
                    } catch (ArithmeticException e) {
                        // Success.
                    }
                } else {
                    Assertions.assertEquals(remainderUnsignedExpected(dividend, divisor), ArithmeticUtils.remainderUnsigned(dividend, divisor));
    }
    }
    }
    }

    @Test
    void testDivideUnsignedInt_1_oe() {
        Assertions.assertEquals(34087115, ArithmeticUtils.divideUnsigned(-2147479015, 63));
    }

    @Test
    void testDivideUnsignedInt_2_oe() {
        // removed other assertion
        Assertions.assertEquals(85899531, ArithmeticUtils.divideUnsigned(-2147479015, 25));
    }

    @Test
    void testDivideUnsignedInt_3_oe() {
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(2147483646, ArithmeticUtils.divideUnsigned(-3, 2));
    }

    @Test
    void testDivideUnsignedInt_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(330382098, ArithmeticUtils.divideUnsigned(-16, 13));
    }

    @Test
    void testDivideUnsignedInt_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(306783377, ArithmeticUtils.divideUnsigned(-16, 14));
    }

    @Test
    void testDivideUnsignedInt_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(2, ArithmeticUtils.divideUnsigned(-1, 2147483647));
    }

    @Test
    void testDivideUnsignedInt_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(2, ArithmeticUtils.divideUnsigned(-2, 2147483647));
    }

    @Test
    void testDivideUnsignedInt_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, ArithmeticUtils.divideUnsigned(-3, 2147483647));
    }

    @Test
    void testDivideUnsignedInt_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, ArithmeticUtils.divideUnsigned(-16, 2147483647));
    }

    @Test
    void testDivideUnsignedInt_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, ArithmeticUtils.divideUnsigned(-16, 2147483646));
    }

    @Test
    void testDivideUnsignedIntSpecialCases_1_oe() {
        int[] ints = getIntSpecialCases();
        for (int dividend : ints) {
            for (int divisor : ints) {
                if (divisor == 0) {
                    Assertions.assertThrows(ArithmeticException.class, () -> ArithmeticUtils.divideUnsigned(dividend, divisor) );
    }
    }
    }
    }

    @Test
    void testDivideUnsignedIntSpecialCases_2_oe() {
        int[] ints = getIntSpecialCases();
        for (int dividend : ints) {
            for (int divisor : ints) {
                if (divisor == 0) {
                    // removed other assertion
                } else {
                    Assertions.assertEquals(divideUnsignedExpected(dividend, divisor), ArithmeticUtils.divideUnsigned(dividend, divisor));
    }
    }
    }
    }

    @Test
    void testDivideUnsignedLong_1_oe() {
        Assertions.assertEquals(292805461453366231L, ArithmeticUtils.divideUnsigned(-2147479015L, 63L));
    }

    @Test
    void testDivideUnsignedLongSpecialCases_1_oe() {
        long[] longs = getLongSpecialCases();
        for (long dividend : longs) {
            for (long divisor : longs) {
                if (divisor == 0L) {
                    Assertions.assertThrows(ArithmeticException.class, () -> ArithmeticUtils.divideUnsigned(dividend, divisor) );
    }
    }
    }
    }

    @Test
    void testDivideUnsignedLongSpecialCases_2_oe() {
        long[] longs = getLongSpecialCases();
        for (long dividend : longs) {
            for (long divisor : longs) {
                if (divisor == 0L) {
                    // removed other assertion
                } else {
                    Assertions.assertEquals(divideUnsignedExpected(dividend, divisor), ArithmeticUtils.divideUnsigned(dividend, divisor));
    }
    }
    }
    }

}
