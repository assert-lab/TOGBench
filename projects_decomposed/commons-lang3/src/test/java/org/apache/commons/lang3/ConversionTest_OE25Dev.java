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
package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;


/**
 * Unit tests {@link Conversion}.
 */
public class ConversionTest_OE25Dev {

    /**
     * Tests {@link Conversion#hexDigitToInt(char)}.
     */

    /**
     * Tests {@link Conversion#hexDigitMsb0ToInt(char)}.
     */

    /**
     * Tests {@link Conversion#hexDigitToBinary(char)}.
     */

    /**
     * Tests {@link Conversion#hexDigitMsb0ToBinary(char)}.
     */

    /**
     * Tests {@link Conversion#binaryToHexDigit(boolean[])}.
     */

    /**
     * Tests {@link Conversion#binaryBeMsb0ToHexDigit(boolean[], int)}.
     */

    /**
     * Tests {@link Conversion#binaryToHexDigitMsb0_4bits(boolean[])}.
     */

    /**
     * Tests {@link Conversion#binaryToHexDigitMsb0_4bits(boolean[], int)}.
     */

    /**
     * Tests {@link Conversion#binaryToHexDigit(boolean[])}.
     */

    /**
     * Tests {@link Conversion#binaryToHexDigit(boolean[], int)}.
     */

    /**
     * Tests {@link Conversion#intToHexDigit(int)}.
     */

    /**
     * Tests {@link Conversion#intToHexDigitMsb0(int)}.
     */

    static String dbgPrint(final boolean[] src) {
        final StringBuilder sb = new StringBuilder();
        for (final boolean e : src) {
            if (e) {
                sb.append("1, ");
            } else {
                sb.append("0, ");
            }
        }
        final String out = sb.toString();
        return out.substring(0, out.length() - 1);
    }

    /**
     * Tests {@link Conversion#intArrayToLong(int[], int, long, int, int)}.
     */

    /**
     * Tests {@link Conversion#shortArrayToLong(short[], int, long, int, int)}.
     */

    /**
     * Tests {@link Conversion#byteArrayToLong(byte[], int, long, int, int)}.
     */

    /**
     * Tests {@link Conversion#shortArrayToInt(short[], int, int, int, int)}.
     */

    /**
     * Tests {@link Conversion#byteArrayToInt(byte[], int, int, int, int)}.
     */

    /**
     * Tests {@link Conversion#byteArrayToShort(byte[], int, short, int, int)}.
     */

    /**
     * Tests {@link Conversion#hexToLong(String, int, long, int, int)}.
     */

    /**
     * Tests {@link Conversion#hexToInt(String, int, int, int, int)}.
     */

    /**
     * Tests {@link Conversion#hexToShort(String, int, short, int, int)}.
     */

    /**
     * Tests {@link Conversion#hexToByte(String, int, byte, int, int)}.
     */

    /**
     * Tests {@link Conversion#binaryToLong(boolean[], int, long, int, int)}.
     */

    /**
     * Tests {@link Conversion#binaryToInt(boolean[], int, int, int, int)}.
     */

    /**
     * Tests {@link Conversion#binaryToShort(boolean[], int, short, int, int)}.
     */

    /**
     * Tests {@link Conversion#binaryToByte(boolean[], int, byte, int, int)}.
     */

    /**
     * Tests {@link Conversion#longToIntArray(long, int, int[], int, int)}.
     */

    /**
     * Tests {@link Conversion#longToShortArray(long, int, short[], int, int)}.
     */

    /**
     * Tests {@link Conversion#intToShortArray(int, int, short[], int, int)}.
     */

    /**
     * Tests {@link Conversion#longToByteArray(long, int, byte[], int, int)}.
     */

    /**
     * Tests {@link Conversion#intToByteArray(int, int, byte[], int, int)}.
     */

    /**
     * Tests {@link Conversion#shortToByteArray(short, int, byte[], int, int)}.
     */

    /**
     * Tests {@link Conversion#longToHex(long, int, String, int, int)}.
     */

    /**
     * Tests {@link Conversion#intToHex(int, int, String, int, int)}.
     */

    /**
     * Tests {@link Conversion#shortToHex(short, int, String, int, int)}.
     */

    /**
     * Tests {@link Conversion#byteToHex(byte, int, String, int, int)}.
     */

    /**
     * Tests {@link Conversion#longToBinary(long, int, boolean[], int, int)}.
     */

    /**
     * Tests {@link Conversion#intToBinary(int, int, boolean[], int, int)}.
     */

    /**
     * Tests {@link Conversion#shortToBinary(short, int, boolean[], int, int)}.
     */

    /**
     * Tests {@link Conversion#byteToBinary(byte, int, boolean[], int, int)}.
     */

    /**
     * Tests {@link Conversion#uuidToByteArray(UUID, byte[], int, int)}.
     */

    /**
     * Tests {@link Conversion#byteArrayToUuid(byte[], int)}.
     */

    @Test
    public void testHexDigitToInt_1_oe() {
        assertEquals(0, Conversion.hexDigitToInt('0'));
    }

    @Test
    public void testHexDigitToInt_2_oe() {
        // removed other assertion
        assertEquals(1, Conversion.hexDigitToInt('1'));
    }

