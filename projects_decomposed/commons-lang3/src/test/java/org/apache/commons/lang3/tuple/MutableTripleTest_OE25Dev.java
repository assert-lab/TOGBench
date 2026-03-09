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
    public void testBasic() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.FALSE);
        assertEquals(0, triple.getLeft().intValue());
        assertEquals("foo", triple.getMiddle());
        assertEquals(Boolean.FALSE, triple.getRight());
        final MutableTriple<Object, String, String> triple2 = new MutableTriple<>(null, "bar", "hello");
        assertNull(triple2.getLeft());
        assertEquals("bar", triple2.getMiddle());
        assertEquals("hello", triple2.getRight());
    }

    @Test
    public void testDefault() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>();
        assertNull(triple.getLeft());
        assertNull(triple.getMiddle());
        assertNull(triple.getRight());
    }

    @Test
    public void testEmptyArrayGenerics() {
        final MutableTriple<Integer, String, Boolean>[] empty = MutableTriple.emptyArray();
        assertEquals(0, empty.length);
    }

    @Test
    public void testEmptyArrayLength() {
        @SuppressWarnings("unchecked")
        final MutableTriple<Integer, String, Boolean>[] empty = (MutableTriple<Integer, String, Boolean>[]) MutableTriple.EMPTY_ARRAY;
        assertEquals(0, empty.length);
    }

    @Test
    public void testEquals() {
        assertEquals(MutableTriple.of(null, "foo", "baz"), MutableTriple.of(null, "foo", "baz"));
        assertNotEquals(MutableTriple.of("foo", 0, Boolean.TRUE), MutableTriple.of("foo", null, Boolean.TRUE));
        assertNotEquals(MutableTriple.of("foo", "bar", "baz"), MutableTriple.of("xyz", "bar", "baz"));
        assertNotEquals(MutableTriple.of("foo", "bar", "baz"), MutableTriple.of("foo", "bar", "blo"));

        final MutableTriple<String, String, String> p = MutableTriple.of("foo", "bar", "baz");
        assertEquals(p, p);
        assertNotEquals(p, new Object());
    }

    @Test
    public void testHashCode() {
        assertEquals(MutableTriple.of(null, "foo", "baz").hashCode(), MutableTriple.of(null, "foo", "baz").hashCode());
    }

    @Test
    public void testMutate() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.TRUE);
        triple.setLeft(42);
        triple.setMiddle("bar");
        triple.setRight(Boolean.FALSE);
        assertEquals(42, triple.getLeft().intValue());
        assertEquals("bar", triple.getMiddle());
        assertEquals(Boolean.FALSE, triple.getRight());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSerialization() throws Exception {
        final MutableTriple<Integer, String, Boolean> origTriple = MutableTriple.of(0, "foo", Boolean.TRUE);
        final MutableTriple<Integer, String, Boolean> deserializedTriple = SerializationUtils.roundtrip(origTriple);
        assertEquals(origTriple, deserializedTriple);
        assertEquals(origTriple.hashCode(), deserializedTriple.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("(null,null,null)", MutableTriple.of(null, null, null).toString());
        assertEquals("(null,two,null)", MutableTriple.of(null, "two", null).toString());
        assertEquals("(one,null,null)", MutableTriple.of("one", null, null).toString());
        assertEquals("(one,two,null)", MutableTriple.of("one", "two", null).toString());
        assertEquals("(null,two,three)", MutableTriple.of(null, "two", "three").toString());
        assertEquals("(one,null,three)", MutableTriple.of("one", null, "three").toString());
        assertEquals("(one,two,three)", MutableTriple.of("one", "two", "three").toString());
    }

    @Test
    public void testTripleOf() {
        final MutableTriple<Integer, String, Boolean> triple = MutableTriple.of(0, "foo", Boolean.TRUE);
        assertEquals(0, triple.getLeft().intValue());
        assertEquals("foo", triple.getMiddle());
        assertEquals(Boolean.TRUE, triple.getRight());
        final MutableTriple<Object, String, String> triple2 = MutableTriple.of(null, "bar", "hello");
        assertNull(triple2.getLeft());
        assertEquals("bar", triple2.getMiddle());
        assertEquals("hello", triple2.getRight());
    }

    @Test
    public void testBasic_1_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.FALSE);
        assertEquals(0, triple.getLeft().intValue());
    }

    @Test
    public void testBasic_2_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.FALSE);
        assertEquals("foo", triple.getMiddle());
    }

    @Test
    public void testBasic_3_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.FALSE);
        assertEquals(Boolean.FALSE, triple.getRight());
    }

    @Test
    public void testBasic_4_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.FALSE);
        final MutableTriple<Object, String, String> triple2 = new MutableTriple<>(null, "bar", "hello");
        assertNull(triple2.getLeft());
    }

    @Test
    public void testBasic_5_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.FALSE);
        final MutableTriple<Object, String, String> triple2 = new MutableTriple<>(null, "bar", "hello");
        assertEquals("bar", triple2.getMiddle());
    }

    @Test
    public void testBasic_6_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.FALSE);
        final MutableTriple<Object, String, String> triple2 = new MutableTriple<>(null, "bar", "hello");
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
        assertNull(triple.getMiddle());
    }

    @Test
    public void testDefault_3_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>();
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
        assertNotEquals(MutableTriple.of("foo", 0, Boolean.TRUE), MutableTriple.of("foo", null, Boolean.TRUE));
    }

    @Test
    public void testEquals_3_oe() {
        assertNotEquals(MutableTriple.of("foo", "bar", "baz"), MutableTriple.of("xyz", "bar", "baz"));
    }

    @Test
    public void testEquals_4_oe() {
        assertNotEquals(MutableTriple.of("foo", "bar", "baz"), MutableTriple.of("foo", "bar", "blo"));
    }

    @Test
    public void testEquals_5_oe() {

        final MutableTriple<String, String, String> p = MutableTriple.of("foo", "bar", "baz");
        assertEquals(p, p);
    }

    @Test
    public void testEquals_6_oe() {

        final MutableTriple<String, String, String> p = MutableTriple.of("foo", "bar", "baz");
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
        assertEquals("bar", triple.getMiddle());
    }

    @Test
    public void testMutate_3_oe() {
        final MutableTriple<Integer, String, Boolean> triple = new MutableTriple<>(0, "foo", Boolean.TRUE);
        triple.setLeft(42);
        triple.setMiddle("bar");
        triple.setRight(Boolean.FALSE);
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
        assertEquals(origTriple.hashCode(), deserializedTriple.hashCode());
    }

    @Test
    public void testToString_1_oe() {
        assertEquals("(null,null,null)", MutableTriple.of(null, null, null).toString());
    }

    @Test
    public void testToString_2_oe() {
        assertEquals("(null,two,null)", MutableTriple.of(null, "two", null).toString());
    }

    @Test
    public void testToString_3_oe() {
        assertEquals("(one,null,null)", MutableTriple.of("one", null, null).toString());
    }

    @Test
    public void testToString_4_oe() {
        assertEquals("(one,two,null)", MutableTriple.of("one", "two", null).toString());
    }

    @Test
    public void testToString_5_oe() {
        assertEquals("(null,two,three)", MutableTriple.of(null, "two", "three").toString());
    }

    @Test
    public void testToString_6_oe() {
        assertEquals("(one,null,three)", MutableTriple.of("one", null, "three").toString());
    }

    @Test
    public void testToString_7_oe() {
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
        assertEquals("foo", triple.getMiddle());
    }

    @Test
    public void testTripleOf_3_oe() {
        final MutableTriple<Integer, String, Boolean> triple = MutableTriple.of(0, "foo", Boolean.TRUE);
        assertEquals(Boolean.TRUE, triple.getRight());
    }

    @Test
    public void testTripleOf_4_oe() {
        final MutableTriple<Integer, String, Boolean> triple = MutableTriple.of(0, "foo", Boolean.TRUE);
        final MutableTriple<Object, String, String> triple2 = MutableTriple.of(null, "bar", "hello");
        assertNull(triple2.getLeft());
    }

    @Test
    public void testTripleOf_5_oe() {
        final MutableTriple<Integer, String, Boolean> triple = MutableTriple.of(0, "foo", Boolean.TRUE);
        final MutableTriple<Object, String, String> triple2 = MutableTriple.of(null, "bar", "hello");
        assertEquals("bar", triple2.getMiddle());
    }

    @Test
    public void testTripleOf_6_oe() {
        final MutableTriple<Integer, String, Boolean> triple = MutableTriple.of(0, "foo", Boolean.TRUE);
        final MutableTriple<Object, String, String> triple2 = MutableTriple.of(null, "bar", "hello");
        assertEquals("hello", triple2.getRight());
    }

}

