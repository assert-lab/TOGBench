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

import java.util.Map;

import org.apache.commons.collections4.KeyValue;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the DefaultMapEntry class.
 *
 * @since 3.0
 */
public class DefaultMapEntryTest_OE25Dev<K, V> extends AbstractMapEntryTest<K, V> {

    //-----------------------------------------------------------------------
    /**
     * Make an instance of Map.Entry with the default (null) key and value.
     * Subclasses should override this method to return a Map.Entry
     * of the type being tested.
     */
    @Override
    public Map.Entry<K, V> makeMapEntry() {
        return new DefaultMapEntry<>(null, null);
    }

    /**
     * Make an instance of Map.Entry with the specified key and value.
     * Subclasses should override this method to return a Map.Entry
     * of the type being tested.
     */
    @Override
    public Map.Entry<K, V> makeMapEntry(final K key, final V value) {
        return new DefaultMapEntry<>(key, value);
    }

    //-----------------------------------------------------------------------
    /**
     * Subclasses should override this method.
     *
     */

    @Test
    public void testConstructors_1_oe() {
        // 1. test key-value constructor
        final Map.Entry<K, V> entry = new DefaultMapEntry<>((K) key, (V) value);
        assertSame(key, entry.getKey());
    }

    @Test
    public void testConstructors_2_oe() {
        // 1. test key-value constructor
        final Map.Entry<K, V> entry = new DefaultMapEntry<>((K) key, (V) value);
        // removed other assertion
        assertSame(value, entry.getValue());
    }

    @Test
    public void testConstructors_3_oe() {
        // 1. test key-value constructor
        final Map.Entry<K, V> entry = new DefaultMapEntry<>((K) key, (V) value);
        // removed other assertion
        // removed other assertion

        // 2. test pair constructor
        final KeyValue<K, V> pair = new DefaultKeyValue<>((K) key, (V) value);
        assertSame(key, pair.getKey());
    }

    @Test
    public void testConstructors_4_oe() {
        // 1. test key-value constructor
        final Map.Entry<K, V> entry = new DefaultMapEntry<>((K) key, (V) value);
        // removed other assertion
        // removed other assertion

        // 2. test pair constructor
        final KeyValue<K, V> pair = new DefaultKeyValue<>((K) key, (V) value);
        // removed other assertion
        assertSame(value, pair.getValue());
    }

    @Test
    public void testConstructors_5_oe() {
        // 1. test key-value constructor
        final Map.Entry<K, V> entry = new DefaultMapEntry<>((K) key, (V) value);
        // removed other assertion
        // removed other assertion

        // 2. test pair constructor
        final KeyValue<K, V> pair = new DefaultKeyValue<>((K) key, (V) value);
        // removed other assertion
        // removed other assertion

        // 3. test copy constructor
        final Map.Entry<K, V> entry2 = new DefaultMapEntry<>(entry);
        assertSame(key, entry2.getKey());
    }

    @Test
    public void testConstructors_6_oe() {
        // 1. test key-value constructor
        final Map.Entry<K, V> entry = new DefaultMapEntry<>((K) key, (V) value);
        // removed other assertion
        // removed other assertion

        // 2. test pair constructor
        final KeyValue<K, V> pair = new DefaultKeyValue<>((K) key, (V) value);
        // removed other assertion
        // removed other assertion

        // 3. test copy constructor
        final Map.Entry<K, V> entry2 = new DefaultMapEntry<>(entry);
        // removed other assertion
        assertSame(value, entry2.getValue());
    }

    @Test
    public void testConstructors_7_oe() {
        // 1. test key-value constructor
        final Map.Entry<K, V> entry = new DefaultMapEntry<>((K) key, (V) value);
        // removed other assertion
        // removed other assertion

        // 2. test pair constructor
        final KeyValue<K, V> pair = new DefaultKeyValue<>((K) key, (V) value);
        // removed other assertion
        // removed other assertion

        // 3. test copy constructor
        final Map.Entry<K, V> entry2 = new DefaultMapEntry<>(entry);
        // removed other assertion
        // removed other assertion

        // test that the objects are independent
        entry.setValue(null);
        assertSame(value, entry2.getValue());
    }

    public void testSelfReferenceHandling_2_oe() {
        final Map.Entry<K, V> entry = makeMapEntry();

        try {
            entry.setValue((V) entry);
            // removed other assertion

        } catch (final Exception e) {
            fail("This Map.Entry implementation supports value self-reference.");
    }
    }

}
