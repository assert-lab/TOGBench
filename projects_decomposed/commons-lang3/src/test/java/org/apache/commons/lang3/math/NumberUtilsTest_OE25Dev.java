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
package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests {@link org.apache.commons.lang3.math.NumberUtils}.
 */
public class NumberUtilsTest_OE25Dev {

    private boolean checkCreateNumber(final String val) {
        try {
            final Object obj = NumberUtils.createNumber(val);
            return obj != null;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    // ---------------------------------------------------------------------

    private void compareIsCreatableWithCreateNumber(final String val, final boolean expected) {
        final boolean isValid = NumberUtils.isCreatable(val);
        final boolean canCreate = checkCreateNumber(val);
        assertTrue(isValid == expected && canCreate == expected,"Expecting " + expected + " for isCreatable/createNumber using \"" + val + "\" but got " + isValid + " and " + canCreate);
    }

    private void compareIsNumberWithCreateNumber(final String val, final boolean expected) {
        final boolean isValid = NumberUtils.isCreatable(val);
        final boolean canCreate = checkCreateNumber(val);
        assertTrue(isValid == expected && canCreate == expected,"Expecting " + expected + " for isCreatable/createNumber using \"" + val + "\" but got " + isValid + " and " + canCreate);
    }

    /**
     * Test for {@link NumberUtils#toDouble(BigDecimal)}
     */

    /**
     * Test for {@link NumberUtils#toDouble(BigDecimal, double)}
     */

    // Testing JDK against old Lang functionality

    // -----------------------------------------------------------------------

    protected void testCreateBigDecimalFailure(final String str) {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createBigDecimal(str),
            "createBigDecimal(\"" + str + "\") should have failed.");
    }

    protected void testCreateBigIntegerFailure(final String str) {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createBigInteger(str),
            "createBigInteger(\"" + str + "\") should have failed.");
    }

