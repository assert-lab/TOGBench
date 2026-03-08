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
package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.collections4.OrderedMapIterator;

/**
 * Abstract class for testing the OrderedMapIterator interface.
 * <p>
 * This class provides a framework for testing an implementation of MapIterator.
 * Concrete subclasses must provide the list iterator to be tested.
 * They must also specify certain details of how the list iterator operates by
 * overriding the supportsXxx() methods if necessary.
 *
 * @since 3.0
 */
public abstract class AbstractOrderedMapIteratorTest_OE25Dev<K, V> extends AbstractMapIteratorTest<K, V> {

    /**
     * JUnit constructor.
     *
     * @param testName  the test class name
     */
    public AbstractOrderedMapIteratorTest_OE25Dev(final String testName) {
        super(testName);
    }

    //-----------------------------------------------------------------------
    @Override
    public abstract OrderedMapIterator<K, V> makeEmptyIterator();

    @Override
    public abstract OrderedMapIterator<K, V> makeObject();

    //-----------------------------------------------------------------------
    /**
     * Test that the empty list iterator contract is correct.
     */

    //-----------------------------------------------------------------------
    /**
     * Test that the full list iterator contract is correct.
     */

    //-----------------------------------------------------------------------
    /**
     * Test that the iterator order matches the keySet order.
     */

    public void testEmptyMapIterator_1_oe() {
        if (!supportsEmptyIterator()) {
            return;
        }

        super.testEmptyMapIterator();

        final OrderedMapIterator<K, V> it = makeEmptyIterator();
        assertEquals(false, it.hasPrevious());
    }

    public void testFullMapIterator_1_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        super.testFullMapIterator();

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();

