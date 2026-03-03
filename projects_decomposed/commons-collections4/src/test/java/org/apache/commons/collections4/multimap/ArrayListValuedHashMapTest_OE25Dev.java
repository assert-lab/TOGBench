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

import java.util.List;
import java.util.ListIterator;

import junit.framework.Test;

import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.MultiValuedMap;

/**
 * Test ArrayListValuedHashMap
 *
 * @since 4.1
 */
public class ArrayListValuedHashMapTest_OE25Dev<K, V> extends AbstractMultiValuedMapTest<K, V> {

    public ArrayListValuedHashMapTest_OE25Dev(final String testName) {
        super(testName);
    }

public static Test suite() {
    return new junit.framework.TestSuite(ArrayListValuedHashMapTest_OE25Dev.class);
}

    // -----------------------------------------------------------------------
    @Override
    public ListValuedMap<K, V> makeObject() {
        return new ArrayListValuedHashMap<>();
    }

    // -----------------------------------------------------------------------

//    public void testCreate() throws Exception {
//        writeExternalFormToDisk((java.io.Serializable) makeObject(),
//                "src/test/resources/data/test/ArrayListValuedHashMap.emptyCollection.version4.1.obj");
//        writeExternalFormToDisk((java.io.Serializable) makeFullMap(),
//                "src/test/resources/data/test/ArrayListValuedHashMap.fullCollection.version4.1.obj");
//    }

    public void testListValuedMapAdd_1_oe() {
        final ListValuedMap<K, V> listMap = makeObject();
        assertTrue(listMap.get((K) "whatever") instanceof List);
    }

    public void testListValuedMapAdd_2_oe() {
        final ListValuedMap<K, V> listMap = makeObject();
        final List<V> list = listMap.get((K) "A");
        list.add((V) "a1");
        assertEquals(1, listMap.size());
    }

    public void testListValuedMapAdd_3_oe() {
        final ListValuedMap<K, V> listMap = makeObject();
        final List<V> list = listMap.get((K) "A");
        list.add((V) "a1");
        assertTrue(listMap.containsKey("A"));
    }

    public void testListValuedMapAddViaListIterator_1_oe() {
        final ListValuedMap<K, V> listMap = makeObject();
        final ListIterator<V> listIt = listMap.get((K) "B").listIterator();
        assertFalse(listIt.hasNext());
    }

    public void testListValuedMapAddViaListIterator_2_oe() {
        final ListValuedMap<K, V> listMap = makeObject();
        final ListIterator<V> listIt = listMap.get((K) "B").listIterator();
        listIt.add((V) "b1");
        listIt.add((V) "b2");
        listIt.add((V) "b3");
        assertEquals(3, listMap.size());
    }

    public void testListValuedMapAddViaListIterator_3_oe() {
        final ListValuedMap<K, V> listMap = makeObject();
        final ListIterator<V> listIt = listMap.get((K) "B").listIterator();
        listIt.add((V) "b1");
        listIt.add((V) "b2");
        listIt.add((V) "b3");
        assertTrue(listMap.containsKey("B"));
    }

    public void testListValuedMapAddViaListIterator_4_oe() {
        final ListValuedMap<K, V> listMap = makeObject();
        final ListIterator<V> listIt = listMap.get((K) "B").listIterator();
        listIt.add((V) "b1");
        listIt.add((V) "b2");
        listIt.add((V) "b3");
        assertFalse(listIt.hasNext());
    }

    public void testListValuedMapRemove_1_oe() {
        final ListValuedMap<K, V> listMap = makeObject();
        final List<V> list = listMap.get((K) "A");
        list.add((V) "a1");
        list.add((V) "a2");
        list.add((V) "a3");
        assertEquals(3, listMap.size());
    }

    public void testListValuedMapRemove_2_oe() {
        final ListValuedMap<K, V> listMap = makeObject();
        final List<V> list = listMap.get((K) "A");
        list.add((V) "a1");
        list.add((V) "a2");
        list.add((V) "a3");
        assertEquals("a1", list.remove(0));
    }

    public void testListValuedMapRemoveViaListIterator_1_oe() {
        final ListValuedMap<K, V> listMap = makeObject();
        ListIterator<V> listIt = listMap.get((K) "B").listIterator();
        listIt.add((V) "b1");
        listIt.add((V) "b2");
        assertEquals(2, listMap.size());
    }

    public void testListValuedMapRemoveViaListIterator_2_oe() {
        final ListValuedMap<K, V> listMap = makeObject();
        ListIterator<V> listIt = listMap.get((K) "B").listIterator();
        listIt.add((V) "b1");
        listIt.add((V) "b2");
        assertTrue(listMap.containsKey("B"));
    }

