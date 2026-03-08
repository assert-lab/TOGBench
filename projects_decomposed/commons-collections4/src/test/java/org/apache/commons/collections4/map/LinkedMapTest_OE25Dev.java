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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.Test;

import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.list.AbstractListTest;

/**
 * JUnit tests.
 *
 */
public class LinkedMapTest_OE25Dev<K, V> extends AbstractOrderedMapTest<K, V> {

    public LinkedMapTest_OE25Dev(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return new junit.framework.TestSuite(LinkedMapTest_OE25Dev.class);
    }

    @Override
    public LinkedMap<K, V> makeObject() {
        return new LinkedMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedMap<K, V> makeFullMap() {
        return (LinkedMap<K, V>) super.makeFullMap();
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testRemoveByIndex() {
        resetEmpty();
        LinkedMap<K, V> lm = getMap();
        try {
            lm.remove(0);
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lm.remove(-1);
        } catch (final IndexOutOfBoundsException ex) {}

        resetFull();
        lm = getMap();
        try {
            lm.remove(-1);
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lm.remove(lm.size());
        } catch (final IndexOutOfBoundsException ex) {}

        final List<K> list = new ArrayList<>();
        for (final MapIterator<K, V> it = lm.mapIterator(); it.hasNext();) {
            list.add(it.next());
        }
        for (int i = 0; i < list.size(); i++) {
            final Object key = list.get(i);
            final Object value = lm.get(key);
            assertEquals(value, lm.remove(i));
            list.remove(i);
            assertEquals(false, lm.containsKey(key));
        }
    }

    public BulkTest bulkTestListView() {
        return new TestListView();
    }

    public class TestListView extends AbstractListTest<K> {

        TestListView() {
            super("TestListView");
        }

        @Override
        public List<K> makeObject() {
            return LinkedMapTest_OE25Dev.this.makeObject().asList();
        }

        @Override
        public List<K> makeFullCollection() {
            return LinkedMapTest_OE25Dev.this.makeFullMap().asList();
        }

        @Override
        public K[] getFullElements() {
            return LinkedMapTest_OE25Dev.this.getSampleKeys();
        }
        @Override
        public boolean isAddSupported() {
            return false;
        }
        @Override
        public boolean isRemoveSupported() {
            return false;
        }
        @Override
        public boolean isSetSupported() {
            return false;
        }
        @Override
        public boolean isNullSupported() {
            return LinkedMapTest_OE25Dev.this.isAllowNullKey();
        }
        @Override
        public boolean isTestSerialization() {
            return false;
        }
    }

//    public void testCreate() throws Exception {
//        resetEmpty();
//        writeExternalFormToDisk((java.io.Serializable) map, "src/test/resources/data/test/LinkedMap.emptyCollection.version4.obj");
//        resetFull();
//        writeExternalFormToDisk((java.io.Serializable) map, "src/test/resources/data/test/LinkedMap.fullCollection.version4.obj");
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedMap<K, V> getMap() {
        return (LinkedMap<K, V>) super.getMap();
    }

    /**
     * Test for <a href="https://issues.apache.org/jira/browse/COLLECTIONS-323">COLLECTIONS-323</a>.
     */

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

    public void testInsertionOrder_1_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> keyIter;
        Iterator<V> valueIter;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        keyIter = map.keySet().iterator();
        assertSame(keys[0], keyIter.next());
    }

    public void testInsertionOrder_3_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> keyIter;
        Iterator<V> valueIter;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        keyIter = map.keySet().iterator();
        valueIter = map.values().iterator();
        assertSame(values[0], valueIter.next());
    }

    public void testInsertionOrder_5_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> keyIter;
        Iterator<V> valueIter;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        keyIter = map.keySet().iterator();
        valueIter = map.values().iterator();

