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

    @Test
    void testAcos() {
        // acos(z) = (pi / 2) + i ln(iz + sqrt(1 - z^2))
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // The results have been generated using g++ -std=c++11 acos.
        // xp1 * xm1 will overflow:
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        assertComplex(huge, big, name, operation, 0.06241880999595735, -356.27960012801969);
        assertComplex(huge, medium, name, operation, 3.7291703656001039e-153, -356.27765080781188);
        assertComplex(huge, small, name, operation, 2.2250738585072019e-308, -356.27765080781188);
        assertComplex(big, big, name, operation, 0.78539816339744828, -353.85163567585209);
        assertComplex(big, medium, name, operation, 5.9666725849601662e-152, -353.50506208557209);
        assertComplex(big, small, name, operation, 3.560118173611523e-307, -353.50506208557209);
        assertComplex(medium, big, name, operation, 1.5707963267948966, -353.50506208557209);
        assertComplex(medium, medium, name, operation, 0.78541066339744181, -5.6448909570623842);
        assertComplex(medium, small, name, operation, 5.9669709409662999e-156, -5.298292365610485);
        assertComplex(small, big, name, operation, 1.5707963267948966, -353.50506208557209);
        assertComplex(small, medium, name, operation, 1.5707963267948966, -5.2983423656105888);
        assertComplex(small, small, name, operation, 1.5707963267948966, -5.9666725849601654e-154);
        // Additional cases to achieve full coverage
        // xm1 = 0
        assertComplex(1, small, name, operation, 2.4426773395109241e-77, -2.4426773395109241e-77);
        // https://svn.boost.org/trac10/ticket/7290
        assertComplex(1.00000002785941, 5.72464869028403e-200, name, operation, 2.4252018043912224e-196, -0.00023604834149293664);
    }

    // acosh is defined by acos so is not tested

    @Test
    void testAsin() {
        // asin(z) = -i (ln(iz + sqrt(1 - z^2)))
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        // This method is essentially the same as acos and the edge cases are the same.
        // The results have been generated using g++ -std=c++11 asin.
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        assertComplex(huge, big, name, operation, 1.5083775167989393, 356.27960012801969);
        assertComplex(huge, medium, name, operation, 1.5707963267948966, 356.27765080781188);
        assertComplex(huge, small, name, operation, 1.5707963267948966, 356.27765080781188);
        assertComplex(big, big, name, operation, 0.78539816339744828, 353.85163567585209);
        assertComplex(big, medium, name, operation, 1.5707963267948966, 353.50506208557209);
        assertComplex(big, small, name, operation, 1.5707963267948966, 353.50506208557209);
        assertComplex(medium, big, name, operation, 5.9666725849601662e-152, 353.50506208557209);
        assertComplex(medium, medium, name, operation, 0.78538566339745486, 5.6448909570623842);
        assertComplex(medium, small, name, operation, 1.5707963267948966, 5.298292365610485);
        assertComplex(small, big, name, operation, 3.560118173611523e-307, 353.50506208557209);
        assertComplex(small, medium, name, operation, 5.9663742737040751e-156, 5.2983423656105888);
        assertComplex(small, small, name, operation, 5.9666725849601654e-154, 5.9666725849601654e-154);
        // Additional cases to achieve full coverage
        // xm1 = 0
        assertComplex(1, small, name, operation, 1.5707963267948966, 2.4426773395109241e-77);
        // https://svn.boost.org/trac10/ticket/7290
        assertComplex(1.00000002785941, 5.72464869028403e-200, name, operation, 1.5707963267948966, 0.00023604834149293664);
    }

    // asinh is defined by asin so is not tested

    @Test
    void testAtanh() {
        // atanh(z) = (1/2) ln((1 + z) / (1 - z))
        // Odd function: negative real cases defined by positive real cases
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // It hits an edge case when x is big and y > 1.
        // The results have been generated using g++ -std=c++11 atanh.
        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
        assertComplex(big, big, name, operation, 7.4583407312002067e-155, 1.5707963267948966);
        assertComplex(big, medium, name, operation, 1.4916681462400417e-154, 1.5707963267948966);
        assertComplex(big, small, name, operation, 1.4916681462400417e-154, 1.5707963267948966);
        assertComplex(medium, big, name, operation, 2.225073858507202e-306, 1.5707963267948966);
        assertComplex(medium, medium, name, operation, 0.0049999166641667555, 1.5657962434640633);
        assertComplex(medium, small, name, operation, 0.010000333353334761, 1.5707963267948966);
        assertComplex(small, big, name, operation, 0, 1.5707963267948966);
        assertComplex(small, medium, name, operation, 2.9830379886812147e-158, 1.5607966601082315);
        assertComplex(small, small, name, operation, 2.9833362924800827e-154, 2.9833362924800827e-154);
        // Additional cases to achieve full coverage
        assertComplex(inf, big, name, operation, 0, 1.5707963267948966);
        assertComplex(big, inf, name, operation, 0, 1.5707963267948966);
    }

    @Test
    void testCosh() {
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        assertComplex(big, big, name, operation, -inf, inf);
        assertComplex(big, medium, name, operation, -inf, inf);
        assertComplex(big, small, name, operation, inf, inf);
        assertComplex(medium, big, name, operation, -3.7621493762972804, 0.017996317370418576);
        assertComplex(medium, medium, name, operation, -1.5656258353157435, 3.297894836311237);
        assertComplex(medium, small, name, operation, 3.7621956910836314, 8.0700322819551687e-308);
        assertComplex(small, big, name, operation, -0.99998768942655991, 1.1040715888508271e-310);
        assertComplex(small, medium, name, operation, -0.41614683654714241, 2.0232539340376892e-308);
        assertComplex(small, small, name, operation, 1, 0);

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        Assertions.assertEquals(inf, Math.exp(x));
        // As computed by GNU g++
        assertComplex(x, 0, name, operation, 8.9910466927705402e+307, 0.0);
        assertComplex(-x, 0, name, operation, 8.9910466927705402e+307, -0.0);
        // sub-normal number x:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        assertComplex(x, small, name, operation, 8.9910466927705402e+307, 2.0005742956701358);
        assertComplex(-x, small, name, operation, 8.9910466927705402e+307, -2.0005742956701358);
        assertComplex(x, tiny, name, operation, 8.9910466927705402e+307, 4.4421672910524807e-16);
        assertComplex(-x, tiny, name, operation, 8.9910466927705402e+307, -4.4421672910524807e-16);
        // Should not overflow imaginary.
        assertComplex(2 * x, tiny, name, operation, inf, 7.9879467061901743e+292);
        assertComplex(-2 * x, tiny, name, operation, inf, -7.9879467061901743e+292);
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        assertComplex(3 * x, tiny, name, operation, inf, inf);
        assertComplex(-3 * x, tiny, name, operation, inf, -inf);
        // pi / 2 x:
        // cos(x) = ~0 => real = x * (e^|x| / 2)
        // sin(x) = ~1 => imaginary = (e^|x| / 2)
        final double pi2 = Math.PI / 2;
        assertComplex(x, pi2, name, operation, 5.5054282766429199e+291, 8.9910466927705402e+307);
        assertComplex(-x, pi2, name, operation, 5.5054282766429199e+291, -8.9910466927705402e+307);
        assertComplex(2 * x, pi2, name, operation, inf, inf);
        assertComplex(-2 * x, pi2, name, operation, inf, -inf);
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        assertComplex(3 * x, pi2, name, operation, inf, inf);
        assertComplex(-3 * x, pi2, name, operation, inf, -inf);
    }

    @Test
    void testSinh() {
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        assertComplex(big, big, name, operation, -inf, inf);
        assertComplex(big, medium, name, operation, -inf, inf);
        assertComplex(big, small, name, operation, inf, inf);
        assertComplex(medium, big, name, operation, -3.6268157591156114, 0.018667844927220067);
        assertComplex(medium, medium, name, operation, -1.5093064853236158, 3.4209548611170133);
        assertComplex(medium, small, name, operation, 3.626860407847019, 8.3711632828186228e-308);
        assertComplex(small, big, name, operation, -2.2250464665720564e-308, 0.004961954789184062);
        assertComplex(small, medium, name, operation, -9.2595744730151568e-309, 0.90929742682568171);
        assertComplex(small, small, name, operation, 2.2250738585072014e-308, 2.2250738585072014e-308);

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        Assertions.assertEquals(inf, Math.exp(x));
        // As computed by GNU g++
        assertComplex(x, 0, name, operation, 8.9910466927705402e+307, 0.0);
        assertComplex(-x, 0, name, operation, -8.9910466927705402e+307, 0.0);
        // sub-normal number:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        assertComplex(x, small, name, operation, 8.9910466927705402e+307, 2.0005742956701358);
        assertComplex(-x, small, name, operation, -8.9910466927705402e+307, 2.0005742956701358);
        assertComplex(x, tiny, name, operation, 8.9910466927705402e+307, 4.4421672910524807e-16);
        assertComplex(-x, tiny, name, operation, -8.9910466927705402e+307, 4.4421672910524807e-16);
        // Should not overflow imaginary.
        assertComplex(2 * x, tiny, name, operation, inf, 7.9879467061901743e+292);
        assertComplex(-2 * x, tiny, name, operation, -inf, 7.9879467061901743e+292);
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        assertComplex(3 * x, tiny, name, operation, inf, inf);
        assertComplex(-3 * x, tiny, name, operation, -inf, inf);
        // pi / 2 x:
        // cos(x) = ~0 => real = x * (e^|x| / 2)
        // sin(x) = ~1 => imaginary = (e^|x| / 2)
        final double pi2 = Math.PI / 2;
        assertComplex(x, pi2, name, operation, 5.5054282766429199e+291, 8.9910466927705402e+307);
        assertComplex(-x, pi2, name, operation, -5.5054282766429199e+291, 8.9910466927705402e+307);
        assertComplex(2 * x, pi2, name, operation, inf, inf);
        assertComplex(-2 * x, pi2, name, operation, -inf, inf);
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        assertComplex(3 * x, pi2, name, operation, inf, inf);
        assertComplex(-3 * x, pi2, name, operation, -inf, inf);
    }

    @Test
    void testTanh() {
        // tan(a + b i) = sinh(2a)/(cosh(2a)+cos(2b)) + i [sin(2b)/(cosh(2a)+cos(2b))]
        // Odd function: negative real cases defined by positive real cases
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;

        // Overflow on 2b:
        // cos(2b) = cos(inf) = NaN
        // sin(2b) = sin(inf) = NaN
        assertComplex(1, Double.MAX_VALUE, name, operation, 0.76160203106265523, -0.0020838895895863505);

        // Underflow on 2b:
        // cos(2b) -> 1
        // sin(2b) -> 0
        assertComplex(1, Double.MIN_NORMAL, name, operation, 0.76159415595576485, 9.344739287691424e-309);
        assertComplex(1, Double.MIN_VALUE, name, operation, 0.76159415595576485, 0);

        // Overflow on 2a:
        // sinh(2a) = sinh(inf) = inf
        // cosh(2a) = cosh(inf) = inf
        // Test all sign variants as this execution path to treat real as infinite
        // is not tested else where.
        assertComplex(Double.MAX_VALUE, 1, name, operation, 1, 0.0);
        assertComplex(Double.MAX_VALUE, -1, name, operation, 1, -0.0);
        assertComplex(-Double.MAX_VALUE, 1, name, operation, -1, 0.0);
        assertComplex(-Double.MAX_VALUE, -1, name, operation, -1, -0.0);

        // Underflow on 2a:
        // sinh(2a) -> 0
        // cosh(2a) -> 0
        assertComplex(Double.MIN_NORMAL, 1, name, operation, 7.6220323800193346e-308, 1.5574077246549021);
        assertComplex(Double.MIN_VALUE, 1, name, operation, 1.4821969375237396e-323, 1.5574077246549021);

        // Underflow test.
        // sinh(x) can be approximated by exp(x) but must be overflow safe.
        // im = 2 sin(2y) / e^2|x|
        // This can be computed when e^2|x| only just overflows.
        // Set a case where e^2|x| overflows but the imaginary can be computed
        double x = 709.783 / 2;
        double y = Math.PI / 4;
        Assertions.assertEquals(1.0, Math.sin(2 * y), 1e-16);
        Assertions.assertEquals(Double.POSITIVE_INFINITY, Math.exp(2 * x));
        // As computed by GNU g++
        assertComplex(x, y, name, operation, 1, 1.1122175583895849e-308);
    }

    @Test
    void testExp() {
        final String name = "exp";
        final UnaryOperator<Complex> operation = Complex::exp;

        // exp(a + b i) = exp(a) (cos(b) + i sin(b))

        // Overflow if exp(a) == inf
        assertComplex(1000, 0, name, operation, inf, 0.0);
        assertComplex(1000, 1, name, operation, inf, inf);
        assertComplex(1000, 2, name, operation, -inf, inf);
        assertComplex(1000, 3, name, operation, -inf, inf);
        assertComplex(1000, 4, name, operation, -inf, -inf);

        // Underflow if exp(a) == 0
        assertComplex(-1000, 0, name, operation, 0.0, 0.0);
        assertComplex(-1000, 1, name, operation, 0.0, 0.0);
        assertComplex(-1000, 2, name, operation, -0.0, 0.0);
        assertComplex(-1000, 3, name, operation, -0.0, 0.0);
        assertComplex(-1000, 4, name, operation, -0.0, -0.0);
    }

    @Test
    void testLog() {
        final String name = "log";
        final UnaryOperator<Complex> operation = Complex::log;

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        assertComplex(-Double.MAX_VALUE, Double.MAX_VALUE, name, operation, 7.101292864836639e2, Math.PI * 3 / 4);
        assertComplex(Double.MAX_VALUE, Double.MAX_VALUE, name, operation, 7.101292864836639e2, Math.PI / 4);
        assertComplex(-Double.MAX_VALUE, Double.MAX_VALUE / 4, name, operation, 7.098130252042921e2, 2.896613990462929);
        assertComplex(Double.MAX_VALUE, Double.MAX_VALUE / 4, name, operation, 7.098130252042921e2, 2.449786631268641e-1, 2);

        // Underflow if sqrt(a^2 + b^2) -> 0
        assertComplex(-Double.MIN_NORMAL, Double.MIN_NORMAL, name, operation, -708.04984494198413, 2.3561944901923448);
        assertComplex(Double.MIN_NORMAL, Double.MIN_NORMAL, name, operation, -708.04984494198413, 0.78539816339744828);
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        assertComplex(-Double.MIN_VALUE, Double.MIN_VALUE, name, operation, expected, Math.atan2(1, -1));
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        assertComplex(-Double.MIN_VALUE, 2 * Double.MIN_VALUE, name, operation, expected, Math.atan2(2, -1));

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        assertLog(1.0001, Math.sqrt(1.2 - 1.0001 * 1.0001), 1);
        assertLog(1.0001, Math.sqrt(1.1 - 1.0001 * 1.0001), 1);
        assertLog(1.0001, Math.sqrt(1.02 - 1.0001 * 1.0001), 0);
        assertLog(1.0001, Math.sqrt(1.01 - 1.0001 * 1.0001), 0);

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        assertLog(0.99, 0.00001, 0);
        assertLog(0.95, 0.00001, 0);
        assertLog(0.9, 0.00001, 0);
        assertLog(0.85, 0.00001, 0);
        assertLog(0.8, 0.00001, 0);
        assertLog(0.75, 0.00001, 0);
        // At this point the log function does not use high precision computation
        assertLog(0.7, 0.00001, 2);

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        assertLog(0.97, Math.sqrt(0.99 - 0.97 * 0.97), 0);
        // Radius 1.01
        assertLog(0.97, Math.sqrt(1.01 - 0.97 * 0.97), 0);

        // Massive relative error
        // Radius 0.9999
        assertLog(0.97, Math.sqrt(0.9999 - 0.97 * 0.97), 0);

        // polar numbers on a 1/8 circle with a magnitude close to 1.
        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                assertLog(magnitude[j] * Math.sin(theta), magnitude[j] * Math.cos(theta), ulps[j]);
            }
        }

        // cis numbers using an increasingly smaller angle
        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
            assertLog(Math.sin(theta), Math.cos(theta), 0);
        }

        // Extreme cases.
        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
        assertLog(down1, Double.MIN_NORMAL, 0);
        assertLog(down1, Double.MIN_VALUE, 0);
        // No use of high-precision computation
        assertLog(up1, Double.MIN_NORMAL, 2);
        assertLog(up1, Double.MIN_VALUE, 2);

        // Add some cases known to fail without very high precision computation.
        // These have been found using randomly generated cis numbers and the
        // previous Dekker split-summation algorithm:
        // theta = rng.nextDouble()
        // x = Math.sin(theta)
        // y = Math.cos(theta)
        // Easy: <16 ulps with the Dekker summation
        assertLog(0.007640392270319105, 0.9999708117770016, 0);
        assertLog(0.40158433204881533, 0.9158220483548684, 0);
        assertLog(0.13258789214774552, 0.9911712520325727, 0);
        assertLog(0.2552206803398717, 0.9668828286441191, 0);
        // Hard: >1024 ulps with the Dekker summation
        assertLog(0.4650816500945186, 0.8852677892848919, 0);
        assertLog(0.06548693057069123, 0.9978534270745526, 0);
        assertLog(0.08223027214657339, 0.9966133564942327, 0);
        assertLog(0.06548693057069123, 0.9978534270745526, 0);
        assertLog(0.04590800199633988, 0.9989456718724518, 0);
        assertLog(0.3019636508581243, 0.9533194394118022, 0);
    }

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

    @Test
    void testSqrt() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;

        // Test real/imaginary only numbers satisfy the definition using polar coordinates:
        // real = sqrt(abs()) * Math.cos(arg() / 2)
        // imag = sqrt(abs()) * Math.sin(arg() / 2)
        // However direct use of sin/cos will result in incorrect results due floating-point error.
        // This test asserts the on the closest result to the exact answer which is possible
        // if not using a simple polar computation.
        // Note: If this test fails in the set-up assertions it is due to a change in the
        // precision of java.util.Math.

        // For positive real-only the argument is +/-0.
        // For negative real-only the argument is +/-pi.
        Assertions.assertEquals(0, Math.atan2(0, 1));
        Assertions.assertEquals(Math.PI, Math.atan2(0, -1));
        // In both cases the trigonomic functions should be exact but
        // cos(pi/2) cannot be as pi/2 is not exact.
        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        Assertions.assertNotEquals(0.0, Math.cos(Math.PI / 2), "Expected cos(pi/2) to be non-zero");
        Assertions.assertEquals(0.0, Math.cos(Math.PI / 2), 6.123233995736766e-17);
        // For imaginary-only the argument is Math.atan2(y, 0) = +/- pi/2.
        Assertions.assertEquals(Math.PI / 2, Math.atan2(1, 0));
        Assertions.assertEquals(-Math.PI / 2, Math.atan2(-1, 0));
        // There is 1 ULP difference in the result of cos/sin of pi/4.
        // It should be sqrt(2) / 2 for both.
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        Assertions.assertNotEquals(cosArgIm, sinArgIm, "Expected cos(pi/4) to not exactly equal sin(pi/4)");
        Assertions.assertEquals(root2over2, cosArgIm, 0, "Expected cos(pi/4) to be sqrt(2) / 2");
        Assertions.assertEquals(root2over2, sinArgIm, ulp, "Expected sin(pi/4) to be 1 ulp from sqrt(2) / 2");
        for (final double a : new double[] {0.5, 1.0, 1.2322, 345345.234523}) {
            final double rootA = Math.sqrt(a);
            assertComplex(a, 0, name, operation, rootA * cosArgRe, rootA * sinArgRe, 0);
            // This should be exact. It will fail if using the polar computation
            // real = sqrt(abs()) * Math.cos(arg() / 2) as cos(pi/2) is not 0.0 but 6.123233995736766e-17
            assertComplex(-a, 0, name, operation, rootA * sinArgRe, rootA * cosArgRe, 0);
            // This should be exact. It won't be if Complex is using polar computation
            // with sin/cos which does not output the same result for angle pi/4.
            assertComplex(0, a, name, operation, rootA * root2over2, rootA * root2over2, 0);
            assertComplex(0, -a, name, operation, rootA * root2over2, -rootA * root2over2, 0);
        }

        // Check overflow safe.
        double a = Double.MAX_VALUE;
        final double b = a / 4;
        Assertions.assertEquals(inf, Complex.ofCartesian(a, b).abs(), "Expected overflow");
        // The expected absolute value has been computed using BigDecimal on Java 9
        //final double newAbs = new BigDecimal(a).multiply(new BigDecimal(a)).add(
        //                      new BigDecimal(b).multiply(new BigDecimal(b)))
        //                     .sqrt(MathContext.DECIMAL128).sqrt(MathContext.DECIMAL128).doubleValue()
        final double newAbs = 1.3612566508088272E154;
        assertComplex(a, b, name, operation, newAbs * Math.cos(0.5 * Math.atan2(b, a)),
                                             newAbs * Math.sin(0.5 * Math.atan2(b, a)), 3);
        assertComplex(b, a, name, operation, newAbs * Math.cos(0.5 * Math.atan2(a, b)),
                                             newAbs * Math.sin(0.5 * Math.atan2(a, b)), 2);

        // Note that the computation is possible in polar coords if abs() does not overflow.
        a = Double.MAX_VALUE / 2;
        assertComplex(-a, a, name, operation, 4.3145940638864765e+153, 1.0416351505169177e+154, 2);
        assertComplex(a, a, name, operation, 1.0416351505169177e+154, 4.3145940638864758e+153);
        assertComplex(-a, -a, name, operation, 4.3145940638864765e+153,  -1.0416351505169177e+154, 2);
        assertComplex(a, -a, name, operation, 1.0416351505169177e+154, -4.3145940638864758e+153);

        // Check minimum normal value conditions
        // Computing in polar coords produces a very different result with
        // MIN_VALUE so use MIN_NORMAL
        a = Double.MIN_NORMAL;
        assertComplex(-a, a, name, operation, 6.7884304867749663e-155, 1.6388720948399111e-154);
        assertComplex(a, a, name, operation, 1.6388720948399111e-154, 6.7884304867749655e-155);
        assertComplex(-a, -a, name, operation, 6.7884304867749663e-155, -1.6388720948399111e-154);
        assertComplex(a, -a, name, operation, 1.6388720948399111e-154, -6.7884304867749655e-155);
    }

    // Note: inf/nan edge cases for
    // multiply/divide are tested in CStandardTest

    @Test
    void testDivide() {
        final String name = "divide";
        final BiFunction<Complex, Complex, Complex> operation = Complex::divide;

        // Should be able to divide by a complex whose absolute (c*c+d*d)
        // overflows or underflows including all sub-normal numbers.

        // Worst case is using Double.MIN_VALUE
        // Should normalise c and d to range [1, 2) resulting in:
        // c = d = 1
        // c * c + d * d = 2
        // scaled x = (a * c + b * d) / denom = Double.MIN_VALUE
        // scaled y = (b * c - a * d) / denom = 0
        // The values are rescaled by 1023 + 51 (shift the last bit of the 52 bit mantissa)
        double x = Math.scalb(Double.MIN_VALUE, 1023 + 51);
        Assertions.assertEquals(1.0, x);
        // In other words the result is (x+iy) / (x+iy) = (1+i0)
        // The result is the same if imaginary is zero (i.e. a real only divide)

        assertComplex(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, name, operation, 1.0, 0.0);
        assertComplex(Double.MAX_VALUE, 0.0, Double.MAX_VALUE, 0.0, name, operation, 1.0, 0.0);

        assertComplex(1.0, 1.0, 1.0, 1.0, name, operation, 1.0, 0.0);
        assertComplex(1.0, 0.0, 1.0, 0.0, name, operation, 1.0, 0.0);
        // Should work for all small values
        x = Double.MIN_NORMAL;
        while (x != 0) {
            assertComplex(x, x, x, x, name, operation, 1.0, 0.0);
            assertComplex(x, 0, x, 0, name, operation, 1.0, 0.0);
            x /= 2;
        }

        // Some cases of not self-divide
        assertComplex(1, 1, Double.MIN_VALUE, Double.MIN_VALUE, name, operation, inf, 0);
        // As computed by GNU g++
        assertComplex(Double.MIN_NORMAL, Double.MIN_NORMAL, Double.MIN_VALUE, Double.MIN_VALUE, name, operation, 4503599627370496L, 0);
        assertComplex(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_NORMAL, Double.MIN_NORMAL, name, operation, 2.2204460492503131e-16, 0);
    }

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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        Assertions.assertEquals(inf, Math.exp(x));
    }

    @Test
    void testSinh_10_oe() {
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        Assertions.assertEquals(inf, Math.exp(x));
    }

    @Test
    void testTanh_10_oe() {
        // tan(a + b i) = sinh(2a)/(cosh(2a)+cos(2b)) + i [sin(2b)/(cosh(2a)+cos(2b))]
        // Odd function: negative real cases defined by positive real cases
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;

        // Overflow on 2b:
        // cos(2b) = cos(inf) = NaN
        // sin(2b) = sin(inf) = NaN
        // removed other assertion

        // Underflow on 2b:
        // cos(2b) -> 1
        // sin(2b) -> 0
        // removed other assertion
        // removed other assertion

        // Overflow on 2a:
        // sinh(2a) = sinh(inf) = inf
        // cosh(2a) = cosh(inf) = inf
        // Test all sign variants as this execution path to treat real as infinite
        // is not tested else where.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow on 2a:
        // sinh(2a) -> 0
        // cosh(2a) -> 0
        // removed other assertion
        // removed other assertion

        // Underflow test.
        // sinh(x) can be approximated by exp(x) but must be overflow safe.
        // im = 2 sin(2y) / e^2|x|
        // This can be computed when e^2|x| only just overflows.
        // Set a case where e^2|x| overflows but the imaginary can be computed
        double x = 709.783 / 2;
        double y = Math.PI / 4;
        Assertions.assertEquals(1.0, Math.sin(2 * y), 1e-16);
    }

    @Test
    void testTanh_11_oe() {
        // tan(a + b i) = sinh(2a)/(cosh(2a)+cos(2b)) + i [sin(2b)/(cosh(2a)+cos(2b))]
        // Odd function: negative real cases defined by positive real cases
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;

        // Overflow on 2b:
        // cos(2b) = cos(inf) = NaN
        // sin(2b) = sin(inf) = NaN
        // removed other assertion

        // Underflow on 2b:
        // cos(2b) -> 1
        // sin(2b) -> 0
        // removed other assertion
        // removed other assertion

        // Overflow on 2a:
        // sinh(2a) = sinh(inf) = inf
        // cosh(2a) = cosh(inf) = inf
        // Test all sign variants as this execution path to treat real as infinite
        // is not tested else where.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow on 2a:
        // sinh(2a) -> 0
        // cosh(2a) -> 0
        // removed other assertion
        // removed other assertion

        // Underflow test.
        // sinh(x) can be approximated by exp(x) but must be overflow safe.
        // im = 2 sin(2y) / e^2|x|
        // This can be computed when e^2|x| only just overflows.
        // Set a case where e^2|x| overflows but the imaginary can be computed
        double x = 709.783 / 2;
        double y = Math.PI / 4;
        // removed other assertion
        Assertions.assertEquals(Double.POSITIVE_INFINITY, Math.exp(2 * x));
    }

    @Test
    void testSqrt_1_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;

        // Test real/imaginary only numbers satisfy the definition using polar coordinates:
        // real = sqrt(abs()) * Math.cos(arg() / 2)
        // imag = sqrt(abs()) * Math.sin(arg() / 2)
        // However direct use of sin/cos will result in incorrect results due floating-point error.
        // This test asserts the on the closest result to the exact answer which is possible
        // if not using a simple polar computation.
        // Note: If this test fails in the set-up assertions it is due to a change in the
        // precision of java.util.Math.

        // For positive real-only the argument is +/-0.
        // For negative real-only the argument is +/-pi.
        Assertions.assertEquals(0, Math.atan2(0, 1));
    }

    @Test
    void testSqrt_2_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;

        // Test real/imaginary only numbers satisfy the definition using polar coordinates:
        // real = sqrt(abs()) * Math.cos(arg() / 2)
        // imag = sqrt(abs()) * Math.sin(arg() / 2)
        // However direct use of sin/cos will result in incorrect results due floating-point error.
        // This test asserts the on the closest result to the exact answer which is possible
        // if not using a simple polar computation.
        // Note: If this test fails in the set-up assertions it is due to a change in the
        // precision of java.util.Math.

        // For positive real-only the argument is +/-0.
        // For negative real-only the argument is +/-pi.
        // removed other assertion
        Assertions.assertEquals(Math.PI, Math.atan2(0, -1));
    }

    @Test
    void testSqrt_3_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;

        // Test real/imaginary only numbers satisfy the definition using polar coordinates:
        // real = sqrt(abs()) * Math.cos(arg() / 2)
        // imag = sqrt(abs()) * Math.sin(arg() / 2)
        // However direct use of sin/cos will result in incorrect results due floating-point error.
        // This test asserts the on the closest result to the exact answer which is possible
        // if not using a simple polar computation.
        // Note: If this test fails in the set-up assertions it is due to a change in the
        // precision of java.util.Math.

        // For positive real-only the argument is +/-0.
        // For negative real-only the argument is +/-pi.
        // removed other assertion
        // removed other assertion
        // In both cases the trigonomic functions should be exact but
        // cos(pi/2) cannot be as pi/2 is not exact.
        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        Assertions.assertNotEquals(0.0, Math.cos(Math.PI / 2), "Expected cos(pi/2) to be non-zero");
    }

    @Test
    void testSqrt_4_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;

        // Test real/imaginary only numbers satisfy the definition using polar coordinates:
        // real = sqrt(abs()) * Math.cos(arg() / 2)
        // imag = sqrt(abs()) * Math.sin(arg() / 2)
        // However direct use of sin/cos will result in incorrect results due floating-point error.
        // This test asserts the on the closest result to the exact answer which is possible
        // if not using a simple polar computation.
        // Note: If this test fails in the set-up assertions it is due to a change in the
        // precision of java.util.Math.

        // For positive real-only the argument is +/-0.
        // For negative real-only the argument is +/-pi.
        // removed other assertion
        // removed other assertion
        // In both cases the trigonomic functions should be exact but
        // cos(pi/2) cannot be as pi/2 is not exact.
        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        // removed other assertion
        Assertions.assertEquals(0.0, Math.cos(Math.PI / 2), 6.123233995736766e-17);
    }

    @Test
    void testSqrt_5_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;

        // Test real/imaginary only numbers satisfy the definition using polar coordinates:
        // real = sqrt(abs()) * Math.cos(arg() / 2)
        // imag = sqrt(abs()) * Math.sin(arg() / 2)
        // However direct use of sin/cos will result in incorrect results due floating-point error.
        // This test asserts the on the closest result to the exact answer which is possible
        // if not using a simple polar computation.
        // Note: If this test fails in the set-up assertions it is due to a change in the
        // precision of java.util.Math.

        // For positive real-only the argument is +/-0.
        // For negative real-only the argument is +/-pi.
        // removed other assertion
        // removed other assertion
        // In both cases the trigonomic functions should be exact but
        // cos(pi/2) cannot be as pi/2 is not exact.
        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        // removed other assertion
        // removed other assertion
        // For imaginary-only the argument is Math.atan2(y, 0) = +/- pi/2.
        Assertions.assertEquals(Math.PI / 2, Math.atan2(1, 0));
    }

    @Test
    void testSqrt_6_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;

        // Test real/imaginary only numbers satisfy the definition using polar coordinates:
        // real = sqrt(abs()) * Math.cos(arg() / 2)
        // imag = sqrt(abs()) * Math.sin(arg() / 2)
        // However direct use of sin/cos will result in incorrect results due floating-point error.
        // This test asserts the on the closest result to the exact answer which is possible
        // if not using a simple polar computation.
        // Note: If this test fails in the set-up assertions it is due to a change in the
        // precision of java.util.Math.

        // For positive real-only the argument is +/-0.
        // For negative real-only the argument is +/-pi.
        // removed other assertion
        // removed other assertion
        // In both cases the trigonomic functions should be exact but
        // cos(pi/2) cannot be as pi/2 is not exact.
        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        // removed other assertion
        // removed other assertion
        // For imaginary-only the argument is Math.atan2(y, 0) = +/- pi/2.
        // removed other assertion
        Assertions.assertEquals(-Math.PI / 2, Math.atan2(-1, 0));
    }

    @Test
    void testSqrt_7_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;

        // Test real/imaginary only numbers satisfy the definition using polar coordinates:
        // real = sqrt(abs()) * Math.cos(arg() / 2)
        // imag = sqrt(abs()) * Math.sin(arg() / 2)
        // However direct use of sin/cos will result in incorrect results due floating-point error.
        // This test asserts the on the closest result to the exact answer which is possible
        // if not using a simple polar computation.
        // Note: If this test fails in the set-up assertions it is due to a change in the
        // precision of java.util.Math.

        // For positive real-only the argument is +/-0.
        // For negative real-only the argument is +/-pi.
        // removed other assertion
        // removed other assertion
        // In both cases the trigonomic functions should be exact but
        // cos(pi/2) cannot be as pi/2 is not exact.
        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        // removed other assertion
        // removed other assertion
        // For imaginary-only the argument is Math.atan2(y, 0) = +/- pi/2.
        // removed other assertion
        // removed other assertion
        // There is 1 ULP difference in the result of cos/sin of pi/4.
        // It should be sqrt(2) / 2 for both.
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

        // Test real/imaginary only numbers satisfy the definition using polar coordinates:
        // real = sqrt(abs()) * Math.cos(arg() / 2)
        // imag = sqrt(abs()) * Math.sin(arg() / 2)
        // However direct use of sin/cos will result in incorrect results due floating-point error.
        // This test asserts the on the closest result to the exact answer which is possible
        // if not using a simple polar computation.
        // Note: If this test fails in the set-up assertions it is due to a change in the
        // precision of java.util.Math.

        // For positive real-only the argument is +/-0.
        // For negative real-only the argument is +/-pi.
        // removed other assertion
        // removed other assertion
        // In both cases the trigonomic functions should be exact but
        // cos(pi/2) cannot be as pi/2 is not exact.
        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        // removed other assertion
        // removed other assertion
        // For imaginary-only the argument is Math.atan2(y, 0) = +/- pi/2.
        // removed other assertion
        // removed other assertion
        // There is 1 ULP difference in the result of cos/sin of pi/4.
        // It should be sqrt(2) / 2 for both.
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        // removed other assertion
        Assertions.assertEquals(root2over2, cosArgIm, 0, "Expected cos(pi/4) to be sqrt(2) / 2");
    }

    @Test
    void testSqrt_9_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;

        // Test real/imaginary only numbers satisfy the definition using polar coordinates:
        // real = sqrt(abs()) * Math.cos(arg() / 2)
        // imag = sqrt(abs()) * Math.sin(arg() / 2)
        // However direct use of sin/cos will result in incorrect results due floating-point error.
        // This test asserts the on the closest result to the exact answer which is possible
        // if not using a simple polar computation.
        // Note: If this test fails in the set-up assertions it is due to a change in the
        // precision of java.util.Math.

        // For positive real-only the argument is +/-0.
        // For negative real-only the argument is +/-pi.
        // removed other assertion
        // removed other assertion
        // In both cases the trigonomic functions should be exact but
        // cos(pi/2) cannot be as pi/2 is not exact.
        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        // removed other assertion
        // removed other assertion
        // For imaginary-only the argument is Math.atan2(y, 0) = +/- pi/2.
        // removed other assertion
        // removed other assertion
        // There is 1 ULP difference in the result of cos/sin of pi/4.
        // It should be sqrt(2) / 2 for both.
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(root2over2, sinArgIm, ulp, "Expected sin(pi/4) to be 1 ulp from sqrt(2) / 2");
    }

    @Test
    void testSqrt_14_oe() {
        final String name = "sqrt";
        final UnaryOperator<Complex> operation = Complex::sqrt;

        // Test real/imaginary only numbers satisfy the definition using polar coordinates:
        // real = sqrt(abs()) * Math.cos(arg() / 2)
        // imag = sqrt(abs()) * Math.sin(arg() / 2)
        // However direct use of sin/cos will result in incorrect results due floating-point error.
        // This test asserts the on the closest result to the exact answer which is possible
        // if not using a simple polar computation.
        // Note: If this test fails in the set-up assertions it is due to a change in the
        // precision of java.util.Math.

        // For positive real-only the argument is +/-0.
        // For negative real-only the argument is +/-pi.
        // removed other assertion
        // removed other assertion
        // In both cases the trigonomic functions should be exact but
        // cos(pi/2) cannot be as pi/2 is not exact.
        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        // removed other assertion
        // removed other assertion
        // For imaginary-only the argument is Math.atan2(y, 0) = +/- pi/2.
        // removed other assertion
        // removed other assertion
        // There is 1 ULP difference in the result of cos/sin of pi/4.
        // It should be sqrt(2) / 2 for both.
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for (final double a : new double[] {0.5, 1.0, 1.2322, 345345.234523}) {
            final double rootA = Math.sqrt(a);
            // removed other assertion
            // This should be exact. It will fail if using the polar computation
            // real = sqrt(abs()) * Math.cos(arg() / 2) as cos(pi/2) is not 0.0 but 6.123233995736766e-17
            // removed other assertion
            // This should be exact. It won't be if Complex is using polar computation
            // with sin/cos which does not output the same result for angle pi/4.
            // removed other assertion
            // removed other assertion
        }

        // Check overflow safe.
        double a = Double.MAX_VALUE;
        final double b = a / 4;
        Assertions.assertEquals(inf, Complex.ofCartesian(a, b).abs(), "Expected overflow");
    }

    @Test
    void testDivide_1_oe() {
        final String name = "divide";
        final BiFunction<Complex, Complex, Complex> operation = Complex::divide;

        // Should be able to divide by a complex whose absolute (c*c+d*d)
        // overflows or underflows including all sub-normal numbers.

        // Worst case is using Double.MIN_VALUE
        // Should normalise c and d to range [1, 2) resulting in:
        // c = d = 1
        // c * c + d * d = 2
        // scaled x = (a * c + b * d) / denom = Double.MIN_VALUE
        // scaled y = (b * c - a * d) / denom = 0
        // The values are rescaled by 1023 + 51 (shift the last bit of the 52 bit mantissa)
        double x = Math.scalb(Double.MIN_VALUE, 1023 + 51);
        Assertions.assertEquals(1.0, x);
    }

    @Test
    void testAcos_1_oe_1_oe() {
        // acos(z) = (pi / 2) + i ln(iz + sqrt(1 - z^2))
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // The results have been generated using g++ -std=c++11 acos.
        // xp1 * xm1 will overflow:
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
        // acos(z) = (pi / 2) + i ln(iz + sqrt(1 - z^2))
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // The results have been generated using g++ -std=c++11 acos.
        // xp1 * xm1 will overflow:
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
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
        // acos(z) = (pi / 2) + i ln(iz + sqrt(1 - z^2))
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // The results have been generated using g++ -std=c++11 acos.
        // xp1 * xm1 will overflow:
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
        // removed other assertion
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
        // acos(z) = (pi / 2) + i ln(iz + sqrt(1 - z^2))
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // The results have been generated using g++ -std=c++11 acos.
        // xp1 * xm1 will overflow:
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // acos(z) = (pi / 2) + i ln(iz + sqrt(1 - z^2))
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // The results have been generated using g++ -std=c++11 acos.
        // xp1 * xm1 will overflow:
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // acos(z) = (pi / 2) + i ln(iz + sqrt(1 - z^2))
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // The results have been generated using g++ -std=c++11 acos.
        // xp1 * xm1 will overflow:
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // acos(z) = (pi / 2) + i ln(iz + sqrt(1 - z^2))
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // The results have been generated using g++ -std=c++11 acos.
        // xp1 * xm1 will overflow:
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // acos(z) = (pi / 2) + i ln(iz + sqrt(1 - z^2))
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // The results have been generated using g++ -std=c++11 acos.
        // xp1 * xm1 will overflow:
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // acos(z) = (pi / 2) + i ln(iz + sqrt(1 - z^2))
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // The results have been generated using g++ -std=c++11 acos.
        // xp1 * xm1 will overflow:
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // acos(z) = (pi / 2) + i ln(iz + sqrt(1 - z^2))
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // The results have been generated using g++ -std=c++11 acos.
        // xp1 * xm1 will overflow:
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // acos(z) = (pi / 2) + i ln(iz + sqrt(1 - z^2))
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // The results have been generated using g++ -std=c++11 acos.
        // xp1 * xm1 will overflow:
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
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
        // acos(z) = (pi / 2) + i ln(iz + sqrt(1 - z^2))
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // The results have been generated using g++ -std=c++11 acos.
        // xp1 * xm1 will overflow:
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
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
        // acos(z) = (pi / 2) + i ln(iz + sqrt(1 - z^2))
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // The results have been generated using g++ -std=c++11 acos.
        // xp1 * xm1 will overflow:
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
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
        // Additional cases to achieve full coverage
        // xm1 = 0
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
        // acos(z) = (pi / 2) + i ln(iz + sqrt(1 - z^2))
        final String name = "acos";
        final UnaryOperator<Complex> operation = Complex::acos;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // The results have been generated using g++ -std=c++11 acos.
        // xp1 * xm1 will overflow:
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
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
        // Additional cases to achieve full coverage
        // xm1 = 0
        // removed other assertion
        // https://svn.boost.org/trac10/ticket/7290
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
        // asin(z) = -i (ln(iz + sqrt(1 - z^2)))
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        // This method is essentially the same as acos and the edge cases are the same.
        // The results have been generated using g++ -std=c++11 asin.
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
        // asin(z) = -i (ln(iz + sqrt(1 - z^2)))
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        // This method is essentially the same as acos and the edge cases are the same.
        // The results have been generated using g++ -std=c++11 asin.
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
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
        // asin(z) = -i (ln(iz + sqrt(1 - z^2)))
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        // This method is essentially the same as acos and the edge cases are the same.
        // The results have been generated using g++ -std=c++11 asin.
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
        // removed other assertion
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
        // asin(z) = -i (ln(iz + sqrt(1 - z^2)))
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        // This method is essentially the same as acos and the edge cases are the same.
        // The results have been generated using g++ -std=c++11 asin.
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // asin(z) = -i (ln(iz + sqrt(1 - z^2)))
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        // This method is essentially the same as acos and the edge cases are the same.
        // The results have been generated using g++ -std=c++11 asin.
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // asin(z) = -i (ln(iz + sqrt(1 - z^2)))
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        // This method is essentially the same as acos and the edge cases are the same.
        // The results have been generated using g++ -std=c++11 asin.
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // asin(z) = -i (ln(iz + sqrt(1 - z^2)))
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        // This method is essentially the same as acos and the edge cases are the same.
        // The results have been generated using g++ -std=c++11 asin.
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // asin(z) = -i (ln(iz + sqrt(1 - z^2)))
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        // This method is essentially the same as acos and the edge cases are the same.
        // The results have been generated using g++ -std=c++11 asin.
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // asin(z) = -i (ln(iz + sqrt(1 - z^2)))
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        // This method is essentially the same as acos and the edge cases are the same.
        // The results have been generated using g++ -std=c++11 asin.
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // asin(z) = -i (ln(iz + sqrt(1 - z^2)))
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        // This method is essentially the same as acos and the edge cases are the same.
        // The results have been generated using g++ -std=c++11 asin.
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // asin(z) = -i (ln(iz + sqrt(1 - z^2)))
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        // This method is essentially the same as acos and the edge cases are the same.
        // The results have been generated using g++ -std=c++11 asin.
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
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
        // asin(z) = -i (ln(iz + sqrt(1 - z^2)))
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        // This method is essentially the same as acos and the edge cases are the same.
        // The results have been generated using g++ -std=c++11 asin.
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
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
        // asin(z) = -i (ln(iz + sqrt(1 - z^2)))
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        // This method is essentially the same as acos and the edge cases are the same.
        // The results have been generated using g++ -std=c++11 asin.
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
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
        // Additional cases to achieve full coverage
        // xm1 = 0
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
        // asin(z) = -i (ln(iz + sqrt(1 - z^2)))
        final String name = "asin";
        final UnaryOperator<Complex> operation = Complex::asin;

        // This method is essentially the same as acos and the edge cases are the same.
        // The results have been generated using g++ -std=c++11 asin.
        final double huge = Math.sqrt(Double.MAX_VALUE) * 2;
        final double big = Math.sqrt(Double.MAX_VALUE) / 8;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 4;
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
        // Additional cases to achieve full coverage
        // xm1 = 0
        // removed other assertion
        // https://svn.boost.org/trac10/ticket/7290
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
        // atanh(z) = (1/2) ln((1 + z) / (1 - z))
        // Odd function: negative real cases defined by positive real cases
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // It hits an edge case when x is big and y > 1.
        // The results have been generated using g++ -std=c++11 atanh.
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
        // atanh(z) = (1/2) ln((1 + z) / (1 - z))
        // Odd function: negative real cases defined by positive real cases
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // It hits an edge case when x is big and y > 1.
        // The results have been generated using g++ -std=c++11 atanh.
        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
        // removed other assertion
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
        // atanh(z) = (1/2) ln((1 + z) / (1 - z))
        // Odd function: negative real cases defined by positive real cases
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // It hits an edge case when x is big and y > 1.
        // The results have been generated using g++ -std=c++11 atanh.
        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
        // removed other assertion
        // removed other assertion
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
        // atanh(z) = (1/2) ln((1 + z) / (1 - z))
        // Odd function: negative real cases defined by positive real cases
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // It hits an edge case when x is big and y > 1.
        // The results have been generated using g++ -std=c++11 atanh.
        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // atanh(z) = (1/2) ln((1 + z) / (1 - z))
        // Odd function: negative real cases defined by positive real cases
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // It hits an edge case when x is big and y > 1.
        // The results have been generated using g++ -std=c++11 atanh.
        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // atanh(z) = (1/2) ln((1 + z) / (1 - z))
        // Odd function: negative real cases defined by positive real cases
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // It hits an edge case when x is big and y > 1.
        // The results have been generated using g++ -std=c++11 atanh.
        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // atanh(z) = (1/2) ln((1 + z) / (1 - z))
        // Odd function: negative real cases defined by positive real cases
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // It hits an edge case when x is big and y > 1.
        // The results have been generated using g++ -std=c++11 atanh.
        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // atanh(z) = (1/2) ln((1 + z) / (1 - z))
        // Odd function: negative real cases defined by positive real cases
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // It hits an edge case when x is big and y > 1.
        // The results have been generated using g++ -std=c++11 atanh.
        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // atanh(z) = (1/2) ln((1 + z) / (1 - z))
        // Odd function: negative real cases defined by positive real cases
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // It hits an edge case when x is big and y > 1.
        // The results have been generated using g++ -std=c++11 atanh.
        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // atanh(z) = (1/2) ln((1 + z) / (1 - z))
        // Odd function: negative real cases defined by positive real cases
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // It hits an edge case when x is big and y > 1.
        // The results have been generated using g++ -std=c++11 atanh.
        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Additional cases to achieve full coverage
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
        // atanh(z) = (1/2) ln((1 + z) / (1 - z))
        // Odd function: negative real cases defined by positive real cases
        final String name = "atanh";
        final UnaryOperator<Complex> operation = Complex::atanh;

        // Edge cases are when values are big but not infinite and small but not zero.
        // Big and small are set using the limits in atanh.
        // A medium value is used to test outside the range of the CReferenceTest.
        // It hits an edge case when x is big and y > 1.
        // The results have been generated using g++ -std=c++11 atanh.
        final double big = Math.sqrt(Double.MAX_VALUE) / 2;
        final double medium = 100;
        final double small = Math.sqrt(Double.MIN_NORMAL) * 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Additional cases to achieve full coverage
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number x:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number x:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number x:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number x:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number x:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number x:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number x:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number x:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number x:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        // removed other assertion
        // removed other assertion
        // pi / 2 x:
        // cos(x) = ~0 => real = x * (e^|x| / 2)
        // sin(x) = ~1 => imaginary = (e^|x| / 2)
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number x:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        // removed other assertion
        // removed other assertion
        // pi / 2 x:
        // cos(x) = ~0 => real = x * (e^|x| / 2)
        // sin(x) = ~1 => imaginary = (e^|x| / 2)
        final double pi2 = Math.PI / 2;
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number x:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        // removed other assertion
        // removed other assertion
        // pi / 2 x:
        // cos(x) = ~0 => real = x * (e^|x| / 2)
        // sin(x) = ~1 => imaginary = (e^|x| / 2)
        final double pi2 = Math.PI / 2;
        // removed other assertion
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number x:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        // removed other assertion
        // removed other assertion
        // pi / 2 x:
        // cos(x) = ~0 => real = x * (e^|x| / 2)
        // sin(x) = ~1 => imaginary = (e^|x| / 2)
        final double pi2 = Math.PI / 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number x:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        // removed other assertion
        // removed other assertion
        // pi / 2 x:
        // cos(x) = ~0 => real = x * (e^|x| / 2)
        // sin(x) = ~1 => imaginary = (e^|x| / 2)
        final double pi2 = Math.PI / 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
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
        // cosh(a + b i) = cosh(a)cos(b) + i sinh(a)sin(b)
        // Even function: negative real cases defined by positive real cases
        final String name = "cosh";
        final UnaryOperator<Complex> operation = Complex::cosh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // Based on MATH-901 discussion of FastMath functionality.
        // https://issues.apache.org/jira/browse/MATH-901#comment-13500669
        // sinh(x)/cosh(x) can be approximated by exp(x) but must be overflow safe.

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // (e^|x| / 2) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number x:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        // removed other assertion
        // removed other assertion
        // pi / 2 x:
        // cos(x) = ~0 => real = x * (e^|x| / 2)
        // sin(x) = ~1 => imaginary = (e^|x| / 2)
        final double pi2 = Math.PI / 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        // removed other assertion
        // removed other assertion
        // pi / 2 x:
        // cos(x) = ~0 => real = x * (e^|x| / 2)
        // sin(x) = ~1 => imaginary = (e^|x| / 2)
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        // removed other assertion
        // removed other assertion
        // pi / 2 x:
        // cos(x) = ~0 => real = x * (e^|x| / 2)
        // sin(x) = ~1 => imaginary = (e^|x| / 2)
        final double pi2 = Math.PI / 2;
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        // removed other assertion
        // removed other assertion
        // pi / 2 x:
        // cos(x) = ~0 => real = x * (e^|x| / 2)
        // sin(x) = ~1 => imaginary = (e^|x| / 2)
        final double pi2 = Math.PI / 2;
        // removed other assertion
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        // removed other assertion
        // removed other assertion
        // pi / 2 x:
        // cos(x) = ~0 => real = x * (e^|x| / 2)
        // sin(x) = ~1 => imaginary = (e^|x| / 2)
        final double pi2 = Math.PI / 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        // removed other assertion
        // removed other assertion
        // pi / 2 x:
        // cos(x) = ~0 => real = x * (e^|x| / 2)
        // sin(x) = ~1 => imaginary = (e^|x| / 2)
        final double pi2 = Math.PI / 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
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
        // sinh(a + b i) = sinh(a)cos(b) + i cosh(a)sin(b)
        // Odd function: negative real cases defined by positive real cases
        final String name = "sinh";
        final UnaryOperator<Complex> operation = Complex::sinh;

        // Implementation defers to java.util.Math.
        // Hit edge cases for extreme values.
        final double big = Double.MAX_VALUE;
        final double medium = 2;
        final double small = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Overflow test.
        // As per cosh with sign changes to real and imaginary

        // sinh(x) = sign(x) * e^|x| / 2 when x is large.
        // cosh(x) = e^|x| / 2 when x is large.
        // Thus e^|x| can overflow but e^|x| / 2 may not.
        // sinh(x) * sin/cos will always be smaller.
        final double tiny = Double.MIN_VALUE;
        final double x = 709.783;
        // removed other assertion
        // As computed by GNU g++
        // removed other assertion
        // removed other assertion
        // sub-normal number:
        // cos(x) = 1 => real = (e^|x| / 2)
        // sin(x) = x => imaginary = x * (e^|x| / 2)
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Should not overflow imaginary.
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        // removed other assertion
        // removed other assertion
        // pi / 2 x:
        // cos(x) = ~0 => real = x * (e^|x| / 2)
        // sin(x) = ~1 => imaginary = (e^|x| / 2)
        final double pi2 = Math.PI / 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Test when large enough to overflow any non-zero value to infinity. Result should be
        // as if x was infinite and y was finite.
        // removed other assertion
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
        // tan(a + b i) = sinh(2a)/(cosh(2a)+cos(2b)) + i [sin(2b)/(cosh(2a)+cos(2b))]
        // Odd function: negative real cases defined by positive real cases
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;

        // Overflow on 2b:
        // cos(2b) = cos(inf) = NaN
        // sin(2b) = sin(inf) = NaN
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
        // tan(a + b i) = sinh(2a)/(cosh(2a)+cos(2b)) + i [sin(2b)/(cosh(2a)+cos(2b))]
        // Odd function: negative real cases defined by positive real cases
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;

        // Overflow on 2b:
        // cos(2b) = cos(inf) = NaN
        // sin(2b) = sin(inf) = NaN
        // removed other assertion

        // Underflow on 2b:
        // cos(2b) -> 1
        // sin(2b) -> 0
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
        // tan(a + b i) = sinh(2a)/(cosh(2a)+cos(2b)) + i [sin(2b)/(cosh(2a)+cos(2b))]
        // Odd function: negative real cases defined by positive real cases
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;

        // Overflow on 2b:
        // cos(2b) = cos(inf) = NaN
        // sin(2b) = sin(inf) = NaN
        // removed other assertion

        // Underflow on 2b:
        // cos(2b) -> 1
        // sin(2b) -> 0
        // removed other assertion
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
        // tan(a + b i) = sinh(2a)/(cosh(2a)+cos(2b)) + i [sin(2b)/(cosh(2a)+cos(2b))]
        // Odd function: negative real cases defined by positive real cases
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;

        // Overflow on 2b:
        // cos(2b) = cos(inf) = NaN
        // sin(2b) = sin(inf) = NaN
        // removed other assertion

        // Underflow on 2b:
        // cos(2b) -> 1
        // sin(2b) -> 0
        // removed other assertion
        // removed other assertion

        // Overflow on 2a:
        // sinh(2a) = sinh(inf) = inf
        // cosh(2a) = cosh(inf) = inf
        // Test all sign variants as this execution path to treat real as infinite
        // is not tested else where.
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
        // tan(a + b i) = sinh(2a)/(cosh(2a)+cos(2b)) + i [sin(2b)/(cosh(2a)+cos(2b))]
        // Odd function: negative real cases defined by positive real cases
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;

        // Overflow on 2b:
        // cos(2b) = cos(inf) = NaN
        // sin(2b) = sin(inf) = NaN
        // removed other assertion

        // Underflow on 2b:
        // cos(2b) -> 1
        // sin(2b) -> 0
        // removed other assertion
        // removed other assertion

        // Overflow on 2a:
        // sinh(2a) = sinh(inf) = inf
        // cosh(2a) = cosh(inf) = inf
        // Test all sign variants as this execution path to treat real as infinite
        // is not tested else where.
        // removed other assertion
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
        // tan(a + b i) = sinh(2a)/(cosh(2a)+cos(2b)) + i [sin(2b)/(cosh(2a)+cos(2b))]
        // Odd function: negative real cases defined by positive real cases
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;

        // Overflow on 2b:
        // cos(2b) = cos(inf) = NaN
        // sin(2b) = sin(inf) = NaN
        // removed other assertion

        // Underflow on 2b:
        // cos(2b) -> 1
        // sin(2b) -> 0
        // removed other assertion
        // removed other assertion

        // Overflow on 2a:
        // sinh(2a) = sinh(inf) = inf
        // cosh(2a) = cosh(inf) = inf
        // Test all sign variants as this execution path to treat real as infinite
        // is not tested else where.
        // removed other assertion
        // removed other assertion
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
        // tan(a + b i) = sinh(2a)/(cosh(2a)+cos(2b)) + i [sin(2b)/(cosh(2a)+cos(2b))]
        // Odd function: negative real cases defined by positive real cases
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;

        // Overflow on 2b:
        // cos(2b) = cos(inf) = NaN
        // sin(2b) = sin(inf) = NaN
        // removed other assertion

        // Underflow on 2b:
        // cos(2b) -> 1
        // sin(2b) -> 0
        // removed other assertion
        // removed other assertion

        // Overflow on 2a:
        // sinh(2a) = sinh(inf) = inf
        // cosh(2a) = cosh(inf) = inf
        // Test all sign variants as this execution path to treat real as infinite
        // is not tested else where.
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // tan(a + b i) = sinh(2a)/(cosh(2a)+cos(2b)) + i [sin(2b)/(cosh(2a)+cos(2b))]
        // Odd function: negative real cases defined by positive real cases
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;

        // Overflow on 2b:
        // cos(2b) = cos(inf) = NaN
        // sin(2b) = sin(inf) = NaN
        // removed other assertion

        // Underflow on 2b:
        // cos(2b) -> 1
        // sin(2b) -> 0
        // removed other assertion
        // removed other assertion

        // Overflow on 2a:
        // sinh(2a) = sinh(inf) = inf
        // cosh(2a) = cosh(inf) = inf
        // Test all sign variants as this execution path to treat real as infinite
        // is not tested else where.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow on 2a:
        // sinh(2a) -> 0
        // cosh(2a) -> 0
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
        // tan(a + b i) = sinh(2a)/(cosh(2a)+cos(2b)) + i [sin(2b)/(cosh(2a)+cos(2b))]
        // Odd function: negative real cases defined by positive real cases
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;

        // Overflow on 2b:
        // cos(2b) = cos(inf) = NaN
        // sin(2b) = sin(inf) = NaN
        // removed other assertion

        // Underflow on 2b:
        // cos(2b) -> 1
        // sin(2b) -> 0
        // removed other assertion
        // removed other assertion

        // Overflow on 2a:
        // sinh(2a) = sinh(inf) = inf
        // cosh(2a) = cosh(inf) = inf
        // Test all sign variants as this execution path to treat real as infinite
        // is not tested else where.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow on 2a:
        // sinh(2a) -> 0
        // cosh(2a) -> 0
        // removed other assertion
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
        // tan(a + b i) = sinh(2a)/(cosh(2a)+cos(2b)) + i [sin(2b)/(cosh(2a)+cos(2b))]
        // Odd function: negative real cases defined by positive real cases
        final String name = "tanh";
        final UnaryOperator<Complex> operation = Complex::tanh;

        // Overflow on 2b:
        // cos(2b) = cos(inf) = NaN
        // sin(2b) = sin(inf) = NaN
        // removed other assertion

        // Underflow on 2b:
        // cos(2b) -> 1
        // sin(2b) -> 0
        // removed other assertion
        // removed other assertion

        // Overflow on 2a:
        // sinh(2a) = sinh(inf) = inf
        // cosh(2a) = cosh(inf) = inf
        // Test all sign variants as this execution path to treat real as infinite
        // is not tested else where.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow on 2a:
        // sinh(2a) -> 0
        // cosh(2a) -> 0
        // removed other assertion
        // removed other assertion

        // Underflow test.
        // sinh(x) can be approximated by exp(x) but must be overflow safe.
        // im = 2 sin(2y) / e^2|x|
        // This can be computed when e^2|x| only just overflows.
        // Set a case where e^2|x| overflows but the imaginary can be computed
        double x = 709.783 / 2;
        double y = Math.PI / 4;
        // removed other assertion
        // removed other assertion
        // As computed by GNU g++
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

        // exp(a + b i) = exp(a) (cos(b) + i sin(b))

        // Overflow if exp(a) == inf
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

        // exp(a + b i) = exp(a) (cos(b) + i sin(b))

        // Overflow if exp(a) == inf
        // removed other assertion
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

        // exp(a + b i) = exp(a) (cos(b) + i sin(b))

        // Overflow if exp(a) == inf
        // removed other assertion
        // removed other assertion
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

        // exp(a + b i) = exp(a) (cos(b) + i sin(b))

        // Overflow if exp(a) == inf
        // removed other assertion
        // removed other assertion
        // removed other assertion
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

        // exp(a + b i) = exp(a) (cos(b) + i sin(b))

        // Overflow if exp(a) == inf
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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

        // exp(a + b i) = exp(a) (cos(b) + i sin(b))

        // Overflow if exp(a) == inf
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if exp(a) == 0
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

        // exp(a + b i) = exp(a) (cos(b) + i sin(b))

        // Overflow if exp(a) == inf
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if exp(a) == 0
        // removed other assertion
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

        // exp(a + b i) = exp(a) (cos(b) + i sin(b))

        // Overflow if exp(a) == inf
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if exp(a) == 0
        // removed other assertion
        // removed other assertion
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

        // exp(a + b i) = exp(a) (cos(b) + i sin(b))

        // Overflow if exp(a) == inf
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if exp(a) == 0
        // removed other assertion
        // removed other assertion
        // removed other assertion
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

        // exp(a + b i) = exp(a) (cos(b) + i sin(b))

        // Overflow if exp(a) == inf
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if exp(a) == 0
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

                final double x0 = 1.0001;
        final double y0 = Math.sqrt(1.2 - 1.0001 * 1.0001);
        final long maxUlps0 = 1;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
                final double x0 = 1.0001;
        final double y0 = Math.sqrt(1.1 - 1.0001 * 1.0001);
        final long maxUlps0 = 1;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
                final double x0 = 1.0001;
        final double y0 = Math.sqrt(1.02 - 1.0001 * 1.0001);
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double x0 = 1.0001;
        final double y0 = Math.sqrt(1.01 - 1.0001 * 1.0001);
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
                final double x0 = 0.99;
        final double y0 = 0.00001;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
                final double x0 = 0.95;
        final double y0 = 0.00001;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
                final double x0 = 0.9;
        final double y0 = 0.00001;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double x0 = 0.85;
        final double y0 = 0.00001;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double x0 = 0.8;
        final double y0 = 0.00001;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double x0 = 0.75;
        final double y0 = 0.00001;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
                final double x0 = 0.7;
        final double y0 = 0.00001;
        final long maxUlps0 = 2;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
                final double x0 = 0.97;
        final double y0 = Math.sqrt(0.99 - 0.97 * 0.97);
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
                final double x0 = 0.97;
        final double y0 = Math.sqrt(1.01 - 0.97 * 0.97);
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
        // removed other assertion

        // Massive relative error
        // Radius 0.9999
                final double x0 = 0.97;
        final double y0 = Math.sqrt(0.9999 - 0.97 * 0.97);
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
        // removed other assertion

        // Massive relative error
        // Radius 0.9999
        // removed other assertion

        // polar numbers on a 1/8 circle with a magnitude close to 1.
        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                                final double x0 = magnitude[j] * Math.sin(theta);
                final double y0 = magnitude[j] * Math.cos(theta);
                final long maxUlps0 = ulps[j];
                // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
        // removed other assertion

        // Massive relative error
        // Radius 0.9999
        // removed other assertion

        // polar numbers on a 1/8 circle with a magnitude close to 1.
        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                // removed other assertion
            }
        }

        // cis numbers using an increasingly smaller angle
        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
                        final double x0 = Math.sin(theta);
            final double y0 = Math.cos(theta);
            final long maxUlps0 = 0;
            // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
        // removed other assertion

        // Massive relative error
        // Radius 0.9999
        // removed other assertion

        // polar numbers on a 1/8 circle with a magnitude close to 1.
        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                // removed other assertion
            }
        }

        // cis numbers using an increasingly smaller angle
        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
            // removed other assertion
        }

        // Extreme cases.
        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
                final double x0 = down1;
        final double y0 = Double.MIN_NORMAL;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
        // removed other assertion

        // Massive relative error
        // Radius 0.9999
        // removed other assertion

        // polar numbers on a 1/8 circle with a magnitude close to 1.
        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                // removed other assertion
            }
        }

        // cis numbers using an increasingly smaller angle
        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
            // removed other assertion
        }

        // Extreme cases.
        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
        // removed other assertion
                final double x0 = down1;
        final double y0 = Double.MIN_VALUE;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
        // removed other assertion

        // Massive relative error
        // Radius 0.9999
        // removed other assertion

        // polar numbers on a 1/8 circle with a magnitude close to 1.
        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                // removed other assertion
            }
        }

        // cis numbers using an increasingly smaller angle
        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
            // removed other assertion
        }

        // Extreme cases.
        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
        // removed other assertion
        // removed other assertion
        // No use of high-precision computation
                final double x0 = up1;
        final double y0 = Double.MIN_NORMAL;
        final long maxUlps0 = 2;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
        // removed other assertion

        // Massive relative error
        // Radius 0.9999
        // removed other assertion

        // polar numbers on a 1/8 circle with a magnitude close to 1.
        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                // removed other assertion
            }
        }

        // cis numbers using an increasingly smaller angle
        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
            // removed other assertion
        }

        // Extreme cases.
        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
        // removed other assertion
        // removed other assertion
        // No use of high-precision computation
        // removed other assertion
                final double x0 = up1;
        final double y0 = Double.MIN_VALUE;
        final long maxUlps0 = 2;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
        // removed other assertion

        // Massive relative error
        // Radius 0.9999
        // removed other assertion

        // polar numbers on a 1/8 circle with a magnitude close to 1.
        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                // removed other assertion
            }
        }

        // cis numbers using an increasingly smaller angle
        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
            // removed other assertion
        }

        // Extreme cases.
        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
        // removed other assertion
        // removed other assertion
        // No use of high-precision computation
        // removed other assertion
        // removed other assertion

        // Add some cases known to fail without very high precision computation.
        // These have been found using randomly generated cis numbers and the
        // previous Dekker split-summation algorithm:
        // theta = rng.nextDouble()
        // x = Math.sin(theta)
        // y = Math.cos(theta)
        // Easy: <16 ulps with the Dekker summation
                final double x0 = 0.007640392270319105;
        final double y0 = 0.9999708117770016;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
        // removed other assertion

        // Massive relative error
        // Radius 0.9999
        // removed other assertion

        // polar numbers on a 1/8 circle with a magnitude close to 1.
        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                // removed other assertion
            }
        }

        // cis numbers using an increasingly smaller angle
        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
            // removed other assertion
        }

        // Extreme cases.
        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
        // removed other assertion
        // removed other assertion
        // No use of high-precision computation
        // removed other assertion
        // removed other assertion

        // Add some cases known to fail without very high precision computation.
        // These have been found using randomly generated cis numbers and the
        // previous Dekker split-summation algorithm:
        // theta = rng.nextDouble()
        // x = Math.sin(theta)
        // y = Math.cos(theta)
        // Easy: <16 ulps with the Dekker summation
        // removed other assertion
                final double x0 = 0.40158433204881533;
        final double y0 = 0.9158220483548684;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
        // removed other assertion

        // Massive relative error
        // Radius 0.9999
        // removed other assertion

        // polar numbers on a 1/8 circle with a magnitude close to 1.
        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                // removed other assertion
            }
        }

        // cis numbers using an increasingly smaller angle
        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
            // removed other assertion
        }

        // Extreme cases.
        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
        // removed other assertion
        // removed other assertion
        // No use of high-precision computation
        // removed other assertion
        // removed other assertion

        // Add some cases known to fail without very high precision computation.
        // These have been found using randomly generated cis numbers and the
        // previous Dekker split-summation algorithm:
        // theta = rng.nextDouble()
        // x = Math.sin(theta)
        // y = Math.cos(theta)
        // Easy: <16 ulps with the Dekker summation
        // removed other assertion
        // removed other assertion
                final double x0 = 0.13258789214774552;
        final double y0 = 0.9911712520325727;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
        // removed other assertion

        // Massive relative error
        // Radius 0.9999
        // removed other assertion

        // polar numbers on a 1/8 circle with a magnitude close to 1.
        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                // removed other assertion
            }
        }

        // cis numbers using an increasingly smaller angle
        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
            // removed other assertion
        }

        // Extreme cases.
        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
        // removed other assertion
        // removed other assertion
        // No use of high-precision computation
        // removed other assertion
        // removed other assertion

        // Add some cases known to fail without very high precision computation.
        // These have been found using randomly generated cis numbers and the
        // previous Dekker split-summation algorithm:
        // theta = rng.nextDouble()
        // x = Math.sin(theta)
        // y = Math.cos(theta)
        // Easy: <16 ulps with the Dekker summation
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double x0 = 0.2552206803398717;
        final double y0 = 0.9668828286441191;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
        // removed other assertion

        // Massive relative error
        // Radius 0.9999
        // removed other assertion

        // polar numbers on a 1/8 circle with a magnitude close to 1.
        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                // removed other assertion
            }
        }

        // cis numbers using an increasingly smaller angle
        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
            // removed other assertion
        }

        // Extreme cases.
        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
        // removed other assertion
        // removed other assertion
        // No use of high-precision computation
        // removed other assertion
        // removed other assertion

        // Add some cases known to fail without very high precision computation.
        // These have been found using randomly generated cis numbers and the
        // previous Dekker split-summation algorithm:
        // theta = rng.nextDouble()
        // x = Math.sin(theta)
        // y = Math.cos(theta)
        // Easy: <16 ulps with the Dekker summation
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Hard: >1024 ulps with the Dekker summation
                final double x0 = 0.4650816500945186;
        final double y0 = 0.8852677892848919;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
        // removed other assertion

        // Massive relative error
        // Radius 0.9999
        // removed other assertion

        // polar numbers on a 1/8 circle with a magnitude close to 1.
        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                // removed other assertion
            }
        }

        // cis numbers using an increasingly smaller angle
        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
            // removed other assertion
        }

        // Extreme cases.
        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
        // removed other assertion
        // removed other assertion
        // No use of high-precision computation
        // removed other assertion
        // removed other assertion

        // Add some cases known to fail without very high precision computation.
        // These have been found using randomly generated cis numbers and the
        // previous Dekker split-summation algorithm:
        // theta = rng.nextDouble()
        // x = Math.sin(theta)
        // y = Math.cos(theta)
        // Easy: <16 ulps with the Dekker summation
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Hard: >1024 ulps with the Dekker summation
        // removed other assertion
                final double x0 = 0.06548693057069123;
        final double y0 = 0.9978534270745526;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
        // removed other assertion

        // Massive relative error
        // Radius 0.9999
        // removed other assertion

        // polar numbers on a 1/8 circle with a magnitude close to 1.
        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                // removed other assertion
            }
        }

        // cis numbers using an increasingly smaller angle
        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
            // removed other assertion
        }

        // Extreme cases.
        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
        // removed other assertion
        // removed other assertion
        // No use of high-precision computation
        // removed other assertion
        // removed other assertion

        // Add some cases known to fail without very high precision computation.
        // These have been found using randomly generated cis numbers and the
        // previous Dekker split-summation algorithm:
        // theta = rng.nextDouble()
        // x = Math.sin(theta)
        // y = Math.cos(theta)
        // Easy: <16 ulps with the Dekker summation
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Hard: >1024 ulps with the Dekker summation
        // removed other assertion
        // removed other assertion
                final double x0 = 0.08223027214657339;
        final double y0 = 0.9966133564942327;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
        // removed other assertion

        // Massive relative error
        // Radius 0.9999
        // removed other assertion

        // polar numbers on a 1/8 circle with a magnitude close to 1.
        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                // removed other assertion
            }
        }

        // cis numbers using an increasingly smaller angle
        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
            // removed other assertion
        }

        // Extreme cases.
        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
        // removed other assertion
        // removed other assertion
        // No use of high-precision computation
        // removed other assertion
        // removed other assertion

        // Add some cases known to fail without very high precision computation.
        // These have been found using randomly generated cis numbers and the
        // previous Dekker split-summation algorithm:
        // theta = rng.nextDouble()
        // x = Math.sin(theta)
        // y = Math.cos(theta)
        // Easy: <16 ulps with the Dekker summation
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Hard: >1024 ulps with the Dekker summation
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double x0 = 0.06548693057069123;
        final double y0 = 0.9978534270745526;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
        // removed other assertion

        // Massive relative error
        // Radius 0.9999
        // removed other assertion

        // polar numbers on a 1/8 circle with a magnitude close to 1.
        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                // removed other assertion
            }
        }

        // cis numbers using an increasingly smaller angle
        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
            // removed other assertion
        }

        // Extreme cases.
        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
        // removed other assertion
        // removed other assertion
        // No use of high-precision computation
        // removed other assertion
        // removed other assertion

        // Add some cases known to fail without very high precision computation.
        // These have been found using randomly generated cis numbers and the
        // previous Dekker split-summation algorithm:
        // theta = rng.nextDouble()
        // x = Math.sin(theta)
        // y = Math.cos(theta)
        // Easy: <16 ulps with the Dekker summation
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Hard: >1024 ulps with the Dekker summation
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double x0 = 0.04590800199633988;
        final double y0 = 0.9989456718724518;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Underflow if sqrt(a^2 + b^2) -> 0
        // removed other assertion
        // removed other assertion
        // Math.hypot(min, min) = min.
        // To compute the expected result do scaling of the actual hypot = sqrt(2).
        // log(a/n) = log(a) - log(n)
        // n = 2^1074 => log(a) - log(2) * 1074
        double expected = Math.log(Math.sqrt(2)) - Math.log(2) * 1074;
        // removed other assertion
        expected = Math.log(Math.sqrt(5)) - Math.log(2) * 1074;
        // removed other assertion

        // Imprecision if sqrt(a^2 + b^2) == 1 as log(1) is 0.
        // Method should switch to using log1p(x^2 + x^2 - 1) * 0.5.

        // In the following:
        // max = max(real, imaginary)
        // min = min(real, imaginary)

        // No cancellation error when max > 1

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Cancellation error when max < 1.

        // Hard: 4 * min^2 < |max^2 - 1|
        // Gets harder as max is further from 1
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // At this point the log function does not use high precision computation
        // removed other assertion

        // Very hard: 4 * min^2 > |max^2 - 1|

        // Radius 0.99
        // removed other assertion
        // Radius 1.01
        // removed other assertion

        // Massive relative error
        // Radius 0.9999
        // removed other assertion

        // polar numbers on a 1/8 circle with a magnitude close to 1.
        final int steps = 20;
        final double[] magnitude = {0.999, 1.0, 1.001};
        final int[] ulps = {0, 0, 1};
        for (int j = 0; j < magnitude.length; j++) {
            for (int i = 1; i <= steps; i++) {
                final double theta = i * Math.PI / (4 * steps);
                // removed other assertion
            }
        }

        // cis numbers using an increasingly smaller angle
        double theta = Math.PI / (4 * steps);
        while (theta > 0) {
            theta /= 2;
            // removed other assertion
        }

        // Extreme cases.
        final double up1 = Math.nextUp(1.0);
        final double down1 = Math.nextDown(1.0);
        // removed other assertion
        // removed other assertion
        // No use of high-precision computation
        // removed other assertion
        // removed other assertion

        // Add some cases known to fail without very high precision computation.
        // These have been found using randomly generated cis numbers and the
        // previous Dekker split-summation algorithm:
        // theta = rng.nextDouble()
        // x = Math.sin(theta)
        // y = Math.cos(theta)
        // Easy: <16 ulps with the Dekker summation
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Hard: >1024 ulps with the Dekker summation
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final double x0 = 0.3019636508581243;
        final double y0 = 0.9533194394118022;
        final long maxUlps0 = 0;
        // Compute the best value we can
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

        // Test real/imaginary only numbers satisfy the definition using polar coordinates:
        // real = sqrt(abs()) * Math.cos(arg() / 2)
        // imag = sqrt(abs()) * Math.sin(arg() / 2)
        // However direct use of sin/cos will result in incorrect results due floating-point error.
        // This test asserts the on the closest result to the exact answer which is possible
        // if not using a simple polar computation.
        // Note: If this test fails in the set-up assertions it is due to a change in the
        // precision of java.util.Math.

        // For positive real-only the argument is +/-0.
        // For negative real-only the argument is +/-pi.
        // removed other assertion
        // removed other assertion
        // In both cases the trigonomic functions should be exact but
        // cos(pi/2) cannot be as pi/2 is not exact.
        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        // removed other assertion
        // removed other assertion
        // For imaginary-only the argument is Math.atan2(y, 0) = +/- pi/2.
        // removed other assertion
        // removed other assertion
        // There is 1 ULP difference in the result of cos/sin of pi/4.
        // It should be sqrt(2) / 2 for both.
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for (final double a : new double[] {0.5, 1.0, 1.2322, 345345.234523}) {
            final double rootA = Math.sqrt(a);
            // removed other assertion
            // This should be exact. It will fail if using the polar computation
            // real = sqrt(abs()) * Math.cos(arg() / 2) as cos(pi/2) is not 0.0 but 6.123233995736766e-17
            // removed other assertion
            // This should be exact. It won't be if Complex is using polar computation
            // with sin/cos which does not output the same result for angle pi/4.
            // removed other assertion
            // removed other assertion
        }

        // Check overflow safe.
        double a = Double.MAX_VALUE;
        final double b = a / 4;
        // removed other assertion
        // The expected absolute value has been computed using BigDecimal on Java 9
        //final double newAbs = new BigDecimal(a).multiply(new BigDecimal(a)).add(
        //                      new BigDecimal(b).multiply(new BigDecimal(b)))
        //                     .sqrt(MathContext.DECIMAL128).sqrt(MathContext.DECIMAL128).doubleValue()
        final double newAbs = 1.3612566508088272E154;
        // removed other assertion
        // removed other assertion

        // Note that the computation is possible in polar coords if abs() does not overflow.
        a = Double.MAX_VALUE / 2;
        // removed other assertion
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

        // Test real/imaginary only numbers satisfy the definition using polar coordinates:
        // real = sqrt(abs()) * Math.cos(arg() / 2)
        // imag = sqrt(abs()) * Math.sin(arg() / 2)
        // However direct use of sin/cos will result in incorrect results due floating-point error.
        // This test asserts the on the closest result to the exact answer which is possible
        // if not using a simple polar computation.
        // Note: If this test fails in the set-up assertions it is due to a change in the
        // precision of java.util.Math.

        // For positive real-only the argument is +/-0.
        // For negative real-only the argument is +/-pi.
        // removed other assertion
        // removed other assertion
        // In both cases the trigonomic functions should be exact but
        // cos(pi/2) cannot be as pi/2 is not exact.
        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        // removed other assertion
        // removed other assertion
        // For imaginary-only the argument is Math.atan2(y, 0) = +/- pi/2.
        // removed other assertion
        // removed other assertion
        // There is 1 ULP difference in the result of cos/sin of pi/4.
        // It should be sqrt(2) / 2 for both.
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for (final double a : new double[] {0.5, 1.0, 1.2322, 345345.234523}) {
            final double rootA = Math.sqrt(a);
            // removed other assertion
            // This should be exact. It will fail if using the polar computation
            // real = sqrt(abs()) * Math.cos(arg() / 2) as cos(pi/2) is not 0.0 but 6.123233995736766e-17
            // removed other assertion
            // This should be exact. It won't be if Complex is using polar computation
            // with sin/cos which does not output the same result for angle pi/4.
            // removed other assertion
            // removed other assertion
        }

        // Check overflow safe.
        double a = Double.MAX_VALUE;
        final double b = a / 4;
        // removed other assertion
        // The expected absolute value has been computed using BigDecimal on Java 9
        //final double newAbs = new BigDecimal(a).multiply(new BigDecimal(a)).add(
        //                      new BigDecimal(b).multiply(new BigDecimal(b)))
        //                     .sqrt(MathContext.DECIMAL128).sqrt(MathContext.DECIMAL128).doubleValue()
        final double newAbs = 1.3612566508088272E154;
        // removed other assertion
        // removed other assertion

        // Note that the computation is possible in polar coords if abs() does not overflow.
        a = Double.MAX_VALUE / 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
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

        // Test real/imaginary only numbers satisfy the definition using polar coordinates:
        // real = sqrt(abs()) * Math.cos(arg() / 2)
        // imag = sqrt(abs()) * Math.sin(arg() / 2)
        // However direct use of sin/cos will result in incorrect results due floating-point error.
        // This test asserts the on the closest result to the exact answer which is possible
        // if not using a simple polar computation.
        // Note: If this test fails in the set-up assertions it is due to a change in the
        // precision of java.util.Math.

        // For positive real-only the argument is +/-0.
        // For negative real-only the argument is +/-pi.
        // removed other assertion
        // removed other assertion
        // In both cases the trigonomic functions should be exact but
        // cos(pi/2) cannot be as pi/2 is not exact.
        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        // removed other assertion
        // removed other assertion
        // For imaginary-only the argument is Math.atan2(y, 0) = +/- pi/2.
        // removed other assertion
        // removed other assertion
        // There is 1 ULP difference in the result of cos/sin of pi/4.
        // It should be sqrt(2) / 2 for both.
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for (final double a : new double[] {0.5, 1.0, 1.2322, 345345.234523}) {
            final double rootA = Math.sqrt(a);
            // removed other assertion
            // This should be exact. It will fail if using the polar computation
            // real = sqrt(abs()) * Math.cos(arg() / 2) as cos(pi/2) is not 0.0 but 6.123233995736766e-17
            // removed other assertion
            // This should be exact. It won't be if Complex is using polar computation
            // with sin/cos which does not output the same result for angle pi/4.
            // removed other assertion
            // removed other assertion
        }

        // Check overflow safe.
        double a = Double.MAX_VALUE;
        final double b = a / 4;
        // removed other assertion
        // The expected absolute value has been computed using BigDecimal on Java 9
        //final double newAbs = new BigDecimal(a).multiply(new BigDecimal(a)).add(
        //                      new BigDecimal(b).multiply(new BigDecimal(b)))
        //                     .sqrt(MathContext.DECIMAL128).sqrt(MathContext.DECIMAL128).doubleValue()
        final double newAbs = 1.3612566508088272E154;
        // removed other assertion
        // removed other assertion

        // Note that the computation is possible in polar coords if abs() does not overflow.
        a = Double.MAX_VALUE / 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Check minimum normal value conditions
        // Computing in polar coords produces a very different result with
        // MIN_VALUE so use MIN_NORMAL
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

        // Test real/imaginary only numbers satisfy the definition using polar coordinates:
        // real = sqrt(abs()) * Math.cos(arg() / 2)
        // imag = sqrt(abs()) * Math.sin(arg() / 2)
        // However direct use of sin/cos will result in incorrect results due floating-point error.
        // This test asserts the on the closest result to the exact answer which is possible
        // if not using a simple polar computation.
        // Note: If this test fails in the set-up assertions it is due to a change in the
        // precision of java.util.Math.

        // For positive real-only the argument is +/-0.
        // For negative real-only the argument is +/-pi.
        // removed other assertion
        // removed other assertion
        // In both cases the trigonomic functions should be exact but
        // cos(pi/2) cannot be as pi/2 is not exact.
        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        // removed other assertion
        // removed other assertion
        // For imaginary-only the argument is Math.atan2(y, 0) = +/- pi/2.
        // removed other assertion
        // removed other assertion
        // There is 1 ULP difference in the result of cos/sin of pi/4.
        // It should be sqrt(2) / 2 for both.
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for (final double a : new double[] {0.5, 1.0, 1.2322, 345345.234523}) {
            final double rootA = Math.sqrt(a);
            // removed other assertion
            // This should be exact. It will fail if using the polar computation
            // real = sqrt(abs()) * Math.cos(arg() / 2) as cos(pi/2) is not 0.0 but 6.123233995736766e-17
            // removed other assertion
            // This should be exact. It won't be if Complex is using polar computation
            // with sin/cos which does not output the same result for angle pi/4.
            // removed other assertion
            // removed other assertion
        }

        // Check overflow safe.
        double a = Double.MAX_VALUE;
        final double b = a / 4;
        // removed other assertion
        // The expected absolute value has been computed using BigDecimal on Java 9
        //final double newAbs = new BigDecimal(a).multiply(new BigDecimal(a)).add(
        //                      new BigDecimal(b).multiply(new BigDecimal(b)))
        //                     .sqrt(MathContext.DECIMAL128).sqrt(MathContext.DECIMAL128).doubleValue()
        final double newAbs = 1.3612566508088272E154;
        // removed other assertion
        // removed other assertion

        // Note that the computation is possible in polar coords if abs() does not overflow.
        a = Double.MAX_VALUE / 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Check minimum normal value conditions
        // Computing in polar coords produces a very different result with
        // MIN_VALUE so use MIN_NORMAL
        a = Double.MIN_NORMAL;
        // removed other assertion
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

        // Test real/imaginary only numbers satisfy the definition using polar coordinates:
        // real = sqrt(abs()) * Math.cos(arg() / 2)
        // imag = sqrt(abs()) * Math.sin(arg() / 2)
        // However direct use of sin/cos will result in incorrect results due floating-point error.
        // This test asserts the on the closest result to the exact answer which is possible
        // if not using a simple polar computation.
        // Note: If this test fails in the set-up assertions it is due to a change in the
        // precision of java.util.Math.

        // For positive real-only the argument is +/-0.
        // For negative real-only the argument is +/-pi.
        // removed other assertion
        // removed other assertion
        // In both cases the trigonomic functions should be exact but
        // cos(pi/2) cannot be as pi/2 is not exact.
        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        // removed other assertion
        // removed other assertion
        // For imaginary-only the argument is Math.atan2(y, 0) = +/- pi/2.
        // removed other assertion
        // removed other assertion
        // There is 1 ULP difference in the result of cos/sin of pi/4.
        // It should be sqrt(2) / 2 for both.
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for (final double a : new double[] {0.5, 1.0, 1.2322, 345345.234523}) {
            final double rootA = Math.sqrt(a);
            // removed other assertion
            // This should be exact. It will fail if using the polar computation
            // real = sqrt(abs()) * Math.cos(arg() / 2) as cos(pi/2) is not 0.0 but 6.123233995736766e-17
            // removed other assertion
            // This should be exact. It won't be if Complex is using polar computation
            // with sin/cos which does not output the same result for angle pi/4.
            // removed other assertion
            // removed other assertion
        }

        // Check overflow safe.
        double a = Double.MAX_VALUE;
        final double b = a / 4;
        // removed other assertion
        // The expected absolute value has been computed using BigDecimal on Java 9
        //final double newAbs = new BigDecimal(a).multiply(new BigDecimal(a)).add(
        //                      new BigDecimal(b).multiply(new BigDecimal(b)))
        //                     .sqrt(MathContext.DECIMAL128).sqrt(MathContext.DECIMAL128).doubleValue()
        final double newAbs = 1.3612566508088272E154;
        // removed other assertion
        // removed other assertion

        // Note that the computation is possible in polar coords if abs() does not overflow.
        a = Double.MAX_VALUE / 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Check minimum normal value conditions
        // Computing in polar coords produces a very different result with
        // MIN_VALUE so use MIN_NORMAL
        a = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
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

        // Test real/imaginary only numbers satisfy the definition using polar coordinates:
        // real = sqrt(abs()) * Math.cos(arg() / 2)
        // imag = sqrt(abs()) * Math.sin(arg() / 2)
        // However direct use of sin/cos will result in incorrect results due floating-point error.
        // This test asserts the on the closest result to the exact answer which is possible
        // if not using a simple polar computation.
        // Note: If this test fails in the set-up assertions it is due to a change in the
        // precision of java.util.Math.

        // For positive real-only the argument is +/-0.
        // For negative real-only the argument is +/-pi.
        // removed other assertion
        // removed other assertion
        // In both cases the trigonomic functions should be exact but
        // cos(pi/2) cannot be as pi/2 is not exact.
        final double cosArgRe = 1.0;
        final double sinArgRe = 0.0;
        // removed other assertion
        // removed other assertion
        // For imaginary-only the argument is Math.atan2(y, 0) = +/- pi/2.
        // removed other assertion
        // removed other assertion
        // There is 1 ULP difference in the result of cos/sin of pi/4.
        // It should be sqrt(2) / 2 for both.
        final double cosArgIm = Math.cos(Math.PI / 4);
        final double sinArgIm = Math.sin(Math.PI / 4);
        final double root2over2 = Math.sqrt(2) / 2;
        final double ulp = Math.ulp(cosArgIm);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for (final double a : new double[] {0.5, 1.0, 1.2322, 345345.234523}) {
            final double rootA = Math.sqrt(a);
            // removed other assertion
            // This should be exact. It will fail if using the polar computation
            // real = sqrt(abs()) * Math.cos(arg() / 2) as cos(pi/2) is not 0.0 but 6.123233995736766e-17
            // removed other assertion
            // This should be exact. It won't be if Complex is using polar computation
            // with sin/cos which does not output the same result for angle pi/4.
            // removed other assertion
            // removed other assertion
        }

        // Check overflow safe.
        double a = Double.MAX_VALUE;
        final double b = a / 4;
        // removed other assertion
        // The expected absolute value has been computed using BigDecimal on Java 9
        //final double newAbs = new BigDecimal(a).multiply(new BigDecimal(a)).add(
        //                      new BigDecimal(b).multiply(new BigDecimal(b)))
        //                     .sqrt(MathContext.DECIMAL128).sqrt(MathContext.DECIMAL128).doubleValue()
        final double newAbs = 1.3612566508088272E154;
        // removed other assertion
        // removed other assertion

        // Note that the computation is possible in polar coords if abs() does not overflow.
        a = Double.MAX_VALUE / 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Check minimum normal value conditions
        // Computing in polar coords produces a very different result with
        // MIN_VALUE so use MIN_NORMAL
        a = Double.MIN_NORMAL;
        // removed other assertion
        // removed other assertion
        // removed other assertion
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

        // ln(a + b i) = ln(|a + b i|) + i arg(a + b i)
        // |a + b i| = sqrt(a^2 + b^2)
        // arg(a + b i) = Math.atan2(imaginary, real)

        // Overflow if sqrt(a^2 + b^2) == inf.
        // Matlab computes this.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertComplex(Double.MAX_VALUE, Double.MAX_VALUE / 4, name, operation, 7.098130252042921e2, 2.449786631268641e-1, 2);
    }

}
