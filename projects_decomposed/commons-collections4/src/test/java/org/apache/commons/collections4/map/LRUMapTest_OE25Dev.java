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
package org.apache.commons.collections4.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.Test;

import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.ResettableIterator;

/**
 * JUnit tests.
 *
 */
public class LRUMapTest_OE25Dev<K, V> extends AbstractOrderedMapTest<K, V> {

    public LRUMapTest_OE25Dev(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return new junit.framework.TestSuite(LRUMapTest_OE25Dev.class);
    }

    @Override
    public LRUMap<K, V> makeObject() {
        return new LRUMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LRUMap<K, V> makeFullMap() {
        return (LRUMap<K, V>) super.makeFullMap();
    }

    @Override
    public boolean isGetStructuralModify() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LRUMap<K, V> getMap() {
        return (LRUMap<K, V>) super.getMap();
    }

    //-----------------------------------------------------------------------
    public void testCtors() {
        try {
            new LRUMap<K, V>(0);
            fail("maxSize must be positive");
        } catch(final IllegalArgumentException ex) {
            // expected
        }

        try {
            new LRUMap<K, V>(-1, 12, 0.75f, false);
            fail("maxSize must be positive");
        } catch(final IllegalArgumentException ex) {
            // expected
        }

        try {
            new LRUMap<K, V>(10, -1);
            fail("initialSize must not be negative");
        } catch(final IllegalArgumentException ex) {
            // expected
        }

        try {
            new LRUMap<K, V>(10, 12);
            fail("initialSize must not be larger than maxSize");
        } catch(final IllegalArgumentException ex) {
            // expected
        }

        try {
            new LRUMap<K, V>(10, -1, 0.75f, false);
            fail("initialSize must not be negative");
        } catch(final IllegalArgumentException ex) {
            // expected
        }

        try {
            new LRUMap<K, V>(10, 12, 0.75f, false);
            fail("initialSize must not be larger than maxSize");
        } catch(final IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    static class MockLRUMapSubclass<K, V> extends LRUMap<K, V> {
        /**
         * Generated serial version ID.
         */
        private static final long serialVersionUID = -2126883654452042477L;
        LinkEntry<K, V> entry;
        K key;
        V value;

        MockLRUMapSubclass(final int size) {
            super(size);
        }

        @Override
        protected boolean removeLRU(final LinkEntry<K, V> entry) {
            this.entry = entry;
            this.key = entry.getKey();
            this.value = entry.getValue();
            return true;
        }
    }

    static class MockLRUMapSubclassBlocksRemove<K, V> extends LRUMap<K, V> {
        /**
         * Generated serial version ID.
         */
        private static final long serialVersionUID = 6278917461128992945L;

        MockLRUMapSubclassBlocksRemove(final int size, final boolean scanUntilRemove) {
            super(size, scanUntilRemove);
        }

        @Override
        protected boolean removeLRU(final LinkEntry<K, V> entry) {
            return false;
        }
    }

    static class MockLRUMapSubclassFirstBlocksRemove<K, V> extends LRUMap<K, V> {
        /**
         * Generated serial version ID.
         */
        private static final long serialVersionUID = -6939790801702973428L;

        MockLRUMapSubclassFirstBlocksRemove(final int size) {
            super(size, true);
        }

        @Override
        protected boolean removeLRU(final LinkEntry<K, V> entry) {
            if ("a".equals(entry.getValue())) {
                return false;
            }
            return true;
        }
    }

    //-----------------------------------------------------------------------
    static class SingleHashCode {
        private final String code;
        SingleHashCode(final String code) {
            this.code = code;
        }
        @Override
        public int hashCode() {
            // always return the same hashcode
            // that way, it will end up in the same bucket
            return 12;
        }
        @Override
        public String toString() {
            return "SingleHashCode:" + code;
        }
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

//    public void testCreate() throws Exception {
//        resetEmpty();
//        writeExternalFormToDisk((java.io.Serializable) map, "src/test/resources/data/test/LRUMap.emptyCollection.version4.obj");
//        resetFull();
//        writeExternalFormToDisk((java.io.Serializable) map, "src/test/resources/data/test/LRUMap.fullCollection.version4.obj");
//    }

    public void testLRU_1_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);
        assertEquals(0, map.size());
    }

    public void testLRU_2_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);
        assertEquals(false, map.isFull());
    }

