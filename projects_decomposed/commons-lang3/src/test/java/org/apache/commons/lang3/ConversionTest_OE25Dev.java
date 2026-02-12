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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("StringIndexOutOfBoundsException");
} catch (StringIndexOutOfBoundsException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("StringIndexOutOfBoundsException");
} catch (StringIndexOutOfBoundsException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("StringIndexOutOfBoundsException");
} catch (StringIndexOutOfBoundsException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("StringIndexOutOfBoundsException");
} catch (StringIndexOutOfBoundsException e) {
}
    }

}
