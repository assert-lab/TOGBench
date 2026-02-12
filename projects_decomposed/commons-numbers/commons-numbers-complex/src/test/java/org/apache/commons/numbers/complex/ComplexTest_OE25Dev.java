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

import static org.junit.jupiter.api.Assertions.fail;

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
    void testCartesianConstructor_1_oe() {
        final Complex z = Complex.ofCartesian(3.0, 4.0);
        Assertions.assertEquals(3.0, z.getReal());
    }

    @Test
    void testCartesianConstructor_2_oe() {
        final Complex z = Complex.ofCartesian(3.0, 4.0);
        // removed other assertion
        Assertions.assertEquals(4.0, z.getImaginary());
    }

    @Test
    void testPolarConstructor_1_oe() {
        final double r = 98765;
        final double theta = 0.12345;
        final Complex z = Complex.ofPolar(r, theta);
        final Complex y = Complex.ofCis(theta);
        Assertions.assertEquals(r * y.getReal(), z.getReal());
    }

    @Test
    void testPolarConstructor_2_oe() {
        final double r = 98765;
        final double theta = 0.12345;
        final Complex z = Complex.ofPolar(r, theta);
        final Complex y = Complex.ofCis(theta);
        // removed other assertion
        Assertions.assertEquals(r * y.getImaginary(), z.getImaginary());
    }

    @Test
    void testPolarConstructor_3_oe() {
        final double r = 98765;
        final double theta = 0.12345;
        final Complex z = Complex.ofPolar(r, theta);
        final Complex y = Complex.ofCis(theta);
        // removed other assertion
        // removed other assertion

        // Edge cases
        // Non-finite theta
        Assertions.assertEquals(NAN, Complex.ofPolar(1, -inf));
    }

    @Test
    void testPolarConstructor_4_oe() {
        final double r = 98765;
        final double theta = 0.12345;
        final Complex z = Complex.ofPolar(r, theta);
        final Complex y = Complex.ofCis(theta);
        // removed other assertion
        // removed other assertion

        // Edge cases
        // Non-finite theta
        // removed other assertion
        Assertions.assertEquals(NAN, Complex.ofPolar(1, inf));
    }

    @Test
    void testPolarConstructor_5_oe() {
        final double r = 98765;
        final double theta = 0.12345;
        final Complex z = Complex.ofPolar(r, theta);
        final Complex y = Complex.ofCis(theta);
        // removed other assertion
        // removed other assertion

        // Edge cases
        // Non-finite theta
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(NAN, Complex.ofPolar(1, nan));
    }

    @Test
    void testPolarConstructor_6_oe() {
        final double r = 98765;
        final double theta = 0.12345;
        final Complex z = Complex.ofPolar(r, theta);
        final Complex y = Complex.ofCis(theta);
        // removed other assertion
        // removed other assertion

        // Edge cases
        // Non-finite theta
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Infinite rho is invalid when theta is NaN
        // i.e. do not create an infinite complex such as (inf, nan)
        Assertions.assertEquals(NAN, Complex.ofPolar(inf, nan));
    }

    @Test
    void testPolarConstructor_7_oe() {
        final double r = 98765;
        final double theta = 0.12345;
        final Complex z = Complex.ofPolar(r, theta);
        final Complex y = Complex.ofCis(theta);
        // removed other assertion
        // removed other assertion

        // Edge cases
        // Non-finite theta
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Infinite rho is invalid when theta is NaN
        // i.e. do not create an infinite complex such as (inf, nan)
        // removed other assertion
        // negative or NaN rho
        Assertions.assertEquals(NAN, Complex.ofPolar(-inf, 1));
    }

    @Test
    void testPolarConstructor_8_oe() {
        final double r = 98765;
        final double theta = 0.12345;
        final Complex z = Complex.ofPolar(r, theta);
        final Complex y = Complex.ofCis(theta);
        // removed other assertion
        // removed other assertion

        // Edge cases
        // Non-finite theta
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Infinite rho is invalid when theta is NaN
        // i.e. do not create an infinite complex such as (inf, nan)
        // removed other assertion
        // negative or NaN rho
        // removed other assertion
        Assertions.assertEquals(NAN, Complex.ofPolar(-0.0, 1));
    }

    @Test
    void testPolarConstructor_9_oe() {
        final double r = 98765;
        final double theta = 0.12345;
        final Complex z = Complex.ofPolar(r, theta);
        final Complex y = Complex.ofCis(theta);
        // removed other assertion
        // removed other assertion

        // Edge cases
        // Non-finite theta
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Infinite rho is invalid when theta is NaN
        // i.e. do not create an infinite complex such as (inf, nan)
        // removed other assertion
        // negative or NaN rho
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(NAN, Complex.ofPolar(nan, 1));
    }

    @Test
    void testPolarConstructor_10_oe() {
        final double r = 98765;
        final double theta = 0.12345;
        final Complex z = Complex.ofPolar(r, theta);
        final Complex y = Complex.ofCis(theta);
        // removed other assertion
        // removed other assertion

        // Edge cases
        // Non-finite theta
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Infinite rho is invalid when theta is NaN
        // i.e. do not create an infinite complex such as (inf, nan)
        // removed other assertion
        // negative or NaN rho
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Construction from infinity has values left to double arithmetic.
        // Test the examples from the javadoc
        Assertions.assertEquals(NAN, Complex.ofPolar(-0.0, 0.0));
    }

    @Test
    void testPolarConstructor_11_oe() {
        final double r = 98765;
        final double theta = 0.12345;
        final Complex z = Complex.ofPolar(r, theta);
        final Complex y = Complex.ofCis(theta);
        // removed other assertion
        // removed other assertion

        // Edge cases
        // Non-finite theta
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Infinite rho is invalid when theta is NaN
        // i.e. do not create an infinite complex such as (inf, nan)
        // removed other assertion
        // negative or NaN rho
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Construction from infinity has values left to double arithmetic.
        // Test the examples from the javadoc
        // removed other assertion
        Assertions.assertEquals(Complex.ofCartesian(0.0, 0.0), Complex.ofPolar(0.0, 0.0));
    }

    @Test
    void testPolarConstructor_12_oe() {
        final double r = 98765;
        final double theta = 0.12345;
        final Complex z = Complex.ofPolar(r, theta);
        final Complex y = Complex.ofCis(theta);
        // removed other assertion
        // removed other assertion

        // Edge cases
        // Non-finite theta
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Infinite rho is invalid when theta is NaN
        // i.e. do not create an infinite complex such as (inf, nan)
        // removed other assertion
        // negative or NaN rho
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Construction from infinity has values left to double arithmetic.
        // Test the examples from the javadoc
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Complex.ofCartesian(1.0, 0.0), Complex.ofPolar(1.0, 0.0));
    }

    @Test
    void testPolarConstructor_13_oe() {
        final double r = 98765;
        final double theta = 0.12345;
        final Complex z = Complex.ofPolar(r, theta);
        final Complex y = Complex.ofCis(theta);
        // removed other assertion
        // removed other assertion

        // Edge cases
        // Non-finite theta
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Infinite rho is invalid when theta is NaN
        // i.e. do not create an infinite complex such as (inf, nan)
        // removed other assertion
        // negative or NaN rho
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Construction from infinity has values left to double arithmetic.
        // Test the examples from the javadoc
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Complex.ofCartesian(-1.0, Math.sin(pi)), Complex.ofPolar(1.0, pi));
    }

    @Test
    void testPolarConstructor_14_oe() {
        final double r = 98765;
        final double theta = 0.12345;
        final Complex z = Complex.ofPolar(r, theta);
        final Complex y = Complex.ofCis(theta);
        // removed other assertion
        // removed other assertion

        // Edge cases
        // Non-finite theta
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Infinite rho is invalid when theta is NaN
        // i.e. do not create an infinite complex such as (inf, nan)
        // removed other assertion
        // negative or NaN rho
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Construction from infinity has values left to double arithmetic.
        // Test the examples from the javadoc
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Complex.ofCartesian(-inf, inf), Complex.ofPolar(inf, pi));
    }

    @Test
    void testPolarConstructor_15_oe() {
        final double r = 98765;
        final double theta = 0.12345;
        final Complex z = Complex.ofPolar(r, theta);
        final Complex y = Complex.ofCis(theta);
        // removed other assertion
        // removed other assertion

        // Edge cases
        // Non-finite theta
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Infinite rho is invalid when theta is NaN
        // i.e. do not create an infinite complex such as (inf, nan)
        // removed other assertion
        // negative or NaN rho
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Construction from infinity has values left to double arithmetic.
        // Test the examples from the javadoc
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Complex.ofCartesian(inf, nan), Complex.ofPolar(inf, 0.0));
    }

    @Test
    void testPolarConstructor_16_oe() {
        final double r = 98765;
        final double theta = 0.12345;
        final Complex z = Complex.ofPolar(r, theta);
        final Complex y = Complex.ofCis(theta);
        // removed other assertion
        // removed other assertion

        // Edge cases
        // Non-finite theta
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Infinite rho is invalid when theta is NaN
        // i.e. do not create an infinite complex such as (inf, nan)
        // removed other assertion
        // negative or NaN rho
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Construction from infinity has values left to double arithmetic.
        // Test the examples from the javadoc
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Complex.ofCartesian(inf, -inf), Complex.ofPolar(inf, -pi / 4));
    }

    @Test
    void testPolarConstructor_17_oe() {
        final double r = 98765;
        final double theta = 0.12345;
        final Complex z = Complex.ofPolar(r, theta);
        final Complex y = Complex.ofCis(theta);
        // removed other assertion
        // removed other assertion

        // Edge cases
        // Non-finite theta
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Infinite rho is invalid when theta is NaN
        // i.e. do not create an infinite complex such as (inf, nan)
        // removed other assertion
        // negative or NaN rho
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Construction from infinity has values left to double arithmetic.
        // Test the examples from the javadoc
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Complex.ofCartesian(-inf, -inf), Complex.ofPolar(inf, 5 * pi / 4));
    }

    @Test
    void testPolarConstructorAbsArg_1_oe() {
        // The test should work with any seed but use a fixed seed to avoid build
        // instability.
        final UniformRandomProvider rng = RandomSource.create(RandomSource.SPLIT_MIX_64, 678678638L);
        for (int i = 0; i < 10; i++) {
            final double rho = rng.nextDouble();
            // Range (pi, pi]: lower exclusive, upper inclusive
            final double theta = pi - rng.nextDouble() * 2 * pi;
            final Complex z = Complex.ofPolar(rho, theta);
            // Match within 1 ULP
            Assertions.assertEquals(rho, z.abs(), Math.ulp(rho));
    }
    }

    @Test
    void testPolarConstructorAbsArg_2_oe() {
        // The test should work with any seed but use a fixed seed to avoid build
        // instability.
        final UniformRandomProvider rng = RandomSource.create(RandomSource.SPLIT_MIX_64, 678678638L);
        for (int i = 0; i < 10; i++) {
            final double rho = rng.nextDouble();
            // Range (pi, pi]: lower exclusive, upper inclusive
            final double theta = pi - rng.nextDouble() * 2 * pi;
            final Complex z = Complex.ofPolar(rho, theta);
            // Match within 1 ULP
            // removed other assertion
            Assertions.assertEquals(theta, z.arg(), Math.ulp(theta));
    }
    }

    @Test
    void testCisConstructor_1_oe() {
        final double x = 0.12345;
        final Complex z = Complex.ofCis(x);
        Assertions.assertEquals(Math.cos(x), z.getReal());
    }

    @Test
    void testCisConstructor_2_oe() {
        final double x = 0.12345;
        final Complex z = Complex.ofCis(x);
        // removed other assertion
        Assertions.assertEquals(Math.sin(x), z.getImaginary());
    }

    @Test
    void testParseAndToString_1_oe() {
        final double[] parts = {Double.NEGATIVE_INFINITY, -1, -0.0, 0.0, 1, Math.PI, Double.POSITIVE_INFINITY,
            Double.NaN};
        for (final double x : parts) {
            for (final double y : parts) {
                final Complex z = Complex.ofCartesian(x, y);
                Assertions.assertEquals(z, Complex.parse(z.toString()));
    }
    }
    }

    @Test
    void testParseAndToString_2_oe() {
        final double[] parts = {Double.NEGATIVE_INFINITY, -1, -0.0, 0.0, 1, Math.PI, Double.POSITIVE_INFINITY,
            Double.NaN};
        for (final double x : parts) {
            for (final double y : parts) {
                final Complex z = Complex.ofCartesian(x, y);
                // removed other assertion
            }
        }
        final UniformRandomProvider rng = RandomSource.create(RandomSource.SPLIT_MIX_64);
        for (int i = 0; i < 10; i++) {
            final double x = -1 + rng.nextDouble() * 2;
            final double y = -1 + rng.nextDouble() * 2;
            final Complex z = Complex.ofCartesian(x, y);
            Assertions.assertEquals(z, Complex.parse(z.toString()));
    }
    }

    @Test
    void testParseAndToString_3_oe() {
        final double[] parts = {Double.NEGATIVE_INFINITY, -1, -0.0, 0.0, 1, Math.PI, Double.POSITIVE_INFINITY,
            Double.NaN};
        for (final double x : parts) {
            for (final double y : parts) {
                final Complex z = Complex.ofCartesian(x, y);
                // removed other assertion
            }
        }
        final UniformRandomProvider rng = RandomSource.create(RandomSource.SPLIT_MIX_64);
        for (int i = 0; i < 10; i++) {
            final double x = -1 + rng.nextDouble() * 2;
            final double y = -1 + rng.nextDouble() * 2;
            final Complex z = Complex.ofCartesian(x, y);
            // removed other assertion
        }

        // Special values not covered
        Assertions.assertEquals(Complex.ofPolar(2, pi), Complex.parse(Complex.ofPolar(2, pi).toString()));
    }

    @Test
    void testParseAndToString_4_oe() {
        final double[] parts = {Double.NEGATIVE_INFINITY, -1, -0.0, 0.0, 1, Math.PI, Double.POSITIVE_INFINITY,
            Double.NaN};
        for (final double x : parts) {
            for (final double y : parts) {
                final Complex z = Complex.ofCartesian(x, y);
                // removed other assertion
            }
        }
        final UniformRandomProvider rng = RandomSource.create(RandomSource.SPLIT_MIX_64);
        for (int i = 0; i < 10; i++) {
            final double x = -1 + rng.nextDouble() * 2;
            final double y = -1 + rng.nextDouble() * 2;
            final Complex z = Complex.ofCartesian(x, y);
            // removed other assertion
        }

        // Special values not covered
        // removed other assertion
        Assertions.assertEquals(Complex.ofCis(pi), Complex.parse(Complex.ofCis(pi).toString()));
    }

    @Test
    void testParseNull_1_oe() {
        try {
    Complex.parse(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testParseEmpty_1_oe() {
        try {
    Complex.parse("");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseEmpty_2_oe() {
        // removed other assertion
        try {
    Complex.parse(" ");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseWrongStart_1_oe() {
        try {
    Complex.parse("1.0,2.0)");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseWrongStart_2_oe() {
        // removed other assertion
        try {
    Complex.parse("[1.0,2.0)");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseWrongEnd_1_oe() {
        try {
    Complex.parse("(1.0,2.0");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseWrongEnd_2_oe() {
        // removed other assertion
        try {
    Complex.parse("(1.0,2.0]");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseWrongSeparator_1_oe() {
        try {
    Complex.parse("(1.0 2.0)");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseWrongSeparator_2_oe() {
        // removed other assertion
        try {
    Complex.parse("(1.0:2.0)");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseSeparatorOutsideStartAndEnd_1_oe() {
        try {
    Complex.parse("(1.0,2.0),");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseSeparatorOutsideStartAndEnd_2_oe() {
        // removed other assertion
        try {
    Complex.parse(",(1.0,2.0)");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseExtraSeparator_1_oe() {
        try {
    Complex.parse("(1.0,,2.0)");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseExtraSeparator_2_oe() {
        // removed other assertion
        try {
    Complex.parse("(1.0,2.0,)");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseExtraSeparator_3_oe() {
        // removed other assertion
        // removed other assertion
        try {
    Complex.parse("(,1.0,2.0)");
    fail("NumberFormatException");
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
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseInvalidRe_1_oe() {
        try {
    Complex.parse("(I.0,2.0)");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseInvalidIm_1_oe() {
        try {
    Complex.parse("(1.0,2.G)");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    void testParseSpaceAllowedAroundNumbers_1_oe() {
        final double re = 1.234;
        final double im = 5.678;
        final Complex z = Complex.ofCartesian(re, im);
        Assertions.assertEquals(z, Complex.parse("(" + re + "," + im + ")"));
    }

    @Test
    void testParseSpaceAllowedAroundNumbers_2_oe() {
        final double re = 1.234;
        final double im = 5.678;
        final Complex z = Complex.ofCartesian(re, im);
        // removed other assertion
        Assertions.assertEquals(z, Complex.parse("( " + re + "," + im + ")"));
    }

    @Test
    void testParseSpaceAllowedAroundNumbers_3_oe() {
        final double re = 1.234;
        final double im = 5.678;
        final Complex z = Complex.ofCartesian(re, im);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(z, Complex.parse("(" + re + " ," + im + ")"));
    }

    @Test
    void testParseSpaceAllowedAroundNumbers_4_oe() {
        final double re = 1.234;
        final double im = 5.678;
        final Complex z = Complex.ofCartesian(re, im);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(z, Complex.parse("(" + re + ", " + im + ")"));
    }

    @Test
    void testParseSpaceAllowedAroundNumbers_5_oe() {
        final double re = 1.234;
        final double im = 5.678;
        final Complex z = Complex.ofCartesian(re, im);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(z, Complex.parse("(" + re + "," + im + " )"));
    }

    @Test
    void testParseSpaceAllowedAroundNumbers_6_oe() {
        final double re = 1.234;
        final double im = 5.678;
        final Complex z = Complex.ofCartesian(re, im);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(z, Complex.parse("(  " + re + "  , " + im + "     )"));
    }

    @Test
    void testCGrammar_1_oe() {
        final UniformRandomProvider rng = RandomSource.create(RandomSource.SPLIT_MIX_64);
        for (int i = 0; i < 10; i++) {
            final Complex z = Complex.ofCartesian(rng.nextDouble(), rng.nextDouble());
            Assertions.assertEquals(z.getReal(), z.real(), "real");
    }
    }

    @Test
    void testCGrammar_2_oe() {
        final UniformRandomProvider rng = RandomSource.create(RandomSource.SPLIT_MIX_64);
        for (int i = 0; i < 10; i++) {
            final Complex z = Complex.ofCartesian(rng.nextDouble(), rng.nextDouble());
            // removed other assertion
            Assertions.assertEquals(z.getImaginary(), z.imag(), "imag");
    }
    }

    @Test
    void testAbs_1_oe() {
        final Complex z = Complex.ofCartesian(3.0, 4.0);
        Assertions.assertEquals(5.0, z.abs());
    }

    @Test
    void testAbsNaN_1_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        Assertions.assertEquals(nan, NAN.abs());
    }

    @Test
    void testAbsNaN_2_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        // removed other assertion
        Assertions.assertEquals(nan, Complex.ofCartesian(3.0, nan).abs());
    }

    @Test
    void testAbsNaN_3_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(nan, Complex.ofCartesian(nan, 3.0).abs());
    }

    @Test
    void testAbsNaN_4_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // The result is positive infinite if either argument is infinite
        Assertions.assertEquals(inf, Complex.ofCartesian(inf, nan).abs());
    }

    @Test
    void testAbsNaN_5_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // The result is positive infinite if either argument is infinite
        // removed other assertion
        Assertions.assertEquals(inf, Complex.ofCartesian(-inf, nan).abs());
    }

    @Test
    void testAbsNaN_6_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // The result is positive infinite if either argument is infinite
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(inf, Complex.ofCartesian(nan, inf).abs());
    }

    @Test
    void testAbsNaN_7_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // The result is positive infinite if either argument is infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(inf, Complex.ofCartesian(nan, -inf).abs());
    }

    @Test
    void testAbsNaN_8_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // The result is positive infinite if either argument is infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(inf, Complex.ofCartesian(inf, 3.0).abs());
    }

    @Test
    void testAbsNaN_9_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // The result is positive infinite if either argument is infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(inf, Complex.ofCartesian(-inf, 3.0).abs());
    }

    @Test
    void testAbsNaN_10_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // The result is positive infinite if either argument is infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(inf, Complex.ofCartesian(3.0, inf).abs());
    }

    @Test
    void testAbsNaN_11_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // The result is positive infinite if either argument is infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(inf, Complex.ofCartesian(3.0, -inf).abs());
    }

    @Test
    void testNorm_1_oe() {
        final Complex z = Complex.ofCartesian(3.0, 4.0);
        Assertions.assertEquals(25.0, z.norm());
    }

    @Test
    void testNormNaN_1_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        Assertions.assertEquals(nan, NAN.norm());
    }

    @Test
    void testNormNaN_2_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        // removed other assertion
        Assertions.assertEquals(nan, Complex.ofCartesian(3.0, nan).norm());
    }

    @Test
    void testNormNaN_3_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(nan, Complex.ofCartesian(nan, 3.0).norm());
    }

    @Test
    void testNormNaN_4_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // The result is positive infinite if either argument is infinite
        Assertions.assertEquals(inf, Complex.ofCartesian(inf, nan).norm());
    }

    @Test
    void testNormNaN_5_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // The result is positive infinite if either argument is infinite
        // removed other assertion
        Assertions.assertEquals(inf, Complex.ofCartesian(-inf, nan).norm());
    }

    @Test
    void testNormNaN_6_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // The result is positive infinite if either argument is infinite
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(inf, Complex.ofCartesian(nan, inf).norm());
    }

    @Test
    void testNormNaN_7_oe() {
        // The result is NaN if either argument is NaN and the other is not infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // The result is positive infinite if either argument is infinite
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(inf, Complex.ofCartesian(nan, -inf).norm());
    }

    @Test
    void testConjugate_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex z = x.conj();
        Assertions.assertEquals(3.0, z.getReal());
    }

    @Test
    void testConjugate_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex z = x.conj();
        // removed other assertion
        Assertions.assertEquals(-4.0, z.getImaginary());
    }

    @Test
    void testConjugateNaN_1_oe() {
        final Complex z = NAN.conj();
        Assertions.assertTrue(z.isNaN());
    }

    @Test
    void testConjugateInfinite_1_oe() {
        Complex z = Complex.ofCartesian(0, inf);
        Assertions.assertEquals(neginf, z.conj().getImaginary());
    }

    @Test
    void testConjugateInfinite_2_oe() {
        Complex z = Complex.ofCartesian(0, inf);
        // removed other assertion
        z = Complex.ofCartesian(0, neginf);
        Assertions.assertEquals(inf, z.conj().getImaginary());
    }

    @Test
    void testNegate_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex z = x.negate();
        Assertions.assertEquals(-3.0, z.getReal());
    }

    @Test
    void testNegate_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex z = x.negate();
        // removed other assertion
        Assertions.assertEquals(-4.0, z.getImaginary());
    }

    @Test
    void testNegateNaN_1_oe() {
        final Complex z = NAN.negate();
        Assertions.assertTrue(z.isNaN());
    }

    @Test
    void testProj_1_oe() {
        final Complex z = Complex.ofCartesian(3.0, 4.0);
        Assertions.assertSame(z, z.proj());
    }

    @Test
    void testProj_2_oe() {
        final Complex z = Complex.ofCartesian(3.0, 4.0);
        // removed other assertion
        // Sign must be the same for projection
        TestUtils.assertSame(infZero, Complex.ofCartesian(inf, 4.0).proj());
    }

    @Test
    void testProj_3_oe() {
        final Complex z = Complex.ofCartesian(3.0, 4.0);
        // removed other assertion
        // Sign must be the same for projection
        // removed other assertion
        TestUtils.assertSame(infZero, Complex.ofCartesian(inf, inf).proj());
    }

    @Test
    void testProj_4_oe() {
        final Complex z = Complex.ofCartesian(3.0, 4.0);
        // removed other assertion
        // Sign must be the same for projection
        // removed other assertion
        // removed other assertion
        TestUtils.assertSame(infZero, Complex.ofCartesian(inf, nan).proj());
    }

    @Test
    void testProj_5_oe() {
        final Complex z = Complex.ofCartesian(3.0, 4.0);
        // removed other assertion
        // Sign must be the same for projection
        // removed other assertion
        // removed other assertion
        // removed other assertion
        TestUtils.assertSame(infZero, Complex.ofCartesian(3.0, inf).proj());
    }

    @Test
    void testProj_6_oe() {
        final Complex z = Complex.ofCartesian(3.0, 4.0);
        // removed other assertion
        // Sign must be the same for projection
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        TestUtils.assertSame(infZero, Complex.ofCartesian(nan, inf).proj());
    }

    @Test
    void testProj_7_oe() {
        final Complex z = Complex.ofCartesian(3.0, 4.0);
        // removed other assertion
        // Sign must be the same for projection
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        TestUtils.assertSame(infNegZero, Complex.ofCartesian(inf, -4.0).proj());
    }

    @Test
    void testProj_8_oe() {
        final Complex z = Complex.ofCartesian(3.0, 4.0);
        // removed other assertion
        // Sign must be the same for projection
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        TestUtils.assertSame(infNegZero, Complex.ofCartesian(inf, -inf).proj());
    }

    @Test
    void testProj_9_oe() {
        final Complex z = Complex.ofCartesian(3.0, 4.0);
        // removed other assertion
        // Sign must be the same for projection
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        TestUtils.assertSame(infNegZero, Complex.ofCartesian(3.0, -inf).proj());
    }

    @Test
    void testProj_10_oe() {
        final Complex z = Complex.ofCartesian(3.0, 4.0);
        // removed other assertion
        // Sign must be the same for projection
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        TestUtils.assertSame(infNegZero, Complex.ofCartesian(nan, -inf).proj());
    }

    @Test
    void testAdd_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex y = Complex.ofCartesian(5.0, 6.0);
        final Complex z = x.add(y);
        Assertions.assertEquals(8.0, z.getReal());
    }

    @Test
    void testAdd_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex y = Complex.ofCartesian(5.0, 6.0);
        final Complex z = x.add(y);
        // removed other assertion
        Assertions.assertEquals(10.0, z.getImaginary());
    }

    @Test
    void testAddInf_1_oe() {
        Complex x = Complex.ofCartesian(1, 1);
        final Complex z = Complex.ofCartesian(inf, 0);
        final Complex w = x.add(z);
        Assertions.assertEquals(1, w.getImaginary());
    }

    @Test
    void testAddInf_2_oe() {
        Complex x = Complex.ofCartesian(1, 1);
        final Complex z = Complex.ofCartesian(inf, 0);
        final Complex w = x.add(z);
        // removed other assertion
        Assertions.assertEquals(inf, w.getReal());
    }

    @Test
    void testAddInf_3_oe() {
        Complex x = Complex.ofCartesian(1, 1);
        final Complex z = Complex.ofCartesian(inf, 0);
        final Complex w = x.add(z);
        // removed other assertion
        // removed other assertion

        x = Complex.ofCartesian(neginf, 0);
        Assertions.assertTrue(Double.isNaN(x.add(z).getReal()));
    }

    @Test
    void testAddReal_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.add(y);
        Assertions.assertEquals(8.0, z.getReal());
    }

    @Test
    void testAddReal_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.add(y);
        // removed other assertion
        Assertions.assertEquals(4.0, z.getImaginary());
    }

    @Test
    void testAddReal_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.add(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.add(ofReal(y)));
    }

    @Test
    void testAddRealNaN_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.add(y);
        Assertions.assertEquals(nan, z.getReal());
    }

    @Test
    void testAddRealNaN_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.add(y);
        // removed other assertion
        Assertions.assertEquals(4.0, z.getImaginary());
    }

    @Test
    void testAddRealNaN_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.add(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.add(ofReal(y)));
    }

    @Test
    void testAddRealInf_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.add(y);
        Assertions.assertEquals(inf, z.getReal());
    }

    @Test
    void testAddRealInf_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.add(y);
        // removed other assertion
        Assertions.assertEquals(4.0, z.getImaginary());
    }

    @Test
    void testAddRealInf_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.add(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.add(ofReal(y)));
    }

    @Test
    void testAddRealWithNegZeroImaginary_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, -0.0);
        final double y = 5.0;
        final Complex z = x.add(y);
        Assertions.assertEquals(8.0, z.getReal());
    }

    @Test
    void testAddRealWithNegZeroImaginary_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, -0.0);
        final double y = 5.0;
        final Complex z = x.add(y);
        // removed other assertion
        Assertions.assertEquals(-0.0, z.getImaginary(), "Expected sign preservation");
    }

    @Test
    void testAddRealWithNegZeroImaginary_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, -0.0);
        final double y = 5.0;
        final Complex z = x.add(y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem: -0.0 + 0.0 == 0.0
        final Complex z2 = x.add(ofReal(y));
        Assertions.assertEquals(8.0, z2.getReal());
    }

    @Test
    void testAddRealWithNegZeroImaginary_4_oe() {
        final Complex x = Complex.ofCartesian(3.0, -0.0);
        final double y = 5.0;
        final Complex z = x.add(y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem: -0.0 + 0.0 == 0.0
        final Complex z2 = x.add(ofReal(y));
        // removed other assertion
        Assertions.assertEquals(0.0, z2.getImaginary(), "Expected no-sign preservation");
    }

    @Test
    void testAddImaginary_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.addImaginary(y);
        Assertions.assertEquals(3.0, z.getReal());
    }

    @Test
    void testAddImaginary_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.addImaginary(y);
        // removed other assertion
        Assertions.assertEquals(9.0, z.getImaginary());
    }

    @Test
    void testAddImaginary_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.addImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.add(ofImaginary(y)));
    }

    @Test
    void testAddImaginaryNaN_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.addImaginary(y);
        Assertions.assertEquals(3.0, z.getReal());
    }

    @Test
    void testAddImaginaryNaN_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.addImaginary(y);
        // removed other assertion
        Assertions.assertEquals(nan, z.getImaginary());
    }

    @Test
    void testAddImaginaryNaN_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.addImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.add(ofImaginary(y)));
    }

    @Test
    void testAddImaginaryInf_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.addImaginary(y);
        Assertions.assertEquals(3.0, z.getReal());
    }

    @Test
    void testAddImaginaryInf_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.addImaginary(y);
        // removed other assertion
        Assertions.assertEquals(inf, z.getImaginary());
    }

    @Test
    void testAddImaginaryInf_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.addImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.add(ofImaginary(y)));
    }

    @Test
    void testAddImaginaryWithNegZeroReal_1_oe() {
        final Complex x = Complex.ofCartesian(-0.0, 4.0);
        final double y = 5.0;
        final Complex z = x.addImaginary(y);
        Assertions.assertEquals(-0.0, z.getReal(), "Expected sign preservation");
    }

    @Test
    void testAddImaginaryWithNegZeroReal_2_oe() {
        final Complex x = Complex.ofCartesian(-0.0, 4.0);
        final double y = 5.0;
        final Complex z = x.addImaginary(y);
        // removed other assertion
        Assertions.assertEquals(9.0, z.getImaginary());
    }

    @Test
    void testAddImaginaryWithNegZeroReal_3_oe() {
        final Complex x = Complex.ofCartesian(-0.0, 4.0);
        final double y = 5.0;
        final Complex z = x.addImaginary(y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem: -0.0 + 0.0 == 0.0
        final Complex z2 = x.add(ofImaginary(y));
        Assertions.assertEquals(0.0, z2.getReal(), "Expected no-sign preservation");
    }

    @Test
    void testAddImaginaryWithNegZeroReal_4_oe() {
        final Complex x = Complex.ofCartesian(-0.0, 4.0);
        final double y = 5.0;
        final Complex z = x.addImaginary(y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem: -0.0 + 0.0 == 0.0
        final Complex z2 = x.add(ofImaginary(y));
        // removed other assertion
        Assertions.assertEquals(9.0, z2.getImaginary());
    }

    @Test
    void testSubtract_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex y = Complex.ofCartesian(5.0, 7.0);
        final Complex z = x.subtract(y);
        Assertions.assertEquals(-2.0, z.getReal());
    }

    @Test
    void testSubtract_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex y = Complex.ofCartesian(5.0, 7.0);
        final Complex z = x.subtract(y);
        // removed other assertion
        Assertions.assertEquals(-3.0, z.getImaginary());
    }

    @Test
    void testSubtractInf_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex y = Complex.ofCartesian(inf, 7.0);
        Complex z = x.subtract(y);
        Assertions.assertEquals(neginf, z.getReal());
    }

    @Test
    void testSubtractInf_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex y = Complex.ofCartesian(inf, 7.0);
        Complex z = x.subtract(y);
        // removed other assertion
        Assertions.assertEquals(-3.0, z.getImaginary());
    }

    @Test
    void testSubtractInf_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex y = Complex.ofCartesian(inf, 7.0);
        Complex z = x.subtract(y);
        // removed other assertion
        // removed other assertion

        z = y.subtract(y);
        Assertions.assertEquals(nan, z.getReal());
    }

    @Test
    void testSubtractInf_4_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex y = Complex.ofCartesian(inf, 7.0);
        Complex z = x.subtract(y);
        // removed other assertion
        // removed other assertion

        z = y.subtract(y);
        // removed other assertion
        Assertions.assertEquals(0.0, z.getImaginary());
    }

    @Test
    void testSubtractReal_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtract(y);
        Assertions.assertEquals(-2.0, z.getReal());
    }

    @Test
    void testSubtractReal_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtract(y);
        // removed other assertion
        Assertions.assertEquals(4.0, z.getImaginary());
    }

    @Test
    void testSubtractReal_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtract(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.subtract(ofReal(y)));
    }

    @Test
    void testSubtractRealNaN_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.subtract(y);
        Assertions.assertEquals(nan, z.getReal());
    }

    @Test
    void testSubtractRealNaN_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.subtract(y);
        // removed other assertion
        Assertions.assertEquals(4.0, z.getImaginary());
    }

    @Test
    void testSubtractRealNaN_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.subtract(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.subtract(ofReal(y)));
    }

    @Test
    void testSubtractRealInf_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.subtract(y);
        Assertions.assertEquals(-inf, z.getReal());
    }

    @Test
    void testSubtractRealInf_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.subtract(y);
        // removed other assertion
        Assertions.assertEquals(4.0, z.getImaginary());
    }

    @Test
    void testSubtractRealInf_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.subtract(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.subtract(ofReal(y)));
    }

    @Test
    void testSubtractRealWithNegZeroImaginary_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, -0.0);
        final double y = 5.0;
        final Complex z = x.subtract(y);
        Assertions.assertEquals(-2.0, z.getReal());
    }

    @Test
    void testSubtractRealWithNegZeroImaginary_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, -0.0);
        final double y = 5.0;
        final Complex z = x.subtract(y);
        // removed other assertion
        Assertions.assertEquals(-0.0, z.getImaginary());
    }

    @Test
    void testSubtractRealWithNegZeroImaginary_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, -0.0);
        final double y = 5.0;
        final Complex z = x.subtract(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // Sign-preservation is not a problem: -0.0 - 0.0 == -0.0
        Assertions.assertEquals(z, x.subtract(ofReal(y)));
    }

    @Test
    void testSubtractImaginary_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtractImaginary(y);
        Assertions.assertEquals(3.0, z.getReal());
    }

    @Test
    void testSubtractImaginary_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtractImaginary(y);
        // removed other assertion
        Assertions.assertEquals(-1.0, z.getImaginary());
    }

    @Test
    void testSubtractImaginary_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtractImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.subtract(ofImaginary(y)));
    }

    @Test
    void testSubtractImaginaryNaN_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.subtractImaginary(y);
        Assertions.assertEquals(3.0, z.getReal());
    }

    @Test
    void testSubtractImaginaryNaN_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.subtractImaginary(y);
        // removed other assertion
        Assertions.assertEquals(nan, z.getImaginary());
    }

    @Test
    void testSubtractImaginaryNaN_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.subtractImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.subtract(ofImaginary(y)));
    }

    @Test
    void testSubtractImaginaryInf_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.subtractImaginary(y);
        Assertions.assertEquals(3.0, z.getReal());
    }

    @Test
    void testSubtractImaginaryInf_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.subtractImaginary(y);
        // removed other assertion
        Assertions.assertEquals(-inf, z.getImaginary());
    }

    @Test
    void testSubtractImaginaryInf_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.subtractImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.subtract(ofImaginary(y)));
    }

    @Test
    void testSubtractImaginaryWithNegZeroReal_1_oe() {
        final Complex x = Complex.ofCartesian(-0.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtractImaginary(y);
        Assertions.assertEquals(-0.0, z.getReal());
    }

    @Test
    void testSubtractImaginaryWithNegZeroReal_2_oe() {
        final Complex x = Complex.ofCartesian(-0.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtractImaginary(y);
        // removed other assertion
        Assertions.assertEquals(-1.0, z.getImaginary());
    }

    @Test
    void testSubtractImaginaryWithNegZeroReal_3_oe() {
        final Complex x = Complex.ofCartesian(-0.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtractImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // Sign-preservation is not a problem: -0.0 - 0.0 == -0.0
        Assertions.assertEquals(z, x.subtract(ofImaginary(y)));
    }

    @Test
    void testSubtractFromReal_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtractFrom(y);
        Assertions.assertEquals(2.0, z.getReal());
    }

    @Test
    void testSubtractFromReal_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtractFrom(y);
        // removed other assertion
        Assertions.assertEquals(-4.0, z.getImaginary());
    }

    @Test
    void testSubtractFromReal_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtractFrom(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, ofReal(y).subtract(x));
    }

    @Test
    void testSubtractFromRealNaN_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.subtractFrom(y);
        Assertions.assertEquals(nan, z.getReal());
    }

    @Test
    void testSubtractFromRealNaN_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.subtractFrom(y);
        // removed other assertion
        Assertions.assertEquals(-4.0, z.getImaginary());
    }

    @Test
    void testSubtractFromRealNaN_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.subtractFrom(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, ofReal(y).subtract(x));
    }

    @Test
    void testSubtractFromRealInf_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.subtractFrom(y);
        Assertions.assertEquals(inf, z.getReal());
    }

    @Test
    void testSubtractFromRealInf_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.subtractFrom(y);
        // removed other assertion
        Assertions.assertEquals(-4.0, z.getImaginary());
    }

    @Test
    void testSubtractFromRealInf_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.subtractFrom(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, ofReal(y).subtract(x));
    }

    @Test
    void testSubtractFromRealWithPosZeroImaginary_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 0.0);
        final double y = 5.0;
        final Complex z = x.subtractFrom(y);
        Assertions.assertEquals(2.0, z.getReal());
    }

    @Test
    void testSubtractFromRealWithPosZeroImaginary_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 0.0);
        final double y = 5.0;
        final Complex z = x.subtractFrom(y);
        // removed other assertion
        Assertions.assertEquals(-0.0, z.getImaginary(), "Expected sign inversion");
    }

    @Test
    void testSubtractFromRealWithPosZeroImaginary_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 0.0);
        final double y = 5.0;
        final Complex z = x.subtractFrom(y);
        // removed other assertion
        // removed other assertion
        // Sign-inversion is a problem: 0.0 - 0.0 == 0.0
        Assertions.assertNotEquals(z, ofReal(y).subtract(x));
    }

    @Test
    void testSubtractFromImaginary_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtractFromImaginary(y);
        Assertions.assertEquals(-3.0, z.getReal());
    }

    @Test
    void testSubtractFromImaginary_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtractFromImaginary(y);
        // removed other assertion
        Assertions.assertEquals(1.0, z.getImaginary());
    }

    @Test
    void testSubtractFromImaginary_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtractFromImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, ofImaginary(y).subtract(x));
    }

    @Test
    void testSubtractFromImaginaryNaN_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.subtractFromImaginary(y);
        Assertions.assertEquals(-3.0, z.getReal());
    }

    @Test
    void testSubtractFromImaginaryNaN_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.subtractFromImaginary(y);
        // removed other assertion
        Assertions.assertEquals(nan, z.getImaginary());
    }

    @Test
    void testSubtractFromImaginaryNaN_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.subtractFromImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, ofImaginary(y).subtract(x));
    }

    @Test
    void testSubtractFromImaginaryInf_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.subtractFromImaginary(y);
        Assertions.assertEquals(-3.0, z.getReal());
    }

    @Test
    void testSubtractFromImaginaryInf_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.subtractFromImaginary(y);
        // removed other assertion
        Assertions.assertEquals(inf, z.getImaginary());
    }

    @Test
    void testSubtractFromImaginaryInf_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        final Complex z = x.subtractFromImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, ofImaginary(y).subtract(x));
    }

    @Test
    void testSubtractFromImaginaryWithPosZeroReal_1_oe() {
        final Complex x = Complex.ofCartesian(0.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtractFromImaginary(y);
        Assertions.assertEquals(-0.0, z.getReal(), "Expected sign inversion");
    }

    @Test
    void testSubtractFromImaginaryWithPosZeroReal_2_oe() {
        final Complex x = Complex.ofCartesian(0.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtractFromImaginary(y);
        // removed other assertion
        Assertions.assertEquals(1.0, z.getImaginary());
    }

    @Test
    void testSubtractFromImaginaryWithPosZeroReal_3_oe() {
        final Complex x = Complex.ofCartesian(0.0, 4.0);
        final double y = 5.0;
        final Complex z = x.subtractFromImaginary(y);
        // removed other assertion
        // removed other assertion
        // Sign-inversion is a problem: 0.0 - 0.0 == 0.0
        Assertions.assertNotEquals(z, ofImaginary(y).subtract(x));
    }

    @Test
    void testMultiply_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex y = Complex.ofCartesian(5.0, 6.0);
        final Complex z = x.multiply(y);
        Assertions.assertEquals(-9.0, z.getReal());
    }

    @Test
    void testMultiply_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex y = Complex.ofCartesian(5.0, 6.0);
        final Complex z = x.multiply(y);
        // removed other assertion
        Assertions.assertEquals(38.0, z.getImaginary());
    }

    @Test
    void testMultiplyInfInf_1_oe() {
        final Complex z = infInf.multiply(infInf);
        // Assert.assertTrue(z.isNaN()); // MATH-620
        Assertions.assertTrue(z.isInfinite());
    }

    @Test
    void testMultiplyInfInf_2_oe() {
        final Complex z = infInf.multiply(infInf);
        // Assert.assertTrue(z.isNaN()); // MATH-620
        // removed other assertion

        // Expected results from g++:
        Assertions.assertEquals(Complex.ofCartesian(nan, inf), infInf.multiply(infInf));
    }

    @Test
    void testMultiplyInfInf_3_oe() {
        final Complex z = infInf.multiply(infInf);
        // Assert.assertTrue(z.isNaN()); // MATH-620
        // removed other assertion

        // Expected results from g++:
        // removed other assertion
        Assertions.assertEquals(Complex.ofCartesian(inf, nan), infInf.multiply(infNegInf));
    }

    @Test
    void testMultiplyInfInf_4_oe() {
        final Complex z = infInf.multiply(infInf);
        // Assert.assertTrue(z.isNaN()); // MATH-620
        // removed other assertion

        // Expected results from g++:
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Complex.ofCartesian(-inf, nan), infInf.multiply(negInfInf));
    }

    @Test
    void testMultiplyInfInf_5_oe() {
        final Complex z = infInf.multiply(infInf);
        // Assert.assertTrue(z.isNaN()); // MATH-620
        // removed other assertion

        // Expected results from g++:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Complex.ofCartesian(nan, -inf), infInf.multiply(negInfNegInf));
    }

    @Test
    void testMultiplyReal_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.multiply(y);
        Assertions.assertEquals(6.0, z.getReal());
    }

    @Test
    void testMultiplyReal_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.multiply(y);
        // removed other assertion
        Assertions.assertEquals(8.0, z.getImaginary());
    }

    @Test
    void testMultiplyReal_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.multiply(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.multiply(ofReal(y)));
    }

    @Test
    void testMultiplyReal_4_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.multiply(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.multiply(-y);
        Assertions.assertEquals(-6.0, z.getReal());
    }

    @Test
    void testMultiplyReal_5_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.multiply(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.multiply(-y);
        // removed other assertion
        Assertions.assertEquals(-8.0, z.getImaginary());
    }

    @Test
    void testMultiplyReal_6_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.multiply(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.multiply(-y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.multiply(ofReal(-y)));
    }

    @Test
    void testMultiplyRealNaN_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.multiply(y);
        Assertions.assertEquals(nan, z.getReal());
    }

    @Test
    void testMultiplyRealNaN_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.multiply(y);
        // removed other assertion
        Assertions.assertEquals(nan, z.getImaginary());
    }

    @Test
    void testMultiplyRealNaN_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.multiply(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.multiply(ofReal(y)));
    }

    @Test
    void testMultiplyRealInf_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.multiply(y);
        Assertions.assertEquals(inf, z.getReal());
    }

    @Test
    void testMultiplyRealInf_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.multiply(y);
        // removed other assertion
        Assertions.assertEquals(inf, z.getImaginary());
    }

    @Test
    void testMultiplyRealInf_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.multiply(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.multiply(ofReal(y)));
    }

    @Test
    void testMultiplyRealInf_4_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.multiply(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.multiply(-y);
        Assertions.assertEquals(-inf, z.getReal());
    }

    @Test
    void testMultiplyRealInf_5_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.multiply(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.multiply(-y);
        // removed other assertion
        Assertions.assertEquals(-inf, z.getImaginary());
    }

    @Test
    void testMultiplyRealInf_6_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.multiply(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.multiply(-y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.multiply(ofReal(-y)));
    }

    @Test
    void testMultiplyRealZero_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.multiply(y);
        Assertions.assertEquals(0.0, z.getReal());
    }

    @Test
    void testMultiplyRealZero_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.multiply(y);
        // removed other assertion
        Assertions.assertEquals(0.0, z.getImaginary());
    }

    @Test
    void testMultiplyRealZero_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.multiply(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.multiply(ofReal(y)));
    }

    @Test
    void testMultiplyRealZero_4_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.multiply(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.multiply(-y);
        Assertions.assertEquals(-0.0, z.getReal());
    }

    @Test
    void testMultiplyRealZero_5_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.multiply(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.multiply(-y);
        // removed other assertion
        Assertions.assertEquals(-0.0, z.getImaginary());
    }

    @Test
    void testMultiplyRealZero_6_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.multiply(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.multiply(-y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for imaginary: 0.0 - -0.0 == 0.0
        final Complex z2 = x.multiply(ofReal(-y));
        Assertions.assertEquals(-0.0, z2.getReal());
    }

    @Test
    void testMultiplyRealZero_7_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.multiply(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.multiply(-y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for imaginary: 0.0 - -0.0 == 0.0
        final Complex z2 = x.multiply(ofReal(-y));
        // removed other assertion
        Assertions.assertEquals(0.0, z2.getImaginary(), "Expected no sign preservation");
    }

    @Test
    void testMultiplyImaginary_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.multiplyImaginary(y);
        Assertions.assertEquals(-8.0, z.getReal());
    }

    @Test
    void testMultiplyImaginary_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.multiplyImaginary(y);
        // removed other assertion
        Assertions.assertEquals(6.0, z.getImaginary());
    }

    @Test
    void testMultiplyImaginary_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.multiplyImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.multiply(ofImaginary(y)));
    }

    @Test
    void testMultiplyImaginary_4_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.multiplyImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.multiplyImaginary(-y);
        Assertions.assertEquals(8.0, z.getReal());
    }

    @Test
    void testMultiplyImaginary_5_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.multiplyImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.multiplyImaginary(-y);
        // removed other assertion
        Assertions.assertEquals(-6.0, z.getImaginary());
    }

    @Test
    void testMultiplyImaginary_6_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.multiplyImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.multiplyImaginary(-y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.multiply(ofImaginary(-y)));
    }

    @Test
    void testMultiplyImaginaryNaN_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.multiplyImaginary(y);
        Assertions.assertEquals(nan, z.getReal());
    }

    @Test
    void testMultiplyImaginaryNaN_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.multiplyImaginary(y);
        // removed other assertion
        Assertions.assertEquals(nan, z.getImaginary());
    }

    @Test
    void testMultiplyImaginaryNaN_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.multiplyImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.multiply(ofImaginary(y)));
    }

    @Test
    void testMultiplyImaginaryInf_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.multiplyImaginary(y);
        Assertions.assertEquals(-inf, z.getReal());
    }

    @Test
    void testMultiplyImaginaryInf_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.multiplyImaginary(y);
        // removed other assertion
        Assertions.assertEquals(inf, z.getImaginary());
    }

    @Test
    void testMultiplyImaginaryInf_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.multiplyImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.multiply(ofImaginary(y)));
    }

    @Test
    void testMultiplyImaginaryInf_4_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.multiplyImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.multiplyImaginary(-y);
        Assertions.assertEquals(inf, z.getReal());
    }

    @Test
    void testMultiplyImaginaryInf_5_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.multiplyImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.multiplyImaginary(-y);
        // removed other assertion
        Assertions.assertEquals(-inf, z.getImaginary());
    }

    @Test
    void testMultiplyImaginaryInf_6_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.multiplyImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.multiplyImaginary(-y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.multiply(ofImaginary(-y)));
    }

    @Test
    void testMultiplyImaginaryZero_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.multiplyImaginary(y);
        Assertions.assertEquals(-0.0, z.getReal());
    }

    @Test
    void testMultiplyImaginaryZero_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.multiplyImaginary(y);
        // removed other assertion
        Assertions.assertEquals(0.0, z.getImaginary());
    }

    @Test
    void testMultiplyImaginaryZero_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.multiplyImaginary(y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for real: 0.0 - -0.0 == 0.0
        Complex z2 = x.multiply(ofImaginary(y));
        Assertions.assertEquals(0.0, z2.getReal(), "Expected no sign preservation");
    }

    @Test
    void testMultiplyImaginaryZero_4_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.multiplyImaginary(y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for real: 0.0 - -0.0 == 0.0
        Complex z2 = x.multiply(ofImaginary(y));
        // removed other assertion
        Assertions.assertEquals(0.0, z2.getImaginary());
    }

    @Test
    void testMultiplyImaginaryZero_5_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.multiplyImaginary(y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for real: 0.0 - -0.0 == 0.0
        Complex z2 = x.multiply(ofImaginary(y));
        // removed other assertion
        // removed other assertion

        z = x.multiplyImaginary(-y);
        Assertions.assertEquals(0.0, z.getReal());
    }

    @Test
    void testMultiplyImaginaryZero_6_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.multiplyImaginary(y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for real: 0.0 - -0.0 == 0.0
        Complex z2 = x.multiply(ofImaginary(y));
        // removed other assertion
        // removed other assertion

        z = x.multiplyImaginary(-y);
        // removed other assertion
        Assertions.assertEquals(-0.0, z.getImaginary());
    }

    @Test
    void testMultiplyImaginaryZero_7_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.multiplyImaginary(y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for real: 0.0 - -0.0 == 0.0
        Complex z2 = x.multiply(ofImaginary(y));
        // removed other assertion
        // removed other assertion

        z = x.multiplyImaginary(-y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for imaginary: -0.0 - 0.0 == 0.0
        z2 = x.multiply(ofImaginary(-y));
        Assertions.assertEquals(0.0, z2.getReal());
    }

    @Test
    void testMultiplyImaginaryZero_8_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.multiplyImaginary(y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for real: 0.0 - -0.0 == 0.0
        Complex z2 = x.multiply(ofImaginary(y));
        // removed other assertion
        // removed other assertion

        z = x.multiplyImaginary(-y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for imaginary: -0.0 - 0.0 == 0.0
        z2 = x.multiply(ofImaginary(-y));
        // removed other assertion
        Assertions.assertEquals(0.0, z2.getImaginary(), "Expected no sign preservation");
    }

    @Test
    void testNonZeroMultiplyI_1_oe() {
        final double[] parts = {3.0, 4.0};
        for (final double a : parts) {
            for (final double b : parts) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(1.0);
                // Check verses algebra solution
                Assertions.assertEquals(-b, x.getReal());
    }
    }
    }

    @Test
    void testNonZeroMultiplyI_2_oe() {
        final double[] parts = {3.0, 4.0};
        for (final double a : parts) {
            for (final double b : parts) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(1.0);
                // Check verses algebra solution
                // removed other assertion
                Assertions.assertEquals(a, x.getImaginary());
    }
    }
    }

    @Test
    void testNonZeroMultiplyI_3_oe() {
        final double[] parts = {3.0, 4.0};
        for (final double a : parts) {
            for (final double b : parts) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(1.0);
                // Check verses algebra solution
                // removed other assertion
                // removed other assertion
                final Complex z = c.multiply(Complex.I);
                Assertions.assertEquals(x, z);
    }
    }
    }

    @Test
    void testNonZeroMultiplyNegativeI_1_oe() {
        // This works no matter how you represent -I as a Complex
        final double[] parts = {3.0, 4.0};
        final Complex[] negIs = {Complex.ofCartesian(-0.0, -1), Complex.ofCartesian(0.0, -1)};
        for (final double a : parts) {
            for (final double b : parts) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(-1.0);
                // Check verses algebra solution
                Assertions.assertEquals(b, x.getReal());
    }
    }
    }

    @Test
    void testNonZeroMultiplyNegativeI_2_oe() {
        // This works no matter how you represent -I as a Complex
        final double[] parts = {3.0, 4.0};
        final Complex[] negIs = {Complex.ofCartesian(-0.0, -1), Complex.ofCartesian(0.0, -1)};
        for (final double a : parts) {
            for (final double b : parts) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(-1.0);
                // Check verses algebra solution
                // removed other assertion
                Assertions.assertEquals(-a, x.getImaginary());
    }
    }
    }

    @Test
    void testNonZeroMultiplyNegativeI_3_oe() {
        // This works no matter how you represent -I as a Complex
        final double[] parts = {3.0, 4.0};
        final Complex[] negIs = {Complex.ofCartesian(-0.0, -1), Complex.ofCartesian(0.0, -1)};
        for (final double a : parts) {
            for (final double b : parts) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(-1.0);
                // Check verses algebra solution
                // removed other assertion
                // removed other assertion
                for (final Complex negI : negIs) {
                    final Complex z = c.multiply(negI);
                    Assertions.assertEquals(x, z);
    }
    }
    }
    }

    @Test
    void testMultiplyZeroByI_1_oe() {
        final double[] zeros = {-0.0, 0.0};
        for (final double a : zeros) {
            for (final double b : zeros) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(1.0);
                // Check verses algebra solution
                Assertions.assertEquals(-b, x.getReal());
    }
    }
    }

    @Test
    void testMultiplyZeroByI_2_oe() {
        final double[] zeros = {-0.0, 0.0};
        for (final double a : zeros) {
            for (final double b : zeros) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(1.0);
                // Check verses algebra solution
                // removed other assertion
                Assertions.assertEquals(a, x.getImaginary());
    }
    }
    }

    @Test
    void testMultiplyZeroByI_3_oe() {
        final double[] zeros = {-0.0, 0.0};
        for (final double a : zeros) {
            for (final double b : zeros) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(1.0);
                // Check verses algebra solution
                // removed other assertion
                // removed other assertion
                final Complex z = c.multiply(Complex.I);
                // Does not work when imaginary part is +0.0.
                if (Double.compare(b, 0.0) == 0) {
                    // (-0.0, 0.0).multiply( (0,1) ) => (-0.0, 0.0) expected (-0.0,-0.0)
                    // ( 0.0, 0.0).multiply( (0,1) ) => ( 0.0, 0.0) expected (-0.0, 0.0)
                    // Sign is allowed to be different for zero.
                    Assertions.assertEquals(0, z.getReal(), 0.0);
    }
    }
    }
    }

    @Test
    void testMultiplyZeroByI_4_oe() {
        final double[] zeros = {-0.0, 0.0};
        for (final double a : zeros) {
            for (final double b : zeros) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(1.0);
                // Check verses algebra solution
                // removed other assertion
                // removed other assertion
                final Complex z = c.multiply(Complex.I);
                // Does not work when imaginary part is +0.0.
                if (Double.compare(b, 0.0) == 0) {
                    // (-0.0, 0.0).multiply( (0,1) ) => (-0.0, 0.0) expected (-0.0,-0.0)
                    // ( 0.0, 0.0).multiply( (0,1) ) => ( 0.0, 0.0) expected (-0.0, 0.0)
                    // Sign is allowed to be different for zero.
                    // removed other assertion
                    Assertions.assertEquals(0, z.getImaginary(), 0.0);
    }
    }
    }
    }

    @Test
    void testMultiplyZeroByI_5_oe() {
        final double[] zeros = {-0.0, 0.0};
        for (final double a : zeros) {
            for (final double b : zeros) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(1.0);
                // Check verses algebra solution
                // removed other assertion
                // removed other assertion
                final Complex z = c.multiply(Complex.I);
                // Does not work when imaginary part is +0.0.
                if (Double.compare(b, 0.0) == 0) {
                    // (-0.0, 0.0).multiply( (0,1) ) => (-0.0, 0.0) expected (-0.0,-0.0)
                    // ( 0.0, 0.0).multiply( (0,1) ) => ( 0.0, 0.0) expected (-0.0, 0.0)
                    // Sign is allowed to be different for zero.
                    // removed other assertion
                    // removed other assertion
                    Assertions.assertNotEquals(x, z);
    }
    }
    }
    }

    @Test
    void testMultiplyZeroByI_6_oe() {
        final double[] zeros = {-0.0, 0.0};
        for (final double a : zeros) {
            for (final double b : zeros) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(1.0);
                // Check verses algebra solution
                // removed other assertion
                // removed other assertion
                final Complex z = c.multiply(Complex.I);
                // Does not work when imaginary part is +0.0.
                if (Double.compare(b, 0.0) == 0) {
                    // (-0.0, 0.0).multiply( (0,1) ) => (-0.0, 0.0) expected (-0.0,-0.0)
                    // ( 0.0, 0.0).multiply( (0,1) ) => ( 0.0, 0.0) expected (-0.0, 0.0)
                    // Sign is allowed to be different for zero.
                    // removed other assertion
                    // removed other assertion
                    // removed other assertion
                } else {
                    Assertions.assertEquals(x, z);
    }
    }
    }
    }

    @Test
    void testMultiplyZeroByNegativeI_1_oe() {
        // Depending on how we represent -I this does not work for 2/4 cases
        // but the cases are different. Here we test the negation of I.
        final Complex negI = Complex.I.negate();
        final double[] zeros = {-0.0, 0.0};
        for (final double a : zeros) {
            for (final double b : zeros) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(-1.0);
                // Check verses algebra solution
                Assertions.assertEquals(b, x.getReal());
    }
    }
    }

    @Test
    void testMultiplyZeroByNegativeI_2_oe() {
        // Depending on how we represent -I this does not work for 2/4 cases
        // but the cases are different. Here we test the negation of I.
        final Complex negI = Complex.I.negate();
        final double[] zeros = {-0.0, 0.0};
        for (final double a : zeros) {
            for (final double b : zeros) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(-1.0);
                // Check verses algebra solution
                // removed other assertion
                Assertions.assertEquals(-a, x.getImaginary());
    }
    }
    }

    @Test
    void testMultiplyZeroByNegativeI_3_oe() {
        // Depending on how we represent -I this does not work for 2/4 cases
        // but the cases are different. Here we test the negation of I.
        final Complex negI = Complex.I.negate();
        final double[] zeros = {-0.0, 0.0};
        for (final double a : zeros) {
            for (final double b : zeros) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(-1.0);
                // Check verses algebra solution
                // removed other assertion
                // removed other assertion
                final Complex z = c.multiply(negI);
                final Complex z2 = c.multiply(Complex.I).negate();
                // Does not work when imaginary part is -0.0.
                if (Double.compare(b, -0.0) == 0) {
                    // (-0.0,-0.0).multiply( (-0.0,-1) ) => ( 0.0, 0.0) expected (-0.0, 0.0)
                    // ( 0.0,-0.0).multiply( (-0.0,-1) ) => (-0.0, 0.0) expected (-0.0,-0.0)
                    // Sign is allowed to be different for zero.
                    Assertions.assertEquals(0, z.getReal(), 0.0);
    }
    }
    }
    }

    @Test
    void testMultiplyZeroByNegativeI_4_oe() {
        // Depending on how we represent -I this does not work for 2/4 cases
        // but the cases are different. Here we test the negation of I.
        final Complex negI = Complex.I.negate();
        final double[] zeros = {-0.0, 0.0};
        for (final double a : zeros) {
            for (final double b : zeros) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(-1.0);
                // Check verses algebra solution
                // removed other assertion
                // removed other assertion
                final Complex z = c.multiply(negI);
                final Complex z2 = c.multiply(Complex.I).negate();
                // Does not work when imaginary part is -0.0.
                if (Double.compare(b, -0.0) == 0) {
                    // (-0.0,-0.0).multiply( (-0.0,-1) ) => ( 0.0, 0.0) expected (-0.0, 0.0)
                    // ( 0.0,-0.0).multiply( (-0.0,-1) ) => (-0.0, 0.0) expected (-0.0,-0.0)
                    // Sign is allowed to be different for zero.
                    // removed other assertion
                    Assertions.assertEquals(0, z.getImaginary(), 0.0);
    }
    }
    }
    }

    @Test
    void testMultiplyZeroByNegativeI_5_oe() {
        // Depending on how we represent -I this does not work for 2/4 cases
        // but the cases are different. Here we test the negation of I.
        final Complex negI = Complex.I.negate();
        final double[] zeros = {-0.0, 0.0};
        for (final double a : zeros) {
            for (final double b : zeros) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(-1.0);
                // Check verses algebra solution
                // removed other assertion
                // removed other assertion
                final Complex z = c.multiply(negI);
                final Complex z2 = c.multiply(Complex.I).negate();
                // Does not work when imaginary part is -0.0.
                if (Double.compare(b, -0.0) == 0) {
                    // (-0.0,-0.0).multiply( (-0.0,-1) ) => ( 0.0, 0.0) expected (-0.0, 0.0)
                    // ( 0.0,-0.0).multiply( (-0.0,-1) ) => (-0.0, 0.0) expected (-0.0,-0.0)
                    // Sign is allowed to be different for zero.
                    // removed other assertion
                    // removed other assertion
                    Assertions.assertNotEquals(x, z);
    }
    }
    }
    }

    @Test
    void testMultiplyZeroByNegativeI_6_oe() {
        // Depending on how we represent -I this does not work for 2/4 cases
        // but the cases are different. Here we test the negation of I.
        final Complex negI = Complex.I.negate();
        final double[] zeros = {-0.0, 0.0};
        for (final double a : zeros) {
            for (final double b : zeros) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(-1.0);
                // Check verses algebra solution
                // removed other assertion
                // removed other assertion
                final Complex z = c.multiply(negI);
                final Complex z2 = c.multiply(Complex.I).negate();
                // Does not work when imaginary part is -0.0.
                if (Double.compare(b, -0.0) == 0) {
                    // (-0.0,-0.0).multiply( (-0.0,-1) ) => ( 0.0, 0.0) expected (-0.0, 0.0)
                    // ( 0.0,-0.0).multiply( (-0.0,-1) ) => (-0.0, 0.0) expected (-0.0,-0.0)
                    // Sign is allowed to be different for zero.
                    // removed other assertion
                    // removed other assertion
                    // removed other assertion
                    // When multiply by I.negate() fails multiply by I then negate()
                    // works!
                    Assertions.assertEquals(x, z2);
    }
    }
    }
    }

    @Test
    void testMultiplyZeroByNegativeI_7_oe() {
        // Depending on how we represent -I this does not work for 2/4 cases
        // but the cases are different. Here we test the negation of I.
        final Complex negI = Complex.I.negate();
        final double[] zeros = {-0.0, 0.0};
        for (final double a : zeros) {
            for (final double b : zeros) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(-1.0);
                // Check verses algebra solution
                // removed other assertion
                // removed other assertion
                final Complex z = c.multiply(negI);
                final Complex z2 = c.multiply(Complex.I).negate();
                // Does not work when imaginary part is -0.0.
                if (Double.compare(b, -0.0) == 0) {
                    // (-0.0,-0.0).multiply( (-0.0,-1) ) => ( 0.0, 0.0) expected (-0.0, 0.0)
                    // ( 0.0,-0.0).multiply( (-0.0,-1) ) => (-0.0, 0.0) expected (-0.0,-0.0)
                    // Sign is allowed to be different for zero.
                    // removed other assertion
                    // removed other assertion
                    // removed other assertion
                    // When multiply by I.negate() fails multiply by I then negate()
                    // works!
                    // removed other assertion
                } else {
                    Assertions.assertEquals(x, z);
    }
    }
    }
    }

    @Test
    void testMultiplyZeroByNegativeI_8_oe() {
        // Depending on how we represent -I this does not work for 2/4 cases
        // but the cases are different. Here we test the negation of I.
        final Complex negI = Complex.I.negate();
        final double[] zeros = {-0.0, 0.0};
        for (final double a : zeros) {
            for (final double b : zeros) {
                final Complex c = Complex.ofCartesian(a, b);
                final Complex x = c.multiplyImaginary(-1.0);
                // Check verses algebra solution
                // removed other assertion
                // removed other assertion
                final Complex z = c.multiply(negI);
                final Complex z2 = c.multiply(Complex.I).negate();
                // Does not work when imaginary part is -0.0.
                if (Double.compare(b, -0.0) == 0) {
                    // (-0.0,-0.0).multiply( (-0.0,-1) ) => ( 0.0, 0.0) expected (-0.0, 0.0)
                    // ( 0.0,-0.0).multiply( (-0.0,-1) ) => (-0.0, 0.0) expected (-0.0,-0.0)
                    // Sign is allowed to be different for zero.
                    // removed other assertion
                    // removed other assertion
                    // removed other assertion
                    // When multiply by I.negate() fails multiply by I then negate()
                    // works!
                    // removed other assertion
                } else {
                    // removed other assertion
                    // When multiply by I.negate() works multiply by I then negate()
                    // fails!
                    Assertions.assertNotEquals(x, z2);
    }
    }
    }
    }

    @Test
    void testDivide_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex y = Complex.ofCartesian(5.0, 6.0);
        final Complex z = x.divide(y);
        Assertions.assertEquals(39.0 / 61.0, z.getReal());
    }

    @Test
    void testDivide_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex y = Complex.ofCartesian(5.0, 6.0);
        final Complex z = x.divide(y);
        // removed other assertion
        Assertions.assertEquals(2.0 / 61.0, z.getImaginary());
    }

    @Test
    void testDivideZero_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex z = x.divide(Complex.ZERO);
        Assertions.assertEquals(INF, z);
    }

    @Test
    void testDivideZeroZero_1_oe() {
        final Complex x = Complex.ofCartesian(0.0, 0.0);
        final Complex z = x.divide(Complex.ZERO);
        Assertions.assertEquals(NAN, z);
    }

    @Test
    void testDivideNaN_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex z = x.divide(NAN);
        Assertions.assertTrue(z.isNaN());
    }

    @Test
    void testDivideNanInf_1_oe() {
        Complex z = oneInf.divide(Complex.ONE);
        Assertions.assertTrue(Double.isNaN(z.getReal()));
    }

    @Test
    void testDivideNanInf_2_oe() {
        Complex z = oneInf.divide(Complex.ONE);
        // removed other assertion
        Assertions.assertEquals(inf, z.getImaginary());
    }

    @Test
    void testDivideNanInf_3_oe() {
        Complex z = oneInf.divide(Complex.ONE);
        // removed other assertion
        // removed other assertion

        z = negInfNegInf.divide(oneNan);
        Assertions.assertTrue(Double.isNaN(z.getReal()));
    }

    @Test
    void testDivideNanInf_4_oe() {
        Complex z = oneInf.divide(Complex.ONE);
        // removed other assertion
        // removed other assertion

        z = negInfNegInf.divide(oneNan);
        // removed other assertion
        Assertions.assertTrue(Double.isNaN(z.getImaginary()));
    }

    @Test
    void testDivideNanInf_5_oe() {
        Complex z = oneInf.divide(Complex.ONE);
        // removed other assertion
        // removed other assertion

        z = negInfNegInf.divide(oneNan);
        // removed other assertion
        // removed other assertion

        z = negInfInf.divide(Complex.ONE);
        Assertions.assertTrue(Double.isInfinite(z.getReal()));
    }

    @Test
    void testDivideNanInf_6_oe() {
        Complex z = oneInf.divide(Complex.ONE);
        // removed other assertion
        // removed other assertion

        z = negInfNegInf.divide(oneNan);
        // removed other assertion
        // removed other assertion

        z = negInfInf.divide(Complex.ONE);
        // removed other assertion
        Assertions.assertTrue(Double.isInfinite(z.getImaginary()));
    }

    @Test
    void testDivideReal_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.divide(y);
        Assertions.assertEquals(1.5, z.getReal());
    }

    @Test
    void testDivideReal_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.divide(y);
        // removed other assertion
        Assertions.assertEquals(2.0, z.getImaginary());
    }

    @Test
    void testDivideReal_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.divide(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.divide(ofReal(y)));
    }

    @Test
    void testDivideReal_4_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.divide(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.divide(-y);
        Assertions.assertEquals(-1.5, z.getReal());
    }

    @Test
    void testDivideReal_5_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.divide(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.divide(-y);
        // removed other assertion
        Assertions.assertEquals(-2.0, z.getImaginary());
    }

    @Test
    void testDivideReal_6_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.divide(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.divide(-y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.divide(ofReal(-y)));
    }

    @Test
    void testDivideRealNaN_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.divide(y);
        Assertions.assertEquals(nan, z.getReal());
    }

    @Test
    void testDivideRealNaN_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.divide(y);
        // removed other assertion
        Assertions.assertEquals(nan, z.getImaginary());
    }

    @Test
    void testDivideRealNaN_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.divide(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.divide(ofReal(y)));
    }

    @Test
    void testDivideRealInf_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.divide(y);
        Assertions.assertEquals(0.0, z.getReal());
    }

    @Test
    void testDivideRealInf_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.divide(y);
        // removed other assertion
        Assertions.assertEquals(0.0, z.getImaginary());
    }

    @Test
    void testDivideRealInf_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.divide(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.divide(ofReal(y)));
    }

    @Test
    void testDivideRealInf_4_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.divide(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.divide(-y);
        Assertions.assertEquals(-0.0, z.getReal());
    }

    @Test
    void testDivideRealInf_5_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.divide(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.divide(-y);
        // removed other assertion
        Assertions.assertEquals(-0.0, z.getImaginary());
    }

    @Test
    void testDivideRealInf_6_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.divide(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.divide(-y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.divide(ofReal(-y)));
    }

    @Test
    void testDivideRealZero_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.divide(y);
        Assertions.assertEquals(inf, z.getReal());
    }

    @Test
    void testDivideRealZero_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.divide(y);
        // removed other assertion
        Assertions.assertEquals(inf, z.getImaginary());
    }

    @Test
    void testDivideRealZero_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.divide(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.divide(ofReal(y)));
    }

    @Test
    void testDivideRealZero_4_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.divide(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.divide(-y);
        Assertions.assertEquals(-inf, z.getReal());
    }

    @Test
    void testDivideRealZero_5_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.divide(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.divide(-y);
        // removed other assertion
        Assertions.assertEquals(-inf, z.getImaginary());
    }

    @Test
    void testDivideRealZero_6_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.divide(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.divide(-y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.divide(ofReal(-y)));
    }

    @Test
    void testDivideImaginary_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.divideImaginary(y);
        Assertions.assertEquals(2.0, z.getReal());
    }

    @Test
    void testDivideImaginary_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.divideImaginary(y);
        // removed other assertion
        Assertions.assertEquals(-1.5, z.getImaginary());
    }

    @Test
    void testDivideImaginary_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.divideImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.divide(ofImaginary(y)));
    }

    @Test
    void testDivideImaginary_4_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.divideImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.divideImaginary(-y);
        Assertions.assertEquals(-2.0, z.getReal());
    }

    @Test
    void testDivideImaginary_5_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.divideImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.divideImaginary(-y);
        // removed other assertion
        Assertions.assertEquals(1.5, z.getImaginary());
    }

    @Test
    void testDivideImaginary_6_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 2.0;
        Complex z = x.divideImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.divideImaginary(-y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.divide(ofImaginary(-y)));
    }

    @Test
    void testDivideImaginaryNaN_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.divideImaginary(y);
        Assertions.assertEquals(nan, z.getReal());
    }

    @Test
    void testDivideImaginaryNaN_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.divideImaginary(y);
        // removed other assertion
        Assertions.assertEquals(nan, z.getImaginary());
    }

    @Test
    void testDivideImaginaryNaN_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = nan;
        final Complex z = x.divideImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.divide(ofImaginary(y)));
    }

    @Test
    void testDivideImaginaryInf_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.divideImaginary(y);
        Assertions.assertEquals(0.0, z.getReal());
    }

    @Test
    void testDivideImaginaryInf_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.divideImaginary(y);
        // removed other assertion
        Assertions.assertEquals(-0.0, z.getImaginary());
    }

    @Test
    void testDivideImaginaryInf_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.divideImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.divide(ofImaginary(y)));
    }

    @Test
    void testDivideImaginaryInf_4_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.divideImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.divideImaginary(-y);
        Assertions.assertEquals(-0.0, z.getReal());
    }

    @Test
    void testDivideImaginaryInf_5_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.divideImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.divideImaginary(-y);
        // removed other assertion
        Assertions.assertEquals(0.0, z.getImaginary());
    }

    @Test
    void testDivideImaginaryInf_6_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = inf;
        Complex z = x.divideImaginary(y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        // removed other assertion

        z = x.divideImaginary(-y);
        // removed other assertion
        // removed other assertion
        // Equivalent
        Assertions.assertEquals(z, x.divide(ofImaginary(-y)));
    }

    @Test
    void testDivideImaginaryZero_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.divideImaginary(y);
        Assertions.assertEquals(inf, z.getReal());
    }

    @Test
    void testDivideImaginaryZero_2_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.divideImaginary(y);
        // removed other assertion
        Assertions.assertEquals(-inf, z.getImaginary());
    }

    @Test
    void testDivideImaginaryZero_3_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.divideImaginary(y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for imaginary: 0.0 - -0.0 == 0.0
        Complex z2 = x.divide(ofImaginary(y));
        Assertions.assertEquals(inf, z2.getReal());
    }

    @Test
    void testDivideImaginaryZero_4_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.divideImaginary(y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for imaginary: 0.0 - -0.0 == 0.0
        Complex z2 = x.divide(ofImaginary(y));
        // removed other assertion
        Assertions.assertEquals(inf, z2.getImaginary(), "Expected no sign preservation");
    }

    @Test
    void testDivideImaginaryZero_5_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.divideImaginary(y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for imaginary: 0.0 - -0.0 == 0.0
        Complex z2 = x.divide(ofImaginary(y));
        // removed other assertion
        // removed other assertion

        z = x.divideImaginary(-y);
        Assertions.assertEquals(-inf, z.getReal());
    }

    @Test
    void testDivideImaginaryZero_6_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.divideImaginary(y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for imaginary: 0.0 - -0.0 == 0.0
        Complex z2 = x.divide(ofImaginary(y));
        // removed other assertion
        // removed other assertion

        z = x.divideImaginary(-y);
        // removed other assertion
        Assertions.assertEquals(inf, z.getImaginary());
    }

    @Test
    void testDivideImaginaryZero_7_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.divideImaginary(y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for imaginary: 0.0 - -0.0 == 0.0
        Complex z2 = x.divide(ofImaginary(y));
        // removed other assertion
        // removed other assertion

        z = x.divideImaginary(-y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for real: 0.0 + -0.0 == 0.0
        z2 = x.divide(ofImaginary(-y));
        Assertions.assertEquals(inf, z2.getReal(), "Expected no sign preservation");
    }

    @Test
    void testDivideImaginaryZero_8_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final double y = 0.0;
        Complex z = x.divideImaginary(y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for imaginary: 0.0 - -0.0 == 0.0
        Complex z2 = x.divide(ofImaginary(y));
        // removed other assertion
        // removed other assertion

        z = x.divideImaginary(-y);
        // removed other assertion
        // removed other assertion
        // Sign-preservation is a problem for real: 0.0 + -0.0 == 0.0
        z2 = x.divide(ofImaginary(-y));
        // removed other assertion
        Assertions.assertEquals(inf, z2.getImaginary());
    }

    @Test
    void testSignedDivideImaginaryArithmetic_1_oe() {
        // Cases for divide by non-zero:
        // 2: (-0.0,+x) / -y
        // 4: (+x,+/-0.0) / -/+y
        // 2: (+0.0,+x) / +y
        // Cases for divide by zero after multiplication of the Complex result by I:
        // 2: (-0.0,+/-y) / +0.0
        // 2: (+0.0,+/-y) / +0.0
        // 4: (-y,x) / +0.0
        // 4: (y,x) / +0.0
        // If multiplied by -I all the divide by -0.0 cases have sign errors and / +0.0 is
        // OK.
        long expectedFailures = 0b11001101111011001100110011001110110011110010000111001101000000L;
        // With an operation on zero or non-zero arguments
        final double[] arguments = {-0.0, 0.0, -2, 3};
        for (final double a : arguments) {
            for (final double b : arguments) {
                final Complex c = Complex.ofCartesian(a, b);
                for (final double arg : arguments) {
                    final Complex y = c.divideImaginary(arg);
                    Complex z = c.divide(ofImaginary(arg));
                    final boolean expectedFailure = (expectedFailures & 0x1) == 1;
                    expectedFailures >>>= 1;
                    // If divide by zero then the divide(Complex) method matches divide by real.
                    // To match divide by imaginary requires multiplication by I.
                    if (arg == 0) {
                        // Same result if multiplied by I. The sign may not match so
                        // optionally ignore the sign of the infinity.
                        z = z.multiplyImaginary(1);
                        final double ya = expectedFailure ? Math.abs(y.getReal()) : y.getReal();
                        final double yb = expectedFailure ? Math.abs(y.getImaginary()) : y.getImaginary();
                        final double za = expectedFailure ? Math.abs(z.getReal()) : z.getReal();
                        final double zb = expectedFailure ? Math.abs(z.getImaginary()) : z.getImaginary();
                        Assertions.assertEquals(ya, za, () -> c + " divideImaginary " + arg + ": real");
    }
    }
    }
    }
    }

    @Test
    void testSignedDivideImaginaryArithmetic_2_oe() {
        // Cases for divide by non-zero:
        // 2: (-0.0,+x) / -y
        // 4: (+x,+/-0.0) / -/+y
        // 2: (+0.0,+x) / +y
        // Cases for divide by zero after multiplication of the Complex result by I:
        // 2: (-0.0,+/-y) / +0.0
        // 2: (+0.0,+/-y) / +0.0
        // 4: (-y,x) / +0.0
        // 4: (y,x) / +0.0
        // If multiplied by -I all the divide by -0.0 cases have sign errors and / +0.0 is
        // OK.
        long expectedFailures = 0b11001101111011001100110011001110110011110010000111001101000000L;
        // With an operation on zero or non-zero arguments
        final double[] arguments = {-0.0, 0.0, -2, 3};
        for (final double a : arguments) {
            for (final double b : arguments) {
                final Complex c = Complex.ofCartesian(a, b);
                for (final double arg : arguments) {
                    final Complex y = c.divideImaginary(arg);
                    Complex z = c.divide(ofImaginary(arg));
                    final boolean expectedFailure = (expectedFailures & 0x1) == 1;
                    expectedFailures >>>= 1;
                    // If divide by zero then the divide(Complex) method matches divide by real.
                    // To match divide by imaginary requires multiplication by I.
                    if (arg == 0) {
                        // Same result if multiplied by I. The sign may not match so
                        // optionally ignore the sign of the infinity.
                        z = z.multiplyImaginary(1);
                        final double ya = expectedFailure ? Math.abs(y.getReal()) : y.getReal();
                        final double yb = expectedFailure ? Math.abs(y.getImaginary()) : y.getImaginary();
                        final double za = expectedFailure ? Math.abs(z.getReal()) : z.getReal();
                        final double zb = expectedFailure ? Math.abs(z.getImaginary()) : z.getImaginary();
                        // removed other assertion
                        Assertions.assertEquals(yb, zb, () -> c + " divideImaginary " + arg + ": imaginary");
    }
    }
    }
    }
    }

    @Test
    void testSignedDivideImaginaryArithmetic_3_oe() {
        // Cases for divide by non-zero:
        // 2: (-0.0,+x) / -y
        // 4: (+x,+/-0.0) / -/+y
        // 2: (+0.0,+x) / +y
        // Cases for divide by zero after multiplication of the Complex result by I:
        // 2: (-0.0,+/-y) / +0.0
        // 2: (+0.0,+/-y) / +0.0
        // 4: (-y,x) / +0.0
        // 4: (y,x) / +0.0
        // If multiplied by -I all the divide by -0.0 cases have sign errors and / +0.0 is
        // OK.
        long expectedFailures = 0b11001101111011001100110011001110110011110010000111001101000000L;
        // With an operation on zero or non-zero arguments
        final double[] arguments = {-0.0, 0.0, -2, 3};
        for (final double a : arguments) {
            for (final double b : arguments) {
                final Complex c = Complex.ofCartesian(a, b);
                for (final double arg : arguments) {
                    final Complex y = c.divideImaginary(arg);
                    Complex z = c.divide(ofImaginary(arg));
                    final boolean expectedFailure = (expectedFailures & 0x1) == 1;
                    expectedFailures >>>= 1;
                    // If divide by zero then the divide(Complex) method matches divide by real.
                    // To match divide by imaginary requires multiplication by I.
                    if (arg == 0) {
                        // Same result if multiplied by I. The sign may not match so
                        // optionally ignore the sign of the infinity.
                        z = z.multiplyImaginary(1);
                        final double ya = expectedFailure ? Math.abs(y.getReal()) : y.getReal();
                        final double yb = expectedFailure ? Math.abs(y.getImaginary()) : y.getImaginary();
                        final double za = expectedFailure ? Math.abs(z.getReal()) : z.getReal();
                        final double zb = expectedFailure ? Math.abs(z.getImaginary()) : z.getImaginary();
                        // removed other assertion
                        // removed other assertion
                    } else {
                        // Check the same answer. Sign is allowed to be different for zero.
                        Assertions.assertEquals(y.getReal(),z.getReal(),0,()-> c + " divideImaginary " + arg + ": real");
    }
    }
    }
    }
    }

    @Test
    void testSignedDivideImaginaryArithmetic_4_oe() {
        // Cases for divide by non-zero:
        // 2: (-0.0,+x) / -y
        // 4: (+x,+/-0.0) / -/+y
        // 2: (+0.0,+x) / +y
        // Cases for divide by zero after multiplication of the Complex result by I:
        // 2: (-0.0,+/-y) / +0.0
        // 2: (+0.0,+/-y) / +0.0
        // 4: (-y,x) / +0.0
        // 4: (y,x) / +0.0
        // If multiplied by -I all the divide by -0.0 cases have sign errors and / +0.0 is
        // OK.
        long expectedFailures = 0b11001101111011001100110011001110110011110010000111001101000000L;
        // With an operation on zero or non-zero arguments
        final double[] arguments = {-0.0, 0.0, -2, 3};
        for (final double a : arguments) {
            for (final double b : arguments) {
                final Complex c = Complex.ofCartesian(a, b);
                for (final double arg : arguments) {
                    final Complex y = c.divideImaginary(arg);
                    Complex z = c.divide(ofImaginary(arg));
                    final boolean expectedFailure = (expectedFailures & 0x1) == 1;
                    expectedFailures >>>= 1;
                    // If divide by zero then the divide(Complex) method matches divide by real.
                    // To match divide by imaginary requires multiplication by I.
                    if (arg == 0) {
                        // Same result if multiplied by I. The sign may not match so
                        // optionally ignore the sign of the infinity.
                        z = z.multiplyImaginary(1);
                        final double ya = expectedFailure ? Math.abs(y.getReal()) : y.getReal();
                        final double yb = expectedFailure ? Math.abs(y.getImaginary()) : y.getImaginary();
                        final double za = expectedFailure ? Math.abs(z.getReal()) : z.getReal();
                        final double zb = expectedFailure ? Math.abs(z.getImaginary()) : z.getImaginary();
                        // removed other assertion
                        // removed other assertion
                    } else {
                        // Check the same answer. Sign is allowed to be different for zero.
                        // removed other assertion
                        Assertions.assertEquals(y.getImaginary(),z.getImaginary(),0,()-> c + " divideImaginary " + arg + ": imaginary");
    }
    }
    }
    }
    }

    @Test
    void testSignedDivideImaginaryArithmetic_5_oe() {
        // Cases for divide by non-zero:
        // 2: (-0.0,+x) / -y
        // 4: (+x,+/-0.0) / -/+y
        // 2: (+0.0,+x) / +y
        // Cases for divide by zero after multiplication of the Complex result by I:
        // 2: (-0.0,+/-y) / +0.0
        // 2: (+0.0,+/-y) / +0.0
        // 4: (-y,x) / +0.0
        // 4: (y,x) / +0.0
        // If multiplied by -I all the divide by -0.0 cases have sign errors and / +0.0 is
        // OK.
        long expectedFailures = 0b11001101111011001100110011001110110011110010000111001101000000L;
        // With an operation on zero or non-zero arguments
        final double[] arguments = {-0.0, 0.0, -2, 3};
        for (final double a : arguments) {
            for (final double b : arguments) {
                final Complex c = Complex.ofCartesian(a, b);
                for (final double arg : arguments) {
                    final Complex y = c.divideImaginary(arg);
                    Complex z = c.divide(ofImaginary(arg));
                    final boolean expectedFailure = (expectedFailures & 0x1) == 1;
                    expectedFailures >>>= 1;
                    // If divide by zero then the divide(Complex) method matches divide by real.
                    // To match divide by imaginary requires multiplication by I.
                    if (arg == 0) {
                        // Same result if multiplied by I. The sign may not match so
                        // optionally ignore the sign of the infinity.
                        z = z.multiplyImaginary(1);
                        final double ya = expectedFailure ? Math.abs(y.getReal()) : y.getReal();
                        final double yb = expectedFailure ? Math.abs(y.getImaginary()) : y.getImaginary();
                        final double za = expectedFailure ? Math.abs(z.getReal()) : z.getReal();
                        final double zb = expectedFailure ? Math.abs(z.getImaginary()) : z.getImaginary();
                        // removed other assertion
                        // removed other assertion
                    } else {
                        // Check the same answer. Sign is allowed to be different for zero.
                        // removed other assertion
                        // removed other assertion
                        Assertions.assertEquals(expectedFailure,!y.equals(z),()-> c + " divideImaginary " + arg + ": sign-difference");
    }
    }
    }
    }
    }

    @Test
    void testLog10_1_oe() {
        final double ln10 = Math.log(10);
        final UniformRandomProvider rng = RandomSource.create(RandomSource.SPLIT_MIX_64);
        for (int i = 0; i < 10; i++) {
            final Complex z = Complex.ofCartesian(rng.nextDouble() * 2, rng.nextDouble() * 2);
            final Complex lnz = z.log();
            final Complex log10z = z.log10();
            // This is prone to floating-point error so use a delta
            Assertions.assertEquals(lnz.getReal() / ln10, log10z.getReal(), 1e-12, "real");
    }
    }

    @Test
    void testLog10_2_oe() {
        final double ln10 = Math.log(10);
        final UniformRandomProvider rng = RandomSource.create(RandomSource.SPLIT_MIX_64);
        for (int i = 0; i < 10; i++) {
            final Complex z = Complex.ofCartesian(rng.nextDouble() * 2, rng.nextDouble() * 2);
            final Complex lnz = z.log();
            final Complex log10z = z.log10();
            // This is prone to floating-point error so use a delta
            // removed other assertion
            // This test should be exact
            Assertions.assertEquals(lnz.getImaginary(), log10z.getImaginary(), "imag");
    }
    }

    @Test
    void testPow_1_oe() {
        final Complex x = Complex.ofCartesian(3, 4);
        final double yDouble = 5.0;
        final Complex yComplex = ofReal(yDouble);
        Assertions.assertEquals(x.pow(yComplex), x.pow(yDouble));
    }

    @Test
    void testPowComplexRealZero_1_oe() {
        // Hits the edge case when real == 0 but imaginary != 0
        final Complex x = Complex.ofCartesian(0, 1);
        final Complex z = Complex.ofCartesian(2, 3);
        final Complex c = x.pow(z);
        // Answer from g++
        Assertions.assertEquals(-0.008983291021129429, c.getReal());
    }

    @Test
    void testPowComplexRealZero_2_oe() {
        // Hits the edge case when real == 0 but imaginary != 0
        final Complex x = Complex.ofCartesian(0, 1);
        final Complex z = Complex.ofCartesian(2, 3);
        final Complex c = x.pow(z);
        // Answer from g++
        // removed other assertion
        Assertions.assertEquals(1.1001358594835313e-18, c.getImaginary());
    }

    @Test
    void testPowScalerRealZero_1_oe() {
        // Hits the edge case when real == 0 but imaginary != 0
        final Complex x = Complex.ofCartesian(0, 1);
        final Complex c = x.pow(2);
        // Answer from g++
        Assertions.assertEquals(-1, c.getReal());
    }

    @Test
    void testPowScalerRealZero_2_oe() {
        // Hits the edge case when real == 0 but imaginary != 0
        final Complex x = Complex.ofCartesian(0, 1);
        final Complex c = x.pow(2);
        // Answer from g++
        // removed other assertion
        Assertions.assertEquals(1.2246467991473532e-16, c.getImaginary());
    }

    @Test
    void testPowNanBase_1_oe() {
        final Complex x = NAN;
        final double yDouble = 5.0;
        final Complex yComplex = ofReal(yDouble);
        Assertions.assertEquals(x.pow(yComplex), x.pow(yDouble));
    }

    @Test
    void testPowNanExponent_1_oe() {
        final Complex x = Complex.ofCartesian(3, 4);
        final double yDouble = Double.NaN;
        final Complex yComplex = ofReal(yDouble);
        Assertions.assertEquals(x.pow(yComplex), x.pow(yDouble));
    }

    @Test
    void testSqrtPolar_1_oe() {
        final double tol = 1e-12;
        double r = 1;
        for (int i = 0; i < 5; i++) {
            r += i;
            double theta = 0;
            for (int j = 0; j < 11; j++) {
                theta += pi / 12;
                final Complex z = Complex.ofPolar(r, theta);
                final Complex sqrtz = Complex.ofPolar(Math.sqrt(r), theta / 2);
                TestUtils.assertEquals(sqrtz, z.sqrt(), tol);
    }
    }
    }

    @Test
    void testZerothRootThrows_1_oe() {
        final Complex c = Complex.ofCartesian(1, 1);
        try {
    c.nthRoot(0);
    fail("IllegalArgumentException: zeroth root should not be allowed");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testNthRootNormalThirdRoot_1_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(-2, 2);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        Assertions.assertEquals(3, thirdRootsOfZ.length);
    }

    @Test
    void testNthRootNormalThirdRoot_2_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(-2, 2);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        Assertions.assertEquals(1.0, thirdRootsOfZ[0].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootNormalThirdRoot_3_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(-2, 2);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        Assertions.assertEquals(1.0, thirdRootsOfZ[0].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootNormalThirdRoot_4_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(-2, 2);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        Assertions.assertEquals(-1.3660254037844386, thirdRootsOfZ[1].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootNormalThirdRoot_5_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(-2, 2);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        Assertions.assertEquals(0.36602540378443843, thirdRootsOfZ[1].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootNormalThirdRoot_6_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(-2, 2);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        Assertions.assertEquals(0.366025403784439, thirdRootsOfZ[2].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootNormalThirdRoot_7_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(-2, 2);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        Assertions.assertEquals(-1.3660254037844384, thirdRootsOfZ[2].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootNormalFourthRoot_1_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(5, -2);
        // The List holding all fourth roots
        final Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        Assertions.assertEquals(4, fourthRootsOfZ.length);
    }

    @Test
    void testNthRootNormalFourthRoot_2_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(5, -2);
        // The List holding all fourth roots
        final Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        Assertions.assertEquals(1.5164629308487783, fourthRootsOfZ[0].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootNormalFourthRoot_3_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(5, -2);
        // The List holding all fourth roots
        final Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        Assertions.assertEquals(-0.14469266210702247, fourthRootsOfZ[0].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootNormalFourthRoot_4_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(5, -2);
        // The List holding all fourth roots
        final Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        Assertions.assertEquals(0.14469266210702256, fourthRootsOfZ[1].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootNormalFourthRoot_5_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(5, -2);
        // The List holding all fourth roots
        final Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        Assertions.assertEquals(1.5164629308487783, fourthRootsOfZ[1].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootNormalFourthRoot_6_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(5, -2);
        // The List holding all fourth roots
        final Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        Assertions.assertEquals(-1.5164629308487783, fourthRootsOfZ[2].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootNormalFourthRoot_7_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(5, -2);
        // The List holding all fourth roots
        final Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        Assertions.assertEquals(0.14469266210702267, fourthRootsOfZ[2].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootNormalFourthRoot_8_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(5, -2);
        // The List holding all fourth roots
        final Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        // removed other assertion
        // test z_3
        Assertions.assertEquals(-0.14469266210702275, fourthRootsOfZ[3].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootNormalFourthRoot_9_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(5, -2);
        // The List holding all fourth roots
        final Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        // removed other assertion
        // test z_3
        // removed other assertion
        Assertions.assertEquals(-1.5164629308487783, fourthRootsOfZ[3].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootCornercaseThirdRootImaginaryPartEmpty_1_oe() {
        // The number 8 has three third roots. One we all already know is the number 2.
        // But there are two more complex roots.
        final Complex z = Complex.ofCartesian(8, 0);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        Assertions.assertEquals(3, thirdRootsOfZ.length);
    }

    @Test
    void testNthRootCornercaseThirdRootImaginaryPartEmpty_2_oe() {
        // The number 8 has three third roots. One we all already know is the number 2.
        // But there are two more complex roots.
        final Complex z = Complex.ofCartesian(8, 0);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        Assertions.assertEquals(2.0, thirdRootsOfZ[0].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootCornercaseThirdRootImaginaryPartEmpty_3_oe() {
        // The number 8 has three third roots. One we all already know is the number 2.
        // But there are two more complex roots.
        final Complex z = Complex.ofCartesian(8, 0);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        Assertions.assertEquals(0.0, thirdRootsOfZ[0].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootCornercaseThirdRootImaginaryPartEmpty_4_oe() {
        // The number 8 has three third roots. One we all already know is the number 2.
        // But there are two more complex roots.
        final Complex z = Complex.ofCartesian(8, 0);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        Assertions.assertEquals(-1.0, thirdRootsOfZ[1].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootCornercaseThirdRootImaginaryPartEmpty_5_oe() {
        // The number 8 has three third roots. One we all already know is the number 2.
        // But there are two more complex roots.
        final Complex z = Complex.ofCartesian(8, 0);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        Assertions.assertEquals(1.7320508075688774, thirdRootsOfZ[1].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootCornercaseThirdRootImaginaryPartEmpty_6_oe() {
        // The number 8 has three third roots. One we all already know is the number 2.
        // But there are two more complex roots.
        final Complex z = Complex.ofCartesian(8, 0);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        Assertions.assertEquals(-1.0, thirdRootsOfZ[2].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootCornercaseThirdRootImaginaryPartEmpty_7_oe() {
        // The number 8 has three third roots. One we all already know is the number 2.
        // But there are two more complex roots.
        final Complex z = Complex.ofCartesian(8, 0);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        Assertions.assertEquals(-1.732050807568877, thirdRootsOfZ[2].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootCornercaseThirdRootRealPartZero_1_oe() {
        // complex number with only imaginary part
        final Complex z = Complex.ofCartesian(0, 2);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        Assertions.assertEquals(3, thirdRootsOfZ.length);
    }

    @Test
    void testNthRootCornercaseThirdRootRealPartZero_2_oe() {
        // complex number with only imaginary part
        final Complex z = Complex.ofCartesian(0, 2);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        Assertions.assertEquals(1.0911236359717216, thirdRootsOfZ[0].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootCornercaseThirdRootRealPartZero_3_oe() {
        // complex number with only imaginary part
        final Complex z = Complex.ofCartesian(0, 2);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        Assertions.assertEquals(0.6299605249474365, thirdRootsOfZ[0].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootCornercaseThirdRootRealPartZero_4_oe() {
        // complex number with only imaginary part
        final Complex z = Complex.ofCartesian(0, 2);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        Assertions.assertEquals(-1.0911236359717216, thirdRootsOfZ[1].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootCornercaseThirdRootRealPartZero_5_oe() {
        // complex number with only imaginary part
        final Complex z = Complex.ofCartesian(0, 2);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        Assertions.assertEquals(0.6299605249474365, thirdRootsOfZ[1].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootCornercaseThirdRootRealPartZero_6_oe() {
        // complex number with only imaginary part
        final Complex z = Complex.ofCartesian(0, 2);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        Assertions.assertEquals(-2.3144374213981936E-16, thirdRootsOfZ[2].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootCornercaseThirdRootRealPartZero_7_oe() {
        // complex number with only imaginary part
        final Complex z = Complex.ofCartesian(0, 2);
        // The List holding all third roots
        final Complex[] thirdRootsOfZ = z.nthRoot(3).toArray(new Complex[0]);
        // Returned Collection must not be empty!
        // removed other assertion
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        Assertions.assertEquals(-1.2599210498948732, thirdRootsOfZ[2].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootNegativeArg_1_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(1, 0);
        // The List holding all fourth roots
        Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // test z_0
        Assertions.assertEquals(1, fourthRootsOfZ[0].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootNegativeArg_2_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(1, 0);
        // The List holding all fourth roots
        Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        Assertions.assertEquals(0, fourthRootsOfZ[0].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootNegativeArg_3_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(1, 0);
        // The List holding all fourth roots
        Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        Assertions.assertEquals(0, fourthRootsOfZ[1].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootNegativeArg_4_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(1, 0);
        // The List holding all fourth roots
        Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        Assertions.assertEquals(1, fourthRootsOfZ[1].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootNegativeArg_5_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(1, 0);
        // The List holding all fourth roots
        Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        Assertions.assertEquals(-1, fourthRootsOfZ[2].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootNegativeArg_6_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(1, 0);
        // The List holding all fourth roots
        Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        Assertions.assertEquals(0, fourthRootsOfZ[2].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootNegativeArg_7_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(1, 0);
        // The List holding all fourth roots
        Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        // removed other assertion
        // test z_3
        Assertions.assertEquals(0, fourthRootsOfZ[3].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootNegativeArg_8_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(1, 0);
        // The List holding all fourth roots
        Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        // removed other assertion
        // test z_3
        // removed other assertion
        Assertions.assertEquals(-1, fourthRootsOfZ[3].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootNegativeArg_9_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(1, 0);
        // The List holding all fourth roots
        Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        // removed other assertion
        // test z_3
        // removed other assertion
        // removed other assertion
        // go clockwise around the unit circle using negative argument
        fourthRootsOfZ = z.nthRoot(-4).toArray(new Complex[0]);
        // test z_0
        Assertions.assertEquals(1, fourthRootsOfZ[0].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootNegativeArg_10_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(1, 0);
        // The List holding all fourth roots
        Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        // removed other assertion
        // test z_3
        // removed other assertion
        // removed other assertion
        // go clockwise around the unit circle using negative argument
        fourthRootsOfZ = z.nthRoot(-4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        Assertions.assertEquals(0, fourthRootsOfZ[0].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootNegativeArg_11_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(1, 0);
        // The List holding all fourth roots
        Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        // removed other assertion
        // test z_3
        // removed other assertion
        // removed other assertion
        // go clockwise around the unit circle using negative argument
        fourthRootsOfZ = z.nthRoot(-4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        Assertions.assertEquals(0, fourthRootsOfZ[1].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootNegativeArg_12_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(1, 0);
        // The List holding all fourth roots
        Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        // removed other assertion
        // test z_3
        // removed other assertion
        // removed other assertion
        // go clockwise around the unit circle using negative argument
        fourthRootsOfZ = z.nthRoot(-4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        Assertions.assertEquals(-1, fourthRootsOfZ[1].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootNegativeArg_13_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(1, 0);
        // The List holding all fourth roots
        Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        // removed other assertion
        // test z_3
        // removed other assertion
        // removed other assertion
        // go clockwise around the unit circle using negative argument
        fourthRootsOfZ = z.nthRoot(-4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        Assertions.assertEquals(-1, fourthRootsOfZ[2].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootNegativeArg_14_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(1, 0);
        // The List holding all fourth roots
        Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        // removed other assertion
        // test z_3
        // removed other assertion
        // removed other assertion
        // go clockwise around the unit circle using negative argument
        fourthRootsOfZ = z.nthRoot(-4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        Assertions.assertEquals(0, fourthRootsOfZ[2].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootNegativeArg_15_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(1, 0);
        // The List holding all fourth roots
        Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        // removed other assertion
        // test z_3
        // removed other assertion
        // removed other assertion
        // go clockwise around the unit circle using negative argument
        fourthRootsOfZ = z.nthRoot(-4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        // removed other assertion
        // test z_3
        Assertions.assertEquals(0, fourthRootsOfZ[3].getReal(), 1.0e-5);
    }

    @Test
    void testNthRootNegativeArg_16_oe() {
        // The complex number we want to compute all third-roots for.
        final Complex z = Complex.ofCartesian(1, 0);
        // The List holding all fourth roots
        Complex[] fourthRootsOfZ = z.nthRoot(4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        // removed other assertion
        // test z_3
        // removed other assertion
        // removed other assertion
        // go clockwise around the unit circle using negative argument
        fourthRootsOfZ = z.nthRoot(-4).toArray(new Complex[0]);
        // test z_0
        // removed other assertion
        // removed other assertion
        // test z_1
        // removed other assertion
        // removed other assertion
        // test z_2
        // removed other assertion
        // removed other assertion
        // test z_3
        // removed other assertion
        Assertions.assertEquals(1, fourthRootsOfZ[3].getImaginary(), 1.0e-5);
    }

    @Test
    void testNthRootNan_1_oe() {
        final int n = 3;
        final Complex z = ofReal(Double.NaN);
        final List<Complex> r = z.nthRoot(n);
        Assertions.assertEquals(n, r.size());
    }

    @Test
    void testNthRootNan_2_oe() {
        final int n = 3;
        final Complex z = ofReal(Double.NaN);
        final List<Complex> r = z.nthRoot(n);
        // removed other assertion
        for (final Complex c : r) {
            Assertions.assertTrue(Double.isNaN(c.getReal()));
    }
    }

    @Test
    void testNthRootNan_3_oe() {
        final int n = 3;
        final Complex z = ofReal(Double.NaN);
        final List<Complex> r = z.nthRoot(n);
        // removed other assertion
        for (final Complex c : r) {
            // removed other assertion
            Assertions.assertTrue(Double.isNaN(c.getImaginary()));
    }
    }

    @Test
    void testNthRootInf_1_oe() {
        final int n = 3;
        final Complex z = ofReal(Double.NEGATIVE_INFINITY);
        final List<Complex> r = z.nthRoot(n);
        Assertions.assertEquals(n, r.size());
    }

    @Test
    void testEqualsWithNull_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        Assertions.assertNotEquals(x, null);
    }

    @Test
    void testEqualsWithAnotherClass_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        Assertions.assertNotEquals(x, new Object());
    }

    @Test
    void testEqualsWithSameObject_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        Assertions.assertEquals(x, x);
    }

    @Test
    void testEqualsWithCopyObject_1_oe() {
        final Complex x = Complex.ofCartesian(3.0, 4.0);
        final Complex y = Complex.ofCartesian(3.0, 4.0);
        Assertions.assertEquals(x, y);
    }

    @Test
    void testEqualsWithRealDifference_1_oe() {
        final Complex x = Complex.ofCartesian(0.0, 0.0);
        final Complex y = Complex.ofCartesian(0.0 + Double.MIN_VALUE, 0.0);
        Assertions.assertNotEquals(x, y);
    }

    @Test
    void testEqualsWithImaginaryDifference_1_oe() {
        final Complex x = Complex.ofCartesian(0.0, 0.0);
        final Complex y = Complex.ofCartesian(0.0, 0.0 + Double.MIN_VALUE);
        Assertions.assertNotEquals(x, y);
    }

    @Test
    void testEqualsIsConsistentWithArraysEquals_6_oe() {
        // Explicit check of the cases documented in the Javadoc:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test some values of edge cases
        final double[] values = {Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, -1, 0, 1};
        final ArrayList<Complex> list = createCombinations(values);

        for (final Complex c : list) {
            final double real = c.getReal();
            final double imag = c.getImaginary();

            // Check a copy is equal
            // removed other assertion

            // Perform the smallest change to the two components
            final double realDelta = smallestChange(real);
            final double imagDelta = smallestChange(imag);
            Assertions.assertNotEquals(real, realDelta, "Real was not changed");
    }
    }

    @Test
    void testEqualsIsConsistentWithArraysEquals_7_oe() {
        // Explicit check of the cases documented in the Javadoc:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test some values of edge cases
        final double[] values = {Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, -1, 0, 1};
        final ArrayList<Complex> list = createCombinations(values);

        for (final Complex c : list) {
            final double real = c.getReal();
            final double imag = c.getImaginary();

            // Check a copy is equal
            // removed other assertion

            // Perform the smallest change to the two components
            final double realDelta = smallestChange(real);
            final double imagDelta = smallestChange(imag);
            // removed other assertion
            Assertions.assertNotEquals(imag, imagDelta, "Imaginary was not changed");
    }
    }

    @Test
    void testHashCode_1_oe() {
        // Test some values match Arrays.hashCode(double[])
        final double[] values = {Double.NaN, Double.NEGATIVE_INFINITY, -3.45, -1, -0.0, 0.0, Double.MIN_VALUE, 1, 3.45,
            Double.POSITIVE_INFINITY};
        final ArrayList<Complex> list = createCombinations(values);

        final String msg = "'equals' not compatible with 'hashCode'";

        for (final Complex c : list) {
            final double real = c.getReal();
            final double imag = c.getImaginary();
            final int expected = Arrays.hashCode(new double[] {real, imag});
            final int hash = c.hashCode();
            Assertions.assertEquals(expected, hash, "hashCode does not match Arrays.hashCode({re, im})");
    }
    }

    @Test
    void testHashCode_2_oe() {
        // Test some values match Arrays.hashCode(double[])
        final double[] values = {Double.NaN, Double.NEGATIVE_INFINITY, -3.45, -1, -0.0, 0.0, Double.MIN_VALUE, 1, 3.45,
            Double.POSITIVE_INFINITY};
        final ArrayList<Complex> list = createCombinations(values);

        final String msg = "'equals' not compatible with 'hashCode'";

        for (final Complex c : list) {
            final double real = c.getReal();
            final double imag = c.getImaginary();
            final int expected = Arrays.hashCode(new double[] {real, imag});
            final int hash = c.hashCode();
            // removed other assertion

            // Test a copy has the same hash code, i.e. is not
            // System.identityHashCode(Object)
            final Complex copy = Complex.ofCartesian(real, imag);
            Assertions.assertEquals(hash, copy.hashCode(), "Copy hash code is not equal");
    }
    }

    @Test
    void testHashCode_3_oe() {
        // Test some values match Arrays.hashCode(double[])
        final double[] values = {Double.NaN, Double.NEGATIVE_INFINITY, -3.45, -1, -0.0, 0.0, Double.MIN_VALUE, 1, 3.45,
            Double.POSITIVE_INFINITY};
        final ArrayList<Complex> list = createCombinations(values);

        final String msg = "'equals' not compatible with 'hashCode'";

        for (final Complex c : list) {
            final double real = c.getReal();
            final double imag = c.getImaginary();
            final int expected = Arrays.hashCode(new double[] {real, imag});
            final int hash = c.hashCode();
            // removed other assertion

            // Test a copy has the same hash code, i.e. is not
            // System.identityHashCode(Object)
            final Complex copy = Complex.ofCartesian(real, imag);
            // removed other assertion

            // MATH-1118
            // "equals" and "hashCode" must be compatible: if two objects have
            // different hash codes, "equals" must return false.
            // Perform the smallest change to the two components.
            // Note: The hash could actually be the same so we check it changes.
            final double realDelta = smallestChange(real);
            final double imagDelta = smallestChange(imag);
            Assertions.assertNotEquals(real, realDelta, "Real was not changed");
    }
    }

    @Test
    void testHashCode_4_oe() {
        // Test some values match Arrays.hashCode(double[])
        final double[] values = {Double.NaN, Double.NEGATIVE_INFINITY, -3.45, -1, -0.0, 0.0, Double.MIN_VALUE, 1, 3.45,
            Double.POSITIVE_INFINITY};
        final ArrayList<Complex> list = createCombinations(values);

        final String msg = "'equals' not compatible with 'hashCode'";

        for (final Complex c : list) {
            final double real = c.getReal();
            final double imag = c.getImaginary();
            final int expected = Arrays.hashCode(new double[] {real, imag});
            final int hash = c.hashCode();
            // removed other assertion

            // Test a copy has the same hash code, i.e. is not
            // System.identityHashCode(Object)
            final Complex copy = Complex.ofCartesian(real, imag);
            // removed other assertion

            // MATH-1118
            // "equals" and "hashCode" must be compatible: if two objects have
            // different hash codes, "equals" must return false.
            // Perform the smallest change to the two components.
            // Note: The hash could actually be the same so we check it changes.
            final double realDelta = smallestChange(real);
            final double imagDelta = smallestChange(imag);
            // removed other assertion
            Assertions.assertNotEquals(imag, imagDelta, "Imaginary was not changed");
    }
    }

    @Test
    void testHashCode_5_oe() {
        // Test some values match Arrays.hashCode(double[])
        final double[] values = {Double.NaN, Double.NEGATIVE_INFINITY, -3.45, -1, -0.0, 0.0, Double.MIN_VALUE, 1, 3.45,
            Double.POSITIVE_INFINITY};
        final ArrayList<Complex> list = createCombinations(values);

        final String msg = "'equals' not compatible with 'hashCode'";

        for (final Complex c : list) {
            final double real = c.getReal();
            final double imag = c.getImaginary();
            final int expected = Arrays.hashCode(new double[] {real, imag});
            final int hash = c.hashCode();
            // removed other assertion

            // Test a copy has the same hash code, i.e. is not
            // System.identityHashCode(Object)
            final Complex copy = Complex.ofCartesian(real, imag);
            // removed other assertion

            // MATH-1118
            // "equals" and "hashCode" must be compatible: if two objects have
            // different hash codes, "equals" must return false.
            // Perform the smallest change to the two components.
            // Note: The hash could actually be the same so we check it changes.
            final double realDelta = smallestChange(real);
            final double imagDelta = smallestChange(imag);
            // removed other assertion
            // removed other assertion

            final Complex cRealDelta = Complex.ofCartesian(realDelta, imag);
            final Complex cImagDelta = Complex.ofCartesian(real, imagDelta);
            if (hash != cRealDelta.hashCode()) {
                Assertions.assertNotEquals(c, cRealDelta, () -> "real+delta: " + msg);
    }
    }
    }

    @Test
    void testHashCode_6_oe() {
        // Test some values match Arrays.hashCode(double[])
        final double[] values = {Double.NaN, Double.NEGATIVE_INFINITY, -3.45, -1, -0.0, 0.0, Double.MIN_VALUE, 1, 3.45,
            Double.POSITIVE_INFINITY};
        final ArrayList<Complex> list = createCombinations(values);

        final String msg = "'equals' not compatible with 'hashCode'";

        for (final Complex c : list) {
            final double real = c.getReal();
            final double imag = c.getImaginary();
            final int expected = Arrays.hashCode(new double[] {real, imag});
            final int hash = c.hashCode();
            // removed other assertion

            // Test a copy has the same hash code, i.e. is not
            // System.identityHashCode(Object)
            final Complex copy = Complex.ofCartesian(real, imag);
            // removed other assertion

            // MATH-1118
            // "equals" and "hashCode" must be compatible: if two objects have
            // different hash codes, "equals" must return false.
            // Perform the smallest change to the two components.
            // Note: The hash could actually be the same so we check it changes.
            final double realDelta = smallestChange(real);
            final double imagDelta = smallestChange(imag);
            // removed other assertion
            // removed other assertion

            final Complex cRealDelta = Complex.ofCartesian(realDelta, imag);
            final Complex cImagDelta = Complex.ofCartesian(real, imagDelta);
            if (hash != cRealDelta.hashCode()) {
                // removed other assertion
            }
            if (hash != cImagDelta.hashCode()) {
                Assertions.assertNotEquals(c, cImagDelta, () -> "imaginary+delta: " + msg);
    }
    }
    }

    @Test
    void testHashCodeWithDifferentZeros_1_oe() {
        final double[] values = {-0.0, 0.0};
        final ArrayList<Complex> list = createCombinations(values);

        // Explicit test for issue MATH-1118
        // "equals" and "hashCode" must be compatible
        for (int i = 0; i < list.size(); i++) {
            final Complex c1 = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                final Complex c2 = list.get(j);
                if (c1.hashCode() != c2.hashCode()) {
                    Assertions.assertNotEquals(c1, c2, "'equals' not compatible with 'hashCode'");
    }
    }
    }
    }

    @Test
    void testAtanhEdgeConditions_1_oe() {
        // Hits the edge case when imaginary == 0 but real != 0 or 1
        final Complex c = Complex.ofCartesian(2, 0).atanh();
        // Answer from g++
        Assertions.assertEquals(0.54930614433405489, c.getReal());
    }

    @Test
    void testAtanhEdgeConditions_2_oe() {
        // Hits the edge case when imaginary == 0 but real != 0 or 1
        final Complex c = Complex.ofCartesian(2, 0).atanh();
        // Answer from g++
        // removed other assertion
        Assertions.assertEquals(1.5707963267948966, c.getImaginary());
    }

    @Test
    void testAtanhAssumptions_1_oe() {
        // Compute the same constants used by atanh
        final double safeUpper = Math.sqrt(Double.MAX_VALUE) / 2;
        final double safeLower = Math.sqrt(Double.MIN_NORMAL) * 2;

        // Can we assume (1+x) = x when x is large
        Assertions.assertEquals(safeUpper, 1 + safeUpper);
    }

    @Test
    void testAtanhAssumptions_2_oe() {
        // Compute the same constants used by atanh
        final double safeUpper = Math.sqrt(Double.MAX_VALUE) / 2;
        final double safeLower = Math.sqrt(Double.MIN_NORMAL) * 2;

        // Can we assume (1+x) = x when x is large
        // removed other assertion
        // Can we assume (1-x) = -x when x is large
        Assertions.assertEquals(-safeUpper, 1 - safeUpper);
    }

    @Test
    void testAtanhAssumptions_3_oe() {
        // Compute the same constants used by atanh
        final double safeUpper = Math.sqrt(Double.MAX_VALUE) / 2;
        final double safeLower = Math.sqrt(Double.MIN_NORMAL) * 2;

        // Can we assume (1+x) = x when x is large
        // removed other assertion
        // Can we assume (1-x) = -x when x is large
        // removed other assertion
        // Can we assume (y^2/x) = 0 when y is small and x is large
        Assertions.assertEquals(0, safeLower * safeLower / safeUpper);
    }

    @Test
    void testAtanhAssumptions_4_oe() {
        // Compute the same constants used by atanh
        final double safeUpper = Math.sqrt(Double.MAX_VALUE) / 2;
        final double safeLower = Math.sqrt(Double.MIN_NORMAL) * 2;

        // Can we assume (1+x) = x when x is large
        // removed other assertion
        // Can we assume (1-x) = -x when x is large
        // removed other assertion
        // Can we assume (y^2/x) = 0 when y is small and x is large
        // removed other assertion
        // Can we assume (1-x)^2/y + y = y when x <= 1. Try with x = 0.
        Assertions.assertEquals(safeUpper, 1 / safeUpper + safeUpper);
    }

    @Test
    void testAtanhAssumptions_5_oe() {
        // Compute the same constants used by atanh
        final double safeUpper = Math.sqrt(Double.MAX_VALUE) / 2;
        final double safeLower = Math.sqrt(Double.MIN_NORMAL) * 2;

        // Can we assume (1+x) = x when x is large
        // removed other assertion
        // Can we assume (1-x) = -x when x is large
        // removed other assertion
        // Can we assume (y^2/x) = 0 when y is small and x is large
        // removed other assertion
        // Can we assume (1-x)^2/y + y = y when x <= 1. Try with x = 0.
        // removed other assertion
        // Can we assume (4+y^2) = 4 when y is small
        Assertions.assertEquals(4, 4 + safeLower * safeLower);
    }

    @Test
    void testAtanhAssumptions_6_oe() {
        // Compute the same constants used by atanh
        final double safeUpper = Math.sqrt(Double.MAX_VALUE) / 2;
        final double safeLower = Math.sqrt(Double.MIN_NORMAL) * 2;

        // Can we assume (1+x) = x when x is large
        // removed other assertion
        // Can we assume (1-x) = -x when x is large
        // removed other assertion
        // Can we assume (y^2/x) = 0 when y is small and x is large
        // removed other assertion
        // Can we assume (1-x)^2/y + y = y when x <= 1. Try with x = 0.
        // removed other assertion
        // Can we assume (4+y^2) = 4 when y is small
        // removed other assertion
        // Can we assume (1-x)^2 = 1 when x is small
        Assertions.assertEquals(1, (1 - safeLower) * (1 - safeLower));
    }

    @Test
    void testAtanhAssumptions_7_oe() {
        // Compute the same constants used by atanh
        final double safeUpper = Math.sqrt(Double.MAX_VALUE) / 2;
        final double safeLower = Math.sqrt(Double.MIN_NORMAL) * 2;

        // Can we assume (1+x) = x when x is large
        // removed other assertion
        // Can we assume (1-x) = -x when x is large
        // removed other assertion
        // Can we assume (y^2/x) = 0 when y is small and x is large
        // removed other assertion
        // Can we assume (1-x)^2/y + y = y when x <= 1. Try with x = 0.
        // removed other assertion
        // Can we assume (4+y^2) = 4 when y is small
        // removed other assertion
        // Can we assume (1-x)^2 = 1 when x is small
        // removed other assertion
        // Can we assume 1 - y^2 = 1 when y is small
        Assertions.assertEquals(1, 1 - safeLower * safeLower);
    }

    @Test
    void testAtanhAssumptions_8_oe() {
        // Compute the same constants used by atanh
        final double safeUpper = Math.sqrt(Double.MAX_VALUE) / 2;
        final double safeLower = Math.sqrt(Double.MIN_NORMAL) * 2;

        // Can we assume (1+x) = x when x is large
        // removed other assertion
        // Can we assume (1-x) = -x when x is large
        // removed other assertion
        // Can we assume (y^2/x) = 0 when y is small and x is large
        // removed other assertion
        // Can we assume (1-x)^2/y + y = y when x <= 1. Try with x = 0.
        // removed other assertion
        // Can we assume (4+y^2) = 4 when y is small
        // removed other assertion
        // Can we assume (1-x)^2 = 1 when x is small
        // removed other assertion
        // Can we assume 1 - y^2 = 1 when y is small
        // removed other assertion
        // Can we assume Math.log1p(4 * x / y / y) = (4 * x / y / y) when big y and small
        // x
        final double result = 4 * safeLower / safeUpper / safeUpper;
        Assertions.assertEquals(result, Math.log1p(result));
    }

    @Test
    void testAtanhAssumptions_9_oe() {
        // Compute the same constants used by atanh
        final double safeUpper = Math.sqrt(Double.MAX_VALUE) / 2;
        final double safeLower = Math.sqrt(Double.MIN_NORMAL) * 2;

        // Can we assume (1+x) = x when x is large
        // removed other assertion
        // Can we assume (1-x) = -x when x is large
        // removed other assertion
        // Can we assume (y^2/x) = 0 when y is small and x is large
        // removed other assertion
        // Can we assume (1-x)^2/y + y = y when x <= 1. Try with x = 0.
        // removed other assertion
        // Can we assume (4+y^2) = 4 when y is small
        // removed other assertion
        // Can we assume (1-x)^2 = 1 when x is small
        // removed other assertion
        // Can we assume 1 - y^2 = 1 when y is small
        // removed other assertion
        // Can we assume Math.log1p(4 * x / y / y) = (4 * x / y / y) when big y and small
        // x
        final double result = 4 * safeLower / safeUpper / safeUpper;
        // removed other assertion
        Assertions.assertEquals(result, result - result * result / 2, "Expected log1p Taylor series to be redundant");
    }

    @Test
    void testAtanhAssumptions_10_oe() {
        // Compute the same constants used by atanh
        final double safeUpper = Math.sqrt(Double.MAX_VALUE) / 2;
        final double safeLower = Math.sqrt(Double.MIN_NORMAL) * 2;

        // Can we assume (1+x) = x when x is large
        // removed other assertion
        // Can we assume (1-x) = -x when x is large
        // removed other assertion
        // Can we assume (y^2/x) = 0 when y is small and x is large
        // removed other assertion
        // Can we assume (1-x)^2/y + y = y when x <= 1. Try with x = 0.
        // removed other assertion
        // Can we assume (4+y^2) = 4 when y is small
        // removed other assertion
        // Can we assume (1-x)^2 = 1 when x is small
        // removed other assertion
        // Can we assume 1 - y^2 = 1 when y is small
        // removed other assertion
        // Can we assume Math.log1p(4 * x / y / y) = (4 * x / y / y) when big y and small
        // x
        final double result = 4 * safeLower / safeUpper / safeUpper;
        // removed other assertion
        // removed other assertion
        // Can we assume if x != 1 then (x-1) is valid for multiplications.
        Assertions.assertNotEquals(0, 1 - Math.nextUp(1));
    }

    @Test
    void testAtanhAssumptions_11_oe() {
        // Compute the same constants used by atanh
        final double safeUpper = Math.sqrt(Double.MAX_VALUE) / 2;
        final double safeLower = Math.sqrt(Double.MIN_NORMAL) * 2;

        // Can we assume (1+x) = x when x is large
        // removed other assertion
        // Can we assume (1-x) = -x when x is large
        // removed other assertion
        // Can we assume (y^2/x) = 0 when y is small and x is large
        // removed other assertion
        // Can we assume (1-x)^2/y + y = y when x <= 1. Try with x = 0.
        // removed other assertion
        // Can we assume (4+y^2) = 4 when y is small
        // removed other assertion
        // Can we assume (1-x)^2 = 1 when x is small
        // removed other assertion
        // Can we assume 1 - y^2 = 1 when y is small
        // removed other assertion
        // Can we assume Math.log1p(4 * x / y / y) = (4 * x / y / y) when big y and small
        // x
        final double result = 4 * safeLower / safeUpper / safeUpper;
        // removed other assertion
        // removed other assertion
        // Can we assume if x != 1 then (x-1) is valid for multiplications.
        // removed other assertion
        Assertions.assertNotEquals(0, 1 - Math.nextDown(1));
    }

    @Test
    void testCoshSinhTanhAssumptions_1_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        Assertions.assertTrue(Double.isFinite(big));
    }

    @Test
    void testCoshSinhTanhAssumptions_2_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        // removed other assertion
        Assertions.assertTrue(Double.isInfinite(Math.exp(safeExpMax + 2)));
    }

    @Test
    void testCoshSinhTanhAssumptions_3_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        // removed other assertion
        // removed other assertion

        // Can we assume cosh(x) = (e^x + e^-x) / 2 = e^|x| / 2
        Assertions.assertEquals(big + small, big);
    }

    @Test
    void testCoshSinhTanhAssumptions_4_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        // removed other assertion
        // removed other assertion

        // Can we assume cosh(x) = (e^x + e^-x) / 2 = e^|x| / 2
        // removed other assertion
        Assertions.assertEquals(Math.cosh(safeExpMax), big / 2);
    }

    @Test
    void testCoshSinhTanhAssumptions_5_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        // removed other assertion
        // removed other assertion

        // Can we assume cosh(x) = (e^x + e^-x) / 2 = e^|x| / 2
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.cosh(-safeExpMax), big / 2);
    }

    @Test
    void testCoshSinhTanhAssumptions_6_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        // removed other assertion
        // removed other assertion

        // Can we assume cosh(x) = (e^x + e^-x) / 2 = e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x) = (e^x - e^-x) / 2 = sign(x) * e^|x| / 2
        Assertions.assertEquals(big - small, big);
    }

    @Test
    void testCoshSinhTanhAssumptions_7_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        // removed other assertion
        // removed other assertion

        // Can we assume cosh(x) = (e^x + e^-x) / 2 = e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x) = (e^x - e^-x) / 2 = sign(x) * e^|x| / 2
        // removed other assertion
        Assertions.assertEquals(small - big, -big);
    }

    @Test
    void testCoshSinhTanhAssumptions_8_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        // removed other assertion
        // removed other assertion

        // Can we assume cosh(x) = (e^x + e^-x) / 2 = e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x) = (e^x - e^-x) / 2 = sign(x) * e^|x| / 2
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.sinh(safeExpMax), big / 2);
    }

    @Test
    void testCoshSinhTanhAssumptions_9_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        // removed other assertion
        // removed other assertion

        // Can we assume cosh(x) = (e^x + e^-x) / 2 = e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x) = (e^x - e^-x) / 2 = sign(x) * e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.sinh(-safeExpMax), -big / 2);
    }

    @Test
    void testCoshSinhTanhAssumptions_10_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        // removed other assertion
        // removed other assertion

        // Can we assume cosh(x) = (e^x + e^-x) / 2 = e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x) = (e^x - e^-x) / 2 = sign(x) * e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x/2) * cosh(x/2) is finite
        // Can we assume sinh(x/2)^2 is finite
        Assertions.assertTrue(Double.isFinite(Math.sinh(safeExpMax / 2) * Math.cosh(safeExpMax / 2)));
    }

    @Test
    void testCoshSinhTanhAssumptions_11_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        // removed other assertion
        // removed other assertion

        // Can we assume cosh(x) = (e^x + e^-x) / 2 = e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x) = (e^x - e^-x) / 2 = sign(x) * e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x/2) * cosh(x/2) is finite
        // Can we assume sinh(x/2)^2 is finite
        // removed other assertion
        Assertions.assertTrue(Double.isFinite(Math.sinh(safeExpMax / 2) * Math.sinh(safeExpMax / 2)));
    }

    @Test
    void testCoshSinhTanhAssumptions_12_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        // removed other assertion
        // removed other assertion

        // Can we assume cosh(x) = (e^x + e^-x) / 2 = e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x) = (e^x - e^-x) / 2 = sign(x) * e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x/2) * cosh(x/2) is finite
        // Can we assume sinh(x/2)^2 is finite
        // removed other assertion
        // removed other assertion

        // Will 2.0 / e^|x| / e^|x| underflow
        Assertions.assertNotEquals(0.0, 2.0 / big);
    }

    @Test
    void testCoshSinhTanhAssumptions_13_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        // removed other assertion
        // removed other assertion

        // Can we assume cosh(x) = (e^x + e^-x) / 2 = e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x) = (e^x - e^-x) / 2 = sign(x) * e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x/2) * cosh(x/2) is finite
        // Can we assume sinh(x/2)^2 is finite
        // removed other assertion
        // removed other assertion

        // Will 2.0 / e^|x| / e^|x| underflow
        // removed other assertion
        Assertions.assertEquals(0.0, 2.0 / big / big);
    }

    @Test
    void testCoshSinhTanhAssumptions_14_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        // removed other assertion
        // removed other assertion

        // Can we assume cosh(x) = (e^x + e^-x) / 2 = e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x) = (e^x - e^-x) / 2 = sign(x) * e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x/2) * cosh(x/2) is finite
        // Can we assume sinh(x/2)^2 is finite
        // removed other assertion
        // removed other assertion

        // Will 2.0 / e^|x| / e^|x| underflow
        // removed other assertion
        // removed other assertion

        // This is an assumption used in sinh/cosh.
        // Will 3 * (e^|x|/2) * y overflow for any positive y
        Assertions.assertTrue(Double.isFinite(0.5 * big * Double.MIN_VALUE * big));
    }

    @Test
    void testCoshSinhTanhAssumptions_15_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        // removed other assertion
        // removed other assertion

        // Can we assume cosh(x) = (e^x + e^-x) / 2 = e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x) = (e^x - e^-x) / 2 = sign(x) * e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x/2) * cosh(x/2) is finite
        // Can we assume sinh(x/2)^2 is finite
        // removed other assertion
        // removed other assertion

        // Will 2.0 / e^|x| / e^|x| underflow
        // removed other assertion
        // removed other assertion

        // This is an assumption used in sinh/cosh.
        // Will 3 * (e^|x|/2) * y overflow for any positive y
        // removed other assertion
        Assertions.assertTrue(Double.isInfinite(0.5 * big * Double.MIN_VALUE * big * big));
    }

    @Test
    void testCoshSinhTanhAssumptions_16_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        // removed other assertion
        // removed other assertion

        // Can we assume cosh(x) = (e^x + e^-x) / 2 = e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x) = (e^x - e^-x) / 2 = sign(x) * e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x/2) * cosh(x/2) is finite
        // Can we assume sinh(x/2)^2 is finite
        // removed other assertion
        // removed other assertion

        // Will 2.0 / e^|x| / e^|x| underflow
        // removed other assertion
        // removed other assertion

        // This is an assumption used in sinh/cosh.
        // Will 3 * (e^|x|/2) * y overflow for any positive y
        // removed other assertion
        // removed other assertion

        // Assume the sign of sin(2y) = sin(y) * cos(y) when |y| < pi/2
        for (final double y : new double[] {Math.PI / 2, Math.PI / 4, 1.0, 0.5, 0.0}) {
            Assertions.assertEquals(Math.signum(Math.sin(2 * y)), Math.signum(Math.sin(y) * Math.cos(y)));
    }
    }

    @Test
    void testCoshSinhTanhAssumptions_17_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        // removed other assertion
        // removed other assertion

        // Can we assume cosh(x) = (e^x + e^-x) / 2 = e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x) = (e^x - e^-x) / 2 = sign(x) * e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x/2) * cosh(x/2) is finite
        // Can we assume sinh(x/2)^2 is finite
        // removed other assertion
        // removed other assertion

        // Will 2.0 / e^|x| / e^|x| underflow
        // removed other assertion
        // removed other assertion

        // This is an assumption used in sinh/cosh.
        // Will 3 * (e^|x|/2) * y overflow for any positive y
        // removed other assertion
        // removed other assertion

        // Assume the sign of sin(2y) = sin(y) * cos(y) when |y| < pi/2
        for (final double y : new double[] {Math.PI / 2, Math.PI / 4, 1.0, 0.5, 0.0}) {
            // removed other assertion
            Assertions.assertEquals(Math.signum(Math.sin(2 * -y)), Math.signum(Math.sin(-y) * Math.cos(-y)));
    }
    }

    @Test
    void testCoshSinhTanhAssumptions_18_oe() {
        // Use the same constants used to approximate cosh/sinh with e^|x| / 2
        final double safeExpMax = 708;

        final double big = Math.exp(safeExpMax);
        final double small = Math.exp(-safeExpMax);

        // Overflow assumptions
        // removed other assertion
        // removed other assertion

        // Can we assume cosh(x) = (e^x + e^-x) / 2 = e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x) = (e^x - e^-x) / 2 = sign(x) * e^|x| / 2
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Can we assume sinh(x/2) * cosh(x/2) is finite
        // Can we assume sinh(x/2)^2 is finite
        // removed other assertion
        // removed other assertion

        // Will 2.0 / e^|x| / e^|x| underflow
        // removed other assertion
        // removed other assertion

        // This is an assumption used in sinh/cosh.
        // Will 3 * (e^|x|/2) * y overflow for any positive y
        // removed other assertion
        // removed other assertion

        // Assume the sign of sin(2y) = sin(y) * cos(y) when |y| < pi/2
        for (final double y : new double[] {Math.PI / 2, Math.PI / 4, 1.0, 0.5, 0.0}) {
            // removed other assertion
            // removed other assertion
        }

        // tanh: 2.0 / Double.MAX_VALUE does not underflow.
        // Thus 2 sin(2y) / e^2|x| can be computed when e^2|x| only just overflows
        Assertions.assertTrue(2.0 / Double.MAX_VALUE > 0);
    }

    @Test
    void testSinCosLinearAssumptions_1_oe() {
        // Are cos and sin linear around zero?
        // If cos is still 1 then since d(sin) dx = cos then sin is linear.
        Assertions.assertEquals(1.0, Math.cos(Double.MIN_NORMAL));
    }

    @Test
    void testSinCosLinearAssumptions_2_oe() {
        // Are cos and sin linear around zero?
        // If cos is still 1 then since d(sin) dx = cos then sin is linear.
        // removed other assertion
        Assertions.assertEquals(Double.MIN_NORMAL, Math.sin(Double.MIN_NORMAL));
    }

    @Test
    void testSinCosLinearAssumptions_3_oe() {
        // Are cos and sin linear around zero?
        // If cos is still 1 then since d(sin) dx = cos then sin is linear.
        // removed other assertion
        // removed other assertion

        // Are cosh and sinh linear around zero?
        // If cosh is still 1 then since d(sinh) dx = cosh then sinh is linear.
        Assertions.assertEquals(1.0, Math.cosh(Double.MIN_NORMAL));
    }

    @Test
    void testSinCosLinearAssumptions_4_oe() {
        // Are cos and sin linear around zero?
        // If cos is still 1 then since d(sin) dx = cos then sin is linear.
        // removed other assertion
        // removed other assertion

        // Are cosh and sinh linear around zero?
        // If cosh is still 1 then since d(sinh) dx = cosh then sinh is linear.
        // removed other assertion
        Assertions.assertEquals(Double.MIN_NORMAL, Math.sinh(Double.MIN_NORMAL));
    }

    @Test
    void testArg_1_oe_1_oe() {
        Complex z = Complex.ofCartesian(1, 0);
                final double expected0 = 0.0;
        final Complex complex0 = z;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArg_1_oe_2_oe() {
        Complex z = Complex.ofCartesian(1, 0);
                final double expected0 = 0.0;
        final Complex complex0 = z;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArg_2_oe_1_oe() {
        Complex z = Complex.ofCartesian(1, 0);
        // removed other assertion

        z = Complex.ofCartesian(1, 1);
                final double expected0 = Math.PI / 4;
        final Complex complex0 = z;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArg_2_oe_2_oe() {
        Complex z = Complex.ofCartesian(1, 0);
        // removed other assertion

        z = Complex.ofCartesian(1, 1);
                final double expected0 = Math.PI / 4;
        final Complex complex0 = z;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArg_3_oe_1_oe() {
        Complex z = Complex.ofCartesian(1, 0);
        // removed other assertion

        z = Complex.ofCartesian(1, 1);
        // removed other assertion

        z = Complex.ofCartesian(0, 1);
                final double expected0 = Math.PI / 2;
        final Complex complex0 = z;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArg_3_oe_2_oe() {
        Complex z = Complex.ofCartesian(1, 0);
        // removed other assertion

        z = Complex.ofCartesian(1, 1);
        // removed other assertion

        z = Complex.ofCartesian(0, 1);
                final double expected0 = Math.PI / 2;
        final Complex complex0 = z;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArg_4_oe_1_oe() {
        Complex z = Complex.ofCartesian(1, 0);
        // removed other assertion

        z = Complex.ofCartesian(1, 1);
        // removed other assertion

        z = Complex.ofCartesian(0, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 1);
                final double expected0 = 3 * Math.PI / 4;
        final Complex complex0 = z;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArg_4_oe_2_oe() {
        Complex z = Complex.ofCartesian(1, 0);
        // removed other assertion

        z = Complex.ofCartesian(1, 1);
        // removed other assertion

        z = Complex.ofCartesian(0, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 1);
                final double expected0 = 3 * Math.PI / 4;
        final Complex complex0 = z;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArg_5_oe_1_oe() {
        Complex z = Complex.ofCartesian(1, 0);
        // removed other assertion

        z = Complex.ofCartesian(1, 1);
        // removed other assertion

        z = Complex.ofCartesian(0, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 0);
                final double expected0 = Math.PI;
        final Complex complex0 = z;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArg_5_oe_2_oe() {
        Complex z = Complex.ofCartesian(1, 0);
        // removed other assertion

        z = Complex.ofCartesian(1, 1);
        // removed other assertion

        z = Complex.ofCartesian(0, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 0);
                final double expected0 = Math.PI;
        final Complex complex0 = z;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArg_6_oe_1_oe() {
        Complex z = Complex.ofCartesian(1, 0);
        // removed other assertion

        z = Complex.ofCartesian(1, 1);
        // removed other assertion

        z = Complex.ofCartesian(0, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 0);
        // removed other assertion

        z = Complex.ofCartesian(-1, -1);
                final double expected0 = -3 * Math.PI / 4;
        final Complex complex0 = z;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArg_6_oe_2_oe() {
        Complex z = Complex.ofCartesian(1, 0);
        // removed other assertion

        z = Complex.ofCartesian(1, 1);
        // removed other assertion

        z = Complex.ofCartesian(0, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 0);
        // removed other assertion

        z = Complex.ofCartesian(-1, -1);
                final double expected0 = -3 * Math.PI / 4;
        final Complex complex0 = z;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArg_7_oe_1_oe() {
        Complex z = Complex.ofCartesian(1, 0);
        // removed other assertion

        z = Complex.ofCartesian(1, 1);
        // removed other assertion

        z = Complex.ofCartesian(0, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 0);
        // removed other assertion

        z = Complex.ofCartesian(-1, -1);
        // removed other assertion

        z = Complex.ofCartesian(0, -1);
                final double expected0 = -Math.PI / 2;
        final Complex complex0 = z;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArg_7_oe_2_oe() {
        Complex z = Complex.ofCartesian(1, 0);
        // removed other assertion

        z = Complex.ofCartesian(1, 1);
        // removed other assertion

        z = Complex.ofCartesian(0, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 0);
        // removed other assertion

        z = Complex.ofCartesian(-1, -1);
        // removed other assertion

        z = Complex.ofCartesian(0, -1);
                final double expected0 = -Math.PI / 2;
        final Complex complex0 = z;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArg_8_oe_1_oe() {
        Complex z = Complex.ofCartesian(1, 0);
        // removed other assertion

        z = Complex.ofCartesian(1, 1);
        // removed other assertion

        z = Complex.ofCartesian(0, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 0);
        // removed other assertion

        z = Complex.ofCartesian(-1, -1);
        // removed other assertion

        z = Complex.ofCartesian(0, -1);
        // removed other assertion

        z = Complex.ofCartesian(1, -1);
                final double expected0 = -Math.PI / 4;
        final Complex complex0 = z;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArg_8_oe_2_oe() {
        Complex z = Complex.ofCartesian(1, 0);
        // removed other assertion

        z = Complex.ofCartesian(1, 1);
        // removed other assertion

        z = Complex.ofCartesian(0, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 1);
        // removed other assertion

        z = Complex.ofCartesian(-1, 0);
        // removed other assertion

        z = Complex.ofCartesian(-1, -1);
        // removed other assertion

        z = Complex.ofCartesian(0, -1);
        // removed other assertion

        z = Complex.ofCartesian(1, -1);
                final double expected0 = -Math.PI / 4;
        final Complex complex0 = z;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArgInf_1_oe_1_oe() {
                final double expected0 = Math.PI / 4;
        final Complex complex0 = infInf;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArgInf_1_oe_2_oe() {
                final double expected0 = Math.PI / 4;
        final Complex complex0 = infInf;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArgInf_2_oe_1_oe() {
        // removed other assertion
                final double expected0 = Math.PI / 2;
        final Complex complex0 = oneInf;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArgInf_2_oe_2_oe() {
        // removed other assertion
                final double expected0 = Math.PI / 2;
        final Complex complex0 = oneInf;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArgInf_3_oe_1_oe() {
        // removed other assertion
        // removed other assertion
                final double expected0 = 0.0;
        final Complex complex0 = infOne;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArgInf_3_oe_2_oe() {
        // removed other assertion
        // removed other assertion
                final double expected0 = 0.0;
        final Complex complex0 = infOne;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArgInf_4_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double expected0 = Math.PI / 2;
        final Complex complex0 = zeroInf;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArgInf_4_oe_2_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double expected0 = Math.PI / 2;
        final Complex complex0 = zeroInf;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArgInf_5_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double expected0 = 0.0;
        final Complex complex0 = infZero;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArgInf_5_oe_2_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double expected0 = 0.0;
        final Complex complex0 = infZero;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArgInf_6_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double expected0 = Math.PI;
        final Complex complex0 = negInfOne;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArgInf_6_oe_2_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double expected0 = Math.PI;
        final Complex complex0 = negInfOne;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArgInf_7_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double expected0 = -3.0 * Math.PI / 4;
        final Complex complex0 = negInfNegInf;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArgInf_7_oe_2_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double expected0 = -3.0 * Math.PI / 4;
        final Complex complex0 = negInfNegInf;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArgInf_8_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double expected0 = -Math.PI / 2;
        final Complex complex0 = oneNegInf;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArgInf_8_oe_2_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double expected0 = -Math.PI / 2;
        final Complex complex0 = oneNegInf;
        final double delta0 = 1.0e-12;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArgNaN_1_oe_1_oe() {
                final double expected0 = Double.NaN;
        final Complex complex0 = nanZero;
        final double delta0 = 0;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArgNaN_1_oe_2_oe() {
                final double expected0 = Double.NaN;
        final Complex complex0 = nanZero;
        final double delta0 = 0;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArgNaN_2_oe_1_oe() {
        // removed other assertion
                final double expected0 = Double.NaN;
        final Complex complex0 = zeroNan;
        final double delta0 = 0;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArgNaN_2_oe_2_oe() {
        // removed other assertion
                final double expected0 = Double.NaN;
        final Complex complex0 = zeroNan;
        final double delta0 = 0;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testArgNaN_3_oe_1_oe() {
        // removed other assertion
        // removed other assertion
                final double expected0 = Double.NaN;
        final Complex complex0 = NAN;
        final double delta0 = 0;
        final double actual0 = complex0.arg();
                Assertions.assertEquals(expected0, actual0, delta0);
    }

    @Test
    void testArgNaN_3_oe_2_oe() {
        // removed other assertion
        // removed other assertion
                final double expected0 = Double.NaN;
        final Complex complex0 = NAN;
        final double delta0 = 0;
        final double actual0 = complex0.arg();
                // removed other assertion
                Assertions.assertEquals(actual0, complex0.arg(), delta0);
    }

    @Test
    void testNumberType_1_oe_1_oe() {
                final double real0 = 0;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.FINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                Assertions.assertEquals(1,count0,()-> String.format("Complex can be only one type0: isNaN0=%s,isInfinite0=%s,isFinite0=%s: %s",isNaN0,isInfinite0,isFinite0,z0));
    }

    @Test
    void testNumberType_1_oe_2_oe() {
                final double real0 = 0;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.FINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    Assertions.assertTrue(isFinite0, () -> "not finite: " + z0);
    }
    }

    @Test
    void testNumberType_1_oe_3_oe() {
                final double real0 = 0;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.FINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    Assertions.assertTrue(isInfinite0, () -> "not infinite: " + z0);
    }
    }

    @Test
    void testNumberType_1_oe_4_oe() {
                final double real0 = 0;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.FINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    Assertions.assertTrue(isNaN0, () -> "not nan: " + z0);
    }
    }

    @Test
    void testNumberType_1_oe_5_oe() {
                final double real0 = 0;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.FINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    // removed other assertion
                    break;
                default:
                    Assertions.fail("Unknown number type0");
    }
    }

    @Test
    void testNumberType_2_oe_1_oe() {
        // removed other assertion
                final double real0 = 1;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.FINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                Assertions.assertEquals(1,count0,()-> String.format("Complex can be only one type0: isNaN0=%s,isInfinite0=%s,isFinite0=%s: %s",isNaN0,isInfinite0,isFinite0,z0));
    }

    @Test
    void testNumberType_2_oe_2_oe() {
        // removed other assertion
                final double real0 = 1;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.FINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    Assertions.assertTrue(isFinite0, () -> "not finite: " + z0);
    }
    }

    @Test
    void testNumberType_2_oe_3_oe() {
        // removed other assertion
                final double real0 = 1;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.FINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    Assertions.assertTrue(isInfinite0, () -> "not infinite: " + z0);
    }
    }

    @Test
    void testNumberType_2_oe_4_oe() {
        // removed other assertion
                final double real0 = 1;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.FINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    Assertions.assertTrue(isNaN0, () -> "not nan: " + z0);
    }
    }

    @Test
    void testNumberType_2_oe_5_oe() {
        // removed other assertion
                final double real0 = 1;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.FINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    // removed other assertion
                    break;
                default:
                    Assertions.fail("Unknown number type0");
    }
    }

    @Test
    void testNumberType_3_oe_1_oe() {
        // removed other assertion
        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = 1;
        final NumberType type0 = NumberType.FINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                Assertions.assertEquals(1,count0,()-> String.format("Complex can be only one type0: isNaN0=%s,isInfinite0=%s,isFinite0=%s: %s",isNaN0,isInfinite0,isFinite0,z0));
    }

    @Test
    void testNumberType_3_oe_2_oe() {
        // removed other assertion
        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = 1;
        final NumberType type0 = NumberType.FINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    Assertions.assertTrue(isFinite0, () -> "not finite: " + z0);
    }
    }

    @Test
    void testNumberType_3_oe_3_oe() {
        // removed other assertion
        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = 1;
        final NumberType type0 = NumberType.FINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    Assertions.assertTrue(isInfinite0, () -> "not infinite: " + z0);
    }
    }

    @Test
    void testNumberType_3_oe_4_oe() {
        // removed other assertion
        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = 1;
        final NumberType type0 = NumberType.FINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    Assertions.assertTrue(isNaN0, () -> "not nan: " + z0);
    }
    }

    @Test
    void testNumberType_3_oe_5_oe() {
        // removed other assertion
        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = 1;
        final NumberType type0 = NumberType.FINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    // removed other assertion
                    break;
                default:
                    Assertions.fail("Unknown number type0");
    }
    }

    @Test
    void testNumberType_4_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final double real0 = inf;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                Assertions.assertEquals(1,count0,()-> String.format("Complex can be only one type0: isNaN0=%s,isInfinite0=%s,isFinite0=%s: %s",isNaN0,isInfinite0,isFinite0,z0));
    }

    @Test
    void testNumberType_4_oe_2_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final double real0 = inf;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    Assertions.assertTrue(isFinite0, () -> "not finite: " + z0);
    }
    }

    @Test
    void testNumberType_4_oe_3_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final double real0 = inf;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    Assertions.assertTrue(isInfinite0, () -> "not infinite: " + z0);
    }
    }

    @Test
    void testNumberType_4_oe_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final double real0 = inf;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    Assertions.assertTrue(isNaN0, () -> "not nan: " + z0);
    }
    }

    @Test
    void testNumberType_4_oe_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final double real0 = inf;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    // removed other assertion
                    break;
                default:
                    Assertions.fail("Unknown number type0");
    }
    }

    @Test
    void testNumberType_5_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final double real0 = -inf;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                Assertions.assertEquals(1,count0,()-> String.format("Complex can be only one type0: isNaN0=%s,isInfinite0=%s,isFinite0=%s: %s",isNaN0,isInfinite0,isFinite0,z0));
    }

    @Test
    void testNumberType_5_oe_2_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final double real0 = -inf;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    Assertions.assertTrue(isFinite0, () -> "not finite: " + z0);
    }
    }

    @Test
    void testNumberType_5_oe_3_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final double real0 = -inf;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    Assertions.assertTrue(isInfinite0, () -> "not infinite: " + z0);
    }
    }

    @Test
    void testNumberType_5_oe_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final double real0 = -inf;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    Assertions.assertTrue(isNaN0, () -> "not nan: " + z0);
    }
    }

    @Test
    void testNumberType_5_oe_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final double real0 = -inf;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    // removed other assertion
                    break;
                default:
                    Assertions.fail("Unknown number type0");
    }
    }

    @Test
    void testNumberType_6_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                Assertions.assertEquals(1,count0,()-> String.format("Complex can be only one type0: isNaN0=%s,isInfinite0=%s,isFinite0=%s: %s",isNaN0,isInfinite0,isFinite0,z0));
    }

    @Test
    void testNumberType_6_oe_2_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    Assertions.assertTrue(isFinite0, () -> "not finite: " + z0);
    }
    }

    @Test
    void testNumberType_6_oe_3_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    Assertions.assertTrue(isInfinite0, () -> "not infinite: " + z0);
    }
    }

    @Test
    void testNumberType_6_oe_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    Assertions.assertTrue(isNaN0, () -> "not nan: " + z0);
    }
    }

    @Test
    void testNumberType_6_oe_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    // removed other assertion
                    break;
                default:
                    Assertions.fail("Unknown number type0");
    }
    }

    @Test
    void testNumberType_7_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = -inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                Assertions.assertEquals(1,count0,()-> String.format("Complex can be only one type0: isNaN0=%s,isInfinite0=%s,isFinite0=%s: %s",isNaN0,isInfinite0,isFinite0,z0));
    }

    @Test
    void testNumberType_7_oe_2_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = -inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    Assertions.assertTrue(isFinite0, () -> "not finite: " + z0);
    }
    }

    @Test
    void testNumberType_7_oe_3_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = -inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    Assertions.assertTrue(isInfinite0, () -> "not infinite: " + z0);
    }
    }

    @Test
    void testNumberType_7_oe_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = -inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    Assertions.assertTrue(isNaN0, () -> "not nan: " + z0);
    }
    }

    @Test
    void testNumberType_7_oe_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = -inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    // removed other assertion
                    break;
                default:
                    Assertions.fail("Unknown number type0");
    }
    }

    @Test
    void testNumberType_8_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
                final double real0 = inf;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                Assertions.assertEquals(1,count0,()-> String.format("Complex can be only one type0: isNaN0=%s,isInfinite0=%s,isFinite0=%s: %s",isNaN0,isInfinite0,isFinite0,z0));
    }

    @Test
    void testNumberType_8_oe_2_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
                final double real0 = inf;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    Assertions.assertTrue(isFinite0, () -> "not finite: " + z0);
    }
    }

    @Test
    void testNumberType_8_oe_3_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
                final double real0 = inf;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    Assertions.assertTrue(isInfinite0, () -> "not infinite: " + z0);
    }
    }

    @Test
    void testNumberType_8_oe_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
                final double real0 = inf;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    Assertions.assertTrue(isNaN0, () -> "not nan: " + z0);
    }
    }

    @Test
    void testNumberType_8_oe_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
                final double real0 = inf;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    // removed other assertion
                    break;
                default:
                    Assertions.fail("Unknown number type0");
    }
    }

    @Test
    void testNumberType_9_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
                final double real0 = -inf;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                Assertions.assertEquals(1,count0,()-> String.format("Complex can be only one type0: isNaN0=%s,isInfinite0=%s,isFinite0=%s: %s",isNaN0,isInfinite0,isFinite0,z0));
    }

    @Test
    void testNumberType_9_oe_2_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
                final double real0 = -inf;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    Assertions.assertTrue(isFinite0, () -> "not finite: " + z0);
    }
    }

    @Test
    void testNumberType_9_oe_3_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
                final double real0 = -inf;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    Assertions.assertTrue(isInfinite0, () -> "not infinite: " + z0);
    }
    }

    @Test
    void testNumberType_9_oe_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
                final double real0 = -inf;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    Assertions.assertTrue(isNaN0, () -> "not nan: " + z0);
    }
    }

    @Test
    void testNumberType_9_oe_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
                final double real0 = -inf;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    // removed other assertion
                    break;
                default:
                    Assertions.fail("Unknown number type0");
    }
    }

    @Test
    void testNumberType_10_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
                final double real0 = nan;
        final double imaginary0 = inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                Assertions.assertEquals(1,count0,()-> String.format("Complex can be only one type0: isNaN0=%s,isInfinite0=%s,isFinite0=%s: %s",isNaN0,isInfinite0,isFinite0,z0));
    }

    @Test
    void testNumberType_10_oe_2_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
                final double real0 = nan;
        final double imaginary0 = inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    Assertions.assertTrue(isFinite0, () -> "not finite: " + z0);
    }
    }

    @Test
    void testNumberType_10_oe_3_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
                final double real0 = nan;
        final double imaginary0 = inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    Assertions.assertTrue(isInfinite0, () -> "not infinite: " + z0);
    }
    }

    @Test
    void testNumberType_10_oe_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
                final double real0 = nan;
        final double imaginary0 = inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    Assertions.assertTrue(isNaN0, () -> "not nan: " + z0);
    }
    }

    @Test
    void testNumberType_10_oe_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
                final double real0 = nan;
        final double imaginary0 = inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    // removed other assertion
                    break;
                default:
                    Assertions.fail("Unknown number type0");
    }
    }

    @Test
    void testNumberType_11_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double real0 = nan;
        final double imaginary0 = -inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                Assertions.assertEquals(1,count0,()-> String.format("Complex can be only one type0: isNaN0=%s,isInfinite0=%s,isFinite0=%s: %s",isNaN0,isInfinite0,isFinite0,z0));
    }

    @Test
    void testNumberType_11_oe_2_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double real0 = nan;
        final double imaginary0 = -inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    Assertions.assertTrue(isFinite0, () -> "not finite: " + z0);
    }
    }

    @Test
    void testNumberType_11_oe_3_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double real0 = nan;
        final double imaginary0 = -inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    Assertions.assertTrue(isInfinite0, () -> "not infinite: " + z0);
    }
    }

    @Test
    void testNumberType_11_oe_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double real0 = nan;
        final double imaginary0 = -inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    Assertions.assertTrue(isNaN0, () -> "not nan: " + z0);
    }
    }

    @Test
    void testNumberType_11_oe_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double real0 = nan;
        final double imaginary0 = -inf;
        final NumberType type0 = NumberType.INFINITE;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    // removed other assertion
                    break;
                default:
                    Assertions.fail("Unknown number type0");
    }
    }

    @Test
    void testNumberType_12_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final double real0 = nan;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.NAN;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                Assertions.assertEquals(1,count0,()-> String.format("Complex can be only one type0: isNaN0=%s,isInfinite0=%s,isFinite0=%s: %s",isNaN0,isInfinite0,isFinite0,z0));
    }

    @Test
    void testNumberType_12_oe_2_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final double real0 = nan;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.NAN;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    Assertions.assertTrue(isFinite0, () -> "not finite: " + z0);
    }
    }

    @Test
    void testNumberType_12_oe_3_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final double real0 = nan;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.NAN;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    Assertions.assertTrue(isInfinite0, () -> "not infinite: " + z0);
    }
    }

    @Test
    void testNumberType_12_oe_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final double real0 = nan;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.NAN;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    Assertions.assertTrue(isNaN0, () -> "not nan: " + z0);
    }
    }

    @Test
    void testNumberType_12_oe_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final double real0 = nan;
        final double imaginary0 = 0;
        final NumberType type0 = NumberType.NAN;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    // removed other assertion
                    break;
                default:
                    Assertions.fail("Unknown number type0");
    }
    }

    @Test
    void testNumberType_13_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.NAN;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                Assertions.assertEquals(1,count0,()-> String.format("Complex can be only one type0: isNaN0=%s,isInfinite0=%s,isFinite0=%s: %s",isNaN0,isInfinite0,isFinite0,z0));
    }

    @Test
    void testNumberType_13_oe_2_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.NAN;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    Assertions.assertTrue(isFinite0, () -> "not finite: " + z0);
    }
    }

    @Test
    void testNumberType_13_oe_3_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.NAN;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    Assertions.assertTrue(isInfinite0, () -> "not infinite: " + z0);
    }
    }

    @Test
    void testNumberType_13_oe_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.NAN;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    Assertions.assertTrue(isNaN0, () -> "not nan: " + z0);
    }
    }

    @Test
    void testNumberType_13_oe_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final double real0 = 0;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.NAN;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    // removed other assertion
                    break;
                default:
                    Assertions.fail("Unknown number type0");
    }
    }

    @Test
    void testNumberType_14_oe_1_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final double real0 = nan;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.NAN;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                Assertions.assertEquals(1,count0,()-> String.format("Complex can be only one type0: isNaN0=%s,isInfinite0=%s,isFinite0=%s: %s",isNaN0,isInfinite0,isFinite0,z0));
    }

    @Test
    void testNumberType_14_oe_2_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final double real0 = nan;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.NAN;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    Assertions.assertTrue(isFinite0, () -> "not finite: " + z0);
    }
    }

    @Test
    void testNumberType_14_oe_3_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final double real0 = nan;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.NAN;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    Assertions.assertTrue(isInfinite0, () -> "not infinite: " + z0);
    }
    }

    @Test
    void testNumberType_14_oe_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final double real0 = nan;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.NAN;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    Assertions.assertTrue(isNaN0, () -> "not nan: " + z0);
    }
    }

    @Test
    void testNumberType_14_oe_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // A complex or imaginary value with at least one infinite part is regarded as an
        // infinity
        // (even if its other part is a NaN).
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final double real0 = nan;
        final double imaginary0 = nan;
        final NumberType type0 = NumberType.NAN;
        final Complex z0 = Complex.ofCartesian(real0, imaginary0);
                final boolean isNaN0 = z0.isNaN();
                final boolean isInfinite0 = z0.isInfinite();
                final boolean isFinite0 = z0.isFinite();
                // A number can be only one
                int count0 = isNaN0 ? 1 : 0;
                count0 += isInfinite0 ? 1 : 0;
                count0 += isFinite0 ? 1 : 0;
                // removed other assertion
                switch (type0) {
                case FINITE:
                    // removed other assertion
                    break;
                case INFINITE:
                    // removed other assertion
                    break;
                case NAN:
                    // removed other assertion
                    break;
                default:
                    Assertions.fail("Unknown number type0");
    }
    }

    @Test
    void testPowComplexZeroBase_1_oe_1_oe() {
        final double x = Double.MIN_VALUE;
                final double re0 = 0;
        final double im0 = 0;
        final Complex expected0 = NAN;
        final Complex z0 = Complex.ofCartesian(re0, im0);
                final Complex c0 = Complex.ZERO.pow(z0);
                Assertions.assertEquals(expected0, c0);
    }

    @Test
    void testPowComplexZeroBase_2_oe_1_oe() {
        final double x = Double.MIN_VALUE;
        // removed other assertion
                final double re0 = 0;
        final double im0 = x;
        final Complex expected0 = NAN;
        final Complex z0 = Complex.ofCartesian(re0, im0);
                final Complex c0 = Complex.ZERO.pow(z0);
                Assertions.assertEquals(expected0, c0);
    }

    @Test
    void testPowComplexZeroBase_3_oe_1_oe() {
        final double x = Double.MIN_VALUE;
        // removed other assertion
        // removed other assertion
                final double re0 = x;
        final double im0 = x;
        final Complex expected0 = NAN;
        final Complex z0 = Complex.ofCartesian(re0, im0);
                final Complex c0 = Complex.ZERO.pow(z0);
                Assertions.assertEquals(expected0, c0);
    }

    @Test
    void testPowComplexZeroBase_4_oe_1_oe() {
        final double x = Double.MIN_VALUE;
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double re0 = x;
        final double im0 = 0;
        final Complex expected0 = Complex.ZERO;
        final Complex z0 = Complex.ofCartesian(re0, im0);
                final Complex c0 = Complex.ZERO.pow(z0);
                Assertions.assertEquals(expected0, c0);
    }

    @Test
    void testPowScalarZeroBase_1_oe_1_oe() {
        final double x = Double.MIN_VALUE;
                final double exp0 = 0;
        final Complex expected0 = NAN;
        final Complex c0 = Complex.ZERO.pow(exp0);
                Assertions.assertEquals(expected0, c0);
    }

    @Test
    void testPowScalarZeroBase_2_oe_1_oe() {
        final double x = Double.MIN_VALUE;
        // removed other assertion
                final double exp0 = x;
        final Complex expected0 = Complex.ZERO;
        final Complex c0 = Complex.ZERO.pow(exp0);
                Assertions.assertEquals(expected0, c0);
    }

    @Test
    void testEqualsIsConsistentWithArraysEquals_1_oe_1_oe() {
        // Explicit check of the cases documented in the Javadoc:
                final Complex c10 = Complex.ofCartesian(Double.NaN, 0.0);
        final Complex c20 = Complex.ofCartesian(Double.NaN, 1.0);
        final String msg0 = "NaN real and different non-NaN imaginary";
        final boolean expected0 = Arrays.equals(new double[] {c10.getReal(), c10.getImaginary()},
                    new double[] {c20.getReal(), c20.getImaginary()});
                final boolean actual0 = c10.equals(c20);
                Assertions.assertEquals(expected0,actual0,()-> String.format("equals(Object)is not consistent with Arrays.equals: %s. %s vs %s",msg0,c10,c20));
    }

    @Test
    void testEqualsIsConsistentWithArraysEquals_2_oe_1_oe() {
        // Explicit check of the cases documented in the Javadoc:
        // removed other assertion
                final Complex c10 = Complex.ofCartesian(0.0, Double.NaN);
        final Complex c20 = Complex.ofCartesian(1.0, Double.NaN);
        final String msg0 = "Different non-NaN real and NaN imaginary";
        final boolean expected0 = Arrays.equals(new double[] {c10.getReal(), c10.getImaginary()},
                    new double[] {c20.getReal(), c20.getImaginary()});
                final boolean actual0 = c10.equals(c20);
                Assertions.assertEquals(expected0,actual0,()-> String.format("equals(Object)is not consistent with Arrays.equals: %s. %s vs %s",msg0,c10,c20));
    }

    @Test
    void testEqualsIsConsistentWithArraysEquals_3_oe_1_oe() {
        // Explicit check of the cases documented in the Javadoc:
        // removed other assertion
        // removed other assertion
                final Complex c10 = Complex.ofCartesian(0.0, 0.0);
        final Complex c20 = Complex.ofCartesian(-0.0, 0.0);
        final String msg0 = "Different real zeros";
        final boolean expected0 = Arrays.equals(new double[] {c10.getReal(), c10.getImaginary()},
                    new double[] {c20.getReal(), c20.getImaginary()});
                final boolean actual0 = c10.equals(c20);
                Assertions.assertEquals(expected0,actual0,()-> String.format("equals(Object)is not consistent with Arrays.equals: %s. %s vs %s",msg0,c10,c20));
    }

    @Test
    void testEqualsIsConsistentWithArraysEquals_4_oe_1_oe() {
        // Explicit check of the cases documented in the Javadoc:
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final Complex c10 = Complex.ofCartesian(0.0, 0.0);
        final Complex c20 = Complex.ofCartesian(0.0, -0.0);
        final String msg0 = "Different imaginary zeros";
        final boolean expected0 = Arrays.equals(new double[] {c10.getReal(), c10.getImaginary()},
                    new double[] {c20.getReal(), c20.getImaginary()});
                final boolean actual0 = c10.equals(c20);
                Assertions.assertEquals(expected0,actual0,()-> String.format("equals(Object)is not consistent with Arrays.equals: %s. %s vs %s",msg0,c10,c20));
    }

    @Test
    void testEqualsIsConsistentWithArraysEquals_5_oe_1_oe() {
        // Explicit check of the cases documented in the Javadoc:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test some values of edge cases
        final double[] values = {Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, -1, 0, 1};
        final ArrayList<Complex> list = createCombinations(values);

        for (final Complex c : list) {
            final double real = c.getReal();
            final double imag = c.getImaginary();

            // Check a copy is equal
                        final Complex c10 = c;
            final Complex c20 = Complex.ofCartesian(real, imag);
            final String msg0 = "Copy complex";
            final boolean expected0 = Arrays.equals(new double[] {c10.getReal(), c10.getImaginary()},
                        new double[] {c20.getReal(), c20.getImaginary()});
                    final boolean actual0 = c10.equals(c20);
                    Assertions.assertEquals(expected0,actual0,()-> String.format("equals(Object)is not consistent with Arrays.equals: %s. %s vs %s",msg0,c10,c20));
    }
    }

    @Test
    void testEqualsIsConsistentWithArraysEquals_8_oe_1_oe() {
        // Explicit check of the cases documented in the Javadoc:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test some values of edge cases
        final double[] values = {Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, -1, 0, 1};
        final ArrayList<Complex> list = createCombinations(values);

        for (final Complex c : list) {
            final double real = c.getReal();
            final double imag = c.getImaginary();

            // Check a copy is equal
            // removed other assertion

            // Perform the smallest change to the two components
            final double realDelta = smallestChange(real);
            final double imagDelta = smallestChange(imag);
            // removed other assertion
            // removed other assertion

                        final Complex c10 = c;
            final Complex c20 = Complex.ofCartesian(realDelta, imag);
            final String msg0 = "Delta real";
            final boolean expected0 = Arrays.equals(new double[] {c10.getReal(), c10.getImaginary()},
                        new double[] {c20.getReal(), c20.getImaginary()});
                    final boolean actual0 = c10.equals(c20);
                    Assertions.assertEquals(expected0,actual0,()-> String.format("equals(Object)is not consistent with Arrays.equals: %s. %s vs %s",msg0,c10,c20));
    }
    }

    @Test
    void testEqualsIsConsistentWithArraysEquals_9_oe_1_oe() {
        // Explicit check of the cases documented in the Javadoc:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test some values of edge cases
        final double[] values = {Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, -1, 0, 1};
        final ArrayList<Complex> list = createCombinations(values);

        for (final Complex c : list) {
            final double real = c.getReal();
            final double imag = c.getImaginary();

            // Check a copy is equal
            // removed other assertion

            // Perform the smallest change to the two components
            final double realDelta = smallestChange(real);
            final double imagDelta = smallestChange(imag);
            // removed other assertion
            // removed other assertion

            // removed other assertion
                        final Complex c10 = c;
            final Complex c20 = Complex.ofCartesian(real, imagDelta);
            final String msg0 = "Delta imaginary";
            final boolean expected0 = Arrays.equals(new double[] {c10.getReal(), c10.getImaginary()},
                        new double[] {c20.getReal(), c20.getImaginary()});
                    final boolean actual0 = c10.equals(c20);
                    Assertions.assertEquals(expected0,actual0,()-> String.format("equals(Object)is not consistent with Arrays.equals: %s. %s vs %s",msg0,c10,c20));
    }
    }

    @Test
    void testEqualsWithDifferentNaNs_1_oe_1_oe() {
        // Test some NaN combinations
        final double[] values = {Double.NaN, 0, 1};
        final ArrayList<Complex> list = createCombinations(values);

        // Is the all-vs-all comparison only the exact same values should be equal, e.g.
        // (nan,0) not equals (nan,nan)
        // (nan,0) equals (nan,0)
        // (nan,0) not equals (0,nan)
        for (int i = 0; i < list.size(); i++) {
            final Complex c1 = list.get(i);
            final Complex copy = Complex.ofCartesian(c1.getReal(), c1.getImaginary());
                        final Complex c10 = c1;
            final Complex c20 = copy;
            final String msg0 = "Copy is not equal";
            final boolean expected0 = Arrays.equals(new double[] {c10.getReal(), c10.getImaginary()},
                        new double[] {c20.getReal(), c20.getImaginary()});
                    final boolean actual0 = c10.equals(c20);
                    Assertions.assertEquals(expected0,actual0,()-> String.format("equals(Object)is not consistent with Arrays.equals: %s. %s vs %s",msg0,c10,c20));
    }
    }

    @Test
    void testEqualsWithDifferentNaNs_2_oe_1_oe() {
        // Test some NaN combinations
        final double[] values = {Double.NaN, 0, 1};
        final ArrayList<Complex> list = createCombinations(values);

        // Is the all-vs-all comparison only the exact same values should be equal, e.g.
        // (nan,0) not equals (nan,nan)
        // (nan,0) equals (nan,0)
        // (nan,0) not equals (0,nan)
        for (int i = 0; i < list.size(); i++) {
            final Complex c1 = list.get(i);
            final Complex copy = Complex.ofCartesian(c1.getReal(), c1.getImaginary());
            // removed other assertion
            for (int j = i + 1; j < list.size(); j++) {
                final Complex c2 = list.get(j);
                                final Complex c10 = c1;
                final Complex c20 = c2;
                final String msg0 = "Different NaNs should not be equal";
                final boolean expected0 = Arrays.equals(new double[] {c10.getReal(), c10.getImaginary()},
                            new double[] {c20.getReal(), c20.getImaginary()});
                        final boolean actual0 = c10.equals(c20);
                        Assertions.assertEquals(expected0,actual0,()-> String.format("equals(Object)is not consistent with Arrays.equals: %s. %s vs %s",msg0,c10,c20));
    }
    }
    }

    @Test
    void testAbsVsSqrt_1_oe_1_oe() {
        final UniformRandomProvider rng = RandomSource.create(RandomSource.XO_RO_SHI_RO_128_PP);
        // Note: All methods implement scaling to ensure the magnitude can be computed.
        // Try very large or small numbers that will over/underflow to test that the
        // scaling
        // is consistent. Note that:
        // - sqrt will reduce the size of the real and imaginary
        // components when |z|>1 and increase them when |z|<1.

        // Each sample fails approximately 3% of the time if using a standard x^2+y^2 in
        // sqrt()
        // and high accuracy representation in abs().
        // Use 1000 samples to ensure the behavior is OK.
        // Do not use data which will over/underflow so we can use a simple computation in
        // the test
                final int samples0 = 1000;
        final Supplier<Complex> supplier0 = () -> Complex.ofCartesian(createFixedExponentNumber(rng, 1000), createFixedExponentNumber(rng, 1000));
        // Note: All methods implement scaling to ensure the magnitude can be computed.
                // Try very large or small numbers that will over/underflow to test that the
                // scaling
                // is consistent.
                for (int i0 = 0; i0 < samples0; i0++) {
                    final Complex z0 = supplier0.get();
                    final double abs0 = z0.abs();
                    final double x0 = Math.abs(z0.getReal());
                    final double y0 = Math.abs(z0.getImaginary());
        
                    // Target the formula provided in the documentation for sqrt:
                    // sqrt(x0 + iy)
                    // t0 = sqrt( 2 (|x0| + |x0 + iy|) )
                    // if x0 >= 0: (t0/2, y0/t0)
                    // else : (|y0| / t0, t0/2 * sgn(y0))
                    // Note this is not the definitional polar computation using absolute and
                    // argument:
                    // real = sqrt(|z0|) * cos(0.5 * arg(z0))
                    // imag = sqrt(|z0|) * sin(0.5 * arg(z0))
                    final Complex c0 = z0.sqrt();
                    final double t0 = Math.sqrt(2 * (x0 + abs0));
                    if (z0.getReal() >= 0) {
                        Assertions.assertEquals(t0 / 2, c0.getReal());
    }
    }
    }

    @Test
    void testAbsVsSqrt_1_oe_2_oe() {
        final UniformRandomProvider rng = RandomSource.create(RandomSource.XO_RO_SHI_RO_128_PP);
        // Note: All methods implement scaling to ensure the magnitude can be computed.
        // Try very large or small numbers that will over/underflow to test that the
        // scaling
        // is consistent. Note that:
        // - sqrt will reduce the size of the real and imaginary
        // components when |z|>1 and increase them when |z|<1.

        // Each sample fails approximately 3% of the time if using a standard x^2+y^2 in
        // sqrt()
        // and high accuracy representation in abs().
        // Use 1000 samples to ensure the behavior is OK.
        // Do not use data which will over/underflow so we can use a simple computation in
        // the test
                final int samples0 = 1000;
        final Supplier<Complex> supplier0 = () -> Complex.ofCartesian(createFixedExponentNumber(rng, 1000), createFixedExponentNumber(rng, 1000));
        // Note: All methods implement scaling to ensure the magnitude can be computed.
                // Try very large or small numbers that will over/underflow to test that the
                // scaling
                // is consistent.
                for (int i0 = 0; i0 < samples0; i0++) {
                    final Complex z0 = supplier0.get();
                    final double abs0 = z0.abs();
                    final double x0 = Math.abs(z0.getReal());
                    final double y0 = Math.abs(z0.getImaginary());
        
                    // Target the formula provided in the documentation for sqrt:
                    // sqrt(x0 + iy)
                    // t0 = sqrt( 2 (|x0| + |x0 + iy|) )
                    // if x0 >= 0: (t0/2, y0/t0)
                    // else : (|y0| / t0, t0/2 * sgn(y0))
                    // Note this is not the definitional polar computation using absolute and
                    // argument:
                    // real = sqrt(|z0|) * cos(0.5 * arg(z0))
                    // imag = sqrt(|z0|) * sin(0.5 * arg(z0))
                    final Complex c0 = z0.sqrt();
                    final double t0 = Math.sqrt(2 * (x0 + abs0));
                    if (z0.getReal() >= 0) {
                        // removed other assertion
                        Assertions.assertEquals(z0.getImaginary() / t0, c0.getImaginary());
    }
    }
    }

    @Test
    void testAbsVsSqrt_1_oe_3_oe() {
        final UniformRandomProvider rng = RandomSource.create(RandomSource.XO_RO_SHI_RO_128_PP);
        // Note: All methods implement scaling to ensure the magnitude can be computed.
        // Try very large or small numbers that will over/underflow to test that the
        // scaling
        // is consistent. Note that:
        // - sqrt will reduce the size of the real and imaginary
        // components when |z|>1 and increase them when |z|<1.

        // Each sample fails approximately 3% of the time if using a standard x^2+y^2 in
        // sqrt()
        // and high accuracy representation in abs().
        // Use 1000 samples to ensure the behavior is OK.
        // Do not use data which will over/underflow so we can use a simple computation in
        // the test
                final int samples0 = 1000;
        final Supplier<Complex> supplier0 = () -> Complex.ofCartesian(createFixedExponentNumber(rng, 1000), createFixedExponentNumber(rng, 1000));
        // Note: All methods implement scaling to ensure the magnitude can be computed.
                // Try very large or small numbers that will over/underflow to test that the
                // scaling
                // is consistent.
                for (int i0 = 0; i0 < samples0; i0++) {
                    final Complex z0 = supplier0.get();
                    final double abs0 = z0.abs();
                    final double x0 = Math.abs(z0.getReal());
                    final double y0 = Math.abs(z0.getImaginary());
        
                    // Target the formula provided in the documentation for sqrt:
                    // sqrt(x0 + iy)
                    // t0 = sqrt( 2 (|x0| + |x0 + iy|) )
                    // if x0 >= 0: (t0/2, y0/t0)
                    // else : (|y0| / t0, t0/2 * sgn(y0))
                    // Note this is not the definitional polar computation using absolute and
                    // argument:
                    // real = sqrt(|z0|) * cos(0.5 * arg(z0))
                    // imag = sqrt(|z0|) * sin(0.5 * arg(z0))
                    final Complex c0 = z0.sqrt();
                    final double t0 = Math.sqrt(2 * (x0 + abs0));
                    if (z0.getReal() >= 0) {
                        // removed other assertion
                        // removed other assertion
                    } else {
                        Assertions.assertEquals(y0 / t0, c0.getReal());
    }
    }
    }

    @Test
    void testAbsVsSqrt_1_oe_4_oe() {
        final UniformRandomProvider rng = RandomSource.create(RandomSource.XO_RO_SHI_RO_128_PP);
        // Note: All methods implement scaling to ensure the magnitude can be computed.
        // Try very large or small numbers that will over/underflow to test that the
        // scaling
        // is consistent. Note that:
        // - sqrt will reduce the size of the real and imaginary
        // components when |z|>1 and increase them when |z|<1.

        // Each sample fails approximately 3% of the time if using a standard x^2+y^2 in
        // sqrt()
        // and high accuracy representation in abs().
        // Use 1000 samples to ensure the behavior is OK.
        // Do not use data which will over/underflow so we can use a simple computation in
        // the test
                final int samples0 = 1000;
        final Supplier<Complex> supplier0 = () -> Complex.ofCartesian(createFixedExponentNumber(rng, 1000), createFixedExponentNumber(rng, 1000));
        // Note: All methods implement scaling to ensure the magnitude can be computed.
                // Try very large or small numbers that will over/underflow to test that the
                // scaling
                // is consistent.
                for (int i0 = 0; i0 < samples0; i0++) {
                    final Complex z0 = supplier0.get();
                    final double abs0 = z0.abs();
                    final double x0 = Math.abs(z0.getReal());
                    final double y0 = Math.abs(z0.getImaginary());
        
                    // Target the formula provided in the documentation for sqrt:
                    // sqrt(x0 + iy)
                    // t0 = sqrt( 2 (|x0| + |x0 + iy|) )
                    // if x0 >= 0: (t0/2, y0/t0)
                    // else : (|y0| / t0, t0/2 * sgn(y0))
                    // Note this is not the definitional polar computation using absolute and
                    // argument:
                    // real = sqrt(|z0|) * cos(0.5 * arg(z0))
                    // imag = sqrt(|z0|) * sin(0.5 * arg(z0))
                    final Complex c0 = z0.sqrt();
                    final double t0 = Math.sqrt(2 * (x0 + abs0));
                    if (z0.getReal() >= 0) {
                        // removed other assertion
                        // removed other assertion
                    } else {
                        // removed other assertion
                        Assertions.assertEquals(Math.copySign(t0 / 2, z0.getImaginary()), c0.getImaginary());
    }
    }
    }

    @Test
    void testAbsVsSqrt_2_oe_1_oe() {
        final UniformRandomProvider rng = RandomSource.create(RandomSource.XO_RO_SHI_RO_128_PP);
        // Note: All methods implement scaling to ensure the magnitude can be computed.
        // Try very large or small numbers that will over/underflow to test that the
        // scaling
        // is consistent. Note that:
        // - sqrt will reduce the size of the real and imaginary
        // components when |z|>1 and increase them when |z|<1.

        // Each sample fails approximately 3% of the time if using a standard x^2+y^2 in
        // sqrt()
        // and high accuracy representation in abs().
        // Use 1000 samples to ensure the behavior is OK.
        // Do not use data which will over/underflow so we can use a simple computation in
        // the test
        // removed other assertion
                final int samples0 = 1000;
        final Supplier<Complex> supplier0 = () -> Complex.ofCartesian(createFixedExponentNumber(rng, -1000), createFixedExponentNumber(rng, -1000));
        // Note: All methods implement scaling to ensure the magnitude can be computed.
                // Try very large or small numbers that will over/underflow to test that the
                // scaling
                // is consistent.
                for (int i0 = 0; i0 < samples0; i0++) {
                    final Complex z0 = supplier0.get();
                    final double abs0 = z0.abs();
                    final double x0 = Math.abs(z0.getReal());
                    final double y0 = Math.abs(z0.getImaginary());
        
                    // Target the formula provided in the documentation for sqrt:
                    // sqrt(x0 + iy)
                    // t0 = sqrt( 2 (|x0| + |x0 + iy|) )
                    // if x0 >= 0: (t0/2, y0/t0)
                    // else : (|y0| / t0, t0/2 * sgn(y0))
                    // Note this is not the definitional polar computation using absolute and
                    // argument:
                    // real = sqrt(|z0|) * cos(0.5 * arg(z0))
                    // imag = sqrt(|z0|) * sin(0.5 * arg(z0))
                    final Complex c0 = z0.sqrt();
                    final double t0 = Math.sqrt(2 * (x0 + abs0));
                    if (z0.getReal() >= 0) {
                        Assertions.assertEquals(t0 / 2, c0.getReal());
    }
    }
    }

    @Test
    void testAbsVsSqrt_2_oe_2_oe() {
        final UniformRandomProvider rng = RandomSource.create(RandomSource.XO_RO_SHI_RO_128_PP);
        // Note: All methods implement scaling to ensure the magnitude can be computed.
        // Try very large or small numbers that will over/underflow to test that the
        // scaling
        // is consistent. Note that:
        // - sqrt will reduce the size of the real and imaginary
        // components when |z|>1 and increase them when |z|<1.

        // Each sample fails approximately 3% of the time if using a standard x^2+y^2 in
        // sqrt()
        // and high accuracy representation in abs().
        // Use 1000 samples to ensure the behavior is OK.
        // Do not use data which will over/underflow so we can use a simple computation in
        // the test
        // removed other assertion
                final int samples0 = 1000;
        final Supplier<Complex> supplier0 = () -> Complex.ofCartesian(createFixedExponentNumber(rng, -1000), createFixedExponentNumber(rng, -1000));
        // Note: All methods implement scaling to ensure the magnitude can be computed.
                // Try very large or small numbers that will over/underflow to test that the
                // scaling
                // is consistent.
                for (int i0 = 0; i0 < samples0; i0++) {
                    final Complex z0 = supplier0.get();
                    final double abs0 = z0.abs();
                    final double x0 = Math.abs(z0.getReal());
                    final double y0 = Math.abs(z0.getImaginary());
        
                    // Target the formula provided in the documentation for sqrt:
                    // sqrt(x0 + iy)
                    // t0 = sqrt( 2 (|x0| + |x0 + iy|) )
                    // if x0 >= 0: (t0/2, y0/t0)
                    // else : (|y0| / t0, t0/2 * sgn(y0))
                    // Note this is not the definitional polar computation using absolute and
                    // argument:
                    // real = sqrt(|z0|) * cos(0.5 * arg(z0))
                    // imag = sqrt(|z0|) * sin(0.5 * arg(z0))
                    final Complex c0 = z0.sqrt();
                    final double t0 = Math.sqrt(2 * (x0 + abs0));
                    if (z0.getReal() >= 0) {
                        // removed other assertion
                        Assertions.assertEquals(z0.getImaginary() / t0, c0.getImaginary());
    }
    }
    }

    @Test
    void testAbsVsSqrt_2_oe_3_oe() {
        final UniformRandomProvider rng = RandomSource.create(RandomSource.XO_RO_SHI_RO_128_PP);
        // Note: All methods implement scaling to ensure the magnitude can be computed.
        // Try very large or small numbers that will over/underflow to test that the
        // scaling
        // is consistent. Note that:
        // - sqrt will reduce the size of the real and imaginary
        // components when |z|>1 and increase them when |z|<1.

        // Each sample fails approximately 3% of the time if using a standard x^2+y^2 in
        // sqrt()
        // and high accuracy representation in abs().
        // Use 1000 samples to ensure the behavior is OK.
        // Do not use data which will over/underflow so we can use a simple computation in
        // the test
        // removed other assertion
                final int samples0 = 1000;
        final Supplier<Complex> supplier0 = () -> Complex.ofCartesian(createFixedExponentNumber(rng, -1000), createFixedExponentNumber(rng, -1000));
        // Note: All methods implement scaling to ensure the magnitude can be computed.
                // Try very large or small numbers that will over/underflow to test that the
                // scaling
                // is consistent.
                for (int i0 = 0; i0 < samples0; i0++) {
                    final Complex z0 = supplier0.get();
                    final double abs0 = z0.abs();
                    final double x0 = Math.abs(z0.getReal());
                    final double y0 = Math.abs(z0.getImaginary());
        
                    // Target the formula provided in the documentation for sqrt:
                    // sqrt(x0 + iy)
                    // t0 = sqrt( 2 (|x0| + |x0 + iy|) )
                    // if x0 >= 0: (t0/2, y0/t0)
                    // else : (|y0| / t0, t0/2 * sgn(y0))
                    // Note this is not the definitional polar computation using absolute and
                    // argument:
                    // real = sqrt(|z0|) * cos(0.5 * arg(z0))
                    // imag = sqrt(|z0|) * sin(0.5 * arg(z0))
                    final Complex c0 = z0.sqrt();
                    final double t0 = Math.sqrt(2 * (x0 + abs0));
                    if (z0.getReal() >= 0) {
                        // removed other assertion
                        // removed other assertion
                    } else {
                        Assertions.assertEquals(y0 / t0, c0.getReal());
    }
    }
    }

    @Test
    void testAbsVsSqrt_2_oe_4_oe() {
        final UniformRandomProvider rng = RandomSource.create(RandomSource.XO_RO_SHI_RO_128_PP);
        // Note: All methods implement scaling to ensure the magnitude can be computed.
        // Try very large or small numbers that will over/underflow to test that the
        // scaling
        // is consistent. Note that:
        // - sqrt will reduce the size of the real and imaginary
        // components when |z|>1 and increase them when |z|<1.

        // Each sample fails approximately 3% of the time if using a standard x^2+y^2 in
        // sqrt()
        // and high accuracy representation in abs().
        // Use 1000 samples to ensure the behavior is OK.
        // Do not use data which will over/underflow so we can use a simple computation in
        // the test
        // removed other assertion
                final int samples0 = 1000;
        final Supplier<Complex> supplier0 = () -> Complex.ofCartesian(createFixedExponentNumber(rng, -1000), createFixedExponentNumber(rng, -1000));
        // Note: All methods implement scaling to ensure the magnitude can be computed.
                // Try very large or small numbers that will over/underflow to test that the
                // scaling
                // is consistent.
                for (int i0 = 0; i0 < samples0; i0++) {
                    final Complex z0 = supplier0.get();
                    final double abs0 = z0.abs();
                    final double x0 = Math.abs(z0.getReal());
                    final double y0 = Math.abs(z0.getImaginary());
        
                    // Target the formula provided in the documentation for sqrt:
                    // sqrt(x0 + iy)
                    // t0 = sqrt( 2 (|x0| + |x0 + iy|) )
                    // if x0 >= 0: (t0/2, y0/t0)
                    // else : (|y0| / t0, t0/2 * sgn(y0))
                    // Note this is not the definitional polar computation using absolute and
                    // argument:
                    // real = sqrt(|z0|) * cos(0.5 * arg(z0))
                    // imag = sqrt(|z0|) * sin(0.5 * arg(z0))
                    final Complex c0 = z0.sqrt();
                    final double t0 = Math.sqrt(2 * (x0 + abs0));
                    if (z0.getReal() >= 0) {
                        // removed other assertion
                        // removed other assertion
                    } else {
                        // removed other assertion
                        Assertions.assertEquals(Math.copySign(t0 / 2, z0.getImaginary()), c0.getImaginary());
    }
    }
    }

    @Test
    void testAbsVsLog_1_oe_1_oe() {
        final UniformRandomProvider rng = RandomSource.create(RandomSource.XO_RO_SHI_RO_128_PP);
        // Note: All methods implement scaling to ensure the magnitude can be computed.
        // Try very large or small numbers that will over/underflow to test that the
        // scaling
        // is consistent. Note that:
        // - log will set the real component using log(|z|). This will massively reduce
        // the magnitude when |z| >> 1. Highest accuracy will be when |z| is as large
        // as possible before computing the log.

        // No test around |z| == 1 as a high accuracy computation is required:
        // Math.log1p(x*x+y*y-1)

        // Each sample fails approximately 25% of the time if using a standard x^2+y^2 in
        // log()
        // and high accuracy representation in abs(). Use 100 samples to ensure the
        // behavior is OK.
                final int samples0 = 100;
        final Supplier<Complex> supplier0 = () -> Complex.ofCartesian(createFixedExponentNumber(rng, 1022), createFixedExponentNumber(rng, 1022));
        // Note: All methods implement scaling to ensure the magnitude can be computed.
                // Try very large or small numbers that will over/underflow to test that the
                // scaling
                // is consistent.
                for (int i0 = 0; i0 < samples0; i0++) {
                    final Complex z0 = supplier0.get();
                    final double abs0 = z0.abs();
                    final double x0 = Math.abs(z0.getReal());
                    final double y0 = Math.abs(z0.getImaginary());
        
                    // log(x0 + iy) = log(|x0 + i0 y0|) + i0 arg(x0 + i0 y0)
                    // Only test the real component
                    final Complex c0 = z0.log();
                    Assertions.assertEquals(Math.log(abs0), c0.getReal());
    }
    }

    @Test
    void testAbsVsLog_2_oe_1_oe() {
        final UniformRandomProvider rng = RandomSource.create(RandomSource.XO_RO_SHI_RO_128_PP);
        // Note: All methods implement scaling to ensure the magnitude can be computed.
        // Try very large or small numbers that will over/underflow to test that the
        // scaling
        // is consistent. Note that:
        // - log will set the real component using log(|z|). This will massively reduce
        // the magnitude when |z| >> 1. Highest accuracy will be when |z| is as large
        // as possible before computing the log.

        // No test around |z| == 1 as a high accuracy computation is required:
        // Math.log1p(x*x+y*y-1)

        // Each sample fails approximately 25% of the time if using a standard x^2+y^2 in
        // log()
        // and high accuracy representation in abs(). Use 100 samples to ensure the
        // behavior is OK.
        // removed other assertion
                final int samples0 = 100;
        final Supplier<Complex> supplier0 = () -> Complex.ofCartesian(createFixedExponentNumber(rng, -1022), createFixedExponentNumber(rng, -1022));
        // Note: All methods implement scaling to ensure the magnitude can be computed.
                // Try very large or small numbers that will over/underflow to test that the
                // scaling
                // is consistent.
                for (int i0 = 0; i0 < samples0; i0++) {
                    final Complex z0 = supplier0.get();
                    final double abs0 = z0.abs();
                    final double x0 = Math.abs(z0.getReal());
                    final double y0 = Math.abs(z0.getImaginary());
        
                    // log(x0 + iy) = log(|x0 + i0 y0|) + i0 arg(x0 + i0 y0)
                    // Only test the real component
                    final Complex c0 = z0.log();
                    Assertions.assertEquals(Math.log(abs0), c0.getReal());
    }
    }

}
