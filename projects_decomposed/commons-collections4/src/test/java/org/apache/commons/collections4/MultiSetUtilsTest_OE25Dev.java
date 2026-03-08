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
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.apache.commons.collections4.multiset.HashMultiSet;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for MultiSetUtils.
 * @since 4.2
 */
public class MultiSetUtilsTest_OE25Dev {

    private String[] fullArray;
    private MultiSet<String> multiSet;

    @Before
    public void setUp() {
        fullArray = new String[]{
            "a", "a", "b", "c", "d", "d", "d"
        };
        multiSet = new HashMultiSet<>(Arrays.asList(fullArray));
    }

    /**
     * Tests {@link MultiSetUtils#emptyMultiSet()}.
     */

    /**
     * Tests {@link MultiSetUtils#unmodifiableMultiSet(org.apache.commons.collections4.MultiSet) ()}.
     */

    /**
     * Tests {@link MultiSetUtils#unmodifiableMultiSet(org.apache.commons.collections4.MultiSet) ()}.
     */

    /**
     * Tests {@link MultiSetUtils#predicatedMultiSet(org.apache.commons.collections4.MultiSet, org.apache.commons.collections4.Predicate)}.
     */

    @Test
    public void testEmptyMultiSet_1_oe() {
        final MultiSet<Integer> empty = MultiSetUtils.emptyMultiSet();
        assertEquals(0, empty.size());
    }

    @Test
    public void testUnmodifiableMultiSet_1_oe() {
        final MultiSet<String> unmodifiable = MultiSetUtils.unmodifiableMultiSet(multiSet);
        assertEquals(multiSet, unmodifiable);
    }

    @Test
    public void testSynchronizedMultiSet_1_oe() {
        final MultiSet<String> synced = MultiSetUtils.synchronizedMultiSet(multiSet);
        assertEquals(multiSet, synced);
    }

    @Test
    public void testPredicatedMultiSet_1_oe() {
        final Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean evaluate(final String object) {
                return object.length() == 1;
            };
        };
        final MultiSet<String> predicated = MultiSetUtils.predicatedMultiSet(multiSet, predicate);
        assertEquals(multiSet.size(), predicated.size());
    }

    @Test
    public void testPredicatedMultiSet_2_oe() {
        final Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean evaluate(final String object) {
                return object.length() == 1;
            };
        };
        final MultiSet<String> predicated = MultiSetUtils.predicatedMultiSet(multiSet, predicate);
        assertEquals(multiSet.getCount("a"), predicated.getCount("a"));
    }

@Test
    public void testUnmodifiableMultiSet_oe_102_oe() {
        try {
            MultiSetUtils.unmodifiableMultiSet(null);
            fail("Expecting NPE");
        } catch (final NullPointerException e) {
        }
    }

@Test
    public void testPredicatedMultiSet_oe_102_oe() {
        try {
            MultiSetUtils.predicatedMultiSet(multiSet, null);
            fail("Expecting NPE");
        } catch (final NullPointerException e) {
        }
    }

}
