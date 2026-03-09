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

package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * <p>
 * Tests the methods in the {@link org.apache.commons.lang3.Range} class.
 * </p>
 */
@SuppressWarnings("boxing")
public class RangeTest_OE25Dev {

    private Range<Byte> byteRange;
    private Range<Byte> byteRange2;
    private Range<Byte> byteRange3;

    private Range<Double> doubleRange;
    private Range<Float> floatRange;
    private Range<Integer> intRange;
    private Range<Long> longRange;

    @BeforeEach
    public void setUp() {
        byteRange = Range.between((byte) 0, (byte) 5);
        byteRange2 = Range.between((byte) 0, (byte) 5);
        byteRange3 = Range.between((byte) 0, (byte) 10);

        intRange = Range.between(10, 20);
        longRange = Range.between(10L, 20L);
        floatRange = Range.between((float) 10, (float) 20);
        doubleRange = Range.between((double) 10, (double) 20);
    }

    @Test
    public void testBetweenWithCompare() {
        // all integers are equal
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        assertFalse(rb.contains(null), "should not contain null");
        assertTrue(rb.contains(10), "should contain 10");
        assertTrue(rb.contains(-10), "should contain -10");
        assertFalse(rb.contains(21), "should not contain 21");
        assertFalse(rb.contains(-11), "should not contain -11");
        rb = Range.between(-10, 20, c);
        assertFalse(rb.contains(null), "should not contain null");
        assertTrue(rb.contains(10), "should contain 10");
        assertTrue(rb.contains(-10), "should contain -10");
        assertTrue(rb.contains(21), "should contain 21");
        assertTrue(rb.contains(-11), "should contain -11");
        Range<String> rbstr = Range.between("house", "i");
        assertFalse(rbstr.contains(null), "should not contain null");
        assertTrue(rbstr.contains("house"), "should contain house");
        assertTrue(rbstr.contains("i"), "should contain i");
        assertFalse(rbstr.contains("hose"), "should not contain hose");
        assertFalse(rbstr.contains("ice"), "should not contain ice");
        rbstr = Range.between("house", "i", lengthComp);
        assertFalse(rbstr.contains(null), "should not contain null");
        assertTrue(rbstr.contains("house"), "should contain house");
        assertTrue(rbstr.contains("i"), "should contain i");
        assertFalse(rbstr.contains("houses"), "should not contain houses");
        assertFalse(rbstr.contains(""), "should not contain ''");
    }

    // -----------------------------------------------------------------------
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void testComparableConstructors() {
        final Comparable c = other -> 1;
        final Range r1 = Range.is(c);
        final Range r2 = Range.between(c, c);
        assertTrue(r1.isNaturalOrdering());
        assertTrue(r2.isNaturalOrdering());
    }

    @Test
    public void testContains() {
        assertFalse(intRange.contains(null));

        assertFalse(intRange.contains(5));
        assertTrue(intRange.contains(10));
        assertTrue(intRange.contains(15));
        assertTrue(intRange.contains(20));
        assertFalse(intRange.contains(25));
    }

    // -----------------------------------------------------------------------
    @Test
    public void testContainsRange() {

        // null handling
        assertFalse(intRange.containsRange(null));

        // easy inside range
        assertTrue(intRange.containsRange(Range.between(12, 18)));

        // outside range on each side
        assertFalse(intRange.containsRange(Range.between(32, 45)));
        assertFalse(intRange.containsRange(Range.between(2, 8)));

        // equals range
        assertTrue(intRange.containsRange(Range.between(10, 20)));

        // overlaps
        assertFalse(intRange.containsRange(Range.between(9, 14)));
        assertFalse(intRange.containsRange(Range.between(16, 21)));

        // touches lower boundary
        assertTrue(intRange.containsRange(Range.between(10, 19)));
        assertFalse(intRange.containsRange(Range.between(10, 21)));

        // touches upper boundary
        assertTrue(intRange.containsRange(Range.between(11, 20)));
        assertFalse(intRange.containsRange(Range.between(9, 20)));

        // negative
        assertFalse(intRange.containsRange(Range.between(-11, -18)));
    }

