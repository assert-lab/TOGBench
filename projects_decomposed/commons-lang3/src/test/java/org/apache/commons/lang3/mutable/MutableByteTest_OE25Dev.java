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
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * JUnit tests.
 *
 * @see MutableByte
 */
public class MutableByteTest_OE25Dev {

    // ----------------------------------------------------------------

    @Test
    public void testAddAndGetValueObject_1_oe() {
        final MutableByte mutableByte = new MutableByte((byte) 0);
        final byte result = mutableByte.addAndGet(Byte.valueOf((byte) 1));

        assertEquals((byte) 1, result);
    }

    @Test
    public void testAddAndGetValueObject_2_oe() {
        final MutableByte mutableByte = new MutableByte((byte) 0);
        final byte result = mutableByte.addAndGet(Byte.valueOf((byte) 1));

        // removed other assertion
        assertEquals((byte) 1, mutableByte.byteValue());
    }

    @Test
    public void testAddAndGetValuePrimitive_1_oe() {
        final MutableByte mutableByte = new MutableByte((byte) 0);
        final byte result = mutableByte.addAndGet((byte) 1);

        assertEquals((byte) 1, result);
    }

    @Test
    public void testAddAndGetValuePrimitive_2_oe() {
        final MutableByte mutableByte = new MutableByte((byte) 0);
        final byte result = mutableByte.addAndGet((byte) 1);

        // removed other assertion
        assertEquals((byte) 1, mutableByte.byteValue());
    }

    @Test
    public void testAddValueObject_1_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        mutNum.add(Integer.valueOf(1));

