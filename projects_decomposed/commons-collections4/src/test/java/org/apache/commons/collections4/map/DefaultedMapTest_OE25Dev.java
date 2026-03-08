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

import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FactoryUtils;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ConstantFactory;

/**
 * Extension of {@link AbstractMapTest} for exercising the
 * {@link DefaultedMap} implementation.
 *
 * @since 3.2
 */
public class DefaultedMapTest_OE25Dev<K, V> extends AbstractIterableMapTest<K, V> {

    protected final Factory<V> nullFactory = FactoryUtils.<V>nullFactory();

    public DefaultedMapTest_OE25Dev(final String testName) {
        super(testName);
    }

    //-----------------------------------------------------------------------
    @Override
    public IterableMap<K, V> makeObject() {
        return DefaultedMap.defaultedMap(new HashMap<K, V>(), nullFactory);
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
//            "src/test/resources/data/test/DefaultedMap.emptyCollection.version4.obj");
//        resetFull();
//        writeExternalFormToDisk(
//            (java.io.Serializable) map,
//            "src/test/resources/data/test/DefaultedMap.fullCollection.version4.obj");
//    }

    public void testMapGet_1_oe() {
        final Map<K, V> map = new DefaultedMap<>((V) "NULL");

        assertEquals(0, map.size());
    }

    public void testMapGet_2_oe() {
        final Map<K, V> map = new DefaultedMap<>((V) "NULL");

        assertEquals(false, map.containsKey("NotInMap"));
    }

    public void testMapGet_3_oe() {
        final Map<K, V> map = new DefaultedMap<>((V) "NULL");

        assertEquals("NULL", map.get("NotInMap"));
    }

    public void testMapGet_4_oe() {
        final Map<K, V> map = new DefaultedMap<>((V) "NULL");


        map.put((K) "Key", (V) "Value");
        assertEquals(1, map.size());
    }

    public void testMapGet_5_oe() {
        final Map<K, V> map = new DefaultedMap<>((V) "NULL");


        map.put((K) "Key", (V) "Value");
        assertEquals(true, map.containsKey("Key"));
    }

    public void testMapGet_6_oe() {
        final Map<K, V> map = new DefaultedMap<>((V) "NULL");


        map.put((K) "Key", (V) "Value");
        assertEquals("Value", map.get("Key"));
    }

    public void testMapGet_7_oe() {
        final Map<K, V> map = new DefaultedMap<>((V) "NULL");


        map.put((K) "Key", (V) "Value");
        assertEquals(false, map.containsKey("NotInMap"));
    }

    public void testMapGet_8_oe() {
        final Map<K, V> map = new DefaultedMap<>((V) "NULL");


        map.put((K) "Key", (V) "Value");
        assertEquals("NULL", map.get("NotInMap"));
    }

    public void testMapGet2_1_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, (V) "NULL");

