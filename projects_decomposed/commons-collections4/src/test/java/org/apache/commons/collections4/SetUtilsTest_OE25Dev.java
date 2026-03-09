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
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections4.SetUtils.SetView;
import org.apache.commons.collections4.set.PredicatedSet;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for SetUtils.
 *
 */
public class SetUtilsTest_OE25Dev {

    private Set<Integer> setA;
    private Set<Integer> setB;

    @Test
    public void difference() {
        final SetView<Integer> set = SetUtils.difference(setA, setB);
        assertEquals(2, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        for (final Integer i : setB) {
            assertFalse(set.contains(i));
        }

        final Set<Integer> set2 = SetUtils.difference(setA, SetUtils.<Integer>emptySet());
        assertEquals(setA, set2);

        try {
            SetUtils.difference(setA, null);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }

        try {
            SetUtils.difference(null, setA);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }

    @Test
    public void disjunction() {
        final SetView<Integer> set = SetUtils.disjunction(setA, setB);
        assertEquals(4, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(6));
        assertTrue(set.contains(7));
        assertFalse(set.contains(3));
        assertFalse(set.contains(4));
        assertFalse(set.contains(5));

        final Set<Integer> set2 = SetUtils.disjunction(setA, SetUtils.<Integer>emptySet());
        assertEquals(setA, set2);

        try {
            SetUtils.disjunction(setA, null);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }

        try {
            SetUtils.disjunction(null, setA);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }

    @Test
    public void intersection() {
        final SetView<Integer> set = SetUtils.intersection(setA, setB);
        assertEquals(3, set.size());
        assertTrue(set.contains(3));
        assertTrue(set.contains(4));
        assertTrue(set.contains(5));
        assertFalse(set.contains(1));
        assertFalse(set.contains(2));
        assertFalse(set.contains(6));
        assertFalse(set.contains(7));

        final Set<Integer> set2 = SetUtils.intersection(setA, SetUtils.<Integer>emptySet());
        assertEquals(SetUtils.<Integer>emptySet(), set2);

        try {
            SetUtils.intersection(setA, null);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }

        try {
            SetUtils.intersection(null, setA);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }

    @Before
    public void setUp() {
        setA = new HashSet<>();
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setA.add(4);
        setA.add(5);

        setB = new HashSet<>();
        setB.add(3);
        setB.add(4);
        setB.add(5);
        setB.add(6);
        setB.add(7);
    }

    @Test
    public void testEmptyIfNull() {
        assertTrue(SetUtils.emptyIfNull(null).isEmpty());

        final Set<Long> set = new HashSet<>();
        assertSame(set, SetUtils.emptyIfNull(set));
    }

    @Test
    public void testEquals() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final Set<String> a = new HashSet<>(data);
        final Set<String> b = new HashSet<>(data);

        assertEquals(true, a.equals(b));
        assertEquals(true, SetUtils.isEqualSet(a, b));
        a.clear();
        assertEquals(false, SetUtils.isEqualSet(a, b));
        assertEquals(false, SetUtils.isEqualSet(a, null));
        assertEquals(false, SetUtils.isEqualSet(null, b));
        assertEquals(true, SetUtils.isEqualSet(null, null));
    }

    @Test
    public void testHashCode() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final Set<String> a = new HashSet<>(data);
        final Set<String> b = new HashSet<>(data);

        assertEquals(true, a.hashCode() == b.hashCode());
        assertEquals(true, a.hashCode() == SetUtils.hashCodeForSet(a));
        assertEquals(true, b.hashCode() == SetUtils.hashCodeForSet(b));
        assertEquals(true, SetUtils.hashCodeForSet(a) == SetUtils.hashCodeForSet(b));
        a.clear();
        assertEquals(false, SetUtils.hashCodeForSet(a) == SetUtils.hashCodeForSet(b));
        assertEquals(0, SetUtils.hashCodeForSet(null));
    }

    @Test
    public void testHashSet()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();
        assertTrue("set is empty", set1.isEmpty());

        Set<Integer> set2 = SetUtils.hashSet(1, 2, 2, 3);
        assertEquals("set has 3 elements", 3, set2.size());
        assertTrue("set contains 1", set2.contains(1));
        assertTrue("set contains 2", set2.contains(2));
        assertTrue("set contains 3", set2.contains(3));

        Set<String> set3 = SetUtils.hashSet("1", "2", "2", "3");
        assertEquals("set has 3 elements", 3, set3.size());
        assertTrue("set contains 1", set3.contains("1"));
        assertTrue("set contains 2", set3.contains("2"));
        assertTrue("set contains 3", set3.contains("3"));

        Set<?> set4 = SetUtils.hashSet(null, null);
        assertEquals("set has 1 element", 1, set4.size());
        assertTrue("set contains null", set4.contains(null));

        Set<?> set5 = SetUtils.hashSet((Object[]) null);
        assertEquals("set is null", null, set5);
    }

    @Test
    public void testNewIdentityHashSet() {
        final Set<String> set = SetUtils.newIdentityHashSet();
        final String a = new String("a");
        set.add(a);
        set.add(new String("b"));
        set.add(a);

        assertEquals(2, set.size());

        set.add(new String("a"));
        assertEquals(3, set.size());

        set.remove(a);
        assertEquals(2, set.size());
    }

    @Test
    public void testpredicatedSet() {
        final Predicate<Object> predicate = new Predicate<Object>() {
            @Override
            public boolean evaluate(final Object o) {
                return o instanceof String;
            }
        };
        final Set<Object> set = SetUtils.predicatedSet(new HashSet<>(), predicate);
        assertTrue("returned object should be a PredicatedSet", set instanceof PredicatedSet);
        try {
            SetUtils.predicatedSet(new HashSet<>(), null);
            fail("Expecting NullPointerException for null predicate.");
        } catch (final NullPointerException ex) {
            // expected
        }
        try {
            SetUtils.predicatedSet(null, predicate);
            fail("Expecting NullPointerException for null set.");
        } catch (final NullPointerException ex) {
            // expected
        }
    }

    @Test
    public void testUnmodifiableSet()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();
        assertTrue("set is empty", set1.isEmpty());

        Set<Integer> set2 = SetUtils.unmodifiableSet(1, 2, 2, 3);
        assertEquals("set has 3 elements", 3, set2.size());
        assertTrue("set contains 1", set2.contains(1));
        assertTrue("set contains 2", set2.contains(2));
        assertTrue("set contains 3", set2.contains(3));

        Set<String> set3 = SetUtils.unmodifiableSet("1", "2", "2", "3");
        assertEquals("set has 3 elements", 3, set3.size());
        assertTrue("set contains 1", set3.contains("1"));
        assertTrue("set contains 2", set3.contains("2"));
        assertTrue("set contains 3", set3.contains("3"));

        Set<?> set4 = SetUtils.unmodifiableSet(null, null);
        assertEquals("set has 1 element", 1, set4.size());
        assertTrue("set contains null", set4.contains(null));

        Set<?> set5 = SetUtils.unmodifiableSet((Object[]) null);
        assertEquals("set is null", null, set5);
    }

