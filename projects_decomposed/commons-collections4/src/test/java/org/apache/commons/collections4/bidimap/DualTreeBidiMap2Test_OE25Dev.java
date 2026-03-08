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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import junit.framework.Test;

import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.apache.commons.collections4.comparators.ReverseComparator;

/**
 * JUnit tests.
 *
 */
@SuppressWarnings("boxing")
public class DualTreeBidiMap2Test_OE25Dev<K extends Comparable<K>, V extends Comparable<V>> extends AbstractSortedBidiMapTest<K, V> {

    public static Test suite() {
        return new junit.framework.TestSuite(DualTreeBidiMap2Test_OE25Dev.class);
    }

    public DualTreeBidiMap2Test_OE25Dev(final String testName) {
        super(testName);
    }

    @Override
    public DualTreeBidiMap<K, V> makeObject() {
        return new DualTreeBidiMap<>(
                new ReverseComparator<>(ComparableComparator.<K> comparableComparator()),
                new ReverseComparator<>(ComparableComparator.<V> comparableComparator()));
    }

    @Override
    public TreeMap<K, V> makeConfirmedMap() {
        return new TreeMap<>(new ReverseComparator<>(ComparableComparator.<K>comparableComparator()));
    }

    public void testComparator() {
        resetEmpty();
        final SortedBidiMap<K, V> bidi = (SortedBidiMap<K, V>) map;
        assertNotNull(bidi.comparator());
        assertTrue(bidi.comparator() instanceof ReverseComparator);
    }

    public void testComparator2() {
        final DualTreeBidiMap<String, Integer> dtbm = new DualTreeBidiMap<>(
                String.CASE_INSENSITIVE_ORDER, null);
        dtbm.put("two", 0);
        dtbm.put("one", 1);
        assertEquals("one", dtbm.firstKey());
        assertEquals("two", dtbm.lastKey());

    }

    public void testSerializeDeserializeCheckComparator() throws Exception {
        final SortedBidiMap<?, ?> obj = makeObject();
        if (obj instanceof Serializable && isTestSerialization()) {
            final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            final ObjectOutputStream out = new ObjectOutputStream(buffer);
            out.writeObject(obj);
            out.close();

            final ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
            final Object dest = in.readObject();
            in.close();

            final SortedBidiMap<?,?> bidi = (SortedBidiMap<?,?>) dest;
            assertNotNull(obj.comparator());
            assertNotNull(bidi.comparator());
            assertTrue(bidi.comparator() instanceof ReverseComparator);
        }
    }

    private static class IntegerComparator implements Comparator<Integer>, Serializable{
        private static final long serialVersionUID = 1L;
        @Override
        public int compare(final Integer o1, final Integer o2) {
            return o1.compareTo(o2);
        }
    }

    public void testCollections364() throws Exception {
        final DualTreeBidiMap<String, Integer> original = new DualTreeBidiMap<>(
                String.CASE_INSENSITIVE_ORDER, new IntegerComparator());
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(buffer);
        out.writeObject(original);
        out.close();

        final ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
        @SuppressWarnings("unchecked")
        final DualTreeBidiMap<String, Integer> deserialised = (DualTreeBidiMap<String, Integer>) in.readObject();
        in.close();

        assertNotNull(original.comparator());
        assertNotNull(deserialised.comparator());
        assertEquals(original.comparator().getClass(), deserialised.comparator().getClass());
        assertEquals(original.valueComparator().getClass(), deserialised.valueComparator().getClass());
    }

    public void testSortOrder() throws Exception {
        final SortedBidiMap<K, V> sm = makeFullMap();

        // Sort by the comparator used in the makeEmptyBidiMap() method
        List<K> newSortedKeys = getAsList(getSampleKeys());
        Collections.sort(newSortedKeys, new ReverseComparator<>(ComparableComparator.<K>comparableComparator()));
        newSortedKeys = Collections.unmodifiableList(newSortedKeys);

        final Iterator<K> mapIter = sm.keySet().iterator();
        final Iterator<K> expectedIter = newSortedKeys.iterator();
        while (expectedIter.hasNext()) {
            final K expectedKey = expectedIter.next();
            final K mapKey = mapIter.next();
            assertNotNull("key in sorted list may not be null", expectedKey);
            assertNotNull("key in map may not be null", mapKey);
            assertEquals("key from sorted list and map must be equal", expectedKey, mapKey);
        }
    }

