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
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.apache.commons.collections4.collection.TransformedCollectionTest;

/**
 * Extension of {@link AbstractMapTest} for exercising the {@link TransformedMap}
 * implementation.
 *
 * @since 3.0
 */
public class TransformedMapTest_OE25Dev<K, V> extends AbstractIterableMapTest<K, V> {

    public TransformedMapTest_OE25Dev(final String testName) {
        super(testName);
    }

    //-----------------------------------------------------------------------
    @Override
    public IterableMap<K, V> makeObject() {
        return TransformedMap.transformingMap(new HashMap<K, V>(), TransformerUtils.<K> nopTransformer(),
                TransformerUtils.<V> nopTransformer());
    }

    //-----------------------------------------------------------------------

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
//            "src/test/resources/data/test/TransformedMap.emptyCollection.version4.obj");
//        resetFull();
//        writeExternalFormToDisk(
//            (java.io.Serializable) map,
//            "src/test/resources/data/test/TransformedMap.fullCollection.version4.obj");
//    }

    public void testTransformedMap_1_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        assertEquals(0, map.size());
    }

    public void testTransformedMap_2_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            assertEquals(i + 1, map.size());
    }
    }

    public void testTransformedMap_3_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            assertEquals(true, map.containsKey(Integer.valueOf((String) els[i])));
    }
    }

    public void testTransformedMap_4_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            assertEquals(false, map.containsKey(els[i]));
    }
    }

    public void testTransformedMap_5_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(true, map.containsValue(els[i]));
    }
    }

    public void testTransformedMap_6_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(els[i], map.get(Integer.valueOf((String) els[i])));
    }
    }

    public void testTransformedMap_7_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        assertEquals(null, map.remove(els[0]));
    }

    public void testTransformedMap_8_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        assertEquals(els[0], map.remove(Integer.valueOf((String) els[0])));
    }

    public void testTransformedMap_9_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        map = TransformedMap.transformingMap(new HashMap(), null,
                                             // cast needed for eclipse compiler
                                             (Transformer) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(0, map.size());
    }

    public void testTransformedMap_10_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        map = TransformedMap.transformingMap(new HashMap(), null,
                                             // cast needed for eclipse compiler
                                             (Transformer) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            assertEquals(i + 1, map.size());
    }
    }

    public void testTransformedMap_11_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        map = TransformedMap.transformingMap(new HashMap(), null,
                                             // cast needed for eclipse compiler
                                             (Transformer) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            assertEquals(true, map.containsValue(Integer.valueOf((String) els[i])));
    }
    }

    public void testTransformedMap_12_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        map = TransformedMap.transformingMap(new HashMap(), null,
                                             // cast needed for eclipse compiler
                                             (Transformer) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            assertEquals(false, map.containsValue(els[i]));
    }
    }

    public void testTransformedMap_13_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        map = TransformedMap.transformingMap(new HashMap(), null,
                                             // cast needed for eclipse compiler
                                             (Transformer) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(true, map.containsKey(els[i]));
    }
    }

    public void testTransformedMap_14_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        map = TransformedMap.transformingMap(new HashMap(), null,
                                             // cast needed for eclipse compiler
                                             (Transformer) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(Integer.valueOf((String) els[i]), map.get(els[i]));
    }
    }

    public void testTransformedMap_15_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        map = TransformedMap.transformingMap(new HashMap(), null,
                                             // cast needed for eclipse compiler
                                             (Transformer) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        assertEquals(Integer.valueOf((String) els[0]), map.remove(els[0]));
    }

    public void testTransformedMap_16_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        map = TransformedMap.transformingMap(new HashMap(), null,
                                             // cast needed for eclipse compiler
                                             (Transformer) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion

        final Set<Map.Entry<K, V>> entrySet = map.entrySet();
        final Map.Entry<K, V>[] array = entrySet.toArray(new Map.Entry[0]);
        array[0].setValue((V) "66");
        assertEquals(Integer.valueOf(66), array[0].getValue());
    }

    public void testTransformedMap_17_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        map = TransformedMap.transformingMap(new HashMap(), null,
                                             // cast needed for eclipse compiler
                                             (Transformer) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion

        final Set<Map.Entry<K, V>> entrySet = map.entrySet();
        final Map.Entry<K, V>[] array = entrySet.toArray(new Map.Entry[0]);
        array[0].setValue((V) "66");
        // removed other assertion
        assertEquals(Integer.valueOf(66), map.get(array[0].getKey()));
    }

    public void testTransformedMap_18_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        map = TransformedMap.transformingMap(new HashMap(), null,
                                             // cast needed for eclipse compiler
                                             (Transformer) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion

        final Set<Map.Entry<K, V>> entrySet = map.entrySet();
        final Map.Entry<K, V>[] array = entrySet.toArray(new Map.Entry[0]);
        array[0].setValue((V) "66");
        // removed other assertion
        // removed other assertion

        final Map.Entry<K, V> entry = entrySet.iterator().next();
        entry.setValue((V) "88");
        assertEquals(Integer.valueOf(88), entry.getValue());
    }

    public void testTransformedMap_19_oe() {
        final Object[] els = new Object[] { "1", "3", "5", "7", "2", "4", "6" };

        Map<K, V> map = TransformedMap
                .transformingMap(
                        new HashMap<K, V>(),
                        (Transformer<? super K, ? extends K>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER,
                        null);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        // removed other assertion

        map = TransformedMap.transformingMap(new HashMap(), null,
                                             // cast needed for eclipse compiler
                                             (Transformer) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        for (int i = 0; i < els.length; i++) {
            map.put((K) els[i], (V) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion

        final Set<Map.Entry<K, V>> entrySet = map.entrySet();
        final Map.Entry<K, V>[] array = entrySet.toArray(new Map.Entry[0]);
        array[0].setValue((V) "66");
        // removed other assertion
        // removed other assertion

        final Map.Entry<K, V> entry = entrySet.iterator().next();
        entry.setValue((V) "88");
        // removed other assertion
        assertEquals(Integer.valueOf(88), map.get(entry.getKey()));
    }

    public void testFactory_Decorate_1_oe() {
        final Map<K, V> base = new HashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final Map<K, V> trans = TransformedMap
                .transformingMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(3, trans.size());
    }

    public void testFactory_Decorate_2_oe() {
        final Map<K, V> base = new HashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final Map<K, V> trans = TransformedMap
                .transformingMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        assertEquals("1", trans.get("A"));
    }

    public void testFactory_Decorate_3_oe() {
        final Map<K, V> base = new HashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final Map<K, V> trans = TransformedMap
                .transformingMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        // removed other assertion
        assertEquals("2", trans.get("B"));
    }

    public void testFactory_Decorate_4_oe() {
        final Map<K, V> base = new HashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final Map<K, V> trans = TransformedMap
                .transformingMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", trans.get("C"));
    }

    public void testFactory_Decorate_5_oe() {
        final Map<K, V> base = new HashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final Map<K, V> trans = TransformedMap
                .transformingMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trans.put((K) "D", (V) "4");
        assertEquals(Integer.valueOf(4), trans.get("D"));
    }

    public void testFactory_decorateTransform_1_oe() {
        final Map<K, V> base = new HashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final Map<K, V> trans = TransformedMap
                .transformedMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(3, trans.size());
    }

    public void testFactory_decorateTransform_2_oe() {
        final Map<K, V> base = new HashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final Map<K, V> trans = TransformedMap
                .transformedMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        assertEquals(Integer.valueOf(1), trans.get("A"));
    }

    public void testFactory_decorateTransform_3_oe() {
        final Map<K, V> base = new HashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final Map<K, V> trans = TransformedMap
                .transformedMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(2), trans.get("B"));
    }

    public void testFactory_decorateTransform_4_oe() {
        final Map<K, V> base = new HashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final Map<K, V> trans = TransformedMap
                .transformedMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(3), trans.get("C"));
    }

    public void testFactory_decorateTransform_5_oe() {
        final Map<K, V> base = new HashMap<>();
        base.put((K) "A", (V) "1");
        base.put((K) "B", (V) "2");
        base.put((K) "C", (V) "3");

        final Map<K, V> trans = TransformedMap
                .transformedMap(
                        base,
                        null,
                        (Transformer<? super V, ? extends V>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        trans.put((K) "D", (V) "4");
        assertEquals(Integer.valueOf(4), trans.get("D"));
    }

}
