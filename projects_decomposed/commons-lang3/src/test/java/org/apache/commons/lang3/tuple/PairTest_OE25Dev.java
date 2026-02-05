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
package org.apache.commons.lang3.tuple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

/**
 * Test the Pair class.
 */
public class PairTest_OE25Dev {

@Test
    public void testComparable1_1_oe() {
        final Pair<String, String> pair1 = Pair.of("A", "D");
        final Pair<String, String> pair2 = Pair.of("B", "C");
        assertEquals(0, pair1.compareTo(pair1));
    }

@Test
    public void testComparable1_2_oe() {
        final Pair<String, String> pair1 = Pair.of("A", "D");
        final Pair<String, String> pair2 = Pair.of("B", "C");
        // removed other assertion
        assertTrue(pair1.compareTo(pair2) < 0);
    }

@Test
    public void testComparable1_3_oe() {
        final Pair<String, String> pair1 = Pair.of("A", "D");
        final Pair<String, String> pair2 = Pair.of("B", "C");
        // removed other assertion
        // removed other assertion
        assertEquals(0, pair2.compareTo(pair2));
    }

@Test
    public void testComparable1_4_oe() {
        final Pair<String, String> pair1 = Pair.of("A", "D");
        final Pair<String, String> pair2 = Pair.of("B", "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(pair2.compareTo(pair1) > 0);
    }

@Test
    public void testComparable2_1_oe() {
        final Pair<String, String> pair1 = Pair.of("A", "C");
        final Pair<String, String> pair2 = Pair.of("A", "D");
        assertEquals(0, pair1.compareTo(pair1));
    }

@Test
    public void testComparable2_2_oe() {
        final Pair<String, String> pair1 = Pair.of("A", "C");
        final Pair<String, String> pair2 = Pair.of("A", "D");
        // removed other assertion
        assertTrue(pair1.compareTo(pair2) < 0);
    }

@Test
    public void testComparable2_3_oe() {
        final Pair<String, String> pair1 = Pair.of("A", "C");
        final Pair<String, String> pair2 = Pair.of("A", "D");
        // removed other assertion
        // removed other assertion
        assertEquals(0, pair2.compareTo(pair2));
    }

@Test
    public void testComparable2_4_oe() {
        final Pair<String, String> pair1 = Pair.of("A", "C");
        final Pair<String, String> pair2 = Pair.of("A", "D");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(pair2.compareTo(pair1) > 0);
    }

@Test
    public void testCompatibilityBetweenPairs_1_oe() {
        final Pair<Integer, String> pair = ImmutablePair.of(0, "foo");
        final Pair<Integer, String> pair2 = MutablePair.of(0, "foo");
        assertEquals(pair, pair2);
    }

@Test
    public void testCompatibilityBetweenPairs_2_oe() {
        final Pair<Integer, String> pair = ImmutablePair.of(0, "foo");
        final Pair<Integer, String> pair2 = MutablePair.of(0, "foo");
        // removed other assertion
        assertEquals(pair.hashCode(), pair2.hashCode());
    }

@Test
    public void testCompatibilityBetweenPairs_3_oe() {
        final Pair<Integer, String> pair = ImmutablePair.of(0, "foo");
        final Pair<Integer, String> pair2 = MutablePair.of(0, "foo");
        // removed other assertion
        // removed other assertion
        final HashSet<Pair<Integer, String>> set = new HashSet<>();
        set.add(pair);
        assertTrue(set.contains(pair2));
    }

@Test
    public void testCompatibilityBetweenPairs_4_oe() {
        final Pair<Integer, String> pair = ImmutablePair.of(0, "foo");
        final Pair<Integer, String> pair2 = MutablePair.of(0, "foo");
        // removed other assertion
        // removed other assertion
        final HashSet<Pair<Integer, String>> set = new HashSet<>();
        set.add(pair);
        // removed other assertion

        pair2.setValue("bar");
        assertNotEquals(pair, pair2);
    }

@Test
    public void testCompatibilityBetweenPairs_5_oe() {
        final Pair<Integer, String> pair = ImmutablePair.of(0, "foo");
        final Pair<Integer, String> pair2 = MutablePair.of(0, "foo");
        // removed other assertion
        // removed other assertion
        final HashSet<Pair<Integer, String>> set = new HashSet<>();
        set.add(pair);
        // removed other assertion

        pair2.setValue("bar");
        // removed other assertion
        assertNotEquals(pair.hashCode(), pair2.hashCode());
    }

@Test
    public void testEmptyArrayGenerics_1_oe() {
        final Pair<Integer, String>[] empty = Pair.emptyArray();
        assertEquals(0, empty.length);
    }

@Test
    public void testEmptyArrayLength_1_oe() {
        @SuppressWarnings("unchecked")
        final Pair<Integer, String>[] empty = (Pair<Integer, String>[]) Pair.EMPTY_ARRAY;
        assertEquals(0, empty.length);
    }

@Test
    public void testFormattable_padded_1_oe() {
        final Pair<String, String> pair = Pair.of("Key", "Value");
        assertEquals("         (Key,Value)", String.format("%1$20s", pair));
    }

@Test
    public void testFormattable_simple_1_oe() {
        final Pair<String, String> pair = Pair.of("Key", "Value");
        assertEquals("(Key,Value)", String.format("%1$s", pair));
    }

@Test
    public void testMapEntry_1_oe() {
        final Pair<Integer, String> pair = ImmutablePair.of(0, "foo");
        final HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "foo");
        final Entry<Integer, String> entry = map.entrySet().iterator().next();
        assertEquals(pair, entry);
    }

@Test
    public void testMapEntry_2_oe() {
        final Pair<Integer, String> pair = ImmutablePair.of(0, "foo");
        final HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "foo");
        final Entry<Integer, String> entry = map.entrySet().iterator().next();
        // removed other assertion
        assertEquals(pair.hashCode(), entry.hashCode());
    }

