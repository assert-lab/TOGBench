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

package org.apache.commons.lang3.mutable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * JUnit tests.
 *
 * @since 2.2
 * @see MutableBoolean
 */
public class MutableBooleanTest_OE25Dev {

    // ----------------------------------------------------------------

    @Test
    public void testCompareTo_1_oe() {
        final MutableBoolean mutBool = new MutableBoolean(false);

        assertEquals(0, mutBool.compareTo(new MutableBoolean(false)));
    }

    @Test
    public void testCompareTo_2_oe() {
        final MutableBoolean mutBool = new MutableBoolean(false);

        // removed other assertion
        assertEquals(-1, mutBool.compareTo(new MutableBoolean(true)));
    }

    @Test
    public void testCompareTo_3_oe() {
        final MutableBoolean mutBool = new MutableBoolean(false);

        // removed other assertion
        // removed other assertion
        mutBool.setValue(true);
        assertEquals(+1, mutBool.compareTo(new MutableBoolean(false)));
    }

    @Test
    public void testCompareTo_4_oe() {
        final MutableBoolean mutBool = new MutableBoolean(false);

        // removed other assertion
        // removed other assertion
        mutBool.setValue(true);
        // removed other assertion
        assertEquals(0, mutBool.compareTo(new MutableBoolean(true)));
    }

    @Test
    public void testCompareToNull_1_oe() {
        final MutableBoolean mutBool = new MutableBoolean(false);
        assertThrows(NullPointerException.class, () -> mutBool.compareTo(null));
    }

    @Test
    public void testConstructorNull_1_oe() {
        assertThrows(NullPointerException.class, () -> new MutableBoolean(null));
    }

    @Test
    public void testConstructors_1_oe() {
        assertFalse(new MutableBoolean().booleanValue());
    }

    @Test
    public void testConstructors_2_oe() {
        // removed other assertion

        assertTrue(new MutableBoolean(true).booleanValue());
    }

    @Test
    public void testConstructors_3_oe() {
        // removed other assertion

        // removed other assertion
        assertFalse(new MutableBoolean(false).booleanValue());
    }

