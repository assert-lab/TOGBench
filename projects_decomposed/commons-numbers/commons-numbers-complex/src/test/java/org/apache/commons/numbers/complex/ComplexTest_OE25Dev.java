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

package org.apache.commons.numbers.complex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.DoubleFunction;
import java.util.function.Supplier;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Complex}.
 *
 * <p>Note: The ISO C99 math functions are not fully tested in this class. See also:
 *
 * <ul>
 * <li>{@link CStandardTest} for a test of the ISO C99 standards including special case handling.
 * <li>{@link CReferenceTest} for a test of the output using standard finite value against an
 *     ISO C99 compliant reference implementation.
 * <li>{@link ComplexEdgeCaseTest} for a test of extreme edge case finite values for real and/or
 *     imaginary parts that can create intermediate overflow or underflow.
 * </ul>
 */
class ComplexTest_OE25Dev {

    private static final double inf = Double.POSITIVE_INFINITY;
    private static final double neginf = Double.NEGATIVE_INFINITY;
    private static final double nan = Double.NaN;
    private static final double pi = Math.PI;
    private static final Complex oneInf = Complex.ofCartesian(1, inf);
    private static final Complex oneNegInf = Complex.ofCartesian(1, neginf);
    private static final Complex infOne = Complex.ofCartesian(inf, 1);
    private static final Complex infZero = Complex.ofCartesian(inf, 0);
    private static final Complex infNegZero = Complex.ofCartesian(inf, -0.0);
    private static final Complex infNegInf = Complex.ofCartesian(inf, neginf);
    private static final Complex infInf = Complex.ofCartesian(inf, inf);
    private static final Complex negInfInf = Complex.ofCartesian(neginf, inf);
    private static final Complex negInfOne = Complex.ofCartesian(neginf, 1);
    private static final Complex negInfNegInf = Complex.ofCartesian(neginf, neginf);
    private static final Complex oneNan = Complex.ofCartesian(1, nan);
    private static final Complex zeroInf = Complex.ofCartesian(0, inf);
    private static final Complex zeroNan = Complex.ofCartesian(0, nan);
    private static final Complex nanZero = Complex.ofCartesian(nan, 0);
    private static final Complex NAN = Complex.ofCartesian(nan, nan);
    private static final Complex INF = Complex.ofCartesian(inf, inf);

    /**
     * Used to test the number category of a Complex.
     */
    private enum NumberType {
        NAN, INFINITE, FINITE
    }

    /**
     * Create a complex number given the real part.
     *
     * @param real Real part.
     * @return {@code Complex} object
     */
    private static Complex ofReal(double real) {
        return Complex.ofCartesian(real, 0);
    }

    /**
     * Create a complex number given the imaginary part.
     *
     * @param imaginary Imaginary part.
     * @return {@code Complex} object
     */
    private static Complex ofImaginary(double imaginary) {
        return Complex.ofCartesian(0, imaginary);
    }

    @Test
    @Disabled("Used to output the java environment")
    @SuppressWarnings("squid:S2699")
    void testJava() {
        // CHECKSTYLE: stop Regexp
        System.out.println(">>testJava()");
        // MathTest#testExpSpecialCases() checks the following:
        // Assert.assertEquals("exp of -infinity should be 0.0", 0.0,
        // Math.exp(Double.NEGATIVE_INFINITY), Precision.EPSILON);
        // Let's check how well Math works:
        System.out.println("Math.exp=" + Math.exp(Double.NEGATIVE_INFINITY));
        final String[] props = {"java.version", // Java Runtime Environment version
            "java.vendor", // Java Runtime Environment vendor
            "java.vm.specification.version", // Java Virtual Machine specification version
            "java.vm.specification.vendor", // Java Virtual Machine specification vendor
            "java.vm.specification.name", // Java Virtual Machine specification name
            "java.vm.version", // Java Virtual Machine implementation version
            "java.vm.vendor", // Java Virtual Machine implementation vendor
            "java.vm.name", // Java Virtual Machine implementation name
            "java.specification.version", // Java Runtime Environment specification
                                          // version
            "java.specification.vendor", // Java Runtime Environment specification vendor
            "java.specification.name", // Java Runtime Environment specification name
            "java.class.version", // Java class format version number
        };
        for (final String t : props) {
            System.out.println(t + "=" + System.getProperty(t));
        }
        System.out.println("<<testJava()");
        // CHECKSTYLE: resume Regexp
    }

