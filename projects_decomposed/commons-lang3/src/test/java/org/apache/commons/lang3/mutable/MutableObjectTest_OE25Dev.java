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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;


/**
 * JUnit tests.
 *
 * @see MutableShort
 */
public class MutableObjectTest_OE25Dev {

    // ----------------------------------------------------------------

@Test
    public void testConstructors_1_oe() {
        assertNull(new MutableObject<String>().getValue());
    }

@Test
    public void testConstructors_2_oe() {
        // removed other assertion

        final Integer i = Integer.valueOf(6);
        assertSame(i, new MutableObject<>(i).getValue());
    }

@Test
    public void testConstructors_3_oe() {
        // removed other assertion

        final Integer i = Integer.valueOf(6);
        // removed other assertion
        assertSame("HI", new MutableObject<>("HI").getValue());
    }

@Test
    public void testConstructors_4_oe() {
        // removed other assertion

        final Integer i = Integer.valueOf(6);
        // removed other assertion
        // removed other assertion
        assertSame(null, new MutableObject<>(null).getValue());
    }

@Test
    public void testEquals_1_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        assertEquals(mutNumA, mutNumA);
    }

@Test
    public void testEquals_2_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        // removed other assertion
        assertEquals(mutNumA, mutNumB);
    }

@Test
    public void testEquals_3_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        // removed other assertion
        // removed other assertion
        assertEquals(mutNumB, mutNumA);
    }

@Test
    public void testEquals_4_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(mutNumB, mutNumB);
    }

@Test
    public void testEquals_5_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(mutNumA, mutNumC);
    }

@Test
    public void testEquals_6_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(mutNumB, mutNumC);
    }

@Test
    public void testEquals_7_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(mutNumC, mutNumC);
    }

@Test
    public void testEquals_8_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(mutNumA, mutNumD);
    }

@Test
    public void testEquals_9_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(mutNumD, mutNumD);
    }

@Test
    public void testEquals_10_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertNotEquals(null, mutNumA);
    }

@Test
    public void testEquals_11_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertNotEquals(mutNumA, new Object());
    }

@Test
    public void testEquals_12_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNotEquals("0", mutNumA);
    }

@Test
    public void testGetSet_1_oe() {
        final MutableObject<String> mutNum = new MutableObject<>();
        assertNull(new MutableObject<>().getValue());
    }

@Test
    public void testGetSet_2_oe() {
        final MutableObject<String> mutNum = new MutableObject<>();
        // removed other assertion

        mutNum.setValue("HELLO");
        assertSame("HELLO", mutNum.getValue());
    }

@Test
    public void testGetSet_3_oe() {
        final MutableObject<String> mutNum = new MutableObject<>();
        // removed other assertion

        mutNum.setValue("HELLO");
        // removed other assertion

        mutNum.setValue(null);
        assertSame(null, mutNum.getValue());
    }

@Test
    public void testHashCode_1_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        assertEquals(mutNumA.hashCode(), mutNumA.hashCode());
    }

@Test
    public void testHashCode_2_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        // removed other assertion
        assertEquals(mutNumA.hashCode(), mutNumB.hashCode());
    }

@Test
    public void testHashCode_3_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        // removed other assertion
        // removed other assertion
        assertNotEquals(mutNumA.hashCode(), mutNumC.hashCode());
    }

@Test
    public void testHashCode_4_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(mutNumA.hashCode(), mutNumD.hashCode());
    }

@Test
    public void testHashCode_5_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(mutNumA.hashCode(), "ALPHA".hashCode());
    }

@Test
    public void testHashCode_6_oe() {
        final MutableObject<String> mutNumA = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumB = new MutableObject<>("ALPHA");
        final MutableObject<String> mutNumC = new MutableObject<>("BETA");
        final MutableObject<String> mutNumD = new MutableObject<>(null);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, mutNumD.hashCode());
    }

@Test
    public void testToString_1_oe() {
        assertEquals("HI", new MutableObject<>("HI").toString());
    }

@Test
    public void testToString_2_oe() {
        // removed other assertion
        assertEquals("10.0", new MutableObject<>(Double.valueOf(10)).toString());
    }

@Test
    public void testToString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("null", new MutableObject<>(null).toString());
    }

}