        assertEquals((byte) 2, mutNum.byteValue());
    }

    @Test
    public void testAddValuePrimitive_1_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        mutNum.add((byte) 1);

        assertEquals((byte) 2, mutNum.byteValue());
    }

    @Test
    public void testCompareTo_1_oe() {
        final MutableByte mutNum = new MutableByte((byte) 0);

        assertEquals((byte) 0, mutNum.compareTo(new MutableByte((byte) 0)));
    }

    @Test
    public void testCompareTo_2_oe() {
        final MutableByte mutNum = new MutableByte((byte) 0);

        // removed other assertion
        assertEquals((byte) +1, mutNum.compareTo(new MutableByte((byte) -1)));
    }

    @Test
    public void testCompareTo_3_oe() {
        final MutableByte mutNum = new MutableByte((byte) 0);

        // removed other assertion
        // removed other assertion
        assertEquals((byte) -1, mutNum.compareTo(new MutableByte((byte) 1)));
    }

    @Test
    public void testCompareToNull_1_oe() throws Exception {
        final MutableByte mutNum = new MutableByte((byte) 0);
        try {
    mutNum.compareTo(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testConstructorNull_1_oe() throws Exception {
        try {
    new MutableByte((Number) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testConstructors_1_oe() {
        assertEquals((byte) 0, new MutableByte().byteValue());
    }

    @Test
    public void testConstructors_2_oe() {
        // removed other assertion

        assertEquals((byte) 1, new MutableByte((byte) 1).byteValue());
    }

    @Test
    public void testConstructors_3_oe() {
        // removed other assertion

        // removed other assertion

        assertEquals((byte) 2, new MutableByte(Byte.valueOf((byte) 2)).byteValue());
    }

    @Test
    public void testConstructors_4_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertEquals((byte) 3, new MutableByte(new MutableByte((byte) 3)).byteValue());
    }

    @Test
    public void testConstructors_5_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals((byte) 2, new MutableByte("2").byteValue());
    }

    @Test
    public void testDecrement_1_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        mutNum.decrement();

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testDecrement_2_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        mutNum.decrement();

        // removed other assertion
        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testDecrementAndGet_1_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        final byte result = mutNum.decrementAndGet();

        assertEquals(0, result);
    }

    @Test
    public void testDecrementAndGet_2_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        final byte result = mutNum.decrementAndGet();

        // removed other assertion
        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testDecrementAndGet_3_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        final byte result = mutNum.decrementAndGet();

        // removed other assertion
        // removed other assertion
        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testEquals_1_oe() {
        final MutableByte mutNumA = new MutableByte((byte) 0);
        final MutableByte mutNumB = new MutableByte((byte) 0);
        final MutableByte mutNumC = new MutableByte((byte) 1);

        assertEquals(mutNumA, mutNumA);
    }

    @Test
    public void testEquals_2_oe() {
        final MutableByte mutNumA = new MutableByte((byte) 0);
        final MutableByte mutNumB = new MutableByte((byte) 0);
        final MutableByte mutNumC = new MutableByte((byte) 1);

        // removed other assertion
        assertEquals(mutNumA, mutNumB);
    }

    @Test
    public void testEquals_3_oe() {
        final MutableByte mutNumA = new MutableByte((byte) 0);
        final MutableByte mutNumB = new MutableByte((byte) 0);
        final MutableByte mutNumC = new MutableByte((byte) 1);

        // removed other assertion
        // removed other assertion
        assertEquals(mutNumB, mutNumA);
    }

    @Test
    public void testEquals_4_oe() {
        final MutableByte mutNumA = new MutableByte((byte) 0);
        final MutableByte mutNumB = new MutableByte((byte) 0);
        final MutableByte mutNumC = new MutableByte((byte) 1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(mutNumB, mutNumB);
    }

    @Test
    public void testEquals_5_oe() {
        final MutableByte mutNumA = new MutableByte((byte) 0);
        final MutableByte mutNumB = new MutableByte((byte) 0);
        final MutableByte mutNumC = new MutableByte((byte) 1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(mutNumA, mutNumC);
    }

    @Test
    public void testEquals_6_oe() {
        final MutableByte mutNumA = new MutableByte((byte) 0);
        final MutableByte mutNumB = new MutableByte((byte) 0);
        final MutableByte mutNumC = new MutableByte((byte) 1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(mutNumB, mutNumC);
    }

    @Test
    public void testEquals_7_oe() {
        final MutableByte mutNumA = new MutableByte((byte) 0);
        final MutableByte mutNumB = new MutableByte((byte) 0);
        final MutableByte mutNumC = new MutableByte((byte) 1);

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
        final MutableByte mutNumA = new MutableByte((byte) 0);
        final MutableByte mutNumB = new MutableByte((byte) 0);
        final MutableByte mutNumC = new MutableByte((byte) 1);

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
    public void testEquals_9_oe() {
        final MutableByte mutNumA = new MutableByte((byte) 0);
        final MutableByte mutNumB = new MutableByte((byte) 0);
        final MutableByte mutNumC = new MutableByte((byte) 1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(mutNumA, Byte.valueOf((byte) 0));
    }

    @Test
    public void testEquals_10_oe() {
        final MutableByte mutNumA = new MutableByte((byte) 0);
        final MutableByte mutNumB = new MutableByte((byte) 0);
        final MutableByte mutNumC = new MutableByte((byte) 1);

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
    public void testGetAndAddValueObject_1_oe() {
        final MutableByte mutableByte = new MutableByte((byte) 0);
        final byte result = mutableByte.getAndAdd(Byte.valueOf((byte) 1));

        assertEquals((byte) 0, result);
    }

    @Test
    public void testGetAndAddValueObject_2_oe() {
        final MutableByte mutableByte = new MutableByte((byte) 0);
        final byte result = mutableByte.getAndAdd(Byte.valueOf((byte) 1));

        // removed other assertion
        assertEquals((byte) 1, mutableByte.byteValue());
    }

    @Test
    public void testGetAndAddValuePrimitive_1_oe() {
        final MutableByte mutableByte = new MutableByte((byte) 0);
        final byte result = mutableByte.getAndAdd((byte) 1);

        assertEquals((byte) 0, result);
    }

    @Test
    public void testGetAndAddValuePrimitive_2_oe() {
        final MutableByte mutableByte = new MutableByte((byte) 0);
        final byte result = mutableByte.getAndAdd((byte) 1);

        // removed other assertion
        assertEquals((byte) 1, mutableByte.byteValue());
    }

    @Test
    public void testGetAndDecrement_1_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        final byte result = mutNum.getAndDecrement();

        assertEquals(1, result);
    }

    @Test
    public void testGetAndDecrement_2_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        final byte result = mutNum.getAndDecrement();

        // removed other assertion
        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testGetAndDecrement_3_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        final byte result = mutNum.getAndDecrement();

        // removed other assertion
        // removed other assertion
        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testGetAndIncrement_1_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        final byte result = mutNum.getAndIncrement();

        assertEquals(1, result);
    }

    @Test
    public void testGetAndIncrement_2_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        final byte result = mutNum.getAndIncrement();

        // removed other assertion
        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testGetAndIncrement_3_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        final byte result = mutNum.getAndIncrement();

        // removed other assertion
        // removed other assertion
        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testGetSet_1_oe() {
        final MutableByte mutNum = new MutableByte((byte) 0);
        assertEquals((byte) 0, new MutableByte().byteValue());
    }

    @Test
    public void testGetSet_2_oe() {
        final MutableByte mutNum = new MutableByte((byte) 0);
        // removed other assertion
        assertEquals(Byte.valueOf((byte) 0), new MutableByte().getValue());
    }

    @Test
    public void testGetSet_3_oe() {
        final MutableByte mutNum = new MutableByte((byte) 0);
        // removed other assertion
        // removed other assertion

        mutNum.setValue((byte) 1);
        assertEquals((byte) 1, mutNum.byteValue());
    }

    @Test
    public void testGetSet_4_oe() {
        final MutableByte mutNum = new MutableByte((byte) 0);
        // removed other assertion
        // removed other assertion

        mutNum.setValue((byte) 1);
        // removed other assertion
        assertEquals(Byte.valueOf((byte) 1), mutNum.getValue());
    }

    @Test
    public void testGetSet_5_oe() {
        final MutableByte mutNum = new MutableByte((byte) 0);
        // removed other assertion
        // removed other assertion

        mutNum.setValue((byte) 1);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(Byte.valueOf((byte) 2));
        assertEquals((byte) 2, mutNum.byteValue());
    }

    @Test
    public void testGetSet_6_oe() {
        final MutableByte mutNum = new MutableByte((byte) 0);
        // removed other assertion
        // removed other assertion

        mutNum.setValue((byte) 1);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(Byte.valueOf((byte) 2));
        // removed other assertion
        assertEquals(Byte.valueOf((byte) 2), mutNum.getValue());
    }

    @Test
    public void testGetSet_7_oe() {
        final MutableByte mutNum = new MutableByte((byte) 0);
        // removed other assertion
        // removed other assertion

        mutNum.setValue((byte) 1);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(Byte.valueOf((byte) 2));
        // removed other assertion
        // removed other assertion

        mutNum.setValue(new MutableByte((byte) 3));
        assertEquals((byte) 3, mutNum.byteValue());
    }

    @Test
    public void testGetSet_8_oe() {
        final MutableByte mutNum = new MutableByte((byte) 0);
        // removed other assertion
        // removed other assertion

        mutNum.setValue((byte) 1);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(Byte.valueOf((byte) 2));
        // removed other assertion
        // removed other assertion

        mutNum.setValue(new MutableByte((byte) 3));
        // removed other assertion
        assertEquals(Byte.valueOf((byte) 3), mutNum.getValue());
    }

    @Test
    public void testHashCode_1_oe() {
        final MutableByte mutNumA = new MutableByte((byte) 0);
        final MutableByte mutNumB = new MutableByte((byte) 0);
        final MutableByte mutNumC = new MutableByte((byte) 1);

        assertEquals(mutNumA.hashCode(), mutNumA.hashCode());
    }

    @Test
    public void testHashCode_2_oe() {
        final MutableByte mutNumA = new MutableByte((byte) 0);
        final MutableByte mutNumB = new MutableByte((byte) 0);
        final MutableByte mutNumC = new MutableByte((byte) 1);

        // removed other assertion
        assertEquals(mutNumA.hashCode(), mutNumB.hashCode());
    }

    @Test
    public void testHashCode_3_oe() {
        final MutableByte mutNumA = new MutableByte((byte) 0);
        final MutableByte mutNumB = new MutableByte((byte) 0);
        final MutableByte mutNumC = new MutableByte((byte) 1);

        // removed other assertion
        // removed other assertion
        assertNotEquals(mutNumA.hashCode(), mutNumC.hashCode());
    }

    @Test
    public void testHashCode_4_oe() {
        final MutableByte mutNumA = new MutableByte((byte) 0);
        final MutableByte mutNumB = new MutableByte((byte) 0);
        final MutableByte mutNumC = new MutableByte((byte) 1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(mutNumA.hashCode(), Byte.valueOf((byte) 0).hashCode());
    }

    @Test
    public void testIncrement_1_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        mutNum.increment();

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testIncrement_2_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        mutNum.increment();

        // removed other assertion
        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testIncrementAndGet_1_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        final byte result = mutNum.incrementAndGet();

        assertEquals(2, result);
    }

    @Test
    public void testIncrementAndGet_2_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        final byte result = mutNum.incrementAndGet();

        // removed other assertion
        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testIncrementAndGet_3_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        final byte result = mutNum.incrementAndGet();

        // removed other assertion
        // removed other assertion
        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testPrimitiveValues_1_oe() {
        final MutableByte mutNum = new MutableByte( (byte) 1 );
        assertEquals(1.0F, mutNum.floatValue());
    }

    @Test
    public void testPrimitiveValues_2_oe() {
        final MutableByte mutNum = new MutableByte( (byte) 1 );
        // removed other assertion
        assertEquals(1.0, mutNum.doubleValue());
    }

    @Test
    public void testPrimitiveValues_3_oe() {
        final MutableByte mutNum = new MutableByte( (byte) 1 );
        // removed other assertion
        // removed other assertion
        assertEquals( (byte) 1, mutNum.byteValue() );
    }

    @Test
    public void testPrimitiveValues_4_oe() {
        final MutableByte mutNum = new MutableByte( (byte) 1 );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( (short) 1, mutNum.shortValue() );
    }

    @Test
    public void testPrimitiveValues_5_oe() {
        final MutableByte mutNum = new MutableByte( (byte) 1 );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 1, mutNum.intValue() );
    }

    @Test
    public void testPrimitiveValues_6_oe() {
        final MutableByte mutNum = new MutableByte( (byte) 1 );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 1L, mutNum.longValue() );
    }

    @Test
    public void testSetNull_1_oe() throws Exception {
        final MutableByte mutNum = new MutableByte((byte) 0);
        try {
    mutNum.setValue(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testSubtractValueObject_1_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        mutNum.subtract(Integer.valueOf(1));

        assertEquals((byte) 0, mutNum.byteValue());
    }

    @Test
    public void testSubtractValuePrimitive_1_oe() {
        final MutableByte mutNum = new MutableByte((byte) 1);
        mutNum.subtract((byte) 1);

        assertEquals((byte) 0, mutNum.byteValue());
    }

    @Test
    public void testToByte_1_oe() {
        assertEquals(Byte.valueOf((byte) 0), new MutableByte((byte) 0).toByte());
    }

    @Test
    public void testToByte_2_oe() {
        // removed other assertion
        assertEquals(Byte.valueOf((byte) 123), new MutableByte((byte) 123).toByte());
    }

    @Test
    public void testToString_1_oe() {
        assertEquals("0", new MutableByte((byte) 0).toString());
    }

    @Test
    public void testToString_2_oe() {
        // removed other assertion
        assertEquals("10", new MutableByte((byte) 10).toString());
    }

    @Test
    public void testToString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("-123", new MutableByte((byte) -123).toString());
    }

}
