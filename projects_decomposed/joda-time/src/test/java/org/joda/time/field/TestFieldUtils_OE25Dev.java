/*
 *  Copyright 2001-2005 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.time.field;

import java.math.RoundingMode;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 *
 * @author Brian S O'Neill
 */
public class TestFieldUtils_OE25Dev extends TestCase {
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestFieldUtils.class);
    }

    public TestFieldUtils_OE25Dev(String name) {
        super(name);
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testSafeAddInt_1_oe() {
        assertEquals(0, FieldUtils.safeAdd(0, 0));
    }

    public void testSafeAddInt_2_oe() {
        // removed other assertion

        assertEquals(5, FieldUtils.safeAdd(2, 3));
    }

    public void testSafeAddInt_3_oe() {
        // removed other assertion

        // removed other assertion
        assertEquals(-1, FieldUtils.safeAdd(2, -3));
    }

    public void testSafeAddInt_4_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(1, FieldUtils.safeAdd(-2, 3));
    }

    public void testSafeAddInt_5_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-5, FieldUtils.safeAdd(-2, -3));
    }

    public void testSafeAddInt_6_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(Integer.MAX_VALUE - 1, FieldUtils.safeAdd(Integer.MAX_VALUE, -1));
    }

    public void testSafeAddInt_7_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(Integer.MIN_VALUE + 1, FieldUtils.safeAdd(Integer.MIN_VALUE, 1));
    }

    public void testSafeAddInt_8_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(-1, FieldUtils.safeAdd(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    public void testSafeAddInt_9_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-1, FieldUtils.safeAdd(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }

    public void testSafeAddLong_1_oe() {
        assertEquals(0L, FieldUtils.safeAdd(0L, 0L));
    }

    public void testSafeAddLong_2_oe() {
        // removed other assertion

        assertEquals(5L, FieldUtils.safeAdd(2L, 3L));
    }

    public void testSafeAddLong_3_oe() {
        // removed other assertion

        // removed other assertion
        assertEquals(-1L, FieldUtils.safeAdd(2L, -3L));
    }

    public void testSafeAddLong_4_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(1L, FieldUtils.safeAdd(-2L, 3L));
    }

    public void testSafeAddLong_5_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-5L, FieldUtils.safeAdd(-2L, -3L));
    }

    public void testSafeAddLong_6_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(Long.MAX_VALUE - 1, FieldUtils.safeAdd(Long.MAX_VALUE, -1L));
    }

    public void testSafeAddLong_7_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(Long.MIN_VALUE + 1, FieldUtils.safeAdd(Long.MIN_VALUE, 1L));
    }

    public void testSafeAddLong_8_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(-1, FieldUtils.safeAdd(Long.MIN_VALUE, Long.MAX_VALUE));
    }

    public void testSafeAddLong_9_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-1, FieldUtils.safeAdd(Long.MAX_VALUE, Long.MIN_VALUE));
    }

    public void testSafeSubtractLong_1_oe() {
        assertEquals(0L, FieldUtils.safeSubtract(0L, 0L));
    }

    public void testSafeSubtractLong_2_oe() {
        // removed other assertion

        assertEquals(-1L, FieldUtils.safeSubtract(2L, 3L));
    }

    public void testSafeSubtractLong_3_oe() {
        // removed other assertion

        // removed other assertion
        assertEquals(5L, FieldUtils.safeSubtract(2L, -3L));
    }

    public void testSafeSubtractLong_4_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(-5L, FieldUtils.safeSubtract(-2L, 3L));
    }

    public void testSafeSubtractLong_5_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1L, FieldUtils.safeSubtract(-2L, -3L));
    }

    public void testSafeSubtractLong_6_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(Long.MAX_VALUE - 1, FieldUtils.safeSubtract(Long.MAX_VALUE, 1L));
    }

    public void testSafeSubtractLong_7_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(Long.MIN_VALUE + 1, FieldUtils.safeSubtract(Long.MIN_VALUE, -1L));
    }

    public void testSafeSubtractLong_8_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(0, FieldUtils.safeSubtract(Long.MIN_VALUE, Long.MIN_VALUE));
    }

    public void testSafeSubtractLong_9_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, FieldUtils.safeSubtract(Long.MAX_VALUE, Long.MAX_VALUE));
    }

    public void testSafeMultiplyLongLong_1_oe() {
        assertEquals(0L, FieldUtils.safeMultiply(0L, 0L));
    }

    public void testSafeMultiplyLongLong_2_oe() {
        // removed other assertion
        
        assertEquals(1L, FieldUtils.safeMultiply(1L, 1L));
    }

    public void testSafeMultiplyLongLong_3_oe() {
        // removed other assertion
        
        // removed other assertion
        assertEquals(3L, FieldUtils.safeMultiply(1L, 3L));
    }

    public void testSafeMultiplyLongLong_4_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(3L, FieldUtils.safeMultiply(3L, 1L));
    }

    public void testSafeMultiplyLongLong_5_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(6L, FieldUtils.safeMultiply(2L, 3L));
    }

    public void testSafeMultiplyLongLong_6_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(-6L, FieldUtils.safeMultiply(2L, -3L));
    }

    public void testSafeMultiplyLongLong_7_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(-6L, FieldUtils.safeMultiply(-2L, 3L));
    }

    public void testSafeMultiplyLongLong_8_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6L, FieldUtils.safeMultiply(-2L, -3L));
    }

    public void testSafeMultiplyLongLong_9_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(Long.MAX_VALUE, FieldUtils.safeMultiply(Long.MAX_VALUE, 1L));
    }

    public void testSafeMultiplyLongLong_10_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(Long.MIN_VALUE, FieldUtils.safeMultiply(Long.MIN_VALUE, 1L));
    }

    public void testSafeMultiplyLongLong_11_oe() {
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
        assertEquals(-Long.MAX_VALUE, FieldUtils.safeMultiply(Long.MAX_VALUE, -1L));
    }

    public void testSafeMultiplyLongInt_1_oe() {
        assertEquals(0L, FieldUtils.safeMultiply(0L, 0));
    }

    public void testSafeMultiplyLongInt_2_oe() {
        // removed other assertion
        
        assertEquals(1L, FieldUtils.safeMultiply(1L, 1));
    }

    public void testSafeMultiplyLongInt_3_oe() {
        // removed other assertion
        
        // removed other assertion
        assertEquals(3L, FieldUtils.safeMultiply(1L, 3));
    }

    public void testSafeMultiplyLongInt_4_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(3L, FieldUtils.safeMultiply(3L, 1));
    }

    public void testSafeMultiplyLongInt_5_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(6L, FieldUtils.safeMultiply(2L, 3));
    }

    public void testSafeMultiplyLongInt_6_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(-6L, FieldUtils.safeMultiply(2L, -3));
    }

    public void testSafeMultiplyLongInt_7_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(-6L, FieldUtils.safeMultiply(-2L, 3));
    }

    public void testSafeMultiplyLongInt_8_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6L, FieldUtils.safeMultiply(-2L, -3));
    }

    public void testSafeMultiplyLongInt_9_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(-1L * Integer.MIN_VALUE, FieldUtils.safeMultiply(-1L, Integer.MIN_VALUE));
    }

    public void testSafeMultiplyLongInt_10_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        
        assertEquals(Long.MAX_VALUE, FieldUtils.safeMultiply(Long.MAX_VALUE, 1));
    }

    public void testSafeMultiplyLongInt_11_oe() {
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
        assertEquals(Long.MIN_VALUE, FieldUtils.safeMultiply(Long.MIN_VALUE, 1));
    }

    public void testSafeMultiplyLongInt_12_oe() {
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
        assertEquals(-Long.MAX_VALUE, FieldUtils.safeMultiply(Long.MAX_VALUE, -1));
    }

    public void testSafeDivideLongLong_1_oe() {
        assertEquals(1L, FieldUtils.safeDivide(1L, 1L));
    }

    public void testSafeDivideLongLong_2_oe() {
        // removed other assertion
        
        assertEquals(1L, FieldUtils.safeDivide(3L, 3L));
    }

    public void testSafeDivideLongLong_3_oe() {
        // removed other assertion
        
        // removed other assertion
        assertEquals(0L, FieldUtils.safeDivide(1L, 3L));
    }

    public void testSafeDivideLongLong_4_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(3L, FieldUtils.safeDivide(3L, 1L));
    }

    public void testSafeDivideLongLong_5_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(1L, FieldUtils.safeDivide(5L, 3L));
    }

    public void testSafeDivideLongLong_6_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(-1L, FieldUtils.safeDivide(5L, -3L));
    }

    public void testSafeDivideLongLong_7_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        assertEquals(-1L, FieldUtils.safeDivide(-5L, 3L));
    }

    public void testSafeDivideLongLong_8_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1L, FieldUtils.safeDivide(-5L, -3L));
    }

    public void testSafeDivideLongLong_9_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(2L, FieldUtils.safeDivide(6L, 3L));
    }

    public void testSafeDivideLongLong_10_oe() {
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        assertEquals(-2L, FieldUtils.safeDivide(6L, -3L));
    }

    public void testSafeDivideLongLong_11_oe() {
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
        assertEquals(-2L, FieldUtils.safeDivide(-6L, 3L));
    }

    public void testSafeDivideLongLong_12_oe() {
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
        assertEquals(2L, FieldUtils.safeDivide(-6L, -3L));
    }

    public void testSafeDivideLongLong_13_oe() {
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
        
        assertEquals(2L, FieldUtils.safeDivide(7L, 3L));
    }

    public void testSafeDivideLongLong_14_oe() {
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
        assertEquals(-2L, FieldUtils.safeDivide(7L, -3L));
    }

    public void testSafeDivideLongLong_15_oe() {
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
        assertEquals(-2L, FieldUtils.safeDivide(-7L, 3L));
    }

    public void testSafeDivideLongLong_16_oe() {
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
        assertEquals(2L, FieldUtils.safeDivide(-7L, -3L));
    }

    public void testSafeDivideLongLong_17_oe() {
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
        
        assertEquals(Long.MAX_VALUE, FieldUtils.safeDivide(Long.MAX_VALUE, 1L));
    }

    public void testSafeDivideLongLong_18_oe() {
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
        assertEquals(Long.MIN_VALUE, FieldUtils.safeDivide(Long.MIN_VALUE, 1L));
    }

    public void testSafeDivideLongLong_19_oe() {
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
        assertEquals(-Long.MAX_VALUE, FieldUtils.safeDivide(Long.MAX_VALUE, -1L));
    }

    public void testSafeDivideRoundingModeLong_1_oe() {
        assertEquals(3L, FieldUtils.safeDivide(15L, 5L, RoundingMode.UNNECESSARY));
    }

    public void testSafeDivideRoundingModeLong_2_oe() {
        // removed other assertion
        assertEquals(59L, FieldUtils.safeDivide(179L, 3L, RoundingMode.FLOOR));
    }

    public void testSafeDivideRoundingModeLong_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(60L, FieldUtils.safeDivide(179L, 3L, RoundingMode.CEILING));
    }

    public void testSafeDivideRoundingModeLong_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60L, FieldUtils.safeDivide(179L, 3L, RoundingMode.HALF_UP));
    }

    public void testSafeDivideRoundingModeLong_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-60L, FieldUtils.safeDivide(-179L, 3L, RoundingMode.HALF_UP));
    }

    public void testSafeDivideRoundingModeLong_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(60L, FieldUtils.safeDivide(179L, 3L, RoundingMode.HALF_DOWN));
    }

    public void testSafeDivideRoundingModeLong_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-60L, FieldUtils.safeDivide(-179L, 3L, RoundingMode.HALF_DOWN));
    }

    public void testSafeDivideRoundingModeLong_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(Long.MAX_VALUE, FieldUtils.safeDivide(Long.MAX_VALUE, 1L, RoundingMode.UNNECESSARY));
    }

    public void testSafeDivideRoundingModeLong_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(Long.MIN_VALUE, FieldUtils.safeDivide(Long.MIN_VALUE, 1L, RoundingMode.UNNECESSARY));
    }

    public void testSafeDivideRoundingModeLong_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(-Long.MAX_VALUE, FieldUtils.safeDivide(Long.MAX_VALUE, -1L, RoundingMode.UNNECESSARY));
    }

}
