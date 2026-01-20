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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.collections4.AbstractObjectTest;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.MultiMap;

/**
 * TestMultiValueMap.
 *
 * @since 3.2
 */
@Deprecated
public class MultiValueMapTest_OE25Dev<K, V> extends AbstractObjectTest {

    public MultiValueMapTest_OE25Dev(final String testName) {
        super(testName);
    }

    @SuppressWarnings("unchecked")
    private MultiValueMap<K, V> createTestMap() {
        return createTestMap(ArrayList.class);
    }

    @SuppressWarnings("unchecked")
    private <C extends Collection<V>> MultiValueMap<K, V> createTestMap(final Class<C> collectionClass) {
        final MultiValueMap<K, V> map = MultiValueMap.multiValueMap(new HashMap<K, C>(), collectionClass);
        map.put((K) "one", (V) "uno");
        map.put((K) "one", (V) "un");
        map.put((K) "two", (V) "dos");
        map.put((K) "two", (V) "deux");
        map.put((K) "three", (V) "tres");
        map.put((K) "three", (V) "trois");
        return map;
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    private byte[] serialize(final Object object) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);

        oos.writeObject(object);
        oos.close();

        return baos.toByteArray();
    }

    private Object deserialize(final byte[] data) throws IOException, ClassNotFoundException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(data);
        final ObjectInputStream iis = new ObjectInputStream(bais);

        return iis.readObject();
    }

    //-----------------------------------------------------------------------
    // Manual serialization testing as this class cannot easily
    // extend the AbstractTestMap
    //-----------------------------------------------------------------------

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    @Override
    public Object makeObject() {
        @SuppressWarnings("unchecked")
        final Map<String, String> m = makeEmptyMap();
        m.put("a", "1");
        m.put("a", "1b");
        m.put("b", "2");
        m.put("c", "3");
        m.put("c", "3b");
        m.put("d", "4");
        return m;
    }

    @SuppressWarnings("rawtypes")
    private Map makeEmptyMap() {
        return new MultiValueMap();
    }

