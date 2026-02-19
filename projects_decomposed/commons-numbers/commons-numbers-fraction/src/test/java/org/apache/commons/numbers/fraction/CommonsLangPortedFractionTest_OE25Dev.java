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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test cases for the {@link Fraction} class.
 *
 * <p>This class is ported from commons lang to demonstrate interoperability of
 * the Fraction class in numbers.</p>
 */
class CommonsLangPortedFractionTest_OE25Dev {

    private static final int SKIP = 500;  //53

    //--------------------------------------------------------------------------

/*
 *  Removed as not supported in numbers.
 *
 *  @Test
 *  void testFactory_int_int_int() {
 *      Fraction f = null;
 *
 *      // zero
 *      f = Fraction.of(0, 0, 2);
 *      assertEquals(0, f.getNumerator());
 *      assertEquals(1, f.getDenominator());
 *
 *      f = Fraction.of(2, 0, 2);
 *      assertEquals(2, f.getNumerator());
 *      assertEquals(1, f.getDenominator());
 *
 *      f = Fraction.of(0, 1, 2);
 *      assertEquals(1, f.getNumerator());
 *      assertEquals(2, f.getDenominator());
 *
 *      // normal
 *      f = Fraction.of(1, 1, 2);
 *      assertEquals(3, f.getNumerator());
 *      assertEquals(2, f.getDenominator());
 *
 *      // negatives
 *      assertThrows(ArithmeticException.class, () -> Fraction.of(1, -6, -10));
 *      assertThrows(ArithmeticException.class, () -> Fraction.of(1, -6, -10));
 *      assertThrows(ArithmeticException.class, () -> Fraction.of(1, -6, -10));
 *
 *      // negative whole
 *      f = Fraction.of(-1, 6, 10);
 *      assertEquals(-8, f.getNumerator());
 *      assertEquals(5, f.getDenominator());
 *
 *      assertThrows(ArithmeticException.class, () -> Fraction.of(-1, -6, 10));
 *      assertThrows(ArithmeticException.class, () -> Fraction.of(-1, 6, -10));
 *      assertThrows(ArithmeticException.class, () -> Fraction.of(-1, -6, -10));
 *
 *      // zero denominator
 *      assertThrows(ArithmeticException.class, () -> Fraction.of(0, 1, 0));
 *      assertThrows(ArithmeticException.class, () -> Fraction.of(1, 2, 0));
 *      assertThrows(ArithmeticException.class, () -> Fraction.of(-1, -3, 0));
 *      assertThrows(ArithmeticException.class, () -> Fraction.of(Integer.MAX_VALUE, 1, 2));
 *      assertThrows(ArithmeticException.class, () -> Fraction.of(-Integer.MAX_VALUE, 1, 2));
 *
 *      // very large
 *      f = Fraction.of(-1, 0, Integer.MAX_VALUE);
 *      assertEquals(-1, f.getNumerator());
 *      assertEquals(1, f.getDenominator());
 *
 *      // negative denominators not allowed in this constructor.
 *      assertThrows(ArithmeticException.class, () -> Fraction.of(0, 4, Integer.MIN_VALUE));
 *      assertThrows(ArithmeticException.class, () -> Fraction.of(1, 1, Integer.MAX_VALUE));
 *      assertThrows(ArithmeticException.class, () -> Fraction.of(-1, 2, Integer.MAX_VALUE));
 *  }
 */

/*
 *  Removed as not supported in numbers.
 *
 *  @Test
 *  void testFactory_String() {
 *      assertThrows(NullPointerException.class, () -> Fraction.from(null));
 *  }
 *
 *
 *  @Test
 *  void testFactory_String_double() {
 *      Fraction f = null;
 *
 *      f = Fraction.from("0.0");
 *      assertEquals(0, f.getNumerator());
 *      assertEquals(1, f.getDenominator());
 *
 *      f = Fraction.from("0.2");
 *      assertEquals(1, f.getNumerator());
 *      assertEquals(5, f.getDenominator());
 *
 *      f = Fraction.from("0.5");
 *      assertEquals(1, f.getNumerator());
 *      assertEquals(2, f.getDenominator());
 *
 *      f = Fraction.from("0.66666");
 *      assertEquals(2, f.getNumerator());
 *      assertEquals(3, f.getDenominator());
 *
 *      assertThrows(NumberFormatException.class, () -> Fraction.from("2.3R"));
 *      assertThrows(NumberFormatException.class, () -> Fraction.from("2147483648")); // too big
 *      assertThrows(NumberFormatException.class, () -> Fraction.from("."));
 *  }
 *
 *  @Test
 *  void testFactory_String_proper() {
 *      Fraction f = null;
 *
 *      f = Fraction.from("0 0/1");
 *      assertEquals(0, f.getNumerator());
 *      assertEquals(1, f.getDenominator());
 *
 *      f = Fraction.from("1 1/5");
 *      assertEquals(6, f.getNumerator());
 *      assertEquals(5, f.getDenominator());
 *
 *      f = Fraction.from("7 1/2");
 *      assertEquals(15, f.getNumerator());
 *      assertEquals(2, f.getDenominator());
 *
 *      f = Fraction.from("1 2/4");
 *      assertEquals(3, f.getNumerator());
 *      assertEquals(2, f.getDenominator());
 *
 *      f = Fraction.from("-7 1/2");
 *      assertEquals(-15, f.getNumerator());
 *      assertEquals(2, f.getDenominator());
 *
 *      f = Fraction.from("-1 2/4");
 *      assertEquals(-3, f.getNumerator());
 *      assertEquals(2, f.getDenominator());
 *
 *      assertThrows(NumberFormatException.class, () -> Fraction.from("2 3"));
 *      assertThrows(NumberFormatException.class, () -> Fraction.from("a 3"));
 *      assertThrows(NumberFormatException.class, () -> Fraction.from("2 b/4"));
 *      assertThrows(NumberFormatException.class, () -> Fraction.from("2 "));
 *      assertThrows(NumberFormatException.class, () -> Fraction.from(" 3"));
 *      assertThrows(NumberFormatException.class, () -> Fraction.from(" "));
 *  }
 *
 *  @Test
 *  void testFactory_String_improper() {
 *      Fraction f = null;
 *
 *      f = Fraction.from("0/1");
 *      assertEquals(0, f.getNumerator());
 *      assertEquals(1, f.getDenominator());
 *
 *      f = Fraction.from("1/5");
 *      assertEquals(1, f.getNumerator());
 *      assertEquals(5, f.getDenominator());
 *
 *      f = Fraction.from("1/2");
 *      assertEquals(1, f.getNumerator());
 *      assertEquals(2, f.getDenominator());
 *
 *      f = Fraction.from("2/3");
 *      assertEquals(2, f.getNumerator());
 *      assertEquals(3, f.getDenominator());
 *
 *      f = Fraction.from("7/3");
 *      assertEquals(7, f.getNumerator());
 *      assertEquals(3, f.getDenominator());
 *
 *      f = Fraction.from("2/4");
 *      assertEquals(1, f.getNumerator());
 *      assertEquals(2, f.getDenominator());
 *
 *      assertThrows(NumberFormatException.class, () -> Fraction.from("2/d"));
 *      assertThrows(NumberFormatException.class, () -> Fraction.from("2e/3"));
 *      assertThrows(NumberFormatException.class, () -> Fraction.from("2/"));
 *      assertThrows(NumberFormatException.class, () -> Fraction.from("/"));
 *  }
 *
 *  @Test
 *  void testGets() {
 *      Fraction f = null;
 *
 *      f = Fraction.of(3, 5, 6);
 *      assertEquals(23, f.getNumerator());
 *      assertEquals(3, f.getProperWhole());
 *      assertEquals(5, f.getProperNumerator());
 *      assertEquals(6, f.getDenominator());
 *
 *      f = Fraction.of(-3, 5, 6);
 *      assertEquals(-23, f.getNumerator());
 *      assertEquals(-3, f.getProperWhole());
 *      assertEquals(5, f.getProperNumerator());
 *      assertEquals(6, f.getDenominator());
 *
 *      f = Fraction.of(Integer.MIN_VALUE, 0, 1);
 *      assertEquals(Integer.MIN_VALUE, f.getNumerator());
 *      assertEquals(Integer.MIN_VALUE, f.getProperWhole());
 *      assertEquals(0, f.getProperNumerator());
 *      assertEquals(1, f.getDenominator());
 *  }
 *
 *  @Test
 *  void testConversions() {
 *      Fraction f = null;
 *
 *      f = Fraction.of(3, 7, 8);
 *      assertEquals(3, f.intValue());
 *      assertEquals(3L, f.longValue());
 *      assertEquals(3.875f, f.floatValue(), 0.00001f);
 *      assertEquals(3.875d, f.doubleValue(), 0.00001d);
 *  }
 *
 *  @Test
 *  void testReduce() {
 *      Fraction f = null;
 *
 *      f = Fraction.of(50, 75);
 *      Fraction result = f.reduce();
 *      assertEquals(2, result.getNumerator());
 *      assertEquals(3, result.getDenominator());
 *
 *      f = Fraction.of(-2, -3);
 *      result = f.reduce();
 *      assertEquals(2, result.getNumerator());
 *      assertEquals(3, result.getDenominator());
 *
 *      f = Fraction.of(2, -3);
 *      result = f.reduce();
 *      assertEquals(-2, result.getNumerator());
 *      assertEquals(3, result.getDenominator());
 *
 *      f = Fraction.of(-2, 3);
 *      result = f.reduce();
 *      assertEquals(-2, result.getNumerator());
 *      assertEquals(3, result.getDenominator());
 *      assertSame(f, result);
 *
 *      f = Fraction.of(2, 3);
 *      result = f.reduce();
 *      assertEquals(2, result.getNumerator());
 *      assertEquals(3, result.getDenominator());
 *      assertSame(f, result);
 *
 *      f = Fraction.of(0, 1);
 *      result = f.reduce();
 *      assertEquals(0, result.getNumerator());
 *      assertEquals(1, result.getDenominator());
 *      assertSame(f, result);
 *
 *      f = Fraction.of(0, 100);
 *      result = f.reduce();
 *      assertEquals(0, result.getNumerator());
 *      assertEquals(1, result.getDenominator());
 *      assertSame(result, Fraction.ZERO);
 *
 *      f = Fraction.of(Integer.MIN_VALUE, 2);
 *      result = f.reduce();
 *      assertEquals(Integer.MIN_VALUE / 2, result.getNumerator());
 *      assertEquals(1, result.getDenominator());
 *  }
 *
 *  @Test
 *  void testreciprocal() {
 *      Fraction f = null;
 *
 *      f = Fraction.of(50, 75);
 *      f = f.reciprocal();
 *      assertEquals(3, f.getNumerator());
 *      assertEquals(2, f.getDenominator());
 *
 *      f = Fraction.of(4, 3);
 *      f = f.reciprocal();
 *      assertEquals(3, f.getNumerator());
 *      assertEquals(4, f.getDenominator());
 *
 *      f = Fraction.of(-15, 47);
 *      f = f.reciprocal();
 *      assertEquals(47, f.getNumerator());
 *      assertEquals(-15, f.getDenominator());
 *
 *      assertThrows(ArithmeticException.class, () -> Fraction.of(0, 3).reciprocal());
 *      Fraction.of(Integer.MIN_VALUE, 1).reciprocal();
 *
 *      f = Fraction.of(Integer.MAX_VALUE, 1);
 *      f = f.reciprocal();
 *      assertEquals(1, f.getNumerator());
 *      assertEquals(Integer.MAX_VALUE, f.getDenominator());
 *  }
 */

/*
 *  Removed as not supported in numbers.
 *
 *  @Test
 *  void testToProperString() {
 *      Fraction f = null;
 *
 *      f = Fraction.of(3, 5);
 *      final String str = f.toProperString();
 *      assertEquals("3/5", str);
 *      assertSame(str, f.toProperString());
 *
 *      f = Fraction.of(7, 5);
 *      assertEquals("1 2/5", f.toProperString());
 *
 *      f = Fraction.of(14, 10);
 *      assertEquals("1 2/5", f.toProperString());
 *
 *      f = Fraction.of(4, 2);
 *      assertEquals("2", f.toProperString());
 *
 *      f = Fraction.of(0, 2);
 *      assertEquals("0", f.toProperString());
 *
 *      f = Fraction.of(2, 2);
 *      assertEquals("1", f.toProperString());
 *
 *      f = Fraction.of(-7, 5);
 *      assertEquals("-1 2/5", f.toProperString());
 *
 *      f = Fraction.of(Integer.MIN_VALUE, 0, 1);
 *      assertEquals("-2147483648", f.toProperString());
 *
 *      f = Fraction.of(-1, 1, Integer.MAX_VALUE);
 *      assertEquals("-1 1/2147483647", f.toProperString());
 *
 *      assertEquals("-1", Fraction.of(-1).toProperString());
 *  }
 */

