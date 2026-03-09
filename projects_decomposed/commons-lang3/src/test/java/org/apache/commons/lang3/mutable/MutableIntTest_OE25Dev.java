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
 * @see MutableInt
 */
public class MutableIntTest_OE25Dev {

    @Test
    public void testAddAndGetValueObject() {
        final MutableInt mutableInteger = new MutableInt(0);
        final int result = mutableInteger.addAndGet(Integer.valueOf(1));

        assertEquals(1, result);
        assertEquals(1, mutableInteger.intValue());
    }

    @Test
    public void testAddAndGetValuePrimitive() {
        final MutableInt mutableInteger = new MutableInt(0);
        final int result = mutableInteger.addAndGet(1);

        assertEquals(1, result);
        assertEquals(1, mutableInteger.intValue());
    }

    @Test
    public void testAddValueObject() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.add(Integer.valueOf(1));

        assertEquals(2, mutNum.intValue());
        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testAddValuePrimitive() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.add(1);

        assertEquals(2, mutNum.intValue());
        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testCompareTo() {
        final MutableInt mutNum = new MutableInt(0);

        assertEquals(0, mutNum.compareTo(new MutableInt(0)));
        assertEquals(+1, mutNum.compareTo(new MutableInt(-1)));
        assertEquals(-1, mutNum.compareTo(new MutableInt(1)));
    }

    @Test
    public void testCompareToNull() {
        final MutableInt mutNum = new MutableInt(0);
        assertThrows(NullPointerException.class, () -> mutNum.compareTo(null));
    }

    @Test
    public void testConstructorNull() {
        assertThrows(NullPointerException.class, () -> new MutableInt((Number) null));
    }

    // ----------------------------------------------------------------
    @Test
    public void testConstructors() {
        assertEquals(0, new MutableInt().intValue());

        assertEquals(1, new MutableInt(1).intValue());

        assertEquals(2, new MutableInt(Integer.valueOf(2)).intValue());
        assertEquals(3, new MutableInt(new MutableLong(3)).intValue());

        assertEquals(2, new MutableInt("2").intValue());

    }

    @Test
    public void testDecrement() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.decrement();

