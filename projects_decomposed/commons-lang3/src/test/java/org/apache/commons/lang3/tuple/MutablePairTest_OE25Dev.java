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

import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

/**
 * Test the MutablePair class.
 */
public class MutablePairTest_OE25Dev {

    @Test
    public void testBasic_1_oe() {
        MutablePair<Integer, String> oldPair = new MutablePair<>(0, "foo");
        MutablePair<Integer, String> nowPair;
        for (int i=0; i<4; i++) {
            nowPair = MutablePair.of(oldPair);
            assertEquals(0, nowPair.left.intValue());
    }
    }

    @Test
    public void testBasic_2_oe() {
        MutablePair<Integer, String> oldPair = new MutablePair<>(0, "foo");
        MutablePair<Integer, String> nowPair;
        for (int i=0; i<4; i++) {
            nowPair = MutablePair.of(oldPair);
            // removed other assertion
            assertEquals(0, nowPair.getLeft().intValue());
    }
    }

    @Test
    public void testBasic_3_oe() {
        MutablePair<Integer, String> oldPair = new MutablePair<>(0, "foo");
        MutablePair<Integer, String> nowPair;
        for (int i=0; i<4; i++) {
            nowPair = MutablePair.of(oldPair);
            // removed other assertion
            // removed other assertion
            assertEquals("foo", nowPair.right);
    }
    }

    @Test
    public void testBasic_4_oe() {
        MutablePair<Integer, String> oldPair = new MutablePair<>(0, "foo");
        MutablePair<Integer, String> nowPair;
        for (int i=0; i<4; i++) {
            nowPair = MutablePair.of(oldPair);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("foo", nowPair.getRight());
    }
    }

    @Test
    public void testBasic_5_oe() {
        MutablePair<Integer, String> oldPair = new MutablePair<>(0, "foo");
        MutablePair<Integer, String> nowPair;
        for (int i=0; i<4; i++) {
            nowPair = MutablePair.of(oldPair);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(oldPair, nowPair);
    }
    }

    @Test
    public void testBasic_6_oe() {
        MutablePair<Integer, String> oldPair = new MutablePair<>(0, "foo");
        MutablePair<Integer, String> nowPair;
        for (int i=0; i<4; i++) {
            nowPair = MutablePair.of(oldPair);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            oldPair = nowPair;
        }

        MutablePair<Object, String> oldPair2 = new MutablePair<>(null, "bar");
        MutablePair<Object, String> nowPair2;
        for (int i=0; i<4; i++) {
            nowPair2 = MutablePair.of(oldPair2);
            assertNull(nowPair2.left);
    }
    }

    @Test
    public void testBasic_7_oe() {
        MutablePair<Integer, String> oldPair = new MutablePair<>(0, "foo");
        MutablePair<Integer, String> nowPair;
        for (int i=0; i<4; i++) {
            nowPair = MutablePair.of(oldPair);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            oldPair = nowPair;
        }

        MutablePair<Object, String> oldPair2 = new MutablePair<>(null, "bar");
        MutablePair<Object, String> nowPair2;
        for (int i=0; i<4; i++) {
            nowPair2 = MutablePair.of(oldPair2);
            // removed other assertion
            assertNull(nowPair2.getLeft());
    }
    }

    @Test
    public void testBasic_8_oe() {
        MutablePair<Integer, String> oldPair = new MutablePair<>(0, "foo");
        MutablePair<Integer, String> nowPair;
        for (int i=0; i<4; i++) {
            nowPair = MutablePair.of(oldPair);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            oldPair = nowPair;
        }

        MutablePair<Object, String> oldPair2 = new MutablePair<>(null, "bar");
        MutablePair<Object, String> nowPair2;
        for (int i=0; i<4; i++) {
            nowPair2 = MutablePair.of(oldPair2);
            // removed other assertion
            // removed other assertion
            assertEquals("bar", nowPair2.right);
    }
    }

    @Test
    public void testBasic_9_oe() {
        MutablePair<Integer, String> oldPair = new MutablePair<>(0, "foo");
        MutablePair<Integer, String> nowPair;
        for (int i=0; i<4; i++) {
            nowPair = MutablePair.of(oldPair);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            oldPair = nowPair;
        }

        MutablePair<Object, String> oldPair2 = new MutablePair<>(null, "bar");
        MutablePair<Object, String> nowPair2;
        for (int i=0; i<4; i++) {
            nowPair2 = MutablePair.of(oldPair2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("bar", nowPair2.getRight());
    }
    }

    @Test
    public void testDefault_1_oe() {
        final MutablePair<Integer, String> pair = new MutablePair<>();
        assertNull(pair.getLeft());
    }

    @Test
    public void testDefault_2_oe() {
        final MutablePair<Integer, String> pair = new MutablePair<>();
        // removed other assertion
        assertNull(pair.getRight());
    }

    @Test
    public void testEmptyArrayGenerics_1_oe() {
        final MutablePair<Integer, String>[] empty = MutablePair.emptyArray();
        assertEquals(0, empty.length);
    }

    @Test
    public void testEmptyArrayLength_1_oe() {
        @SuppressWarnings("unchecked")
        final MutablePair<Integer, String>[] empty = (MutablePair<Integer, String>[]) MutablePair.EMPTY_ARRAY;
        assertEquals(0, empty.length);
    }

    @Test
    public void testEquals_1_oe() {
        assertEquals(MutablePair.of(null, "foo"), MutablePair.of(null, "foo"));
    }

    @Test
    public void testEquals_2_oe() {
        // removed other assertion
        assertNotEquals(MutablePair.of("foo", 0), MutablePair.of("foo", null));
    }

    @Test
    public void testEquals_3_oe() {
        // removed other assertion
        // removed other assertion
        assertNotEquals(MutablePair.of("foo", "bar"), MutablePair.of("xyz", "bar"));
    }

    @Test
    public void testEquals_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MutablePair<String, String> p = MutablePair.of("foo", "bar");
        assertEquals(p, p);
    }