    @Test
    public void testHexDigitToInt_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(2, Conversion.hexDigitToInt('2'));
    }

    @Test
    public void testHexDigitToInt_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, Conversion.hexDigitToInt('3'));
    }

    @Test
    public void testHexDigitToInt_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, Conversion.hexDigitToInt('4'));
    }

    @Test
    public void testHexDigitToInt_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, Conversion.hexDigitToInt('5'));
    }

    @Test
    public void testHexDigitToInt_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, Conversion.hexDigitToInt('6'));
    }

    @Test
    public void testHexDigitToInt_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, Conversion.hexDigitToInt('7'));
    }

    @Test
    public void testHexDigitToInt_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, Conversion.hexDigitToInt('8'));
    }

    @Test
    public void testHexDigitToInt_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, Conversion.hexDigitToInt('9'));
    }

    @Test
    public void testHexDigitToInt_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10, Conversion.hexDigitToInt('A'));
    }

    @Test
    public void testHexDigitToInt_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(10, Conversion.hexDigitToInt('a'));
    }

    @Test
    public void testHexDigitToInt_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(11, Conversion.hexDigitToInt('B'));
    }

    @Test
    public void testHexDigitToInt_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(11, Conversion.hexDigitToInt('b'));
    }

    @Test
    public void testHexDigitToInt_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(12, Conversion.hexDigitToInt('C'));
    }

    @Test
    public void testHexDigitToInt_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(12, Conversion.hexDigitToInt('c'));
    }

    @Test
    public void testHexDigitToInt_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(13, Conversion.hexDigitToInt('D'));
    }

    @Test
    public void testHexDigitToInt_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(13, Conversion.hexDigitToInt('d'));
    }

    @Test
    public void testHexDigitToInt_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(14, Conversion.hexDigitToInt('E'));
    }

    @Test
    public void testHexDigitToInt_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(14, Conversion.hexDigitToInt('e'));
    }

    @Test
    public void testHexDigitToInt_21_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(15, Conversion.hexDigitToInt('F'));
    }

    @Test
    public void testHexDigitToInt_22_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(15, Conversion.hexDigitToInt('f'));
    }

    @Test
    public void testHexDigitToInt_23_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Conversion.hexDigitToInt('G');
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testHexDigitMsb0ToInt_1_oe() {
        assertEquals(0x0, Conversion.hexDigitMsb0ToInt('0'));
    }

    @Test
    public void testHexDigitMsb0ToInt_2_oe() {
        // removed other assertion
        assertEquals(0x8, Conversion.hexDigitMsb0ToInt('1'));
    }

    @Test
    public void testHexDigitMsb0ToInt_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(0x4, Conversion.hexDigitMsb0ToInt('2'));
    }

    @Test
    public void testHexDigitMsb0ToInt_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0xC, Conversion.hexDigitMsb0ToInt('3'));
    }

    @Test
    public void testHexDigitMsb0ToInt_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x2, Conversion.hexDigitMsb0ToInt('4'));
    }

    @Test
    public void testHexDigitMsb0ToInt_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0xA, Conversion.hexDigitMsb0ToInt('5'));
    }

    @Test
    public void testHexDigitMsb0ToInt_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x6, Conversion.hexDigitMsb0ToInt('6'));
    }

    @Test
    public void testHexDigitMsb0ToInt_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0xE, Conversion.hexDigitMsb0ToInt('7'));
    }

    @Test
    public void testHexDigitMsb0ToInt_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x1, Conversion.hexDigitMsb0ToInt('8'));
    }

    @Test
    public void testHexDigitMsb0ToInt_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x9, Conversion.hexDigitMsb0ToInt('9'));
    }

    @Test
    public void testHexDigitMsb0ToInt_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x5, Conversion.hexDigitMsb0ToInt('A'));
    }

    @Test
    public void testHexDigitMsb0ToInt_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x5, Conversion.hexDigitMsb0ToInt('a'));
    }

    @Test
    public void testHexDigitMsb0ToInt_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0xD, Conversion.hexDigitMsb0ToInt('B'));
    }

    @Test
    public void testHexDigitMsb0ToInt_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0xD, Conversion.hexDigitMsb0ToInt('b'));
    }

    @Test
    public void testHexDigitMsb0ToInt_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x3, Conversion.hexDigitMsb0ToInt('C'));
    }

    @Test
    public void testHexDigitMsb0ToInt_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x3, Conversion.hexDigitMsb0ToInt('c'));
    }

    @Test
    public void testHexDigitMsb0ToInt_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0xB, Conversion.hexDigitMsb0ToInt('D'));
    }

    @Test
    public void testHexDigitMsb0ToInt_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0xB, Conversion.hexDigitMsb0ToInt('d'));
    }

    @Test
    public void testHexDigitMsb0ToInt_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x7, Conversion.hexDigitMsb0ToInt('E'));
    }

    @Test
    public void testHexDigitMsb0ToInt_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x7, Conversion.hexDigitMsb0ToInt('e'));
    }

    @Test
    public void testHexDigitMsb0ToInt_21_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0xF, Conversion.hexDigitMsb0ToInt('F'));
    }

    @Test
    public void testHexDigitMsb0ToInt_22_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0xF, Conversion.hexDigitMsb0ToInt('f'));
    }

    @Test
    public void testHexDigitMsb0ToInt_23_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Conversion.hexDigitMsb0ToInt('G');
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testHexDigitToBinary_1_oe() {
        assertArrayEquals( new boolean[]{false, false, false, false}, Conversion.hexDigitToBinary('0'));
    }

    @Test
    public void testHexDigitToBinary_2_oe() {
        // removed other assertion
        assertArrayEquals( new boolean[]{true, false, false, false}, Conversion.hexDigitToBinary('1'));
    }

    @Test
    public void testHexDigitToBinary_3_oe() {
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{false, true, false, false}, Conversion.hexDigitToBinary('2'));
    }

    @Test
    public void testHexDigitToBinary_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, true, false, false}, Conversion.hexDigitToBinary('3'));
    }

    @Test
    public void testHexDigitToBinary_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{false, false, true, false}, Conversion.hexDigitToBinary('4'));
    }

    @Test
    public void testHexDigitToBinary_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, false, true, false}, Conversion.hexDigitToBinary('5'));
    }

    @Test
    public void testHexDigitToBinary_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{false, true, true, false}, Conversion.hexDigitToBinary('6'));
    }

    @Test
    public void testHexDigitToBinary_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, true, true, false}, Conversion.hexDigitToBinary('7'));
    }

    @Test
    public void testHexDigitToBinary_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{false, false, false, true}, Conversion.hexDigitToBinary('8'));
    }

    @Test
    public void testHexDigitToBinary_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, false, false, true}, Conversion.hexDigitToBinary('9'));
    }

    @Test
    public void testHexDigitToBinary_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{false, true, false, true}, Conversion.hexDigitToBinary('A'));
    }

    @Test
    public void testHexDigitToBinary_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{false, true, false, true}, Conversion.hexDigitToBinary('a'));
    }

    @Test
    public void testHexDigitToBinary_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, true, false, true}, Conversion.hexDigitToBinary('B'));
    }

    @Test
    public void testHexDigitToBinary_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, true, false, true}, Conversion.hexDigitToBinary('b'));
    }

    @Test
    public void testHexDigitToBinary_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{false, false, true, true}, Conversion.hexDigitToBinary('C'));
    }

    @Test
    public void testHexDigitToBinary_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{false, false, true, true}, Conversion.hexDigitToBinary('c'));
    }

    @Test
    public void testHexDigitToBinary_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, false, true, true}, Conversion.hexDigitToBinary('D'));
    }

    @Test
    public void testHexDigitToBinary_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, false, true, true}, Conversion.hexDigitToBinary('d'));
    }

    @Test
    public void testHexDigitToBinary_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{false, true, true, true}, Conversion.hexDigitToBinary('E'));
    }

    @Test
    public void testHexDigitToBinary_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{false, true, true, true}, Conversion.hexDigitToBinary('e'));
    }

    @Test
    public void testHexDigitToBinary_21_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, true, true, true}, Conversion.hexDigitToBinary('F'));
    }

    @Test
    public void testHexDigitToBinary_22_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, true, true, true}, Conversion.hexDigitToBinary('f'));
    }

    @Test
    public void testHexDigitToBinary_23_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Conversion.hexDigitToBinary('G');
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testHexDigitMsb0ToBinary_1_oe() {
        assertArrayEquals( new boolean[]{false, false, false, false}, Conversion.hexDigitMsb0ToBinary('0'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_2_oe() {
        // removed other assertion
        assertArrayEquals( new boolean[]{false, false, false, true}, Conversion.hexDigitMsb0ToBinary('1'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_3_oe() {
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{false, false, true, false}, Conversion.hexDigitMsb0ToBinary('2'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{false, false, true, true}, Conversion.hexDigitMsb0ToBinary('3'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{false, true, false, false}, Conversion.hexDigitMsb0ToBinary('4'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{false, true, false, true}, Conversion.hexDigitMsb0ToBinary('5'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{false, true, true, false}, Conversion.hexDigitMsb0ToBinary('6'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{false, true, true, true}, Conversion.hexDigitMsb0ToBinary('7'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, false, false, false}, Conversion.hexDigitMsb0ToBinary('8'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, false, false, true}, Conversion.hexDigitMsb0ToBinary('9'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, false, true, false}, Conversion.hexDigitMsb0ToBinary('A'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, false, true, false}, Conversion.hexDigitMsb0ToBinary('a'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, false, true, true}, Conversion.hexDigitMsb0ToBinary('B'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, false, true, true}, Conversion.hexDigitMsb0ToBinary('b'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, true, false, false}, Conversion.hexDigitMsb0ToBinary('C'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, true, false, false}, Conversion.hexDigitMsb0ToBinary('c'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, true, false, true}, Conversion.hexDigitMsb0ToBinary('D'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, true, false, true}, Conversion.hexDigitMsb0ToBinary('d'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, true, true, false}, Conversion.hexDigitMsb0ToBinary('E'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, true, true, false}, Conversion.hexDigitMsb0ToBinary('e'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_21_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, true, true, true}, Conversion.hexDigitMsb0ToBinary('F'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_22_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{true, true, true, true}, Conversion.hexDigitMsb0ToBinary('f'));
    }

    @Test
    public void testHexDigitMsb0ToBinary_23_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Conversion.hexDigitMsb0ToBinary('G');
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBinaryToHexDigit_1_oe() {
        assertEquals( '0', Conversion.binaryToHexDigit(new boolean[]{false, false, false, false}));
    }

    @Test
    public void testBinaryToHexDigit_2_oe() {
        // removed other assertion
        assertEquals('1', Conversion.binaryToHexDigit(new boolean[]{true, false, false, false}));
    }

    @Test
    public void testBinaryToHexDigit_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals('2', Conversion.binaryToHexDigit(new boolean[]{false, true, false, false}));
    }

    @Test
    public void testBinaryToHexDigit_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('3', Conversion.binaryToHexDigit(new boolean[]{true, true, false, false}));
    }

    @Test
    public void testBinaryToHexDigit_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('4', Conversion.binaryToHexDigit(new boolean[]{false, false, true, false}));
    }

    @Test
    public void testBinaryToHexDigit_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('5', Conversion.binaryToHexDigit(new boolean[]{true, false, true, false}));
    }

    @Test
    public void testBinaryToHexDigit_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('6', Conversion.binaryToHexDigit(new boolean[]{false, true, true, false}));
    }

    @Test
    public void testBinaryToHexDigit_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('7', Conversion.binaryToHexDigit(new boolean[]{true, true, true, false}));
    }

    @Test
    public void testBinaryToHexDigit_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('8', Conversion.binaryToHexDigit(new boolean[]{false, false, false, true}));
    }

    @Test
    public void testBinaryToHexDigit_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('9', Conversion.binaryToHexDigit(new boolean[]{true, false, false, true}));
    }

    @Test
    public void testBinaryToHexDigit_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('a', Conversion.binaryToHexDigit(new boolean[]{false, true, false, true}));
    }

    @Test
    public void testBinaryToHexDigit_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('b', Conversion.binaryToHexDigit(new boolean[]{true, true, false, true}));
    }

    @Test
    public void testBinaryToHexDigit_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('c', Conversion.binaryToHexDigit(new boolean[]{false, false, true, true}));
    }

    @Test
    public void testBinaryToHexDigit_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('d', Conversion.binaryToHexDigit(new boolean[]{true, false, true, true}));
    }

    @Test
    public void testBinaryToHexDigit_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('e', Conversion.binaryToHexDigit(new boolean[]{false, true, true, true}));
    }

    @Test
    public void testBinaryToHexDigit_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('f', Conversion.binaryToHexDigit(new boolean[]{true, true, true, true}));
    }

    @Test
    public void testBinaryToHexDigit_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('1', Conversion.binaryToHexDigit(new boolean[]{true}));
    }

    @Test
    public void testBinaryToHexDigit_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 'f', Conversion.binaryToHexDigit(new boolean[]{true, true, true, true, true}));
    }

    @Test
    public void testBinaryToHexDigit_19_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Conversion.binaryToHexDigit(new boolean[]{});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBinaryToHexDigit_2args_1_oe() {
        final boolean[] shortArray = new boolean[]{false, true, true};
        assertEquals('6', Conversion.binaryToHexDigit(shortArray, 0));
    }

    @Test
    public void testBinaryToHexDigit_2args_2_oe() {
        final boolean[] shortArray = new boolean[]{false, true, true};
        // removed other assertion
        assertEquals('3', Conversion.binaryToHexDigit(shortArray, 1));
    }

    @Test
    public void testBinaryToHexDigit_2args_3_oe() {
        final boolean[] shortArray = new boolean[]{false, true, true};
        // removed other assertion
        // removed other assertion
        assertEquals('1', Conversion.binaryToHexDigit(shortArray, 2));
    }

    @Test
    public void testBinaryToHexDigit_2args_4_oe() {
        final boolean[] shortArray = new boolean[]{false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        assertEquals('5', Conversion.binaryToHexDigit(longArray, 0));
    }

    @Test
    public void testBinaryToHexDigit_2args_5_oe() {
        final boolean[] shortArray = new boolean[]{false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        // removed other assertion
        assertEquals('2', Conversion.binaryToHexDigit(longArray, 1));
    }

    @Test
    public void testBinaryToHexDigit_2args_6_oe() {
        final boolean[] shortArray = new boolean[]{false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        // removed other assertion
        // removed other assertion
        assertEquals('9', Conversion.binaryToHexDigit(longArray, 2));
    }

    @Test
    public void testBinaryToHexDigit_2args_7_oe() {
        final boolean[] shortArray = new boolean[]{false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('c', Conversion.binaryToHexDigit(longArray, 3));
    }

    @Test
    public void testBinaryToHexDigit_2args_8_oe() {
        final boolean[] shortArray = new boolean[]{false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('6', Conversion.binaryToHexDigit(longArray, 4));
    }

    @Test
    public void testBinaryToHexDigit_2args_9_oe() {
        final boolean[] shortArray = new boolean[]{false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('3', Conversion.binaryToHexDigit(longArray, 5));
    }

    @Test
    public void testBinaryToHexDigit_2args_10_oe() {
        final boolean[] shortArray = new boolean[]{false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('1', Conversion.binaryToHexDigit(longArray, 6));
    }

    @Test
    public void testBinaryToHexDigitMsb0_bits_1_oe() {
        assertEquals( '0', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{false, false, false, false}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_bits_2_oe() {
        // removed other assertion
        assertEquals( '1', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{false, false, false, true}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_bits_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals( '2', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{false, false, true, false}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_bits_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( '3', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{false, false, true, true}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_bits_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( '4', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{false, true, false, false}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_bits_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( '5', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{false, true, false, true}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_bits_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( '6', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{false, true, true, false}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_bits_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( '7', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{false, true, true, true}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_bits_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( '8', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, false, false, false}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_bits_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( '9', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, false, false, true}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_bits_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 'a', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, false, true, false}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_bits_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 'b', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, false, true, true}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_bits_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 'c', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, true, false, false}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_bits_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 'd', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, true, false, true}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_bits_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 'e', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, true, true, false}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_bits_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 'f', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, true, true, true}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_bits_17_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBinaryToHexDigitMsb0_4bits_2args_1_oe() {
        // boolean[] shortArray = new boolean[]{true, true, false};
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(shortArray, 0));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(shortArray, 1));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(shortArray, 2));
        final boolean[] shortArray = new boolean[]{true, true, false, true};
        assertEquals('d', Conversion.binaryToHexDigitMsb0_4bits(shortArray, 0));
    }

    @Test
    public void testBinaryToHexDigitMsb0_4bits_2args_2_oe() {
        // boolean[] shortArray = new boolean[]{true, true, false};
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(shortArray, 0));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(shortArray, 1));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(shortArray, 2));
        final boolean[] shortArray = new boolean[]{true, true, false, true};
        // removed other assertion
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        assertEquals('a', Conversion.binaryToHexDigitMsb0_4bits(longArray, 0));
    }

    @Test
    public void testBinaryToHexDigitMsb0_4bits_2args_3_oe() {
        // boolean[] shortArray = new boolean[]{true, true, false};
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(shortArray, 0));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(shortArray, 1));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(shortArray, 2));
        final boolean[] shortArray = new boolean[]{true, true, false, true};
        // removed other assertion
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        // removed other assertion
        assertEquals('4', Conversion.binaryToHexDigitMsb0_4bits(longArray, 1));
    }

    @Test
    public void testBinaryToHexDigitMsb0_4bits_2args_4_oe() {
        // boolean[] shortArray = new boolean[]{true, true, false};
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(shortArray, 0));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(shortArray, 1));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(shortArray, 2));
        final boolean[] shortArray = new boolean[]{true, true, false, true};
        // removed other assertion
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        // removed other assertion
        // removed other assertion
        assertEquals('9', Conversion.binaryToHexDigitMsb0_4bits(longArray, 2));
    }

    @Test
    public void testBinaryToHexDigitMsb0_4bits_2args_5_oe() {
        // boolean[] shortArray = new boolean[]{true, true, false};
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(shortArray, 0));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(shortArray, 1));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(shortArray, 2));
        final boolean[] shortArray = new boolean[]{true, true, false, true};
        // removed other assertion
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('3', Conversion.binaryToHexDigitMsb0_4bits(longArray, 3));
    }

    @Test
    public void testBinaryToHexDigitMsb0_4bits_2args_6_oe() {
        // boolean[] shortArray = new boolean[]{true, true, false};
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(shortArray, 0));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(shortArray, 1));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(shortArray, 2));
        final boolean[] shortArray = new boolean[]{true, true, false, true};
        // removed other assertion
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(longArray, 4));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(longArray, 5));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(longArray, 6));
        final boolean[] maxLengthArray = new boolean[]{
            true, false, true, false, false, true, true, true};
        assertEquals('a', Conversion.binaryToHexDigitMsb0_4bits(maxLengthArray, 0));
    }

    @Test
    public void testBinaryToHexDigitMsb0_4bits_2args_7_oe() {
        // boolean[] shortArray = new boolean[]{true, true, false};
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(shortArray, 0));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(shortArray, 1));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(shortArray, 2));
        final boolean[] shortArray = new boolean[]{true, true, false, true};
        // removed other assertion
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(longArray, 4));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(longArray, 5));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(longArray, 6));
        final boolean[] maxLengthArray = new boolean[]{
            true, false, true, false, false, true, true, true};
        // removed other assertion
        assertEquals('4', Conversion.binaryToHexDigitMsb0_4bits(maxLengthArray, 1));
    }

    @Test
    public void testBinaryToHexDigitMsb0_4bits_2args_8_oe() {
        // boolean[] shortArray = new boolean[]{true, true, false};
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(shortArray, 0));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(shortArray, 1));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(shortArray, 2));
        final boolean[] shortArray = new boolean[]{true, true, false, true};
        // removed other assertion
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(longArray, 4));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(longArray, 5));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(longArray, 6));
        final boolean[] maxLengthArray = new boolean[]{
            true, false, true, false, false, true, true, true};
        // removed other assertion
        // removed other assertion
        assertEquals('9', Conversion.binaryToHexDigitMsb0_4bits(maxLengthArray, 2));
    }

    @Test
    public void testBinaryToHexDigitMsb0_4bits_2args_9_oe() {
        // boolean[] shortArray = new boolean[]{true, true, false};
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(shortArray, 0));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(shortArray, 1));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(shortArray, 2));
        final boolean[] shortArray = new boolean[]{true, true, false, true};
        // removed other assertion
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(longArray, 4));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(longArray, 5));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(longArray, 6));
        final boolean[] maxLengthArray = new boolean[]{
            true, false, true, false, false, true, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('3', Conversion.binaryToHexDigitMsb0_4bits(maxLengthArray, 3));
    }

    @Test
    public void testBinaryToHexDigitMsb0_4bits_2args_10_oe() {
        // boolean[] shortArray = new boolean[]{true, true, false};
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(shortArray, 0));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(shortArray, 1));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(shortArray, 2));
        final boolean[] shortArray = new boolean[]{true, true, false, true};
        // removed other assertion
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(longArray, 4));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(longArray, 5));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(longArray, 6));
        final boolean[] maxLengthArray = new boolean[]{
            true, false, true, false, false, true, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('7', Conversion.binaryToHexDigitMsb0_4bits(maxLengthArray, 4));
    }

    @Test
    public void testBinaryToHexDigitMsb0_4bits_2args_11_oe() {
        // boolean[] shortArray = new boolean[]{true, true, false};
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(shortArray, 0));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(shortArray, 1));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(shortArray, 2));
        final boolean[] shortArray = new boolean[]{true, true, false, true};
        // removed other assertion
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(longArray, 4));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(longArray, 5));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(longArray, 6));
        final boolean[] maxLengthArray = new boolean[]{
            true, false, true, false, false, true, true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals('7', Conversion.BinaryToHexDigitMsb0(longArray, 5));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(longArray, 6));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(longArray, 7));
        final boolean[] javaDocCheck = new boolean[]{
            true, false, false, true, true, false, true, false};
        assertEquals('d', Conversion.binaryToHexDigitMsb0_4bits(javaDocCheck, 3));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_1_oe() {
        assertEquals( '0', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, false, false, false}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2_oe() {
        // removed other assertion
        assertEquals( '1', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, false, false, true}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals( '2', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, false, true, false}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( '3', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, false, true, true}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( '4', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, true, false, false}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( '5', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, true, false, true}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( '6', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, true, true, false}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( '7', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, true, true, true}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( '8', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, false, false, false}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( '9', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, false, false, true}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 'a', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, false, true, false}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 'b', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, false, true, true}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 'c', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, true, false, false}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 'd', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, true, false, true}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 'e', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, true, true, false}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 'f', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, true, true, true}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( '4', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{ true, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_18_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Conversion.binaryBeMsb0ToHexDigit(new boolean[]{});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_1_oe() {
        assertEquals( '5', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{ true, false, false, false, false, false, false, false, false, false, false, true, false, true, false, false}, 2));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_2_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        assertEquals('6', Conversion.binaryBeMsb0ToHexDigit(shortArray, 0));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_3_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        assertEquals('3', Conversion.binaryBeMsb0ToHexDigit(shortArray, 1));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_4_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        assertEquals('1', Conversion.binaryBeMsb0ToHexDigit(shortArray, 2));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_5_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        assertEquals('5', Conversion.binaryBeMsb0ToHexDigit(shortArray2, 0));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_6_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        assertEquals('2', Conversion.binaryBeMsb0ToHexDigit(shortArray2, 1));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_7_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        assertEquals('9', Conversion.binaryBeMsb0ToHexDigit(shortArray2, 2));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_8_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('c', Conversion.binaryBeMsb0ToHexDigit(shortArray2, 3));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_9_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('e', Conversion.binaryBeMsb0ToHexDigit(shortArray2, 4));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_10_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('7', Conversion.binaryBeMsb0ToHexDigit(shortArray2, 5));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_11_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('3', Conversion.binaryBeMsb0ToHexDigit(shortArray2, 6));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_12_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('1', Conversion.binaryBeMsb0ToHexDigit(shortArray2, 7));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_13_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] multiBytesArray = new boolean[]{
            true, true, false, false, true, false, true, false, true, true, true, false, false,
            true, false, true};
        assertEquals('5', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 0));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_14_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] multiBytesArray = new boolean[]{
            true, true, false, false, true, false, true, false, true, true, true, false, false,
            true, false, true};
        // removed other assertion
        assertEquals('2', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 1));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_15_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] multiBytesArray = new boolean[]{
            true, true, false, false, true, false, true, false, true, true, true, false, false,
            true, false, true};
        // removed other assertion
        // removed other assertion
        assertEquals('9', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 2));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_16_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] multiBytesArray = new boolean[]{
            true, true, false, false, true, false, true, false, true, true, true, false, false,
            true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('c', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 3));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_17_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] multiBytesArray = new boolean[]{
            true, true, false, false, true, false, true, false, true, true, true, false, false,
            true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('e', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 4));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_18_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] multiBytesArray = new boolean[]{
            true, true, false, false, true, false, true, false, true, true, true, false, false,
            true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('7', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 5));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_19_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] multiBytesArray = new boolean[]{
            true, true, false, false, true, false, true, false, true, true, true, false, false,
            true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('b', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 6));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_20_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] multiBytesArray = new boolean[]{
            true, true, false, false, true, false, true, false, true, true, true, false, false,
            true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('5', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 7));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_21_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] multiBytesArray = new boolean[]{
            true, true, false, false, true, false, true, false, true, true, true, false, false,
            true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals('a', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 8));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_22_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] multiBytesArray = new boolean[]{
            true, true, false, false, true, false, true, false, true, true, true, false, false,
            true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals('5', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 9));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_23_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] multiBytesArray = new boolean[]{
            true, true, false, false, true, false, true, false, true, true, true, false, false,
            true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals('2', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 10));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_24_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] multiBytesArray = new boolean[]{
            true, true, false, false, true, false, true, false, true, true, true, false, false,
            true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('9', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 11));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_25_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] multiBytesArray = new boolean[]{
            true, true, false, false, true, false, true, false, true, true, true, false, false,
            true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('c', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 12));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_26_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] multiBytesArray = new boolean[]{
            true, true, false, false, true, false, true, false, true, true, true, false, false,
            true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('6', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 13));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_27_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] multiBytesArray = new boolean[]{
            true, true, false, false, true, false, true, false, true, true, true, false, false,
            true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('3', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 14));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigit_2args_28_oe() {
        // removed other assertion

        final boolean[] shortArray = new boolean[]{true, true, false};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final boolean[] multiBytesArray = new boolean[]{
            true, true, false, false, true, false, true, false, true, true, true, false, false,
            true, false, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('1', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 15));
    }

    @Test
    public void testIntToHexDigit_1_oe() {
        assertEquals('0', Conversion.intToHexDigit(0));
    }

    @Test
    public void testIntToHexDigit_2_oe() {
        // removed other assertion
        assertEquals('1', Conversion.intToHexDigit(1));
    }

    @Test
    public void testIntToHexDigit_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals('2', Conversion.intToHexDigit(2));
    }

    @Test
    public void testIntToHexDigit_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('3', Conversion.intToHexDigit(3));
    }

    @Test
    public void testIntToHexDigit_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('4', Conversion.intToHexDigit(4));
    }

    @Test
    public void testIntToHexDigit_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('5', Conversion.intToHexDigit(5));
    }

    @Test
    public void testIntToHexDigit_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('6', Conversion.intToHexDigit(6));
    }

    @Test
    public void testIntToHexDigit_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('7', Conversion.intToHexDigit(7));
    }

    @Test
    public void testIntToHexDigit_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('8', Conversion.intToHexDigit(8));
    }

    @Test
    public void testIntToHexDigit_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('9', Conversion.intToHexDigit(9));
    }

    @Test
    public void testIntToHexDigit_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('a', Conversion.intToHexDigit(10));
    }

    @Test
    public void testIntToHexDigit_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('b', Conversion.intToHexDigit(11));
    }

    @Test
    public void testIntToHexDigit_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('c', Conversion.intToHexDigit(12));
    }

    @Test
    public void testIntToHexDigit_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('d', Conversion.intToHexDigit(13));
    }

    @Test
    public void testIntToHexDigit_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('e', Conversion.intToHexDigit(14));
    }

    @Test
    public void testIntToHexDigit_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('f', Conversion.intToHexDigit(15));
    }

    @Test
    public void testIntToHexDigit_17_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Conversion.intToHexDigit(16);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testIntToHexDigitMsb0_1_oe() {
        assertEquals('0', Conversion.intToHexDigitMsb0(0));
    }

    @Test
    public void testIntToHexDigitMsb0_2_oe() {
        // removed other assertion
        assertEquals('8', Conversion.intToHexDigitMsb0(1));
    }

    @Test
    public void testIntToHexDigitMsb0_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals('4', Conversion.intToHexDigitMsb0(2));
    }

    @Test
    public void testIntToHexDigitMsb0_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('c', Conversion.intToHexDigitMsb0(3));
    }

    @Test
    public void testIntToHexDigitMsb0_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('2', Conversion.intToHexDigitMsb0(4));
    }

    @Test
    public void testIntToHexDigitMsb0_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('a', Conversion.intToHexDigitMsb0(5));
    }

    @Test
    public void testIntToHexDigitMsb0_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('6', Conversion.intToHexDigitMsb0(6));
    }

    @Test
    public void testIntToHexDigitMsb0_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('e', Conversion.intToHexDigitMsb0(7));
    }

    @Test
    public void testIntToHexDigitMsb0_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('1', Conversion.intToHexDigitMsb0(8));
    }

    @Test
    public void testIntToHexDigitMsb0_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('9', Conversion.intToHexDigitMsb0(9));
    }

    @Test
    public void testIntToHexDigitMsb0_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('5', Conversion.intToHexDigitMsb0(10));
    }

    @Test
    public void testIntToHexDigitMsb0_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('d', Conversion.intToHexDigitMsb0(11));
    }

    @Test
    public void testIntToHexDigitMsb0_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('3', Conversion.intToHexDigitMsb0(12));
    }

    @Test
    public void testIntToHexDigitMsb0_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('b', Conversion.intToHexDigitMsb0(13));
    }

    @Test
    public void testIntToHexDigitMsb0_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('7', Conversion.intToHexDigitMsb0(14));
    }

    @Test
    public void testIntToHexDigitMsb0_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('f', Conversion.intToHexDigitMsb0(15));
    }

    @Test
    public void testIntToHexDigitMsb0_17_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    Conversion.intToHexDigitMsb0(16);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testIntArrayToLong_1_oe() {
        final int[] src = new int[]{0xCDF1F0C1, 0x0F123456, 0x78000000};
        assertEquals(0x0000000000000000L, Conversion.intArrayToLong(src, 0, 0L, 0, 0));
    }

    @Test
    public void testIntArrayToLong_2_oe() {
        final int[] src = new int[]{0xCDF1F0C1, 0x0F123456, 0x78000000};
        // removed other assertion
        assertEquals(0x0000000000000000L, Conversion.intArrayToLong(src, 1, 0L, 0, 0));
    }

    @Test
    public void testIntArrayToLong_3_oe() {
        final int[] src = new int[]{0xCDF1F0C1, 0x0F123456, 0x78000000};
        // removed other assertion
        // removed other assertion
        assertEquals(0x00000000CDF1F0C1L, Conversion.intArrayToLong(src, 0, 0L, 0, 1));
    }

    @Test
    public void testIntArrayToLong_4_oe() {
        final int[] src = new int[]{0xCDF1F0C1, 0x0F123456, 0x78000000};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x0F123456CDF1F0C1L, Conversion.intArrayToLong(src, 0, 0L, 0, 2));
    }

    @Test
    public void testIntArrayToLong_5_oe() {
        final int[] src = new int[]{0xCDF1F0C1, 0x0F123456, 0x78000000};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x000000000F123456L, Conversion.intArrayToLong(src, 1, 0L, 0, 1));
    }

    @Test
    public void testIntArrayToLong_6_oe() {
        final int[] src = new int[]{0xCDF1F0C1, 0x0F123456, 0x78000000};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x123456789ABCDEF0L,Conversion.intArrayToLong(src,0,0x123456789ABCDEF0L,0,0));
    }

    @Test
    public void testIntArrayToLong_7_oe() {
        final int[] src = new int[]{0xCDF1F0C1, 0x0F123456, 0x78000000};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x1234567878000000L,Conversion.intArrayToLong(src,2,0x123456789ABCDEF0L,0,1));
    }

    @Test
    public void testShortArrayToLong_1_oe() {
        final short[] src = new short[]{
            (short) 0xCDF1, (short) 0xF0C1, (short) 0x0F12, (short) 0x3456, (short) 0x7800};
        assertEquals(0x0000000000000000L, Conversion.shortArrayToLong(src, 0, 0L, 0, 0));
    }

    @Test
    public void testShortArrayToLong_2_oe() {
        final short[] src = new short[]{
            (short) 0xCDF1, (short) 0xF0C1, (short) 0x0F12, (short) 0x3456, (short) 0x7800};
        // removed other assertion
        assertEquals(0x000000000000CDF1L, Conversion.shortArrayToLong(src, 0, 0L, 0, 1));
    }

    @Test
    public void testShortArrayToLong_3_oe() {
        final short[] src = new short[]{
            (short) 0xCDF1, (short) 0xF0C1, (short) 0x0F12, (short) 0x3456, (short) 0x7800};
        // removed other assertion
        // removed other assertion
        assertEquals(0x00000000F0C1CDF1L, Conversion.shortArrayToLong(src, 0, 0L, 0, 2));
    }

    @Test
    public void testShortArrayToLong_4_oe() {
        final short[] src = new short[]{
            (short) 0xCDF1, (short) 0xF0C1, (short) 0x0F12, (short) 0x3456, (short) 0x7800};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x780034560F12F0C1L, Conversion.shortArrayToLong(src, 1, 0L, 0, 4));
    }

    @Test
    public void testShortArrayToLong_5_oe() {
        final short[] src = new short[]{
            (short) 0xCDF1, (short) 0xF0C1, (short) 0x0F12, (short) 0x3456, (short) 0x7800};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x123456789ABCDEF0L,Conversion.shortArrayToLong(src,0,0x123456789ABCDEF0L,0,0));
    }

    @Test
    public void testShortArrayToLong_6_oe() {
        final short[] src = new short[]{
            (short) 0xCDF1, (short) 0xF0C1, (short) 0x0F12, (short) 0x3456, (short) 0x7800};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x123456CDF1BCDEF0L,Conversion.shortArrayToLong(src,0,0x123456789ABCDEF0L,24,1));
    }

    @Test
    public void testShortArrayToLong_7_oe() {
        final short[] src = new short[]{
            (short) 0xCDF1, (short) 0xF0C1, (short) 0x0F12, (short) 0x3456, (short) 0x7800};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x123478003456DEF0L,Conversion.shortArrayToLong(src,3,0x123456789ABCDEF0L,16,2));
    }

    @Test
    public void testByteArrayToLong_1_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        assertEquals(0x0000000000000000L, Conversion.byteArrayToLong(src, 0, 0L, 0, 0));
    }

    @Test
    public void testByteArrayToLong_2_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        // removed other assertion
        assertEquals(0x00000000000000CDL, Conversion.byteArrayToLong(src, 0, 0L, 0, 1));
    }

    @Test
    public void testByteArrayToLong_3_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        // removed other assertion
        // removed other assertion
        assertEquals(0x00000000C1F0F1CDL, Conversion.byteArrayToLong(src, 0, 0L, 0, 4));
    }

    @Test
    public void testByteArrayToLong_4_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x000000000FC1F0F1L, Conversion.byteArrayToLong(src, 1, 0L, 0, 4));
    }

    @Test
    public void testByteArrayToLong_5_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x123456789ABCDEF0L,Conversion.byteArrayToLong(src,0,0x123456789ABCDEF0L,0,0));
    }

    @Test
    public void testByteArrayToLong_6_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x12345678CDBCDEF0L,Conversion.byteArrayToLong(src,0,0x123456789ABCDEF0L,24,1));
    }

    @Test
    public void testByteArrayToLong_7_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x123456789A7856F0L,Conversion.byteArrayToLong(src,7,0x123456789ABCDEF0L,8,2));
    }

    @Test
    public void testShortArrayToInt_1_oe() {
        final short[] src = new short[]{
            (short) 0xCDF1, (short) 0xF0C1, (short) 0x0F12, (short) 0x3456, (short) 0x7800};
        assertEquals(0x00000000, Conversion.shortArrayToInt(src, 0, 0, 0, 0));
    }

    @Test
    public void testShortArrayToInt_2_oe() {
        final short[] src = new short[]{
            (short) 0xCDF1, (short) 0xF0C1, (short) 0x0F12, (short) 0x3456, (short) 0x7800};
        // removed other assertion
        assertEquals(0x0000CDF1, Conversion.shortArrayToInt(src, 0, 0, 0, 1));
    }

    @Test
    public void testShortArrayToInt_3_oe() {
        final short[] src = new short[]{
            (short) 0xCDF1, (short) 0xF0C1, (short) 0x0F12, (short) 0x3456, (short) 0x7800};
        // removed other assertion
        // removed other assertion
        assertEquals(0xF0C1CDF1, Conversion.shortArrayToInt(src, 0, 0, 0, 2));
    }

    @Test
    public void testShortArrayToInt_4_oe() {
        final short[] src = new short[]{
            (short) 0xCDF1, (short) 0xF0C1, (short) 0x0F12, (short) 0x3456, (short) 0x7800};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x0F12F0C1, Conversion.shortArrayToInt(src, 1, 0, 0, 2));
    }

    @Test
    public void testShortArrayToInt_5_oe() {
        final short[] src = new short[]{
            (short) 0xCDF1, (short) 0xF0C1, (short) 0x0F12, (short) 0x3456, (short) 0x7800};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x12345678, Conversion.shortArrayToInt(src, 0, 0x12345678, 0, 0));
    }

    @Test
    public void testShortArrayToInt_6_oe() {
        final short[] src = new short[]{
            (short) 0xCDF1, (short) 0xF0C1, (short) 0x0F12, (short) 0x3456, (short) 0x7800};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0xCDF15678, Conversion.shortArrayToInt(src, 0, 0x12345678, 16, 1));
    }

    @Test
    public void testByteArrayToInt_1_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        assertEquals(0x00000000, Conversion.byteArrayToInt(src, 0, 0, 0, 0));
    }

    @Test
    public void testByteArrayToInt_2_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        // removed other assertion
        assertEquals(0x000000CD, Conversion.byteArrayToInt(src, 0, 0, 0, 1));
    }

    @Test
    public void testByteArrayToInt_3_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        // removed other assertion
        // removed other assertion
        assertEquals(0xC1F0F1CD, Conversion.byteArrayToInt(src, 0, 0, 0, 4));
    }

    @Test
    public void testByteArrayToInt_4_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x0FC1F0F1, Conversion.byteArrayToInt(src, 1, 0, 0, 4));
    }

    @Test
    public void testByteArrayToInt_5_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x12345678, Conversion.byteArrayToInt(src, 0, 0x12345678, 0, 0));
    }

    @Test
    public void testByteArrayToInt_6_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0xCD345678, Conversion.byteArrayToInt(src, 0, 0x12345678, 24, 1));
    }

    @Test
    public void testByteArrayToShort_1_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        assertEquals((short) 0x0000, Conversion.byteArrayToShort(src, 0, (short) 0, 0, 0));
    }

    @Test
    public void testByteArrayToShort_2_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        // removed other assertion
        assertEquals((short) 0x00CD, Conversion.byteArrayToShort(src, 0, (short) 0, 0, 1));
    }

    @Test
    public void testByteArrayToShort_3_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        // removed other assertion
        // removed other assertion
        assertEquals((short) 0xF1CD, Conversion.byteArrayToShort(src, 0, (short) 0, 0, 2));
    }

    @Test
    public void testByteArrayToShort_4_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((short) 0xF0F1, Conversion.byteArrayToShort(src, 1, (short) 0, 0, 2));
    }

    @Test
    public void testByteArrayToShort_5_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((short) 0x1234, Conversion.byteArrayToShort(src, 0, (short) 0x1234, 0, 0));
    }

    @Test
    public void testByteArrayToShort_6_oe() {
        final byte[] src = new byte[]{
            (byte) 0xCD, (byte) 0xF1, (byte) 0xF0, (byte) 0xC1, (byte) 0x0F, (byte) 0x12, (byte) 0x34,
            (byte) 0x56, (byte) 0x78};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((short) 0xCD34, Conversion.byteArrayToShort(src, 0, (short) 0x1234, 8, 1));
    }

    @Test
    public void testHexToLong_1_oe() {
        final String src = "CDF1F0C10F12345678";
        assertEquals(0x0000000000000000L, Conversion.hexToLong(src, 0, 0L, 0, 0));
    }

    @Test
    public void testHexToLong_2_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        assertEquals(0x000000000000000CL, Conversion.hexToLong(src, 0, 0L, 0, 1));
    }

    @Test
    public void testHexToLong_3_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        // removed other assertion
        assertEquals(0x000000001C0F1FDCL, Conversion.hexToLong(src, 0, 0L, 0, 8));
    }

    @Test
    public void testHexToLong_4_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x0000000001C0F1FDL, Conversion.hexToLong(src, 1, 0L, 0, 8));
    }

    @Test
    public void testHexToLong_5_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x123456798ABCDEF0L,Conversion.hexToLong(src,0,0x123456798ABCDEF0L,0,0));
    }

    @Test
    public void testHexToLong_6_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x1234567876BCDEF0L,Conversion.hexToLong(src,15,0x123456798ABCDEF0L,24,3));
    }

    @Test
    public void testHexToInt_1_oe() {
        final String src = "CDF1F0C10F12345678";
        assertEquals(0x00000000, Conversion.hexToInt(src, 0, 0, 0, 0));
    }

    @Test
    public void testHexToInt_2_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        assertEquals(0x0000000C, Conversion.hexToInt(src, 0, 0, 0, 1));
    }

    @Test
    public void testHexToInt_3_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        // removed other assertion
        assertEquals(0x1C0F1FDC, Conversion.hexToInt(src, 0, 0, 0, 8));
    }

    @Test
    public void testHexToInt_4_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x01C0F1FD, Conversion.hexToInt(src, 1, 0, 0, 8));
    }

    @Test
    public void testHexToInt_5_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x12345679, Conversion.hexToInt(src, 0, 0x12345679, 0, 0));
    }

    @Test
    public void testHexToInt_6_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x87645679, Conversion.hexToInt(src, 15, 0x12345679, 20, 3));
    }

    @Test
    public void testHexToShort_1_oe() {
        final String src = "CDF1F0C10F12345678";
        assertEquals((short) 0x0000, Conversion.hexToShort(src, 0, (short) 0, 0, 0));
    }

    @Test
    public void testHexToShort_2_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        assertEquals((short) 0x000C, Conversion.hexToShort(src, 0, (short) 0, 0, 1));
    }

    @Test
    public void testHexToShort_3_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        // removed other assertion
        assertEquals((short) 0x1FDC, Conversion.hexToShort(src, 0, (short) 0, 0, 4));
    }

    @Test
    public void testHexToShort_4_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((short) 0xF1FD, Conversion.hexToShort(src, 1, (short) 0, 0, 4));
    }

    @Test
    public void testHexToShort_5_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((short) 0x1234, Conversion.hexToShort(src, 0, (short) 0x1234, 0, 0));
    }

    @Test
    public void testHexToShort_6_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((short) 0x8764, Conversion.hexToShort(src, 15, (short) 0x1234, 4, 3));
    }

    @Test
    public void testHexToByte_1_oe() {
        final String src = "CDF1F0C10F12345678";
        assertEquals((byte) 0x00, Conversion.hexToByte(src, 0, (byte) 0, 0, 0));
    }

    @Test
    public void testHexToByte_2_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        assertEquals((byte) 0x0C, Conversion.hexToByte(src, 0, (byte) 0, 0, 1));
    }

    @Test
    public void testHexToByte_3_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        // removed other assertion
        assertEquals((byte) 0xDC, Conversion.hexToByte(src, 0, (byte) 0, 0, 2));
    }

    @Test
    public void testHexToByte_4_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((byte) 0xFD, Conversion.hexToByte(src, 1, (byte) 0, 0, 2));
    }

    @Test
    public void testHexToByte_5_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((byte) 0x34, Conversion.hexToByte(src, 0, (byte) 0x34, 0, 0));
    }

    @Test
    public void testHexToByte_6_oe() {
        final String src = "CDF1F0C10F12345678";
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((byte) 0x84, Conversion.hexToByte(src, 17, (byte) 0x34, 4, 1));
    }

    @Test
    public void testBinaryToLong_1_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        assertEquals(0x0000000000000000L, Conversion.binaryToLong(src, 0, 0L, 0, 0));
    }

    @Test
    public void testBinaryToLong_2_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        assertEquals(0x000000000000000CL, Conversion.binaryToLong(src, 0, 0L, 0, 1 * 4));
    }

    @Test
    public void testBinaryToLong_3_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        // removed other assertion
        assertEquals(0x000000001C0F1FDCL, Conversion.binaryToLong(src, 0, 0L, 0, 8 * 4));
    }

    @Test
    public void testBinaryToLong_4_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x0000000001C0F1FDL, Conversion.binaryToLong(src, 1 * 4, 0L, 0, 8 * 4));
    }

    @Test
    public void testBinaryToLong_5_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x123456798ABCDEF0L,Conversion.binaryToLong(src,0,0x123456798ABCDEF0L,0,0));
    }

    @Test
    public void testBinaryToLong_6_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x1234567876BCDEF0L,Conversion.binaryToLong(src,15 * 4,0x123456798ABCDEF0L,24,3 * 4));
    }

    @Test
    public void testBinaryToInt_1_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        assertEquals(0x00000000, Conversion.binaryToInt(src, 0 * 4, 0, 0, 0 * 4));
    }

    @Test
    public void testBinaryToInt_2_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        assertEquals(0x0000000C, Conversion.binaryToInt(src, 0 * 4, 0, 0, 1 * 4));
    }

    @Test
    public void testBinaryToInt_3_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        // removed other assertion
        assertEquals(0x1C0F1FDC, Conversion.binaryToInt(src, 0 * 4, 0, 0, 8 * 4));
    }

    @Test
    public void testBinaryToInt_4_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x01C0F1FD, Conversion.binaryToInt(src, 1 * 4, 0, 0, 8 * 4));
    }

    @Test
    public void testBinaryToInt_5_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x12345679, Conversion.binaryToInt(src, 0 * 4, 0x12345679, 0, 0 * 4));
    }

    @Test
    public void testBinaryToInt_6_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0x87645679, Conversion.binaryToInt(src, 15 * 4, 0x12345679, 20, 3 * 4));
    }

    @Test
    public void testBinaryToShort_1_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        assertEquals((short) 0x0000, Conversion.binaryToShort(src, 0 * 4, (short) 0, 0, 0 * 4));
    }

    @Test
    public void testBinaryToShort_2_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        assertEquals((short) 0x000C, Conversion.binaryToShort(src, 0 * 4, (short) 0, 0, 1 * 4));
    }

    @Test
    public void testBinaryToShort_3_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        // removed other assertion
        assertEquals((short) 0x1FDC, Conversion.binaryToShort(src, 0 * 4, (short) 0, 0, 4 * 4));
    }

    @Test
    public void testBinaryToShort_4_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((short) 0xF1FD, Conversion.binaryToShort(src, 1 * 4, (short) 0, 0, 4 * 4));
    }

    @Test
    public void testBinaryToShort_5_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((short)0x1234,Conversion.binaryToShort(src,0 * 4,(short)0x1234,0,0 * 4));
    }

    @Test
    public void testBinaryToShort_6_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((short)0x8764,Conversion.binaryToShort(src,15 * 4,(short)0x1234,4,3 * 4));
    }

    @Test
    public void testBinaryToByte_1_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        assertEquals((byte) 0x00, Conversion.binaryToByte(src, 0 * 4, (byte) 0, 0, 0 * 4));
    }

    @Test
    public void testBinaryToByte_2_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        assertEquals((byte) 0x0C, Conversion.binaryToByte(src, 0 * 4, (byte) 0, 0, 1 * 4));
    }

    @Test
    public void testBinaryToByte_3_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        // removed other assertion
        assertEquals((byte) 0xDC, Conversion.binaryToByte(src, 0 * 4, (byte) 0, 0, 2 * 4));
    }

    @Test
    public void testBinaryToByte_4_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((byte) 0xFD, Conversion.binaryToByte(src, 1 * 4, (byte) 0, 0, 2 * 4));
    }

    @Test
    public void testBinaryToByte_5_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((byte) 0x34, Conversion.binaryToByte(src, 0 * 4, (byte) 0x34, 0, 0 * 4));
    }

    @Test
    public void testBinaryToByte_6_oe() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((byte) 0x84, Conversion.binaryToByte(src, 17 * 4, (byte) 0x34, 4, 1 * 4));
    }

    @Test
    public void testLongToIntArray_1_oe() {
        assertArrayEquals( new int[]{}, Conversion.longToIntArray(0x0000000000000000L, 0, new int[]{}, 0, 0));
    }

    @Test
    public void testLongToIntArray_2_oe() {
        // removed other assertion
        assertArrayEquals( new int[]{}, Conversion.longToIntArray(0x0000000000000000L, 100, new int[]{}, 0, 0));
    }

    @Test
    public void testLongToIntArray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new int[]{}, Conversion.longToIntArray(0x0000000000000000L, 0, new int[]{}, 100, 0));
    }

    @Test
    public void testLongToIntArray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new int[]{0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF}, Conversion.longToIntArray(0x1234567890ABCDEFL, 0, new int[]{-1, -1, -1, -1}, 0, 0));
    }

    @Test
    public void testLongToIntArray_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new int[]{0x90ABCDEF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF}, Conversion.longToIntArray(0x1234567890ABCDEFL, 0, new int[]{-1, -1, -1, -1}, 0, 1));
    }

    @Test
    public void testLongToIntArray_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new int[]{0x90ABCDEF, 0x12345678, 0xFFFFFFFF, 0xFFFFFFFF}, Conversion.longToIntArray(0x1234567890ABCDEFL, 0, new int[]{-1, -1, -1, -1}, 0, 2));
    }

    @Test
    public void testLongToIntArray_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // int[]{0x90ABCDEF, 0x12345678, 0x90ABCDEF, 0x12345678}, Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0, new int[]{-1, -1, -1, -1}, 0, 4));//rejected by assertion
        // assertArrayEquals(new
        // int[]{0xFFFFFFFF, 0x90ABCDEF, 0x12345678, 0x90ABCDEF}, Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0, new int[]{-1, -1, -1, -1}, 1, 3));
        assertArrayEquals( new int[]{0xFFFFFFFF, 0xFFFFFFFF, 0x90ABCDEF, 0x12345678}, Conversion.longToIntArray(0x1234567890ABCDEFL, 0, new int[]{-1, -1, -1, -1}, 2, 2));
    }

    @Test
    public void testLongToIntArray_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // int[]{0x90ABCDEF, 0x12345678, 0x90ABCDEF, 0x12345678}, Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0, new int[]{-1, -1, -1, -1}, 0, 4));//rejected by assertion
        // assertArrayEquals(new
        // int[]{0xFFFFFFFF, 0x90ABCDEF, 0x12345678, 0x90ABCDEF}, Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0, new int[]{-1, -1, -1, -1}, 1, 3));
        // removed other assertion
        assertArrayEquals( new int[]{0xFFFFFFFF, 0xFFFFFFFF, 0x90ABCDEF, 0xFFFFFFFF}, Conversion.longToIntArray(0x1234567890ABCDEFL, 0, new int[]{-1, -1, -1, -1}, 2, 1));
    }

    @Test
    public void testLongToIntArray_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // int[]{0x90ABCDEF, 0x12345678, 0x90ABCDEF, 0x12345678}, Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0, new int[]{-1, -1, -1, -1}, 0, 4));//rejected by assertion
        // assertArrayEquals(new
        // int[]{0xFFFFFFFF, 0x90ABCDEF, 0x12345678, 0x90ABCDEF}, Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0, new int[]{-1, -1, -1, -1}, 1, 3));
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new int[]{0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x90ABCDEF}, Conversion.longToIntArray(0x1234567890ABCDEFL, 0, new int[]{-1, -1, -1, -1}, 3, 1));
    }

    @Test
    public void testLongToIntArray_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // int[]{0x90ABCDEF, 0x12345678, 0x90ABCDEF, 0x12345678}, Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0, new int[]{-1, -1, -1, -1}, 0, 4));//rejected by assertion
        // assertArrayEquals(new
        // int[]{0xFFFFFFFF, 0x90ABCDEF, 0x12345678, 0x90ABCDEF}, Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0, new int[]{-1, -1, -1, -1}, 1, 3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new int[]{0xFFFFFFFF, 0xFFFFFFFF, 0x4855E6F7, 0xFFFFFFFF}, Conversion.longToIntArray(0x1234567890ABCDEFL, 1, new int[]{-1, -1, -1, -1}, 2, 1));
    }

    @Test
    public void testLongToIntArray_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // int[]{0x90ABCDEF, 0x12345678, 0x90ABCDEF, 0x12345678}, Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0, new int[]{-1, -1, -1, -1}, 0, 4));//rejected by assertion
        // assertArrayEquals(new
        // int[]{0xFFFFFFFF, 0x90ABCDEF, 0x12345678, 0x90ABCDEF}, Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0, new int[]{-1, -1, -1, -1}, 1, 3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new int[]{0xFFFFFFFF, 0xFFFFFFFF, 0x242AF37B, 0xFFFFFFFF}, Conversion.longToIntArray(0x1234567890ABCDEFL, 2, new int[]{-1, -1, -1, -1}, 2, 1));
    }

    @Test
    public void testLongToIntArray_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // int[]{0x90ABCDEF, 0x12345678, 0x90ABCDEF, 0x12345678}, Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0, new int[]{-1, -1, -1, -1}, 0, 4));//rejected by assertion
        // assertArrayEquals(new
        // int[]{0xFFFFFFFF, 0x90ABCDEF, 0x12345678, 0x90ABCDEF}, Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0, new int[]{-1, -1, -1, -1}, 1, 3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new int[]{0xFFFFFFFF, 0xFFFFFFFF, 0x121579BD, 0xFFFFFFFF}, Conversion.longToIntArray(0x1234567890ABCDEFL, 3, new int[]{-1, -1, -1, -1}, 2, 1));
    }

    @Test
    public void testLongToIntArray_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // int[]{0x90ABCDEF, 0x12345678, 0x90ABCDEF, 0x12345678}, Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0, new int[]{-1, -1, -1, -1}, 0, 4));//rejected by assertion
        // assertArrayEquals(new
        // int[]{0xFFFFFFFF, 0x90ABCDEF, 0x12345678, 0x90ABCDEF}, Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0, new int[]{-1, -1, -1, -1}, 1, 3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new int[]{0xFFFFFFFF, 0xFFFFFFFF, 0x890ABCDE, 0xFFFFFFFF}, Conversion.longToIntArray(0x1234567890ABCDEFL, 4, new int[]{-1, -1, -1, -1}, 2, 1));
    }

    @Test
    public void testLongToIntArray_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // int[]{0x90ABCDEF, 0x12345678, 0x90ABCDEF, 0x12345678}, Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0, new int[]{-1, -1, -1, -1}, 0, 4));//rejected by assertion
        // assertArrayEquals(new
        // int[]{0xFFFFFFFF, 0x90ABCDEF, 0x12345678, 0x90ABCDEF}, Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0, new int[]{-1, -1, -1, -1}, 1, 3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // int[]{0x4855E6F7, 0x091A2B3C, 0x4855E6F7, 0x091A2B3C}, Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 1, new int[]{-1, -1, -1, -1}, 0, 4));//rejected by assertion
        assertArrayEquals( new int[]{0x091A2B3C}, Conversion.longToIntArray(0x1234567890ABCDEFL, 33, new int[]{0}, 0, 1));
    }

    @Test
    public void testLongToShortArray_1_oe() {
        assertArrayEquals( new short[]{}, Conversion.longToShortArray(0x0000000000000000L, 0, new short[]{}, 0, 0));
    }

    @Test
    public void testLongToShortArray_2_oe() {
        // removed other assertion
        assertArrayEquals( new short[]{}, Conversion.longToShortArray(0x0000000000000000L, 100, new short[]{}, 0, 0));
    }

    @Test
    public void testLongToShortArray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{}, Conversion.longToShortArray(0x0000000000000000L, 0, new short[]{}, 100, 0));
    }

    @Test
    public void testLongToShortArray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xFFFF, (short) 0xFFFF, (short) 0xFFFF, (short) 0xFFFF}, Conversion.longToShortArray( 0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 0, 0));
    }

    @Test
    public void testLongToShortArray_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xCDEF, (short) 0xFFFF, (short) 0xFFFF, (short) 0xFFFF}, Conversion.longToShortArray( 0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 0, 1));
    }

    @Test
    public void testLongToShortArray_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xCDEF, (short) 0x90AB, (short) 0xFFFF, (short) 0xFFFF}, Conversion.longToShortArray( 0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 0, 2));
    }

    @Test
    public void testLongToShortArray_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xCDEF, (short) 0x90AB, (short) 0x5678, (short) 0xFFFF}, Conversion.longToShortArray( 0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 0, 3));
    }

    @Test
    public void testLongToShortArray_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xCDEF, (short) 0x90AB, (short) 0x5678, (short) 0x1234}, Conversion.longToShortArray( 0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 0, 4));
    }

    @Test
    public void testLongToShortArray_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xFFFF, (short) 0xCDEF, (short) 0x90AB, (short) 0x5678}, Conversion.longToShortArray( 0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 1, 3));
    }

    @Test
    public void testLongToShortArray_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xFFFF, (short) 0xFFFF, (short) 0xCDEF, (short) 0x90AB}, Conversion.longToShortArray( 0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 2, 2));
    }

    @Test
    public void testLongToShortArray_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xFFFF, (short) 0xFFFF, (short) 0xCDEF, (short) 0xFFFF}, Conversion.longToShortArray( 0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 2, 1));
    }

    @Test
    public void testLongToShortArray_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xFFFF, (short) 0xFFFF, (short) 0xFFFF, (short) 0xCDEF}, Conversion.longToShortArray( 0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 3, 1));
    }

    @Test
    public void testLongToShortArray_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xFFFF, (short) 0xFFFF, (short) 0xE6F7, (short) 0xFFFF}, Conversion.longToShortArray( 0x1234567890ABCDEFL, 1, new short[]{-1, -1, -1, -1}, 2, 1));
    }

    @Test
    public void testLongToShortArray_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xFFFF, (short) 0xFFFF, (short) 0xF37B, (short) 0xFFFF}, Conversion.longToShortArray( 0x1234567890ABCDEFL, 2, new short[]{-1, -1, -1, -1}, 2, 1));
    }

    @Test
    public void testLongToShortArray_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xFFFF, (short) 0xFFFF, (short) 0x79BD, (short) 0xFFFF}, Conversion.longToShortArray( 0x1234567890ABCDEFL, 3, new short[]{-1, -1, -1, -1}, 2, 1));
    }

    @Test
    public void testLongToShortArray_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xFFFF, (short) 0xFFFF, (short) 0xBCDE, (short) 0xFFFF}, Conversion.longToShortArray( 0x1234567890ABCDEFL, 4, new short[]{-1, -1, -1, -1}, 2, 1));
    }

    @Test
    public void testLongToShortArray_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xE6F7, (short) 0x4855, (short) 0x2B3C, (short) 0x091A}, Conversion.longToShortArray( 0x1234567890ABCDEFL, 1, new short[]{-1, -1, -1, -1}, 0, 4));
    }

    @Test
    public void testLongToShortArray_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0x2B3C}, Conversion.longToShortArray(0x1234567890ABCDEFL, 33, new short[]{0}, 0, 1));
    }

    @Test
    public void testIntToShortArray_1_oe() {
        assertArrayEquals( new short[]{}, Conversion.intToShortArray(0x00000000, 0, new short[]{}, 0, 0));
    }

    @Test
    public void testIntToShortArray_2_oe() {
        // removed other assertion
        assertArrayEquals( new short[]{}, Conversion.intToShortArray(0x00000000, 100, new short[]{}, 0, 0));
    }

    @Test
    public void testIntToShortArray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{}, Conversion.intToShortArray(0x00000000, 0, new short[]{}, 100, 0));
    }

    @Test
    public void testIntToShortArray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xFFFF, (short) 0xFFFF, (short) 0xFFFF, (short) 0xFFFF}, Conversion.intToShortArray(0x12345678, 0, new short[]{-1, -1, -1, -1}, 0, 0));
    }

    @Test
    public void testIntToShortArray_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0x5678, (short) 0xFFFF, (short) 0xFFFF, (short) 0xFFFF}, Conversion.intToShortArray(0x12345678, 0, new short[]{-1, -1, -1, -1}, 0, 1));
    }

    @Test
    public void testIntToShortArray_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0x5678, (short) 0x1234, (short) 0xFFFF, (short) 0xFFFF}, Conversion.intToShortArray(0x12345678, 0, new short[]{-1, -1, -1, -1}, 0, 2));
    }

    @Test
    public void testIntToShortArray_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // short[]{(short) 0x5678, (short) 0x1234, (short) 0x5678, (short) 0xFFFF}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 0, 3));//rejected by assertion
        // assertArrayEquals(new
        // short[]{(short) 0x5678, (short) 0x1234, (short) 0x5678, (short) 0x1234}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 0, 4));
        // assertArrayEquals(new
        // short[]{(short) 0xFFFF, (short) 0x5678, (short) 0x1234, (short) 0x5678}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 1, 3));
        assertArrayEquals( new short[]{(short) 0xFFFF, (short) 0xFFFF, (short) 0x5678, (short) 0x1234}, Conversion.intToShortArray(0x12345678, 0, new short[]{-1, -1, -1, -1}, 2, 2));
    }

    @Test
    public void testIntToShortArray_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // short[]{(short) 0x5678, (short) 0x1234, (short) 0x5678, (short) 0xFFFF}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 0, 3));//rejected by assertion
        // assertArrayEquals(new
        // short[]{(short) 0x5678, (short) 0x1234, (short) 0x5678, (short) 0x1234}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 0, 4));
        // assertArrayEquals(new
        // short[]{(short) 0xFFFF, (short) 0x5678, (short) 0x1234, (short) 0x5678}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 1, 3));
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xFFFF, (short) 0xFFFF, (short) 0x5678, (short) 0xFFFF}, Conversion.intToShortArray(0x12345678, 0, new short[]{-1, -1, -1, -1}, 2, 1));
    }

    @Test
    public void testIntToShortArray_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // short[]{(short) 0x5678, (short) 0x1234, (short) 0x5678, (short) 0xFFFF}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 0, 3));//rejected by assertion
        // assertArrayEquals(new
        // short[]{(short) 0x5678, (short) 0x1234, (short) 0x5678, (short) 0x1234}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 0, 4));
        // assertArrayEquals(new
        // short[]{(short) 0xFFFF, (short) 0x5678, (short) 0x1234, (short) 0x5678}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 1, 3));
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xFFFF, (short) 0xFFFF, (short) 0xFFFF, (short) 0x5678}, Conversion.intToShortArray(0x12345678, 0, new short[]{-1, -1, -1, -1}, 3, 1));
    }

    @Test
    public void testIntToShortArray_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // short[]{(short) 0x5678, (short) 0x1234, (short) 0x5678, (short) 0xFFFF}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 0, 3));//rejected by assertion
        // assertArrayEquals(new
        // short[]{(short) 0x5678, (short) 0x1234, (short) 0x5678, (short) 0x1234}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 0, 4));
        // assertArrayEquals(new
        // short[]{(short) 0xFFFF, (short) 0x5678, (short) 0x1234, (short) 0x5678}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 1, 3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xFFFF, (short) 0xFFFF, (short) 0x2B3C, (short) 0xFFFF}, Conversion.intToShortArray(0x12345678, 1, new short[]{-1, -1, -1, -1}, 2, 1));
    }

    @Test
    public void testIntToShortArray_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // short[]{(short) 0x5678, (short) 0x1234, (short) 0x5678, (short) 0xFFFF}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 0, 3));//rejected by assertion
        // assertArrayEquals(new
        // short[]{(short) 0x5678, (short) 0x1234, (short) 0x5678, (short) 0x1234}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 0, 4));
        // assertArrayEquals(new
        // short[]{(short) 0xFFFF, (short) 0x5678, (short) 0x1234, (short) 0x5678}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 1, 3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xFFFF, (short) 0xFFFF, (short) 0x159E, (short) 0xFFFF}, Conversion.intToShortArray(0x12345678, 2, new short[]{-1, -1, -1, -1}, 2, 1));
    }

    @Test
    public void testIntToShortArray_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // short[]{(short) 0x5678, (short) 0x1234, (short) 0x5678, (short) 0xFFFF}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 0, 3));//rejected by assertion
        // assertArrayEquals(new
        // short[]{(short) 0x5678, (short) 0x1234, (short) 0x5678, (short) 0x1234}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 0, 4));
        // assertArrayEquals(new
        // short[]{(short) 0xFFFF, (short) 0x5678, (short) 0x1234, (short) 0x5678}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 1, 3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xFFFF, (short) 0xFFFF, (short) 0x8ACF, (short) 0xFFFF}, Conversion.intToShortArray(0x12345678, 3, new short[]{-1, -1, -1, -1}, 2, 1));
    }

    @Test
    public void testIntToShortArray_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // short[]{(short) 0x5678, (short) 0x1234, (short) 0x5678, (short) 0xFFFF}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 0, 3));//rejected by assertion
        // assertArrayEquals(new
        // short[]{(short) 0x5678, (short) 0x1234, (short) 0x5678, (short) 0x1234}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 0, 4));
        // assertArrayEquals(new
        // short[]{(short) 0xFFFF, (short) 0x5678, (short) 0x1234, (short) 0x5678}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 1, 3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new short[]{(short) 0xFFFF, (short) 0xFFFF, (short) 0x4567, (short) 0xFFFF}, Conversion.intToShortArray(0x12345678, 4, new short[]{-1, -1, -1, -1}, 2, 1));
    }

    @Test
    public void testIntToShortArray_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // short[]{(short) 0x5678, (short) 0x1234, (short) 0x5678, (short) 0xFFFF}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 0, 3));//rejected by assertion
        // assertArrayEquals(new
        // short[]{(short) 0x5678, (short) 0x1234, (short) 0x5678, (short) 0x1234}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 0, 4));
        // assertArrayEquals(new
        // short[]{(short) 0xFFFF, (short) 0x5678, (short) 0x1234, (short) 0x5678}, Conversion.intToShortArray(0x12345678,
        // 0, new short[]{-1, -1, -1, -1}, 1, 3));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // short[]{(short) 0xE6F7, (short) 0x4855, (short) 0x2B3C, (short) 0x091A}, Conversion.intToShortArray(0x12345678,
        // 1, new short[]{-1, -1, -1, -1}, 0, 4));//rejected by assertion
        // assertArrayEquals(new
        // short[]{(short) 0x2B3C}, Conversion.intToShortArray(0x12345678, 33, new
        // short[]{0}, 0, 1));//rejected by assertion
        assertArrayEquals( new short[]{(short) 0x091A}, Conversion.intToShortArray(0x12345678, 17, new short[]{0}, 0, 1));
    }

    @Test
    public void testLongToByteArray_1_oe() {
        assertArrayEquals( new byte[]{}, Conversion.longToByteArray(0x0000000000000000L, 0, new byte[]{}, 0, 0));
    }

    @Test
    public void testLongToByteArray_2_oe() {
        // removed other assertion
        assertArrayEquals( new byte[]{}, Conversion.longToByteArray(0x0000000000000000L, 100, new byte[]{}, 0, 0));
    }

    @Test
    public void testLongToByteArray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{}, Conversion.longToByteArray(0x0000000000000000L, 0, new byte[]{}, 100, 0));
    }

    @Test
    public void testLongToByteArray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 0));
    }

    @Test
    public void testLongToByteArray_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xEF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 1));
    }

    @Test
    public void testLongToByteArray_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xEF, (byte) 0xCD, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 2));
    }

    @Test
    public void testLongToByteArray_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x90, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 4));
    }

    @Test
    public void testLongToByteArray_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x90, (byte) 0x78, (byte) 0x56, (byte) 0x34, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 7));
    }

    @Test
    public void testLongToByteArray_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x90, (byte) 0x78, (byte) 0x56, (byte) 0x34, (byte) 0x12, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 8));
    }

    @Test
    public void testLongToByteArray_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xEF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 1));
    }

    @Test
    public void testLongToByteArray_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xEF, (byte) 0xCD, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 2));
    }

    @Test
    public void testLongToByteArray_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x90, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 4));
    }

    @Test
    public void testLongToByteArray_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x90, (byte) 0x78, (byte) 0x56, (byte) 0x34, (byte) 0xFF}, Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 7));
    }

    @Test
    public void testLongToByteArray_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x90, (byte) 0x78, (byte) 0x56, (byte) 0x34, (byte) 0x12}, Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 8));
    }

    @Test
    public void testLongToByteArray_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xF7, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.longToByteArray(0x1234567890ABCDEFL, 1, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 1));
    }

    @Test
    public void testLongToByteArray_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0x7B, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.longToByteArray(0x1234567890ABCDEFL, 2, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 1));
    }

    @Test
    public void testLongToByteArray_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0x00, (byte) 0xFF, (byte) 0x6F, (byte) 0x5E, (byte) 0x85, (byte) 0xC4, (byte) 0xB3, (byte) 0xA2, (byte) 0x91, (byte) 0x00}, Conversion.longToByteArray(0x1234567890ABCDEFL, 5, new byte[]{ -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 8));
    }

    @Test
    public void testLongToByteArray_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0xFF, (byte) 0x5E, (byte) 0x85, (byte) 0xC4, (byte) 0xB3, (byte) 0xA2, (byte) 0x91, (byte) 0x00, (byte) 0x00}, Conversion.longToByteArray(0x1234567890ABCDEFL, 13, new
        // byte[]{-1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 8));//rejected by assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0x00, (byte) 0xFF, (byte) 0x5E, (byte) 0x85, (byte) 0xC4, (byte) 0xB3, (byte) 0xA2, (byte) 0x91, (byte) 0x00, (byte) 0xFF}, Conversion.longToByteArray(0x1234567890ABCDEFL, 13, new byte[]{ -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 7));
    }

    @Test
    public void testIntToByteArray_1_oe() {
        assertArrayEquals( new byte[]{}, Conversion.intToByteArray(0x00000000, 0, new byte[]{}, 0, 0));
    }

    @Test
    public void testIntToByteArray_2_oe() {
        // removed other assertion
        assertArrayEquals( new byte[]{}, Conversion.intToByteArray(0x00000000, 100, new byte[]{}, 0, 0));
    }

    @Test
    public void testIntToByteArray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{}, Conversion.intToByteArray(0x00000000, 0, new byte[]{}, 100, 0));
    }

    @Test
    public void testIntToByteArray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.intToByteArray(0x90ABCDEF, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 0));
    }

    @Test
    public void testIntToByteArray_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xEF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.intToByteArray(0x90ABCDEF, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 1));
    }

    @Test
    public void testIntToByteArray_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xEF, (byte) 0xCD, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.intToByteArray(0x90ABCDEF, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 2));
    }

    @Test
    public void testIntToByteArray_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x90, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.intToByteArray(0x90ABCDEF, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 4));
    }

    @Test
    public void testIntToByteArray_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xEF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.intToByteArray(0x90ABCDEF, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 1));
    }

    @Test
    public void testIntToByteArray_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xEF, (byte) 0xCD, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.intToByteArray(0x90ABCDEF, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 2));
    }

    @Test
    public void testIntToByteArray_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x90, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.intToByteArray(0x90ABCDEF, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 4));
    }

    @Test
    public void testIntToByteArray_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xF7, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.intToByteArray(0x90ABCDEF, 1, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 1));
    }

    @Test
    public void testIntToByteArray_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0x7B, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.intToByteArray(0x90ABCDEF, 2, new byte[]{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 1));
    }

    @Test
    public void testIntToByteArray_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0x00, (byte) 0xFF, (byte) 0x6F, (byte) 0x5E, (byte) 0x85, (byte) 0xFC, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.intToByteArray(0x90ABCDEF, 5, new byte[]{ -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 4));
    }

    @Test
    public void testIntToByteArray_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0xFF, (byte) 0x5E, (byte) 0x85, (byte) 0xFC, (byte) 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.intToByteArray(0x90ABCDEF, 13, new
        // byte[]{-1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 4));//rejected by assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0x00, (byte) 0xFF, (byte) 0x5E, (byte) 0x85, (byte) 0xFC, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.intToByteArray(0x90ABCDEF, 13, new byte[]{ -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 3));
    }

    @Test
    public void testShortToByteArray_1_oe() {
        assertArrayEquals( new byte[]{}, Conversion.shortToByteArray((short) 0x0000, 0, new byte[]{}, 0, 0));
    }

    @Test
    public void testShortToByteArray_2_oe() {
        // removed other assertion
        assertArrayEquals( new byte[]{}, Conversion.shortToByteArray((short) 0x0000, 100, new byte[]{}, 0, 0));
    }

    @Test
    public void testShortToByteArray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{}, Conversion.shortToByteArray((short) 0x0000, 0, new byte[]{}, 100, 0));
    }

    @Test
    public void testShortToByteArray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.shortToByteArray((short) 0xCDEF, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1}, 0, 0));
    }

    @Test
    public void testShortToByteArray_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xEF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.shortToByteArray((short) 0xCDEF, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1}, 0, 1));
    }

    @Test
    public void testShortToByteArray_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xEF, (byte) 0xCD, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.shortToByteArray((short) 0xCDEF, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1}, 0, 2));
    }

    @Test
    public void testShortToByteArray_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xEF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.shortToByteArray((short) 0xCDEF, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1}, 3, 1));
    }

    @Test
    public void testShortToByteArray_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xEF, (byte) 0xCD, (byte) 0xFF, (byte) 0xFF}, Conversion.shortToByteArray((short) 0xCDEF, 0, new byte[]{ -1, -1, -1, -1, -1, -1, -1}, 3, 2));
    }

    @Test
    public void testShortToByteArray_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xF7, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.shortToByteArray((short) 0xCDEF, 1, new byte[]{ -1, -1, -1, -1, -1, -1, -1}, 0, 1));
    }

    @Test
    public void testShortToByteArray_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0x7B, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.shortToByteArray((short) 0xCDEF, 2, new byte[]{ -1, -1, -1, -1, -1, -1, -1}, 0, 1));
    }

    @Test
    public void testShortToByteArray_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0x00, (byte) 0xFF, (byte) 0x6F, (byte) 0xFE, (byte) 0xFF, (byte) 0xFF}, Conversion.shortToByteArray((short) 0xCDEF, 5, new byte[]{ -1, 0, -1, -1, -1, -1, -1}, 3, 2));
    }

    @Test
    public void testShortToByteArray_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new
        // byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0xFF, (byte) 0x5E, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.shortToByteArray((short) 0xCDEF, 13, new
        // byte[]{-1, 0, -1, -1, -1, -1, -1}, 3, 2));//rejected by assertion
        assertArrayEquals( new byte[]{ (byte) 0xFF, (byte) 0x00, (byte) 0xFF, (byte) 0xFE, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, Conversion.shortToByteArray((short) 0xCDEF, 13, new byte[]{ -1, 0, -1, -1, -1, -1, -1}, 3, 1));
    }

    @Test
    public void testLongToHex_1_oe() {
        assertEquals("", Conversion.longToHex(0x0000000000000000L, 0, "", 0, 0));
    }

    @Test
    public void testLongToHex_2_oe() {
        // removed other assertion
        assertEquals("", Conversion.longToHex(0x0000000000000000L, 100, "", 0, 0));
    }

    @Test
    public void testLongToHex_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("", Conversion.longToHex(0x0000000000000000L, 0, "", 100, 0));
    }

    @Test
    public void testLongToHex_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ffffffffffffffffffffffff",Conversion.longToHex(0x1234567890ABCDEFL,0,"ffffffffffffffffffffffff",0,0));
    }

    @Test
    public void testLongToHex_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3fffffffffffffffffffffff",Conversion.longToHex(0x1234567890ABCDE3L,0,"ffffffffffffffffffffffff",0,1));
    }

    @Test
    public void testLongToHex_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("feffffffffffffffffffffff",Conversion.longToHex(0x1234567890ABCDEFL,0,"ffffffffffffffffffffffff",0,2));
    }

    @Test
    public void testLongToHex_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fedcffffffffffffffffffff",Conversion.longToHex(0x1234567890ABCDEFL,0,"ffffffffffffffffffffffff",0,4));
    }

    @Test
    public void testLongToHex_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fedcba098765432fffffffff",Conversion.longToHex(0x1234567890ABCDEFL,0,"ffffffffffffffffffffffff",0,15));
    }

    @Test
    public void testLongToHex_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fedcba0987654321ffffffff",Conversion.longToHex(0x1234567890ABCDEFL,0,"ffffffffffffffffffffffff",0,16));
    }

    @Test
    public void testLongToHex_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fff3ffffffffffffffffffff",Conversion.longToHex(0x1234567890ABCDE3L,0,"ffffffffffffffffffffffff",3,1));
    }

    @Test
    public void testLongToHex_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ffffefffffffffffffffffff",Conversion.longToHex(0x1234567890ABCDEFL,0,"ffffffffffffffffffffffff",3,2));
    }

    @Test
    public void testLongToHex_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ffffedcfffffffffffffffff",Conversion.longToHex(0x1234567890ABCDEFL,0,"ffffffffffffffffffffffff",3,4));
    }

    @Test
    public void testLongToHex_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ffffedcba098765432ffffff",Conversion.longToHex(0x1234567890ABCDEFL,0,"ffffffffffffffffffffffff",3,15));
    }

    @Test
    public void testLongToHex_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ffffedcba0987654321fffff",Conversion.longToHex(0x1234567890ABCDEFL,0,"ffffffffffffffffffffffff",3,16));
    }

    @Test
    public void testLongToHex_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("7fffffffffffffffffffffff",Conversion.longToHex(0x1234567890ABCDEFL,1,"ffffffffffffffffffffffff",0,1));
    }

    @Test
    public void testLongToHex_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("bfffffffffffffffffffffff",Conversion.longToHex(0x1234567890ABCDEFL,2,"ffffffffffffffffffffffff",0,1));
    }

    @Test
    public void testLongToHex_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fffdb975121fca86420fffff",Conversion.longToHex(0x1234567890ABCDEFL,3,"ffffffffffffffffffffffff",3,16));
    }

    @Test
    public void testLongToHex_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals("ffffffffffffffffffffffff", Conversion.longToHex(0x1234567890ABCDEFL, 4, "ffffffffffffffffffffffff", 3, 16));//rejected
        // by assertion
        assertEquals("fffedcba0987654321ffffff",Conversion.longToHex(0x1234567890ABCDEFL,4,"ffffffffffffffffffffffff",3,15));
    }

    @Test
    public void testLongToHex_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals("ffffffffffffffffffffffff", Conversion.longToHex(0x1234567890ABCDEFL, 4, "ffffffffffffffffffffffff", 3, 16));//rejected
        // by assertion
        // removed other assertion
        assertEquals("fedcba0987654321",Conversion.longToHex(0x1234567890ABCDEFL,0,"",0,16));
    }

    @Test
    public void testLongToHex_20_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals("ffffffffffffffffffffffff", Conversion.longToHex(0x1234567890ABCDEFL, 4, "ffffffffffffffffffffffff", 3, 16));//rejected
        // by assertion
        // removed other assertion
        // removed other assertion
        try {
    Conversion.longToHex(0x1234567890ABCDEFL, 0, "", 1, 8);
    fail("StringIndexOutOfBoundsException");
} catch (StringIndexOutOfBoundsException e) {
}
    }

    @Test
    public void testIntToHex_1_oe() {
        assertEquals("", Conversion.intToHex(0x00000000, 0, "", 0, 0));
    }

    @Test
    public void testIntToHex_2_oe() {
        // removed other assertion
        assertEquals("", Conversion.intToHex(0x00000000, 100, "", 0, 0));
    }

    @Test
    public void testIntToHex_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("", Conversion.intToHex(0x00000000, 0, "", 100, 0));
    }

    @Test
    public void testIntToHex_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ffffffffffffffffffffffff",Conversion.intToHex(0x90ABCDEF,0,"ffffffffffffffffffffffff",0,0));
    }

    @Test
    public void testIntToHex_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3fffffffffffffffffffffff",Conversion.intToHex(0x90ABCDE3,0,"ffffffffffffffffffffffff",0,1));
    }

    @Test
    public void testIntToHex_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("feffffffffffffffffffffff",Conversion.intToHex(0x90ABCDEF,0,"ffffffffffffffffffffffff",0,2));
    }

    @Test
    public void testIntToHex_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fedcffffffffffffffffffff",Conversion.intToHex(0x90ABCDEF,0,"ffffffffffffffffffffffff",0,4));
    }

    @Test
    public void testIntToHex_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fedcba0fffffffffffffffff",Conversion.intToHex(0x90ABCDEF,0,"ffffffffffffffffffffffff",0,7));
    }

    @Test
    public void testIntToHex_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fedcba09ffffffffffffffff",Conversion.intToHex(0x90ABCDEF,0,"ffffffffffffffffffffffff",0,8));
    }

    @Test
    public void testIntToHex_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fff3ffffffffffffffffffff",Conversion.intToHex(0x90ABCDE3,0,"ffffffffffffffffffffffff",3,1));
    }

    @Test
    public void testIntToHex_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ffffefffffffffffffffffff",Conversion.intToHex(0x90ABCDEF,0,"ffffffffffffffffffffffff",3,2));
    }

    @Test
    public void testIntToHex_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ffffedcfffffffffffffffff",Conversion.intToHex(0x90ABCDEF,0,"ffffffffffffffffffffffff",3,4));
    }

    @Test
    public void testIntToHex_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ffffedcba0ffffffffffffff",Conversion.intToHex(0x90ABCDEF,0,"ffffffffffffffffffffffff",3,7));
    }

    @Test
    public void testIntToHex_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ffffedcba09fffffffffffff",Conversion.intToHex(0x90ABCDEF,0,"ffffffffffffffffffffffff",3,8));
    }

    @Test
    public void testIntToHex_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("7fffffffffffffffffffffff",Conversion.intToHex(0x90ABCDEF,1,"ffffffffffffffffffffffff",0,1));
    }

    @Test
    public void testIntToHex_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("bfffffffffffffffffffffff",Conversion.intToHex(0x90ABCDEF,2,"ffffffffffffffffffffffff",0,1));
    }

    @Test
    public void testIntToHex_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fffdb97512ffffffffffffff",Conversion.intToHex(0x90ABCDEF,3,"ffffffffffffffffffffffff",3,8));
    }

    @Test
    public void testIntToHex_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals("ffffffffffffffffffffffff", Conversion.intToHex(0x90ABCDEF,
        // 4, "ffffffffffffffffffffffff", 3, 8));//rejected by assertion
        assertEquals("fffedcba09ffffffffffffff",Conversion.intToHex(0x90ABCDEF,4,"ffffffffffffffffffffffff",3,7));
    }

    @Test
    public void testIntToHex_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals("ffffffffffffffffffffffff", Conversion.intToHex(0x90ABCDEF,
        // 4, "ffffffffffffffffffffffff", 3, 8));//rejected by assertion
        // removed other assertion
        assertEquals("fedcba09", Conversion.intToHex(0x90ABCDEF, 0, "", 0, 8));
    }

    @Test
    public void testIntToHex_20_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals("ffffffffffffffffffffffff", Conversion.intToHex(0x90ABCDEF,
        // 4, "ffffffffffffffffffffffff", 3, 8));//rejected by assertion
        // removed other assertion
        // removed other assertion
        try {
    Conversion.intToHex(0x90ABCDEF, 0, "", 1, 8);
    fail("StringIndexOutOfBoundsException");
} catch (StringIndexOutOfBoundsException e) {
}
    }

    @Test
    public void testShortToHex_1_oe() {
        assertEquals("", Conversion.shortToHex((short) 0x0000, 0, "", 0, 0));
    }

    @Test
    public void testShortToHex_2_oe() {
        // removed other assertion
        assertEquals("", Conversion.shortToHex((short) 0x0000, 100, "", 0, 0));
    }

    @Test
    public void testShortToHex_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("", Conversion.shortToHex((short) 0x0000, 0, "", 100, 0));
    }

    @Test
    public void testShortToHex_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ffffffffffffffffffffffff",Conversion.shortToHex((short)0xCDEF,0,"ffffffffffffffffffffffff",0,0));
    }

    @Test
    public void testShortToHex_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3fffffffffffffffffffffff",Conversion.shortToHex((short)0xCDE3,0,"ffffffffffffffffffffffff",0,1));
    }

    @Test
    public void testShortToHex_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("feffffffffffffffffffffff",Conversion.shortToHex((short)0xCDEF,0,"ffffffffffffffffffffffff",0,2));
    }

    @Test
    public void testShortToHex_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fedfffffffffffffffffffff",Conversion.shortToHex((short)0xCDEF,0,"ffffffffffffffffffffffff",0,3));
    }

    @Test
    public void testShortToHex_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fedcffffffffffffffffffff",Conversion.shortToHex((short)0xCDEF,0,"ffffffffffffffffffffffff",0,4));
    }

    @Test
    public void testShortToHex_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fff3ffffffffffffffffffff",Conversion.shortToHex((short)0xCDE3,0,"ffffffffffffffffffffffff",3,1));
    }

    @Test
    public void testShortToHex_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ffffefffffffffffffffffff",Conversion.shortToHex((short)0xCDEF,0,"ffffffffffffffffffffffff",3,2));
    }

    @Test
    public void testShortToHex_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("7fffffffffffffffffffffff",Conversion.shortToHex((short)0xCDEF,1,"ffffffffffffffffffffffff",0,1));
    }

    @Test
    public void testShortToHex_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("bfffffffffffffffffffffff",Conversion.shortToHex((short)0xCDEF,2,"ffffffffffffffffffffffff",0,1));
    }

    @Test
    public void testShortToHex_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fffdb9ffffffffffffffffff",Conversion.shortToHex((short)0xCDEF,3,"ffffffffffffffffffffffff",3,4));
    }

    @Test
    public void testShortToHex_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals("ffffffffffffffffffffffff", Conversion.shortToHex((short) 0xCDEF,
        // 4, "ffffffffffffffffffffffff", 3, 4));//rejected by assertion
        assertEquals("fffedcffffffffffffffffff",Conversion.shortToHex((short)0xCDEF,4,"ffffffffffffffffffffffff",3,3));
    }

    @Test
    public void testShortToHex_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals("ffffffffffffffffffffffff", Conversion.shortToHex((short) 0xCDEF,
        // 4, "ffffffffffffffffffffffff", 3, 4));//rejected by assertion
        // removed other assertion
        assertEquals("fedc", Conversion.shortToHex((short) 0xCDEF, 0, "", 0, 4));
    }

    @Test
    public void testShortToHex_16_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals("ffffffffffffffffffffffff", Conversion.shortToHex((short) 0xCDEF,
        // 4, "ffffffffffffffffffffffff", 3, 4));//rejected by assertion
        // removed other assertion
        // removed other assertion
        try {
    Conversion.shortToHex((short) 0xCDEF, 0, "", 1, 4);
    fail("StringIndexOutOfBoundsException");
} catch (StringIndexOutOfBoundsException e) {
}
    }

    @Test
    public void testByteToHex_1_oe() {
        assertEquals("", Conversion.byteToHex((byte) 0x00, 0, "", 0, 0));
    }

    @Test
    public void testByteToHex_2_oe() {
        // removed other assertion
        assertEquals("", Conversion.byteToHex((byte) 0x00, 100, "", 0, 0));
    }

    @Test
    public void testByteToHex_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("", Conversion.byteToHex((byte) 0x00, 0, "", 100, 0));
    }

    @Test
    public void testByteToHex_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("00000", Conversion.byteToHex((byte) 0xEF, 0, "00000", 0, 0));
    }

    @Test
    public void testByteToHex_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("f0000", Conversion.byteToHex((byte) 0xEF, 0, "00000", 0, 1));
    }

    @Test
    public void testByteToHex_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fe000", Conversion.byteToHex((byte) 0xEF, 0, "00000", 0, 2));
    }

    @Test
    public void testByteToHex_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("000f0", Conversion.byteToHex((byte) 0xEF, 0, "00000", 3, 1));
    }

    @Test
    public void testByteToHex_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("000fe", Conversion.byteToHex((byte) 0xEF, 0, "00000", 3, 2));
    }

    @Test
    public void testByteToHex_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("70000", Conversion.byteToHex((byte) 0xEF, 1, "00000", 0, 1));
    }

    @Test
    public void testByteToHex_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("b0000", Conversion.byteToHex((byte) 0xEF, 2, "00000", 0, 1));
    }

    @Test
    public void testByteToHex_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("000df", Conversion.byteToHex((byte) 0xEF, 3, "00000", 3, 2));
    }

    @Test
    public void testByteToHex_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals("00000", Conversion.byteToHex((byte) 0xEF, 4, "00000", 3, 2));//rejected by
        // assertion
        assertEquals("000e0", Conversion.byteToHex((byte) 0xEF, 4, "00000", 3, 1));
    }

    @Test
    public void testByteToHex_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals("00000", Conversion.byteToHex((byte) 0xEF, 4, "00000", 3, 2));//rejected by
        // assertion
        // removed other assertion
        assertEquals("fe", Conversion.byteToHex((byte) 0xEF, 0, "", 0, 2));
    }

    @Test
    public void testByteToHex_14_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertEquals("00000", Conversion.byteToHex((byte) 0xEF, 4, "00000", 3, 2));//rejected by
        // assertion
        // removed other assertion
        // removed other assertion
        try {
    Conversion.byteToHex((byte) 0xEF, 0, "", 1, 2);
    fail("StringIndexOutOfBoundsException");
} catch (StringIndexOutOfBoundsException e) {
}
    }

    @Test
    public void testLongToBinary_1_oe() {
        assertArrayEquals( new boolean[]{}, Conversion.longToBinary(0x0000000000000000L, 0, new boolean[]{}, 0, 0));
    }

    @Test
    public void testLongToBinary_2_oe() {
        // removed other assertion
        assertArrayEquals( new boolean[]{}, Conversion.longToBinary(0x0000000000000000L, 100, new boolean[]{}, 0, 0));
    }

    @Test
    public void testLongToBinary_3_oe() {
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{}, Conversion.longToBinary(0x0000000000000000L, 0, new boolean[]{}, 100, 0));
    }

    @Test
    public void testLongToBinary_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[69],Conversion.longToBinary(0x1234567890ABCDEFL,0,new boolean[69],0,0));
    }

    @Test
    public void testLongToBinary_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertArrayEquals( new boolean[]{ true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, Conversion.longToBinary(0x1234567890ABCDEFL, 0, new boolean[69], 0, 1));
    }

    @Test
    public void testLongToBinary_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals( new boolean[]{ true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, Conversion.longToBinary(0x1234567890ABCDEFL, 0, new boolean[69], 0, 2));
    }

    @Test
    public void testLongToBinary_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, Conversion.longToBinary(0x1234567890ABCDEFL, 0, new boolean[69], 0, 3));
    }

    @Test
    public void testLongToBinary_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ true, true, true, true, false, true, true, true, true, false, true, true, false, false, true, true, true, true, false, true, false, true, false, true, false, false, false, false, true, false, false, true, false, false, false, true, true, true, true, false, false, true, true, false, true, false, true, false, false, false, true, false, true, true, false, false, false, true, false, false, true, false, false, false, false, false, false, false, false}, Conversion.longToBinary(0x1234567890ABCDEFL, 0, new boolean[69], 0, 63));
    }

    @Test
    public void testLongToBinary_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ true, true, true, true, false, true, true, true, true, false, true, true, false, false, true, true, true, true, false, true, false, true, false, true, false, false, false, false, true, false, false, true, false, false, false, true, true, true, true, false, false, true, true, false, true, false, true, false, false, false, true, false, true, true, false, false, false, true, false, false, true, false, false, false, false, false, false, false, false}, Conversion.longToBinary(0x1234567890ABCDEFL, 0, new boolean[69], 0, 64));
    }

    @Test
    public void testLongToBinary_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, Conversion.longToBinary(0x1234567890ABCDEFL, 0, new boolean[69], 2, 1));
    }

    @Test
    public void testLongToBinary_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ false, false, true, true, true, true, false, true, true, true, true, false, true, true, false, false, true, true, true, true, false, true, false, true, false, true, false, false, false, false, true, false, false, true, false, false, false, true, true, true, true, false, false, true, true, false, true, false, true, false, false, false, true, false, true, true, false, false, false, true, false, false, true, false, false, false, false, false, false}, Conversion.longToBinary(0x1234567890ABCDEFL, 0, new boolean[69], 2, 64));
    }

    @Test
    public void testLongToBinary_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ true, true, true, false, true, true, true, true, false, true, true, false, false, true, true, true, true, false, true, false, true, false, true, false, false, false, false, true, false, false, true, false, false, false, true, true, true, true, false, false, true, true, false, true, false, true, false, false, false, true, false, true, true, false, false, false, true, false, false, true, false, false, false, false, false, false, false, false, false}, Conversion.longToBinary(0x1234567890ABCDEFL, 1, new boolean[69], 0, 63));
    }

    @Test
    public void testLongToBinary_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ true, true, false, true, true, true, true, false, true, true, false, false, true, true, true, true, false, true, false, true, false, true, false, false, false, false, true, false, false, true, false, false, false, true, true, true, true, false, false, true, true, false, true, false, true, false, false, false, true, false, true, true, false, false, false, true, false, false, true, false, false, false, false, false, false, false, false, false, false}, Conversion.longToBinary(0x1234567890ABCDEFL, 2, new boolean[69], 0, 62));
    }

    @Test
    public void testLongToBinary_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // assertArrayEquals(new boolean[]{false, false, false, true, true, false, true, true,
        // true, true, false, true, true, false, false, true, true, true, true, false, true,
        // false, true, false, true, false, false, false, false, true, false, false, true,
        // false, false, false, true, true, true, true, false, false, true, true, false, true,
        // false, true, false, false, false, true, false, true, true, false, false, false, true,
        // false, false, true, false, false, false
        // , false, false, false, false}, Conversion.longToBinary(0x1234567890ABCDEFL, 2, new
        // boolean[69], 3, 63));//rejected by assertion
        assertArrayEquals( new boolean[]{ false, false, false, true, true, false, true, true, true, true, false, true, true, false, false, true, true, true, true, false, true, false, true, false, true, false, false, false, false, true, false, false, true, false, false, false, true, true, true, true, false, false, true, true, false, true, false, true, false, false, false, true, false, true, true, false, false, false, true, false, false, true, false, false, false, false, false, false, false}, Conversion.longToBinary(0x1234567890ABCDEFL, 2, new boolean[69], 3, 62));
    }

    @Test
    public void testIntToBinary_1_oe() {
        assertArrayEquals( new boolean[]{}, Conversion.intToBinary(0x00000000, 0, new boolean[]{}, 0, 0));
    }

    @Test
    public void testIntToBinary_2_oe() {
        // removed other assertion
        assertArrayEquals( new boolean[]{}, Conversion.intToBinary(0x00000000, 100, new boolean[]{}, 0, 0));
    }

    @Test
    public void testIntToBinary_3_oe() {
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{}, Conversion.intToBinary(0x00000000, 0, new boolean[]{}, 100, 0));
    }

    @Test
    public void testIntToBinary_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[69],Conversion.intToBinary(0x90ABCDEF,0,new boolean[69],0,0));
    }

    @Test
    public void testIntToBinary_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[]{ true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 0, 1));
    }

    @Test
    public void testIntToBinary_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[]{ true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 0, 2));
    }

    @Test
    public void testIntToBinary_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[]{ true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 0, 3));
    }

    @Test
    public void testIntToBinary_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ true, true, true, true, false, true, true, true, true, false, true, true, false, false, true, true, true, true, false, true, false, true, false, true, false, false, false, false, true, false, false, false, false, false, false, false, false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 0, 31));
    }

    @Test
    public void testIntToBinary_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ true, true, true, true, false, true, true, true, true, false, true, true, false, false, true, true, true, true, false, true, false, true, false, true, false, false, false, false, true, false, false, true, false, false, false, false, false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 0, 32));
    }

    @Test
    public void testIntToBinary_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[]{ false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 2, 1));
    }

    @Test
    public void testIntToBinary_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ false, false, true, true, true, true, false, true, true, true, true, false, true, true, false, false, true, true, true, true, false, true, false, true, false, true, false, false, false, false, true, false, false, true, false, false, false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 2, 32));
    }

    @Test
    public void testIntToBinary_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ true, true, true, false, true, true, true, true, false, true, true, false, false, true, true, true, true, false, true, false, true, false, true, false, false, false, false, true, false, false, true, false, false, false, false, false, false}, Conversion.intToBinary(0x90ABCDEF, 1, new boolean[37], 0, 31));
    }

    @Test
    public void testIntToBinary_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ true, true, false, true, true, true, true, false, true, true, false, false, true, true, true, true, false, true, false, true, false, true, false, false, false, false, true, false, false, true, false, false, false, false, false, false, false}, Conversion.intToBinary(0x90ABCDEF, 2, new boolean[37], 0, 30));
    }

    @Test
    public void testIntToBinary_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new boolean[]{false, false, false, true, true, false, true,
        // true,
        // true, true, false, true, true, false, false, true, true, true, true, false, true,
        // false, true, false, true, false, false, false, false, true, false, false, false,
        // false, false, false, false}, Conversion.intToBinary(0x90ABCDEF, 2, new boolean[37],
        // 3, 31));//rejected by assertion
        assertArrayEquals( new boolean[]{ false, false, false, true, true, false, true, true, true, true, false, true, true, false, false, true, true, true, true, false, true, false, true, false, true, false, false, false, false, true, false, false, true, false, false, false, false}, Conversion.intToBinary(0x90ABCDEF, 2, new boolean[37], 3, 30));
    }

    @Test
    public void testShortToBinary_1_oe() {
        assertArrayEquals( new boolean[]{}, Conversion.shortToBinary((short) 0x0000, 0, new boolean[]{}, 0, 0));
    }

    @Test
    public void testShortToBinary_2_oe() {
        // removed other assertion
        assertArrayEquals( new boolean[]{}, Conversion.shortToBinary((short) 0x0000, 100, new boolean[]{}, 0, 0));
    }

    @Test
    public void testShortToBinary_3_oe() {
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{}, Conversion.shortToBinary((short) 0x0000, 0, new boolean[]{}, 100, 0));
    }

    @Test
    public void testShortToBinary_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[69],Conversion.shortToBinary((short)0xCDEF,0,new boolean[69],0,0));
    }

    @Test
    public void testShortToBinary_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, Conversion.shortToBinary((short) 0xCDEF, 0, new boolean[21], 0, 1));
    }

    @Test
    public void testShortToBinary_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, Conversion.shortToBinary((short) 0xCDEF, 0, new boolean[21], 0, 2));
    }

    @Test
    public void testShortToBinary_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, Conversion.shortToBinary((short) 0xCDEF, 0, new boolean[21], 0, 3));
    }

    @Test
    public void testShortToBinary_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ true, true, true, true, false, true, true, true, true, false, true, true, false, false, true, false, false, false, false, false, false}, Conversion.shortToBinary((short) 0xCDEF, 0, new boolean[21], 0, 15));
    }

    @Test
    public void testShortToBinary_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ true, true, true, true, false, true, true, true, true, false, true, true, false, false, true, true, false, false, false, false, false}, Conversion.shortToBinary((short) 0xCDEF, 0, new boolean[21], 0, 16));
    }

    @Test
    public void testShortToBinary_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, Conversion.shortToBinary((short) 0xCDEF, 0, new boolean[21], 2, 1));
    }

    @Test
    public void testShortToBinary_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ false, false, true, true, true, true, false, true, true, true, true, false, true, true, false, false, true, true, false, false, false}, Conversion.shortToBinary((short) 0xCDEF, 0, new boolean[21], 2, 16));
    }

    @Test
    public void testShortToBinary_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ true, true, true, false, true, true, true, true, false, true, true, false, false, true, true, false, false, false, false, false, false}, Conversion.shortToBinary((short) 0xCDEF, 1, new boolean[21], 0, 15));
    }

    @Test
    public void testShortToBinary_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{ true, true, false, true, true, true, true, false, true, true, false, false, true, true, false, false, false, false, false, false, false}, Conversion.shortToBinary((short) 0xCDEF, 2, new boolean[21], 0, 14));
    }

    @Test
    public void testShortToBinary_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new boolean[]{false, false, false, true, true, false, true, true,
        // true, true, false, true, true, false, false, true, false, false, false, false,
        // false}, Conversion.shortToBinary((short) 0xCDEF, 2, new boolean[21],
        // 3, 15));//rejected by
        // assertion
        assertArrayEquals( new boolean[]{ false, false, false, true, true, false, true, true, true, true, false, true, true, false, false, true, true, false, false, false, false}, Conversion.shortToBinary((short) 0xCDEF, 2, new boolean[21], 3, 14));
    }

    @Test
    public void testByteToBinary_1_oe() {
        assertArrayEquals( new boolean[]{}, Conversion.byteToBinary((byte) 0x00, 0, new boolean[]{}, 0, 0));
    }

    @Test
    public void testByteToBinary_2_oe() {
        // removed other assertion
        assertArrayEquals( new boolean[]{}, Conversion.byteToBinary((byte) 0x00, 100, new boolean[]{}, 0, 0));
    }

    @Test
    public void testByteToBinary_3_oe() {
        // removed other assertion
        // removed other assertion
        assertArrayEquals( new boolean[]{}, Conversion.byteToBinary((byte) 0x00, 0, new boolean[]{}, 100, 0));
    }

    @Test
    public void testByteToBinary_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[69],Conversion.byteToBinary((byte)0xEF,0,new boolean[69],0,0));
    }

    @Test
    public void testByteToBinary_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[]{ true, false, false, false, false, false, false, false, false, false, false, false, false}, Conversion.byteToBinary((byte) 0x95, 0, new boolean[13], 0, 1));
    }

    @Test
    public void testByteToBinary_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[]{ true, false, false, false, false, false, false, false, false, false, false, false, false}, Conversion.byteToBinary((byte) 0x95, 0, new boolean[13], 0, 2));
    }

    @Test
    public void testByteToBinary_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[]{ true, false, true, false, false, false, false, false, false, false, false, false, false}, Conversion.byteToBinary((byte) 0x95, 0, new boolean[13], 0, 3));
    }

    @Test
    public void testByteToBinary_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[]{ true, false, true, false, true, false, false, false, false, false, false, false, false}, Conversion.byteToBinary((byte) 0x95, 0, new boolean[13], 0, 7));
    }

    @Test
    public void testByteToBinary_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[]{ true, false, true, false, true, false, false, true, false, false, false, false, false}, Conversion.byteToBinary((byte) 0x95, 0, new boolean[13], 0, 8));
    }

    @Test
    public void testByteToBinary_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[]{ false, false, true, false, false, false, false, false, false, false, false, false, false}, Conversion.byteToBinary((byte) 0x95, 0, new boolean[13], 2, 1));
    }

    @Test
    public void testByteToBinary_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[]{ false, false, true, false, true, false, true, false, false, true, false, false, false}, Conversion.byteToBinary((byte) 0x95, 0, new boolean[13], 2, 8));
    }

    @Test
    public void testByteToBinary_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[]{ false, true, false, true, false, false, true, false, false, false, false, false, false}, Conversion.byteToBinary((byte) 0x95, 1, new boolean[13], 0, 7));
    }

    @Test
    public void testByteToBinary_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[]{ true, false, true, false, false, true, false, false, false, false, false, false, false}, Conversion.byteToBinary((byte) 0x95, 2, new boolean[13], 0, 6));
    }

    @Test
    public void testByteToBinary_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // assertArrayEquals(new boolean[]{false, false, false, true, true, false, true, true,
        // false, false, false, false, false}, Conversion.byteToBinary((byte) 0x95, 2, new
        // boolean[13], 3, 7));//rejected by assertion
        assertArrayEquals(new boolean[]{ false, false, false, true, false, true, false, false, true, false, false, false, false}, Conversion.byteToBinary((byte) 0x95, 2, new boolean[13], 3, 6));
    }

    @Test
    public void testUuidToByteArray_1_oe() {
        assertArrayEquals(new byte[]{ (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff}, Conversion.uuidToByteArray(new UUID( 0xFFFFFFFFFFFFFFFFL, 0xFFFFFFFFFFFFFFFFL), new byte[16], 0, 16));
    }

    @Test
    public void testUuidToByteArray_2_oe() {
        // removed other assertion
        assertArrayEquals(new byte[]{ (byte) 0x88, (byte) 0x99, (byte) 0xaa, (byte) 0xbb, (byte) 0xcc, (byte) 0xdd, (byte) 0xee, (byte) 0xff, (byte) 0x00, (byte) 0x11, (byte) 0x22, (byte) 0x33, (byte) 0x44, (byte) 0x55, (byte) 0x66, (byte) 0x77}, Conversion.uuidToByteArray(new UUID( 0xFFEEDDCCBBAA9988L, 0x7766554433221100L), new byte[16], 0, 16));
    }

    @Test
    public void testUuidToByteArray_3_oe() {
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new byte[]{ (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x88, (byte) 0x99, (byte) 0xaa, (byte) 0xbb, (byte) 0xcc, (byte) 0xdd, (byte) 0xee, (byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00}, Conversion.uuidToByteArray(new UUID( 0xFFEEDDCCBBAA9988L, 0x7766554433221100L), new byte[16], 4, 8));
    }

    @Test
    public void testUuidToByteArray_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new byte[]{ (byte) 0x00, (byte) 0x00, (byte) 0x88, (byte) 0x99, (byte) 0xaa, (byte) 0xbb, (byte) 0xcc, (byte) 0xdd, (byte) 0xee, (byte) 0xff, (byte) 0x00, (byte) 0x11, (byte) 0x22, (byte) 0x33, (byte) 0x00, (byte) 0x00}, Conversion.uuidToByteArray(new UUID( 0xFFEEDDCCBBAA9988L, 0x7766554433221100L), new byte[16], 2, 12));
    }

    @Test
    public void testByteArrayToUuid_1_oe() {
        assertEquals( new UUID(0xFFFFFFFFFFFFFFFFL, 0xFFFFFFFFFFFFFFFFL), Conversion.byteArrayToUuid(new byte[]{ (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff}, 0));
    }

    @Test
    public void testByteArrayToUuid_2_oe() {
        // removed other assertion
        assertEquals( new UUID(0xFFEEDDCCBBAA9988L, 0x7766554433221100L), Conversion.byteArrayToUuid(new byte[]{ (byte) 0x88, (byte) 0x99, (byte) 0xaa, (byte) 0xbb, (byte) 0xcc, (byte) 0xdd, (byte) 0xee, (byte) 0xff, (byte) 0x00, (byte) 0x11, (byte) 0x22, (byte) 0x33, (byte) 0x44, (byte) 0x55, (byte) 0x66, (byte) 0x77}, 0));
    }

    @Test
    public void testByteArrayToUuid_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals( new UUID(0xFFEEDDCCBBAA9988L, 0x7766554433221100L), Conversion.byteArrayToUuid(new byte[]{ 0, 0, (byte) 0x88, (byte) 0x99, (byte) 0xaa, (byte) 0xbb, (byte) 0xcc, (byte) 0xdd, (byte) 0xee, (byte) 0xff, (byte) 0x00, (byte) 0x11, (byte) 0x22, (byte) 0x33, (byte) 0x44, (byte) 0x55, (byte) 0x66, (byte) 0x77}, 2));
    }

}
