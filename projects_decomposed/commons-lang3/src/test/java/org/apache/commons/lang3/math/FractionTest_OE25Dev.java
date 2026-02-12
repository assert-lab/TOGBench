/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for the {@link Fraction} class
 */
public class FractionTest_OE25Dev  {

    private static final int SKIP = 500;  //53

    //--------------------------------------------------------------------------

    @Test
    public void testAbs_9_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(Integer.MAX_VALUE, 1);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(Integer.MAX_VALUE, -1);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        try {
    Fraction.getFraction(Integer.MIN_VALUE, 1).abs();
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testAdd_19_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 8);
        f2 = Fraction.getFraction(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.getFraction(-1, 13*13*2*2);
        f2 = Fraction.getFraction(-2, 13*17*2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        // removed other assertion

        try {
    fr.add(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testAdd_26_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 8);
        f2 = Fraction.getFraction(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.getFraction(-1, 13*13*2*2);
        f2 = Fraction.getFraction(-2, 13*17*2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // if this fraction is added naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.getFraction(1, 32768*3);
        f2 = Fraction.getFraction(1, 59049);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MIN_VALUE, 3);
        f2 = Fraction.ONE_THIRD;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        final Fraction overflower = f;
        try {
    overflower.add(Fraction.ONE);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
} // should overflow;
    }

    @Test
    public void testAdd_27_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 8);
        f2 = Fraction.getFraction(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.getFraction(-1, 13*13*2*2);
        f2 = Fraction.getFraction(-2, 13*17*2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // if this fraction is added naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.getFraction(1, 32768*3);
        f2 = Fraction.getFraction(1, 59049);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MIN_VALUE, 3);
        f2 = Fraction.ONE_THIRD;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        final Fraction overflower = f;
        // removed other assertion

        // denominator should not be a multiple of 2 or 3 to trigger overflow
        try {
    Fraction.getFraction(Integer.MIN_VALUE, 5).add(Fraction.getFraction(-1, 5));
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testAdd_28_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 8);
        f2 = Fraction.getFraction(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.getFraction(-1, 13*13*2*2);
        f2 = Fraction.getFraction(-2, 13*17*2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // if this fraction is added naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.getFraction(1, 32768*3);
        f2 = Fraction.getFraction(1, 59049);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MIN_VALUE, 3);
        f2 = Fraction.ONE_THIRD;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        final Fraction overflower = f;
        // removed other assertion

        // denominator should not be a multiple of 2 or 3 to trigger overflow
        // removed other assertion

        final Fraction maxValue = Fraction.getFraction(-Integer.MAX_VALUE, 1);
        try {
    maxValue.add(maxValue);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testAdd_29_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 8);
        f2 = Fraction.getFraction(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.getFraction(-1, 13*13*2*2);
        f2 = Fraction.getFraction(-2, 13*17*2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // if this fraction is added naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.getFraction(1, 32768*3);
        f2 = Fraction.getFraction(1, 59049);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MIN_VALUE, 3);
        f2 = Fraction.ONE_THIRD;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        final Fraction overflower = f;
        // removed other assertion

        // denominator should not be a multiple of 2 or 3 to trigger overflow
        // removed other assertion

        final Fraction maxValue = Fraction.getFraction(-Integer.MAX_VALUE, 1);
        // removed other assertion

        final Fraction negativeMaxValue = Fraction.getFraction(-Integer.MAX_VALUE, 1);
        try {
    negativeMaxValue.add(negativeMaxValue);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testAdd_30_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(3, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 2);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 8);
        f2 = Fraction.getFraction(1, 6);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.add(f2);
        // removed other assertion
        f = f2.add(f1);
        // removed other assertion

        f1 = Fraction.getFraction(-1, 13*13*2*2);
        f2 = Fraction.getFraction(-2, 13*17*2);
        final Fraction fr = f1.add(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // if this fraction is added naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.getFraction(1, 32768*3);
        f2 = Fraction.getFraction(1, 59049);
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MIN_VALUE, 3);
        f2 = Fraction.ONE_THIRD;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE - 1, 1);
        f2 = Fraction.ONE;
        f = f1.add(f2);
        // removed other assertion
        // removed other assertion

        final Fraction overflower = f;
        // removed other assertion

        // denominator should not be a multiple of 2 or 3 to trigger overflow
        // removed other assertion

        final Fraction maxValue = Fraction.getFraction(-Integer.MAX_VALUE, 1);
        // removed other assertion

        final Fraction negativeMaxValue = Fraction.getFraction(-Integer.MAX_VALUE, 1);
        // removed other assertion

        final Fraction f3 = Fraction.getFraction(3, 327680);
        final Fraction f4 = Fraction.getFraction(2, 59049);
        try {
    f3.add(f4);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
} // should overflow;
    }

    @Test
    public void testCompareTo_2_oe() throws Exception {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        try {
    fr.compareTo(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testDivide_3_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.divideBy(f2);
        // removed other assertion
        // removed other assertion

        try {
    Fraction.getFraction(3, 5).divideBy(Fraction.ZERO);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testDivide_11_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.divideBy(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(2, 7);
        f = f1.divideBy(f2);
        // removed other assertion

        f1 = Fraction.getFraction(2, 7);
        f2 = Fraction.ONE;
        f = f1.divideBy(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(1, Integer.MAX_VALUE);
        f = f1.divideBy(f1);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MIN_VALUE, Integer.MAX_VALUE);
        f2 = Fraction.getFraction(1, Integer.MAX_VALUE);
        final Fraction fr = f1.divideBy(f2);
        // removed other assertion
        // removed other assertion

        try {
    fr.divideBy(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testDivide_12_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.divideBy(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(2, 7);
        f = f1.divideBy(f2);
        // removed other assertion

        f1 = Fraction.getFraction(2, 7);
        f2 = Fraction.ONE;
        f = f1.divideBy(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(1, Integer.MAX_VALUE);
        f = f1.divideBy(f1);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MIN_VALUE, Integer.MAX_VALUE);
        f2 = Fraction.getFraction(1, Integer.MAX_VALUE);
        final Fraction fr = f1.divideBy(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final Fraction smallest = Fraction.getFraction(1, Integer.MAX_VALUE);
        try {
    smallest.divideBy(smallest.invert());
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
} // Should overflow;
    }

    @Test
    public void testDivide_13_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.divideBy(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(2, 7);
        f = f1.divideBy(f2);
        // removed other assertion

        f1 = Fraction.getFraction(2, 7);
        f2 = Fraction.ONE;
        f = f1.divideBy(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(1, Integer.MAX_VALUE);
        f = f1.divideBy(f1);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MIN_VALUE, Integer.MAX_VALUE);
        f2 = Fraction.getFraction(1, Integer.MAX_VALUE);
        final Fraction fr = f1.divideBy(f2);
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final Fraction smallest = Fraction.getFraction(1, Integer.MAX_VALUE);
        // removed other assertion

        final Fraction negative = Fraction.getFraction(1, -Integer.MAX_VALUE);
        try {
    negative.divideBy(negative.invert());
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
} // Should overflow;
    }

    @Test
    public void testFactory_double_1_oe() throws Exception {
        try {
    Fraction.getFraction(Double.NaN);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_double_2_oe() throws Exception {
        // removed other assertion
        try {
    Fraction.getFraction(Double.POSITIVE_INFINITY);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_double_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    Fraction.getFraction(Double.NEGATIVE_INFINITY);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_double_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Fraction.getFraction((double) Integer.MAX_VALUE + 1);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_19_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.getFraction(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.getFraction(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        try {
    Fraction.getFraction(1, 0);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_20_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.getFraction(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.getFraction(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        try {
    Fraction.getFraction(2, 0);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_21_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.getFraction(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.getFraction(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        try {
    Fraction.getFraction(-3, 0);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_22_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.getFraction(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.getFraction(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // very large: can't represent as unsimplified fraction, although
        try {
    Fraction.getFraction(4, Integer.MIN_VALUE);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_23_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(23, 345);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.getFraction(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.getFraction(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // very large: can't represent as unsimplified fraction, although
        // removed other assertion
        try {
    Fraction.getFraction(1, Integer.MIN_VALUE);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_int_9_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1, 2);
        // removed other assertion
        // removed other assertion

        // negatives
        try {
    Fraction.getFraction(1, -6, -10);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_int_10_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1, 2);
        // removed other assertion
        // removed other assertion

        // negatives
        // removed other assertion
        try {
    Fraction.getFraction(1, -6, -10);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_int_11_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1, 2);
        // removed other assertion
        // removed other assertion

        // negatives
        // removed other assertion
        // removed other assertion
        try {
    Fraction.getFraction(1, -6, -10);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_int_14_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1, 2);
        // removed other assertion
        // removed other assertion

        // negatives
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // negative whole
        f = Fraction.getFraction(-1, 6, 10);
        // removed other assertion
        // removed other assertion

        try {
    Fraction.getFraction(-1, -6, 10);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_int_15_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1, 2);
        // removed other assertion
        // removed other assertion

        // negatives
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // negative whole
        f = Fraction.getFraction(-1, 6, 10);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    Fraction.getFraction(-1, 6, -10);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_int_16_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1, 2);
        // removed other assertion
        // removed other assertion

        // negatives
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // negative whole
        f = Fraction.getFraction(-1, 6, 10);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        try {
    Fraction.getFraction(-1, -6, -10);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_int_17_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1, 2);
        // removed other assertion
        // removed other assertion

        // negatives
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // negative whole
        f = Fraction.getFraction(-1, 6, 10);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero denominator
        try {
    Fraction.getFraction(0, 1, 0);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_int_18_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1, 2);
        // removed other assertion
        // removed other assertion

        // negatives
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // negative whole
        f = Fraction.getFraction(-1, 6, 10);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        try {
    Fraction.getFraction(1, 2, 0);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_int_19_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1, 2);
        // removed other assertion
        // removed other assertion

        // negatives
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // negative whole
        f = Fraction.getFraction(-1, 6, 10);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        try {
    Fraction.getFraction(-1, -3, 0);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_int_20_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1, 2);
        // removed other assertion
        // removed other assertion

        // negatives
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // negative whole
        f = Fraction.getFraction(-1, 6, 10);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Fraction.getFraction(Integer.MAX_VALUE, 1, 2);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_int_21_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1, 2);
        // removed other assertion
        // removed other assertion

        // negatives
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // negative whole
        f = Fraction.getFraction(-1, 6, 10);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Fraction.getFraction(-Integer.MAX_VALUE, 1, 2);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_int_24_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1, 2);
        // removed other assertion
        // removed other assertion

        // negatives
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // negative whole
        f = Fraction.getFraction(-1, 6, 10);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // very large
        f = Fraction.getFraction(-1, 0, Integer.MAX_VALUE);
        // removed other assertion
        // removed other assertion

        // negative denominators not allowed in this constructor.
        try {
    Fraction.getFraction(0, 4, Integer.MIN_VALUE);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_int_25_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1, 2);
        // removed other assertion
        // removed other assertion

        // negatives
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // negative whole
        f = Fraction.getFraction(-1, 6, 10);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // very large
        f = Fraction.getFraction(-1, 0, Integer.MAX_VALUE);
        // removed other assertion
        // removed other assertion

        // negative denominators not allowed in this constructor.
        // removed other assertion
        try {
    Fraction.getFraction(1, 1, Integer.MAX_VALUE);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_int_26_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1, 2);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getFraction(1, 1, 2);
        // removed other assertion
        // removed other assertion

        // negatives
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // negative whole
        f = Fraction.getFraction(-1, 6, 10);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // very large
        f = Fraction.getFraction(-1, 0, Integer.MAX_VALUE);
        // removed other assertion
        // removed other assertion

        // negative denominators not allowed in this constructor.
        // removed other assertion
        // removed other assertion
        try {
    Fraction.getFraction(-1, 2, Integer.MAX_VALUE);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_String_1_oe() throws Exception {
        try {
    Fraction.getFraction(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testFactory_String_double_9_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction("0.0");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("0.2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("0.5");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("0.66666");
        // removed other assertion
        // removed other assertion

        try {
    Fraction.getFraction("2.3R");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    public void testFactory_String_double_10_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction("0.0");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("0.2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("0.5");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("0.66666");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    Fraction.getFraction("2147483648");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
} // too big;
    }

    @Test
    public void testFactory_String_double_11_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction("0.0");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("0.2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("0.5");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("0.66666");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        try {
    Fraction.getFraction(".");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    public void testFactory_String_improper_13_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction("0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1/5");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1/2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("2/3");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("7/3");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("2/4");
        // removed other assertion
        // removed other assertion

        try {
    Fraction.getFraction("2/d");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    public void testFactory_String_improper_14_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction("0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1/5");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1/2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("2/3");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("7/3");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("2/4");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    Fraction.getFraction("2e/3");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    public void testFactory_String_improper_15_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction("0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1/5");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1/2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("2/3");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("7/3");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("2/4");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        try {
    Fraction.getFraction("2/");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    public void testFactory_String_improper_16_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction("0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1/5");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1/2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("2/3");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("7/3");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("2/4");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Fraction.getFraction("/");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    public void testFactory_String_proper_13_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction("0 0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1 1/5");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("7 1/2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1 2/4");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("-7 1/2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("-1 2/4");
        // removed other assertion
        // removed other assertion

        try {
    Fraction.getFraction("2 3");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    public void testFactory_String_proper_14_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction("0 0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1 1/5");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("7 1/2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1 2/4");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("-7 1/2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("-1 2/4");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    Fraction.getFraction("a 3");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    public void testFactory_String_proper_15_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction("0 0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1 1/5");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("7 1/2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1 2/4");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("-7 1/2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("-1 2/4");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        try {
    Fraction.getFraction("2 b/4");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    public void testFactory_String_proper_16_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction("0 0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1 1/5");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("7 1/2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1 2/4");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("-7 1/2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("-1 2/4");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Fraction.getFraction("2 ");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    public void testFactory_String_proper_17_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction("0 0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1 1/5");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("7 1/2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1 2/4");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("-7 1/2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("-1 2/4");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Fraction.getFraction(" 3");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    public void testFactory_String_proper_18_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction("0 0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1 1/5");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("7 1/2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1 2/4");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("-7 1/2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("-1 2/4");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Fraction.getFraction(" ");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    public void testInvert_7_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        f = f.invert();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(4, 3);
        f = f.invert();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-15, 47);
        f = f.invert();
        // removed other assertion
        // removed other assertion

        try {
    Fraction.getFraction(0, 3).invert();
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testInvert_8_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        f = f.invert();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(4, 3);
        f = f.invert();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-15, 47);
        f = f.invert();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    Fraction.getFraction(Integer.MIN_VALUE, 1).invert();
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testMultiply_16_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(6, 10);
        f2 = Fraction.getFraction(6, 10);
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion
        f = f.multiplyBy(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(-2, 5);
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(-3, 5);
        f2 = Fraction.getFraction(-2, 5);
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion


        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(2, 7);
        f = f1.multiplyBy(f2);
        // removed other assertion

        f1 = Fraction.getFraction(2, 7);
        f2 = Fraction.ONE;
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE, 1);
        f2 = Fraction.getFraction(Integer.MIN_VALUE, Integer.MAX_VALUE);
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion

        final Fraction fr = f;
        try {
    fr.multiplyBy(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMultiply_17_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(6, 10);
        f2 = Fraction.getFraction(6, 10);
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion
        f = f.multiplyBy(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(-2, 5);
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(-3, 5);
        f2 = Fraction.getFraction(-2, 5);
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion


        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(2, 7);
        f = f1.multiplyBy(f2);
        // removed other assertion

        f1 = Fraction.getFraction(2, 7);
        f2 = Fraction.ONE;
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE, 1);
        f2 = Fraction.getFraction(Integer.MIN_VALUE, Integer.MAX_VALUE);
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion

        final Fraction fr = f;
        // removed other assertion

        final Fraction fr1 = Fraction.getFraction(1, Integer.MAX_VALUE);
        try {
    fr1.multiplyBy(fr1);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testMultiply_18_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(6, 10);
        f2 = Fraction.getFraction(6, 10);
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion
        f = f.multiplyBy(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(-2, 5);
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(-3, 5);
        f2 = Fraction.getFraction(-2, 5);
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion


        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(2, 7);
        f = f1.multiplyBy(f2);
        // removed other assertion

        f1 = Fraction.getFraction(2, 7);
        f2 = Fraction.ONE;
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE, 1);
        f2 = Fraction.getFraction(Integer.MIN_VALUE, Integer.MAX_VALUE);
        f = f1.multiplyBy(f2);
        // removed other assertion
        // removed other assertion

        final Fraction fr = f;
        // removed other assertion

        final Fraction fr1 = Fraction.getFraction(1, Integer.MAX_VALUE);
        // removed other assertion

        final Fraction fr2 = Fraction.getFraction(1, -Integer.MAX_VALUE);
        try {
    fr2.multiplyBy(fr2);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testNegate_7_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-50, 75);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        // large values
        f = Fraction.getFraction(Integer.MAX_VALUE-1, Integer.MAX_VALUE);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        try {
    Fraction.getFraction(Integer.MIN_VALUE, 1).negate();
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testPow_29_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.getFraction(6, 10);
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.getFraction(0, 1231);
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
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testPow_30_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.getFraction(6, 10);
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.getFraction(0, 1231);
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
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testPow_36_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.getFraction(6, 10);
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.getFraction(0, 1231);
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
        f = Fraction.getFraction(1, 1);
        f = f.pow(0);
        // removed other assertion
        f = f.pow(1);
        // removed other assertion
        f = f.pow(-1);
        // removed other assertion
        f = f.pow(Integer.MAX_VALUE);
        // removed other assertion
        f = f.pow(Integer.MIN_VALUE);
        // removed other assertion

        try {
    Fraction.getFraction(Integer.MAX_VALUE, 1).pow(2);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testPow_37_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.getFraction(6, 10);
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.getFraction(0, 1231);
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
        f = Fraction.getFraction(1, 1);
        f = f.pow(0);
        // removed other assertion
        f = f.pow(1);
        // removed other assertion
        f = f.pow(-1);
        // removed other assertion
        f = f.pow(Integer.MAX_VALUE);
        // removed other assertion
        f = f.pow(Integer.MIN_VALUE);
        // removed other assertion

        // removed other assertion

        // Numerator growing too negative during the pow operation.
        try {
    Fraction.getFraction(Integer.MIN_VALUE, 1).pow(3);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testPow_38_oe() throws Exception {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // check unreduced fractions stay that way.
        f = Fraction.getFraction(6, 10);
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(3);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(-1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(6, 10);
        f = f.pow(-2);
        // removed other assertion
        // removed other assertion

        // zero to any positive power is still zero.
        f = Fraction.getFraction(0, 1231);
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
        f = Fraction.getFraction(1, 1);
        f = f.pow(0);
        // removed other assertion
        f = f.pow(1);
        // removed other assertion
        f = f.pow(-1);
        // removed other assertion
        f = f.pow(Integer.MAX_VALUE);
        // removed other assertion
        f = f.pow(Integer.MIN_VALUE);
        // removed other assertion

        // removed other assertion

        // Numerator growing too negative during the pow operation.
        // removed other assertion

        try {
    Fraction.getFraction(65536, 1).pow(2);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testReducedFactory_int_int_15_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getReducedFraction(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getReducedFraction(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getReducedFraction(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.getReducedFraction(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.getReducedFraction(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getReducedFraction(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getReducedFraction(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        try {
    Fraction.getReducedFraction(1, 0);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testReducedFactory_int_int_16_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getReducedFraction(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getReducedFraction(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getReducedFraction(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.getReducedFraction(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.getReducedFraction(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getReducedFraction(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getReducedFraction(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        try {
    Fraction.getReducedFraction(2, 0);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testReducedFactory_int_int_17_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getReducedFraction(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getReducedFraction(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getReducedFraction(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.getReducedFraction(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.getReducedFraction(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getReducedFraction(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getReducedFraction(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        try {
    Fraction.getReducedFraction(-3, 0);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testReducedFactory_int_int_30_oe() throws Exception {
        Fraction f;

        // zero
        f = Fraction.getReducedFraction(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getReducedFraction(1, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getReducedFraction(2, 1);
        // removed other assertion
        // removed other assertion

        // improper
        f = Fraction.getReducedFraction(22, 7);
        // removed other assertion
        // removed other assertion

        // negatives
        f = Fraction.getReducedFraction(-6, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getReducedFraction(6, -10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getReducedFraction(-6, -10);
        // removed other assertion
        // removed other assertion

        // zero denominator
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // reduced
        f = Fraction.getReducedFraction(0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getReducedFraction(2, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getReducedFraction(2, 4);
        // removed other assertion
        // removed other assertion

        f = Fraction.getReducedFraction(15, 10);
        // removed other assertion
        // removed other assertion

        f = Fraction.getReducedFraction(121, 22);
        // removed other assertion
        // removed other assertion

        // Extreme values
        // OK, can reduce before negating
        f = Fraction.getReducedFraction(-2, Integer.MIN_VALUE);
        // removed other assertion
        // removed other assertion

        // Can't reduce, negation will throw
        try {
    Fraction.getReducedFraction(-7, Integer.MIN_VALUE);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testSubtract_16_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(7, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 2);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f2.subtract(f1);
        // removed other assertion

        final Fraction fr = f;
        try {
    fr.subtract(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testSubtract_23_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(7, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 2);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f2.subtract(f1);
        // removed other assertion

        final Fraction fr = f;
        // removed other assertion

        // if this fraction is subtracted naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.getFraction(1, 32768*3);
        f2 = Fraction.getFraction(1, 59049);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MIN_VALUE, 3);
        f2 = Fraction.ONE_THIRD.negate();
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE, 1);
        f2 = Fraction.ONE;
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        // Should overflow
        try {
    Fraction.getFraction(1, Integer.MAX_VALUE).subtract(Fraction.getFraction(1, Integer.MAX_VALUE - 1));
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testSubtract_24_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(7, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 2);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f2.subtract(f1);
        // removed other assertion

        final Fraction fr = f;
        // removed other assertion

        // if this fraction is subtracted naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.getFraction(1, 32768*3);
        f2 = Fraction.getFraction(1, 59049);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MIN_VALUE, 3);
        f2 = Fraction.ONE_THIRD.negate();
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE, 1);
        f2 = Fraction.ONE;
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        // Should overflow
        // removed other assertion
            f = f1.subtract(f2);

        // denominator should not be a multiple of 2 or 3 to trigger overflow
        try {
    Fraction.getFraction(Integer.MIN_VALUE, 5).subtract(Fraction.getFraction(1, 5));
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testSubtract_25_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(7, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 2);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f2.subtract(f1);
        // removed other assertion

        final Fraction fr = f;
        // removed other assertion

        // if this fraction is subtracted naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.getFraction(1, 32768*3);
        f2 = Fraction.getFraction(1, 59049);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MIN_VALUE, 3);
        f2 = Fraction.ONE_THIRD.negate();
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE, 1);
        f2 = Fraction.ONE;
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        // Should overflow
        // removed other assertion
            f = f1.subtract(f2);

        // denominator should not be a multiple of 2 or 3 to trigger overflow
        // removed other assertion

        try {
    Fraction.getFraction(Integer.MIN_VALUE, 1).subtract(Fraction.ONE);
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testSubtract_26_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(7, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 2);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f2.subtract(f1);
        // removed other assertion

        final Fraction fr = f;
        // removed other assertion

        // if this fraction is subtracted naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.getFraction(1, 32768*3);
        f2 = Fraction.getFraction(1, 59049);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MIN_VALUE, 3);
        f2 = Fraction.ONE_THIRD.negate();
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE, 1);
        f2 = Fraction.ONE;
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        // Should overflow
        // removed other assertion
            f = f1.subtract(f2);

        // denominator should not be a multiple of 2 or 3 to trigger overflow
        // removed other assertion

        // removed other assertion

        try {
    Fraction.getFraction(Integer.MAX_VALUE, 1).subtract(Fraction.ONE.negate());
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testSubtract_27_oe() throws Exception {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(7, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(3, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(-4, 5);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 2);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(0, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f2.subtract(f1);
        // removed other assertion

        final Fraction fr = f;
        // removed other assertion

        // if this fraction is subtracted naively, it will overflow.
        // check that it doesn't.
        f1 = Fraction.getFraction(1, 32768*3);
        f2 = Fraction.getFraction(1, 59049);
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MIN_VALUE, 3);
        f2 = Fraction.ONE_THIRD.negate();
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(Integer.MAX_VALUE, 1);
        f2 = Fraction.ONE;
        f = f1.subtract(f2);
        // removed other assertion
        // removed other assertion

        // Should overflow
        // removed other assertion
            f = f1.subtract(f2);

        // denominator should not be a multiple of 2 or 3 to trigger overflow
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // Should overflow
        try {
    Fraction.getFraction(3, 327680).subtract(Fraction.getFraction(2, 59049));
    org.junit.jupiter.api.Assertions.fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

}