@Test
    public void testPairOfMapEntry_1_oe() {
        final HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "foo");
        final Entry<Integer, String> entry = map.entrySet().iterator().next();
        final Pair<Integer, String> pair = Pair.of(entry);
        assertEquals(entry.getKey(), pair.getLeft());
    }

@Test
    public void testPairOfMapEntry_2_oe() {
        final HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "foo");
        final Entry<Integer, String> entry = map.entrySet().iterator().next();
        final Pair<Integer, String> pair = Pair.of(entry);
        // removed other assertion
        assertEquals(entry.getValue(), pair.getRight());
    }

@Test
    public void testPairOfObjects_1_oe() {
        final Pair<Integer, String> pair = Pair.of(0, "foo");
        assertTrue(pair instanceof ImmutablePair<?, ?>);
    }

@Test
    public void testPairOfObjects_2_oe() {
        final Pair<Integer, String> pair = Pair.of(0, "foo");
        // removed other assertion
        assertEquals(0, ((ImmutablePair<Integer, String>) pair).left.intValue());
    }

@Test
    public void testPairOfObjects_3_oe() {
        final Pair<Integer, String> pair = Pair.of(0, "foo");
        // removed other assertion
        // removed other assertion
        assertEquals("foo", ((ImmutablePair<Integer, String>) pair).right);
    }

@Test
    public void testPairOfObjects_4_oe() {
        final Pair<Integer, String> pair = Pair.of(0, "foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Pair<Object, String> pair2 = Pair.of(null, "bar");
        assertTrue(pair2 instanceof ImmutablePair<?, ?>);
    }

@Test
    public void testPairOfObjects_5_oe() {
        final Pair<Integer, String> pair = Pair.of(0, "foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Pair<Object, String> pair2 = Pair.of(null, "bar");
        // removed other assertion
        assertNull(((ImmutablePair<Object, String>) pair2).left);
    }

@Test
    public void testPairOfObjects_6_oe() {
        final Pair<Integer, String> pair = Pair.of(0, "foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Pair<Object, String> pair2 = Pair.of(null, "bar");
        // removed other assertion
        // removed other assertion
        assertEquals("bar", ((ImmutablePair<Object, String>) pair2).right);
    }

@Test
    public void testPairOfObjects_7_oe() {
        final Pair<Integer, String> pair = Pair.of(0, "foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Pair<Object, String> pair2 = Pair.of(null, "bar");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Pair pair3 = Pair.of(null, null);
        assertNull(pair3.getLeft());
    }

@Test
    public void testPairOfObjects_8_oe() {
        final Pair<Integer, String> pair = Pair.of(0, "foo");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Pair<Object, String> pair2 = Pair.of(null, "bar");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Pair pair3 = Pair.of(null, null);
        // removed other assertion
        assertNull(pair3.getRight());
    }

@Test
    public void testToString_1_oe() {
        final Pair<String, String> pair = Pair.of("Key", "Value");
        assertEquals("(Key,Value)", pair.toString());
    }

@Test
    public void testToStringCustom_1_oe() {
        final Calendar date = Calendar.getInstance();
        date.set(2011, Calendar.APRIL, 25);
        final Pair<String, Calendar> pair = Pair.of("DOB", date);
        assertEquals("Test created on " + "04-25-2011", pair.toString("Test created on %2$tm-%2$td-%2$tY"));
    }

}
