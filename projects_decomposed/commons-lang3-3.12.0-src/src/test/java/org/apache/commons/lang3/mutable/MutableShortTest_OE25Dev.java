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

/**
 * JUnit tests.
 *
 * @see MutableShort
 */
public class MutableShortTest_OE25Dev {

    // ----------------------------------------------------------------

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

        // removed other assertion
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

        // removed other assertion
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

        // removed other assertion
        assertEquals((short) +1, mutNum.compareTo(new MutableShort((short) -1)));
    }

    @Test
    public void testCompareTo_3_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);

        // removed other assertion
        // removed other assertion
        assertEquals((short) -1, mutNum.compareTo(new MutableShort((short) 1)));
    }

    @Test
    public void testCompareTo_4_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(NullPointerException.class, () -> mutNum.compareTo(null));
    }

    @Test
    public void testConstructors_1_oe() {
        assertEquals((short) 0, new MutableShort().shortValue());
    }

    @Test
    public void testConstructors_2_oe() {
        // removed other assertion

        assertEquals((short) 1, new MutableShort((short) 1).shortValue());
    }

    @Test
    public void testConstructors_3_oe() {
        // removed other assertion

        // removed other assertion

        assertEquals((short) 2, new MutableShort(Short.valueOf((short) 2)).shortValue());
    }

    @Test
    public void testConstructors_4_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertEquals((short) 3, new MutableShort(new MutableShort((short) 3)).shortValue());
    }

    @Test
    public void testConstructors_5_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals((short) 2, new MutableShort("2").shortValue());
    }

    @Test
    public void testConstructors_6_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertThrows(NullPointerException.class, () -> new MutableShort((Number) null));
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

        // removed other assertion
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

        // removed other assertion
        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testDecrementAndGet_3_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.decrementAndGet();

        // removed other assertion
        // removed other assertion
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

        // removed other assertion
        assertEquals(mutNumA, mutNumB);
    }

    @Test
    public void testEquals_3_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        // removed other assertion
        // removed other assertion
        assertEquals(mutNumB, mutNumA);
    }

    @Test
    public void testEquals_4_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(mutNumB, mutNumB);
    }

    @Test
    public void testEquals_5_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(mutNumA, mutNumC);
    }

    @Test
    public void testEquals_6_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(mutNumB, mutNumC);
    }

    @Test
    public void testEquals_7_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

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
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

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
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(mutNumA, Short.valueOf((short) 0));
    }

    @Test
    public void testEquals_10_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

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
        final MutableShort mutableShort = new MutableShort((short) 0);
        final short result = mutableShort.getAndAdd(Short.valueOf((short) 1));

        assertEquals((short) 0, result);
    }

    @Test
    public void testGetAndAddValueObject_2_oe() {
        final MutableShort mutableShort = new MutableShort((short) 0);
        final short result = mutableShort.getAndAdd(Short.valueOf((short) 1));

        // removed other assertion
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

        // removed other assertion
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

        // removed other assertion
        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testGetAndDecrement_3_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.getAndDecrement();

        // removed other assertion
        // removed other assertion
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

        // removed other assertion
        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testGetAndIncrement_3_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.getAndIncrement();

        // removed other assertion
        // removed other assertion
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
        // removed other assertion
        assertEquals(Short.valueOf((short) 0), new MutableShort().getValue());
    }

    @Test
    public void testGetSet_3_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);
        // removed other assertion
        // removed other assertion

        mutNum.setValue((short) 1);
        assertEquals((short) 1, mutNum.shortValue());
    }

    @Test
    public void testGetSet_4_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);
        // removed other assertion
        // removed other assertion

        mutNum.setValue((short) 1);
        // removed other assertion
        assertEquals(Short.valueOf((short) 1), mutNum.getValue());
    }

    @Test
    public void testGetSet_5_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);
        // removed other assertion
        // removed other assertion

        mutNum.setValue((short) 1);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(Short.valueOf((short) 2));
        assertEquals((short) 2, mutNum.shortValue());
    }

    @Test
    public void testGetSet_6_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);
        // removed other assertion
        // removed other assertion

        mutNum.setValue((short) 1);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(Short.valueOf((short) 2));
        // removed other assertion
        assertEquals(Short.valueOf((short) 2), mutNum.getValue());
    }

    @Test
    public void testGetSet_7_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);
        // removed other assertion
        // removed other assertion

        mutNum.setValue((short) 1);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(Short.valueOf((short) 2));
        // removed other assertion
        // removed other assertion

        mutNum.setValue(new MutableShort((short) 3));
        assertEquals((short) 3, mutNum.shortValue());
    }

    @Test
    public void testGetSet_8_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);
        // removed other assertion
        // removed other assertion

        mutNum.setValue((short) 1);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(Short.valueOf((short) 2));
        // removed other assertion
        // removed other assertion

        mutNum.setValue(new MutableShort((short) 3));
        // removed other assertion
        assertEquals(Short.valueOf((short) 3), mutNum.getValue());
    }

    @Test
    public void testGetSet_9_oe() {
        final MutableShort mutNum = new MutableShort((short) 0);
        // removed other assertion
        // removed other assertion

        mutNum.setValue((short) 1);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(Short.valueOf((short) 2));
        // removed other assertion
        // removed other assertion

        mutNum.setValue(new MutableShort((short) 3));
        // removed other assertion
        // removed other assertion
        assertThrows(NullPointerException.class, () -> mutNum.setValue(null));
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

        // removed other assertion
        assertEquals(mutNumA.hashCode(), mutNumB.hashCode());
    }

    @Test
    public void testHashCode_3_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        // removed other assertion
        // removed other assertion
        assertNotEquals(mutNumA.hashCode(), mutNumC.hashCode());
    }

    @Test
    public void testHashCode_4_oe() {
        final MutableShort mutNumA = new MutableShort((short) 0);
        final MutableShort mutNumB = new MutableShort((short) 0);
        final MutableShort mutNumC = new MutableShort((short) 1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
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

        // removed other assertion
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

        // removed other assertion
        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testIncrementAndGet_3_oe() {
        final MutableShort mutNum = new MutableShort((short) 1);
        final short result = mutNum.incrementAndGet();

        // removed other assertion
        // removed other assertion
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
        // removed other assertion
        assertEquals(1.0, mutNum.doubleValue());
    }

    @Test
    public void testPrimitiveValues_3_oe() {
        final MutableShort mutNum = new MutableShort( (short) 1 );
        // removed other assertion
        // removed other assertion
        assertEquals( (byte) 1, mutNum.byteValue() );
    }

    @Test
    public void testPrimitiveValues_4_oe() {
        final MutableShort mutNum = new MutableShort( (short) 1 );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( (short) 1, mutNum.shortValue() );
    }

    @Test
    public void testPrimitiveValues_5_oe() {
        final MutableShort mutNum = new MutableShort( (short) 1 );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 1, mutNum.intValue() );
    }

    @Test
    public void testPrimitiveValues_6_oe() {
        final MutableShort mutNum = new MutableShort( (short) 1 );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // removed other assertion
        assertEquals(Short.valueOf((short) 123), new MutableShort((short) 123).toShort());
    }

    @Test
    public void testToString_1_oe() {
        assertEquals("0", new MutableShort((short) 0).toString());
    }

    @Test
    public void testToString_2_oe() {
        // removed other assertion
        assertEquals("10", new MutableShort((short) 10).toString());
    }

    @Test
    public void testToString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("-123", new MutableShort((short) -123).toString());
    }

}