    /**
     * Assert the specified operation on the fraction throws the expected type.
     * This method exists to ensure the fractions are constructed without an exception
     * and the operation is tested to throw the exception.
     *
     * @param <T> the generic type
     * @param expectedType the expected type
     * @param f the fraction
     * @param operation the operation
     * @return the throwable
     */
    private static <T extends Throwable> T assertOperationThrows(Class<T> expectedType,
            Fraction f, UnaryOperator<Fraction> operation) {
        return assertThrows(expectedType, () -> operation.apply(f));
    }

    /**
     * Assert the specified operation on two fractions throws the expected type.
     * This method exists to ensure the fractions are constructed without an exception
     * and the operation is tested to throw the exception.
     *
     * @param <T> the generic type
     * @param expectedType the expected type
     * @param f1 the first fraction
     * @param f2 the second fraction
     * @param operation the operation
     * @return the throwable
     */
    private static <T extends Throwable> T assertOperationThrows(Class<T> expectedType,
            Fraction f1, Fraction f2, BiFunction<Fraction, Fraction, Fraction> operation) {
        return assertThrows(expectedType, () -> operation.apply(f1, f2));
    }

    @Test
    void testConstants_1_oe() {
        assertEquals(0, Fraction.ZERO.getNumerator());
    }

    @Test
    void testConstants_2_oe() {
        // removed other assertion
        assertEquals(1, Fraction.ZERO.getDenominator());
    }

