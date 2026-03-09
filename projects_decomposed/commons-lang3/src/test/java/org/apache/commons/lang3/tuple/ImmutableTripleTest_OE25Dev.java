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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

/**
 * Test the Triple class.
 */
public class ImmutableTripleTest_OE25Dev {

    @Test
    public void testBasic() {
        final ImmutableTriple<Integer, String, Boolean> triple = new ImmutableTriple<>(0, "foo", Boolean.TRUE);
        assertEquals(0, triple.left.intValue());
        assertEquals(0, triple.getLeft().intValue());
        assertEquals("foo", triple.middle);
        assertEquals("foo", triple.getMiddle());
        assertEquals(Boolean.TRUE, triple.right);
        assertEquals(Boolean.TRUE, triple.getRight());
        final ImmutableTriple<Object, String, Integer> triple2 = new ImmutableTriple<>(null, "bar", 42);
        assertNull(triple2.left);
        assertNull(triple2.getLeft());
        assertEquals("bar", triple2.middle);
        assertEquals("bar", triple2.getMiddle());
        assertEquals(new Integer(42), triple2.right);
        assertEquals(new Integer(42), triple2.getRight());
    }

    @Test
    public void testEmptyArrayGenerics() {
        final ImmutableTriple<Integer, String, Boolean>[] empty = ImmutableTriple.emptyArray();
        assertEquals(0, empty.length);
    }

    @Test
    public void testEmptyArrayLength() {
        @SuppressWarnings("unchecked")
        final ImmutableTriple<Integer, String, Boolean>[] empty = (ImmutableTriple<Integer, String, Boolean>[]) ImmutableTriple.EMPTY_ARRAY;
        assertEquals(0, empty.length);
    }

    @Test
    public void testEquals() {
        assertEquals(ImmutableTriple.of(null, "foo", 42), ImmutableTriple.of(null, "foo", 42));
        assertNotEquals(ImmutableTriple.of("foo", 0, Boolean.TRUE), ImmutableTriple.of("foo", null, null));
        assertNotEquals(ImmutableTriple.of("foo", "bar", "baz"), ImmutableTriple.of("xyz", "bar", "blo"));

        final ImmutableTriple<String, String, String> p = ImmutableTriple.of("foo", "bar", "baz");
        assertEquals(p, p);
        assertNotEquals(p, new Object());
    }

    @Test
    public void testHashCode() {
        assertEquals(ImmutableTriple.of(null, "foo", Boolean.TRUE).hashCode(), ImmutableTriple.of(null, "foo", Boolean.TRUE).hashCode());
    }

    @Test
    public void testNullTripleEquals() {
        assertEquals(ImmutableTriple.nullTriple(), ImmutableTriple.nullTriple());
    }

    @Test
    public void testNullTripleLeft() {
        assertNull(ImmutableTriple.nullTriple().getLeft());
    }

    @Test
    public void testNullTripleMiddle() {
        assertNull(ImmutableTriple.nullTriple().getMiddle());
    }

    @Test
    public void testNullTripleRight() {
        assertNull(ImmutableTriple.nullTriple().getRight());
    }

    @Test
    public void testNullTripleSame() {
        assertSame(ImmutableTriple.nullTriple(), ImmutableTriple.nullTriple());
    }