    public void testListValuedMapRemoveViaListIterator_3_oe() {
        final ListValuedMap<K, V> listMap = makeObject();
        ListIterator<V> listIt = listMap.get((K) "B").listIterator();
        listIt.add((V) "b1");
        listIt.add((V) "b2");
        listIt = listMap.get((K) "B").listIterator();
        while (listIt.hasNext()) {
            listIt.next();
            listIt.remove();
        }
        assertFalse(listMap.containsKey("B"));
    }

    public void testListValuedMapRemoveViaListIterator_4_oe() {
        final ListValuedMap<K, V> listMap = makeObject();
        ListIterator<V> listIt = listMap.get((K) "B").listIterator();
        listIt.add((V) "b1");
        listIt.add((V) "b2");
        listIt = listMap.get((K) "B").listIterator();
        while (listIt.hasNext()) {
            listIt.next();
            listIt.remove();
        }
        listIt.add((V) "b1");
        listIt.add((V) "b2");
        assertTrue(listMap.containsKey("B"));
    }

    public void testListValuedMapRemoveViaListIterator_5_oe() {
        final ListValuedMap<K, V> listMap = makeObject();
        ListIterator<V> listIt = listMap.get((K) "B").listIterator();
        listIt.add((V) "b1");
        listIt.add((V) "b2");
        listIt = listMap.get((K) "B").listIterator();
        while (listIt.hasNext()) {
            listIt.next();
            listIt.remove();
        }
        listIt.add((V) "b1");
        listIt.add((V) "b2");
        assertEquals(2, listMap.get((K) "B").size());
    }

    public void testEqualsHashCodeContract_1_oe() {
        final MultiValuedMap map1 = makeObject();
        final MultiValuedMap map2 = makeObject();

        map1.put("a", "a1");
        map1.put("a", "a2");
        map2.put("a", "a1");
        map2.put("a", "a2");
        assertEquals(map1, map2);
    }

    public void testEqualsHashCodeContract_2_oe() {
        final MultiValuedMap map1 = makeObject();
        final MultiValuedMap map2 = makeObject();

        map1.put("a", "a1");
        map1.put("a", "a2");
        map2.put("a", "a1");
        map2.put("a", "a2");
        assertEquals(map1.hashCode(), map2.hashCode());
    }

    public void testEqualsHashCodeContract_3_oe() {
        final MultiValuedMap map1 = makeObject();
        final MultiValuedMap map2 = makeObject();

        map1.put("a", "a1");
        map1.put("a", "a2");
        map2.put("a", "a1");
        map2.put("a", "a2");

        map2.put("a", "a2");
        assertNotSame(map1, map2);
    }

    public void testEqualsHashCodeContract_4_oe() {
        final MultiValuedMap map1 = makeObject();
        final MultiValuedMap map2 = makeObject();

        map1.put("a", "a1");
        map1.put("a", "a2");
        map2.put("a", "a1");
        map2.put("a", "a2");

        map2.put("a", "a2");
        assertNotSame(map1.hashCode(), map2.hashCode());
    }

    public void testListValuedMapEqualsHashCodeContract_1_oe() {
        final ListValuedMap map1 = makeObject();
        final ListValuedMap map2 = makeObject();

        map1.put("a", "a1");
        map1.put("a", "a2");
        map2.put("a", "a1");
        map2.put("a", "a2");
        assertEquals(map1, map2);
    }

    public void testListValuedMapEqualsHashCodeContract_2_oe() {
        final ListValuedMap map1 = makeObject();
        final ListValuedMap map2 = makeObject();

        map1.put("a", "a1");
        map1.put("a", "a2");
        map2.put("a", "a1");
        map2.put("a", "a2");
        assertEquals(map1.hashCode(), map2.hashCode());
    }

    public void testListValuedMapEqualsHashCodeContract_3_oe() {
        final ListValuedMap map1 = makeObject();
        final ListValuedMap map2 = makeObject();

        map1.put("a", "a1");
        map1.put("a", "a2");
        map2.put("a", "a1");
        map2.put("a", "a2");

        map1.put("b", "b1");
        map1.put("b", "b2");
        map2.put("b", "b2");
        map2.put("b", "b1");
        assertNotSame(map1, map2);
    }

    public void testListValuedMapEqualsHashCodeContract_4_oe() {
        final ListValuedMap map1 = makeObject();
        final ListValuedMap map2 = makeObject();

        map1.put("a", "a1");
        map1.put("a", "a2");
        map2.put("a", "a1");
        map2.put("a", "a2");

        map1.put("b", "b1");
        map1.put("b", "b2");
        map2.put("b", "b2");
        map2.put("b", "b1");
        assertNotSame(map1.hashCode(), map2.hashCode());
    }

}
