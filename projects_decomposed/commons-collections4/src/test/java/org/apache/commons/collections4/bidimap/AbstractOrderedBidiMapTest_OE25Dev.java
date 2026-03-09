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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.iterators.AbstractMapIteratorTest;

/**
 * Abstract test class for {@link OrderedBidiMap} methods and contracts.
 *
 */
public abstract class AbstractOrderedBidiMapTest_OE25Dev<K, V> extends AbstractBidiMapTest<K, V> {

    public AbstractOrderedBidiMapTest_OE25Dev(final String testName) {
        super(testName);
    }

    public AbstractOrderedBidiMapTest_OE25Dev() {
        super();
    }

    //-----------------------------------------------------------------------
    public void testFirstKey() {
        resetEmpty();
        OrderedBidiMap<K, V> bidi = getMap();
        try {
            bidi.firstKey();
            fail();
        } catch (final NoSuchElementException ex) {}

        resetFull();
        bidi = getMap();
        final K confirmedFirst = confirmed.keySet().iterator().next();
        assertEquals(confirmedFirst, bidi.firstKey());
    }

    public void testLastKey() {
        resetEmpty();
        OrderedBidiMap<K, V> bidi = getMap();
        try {
            bidi.lastKey();
            fail();
        } catch (final NoSuchElementException ex) {}

        resetFull();
        bidi = getMap();
        K confirmedLast = null;
        for (final Iterator<K> it = confirmed.keySet().iterator(); it.hasNext();) {
            confirmedLast = it.next();
        }
        assertEquals(confirmedLast, bidi.lastKey());
    }

    //-----------------------------------------------------------------------
    public void testNextKey() {
        resetEmpty();
        OrderedBidiMap<K, V> bidi = (OrderedBidiMap<K, V>) map;
        assertEquals(null, bidi.nextKey(getOtherKeys()[0]));
        if (!isAllowNullKey()) {
            try {
                assertEquals(null, bidi.nextKey(null)); // this is allowed too
            } catch (final NullPointerException ex) {}
        } else {
            assertEquals(null, bidi.nextKey(null));
        }

        resetFull();
        bidi = (OrderedBidiMap<K, V>) map;
        final Iterator<K> it = confirmed.keySet().iterator();
        K confirmedLast = it.next();
        while (it.hasNext()) {
            final K confirmedObject = it.next();
            assertEquals(confirmedObject, bidi.nextKey(confirmedLast));
            confirmedLast = confirmedObject;
        }
        assertEquals(null, bidi.nextKey(confirmedLast));

        if (!isAllowNullKey()) {
            try {
                bidi.nextKey(null);
                fail();
            } catch (final NullPointerException ex) {}
        } else {
            assertEquals(null, bidi.nextKey(null));
        }
    }

    public void testPreviousKey() {
        resetEmpty();
        OrderedBidiMap<K, V> bidi = getMap();
        assertEquals(null, bidi.previousKey(getOtherKeys()[0]));
        if (!isAllowNullKey()) {
            try {
                assertEquals(null, bidi.previousKey(null)); // this is allowed too
            } catch (final NullPointerException ex) {}
        } else {
            assertEquals(null, bidi.previousKey(null));
        }

        resetFull();
        bidi = getMap();
        final List<K> list = new ArrayList<>(confirmed.keySet());
        Collections.reverse(list);
        final Iterator<K> it = list.iterator();
        K confirmedLast = it.next();
        while (it.hasNext()) {
            final K confirmedObject = it.next();
            assertEquals(confirmedObject, bidi.previousKey(confirmedLast));
            confirmedLast = confirmedObject;
        }
        assertEquals(null, bidi.previousKey(confirmedLast));

        if (!isAllowNullKey()) {
            try {
                bidi.previousKey(null);
                fail();
            } catch (final NullPointerException ex) {}
        } else {
            assertEquals(null, bidi.previousKey(null));
        }
    }