    /**
     * Test parse and toString are compatible.
     */

    /**
     * Test standard values
     */

    /**
     * Verify atan2-style handling of infinite parts
     */

    /**
     * Verify that either part NaN results in NaN
     */

    private static void assertArgument(double expected, Complex complex, double delta) {
        final double actual = complex.arg();
        Assertions.assertEquals(expected, actual, delta);
        Assertions.assertEquals(actual, complex.arg(), delta);
    }

    /**
     * Test all number types: isNaN, isInfinite, isFinite.
     */

    /**
     * Assert the number type of the Complex created from the real and imaginary
     * components.
     *
     * @param real the real component
     * @param imaginary the imaginary component
     * @param type the type
     */
    private static void assertNumberType(double real, double imaginary, NumberType type) {
        final Complex z = Complex.ofCartesian(real, imaginary);
        final boolean isNaN = z.isNaN();
        final boolean isInfinite = z.isInfinite();
        final boolean isFinite = z.isFinite();
        // A number can be only one
        int count = isNaN ? 1 : 0;
        count += isInfinite ? 1 : 0;
        count += isFinite ? 1 : 0;
        Assertions.assertEquals(1,count,()-> String.format("Complex can be only one type: isNaN=%s,isInfinite=%s,isFinite=%s: %s",isNaN,isInfinite,isFinite,z));
        switch (type) {
        case FINITE:
            Assertions.assertTrue(isFinite, () -> "not finite: " + z);
            break;
        case INFINITE:
            Assertions.assertTrue(isInfinite, () -> "not infinite: " + z);
            break;
        case NAN:
            Assertions.assertTrue(isNaN, () -> "not nan: " + z);
            break;
        default:
            Assertions.fail("Unknown number type");
        }
    }

