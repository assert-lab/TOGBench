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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.map.AbstractSortedMapTest;

/**
 * Abstract test class for {@link SortedBidiMap} methods and contracts.
 *
 */
public abstract class AbstractSortedBidiMapTest_OE25Dev<K extends Comparable<K>, V extends Comparable<V>> extends AbstractOrderedBidiMapTest<K, V> {

    protected List<K> sortedKeys;
    protected List<V> sortedValues = new ArrayList<>();
    protected SortedSet<V> sortedNewValues = new TreeSet<>();

    public AbstractSortedBidiMapTest_OE25Dev(final String testName) {
        super(testName);
        sortedKeys = getAsList(getSampleKeys());
        Collections.sort(sortedKeys);
        sortedKeys = Collections.unmodifiableList(sortedKeys);

        final Map<K, V> map = new TreeMap<>();
        addSampleMappings(map);

        sortedValues.addAll(map.values());
        sortedValues = Collections.unmodifiableList(sortedValues);

        sortedNewValues.addAll(this.<V> getAsList(getNewSampleValues()));
    }

//    public AbstractTestSortedBidiMap() {
//        super();
//        sortedKeys.addAll(Arrays.asList(getSampleValues()));
//        Collections.sort(sortedKeys);
//        sortedKeys = Collections.unmodifiableList(sortedKeys);
//
//        Map map = new TreeMap();
//        for (int i = 0; i < getSampleKeys().length; i++) {
//            map.put(getSampleValues()[i], getSampleKeys()[i]);
//        }
//        sortedValues.addAll(map.values());
//        sortedValues = Collections.unmodifiableList(sortedValues);
//
//        sortedNewValues.addAll(Arrays.asList(getNewSampleValues()));
//    }

    //-----------------------------------------------------------------------
    @Override
    public boolean isAllowNullKey() {
        return false;
    }

    @Override
    public boolean isAllowNullValue() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract SortedBidiMap<K, V> makeObject();

    /**
     * {@inheritDoc}
     */
    @Override
    public SortedBidiMap<K, V> makeFullMap() {
        return (SortedBidiMap<K, V>) super.makeFullMap();
    }

    @Override
    public SortedMap<K, V> makeConfirmedMap() {
        return new TreeMap<>();
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    public BulkTest bulkTestHeadMap() {
        return new AbstractSortedMapTest.TestHeadMap<>(this);
    }

    public BulkTest bulkTestTailMap() {
        return new AbstractSortedMapTest.TestTailMap<>(this);
    }

    public BulkTest bulkTestSubMap() {
        return new AbstractSortedMapTest.TestSubMap<>(this);
    }

    public void testBidiHeadMapContains_1_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K toKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> head = sm.headMap(toKey);
        assertEquals(1, head.size());
    }

