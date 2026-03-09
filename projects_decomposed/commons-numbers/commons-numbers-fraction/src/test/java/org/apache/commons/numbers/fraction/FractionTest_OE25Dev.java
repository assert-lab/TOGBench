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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.numbers.core.TestUtils;
import org.apache.commons.numbers.fraction.CommonTestCases.BinaryIntOperatorTestCase;
import org.apache.commons.numbers.fraction.CommonTestCases.BinaryOperatorTestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests for {@link Fraction}.
 */
class FractionTest_OE25Dev {

    /** The zero representation with positive denominator. */
    private static final Fraction ZERO_P = Fraction.of(0, 1);
    /** The zero representation with negative denominator. */
    private static final Fraction ZERO_N = Fraction.of(0, -1);

    private static void assertFraction(int expectedNumerator, int expectedDenominator, Fraction actual) {
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
        Assertions.assertEquals(expectedDenominator, actual.getDenominator());
        Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    private static void assertDoubleValue(double expected, int numerator, int denominator) {
        final Fraction f = Fraction.of(numerator, denominator);
        Assertions.assertEquals(expected, f.doubleValue());
    }

    @Test
    void testConstructor() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            assertFraction(
                    testCase.expectedNumerator,
                    testCase.expectedDenominator,
                    Fraction.of(testCase.operandNumerator, testCase.operandDenominator)
            );
        }