    /**
     * Arithmetic test using combinations of +/- x for real, imaginary and the double
     * argument for add, subtract, subtractFrom, multiply and divide, where x is zero or
     * non-zero.
     *
     * <p>The differences to the same argument as a Complex are tested. The only
     * differences should be the sign of zero in certain cases.
     */
    @Test
    void testSignedArithmetic() {
        // The following lists the conditions for the double primitive operation where
        // the Complex operation is different. Here the double argument can be:
        // x : any value
        // +x : positive
        // +0.0: positive zero
        // -x : negative
        // -0.0: negative zero
        // 0 : any zero
        // use y for any non-zero value

        // Check the known fail cases using an integer as a bit set.
        // If a bit is 1 then the case is known to fail.
        // The 64 cases are enumerated as:
        // 4 cases: (a,-0.0) operation on -0.0, 0.0, -2, 3
        // 4 cases: (a, 0.0) operation on -0.0, 0.0, -2, 3
        // 4 cases: (a,-2.0) operation on -0.0, 0.0, -2, 3
        // 4 cases: (a, 3.0) operation on -0.0, 0.0, -2, 3
        // with a in [-0.0, 0.0, -2, 3]
        // The least significant bit is for the first case.

        // The bit set was generated for this test. The summary below demonstrates
        // documenting the sign change cases for multiply and divide is non-trivial
        // and the javadoc in Complex does not break down the actual cases.

        // 16: (x,-0.0) + x
        assertSignedZeroArithmetic("addReal", Complex::add, ComplexTest_OE25Dev::ofReal, Complex::add,
            0b1111000000000000111100000000000011110000000000001111L);
        // 16: (-0.0,x) + x
        assertSignedZeroArithmetic("addImaginary", Complex::addImaginary, ComplexTest_OE25Dev::ofImaginary, Complex::add,
            0b1111111111111111L);
        // 0:
        assertSignedZeroArithmetic("subtractReal", Complex::subtract, ComplexTest_OE25Dev::ofReal, Complex::subtract, 0);
        // 0:
        assertSignedZeroArithmetic("subtractImaginary", Complex::subtractImaginary, ComplexTest_OE25Dev::ofImaginary,
            Complex::subtract, 0);
        // 16: x - (x,+0.0)
        assertSignedZeroArithmetic("subtractFromReal", Complex::subtractFrom, ComplexTest_OE25Dev::ofReal,
            (y, z) -> z.subtract(y), 0b11110000000000001111000000000000111100000000000011110000L);
        // 16: x - (+0.0,x)
        assertSignedZeroArithmetic("subtractFromImaginary", Complex::subtractFromImaginary, ComplexTest_OE25Dev::ofImaginary,
            (y, z) -> z.subtract(y), 0b11111111111111110000000000000000L);
        // 4: (-0.0,-x) * +x
        // 4: (+0.0,-0.0) * x
        // 4: (+0.0,x) * -x
        // 2: (-y,-x) * +0.0
        // 2: (+y,+0.0) * -x
        // 2: (+y,-0.0) * +x
        // 2: (+y,-x) * -0.0
        // 2: (+x,-y) * +0.0
        // 2: (+x,+y) * -0.0
        assertSignedZeroArithmetic("multiplyReal", Complex::multiply, ComplexTest_OE25Dev::ofReal, Complex::multiply,
            0b1001101011011000000100000001000010111010111110000101000001010L);
        // 4: (-0.0,+x) * +x
        // 2: (+0.0,-0.0) * -x
        // 4: (+0.0,+0.0) * x
        // 2: (+0.0,+y) * -x
        // 2: (-y,+x) * +0.0
        // 4: (+y,x) * -0.0
        // 2: (+0.0,+/-y) * -/+0
        // 2: (+y,+/-0.0) * +/-y (sign 0.0 matches sign y)
        // 2: (+y,+x) * +0.0
        assertSignedZeroArithmetic("multiplyImaginary", Complex::multiplyImaginary, ComplexTest_OE25Dev::ofImaginary,
            Complex::multiply, 0b11000110110101001000000010000001110001111101011010000010100000L);
        // 2: (-0.0,0) / +y
        // 2: (+0.0,+x) / -y
        // 2: (-x,0) / -y
        // 1: (-0.0,+y) / +y
        // 1: (-y,+0.0) / -y
        assertSignedZeroArithmetic("divideReal", Complex::divide, ComplexTest_OE25Dev::ofReal, Complex::divide,
            0b100100001000000010000001000000011001000L);

        // DivideImaginary has its own test as the result is not always equal ignoring the
        // sign.
    }

    private static void assertSignedZeroArithmetic(String name, BiFunction<Complex, Double, Complex> doubleOperation,
        DoubleFunction<Complex> doubleToComplex, BiFunction<Complex, Complex, Complex> complexOperation,
        long expectedFailures) {
        // With an operation on zero or non-zero arguments
        final double[] arguments = {-0.0, 0.0, -2, 3};
        for (final double a : arguments) {
            for (final double b : arguments) {
                final Complex c = Complex.ofCartesian(a, b);
                for (final double arg : arguments) {
                    final Complex y = doubleOperation.apply(c, arg);
                    final Complex z = complexOperation.apply(c, doubleToComplex.apply(arg));
                    final boolean expectedFailure = (expectedFailures & 0x1) == 1;
                    expectedFailures >>>= 1;
                    // Check the same answer. Sign is allowed to be different for zero.
                    Assertions.assertEquals(y.getReal(), z.getReal(), 0, () -> c + " " + name + " " + arg + ": real");
                    Assertions.assertEquals(y.getImaginary(),z.getImaginary(),0,()-> c + " " + name + " " + arg + ": imaginary");
                    Assertions.assertEquals(expectedFailure,!y.equals(z),()-> c + " " + name + " " + arg + ": sign-difference");
                }
            }
        }
    }

