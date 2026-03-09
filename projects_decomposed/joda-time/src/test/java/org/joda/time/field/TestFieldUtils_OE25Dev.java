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
        return new TestSuite(TestFieldUtils_OE25Dev.class);
    }

    public TestFieldUtils_OE25Dev(String name) {
        super(name);
    }

    public void testSafeAddInt() {
        assertEquals(0,FieldUtils.safeAdd(0,0));

        assertEquals(5,FieldUtils.safeAdd(2,3));
        assertEquals(-1,FieldUtils.safeAdd(2,-3));
        assertEquals(1,FieldUtils.safeAdd(-2,3));
        assertEquals(-5,FieldUtils.safeAdd(-2,-3));

        assertEquals(Integer.MAX_VALUE - 1,FieldUtils.safeAdd(Integer.MAX_VALUE,-1));
        assertEquals(Integer.MIN_VALUE + 1,FieldUtils.safeAdd(Integer.MIN_VALUE,1));

        assertEquals(-1,FieldUtils.safeAdd(Integer.MIN_VALUE,Integer.MAX_VALUE));
        assertEquals(-1,FieldUtils.safeAdd(Integer.MAX_VALUE,Integer.MIN_VALUE));

        try {
            FieldUtils.safeAdd(Integer.MAX_VALUE, 1);
            fail();
        } catch (ArithmeticException e) {
        }

        try {
            FieldUtils.safeAdd(Integer.MAX_VALUE, 100);
            fail();
        } catch (ArithmeticException e) {
        }

        try {
            FieldUtils.safeAdd(Integer.MAX_VALUE, Integer.MAX_VALUE);
            fail();
        } catch (ArithmeticException e) {
        }

        try {
            FieldUtils.safeAdd(Integer.MIN_VALUE, -1);
            fail();
        } catch (ArithmeticException e) {
        }

        try {
            FieldUtils.safeAdd(Integer.MIN_VALUE, -100);
            fail();
        } catch (ArithmeticException e) {
        }

        try {
            FieldUtils.safeAdd(Integer.MIN_VALUE, Integer.MIN_VALUE);
            fail();
        } catch (ArithmeticException e) {
        }
    }

    public void testSafeAddLong() {
        assertEquals(0L,FieldUtils.safeAdd(0L,0L));

        assertEquals(5L,FieldUtils.safeAdd(2L,3L));
        assertEquals(-1L,FieldUtils.safeAdd(2L,-3L));
        assertEquals(1L,FieldUtils.safeAdd(-2L,3L));
        assertEquals(-5L,FieldUtils.safeAdd(-2L,-3L));

        assertEquals(Long.MAX_VALUE - 1,FieldUtils.safeAdd(Long.MAX_VALUE,-1L));
        assertEquals(Long.MIN_VALUE + 1,FieldUtils.safeAdd(Long.MIN_VALUE,1L));

        assertEquals(-1,FieldUtils.safeAdd(Long.MIN_VALUE,Long.MAX_VALUE));
        assertEquals(-1,FieldUtils.safeAdd(Long.MAX_VALUE,Long.MIN_VALUE));

        try {
            FieldUtils.safeAdd(Long.MAX_VALUE, 1L);
            fail();
        } catch (ArithmeticException e) {
        }

        try {
            FieldUtils.safeAdd(Long.MAX_VALUE, 100L);
            fail();
        } catch (ArithmeticException e) {
        }

        try {
            FieldUtils.safeAdd(Long.MAX_VALUE, Long.MAX_VALUE);
            fail();
        } catch (ArithmeticException e) {
        }

        try {
            FieldUtils.safeAdd(Long.MIN_VALUE, -1L);
            fail();
        } catch (ArithmeticException e) {
        }

        try {
            FieldUtils.safeAdd(Long.MIN_VALUE, -100L);
            fail();
        } catch (ArithmeticException e) {
        }

        try {
            FieldUtils.safeAdd(Long.MIN_VALUE, Long.MIN_VALUE);
            fail();
        } catch (ArithmeticException e) {
        }
    }

    public void testSafeSubtractLong() {
        assertEquals(0L,FieldUtils.safeSubtract(0L,0L));

        assertEquals(-1L,FieldUtils.safeSubtract(2L,3L));
        assertEquals(5L,FieldUtils.safeSubtract(2L,-3L));
        assertEquals(-5L,FieldUtils.safeSubtract(-2L,3L));
        assertEquals(1L,FieldUtils.safeSubtract(-2L,-3L));

        assertEquals(Long.MAX_VALUE - 1,FieldUtils.safeSubtract(Long.MAX_VALUE,1L));
        assertEquals(Long.MIN_VALUE + 1,FieldUtils.safeSubtract(Long.MIN_VALUE,-1L));

        assertEquals(0,FieldUtils.safeSubtract(Long.MIN_VALUE,Long.MIN_VALUE));
        assertEquals(0,FieldUtils.safeSubtract(Long.MAX_VALUE,Long.MAX_VALUE));

        try {
            FieldUtils.safeSubtract(Long.MIN_VALUE, 1L);
            fail();
        } catch (ArithmeticException e) {
        }

        try {
            FieldUtils.safeSubtract(Long.MIN_VALUE, 100L);
            fail();
        } catch (ArithmeticException e) {
        }

        try {
            FieldUtils.safeSubtract(Long.MIN_VALUE, Long.MAX_VALUE);
            fail();
        } catch (ArithmeticException e) {
        }

        try {
            FieldUtils.safeSubtract(Long.MAX_VALUE, -1L);
            fail();
        } catch (ArithmeticException e) {
        }

        try {
            FieldUtils.safeSubtract(Long.MAX_VALUE, -100L);
            fail();
        } catch (ArithmeticException e) {
        }

        try {
            FieldUtils.safeSubtract(Long.MAX_VALUE, Long.MIN_VALUE);
            fail();
        } catch (ArithmeticException e) {
        }
    }

    //-----------------------------------------------------------------------
    public void testSafeMultiplyLongLong() {
        assertEquals(0L,FieldUtils.safeMultiply(0L,0L));
        
        assertEquals(1L,FieldUtils.safeMultiply(1L,1L));
        assertEquals(3L,FieldUtils.safeMultiply(1L,3L));
        assertEquals(3L,FieldUtils.safeMultiply(3L,1L));
        
        assertEquals(6L,FieldUtils.safeMultiply(2L,3L));
        assertEquals(-6L,FieldUtils.safeMultiply(2L,-3L));
        assertEquals(-6L,FieldUtils.safeMultiply(-2L,3L));
        assertEquals(6L,FieldUtils.safeMultiply(-2L,-3L));
        
        assertEquals(Long.MAX_VALUE,FieldUtils.safeMultiply(Long.MAX_VALUE,1L));
        assertEquals(Long.MIN_VALUE,FieldUtils.safeMultiply(Long.MIN_VALUE,1L));
        assertEquals(-Long.MAX_VALUE,FieldUtils.safeMultiply(Long.MAX_VALUE,-1L));
        
        try {
            FieldUtils.safeMultiply(Long.MIN_VALUE, -1L);
            fail();
        } catch (ArithmeticException e) {
        }
        
        try {
            FieldUtils.safeMultiply(-1L, Long.MIN_VALUE);
            fail();
        } catch (ArithmeticException e) {
        }
      
        try {
            FieldUtils.safeMultiply(Long.MIN_VALUE, 100L);
            fail();
        } catch (ArithmeticException e) {
        }
        
        try {
            FieldUtils.safeMultiply(Long.MIN_VALUE, Long.MAX_VALUE);
            fail();
        } catch (ArithmeticException e) {
        }
        
        try {
            FieldUtils.safeMultiply(Long.MAX_VALUE, Long.MIN_VALUE);
            fail();
        } catch (ArithmeticException e) {
        }
    }

    //-----------------------------------------------------------------------
    public void testSafeMultiplyLongInt() {
        assertEquals(0L,FieldUtils.safeMultiply(0L,0));
        
        assertEquals(1L,FieldUtils.safeMultiply(1L,1));
        assertEquals(3L,FieldUtils.safeMultiply(1L,3));
        assertEquals(3L,FieldUtils.safeMultiply(3L,1));
        
        assertEquals(6L,FieldUtils.safeMultiply(2L,3));
        assertEquals(-6L,FieldUtils.safeMultiply(2L,-3));
        assertEquals(-6L,FieldUtils.safeMultiply(-2L,3));
        assertEquals(6L,FieldUtils.safeMultiply(-2L,-3));
        
        assertEquals(-1L * Integer.MIN_VALUE,FieldUtils.safeMultiply(-1L,Integer.MIN_VALUE));
        
        assertEquals(Long.MAX_VALUE,FieldUtils.safeMultiply(Long.MAX_VALUE,1));
        assertEquals(Long.MIN_VALUE,FieldUtils.safeMultiply(Long.MIN_VALUE,1));
        assertEquals(-Long.MAX_VALUE,FieldUtils.safeMultiply(Long.MAX_VALUE,-1));
        
        try {
            FieldUtils.safeMultiply(Long.MIN_VALUE, -1);
            fail();
        } catch (ArithmeticException e) {
        }
        
        try {
            FieldUtils.safeMultiply(Long.MIN_VALUE, 100);
            fail();
        } catch (ArithmeticException e) {
        }
        
        try {
            FieldUtils.safeMultiply(Long.MIN_VALUE, Integer.MAX_VALUE);
            fail();
        } catch (ArithmeticException e) {
        }
        
        try {
            FieldUtils.safeMultiply(Long.MAX_VALUE, Integer.MIN_VALUE);
            fail();
        } catch (ArithmeticException e) {
        }
    }

    //-----------------------------------------------------------------------
    public void testSafeDivideLongLong() {
        assertEquals(1L,FieldUtils.safeDivide(1L,1L));
        
        assertEquals(1L,FieldUtils.safeDivide(3L,3L));
        assertEquals(0L,FieldUtils.safeDivide(1L,3L));
        assertEquals(3L,FieldUtils.safeDivide(3L,1L));
        
        assertEquals(1L,FieldUtils.safeDivide(5L,3L));
        assertEquals(-1L,FieldUtils.safeDivide(5L,-3L));
        assertEquals(-1L,FieldUtils.safeDivide(-5L,3L));
        assertEquals(1L,FieldUtils.safeDivide(-5L,-3L));
        
        assertEquals(2L,FieldUtils.safeDivide(6L,3L));
        assertEquals(-2L,FieldUtils.safeDivide(6L,-3L));
        assertEquals(-2L,FieldUtils.safeDivide(-6L,3L));
        assertEquals(2L,FieldUtils.safeDivide(-6L,-3L));
        
        assertEquals(2L,FieldUtils.safeDivide(7L,3L));
        assertEquals(-2L,FieldUtils.safeDivide(7L,-3L));
        assertEquals(-2L,FieldUtils.safeDivide(-7L,3L));
        assertEquals(2L,FieldUtils.safeDivide(-7L,-3L));
        
        assertEquals(Long.MAX_VALUE,FieldUtils.safeDivide(Long.MAX_VALUE,1L));
        assertEquals(Long.MIN_VALUE,FieldUtils.safeDivide(Long.MIN_VALUE,1L));
        assertEquals(-Long.MAX_VALUE,FieldUtils.safeDivide(Long.MAX_VALUE,-1L));
        
        try {
            FieldUtils.safeDivide(Long.MIN_VALUE, -1L);
            fail();
        } catch (ArithmeticException e) {
        }
        
        try {
            FieldUtils.safeDivide(1L, 0L);
            fail();
        } catch (ArithmeticException e) {
        }
    }

    //-----------------------------------------------------------------------
    public void testSafeDivideRoundingModeLong() {
        assertEquals(3L,FieldUtils.safeDivide(15L,5L,RoundingMode.UNNECESSARY));
        assertEquals(59L,FieldUtils.safeDivide(179L,3L,RoundingMode.FLOOR));
        assertEquals(60L,FieldUtils.safeDivide(179L,3L,RoundingMode.CEILING));
        assertEquals(60L,FieldUtils.safeDivide(179L,3L,RoundingMode.HALF_UP));
        assertEquals(-60L,FieldUtils.safeDivide(-179L,3L,RoundingMode.HALF_UP));
        assertEquals(60L,FieldUtils.safeDivide(179L,3L,RoundingMode.HALF_DOWN));
        assertEquals(-60L,FieldUtils.safeDivide(-179L,3L,RoundingMode.HALF_DOWN));

        assertEquals(Long.MAX_VALUE,FieldUtils.safeDivide(Long.MAX_VALUE,1L,RoundingMode.UNNECESSARY));
        assertEquals(Long.MIN_VALUE,FieldUtils.safeDivide(Long.MIN_VALUE,1L,RoundingMode.UNNECESSARY));
        assertEquals(-Long.MAX_VALUE,FieldUtils.safeDivide(Long.MAX_VALUE,-1L,RoundingMode.UNNECESSARY));

        try {
            FieldUtils.safeDivide(Long.MIN_VALUE, -1L, RoundingMode.UNNECESSARY);
            fail();
        } catch (ArithmeticException e) {
        }

        try {
            FieldUtils.safeDivide(1L, 0L, RoundingMode.UNNECESSARY);
            fail();
        } catch (ArithmeticException e) {
        }

    }

    public void testSafeAddInt_1_oe() {
        int a = 0;
        assertEquals(0, a);
    }

    public void testSafeAddInt_3_oe() {

        int a = -1;
        assertEquals((-1), a);
    }

    public void testSafeAddInt_4_oe() {

        int a = 1;
        assertEquals(1, a);
    }

    public void testSafeAddInt_5_oe() {

        int a = -5;
        assertEquals((-5), a);
    }

    public void testSafeAddInt_6_oe() {


        Object a = Integer.MAX_VALUE - 1;
        assertEquals(2147483647, Integer.MAX_VALUE);
    }

    public void testSafeAddInt_7_oe() {


        Object a = Integer.MIN_VALUE + 1;
        assertEquals(2147483647, Integer.MAX_VALUE);
    }

    public void testSafeAddInt_8_oe() {



        int a = -1;
        assertEquals((-1), a);
    }

    public void testSafeAddInt_9_oe() {



        int a = -1;
        assertEquals((-1), a);
    }

    public void testSafeAddLong_1_oe() {
        long a = 0L;
        assertEquals(0L, a);
    }

    public void testSafeAddLong_2_oe() {

        long a = 5L;
        assertEquals(5L, a);
    }

    public void testSafeAddLong_3_oe() {

        long a = -1L;
        assertEquals((-1L), a);
    }

    public void testSafeAddLong_4_oe() {

        long a = 1L;
        assertEquals(1L, a);
    }

    public void testSafeAddLong_5_oe() {

        long a = -5L;
        assertEquals((-5L), a);
    }

    public void testSafeAddLong_6_oe() {


        Object a = Long.MAX_VALUE - 1;
        assertEquals(9223372036854775806L, a);
    }

    public void testSafeAddLong_7_oe() {


        Object a = Long.MIN_VALUE + 1;
        assertEquals(9223372036854775807L, Long.MAX_VALUE);
    }

    public void testSafeAddLong_8_oe() {



        int a = -1;
        assertEquals((-1), a);
    }

    public void testSafeAddLong_9_oe() {



        int a = -1;
        assertEquals((-1), a);
    }

    public void testSafeSubtractLong_1_oe() {
        long a = 0L;
        assertEquals(0L, a);
    }

    public void testSafeSubtractLong_2_oe() {

        long a = -1L;
        assertEquals((-1L), a);
    }

    public void testSafeSubtractLong_3_oe() {

        long a = 5L;
        assertEquals(5L, a);
    }

    public void testSafeSubtractLong_4_oe() {

        long a = -5L;
// incorrect assertion         assertEquals((-5L), safeSubtract(5L, 0L));
    }

    public void testSafeSubtractLong_5_oe() {

        long a = 1L;
        assertEquals(0L, a);
    }

    public void testSafeSubtractLong_6_oe() {


        Object a = Long.MAX_VALUE - 1;
        assertEquals(9223372036854775807L, Long.MAX_VALUE);
    }

    public void testSafeSubtractLong_7_oe() {


        Object a = Long.MIN_VALUE + 1;
        assertEquals(9223372036854775807L, Long.MAX_VALUE);
    }

    public void testSafeSubtractLong_8_oe() {



        int a = 0;
// incorrect assertion         assertEquals(0L, safeSubtract(0L, 0L));
    }

    public void testSafeSubtractLong_9_oe() {



        int a = 0;
// incorrect assertion         assertEquals(0L, safeSubtract(0L, 0L));
    }

    public void testSafeMultiplyLongLong_6_oe() {
        
        
        long a = -6L;
// incorrect assertion         assertDoesNotThrow(() -> Integer.safeMultiply(0, 0));
    }

    public void testSafeMultiplyLongLong_7_oe() {
        
        
        long a = -6L;
        assertEquals(0L, a);
    }

    public void testSafeMultiplyLongLong_8_oe() {
        
        
        long a = 6L;
        assertNotNull(Long.valueOf(6L));
    }

    public void testSafeMultiplyLongLong_11_oe() {
        
        
        
        Object a = -Long.MAX_VALUE;
// incorrect assertion         assertDoesNotThrow(() -> Objects.requireNonNull(a));
    }

    public void testSafeMultiplyLongInt_2_oe() {
        
        long a = 1L;
// incorrect assertion         assertDoesNotThrow(() -> IntegerUtils.safeMultiply(1, 1));
    }

    public void testSafeMultiplyLongInt_3_oe() {
        
        long a = 3L;
        assertEquals(0L, a);
    }

    public void testSafeMultiplyLongInt_4_oe() {
        
        long a = 3L;
// incorrect assertion         assertDoesNotThrow(() -> Objects.requireNonNull(a));
    }

    public void testSafeMultiplyLongInt_6_oe() {
        
        
        long a = -6L;
// incorrect assertion         assertDoesNotThrow(() -> Objects.requireNonNull(a));
    }

    public void testSafeMultiplyLongInt_12_oe() {
        
        
        
        
        Object a = -Long.MAX_VALUE;
// incorrect assertion         assertDoesNotThrow(() -> Objects.requireNonNull(a));
    }

    public void testSafeDivideLongLong_1_oe() {
        long a = 1L;
        assertEquals(1L, a);
    }

    public void testSafeDivideLongLong_2_oe() {
        
        long a = 1L;
// incorrect assertion         assertEquals(1L, safeDivide(1L, 1L));
    }

    public void testSafeDivideLongLong_3_oe() {
        
        long a = 0L;
        assertEquals(0L, a);
    }

    public void testSafeDivideLongLong_4_oe() {
        
        long a = 3L;
// incorrect assertion         assertEquals(3L, safeDivide(3L, 1L));
    }

    public void testSafeDivideLongLong_5_oe() {
        
        
        long a = 1L;
        assertEquals(1L, a);
    }

    public void testSafeDivideLongLong_6_oe() {
        
        
        long a = -1L;
// incorrect assertion         assertEquals((-1L), safeDivide(-1L, 1L));
    }

    public void testSafeDivideLongLong_7_oe() {
        
        
        long a = -1L;
// incorrect assertion         assertEquals((-1L), safeDivide(-1L, 1L));
    }

    public void testSafeDivideLongLong_8_oe() {
        
        
        long a = 1L;
        assertEquals(1L, a);
    }

    public void testSafeDivideLongLong_9_oe() {
        
        
        
        long a = 2L;
        assertEquals(2L, a);
    }

    public void testSafeDivideLongLong_10_oe() {
        
        
        
        long a = -2L;
// incorrect assertion         assertEquals((-2L), safeDivide(-2L, 1L));
    }

    public void testSafeDivideLongLong_11_oe() {
        
        
        
        long a = -2L;
        assertEquals(0L, a);
    }

    public void testSafeDivideLongLong_12_oe() {
        
        
        
        long a = 2L;
// incorrect assertion         assertEquals(2L, safeDivide(2L, 1L));
    }

    public void testSafeDivideLongLong_13_oe() {
        
        
        
        
        long a = 2L;
// incorrect assertion         assertEquals(2L, safeDivide(2L, 1L));
    }

    public void testSafeDivideLongLong_14_oe() {
        
        
        
        
        long a = -2L;
// incorrect assertion         assertEquals((-2L), safeDivide(-2L, 1L));
    }

    public void testSafeDivideLongLong_15_oe() {
        
        
        
        
        long a = -2L;
// incorrect assertion         assertEquals((-2L), safeDivide(-2L, 1L));
    }

    public void testSafeDivideLongLong_16_oe() {
        
        
        
        
        long a = 2L;
// incorrect assertion         assertEquals(2L, safeDivide(2L, 1L));
    }

    public void testSafeDivideLongLong_17_oe() {
        
        
        
        
        
        Object a = Long.MAX_VALUE;
// incorrect assertion         assertDoesNotThrow(() -> Long.safeDivide(Long.MAX_VALUE, 1L));
    }

    public void testSafeDivideLongLong_18_oe() {
        
        
        
        
        
        Object a = Long.MIN_VALUE;
// incorrect assertion         assertDoesNotThrow(() -> Long.safeDivide(Long.MIN_VALUE, -1L));
    }

    public void testSafeDivideLongLong_19_oe() {
        
        
        
        
        
        Object a = -Long.MAX_VALUE;
// incorrect assertion         assertDoesNotThrow(() -> Objects.requireNonNull(a));
    }

    public void testSafeDivideRoundingModeLong_1_oe() {
        long a = 3L;
// incorrect assertion         assertEquals(3L, safeDivide(3L, 1L));
    }

    public void testSafeDivideRoundingModeLong_2_oe() {
        long a = 59L;
// incorrect assertion         assertEquals(59L, safeDivide(59L, 1L));
    }

    public void testSafeDivideRoundingModeLong_3_oe() {
        long a = 60L;
// incorrect assertion         assertEquals(60L, safeDivide(60L, 1L));
    }

    public void testSafeDivideRoundingModeLong_4_oe() {
        long a = 60L;
// incorrect assertion         assertEquals(60L, safeDivide(60L, 1L));
    }

    public void testSafeDivideRoundingModeLong_5_oe() {
        long a = -60L;
        assertEquals(0L, a);
    }

    public void testSafeDivideRoundingModeLong_6_oe() {
        long a = 60L;
// incorrect assertion         assertEquals(60L, safeDivide(60L, 1L));
    }

    public void testSafeDivideRoundingModeLong_7_oe() {
        long a = -60L;
// incorrect assertion         assertEquals((-60L), safeDivide(-60L, 1L));
    }

    public void testSafeDivideRoundingModeLong_8_oe() {

        Object a = Long.MAX_VALUE;
        assertEquals(0L, Long.MAX_VALUE);
    }

    public void testSafeDivideRoundingModeLong_9_oe() {

        Object a = Long.MIN_VALUE;
        assertEquals(0L, Long.MIN_VALUE);
    }

    public void testSafeDivideRoundingModeLong_10_oe() {

        Object a = -Long.MAX_VALUE;
// incorrect assertion         assertDoesNotThrow(() -> Objects.requireNonNull(a));
    }

}
