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
package org.apache.commons.validator.util;

import junit.framework.TestCase;

/**
 * Test the Flags class.
 *
 * @version $Revision$
 */
public class FlagsTest_OE25Dev extends TestCase {

    /**
     * Declare some flags for testing.
     */
    private static final long LONG_FLAG = 1;
    private static final long LONG_FLAG_2 = 2;
    private static final int INT_FLAG = 4;

    /**
     * Constructor for FlagsTest_OE25Dev.
     */
    public FlagsTest_OE25Dev(String name) {
        super(name);
    }

    public void testTurnOnOff() {
    }

    public void testTurnOff() {
    }

    /**
     * Test for Object clone()
     */
    public void testClone() {
    }

    /**
     * Test for boolean equals(Object)
     */
    public void testEqualsObject() {
    }

    /**
     * Test for String toString()
     */

    public void testHashCode_1_oe() {
        Flags f = new Flags(45);
        assertEquals(f.hashCode(), 45);
    }

    public void testGetFlags_1_oe() {
        Flags f = new Flags(45);
        assertEquals(f.getFlags(), 45);
    }

    public void testIsOnOff_1_oe() {
        Flags f = new Flags();
        f.turnOn(LONG_FLAG);
        f.turnOn(INT_FLAG);
        assertTrue(f.isOn(LONG_FLAG));
    }

    public void testIsOnOff_2_oe() {
        Flags f = new Flags();
        f.turnOn(LONG_FLAG);
        f.turnOn(INT_FLAG);
        // removed other assertion
        assertTrue(!f.isOff(LONG_FLAG));
    }

    public void testIsOnOff_3_oe() {
        Flags f = new Flags();
        f.turnOn(LONG_FLAG);
        f.turnOn(INT_FLAG);
        // removed other assertion
        // removed other assertion

        assertTrue(f.isOn(INT_FLAG));
    }

    public void testIsOnOff_4_oe() {
        Flags f = new Flags();
        f.turnOn(LONG_FLAG);
        f.turnOn(INT_FLAG);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(!f.isOff(INT_FLAG));
    }

    public void testIsOnOff_5_oe() {
        Flags f = new Flags();
        f.turnOn(LONG_FLAG);
        f.turnOn(INT_FLAG);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertTrue(f.isOff(LONG_FLAG_2));
    }

    public void testTurnOffAll_1_oe() {
        Flags f = new Flags(98432);
        f.turnOffAll();
        assertEquals(0, f.getFlags());
    }

    public void testClear_1_oe() {
        Flags f = new Flags(98432);
        f.clear();
        assertEquals(0, f.getFlags());
    }

    public void testTurnOnAll_1_oe() {
        Flags f = new Flags();
        f.turnOnAll();
        assertEquals(~0, f.getFlags());
    }

    public void testIsOn_isFalseWhenNotAllFlagsInArgumentAreOn_1_oe() {
        Flags first = new Flags(1);
        long firstAndSecond = 3;
        
        assertFalse(first.isOn(firstAndSecond));
    }

    public void testIsOn_isTrueWhenHighOrderBitIsSetAndQueried_1_oe() {
        Flags allOn = new Flags(~0);
        long highOrderBit = 0x8000000000000000L;
        
        assertTrue(allOn.isOn(highOrderBit));
    }

    public void testToString_1_oe() {
        Flags f = new Flags();
        String s = f.toString();
        assertEquals(64, s.length());
    }

    public void testToString_2_oe() {
        Flags f = new Flags();
        String s = f.toString();
        // removed other assertion

        f.turnOn(INT_FLAG);
        s = f.toString();
        assertEquals(64, s.length());
    }

    public void testToString_3_oe() {
        Flags f = new Flags();
        String s = f.toString();
        // removed other assertion

        f.turnOn(INT_FLAG);
        s = f.toString();
        // removed other assertion

        assertEquals( "0000000000000000000000000000000000000000000000000000000000000100", s);
    }

}
