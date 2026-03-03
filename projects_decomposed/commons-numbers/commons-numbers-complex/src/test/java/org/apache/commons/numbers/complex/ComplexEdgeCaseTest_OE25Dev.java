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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

/**
 * Edge case tests for the functions defined by the C.99 standard for complex numbers
 * defined in ISO/IEC 9899, Annex G.
 *
 * <p>The test contained here are specifically written to target edge cases of finite valued
 * input values that cause overflow/underflow during the computation.
 *
 * <p>The test data is generated from a known implementation of the standard.
 *
 * @see <a href="http://www.open-std.org/JTC1/SC22/WG14/www/standards">
 *    ISO/IEC 9899 - Programming languages - C</a>
 */
class ComplexEdgeCaseTest_OE25Dev {
    private static final double inf = Double.POSITIVE_INFINITY;
    private static final double nan = Double.NaN;

    /**
     * Assert the operation on the complex number is equal to the expected value.
     *
     * <p>The results are are considered equal if there are no floating-point values between them.
     *
     * @param a Real part.
     * @param b Imaginary part.
     * @param name The operation name.
     * @param operation The operation.
     * @param x Expected real part.
     * @param y Expected imaginary part.
     */
    private static void assertComplex(double a, double b,
            String name, UnaryOperator<Complex> operation,
            double x, double y) {
        assertComplex(a, b, name, operation, x, y, 1);
    }

    /**
     * Assert the operation on the complex number is equal to the expected value.
     *
     * <p>The results are considered equal within the provided units of least
     * precision. The maximum count of numbers allowed between the two values is
     * {@code maxUlps - 1}.
     *
     * @param a Real part.
     * @param b Imaginary part.
     * @param name The operation name.
     * @param operation The operation.
     * @param x Expected real part.
     * @param y Expected imaginary part.
     * @param maxUlps the maximum units of least precision between the two values
     */
    private static void assertComplex(double a, double b,
            String name, UnaryOperator<Complex> operation,
            double x, double y, long maxUlps) {
        final Complex c = Complex.ofCartesian(a, b);
        final Complex e = Complex.ofCartesian(x, y);
        CReferenceTest.assertComplex(c, name, operation, e, maxUlps);
    }

    /**
     * Assert the operation on the complex numbers is equal to the expected value.
     *
     * <p>The results are considered equal if there are no floating-point values between them.
     *
     * @param a Real part of first number.
     * @param b Imaginary part of first number.
     * @param c Real part of second number.
     * @param d Imaginary part of second number.
     * @param name The operation name.
     * @param operation The operation.
     * @param x Expected real part.
     * @param y Expected imaginary part.
     */
    // CHECKSTYLE: stop ParameterNumberCheck
    private static void assertComplex(double a, double b, double c, double d,
            String name, BiFunction<Complex, Complex, Complex> operation,
            double x, double y) {
        assertComplex(a, b, c, d, name, operation, x, y, 1);
    }

    /**
     * Assert the operation on the complex numbers is equal to the expected value.
     *
     * <p>The results are considered equal within the provided units of least
     * precision. The maximum count of numbers allowed between the two values is
     * {@code maxUlps - 1}.
     *
     * @param a Real part of first number.
     * @param b Imaginary part of first number.
     * @param c Real part of second number.
     * @param d Imaginary part of second number.
     * @param name The operation name
     * @param operation the operation
     * @param x Expected real part.
     * @param y Expected imaginary part.
     * @param maxUlps the maximum units of least precision between the two values
     */
    private static void assertComplex(double a, double b, double c, double d,
            String name, BiFunction<Complex, Complex, Complex> operation,
            double x, double y, long maxUlps) {
        final Complex c1 = Complex.ofCartesian(a, b);
        final Complex c2 = Complex.ofCartesian(c, d);
        final Complex e = Complex.ofCartesian(x, y);
        CReferenceTest.assertComplex(c1, c2, name, operation, e, maxUlps);
    }

    // acosh is defined by acos so is not tested

    // asinh is defined by asin so is not tested

