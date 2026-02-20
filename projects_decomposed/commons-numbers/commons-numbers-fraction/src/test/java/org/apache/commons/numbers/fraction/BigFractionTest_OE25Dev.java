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
package org.apache.commons.numbers.fraction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import org.apache.commons.numbers.core.TestUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests for {@link BigFraction}.
 */
class BigFractionTest_OE25Dev {

    /** The zero representation with positive denominator. */
    private static final BigFraction ZERO_P = BigFraction.of(0, 1);
    /** The zero representation with negative denominator. */
    private static final BigFraction ZERO_N = BigFraction.of(0, -1);

    private static void assertFraction(int expectedNumerator, int expectedDenominator, BigFraction actual) {
        Assertions.assertEquals(expectedNumerator, actual.getNumeratorAsInt());
        Assertions.assertEquals(expectedDenominator, actual.getDenominatorAsInt());
        Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    private static void assertFraction(long expectedNumerator, long expectedDenominator, BigFraction actual) {
        Assertions.assertEquals(expectedNumerator, actual.getNumeratorAsLong());
        Assertions.assertEquals(expectedDenominator, actual.getDenominatorAsLong());
        Assertions.assertEquals(Long.signum(expectedNumerator)* Long.signum(expectedDenominator),actual.signum());
    }

    private static void assertFraction(BigInteger expectedNumerator, BigInteger expectedDenominator, BigFraction actual) {
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
        Assertions.assertEquals(expectedDenominator, actual.getDenominator());
        Assertions.assertEquals(expectedNumerator.signum()* expectedDenominator.signum(),actual.signum());
    }

    private static void assertDoubleValue(double expected, BigInteger numerator, BigInteger denominator) {
        final BigFraction f = BigFraction.of(numerator, denominator);
        Assertions.assertEquals(expected, f.doubleValue());
    }

    private static void assertDoubleValue(double expected, long numerator, long denominator) {
        assertDoubleValue(expected, BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    // MATH-179

    // MATH-181
    // NUMBERS-147

    // MATH-1029

    private void assertDoubleConstructorOverflow(final double a) {
        Assertions.assertThrows(ArithmeticException.class,
            () -> BigFraction.from(a, 1.0e-12, 1000)
        );
    }

    // MATH-744

    // MATH-744

    // NUMBERS-15

    /**
     * Assert the two fractions are equal. The contract of {@link Object#hashCode()} requires
     * that the hash code must also be equal.
     *
     * <p>Ideally this method should not be called with the same instance for both arguments.
     * It is intended to be used to test different objects that are equal have the same hash code.
     * However the same object may be constructed for different arguments using factory
     * constructors, e.g. zero.
     *
     * @param f1 Fraction 1.
     * @param f2 Fraction 2.
     */
    private static void assertEqualAndHashCodeEqual(BigFraction f1, BigFraction f2) {
        Assertions.assertEquals(f1, f2);
        Assertions.assertEquals(f1.hashCode(), f2.hashCode(), "Equal fractions have different hashCode");
        // Check the computation matches the result of Arrays.hashCode and the signum.
        // This is not mandated but is a recommendation.
        final int expected = f1.signum() *
                             Arrays.hashCode(new Object[] {f1.getNumerator().abs(),
                                                           f1.getDenominator().abs()});
        Assertions.assertEquals(expected, f1.hashCode(), "Hashcode not equal to using Arrays.hashCode");
    }

    @Test
    void testConstructor_6_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Long/BigInteger arguments
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Divide by zero
        try {
    BigFraction.of(BigInteger.ONE, BigInteger.ZERO);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testConstructor_7_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Long/BigInteger arguments
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Divide by zero
        // removed other assertion

        // Null pointers
        try {
    BigFraction.of(null, BigInteger.ONE);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testConstructor_8_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Long/BigInteger arguments
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Divide by zero
        // removed other assertion

        // Null pointers
        // removed other assertion
        try {
    BigFraction.of(BigInteger.ONE, null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testConstructor_9_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Long/BigInteger arguments
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Divide by zero
        // removed other assertion

        // Null pointers
        // removed other assertion
        // removed other assertion
        try {
    BigFraction.of(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testConstructor_10_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Long/BigInteger arguments
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Divide by zero
        // removed other assertion

        // Null pointers
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    BigFraction.from(2.0 * Integer.MAX_VALUE, 1.0e-5, 100000);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testConstructorZero_1_oe() {
        Assertions.assertSame(BigFraction.ZERO, BigFraction.from(0.0));
    }

    @Test
    void testConstructorZero_2_oe() {
        // removed other assertion
        Assertions.assertSame(BigFraction.ZERO, BigFraction.from(0.0, 1e-10, 100));
    }

    @Test
    void testConstructorZero_3_oe() {
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(BigFraction.ZERO, BigFraction.from(0.0, 100));
    }

    @Test
    void testConstructorZero_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(BigFraction.ZERO, BigFraction.of(0));
    }

    @Test
    void testConstructorZero_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(BigFraction.ZERO, BigFraction.of(0L));
    }

    @Test
    void testConstructorZero_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(BigFraction.ZERO, BigFraction.of(BigInteger.ZERO));
    }

    @Test
    void testConstructorZero_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(BigFraction.ZERO, BigFraction.of(0, 1));
    }

    @Test
    void testConstructorZero_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(BigFraction.ZERO, BigFraction.of(0, -1));
    }

    @Test
    void testConstructorZero_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(BigFraction.ZERO, BigFraction.of(0L, 1L));
    }

    @Test
    void testConstructorZero_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(BigFraction.ZERO, BigFraction.of(0L, -1L));
    }

    @Test
    void testConstructorZero_11_oe() {
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
        Assertions.assertSame(BigFraction.ZERO, BigFraction.of(BigInteger.ZERO, BigInteger.ONE));
    }

    @Test
    void testConstructorZero_12_oe() {
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
        Assertions.assertSame(BigFraction.ZERO, BigFraction.of(BigInteger.ZERO, BigInteger.ONE.negate()));
    }

    @Test
    void testDoubleConstructor_8_oe() throws Exception {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from Fraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Extreme double values
        Assertions.assertEquals(1L, BigFraction.from(Double.MAX_VALUE).getDenominatorAsLong());
    }

    @Test
    void testDoubleConstructor_9_oe() throws Exception {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from Fraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Extreme double values
        // removed other assertion
        Assertions.assertEquals(1L, BigFraction.from(Double.longBitsToDouble(0x0010000000000000L)).getNumeratorAsLong());
    }

    @Test
    void testDoubleConstructor_11_oe() throws Exception {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from Fraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Extreme double values
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Check exact round-trip of double
        Assertions.assertEquals(0.00000000000001, BigFraction.from(0.00000000000001).doubleValue());
    }

    @Test
    void testDoubleConstructor_12_oe() throws Exception {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from Fraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Extreme double values
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Check exact round-trip of double
        // removed other assertion
        Assertions.assertEquals(0.40000000000001, BigFraction.from(0.40000000000001).doubleValue());
    }

    @Test
    void testDoubleConstructor_13_oe() throws Exception {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from Fraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Extreme double values
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Check exact round-trip of double
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(15.0000000000001, BigFraction.from(15.0000000000001).doubleValue());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_6_oe() throws Exception {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from Fraction
        final long pow31 = 1L << 31;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    BigFraction.from(1.0, 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testDoubleConstructorThrows_1_oe() {
        final double eps = 1e-5;
        final int maxIterations = Integer.MAX_VALUE;
        final int maxDenominator = Integer.MAX_VALUE;
        for (final double value : new double[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            try {
    BigFraction.from(value);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }
    }

    @Test
    void testDoubleConstructorThrows_2_oe() {
        final double eps = 1e-5;
        final int maxIterations = Integer.MAX_VALUE;
        final int maxDenominator = Integer.MAX_VALUE;
        for (final double value : new double[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            // removed other assertion
            try {
    BigFraction.from(value, eps, maxIterations);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }
    }

    @Test
    void testDoubleConstructorThrows_3_oe() {
        final double eps = 1e-5;
        final int maxIterations = Integer.MAX_VALUE;
        final int maxDenominator = Integer.MAX_VALUE;
        for (final double value : new double[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            // removed other assertion
            // removed other assertion
            try {
    BigFraction.from(value, maxDenominator);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }
    }

    @Test
    void testDoubleConstructorThrows_4_oe() {
        final double eps = 1e-5;
        final int maxIterations = Integer.MAX_VALUE;
        final int maxDenominator = Integer.MAX_VALUE;
        for (final double value : new double[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }
        try {
    BigFraction.from(1.0, Double.NaN, maxIterations);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testDoubleConstructorThrows_5_oe() {
        final double eps = 1e-5;
        final int maxIterations = Integer.MAX_VALUE;
        final int maxDenominator = Integer.MAX_VALUE;
        for (final double value : new double[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        try {
    BigFraction.from(1.0, -1.0, maxIterations);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testDoubleConstructorThrows_6_oe() {
        final double eps = 1e-5;
        final int maxIterations = Integer.MAX_VALUE;
        final int maxDenominator = Integer.MAX_VALUE;
        for (final double value : new double[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        try {
    BigFraction.from(1.0, eps, 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testDoubleConstructorGoldenRatioThrows_1_oe() {
        // the golden ratio is notoriously a difficult number for continuous fraction
        try {
    BigFraction.from((1 + Math.sqrt(5)) / 2, 1.0e-12, 25);
    fail("FractionException");
} catch (FractionException e) {
}
    }

    @Test
    void testDoubleConstructorWithMaxDenominatorOverFlow_1_oe() {
        try {
    BigFraction.from(1e10, 1000);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorWithMaxDenominatorOverFlow_2_oe() {
        // removed other assertion
        try {
    BigFraction.from(-1e10, 1000);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testCompareTo_1_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(0, a.compareTo(a));
    }

    @Test
    void testCompareTo_2_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

        // removed other assertion
        Assertions.assertEquals(0, a.compareTo(c));
    }

    @Test
    void testCompareTo_3_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, a.compareTo(b));
    }

    @Test
    void testCompareTo_4_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-1, b.compareTo(a));
    }

    @Test
    void testCompareTo_5_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-1, d.compareTo(a));
    }

    @Test
    void testCompareTo_6_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, a.compareTo(d));
    }

    @Test
    void testCompareTo_7_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-1, e.compareTo(a));
    }

    @Test
    void testCompareTo_8_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, a.compareTo(e));
    }

    @Test
    void testCompareTo_9_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, d.compareTo(e));
    }

    @Test
    void testCompareTo_10_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, a.compareTo(f));
    }

    @Test
    void testCompareTo_11_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

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
        Assertions.assertEquals(0, f.compareTo(a));
    }

    @Test
    void testCompareTo_12_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

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
        Assertions.assertEquals(1, f.compareTo(e));
    }

    @Test
    void testCompareTo_13_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

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
        Assertions.assertEquals(-1, e.compareTo(f));
    }

    @Test
    void testCompareTo_14_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

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
        Assertions.assertEquals(-1, g.compareTo(a));
    }

    @Test
    void testCompareTo_15_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

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
        Assertions.assertEquals(-1, g.compareTo(f));
    }

    @Test
    void testCompareTo_16_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

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
        Assertions.assertEquals(1, a.compareTo(g));
    }

    @Test
    void testCompareTo_17_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

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
        Assertions.assertEquals(-1, d.compareTo(g));
    }

    @Test
    void testCompareTo_18_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

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

        Assertions.assertEquals(0, BigFraction.of(0, 3).compareTo(BigFraction.of(0, -2)));
    }

    @Test
    void testCompareTo_19_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

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

        // removed other assertion

        // these two values are different approximations of PI
        // the first  one is approximately PI - 3.07e-18
        // the second one is approximately PI + 1.936e-17
        final BigFraction pi1 = BigFraction.of(1068966896, 340262731);
        final BigFraction pi2 = BigFraction.of(411557987, 131002976);
        Assertions.assertEquals(-1, pi1.compareTo(pi2));
    }

