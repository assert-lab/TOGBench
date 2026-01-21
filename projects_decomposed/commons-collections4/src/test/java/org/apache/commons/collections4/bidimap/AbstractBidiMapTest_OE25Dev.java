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
package org.apache.commons.collections4.bidimap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.iterators.AbstractMapIteratorTest;
import org.apache.commons.collections4.map.AbstractIterableMapTest;

/**
 * Abstract test class for {@link BidiMap} methods and contracts.
 *
 */
public abstract class AbstractBidiMapTest_OE25Dev<K, V> extends AbstractIterableMapTest<K, V> {

    public AbstractBidiMapTest_OE25Dev(final String testName) {
        super(testName);
    }

    public AbstractBidiMapTest_OE25Dev() {
        super("Inverse");
    }

    //-----------------------------------------------------------------------
    /**
     * Override to create a full <code>BidiMap</code> other than the default.
     *
     * @return a full <code>BidiMap</code> implementation.
     */
    @Override
    public BidiMap<K, V> makeFullMap() {
        return (BidiMap<K, V>) super.makeFullMap();
    }

    /**
     * Override to return the empty BidiMap.
     */
    @Override
    public abstract BidiMap<K, V> makeObject();

    /**
     * Override to indicate to AbstractTestMap this is a BidiMap.
     */
    @Override
    public boolean isAllowDuplicateValues() {
        return false;
    }

    /**
     * Override as DualHashBidiMap didn't exist until version 3.
     */
    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    // BidiPut
    //-----------------------------------------------------------------------

    /**
     * Verifies that {@link #map} is still equal to {@link #confirmed}.
     * <p>
     * This implementation checks the inverse map as well.
     */
    @Override
    public void verify() {
        verifyInverse();
        super.verify();
    }

    public void verifyInverse() {
        assertEquals(map.size(), ((BidiMap<K, V>) map).inverseBidiMap().size());
        final Map<K, V> map1 = new HashMap<>(map);
        final Map<V, K> map2 = new HashMap<>(((BidiMap<K, V>) map).inverseBidiMap());
        final Set<K> keys1 = map1.keySet();
        final Set<V> keys2 = map2.keySet();
        final Collection<V> values1 = map1.values();
        final Collection<K> values2 = map2.values();
        assertEquals(true, keys1.containsAll(values2));
        assertEquals(true, values2.containsAll(keys1));
        assertEquals(true, values1.containsAll(keys2));
        assertEquals(true, keys2.containsAll(values1));
    }

    // testGetKey
    //-----------------------------------------------------------------------
    public void testBidiGetKey() {
        doTestGetKey(makeFullMap(), getSampleKeys()[0], getSampleValues()[0]);
    }

    public void testBidiGetKeyInverse() {
        doTestGetKey(
            makeFullMap().inverseBidiMap(),
            getSampleValues()[0],
            getSampleKeys()[0]);
    }

    private void doTestGetKey(final BidiMap<?, ?> map, final Object key, final Object value) {
        assertEquals("Value not found for key.", value, map.get(key));
        assertEquals("Key not found for value.", key, map.getKey(value));
    }

    // testInverse
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    public void testBidiModifyEntrySet() {
        if (!isSetValueSupported()) {
            return;
        }

        modifyEntrySet(makeFullMap());
        modifyEntrySet(makeFullMap().inverseBidiMap());
    }