        assertEquals(0, map.size());
    }

    public void testMapGet2_2_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, (V) "NULL");

        assertEquals(0, base.size());
    }

    public void testMapGet2_3_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, (V) "NULL");

        assertEquals(false, map.containsKey("NotInMap"));
    }

    public void testMapGet2_4_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, (V) "NULL");

        assertEquals("NULL", map.get("NotInMap"));
    }

    public void testMapGet2_5_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, (V) "NULL");


        map.put((K) "Key", (V) "Value");
        assertEquals(1, map.size());
    }

    public void testMapGet2_6_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, (V) "NULL");


        map.put((K) "Key", (V) "Value");
        assertEquals(1, base.size());
    }

    public void testMapGet2_7_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, (V) "NULL");


        map.put((K) "Key", (V) "Value");
        assertEquals(true, map.containsKey("Key"));
    }

    public void testMapGet2_8_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, (V) "NULL");


        map.put((K) "Key", (V) "Value");
        assertEquals("Value", map.get("Key"));
    }

    public void testMapGet2_9_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, (V) "NULL");


        map.put((K) "Key", (V) "Value");
        assertEquals(false, map.containsKey("NotInMap"));
    }

    public void testMapGet2_10_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, (V) "NULL");


        map.put((K) "Key", (V) "Value");
        assertEquals("NULL", map.get("NotInMap"));
    }

    public void testMapGet3_1_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, ConstantFactory.constantFactory((V) "NULL"));

        assertEquals(0, map.size());
    }

    public void testMapGet3_2_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, ConstantFactory.constantFactory((V) "NULL"));

        assertEquals(0, base.size());
    }

    public void testMapGet3_3_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, ConstantFactory.constantFactory((V) "NULL"));

        assertEquals(false, map.containsKey("NotInMap"));
    }

    public void testMapGet3_4_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, ConstantFactory.constantFactory((V) "NULL"));

        assertEquals("NULL", map.get("NotInMap"));
    }

    public void testMapGet3_5_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, ConstantFactory.constantFactory((V) "NULL"));


        map.put((K) "Key", (V) "Value");
        assertEquals(1, map.size());
    }

    public void testMapGet3_6_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, ConstantFactory.constantFactory((V) "NULL"));


        map.put((K) "Key", (V) "Value");
        assertEquals(1, base.size());
    }

    public void testMapGet3_7_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, ConstantFactory.constantFactory((V) "NULL"));


        map.put((K) "Key", (V) "Value");
        assertEquals(true, map.containsKey("Key"));
    }

    public void testMapGet3_8_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, ConstantFactory.constantFactory((V) "NULL"));


        map.put((K) "Key", (V) "Value");
        assertEquals("Value", map.get("Key"));
    }

    public void testMapGet3_9_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, ConstantFactory.constantFactory((V) "NULL"));


        map.put((K) "Key", (V) "Value");
        assertEquals(false, map.containsKey("NotInMap"));
    }

    public void testMapGet3_10_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, ConstantFactory.constantFactory((V) "NULL"));


        map.put((K) "Key", (V) "Value");
        assertEquals("NULL", map.get("NotInMap"));
    }

    public void testMapGet4_1_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, new Transformer<K, V>() {
            @Override
            public V transform(final K input) {
                if (input instanceof String) {
                    return (V) "NULL";
                }
                return (V) "NULL_OBJECT";
            }
        });

        assertEquals(0, map.size());
    }

    public void testMapGet4_2_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, new Transformer<K, V>() {
            @Override
            public V transform(final K input) {
                if (input instanceof String) {
                    return (V) "NULL";
                }
                return (V) "NULL_OBJECT";
            }
        });

        assertEquals(0, base.size());
    }

    public void testMapGet4_3_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, new Transformer<K, V>() {
            @Override
            public V transform(final K input) {
                if (input instanceof String) {
                    return (V) "NULL";
                }
                return (V) "NULL_OBJECT";
            }
        });

        assertEquals(false, map.containsKey("NotInMap"));
    }

    public void testMapGet4_4_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, new Transformer<K, V>() {
            @Override
            public V transform(final K input) {
                if (input instanceof String) {
                    return (V) "NULL";
                }
                return (V) "NULL_OBJECT";
            }
        });

        assertEquals("NULL", map.get("NotInMap"));
    }

    public void testMapGet4_5_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, new Transformer<K, V>() {
            @Override
            public V transform(final K input) {
                if (input instanceof String) {
                    return (V) "NULL";
                }
                return (V) "NULL_OBJECT";
            }
        });

        assertEquals("NULL_OBJECT", map.get(Integer.valueOf(0)));
    }

    public void testMapGet4_6_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, new Transformer<K, V>() {
            @Override
            public V transform(final K input) {
                if (input instanceof String) {
                    return (V) "NULL";
                }
                return (V) "NULL_OBJECT";
            }
        });


        map.put((K) "Key", (V) "Value");
        assertEquals(1, map.size());
    }

    public void testMapGet4_7_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, new Transformer<K, V>() {
            @Override
            public V transform(final K input) {
                if (input instanceof String) {
                    return (V) "NULL";
                }
                return (V) "NULL_OBJECT";
            }
        });


        map.put((K) "Key", (V) "Value");
        assertEquals(1, base.size());
    }

    public void testMapGet4_8_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, new Transformer<K, V>() {
            @Override
            public V transform(final K input) {
                if (input instanceof String) {
                    return (V) "NULL";
                }
                return (V) "NULL_OBJECT";
            }
        });


        map.put((K) "Key", (V) "Value");
        assertEquals(true, map.containsKey("Key"));
    }

    public void testMapGet4_9_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, new Transformer<K, V>() {
            @Override
            public V transform(final K input) {
                if (input instanceof String) {
                    return (V) "NULL";
                }
                return (V) "NULL_OBJECT";
            }
        });


        map.put((K) "Key", (V) "Value");
        assertEquals("Value", map.get("Key"));
    }

    public void testMapGet4_10_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, new Transformer<K, V>() {
            @Override
            public V transform(final K input) {
                if (input instanceof String) {
                    return (V) "NULL";
                }
                return (V) "NULL_OBJECT";
            }
        });


        map.put((K) "Key", (V) "Value");
        assertEquals(false, map.containsKey("NotInMap"));
    }

    public void testMapGet4_11_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, new Transformer<K, V>() {
            @Override
            public V transform(final K input) {
                if (input instanceof String) {
                    return (V) "NULL";
                }
                return (V) "NULL_OBJECT";
            }
        });


        map.put((K) "Key", (V) "Value");
        assertEquals("NULL", map.get("NotInMap"));
    }

    public void testMapGet4_12_oe() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, new Transformer<K, V>() {
            @Override
            public V transform(final K input) {
                if (input instanceof String) {
                    return (V) "NULL";
                }
                return (V) "NULL_OBJECT";
            }
        });


        map.put((K) "Key", (V) "Value");
        assertEquals("NULL_OBJECT", map.get(Integer.valueOf(0)));
    }

}
