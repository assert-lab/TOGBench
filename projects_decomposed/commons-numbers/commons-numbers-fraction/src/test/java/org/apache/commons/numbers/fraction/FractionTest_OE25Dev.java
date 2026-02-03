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
    void testConstructorZero() {
        Assertions.assertSame(Fraction.ZERO, Fraction.from(0.0));
        Assertions.assertSame(Fraction.ZERO, Fraction.from(0.0, 1e-10, 100));
        Assertions.assertSame(Fraction.ZERO, Fraction.from(0.0, 100));
        Assertions.assertSame(Fraction.ZERO, Fraction.of(0));
        Assertions.assertSame(Fraction.ZERO, Fraction.of(0, 1));
        Assertions.assertSame(Fraction.ZERO, Fraction.of(0, -1));
    }

    // MATH-179

    // MATH-181
    // NUMBERS-147

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

    private void assertDoubleConstructorOverflow(final double a) {
        Assertions.assertThrows(ArithmeticException.class,
            () -> Fraction.from(a, 1.0e-12, 1000)
        );
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
    void testConstructor_1_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
            Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }
    }

    @Test
    void testConstructor_1_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }
    }

    @Test
    void testConstructor_1_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }
    }

    @Test
    void testConstructor_2_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Special cases.
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = Fraction.of(Integer.MIN_VALUE, -1);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testConstructor_2_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Special cases.
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = Fraction.of(Integer.MIN_VALUE, -1);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testConstructor_2_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Special cases.
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = Fraction.of(Integer.MIN_VALUE, -1);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testConstructor_3_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Special cases.
        // removed other assertion
                final int expectedNumerator = 1;
        final int expectedDenominator = Integer.MIN_VALUE;
        final Fraction actual = Fraction.of(1, Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testConstructor_3_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Special cases.
        // removed other assertion
                final int expectedNumerator = 1;
        final int expectedDenominator = Integer.MIN_VALUE;
        final Fraction actual = Fraction.of(1, Integer.MIN_VALUE);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testConstructor_3_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Special cases.
        // removed other assertion
                final int expectedNumerator = 1;
        final int expectedDenominator = Integer.MIN_VALUE;
        final Fraction actual = Fraction.of(1, Integer.MIN_VALUE);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testConstructor_4_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Special cases.
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = -1;
        final int expectedDenominator = Integer.MIN_VALUE;
        final Fraction actual = Fraction.of(-1, Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testConstructor_4_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Special cases.
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = -1;
        final int expectedDenominator = Integer.MIN_VALUE;
        final Fraction actual = Fraction.of(-1, Integer.MIN_VALUE);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testConstructor_4_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Special cases.
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = -1;
        final int expectedDenominator = Integer.MIN_VALUE;
        final Fraction actual = Fraction.of(-1, Integer.MIN_VALUE);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testConstructor_5_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Special cases.
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 1;
        final int expectedDenominator = 1;
        final Fraction actual = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testConstructor_5_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Special cases.
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 1;
        final int expectedDenominator = 1;
        final Fraction actual = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testConstructor_5_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.numDenConstructorTestCases()) {
            // removed other assertion
        }

        // Special cases.
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 1;
        final int expectedDenominator = 1;
        final Fraction actual = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleConstructor_1_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = Fraction.from(testCase.operand);
            Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }
    }

    @Test
    void testDoubleConstructor_1_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = Fraction.from(testCase.operand);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }
    }

    @Test
    void testDoubleConstructor_1_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = Fraction.from(testCase.operand);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }
    }

    @Test
    void testDoubleConstructor_2_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
                final int expectedNumerator = 1;
        final int expectedDenominator = 3;
        final Fraction actual = Fraction.from(1.0 / 3.0);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testDoubleConstructor_2_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
                final int expectedNumerator = 1;
        final int expectedDenominator = 3;
        final Fraction actual = Fraction.from(1.0 / 3.0);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testDoubleConstructor_2_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
                final int expectedNumerator = 1;
        final int expectedDenominator = 3;
        final Fraction actual = Fraction.from(1.0 / 3.0);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleConstructor_3_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
                final int expectedNumerator = 17;
        final int expectedDenominator = 100;
        final Fraction actual = Fraction.from(17.0 / 100.0);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testDoubleConstructor_3_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
                final int expectedNumerator = 17;
        final int expectedDenominator = 100;
        final Fraction actual = Fraction.from(17.0 / 100.0);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testDoubleConstructor_3_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
                final int expectedNumerator = 17;
        final int expectedDenominator = 100;
        final Fraction actual = Fraction.from(17.0 / 100.0);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleConstructor_4_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 317;
        final int expectedDenominator = 100;
        final Fraction actual = Fraction.from(317.0 / 100.0);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testDoubleConstructor_4_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 317;
        final int expectedDenominator = 100;
        final Fraction actual = Fraction.from(317.0 / 100.0);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testDoubleConstructor_4_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 317;
        final int expectedDenominator = 100;
        final Fraction actual = Fraction.from(317.0 / 100.0);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleConstructor_5_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = -1;
        final int expectedDenominator = 3;
        final Fraction actual = Fraction.from(-1.0 / 3.0);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testDoubleConstructor_5_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = -1;
        final int expectedDenominator = 3;
        final Fraction actual = Fraction.from(-1.0 / 3.0);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testDoubleConstructor_5_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = -1;
        final int expectedDenominator = 3;
        final Fraction actual = Fraction.from(-1.0 / 3.0);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleConstructor_6_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = -17;
        final int expectedDenominator = 100;
        final Fraction actual = Fraction.from(17.0 / -100.0);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testDoubleConstructor_6_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = -17;
        final int expectedDenominator = 100;
        final Fraction actual = Fraction.from(17.0 / -100.0);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testDoubleConstructor_6_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = -17;
        final int expectedDenominator = 100;
        final Fraction actual = Fraction.from(17.0 / -100.0);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleConstructor_7_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = -317;
        final int expectedDenominator = 100;
        final Fraction actual = Fraction.from(-317.0 / 100.0);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testDoubleConstructor_7_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = -317;
        final int expectedDenominator = 100;
        final Fraction actual = Fraction.from(-317.0 / 100.0);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testDoubleConstructor_7_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = -317;
        final int expectedDenominator = 100;
        final Fraction actual = Fraction.from(-317.0 / 100.0);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_1_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = Fraction.from(testCase.operand, testCase.maxDenominator);
            Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_1_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = Fraction.from(testCase.operand, testCase.maxDenominator);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_1_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = Fraction.from(testCase.operand, testCase.maxDenominator);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_2_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = Fraction.from(Integer.MIN_VALUE * -1.0, 2);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_2_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = Fraction.from(Integer.MIN_VALUE * -1.0, 2);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_2_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = Fraction.from(Integer.MIN_VALUE * -1.0, 2);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_3_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -3;
        final Fraction actual = Fraction.from(Integer.MIN_VALUE / -3.0, 10);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_3_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -3;
        final Fraction actual = Fraction.from(Integer.MIN_VALUE / -3.0, 10);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_3_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -3;
        final Fraction actual = Fraction.from(Integer.MIN_VALUE / -3.0, 10);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_4_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 1;
        final int expectedDenominator = Integer.MIN_VALUE;
        final Fraction actual = Fraction.from(1.0 / Integer.MIN_VALUE, Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_4_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 1;
        final int expectedDenominator = Integer.MIN_VALUE;
        final Fraction actual = Fraction.from(1.0 / Integer.MIN_VALUE, Integer.MIN_VALUE);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_4_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 1;
        final int expectedDenominator = Integer.MIN_VALUE;
        final Fraction actual = Fraction.from(1.0 / Integer.MIN_VALUE, Integer.MIN_VALUE);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_5_oe_1_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = -1;
        final int expectedDenominator = Integer.MIN_VALUE;
        final Fraction actual = Fraction.from(-1.0 / Integer.MIN_VALUE, Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_5_oe_2_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = -1;
        final int expectedDenominator = Integer.MIN_VALUE;
        final Fraction actual = Fraction.from(-1.0 / Integer.MIN_VALUE, Integer.MIN_VALUE);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_5_oe_3_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = -1;
        final int expectedDenominator = Integer.MIN_VALUE;
        final Fraction actual = Fraction.from(-1.0 / Integer.MIN_VALUE, Integer.MIN_VALUE);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
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
                final int expectedNumerator = 1;
        final int expectedDenominator = 1;
        final Fraction actual = Fraction.from(1.0, 0, maxIterations);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
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
                final int expectedNumerator = 1;
        final int expectedDenominator = 1;
        final Fraction actual = Fraction.from(1.0, 0, maxIterations);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
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
                final int expectedNumerator = 1;
        final int expectedDenominator = 1;
        final Fraction actual = Fraction.from(1.0, 0, maxIterations);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleConstructorOverflow_1_oe_1_oe() {
                final double a = 0.75000000001455192;
        Assertions.assertThrows(ArithmeticException.class, () -> Fraction.from(a, 1.0e-12, 1000) );
    }

    @Test
    void testDoubleConstructorOverflow_2_oe_1_oe() {
        // removed other assertion
                final double a = 1.0e10;
        Assertions.assertThrows(ArithmeticException.class, () -> Fraction.from(a, 1.0e-12, 1000) );
    }

    @Test
    void testDoubleConstructorOverflow_3_oe_1_oe() {
        // removed other assertion
        // removed other assertion
                final double a = -1.0e10;
        Assertions.assertThrows(ArithmeticException.class, () -> Fraction.from(a, 1.0e-12, 1000) );
    }

    @Test
    void testDoubleConstructorOverflow_4_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double a = -43979.60679604749;
        Assertions.assertThrows(ArithmeticException.class, () -> Fraction.from(a, 1.0e-12, 1000) );
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_1_oe_1_oe() throws Exception  {
                final int expectedNumerator = 2;
        final int expectedDenominator = 5;
        final Fraction actual = Fraction.from(0.4, 1.0e-5, 100);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_1_oe_2_oe() throws Exception  {
                final int expectedNumerator = 2;
        final int expectedDenominator = 5;
        final Fraction actual = Fraction.from(0.4, 1.0e-5, 100);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_1_oe_3_oe() throws Exception  {
                final int expectedNumerator = 2;
        final int expectedDenominator = 5;
        final Fraction actual = Fraction.from(0.4, 1.0e-5, 100);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_2_oe_1_oe() throws Exception  {
        // removed other assertion

                final int expectedNumerator = 3;
        final int expectedDenominator = 5;
        final Fraction actual = Fraction.from(0.6152, 0.02, 100);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_2_oe_2_oe() throws Exception  {
        // removed other assertion

                final int expectedNumerator = 3;
        final int expectedDenominator = 5;
        final Fraction actual = Fraction.from(0.6152, 0.02, 100);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_2_oe_3_oe() throws Exception  {
        // removed other assertion

                final int expectedNumerator = 3;
        final int expectedDenominator = 5;
        final Fraction actual = Fraction.from(0.6152, 0.02, 100);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_3_oe_1_oe() throws Exception  {
        // removed other assertion

        // removed other assertion
                final int expectedNumerator = 8;
        final int expectedDenominator = 13;
        final Fraction actual = Fraction.from(0.6152, 1.0e-3, 100);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_3_oe_2_oe() throws Exception  {
        // removed other assertion

        // removed other assertion
                final int expectedNumerator = 8;
        final int expectedDenominator = 13;
        final Fraction actual = Fraction.from(0.6152, 1.0e-3, 100);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_3_oe_3_oe() throws Exception  {
        // removed other assertion

        // removed other assertion
                final int expectedNumerator = 8;
        final int expectedDenominator = 13;
        final Fraction actual = Fraction.from(0.6152, 1.0e-3, 100);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_4_oe_1_oe() throws Exception  {
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 251;
        final int expectedDenominator = 408;
        final Fraction actual = Fraction.from(0.6152, 1.0e-4, 100);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_4_oe_2_oe() throws Exception  {
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 251;
        final int expectedDenominator = 408;
        final Fraction actual = Fraction.from(0.6152, 1.0e-4, 100);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_4_oe_3_oe() throws Exception  {
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 251;
        final int expectedDenominator = 408;
        final Fraction actual = Fraction.from(0.6152, 1.0e-4, 100);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_5_oe_1_oe() throws Exception  {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 251;
        final int expectedDenominator = 408;
        final Fraction actual = Fraction.from(0.6152, 1.0e-5, 100);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_5_oe_2_oe() throws Exception  {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 251;
        final int expectedDenominator = 408;
        final Fraction actual = Fraction.from(0.6152, 1.0e-5, 100);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_5_oe_3_oe() throws Exception  {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 251;
        final int expectedDenominator = 408;
        final Fraction actual = Fraction.from(0.6152, 1.0e-5, 100);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_6_oe_1_oe() throws Exception  {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 510;
        final int expectedDenominator = 829;
        final Fraction actual = Fraction.from(0.6152, 1.0e-6, 100);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_6_oe_2_oe() throws Exception  {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 510;
        final int expectedDenominator = 829;
        final Fraction actual = Fraction.from(0.6152, 1.0e-6, 100);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_6_oe_3_oe() throws Exception  {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 510;
        final int expectedDenominator = 829;
        final Fraction actual = Fraction.from(0.6152, 1.0e-6, 100);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_7_oe_1_oe() throws Exception  {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 769;
        final int expectedDenominator = 1250;
        final Fraction actual = Fraction.from(0.6152, 1.0e-7, 100);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_7_oe_2_oe() throws Exception  {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 769;
        final int expectedDenominator = 1250;
        final Fraction actual = Fraction.from(0.6152, 1.0e-7, 100);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testDoubleConstructorWithEpsilonLimit_7_oe_3_oe() throws Exception  {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = 769;
        final int expectedDenominator = 1250;
        final Fraction actual = Fraction.from(0.6152, 1.0e-7, 100);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDoubleValue_1_oe_1_oe() {
                final double expected = 0.5;
        final int numerator = 1;
        final int denominator = 2;
        final Fraction f = Fraction.of(numerator, denominator);
                Assertions.assertEquals(expected, f.doubleValue());
    }

    @Test
    void testDoubleValue_2_oe_1_oe() {
        // removed other assertion
                final double expected = -0.5;
        final int numerator = -1;
        final int denominator = 2;
        final Fraction f = Fraction.of(numerator, denominator);
                Assertions.assertEquals(expected, f.doubleValue());
    }

    @Test
    void testDoubleValue_3_oe_1_oe() {
        // removed other assertion
        // removed other assertion
                final double expected = -0.5;
        final int numerator = 1;
        final int denominator = -2;
        final Fraction f = Fraction.of(numerator, denominator);
                Assertions.assertEquals(expected, f.doubleValue());
    }

    @Test
    void testDoubleValue_4_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double expected = 0.5;
        final int numerator = -1;
        final int denominator = -2;
        final Fraction f = Fraction.of(numerator, denominator);
                Assertions.assertEquals(expected, f.doubleValue());
    }

    @Test
    void testDoubleValue_5_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double expected = 1.0 / 3.0;
        final int numerator = 1;
        final int denominator = 3;
        final Fraction f = Fraction.of(numerator, denominator);
                Assertions.assertEquals(expected, f.doubleValue());
    }

    @Test
    void testAbs_1_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.absTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f.abs();
            Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }
    }

    @Test
    void testAbs_1_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.absTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f.abs();
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }
    }

    @Test
    void testAbs_1_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.absTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f.abs();
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }
    }

    @Test
    void testReciprocal_1_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.reciprocalTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f.reciprocal();
            Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }
    }

    @Test
    void testReciprocal_1_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.reciprocalTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f.reciprocal();
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }
    }

    @Test
    void testReciprocal_1_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.reciprocalTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f.reciprocal();
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }
    }

    @Test
    void testNegate_1_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f.negate();
            Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }
    }

    @Test
    void testNegate_1_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f.negate();
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }
    }

    @Test
    void testNegate_1_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f.negate();
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }
    }

    @Test
    void testNegate_2_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
            // removed other assertion
        }

        // Test special cases of negation that differ from BigFraction.
        final Fraction one = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
                final int expectedNumerator = -1;
        final int expectedDenominator = 1;
        final Fraction actual = one.negate();
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testNegate_2_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
            // removed other assertion
        }

        // Test special cases of negation that differ from BigFraction.
        final Fraction one = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
                final int expectedNumerator = -1;
        final int expectedDenominator = 1;
        final Fraction actual = one.negate();
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testNegate_2_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
            // removed other assertion
        }

        // Test special cases of negation that differ from BigFraction.
        final Fraction one = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
                final int expectedNumerator = -1;
        final int expectedDenominator = 1;
        final Fraction actual = one.negate();
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testNegate_3_oe_1_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
            // removed other assertion
        }

        // Test special cases of negation that differ from BigFraction.
        final Fraction one = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
        // removed other assertion
        // Special case where the negation of the numerator is not possible.
        final Fraction minValue = Fraction.of(Integer.MIN_VALUE, 1);
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = minValue.negate();
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testNegate_3_oe_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
            // removed other assertion
        }

        // Test special cases of negation that differ from BigFraction.
        final Fraction one = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
        // removed other assertion
        // Special case where the negation of the numerator is not possible.
        final Fraction minValue = Fraction.of(Integer.MIN_VALUE, 1);
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = minValue.negate();
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testNegate_3_oe_3_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.negateTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
            // removed other assertion
        }

        // Test special cases of negation that differ from BigFraction.
        final Fraction one = Fraction.of(Integer.MIN_VALUE, Integer.MIN_VALUE);
        // removed other assertion
        // Special case where the negation of the numerator is not possible.
        final Fraction minValue = Fraction.of(Integer.MIN_VALUE, 1);
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = minValue.negate();
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testAdd_1_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.add(f2);
            Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }
    }

    @Test
    void testAdd_1_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.add(f2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }
    }

    @Test
    void testAdd_1_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.add(f2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }
    }

    @Test
    void testAdd_2_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.add(i2);
            Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }
    }

    @Test
    void testAdd_2_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.add(i2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }
    }

    @Test
    void testAdd_2_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.add(i2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }
    }

    @Test
    void testAdd_6_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = Fraction.ZERO.add(Fraction.of(Integer.MIN_VALUE, -1));
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testAdd_6_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = Fraction.ZERO.add(Fraction.of(Integer.MIN_VALUE, -1));
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testAdd_6_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = Fraction.ZERO.add(Fraction.of(Integer.MIN_VALUE, -1));
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testAdd_7_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
        // removed other assertion
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = 1;
        final Fraction actual = Fraction.ZERO.add(Fraction.of(Integer.MIN_VALUE, 1));
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testAdd_7_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
        // removed other assertion
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = 1;
        final Fraction actual = Fraction.ZERO.add(Fraction.of(Integer.MIN_VALUE, 1));
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testAdd_7_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
        // removed other assertion
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = 1;
        final Fraction actual = Fraction.ZERO.add(Fraction.of(Integer.MIN_VALUE, 1));
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testAdd_8_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = 1;
        final Fraction actual = Fraction.ZERO.add(Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testAdd_8_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = 1;
        final Fraction actual = Fraction.ZERO.add(Integer.MIN_VALUE);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testAdd_8_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.addFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.addIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : addFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : addIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = 1;
        final Fraction actual = Fraction.ZERO.add(Integer.MIN_VALUE);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testDivide_1_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.divide(f2);
            Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }
    }

    @Test
    void testDivide_1_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.divide(f2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }
    }

    @Test
    void testDivide_1_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.divide(f2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }
    }

    @Test
    void testDivide_2_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.divide(i2);
            Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }
    }

    @Test
    void testDivide_2_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.divide(i2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }
    }

    @Test
    void testDivide_2_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.divide(i2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }
    }

    @Test
    void testMultiply_1_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.multiply(f2);
            Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }
    }

    @Test
    void testMultiply_1_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.multiply(f2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }
    }

    @Test
    void testMultiply_1_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.multiply(f2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }
    }

    @Test
    void testMultiply_2_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.multiply(i2);
            Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }
    }

    @Test
    void testMultiply_2_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.multiply(i2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }
    }

    @Test
    void testMultiply_2_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.multiply(i2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }
    }

    @Test
    void testPow_1_oe_1_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.pow(exponent);
            Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }
    }

    @Test
    void testPow_1_oe_2_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.pow(exponent);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }
    }

    @Test
    void testPow_1_oe_3_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.pow(exponent);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }
    }

    @Test
    void testSubtract_1_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.subtract(f2);
            Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }
    }

    @Test
    void testSubtract_1_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.subtract(f2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }
    }

    @Test
    void testSubtract_1_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.subtract(f2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }
    }

    @Test
    void testSubtract_2_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.subtract(i2);
            Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }
    }

    @Test
    void testSubtract_2_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.subtract(i2);
            // removed other assertion
                    Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }
    }

    @Test
    void testSubtract_2_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
                        final int expectedNumerator = testCase.expectedNumerator;
            final int expectedDenominator = testCase.expectedDenominator;
            final Fraction actual = f1.subtract(i2);
            // removed other assertion
                    // removed other assertion
                    Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }
    }

    @Test
    void testSubtract_6_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = 1;
        final Fraction actual = Fraction.ZERO.subtract(Fraction.of(Integer.MIN_VALUE, -1));
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testSubtract_6_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = 1;
        final Fraction actual = Fraction.ZERO.subtract(Fraction.of(Integer.MIN_VALUE, -1));
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testSubtract_6_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = 1;
        final Fraction actual = Fraction.ZERO.subtract(Fraction.of(Integer.MIN_VALUE, -1));
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testSubtract_7_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
        // removed other assertion
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = Fraction.ZERO.subtract(Fraction.of(Integer.MIN_VALUE, 1));
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testSubtract_7_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
        // removed other assertion
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = Fraction.ZERO.subtract(Fraction.of(Integer.MIN_VALUE, 1));
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testSubtract_7_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
        // removed other assertion
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = Fraction.ZERO.subtract(Fraction.of(Integer.MIN_VALUE, 1));
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testSubtract_8_oe_1_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = Fraction.ZERO.subtract(Integer.MIN_VALUE);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testSubtract_8_oe_2_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = Fraction.ZERO.subtract(Integer.MIN_VALUE);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testSubtract_8_oe_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.subtractFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.subtractIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : subtractFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : subtractIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // Edge case
        // removed other assertion
        // removed other assertion
                final int expectedNumerator = Integer.MIN_VALUE;
        final int expectedDenominator = -1;
        final Fraction actual = Fraction.ZERO.subtract(Integer.MIN_VALUE);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testEqualsAndHashCode_5_oe_1_oe() {
        final Fraction zero = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final Fraction zero2 = Fraction.of(0, 2);
                final Fraction f1 = zero;
        final Fraction f2 = zero2;
        Assertions.assertEquals(f1, f2);
    }

    @Test
    void testEqualsAndHashCode_5_oe_2_oe() {
        final Fraction zero = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final Fraction zero2 = Fraction.of(0, 2);
                final Fraction f1 = zero;
        final Fraction f2 = zero2;
        // removed other assertion
                Assertions.assertEquals(f1.hashCode(), f2.hashCode(), "Equal fractions have different hashCode");
    }

    @Test
    void testEqualsAndHashCode_5_oe_3_oe() {
        final Fraction zero = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final Fraction zero2 = Fraction.of(0, 2);
                final Fraction f1 = zero;
        final Fraction f2 = zero2;
        // removed other assertion
                // removed other assertion
                // Check the computation matches the result of Arrays.hashCode and the signum.
                // This is not mandated but is a recommendation.
                final int expected = f1.signum() *
                                     Arrays.hashCode(new int[] {Math.abs(f1.getNumerator()),
                                                                Math.abs(f1.getDenominator())});
                Assertions.assertEquals(expected, f1.hashCode(), "Hashcode not equal to using Arrays.hashCode");
    }

    @Test
    void testEqualsAndHashCode_8_oe_1_oe() {
        final Fraction zero = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final Fraction zero2 = Fraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final Fraction one = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
                        final Fraction f11 = f1;
            final Fraction f21 = f2;
            Assertions.assertEquals(f11, f21);
    }
    }

    @Test
    void testEqualsAndHashCode_8_oe_2_oe() {
        final Fraction zero = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final Fraction zero2 = Fraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final Fraction one = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
                        final Fraction f11 = f1;
            final Fraction f21 = f2;
            // removed other assertion
                    Assertions.assertEquals(f11.hashCode(), f21.hashCode(), "Equal fractions have different hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_8_oe_3_oe() {
        final Fraction zero = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final Fraction zero2 = Fraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final Fraction one = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
                        final Fraction f11 = f1;
            final Fraction f21 = f2;
            // removed other assertion
                    // removed other assertion
                    // Check the computation matches the result of Arrays.hashCode and the signum.
                    // This is not mandated but is a recommendation.
                    final int expected = f11.signum() *
                                         Arrays.hashCode(new int[] {Math.abs(f11.getNumerator()),
                                                                    Math.abs(f11.getDenominator())});
                    Assertions.assertEquals(expected, f11.hashCode(), "Hashcode not equal to using Arrays.hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_9_oe_1_oe() {
        final Fraction zero = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final Fraction zero2 = Fraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final Fraction one = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            // removed other assertion
                        final Fraction f11 = f2;
            final Fraction f21 = f1;
            Assertions.assertEquals(f11, f21);
    }
    }

    @Test
    void testEqualsAndHashCode_9_oe_2_oe() {
        final Fraction zero = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final Fraction zero2 = Fraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final Fraction one = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            // removed other assertion
                        final Fraction f11 = f2;
            final Fraction f21 = f1;
            // removed other assertion
                    Assertions.assertEquals(f11.hashCode(), f21.hashCode(), "Equal fractions have different hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_9_oe_3_oe() {
        final Fraction zero = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final Fraction zero2 = Fraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final Fraction one = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            // removed other assertion
                        final Fraction f11 = f2;
            final Fraction f21 = f1;
            // removed other assertion
                    // removed other assertion
                    // Check the computation matches the result of Arrays.hashCode and the signum.
                    // This is not mandated but is a recommendation.
                    final int expected = f11.signum() *
                                         Arrays.hashCode(new int[] {Math.abs(f11.getNumerator()),
                                                                    Math.abs(f11.getDenominator())});
                    Assertions.assertEquals(expected, f11.hashCode(), "Hashcode not equal to using Arrays.hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_10_oe_1_oe() {
        final Fraction zero = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final Fraction zero2 = Fraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final Fraction one = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            // removed other assertion
            // removed other assertion
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
                        final Fraction f11 = f1;
            final Fraction f21 = f2;
            Assertions.assertEquals(f11, f21);
    }
    }

    @Test
    void testEqualsAndHashCode_10_oe_2_oe() {
        final Fraction zero = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final Fraction zero2 = Fraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final Fraction one = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            // removed other assertion
            // removed other assertion
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
                        final Fraction f11 = f1;
            final Fraction f21 = f2;
            // removed other assertion
                    Assertions.assertEquals(f11.hashCode(), f21.hashCode(), "Equal fractions have different hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_10_oe_3_oe() {
        final Fraction zero = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final Fraction zero2 = Fraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final Fraction one = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            // removed other assertion
            // removed other assertion
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
                        final Fraction f11 = f1;
            final Fraction f21 = f2;
            // removed other assertion
                    // removed other assertion
                    // Check the computation matches the result of Arrays.hashCode and the signum.
                    // This is not mandated but is a recommendation.
                    final int expected = f11.signum() *
                                         Arrays.hashCode(new int[] {Math.abs(f11.getNumerator()),
                                                                    Math.abs(f11.getDenominator())});
                    Assertions.assertEquals(expected, f11.hashCode(), "Hashcode not equal to using Arrays.hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_11_oe_1_oe() {
        final Fraction zero = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final Fraction zero2 = Fraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final Fraction one = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            // removed other assertion
            // removed other assertion
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
            // removed other assertion
                        final Fraction f11 = f2;
            final Fraction f21 = f1;
            Assertions.assertEquals(f11, f21);
    }
    }

    @Test
    void testEqualsAndHashCode_11_oe_2_oe() {
        final Fraction zero = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final Fraction zero2 = Fraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final Fraction one = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            // removed other assertion
            // removed other assertion
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
            // removed other assertion
                        final Fraction f11 = f2;
            final Fraction f21 = f1;
            // removed other assertion
                    Assertions.assertEquals(f11.hashCode(), f21.hashCode(), "Equal fractions have different hashCode");
    }
    }

    @Test
    void testEqualsAndHashCode_11_oe_3_oe() {
        final Fraction zero = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Equal to same rational number
        final Fraction zero2 = Fraction.of(0, 2);
        // removed other assertion

        // Not equal to different rational number
        final Fraction one = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        // Test using different representations of the same fraction
        // (Denominators are primes)
        for (final int[] f : new int[][] {{1, 1}, {2, 3}, {6826, 15373}, {1373, 103813}, {0, 3}}) {
            final int num = f[0];
            final int den = f[1];
            Fraction f1 = Fraction.of(-num, den);
            Fraction f2 = Fraction.of(num, -den);
            // removed other assertion
            // removed other assertion
            f1 = Fraction.of(num, den);
            f2 = Fraction.of(-num, -den);
            // removed other assertion
                        final Fraction f11 = f2;
            final Fraction f21 = f1;
            // removed other assertion
                    // removed other assertion
                    // Check the computation matches the result of Arrays.hashCode and the signum.
                    // This is not mandated but is a recommendation.
                    final int expected = f11.signum() *
                                         Arrays.hashCode(new int[] {Math.abs(f11.getNumerator()),
                                                                    Math.abs(f11.getDenominator())});
                    Assertions.assertEquals(expected, f11.hashCode(), "Hashcode not equal to using Arrays.hashCode");
    }
    }

    @Test
    void testMath1261_1_oe_1_oe() {
        final Fraction a = Fraction.of(Integer.MAX_VALUE, 2);
                final int expectedNumerator = Integer.MAX_VALUE;
        final int expectedDenominator = 1;
        final Fraction actual = a.multiply(2);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testMath1261_1_oe_2_oe() {
        final Fraction a = Fraction.of(Integer.MAX_VALUE, 2);
                final int expectedNumerator = Integer.MAX_VALUE;
        final int expectedDenominator = 1;
        final Fraction actual = a.multiply(2);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testMath1261_1_oe_3_oe() {
        final Fraction a = Fraction.of(Integer.MAX_VALUE, 2);
                final int expectedNumerator = Integer.MAX_VALUE;
        final int expectedDenominator = 1;
        final Fraction actual = a.multiply(2);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

    @Test
    void testMath1261_2_oe_1_oe() {
        final Fraction a = Fraction.of(Integer.MAX_VALUE, 2);
        // removed other assertion

        final Fraction b = Fraction.of(2, Integer.MAX_VALUE);
                final int expectedNumerator = 1;
        final int expectedDenominator = Integer.MAX_VALUE;
        final Fraction actual = b.divide(2);
        Assertions.assertEquals(expectedNumerator, actual.getNumerator());
    }

    @Test
    void testMath1261_2_oe_2_oe() {
        final Fraction a = Fraction.of(Integer.MAX_VALUE, 2);
        // removed other assertion

        final Fraction b = Fraction.of(2, Integer.MAX_VALUE);
                final int expectedNumerator = 1;
        final int expectedDenominator = Integer.MAX_VALUE;
        final Fraction actual = b.divide(2);
        // removed other assertion
                Assertions.assertEquals(expectedDenominator, actual.getDenominator());
    }

    @Test
    void testMath1261_2_oe_3_oe() {
        final Fraction a = Fraction.of(Integer.MAX_VALUE, 2);
        // removed other assertion

        final Fraction b = Fraction.of(2, Integer.MAX_VALUE);
                final int expectedNumerator = 1;
        final int expectedDenominator = Integer.MAX_VALUE;
        final Fraction actual = b.divide(2);
        // removed other assertion
                // removed other assertion
                Assertions.assertEquals(Integer.signum(expectedNumerator)* Integer.signum(expectedDenominator),actual.signum());
    }

}
