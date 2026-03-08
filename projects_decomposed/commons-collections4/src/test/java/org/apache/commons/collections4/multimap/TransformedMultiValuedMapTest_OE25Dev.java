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

import java.util.Collection;

import junit.framework.Test;

import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.apache.commons.collections4.collection.TransformedCollectionTest;

/**
 * Tests for TransformedMultiValuedMap
 *
 * @since 4.1
 */
public class TransformedMultiValuedMapTest_OE25Dev<K, V> extends AbstractMultiValuedMapTest<K, V> {

    public TransformedMultiValuedMapTest_OE25Dev(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return new junit.framework.TestSuite(TransformedMultiValuedMapTest_OE25Dev.class);
    }

    // -----------------------------------------------------------------------
    @Override
    public MultiValuedMap<K, V> makeObject() {
        return TransformedMultiValuedMap.transformingMap(new ArrayListValuedHashMap<K, V>(),
                TransformerUtils.<K> nopTransformer(), TransformerUtils.<V> nopTransformer());
    }

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

//    public void testCreate() throws Exception {
//        writeExternalFormToDisk((java.io.Serializable) makeObject(),
//                "src/test/resources/data/test/TransformedMultiValuedMap.emptyCollection.version4.1.obj");
//        writeExternalFormToDisk((java.io.Serializable) makeFullMap(),
//                "src/test/resources/data/test/TransformedMultiValuedMap.fullCollection.version4.1.obj");
//    }

    public void testKeyTransformedMap_1_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        final MultiValuedMap<K, V> map = TransformedMultiValuedMap.transformingMap(
                new ArrayListValuedHashMap<K, V>(),
                (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                null);
        assertEquals(0, map.size());
    }