    @Override
    public String getCompatibilityVersion() {
        return "4.Test2";
    }

    /**
     * Override to prevent infinite recursion of tests.
     */
    @Override
    public String[] ignoredTests() {
        final String recursiveTest = "DualTreeBidiMap2Test_OE25Dev.bulkTestInverseMap.bulkTestInverseMap";
        return new String[] { recursiveTest };
    }


//    public void testCreate() throws Exception {
//        resetEmpty();
//        writeExternalFormToDisk((java.io.Serializable) map, "src/test/resources/data/test/DualTreeBidiMap.emptyCollection.version4.Test2.obj");
//        resetFull();
//        writeExternalFormToDisk((java.io.Serializable) map, "src/test/resources/data/test/DualTreeBidiMap.fullCollection.version4.Test2.obj");
//    }

    public void testComparator_1_oe() {
        resetEmpty();
        final SortedBidiMap<K, V> bidi = (SortedBidiMap<K, V>) map;
        assertNotNull(bidi.comparator());
    }

    public void testComparator_2_oe() {
        resetEmpty();
        final SortedBidiMap<K, V> bidi = (SortedBidiMap<K, V>) map;
        assertTrue(bidi.comparator() instanceof ReverseComparator);
    }

    public void testComparator2_1_oe() {
        final DualTreeBidiMap<String, Integer> dtbm = new DualTreeBidiMap<>(
                String.CASE_INSENSITIVE_ORDER, null);
        dtbm.put("two", 0);
        dtbm.put("one", 1);
        assertEquals("one", dtbm.firstKey());
    }

    public void testComparator2_2_oe() {
        final DualTreeBidiMap<String, Integer> dtbm = new DualTreeBidiMap<>(
                String.CASE_INSENSITIVE_ORDER, null);
        dtbm.put("two", 0);
        dtbm.put("one", 1);
        assertEquals("two", dtbm.lastKey());
    }

    public void testSerializeDeserializeCheckComparator_1_oe() throws Exception {
        final SortedBidiMap<?, ?> obj = makeObject();
        if (obj instanceof Serializable && isTestSerialization()) {
            final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            final ObjectOutputStream out = new ObjectOutputStream(buffer);
            out.writeObject(obj);
            out.close();

            final ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
            final Object dest = in.readObject();
            in.close();

            final SortedBidiMap<?,?> bidi = (SortedBidiMap<?,?>) dest;
            assertNotNull(obj.comparator());
    }
    }

    public void testSerializeDeserializeCheckComparator_2_oe() throws Exception {
        final SortedBidiMap<?, ?> obj = makeObject();
        if (obj instanceof Serializable && isTestSerialization()) {
            final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            final ObjectOutputStream out = new ObjectOutputStream(buffer);
            out.writeObject(obj);
            out.close();

            final ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
            final Object dest = in.readObject();
            in.close();

            final SortedBidiMap<?,?> bidi = (SortedBidiMap<?,?>) dest;
            assertNotNull(bidi.comparator());
    }
    }

    public void testSerializeDeserializeCheckComparator_3_oe() throws Exception {
        final SortedBidiMap<?, ?> obj = makeObject();
        if (obj instanceof Serializable && isTestSerialization()) {
            final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            final ObjectOutputStream out = new ObjectOutputStream(buffer);
            out.writeObject(obj);
            out.close();

            final ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
            final Object dest = in.readObject();
            in.close();

            final SortedBidiMap<?,?> bidi = (SortedBidiMap<?,?>) dest;
            assertTrue(bidi.comparator() instanceof ReverseComparator);
    }
    }

    public void testCollections364_1_oe() throws Exception {
        final DualTreeBidiMap<String, Integer> original = new DualTreeBidiMap<>(
                String.CASE_INSENSITIVE_ORDER, new IntegerComparator());
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(buffer);
        out.writeObject(original);
        out.close();

        final ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
        @SuppressWarnings("unchecked")
        final DualTreeBidiMap<String, Integer> deserialised = (DualTreeBidiMap<String, Integer>) in.readObject();
        in.close();

        assertNotNull(original.comparator());
    }