    protected void testCreateDoubleFailure(final String str) {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createDouble(str),
            "createDouble(\"" + str + "\") should have failed.");
    }

    protected void testCreateFloatFailure(final String str) {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createFloat(str),
            "createFloat(\"" + str + "\") should have failed.");
    }

    protected void testCreateIntegerFailure(final String str) {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createInteger(str),
            "createInteger(\"" + str + "\") should have failed.");
    }

    protected void testCreateLongFailure(final String str) {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createLong(str),
            "createLong(\"" + str + "\") should have failed.");
    }

    @Test
    // Check that the code fails to create a valid number when preceded by -- rather than -
    public void testCreateNumberFailure_1() {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("--1.1E-700F"));
    }

    @Test
    // Check that the code fails to create a valid number when both e and E are present (with decimal)
    public void testCreateNumberFailure_2() {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("-1.1E+0-7e00"));
    }

    @Test
    // Check that the code fails to create a valid number when both e and E are present (no decimal)
    public void testCreateNumberFailure_3() {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("-11E+0-7e00"));
    }

    @Test
    // Check that the code fails to create a valid number when both e and E are present (no decimal)
    public void testCreateNumberFailure_4() {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1eE+00001"));
    }

    @Test
    // Check that the code fails to create a valid number when there are multiple trailing 'f' characters (LANG-1205)
    public void testCreateNumberFailure_5() {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1234.5ff"));
    }

    @Test
    // Check that the code fails to create a valid number when there are multiple trailing 'F' characters (LANG-1205)
    public void testCreateNumberFailure_6() {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1234.5FF"));
    }

    @Test
    // Check that the code fails to create a valid number when there are multiple trailing 'd' characters (LANG-1205)
    public void testCreateNumberFailure_7() {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1234.5dd"));
    }

    @Test
    // Check that the code fails to create a valid number when there are multiple trailing 'D' characters (LANG-1205)
    public void testCreateNumberFailure_8() {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createNumber("1234.5DD"));
    }

    // Tests to show when magnitude causes switch to next Number type
    // Will probably need to be adjusted if code is changed to check precision (LANG-693)

    /**
     * Tests isCreatable(String) and tests that createNumber(String) returns a valid number iff isCreatable(String)
     * returns false.
     */
    @Test
    public void testIsCreatable() {
        compareIsCreatableWithCreateNumber("12345", true);
        compareIsCreatableWithCreateNumber("1234.5", true);
        compareIsCreatableWithCreateNumber(".12345", true);
        compareIsCreatableWithCreateNumber("1234E5", true);
        compareIsCreatableWithCreateNumber("1234E+5", true);
        compareIsCreatableWithCreateNumber("1234E-5", true);
        compareIsCreatableWithCreateNumber("123.4E5", true);
        compareIsCreatableWithCreateNumber("-1234", true);
        compareIsCreatableWithCreateNumber("-1234.5", true);
        compareIsCreatableWithCreateNumber("-.12345", true);
        compareIsCreatableWithCreateNumber("-1234E5", true);
        compareIsCreatableWithCreateNumber("0", true);
        compareIsCreatableWithCreateNumber("0.1", true); // LANG-1216
        compareIsCreatableWithCreateNumber("-0", true);
        compareIsCreatableWithCreateNumber("01234", true);
        compareIsCreatableWithCreateNumber("-01234", true);
        compareIsCreatableWithCreateNumber("-0xABC123", true);
        compareIsCreatableWithCreateNumber("-0x0", true);
        compareIsCreatableWithCreateNumber("123.4E21D", true);
        compareIsCreatableWithCreateNumber("-221.23F", true);
        compareIsCreatableWithCreateNumber("22338L", true);

        compareIsCreatableWithCreateNumber(null, false);
        compareIsCreatableWithCreateNumber("", false);
        compareIsCreatableWithCreateNumber(" ", false);
        compareIsCreatableWithCreateNumber("\r\n\t", false);
        compareIsCreatableWithCreateNumber("--2.3", false);
        compareIsCreatableWithCreateNumber(".12.3", false);
        compareIsCreatableWithCreateNumber("-123E", false);
        compareIsCreatableWithCreateNumber("-123E+-212", false);
        compareIsCreatableWithCreateNumber("-123E2.12", false);
        compareIsCreatableWithCreateNumber("0xGF", false);
        compareIsCreatableWithCreateNumber("0xFAE-1", false);
        compareIsCreatableWithCreateNumber(".", false);
        compareIsCreatableWithCreateNumber("-0ABC123", false);
        compareIsCreatableWithCreateNumber("123.4E-D", false);
        compareIsCreatableWithCreateNumber("123.4ED", false);
        compareIsCreatableWithCreateNumber("1234E5l", false);
        compareIsCreatableWithCreateNumber("11a", false);
        compareIsCreatableWithCreateNumber("1a", false);
        compareIsCreatableWithCreateNumber("a", false);
        compareIsCreatableWithCreateNumber("11g", false);
        compareIsCreatableWithCreateNumber("11z", false);
        compareIsCreatableWithCreateNumber("11def", false);
        compareIsCreatableWithCreateNumber("11d11", false);
        compareIsCreatableWithCreateNumber("11 11", false);
        compareIsCreatableWithCreateNumber(" 1111", false);
        compareIsCreatableWithCreateNumber("1111 ", false);

        compareIsCreatableWithCreateNumber("2.", true); // LANG-521
        compareIsCreatableWithCreateNumber("1.1L", false); // LANG-664
    }

    /**
     * Tests isCreatable(String) and tests that createNumber(String) returns a valid number iff isCreatable(String)
     * returns false.
     */
    @Test
    public void testIsNumber() {
        compareIsNumberWithCreateNumber("12345", true);
        compareIsNumberWithCreateNumber("1234.5", true);
        compareIsNumberWithCreateNumber(".12345", true);
        compareIsNumberWithCreateNumber("1234E5", true);
        compareIsNumberWithCreateNumber("1234E+5", true);
        compareIsNumberWithCreateNumber("1234E-5", true);
        compareIsNumberWithCreateNumber("123.4E5", true);
        compareIsNumberWithCreateNumber("-1234", true);
        compareIsNumberWithCreateNumber("-1234.5", true);
        compareIsNumberWithCreateNumber("-.12345", true);
        compareIsNumberWithCreateNumber("-0001.12345", true);
        compareIsNumberWithCreateNumber("-000.12345", true);
        compareIsNumberWithCreateNumber("+00.12345", true);
        compareIsNumberWithCreateNumber("+0002.12345", true);
        compareIsNumberWithCreateNumber("-1234E5", true);
        compareIsNumberWithCreateNumber("0", true);
        compareIsNumberWithCreateNumber("-0", true);
        compareIsNumberWithCreateNumber("01234", true);
        compareIsNumberWithCreateNumber("-01234", true);
        compareIsNumberWithCreateNumber("-0xABC123", true);
        compareIsNumberWithCreateNumber("-0x0", true);
        compareIsNumberWithCreateNumber("123.4E21D", true);
        compareIsNumberWithCreateNumber("-221.23F", true);
        compareIsNumberWithCreateNumber("22338L", true);

        compareIsNumberWithCreateNumber(null, false);
        compareIsNumberWithCreateNumber("", false);
        compareIsNumberWithCreateNumber(" ", false);
        compareIsNumberWithCreateNumber("\r\n\t", false);
        compareIsNumberWithCreateNumber("--2.3", false);

        compareIsNumberWithCreateNumber(".12.3", false);
        compareIsNumberWithCreateNumber("-123E", false);
        compareIsNumberWithCreateNumber("-123E+-212", false);
        compareIsNumberWithCreateNumber("-123E2.12", false);
        compareIsNumberWithCreateNumber("0xGF", false);
        compareIsNumberWithCreateNumber("0xFAE-1", false);
        compareIsNumberWithCreateNumber(".", false);
        compareIsNumberWithCreateNumber("-0ABC123", false);
        compareIsNumberWithCreateNumber("123.4E-D", false);
        compareIsNumberWithCreateNumber("123.4ED", false);
        compareIsNumberWithCreateNumber("+000E.12345", false);
        compareIsNumberWithCreateNumber("-000E.12345", false);
        compareIsNumberWithCreateNumber("1234E5l", false);
        compareIsNumberWithCreateNumber("11a", false);
        compareIsNumberWithCreateNumber("1a", false);
        compareIsNumberWithCreateNumber("a", false);
        compareIsNumberWithCreateNumber("11g", false);
        compareIsNumberWithCreateNumber("11z", false);
        compareIsNumberWithCreateNumber("11def", false);
        compareIsNumberWithCreateNumber("11d11", false);
        compareIsNumberWithCreateNumber("11 11", false);
        compareIsNumberWithCreateNumber(" 1111", false);
        compareIsNumberWithCreateNumber("1111 ", false);

        compareIsNumberWithCreateNumber("2.", true); // LANG-521
        compareIsNumberWithCreateNumber("1.1L", false); // LANG-664
    }

    @Test
    public void testIsNumberLANG1252() {
        compareIsNumberWithCreateNumber("+2", true);
        compareIsNumberWithCreateNumber("+2.0", true);
    }

    @Test
    public void testIsNumberLANG1385() {
        compareIsNumberWithCreateNumber("L", false);
    }

    @Test
    public void testIsNumberLANG971() {
        compareIsNumberWithCreateNumber("0085", false);
        compareIsNumberWithCreateNumber("085", false);
        compareIsNumberWithCreateNumber("08", false);
        compareIsNumberWithCreateNumber("07", true);
        compareIsNumberWithCreateNumber("00", true);
    }

    @Test
    public void testIsNumberLANG972() {
        compareIsNumberWithCreateNumber("0xABCD", true);
        compareIsNumberWithCreateNumber("0XABCD", true);
    }

    @Test
    public void testIsNumberLANG992() {
        compareIsNumberWithCreateNumber("0.0", true);
        compareIsNumberWithCreateNumber("0.4790", true);
    }

    @Test
    public void testLANG1252() {
        compareIsCreatableWithCreateNumber("+2", true);
        compareIsCreatableWithCreateNumber("+2.0", true);
    }

    @Test
    public void testLang300() {
        NumberUtils.createNumber("-1l");
        NumberUtils.createNumber("01l");
        NumberUtils.createNumber("1l");
    }

    @Test
    public void testLANG971() {
        compareIsCreatableWithCreateNumber("0085", false);
        compareIsCreatableWithCreateNumber("085", false);
        compareIsCreatableWithCreateNumber("08", false);
        compareIsCreatableWithCreateNumber("07", true);
        compareIsCreatableWithCreateNumber("00", true);
    }

    @Test
    public void testLANG972() {
        compareIsCreatableWithCreateNumber("0xABCD", true);
        compareIsCreatableWithCreateNumber("0XABCD", true);
    }

    @Test
    public void testLANG992() {
        compareIsCreatableWithCreateNumber("0.0", true);
        compareIsCreatableWithCreateNumber("0.4790", true);
    }

    // min/max tests
    // ----------------------------------------------------------------------

    /**
     * Test for {(@link NumberUtils#createNumber(String)}
     */

    /**
     * Test for {@link NumberUtils#toDouble(String)}.
     */

    /**
     * Test for {@link NumberUtils#toDouble(String, double)}.
     */

    /**
     * Test for {@link NumberUtils#toByte(String)}.
     */

    /**
     * Test for {@link NumberUtils#toByte(String, byte)}.
     */

    /**
     * Test for {@link NumberUtils#toFloat(String)}.
     */

    /**
     * Test for {@link NumberUtils#toFloat(String, float)}.
     */

    /**
     * Test for {@link NumberUtils#toInt(String)}.
     */

    /**
     * Test for {@link NumberUtils#toInt(String, int)}.
     */

    /**
     * Test for {@link NumberUtils#toLong(String)}.
     */

    /**
     * Test for {@link NumberUtils#toLong(String, long)}.
     */

    /**
     * Test for {@link NumberUtils#toScaledBigDecimal(BigDecimal)}.
     */

    /**
     * Test for {@link NumberUtils#toScaledBigDecimal(BigDecimal, int, RoundingMode)}.
     */

    /**
     * Test for {@link NumberUtils#toScaledBigDecimal(Double)}.
     */

    /**
     * Test for {@link NumberUtils#toScaledBigDecimal(Double, int, RoundingMode)}.
     */

    /**
     * Test for {@link NumberUtils#toScaledBigDecimal(Float)}.
     */

    /**
     * Test for {@link NumberUtils#toScaledBigDecimal(Float, int, RoundingMode)}.
     */

    /**
     * Test for {@link NumberUtils#toScaledBigDecimal(Double)}.
     */

    /**
     * Test for {@link NumberUtils#toScaledBigDecimal(Double, int, RoundingMode)}.
     */

    /**
     * Test for {@link NumberUtils#toShort(String)}.
     */

    /**
     * Test for {@link NumberUtils#toShort(String, short)}.
     */

    @Test
    public void compareByte_1_oe() {
        assertTrue(NumberUtils.compare((byte) -3, (byte) 0) < 0);
    }

    @Test
    public void compareByte_2_oe() {
        // removed other assertion
        assertEquals(0, NumberUtils.compare((byte) 113, (byte) 113));
    }

    @Test
    public void compareByte_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.compare((byte) 123, (byte) 32) > 0);
    }

    @Test
    public void compareInt_1_oe() {
        assertTrue(NumberUtils.compare(-3, 0) < 0);
    }

    @Test
    public void compareInt_2_oe() {
        // removed other assertion
        assertEquals(0, NumberUtils.compare(113, 113));
    }

    @Test
    public void compareInt_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.compare(213, 32) > 0);
    }

    @Test
    public void compareLong_1_oe() {
        assertTrue(NumberUtils.compare(-3L, 0L) < 0);
    }

    @Test
    public void compareLong_2_oe() {
        // removed other assertion
        assertEquals(0, NumberUtils.compare(113L, 113L));
    }

    @Test
    public void compareLong_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.compare(213L, 32L) > 0);
    }

    @Test
    public void compareShort_1_oe() {
        assertTrue(NumberUtils.compare((short) -3, (short) 0) < 0);
    }

    @Test
    public void compareShort_2_oe() {
        // removed other assertion
        assertEquals(0, NumberUtils.compare((short) 113, (short) 113));
    }

    @Test
    public void compareShort_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.compare((short) 213, (short) 32) > 0);
    }

    @Test
    public void testBigIntegerToDoubleBigInteger_1_oe() {
        assertEquals(0.0d, NumberUtils.toDouble((BigDecimal) null), "toDouble(BigInteger) 1 failed");
    }

    @Test
    public void testBigIntegerToDoubleBigInteger_2_oe() {
        // removed other assertion
        assertEquals(8.5d, NumberUtils.toDouble(BigDecimal.valueOf(8.5d)), "toDouble(BigInteger) 2 failed");
    }

    @Test
    public void testBigIntegerToDoubleBigIntegerD_1_oe() {
        assertEquals(1.1d, NumberUtils.toDouble((BigDecimal) null, 1.1d), "toDouble(BigInteger) 1 failed");
    }

    @Test
    public void testBigIntegerToDoubleBigIntegerD_2_oe() {
        // removed other assertion
        assertEquals(8.5d, NumberUtils.toDouble(BigDecimal.valueOf(8.5d), 1.1d), "toDouble(BigInteger) 2 failed");
    }

    @Test
    public void testCompareDouble_1_oe() {
        assertEquals(0, Double.compare(Double.NaN, Double.NaN));
    }

    @Test
    public void testCompareDouble_2_oe() {
        // removed other assertion
        assertEquals(Double.compare(Double.NaN, Double.POSITIVE_INFINITY), +1);
    }

    @Test
    public void testCompareDouble_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(Double.NaN, Double.MAX_VALUE), +1);
    }

    @Test
    public void testCompareDouble_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(Double.NaN, 1.2d), +1);
    }

    @Test
    public void testCompareDouble_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(Double.NaN, 0.0d), +1);
    }

    @Test
    public void testCompareDouble_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(Double.NaN, -0.0d), +1);
    }

    @Test
    public void testCompareDouble_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(Double.NaN, -1.2d), +1);
    }

    @Test
    public void testCompareDouble_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(Double.NaN, -Double.MAX_VALUE), +1);
    }

    @Test
    public void testCompareDouble_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(Double.NaN, Double.NEGATIVE_INFINITY), +1);
    }

    @Test
    public void testCompareDouble_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(Double.compare(Double.POSITIVE_INFINITY, Double.NaN), -1);
    }

    @Test
    public void testCompareDouble_11_oe() {
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
        assertEquals(0, Double.compare(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY));
    }

    @Test
    public void testCompareDouble_12_oe() {
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
        assertEquals(Double.compare(Double.POSITIVE_INFINITY, Double.MAX_VALUE), +1);
    }

    @Test
    public void testCompareDouble_13_oe() {
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
        assertEquals(Double.compare(Double.POSITIVE_INFINITY, 1.2d), +1);
    }

    @Test
    public void testCompareDouble_14_oe() {
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
        assertEquals(Double.compare(Double.POSITIVE_INFINITY, 0.0d), +1);
    }

    @Test
    public void testCompareDouble_15_oe() {
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
        assertEquals(Double.compare(Double.POSITIVE_INFINITY, -0.0d), +1);
    }

    @Test
    public void testCompareDouble_16_oe() {
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
        assertEquals(Double.compare(Double.POSITIVE_INFINITY, -1.2d), +1);
    }

    @Test
    public void testCompareDouble_17_oe() {
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
        assertEquals(Double.compare(Double.POSITIVE_INFINITY, -Double.MAX_VALUE), +1);
    }

    @Test
    public void testCompareDouble_18_oe() {
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
        assertEquals(Double.compare(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY), +1);
    }

    @Test
    public void testCompareDouble_19_oe() {
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

        assertEquals(Double.compare(Double.MAX_VALUE, Double.NaN), -1);
    }

    @Test
    public void testCompareDouble_20_oe() {
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
        assertEquals(Double.compare(Double.MAX_VALUE, Double.POSITIVE_INFINITY), -1);
    }

    @Test
    public void testCompareDouble_21_oe() {
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
        assertEquals(0, Double.compare(Double.MAX_VALUE, Double.MAX_VALUE));
    }

    @Test
    public void testCompareDouble_22_oe() {
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
        assertEquals(Double.compare(Double.MAX_VALUE, 1.2d), +1);
    }

    @Test
    public void testCompareDouble_23_oe() {
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
        assertEquals(Double.compare(Double.MAX_VALUE, 0.0d), +1);
    }

    @Test
    public void testCompareDouble_24_oe() {
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
        assertEquals(Double.compare(Double.MAX_VALUE, -0.0d), +1);
    }

    @Test
    public void testCompareDouble_25_oe() {
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
        // removed other assertion
        assertEquals(Double.compare(Double.MAX_VALUE, -1.2d), +1);
    }

    @Test
    public void testCompareDouble_26_oe() {
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
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(Double.MAX_VALUE, -Double.MAX_VALUE), +1);
    }

    @Test
    public void testCompareDouble_27_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(Double.MAX_VALUE, Double.NEGATIVE_INFINITY), +1);
    }

    @Test
    public void testCompareDouble_28_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(Double.compare(1.2d, Double.NaN), -1);
    }

    @Test
    public void testCompareDouble_29_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(Double.compare(1.2d, Double.POSITIVE_INFINITY), -1);
    }

    @Test
    public void testCompareDouble_30_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(1.2d, Double.MAX_VALUE), -1);
    }

    @Test
    public void testCompareDouble_31_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, Double.compare(1.2d, 1.2d));
    }

    @Test
    public void testCompareDouble_32_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(1.2d, 0.0d), +1);
    }

    @Test
    public void testCompareDouble_33_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(1.2d, -0.0d), +1);
    }

    @Test
    public void testCompareDouble_34_oe() {
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
        assertEquals(Double.compare(1.2d, -1.2d), +1);
    }

    @Test
    public void testCompareDouble_35_oe() {
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
        assertEquals(Double.compare(1.2d, -Double.MAX_VALUE), +1);
    }

    @Test
    public void testCompareDouble_36_oe() {
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
        assertEquals(Double.compare(1.2d, Double.NEGATIVE_INFINITY), +1);
    }

    @Test
    public void testCompareDouble_37_oe() {
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

        assertEquals(Double.compare(0.0d, Double.NaN), -1);
    }

    @Test
    public void testCompareDouble_38_oe() {
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
        assertEquals(Double.compare(0.0d, Double.POSITIVE_INFINITY), -1);
    }

    @Test
    public void testCompareDouble_39_oe() {
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
        assertEquals(Double.compare(0.0d, Double.MAX_VALUE), -1);
    }

    @Test
    public void testCompareDouble_40_oe() {
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
        assertEquals(Double.compare(0.0d, 1.2d), -1);
    }

    @Test
    public void testCompareDouble_41_oe() {
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
        assertEquals(0, Double.compare(0.0d, 0.0d));
    }

    @Test
    public void testCompareDouble_42_oe() {
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
        assertEquals(Double.compare(0.0d, -0.0d), +1);
    }

    @Test
    public void testCompareDouble_43_oe() {
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
        assertEquals(Double.compare(0.0d, -1.2d), +1);
    }

    @Test
    public void testCompareDouble_44_oe() {
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
        assertEquals(Double.compare(0.0d, -Double.MAX_VALUE), +1);
    }

    @Test
    public void testCompareDouble_45_oe() {
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
        assertEquals(Double.compare(0.0d, Double.NEGATIVE_INFINITY), +1);
    }

    @Test
    public void testCompareDouble_46_oe() {
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

        assertEquals(Double.compare(-0.0d, Double.NaN), -1);
    }

    @Test
    public void testCompareDouble_47_oe() {
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
        assertEquals(Double.compare(-0.0d, Double.POSITIVE_INFINITY), -1);
    }

    @Test
    public void testCompareDouble_48_oe() {
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
        // removed other assertion
        assertEquals(Double.compare(-0.0d, Double.MAX_VALUE), -1);
    }

    @Test
    public void testCompareDouble_49_oe() {
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
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(-0.0d, 1.2d), -1);
    }

    @Test
    public void testCompareDouble_50_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(-0.0d, 0.0d), -1);
    }

    @Test
    public void testCompareDouble_51_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, Double.compare(-0.0d, -0.0d));
    }

    @Test
    public void testCompareDouble_52_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(-0.0d, -1.2d), +1);
    }

    @Test
    public void testCompareDouble_53_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(-0.0d, -Double.MAX_VALUE), +1);
    }

    @Test
    public void testCompareDouble_54_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(-0.0d, Double.NEGATIVE_INFINITY), +1);
    }

    @Test
    public void testCompareDouble_55_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(Double.compare(-1.2d, Double.NaN), -1);
    }

    @Test
    public void testCompareDouble_56_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(Double.compare(-1.2d, Double.POSITIVE_INFINITY), -1);
    }

    @Test
    public void testCompareDouble_57_oe() {
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
        assertEquals(Double.compare(-1.2d, Double.MAX_VALUE), -1);
    }

    @Test
    public void testCompareDouble_58_oe() {
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
        assertEquals(Double.compare(-1.2d, 1.2d), -1);
    }

    @Test
    public void testCompareDouble_59_oe() {
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
        assertEquals(Double.compare(-1.2d, 0.0d), -1);
    }

    @Test
    public void testCompareDouble_60_oe() {
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
        assertEquals(Double.compare(-1.2d, -0.0d), -1);
    }

    @Test
    public void testCompareDouble_61_oe() {
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
        assertEquals(0, Double.compare(-1.2d, -1.2d));
    }

    @Test
    public void testCompareDouble_62_oe() {
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
        assertEquals(Double.compare(-1.2d, -Double.MAX_VALUE), +1);
    }

    @Test
    public void testCompareDouble_63_oe() {
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
        assertEquals(Double.compare(-1.2d, Double.NEGATIVE_INFINITY), +1);
    }

    @Test
    public void testCompareDouble_64_oe() {
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

        assertEquals(Double.compare(-Double.MAX_VALUE, Double.NaN), -1);
    }

    @Test
    public void testCompareDouble_65_oe() {
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
        assertEquals(Double.compare(-Double.MAX_VALUE, Double.POSITIVE_INFINITY), -1);
    }

    @Test
    public void testCompareDouble_66_oe() {
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
        assertEquals(Double.compare(-Double.MAX_VALUE, Double.MAX_VALUE), -1);
    }

    @Test
    public void testCompareDouble_67_oe() {
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
        assertEquals(Double.compare(-Double.MAX_VALUE, 1.2d), -1);
    }

    @Test
    public void testCompareDouble_68_oe() {
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
        assertEquals(Double.compare(-Double.MAX_VALUE, 0.0d), -1);
    }

    @Test
    public void testCompareDouble_69_oe() {
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
        assertEquals(Double.compare(-Double.MAX_VALUE, -0.0d), -1);
    }

    @Test
    public void testCompareDouble_70_oe() {
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
        assertEquals(Double.compare(-Double.MAX_VALUE, -1.2d), -1);
    }

    @Test
    public void testCompareDouble_71_oe() {
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
        // removed other assertion
        assertEquals(0, Double.compare(-Double.MAX_VALUE, -Double.MAX_VALUE));
    }

    @Test
    public void testCompareDouble_72_oe() {
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
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(-Double.MAX_VALUE, Double.NEGATIVE_INFINITY), +1);
    }

    @Test
    public void testCompareDouble_73_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(Double.compare(Double.NEGATIVE_INFINITY, Double.NaN), -1);
    }

    @Test
    public void testCompareDouble_74_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(Double.compare(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY), -1);
    }

    @Test
    public void testCompareDouble_75_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(Double.NEGATIVE_INFINITY, Double.MAX_VALUE), -1);
    }

    @Test
    public void testCompareDouble_76_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(Double.NEGATIVE_INFINITY, 1.2d), -1);
    }

    @Test
    public void testCompareDouble_77_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(Double.NEGATIVE_INFINITY, 0.0d), -1);
    }

    @Test
    public void testCompareDouble_78_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(Double.NEGATIVE_INFINITY, -0.0d), -1);
    }

    @Test
    public void testCompareDouble_79_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.compare(Double.NEGATIVE_INFINITY, -1.2d), -1);
    }

    @Test
    public void testCompareDouble_80_oe() {
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
        assertEquals(Double.compare(Double.NEGATIVE_INFINITY, -Double.MAX_VALUE), -1);
    }

    @Test
    public void testCompareDouble_81_oe() {
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
        assertEquals(0, Double.compare(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY));
    }

    @Test
    public void testCompareFloat_1_oe() {
        assertEquals(0, Float.compare(Float.NaN, Float.NaN));
    }

    @Test
    public void testCompareFloat_2_oe() {
        // removed other assertion
        assertEquals(Float.compare(Float.NaN, Float.POSITIVE_INFINITY), +1);
    }

    @Test
    public void testCompareFloat_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(Float.NaN, Float.MAX_VALUE), +1);
    }

    @Test
    public void testCompareFloat_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(Float.NaN, 1.2f), +1);
    }

    @Test
    public void testCompareFloat_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(Float.NaN, 0.0f), +1);
    }

    @Test
    public void testCompareFloat_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(Float.NaN, -0.0f), +1);
    }

    @Test
    public void testCompareFloat_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(Float.NaN, -1.2f), +1);
    }

    @Test
    public void testCompareFloat_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(Float.NaN, -Float.MAX_VALUE), +1);
    }

    @Test
    public void testCompareFloat_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(Float.NaN, Float.NEGATIVE_INFINITY), +1);
    }

    @Test
    public void testCompareFloat_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(Float.compare(Float.POSITIVE_INFINITY, Float.NaN), -1);
    }

    @Test
    public void testCompareFloat_11_oe() {
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
        assertEquals(0, Float.compare(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY));
    }

    @Test
    public void testCompareFloat_12_oe() {
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
        assertEquals(Float.compare(Float.POSITIVE_INFINITY, Float.MAX_VALUE), +1);
    }

    @Test
    public void testCompareFloat_13_oe() {
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
        assertEquals(Float.compare(Float.POSITIVE_INFINITY, 1.2f), +1);
    }

    @Test
    public void testCompareFloat_14_oe() {
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
        assertEquals(Float.compare(Float.POSITIVE_INFINITY, 0.0f), +1);
    }

    @Test
    public void testCompareFloat_15_oe() {
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
        assertEquals(Float.compare(Float.POSITIVE_INFINITY, -0.0f), +1);
    }

    @Test
    public void testCompareFloat_16_oe() {
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
        assertEquals(Float.compare(Float.POSITIVE_INFINITY, -1.2f), +1);
    }

    @Test
    public void testCompareFloat_17_oe() {
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
        assertEquals(Float.compare(Float.POSITIVE_INFINITY, -Float.MAX_VALUE), +1);
    }

    @Test
    public void testCompareFloat_18_oe() {
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
        assertEquals(Float.compare(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY), +1);
    }

    @Test
    public void testCompareFloat_19_oe() {
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

        assertEquals(Float.compare(Float.MAX_VALUE, Float.NaN), -1);
    }

    @Test
    public void testCompareFloat_20_oe() {
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
        assertEquals(Float.compare(Float.MAX_VALUE, Float.POSITIVE_INFINITY), -1);
    }

    @Test
    public void testCompareFloat_21_oe() {
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
        assertEquals(0, Float.compare(Float.MAX_VALUE, Float.MAX_VALUE));
    }

    @Test
    public void testCompareFloat_22_oe() {
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
        assertEquals(Float.compare(Float.MAX_VALUE, 1.2f), +1);
    }

    @Test
    public void testCompareFloat_23_oe() {
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
        assertEquals(Float.compare(Float.MAX_VALUE, 0.0f), +1);
    }

    @Test
    public void testCompareFloat_24_oe() {
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
        assertEquals(Float.compare(Float.MAX_VALUE, -0.0f), +1);
    }

    @Test
    public void testCompareFloat_25_oe() {
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
        // removed other assertion
        assertEquals(Float.compare(Float.MAX_VALUE, -1.2f), +1);
    }

    @Test
    public void testCompareFloat_26_oe() {
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
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(Float.MAX_VALUE, -Float.MAX_VALUE), +1);
    }

    @Test
    public void testCompareFloat_27_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(Float.MAX_VALUE, Float.NEGATIVE_INFINITY), +1);
    }

    @Test
    public void testCompareFloat_28_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(Float.compare(1.2f, Float.NaN), -1);
    }

    @Test
    public void testCompareFloat_29_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(Float.compare(1.2f, Float.POSITIVE_INFINITY), -1);
    }

    @Test
    public void testCompareFloat_30_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(1.2f, Float.MAX_VALUE), -1);
    }

    @Test
    public void testCompareFloat_31_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, Float.compare(1.2f, 1.2f));
    }

    @Test
    public void testCompareFloat_32_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(1.2f, 0.0f), +1);
    }

    @Test
    public void testCompareFloat_33_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(1.2f, -0.0f), +1);
    }

    @Test
    public void testCompareFloat_34_oe() {
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
        assertEquals(Float.compare(1.2f, -1.2f), +1);
    }

    @Test
    public void testCompareFloat_35_oe() {
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
        assertEquals(Float.compare(1.2f, -Float.MAX_VALUE), +1);
    }

    @Test
    public void testCompareFloat_36_oe() {
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
        assertEquals(Float.compare(1.2f, Float.NEGATIVE_INFINITY), +1);
    }

    @Test
    public void testCompareFloat_37_oe() {
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

        assertEquals(Float.compare(0.0f, Float.NaN), -1);
    }

    @Test
    public void testCompareFloat_38_oe() {
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
        assertEquals(Float.compare(0.0f, Float.POSITIVE_INFINITY), -1);
    }

    @Test
    public void testCompareFloat_39_oe() {
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
        assertEquals(Float.compare(0.0f, Float.MAX_VALUE), -1);
    }

    @Test
    public void testCompareFloat_40_oe() {
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
        assertEquals(Float.compare(0.0f, 1.2f), -1);
    }

    @Test
    public void testCompareFloat_41_oe() {
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
        assertEquals(0, Float.compare(0.0f, 0.0f));
    }

    @Test
    public void testCompareFloat_42_oe() {
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
        assertEquals(Float.compare(0.0f, -0.0f), +1);
    }

    @Test
    public void testCompareFloat_43_oe() {
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
        assertEquals(Float.compare(0.0f, -1.2f), +1);
    }

    @Test
    public void testCompareFloat_44_oe() {
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
        assertEquals(Float.compare(0.0f, -Float.MAX_VALUE), +1);
    }

    @Test
    public void testCompareFloat_45_oe() {
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
        assertEquals(Float.compare(0.0f, Float.NEGATIVE_INFINITY), +1);
    }

    @Test
    public void testCompareFloat_46_oe() {
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

        assertEquals(Float.compare(-0.0f, Float.NaN), -1);
    }

    @Test
    public void testCompareFloat_47_oe() {
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
        assertEquals(Float.compare(-0.0f, Float.POSITIVE_INFINITY), -1);
    }

    @Test
    public void testCompareFloat_48_oe() {
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
        // removed other assertion
        assertEquals(Float.compare(-0.0f, Float.MAX_VALUE), -1);
    }

    @Test
    public void testCompareFloat_49_oe() {
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
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(-0.0f, 1.2f), -1);
    }

    @Test
    public void testCompareFloat_50_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(-0.0f, 0.0f), -1);
    }

    @Test
    public void testCompareFloat_51_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, Float.compare(-0.0f, -0.0f));
    }

    @Test
    public void testCompareFloat_52_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(-0.0f, -1.2f), +1);
    }

    @Test
    public void testCompareFloat_53_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(-0.0f, -Float.MAX_VALUE), +1);
    }

    @Test
    public void testCompareFloat_54_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(-0.0f, Float.NEGATIVE_INFINITY), +1);
    }

    @Test
    public void testCompareFloat_55_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(Float.compare(-1.2f, Float.NaN), -1);
    }

    @Test
    public void testCompareFloat_56_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(Float.compare(-1.2f, Float.POSITIVE_INFINITY), -1);
    }

    @Test
    public void testCompareFloat_57_oe() {
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
        assertEquals(Float.compare(-1.2f, Float.MAX_VALUE), -1);
    }

    @Test
    public void testCompareFloat_58_oe() {
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
        assertEquals(Float.compare(-1.2f, 1.2f), -1);
    }

    @Test
    public void testCompareFloat_59_oe() {
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
        assertEquals(Float.compare(-1.2f, 0.0f), -1);
    }

    @Test
    public void testCompareFloat_60_oe() {
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
        assertEquals(Float.compare(-1.2f, -0.0f), -1);
    }

    @Test
    public void testCompareFloat_61_oe() {
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
        assertEquals(0, Float.compare(-1.2f, -1.2f));
    }

    @Test
    public void testCompareFloat_62_oe() {
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
        assertEquals(Float.compare(-1.2f, -Float.MAX_VALUE), +1);
    }

    @Test
    public void testCompareFloat_63_oe() {
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
        assertEquals(Float.compare(-1.2f, Float.NEGATIVE_INFINITY), +1);
    }

    @Test
    public void testCompareFloat_64_oe() {
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

        assertEquals(Float.compare(-Float.MAX_VALUE, Float.NaN), -1);
    }

    @Test
    public void testCompareFloat_65_oe() {
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
        assertEquals(Float.compare(-Float.MAX_VALUE, Float.POSITIVE_INFINITY), -1);
    }

    @Test
    public void testCompareFloat_66_oe() {
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
        assertEquals(Float.compare(-Float.MAX_VALUE, Float.MAX_VALUE), -1);
    }

    @Test
    public void testCompareFloat_67_oe() {
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
        assertEquals(Float.compare(-Float.MAX_VALUE, 1.2f), -1);
    }

    @Test
    public void testCompareFloat_68_oe() {
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
        assertEquals(Float.compare(-Float.MAX_VALUE, 0.0f), -1);
    }

    @Test
    public void testCompareFloat_69_oe() {
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
        assertEquals(Float.compare(-Float.MAX_VALUE, -0.0f), -1);
    }

    @Test
    public void testCompareFloat_70_oe() {
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
        assertEquals(Float.compare(-Float.MAX_VALUE, -1.2f), -1);
    }

    @Test
    public void testCompareFloat_71_oe() {
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
        // removed other assertion
        assertEquals(0, Float.compare(-Float.MAX_VALUE, -Float.MAX_VALUE));
    }

    @Test
    public void testCompareFloat_72_oe() {
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
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(-Float.MAX_VALUE, Float.NEGATIVE_INFINITY), +1);
    }

    @Test
    public void testCompareFloat_73_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(Float.compare(Float.NEGATIVE_INFINITY, Float.NaN), -1);
    }

    @Test
    public void testCompareFloat_74_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(Float.compare(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY), -1);
    }

    @Test
    public void testCompareFloat_75_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(Float.NEGATIVE_INFINITY, Float.MAX_VALUE), -1);
    }

    @Test
    public void testCompareFloat_76_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(Float.NEGATIVE_INFINITY, 1.2f), -1);
    }

    @Test
    public void testCompareFloat_77_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(Float.NEGATIVE_INFINITY, 0.0f), -1);
    }

    @Test
    public void testCompareFloat_78_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(Float.NEGATIVE_INFINITY, -0.0f), -1);
    }

    @Test
    public void testCompareFloat_79_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.compare(Float.NEGATIVE_INFINITY, -1.2f), -1);
    }

    @Test
    public void testCompareFloat_80_oe() {
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
        assertEquals(Float.compare(Float.NEGATIVE_INFINITY, -Float.MAX_VALUE), -1);
    }

    @Test
    public void testCompareFloat_81_oe() {
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
        assertEquals(0, Float.compare(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY));
    }

    @Test
    public void testConstants_1_oe() {
        assertTrue(NumberUtils.LONG_ZERO instanceof Long);
    }

    @Test
    public void testConstants_2_oe() {
        // removed other assertion
        assertTrue(NumberUtils.LONG_ONE instanceof Long);
    }

    @Test
    public void testConstants_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.LONG_MINUS_ONE instanceof Long);
    }

    @Test
    public void testConstants_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.INTEGER_ZERO instanceof Integer);
    }

    @Test
    public void testConstants_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.INTEGER_ONE instanceof Integer);
    }

    @Test
    public void testConstants_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.INTEGER_MINUS_ONE instanceof Integer);
    }

    @Test
    public void testConstants_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.SHORT_ZERO instanceof Short);
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
        assertTrue(NumberUtils.SHORT_ONE instanceof Short);
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
        assertTrue(NumberUtils.SHORT_MINUS_ONE instanceof Short);
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
        assertTrue(NumberUtils.BYTE_ZERO instanceof Byte);
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
        assertTrue(NumberUtils.BYTE_ONE instanceof Byte);
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
        assertTrue(NumberUtils.BYTE_MINUS_ONE instanceof Byte);
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
        assertTrue(NumberUtils.DOUBLE_ZERO instanceof Double);
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
        assertTrue(NumberUtils.DOUBLE_ONE instanceof Double);
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
        assertTrue(NumberUtils.DOUBLE_MINUS_ONE instanceof Double);
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
        assertTrue(NumberUtils.FLOAT_ZERO instanceof Float);
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
        assertTrue(NumberUtils.FLOAT_ONE instanceof Float);
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
        assertTrue(NumberUtils.FLOAT_MINUS_ONE instanceof Float);
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

        assertEquals(0, NumberUtils.LONG_ZERO.longValue());
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
        assertEquals(1, NumberUtils.LONG_ONE.longValue());
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
        assertEquals(NumberUtils.LONG_MINUS_ONE.longValue(), -1);
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
        assertEquals(0, NumberUtils.INTEGER_ZERO.intValue());
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
        assertEquals(1, NumberUtils.INTEGER_ONE.intValue());
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
        assertEquals(NumberUtils.INTEGER_MINUS_ONE.intValue(), -1);
    }

    @Test
    public void testConstants_25_oe() {
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
        // removed other assertion
        assertEquals(0, NumberUtils.SHORT_ZERO.shortValue());
    }

    @Test
    public void testConstants_26_oe() {
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
        // removed other assertion
        // removed other assertion
        assertEquals(1, NumberUtils.SHORT_ONE.shortValue());
    }

    @Test
    public void testConstants_27_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(NumberUtils.SHORT_MINUS_ONE.shortValue(), -1);
    }

    @Test
    public void testConstants_28_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, NumberUtils.BYTE_ZERO.byteValue());
    }

    @Test
    public void testConstants_29_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, NumberUtils.BYTE_ONE.byteValue());
    }

    @Test
    public void testConstants_30_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(NumberUtils.BYTE_MINUS_ONE.byteValue(), -1);
    }

    @Test
    public void testConstants_31_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0.0d, NumberUtils.DOUBLE_ZERO.doubleValue());
    }

    @Test
    public void testConstants_32_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1.0d, NumberUtils.DOUBLE_ONE.doubleValue());
    }

    @Test
    public void testConstants_33_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(NumberUtils.DOUBLE_MINUS_ONE.doubleValue(), -1.0d);
    }

    @Test
    public void testConstants_34_oe() {
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
        assertEquals(0.0f, NumberUtils.FLOAT_ZERO.floatValue());
    }

    @Test
    public void testConstants_35_oe() {
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
        assertEquals(1.0f, NumberUtils.FLOAT_ONE.floatValue());
    }

    @Test
    public void testConstants_36_oe() {
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
        assertEquals(NumberUtils.FLOAT_MINUS_ONE.floatValue(), -1.0f);
    }

    @Test
    public void testConstructor_1_oe() {
        assertNotNull(new NumberUtils());
    }

    @Test
    public void testConstructor_2_oe() {
        // removed other assertion
        final Constructor<?>[] cons = NumberUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
    }

    @Test
    public void testConstructor_3_oe() {
        // removed other assertion
        final Constructor<?>[] cons = NumberUtils.class.getDeclaredConstructors();
        // removed other assertion
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
    }

    @Test
    public void testConstructor_4_oe() {
        // removed other assertion
        final Constructor<?>[] cons = NumberUtils.class.getDeclaredConstructors();
        // removed other assertion
        // removed other assertion
        assertTrue(Modifier.isPublic(NumberUtils.class.getModifiers()));
    }

    @Test
    public void testConstructor_5_oe() {
        // removed other assertion
        final Constructor<?>[] cons = NumberUtils.class.getDeclaredConstructors();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(Modifier.isFinal(NumberUtils.class.getModifiers()));
    }

    @Test
    public void testCreateBigDecimal_1_oe() {
        assertEquals(new BigDecimal("1234.5"),NumberUtils.createBigDecimal("1234.5"),"createBigDecimal(String)failed");
    }

    @Test
    public void testCreateBigDecimal_2_oe() {
        // removed other assertion
        assertNull(NumberUtils.createBigDecimal(null), "createBigDecimal(null) failed");
    }

    @Test
    public void testCreateBigInteger_1_oe() {
        assertEquals(new BigInteger("12345"), NumberUtils.createBigInteger("12345"), "createBigInteger(String) failed");
    }

    @Test
    public void testCreateBigInteger_2_oe() {
        // removed other assertion
        assertNull(NumberUtils.createBigInteger(null), "createBigInteger(null) failed");
    }

    @Test
    public void testCreateBigInteger_3_oe() {
        // removed other assertion
        // removed other assertion
        this.testCreateBigIntegerFailure("");
        this.testCreateBigIntegerFailure(" ");
        this.testCreateBigIntegerFailure("\b\t\n\f\r");
        // Funky whitespaces
        this.testCreateBigIntegerFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
        assertEquals(new BigInteger("255"), NumberUtils.createBigInteger("0xff"), "createBigInteger(String) failed");
    }

    @Test
    public void testCreateBigInteger_4_oe() {
        // removed other assertion
        // removed other assertion
        this.testCreateBigIntegerFailure("");
        this.testCreateBigIntegerFailure(" ");
        this.testCreateBigIntegerFailure("\b\t\n\f\r");
        // Funky whitespaces
        this.testCreateBigIntegerFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
        // removed other assertion
        assertEquals(new BigInteger("255"), NumberUtils.createBigInteger("0Xff"), "createBigInteger(String) failed");
    }

    @Test
    public void testCreateBigInteger_5_oe() {
        // removed other assertion
        // removed other assertion
        this.testCreateBigIntegerFailure("");
        this.testCreateBigIntegerFailure(" ");
        this.testCreateBigIntegerFailure("\b\t\n\f\r");
        // Funky whitespaces
        this.testCreateBigIntegerFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
        // removed other assertion
        // removed other assertion
        assertEquals(new BigInteger("255"), NumberUtils.createBigInteger("#ff"), "createBigInteger(String) failed");
    }

    @Test
    public void testCreateBigInteger_6_oe() {
        // removed other assertion
        // removed other assertion
        this.testCreateBigIntegerFailure("");
        this.testCreateBigIntegerFailure(" ");
        this.testCreateBigIntegerFailure("\b\t\n\f\r");
        // Funky whitespaces
        this.testCreateBigIntegerFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new BigInteger("-255"), NumberUtils.createBigInteger("-0xff"), "createBigInteger(String) failed");
    }

    @Test
    public void testCreateBigInteger_7_oe() {
        // removed other assertion
        // removed other assertion
        this.testCreateBigIntegerFailure("");
        this.testCreateBigIntegerFailure(" ");
        this.testCreateBigIntegerFailure("\b\t\n\f\r");
        // Funky whitespaces
        this.testCreateBigIntegerFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new BigInteger("255"), NumberUtils.createBigInteger("0377"), "createBigInteger(String) failed");
    }

    @Test
    public void testCreateBigInteger_8_oe() {
        // removed other assertion
        // removed other assertion
        this.testCreateBigIntegerFailure("");
        this.testCreateBigIntegerFailure(" ");
        this.testCreateBigIntegerFailure("\b\t\n\f\r");
        // Funky whitespaces
        this.testCreateBigIntegerFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new BigInteger("-255"), NumberUtils.createBigInteger("-0377"), "createBigInteger(String) failed");
    }

    @Test
    public void testCreateBigInteger_9_oe() {
        // removed other assertion
        // removed other assertion
        this.testCreateBigIntegerFailure("");
        this.testCreateBigIntegerFailure(" ");
        this.testCreateBigIntegerFailure("\b\t\n\f\r");
        // Funky whitespaces
        this.testCreateBigIntegerFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new BigInteger("-255"), NumberUtils.createBigInteger("-0377"), "createBigInteger(String) failed");
    }

    @Test
    public void testCreateBigInteger_10_oe() {
        // removed other assertion
        // removed other assertion
        this.testCreateBigIntegerFailure("");
        this.testCreateBigIntegerFailure(" ");
        this.testCreateBigIntegerFailure("\b\t\n\f\r");
        // Funky whitespaces
        this.testCreateBigIntegerFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new BigInteger("-0"), NumberUtils.createBigInteger("-0"), "createBigInteger(String) failed");
    }

    @Test
    public void testCreateBigInteger_11_oe() {
        // removed other assertion
        // removed other assertion
        this.testCreateBigIntegerFailure("");
        this.testCreateBigIntegerFailure(" ");
        this.testCreateBigIntegerFailure("\b\t\n\f\r");
        // Funky whitespaces
        this.testCreateBigIntegerFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new BigInteger("0"), NumberUtils.createBigInteger("0"), "createBigInteger(String) failed");
    }

    @Test
    public void testCreateDouble_1_oe() {
        assertEquals(Double.valueOf("1234.5"), NumberUtils.createDouble("1234.5"), "createDouble(String) failed");
    }

    @Test
    public void testCreateDouble_2_oe() {
        // removed other assertion
        assertNull(NumberUtils.createDouble(null), "createDouble(null) failed");
    }

    @Test
    public void testCreateFloat_1_oe() {
        assertEquals(Float.valueOf("1234.5"), NumberUtils.createFloat("1234.5"), "createFloat(String) failed");
    }

    @Test
    public void testCreateFloat_2_oe() {
        // removed other assertion
        assertNull(NumberUtils.createFloat(null), "createFloat(null) failed");
    }

    @Test
    public void testCreateInteger_1_oe() {
        assertEquals(Integer.valueOf("12345"), NumberUtils.createInteger("12345"), "createInteger(String) failed");
    }

    @Test
    public void testCreateInteger_2_oe() {
        // removed other assertion
        assertNull(NumberUtils.createInteger(null), "createInteger(null) failed");
    }

    @Test
    public void testCreateLong_1_oe() {
        assertEquals(Long.valueOf("12345"), NumberUtils.createLong("12345"), "createLong(String) failed");
    }

    @Test
    public void testCreateLong_2_oe() {
        // removed other assertion
        assertNull(NumberUtils.createLong(null), "createLong(null) failed");
    }

    @Test
    public void testCreateNumber_1_oe() {
        // a lot of things can go wrong
        assertEquals(Float.valueOf("1234.5"), NumberUtils.createNumber("1234.5"), "createNumber(String) 1 failed");
    }

    @Test
    public void testCreateNumber_2_oe() {
        // a lot of things can go wrong
        // removed other assertion
        assertEquals(Integer.valueOf("12345"), NumberUtils.createNumber("12345"), "createNumber(String) 2 failed");
    }

    @Test
    public void testCreateNumber_3_oe() {
        // a lot of things can go wrong
        // removed other assertion
        // removed other assertion
        assertEquals(Double.valueOf("1234.5"), NumberUtils.createNumber("1234.5D"), "createNumber(String) 3 failed");
    }

    @Test
    public void testCreateNumber_4_oe() {
        // a lot of things can go wrong
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.valueOf("1234.5"), NumberUtils.createNumber("1234.5d"), "createNumber(String) 3 failed");
    }

    @Test
    public void testCreateNumber_5_oe() {
        // a lot of things can go wrong
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.valueOf("1234.5"), NumberUtils.createNumber("1234.5F"), "createNumber(String) 4 failed");
    }

    @Test
    public void testCreateNumber_6_oe() {
        // a lot of things can go wrong
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.valueOf("1234.5"), NumberUtils.createNumber("1234.5f"), "createNumber(String) 4 failed");
    }

    @Test
    public void testCreateNumber_7_oe() {
        // a lot of things can go wrong
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.valueOf(Integer.MAX_VALUE + 1L),NumberUtils.createNumber("" +(Integer.MAX_VALUE + 1L)),"createNumber(String)5 failed");
    }

    @Test
    public void testCreateNumber_8_oe() {
        // a lot of things can go wrong
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.valueOf(12345), NumberUtils.createNumber("12345L"), "createNumber(String) 6 failed");
    }

    @Test
    public void testCreateNumber_9_oe() {
        // a lot of things can go wrong
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.valueOf(12345), NumberUtils.createNumber("12345l"), "createNumber(String) 6 failed");
    }

    @Test
    public void testCreateNumber_10_oe() {
        // a lot of things can go wrong
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.valueOf("-1234.5"), NumberUtils.createNumber("-1234.5"), "createNumber(String) 7 failed");
    }

    @Test
    public void testCreateNumber_11_oe() {
        // a lot of things can go wrong
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
        assertEquals(Integer.valueOf("-12345"), NumberUtils.createNumber("-12345"), "createNumber(String) 8 failed");
    }

    @Test
    public void testCreateNumber_12_oe() {
        // a lot of things can go wrong
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
        assertEquals(0xFADE, NumberUtils.createNumber("0xFADE").intValue(), "createNumber(String) 9a failed");
    }

    @Test
    public void testCreateNumber_13_oe() {
        // a lot of things can go wrong
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
        assertEquals(0xFADE, NumberUtils.createNumber("0Xfade").intValue(), "createNumber(String) 9b failed");
    }

    @Test
    public void testCreateNumber_14_oe() {
        // a lot of things can go wrong
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
        assertEquals(-0xFADE, NumberUtils.createNumber("-0xFADE").intValue(), "createNumber(String) 10a failed");
    }

    @Test
    public void testCreateNumber_15_oe() {
        // a lot of things can go wrong
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
        assertEquals(-0xFADE, NumberUtils.createNumber("-0Xfade").intValue(), "createNumber(String) 10b failed");
    }

    @Test
    public void testCreateNumber_16_oe() {
        // a lot of things can go wrong
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
        assertEquals(Double.valueOf("1.1E200"), NumberUtils.createNumber("1.1E200"), "createNumber(String) 11 failed");
    }

    @Test
    public void testCreateNumber_17_oe() {
        // a lot of things can go wrong
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
        assertEquals(Float.valueOf("1.1E20"), NumberUtils.createNumber("1.1E20"), "createNumber(String) 12 failed");
    }

    @Test
    public void testCreateNumber_18_oe() {
        // a lot of things can go wrong
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
        assertEquals(Double.valueOf("-1.1E200"),NumberUtils.createNumber("-1.1E200"),"createNumber(String)13 failed");
    }

    @Test
    public void testCreateNumber_19_oe() {
        // a lot of things can go wrong
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
        assertEquals(Double.valueOf("1.1E-200"),NumberUtils.createNumber("1.1E-200"),"createNumber(String)14 failed");
    }

    @Test
    public void testCreateNumber_20_oe() {
        // a lot of things can go wrong
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
        assertNull(NumberUtils.createNumber(null), "createNumber(null) failed");
    }

    @Test
    public void testCreateNumber_21_oe() {
        // a lot of things can go wrong
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
        assertEquals(new BigInteger("12345678901234567890"),NumberUtils.createNumber("12345678901234567890L"),"createNumber(String)failed");
    }

    @Test
    public void testCreateNumber_22_oe() {
        // a lot of things can go wrong
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

        assertEquals(new BigDecimal("1.1E-700"),NumberUtils.createNumber("1.1E-700F"),"createNumber(String)15 failed");
    }

    @Test
    public void testCreateNumber_23_oe() {
        // a lot of things can go wrong
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

        assertEquals(Long.valueOf("10" + Integer.MAX_VALUE),NumberUtils.createNumber("10" + Integer.MAX_VALUE + "L"),"createNumber(String)16 failed");
    }

    @Test
    public void testCreateNumber_24_oe() {
        // a lot of things can go wrong
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
        assertEquals(Long.valueOf("10" + Integer.MAX_VALUE),NumberUtils.createNumber("10" + Integer.MAX_VALUE),"createNumber(String)17 failed");
    }

    @Test
    public void testCreateNumber_25_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        assertEquals(new BigInteger("10" + Long.MAX_VALUE),NumberUtils.createNumber("10" + Long.MAX_VALUE),"createNumber(String)18 failed");
    }

    @Test
    public void testCreateNumber_26_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        assertEquals(Float.valueOf("2."), NumberUtils.createNumber("2."), "createNumber(String) LANG-521 failed");
    }

    @Test
    public void testCreateNumber_27_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        assertFalse(checkCreateNumber("1eE"), "createNumber(String) succeeded");
    }

    @Test
    public void testCreateNumber_28_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        assertEquals(Double.valueOf(Double.MAX_VALUE),NumberUtils.createNumber("" + Double.MAX_VALUE),"createNumber(String)LANG-693 failed");
    }

    @Test
    public void testCreateNumber_29_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        assertNotNull(bigNum);
    }

    @Test
    public void testCreateNumber_30_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        assertEquals(BigDecimal.class, bigNum.getClass());
    }

    @Test
    public void testCreateNumber_31_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        // removed other assertion

        // LANG-1018
        assertEquals(Double.valueOf("-160952.54"),NumberUtils.createNumber("-160952.54"),"createNumber(String)LANG-1018 failed");
    }

    @Test
    public void testCreateNumber_32_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        // removed other assertion

        // LANG-1018
        // removed other assertion
        // LANG-1187
        assertEquals(Double.valueOf("6264583.33"),NumberUtils.createNumber("6264583.33"),"createNumber(String)LANG-1187 failed");
    }

    @Test
    public void testCreateNumber_33_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        // removed other assertion

        // LANG-1018
        // removed other assertion
        // LANG-1187
        // removed other assertion
        // LANG-1215
        assertEquals(Double.valueOf("193343.82"),NumberUtils.createNumber("193343.82"),"createNumber(String)LANG-1215 failed");
    }

    @Test
    public void testCreateNumber_34_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        // removed other assertion

        // LANG-1018
        // removed other assertion
        // LANG-1187
        // removed other assertion
        // LANG-1215
        // removed other assertion
        // LANG-1060
        assertEquals(Double.valueOf("001234.5678"),NumberUtils.createNumber("001234.5678"),"createNumber(String)LANG-1060a failed");
    }

    @Test
    public void testCreateNumber_35_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        // removed other assertion

        // LANG-1018
        // removed other assertion
        // LANG-1187
        // removed other assertion
        // LANG-1215
        // removed other assertion
        // LANG-1060
        // removed other assertion
        assertEquals(Double.valueOf("+001234.5678"),NumberUtils.createNumber("+001234.5678"),"createNumber(String)LANG-1060b failed");
    }

    @Test
    public void testCreateNumber_36_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        // removed other assertion

        // LANG-1018
        // removed other assertion
        // LANG-1187
        // removed other assertion
        // LANG-1215
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        assertEquals(Double.valueOf("-001234.5678"),NumberUtils.createNumber("-001234.5678"),"createNumber(String)LANG-1060c failed");
    }

    @Test
    public void testCreateNumber_37_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        // removed other assertion

        // LANG-1018
        // removed other assertion
        // LANG-1187
        // removed other assertion
        // LANG-1215
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.valueOf("0000.00000"),NumberUtils.createNumber("0000.00000d"),"createNumber(String)LANG-1060d failed");
    }

    @Test
    public void testCreateNumber_38_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        // removed other assertion

        // LANG-1018
        // removed other assertion
        // LANG-1187
        // removed other assertion
        // LANG-1215
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.valueOf("001234.56"),NumberUtils.createNumber("001234.56"),"createNumber(String)LANG-1060e failed");
    }

    @Test
    public void testCreateNumber_39_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        // removed other assertion

        // LANG-1018
        // removed other assertion
        // LANG-1187
        // removed other assertion
        // LANG-1215
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.valueOf("+001234.56"),NumberUtils.createNumber("+001234.56"),"createNumber(String)LANG-1060f failed");
    }

    @Test
    public void testCreateNumber_40_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        // removed other assertion

        // LANG-1018
        // removed other assertion
        // LANG-1187
        // removed other assertion
        // LANG-1215
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.valueOf("-001234.56"),NumberUtils.createNumber("-001234.56"),"createNumber(String)LANG-1060g failed");
    }

    @Test
    public void testCreateNumber_41_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        // removed other assertion

        // LANG-1018
        // removed other assertion
        // LANG-1187
        // removed other assertion
        // LANG-1215
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.valueOf("0000.10"),NumberUtils.createNumber("0000.10"),"createNumber(String)LANG-1060h failed");
    }

    @Test
    public void testCreateNumber_42_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        // removed other assertion

        // LANG-1018
        // removed other assertion
        // LANG-1187
        // removed other assertion
        // LANG-1215
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.valueOf("001.1E20"),NumberUtils.createNumber("001.1E20"),"createNumber(String)LANG-1060i failed");
    }

    @Test
    public void testCreateNumber_43_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        // removed other assertion

        // LANG-1018
        // removed other assertion
        // LANG-1187
        // removed other assertion
        // LANG-1215
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.valueOf("+001.1E20"),NumberUtils.createNumber("+001.1E20"),"createNumber(String)LANG-1060j failed");
    }

    @Test
    public void testCreateNumber_44_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        // removed other assertion

        // LANG-1018
        // removed other assertion
        // LANG-1187
        // removed other assertion
        // LANG-1215
        // removed other assertion
        // LANG-1060
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
        assertEquals(Float.valueOf("-001.1E20"),NumberUtils.createNumber("-001.1E20"),"createNumber(String)LANG-1060k failed");
    }

    @Test
    public void testCreateNumber_45_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        // removed other assertion

        // LANG-1018
        // removed other assertion
        // LANG-1187
        // removed other assertion
        // LANG-1215
        // removed other assertion
        // LANG-1060
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
        assertEquals(Double.valueOf("001.1E200"),NumberUtils.createNumber("001.1E200"),"createNumber(String)LANG-1060l failed");
    }

    @Test
    public void testCreateNumber_46_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        // removed other assertion

        // LANG-1018
        // removed other assertion
        // LANG-1187
        // removed other assertion
        // LANG-1215
        // removed other assertion
        // LANG-1060
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
        assertEquals(Double.valueOf("+001.1E200"),NumberUtils.createNumber("+001.1E200"),"createNumber(String)LANG-1060m failed");
    }

    @Test
    public void testCreateNumber_47_oe() {
        // a lot of things can go wrong
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
        // removed other assertion
        // removed other assertion

        // LANG-521
        // removed other assertion

        // LANG-638
        // removed other assertion

        // LANG-693
        // removed other assertion

        // LANG-822
        // ensure that the underlying negative number would create a BigDecimal
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        // removed other assertion
        // removed other assertion

        // LANG-1018
        // removed other assertion
        // LANG-1187
        // removed other assertion
        // LANG-1215
        // removed other assertion
        // LANG-1060
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
        assertEquals(Double.valueOf("-001.1E200"),NumberUtils.createNumber("-001.1E200"),"createNumber(String)LANG-1060n failed");
    }

    @Test
    // Check that the code fails to create a valid number when preceded by -- rather than -
    public void testCreateNumberFailure_1_1_oe() throws Exception {
        try {
    NumberUtils.createNumber("--1.1E-700F");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    // Check that the code fails to create a valid number when both e and E are present (with decimal)
    public void testCreateNumberFailure_2_1_oe() throws Exception {
        try {
    NumberUtils.createNumber("-1.1E+0-7e00");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    // Check that the code fails to create a valid number when both e and E are present (no decimal)
    public void testCreateNumberFailure_3_1_oe() throws Exception {
        try {
    NumberUtils.createNumber("-11E+0-7e00");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    // Check that the code fails to create a valid number when both e and E are present (no decimal)
    public void testCreateNumberFailure_4_1_oe() throws Exception {
        try {
    NumberUtils.createNumber("1eE+00001");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    // Check that the code fails to create a valid number when there are multiple trailing 'f' characters (LANG-1205)
    public void testCreateNumberFailure_5_1_oe() throws Exception {
        try {
    NumberUtils.createNumber("1234.5ff");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    // Check that the code fails to create a valid number when there are multiple trailing 'F' characters (LANG-1205)
    public void testCreateNumberFailure_6_1_oe() throws Exception {
        try {
    NumberUtils.createNumber("1234.5FF");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    // Check that the code fails to create a valid number when there are multiple trailing 'd' characters (LANG-1205)
    public void testCreateNumberFailure_7_1_oe() throws Exception {
        try {
    NumberUtils.createNumber("1234.5dd");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    // Check that the code fails to create a valid number when there are multiple trailing 'D' characters (LANG-1205)
    public void testCreateNumberFailure_8_1_oe() throws Exception {
        try {
    NumberUtils.createNumber("1234.5DD");
    fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    public void testCreateNumberMagnitude_1_oe() {
        // Test Float.MAX_VALUE, and same with +1 in final digit to check conversion changes to next Number type
        assertEquals(Float.valueOf(Float.MAX_VALUE), NumberUtils.createNumber("3.4028235e+38"));
    }

    @Test
    public void testCreateNumberMagnitude_2_oe() {
        // Test Float.MAX_VALUE, and same with +1 in final digit to check conversion changes to next Number type
        // removed other assertion
        assertEquals(Double.valueOf(3.4028236e+38), NumberUtils.createNumber("3.4028236e+38"));
    }

    @Test
    public void testCreateNumberMagnitude_3_oe() {
        // Test Float.MAX_VALUE, and same with +1 in final digit to check conversion changes to next Number type
        // removed other assertion
        // removed other assertion

        // Test Double.MAX_VALUE
        assertEquals(Double.valueOf(Double.MAX_VALUE), NumberUtils.createNumber("1.7976931348623157e+308"));
    }

    @Test
    public void testCreateNumberMagnitude_4_oe() {
        // Test Float.MAX_VALUE, and same with +1 in final digit to check conversion changes to next Number type
        // removed other assertion
        // removed other assertion

        // Test Double.MAX_VALUE
        // removed other assertion
        // Test with +2 in final digit (+1 does not cause roll-over to BigDecimal)
        assertEquals(new BigDecimal("1.7976931348623159e+308"), NumberUtils.createNumber("1.7976931348623159e+308"));
    }

    @Test
    public void testCreateNumberMagnitude_5_oe() {
        // Test Float.MAX_VALUE, and same with +1 in final digit to check conversion changes to next Number type
        // removed other assertion
        // removed other assertion

        // Test Double.MAX_VALUE
        // removed other assertion
        // Test with +2 in final digit (+1 does not cause roll-over to BigDecimal)
        // removed other assertion

        assertEquals(Integer.valueOf(0x12345678), NumberUtils.createNumber("0x12345678"));
    }

    @Test
    public void testCreateNumberMagnitude_6_oe() {
        // Test Float.MAX_VALUE, and same with +1 in final digit to check conversion changes to next Number type
        // removed other assertion
        // removed other assertion

        // Test Double.MAX_VALUE
        // removed other assertion
        // Test with +2 in final digit (+1 does not cause roll-over to BigDecimal)
        // removed other assertion

        // removed other assertion
        assertEquals(Long.valueOf(0x123456789L), NumberUtils.createNumber("0x123456789"));
    }

    @Test
    public void testCreateNumberMagnitude_7_oe() {
        // Test Float.MAX_VALUE, and same with +1 in final digit to check conversion changes to next Number type
        // removed other assertion
        // removed other assertion

        // Test Double.MAX_VALUE
        // removed other assertion
        // Test with +2 in final digit (+1 does not cause roll-over to BigDecimal)
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(Long.valueOf(0x7fffffffffffffffL), NumberUtils.createNumber("0x7fffffffffffffff"));
    }

    @Test
    public void testCreateNumberMagnitude_8_oe() {
        // Test Float.MAX_VALUE, and same with +1 in final digit to check conversion changes to next Number type
        // removed other assertion
        // removed other assertion

        // Test Double.MAX_VALUE
        // removed other assertion
        // Test with +2 in final digit (+1 does not cause roll-over to BigDecimal)
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // Does not appear to be a way to create a literal BigInteger of this magnitude
        assertEquals(new BigInteger("7fffffffffffffff0", 16), NumberUtils.createNumber("0x7fffffffffffffff0"));
    }

    @Test
    public void testCreateNumberMagnitude_9_oe() {
        // Test Float.MAX_VALUE, and same with +1 in final digit to check conversion changes to next Number type
        // removed other assertion
        // removed other assertion

        // Test Double.MAX_VALUE
        // removed other assertion
        // Test with +2 in final digit (+1 does not cause roll-over to BigDecimal)
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // Does not appear to be a way to create a literal BigInteger of this magnitude
        // removed other assertion

        assertEquals(Long.valueOf(0x7fffffffffffffffL), NumberUtils.createNumber("#7fffffffffffffff"));
    }

    @Test
    public void testCreateNumberMagnitude_10_oe() {
        // Test Float.MAX_VALUE, and same with +1 in final digit to check conversion changes to next Number type
        // removed other assertion
        // removed other assertion

        // Test Double.MAX_VALUE
        // removed other assertion
        // Test with +2 in final digit (+1 does not cause roll-over to BigDecimal)
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // Does not appear to be a way to create a literal BigInteger of this magnitude
        // removed other assertion

        // removed other assertion
        assertEquals(new BigInteger("7fffffffffffffff0", 16), NumberUtils.createNumber("#7fffffffffffffff0"));
    }

    @Test
    public void testCreateNumberMagnitude_11_oe() {
        // Test Float.MAX_VALUE, and same with +1 in final digit to check conversion changes to next Number type
        // removed other assertion
        // removed other assertion

        // Test Double.MAX_VALUE
        // removed other assertion
        // Test with +2 in final digit (+1 does not cause roll-over to BigDecimal)
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // Does not appear to be a way to create a literal BigInteger of this magnitude
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(Integer.valueOf(017777777777),NumberUtils.createNumber("017777777777"));// 31 bits assertEquals(Long.valueOf(037777777777L),NumberUtils.createNumber("037777777777"));// 32 bits assertEquals(Long.valueOf(0777777777777777777777L),NumberUtils.createNumber("0777777777777777777777"));
    }

    @Test
    public void testCreateNumberMagnitude_12_oe() {
        // Test Float.MAX_VALUE, and same with +1 in final digit to check conversion changes to next Number type
        // removed other assertion
        // removed other assertion

        // Test Double.MAX_VALUE
        // removed other assertion
        // Test with +2 in final digit (+1 does not cause roll-over to BigDecimal)
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // Does not appear to be a way to create a literal BigInteger of this magnitude
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // 64 bits
        assertEquals(new BigInteger("1777777777777777777777", 8), NumberUtils.createNumber("01777777777777777777777"));
    }

    @Test
    public void testIsDigits_1_oe() {
        assertFalse(NumberUtils.isDigits(null), "isDigits(null) failed");
    }

    @Test
    public void testIsDigits_2_oe() {
        // removed other assertion
        assertFalse(NumberUtils.isDigits(""), "isDigits('') failed");
    }

    @Test
    public void testIsDigits_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.isDigits("12345"), "isDigits(String) failed");
    }

    @Test
    public void testIsDigits_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(NumberUtils.isDigits("1234.5"), "isDigits(String) neg 1 failed");
    }

    @Test
    public void testIsDigits_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(NumberUtils.isDigits("1ab"), "isDigits(String) neg 3 failed");
    }

    @Test
    public void testIsDigits_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(NumberUtils.isDigits("abc"), "isDigits(String) neg 4 failed");
    }

    @Test
    public void testIsParsable_1_oe() {
        assertFalse(NumberUtils.isParsable(null));
    }

    @Test
    public void testIsParsable_2_oe() {
        // removed other assertion
        assertFalse(NumberUtils.isParsable(""));
    }

    @Test
    public void testIsParsable_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(NumberUtils.isParsable("0xC1AB"));
    }

    @Test
    public void testIsParsable_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(NumberUtils.isParsable("65CBA2"));
    }

    @Test
    public void testIsParsable_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(NumberUtils.isParsable("pendro"));
    }

    @Test
    public void testIsParsable_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(NumberUtils.isParsable("64, 2"));
    }

    @Test
    public void testIsParsable_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(NumberUtils.isParsable("64.2.2"));
    }

    @Test
    public void testIsParsable_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(NumberUtils.isParsable("64."));
    }

    @Test
    public void testIsParsable_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(NumberUtils.isParsable("64L"));
    }

    @Test
    public void testIsParsable_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(NumberUtils.isParsable("-"));
    }

    @Test
    public void testIsParsable_11_oe() {
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
        assertFalse(NumberUtils.isParsable("--2"));
    }

    @Test
    public void testIsParsable_12_oe() {
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
        assertTrue(NumberUtils.isParsable("64.2"));
    }

    @Test
    public void testIsParsable_13_oe() {
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
        assertTrue(NumberUtils.isParsable("64"));
    }

    @Test
    public void testIsParsable_14_oe() {
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
        assertTrue(NumberUtils.isParsable("018"));
    }

    @Test
    public void testIsParsable_15_oe() {
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
        assertTrue(NumberUtils.isParsable(".18"));
    }

    @Test
    public void testIsParsable_16_oe() {
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
        assertTrue(NumberUtils.isParsable("-65"));
    }

    @Test
    public void testIsParsable_17_oe() {
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
        assertTrue(NumberUtils.isParsable("-018"));
    }

    @Test
    public void testIsParsable_18_oe() {
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
        assertTrue(NumberUtils.isParsable("-018.2"));
    }

    @Test
    public void testIsParsable_19_oe() {
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
        assertTrue(NumberUtils.isParsable("-.236"));
    }

    @Test
    public void testLang1087_1_oe() {
        // no sign cases
        assertEquals(Float.class, NumberUtils.createNumber("0.0").getClass());
    }

    @Test
    public void testLang1087_2_oe() {
        // no sign cases
        // removed other assertion
        assertEquals(Float.valueOf("0.0"), NumberUtils.createNumber("0.0"));
    }

    @Test
    public void testLang1087_3_oe() {
        // no sign cases
        // removed other assertion
        // removed other assertion
        // explicit positive sign cases
        assertEquals(Float.class, NumberUtils.createNumber("+0.0").getClass());
    }

    @Test
    public void testLang1087_4_oe() {
        // no sign cases
        // removed other assertion
        // removed other assertion
        // explicit positive sign cases
        // removed other assertion
        assertEquals(Float.valueOf("+0.0"), NumberUtils.createNumber("+0.0"));
    }

    @Test
    public void testLang1087_5_oe() {
        // no sign cases
        // removed other assertion
        // removed other assertion
        // explicit positive sign cases
        // removed other assertion
        // removed other assertion
        // negative sign cases
        assertEquals(Float.class, NumberUtils.createNumber("-0.0").getClass());
    }

    @Test
    public void testLang1087_6_oe() {
        // no sign cases
        // removed other assertion
        // removed other assertion
        // explicit positive sign cases
        // removed other assertion
        // removed other assertion
        // negative sign cases
        // removed other assertion
        assertEquals(Float.valueOf("-0.0"), NumberUtils.createNumber("-0.0"));
    }

    @Test
    public void testLang381_1_oe() {
        assertTrue(Double.isNaN(NumberUtils.min(1.2, 2.5, Double.NaN)));
    }

    @Test
    public void testLang381_2_oe() {
        // removed other assertion
        assertTrue(Double.isNaN(NumberUtils.max(1.2, 2.5, Double.NaN)));
    }

    @Test
    public void testLang381_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(Float.isNaN(NumberUtils.min(1.2f, 2.5f, Float.NaN)));
    }

    @Test
    public void testLang381_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(Float.isNaN(NumberUtils.max(1.2f, 2.5f, Float.NaN)));
    }

    @Test
    public void testLang381_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final double[] a = new double[] {1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN};
        assertTrue(Double.isNaN(NumberUtils.max(a)));
    }

    @Test
    public void testLang381_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final double[] a = new double[] {1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN};
        // removed other assertion
        assertTrue(Double.isNaN(NumberUtils.min(a)));
    }

    @Test
    public void testLang381_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final double[] a = new double[] {1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN};
        // removed other assertion
        // removed other assertion

        final double[] b = new double[] {Double.NaN, 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN};
        assertTrue(Double.isNaN(NumberUtils.max(b)));
    }

    @Test
    public void testLang381_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final double[] a = new double[] {1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN};
        // removed other assertion
        // removed other assertion

        final double[] b = new double[] {Double.NaN, 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN};
        // removed other assertion
        assertTrue(Double.isNaN(NumberUtils.min(b)));
    }

    @Test
    public void testLang381_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final double[] a = new double[] {1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN};
        // removed other assertion
        // removed other assertion

        final double[] b = new double[] {Double.NaN, 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN};
        // removed other assertion
        // removed other assertion

        final float[] aF = new float[] {1.2f, Float.NaN, 3.7f, 27.0f, 42.0f, Float.NaN};
        assertTrue(Float.isNaN(NumberUtils.max(aF)));
    }

    @Test
    public void testLang381_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final double[] a = new double[] {1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN};
        // removed other assertion
        // removed other assertion

        final double[] b = new double[] {Double.NaN, 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN};
        // removed other assertion
        // removed other assertion

        final float[] aF = new float[] {1.2f, Float.NaN, 3.7f, 27.0f, 42.0f, Float.NaN};
        // removed other assertion

        final float[] bF = new float[] {Float.NaN, 1.2f, Float.NaN, 3.7f, 27.0f, 42.0f, Float.NaN};
        assertTrue(Float.isNaN(NumberUtils.max(bF)));
    }

    @Test
    public void TestLang747_1_oe() {
        assertEquals(Integer.valueOf(0x8000), NumberUtils.createNumber("0x8000"));
    }

    @Test
    public void TestLang747_2_oe() {
        // removed other assertion
        assertEquals(Integer.valueOf(0x80000), NumberUtils.createNumber("0x80000"));
    }

    @Test
    public void TestLang747_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(0x800000), NumberUtils.createNumber("0x800000"));
    }

    @Test
    public void TestLang747_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(0x8000000), NumberUtils.createNumber("0x8000000"));
    }

    @Test
    public void TestLang747_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(0x7FFFFFFF), NumberUtils.createNumber("0x7FFFFFFF"));
    }

    @Test
    public void TestLang747_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.valueOf(0x80000000L), NumberUtils.createNumber("0x80000000"));
    }

    @Test
    public void TestLang747_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.valueOf(0xFFFFFFFFL), NumberUtils.createNumber("0xFFFFFFFF"));
    }

    @Test
    public void TestLang747_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
        assertEquals(Integer.valueOf(0x8000000), NumberUtils.createNumber("0x08000000"));
    }

    @Test
    public void TestLang747_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
        // removed other assertion
        assertEquals(Integer.valueOf(0x7FFFFFFF), NumberUtils.createNumber("0x007FFFFFFF"));
    }

    @Test
    public void TestLang747_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
        // removed other assertion
        // removed other assertion
        assertEquals(Long.valueOf(0x80000000L), NumberUtils.createNumber("0x080000000"));
    }

    @Test
    public void TestLang747_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.valueOf(0xFFFFFFFFL), NumberUtils.createNumber("0x00FFFFFFFF"));
    }

    @Test
    public void TestLang747_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(Long.valueOf(0x800000000L), NumberUtils.createNumber("0x800000000"));
    }

    @Test
    public void TestLang747_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(Long.valueOf(0x8000000000L), NumberUtils.createNumber("0x8000000000"));
    }

    @Test
    public void TestLang747_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(Long.valueOf(0x80000000000L), NumberUtils.createNumber("0x80000000000"));
    }

    @Test
    public void TestLang747_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.valueOf(0x800000000000L), NumberUtils.createNumber("0x800000000000"));
    }

    @Test
    public void TestLang747_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.valueOf(0x8000000000000L), NumberUtils.createNumber("0x8000000000000"));
    }

    @Test
    public void TestLang747_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.valueOf(0x80000000000000L), NumberUtils.createNumber("0x80000000000000"));
    }

    @Test
    public void TestLang747_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
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
        assertEquals(Long.valueOf(0x800000000000000L), NumberUtils.createNumber("0x800000000000000"));
    }

    @Test
    public void TestLang747_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
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
        assertEquals(Long.valueOf(0x7FFFFFFFFFFFFFFFL), NumberUtils.createNumber("0x7FFFFFFFFFFFFFFF"));
    }

    @Test
    public void TestLang747_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
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
        // N.B. Cannot use a hex constant such as 0x8000000000000000L here as that is interpreted as a negative long
        assertEquals(new BigInteger("8000000000000000", 16), NumberUtils.createNumber("0x8000000000000000"));
    }

    @Test
    public void TestLang747_21_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
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
        // N.B. Cannot use a hex constant such as 0x8000000000000000L here as that is interpreted as a negative long
        // removed other assertion
        assertEquals(new BigInteger("FFFFFFFFFFFFFFFF", 16), NumberUtils.createNumber("0xFFFFFFFFFFFFFFFF"));
    }

    @Test
    public void TestLang747_22_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
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
        // N.B. Cannot use a hex constant such as 0x8000000000000000L here as that is interpreted as a negative long
        // removed other assertion
        // removed other assertion

        // Leading zero tests
        assertEquals(Long.valueOf(0x80000000000000L), NumberUtils.createNumber("0x00080000000000000"));
    }

    @Test
    public void TestLang747_23_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
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
        // N.B. Cannot use a hex constant such as 0x8000000000000000L here as that is interpreted as a negative long
        // removed other assertion
        // removed other assertion

        // Leading zero tests
        // removed other assertion
        assertEquals(Long.valueOf(0x800000000000000L), NumberUtils.createNumber("0x0800000000000000"));
    }

    @Test
    public void TestLang747_24_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
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
        // N.B. Cannot use a hex constant such as 0x8000000000000000L here as that is interpreted as a negative long
        // removed other assertion
        // removed other assertion

        // Leading zero tests
        // removed other assertion
        // removed other assertion
        assertEquals(Long.valueOf(0x7FFFFFFFFFFFFFFFL), NumberUtils.createNumber("0x07FFFFFFFFFFFFFFF"));
    }

    @Test
    public void TestLang747_25_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
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
        // N.B. Cannot use a hex constant such as 0x8000000000000000L here as that is interpreted as a negative long
        // removed other assertion
        // removed other assertion

        // Leading zero tests
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // N.B. Cannot use a hex constant such as 0x8000000000000000L here as that is interpreted as a negative long
        assertEquals(new BigInteger("8000000000000000", 16), NumberUtils.createNumber("0x00008000000000000000"));
    }

    @Test
    public void TestLang747_26_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Leading zero tests
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
        // N.B. Cannot use a hex constant such as 0x8000000000000000L here as that is interpreted as a negative long
        // removed other assertion
        // removed other assertion

        // Leading zero tests
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // N.B. Cannot use a hex constant such as 0x8000000000000000L here as that is interpreted as a negative long
        // removed other assertion
        assertEquals(new BigInteger("FFFFFFFFFFFFFFFF", 16), NumberUtils.createNumber("0x0FFFFFFFFFFFFFFFF"));
    }

    @Test
    public void testMaxByte_1_oe() {
        assertEquals((byte) 5, NumberUtils.max((byte) 5), "max(byte[]) failed for array length 1");
    }

    @Test
    public void testMaxByte_2_oe() {
        // removed other assertion
        assertEquals((byte) 9, NumberUtils.max((byte) 6, (byte) 9), "max(byte[]) failed for array length 2");
    }

    @Test
    public void testMaxByte_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals((byte)10,NumberUtils.max((byte)-10,(byte)-5,(byte)0,(byte)5,(byte)10),"max(byte[])failed for array length 5");
    }

    @Test
    public void testMaxByte_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((byte) 10, NumberUtils.max((byte) -10, (byte) -5, (byte) 0, (byte) 5, (byte) 10));
    }

    @Test
    public void testMaxByte_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((byte) 10, NumberUtils.max((byte) -5, (byte) 0, (byte) 10, (byte) 5, (byte) -10));
    }

    @Test
    public void testMaxByte_emptyArray_1_oe() throws Exception {
        try {
    NumberUtils.max();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testMaxByte_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.max((byte[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMaxDouble_1_oe() throws Exception {
        final double[] d = null;
        try {
    NumberUtils.max(d);
    fail("NullPointerException: No exception was thrown for null input.");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMaxDouble_2_oe() throws Exception {
        final double[] d = null;
        // removed other assertion

        try {
    NumberUtils.max();
    fail("IllegalArgumentException: No exception was thrown for empty input.");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testMaxDouble_3_oe() {
        final double[] d = null;
        // removed other assertion

        // removed other assertion

        assertEquals(5.1f, NumberUtils.max(5.1f), "max(double[]) failed for array length 1");
    }

    @Test
    public void testMaxDouble_4_oe() {
        final double[] d = null;
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertEquals(9.2f, NumberUtils.max(6.3f, 9.2f), "max(double[]) failed for array length 2");
    }

    @Test
    public void testMaxDouble_5_oe() {
        final double[] d = null;
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(10.4f, NumberUtils.max(-10.5f, -5.6f, 0, 5.7f, 10.4f), "max(double[]) failed for float length 5");
    }

    @Test
    public void testMaxDouble_6_oe() {
        final double[] d = null;
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10, NumberUtils.max(-10, -5, 0, 5, 10), 0.0001);
    }

    @Test
    public void testMaxDouble_7_oe() {
        final double[] d = null;
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10, NumberUtils.max(-5, 0, 10, 5, -10), 0.0001);
    }

    @Test
    public void testMaxDouble_emptyArray_1_oe() throws Exception {
        try {
    NumberUtils.max();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testMaxDouble_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.max((double[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMaxFloat_1_oe() {
        assertEquals(5.1f, NumberUtils.max(5.1f), "max(float[]) failed for array length 1");
    }

    @Test
    public void testMaxFloat_2_oe() {
        // removed other assertion
        assertEquals(9.2f, NumberUtils.max(6.3f, 9.2f), "max(float[]) failed for array length 2");
    }

    @Test
    public void testMaxFloat_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(10.4f, NumberUtils.max(-10.5f, -5.6f, 0, 5.7f, 10.4f), "max(float[]) failed for float length 5");
    }

    @Test
    public void testMaxFloat_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10, NumberUtils.max(-10, -5, 0, 5, 10), 0.0001f);
    }

    @Test
    public void testMaxFloat_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10, NumberUtils.max(-5, 0, 10, 5, -10), 0.0001f);
    }

    @Test
    public void testMaxFloat_emptyArray_1_oe() throws Exception {
        try {
    NumberUtils.max();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testMaxFloat_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.max((float[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMaximumByte_1_oe() {
        final byte low = 123;
        final byte mid = 123 + 1;
        final byte high = 123 + 2;
        assertEquals(high, NumberUtils.max(low, mid, high), "maximum(byte, byte, byte) 1 failed");
    }

    @Test
    public void testMaximumByte_2_oe() {
        final byte low = 123;
        final byte mid = 123 + 1;
        final byte high = 123 + 2;
        // removed other assertion
        assertEquals(high, NumberUtils.max(mid, low, high), "maximum(byte, byte, byte) 2 failed");
    }

    @Test
    public void testMaximumByte_3_oe() {
        final byte low = 123;
        final byte mid = 123 + 1;
        final byte high = 123 + 2;
        // removed other assertion
        // removed other assertion
        assertEquals(high, NumberUtils.max(mid, high, low), "maximum(byte, byte, byte) 3 failed");
    }

    @Test
    public void testMaximumByte_4_oe() {
        final byte low = 123;
        final byte mid = 123 + 1;
        final byte high = 123 + 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(high, NumberUtils.max(high, mid, high), "maximum(byte, byte, byte) 4 failed");
    }

    @Test
    public void testMaximumDouble_1_oe() {
        final double low = 12.3;
        final double mid = 12.3 + 1;
        final double high = 12.3 + 2;
        assertEquals(high, NumberUtils.max(low, mid, high), 0.0001);
    }

    @Test
    public void testMaximumDouble_2_oe() {
        final double low = 12.3;
        final double mid = 12.3 + 1;
        final double high = 12.3 + 2;
        // removed other assertion
        assertEquals(high, NumberUtils.max(mid, low, high), 0.0001);
    }

    @Test
    public void testMaximumDouble_3_oe() {
        final double low = 12.3;
        final double mid = 12.3 + 1;
        final double high = 12.3 + 2;
        // removed other assertion
        // removed other assertion
        assertEquals(high, NumberUtils.max(mid, high, low), 0.0001);
    }

    @Test
    public void testMaximumDouble_4_oe() {
        final double low = 12.3;
        final double mid = 12.3 + 1;
        final double high = 12.3 + 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(mid, NumberUtils.max(low, mid, low), 0.0001);
    }

    @Test
    public void testMaximumDouble_5_oe() {
        final double low = 12.3;
        final double mid = 12.3 + 1;
        final double high = 12.3 + 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(high, NumberUtils.max(high, mid, high), 0.0001);
    }

    @Test
    public void testMaximumFloat_1_oe() {
        final float low = 12.3f;
        final float mid = 12.3f + 1;
        final float high = 12.3f + 2;
        assertEquals(high, NumberUtils.max(low, mid, high), 0.0001f);
    }

    @Test
    public void testMaximumFloat_2_oe() {
        final float low = 12.3f;
        final float mid = 12.3f + 1;
        final float high = 12.3f + 2;
        // removed other assertion
        assertEquals(high, NumberUtils.max(mid, low, high), 0.0001f);
    }

    @Test
    public void testMaximumFloat_3_oe() {
        final float low = 12.3f;
        final float mid = 12.3f + 1;
        final float high = 12.3f + 2;
        // removed other assertion
        // removed other assertion
        assertEquals(high, NumberUtils.max(mid, high, low), 0.0001f);
    }

    @Test
    public void testMaximumFloat_4_oe() {
        final float low = 12.3f;
        final float mid = 12.3f + 1;
        final float high = 12.3f + 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(mid, NumberUtils.max(low, mid, low), 0.0001f);
    }

    @Test
    public void testMaximumFloat_5_oe() {
        final float low = 12.3f;
        final float mid = 12.3f + 1;
        final float high = 12.3f + 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(high, NumberUtils.max(high, mid, high), 0.0001f);
    }

    @Test
    public void testMaximumInt_1_oe() {
        assertEquals(12345, NumberUtils.max(12345, 12345 - 1, 12345 - 2), "maximum(int, int, int) 1 failed");
    }

    @Test
    public void testMaximumInt_2_oe() {
        // removed other assertion
        assertEquals(12345, NumberUtils.max(12345 - 1, 12345, 12345 - 2), "maximum(int, int, int) 2 failed");
    }

    @Test
    public void testMaximumInt_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(12345, NumberUtils.max(12345 - 1, 12345 - 2, 12345), "maximum(int, int, int) 3 failed");
    }

    @Test
    public void testMaximumInt_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(12345, NumberUtils.max(12345 - 1, 12345, 12345), "maximum(int, int, int) 4 failed");
    }

    @Test
    public void testMaximumInt_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(12345, NumberUtils.max(12345, 12345, 12345), "maximum(int, int, int) 5 failed");
    }

    @Test
    public void testMaximumLong_1_oe() {
        assertEquals(12345L, NumberUtils.max(12345L, 12345L - 1L, 12345L - 2L), "maximum(long, long, long) 1 failed");
    }

    @Test
    public void testMaximumLong_2_oe() {
        // removed other assertion
        assertEquals(12345L, NumberUtils.max(12345L - 1L, 12345L, 12345L - 2L), "maximum(long, long, long) 2 failed");
    }

    @Test
    public void testMaximumLong_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(12345L, NumberUtils.max(12345L - 1L, 12345L - 2L, 12345L), "maximum(long, long, long) 3 failed");
    }

    @Test
    public void testMaximumLong_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(12345L, NumberUtils.max(12345L - 1L, 12345L, 12345L), "maximum(long, long, long) 4 failed");
    }

    @Test
    public void testMaximumLong_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(12345L, NumberUtils.max(12345L, 12345L, 12345L), "maximum(long, long, long) 5 failed");
    }

    @Test
    public void testMaximumShort_1_oe() {
        final short low = 1234;
        final short mid = 1234 + 1;
        final short high = 1234 + 2;
        assertEquals(high, NumberUtils.max(low, mid, high), "maximum(short, short, short) 1 failed");
    }

    @Test
    public void testMaximumShort_2_oe() {
        final short low = 1234;
        final short mid = 1234 + 1;
        final short high = 1234 + 2;
        // removed other assertion
        assertEquals(high, NumberUtils.max(mid, low, high), "maximum(short, short, short) 2 failed");
    }

    @Test
    public void testMaximumShort_3_oe() {
        final short low = 1234;
        final short mid = 1234 + 1;
        final short high = 1234 + 2;
        // removed other assertion
        // removed other assertion
        assertEquals(high, NumberUtils.max(mid, high, low), "maximum(short, short, short) 3 failed");
    }

    @Test
    public void testMaximumShort_4_oe() {
        final short low = 1234;
        final short mid = 1234 + 1;
        final short high = 1234 + 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(high, NumberUtils.max(high, mid, high), "maximum(short, short, short) 4 failed");
    }

    @Test
    public void testMaxInt_1_oe() {
        assertEquals(5, NumberUtils.max(5), "max(int[]) failed for array length 1");
    }

    @Test
    public void testMaxInt_2_oe() {
        // removed other assertion
        assertEquals(9, NumberUtils.max(6, 9), "max(int[]) failed for array length 2");
    }

    @Test
    public void testMaxInt_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(10, NumberUtils.max(-10, -5, 0, 5, 10), "max(int[]) failed for array length 5");
    }

    @Test
    public void testMaxInt_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10, NumberUtils.max(-10, -5, 0, 5, 10));
    }

    @Test
    public void testMaxInt_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10, NumberUtils.max(-5, 0, 10, 5, -10));
    }

    @Test
    public void testMaxInt_emptyArray_1_oe() throws Exception {
        try {
    NumberUtils.max();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testMaxInt_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.max((int[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMaxLong_1_oe() {
        assertEquals(5L, NumberUtils.max(5L), "max(long[]) failed for array length 1");
    }

    @Test
    public void testMaxLong_2_oe() {
        // removed other assertion
        assertEquals(9L, NumberUtils.max(6L, 9L), "max(long[]) failed for array length 2");
    }

    @Test
    public void testMaxLong_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(10L, NumberUtils.max(-10L, -5L, 0L, 5L, 10L), "max(long[]) failed for array length 5");
    }

    @Test
    public void testMaxLong_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10L, NumberUtils.max(-10L, -5L, 0L, 5L, 10L));
    }

    @Test
    public void testMaxLong_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10L, NumberUtils.max(-5L, 0L, 10L, 5L, -10L));
    }

    @Test
    public void testMaxLong_emptyArray_1_oe() throws Exception {
        try {
    NumberUtils.max();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testMaxLong_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.max((long[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMaxShort_1_oe() {
        assertEquals((short) 5, NumberUtils.max((short) 5), "max(short[]) failed for array length 1");
    }

    @Test
    public void testMaxShort_2_oe() {
        // removed other assertion
        assertEquals((short) 9, NumberUtils.max((short) 6, (short) 9), "max(short[]) failed for array length 2");
    }

    @Test
    public void testMaxShort_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals((short)10,NumberUtils.max((short)-10,(short)-5,(short)0,(short)5,(short)10),"max(short[])failed for array length 5");
    }

    @Test
    public void testMaxShort_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((short) 10, NumberUtils.max((short) -10, (short) -5, (short) 0, (short) 5, (short) 10));
    }

    @Test
    public void testMaxShort_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((short) 10, NumberUtils.max((short) -5, (short) 0, (short) 10, (short) 5, (short) -10));
    }

    @Test
    public void testMaxShort_emptyArray_1_oe() throws Exception {
        try {
    NumberUtils.max();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testMaxShort_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.max((short[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMinByte_1_oe() {
        assertEquals((byte) 5, NumberUtils.min((byte) 5), "min(byte[]) failed for array length 1");
    }

    @Test
    public void testMinByte_2_oe() {
        // removed other assertion
        assertEquals((byte) 6, NumberUtils.min((byte) 6, (byte) 9), "min(byte[]) failed for array length 2");
    }

    @Test
    public void testMinByte_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals((byte) -10, NumberUtils.min((byte) -10, (byte) -5, (byte) 0, (byte) 5, (byte) 10));
    }

    @Test
    public void testMinByte_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals((byte) -10, NumberUtils.min((byte) -5, (byte) 0, (byte) -10, (byte) 5, (byte) 10));
    }

    @Test
    public void testMinByte_emptyArray_1_oe() throws Exception {
        try {
    NumberUtils.min();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testMinByte_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.min((byte[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMinDouble_1_oe() {
        assertEquals(5.12, NumberUtils.min(5.12), "min(double[]) failed for array length 1");
    }

    @Test
    public void testMinDouble_2_oe() {
        // removed other assertion
        assertEquals(6.23, NumberUtils.min(6.23, 9.34), "min(double[]) failed for array length 2");
    }

    @Test
    public void testMinDouble_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-10.45, NumberUtils.min(-10.45, -5.56, 0, 5.67, 10.78), "min(double[]) failed for array length 5");
    }

    @Test
    public void testMinDouble_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-10, NumberUtils.min(-10, -5, 0, 5, 10), 0.0001);
    }

    @Test
    public void testMinDouble_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-10, NumberUtils.min(-5, 0, -10, 5, 10), 0.0001);
    }

    @Test
    public void testMinDouble_emptyArray_1_oe() throws Exception {
        try {
    NumberUtils.min();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testMinDouble_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.min((double[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMinFloat_1_oe() {
        assertEquals(5.9f, NumberUtils.min(5.9f), "min(float[]) failed for array length 1");
    }

    @Test
    public void testMinFloat_2_oe() {
        // removed other assertion
        assertEquals(6.8f, NumberUtils.min(6.8f, 9.7f), "min(float[]) failed for array length 2");
    }

    @Test
    public void testMinFloat_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(-10.6f, NumberUtils.min(-10.6f, -5.5f, 0, 5.4f, 10.3f), "min(float[]) failed for array length 5");
    }

    @Test
    public void testMinFloat_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-10, NumberUtils.min(-10, -5, 0, 5, 10), 0.0001f);
    }

    @Test
    public void testMinFloat_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-10, NumberUtils.min(-5, 0, -10, 5, 10), 0.0001f);
    }

    @Test
    public void testMinFloat_emptyArray_1_oe() throws Exception {
        try {
    NumberUtils.min();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testMinFloat_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.min((float[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMinimumByte_1_oe() {
        final byte low = 123;
        final byte mid = 123 + 1;
        final byte high = 123 + 2;
        assertEquals(low, NumberUtils.min(low, mid, high), "minimum(byte, byte, byte) 1 failed");
    }

    @Test
    public void testMinimumByte_2_oe() {
        final byte low = 123;
        final byte mid = 123 + 1;
        final byte high = 123 + 2;
        // removed other assertion
        assertEquals(low, NumberUtils.min(mid, low, high), "minimum(byte, byte, byte) 2 failed");
    }

    @Test
    public void testMinimumByte_3_oe() {
        final byte low = 123;
        final byte mid = 123 + 1;
        final byte high = 123 + 2;
        // removed other assertion
        // removed other assertion
        assertEquals(low, NumberUtils.min(mid, high, low), "minimum(byte, byte, byte) 3 failed");
    }

    @Test
    public void testMinimumByte_4_oe() {
        final byte low = 123;
        final byte mid = 123 + 1;
        final byte high = 123 + 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(low, NumberUtils.min(low, mid, low), "minimum(byte, byte, byte) 4 failed");
    }

    @Test
    public void testMinimumDouble_1_oe() {
        final double low = 12.3;
        final double mid = 12.3 + 1;
        final double high = 12.3 + 2;
        assertEquals(low, NumberUtils.min(low, mid, high), 0.0001);
    }

    @Test
    public void testMinimumDouble_2_oe() {
        final double low = 12.3;
        final double mid = 12.3 + 1;
        final double high = 12.3 + 2;
        // removed other assertion
        assertEquals(low, NumberUtils.min(mid, low, high), 0.0001);
    }

    @Test
    public void testMinimumDouble_3_oe() {
        final double low = 12.3;
        final double mid = 12.3 + 1;
        final double high = 12.3 + 2;
        // removed other assertion
        // removed other assertion
        assertEquals(low, NumberUtils.min(mid, high, low), 0.0001);
    }

    @Test
    public void testMinimumDouble_4_oe() {
        final double low = 12.3;
        final double mid = 12.3 + 1;
        final double high = 12.3 + 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(low, NumberUtils.min(low, mid, low), 0.0001);
    }

    @Test
    public void testMinimumDouble_5_oe() {
        final double low = 12.3;
        final double mid = 12.3 + 1;
        final double high = 12.3 + 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(mid, NumberUtils.min(high, mid, high), 0.0001);
    }

    @Test
    public void testMinimumFloat_1_oe() {
        final float low = 12.3f;
        final float mid = 12.3f + 1;
        final float high = 12.3f + 2;
        assertEquals(low, NumberUtils.min(low, mid, high), 0.0001f);
    }

    @Test
    public void testMinimumFloat_2_oe() {
        final float low = 12.3f;
        final float mid = 12.3f + 1;
        final float high = 12.3f + 2;
        // removed other assertion
        assertEquals(low, NumberUtils.min(mid, low, high), 0.0001f);
    }

    @Test
    public void testMinimumFloat_3_oe() {
        final float low = 12.3f;
        final float mid = 12.3f + 1;
        final float high = 12.3f + 2;
        // removed other assertion
        // removed other assertion
        assertEquals(low, NumberUtils.min(mid, high, low), 0.0001f);
    }

    @Test
    public void testMinimumFloat_4_oe() {
        final float low = 12.3f;
        final float mid = 12.3f + 1;
        final float high = 12.3f + 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(low, NumberUtils.min(low, mid, low), 0.0001f);
    }

    @Test
    public void testMinimumFloat_5_oe() {
        final float low = 12.3f;
        final float mid = 12.3f + 1;
        final float high = 12.3f + 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(mid, NumberUtils.min(high, mid, high), 0.0001f);
    }

    @Test
    public void testMinimumInt_1_oe() {
        assertEquals(12345, NumberUtils.min(12345, 12345 + 1, 12345 + 2), "minimum(int, int, int) 1 failed");
    }

    @Test
    public void testMinimumInt_2_oe() {
        // removed other assertion
        assertEquals(12345, NumberUtils.min(12345 + 1, 12345, 12345 + 2), "minimum(int, int, int) 2 failed");
    }

    @Test
    public void testMinimumInt_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(12345, NumberUtils.min(12345 + 1, 12345 + 2, 12345), "minimum(int, int, int) 3 failed");
    }

    @Test
    public void testMinimumInt_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(12345, NumberUtils.min(12345 + 1, 12345, 12345), "minimum(int, int, int) 4 failed");
    }

    @Test
    public void testMinimumInt_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(12345, NumberUtils.min(12345, 12345, 12345), "minimum(int, int, int) 5 failed");
    }

    @Test
    public void testMinimumLong_1_oe() {
        assertEquals(12345L, NumberUtils.min(12345L, 12345L + 1L, 12345L + 2L), "minimum(long, long, long) 1 failed");
    }

    @Test
    public void testMinimumLong_2_oe() {
        // removed other assertion
        assertEquals(12345L, NumberUtils.min(12345L + 1L, 12345L, 12345 + 2L), "minimum(long, long, long) 2 failed");
    }

    @Test
    public void testMinimumLong_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(12345L, NumberUtils.min(12345L + 1L, 12345L + 2L, 12345L), "minimum(long, long, long) 3 failed");
    }

    @Test
    public void testMinimumLong_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(12345L, NumberUtils.min(12345L + 1L, 12345L, 12345L), "minimum(long, long, long) 4 failed");
    }

    @Test
    public void testMinimumLong_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(12345L, NumberUtils.min(12345L, 12345L, 12345L), "minimum(long, long, long) 5 failed");
    }

    @Test
    public void testMinimumShort_1_oe() {
        final short low = 1234;
        final short mid = 1234 + 1;
        final short high = 1234 + 2;
        assertEquals(low, NumberUtils.min(low, mid, high), "minimum(short, short, short) 1 failed");
    }

    @Test
    public void testMinimumShort_2_oe() {
        final short low = 1234;
        final short mid = 1234 + 1;
        final short high = 1234 + 2;
        // removed other assertion
        assertEquals(low, NumberUtils.min(mid, low, high), "minimum(short, short, short) 2 failed");
    }

    @Test
    public void testMinimumShort_3_oe() {
        final short low = 1234;
        final short mid = 1234 + 1;
        final short high = 1234 + 2;
        // removed other assertion
        // removed other assertion
        assertEquals(low, NumberUtils.min(mid, high, low), "minimum(short, short, short) 3 failed");
    }

    @Test
    public void testMinimumShort_4_oe() {
        final short low = 1234;
        final short mid = 1234 + 1;
        final short high = 1234 + 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(low, NumberUtils.min(low, mid, low), "minimum(short, short, short) 4 failed");
    }

    @Test
    public void testMinInt_1_oe() {
        assertEquals(5, NumberUtils.min(5), "min(int[]) failed for array length 1");
    }

    @Test
    public void testMinInt_2_oe() {
        // removed other assertion
        assertEquals(6, NumberUtils.min(6, 9), "min(int[]) failed for array length 2");
    }

    @Test
    public void testMinInt_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals(-10, NumberUtils.min(-10, -5, 0, 5, 10));
    }

    @Test
    public void testMinInt_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-10, NumberUtils.min(-5, 0, -10, 5, 10));
    }

    @Test
    public void testMinInt_emptyArray_1_oe() throws Exception {
        try {
    NumberUtils.min();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testMinInt_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.min((int[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMinLong_1_oe() {
        assertEquals(5L, NumberUtils.min(5L), "min(long[]) failed for array length 1");
    }

    @Test
    public void testMinLong_2_oe() {
        // removed other assertion
        assertEquals(6L, NumberUtils.min(6L, 9L), "min(long[]) failed for array length 2");
    }

    @Test
    public void testMinLong_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals(-10L, NumberUtils.min(-10L, -5L, 0L, 5L, 10L));
    }

    @Test
    public void testMinLong_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(-10L, NumberUtils.min(-5L, 0L, -10L, 5L, 10L));
    }

    @Test
    public void testMinLong_emptyArray_1_oe() throws Exception {
        try {
    NumberUtils.min();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testMinLong_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.min((long[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMinShort_1_oe() {
        assertEquals((short) 5, NumberUtils.min((short) 5), "min(short[]) failed for array length 1");
    }

    @Test
    public void testMinShort_2_oe() {
        // removed other assertion
        assertEquals((short) 6, NumberUtils.min((short) 6, (short) 9), "min(short[]) failed for array length 2");
    }

    @Test
    public void testMinShort_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals((short) -10, NumberUtils.min((short) -10, (short) -5, (short) 0, (short) 5, (short) 10));
    }

    @Test
    public void testMinShort_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals((short) -10, NumberUtils.min((short) -5, (short) 0, (short) -10, (short) 5, (short) 10));
    }

    @Test
    public void testMinShort_emptyArray_1_oe() throws Exception {
        try {
    NumberUtils.min();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testMinShort_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.min((short[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_1_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        assertTrue(NumberUtils.createNumber(shouldBeFloat) instanceof Float);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_2_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        assertTrue(NumberUtils.createNumber(shouldBeDouble) instanceof Double);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_3_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.createNumber(shouldBeBigDecimal) instanceof BigDecimal);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_4_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        assertTrue(NumberUtils.createNumber("001.12") instanceof Float);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_5_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        assertTrue(NumberUtils.createNumber("-001.12") instanceof Float);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_6_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.createNumber("+001.12") instanceof Float);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_7_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.createNumber("003.40282354e+38") instanceof Double);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_8_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.createNumber("-003.40282354e+38") instanceof Double);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_9_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.createNumber("+003.40282354e+38") instanceof Double);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_10_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.createNumber("0001.797693134862315759e+308") instanceof BigDecimal);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_11_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.createNumber("-001.797693134862315759e+308") instanceof BigDecimal);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_12_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.createNumber("+001.797693134862315759e+308") instanceof BigDecimal);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_13_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //LANG-1613
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MIN_NORMAL)) instanceof Double);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_14_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //LANG-1613
        // removed other assertion
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MIN_NORMAL) + "D") instanceof Double);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_15_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //LANG-1613
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MIN_NORMAL) + "F") instanceof Double);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_16_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //LANG-1613
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MIN_VALUE)) instanceof Double);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_17_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //LANG-1613
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MIN_VALUE) + "D") instanceof Double);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_18_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //LANG-1613
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MIN_VALUE) + "F") instanceof Double);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_19_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //LANG-1613
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MAX_VALUE)) instanceof Double);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_20_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //LANG-1613
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MAX_VALUE) + "D") instanceof Double);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_21_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //LANG-1613
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.createNumber(Double.toString(Double.MAX_VALUE) + "F") instanceof Double);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_22_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //LANG-1613
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(NumberUtils.createNumber("4.9e-324D") instanceof Double);
    }

    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss_23_oe() {
        final String shouldBeFloat = "1.23";
        final String shouldBeDouble = "3.40282354e+38";
        final String shouldBeBigDecimal = "1.797693134862315759e+308";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        //LANG-1613
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
        assertTrue(NumberUtils.createNumber("4.9e-324F") instanceof Double);
    }

    @Test
    public void testStringToDoubleString_1_oe() {
        assertEquals(NumberUtils.toDouble("-1.2345"), -1.2345d, "toDouble(String) 1 failed");
    }

    @Test
    public void testStringToDoubleString_2_oe() {
        // removed other assertion
        assertEquals(1.2345d, NumberUtils.toDouble("1.2345"), "toDouble(String) 2 failed");
    }

    @Test
    public void testStringToDoubleString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(0.0d, NumberUtils.toDouble("abc"), "toDouble(String) 3 failed");
    }

    @Test
    public void testStringToDoubleString_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        assertEquals(NumberUtils.toDouble("-001.2345"), -1.2345d, "toDouble(String) 4 failed");
    }

    @Test
    public void testStringToDoubleString_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        assertEquals(1.2345d, NumberUtils.toDouble("+001.2345"), "toDouble(String) 5 failed");
    }

    @Test
    public void testStringToDoubleString_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        assertEquals(1.2345d, NumberUtils.toDouble("001.2345"), "toDouble(String) 6 failed");
    }

    @Test
    public void testStringToDoubleString_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0d, NumberUtils.toDouble("000.00000"), "toDouble(String) 7 failed");
    }

    @Test
    public void testStringToDoubleString_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(NumberUtils.toDouble(Double.MAX_VALUE + ""),Double.MAX_VALUE,"toDouble(Double.MAX_VALUE)failed");
    }

    @Test
    public void testStringToDoubleString_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(NumberUtils.toDouble(Double.MIN_VALUE + ""),Double.MIN_VALUE,"toDouble(Double.MIN_VALUE)failed");
    }

    @Test
    public void testStringToDoubleString_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(0.0d, NumberUtils.toDouble(""), "toDouble(empty) failed");
    }

    @Test
    public void testStringToDoubleString_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0.0d, NumberUtils.toDouble((String) null), "toDouble(null) failed");
    }

    @Test
    public void testStringToDoubleStringD_1_oe() {
        assertEquals(1.2345d, NumberUtils.toDouble("1.2345", 5.1d), "toDouble(String, int) 1 failed");
    }

    @Test
    public void testStringToDoubleStringD_2_oe() {
        // removed other assertion
        assertEquals(5.0d, NumberUtils.toDouble("a", 5.0d), "toDouble(String, int) 2 failed");
    }

    @Test
    public void testStringToDoubleStringD_3_oe() {
        // removed other assertion
        // removed other assertion
        // LANG-1060
        assertEquals(1.2345d, NumberUtils.toDouble("001.2345", 5.1d), "toDouble(String, int) 3 failed");
    }

    @Test
    public void testStringToDoubleStringD_4_oe() {
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        assertEquals(NumberUtils.toDouble("-001.2345", 5.1d), -1.2345d, "toDouble(String, int) 4 failed");
    }

    @Test
    public void testStringToDoubleStringD_5_oe() {
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        assertEquals(1.2345d, NumberUtils.toDouble("+001.2345", 5.1d), "toDouble(String, int) 5 failed");
    }

    @Test
    public void testStringToDoubleStringD_6_oe() {
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0d, NumberUtils.toDouble("000.00", 5.1d), "toDouble(String, int) 7 failed");
    }

    @Test
    public void testToByteString_1_oe() {
        assertEquals(123, NumberUtils.toByte("123"), "toByte(String) 1 failed");
    }

    @Test
    public void testToByteString_2_oe() {
        // removed other assertion
        assertEquals(0, NumberUtils.toByte("abc"), "toByte(String) 2 failed");
    }

    @Test
    public void testToByteString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(0, NumberUtils.toByte(""), "toByte(empty) failed");
    }

    @Test
    public void testToByteString_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, NumberUtils.toByte(null), "toByte(null) failed");
    }

    @Test
    public void testToByteStringI_1_oe() {
        assertEquals(123, NumberUtils.toByte("123", (byte) 5), "toByte(String, byte) 1 failed");
    }

    @Test
    public void testToByteStringI_2_oe() {
        // removed other assertion
        assertEquals(5, NumberUtils.toByte("12.3", (byte) 5), "toByte(String, byte) 2 failed");
    }

    @Test
    public void testToFloatString_1_oe() {
        assertEquals(NumberUtils.toFloat("-1.2345"), -1.2345f, "toFloat(String) 1 failed");
    }

    @Test
    public void testToFloatString_2_oe() {
        // removed other assertion
        assertEquals(1.2345f, NumberUtils.toFloat("1.2345"), "toFloat(String) 2 failed");
    }

    @Test
    public void testToFloatString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(0.0f, NumberUtils.toFloat("abc"), "toFloat(String) 3 failed");
    }

    @Test
    public void testToFloatString_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        assertEquals(NumberUtils.toFloat("-001.2345"), -1.2345f, "toFloat(String) 4 failed");
    }

    @Test
    public void testToFloatString_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        assertEquals(1.2345f, NumberUtils.toFloat("+001.2345"), "toFloat(String) 5 failed");
    }

    @Test
    public void testToFloatString_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        assertEquals(1.2345f, NumberUtils.toFloat("001.2345"), "toFloat(String) 6 failed");
    }

    @Test
    public void testToFloatString_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0f, NumberUtils.toFloat("000.00"), "toFloat(String) 7 failed");
    }

    @Test
    public void testToFloatString_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(NumberUtils.toFloat(Float.MAX_VALUE + ""), Float.MAX_VALUE, "toFloat(Float.MAX_VALUE) failed");
    }

    @Test
    public void testToFloatString_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(NumberUtils.toFloat(Float.MIN_VALUE + ""), Float.MIN_VALUE, "toFloat(Float.MIN_VALUE) failed");
    }

    @Test
    public void testToFloatString_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(0.0f, NumberUtils.toFloat(""), "toFloat(empty) failed");
    }

    @Test
    public void testToFloatString_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0.0f, NumberUtils.toFloat(null), "toFloat(null) failed");
    }

    @Test
    public void testToFloatStringF_1_oe() {
        assertEquals(1.2345f, NumberUtils.toFloat("1.2345", 5.1f), "toFloat(String, int) 1 failed");
    }

    @Test
    public void testToFloatStringF_2_oe() {
        // removed other assertion
        assertEquals(5.0f, NumberUtils.toFloat("a", 5.0f), "toFloat(String, int) 2 failed");
    }

    @Test
    public void testToFloatStringF_3_oe() {
        // removed other assertion
        // removed other assertion
        // LANG-1060
        assertEquals(5.0f, NumberUtils.toFloat("-001Z.2345", 5.0f), "toFloat(String, int) 3 failed");
    }

    @Test
    public void testToFloatStringF_4_oe() {
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        assertEquals(5.0f, NumberUtils.toFloat("+001AB.2345", 5.0f), "toFloat(String, int) 4 failed");
    }

    @Test
    public void testToFloatStringF_5_oe() {
        // removed other assertion
        // removed other assertion
        // LANG-1060
        // removed other assertion
        // removed other assertion
        assertEquals(5.0f, NumberUtils.toFloat("001Z.2345", 5.0f), "toFloat(String, int) 5 failed");
    }

    @Test
    public void testToIntString_1_oe() {
        assertEquals(12345, NumberUtils.toInt("12345"), "toInt(String) 1 failed");
    }

    @Test
    public void testToIntString_2_oe() {
        // removed other assertion
        assertEquals(0, NumberUtils.toInt("abc"), "toInt(String) 2 failed");
    }

    @Test
    public void testToIntString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(0, NumberUtils.toInt(""), "toInt(empty) failed");
    }

    @Test
    public void testToIntString_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, NumberUtils.toInt(null), "toInt(null) failed");
    }

    @Test
    public void testToIntStringI_1_oe() {
        assertEquals(12345, NumberUtils.toInt("12345", 5), "toInt(String, int) 1 failed");
    }

    @Test
    public void testToIntStringI_2_oe() {
        // removed other assertion
        assertEquals(5, NumberUtils.toInt("1234.5", 5), "toInt(String, int) 2 failed");
    }

    @Test
    public void testToLongString_1_oe() {
        assertEquals(12345L, NumberUtils.toLong("12345"), "toLong(String) 1 failed");
    }

    @Test
    public void testToLongString_2_oe() {
        // removed other assertion
        assertEquals(0L, NumberUtils.toLong("abc"), "toLong(String) 2 failed");
    }

    @Test
    public void testToLongString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(0L, NumberUtils.toLong("1L"), "toLong(String) 3 failed");
    }

    @Test
    public void testToLongString_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, NumberUtils.toLong("1l"), "toLong(String) 4 failed");
    }

    @Test
    public void testToLongString_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(NumberUtils.toLong(Long.MAX_VALUE + ""), Long.MAX_VALUE, "toLong(Long.MAX_VALUE) failed");
    }

    @Test
    public void testToLongString_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(NumberUtils.toLong(Long.MIN_VALUE + ""), Long.MIN_VALUE, "toLong(Long.MIN_VALUE) failed");
    }

    @Test
    public void testToLongString_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, NumberUtils.toLong(""), "toLong(empty) failed");
    }

    @Test
    public void testToLongString_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, NumberUtils.toLong(null), "toLong(null) failed");
    }

    @Test
    public void testToLongStringL_1_oe() {
        assertEquals(12345L, NumberUtils.toLong("12345", 5L), "toLong(String, long) 1 failed");
    }

    @Test
    public void testToLongStringL_2_oe() {
        // removed other assertion
        assertEquals(5L, NumberUtils.toLong("1234.5", 5L), "toLong(String, long) 2 failed");
    }

    @Test
    public void testToScaledBigDecimalBigDecimal_1_oe() {
        assertEquals(NumberUtils.toScaledBigDecimal(BigDecimal.valueOf(123.456)),BigDecimal.valueOf(123.46),"toScaledBigDecimal(BigDecimal)1 failed");
    }

    @Test
    public void testToScaledBigDecimalBigDecimal_2_oe() {
        // removed other assertion
        // Test RoudingMode.HALF_EVEN default rounding.
        assertEquals(NumberUtils.toScaledBigDecimal(BigDecimal.valueOf(23.515)),BigDecimal.valueOf(23.52),"toScaledBigDecimal(BigDecimal)2 failed");
    }

    @Test
    public void testToScaledBigDecimalBigDecimal_3_oe() {
        // removed other assertion
        // Test RoudingMode.HALF_EVEN default rounding.
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal(BigDecimal.valueOf(23.525)),BigDecimal.valueOf(23.52),"toScaledBigDecimal(BigDecimal)3 failed");
    }

    @Test
    public void testToScaledBigDecimalBigDecimal_4_oe() {
        // removed other assertion
        // Test RoudingMode.HALF_EVEN default rounding.
        // removed other assertion
        // removed other assertion
        assertEquals("2352.00",NumberUtils.toScaledBigDecimal(BigDecimal.valueOf(23.525)).multiply(BigDecimal.valueOf(100)).toString(),"toScaledBigDecimal(BigDecimal)4 failed");
    }

    @Test
    public void testToScaledBigDecimalBigDecimal_5_oe() {
        // removed other assertion
        // Test RoudingMode.HALF_EVEN default rounding.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal((BigDecimal)null),BigDecimal.ZERO,"toScaledBigDecimal(BigDecimal)5 failed");
    }

    @Test
    public void testToScaledBigDecimalBigDecimalIRM_1_oe() {
        assertEquals(NumberUtils.toScaledBigDecimal(BigDecimal.valueOf(123.456),1,RoundingMode.CEILING),BigDecimal.valueOf(123.5),"toScaledBigDecimal(BigDecimal,int,RoudingMode)1 failed");
    }

    @Test
    public void testToScaledBigDecimalBigDecimalIRM_2_oe() {
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal(BigDecimal.valueOf(23.5159),3,RoundingMode.FLOOR),BigDecimal.valueOf(23.515),"toScaledBigDecimal(BigDecimal,int,RoudingMode)2 failed");
    }

    @Test
    public void testToScaledBigDecimalBigDecimalIRM_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal(BigDecimal.valueOf(23.525),2,RoundingMode.HALF_UP),BigDecimal.valueOf(23.53),"toScaledBigDecimal(BigDecimal,int,RoudingMode)3 failed");
    }

    @Test
    public void testToScaledBigDecimalBigDecimalIRM_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("23521.0000",NumberUtils.toScaledBigDecimal(BigDecimal.valueOf(23.521),4,RoundingMode.HALF_EVEN).multiply(BigDecimal.valueOf(1000)).toString(),"toScaledBigDecimal(BigDecimal,int,RoudingMode)4 failed");
    }

    @Test
    public void testToScaledBigDecimalBigDecimalIRM_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal((BigDecimal)null,2,RoundingMode.HALF_UP),BigDecimal.ZERO,"toScaledBigDecimal(BigDecimal,int,RoudingMode)5 failed");
    }

    @Test
    public void testToScaledBigDecimalDouble_1_oe() {
        assertEquals(NumberUtils.toScaledBigDecimal(Double.valueOf(123.456d)),BigDecimal.valueOf(123.46),"toScaledBigDecimal(Double)1 failed");
    }

    @Test
    public void testToScaledBigDecimalDouble_2_oe() {
        // removed other assertion
        // Test RoudingMode.HALF_EVEN default rounding.
        assertEquals(NumberUtils.toScaledBigDecimal(Double.valueOf(23.515d)),BigDecimal.valueOf(23.52),"toScaledBigDecimal(Double)2 failed");
    }

    @Test
    public void testToScaledBigDecimalDouble_3_oe() {
        // removed other assertion
        // Test RoudingMode.HALF_EVEN default rounding.
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal(Double.valueOf(23.525d)),BigDecimal.valueOf(23.52),"toScaledBigDecimal(Double)3 failed");
    }

    @Test
    public void testToScaledBigDecimalDouble_4_oe() {
        // removed other assertion
        // Test RoudingMode.HALF_EVEN default rounding.
        // removed other assertion
        // removed other assertion
        assertEquals("2352.00",NumberUtils.toScaledBigDecimal(Double.valueOf(23.525d)).multiply(BigDecimal.valueOf(100)).toString(),"toScaledBigDecimal(Double)4 failed");
    }

    @Test
    public void testToScaledBigDecimalDouble_5_oe() {
        // removed other assertion
        // Test RoudingMode.HALF_EVEN default rounding.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal((Double)null),BigDecimal.ZERO,"toScaledBigDecimal(Double)5 failed");
    }

    @Test
    public void testToScaledBigDecimalDoubleIRM_1_oe() {
        assertEquals(NumberUtils.toScaledBigDecimal(Double.valueOf(123.456d),1,RoundingMode.CEILING),BigDecimal.valueOf(123.5),"toScaledBigDecimal(Double,int,RoudingMode)1 failed");
    }

    @Test
    public void testToScaledBigDecimalDoubleIRM_2_oe() {
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal(Double.valueOf(23.5159d),3,RoundingMode.FLOOR),BigDecimal.valueOf(23.515),"toScaledBigDecimal(Double,int,RoudingMode)2 failed");
    }

    @Test
    public void testToScaledBigDecimalDoubleIRM_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal(Double.valueOf(23.525d),2,RoundingMode.HALF_UP),BigDecimal.valueOf(23.53),"toScaledBigDecimal(Double,int,RoudingMode)3 failed");
    }

    @Test
    public void testToScaledBigDecimalDoubleIRM_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("23521.0000",NumberUtils.toScaledBigDecimal(Double.valueOf(23.521d),4,RoundingMode.HALF_EVEN).multiply(BigDecimal.valueOf(1000)).toString(),"toScaledBigDecimal(Double,int,RoudingMode)4 failed");
    }

    @Test
    public void testToScaledBigDecimalDoubleIRM_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal((Double)null,2,RoundingMode.HALF_UP),BigDecimal.ZERO,"toScaledBigDecimal(Double,int,RoudingMode)5 failed");
    }

    @Test
    public void testToScaledBigDecimalFloat_1_oe() {
        assertEquals(NumberUtils.toScaledBigDecimal(Float.valueOf(123.456f)),BigDecimal.valueOf(123.46),"toScaledBigDecimal(Float)1 failed");
    }

    @Test
    public void testToScaledBigDecimalFloat_2_oe() {
        // removed other assertion
        // Test RoudingMode.HALF_EVEN default rounding.
        assertEquals(NumberUtils.toScaledBigDecimal(Float.valueOf(23.515f)),BigDecimal.valueOf(23.51),"toScaledBigDecimal(Float)2 failed");
    }

    @Test
    public void testToScaledBigDecimalFloat_3_oe() {
        // removed other assertion
        // Test RoudingMode.HALF_EVEN default rounding.
        // removed other assertion
        // Note. NumberUtils.toScaledBigDecimal(Float.valueOf(23.515f)).equals(BigDecimal.valueOf(23.51))
        // because of roundoff error. It is ok.
        assertEquals(NumberUtils.toScaledBigDecimal(Float.valueOf(23.525f)),BigDecimal.valueOf(23.52),"toScaledBigDecimal(Float)3 failed");
    }

    @Test
    public void testToScaledBigDecimalFloat_4_oe() {
        // removed other assertion
        // Test RoudingMode.HALF_EVEN default rounding.
        // removed other assertion
        // Note. NumberUtils.toScaledBigDecimal(Float.valueOf(23.515f)).equals(BigDecimal.valueOf(23.51))
        // because of roundoff error. It is ok.
        // removed other assertion
        assertEquals("2352.00",NumberUtils.toScaledBigDecimal(Float.valueOf(23.525f)).multiply(BigDecimal.valueOf(100)).toString(),"toScaledBigDecimal(Float)4 failed");
    }

    @Test
    public void testToScaledBigDecimalFloat_5_oe() {
        // removed other assertion
        // Test RoudingMode.HALF_EVEN default rounding.
        // removed other assertion
        // Note. NumberUtils.toScaledBigDecimal(Float.valueOf(23.515f)).equals(BigDecimal.valueOf(23.51))
        // because of roundoff error. It is ok.
        // removed other assertion
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal((Float)null),BigDecimal.ZERO,"toScaledBigDecimal(Float)5 failed");
    }

    @Test
    public void testToScaledBigDecimalFloatIRM_1_oe() {
        assertEquals(NumberUtils.toScaledBigDecimal(Float.valueOf(123.456f),1,RoundingMode.CEILING),BigDecimal.valueOf(123.5),"toScaledBigDecimal(Float,int,RoudingMode)1 failed");
    }

    @Test
    public void testToScaledBigDecimalFloatIRM_2_oe() {
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal(Float.valueOf(23.5159f),3,RoundingMode.FLOOR),BigDecimal.valueOf(23.515),"toScaledBigDecimal(Float,int,RoudingMode)2 failed");
    }

    @Test
    public void testToScaledBigDecimalFloatIRM_3_oe() {
        // removed other assertion
        // removed other assertion
        // The following happens due to roundoff error. We're ok with this.
        assertEquals(NumberUtils.toScaledBigDecimal(Float.valueOf(23.525f),2,RoundingMode.HALF_UP),BigDecimal.valueOf(23.52),"toScaledBigDecimal(Float,int,RoudingMode)3 failed");
    }

    @Test
    public void testToScaledBigDecimalFloatIRM_4_oe() {
        // removed other assertion
        // removed other assertion
        // The following happens due to roundoff error. We're ok with this.
        // removed other assertion
        assertEquals("23521.0000",NumberUtils.toScaledBigDecimal(Float.valueOf(23.521f),4,RoundingMode.HALF_EVEN).multiply(BigDecimal.valueOf(1000)).toString(),"toScaledBigDecimal(Float,int,RoudingMode)4 failed");
    }

    @Test
    public void testToScaledBigDecimalFloatIRM_5_oe() {
        // removed other assertion
        // removed other assertion
        // The following happens due to roundoff error. We're ok with this.
        // removed other assertion
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal((Float)null,2,RoundingMode.HALF_UP),BigDecimal.ZERO,"toScaledBigDecimal(Float,int,RoudingMode)5 failed");
    }

    @Test
    public void testToScaledBigDecimalString_1_oe() {
        assertEquals(NumberUtils.toScaledBigDecimal("123.456"),BigDecimal.valueOf(123.46),"toScaledBigDecimal(String)1 failed");
    }

    @Test
    public void testToScaledBigDecimalString_2_oe() {
        // removed other assertion
        // Test RoudingMode.HALF_EVEN default rounding.
        assertEquals(NumberUtils.toScaledBigDecimal("23.515"),BigDecimal.valueOf(23.52),"toScaledBigDecimal(String)2 failed");
    }

    @Test
    public void testToScaledBigDecimalString_3_oe() {
        // removed other assertion
        // Test RoudingMode.HALF_EVEN default rounding.
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal("23.525"),BigDecimal.valueOf(23.52),"toScaledBigDecimal(String)3 failed");
    }

    @Test
    public void testToScaledBigDecimalString_4_oe() {
        // removed other assertion
        // Test RoudingMode.HALF_EVEN default rounding.
        // removed other assertion
        // removed other assertion
        assertEquals("2352.00",NumberUtils.toScaledBigDecimal("23.525").multiply(BigDecimal.valueOf(100)).toString(),"toScaledBigDecimal(String)4 failed");
    }

    @Test
    public void testToScaledBigDecimalString_5_oe() {
        // removed other assertion
        // Test RoudingMode.HALF_EVEN default rounding.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal((String)null),BigDecimal.ZERO,"toScaledBigDecimal(String)5 failed");
    }

    @Test
    public void testToScaledBigDecimalStringIRM_1_oe() {
        assertEquals(NumberUtils.toScaledBigDecimal("123.456",1,RoundingMode.CEILING),BigDecimal.valueOf(123.5),"toScaledBigDecimal(String,int,RoudingMode)1 failed");
    }

    @Test
    public void testToScaledBigDecimalStringIRM_2_oe() {
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal("23.5159",3,RoundingMode.FLOOR),BigDecimal.valueOf(23.515),"toScaledBigDecimal(String,int,RoudingMode)2 failed");
    }

    @Test
    public void testToScaledBigDecimalStringIRM_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal("23.525",2,RoundingMode.HALF_UP),BigDecimal.valueOf(23.53),"toScaledBigDecimal(String,int,RoudingMode)3 failed");
    }

    @Test
    public void testToScaledBigDecimalStringIRM_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("23521.0000",NumberUtils.toScaledBigDecimal("23.521",4,RoundingMode.HALF_EVEN).multiply(BigDecimal.valueOf(1000)).toString(),"toScaledBigDecimal(String,int,RoudingMode)4 failed");
    }

    @Test
    public void testToScaledBigDecimalStringIRM_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(NumberUtils.toScaledBigDecimal((String)null,2,RoundingMode.HALF_UP),BigDecimal.ZERO,"toScaledBigDecimal(String,int,RoudingMode)5 failed");
    }

    @Test
    public void testToShortString_1_oe() {
        assertEquals(12345, NumberUtils.toShort("12345"), "toShort(String) 1 failed");
    }

    @Test
    public void testToShortString_2_oe() {
        // removed other assertion
        assertEquals(0, NumberUtils.toShort("abc"), "toShort(String) 2 failed");
    }

    @Test
    public void testToShortString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(0, NumberUtils.toShort(""), "toShort(empty) failed");
    }

    @Test
    public void testToShortString_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, NumberUtils.toShort(null), "toShort(null) failed");
    }

    @Test
    public void testToShortStringI_1_oe() {
        assertEquals(12345, NumberUtils.toShort("12345", (short) 5), "toShort(String, short) 1 failed");
    }

    @Test
    public void testToShortStringI_2_oe() {
        // removed other assertion
        assertEquals(5, NumberUtils.toShort("1234.5", (short) 5), "toShort(String, short) 2 failed");
    }

}
