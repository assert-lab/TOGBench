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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.Test;

import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.list.AbstractListTest;

/**
 * Extension of {@link AbstractOrderedMapTest} for exercising the {@link ListOrderedMap}
 * implementation.
 *
 * @since 3.0
 */
public class ListOrderedMapTest_OE25Dev<K, V> extends AbstractOrderedMapTest<K, V> {

    public ListOrderedMapTest_OE25Dev(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(ListOrderedMapTest_OE25Dev.class);
    }

    @Override
    public ListOrderedMap<K, V> makeObject() {
        return ListOrderedMap.listOrderedMap(new HashMap<K, V>());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListOrderedMap<K, V> makeFullMap() {
        return (ListOrderedMap<K, V>) super.makeFullMap();
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    public BulkTest bulkTestKeyListView() {
        return new TestKeyListView();
    }

    public BulkTest bulkTestValueListView() {
        return new TestValueListView();
    }

    //-----------------------------------------------------------------------
    public class TestKeyListView extends AbstractListTest<K> {
        TestKeyListView() {
            super("TestKeyListView");
        }

        @Override
        public List<K> makeObject() {
            return ListOrderedMapTest_OE25Dev.this.makeObject().keyList();
        }
        @Override
        public List<K> makeFullCollection() {
            return ListOrderedMapTest_OE25Dev.this.makeFullMap().keyList();
        }

        @Override
        public K[] getFullElements() {
            return ListOrderedMapTest_OE25Dev.this.getSampleKeys();
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
            return ListOrderedMapTest_OE25Dev.this.isAllowNullKey();
        }
        @Override
        public boolean isTestSerialization() {
            return false;
        }
    }

    //-----------------------------------------------------------------------
    public class TestValueListView extends AbstractListTest<V> {
        TestValueListView() {
            super("TestValueListView");
        }

        @Override
        public List<V> makeObject() {
            return ListOrderedMapTest_OE25Dev.this.makeObject().valueList();
        }
        @Override
        public List<V> makeFullCollection() {
            return ListOrderedMapTest_OE25Dev.this.makeFullMap().valueList();
        }

        @Override
        public V[] getFullElements() {
            return ListOrderedMapTest_OE25Dev.this.getSampleValues();
        }
        @Override
        public boolean isAddSupported() {
            return false;
        }
        @Override
        public boolean isRemoveSupported() {
            return true;
        }
        @Override
        public boolean isSetSupported() {
            return true;
        }
        @Override
        public boolean isNullSupported() {
            return ListOrderedMapTest_OE25Dev.this.isAllowNullKey();
        }
        @Override
        public boolean isTestSerialization() {
            return false;
        }
    }

    //-----------------------------------------------------------------------
    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

//    public void testCreate() throws Exception {
//        resetEmpty();
//        writeExternalFormToDisk(
//            (java.io.Serializable) map,
//            "src/test/resources/data/test/ListOrderedMap.emptyCollection.version4.obj");
//        resetFull();
//        writeExternalFormToDisk(
//            (java.io.Serializable) map,
//            "src/test/resources/data/test/ListOrderedMap.fullCollection.version4.obj");
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListOrderedMap<K, V> getMap() {
        return (ListOrderedMap<K, V>) super.getMap();
    }

    public void testGetByIndex_1_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();
        try {
            lom.get(0);
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.get(-1);
        } catch (final IndexOutOfBoundsException ex) {}

        resetFull();
        lom = getMap();
        try {
            lom.get(-1);
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.get(lom.size());
        } catch (final IndexOutOfBoundsException ex) {}

        int i = 0;
        for (final MapIterator<K, V> it = lom.mapIterator(); it.hasNext(); i++) {
            assertSame(it.next(), lom.get(i));
    }
    }

    public void testGetValueByIndex_1_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();
        try {
            lom.getValue(0);
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.getValue(-1);
        } catch (final IndexOutOfBoundsException ex) {}

        resetFull();
        lom = getMap();
        try {
            lom.getValue(-1);
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.getValue(lom.size());
        } catch (final IndexOutOfBoundsException ex) {}

        int i = 0;
        for (final MapIterator<K, V> it = lom.mapIterator(); it.hasNext(); i++) {
            it.next();
            assertSame(it.getValue(), lom.getValue(i));
    }
    }