    /**
     * Assert the Complex log function using BigDecimal to compute the field norm
     * {@code x*x + y*y} and then {@link Math#log1p(double)} to compute the log of
     * the modulus \ using {@code 0.5 * log1p(x*x + y*y - 1)}. This test is for the
     * extreme case for performance around {@code sqrt(x*x + y*y) = 1} where using
     * {@link Math#log(double)} will fail dramatically.
     *
     * @param x the real value of the complex
     * @param y the imaginary value of the complex
     * @param maxUlps the maximum units of least precision between the two values
     */
    private static void assertLog(double x, double y, long maxUlps) {
        // Compute the best value we can
        final BigDecimal bx = new BigDecimal(x);
        final BigDecimal by = new BigDecimal(y);
        final BigDecimal exact = bx.multiply(bx).add(by.multiply(by)).subtract(BigDecimal.ONE);
        final double real = 0.5 * Math.log1p(exact.doubleValue());
        final double imag = Math.atan2(y, x);
        assertComplex(x, y, "log", Complex::log, real, imag, maxUlps);
    }

    // Note: inf/nan edge cases for
    // multiply/divide are tested in CStandardTest

    @Test
    void testPow() {
        final String name = "pow";
        final BiFunction<Complex, Complex, Complex> operation = Complex::pow;

        // pow(Complex) is log().multiply(Complex).exp()
        // All are overflow safe and handle infinities as defined in the C99 standard.
        // TODO: Test edge cases with:
        // Double.MAX_VALUE, Double.MIN_NORMAL, Inf
        // using other library implementations.

        // Test NaN
        assertComplex(1, 1, nan, nan, name, operation, nan, nan);
        assertComplex(nan, nan, 1, 1, name, operation, nan, nan);
        assertComplex(nan, 1, 1, 1, name, operation, nan, nan);
        assertComplex(1, nan, 1, 1, name, operation, nan, nan);
        assertComplex(1, 1, nan, 1, name, operation, nan, nan);
        assertComplex(1, 1, 1, nan, name, operation, nan, nan);

        // Test overflow.
        assertComplex(Double.MAX_VALUE, 1, 2, 2, name, operation, inf, -inf);
        assertComplex(1, Double.MAX_VALUE, 2, 2, name, operation, -inf, inf);
    }

