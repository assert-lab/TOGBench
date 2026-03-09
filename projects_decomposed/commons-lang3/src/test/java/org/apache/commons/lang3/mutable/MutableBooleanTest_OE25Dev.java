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

import static org.junit.jupiter.api.Assertions.fail;

/**
 * JUnit tests.
 *
 * @since 2.2
 * @see MutableBoolean
 */
public class MutableBooleanTest_OE25Dev {

    @Test
    public void testCompareTo() {
        final MutableBoolean mutBool = new MutableBoolean(false);

        assertEquals(0, mutBool.compareTo(new MutableBoolean(false)));
        assertEquals(-1, mutBool.compareTo(new MutableBoolean(true)));
        mutBool.setValue(true);
        assertEquals(+1, mutBool.compareTo(new MutableBoolean(false)));
        assertEquals(0, mutBool.compareTo(new MutableBoolean(true)));
    }

    @Test
    public void testCompareToNull() {
        final MutableBoolean mutBool = new MutableBoolean(false);
        assertThrows(NullPointerException.class, () -> mutBool.compareTo(null));
    }

    @Test
    public void testConstructorNull() {
        assertThrows(NullPointerException.class, () -> new MutableBoolean(null));
    }

    // ----------------------------------------------------------------
    @Test
    public void testConstructors() {
        assertFalse(new MutableBoolean().booleanValue());

        assertTrue(new MutableBoolean(true).booleanValue());
        assertFalse(new MutableBoolean(false).booleanValue());

        assertTrue(new MutableBoolean(Boolean.TRUE).booleanValue());
        assertFalse(new MutableBoolean(Boolean.FALSE).booleanValue());

    }