    @Test
    public void testElementCompareTo() {
        assertThrows(NullPointerException.class, () -> intRange.elementCompareTo(null));

        assertEquals(-1, intRange.elementCompareTo(5));
        assertEquals(0, intRange.elementCompareTo(10));
        assertEquals(0, intRange.elementCompareTo(15));
        assertEquals(0, intRange.elementCompareTo(20));
        assertEquals(1, intRange.elementCompareTo(25));
    }

    // -----------------------------------------------------------------------
    @Test
    public void testEqualsObject() {
        assertEquals(byteRange, byteRange);
        assertEquals(byteRange, byteRange2);
        assertEquals(byteRange2, byteRange2);
        assertEquals(byteRange, byteRange);
        assertEquals(byteRange2, byteRange2);
        assertEquals(byteRange3, byteRange3);
        assertNotEquals(byteRange2, byteRange3);
        assertNotEquals(null, byteRange2);
        assertNotEquals("Ni!", byteRange2);
    }

    @Test
    public void testFit() {
        assertEquals(intRange.getMinimum(), intRange.fit(Integer.MIN_VALUE));
        assertEquals(intRange.getMinimum(), intRange.fit(intRange.getMinimum()));
        assertEquals(intRange.getMaximum(), intRange.fit(Integer.MAX_VALUE));
        assertEquals(intRange.getMaximum(), intRange.fit(intRange.getMaximum()));
        assertEquals(15, intRange.fit(15));
    }

    @Test
    public void testFitNull() {
        assertThrows(NullPointerException.class, () -> {
            intRange.fit(null);
        });
    }

    @Test
    public void testGetMaximum() {
        assertEquals(20, (int) intRange.getMaximum());
        assertEquals(20L, (long) longRange.getMaximum());
        assertEquals(20f, floatRange.getMaximum(), 0.00001f);
        assertEquals(20d, doubleRange.getMaximum(), 0.00001d);
    }

    // -----------------------------------------------------------------------
    @Test
    public void testGetMinimum() {
        assertEquals(10, (int) intRange.getMinimum());
        assertEquals(10L, (long) longRange.getMinimum());
        assertEquals(10f, floatRange.getMinimum(), 0.00001f);
        assertEquals(10d, doubleRange.getMinimum(), 0.00001d);
    }

    @Test
    public void testHashCode() {
        assertEquals(byteRange.hashCode(), byteRange2.hashCode());
        assertNotEquals(byteRange.hashCode(), byteRange3.hashCode());

        assertEquals(intRange.hashCode(), intRange.hashCode());
        assertTrue(intRange.hashCode() != 0);
    }

    @Test
    public void testIntersectionWith() {
        assertSame(intRange, intRange.intersectionWith(intRange));
        assertSame(byteRange, byteRange.intersectionWith(byteRange));
        assertSame(longRange, longRange.intersectionWith(longRange));
        assertSame(floatRange, floatRange.intersectionWith(floatRange));
        assertSame(doubleRange, doubleRange.intersectionWith(doubleRange));

        assertEquals(Range.between(10, 15), intRange.intersectionWith(Range.between(5, 15)));
    }

    @Test
    public void testIntersectionWithNonOverlapping() {
        assertThrows(IllegalArgumentException.class, () -> intRange.intersectionWith(Range.between(0, 9)));
    }

    @Test
    public void testIntersectionWithNull() {
        assertThrows(IllegalArgumentException.class, () -> intRange.intersectionWith(null));
    }

    @Test
    public void testIsAfter() {
        assertFalse(intRange.isAfter(null));

        assertTrue(intRange.isAfter(5));
        assertFalse(intRange.isAfter(10));
        assertFalse(intRange.isAfter(15));
        assertFalse(intRange.isAfter(20));
        assertFalse(intRange.isAfter(25));
    }

    @Test
    public void testIsAfterRange() {
        assertFalse(intRange.isAfterRange(null));

        assertTrue(intRange.isAfterRange(Range.between(5, 9)));

        assertFalse(intRange.isAfterRange(Range.between(5, 10)));
        assertFalse(intRange.isAfterRange(Range.between(5, 20)));
        assertFalse(intRange.isAfterRange(Range.between(5, 25)));
        assertFalse(intRange.isAfterRange(Range.between(15, 25)));

        assertFalse(intRange.isAfterRange(Range.between(21, 25)));

        assertFalse(intRange.isAfterRange(Range.between(10, 20)));
    }