    public void testIndexOf_1_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();
        assertEquals(-1, lom.indexOf(getOtherKeys()));
    }

    public void testIndexOf_2_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();
        // removed other assertion

        resetFull();
        lom = getMap();
        final List<K> list = new ArrayList<>();
        for (final MapIterator<K, V> it = lom.mapIterator(); it.hasNext();) {
            list.add(it.next());
        }
        for (int i = 0; i < list.size(); i++) {
            assertEquals(i, lom.indexOf(list.get(i)));
    }
    }

    public void testSetValueByIndex_1_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();
        try {
            lom.setValue(0, (V) "");
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.setValue(-1, (V) "");
        } catch (final IndexOutOfBoundsException ex) {}

        resetFull();
        lom = getMap();
        try {
            lom.setValue(-1, (V) "");
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.setValue(lom.size(), (V) "");
        } catch (final IndexOutOfBoundsException ex) {}

        for (int i = 0; i < lom.size(); i++) {
            final V value = lom.getValue(i);
            final Object input = Integer.valueOf(i);
            assertEquals(value, lom.setValue(i, (V) input));
    }
    }

    public void testSetValueByIndex_2_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();
        try {
            lom.setValue(0, (V) "");
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.setValue(-1, (V) "");
        } catch (final IndexOutOfBoundsException ex) {}

        resetFull();
        lom = getMap();
        try {
            lom.setValue(-1, (V) "");
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.setValue(lom.size(), (V) "");
        } catch (final IndexOutOfBoundsException ex) {}

        for (int i = 0; i < lom.size(); i++) {
            final V value = lom.getValue(i);
            final Object input = Integer.valueOf(i);
            // removed other assertion
            assertEquals(input, lom.getValue(i));
    }
    }

    public void testRemoveByIndex_1_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();
        try {
            lom.remove(0);
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.remove(-1);
        } catch (final IndexOutOfBoundsException ex) {}

        resetFull();
        lom = getMap();
        try {
            lom.remove(-1);
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.remove(lom.size());
        } catch (final IndexOutOfBoundsException ex) {}

        final List<K> list = new ArrayList<>();
        for (final MapIterator<K, V> it = lom.mapIterator(); it.hasNext();) {
            list.add(it.next());
        }
        for (int i = 0; i < list.size(); i++) {
            final Object key = list.get(i);
            final Object value = lom.get(key);
            assertEquals(value, lom.remove(i));
    }
    }

    public void testRemoveByIndex_2_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();
        try {
            lom.remove(0);
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.remove(-1);
        } catch (final IndexOutOfBoundsException ex) {}

        resetFull();
        lom = getMap();
        try {
            lom.remove(-1);
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.remove(lom.size());
        } catch (final IndexOutOfBoundsException ex) {}

        final List<K> list = new ArrayList<>();
        for (final MapIterator<K, V> it = lom.mapIterator(); it.hasNext();) {
            list.add(it.next());
        }
        for (int i = 0; i < list.size(); i++) {
            final Object key = list.get(i);
            final Object value = lom.get(key);
            // removed other assertion
            list.remove(i);
            assertEquals(false, lom.containsKey(key));
    }
    }

    public void testPut_intObjectObject_3_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        assertEquals("testInsert1v", lom.getValue(0));
    }

    public void testPut_intObjectObject_4_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        assertEquals("testInsert1v", lom.getValue(0));
    }

    public void testPut_intObjectObject_5_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        assertEquals("testInsertPutv", lom.getValue(1));
    }

    public void testPut_intObjectObject_6_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        assertEquals("testInsert0v", lom.getValue(0));
    }

    public void testPut_intObjectObject_7_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        assertEquals("testInsert1v", lom.getValue(1));
    }

    public void testPut_intObjectObject_8_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        assertEquals("testInsertPutv", lom.getValue(2));
    }

    public void testPut_intObjectObject_9_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        assertEquals("testInsert0v", lom.getValue(0));
    }

    public void testPut_intObjectObject_10_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        assertEquals("testInsert1v", lom.getValue(1));
    }

    public void testPut_intObjectObject_11_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        assertEquals("testInsertPutv", lom.getValue(2));
    }

    public void testPut_intObjectObject_12_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("testInsert3v", lom.getValue(3));
    }

    public void testPut_intObjectObject_13_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        assertEquals("testInsert0v", lom2.getValue(0));
    }

    public void testPut_intObjectObject_14_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            assertEquals(lom2.getValue(i + 1), lom.getValue(i));
    }
    }

    public void testPut_intObjectObject_15_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        assertEquals(3, lom.size());
    }

    public void testPut_intObjectObject_16_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        assertEquals(3, lom.map.size());
    }

    public void testPut_intObjectObject_17_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        assertEquals(3, lom.keyList().size());
    }

    public void testPut_intObjectObject_18_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("One", lom.getValue(0));
    }

    public void testPut_intObjectObject_19_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(i1, lom.get(0));
    }

    public void testPut_intObjectObject_20_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        assertEquals(3, lom.size());
    }

    public void testPut_intObjectObject_21_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        assertEquals(3, lom.map.size());
    }

    public void testPut_intObjectObject_22_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        assertEquals(3, lom.keyList().size());
    }

    public void testPut_intObjectObject_23_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("One", lom.getValue(0));
    }

    public void testPut_intObjectObject_24_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2", lom.getValue(1));
    }

    public void testPut_intObjectObject_25_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", lom.getValue(2));
    }

    public void testPut_intObjectObject_26_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(i1b, lom.get(0));
    }

    public void testPut_intObjectObject_27_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        assertEquals(3, lom.size());
    }

    public void testPut_intObjectObject_28_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        // removed other assertion
        assertEquals(3, lom.map.size());
    }

    public void testPut_intObjectObject_29_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        assertEquals(3, lom.keyList().size());
    }

    public void testPut_intObjectObject_30_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("One", lom.getValue(0));
    }

    public void testPut_intObjectObject_31_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2", lom.getValue(1));
    }

    public void testPut_intObjectObject_32_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", lom.getValue(2));
    }

    public void testPut_intObjectObject_33_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(2, (K) i1b, (V) "One");
        assertEquals(3, lom.size());
    }

    public void testPut_intObjectObject_34_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(2, (K) i1b, (V) "One");
        // removed other assertion
        assertEquals(3, lom.map.size());
    }

    public void testPut_intObjectObject_35_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(2, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        assertEquals(3, lom.keyList().size());
    }

    public void testPut_intObjectObject_36_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(2, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2", lom.getValue(0));
    }

    public void testPut_intObjectObject_37_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(2, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("One", lom.getValue(1));
    }

    public void testPut_intObjectObject_38_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(2, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", lom.getValue(2));
    }

    public void testPut_intObjectObject_39_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(2, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(3, (K) i1b, (V) "One");
        assertEquals(3, lom.size());
    }

    public void testPut_intObjectObject_40_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(2, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(3, (K) i1b, (V) "One");
        // removed other assertion
        assertEquals(3, lom.map.size());
    }

    public void testPut_intObjectObject_41_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(2, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(3, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        assertEquals(3, lom.keyList().size());
    }

    public void testPut_intObjectObject_42_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(2, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(3, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2", lom.getValue(0));
    }

    public void testPut_intObjectObject_43_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(2, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(3, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", lom.getValue(1));
    }

    public void testPut_intObjectObject_44_oe() {
        resetEmpty();
        ListOrderedMap<K, V> lom = getMap();

        try {
            lom.put(1, (K) "testInsert1", (V) "testInsert1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}
        try {
            lom.put(-1, (K) "testInsert-1", (V) "testInsert-1v");
            // removed other assertion
        } catch (final IndexOutOfBoundsException ex) {}

        // put where key doesn't exist
        lom.put(0, (K) "testInsert1", (V) "testInsert1v");
        // removed other assertion

        lom.put((K) "testInsertPut", (V) "testInsertPutv");
        // removed other assertion
        // removed other assertion

        lom.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lom.put(3, (K) "testInsert3", (V) "testInsert3v");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // put in a full map
        resetFull();
        lom = getMap();
        final ListOrderedMap<K, V> lom2 = new ListOrderedMap<>();
        lom2.putAll(lom);

        lom2.put(0, (K) "testInsert0", (V) "testInsert0v");
        // removed other assertion
        for (int i = 0; i < lom.size(); i++) {
            // removed other assertion
        }

        // put where key does exist
        final Integer i1 = Integer.valueOf(1);
        final Integer i1b = Integer.valueOf(1);
        final Integer i2 = Integer.valueOf(2);
        final Integer i3 = Integer.valueOf(3);

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(0, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(1, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(2, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        resetEmpty();
        lom = getMap();
        lom.put((K) i1, (V) "1");
        lom.put((K) i2, (V) "2");
        lom.put((K) i3, (V) "3");
        lom.put(3, (K) i1b, (V) "One");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("One", lom.getValue(2));
    }

    public void testPutAllWithIndex_1_oe() {
        resetEmpty();
        @SuppressWarnings("unchecked")
        final ListOrderedMap<String, String> lom = (ListOrderedMap<String, String>) map;

        // Create Initial Data
        lom.put("testInsert0", "testInsert0v");
        lom.put("testInsert1", "testInsert1v");
        lom.put("testInsert2", "testInsert2v");
        assertEquals("testInsert0v", lom.getValue(0));
    }

    public void testPutAllWithIndex_2_oe() {
        resetEmpty();
        @SuppressWarnings("unchecked")
        final ListOrderedMap<String, String> lom = (ListOrderedMap<String, String>) map;

        // Create Initial Data
        lom.put("testInsert0", "testInsert0v");
        lom.put("testInsert1", "testInsert1v");
        lom.put("testInsert2", "testInsert2v");
        // removed other assertion
        assertEquals("testInsert1v", lom.getValue(1));
    }

    public void testPutAllWithIndex_3_oe() {
        resetEmpty();
        @SuppressWarnings("unchecked")
        final ListOrderedMap<String, String> lom = (ListOrderedMap<String, String>) map;

        // Create Initial Data
        lom.put("testInsert0", "testInsert0v");
        lom.put("testInsert1", "testInsert1v");
        lom.put("testInsert2", "testInsert2v");
        // removed other assertion
        // removed other assertion
        assertEquals("testInsert2v", lom.getValue(2));
    }

    public void testPutAllWithIndex_4_oe() {
        resetEmpty();
        @SuppressWarnings("unchecked")
        final ListOrderedMap<String, String> lom = (ListOrderedMap<String, String>) map;

        // Create Initial Data
        lom.put("testInsert0", "testInsert0v");
        lom.put("testInsert1", "testInsert1v");
        lom.put("testInsert2", "testInsert2v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create New Test Map and Add using putAll(int, Object, Object)
        final Map<String, String> values = new ListOrderedMap<>();
        values.put("NewInsert0", "NewInsert0v");
        values.put("NewInsert1", "NewInsert1v");
        lom.putAll(1, values);

        // Perform Asserts
        assertEquals("testInsert0v", lom.getValue(0));
    }

    public void testPutAllWithIndex_5_oe() {
        resetEmpty();
        @SuppressWarnings("unchecked")
        final ListOrderedMap<String, String> lom = (ListOrderedMap<String, String>) map;

        // Create Initial Data
        lom.put("testInsert0", "testInsert0v");
        lom.put("testInsert1", "testInsert1v");
        lom.put("testInsert2", "testInsert2v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create New Test Map and Add using putAll(int, Object, Object)
        final Map<String, String> values = new ListOrderedMap<>();
        values.put("NewInsert0", "NewInsert0v");
        values.put("NewInsert1", "NewInsert1v");
        lom.putAll(1, values);

        // Perform Asserts
        // removed other assertion
        assertEquals("NewInsert0v", lom.getValue(1));
    }

    public void testPutAllWithIndex_6_oe() {
        resetEmpty();
        @SuppressWarnings("unchecked")
        final ListOrderedMap<String, String> lom = (ListOrderedMap<String, String>) map;

        // Create Initial Data
        lom.put("testInsert0", "testInsert0v");
        lom.put("testInsert1", "testInsert1v");
        lom.put("testInsert2", "testInsert2v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create New Test Map and Add using putAll(int, Object, Object)
        final Map<String, String> values = new ListOrderedMap<>();
        values.put("NewInsert0", "NewInsert0v");
        values.put("NewInsert1", "NewInsert1v");
        lom.putAll(1, values);

        // Perform Asserts
        // removed other assertion
        // removed other assertion
        assertEquals("NewInsert1v", lom.getValue(2));
    }

    public void testPutAllWithIndex_7_oe() {
        resetEmpty();
        @SuppressWarnings("unchecked")
        final ListOrderedMap<String, String> lom = (ListOrderedMap<String, String>) map;

        // Create Initial Data
        lom.put("testInsert0", "testInsert0v");
        lom.put("testInsert1", "testInsert1v");
        lom.put("testInsert2", "testInsert2v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create New Test Map and Add using putAll(int, Object, Object)
        final Map<String, String> values = new ListOrderedMap<>();
        values.put("NewInsert0", "NewInsert0v");
        values.put("NewInsert1", "NewInsert1v");
        lom.putAll(1, values);

        // Perform Asserts
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("testInsert1v", lom.getValue(3));
    }

    public void testPutAllWithIndex_8_oe() {
        resetEmpty();
        @SuppressWarnings("unchecked")
        final ListOrderedMap<String, String> lom = (ListOrderedMap<String, String>) map;

        // Create Initial Data
        lom.put("testInsert0", "testInsert0v");
        lom.put("testInsert1", "testInsert1v");
        lom.put("testInsert2", "testInsert2v");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create New Test Map and Add using putAll(int, Object, Object)
        final Map<String, String> values = new ListOrderedMap<>();
        values.put("NewInsert0", "NewInsert0v");
        values.put("NewInsert1", "NewInsert1v");
        lom.putAll(1, values);

        // Perform Asserts
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("testInsert2v", lom.getValue(4));
    }

    public void testPutAllWithIndexBug441_1_oe() {
        // see COLLECTIONS-441
        resetEmpty();
        final ListOrderedMap<K, V> lom = getMap();

        final int size = 5;
        for (int i = 0; i < size; i++) {
            lom.put((K) Integer.valueOf(i), (V) Boolean.TRUE);
        }

        final Map<K, V> map = new TreeMap<>();
        for (int i = 0; i < size; i++) {
            map.put((K) Integer.valueOf(i), (V) Boolean.FALSE);
        }

        lom.putAll(3, map);

        final List<K> orderedList = lom.asList();
        for (int i = 0; i < size; i++) {
            assertEquals(Integer.valueOf(i), orderedList.get(i));
    }
    }

    public void testValueList_getByIndex_1_oe() {
        resetFull();
        final ListOrderedMap<K, V> lom = getMap();
        for (int i = 0; i < lom.size(); i++) {
            final V expected = lom.getValue(i);
            assertEquals(expected, lom.valueList().get(i));
    }
    }

    public void testValueList_setByIndex_1_oe() {
        resetFull();
        final ListOrderedMap<K, V> lom = getMap();
        for (int i = 0; i < lom.size(); i++) {
            final Object input = Integer.valueOf(i);
            final V expected = lom.getValue(i);
            assertEquals(expected, lom.valueList().set(i, (V) input));
    }
    }

    public void testValueList_setByIndex_2_oe() {
        resetFull();
        final ListOrderedMap<K, V> lom = getMap();
        for (int i = 0; i < lom.size(); i++) {
            final Object input = Integer.valueOf(i);
            final V expected = lom.getValue(i);
            // removed other assertion
            assertEquals(input, lom.getValue(i));
    }
    }

    public void testValueList_setByIndex_3_oe() {
        resetFull();
        final ListOrderedMap<K, V> lom = getMap();
        for (int i = 0; i < lom.size(); i++) {
            final Object input = Integer.valueOf(i);
            final V expected = lom.getValue(i);
            // removed other assertion
            // removed other assertion
            assertEquals(input, lom.valueList().get(i));
    }
    }

    public void testValueList_removeByIndex_1_oe() {
        resetFull();
        final ListOrderedMap<K, V> lom = getMap();
        while (lom.size() > 1) {
            final V expected = lom.getValue(1);
            assertEquals(expected, lom.valueList().remove(1));
    }
    }

    public void testCOLLECTIONS_474_nullValues_1_oe () {
        final Object key1 = new Object();
        final Object key2 = new Object();
        final HashMap<Object, Object> hmap = new HashMap<>();
        hmap.put(key1, null);
        hmap.put(key2, null);
        assertEquals("Should have two elements", 2, hmap.size());
    }

    public void testCOLLECTIONS_474_nullValues_2_oe () {
        final Object key1 = new Object();
        final Object key2 = new Object();
        final HashMap<Object, Object> hmap = new HashMap<>();
        hmap.put(key1, null);
        hmap.put(key2, null);
        // removed other assertion
        final ListOrderedMap<Object, Object> listMap = new ListOrderedMap<>();
        listMap.put(key1, null);
        listMap.put(key2, null);
        assertEquals("Should have two elements", 2, listMap.size());
    }

    public void testCOLLECTIONS_474_nonNullValues_1_oe () {
        final Object key1 = new Object();
        final Object key2 = new Object();
        final HashMap<Object, Object> hmap = new HashMap<>();
        hmap.put(key1, "1");
        hmap.put(key2, "2");
        assertEquals("Should have two elements", 2, hmap.size());
    }

    public void testCOLLECTIONS_474_nonNullValues_2_oe () {
        final Object key1 = new Object();
        final Object key2 = new Object();
        final HashMap<Object, Object> hmap = new HashMap<>();
        hmap.put(key1, "1");
        hmap.put(key2, "2");
        // removed other assertion
        final ListOrderedMap<Object, Object> listMap = new ListOrderedMap<>();
        listMap.put(key1, "3");
        listMap.put(key2, "4");
        assertEquals("Should have two elements", 2, listMap.size());
    }

}
