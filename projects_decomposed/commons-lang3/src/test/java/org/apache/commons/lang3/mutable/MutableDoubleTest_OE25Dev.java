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

/**
 * JUnit tests.
 *
 * @see MutableDouble
 */
public class MutableDoubleTest_OE25Dev {

    // ----------------------------------------------------------------

    @Test
    public void testAddAndGetValueObject_1_oe() {
        final MutableDouble mutableDouble = new MutableDouble(7.5d);
        final double result = mutableDouble.addAndGet(Double.valueOf(-2.5d));

        assertEquals(5d, result, 0.01d);
    }

    @Test
    public void testAddAndGetValueObject_2_oe() {
        final MutableDouble mutableDouble = new MutableDouble(7.5d);
        final double result = mutableDouble.addAndGet(Double.valueOf(-2.5d));

        // removed other assertion
        assertEquals(5d, mutableDouble.doubleValue(), 0.01d);
    }

    @Test
    public void testAddAndGetValuePrimitive_1_oe() {
        final MutableDouble mutableDouble = new MutableDouble(10.5d);
        final double result = mutableDouble.addAndGet(-0.5d);

        assertEquals(10d, result, 0.01d);
    }

    @Test
    public void testAddAndGetValuePrimitive_2_oe() {
        final MutableDouble mutableDouble = new MutableDouble(10.5d);
        final double result = mutableDouble.addAndGet(-0.5d);

        // removed other assertion
        assertEquals(10d, mutableDouble.doubleValue(), 0.01d);
    }

    @Test
    public void testAddValueObject_1_oe() {
        final MutableDouble mutNum = new MutableDouble(1);
        mutNum.add(Double.valueOf(1.1d));

        assertEquals(2.1d, mutNum.doubleValue(), 0.01d);
    }

    @Test
    public void testAddValuePrimitive_1_oe() {
        final MutableDouble mutNum = new MutableDouble(1);
        mutNum.add(1.1d);

        assertEquals(2.1d, mutNum.doubleValue(), 0.01d);
    }

    @Test
    public void testCompareTo_1_oe() {
        final MutableDouble mutNum = new MutableDouble(0d);

        assertEquals(0, mutNum.compareTo(new MutableDouble(0d)));
    }

    @Test
    public void testCompareTo_2_oe() {
        final MutableDouble mutNum = new MutableDouble(0d);

        // removed other assertion
        assertEquals(+1, mutNum.compareTo(new MutableDouble(-1d)));
    }

    @Test
    public void testCompareTo_3_oe() {
        final MutableDouble mutNum = new MutableDouble(0d);

        // removed other assertion
        // removed other assertion
        assertEquals(-1, mutNum.compareTo(new MutableDouble(1d)));
    }

    @Test
    public void testCompareToNull_1_oe() {
        final MutableDouble mutNum = new MutableDouble(0d);
        assertThrows(NullPointerException.class, () -> mutNum.compareTo(null));
    }

    @Test
    public void testConstructorNull_1_oe() {
        assertThrows(NullPointerException.class, () -> new MutableDouble((Number) null));
    }

    @Test
    public void testConstructors_1_oe() {
        assertEquals(0d, new MutableDouble().doubleValue(), 0.0001d);
    }

    @Test
    public void testConstructors_2_oe() {
        // removed other assertion

        assertEquals(1d, new MutableDouble(1d).doubleValue(), 0.0001d);
    }

    @Test
    public void testConstructors_3_oe() {
        // removed other assertion

        // removed other assertion

        assertEquals(2d, new MutableDouble(Double.valueOf(2d)).doubleValue(), 0.0001d);
    }