    @Test
    public void testEquals_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MutablePair<String, String> p = MutablePair.of("foo", "bar");
        // removed other assertion
        assertNotEquals(p, new Object());
    }

    @Test
    public void testHashCode_1_oe() {
        assertEquals(MutablePair.of(null, "foo").hashCode(), MutablePair.of(null, "foo").hashCode());
    }

    @Test
    public void testMutate_1_oe() {
        final MutablePair<Integer, String> pair = new MutablePair<>(0, "foo");
        pair.setLeft(42);
        pair.setRight("bar");
        assertEquals(42, pair.getLeft().intValue());
    }

    @Test
    public void testMutate_2_oe() {
        final MutablePair<Integer, String> pair = new MutablePair<>(0, "foo");
        pair.setLeft(42);
        pair.setRight("bar");
        // removed other assertion
        assertEquals("bar", pair.getRight());
    }

    @Test
    public void testPairOfMapEntry_1_oe() {
        final HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "foo");
        final Entry<Integer, String> entry = map.entrySet().iterator().next();
        final Pair<Integer, String> pair = MutablePair.of(entry);
        assertEquals(entry.getKey(), pair.getLeft());
    }

    @Test
    public void testPairOfMapEntry_2_oe() {
        final HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "foo");
        final Entry<Integer, String> entry = map.entrySet().iterator().next();
        final Pair<Integer, String> pair = MutablePair.of(entry);
        // removed other assertion
        assertEquals(entry.getValue(), pair.getRight());
    }

    @Test
    public void testPairOfObjects_1_oe() {
        final MutablePair<Integer, String> pair = MutablePair.of(0, "foo");
        assertEquals(0, pair.getLeft().intValue());
    }

    @Test
    public void testPairOfObjects_2_oe() {
        final MutablePair<Integer, String> pair = MutablePair.of(0, "foo");
        // removed other assertion
        assertEquals("foo", pair.getRight());
    }

    @Test
    public void testPairOfObjects_3_oe() {
        final MutablePair<Integer, String> pair = MutablePair.of(0, "foo");
        // removed other assertion
        // removed other assertion
        final MutablePair<Object, String> pair2 = MutablePair.of(null, "bar");
        assertNull(pair2.getLeft());
    }

    @Test
    public void testPairOfObjects_4_oe() {
        final MutablePair<Integer, String> pair = MutablePair.of(0, "foo");
        // removed other assertion
        // removed other assertion
        final MutablePair<Object, String> pair2 = MutablePair.of(null, "bar");
        // removed other assertion
        assertEquals("bar", pair2.getRight());
    }

    @Test
    public void testPairOfObjects_5_oe() {
        final MutablePair<Integer, String> pair = MutablePair.of(0, "foo");
        // removed other assertion
        // removed other assertion
        final MutablePair<Object, String> pair2 = MutablePair.of(null, "bar");
        // removed other assertion
        // removed other assertion
        final MutablePair<?, ?> pair3 = MutablePair.of(null, null);
        assertNull(pair3.left);
    }

    @Test
    public void testPairOfObjects_6_oe() {
        final MutablePair<Integer, String> pair = MutablePair.of(0, "foo");
        // removed other assertion
        // removed other assertion
        final MutablePair<Object, String> pair2 = MutablePair.of(null, "bar");
        // removed other assertion
        // removed other assertion
        final MutablePair<?, ?> pair3 = MutablePair.of(null, null);
        // removed other assertion
        assertNull(pair3.right);
    }

    @Test
    public void testSerialization_1_oe() throws Exception {
        final MutablePair<Integer, String> origPair = MutablePair.of(0, "foo");
        final MutablePair<Integer, String> deserializedPair = SerializationUtils.roundtrip(origPair);
        assertEquals(origPair, deserializedPair);
    }

    @Test
    public void testSerialization_2_oe() throws Exception {
        final MutablePair<Integer, String> origPair = MutablePair.of(0, "foo");
        final MutablePair<Integer, String> deserializedPair = SerializationUtils.roundtrip(origPair);
        // removed other assertion
        assertEquals(origPair.hashCode(), deserializedPair.hashCode());
    }

    @Test
    public void testToString_1_oe() {
        assertEquals("(null,null)", MutablePair.of(null, null).toString());
    }

    @Test
    public void testToString_2_oe() {
        // removed other assertion
        assertEquals("(null,two)", MutablePair.of(null, "two").toString());
    }

    @Test
    public void testToString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("(one,null)", MutablePair.of("one", null).toString());
    }

    @Test
    public void testToString_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("(one,two)", MutablePair.of("one", "two").toString());
    }

}