    @Test
    void testCosh_10_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        Assertions.assertEquals(inf, Math.exp(x));
    }

    @Test
    void testSinh_10_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        Assertions.assertEquals(inf, Math.exp(x));
    }

    @Test
    void testTanh_10_oe() {
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;





        double x = 709.783 / 2;
        double y = Math.PI / 4;
        Assertions.assertEquals(1.0, Math.sin(2 * y), 1e-16);
    }

    @Test
    void testTanh_11_oe() {
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;





        double x = 709.783 / 2;
        double y = Math.PI / 4;
        Assertions.assertEquals(Double.POSITIVE_INFINITY, Math.exp(2 * x));
    }

    @Test
    void testSqrt_1_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;


        Assertions.assertEquals(0, Math.atan2(0, 1));
    }

    @Test
    void testSqrt_2_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;


        Assertions.assertEquals(Math.PI, Math.atan2(0, -1));
    }

    @Test
    void testSqrt_3_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;


        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        Assertions.assertNotEquals(0.0, Math.cos(Math.PI / 2), "Expected cos(pi/2) to be non-zero");
    }

    @Test
    void testSqrt_4_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;


        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        Assertions.assertEquals(0.0, Math.cos(Math.PI / 2), 6.123233995736766e-17);
    }

    @Test
    void testSqrt_5_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;


        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        Assertions.assertEquals(Math.PI / 2, Math.atan2(1, 0));
    }

    @Test
    void testSqrt_6_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;


        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        Assertions.assertEquals(-Math.PI / 2, Math.atan2(-1, 0));
    }

    @Test
    void testSqrt_7_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;


        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        Assertions.assertNotEquals(cosArgIm, sinArgIm, "Expected cos(pi/4) to not exactly equal sin(pi/4)");
    }

    @Test
    void testSqrt_8_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;


        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        Assertions.assertEquals(root2over2, cosArgIm, 0, "Expected cos(pi/4) to be sqrt(2) / 2");
    }

    @Test
    void testSqrt_9_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;


        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        Assertions.assertEquals(root2over2, sinArgIm, ulp, "Expected sin(pi/4) to be 1 ulp from sqrt(2) / 2");
    }

    @Test
    void testSqrt_14_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;


        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        for (final double a : new double[] {0.5, 1.0, 1.2322, 345345.234523}) {
            final double rootA = Math.sqrt(a);
        }

        double a = Double.MAX_VALUE;
        final double b = a / 4;
        Assertions.assertEquals(inf, Complex.ofCartesian(a, b).abs(), "Expected overflow");
    }

    @Test
    void testDivide_1_oe() {
        final String name = "divide";
        final BiFunction<Complex, Complex, Complex> operation = Complex::divide;


        double x = Math.scalb(Double.MIN_VALUE, 1023 + 51);
        Assertions.assertEquals(1.0, x);
    }

    @Test
    void testAcos_1_oe_1_oe() {
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = huge;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 0.06241880999595735;
        final double y0 = -356.27960012801969;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAcos_2_oe_1_oe() {
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = huge;
        final double b0 = medium;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 3.7291703656001039e-153;
        final double y0 = -356.27765080781188;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAcos_3_oe_1_oe() {
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = huge;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 2.2250738585072019e-308;
        final double y0 = -356.27765080781188;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAcos_4_oe_1_oe() {
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = big;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 0.78539816339744828;
        final double y0 = -353.85163567585209;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAcos_5_oe_1_oe() {
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = big;
        final double b0 = medium;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 5.9666725849601662e-152;
        final double y0 = -353.50506208557209;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAcos_6_oe_1_oe() {
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = big;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 3.560118173611523e-307;
        final double y0 = -353.50506208557209;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAcos_7_oe_1_oe() {
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = medium;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.5707963267948966;
        final double y0 = -353.50506208557209;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAcos_8_oe_1_oe() {
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = medium;
        final double b0 = medium;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 0.78541066339744181;
        final double y0 = -5.6448909570623842;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAcos_9_oe_1_oe() {
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = medium;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 5.9669709409662999e-156;
        final double y0 = -5.298292365610485;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAcos_10_oe_1_oe() {
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = small;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.5707963267948966;
        final double y0 = -353.50506208557209;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAcos_11_oe_1_oe() {
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = small;
        final double b0 = medium;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.5707963267948966;
        final double y0 = -5.2983423656105888;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAcos_12_oe_1_oe() {
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = small;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.5707963267948966;
        final double y0 = -5.9666725849601654e-154;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAcos_13_oe_1_oe() {
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = 1;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 2.4426773395109241e-77;
        final double y0 = -2.4426773395109241e-77;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAcos_14_oe_1_oe() {
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = 1.00000002785941;
        final double b0 = 5.72464869028403e-200;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 2.4252018043912224e-196;
        final double y0 = -0.00023604834149293664;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAsin_1_oe_1_oe() {
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = huge;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.5083775167989393;
        final double y0 = 356.27960012801969;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAsin_2_oe_1_oe() {
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = huge;
        final double b0 = medium;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.5707963267948966;
        final double y0 = 356.27765080781188;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAsin_3_oe_1_oe() {
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = huge;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.5707963267948966;
        final double y0 = 356.27765080781188;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAsin_4_oe_1_oe() {
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = big;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 0.78539816339744828;
        final double y0 = 353.85163567585209;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAsin_5_oe_1_oe() {
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = big;
        final double b0 = medium;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.5707963267948966;
        final double y0 = 353.50506208557209;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAsin_6_oe_1_oe() {
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = big;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.5707963267948966;
        final double y0 = 353.50506208557209;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAsin_7_oe_1_oe() {
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = medium;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 5.9666725849601662e-152;
        final double y0 = 353.50506208557209;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAsin_8_oe_1_oe() {
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = medium;
        final double b0 = medium;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 0.78538566339745486;
        final double y0 = 5.6448909570623842;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAsin_9_oe_1_oe() {
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = medium;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.5707963267948966;
        final double y0 = 5.298292365610485;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAsin_10_oe_1_oe() {
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = small;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 3.560118173611523e-307;
        final double y0 = 353.50506208557209;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAsin_11_oe_1_oe() {
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = small;
        final double b0 = medium;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 5.9663742737040751e-156;
        final double y0 = 5.2983423656105888;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAsin_12_oe_1_oe() {
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = small;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 5.9666725849601654e-154;
        final double y0 = 5.9666725849601654e-154;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAsin_13_oe_1_oe() {
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = 1;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.5707963267948966;
        final double y0 = 2.4426773395109241e-77;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAsin_14_oe_1_oe() {
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
                final double a0 = 1.00000002785941;
        final double b0 = 5.72464869028403e-200;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.5707963267948966;
        final double y0 = 0.00023604834149293664;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAtanh_1_oe_1_oe() {
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
                final double a0 = big;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 7.4583407312002067e-155;
        final double y0 = 1.5707963267948966;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAtanh_2_oe_1_oe() {
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
                final double a0 = big;
        final double b0 = medium;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.4916681462400417e-154;
        final double y0 = 1.5707963267948966;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAtanh_3_oe_1_oe() {
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
                final double a0 = big;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.4916681462400417e-154;
        final double y0 = 1.5707963267948966;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAtanh_4_oe_1_oe() {
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
                final double a0 = medium;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 2.225073858507202e-306;
        final double y0 = 1.5707963267948966;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAtanh_5_oe_1_oe() {
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
                final double a0 = medium;
        final double b0 = medium;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 0.0049999166641667555;
        final double y0 = 1.5657962434640633;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAtanh_6_oe_1_oe() {
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
                final double a0 = medium;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 0.010000333353334761;
        final double y0 = 1.5707963267948966;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAtanh_7_oe_1_oe() {
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
                final double a0 = small;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 0;
        final double y0 = 1.5707963267948966;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAtanh_8_oe_1_oe() {
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
                final double a0 = small;
        final double b0 = medium;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 2.9830379886812147e-158;
        final double y0 = 1.5607966601082315;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAtanh_9_oe_1_oe() {
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
                final double a0 = small;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 2.9833362924800827e-154;
        final double y0 = 2.9833362924800827e-154;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAtanh_10_oe_1_oe() {
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
                final double a0 = inf;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 0;
        final double y0 = 1.5707963267948966;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testAtanh_11_oe_1_oe() {
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
                final double a0 = big;
        final double b0 = inf;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 0;
        final double y0 = 1.5707963267948966;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_1_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = big;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_2_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = big;
        final double b0 = medium;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_3_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = big;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_4_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = medium;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -3.7621493762972804;
        final double y0 = 0.017996317370418576;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_5_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = medium;
        final double b0 = medium;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -1.5656258353157435;
        final double y0 = 3.297894836311237;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_6_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = medium;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 3.7621956910836314;
        final double y0 = 8.0700322819551687e-308;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_7_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = small;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -0.99998768942655991;
        final double y0 = 1.1040715888508271e-310;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_8_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = small;
        final double b0 = medium;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -0.41614683654714241;
        final double y0 = 2.0232539340376892e-308;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_9_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = small;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1;
        final double y0 = 0;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_11_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = x;
        final double b0 = 0;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 8.9910466927705402e+307;
        final double y0 = 0.0;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_12_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = -x;
        final double b0 = 0;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 8.9910466927705402e+307;
        final double y0 = -0.0;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_13_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = x;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 8.9910466927705402e+307;
        final double y0 = 2.0005742956701358;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_14_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = -x;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 8.9910466927705402e+307;
        final double y0 = -2.0005742956701358;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_15_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = x;
        final double b0 = tiny;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 8.9910466927705402e+307;
        final double y0 = 4.4421672910524807e-16;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_16_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = -x;
        final double b0 = tiny;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 8.9910466927705402e+307;
        final double y0 = -4.4421672910524807e-16;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_17_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = 2 * x;
        final double b0 = tiny;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = inf;
        final double y0 = 7.9879467061901743e+292;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_18_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = -2 * x;
        final double b0 = tiny;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = inf;
        final double y0 = -7.9879467061901743e+292;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_19_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = 3 * x;
        final double b0 = tiny;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_20_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = -3 * x;
        final double b0 = tiny;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = inf;
        final double y0 = -inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_21_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        final double pi2 = Math.PI / 2;
                final double a0 = x;
        final double b0 = pi2;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 5.5054282766429199e+291;
        final double y0 = 8.9910466927705402e+307;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_22_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        final double pi2 = Math.PI / 2;
                final double a0 = -x;
        final double b0 = pi2;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 5.5054282766429199e+291;
        final double y0 = -8.9910466927705402e+307;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_23_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        final double pi2 = Math.PI / 2;
                final double a0 = 2 * x;
        final double b0 = pi2;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_24_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        final double pi2 = Math.PI / 2;
                final double a0 = -2 * x;
        final double b0 = pi2;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = inf;
        final double y0 = -inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_25_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        final double pi2 = Math.PI / 2;
                final double a0 = 3 * x;
        final double b0 = pi2;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testCosh_26_oe_1_oe() {
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        final double pi2 = Math.PI / 2;
                final double a0 = -3 * x;
        final double b0 = pi2;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = inf;
        final double y0 = -inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_1_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = big;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_2_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = big;
        final double b0 = medium;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_3_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = big;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_4_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = medium;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -3.6268157591156114;
        final double y0 = 0.018667844927220067;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_5_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = medium;
        final double b0 = medium;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -1.5093064853236158;
        final double y0 = 3.4209548611170133;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_6_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = medium;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 3.626860407847019;
        final double y0 = 8.3711632828186228e-308;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_7_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = small;
        final double b0 = big;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -2.2250464665720564e-308;
        final double y0 = 0.004961954789184062;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_8_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = small;
        final double b0 = medium;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -9.2595744730151568e-309;
        final double y0 = 0.90929742682568171;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_9_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
                final double a0 = small;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 2.2250738585072014e-308;
        final double y0 = 2.2250738585072014e-308;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_11_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = x;
        final double b0 = 0;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 8.9910466927705402e+307;
        final double y0 = 0.0;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_12_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = -x;
        final double b0 = 0;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -8.9910466927705402e+307;
        final double y0 = 0.0;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_13_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = x;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 8.9910466927705402e+307;
        final double y0 = 2.0005742956701358;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_14_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = -x;
        final double b0 = small;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -8.9910466927705402e+307;
        final double y0 = 2.0005742956701358;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_15_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = x;
        final double b0 = tiny;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 8.9910466927705402e+307;
        final double y0 = 4.4421672910524807e-16;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_16_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = -x;
        final double b0 = tiny;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -8.9910466927705402e+307;
        final double y0 = 4.4421672910524807e-16;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_17_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = 2 * x;
        final double b0 = tiny;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = inf;
        final double y0 = 7.9879467061901743e+292;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_18_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = -2 * x;
        final double b0 = tiny;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -inf;
        final double y0 = 7.9879467061901743e+292;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_19_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = 3 * x;
        final double b0 = tiny;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_20_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
                final double a0 = -3 * x;
        final double b0 = tiny;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_21_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        final double pi2 = Math.PI / 2;
                final double a0 = x;
        final double b0 = pi2;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 5.5054282766429199e+291;
        final double y0 = 8.9910466927705402e+307;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_22_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        final double pi2 = Math.PI / 2;
                final double a0 = -x;
        final double b0 = pi2;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -5.5054282766429199e+291;
        final double y0 = 8.9910466927705402e+307;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_23_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        final double pi2 = Math.PI / 2;
                final double a0 = 2 * x;
        final double b0 = pi2;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_24_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        final double pi2 = Math.PI / 2;
                final double a0 = -2 * x;
        final double b0 = pi2;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_25_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        final double pi2 = Math.PI / 2;
                final double a0 = 3 * x;
        final double b0 = pi2;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSinh_26_oe_1_oe() {
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;


        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        final double pi2 = Math.PI / 2;
                final double a0 = -3 * x;
        final double b0 = pi2;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testTanh_1_oe_1_oe() {
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;

                final double a0 = 1;
        final double b0 = Double.MAX_VALUE;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 0.76160203106265523;
        final double y0 = -0.0020838895895863505;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testTanh_2_oe_1_oe() {
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;


                final double a0 = 1;
        final double b0 = Double.MIN_NORMAL;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 0.76159415595576485;
        final double y0 = 9.344739287691424e-309;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testTanh_3_oe_1_oe() {
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;


                final double a0 = 1;
        final double b0 = Double.MIN_VALUE;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 0.76159415595576485;
        final double y0 = 0;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testTanh_4_oe_1_oe() {
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;



                final double a0 = Double.MAX_VALUE;
        final double b0 = 1;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1;
        final double y0 = 0.0;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testTanh_5_oe_1_oe() {
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;



                final double a0 = Double.MAX_VALUE;
        final double b0 = -1;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1;
        final double y0 = -0.0;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testTanh_6_oe_1_oe() {
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;



                final double a0 = -Double.MAX_VALUE;
        final double b0 = 1;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -1;
        final double y0 = 0.0;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testTanh_7_oe_1_oe() {
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;



                final double a0 = -Double.MAX_VALUE;
        final double b0 = -1;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -1;
        final double y0 = -0.0;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testTanh_8_oe_1_oe() {
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;




                final double a0 = Double.MIN_NORMAL;
        final double b0 = 1;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 7.6220323800193346e-308;
        final double y0 = 1.5574077246549021;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testTanh_9_oe_1_oe() {
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;




                final double a0 = Double.MIN_VALUE;
        final double b0 = 1;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.4821969375237396e-323;
        final double y0 = 1.5574077246549021;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testTanh_12_oe_1_oe() {
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;





        double x = 709.783 / 2;
        double y = Math.PI / 4;
                final double a0 = x;
        final double b0 = y;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1;
        final double y0 = 1.1122175583895849e-308;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testExp_1_oe_1_oe() {
        final String name = "exp";
        final UnaryOperator<Complex> operation = Complex::exp;


                final double a0 = 1000;
        final double b0 = 0;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = inf;
        final double y0 = 0.0;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testExp_2_oe_1_oe() {
        final String name = "exp";
        final UnaryOperator<Complex> operation = Complex::exp;


                final double a0 = 1000;
        final double b0 = 1;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testExp_3_oe_1_oe() {
        final String name = "exp";
        final UnaryOperator<Complex> operation = Complex::exp;


                final double a0 = 1000;
        final double b0 = 2;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testExp_4_oe_1_oe() {
        final String name = "exp";
        final UnaryOperator<Complex> operation = Complex::exp;


                final double a0 = 1000;
        final double b0 = 3;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -inf;
        final double y0 = inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testExp_5_oe_1_oe() {
        final String name = "exp";
        final UnaryOperator<Complex> operation = Complex::exp;


                final double a0 = 1000;
        final double b0 = 4;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -inf;
        final double y0 = -inf;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testExp_6_oe_1_oe() {
        final String name = "exp";
        final UnaryOperator<Complex> operation = Complex::exp;



                final double a0 = -1000;
        final double b0 = 0;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 0.0;
        final double y0 = 0.0;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testExp_7_oe_1_oe() {
        final String name = "exp";
        final UnaryOperator<Complex> operation = Complex::exp;



                final double a0 = -1000;
        final double b0 = 1;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 0.0;
        final double y0 = 0.0;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testExp_8_oe_1_oe() {
        final String name = "exp";
        final UnaryOperator<Complex> operation = Complex::exp;



                final double a0 = -1000;
        final double b0 = 2;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -0.0;
        final double y0 = 0.0;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testExp_9_oe_1_oe() {
        final String name = "exp";
        final UnaryOperator<Complex> operation = Complex::exp;



                final double a0 = -1000;
        final double b0 = 3;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -0.0;
        final double y0 = 0.0;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testExp_10_oe_1_oe() {
        final String name = "exp";
        final UnaryOperator<Complex> operation = Complex::exp;



                final double a0 = -1000;
        final double b0 = 4;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -0.0;
        final double y0 = -0.0;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testLog_1_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;


                final double a0 = -Double.MAX_VALUE;
        final double b0 = Double.MAX_VALUE;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 7.101292864836639e2;
        final double y0 = Math.PI * 3 / 4;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testLog_2_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;


                final double a0 = Double.MAX_VALUE;
        final double b0 = Double.MAX_VALUE;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 7.101292864836639e2;
        final double y0 = Math.PI / 4;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testLog_3_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;


                final double a0 = -Double.MAX_VALUE;
        final double b0 = Double.MAX_VALUE / 4;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 7.098130252042921e2;
        final double y0 = 2.896613990462929;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testLog_5_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



                final double a0 = -Double.MIN_NORMAL;
        final double b0 = Double.MIN_NORMAL;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -708.04984494198413;
        final double y0 = 2.3561944901923448;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testLog_6_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



                final double a0 = Double.MIN_NORMAL;
        final double b0 = Double.MIN_NORMAL;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = -708.04984494198413;
        final double y0 = 0.78539816339744828;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testLog_7_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
                final double a0 = -Double.MIN_VALUE;
        final double b0 = Double.MIN_VALUE;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = expected;
        final double y0 = Math.atan2(1, -1);
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testLog_8_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
                final double a0 = -Double.MIN_VALUE;
        final double b0 = 2 * Double.MIN_VALUE;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = expected;
        final double y0 = Math.atan2(2, -1);
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testLog_9_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;




                final double x0 = 1.0001;
        final double y0 = Math.sqrt(1.2 - 1.0001 * 1.0001);
        final long maxUlps0 = 1;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_10_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;




                final double x0 = 1.0001;
        final double y0 = Math.sqrt(1.1 - 1.0001 * 1.0001);
        final long maxUlps0 = 1;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_11_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;




                final double x0 = 1.0001;
        final double y0 = Math.sqrt(1.02 - 1.0001 * 1.0001);
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_12_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;




                final double x0 = 1.0001;
        final double y0 = Math.sqrt(1.01 - 1.0001 * 1.0001);
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_13_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;






                final double x0 = 0.99;
        final double y0 = 0.00001;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_14_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;






                final double x0 = 0.95;
        final double y0 = 0.00001;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_15_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;






                final double x0 = 0.9;
        final double y0 = 0.00001;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_16_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;






                final double x0 = 0.85;
        final double y0 = 0.00001;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_17_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;






                final double x0 = 0.8;
        final double y0 = 0.00001;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_18_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;






                final double x0 = 0.75;
        final double y0 = 0.00001;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_19_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;






                final double x0 = 0.7;
        final double y0 = 0.00001;
        final long maxUlps0 = 2;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_20_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;








                final double x0 = 0.97;
        final double y0 = Math.sqrt(0.99 - 0.97 * 0.97);
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_21_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;








                final double x0 = 0.97;
        final double y0 = Math.sqrt(1.01 - 0.97 * 0.97);
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_22_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;









                final double x0 = 0.97;
        final double y0 = Math.sqrt(0.9999 - 0.97 * 0.97);
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_23_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;










        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                                final double x0 = magnitude[j] * Math.sin(theta);
                final double y0 = magnitude[j] * Math.cos(theta);
                final long maxUlps0 = ulps[j];
                        final BigDecimal bx0 = new BigDecimal(x0);
                        final BigDecimal by0 = new BigDecimal(y0);
                        final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                        final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                        final double imag0 = Math.atan2(y0, x0);
                        assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }
    }
    }

    @Test
    void testLog_24_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;










        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
            }
        }

        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
                        final double x0 = Math.sin(theta);
            final double y0 = Math.cos(theta);
            final long maxUlps0 = 0;
                    final BigDecimal bx0 = new BigDecimal(x0);
                    final BigDecimal by0 = new BigDecimal(y0);
                    final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                    final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                    final double imag0 = Math.atan2(y0, x0);
                    assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }
    }

    @Test
    void testLog_25_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;










        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
            }
        }

        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
        }

        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
                final double x0 = down1;
        final double y0 = Double.MIN_NORMAL;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_26_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;










        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
            }
        }

        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
        }

        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
                final double x0 = down1;
        final double y0 = Double.MIN_VALUE;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_27_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;










        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
            }
        }

        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
        }

        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
                final double x0 = up1;
        final double y0 = Double.MIN_NORMAL;
        final long maxUlps0 = 2;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_28_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;










        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
            }
        }

        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
        }

        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
                final double x0 = up1;
        final double y0 = Double.MIN_VALUE;
        final long maxUlps0 = 2;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_29_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;










        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
            }
        }

        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
        }

        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);

                final double x0 = 0.007640392270319105;
        final double y0 = 0.9999708117770016;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_30_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;










        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
            }
        }

        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
        }

        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);

                final double x0 = 0.40158433204881533;
        final double y0 = 0.9158220483548684;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_31_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;










        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
            }
        }

        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
        }

        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);

                final double x0 = 0.13258789214774552;
        final double y0 = 0.9911712520325727;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_32_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;










        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
            }
        }

        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
        }

        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);

                final double x0 = 0.2552206803398717;
        final double y0 = 0.9668828286441191;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_33_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;










        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
            }
        }

        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
        }

        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);

                final double x0 = 0.4650816500945186;
        final double y0 = 0.8852677892848919;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_34_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;










        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
            }
        }

        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
        }

        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);

                final double x0 = 0.06548693057069123;
        final double y0 = 0.9978534270745526;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_35_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;










        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
            }
        }

        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
        }

        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);

                final double x0 = 0.08223027214657339;
        final double y0 = 0.9966133564942327;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_36_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;










        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
            }
        }

        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
        }

        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);

                final double x0 = 0.06548693057069123;
        final double y0 = 0.9978534270745526;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_37_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;










        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
            }
        }

        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
        }

        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);

                final double x0 = 0.04590800199633988;
        final double y0 = 0.9989456718724518;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testLog_38_oe_1_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;



        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;










        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
            }
        }

        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
        }

        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);

                final double x0 = 0.3019636508581243;
        final double y0 = 0.9533194394118022;
        final long maxUlps0 = 0;
                final BigDecimal bx0 = new BigDecimal(x0);
                final BigDecimal by0 = new BigDecimal(y0);
                final BigDecimal exact0 = bx0.multiply(bx0).add(by0.multiply(by0)).subtract(BigDecimal.ONE);
                final double real0 = 0.5 * Math.log1p(exact0.doubleValue());
                final double imag0 = Math.atan2(y0, x0);
                assertComplex(x0, y0, "log", Complex::log, real0, imag0, maxUlps0);
    }

    @Test
    void testSqrt_18_oe_1_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;


        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        for (final double a : new double[] {0.5, 1.0, 1.2322, 345345.234523}) {
            final double rootA = Math.sqrt(a);
        }

        double a = Double.MAX_VALUE;
        final double b = a / 4;
        final double newAbs = 1.3612566508088272E154;

        a = Double.MAX_VALUE / 2;
                final double a0 = a;
        final double b0 = a;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.0416351505169177e+154;
        final double y0 = 4.3145940638864758e+153;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSqrt_20_oe_1_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;


        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        for (final double a : new double[] {0.5, 1.0, 1.2322, 345345.234523}) {
            final double rootA = Math.sqrt(a);
        }

        double a = Double.MAX_VALUE;
        final double b = a / 4;
        final double newAbs = 1.3612566508088272E154;

        a = Double.MAX_VALUE / 2;
                final double a0 = a;
        final double b0 = -a;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.0416351505169177e+154;
        final double y0 = -4.3145940638864758e+153;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSqrt_21_oe_1_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;


        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        for (final double a : new double[] {0.5, 1.0, 1.2322, 345345.234523}) {
            final double rootA = Math.sqrt(a);
        }

        double a = Double.MAX_VALUE;
        final double b = a / 4;
        final double newAbs = 1.3612566508088272E154;

        a = Double.MAX_VALUE / 2;

        a = Double.MIN_NORMAL;
                final double a0 = -a;
        final double b0 = a;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 6.7884304867749663e-155;
        final double y0 = 1.6388720948399111e-154;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSqrt_22_oe_1_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;


        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        for (final double a : new double[] {0.5, 1.0, 1.2322, 345345.234523}) {
            final double rootA = Math.sqrt(a);
        }

        double a = Double.MAX_VALUE;
        final double b = a / 4;
        final double newAbs = 1.3612566508088272E154;

        a = Double.MAX_VALUE / 2;

        a = Double.MIN_NORMAL;
                final double a0 = a;
        final double b0 = a;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.6388720948399111e-154;
        final double y0 = 6.7884304867749655e-155;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSqrt_23_oe_1_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;


        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        for (final double a : new double[] {0.5, 1.0, 1.2322, 345345.234523}) {
            final double rootA = Math.sqrt(a);
        }

        double a = Double.MAX_VALUE;
        final double b = a / 4;
        final double newAbs = 1.3612566508088272E154;

        a = Double.MAX_VALUE / 2;

        a = Double.MIN_NORMAL;
                final double a0 = -a;
        final double b0 = -a;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 6.7884304867749663e-155;
        final double y0 = -1.6388720948399111e-154;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

    @Test
    void testSqrt_24_oe_1_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;


        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        for (final double a : new double[] {0.5, 1.0, 1.2322, 345345.234523}) {
            final double rootA = Math.sqrt(a);
        }

        double a = Double.MAX_VALUE;
        final double b = a / 4;
        final double newAbs = 1.3612566508088272E154;

        a = Double.MAX_VALUE / 2;

        a = Double.MIN_NORMAL;
                final double a0 = a;
        final double b0 = -a;
        final String name0 = name;
        final UnaryOperator<Complex> operation0 = operation;
        final double x0 = 1.6388720948399111e-154;
        final double y0 = -6.7884304867749655e-155;
        assertComplex(a0, b0, name0, operation0, x0, y0, 1);
    }

@Test
    void testLog_4_oe() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;


        assertComplex(Double.MAX_VALUE, Double.MAX_VALUE / 4, name, operation, 7.098130252042921e2, 2.449786631268641e-1, 2);
    }

}