    @Test
    void testCompareTo_20_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

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

        // removed other assertion

        // these two values are different approximations of PI
        // the first  one is approximately PI - 3.07e-18
        // the second one is approximately PI + 1.936e-17
        final BigFraction pi1 = BigFraction.of(1068966896, 340262731);
        final BigFraction pi2 = BigFraction.of(411557987, 131002976);
        // removed other assertion
        Assertions.assertEquals(1, pi2.compareTo(pi1));
    }

    @Test
    void testCompareTo_21_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

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

        // removed other assertion

        // these two values are different approximations of PI
        // the first  one is approximately PI - 3.07e-18
        // the second one is approximately PI + 1.936e-17
        final BigFraction pi1 = BigFraction.of(1068966896, 340262731);
        final BigFraction pi2 = BigFraction.of(411557987, 131002976);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0, pi1.doubleValue() - pi2.doubleValue(), 1.0e-20);
    }

    @Test
    void testCompareTo_22_oe() {
        final BigFraction a = BigFraction.of(1, 2);
        final BigFraction b = BigFraction.of(1, 3);
        final BigFraction c = BigFraction.of(1, 2);
        final BigFraction d = BigFraction.of(-1, 2);
        final BigFraction e = BigFraction.of(1, -2);
        final BigFraction f = BigFraction.of(-1, -2);
        final BigFraction g = BigFraction.of(-1, Integer.MIN_VALUE);

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

        // removed other assertion

        // these two values are different approximations of PI
        // the first  one is approximately PI - 3.07e-18
        // the second one is approximately PI + 1.936e-17
        final BigFraction pi1 = BigFraction.of(1068966896, 340262731);
        final BigFraction pi2 = BigFraction.of(411557987, 131002976);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, ZERO_P.compareTo(ZERO_N));
    }

    @Test
    void testDoubleValue_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0.0, BigFraction.ZERO.doubleValue());
    }

    @Test
    void testDoubleValue_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0.0, ZERO_P.doubleValue());
    }

    @Test
    void testDoubleValue_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0, ZERO_N.doubleValue());
    }

    @Test
    void testDoubleValueForLargeNumeratorAndDenominator_1_oe() {
        final BigInteger pow400 = BigInteger.TEN.pow(400);
        final BigInteger pow401 = BigInteger.TEN.pow(401);
        final BigInteger two = new BigInteger("2");
        final BigFraction large = BigFraction.of(pow401.add(BigInteger.ONE),
                                                 pow400.multiply(two));

        Assertions.assertEquals(5, large.doubleValue(), 1e-15);
    }

    @Test
    void testFloatValueForLargeNumeratorAndDenominator_1_oe() {
        final BigInteger pow400 = BigInteger.TEN.pow(400);
        final BigInteger pow401 = BigInteger.TEN.pow(401);
        final BigInteger two = new BigInteger("2");
        final BigFraction large = BigFraction.of(pow401.add(BigInteger.ONE),
                                                 pow400.multiply(two));

        Assertions.assertEquals(5, large.floatValue(), 1e-15);
    }

    @Test
    void testDoubleValueForLargeNumeratorAndSmallDenominator_1_oe() {
        // NUMBERS-15
        final BigInteger pow300 = BigInteger.TEN.pow(300);
        final BigInteger pow330 = BigInteger.TEN.pow(330);
        final BigFraction large = BigFraction.of(pow330.add(BigInteger.ONE),
                                                 pow300);

        Assertions.assertEquals(1e30, large.doubleValue(), 1e-15);
    }

    @Test
    void testFloatValueForLargeNumeratorAndSmallDenominator_1_oe() {
        final BigInteger pow30 = BigInteger.TEN.pow(30);
        final BigInteger pow40 = BigInteger.TEN.pow(40);
        final BigFraction large = BigFraction.of(pow40.add(BigInteger.ONE),
                                                 pow30);

        Assertions.assertEquals(1e10f, large.floatValue(), 1e-15);
    }

    @Test
    void testFloatValue_1_oe() {
        Assertions.assertEquals(0.5f, BigFraction.of(1, 2).floatValue());
    }

    @Test
    void testFloatValue_2_oe() {
        // removed other assertion
        Assertions.assertEquals(0.5f, BigFraction.of(-1, -2).floatValue());
    }

    @Test
    void testFloatValue_3_oe() {
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-0.5f, BigFraction.of(-1, 2).floatValue());
    }

    @Test
    void testFloatValue_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-0.5f, BigFraction.of(1, -2).floatValue());
    }

    @Test
    void testFloatValue_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final float e = 1f / 3f;
        Assertions.assertEquals(e, BigFraction.of(1, 3).floatValue());
    }

    @Test
    void testFloatValue_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final float e = 1f / 3f;
        // removed other assertion
        Assertions.assertEquals(e, BigFraction.of(-1, -3).floatValue());
    }

    @Test
    void testFloatValue_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final float e = 1f / 3f;
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-e, BigFraction.of(-1, 3).floatValue());
    }

    @Test
    void testFloatValue_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final float e = 1f / 3f;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-e, BigFraction.of(1, -3).floatValue());
    }

    @Test
    void testFloatValue_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final float e = 1f / 3f;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0.0f, ZERO_P.floatValue());
    }

    @Test
    void testFloatValue_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final float e = 1f / 3f;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0.0f, ZERO_N.floatValue());
    }

    @Test
    void testIntValue_1_oe() {
        Assertions.assertEquals(0, BigFraction.of(1, 2).intValue());
    }

    @Test
    void testIntValue_2_oe() {
        // removed other assertion
        Assertions.assertEquals(0, BigFraction.of(-1, -2).intValue());
    }

    @Test
    void testIntValue_3_oe() {
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, BigFraction.of(-1, 2).intValue());
    }

    @Test
    void testIntValue_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0, BigFraction.of(1, -2).intValue());
    }

    @Test
    void testIntValue_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, BigFraction.of(3, 2).intValue());
    }

    @Test
    void testIntValue_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, BigFraction.of(-3, -2).intValue());
    }

    @Test
    void testIntValue_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-1, BigFraction.of(-3, 2).intValue());
    }

    @Test
    void testIntValue_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-1, BigFraction.of(3, -2).intValue());
    }

    @Test
    void testIntValue_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, ZERO_P.intValue());
    }

    @Test
    void testIntValue_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0, ZERO_N.intValue());
    }

    @Test
    void testLongValue_1_oe() {
        Assertions.assertEquals(0L, BigFraction.of(1, 2).longValue());
    }

    @Test
    void testLongValue_2_oe() {
        // removed other assertion
        Assertions.assertEquals(0L, BigFraction.of(-1, -2).longValue());
    }

    @Test
    void testLongValue_3_oe() {
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0L, BigFraction.of(-1, 2).longValue());
    }

    @Test
    void testLongValue_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0L, BigFraction.of(1, -2).longValue());
    }

    @Test
    void testLongValue_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1L, BigFraction.of(3, 2).longValue());
    }

    @Test
    void testLongValue_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1L, BigFraction.of(-3, -2).longValue());
    }

    @Test
    void testLongValue_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-1L, BigFraction.of(-3, 2).longValue());
    }

    @Test
    void testLongValue_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(-1L, BigFraction.of(3, -2).longValue());
    }

    @Test
    void testLongValue_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, ZERO_P.longValue());
    }

    @Test
    void testLongValue_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0, ZERO_N.longValue());
    }

    @Test
    void testBigDecimalValue_1_oe() {
        Assertions.assertEquals(new BigDecimal(0.5), BigFraction.of(1, 2).bigDecimalValue());
    }

    @Test
    void testBigDecimalValue_2_oe() {
        // removed other assertion
        Assertions.assertEquals(new BigDecimal("0.0003"), BigFraction.of(3, 10000).bigDecimalValue());
    }

    @Test
    void testBigDecimalValue_3_oe() {
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(new BigDecimal("0"), BigFraction.of(1, 3).bigDecimalValue(RoundingMode.DOWN));
    }

    @Test
    void testBigDecimalValue_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(new BigDecimal("0.333"), BigFraction.of(1, 3).bigDecimalValue(3, RoundingMode.DOWN));
    }

    @Test
    void testReciprocal_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.reciprocalTestCases()) {
            final BigFraction f = BigFraction.of(testCase.operandNumerator, testCase.operandDenominator);
            // removed other assertion
        }

        final BigFraction f = BigFraction.of(0, 3);
        try {
    f.reciprocal();
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testAdd_5_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
    BigFraction.ONE.add((BigFraction) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testAdd_6_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        try {
    BigFraction.ONE.add((BigInteger) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testDivide_5_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
    BigFraction.ONE.divide((BigFraction) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testDivide_6_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        try {
    BigFraction.ONE.divide((BigInteger) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testDivide_7_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        try {
    BigFraction.of(1, 2).divide(BigFraction.ZERO);
    fail("FractionException");
} catch (FractionException e) {
}
    }

    @Test
    void testDivide_8_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    BigFraction.of(1, 2).divide(0);
    fail("FractionException");
} catch (FractionException e) {
}
    }

    @Test
    void testDivide_9_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        try {
    BigFraction.of(1, 2).divide(0L);
    fail("FractionException");
} catch (FractionException e) {
}
    }

    @Test
    void testDivide_10_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    BigFraction.of(1, 2).divide(BigInteger.ZERO);
    fail("FractionException");
} catch (FractionException e) {
}
    }

    @Test
    void testMultiply_5_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
    BigFraction.ONE.multiply((BigFraction) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testMultiply_6_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        try {
    BigFraction.ONE.multiply((BigInteger) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testPow_2_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
            // removed other assertion
        }

        // Note: BigInteger magnitude is limited to 2^Integer.MAX_VALUE exclusive
        // in the reference implementation (up to at least JDK 14).
        try {
    BigFraction.of(2).pow(Integer.MAX_VALUE);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testPow_3_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
            // removed other assertion
        }

        // Note: BigInteger magnitude is limited to 2^Integer.MAX_VALUE exclusive
        // in the reference implementation (up to at least JDK 14).
        // removed other assertion
        try {
    BigFraction.of(1, 2).pow(Integer.MAX_VALUE);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testPow_4_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
            // removed other assertion
        }

        // Note: BigInteger magnitude is limited to 2^Integer.MAX_VALUE exclusive
        // in the reference implementation (up to at least JDK 14).
        // removed other assertion
        // removed other assertion
        try {
    BigFraction.of(2).pow(-Integer.MAX_VALUE);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testPow_5_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
            // removed other assertion
        }

        // Note: BigInteger magnitude is limited to 2^Integer.MAX_VALUE exclusive
        // in the reference implementation (up to at least JDK 14).
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    BigFraction.of(1, 2).pow(-Integer.MAX_VALUE);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testSubtract_5_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        try {
    BigFraction.ONE.subtract((BigFraction) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testSubtract_6_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        try {
    BigFraction.ONE.subtract((BigInteger) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testEqualsAndHashCode_1_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        Assertions.assertEquals(zero, zero);
    }

    @Test
    void testEqualsAndHashCode_2_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        Assertions.assertNotEquals(zero, null);
    }

    @Test
    void testEqualsAndHashCode_3_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        Assertions.assertNotEquals(zero, new Object());
    }

    @Test
    void testEqualsAndHashCode_4_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNotEquals(zero, Double.valueOf(0));
    }

    @Test
    void testEqualsAndHashCode_6_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final BigFraction one = BigFraction.of(1, 1);
        Assertions.assertNotEquals(zero, one);
    }

    @Test
    void testEqualsAndHashCode_7_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final BigFraction one = BigFraction.of(1, 1);
        // removed other assertion
        Assertions.assertNotEquals(one, zero);
    }

    @Test
    void testEqualsAndHashCode_12_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final BigFraction one = BigFraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            BigFraction f1 = BigFraction.of(-num, den);
            BigFraction f2 = BigFraction.of(num, -den);
            // removed other assertion
            // removed other assertion
            f1 = BigFraction.of(num, den);
            f2 = BigFraction.of(-num, -den);
            // removed other assertion
            // removed other assertion
        }

        // Same numerator or denominator as 1/1
        final BigFraction half = BigFraction.of(1, 2);
        final BigFraction two = BigFraction.of(2, 1);
        Assertions.assertNotEquals(one, half);
    }

    @Test
    void testEqualsAndHashCode_13_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final BigFraction one = BigFraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            BigFraction f1 = BigFraction.of(-num, den);
            BigFraction f2 = BigFraction.of(num, -den);
            // removed other assertion
            // removed other assertion
            f1 = BigFraction.of(num, den);
            f2 = BigFraction.of(-num, -den);
            // removed other assertion
            // removed other assertion
        }

        // Same numerator or denominator as 1/1
        final BigFraction half = BigFraction.of(1, 2);
        final BigFraction two = BigFraction.of(2, 1);
        // removed other assertion
        Assertions.assertNotEquals(one, two);
    }

    @Test
    void testAdditiveNeutral_1_oe() {
        Assertions.assertEquals(BigFraction.ZERO, BigFraction.ONE.zero());
    }

    @Test
    void testMultiplicativeNeutral_1_oe() {
        Assertions.assertEquals(BigFraction.ONE, BigFraction.ZERO.one());
    }

    @Test
    void testSerial_1_oe() {
        final BigFraction[] fractions = {
            BigFraction.of(3, 4), BigFraction.ONE, BigFraction.ZERO,
            BigFraction.of(17), BigFraction.from(Math.PI, 1000),
            BigFraction.of(-5, 2)
        };
        for (final BigFraction fraction : fractions) {
            Assertions.assertEquals(fraction,TestUtils.serializeAndRecover(fraction));
    }
    }

    @Test
    void testToString_1_oe() {
        Assertions.assertEquals("0", BigFraction.of(0, 3).toString());
    }

    @Test
    void testToString_2_oe() {
        // removed other assertion
        Assertions.assertEquals("0", BigFraction.of(0, -3).toString());
    }

    @Test
    void testToString_3_oe() {
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals("3", BigFraction.of(6, 2).toString());
    }

    @Test
    void testToString_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals("2 / 3", BigFraction.of(18, 27).toString());
    }

    @Test
    void testToString_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals("-10 / 11", BigFraction.of(-10, 11).toString());
    }

    @Test
    void testToString_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals("10 / -11", BigFraction.of(10, -11).toString());
    }

    @Test
    void testParse_2_oe() {
        final String[] validExpressions = new String[] {
            "1 / 2",
            "-1 / 2",
            "1 / -2",
            "-1 / -2",
            "01 / 2",
            "01 / 02",
            "-01 / 02",
            "01 / -02",
            "15 / 16",
            "-2 / 3",
            "8 / 7",
            "5",
            "-3",
            "-3",
            "2147,483,647 / 2,147,483,648", //over largest int value
            "9,223,372,036,854,775,807 / 9,223,372,036,854,775,808" //over largest long value
        };
        final BigFraction[] fractions = {
                BigFraction.of(1, 2),
                BigFraction.of(-1, 2),
                BigFraction.of(1, -2),
                BigFraction.of(-1, -2),
                BigFraction.of(1, 2),
                BigFraction.of(1, 2),
                BigFraction.of(-1, 2),
                BigFraction.of(1, -2),
                BigFraction.of(15, 16),
                BigFraction.of(-2, 3),
                BigFraction.of(8, 7),
                BigFraction.of(5, 1),
                BigFraction.of(-3, 1),
                BigFraction.of(3, -1),
                BigFraction.of(2147483647, 2147483648L),
                BigFraction.of(new BigInteger("9223372036854775807"),
                               new BigInteger("9223372036854775808"))
        };
        int inc = 0;
        for (final BigFraction fraction: fractions) {
            // removed other assertion
            inc++;
        }

        try {
    BigFraction.parse("1 // 2");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParse_3_oe() {
        final String[] validExpressions = new String[] {
            "1 / 2",
            "-1 / 2",
            "1 / -2",
            "-1 / -2",
            "01 / 2",
            "01 / 02",
            "-01 / 02",
            "01 / -02",
            "15 / 16",
            "-2 / 3",
            "8 / 7",
            "5",
            "-3",
            "-3",
            "2147,483,647 / 2,147,483,648", //over largest int value
            "9,223,372,036,854,775,807 / 9,223,372,036,854,775,808" //over largest long value
        };
        final BigFraction[] fractions = {
                BigFraction.of(1, 2),
                BigFraction.of(-1, 2),
                BigFraction.of(1, -2),
                BigFraction.of(-1, -2),
                BigFraction.of(1, 2),
                BigFraction.of(1, 2),
                BigFraction.of(-1, 2),
                BigFraction.of(1, -2),
                BigFraction.of(15, 16),
                BigFraction.of(-2, 3),
                BigFraction.of(8, 7),
                BigFraction.of(5, 1),
                BigFraction.of(-3, 1),
                BigFraction.of(3, -1),
                BigFraction.of(2147483647, 2147483648L),
                BigFraction.of(new BigInteger("9223372036854775807"),
                               new BigInteger("9223372036854775808"))
        };
        int inc = 0;
        for (final BigFraction fraction: fractions) {
            // removed other assertion
            inc++;
        }

        // removed other assertion
        try {
    BigFraction.parse("1 / z");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParse_4_oe() {
        final String[] validExpressions = new String[] {
            "1 / 2",
            "-1 / 2",
            "1 / -2",
            "-1 / -2",
            "01 / 2",
            "01 / 02",
            "-01 / 02",
            "01 / -02",
            "15 / 16",
            "-2 / 3",
            "8 / 7",
            "5",
            "-3",
            "-3",
            "2147,483,647 / 2,147,483,648", //over largest int value
            "9,223,372,036,854,775,807 / 9,223,372,036,854,775,808" //over largest long value
        };
        final BigFraction[] fractions = {
                BigFraction.of(1, 2),
                BigFraction.of(-1, 2),
                BigFraction.of(1, -2),
                BigFraction.of(-1, -2),
                BigFraction.of(1, 2),
                BigFraction.of(1, 2),
                BigFraction.of(-1, 2),
                BigFraction.of(1, -2),
                BigFraction.of(15, 16),
                BigFraction.of(-2, 3),
                BigFraction.of(8, 7),
                BigFraction.of(5, 1),
                BigFraction.of(-3, 1),
                BigFraction.of(3, -1),
                BigFraction.of(2147483647, 2147483648L),
                BigFraction.of(new BigInteger("9223372036854775807"),
                               new BigInteger("9223372036854775808"))
        };
        int inc = 0;
        for (final BigFraction fraction: fractions) {
            // removed other assertion
            inc++;
        }

        // removed other assertion
        // removed other assertion
        try {
    BigFraction.parse("1 / --2");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParse_5_oe() {
        final String[] validExpressions = new String[] {
            "1 / 2",
            "-1 / 2",
            "1 / -2",
            "-1 / -2",
            "01 / 2",
            "01 / 02",
            "-01 / 02",
            "01 / -02",
            "15 / 16",
            "-2 / 3",
            "8 / 7",
            "5",
            "-3",
            "-3",
            "2147,483,647 / 2,147,483,648", //over largest int value
            "9,223,372,036,854,775,807 / 9,223,372,036,854,775,808" //over largest long value
        };
        final BigFraction[] fractions = {
                BigFraction.of(1, 2),
                BigFraction.of(-1, 2),
                BigFraction.of(1, -2),
                BigFraction.of(-1, -2),
                BigFraction.of(1, 2),
                BigFraction.of(1, 2),
                BigFraction.of(-1, 2),
                BigFraction.of(1, -2),
                BigFraction.of(15, 16),
                BigFraction.of(-2, 3),
                BigFraction.of(8, 7),
                BigFraction.of(5, 1),
                BigFraction.of(-3, 1),
                BigFraction.of(3, -1),
                BigFraction.of(2147483647, 2147483648L),
                BigFraction.of(new BigInteger("9223372036854775807"),
                               new BigInteger("9223372036854775808"))
        };
        int inc = 0;
        for (final BigFraction fraction: fractions) {
            // removed other assertion
            inc++;
        }

        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    BigFraction.parse("x");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testMath340_1_oe() {
        final BigFraction fractionA = BigFraction.from(0.00131);
        final BigFraction fractionB = BigFraction.from(.37).reciprocal();
        final BigFraction errorResult = fractionA.multiply(fractionB);
        final BigFraction correctResult = BigFraction.of(fractionA.getNumerator().multiply(fractionB.getNumerator()),
                                                   fractionA.getDenominator().multiply(fractionB.getDenominator()));
        Assertions.assertEquals(correctResult, errorResult);
    }

    @Test
    void testNumbers150_1_oe() {
        // zero to negative powers should throw an exception
        try {
    BigFraction.ZERO.pow(-1);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testNumbers150_2_oe() {
        // zero to negative powers should throw an exception
        // removed other assertion
        try {
    BigFraction.ZERO.pow(Integer.MIN_VALUE);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testNumbers150_3_oe() {
        // zero to negative powers should throw an exception
        // removed other assertion
        // removed other assertion

        // shall overflow
        final BigFraction f2 = BigFraction.of(2);
        try {
    f2.pow(Integer.MIN_VALUE);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testNumbers150_4_oe() {
        // zero to negative powers should throw an exception
        // removed other assertion
        // removed other assertion

        // shall overflow
        final BigFraction f2 = BigFraction.of(2);
        // removed other assertion
        final BigFraction f12 = BigFraction.of(1, 2);
        try {
    f12.pow(Integer.MIN_VALUE);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testConstructor_1_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = BigFraction.of(testCase.operandNumerator, testCase.operandDenominator);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testConstructor_1_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = BigFraction.of(testCase.operandNumerator, testCase.operandDenominator);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testConstructor_2_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Long/BigInteger arguments
                final long expectedNumerator0 = 0;
        final long expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.of(0L, 2L);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testConstructor_2_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Long/BigInteger arguments
                final long expectedNumerator0 = 0;
        final long expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.of(0L, 2L);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testConstructor_3_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Long/BigInteger arguments
        // removed other assertion
                final long expectedNumerator0 = 1L;
        final long expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.of(1L);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testConstructor_3_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Long/BigInteger arguments
        // removed other assertion
                final long expectedNumerator0 = 1L;
        final long expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.of(1L);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testConstructor_4_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Long/BigInteger arguments
        // removed other assertion
        // removed other assertion
                final long expectedNumerator0 = 11;
        final long expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.of(11L);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testConstructor_4_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Long/BigInteger arguments
        // removed other assertion
        // removed other assertion
                final long expectedNumerator0 = 11;
        final long expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.of(11L);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testConstructor_5_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Long/BigInteger arguments
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final long expectedNumerator0 = 11;
        final long expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.of(new BigInteger("11"));
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testConstructor_5_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Long/BigInteger arguments
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final long expectedNumerator0 = 11;
        final long expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.of(new BigInteger("11"));
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testDoubleConstructor_1_oe_1_oe() throws Exception {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = BigFraction.from(testCase.operand, 1.0e-5, 100);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testDoubleConstructor_1_oe_2_oe() throws Exception {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = BigFraction.from(testCase.operand, 1.0e-5, 100);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_1_oe_1_oe() throws Exception {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = BigFraction.from(testCase.operand, testCase.maxDenominator);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_1_oe_2_oe() throws Exception {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = BigFraction.from(testCase.operand, testCase.maxDenominator);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_2_oe_2_oe() throws Exception {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from Fraction
        final long pow31 = 1L << 31;
                final long expectedNumerator0 = pow31;
        final long expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.from(Integer.MIN_VALUE * -1.0, 2);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_3_oe_2_oe() throws Exception {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from Fraction
        final long pow31 = 1L << 31;
        // removed other assertion
                final long expectedNumerator0 = pow31;
        final long expectedDenominator0 = 3;
        final BigFraction actual0 = BigFraction.from(Integer.MIN_VALUE / -3.0, 10);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_4_oe_1_oe() throws Exception {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from Fraction
        final long pow31 = 1L << 31;
        // removed other assertion
        // removed other assertion
                final long expectedNumerator0 = -1;
        final long expectedDenominator0 = pow31;
        final BigFraction actual0 = BigFraction.from(1.0 / Integer.MIN_VALUE, Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_5_oe_1_oe() throws Exception {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from Fraction
        final long pow31 = 1L << 31;
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final long expectedNumerator0 = 1;
        final long expectedDenominator0 = pow31;
        final BigFraction actual0 = BigFraction.from(-1.0 / Integer.MIN_VALUE, Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testDoubleConstructorThrows_7_oe_1_oe() {
        final double eps = 1e-5;
        final int maxIterations = Integer.MAX_VALUE;
        final int maxDenominator = Integer.MAX_VALUE;
        for (final double value : new double[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Test a zero epsilon is allowed
                final long expectedNumerator0 = 1;
        final long expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.from(1.0, 0, maxIterations);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testDoubleConstructorThrows_7_oe_2_oe() {
        final double eps = 1e-5;
        final int maxIterations = Integer.MAX_VALUE;
        final int maxDenominator = Integer.MAX_VALUE;
        for (final double value : new double[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Test a zero epsilon is allowed
                final long expectedNumerator0 = 1;
        final long expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.from(1.0, 0, maxIterations);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testDoubleConstructorOverflow_1_oe_1_oe() {
                final double a0 = 0.75000000001455192;
        try {
    BigFraction.from(a0, 1.0e-12, 1000);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorOverflow_2_oe_1_oe() {
        // removed other assertion
                final double a0 = 1.0e10;
        try {
    BigFraction.from(a0, 1.0e-12, 1000);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorOverflow_3_oe_1_oe() {
        // removed other assertion
        // removed other assertion
                final double a0 = -1.0e10;
        try {
    BigFraction.from(a0, 1.0e-12, 1000);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorOverflow_4_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double a0 = -43979.60679604749;
        try {
    BigFraction.from(a0, 1.0e-12, 1000);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_1_oe_1_oe() throws Exception {
                final long expectedNumerator0 = 2;
        final long expectedDenominator0 = 5;
        final BigFraction actual0 = BigFraction.from(0.4, 1.0e-5, 100);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_1_oe_2_oe() throws Exception {
                final long expectedNumerator0 = 2;
        final long expectedDenominator0 = 5;
        final BigFraction actual0 = BigFraction.from(0.4, 1.0e-5, 100);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_3_oe_1_oe() throws Exception {
        // removed other assertion

        // removed other assertion
                final long expectedNumerator0 = 8;
        final long expectedDenominator0 = 13;
        final BigFraction actual0 = BigFraction.from(0.6152, 1.0e-3, 100);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_3_oe_2_oe() throws Exception {
        // removed other assertion

        // removed other assertion
                final long expectedNumerator0 = 8;
        final long expectedDenominator0 = 13;
        final BigFraction actual0 = BigFraction.from(0.6152, 1.0e-3, 100);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_4_oe_1_oe() throws Exception {
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final long expectedNumerator0 = 251;
        final long expectedDenominator0 = 408;
        final BigFraction actual0 = BigFraction.from(0.6152, 1.0e-4, 100);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_4_oe_2_oe() throws Exception {
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final long expectedNumerator0 = 251;
        final long expectedDenominator0 = 408;
        final BigFraction actual0 = BigFraction.from(0.6152, 1.0e-4, 100);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_5_oe_1_oe() throws Exception {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final long expectedNumerator0 = 251;
        final long expectedDenominator0 = 408;
        final BigFraction actual0 = BigFraction.from(0.6152, 1.0e-5, 100);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_5_oe_2_oe() throws Exception {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final long expectedNumerator0 = 251;
        final long expectedDenominator0 = 408;
        final BigFraction actual0 = BigFraction.from(0.6152, 1.0e-5, 100);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_6_oe_1_oe() throws Exception {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final long expectedNumerator0 = 510;
        final long expectedDenominator0 = 829;
        final BigFraction actual0 = BigFraction.from(0.6152, 1.0e-6, 100);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_6_oe_2_oe() throws Exception {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final long expectedNumerator0 = 510;
        final long expectedDenominator0 = 829;
        final BigFraction actual0 = BigFraction.from(0.6152, 1.0e-6, 100);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_7_oe_1_oe() throws Exception {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final long expectedNumerator0 = 769;
        final long expectedDenominator0 = 1250;
        final BigFraction actual0 = BigFraction.from(0.6152, 1.0e-7, 100);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_7_oe_2_oe() throws Exception {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final long expectedNumerator0 = 769;
        final long expectedDenominator0 = 1250;
        final BigFraction actual0 = BigFraction.from(0.6152, 1.0e-7, 100);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testDoubleValueForSubnormalNumbers_2_oe_1_oe() {
        // removed other assertion

                final double expected0 = Double.MIN_VALUE;
        final BigInteger numerator0 = BigInteger.ONE;
        final BigInteger denominator0 = BigInteger.ONE.shiftLeft(1074);
        final BigFraction f0 = BigFraction.of(numerator0, denominator0);
                Assertions.assertEquals(expected0, f0.doubleValue());
    }

    @Test
    void testDoubleValueForSubnormalNumbers_3_oe_1_oe() {
        // removed other assertion

        // removed other assertion
                final double expected0 = Double.MIN_VALUE * 2;
        final BigInteger numerator0 = BigInteger.valueOf(2);
        final BigInteger denominator0 = BigInteger.ONE.shiftLeft(1074);
        final BigFraction f0 = BigFraction.of(numerator0, denominator0);
                Assertions.assertEquals(expected0, f0.doubleValue());
    }

    @Test
    void testDoubleValueForSubnormalNumbers_4_oe_1_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final double expected0 = Double.MIN_VALUE * 3;
        final BigInteger numerator0 = BigInteger.valueOf(3);
        final BigInteger denominator0 = BigInteger.ONE.shiftLeft(1074);
        final BigFraction f0 = BigFraction.of(numerator0, denominator0);
                Assertions.assertEquals(expected0, f0.doubleValue());
    }

    @Test
    void testDoubleValueForSubnormalNumbers_5_oe_1_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

                final double expected0 = Double.MIN_NORMAL - Double.MIN_VALUE;
        final BigInteger numerator0 = BigInteger.ONE.shiftLeft(52).subtract(BigInteger.ONE);
        final BigInteger denominator0 = BigInteger.ONE.shiftLeft(1074);
        final BigFraction f0 = BigFraction.of(numerator0, denominator0);
                Assertions.assertEquals(expected0, f0.doubleValue());
    }

    @Test
    void testDoubleValueForSubnormalNumbers_6_oe_1_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final double expected0 = Double.MIN_NORMAL - 2 * Double.MIN_VALUE;
        final BigInteger numerator0 = BigInteger.ONE.shiftLeft(52).subtract(BigInteger.valueOf(2));
        final BigInteger denominator0 = BigInteger.ONE.shiftLeft(1074);
        final BigFraction f0 = BigFraction.of(numerator0, denominator0);
                Assertions.assertEquals(expected0, f0.doubleValue());
    }

    @Test
    void testDoubleValueForSubnormalNumbers_7_oe_1_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        //this number is smaller than Double.MIN_NORMAL, but should round up to it
                final double expected0 = Double.MIN_NORMAL;
        final BigInteger numerator0 = BigInteger.ONE.shiftLeft(53).subtract(BigInteger.ONE);
        final BigInteger denominator0 = BigInteger.ONE.shiftLeft(1075);
        final BigFraction f0 = BigFraction.of(numerator0, denominator0);
                Assertions.assertEquals(expected0, f0.doubleValue());
    }

    @Test
    void testDoubleValueForInfinities_1_oe_1_oe() {
        //the smallest integer that rounds up to Double.POSITIVE_INFINITY
        final BigInteger minInf = BigInteger.ONE
                .shiftLeft(1024)
                .subtract(BigInteger.ONE.shiftLeft(970));

                final double expected0 = Double.NEGATIVE_INFINITY;
        final BigInteger numerator0 = minInf.negate();
        final BigInteger denominator0 = BigInteger.ONE;
        final BigFraction f0 = BigFraction.of(numerator0, denominator0);
                Assertions.assertEquals(expected0, f0.doubleValue());
    }

    @Test
    void testDoubleValueForInfinities_2_oe_1_oe() {
        //the smallest integer that rounds up to Double.POSITIVE_INFINITY
        final BigInteger minInf = BigInteger.ONE
                .shiftLeft(1024)
                .subtract(BigInteger.ONE.shiftLeft(970));

        // removed other assertion
                final double expected0 = Double.POSITIVE_INFINITY;
        final BigInteger numerator0 = minInf;
        final BigInteger denominator0 = BigInteger.ONE;
        final BigFraction f0 = BigFraction.of(numerator0, denominator0);
                Assertions.assertEquals(expected0, f0.doubleValue());
    }

    @Test
    void testDoubleValueForLargeNumeratorAndSmallDenominator_2_oe_1_oe() {
        // NUMBERS-15
        final BigInteger pow300 = BigInteger.TEN.pow(300);
        final BigInteger pow330 = BigInteger.TEN.pow(330);
        final BigFraction large = BigFraction.of(pow330.add(BigInteger.ONE),
                                                 pow300);

        // removed other assertion

        // NUMBERS-120
                final double expected0 = 5.992310449541053E307;
        final BigInteger numerator0 = BigInteger.ONE .shiftLeft(1024) .subtract(BigInteger.ONE.shiftLeft(970)) .add(BigInteger.ONE);
        final BigInteger denominator0 = BigInteger.valueOf(3);
        final BigFraction f0 = BigFraction.of(numerator0, denominator0);
                Assertions.assertEquals(expected0, f0.doubleValue());
    }

    @Test
    void testDoubleValueForLargeNumeratorAndSmallDenominator_3_oe_1_oe() {
        // NUMBERS-15
        final BigInteger pow300 = BigInteger.TEN.pow(300);
        final BigInteger pow330 = BigInteger.TEN.pow(330);
        final BigFraction large = BigFraction.of(pow330.add(BigInteger.ONE),
                                                 pow300);

        // removed other assertion

        // NUMBERS-120
        // removed other assertion

                final double expected0 = Double.MAX_VALUE;
        final BigInteger numerator0 = BigInteger.ONE .shiftLeft(1025) .subtract(BigInteger.ONE.shiftLeft(972)) .subtract(BigInteger.ONE);
        final BigInteger denominator0 = BigInteger.valueOf(2);
        final BigFraction f0 = BigFraction.of(numerator0, denominator0);
                Assertions.assertEquals(expected0, f0.doubleValue());
    }

    @Test
    void testAbs_1_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.absTestCases()) {
            final BigFraction f = BigFraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f.abs();
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testAbs_1_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.absTestCases()) {
            final BigFraction f = BigFraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f.abs();
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testReciprocal_1_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.reciprocalTestCases()) {
            final BigFraction f = BigFraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f.reciprocal();
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testReciprocal_1_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.reciprocalTestCases()) {
            final BigFraction f = BigFraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f.reciprocal();
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testNegate_1_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final BigFraction f = BigFraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f.negate();
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testNegate_1_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final BigFraction f = BigFraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f.negate();
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testAdd_1_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.add(f2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testAdd_1_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.add(f2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testAdd_2_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.add(i2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testAdd_2_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.add(i2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testAdd_3_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.add((long) i2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testAdd_3_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.add((long) i2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testAdd_4_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.add(BigInteger.valueOf(i2));
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testAdd_4_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.add(BigInteger.valueOf(i2));
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testAdd_7_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // Special cases
        final BigFraction f2 = BigFraction.of(1, 2);
                final long expectedNumerator0 = 1;
        final long expectedDenominator0 = 2;
        final BigFraction actual0 = f2.add(BigInteger.ZERO);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testAdd_7_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // Special cases
        final BigFraction f2 = BigFraction.of(1, 2);
                final long expectedNumerator0 = 1;
        final long expectedDenominator0 = 2;
        final BigFraction actual0 = f2.add(BigInteger.ZERO);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testAdd_8_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // Special cases
        final BigFraction f2 = BigFraction.of(1, 2);
        // removed other assertion
                final long expectedNumerator0 = 12;
        final long expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.ZERO.add(BigInteger.valueOf(12));
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testAdd_8_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // Special cases
        final BigFraction f2 = BigFraction.of(1, 2);
        // removed other assertion
                final long expectedNumerator0 = 12;
        final long expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.ZERO.add(BigInteger.valueOf(12));
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testDivide_1_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.divide(f2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testDivide_1_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.divide(f2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testDivide_2_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.divide(i2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testDivide_2_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.divide(i2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testDivide_3_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.divide((long) i2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testDivide_3_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.divide((long) i2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testDivide_4_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.divide(BigInteger.valueOf(i2));
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testDivide_4_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.divide(BigInteger.valueOf(i2));
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testDivide_11_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Special cases
        final BigFraction f1 = BigFraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
                final long expectedNumerator0 = -1;
        final long expectedDenominator0 = -Integer.MAX_VALUE;
        final BigFraction actual0 = f1.divide(Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testDivide_11_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Special cases
        final BigFraction f1 = BigFraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
                final long expectedNumerator0 = -1;
        final long expectedDenominator0 = -Integer.MAX_VALUE;
        final BigFraction actual0 = f1.divide(Integer.MIN_VALUE);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testDivide_12_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Special cases
        final BigFraction f1 = BigFraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        // removed other assertion
                final long expectedNumerator0 = -1;
        final long expectedDenominator0 = -Integer.MAX_VALUE;
        final BigFraction actual0 = f1.divide((long) Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testDivide_12_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Special cases
        final BigFraction f1 = BigFraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        // removed other assertion
                final long expectedNumerator0 = -1;
        final long expectedDenominator0 = -Integer.MAX_VALUE;
        final BigFraction actual0 = f1.divide((long) Integer.MIN_VALUE);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testDivide_13_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Special cases
        final BigFraction f1 = BigFraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        // removed other assertion
        // removed other assertion
                final long expectedNumerator0 = -1;
        final long expectedDenominator0 = -Integer.MAX_VALUE;
        final BigFraction actual0 = f1.divide(BigInteger.valueOf(Integer.MIN_VALUE));
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testDivide_13_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Special cases
        final BigFraction f1 = BigFraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        // removed other assertion
        // removed other assertion
                final long expectedNumerator0 = -1;
        final long expectedDenominator0 = -Integer.MAX_VALUE;
        final BigFraction actual0 = f1.divide(BigInteger.valueOf(Integer.MIN_VALUE));
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testMultiply_1_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.multiply(f2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testMultiply_1_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.multiply(f2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testMultiply_2_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.multiply(i2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testMultiply_2_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.multiply(i2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testMultiply_3_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.multiply((long) i2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testMultiply_3_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.multiply((long) i2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testMultiply_4_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.multiply(BigInteger.valueOf(i2));
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testMultiply_4_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.multiply(BigInteger.valueOf(i2));
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testPow_1_oe_1_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.pow(exponent);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testPow_1_oe_2_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.pow(exponent);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testSubtract_1_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.subtract(f2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testSubtract_1_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.subtract(f2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testSubtract_2_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.subtract(i2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testSubtract_2_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.subtract(i2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testSubtract_3_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.subtract((long) i2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testSubtract_3_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.subtract((long) i2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testSubtract_4_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.subtract(BigInteger.valueOf(i2));
            Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }
    }

    @Test
    void testSubtract_4_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.subtract(BigInteger.valueOf(i2));
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }
    }

    @Test
    void testEqualsAndHashCode_5_oe_1_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
                final BigFraction f10 = zero;
        final BigFraction f20 = zero2;
        Assertions.assertEquals(f10, f20);
    }

    @Test
    void testEqualsAndHashCode_5_oe_2_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
                final BigFraction f10 = zero;
        final BigFraction f20 = zero2;
        // removed other assertion
                Assertions.assertEquals(f10.hashCode(), f20.hashCode(), "Equal fractions have different hashCode");
    }

    @Test
    void testEqualsAndHashCode_5_oe_3_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
                final BigFraction f10 = zero;
        final BigFraction f20 = zero2;
        // removed other assertion
                // removed other assertion
                // Check the computation matches the result of Arrays.hashCode and the signum.
                // This is not mandated but is a recommendation.
                final int expected0 = f10.signum() *
                                     Arrays.hashCode(new Object[] {f10.getNumerator().abs(),
                                                                   f10.getDenominator().abs()});
                Assertions.assertEquals(expected0, f10.hashCode(), "Hashcode not equal to using Arrays.hashCode");
    }

    @Test
    void testEqualsAndHashCode_8_oe_1_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final BigFraction one = BigFraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            BigFraction f1 = BigFraction.of(-num, den);
            BigFraction f2 = BigFraction.of(num, -den);
                        final BigFraction f10 = f1;
            final BigFraction f20 = f2;
            Assertions.assertEquals(f10, f20);
    }
    }

    @Test
    void testEqualsAndHashCode_8_oe_2_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final BigFraction one = BigFraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            BigFraction f1 = BigFraction.of(-num, den);
            BigFraction f2 = BigFraction.of(num, -den);
                        final BigFraction f10 = f1;
            final BigFraction f20 = f2;
            // removed other assertion
                    Assertions.assertEquals(f10.hashCode(), f20.hashCode(), "Equal fractions have different hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_8_oe_3_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final BigFraction one = BigFraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            BigFraction f1 = BigFraction.of(-num, den);
            BigFraction f2 = BigFraction.of(num, -den);
                        final BigFraction f10 = f1;
            final BigFraction f20 = f2;
            // removed other assertion
                    // removed other assertion
                    // Check the computation matches the result of Arrays.hashCode and the signum.
                    // This is not mandated but is a recommendation.
                    final int expected0 = f10.signum() *
                                         Arrays.hashCode(new Object[] {f10.getNumerator().abs(),
                                                                       f10.getDenominator().abs()});
                    Assertions.assertEquals(expected0, f10.hashCode(), "Hashcode not equal to using Arrays.hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_9_oe_1_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final BigFraction one = BigFraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            BigFraction f1 = BigFraction.of(-num, den);
            BigFraction f2 = BigFraction.of(num, -den);
            // removed other assertion
                        final BigFraction f10 = f2;
            final BigFraction f20 = f1;
            Assertions.assertEquals(f10, f20);
    }
    }

    @Test
    void testEqualsAndHashCode_9_oe_2_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final BigFraction one = BigFraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            BigFraction f1 = BigFraction.of(-num, den);
            BigFraction f2 = BigFraction.of(num, -den);
            // removed other assertion
                        final BigFraction f10 = f2;
            final BigFraction f20 = f1;
            // removed other assertion
                    Assertions.assertEquals(f10.hashCode(), f20.hashCode(), "Equal fractions have different hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_9_oe_3_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final BigFraction one = BigFraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            BigFraction f1 = BigFraction.of(-num, den);
            BigFraction f2 = BigFraction.of(num, -den);
            // removed other assertion
                        final BigFraction f10 = f2;
            final BigFraction f20 = f1;
            // removed other assertion
                    // removed other assertion
                    // Check the computation matches the result of Arrays.hashCode and the signum.
                    // This is not mandated but is a recommendation.
                    final int expected0 = f10.signum() *
                                         Arrays.hashCode(new Object[] {f10.getNumerator().abs(),
                                                                       f10.getDenominator().abs()});
                    Assertions.assertEquals(expected0, f10.hashCode(), "Hashcode not equal to using Arrays.hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_10_oe_1_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final BigFraction one = BigFraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            BigFraction f1 = BigFraction.of(-num, den);
            BigFraction f2 = BigFraction.of(num, -den);
            // removed other assertion
            // removed other assertion
            f1 = BigFraction.of(num, den);
            f2 = BigFraction.of(-num, -den);
                        final BigFraction f10 = f1;
            final BigFraction f20 = f2;
            Assertions.assertEquals(f10, f20);
    }
    }

    @Test
    void testEqualsAndHashCode_10_oe_2_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final BigFraction one = BigFraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            BigFraction f1 = BigFraction.of(-num, den);
            BigFraction f2 = BigFraction.of(num, -den);
            // removed other assertion
            // removed other assertion
            f1 = BigFraction.of(num, den);
            f2 = BigFraction.of(-num, -den);
                        final BigFraction f10 = f1;
            final BigFraction f20 = f2;
            // removed other assertion
                    Assertions.assertEquals(f10.hashCode(), f20.hashCode(), "Equal fractions have different hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_10_oe_3_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final BigFraction one = BigFraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            BigFraction f1 = BigFraction.of(-num, den);
            BigFraction f2 = BigFraction.of(num, -den);
            // removed other assertion
            // removed other assertion
            f1 = BigFraction.of(num, den);
            f2 = BigFraction.of(-num, -den);
                        final BigFraction f10 = f1;
            final BigFraction f20 = f2;
            // removed other assertion
                    // removed other assertion
                    // Check the computation matches the result of Arrays.hashCode and the signum.
                    // This is not mandated but is a recommendation.
                    final int expected0 = f10.signum() *
                                         Arrays.hashCode(new Object[] {f10.getNumerator().abs(),
                                                                       f10.getDenominator().abs()});
                    Assertions.assertEquals(expected0, f10.hashCode(), "Hashcode not equal to using Arrays.hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_11_oe_1_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final BigFraction one = BigFraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            BigFraction f1 = BigFraction.of(-num, den);
            BigFraction f2 = BigFraction.of(num, -den);
            // removed other assertion
            // removed other assertion
            f1 = BigFraction.of(num, den);
            f2 = BigFraction.of(-num, -den);
            // removed other assertion
                        final BigFraction f10 = f2;
            final BigFraction f20 = f1;
            Assertions.assertEquals(f10, f20);
    }
    }

    @Test
    void testEqualsAndHashCode_11_oe_2_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final BigFraction one = BigFraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            BigFraction f1 = BigFraction.of(-num, den);
            BigFraction f2 = BigFraction.of(num, -den);
            // removed other assertion
            // removed other assertion
            f1 = BigFraction.of(num, den);
            f2 = BigFraction.of(-num, -den);
            // removed other assertion
                        final BigFraction f10 = f2;
            final BigFraction f20 = f1;
            // removed other assertion
                    Assertions.assertEquals(f10.hashCode(), f20.hashCode(), "Equal fractions have different hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_11_oe_3_oe() {
        final BigFraction zero = BigFraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final BigFraction zero2 = BigFraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final BigFraction one = BigFraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            BigFraction f1 = BigFraction.of(-num, den);
            BigFraction f2 = BigFraction.of(num, -den);
            // removed other assertion
            // removed other assertion
            f1 = BigFraction.of(num, den);
            f2 = BigFraction.of(-num, -den);
            // removed other assertion
                        final BigFraction f10 = f2;
            final BigFraction f20 = f1;
            // removed other assertion
                    // removed other assertion
                    // Check the computation matches the result of Arrays.hashCode and the signum.
                    // This is not mandated but is a recommendation.
                    final int expected0 = f10.signum() *
                                         Arrays.hashCode(new Object[] {f10.getNumerator().abs(),
                                                                       f10.getDenominator().abs()});
                    Assertions.assertEquals(expected0, f10.hashCode(), "Hashcode not equal to using Arrays.hashCode");
    }
    }

    @Test
    void testConstructor_1_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = BigFraction.of(testCase.operandNumerator, testCase.operandDenominator);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testConstructor_2_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Long/BigInteger arguments
                final int expectedNumerator0 = 0;
        final int expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.of(0L, 2L);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testConstructor_4_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Long/BigInteger arguments
        // removed other assertion
        // removed other assertion
                final int expectedNumerator0 = 11;
        final int expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.of(11L);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testConstructor_5_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Long/BigInteger arguments
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator0 = 11;
        final int expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.of(new BigInteger("11"));
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructor_1_oe_3_oe() throws Exception {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = BigFraction.from(testCase.operand, 1.0e-5, 100);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_1_oe_3_oe() throws Exception {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = BigFraction.from(testCase.operand, testCase.maxDenominator);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testDoubleConstructorThrows_7_oe_3_oe() {
        final double eps = 1e-5;
        final int maxIterations = Integer.MAX_VALUE;
        final int maxDenominator = Integer.MAX_VALUE;
        for (final double value : new double[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Test a zero epsilon is allowed
                final int expectedNumerator0 = 1;
        final int expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.from(1.0, 0, maxIterations);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_1_oe_3_oe() throws Exception {
                final int expectedNumerator0 = 2;
        final int expectedDenominator0 = 5;
        final BigFraction actual0 = BigFraction.from(0.4, 1.0e-5, 100);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_2_oe_1_oe() throws Exception {
        // removed other assertion

                final int expectedNumerator0 = 3;
        final int expectedDenominator0 = 5;
        final BigFraction actual0 = BigFraction.from(0.6152, 0.02, 100);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumeratorAsInt());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_2_oe_2_oe() throws Exception {
        // removed other assertion

                final int expectedNumerator0 = 3;
        final int expectedDenominator0 = 5;
        final BigFraction actual0 = BigFraction.from(0.6152, 0.02, 100);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominatorAsInt());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_2_oe_3_oe() throws Exception {
        // removed other assertion

                final int expectedNumerator0 = 3;
        final int expectedDenominator0 = 5;
        final BigFraction actual0 = BigFraction.from(0.6152, 0.02, 100);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_3_oe_3_oe() throws Exception {
        // removed other assertion

        // removed other assertion
                final int expectedNumerator0 = 8;
        final int expectedDenominator0 = 13;
        final BigFraction actual0 = BigFraction.from(0.6152, 1.0e-3, 100);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_4_oe_3_oe() throws Exception {
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final int expectedNumerator0 = 251;
        final int expectedDenominator0 = 408;
        final BigFraction actual0 = BigFraction.from(0.6152, 1.0e-4, 100);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_5_oe_3_oe() throws Exception {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator0 = 251;
        final int expectedDenominator0 = 408;
        final BigFraction actual0 = BigFraction.from(0.6152, 1.0e-5, 100);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_6_oe_3_oe() throws Exception {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator0 = 510;
        final int expectedDenominator0 = 829;
        final BigFraction actual0 = BigFraction.from(0.6152, 1.0e-6, 100);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_7_oe_3_oe() throws Exception {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator0 = 769;
        final int expectedDenominator0 = 1250;
        final BigFraction actual0 = BigFraction.from(0.6152, 1.0e-7, 100);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testAbs_1_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.absTestCases()) {
            final BigFraction f = BigFraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f.abs();
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testReciprocal_1_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.reciprocalTestCases()) {
            final BigFraction f = BigFraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f.reciprocal();
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testNegate_1_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final BigFraction f = BigFraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f.negate();
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testAdd_1_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.add(f2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testAdd_2_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.add(i2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testAdd_3_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.add((long) i2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testAdd_4_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.add(BigInteger.valueOf(i2));
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testAdd_7_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // Special cases
        final BigFraction f2 = BigFraction.of(1, 2);
                final int expectedNumerator0 = 1;
        final int expectedDenominator0 = 2;
        final BigFraction actual0 = f2.add(BigInteger.ZERO);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testAdd_8_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // Special cases
        final BigFraction f2 = BigFraction.of(1, 2);
        // removed other assertion
                final int expectedNumerator0 = 12;
        final int expectedDenominator0 = 1;
        final BigFraction actual0 = BigFraction.ZERO.add(BigInteger.valueOf(12));
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDivide_1_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.divide(f2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testDivide_2_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.divide(i2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testDivide_3_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.divide((long) i2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testDivide_4_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.divide(BigInteger.valueOf(i2));
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testDivide_11_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Special cases
        final BigFraction f1 = BigFraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
                final int expectedNumerator0 = -1;
        final int expectedDenominator0 = -Integer.MAX_VALUE;
        final BigFraction actual0 = f1.divide(Integer.MIN_VALUE);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDivide_12_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Special cases
        final BigFraction f1 = BigFraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        // removed other assertion
                final int expectedNumerator0 = -1;
        final int expectedDenominator0 = -Integer.MAX_VALUE;
        final BigFraction actual0 = f1.divide((long) Integer.MIN_VALUE);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDivide_13_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Special cases
        final BigFraction f1 = BigFraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        // removed other assertion
        // removed other assertion
                final int expectedNumerator0 = -1;
        final int expectedDenominator0 = -Integer.MAX_VALUE;
        final BigFraction actual0 = f1.divide(BigInteger.valueOf(Integer.MIN_VALUE));
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testMultiply_1_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.multiply(f2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testMultiply_2_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.multiply(i2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testMultiply_3_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.multiply((long) i2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testMultiply_4_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.multiply(BigInteger.valueOf(i2));
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testPow_1_oe_3_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.pow(exponent);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testSubtract_1_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.subtract(f2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testSubtract_2_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.subtract(i2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testSubtract_3_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.subtract((long) i2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testSubtract_4_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final BigFraction f2 = BigFraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final BigFraction f1 = BigFraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
            // removed other assertion
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final BigFraction actual0 = f1.subtract(BigInteger.valueOf(i2));
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

}
