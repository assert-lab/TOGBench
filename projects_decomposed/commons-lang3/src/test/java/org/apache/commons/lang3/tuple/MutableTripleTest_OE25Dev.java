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

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

/**
 * Test the MutableTriple class.
 */
public class MutableTripleTest_OE25Dev {

@Test
    public void testBasic_1_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.FALSE);
        assertEquals(0, triple.getLeft().intValue());
    }

@Test
    public void testBasic_2_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.FALSE);
        // removed other assertion
        assertEquals("foo", triple.getMiddle());
    }

@Test
    public void testBasic_3_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.FALSE);
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.FALSE, triple.getRight());
    }

@Test
    public void testBasic_4_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.FALSE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final MutableTriple<Object, String, String> triple2 = new MutableTriple<>(null, "bar", "hello");
        assertNull(triple2.getLeft());
    }

@Test
    public void testBasic_5_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.FALSE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final MutableTriple<Object, String, String> triple2 = new MutableTriple<>(null, "bar", "hello");
        // removed other assertion
        assertEquals("bar", triple2.getMiddle());
    }

@Test
    public void testBasic_6_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.FALSE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final MutableTriple<Object, String, String> triple2 = new MutableTriple<>(null, "bar", "hello");
        // removed other assertion
        // removed other assertion
        assertEquals("hello", triple2.getRight());
    }

@Test
    public void testDefault_1_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>();
        assertNull(triple.getLeft());
    }

@Test
    public void testDefault_2_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>();
        // removed other assertion
        assertNull(triple.getMiddle());
    }

@Test
    public void testDefault_3_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>();
        // removed other assertion
        // removed other assertion
        assertNull(triple.getRight());
    }

@Test
    public void testEmptyArrayGenerics_1_oe() {
        final MutableTriple<Integer, String, Boolean>[] empty = MutableTriple.emptyArray();
        assertEquals(0, empty.length);
    }

@Test
    public void testEmptyArrayLength_1_oe() {
        @SuppressWarnings("unchecked")
        final MutableTriple<Integer, String, Boolean>[] empty = (MutableTriple<Integer, String, Boolean>[]) MutableTriple.EMPTY_ARRAY;
        assertEquals(0, empty.length);
    }

@Test
    public void testEquals_1_oe() {
        assertEquals(MutableTriple.of(null, "foo", "baz"), MutableTriple.of(null, "foo", "baz"));
    }

@Test
    public void testEquals_2_oe() {
        // removed other assertion
        assertNotEquals(MutableTriple.of("foo", 0, Boolean.TRUE), MutableTriple.of("foo", null, Boolean.TRUE));
    }

@Test
    public void testEquals_3_oe() {
        // removed other assertion
        // removed other assertion
        assertNotEquals(MutableTriple.of("foo", "bar", "baz"), MutableTriple.of("xyz", "bar", "baz"));
    }

@Test
    public void testEquals_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(MutableTriple.of("foo", "bar", "baz"), MutableTriple.of("foo", "bar", "blo"));
    }

@Test
    public void testEquals_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MutableTriple<String, String, String> p = MutableTriple.of("foo", "bar", "baz");
        assertEquals(p, p);
    }

@Test
    public void testEquals_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MutableTriple<String, String, String> p = MutableTriple.of("foo", "bar", "baz");
        // removed other assertion
        assertNotEquals(p, new Object());
    }

@Test
    public void testHashCode_1_oe() {
        assertEquals(MutableTriple.of(null, "foo", "baz").hashCode(), MutableTriple.of(null, "foo", "baz").hashCode());
    }

@Test
    public void testMutate_1_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.TRUE);
        triple.setLeft(42);
        triple.setMiddle("bar");
        triple.setRight(Boolean.FALSE);
        assertEquals(42, triple.getLeft().intValue());
    }