    @Test
    public void testNullTripleTyped() {
        // No compiler warnings
        // How do we assert that?
        final ImmutableTriple<String, String, String> triple = ImmutableTriple.nullTriple();
        assertNotNull(triple);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSerialization() throws Exception {
        final ImmutableTriple<Integer, String, Boolean> origTriple = ImmutableTriple.of(0, "foo", Boolean.TRUE);
        final ImmutableTriple<Integer, String, Boolean> deserializedTriple = SerializationUtils.roundtrip(origTriple);
        assertEquals(origTriple, deserializedTriple);
        assertEquals(origTriple.hashCode(), deserializedTriple.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("(null,null,null)", ImmutableTriple.of(null, null, null).toString());
        assertEquals("(null,two,null)", ImmutableTriple.of(null, "two", null).toString());
        assertEquals("(one,null,null)", ImmutableTriple.of("one", null, null).toString());
        assertEquals("(one,two,null)", ImmutableTriple.of("one", "two", null).toString());
        assertEquals("(null,two,three)", ImmutableTriple.of(null, "two", "three").toString());
        assertEquals("(one,null,three)", ImmutableTriple.of("one", null, "three").toString());
        assertEquals("(one,two,three)", MutableTriple.of("one", "two", "three").toString());
    }

    @Test
    public void testTripleOf() {
        final ImmutableTriple<Integer, String, Boolean> triple = ImmutableTriple.of(0, "foo", Boolean.FALSE);
        assertEquals(0, triple.left.intValue());
        assertEquals(0, triple.getLeft().intValue());
        assertEquals("foo", triple.middle);
        assertEquals("foo", triple.getMiddle());
        assertEquals(Boolean.FALSE, triple.right);
        assertEquals(Boolean.FALSE, triple.getRight());
        final ImmutableTriple<Object, String, Boolean> triple2 = ImmutableTriple.of(null, "bar", Boolean.TRUE);
        assertNull(triple2.left);
        assertNull(triple2.getLeft());
        assertEquals("bar", triple2.middle);
        assertEquals("bar", triple2.getMiddle());
        assertEquals(Boolean.TRUE, triple2.right);
        assertEquals(Boolean.TRUE, triple2.getRight());
    }

    @Test
    public void testUseAsKeyOfHashMap() {
        final HashMap<ImmutableTriple<Object, Object, Object>, String> map = new HashMap<>();
        final Object o1 = new Object();
        final Object o2 = new Object();
        final Object o3 = new Object();
        final ImmutableTriple<Object, Object, Object> key1 = ImmutableTriple.of(o1, o2, o3);
        final String value1 = "a1";
        map.put(key1, value1);
        assertEquals(value1, map.get(key1));
        assertEquals(value1, map.get(ImmutableTriple.of(o1, o2, o3)));
    }

    @Test
    public void testUseAsKeyOfTreeMap() {
        final TreeMap<ImmutableTriple<Integer, Integer, Integer>, String> map = new TreeMap<>();
        map.put(ImmutableTriple.of(0, 1, 2), "012");
        map.put(ImmutableTriple.of(0, 1, 1), "011");
        map.put(ImmutableTriple.of(0, 0, 1), "001");
        final ArrayList<ImmutableTriple<Integer, Integer, Integer>> expected = new ArrayList<>();
        expected.add(ImmutableTriple.of(0, 0, 1));
        expected.add(ImmutableTriple.of(0, 1, 1));
        expected.add(ImmutableTriple.of(0, 1, 2));
        final Iterator<Entry<ImmutableTriple<Integer, Integer, Integer>, String>> it = map.entrySet().iterator();
        for (final ImmutableTriple<Integer, Integer, Integer> item : expected) {
            final Entry<ImmutableTriple<Integer, Integer, Integer>, String> entry = it.next();
            assertEquals(item, entry.getKey());
            assertEquals(item.getLeft() + "" + item.getMiddle() + "" + item.getRight(), entry.getValue());
        }
    }

    @Test
    public void testBasic_1_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = new ImmutableTriple<>(0, "foo", Boolean.TRUE);
        assertEquals(0, triple.left.intValue());
    }

    @Test
    public void testBasic_2_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = new ImmutableTriple<>(0, "foo", Boolean.TRUE);
        assertEquals(0, triple.getLeft().intValue());
    }