        assertEquals(0, mutNum.intValue());
        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testDecrementAndGet() {
        final MutableInt mutNum = new MutableInt(1);
        final int result = mutNum.decrementAndGet();

        assertEquals(0, result);
        assertEquals(0, mutNum.intValue());
        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testEquals() {
        this.testEquals(new MutableInt(0), new MutableInt(0), new MutableInt(1));
        // Should Numbers be supported? GaryG July-21-2005.
        //this.testEquals(mutNumA, Integer.valueOf(0), mutNumC);
    }

    /**
     * @param numA must not be a 0 Integer; must not equal numC.
     * @param numB must equal numA; must not equal numC.
     * @param numC must not equal numA; must not equal numC.
     */
    void testEquals(final Number numA, final Number numB, final Number numC) {
        assertEquals(numA, numA);
        assertEquals(numA, numB);
        assertEquals(numB, numA);
        assertEquals(numB, numB);
        assertNotEquals(numA, numC);
        assertNotEquals(numB, numC);
        assertEquals(numC, numC);
        assertNotEquals(null, numA);
        assertNotEquals(numA, Integer.valueOf(0));
        assertNotEquals("0", numA);
    }

    @Test
    public void testGetAndAddValueObject() {
        final MutableInt mutableInteger = new MutableInt(0);
        final int result = mutableInteger.getAndAdd(Integer.valueOf(1));

        assertEquals(0, result);
        assertEquals(1, mutableInteger.intValue());
    }

    @Test
    public void testGetAndAddValuePrimitive() {
        final MutableInt mutableInteger = new MutableInt(0);
        final int result = mutableInteger.getAndAdd(1);

        assertEquals(0, result);
        assertEquals(1, mutableInteger.intValue());
    }

    @Test
    public void testGetAndDecrement() {
        final MutableInt mutNum = new MutableInt(1);
        final int result = mutNum.getAndDecrement();

        assertEquals(1, result);
        assertEquals(0, mutNum.intValue());
        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testGetAndIncrement() {
        final MutableInt mutNum = new MutableInt(1);
        final int result = mutNum.getAndIncrement();

        assertEquals(1, result);
        assertEquals(2, mutNum.intValue());
        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testGetSet() {
        final MutableInt mutNum = new MutableInt(0);
        assertEquals(0, new MutableInt().intValue());
        assertEquals(Integer.valueOf(0), new MutableInt().getValue());

        mutNum.setValue(1);
        assertEquals(1, mutNum.intValue());
        assertEquals(Integer.valueOf(1), mutNum.getValue());

        mutNum.setValue(Integer.valueOf(2));
        assertEquals(2, mutNum.intValue());
        assertEquals(Integer.valueOf(2), mutNum.getValue());

        mutNum.setValue(new MutableLong(3));
        assertEquals(3, mutNum.intValue());
        assertEquals(Integer.valueOf(3), mutNum.getValue());
    }

    @Test
    public void testHashCode() {
        final MutableInt mutNumA = new MutableInt(0);
        final MutableInt mutNumB = new MutableInt(0);
        final MutableInt mutNumC = new MutableInt(1);

        assertEquals(mutNumA.hashCode(), mutNumA.hashCode());
        assertEquals(mutNumA.hashCode(), mutNumB.hashCode());
        assertNotEquals(mutNumA.hashCode(), mutNumC.hashCode());
        assertEquals(mutNumA.hashCode(), Integer.valueOf(0).hashCode());
    }

    @Test
    public void testIncrement() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.increment();

        assertEquals(2, mutNum.intValue());
        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testIncrementAndGet() {
        final MutableInt mutNum = new MutableInt(1);
        final int result = mutNum.incrementAndGet();

        assertEquals(2, result);
        assertEquals(2, mutNum.intValue());
        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testPrimitiveValues() {
        final MutableInt mutNum = new MutableInt(1);
        assertEquals( (byte) 1, mutNum.byteValue() );
        assertEquals( (short) 1, mutNum.shortValue() );
        assertEquals(1.0F, mutNum.floatValue());
        assertEquals(1.0, mutNum.doubleValue());
        assertEquals( 1L, mutNum.longValue() );
    }

    @Test
    public void testSetNull() {
        final MutableInt mutNum = new MutableInt(0);
        assertThrows(NullPointerException.class, () -> mutNum.setValue(null));
    }

    @Test
    public void testSubtractValueObject() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.subtract(Integer.valueOf(1));

        assertEquals(0, mutNum.intValue());
        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testSubtractValuePrimitive() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.subtract(1);

        assertEquals(0, mutNum.intValue());
        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testToInteger() {
        assertEquals(Integer.valueOf(0), new MutableInt(0).toInteger());
        assertEquals(Integer.valueOf(123), new MutableInt(123).toInteger());
    }

    @Test
    public void testToString() {
        assertEquals("0", new MutableInt(0).toString());
        assertEquals("10", new MutableInt(10).toString());
        assertEquals("-123", new MutableInt(-123).toString());
    }

    @Test
    public void testAddAndGetValueObject_1_oe() {
        final MutableInt mutableInteger = new MutableInt(0);
        final int result = mutableInteger.addAndGet(Integer.valueOf(1));

        assertEquals(1, result);
    }

    @Test
    public void testAddAndGetValueObject_2_oe() {
        final MutableInt mutableInteger = new MutableInt(0);
        final int result = mutableInteger.addAndGet(Integer.valueOf(1));

        assertEquals(1, mutableInteger.intValue());
    }

    @Test
    public void testAddAndGetValuePrimitive_1_oe() {
        final MutableInt mutableInteger = new MutableInt(0);
        final int result = mutableInteger.addAndGet(1);

        assertEquals(1, result);
    }

    @Test
    public void testAddAndGetValuePrimitive_2_oe() {
        final MutableInt mutableInteger = new MutableInt(0);
        final int result = mutableInteger.addAndGet(1);

        assertEquals(1, mutableInteger.intValue());
    }

    @Test
    public void testAddValueObject_1_oe() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.add(Integer.valueOf(1));

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testAddValueObject_2_oe() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.add(Integer.valueOf(1));

        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testAddValuePrimitive_1_oe() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.add(1);

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testAddValuePrimitive_2_oe() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.add(1);

        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testCompareTo_1_oe() {
        final MutableInt mutNum = new MutableInt(0);

        assertEquals(0, mutNum.compareTo(new MutableInt(0)));
    }

    @Test
    public void testCompareTo_2_oe() {
        final MutableInt mutNum = new MutableInt(0);

        assertEquals(+1, mutNum.compareTo(new MutableInt(-1)));
    }

    @Test
    public void testCompareTo_3_oe() {
        final MutableInt mutNum = new MutableInt(0);

        assertEquals(-1, mutNum.compareTo(new MutableInt(1)));
    }

    @Test
    public void testCompareToNull_1_oe() throws Exception {
        final MutableInt mutNum = new MutableInt(0);
        try {
    mutNum.compareTo(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testConstructorNull_1_oe() throws Exception {
        try {
    new MutableInt((Number) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testConstructors_1_oe() {
        assertEquals(0, new MutableInt().intValue());
    }

    @Test
    public void testConstructors_2_oe() {

        assertEquals(1, new MutableInt(1).intValue());
    }

    @Test
    public void testConstructors_3_oe() {


        assertEquals(2, new MutableInt(Integer.valueOf(2)).intValue());
    }

    @Test
    public void testConstructors_4_oe() {


        assertEquals(3, new MutableInt(new MutableLong(3)).intValue());
    }

    @Test
    public void testConstructors_5_oe() {



        assertEquals(2, new MutableInt("2").intValue());
    }

    @Test
    public void testDecrement_1_oe() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.decrement();

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testDecrement_2_oe() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.decrement();

        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testDecrementAndGet_1_oe() {
        final MutableInt mutNum = new MutableInt(1);
        final int result = mutNum.decrementAndGet();

        assertEquals(0, result);
    }

    @Test
    public void testDecrementAndGet_2_oe() {
        final MutableInt mutNum = new MutableInt(1);
        final int result = mutNum.decrementAndGet();

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testDecrementAndGet_3_oe() {
        final MutableInt mutNum = new MutableInt(1);
        final int result = mutNum.decrementAndGet();

        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testGetAndAddValueObject_1_oe() {
        final MutableInt mutableInteger = new MutableInt(0);
        final int result = mutableInteger.getAndAdd(Integer.valueOf(1));

        assertEquals(0, result);
    }

    @Test
    public void testGetAndAddValueObject_2_oe() {
        final MutableInt mutableInteger = new MutableInt(0);
        final int result = mutableInteger.getAndAdd(Integer.valueOf(1));

        assertEquals(1, mutableInteger.intValue());
    }

    @Test
    public void testGetAndAddValuePrimitive_1_oe() {
        final MutableInt mutableInteger = new MutableInt(0);
        final int result = mutableInteger.getAndAdd(1);

        assertEquals(0, result);
    }

    @Test
    public void testGetAndAddValuePrimitive_2_oe() {
        final MutableInt mutableInteger = new MutableInt(0);
        final int result = mutableInteger.getAndAdd(1);

        assertEquals(1, mutableInteger.intValue());
    }

    @Test
    public void testGetAndDecrement_1_oe() {
        final MutableInt mutNum = new MutableInt(1);
        final int result = mutNum.getAndDecrement();

        assertEquals(1, result);
    }

    @Test
    public void testGetAndDecrement_2_oe() {
        final MutableInt mutNum = new MutableInt(1);
        final int result = mutNum.getAndDecrement();

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testGetAndDecrement_3_oe() {
        final MutableInt mutNum = new MutableInt(1);
        final int result = mutNum.getAndDecrement();

        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testGetAndIncrement_1_oe() {
        final MutableInt mutNum = new MutableInt(1);
        final int result = mutNum.getAndIncrement();

        assertEquals(1, result);
    }

    @Test
    public void testGetAndIncrement_2_oe() {
        final MutableInt mutNum = new MutableInt(1);
        final int result = mutNum.getAndIncrement();

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testGetAndIncrement_3_oe() {
        final MutableInt mutNum = new MutableInt(1);
        final int result = mutNum.getAndIncrement();

        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testGetSet_1_oe() {
        final MutableInt mutNum = new MutableInt(0);
        assertEquals(0, new MutableInt().intValue());
    }

    @Test
    public void testGetSet_2_oe() {
        final MutableInt mutNum = new MutableInt(0);
        assertEquals(Integer.valueOf(0), new MutableInt().getValue());
    }

    @Test
    public void testGetSet_3_oe() {
        final MutableInt mutNum = new MutableInt(0);

        mutNum.setValue(1);
        assertEquals(1, mutNum.intValue());
    }

    @Test
    public void testGetSet_4_oe() {
        final MutableInt mutNum = new MutableInt(0);

        mutNum.setValue(1);
        assertEquals(Integer.valueOf(1), mutNum.getValue());
    }

    @Test
    public void testGetSet_5_oe() {
        final MutableInt mutNum = new MutableInt(0);

        mutNum.setValue(1);

        mutNum.setValue(Integer.valueOf(2));
        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testGetSet_6_oe() {
        final MutableInt mutNum = new MutableInt(0);

        mutNum.setValue(1);

        mutNum.setValue(Integer.valueOf(2));
        assertEquals(Integer.valueOf(2), mutNum.getValue());
    }

    @Test
    public void testGetSet_7_oe() {
        final MutableInt mutNum = new MutableInt(0);

        mutNum.setValue(1);

        mutNum.setValue(Integer.valueOf(2));

        mutNum.setValue(new MutableLong(3));
        assertEquals(3, mutNum.intValue());
    }

    @Test
    public void testGetSet_8_oe() {
        final MutableInt mutNum = new MutableInt(0);

        mutNum.setValue(1);

        mutNum.setValue(Integer.valueOf(2));

        mutNum.setValue(new MutableLong(3));
        assertEquals(Integer.valueOf(3), mutNum.getValue());
    }

    @Test
    public void testHashCode_1_oe() {
        final MutableInt mutNumA = new MutableInt(0);
        final MutableInt mutNumB = new MutableInt(0);
        final MutableInt mutNumC = new MutableInt(1);

        assertEquals(mutNumA.hashCode(), mutNumA.hashCode());
    }

    @Test
    public void testHashCode_2_oe() {
        final MutableInt mutNumA = new MutableInt(0);
        final MutableInt mutNumB = new MutableInt(0);
        final MutableInt mutNumC = new MutableInt(1);

        assertEquals(mutNumA.hashCode(), mutNumB.hashCode());
    }

    @Test
    public void testHashCode_3_oe() {
        final MutableInt mutNumA = new MutableInt(0);
        final MutableInt mutNumB = new MutableInt(0);
        final MutableInt mutNumC = new MutableInt(1);

        assertNotEquals(mutNumA.hashCode(), mutNumC.hashCode());
    }

    @Test
    public void testHashCode_4_oe() {
        final MutableInt mutNumA = new MutableInt(0);
        final MutableInt mutNumB = new MutableInt(0);
        final MutableInt mutNumC = new MutableInt(1);

        assertEquals(mutNumA.hashCode(), Integer.valueOf(0).hashCode());
    }

    @Test
    public void testIncrement_1_oe() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.increment();

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testIncrement_2_oe() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.increment();

        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testIncrementAndGet_1_oe() {
        final MutableInt mutNum = new MutableInt(1);
        final int result = mutNum.incrementAndGet();

        assertEquals(2, result);
    }

    @Test
    public void testIncrementAndGet_2_oe() {
        final MutableInt mutNum = new MutableInt(1);
        final int result = mutNum.incrementAndGet();

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testIncrementAndGet_3_oe() {
        final MutableInt mutNum = new MutableInt(1);
        final int result = mutNum.incrementAndGet();

        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testPrimitiveValues_1_oe() {
        final MutableInt mutNum = new MutableInt(1);
        assertEquals( (byte) 1, mutNum.byteValue() );
    }

    @Test
    public void testPrimitiveValues_2_oe() {
        final MutableInt mutNum = new MutableInt(1);
        assertEquals( (short) 1, mutNum.shortValue() );
    }

    @Test
    public void testPrimitiveValues_3_oe() {
        final MutableInt mutNum = new MutableInt(1);
        assertEquals(1.0F, mutNum.floatValue());
    }

    @Test
    public void testPrimitiveValues_4_oe() {
        final MutableInt mutNum = new MutableInt(1);
        assertEquals(1.0, mutNum.doubleValue());
    }

    @Test
    public void testPrimitiveValues_5_oe() {
        final MutableInt mutNum = new MutableInt(1);
        assertEquals( 1L, mutNum.longValue() );
    }

    @Test
    public void testSetNull_1_oe() throws Exception {
        final MutableInt mutNum = new MutableInt(0);
        try {
    mutNum.setValue(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testSubtractValueObject_1_oe() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.subtract(Integer.valueOf(1));

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testSubtractValueObject_2_oe() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.subtract(Integer.valueOf(1));

        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testSubtractValuePrimitive_1_oe() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.subtract(1);

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testSubtractValuePrimitive_2_oe() {
        final MutableInt mutNum = new MutableInt(1);
        mutNum.subtract(1);

        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testToInteger_1_oe() {
        assertEquals(Integer.valueOf(0), new MutableInt(0).toInteger());
    }

    @Test
    public void testToInteger_2_oe() {
        assertEquals(Integer.valueOf(123), new MutableInt(123).toInteger());
    }

    @Test
    public void testToString_1_oe() {
        assertEquals("0", new MutableInt(0).toString());
    }

    @Test
    public void testToString_2_oe() {
        assertEquals("10", new MutableInt(10).toString());
    }

    @Test
    public void testToString_3_oe() {
        assertEquals("-123", new MutableInt(-123).toString());
    }

}
