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
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * JUnit tests.
 *
 * @see MutableFloat
 */
public class MutableFloatTest_OE25Dev {

    // ----------------------------------------------------------------

    @Test
    public void testAddAndGetValueObject_1_oe() {
        final MutableFloat mutableFloat = new MutableFloat(5f);
        final float result = mutableFloat.addAndGet(Float.valueOf(2.5f));

        assertEquals(7.5f, result, 0.01f);
    }

    @Test
    public void testAddAndGetValueObject_2_oe() {
        final MutableFloat mutableFloat = new MutableFloat(5f);
        final float result = mutableFloat.addAndGet(Float.valueOf(2.5f));

        assertEquals(7.5f, mutableFloat.floatValue(), 0.01f);
    }

    @Test
    public void testAddAndGetValuePrimitive_1_oe() {
        final MutableFloat mutableFloat = new MutableFloat(0.5f);
        final float result = mutableFloat.addAndGet(1f);

        assertEquals(1.5f, result, 0.01f);
    }

    @Test
    public void testAddAndGetValuePrimitive_2_oe() {
        final MutableFloat mutableFloat = new MutableFloat(0.5f);
        final float result = mutableFloat.addAndGet(1f);

        assertEquals(1.5f, mutableFloat.floatValue(), 0.01f);
    }

    @Test
    public void testAddValueObject_1_oe() {
        final MutableFloat mutNum = new MutableFloat(1);
        mutNum.add(Float.valueOf(1.1f));

        assertEquals(2.1f, mutNum.floatValue(), 0.01f);
    }

    @Test
    public void testAddValuePrimitive_1_oe() {
        final MutableFloat mutNum = new MutableFloat(1);
        mutNum.add(1.1f);

        assertEquals(2.1f, mutNum.floatValue(), 0.01f);
    }

    @Test
    public void testCompareTo_1_oe() {
        final MutableFloat mutNum = new MutableFloat(0f);

        assertEquals(0, mutNum.compareTo(new MutableFloat(0f)));
    }

    @Test
    public void testCompareTo_2_oe() {
        final MutableFloat mutNum = new MutableFloat(0f);

        assertEquals(+1, mutNum.compareTo(new MutableFloat(-1f)));
    }

    @Test
    public void testCompareTo_3_oe() {
        final MutableFloat mutNum = new MutableFloat(0f);

        assertEquals(-1, mutNum.compareTo(new MutableFloat(1f)));
    }

