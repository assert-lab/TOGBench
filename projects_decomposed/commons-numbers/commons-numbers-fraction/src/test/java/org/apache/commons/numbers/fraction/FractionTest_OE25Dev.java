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

    // MATH-179

    // MATH-181
    // NUMBERS-147

    // MATH-1029

    private void assertDoubleConstructorOverflow(final double a) {
        Assertions.assertThrows(ArithmeticException.class,
            () -> Fraction.from(a, 1.0e-12, 1000)
        );
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
            // removed other assertion
        }

        // Special cases.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Divide by zero
        try {
    Fraction.of(1, 0);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorWithMaxDenominator_6_oe() throws Exception  {
        for (final CommonTestCases.DoubleToFractionTestCase testCase : CommonTestCases.doubleMaxDenomConstructorTestCases()) {
            // removed other assertion
        }

        // Cases with different exact results from BigFraction
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    Fraction.from(1.0, 0);
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
    Fraction.from(value);
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
    Fraction.from(value, eps, maxIterations);
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
    Fraction.from(value, maxDenominator);
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
    Fraction.from(1.0, Double.NaN, maxIterations);
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
    Fraction.from(1.0, -1.0, maxIterations);
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
    Fraction.from(1.0, eps, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testDoubleConstructorGoldenRatioThrows_1_oe() {
        // the golden ratio is notoriously a difficult number for continuous fraction
        try {
    Fraction.from((1 + Math.sqrt(5)) / 2, 1.0e-12, 25);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorWithMaxDenominatorOverFlow_1_oe() {
        try {
    Fraction.from(1e10, 1000);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorWithMaxDenominatorOverFlow_2_oe() {
        // removed other assertion
        try {
    Fraction.from(-1e10, 1000);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testReciprocal_2_oe() {
        for (final CommonTestCases.UnaryOperatorTestCase testCase : CommonTestCases.reciprocalTestCases()) {
            final Fraction f = Fraction.of(testCase.operandNumerator, testCase.operandDenominator);
            // removed other assertion
        }

        final Fraction f = Fraction.of(0, 3);
        Assertions.assertThrows(ArithmeticException.class, f::reciprocal);
    }

    @Test
    void testAdd_3_oe() {
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
            try {
    f1.add(f2);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testAdd_4_oe() {
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
            try {
    f1.add(i2);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testAdd_5_oe() {
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

        try {
    Fraction.ONE.add((Fraction) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testDivide_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : divideByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            try {
    f1.divide(f2);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testDivide_4_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : divideByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : divideByIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            try {
    f1.divide(i2);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testDivide_5_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : divideByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : divideByIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        try {
    Fraction.ONE.divide((Fraction) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testDivide_6_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : divideByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : divideByIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        try {
    Fraction.of(1, 2).divide(Fraction.ZERO);
    org.junit.jupiter.api.Assertions.fail("FractionException");
} catch (FractionException e) {
}
    }

    @Test
    void testDivide_7_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.divideByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.divideByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : divideByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : divideByIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion

        // removed other assertion
        try {
    Fraction.of(1, 2).divide(0);
    org.junit.jupiter.api.Assertions.fail("FractionException");
} catch (FractionException e) {
}
    }

    @Test
    void testMultiply_3_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : multiplyByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            try {
    f1.multiply(f2);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testMultiply_4_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : multiplyByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : multiplyByIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            try {
    f1.multiply(i2);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testMultiply_5_oe() {
        for (final CommonTestCases.BinaryOperatorTestCase testCase : CommonTestCases.multiplyByFractionTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.multiplyByIntTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }
        for (final CommonTestCases.BinaryOperatorTestCase testCase : multiplyByFractionOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final Fraction f2 = Fraction.of(testCase.secondOperandNumerator, testCase.secondOperandDenominator);
            // removed other assertion
        }
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : multiplyByIntOverflowTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int i2 = testCase.secondOperand;
            // removed other assertion
        }

        try {
    Fraction.ONE.multiply((Fraction) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testPow_2_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
            // removed other assertion
        }

        try {
    Fraction.of(Integer.MAX_VALUE).pow(2);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testPow_3_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion
        try {
    Fraction.of(1, Integer.MAX_VALUE).pow(2);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testPow_4_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion
        try {
    Fraction.of(Integer.MAX_VALUE).pow(-2);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testPow_5_oe() {
        for (final CommonTestCases.BinaryIntOperatorTestCase testCase : CommonTestCases.powTestCases()) {
            final Fraction f1 = Fraction.of(testCase.firstOperandNumerator, testCase.firstOperandDenominator);
            final int exponent = testCase.secondOperand;
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Fraction.of(1, Integer.MAX_VALUE).pow(-2);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testSubtract_3_oe() {
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
            try {
    f1.subtract(f2);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testSubtract_4_oe() {
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
            try {
    f1.subtract(i2);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }
    }

    @Test
    void testSubtract_5_oe() {
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

        try {
    Fraction.ONE.add((Fraction) null);
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
            // removed other assertion
            inc++;
        }

        try {
    Fraction.parse("1 // 2");
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
            // removed other assertion
            inc++;
        }

        // removed other assertion
        try {
    Fraction.parse("1 / z");
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
            // removed other assertion
            inc++;
        }

        // removed other assertion
        // removed other assertion
        try {
    Fraction.parse("1 / --2");
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
            // removed other assertion
            inc++;
        }

        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Fraction.parse("x");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testNumbers150_1_oe() {
        // zero to negative powers should throw an exception
        try {
    Fraction.ZERO.pow(-1);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testNumbers150_2_oe() {
        // zero to negative powers should throw an exception
        // removed other assertion
        try {
    Fraction.ZERO.pow(Integer.MIN_VALUE);
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
        final Fraction f2 = Fraction.of(2);
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
        final Fraction f2 = Fraction.of(2);
        // removed other assertion
        final Fraction f12 = Fraction.of(1, 2);
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
    Fraction.from(a0, 1.0e-12, 1000);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDoubleConstructorOverflow_2_oe_1_oe() {
        // removed other assertion
                final double a0 = 1.0e10;
        try {
    Fraction.from(a0, 1.0e-12, 1000);
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
    Fraction.from(a0, 1.0e-12, 1000);
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
    Fraction.from(a0, 1.0e-12, 1000);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

}
