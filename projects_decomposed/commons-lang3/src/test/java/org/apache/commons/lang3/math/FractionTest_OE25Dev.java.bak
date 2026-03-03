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

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test cases for the {@link Fraction} class
 */
public class FractionTest_OE25Dev  {

    private static final int SKIP = 500;  //53

    //--------------------------------------------------------------------------

    @Test
    public void testAbs_1_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        f = f.abs();
        assertEquals(50, f.getNumerator());
    }

    @Test
    public void testAbs_2_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        f = f.abs();
        // removed other assertion
        assertEquals(75, f.getDenominator());
    }

    @Test
    public void testAbs_3_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-50, 75);
        f = f.abs();
        assertEquals(50, f.getNumerator());
    }

    @Test
    public void testAbs_4_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        f = f.abs();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-50, 75);
        f = f.abs();
        // removed other assertion
        assertEquals(75, f.getDenominator());
    }

    @Test
    public void testAbs_5_oe() {
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
        assertEquals(Integer.MAX_VALUE, f.getNumerator());
    }

    @Test
    public void testAbs_6_oe() {
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
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testAbs_7_oe() {
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
        assertEquals(Integer.MAX_VALUE, f.getNumerator());
    }

    @Test
    public void testAbs_8_oe() {
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
        assertEquals(1, f.getDenominator());
    }

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
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testAdd_1_oe() {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.add(f2);
        assertEquals(4, f.getNumerator());
    }

    @Test
    public void testAdd_2_oe() {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.add(f2);
        // removed other assertion
        assertEquals(5, f.getDenominator());
    }

    @Test
    public void testAdd_3_oe() {
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
        assertEquals(1, f.getNumerator());
    }

    @Test
    public void testAdd_4_oe() {
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
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testAdd_5_oe() {
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
        assertEquals(6, f.getNumerator());
    }

    @Test
    public void testAdd_6_oe() {
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
        assertEquals(5, f.getDenominator());
    }

    @Test
    public void testAdd_7_oe() {
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
        assertEquals(-1, f.getNumerator());
    }

    @Test
    public void testAdd_8_oe() {
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
        assertEquals(5, f.getDenominator());
    }

    @Test
    public void testAdd_9_oe() {
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
        assertEquals(Integer.MAX_VALUE, f.getNumerator());
    }

    @Test
    public void testAdd_10_oe() {
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
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testAdd_11_oe() {
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
        assertEquals(11, f.getNumerator());
    }

    @Test
    public void testAdd_12_oe() {
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
        assertEquals(10, f.getDenominator());
    }

    @Test
    public void testAdd_13_oe() {
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
        assertEquals(13, f.getNumerator());
    }

    @Test
    public void testAdd_14_oe() {
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
        assertEquals(24, f.getDenominator());
    }

    @Test
    public void testAdd_15_oe() {
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
        assertSame(f2, f);
    }

    @Test
    public void testAdd_16_oe() {
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
        assertSame(f2, f);
    }

    @Test
    public void testAdd_17_oe() {
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
        assertEquals(13*13*17*2*2, fr.getDenominator());
    }

    @Test
    public void testAdd_18_oe() {
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
        assertEquals(-17 - 2*13*2, fr.getNumerator());
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
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testAdd_20_oe() {
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
        assertEquals(52451, f.getNumerator());
    }

    @Test
    public void testAdd_21_oe() {
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
        assertEquals(1934917632, f.getDenominator());
    }

    @Test
    public void testAdd_22_oe() {
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
        assertEquals(Integer.MIN_VALUE+1, f.getNumerator());
    }

    @Test
    public void testAdd_23_oe() {
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
        assertEquals(3, f.getDenominator());
    }

    @Test
    public void testAdd_24_oe() {
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
        assertEquals(Integer.MAX_VALUE, f.getNumerator());
    }

    @Test
    public void testAdd_25_oe() {
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
        assertEquals(1, f.getDenominator());
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
} catch (ArithmeticException e) {
} // should overflow;
    }

    @Test
    public void testCompareTo_1_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        assertEquals(0, f1.compareTo(f1));
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
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testCompareTo_3_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.getFraction(2, 5);
        assertTrue(f1.compareTo(f2) > 0);
    }

    @Test
    public void testCompareTo_4_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.getFraction(2, 5);
        // removed other assertion
        assertEquals(0, f2.compareTo(f2));
    }

    @Test
    public void testCompareTo_5_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.getFraction(2, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(4, 5);
        assertTrue(f1.compareTo(f2) < 0);
    }

    @Test
    public void testCompareTo_6_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.getFraction(2, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(4, 5);
        // removed other assertion
        assertEquals(0, f2.compareTo(f2));
    }

    @Test
    public void testCompareTo_7_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.getFraction(2, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(4, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(3, 5);
        assertEquals(0, f1.compareTo(f2));
    }

    @Test
    public void testCompareTo_8_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.getFraction(2, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(4, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(3, 5);
        // removed other assertion
        assertEquals(0, f2.compareTo(f2));
    }

    @Test
    public void testCompareTo_9_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.getFraction(2, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(4, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(3, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(6, 10);
        assertEquals(0, f1.compareTo(f2));
    }

    @Test
    public void testCompareTo_10_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.getFraction(2, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(4, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(3, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(6, 10);
        // removed other assertion
        assertEquals(0, f2.compareTo(f2));
    }

    @Test
    public void testCompareTo_11_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.getFraction(2, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(4, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(3, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(6, 10);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(-1, 1, Integer.MAX_VALUE);
        assertTrue(f1.compareTo(f2) > 0);
    }

    @Test
    public void testCompareTo_12_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion

        final Fraction fr = f1;
        // removed other assertion

        f2 = Fraction.getFraction(2, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(4, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(3, 5);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(6, 10);
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(-1, 1, Integer.MAX_VALUE);
        // removed other assertion
        assertEquals(0, f2.compareTo(f2));
    }

    @Test
    public void testConstants_1_oe() {
        assertEquals(0, Fraction.ZERO.getNumerator());
    }

    @Test
    public void testConstants_2_oe() {
        // removed other assertion
        assertEquals(1, Fraction.ZERO.getDenominator());
    }

    @Test
    public void testConstants_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals(1, Fraction.ONE.getNumerator());
    }

    @Test
    public void testConstants_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(1, Fraction.ONE.getDenominator());
    }

    @Test
    public void testConstants_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(1, Fraction.ONE_HALF.getNumerator());
    }

    @Test
    public void testConstants_6_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(2, Fraction.ONE_HALF.getDenominator());
    }

    @Test
    public void testConstants_7_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(1, Fraction.ONE_THIRD.getNumerator());
    }

    @Test
    public void testConstants_8_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(3, Fraction.ONE_THIRD.getDenominator());
    }

    @Test
    public void testConstants_9_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(2, Fraction.TWO_THIRDS.getNumerator());
    }

    @Test
    public void testConstants_10_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(3, Fraction.TWO_THIRDS.getDenominator());
    }

    @Test
    public void testConstants_11_oe() {
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

        assertEquals(1, Fraction.ONE_QUARTER.getNumerator());
    }

    @Test
    public void testConstants_12_oe() {
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
        assertEquals(4, Fraction.ONE_QUARTER.getDenominator());
    }

    @Test
    public void testConstants_13_oe() {
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

        assertEquals(2, Fraction.TWO_QUARTERS.getNumerator());
    }

    @Test
    public void testConstants_14_oe() {
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

        // removed other assertion
        assertEquals(4, Fraction.TWO_QUARTERS.getDenominator());
    }

    @Test
    public void testConstants_15_oe() {
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

        // removed other assertion
        // removed other assertion

        assertEquals(3, Fraction.THREE_QUARTERS.getNumerator());
    }

    @Test
    public void testConstants_16_oe() {
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

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(4, Fraction.THREE_QUARTERS.getDenominator());
    }

    @Test
    public void testConstants_17_oe() {
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

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(1, Fraction.ONE_FIFTH.getNumerator());
    }

    @Test
    public void testConstants_18_oe() {
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

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(5, Fraction.ONE_FIFTH.getDenominator());
    }

    @Test
    public void testConstants_19_oe() {
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

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(2, Fraction.TWO_FIFTHS.getNumerator());
    }

    @Test
    public void testConstants_20_oe() {
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

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(5, Fraction.TWO_FIFTHS.getDenominator());
    }

    @Test
    public void testConstants_21_oe() {
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

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(3, Fraction.THREE_FIFTHS.getNumerator());
    }

    @Test
    public void testConstants_22_oe() {
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

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(5, Fraction.THREE_FIFTHS.getDenominator());
    }

    @Test
    public void testConstants_23_oe() {
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

        assertEquals(4, Fraction.FOUR_FIFTHS.getNumerator());
    }

    @Test
    public void testConstants_24_oe() {
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
        assertEquals(5, Fraction.FOUR_FIFTHS.getDenominator());
    }

    @Test
    public void testConversions_1_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 7, 8);
        assertEquals(3, f.intValue());
    }

    @Test
    public void testConversions_2_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 7, 8);
        // removed other assertion
        assertEquals(3L, f.longValue());
    }

    @Test
    public void testConversions_3_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 7, 8);
        // removed other assertion
        // removed other assertion
        assertEquals(3.875f, f.floatValue(), 0.00001f);
    }

    @Test
    public void testConversions_4_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 7, 8);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3.875d, f.doubleValue(), 0.00001d);
    }

    @Test
    public void testDivide_1_oe() {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.divideBy(f2);
        assertEquals(3, f.getNumerator());
    }

    @Test
    public void testDivide_2_oe() {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.divideBy(f2);
        // removed other assertion
        assertEquals(2, f.getDenominator());
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
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testDivide_4_oe() {
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
        assertSame(Fraction.ZERO, f);
    }

    @Test
    public void testDivide_5_oe() {
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
        assertEquals(2, f.getNumerator());
    }

    @Test
    public void testDivide_6_oe() {
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
        assertEquals(7, f.getDenominator());
    }

    @Test
    public void testDivide_7_oe() {
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
        assertEquals(1, f.getNumerator());
    }

    @Test
    public void testDivide_8_oe() {
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
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testDivide_9_oe() {
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
        assertEquals(Integer.MIN_VALUE, fr.getNumerator());
    }

    @Test
    public void testDivide_10_oe() {
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
        assertEquals(1, fr.getDenominator());
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
    fail("NullPointerException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
} catch (ArithmeticException e) {
} // Should overflow;
    }

    @Test
    public void testEquals_1_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        assertNotEquals(null, f1);
    }

    @Test
    public void testEquals_2_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion
        assertNotEquals(f1, new Object());
    }

    @Test
    public void testEquals_3_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion
        // removed other assertion
        assertNotEquals(f1, Integer.valueOf(6));
    }

    @Test
    public void testEquals_4_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        assertNotEquals(f1, f2);
    }

    @Test
    public void testEquals_5_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        // removed other assertion
        assertEquals(f1, f1);
    }

    @Test
    public void testEquals_6_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        // removed other assertion
        // removed other assertion
        assertEquals(f2, f2);
    }

    @Test
    public void testEquals_7_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(3, 5);
        assertEquals(f1, f2);
    }

    @Test
    public void testEquals_8_oe() {
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f2 = Fraction.getFraction(3, 5);
        // removed other assertion

        f2 = Fraction.getFraction(6, 10);
        assertNotEquals(f1, f2);
    }

    @Test
    public void testFactory_double_1_oe() throws Exception {
        try {
    Fraction.getFraction(Double.NaN);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_double_2_oe() throws Exception {
        // removed other assertion
        try {
    Fraction.getFraction(Double.POSITIVE_INFINITY);
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_double_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    Fraction.getFraction(Double.NEGATIVE_INFINITY);
    fail("ArithmeticException");
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
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_double_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        assertEquals(0, f.getNumerator());
    }

    @Test
    public void testFactory_double_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testFactory_double_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.getFraction(1.0d);
        assertEquals(1, f.getNumerator());
    }

    @Test
    public void testFactory_double_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.getFraction(1.0d);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testFactory_double_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.getFraction(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.getFraction(0.5d);
        assertEquals(1, f.getNumerator());
    }

    @Test
    public void testFactory_double_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.getFraction(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.getFraction(0.5d);
        // removed other assertion
        assertEquals(2, f.getDenominator());
    }

    @Test
    public void testFactory_double_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.getFraction(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.getFraction(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.getFraction(-0.875d);
        assertEquals(-7, f.getNumerator());
    }

    @Test
    public void testFactory_double_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.getFraction(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.getFraction(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.getFraction(-0.875d);
        // removed other assertion
        assertEquals(8, f.getDenominator());
    }

    @Test
    public void testFactory_double_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.getFraction(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.getFraction(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.getFraction(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.getFraction(1.25d);
        assertEquals(5, f.getNumerator());
    }

    @Test
    public void testFactory_double_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.getFraction(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.getFraction(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.getFraction(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.getFraction(1.25d);
        // removed other assertion
        assertEquals(4, f.getDenominator());
    }

    @Test
    public void testFactory_double_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.getFraction(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.getFraction(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.getFraction(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.getFraction(1.25d);
        // removed other assertion
        // removed other assertion

        // two thirds
        f = Fraction.getFraction(0.66666d);
        assertEquals(2, f.getNumerator());
    }

    @Test
    public void testFactory_double_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.getFraction(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.getFraction(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.getFraction(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.getFraction(1.25d);
        // removed other assertion
        // removed other assertion

        // two thirds
        f = Fraction.getFraction(0.66666d);
        // removed other assertion
        assertEquals(3, f.getDenominator());
    }

    @Test
    public void testFactory_double_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.getFraction(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.getFraction(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.getFraction(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.getFraction(1.25d);
        // removed other assertion
        // removed other assertion

        // two thirds
        f = Fraction.getFraction(0.66666d);
        // removed other assertion
        // removed other assertion

        // small
        f = Fraction.getFraction(1.0d/10001d);
        assertEquals(0, f.getNumerator());
    }

    @Test
    public void testFactory_double_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.getFraction(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.getFraction(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.getFraction(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.getFraction(1.25d);
        // removed other assertion
        // removed other assertion

        // two thirds
        f = Fraction.getFraction(0.66666d);
        // removed other assertion
        // removed other assertion

        // small
        f = Fraction.getFraction(1.0d/10001d);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testFactory_double_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.getFraction(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.getFraction(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.getFraction(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.getFraction(1.25d);
        // removed other assertion
        // removed other assertion

        // two thirds
        f = Fraction.getFraction(0.66666d);
        // removed other assertion
        // removed other assertion

        // small
        f = Fraction.getFraction(1.0d/10001d);
        // removed other assertion
        // removed other assertion

        // normal
        Fraction f2 = null;
        for (int i = 1; i <= 100; i++) {  // denominator
            for (int j = 1; j <= i; j++) {  // numerator
                f = Fraction.getFraction((double) j / (double) i);

                f2 = Fraction.getReducedFraction(j, i);
                assertEquals(f2.getNumerator(), f.getNumerator());
    }
    }
    }

    @Test
    public void testFactory_double_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.getFraction(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.getFraction(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.getFraction(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.getFraction(1.25d);
        // removed other assertion
        // removed other assertion

        // two thirds
        f = Fraction.getFraction(0.66666d);
        // removed other assertion
        // removed other assertion

        // small
        f = Fraction.getFraction(1.0d/10001d);
        // removed other assertion
        // removed other assertion

        // normal
        Fraction f2 = null;
        for (int i = 1; i <= 100; i++) {  // denominator
            for (int j = 1; j <= i; j++) {  // numerator
                f = Fraction.getFraction((double) j / (double) i);

                f2 = Fraction.getReducedFraction(j, i);
                // removed other assertion
                assertEquals(f2.getDenominator(), f.getDenominator());
    }
    }
    }

    @Test
    public void testFactory_double_21_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.getFraction(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.getFraction(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.getFraction(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.getFraction(1.25d);
        // removed other assertion
        // removed other assertion

        // two thirds
        f = Fraction.getFraction(0.66666d);
        // removed other assertion
        // removed other assertion

        // small
        f = Fraction.getFraction(1.0d/10001d);
        // removed other assertion
        // removed other assertion

        // normal
        Fraction f2 = null;
        for (int i = 1; i <= 100; i++) {  // denominator
            for (int j = 1; j <= i; j++) {  // numerator
                f = Fraction.getFraction((double) j / (double) i);

                f2 = Fraction.getReducedFraction(j, i);
                // removed other assertion
                // removed other assertion
            }
        }
        // save time by skipping some tests!  (
        for (int i = 1001; i <= 10000; i+=SKIP) {  // denominator
            for (int j = 1; j <= i; j++) {  // numerator
                f = Fraction.getFraction((double) j / (double) i);
                f2 = Fraction.getReducedFraction(j, i);
                assertEquals(f2.getNumerator(), f.getNumerator());
    }
    }
    }

    @Test
    public void testFactory_double_22_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // zero
        Fraction f = Fraction.getFraction(0.0d);
        // removed other assertion
        // removed other assertion

        // one
        f = Fraction.getFraction(1.0d);
        // removed other assertion
        // removed other assertion

        // one half
        f = Fraction.getFraction(0.5d);
        // removed other assertion
        // removed other assertion

        // negative
        f = Fraction.getFraction(-0.875d);
        // removed other assertion
        // removed other assertion

        // over 1
        f = Fraction.getFraction(1.25d);
        // removed other assertion
        // removed other assertion

        // two thirds
        f = Fraction.getFraction(0.66666d);
        // removed other assertion
        // removed other assertion

        // small
        f = Fraction.getFraction(1.0d/10001d);
        // removed other assertion
        // removed other assertion

        // normal
        Fraction f2 = null;
        for (int i = 1; i <= 100; i++) {  // denominator
            for (int j = 1; j <= i; j++) {  // numerator
                f = Fraction.getFraction((double) j / (double) i);

                f2 = Fraction.getReducedFraction(j, i);
                // removed other assertion
                // removed other assertion
            }
        }
        // save time by skipping some tests!  (
        for (int i = 1001; i <= 10000; i+=SKIP) {  // denominator
            for (int j = 1; j <= i; j++) {  // numerator
                f = Fraction.getFraction((double) j / (double) i);
                f2 = Fraction.getReducedFraction(j, i);
                // removed other assertion
                assertEquals(f2.getDenominator(), f.getDenominator());
    }
    }
    }

    @Test
    public void testFactory_int_int_1_oe() {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 1);
        assertEquals(0, f.getNumerator());
    }

    @Test
    public void testFactory_int_int_2_oe() {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 1);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testFactory_int_int_3_oe() {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 2);
        assertEquals(0, f.getNumerator());
    }

    @Test
    public void testFactory_int_int_4_oe() {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 1);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 2);
        // removed other assertion
        assertEquals(2, f.getDenominator());
    }

    @Test
    public void testFactory_int_int_5_oe() {
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
        assertEquals(1, f.getNumerator());
    }

    @Test
    public void testFactory_int_int_6_oe() {
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
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testFactory_int_int_7_oe() {
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
        assertEquals(2, f.getNumerator());
    }

    @Test
    public void testFactory_int_int_8_oe() {
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
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testFactory_int_int_9_oe() {
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
        assertEquals(23, f.getNumerator());
    }

    @Test
    public void testFactory_int_int_10_oe() {
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
        assertEquals(345, f.getDenominator());
    }

    @Test
    public void testFactory_int_int_11_oe() {
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
        assertEquals(22, f.getNumerator());
    }

    @Test
    public void testFactory_int_int_12_oe() {
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
        assertEquals(7, f.getDenominator());
    }

    @Test
    public void testFactory_int_int_13_oe() {
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
        assertEquals(-6, f.getNumerator());
    }

    @Test
    public void testFactory_int_int_14_oe() {
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
        assertEquals(10, f.getDenominator());
    }

    @Test
    public void testFactory_int_int_15_oe() {
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
        assertEquals(-6, f.getNumerator());
    }

    @Test
    public void testFactory_int_int_16_oe() {
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
        assertEquals(10, f.getDenominator());
    }

    @Test
    public void testFactory_int_int_17_oe() {
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
        assertEquals(6, f.getNumerator());
    }

    @Test
    public void testFactory_int_int_18_oe() {
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
        assertEquals(10, f.getDenominator());
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_int_1_oe() {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        assertEquals(0, f.getNumerator());
    }

    @Test
    public void testFactory_int_int_int_2_oe() {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        assertEquals(2, f.getDenominator());
    }

    @Test
    public void testFactory_int_int_int_3_oe() {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 0, 2);
        assertEquals(4, f.getNumerator());
    }

    @Test
    public void testFactory_int_int_int_4_oe() {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 0, 2);
        // removed other assertion
        assertEquals(2, f.getDenominator());
    }

    @Test
    public void testFactory_int_int_int_5_oe() {
        Fraction f;

        // zero
        f = Fraction.getFraction(0, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 0, 2);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1, 2);
        assertEquals(1, f.getNumerator());
    }

    @Test
    public void testFactory_int_int_int_6_oe() {
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
        assertEquals(2, f.getDenominator());
    }

    @Test
    public void testFactory_int_int_int_7_oe() {
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
        assertEquals(3, f.getNumerator());
    }

    @Test
    public void testFactory_int_int_int_8_oe() {
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
        assertEquals(2, f.getDenominator());
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_int_12_oe() {
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
        assertEquals(-16, f.getNumerator());
    }

    @Test
    public void testFactory_int_int_int_13_oe() {
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
        assertEquals(10, f.getDenominator());
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_int_int_int_22_oe() {
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
        assertEquals(-Integer.MAX_VALUE, f.getNumerator());
    }

    @Test
    public void testFactory_int_int_int_23_oe() {
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
        assertEquals(Integer.MAX_VALUE, f.getDenominator());
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testFactory_String_1_oe() throws Exception {
        try {
    Fraction.getFraction(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testFactory_String_double_1_oe() {
        Fraction f;

        f = Fraction.getFraction("0.0");
        assertEquals(0, f.getNumerator());
    }

    @Test
    public void testFactory_String_double_2_oe() {
        Fraction f;

        f = Fraction.getFraction("0.0");
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testFactory_String_double_3_oe() {
        Fraction f;

        f = Fraction.getFraction("0.0");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("0.2");
        assertEquals(1, f.getNumerator());
    }

    @Test
    public void testFactory_String_double_4_oe() {
        Fraction f;

        f = Fraction.getFraction("0.0");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("0.2");
        // removed other assertion
        assertEquals(5, f.getDenominator());
    }

    @Test
    public void testFactory_String_double_5_oe() {
        Fraction f;

        f = Fraction.getFraction("0.0");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("0.2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("0.5");
        assertEquals(1, f.getNumerator());
    }

    @Test
    public void testFactory_String_double_6_oe() {
        Fraction f;

        f = Fraction.getFraction("0.0");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("0.2");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("0.5");
        // removed other assertion
        assertEquals(2, f.getDenominator());
    }

    @Test
    public void testFactory_String_double_7_oe() {
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
        assertEquals(2, f.getNumerator());
    }

    @Test
    public void testFactory_String_double_8_oe() {
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
        assertEquals(3, f.getDenominator());
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
    fail("NumberFormatException");
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
    fail("NumberFormatException");
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
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    public void testFactory_String_improper_1_oe() {
        Fraction f;

        f = Fraction.getFraction("0/1");
        assertEquals(0, f.getNumerator());
    }

    @Test
    public void testFactory_String_improper_2_oe() {
        Fraction f;

        f = Fraction.getFraction("0/1");
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testFactory_String_improper_3_oe() {
        Fraction f;

        f = Fraction.getFraction("0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1/5");
        assertEquals(1, f.getNumerator());
    }

    @Test
    public void testFactory_String_improper_4_oe() {
        Fraction f;

        f = Fraction.getFraction("0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1/5");
        // removed other assertion
        assertEquals(5, f.getDenominator());
    }

    @Test
    public void testFactory_String_improper_5_oe() {
        Fraction f;

        f = Fraction.getFraction("0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1/5");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1/2");
        assertEquals(1, f.getNumerator());
    }

    @Test
    public void testFactory_String_improper_6_oe() {
        Fraction f;

        f = Fraction.getFraction("0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1/5");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1/2");
        // removed other assertion
        assertEquals(2, f.getDenominator());
    }

    @Test
    public void testFactory_String_improper_7_oe() {
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
        assertEquals(2, f.getNumerator());
    }

    @Test
    public void testFactory_String_improper_8_oe() {
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
        assertEquals(3, f.getDenominator());
    }

    @Test
    public void testFactory_String_improper_9_oe() {
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
        assertEquals(7, f.getNumerator());
    }

    @Test
    public void testFactory_String_improper_10_oe() {
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
        assertEquals(3, f.getDenominator());
    }

    @Test
    public void testFactory_String_improper_11_oe() {
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
        assertEquals(2, f.getNumerator());
    }

    @Test
    public void testFactory_String_improper_12_oe() {
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
        assertEquals(4, f.getDenominator());
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
    fail("NumberFormatException");
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
    fail("NumberFormatException");
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
    fail("NumberFormatException");
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
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    public void testFactory_String_proper_1_oe() {
        Fraction f;

        f = Fraction.getFraction("0 0/1");
        assertEquals(0, f.getNumerator());
    }

    @Test
    public void testFactory_String_proper_2_oe() {
        Fraction f;

        f = Fraction.getFraction("0 0/1");
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testFactory_String_proper_3_oe() {
        Fraction f;

        f = Fraction.getFraction("0 0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1 1/5");
        assertEquals(6, f.getNumerator());
    }

    @Test
    public void testFactory_String_proper_4_oe() {
        Fraction f;

        f = Fraction.getFraction("0 0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1 1/5");
        // removed other assertion
        assertEquals(5, f.getDenominator());
    }

    @Test
    public void testFactory_String_proper_5_oe() {
        Fraction f;

        f = Fraction.getFraction("0 0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1 1/5");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("7 1/2");
        assertEquals(15, f.getNumerator());
    }

    @Test
    public void testFactory_String_proper_6_oe() {
        Fraction f;

        f = Fraction.getFraction("0 0/1");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("1 1/5");
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction("7 1/2");
        // removed other assertion
        assertEquals(2, f.getDenominator());
    }

    @Test
    public void testFactory_String_proper_7_oe() {
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
        assertEquals(6, f.getNumerator());
    }

    @Test
    public void testFactory_String_proper_8_oe() {
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
        assertEquals(4, f.getDenominator());
    }

    @Test
    public void testFactory_String_proper_9_oe() {
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
        assertEquals(-15, f.getNumerator());
    }

    @Test
    public void testFactory_String_proper_10_oe() {
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
        assertEquals(2, f.getDenominator());
    }

    @Test
    public void testFactory_String_proper_11_oe() {
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
        assertEquals(-6, f.getNumerator());
    }

    @Test
    public void testFactory_String_proper_12_oe() {
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
        assertEquals(4, f.getDenominator());
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
    fail("NumberFormatException");
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
    fail("NumberFormatException");
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
    fail("NumberFormatException");
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
    fail("NumberFormatException");
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
    fail("NumberFormatException");
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
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    public void testGets_1_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5, 6);
        assertEquals(23, f.getNumerator());
    }

    @Test
    public void testGets_2_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5, 6);
        // removed other assertion
        assertEquals(3, f.getProperWhole());
    }

    @Test
    public void testGets_3_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5, 6);
        // removed other assertion
        // removed other assertion
        assertEquals(5, f.getProperNumerator());
    }

    @Test
    public void testGets_4_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, f.getDenominator());
    }

    @Test
    public void testGets_5_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-3, 5, 6);
        assertEquals(-23, f.getNumerator());
    }

    @Test
    public void testGets_6_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-3, 5, 6);
        // removed other assertion
        assertEquals(-3, f.getProperWhole());
    }

    @Test
    public void testGets_7_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-3, 5, 6);
        // removed other assertion
        // removed other assertion
        assertEquals(5, f.getProperNumerator());
    }

    @Test
    public void testGets_8_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-3, 5, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, f.getDenominator());
    }

    @Test
    public void testGets_9_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-3, 5, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(Integer.MIN_VALUE, 0, 1);
        assertEquals(Integer.MIN_VALUE, f.getNumerator());
    }

    @Test
    public void testGets_10_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-3, 5, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(Integer.MIN_VALUE, 0, 1);
        // removed other assertion
        assertEquals(Integer.MIN_VALUE, f.getProperWhole());
    }

    @Test
    public void testGets_11_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-3, 5, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(Integer.MIN_VALUE, 0, 1);
        // removed other assertion
        // removed other assertion
        assertEquals(0, f.getProperNumerator());
    }

    @Test
    public void testGets_12_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-3, 5, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(Integer.MIN_VALUE, 0, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testHashCode_1_oe() {
        final Fraction f1 = Fraction.getFraction(3, 5);
        Fraction f2 = Fraction.getFraction(3, 5);

        assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    public void testHashCode_2_oe() {
        final Fraction f1 = Fraction.getFraction(3, 5);
        Fraction f2 = Fraction.getFraction(3, 5);

        // removed other assertion

        f2 = Fraction.getFraction(2, 5);
        assertTrue(f1.hashCode() != f2.hashCode());
    }

    @Test
    public void testHashCode_3_oe() {
        final Fraction f1 = Fraction.getFraction(3, 5);
        Fraction f2 = Fraction.getFraction(3, 5);

        // removed other assertion

        f2 = Fraction.getFraction(2, 5);
        // removed other assertion

        f2 = Fraction.getFraction(6, 10);
        assertTrue(f1.hashCode() != f2.hashCode());
    }

    @Test
    public void testInvert_1_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        f = f.invert();
        assertEquals(75, f.getNumerator());
    }

    @Test
    public void testInvert_2_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        f = f.invert();
        // removed other assertion
        assertEquals(50, f.getDenominator());
    }

    @Test
    public void testInvert_3_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        f = f.invert();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(4, 3);
        f = f.invert();
        assertEquals(3, f.getNumerator());
    }

    @Test
    public void testInvert_4_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        f = f.invert();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(4, 3);
        f = f.invert();
        // removed other assertion
        assertEquals(4, f.getDenominator());
    }

    @Test
    public void testInvert_5_oe() {
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
        assertEquals(-47, f.getNumerator());
    }

    @Test
    public void testInvert_6_oe() {
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
        assertEquals(15, f.getDenominator());
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testInvert_9_oe() {
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
        // removed other assertion

        f = Fraction.getFraction(Integer.MAX_VALUE, 1);
        f = f.invert();
        assertEquals(1, f.getNumerator());
    }

    @Test
    public void testInvert_10_oe() {
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
        // removed other assertion

        f = Fraction.getFraction(Integer.MAX_VALUE, 1);
        f = f.invert();
        // removed other assertion
        assertEquals(Integer.MAX_VALUE, f.getDenominator());
    }

    @Test
    public void testMultiply_1_oe() {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.multiplyBy(f2);
        assertEquals(6, f.getNumerator());
    }

    @Test
    public void testMultiply_2_oe() {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(2, 5);
        f = f1.multiplyBy(f2);
        // removed other assertion
        assertEquals(25, f.getDenominator());
    }

    @Test
    public void testMultiply_3_oe() {
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
        assertEquals(9, f.getNumerator());
    }

    @Test
    public void testMultiply_4_oe() {
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
        assertEquals(25, f.getDenominator());
    }

    @Test
    public void testMultiply_5_oe() {
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
        assertEquals(27, f.getNumerator());
    }

    @Test
    public void testMultiply_6_oe() {
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
        assertEquals(125, f.getDenominator());
    }

    @Test
    public void testMultiply_7_oe() {
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
        assertEquals(-6, f.getNumerator());
    }

    @Test
    public void testMultiply_8_oe() {
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
        assertEquals(25, f.getDenominator());
    }

    @Test
    public void testMultiply_9_oe() {
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
        assertEquals(6, f.getNumerator());
    }

    @Test
    public void testMultiply_10_oe() {
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
        assertEquals(25, f.getDenominator());
    }

    @Test
    public void testMultiply_11_oe() {
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
        assertSame(Fraction.ZERO, f);
    }

    @Test
    public void testMultiply_12_oe() {
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
        assertEquals(2, f.getNumerator());
    }

    @Test
    public void testMultiply_13_oe() {
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
        assertEquals(7, f.getDenominator());
    }

    @Test
    public void testMultiply_14_oe() {
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
        assertEquals(Integer.MIN_VALUE, f.getNumerator());
    }

    @Test
    public void testMultiply_15_oe() {
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
        assertEquals(1, f.getDenominator());
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
    fail("NullPointerException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testNegate_1_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        f = f.negate();
        assertEquals(-50, f.getNumerator());
    }

    @Test
    public void testNegate_2_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        f = f.negate();
        // removed other assertion
        assertEquals(75, f.getDenominator());
    }

    @Test
    public void testNegate_3_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-50, 75);
        f = f.negate();
        assertEquals(50, f.getNumerator());
    }

    @Test
    public void testNegate_4_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        f = f.negate();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-50, 75);
        f = f.negate();
        // removed other assertion
        assertEquals(75, f.getDenominator());
    }

    @Test
    public void testNegate_5_oe() {
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
        assertEquals(Integer.MIN_VALUE+2, f.getNumerator());
    }

    @Test
    public void testNegate_6_oe() {
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
        assertEquals(Integer.MAX_VALUE, f.getDenominator());
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
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testPow_1_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        assertEquals(Fraction.ONE, f.pow(0));
    }

    @Test
    public void testPow_2_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        assertSame(f, f.pow(1));
    }

    @Test
    public void testPow_3_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        // removed other assertion
        assertEquals(f, f.pow(1));
    }

    @Test
    public void testPow_4_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(2);
        assertEquals(9, f.getNumerator());
    }

    @Test
    public void testPow_5_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(3, 5);
        f = f.pow(2);
        // removed other assertion
        assertEquals(25, f.getDenominator());
    }

    @Test
    public void testPow_6_oe() {
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
        assertEquals(27, f.getNumerator());
    }

    @Test
    public void testPow_7_oe() {
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
        assertEquals(125, f.getDenominator());
    }

    @Test
    public void testPow_8_oe() {
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
        assertEquals(5, f.getNumerator());
    }

    @Test
    public void testPow_9_oe() {
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
        assertEquals(3, f.getDenominator());
    }

    @Test
    public void testPow_10_oe() {
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
        assertEquals(25, f.getNumerator());
    }

    @Test
    public void testPow_11_oe() {
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
        assertEquals(9, f.getDenominator());
    }

    @Test
    public void testPow_12_oe() {
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
        assertEquals(Fraction.ONE, f.pow(0));
    }

    @Test
    public void testPow_13_oe() {
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
        assertEquals(f, f.pow(1));
    }

    @Test
    public void testPow_14_oe() {
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
        assertNotEquals(f.pow(1), Fraction.getFraction(3, 5));
    }

    @Test
    public void testPow_15_oe() {
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
        assertEquals(9, f.getNumerator());
    }

    @Test
    public void testPow_16_oe() {
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
        assertEquals(25, f.getDenominator());
    }

    @Test
    public void testPow_17_oe() {
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
        assertEquals(27, f.getNumerator());
    }

    @Test
    public void testPow_18_oe() {
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
        assertEquals(125, f.getDenominator());
    }

    @Test
    public void testPow_19_oe() {
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
        assertEquals(10, f.getNumerator());
    }

    @Test
    public void testPow_20_oe() {
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
        assertEquals(6, f.getDenominator());
    }

    @Test
    public void testPow_21_oe() {
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
        assertEquals(25, f.getNumerator());
    }

    @Test
    public void testPow_22_oe() {
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
        assertEquals(9, f.getDenominator());
    }

    @Test
    public void testPow_23_oe() {
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
        assertEquals(0, f.compareTo(Fraction.ZERO));
    }

    @Test
    public void testPow_24_oe() {
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
        assertEquals(0, f.getNumerator());
    }

    @Test
    public void testPow_25_oe() {
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
        assertEquals(1231, f.getDenominator());
    }

    @Test
    public void testPow_26_oe() {
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
        assertEquals(0, f.compareTo(Fraction.ZERO));
    }

    @Test
    public void testPow_27_oe() {
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
        assertEquals(0, f.getNumerator());
    }

    @Test
    public void testPow_28_oe() {
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
        assertEquals(1, f.getDenominator());
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testPow_31_oe() {
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
        assertEquals(f, Fraction.ONE);
    }

    @Test
    public void testPow_32_oe() {
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
        assertEquals(f, Fraction.ONE);
    }

    @Test
    public void testPow_33_oe() {
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
        assertEquals(f, Fraction.ONE);
    }

    @Test
    public void testPow_34_oe() {
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
        assertEquals(f, Fraction.ONE);
    }

    @Test
    public void testPow_35_oe() {
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
        assertEquals(f, Fraction.ONE);
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testReduce_1_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        assertEquals(2, result.getNumerator());
    }

    @Test
    public void testReduce_2_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        assertEquals(3, result.getDenominator());
    }

    @Test
    public void testReduce_3_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        assertEquals(2, result.getNumerator());
    }

    @Test
    public void testReduce_4_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        // removed other assertion
        assertEquals(3, result.getDenominator());
    }

    @Test
    public void testReduce_5_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, -3);
        result = f.reduce();
        assertEquals(-2, result.getNumerator());
    }

    @Test
    public void testReduce_6_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, -3);
        result = f.reduce();
        // removed other assertion
        assertEquals(3, result.getDenominator());
    }

    @Test
    public void testReduce_7_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, 3);
        result = f.reduce();
        assertEquals(-2, result.getNumerator());
    }

    @Test
    public void testReduce_8_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, 3);
        result = f.reduce();
        // removed other assertion
        assertEquals(3, result.getDenominator());
    }

    @Test
    public void testReduce_9_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        assertSame(f, result);
    }

    @Test
    public void testReduce_10_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 3);
        result = f.reduce();
        assertEquals(2, result.getNumerator());
    }

    @Test
    public void testReduce_11_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 3);
        result = f.reduce();
        // removed other assertion
        assertEquals(3, result.getDenominator());
    }

    @Test
    public void testReduce_12_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        assertSame(f, result);
    }

    @Test
    public void testReduce_13_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1);
        result = f.reduce();
        assertEquals(0, result.getNumerator());
    }

    @Test
    public void testReduce_14_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1);
        result = f.reduce();
        // removed other assertion
        assertEquals(1, result.getDenominator());
    }

    @Test
    public void testReduce_15_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        assertSame(f, result);
    }

    @Test
    public void testReduce_16_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 100);
        result = f.reduce();
        assertEquals(0, result.getNumerator());
    }

    @Test
    public void testReduce_17_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 100);
        result = f.reduce();
        // removed other assertion
        assertEquals(1, result.getDenominator());
    }

    @Test
    public void testReduce_18_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 100);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        assertSame(result, Fraction.ZERO);
    }

    @Test
    public void testReduce_19_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 100);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(Integer.MIN_VALUE, 2);
        result = f.reduce();
        assertEquals(Integer.MIN_VALUE / 2, result.getNumerator());
    }

    @Test
    public void testReduce_20_oe() {
        Fraction f;

        f = Fraction.getFraction(50, 75);
        Fraction result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, -3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(-2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(2, 3);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 1);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(0, 100);
        result = f.reduce();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(Integer.MIN_VALUE, 2);
        result = f.reduce();
        // removed other assertion
        assertEquals(1, result.getDenominator());
    }

    @Test
    public void testReducedFactory_int_int_1_oe() {
        Fraction f;

        // zero
        f = Fraction.getReducedFraction(0, 1);
        assertEquals(0, f.getNumerator());
    }

    @Test
    public void testReducedFactory_int_int_2_oe() {
        Fraction f;

        // zero
        f = Fraction.getReducedFraction(0, 1);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testReducedFactory_int_int_3_oe() {
        Fraction f;

        // zero
        f = Fraction.getReducedFraction(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getReducedFraction(1, 1);
        assertEquals(1, f.getNumerator());
    }

    @Test
    public void testReducedFactory_int_int_4_oe() {
        Fraction f;

        // zero
        f = Fraction.getReducedFraction(0, 1);
        // removed other assertion
        // removed other assertion

        // normal
        f = Fraction.getReducedFraction(1, 1);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testReducedFactory_int_int_5_oe() {
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
        assertEquals(2, f.getNumerator());
    }

    @Test
    public void testReducedFactory_int_int_6_oe() {
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
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testReducedFactory_int_int_7_oe() {
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
        assertEquals(22, f.getNumerator());
    }

    @Test
    public void testReducedFactory_int_int_8_oe() {
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
        assertEquals(7, f.getDenominator());
    }

    @Test
    public void testReducedFactory_int_int_9_oe() {
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
        assertEquals(-3, f.getNumerator());
    }

    @Test
    public void testReducedFactory_int_int_10_oe() {
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
        assertEquals(5, f.getDenominator());
    }

    @Test
    public void testReducedFactory_int_int_11_oe() {
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
        assertEquals(-3, f.getNumerator());
    }

    @Test
    public void testReducedFactory_int_int_12_oe() {
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
        assertEquals(5, f.getDenominator());
    }

    @Test
    public void testReducedFactory_int_int_13_oe() {
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
        assertEquals(3, f.getNumerator());
    }

    @Test
    public void testReducedFactory_int_int_14_oe() {
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
        assertEquals(5, f.getDenominator());
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testReducedFactory_int_int_18_oe() {
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
        assertEquals(0, f.getNumerator());
    }

    @Test
    public void testReducedFactory_int_int_19_oe() {
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
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testReducedFactory_int_int_20_oe() {
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
        assertEquals(1, f.getNumerator());
    }

    @Test
    public void testReducedFactory_int_int_21_oe() {
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
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testReducedFactory_int_int_22_oe() {
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
        assertEquals(1, f.getNumerator());
    }

    @Test
    public void testReducedFactory_int_int_23_oe() {
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
        assertEquals(2, f.getDenominator());
    }

    @Test
    public void testReducedFactory_int_int_24_oe() {
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
        assertEquals(3, f.getNumerator());
    }

    @Test
    public void testReducedFactory_int_int_25_oe() {
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
        assertEquals(2, f.getDenominator());
    }

    @Test
    public void testReducedFactory_int_int_26_oe() {
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
        assertEquals(11, f.getNumerator());
    }

    @Test
    public void testReducedFactory_int_int_27_oe() {
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
        assertEquals(2, f.getDenominator());
    }

    @Test
    public void testReducedFactory_int_int_28_oe() {
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
        assertEquals(1, f.getNumerator());
    }

    @Test
    public void testReducedFactory_int_int_29_oe() {
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
        assertEquals(-(Integer.MIN_VALUE / 2), f.getDenominator());
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
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testReducedFactory_int_int_31_oe() {
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
        // removed other assertion

        // LANG-662
        f = Fraction.getReducedFraction(Integer.MIN_VALUE, 2);
        assertEquals(Integer.MIN_VALUE / 2, f.getNumerator());
    }

    @Test
    public void testReducedFactory_int_int_32_oe() {
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
        // removed other assertion

        // LANG-662
        f = Fraction.getReducedFraction(Integer.MIN_VALUE, 2);
        // removed other assertion
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testSubtract_1_oe() {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.subtract(f2);
        assertEquals(2, f.getNumerator());
    }

    @Test
    public void testSubtract_2_oe() {
        Fraction f;
        Fraction f1;
        Fraction f2;

        f1 = Fraction.getFraction(3, 5);
        f2 = Fraction.getFraction(1, 5);
        f = f1.subtract(f2);
        // removed other assertion
        assertEquals(5, f.getDenominator());
    }

    @Test
    public void testSubtract_3_oe() {
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
        assertEquals(1, f.getNumerator());
    }

    @Test
    public void testSubtract_4_oe() {
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
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testSubtract_5_oe() {
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
        assertEquals(0, f.getNumerator());
    }

    @Test
    public void testSubtract_6_oe() {
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
        assertEquals(1, f.getDenominator());
    }

    @Test
    public void testSubtract_7_oe() {
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
        assertEquals(7, f.getNumerator());
    }

    @Test
    public void testSubtract_8_oe() {
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
        assertEquals(5, f.getDenominator());
    }

    @Test
    public void testSubtract_9_oe() {
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
        assertEquals(-4, f.getNumerator());
    }

    @Test
    public void testSubtract_10_oe() {
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
        assertEquals(5, f.getDenominator());
    }

    @Test
    public void testSubtract_11_oe() {
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
        assertEquals(4, f.getNumerator());
    }

    @Test
    public void testSubtract_12_oe() {
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
        assertEquals(5, f.getDenominator());
    }

    @Test
    public void testSubtract_13_oe() {
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
        assertEquals(1, f.getNumerator());
    }

    @Test
    public void testSubtract_14_oe() {
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
        assertEquals(10, f.getDenominator());
    }

    @Test
    public void testSubtract_15_oe() {
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
        assertSame(f2, f);
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
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testSubtract_17_oe() {
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
        assertEquals(-13085, f.getNumerator());
    }

    @Test
    public void testSubtract_18_oe() {
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
        assertEquals(1934917632, f.getDenominator());
    }

    @Test
    public void testSubtract_19_oe() {
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
        assertEquals(Integer.MIN_VALUE+1, f.getNumerator());
    }

    @Test
    public void testSubtract_20_oe() {
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
        assertEquals(3, f.getDenominator());
    }

    @Test
    public void testSubtract_21_oe() {
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
        assertEquals(Integer.MAX_VALUE-1, f.getNumerator());
    }

    @Test
    public void testSubtract_22_oe() {
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
        assertEquals(1, f.getDenominator());
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
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
    fail("ArithmeticException");
} catch (ArithmeticException e) {
}
    }

    @Test
    public void testToProperString_1_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toProperString();
        assertEquals("3/5", str);
    }

    @Test
    public void testToProperString_2_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toProperString();
        // removed other assertion
        assertSame(str, f.toProperString());
    }

    @Test
    public void testToProperString_3_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toProperString();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(7, 5);
        assertEquals("1 2/5", f.toProperString());
    }

    @Test
    public void testToProperString_4_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toProperString();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(7, 5);
        // removed other assertion

        f = Fraction.getFraction(14, 10);
        assertEquals("1 4/10", f.toProperString());
    }

    @Test
    public void testToProperString_5_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toProperString();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(7, 5);
        // removed other assertion

        f = Fraction.getFraction(14, 10);
        // removed other assertion

        f = Fraction.getFraction(4, 2);
        assertEquals("2", f.toProperString());
    }

    @Test
    public void testToProperString_6_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toProperString();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(7, 5);
        // removed other assertion

        f = Fraction.getFraction(14, 10);
        // removed other assertion

        f = Fraction.getFraction(4, 2);
        // removed other assertion

        f = Fraction.getFraction(0, 2);
        assertEquals("0", f.toProperString());
    }

    @Test
    public void testToProperString_7_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toProperString();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(7, 5);
        // removed other assertion

        f = Fraction.getFraction(14, 10);
        // removed other assertion

        f = Fraction.getFraction(4, 2);
        // removed other assertion

        f = Fraction.getFraction(0, 2);
        // removed other assertion

        f = Fraction.getFraction(2, 2);
        assertEquals("1", f.toProperString());
    }

    @Test
    public void testToProperString_8_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toProperString();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(7, 5);
        // removed other assertion

        f = Fraction.getFraction(14, 10);
        // removed other assertion

        f = Fraction.getFraction(4, 2);
        // removed other assertion

        f = Fraction.getFraction(0, 2);
        // removed other assertion

        f = Fraction.getFraction(2, 2);
        // removed other assertion

        f = Fraction.getFraction(-7, 5);
        assertEquals("-1 2/5", f.toProperString());
    }

    @Test
    public void testToProperString_9_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toProperString();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(7, 5);
        // removed other assertion

        f = Fraction.getFraction(14, 10);
        // removed other assertion

        f = Fraction.getFraction(4, 2);
        // removed other assertion

        f = Fraction.getFraction(0, 2);
        // removed other assertion

        f = Fraction.getFraction(2, 2);
        // removed other assertion

        f = Fraction.getFraction(-7, 5);
        // removed other assertion

        f = Fraction.getFraction(Integer.MIN_VALUE, 0, 1);
        assertEquals("-2147483648", f.toProperString());
    }

    @Test
    public void testToProperString_10_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toProperString();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(7, 5);
        // removed other assertion

        f = Fraction.getFraction(14, 10);
        // removed other assertion

        f = Fraction.getFraction(4, 2);
        // removed other assertion

        f = Fraction.getFraction(0, 2);
        // removed other assertion

        f = Fraction.getFraction(2, 2);
        // removed other assertion

        f = Fraction.getFraction(-7, 5);
        // removed other assertion

        f = Fraction.getFraction(Integer.MIN_VALUE, 0, 1);
        // removed other assertion

        f = Fraction.getFraction(-1, 1, Integer.MAX_VALUE);
        assertEquals("-1 1/2147483647", f.toProperString());
    }

    @Test
    public void testToProperString_11_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toProperString();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(7, 5);
        // removed other assertion

        f = Fraction.getFraction(14, 10);
        // removed other assertion

        f = Fraction.getFraction(4, 2);
        // removed other assertion

        f = Fraction.getFraction(0, 2);
        // removed other assertion

        f = Fraction.getFraction(2, 2);
        // removed other assertion

        f = Fraction.getFraction(-7, 5);
        // removed other assertion

        f = Fraction.getFraction(Integer.MIN_VALUE, 0, 1);
        // removed other assertion

        f = Fraction.getFraction(-1, 1, Integer.MAX_VALUE);
        // removed other assertion

        assertEquals("-1", Fraction.getFraction(-1).toProperString());
    }

    @Test
    public void testToString_1_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toString();
        assertEquals("3/5", str);
    }

    @Test
    public void testToString_2_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toString();
        // removed other assertion
        assertSame(str, f.toString());
    }

    @Test
    public void testToString_3_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toString();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(7, 5);
        assertEquals("7/5", f.toString());
    }

    @Test
    public void testToString_4_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toString();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(7, 5);
        // removed other assertion

        f = Fraction.getFraction(4, 2);
        assertEquals("4/2", f.toString());
    }

    @Test
    public void testToString_5_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toString();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(7, 5);
        // removed other assertion

        f = Fraction.getFraction(4, 2);
        // removed other assertion

        f = Fraction.getFraction(0, 2);
        assertEquals("0/2", f.toString());
    }

    @Test
    public void testToString_6_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toString();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(7, 5);
        // removed other assertion

        f = Fraction.getFraction(4, 2);
        // removed other assertion

        f = Fraction.getFraction(0, 2);
        // removed other assertion

        f = Fraction.getFraction(2, 2);
        assertEquals("2/2", f.toString());
    }

    @Test
    public void testToString_7_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toString();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(7, 5);
        // removed other assertion

        f = Fraction.getFraction(4, 2);
        // removed other assertion

        f = Fraction.getFraction(0, 2);
        // removed other assertion

        f = Fraction.getFraction(2, 2);
        // removed other assertion

        f = Fraction.getFraction(Integer.MIN_VALUE, 0, 1);
        assertEquals("-2147483648/1", f.toString());
    }

    @Test
    public void testToString_8_oe() {
        Fraction f;

        f = Fraction.getFraction(3, 5);
        final String str = f.toString();
        // removed other assertion
        // removed other assertion

        f = Fraction.getFraction(7, 5);
        // removed other assertion

        f = Fraction.getFraction(4, 2);
        // removed other assertion

        f = Fraction.getFraction(0, 2);
        // removed other assertion

        f = Fraction.getFraction(2, 2);
        // removed other assertion

        f = Fraction.getFraction(Integer.MIN_VALUE, 0, 1);
        // removed other assertion

        f = Fraction.getFraction(-1, 1, Integer.MAX_VALUE);
        assertEquals("-2147483648/2147483647", f.toString());
    }

}