    @Test
    public void testIsBefore() {
        assertFalse(intRange.isBefore(null));

        assertFalse(intRange.isBefore(5));
        assertFalse(intRange.isBefore(10));
        assertFalse(intRange.isBefore(15));
        assertFalse(intRange.isBefore(20));
        assertTrue(intRange.isBefore(25));
    }

    @Test
    public void testIsBeforeRange() {
        assertFalse(intRange.isBeforeRange(null));

        assertFalse(intRange.isBeforeRange(Range.between(5, 9)));

        assertFalse(intRange.isBeforeRange(Range.between(5, 10)));
        assertFalse(intRange.isBeforeRange(Range.between(5, 20)));
        assertFalse(intRange.isBeforeRange(Range.between(5, 25)));
        assertFalse(intRange.isBeforeRange(Range.between(15, 25)));

        assertTrue(intRange.isBeforeRange(Range.between(21, 25)));

        assertFalse(intRange.isBeforeRange(Range.between(10, 20)));
    }

    @Test
    public void testIsEndedBy() {
        assertFalse(intRange.isEndedBy(null));

        assertFalse(intRange.isEndedBy(5));
        assertFalse(intRange.isEndedBy(10));
        assertFalse(intRange.isEndedBy(15));
        assertTrue(intRange.isEndedBy(20));
        assertFalse(intRange.isEndedBy(25));
    }

    @Test
    public void testIsOverlappedBy() {

        // null handling
        assertFalse(intRange.isOverlappedBy(null));

        // easy inside range
        assertTrue(intRange.isOverlappedBy(Range.between(12, 18)));

        // outside range on each side
        assertFalse(intRange.isOverlappedBy(Range.between(32, 45)));
        assertFalse(intRange.isOverlappedBy(Range.between(2, 8)));

        // equals range
        assertTrue(intRange.isOverlappedBy(Range.between(10, 20)));

        // overlaps
        assertTrue(intRange.isOverlappedBy(Range.between(9, 14)));
        assertTrue(intRange.isOverlappedBy(Range.between(16, 21)));

        // touches lower boundary
        assertTrue(intRange.isOverlappedBy(Range.between(10, 19)));
        assertTrue(intRange.isOverlappedBy(Range.between(10, 21)));

        // touches upper boundary
        assertTrue(intRange.isOverlappedBy(Range.between(11, 20)));
        assertTrue(intRange.isOverlappedBy(Range.between(9, 20)));

        // negative
        assertFalse(intRange.isOverlappedBy(Range.between(-11, -18)));
    }

    @Test
    public void testIsStartedBy() {
        assertFalse(intRange.isStartedBy(null));

        assertFalse(intRange.isStartedBy(5));
        assertTrue(intRange.isStartedBy(10));
        assertFalse(intRange.isStartedBy(15));
        assertFalse(intRange.isStartedBy(20));
        assertFalse(intRange.isStartedBy(25));
    }

    @Test
    public void testIsWithCompare() {
        // all integers are equal
        final Comparator<Integer> c = (o1, o2) -> 0;
        Range<Integer> ri = Range.is(10);
        assertFalse(ri.contains(null), "should not contain null");
        assertTrue(ri.contains(10), "should contain 10");
        assertFalse(ri.contains(11), "should not contain 11");
        ri = Range.is(10, c);
        assertFalse(ri.contains(null), "should not contain null");
        assertTrue(ri.contains(10), "should contain 10");
        assertTrue(ri.contains(11), "should contain 11");
    }

    // -----------------------------------------------------------------------
    @Test
    public void testRangeOfChars() {
        final Range<Character> chars = Range.between('a', 'z');
        assertTrue(chars.contains('b'));
        assertFalse(chars.contains('B'));
    }

    // -----------------------------------------------------------------------
    @Test
    public void testSerializing() {
        SerializationUtils.clone(intRange);
    }

    @Test
    public void testToString() {
        assertNotNull(byteRange.toString());

        final String str = intRange.toString();
        assertEquals("[10..20]", str);
        assertEquals("[-20..-10]", Range.between(-20, -10).toString());
    }