    @Test
    public void testBasic_3_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = new ImmutableTriple<>(0, "foo", Boolean.TRUE);
        assertEquals("foo", triple.middle);
    }

    @Test
    public void testBasic_4_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = new ImmutableTriple<>(0, "foo", Boolean.TRUE);
        assertEquals("foo", triple.getMiddle());
    }

    @Test
    public void testBasic_5_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = new ImmutableTriple<>(0, "foo", Boolean.TRUE);
        assertEquals(Boolean.TRUE, triple.right);
    }

    @Test
    public void testBasic_6_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = new ImmutableTriple<>(0, "foo", Boolean.TRUE);
        assertEquals(Boolean.TRUE, triple.getRight());
    }

    @Test
    public void testBasic_7_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = new ImmutableTriple<>(0, "foo", Boolean.TRUE);
        final ImmutableTriple<Object, String, Integer> triple2 = new ImmutableTriple<>(null, "bar", 42);
        assertNull(triple2.left);
    }

    @Test
    public void testBasic_8_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = new ImmutableTriple<>(0, "foo", Boolean.TRUE);
        final ImmutableTriple<Object, String, Integer> triple2 = new ImmutableTriple<>(null, "bar", 42);
        assertNull(triple2.getLeft());
    }

    @Test
    public void testBasic_9_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = new ImmutableTriple<>(0, "foo", Boolean.TRUE);
        final ImmutableTriple<Object, String, Integer> triple2 = new ImmutableTriple<>(null, "bar", 42);
        assertEquals("bar", triple2.middle);
    }

    @Test
    public void testBasic_10_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = new ImmutableTriple<>(0, "foo", Boolean.TRUE);
        final ImmutableTriple<Object, String, Integer> triple2 = new ImmutableTriple<>(null, "bar", 42);
        assertEquals("bar", triple2.getMiddle());
    }

    @Test
    public void testBasic_11_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = new ImmutableTriple<>(0, "foo", Boolean.TRUE);
        final ImmutableTriple<Object, String, Integer> triple2 = new ImmutableTriple<>(null, "bar", 42);
        assertEquals(new Integer(42), triple2.right);
    }

    @Test
    public void testBasic_12_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = new ImmutableTriple<>(0, "foo", Boolean.TRUE);
        final ImmutableTriple<Object, String, Integer> triple2 = new ImmutableTriple<>(null, "bar", 42);
        assertEquals(new Integer(42), triple2.getRight());
    }

    @Test
    public void testEmptyArrayGenerics_1_oe() {
        final ImmutableTriple<Integer, String, Boolean>[] empty = ImmutableTriple.emptyArray();
        assertEquals(0, empty.length);
    }

    @Test
    public void testEmptyArrayLength_1_oe() {
        @SuppressWarnings("unchecked")
        final ImmutableTriple<Integer, String, Boolean>[] empty = (ImmutableTriple<Integer, String, Boolean>[]) ImmutableTriple.EMPTY_ARRAY;
        assertEquals(0, empty.length);
    }

    @Test
    public void testEquals_1_oe() {
        assertEquals(ImmutableTriple.of(null, "foo", 42), ImmutableTriple.of(null, "foo", 42));
    }

    @Test
    public void testEquals_2_oe() {
        assertNotEquals(ImmutableTriple.of("foo", 0, Boolean.TRUE), ImmutableTriple.of("foo", null, null));
    }

    @Test
    public void testEquals_3_oe() {
        assertNotEquals(ImmutableTriple.of("foo", "bar", "baz"), ImmutableTriple.of("xyz", "bar", "blo"));
    }

    @Test
    public void testEquals_4_oe() {

        final ImmutableTriple<String, String, String> p = ImmutableTriple.of("foo", "bar", "baz");
        assertEquals(p, p);
    }

    @Test
    public void testEquals_5_oe() {

        final ImmutableTriple<String, String, String> p = ImmutableTriple.of("foo", "bar", "baz");
        assertNotEquals(p, new Object());
    }

    @Test
    public void testHashCode_1_oe() {
        assertEquals(ImmutableTriple.of(null, "foo", Boolean.TRUE).hashCode(), ImmutableTriple.of(null, "foo", Boolean.TRUE).hashCode());
    }

    @Test
    public void testNullTripleEquals_1_oe() {
        assertEquals(ImmutableTriple.nullTriple(), ImmutableTriple.nullTriple());
    }

    @Test
    public void testNullTripleLeft_1_oe() {
        assertNull(ImmutableTriple.nullTriple().getLeft());
    }

    @Test
    public void testNullTripleMiddle_1_oe() {
        assertNull(ImmutableTriple.nullTriple().getMiddle());
    }

    @Test
    public void testNullTripleRight_1_oe() {
        assertNull(ImmutableTriple.nullTriple().getRight());
    }

    @Test
    public void testNullTripleSame_1_oe() {
        assertSame(ImmutableTriple.nullTriple(), ImmutableTriple.nullTriple());
    }

    @Test
    public void testNullTripleTyped_1_oe() {
        final ImmutableTriple<String, String, String> triple = ImmutableTriple.nullTriple();
        assertNotNull(triple);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSerialization_1_oe() throws Exception {
        final ImmutableTriple<Integer, String, Boolean> origTriple = ImmutableTriple.of(0, "foo", Boolean.TRUE);
        final ImmutableTriple<Integer, String, Boolean> deserializedTriple = SerializationUtils.roundtrip(origTriple);
        assertEquals(origTriple, deserializedTriple);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSerialization_2_oe() throws Exception {
        final ImmutableTriple<Integer, String, Boolean> origTriple = ImmutableTriple.of(0, "foo", Boolean.TRUE);
        final ImmutableTriple<Integer, String, Boolean> deserializedTriple = SerializationUtils.roundtrip(origTriple);
        assertEquals(origTriple.hashCode(), deserializedTriple.hashCode());
    }

    @Test
    public void testToString_1_oe() {
        assertEquals("(null,null,null)", ImmutableTriple.of(null, null, null).toString());
    }

    @Test
    public void testToString_2_oe() {
        assertEquals("(null,two,null)", ImmutableTriple.of(null, "two", null).toString());
    }

    @Test
    public void testToString_3_oe() {
        assertEquals("(one,null,null)", ImmutableTriple.of("one", null, null).toString());
    }

    @Test
    public void testToString_4_oe() {
        assertEquals("(one,two,null)", ImmutableTriple.of("one", "two", null).toString());
    }

    @Test
    public void testToString_5_oe() {
        assertEquals("(null,two,three)", ImmutableTriple.of(null, "two", "three").toString());
    }

    @Test
    public void testToString_6_oe() {
        assertEquals("(one,null,three)", ImmutableTriple.of("one", null, "three").toString());
    }

    @Test
    public void testToString_7_oe() {
        assertEquals("(one,two,three)", MutableTriple.of("one", "two", "three").toString());
    }

    @Test
    public void testTripleOf_1_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = ImmutableTriple.of(0, "foo", Boolean.FALSE);
        assertEquals(0, triple.left.intValue());
    }

    @Test
    public void testTripleOf_2_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = ImmutableTriple.of(0, "foo", Boolean.FALSE);
        assertEquals(0, triple.getLeft().intValue());
    }

    @Test
    public void testTripleOf_3_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = ImmutableTriple.of(0, "foo", Boolean.FALSE);
        assertEquals("foo", triple.middle);
    }

    @Test
    public void testTripleOf_4_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = ImmutableTriple.of(0, "foo", Boolean.FALSE);
        assertEquals("foo", triple.getMiddle());
    }

    @Test
    public void testTripleOf_5_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = ImmutableTriple.of(0, "foo", Boolean.FALSE);
        assertEquals(Boolean.FALSE, triple.right);
    }

    @Test
    public void testTripleOf_6_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = ImmutableTriple.of(0, "foo", Boolean.FALSE);
        assertEquals(Boolean.FALSE, triple.getRight());
    }

    @Test
    public void testTripleOf_7_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = ImmutableTriple.of(0, "foo", Boolean.FALSE);
        final ImmutableTriple<Object, String, Boolean> triple2 = ImmutableTriple.of(null, "bar", Boolean.TRUE);
        assertNull(triple2.left);
    }

    @Test
    public void testTripleOf_8_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = ImmutableTriple.of(0, "foo", Boolean.FALSE);
        final ImmutableTriple<Object, String, Boolean> triple2 = ImmutableTriple.of(null, "bar", Boolean.TRUE);
        assertNull(triple2.getLeft());
    }

    @Test
    public void testTripleOf_9_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = ImmutableTriple.of(0, "foo", Boolean.FALSE);
        final ImmutableTriple<Object, String, Boolean> triple2 = ImmutableTriple.of(null, "bar", Boolean.TRUE);
        assertEquals("bar", triple2.middle);
    }

    @Test
    public void testTripleOf_10_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = ImmutableTriple.of(0, "foo", Boolean.FALSE);
        final ImmutableTriple<Object, String, Boolean> triple2 = ImmutableTriple.of(null, "bar", Boolean.TRUE);
        assertEquals("bar", triple2.getMiddle());
    }

    @Test
    public void testTripleOf_11_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = ImmutableTriple.of(0, "foo", Boolean.FALSE);
        final ImmutableTriple<Object, String, Boolean> triple2 = ImmutableTriple.of(null, "bar", Boolean.TRUE);
        assertEquals(Boolean.TRUE, triple2.right);
    }

    @Test
    public void testTripleOf_12_oe() {
        final ImmutableTriple<Integer, String, Boolean> triple = ImmutableTriple.of(0, "foo", Boolean.FALSE);
        final ImmutableTriple<Object, String, Boolean> triple2 = ImmutableTriple.of(null, "bar", Boolean.TRUE);
        assertEquals(Boolean.TRUE, triple2.getRight());
    }

    @Test
    public void testUseAsKeyOfHashMap_1_oe() {
        final HashMap<ImmutableTriple<Object, Object, Object>, String> map = new HashMap<>();
        final Object o1 = new Object();
        final Object o2 = new Object();
        final Object o3 = new Object();
        final ImmutableTriple<Object, Object, Object> key1 = ImmutableTriple.of(o1, o2, o3);
        final String value1 = "a1";
        map.put(key1, value1);
        assertEquals(value1, map.get(key1));
    }

    @Test
    public void testUseAsKeyOfHashMap_2_oe() {
        final HashMap<ImmutableTriple<Object, Object, Object>, String> map = new HashMap<>();
        final Object o1 = new Object();
        final Object o2 = new Object();
        final Object o3 = new Object();
        final ImmutableTriple<Object, Object, Object> key1 = ImmutableTriple.of(o1, o2, o3);
        final String value1 = "a1";
        map.put(key1, value1);
        assertEquals(value1, map.get(ImmutableTriple.of(o1, o2, o3)));
    }

    @Test
    public void testUseAsKeyOfTreeMap_1_oe() {
        final TreeMap<ImmutableTriple<Integer, Integer, Integer>, String> map = new TreeMap<>();
        map.put(ImmutableTriple.of(0, 1, 2), "012");
        map.put(ImmutableTriple.of(0, 1, 1), "011");
        map.put(ImmutableTriple.of(0, 0, 1), "001");
        final ArrayList<ImmutableTriple<Integer, Integer, Integer>> expected = new ArrayList<>();
        expected.add(ImmutableTriple.of(0, 0, 1));
        expected.add(ImmutableTriple.of(0, 1, 1));
        expected.add(ImmutableTriple.of(0, 1, 2));
        final Iterator<Entry<ImmutableTriple<Integer, Integer, Integer>, String>> it = map.entrySet().iterator();
        for (final ImmutableTriple<Integer, Integer, Integer> item : expected) {
            final Entry<ImmutableTriple<Integer, Integer, Integer>, String> entry = it.next();
            assertEquals(item, entry.getKey());
    }
    }

    @Test
    public void testUseAsKeyOfTreeMap_2_oe() {
        final TreeMap<ImmutableTriple<Integer, Integer, Integer>, String> map = new TreeMap<>();
        map.put(ImmutableTriple.of(0, 1, 2), "012");
        map.put(ImmutableTriple.of(0, 1, 1), "011");
        map.put(ImmutableTriple.of(0, 0, 1), "001");
        final ArrayList<ImmutableTriple<Integer, Integer, Integer>> expected = new ArrayList<>();
        expected.add(ImmutableTriple.of(0, 0, 1));
        expected.add(ImmutableTriple.of(0, 1, 1));
        expected.add(ImmutableTriple.of(0, 1, 2));
        final Iterator<Entry<ImmutableTriple<Integer, Integer, Integer>, String>> it = map.entrySet().iterator();
        for (final ImmutableTriple<Integer, Integer, Integer> item : expected) {
            final Entry<ImmutableTriple<Integer, Integer, Integer>, String> entry = it.next();
            assertEquals(item.getLeft() + "" + item.getMiddle() + "" + item.getRight(), entry.getValue());
    }
    }

}