@Test
    public void testMutate_2_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.TRUE);
        triple.setLeft(42);
        triple.setMiddle("bar");
        triple.setRight(Boolean.FALSE);
        // removed other assertion
        assertEquals("bar", triple.getMiddle());
    }

@Test
    public void testMutate_3_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.TRUE);
        triple.setLeft(42);
        triple.setMiddle("bar");
        triple.setRight(Boolean.FALSE);
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.FALSE, triple.getRight());
    }

@Test
    @SuppressWarnings("unchecked")
    public void testSerialization_1_oe() throws Exception {
        final MutableTriple<Integer, String, Boolean> origTriple = MutableTriple.of(0, "foo", Boolean.TRUE);
        final MutableTriple<Integer, String, Boolean> deserializedTriple = SerializationUtils.roundtrip(origTriple);
        assertEquals(origTriple, deserializedTriple);
    }

@Test
    @SuppressWarnings("unchecked")
    public void testSerialization_2_oe() throws Exception {
        final MutableTriple<Integer, String, Boolean> origTriple = MutableTriple.of(0, "foo", Boolean.TRUE);
        final MutableTriple<Integer, String, Boolean> deserializedTriple = SerializationUtils.roundtrip(origTriple);
        // removed other assertion
        assertEquals(origTriple.hashCode(), deserializedTriple.hashCode());
    }

@Test
    public void testToString_1_oe() {
        assertEquals("(null,null,null)", MutableTriple.of(null, null, null).toString());
    }

@Test
    public void testToString_2_oe() {
        // removed other assertion
        assertEquals("(null,two,null)", MutableTriple.of(null, "two", null).toString());
    }

@Test
    public void testToString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("(one,null,null)", MutableTriple.of("one", null, null).toString());
    }

@Test
    public void testToString_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("(one,two,null)", MutableTriple.of("one", "two", null).toString());
    }

@Test
    public void testToString_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("(null,two,three)", MutableTriple.of(null, "two", "three").toString());
    }

@Test
    public void testToString_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("(one,null,three)", MutableTriple.of("one", null, "three").toString());
    }

@Test
    public void testToString_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("(one,two,three)", MutableTriple.of("one", "two", "three").toString());
    }

@Test
    public void testTripleOf_1_oe() {
        final MutableTriple<Integer, String, Boolean> triple = MutableTriple.of(0, "foo", Boolean.TRUE);
        assertEquals(0, triple.getLeft().intValue());
    }

@Test
    public void testTripleOf_2_oe() {
        final MutableTriple<Integer, String, Boolean> triple = MutableTriple.of(0, "foo", Boolean.TRUE);
        // removed other assertion
        assertEquals("foo", triple.getMiddle());
    }

@Test
    public void testTripleOf_3_oe() {
        final MutableTriple<Integer, String, Boolean> triple = MutableTriple.of(0, "foo", Boolean.TRUE);
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.TRUE, triple.getRight());
    }

@Test
    public void testTripleOf_4_oe() {
        final MutableTriple<Integer, String, Boolean> triple = MutableTriple.of(0, "foo", Boolean.TRUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final MutableTriple<Object, String, String> triple2 = MutableTriple.of(null, "bar", "hello");
        assertNull(triple2.getLeft());
    }

@Test
    public void testTripleOf_5_oe() {
        final MutableTriple<Integer, String, Boolean> triple = MutableTriple.of(0, "foo", Boolean.TRUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final MutableTriple<Object, String, String> triple2 = MutableTriple.of(null, "bar", "hello");
        // removed other assertion
        assertEquals("bar", triple2.getMiddle());
    }

@Test
    public void testTripleOf_6_oe() {
        final MutableTriple<Integer, String, Boolean> triple = MutableTriple.of(0, "foo", Boolean.TRUE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final MutableTriple<Object, String, String> triple2 = MutableTriple.of(null, "bar", "hello");
        // removed other assertion
        // removed other assertion
        assertEquals("hello", triple2.getRight());
    }

}

