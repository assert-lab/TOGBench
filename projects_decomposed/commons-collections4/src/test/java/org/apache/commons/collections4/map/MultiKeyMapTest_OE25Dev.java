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

import java.util.Map;

import junit.framework.Test;

import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.keyvalue.MultiKey;

/**
 * JUnit tests.
 *
 */
public class MultiKeyMapTest_OE25Dev<K, V> extends AbstractIterableMapTest<MultiKey<? extends K>, V> {

    static final Integer I1 = Integer.valueOf(1);
    static final Integer I2 = Integer.valueOf(2);
    static final Integer I3 = Integer.valueOf(3);
    static final Integer I4 = Integer.valueOf(4);
    static final Integer I5 = Integer.valueOf(5);
    static final Integer I6 = Integer.valueOf(6);
    static final Integer I7 = Integer.valueOf(7);
    static final Integer I8 = Integer.valueOf(8);

    public MultiKeyMapTest_OE25Dev(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(MultiKeyMapTest_OE25Dev.class);
    }

    @Override
    public MultiKeyMap<K, V> makeObject() {
        return new MultiKeyMap<>();
    }

    @Override
    public MultiKey<K>[] getSampleKeys() {
        return getMultiKeyKeys();
    }

    @SuppressWarnings("unchecked")
    private MultiKey<K>[] getMultiKeyKeys() {
        return new MultiKey[] {
            new MultiKey<>(I1, I2),
            new MultiKey<>(I2, I3),
            new MultiKey<>(I3, I4),
            new MultiKey<>(I1, I1, I2),
            new MultiKey<>(I2, I3, I4),
            new MultiKey<>(I3, I7, I6),
            new MultiKey<>(I1, I1, I2, I3),
            new MultiKey<>(I2, I4, I5, I6),
            new MultiKey<>(I3, I6, I7, I8),
            new MultiKey<>(I1, I1, I2, I3, I4),
            new MultiKey<>(I2, I3, I4, I5, I6),
            new MultiKey<>(I3, I5, I6, I7, I8),
        };
    }

    @Override
    @SuppressWarnings("unchecked")
    public V[] getSampleValues() {
        return (V[]) new Object[] {
            "2A", "2B", "2C",
            "3D", "3E", "3F",
            "4G", "4H", "4I",
            "5J", "5K", "5L",
        };
    }

    @Override
    @SuppressWarnings("unchecked")
    public V[] getNewSampleValues() {
        return (V[]) new Object[] {
            "1a", "1b", "1c",
            "2d", "2e", "2f",
            "3g", "3h", "3i",
            "4j", "4k", "4l",
        };
    }

    @Override
    @SuppressWarnings("unchecked")
    public MultiKey<K>[] getOtherKeys() {
        return new MultiKey[] {
            new MultiKey<>(I1, I7),
            new MultiKey<>(I1, I8),
            new MultiKey<>(I2, I4),
            new MultiKey<>(I2, I5),
        };
    }

    @Override
    public boolean isAllowNullKey() {
        return false;
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

//    public void testCreate() throws Exception {
//        resetEmpty();
//        writeExternalFormToDisk(
//            (java.io.Serializable) map,
//            "src/test/resources/data/test/MultiKeyMap.emptyCollection.version4.obj");
//        resetFull();
//        writeExternalFormToDisk(
//            (java.io.Serializable) map,
//            "src/test/resources/data/test/MultiKeyMap.fullCollection.version4.obj");
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MultiKeyMap<K, V> getMap() {
        return (MultiKeyMap<K, V>) super.getMap();
    }

    public void testNullHandling_1_oe() {
        resetFull();
        assertEquals(null, map.get(null));
    }

    public void testNullHandling_2_oe() {
        resetFull();
        // removed other assertion
        assertEquals(false, map.containsKey(null));
    }

    public void testNullHandling_3_oe() {
        resetFull();
        // removed other assertion
        // removed other assertion
        assertEquals(false, map.containsValue(null));
    }

    public void testNullHandling_4_oe() {
        resetFull();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, map.remove(null));
    }