    //-----------------------------------------------------------------------
    public BulkTest bulkTestOrderedMapIterator() {
        return new TestBidiOrderedMapIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderedBidiMap<K, V> getMap() {
        return (OrderedBidiMap<K, V>) super.getMap();
    }

    public class TestBidiOrderedMapIterator extends AbstractMapIteratorTest<K, V> {
        public TestBidiOrderedMapIterator() {
            super("TestBidiOrderedMapIterator");
        }

        @Override
        public V[] addSetValues() {
            return AbstractOrderedBidiMapTest_OE25Dev.this.getNewSampleValues();
        }

        @Override
        public boolean supportsRemove() {
            return AbstractOrderedBidiMapTest_OE25Dev.this.isRemoveSupported();
        }

        @Override
        public boolean supportsSetValue() {
            return AbstractOrderedBidiMapTest_OE25Dev.this.isSetValueSupported();
        }

        @Override
        public MapIterator<K, V> makeEmptyIterator() {
            resetEmpty();
            return AbstractOrderedBidiMapTest_OE25Dev.this.getMap().mapIterator();
        }

        @Override
        public MapIterator<K, V> makeObject() {
            resetFull();
            return AbstractOrderedBidiMapTest_OE25Dev.this.getMap().mapIterator();
        }

        @Override
        public Map<K, V> getMap() {
            // assumes makeFullMapIterator() called first
            return AbstractOrderedBidiMapTest_OE25Dev.this.map;
        }

        @Override
        public Map<K, V> getConfirmedMap() {
            // assumes makeFullMapIterator() called first
            return AbstractOrderedBidiMapTest_OE25Dev.this.confirmed;
        }

        @Override
        public void verify() {
            super.verify();
            AbstractOrderedBidiMapTest_OE25Dev.this.verify();
        }
    }

    public void testFirstKey_2_oe() {
        resetEmpty();
        OrderedBidiMap<K, V> bidi = getMap();
        try {
            bidi.firstKey();
        } catch (final NoSuchElementException ex) {}

        resetFull();
        bidi = getMap();
        final K confirmedFirst = confirmed.keySet().iterator().next();
        assertEquals(confirmedFirst, bidi.firstKey());
    }

    public void testLastKey_2_oe() {
        resetEmpty();
        OrderedBidiMap<K, V> bidi = getMap();
        try {
            bidi.lastKey();
        } catch (final NoSuchElementException ex) {}

        resetFull();
        bidi = getMap();
        K confirmedLast = null;
        for (final Iterator<K> it = confirmed.keySet().iterator(); it.hasNext();) {
            confirmedLast = it.next();
        }
        assertEquals(confirmedLast, bidi.lastKey());
    }

    public void testNextKey_1_oe() {
        resetEmpty();
        OrderedBidiMap<K, V> bidi = (OrderedBidiMap<K, V>) map;
        assertEquals(null, bidi.nextKey(getOtherKeys()[0]));
    }

    public void testNextKey_3_oe() {
        resetEmpty();
        OrderedBidiMap<K, V> bidi = (OrderedBidiMap<K, V>) map;
        if (!isAllowNullKey()) {
            try {
            } catch (final NullPointerException ex) {}
        } else {
            assertEquals(null, bidi.nextKey(null));
    }
    }

    public void testNextKey_4_oe() {
        resetEmpty();
        OrderedBidiMap<K, V> bidi = (OrderedBidiMap<K, V>) map;
        if (!isAllowNullKey()) {
            try {
            } catch (final NullPointerException ex) {}
        } else {
        }

        resetFull();
        bidi = (OrderedBidiMap<K, V>) map;
        final Iterator<K> it = confirmed.keySet().iterator();
        K confirmedLast = it.next();
        while (it.hasNext()) {
            final K confirmedObject = it.next();
            assertEquals(confirmedObject, bidi.nextKey(confirmedLast));
    }
    }

    public void testNextKey_5_oe() {
        resetEmpty();
        OrderedBidiMap<K, V> bidi = (OrderedBidiMap<K, V>) map;
        if (!isAllowNullKey()) {
            try {
            } catch (final NullPointerException ex) {}
        } else {
        }

        resetFull();
        bidi = (OrderedBidiMap<K, V>) map;
        final Iterator<K> it = confirmed.keySet().iterator();
        K confirmedLast = it.next();
        while (it.hasNext()) {
            final K confirmedObject = it.next();
            confirmedLast = confirmedObject;
        }
        assertEquals(null, bidi.nextKey(confirmedLast));
    }

    public void testNextKey_7_oe() {
        resetEmpty();
        OrderedBidiMap<K, V> bidi = (OrderedBidiMap<K, V>) map;
        if (!isAllowNullKey()) {
            try {
            } catch (final NullPointerException ex) {}
        } else {
        }

        resetFull();
        bidi = (OrderedBidiMap<K, V>) map;
        final Iterator<K> it = confirmed.keySet().iterator();
        K confirmedLast = it.next();
        while (it.hasNext()) {
            final K confirmedObject = it.next();
            confirmedLast = confirmedObject;
        }

        if (!isAllowNullKey()) {
            try {
                bidi.nextKey(null);
            } catch (final NullPointerException ex) {}
        } else {
            assertEquals(null, bidi.nextKey(null));
    }
    }

    public void testPreviousKey_1_oe() {
        resetEmpty();
        OrderedBidiMap<K, V> bidi = getMap();
        assertEquals(null, bidi.previousKey(getOtherKeys()[0]));
    }

    public void testPreviousKey_3_oe() {
        resetEmpty();
        OrderedBidiMap<K, V> bidi = getMap();
        if (!isAllowNullKey()) {
            try {
            } catch (final NullPointerException ex) {}
        } else {
            assertEquals(null, bidi.previousKey(null));
    }
    }

    public void testPreviousKey_4_oe() {
        resetEmpty();
        OrderedBidiMap<K, V> bidi = getMap();
        if (!isAllowNullKey()) {
            try {
            } catch (final NullPointerException ex) {}
        } else {
        }

        resetFull();
        bidi = getMap();
        final List<K> list = new ArrayList<>(confirmed.keySet());
        Collections.reverse(list);
        final Iterator<K> it = list.iterator();
        K confirmedLast = it.next();
        while (it.hasNext()) {
            final K confirmedObject = it.next();
            assertEquals(confirmedObject, bidi.previousKey(confirmedLast));
    }
    }

    public void testPreviousKey_5_oe() {
        resetEmpty();
        OrderedBidiMap<K, V> bidi = getMap();
        if (!isAllowNullKey()) {
            try {
            } catch (final NullPointerException ex) {}
        } else {
        }

        resetFull();
        bidi = getMap();
        final List<K> list = new ArrayList<>(confirmed.keySet());
        Collections.reverse(list);
        final Iterator<K> it = list.iterator();
        K confirmedLast = it.next();
        while (it.hasNext()) {
            final K confirmedObject = it.next();
            confirmedLast = confirmedObject;
        }
        assertEquals(null, bidi.previousKey(confirmedLast));
    }

    public void testPreviousKey_7_oe() {
        resetEmpty();
        OrderedBidiMap<K, V> bidi = getMap();
        if (!isAllowNullKey()) {
            try {
            } catch (final NullPointerException ex) {}
        } else {
        }

        resetFull();
        bidi = getMap();
        final List<K> list = new ArrayList<>(confirmed.keySet());
        Collections.reverse(list);
        final Iterator<K> it = list.iterator();
        K confirmedLast = it.next();
        while (it.hasNext()) {
            final K confirmedObject = it.next();
            confirmedLast = confirmedObject;
        }

        if (!isAllowNullKey()) {
            try {
                bidi.previousKey(null);
            } catch (final NullPointerException ex) {}
        } else {
            assertEquals(null, bidi.previousKey(null));
    }
    }

}
