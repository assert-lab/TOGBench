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
 * @see MutableShort
 */
public class MutableShortTest_OE25Dev {

    @Test
    public void testAddAndGetValueObject() {
        final MutableShort mutableShort = new MutableShort((short) 0);
        final short result = mutableShort.addAndGet(Short.valueOf((short) 1));

        assertEquals((short) 1, result);
        assertEquals((short) 1, mutableShort.shortValue());
    }

    @Test
    public void testAddAndGetValuePrimitive() {
        final MutableShort mutableShort = new MutableShort((short) 0);
        final short result = mutableShort.addAndGet((short) 1);

        assertEquals((short) 1, result);
        assertEquals((short) 1, mutableShort.shortValue());
    }

    @Test
    public void testAddValueObject() {
        final MutableShort mutNum = new MutableShort((short) 1);
        mutNum.add(Short.valueOf((short) 1));

        assertEquals((short) 2, mutNum.shortValue());
    }

    @Test
    public void testAddValuePrimitive() {
        final MutableShort mutNum = new MutableShort((short) 1);
        mutNum.add((short) 1);

        assertEquals((short) 2, mutNum.shortValue());
    }

    @Test
    public void testCompareTo() {
        final MutableShort mutNum = new MutableShort((short) 0);

        assertEquals((short) 0, mutNum.compareTo(new MutableShort((short) 0)));
        assertEquals((short) +1, mutNum.compareTo(new MutableShort((short) -1)));
        assertEquals((short) -1, mutNum.compareTo(new MutableShort((short) 1)));
        assertThrows(NullPointerException.class, () -> mutNum.compareTo(null));
    }

    // ----------------------------------------------------------------
    @Test
    public void testConstructors() {
        assertEquals((short) 0, new MutableShort().shortValue());

        assertEquals((short) 1, new MutableShort((short) 1).shortValue());

        assertEquals((short) 2, new MutableShort(Short.valueOf((short) 2)).shortValue());
        assertEquals((short) 3, new MutableShort(new MutableShort((short) 3)).shortValue());

        assertEquals((short) 2, new MutableShort("2").shortValue());

        assertThrows(NullPointerException.class, () -> new MutableShort((Number) null));
    }

    @Test
    public void testDecrement() {
        final MutableShort mutNum = new MutableShort((short) 1);
        mutNum.decrement();

        assertEquals(0, mutNum.intValue());
        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testDecrementAndGet() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.decrementAndGet();

        assertEquals(0, result);
        assertEquals(0, mutNum.intValue());
        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testEquals() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        assertEquals(mutNumA, mutNumA);
        assertEquals(mutNumA, mutNumB);
        assertEquals(mutNumB, mutNumA);
        assertEquals(mutNumB, mutNumB);
        assertNotEquals(mutNumA, mutNumC);
        assertNotEquals(mutNumB, mutNumC);
        assertEquals(mutNumC, mutNumC);
        assertNotEquals(null, mutNumA);
        assertNotEquals(mutNumA, Short.valueOf((short) 0));
        assertNotEquals("0", mutNumA);
    }

    @Test
    public void testGetAndAddValueObject() {
        final MutableShort mutableShort = new MutableShort((short) 0);
        final short result = mutableShort.getAndAdd(Short.valueOf((short) 1));

        assertEquals((short) 0, result);
        assertEquals((short) 1, mutableShort.shortValue());
    }

    @Test
    public void testGetAndAddValuePrimitive() {
        final MutableShort mutableShort = new MutableShort((short) 0);
        final short result = mutableShort.getAndAdd((short) 1);

        assertEquals((short) 0, result);
        assertEquals((short) 1, mutableShort.shortValue());
    }

    @Test
    public void testGetAndDecrement() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.getAndDecrement();

        assertEquals(1, result);
        assertEquals(0, mutNum.intValue());
        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testGetAndIncrement() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.getAndIncrement();

        assertEquals(1, result);
        assertEquals(2, mutNum.intValue());
        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testGetSet() {
        final MutableShort mutNum = new MutableShort((short) 0);
        assertEquals((short) 0, new MutableShort().shortValue());
        assertEquals(Short.valueOf((short) 0), new MutableShort().getValue());

        mutNum.setValue((short) 1);
        assertEquals((short) 1, mutNum.shortValue());
        assertEquals(Short.valueOf((short) 1), mutNum.getValue());

        mutNum.setValue(Short.valueOf((short) 2));
        assertEquals((short) 2, mutNum.shortValue());
        assertEquals(Short.valueOf((short) 2), mutNum.getValue());

        mutNum.setValue(new MutableShort((short) 3));
        assertEquals((short) 3, mutNum.shortValue());
        assertEquals(Short.valueOf((short) 3), mutNum.getValue());
        assertThrows(NullPointerException.class, () -> mutNum.setValue(null));
    }