//    public void testCreate() throws Exception {
//        writeExternalFormToDisk(
//            (java.io.Serializable) makeEmptyMap(),
//            "src/test/resources/data/test/MultiValueMap.emptyCollection.version4.obj");
//
//        writeExternalFormToDisk(
//            (java.io.Serializable) makeObject(),
//            "src/test/resources/data/test/MultiValueMap.fullCollection.version4.obj");
//    }

    public void testNoMappingReturnsNull_1_oe() {
        final MultiValueMap<K, V> map = createTestMap();
        assertNull(map.get("whatever"));
    }

    public void testValueCollectionType_1_oe() {
        final MultiValueMap<K, V> map = createTestMap(LinkedList.class);
        assertTrue(map.get("one") instanceof LinkedList);
    }

    public void testMultipleValues_1_oe() {
        final MultiValueMap<K, V> map = createTestMap(HashSet.class);
        final HashSet<V> expected = new HashSet<>();
        expected.add((V) "uno");
        expected.add((V) "un");
        assertEquals(expected, map.get("one"));
    }

    public void testContainsValue_1_oe() {
        final MultiValueMap<K, V> map = createTestMap(HashSet.class);
        assertTrue(map.containsValue("uno"));
    }

    public void testContainsValue_2_oe() {
        final MultiValueMap<K, V> map = createTestMap(HashSet.class);
        // removed other assertion
        assertTrue(map.containsValue("un"));
    }

    public void testContainsValue_3_oe() {
        final MultiValueMap<K, V> map = createTestMap(HashSet.class);
        // removed other assertion
        // removed other assertion
        assertTrue(map.containsValue("dos"));
    }

    public void testContainsValue_4_oe() {
        final MultiValueMap<K, V> map = createTestMap(HashSet.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(map.containsValue("deux"));
    }

    public void testContainsValue_5_oe() {
        final MultiValueMap<K, V> map = createTestMap(HashSet.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(map.containsValue("tres"));
    }

    public void testContainsValue_6_oe() {
        final MultiValueMap<K, V> map = createTestMap(HashSet.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(map.containsValue("trois"));
    }

    public void testContainsValue_7_oe() {
        final MultiValueMap<K, V> map = createTestMap(HashSet.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(map.containsValue("quatro"));
    }

    public void testKeyContainsValue_1_oe() {
        final MultiValueMap<K, V> map = createTestMap(HashSet.class);
        assertTrue(map.containsValue("one", "uno"));
    }

    public void testKeyContainsValue_2_oe() {
        final MultiValueMap<K, V> map = createTestMap(HashSet.class);
        // removed other assertion
        assertTrue(map.containsValue("one", "un"));
    }

    public void testKeyContainsValue_3_oe() {
        final MultiValueMap<K, V> map = createTestMap(HashSet.class);
        // removed other assertion
        // removed other assertion
        assertTrue(map.containsValue("two", "dos"));
    }

    public void testKeyContainsValue_4_oe() {
        final MultiValueMap<K, V> map = createTestMap(HashSet.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(map.containsValue("two", "deux"));
    }

    public void testKeyContainsValue_5_oe() {
        final MultiValueMap<K, V> map = createTestMap(HashSet.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(map.containsValue("three", "tres"));
    }

    public void testKeyContainsValue_6_oe() {
        final MultiValueMap<K, V> map = createTestMap(HashSet.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(map.containsValue("three", "trois"));
    }

    public void testKeyContainsValue_7_oe() {
        final MultiValueMap<K, V> map = createTestMap(HashSet.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(map.containsValue("four", "quatro"));
    }

    public void testValues_1_oe() {
        final MultiValueMap<K, V> map = createTestMap(HashSet.class);
        final HashSet<V> expected = new HashSet<>();
        expected.add((V) "uno");
        expected.add((V) "dos");
        expected.add((V) "tres");
        expected.add((V) "un");
        expected.add((V) "deux");
        expected.add((V) "trois");
        final Collection<Object> c = map.values();
        assertEquals(6, c.size());
    }

    public void testValues_2_oe() {
        final MultiValueMap<K, V> map = createTestMap(HashSet.class);
        final HashSet<V> expected = new HashSet<>();
        expected.add((V) "uno");
        expected.add((V) "dos");
        expected.add((V) "tres");
        expected.add((V) "un");
        expected.add((V) "deux");
        expected.add((V) "trois");
        final Collection<Object> c = map.values();
        // removed other assertion
        assertEquals(expected, new HashSet<>(c));
    }

    public void testKeyedIterator_1_oe() {
        final MultiValueMap<K, V> map = createTestMap();
        final ArrayList<Object> actual = new ArrayList<>(IteratorUtils.toList(map.iterator("one")));
        final ArrayList<Object> expected = new ArrayList<>(Arrays.asList("uno", "un"));
        assertEquals(expected, actual);
    }

    public void testRemoveAllViaIterator_1_oe() {
        final MultiValueMap<K, V> map = createTestMap();
        for (final Iterator<?> i = map.values().iterator(); i.hasNext();) {
            i.next();
            i.remove();
        }
        assertNull(map.get("one"));
    }

    public void testRemoveAllViaIterator_2_oe() {
        final MultiValueMap<K, V> map = createTestMap();
        for (final Iterator<?> i = map.values().iterator(); i.hasNext();) {
            i.next();
            i.remove();
        }
        // removed other assertion
        assertTrue(map.isEmpty());
    }

    public void testRemoveAllViaKeyedIterator_1_oe() {
        final MultiValueMap<K, V> map = createTestMap();
        for (final Iterator<?> i = map.iterator("one"); i.hasNext();) {
            i.next();
            i.remove();
        }
        assertNull(map.get("one"));
    }

    public void testRemoveAllViaKeyedIterator_2_oe() {
        final MultiValueMap<K, V> map = createTestMap();
        for (final Iterator<?> i = map.iterator("one"); i.hasNext();) {
            i.next();
            i.remove();
        }
        // removed other assertion
        assertEquals(4, map.totalSize());
    }

    public void testIterator_1_oe() {
        final MultiValueMap<K, V> map = createTestMap();
        @SuppressWarnings("unchecked")
        final
        Collection<V> values = new ArrayList<>((Collection<V>) map.values());
        final Iterator<Map.Entry<K, V>> iterator = map.iterator();
        while (iterator.hasNext()) {
            final Map.Entry<K, V> entry = iterator.next();
            assertTrue(map.containsValue(entry.getKey(), entry.getValue()));
    }
    }

    public void testIterator_2_oe() {
        final MultiValueMap<K, V> map = createTestMap();
        @SuppressWarnings("unchecked")
        final
        Collection<V> values = new ArrayList<>((Collection<V>) map.values());
        final Iterator<Map.Entry<K, V>> iterator = map.iterator();
        while (iterator.hasNext()) {
            final Map.Entry<K, V> entry = iterator.next();
            // removed other assertion
            assertTrue(values.contains(entry.getValue()));
    }
    }

    public void testIterator_3_oe() {
        final MultiValueMap<K, V> map = createTestMap();
        @SuppressWarnings("unchecked")
        final
        Collection<V> values = new ArrayList<>((Collection<V>) map.values());
        final Iterator<Map.Entry<K, V>> iterator = map.iterator();
        while (iterator.hasNext()) {
            final Map.Entry<K, V> entry = iterator.next();
            // removed other assertion
            // removed other assertion
            assertTrue(values.remove(entry.getValue()));
    }
    }

    public void testIterator_4_oe() {
        final MultiValueMap<K, V> map = createTestMap();
        @SuppressWarnings("unchecked")
        final
        Collection<V> values = new ArrayList<>((Collection<V>) map.values());
        final Iterator<Map.Entry<K, V>> iterator = map.iterator();
        while (iterator.hasNext()) {
            final Map.Entry<K, V> entry = iterator.next();
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }
        assertTrue(values.isEmpty());
    }

    public void testRemoveAllViaEntryIterator_1_oe() {
        final MultiValueMap<K, V> map = createTestMap();
        for (final Iterator<?> i = map.iterator(); i.hasNext();) {
            i.next();
            i.remove();
        }
        assertNull(map.get("one"));
    }

    public void testRemoveAllViaEntryIterator_2_oe() {
        final MultiValueMap<K, V> map = createTestMap();
        for (final Iterator<?> i = map.iterator(); i.hasNext();) {
            i.next();
            i.remove();
        }
        // removed other assertion
        assertEquals(0, map.totalSize());
    }

    public void testTotalSizeA_1_oe() {
        assertEquals(6, createTestMap().totalSize());
    }

    public void testMapEquals_1_oe() {
        final MultiValueMap<K, V> one = new MultiValueMap<>();
        final Integer value = Integer.valueOf(1);
        one.put((K) "One", value);
        one.removeMapping("One", value);

        final MultiValueMap<K, V> two = new MultiValueMap<>();
        assertEquals(two, one);
    }

    public void testGetCollection_1_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        map.put((K) "A", "AA");
        assertSame(map.get("A"), map.getCollection("A"));
    }

    public void testTotalSize_1_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        assertEquals(0, map.totalSize());
    }

    public void testTotalSize_2_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        map.put((K) "A", "AA");
        assertEquals(1, map.totalSize());
    }

    public void testTotalSize_3_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        map.put((K) "B", "BA");
        assertEquals(2, map.totalSize());
    }

    public void testTotalSize_4_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        map.put((K) "B", "BA");
        // removed other assertion
        map.put((K) "B", "BB");
        assertEquals(3, map.totalSize());
    }

    public void testTotalSize_5_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        map.put((K) "B", "BA");
        // removed other assertion
        map.put((K) "B", "BB");
        // removed other assertion
        map.put((K) "B", "BC");
        assertEquals(4, map.totalSize());
    }

    public void testTotalSize_6_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        map.put((K) "B", "BA");
        // removed other assertion
        map.put((K) "B", "BB");
        // removed other assertion
        map.put((K) "B", "BC");
        // removed other assertion
        map.remove("A");
        assertEquals(3, map.totalSize());
    }

    public void testTotalSize_7_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        map.put((K) "B", "BA");
        // removed other assertion
        map.put((K) "B", "BB");
        // removed other assertion
        map.put((K) "B", "BC");
        // removed other assertion
        map.remove("A");
        // removed other assertion
        map.removeMapping("B", "BC");
        assertEquals(2, map.totalSize());
    }

    public void testSize_1_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        assertEquals(0, map.size());
    }

    public void testSize_2_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        map.put((K) "A", "AA");
        assertEquals(1, map.size());
    }

    public void testSize_3_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        map.put((K) "B", "BA");
        assertEquals(2, map.size());
    }

    public void testSize_4_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        map.put((K) "B", "BA");
        // removed other assertion
        map.put((K) "B", "BB");
        assertEquals(2, map.size());
    }

    public void testSize_5_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        map.put((K) "B", "BA");
        // removed other assertion
        map.put((K) "B", "BB");
        // removed other assertion
        map.put((K) "B", "BC");
        assertEquals(2, map.size());
    }

    public void testSize_6_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        map.put((K) "B", "BA");
        // removed other assertion
        map.put((K) "B", "BB");
        // removed other assertion
        map.put((K) "B", "BC");
        // removed other assertion
        map.remove("A");
        assertEquals(1, map.size());
    }

    public void testSize_7_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        map.put((K) "B", "BA");
        // removed other assertion
        map.put((K) "B", "BB");
        // removed other assertion
        map.put((K) "B", "BC");
        // removed other assertion
        map.remove("A");
        // removed other assertion
        map.removeMapping("B", "BC");
        assertEquals(1, map.size());
    }

    public void testSize_Key_1_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        assertEquals(0, map.size("A"));
    }

    public void testSize_Key_2_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        assertEquals(0, map.size("B"));
    }

    public void testSize_Key_3_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        // removed other assertion
        map.put((K) "A", "AA");
        assertEquals(1, map.size("A"));
    }

    public void testSize_Key_4_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        assertEquals(0, map.size("B"));
    }

    public void testSize_Key_5_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BA");
        assertEquals(1, map.size("A"));
    }

    public void testSize_Key_6_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BA");
        // removed other assertion
        assertEquals(1, map.size("B"));
    }

    public void testSize_Key_7_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BB");
        assertEquals(1, map.size("A"));
    }

    public void testSize_Key_8_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BB");
        // removed other assertion
        assertEquals(2, map.size("B"));
    }

    public void testSize_Key_9_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BB");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BC");
        assertEquals(1, map.size("A"));
    }

    public void testSize_Key_10_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BB");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BC");
        // removed other assertion
        assertEquals(3, map.size("B"));
    }

    public void testSize_Key_11_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BB");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BC");
        // removed other assertion
        // removed other assertion
        map.remove("A");
        assertEquals(0, map.size("A"));
    }

    public void testSize_Key_12_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BB");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BC");
        // removed other assertion
        // removed other assertion
        map.remove("A");
        // removed other assertion
        assertEquals(3, map.size("B"));
    }

    public void testSize_Key_13_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BB");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BC");
        // removed other assertion
        // removed other assertion
        map.remove("A");
        // removed other assertion
        // removed other assertion
        map.removeMapping("B", "BC");
        assertEquals(0, map.size("A"));
    }

    public void testSize_Key_14_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BA");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BB");
        // removed other assertion
        // removed other assertion
        map.put((K) "B", "BC");
        // removed other assertion
        // removed other assertion
        map.remove("A");
        // removed other assertion
        // removed other assertion
        map.removeMapping("B", "BC");
        // removed other assertion
        assertEquals(2, map.size("B"));
    }

    public void testIterator_Key_1_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        assertEquals(false, map.iterator("A").hasNext());
    }

    public void testIterator_Key_2_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        map.put((K) "A", "AA");
        final Iterator<?> it = map.iterator("A");
        assertEquals(true, it.hasNext());
    }

    public void testIterator_Key_3_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        map.put((K) "A", "AA");
        final Iterator<?> it = map.iterator("A");
        // removed other assertion
        it.next();
        assertEquals(false, it.hasNext());
    }

    public void testContainsValue_Key_1_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        assertEquals(false, map.containsValue("A", "AA"));
    }

    public void testContainsValue_Key_2_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        assertEquals(false, map.containsValue("B", "BB"));
    }

    public void testContainsValue_Key_3_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        // removed other assertion
        map.put((K) "A", "AA");
        assertEquals(true, map.containsValue("A", "AA"));
    }

    public void testContainsValue_Key_4_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        // removed other assertion
        // removed other assertion
        map.put((K) "A", "AA");
        // removed other assertion
        assertEquals(false, map.containsValue("A", "AB"));
    }

    public void testPutWithList_1_oe() {
        @SuppressWarnings("rawtypes")
        final MultiValueMap<K, V> test = MultiValueMap.multiValueMap(new HashMap<K, Collection>(), ArrayList.class);
        assertEquals("a", test.put((K) "A", "a"));
    }

    public void testPutWithList_2_oe() {
        @SuppressWarnings("rawtypes")
        final MultiValueMap<K, V> test = MultiValueMap.multiValueMap(new HashMap<K, Collection>(), ArrayList.class);
        // removed other assertion
        assertEquals("b", test.put((K) "A", "b"));
    }

    public void testPutWithList_3_oe() {
        @SuppressWarnings("rawtypes")
        final MultiValueMap<K, V> test = MultiValueMap.multiValueMap(new HashMap<K, Collection>(), ArrayList.class);
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.size());
    }

    public void testPutWithList_4_oe() {
        @SuppressWarnings("rawtypes")
        final MultiValueMap<K, V> test = MultiValueMap.multiValueMap(new HashMap<K, Collection>(), ArrayList.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.size("A"));
    }

    public void testPutWithList_5_oe() {
        @SuppressWarnings("rawtypes")
        final MultiValueMap<K, V> test = MultiValueMap.multiValueMap(new HashMap<K, Collection>(), ArrayList.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.totalSize());
    }

    public void testPutWithSet_1_oe() {
        @SuppressWarnings("rawtypes")
        final MultiValueMap<K, V> test = MultiValueMap.multiValueMap(new HashMap<K, HashSet>(), HashSet.class);
        assertEquals("a", test.put((K) "A", "a"));
    }

    public void testPutWithSet_2_oe() {
        @SuppressWarnings("rawtypes")
        final MultiValueMap<K, V> test = MultiValueMap.multiValueMap(new HashMap<K, HashSet>(), HashSet.class);
        // removed other assertion
        assertEquals("b", test.put((K) "A", "b"));
    }

    public void testPutWithSet_3_oe() {
        @SuppressWarnings("rawtypes")
        final MultiValueMap<K, V> test = MultiValueMap.multiValueMap(new HashMap<K, HashSet>(), HashSet.class);
        // removed other assertion
        // removed other assertion
        assertEquals(null, test.put((K) "A", "a"));
    }

    public void testPutWithSet_4_oe() {
        @SuppressWarnings("rawtypes")
        final MultiValueMap<K, V> test = MultiValueMap.multiValueMap(new HashMap<K, HashSet>(), HashSet.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.size());
    }

    public void testPutWithSet_5_oe() {
        @SuppressWarnings("rawtypes")
        final MultiValueMap<K, V> test = MultiValueMap.multiValueMap(new HashMap<K, HashSet>(), HashSet.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.size("A"));
    }

    public void testPutWithSet_6_oe() {
        @SuppressWarnings("rawtypes")
        final MultiValueMap<K, V> test = MultiValueMap.multiValueMap(new HashMap<K, HashSet>(), HashSet.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.totalSize());
    }

    public void testPutAll_Map1_1_oe() {
        final MultiMap<K, V> original = new MultiValueMap<>();
        original.put((K) "key", "object1");
        original.put((K) "key", "object2");

        final MultiValueMap<K, V> test = new MultiValueMap<>();
        test.put((K) "keyA", "objectA");
        test.put((K) "key", "object0");
        test.putAll(original);

        assertEquals(2, test.size());
    }

    public void testPutAll_Map1_2_oe() {
        final MultiMap<K, V> original = new MultiValueMap<>();
        original.put((K) "key", "object1");
        original.put((K) "key", "object2");

        final MultiValueMap<K, V> test = new MultiValueMap<>();
        test.put((K) "keyA", "objectA");
        test.put((K) "key", "object0");
        test.putAll(original);

        // removed other assertion
        assertEquals(4, test.totalSize());
    }

    public void testPutAll_Map1_3_oe() {
        final MultiMap<K, V> original = new MultiValueMap<>();
        original.put((K) "key", "object1");
        original.put((K) "key", "object2");

        final MultiValueMap<K, V> test = new MultiValueMap<>();
        test.put((K) "keyA", "objectA");
        test.put((K) "key", "object0");
        test.putAll(original);

        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getCollection("keyA").size());
    }

    public void testPutAll_Map1_4_oe() {
        final MultiMap<K, V> original = new MultiValueMap<>();
        original.put((K) "key", "object1");
        original.put((K) "key", "object2");

        final MultiValueMap<K, V> test = new MultiValueMap<>();
        test.put((K) "keyA", "objectA");
        test.put((K) "key", "object0");
        test.putAll(original);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, test.getCollection("key").size());
    }

    public void testPutAll_Map1_5_oe() {
        final MultiMap<K, V> original = new MultiValueMap<>();
        original.put((K) "key", "object1");
        original.put((K) "key", "object2");

        final MultiValueMap<K, V> test = new MultiValueMap<>();
        test.put((K) "keyA", "objectA");
        test.put((K) "key", "object0");
        test.putAll(original);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.containsValue("objectA"));
    }

    public void testPutAll_Map1_6_oe() {
        final MultiMap<K, V> original = new MultiValueMap<>();
        original.put((K) "key", "object1");
        original.put((K) "key", "object2");

        final MultiValueMap<K, V> test = new MultiValueMap<>();
        test.put((K) "keyA", "objectA");
        test.put((K) "key", "object0");
        test.putAll(original);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.containsValue("object0"));
    }

    public void testPutAll_Map1_7_oe() {
        final MultiMap<K, V> original = new MultiValueMap<>();
        original.put((K) "key", "object1");
        original.put((K) "key", "object2");

        final MultiValueMap<K, V> test = new MultiValueMap<>();
        test.put((K) "keyA", "objectA");
        test.put((K) "key", "object0");
        test.putAll(original);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.containsValue("object1"));
    }

    public void testPutAll_Map1_8_oe() {
        final MultiMap<K, V> original = new MultiValueMap<>();
        original.put((K) "key", "object1");
        original.put((K) "key", "object2");

        final MultiValueMap<K, V> test = new MultiValueMap<>();
        test.put((K) "keyA", "objectA");
        test.put((K) "key", "object0");
        test.putAll(original);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.containsValue("object2"));
    }

    public void testPutAll_Map2_1_oe() {
        final Map<K, V> original = new HashMap<>();
        original.put((K) "keyX", (V) "object1");
        original.put((K) "keyY", (V) "object2");

        final MultiValueMap<K, V> test = new MultiValueMap<>();
        test.put((K) "keyA", "objectA");
        test.put((K) "keyX", "object0");
        test.putAll(original);

        assertEquals(3, test.size());
    }

    public void testPutAll_Map2_2_oe() {
        final Map<K, V> original = new HashMap<>();
        original.put((K) "keyX", (V) "object1");
        original.put((K) "keyY", (V) "object2");

        final MultiValueMap<K, V> test = new MultiValueMap<>();
        test.put((K) "keyA", "objectA");
        test.put((K) "keyX", "object0");
        test.putAll(original);

        // removed other assertion
        assertEquals(4, test.totalSize());
    }

    public void testPutAll_Map2_3_oe() {
        final Map<K, V> original = new HashMap<>();
        original.put((K) "keyX", (V) "object1");
        original.put((K) "keyY", (V) "object2");

        final MultiValueMap<K, V> test = new MultiValueMap<>();
        test.put((K) "keyA", "objectA");
        test.put((K) "keyX", "object0");
        test.putAll(original);

        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getCollection("keyA").size());
    }

    public void testPutAll_Map2_4_oe() {
        final Map<K, V> original = new HashMap<>();
        original.put((K) "keyX", (V) "object1");
        original.put((K) "keyY", (V) "object2");

        final MultiValueMap<K, V> test = new MultiValueMap<>();
        test.put((K) "keyA", "objectA");
        test.put((K) "keyX", "object0");
        test.putAll(original);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, test.getCollection("keyX").size());
    }

    public void testPutAll_Map2_5_oe() {
        final Map<K, V> original = new HashMap<>();
        original.put((K) "keyX", (V) "object1");
        original.put((K) "keyY", (V) "object2");

        final MultiValueMap<K, V> test = new MultiValueMap<>();
        test.put((K) "keyA", "objectA");
        test.put((K) "keyX", "object0");
        test.putAll(original);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, test.getCollection("keyY").size());
    }

    public void testPutAll_Map2_6_oe() {
        final Map<K, V> original = new HashMap<>();
        original.put((K) "keyX", (V) "object1");
        original.put((K) "keyY", (V) "object2");

        final MultiValueMap<K, V> test = new MultiValueMap<>();
        test.put((K) "keyA", "objectA");
        test.put((K) "keyX", "object0");
        test.putAll(original);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.containsValue("objectA"));
    }

    public void testPutAll_Map2_7_oe() {
        final Map<K, V> original = new HashMap<>();
        original.put((K) "keyX", (V) "object1");
        original.put((K) "keyY", (V) "object2");

        final MultiValueMap<K, V> test = new MultiValueMap<>();
        test.put((K) "keyA", "objectA");
        test.put((K) "keyX", "object0");
        test.putAll(original);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.containsValue("object0"));
    }

    public void testPutAll_Map2_8_oe() {
        final Map<K, V> original = new HashMap<>();
        original.put((K) "keyX", (V) "object1");
        original.put((K) "keyY", (V) "object2");

        final MultiValueMap<K, V> test = new MultiValueMap<>();
        test.put((K) "keyA", "objectA");
        test.put((K) "keyX", "object0");
        test.putAll(original);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.containsValue("object1"));
    }

    public void testPutAll_Map2_9_oe() {
        final Map<K, V> original = new HashMap<>();
        original.put((K) "keyX", (V) "object1");
        original.put((K) "keyY", (V) "object2");

        final MultiValueMap<K, V> test = new MultiValueMap<>();
        test.put((K) "keyA", "objectA");
        test.put((K) "keyX", "object0");
        test.putAll(original);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, test.containsValue("object2"));
    }

    public void testPutAll_KeyCollection_1_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        assertEquals(true, map.putAll((K) "A", coll));
    }

    public void testPutAll_KeyCollection_2_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        assertEquals(3, map.size("A"));
    }

    public void testPutAll_KeyCollection_3_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsValue("A", "X"));
    }

    public void testPutAll_KeyCollection_4_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsValue("A", "Y"));
    }

    public void testPutAll_KeyCollection_5_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsValue("A", "Z"));
    }

    public void testPutAll_KeyCollection_6_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(false, map.putAll((K) "A", null));
    }

    public void testPutAll_KeyCollection_7_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(3, map.size("A"));
    }

    public void testPutAll_KeyCollection_8_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsValue("A", "X"));
    }

    public void testPutAll_KeyCollection_9_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsValue("A", "Y"));
    }

    public void testPutAll_KeyCollection_10_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsValue("A", "Z"));
    }

    public void testPutAll_KeyCollection_11_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(false, map.putAll((K) "A", new ArrayList<V>()));
    }

    public void testPutAll_KeyCollection_12_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(3, map.size("A"));
    }

    public void testPutAll_KeyCollection_13_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsValue("A", "X"));
    }

    public void testPutAll_KeyCollection_14_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsValue("A", "Y"));
    }

    public void testPutAll_KeyCollection_15_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsValue("A", "Z"));
    }

    public void testPutAll_KeyCollection_16_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        coll = (Collection<V>) Arrays.asList("M");
        assertEquals(true, map.putAll((K) "A", coll));
    }

    public void testPutAll_KeyCollection_17_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        coll = (Collection<V>) Arrays.asList("M");
        // removed other assertion
        assertEquals(4, map.size("A"));
    }

    public void testPutAll_KeyCollection_18_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        coll = (Collection<V>) Arrays.asList("M");
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsValue("A", "X"));
    }

    public void testPutAll_KeyCollection_19_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        coll = (Collection<V>) Arrays.asList("M");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsValue("A", "Y"));
    }

    public void testPutAll_KeyCollection_20_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        coll = (Collection<V>) Arrays.asList("M");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsValue("A", "Z"));
    }

    public void testPutAll_KeyCollection_21_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        Collection<V> coll = (Collection<V>) Arrays.asList("X", "Y", "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        coll = (Collection<V>) Arrays.asList("M");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsValue("A", "M"));
    }

    public void testRemove_KeyItem_1_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        map.put((K) "A", "AA");
        map.put((K) "A", "AB");
        map.put((K) "A", "AC");
        assertEquals(false, map.removeMapping("C", "CA"));
    }

    public void testRemove_KeyItem_2_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        map.put((K) "A", "AA");
        map.put((K) "A", "AB");
        map.put((K) "A", "AC");
        // removed other assertion
        assertEquals(false, map.removeMapping("A", "AD"));
    }

    public void testRemove_KeyItem_3_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        map.put((K) "A", "AA");
        map.put((K) "A", "AB");
        map.put((K) "A", "AC");
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.removeMapping("A", "AC"));
    }

    public void testRemove_KeyItem_4_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        map.put((K) "A", "AA");
        map.put((K) "A", "AB");
        map.put((K) "A", "AC");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.removeMapping("A", "AB"));
    }

    public void testRemove_KeyItem_5_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        map.put((K) "A", "AA");
        map.put((K) "A", "AB");
        map.put((K) "A", "AC");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.removeMapping("A", "AA"));
    }

    public void testRemove_KeyItem_6_oe() {
        final MultiValueMap<K, V> map = new MultiValueMap<>();
        map.put((K) "A", "AA");
        map.put((K) "A", "AB");
        map.put((K) "A", "AC");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new MultiValueMap<K, V>(), map);
    }

    public void testUnsafeDeSerialization_1_oe() throws Exception {
        final MultiValueMap map1 = MultiValueMap.multiValueMap(new HashMap(), ArrayList.class);
        byte[] bytes = serialize(map1);
        Object result = deserialize(bytes);
        assertEquals(map1, result);
    }

    public void testEmptyMapCompatibility_1_oe() throws Exception {
        final Map<?,?> map = makeEmptyMap();
        final Map<?,?> map2 = (Map<?,?>) readExternalFormFromDisk(getCanonicalEmptyCollectionName(map));
        assertEquals("Map is empty", 0, map2.size());
    }

    public void testFullMapCompatibility_1_oe() throws Exception {
        final Map<?,?> map = (Map<?,?>) makeObject();
        final Map<?,?> map2 = (Map<?,?>) readExternalFormFromDisk(getCanonicalFullCollectionName(map));
        assertEquals("Map is the right size", map.size(), map2.size());
    }

    public void testFullMapCompatibility_2_oe() throws Exception {
        final Map<?,?> map = (Map<?,?>) makeObject();
        final Map<?,?> map2 = (Map<?,?>) readExternalFormFromDisk(getCanonicalFullCollectionName(map));
        // removed other assertion
        for (final Object key : map.keySet()) {
            assertEquals( "Map had inequal elements", map.get(key), map2.get(key) );
    }
    }

    public void testFullMapCompatibility_3_oe() throws Exception {
        final Map<?,?> map = (Map<?,?>) makeObject();
        final Map<?,?> map2 = (Map<?,?>) readExternalFormFromDisk(getCanonicalFullCollectionName(map));
        // removed other assertion
        for (final Object key : map.keySet()) {
            // removed other assertion
            map2.remove(key);
        }
        assertEquals("Map had extra values", 0, map2.size());
    }

}