    public void testKeyTransformedMap_2_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        final MultiValuedMap<K, V> map = TransformedMultiValuedMap.transformingMap(
                new ArrayListValuedHashMap<K, V>(),
                (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                null);
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            assertEquals(i + 1, map.size());
    }
    }

    public void testKeyTransformedMap_3_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        final MultiValuedMap<K, V> map = TransformedMultiValuedMap.transformingMap(
                new ArrayListValuedHashMap<K, V>(),
                (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                null);
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            assertEquals(true, map.containsKey(Integer.valueOf((String) els[i])));
    }
    }

    public void testKeyTransformedMap_4_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        final MultiValuedMap<K, V> map = TransformedMultiValuedMap.transformingMap(
                new ArrayListValuedHashMap<K, V>(),
                (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                null);
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            assertEquals(false, map.containsKey(els[i]));
    }
    }

    public void testKeyTransformedMap_5_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        final MultiValuedMap<K, V> map = TransformedMultiValuedMap.transformingMap(
                new ArrayListValuedHashMap<K, V>(),
                (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                null);
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            assertEquals(true, map.containsValue(els[i]));
    }
    }

    public void testKeyTransformedMap_6_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        final MultiValuedMap<K, V> map = TransformedMultiValuedMap.transformingMap(
                new ArrayListValuedHashMap<K, V>(),
                (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                null);
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            assertEquals(true, map.get((K) Integer.valueOf((String) els[i])).contains(els[i]));
    }
    }

    public void testKeyTransformedMap_7_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        final MultiValuedMap<K, V> map = TransformedMultiValuedMap.transformingMap(
                new ArrayListValuedHashMap<K, V>(),
                (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                null);
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
        }

        final Collection<V> coll = map.remove(els[0]);
        assertNotNull(coll);
    }

    public void testKeyTransformedMap_8_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        final MultiValuedMap<K, V> map = TransformedMultiValuedMap.transformingMap(
                new ArrayListValuedHashMap<K, V>(),
                (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                null);
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
        }

        final Collection<V> coll = map.remove(els[0]);
        assertEquals(0, coll.size());
    }

    public void testKeyTransformedMap_9_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        final MultiValuedMap<K, V> map = TransformedMultiValuedMap.transformingMap(
                new ArrayListValuedHashMap<K, V>(),
                (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                null);
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
        }

        final Collection<V> coll = map.remove(els[0]);
        assertEquals(true, map.remove(Integer.valueOf((String) els[0])).contains(els[0]));
    }

    public void testValueTransformedMap_1_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        final MultiValuedMap<K, V> map = TransformedMultiValuedMap.transformingMap(
                new ArrayListValuedHashMap<K, V>(), null,
                (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(0, map.size());
    }

    public void testValueTransformedMap_2_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        final MultiValuedMap<K, V> map = TransformedMultiValuedMap.transformingMap(
                new ArrayListValuedHashMap<K, V>(), null,
                (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            assertEquals(i + 1, map.size());
    }
    }

    public void testValueTransformedMap_3_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        final MultiValuedMap<K, V> map = TransformedMultiValuedMap.transformingMap(
                new ArrayListValuedHashMap<K, V>(), null,
                (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            assertEquals(true, map.containsValue(Integer.valueOf((String) els[i])));
    }
    }

    public void testValueTransformedMap_4_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        final MultiValuedMap<K, V> map = TransformedMultiValuedMap.transformingMap(
                new ArrayListValuedHashMap<K, V>(), null,
                (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            assertEquals(false, map.containsValue(els[i]));
    }
    }

    public void testValueTransformedMap_5_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        final MultiValuedMap<K, V> map = TransformedMultiValuedMap.transformingMap(
                new ArrayListValuedHashMap<K, V>(), null,
                (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            assertEquals(true, map.containsKey(els[i]));
    }
    }

    public void testValueTransformedMap_6_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        final MultiValuedMap<K, V> map = TransformedMultiValuedMap.transformingMap(
                new ArrayListValuedHashMap<K, V>(), null,
                (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            assertEquals(true, map.get((K) els[i]).contains(Integer.valueOf((String) els[i])));
    }
    }

    public void testValueTransformedMap_7_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        final MultiValuedMap<K, V> map = TransformedMultiValuedMap.transformingMap(
                new ArrayListValuedHashMap<K, V>(), null,
                (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
        }
        assertEquals(true, map.remove(els[0]).contains(Integer.valueOf((String) els[0])));
    }

    public void testFactory_Decorate_1_oe() {
        final MultiValuedMap<K, V> base = new ArrayListValuedHashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final MultiValuedMap<K, V> trans = TransformedMultiValuedMap
                .transformingMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(3, trans.size());
    }

    public void testFactory_Decorate_2_oe() {
        final MultiValuedMap<K, V> base = new ArrayListValuedHashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final MultiValuedMap<K, V> trans = TransformedMultiValuedMap
                .transformingMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(true, trans.get((K) "A").contains("1"));
    }

    public void testFactory_Decorate_3_oe() {
        final MultiValuedMap<K, V> base = new ArrayListValuedHashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final MultiValuedMap<K, V> trans = TransformedMultiValuedMap
                .transformingMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(true, trans.get((K) "B").contains("2"));
    }

    public void testFactory_Decorate_4_oe() {
        final MultiValuedMap<K, V> base = new ArrayListValuedHashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final MultiValuedMap<K, V> trans = TransformedMultiValuedMap
                .transformingMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(true, trans.get((K) "C").contains("3"));
    }

    public void testFactory_Decorate_5_oe() {
        final MultiValuedMap<K, V> base = new ArrayListValuedHashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final MultiValuedMap<K, V> trans = TransformedMultiValuedMap
                .transformingMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        trans.put((K) "D", (V) "4");
        assertEquals(true, trans.get((K) "D").contains(Integer.valueOf(4)));
    }

    public void testFactory_decorateTransform_1_oe() {
        final MultiValuedMap<K, V> base = new ArrayListValuedHashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final MultiValuedMap<K, V> trans = TransformedMultiValuedMap
                .transformedMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(3, trans.size());
    }

    public void testFactory_decorateTransform_2_oe() {
        final MultiValuedMap<K, V> base = new ArrayListValuedHashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final MultiValuedMap<K, V> trans = TransformedMultiValuedMap
                .transformedMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(true, trans.get((K) "A").contains(Integer.valueOf(1)));
    }

    public void testFactory_decorateTransform_3_oe() {
        final MultiValuedMap<K, V> base = new ArrayListValuedHashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final MultiValuedMap<K, V> trans = TransformedMultiValuedMap
                .transformedMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(true, trans.get((K) "B").contains(Integer.valueOf(2)));
    }

    public void testFactory_decorateTransform_4_oe() {
        final MultiValuedMap<K, V> base = new ArrayListValuedHashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final MultiValuedMap<K, V> trans = TransformedMultiValuedMap
                .transformedMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(true, trans.get((K) "C").contains(Integer.valueOf(3)));
    }

    public void testFactory_decorateTransform_5_oe() {
        final MultiValuedMap<K, V> base = new ArrayListValuedHashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final MultiValuedMap<K, V> trans = TransformedMultiValuedMap
                .transformedMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        trans.put((K) "D", (V) "4");
        assertEquals(true, trans.get((K) "D").contains(Integer.valueOf(4)));
    }

}