    /**
     * Arithmetic test using combinations of +/- x for real, imaginary and and the double
     * argument for divideImaginary, where x is zero or non-zero.
     *
     * <p>The differences to the same argument as a Complex are tested. This checks for
     * sign differences of zero or, if divide by zero, that the result is equal to divide
     * by zero using a Complex then multiplied by I.
     */

    private static void assertPowComplexZeroBase(double re, double im, Complex expected) {
        final Complex z = Complex.ofCartesian(re, im);
        final Complex c = Complex.ZERO.pow(z);
        Assertions.assertEquals(expected, c);
    }

    private static void assertPowScalarZeroBase(double exp, Complex expected) {
        final Complex c = Complex.ZERO.pow(exp);
        Assertions.assertEquals(expected, c);
    }

    /**
     * Test: computing <b>third roots</b> of z.
     *
     * <pre>
     * <code>
     * <b>z = -2 + 2 * i</b>
     *   => z_0 =  1      +          i
     *   => z_1 = -1.3660 + 0.3660 * i
     *   => z_2 =  0.3660 - 1.3660 * i
     * </code>
     * </pre>
     */

    /**
     * Test: computing <b>fourth roots</b> of z.
     *
     * <pre>
     * <code>
     * <b>z = 5 - 2 * i</b>
     *   => z_0 =  1.5164 - 0.1446 * i
     *   => z_1 =  0.1446 + 1.5164 * i
     *   => z_2 = -1.5164 + 0.1446 * i
     *   => z_3 = -1.5164 - 0.1446 * i
     * </code>
     * </pre>
     */

    /**
     * Test: computing <b>third roots</b> of z.
     *
     * <pre>
     * <code>
     * <b>z = 8</b>
     *   => z_0 =  2
     *   => z_1 = -1 + 1.73205 * i
     *   => z_2 = -1 - 1.73205 * i
     * </code>
     * </pre>
     */

    /**
     * Test: computing <b>third roots</b> of z with real part 0.
     *
     * <pre>
     * <code>
     * <b>z = 2 * i</b>
     *   => z_0 =  1.0911 + 0.6299 * i
     *   => z_1 = -1.0911 + 0.6299 * i
     *   => z_2 = -2.3144 - 1.2599 * i
     * </code>
     * </pre>
     */

    /**
     * Test: compute <b>third roots</b> using a negative argument to go clockwise around
     * the unit circle. Fourth roots of one are taken in both directions around the circle
     * using positive and negative arguments.
     *
     * <pre>
     * <code>
     * <b>z = 1</b>
     *   => z_0 = Positive: 1,0 ; Negative: 1,0
     *   => z_1 = Positive: 0,1 ; Negative: 0,-1
     *   => z_2 = Positive: -1,0 ; Negative: -1,0
     *   => z_3 = Positive: 0,-1 ; Negative: 0,1
     * </code>
     * </pre>
     */

    /**
     * Test {@link Complex#equals(Object)}. It should be consistent with
     * {@link Arrays#equals(double[], double[])} called using the components of two
     * complex numbers.
     */

    /**
     * Specific test to target different representations that return {@code true} for
     * {@link Complex#isNaN()} are {@code false} for {@link Complex#equals(Object)}.
     */

    /**
     * Test the two complex numbers with {@link Complex#equals(Object)} and check the
     * result is consistent with {@link Arrays#equals(double[], double[])}.
     *
     * @param c1 the first complex
     * @param c2 the second complex
     * @param msg the message to append to an assertion error
     */
    private static void assertEqualsIsConsistentWithArraysEquals(Complex c1, Complex c2, String msg) {
        final boolean expected = Arrays.equals(new double[] {c1.getReal(), c1.getImaginary()},
            new double[] {c2.getReal(), c2.getImaginary()});
        final boolean actual = c1.equals(c2);
        Assertions.assertEquals(expected,actual,()-> String.format("equals(Object)is not consistent with Arrays.equals: %s. %s vs %s",msg,c1,c2));
    }

