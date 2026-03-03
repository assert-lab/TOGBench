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
 * @see MutableLong
 */
public class MutableLongTest_OE25Dev {

    // ----------------------------------------------------------------

    @Test
    public void testAddAndGetValueObject_1_oe() {
        final MutableLong mutableLong = new MutableLong(0L);
        final long result = mutableLong.addAndGet(Long.valueOf(1L));

        assertEquals(1L, result);
    }

    @Test
    public void testAddAndGetValueObject_2_oe() {
        final MutableLong mutableLong = new MutableLong(0L);
        final long result = mutableLong.addAndGet(Long.valueOf(1L));

        assertEquals(1L, mutableLong.longValue());
    }

    @Test
    public void testAddAndGetValuePrimitive_1_oe() {
        final MutableLong mutableLong = new MutableLong(0L);
        final long result = mutableLong.addAndGet(1L);

        assertEquals(1L, result);
    }

    @Test
    public void testAddAndGetValuePrimitive_2_oe() {
        final MutableLong mutableLong = new MutableLong(0L);
        final long result = mutableLong.addAndGet(1L);

        assertEquals(1L, mutableLong.longValue());
    }

    @Test
    public void testAddValueObject_1_oe() {
        final MutableLong mutNum = new MutableLong(1);
        mutNum.add(Long.valueOf(1));

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testAddValueObject_2_oe() {
        final MutableLong mutNum = new MutableLong(1);
        mutNum.add(Long.valueOf(1));

        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testAddValuePrimitive_1_oe() {
        final MutableLong mutNum = new MutableLong(1);
        mutNum.add(1);

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testAddValuePrimitive_2_oe() {
        final MutableLong mutNum = new MutableLong(1);
        mutNum.add(1);

        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testCompareTo_1_oe() {
        final MutableLong mutNum = new MutableLong(0);

        assertEquals(0, mutNum.compareTo(new MutableLong(0)));
    }

    @Test
    public void testCompareTo_2_oe() {
        final MutableLong mutNum = new MutableLong(0);

        assertEquals(+1, mutNum.compareTo(new MutableLong(-1)));
    }

    @Test
    public void testCompareTo_3_oe() {
        final MutableLong mutNum = new MutableLong(0);

        assertEquals(-1, mutNum.compareTo(new MutableLong(1)));
    }

    @Test
    public void testCompareToNull_1_oe() throws Exception {
        final MutableLong mutNum = new MutableLong(0);
        try {
    mutNum.compareTo(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testConstructorNull_1_oe() throws Exception {
        try {
    new MutableLong((Number) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testConstructors_1_oe() {
        assertEquals(0, new MutableLong().longValue());
    }

    @Test
    public void testConstructors_2_oe() {

        assertEquals(1, new MutableLong(1).longValue());
    }

    @Test
    public void testConstructors_3_oe() {


        assertEquals(2, new MutableLong(Long.valueOf(2)).longValue());
    }

    @Test
    public void testConstructors_4_oe() {


        assertEquals(3, new MutableLong(new MutableLong(3)).longValue());
    }

    @Test
    public void testConstructors_5_oe() {



        assertEquals(2, new MutableLong("2").longValue());
    }

    @Test
    public void testDecrement_1_oe() {
        final MutableLong mutNum = new MutableLong(1);
        mutNum.decrement();

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testDecrement_2_oe() {
        final MutableLong mutNum = new MutableLong(1);
        mutNum.decrement();

        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testDecrementAndGet_1_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        final long result = mutNum.decrementAndGet();

        assertEquals(0, result);
    }

    @Test
    public void testDecrementAndGet_2_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        final long result = mutNum.decrementAndGet();

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testDecrementAndGet_3_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        final long result = mutNum.decrementAndGet();

        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testEquals_1_oe() {
        final MutableLong mutNumA = new MutableLong(0);
        final MutableLong mutNumB = new MutableLong(0);
        final MutableLong mutNumC = new MutableLong(1);

        assertEquals(mutNumA, mutNumA);
    }

    @Test
    public void testEquals_2_oe() {
        final MutableLong mutNumA = new MutableLong(0);
        final MutableLong mutNumB = new MutableLong(0);
        final MutableLong mutNumC = new MutableLong(1);

        assertEquals(mutNumA, mutNumB);
    }

    @Test
    public void testEquals_3_oe() {
        final MutableLong mutNumA = new MutableLong(0);
        final MutableLong mutNumB = new MutableLong(0);
        final MutableLong mutNumC = new MutableLong(1);

        assertEquals(mutNumB, mutNumA);
    }

    @Test
    public void testEquals_4_oe() {
        final MutableLong mutNumA = new MutableLong(0);
        final MutableLong mutNumB = new MutableLong(0);
        final MutableLong mutNumC = new MutableLong(1);

        assertEquals(mutNumB, mutNumB);
    }

    @Test
    public void testEquals_5_oe() {
        final MutableLong mutNumA = new MutableLong(0);
        final MutableLong mutNumB = new MutableLong(0);
        final MutableLong mutNumC = new MutableLong(1);

        assertNotEquals(mutNumA, mutNumC);
    }

    @Test
    public void testEquals_6_oe() {
        final MutableLong mutNumA = new MutableLong(0);
        final MutableLong mutNumB = new MutableLong(0);
        final MutableLong mutNumC = new MutableLong(1);

        assertNotEquals(mutNumB, mutNumC);
    }

    @Test
    public void testEquals_7_oe() {
        final MutableLong mutNumA = new MutableLong(0);
        final MutableLong mutNumB = new MutableLong(0);
        final MutableLong mutNumC = new MutableLong(1);

        assertEquals(mutNumC, mutNumC);
    }

    @Test
    public void testEquals_8_oe() {
        final MutableLong mutNumA = new MutableLong(0);
        final MutableLong mutNumB = new MutableLong(0);
        final MutableLong mutNumC = new MutableLong(1);

        assertNotEquals(null, mutNumA);
    }

    @Test
    public void testEquals_9_oe() {
        final MutableLong mutNumA = new MutableLong(0);
        final MutableLong mutNumB = new MutableLong(0);
        final MutableLong mutNumC = new MutableLong(1);

        assertNotEquals(mutNumA, Long.valueOf(0));
    }

    @Test
    public void testEquals_10_oe() {
        final MutableLong mutNumA = new MutableLong(0);
        final MutableLong mutNumB = new MutableLong(0);
        final MutableLong mutNumC = new MutableLong(1);

        assertNotEquals("0", mutNumA);
    }

    @Test
    public void testGetAndAddValueObject_1_oe() {
        final MutableLong mutableLong = new MutableLong(0L);
        final long result = mutableLong.getAndAdd(Long.valueOf(1L));

        assertEquals(0L, result);
    }

    @Test
    public void testGetAndAddValueObject_2_oe() {
        final MutableLong mutableLong = new MutableLong(0L);
        final long result = mutableLong.getAndAdd(Long.valueOf(1L));

        assertEquals(1L, mutableLong.longValue());
    }

    @Test
    public void testGetAndAddValuePrimitive_1_oe() {
        final MutableLong mutableLong = new MutableLong(0L);
        final long result = mutableLong.getAndAdd(1L);

        assertEquals(0L, result);
    }

    @Test
    public void testGetAndAddValuePrimitive_2_oe() {
        final MutableLong mutableLong = new MutableLong(0L);
        final long result = mutableLong.getAndAdd(1L);

        assertEquals(1L, mutableLong.longValue());
    }

    @Test
    public void testGetAndDecrement_1_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        final long result = mutNum.getAndDecrement();

        assertEquals(1, result);
    }

    @Test
    public void testGetAndDecrement_2_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        final long result = mutNum.getAndDecrement();

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testGetAndDecrement_3_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        final long result = mutNum.getAndDecrement();

        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testGetAndIncrement_1_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        final long result = mutNum.getAndIncrement();

        assertEquals(1, result);
    }

    @Test
    public void testGetAndIncrement_2_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        final long result = mutNum.getAndIncrement();

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testGetAndIncrement_3_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        final long result = mutNum.getAndIncrement();

        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testGetSet_1_oe() {
        final MutableLong mutNum = new MutableLong(0);
        assertEquals(0, new MutableLong().longValue());
    }

    @Test
    public void testGetSet_2_oe() {
        final MutableLong mutNum = new MutableLong(0);
        assertEquals(Long.valueOf(0), new MutableLong().getValue());
    }

    @Test
    public void testGetSet_3_oe() {
        final MutableLong mutNum = new MutableLong(0);

        mutNum.setValue(1);
        assertEquals(1, mutNum.longValue());
    }

    @Test
    public void testGetSet_4_oe() {
        final MutableLong mutNum = new MutableLong(0);

        mutNum.setValue(1);
        assertEquals(Long.valueOf(1), mutNum.getValue());
    }

    @Test
    public void testGetSet_5_oe() {
        final MutableLong mutNum = new MutableLong(0);

        mutNum.setValue(1);

        mutNum.setValue(Long.valueOf(2));
        assertEquals(2, mutNum.longValue());
    }

    @Test
    public void testGetSet_6_oe() {
        final MutableLong mutNum = new MutableLong(0);

        mutNum.setValue(1);

        mutNum.setValue(Long.valueOf(2));
        assertEquals(Long.valueOf(2), mutNum.getValue());
    }

    @Test
    public void testGetSet_7_oe() {
        final MutableLong mutNum = new MutableLong(0);

        mutNum.setValue(1);

        mutNum.setValue(Long.valueOf(2));

        mutNum.setValue(new MutableLong(3));
        assertEquals(3, mutNum.longValue());
    }

    @Test
    public void testGetSet_8_oe() {
        final MutableLong mutNum = new MutableLong(0);

        mutNum.setValue(1);

        mutNum.setValue(Long.valueOf(2));

        mutNum.setValue(new MutableLong(3));
        assertEquals(Long.valueOf(3), mutNum.getValue());
    }

    @Test
    public void testHashCode_1_oe() {
        final MutableLong mutNumA = new MutableLong(0);
        final MutableLong mutNumB = new MutableLong(0);
        final MutableLong mutNumC = new MutableLong(1);

        assertEquals(mutNumA.hashCode(), mutNumA.hashCode());
    }

    @Test
    public void testHashCode_2_oe() {
        final MutableLong mutNumA = new MutableLong(0);
        final MutableLong mutNumB = new MutableLong(0);
        final MutableLong mutNumC = new MutableLong(1);

        assertEquals(mutNumA.hashCode(), mutNumB.hashCode());
    }

    @Test
    public void testHashCode_3_oe() {
        final MutableLong mutNumA = new MutableLong(0);
        final MutableLong mutNumB = new MutableLong(0);
        final MutableLong mutNumC = new MutableLong(1);

        assertNotEquals(mutNumA.hashCode(), mutNumC.hashCode());
    }

    @Test
    public void testHashCode_4_oe() {
        final MutableLong mutNumA = new MutableLong(0);
        final MutableLong mutNumB = new MutableLong(0);
        final MutableLong mutNumC = new MutableLong(1);

        assertEquals(mutNumA.hashCode(), Long.valueOf(0).hashCode());
    }

    @Test
    public void testIncrement_1_oe() {
        final MutableLong mutNum = new MutableLong(1);
        mutNum.increment();

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testIncrement_2_oe() {
        final MutableLong mutNum = new MutableLong(1);
        mutNum.increment();

        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testIncrementAndGet_1_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        final long result = mutNum.incrementAndGet();

        assertEquals(2, result);
    }

    @Test
    public void testIncrementAndGet_2_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        final long result = mutNum.incrementAndGet();

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testIncrementAndGet_3_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        final long result = mutNum.incrementAndGet();

        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testPrimitiveValues_1_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        assertEquals(1.0F, mutNum.floatValue());
    }

    @Test
    public void testPrimitiveValues_2_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        assertEquals(1.0, mutNum.doubleValue());
    }

    @Test
    public void testPrimitiveValues_3_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        assertEquals( (byte) 1, mutNum.byteValue() );
    }

    @Test
    public void testPrimitiveValues_4_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        assertEquals( (short) 1, mutNum.shortValue() );
    }

    @Test
    public void testPrimitiveValues_5_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        assertEquals( 1, mutNum.intValue() );
    }

    @Test
    public void testPrimitiveValues_6_oe() {
        final MutableLong mutNum = new MutableLong(1L);
        assertEquals( 1L, mutNum.longValue() );
    }

    @Test
    public void testSetNull_1_oe() throws Exception {
        final MutableLong mutNum = new MutableLong(0);
        try {
    mutNum.setValue(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testSubtractValueObject_1_oe() {
        final MutableLong mutNum = new MutableLong(1);
        mutNum.subtract(Long.valueOf(1));

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testSubtractValueObject_2_oe() {
        final MutableLong mutNum = new MutableLong(1);
        mutNum.subtract(Long.valueOf(1));

        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testSubtractValuePrimitive_1_oe() {
        final MutableLong mutNum = new MutableLong(1);
        mutNum.subtract(1);

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testSubtractValuePrimitive_2_oe() {
        final MutableLong mutNum = new MutableLong(1);
        mutNum.subtract(1);

        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testToLong_1_oe() {
        assertEquals(Long.valueOf(0L), new MutableLong(0L).toLong());
    }

    @Test
    public void testToLong_2_oe() {
        assertEquals(Long.valueOf(123L), new MutableLong(123L).toLong());
    }

    @Test
    public void testToString_1_oe() {
        assertEquals("0", new MutableLong(0).toString());
    }

    @Test
    public void testToString_2_oe() {
        assertEquals("10", new MutableLong(10).toString());
    }

    @Test
    public void testToString_3_oe() {
        assertEquals("-123", new MutableLong(-123).toString());
    }

}