    @Test
    public void testHashCode() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        assertEquals(mutNumA.hashCode(), mutNumA.hashCode());
        assertEquals(mutNumA.hashCode(), mutNumB.hashCode());
        assertNotEquals(mutNumA.hashCode(), mutNumC.hashCode());
        assertEquals(mutNumA.hashCode(), Short.valueOf((short) 0).hashCode());
    }

    @Test
    public void testIncrement() {
        final MutableShort mutNum = new MutableShort((short) 1);
        mutNum.increment();

        assertEquals(2, mutNum.intValue());
        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testIncrementAndGet() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.incrementAndGet();

        assertEquals(2, result);
        assertEquals(2, mutNum.intValue());
        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testPrimitiveValues() {
        final MutableShort mutNum = new MutableShort( (short) 1 );
        assertEquals(1.0F, mutNum.floatValue());
        assertEquals(1.0, mutNum.doubleValue());
        assertEquals( (byte) 1, mutNum.byteValue() );
        assertEquals( (short) 1, mutNum.shortValue() );
        assertEquals( 1, mutNum.intValue() );
        assertEquals( 1L, mutNum.longValue() );
    }

    @Test
    public void testSubtractValueObject() {
        final MutableShort mutNum = new MutableShort((short) 1);
        mutNum.subtract(Short.valueOf((short) 1));

        assertEquals((short) 0, mutNum.shortValue());
    }

    @Test
    public void testSubtractValuePrimitive() {
        final MutableShort mutNum = new MutableShort((short) 1);
        mutNum.subtract((short) 1);

        assertEquals((short) 0, mutNum.shortValue());
    }

    @Test
    public void testToShort() {
        assertEquals(Short.valueOf((short) 0), new MutableShort((short) 0).toShort());
        assertEquals(Short.valueOf((short) 123), new MutableShort((short) 123).toShort());
    }

    @Test
    public void testToString() {
        assertEquals("0", new MutableShort((short) 0).toString());
        assertEquals("10", new MutableShort((short) 10).toString());
        assertEquals("-123", new MutableShort((short) -123).toString());
    }

    @Test
    public void testAddAndGetValueObject_1_oe() {
        final MutableShort mutableShort = new MutableShort((short) 0);
        final short result = mutableShort.addAndGet(Short.valueOf((short) 1));

        assertEquals((short) 1, result);
    }

    @Test
    public void testAddAndGetValueObject_2_oe() {
        final MutableShort mutableShort = new MutableShort((short) 0);
        final short result = mutableShort.addAndGet(Short.valueOf((short) 1));

        assertEquals((short) 1, mutableShort.shortValue());
    }

    @Test
    public void testAddAndGetValuePrimitive_1_oe() {
        final MutableShort mutableShort = new MutableShort((short) 0);
        final short result = mutableShort.addAndGet((short) 1);

        assertEquals((short) 1, result);
    }

    @Test
    public void testAddAndGetValuePrimitive_2_oe() {
        final MutableShort mutableShort = new MutableShort((short) 0);
        final short result = mutableShort.addAndGet((short) 1);

        assertEquals((short) 1, mutableShort.shortValue());
    }

    @Test
    public void testAddValueObject_1_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        mutNum.add(Short.valueOf((short) 1));

        assertEquals((short) 2, mutNum.shortValue());
    }

    @Test
    public void testAddValuePrimitive_1_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        mutNum.add((short) 1);

        assertEquals((short) 2, mutNum.shortValue());
    }

    @Test
    public void testCompareTo_1_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);

        assertEquals((short) 0, mutNum.compareTo(new MutableShort((short) 0)));
    }

    @Test
    public void testCompareTo_2_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);

        assertEquals((short) +1, mutNum.compareTo(new MutableShort((short) -1)));
    }

    @Test
    public void testCompareTo_3_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);

        assertEquals((short) -1, mutNum.compareTo(new MutableShort((short) 1)));
    }

    @Test
    public void testCompareTo_4_oe() throws Exception {
        final MutableShort mutNum = new MutableShort((short) 0);

        try {
    mutNum.compareTo(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testConstructors_1_oe() {
        assertEquals((short) 0, new MutableShort().shortValue());
    }

    @Test
    public void testConstructors_2_oe() {

        assertEquals((short) 1, new MutableShort((short) 1).shortValue());
    }

    @Test
    public void testConstructors_3_oe() {


        assertEquals((short) 2, new MutableShort(Short.valueOf((short) 2)).shortValue());
    }

    @Test
    public void testConstructors_4_oe() {


        assertEquals((short) 3, new MutableShort(new MutableShort((short) 3)).shortValue());
    }

    @Test
    public void testConstructors_5_oe() {



        assertEquals((short) 2, new MutableShort("2").shortValue());
    }

    @Test
    public void testConstructors_6_oe() throws Exception {




        try {
    new MutableShort((Number) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testDecrement_1_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        mutNum.decrement();

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testDecrement_2_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        mutNum.decrement();

        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testDecrementAndGet_1_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.decrementAndGet();

        assertEquals(0, result);
    }

    @Test
    public void testDecrementAndGet_2_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.decrementAndGet();

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testDecrementAndGet_3_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.decrementAndGet();

        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testEquals_1_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        assertEquals(mutNumA, mutNumA);
    }

    @Test
    public void testEquals_2_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        assertEquals(mutNumA, mutNumB);
    }

    @Test
    public void testEquals_3_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        assertEquals(mutNumB, mutNumA);
    }

    @Test
    public void testEquals_4_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        assertEquals(mutNumB, mutNumB);
    }

    @Test
    public void testEquals_5_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        assertNotEquals(mutNumA, mutNumC);
    }

    @Test
    public void testEquals_6_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        assertNotEquals(mutNumB, mutNumC);
    }

    @Test
    public void testEquals_7_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        assertEquals(mutNumC, mutNumC);
    }

    @Test
    public void testEquals_8_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        assertNotEquals(null, mutNumA);
    }

    @Test
    public void testEquals_9_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        assertNotEquals(mutNumA, Short.valueOf((short) 0));
    }

    @Test
    public void testEquals_10_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        assertNotEquals("0", mutNumA);
    }

    @Test
    public void testGetAndAddValueObject_1_oe() {
        final MutableShort mutableShort = new MutableShort((short) 0);
        final short result = mutableShort.getAndAdd(Short.valueOf((short) 1));

        assertEquals((short) 0, result);
    }

    @Test
    public void testGetAndAddValueObject_2_oe() {
        final MutableShort mutableShort = new MutableShort((short) 0);
        final short result = mutableShort.getAndAdd(Short.valueOf((short) 1));

        assertEquals((short) 1, mutableShort.shortValue());
    }

    @Test
    public void testGetAndAddValuePrimitive_1_oe() {
        final MutableShort mutableShort = new MutableShort((short) 0);
        final short result = mutableShort.getAndAdd((short) 1);

        assertEquals((short) 0, result);
    }

    @Test
    public void testGetAndAddValuePrimitive_2_oe() {
        final MutableShort mutableShort = new MutableShort((short) 0);
        final short result = mutableShort.getAndAdd((short) 1);

        assertEquals((short) 1, mutableShort.shortValue());
    }

    @Test
    public void testGetAndDecrement_1_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.getAndDecrement();

        assertEquals(1, result);
    }

    @Test
    public void testGetAndDecrement_2_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.getAndDecrement();

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testGetAndDecrement_3_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.getAndDecrement();

        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testGetAndIncrement_1_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.getAndIncrement();

        assertEquals(1, result);
    }

    @Test
    public void testGetAndIncrement_2_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.getAndIncrement();

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testGetAndIncrement_3_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.getAndIncrement();

        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testGetSet_1_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);
        assertEquals((short) 0, new MutableShort().shortValue());
    }

    @Test
    public void testGetSet_2_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);
        assertEquals(Short.valueOf((short) 0), new MutableShort().getValue());
    }

    @Test
    public void testGetSet_3_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);

        mutNum.setValue((short) 1);
        assertEquals((short) 1, mutNum.shortValue());
    }

    @Test
    public void testGetSet_4_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);

        mutNum.setValue((short) 1);
        assertEquals(Short.valueOf((short) 1), mutNum.getValue());
    }

    @Test
    public void testGetSet_5_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);

        mutNum.setValue((short) 1);

        mutNum.setValue(Short.valueOf((short) 2));
        assertEquals((short) 2, mutNum.shortValue());
    }

    @Test
    public void testGetSet_6_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);

        mutNum.setValue((short) 1);

        mutNum.setValue(Short.valueOf((short) 2));
        assertEquals(Short.valueOf((short) 2), mutNum.getValue());
    }

    @Test
    public void testGetSet_7_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);

        mutNum.setValue((short) 1);

        mutNum.setValue(Short.valueOf((short) 2));

        mutNum.setValue(new MutableShort((short) 3));
        assertEquals((short) 3, mutNum.shortValue());
    }

    @Test
    public void testGetSet_8_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);

        mutNum.setValue((short) 1);

        mutNum.setValue(Short.valueOf((short) 2));

        mutNum.setValue(new MutableShort((short) 3));
        assertEquals(Short.valueOf((short) 3), mutNum.getValue());
    }

    @Test
    public void testGetSet_9_oe() throws Exception {
        final MutableShort mutNum = new MutableShort((short) 0);

        mutNum.setValue((short) 1);

        mutNum.setValue(Short.valueOf((short) 2));

        mutNum.setValue(new MutableShort((short) 3));
        try {
    mutNum.setValue(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testHashCode_1_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        assertEquals(mutNumA.hashCode(), mutNumA.hashCode());
    }

    @Test
    public void testHashCode_2_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        assertEquals(mutNumA.hashCode(), mutNumB.hashCode());
    }

    @Test
    public void testHashCode_3_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        assertNotEquals(mutNumA.hashCode(), mutNumC.hashCode());
    }

    @Test
    public void testHashCode_4_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        assertEquals(mutNumA.hashCode(), Short.valueOf((short) 0).hashCode());
    }

    @Test
    public void testIncrement_1_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        mutNum.increment();

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testIncrement_2_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        mutNum.increment();

        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testIncrementAndGet_1_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.incrementAndGet();

        assertEquals(2, result);
    }

    @Test
    public void testIncrementAndGet_2_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.incrementAndGet();

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testIncrementAndGet_3_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.incrementAndGet();

        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testPrimitiveValues_1_oe() {
        final MutableShort mutNum = new MutableShort( (short) 1 );
        assertEquals(1.0F, mutNum.floatValue());
    }

    @Test
    public void testPrimitiveValues_2_oe() {
        final MutableShort mutNum = new MutableShort( (short) 1 );
        assertEquals(1.0, mutNum.doubleValue());
    }

    @Test
    public void testPrimitiveValues_3_oe() {
        final MutableShort mutNum = new MutableShort( (short) 1 );
        assertEquals( (byte) 1, mutNum.byteValue() );
    }

    @Test
    public void testPrimitiveValues_4_oe() {
        final MutableShort mutNum = new MutableShort( (short) 1 );
        assertEquals( (short) 1, mutNum.shortValue() );
    }

    @Test
    public void testPrimitiveValues_5_oe() {
        final MutableShort mutNum = new MutableShort( (short) 1 );
        assertEquals( 1, mutNum.intValue() );
    }

    @Test
    public void testPrimitiveValues_6_oe() {
        final MutableShort mutNum = new MutableShort( (short) 1 );
        assertEquals( 1L, mutNum.longValue() );
    }

    @Test
    public void testSubtractValueObject_1_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        mutNum.subtract(Short.valueOf((short) 1));

        assertEquals((short) 0, mutNum.shortValue());
    }

    @Test
    public void testSubtractValuePrimitive_1_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        mutNum.subtract((short) 1);

        assertEquals((short) 0, mutNum.shortValue());
    }

    @Test
    public void testToShort_1_oe() {
        assertEquals(Short.valueOf((short) 0), new MutableShort((short) 0).toShort());
    }

    @Test
    public void testToShort_2_oe() {
        assertEquals(Short.valueOf((short) 123), new MutableShort((short) 123).toShort());
    }

    @Test
    public void testToString_1_oe() {
        assertEquals("0", new MutableShort((short) 0).toString());
    }

    @Test
    public void testToString_2_oe() {
        assertEquals("10", new MutableShort((short) 10).toString());
    }

    @Test
    public void testToString_3_oe() {
        assertEquals("-123", new MutableShort((short) -123).toString());
    }

}