        map.put(keys[1], values[1]);
        keyIter = map.keySet().iterator();
        assertSame(keys[0], keyIter.next());
    }

    public void testInsertionOrder_7_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> keyIter;
        Iterator<V> valueIter;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        keyIter = map.keySet().iterator();
        valueIter = map.values().iterator();

        map.put(keys[1], values[1]);
        keyIter = map.keySet().iterator();
        valueIter = map.values().iterator();
        assertSame(values[0], valueIter.next());
    }

    public void testInsertionOrder_9_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> keyIter;
        Iterator<V> valueIter;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        keyIter = map.keySet().iterator();
        valueIter = map.values().iterator();

        map.put(keys[1], values[1]);
        keyIter = map.keySet().iterator();
        valueIter = map.values().iterator();

        map.put(keys[1], values[2]);
        keyIter = map.keySet().iterator();
        assertSame(keys[0], keyIter.next());
    }

    public void testInsertionOrder_11_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> keyIter;
        Iterator<V> valueIter;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        keyIter = map.keySet().iterator();
        valueIter = map.values().iterator();

        map.put(keys[1], values[1]);
        keyIter = map.keySet().iterator();
        valueIter = map.values().iterator();

        map.put(keys[1], values[2]);
        keyIter = map.keySet().iterator();
        valueIter = map.values().iterator();
        assertSame(values[0], valueIter.next());
    }

    public void testInsertionOrder_13_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> keyIter;
        Iterator<V> valueIter;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        keyIter = map.keySet().iterator();
        valueIter = map.values().iterator();

        map.put(keys[1], values[1]);
        keyIter = map.keySet().iterator();
        valueIter = map.values().iterator();

        map.put(keys[1], values[2]);
        keyIter = map.keySet().iterator();
        valueIter = map.values().iterator();

        map.put(keys[0], values[3]);
        keyIter = map.keySet().iterator();
        assertSame(keys[0], keyIter.next());
    }

    public void testInsertionOrder_15_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        Iterator<K> keyIter;
        Iterator<V> valueIter;

        resetEmpty();
        map.put(keys[0], values[0]);
        map.put(keys[1], values[1]);
        keyIter = map.keySet().iterator();
        valueIter = map.values().iterator();

        map.put(keys[1], values[1]);
        keyIter = map.keySet().iterator();
        valueIter = map.values().iterator();

        map.put(keys[1], values[2]);
        keyIter = map.keySet().iterator();
        valueIter = map.values().iterator();

        map.put(keys[0], values[3]);
        keyIter = map.keySet().iterator();
        valueIter = map.values().iterator();
        assertSame(values[3], valueIter.next());
    }

    public void testGetByIndex_1_oe() {
        resetEmpty();
        LinkedMap<K, V> lm = getMap();
        try {
            lm.get(0);
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lm.get(-1);
        } catch (final IndexOutOfBoundsException ex) {}

        resetFull();
        lm = getMap();
        try {
            lm.get(-1);
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lm.get(lm.size());
        } catch (final IndexOutOfBoundsException ex) {}

        int i = 0;
        for (final MapIterator<K, V> it = lm.mapIterator(); it.hasNext(); i++) {
            assertSame(it.next(), lm.get(i));
    }
    }

    public void testGetValueByIndex_1_oe() {
        resetEmpty();
        LinkedMap<K, V> lm = getMap();
        try {
            lm.getValue(0);
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lm.getValue(-1);
        } catch (final IndexOutOfBoundsException ex) {}

        resetFull();
        lm = getMap();
        try {
            lm.getValue(-1);
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lm.getValue(lm.size());
        } catch (final IndexOutOfBoundsException ex) {}

        int i = 0;
        for (final MapIterator<K, V> it = lm.mapIterator(); it.hasNext(); i++) {
            it.next();
            assertSame(it.getValue(), lm.getValue(i));
    }
    }

    public void testIndexOf_1_oe() {
        resetEmpty();
        LinkedMap<K, V> lm = getMap();
        assertEquals(-1, lm.indexOf(getOtherKeys()));
    }

    public void testIndexOf_2_oe() {
        resetEmpty();
        LinkedMap<K, V> lm = getMap();

        resetFull();
        lm = getMap();
        final List<K> list = new ArrayList<>();
        for (final MapIterator<K, V> it = lm.mapIterator(); it.hasNext();) {
            list.add(it.next());
        }
        for (int i = 0; i < list.size(); i++) {
            assertEquals(i, lm.indexOf(list.get(i)));
    }
    }

    public void testClone_1_oe() {
        final LinkedMap<K, V> map = new LinkedMap<>(10);
        map.put((K) "1", (V) "1");
        final Map<K, V> cloned = map.clone();
        assertEquals(map.size(), cloned.size());
    }

    public void testClone_2_oe() {
        final LinkedMap<K, V> map = new LinkedMap<>(10);
        map.put((K) "1", (V) "1");
        final Map<K, V> cloned = map.clone();
        assertSame(map.get("1"), cloned.get("1"));
    }

    public void testInitialCapacityZero_1_oe() {
        final LinkedMap<String,String> map = new LinkedMap<>(0);
        assertEquals(1, map.data.length);
    }

}
