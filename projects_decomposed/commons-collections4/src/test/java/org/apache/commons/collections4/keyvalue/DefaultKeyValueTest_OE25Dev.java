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
package org.apache.commons.collections4.keyvalue;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Test the DefaultKeyValue class.
 *
 * @since 3.0
 */
public class DefaultKeyValueTest_OE25Dev<K, V> {

    private final String key = "name";
    private final String value = "duke";

    //-----------------------------------------------------------------------
    /**
     * Make an instance of DefaultKeyValue with the default (null) key and value.
     * Subclasses should override this method to return a DefaultKeyValue
     * of the type being tested.
     */
    protected DefaultKeyValue<K, V> makeDefaultKeyValue() {
        return new DefaultKeyValue<>(null, null);
    }

    /**
     * Make an instance of DefaultKeyValue with the specified key and value.
     * Subclasses should override this method to return a DefaultKeyValue
     * of the type being tested.
     */
    protected DefaultKeyValue<K, V> makeDefaultKeyValue(final K key, final V value) {
        return new DefaultKeyValue<>(key, value);
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    @Test
    public void testAccessorsAndMutators() {
        final DefaultKeyValue<K, V> kv = makeDefaultKeyValue();

        kv.setKey((K) key);
        assertTrue(kv.getKey() == key);

        kv.setValue((V) value);
        assertTrue(kv.getValue() == value);

        // check that null doesn't do anything funny
        kv.setKey(null);
        assertTrue(kv.getKey() == null);

        kv.setValue(null);
        assertTrue(kv.getValue() == null);

    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSelfReferenceHandling() {
        // test that #setKey and #setValue do not permit
        //  the KVP to contain itself (and thus cause infinite recursion
        //  in #hashCode and #toString)

        final DefaultKeyValue<K, V> kv = makeDefaultKeyValue();

        try {
            kv.setKey((K) kv);
            fail("Should throw an IllegalArgumentException");
        } catch (final IllegalArgumentException iae) {
            // expected to happen...

            // check that the KVP's state has not changed
            assertTrue(kv.getKey() == null && kv.getValue() == null);
        }

        try {
            kv.setValue((V) kv);
            fail("Should throw an IllegalArgumentException");
        } catch (final IllegalArgumentException iae) {
            // expected to happen...

            // check that the KVP's state has not changed
            assertTrue(kv.getKey() == null && kv.getValue() == null);
        }
    }

    /**
     * Subclasses should override this method to test their own constructors.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testConstructors() {
        // 1. test default constructor
        DefaultKeyValue<K, V> kv = new DefaultKeyValue<>();
        assertTrue(kv.getKey() == null && kv.getValue() == null);

        // 2. test key-value constructor
        kv = new DefaultKeyValue<>((K) key, (V) value);
        assertTrue(kv.getKey() == key && kv.getValue() == value);

        // 3. test copy constructor
        final DefaultKeyValue<K, V> kv2 = new DefaultKeyValue<>(kv);
        assertTrue(kv2.getKey() == key && kv2.getValue() == value);

        // test that the KVPs are independent
        kv.setKey(null);
        kv.setValue(null);

        assertTrue(kv2.getKey() == key && kv2.getValue() == value);

        // 4. test Map.Entry constructor
        final Map<K, V> map = new HashMap<>();
        map.put((K) key, (V) value);
        final Map.Entry<K, V> entry = map.entrySet().iterator().next();

        kv = new DefaultKeyValue<>(entry);
        assertTrue(kv.getKey() == key && kv.getValue() == value);

        // test that the KVP is independent of the Map.Entry
        entry.setValue(null);
        assertTrue(kv.getValue() == value);

    }

    @SuppressWarnings("unchecked")
    @Test
    public void testEqualsAndHashCode() {
        // 1. test with object data
        DefaultKeyValue<K, V> kv = makeDefaultKeyValue((K) key, (V) value);
        DefaultKeyValue<K, V> kv2 = makeDefaultKeyValue((K) key, (V) value);

        assertTrue(kv.equals(kv));
        assertTrue(kv.equals(kv2));
        assertTrue(kv.hashCode() == kv2.hashCode());

        // 2. test with nulls
        kv = makeDefaultKeyValue(null, null);
        kv2 = makeDefaultKeyValue(null, null);

        assertTrue(kv.equals(kv));
        assertTrue(kv.equals(kv2));
        assertTrue(kv.hashCode() == kv2.hashCode());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testToString() {
        DefaultKeyValue<K, V> kv = makeDefaultKeyValue((K) key, (V) value);
        assertTrue(kv.toString().equals(kv.getKey() + "=" + kv.getValue()));

        // test with nulls
        kv = makeDefaultKeyValue(null, null);
        assertTrue(kv.toString().equals(kv.getKey() + "=" + kv.getValue()));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testToMapEntry() {
        final DefaultKeyValue<K, V> kv = makeDefaultKeyValue((K) key, (V) value);

        final Map<K, V> map = new HashMap<>();
        map.put(kv.getKey(), kv.getValue());
        final Map.Entry<K, V> entry = map.entrySet().iterator().next();

        assertTrue(entry.equals(kv.toMapEntry()));
        assertTrue(entry.hashCode() == kv.hashCode());
    }

    @Test
    public void testAccessorsAndMutators_1_oe() {
        final DefaultKeyValue<K, V> kv = makeDefaultKeyValue();

        kv.setKey((K) key);
        assertNull(kv.getKey());
    }

    @Test
    public void testAccessorsAndMutators_3_oe() {
        final DefaultKeyValue<K, V> kv = makeDefaultKeyValue();

        kv.setKey((K) key);

        kv.setValue((V) value);

        kv.setKey(null);
        assertNull(kv.getKey());
    }

    @Test
    public void testConstructors_1_oe() {
        DefaultKeyValue<K, V> kv = new DefaultKeyValue<>();
        assertNull(kv.getKey());
    }

    @Test
    public void testConstructors_2_oe() {
        DefaultKeyValue<K, V> kv = new DefaultKeyValue<>();

        kv = new DefaultKeyValue<>((K) key, (V) value);
        assertNull(kv.getKey());
    }

    @Test
    public void testConstructors_3_oe() {
        DefaultKeyValue<K, V> kv = new DefaultKeyValue<>();

        kv = new DefaultKeyValue<>((K) key, (V) value);

        final DefaultKeyValue<K, V> kv2 = new DefaultKeyValue<>(kv);
        assertNotNull(kv2.getKey());
    }

    @Test
    public void testConstructors_4_oe() {
        DefaultKeyValue<K, V> kv = new DefaultKeyValue<>();

        kv = new DefaultKeyValue<>((K) key, (V) value);

        final DefaultKeyValue<K, V> kv2 = new DefaultKeyValue<>(kv);

        kv.setKey(null);
        kv.setValue(null);

        assertNull(kv2.getKey());
    }

    @Test
    public void testEqualsAndHashCode_1_oe() {
        DefaultKeyValue<K, V> kv = makeDefaultKeyValue((K) key, (V) value);
        DefaultKeyValue<K, V> kv2 = makeDefaultKeyValue((K) key, (V) value);

        assertEquals(false, kv2.equals(kv));
    }

    @Test
    public void testEqualsAndHashCode_2_oe() {
        DefaultKeyValue<K, V> kv = makeDefaultKeyValue((K) key, (V) value);
        DefaultKeyValue<K, V> kv2 = makeDefaultKeyValue((K) key, (V) value);

        assertEquals(false, kv2.equals(kv));
    }

    @Test
    public void testEqualsAndHashCode_3_oe() {
        DefaultKeyValue<K, V> kv = makeDefaultKeyValue((K) key, (V) value);
        DefaultKeyValue<K, V> kv2 = makeDefaultKeyValue((K) key, (V) value);

        assertEquals(false, kv2.equals(kv));
    }

    @Test
    public void testEqualsAndHashCode_4_oe() {
        DefaultKeyValue<K, V> kv = makeDefaultKeyValue((K) key, (V) value);
        DefaultKeyValue<K, V> kv2 = makeDefaultKeyValue((K) key, (V) value);


        kv = makeDefaultKeyValue(null, null);
        kv2 = makeDefaultKeyValue(null, null);

        assertEquals(0, kv2.hashCode());
    }

    @Test
    public void testEqualsAndHashCode_5_oe() {
        DefaultKeyValue<K, V> kv = makeDefaultKeyValue((K) key, (V) value);
        DefaultKeyValue<K, V> kv2 = makeDefaultKeyValue((K) key, (V) value);


        kv = makeDefaultKeyValue(null, null);
        kv2 = makeDefaultKeyValue(null, null);

        assertEquals(0, kv2.hashCode());
    }

    @Test
    public void testEqualsAndHashCode_6_oe() {
        DefaultKeyValue<K, V> kv = makeDefaultKeyValue((K) key, (V) value);
        DefaultKeyValue<K, V> kv2 = makeDefaultKeyValue((K) key, (V) value);


        kv = makeDefaultKeyValue(null, null);
        kv2 = makeDefaultKeyValue(null, null);

        assertEquals(0, kv.hashCode());  assertEquals(0, kv2.hashCode());
    }

    @Test
    public void testToString_1_oe() {
        DefaultKeyValue<K, V> kv = makeDefaultKeyValue((K) key, (V) value);
        assertEquals(0, kv.hashCode());
    }

    @Test
    public void testToString_2_oe() {
        DefaultKeyValue<K, V> kv = makeDefaultKeyValue((K) key, (V) value);

        kv = makeDefaultKeyValue(null, null);
        assertEquals(0, kv.hashCode());
    }

    @Test
    public void testToMapEntry_1_oe() {
        final DefaultKeyValue<K, V> kv = makeDefaultKeyValue((K) key, (V) value);

        final Map<K, V> map = new HashMap<>();
        map.put(kv.getKey(), kv.getValue());
        final Map.Entry<K, V> entry = map.entrySet().iterator().next();

        assertEquals(0, map.size());
    }

    @Test
    public void testToMapEntry_2_oe() {
        final DefaultKeyValue<K, V> kv = makeDefaultKeyValue((K) key, (V) value);

        final Map<K, V> map = new HashMap<>();
        map.put(kv.getKey(), kv.getValue());
        final Map.Entry<K, V> entry = map.entrySet().iterator().next();

        assertEquals(1, map.size());
    }

}
