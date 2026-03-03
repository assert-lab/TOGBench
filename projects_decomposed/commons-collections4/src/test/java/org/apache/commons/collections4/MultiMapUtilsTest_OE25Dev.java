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
package org.apache.commons.collections4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.junit.Test;

/**
 * Tests for MultiMapUtils
 *
 * @since 4.1
 */
public class MultiMapUtilsTest_OE25Dev {

    public void testEmptyUnmodifiableMultiValuedMap_1_oe() {
        final MultiValuedMap map = MultiMapUtils.EMPTY_MULTI_VALUED_MAP;
        assertTrue(map.isEmpty());
    }

    @Test
    public void testTypeSafeEmptyMultiValuedMap_1_oe() {
        final MultiValuedMap<String, String> map = MultiMapUtils.<String, String>emptyMultiValuedMap();
        assertTrue(map.isEmpty());
    }

    @Test
    public void testEmptyIfNull_1_oe() {
        assertTrue(MultiMapUtils.emptyIfNull(null).isEmpty());
    }

    @Test
    public void testEmptyIfNull_2_oe() {

        final MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        map.put("item", "value");
        assertFalse(MultiMapUtils.emptyIfNull(map).isEmpty());
    }

    @Test
    public void testIsEmptyWithEmptyMap_1_oe() {
        final MultiValuedMap<Object, Object> map = new ArrayListValuedHashMap<>();
        assertEquals(true, MultiMapUtils.isEmpty(map));
    }

    @Test
    public void testIsEmptyWithNonEmptyMap_1_oe() {
        final MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        map.put("item", "value");
        assertEquals(false, MultiMapUtils.isEmpty(map));
    }

    @Test
    public void testIsEmptyWithNull_1_oe() {
        final MultiValuedMap<Object, Object> map = null;
        assertEquals(true, MultiMapUtils.isEmpty(map));
    }

    @Test
    public void testGetCollection_1_oe() {
        assertNull(MultiMapUtils.getCollection(null, "key1"));
    }

    @Test
    public void testGetCollection_2_oe() {

        final String values[] = { "v1", "v2", "v3" };
        final MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        for (final String val : values) {
            map.put("key1", val);
        }

        final Collection<String> col = MultiMapUtils.getCollection(map, "key1");
        for (final String val : values) {
            assertTrue(col.contains(val));
    }
    }

    @Test
    public void testGetValuesAsList_1_oe() {
        assertNull(MultiMapUtils.getValuesAsList(null, "key1"));
    }

    @Test
    public void testGetValuesAsList_2_oe() {

        final String values[] = { "v1", "v2", "v3" };
        final MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        for (final String val : values) {
            map.put("key1", val);
        }

        final List<String> list = MultiMapUtils.getValuesAsList(map, "key1");
        int i = 0;
        for (final String val : list) {
            assertTrue(val.equals(values[i++]));
    }
    }

    @Test
    public void testGetValuesAsSet_1_oe() {
        assertNull(MultiMapUtils.getValuesAsList(null, "key1"));
    }

    @Test
    public void testGetValuesAsSet_2_oe() {

        final String values[] = { "v1", "v2", "v3" };
        final MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        for (final String val : values) {
            map.put("key1", val);
            map.put("key1", val);
        }

        final Set<String> set = MultiMapUtils.getValuesAsSet(map, "key1");
        assertEquals(3, set.size());
    }

    @Test
    public void testGetValuesAsSet_3_oe() {

        final String values[] = { "v1", "v2", "v3" };
        final MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        for (final String val : values) {
            map.put("key1", val);
            map.put("key1", val);
        }

        final Set<String> set = MultiMapUtils.getValuesAsSet(map, "key1");
        for (final String val : values) {
            assertTrue(set.contains(val));
    }
    }

    @Test
    public void testGetValuesAsBag_1_oe() {
        assertNull(MultiMapUtils.getValuesAsBag(null, "key1"));
    }

    @Test
    public void testGetValuesAsBag_2_oe() {

        final String values[] = { "v1", "v2", "v3" };
        final MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        for (final String val : values) {
            map.put("key1", val);
            map.put("key1", val);
        }

        final Bag<String> bag = MultiMapUtils.getValuesAsBag(map, "key1");
        assertEquals(6, bag.size());
    }

    @Test
    public void testGetValuesAsBag_3_oe() {

        final String values[] = { "v1", "v2", "v3" };
        final MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        for (final String val : values) {
            map.put("key1", val);
            map.put("key1", val);
        }

        final Bag<String> bag = MultiMapUtils.getValuesAsBag(map, "key1");
        for (final String val : values) {
            assertTrue(bag.contains(val));
    }
    }

    @Test
    public void testGetValuesAsBag_4_oe() {

        final String values[] = { "v1", "v2", "v3" };
        final MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        for (final String val : values) {
            map.put("key1", val);
            map.put("key1", val);
        }

        final Bag<String> bag = MultiMapUtils.getValuesAsBag(map, "key1");
        for (final String val : values) {
            assertEquals(2, bag.getCount(val));
    }
    }

}