        // Special cases.
        assertFraction(Integer.MIN_VALUE, -1, Fraction.of(Integer.MIN_VALUE, -1));
        assertFraction(1, Integer.MIN_VALUE, Fraction.of(1, Integer.MIN_VALUE));
        assertFraction(-1, Integer.MIN_VALUE, Fraction.of(-1, Integer.MIN_VALUE));
        assertFraction(1, 1, Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE));

        // Divide by zero
        Assertions.assertThrows(ArithmeticException.class, () -> Fraction.of(1, 0));
    }

    @Test
    void testConstructorZero() {
        Assertions.assertSame(Fraction.ZERO, Fraction.from(0.0));
        Assertions.assertSame(Fraction.ZERO, Fraction.from(0.0, 1e-10, 100));
        Assertions.assertSame(Fraction.ZERO, Fraction.from(0.0, 100));
        Assertions.assertSame(Fraction.ZERO, Fraction.of(0));
        Assertions.assertSame(Fraction.ZERO, Fraction.of(0, 1));
        Assertions.assertSame(Fraction.ZERO, Fraction.of(0, -1));
    }

    // MATH-179
    @Test
    void testDoubleConstructor() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            assertFraction(
                    testCase.expectedNumerator,
                    testCase.expectedDenominator,
                    Fraction.from(testCase.operand)
            );
        }

        // Cases with different exact results from BigFraction
        assertFraction(1, 3, Fraction.from(1.0 / 3.0));
        assertFraction(17, 100, Fraction.from(17.0 / 100.0));
        assertFraction(317, 100, Fraction.from(317.0 / 100.0));
        assertFraction(-1, 3, Fraction.from(-1.0 / 3.0));
        assertFraction(-17, 100, Fraction.from(17.0 / -100.0));
        assertFraction(-317, 100, Fraction.from(-317.0 / 100.0));
    }

    // MATH-181
    // NUMBERS-147
    @Test
    void testDoubleConstructorWithMaxDenominator() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            assertFraction(
                    testCase.expectedNumerator,
                    testCase.expectedDenominator,
                    Fraction.from(testCase.operand, testCase.maxDenominator)
            );
        }

        // Cases with different exact results from BigFraction
        assertFraction(Integer.MIN_VALUE, -1, Fraction.from(Integer.MIN_VALUE * -1.0, 2));
        assertFraction(Integer.MIN_VALUE, -3, Fraction.from(Integer.MIN_VALUE / -3.0, 10));
        assertFraction(1, Integer.MIN_VALUE, Fraction.from(1.0 / Integer.MIN_VALUE, Integer.MIN_VALUE));
        assertFraction(-1, Integer.MIN_VALUE, Fraction.from(-1.0 / Integer.MIN_VALUE, Integer.MIN_VALUE));

        Assertions.assertThrows(IllegalArgumentException.class, () -> Fraction.from(1.0, 0));
    }

    @Test
    void testDoubleConstructorThrows() {
        final double eps = 1e-5;
        final int maxIterations = Integer.MAX_VALUE;
        final int maxDenominator = Integer.MAX_VALUE;
        for (final double value : new double[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> Fraction.from(value));
            Assertions.assertThrows(IllegalArgumentException.class, () -> Fraction.from(value, eps, maxIterations));
            Assertions.assertThrows(IllegalArgumentException.class, () -> Fraction.from(value, maxDenominator));
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> Fraction.from(1.0, Double.NaN, maxIterations));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Fraction.from(1.0, -1.0, maxIterations));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Fraction.from(1.0, eps, 0));
        // Test a zero epsilon is allowed
        assertFraction(1, 1, Fraction.from(1.0, 0, maxIterations));
    }

    @Test
    void testDoubleConstructorGoldenRatioThrows() {
        // the golden ratio is notoriously a difficult number for continuous fraction
        Assertions.assertThrows(ArithmeticException.class,
            () -> Fraction.from((1 + Math.sqrt(5)) / 2, 1.0e-12, 25)
        );
    }

    // MATH-1029
    @Test
    void testDoubleConstructorWithMaxDenominatorOverFlow() {
        Assertions.assertThrows(ArithmeticException.class,
            () -> Fraction.from(1e10, 1000)
        );
        Assertions.assertThrows(ArithmeticException.class,
            () -> Fraction.from(-1e10, 1000)
        );
    }

    @Test
    void testDoubleConstructorOverflow() {
        assertDoubleConstructorOverflow(0.75000000001455192);
        assertDoubleConstructorOverflow(1.0e10);
        assertDoubleConstructorOverflow(-1.0e10);
        assertDoubleConstructorOverflow(-43979.60679604749);
    }

    private void assertDoubleConstructorOverflow(final double a) {
        Assertions.assertThrows(ArithmeticException.class,
            () -> Fraction.from(a, 1.0e-12, 1000)
        );
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit() throws Exception  {
        assertFraction(2, 5, Fraction.from(0.4, 1.0e-5, 100));

        assertFraction(3, 5,      Fraction.from(0.6152, 0.02, 100));
        assertFraction(8, 13,     Fraction.from(0.6152, 1.0e-3, 100));
        assertFraction(251, 408,  Fraction.from(0.6152, 1.0e-4, 100));
        assertFraction(251, 408,  Fraction.from(0.6152, 1.0e-5, 100));
        assertFraction(510, 829,  Fraction.from(0.6152, 1.0e-6, 100));
        assertFraction(769, 1250, Fraction.from(0.6152, 1.0e-7, 100));
    }

    @Test
    void testCompareTo() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(0, a.compareTo(a));
        Assertions.assertEquals(0, a.compareTo(c));
        Assertions.assertEquals(1, a.compareTo(b));
        Assertions.assertEquals(-1, b.compareTo(a));
        Assertions.assertEquals(-1, d.compareTo(a));
        Assertions.assertEquals(1, a.compareTo(d));
        Assertions.assertEquals(-1, e.compareTo(a));
        Assertions.assertEquals(1, a.compareTo(e));
        Assertions.assertEquals(0, d.compareTo(e));
        Assertions.assertEquals(0, a.compareTo(f));
        Assertions.assertEquals(0, f.compareTo(a));
        Assertions.assertEquals(1, f.compareTo(e));
        Assertions.assertEquals(-1, e.compareTo(f));
        Assertions.assertEquals(-1, g.compareTo(a));
        Assertions.assertEquals(-1, g.compareTo(f));
        Assertions.assertEquals(1, a.compareTo(g));
        Assertions.assertEquals(-1, d.compareTo(g));

        Assertions.assertEquals(0, Fraction.of(0, 3).compareTo(Fraction.of(0, -2)));

        // these two values are different approximations of PI
        // the first  one is approximately PI - 3.07e-18
        // the second one is approximately PI + 1.936e-17
        final Fraction pi1 = Fraction.of(1068966896, 340262731);
        final Fraction pi2 = Fraction.of(411557987, 131002976);
        Assertions.assertEquals(-1, pi1.compareTo(pi2));
        Assertions.assertEquals(1, pi2.compareTo(pi1));
        Assertions.assertEquals(0.0, pi1.doubleValue() - pi2.doubleValue(), 1.0e-20);

        Assertions.assertEquals(0, ZERO_P.compareTo(ZERO_N));
    }

    @Test
    void testDoubleValue() {
        assertDoubleValue(0.5, 1, 2);
        assertDoubleValue(-0.5, -1, 2);
        assertDoubleValue(-0.5, 1, -2);
        assertDoubleValue(0.5, -1, -2);
        assertDoubleValue(1.0 / 3.0, 1, 3);

        Assertions.assertEquals(0.0, Fraction.ZERO.doubleValue());
        Assertions.assertEquals(0.0, ZERO_P.doubleValue());
        Assertions.assertEquals(0.0, ZERO_N.doubleValue());
    }

    @Test
    void testFloatValue() {
        Assertions.assertEquals(0.5f, Fraction.of(1, 2).floatValue());
        Assertions.assertEquals(0.5f, Fraction.of(-1, -2).floatValue());
        Assertions.assertEquals(-0.5f, Fraction.of(-1, 2).floatValue());
        Assertions.assertEquals(-0.5f, Fraction.of(1, -2).floatValue());

        final float e = 1f / 3f;
        Assertions.assertEquals(e, Fraction.of(1, 3).floatValue());
        Assertions.assertEquals(e, Fraction.of(-1, -3).floatValue());
        Assertions.assertEquals(-e, Fraction.of(-1, 3).floatValue());
        Assertions.assertEquals(-e, Fraction.of(1, -3).floatValue());

        Assertions.assertEquals(0.0f, ZERO_P.floatValue());
        Assertions.assertEquals(0.0f, ZERO_N.floatValue());
    }

    @Test
    void testIntValue() {
        Assertions.assertEquals(0, Fraction.of(1, 2).intValue());
        Assertions.assertEquals(0, Fraction.of(-1, -2).intValue());
        Assertions.assertEquals(0, Fraction.of(-1, 2).intValue());
        Assertions.assertEquals(0, Fraction.of(1, -2).intValue());

        Assertions.assertEquals(1, Fraction.of(3, 2).intValue());
        Assertions.assertEquals(1, Fraction.of(-3, -2).intValue());
        Assertions.assertEquals(-1, Fraction.of(-3, 2).intValue());
        Assertions.assertEquals(-1, Fraction.of(3, -2).intValue());

        Assertions.assertEquals(0, Fraction.of(1, Integer.MIN_VALUE).intValue());
        Assertions.assertEquals(0, Fraction.of(-1, Integer.MIN_VALUE).intValue());
        Assertions.assertEquals(Integer.MIN_VALUE, Fraction.of(Integer.MIN_VALUE, 1).intValue());
        Assertions.assertEquals(Integer.MAX_VALUE, Fraction.of(Integer.MIN_VALUE, -1).intValue());

        Assertions.assertEquals(0, ZERO_P.intValue());
        Assertions.assertEquals(0, ZERO_N.intValue());
    }

    @Test
    void testLongValue() {
        Assertions.assertEquals(0L, Fraction.of(1, 2).longValue());
        Assertions.assertEquals(0L, Fraction.of(-1, -2).longValue());
        Assertions.assertEquals(0L, Fraction.of(-1, 2).longValue());
        Assertions.assertEquals(0L, Fraction.of(1, -2).longValue());

        Assertions.assertEquals(1L, Fraction.of(3, 2).longValue());
        Assertions.assertEquals(1L, Fraction.of(-3, -2).longValue());
        Assertions.assertEquals(-1L, Fraction.of(-3, 2).longValue());
        Assertions.assertEquals(-1L, Fraction.of(3, -2).longValue());

        Assertions.assertEquals(0, Fraction.of(1, Integer.MIN_VALUE).longValue());
        Assertions.assertEquals(0, Fraction.of(-1, Integer.MIN_VALUE).longValue());
        Assertions.assertEquals(Integer.MIN_VALUE, Fraction.of(Integer.MIN_VALUE, 1).longValue());
        Assertions.assertEquals(Integer.MAX_VALUE + 1L, Fraction.of(Integer.MIN_VALUE, -1).longValue());

        Assertions.assertEquals(0L, ZERO_P.longValue());
        Assertions.assertEquals(0L, ZERO_N.longValue());
    }

    @Test
    void testAbs() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.absTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
            assertFraction(testCase.expectedNumerator, testCase.expectedDenominator, f.abs());
        }
    }

    @Test
    void testReciprocal() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.reciprocalTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
            assertFraction(testCase.expectedNumerator, testCase.expectedDenominator, f.reciprocal());
        }

        final Fraction f = Fraction.of(0, 3);
        Assertions.assertThrows(ArithmeticException.class, f::reciprocal);
    }

    @Test
    void testNegate() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
            assertFraction(testCase.expectedNumerator, testCase.expectedDenominator, f.negate());
        }

        // Test special cases of negation that differ from BigFraction.
        final Fraction one = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
        assertFraction(-1, 1, one.negate());
        // Special case where the negation of the numerator is not possible.
        final Fraction minValue = Fraction.of(Integer.MIN_VALUE, 1);
        assertFraction(Integer.MIN_VALUE, -1, minValue.negate());
    }

    @Test
    void testAdd() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            assertFraction(testCase.expectedNumerator, testCase.expectedDenominator, f1.add(f2));
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            assertFraction(testCase.expectedNumerator, testCase.expectedDenominator, f1.add(i2));
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            Assertions.assertThrows(ArithmeticException.class, () -> f1.add(f2));
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            Assertions.assertThrows(ArithmeticException.class, () -> f1.add(i2));
        }

        Assertions.assertThrows(NullPointerException.class, () -> Fraction.ONE.add((Fraction) null));

        // Edge case
        assertFraction(Integer.MIN_VALUE, -1, Fraction.ZERO.add(Fraction.of(Integer.MIN_VALUE, -1)));
        assertFraction(Integer.MIN_VALUE, 1, Fraction.ZERO.add(Fraction.of(Integer.MIN_VALUE, 1)));
        assertFraction(Integer.MIN_VALUE, 1, Fraction.ZERO.add(Integer.MIN_VALUE));
    }

    @Test
    void testDivide() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            assertFraction(testCase.expectedNumerator, testCase.expectedDenominator, f1.divide(f2));
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            assertFraction(testCase.expectedNumerator, testCase.expectedDenominator, f1.divide(i2));
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : divideByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            Assertions.assertThrows(ArithmeticException.class, () -> f1.divide(f2));
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : divideByIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            Assertions.assertThrows(ArithmeticException.class, () -> f1.divide(i2));
        }

        Assertions.assertThrows(NullPointerException.class, () -> Fraction.ONE.divide((Fraction) null));

        Assertions.assertThrows(FractionException.class, () -> Fraction.of(1, 2).divide(Fraction.ZERO));
        Assertions.assertThrows(FractionException.class, () -> Fraction.of(1, 2).divide(0));
    }

    @Test
    void testMultiply() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            assertFraction(testCase.expectedNumerator, testCase.expectedDenominator, f1.multiply(f2));
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            assertFraction(testCase.expectedNumerator, testCase.expectedDenominator, f1.multiply(i2));
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : multiplyByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            Assertions.assertThrows(ArithmeticException.class, () -> f1.multiply(f2));
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : multiplyByIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            Assertions.assertThrows(ArithmeticException.class, () -> f1.multiply(i2));
        }

        Assertions.assertThrows(NullPointerException.class, () -> Fraction.ONE.multiply((Fraction) null));
    }

    @Test
    void testPow() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
            assertFraction(testCase.expectedNumerator, testCase.expectedDenominator, f1.pow(exponent));
        }

        Assertions.assertThrows(ArithmeticException.class, () -> Fraction.of(Integer.MAX_VALUE).pow(2));
        Assertions.assertThrows(ArithmeticException.class, () -> Fraction.of(1, Integer.MAX_VALUE).pow(2));
        Assertions.assertThrows(ArithmeticException.class, () -> Fraction.of(Integer.MAX_VALUE).pow(-2));
        Assertions.assertThrows(ArithmeticException.class, () -> Fraction.of(1, Integer.MAX_VALUE).pow(-2));
    }

    @Test
    void testSubtract() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            assertFraction(testCase.expectedNumerator, testCase.expectedDenominator, f1.subtract(f2));
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            assertFraction(testCase.expectedNumerator, testCase.expectedDenominator, f1.subtract(i2));
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            Assertions.assertThrows(ArithmeticException.class, () -> f1.subtract(f2));
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            Assertions.assertThrows(ArithmeticException.class, () -> f1.subtract(i2));
        }

        Assertions.assertThrows(NullPointerException.class, () -> Fraction.ONE.add((Fraction) null));

        // Edge case
        assertFraction(Integer.MIN_VALUE, 1, Fraction.ZERO.subtract(Fraction.of(Integer.MIN_VALUE, -1)));
        assertFraction(Integer.MIN_VALUE, -1, Fraction.ZERO.subtract(Fraction.of(Integer.MIN_VALUE, 1)));
        assertFraction(Integer.MIN_VALUE, -1, Fraction.ZERO.subtract(Integer.MIN_VALUE));
    }

    @Test
    void testEqualsAndHashCode() {
        final Fraction zero = Fraction.of(0, 1);
        Assertions.assertEquals(zero, zero);
        Assertions.assertNotEquals(zero, null);
        Assertions.assertNotEquals(zero, new Object());
        Assertions.assertNotEquals(zero, Double.valueOf(0));

        // Equal to same rational number
        final Fraction zero2 = Fraction.of(0, 2);
        assertEqualAndHashCodeEqual(zero, zero2);

        // Not equal to different rational number
        final Fraction one = Fraction.of(1, 1);
        Assertions.assertNotEquals(zero, one);
        Assertions.assertNotEquals(one, zero);

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            assertEqualAndHashCodeEqual(f1, f2);
            assertEqualAndHashCodeEqual(f2, f1);
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
            assertEqualAndHashCodeEqual(f1, f2);
            assertEqualAndHashCodeEqual(f2, f1);
        }

        // Same numerator or denominator as 1/1
        final Fraction half = Fraction.of(1, 2);
        final Fraction two = Fraction.of(2, 1);
        Assertions.assertNotEquals(one, half);
        Assertions.assertNotEquals(one, two);

        // Check worst case fractions which will have a component using MIN_VALUE.
        // Note: abs(MIN_VALUE) is negative but this should not effect the equals result.
        final Fraction almostOne = Fraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        final Fraction almostOne2 = Fraction.of(Integer.MIN_VALUE, -Integer.MAX_VALUE);
        Assertions.assertEquals(almostOne, almostOne);
        Assertions.assertNotEquals(almostOne, almostOne2);
        final Fraction almostZero = Fraction.of(-1, Integer.MIN_VALUE);
        final Fraction almostZero2 = Fraction.of(1, Integer.MIN_VALUE);
        Assertions.assertEquals(almostZero, almostZero);
        Assertions.assertNotEquals(almostZero, almostZero2);
    }

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
    private static void assertEqualAndHashCodeEqual(Fraction f1, Fraction f2) {
        Assertions.assertEquals(f1, f2);
        Assertions.assertEquals(f1.hashCode(), f2.hashCode(), "Equal fractions have different hashCode");
        // Check the computation matches the result of Arrays.hashCode and the signum.
        // This is not mandated but is a recommendation.
        final int expected = f1.signum() *
                             Arrays.hashCode(new int[] {Math.abs(f1.getNumerator()),
                                                        Math.abs(f1.getDenominator())});
        Assertions.assertEquals(expected, f1.hashCode(), "Hashcode not equal to using Arrays.hashCode");
    }

    @Test
    void testAdditiveNeutral() {
        Assertions.assertEquals(Fraction.ZERO, Fraction.ONE.zero());
    }

    @Test
    void testMultiplicativeNeutral() {
        Assertions.assertEquals(Fraction.ONE, Fraction.ZERO.one());
    }

    @Test
    void testSerial() {
        final Fraction[] fractions = {
            Fraction.of(3, 4), Fraction.ONE, Fraction.ZERO,
            Fraction.of(17), Fraction.from(Math.PI, 1000),
            Fraction.of(-5, 2)
        };
        for (final Fraction fraction : fractions) {
            Assertions.assertEquals(fraction,TestUtils.serializeAndRecover(fraction));
        }
    }

    @Test
    void testToString() {
        Assertions.assertEquals("0", Fraction.of(0, 3).toString());
        Assertions.assertEquals("0", Fraction.of(0, -3).toString());
        Assertions.assertEquals("3", Fraction.of(6, 2).toString());
        Assertions.assertEquals("2 / 3", Fraction.of(18, 27).toString());
        Assertions.assertEquals("-10 / 11", Fraction.of(-10, 11).toString());
        Assertions.assertEquals("10 / -11", Fraction.of(10, -11).toString());
    }

    @Test
    void testParse() {
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
            "-3"
        };
        final Fraction[] fractions = {
            Fraction.of(1, 2),
            Fraction.of(-1, 2),
            Fraction.of(1, -2),
            Fraction.of(-1, -2),
            Fraction.of(1, 2),
            Fraction.of(1, 2),
            Fraction.of(-1, 2),
            Fraction.of(1, -2),
            Fraction.of(15, 16),
            Fraction.of(-2, 3),
            Fraction.of(8, 7),
            Fraction.of(5, 1),
            Fraction.of(-3, 1),
            Fraction.of(3, -1),
        };
        int inc = 0;
        for (final Fraction fraction : fractions) {
            Assertions.assertEquals(fraction,Fraction.parse(validExpressions[inc]));
            inc++;
        }

        Assertions.assertThrows(NumberFormatException.class, () -> Fraction.parse("1 // 2"));
        Assertions.assertThrows(NumberFormatException.class, () -> Fraction.parse("1 / z"));
        Assertions.assertThrows(NumberFormatException.class, () -> Fraction.parse("1 / --2"));
        Assertions.assertThrows(NumberFormatException.class, () -> Fraction.parse("x"));
    }

    @Test
    void testMath1261() {
        final Fraction a = Fraction.of(Integer.MAX_VALUE, 2);
        assertFraction(Integer.MAX_VALUE, 1, a.multiply(2));

        final Fraction b = Fraction.of(2, Integer.MAX_VALUE);
        assertFraction(1, Integer.MAX_VALUE, b.divide(2));
    }

    @Test
    void testNumbers150() {
        // zero to negative powers should throw an exception
        Assertions.assertThrows(ArithmeticException.class, () -> Fraction.ZERO.pow(-1));
        Assertions.assertThrows(ArithmeticException.class, () -> Fraction.ZERO.pow(Integer.MIN_VALUE));

        // shall overflow
        final Fraction f2 = Fraction.of(2);
        Assertions.assertThrows(ArithmeticException.class, () -> f2.pow(Integer.MIN_VALUE));
        final Fraction f12 = Fraction.of(1, 2);
        Assertions.assertThrows(ArithmeticException.class, () -> f12.pow(Integer.MIN_VALUE));
    }

    /**
     * Defines test cases that cause overflow in {@link Fraction#add(Fraction)}.
     * @return a list of test cases
     */
    private static List<BinaryOperatorTestCase> addFractionOverflowTestCases() {
        final List<BinaryOperatorTestCase> testCases = new ArrayList<>();
        testCases.add(new BinaryOperatorTestCase(1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE - 1, 0, 0));
        testCases.add(new BinaryOperatorTestCase(Integer.MIN_VALUE, 5, -1, 5, 0, 0));
        testCases.add(new BinaryOperatorTestCase(Integer.MIN_VALUE, 1, -1, 1, 0, 0));
        testCases.add(new BinaryOperatorTestCase(Integer.MAX_VALUE, 1, 1, 1, 0, 0));
        testCases.add(new BinaryOperatorTestCase(3, 327680, 2, 59049, 0, 0));
        testCases.add(new BinaryOperatorTestCase(1, 2, Integer.MIN_VALUE, -2, 0, 0));
        return testCases;
    }

    /**
     * Defines test cases that cause overflow in {@link Fraction#add(int)}.
     * @return a list of test cases
     */
    private static List<BinaryIntOperatorTestCase> addIntOverflowTestCases() {
        final List<BinaryIntOperatorTestCase> testCases = new ArrayList<>();
        testCases.add(new BinaryIntOperatorTestCase(Integer.MIN_VALUE, 1, -1, 0, 0));
        testCases.add(new BinaryIntOperatorTestCase(Integer.MAX_VALUE, 1, 1, 0, 0));
        testCases.add(new BinaryIntOperatorTestCase(1, 2, Integer.MIN_VALUE / -2, 0, 0));
        return testCases;
    }

    /**
     * Defines test cases that cause overflow in {@link Fraction#divide(Fraction)}.
     * @return a list of test cases
     */
    private static List<BinaryOperatorTestCase> divideByFractionOverflowTestCases() {
        final List<BinaryOperatorTestCase> testCases = new ArrayList<>();
        testCases.add(new BinaryOperatorTestCase(1, Integer.MAX_VALUE, 2, 1, 0, 0));
        testCases.add(new BinaryOperatorTestCase(1, Integer.MAX_VALUE, -2, 1, 0, 0));
        testCases.add(new BinaryOperatorTestCase(1, Integer.MIN_VALUE, 2, 1, 0, 0));
        testCases.add(new BinaryOperatorTestCase(1, Integer.MIN_VALUE, -2, 1, 0, 0));
        return testCases;
    }

    /**
     * Defines test cases that cause overflow in {@link Fraction#divide(int)}.
     * @return a list of test cases
     */
    private static List<BinaryIntOperatorTestCase> divideByIntOverflowTestCases() {
        final List<BinaryIntOperatorTestCase> testCases = new ArrayList<>();
        testCases.add(new BinaryIntOperatorTestCase(1, Integer.MAX_VALUE, 2, 0, 0));
        testCases.add(new BinaryIntOperatorTestCase(1, Integer.MAX_VALUE, -2, 0, 0));
        testCases.add(new BinaryIntOperatorTestCase(1, Integer.MIN_VALUE, 2, 0, 0));
        testCases.add(new BinaryIntOperatorTestCase(1, Integer.MIN_VALUE, -2, 0, 0));
        return testCases;
    }

    /**
     * Defines test cases that cause overflow in {@link Fraction#multiply(Fraction)}.
     * @return a list of test cases
     */
    private static List<BinaryOperatorTestCase> multiplyByFractionOverflowTestCases() {
        final List<BinaryOperatorTestCase> testCases = new ArrayList<>();
        testCases.add(new BinaryOperatorTestCase(Integer.MAX_VALUE, 1, 2, 1, 0, 0));
        testCases.add(new BinaryOperatorTestCase(Integer.MAX_VALUE, 1, -2, 1, 0, 0));
        testCases.add(new BinaryOperatorTestCase(Integer.MIN_VALUE, 1, 2, 1, 0, 0));
        testCases.add(new BinaryOperatorTestCase(Integer.MIN_VALUE, 1, -2, 1, 0, 0));
        return testCases;
    }

    /**
     * Defines test cases that cause overflow in {@link Fraction#multiply(int)}.
     * @return a list of test cases
     */
    private static List<BinaryIntOperatorTestCase> multiplyByIntOverflowTestCases() {
        final List<BinaryIntOperatorTestCase> testCases = new ArrayList<>();
        testCases.add(new BinaryIntOperatorTestCase(Integer.MAX_VALUE, 1, 2, 0, 0));
        testCases.add(new BinaryIntOperatorTestCase(Integer.MAX_VALUE, 1, -2, 0, 0));
        testCases.add(new BinaryIntOperatorTestCase(Integer.MIN_VALUE, 1, 2, 0, 0));
        testCases.add(new BinaryIntOperatorTestCase(Integer.MIN_VALUE, 1, -2, 0, 0));
        return testCases;
    }

    /**
     * Defines test cases that cause overflow in {@link Fraction#subtract(Fraction)}.
     * @return a list of test cases
     */
    private static List<BinaryOperatorTestCase> subtractFractionOverflowTestCases() {
        final List<BinaryOperatorTestCase> testCases = new ArrayList<>();
        testCases.add(new BinaryOperatorTestCase(1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE - 1, 0, 0));
        testCases.add(new BinaryOperatorTestCase(Integer.MIN_VALUE, 5, 1, 5, 0, 0));
        testCases.add(new BinaryOperatorTestCase(Integer.MIN_VALUE, 1, 1, 1, 0, 0));
        testCases.add(new BinaryOperatorTestCase(Integer.MAX_VALUE, 1, -1, 1, 0, 0));
        testCases.add(new BinaryOperatorTestCase(3, 327680, 2, 59049, 0, 0));
        testCases.add(new BinaryOperatorTestCase(1, 2, Integer.MIN_VALUE, 2, 0, 0));
        return testCases;
    }

    /**
     * Defines test cases that cause overflow in {@link Fraction#subtract(int)}.
     * @return a list of test cases
     */
    private static List<BinaryIntOperatorTestCase> subtractIntOverflowTestCases() {
        final List<BinaryIntOperatorTestCase> testCases = new ArrayList<>();
        testCases.add(new BinaryIntOperatorTestCase(Integer.MIN_VALUE, 1, 1, 0, 0));
        testCases.add(new BinaryIntOperatorTestCase(Integer.MAX_VALUE, 1, -1, 0, 0));
        testCases.add(new BinaryIntOperatorTestCase(1, 2, Integer.MIN_VALUE / 2, 0, 0));
        return testCases;
    }

    @Test
    void testConstructor_6_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
        }


        try {
    Fraction.of(1, 0);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testConstructorZero_1_oe() {
        Assertions.assertSame(Fraction.ZERO, Fraction.from(0.0));
    }

    @Test
    void testConstructorZero_2_oe() {
        Assertions.assertSame(Fraction.ZERO, Fraction.from(0.0, 1e-10, 100));
    }

    @Test
    void testConstructorZero_3_oe() {
        Assertions.assertSame(Fraction.ZERO, Fraction.from(0.0, 100));
    }

    @Test
    void testConstructorZero_4_oe() {
        Assertions.assertSame(Fraction.ZERO, Fraction.of(0));
    }

    @Test
    void testConstructorZero_5_oe() {
        Assertions.assertSame(Fraction.ZERO, Fraction.of(0, 1));
    }

    @Test
    void testConstructorZero_6_oe() {
        Assertions.assertSame(Fraction.ZERO, Fraction.of(0, -1));
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_6_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
        }


        try {
    Fraction.from(1.0, 0);
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
    Fraction.from(value);
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
            try {
    Fraction.from(value, eps, maxIterations);
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
            try {
    Fraction.from(value, maxDenominator);
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
        }
        try {
    Fraction.from(1.0, Double.NaN, maxIterations);
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
        }
        try {
    Fraction.from(1.0, -1.0, maxIterations);
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
        }
        try {
    Fraction.from(1.0, eps, 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testDoubleConstructorGoldenRatioThrows_1_oe() {
        try {
    Fraction.from((1 + Math.sqrt(5)) / 2, 1.0e-12, 25);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorWithMaxDenominatorOverFlow_1_oe() {
        try {
    Fraction.from(1e10, 1000);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorWithMaxDenominatorOverFlow_2_oe() {
        try {
    Fraction.from(-1e10, 1000);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testCompareTo_1_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(0, a.compareTo(a));
    }

    @Test
    void testCompareTo_2_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(0, a.compareTo(c));
    }

    @Test
    void testCompareTo_3_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(1, a.compareTo(b));
    }

    @Test
    void testCompareTo_4_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(-1, b.compareTo(a));
    }

    @Test
    void testCompareTo_5_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(-1, d.compareTo(a));
    }

    @Test
    void testCompareTo_6_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(1, a.compareTo(d));
    }

    @Test
    void testCompareTo_7_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(-1, e.compareTo(a));
    }

    @Test
    void testCompareTo_8_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(1, a.compareTo(e));
    }

    @Test
    void testCompareTo_9_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(0, d.compareTo(e));
    }

    @Test
    void testCompareTo_10_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(0, a.compareTo(f));
    }

    @Test
    void testCompareTo_11_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(0, f.compareTo(a));
    }

    @Test
    void testCompareTo_12_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(1, f.compareTo(e));
    }

    @Test
    void testCompareTo_13_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(-1, e.compareTo(f));
    }

    @Test
    void testCompareTo_14_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(-1, g.compareTo(a));
    }

    @Test
    void testCompareTo_15_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(-1, g.compareTo(f));
    }

    @Test
    void testCompareTo_16_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(1, a.compareTo(g));
    }

    @Test
    void testCompareTo_17_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);

        Assertions.assertEquals(-1, d.compareTo(g));
    }

    @Test
    void testCompareTo_18_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);


        Assertions.assertEquals(0, Fraction.of(0, 3).compareTo(Fraction.of(0, -2)));
    }

    @Test
    void testCompareTo_19_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);



        final Fraction pi1 = Fraction.of(1068966896, 340262731);
        final Fraction pi2 = Fraction.of(411557987, 131002976);
        Assertions.assertEquals(-1, pi1.compareTo(pi2));
    }

    @Test
    void testCompareTo_20_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);



        final Fraction pi1 = Fraction.of(1068966896, 340262731);
        final Fraction pi2 = Fraction.of(411557987, 131002976);
        Assertions.assertEquals(1, pi2.compareTo(pi1));
    }

    @Test
    void testCompareTo_21_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);



        final Fraction pi1 = Fraction.of(1068966896, 340262731);
        final Fraction pi2 = Fraction.of(411557987, 131002976);
        Assertions.assertEquals(0.0, pi1.doubleValue() - pi2.doubleValue(), 1.0e-20);
    }

    @Test
    void testCompareTo_22_oe() {
        final Fraction a = Fraction.of(1, 2);
        final Fraction b = Fraction.of(1, 3);
        final Fraction c = Fraction.of(1, 2);
        final Fraction d = Fraction.of(-1, 2);
        final Fraction e = Fraction.of(1, -2);
        final Fraction f = Fraction.of(-1, -2);
        final Fraction g = Fraction.of(-1, Integer.MIN_VALUE);



        final Fraction pi1 = Fraction.of(1068966896, 340262731);
        final Fraction pi2 = Fraction.of(411557987, 131002976);

        Assertions.assertEquals(0, ZERO_P.compareTo(ZERO_N));
    }

    @Test
    void testDoubleValue_6_oe() {

        Assertions.assertEquals(0.0, Fraction.ZERO.doubleValue());
    }

    @Test
    void testDoubleValue_7_oe() {

        Assertions.assertEquals(0.0, ZERO_P.doubleValue());
    }

    @Test
    void testDoubleValue_8_oe() {

        Assertions.assertEquals(0.0, ZERO_N.doubleValue());
    }

    @Test
    void testFloatValue_1_oe() {
        Assertions.assertEquals(0.5f, Fraction.of(1, 2).floatValue());
    }

    @Test
    void testFloatValue_2_oe() {
        Assertions.assertEquals(0.5f, Fraction.of(-1, -2).floatValue());
    }

    @Test
    void testFloatValue_3_oe() {
        Assertions.assertEquals(-0.5f, Fraction.of(-1, 2).floatValue());
    }

    @Test
    void testFloatValue_4_oe() {
        Assertions.assertEquals(-0.5f, Fraction.of(1, -2).floatValue());
    }

    @Test
    void testFloatValue_5_oe() {

        final float e = 1f / 3f;
        Assertions.assertEquals(e, Fraction.of(1, 3).floatValue());
    }

    @Test
    void testFloatValue_6_oe() {

        final float e = 1f / 3f;
        Assertions.assertEquals(e, Fraction.of(-1, -3).floatValue());
    }

    @Test
    void testFloatValue_7_oe() {

        final float e = 1f / 3f;
        Assertions.assertEquals(-e, Fraction.of(-1, 3).floatValue());
    }

    @Test
    void testFloatValue_8_oe() {

        final float e = 1f / 3f;
        Assertions.assertEquals(-e, Fraction.of(1, -3).floatValue());
    }

    @Test
    void testFloatValue_9_oe() {

        final float e = 1f / 3f;

        Assertions.assertEquals(0.0f, ZERO_P.floatValue());
    }

    @Test
    void testFloatValue_10_oe() {

        final float e = 1f / 3f;

        Assertions.assertEquals(0.0f, ZERO_N.floatValue());
    }

    @Test
    void testIntValue_1_oe() {
        Assertions.assertEquals(0, Fraction.of(1, 2).intValue());
    }

    @Test
    void testIntValue_2_oe() {
        Assertions.assertEquals(0, Fraction.of(-1, -2).intValue());
    }

    @Test
    void testIntValue_3_oe() {
        Assertions.assertEquals(0, Fraction.of(-1, 2).intValue());
    }

    @Test
    void testIntValue_4_oe() {
        Assertions.assertEquals(0, Fraction.of(1, -2).intValue());
    }

    @Test
    void testIntValue_5_oe() {

        Assertions.assertEquals(1, Fraction.of(3, 2).intValue());
    }

    @Test
    void testIntValue_6_oe() {

        Assertions.assertEquals(1, Fraction.of(-3, -2).intValue());
    }

    @Test
    void testIntValue_7_oe() {

        Assertions.assertEquals(-1, Fraction.of(-3, 2).intValue());
    }

    @Test
    void testIntValue_8_oe() {

        Assertions.assertEquals(-1, Fraction.of(3, -2).intValue());
    }

    @Test
    void testIntValue_9_oe() {


        Assertions.assertEquals(0, Fraction.of(1, Integer.MIN_VALUE).intValue());
    }

    @Test
    void testIntValue_10_oe() {


        Assertions.assertEquals(0, Fraction.of(-1, Integer.MIN_VALUE).intValue());
    }

    @Test
    void testIntValue_11_oe() {


        Assertions.assertEquals(Integer.MIN_VALUE, Fraction.of(Integer.MIN_VALUE, 1).intValue());
    }

    @Test
    void testIntValue_12_oe() {


        Assertions.assertEquals(Integer.MAX_VALUE, Fraction.of(Integer.MIN_VALUE, -1).intValue());
    }

    @Test
    void testIntValue_13_oe() {



        Assertions.assertEquals(0, ZERO_P.intValue());
    }

    @Test
    void testIntValue_14_oe() {



        Assertions.assertEquals(0, ZERO_N.intValue());
    }

    @Test
    void testLongValue_1_oe() {
        Assertions.assertEquals(0L, Fraction.of(1, 2).longValue());
    }

    @Test
    void testLongValue_2_oe() {
        Assertions.assertEquals(0L, Fraction.of(-1, -2).longValue());
    }

    @Test
    void testLongValue_3_oe() {
        Assertions.assertEquals(0L, Fraction.of(-1, 2).longValue());
    }

    @Test
    void testLongValue_4_oe() {
        Assertions.assertEquals(0L, Fraction.of(1, -2).longValue());
    }

    @Test
    void testLongValue_5_oe() {

        Assertions.assertEquals(1L, Fraction.of(3, 2).longValue());
    }

    @Test
    void testLongValue_6_oe() {

        Assertions.assertEquals(1L, Fraction.of(-3, -2).longValue());
    }

    @Test
    void testLongValue_7_oe() {

        Assertions.assertEquals(-1L, Fraction.of(-3, 2).longValue());
    }

    @Test
    void testLongValue_8_oe() {

        Assertions.assertEquals(-1L, Fraction.of(3, -2).longValue());
    }

    @Test
    void testLongValue_9_oe() {


        Assertions.assertEquals(0, Fraction.of(1, Integer.MIN_VALUE).longValue());
    }

    @Test
    void testLongValue_10_oe() {


        Assertions.assertEquals(0, Fraction.of(-1, Integer.MIN_VALUE).longValue());
    }

    @Test
    void testLongValue_11_oe() {


        Assertions.assertEquals(Integer.MIN_VALUE, Fraction.of(Integer.MIN_VALUE, 1).longValue());
    }

    @Test
    void testLongValue_12_oe() {


        Assertions.assertEquals(Integer.MAX_VALUE + 1L, Fraction.of(Integer.MIN_VALUE, -1).longValue());
    }

    @Test
    void testLongValue_13_oe() {



        Assertions.assertEquals(0L, ZERO_P.longValue());
    }

    @Test
    void testLongValue_14_oe() {



        Assertions.assertEquals(0L, ZERO_N.longValue());
    }

    @Test
    void testReciprocal_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.reciprocalTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
        }

        final Fraction f = Fraction.of(0, 3);
        try {
    f.reciprocal();
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testAdd_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            try {
    f1.add(f2);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testAdd_4_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            try {
    f1.add(i2);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testAdd_5_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }

        try {
    Fraction.ONE.add((Fraction) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testDivide_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : divideByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            try {
    f1.divide(f2);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testDivide_4_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : divideByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : divideByIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            try {
    f1.divide(i2);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testDivide_5_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : divideByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : divideByIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }

        try {
    Fraction.ONE.divide((Fraction) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testDivide_6_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : divideByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : divideByIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


        try {
    Fraction.of(1, 2).divide(Fraction.ZERO);
    fail("FractionException");
} catch (FractionException e) {
}
    }

    @Test
    void testDivide_7_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : divideByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : divideByIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


        try {
    Fraction.of(1, 2).divide(0);
    fail("FractionException");
} catch (FractionException e) {
}
    }

    @Test
    void testMultiply_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : multiplyByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            try {
    f1.multiply(f2);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testMultiply_4_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : multiplyByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : multiplyByIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            try {
    f1.multiply(i2);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testMultiply_5_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : multiplyByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : multiplyByIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }

        try {
    Fraction.ONE.multiply((Fraction) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testPow_2_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
        }

        try {
    Fraction.of(Integer.MAX_VALUE).pow(2);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testPow_3_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
        }

        try {
    Fraction.of(1, Integer.MAX_VALUE).pow(2);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testPow_4_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
        }

        try {
    Fraction.of(Integer.MAX_VALUE).pow(-2);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testPow_5_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
        }

        try {
    Fraction.of(1, Integer.MAX_VALUE).pow(-2);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testSubtract_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            try {
    f1.subtract(f2);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testSubtract_4_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            try {
    f1.subtract(i2);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testSubtract_5_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }

        try {
    Fraction.ONE.add((Fraction) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testEqualsAndHashCode_1_oe() {
        final Fraction zero = Fraction.of(0, 1);
        Assertions.assertEquals(zero, zero);
    }

    @Test
    void testEqualsAndHashCode_2_oe() {
        final Fraction zero = Fraction.of(0, 1);
        Assertions.assertNotEquals(zero, null);
    }

    @Test
    void testEqualsAndHashCode_3_oe() {
        final Fraction zero = Fraction.of(0, 1);
        Assertions.assertNotEquals(zero, new Object());
    }

    @Test
    void testEqualsAndHashCode_4_oe() {
        final Fraction zero = Fraction.of(0, 1);
        Assertions.assertNotEquals(zero, Double.valueOf(0));
    }

    @Test
    void testEqualsAndHashCode_6_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);
        Assertions.assertNotEquals(zero, one);
    }

    @Test
    void testEqualsAndHashCode_7_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);
        Assertions.assertNotEquals(one, zero);
    }

    @Test
    void testEqualsAndHashCode_12_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
        }

        final Fraction half = Fraction.of(1, 2);
        final Fraction two = Fraction.of(2, 1);
        Assertions.assertNotEquals(one, half);
    }

    @Test
    void testEqualsAndHashCode_13_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
        }

        final Fraction half = Fraction.of(1, 2);
        final Fraction two = Fraction.of(2, 1);
        Assertions.assertNotEquals(one, two);
    }

    @Test
    void testEqualsAndHashCode_14_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
        }

        final Fraction half = Fraction.of(1, 2);
        final Fraction two = Fraction.of(2, 1);

        final Fraction almostOne = Fraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        final Fraction almostOne2 = Fraction.of(Integer.MIN_VALUE, -Integer.MAX_VALUE);
        Assertions.assertEquals(almostOne, almostOne);
    }

    @Test
    void testEqualsAndHashCode_15_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
        }

        final Fraction half = Fraction.of(1, 2);
        final Fraction two = Fraction.of(2, 1);

        final Fraction almostOne = Fraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        final Fraction almostOne2 = Fraction.of(Integer.MIN_VALUE, -Integer.MAX_VALUE);
        Assertions.assertNotEquals(almostOne, almostOne2);
    }

    @Test
    void testEqualsAndHashCode_16_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
        }

        final Fraction half = Fraction.of(1, 2);
        final Fraction two = Fraction.of(2, 1);

        final Fraction almostOne = Fraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        final Fraction almostOne2 = Fraction.of(Integer.MIN_VALUE, -Integer.MAX_VALUE);
        final Fraction almostZero = Fraction.of(-1, Integer.MIN_VALUE);
        final Fraction almostZero2 = Fraction.of(1, Integer.MIN_VALUE);
        Assertions.assertEquals(almostZero, almostZero);
    }

    @Test
    void testEqualsAndHashCode_17_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
        }

        final Fraction half = Fraction.of(1, 2);
        final Fraction two = Fraction.of(2, 1);

        final Fraction almostOne = Fraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        final Fraction almostOne2 = Fraction.of(Integer.MIN_VALUE, -Integer.MAX_VALUE);
        final Fraction almostZero = Fraction.of(-1, Integer.MIN_VALUE);
        final Fraction almostZero2 = Fraction.of(1, Integer.MIN_VALUE);
        Assertions.assertNotEquals(almostZero, almostZero2);
    }

    @Test
    void testAdditiveNeutral_1_oe() {
        Assertions.assertEquals(Fraction.ZERO, Fraction.ONE.zero());
    }

    @Test
    void testMultiplicativeNeutral_1_oe() {
        Assertions.assertEquals(Fraction.ONE, Fraction.ZERO.one());
    }

    @Test
    void testSerial_1_oe() {
        final Fraction[] fractions = {
            Fraction.of(3, 4), Fraction.ONE, Fraction.ZERO,
            Fraction.of(17), Fraction.from(Math.PI, 1000),
            Fraction.of(-5, 2)
        };
        for (final Fraction fraction : fractions) {
            Assertions.assertEquals(fraction,TestUtils.serializeAndRecover(fraction));
    }
    }

    @Test
    void testToString_1_oe() {
        Assertions.assertEquals("0", Fraction.of(0, 3).toString());
    }

    @Test
    void testToString_2_oe() {
        Assertions.assertEquals("0", Fraction.of(0, -3).toString());
    }

    @Test
    void testToString_3_oe() {
        Assertions.assertEquals("3", Fraction.of(6, 2).toString());
    }

    @Test
    void testToString_4_oe() {
        Assertions.assertEquals("2 / 3", Fraction.of(18, 27).toString());
    }

    @Test
    void testToString_5_oe() {
        Assertions.assertEquals("-10 / 11", Fraction.of(-10, 11).toString());
    }

    @Test
    void testToString_6_oe() {
        Assertions.assertEquals("10 / -11", Fraction.of(10, -11).toString());
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
            "-3"
        };
        final Fraction[] fractions = {
            Fraction.of(1, 2),
            Fraction.of(-1, 2),
            Fraction.of(1, -2),
            Fraction.of(-1, -2),
            Fraction.of(1, 2),
            Fraction.of(1, 2),
            Fraction.of(-1, 2),
            Fraction.of(1, -2),
            Fraction.of(15, 16),
            Fraction.of(-2, 3),
            Fraction.of(8, 7),
            Fraction.of(5, 1),
            Fraction.of(-3, 1),
            Fraction.of(3, -1),
        };
        int inc = 0;
        for (final Fraction fraction : fractions) {
            inc++;
        }

        try {
    Fraction.parse("1 // 2");
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
            "-3"
        };
        final Fraction[] fractions = {
            Fraction.of(1, 2),
            Fraction.of(-1, 2),
            Fraction.of(1, -2),
            Fraction.of(-1, -2),
            Fraction.of(1, 2),
            Fraction.of(1, 2),
            Fraction.of(-1, 2),
            Fraction.of(1, -2),
            Fraction.of(15, 16),
            Fraction.of(-2, 3),
            Fraction.of(8, 7),
            Fraction.of(5, 1),
            Fraction.of(-3, 1),
            Fraction.of(3, -1),
        };
        int inc = 0;
        for (final Fraction fraction : fractions) {
            inc++;
        }

        try {
    Fraction.parse("1 / z");
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
            "-3"
        };
        final Fraction[] fractions = {
            Fraction.of(1, 2),
            Fraction.of(-1, 2),
            Fraction.of(1, -2),
            Fraction.of(-1, -2),
            Fraction.of(1, 2),
            Fraction.of(1, 2),
            Fraction.of(-1, 2),
            Fraction.of(1, -2),
            Fraction.of(15, 16),
            Fraction.of(-2, 3),
            Fraction.of(8, 7),
            Fraction.of(5, 1),
            Fraction.of(-3, 1),
            Fraction.of(3, -1),
        };
        int inc = 0;
        for (final Fraction fraction : fractions) {
            inc++;
        }

        try {
    Fraction.parse("1 / --2");
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
            "-3"
        };
        final Fraction[] fractions = {
            Fraction.of(1, 2),
            Fraction.of(-1, 2),
            Fraction.of(1, -2),
            Fraction.of(-1, -2),
            Fraction.of(1, 2),
            Fraction.of(1, 2),
            Fraction.of(-1, 2),
            Fraction.of(1, -2),
            Fraction.of(15, 16),
            Fraction.of(-2, 3),
            Fraction.of(8, 7),
            Fraction.of(5, 1),
            Fraction.of(-3, 1),
            Fraction.of(3, -1),
        };
        int inc = 0;
        for (final Fraction fraction : fractions) {
            inc++;
        }

        try {
    Fraction.parse("x");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testNumbers150_1_oe() {
        try {
    Fraction.ZERO.pow(-1);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testNumbers150_2_oe() {
        try {
    Fraction.ZERO.pow(Integer.MIN_VALUE);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testNumbers150_3_oe() {

        final Fraction f2 = Fraction.of(2);
        try {
    f2.pow(Integer.MIN_VALUE);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testNumbers150_4_oe() {

        final Fraction f2 = Fraction.of(2);
        final Fraction f12 = Fraction.of(1, 2);
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
            final Fraction actual0 = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }
    }

    @Test
    void testConstructor_1_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }
    }

    @Test
    void testConstructor_2_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
        }

                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = -1;
        final Fraction actual0 = Fraction.of(Integer.MIN_VALUE, -1);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testConstructor_2_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
        }

                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = -1;
        final Fraction actual0 = Fraction.of(Integer.MIN_VALUE, -1);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testConstructor_3_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
        }

                final long expectedNumerator0 = 1;
        final long expectedDenominator0 = Integer.MIN_VALUE;
        final Fraction actual0 = Fraction.of(1, Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testConstructor_3_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
        }

                final long expectedNumerator0 = 1;
        final long expectedDenominator0 = Integer.MIN_VALUE;
        final Fraction actual0 = Fraction.of(1, Integer.MIN_VALUE);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testConstructor_4_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
        }

                final long expectedNumerator0 = -1;
        final long expectedDenominator0 = Integer.MIN_VALUE;
        final Fraction actual0 = Fraction.of(-1, Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testConstructor_4_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
        }

                final long expectedNumerator0 = -1;
        final long expectedDenominator0 = Integer.MIN_VALUE;
        final Fraction actual0 = Fraction.of(-1, Integer.MIN_VALUE);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testConstructor_5_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
        }

                final long expectedNumerator0 = 1;
        final long expectedDenominator0 = 1;
        final Fraction actual0 = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testConstructor_5_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
        }

                final long expectedNumerator0 = 1;
        final long expectedDenominator0 = 1;
        final Fraction actual0 = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructor_1_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = Fraction.from(testCase.operand);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }
    }

    @Test
    void testDoubleConstructor_1_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = Fraction.from(testCase.operand);
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }
    }

    @Test
    void testDoubleConstructor_2_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final long expectedNumerator0 = 1;
        final long expectedDenominator0 = 3;
        final Fraction actual0 = Fraction.from(1.0 / 3.0);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructor_2_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final long expectedNumerator0 = 1;
        final long expectedDenominator0 = 3;
        final Fraction actual0 = Fraction.from(1.0 / 3.0);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructor_3_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final long expectedNumerator0 = 17;
        final long expectedDenominator0 = 100;
        final Fraction actual0 = Fraction.from(17.0 / 100.0);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructor_3_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final long expectedNumerator0 = 17;
        final long expectedDenominator0 = 100;
        final Fraction actual0 = Fraction.from(17.0 / 100.0);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructor_4_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final long expectedNumerator0 = 317;
        final long expectedDenominator0 = 100;
        final Fraction actual0 = Fraction.from(317.0 / 100.0);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructor_4_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final long expectedNumerator0 = 317;
        final long expectedDenominator0 = 100;
        final Fraction actual0 = Fraction.from(317.0 / 100.0);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructor_5_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final long expectedNumerator0 = -1;
        final long expectedDenominator0 = 3;
        final Fraction actual0 = Fraction.from(-1.0 / 3.0);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructor_5_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final long expectedNumerator0 = -1;
        final long expectedDenominator0 = 3;
        final Fraction actual0 = Fraction.from(-1.0 / 3.0);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructor_6_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final long expectedNumerator0 = -17;
        final long expectedDenominator0 = 100;
        final Fraction actual0 = Fraction.from(17.0 / -100.0);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructor_6_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final long expectedNumerator0 = -17;
        final long expectedDenominator0 = 100;
        final Fraction actual0 = Fraction.from(17.0 / -100.0);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructor_7_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final long expectedNumerator0 = -317;
        final long expectedDenominator0 = 100;
        final Fraction actual0 = Fraction.from(-317.0 / 100.0);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructor_7_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final long expectedNumerator0 = -317;
        final long expectedDenominator0 = 100;
        final Fraction actual0 = Fraction.from(-317.0 / 100.0);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_1_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = Fraction.from(testCase.operand, testCase.maxDenominator);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_1_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = Fraction.from(testCase.operand, testCase.maxDenominator);
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_2_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
        }

                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = -1;
        final Fraction actual0 = Fraction.from(Integer.MIN_VALUE * -1.0, 2);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_2_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
        }

                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = -1;
        final Fraction actual0 = Fraction.from(Integer.MIN_VALUE * -1.0, 2);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_3_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
        }

                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = -3;
        final Fraction actual0 = Fraction.from(Integer.MIN_VALUE / -3.0, 10);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_3_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
        }

                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = -3;
        final Fraction actual0 = Fraction.from(Integer.MIN_VALUE / -3.0, 10);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_4_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
        }

                final long expectedNumerator0 = 1;
        final long expectedDenominator0 = Integer.MIN_VALUE;
        final Fraction actual0 = Fraction.from(1.0 / Integer.MIN_VALUE, Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_4_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
        }

                final long expectedNumerator0 = 1;
        final long expectedDenominator0 = Integer.MIN_VALUE;
        final Fraction actual0 = Fraction.from(1.0 / Integer.MIN_VALUE, Integer.MIN_VALUE);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_5_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
        }

                final long expectedNumerator0 = -1;
        final long expectedDenominator0 = Integer.MIN_VALUE;
        final Fraction actual0 = Fraction.from(-1.0 / Integer.MIN_VALUE, Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_5_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
        }

                final long expectedNumerator0 = -1;
        final long expectedDenominator0 = Integer.MIN_VALUE;
        final Fraction actual0 = Fraction.from(-1.0 / Integer.MIN_VALUE, Integer.MIN_VALUE);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructorThrows_7_oe_1_oe() {
        final double eps = 1e-5;
        final int maxIterations = Integer.MAX_VALUE;
        final int maxDenominator = Integer.MAX_VALUE;
        for (final double value : new double[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
        }
                final long expectedNumerator0 = 1;
        final long expectedDenominator0 = 1;
        final Fraction actual0 = Fraction.from(1.0, 0, maxIterations);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructorThrows_7_oe_2_oe() {
        final double eps = 1e-5;
        final int maxIterations = Integer.MAX_VALUE;
        final int maxDenominator = Integer.MAX_VALUE;
        for (final double value : new double[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
        }
                final long expectedNumerator0 = 1;
        final long expectedDenominator0 = 1;
        final Fraction actual0 = Fraction.from(1.0, 0, maxIterations);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructorOverflow_1_oe_1_oe() {
                final double a0 = 0.75000000001455192;
        try {
    Fraction.from(a0, 1.0e-12, 1000);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorOverflow_2_oe_1_oe() {
                final double a0 = 1.0e10;
        try {
    Fraction.from(a0, 1.0e-12, 1000);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorOverflow_3_oe_1_oe() {
                final double a0 = -1.0e10;
        try {
    Fraction.from(a0, 1.0e-12, 1000);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorOverflow_4_oe_1_oe() {
                final double a0 = -43979.60679604749;
        try {
    Fraction.from(a0, 1.0e-12, 1000);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_1_oe_1_oe() throws Exception  {
                final long expectedNumerator0 = 2;
        final long expectedDenominator0 = 5;
        final Fraction actual0 = Fraction.from(0.4, 1.0e-5, 100);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_1_oe_2_oe() throws Exception  {
                final long expectedNumerator0 = 2;
        final long expectedDenominator0 = 5;
        final Fraction actual0 = Fraction.from(0.4, 1.0e-5, 100);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_2_oe_1_oe() throws Exception  {

                final long expectedNumerator0 = 3;
        final long expectedDenominator0 = 5;
        final Fraction actual0 = Fraction.from(0.6152, 0.02, 100);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_2_oe_2_oe() throws Exception  {

                final long expectedNumerator0 = 3;
        final long expectedDenominator0 = 5;
        final Fraction actual0 = Fraction.from(0.6152, 0.02, 100);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_3_oe_1_oe() throws Exception  {

                final long expectedNumerator0 = 8;
        final long expectedDenominator0 = 13;
        final Fraction actual0 = Fraction.from(0.6152, 1.0e-3, 100);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_3_oe_2_oe() throws Exception  {

                final long expectedNumerator0 = 8;
        final long expectedDenominator0 = 13;
        final Fraction actual0 = Fraction.from(0.6152, 1.0e-3, 100);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_4_oe_1_oe() throws Exception  {

                final long expectedNumerator0 = 251;
        final long expectedDenominator0 = 408;
        final Fraction actual0 = Fraction.from(0.6152, 1.0e-4, 100);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_4_oe_2_oe() throws Exception  {

                final long expectedNumerator0 = 251;
        final long expectedDenominator0 = 408;
        final Fraction actual0 = Fraction.from(0.6152, 1.0e-4, 100);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_5_oe_1_oe() throws Exception  {

                final long expectedNumerator0 = 251;
        final long expectedDenominator0 = 408;
        final Fraction actual0 = Fraction.from(0.6152, 1.0e-5, 100);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_5_oe_2_oe() throws Exception  {

                final long expectedNumerator0 = 251;
        final long expectedDenominator0 = 408;
        final Fraction actual0 = Fraction.from(0.6152, 1.0e-5, 100);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_6_oe_1_oe() throws Exception  {

                final long expectedNumerator0 = 510;
        final long expectedDenominator0 = 829;
        final Fraction actual0 = Fraction.from(0.6152, 1.0e-6, 100);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_6_oe_2_oe() throws Exception  {

                final long expectedNumerator0 = 510;
        final long expectedDenominator0 = 829;
        final Fraction actual0 = Fraction.from(0.6152, 1.0e-6, 100);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_7_oe_1_oe() throws Exception  {

                final long expectedNumerator0 = 769;
        final long expectedDenominator0 = 1250;
        final Fraction actual0 = Fraction.from(0.6152, 1.0e-7, 100);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_7_oe_2_oe() throws Exception  {

                final long expectedNumerator0 = 769;
        final long expectedDenominator0 = 1250;
        final Fraction actual0 = Fraction.from(0.6152, 1.0e-7, 100);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDoubleValue_1_oe_1_oe() {
                final double expected0 = 0.5;
        final int numerator0 = 1;
        final int denominator0 = 2;
        final Fraction f0 = Fraction.of(numerator0, denominator0);
                Assertions.assertEquals(expected0, f0.doubleValue());
    }

    @Test
    void testDoubleValue_2_oe_1_oe() {
                final double expected0 = -0.5;
        final int numerator0 = -1;
        final int denominator0 = 2;
        final Fraction f0 = Fraction.of(numerator0, denominator0);
                Assertions.assertEquals(expected0, f0.doubleValue());
    }

    @Test
    void testDoubleValue_3_oe_1_oe() {
                final double expected0 = -0.5;
        final int numerator0 = 1;
        final int denominator0 = -2;
        final Fraction f0 = Fraction.of(numerator0, denominator0);
                Assertions.assertEquals(expected0, f0.doubleValue());
    }

    @Test
    void testDoubleValue_4_oe_1_oe() {
                final double expected0 = 0.5;
        final int numerator0 = -1;
        final int denominator0 = -2;
        final Fraction f0 = Fraction.of(numerator0, denominator0);
                Assertions.assertEquals(expected0, f0.doubleValue());
    }

    @Test
    void testDoubleValue_5_oe_1_oe() {
                final double expected0 = 1.0 / 3.0;
        final int numerator0 = 1;
        final int denominator0 = 3;
        final Fraction f0 = Fraction.of(numerator0, denominator0);
                Assertions.assertEquals(expected0, f0.doubleValue());
    }

    @Test
    void testAbs_1_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.absTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f.abs();
            Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }
    }

    @Test
    void testAbs_1_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.absTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f.abs();
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }
    }

    @Test
    void testReciprocal_1_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.reciprocalTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f.reciprocal();
            Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }
    }

    @Test
    void testReciprocal_1_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.reciprocalTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f.reciprocal();
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }
    }

    @Test
    void testNegate_1_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f.negate();
            Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }
    }

    @Test
    void testNegate_1_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f.negate();
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }
    }

    @Test
    void testNegate_2_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
        }

        final Fraction one = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
                final long expectedNumerator0 = -1;
        final long expectedDenominator0 = 1;
        final Fraction actual0 = one.negate();
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testNegate_2_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
        }

        final Fraction one = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
                final long expectedNumerator0 = -1;
        final long expectedDenominator0 = 1;
        final Fraction actual0 = one.negate();
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testNegate_3_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
        }

        final Fraction one = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
        final Fraction minValue = Fraction.of(Integer.MIN_VALUE, 1);
                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = -1;
        final Fraction actual0 = minValue.negate();
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testNegate_3_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
        }

        final Fraction one = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
        final Fraction minValue = Fraction.of(Integer.MIN_VALUE, 1);
                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = -1;
        final Fraction actual0 = minValue.negate();
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testAdd_1_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.add(f2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }
    }

    @Test
    void testAdd_1_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.add(f2);
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }
    }

    @Test
    void testAdd_2_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.add(i2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }
    }

    @Test
    void testAdd_2_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.add(i2);
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }
    }

    @Test
    void testAdd_6_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = -1;
        final Fraction actual0 = Fraction.ZERO.add(Fraction.of(Integer.MIN_VALUE, -1));
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testAdd_6_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = -1;
        final Fraction actual0 = Fraction.ZERO.add(Fraction.of(Integer.MIN_VALUE, -1));
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testAdd_7_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = 1;
        final Fraction actual0 = Fraction.ZERO.add(Fraction.of(Integer.MIN_VALUE, 1));
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testAdd_7_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = 1;
        final Fraction actual0 = Fraction.ZERO.add(Fraction.of(Integer.MIN_VALUE, 1));
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testAdd_8_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = 1;
        final Fraction actual0 = Fraction.ZERO.add(Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testAdd_8_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = 1;
        final Fraction actual0 = Fraction.ZERO.add(Integer.MIN_VALUE);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testDivide_1_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.divide(f2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }
    }

    @Test
    void testDivide_1_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.divide(f2);
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }
    }

    @Test
    void testDivide_2_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.divide(i2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }
    }

    @Test
    void testDivide_2_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.divide(i2);
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }
    }

    @Test
    void testMultiply_1_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.multiply(f2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }
    }

    @Test
    void testMultiply_1_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.multiply(f2);
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }
    }

    @Test
    void testMultiply_2_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.multiply(i2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }
    }

    @Test
    void testMultiply_2_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.multiply(i2);
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }
    }

    @Test
    void testPow_1_oe_1_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.pow(exponent);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }
    }

    @Test
    void testPow_1_oe_2_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.pow(exponent);
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }
    }

    @Test
    void testSubtract_1_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.subtract(f2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }
    }

    @Test
    void testSubtract_1_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.subtract(f2);
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }
    }

    @Test
    void testSubtract_2_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.subtract(i2);
            Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }
    }

    @Test
    void testSubtract_2_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final long expectedNumerator0 = testCase.expectedNumerator;
            final long expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.subtract(i2);
                    Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }
    }

    @Test
    void testSubtract_6_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = 1;
        final Fraction actual0 = Fraction.ZERO.subtract(Fraction.of(Integer.MIN_VALUE, -1));
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testSubtract_6_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = 1;
        final Fraction actual0 = Fraction.ZERO.subtract(Fraction.of(Integer.MIN_VALUE, -1));
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testSubtract_7_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = -1;
        final Fraction actual0 = Fraction.ZERO.subtract(Fraction.of(Integer.MIN_VALUE, 1));
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testSubtract_7_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = -1;
        final Fraction actual0 = Fraction.ZERO.subtract(Fraction.of(Integer.MIN_VALUE, 1));
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testSubtract_8_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = -1;
        final Fraction actual0 = Fraction.ZERO.subtract(Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testSubtract_8_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final long expectedNumerator0 = Integer.MIN_VALUE;
        final long expectedDenominator0 = -1;
        final Fraction actual0 = Fraction.ZERO.subtract(Integer.MIN_VALUE);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testEqualsAndHashCode_5_oe_1_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);
                final Fraction f10 = zero;
        final Fraction f20 = zero2;
        Assertions.assertEquals(f10, f20);
    }

    @Test
    void testEqualsAndHashCode_5_oe_2_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);
                final Fraction f10 = zero;
        final Fraction f20 = zero2;
                Assertions.assertEquals(f10.hashCode(), f20.hashCode(), "Equal fractions have different hashCode");
    }

    @Test
    void testEqualsAndHashCode_5_oe_3_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);
                final Fraction f10 = zero;
        final Fraction f20 = zero2;
                final int expected0 = f10.signum() *
                                     Arrays.hashCode(new int[] {Math.abs(f10.getNumerator()),
                                                                Math.abs(f10.getDenominator())});
                Assertions.assertEquals(expected0, f10.hashCode(), "Hashcode not equal to using Arrays.hashCode");
    }

    @Test
    void testEqualsAndHashCode_8_oe_1_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
                        final Fraction f10 = f1;
            final Fraction f20 = f2;
            Assertions.assertEquals(f10, f20);
    }
    }

    @Test
    void testEqualsAndHashCode_8_oe_2_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
                        final Fraction f10 = f1;
            final Fraction f20 = f2;
                    Assertions.assertEquals(f10.hashCode(), f20.hashCode(), "Equal fractions have different hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_8_oe_3_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
                        final Fraction f10 = f1;
            final Fraction f20 = f2;
                    final int expected0 = f10.signum() *
                                         Arrays.hashCode(new int[] {Math.abs(f10.getNumerator()),
                                                                    Math.abs(f10.getDenominator())});
                    Assertions.assertEquals(expected0, f10.hashCode(), "Hashcode not equal to using Arrays.hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_9_oe_1_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
                        final Fraction f10 = f2;
            final Fraction f20 = f1;
            Assertions.assertEquals(f10, f20);
    }
    }

    @Test
    void testEqualsAndHashCode_9_oe_2_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
                        final Fraction f10 = f2;
            final Fraction f20 = f1;
                    Assertions.assertEquals(f10.hashCode(), f20.hashCode(), "Equal fractions have different hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_9_oe_3_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
                        final Fraction f10 = f2;
            final Fraction f20 = f1;
                    final int expected0 = f10.signum() *
                                         Arrays.hashCode(new int[] {Math.abs(f10.getNumerator()),
                                                                    Math.abs(f10.getDenominator())});
                    Assertions.assertEquals(expected0, f10.hashCode(), "Hashcode not equal to using Arrays.hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_10_oe_1_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
                        final Fraction f10 = f1;
            final Fraction f20 = f2;
            Assertions.assertEquals(f10, f20);
    }
    }

    @Test
    void testEqualsAndHashCode_10_oe_2_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
                        final Fraction f10 = f1;
            final Fraction f20 = f2;
                    Assertions.assertEquals(f10.hashCode(), f20.hashCode(), "Equal fractions have different hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_10_oe_3_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
                        final Fraction f10 = f1;
            final Fraction f20 = f2;
                    final int expected0 = f10.signum() *
                                         Arrays.hashCode(new int[] {Math.abs(f10.getNumerator()),
                                                                    Math.abs(f10.getDenominator())});
                    Assertions.assertEquals(expected0, f10.hashCode(), "Hashcode not equal to using Arrays.hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_11_oe_1_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
                        final Fraction f10 = f2;
            final Fraction f20 = f1;
            Assertions.assertEquals(f10, f20);
    }
    }

    @Test
    void testEqualsAndHashCode_11_oe_2_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
                        final Fraction f10 = f2;
            final Fraction f20 = f1;
                    Assertions.assertEquals(f10.hashCode(), f20.hashCode(), "Equal fractions have different hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_11_oe_3_oe() {
        final Fraction zero = Fraction.of(0, 1);

        final Fraction zero2 = Fraction.of(0, 2);

        final Fraction one = Fraction.of(1, 1);

        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
                        final Fraction f10 = f2;
            final Fraction f20 = f1;
                    final int expected0 = f10.signum() *
                                         Arrays.hashCode(new int[] {Math.abs(f10.getNumerator()),
                                                                    Math.abs(f10.getDenominator())});
                    Assertions.assertEquals(expected0, f10.hashCode(), "Hashcode not equal to using Arrays.hashCode");
    }
    }

    @Test
    void testMath1261_1_oe_1_oe() {
        final Fraction a = Fraction.of(Integer.MAX_VALUE, 2);
                final long expectedNumerator0 = Integer.MAX_VALUE;
        final long expectedDenominator0 = 1;
        final Fraction actual0 = a.multiply(2);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testMath1261_1_oe_2_oe() {
        final Fraction a = Fraction.of(Integer.MAX_VALUE, 2);
                final long expectedNumerator0 = Integer.MAX_VALUE;
        final long expectedDenominator0 = 1;
        final Fraction actual0 = a.multiply(2);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testMath1261_2_oe_1_oe() {
        final Fraction a = Fraction.of(Integer.MAX_VALUE, 2);

        final Fraction b = Fraction.of(2, Integer.MAX_VALUE);
                final long expectedNumerator0 = 1;
        final long expectedDenominator0 = Integer.MAX_VALUE;
        final Fraction actual0 = b.divide(2);
        Assertions.assertEquals(expectedNumerator0, actual0.getNumerator());
    }

    @Test
    void testMath1261_2_oe_2_oe() {
        final Fraction a = Fraction.of(Integer.MAX_VALUE, 2);

        final Fraction b = Fraction.of(2, Integer.MAX_VALUE);
                final long expectedNumerator0 = 1;
        final long expectedDenominator0 = Integer.MAX_VALUE;
        final Fraction actual0 = b.divide(2);
                Assertions.assertEquals(expectedDenominator0, actual0.getDenominator());
    }

    @Test
    void testConstructor_1_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testConstructor_2_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
        }

                final int expectedNumerator0 = Integer.MIN_VALUE;
        final int expectedDenominator0 = -1;
        final Fraction actual0 = Fraction.of(Integer.MIN_VALUE, -1);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testConstructor_3_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
        }

                final int expectedNumerator0 = 1;
        final int expectedDenominator0 = Integer.MIN_VALUE;
        final Fraction actual0 = Fraction.of(1, Integer.MIN_VALUE);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testConstructor_4_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
        }

                final int expectedNumerator0 = -1;
        final int expectedDenominator0 = Integer.MIN_VALUE;
        final Fraction actual0 = Fraction.of(-1, Integer.MIN_VALUE);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testConstructor_5_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
        }

                final int expectedNumerator0 = 1;
        final int expectedDenominator0 = 1;
        final Fraction actual0 = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructor_1_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = Fraction.from(testCase.operand);
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testDoubleConstructor_2_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final int expectedNumerator0 = 1;
        final int expectedDenominator0 = 3;
        final Fraction actual0 = Fraction.from(1.0 / 3.0);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructor_3_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final int expectedNumerator0 = 17;
        final int expectedDenominator0 = 100;
        final Fraction actual0 = Fraction.from(17.0 / 100.0);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructor_4_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final int expectedNumerator0 = 317;
        final int expectedDenominator0 = 100;
        final Fraction actual0 = Fraction.from(317.0 / 100.0);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructor_5_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final int expectedNumerator0 = -1;
        final int expectedDenominator0 = 3;
        final Fraction actual0 = Fraction.from(-1.0 / 3.0);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructor_6_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final int expectedNumerator0 = -17;
        final int expectedDenominator0 = 100;
        final Fraction actual0 = Fraction.from(17.0 / -100.0);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructor_7_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
        }

                final int expectedNumerator0 = -317;
        final int expectedDenominator0 = 100;
        final Fraction actual0 = Fraction.from(-317.0 / 100.0);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_1_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = Fraction.from(testCase.operand, testCase.maxDenominator);
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_2_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
        }

                final int expectedNumerator0 = Integer.MIN_VALUE;
        final int expectedDenominator0 = -1;
        final Fraction actual0 = Fraction.from(Integer.MIN_VALUE * -1.0, 2);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_3_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
        }

                final int expectedNumerator0 = Integer.MIN_VALUE;
        final int expectedDenominator0 = -3;
        final Fraction actual0 = Fraction.from(Integer.MIN_VALUE / -3.0, 10);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_4_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
        }

                final int expectedNumerator0 = 1;
        final int expectedDenominator0 = Integer.MIN_VALUE;
        final Fraction actual0 = Fraction.from(1.0 / Integer.MIN_VALUE, Integer.MIN_VALUE);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_5_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
        }

                final int expectedNumerator0 = -1;
        final int expectedDenominator0 = Integer.MIN_VALUE;
        final Fraction actual0 = Fraction.from(-1.0 / Integer.MIN_VALUE, Integer.MIN_VALUE);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorThrows_7_oe_3_oe() {
        final double eps = 1e-5;
        final int maxIterations = Integer.MAX_VALUE;
        final int maxDenominator = Integer.MAX_VALUE;
        for (final double value : new double[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
        }
                final int expectedNumerator0 = 1;
        final int expectedDenominator0 = 1;
        final Fraction actual0 = Fraction.from(1.0, 0, maxIterations);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_1_oe_3_oe() throws Exception  {
                final int expectedNumerator0 = 2;
        final int expectedDenominator0 = 5;
        final Fraction actual0 = Fraction.from(0.4, 1.0e-5, 100);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_2_oe_3_oe() throws Exception  {

                final int expectedNumerator0 = 3;
        final int expectedDenominator0 = 5;
        final Fraction actual0 = Fraction.from(0.6152, 0.02, 100);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_3_oe_3_oe() throws Exception  {

                final int expectedNumerator0 = 8;
        final int expectedDenominator0 = 13;
        final Fraction actual0 = Fraction.from(0.6152, 1.0e-3, 100);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_4_oe_3_oe() throws Exception  {

                final int expectedNumerator0 = 251;
        final int expectedDenominator0 = 408;
        final Fraction actual0 = Fraction.from(0.6152, 1.0e-4, 100);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_5_oe_3_oe() throws Exception  {

                final int expectedNumerator0 = 251;
        final int expectedDenominator0 = 408;
        final Fraction actual0 = Fraction.from(0.6152, 1.0e-5, 100);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_6_oe_3_oe() throws Exception  {

                final int expectedNumerator0 = 510;
        final int expectedDenominator0 = 829;
        final Fraction actual0 = Fraction.from(0.6152, 1.0e-6, 100);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_7_oe_3_oe() throws Exception  {

                final int expectedNumerator0 = 769;
        final int expectedDenominator0 = 1250;
        final Fraction actual0 = Fraction.from(0.6152, 1.0e-7, 100);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testAbs_1_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.absTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f.abs();
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testReciprocal_1_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.reciprocalTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f.reciprocal();
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testNegate_1_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f.negate();
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testNegate_2_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
        }

        final Fraction one = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
                final int expectedNumerator0 = -1;
        final int expectedDenominator0 = 1;
        final Fraction actual0 = one.negate();
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testNegate_3_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
        }

        final Fraction one = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
        final Fraction minValue = Fraction.of(Integer.MIN_VALUE, 1);
                final int expectedNumerator0 = Integer.MIN_VALUE;
        final int expectedDenominator0 = -1;
        final Fraction actual0 = minValue.negate();
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testAdd_1_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.add(f2);
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testAdd_2_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.add(i2);
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testAdd_6_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final int expectedNumerator0 = Integer.MIN_VALUE;
        final int expectedDenominator0 = -1;
        final Fraction actual0 = Fraction.ZERO.add(Fraction.of(Integer.MIN_VALUE, -1));
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testAdd_7_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final int expectedNumerator0 = Integer.MIN_VALUE;
        final int expectedDenominator0 = 1;
        final Fraction actual0 = Fraction.ZERO.add(Fraction.of(Integer.MIN_VALUE, 1));
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testAdd_8_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final int expectedNumerator0 = Integer.MIN_VALUE;
        final int expectedDenominator0 = 1;
        final Fraction actual0 = Fraction.ZERO.add(Integer.MIN_VALUE);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testDivide_1_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.divide(f2);
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testDivide_2_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.divide(i2);
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testMultiply_1_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.multiply(f2);
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testMultiply_2_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.multiply(i2);
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testPow_1_oe_3_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.pow(exponent);
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testSubtract_1_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.subtract(f2);
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testSubtract_2_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator0 = testCase.expectedNumerator;
            final int expectedDenominator0 = testCase.expectedDenominator;
            final Fraction actual0 = f1.subtract(i2);
                    Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }
    }

    @Test
    void testSubtract_6_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final int expectedNumerator0 = Integer.MIN_VALUE;
        final int expectedDenominator0 = 1;
        final Fraction actual0 = Fraction.ZERO.subtract(Fraction.of(Integer.MIN_VALUE, -1));
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testSubtract_7_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final int expectedNumerator0 = Integer.MIN_VALUE;
        final int expectedDenominator0 = -1;
        final Fraction actual0 = Fraction.ZERO.subtract(Fraction.of(Integer.MIN_VALUE, 1));
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testSubtract_8_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
        }


                final int expectedNumerator0 = Integer.MIN_VALUE;
        final int expectedDenominator0 = -1;
        final Fraction actual0 = Fraction.ZERO.subtract(Integer.MIN_VALUE);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testMath1261_1_oe_3_oe() {
        final Fraction a = Fraction.of(Integer.MAX_VALUE, 2);
                final int expectedNumerator0 = Integer.MAX_VALUE;
        final int expectedDenominator0 = 1;
        final Fraction actual0 = a.multiply(2);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

    @Test
    void testMath1261_2_oe_3_oe() {
        final Fraction a = Fraction.of(Integer.MAX_VALUE, 2);

        final Fraction b = Fraction.of(2, Integer.MAX_VALUE);
                final int expectedNumerator0 = 1;
        final int expectedDenominator0 = Integer.MAX_VALUE;
        final Fraction actual0 = b.divide(2);
                Assertions.assertEquals(Integer.signum(expectedNumerator0)* Integer.signum(expectedDenominator0),actual0.signum());
    }

}
