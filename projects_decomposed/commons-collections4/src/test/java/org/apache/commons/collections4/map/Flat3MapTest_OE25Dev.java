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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import junit.framework.Test;

import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.iterators.AbstractMapIteratorTest;

/**
 * JUnit tests.
 *
 */
public class Flat3MapTest_OE25Dev<K, V> extends AbstractIterableMapTest<K, V> {

    private static final Integer ONE = Integer.valueOf(1);
    private static final Integer TWO = Integer.valueOf(2);
    private static final Integer THREE = Integer.valueOf(3);
    private static final String TEN = "10";
    private static final String TWENTY = "20";
    private static final String THIRTY = "30";

    public Flat3MapTest_OE25Dev(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return new junit.framework.TestSuite(Flat3MapTest_OE25Dev.class);
    }

    @Override
    public Flat3Map<K, V> makeObject() {
        return new Flat3Map<>();
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public void testEquals1() {
        final Flat3Map<K, V> map1 = makeObject();
        map1.put((K) "a", (V) "testA");
        map1.put((K) "b", (V) "testB");
        final Flat3Map<K, V> map2 = makeObject();
        map2.put((K) "a", (V) "testB");
        map2.put((K) "b", (V) "testA");
        assertEquals(false, map1.equals(map2));
    }

    @SuppressWarnings("unchecked")
    public void testEquals2() {
        final Flat3Map<K, V> map1 = makeObject();
        map1.put((K) "a", (V) "testA");
        map1.put((K) "b", (V) "testB");
        final Flat3Map<K, V> map2 = makeObject();
        map2.put((K) "a", (V) "testB");
        map2.put((K) "c", (V) "testA");
        assertEquals(false, map1.equals(map2));
    }

    @SuppressWarnings("unchecked")
    public void testClone2() {
        final Flat3Map<K, V> map = makeObject();
        assertEquals(0, map.size());
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        assertEquals(2, map.size());
        assertEquals(true, map.containsKey(ONE));
        assertEquals(true, map.containsKey(TWO));
        assertSame(TEN, map.get(ONE));
        assertSame(TWENTY, map.get(TWO));

        // clone works (size = 2)
        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(2, cloned.size());
        assertEquals(true, cloned.containsKey(ONE));
        assertEquals(true, cloned.containsKey(TWO));
        assertSame(TEN, cloned.get(ONE));
        assertSame(TWENTY, cloned.get(TWO));

        // change original doesn't change clone
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);
        assertEquals(4, map.size());
        assertEquals(2, cloned.size());
        assertEquals(true, cloned.containsKey(ONE));
        assertEquals(true, cloned.containsKey(TWO));
        assertSame(TEN, cloned.get(ONE));
        assertSame(TWENTY, cloned.get(TWO));
    }

    @SuppressWarnings("unchecked")
    public void testClone4() {
        final Flat3Map<K, V> map = makeObject();
        assertEquals(0, map.size());
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(4, map.size());
        assertEquals(4, cloned.size());
        assertEquals(true, cloned.containsKey(ONE));
        assertEquals(true, cloned.containsKey(TWO));
        assertEquals(true, cloned.containsKey(TEN));
        assertEquals(true, cloned.containsKey(TWENTY));
        assertSame(TEN, cloned.get(ONE));
        assertSame(TWENTY, cloned.get(TWO));
        assertSame(ONE, cloned.get(TEN));
        assertSame(TWO, cloned.get(TWENTY));

        // change original doesn't change clone
        map.clear();
        assertEquals(0, map.size());
        assertEquals(4, cloned.size());
        assertEquals(true, cloned.containsKey(ONE));
        assertEquals(true, cloned.containsKey(TWO));
        assertEquals(true, cloned.containsKey(TEN));
        assertEquals(true, cloned.containsKey(TWENTY));
        assertSame(TEN, cloned.get(ONE));
        assertSame(TWENTY, cloned.get(TWO));
        assertSame(ONE, cloned.get(TEN));
        assertSame(TWO, cloned.get(TWENTY));
    }

