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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Class to test BitField functionality
 */
public class BitFieldTest_OE25Dev  {

    private static final BitField bf_multi  = new BitField(0x3F80);
    private static final BitField bf_single = new BitField(0x4000);
    private static final BitField bf_zero = new BitField(0);

    /**
     * test the getValue() method
     */

    /**
     * test the getShortValue() method
     */

    /**
     * test the getRawValue() method
     */

    /**
     * test the getShortRawValue() method
     */

    /**
     * test the isSet() method
     */

    /**
     * test the isAllSet() method
     */

    /**
     * test the setValue() method
     */

    /**
     * test the setShortValue() method
     */

    /**
     * test the clear() method
     */

    /**
     * test the clearShort() method
     */

    /**
     * test the set() method
     */

    /**
     * test the setShort() method
     */

    /**
     * test the setBoolean() method
     */

    /**
     * test the setShortBoolean() method
     */

    @Test
    public void testGetValue_1_oe() {
        assertEquals(bf_multi.getValue(-1), 127);
    }

    @Test
    public void testGetValue_2_oe() {
        // removed other assertion
        assertEquals(bf_multi.getValue(0), 0);
    }

    @Test
    public void testGetValue_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(bf_single.getValue(-1), 1);
    }

    @Test
    public void testGetValue_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_single.getValue(0), 0);
    }

    @Test
    public void testGetValue_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_zero.getValue(-1), 0);
    }

    @Test
    public void testGetValue_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_zero.getValue(0), 0);
    }

    @Test
    public void testGetShortValue_1_oe() {
        assertEquals(bf_multi.getShortValue((short) - 1), (short) 127);
    }

    @Test
    public void testGetShortValue_2_oe() {
        // removed other assertion
        assertEquals(bf_multi.getShortValue((short) 0), (short) 0);
    }

    @Test
    public void testGetShortValue_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(bf_single.getShortValue((short) - 1), (short) 1);
    }

    @Test
    public void testGetShortValue_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_single.getShortValue((short) 0), (short) 0);
    }

    @Test
    public void testGetShortValue_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_zero.getShortValue((short) -1), (short) 0);
    }

    @Test
    public void testGetShortValue_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_zero.getShortValue((short) 0), (short) 0);
    }

    @Test
    public void testGetRawValue_1_oe() {
        assertEquals(bf_multi.getRawValue(-1), 0x3F80);
    }

    @Test
    public void testGetRawValue_2_oe() {
        // removed other assertion
        assertEquals(bf_multi.getRawValue(0), 0);
    }

    @Test
    public void testGetRawValue_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(bf_single.getRawValue(-1), 0x4000);
    }

    @Test
    public void testGetRawValue_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_single.getRawValue(0), 0);
    }

    @Test
    public void testGetRawValue_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_zero.getRawValue(-1), 0);
    }

    @Test
    public void testGetRawValue_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_zero.getRawValue(0), 0);
    }

    @Test
    public void testGetShortRawValue_1_oe() {
        assertEquals(bf_multi.getShortRawValue((short) - 1), (short) 0x3F80);
    }

    @Test
    public void testGetShortRawValue_2_oe() {
        // removed other assertion
        assertEquals(bf_multi.getShortRawValue((short) 0), (short) 0);
    }

    @Test
    public void testGetShortRawValue_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(bf_single.getShortRawValue((short) - 1), (short) 0x4000);
    }

    @Test
    public void testGetShortRawValue_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_single.getShortRawValue((short) 0), (short) 0);
    }

    @Test
    public void testGetShortRawValue_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_zero.getShortRawValue((short) -1), (short) 0);
    }

    @Test
    public void testGetShortRawValue_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_zero.getShortRawValue((short) 0), (short) 0);
    }

    @Test
    public void testIsSet_1_oe() {
        assertFalse(bf_multi.isSet(0));
    }

    @Test
    public void testIsSet_2_oe() {
        // removed other assertion
        assertFalse(bf_zero.isSet(0));
    }

    @Test
    public void testIsSet_3_oe() {
        // removed other assertion
        // removed other assertion
        for (int j = 0x80; j <= 0x3F80; j += 0x80) {
            assertTrue(bf_multi.isSet(j));
    }
    }

    @Test
    public void testIsSet_4_oe() {
        // removed other assertion
        // removed other assertion
        for (int j = 0x80; j <= 0x3F80; j += 0x80) {
            // removed other assertion
        }
        for (int j = 0x80; j <= 0x3F80; j += 0x80) {
            assertFalse(bf_zero.isSet(j));
    }
    }

    @Test
    public void testIsSet_5_oe() {
        // removed other assertion
        // removed other assertion
        for (int j = 0x80; j <= 0x3F80; j += 0x80) {
            // removed other assertion
        }
        for (int j = 0x80; j <= 0x3F80; j += 0x80) {
            // removed other assertion
        }
        assertFalse(bf_single.isSet(0));
    }

    @Test
    public void testIsSet_6_oe() {
        // removed other assertion
        // removed other assertion
        for (int j = 0x80; j <= 0x3F80; j += 0x80) {
            // removed other assertion
        }
        for (int j = 0x80; j <= 0x3F80; j += 0x80) {
            // removed other assertion
        }
        // removed other assertion
        assertTrue(bf_single.isSet(0x4000));
    }

    @Test
    public void testIsAllSet_1_oe() {
        for (int j = 0; j < 0x3F80; j += 0x80) {
            assertFalse(bf_multi.isAllSet(j));
    }
    }

    @Test
    public void testIsAllSet_2_oe() {
        for (int j = 0; j < 0x3F80; j += 0x80) {
            // removed other assertion
            assertTrue(bf_zero.isAllSet(j));
    }
    }

    @Test
    public void testIsAllSet_3_oe() {
        for (int j = 0; j < 0x3F80; j += 0x80) {
            // removed other assertion
            // removed other assertion
        }
        assertTrue(bf_multi.isAllSet(0x3F80));
    }

    @Test
    public void testIsAllSet_4_oe() {
        for (int j = 0; j < 0x3F80; j += 0x80) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        assertFalse(bf_single.isAllSet(0));
    }

    @Test
    public void testIsAllSet_5_oe() {
        for (int j = 0; j < 0x3F80; j += 0x80) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        assertTrue(bf_single.isAllSet(0x4000));
    }

    @Test
    public void testSetValue_1_oe() {
        for (int j = 0; j < 128; j++) {
            assertEquals(bf_multi.getValue(bf_multi.setValue(0, j)), j);
    }
    }

    @Test
    public void testSetValue_2_oe() {
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            assertEquals(bf_multi.setValue(0, j), j << 7);
    }
    }

    @Test
    public void testSetValue_3_oe() {
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            // removed other assertion
        }
        for (int j = 0; j < 128; j++) {
          assertEquals(bf_zero.getValue(bf_zero.setValue(0, j)), 0);
    }
    }

    @Test
    public void testSetValue_4_oe() {
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            // removed other assertion
        }
        for (int j = 0; j < 128; j++) {
          // removed other assertion
          assertEquals(bf_zero.setValue(0, j), 0);
    }
    }

    @Test
    public void testSetValue_5_oe() {
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            // removed other assertion
        }
        for (int j = 0; j < 128; j++) {
          // removed other assertion
          // removed other assertion
      }

        // verify that excess bits are stripped off
        assertEquals(bf_multi.setValue(0x3f80, 128), 0);
    }

    @Test
    public void testSetValue_6_oe() {
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            // removed other assertion
        }
        for (int j = 0; j < 128; j++) {
          // removed other assertion
          // removed other assertion
      }

        // verify that excess bits are stripped off
        // removed other assertion
        for (int j = 0; j < 2; j++) {
            assertEquals(bf_single.getValue(bf_single.setValue(0, j)), j);
    }
    }

    @Test
    public void testSetValue_7_oe() {
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            // removed other assertion
        }
        for (int j = 0; j < 128; j++) {
          // removed other assertion
          // removed other assertion
      }

        // verify that excess bits are stripped off
        // removed other assertion
        for (int j = 0; j < 2; j++) {
            // removed other assertion
            assertEquals(bf_single.setValue(0, j), j << 14);
    }
    }

    @Test
    public void testSetValue_8_oe() {
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            // removed other assertion
        }
        for (int j = 0; j < 128; j++) {
          // removed other assertion
          // removed other assertion
      }

        // verify that excess bits are stripped off
        // removed other assertion
        for (int j = 0; j < 2; j++) {
            // removed other assertion
            // removed other assertion
        }

        // verify that excess bits are stripped off
        assertEquals(bf_single.setValue(0x4000, 2), 0);
    }

    @Test
    public void testSetShortValue_1_oe() {
        for (int j = 0; j < 128; j++) {
            assertEquals(bf_multi.getShortValue(bf_multi.setShortValue((short) 0, (short) j)), (short) j);
    }
    }

    @Test
    public void testSetShortValue_2_oe() {
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            assertEquals(bf_multi.setShortValue((short) 0, (short) j), (short) (j << 7));
    }
    }

    @Test
    public void testSetShortValue_3_oe() {
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            // removed other assertion
        }
        for (int j = 0; j < 128; j++) {
            assertEquals(bf_zero.getShortValue(bf_zero.setShortValue((short) 0, (short) j)), (short) 0);
    }
    }

    @Test
    public void testSetShortValue_4_oe() {
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            // removed other assertion
        }
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            assertEquals(bf_zero.setShortValue((short) 0, (short) j), (short) 0);
    }
    }

    @Test
    public void testSetShortValue_5_oe() {
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            // removed other assertion
        }
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            // removed other assertion
        }

        // verify that excess bits are stripped off
        assertEquals(bf_multi.setShortValue((short) 0x3f80, (short) 128), (short) 0);
    }

    @Test
    public void testSetShortValue_6_oe() {
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            // removed other assertion
        }
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            // removed other assertion
        }

        // verify that excess bits are stripped off
        // removed other assertion
        for (int j = 0; j < 2; j++) {
            assertEquals(bf_single.getShortValue(bf_single.setShortValue((short) 0, (short) j)), (short) j);
    }
    }

    @Test
    public void testSetShortValue_7_oe() {
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            // removed other assertion
        }
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            // removed other assertion
        }

        // verify that excess bits are stripped off
        // removed other assertion
        for (int j = 0; j < 2; j++) {
            // removed other assertion
            assertEquals(bf_single.setShortValue((short) 0, (short) j), (short) (j << 14));
    }
    }

    @Test
    public void testSetShortValue_8_oe() {
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            // removed other assertion
        }
        for (int j = 0; j < 128; j++) {
            // removed other assertion
            // removed other assertion
        }

        // verify that excess bits are stripped off
        // removed other assertion
        for (int j = 0; j < 2; j++) {
            // removed other assertion
            // removed other assertion
        }

        // verify that excess bits are stripped off
        assertEquals(bf_single.setShortValue((short) 0x4000, (short) 2), (short) 0);
    }

    @Test
    public void testByte_1_oe() {
        assertEquals(0, new BitField(0).setByteBoolean((byte) 0, true));
    }

    @Test
    public void testByte_2_oe() {
        // removed other assertion
        assertEquals(1, new BitField(1).setByteBoolean((byte) 0, true));
    }

    @Test
    public void testByte_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(2, new BitField(2).setByteBoolean((byte) 0, true));
    }

    @Test
    public void testByte_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, new BitField(4).setByteBoolean((byte) 0, true));
    }

    @Test
    public void testByte_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, new BitField(8).setByteBoolean((byte) 0, true));
    }

    @Test
    public void testByte_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(16, new BitField(16).setByteBoolean((byte) 0, true));
    }

    @Test
    public void testByte_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(32, new BitField(32).setByteBoolean((byte) 0, true));
    }

    @Test
    public void testByte_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(64, new BitField(64).setByteBoolean((byte) 0, true));
    }

    @Test
    public void testByte_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-128, new BitField(128).setByteBoolean((byte) 0, true));
    }

    @Test
    public void testByte_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, new BitField(0).setByteBoolean((byte) 1, false));
    }

    @Test
    public void testByte_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, new BitField(1).setByteBoolean((byte) 1, false));
    }

    @Test
    public void testByte_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, new BitField(2).setByteBoolean((byte) 2, false));
    }

    @Test
    public void testByte_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, new BitField(4).setByteBoolean((byte) 4, false));
    }

    @Test
    public void testByte_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, new BitField(8).setByteBoolean((byte) 8, false));
    }

    @Test
    public void testByte_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, new BitField(16).setByteBoolean((byte) 16, false));
    }

    @Test
    public void testByte_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, new BitField(32).setByteBoolean((byte) 32, false));
    }

    @Test
    public void testByte_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, new BitField(64).setByteBoolean((byte) 64, false));
    }

    @Test
    public void testByte_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, new BitField(128).setByteBoolean((byte) 128, false));
    }

    @Test
    public void testByte_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-2, new BitField(1).setByteBoolean((byte) 255, false));
    }

    @Test
    public void testByte_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final byte clearedBit = new BitField(0x40).setByteBoolean((byte) - 63, false);

        assertFalse(new BitField(0x40).isSet(clearedBit));
    }

    @Test
    public void testClear_1_oe() {
        assertEquals(bf_multi.clear(-1), 0xFFFFC07F);
    }

    @Test
    public void testClear_2_oe() {
        // removed other assertion
        assertEquals(bf_single.clear(-1), 0xFFFFBFFF);
    }

    @Test
    public void testClear_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(bf_zero.clear(-1), 0xFFFFFFFF);
    }

    @Test
    public void testClearShort_1_oe() {
        assertEquals(bf_multi.clearShort((short) - 1), (short) 0xC07F);
    }

    @Test
    public void testClearShort_2_oe() {
        // removed other assertion
        assertEquals(bf_single.clearShort((short) - 1), (short) 0xBFFF);
    }

    @Test
    public void testClearShort_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(bf_zero.clearShort((short) -1), (short) 0xFFFF);
    }

    @Test
    public void testSet_1_oe() {
        assertEquals(bf_multi.set(0), 0x3F80);
    }

    @Test
    public void testSet_2_oe() {
        // removed other assertion
        assertEquals(bf_single.set(0), 0x4000);
    }

    @Test
    public void testSet_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(bf_zero.set(0), 0);
    }

    @Test
    public void testSetShort_1_oe() {
        assertEquals(bf_multi.setShort((short) 0), (short) 0x3F80);
    }

    @Test
    public void testSetShort_2_oe() {
        // removed other assertion
        assertEquals(bf_single.setShort((short) 0), (short) 0x4000);
    }

    @Test
    public void testSetShort_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(bf_zero.setShort((short) 0), (short) 0);
    }

    @Test
    public void testSetBoolean_1_oe() {
        assertEquals(bf_multi.set(0), bf_multi.setBoolean(0, true));
    }

    @Test
    public void testSetBoolean_2_oe() {
        // removed other assertion
        assertEquals(bf_single.set(0), bf_single.setBoolean(0, true));
    }

    @Test
    public void testSetBoolean_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(bf_zero.set(0), bf_zero.setBoolean(0, true));
    }

    @Test
    public void testSetBoolean_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_multi.clear(-1), bf_multi.setBoolean(-1, false));
    }

    @Test
    public void testSetBoolean_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_single.clear(-1), bf_single.setBoolean(-1, false));
    }

    @Test
    public void testSetBoolean_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_zero.clear(-1), bf_zero.setBoolean(-1, false));
    }

    @Test
    public void testSetShortBoolean_1_oe() {
        assertEquals(bf_multi.setShort((short) 0), bf_multi.setShortBoolean((short) 0, true));
    }

    @Test
    public void testSetShortBoolean_2_oe() {
        // removed other assertion
        assertEquals(bf_single.setShort((short) 0), bf_single.setShortBoolean((short) 0, true));
    }

    @Test
    public void testSetShortBoolean_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(bf_zero.setShort((short) 0), bf_zero.setShortBoolean((short) 0, true));
    }

    @Test
    public void testSetShortBoolean_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_multi.clearShort((short) - 1), bf_multi.setShortBoolean((short) - 1, false));
    }

    @Test
    public void testSetShortBoolean_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_single.clearShort((short) - 1), bf_single.setShortBoolean((short) - 1, false));
    }

    @Test
    public void testSetShortBoolean_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bf_zero.clearShort((short) -1), bf_zero.setShortBoolean((short) -1, false));
    }

}