        assertEquals(true, it.hasNext());
    }

    public void testFullMapIterator_2_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        super.testFullMapIterator();

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();

        assertEquals(false, it.hasPrevious());
    }

    public void testFullMapIterator_3_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        super.testFullMapIterator();

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();

        final Set<K> set = new HashSet<>();
        while (it.hasNext()) {
            final K key = it.next();
            assertSame("it.next() should equals getKey()", key, it.getKey());
    }
    }

    public void testFullMapIterator_4_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        super.testFullMapIterator();

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();

        final Set<K> set = new HashSet<>();
        while (it.hasNext()) {
            final K key = it.next();
            assertTrue("Key must be in map",  map.containsKey(key));
    }
    }

    public void testFullMapIterator_5_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        super.testFullMapIterator();

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();

        final Set<K> set = new HashSet<>();
        while (it.hasNext()) {
            final K key = it.next();
            assertTrue("Key must be unique", set.add(key));
    }
    }

    public void testFullMapIterator_6_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        super.testFullMapIterator();

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();

        final Set<K> set = new HashSet<>();
        while (it.hasNext()) {
            final K key = it.next();

            final V value = it.getValue();
            if (!isGetStructuralModify()) {
                assertSame("Value must be mapped to key", map.get(key), value);
    }
    }
    }

    public void testFullMapIterator_7_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        super.testFullMapIterator();

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();

        final Set<K> set = new HashSet<>();
        while (it.hasNext()) {
            final K key = it.next();

            final V value = it.getValue();
            if (!isGetStructuralModify()) {
            }
            assertTrue("Value must be in map",  map.containsValue(value));
    }
    }

    public void testFullMapIterator_8_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        super.testFullMapIterator();

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();

        final Set<K> set = new HashSet<>();
        while (it.hasNext()) {
            final K key = it.next();

            final V value = it.getValue();
            if (!isGetStructuralModify()) {
            }

            assertEquals(true, it.hasPrevious());
    }
    }

    public void testFullMapIterator_9_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        super.testFullMapIterator();

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();

        final Set<K> set = new HashSet<>();
        while (it.hasNext()) {
            final K key = it.next();

            final V value = it.getValue();
            if (!isGetStructuralModify()) {
            }


            verify();
        }
        while (it.hasPrevious()) {
            final Object key = it.previous();
            assertSame("it.previous() should equals getKey()", key, it.getKey());
    }
    }

    public void testFullMapIterator_10_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        super.testFullMapIterator();

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();

        final Set<K> set = new HashSet<>();
        while (it.hasNext()) {
            final K key = it.next();

            final V value = it.getValue();
            if (!isGetStructuralModify()) {
            }


            verify();
        }
        while (it.hasPrevious()) {
            final Object key = it.previous();
            assertTrue("Key must be in map",  map.containsKey(key));
    }
    }

    public void testFullMapIterator_11_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        super.testFullMapIterator();

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();

        final Set<K> set = new HashSet<>();
        while (it.hasNext()) {
            final K key = it.next();

            final V value = it.getValue();
            if (!isGetStructuralModify()) {
            }


            verify();
        }
        while (it.hasPrevious()) {
            final Object key = it.previous();
            assertTrue("Key must be unique", set.remove(key));
    }
    }

    public void testFullMapIterator_12_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        super.testFullMapIterator();

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();

        final Set<K> set = new HashSet<>();
        while (it.hasNext()) {
            final K key = it.next();

            final V value = it.getValue();
            if (!isGetStructuralModify()) {
            }


            verify();
        }
        while (it.hasPrevious()) {
            final Object key = it.previous();

            final Object value = it.getValue();
            if (!isGetStructuralModify()) {
                assertSame("Value must be mapped to key", map.get(key), value);
    }
    }
    }

    public void testFullMapIterator_13_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        super.testFullMapIterator();

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();

        final Set<K> set = new HashSet<>();
        while (it.hasNext()) {
            final K key = it.next();

            final V value = it.getValue();
            if (!isGetStructuralModify()) {
            }


            verify();
        }
        while (it.hasPrevious()) {
            final Object key = it.previous();

            final Object value = it.getValue();
            if (!isGetStructuralModify()) {
            }
            assertTrue("Value must be in map",  map.containsValue(value));
    }
    }

    public void testFullMapIterator_14_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        super.testFullMapIterator();

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();

        final Set<K> set = new HashSet<>();
        while (it.hasNext()) {
            final K key = it.next();

            final V value = it.getValue();
            if (!isGetStructuralModify()) {
            }


            verify();
        }
        while (it.hasPrevious()) {
            final Object key = it.previous();

            final Object value = it.getValue();
            if (!isGetStructuralModify()) {
            }

            assertEquals(true, it.hasNext());
    }
    }

    public void testMapIteratorOrder_1_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();

        assertEquals("keySet() not consistent", new ArrayList<>(map.keySet()), new ArrayList<>(map.keySet()));
    }

    public void testMapIteratorOrder_2_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();


        final Iterator<K> it2 = map.keySet().iterator();
        assertEquals(true, it.hasNext());
    }

    public void testMapIteratorOrder_3_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();


        final Iterator<K> it2 = map.keySet().iterator();
        assertEquals(true, it2.hasNext());
    }

    public void testMapIteratorOrder_4_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();


        final Iterator<K> it2 = map.keySet().iterator();
        final List<K> list = new ArrayList<>();
        while (it.hasNext()) {
            final K key = it.next();
            assertEquals(it2.next(), key);
    }
    }

    public void testMapIteratorOrder_5_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();


        final Iterator<K> it2 = map.keySet().iterator();
        final List<K> list = new ArrayList<>();
        while (it.hasNext()) {
            final K key = it.next();
            list.add(key);
        }
        assertEquals(map.size(), list.size());
    }

    public void testMapIteratorOrder_6_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();


        final Iterator<K> it2 = map.keySet().iterator();
        final List<K> list = new ArrayList<>();
        while (it.hasNext()) {
            final K key = it.next();
            list.add(key);
        }
        while (it.hasPrevious()) {
            final K key = it.previous();
            assertEquals(list.get(list.size() - 1), key);
    }
    }

    public void testMapIteratorOrder_7_oe() {
        if (!supportsFullIterator()) {
            return;
        }

        final OrderedMapIterator<K, V> it = makeObject();
        final Map<K, V> map = getMap();


        final Iterator<K> it2 = map.keySet().iterator();
        final List<K> list = new ArrayList<>();
        while (it.hasNext()) {
            final K key = it.next();
            list.add(key);
        }
        while (it.hasPrevious()) {
            final K key = it.previous();
            list.remove(list.size() - 1);
        }
        assertEquals(0, list.size());
    }

}
