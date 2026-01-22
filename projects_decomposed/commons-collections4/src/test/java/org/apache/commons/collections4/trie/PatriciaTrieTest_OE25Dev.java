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
package org.apache.commons.collections4.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;

import junit.framework.Test;

import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.map.AbstractSortedMapTest;
import org.junit.Assert;

/**
 * JUnit tests for the PatriciaTrie.
 *
 * @since 4.0
 */
public class PatriciaTrieTest_OE25Dev<V> extends AbstractSortedMapTest<String, V> {

    public PatriciaTrieTest_OE25Dev(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(PatriciaTrieTest_OE25Dev.class);
    }

    @Override
    public SortedMap<String, V> makeObject() {
        return new PatriciaTrie<>();
    }

    @Override
    public boolean isAllowNullKey() {
        return false;
    }

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
//            "src/test/resources/data/test/PatriciaTrie.emptyCollection.version4.obj");
//        resetFull();
//        writeExternalFormToDisk(
//            (java.io.Serializable) map,
//            "src/test/resources/data/test/PatriciaTrie.fullCollection.version4.obj");
//    }

    public void testPrefixMap_1_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        Assert.assertEquals(8, map.size());
    }

    public void testPrefixMap_2_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        Assert.assertEquals("Alabama", map.firstKey());
    }

    public void testPrefixMap_3_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Alliese", map.lastKey());
    }

    public void testPrefixMap_4_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Albertoo", map.get("Albertoo"));
    }

    public void testPrefixMap_5_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertNotNull(trie.get("Xavier"));
    }

    public void testPrefixMap_6_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertNull(map.get("Xavier"));
    }

    public void testPrefixMap_7_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertNull(trie.get("Alice"));
    }

    public void testPrefixMap_8_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertNull(map.get("Alice"));
    }

    public void testPrefixMap_9_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        Assert.assertEquals("Alabama", iterator.next());
    }

    public void testPrefixMap_10_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        Assert.assertEquals("Albert", iterator.next());
    }

    public void testPrefixMap_11_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Alberto", iterator.next());
    }

    public void testPrefixMap_12_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Albertoo", iterator.next());
    }

    public void testPrefixMap_13_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Alberts", iterator.next());
    }

    public void testPrefixMap_14_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Alien", iterator.next());
    }

    public void testPrefixMap_15_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Allie", iterator.next());
    }

    public void testPrefixMap_16_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Alliese", iterator.next());
    }

    public void testPrefixMap_17_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(iterator.hasNext());
    }

    public void testPrefixMap_18_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        Assert.assertEquals("Albert", iterator.next());
    }

    public void testPrefixMap_19_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        Assert.assertEquals("Alberto", iterator.next());
    }

    public void testPrefixMap_20_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Albertoo", iterator.next());
    }

    public void testPrefixMap_21_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Alberts", iterator.next());
    }

    public void testPrefixMap_22_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(iterator.hasNext());
    }

    public void testPrefixMap_23_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals(4, map.size());
    }

    public void testPrefixMap_24_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Albert", map.firstKey());
    }

    public void testPrefixMap_25_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Alberts", map.lastKey());
    }

    public void testPrefixMap_26_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertNull(trie.get("Albertz"));
    }

    public void testPrefixMap_27_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        Assert.assertEquals("Albertz", trie.get("Albertz"));
    }

    public void testPrefixMap_28_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        Assert.assertEquals(5, map.size());
    }

    public void testPrefixMap_29_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Albertz", map.lastKey());
    }

    public void testPrefixMap_30_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        Assert.assertEquals("Albert", iterator.next());
    }

    public void testPrefixMap_31_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        Assert.assertEquals("Alberto", iterator.next());
    }

    public void testPrefixMap_32_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Albertoo", iterator.next());
    }

    public void testPrefixMap_33_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Alberts", iterator.next());
    }

    public void testPrefixMap_34_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Albertz", iterator.next());
    }

    public void testPrefixMap_35_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(iterator.hasNext());
    }

    public void testPrefixMap_36_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Albertz", map.remove("Albertz"));
    }

    public void testPrefixMap_37_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        Assert.assertEquals(2, map.size());
    }

    public void testPrefixMap_38_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        Assert.assertEquals("Alberto", map.firstKey());
    }

    public void testPrefixMap_39_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Albertoo", map.lastKey());
    }

    public void testPrefixMap_40_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        Assert.assertEquals("Alberto", entry.getKey());
    }

    public void testPrefixMap_41_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        Assert.assertEquals("Alberto", entry.getValue());
    }

    public void testPrefixMap_42_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        Assert.assertEquals("Albertoo", entry.getKey());
    }

    public void testPrefixMap_43_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        Assert.assertEquals("Albertoo", entry.getValue());
    }

    public void testPrefixMap_44_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(entryIterator.hasNext());
    }

    public void testPrefixMap_45_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        Assert.assertEquals(3, map.size());
    }

    public void testPrefixMap_46_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        Assert.assertEquals("Alberto", map.firstKey());
    }

    public void testPrefixMap_47_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Albertoo", map.lastKey());
    }

    public void testPrefixMap_48_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        Assert.assertEquals("Alberto", entry.getKey());
    }

    public void testPrefixMap_49_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        Assert.assertEquals("Alberto", entry.getValue());
    }

    public void testPrefixMap_50_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        Assert.assertEquals("Albertoad", entry.getKey());
    }

    public void testPrefixMap_51_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        Assert.assertEquals("Albertoad", entry.getValue());
    }

    public void testPrefixMap_52_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        Assert.assertEquals("Albertoo", entry.getKey());
    }

    public void testPrefixMap_53_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        Assert.assertEquals("Albertoo", entry.getValue());
    }

    public void testPrefixMap_54_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(entryIterator.hasNext());
    }

    public void testPrefixMap_55_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Albertoo", trie.remove("Albertoo"));
    }

    public void testPrefixMap_56_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Alberto", map.firstKey());
    }

    public void testPrefixMap_57_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Albertoad", map.lastKey());
    }

    public void testPrefixMap_58_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals(2, map.size());
    }

    public void testPrefixMap_59_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        Assert.assertEquals("Alberto", entry.getKey());
    }

    public void testPrefixMap_60_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        Assert.assertEquals("Alberto", entry.getValue());
    }

    public void testPrefixMap_61_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        Assert.assertEquals("Albertoad", entry.getKey());
    }

    public void testPrefixMap_62_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        Assert.assertEquals("Albertoad", entry.getValue());
    }

    public void testPrefixMap_63_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(entryIterator.hasNext());
    }

    public void testPrefixMap_64_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Albertoad", trie.remove("Albertoad"));
    }

    public void testPrefixMap_65_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        Assert.assertEquals(2, map.size());
    }

    public void testPrefixMap_66_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        Assert.assertFalse(map.containsKey("Albert"));
    }

    public void testPrefixMap_67_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(map.containsKey("Xavier"));
    }

    public void testPrefixMap_68_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(map.containsKey("Xalan"));
    }

    public void testPrefixMap_69_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        Assert.assertEquals("Xavier", iterator.next());
    }

    public void testPrefixMap_70_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        Assert.assertEquals("XyZ", iterator.next());
    }

    public void testPrefixMap_71_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(iterator.hasNext());
    }

    public void testPrefixMap_72_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("An");
        Assert.assertEquals(1, map.size());
    }

    public void testPrefixMap_73_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("An");
        // removed other assertion
        Assert.assertEquals("Anna", map.firstKey());
    }

    public void testPrefixMap_74_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("An");
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Anna", map.lastKey());
    }

    public void testPrefixMap_75_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("An");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        Assert.assertEquals("Anna", iterator.next());
    }

    public void testPrefixMap_76_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("An");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        Assert.assertFalse(iterator.hasNext());
    }

    public void testPrefixMap_77_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("An");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ban");
        Assert.assertEquals(1, map.size());
    }

    public void testPrefixMap_78_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("An");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ban");
        // removed other assertion
        Assert.assertEquals("Banane", map.firstKey());
    }

    public void testPrefixMap_79_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("An");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ban");
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Banane", map.lastKey());
    }

    public void testPrefixMap_80_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("An");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ban");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        Assert.assertEquals("Banane", iterator.next());
    }

    public void testPrefixMap_81_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("An");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ban");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        Assert.assertFalse(iterator.hasNext());
    }

    public void testPrefixMap_82_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("An");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ban");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Am");
        Assert.assertFalse(map.isEmpty());
    }

    public void testPrefixMap_83_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("An");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ban");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Am");
        // removed other assertion
        Assert.assertEquals(3, map.size());
    }

    public void testPrefixMap_84_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("An");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ban");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Am");
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Amber", trie.remove("Amber"));
    }

    public void testPrefixMap_85_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("An");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ban");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Am");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        Assert.assertEquals("Amma", iterator.next());
    }

    public void testPrefixMap_86_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("An");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ban");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Am");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        Assert.assertEquals("Ammun", iterator.next());
    }

    public void testPrefixMap_87_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("An");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ban");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Am");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(iterator.hasNext());
    }

    public void testPrefixMap_88_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "",
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map;
        Iterator<String> iterator;
        Iterator<Map.Entry<String, String>> entryIterator;
        Map.Entry<String, String> entry;

        map = trie.prefixMap("Al");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        map.put("Albertz", "Albertz");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Alberto");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoad", "Albertoad");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        entry = entryIterator.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.values().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("An");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ban");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Am");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iterator = map.keySet().iterator();
        map.put("Amber", "Amber");
        Assert.assertEquals(3, map.size());
    }

    public void testPrefixMapRemoval_1_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        Assert.assertEquals(8, map.size());
    }

    public void testPrefixMapRemoval_2_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        // removed other assertion
        Iterator<String> iter = map.keySet().iterator();
        Assert.assertEquals("Alabama", iter.next());
    }

    public void testPrefixMapRemoval_3_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        // removed other assertion
        Iterator<String> iter = map.keySet().iterator();
        // removed other assertion
        Assert.assertEquals("Albert", iter.next());
    }

    public void testPrefixMapRemoval_4_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        // removed other assertion
        Iterator<String> iter = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Alberto", iter.next());
    }

    public void testPrefixMapRemoval_5_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        // removed other assertion
        Iterator<String> iter = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Albertoo", iter.next());
    }

    public void testPrefixMapRemoval_6_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        // removed other assertion
        Iterator<String> iter = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Alberts", iter.next());
    }

    public void testPrefixMapRemoval_7_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        // removed other assertion
        Iterator<String> iter = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Alien", iter.next());
    }

    public void testPrefixMapRemoval_8_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        // removed other assertion
        Iterator<String> iter = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iter.remove();
        Assert.assertEquals(7, map.size());
    }

    public void testPrefixMapRemoval_9_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        // removed other assertion
        Iterator<String> iter = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iter.remove();
        // removed other assertion
        Assert.assertEquals("Allie", iter.next());
    }

    public void testPrefixMapRemoval_10_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        // removed other assertion
        Iterator<String> iter = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iter.remove();
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Alliese", iter.next());
    }

    public void testPrefixMapRemoval_11_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        // removed other assertion
        Iterator<String> iter = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iter.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(iter.hasNext());
    }

    public void testPrefixMapRemoval_12_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        // removed other assertion
        Iterator<String> iter = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iter.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ak");
        Assert.assertEquals(2, map.size());
    }

    public void testPrefixMapRemoval_13_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        // removed other assertion
        Iterator<String> iter = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iter.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ak");
        // removed other assertion
        iter = map.keySet().iterator();
        Assert.assertEquals("Akka", iter.next());
    }

    public void testPrefixMapRemoval_14_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        // removed other assertion
        Iterator<String> iter = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iter.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ak");
        // removed other assertion
        iter = map.keySet().iterator();
        // removed other assertion
        iter.remove();
        Assert.assertEquals(1, map.size());
    }

    public void testPrefixMapRemoval_15_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        // removed other assertion
        Iterator<String> iter = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iter.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ak");
        // removed other assertion
        iter = map.keySet().iterator();
        // removed other assertion
        iter.remove();
        // removed other assertion
        Assert.assertEquals("Akko", iter.next());
    }

    public void testPrefixMapRemoval_16_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        // removed other assertion
        Iterator<String> iter = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iter.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ak");
        // removed other assertion
        iter = map.keySet().iterator();
        // removed other assertion
        iter.remove();
        // removed other assertion
        // removed other assertion
        if(iter.hasNext()) {
            Assert.fail("shouldn't have next (but was: " + iter.next() + ")");
    }
    }

    public void testPrefixMapRemoval_17_oe() {
        final PatriciaTrie<String> trie = new PatriciaTrie<>();

        final String[] keys = new String[]{
                "Albert", "Xavier", "XyZ", "Anna", "Alien", "Alberto",
                "Alberts", "Allie", "Alliese", "Alabama", "Banane",
                "Blabla", "Amber", "Ammun", "Akka", "Akko", "Albertoo",
                "Amma"
        };

        for (final String key : keys) {
            trie.put(key, key);
        }

        SortedMap<String, String> map = trie.prefixMap("Al");
        // removed other assertion
        Iterator<String> iter = map.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        iter.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        map = trie.prefixMap("Ak");
        // removed other assertion
        iter = map.keySet().iterator();
        // removed other assertion
        iter.remove();
        // removed other assertion
        // removed other assertion
        if(iter.hasNext()) {
            // removed other assertion
        }
        Assert.assertFalse(iter.hasNext());
    }

    public void testPrefixMapSizes_1_oe() {
        // COLLECTIONS-525
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");
        assertTrue(aTree.prefixMap("点").containsKey("点评"));
    }

    public void testPrefixMapSizes_2_oe() {
        // COLLECTIONS-525
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");
        // removed other assertion
        assertEquals("测试", aTree.prefixMap("点").get("点评"));
    }

    public void testPrefixMapSizes_3_oe() {
        // COLLECTIONS-525
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");
        // removed other assertion
        // removed other assertion
        assertFalse(aTree.prefixMap("点").isEmpty());
    }

    public void testPrefixMapSizes_4_oe() {
        // COLLECTIONS-525
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, aTree.prefixMap("点").size());
    }

    public void testPrefixMapSizes_5_oe() {
        // COLLECTIONS-525
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, aTree.prefixMap("点").keySet().size());
    }

    public void testPrefixMapSizes_6_oe() {
        // COLLECTIONS-525
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, aTree.prefixMap("点").entrySet().size());
    }

    public void testPrefixMapSizes_7_oe() {
        // COLLECTIONS-525
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, aTree.prefixMap("点评").values().size());
    }

    public void testPrefixMapSizes_8_oe() {
        // COLLECTIONS-525
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        aTree.clear();
        aTree.put("点评", "联盟");
        aTree.put("点版", "定向");
        assertEquals(2, aTree.prefixMap("点").keySet().size());
    }

    public void testPrefixMapSizes_9_oe() {
        // COLLECTIONS-525
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        aTree.clear();
        aTree.put("点评", "联盟");
        aTree.put("点版", "定向");
        // removed other assertion
        assertEquals(2, aTree.prefixMap("点").values().size());
    }

    public void testPrefixMapSizes2_1_oe() {
        final char u8000 = Character.toChars(32768)[0]; // U+8000 (1000000000000000)
        final char char_b = 'b'; // 1100010

        final PatriciaTrie<String> trie = new PatriciaTrie<>();
        final String prefixString = "" + char_b;
        final String longerString = prefixString + u8000;

        assertEquals(1, prefixString.length());
    }

    public void testPrefixMapSizes2_2_oe() {
        final char u8000 = Character.toChars(32768)[0]; // U+8000 (1000000000000000)
        final char char_b = 'b'; // 1100010

        final PatriciaTrie<String> trie = new PatriciaTrie<>();
        final String prefixString = "" + char_b;
        final String longerString = prefixString + u8000;

        // removed other assertion
        assertEquals(2, longerString.length());
    }

    public void testPrefixMapSizes2_3_oe() {
        final char u8000 = Character.toChars(32768)[0]; // U+8000 (1000000000000000)
        final char char_b = 'b'; // 1100010

        final PatriciaTrie<String> trie = new PatriciaTrie<>();
        final String prefixString = "" + char_b;
        final String longerString = prefixString + u8000;

        // removed other assertion
        // removed other assertion

        assertTrue(longerString.startsWith(prefixString));
    }

    public void testPrefixMapSizes2_4_oe() {
        final char u8000 = Character.toChars(32768)[0]; // U+8000 (1000000000000000)
        final char char_b = 'b'; // 1100010

        final PatriciaTrie<String> trie = new PatriciaTrie<>();
        final String prefixString = "" + char_b;
        final String longerString = prefixString + u8000;

        // removed other assertion
        // removed other assertion

        // removed other assertion

        trie.put(prefixString, "prefixString");
        trie.put(longerString, "longerString");

        assertEquals(2, trie.prefixMap(prefixString).size());
    }

    public void testPrefixMapSizes2_5_oe() {
        final char u8000 = Character.toChars(32768)[0]; // U+8000 (1000000000000000)
        final char char_b = 'b'; // 1100010

        final PatriciaTrie<String> trie = new PatriciaTrie<>();
        final String prefixString = "" + char_b;
        final String longerString = prefixString + u8000;

        // removed other assertion
        // removed other assertion

        // removed other assertion

        trie.put(prefixString, "prefixString");
        trie.put(longerString, "longerString");

        // removed other assertion
        assertTrue(trie.prefixMap(prefixString).containsKey(longerString));
    }

    public void testPrefixMapClear_1_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        trie.put("Anna", 1);
        trie.put("Anael", 2);
        trie.put("Analu", 3);
        trie.put("Andreas", 4);
        trie.put("Andrea", 5);
        trie.put("Andres", 6);
        trie.put("Anatole", 7);
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        assertEquals(new HashSet<>(Arrays.asList("Andrea", "Andreas", "Andres")), prefixMap.keySet());
    }

    public void testPrefixMapClear_2_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        trie.put("Anna", 1);
        trie.put("Anael", 2);
        trie.put("Analu", 3);
        trie.put("Andreas", 4);
        trie.put("Andrea", 5);
        trie.put("Andres", 6);
        trie.put("Anatole", 7);
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        // removed other assertion
        assertEquals(Arrays.asList(5, 4, 6), new ArrayList<>(prefixMap.values()));
    }

    public void testPrefixMapClear_3_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        trie.put("Anna", 1);
        trie.put("Anael", 2);
        trie.put("Analu", 3);
        trie.put("Andreas", 4);
        trie.put("Andrea", 5);
        trie.put("Andres", 6);
        trie.put("Anatole", 7);
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        // removed other assertion
        // removed other assertion

        prefixMap.clear();
        assertTrue(prefixMap.isEmpty());
    }

    public void testPrefixMapClear_4_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        trie.put("Anna", 1);
        trie.put("Anael", 2);
        trie.put("Analu", 3);
        trie.put("Andreas", 4);
        trie.put("Andrea", 5);
        trie.put("Andres", 6);
        trie.put("Anatole", 7);
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        // removed other assertion
        // removed other assertion

        prefixMap.clear();
        // removed other assertion
        assertTrue(prefixMap.keySet().isEmpty());
    }

    public void testPrefixMapClear_5_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        trie.put("Anna", 1);
        trie.put("Anael", 2);
        trie.put("Analu", 3);
        trie.put("Andreas", 4);
        trie.put("Andrea", 5);
        trie.put("Andres", 6);
        trie.put("Anatole", 7);
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        // removed other assertion
        // removed other assertion

        prefixMap.clear();
        // removed other assertion
        // removed other assertion
        assertTrue(prefixMap.values().isEmpty());
    }

    public void testPrefixMapClear_6_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        trie.put("Anna", 1);
        trie.put("Anael", 2);
        trie.put("Analu", 3);
        trie.put("Andreas", 4);
        trie.put("Andrea", 5);
        trie.put("Andres", 6);
        trie.put("Anatole", 7);
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        // removed other assertion
        // removed other assertion

        prefixMap.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new HashSet<>(Arrays.asList("Anael", "Analu", "Anatole", "Anna")), trie.keySet());
    }

    public void testPrefixMapClear_7_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        trie.put("Anna", 1);
        trie.put("Anael", 2);
        trie.put("Analu", 3);
        trie.put("Andreas", 4);
        trie.put("Andrea", 5);
        trie.put("Andres", 6);
        trie.put("Anatole", 7);
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        // removed other assertion
        // removed other assertion

        prefixMap.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Arrays.asList(2, 3, 7, 1), new ArrayList<>(trie.values()));
    }

    public void testPrefixMapClearNothing_1_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        assertEquals(new HashSet<String>(), prefixMap.keySet());
    }

    public void testPrefixMapClearNothing_2_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        // removed other assertion
        assertEquals(new ArrayList<Integer>(0), new ArrayList<>(prefixMap.values()));
    }

    public void testPrefixMapClearNothing_3_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        // removed other assertion
        // removed other assertion

        prefixMap.clear();
        assertTrue(prefixMap.isEmpty());
    }

    public void testPrefixMapClearNothing_4_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        // removed other assertion
        // removed other assertion

        prefixMap.clear();
        // removed other assertion
        assertTrue(prefixMap.keySet().isEmpty());
    }

    public void testPrefixMapClearNothing_5_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        // removed other assertion
        // removed other assertion

        prefixMap.clear();
        // removed other assertion
        // removed other assertion
        assertTrue(prefixMap.values().isEmpty());
    }

    public void testPrefixMapClearNothing_6_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        // removed other assertion
        // removed other assertion

        prefixMap.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new HashSet<String>(), trie.keySet());
    }

    public void testPrefixMapClearNothing_7_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        // removed other assertion
        // removed other assertion

        prefixMap.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new ArrayList<Integer>(0), new ArrayList<>(trie.values()));
    }

    public void testPrefixMapClearUsingRemove_1_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        trie.put("Anna", 1);
        trie.put("Anael", 2);
        trie.put("Analu", 3);
        trie.put("Andreas", 4);
        trie.put("Andrea", 5);
        trie.put("Andres", 6);
        trie.put("Anatole", 7);
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        assertEquals(new HashSet<>(Arrays.asList("Andrea", "Andreas", "Andres")), prefixMap.keySet());
    }

    public void testPrefixMapClearUsingRemove_2_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        trie.put("Anna", 1);
        trie.put("Anael", 2);
        trie.put("Analu", 3);
        trie.put("Andreas", 4);
        trie.put("Andrea", 5);
        trie.put("Andres", 6);
        trie.put("Anatole", 7);
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        // removed other assertion
        assertEquals(Arrays.asList(5, 4, 6), new ArrayList<>(prefixMap.values()));
    }

    public void testPrefixMapClearUsingRemove_3_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        trie.put("Anna", 1);
        trie.put("Anael", 2);
        trie.put("Analu", 3);
        trie.put("Andreas", 4);
        trie.put("Andrea", 5);
        trie.put("Andres", 6);
        trie.put("Anatole", 7);
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        // removed other assertion
        // removed other assertion

        final Set<String> keys = new HashSet<>(prefixMap.keySet());
        for (final String key : keys) {
            prefixMap.remove(key);
        }
        assertTrue(prefixMap.keySet().isEmpty());
    }

    public void testPrefixMapClearUsingRemove_4_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        trie.put("Anna", 1);
        trie.put("Anael", 2);
        trie.put("Analu", 3);
        trie.put("Andreas", 4);
        trie.put("Andrea", 5);
        trie.put("Andres", 6);
        trie.put("Anatole", 7);
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        // removed other assertion
        // removed other assertion

        final Set<String> keys = new HashSet<>(prefixMap.keySet());
        for (final String key : keys) {
            prefixMap.remove(key);
        }
        // removed other assertion
        assertTrue(prefixMap.values().isEmpty());
    }

    public void testPrefixMapClearUsingRemove_5_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        trie.put("Anna", 1);
        trie.put("Anael", 2);
        trie.put("Analu", 3);
        trie.put("Andreas", 4);
        trie.put("Andrea", 5);
        trie.put("Andres", 6);
        trie.put("Anatole", 7);
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        // removed other assertion
        // removed other assertion

        final Set<String> keys = new HashSet<>(prefixMap.keySet());
        for (final String key : keys) {
            prefixMap.remove(key);
        }
        // removed other assertion
        // removed other assertion
        assertEquals(new HashSet<>(Arrays.asList("Anael", "Analu", "Anatole", "Anna")), trie.keySet());
    }

    public void testPrefixMapClearUsingRemove_6_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        trie.put("Anna", 1);
        trie.put("Anael", 2);
        trie.put("Analu", 3);
        trie.put("Andreas", 4);
        trie.put("Andrea", 5);
        trie.put("Andres", 6);
        trie.put("Anatole", 7);
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        // removed other assertion
        // removed other assertion

        final Set<String> keys = new HashSet<>(prefixMap.keySet());
        for (final String key : keys) {
            prefixMap.remove(key);
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Arrays.asList(2, 3, 7, 1), new ArrayList<>(trie.values()));
    }

}