    @Test
    public void testUnmodifiableSetWrap()
    {
        Set<Integer> set1 = SetUtils.unmodifiableSet(1, 2, 2, 3);
        Set<Integer> set2 = SetUtils.unmodifiableSet(set1);
        assertSame(set1, set2);
    }

    @Test
    public void union() {
        final SetView<Integer> set = SetUtils.union(setA, setB);
        assertEquals(7, set.size());
        assertTrue(set.containsAll(setA));
        assertTrue(set.containsAll(setB));

        final Set<Integer> set2 = SetUtils.union(setA, SetUtils.<Integer>emptySet());
        assertEquals(setA, set2);

        try {
            SetUtils.union(setA, null);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }

        try {
            SetUtils.union(null, setA);
            fail("Expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }

    @Test
    public void difference_1_oe() {
        final SetView<Integer> set = SetUtils.difference(setA, setB);
        assertEquals(0, set.size());
    }

    @Test
    public void difference_2_oe() {
        final SetView<Integer> set = SetUtils.difference(setA, setB);
        assertEquals(true, set.contains(1));
    }

    @Test
    public void difference_3_oe() {
        final SetView<Integer> set = SetUtils.difference(setA, setB);
        assertEquals(true, set.contains(1));
    }

    @Test
    public void disjunction_1_oe() {
        final SetView<Integer> set = SetUtils.disjunction(setA, setB);
        assertEquals(0, set.size());
    }

    @Test
    public void disjunction_2_oe() {
        final SetView<Integer> set = SetUtils.disjunction(setA, setB);
        assertEquals(true, set.contains(1));
    }

    @Test
    public void disjunction_3_oe() {
        final SetView<Integer> set = SetUtils.disjunction(setA, setB);
        assertEquals(true, set.contains(1));
    }

    @Test
    public void disjunction_4_oe() {
        final SetView<Integer> set = SetUtils.disjunction(setA, setB);
        assertEquals(true, set.contains(1));
    }

    @Test
    public void disjunction_5_oe() {
        final SetView<Integer> set = SetUtils.disjunction(setA, setB);
        assertEquals(true, set.contains(1));
    }

    @Test
    public void disjunction_6_oe() {
        final SetView<Integer> set = SetUtils.disjunction(setA, setB);
        assertEquals(true, set.contains(1));
    }

    @Test
    public void disjunction_7_oe() {
        final SetView<Integer> set = SetUtils.disjunction(setA, setB);
        assertEquals(true, set.contains(1));
    }

    @Test
    public void disjunction_8_oe() {
        final SetView<Integer> set = SetUtils.disjunction(setA, setB);
        assertEquals(true, set.contains(1));
    }

    @Test
    public void intersection_1_oe() {
        final SetView<Integer> set = SetUtils.intersection(setA, setB);
        assertEquals(0, set.size());
    }

    @Test
    public void intersection_2_oe() {
        final SetView<Integer> set = SetUtils.intersection(setA, setB);
        assertEquals(true, set.contains(1));
    }

    @Test
    public void intersection_3_oe() {
        final SetView<Integer> set = SetUtils.intersection(setA, setB);
        assertEquals(true, set.contains(1));
    }

    @Test
    public void intersection_4_oe() {
        final SetView<Integer> set = SetUtils.intersection(setA, setB);
        assertEquals(true, set.contains(1));
    }

    @Test
    public void intersection_5_oe() {
        final SetView<Integer> set = SetUtils.intersection(setA, setB);
        assertEquals(true, set.contains(1));
    }

    @Test
    public void intersection_6_oe() {
        final SetView<Integer> set = SetUtils.intersection(setA, setB);
        assertEquals(true, set.contains(1));
    }

    @Test
    public void intersection_7_oe() {
        final SetView<Integer> set = SetUtils.intersection(setA, setB);
        assertEquals(true, set.contains(1));
    }

    @Test
    public void intersection_8_oe() {
        final SetView<Integer> set = SetUtils.intersection(setA, setB);
        assertEquals(true, set.contains(1));
    }

    @Test
    public void testEmptyIfNull_1_oe() {
        boolean a = SetUtils.emptyIfNull(null).isEmpty();
        assertEquals(true, a);
    }

    @Test
    public void testEmptyIfNull_2_oe() {

        final Set<Long> set = new HashSet<>();
        assertEquals(true, emptyIfNull(set).isEmpty());
    }

    @Test
    public void testEquals_1_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final Set<String> a = new HashSet<>(data);
        final Set<String> b = new HashSet<>(data);

        assertEquals(true, a.equals(b));
    }

    @Test
    public void testHashCode_1_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final Set<String> a = new HashSet<>(data);
        final Set<String> b = new HashSet<>(data);

        assertEquals(true, a.equals(b));
    }

    @Test
    public void testHashCode_2_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final Set<String> a = new HashSet<>(data);
        final Set<String> b = new HashSet<>(data);

        assertEquals(true, a.equals(b));
    }