    public void testBidiHeadMapContains_2_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K toKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> head = sm.headMap(toKey);
        // removed other assertion
        assertEquals(true, sm.containsKey(first));
    }

    public void testBidiHeadMapContains_3_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K toKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> head = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        assertEquals(true, head.containsKey(first));
    }

    public void testBidiHeadMapContains_4_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K toKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> head = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsValue(firstValue));
    }

    public void testBidiHeadMapContains_5_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K toKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> head = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, head.containsValue(firstValue));
    }

    public void testBidiHeadMapContains_6_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K toKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> head = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsKey(second));
    }

    public void testBidiHeadMapContains_7_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K toKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> head = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, head.containsKey(second));
    }

    public void testBidiHeadMapContains_8_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K toKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> head = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsValue(secondValue));
    }

    public void testBidiHeadMapContains_9_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K toKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> head = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, head.containsValue(secondValue));
    }

    public void testBidiClearByHeadMap_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        assertEquals(2, sub.size());
    }

    public void testBidiClearByHeadMap_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        assertEquals(0, sub.size());
    }

    public void testBidiClearByHeadMap_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        assertEquals(size - 2, sm.size());
    }

    public void testBidiClearByHeadMap_4_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        assertEquals(size - 2, sm.inverseBidiMap().size());
    }

    public void testBidiClearByHeadMap_5_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(false, sm.containsKey(first));
    }

    public void testBidiClearByHeadMap_6_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(false, sm.containsValue(firstValue));
    }

    public void testBidiClearByHeadMap_7_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(firstValue));
    }

    public void testBidiClearByHeadMap_8_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(first));
    }

    public void testBidiClearByHeadMap_9_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(first));
    }

    public void testBidiClearByHeadMap_10_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(firstValue));
    }

    public void testBidiClearByHeadMap_11_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(false, sm.containsKey(second));
    }

    public void testBidiClearByHeadMap_12_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(false, sm.containsValue(secondValue));
    }

    public void testBidiClearByHeadMap_13_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(secondValue));
    }

    public void testBidiClearByHeadMap_14_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(second));
    }

    public void testBidiClearByHeadMap_15_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(second));
    }

    public void testBidiClearByHeadMap_16_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(secondValue));
    }

    public void testBidiClearByHeadMap_17_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(true, sm.containsKey(toKey));
    }

    public void testBidiClearByHeadMap_18_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(true, sm.containsValue(toKeyValue));
    }

    public void testBidiClearByHeadMap_19_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.inverseBidiMap().containsKey(toKeyValue));
    }

    public void testBidiClearByHeadMap_20_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.inverseBidiMap().containsValue(toKey));
    }

    public void testBidiClearByHeadMap_21_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(toKey));
    }

    public void testBidiClearByHeadMap_22_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.headMap(toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(toKeyValue));
    }

    public void testBidiRemoveByHeadMap_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        assertEquals(2, sub.size());
    }

    public void testBidiRemoveByHeadMap_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        assertEquals(true, sm.containsKey(first));
    }

    public void testBidiRemoveByHeadMap_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.containsKey(first));
    }

    public void testBidiRemoveByHeadMap_4_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsKey(second));
    }

    public void testBidiRemoveByHeadMap_5_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.containsKey(second));
    }

    public void testBidiRemoveByHeadMap_6_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        assertEquals(1, sub.size());
    }

    public void testBidiRemoveByHeadMap_7_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        assertEquals(size - 1, sm.size());
    }

    public void testBidiRemoveByHeadMap_8_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        assertEquals(size - 1, sm.inverseBidiMap().size());
    }

    public void testBidiRemoveByHeadMap_9_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsKey(first));
    }

    public void testBidiRemoveByHeadMap_10_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsValue(firstValue));
    }

    public void testBidiRemoveByHeadMap_11_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(firstValue));
    }

    public void testBidiRemoveByHeadMap_12_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(first));
    }

    public void testBidiRemoveByHeadMap_13_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(first));
    }

    public void testBidiRemoveByHeadMap_14_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(firstValue));
    }

    public void testBidiRemoveByHeadMap_15_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        assertEquals(0, sub.size());
    }

    public void testBidiRemoveByHeadMap_16_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        // removed other assertion
        assertEquals(size - 2, sm.size());
    }

    public void testBidiRemoveByHeadMap_17_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        assertEquals(size - 2, sm.inverseBidiMap().size());
    }

    public void testBidiRemoveByHeadMap_18_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsKey(second));
    }

    public void testBidiRemoveByHeadMap_19_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsValue(secondValue));
    }

    public void testBidiRemoveByHeadMap_20_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(secondValue));
    }

    public void testBidiRemoveByHeadMap_21_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(second));
    }

    public void testBidiRemoveByHeadMap_22_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(second));
    }

    public void testBidiRemoveByHeadMap_23_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(secondValue));
    }

    public void testBidiRemoveByHeadMapEntrySet_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        assertEquals(2, sub.size());
    }

    public void testBidiRemoveByHeadMapEntrySet_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        assertEquals(2, set.size());
    }

    public void testBidiRemoveByHeadMapEntrySet_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        assertEquals(true, sm.containsKey(first));
    }

    public void testBidiRemoveByHeadMapEntrySet_4_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        assertEquals(true, sub.containsKey(first));
    }

    public void testBidiRemoveByHeadMapEntrySet_5_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        assertEquals(true, set.contains(firstEntry));
    }

    public void testBidiRemoveByHeadMapEntrySet_6_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsKey(second));
    }

    public void testBidiRemoveByHeadMapEntrySet_7_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.containsKey(second));
    }

    public void testBidiRemoveByHeadMapEntrySet_8_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, set.contains(secondEntry));
    }

    public void testBidiRemoveByHeadMapEntrySet_9_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        assertEquals(1, sub.size());
    }

    public void testBidiRemoveByHeadMapEntrySet_10_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        assertEquals(size - 1, sm.size());
    }

    public void testBidiRemoveByHeadMapEntrySet_11_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        assertEquals(size - 1, sm.inverseBidiMap().size());
    }

    public void testBidiRemoveByHeadMapEntrySet_12_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsKey(firstEntry.getKey()));
    }

    public void testBidiRemoveByHeadMapEntrySet_13_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsValue(firstEntry.getValue()));
    }

    public void testBidiRemoveByHeadMapEntrySet_14_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(firstEntry.getValue()));
    }

    public void testBidiRemoveByHeadMapEntrySet_15_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(firstEntry.getKey()));
    }

    public void testBidiRemoveByHeadMapEntrySet_16_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(firstEntry.getKey()));
    }

    public void testBidiRemoveByHeadMapEntrySet_17_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(firstEntry.getValue()));
    }

    public void testBidiRemoveByHeadMapEntrySet_18_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, set.contains(firstEntry));
    }

    public void testBidiRemoveByHeadMapEntrySet_19_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        assertEquals(0, sub.size());
    }

    public void testBidiRemoveByHeadMapEntrySet_20_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        assertEquals(size - 2, sm.size());
    }

    public void testBidiRemoveByHeadMapEntrySet_21_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        assertEquals(size - 2, sm.inverseBidiMap().size());
    }

    public void testBidiRemoveByHeadMapEntrySet_22_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsKey(secondEntry.getKey()));
    }

    public void testBidiRemoveByHeadMapEntrySet_23_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsValue(secondEntry.getValue()));
    }

    public void testBidiRemoveByHeadMapEntrySet_24_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(secondEntry.getValue()));
    }

    public void testBidiRemoveByHeadMapEntrySet_25_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(secondEntry.getKey()));
    }

    public void testBidiRemoveByHeadMapEntrySet_26_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(secondEntry.getKey()));
    }

    public void testBidiRemoveByHeadMapEntrySet_27_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(secondEntry.getValue()));
    }

    public void testBidiRemoveByHeadMapEntrySet_28_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.headMap(toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        // removed other assertion

        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, set.contains(secondEntry));
    }

    public void testBidiTailMapContains_1_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        assertEquals(sm.size() - 1, sub.size());
    }

    public void testBidiTailMapContains_2_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        assertEquals(true, sm.containsKey(first));
    }

    public void testBidiTailMapContains_3_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(first));
    }

    public void testBidiTailMapContains_4_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsValue(firstValue));
    }

    public void testBidiTailMapContains_5_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(firstValue));
    }

    public void testBidiTailMapContains_6_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsKey(fromKey));
    }

    public void testBidiTailMapContains_7_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.containsKey(fromKey));
    }

    public void testBidiTailMapContains_8_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsValue(fromKeyValue));
    }

    public void testBidiTailMapContains_9_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.containsValue(fromKeyValue));
    }

    public void testBidiTailMapContains_10_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsKey(second));
    }

    public void testBidiTailMapContains_11_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.containsKey(second));
    }

    public void testBidiTailMapContains_12_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsValue(secondValue));
    }

    public void testBidiTailMapContains_13_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.containsValue(secondValue));
    }

    public void testBidiClearByTailMap_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        assertEquals(size - 3, sub.size());
    }

    public void testBidiClearByTailMap_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        assertEquals(0, sub.size());
    }

    public void testBidiClearByTailMap_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        assertEquals(3, sm.size());
    }

    public void testBidiClearByTailMap_4_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        assertEquals(3, sm.inverseBidiMap().size());
    }

    public void testBidiClearByTailMap_5_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(true, sm.containsKey(first));
    }

    public void testBidiClearByTailMap_6_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(true, sm.containsValue(firstValue));
    }

    public void testBidiClearByTailMap_7_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.inverseBidiMap().containsKey(firstValue));
    }

    public void testBidiClearByTailMap_8_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.inverseBidiMap().containsValue(first));
    }

    public void testBidiClearByTailMap_9_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(first));
    }

    public void testBidiClearByTailMap_10_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(firstValue));
    }

    public void testBidiClearByTailMap_11_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(false, sm.containsKey(fromKey));
    }

    public void testBidiClearByTailMap_12_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(false, sm.containsValue(fromKeyValue));
    }

    public void testBidiClearByTailMap_13_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(fromKeyValue));
    }

    public void testBidiClearByTailMap_14_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(fromKey));
    }

    public void testBidiClearByTailMap_15_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(fromKey));
    }

    public void testBidiClearByTailMap_16_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(fromKeyValue));
    }

    public void testBidiClearByTailMap_17_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(false, sm.containsKey(second));
    }

    public void testBidiClearByTailMap_18_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(false, sm.containsValue(secondValue));
    }

    public void testBidiClearByTailMap_19_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(secondValue));
    }

    public void testBidiClearByTailMap_20_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(second));
    }

    public void testBidiClearByTailMap_21_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(second));
    }

    public void testBidiClearByTailMap_22_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();

        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);

        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(secondValue));
    }

    public void testBidiRemoveByTailMap_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        assertEquals(true, sm.containsKey(first));
    }

    public void testBidiRemoveByTailMap_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        assertEquals(true, sub.containsKey(first));
    }

    public void testBidiRemoveByTailMap_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsKey(second));
    }

    public void testBidiRemoveByTailMap_4_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.containsKey(second));
    }

    public void testBidiRemoveByTailMap_5_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        assertEquals(size - 3, sub.size());
    }

    public void testBidiRemoveByTailMap_6_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        // removed other assertion
        assertEquals(size - 1, sm.size());
    }

    public void testBidiRemoveByTailMap_7_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        assertEquals(size - 1, sm.inverseBidiMap().size());
    }

    public void testBidiRemoveByTailMap_8_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsKey(first));
    }

    public void testBidiRemoveByTailMap_9_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsValue(firstValue));
    }

    public void testBidiRemoveByTailMap_10_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(firstValue));
    }

    public void testBidiRemoveByTailMap_11_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(first));
    }

    public void testBidiRemoveByTailMap_12_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(first));
    }

    public void testBidiRemoveByTailMap_13_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(firstValue));
    }

    public void testBidiRemoveByTailMap_14_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object secondValue = sub.remove(second);
        assertEquals(size - 4, sub.size());
    }

    public void testBidiRemoveByTailMap_15_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object secondValue = sub.remove(second);
        // removed other assertion
        assertEquals(size - 2, sm.size());
    }

    public void testBidiRemoveByTailMap_16_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        assertEquals(size - 2, sm.inverseBidiMap().size());
    }

    public void testBidiRemoveByTailMap_17_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsKey(second));
    }

    public void testBidiRemoveByTailMap_18_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsValue(secondValue));
    }

    public void testBidiRemoveByTailMap_19_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(secondValue));
    }

    public void testBidiRemoveByTailMap_20_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(second));
    }

    public void testBidiRemoveByTailMap_21_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(second));
    }

    public void testBidiRemoveByTailMap_22_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(secondValue));
    }

    public void testBidiRemoveByTailMapEntrySet_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        assertEquals(true, sm.containsKey(first));
    }

    public void testBidiRemoveByTailMapEntrySet_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        assertEquals(true, sub.containsKey(first));
    }

    public void testBidiRemoveByTailMapEntrySet_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        assertEquals(true, set.contains(firstEntry));
    }

    public void testBidiRemoveByTailMapEntrySet_4_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsKey(second));
    }

    public void testBidiRemoveByTailMapEntrySet_5_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.containsKey(second));
    }

    public void testBidiRemoveByTailMapEntrySet_6_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, set.contains(secondEntry));
    }

    public void testBidiRemoveByTailMapEntrySet_7_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        assertEquals(size - 3, sub.size());
    }

    public void testBidiRemoveByTailMapEntrySet_8_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        assertEquals(size - 1, sm.size());
    }

    public void testBidiRemoveByTailMapEntrySet_9_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        assertEquals(size - 1, sm.inverseBidiMap().size());
    }

    public void testBidiRemoveByTailMapEntrySet_10_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsKey(firstEntry.getKey()));
    }

    public void testBidiRemoveByTailMapEntrySet_11_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsValue(firstEntry.getValue()));
    }

    public void testBidiRemoveByTailMapEntrySet_12_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(firstEntry.getValue()));
    }

    public void testBidiRemoveByTailMapEntrySet_13_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(firstEntry.getKey()));
    }

    public void testBidiRemoveByTailMapEntrySet_14_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(firstEntry.getKey()));
    }

    public void testBidiRemoveByTailMapEntrySet_15_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(firstEntry.getValue()));
    }

    public void testBidiRemoveByTailMapEntrySet_16_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, set.contains(firstEntry));
    }

    public void testBidiRemoveByTailMapEntrySet_17_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        assertEquals(size - 4, sub.size());
    }

    public void testBidiRemoveByTailMapEntrySet_18_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        assertEquals(size - 2, sm.size());
    }

    public void testBidiRemoveByTailMapEntrySet_19_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        assertEquals(size - 2, sm.inverseBidiMap().size());
    }

    public void testBidiRemoveByTailMapEntrySet_20_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsKey(secondEntry.getKey()));
    }

    public void testBidiRemoveByTailMapEntrySet_21_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsValue(secondEntry.getValue()));
    }

    public void testBidiRemoveByTailMapEntrySet_22_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(secondEntry.getValue()));
    }

    public void testBidiRemoveByTailMapEntrySet_23_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(secondEntry.getKey()));
    }

    public void testBidiRemoveByTailMapEntrySet_24_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(secondEntry.getKey()));
    }

    public void testBidiRemoveByTailMapEntrySet_25_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(secondEntry.getValue()));
    }

    public void testBidiRemoveByTailMapEntrySet_26_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.tailMap(fromKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, set.contains(secondEntry));
    }

    public void testBidiSubMapContains_1_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final K toKey = it.next();
        final K third = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);
        final V thirdValue = sm.get(third);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        assertEquals(2, sub.size());
    }

    public void testBidiSubMapContains_2_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final K toKey = it.next();
        final K third = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);
        final V thirdValue = sm.get(third);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        assertEquals(true, sm.containsKey(first));
    }

    public void testBidiSubMapContains_3_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final K toKey = it.next();
        final K third = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);
        final V thirdValue = sm.get(third);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(first));
    }

    public void testBidiSubMapContains_4_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final K toKey = it.next();
        final K third = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);
        final V thirdValue = sm.get(third);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsValue(firstValue));
    }

    public void testBidiSubMapContains_5_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final K toKey = it.next();
        final K third = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);
        final V thirdValue = sm.get(third);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(firstValue));
    }

    public void testBidiSubMapContains_6_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final K toKey = it.next();
        final K third = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);
        final V thirdValue = sm.get(third);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsKey(fromKey));
    }

    public void testBidiSubMapContains_7_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final K toKey = it.next();
        final K third = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);
        final V thirdValue = sm.get(third);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.containsKey(fromKey));
    }

    public void testBidiSubMapContains_8_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final K toKey = it.next();
        final K third = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);
        final V thirdValue = sm.get(third);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsValue(fromKeyValue));
    }

    public void testBidiSubMapContains_9_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final K toKey = it.next();
        final K third = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);
        final V thirdValue = sm.get(third);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.containsValue(fromKeyValue));
    }

    public void testBidiSubMapContains_10_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final K toKey = it.next();
        final K third = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);
        final V thirdValue = sm.get(third);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsKey(second));
    }

    public void testBidiSubMapContains_11_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final K toKey = it.next();
        final K third = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);
        final V thirdValue = sm.get(third);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.containsKey(second));
    }

    public void testBidiSubMapContains_12_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final K toKey = it.next();
        final K third = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);
        final V thirdValue = sm.get(third);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsValue(secondValue));
    }

    public void testBidiSubMapContains_13_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final K toKey = it.next();
        final K third = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);
        final V thirdValue = sm.get(third);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.containsValue(secondValue));
    }

    public void testBidiSubMapContains_14_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final K toKey = it.next();
        final K third = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);
        final V thirdValue = sm.get(third);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsKey(third));
    }

    public void testBidiSubMapContains_15_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final K toKey = it.next();
        final K third = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);
        final V thirdValue = sm.get(third);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(third));
    }

    public void testBidiSubMapContains_16_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final K toKey = it.next();
        final K third = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);
        final V thirdValue = sm.get(third);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsValue(thirdValue));
    }

    public void testBidiSubMapContains_17_oe() {
        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        final K first = it.next();
        final K fromKey = it.next();
        final K second = it.next();
        final K toKey = it.next();
        final K third = it.next();
        final V firstValue = sm.get(first);
        final V fromKeyValue = sm.get(fromKey);
        final V secondValue = sm.get(second);
        final V thirdValue = sm.get(third);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(thirdValue));
    }

    public void testBidiClearBySubMap_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        assertEquals(3, sub.size());
    }

    public void testBidiClearBySubMap_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        assertEquals(0, sub.size());
    }

    public void testBidiClearBySubMap_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        assertEquals(size - 3, sm.size());
    }

    public void testBidiClearBySubMap_4_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        assertEquals(size - 3, sm.inverseBidiMap().size());
    }

    public void testBidiClearBySubMap_5_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(false, sm.containsKey(fromKey));
    }

    public void testBidiClearBySubMap_6_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(false, sm.containsValue(fromKeyValue));
    }

    public void testBidiClearBySubMap_7_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(fromKeyValue));
    }

    public void testBidiClearBySubMap_8_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(fromKey));
    }

    public void testBidiClearBySubMap_9_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(fromKey));
    }

    public void testBidiClearBySubMap_10_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(fromKeyValue));
    }

    public void testBidiClearBySubMap_11_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(false, sm.containsKey(first));
    }

    public void testBidiClearBySubMap_12_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(false, sm.containsValue(firstValue));
    }

    public void testBidiClearBySubMap_13_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(firstValue));
    }

    public void testBidiClearBySubMap_14_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(first));
    }

    public void testBidiClearBySubMap_15_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(first));
    }

    public void testBidiClearBySubMap_16_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(firstValue));
    }

    public void testBidiClearBySubMap_17_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(false, sm.containsKey(second));
    }

    public void testBidiClearBySubMap_18_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(false, sm.containsValue(secondValue));
    }

    public void testBidiClearBySubMap_19_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(secondValue));
    }

    public void testBidiClearBySubMap_20_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(second));
    }

    public void testBidiClearBySubMap_21_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(second));
    }

    public void testBidiClearBySubMap_22_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(secondValue));
    }

    public void testBidiClearBySubMap_23_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(true, sm.containsKey(toKey));
    }

    public void testBidiClearBySubMap_24_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(true, sm.containsValue(toKeyValue));
    }

    public void testBidiClearBySubMap_25_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.inverseBidiMap().containsKey(toKeyValue));
    }

    public void testBidiClearBySubMap_26_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.inverseBidiMap().containsValue(toKey));
    }

    public void testBidiClearBySubMap_27_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(toKey));
    }

    public void testBidiClearBySubMap_28_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final V fromKeyValue = sm.get(fromKey);
        final V firstValue = sm.get(first);
        final V secondValue = sm.get(second);
        final V toKeyValue = sm.get(toKey);

        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final int size = sm.size();
        // removed other assertion
        sub.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(toKeyValue));
    }

    public void testBidiRemoveBySubMap_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        assertEquals(true, sm.containsKey(first));
    }

    public void testBidiRemoveBySubMap_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        assertEquals(true, sub.containsKey(first));
    }

    public void testBidiRemoveBySubMap_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsKey(second));
    }

    public void testBidiRemoveBySubMap_4_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.containsKey(second));
    }

    public void testBidiRemoveBySubMap_5_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        assertEquals(2, sub.size());
    }

    public void testBidiRemoveBySubMap_6_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        assertEquals(size - 1, sm.size());
    }

    public void testBidiRemoveBySubMap_7_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        assertEquals(size - 1, sm.inverseBidiMap().size());
    }

    public void testBidiRemoveBySubMap_8_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsKey(first));
    }

    public void testBidiRemoveBySubMap_9_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsValue(firstValue));
    }

    public void testBidiRemoveBySubMap_10_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(firstValue));
    }

    public void testBidiRemoveBySubMap_11_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(first));
    }

    public void testBidiRemoveBySubMap_12_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(first));
    }

    public void testBidiRemoveBySubMap_13_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(firstValue));
    }

    public void testBidiRemoveBySubMap_14_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        assertEquals(1, sub.size());
    }

    public void testBidiRemoveBySubMap_15_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        // removed other assertion
        assertEquals(size - 2, sm.size());
    }

    public void testBidiRemoveBySubMap_16_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        assertEquals(size - 2, sm.inverseBidiMap().size());
    }

    public void testBidiRemoveBySubMap_17_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsKey(second));
    }

    public void testBidiRemoveBySubMap_18_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsValue(secondValue));
    }

    public void testBidiRemoveBySubMap_19_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(secondValue));
    }

    public void testBidiRemoveBySubMap_20_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(second));
    }

    public void testBidiRemoveBySubMap_21_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(second));
    }

    public void testBidiRemoveBySubMap_22_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V firstValue = sub.remove(first);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final V secondValue = sub.remove(second);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(secondValue));
    }

    public void testBidiRemoveBySubMapEntrySet_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        assertEquals(3, set.size());
    }

    public void testBidiRemoveBySubMapEntrySet_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        assertEquals(true, sm.containsKey(first));
    }

    public void testBidiRemoveBySubMapEntrySet_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        assertEquals(true, sub.containsKey(first));
    }

    public void testBidiRemoveBySubMapEntrySet_4_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        assertEquals(true, set.contains(firstEntry));
    }

    public void testBidiRemoveBySubMapEntrySet_5_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sm.containsKey(second));
    }

    public void testBidiRemoveBySubMapEntrySet_6_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, sub.containsKey(second));
    }

    public void testBidiRemoveBySubMapEntrySet_7_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, set.contains(secondEntry));
    }

    public void testBidiRemoveBySubMapEntrySet_8_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        assertEquals(2, sub.size());
    }

    public void testBidiRemoveBySubMapEntrySet_9_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        assertEquals(size - 1, sm.size());
    }

    public void testBidiRemoveBySubMapEntrySet_10_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        assertEquals(size - 1, sm.inverseBidiMap().size());
    }

    public void testBidiRemoveBySubMapEntrySet_11_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsKey(firstEntry.getKey()));
    }

    public void testBidiRemoveBySubMapEntrySet_12_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsValue(firstEntry.getValue()));
    }

    public void testBidiRemoveBySubMapEntrySet_13_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(firstEntry.getValue()));
    }

    public void testBidiRemoveBySubMapEntrySet_14_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(firstEntry.getKey()));
    }

    public void testBidiRemoveBySubMapEntrySet_15_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(firstEntry.getKey()));
    }

    public void testBidiRemoveBySubMapEntrySet_16_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(firstEntry.getValue()));
    }

    public void testBidiRemoveBySubMapEntrySet_17_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, set.contains(firstEntry));
    }

    public void testBidiRemoveBySubMapEntrySet_18_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        assertEquals(1, sub.size());
    }

    public void testBidiRemoveBySubMapEntrySet_19_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        assertEquals(size - 2, sm.size());
    }

    public void testBidiRemoveBySubMapEntrySet_20_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        assertEquals(size - 2, sm.inverseBidiMap().size());
    }

    public void testBidiRemoveBySubMapEntrySet_21_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsKey(secondEntry.getKey()));
    }

    public void testBidiRemoveBySubMapEntrySet_22_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.containsValue(secondEntry.getValue()));
    }

    public void testBidiRemoveBySubMapEntrySet_23_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsKey(secondEntry.getValue()));
    }

    public void testBidiRemoveBySubMapEntrySet_24_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sm.inverseBidiMap().containsValue(secondEntry.getKey()));
    }

    public void testBidiRemoveBySubMapEntrySet_25_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsKey(secondEntry.getKey()));
    }

    public void testBidiRemoveBySubMapEntrySet_26_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, sub.containsValue(secondEntry.getValue()));
    }

    public void testBidiRemoveBySubMapEntrySet_27_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // extra test as other tests get complex
        final SortedBidiMap<K, V> sm = makeFullMap();
        final Iterator<K> it = sm.keySet().iterator();
        it.next();
        it.next();
        final K fromKey = it.next();
        final K first = it.next();
        final K second = it.next();
        final K toKey = it.next();

        final int size = sm.size();
        final SortedMap<K, V> sub = sm.subMap(fromKey, toKey);
        final Set<Map.Entry<K, V>> set = sub.entrySet();
        // removed other assertion
        final Iterator<Map.Entry<K, V>> it2 = set.iterator();
        it2.next();
        final Map.Entry<K, V> firstEntry = cloneMapEntry(it2.next());
        final Map.Entry<K, V> secondEntry = cloneMapEntry(it2.next());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(firstEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(secondEntry);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, set.contains(secondEntry));
    }

}