    @Test
    public void testConstructors_4_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertTrue(new MutableBoolean(Boolean.TRUE).booleanValue());
    }

    @Test
    public void testConstructors_5_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(new MutableBoolean(Boolean.FALSE).booleanValue());
    }

    @Test
    public void testEquals_1_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        assertEquals(mutBoolA, mutBoolA);
    }

    @Test
    public void testEquals_2_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        // removed other assertion
        assertEquals(mutBoolA, mutBoolB);
    }

    @Test
    public void testEquals_3_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        // removed other assertion
        // removed other assertion
        assertEquals(mutBoolB, mutBoolA);
    }

    @Test
    public void testEquals_4_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(mutBoolB, mutBoolB);
    }

    @Test
    public void testEquals_5_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(mutBoolA, mutBoolC);
    }

    @Test
    public void testEquals_6_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(mutBoolB, mutBoolC);
    }

    @Test
    public void testEquals_7_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(mutBoolC, mutBoolC);
    }

    @Test
    public void testEquals_8_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(null, mutBoolA);
    }

    @Test
    public void testEquals_9_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(mutBoolA, Boolean.FALSE);
    }

    @Test
    public void testEquals_10_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals("false", mutBoolA);
    }

    @Test
    public void testGetSet_1_oe() {
        assertFalse(new MutableBoolean().booleanValue());
    }

    @Test
    public void testGetSet_2_oe() {
        // removed other assertion
        assertEquals(Boolean.FALSE, new MutableBoolean().getValue());
    }

    @Test
    public void testGetSet_3_oe() {
        // removed other assertion
        // removed other assertion

        final MutableBoolean mutBool = new MutableBoolean(false);
        assertEquals(Boolean.FALSE, mutBool.toBoolean());
    }

    @Test
    public void testGetSet_4_oe() {
        // removed other assertion
        // removed other assertion

        final MutableBoolean mutBool = new MutableBoolean(false);
        // removed other assertion
        assertFalse(mutBool.booleanValue());
    }

    @Test
    public void testGetSet_5_oe() {
        // removed other assertion
        // removed other assertion

        final MutableBoolean mutBool = new MutableBoolean(false);
        // removed other assertion
        // removed other assertion
        assertTrue(mutBool.isFalse());
    }

    @Test
    public void testGetSet_6_oe() {
        // removed other assertion
        // removed other assertion

        final MutableBoolean mutBool = new MutableBoolean(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(mutBool.isTrue());
    }

    @Test
    public void testGetSet_7_oe() {
        // removed other assertion
        // removed other assertion

        final MutableBoolean mutBool = new MutableBoolean(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        mutBool.setValue(Boolean.TRUE);
        assertEquals(Boolean.TRUE, mutBool.toBoolean());
    }

    @Test
    public void testGetSet_8_oe() {
        // removed other assertion
        // removed other assertion

        final MutableBoolean mutBool = new MutableBoolean(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        mutBool.setValue(Boolean.TRUE);
        // removed other assertion
        assertTrue(mutBool.booleanValue());
    }

    @Test
    public void testGetSet_9_oe() {
        // removed other assertion
        // removed other assertion

        final MutableBoolean mutBool = new MutableBoolean(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        mutBool.setValue(Boolean.TRUE);
        // removed other assertion
        // removed other assertion
        assertFalse(mutBool.isFalse());
    }

    @Test
    public void testGetSet_10_oe() {
        // removed other assertion
        // removed other assertion

        final MutableBoolean mutBool = new MutableBoolean(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        mutBool.setValue(Boolean.TRUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(mutBool.isTrue());
    }

    @Test
    public void testGetSet_11_oe() {
        // removed other assertion
        // removed other assertion

        final MutableBoolean mutBool = new MutableBoolean(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        mutBool.setValue(Boolean.TRUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        mutBool.setValue(false);
        assertFalse(mutBool.booleanValue());
    }

    @Test
    public void testGetSet_12_oe() {
        // removed other assertion
        // removed other assertion

        final MutableBoolean mutBool = new MutableBoolean(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        mutBool.setValue(Boolean.TRUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        mutBool.setValue(false);
        // removed other assertion

        mutBool.setValue(true);
        assertTrue(mutBool.booleanValue());
    }

    @Test
    public void testGetSet_13_oe() {
        // removed other assertion
        // removed other assertion

        final MutableBoolean mutBool = new MutableBoolean(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        mutBool.setValue(Boolean.TRUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        mutBool.setValue(false);
        // removed other assertion

        mutBool.setValue(true);
        // removed other assertion

        mutBool.setFalse();
        assertFalse(mutBool.booleanValue());
    }

    @Test
    public void testGetSet_14_oe() {
        // removed other assertion
        // removed other assertion

        final MutableBoolean mutBool = new MutableBoolean(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        mutBool.setValue(Boolean.TRUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        mutBool.setValue(false);
        // removed other assertion

        mutBool.setValue(true);
        // removed other assertion

        mutBool.setFalse();
        // removed other assertion

        mutBool.setTrue();
        assertTrue(mutBool.booleanValue());
    }

    @Test
    public void testHashCode_1_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        assertEquals(mutBoolA.hashCode(), mutBoolA.hashCode());
    }

    @Test
    public void testHashCode_2_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        // removed other assertion
        assertEquals(mutBoolA.hashCode(), mutBoolB.hashCode());
    }

    @Test
    public void testHashCode_3_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        // removed other assertion
        // removed other assertion
        assertNotEquals(mutBoolA.hashCode(), mutBoolC.hashCode());
    }

    @Test
    public void testHashCode_4_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(mutBoolA.hashCode(), Boolean.FALSE.hashCode());
    }

    @Test
    public void testHashCode_5_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(mutBoolC.hashCode(), Boolean.TRUE.hashCode());
    }

    @Test
    public void testSetNull_1_oe() {
        final MutableBoolean mutBool = new MutableBoolean(false);
        assertThrows(NullPointerException.class, () -> mutBool.setValue(null));
    }

    @Test
    public void testToString_1_oe() {
        assertEquals(Boolean.FALSE.toString(), new MutableBoolean(false).toString());
    }

    @Test
    public void testToString_2_oe() {
        // removed other assertion
        assertEquals(Boolean.TRUE.toString(), new MutableBoolean(true).toString());
    }

}