    @Test
    public void testHashCode_3_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final Set<String> a = new HashSet<>(data);
        final Set<String> b = new HashSet<>(data);

        assertEquals(true, a.equals(b));
    }

    @Test
    public void testHashCode_4_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final Set<String> a = new HashSet<>(data);
        final Set<String> b = new HashSet<>(data);

        assertEquals(0, HashCodeForSet.hashCodeForSet(null));
    }

    @Test
    public void testHashCode_5_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final Set<String> a = new HashSet<>(data);
        final Set<String> b = new HashSet<>(data);

        a.clear();
        assertEquals(0, HashCodeForSet.hashCodeForSet(a));
    }

    @Test
    public void testHashCode_6_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final Set<String> a = new HashSet<>(data);
        final Set<String> b = new HashSet<>(data);

        a.clear();
        assertEquals(0, HashCodeForSet.hashCodeForSet(a));
    }

    @Test
    public void testHashSet_1_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();
        assertEquals(true, set1.isEmpty());
    }

    @Test
    public void testHashSet_2_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.hashSet(1, 2, 2, 3);
        assertEquals(4, set2.size());
    }

    @Test
    public void testHashSet_3_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.hashSet(1, 2, 2, 3);
        assertEquals(false, set1.contains(set2));
    }

    @Test
    public void testHashSet_4_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.hashSet(1, 2, 2, 3);
        assertEquals(false, set1.contains(set2));
    }

    @Test
    public void testHashSet_5_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.hashSet(1, 2, 2, 3);
        assertEquals(false, set1.contains(set2));
    }

    @Test
    public void testHashSet_6_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.hashSet(1, 2, 2, 3);

        Set<String> set3 = SetUtils.hashSet("1", "2", "2", "3");
        assertEquals(4, set2.size());
    }

    @Test
    public void testHashSet_7_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.hashSet(1, 2, 2, 3);

        Set<String> set3 = SetUtils.hashSet("1", "2", "2", "3");
        assertEquals(false, set1.contains(set2));
    }

    @Test
    public void testHashSet_8_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.hashSet(1, 2, 2, 3);

        Set<String> set3 = SetUtils.hashSet("1", "2", "2", "3");
        assertEquals(false, set1.contains(set2));
    }

    @Test
    public void testHashSet_9_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.hashSet(1, 2, 2, 3);

        Set<String> set3 = SetUtils.hashSet("1", "2", "2", "3");
        assertEquals(false, set1.contains(set2));
    }

    @Test
    public void testHashSet_10_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.hashSet(1, 2, 2, 3);

        Set<String> set3 = SetUtils.hashSet("1", "2", "2", "3");

        Set<?> set4 = SetUtils.hashSet(null, null);
        assertEquals(4, set3.size());
    }

    @Test
    public void testHashSet_11_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.hashSet(1, 2, 2, 3);

        Set<String> set3 = SetUtils.hashSet("1", "2", "2", "3");

        Set<?> set4 = SetUtils.hashSet(null, null);
        assertEquals(false, set4.contains(null));
    }

    @Test
    public void testNewIdentityHashSet_1_oe() {
        final Set<String> set = SetUtils.newIdentityHashSet();
        final String a = new String("a");
        set.add(a);
        set.add(new String("b"));
        set.add(a);

        assertEquals(3, set.size());
    }

    @Test
    public void testNewIdentityHashSet_2_oe() {
        final Set<String> set = SetUtils.newIdentityHashSet();
        final String a = new String("a");
        set.add(a);
        set.add(new String("b"));
        set.add(a);


        set.add(new String("a"));
        assertEquals(4, set.size());
    }

    @Test
    public void testNewIdentityHashSet_3_oe() {
        final Set<String> set = SetUtils.newIdentityHashSet();
        final String a = new String("a");
        set.add(a);
        set.add(new String("b"));
        set.add(a);


        set.add(new String("a"));

        set.remove(a);
        assertEquals(2, set.size());
    }

    @Test
    public void testpredicatedSet_1_oe() {
        final Predicate<Object> predicate = new Predicate<Object>() {
            @Override
            public boolean evaluate(final Object o) {
                return o instanceof String;
            }
        };
        final Set<Object> set = SetUtils.predicatedSet(new HashSet<>(), predicate);
        assertNotNull(set);
    }

    @Test
    public void testUnmodifiableSet_1_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();
        assertEquals(true, set1.isEmpty());
    }

    @Test
    public void testUnmodifiableSet_2_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.unmodifiableSet(1, 2, 2, 3);
        assertEquals(4, set2.size());
    }

    @Test
    public void testUnmodifiableSet_3_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.unmodifiableSet(1, 2, 2, 3);
        assertEquals(false, set2.containsAll(set1));
    }

    @Test
    public void testUnmodifiableSet_4_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.unmodifiableSet(1, 2, 2, 3);
        assertEquals(false, set2.containsAll(set1));
    }

    @Test
    public void testUnmodifiableSet_5_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.unmodifiableSet(1, 2, 2, 3);
        assertEquals(false, set2.containsAll(set1));
    }

    @Test
    public void testUnmodifiableSet_6_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.unmodifiableSet(1, 2, 2, 3);

        Set<String> set3 = SetUtils.unmodifiableSet("1", "2", "2", "3");
        assertEquals(4, set2.size());
    }

    @Test
    public void testUnmodifiableSet_7_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.unmodifiableSet(1, 2, 2, 3);

        Set<String> set3 = SetUtils.unmodifiableSet("1", "2", "2", "3");
        assertEquals(false, set2.containsAll(set3));
    }

    @Test
    public void testUnmodifiableSet_8_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.unmodifiableSet(1, 2, 2, 3);

        Set<String> set3 = SetUtils.unmodifiableSet("1", "2", "2", "3");
        assertEquals(false, set2.containsAll(set3));
    }

    @Test
    public void testUnmodifiableSet_9_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.unmodifiableSet(1, 2, 2, 3);

        Set<String> set3 = SetUtils.unmodifiableSet("1", "2", "2", "3");
        assertEquals(false, set2.containsAll(set3));
    }

    @Test
    public void testUnmodifiableSet_10_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.unmodifiableSet(1, 2, 2, 3);

        Set<String> set3 = SetUtils.unmodifiableSet("1", "2", "2", "3");

        Set<?> set4 = SetUtils.unmodifiableSet(null, null);
        assertEquals(4, set2.size());
    }

    @Test
    public void testUnmodifiableSet_11_oe()
    {
        Set<?> set1 = SetUtils.unmodifiableSet();

        Set<Integer> set2 = SetUtils.unmodifiableSet(1, 2, 2, 3);

        Set<String> set3 = SetUtils.unmodifiableSet("1", "2", "2", "3");

        Set<?> set4 = SetUtils.unmodifiableSet(null, null);
        assertEquals(false, set4.contains(null));
    }

    @Test
    public void union_1_oe() {
        final SetView<Integer> set = SetUtils.union(setA, setB);
        assertEquals(0, set.size());
    }

}