    public void testNullHandling_5_oe() {
        resetFull();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, map.entrySet().contains(null));
    }

    public void testNullHandling_6_oe() {
        resetFull();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, map.keySet().contains(null));
    }

    public void testNullHandling_7_oe() {
        resetFull();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, map.values().contains(null));
    }

    public void testNullHandling_9_oe() {
        resetFull();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
            map.put(null, null);
            // removed other assertion
        } catch (final NullPointerException ex) {}
        assertEquals(null, map.put(new MultiKey<K>(null, null), null));
    }

    public void testMultiKeyGet_1_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                assertEquals(value, multimap.get(key.getKey(0), key.getKey(1)));
    }
    }
    }

    public void testMultiKeyGet_2_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                assertEquals(null, multimap.get(null, key.getKey(1)));
    }
    }
    }

    public void testMultiKeyGet_3_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), null));
    }
    }
    }

    public void testMultiKeyGet_4_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(null, null));
    }
    }
    }

    public void testMultiKeyGet_5_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), key.getKey(1), null));
    }
    }
    }

    public void testMultiKeyGet_6_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), key.getKey(1), null, null));
    }
    }
    }

    public void testMultiKeyGet_7_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), key.getKey(1), null, null, null));
    }
    }
    }

    public void testMultiKeyGet_8_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                assertEquals(value, multimap.get(key.getKey(0), key.getKey(1), key.getKey(2)));
    }
    }
    }

    public void testMultiKeyGet_9_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                assertEquals(null, multimap.get(null, key.getKey(1), key.getKey(2)));
    }
    }
    }

    public void testMultiKeyGet_10_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), null, key.getKey(2)));
    }
    }
    }

    public void testMultiKeyGet_11_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), key.getKey(1), null));
    }
    }
    }

    public void testMultiKeyGet_12_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(null, null, null));
    }
    }
    }

    public void testMultiKeyGet_13_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), key.getKey(1), key.getKey(2), null));
    }
    }
    }

    public void testMultiKeyGet_14_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), key.getKey(1), key.getKey(2), null, null));
    }
    }
    }

    public void testMultiKeyGet_15_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                assertEquals(value, multimap.get(key.getKey(0), key.getKey(1), key.getKey(2), key.getKey(3)));
    }
    }
    }

    public void testMultiKeyGet_16_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                assertEquals(null, multimap.get(null, key.getKey(1), key.getKey(2), key.getKey(3)));
    }
    }
    }

    public void testMultiKeyGet_17_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), null, key.getKey(2), key.getKey(3)));
    }
    }
    }

    public void testMultiKeyGet_18_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), key.getKey(1), null, key.getKey(3)));
    }
    }
    }

    public void testMultiKeyGet_19_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), key.getKey(1), key.getKey(2), null));
    }
    }
    }

    public void testMultiKeyGet_20_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(null, null, null, null));
    }
    }
    }

    public void testMultiKeyGet_21_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), key.getKey(1), key.getKey(2), key.getKey(3), null));
    }
    }
    }

    public void testMultiKeyGet_22_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                assertEquals(value, multimap.get(key.getKey(0), key.getKey(1), key.getKey(2), key.getKey(3), key.getKey(4)));
    }
    }
    }

    public void testMultiKeyGet_23_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                assertEquals(null, multimap.get(null, key.getKey(1), key.getKey(2), key.getKey(3), key.getKey(4)));
    }
    }
    }

    public void testMultiKeyGet_24_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), null, key.getKey(2), key.getKey(3), key.getKey(4)));
    }
    }
    }

    public void testMultiKeyGet_25_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), key.getKey(1), null, key.getKey(3), key.getKey(4)));
    }
    }
    }

    public void testMultiKeyGet_26_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), key.getKey(1), key.getKey(2), null, key.getKey(4)));
    }
    }
    }

    public void testMultiKeyGet_27_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), key.getKey(1), key.getKey(2), key.getKey(3), null));
    }
    }
    }

    public void testMultiKeyGet_28_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(null, null, null, null, null));
    }
    }
    }

    public void testMultiKeyGet_29_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                default:
                fail("Invalid key size");
    }
    }
    }

    public void testMultiKeyContainsKey_1_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                assertEquals(true, multimap.containsKey(key.getKey(0), key.getKey(1)));
    }
    }
    }

    public void testMultiKeyContainsKey_2_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                assertEquals(false, multimap.containsKey(null, key.getKey(1)));
    }
    }
    }

    public void testMultiKeyContainsKey_3_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(key.getKey(0), null));
    }
    }
    }

    public void testMultiKeyContainsKey_4_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(null, null));
    }
    }
    }

    public void testMultiKeyContainsKey_5_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(key.getKey(0), key.getKey(1), null));
    }
    }
    }

    public void testMultiKeyContainsKey_6_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(key.getKey(0), key.getKey(1), null, null));
    }
    }
    }

    public void testMultiKeyContainsKey_7_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(key.getKey(0), key.getKey(1), null, null, null));
    }
    }
    }

    public void testMultiKeyContainsKey_8_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                assertEquals(true, multimap.containsKey(key.getKey(0), key.getKey(1), key.getKey(2)));
    }
    }
    }

    public void testMultiKeyContainsKey_9_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                assertEquals(false, multimap.containsKey(null, key.getKey(1), key.getKey(2)));
    }
    }
    }

    public void testMultiKeyContainsKey_10_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(key.getKey(0), null, key.getKey(2)));
    }
    }
    }

    public void testMultiKeyContainsKey_11_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(key.getKey(0), key.getKey(1), null));
    }
    }
    }

    public void testMultiKeyContainsKey_12_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(null, null, null));
    }
    }
    }

    public void testMultiKeyContainsKey_13_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(key.getKey(0), key.getKey(1), key.getKey(2), null));
    }
    }
    }

    public void testMultiKeyContainsKey_14_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(key.getKey(0), key.getKey(1), key.getKey(2), null, null));
    }
    }
    }

    public void testMultiKeyContainsKey_15_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                assertEquals(true, multimap.containsKey(key.getKey(0), key.getKey(1), key.getKey(2), key.getKey(3)));
    }
    }
    }

    public void testMultiKeyContainsKey_16_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                assertEquals(false, multimap.containsKey(null, key.getKey(1), key.getKey(2), key.getKey(3)));
    }
    }
    }

    public void testMultiKeyContainsKey_17_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(key.getKey(0), null, key.getKey(2), key.getKey(3)));
    }
    }
    }

    public void testMultiKeyContainsKey_18_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(key.getKey(0), key.getKey(1), null, key.getKey(3)));
    }
    }
    }

    public void testMultiKeyContainsKey_19_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(key.getKey(0), key.getKey(1), key.getKey(2), null));
    }
    }
    }

    public void testMultiKeyContainsKey_20_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(null, null, null, null));
    }
    }
    }

    public void testMultiKeyContainsKey_21_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(key.getKey(0), key.getKey(1), key.getKey(2), key.getKey(3), null));
    }
    }
    }

    public void testMultiKeyContainsKey_22_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                assertEquals(true, multimap.containsKey(key.getKey(0), key.getKey(1), key.getKey(2), key.getKey(3), key.getKey(4)));
    }
    }
    }

    public void testMultiKeyContainsKey_23_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                assertEquals(false, multimap.containsKey(null, key.getKey(1), key.getKey(2), key.getKey(3), key.getKey(4)));
    }
    }
    }

    public void testMultiKeyContainsKey_24_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(key.getKey(0), null, key.getKey(2), key.getKey(3), key.getKey(4)));
    }
    }
    }

    public void testMultiKeyContainsKey_25_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(key.getKey(0), key.getKey(1), null, key.getKey(3), key.getKey(4)));
    }
    }
    }

    public void testMultiKeyContainsKey_26_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(key.getKey(0), key.getKey(1), key.getKey(2), null, key.getKey(4)));
    }
    }
    }

    public void testMultiKeyContainsKey_27_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(key.getKey(0), key.getKey(1), key.getKey(2), key.getKey(3), null));
    }
    }
    }

    public void testMultiKeyContainsKey_28_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(false, multimap.containsKey(null, null, null, null, null));
    }
    }
    }

    public void testMultiKeyContainsKey_29_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        final MultiKey<K>[] keys = getMultiKeyKeys();

        for (final MultiKey<K> key : keys) {
            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                default:
                fail("Invalid key size");
    }
    }
    }

    public void testMultiKeyPut_1_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKeyMap<K, V> multimap = new MultiKeyMap<>();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                assertEquals(null, multimap.put(key.getKey(0), key.getKey(1), value));
    }
    }
    }

    public void testMultiKeyPut_8_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKeyMap<K, V> multimap = new MultiKeyMap<>();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), key.getKey(1)));
    }
    }
    }

    public void testMultiKeyPut_10_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKeyMap<K, V> multimap = new MultiKeyMap<>();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                assertEquals(null, multimap.put(key.getKey(0), key.getKey(1), key.getKey(2), value));
    }
    }
    }

    public void testMultiKeyPut_17_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKeyMap<K, V> multimap = new MultiKeyMap<>();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), key.getKey(1), key.getKey(2)));
    }
    }
    }

    public void testMultiKeyPut_19_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKeyMap<K, V> multimap = new MultiKeyMap<>();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                assertEquals(null, multimap.put(key.getKey(0), key.getKey(1), key.getKey(2), key.getKey(3), value));
    }
    }
    }

    public void testMultiKeyPut_26_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKeyMap<K, V> multimap = new MultiKeyMap<>();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), key.getKey(1), key.getKey(2), key.getKey(3)));
    }
    }
    }

    public void testMultiKeyPut_28_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKeyMap<K, V> multimap = new MultiKeyMap<>();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                assertEquals(null, multimap.put(key.getKey(0), key.getKey(1), key.getKey(2), key.getKey(3), key.getKey(4), value));
    }
    }
    }

    public void testMultiKeyPut_35_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKeyMap<K, V> multimap = new MultiKeyMap<>();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                assertEquals(null, multimap.get(key.getKey(0), key.getKey(1), key.getKey(2), key.getKey(3), key.getKey(4)));
    }
    }
    }

    public void testMultiKeyPut_37_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            final MultiKeyMap<K, V> multimap = new MultiKeyMap<>();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                default:
                fail("Invalid key size");
    }
    }
    }

    public void testMultiKeyPutWithNullKey_1_oe() {
        final MultiKeyMap<String, String> map = new MultiKeyMap<>();
        map.put("a", null, "value1");
        map.put("b", null, "value2");
        map.put("c", null, "value3");
        map.put("a", "z",  "value4");
        map.put("a", null, "value5");
        map.put(null, "a", "value6");
        map.put(null, null, "value7");

        assertEquals(6, map.size());
    }

    public void testMultiKeyPutWithNullKey_2_oe() {
        final MultiKeyMap<String, String> map = new MultiKeyMap<>();
        map.put("a", null, "value1");
        map.put("b", null, "value2");
        map.put("c", null, "value3");
        map.put("a", "z",  "value4");
        map.put("a", null, "value5");
        map.put(null, "a", "value6");
        map.put(null, null, "value7");

        // removed other assertion
        assertEquals("value5", map.get("a", null));
    }

    public void testMultiKeyPutWithNullKey_3_oe() {
        final MultiKeyMap<String, String> map = new MultiKeyMap<>();
        map.put("a", null, "value1");
        map.put("b", null, "value2");
        map.put("c", null, "value3");
        map.put("a", "z",  "value4");
        map.put("a", null, "value5");
        map.put(null, "a", "value6");
        map.put(null, null, "value7");

        // removed other assertion
        // removed other assertion
        assertEquals("value4", map.get("a", "z"));
    }

    public void testMultiKeyPutWithNullKey_4_oe() {
        final MultiKeyMap<String, String> map = new MultiKeyMap<>();
        map.put("a", null, "value1");
        map.put("b", null, "value2");
        map.put("c", null, "value3");
        map.put("a", "z",  "value4");
        map.put("a", null, "value5");
        map.put(null, "a", "value6");
        map.put(null, null, "value7");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("value6", map.get(null, "a"));
    }

    public void testMultiKeyRemove_1_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            resetFull();
            final MultiKeyMap<K, V> multimap = getMap();
            final int size = multimap.size();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                assertEquals(true, multimap.containsKey(key.getKey(0), key.getKey(1)));
    }
    }
    }

    public void testMultiKeyRemove_2_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            resetFull();
            final MultiKeyMap<K, V> multimap = getMap();
            final int size = multimap.size();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                assertEquals(value, multimap.removeMultiKey(key.getKey(0), key.getKey(1)));
    }
    }
    }

    public void testMultiKeyRemove_7_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            resetFull();
            final MultiKeyMap<K, V> multimap = getMap();
            final int size = multimap.size();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                assertEquals(true, multimap.containsKey(key.getKey(0), key.getKey(1), key.getKey(2)));
    }
    }
    }

    public void testMultiKeyRemove_8_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            resetFull();
            final MultiKeyMap<K, V> multimap = getMap();
            final int size = multimap.size();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                assertEquals(value, multimap.removeMultiKey(key.getKey(0), key.getKey(1), key.getKey(2)));
    }
    }
    }

    public void testMultiKeyRemove_13_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            resetFull();
            final MultiKeyMap<K, V> multimap = getMap();
            final int size = multimap.size();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                assertEquals(true, multimap.containsKey(key.getKey(0), key.getKey(1), key.getKey(2), key.getKey(3)));
    }
    }
    }

    public void testMultiKeyRemove_14_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            resetFull();
            final MultiKeyMap<K, V> multimap = getMap();
            final int size = multimap.size();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                assertEquals(value, multimap.removeMultiKey(key.getKey(0), key.getKey(1), key.getKey(2), key.getKey(3)));
    }
    }
    }

    public void testMultiKeyRemove_19_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            resetFull();
            final MultiKeyMap<K, V> multimap = getMap();
            final int size = multimap.size();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                assertEquals(true, multimap.containsKey(key.getKey(0), key.getKey(1), key.getKey(2), key.getKey(3), key.getKey(4)));
    }
    }
    }

    public void testMultiKeyRemove_20_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            resetFull();
            final MultiKeyMap<K, V> multimap = getMap();
            final int size = multimap.size();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                assertEquals(value, multimap.removeMultiKey(key.getKey(0), key.getKey(1), key.getKey(2), key.getKey(3), key.getKey(4)));
    }
    }
    }

    public void testMultiKeyRemove_25_oe() {
        final MultiKey<K>[] keys = getMultiKeyKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            resetFull();
            final MultiKeyMap<K, V> multimap = getMap();
            final int size = multimap.size();

            final MultiKey<K> key = keys[i];
            final V value = values[i];

            switch (key.size()) {
                case 2:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 3:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 4:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                case 5:
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                break;
                default:
                fail("Invalid key size");
    }
    }
    }

    public void testMultiKeyRemoveAll1_1_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        assertEquals(12, multimap.size());
    }

    public void testMultiKeyRemoveAll1_2_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        // removed other assertion

        multimap.removeAll(I1);
        assertEquals(8, multimap.size());
    }

    public void testMultiKeyRemoveAll1_3_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        // removed other assertion

        multimap.removeAll(I1);
        // removed other assertion
        for (final MapIterator<MultiKey<? extends K>, V> it = multimap.mapIterator(); it.hasNext();) {
            final MultiKey<? extends K> key = it.next();
            assertEquals(false, I1.equals(key.getKey(0)));
    }
    }

    public void testMultiKeyRemoveAll2_1_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        assertEquals(12, multimap.size());
    }

    public void testMultiKeyRemoveAll2_2_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        // removed other assertion

        multimap.removeAll(I2, I3);
        assertEquals(9, multimap.size());
    }

    public void testMultiKeyRemoveAll2_3_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        // removed other assertion

        multimap.removeAll(I2, I3);
        // removed other assertion
        for (final MapIterator<MultiKey<? extends K>, V> it = multimap.mapIterator(); it.hasNext();) {
            final MultiKey<? extends K> key = it.next();
            assertEquals(false, I2.equals(key.getKey(0)) && I3.equals(key.getKey(1)));
    }
    }

    public void testMultiKeyRemoveAll3_1_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        assertEquals(12, multimap.size());
    }

    public void testMultiKeyRemoveAll3_2_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        // removed other assertion

        multimap.removeAll(I1, I1, I2);
        assertEquals(9, multimap.size());
    }

    public void testMultiKeyRemoveAll3_3_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        // removed other assertion

        multimap.removeAll(I1, I1, I2);
        // removed other assertion
        for (final MapIterator<MultiKey<? extends K>, V> it = multimap.mapIterator(); it.hasNext();) {
            final MultiKey<? extends K> key = it.next();
            assertEquals(false, I1.equals(key.getKey(0)) && I1.equals(key.getKey(1)) && I2.equals(key.getKey(2)));
    }
    }

    public void testMultiKeyRemoveAll4_1_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        assertEquals(12, multimap.size());
    }

    public void testMultiKeyRemoveAll4_2_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        // removed other assertion

        multimap.removeAll(I1, I1, I2, I3);
        assertEquals(10, multimap.size());
    }

    public void testMultiKeyRemoveAll4_3_oe() {
        resetFull();
        final MultiKeyMap<K, V> multimap = getMap();
        // removed other assertion

        multimap.removeAll(I1, I1, I2, I3);
        // removed other assertion
        for (final MapIterator<MultiKey<? extends K>, V> it = multimap.mapIterator(); it.hasNext();) {
            final MultiKey<? extends K> key = it.next();
            assertEquals(false, I1.equals(key.getKey(0)) && I1.equals(key.getKey(1)) && I2.equals(key.getKey(2)) && key.size() >= 4 && I3.equals(key.getKey(3)));
    }
    }

    public void testClone_1_oe() {
        final MultiKeyMap<K, V> map = new MultiKeyMap<>();
        map.put(new MultiKey<>((K) I1, (K) I2), (V) "1-2");
        final Map<MultiKey<? extends K>, V> cloned = map.clone();
        assertEquals(map.size(), cloned.size());
    }

    public void testClone_2_oe() {
        final MultiKeyMap<K, V> map = new MultiKeyMap<>();
        map.put(new MultiKey<>((K) I1, (K) I2), (V) "1-2");
        final Map<MultiKey<? extends K>, V> cloned = map.clone();
        // removed other assertion
        assertSame(map.get(new MultiKey<>((K) I1, (K) I2)), cloned.get(new MultiKey<>((K) I1, (K) I2)));
    }

    public void testLRUMultiKeyMap_1_oe() {
        final MultiKeyMap<K, V> map = MultiKeyMap.multiKeyMap(new LRUMap<MultiKey<? extends K>, V>(2));
        map.put((K) I1, (K) I2, (V) "1-2");
        map.put((K) I1, (K) I3, (V) "1-1");
        assertEquals(2, map.size());
    }

    public void testLRUMultiKeyMap_2_oe() {
        final MultiKeyMap<K, V> map = MultiKeyMap.multiKeyMap(new LRUMap<MultiKey<? extends K>, V>(2));
        map.put((K) I1, (K) I2, (V) "1-2");
        map.put((K) I1, (K) I3, (V) "1-1");
        // removed other assertion
        map.put((K) I1, (K) I4, (V) "1-4");
        assertEquals(2, map.size());
    }

    public void testLRUMultiKeyMap_3_oe() {
        final MultiKeyMap<K, V> map = MultiKeyMap.multiKeyMap(new LRUMap<MultiKey<? extends K>, V>(2));
        map.put((K) I1, (K) I2, (V) "1-2");
        map.put((K) I1, (K) I3, (V) "1-1");
        // removed other assertion
        map.put((K) I1, (K) I4, (V) "1-4");
        // removed other assertion
        assertEquals(true, map.containsKey(I1, I3));
    }

    public void testLRUMultiKeyMap_4_oe() {
        final MultiKeyMap<K, V> map = MultiKeyMap.multiKeyMap(new LRUMap<MultiKey<? extends K>, V>(2));
        map.put((K) I1, (K) I2, (V) "1-2");
        map.put((K) I1, (K) I3, (V) "1-1");
        // removed other assertion
        map.put((K) I1, (K) I4, (V) "1-4");
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsKey(I1, I4));
    }

    public void testLRUMultiKeyMap_5_oe() {
        final MultiKeyMap<K, V> map = MultiKeyMap.multiKeyMap(new LRUMap<MultiKey<? extends K>, V>(2));
        map.put((K) I1, (K) I2, (V) "1-2");
        map.put((K) I1, (K) I3, (V) "1-1");
        // removed other assertion
        map.put((K) I1, (K) I4, (V) "1-4");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, map.containsKey(I1, I2));
    }

    public void testLRUMultiKeyMap_6_oe() {
        final MultiKeyMap<K, V> map = MultiKeyMap.multiKeyMap(new LRUMap<MultiKey<? extends K>, V>(2));
        map.put((K) I1, (K) I2, (V) "1-2");
        map.put((K) I1, (K) I3, (V) "1-1");
        // removed other assertion
        map.put((K) I1, (K) I4, (V) "1-4");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MultiKeyMap<K, V> cloned = map.clone();
        assertEquals(2, map.size());
    }

    public void testLRUMultiKeyMap_7_oe() {
        final MultiKeyMap<K, V> map = MultiKeyMap.multiKeyMap(new LRUMap<MultiKey<? extends K>, V>(2));
        map.put((K) I1, (K) I2, (V) "1-2");
        map.put((K) I1, (K) I3, (V) "1-1");
        // removed other assertion
        map.put((K) I1, (K) I4, (V) "1-4");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MultiKeyMap<K, V> cloned = map.clone();
        // removed other assertion
        assertEquals(true, cloned.containsKey(I1, I3));
    }

    public void testLRUMultiKeyMap_8_oe() {
        final MultiKeyMap<K, V> map = MultiKeyMap.multiKeyMap(new LRUMap<MultiKey<? extends K>, V>(2));
        map.put((K) I1, (K) I2, (V) "1-2");
        map.put((K) I1, (K) I3, (V) "1-1");
        // removed other assertion
        map.put((K) I1, (K) I4, (V) "1-4");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MultiKeyMap<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        assertEquals(true, cloned.containsKey(I1, I4));
    }

    public void testLRUMultiKeyMap_9_oe() {
        final MultiKeyMap<K, V> map = MultiKeyMap.multiKeyMap(new LRUMap<MultiKey<? extends K>, V>(2));
        map.put((K) I1, (K) I2, (V) "1-2");
        map.put((K) I1, (K) I3, (V) "1-1");
        // removed other assertion
        map.put((K) I1, (K) I4, (V) "1-4");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MultiKeyMap<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false, cloned.containsKey(I1, I2));
    }

    public void testLRUMultiKeyMap_10_oe() {
        final MultiKeyMap<K, V> map = MultiKeyMap.multiKeyMap(new LRUMap<MultiKey<? extends K>, V>(2));
        map.put((K) I1, (K) I2, (V) "1-2");
        map.put((K) I1, (K) I3, (V) "1-1");
        // removed other assertion
        map.put((K) I1, (K) I4, (V) "1-4");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MultiKeyMap<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        cloned.put((K) I1, (K) I5, (V) "1-5");
        assertEquals(2, cloned.size());
    }

    public void testLRUMultiKeyMap_11_oe() {
        final MultiKeyMap<K, V> map = MultiKeyMap.multiKeyMap(new LRUMap<MultiKey<? extends K>, V>(2));
        map.put((K) I1, (K) I2, (V) "1-2");
        map.put((K) I1, (K) I3, (V) "1-1");
        // removed other assertion
        map.put((K) I1, (K) I4, (V) "1-4");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MultiKeyMap<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        cloned.put((K) I1, (K) I5, (V) "1-5");
        // removed other assertion
        assertEquals(true, cloned.containsKey(I1, I4));
    }

    public void testLRUMultiKeyMap_12_oe() {
        final MultiKeyMap<K, V> map = MultiKeyMap.multiKeyMap(new LRUMap<MultiKey<? extends K>, V>(2));
        map.put((K) I1, (K) I2, (V) "1-2");
        map.put((K) I1, (K) I3, (V) "1-1");
        // removed other assertion
        map.put((K) I1, (K) I4, (V) "1-4");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MultiKeyMap<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        cloned.put((K) I1, (K) I5, (V) "1-5");
        // removed other assertion
        // removed other assertion
        assertEquals(true, cloned.containsKey(I1, I5));
    }

}