    @Test
    public void testEquals() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        assertEquals(mutBoolA, mutBoolA);
        assertEquals(mutBoolA, mutBoolB);
        assertEquals(mutBoolB, mutBoolA);
        assertEquals(mutBoolB, mutBoolB);
        assertNotEquals(mutBoolA, mutBoolC);
        assertNotEquals(mutBoolB, mutBoolC);
        assertEquals(mutBoolC, mutBoolC);
        assertNotEquals(null, mutBoolA);
        assertNotEquals(mutBoolA, Boolean.FALSE);
        assertNotEquals("false", mutBoolA);
    }

    @Test
    public void testGetSet() {
        assertFalse(new MutableBoolean().booleanValue());
        assertEquals(Boolean.FALSE, new MutableBoolean().getValue());

        final MutableBoolean mutBool = new MutableBoolean(false);
        assertEquals(Boolean.FALSE, mutBool.toBoolean());
        assertFalse(mutBool.booleanValue());
        assertTrue(mutBool.isFalse());
        assertFalse(mutBool.isTrue());

        mutBool.setValue(Boolean.TRUE);
        assertEquals(Boolean.TRUE, mutBool.toBoolean());
        assertTrue(mutBool.booleanValue());
        assertFalse(mutBool.isFalse());
        assertTrue(mutBool.isTrue());

        mutBool.setValue(false);
        assertFalse(mutBool.booleanValue());

        mutBool.setValue(true);
        assertTrue(mutBool.booleanValue());

        mutBool.setFalse();
        assertFalse(mutBool.booleanValue());

        mutBool.setTrue();
        assertTrue(mutBool.booleanValue());

    }

    @Test
    public void testHashCode() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        assertEquals(mutBoolA.hashCode(), mutBoolA.hashCode());
        assertEquals(mutBoolA.hashCode(), mutBoolB.hashCode());
        assertNotEquals(mutBoolA.hashCode(), mutBoolC.hashCode());
        assertEquals(mutBoolA.hashCode(), Boolean.FALSE.hashCode());
        assertEquals(mutBoolC.hashCode(), Boolean.TRUE.hashCode());
    }

    @Test
    public void testSetNull() {
        final MutableBoolean mutBool = new MutableBoolean(false);
        assertThrows(NullPointerException.class, () -> mutBool.setValue(null));
    }

    @Test
    public void testToString() {
        assertEquals(Boolean.FALSE.toString(), new MutableBoolean(false).toString());
        assertEquals(Boolean.TRUE.toString(), new MutableBoolean(true).toString());
    }

    @Test
    public void testCompareTo_1_oe() {
        final MutableBoolean mutBool = new MutableBoolean(false);

        assertEquals(0, mutBool.compareTo(new MutableBoolean(false)));
    }

    @Test
    public void testCompareTo_2_oe() {
        final MutableBoolean mutBool = new MutableBoolean(false);

        assertEquals(-1, mutBool.compareTo(new MutableBoolean(true)));
    }

    @Test
    public void testCompareTo_3_oe() {
        final MutableBoolean mutBool = new MutableBoolean(false);

        mutBool.setValue(true);
        assertEquals(+1, mutBool.compareTo(new MutableBoolean(false)));
    }

    @Test
    public void testCompareTo_4_oe() {
        final MutableBoolean mutBool = new MutableBoolean(false);

        mutBool.setValue(true);
        assertEquals(0, mutBool.compareTo(new MutableBoolean(true)));
    }

    @Test
    public void testCompareToNull_1_oe() throws Exception {
        final MutableBoolean mutBool = new MutableBoolean(false);
        try {
    mutBool.compareTo(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testConstructorNull_1_oe() throws Exception {
        try {
    new MutableBoolean(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testConstructors_1_oe() {
        assertFalse(new MutableBoolean().booleanValue());
    }

    @Test
    public void testConstructors_2_oe() {

        assertTrue(new MutableBoolean(true).booleanValue());
    }

    @Test
    public void testConstructors_3_oe() {

        assertFalse(new MutableBoolean(false).booleanValue());
    }

    @Test
    public void testConstructors_4_oe() {


        assertTrue(new MutableBoolean(Boolean.TRUE).booleanValue());
    }

    @Test
    public void testConstructors_5_oe() {


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

        assertEquals(mutBoolA, mutBoolB);
    }

    @Test
    public void testEquals_3_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        assertEquals(mutBoolB, mutBoolA);
    }

    @Test
    public void testEquals_4_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        assertEquals(mutBoolB, mutBoolB);
    }

    @Test
    public void testEquals_5_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        assertNotEquals(mutBoolA, mutBoolC);
    }

    @Test
    public void testEquals_6_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        assertNotEquals(mutBoolB, mutBoolC);
    }

    @Test
    public void testEquals_7_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        assertEquals(mutBoolC, mutBoolC);
    }

    @Test
    public void testEquals_8_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        assertNotEquals(null, mutBoolA);
    }

    @Test
    public void testEquals_9_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        assertNotEquals(mutBoolA, Boolean.FALSE);
    }

    @Test
    public void testEquals_10_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        assertNotEquals("false", mutBoolA);
    }

    @Test
    public void testGetSet_1_oe() {
        assertFalse(new MutableBoolean().booleanValue());
    }

    @Test
    public void testGetSet_2_oe() {
        assertEquals(Boolean.FALSE, new MutableBoolean().getValue());
    }

    @Test
    public void testGetSet_3_oe() {

        final MutableBoolean mutBool = new MutableBoolean(false);
        assertEquals(Boolean.FALSE, mutBool.toBoolean());
    }

    @Test
    public void testGetSet_4_oe() {

        final MutableBoolean mutBool = new MutableBoolean(false);
        assertFalse(mutBool.booleanValue());
    }

    @Test
    public void testGetSet_5_oe() {

        final MutableBoolean mutBool = new MutableBoolean(false);
        assertTrue(mutBool.isFalse());
    }

    @Test
    public void testGetSet_6_oe() {

        final MutableBoolean mutBool = new MutableBoolean(false);
        assertFalse(mutBool.isTrue());
    }

    @Test
    public void testGetSet_7_oe() {

        final MutableBoolean mutBool = new MutableBoolean(false);

        mutBool.setValue(Boolean.TRUE);
        assertEquals(Boolean.TRUE, mutBool.toBoolean());
    }

    @Test
    public void testGetSet_8_oe() {

        final MutableBoolean mutBool = new MutableBoolean(false);

        mutBool.setValue(Boolean.TRUE);
        assertTrue(mutBool.booleanValue());
    }

    @Test
    public void testGetSet_9_oe() {

        final MutableBoolean mutBool = new MutableBoolean(false);

        mutBool.setValue(Boolean.TRUE);
        assertFalse(mutBool.isFalse());
    }

    @Test
    public void testGetSet_10_oe() {

        final MutableBoolean mutBool = new MutableBoolean(false);

        mutBool.setValue(Boolean.TRUE);
        assertTrue(mutBool.isTrue());
    }

    @Test
    public void testGetSet_11_oe() {

        final MutableBoolean mutBool = new MutableBoolean(false);

        mutBool.setValue(Boolean.TRUE);

        mutBool.setValue(false);
        assertFalse(mutBool.booleanValue());
    }

    @Test
    public void testGetSet_12_oe() {

        final MutableBoolean mutBool = new MutableBoolean(false);

        mutBool.setValue(Boolean.TRUE);

        mutBool.setValue(false);

        mutBool.setValue(true);
        assertTrue(mutBool.booleanValue());
    }

    @Test
    public void testGetSet_13_oe() {

        final MutableBoolean mutBool = new MutableBoolean(false);

        mutBool.setValue(Boolean.TRUE);

        mutBool.setValue(false);

        mutBool.setValue(true);

        mutBool.setFalse();
        assertFalse(mutBool.booleanValue());
    }

    @Test
    public void testGetSet_14_oe() {

        final MutableBoolean mutBool = new MutableBoolean(false);

        mutBool.setValue(Boolean.TRUE);

        mutBool.setValue(false);

        mutBool.setValue(true);

        mutBool.setFalse();

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

        assertEquals(mutBoolA.hashCode(), mutBoolB.hashCode());
    }

    @Test
    public void testHashCode_3_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        assertNotEquals(mutBoolA.hashCode(), mutBoolC.hashCode());
    }

    @Test
    public void testHashCode_4_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        assertEquals(mutBoolA.hashCode(), Boolean.FALSE.hashCode());
    }

    @Test
    public void testHashCode_5_oe() {
        final MutableBoolean mutBoolA = new MutableBoolean(false);
        final MutableBoolean mutBoolB = new MutableBoolean(false);
        final MutableBoolean mutBoolC = new MutableBoolean(true);

        assertEquals(mutBoolC.hashCode(), Boolean.TRUE.hashCode());
    }

    @Test
    public void testSetNull_1_oe() throws Exception {
        final MutableBoolean mutBool = new MutableBoolean(false);
        try {
    mutBool.setValue(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testToString_1_oe() {
        assertEquals(Boolean.FALSE.toString(), new MutableBoolean(false).toString());
    }

    @Test
    public void testToString_2_oe() {
        assertEquals(Boolean.TRUE.toString(), new MutableBoolean(true).toString());
    }

}
