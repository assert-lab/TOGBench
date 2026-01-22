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
package org.apache.commons.collections4.multimap;

import java.util.Iterator;
import java.util.Set;

import junit.framework.Test;

import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.SetValuedMap;

/**
 * Test HashSetValuedHashMap
 *
 * @since 4.1
 */
public class HashSetValuedHashMapTest_OE25Dev<K, V> extends AbstractMultiValuedMapTest<K, V> {

    public HashSetValuedHashMapTest_OE25Dev(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(HashSetValuedHashMapTest_OE25Dev.class);
    }

    // -----------------------------------------------------------------------
    @Override
    public SetValuedMap<K, V> makeObject() {
        return new HashSetValuedHashMap<>();
    }

    @Override
    public MultiValuedMap<K, V> makeConfirmedMap() {
        return new HashSetValuedHashMap<>();
    }

    // -----------------------------------------------------------------------

//    public void testCreate() throws Exception {
//        writeExternalFormToDisk((java.io.Serializable) makeObject(),
//                "src/test/resources/data/test/HashSetValuedHashMap.emptyCollection.version4.1.obj");
//        writeExternalFormToDisk((java.io.Serializable) makeFullMap(),
//                "src/test/resources/data/test/HashSetValuedHashMap.fullCollection.version4.1.obj");
//    }

    public void testSetValuedMapAdd_1_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        assertTrue(setMap.get((K) "whatever") instanceof Set);
    }

    public void testSetValuedMapAdd_2_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        // removed other assertion

        final Set<V> set = setMap.get((K) "A");
        assertTrue(set.add((V) "a1"));
    }

    public void testSetValuedMapAdd_3_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        // removed other assertion

        final Set<V> set = setMap.get((K) "A");
        // removed other assertion
        assertTrue(set.add((V) "a2"));
    }

    public void testSetValuedMapAdd_4_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        // removed other assertion

        final Set<V> set = setMap.get((K) "A");
        // removed other assertion
        // removed other assertion
        assertFalse(set.add((V) "a1"));
    }

    public void testSetValuedMapAdd_5_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        // removed other assertion

        final Set<V> set = setMap.get((K) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, setMap.size());
    }

    public void testSetValuedMapAdd_6_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        // removed other assertion

        final Set<V> set = setMap.get((K) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(setMap.containsKey("A"));
    }

    public void testSetValuedMapRemove_1_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        assertTrue(setMap.get((K) "whatever") instanceof Set);
    }

    public void testSetValuedMapRemove_2_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        // removed other assertion

        final Set<V> set = setMap.get((K) "A");
        assertTrue(set.add((V) "a1"));
    }

    public void testSetValuedMapRemove_3_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        // removed other assertion

        final Set<V> set = setMap.get((K) "A");
        // removed other assertion
        assertTrue(set.add((V) "a2"));
    }

    public void testSetValuedMapRemove_4_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        // removed other assertion

        final Set<V> set = setMap.get((K) "A");
        // removed other assertion
        // removed other assertion
        assertFalse(set.add((V) "a1"));
    }

    public void testSetValuedMapRemove_5_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        // removed other assertion

        final Set<V> set = setMap.get((K) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, setMap.size());
    }

    public void testSetValuedMapRemove_6_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        // removed other assertion

        final Set<V> set = setMap.get((K) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(setMap.containsKey("A"));
    }

    public void testSetValuedMapRemove_7_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        // removed other assertion

        final Set<V> set = setMap.get((K) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(set.remove("a1"));
    }

    public void testSetValuedMapRemove_8_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        // removed other assertion

        final Set<V> set = setMap.get((K) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(set.remove("a2"));
    }

    public void testSetValuedMapRemove_9_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        // removed other assertion

        final Set<V> set = setMap.get((K) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(set.remove("a1"));
    }

    public void testSetValuedMapRemove_10_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        // removed other assertion

        final Set<V> set = setMap.get((K) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(0, setMap.size());
    }

    public void testSetValuedMapRemove_11_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        // removed other assertion

        final Set<V> set = setMap.get((K) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(setMap.containsKey("A"));
    }

    public void testSetValuedMapRemoveViaIterator_1_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        assertTrue(setMap.get((K) "whatever") instanceof Set);
    }

    public void testSetValuedMapRemoveViaIterator_2_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        // removed other assertion

        final Set<V> set = setMap.get((K) "A");
        set.add((V) "a1");
        set.add((V) "a2");
        set.add((V) "a1");

        final Iterator<V> it = set.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        assertEquals(0, setMap.size());
    }

    public void testSetValuedMapRemoveViaIterator_3_oe() {
        final SetValuedMap<K, V> setMap = makeObject();
        // removed other assertion

        final Set<V> set = setMap.get((K) "A");
        set.add((V) "a1");
        set.add((V) "a2");
        set.add((V) "a1");

        final Iterator<V> it = set.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        // removed other assertion
        assertFalse(setMap.containsKey("A"));
    }

    public void testSetValuedMapEqualsHashCodeContract_1_oe() {
        final SetValuedMap map1 = makeObject();
        final SetValuedMap map2 = makeObject();

        map1.put("a", "a1");
        map1.put("a", "a2");
        map2.put("a", "a2");
        map2.put("a", "a1");
        assertEquals(map1, map2);
    }

    public void testSetValuedMapEqualsHashCodeContract_2_oe() {
        final SetValuedMap map1 = makeObject();
        final SetValuedMap map2 = makeObject();

        map1.put("a", "a1");
        map1.put("a", "a2");
        map2.put("a", "a2");
        map2.put("a", "a1");
        // removed other assertion
        assertEquals(map1.hashCode(), map2.hashCode());
    }

    public void testSetValuedMapEqualsHashCodeContract_3_oe() {
        final SetValuedMap map1 = makeObject();
        final SetValuedMap map2 = makeObject();

        map1.put("a", "a1");
        map1.put("a", "a2");
        map2.put("a", "a2");
        map2.put("a", "a1");
        // removed other assertion
        // removed other assertion

        map2.put("a", "a2");
        assertEquals(map1, map2);
    }

    public void testSetValuedMapEqualsHashCodeContract_4_oe() {
        final SetValuedMap map1 = makeObject();
        final SetValuedMap map2 = makeObject();

        map1.put("a", "a1");
        map1.put("a", "a2");
        map2.put("a", "a2");
        map2.put("a", "a1");
        // removed other assertion
        // removed other assertion

        map2.put("a", "a2");
        // removed other assertion
        assertEquals(map1.hashCode(), map2.hashCode());
    }

    public void testSetValuedMapEqualsHashCodeContract_5_oe() {
        final SetValuedMap map1 = makeObject();
        final SetValuedMap map2 = makeObject();

        map1.put("a", "a1");
        map1.put("a", "a2");
        map2.put("a", "a2");
        map2.put("a", "a1");
        // removed other assertion
        // removed other assertion

        map2.put("a", "a2");
        // removed other assertion
        // removed other assertion

        map2.put("a", "a3");
        assertNotSame(map1, map2);
    }

    public void testSetValuedMapEqualsHashCodeContract_6_oe() {
        final SetValuedMap map1 = makeObject();
        final SetValuedMap map2 = makeObject();

        map1.put("a", "a1");
        map1.put("a", "a2");
        map2.put("a", "a2");
        map2.put("a", "a1");
        // removed other assertion
        // removed other assertion

        map2.put("a", "a2");
        // removed other assertion
        // removed other assertion

        map2.put("a", "a3");
        // removed other assertion
        assertNotSame(map1.hashCode(), map2.hashCode());
    }

}
