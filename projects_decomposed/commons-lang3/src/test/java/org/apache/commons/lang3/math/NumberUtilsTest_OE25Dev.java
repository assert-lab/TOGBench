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
    // Check that the code fails to create a valid number when preceded by -- rather than -
    public void testCreateNumberFailure_1_1_oe() throws Exception {
        try {
    NumberUtils.createNumber("--1.1E-700F");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    // Check that the code fails to create a valid number when both e and E are present (with decimal)
    public void testCreateNumberFailure_2_1_oe() throws Exception {
        try {
    NumberUtils.createNumber("-1.1E+0-7e00");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    // Check that the code fails to create a valid number when both e and E are present (no decimal)
    public void testCreateNumberFailure_3_1_oe() throws Exception {
        try {
    NumberUtils.createNumber("-11E+0-7e00");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    // Check that the code fails to create a valid number when both e and E are present (no decimal)
    public void testCreateNumberFailure_4_1_oe() throws Exception {
        try {
    NumberUtils.createNumber("1eE+00001");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    // Check that the code fails to create a valid number when there are multiple trailing 'f' characters (LANG-1205)
    public void testCreateNumberFailure_5_1_oe() throws Exception {
        try {
    NumberUtils.createNumber("1234.5ff");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    // Check that the code fails to create a valid number when there are multiple trailing 'F' characters (LANG-1205)
    public void testCreateNumberFailure_6_1_oe() throws Exception {
        try {
    NumberUtils.createNumber("1234.5FF");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    // Check that the code fails to create a valid number when there are multiple trailing 'd' characters (LANG-1205)
    public void testCreateNumberFailure_7_1_oe() throws Exception {
        try {
    NumberUtils.createNumber("1234.5dd");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    // Check that the code fails to create a valid number when there are multiple trailing 'D' characters (LANG-1205)
    public void testCreateNumberFailure_8_1_oe() throws Exception {
        try {
    NumberUtils.createNumber("1234.5DD");
    org.junit.jupiter.api.Assertions.fail("NumberFormatException");
} catch (NumberFormatException e) {
}
    }

    @Test
    public void testMaxByte_emptyArray_1_oe() {
        assertThrows(IllegalArgumentException.class, NumberUtils::max);
    }

    @Test
    public void testMaxByte_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.max((byte[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMaxDouble_1_oe() throws Exception {
        final double[] d = null;
        try {
    NumberUtils.max(d);
    org.junit.jupiter.api.Assertions.fail("NullPointerException: No exception was thrown for null input.");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMaxDouble_2_oe() {
        final double[] d = null;
        // removed other assertion

        assertThrows(IllegalArgumentException.class, NumberUtils::max, "No exception was thrown for empty input.");
    }

    @Test
    public void testMaxDouble_emptyArray_1_oe() {
        assertThrows(IllegalArgumentException.class, NumberUtils::max);
    }

    @Test
    public void testMaxDouble_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.max((double[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMaxFloat_emptyArray_1_oe() {
        assertThrows(IllegalArgumentException.class, NumberUtils::max);
    }

    @Test
    public void testMaxFloat_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.max((float[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMaxInt_emptyArray_1_oe() {
        assertThrows(IllegalArgumentException.class, NumberUtils::max);
    }

    @Test
    public void testMaxInt_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.max((int[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMaxLong_emptyArray_1_oe() {
        assertThrows(IllegalArgumentException.class, NumberUtils::max);
    }

    @Test
    public void testMaxLong_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.max((long[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMaxShort_emptyArray_1_oe() {
        assertThrows(IllegalArgumentException.class, NumberUtils::max);
    }

    @Test
    public void testMaxShort_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.max((short[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMinByte_emptyArray_1_oe() {
        assertThrows(IllegalArgumentException.class, NumberUtils::min);
    }

    @Test
    public void testMinByte_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.min((byte[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMinDouble_emptyArray_1_oe() {
        assertThrows(IllegalArgumentException.class, NumberUtils::min);
    }

    @Test
    public void testMinDouble_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.min((double[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMinFloat_emptyArray_1_oe() {
        assertThrows(IllegalArgumentException.class, NumberUtils::min);
    }

    @Test
    public void testMinFloat_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.min((float[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMinInt_emptyArray_1_oe() {
        assertThrows(IllegalArgumentException.class, NumberUtils::min);
    }

    @Test
    public void testMinInt_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.min((int[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMinLong_emptyArray_1_oe() {
        assertThrows(IllegalArgumentException.class, NumberUtils::min);
    }

    @Test
    public void testMinLong_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.min((long[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMinShort_emptyArray_1_oe() {
        assertThrows(IllegalArgumentException.class, NumberUtils::min);
    }

    @Test
    public void testMinShort_nullArray_1_oe() throws Exception {
        try {
    NumberUtils.min((short[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

}
