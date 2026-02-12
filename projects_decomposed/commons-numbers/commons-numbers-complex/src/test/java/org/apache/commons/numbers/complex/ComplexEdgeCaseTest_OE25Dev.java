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


}