    @Test
    public void testConstructors_4_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertEquals(3d, new MutableDouble(new MutableDouble(3d)).doubleValue(), 0.0001d);
    }

    @Test
    public void testConstructors_5_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(2d, new MutableDouble("2.0").doubleValue(), 0.0001d);
    }

    @Test
    public void testDecrement_1_oe() {
        final MutableDouble mutNum = new MutableDouble(1);
        mutNum.decrement();

        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testDecrement_2_oe() {
        final MutableDouble mutNum = new MutableDouble(1);
        mutNum.decrement();

        // removed other assertion
        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testDecrementAndGet_1_oe() {
        final MutableDouble mutNum = new MutableDouble(1d);
        final double result = mutNum.decrementAndGet();

        assertEquals(0d, result, 0.01d);
    }

    @Test
    public void testDecrementAndGet_2_oe() {
        final MutableDouble mutNum = new MutableDouble(1d);
        final double result = mutNum.decrementAndGet();

        // removed other assertion
        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testDecrementAndGet_3_oe() {
        final MutableDouble mutNum = new MutableDouble(1d);
        final double result = mutNum.decrementAndGet();

        // removed other assertion
        // removed other assertion
        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testEquals_1_oe() {
        final MutableDouble mutNumA = new MutableDouble(0d);
        final MutableDouble mutNumB = new MutableDouble(0d);
        final MutableDouble mutNumC = new MutableDouble(1d);

        assertEquals(mutNumA, mutNumA);
    }

    @Test
    public void testEquals_2_oe() {
        final MutableDouble mutNumA = new MutableDouble(0d);
        final MutableDouble mutNumB = new MutableDouble(0d);
        final MutableDouble mutNumC = new MutableDouble(1d);

        // removed other assertion
        assertEquals(mutNumA, mutNumB);
    }

    @Test
    public void testEquals_3_oe() {
        final MutableDouble mutNumA = new MutableDouble(0d);
        final MutableDouble mutNumB = new MutableDouble(0d);
        final MutableDouble mutNumC = new MutableDouble(1d);

        // removed other assertion
        // removed other assertion
        assertEquals(mutNumB, mutNumA);
    }

    @Test
    public void testEquals_4_oe() {
        final MutableDouble mutNumA = new MutableDouble(0d);
        final MutableDouble mutNumB = new MutableDouble(0d);
        final MutableDouble mutNumC = new MutableDouble(1d);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(mutNumB, mutNumB);
    }

    @Test
    public void testEquals_5_oe() {
        final MutableDouble mutNumA = new MutableDouble(0d);
        final MutableDouble mutNumB = new MutableDouble(0d);
        final MutableDouble mutNumC = new MutableDouble(1d);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(mutNumA, mutNumC);
    }

    @Test
    public void testEquals_6_oe() {
        final MutableDouble mutNumA = new MutableDouble(0d);
        final MutableDouble mutNumB = new MutableDouble(0d);
        final MutableDouble mutNumC = new MutableDouble(1d);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(mutNumB, mutNumC);
    }

    @Test
    public void testEquals_7_oe() {
        final MutableDouble mutNumA = new MutableDouble(0d);
        final MutableDouble mutNumB = new MutableDouble(0d);
        final MutableDouble mutNumC = new MutableDouble(1d);

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
        final MutableDouble mutNumA = new MutableDouble(0d);
        final MutableDouble mutNumB = new MutableDouble(0d);
        final MutableDouble mutNumC = new MutableDouble(1d);

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
        final MutableDouble mutNumA = new MutableDouble(0d);
        final MutableDouble mutNumB = new MutableDouble(0d);
        final MutableDouble mutNumC = new MutableDouble(1d);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(mutNumA, Double.valueOf(0d));
    }

    @Test
    public void testEquals_10_oe() {
        final MutableDouble mutNumA = new MutableDouble(0d);
        final MutableDouble mutNumB = new MutableDouble(0d);
        final MutableDouble mutNumC = new MutableDouble(1d);

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
        final MutableDouble mutableDouble = new MutableDouble(0.5d);
        final double result = mutableDouble.getAndAdd(Double.valueOf(2d));

        assertEquals(0.5d, result, 0.01d);
    }

    @Test
    public void testGetAndAddValueObject_2_oe() {
        final MutableDouble mutableDouble = new MutableDouble(0.5d);
        final double result = mutableDouble.getAndAdd(Double.valueOf(2d));

        // removed other assertion
        assertEquals(2.5d, mutableDouble.doubleValue(), 0.01d);
    }

    @Test
    public void testGetAndAddValuePrimitive_1_oe() {
        final MutableDouble mutableDouble = new MutableDouble(0.5d);
        final double result = mutableDouble.getAndAdd(1d);

        assertEquals(0.5d, result, 0.01d);
    }

    @Test
    public void testGetAndAddValuePrimitive_2_oe() {
        final MutableDouble mutableDouble = new MutableDouble(0.5d);
        final double result = mutableDouble.getAndAdd(1d);

        // removed other assertion
        assertEquals(1.5d, mutableDouble.doubleValue(), 0.01d);
    }

    @Test
    public void testGetAndDecrement_1_oe() {
        final MutableDouble mutNum = new MutableDouble(1d);
        final double result = mutNum.getAndDecrement();

        assertEquals(1d, result, 0.01d);
    }

    @Test
    public void testGetAndDecrement_2_oe() {
        final MutableDouble mutNum = new MutableDouble(1d);
        final double result = mutNum.getAndDecrement();

        // removed other assertion
        assertEquals(0, mutNum.intValue());
    }

    @Test
    public void testGetAndDecrement_3_oe() {
        final MutableDouble mutNum = new MutableDouble(1d);
        final double result = mutNum.getAndDecrement();

        // removed other assertion
        // removed other assertion
        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testGetAndIncrement_1_oe() {
        final MutableDouble mutNum = new MutableDouble(1d);
        final double result = mutNum.getAndIncrement();

        assertEquals(1d, result, 0.01d);
    }

    @Test
    public void testGetAndIncrement_2_oe() {
        final MutableDouble mutNum = new MutableDouble(1d);
        final double result = mutNum.getAndIncrement();

        // removed other assertion
        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testGetAndIncrement_3_oe() {
        final MutableDouble mutNum = new MutableDouble(1d);
        final double result = mutNum.getAndIncrement();

        // removed other assertion
        // removed other assertion
        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testGetSet_1_oe() {
        final MutableDouble mutNum = new MutableDouble(0d);
        assertEquals(0d, new MutableDouble().doubleValue(), 0.0001d);
    }

    @Test
    public void testGetSet_2_oe() {
        final MutableDouble mutNum = new MutableDouble(0d);
        // removed other assertion
        assertEquals(Double.valueOf(0), new MutableDouble().getValue());
    }

    @Test
    public void testGetSet_3_oe() {
        final MutableDouble mutNum = new MutableDouble(0d);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(1);
        assertEquals(1d, mutNum.doubleValue(), 0.0001d);
    }

    @Test
    public void testGetSet_4_oe() {
        final MutableDouble mutNum = new MutableDouble(0d);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(1);
        // removed other assertion
        assertEquals(Double.valueOf(1d), mutNum.getValue());
    }

    @Test
    public void testGetSet_5_oe() {
        final MutableDouble mutNum = new MutableDouble(0d);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(1);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(Double.valueOf(2d));
        assertEquals(2d, mutNum.doubleValue(), 0.0001d);
    }

    @Test
    public void testGetSet_6_oe() {
        final MutableDouble mutNum = new MutableDouble(0d);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(1);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(Double.valueOf(2d));
        // removed other assertion
        assertEquals(Double.valueOf(2d), mutNum.getValue());
    }

    @Test
    public void testGetSet_7_oe() {
        final MutableDouble mutNum = new MutableDouble(0d);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(1);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(Double.valueOf(2d));
        // removed other assertion
        // removed other assertion

        mutNum.setValue(new MutableDouble(3d));
        assertEquals(3d, mutNum.doubleValue(), 0.0001d);
    }

    @Test
    public void testGetSet_8_oe() {
        final MutableDouble mutNum = new MutableDouble(0d);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(1);
        // removed other assertion
        // removed other assertion

        mutNum.setValue(Double.valueOf(2d));
        // removed other assertion
        // removed other assertion

        mutNum.setValue(new MutableDouble(3d));
        // removed other assertion
        assertEquals(Double.valueOf(3d), mutNum.getValue());
    }

    @Test
    public void testHashCode_1_oe() {
        final MutableDouble mutNumA = new MutableDouble(0d);
        final MutableDouble mutNumB = new MutableDouble(0d);
        final MutableDouble mutNumC = new MutableDouble(1d);

        assertEquals(mutNumA.hashCode(), mutNumA.hashCode());
    }

    @Test
    public void testHashCode_2_oe() {
        final MutableDouble mutNumA = new MutableDouble(0d);
        final MutableDouble mutNumB = new MutableDouble(0d);
        final MutableDouble mutNumC = new MutableDouble(1d);

        // removed other assertion
        assertEquals(mutNumA.hashCode(), mutNumB.hashCode());
    }

    @Test
    public void testHashCode_3_oe() {
        final MutableDouble mutNumA = new MutableDouble(0d);
        final MutableDouble mutNumB = new MutableDouble(0d);
        final MutableDouble mutNumC = new MutableDouble(1d);

        // removed other assertion
        // removed other assertion
        assertNotEquals(mutNumA.hashCode(), mutNumC.hashCode());
    }

    @Test
    public void testHashCode_4_oe() {
        final MutableDouble mutNumA = new MutableDouble(0d);
        final MutableDouble mutNumB = new MutableDouble(0d);
        final MutableDouble mutNumC = new MutableDouble(1d);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(mutNumA.hashCode(), Double.valueOf(0d).hashCode());
    }

    @Test
    public void testIncrement_1_oe() {
        final MutableDouble mutNum = new MutableDouble(1);
        mutNum.increment();

        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testIncrement_2_oe() {
        final MutableDouble mutNum = new MutableDouble(1);
        mutNum.increment();

        // removed other assertion
        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testIncrementAndGet_1_oe() {
        final MutableDouble mutNum = new MutableDouble(1d);
        final double result = mutNum.incrementAndGet();

        assertEquals(2d, result, 0.01d);
    }

    @Test
    public void testIncrementAndGet_2_oe() {
        final MutableDouble mutNum = new MutableDouble(1d);
        final double result = mutNum.incrementAndGet();

        // removed other assertion
        assertEquals(2, mutNum.intValue());
    }

    @Test
    public void testIncrementAndGet_3_oe() {
        final MutableDouble mutNum = new MutableDouble(1d);
        final double result = mutNum.incrementAndGet();

        // removed other assertion
        // removed other assertion
        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testNanInfinite_1_oe() {
        MutableDouble mutNum = new MutableDouble(Double.NaN);
        assertTrue(mutNum.isNaN());
    }

    @Test
    public void testNanInfinite_2_oe() {
        MutableDouble mutNum = new MutableDouble(Double.NaN);
        // removed other assertion

        mutNum = new MutableDouble(Double.POSITIVE_INFINITY);
        assertTrue(mutNum.isInfinite());
    }

    @Test
    public void testNanInfinite_3_oe() {
        MutableDouble mutNum = new MutableDouble(Double.NaN);
        // removed other assertion

        mutNum = new MutableDouble(Double.POSITIVE_INFINITY);
        // removed other assertion

        mutNum = new MutableDouble(Double.NEGATIVE_INFINITY);
        assertTrue(mutNum.isInfinite());
    }

    @Test
    public void testPrimitiveValues_1_oe() {
        final MutableDouble mutNum = new MutableDouble(1.7);
        assertEquals(1.7F, mutNum.floatValue());
    }

    @Test
    public void testPrimitiveValues_2_oe() {
        final MutableDouble mutNum = new MutableDouble(1.7);
        // removed other assertion
        assertEquals(1.7, mutNum.doubleValue());
    }

    @Test
    public void testPrimitiveValues_3_oe() {
        final MutableDouble mutNum = new MutableDouble(1.7);
        // removed other assertion
        // removed other assertion
        assertEquals( (byte) 1, mutNum.byteValue() );
    }

    @Test
    public void testPrimitiveValues_4_oe() {
        final MutableDouble mutNum = new MutableDouble(1.7);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( (short) 1, mutNum.shortValue() );
    }

    @Test
    public void testPrimitiveValues_5_oe() {
        final MutableDouble mutNum = new MutableDouble(1.7);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 1, mutNum.intValue() );
    }

    @Test
    public void testPrimitiveValues_6_oe() {
        final MutableDouble mutNum = new MutableDouble(1.7);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( 1L, mutNum.longValue() );
    }

    @Test
    public void testSetNull_1_oe() {
        final MutableDouble mutNum = new MutableDouble(0d);
        assertThrows(NullPointerException.class, () -> mutNum.setValue(null));
    }

    @Test
    public void testSubtractValueObject_1_oe() {
        final MutableDouble mutNum = new MutableDouble(1);
        mutNum.subtract(Double.valueOf(0.9d));

        assertEquals(0.1d, mutNum.doubleValue(), 0.01d);
    }

    @Test
    public void testSubtractValuePrimitive_1_oe() {
        final MutableDouble mutNum = new MutableDouble(1);
        mutNum.subtract(0.9d);

        assertEquals(0.1d, mutNum.doubleValue(), 0.01d);
    }

    @Test
    public void testToDouble_1_oe() {
        assertEquals(Double.valueOf(0d), new MutableDouble(0d).toDouble());
    }

    @Test
    public void testToDouble_2_oe() {
        // removed other assertion
        assertEquals(Double.valueOf(12.3d), new MutableDouble(12.3d).toDouble());
    }

    @Test
    public void testToString_1_oe() {
        assertEquals("0.0", new MutableDouble(0d).toString());
    }

    @Test
    public void testToString_2_oe() {
        // removed other assertion
        assertEquals("10.0", new MutableDouble(10d).toString());
    }

    @Test
    public void testToString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("-123.0", new MutableDouble(-123d).toString());
    }

}