    @Test
    public void testToStringFormat() {
        final String str = intRange.toString("From %1$s to %2$s");
        assertEquals("From 10 to 20", str);
    }

    @Test
    public void testBetweenWithCompare_1_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        assertFalse(rb.contains(null), "should not contain null");
    }

    @Test
    public void testBetweenWithCompare_2_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        assertTrue(rb.contains(10), "should contain 10");
    }

    @Test
    public void testBetweenWithCompare_3_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        assertTrue(rb.contains(-10), "should contain -10");
    }

    @Test
    public void testBetweenWithCompare_4_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        assertFalse(rb.contains(21), "should not contain 21");
    }

    @Test
    public void testBetweenWithCompare_5_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        assertFalse(rb.contains(-11), "should not contain -11");
    }

    @Test
    public void testBetweenWithCompare_6_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        rb = Range.between(-10, 20, c);
        assertFalse(rb.contains(null), "should not contain null");
    }

    @Test
    public void testBetweenWithCompare_7_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        rb = Range.between(-10, 20, c);
        assertTrue(rb.contains(10), "should contain 10");
    }

    @Test
    public void testBetweenWithCompare_8_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        rb = Range.between(-10, 20, c);
        assertTrue(rb.contains(-10), "should contain -10");
    }

    @Test
    public void testBetweenWithCompare_9_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        rb = Range.between(-10, 20, c);
        assertTrue(rb.contains(21), "should contain 21");
    }

    @Test
    public void testBetweenWithCompare_10_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        rb = Range.between(-10, 20, c);
        assertTrue(rb.contains(-11), "should contain -11");
    }

    @Test
    public void testBetweenWithCompare_11_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        rb = Range.between(-10, 20, c);
        Range<String> rbstr = Range.between("house", "i");
        assertFalse(rbstr.contains(null), "should not contain null");
    }

    @Test
    public void testBetweenWithCompare_12_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        rb = Range.between(-10, 20, c);
        Range<String> rbstr = Range.between("house", "i");
        assertTrue(rbstr.contains("house"), "should contain house");
    }

    @Test
    public void testBetweenWithCompare_13_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        rb = Range.between(-10, 20, c);
        Range<String> rbstr = Range.between("house", "i");
        assertTrue(rbstr.contains("i"), "should contain i");
    }

    @Test
    public void testBetweenWithCompare_14_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        rb = Range.between(-10, 20, c);
        Range<String> rbstr = Range.between("house", "i");
        assertFalse(rbstr.contains("hose"), "should not contain hose");
    }

    @Test
    public void testBetweenWithCompare_15_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        rb = Range.between(-10, 20, c);
        Range<String> rbstr = Range.between("house", "i");
        assertFalse(rbstr.contains("ice"), "should not contain ice");
    }

    @Test
    public void testBetweenWithCompare_16_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        rb = Range.between(-10, 20, c);
        Range<String> rbstr = Range.between("house", "i");
        rbstr = Range.between("house", "i", lengthComp);
        assertFalse(rbstr.contains(null), "should not contain null");
    }

    @Test
    public void testBetweenWithCompare_17_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        rb = Range.between(-10, 20, c);
        Range<String> rbstr = Range.between("house", "i");
        rbstr = Range.between("house", "i", lengthComp);
        assertTrue(rbstr.contains("house"), "should contain house");
    }

    @Test
    public void testBetweenWithCompare_18_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        rb = Range.between(-10, 20, c);
        Range<String> rbstr = Range.between("house", "i");
        rbstr = Range.between("house", "i", lengthComp);
        assertTrue(rbstr.contains("i"), "should contain i");
    }

    @Test
    public void testBetweenWithCompare_19_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        rb = Range.between(-10, 20, c);
        Range<String> rbstr = Range.between("house", "i");
        rbstr = Range.between("house", "i", lengthComp);
        assertFalse(rbstr.contains("houses"), "should not contain houses");
    }

    @Test
    public void testBetweenWithCompare_20_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        final Comparator<String> lengthComp = Comparator.comparingInt(String::length);
        Range<Integer> rb = Range.between(-10, 20);
        rb = Range.between(-10, 20, c);
        Range<String> rbstr = Range.between("house", "i");
        rbstr = Range.between("house", "i", lengthComp);
        assertFalse(rbstr.contains(""), "should not contain ''");
    }

    @Test
    public void testComparableConstructors_1_oe() {
        final Comparable c = other -> 1;
        final Range r1 = Range.is(c);
        final Range r2 = Range.between(c, c);
        assertTrue(r1.isNaturalOrdering());
    }

    @Test
    public void testComparableConstructors_2_oe() {
        final Comparable c = other -> 1;
        final Range r1 = Range.is(c);
        final Range r2 = Range.between(c, c);
        assertTrue(r2.isNaturalOrdering());
    }

    @Test
    public void testContains_1_oe() {
        assertFalse(intRange.contains(null));
    }

    @Test
    public void testContains_2_oe() {

        assertFalse(intRange.contains(5));
    }

    @Test
    public void testContains_3_oe() {

        assertTrue(intRange.contains(10));
    }

    @Test
    public void testContains_4_oe() {

        assertTrue(intRange.contains(15));
    }

    @Test
    public void testContains_5_oe() {

        assertTrue(intRange.contains(20));
    }

    @Test
    public void testContains_6_oe() {

        assertFalse(intRange.contains(25));
    }

    @Test
    public void testContainsRange_1_oe() {

        assertFalse(intRange.containsRange(null));
    }

    @Test
    public void testContainsRange_2_oe() {


        assertTrue(intRange.containsRange(Range.between(12, 18)));
    }

    @Test
    public void testContainsRange_3_oe() {



        assertFalse(intRange.containsRange(Range.between(32, 45)));
    }

    @Test
    public void testContainsRange_4_oe() {



        assertFalse(intRange.containsRange(Range.between(2, 8)));
    }

    @Test
    public void testContainsRange_5_oe() {




        assertTrue(intRange.containsRange(Range.between(10, 20)));
    }

    @Test
    public void testContainsRange_6_oe() {





        assertFalse(intRange.containsRange(Range.between(9, 14)));
    }

    @Test
    public void testContainsRange_7_oe() {





        assertFalse(intRange.containsRange(Range.between(16, 21)));
    }

    @Test
    public void testContainsRange_8_oe() {






        assertTrue(intRange.containsRange(Range.between(10, 19)));
    }

    @Test
    public void testContainsRange_9_oe() {






        assertFalse(intRange.containsRange(Range.between(10, 21)));
    }

    @Test
    public void testContainsRange_10_oe() {







        assertTrue(intRange.containsRange(Range.between(11, 20)));
    }

    @Test
    public void testContainsRange_11_oe() {







        assertFalse(intRange.containsRange(Range.between(9, 20)));
    }

    @Test
    public void testContainsRange_12_oe() {








        assertFalse(intRange.containsRange(Range.between(-11, -18)));
    }

    @Test
    public void testElementCompareTo_1_oe() throws Exception {
        try {
    intRange.elementCompareTo(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testElementCompareTo_2_oe() {

        assertEquals(-1, intRange.elementCompareTo(5));
    }

    @Test
    public void testElementCompareTo_3_oe() {

        assertEquals(0, intRange.elementCompareTo(10));
    }

    @Test
    public void testElementCompareTo_4_oe() {

        assertEquals(0, intRange.elementCompareTo(15));
    }

    @Test
    public void testElementCompareTo_5_oe() {

        assertEquals(0, intRange.elementCompareTo(20));
    }

    @Test
    public void testElementCompareTo_6_oe() {

        assertEquals(1, intRange.elementCompareTo(25));
    }

    @Test
    public void testEqualsObject_1_oe() {
        assertEquals(byteRange, byteRange);
    }

    @Test
    public void testEqualsObject_2_oe() {
        assertEquals(byteRange, byteRange2);
    }

    @Test
    public void testEqualsObject_3_oe() {
        assertEquals(byteRange2, byteRange2);
    }

    @Test
    public void testEqualsObject_4_oe() {
        assertEquals(byteRange, byteRange);
    }

    @Test
    public void testEqualsObject_5_oe() {
        assertEquals(byteRange2, byteRange2);
    }

    @Test
    public void testEqualsObject_6_oe() {
        assertEquals(byteRange3, byteRange3);
    }

    @Test
    public void testEqualsObject_7_oe() {
        assertNotEquals(byteRange2, byteRange3);
    }

    @Test
    public void testEqualsObject_8_oe() {
        assertNotEquals(null, byteRange2);
    }

    @Test
    public void testEqualsObject_9_oe() {
        assertNotEquals("Ni!", byteRange2);
    }

    @Test
    public void testFit_1_oe() {
        assertEquals(intRange.getMinimum(), intRange.fit(Integer.MIN_VALUE));
    }

    @Test
    public void testFit_2_oe() {
        assertEquals(intRange.getMinimum(), intRange.fit(intRange.getMinimum()));
    }

    @Test
    public void testFit_3_oe() {
        assertEquals(intRange.getMaximum(), intRange.fit(Integer.MAX_VALUE));
    }

    @Test
    public void testFit_4_oe() {
        assertEquals(intRange.getMaximum(), intRange.fit(intRange.getMaximum()));
    }

    @Test
    public void testFit_5_oe() {
        assertEquals(15, intRange.fit(15));
    }

    @Test
    public void testFitNull_1_oe() throws Exception {
        try {
     intRange.fit(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetMaximum_1_oe() {
        assertEquals(20, (int) intRange.getMaximum());
    }

    @Test
    public void testGetMaximum_2_oe() {
        assertEquals(20L, (long) longRange.getMaximum());
    }

    @Test
    public void testGetMaximum_3_oe() {
        assertEquals(20f, floatRange.getMaximum(), 0.00001f);
    }

    @Test
    public void testGetMaximum_4_oe() {
        assertEquals(20d, doubleRange.getMaximum(), 0.00001d);
    }

    @Test
    public void testGetMinimum_1_oe() {
        assertEquals(10, (int) intRange.getMinimum());
    }

    @Test
    public void testGetMinimum_2_oe() {
        assertEquals(10L, (long) longRange.getMinimum());
    }

    @Test
    public void testGetMinimum_3_oe() {
        assertEquals(10f, floatRange.getMinimum(), 0.00001f);
    }

    @Test
    public void testGetMinimum_4_oe() {
        assertEquals(10d, doubleRange.getMinimum(), 0.00001d);
    }

    @Test
    public void testHashCode_1_oe() {
        assertEquals(byteRange.hashCode(), byteRange2.hashCode());
    }

    @Test
    public void testHashCode_2_oe() {
        assertNotEquals(byteRange.hashCode(), byteRange3.hashCode());
    }

    @Test
    public void testHashCode_3_oe() {

        assertEquals(intRange.hashCode(), intRange.hashCode());
    }

    @Test
    public void testHashCode_4_oe() {

        assertTrue(intRange.hashCode() != 0);
    }

    @Test
    public void testIntersectionWith_1_oe() {
        assertSame(intRange, intRange.intersectionWith(intRange));
    }

    @Test
    public void testIntersectionWith_2_oe() {
        assertSame(byteRange, byteRange.intersectionWith(byteRange));
    }

    @Test
    public void testIntersectionWith_3_oe() {
        assertSame(longRange, longRange.intersectionWith(longRange));
    }

    @Test
    public void testIntersectionWith_4_oe() {
        assertSame(floatRange, floatRange.intersectionWith(floatRange));
    }

    @Test
    public void testIntersectionWith_5_oe() {
        assertSame(doubleRange, doubleRange.intersectionWith(doubleRange));
    }

    @Test
    public void testIntersectionWith_6_oe() {

        assertEquals(Range.between(10, 15), intRange.intersectionWith(Range.between(5, 15)));
    }

    @Test
    public void testIntersectionWithNonOverlapping_1_oe() throws Exception {
        try {
    intRange.intersectionWith(Range.between(0, 9));
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testIntersectionWithNull_1_oe() throws Exception {
        try {
    intRange.intersectionWith(null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testIsAfter_1_oe() {
        assertFalse(intRange.isAfter(null));
    }

    @Test
    public void testIsAfter_2_oe() {

        assertTrue(intRange.isAfter(5));
    }

    @Test
    public void testIsAfter_3_oe() {

        assertFalse(intRange.isAfter(10));
    }

    @Test
    public void testIsAfter_4_oe() {

        assertFalse(intRange.isAfter(15));
    }

    @Test
    public void testIsAfter_5_oe() {

        assertFalse(intRange.isAfter(20));
    }

    @Test
    public void testIsAfter_6_oe() {

        assertFalse(intRange.isAfter(25));
    }

    @Test
    public void testIsAfterRange_1_oe() {
        assertFalse(intRange.isAfterRange(null));
    }

    @Test
    public void testIsAfterRange_2_oe() {

        assertTrue(intRange.isAfterRange(Range.between(5, 9)));
    }

    @Test
    public void testIsAfterRange_3_oe() {


        assertFalse(intRange.isAfterRange(Range.between(5, 10)));
    }

    @Test
    public void testIsAfterRange_4_oe() {


        assertFalse(intRange.isAfterRange(Range.between(5, 20)));
    }

    @Test
    public void testIsAfterRange_5_oe() {


        assertFalse(intRange.isAfterRange(Range.between(5, 25)));
    }

    @Test
    public void testIsAfterRange_6_oe() {


        assertFalse(intRange.isAfterRange(Range.between(15, 25)));
    }

    @Test
    public void testIsAfterRange_7_oe() {



        assertFalse(intRange.isAfterRange(Range.between(21, 25)));
    }

    @Test
    public void testIsAfterRange_8_oe() {




        assertFalse(intRange.isAfterRange(Range.between(10, 20)));
    }

    @Test
    public void testIsBefore_1_oe() {
        assertFalse(intRange.isBefore(null));
    }

    @Test
    public void testIsBefore_2_oe() {

        assertFalse(intRange.isBefore(5));
    }

    @Test
    public void testIsBefore_3_oe() {

        assertFalse(intRange.isBefore(10));
    }

    @Test
    public void testIsBefore_4_oe() {

        assertFalse(intRange.isBefore(15));
    }

    @Test
    public void testIsBefore_5_oe() {

        assertFalse(intRange.isBefore(20));
    }

    @Test
    public void testIsBefore_6_oe() {

        assertTrue(intRange.isBefore(25));
    }

    @Test
    public void testIsBeforeRange_1_oe() {
        assertFalse(intRange.isBeforeRange(null));
    }

    @Test
    public void testIsBeforeRange_2_oe() {

        assertFalse(intRange.isBeforeRange(Range.between(5, 9)));
    }

    @Test
    public void testIsBeforeRange_3_oe() {


        assertFalse(intRange.isBeforeRange(Range.between(5, 10)));
    }

    @Test
    public void testIsBeforeRange_4_oe() {


        assertFalse(intRange.isBeforeRange(Range.between(5, 20)));
    }

    @Test
    public void testIsBeforeRange_5_oe() {


        assertFalse(intRange.isBeforeRange(Range.between(5, 25)));
    }

    @Test
    public void testIsBeforeRange_6_oe() {


        assertFalse(intRange.isBeforeRange(Range.between(15, 25)));
    }

    @Test
    public void testIsBeforeRange_7_oe() {



        assertTrue(intRange.isBeforeRange(Range.between(21, 25)));
    }

    @Test
    public void testIsBeforeRange_8_oe() {




        assertFalse(intRange.isBeforeRange(Range.between(10, 20)));
    }

    @Test
    public void testIsEndedBy_1_oe() {
        assertFalse(intRange.isEndedBy(null));
    }

    @Test
    public void testIsEndedBy_2_oe() {

        assertFalse(intRange.isEndedBy(5));
    }

    @Test
    public void testIsEndedBy_3_oe() {

        assertFalse(intRange.isEndedBy(10));
    }

    @Test
    public void testIsEndedBy_4_oe() {

        assertFalse(intRange.isEndedBy(15));
    }

    @Test
    public void testIsEndedBy_5_oe() {

        assertTrue(intRange.isEndedBy(20));
    }

    @Test
    public void testIsEndedBy_6_oe() {

        assertFalse(intRange.isEndedBy(25));
    }

    @Test
    public void testIsOverlappedBy_1_oe() {

        assertFalse(intRange.isOverlappedBy(null));
    }

    @Test
    public void testIsOverlappedBy_2_oe() {


        assertTrue(intRange.isOverlappedBy(Range.between(12, 18)));
    }

    @Test
    public void testIsOverlappedBy_3_oe() {



        assertFalse(intRange.isOverlappedBy(Range.between(32, 45)));
    }

    @Test
    public void testIsOverlappedBy_4_oe() {



        assertFalse(intRange.isOverlappedBy(Range.between(2, 8)));
    }

    @Test
    public void testIsOverlappedBy_5_oe() {




        assertTrue(intRange.isOverlappedBy(Range.between(10, 20)));
    }

    @Test
    public void testIsOverlappedBy_6_oe() {





        assertTrue(intRange.isOverlappedBy(Range.between(9, 14)));
    }

    @Test
    public void testIsOverlappedBy_7_oe() {





        assertTrue(intRange.isOverlappedBy(Range.between(16, 21)));
    }

    @Test
    public void testIsOverlappedBy_8_oe() {






        assertTrue(intRange.isOverlappedBy(Range.between(10, 19)));
    }

    @Test
    public void testIsOverlappedBy_9_oe() {






        assertTrue(intRange.isOverlappedBy(Range.between(10, 21)));
    }

    @Test
    public void testIsOverlappedBy_10_oe() {







        assertTrue(intRange.isOverlappedBy(Range.between(11, 20)));
    }

    @Test
    public void testIsOverlappedBy_11_oe() {







        assertTrue(intRange.isOverlappedBy(Range.between(9, 20)));
    }

    @Test
    public void testIsOverlappedBy_12_oe() {








        assertFalse(intRange.isOverlappedBy(Range.between(-11, -18)));
    }

    @Test
    public void testIsStartedBy_1_oe() {
        assertFalse(intRange.isStartedBy(null));
    }

    @Test
    public void testIsStartedBy_2_oe() {

        assertFalse(intRange.isStartedBy(5));
    }

    @Test
    public void testIsStartedBy_3_oe() {

        assertTrue(intRange.isStartedBy(10));
    }

    @Test
    public void testIsStartedBy_4_oe() {

        assertFalse(intRange.isStartedBy(15));
    }

    @Test
    public void testIsStartedBy_5_oe() {

        assertFalse(intRange.isStartedBy(20));
    }

    @Test
    public void testIsStartedBy_6_oe() {

        assertFalse(intRange.isStartedBy(25));
    }

    @Test
    public void testIsWithCompare_1_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        Range<Integer> ri = Range.is(10);
        assertFalse(ri.contains(null), "should not contain null");
    }

    @Test
    public void testIsWithCompare_2_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        Range<Integer> ri = Range.is(10);
        assertTrue(ri.contains(10), "should contain 10");
    }

    @Test
    public void testIsWithCompare_3_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        Range<Integer> ri = Range.is(10);
        assertFalse(ri.contains(11), "should not contain 11");
    }

    @Test
    public void testIsWithCompare_4_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        Range<Integer> ri = Range.is(10);
        ri = Range.is(10, c);
        assertFalse(ri.contains(null), "should not contain null");
    }

    @Test
    public void testIsWithCompare_5_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        Range<Integer> ri = Range.is(10);
        ri = Range.is(10, c);
        assertTrue(ri.contains(10), "should contain 10");
    }

    @Test
    public void testIsWithCompare_6_oe() {
        final Comparator<Integer> c = (o1, o2) -> 0;
        Range<Integer> ri = Range.is(10);
        ri = Range.is(10, c);
        assertTrue(ri.contains(11), "should contain 11");
    }

    @Test
    public void testRangeOfChars_1_oe() {
        final Range<Character> chars = Range.between('a', 'z');
        assertTrue(chars.contains('b'));
    }

    @Test
    public void testRangeOfChars_2_oe() {
        final Range<Character> chars = Range.between('a', 'z');
        assertFalse(chars.contains('B'));
    }

    @Test
    public void testToString_1_oe() {
        assertNotNull(byteRange.toString());
    }

    @Test
    public void testToString_2_oe() {

        final String str = intRange.toString();
        assertEquals("[10..20]", str);
    }

    @Test
    public void testToString_3_oe() {

        final String str = intRange.toString();
        assertEquals("[-20..-10]", Range.between(-20, -10).toString());
    }

    @Test
    public void testToStringFormat_1_oe() {
        final String str = intRange.toString("From %1$s to %2$s");
        assertEquals("From 10 to 20", str);
    }

}