    @Test
    public void testCompareToNull_1_oe() throws Exception {
        final MutableFloat mutNum = new MutableFloat(0f);
        try {
    mutNum.compareTo(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testConstructorNull_1_oe() throws Exception {
        try {
    new MutableFloat((Number) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testConstructors_1_oe() {
        assertEquals(0f, new MutableFloat().floatValue(), 0.0001f);
    }

    @Test
    public void testConstructors_2_oe() {

        assertEquals(1f, new MutableFloat(1f).floatValue(), 0.0001f);
    }

    @Test
    public void testConstructors_3_oe() {


        assertEquals(2f, new MutableFloat(Float.valueOf(2f)).floatValue(), 0.0001f);
    }

    @Test
    public void testConstructors_4_oe() {


        assertEquals(3f, new MutableFloat(new MutableFloat(3f)).floatValue(), 0.0001f);
    }

    @Test
    public void testConstructors_5_oe() {



        assertEquals(2f, new MutableFloat("2.0").floatValue(), 0.0001f);
    }

    @Test
    public void testDecrement_1_oe() {
        final MutableFloat mutNum = new MutableFloat(1);
        mutNum.decrement();

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testDecrement_2_oe() {
        final MutableFloat mutNum = new MutableFloat(1);
        mutNum.decrement();

        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testDecrementAndGet_1_oe() {
        final MutableFloat mutNum = new MutableFloat(1f);
        final float result = mutNum.decrementAndGet();

        assertEquals(0f, result, 0.01f);
    }

    @Test
    public void testDecrementAndGet_2_oe() {
        final MutableFloat mutNum = new MutableFloat(1f);
        final float result = mutNum.decrementAndGet();

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testDecrementAndGet_3_oe() {
        final MutableFloat mutNum = new MutableFloat(1f);
        final float result = mutNum.decrementAndGet();

        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testEquals_1_oe() {
        final MutableFloat mutNumA = new MutableFloat(0f);
        final MutableFloat mutNumB = new MutableFloat(0f);
        final MutableFloat mutNumC = new MutableFloat(1f);

        assertEquals(mutNumA, mutNumA);
    }

    @Test
    public void testEquals_2_oe() {
        final MutableFloat mutNumA = new MutableFloat(0f);
        final MutableFloat mutNumB = new MutableFloat(0f);
        final MutableFloat mutNumC = new MutableFloat(1f);

        assertEquals(mutNumA, mutNumB);
    }

    @Test
    public void testEquals_3_oe() {
        final MutableFloat mutNumA = new MutableFloat(0f);
        final MutableFloat mutNumB = new MutableFloat(0f);
        final MutableFloat mutNumC = new MutableFloat(1f);

        assertEquals(mutNumB, mutNumA);
    }

    @Test
    public void testEquals_4_oe() {
        final MutableFloat mutNumA = new MutableFloat(0f);
        final MutableFloat mutNumB = new MutableFloat(0f);
        final MutableFloat mutNumC = new MutableFloat(1f);

        assertEquals(mutNumB, mutNumB);
    }

    @Test
    public void testEquals_5_oe() {
        final MutableFloat mutNumA = new MutableFloat(0f);
        final MutableFloat mutNumB = new MutableFloat(0f);
        final MutableFloat mutNumC = new MutableFloat(1f);

        assertNotEquals(mutNumA, mutNumC);
    }

    @Test
    public void testEquals_6_oe() {
        final MutableFloat mutNumA = new MutableFloat(0f);
        final MutableFloat mutNumB = new MutableFloat(0f);
        final MutableFloat mutNumC = new MutableFloat(1f);

        assertNotEquals(mutNumB, mutNumC);
    }

    @Test
    public void testEquals_7_oe() {
        final MutableFloat mutNumA = new MutableFloat(0f);
        final MutableFloat mutNumB = new MutableFloat(0f);
        final MutableFloat mutNumC = new MutableFloat(1f);

        assertEquals(mutNumC, mutNumC);
    }

    @Test
    public void testEquals_8_oe() {
        final MutableFloat mutNumA = new MutableFloat(0f);
        final MutableFloat mutNumB = new MutableFloat(0f);
        final MutableFloat mutNumC = new MutableFloat(1f);

        assertNotEquals(null, mutNumA);
    }

    @Test
    public void testEquals_9_oe() {
        final MutableFloat mutNumA = new MutableFloat(0f);
        final MutableFloat mutNumB = new MutableFloat(0f);
        final MutableFloat mutNumC = new MutableFloat(1f);

        assertNotEquals(mutNumA, Float.valueOf(0f));
    }

    @Test
    public void testEquals_10_oe() {
        final MutableFloat mutNumA = new MutableFloat(0f);
        final MutableFloat mutNumB = new MutableFloat(0f);
        final MutableFloat mutNumC = new MutableFloat(1f);

        assertNotEquals("0", mutNumA);
    }

    @Test
    public void testGetAndAddValueObject_1_oe() {
        final MutableFloat mutableFloat = new MutableFloat(7.75f);
        final float result = mutableFloat.getAndAdd(Float.valueOf(2.25f));

        assertEquals(7.75f, result, 0.01f);
    }

    @Test
    public void testGetAndAddValueObject_2_oe() {
        final MutableFloat mutableFloat = new MutableFloat(7.75f);
        final float result = mutableFloat.getAndAdd(Float.valueOf(2.25f));

        assertEquals(10f, mutableFloat.floatValue(), 0.01f);
    }

    @Test
    public void testGetAndAddValuePrimitive_1_oe() {
        final MutableFloat mutableFloat = new MutableFloat(1.25f);
        final float result = mutableFloat.getAndAdd(0.75f);

        assertEquals(1.25f, result, 0.01f);
    }

    @Test
    public void testGetAndAddValuePrimitive_2_oe() {
        final MutableFloat mutableFloat = new MutableFloat(1.25f);
        final float result = mutableFloat.getAndAdd(0.75f);

        assertEquals(2f, mutableFloat.floatValue(), 0.01f);
    }

    @Test
    public void testGetAndDecrement_1_oe() {
        final MutableFloat mutNum = new MutableFloat(1f);
        final float result = mutNum.getAndDecrement();

        assertEquals(1f, result, 0.01f);
    }

    @Test
    public void testGetAndDecrement_2_oe() {
        final MutableFloat mutNum = new MutableFloat(1f);
        final float result = mutNum.getAndDecrement();

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testGetAndDecrement_3_oe() {
        final MutableFloat mutNum = new MutableFloat(1f);
        final float result = mutNum.getAndDecrement();

        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testGetAndIncrement_1_oe() {
        final MutableFloat mutNum = new MutableFloat(1f);
        final float result = mutNum.getAndIncrement();

        assertEquals(1f, result, 0.01f);
    }

    @Test
    public void testGetAndIncrement_2_oe() {
        final MutableFloat mutNum = new MutableFloat(1f);
        final float result = mutNum.getAndIncrement();

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testGetAndIncrement_3_oe() {
        final MutableFloat mutNum = new MutableFloat(1f);
        final float result = mutNum.getAndIncrement();

        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testGetSet_1_oe() {
        final MutableFloat mutNum = new MutableFloat(0f);
        assertEquals(0f, new MutableFloat().floatValue(), 0.0001f);
    }

    @Test
    public void testGetSet_2_oe() {
        final MutableFloat mutNum = new MutableFloat(0f);
        assertEquals(Float.valueOf(0), new MutableFloat().getValue());
    }

    @Test
    public void testGetSet_3_oe() {
        final MutableFloat mutNum = new MutableFloat(0f);

        mutNum.setValue(1);
        assertEquals(1f, mutNum.floatValue(), 0.0001f);
    }

    @Test
    public void testGetSet_4_oe() {
        final MutableFloat mutNum = new MutableFloat(0f);

        mutNum.setValue(1);
        assertEquals(Float.valueOf(1f), mutNum.getValue());
    }

    @Test
    public void testGetSet_5_oe() {
        final MutableFloat mutNum = new MutableFloat(0f);

        mutNum.setValue(1);

        mutNum.setValue(Float.valueOf(2f));
        assertEquals(2f, mutNum.floatValue(), 0.0001f);
    }

    @Test
    public void testGetSet_6_oe() {
        final MutableFloat mutNum = new MutableFloat(0f);

        mutNum.setValue(1);

        mutNum.setValue(Float.valueOf(2f));
        assertEquals(Float.valueOf(2f), mutNum.getValue());
    }

    @Test
    public void testGetSet_7_oe() {
        final MutableFloat mutNum = new MutableFloat(0f);

        mutNum.setValue(1);

        mutNum.setValue(Float.valueOf(2f));

        mutNum.setValue(new MutableFloat(3f));
        assertEquals(3f, mutNum.floatValue(), 0.0001f);
    }

    @Test
    public void testGetSet_8_oe() {
        final MutableFloat mutNum = new MutableFloat(0f);

        mutNum.setValue(1);

        mutNum.setValue(Float.valueOf(2f));

        mutNum.setValue(new MutableFloat(3f));
        assertEquals(Float.valueOf(3f), mutNum.getValue());
    }

    @Test
    public void testHashCode_1_oe() {
        final MutableFloat mutNumA = new MutableFloat(0f);
        final MutableFloat mutNumB = new MutableFloat(0f);
        final MutableFloat mutNumC = new MutableFloat(1f);

        assertEquals(mutNumA.hashCode(), mutNumA.hashCode());
    }

    @Test
    public void testHashCode_2_oe() {
        final MutableFloat mutNumA = new MutableFloat(0f);
        final MutableFloat mutNumB = new MutableFloat(0f);
        final MutableFloat mutNumC = new MutableFloat(1f);

        assertEquals(mutNumA.hashCode(), mutNumB.hashCode());
    }

    @Test
    public void testHashCode_3_oe() {
        final MutableFloat mutNumA = new MutableFloat(0f);
        final MutableFloat mutNumB = new MutableFloat(0f);
        final MutableFloat mutNumC = new MutableFloat(1f);

        assertNotEquals(mutNumA.hashCode(), mutNumC.hashCode());
    }

    @Test
    public void testHashCode_4_oe() {
        final MutableFloat mutNumA = new MutableFloat(0f);
        final MutableFloat mutNumB = new MutableFloat(0f);
        final MutableFloat mutNumC = new MutableFloat(1f);

        assertEquals(mutNumA.hashCode(), Float.valueOf(0f).hashCode());
    }

    @Test
    public void testIncrement_1_oe() {
        final MutableFloat mutNum = new MutableFloat(1);
        mutNum.increment();

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testIncrement_2_oe() {
        final MutableFloat mutNum = new MutableFloat(1);
        mutNum.increment();

        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testIncrementAndGet_1_oe() {
        final MutableFloat mutNum = new MutableFloat(1f);
        final float result = mutNum.incrementAndGet();

        assertEquals(2f, result, 0.01f);
    }

    @Test
    public void testIncrementAndGet_2_oe() {
        final MutableFloat mutNum = new MutableFloat(1f);
        final float result = mutNum.incrementAndGet();

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testIncrementAndGet_3_oe() {
        final MutableFloat mutNum = new MutableFloat(1f);
        final float result = mutNum.incrementAndGet();

        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testNanInfinite_1_oe() {
        MutableFloat mutNum = new MutableFloat(Float.NaN);
        assertTrue(mutNum.isNaN());
    }

    @Test
    public void testNanInfinite_2_oe() {
        MutableFloat mutNum = new MutableFloat(Float.NaN);

        mutNum = new MutableFloat(Float.POSITIVE_INFINITY);
        assertTrue(mutNum.isInfinite());
    }

    @Test
    public void testNanInfinite_3_oe() {
        MutableFloat mutNum = new MutableFloat(Float.NaN);

        mutNum = new MutableFloat(Float.POSITIVE_INFINITY);

        mutNum = new MutableFloat(Float.NEGATIVE_INFINITY);
        assertTrue(mutNum.isInfinite());
    }

    @Test
    public void testPrimitiveValues_1_oe() {
        final MutableFloat mutNum = new MutableFloat(1.7F);

        assertEquals( 1, mutNum.intValue() );
    }

    @Test
    public void testPrimitiveValues_2_oe() {
        final MutableFloat mutNum = new MutableFloat(1.7F);

        assertEquals( 1.7, mutNum.doubleValue(), 0.00001 );
    }

    @Test
    public void testPrimitiveValues_3_oe() {
        final MutableFloat mutNum = new MutableFloat(1.7F);

        assertEquals( (byte) 1, mutNum.byteValue() );
    }

    @Test
    public void testPrimitiveValues_4_oe() {
        final MutableFloat mutNum = new MutableFloat(1.7F);

        assertEquals( (short) 1, mutNum.shortValue() );
    }

    @Test
    public void testPrimitiveValues_5_oe() {
        final MutableFloat mutNum = new MutableFloat(1.7F);

        assertEquals( 1, mutNum.intValue() );
    }

    @Test
    public void testPrimitiveValues_6_oe() {
        final MutableFloat mutNum = new MutableFloat(1.7F);

        assertEquals( 1L, mutNum.longValue() );
    }

    @Test
    public void testSetNull_1_oe() throws Exception {
        final MutableFloat mutNum = new MutableFloat(0f);
        try {
    mutNum.setValue(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testSubtractValueObject_1_oe() {
        final MutableFloat mutNum = new MutableFloat(1);
        mutNum.subtract(Float.valueOf(0.9f));

        assertEquals(0.1f, mutNum.floatValue(), 0.01f);
    }

    @Test
    public void testSubtractValuePrimitive_1_oe() {
        final MutableFloat mutNum = new MutableFloat(1);
        mutNum.subtract(0.9f);

        assertEquals(0.1f, mutNum.floatValue(), 0.01f);
    }

    @Test
    public void testToFloat_1_oe() {
        assertEquals(Float.valueOf(0f), new MutableFloat(0f).toFloat());
    }

    @Test
    public void testToFloat_2_oe() {
        assertEquals(Float.valueOf(12.3f), new MutableFloat(12.3f).toFloat());
    }

    @Test
    public void testToString_1_oe() {
        assertEquals("0.0", new MutableFloat(0f).toString());
    }

    @Test
    public void testToString_2_oe() {
        assertEquals("10.0", new MutableFloat(10f).toString());
    }

    @Test
    public void testToString_3_oe() {
        assertEquals("-123.0", new MutableFloat(-123f).toString());
    }

}