    @Test
    void testConstants_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals(1, Fraction.ONE.getNumerator());
    }

    @Test
    void testConstants_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(1, Fraction.ONE.getDenominator());
    }

    @Test
    void testFactory_int_int_1_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        assertEquals(0, f.getNumerator());
    }

    @Test
    void testFactory_int_int_2_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testFactory_int_int_3_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        assertEquals(0, f.getNumerator());
    }

    @Test
    void testFactory_int_int_4_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testFactory_int_int_5_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        assertEquals(1, f.getNumerator());
    }

    @Test
    void testFactory_int_int_6_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testFactory_int_int_7_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        assertEquals(2, f.getNumerator());
    }

    @Test
    void testFactory_int_int_8_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testFactory_int_int_9_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        assertEquals(1, f.getNumerator());
    }

    @Test
    void testFactory_int_int_10_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        assertEquals(15, f.getDenominator());
    }

    @Test
    void testFactory_int_int_11_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        assertEquals(22, f.getNumerator());
    }

    @Test
    void testFactory_int_int_12_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        assertEquals(7, f.getDenominator());
    }

    @Test
    void testFactory_int_int_13_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        assertEquals(-3, f.getNumerator());
    }

    @Test
    void testFactory_int_int_14_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        assertEquals(5, f.getDenominator());
    }

    @Test
    void testFactory_int_int_15_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        assertEquals(3, f.getNumerator());
    }

    @Test
    void testFactory_int_int_16_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        assertEquals(-5, f.getDenominator());
    }

    @Test
    void testFactory_int_int_17_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        assertEquals(-3, f.getNumerator());
    }

    @Test
    void testFactory_int_int_18_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        assertEquals(-5, f.getDenominator());
    }

    @Test
    void testFactory_int_int_19_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        try {
    Fraction.of(1, 0);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testFactory_int_int_20_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        try {
    Fraction.of(2, 0);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testFactory_int_int_21_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        try {
    Fraction.of(-3, 0);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testFactory_int_int_22_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // lang cannot represent the unsimplified fraction with MIN_VALUE as the denominator
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(4, Integer.MIN_VALUE));
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(1, Integer.MIN_VALUE));
        // numbers will always simplify the fraction
        f = Fraction.of(4, Integer.MIN_VALUE);
        assertEquals(-1, f.signum());
    }

    @Test
    void testFactory_int_int_23_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // lang cannot represent the unsimplified fraction with MIN_VALUE as the denominator
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(4, Integer.MIN_VALUE));
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(1, Integer.MIN_VALUE));
        // numbers will always simplify the fraction
        f = Fraction.of(4, Integer.MIN_VALUE);
        // removed other assertion
        assertEquals(1, f.getNumerator());
    }

    @Test
    void testFactory_int_int_24_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // lang cannot represent the unsimplified fraction with MIN_VALUE as the denominator
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(4, Integer.MIN_VALUE));
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(1, Integer.MIN_VALUE));
        // numbers will always simplify the fraction
        f = Fraction.of(4, Integer.MIN_VALUE);
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MIN_VALUE / 4, f.getDenominator());
    }

    @Test
    void testFactory_int_int_25_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // lang cannot represent the unsimplified fraction with MIN_VALUE as the denominator
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(4, Integer.MIN_VALUE));
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(1, Integer.MIN_VALUE));
        // numbers will always simplify the fraction
        f = Fraction.of(4, Integer.MIN_VALUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // numbers can use MIN_VALUE as the denominator
        f = Fraction.of(1, Integer.MIN_VALUE);
        assertEquals(-1, f.signum());
    }

    @Test
    void testFactory_int_int_26_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // lang cannot represent the unsimplified fraction with MIN_VALUE as the denominator
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(4, Integer.MIN_VALUE));
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(1, Integer.MIN_VALUE));
        // numbers will always simplify the fraction
        f = Fraction.of(4, Integer.MIN_VALUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // numbers can use MIN_VALUE as the denominator
        f = Fraction.of(1, Integer.MIN_VALUE);
        // removed other assertion
        assertEquals(1, f.getNumerator());
    }

    @Test
    void testFactory_int_int_27_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // lang cannot represent the unsimplified fraction with MIN_VALUE as the denominator
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(4, Integer.MIN_VALUE));
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(1, Integer.MIN_VALUE));
        // numbers will always simplify the fraction
        f = Fraction.of(4, Integer.MIN_VALUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // numbers can use MIN_VALUE as the denominator
        f = Fraction.of(1, Integer.MIN_VALUE);
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MIN_VALUE, f.getDenominator());
    }

    @Test
    void testReducedFactory_int_int_1_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        assertEquals(0, f.getNumerator());
    }

    @Test
    void testReducedFactory_int_int_2_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testReducedFactory_int_int_3_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        assertEquals(1, f.getNumerator());
    }

    @Test
    void testReducedFactory_int_int_4_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testReducedFactory_int_int_5_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        assertEquals(2, f.getNumerator());
    }

    @Test
    void testReducedFactory_int_int_6_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testReducedFactory_int_int_7_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        assertEquals(22, f.getNumerator());
    }

    @Test
    void testReducedFactory_int_int_8_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        assertEquals(7, f.getDenominator());
    }

    @Test
    void testReducedFactory_int_int_9_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        assertEquals(-3, f.getNumerator());
    }

    @Test
    void testReducedFactory_int_int_10_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        assertEquals(5, f.getDenominator());
    }

    @Test
    void testReducedFactory_int_int_11_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        assertEquals(3, f.getNumerator());
    }

    @Test
    void testReducedFactory_int_int_12_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        assertEquals(-5, f.getDenominator());
    }

    @Test
    void testReducedFactory_int_int_13_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        assertEquals(-3, f.getNumerator());
    }

    @Test
    void testReducedFactory_int_int_14_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        assertEquals(-5, f.getDenominator());
    }

    @Test
    void testReducedFactory_int_int_15_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        try {
    Fraction.of(1, 0);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testReducedFactory_int_int_16_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        try {
    Fraction.of(2, 0);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testReducedFactory_int_int_17_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        try {
    Fraction.of(-3, 0);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testReducedFactory_int_int_18_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.of(0, 2);
        assertEquals(0, f.getNumerator());
    }

    @Test
    void testReducedFactory_int_int_19_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.of(0, 2);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testReducedFactory_int_int_20_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 2);
        assertEquals(1, f.getNumerator());
    }

    @Test
    void testReducedFactory_int_int_21_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 2);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testReducedFactory_int_int_22_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 4);
        assertEquals(1, f.getNumerator());
    }

    @Test
    void testReducedFactory_int_int_23_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 4);
        // removed other assertion
        assertEquals(2, f.getDenominator());
    }

    @Test
    void testReducedFactory_int_int_24_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 4);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(15, 10);
        assertEquals(3, f.getNumerator());
    }

    @Test
    void testReducedFactory_int_int_25_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 4);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(15, 10);
        // removed other assertion
        assertEquals(2, f.getDenominator());
    }

    @Test
    void testReducedFactory_int_int_26_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 4);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(15, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(121, 22);
        assertEquals(11, f.getNumerator());
    }

    @Test
    void testReducedFactory_int_int_27_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 4);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(15, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(121, 22);
        // removed other assertion
        assertEquals(2, f.getDenominator());
    }

    @Test
    void testReducedFactory_int_int_28_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 4);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(15, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(121, 22);
        // removed other assertion
        // removed other assertion

        // Extreme values
        // OK, can reduce before negating
        f = Fraction.of(-2, Integer.MIN_VALUE);
        assertEquals(-1, f.getNumerator());
    }

    @Test
    void testReducedFactory_int_int_29_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 4);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(15, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(121, 22);
        // removed other assertion
        // removed other assertion

        // Extreme values
        // OK, can reduce before negating
        f = Fraction.of(-2, Integer.MIN_VALUE);
        // removed other assertion
        assertEquals(Integer.MIN_VALUE / 2, f.getDenominator());
    }

    @Test
    void testReducedFactory_int_int_30_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 4);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(15, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(121, 22);
        // removed other assertion
        // removed other assertion

        // Extreme values
        // OK, can reduce before negating
        f = Fraction.of(-2, Integer.MIN_VALUE);
        // removed other assertion
        // removed other assertion

        // lang requires the sign to be in the numerator so this would throw.
        // assertThrows(ArithmeticException.class, () -> Fraction.getReducedFraction(-7, Integer.MIN_VALUE));
        // numbers allows the sign to be in the denominator so this does not throw.
        f = Fraction.of(-7, Integer.MIN_VALUE);
        assertEquals(1, f.signum());
    }

    @Test
    void testReducedFactory_int_int_31_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 4);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(15, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(121, 22);
        // removed other assertion
        // removed other assertion

        // Extreme values
        // OK, can reduce before negating
        f = Fraction.of(-2, Integer.MIN_VALUE);
        // removed other assertion
        // removed other assertion

        // lang requires the sign to be in the numerator so this would throw.
        // assertThrows(ArithmeticException.class, () -> Fraction.getReducedFraction(-7, Integer.MIN_VALUE));
        // numbers allows the sign to be in the denominator so this does not throw.
        f = Fraction.of(-7, Integer.MIN_VALUE);
        // removed other assertion
        assertEquals(-7, f.getNumerator());
    }

    @Test
    void testReducedFactory_int_int_32_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 4);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(15, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(121, 22);
        // removed other assertion
        // removed other assertion

        // Extreme values
        // OK, can reduce before negating
        f = Fraction.of(-2, Integer.MIN_VALUE);
        // removed other assertion
        // removed other assertion

        // lang requires the sign to be in the numerator so this would throw.
        // assertThrows(ArithmeticException.class, () -> Fraction.getReducedFraction(-7, Integer.MIN_VALUE));
        // numbers allows the sign to be in the denominator so this does not throw.
        f = Fraction.of(-7, Integer.MIN_VALUE);
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.MIN_VALUE, f.getDenominator());
    }

    @Test
    void testReducedFactory_int_int_33_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 4);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(15, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(121, 22);
        // removed other assertion
        // removed other assertion

        // Extreme values
        // OK, can reduce before negating
        f = Fraction.of(-2, Integer.MIN_VALUE);
        // removed other assertion
        // removed other assertion

        // lang requires the sign to be in the numerator so this would throw.
        // assertThrows(ArithmeticException.class, () -> Fraction.getReducedFraction(-7, Integer.MIN_VALUE));
        // numbers allows the sign to be in the denominator so this does not throw.
        f = Fraction.of(-7, Integer.MIN_VALUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-662
        f = Fraction.of(Integer.MIN_VALUE, 2);
        assertEquals(Integer.MIN_VALUE / 2, f.getNumerator());
    }

    @Test
    void testReducedFactory_int_int_34_oe() {
        Fraction f = null;

        // zero
        f = Fraction.of(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.of(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.of(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.of(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.of(0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(2, 4);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(15, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(121, 22);
        // removed other assertion
        // removed other assertion

        // Extreme values
        // OK, can reduce before negating
        f = Fraction.of(-2, Integer.MIN_VALUE);
        // removed other assertion
        // removed other assertion

        // lang requires the sign to be in the numerator so this would throw.
        // assertThrows(ArithmeticException.class, () -> Fraction.getReducedFraction(-7, Integer.MIN_VALUE));
        // numbers allows the sign to be in the denominator so this does not throw.
        f = Fraction.of(-7, Integer.MIN_VALUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-662
        f = Fraction.of(Integer.MIN_VALUE, 2);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testFactory_double_1_oe() {
        try {
    Fraction.from(Double.NaN);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFactory_double_2_oe() {
        // removed other assertion
        try {
    Fraction.from(Double.POSITIVE_INFINITY);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFactory_double_3_oe() {
        // removed other assertion
        // removed other assertion
        try {
    Fraction.from(Double.NEGATIVE_INFINITY);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFactory_double_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        assertEquals(0, f.getNumerator());
    }

    @Test
    void testFactory_double_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testFactory_double_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.from(1.0d);
        assertEquals(1, f.getNumerator());
    }

    @Test
    void testFactory_double_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.from(1.0d);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testFactory_double_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.from(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.from(0.5d);
        assertEquals(1, f.getNumerator());
    }

    @Test
    void testFactory_double_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.from(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.from(0.5d);
        // removed other assertion
        assertEquals(2, f.getDenominator());
    }

    @Test
    void testFactory_double_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.from(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.from(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.from(-0.875d);
        assertEquals(-7, f.getNumerator());
    }

    @Test
    void testFactory_double_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.from(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.from(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.from(-0.875d);
        // removed other assertion
        assertEquals(8, f.getDenominator());
    }

    @Test
    void testFactory_double_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.from(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.from(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.from(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.from(1.25d);
        assertEquals(5, f.getNumerator());
    }

    @Test
    void testFactory_double_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.from(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.from(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.from(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.from(1.25d);
        // removed other assertion
        assertEquals(4, f.getDenominator());
    }

    @Test
    void testFactory_double_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.from(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.from(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.from(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.from(1.25d);
        // removed other assertion
        // removed other assertion

        // two thirds
        f = Fraction.from(0.66666d);
        assertEquals(2, f.getNumerator());
    }

    @Test
    void testFactory_double_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.from(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.from(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.from(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.from(1.25d);
        // removed other assertion
        // removed other assertion

        // two thirds
        f = Fraction.from(0.66666d);
        // removed other assertion
        assertEquals(3, f.getDenominator());
    }

    @Test
    void testFactory_double_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.from(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.from(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.from(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.from(1.25d);
        // removed other assertion
        // removed other assertion

        // two thirds
        f = Fraction.from(0.66666d);
        // removed other assertion
        // removed other assertion

        // small
        f = Fraction.from(1.0d / 10001d);
        assertEquals(1, f.getNumerator());
    }

    @Test
    void testFactory_double_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.from(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.from(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.from(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.from(1.25d);
        // removed other assertion
        // removed other assertion

        // two thirds
        f = Fraction.from(0.66666d);
        // removed other assertion
        // removed other assertion

        // small
        f = Fraction.from(1.0d / 10001d);
        // removed other assertion
        assertEquals(10001, f.getDenominator());
    }

    @Test
    void testFactory_double_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.from(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.from(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.from(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.from(1.25d);
        // removed other assertion
        // removed other assertion

        // two thirds
        f = Fraction.from(0.66666d);
        // removed other assertion
        // removed other assertion

        // small
        f = Fraction.from(1.0d / 10001d);
        // removed other assertion
        // removed other assertion

        // normal
        Fraction f2 = null;
        for (int i = 1; i <= 100; i++) {  // denominator
            for (int j = 1; j <= i; j++) {  // numerator
                f = Fraction.from((double) j / (double) i);

                f2 = Fraction.of(j, i);
                assertEquals(f2.getNumerator(), f.getNumerator());
    }
    }
    }

    @Test
    void testFactory_double_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.from(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.from(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.from(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.from(1.25d);
        // removed other assertion
        // removed other assertion

        // two thirds
        f = Fraction.from(0.66666d);
        // removed other assertion
        // removed other assertion

        // small
        f = Fraction.from(1.0d / 10001d);
        // removed other assertion
        // removed other assertion

        // normal
        Fraction f2 = null;
        for (int i = 1; i <= 100; i++) {  // denominator
            for (int j = 1; j <= i; j++) {  // numerator
                f = Fraction.from((double) j / (double) i);

                f2 = Fraction.of(j, i);
                // removed other assertion
                assertEquals(f2.getDenominator(), f.getDenominator());
    }
    }
    }

    @Test
    void testFactory_double_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.from(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.from(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.from(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.from(1.25d);
        // removed other assertion
        // removed other assertion

        // two thirds
        f = Fraction.from(0.66666d);
        // removed other assertion
        // removed other assertion

        // small
        f = Fraction.from(1.0d / 10001d);
        // removed other assertion
        // removed other assertion

        // normal
        Fraction f2 = null;
        for (int i = 1; i <= 100; i++) {  // denominator
            for (int j = 1; j <= i; j++) {  // numerator
                f = Fraction.from((double) j / (double) i);

                f2 = Fraction.of(j, i);
                // removed other assertion
                // removed other assertion
            }
        }
        // save time by skipping some tests!  (
        for (int i = 1001; i <= 10000; i += SKIP) {  // denominator
            for (int j = 1; j <= i; j++) {  // numerator
                f = Fraction.from((double) j / (double) i, 1e-8, 100);
                f2 = Fraction.of(j, i);
                assertEquals(f2.getNumerator(), f.getNumerator());
    }
    }
    }

    @Test
    void testFactory_double_21_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Fraction.from((double) Integer.MAX_VALUE + 1);

        // zero
        Fraction f = Fraction.from(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.from(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.from(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.from(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.from(1.25d);
        // removed other assertion
        // removed other assertion

        // two thirds
        f = Fraction.from(0.66666d);
        // removed other assertion
        // removed other assertion

        // small
        f = Fraction.from(1.0d / 10001d);
        // removed other assertion
        // removed other assertion

        // normal
        Fraction f2 = null;
        for (int i = 1; i <= 100; i++) {  // denominator
            for (int j = 1; j <= i; j++) {  // numerator
                f = Fraction.from((double) j / (double) i);

                f2 = Fraction.of(j, i);
                // removed other assertion
                // removed other assertion
            }
        }
        // save time by skipping some tests!  (
        for (int i = 1001; i <= 10000; i += SKIP) {  // denominator
            for (int j = 1; j <= i; j++) {  // numerator
                f = Fraction.from((double) j / (double) i, 1e-8, 100);
                f2 = Fraction.of(j, i);
                // removed other assertion
                assertEquals(f2.getDenominator(), f.getDenominator());
    }
    }
    }

    @Test
    void testNegate_1_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.negate();
        assertEquals(-2, f.getNumerator());
    }

    @Test
    void testNegate_2_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.negate();
        // removed other assertion
        assertEquals(3, f.getDenominator());
    }

    @Test
    void testNegate_3_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-50, 75);
        f = f.negate();
        assertEquals(2, f.getNumerator());
    }

    @Test
    void testNegate_4_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-50, 75);
        f = f.negate();
        // removed other assertion
        assertEquals(3, f.getDenominator());
    }

    @Test
    void testNegate_5_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-50, 75);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        // large values
        f = Fraction.of(Integer.MAX_VALUE - 1, Integer.MAX_VALUE);
        f = f.negate();
        assertEquals(Integer.MIN_VALUE + 2, f.getNumerator());
    }

    @Test
    void testNegate_6_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-50, 75);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        // large values
        f = Fraction.of(Integer.MAX_VALUE - 1, Integer.MAX_VALUE);
        f = f.negate();
        // removed other assertion
        assertEquals(Integer.MAX_VALUE, f.getDenominator());
    }

    @Test
    void testNegate_7_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-50, 75);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        // large values
        f = Fraction.of(Integer.MAX_VALUE - 1, Integer.MAX_VALUE);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        // lang requires the sign in the numerator and so cannot negate MIN_VALUE as the numerator
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(Integer.MIN_VALUE, 1).negate());
        // numbers allows the sign in the numerator or denominator
        f = Fraction.of(Integer.MIN_VALUE, 1).negate();
        assertEquals(1, f.signum());
    }

    @Test
    void testNegate_8_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-50, 75);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        // large values
        f = Fraction.of(Integer.MAX_VALUE - 1, Integer.MAX_VALUE);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        // lang requires the sign in the numerator and so cannot negate MIN_VALUE as the numerator
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(Integer.MIN_VALUE, 1).negate());
        // numbers allows the sign in the numerator or denominator
        f = Fraction.of(Integer.MIN_VALUE, 1).negate();
        // removed other assertion
        assertEquals(Integer.MIN_VALUE, f.getNumerator());
    }

    @Test
    void testNegate_9_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-50, 75);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        // large values
        f = Fraction.of(Integer.MAX_VALUE - 1, Integer.MAX_VALUE);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        // lang requires the sign in the numerator and so cannot negate MIN_VALUE as the numerator
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(Integer.MIN_VALUE, 1).negate());
        // numbers allows the sign in the numerator or denominator
        f = Fraction.of(Integer.MIN_VALUE, 1).negate();
        // removed other assertion
        // removed other assertion
        assertEquals(-1, f.getDenominator());
    }

    @Test
    void testAbs_1_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.abs();
        assertEquals(2, f.getNumerator());
    }

    @Test
    void testAbs_2_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.abs();
        // removed other assertion
        assertEquals(3, f.getDenominator());
    }

    @Test
    void testAbs_3_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-50, 75);
        f = f.abs();
        assertEquals(2, f.getNumerator());
    }

    @Test
    void testAbs_4_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-50, 75);
        f = f.abs();
        // removed other assertion
        assertEquals(3, f.getDenominator());
    }

    @Test
    void testAbs_5_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(Integer.MAX_VALUE, 1);
        f = f.abs();
        assertEquals(Integer.MAX_VALUE, f.getNumerator());
    }

    @Test
    void testAbs_6_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(Integer.MAX_VALUE, 1);
        f = f.abs();
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testAbs_7_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(Integer.MAX_VALUE, 1);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(Integer.MAX_VALUE, -1);
        f = f.abs();
        assertEquals(-Integer.MAX_VALUE, f.getNumerator());
    }

    @Test
    void testAbs_8_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(Integer.MAX_VALUE, 1);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(Integer.MAX_VALUE, -1);
        f = f.abs();
        // removed other assertion
        assertEquals(-1, f.getDenominator());
    }

    @Test
    void testAbs_9_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(Integer.MAX_VALUE, 1);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(Integer.MAX_VALUE, -1);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        // lang requires the sign in the numerator and so cannot compute the absolute with MIN_VALUE as the numerator
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(Integer.MIN_VALUE, 1).abs());
        // numbers allows the sign in the numerator or denominator
        f = Fraction.of(Integer.MIN_VALUE, 1).abs();
        assertEquals(1, f.signum());
    }

    @Test
    void testAbs_10_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(Integer.MAX_VALUE, 1);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(Integer.MAX_VALUE, -1);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        // lang requires the sign in the numerator and so cannot compute the absolute with MIN_VALUE as the numerator
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(Integer.MIN_VALUE, 1).abs());
        // numbers allows the sign in the numerator or denominator
        f = Fraction.of(Integer.MIN_VALUE, 1).abs();
        // removed other assertion
        assertEquals(Integer.MIN_VALUE, f.getNumerator());
    }

    @Test
    void testAbs_11_oe() {
        Fraction f = null;

        f = Fraction.of(50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(-50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(Integer.MAX_VALUE, 1);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(Integer.MAX_VALUE, -1);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        // lang requires the sign in the numerator and so cannot compute the absolute with MIN_VALUE as the numerator
        // assertThrows(ArithmeticException.class, () -> Fraction.getFraction(Integer.MIN_VALUE, 1).abs());
        // numbers allows the sign in the numerator or denominator
        f = Fraction.of(Integer.MIN_VALUE, 1).abs();
        // removed other assertion
        // removed other assertion
        assertEquals(-1, f.getDenominator());
    }

    @Test
    void testPow_1_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        assertEquals(Fraction.ONE, f.pow(0));
    }

    @Test
    void testPow_2_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        assertEquals(f, f.pow(1));
    }

    @Test
    void testPow_3_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        assertEquals(f, f.pow(1));
    }

    @Test
    void testPow_4_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        assertEquals(9, f.getNumerator());
    }

    @Test
    void testPow_5_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        assertEquals(25, f.getDenominator());
    }

    @Test
    void testPow_6_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        assertEquals(27, f.getNumerator());
    }

    @Test
    void testPow_7_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        assertEquals(125, f.getDenominator());
    }

    @Test
    void testPow_8_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        assertEquals(5, f.getNumerator());
    }

    @Test
    void testPow_9_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        assertEquals(3, f.getDenominator());
    }

    @Test
    void testPow_10_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        assertEquals(25, f.getNumerator());
    }

    @Test
    void testPow_11_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        assertEquals(9, f.getDenominator());
    }

    @Test
    void testPow_12_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        assertEquals(Fraction.ONE, f.pow(0));
    }

    @Test
    void testPow_13_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        assertEquals(f, f.pow(1));
    }

    @Test
    void testPow_14_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        assertEquals(f.pow(1), Fraction.of(3, 5));
    }

    @Test
    void testPow_15_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        assertEquals(9, f.getNumerator());
    }

    @Test
    void testPow_16_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        assertEquals(25, f.getDenominator());
    }

    @Test
    void testPow_17_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        assertEquals(27, f.getNumerator());
    }

    @Test
    void testPow_18_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        assertEquals(125, f.getDenominator());
    }

    @Test
    void testPow_19_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-1);
        assertEquals(5, f.getNumerator());
    }

    @Test
    void testPow_20_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-1);
        // removed other assertion
        assertEquals(3, f.getDenominator());
    }

    @Test
    void testPow_21_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-2);
        assertEquals(25, f.getNumerator());
    }

    @Test
    void testPow_22_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-2);
        // removed other assertion
        assertEquals(9, f.getDenominator());
    }

    @Test
    void testPow_23_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.of(0, 1231);
        f = f.pow(1);
        assertEquals(0, f.compareTo(Fraction.ZERO));
    }

    @Test
    void testPow_24_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.of(0, 1231);
        f = f.pow(1);
        // removed other assertion
        assertEquals(0, f.getNumerator());
    }

    @Test
    void testPow_25_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.of(0, 1231);
        f = f.pow(1);
        // removed other assertion
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testPow_26_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.of(0, 1231);
        f = f.pow(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        f = f.pow(2);
        assertEquals(0, f.compareTo(Fraction.ZERO));
    }

    @Test
    void testPow_27_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.of(0, 1231);
        f = f.pow(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        f = f.pow(2);
        // removed other assertion
        assertEquals(0, f.getNumerator());
    }

    @Test
    void testPow_28_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.of(0, 1231);
        f = f.pow(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        f = f.pow(2);
        // removed other assertion
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testPow_29_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.of(0, 1231);
        f = f.pow(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        f = f.pow(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero to negative powers should throw an exception
        final Fraction fr = f;
        try {
    fr.pow(-1);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testPow_30_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.of(0, 1231);
        f = f.pow(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        f = f.pow(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero to negative powers should throw an exception
        final Fraction fr = f;
        // removed other assertion
        try {
    fr.pow(Integer.MIN_VALUE);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testPow_31_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.of(0, 1231);
        f = f.pow(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        f = f.pow(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero to negative powers should throw an exception
        final Fraction fr = f;
        // removed other assertion
        // removed other assertion

        // one to any power is still one.
        f = Fraction.of(1, 1);
        f = f.pow(0);
        assertEquals(Fraction.ONE, f);
    }

    @Test
    void testPow_32_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.of(0, 1231);
        f = f.pow(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        f = f.pow(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero to negative powers should throw an exception
        final Fraction fr = f;
        // removed other assertion
        // removed other assertion

        // one to any power is still one.
        f = Fraction.of(1, 1);
        f = f.pow(0);
        // removed other assertion
        f = f.pow(1);
        assertEquals(Fraction.ONE, f);
    }

    @Test
    void testPow_33_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.of(0, 1231);
        f = f.pow(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        f = f.pow(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero to negative powers should throw an exception
        final Fraction fr = f;
        // removed other assertion
        // removed other assertion

        // one to any power is still one.
        f = Fraction.of(1, 1);
        f = f.pow(0);
        // removed other assertion
        f = f.pow(1);
        // removed other assertion
        f = f.pow(-1);
        assertEquals(Fraction.ONE, f);
    }

    @Test
    void testPow_34_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.of(0, 1231);
        f = f.pow(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        f = f.pow(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero to negative powers should throw an exception
        final Fraction fr = f;
        // removed other assertion
        // removed other assertion

        // one to any power is still one.
        f = Fraction.of(1, 1);
        f = f.pow(0);
        // removed other assertion
        f = f.pow(1);
        // removed other assertion
        f = f.pow(-1);
        // removed other assertion
        f = f.pow(Integer.MAX_VALUE);
        assertEquals(Fraction.ONE, f);
    }

    @Test
    void testPow_35_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        // removed other assertion

        f = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.of(6, 10);
        // removed other assertion

        f = Fraction.of(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.of(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.of(0, 1231);
        f = f.pow(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        f = f.pow(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero to negative powers should throw an exception
        final Fraction fr = f;
        // removed other assertion
        // removed other assertion

        // one to any power is still one.
        f = Fraction.of(1, 1);
        f = f.pow(0);
        // removed other assertion
        f = f.pow(1);
        // removed other assertion
        f = f.pow(-1);
        // removed other assertion
        f = f.pow(Integer.MAX_VALUE);
        // removed other assertion
        f = f.pow(Integer.MIN_VALUE);
        assertEquals(Fraction.ONE, f);
    }

    @Test
    void testAdd_1_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        assertEquals(4, f.getNumerator());
    }

    @Test
    void testAdd_2_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        assertEquals(5, f.getDenominator());
    }

    @Test
    void testAdd_3_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        assertEquals(1, f.getNumerator());
    }

    @Test
    void testAdd_4_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testAdd_5_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        assertEquals(6, f.getNumerator());
    }

    @Test
    void testAdd_6_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        assertEquals(5, f.getDenominator());
    }

    @Test
    void testAdd_7_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        assertEquals(-1, f.getNumerator());
    }

    @Test
    void testAdd_8_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        assertEquals(5, f.getDenominator());
    }

    @Test
    void testAdd_9_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        assertEquals(Integer.MAX_VALUE, f.getNumerator());
    }

    @Test
    void testAdd_10_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testAdd_11_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        assertEquals(11, f.getNumerator());
    }

    @Test
    void testAdd_12_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        assertEquals(10, f.getDenominator());
    }

    @Test
    void testAdd_13_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 8);
        f2 = Fraction.of(1, 6);
        f = f1.add(f2);
        assertEquals(13, f.getNumerator());
    }

    @Test
    void testAdd_14_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 8);
        f2 = Fraction.of(1, 6);
        f = f1.add(f2);
        // removed other assertion
        assertEquals(24, f.getDenominator());
    }

    @Test
    void testAdd_15_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 8);
        f2 = Fraction.of(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        assertSame(f2, f);
    }

    @Test
    void testAdd_16_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 8);
        f2 = Fraction.of(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        assertSame(f2, f);
    }

    @Test
    void testAdd_17_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 8);
        f2 = Fraction.of(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.of(-1, 13 * 13 * 2 * 2);
        f2 = Fraction.of(-2, 13 * 17 * 2);
        final Fraction fr = f1.add(f2);
        assertEquals(13 * 13 * 17 * 2 * 2, fr.getDenominator());
    }

    @Test
    void testAdd_18_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 8);
        f2 = Fraction.of(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.of(-1, 13 * 13 * 2 * 2);
        f2 = Fraction.of(-2, 13 * 17 * 2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        assertEquals(-17 - 2 * 13 * 2, fr.getNumerator());
    }

    @Test
    void testAdd_19_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 8);
        f2 = Fraction.of(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.of(-1, 13 * 13 * 2 * 2);
        f2 = Fraction.of(-2, 13 * 17 * 2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        // removed other assertion

        try {
    fr.add(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testAdd_20_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 8);
        f2 = Fraction.of(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.of(-1, 13 * 13 * 2 * 2);
        f2 = Fraction.of(-2, 13 * 17 * 2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // if this fraction is added naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.of(1, 32768 * 3);
        f2 = Fraction.of(1, 59049);
        f = f1.add(f2);
        assertEquals(52451, f.getNumerator());
    }

    @Test
    void testAdd_21_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 8);
        f2 = Fraction.of(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.of(-1, 13 * 13 * 2 * 2);
        f2 = Fraction.of(-2, 13 * 17 * 2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // if this fraction is added naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.of(1, 32768 * 3);
        f2 = Fraction.of(1, 59049);
        f = f1.add(f2);
        // removed other assertion
        assertEquals(1934917632, f.getDenominator());
    }

    @Test
    void testAdd_22_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 8);
        f2 = Fraction.of(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.of(-1, 13 * 13 * 2 * 2);
        f2 = Fraction.of(-2, 13 * 17 * 2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // if this fraction is added naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.of(1, 32768 * 3);
        f2 = Fraction.of(1, 59049);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MIN_VALUE, 3);
        f2 = Fraction.of(1, 3);
        f = f1.add(f2);
        assertEquals(Integer.MIN_VALUE + 1, f.getNumerator());
    }

    @Test
    void testAdd_23_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 8);
        f2 = Fraction.of(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.of(-1, 13 * 13 * 2 * 2);
        f2 = Fraction.of(-2, 13 * 17 * 2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // if this fraction is added naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.of(1, 32768 * 3);
        f2 = Fraction.of(1, 59049);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MIN_VALUE, 3);
        f2 = Fraction.of(1, 3);
        f = f1.add(f2);
        // removed other assertion
        assertEquals(3, f.getDenominator());
    }

    @Test
    void testAdd_24_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 8);
        f2 = Fraction.of(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.of(-1, 13 * 13 * 2 * 2);
        f2 = Fraction.of(-2, 13 * 17 * 2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // if this fraction is added naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.of(1, 32768 * 3);
        f2 = Fraction.of(1, 59049);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MIN_VALUE, 3);
        f2 = Fraction.of(1, 3);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        assertEquals(Integer.MAX_VALUE, f.getNumerator());
    }

    @Test
    void testAdd_25_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 8);
        f2 = Fraction.of(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.of(-1, 13 * 13 * 2 * 2);
        f2 = Fraction.of(-2, 13 * 17 * 2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // if this fraction is added naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.of(1, 32768 * 3);
        f2 = Fraction.of(1, 59049);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MIN_VALUE, 3);
        f2 = Fraction.of(1, 3);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testAdd_26_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 8);
        f2 = Fraction.of(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.of(-1, 13 * 13 * 2 * 2);
        f2 = Fraction.of(-2, 13 * 17 * 2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // if this fraction is added naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.of(1, 32768 * 3);
        f2 = Fraction.of(1, 59049);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MIN_VALUE, 3);
        f2 = Fraction.of(1, 3);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        final Fraction overflower = f;
        try {
    overflower.add(Fraction.ONE);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
} // should overflow;
    }

    @Test
    void testAdd_28_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 8);
        f2 = Fraction.of(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.of(-1, 13 * 13 * 2 * 2);
        f2 = Fraction.of(-2, 13 * 17 * 2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // if this fraction is added naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.of(1, 32768 * 3);
        f2 = Fraction.of(1, 59049);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MIN_VALUE, 3);
        f2 = Fraction.of(1, 3);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        final Fraction overflower = f;
        // removed other assertion

        // denominator should not be a multiple of 2 or 3 to trigger overflow
        // removed other assertion

        final Fraction maxValue = Fraction.of(-Integer.MAX_VALUE, 1);
        try {
    maxValue.add(maxValue);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testAdd_29_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 8);
        f2 = Fraction.of(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.of(-1, 13 * 13 * 2 * 2);
        f2 = Fraction.of(-2, 13 * 17 * 2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // if this fraction is added naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.of(1, 32768 * 3);
        f2 = Fraction.of(1, 59049);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MIN_VALUE, 3);
        f2 = Fraction.of(1, 3);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        final Fraction overflower = f;
        // removed other assertion

        // denominator should not be a multiple of 2 or 3 to trigger overflow
        // removed other assertion

        final Fraction maxValue = Fraction.of(-Integer.MAX_VALUE, 1);
        // removed other assertion

        final Fraction negativeMaxValue = Fraction.of(-Integer.MAX_VALUE, 1);
        try {
    negativeMaxValue.add(negativeMaxValue);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testAdd_30_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 8);
        f2 = Fraction.of(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.of(-1, 13 * 13 * 2 * 2);
        f2 = Fraction.of(-2, 13 * 17 * 2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // if this fraction is added naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.of(1, 32768 * 3);
        f2 = Fraction.of(1, 59049);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MIN_VALUE, 3);
        f2 = Fraction.of(1, 3);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        final Fraction overflower = f;
        // removed other assertion

        // denominator should not be a multiple of 2 or 3 to trigger overflow
        // removed other assertion

        final Fraction maxValue = Fraction.of(-Integer.MAX_VALUE, 1);
        // removed other assertion

        final Fraction negativeMaxValue = Fraction.of(-Integer.MAX_VALUE, 1);
        // removed other assertion

        final Fraction f3 = Fraction.of(3, 327680);
        final Fraction f4 = Fraction.of(2, 59049);
        try {
    f3.add(f4);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
} // should overflow;
    }

    @Test
    void testSubtract_1_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        assertEquals(2, f.getNumerator());
    }

    @Test
    void testSubtract_2_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        assertEquals(5, f.getDenominator());
    }

    @Test
    void testSubtract_3_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        assertEquals(1, f.getNumerator());
    }

    @Test
    void testSubtract_4_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testSubtract_5_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        assertEquals(0, f.getNumerator());
    }

    @Test
    void testSubtract_6_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testSubtract_7_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        assertEquals(7, f.getNumerator());
    }

    @Test
    void testSubtract_8_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        assertEquals(5, f.getDenominator());
    }

    @Test
    void testSubtract_9_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(4, 5);
        f = f1.subtract(f2);
        assertEquals(-4, f.getNumerator());
    }

    @Test
    void testSubtract_10_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        assertEquals(5, f.getDenominator());
    }

    @Test
    void testSubtract_11_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        assertEquals(4, f.getNumerator());
    }

    @Test
    void testSubtract_12_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        assertEquals(5, f.getDenominator());
    }

    @Test
    void testSubtract_13_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.subtract(f2);
        assertEquals(1, f.getNumerator());
    }

    @Test
    void testSubtract_14_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.subtract(f2);
        // removed other assertion
        assertEquals(10, f.getDenominator());
    }

    @Test
    void testSubtract_15_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f2.subtract(f1);
        assertSame(f2, f);
    }

    @Test
    void testSubtract_16_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f2.subtract(f1);
        // removed other assertion

        final Fraction fr = f;
        try {
    fr.subtract(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testSubtract_17_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f2.subtract(f1);
        // removed other assertion

        final Fraction fr = f;
        // removed other assertion

        // if this fraction is subtracted naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.of(1, 32768 * 3);
        f2 = Fraction.of(1, 59049);
        f = f1.subtract(f2);
        assertEquals(-13085, f.getNumerator());
    }

    @Test
    void testSubtract_18_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f2.subtract(f1);
        // removed other assertion

        final Fraction fr = f;
        // removed other assertion

        // if this fraction is subtracted naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.of(1, 32768 * 3);
        f2 = Fraction.of(1, 59049);
        f = f1.subtract(f2);
        // removed other assertion
        assertEquals(1934917632, f.getDenominator());
    }

    @Test
    void testSubtract_19_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f2.subtract(f1);
        // removed other assertion

        final Fraction fr = f;
        // removed other assertion

        // if this fraction is subtracted naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.of(1, 32768 * 3);
        f2 = Fraction.of(1, 59049);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MIN_VALUE, 3);
        f2 = Fraction.of(1, 3).negate();
        f = f1.subtract(f2);
        assertEquals(Integer.MIN_VALUE + 1, f.getNumerator());
    }

    @Test
    void testSubtract_20_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f2.subtract(f1);
        // removed other assertion

        final Fraction fr = f;
        // removed other assertion

        // if this fraction is subtracted naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.of(1, 32768 * 3);
        f2 = Fraction.of(1, 59049);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MIN_VALUE, 3);
        f2 = Fraction.of(1, 3).negate();
        f = f1.subtract(f2);
        // removed other assertion
        assertEquals(3, f.getDenominator());
    }

    @Test
    void testSubtract_21_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f2.subtract(f1);
        // removed other assertion

        final Fraction fr = f;
        // removed other assertion

        // if this fraction is subtracted naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.of(1, 32768 * 3);
        f2 = Fraction.of(1, 59049);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MIN_VALUE, 3);
        f2 = Fraction.of(1, 3).negate();
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE, 1);
        f2 = Fraction.ONE;
        f = f1.subtract(f2);
        assertEquals(Integer.MAX_VALUE - 1, f.getNumerator());
    }

    @Test
    void testSubtract_22_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(7, 5);
        f2 = Fraction.of(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(1, 2);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(1, 5);
        f = f2.subtract(f1);
        // removed other assertion

        final Fraction fr = f;
        // removed other assertion

        // if this fraction is subtracted naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.of(1, 32768 * 3);
        f2 = Fraction.of(1, 59049);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MIN_VALUE, 3);
        f2 = Fraction.of(1, 3).negate();
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE, 1);
        f2 = Fraction.ONE;
        f = f1.subtract(f2);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testMultiply_1_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        assertEquals(6, f.getNumerator());
    }

    @Test
    void testMultiply_2_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        assertEquals(25, f.getDenominator());
    }

    @Test
    void testMultiply_3_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(6, 10);
        f2 = Fraction.of(6, 10);
        f = f1.multiply(f2);
        assertEquals(9, f.getNumerator());
    }

    @Test
    void testMultiply_4_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(6, 10);
        f2 = Fraction.of(6, 10);
        f = f1.multiply(f2);
        // removed other assertion
        assertEquals(25, f.getDenominator());
    }

    @Test
    void testMultiply_5_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(6, 10);
        f2 = Fraction.of(6, 10);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion
        f = f.multiply(f2);
        assertEquals(27, f.getNumerator());
    }

    @Test
    void testMultiply_6_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(6, 10);
        f2 = Fraction.of(6, 10);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion
        f = f.multiply(f2);
        // removed other assertion
        assertEquals(125, f.getDenominator());
    }

    @Test
    void testMultiply_7_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(6, 10);
        f2 = Fraction.of(6, 10);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion
        f = f.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        assertEquals(-6, f.getNumerator());
    }

    @Test
    void testMultiply_8_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(6, 10);
        f2 = Fraction.of(6, 10);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion
        f = f.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        assertEquals(25, f.getDenominator());
    }

    @Test
    void testMultiply_9_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(6, 10);
        f2 = Fraction.of(6, 10);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion
        f = f.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(-3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        assertEquals(6, f.getNumerator());
    }

    @Test
    void testMultiply_10_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(6, 10);
        f2 = Fraction.of(6, 10);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion
        f = f.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(-3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        assertEquals(25, f.getDenominator());
    }

    @Test
    void testMultiply_11_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(6, 10);
        f2 = Fraction.of(6, 10);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion
        f = f.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(-3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion


        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.multiply(f2);
        assertSame(Fraction.ZERO, f);
    }

    @Test
    void testMultiply_12_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(6, 10);
        f2 = Fraction.of(6, 10);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion
        f = f.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(-3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion


        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.multiply(f2);
        // removed other assertion

        f1 = Fraction.of(2, 7);
        f2 = Fraction.ONE;
        f = f1.multiply(f2);
        assertEquals(2, f.getNumerator());
    }

    @Test
    void testMultiply_13_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(6, 10);
        f2 = Fraction.of(6, 10);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion
        f = f.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(-3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion


        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.multiply(f2);
        // removed other assertion

        f1 = Fraction.of(2, 7);
        f2 = Fraction.ONE;
        f = f1.multiply(f2);
        // removed other assertion
        assertEquals(7, f.getDenominator());
    }

    @Test
    void testMultiply_14_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(6, 10);
        f2 = Fraction.of(6, 10);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion
        f = f.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(-3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion


        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.multiply(f2);
        // removed other assertion

        f1 = Fraction.of(2, 7);
        f2 = Fraction.ONE;
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE, 1);
        f2 = Fraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        f = f1.multiply(f2);
        assertEquals(Integer.MIN_VALUE, f.getNumerator());
    }

    @Test
    void testMultiply_15_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(6, 10);
        f2 = Fraction.of(6, 10);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion
        f = f.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(-3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion


        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.multiply(f2);
        // removed other assertion

        f1 = Fraction.of(2, 7);
        f2 = Fraction.ONE;
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE, 1);
        f2 = Fraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        f = f1.multiply(f2);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testMultiply_16_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(6, 10);
        f2 = Fraction.of(6, 10);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion
        f = f.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(-3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion


        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.multiply(f2);
        // removed other assertion

        f1 = Fraction.of(2, 7);
        f2 = Fraction.ONE;
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE, 1);
        f2 = Fraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        final Fraction fr = f;
        try {
    fr.multiply(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testMultiply_17_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(6, 10);
        f2 = Fraction.of(6, 10);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion
        f = f.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(-3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion


        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.multiply(f2);
        // removed other assertion

        f1 = Fraction.of(2, 7);
        f2 = Fraction.ONE;
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE, 1);
        f2 = Fraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        final Fraction fr = f;
        // removed other assertion

        final Fraction fr1 = Fraction.of(1, Integer.MAX_VALUE);
        try {
    fr1.multiply(fr1);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testMultiply_18_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(6, 10);
        f2 = Fraction.of(6, 10);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion
        f = f.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(-3, 5);
        f2 = Fraction.of(-2, 5);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion


        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.multiply(f2);
        // removed other assertion

        f1 = Fraction.of(2, 7);
        f2 = Fraction.ONE;
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MAX_VALUE, 1);
        f2 = Fraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        f = f1.multiply(f2);
        // removed other assertion
        // removed other assertion

        final Fraction fr = f;
        // removed other assertion

        final Fraction fr1 = Fraction.of(1, Integer.MAX_VALUE);
        // removed other assertion

        final Fraction fr2 = Fraction.of(1, -Integer.MAX_VALUE);
        try {
    fr2.multiply(fr2);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    void testDivide_1_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.divide(f2);
        assertEquals(3, f.getNumerator());
    }

    @Test
    void testDivide_2_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.divide(f2);
        // removed other assertion
        assertEquals(2, f.getDenominator());
    }

    @Test
    void testDivide_4_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.divide(f2);
        assertSame(Fraction.ZERO, f);
    }

    @Test
    void testDivide_5_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.divide(f2);
        // removed other assertion

        f1 = Fraction.of(2, 7);
        f2 = Fraction.ONE;
        f = f1.divide(f2);
        assertEquals(2, f.getNumerator());
    }

    @Test
    void testDivide_6_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.divide(f2);
        // removed other assertion

        f1 = Fraction.of(2, 7);
        f2 = Fraction.ONE;
        f = f1.divide(f2);
        // removed other assertion
        assertEquals(7, f.getDenominator());
    }

    @Test
    void testDivide_7_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.divide(f2);
        // removed other assertion

        f1 = Fraction.of(2, 7);
        f2 = Fraction.ONE;
        f = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(1, Integer.MAX_VALUE);
        f = f1.divide(f1);
        assertEquals(1, f.getNumerator());
    }

    @Test
    void testDivide_8_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.divide(f2);
        // removed other assertion

        f1 = Fraction.of(2, 7);
        f2 = Fraction.ONE;
        f = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(1, Integer.MAX_VALUE);
        f = f1.divide(f1);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    void testDivide_9_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.divide(f2);
        // removed other assertion

        f1 = Fraction.of(2, 7);
        f2 = Fraction.ONE;
        f = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(1, Integer.MAX_VALUE);
        f = f1.divide(f1);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        f2 = Fraction.of(1, Integer.MAX_VALUE);
        final Fraction fr = f1.divide(f2);
        assertEquals(Integer.MIN_VALUE, fr.getNumerator());
    }

    @Test
    void testDivide_10_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.divide(f2);
        // removed other assertion

        f1 = Fraction.of(2, 7);
        f2 = Fraction.ONE;
        f = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(1, Integer.MAX_VALUE);
        f = f1.divide(f1);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        f2 = Fraction.of(1, Integer.MAX_VALUE);
        final Fraction fr = f1.divide(f2);
        // removed other assertion
        assertEquals(1, fr.getDenominator());
    }

    @Test
    void testDivide_11_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.divide(f2);
        // removed other assertion

        f1 = Fraction.of(2, 7);
        f2 = Fraction.ONE;
        f = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(1, Integer.MAX_VALUE);
        f = f1.divide(f1);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        f2 = Fraction.of(1, Integer.MAX_VALUE);
        final Fraction fr = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        try {
    fr.divide(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testDivide_12_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.divide(f2);
        // removed other assertion

        f1 = Fraction.of(2, 7);
        f2 = Fraction.ONE;
        f = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(1, Integer.MAX_VALUE);
        f = f1.divide(f1);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        f2 = Fraction.of(1, Integer.MAX_VALUE);
        final Fraction fr = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final Fraction smallest = Fraction.of(1, Integer.MAX_VALUE);
        final Fraction smallestReciprocal = smallest.reciprocal();
        try {
    smallest.divide(smallestReciprocal);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
} // Should overflow;
    }

    @Test
    void testDivide_13_oe() {
        Fraction f = null;
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        f = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        f1 = Fraction.of(0, 5);
        f2 = Fraction.of(2, 7);
        f = f1.divide(f2);
        // removed other assertion

        f1 = Fraction.of(2, 7);
        f2 = Fraction.ONE;
        f = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(1, Integer.MAX_VALUE);
        f = f1.divide(f1);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        f2 = Fraction.of(1, Integer.MAX_VALUE);
        final Fraction fr = f1.divide(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final Fraction smallest = Fraction.of(1, Integer.MAX_VALUE);
        final Fraction smallestReciprocal = smallest.reciprocal();
        // removed other assertion

        final Fraction negative = Fraction.of(1, -Integer.MAX_VALUE);
        final Fraction negativeReciprocal = negative.reciprocal();
        try {
    negative.divide(negativeReciprocal);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
} // Should overflow;
    }

    @Test
    void testEquals_1_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        assertNotEquals(f1, null);
    }

    @Test
    void testEquals_2_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        // removed other assertion
        assertNotEquals(f1, new Object());
    }

    @Test
    void testEquals_3_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion
        assertNotEquals(f1, Integer.valueOf(6));
    }

    @Test
    void testEquals_4_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        assertNotEquals(f1, f2);
    }

    @Test
    void testEquals_5_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        // removed other assertion
        assertEquals(f1, f1);
    }

    @Test
    void testEquals_6_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        // removed other assertion
        // removed other assertion
        assertEquals(f2, f2);
    }

    @Test
    void testEquals_7_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f2 = Fraction.of(3, 5);
        assertEquals(f1, f2);
    }

    @Test
    void testEquals_8_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f1 = Fraction.of(3, 5);
        f2 = Fraction.of(2, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f2 = Fraction.of(3, 5);
        // removed other assertion

        f2 = Fraction.of(6, 10);
        assertEquals(f1, f2);
    }

    @Test
    void testHashCode_1_oe() {
        final Fraction f1 = Fraction.of(3, 5);
        Fraction f2 = Fraction.of(3, 5);

        assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void testHashCode_2_oe() {
        final Fraction f1 = Fraction.of(3, 5);
        Fraction f2 = Fraction.of(3, 5);

        // removed other assertion

        f2 = Fraction.of(2, 5);
        assertNotEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void testHashCode_3_oe() {
        final Fraction f1 = Fraction.of(3, 5);
        Fraction f2 = Fraction.of(3, 5);

        // removed other assertion

        f2 = Fraction.of(2, 5);
        // removed other assertion

        f2 = Fraction.of(6, 10);
        assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void testCompareTo_1_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        assertEquals(0, f1.compareTo(f1));
    }

    @Test
    void testCompareTo_2_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        try {
    fr.compareTo(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testCompareTo_3_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.of(2, 5);
        assertTrue(f1.compareTo(f2) > 0);
    }

    @Test
    void testCompareTo_4_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.of(2, 5);
        // removed other assertion
        assertEquals(0, f2.compareTo(f2));
    }

    @Test
    void testCompareTo_5_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.of(2, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.of(4, 5);
        assertTrue(f1.compareTo(f2) < 0);
    }

    @Test
    void testCompareTo_6_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.of(2, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.of(4, 5);
        // removed other assertion
        assertEquals(0, f2.compareTo(f2));
    }

    @Test
    void testCompareTo_7_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.of(2, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.of(4, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.of(3, 5);
        assertEquals(0, f1.compareTo(f2));
    }

    @Test
    void testCompareTo_8_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.of(2, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.of(4, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.of(3, 5);
        // removed other assertion
        assertEquals(0, f2.compareTo(f2));
    }

    @Test
    void testCompareTo_9_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.of(2, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.of(4, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.of(6, 10);
        assertEquals(0, f1.compareTo(f2));
    }

    @Test
    void testCompareTo_10_oe() {
        Fraction f1 = null;
        Fraction f2 = null;

        f1 = Fraction.of(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.of(2, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.of(4, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.of(3, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.of(6, 10);
        // removed other assertion
        assertEquals(0, f2.compareTo(f2));
    }

    @Test
    void testToString_1_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        final String str = f.toString();
        assertEquals("3 / 5", str);
    }

    @Test
    void testToString_2_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        final String str = f.toString();
        // removed other assertion
        assertEquals(str, f.toString());
    }

    @Test
    void testToString_3_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        final String str = f.toString();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(7, 5);
        assertEquals("7 / 5", f.toString());
    }

    @Test
    void testToString_4_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        final String str = f.toString();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(7, 5);
        // removed other assertion

        f = Fraction.of(4, 2);
        assertEquals("2", f.toString());
    }

    @Test
    void testToString_5_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        final String str = f.toString();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(7, 5);
        // removed other assertion

        f = Fraction.of(4, 2);
        // removed other assertion

        f = Fraction.of(0, 2);
        assertEquals("0", f.toString());
    }

    @Test
    void testToString_6_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        final String str = f.toString();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(7, 5);
        // removed other assertion

        f = Fraction.of(4, 2);
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion

        f = Fraction.of(2, 2);
        assertEquals("1", f.toString());
    }

    @Test
    void testToString_7_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        final String str = f.toString();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(7, 5);
        // removed other assertion

        f = Fraction.of(4, 2);
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion

        f = Fraction.of(2, 2);
        // removed other assertion

        f = Fraction.of(Integer.MIN_VALUE);
        assertEquals("-2147483648", f.toString());
    }

    @Test
    void testToString_8_oe() {
        Fraction f = null;

        f = Fraction.of(3, 5);
        final String str = f.toString();
        // removed other assertion
        // removed other assertion

        f = Fraction.of(7, 5);
        // removed other assertion

        f = Fraction.of(4, 2);
        // removed other assertion

        f = Fraction.of(0, 2);
        // removed other assertion

        f = Fraction.of(2, 2);
        // removed other assertion

        f = Fraction.of(Integer.MIN_VALUE);
        // removed other assertion

        f = Fraction.of(-1).add(Fraction.of(-1, Integer.MAX_VALUE));
        assertEquals("-2147483648 / 2147483647", f.toString());
    }

}
