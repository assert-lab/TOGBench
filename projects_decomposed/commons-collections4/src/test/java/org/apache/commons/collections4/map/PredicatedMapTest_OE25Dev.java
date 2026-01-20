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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.TruePredicate;

/**
 * Extension of {@link AbstractMapTest} for exercising the
 * {@link PredicatedMap} implementation.
 *
 * @since 3.0
 */
public class PredicatedMapTest_OE25Dev<K, V> extends AbstractIterableMapTest<K, V> {

    protected static final Predicate<Object> truePredicate = TruePredicate.<Object>truePredicate();

    protected static final Predicate<Object> testPredicate = new Predicate<Object>() {
        @Override
        public boolean evaluate(final Object o) {
            return o instanceof String;
        }
    };

    public PredicatedMapTest_OE25Dev(final String testName) {
        super(testName);
    }

    //-----------------------------------------------------------------------
    protected IterableMap<K, V> decorateMap(final Map<K, V> map, final Predicate<? super K> keyPredicate,
        final Predicate<? super V> valuePredicate) {
        return PredicatedMap.predicatedMap(map, keyPredicate, valuePredicate);
    }

    @Override
    public IterableMap<K, V> makeObject() {
        return decorateMap(new HashMap<K, V>(), truePredicate, truePredicate);
    }

    public IterableMap<K, V> makeTestMap() {
        return decorateMap(new HashMap<K, V>(), testPredicate, testPredicate);
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
//            "src/test/resources/data/test/PredicatedMap.emptyCollection.version4.obj");
//        resetFull();
//        writeExternalFormToDisk(
//            (java.io.Serializable) map,
//            "src/test/resources/data/test/PredicatedMap.fullCollection.version4.obj");
//    }

    public void testEntrySet_1_oe() {
        Map<K, V> map = makeTestMap();
        assertTrue("returned entryset should not be null", map.entrySet() != null);
    }

    public void testEntrySet_2_oe() {
        Map<K, V> map = makeTestMap();
        // removed other assertion
        map = decorateMap(new HashMap<K, V>(), null, null);
        map.put((K) "oneKey", (V) "oneValue");
        assertTrue("returned entryset should contain one entry", map.entrySet().size() == 1);
    }

    public void testPut_3_oe() {
        final Map<K, V> map = makeTestMap();
        try {
            map.put((K) "Hi", (V) Integer.valueOf(3));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // expected
        }

        try {
            map.put((K) Integer.valueOf(3), (V) "Hi");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // expected
        }

        assertTrue(!map.containsKey(Integer.valueOf(3)));
    }

    public void testPut_4_oe() {
        final Map<K, V> map = makeTestMap();
        try {
            map.put((K) "Hi", (V) Integer.valueOf(3));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // expected
        }

        try {
            map.put((K) Integer.valueOf(3), (V) "Hi");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // expected
        }

        // removed other assertion
        assertTrue(!map.containsValue(Integer.valueOf(3)));
    }

}
