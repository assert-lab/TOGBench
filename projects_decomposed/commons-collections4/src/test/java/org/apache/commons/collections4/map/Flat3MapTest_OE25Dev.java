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
        return BulkTest.makeSuite(Flat3MapTest.class);
    }

    @Override
    public Flat3Map<K, V> makeObject() {
        return new Flat3Map<>();
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

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
            return Flat3MapTest.this.getNewSampleValues();
        }

        @Override
        public boolean supportsRemove() {
            return Flat3MapTest.this.isRemoveSupported();
        }

        @Override
        public boolean supportsSetValue() {
            return Flat3MapTest.this.isSetValueSupported();
        }

        @Override
        public MapIterator<K, V> makeEmptyIterator() {
            resetEmpty();
            return Flat3MapTest.this.getMap().mapIterator();
        }

        @Override
        public MapIterator<K, V> makeObject() {
            resetFull();
            return Flat3MapTest.this.getMap().mapIterator();
        }

        @Override
        public IterableMap<K, V> getMap() {
            // assumes makeFullMapIterator() called first
            return Flat3MapTest.this.getMap();
        }

        @Override
        public Map<K, V> getConfirmedMap() {
            // assumes makeFullMapIterator() called first
            return Flat3MapTest.this.getConfirmed();
        }

        @Override
        public void verify() {
            super.verify();
            Flat3MapTest.this.verify();
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

    public void testEquals1_1_oe() {
        final Flat3Map<K, V> map1 = makeObject();
        map1.put((K) "a", (V) "testA");
        map1.put((K) "b", (V) "testB");
        final Flat3Map<K, V> map2 = makeObject();
        map2.put((K) "a", (V) "testB");
        map2.put((K) "b", (V) "testA");
        assertEquals(false, map1.equals(map2));
    }

    public void testEquals2_1_oe() {
        final Flat3Map<K, V> map1 = makeObject();
        map1.put((K) "a", (V) "testA");
        map1.put((K) "b", (V) "testB");
        final Flat3Map<K, V> map2 = makeObject();
        map2.put((K) "a", (V) "testB");
        map2.put((K) "c", (V) "testA");
        assertEquals(false, map1.equals(map2));
    }

    public void testClone2_1_oe() {
        final Flat3Map<K, V> map = makeObject();
        assertEquals(0, map.size());
    }

    public void testClone2_2_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        assertEquals(2, map.size());
    }

    public void testClone2_3_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        // removed other assertion
        assertEquals(true, map.containsKey(ONE));
    }

    public void testClone2_4_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsKey(TWO));
    }

    public void testClone2_5_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TEN, map.get(ONE));
    }

    public void testClone2_6_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TWENTY, map.get(TWO));
    }

    public void testClone2_7_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // clone works (size = 2)
        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(2, cloned.size());
    }

    public void testClone2_8_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // clone works (size = 2)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        assertEquals(true, cloned.containsKey(ONE));
    }

    public void testClone2_9_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // clone works (size = 2)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        assertEquals(true, cloned.containsKey(TWO));
    }

    public void testClone2_10_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // clone works (size = 2)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TEN, cloned.get(ONE));
    }

    public void testClone2_11_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // clone works (size = 2)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TWENTY, cloned.get(TWO));
    }

    public void testClone2_12_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // clone works (size = 2)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // change original doesn't change clone
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);
        assertEquals(4, map.size());
    }

    public void testClone2_13_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // clone works (size = 2)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // change original doesn't change clone
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);
        // removed other assertion
        assertEquals(2, cloned.size());
    }

    public void testClone2_14_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // clone works (size = 2)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // change original doesn't change clone
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);
        // removed other assertion
        // removed other assertion
        assertEquals(true, cloned.containsKey(ONE));
    }

    public void testClone2_15_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // clone works (size = 2)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // change original doesn't change clone
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, cloned.containsKey(TWO));
    }

    public void testClone2_16_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // clone works (size = 2)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // change original doesn't change clone
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TEN, cloned.get(ONE));
    }

    public void testClone2_17_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // clone works (size = 2)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // change original doesn't change clone
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TWENTY, cloned.get(TWO));
    }

    public void testClone4_1_oe() {
        final Flat3Map<K, V> map = makeObject();
        assertEquals(0, map.size());
    }

    public void testClone4_2_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        assertEquals(4, map.size());
    }

    public void testClone4_3_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        assertEquals(4, cloned.size());
    }

    public void testClone4_4_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        assertEquals(true, cloned.containsKey(ONE));
    }

    public void testClone4_5_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, cloned.containsKey(TWO));
    }

    public void testClone4_6_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, cloned.containsKey(TEN));
    }

    public void testClone4_7_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, cloned.containsKey(TWENTY));
    }

    public void testClone4_8_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TEN, cloned.get(ONE));
    }

    public void testClone4_9_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TWENTY, cloned.get(TWO));
    }

    public void testClone4_10_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(ONE, cloned.get(TEN));
    }

    public void testClone4_11_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TWO, cloned.get(TWENTY));
    }

    public void testClone4_12_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // change original doesn't change clone
        map.clear();
        assertEquals(0, map.size());
    }

    public void testClone4_13_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // change original doesn't change clone
        map.clear();
        // removed other assertion
        assertEquals(4, cloned.size());
    }

    public void testClone4_14_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // change original doesn't change clone
        map.clear();
        // removed other assertion
        // removed other assertion
        assertEquals(true, cloned.containsKey(ONE));
    }

    public void testClone4_15_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // change original doesn't change clone
        map.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, cloned.containsKey(TWO));
    }

    public void testClone4_16_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // change original doesn't change clone
        map.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, cloned.containsKey(TEN));
    }

    public void testClone4_17_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // change original doesn't change clone
        map.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, cloned.containsKey(TWENTY));
    }

    public void testClone4_18_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // change original doesn't change clone
        map.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TEN, cloned.get(ONE));
    }

    public void testClone4_19_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // change original doesn't change clone
        map.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TWENTY, cloned.get(TWO));
    }

    public void testClone4_20_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // change original doesn't change clone
        map.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(ONE, cloned.get(TEN));
    }

    public void testClone4_21_oe() {
        final Flat3Map<K, V> map = makeObject();
        // removed other assertion
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) TEN, (V) ONE);
        map.put((K) TWENTY, (V) TWO);

        // clone works (size = 4)
        final Flat3Map<K, V> cloned = map.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // change original doesn't change clone
        map.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TWO, cloned.get(TWENTY));
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
        assertEquals(0, map.size());
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
        // removed other assertion
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
        // removed other assertion
        assertEquals(2, ser.size());
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
        // removed other assertion
        // removed other assertion
        assertEquals(true, ser.containsKey(ONE));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, ser.containsKey(TWO));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TEN, ser.get(ONE));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TWENTY, ser.get(TWO));
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
        // removed other assertion
        assertEquals(4, ser.size());
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
        // removed other assertion
        // removed other assertion
        assertEquals(true, ser.containsKey(ONE));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, ser.containsKey(TWO));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, ser.containsKey(TEN));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, ser.containsKey(TWENTY));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TEN, ser.get(ONE));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TWENTY, ser.get(TWO));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ONE, ser.get(TEN));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TWO, ser.get(TWENTY));
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
        // removed other assertion
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
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsKey(TWO));
    }

    public void testEntryIteratorSetValue1_4_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsKey(THREE));
    }

    public void testEntryIteratorSetValue1_5_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("NewValue", map.get(ONE));
    }

    public void testEntryIteratorSetValue1_6_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TWENTY, map.get(TWO));
    }

    public void testEntryIteratorSetValue1_7_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        final Map.Entry<K, V> entry = it.next();
        entry.setValue((V) "NewValue");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(THIRTY, map.get(THREE));
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
        // removed other assertion
        assertEquals(true, map.containsKey(ONE));
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
        // removed other assertion
        // removed other assertion
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsKey(THREE));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TEN, map.get(ONE));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(THIRTY, map.get(THREE));
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
        // removed other assertion
        assertEquals(true, map.containsKey(ONE));
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
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsKey(TWO));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsKey(THREE));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TEN, map.get(ONE));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TWENTY, map.get(TWO));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("NewValue", map.get(THREE));
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
        // removed other assertion
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
        // removed other assertion
        // removed other assertion
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsKey(THREE));
    }

    public void testMapIteratorSetValue1_5_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.setValue((V) "NewValue");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("NewValue", map.get(ONE));
    }

    public void testMapIteratorSetValue1_6_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.setValue((V) "NewValue");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TWENTY, map.get(TWO));
    }

    public void testMapIteratorSetValue1_7_oe() throws Exception {
        final Flat3Map<K, V> map = makeObject();
        map.put((K) ONE, (V) TEN);
        map.put((K) TWO, (V) TWENTY);
        map.put((K) THREE, (V) THIRTY);

        final MapIterator<K, V> it = map.mapIterator();
        it.next();
        it.setValue((V) "NewValue");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(THIRTY, map.get(THREE));
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
        // removed other assertion
        assertEquals(true, map.containsKey(ONE));
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
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsKey(TWO));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsKey(THREE));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TEN, map.get(ONE));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(THIRTY, map.get(THREE));
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
        // removed other assertion
        assertEquals(true, map.containsKey(ONE));
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
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsKey(TWO));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, map.containsKey(THREE));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TEN, map.get(ONE));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(TWENTY, map.get(TWO));
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("NewValue", map.get(THREE));
    }

    public void testCollections261_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        assertEquals( Integer.valueOf(1), m.remove( Integer.valueOf(1) ) );
    }

    public void testCollections261_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        // removed other assertion
        assertEquals( Integer.valueOf(0), m.remove( Integer.valueOf(0) ) );
    }

    public void testCollections261_3_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        // removed other assertion
        // removed other assertion

        m.put( Integer.valueOf(2), Integer.valueOf(2) );
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        assertEquals( Integer.valueOf(2), m.remove( Integer.valueOf(2) ) );
    }

    public void testCollections261_4_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        // removed other assertion
        // removed other assertion

        m.put( Integer.valueOf(2), Integer.valueOf(2) );
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        // removed other assertion
        assertEquals( Integer.valueOf(1), m.remove( Integer.valueOf(1) ) );
    }

    public void testCollections261_5_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        // removed other assertion
        // removed other assertion

        m.put( Integer.valueOf(2), Integer.valueOf(2) );
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        // removed other assertion
        // removed other assertion
        assertEquals( Integer.valueOf(0), m.remove( Integer.valueOf(0) ) );
    }

    public void testToString_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        final String string0 = m.toString();
        assertNotNull(string0);
    }

    public void testToString_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        final String string0 = m.toString();
        // removed other assertion
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        final String string1 = m.toString();
        assertNotNull(string1);
    }

    public void testToString_3_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        final String string0 = m.toString();
        // removed other assertion
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        final String string1 = m.toString();
        // removed other assertion
        assertNotSame(string0, string1);
    }

    public void testToString_4_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        final String string0 = m.toString();
        // removed other assertion
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        final String string1 = m.toString();
        // removed other assertion
        // removed other assertion
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        final String string2 = m.toString();
        assertNotNull(string2);
    }

    public void testToString_5_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        final String string0 = m.toString();
        // removed other assertion
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        final String string1 = m.toString();
        // removed other assertion
        // removed other assertion
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        final String string2 = m.toString();
        // removed other assertion
        assertNotSame(string0, string2);
    }

    public void testToString_6_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        final String string0 = m.toString();
        // removed other assertion
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        final String string1 = m.toString();
        // removed other assertion
        // removed other assertion
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        final String string2 = m.toString();
        // removed other assertion
        // removed other assertion
        assertNotSame(string1, string2);
    }

    public void testToString_7_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        final String string0 = m.toString();
        // removed other assertion
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        final String string1 = m.toString();
        // removed other assertion
        // removed other assertion
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        final String string2 = m.toString();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        m.put( Integer.valueOf(2), Integer.valueOf(2) );
        final String string3 = m.toString();
        assertNotNull(string3);
    }

    public void testToString_8_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        final String string0 = m.toString();
        // removed other assertion
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        final String string1 = m.toString();
        // removed other assertion
        // removed other assertion
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        final String string2 = m.toString();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        m.put( Integer.valueOf(2), Integer.valueOf(2) );
        final String string3 = m.toString();
        // removed other assertion
        assertNotSame(string0, string3);
    }

    public void testToString_9_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        final String string0 = m.toString();
        // removed other assertion
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        final String string1 = m.toString();
        // removed other assertion
        // removed other assertion
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        final String string2 = m.toString();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        m.put( Integer.valueOf(2), Integer.valueOf(2) );
        final String string3 = m.toString();
        // removed other assertion
        // removed other assertion
        assertNotSame(string1, string3);
    }

    public void testToString_10_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        final String string0 = m.toString();
        // removed other assertion
        m.put( Integer.valueOf(1), Integer.valueOf(1) );
        final String string1 = m.toString();
        // removed other assertion
        // removed other assertion
        m.put( Integer.valueOf(0), Integer.valueOf(0) );
        final String string2 = m.toString();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        m.put( Integer.valueOf(2), Integer.valueOf(2) );
        final String string3 = m.toString();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame(string2, string3);
    }

    public void testRemove1_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        // object is not existing
        Object obj = m.remove(44);
        assertNull(obj);
    }

    public void testRemove1_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        // object is not existing
        Object obj = m.remove(44);
        // removed other assertion

        m.put(ONE, ONE);
        obj = m.remove(ONE);
        assertSame(ONE, obj);
    }

    public void testRemove1_3_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        // object is not existing
        Object obj = m.remove(44);
        // removed other assertion

        m.put(ONE, ONE);
        obj = m.remove(ONE);
        // removed other assertion
        assertEquals(0, m.size());
    }

    public void testRemove1_4_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        // object is not existing
        Object obj = m.remove(44);
        // removed other assertion

        m.put(ONE, ONE);
        obj = m.remove(ONE);
        // removed other assertion
        // removed other assertion

        // after removal, be no longer there
        obj = m.get(ONE);
        assertNull(obj);
    }

    public void testRemove1_5_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        // object is not existing
        Object obj = m.remove(44);
        // removed other assertion

        m.put(ONE, ONE);
        obj = m.remove(ONE);
        // removed other assertion
        // removed other assertion

        // after removal, be no longer there
        obj = m.get(ONE);
        // removed other assertion

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(ONE);
        assertSame(ONE, obj);
    }

    public void testRemove1_6_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        // object is not existing
        Object obj = m.remove(44);
        // removed other assertion

        m.put(ONE, ONE);
        obj = m.remove(ONE);
        // removed other assertion
        // removed other assertion

        // after removal, be no longer there
        obj = m.get(ONE);
        // removed other assertion

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(ONE);
        // removed other assertion

        obj = m.get(ONE);
        assertNull(obj);
    }

    public void testRemove1_7_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        // object is not existing
        Object obj = m.remove(44);
        // removed other assertion

        m.put(ONE, ONE);
        obj = m.remove(ONE);
        // removed other assertion
        // removed other assertion

        // after removal, be no longer there
        obj = m.get(ONE);
        // removed other assertion

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(ONE);
        // removed other assertion

        obj = m.get(ONE);
        // removed other assertion
        obj = m.get(TWO);
        assertSame(TWO, obj);
    }

    public void testRemove2_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(ONE);
        assertSame(ONE, obj);
    }

    public void testRemove2_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(ONE);
        // removed other assertion

        obj = m.get(ONE);
        assertNull(obj);
    }

    public void testRemove2_3_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(ONE);
        // removed other assertion

        obj = m.get(ONE);
        // removed other assertion
        obj = m.get(TWO);
        assertSame(TWO, obj);
    }

    public void testRemove2_4_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(ONE);
        // removed other assertion

        obj = m.get(ONE);
        // removed other assertion
        obj = m.get(TWO);
        // removed other assertion
        obj = m.get(THREE);
        assertSame(THREE, obj);
    }

    public void testRemove3_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(TWO);
        assertSame(TWO, obj);
    }

    public void testRemove3_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(TWO);
        // removed other assertion

        obj = m.get(ONE);
        assertSame(ONE, obj);
    }

    public void testRemove3_3_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(TWO);
        // removed other assertion

        obj = m.get(ONE);
        // removed other assertion
        obj = m.get(TWO);
        assertNull(obj);
    }

    public void testRemove3_4_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(TWO);
        // removed other assertion

        obj = m.get(ONE);
        // removed other assertion
        obj = m.get(TWO);
        // removed other assertion
        obj = m.get(THREE);
        assertSame(THREE, obj);
    }

    public void testRemove4_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(THREE);
        assertSame(THREE, obj);
    }

    public void testRemove4_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(THREE);
        // removed other assertion

        obj = m.get(ONE);
        assertSame(ONE, obj);
    }

    public void testRemove4_3_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(THREE);
        // removed other assertion

        obj = m.get(ONE);
        // removed other assertion
        obj = m.get(TWO);
        assertSame(TWO, obj);
    }

    public void testRemove4_4_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(THREE);
        // removed other assertion

        obj = m.get(ONE);
        // removed other assertion
        obj = m.get(TWO);
        // removed other assertion
        obj = m.get(THREE);
        assertNull(obj);
    }

    public void testRemove5_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(null, ONE);

        obj = m.remove(null);
        assertSame(ONE, obj);
    }

    public void testRemove5_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(null, ONE);

        obj = m.remove(null);
        // removed other assertion

        obj = m.get(null);
        assertNull(obj);
    }

    public void testRemove6_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(null, TWO);

        obj = m.remove(null);
        assertSame(TWO, obj);
    }

    public void testRemove6_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(null, TWO);

        obj = m.remove(null);
        // removed other assertion

        obj = m.get(ONE);
        assertSame(ONE, obj);
    }

    public void testRemove6_3_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(null, TWO);

        obj = m.remove(null);
        // removed other assertion

        obj = m.get(ONE);
        // removed other assertion
        obj = m.get(null);
        assertNull(obj);
    }

    public void testRemove7_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(null, ONE);
        m.put(TWO, TWO);

        obj = m.remove(null);
        assertSame(ONE, obj);
    }

    public void testRemove7_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(null, ONE);
        m.put(TWO, TWO);

        obj = m.remove(null);
        // removed other assertion

        obj = m.get(null);
        assertNull(obj);
    }

    public void testRemove7_3_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(null, ONE);
        m.put(TWO, TWO);

        obj = m.remove(null);
        // removed other assertion

        obj = m.get(null);
        // removed other assertion
        obj = m.get(TWO);
        assertSame(TWO, obj);
    }

    public void testRemove8_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(null, THREE);

        obj = m.remove(null);
        assertSame(THREE, obj);
    }

    public void testRemove8_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(null, THREE);

        obj = m.remove(null);
        // removed other assertion

        obj = m.get(ONE);
        assertSame(ONE, obj);
    }

    public void testRemove8_3_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(null, THREE);

        obj = m.remove(null);
        // removed other assertion

        obj = m.get(ONE);
        // removed other assertion
        obj = m.get(TWO);
        assertSame(TWO, obj);
    }

    public void testRemove8_4_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(null, THREE);

        obj = m.remove(null);
        // removed other assertion

        obj = m.get(ONE);
        // removed other assertion
        obj = m.get(TWO);
        // removed other assertion
        obj = m.get(null);
        assertNull(obj);
    }

    public void testRemove9_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);

        obj = m.remove(null);
        assertNull(obj);
    }

    public void testRemove10_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);

        obj = m.remove(null);
        assertNull(obj);
    }

    public void testRemove11_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(null);
        assertNull(obj);
    }

    public void testRemove12_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);

        obj = m.remove(42);
        assertNull(obj);
    }

    public void testRemove13_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);

        obj = m.remove(42);
        assertNull(obj);
    }

    public void testNewInstance1_1_oe() {
        final Map<Integer, Integer> orig = new HashMap<>();
        orig.put(ONE, ONE);
        orig.put(TWO, TWO);

        final Flat3Map<Integer, Integer> m = new Flat3Map<>(orig);

        assertEquals(orig, m);
    }

    public void testNewInstance1_2_oe() {
        final Map<Integer, Integer> orig = new HashMap<>();
        orig.put(ONE, ONE);
        orig.put(TWO, TWO);

        final Flat3Map<Integer, Integer> m = new Flat3Map<>(orig);

        // removed other assertion
        assertEquals(2, m.size());
    }

    public void testGet1_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(null, ONE);
        obj = m.get(null);
        assertSame(ONE, obj);
    }

    public void testGet2_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(null, TWO);
        obj = m.get(null);
        assertSame(TWO, obj);
    }

    public void testGet3_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();
        Object obj;

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(null, THREE);
        obj = m.get(null);
        assertSame(THREE, obj);
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

    public void testPut1_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(null, THREE);
        final Object old = m.put(null, ONE);
        assertEquals(THREE, old);
    }

    public void testPut1_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(null, THREE);
        final Object old = m.put(null, ONE);
        // removed other assertion
        assertEquals(ONE, m.get(null));
    }

    public void testPut2_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(null, THREE);
        final Object old = m.put(null, ONE);
        assertEquals(THREE, old);
    }

    public void testPut2_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(null, THREE);
        final Object old = m.put(null, ONE);
        // removed other assertion
        assertEquals(ONE, m.get(null));
    }

    public void testPut3_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(null, THREE);
        final Object old = m.put(null, ONE);
        assertEquals(THREE, old);
    }

    public void testPut3_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(null, THREE);
        final Object old = m.put(null, ONE);
        // removed other assertion
        assertEquals(null, m.get(ONE));
    }

    public void testPut4_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);
        final Object old = m.put(THREE, ONE);
        assertEquals(THREE, old);
    }

    public void testPut4_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, TWO);
        m.put(THREE, THREE);
        final Object old = m.put(THREE, ONE);
        // removed other assertion
        assertEquals(ONE, m.get(THREE));
    }

    public void testPut5_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, THREE);
        final Object old = m.put(TWO, ONE);
        assertEquals(THREE, old);
    }

    public void testPut5_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, ONE);
        m.put(TWO, THREE);
        final Object old = m.put(TWO, ONE);
        // removed other assertion
        assertEquals(ONE, m.get(TWO));
    }

    public void testPut6_1_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, THREE);
        final Object old = m.put(ONE, ONE);
        assertEquals(THREE, old);
    }

    public void testPut6_2_oe() {
        final Flat3Map<Integer, Integer> m = new Flat3Map<>();

        m.put(ONE, THREE);
        final Object old = m.put(ONE, ONE);
        // removed other assertion
        assertEquals(ONE, m.get(ONE));
    }

}