    /**
     * Test {@link Complex#hashCode()}. It should be consistent with
     * {@link Arrays#hashCode(double[])} called using the components of the complex number
     * and fulfil the contract of {@link Object#hashCode()}, i.e. objects with different
     * hash codes are {@code false} for {@link Object#equals(Object)}.
     */

    /**
     * Specific test that different representations of zero satisfy the contract of
     * {@link Object#hashCode()}: if two objects have different hash codes, "equals" must
     * return false. This is an issue with using {@link Double#hashCode(double)} to create
     * hash codes and {@code ==} for equality when using different representations of
     * zero: Double.hashCode(-0.0) != Double.hashCode(0.0) but -0.0 == 0.0 is
     * {@code true}.
     *
     * @see <a
     * href="https://issues.apache.org/jira/projects/MATH/issues/MATH-1118">MATH-1118</a>
     */

    /**
     * Creates a list of Complex numbers using an all-vs-all combination of the provided
     * values for both the real and imaginary parts.
     *
     * @param values the values
     * @return the list
     */
    private static ArrayList<Complex> createCombinations(final double[] values) {
        final ArrayList<Complex> list = new ArrayList<>(values.length * values.length);
        for (final double re : values) {
            for (final double im : values) {
                list.add(Complex.ofCartesian(re, im));
            }
        }
        return list;
    }

    /**
     * Perform the smallest change to the value. This returns the next double value
     * adjacent to d in the direction of infinity. Edge cases: if already infinity then
     * return the next closest in the direction of negative infinity; if nan then return
     * 0.
     *
     * @param x the x
     * @return the new value
     */
    private static double smallestChange(double x) {
        if (Double.isNaN(x)) {
            return 0;
        }
        return x == Double.POSITIVE_INFINITY ? Math.nextDown(x) : Math.nextUp(x);
    }

    /**
     * Test that sin and cos are linear around zero. This can be used for fast computation
     * of sin and cos together when |x| is small.
     */

    /**
     * Test the abs and sqrt functions are consistent. The definition of sqrt uses abs and
     * the result should be computed using the same representation of the complex number's
     * magnitude (abs). If the sqrt function uses a simple representation
     * {@code sqrt(x^2 + y^2)} then this may have a 1 ulp or more difference from the high
     * accuracy result computed by abs. This will propagate to create differences in sqrt.
     *
     * <p>Note: This test is separated from the similar test for log to allow testing
     * different numbers.
     */

    private static void assertAbsVsSqrt(int samples, Supplier<Complex> supplier) {
        // Note: All methods implement scaling to ensure the magnitude can be computed.
        // Try very large or small numbers that will over/underflow to test that the
        // scaling
        // is consistent.
        for (int i = 0; i < samples; i++) {
            final Complex z = supplier.get();
            final double abs = z.abs();
            final double x = Math.abs(z.getReal());
            final double y = Math.abs(z.getImaginary());

            // Target the formula provided in the documentation for sqrt:
            // sqrt(x + iy)
            // t = sqrt( 2 (|x| + |x + iy|) )
            // if x >= 0: (t/2, y/t)
            // else : (|y| / t, t/2 * sgn(y))
            // Note this is not the definitional polar computation using absolute and
            // argument:
            // real = sqrt(|z|) * cos(0.5 * arg(z))
            // imag = sqrt(|z|) * sin(0.5 * arg(z))
            final Complex c = z.sqrt();
            final double t = Math.sqrt(2 * (x + abs));
            if (z.getReal() >= 0) {
                Assertions.assertEquals(t / 2, c.getReal());
                Assertions.assertEquals(z.getImaginary() / t, c.getImaginary());
            } else {
                Assertions.assertEquals(y / t, c.getReal());
                Assertions.assertEquals(Math.copySign(t / 2, z.getImaginary()), c.getImaginary());
            }
        }
    }

