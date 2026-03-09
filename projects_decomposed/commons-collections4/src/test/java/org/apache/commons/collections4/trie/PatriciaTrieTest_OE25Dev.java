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
        return new junit.framework.TestSuite(PatriciaTrieTest_OE25Dev.class);
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

    public void testPrefixMap() {
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
        Assert.assertEquals("Alabama", map.firstKey());
        Assert.assertEquals("Alliese", map.lastKey());
        Assert.assertEquals("Albertoo", map.get("Albertoo"));
        Assert.assertNotNull(trie.get("Xavier"));
        Assert.assertNull(map.get("Xavier"));
        Assert.assertNull(trie.get("Alice"));
        Assert.assertNull(map.get("Alice"));
        iterator = map.values().iterator();
        Assert.assertEquals("Alabama", iterator.next());
        Assert.assertEquals("Albert", iterator.next());
        Assert.assertEquals("Alberto", iterator.next());
        Assert.assertEquals("Albertoo", iterator.next());
        Assert.assertEquals("Alberts", iterator.next());
        Assert.assertEquals("Alien", iterator.next());
        Assert.assertEquals("Allie", iterator.next());
        Assert.assertEquals("Alliese", iterator.next());
        Assert.assertFalse(iterator.hasNext());

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        Assert.assertEquals("Albert", iterator.next());
        Assert.assertEquals("Alberto", iterator.next());
        Assert.assertEquals("Albertoo", iterator.next());
        Assert.assertEquals("Alberts", iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(4, map.size());
        Assert.assertEquals("Albert", map.firstKey());
        Assert.assertEquals("Alberts", map.lastKey());
        Assert.assertNull(trie.get("Albertz"));
        map.put("Albertz", "Albertz");
        Assert.assertEquals("Albertz", trie.get("Albertz"));
        Assert.assertEquals(5, map.size());
        Assert.assertEquals("Albertz", map.lastKey());
        iterator = map.keySet().iterator();
        Assert.assertEquals("Albert", iterator.next());
        Assert.assertEquals("Alberto", iterator.next());
        Assert.assertEquals("Albertoo", iterator.next());
        Assert.assertEquals("Alberts", iterator.next());
        Assert.assertEquals("Albertz", iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals("Albertz", map.remove("Albertz"));

        map = trie.prefixMap("Alberto");
        Assert.assertEquals(2, map.size());
        Assert.assertEquals("Alberto", map.firstKey());
        Assert.assertEquals("Albertoo", map.lastKey());
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        Assert.assertEquals("Alberto", entry.getKey());
        Assert.assertEquals("Alberto", entry.getValue());
        entry = entryIterator.next();
        Assert.assertEquals("Albertoo", entry.getKey());
        Assert.assertEquals("Albertoo", entry.getValue());
        Assert.assertFalse(entryIterator.hasNext());
        trie.put("Albertoad", "Albertoad");
        Assert.assertEquals(3, map.size());
        Assert.assertEquals("Alberto", map.firstKey());
        Assert.assertEquals("Albertoo", map.lastKey());
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        Assert.assertEquals("Alberto", entry.getKey());
        Assert.assertEquals("Alberto", entry.getValue());
        entry = entryIterator.next();
        Assert.assertEquals("Albertoad", entry.getKey());
        Assert.assertEquals("Albertoad", entry.getValue());
        entry = entryIterator.next();
        Assert.assertEquals("Albertoo", entry.getKey());
        Assert.assertEquals("Albertoo", entry.getValue());
        Assert.assertFalse(entryIterator.hasNext());
        Assert.assertEquals("Albertoo", trie.remove("Albertoo"));
        Assert.assertEquals("Alberto", map.firstKey());
        Assert.assertEquals("Albertoad", map.lastKey());
        Assert.assertEquals(2, map.size());
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        Assert.assertEquals("Alberto", entry.getKey());
        Assert.assertEquals("Alberto", entry.getValue());
        entry = entryIterator.next();
        Assert.assertEquals("Albertoad", entry.getKey());
        Assert.assertEquals("Albertoad", entry.getValue());
        Assert.assertFalse(entryIterator.hasNext());
        Assert.assertEquals("Albertoad", trie.remove("Albertoad"));
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        Assert.assertEquals(2, map.size());
        Assert.assertFalse(map.containsKey("Albert"));
        Assert.assertTrue(map.containsKey("Xavier"));
        Assert.assertFalse(map.containsKey("Xalan"));
        iterator = map.values().iterator();
        Assert.assertEquals("Xavier", iterator.next());
        Assert.assertEquals("XyZ", iterator.next());
        Assert.assertFalse(iterator.hasNext());

        map = trie.prefixMap("An");
        Assert.assertEquals(1, map.size());
        Assert.assertEquals("Anna", map.firstKey());
        Assert.assertEquals("Anna", map.lastKey());
        iterator = map.keySet().iterator();
        Assert.assertEquals("Anna", iterator.next());
        Assert.assertFalse(iterator.hasNext());

        map = trie.prefixMap("Ban");
        Assert.assertEquals(1, map.size());
        Assert.assertEquals("Banane", map.firstKey());
        Assert.assertEquals("Banane", map.lastKey());
        iterator = map.keySet().iterator();
        Assert.assertEquals("Banane", iterator.next());
        Assert.assertFalse(iterator.hasNext());

        map = trie.prefixMap("Am");
        Assert.assertFalse(map.isEmpty());
        Assert.assertEquals(3, map.size());
        Assert.assertEquals("Amber", trie.remove("Amber"));
        iterator = map.keySet().iterator();
        Assert.assertEquals("Amma", iterator.next());
        Assert.assertEquals("Ammun", iterator.next());
        Assert.assertFalse(iterator.hasNext());
        iterator = map.keySet().iterator();
        map.put("Amber", "Amber");
        Assert.assertEquals(3, map.size());
        try {
            iterator.next();
            Assert.fail("CME expected");
        } catch(final ConcurrentModificationException expected) {}
        Assert.assertEquals("Amber", map.firstKey());
        Assert.assertEquals("Ammun", map.lastKey());

        map = trie.prefixMap("Ak\0");
        Assert.assertTrue(map.isEmpty());

        map = trie.prefixMap("Ak");
        Assert.assertEquals(2, map.size());
        Assert.assertEquals("Akka", map.firstKey());
        Assert.assertEquals("Akko", map.lastKey());
        map.put("Ak", "Ak");
        Assert.assertEquals("Ak", map.firstKey());
        Assert.assertEquals("Akko", map.lastKey());
        Assert.assertEquals(3, map.size());
        trie.put("Al", "Al");
        Assert.assertEquals(3, map.size());
        Assert.assertEquals("Ak", map.remove("Ak"));
        Assert.assertEquals("Akka", map.firstKey());
        Assert.assertEquals("Akko", map.lastKey());
        Assert.assertEquals(2, map.size());
        iterator = map.keySet().iterator();
        Assert.assertEquals("Akka", iterator.next());
        Assert.assertEquals("Akko", iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals("Al", trie.remove("Al"));

        map = trie.prefixMap("Akka");
        Assert.assertEquals(1, map.size());
        Assert.assertEquals("Akka", map.firstKey());
        Assert.assertEquals("Akka", map.lastKey());
        iterator = map.keySet().iterator();
        Assert.assertEquals("Akka", iterator.next());
        Assert.assertFalse(iterator.hasNext());

        map = trie.prefixMap("Ab");
        Assert.assertTrue(map.isEmpty());
        Assert.assertEquals(0, map.size());
        try {
            final Object o = map.firstKey();
            Assert.fail("got a first key: " + o);
        } catch(final NoSuchElementException nsee) {}
        try {
            final Object o = map.lastKey();
            Assert.fail("got a last key: " + o);
        } catch(final NoSuchElementException nsee) {}
        iterator = map.values().iterator();
        Assert.assertFalse(iterator.hasNext());

        map = trie.prefixMap("Albertooo");
        Assert.assertTrue(map.isEmpty());
        Assert.assertEquals(0, map.size());
        try {
            final Object o = map.firstKey();
            Assert.fail("got a first key: " + o);
        } catch(final NoSuchElementException nsee) {}
        try {
            final Object o = map.lastKey();
            Assert.fail("got a last key: " + o);
        } catch(final NoSuchElementException nsee) {}
        iterator = map.values().iterator();
        Assert.assertFalse(iterator.hasNext());

        map = trie.prefixMap("");
        Assert.assertSame(trie,map);// stricter than necessary,but a good check map = trie.prefixMap("\0");
        Assert.assertTrue(map.isEmpty());
        Assert.assertEquals(0, map.size());
        try {
            final Object o = map.firstKey();
            Assert.fail("got a first key: " + o);
        } catch(final NoSuchElementException nsee) {}
        try {
            final Object o = map.lastKey();
            Assert.fail("got a last key: " + o);
        } catch(final NoSuchElementException nsee) {}
        iterator = map.values().iterator();
        Assert.assertFalse(iterator.hasNext());
    }

    public void testPrefixMapRemoval() {
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
        Iterator<String> iter = map.keySet().iterator();
        Assert.assertEquals("Alabama", iter.next());
        Assert.assertEquals("Albert", iter.next());
        Assert.assertEquals("Alberto", iter.next());
        Assert.assertEquals("Albertoo", iter.next());
        Assert.assertEquals("Alberts", iter.next());
        Assert.assertEquals("Alien", iter.next());
        iter.remove();
        Assert.assertEquals(7, map.size());
        Assert.assertEquals("Allie", iter.next());
        Assert.assertEquals("Alliese", iter.next());
        Assert.assertFalse(iter.hasNext());

        map = trie.prefixMap("Ak");
        Assert.assertEquals(2, map.size());
        iter = map.keySet().iterator();
        Assert.assertEquals("Akka", iter.next());
        iter.remove();
        Assert.assertEquals(1, map.size());
        Assert.assertEquals("Akko", iter.next());
        if(iter.hasNext()) {
            Assert.fail("shouldn't have next (but was: " + iter.next() + ")");
        }
        Assert.assertFalse(iter.hasNext());
    }

    public void testPrefixMapSizes() {
        // COLLECTIONS-525
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");
        assertTrue(aTree.prefixMap("点").containsKey("点评"));
        assertEquals("测试", aTree.prefixMap("点").get("点评"));
        assertFalse(aTree.prefixMap("点").isEmpty());
        assertEquals(1, aTree.prefixMap("点").size());
        assertEquals(1, aTree.prefixMap("点").keySet().size());
        assertEquals(1, aTree.prefixMap("点").entrySet().size());
        assertEquals(1, aTree.prefixMap("点评").values().size());

        aTree.clear();
        aTree.put("点评", "联盟");
        aTree.put("点版", "定向");
        assertEquals(2, aTree.prefixMap("点").keySet().size());
        assertEquals(2, aTree.prefixMap("点").values().size());
    }

    public void testPrefixMapSizes2() {
        final char u8000 = Character.toChars(32768)[0]; // U+8000 (1000000000000000)
        final char char_b = 'b'; // 1100010

        final PatriciaTrie<String> trie = new PatriciaTrie<>();
        final String prefixString = "" + char_b;
        final String longerString = prefixString + u8000;

        assertEquals(1, prefixString.length());
        assertEquals(2, longerString.length());

        assertTrue(longerString.startsWith(prefixString));

        trie.put(prefixString, "prefixString");
        trie.put(longerString, "longerString");

        assertEquals(2, trie.prefixMap(prefixString).size());
        assertTrue(trie.prefixMap(prefixString).containsKey(longerString));
    }

    public void testPrefixMapClear() {
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
        assertEquals(Arrays.asList(5, 4, 6), new ArrayList<>(prefixMap.values()));

        prefixMap.clear();
        assertTrue(prefixMap.isEmpty());
        assertTrue(prefixMap.keySet().isEmpty());
        assertTrue(prefixMap.values().isEmpty());
        assertEquals(new HashSet<>(Arrays.asList("Anael", "Analu", "Anatole", "Anna")), trie.keySet());
        assertEquals(Arrays.asList(2, 3, 7, 1), new ArrayList<>(trie.values()));
    }

    public void testPrefixMapClearNothing() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");
        assertEquals(new HashSet<String>(), prefixMap.keySet());
        assertEquals(new ArrayList<Integer>(0), new ArrayList<>(prefixMap.values()));

        prefixMap.clear();
        assertTrue(prefixMap.isEmpty());
        assertTrue(prefixMap.keySet().isEmpty());
        assertTrue(prefixMap.values().isEmpty());
        assertEquals(new HashSet<String>(), trie.keySet());
        assertEquals(new ArrayList<Integer>(0), new ArrayList<>(trie.values()));
    }

    public void testPrefixMapClearUsingRemove() {
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
        assertEquals(Arrays.asList(5, 4, 6), new ArrayList<>(prefixMap.values()));

        final Set<String> keys = new HashSet<>(prefixMap.keySet());
        for (final String key : keys) {
            prefixMap.remove(key);
        }
        assertTrue(prefixMap.keySet().isEmpty());
        assertTrue(prefixMap.values().isEmpty());
        assertEquals(new HashSet<>(Arrays.asList("Anael", "Analu", "Anatole", "Anna")), trie.keySet());
        assertEquals(Arrays.asList(2, 3, 7, 1), new ArrayList<>(trie.values()));
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
        iterator = map.values().iterator();
        Assert.assertEquals("Alabama", iterator.next());
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        Assert.assertEquals("Albert", iterator.next());
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();
        Assert.assertEquals("Albert", iterator.next());
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        Assert.assertEquals("Alberto", map.firstKey());
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        Assert.assertEquals("Albertoad", entry.getValue());
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        iterator = map.values().iterator();
        Assert.assertEquals("Xavier", iterator.next());
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        iterator = map.values().iterator();

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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        iterator = map.values().iterator();

        map = trie.prefixMap("An");
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        iterator = map.values().iterator();

        map = trie.prefixMap("An");
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        iterator = map.values().iterator();

        map = trie.prefixMap("An");
        iterator = map.keySet().iterator();
        Assert.assertEquals("Anna", iterator.next());
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        iterator = map.values().iterator();

        map = trie.prefixMap("An");
        iterator = map.keySet().iterator();

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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        iterator = map.values().iterator();

        map = trie.prefixMap("An");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Ban");
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        iterator = map.values().iterator();

        map = trie.prefixMap("An");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Ban");
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        iterator = map.values().iterator();

        map = trie.prefixMap("An");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Ban");
        iterator = map.keySet().iterator();
        Assert.assertEquals("Banane", iterator.next());
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        iterator = map.values().iterator();

        map = trie.prefixMap("An");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Ban");
        iterator = map.keySet().iterator();

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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        iterator = map.values().iterator();

        map = trie.prefixMap("An");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Ban");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Am");
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        iterator = map.values().iterator();

        map = trie.prefixMap("An");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Ban");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Am");
        Assert.assertEquals("Amber", trie.remove("Amber"));
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
        iterator = map.values().iterator();

        map = trie.prefixMap("Albert");
        iterator = map.keySet().iterator();
        map.put("Albertz", "Albertz");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Alberto");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoad", "Albertoad");
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entry = entryIterator.next();
        entryIterator = map.entrySet().iterator();
        entry = entryIterator.next();
        entry = entryIterator.next();
        trie.put("Albertoo", "Albertoo");

        map = trie.prefixMap("X");
        iterator = map.values().iterator();

        map = trie.prefixMap("An");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Ban");
        iterator = map.keySet().iterator();

        map = trie.prefixMap("Am");
        iterator = map.keySet().iterator();
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
        Iterator<String> iter = map.keySet().iterator();
        Assert.assertEquals("Alabama", iter.next());
    }

    public void testPrefixMapSizes_1_oe() {
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");
        assertTrue(aTree.prefixMap("点").containsKey("点评"));
    }

    public void testPrefixMapSizes_2_oe() {
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");
        assertEquals("测试", aTree.prefixMap("点").get("点评"));
    }

    public void testPrefixMapSizes_3_oe() {
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");
        assertFalse(aTree.prefixMap("点").isEmpty());
    }

    public void testPrefixMapSizes_4_oe() {
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");
        assertEquals(1, aTree.prefixMap("点").size());
    }

    public void testPrefixMapSizes_5_oe() {
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");
        assertEquals(1, aTree.prefixMap("点").keySet().size());
    }

    public void testPrefixMapSizes_6_oe() {
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");
        assertEquals(1, aTree.prefixMap("点").entrySet().size());
    }

    public void testPrefixMapSizes_7_oe() {
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");
        assertEquals(1, aTree.prefixMap("点评").values().size());
    }

    public void testPrefixMapSizes_8_oe() {
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");

        aTree.clear();
        aTree.put("点评", "联盟");
        aTree.put("点版", "定向");
        assertEquals(2, aTree.prefixMap("点").keySet().size());
    }

    public void testPrefixMapSizes_9_oe() {
        final PatriciaTrie<String> aTree = new PatriciaTrie<>();
        aTree.put("点评", "测试");
        aTree.put("书评", "测试");

        aTree.clear();
        aTree.put("点评", "联盟");
        aTree.put("点版", "定向");
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

        assertEquals(2, longerString.length());
    }

    public void testPrefixMapSizes2_3_oe() {
        final char u8000 = Character.toChars(32768)[0]; // U+8000 (1000000000000000)
        final char char_b = 'b'; // 1100010

        final PatriciaTrie<String> trie = new PatriciaTrie<>();
        final String prefixString = "" + char_b;
        final String longerString = prefixString + u8000;


        assertTrue(longerString.startsWith(prefixString));
    }

    public void testPrefixMapSizes2_4_oe() {
        final char u8000 = Character.toChars(32768)[0]; // U+8000 (1000000000000000)
        final char char_b = 'b'; // 1100010

        final PatriciaTrie<String> trie = new PatriciaTrie<>();
        final String prefixString = "" + char_b;
        final String longerString = prefixString + u8000;



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



        trie.put(prefixString, "prefixString");
        trie.put(longerString, "longerString");

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

        prefixMap.clear();
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

        prefixMap.clear();
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

        prefixMap.clear();
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

        prefixMap.clear();
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
        assertEquals(new ArrayList<Integer>(0), new ArrayList<>(prefixMap.values()));
    }

    public void testPrefixMapClearNothing_3_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");

        prefixMap.clear();
        assertTrue(prefixMap.isEmpty());
    }

    public void testPrefixMapClearNothing_4_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");

        prefixMap.clear();
        assertTrue(prefixMap.keySet().isEmpty());
    }

    public void testPrefixMapClearNothing_5_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");

        prefixMap.clear();
        assertTrue(prefixMap.values().isEmpty());
    }

    public void testPrefixMapClearNothing_6_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");

        prefixMap.clear();
        assertEquals(new HashSet<String>(), trie.keySet());
    }

    public void testPrefixMapClearNothing_7_oe() {
        final Trie<String, Integer> trie = new PatriciaTrie<>();
        final SortedMap<String, Integer> prefixMap = trie.prefixMap("And");

        prefixMap.clear();
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

        final Set<String> keys = new HashSet<>(prefixMap.keySet());
        for (final String key : keys) {
            prefixMap.remove(key);
        }
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

        final Set<String> keys = new HashSet<>(prefixMap.keySet());
        for (final String key : keys) {
            prefixMap.remove(key);
        }
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

        final Set<String> keys = new HashSet<>(prefixMap.keySet());
        for (final String key : keys) {
            prefixMap.remove(key);
        }
        assertEquals(Arrays.asList(2, 3, 7, 1), new ArrayList<>(trie.values()));
    }

}