    public void testSerialisation0() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(0, map.size());
        assertEquals(0, ser.size());
    }

    @SuppressWarnings("unchecked")
    public void testSerialisation2() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(2, map.size());
        assertEquals(2, ser.size());
        assertEquals(true, ser.containsKey(ONE));
        assertEquals(true, ser.containsKey(TWO));
        assertEquals(TEN, ser.get(ONE));
        assertEquals(TWENTY, ser.get(TWO));
    }

    @SuppressWarnings("unchecked")
    public void testSerialisation4() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(4, map.size());
        assertEquals(4, ser.size());
        assertEquals(true, ser.containsKey(ONE));
        assertEquals(true, ser.containsKey(TWO));
        assertEquals(true, ser.containsKey(TEN));
        assertEquals(true, ser.containsKey(TWENTY));
        assertEquals(TEN, ser.get(ONE));
        assertEquals(TWENTY, ser.get(TWO));
        assertEquals(ONE, ser.get(TEN));
        assertEquals(TWO, ser.get(TWENTY));
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public void testEntryIteratorSetValue1() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals(3, map.size());
        assertEquals(true, map.containsKey(ONE));
        assertEquals(true, map.containsKey(TWO));
        assertEquals(true, map.containsKey(THREE));
        assertEquals("NewValue", map.get(ONE));
        assertEquals(TWENTY, map.get(TWO));
        assertEquals(THIRTY, map.get(THREE));
    }

    @SuppressWarnings("unchecked")
    public void testEntryIteratorSetValue2() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        it.next();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals(3, map.size());
        assertEquals(true, map.containsKey(ONE));
        assertEquals(true, map.containsKey(TWO));
        assertEquals(true, map.containsKey(THREE));
        assertEquals(TEN, map.get(ONE));
        assertEquals("NewValue", map.get(TWO));
        assertEquals(THIRTY, map.get(THREE));
    }

    @SuppressWarnings("unchecked")
    public void testEntryIteratorSetValue3() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        it.next();
        it.next();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals(3, map.size());
        assertEquals(true, map.containsKey(ONE));
        assertEquals(true, map.containsKey(TWO));
        assertEquals(true, map.containsKey(THREE));
        assertEquals(TEN, map.get(ONE));
        assertEquals(TWENTY, map.get(TWO));
        assertEquals("NewValue", map.get(THREE));
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public void testMapIteratorSetValue1() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals(3, map.size());
        assertEquals(true, map.containsKey(ONE));
        assertEquals(true, map.containsKey(TWO));
        assertEquals(true, map.containsKey(THREE));
        assertEquals("NewValue", map.get(ONE));
        assertEquals(TWENTY, map.get(TWO));
        assertEquals(THIRTY, map.get(THREE));
    }

    @SuppressWarnings("unchecked")
    public void testMapIteratorSetValue2() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals(3, map.size());
        assertEquals(true, map.containsKey(ONE));
        assertEquals(true, map.containsKey(TWO));
        assertEquals(true, map.containsKey(THREE));
        assertEquals(TEN, map.get(ONE));
        assertEquals("NewValue", map.get(TWO));
        assertEquals(THIRTY, map.get(THREE));
    }

    @SuppressWarnings("unchecked")
    public void testMapIteratorSetValue3() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.next();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals(3, map.size());
        assertEquals(true, map.containsKey(ONE));
        assertEquals(true, map.containsKey(TWO));
        assertEquals(true, map.containsKey(THREE));
        assertEquals(TEN, map.get(ONE));
        assertEquals(TWENTY, map.get(TWO));
        assertEquals("NewValue", map.get(THREE));
    }

    //-----------------------------------------------------------------------
    @Override
    public BulkTest bulkTestMapIterator() {
        return new TestFlatMapIterator();
    }

    public class TestFlatMapIterator extends AbstractMapIteratorTest<K, V> {
        public TestFlatMapIterator() {
            super("TestFlatMapIterator");
        }

        @Override
        public V[] addSetValues() {
            return Flat3MapTest_OE25Dev.this.getNewSampleValues();
        }

        @Override
        public boolean supportsRemove() {
            return Flat3MapTest_OE25Dev.this.isRemoveSupported();
        }

        @Override
        public boolean supportsSetValue() {
            return Flat3MapTest_OE25Dev.this.isSetValueSupported();
        }

        @Override
        public MapIterator<K, V> makeEmptyIterator() {
            resetEmpty();
            return Flat3MapTest_OE25Dev.this.getMap().mapIterator();
        }

        @Override
        public MapIterator<K, V> makeObject() {
            resetFull();
            return Flat3MapTest_OE25Dev.this.getMap().mapIterator();
        }

        @Override
        public IterableMap<K, V> getMap() {
            // assumes makeFullMapIterator() called first
            return Flat3MapTest_OE25Dev.this.getMap();
        }

        @Override
        public Map<K, V> getConfirmedMap() {
            // assumes makeFullMapIterator() called first
            return Flat3MapTest_OE25Dev.this.getConfirmed();
        }

        @Override
        public void verify() {
            super.verify();
            Flat3MapTest_OE25Dev.this.verify();
        }
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

//    public void testCreate() throws Exception {
//        resetEmpty();
//        writeExternalFormToDisk(
//            (java.io.Serializable) map,
//            "src/test/resources/data/test/Flat3Map.emptyCollection.version4.obj");
//        resetFull();
//        writeExternalFormToDisk(
//            (java.io.Serializable) map,
//            "src/test/resources/data/test/Flat3Map.fullCollection.version4.obj");
//    }

    public void testCollections261() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        assertEquals( Integer.valueOf(1), m.remove( Integer.valueOf(1) ) );
        assertEquals( Integer.valueOf(0), m.remove( Integer.valueOf(0) ) );

        m.put( Integer.valueOf(2), Integer.valueOf(2) );
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        assertEquals( Integer.valueOf(2), m.remove( Integer.valueOf(2) ) );
        assertEquals( Integer.valueOf(1), m.remove( Integer.valueOf(1) ) );
        assertEquals( Integer.valueOf(0), m.remove( Integer.valueOf(0) ) );
    }

    public void testToString() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        final String string0 = m.toString();
        assertNotNull(string0);
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        final String string1 = m.toString();
        assertNotNull(string1);
        assertNotSame(string0, string1);
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        final String string2 = m.toString();
        assertNotNull(string2);
        assertNotSame(string0, string2);
        assertNotSame(string1, string2);
        m.put( Integer.valueOf(2), Integer.valueOf(2) );
        final String string3 = m.toString();
        assertNotNull(string3);
        assertNotSame(string0, string3);
        assertNotSame(string1, string3);
        assertNotSame(string2, string3);
    }

    public void testRemove1() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        // object is not existing
        Object obj = m.remove(44);
        assertNull(obj);

        m.put(ONE, ONE);
        obj = m.remove(ONE);
        assertSame(ONE, obj);
        assertEquals(0, m.size());

        // after removal, be no longer there
        obj = m.get(ONE);
        assertNull(obj);

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(ONE);
        assertSame(ONE, obj);

        obj = m.get(ONE);
        assertNull(obj);
        obj = m.get(TWO);
        assertSame(TWO, obj);
    }

    public void testRemove2() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(ONE);
        assertSame(ONE, obj);

        obj = m.get(ONE);
        assertNull(obj);
        obj = m.get(TWO);
        assertSame(TWO, obj);
        obj = m.get(THREE);
        assertSame(THREE, obj);
    }

    public void testRemove3() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(TWO);
        assertSame(TWO, obj);

        obj = m.get(ONE);
        assertSame(ONE, obj);
        obj = m.get(TWO);
        assertNull(obj);
        obj = m.get(THREE);
        assertSame(THREE, obj);
    }

    public void testRemove4() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(THREE);
        assertSame(THREE, obj);

        obj = m.get(ONE);
        assertSame(ONE, obj);
        obj = m.get(TWO);
        assertSame(TWO, obj);
        obj = m.get(THREE);
        assertNull(obj);
    }

    public void testRemove5() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(null, ONE);

        obj = m.remove(null);
        assertSame(ONE, obj);

        obj = m.get(null);
        assertNull(obj);
    }

    public void testRemove6() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(null, TWO);

        obj = m.remove(null);
        assertSame(TWO, obj);

        obj = m.get(ONE);
        assertSame(ONE, obj);
        obj = m.get(null);
        assertNull(obj);
    }

    public void testRemove7() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(null, ONE);
        m.put(TWO, TWO);

        obj = m.remove(null);
        assertSame(ONE, obj);

        obj = m.get(null);
        assertNull(obj);
        obj = m.get(TWO);
        assertSame(TWO, obj);
    }

    public void testRemove8() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(null, THREE);

        obj = m.remove(null);
        assertSame(THREE, obj);

        obj = m.get(ONE);
        assertSame(ONE, obj);
        obj = m.get(TWO);
        assertSame(TWO, obj);
        obj = m.get(null);
        assertNull(obj);
    }

    public void testRemove9() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);

        obj = m.remove(null);
        assertNull(obj);
    }

    public void testRemove10() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);

        obj = m.remove(null);
        assertNull(obj);
    }

    public void testRemove11() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(null);
        assertNull(obj);
    }

    public void testRemove12() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(42);
        assertNull(obj);
    }

    public void testRemove13() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);

        obj = m.remove(42);
        assertNull(obj);
    }

    public void testNewInstance1() {
        final Map<Integer, Integer> orig = new HashMap<>();
        orig.put(ONE, ONE);
        orig.put(TWO, TWO);

        final Flat3Map<Integer, Integer> m = new Flat3Map<>(orig);

        assertEquals(orig, m);
        assertEquals(2, m.size());
    }

    public void testGet1() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(null, ONE);
        obj = m.get(null);
        assertSame(ONE, obj);
    }

    public void testGet2() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(null, TWO);
        obj = m.get(null);
        assertSame(TWO, obj);
    }

    public void testGet3() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(null, THREE);
        obj = m.get(null);
        assertSame(THREE, obj);
    }

    public void testContainsKey1() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(null, THREE);
        final boolean contains = m.containsKey(null);
        assertEquals(true, contains);
    }

    public void testContainsKey2() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(null, TWO);
        final boolean contains = m.containsKey(null);
        assertEquals(true, contains);
    }

    public void testContainsKey3() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(null, ONE);
        final boolean contains = m.containsKey(null);
        assertEquals(true, contains);
    }

    public void testContainsValue1() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, null);
        final boolean contains = m.containsValue(null);
        assertEquals(true, contains);
    }

    public void testContainsValue2() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, null);
        final boolean contains = m.containsValue(null);
        assertEquals(true, contains);
    }

    public void testContainsValue3() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, null);
        final boolean contains = m.containsValue(null);
        assertEquals(true, contains);
    }

    public void testPut1() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(null, THREE);
        final Object old = m.put(null, ONE);
        assertEquals(THREE, old);
        assertEquals(ONE, m.get(null));
    }

    public void testPut2() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(null, THREE);
        final Object old = m.put(null, ONE);
        assertEquals(THREE, old);
        assertEquals(ONE, m.get(null));
    }

    public void testPut3() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(null, THREE);
        final Object old = m.put(null, ONE);
        assertEquals(THREE, old);
        assertEquals(null, m.get(ONE));
    }

    public void testPut4() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);
        final Object old = m.put(THREE, ONE);
        assertEquals(THREE, old);
        assertEquals(ONE, m.get(THREE));
    }

    public void testPut5() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, THREE);
        final Object old = m.put(TWO, ONE);
        assertEquals(THREE, old);
        assertEquals(ONE, m.get(TWO));
    }

    public void testPut6() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, THREE);
        final Object old = m.put(ONE, ONE);
        assertEquals(THREE, old);
        assertEquals(ONE, m.get(ONE));
    }

    public void testClone2_1_oe() {
        final Flat3Map<K, V> map = makeObject();
        assertEquals(0, map.size());
    }

    public void testClone2_2_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        assertEquals(2, map.size());
    }

    public void testClone2_3_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        assertEquals(true, map.containsKey(ONE));
    }

    public void testClone2_4_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        assertEquals(true, map.containsKey(ONE));
    }

    public void testClone2_5_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        assertEquals(TWENTY, map.get(TWO));
    }

    public void testClone2_6_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        assertEquals(TWENTY, map.get(TWO));
    }

    public void testClone2_7_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(2, map.size());
    }

    public void testClone2_8_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(true, map.containsKey(ONE));
    }

    public void testClone2_9_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(true, map.containsKey(TWO));
    }

    public void testClone2_10_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(TWENTY, map.get(TWO));
    }

    public void testClone2_11_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(TWENTY, map.get(TWO));
    }

    public void testClone2_12_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final Flat3Map<K, V> cloned = map.clone();

        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);
        assertEquals(4, map.size());
    }

    public void testClone2_13_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final Flat3Map<K, V> cloned = map.clone();

        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);
        assertEquals(4, map.size());
    }

    public void testClone2_14_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final Flat3Map<K, V> cloned = map.clone();

        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);
        assertEquals(true, map.containsKey(TWENTY));
    }

    public void testClone2_15_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final Flat3Map<K, V> cloned = map.clone();

        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);
        assertEquals(true, map.containsKey(TWENTY));
    }

    public void testClone2_16_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final Flat3Map<K, V> cloned = map.clone();

        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);
        assertEquals(TWENTY, map.get(TWO));
    }

    public void testClone2_17_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final Flat3Map<K, V> cloned = map.clone();

        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);
        assertEquals(TWENTY, map.get(TWO));
    }

    public void testClone4_1_oe() {
        final Flat3Map<K, V> map = makeObject();
        assertEquals(0, map.size());
    }

    public void testClone4_2_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(4, map.size());
    }

    public void testClone4_3_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(4, map.size());
    }

    public void testClone4_4_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(true, map.containsKey(TWENTY));
    }

    public void testClone4_5_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(true, map.containsKey(TWENTY));
    }

    public void testClone4_6_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(true, map.containsKey(TWENTY));
    }

    public void testClone4_7_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(true, map.containsKey(TWENTY));
    }

    public void testClone4_8_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(TWENTY, map.get(TWENTY));
    }

    public void testClone4_9_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(TWENTY, map.get(TWENTY));
    }

    public void testClone4_10_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(TWENTY, map.get(TWENTY));
    }

    public void testClone4_11_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(TWENTY, map.get(TWENTY));
    }

    public void testClone4_12_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();

        map.clear();
        assertEquals(0, map.size());
    }

    public void testClone4_13_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();

        map.clear();
        assertEquals(0, map.size());
    }

    public void testClone4_14_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();

        map.clear();
        assertEquals(false, map.containsKey(ONE));
    }

    public void testClone4_15_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();

        map.clear();
        assertEquals(false, map.containsKey(ONE));
    }

    public void testClone4_16_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();

        map.clear();
        assertEquals(false, map.containsKey(ONE));
    }

    public void testClone4_17_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();

        map.clear();
        assertEquals(false, map.containsKey(ONE));
    }

    public void testClone4_18_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();

        map.clear();
        assertEquals(TWENTY, map.get(TWENTY));
    }

    public void testClone4_19_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();

        map.clear();
        assertEquals(TWENTY, map.get(TWO));
    }

    public void testClone4_20_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();

        map.clear();
        assertEquals(TWENTY, map.get(TWO));
    }

    public void testClone4_21_oe() {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final Flat3Map<K, V> cloned = map.clone();

        map.clear();
        assertEquals(TWENTY, map.get(TWO));
    }

    public void testSerialisation0_1_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(0, ser.size());
    }

    public void testSerialisation0_2_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(0, ser.size());
    }

    public void testSerialisation2_1_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(2, map.size());
    }

    public void testSerialisation2_2_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(2, map.size());
    }

    public void testSerialisation2_3_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(true, map.containsKey(ONE));
    }

    public void testSerialisation2_4_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(true, map.containsKey(ONE));
    }

    public void testSerialisation2_5_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(TWENTY, map.get(TWO));
    }

    public void testSerialisation2_6_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(TWENTY, map.get(TWO));
    }

    public void testSerialisation4_1_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(4, map.size());
    }

    public void testSerialisation4_2_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(4, map.size());
    }

    public void testSerialisation4_3_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(true, map.containsKey(TWENTY));
    }

    public void testSerialisation4_4_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(true, map.containsKey(TWENTY));
    }

    public void testSerialisation4_5_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(true, map.containsKey(TWENTY));
    }

    public void testSerialisation4_6_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(true, map.containsKey(TWENTY));
    }

    public void testSerialisation4_7_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(TWENTY, map.get(TWENTY));
    }

    public void testSerialisation4_8_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(TWENTY, map.get(TWENTY));
    }

    public void testSerialisation4_9_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(TWENTY, map.get(TWENTY));
    }

    public void testSerialisation4_10_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(map);
        final byte[] bytes = bout.toByteArray();
        out.close();
        final ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        final ObjectInputStream in = new ObjectInputStream(bin);
        final Flat3Map<?, ?> ser = (Flat3Map<?, ?>) in.readObject();
        in.close();
        assertEquals(TWENTY, map.get(TWENTY));
    }

    public void testEntryIteratorSetValue1_1_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals(3, map.size());
    }

    public void testEntryIteratorSetValue1_2_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals(true, map.containsKey(ONE));
    }

    public void testEntryIteratorSetValue1_3_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals(true, map.containsKey(ONE));
    }

    public void testEntryIteratorSetValue1_4_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals(true, map.containsKey(ONE));
    }

    public void testEntryIteratorSetValue1_5_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testEntryIteratorSetValue1_6_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testEntryIteratorSetValue1_7_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testEntryIteratorSetValue2_1_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        it.next();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals(3, map.size());
    }

    public void testEntryIteratorSetValue2_2_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        it.next();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals(true, map.containsKey(TWO));
    }

    public void testEntryIteratorSetValue2_3_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        it.next();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals(true, map.containsKey(TWO));
    }

    public void testEntryIteratorSetValue2_4_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        it.next();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals(true, map.containsKey(TWO));
    }

    public void testEntryIteratorSetValue2_5_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        it.next();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testEntryIteratorSetValue2_6_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        it.next();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testEntryIteratorSetValue2_7_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        it.next();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testEntryIteratorSetValue3_1_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        it.next();
        it.next();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals(3, map.size());
    }

    public void testEntryIteratorSetValue3_2_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        it.next();
        it.next();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals(true, map.containsKey(TWO));
    }

    public void testEntryIteratorSetValue3_3_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        it.next();
        it.next();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals(true, map.containsKey(THREE));
    }

    public void testEntryIteratorSetValue3_4_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        it.next();
        it.next();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals(true, map.containsKey(TWO));
    }

    public void testEntryIteratorSetValue3_5_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        it.next();
        it.next();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testEntryIteratorSetValue3_6_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        it.next();
        it.next();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testEntryIteratorSetValue3_7_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        it.next();
        it.next();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testMapIteratorSetValue1_1_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals(3, map.size());
    }

    public void testMapIteratorSetValue1_2_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals(true, map.containsKey(ONE));
    }

    public void testMapIteratorSetValue1_3_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals(true, map.containsKey(TWO));
    }

    public void testMapIteratorSetValue1_4_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals(true, map.containsKey(TWO));
    }

    public void testMapIteratorSetValue1_5_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testMapIteratorSetValue1_6_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testMapIteratorSetValue1_7_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testMapIteratorSetValue2_1_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals(3, map.size());
    }

    public void testMapIteratorSetValue2_2_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals(false, it.containsKey());
    }

    public void testMapIteratorSetValue2_3_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals(false, it.containsKey());
    }

    public void testMapIteratorSetValue2_4_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals(false, it.containsKey());
    }

    public void testMapIteratorSetValue2_5_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testMapIteratorSetValue2_6_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testMapIteratorSetValue2_7_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testMapIteratorSetValue3_1_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.next();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals(3, map.size());
    }

    public void testMapIteratorSetValue3_2_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.next();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals(false, it.containsKey());
    }

    public void testMapIteratorSetValue3_3_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.next();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals(false, it.containsKey());
    }

    public void testMapIteratorSetValue3_4_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.next();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals(false, it.containsKey());
    }

    public void testMapIteratorSetValue3_5_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.next();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testMapIteratorSetValue3_6_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.next();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testMapIteratorSetValue3_7_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.next();
        it.next();
        it.setValue((V) "NewValue");
        assertEquals("NewValue", map.get(TWO));
    }

    public void testToString_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        final String string0 = m.toString();
        assertEquals(0, m.size());
    }

    public void testRemove1_3_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        Object obj = m.remove(44);

        m.put(ONE, ONE);
        obj = m.remove(ONE);
        assertEquals(1, m.size());
    }

    public void testRemove3_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(TWO);

        obj = m.get(ONE);
        assertEquals(1, m.size());
    }

    public void testRemove4_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(THREE);

        obj = m.get(ONE);
        assertEquals(1, m.size());
    }

    public void testRemove6_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(null, TWO);

        obj = m.remove(null);

        obj = m.get(ONE);
        assertEquals(1, m.size());
    }

    public void testNewInstance1_2_oe() {
        final Map<Integer, Integer> orig = new HashMap<>();
        orig.put(ONE, ONE);
        orig.put(TWO, TWO);

        final Flat3Map<Integer, Integer> m = new Flat3Map<>(orig);

        assertEquals(2, m.size());
    }

    public void testGet1_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(null, ONE);
        obj = m.get(null);
        assertEquals(1, m.size());
    }

    public void testGet2_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(null, TWO);
        obj = m.get(null);
        assertEquals(2, m.size());
    }

    public void testContainsKey1_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(null, THREE);
        final boolean contains = m.containsKey(null);
        assertEquals(true, contains);
    }

    public void testContainsKey2_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(null, TWO);
        final boolean contains = m.containsKey(null);
        assertEquals(true, contains);
    }

    public void testContainsKey3_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(null, ONE);
        final boolean contains = m.containsKey(null);
        assertEquals(true, contains);
    }

    public void testContainsValue1_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, null);
        final boolean contains = m.containsValue(null);
        assertEquals(true, contains);
    }

    public void testContainsValue2_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, null);
        final boolean contains = m.containsValue(null);
        assertEquals(true, contains);
    }

    public void testContainsValue3_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, null);
        final boolean contains = m.containsValue(null);
        assertEquals(true, contains);
    }

    public void testPut1_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(null, THREE);
        final Object old = m.put(null, ONE);
        assertEquals(Integer.valueOf(1), m.get(null));
    }

    public void testPut2_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(null, THREE);
        final Object old = m.put(null, ONE);
        assertEquals(Integer.valueOf(1), m.get(null));
    }

    public void testPut3_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(null, THREE);
        final Object old = m.put(null, ONE);
        assertEquals(Integer.valueOf(1), m.get(null));
    }

    public void testPut4_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);
        final Object old = m.put(THREE, ONE);
        assertEquals(Integer.valueOf(1), m.get(THREE));
    }

    public void testPut5_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, THREE);
        final Object old = m.put(TWO, ONE);
        assertEquals(Integer.valueOf(1), m.get(TWO));
    }

    public void testPut6_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, THREE);
        final Object old = m.put(ONE, ONE);
        assertEquals(THREE, m.get(ONE));
    }

}