    /**
     * Test the abs and log functions are consistent. The definition of log uses abs and
     * the result should be computed using the same representation of the complex number's
     * magnitude (abs). If the log function uses a simple representation
     * {@code sqrt(x^2 + y^2)} then this may have a 1 ulp or more difference from the high
     * accuracy result computed by abs. This will propagate to create differences in log.
     *
     * <p>Note: This test is separated from the similar test for sqrt to allow testing
     * different numbers.
     */

    private static void assertAbsVsLog(int samples, Supplier<Complex> supplier) {
        // Note: All methods implement scaling to ensure the magnitude can be computed.
        // Try very large or small numbers that will over/underflow to test that the
        // scaling
        // is consistent.
        for (int i = 0; i < samples; i++) {
            final Complex z = supplier.get();
            final double abs = z.abs();
            final double x = Math.abs(z.getReal());
            final double y = Math.abs(z.getImaginary());

            // log(x + iy) = log(|x + i y|) + i arg(x + i y)
            // Only test the real component
            final Complex c = z.log();
            Assertions.assertEquals(Math.log(abs), c.getReal());
        }
    }

    /**
     * Creates a number in the range {@code [1, 2)} with up to 52-bits in the mantissa.
     * Then modifies the exponent by the given amount.
     *
     * @param rng Source of randomness
     * @param exponent Amount to change the exponent (in range [-1023, 1023])
     * @return the number
     */
    private static double createFixedExponentNumber(UniformRandomProvider rng, int exponent) {
        return Double.longBitsToDouble((rng.nextLong() >>> 12) | ((1023L + exponent) << 52));
    }

    @Test
    void testParseNull_1_oe() {
        try {
    Complex.parse(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testParseEmpty_1_oe() {
        try {
    Complex.parse("");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseEmpty_2_oe() {
        // removed other assertion
        try {
    Complex.parse(" ");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseWrongStart_1_oe() {
        try {
    Complex.parse("1.0,2.0)");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseWrongStart_2_oe() {
        // removed other assertion
        try {
    Complex.parse("[1.0,2.0)");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseWrongEnd_1_oe() {
        try {
    Complex.parse("(1.0,2.0");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseWrongEnd_2_oe() {
        // removed other assertion
        try {
    Complex.parse("(1.0,2.0]");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseWrongSeparator_1_oe() {
        try {
    Complex.parse("(1.0 2.0)");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseWrongSeparator_2_oe() {
        // removed other assertion
        try {
    Complex.parse("(1.0:2.0)");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseSeparatorOutsideStartAndEnd_1_oe() {
        try {
    Complex.parse("(1.0,2.0),");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseSeparatorOutsideStartAndEnd_2_oe() {
        // removed other assertion
        try {
    Complex.parse(",(1.0,2.0)");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseExtraSeparator_1_oe() {
        try {
    Complex.parse("(1.0,,2.0)");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseExtraSeparator_2_oe() {
        // removed other assertion
        try {
    Complex.parse("(1.0,2.0,)");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseExtraSeparator_3_oe() {
        // removed other assertion
        // removed other assertion
        try {
    Complex.parse("(,1.0,2.0)");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseExtraSeparator_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Complex.parse("(1.0,2,0)");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseInvalidRe_1_oe() {
        try {
    Complex.parse("(I.0,2.0)");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseInvalidIm_1_oe() {
        try {
    Complex.parse("(1.0,2.G)");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testZerothRootThrows_1_oe() {
        final Complex c = Complex.ofCartesian(1, 1);
        try {
    c.nthRoot(0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: zeroth root should not be allowed");
} catch (IllegalArgumentException e) {
}
    }

}