    public void testLRU_3_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);
        assertEquals(2, map.maxSize());
    }

    public void testLRU_4_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);
        assertEquals(1, map.size());
    }

    public void testLRU_5_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);
        assertEquals(false, map.isFull());
    }

    public void testLRU_6_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);
        assertEquals(2, map.maxSize());
    }

    public void testLRU_7_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        assertEquals(2, map.size());
    }

    public void testLRU_8_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        assertEquals(true, map.isFull());
    }

    public void testLRU_9_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        assertEquals(2, map.maxSize());
    }

    public void testLRU_10_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        assertSame(keys[0], kit.next());
    }

    public void testLRU_12_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();
        assertSame(values[0], vit.next());
    }

    public void testLRU_14_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[2]);
        assertEquals(2, map.size());
    }

    public void testLRU_15_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[2]);
        assertEquals(true, map.isFull());
    }

    public void testLRU_16_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[2]);
        assertEquals(2, map.maxSize());
    }

    public void testLRU_17_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[2]);
        kit = map.keySet().iterator();
        assertSame(keys[1], kit.next());
    }

    public void testLRU_19_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();
        assertSame(values[1], vit.next());
    }

    public void testLRU_21_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[0]);
        assertEquals(2, map.size());
    }

    public void testLRU_22_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[0]);
        assertEquals(true, map.isFull());
    }

    public void testLRU_23_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[0]);
        assertEquals(2, map.maxSize());
    }

    public void testLRU_24_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[0]);
        kit = map.keySet().iterator();
        assertSame(keys[1], kit.next());
    }

    public void testLRU_26_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[0]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();
        assertSame(values[1], vit.next());
    }

    public void testLRU_28_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[0]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[3]);
        assertEquals(2, map.size());
    }

    public void testLRU_29_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[0]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[3]);
        assertEquals(true, map.isFull());
    }

    public void testLRU_30_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[0]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[3]);
        assertEquals(2, map.maxSize());
    }

    public void testLRU_31_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[0]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[3]);
        kit = map.keySet().iterator();
        assertSame(keys[2], kit.next());
    }

    public void testLRU_33_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit;
        Iterator<V> vit;

        final LRUMap<K, V> map = new LRUMap<>(2);

        map.put(keys[0], values[0]);

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[2], values[0]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[3]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();
        assertSame(values[0], vit.next());
    }

    public void testReset_1_oe() {
        resetEmpty();
        OrderedMap<K, V> ordered = getMap();
        ((ResettableIterator<K>) ordered.mapIterator()).reset();

        resetFull();
        ordered = getMap();
        final List<K> list = new ArrayList<>(ordered.keySet());
        final ResettableIterator<K> it = (ResettableIterator<K>) ordered.mapIterator();
        assertSame(list.get(0), it.next());
    }

    public void testReset_3_oe() {
        resetEmpty();
        OrderedMap<K, V> ordered = getMap();
        ((ResettableIterator<K>) ordered.mapIterator()).reset();

        resetFull();
        ordered = getMap();
        final List<K> list = new ArrayList<>(ordered.keySet());
        final ResettableIterator<K> it = (ResettableIterator<K>) ordered.mapIterator();
        it.reset();
        assertSame(list.get(0), it.next());
    }

    public void testAccessOrder_1_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        assertSame(keys[0], kit.next());
    }

    public void testAccessOrder_3_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();
        assertSame(values[0], vit.next());
    }

    public void testAccessOrder_5_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        assertSame(keys[0], kit.next());
    }

    public void testAccessOrder_7_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();
        assertSame(values[0], vit.next());
    }

    public void testAccessOrder_9_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[2]);
        kit = map.keySet().iterator();
        assertSame(keys[0], kit.next());
    }

    public void testAccessOrder_11_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();
        assertSame(values[0], vit.next());
    }

    public void testAccessOrder_13_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[0], values[3]);
        kit = map.keySet().iterator();
        assertSame(keys[1], kit.next());
    }

    public void testAccessOrder_15_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[0], values[3]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();
        assertSame(values[2], vit.next());
    }

    public void testAccessOrder_17_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[0], values[3]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.get(keys[1]);
        kit = map.keySet().iterator();
        assertSame(keys[0], kit.next());
    }

    public void testAccessOrder_19_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[0], values[3]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.get(keys[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();
        assertSame(values[3], vit.next());
    }

    public void testAccessOrder_21_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[0], values[3]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.get(keys[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.get(keys[0]);
        kit = map.keySet().iterator();
        assertSame(keys[1], kit.next());
    }

    public void testAccessOrder_23_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[0], values[3]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.get(keys[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.get(keys[0]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();
        assertSame(values[2], vit.next());
    }

    public void testAccessOrder_25_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[0], values[3]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.get(keys[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.get(keys[0]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.get(keys[0]);
        kit = map.keySet().iterator();
        assertSame(keys[1], kit.next());
    }

    public void testAccessOrder_27_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[1], values[2]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.put(keys[0], values[3]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.get(keys[1]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.get(keys[0]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();

        map.get(keys[0]);
        kit = map.keySet().iterator();
        vit = map.values().iterator();
        assertSame(values[2], vit.next());
    }

    public void testAccessOrder2_1_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        final LRUMap<K, V> lruMap = (LRUMap<K, V>) map;

        lruMap.put(keys[0], values[0]);
        lruMap.put(keys[1], values[1]);
        kit = lruMap.keySet().iterator();
        assertSame(keys[0], kit.next());
    }

    public void testAccessOrder2_3_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        final LRUMap<K, V> lruMap = (LRUMap<K, V>) map;

        lruMap.put(keys[0], values[0]);
        lruMap.put(keys[1], values[1]);
        kit = lruMap.keySet().iterator();
        vit = lruMap.values().iterator();
        assertSame(values[0], vit.next());
    }

    public void testAccessOrder2_5_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        final LRUMap<K, V> lruMap = (LRUMap<K, V>) map;

        lruMap.put(keys[0], values[0]);
        lruMap.put(keys[1], values[1]);
        kit = lruMap.keySet().iterator();
        vit = lruMap.values().iterator();

        lruMap.put(keys[1], values[1]);
        kit = lruMap.keySet().iterator();
        assertSame(keys[0], kit.next());
    }

    public void testAccessOrder2_7_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        final LRUMap<K, V> lruMap = (LRUMap<K, V>) map;

        lruMap.put(keys[0], values[0]);
        lruMap.put(keys[1], values[1]);
        kit = lruMap.keySet().iterator();
        vit = lruMap.values().iterator();

        lruMap.put(keys[1], values[1]);
        kit = lruMap.keySet().iterator();
        vit = lruMap.values().iterator();
        assertSame(values[0], vit.next());
    }

    public void testAccessOrder2_9_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        final LRUMap<K, V> lruMap = (LRUMap<K, V>) map;

        lruMap.put(keys[0], values[0]);
        lruMap.put(keys[1], values[1]);
        kit = lruMap.keySet().iterator();
        vit = lruMap.values().iterator();

        lruMap.put(keys[1], values[1]);
        kit = lruMap.keySet().iterator();
        vit = lruMap.values().iterator();

        lruMap.get(keys[1], false);
        kit = lruMap.keySet().iterator();
        assertSame(keys[0], kit.next());
    }

    public void testAccessOrder2_11_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        final LRUMap<K, V> lruMap = (LRUMap<K, V>) map;

        lruMap.put(keys[0], values[0]);
        lruMap.put(keys[1], values[1]);
        kit = lruMap.keySet().iterator();
        vit = lruMap.values().iterator();

        lruMap.put(keys[1], values[1]);
        kit = lruMap.keySet().iterator();
        vit = lruMap.values().iterator();

        lruMap.get(keys[1], false);
        kit = lruMap.keySet().iterator();
        vit = lruMap.values().iterator();
        assertSame(values[0], vit.next());
    }

    public void testAccessOrder2_13_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        final LRUMap<K, V> lruMap = (LRUMap<K, V>) map;

        lruMap.put(keys[0], values[0]);
        lruMap.put(keys[1], values[1]);
        kit = lruMap.keySet().iterator();
        vit = lruMap.values().iterator();

        lruMap.put(keys[1], values[1]);
        kit = lruMap.keySet().iterator();
        vit = lruMap.values().iterator();

        lruMap.get(keys[1], false);
        kit = lruMap.keySet().iterator();
        vit = lruMap.values().iterator();

        lruMap.get(keys[0], true);
        kit = lruMap.keySet().iterator();
        assertSame(keys[1], kit.next());
    }

    public void testAccessOrder2_15_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> kit = null;
        Iterator<V> vit = null;

        resetEmpty();
        final LRUMap<K, V> lruMap = (LRUMap<K, V>) map;

        lruMap.put(keys[0], values[0]);
        lruMap.put(keys[1], values[1]);
        kit = lruMap.keySet().iterator();
        vit = lruMap.values().iterator();

        lruMap.put(keys[1], values[1]);
        kit = lruMap.keySet().iterator();
        vit = lruMap.values().iterator();

        lruMap.get(keys[1], false);
        kit = lruMap.keySet().iterator();
        vit = lruMap.values().iterator();

        lruMap.get(keys[0], true);
        kit = lruMap.keySet().iterator();
        vit = lruMap.values().iterator();
        assertSame(values[1], vit.next());
    }

    public void testClone_1_oe() {
        final LRUMap<K, V> map = new LRUMap<>(10);
        map.put((K) "1", (V) "1");
        final Map<K, V> cloned = map.clone();
        assertEquals(map.size(), cloned.size());
    }

    public void testClone_2_oe() {
        final LRUMap<K, V> map = new LRUMap<>(10);
        map.put((K) "1", (V) "1");
        final Map<K, V> cloned = map.clone();
        assertSame(map.get("1"), cloned.get("1"));
    }

    public void testRemoveLRU_1_oe() {
        final MockLRUMapSubclass<K, String> map = new MockLRUMapSubclass<>(2);
        assertNull(map.entry);
    }

    public void testRemoveLRU_2_oe() {
        final MockLRUMapSubclass<K, String> map = new MockLRUMapSubclass<>(2);
        map.put((K) "A", "a");
        assertNull(map.entry);
    }

    public void testRemoveLRU_3_oe() {
        final MockLRUMapSubclass<K, String> map = new MockLRUMapSubclass<>(2);
        map.put((K) "A", "a");
        map.put((K) "B", "b");
        assertNull(map.entry);
    }

    public void testRemoveLRU_4_oe() {
        final MockLRUMapSubclass<K, String> map = new MockLRUMapSubclass<>(2);
        map.put((K) "A", "a");
        map.put((K) "B", "b");
        map.put((K) "C", "c");  // removes oldest, which is A=a
        assertNotNull(map.entry);
    }

    public void testRemoveLRU_5_oe() {
        final MockLRUMapSubclass<K, String> map = new MockLRUMapSubclass<>(2);
        map.put((K) "A", "a");
        map.put((K) "B", "b");
        map.put((K) "C", "c");  // removes oldest, which is A=a
        assertEquals("A", map.key);
    }

    public void testRemoveLRU_6_oe() {
        final MockLRUMapSubclass<K, String> map = new MockLRUMapSubclass<>(2);
        map.put((K) "A", "a");
        map.put((K) "B", "b");
        map.put((K) "C", "c");  // removes oldest, which is A=a
        assertEquals("a", map.value);
    }

    public void testRemoveLRU_7_oe() {
        final MockLRUMapSubclass<K, String> map = new MockLRUMapSubclass<>(2);
        map.put((K) "A", "a");
        map.put((K) "B", "b");
        map.put((K) "C", "c");  // removes oldest, which is A=a
        assertEquals("C",map.entry.getKey());// entry is reused assertEquals("c",map.entry.getValue());// entry is reused assertEquals(false,map.containsKey("A"));
    }

    public void testRemoveLRU_8_oe() {
        final MockLRUMapSubclass<K, String> map = new MockLRUMapSubclass<>(2);
        map.put((K) "A", "a");
        map.put((K) "B", "b");
        map.put((K) "C", "c");  // removes oldest, which is A=a
        assertEquals(true, map.containsKey("B"));
    }

    public void testRemoveLRU_9_oe() {
        final MockLRUMapSubclass<K, String> map = new MockLRUMapSubclass<>(2);
        map.put((K) "A", "a");
        map.put((K) "B", "b");
        map.put((K) "C", "c");  // removes oldest, which is A=a
        assertEquals(true, map.containsKey("C"));
    }

    public void testRemoveLRUBlocksRemove_1_oe() {
        final MockLRUMapSubclassBlocksRemove<K, V> map = new MockLRUMapSubclassBlocksRemove<>(2, false);
        assertEquals(0, map.size());
    }

    public void testRemoveLRUBlocksRemove_2_oe() {
        final MockLRUMapSubclassBlocksRemove<K, V> map = new MockLRUMapSubclassBlocksRemove<>(2, false);
        map.put((K) "A", (V) "a");
        assertEquals(1, map.size());
    }

    public void testRemoveLRUBlocksRemove_3_oe() {
        final MockLRUMapSubclassBlocksRemove<K, V> map = new MockLRUMapSubclassBlocksRemove<>(2, false);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        assertEquals(2, map.size());
    }

    public void testRemoveLRUBlocksRemove_4_oe() {
        final MockLRUMapSubclassBlocksRemove<K, V> map = new MockLRUMapSubclassBlocksRemove<>(2, false);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        map.put((K) "C", (V) "c");  // should remove oldest, which is A=a, but this is blocked
        assertEquals(3, map.size());
    }

    public void testRemoveLRUBlocksRemove_5_oe() {
        final MockLRUMapSubclassBlocksRemove<K, V> map = new MockLRUMapSubclassBlocksRemove<>(2, false);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        map.put((K) "C", (V) "c");  // should remove oldest, which is A=a, but this is blocked
        assertEquals(2, map.maxSize());
    }

    public void testRemoveLRUBlocksRemove_6_oe() {
        final MockLRUMapSubclassBlocksRemove<K, V> map = new MockLRUMapSubclassBlocksRemove<>(2, false);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        map.put((K) "C", (V) "c");  // should remove oldest, which is A=a, but this is blocked
        assertEquals(true, map.containsKey("A"));
    }

    public void testRemoveLRUBlocksRemove_7_oe() {
        final MockLRUMapSubclassBlocksRemove<K, V> map = new MockLRUMapSubclassBlocksRemove<>(2, false);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        map.put((K) "C", (V) "c");  // should remove oldest, which is A=a, but this is blocked
        assertEquals(true, map.containsKey("B"));
    }

    public void testRemoveLRUBlocksRemove_8_oe() {
        final MockLRUMapSubclassBlocksRemove<K, V> map = new MockLRUMapSubclassBlocksRemove<>(2, false);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        map.put((K) "C", (V) "c");  // should remove oldest, which is A=a, but this is blocked
        assertEquals(true, map.containsKey("C"));
    }

    public void testRemoveLRUBlocksRemoveScan_1_oe() {
        final MockLRUMapSubclassBlocksRemove<K, V> map = new MockLRUMapSubclassBlocksRemove<>(2, true);
        assertEquals(0, map.size());
    }

    public void testRemoveLRUBlocksRemoveScan_2_oe() {
        final MockLRUMapSubclassBlocksRemove<K, V> map = new MockLRUMapSubclassBlocksRemove<>(2, true);
        map.put((K) "A", (V) "a");
        assertEquals(1, map.size());
    }

    public void testRemoveLRUBlocksRemoveScan_3_oe() {
        final MockLRUMapSubclassBlocksRemove<K, V> map = new MockLRUMapSubclassBlocksRemove<>(2, true);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        assertEquals(2, map.size());
    }

    public void testRemoveLRUBlocksRemoveScan_4_oe() {
        final MockLRUMapSubclassBlocksRemove<K, V> map = new MockLRUMapSubclassBlocksRemove<>(2, true);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        map.put((K) "C", (V) "c");  // should remove oldest, which is A=a, but this is blocked
        assertEquals(3, map.size());
    }

    public void testRemoveLRUBlocksRemoveScan_5_oe() {
        final MockLRUMapSubclassBlocksRemove<K, V> map = new MockLRUMapSubclassBlocksRemove<>(2, true);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        map.put((K) "C", (V) "c");  // should remove oldest, which is A=a, but this is blocked
        assertEquals(2, map.maxSize());
    }

    public void testRemoveLRUBlocksRemoveScan_6_oe() {
        final MockLRUMapSubclassBlocksRemove<K, V> map = new MockLRUMapSubclassBlocksRemove<>(2, true);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        map.put((K) "C", (V) "c");  // should remove oldest, which is A=a, but this is blocked
        assertEquals(true, map.containsKey("A"));
    }

    public void testRemoveLRUBlocksRemoveScan_7_oe() {
        final MockLRUMapSubclassBlocksRemove<K, V> map = new MockLRUMapSubclassBlocksRemove<>(2, true);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        map.put((K) "C", (V) "c");  // should remove oldest, which is A=a, but this is blocked
        assertEquals(true, map.containsKey("B"));
    }

    public void testRemoveLRUBlocksRemoveScan_8_oe() {
        final MockLRUMapSubclassBlocksRemove<K, V> map = new MockLRUMapSubclassBlocksRemove<>(2, true);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        map.put((K) "C", (V) "c");  // should remove oldest, which is A=a, but this is blocked
        assertEquals(true, map.containsKey("C"));
    }

    public void testRemoveLRUFirstBlocksRemove_1_oe() {
        final MockLRUMapSubclassFirstBlocksRemove<K, V> map = new MockLRUMapSubclassFirstBlocksRemove<>(2);
        assertEquals(0, map.size());
    }

    public void testRemoveLRUFirstBlocksRemove_2_oe() {
        final MockLRUMapSubclassFirstBlocksRemove<K, V> map = new MockLRUMapSubclassFirstBlocksRemove<>(2);
        map.put((K) "A", (V) "a");
        assertEquals(1, map.size());
    }

    public void testRemoveLRUFirstBlocksRemove_3_oe() {
        final MockLRUMapSubclassFirstBlocksRemove<K, V> map = new MockLRUMapSubclassFirstBlocksRemove<>(2);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        assertEquals(2, map.size());
    }

    public void testRemoveLRUFirstBlocksRemove_4_oe() {
        final MockLRUMapSubclassFirstBlocksRemove<K, V> map = new MockLRUMapSubclassFirstBlocksRemove<>(2);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        map.put((K) "C", (V) "c");  // should remove oldest, which is A=a  but this is blocked - so advance to B=b
        assertEquals(2, map.size());
    }

    public void testRemoveLRUFirstBlocksRemove_5_oe() {
        final MockLRUMapSubclassFirstBlocksRemove<K, V> map = new MockLRUMapSubclassFirstBlocksRemove<>(2);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        map.put((K) "C", (V) "c");  // should remove oldest, which is A=a  but this is blocked - so advance to B=b
        assertEquals(2, map.maxSize());
    }

    public void testRemoveLRUFirstBlocksRemove_6_oe() {
        final MockLRUMapSubclassFirstBlocksRemove<K, V> map = new MockLRUMapSubclassFirstBlocksRemove<>(2);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        map.put((K) "C", (V) "c");  // should remove oldest, which is A=a  but this is blocked - so advance to B=b
        assertEquals(true, map.containsKey("A"));
    }

    public void testRemoveLRUFirstBlocksRemove_7_oe() {
        final MockLRUMapSubclassFirstBlocksRemove<K, V> map = new MockLRUMapSubclassFirstBlocksRemove<>(2);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        map.put((K) "C", (V) "c");  // should remove oldest, which is A=a  but this is blocked - so advance to B=b
        assertEquals(false, map.containsKey("B"));
    }

    public void testRemoveLRUFirstBlocksRemove_8_oe() {
        final MockLRUMapSubclassFirstBlocksRemove<K, V> map = new MockLRUMapSubclassFirstBlocksRemove<>(2);
        map.put((K) "A", (V) "a");
        map.put((K) "B", (V) "b");
        map.put((K) "C", (V) "c");  // should remove oldest, which is A=a  but this is blocked - so advance to B=b
        assertEquals(true, map.containsKey("C"));
    }

    public void testInternalState_Buckets_1_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");

        assertEquals(4, map.data.length);
    }

    public void testInternalState_Buckets_2_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");

        assertEquals(3, map.size);
    }

    public void testInternalState_Buckets_3_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");

        assertEquals(null, map.header.next);
    }

    public void testInternalState_Buckets_4_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");

        assertEquals(one,map.header.after.key);// LRU assertEquals(two,map.header.after.after.key);
    }

    public void testInternalState_Buckets_5_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");

        assertEquals(three,map.header.after.after.after.key);// MRU assertEquals(three,map.data[hashIndex].key);
    }

    public void testInternalState_Buckets_6_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");

        assertEquals(two, map.data[hashIndex].next.key);
    }

    public void testInternalState_Buckets_7_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");

        assertEquals(one, map.data[hashIndex].next.next.key);
    }

    public void testInternalState_Buckets_8_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list

        assertEquals(4, map.data.length);
    }

    public void testInternalState_Buckets_9_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list

        assertEquals(3, map.size);
    }

    public void testInternalState_Buckets_10_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list

        assertEquals(null, map.header.next);
    }

    public void testInternalState_Buckets_11_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list

        assertEquals(two,map.header.after.key);// LRU assertEquals(three,map.header.after.after.key);
    }

    public void testInternalState_Buckets_12_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list

        assertEquals(four,map.header.after.after.after.key);// MRU assertEquals(four,map.data[hashIndex].key);
    }

    public void testInternalState_Buckets_13_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list

        assertEquals(three, map.data[hashIndex].next.key);
    }

    public void testInternalState_Buckets_14_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list

        assertEquals(two, map.data[hashIndex].next.next.key);
    }

    public void testInternalState_Buckets_15_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);

        assertEquals(4, map.data.length);
    }

    public void testInternalState_Buckets_16_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);

        assertEquals(3, map.size);
    }

    public void testInternalState_Buckets_17_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);

        assertEquals(null, map.header.next);
    }

    public void testInternalState_Buckets_18_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);

        assertEquals(two,map.header.after.key);// LRU assertEquals(four,map.header.after.after.key);
    }

    public void testInternalState_Buckets_19_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);

        assertEquals(three,map.header.after.after.after.key);// MRU assertEquals(four,map.data[hashIndex].key);
    }

    public void testInternalState_Buckets_20_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);

        assertEquals(three, map.data[hashIndex].next.key);
    }

    public void testInternalState_Buckets_21_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);

        assertEquals(two, map.data[hashIndex].next.next.key);
    }

    public void testInternalState_Buckets_22_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list

        assertEquals(4, map.data.length);
    }

    public void testInternalState_Buckets_23_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list

        assertEquals(3, map.size);
    }

    public void testInternalState_Buckets_24_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list

        assertEquals(null, map.header.next);
    }

    public void testInternalState_Buckets_25_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list

        assertEquals(four,map.header.after.key);// LRU assertEquals(three,map.header.after.after.key);
    }

    public void testInternalState_Buckets_26_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list

        assertEquals(five,map.header.after.after.after.key);// MRU assertEquals(five,map.data[hashIndex].key);
    }

    public void testInternalState_Buckets_27_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list

        assertEquals(four, map.data[hashIndex].next.key);
    }

    public void testInternalState_Buckets_28_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list

        assertEquals(three, map.data[hashIndex].next.next.key);
    }

    public void testInternalState_Buckets_29_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list


        map.get(three);
        map.get(five);

        assertEquals(4, map.data.length);
    }

    public void testInternalState_Buckets_30_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list


        map.get(three);
        map.get(five);

        assertEquals(3, map.size);
    }

    public void testInternalState_Buckets_31_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list


        map.get(three);
        map.get(five);

        assertEquals(null, map.header.next);
    }

    public void testInternalState_Buckets_32_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list


        map.get(three);
        map.get(five);

        assertEquals(four,map.header.after.key);// LRU assertEquals(three,map.header.after.after.key);
    }

    public void testInternalState_Buckets_33_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list


        map.get(three);
        map.get(five);

        assertEquals(five,map.header.after.after.after.key);// MRU assertEquals(five,map.data[hashIndex].key);
    }

    public void testInternalState_Buckets_34_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list


        map.get(three);
        map.get(five);

        assertEquals(four, map.data[hashIndex].next.key);
    }

    public void testInternalState_Buckets_35_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list


        map.get(three);
        map.get(five);

        assertEquals(three, map.data[hashIndex].next.next.key);
    }

    public void testInternalState_Buckets_36_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list


        map.get(three);
        map.get(five);


        map.put((K) six, (V) "F");  // reuses middle in next list

        assertEquals(4, map.data.length);
    }

    public void testInternalState_Buckets_37_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list


        map.get(three);
        map.get(five);


        map.put((K) six, (V) "F");  // reuses middle in next list

        assertEquals(3, map.size);
    }

    public void testInternalState_Buckets_38_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list


        map.get(three);
        map.get(five);


        map.put((K) six, (V) "F");  // reuses middle in next list

        assertEquals(null, map.header.next);
    }

    public void testInternalState_Buckets_39_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list


        map.get(three);
        map.get(five);


        map.put((K) six, (V) "F");  // reuses middle in next list

        assertEquals(three,map.header.after.key);// LRU assertEquals(five,map.header.after.after.key);
    }

    public void testInternalState_Buckets_40_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list


        map.get(three);
        map.get(five);


        map.put((K) six, (V) "F");  // reuses middle in next list

        assertEquals(six,map.header.after.after.after.key);// MRU assertEquals(six,map.data[hashIndex].key);
    }

    public void testInternalState_Buckets_41_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list


        map.get(three);
        map.get(five);


        map.put((K) six, (V) "F");  // reuses middle in next list

        assertEquals(five, map.data[hashIndex].next.key);
    }

    public void testInternalState_Buckets_42_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");
        final SingleHashCode four = new SingleHashCode("4");
        final SingleHashCode five = new SingleHashCode("5");
        final SingleHashCode six = new SingleHashCode("6");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        final int hashIndex = map.hashIndex(map.hash(one), 4);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");


        map.put((K) four, (V) "D");  // reuses last in next list


        map.get(three);


        map.put((K) five, (V) "E");  // reuses last in next list


        map.get(three);
        map.get(five);


        map.put((K) six, (V) "F");  // reuses middle in next list

        assertEquals(three, map.data[hashIndex].next.next.key);
    }

    public void testInternalState_getEntry_int_1_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");

        assertEquals(one, map.getEntry(0).key);
    }

    public void testInternalState_getEntry_int_2_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");

        assertEquals(two, map.getEntry(1).key);
    }

    public void testInternalState_getEntry_int_3_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final SingleHashCode one = new SingleHashCode("1");
        final SingleHashCode two = new SingleHashCode("2");
        final SingleHashCode three = new SingleHashCode("3");

        final LRUMap<K, V> map = new LRUMap<>(3, 1.0f);
        map.put((K) one, (V) "A");
        map.put((K) two, (V) "B");
        map.put((K) three, (V) "C");

        assertEquals(three, map.getEntry(2).key);
    }

    public void testSynchronizedRemoveFromMapIterator_2_oe() throws InterruptedException {

        final LRUMap<Object, Thread> map = new LRUMap<>(10000);

        final Map<Throwable, String> exceptions = new HashMap<>();
        final ThreadGroup tg = new ThreadGroup(getName()) {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                exceptions.put(e, t.getName());
                super.uncaughtException(t, e);
            }
        };

        final int[] counter = new int[1];
        counter[0] = 0;
        final Thread[] threads = new Thread[50];
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(tg, "JUnit Thread " + i) {

                @Override
                public void run() {
                    int i = 0;
                    try {
                        synchronized (this) {
                            notifyAll();
                            wait();
                        }
                        final Thread thread = Thread.currentThread();
                        while (i < 1000  && !interrupted()) {
                            synchronized (map) {
                                map.put(thread.getName() + "[" + ++i + "]", thread);
                            }
                        }
                        synchronized (map) {
                            for (final MapIterator<Object, Thread> iter = map.mapIterator(); iter.hasNext();) {
                                iter.next();
                                if (iter.getValue() == this) {
                                    iter.remove();
                                }
                            }
                        }
                    } catch (final InterruptedException e) {
                    }
                    if (i > 0) {
                        synchronized (counter) {
                            counter[0]++;
                        }
                    }
                }

            };
        }

        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.start();
                thread.wait();
            }
        }

        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.notifyAll();
            }
        }

        Thread.sleep(1000);

        for (final Thread thread : threads) {
            thread.interrupt();
        }
        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.join();
            }
        }

        assertEquals("Exceptions have been thrown: " + exceptions, 0, exceptions.size());
    }

    public void testSynchronizedRemoveFromMapIterator_3_oe() throws InterruptedException {

        final LRUMap<Object, Thread> map = new LRUMap<>(10000);

        final Map<Throwable, String> exceptions = new HashMap<>();
        final ThreadGroup tg = new ThreadGroup(getName()) {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                exceptions.put(e, t.getName());
                super.uncaughtException(t, e);
            }
        };

        final int[] counter = new int[1];
        counter[0] = 0;
        final Thread[] threads = new Thread[50];
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(tg, "JUnit Thread " + i) {

                @Override
                public void run() {
                    int i = 0;
                    try {
                        synchronized (this) {
                            notifyAll();
                            wait();
                        }
                        final Thread thread = Thread.currentThread();
                        while (i < 1000  && !interrupted()) {
                            synchronized (map) {
                                map.put(thread.getName() + "[" + ++i + "]", thread);
                            }
                        }
                        synchronized (map) {
                            for (final MapIterator<Object, Thread> iter = map.mapIterator(); iter.hasNext();) {
                                iter.next();
                                if (iter.getValue() == this) {
                                    iter.remove();
                                }
                            }
                        }
                    } catch (final InterruptedException e) {
                    }
                    if (i > 0) {
                        synchronized (counter) {
                            counter[0]++;
                        }
                    }
                }

            };
        }

        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.start();
                thread.wait();
            }
        }

        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.notifyAll();
            }
        }

        Thread.sleep(1000);

        for (final Thread thread : threads) {
            thread.interrupt();
        }
        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.join();
            }
        }

        assertTrue("Each thread should have put at least 1 element into the map,but only " + counter[0] + " did succeed",counter[0] >= threads.length);
    }

    public void testSynchronizedRemoveFromEntrySet_2_oe() throws InterruptedException {

        final Map<Object, Thread> map = new LRUMap<>(10000);

        final Map<Throwable, String> exceptions = new HashMap<>();
        final ThreadGroup tg = new ThreadGroup(getName()) {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                exceptions.put(e, t.getName());
                super.uncaughtException(t, e);
            }
        };

        final int[] counter = new int[1];
        counter[0] = 0;
        final Thread[] threads = new Thread[50];
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(tg, "JUnit Thread " + i) {

                @Override
                public void run() {
                    int i = 0;
                    try {
                        synchronized (this) {
                            notifyAll();
                            wait();
                        }
                        final Thread thread = Thread.currentThread();
                        while (i < 1000  && !interrupted()) {
                            synchronized (map) {
                                map.put(thread.getName() + "[" + ++i + "]", thread);
                            }
                        }
                        synchronized (map) {
                            for (final Iterator<Map.Entry<Object, Thread>> iter = map.entrySet().iterator(); iter.hasNext();) {
                                final Map.Entry<Object, Thread> entry = iter.next();
                                if (entry.getValue() == this) {
                                    iter.remove();
                                }
                            }
                        }
                    } catch (final InterruptedException e) {
                    }
                    if (i > 0) {
                        synchronized (counter) {
                            counter[0]++;
                        }
                    }
                }

            };
        }

        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.start();
                thread.wait();
            }
        }

        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.notifyAll();
            }
        }

        Thread.sleep(1000);

        for (final Thread thread : threads) {
            thread.interrupt();
        }
        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.join();
            }
        }

        assertEquals("Exceptions have been thrown: " + exceptions, 0, exceptions.size());
    }

    public void testSynchronizedRemoveFromEntrySet_3_oe() throws InterruptedException {

        final Map<Object, Thread> map = new LRUMap<>(10000);

        final Map<Throwable, String> exceptions = new HashMap<>();
        final ThreadGroup tg = new ThreadGroup(getName()) {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                exceptions.put(e, t.getName());
                super.uncaughtException(t, e);
            }
        };

        final int[] counter = new int[1];
        counter[0] = 0;
        final Thread[] threads = new Thread[50];
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(tg, "JUnit Thread " + i) {

                @Override
                public void run() {
                    int i = 0;
                    try {
                        synchronized (this) {
                            notifyAll();
                            wait();
                        }
                        final Thread thread = Thread.currentThread();
                        while (i < 1000  && !interrupted()) {
                            synchronized (map) {
                                map.put(thread.getName() + "[" + ++i + "]", thread);
                            }
                        }
                        synchronized (map) {
                            for (final Iterator<Map.Entry<Object, Thread>> iter = map.entrySet().iterator(); iter.hasNext();) {
                                final Map.Entry<Object, Thread> entry = iter.next();
                                if (entry.getValue() == this) {
                                    iter.remove();
                                }
                            }
                        }
                    } catch (final InterruptedException e) {
                    }
                    if (i > 0) {
                        synchronized (counter) {
                            counter[0]++;
                        }
                    }
                }

            };
        }

        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.start();
                thread.wait();
            }
        }

        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.notifyAll();
            }
        }

        Thread.sleep(1000);

        for (final Thread thread : threads) {
            thread.interrupt();
        }
        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.join();
            }
        }

        assertTrue("Each thread should have put at least 1 element into the map,but only " + counter[0] + " did succeed",counter[0] >= threads.length);
    }

    public void testSynchronizedRemoveFromKeySet_2_oe() throws InterruptedException {

        final Map<Object, Thread> map = new LRUMap<>(10000);

        final Map<Throwable, String> exceptions = new HashMap<>();
        final ThreadGroup tg = new ThreadGroup(getName()) {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                exceptions.put(e, t.getName());
                super.uncaughtException(t, e);
            }
        };

        final int[] counter = new int[1];
        counter[0] = 0;
        final Thread[] threads = new Thread[50];
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(tg, "JUnit Thread " + i) {

                @Override
                public void run() {
                    int i = 0;
                    try {
                        synchronized (this) {
                            notifyAll();
                            wait();
                        }
                        final Thread thread = Thread.currentThread();
                        while (i < 1000  && !interrupted()) {
                            synchronized (map) {
                                map.put(thread.getName() + "[" + ++i + "]", thread);
                            }
                        }
                        synchronized (map) {
                            for (final Iterator<Object> iter = map.keySet().iterator(); iter.hasNext();) {
                                final String name = (String) iter.next();
                                if (name.substring(0, name.indexOf('[')).equals(getName())) {
                                    iter.remove();
                                }
                            }
                        }
                    } catch (final InterruptedException e) {
                    }
                    if (i > 0) {
                        synchronized (counter) {
                            counter[0]++;
                        }
                    }
                }

            };
        }

        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.start();
                thread.wait();
            }
        }

        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.notifyAll();
            }
        }

        Thread.sleep(1000);

        for (final Thread thread : threads) {
            thread.interrupt();
        }
        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.join();
            }
        }

        assertEquals("Exceptions have been thrown: " + exceptions, 0, exceptions.size());
    }

    public void testSynchronizedRemoveFromKeySet_3_oe() throws InterruptedException {

        final Map<Object, Thread> map = new LRUMap<>(10000);

        final Map<Throwable, String> exceptions = new HashMap<>();
        final ThreadGroup tg = new ThreadGroup(getName()) {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                exceptions.put(e, t.getName());
                super.uncaughtException(t, e);
            }
        };

        final int[] counter = new int[1];
        counter[0] = 0;
        final Thread[] threads = new Thread[50];
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(tg, "JUnit Thread " + i) {

                @Override
                public void run() {
                    int i = 0;
                    try {
                        synchronized (this) {
                            notifyAll();
                            wait();
                        }
                        final Thread thread = Thread.currentThread();
                        while (i < 1000  && !interrupted()) {
                            synchronized (map) {
                                map.put(thread.getName() + "[" + ++i + "]", thread);
                            }
                        }
                        synchronized (map) {
                            for (final Iterator<Object> iter = map.keySet().iterator(); iter.hasNext();) {
                                final String name = (String) iter.next();
                                if (name.substring(0, name.indexOf('[')).equals(getName())) {
                                    iter.remove();
                                }
                            }
                        }
                    } catch (final InterruptedException e) {
                    }
                    if (i > 0) {
                        synchronized (counter) {
                            counter[0]++;
                        }
                    }
                }

            };
        }

        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.start();
                thread.wait();
            }
        }

        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.notifyAll();
            }
        }

        Thread.sleep(1000);

        for (final Thread thread : threads) {
            thread.interrupt();
        }
        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.join();
            }
        }

        assertTrue("Each thread should have put at least 1 element into the map,but only " + counter[0] + " did succeed",counter[0] >= threads.length);
    }

    public void testSynchronizedRemoveFromValues_2_oe() throws InterruptedException {

        final Map<Object, Thread> map = new LRUMap<>(10000);

        final Map<Throwable, String> exceptions = new HashMap<>();
        final ThreadGroup tg = new ThreadGroup(getName()) {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                exceptions.put(e, t.getName());
                super.uncaughtException(t, e);
            }
        };

        final int[] counter = new int[1];
        counter[0] = 0;
        final Thread[] threads = new Thread[50];
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(tg, "JUnit Thread " + i) {

                @Override
                public void run() {
                    int i = 0;
                    try {
                        synchronized (this) {
                            notifyAll();
                            wait();
                        }
                        final Thread thread = Thread.currentThread();
                        while (i < 1000  && !interrupted()) {
                            synchronized (map) {
                                map.put(thread.getName() + "[" + ++i + "]", thread);
                            }
                        }
                        synchronized (map) {
                            for (final Iterator<Thread> iter = map.values().iterator(); iter.hasNext();) {
                                if (iter.next() == this) {
                                    iter.remove();
                                }
                            }
                        }
                    } catch (final InterruptedException e) {
                    }
                    if (i > 0) {
                        synchronized (counter) {
                            counter[0]++;
                        }
                    }
                }

            };
        }

        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.start();
                thread.wait();
            }
        }

        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.notifyAll();
            }
        }

        Thread.sleep(1000);

        for (final Thread thread : threads) {
            thread.interrupt();
        }
        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.join();
            }
        }

        assertEquals("Exceptions have been thrown: " + exceptions, 0, exceptions.size());
    }

    public void testSynchronizedRemoveFromValues_3_oe() throws InterruptedException {

        final Map<Object, Thread> map = new LRUMap<>(10000);

        final Map<Throwable, String> exceptions = new HashMap<>();
        final ThreadGroup tg = new ThreadGroup(getName()) {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                exceptions.put(e, t.getName());
                super.uncaughtException(t, e);
            }
        };

        final int[] counter = new int[1];
        counter[0] = 0;
        final Thread[] threads = new Thread[50];
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(tg, "JUnit Thread " + i) {

                @Override
                public void run() {
                    int i = 0;
                    try {
                        synchronized (this) {
                            notifyAll();
                            wait();
                        }
                        final Thread thread = Thread.currentThread();
                        while (i < 1000  && !interrupted()) {
                            synchronized (map) {
                                map.put(thread.getName() + "[" + ++i + "]", thread);
                            }
                        }
                        synchronized (map) {
                            for (final Iterator<Thread> iter = map.values().iterator(); iter.hasNext();) {
                                if (iter.next() == this) {
                                    iter.remove();
                                }
                            }
                        }
                    } catch (final InterruptedException e) {
                    }
                    if (i > 0) {
                        synchronized (counter) {
                            counter[0]++;
                        }
                    }
                }

            };
        }

        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.start();
                thread.wait();
            }
        }

        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.notifyAll();
            }
        }

        Thread.sleep(1000);

        for (final Thread thread : threads) {
            thread.interrupt();
        }
        for (final Thread thread : threads) {
            synchronized (thread) {
                thread.join();
            }
        }

        assertTrue("Each thread should have put at least 1 element into the map,but only " + counter[0] + " did succeed",counter[0] >= threads.length);
    }

}