    public void testCollections364_2_oe() throws Exception {
        final DualTreeBidiMap<String, Integer> original = new DualTreeBidiMap<>(
                String.CASE_INSENSITIVE_ORDER, new IntegerComparator());
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(buffer);
        out.writeObject(original);
        out.close();

        final ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
        @SuppressWarnings("unchecked")
        final DualTreeBidiMap<String, Integer> deserialised = (DualTreeBidiMap<String, Integer>) in.readObject();
        in.close();

        assertNotNull(deserialised.comparator());
    }

    public void testCollections364_3_oe() throws Exception {
        final DualTreeBidiMap<String, Integer> original = new DualTreeBidiMap<>(
                String.CASE_INSENSITIVE_ORDER, new IntegerComparator());
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(buffer);
        out.writeObject(original);
        out.close();

        final ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
        @SuppressWarnings("unchecked")
        final DualTreeBidiMap<String, Integer> deserialised = (DualTreeBidiMap<String, Integer>) in.readObject();
        in.close();

        assertEquals(original.comparator().getClass(), deserialised.comparator().getClass());
    }

    public void testCollections364_4_oe() throws Exception {
        final DualTreeBidiMap<String, Integer> original = new DualTreeBidiMap<>(
                String.CASE_INSENSITIVE_ORDER, new IntegerComparator());
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(buffer);
        out.writeObject(original);
        out.close();

        final ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
        @SuppressWarnings("unchecked")
        final DualTreeBidiMap<String, Integer> deserialised = (DualTreeBidiMap<String, Integer>) in.readObject();
        in.close();

        assertEquals(original.valueComparator().getClass(), deserialised.valueComparator().getClass());
    }

    public void testSortOrder_1_oe() throws Exception {
        final SortedBidiMap<K, V> sm = makeFullMap();

        List<K> newSortedKeys = getAsList(getSampleKeys());
        Collections.sort(newSortedKeys, new ReverseComparator<>(ComparableComparator.<K>comparableComparator()));
        newSortedKeys = Collections.unmodifiableList(newSortedKeys);

        final Iterator<K> mapIter = sm.keySet().iterator();
        final Iterator<K> expectedIter = newSortedKeys.iterator();
        while (expectedIter.hasNext()) {
            final K expectedKey = expectedIter.next();
            final K mapKey = mapIter.next();
            assertNotNull("key in sorted list may not be null", expectedKey);
    }
    }

    public void testSortOrder_2_oe() throws Exception {
        final SortedBidiMap<K, V> sm = makeFullMap();

        List<K> newSortedKeys = getAsList(getSampleKeys());
        Collections.sort(newSortedKeys, new ReverseComparator<>(ComparableComparator.<K>comparableComparator()));
        newSortedKeys = Collections.unmodifiableList(newSortedKeys);

        final Iterator<K> mapIter = sm.keySet().iterator();
        final Iterator<K> expectedIter = newSortedKeys.iterator();
        while (expectedIter.hasNext()) {
            final K expectedKey = expectedIter.next();
            final K mapKey = mapIter.next();
            assertNotNull("key in map may not be null", mapKey);
    }
    }

    public void testSortOrder_3_oe() throws Exception {
        final SortedBidiMap<K, V> sm = makeFullMap();

        List<K> newSortedKeys = getAsList(getSampleKeys());
        Collections.sort(newSortedKeys, new ReverseComparator<>(ComparableComparator.<K>comparableComparator()));
        newSortedKeys = Collections.unmodifiableList(newSortedKeys);

        final Iterator<K> mapIter = sm.keySet().iterator();
        final Iterator<K> expectedIter = newSortedKeys.iterator();
        while (expectedIter.hasNext()) {
            final K expectedKey = expectedIter.next();
            final K mapKey = mapIter.next();
            assertEquals("key from sorted list and map must be equal", expectedKey, mapKey);
    }
    }

}