    @SuppressWarnings("unchecked")
    private <T> void modifyEntrySet(final BidiMap<?, T> map) {
        // Gets first entry
        final Map.Entry<?, T> entry = map.entrySet().iterator().next();

        // Gets key and value
        final Object key = entry.getKey();
        final Object oldValue = entry.getValue();

        // Sets new value
        final Object newValue = "newValue";
        entry.setValue((T) newValue);

        assertEquals(
            "Modifying entrySet did not affect underlying Map.",
            newValue,
            map.get(key));

        assertNull(
            "Modifying entrySet did not affect inverse Map.",
            map.getKey(oldValue));
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    private void remove(final BidiMap<?, ?> map, final Object key) {
        final Object value = map.remove(key);
        assertTrue("Key was not removed.", !map.containsKey(key));
        assertNull("Value was not removed.", map.getKey(value));
    }

    private void removeValue(final BidiMap<?, ?> map, final Object value) {
        final Object key = map.removeValue(value);
        assertTrue("Key was not removed.", !map.containsKey(key));
        assertNull("Value was not removed.", map.getKey(value));
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    public void testBidiRemoveByKeySet() {
        if (!isRemoveSupported()) {
            return;
        }

        removeByKeySet(makeFullMap(), getSampleKeys()[0], getSampleValues()[0]);
        removeByKeySet(makeFullMap().inverseBidiMap(), getSampleValues()[0], getSampleKeys()[0]);
    }

    private void removeByKeySet(final BidiMap<?, ?> map, final Object key, final Object value) {
        map.keySet().remove(key);

        assertTrue("Key was not removed.", !map.containsKey(key));
        assertTrue("Value was not removed.", !map.containsValue(value));

        assertTrue(
            "Key was not removed from inverse map.",
            !map.inverseBidiMap().containsValue(key));
        assertTrue(
            "Value was not removed from inverse map.",
            !map.inverseBidiMap().containsKey(value));
    }

    //-----------------------------------------------------------------------
    public void testBidiRemoveByEntrySet() {
        if (!isRemoveSupported()) {
            return;
        }

        removeByEntrySet(makeFullMap(), getSampleKeys()[0], getSampleValues()[0]);
        removeByEntrySet(makeFullMap().inverseBidiMap(), getSampleValues()[0], getSampleKeys()[0]);
    }

    private void removeByEntrySet(final BidiMap<?, ?> map, final Object key, final Object value) {
        final Map<Object, Object> temp = new HashMap<>();
        temp.put(key, value);
        map.entrySet().remove(temp.entrySet().iterator().next());

        assertTrue("Key was not removed.", !map.containsKey(key));
        assertTrue("Value was not removed.", !map.containsValue(value));

        assertTrue(
            "Key was not removed from inverse map.",
            !map.inverseBidiMap().containsValue(key));
        assertTrue(
            "Value was not removed from inverse map.",
            !map.inverseBidiMap().containsKey(value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BidiMap<K, V> getMap() {
        return (BidiMap<K, V>) super.getMap();
    }

    //-----------------------------------------------------------------------
    @Override
    public BulkTest bulkTestMapEntrySet() {
        return new TestBidiMapEntrySet();
    }

    public class TestBidiMapEntrySet extends TestMapEntrySet {
        public TestBidiMapEntrySet() {
            super();
        }
    }

    public BulkTest bulkTestInverseMap() {
        return new TestInverseBidiMap(this);
    }

    public class TestInverseBidiMap extends AbstractBidiMapTest<V, K> {
        final AbstractBidiMapTest<K, V> main;

        public TestInverseBidiMap(final AbstractBidiMapTest<K, V> main) {
            super();
            this.main = main;
        }

        @Override
        public BidiMap<V, K> makeObject() {
            return main.makeObject().inverseBidiMap();
        }

        @Override
        public BidiMap<V, K> makeFullMap() {
            return main.makeFullMap().inverseBidiMap();
        }

        @Override
        public V[] getSampleKeys() {
            return main.getSampleValues();
        }
        @Override
        public K[] getSampleValues() {
            return main.getSampleKeys();
        }

        @Override
        public String getCompatibilityVersion() {
            return main.getCompatibilityVersion();
        }

        @Override
        public boolean isAllowNullKey() {
            return main.isAllowNullKey();
        }

        @Override
        public boolean isAllowNullValue() {
            return main.isAllowNullValue();
        }

        @Override
        public boolean isPutAddSupported() {
            return main.isPutAddSupported();
        }

        @Override
        public boolean isPutChangeSupported() {
            return main.isPutChangeSupported();
        }

        @Override
        public boolean isSetValueSupported() {
            return main.isSetValueSupported();
        }

        @Override
        public boolean isRemoveSupported() {
            return main.isRemoveSupported();
        }

    }

    //-----------------------------------------------------------------------
    public BulkTest bulkTestBidiMapIterator() {
        return new TestBidiMapIterator();
    }

    public class TestBidiMapIterator extends AbstractMapIteratorTest<K, V> {
        public TestBidiMapIterator() {
            super("TestBidiMapIterator");
        }

        @Override
        public V[] addSetValues() {
            return AbstractBidiMapTest.this.getNewSampleValues();
        }

        @Override
        public boolean supportsRemove() {
            return AbstractBidiMapTest.this.isRemoveSupported();
        }

        @Override
        public boolean supportsSetValue() {
            return AbstractBidiMapTest.this.isSetValueSupported();
        }

        @Override
        public MapIterator<K, V> makeEmptyIterator() {
            resetEmpty();
            return AbstractBidiMapTest.this.getMap().mapIterator();
        }

        @Override
        public MapIterator<K, V> makeObject() {
            resetFull();
            return AbstractBidiMapTest.this.getMap().mapIterator();
        }

        @Override
        public BidiMap<K, V> getMap() {
            // assumes makeFullMapIterator() called first
            return AbstractBidiMapTest.this.getMap();
        }

        @Override
        public Map<K, V> getConfirmedMap() {
            // assumes makeFullMapIterator() called first
            return AbstractBidiMapTest.this.getConfirmed();
        }

        @Override
        public void verify() {
            super.verify();
            AbstractBidiMapTest.this.verify();
        }
    }

    //-----------------------------------------------------------------------

    public void testBidiPut_1_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        assertEquals(0, map.size());
    }

    public void testBidiPut_2_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        // removed other assertion
        assertEquals(map.size(), inverse.size());
    }

    public void testBidiPut_3_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "B");
        assertEquals(1, map.size());
    }

    public void testBidiPut_4_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "B");
        // removed other assertion
        assertEquals(map.size(), inverse.size());
    }

    public void testBidiPut_5_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "B");
        // removed other assertion
        // removed other assertion
        assertEquals("B", map.get("A"));
    }

    public void testBidiPut_6_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("A", inverse.get("B"));
    }

    public void testBidiPut_7_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "C");
        assertEquals(1, map.size());
    }

    public void testBidiPut_8_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "C");
        // removed other assertion
        assertEquals(map.size(), inverse.size());
    }

    public void testBidiPut_9_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "C");
        // removed other assertion
        // removed other assertion
        assertEquals("C", map.get("A"));
    }

    public void testBidiPut_10_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("A", inverse.get("C"));
    }

    public void testBidiPut_11_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "B", (V) "C");
        assertEquals(1, map.size());
    }

    public void testBidiPut_12_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "B", (V) "C");
        // removed other assertion
        assertEquals(map.size(), inverse.size());
    }

    public void testBidiPut_13_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "B", (V) "C");
        // removed other assertion
        // removed other assertion
        assertEquals("C", map.get("B"));
    }

    public void testBidiPut_14_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "B", (V) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("B", inverse.get("C"));
    }

    public void testBidiPut_15_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "B", (V) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "E", (V) "F");
        assertEquals(2, map.size());
    }

    public void testBidiPut_16_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "B", (V) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "E", (V) "F");
        // removed other assertion
        assertEquals(map.size(), inverse.size());
    }

    public void testBidiPut_17_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "B", (V) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "E", (V) "F");
        // removed other assertion
        // removed other assertion
        assertEquals("F", map.get("E"));
    }

    public void testBidiPut_18_oe() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }

        final BidiMap<K, V> map = makeObject();
        final BidiMap<V, K> inverse = map.inverseBidiMap();
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "A", (V) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "B", (V) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map.put((K) "E", (V) "F");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("E", inverse.get("F"));
    }

    public void testBidiInverse_1_oe() {
        final BidiMap<K, V> map = makeFullMap();
        final BidiMap<V, K> inverseMap = map.inverseBidiMap();

        assertSame( "Inverse of inverse is not equal to original.", map, inverseMap.inverseBidiMap());
    }

    public void testBidiInverse_2_oe() {
        final BidiMap<K, V> map = makeFullMap();
        final BidiMap<V, K> inverseMap = map.inverseBidiMap();

        // removed other assertion

        assertEquals( "Value not found for key.", getSampleKeys()[0], inverseMap.get(getSampleValues()[0]));
    }

    public void testBidiInverse_3_oe() {
        final BidiMap<K, V> map = makeFullMap();
        final BidiMap<V, K> inverseMap = map.inverseBidiMap();

        // removed other assertion

        // removed other assertion

        assertEquals( "Key not found for value.", getSampleValues()[0], inverseMap.getKey(getSampleKeys()[0]));
    }

    public void testBidiClear_2_oe() {
        if (!isRemoveSupported()) {
            try {
                makeFullMap().clear();
                // removed other assertion
            } catch(final UnsupportedOperationException ex) {}
            return;
        }

        BidiMap<?, ?> map = makeFullMap();
        map.clear();
        assertTrue("Map was not cleared.", map.isEmpty());
    }

    public void testBidiClear_3_oe() {
        if (!isRemoveSupported()) {
            try {
                makeFullMap().clear();
                // removed other assertion
            } catch(final UnsupportedOperationException ex) {}
            return;
        }

        BidiMap<?, ?> map = makeFullMap();
        map.clear();
        // removed other assertion
        assertTrue("Inverse map was not cleared.", map.inverseBidiMap().isEmpty());
    }

    public void testBidiClear_4_oe() {
        if (!isRemoveSupported()) {
            try {
                makeFullMap().clear();
                // removed other assertion
            } catch(final UnsupportedOperationException ex) {}
            return;
        }

        BidiMap<?, ?> map = makeFullMap();
        map.clear();
        // removed other assertion
        // removed other assertion

        // Tests clear on inverse
        map = makeFullMap().inverseBidiMap();
        map.clear();
        assertTrue("Map was not cleared.", map.isEmpty());
    }

    public void testBidiClear_5_oe() {
        if (!isRemoveSupported()) {
            try {
                makeFullMap().clear();
                // removed other assertion
            } catch(final UnsupportedOperationException ex) {}
            return;
        }

        BidiMap<?, ?> map = makeFullMap();
        map.clear();
        // removed other assertion
        // removed other assertion

        // Tests clear on inverse
        map = makeFullMap().inverseBidiMap();
        map.clear();
        // removed other assertion
        assertTrue("Inverse map was not cleared.", map.inverseBidiMap().isEmpty());
    }

    public void testBidiRemove_3_oe() {
        if (!isRemoveSupported()) {
            try {
                makeFullMap().remove(getSampleKeys()[0]);
                // removed other assertion
            } catch(final UnsupportedOperationException ex) {}
            try {
                makeFullMap().removeValue(getSampleValues()[0]);
                // removed other assertion
            } catch(final UnsupportedOperationException ex) {}
            return;
        }

        remove(makeFullMap(), getSampleKeys()[0]);
        remove(makeFullMap().inverseBidiMap(), getSampleValues()[0]);

        removeValue(makeFullMap(), getSampleValues()[0]);
        removeValue(makeFullMap().inverseBidiMap(), getSampleKeys()[0]);

        assertEquals(null, makeFullMap().removeValue("NotPresent"));
    }

    public void testBidiKeySetValuesOrder_1_oe() {
        resetFull();
        final Iterator<K> keys = map.keySet().iterator();
        final Iterator<V> values = map.values().iterator();
        for (; keys.hasNext() && values.hasNext();) {
            final K key = keys.next();
            final V value = values.next();
            assertSame(map.get(key), value);
    }
    }

    public void testBidiKeySetValuesOrder_2_oe() {
        resetFull();
        final Iterator<K> keys = map.keySet().iterator();
        final Iterator<V> values = map.values().iterator();
        for (; keys.hasNext() && values.hasNext();) {
            final K key = keys.next();
            final V value = values.next();
            // removed other assertion
        }
        assertEquals(false, keys.hasNext());
    }

    public void testBidiKeySetValuesOrder_3_oe() {
        resetFull();
        final Iterator<K> keys = map.keySet().iterator();
        final Iterator<V> values = map.values().iterator();
        for (; keys.hasNext() && values.hasNext();) {
            final K key = keys.next();
            final V value = values.next();
            // removed other assertion
        }
        // removed other assertion
        assertEquals(false, values.hasNext());
    }

        public void testMapEntrySetIteratorEntrySetValueCrossCheck_1_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestBidiMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestBidiMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestBidiMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestBidiMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            TestBidiMapEntrySet.this.verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            // these checked in superclass
            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            entry2.setValue(newValue2);
            entryConfirmed2.setValue(newValue2);

            // at this point
            // key1=newValue1, key2=newValue2
            try {
                entry2.setValue(newValue1);  // should remove key1
            } catch (final IllegalArgumentException ex) {
                return;  // simplest way of dealing with tricky situation
            }
            entryConfirmed2.setValue(newValue1);
            AbstractBidiMapTest.this.getConfirmed().remove(key1);
            assertEquals(newValue1, entry2.getValue());
        }

        public void testMapEntrySetIteratorEntrySetValueCrossCheck_2_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestBidiMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestBidiMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestBidiMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestBidiMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            TestBidiMapEntrySet.this.verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            // these checked in superclass
            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            entry2.setValue(newValue2);
            entryConfirmed2.setValue(newValue2);

            // at this point
            // key1=newValue1, key2=newValue2
            try {
                entry2.setValue(newValue1);  // should remove key1
            } catch (final IllegalArgumentException ex) {
                return;  // simplest way of dealing with tricky situation
            }
            entryConfirmed2.setValue(newValue1);
            AbstractBidiMapTest.this.getConfirmed().remove(key1);
            // removed other assertion
            assertEquals(true, AbstractBidiMapTest.this.getMap().containsKey(entry2.getKey()));
        }

        public void testMapEntrySetIteratorEntrySetValueCrossCheck_3_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestBidiMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestBidiMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestBidiMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestBidiMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            TestBidiMapEntrySet.this.verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            // these checked in superclass
            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            entry2.setValue(newValue2);
            entryConfirmed2.setValue(newValue2);

            // at this point
            // key1=newValue1, key2=newValue2
            try {
                entry2.setValue(newValue1);  // should remove key1
            } catch (final IllegalArgumentException ex) {
                return;  // simplest way of dealing with tricky situation
            }
            entryConfirmed2.setValue(newValue1);
            AbstractBidiMapTest.this.getConfirmed().remove(key1);
            // removed other assertion
            // removed other assertion
            assertEquals(true, AbstractBidiMapTest.this.getMap().containsValue(newValue1));
        }

        public void testMapEntrySetIteratorEntrySetValueCrossCheck_4_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestBidiMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestBidiMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestBidiMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestBidiMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            TestBidiMapEntrySet.this.verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            // these checked in superclass
            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            entry2.setValue(newValue2);
            entryConfirmed2.setValue(newValue2);

            // at this point
            // key1=newValue1, key2=newValue2
            try {
                entry2.setValue(newValue1);  // should remove key1
            } catch (final IllegalArgumentException ex) {
                return;  // simplest way of dealing with tricky situation
            }
            entryConfirmed2.setValue(newValue1);
            AbstractBidiMapTest.this.getConfirmed().remove(key1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(newValue1, AbstractBidiMapTest.this.getMap().get(entry2.getKey()));
        }

        public void testMapEntrySetIteratorEntrySetValueCrossCheck_5_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestBidiMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestBidiMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestBidiMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestBidiMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            TestBidiMapEntrySet.this.verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            // these checked in superclass
            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            entry2.setValue(newValue2);
            entryConfirmed2.setValue(newValue2);

            // at this point
            // key1=newValue1, key2=newValue2
            try {
                entry2.setValue(newValue1);  // should remove key1
            } catch (final IllegalArgumentException ex) {
                return;  // simplest way of dealing with tricky situation
            }
            entryConfirmed2.setValue(newValue1);
            AbstractBidiMapTest.this.getConfirmed().remove(key1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(false, AbstractBidiMapTest.this.getMap().containsKey(key1));
        }

        public void testMapEntrySetIteratorEntrySetValueCrossCheck_6_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestBidiMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestBidiMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestBidiMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestBidiMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            TestBidiMapEntrySet.this.verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            // these checked in superclass
            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            entry2.setValue(newValue2);
            entryConfirmed2.setValue(newValue2);

            // at this point
            // key1=newValue1, key2=newValue2
            try {
                entry2.setValue(newValue1);  // should remove key1
            } catch (final IllegalArgumentException ex) {
                return;  // simplest way of dealing with tricky situation
            }
            entryConfirmed2.setValue(newValue1);
            AbstractBidiMapTest.this.getConfirmed().remove(key1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(false, AbstractBidiMapTest.this.getMap().containsValue(newValue2));
        }

    public void testBidiMapIteratorSet_1_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        assertEquals(true, it.hasNext());
    }

    public void testBidiMapIteratorSet_3_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        assertSame(key1, it.getKey());
    }

    public void testBidiMapIteratorSet_4_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        assertSame(newValue1, it.getValue());
    }

    public void testBidiMapIteratorSet_5_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        assertEquals(true, bidi.containsKey(key1));
    }

    public void testBidiMapIteratorSet_6_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, bidi.containsValue(newValue1));
    }

    public void testBidiMapIteratorSet_7_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(newValue1, bidi.get(key1));
    }

    public void testBidiMapIteratorSet_8_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        it.setValue(newValue1);  // same value - should be OK
        confirmed.put(key1, newValue1);
        assertSame(key1, it.getKey());
    }

    public void testBidiMapIteratorSet_9_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        it.setValue(newValue1);  // same value - should be OK
        confirmed.put(key1, newValue1);
        // removed other assertion
        assertSame(newValue1, it.getValue());
    }

    public void testBidiMapIteratorSet_10_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        it.setValue(newValue1);  // same value - should be OK
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        assertEquals(true, bidi.containsKey(key1));
    }

    public void testBidiMapIteratorSet_11_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        it.setValue(newValue1);  // same value - should be OK
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, bidi.containsValue(newValue1));
    }

    public void testBidiMapIteratorSet_12_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        it.setValue(newValue1);  // same value - should be OK
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(newValue1, bidi.get(key1));
    }

    public void testBidiMapIteratorSet_13_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        it.setValue(newValue1);  // same value - should be OK
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        final K key2 = it.next();
        it.setValue(newValue2);
        confirmed.put(key2, newValue2);
        assertSame(key2, it.getKey());
    }

    public void testBidiMapIteratorSet_14_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        it.setValue(newValue1);  // same value - should be OK
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        final K key2 = it.next();
        it.setValue(newValue2);
        confirmed.put(key2, newValue2);
        // removed other assertion
        assertSame(newValue2, it.getValue());
    }

    public void testBidiMapIteratorSet_15_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        it.setValue(newValue1);  // same value - should be OK
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        final K key2 = it.next();
        it.setValue(newValue2);
        confirmed.put(key2, newValue2);
        // removed other assertion
        // removed other assertion
        assertEquals(true, bidi.containsKey(key2));
    }

    public void testBidiMapIteratorSet_16_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        it.setValue(newValue1);  // same value - should be OK
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        final K key2 = it.next();
        it.setValue(newValue2);
        confirmed.put(key2, newValue2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, bidi.containsValue(newValue2));
    }

    public void testBidiMapIteratorSet_17_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        it.setValue(newValue1);  // same value - should be OK
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        final K key2 = it.next();
        it.setValue(newValue2);
        confirmed.put(key2, newValue2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(newValue2, bidi.get(key2));
    }

    public void testBidiMapIteratorSet_19_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        it.setValue(newValue1);  // same value - should be OK
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        final K key2 = it.next();
        it.setValue(newValue2);
        confirmed.put(key2, newValue2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        // at this point
        // key1=newValue1, key2=newValue2
        try {
            it.setValue(newValue1);  // should remove key1
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            return;  // simplest way of dealing with tricky situation
        }
        confirmed.put(key2, newValue1);
        AbstractBidiMapTest.this.getConfirmed().remove(key1);
        assertEquals(newValue1, it.getValue());
    }

    public void testBidiMapIteratorSet_20_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        it.setValue(newValue1);  // same value - should be OK
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        final K key2 = it.next();
        it.setValue(newValue2);
        confirmed.put(key2, newValue2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        // at this point
        // key1=newValue1, key2=newValue2
        try {
            it.setValue(newValue1);  // should remove key1
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            return;  // simplest way of dealing with tricky situation
        }
        confirmed.put(key2, newValue1);
        AbstractBidiMapTest.this.getConfirmed().remove(key1);
        // removed other assertion
        assertEquals(true, bidi.containsKey(it.getKey()));
    }

    public void testBidiMapIteratorSet_21_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        it.setValue(newValue1);  // same value - should be OK
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        final K key2 = it.next();
        it.setValue(newValue2);
        confirmed.put(key2, newValue2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        // at this point
        // key1=newValue1, key2=newValue2
        try {
            it.setValue(newValue1);  // should remove key1
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            return;  // simplest way of dealing with tricky situation
        }
        confirmed.put(key2, newValue1);
        AbstractBidiMapTest.this.getConfirmed().remove(key1);
        // removed other assertion
        // removed other assertion
        assertEquals(true, bidi.containsValue(newValue1));
    }

    public void testBidiMapIteratorSet_22_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        it.setValue(newValue1);  // same value - should be OK
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        final K key2 = it.next();
        it.setValue(newValue2);
        confirmed.put(key2, newValue2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        // at this point
        // key1=newValue1, key2=newValue2
        try {
            it.setValue(newValue1);  // should remove key1
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            return;  // simplest way of dealing with tricky situation
        }
        confirmed.put(key2, newValue1);
        AbstractBidiMapTest.this.getConfirmed().remove(key1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(newValue1, bidi.get(it.getKey()));
    }

    public void testBidiMapIteratorSet_23_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        it.setValue(newValue1);  // same value - should be OK
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        final K key2 = it.next();
        it.setValue(newValue2);
        confirmed.put(key2, newValue2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        // at this point
        // key1=newValue1, key2=newValue2
        try {
            it.setValue(newValue1);  // should remove key1
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            return;  // simplest way of dealing with tricky situation
        }
        confirmed.put(key2, newValue1);
        AbstractBidiMapTest.this.getConfirmed().remove(key1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, bidi.containsKey(key1));
    }

    public void testBidiMapIteratorSet_24_oe() {
        final V newValue1 = getOtherValues()[0];
        final V newValue2 = getOtherValues()[1];

        resetFull();
        final BidiMap<K, V> bidi = getMap();
        final MapIterator<K, V> it = bidi.mapIterator();
        // removed other assertion
        final K key1 = it.next();

        if (!isSetValueSupported()) {
            try {
                it.setValue(newValue1);
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {
            }
            return;
        }

        it.setValue(newValue1);
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        it.setValue(newValue1);  // same value - should be OK
        confirmed.put(key1, newValue1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        final K key2 = it.next();
        it.setValue(newValue2);
        confirmed.put(key2, newValue2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        verify();

        // at this point
        // key1=newValue1, key2=newValue2
        try {
            it.setValue(newValue1);  // should remove key1
            // removed other assertion
        } catch (final IllegalArgumentException ex) {
            return;  // simplest way of dealing with tricky situation
        }
        confirmed.put(key2, newValue1);
        AbstractBidiMapTest.this.getConfirmed().remove(key1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, bidi.containsValue(newValue2));
    }

}
