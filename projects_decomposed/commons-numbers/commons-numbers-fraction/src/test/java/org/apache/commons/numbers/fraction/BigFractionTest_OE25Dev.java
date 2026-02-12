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
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
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
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
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
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
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
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
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
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testDoubleConstructorGoldenRatioThrows_1_oe() {
        // the golden ratio is notoriously a difficult number for continuous fraction
        try {
    BigFraction.from((1 + Math.sqrt(5)) / 2, 1.0e-12, 25);
    org.junit.jupiter.api.Assertions.fail("FractionException");
} catch (FractionException e) {
}
    }

    @Test
    void testDoubleConstructorWithMaxDenominatorOverFlow_1_oe() {
        try {
    BigFraction.from(1e10, 1000);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorWithMaxDenominatorOverFlow_2_oe() {
        // removed other assertion
        try {
    BigFraction.from(-1e10, 1000);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testReciprocal_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.reciprocalTestCases()) {
            final BigFraction f = BigFraction.of(testCase.operandNumerator, testCase.operandDenominator);
            // removed other assertion
        }

        final BigFraction f = BigFraction.of(0, 3);
        Assertions.assertThrows(ArithmeticException.class, f::reciprocal);
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
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
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
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
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
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
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
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
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
    org.junit.jupiter.api.Assertions.fail("FractionException");
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
    org.junit.jupiter.api.Assertions.fail("FractionException");
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
    org.junit.jupiter.api.Assertions.fail("FractionException");
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
    org.junit.jupiter.api.Assertions.fail("FractionException");
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
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
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
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
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
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
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
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
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
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
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
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
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
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
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
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
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
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
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
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
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
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testNumbers150_1_oe() {
        // zero to negative powers should throw an exception
        try {
    BigFraction.ZERO.pow(-1);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testNumbers150_2_oe() {
        // zero to negative powers should throw an exception
        // removed other assertion
        try {
    BigFraction.ZERO.pow(Integer.MIN_VALUE);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
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
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
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
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorOverflow_1_oe_1_oe() {
                final double a0 = 0.75000000001455192;
        try {
    BigFraction.from(a0, 1.0e-12, 1000);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorOverflow_2_oe_1_oe() {
        // removed other assertion
                final double a0 = 1.0e10;
        try {
    BigFraction.from(a0, 1.0e-12, 1000);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
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
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
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
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

}
