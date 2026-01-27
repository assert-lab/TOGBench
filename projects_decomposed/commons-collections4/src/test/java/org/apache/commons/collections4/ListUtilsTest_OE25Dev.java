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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.list.PredicatedList;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for ListUtils.
 *
 */
public class ListUtilsTest_OE25Dev {

    private static final String a = "a";
    private static final String b = "b";
    private static final String c = "c";
    private static final String d = "d";
    private static final String e = "e";
    private static final String x = "x";

    private String[] fullArray;
    private List<String> fullList;

    @Before
    public void setUp() {
        fullArray = new String[]{a, b, c, d, e};
        fullList = new ArrayList<>(Arrays.asList(fullArray));
    }

    /**
     * Tests intersecting a non-empty list with an empty list.
     */

    /**
     * Tests intersecting a non-empty list with an empty list.
     */

    /**
     * Tests intersecting a non-empty list with an subset of itself.
     */

    /**
     * Tests intersecting a non-empty list with an subset of itself.
     */

    /**
     * Tests intersecting a non-empty list with itself.
     */

    /**
     * Tests intersecting two lists in different orders.
     */

    /**
     * Tests the <code>indexOf</code> method in <code>ListUtils</code> class..
     */

    private static Predicate<Number> EQUALS_TWO = new Predicate<Number>() {
        @Override
        public boolean evaluate(final Number input) {
            return input.intValue() == 2;
        }
    };

    @Test
    public void testIntersectNonEmptyWithEmptyList_1_oe() {
        final List<String> empty = Collections.<String>emptyList();
        assertTrue("result not empty", ListUtils.intersection(empty, fullList).isEmpty());
    }

    @Test
    public void testIntersectEmptyWithEmptyList_1_oe() {
        final List<?> empty = Collections.EMPTY_LIST;
        assertTrue("result not empty", ListUtils.intersection(empty, empty).isEmpty());
    }

    @Test
    public void testIntersectNonEmptySubset_1_oe() {
        // create a copy
        final List<String> other = new ArrayList<>(fullList);

        // remove a few items
        assertNotNull(other.remove(0));
    }

    @Test
    public void testIntersectNonEmptySubset_2_oe() {
        // create a copy
        final List<String> other = new ArrayList<>(fullList);

        // remove a few items
        // removed other assertion
        assertNotNull(other.remove(1));
    }

    @Test
    public void testIntersectNonEmptySubset_3_oe() {
        // create a copy
        final List<String> other = new ArrayList<>(fullList);

        // remove a few items
        // removed other assertion
        // removed other assertion

        // make sure the intersection is equal to the copy
        assertEquals(other, ListUtils.intersection(fullList, other));
    }

    @Test
    public void testIntersectListWithNoOverlapAndDifferentTypes_1_oe() {
        @SuppressWarnings("boxing")
        final List<Integer> other = Arrays.asList(1, 23);
        assertTrue(ListUtils.intersection(fullList, other).isEmpty());
    }

    @Test
    public void testIntersectListWithSelf_1_oe() {
        assertEquals(fullList, ListUtils.intersection(fullList, fullList));
    }

    @Test
    public void testIntersectionOrderInsensitivity_1_oe() {
        final List<String> one = new ArrayList<>();
        final List<String> two = new ArrayList<>();
        one.add("a");
        one.add("b");
        two.add("a");
        two.add("a");
        two.add("b");
        two.add("b");
        assertEquals(ListUtils.intersection(one,two),ListUtils.intersection(two, one));
    }

    @Test
    public void testPredicatedList_1_oe() {
        final Predicate<Object> predicate = new Predicate<Object>() {
            @Override
            public boolean evaluate(final Object o) {
                return o instanceof String;
            }
        };
        final List<Object> list = ListUtils.predicatedList(new ArrayList<>(), predicate);
        assertTrue("returned object should be a PredicatedList", list instanceof PredicatedList);
    }

    @Test
    public void testLazyFactoryList_1_oe() {
        final List<Integer> list = ListUtils.lazyList(new ArrayList<Integer>(), new Factory<Integer>() {

            private int index;

            @Override
            public Integer create() {
                index++;
                return Integer.valueOf(index);
            }
        });

        assertNotNull(list.get(5));
    }

    @Test
    public void testLazyFactoryList_3_oe() {
        final List<Integer> list = ListUtils.lazyList(new ArrayList<Integer>(), new Factory<Integer>() {

            private int index;

            @Override
            public Integer create() {
                index++;
                return Integer.valueOf(index);
            }
        });

        // removed other assertion
        // removed other assertion

        assertNotNull(list.get(5));
    }

    @Test
    public void testLazyTransformerList_1_oe() {
        final List<Integer> offsets = Arrays.asList(3, 5, 1, 5, 3, 6);
        final List<Integer> list = ListUtils.lazyList(new ArrayList<>(), new Transformer<Integer, Integer>() {

            private int index;

            @Override
            public Integer transform(Integer input) {
                return offsets.get(input) + index++;
            }

        });

        assertNotNull(list.get(5));
    }

    @Test
    public void testLazyTransformerList_3_oe() {
        final List<Integer> offsets = Arrays.asList(3, 5, 1, 5, 3, 6);
        final List<Integer> list = ListUtils.lazyList(new ArrayList<>(), new Transformer<Integer, Integer>() {

            private int index;

            @Override
            public Integer transform(Integer input) {
                return offsets.get(input) + index++;
            }

        });

        // removed other assertion
        // removed other assertion

        assertNotNull(list.get(5));
    }

    @Test
    public void testEmptyIfNull_1_oe() {
        assertTrue(ListUtils.emptyIfNull(null).isEmpty());
    }

    @Test
    public void testEmptyIfNull_2_oe() {
        // removed other assertion

        final List<Long> list = new ArrayList<>();
        assertSame(list, ListUtils.emptyIfNull(list));
    }

    @Test
    public void testDefaultIfNull_1_oe() {
        assertTrue(ListUtils.defaultIfNull(null, Collections.emptyList()).isEmpty());
    }

    @Test
    public void testDefaultIfNull_2_oe() {
        // removed other assertion

        final List<Long> list = new ArrayList<>();
        assertSame(list, ListUtils.defaultIfNull(list, Collections.<Long>emptyList()));
    }

    @Test
    public void testEquals_1_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final List<String> a = new ArrayList<>( data );
        final List<String> b = new ArrayList<>( data );

        assertEquals(true, a.equals(b));
    }

    @Test
    public void testEquals_2_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final List<String> a = new ArrayList<>( data );
        final List<String> b = new ArrayList<>( data );

        // removed other assertion
        assertEquals(true, ListUtils.isEqualList(a, b));
    }

    @Test
    public void testEquals_3_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final List<String> a = new ArrayList<>( data );
        final List<String> b = new ArrayList<>( data );

        // removed other assertion
        // removed other assertion
        a.clear();
        assertEquals(false, ListUtils.isEqualList(a, b));
    }

    @Test
    public void testEquals_4_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final List<String> a = new ArrayList<>( data );
        final List<String> b = new ArrayList<>( data );

        // removed other assertion
        // removed other assertion
        a.clear();
        // removed other assertion
        assertEquals(false, ListUtils.isEqualList(a, null));
    }

    @Test
    public void testEquals_5_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final List<String> a = new ArrayList<>( data );
        final List<String> b = new ArrayList<>( data );

        // removed other assertion
        // removed other assertion
        a.clear();
        // removed other assertion
        // removed other assertion
        assertEquals(false, ListUtils.isEqualList(null, b));
    }

    @Test
    public void testEquals_6_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final List<String> a = new ArrayList<>( data );
        final List<String> b = new ArrayList<>( data );

        // removed other assertion
        // removed other assertion
        a.clear();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, ListUtils.isEqualList(null, null));
    }

    @Test
    public void testHashCode_1_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final List<String> a = new ArrayList<>(data);
        final List<String> b = new ArrayList<>(data);

        assertEquals(true, a.hashCode() == b.hashCode());
    }

    @Test
    public void testHashCode_2_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final List<String> a = new ArrayList<>(data);
        final List<String> b = new ArrayList<>(data);

        // removed other assertion
        assertEquals(true, a.hashCode() == ListUtils.hashCodeForList(a));
    }

    @Test
    public void testHashCode_3_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final List<String> a = new ArrayList<>(data);
        final List<String> b = new ArrayList<>(data);

        // removed other assertion
        // removed other assertion
        assertEquals(true, b.hashCode() == ListUtils.hashCodeForList(b));
    }

    @Test
    public void testHashCode_4_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final List<String> a = new ArrayList<>(data);
        final List<String> b = new ArrayList<>(data);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true, ListUtils.hashCodeForList(a) == ListUtils.hashCodeForList(b));
    }

    @Test
    public void testHashCode_5_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final List<String> a = new ArrayList<>(data);
        final List<String> b = new ArrayList<>(data);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        a.clear();
        assertEquals(false, ListUtils.hashCodeForList(a) == ListUtils.hashCodeForList(b));
    }

    @Test
    public void testHashCode_6_oe() {
        final Collection<String> data = Arrays.asList("a", "b", "c");

        final List<String> a = new ArrayList<>(data);
        final List<String> b = new ArrayList<>(data);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        a.clear();
        // removed other assertion
        assertEquals(0, ListUtils.hashCodeForList(null));
    }

    @Test
    public void testRetainAll_1_oe() {
        final List<String> sub = new ArrayList<>();
        sub.add(a);
        sub.add(b);
        sub.add(x);

        final List<String> retained = ListUtils.retainAll(fullList, sub);
        assertTrue(retained.size() == 2);
    }

    @Test
    public void testRetainAll_2_oe() {
        final List<String> sub = new ArrayList<>();
        sub.add(a);
        sub.add(b);
        sub.add(x);

        final List<String> retained = ListUtils.retainAll(fullList, sub);
        // removed other assertion
        sub.remove(x);
        assertTrue(retained.equals(sub));
    }

    @Test
    public void testRetainAll_3_oe() {
        final List<String> sub = new ArrayList<>();
        sub.add(a);
        sub.add(b);
        sub.add(x);

        final List<String> retained = ListUtils.retainAll(fullList, sub);
        // removed other assertion
        sub.remove(x);
        // removed other assertion
        fullList.retainAll(sub);
        assertTrue(retained.equals(fullList));
    }

    @Test
    public void testRemoveAll_1_oe() {
        final List<String> sub = new ArrayList<>();
        sub.add(a);
        sub.add(b);
        sub.add(x);

        final List<String> remainder = ListUtils.removeAll(fullList, sub);
        assertTrue(remainder.size() == 3);
    }

    @Test
    public void testRemoveAll_2_oe() {
        final List<String> sub = new ArrayList<>();
        sub.add(a);
        sub.add(b);
        sub.add(x);

        final List<String> remainder = ListUtils.removeAll(fullList, sub);
        // removed other assertion
        fullList.removeAll(sub);
        assertTrue(remainder.equals(fullList));
    }

    @Test
    public void testSubtract_1_oe() {
        final List<String> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(a);
        list.add(x);

        final List<String> sub = new ArrayList<>();
        sub.add(a);

        final List<String> result = ListUtils.subtract(list, sub);
        assertTrue(result.size() == 3);
    }

    @Test
    public void testSubtract_2_oe() {
        final List<String> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(a);
        list.add(x);

        final List<String> sub = new ArrayList<>();
        sub.add(a);

        final List<String> result = ListUtils.subtract(list, sub);
        // removed other assertion

        final List<String> expected = new ArrayList<>();
        expected.add(b);
        expected.add(a);
        expected.add(x);

        assertEquals(expected, result);
    }

    @Test
    public void testSubtractNullElement_1_oe() {
        final List<String> list = new ArrayList<>();
        list.add(a);
        list.add(null);
        list.add(null);
        list.add(x);

        final List<String> sub = new ArrayList<>();
        sub.add(null);

        final List<String> result = ListUtils.subtract(list, sub);
        assertTrue(result.size() == 3);
    }

    @Test
    public void testSubtractNullElement_2_oe() {
        final List<String> list = new ArrayList<>();
        list.add(a);
        list.add(null);
        list.add(null);
        list.add(x);

        final List<String> sub = new ArrayList<>();
        sub.add(null);

        final List<String> result = ListUtils.subtract(list, sub);
        // removed other assertion

        final List<String> expected = new ArrayList<>();
        expected.add(a);
        expected.add(null);
        expected.add(x);

        assertEquals(expected, result);
    }

    @Test
    public void testIndexOf_1_oe() {
        Predicate<String> testPredicate = EqualPredicate.equalPredicate("d");
        int index = ListUtils.indexOf(fullList, testPredicate);
        assertEquals(d, fullList.get(index));
    }

    @Test
    public void testIndexOf_2_oe() {
        Predicate<String> testPredicate = EqualPredicate.equalPredicate("d");
        int index = ListUtils.indexOf(fullList, testPredicate);
        // removed other assertion

        testPredicate = EqualPredicate.equalPredicate("de");
        index = ListUtils.indexOf(fullList, testPredicate);
        assertEquals(index, -1);
    }

    @Test
    public void testIndexOf_3_oe() {
        Predicate<String> testPredicate = EqualPredicate.equalPredicate("d");
        int index = ListUtils.indexOf(fullList, testPredicate);
        // removed other assertion

        testPredicate = EqualPredicate.equalPredicate("de");
        index = ListUtils.indexOf(fullList, testPredicate);
        // removed other assertion

        assertEquals(ListUtils.indexOf(null,testPredicate), -1);
    }

    @Test
    public void testIndexOf_4_oe() {
        Predicate<String> testPredicate = EqualPredicate.equalPredicate("d");
        int index = ListUtils.indexOf(fullList, testPredicate);
        // removed other assertion

        testPredicate = EqualPredicate.equalPredicate("de");
        index = ListUtils.indexOf(fullList, testPredicate);
        // removed other assertion

        // removed other assertion
        assertEquals(ListUtils.indexOf(fullList, null), -1);
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testLongestCommonSubsequence_4_oe() {

        try {
            ListUtils.longestCommonSubsequence((List<?>) null, null);
            // removed other assertion
        } catch (final NullPointerException e) {}

        try {
            ListUtils.longestCommonSubsequence(Arrays.asList('A'), null);
            // removed other assertion
        } catch (final NullPointerException e) {}

        try {
            ListUtils.longestCommonSubsequence(null, Arrays.asList('A'));
            // removed other assertion
        } catch (final NullPointerException e) {}

        @SuppressWarnings("unchecked")
        List<Character> lcs = ListUtils.longestCommonSubsequence(Collections.EMPTY_LIST, Collections.EMPTY_LIST);
        assertEquals(0, lcs.size());
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testLongestCommonSubsequence_5_oe() {

        try {
            ListUtils.longestCommonSubsequence((List<?>) null, null);
            // removed other assertion
        } catch (final NullPointerException e) {}

        try {
            ListUtils.longestCommonSubsequence(Arrays.asList('A'), null);
            // removed other assertion
        } catch (final NullPointerException e) {}

        try {
            ListUtils.longestCommonSubsequence(null, Arrays.asList('A'));
            // removed other assertion
        } catch (final NullPointerException e) {}

        @SuppressWarnings("unchecked")
        List<Character> lcs = ListUtils.longestCommonSubsequence(Collections.EMPTY_LIST, Collections.EMPTY_LIST);
        // removed other assertion

        final List<Character> list1 = Arrays.asList('B', 'A', 'N', 'A', 'N', 'A');
        final List<Character> list2 = Arrays.asList('A', 'N', 'A', 'N', 'A', 'S');
        lcs = ListUtils.longestCommonSubsequence(list1, list2);

        List<Character> expected = Arrays.asList('A', 'N', 'A', 'N', 'A');
        assertEquals(expected, lcs);
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testLongestCommonSubsequence_6_oe() {

        try {
            ListUtils.longestCommonSubsequence((List<?>) null, null);
            // removed other assertion
        } catch (final NullPointerException e) {}

        try {
            ListUtils.longestCommonSubsequence(Arrays.asList('A'), null);
            // removed other assertion
        } catch (final NullPointerException e) {}

        try {
            ListUtils.longestCommonSubsequence(null, Arrays.asList('A'));
            // removed other assertion
        } catch (final NullPointerException e) {}

        @SuppressWarnings("unchecked")
        List<Character> lcs = ListUtils.longestCommonSubsequence(Collections.EMPTY_LIST, Collections.EMPTY_LIST);
        // removed other assertion

        final List<Character> list1 = Arrays.asList('B', 'A', 'N', 'A', 'N', 'A');
        final List<Character> list2 = Arrays.asList('A', 'N', 'A', 'N', 'A', 'S');
        lcs = ListUtils.longestCommonSubsequence(list1, list2);

        List<Character> expected = Arrays.asList('A', 'N', 'A', 'N', 'A');
        // removed other assertion

        final List<Character> list3 = Arrays.asList('A', 'T', 'A', 'N', 'A');
        lcs = ListUtils.longestCommonSubsequence(list1, list3);

        expected = Arrays.asList('A', 'A', 'N', 'A');
        assertEquals(expected, lcs);
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testLongestCommonSubsequence_7_oe() {

        try {
            ListUtils.longestCommonSubsequence((List<?>) null, null);
            // removed other assertion
        } catch (final NullPointerException e) {}

        try {
            ListUtils.longestCommonSubsequence(Arrays.asList('A'), null);
            // removed other assertion
        } catch (final NullPointerException e) {}

        try {
            ListUtils.longestCommonSubsequence(null, Arrays.asList('A'));
            // removed other assertion
        } catch (final NullPointerException e) {}

        @SuppressWarnings("unchecked")
        List<Character> lcs = ListUtils.longestCommonSubsequence(Collections.EMPTY_LIST, Collections.EMPTY_LIST);
        // removed other assertion

        final List<Character> list1 = Arrays.asList('B', 'A', 'N', 'A', 'N', 'A');
        final List<Character> list2 = Arrays.asList('A', 'N', 'A', 'N', 'A', 'S');
        lcs = ListUtils.longestCommonSubsequence(list1, list2);

        List<Character> expected = Arrays.asList('A', 'N', 'A', 'N', 'A');
        // removed other assertion

        final List<Character> list3 = Arrays.asList('A', 'T', 'A', 'N', 'A');
        lcs = ListUtils.longestCommonSubsequence(list1, list3);

        expected = Arrays.asList('A', 'A', 'N', 'A');
        // removed other assertion

        final List<Character> listZorro = Arrays.asList('Z', 'O', 'R', 'R', 'O');
        lcs = ListUtils.longestCommonSubsequence(list1, listZorro);

        assertTrue(lcs.isEmpty());
    }

    @Test
    public void testLongestCommonSubsequenceWithString_4_oe() {

      try {
          ListUtils.longestCommonSubsequence((String) null, null);
          // removed other assertion
      } catch (final NullPointerException e) {}

      try {
          ListUtils.longestCommonSubsequence("A", null);
          // removed other assertion
      } catch (final NullPointerException e) {}

      try {
          ListUtils.longestCommonSubsequence(null, "A");
          // removed other assertion
      } catch (final NullPointerException e) {}

      String lcs = ListUtils.longestCommonSubsequence("", "");
      assertEquals(0, lcs.length());
    }

    @Test
    public void testLongestCommonSubsequenceWithString_5_oe() {

      try {
          ListUtils.longestCommonSubsequence((String) null, null);
          // removed other assertion
      } catch (final NullPointerException e) {}

      try {
          ListUtils.longestCommonSubsequence("A", null);
          // removed other assertion
      } catch (final NullPointerException e) {}

      try {
          ListUtils.longestCommonSubsequence(null, "A");
          // removed other assertion
      } catch (final NullPointerException e) {}

      String lcs = ListUtils.longestCommonSubsequence("", "");
      // removed other assertion

      final String banana = "BANANA";
      final String ananas = "ANANAS";
      lcs = ListUtils.longestCommonSubsequence(banana, ananas);

      assertEquals("ANANA", lcs);
    }

    @Test
    public void testLongestCommonSubsequenceWithString_6_oe() {

      try {
          ListUtils.longestCommonSubsequence((String) null, null);
          // removed other assertion
      } catch (final NullPointerException e) {}

      try {
          ListUtils.longestCommonSubsequence("A", null);
          // removed other assertion
      } catch (final NullPointerException e) {}

      try {
          ListUtils.longestCommonSubsequence(null, "A");
          // removed other assertion
      } catch (final NullPointerException e) {}

      String lcs = ListUtils.longestCommonSubsequence("", "");
      // removed other assertion

      final String banana = "BANANA";
      final String ananas = "ANANAS";
      lcs = ListUtils.longestCommonSubsequence(banana, ananas);

      // removed other assertion

      final String atana = "ATANA";
      lcs = ListUtils.longestCommonSubsequence(banana, atana);

      assertEquals("AANA", lcs);
    }

    @Test
    public void testLongestCommonSubsequenceWithString_7_oe() {

      try {
          ListUtils.longestCommonSubsequence((String) null, null);
          // removed other assertion
      } catch (final NullPointerException e) {}

      try {
          ListUtils.longestCommonSubsequence("A", null);
          // removed other assertion
      } catch (final NullPointerException e) {}

      try {
          ListUtils.longestCommonSubsequence(null, "A");
          // removed other assertion
      } catch (final NullPointerException e) {}

      String lcs = ListUtils.longestCommonSubsequence("", "");
      // removed other assertion

      final String banana = "BANANA";
      final String ananas = "ANANAS";
      lcs = ListUtils.longestCommonSubsequence(banana, ananas);

      // removed other assertion

      final String atana = "ATANA";
      lcs = ListUtils.longestCommonSubsequence(banana, atana);

      // removed other assertion

      final String zorro = "ZORRO";
      lcs = ListUtils.longestCommonSubsequence(banana, zorro);

      assertEquals(0, lcs.length());
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testPartition_1_oe() {
        final List<Integer> strings = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            strings.add(i);
        }

        final List<List<Integer>> partition = ListUtils.partition(strings, 3);

        assertNotNull(partition);
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testPartition_2_oe() {
        final List<Integer> strings = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            strings.add(i);
        }

        final List<List<Integer>> partition = ListUtils.partition(strings, 3);

        // removed other assertion
        assertEquals(3, partition.size());
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testPartition_3_oe() {
        final List<Integer> strings = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            strings.add(i);
        }

        final List<List<Integer>> partition = ListUtils.partition(strings, 3);

        // removed other assertion
        // removed other assertion
        assertEquals(1, partition.get(2).size());
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testPartition_7_oe() {
        final List<Integer> strings = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            strings.add(i);
        }

        final List<List<Integer>> partition = ListUtils.partition(strings, 3);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            ListUtils.partition(null, 3);
            // removed other assertion
        } catch (final NullPointerException e) {}

        try {
            ListUtils.partition(strings, 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {}

        try {
            ListUtils.partition(strings, -10);
            // removed other assertion
        } catch (final IllegalArgumentException e) {}

        final List<List<Integer>> partitionMax = ListUtils.partition(strings, Integer.MAX_VALUE);
        assertEquals(1, partitionMax.size());
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testPartition_8_oe() {
        final List<Integer> strings = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            strings.add(i);
        }

        final List<List<Integer>> partition = ListUtils.partition(strings, 3);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            ListUtils.partition(null, 3);
            // removed other assertion
        } catch (final NullPointerException e) {}

        try {
            ListUtils.partition(strings, 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {}

        try {
            ListUtils.partition(strings, -10);
            // removed other assertion
        } catch (final IllegalArgumentException e) {}

        final List<List<Integer>> partitionMax = ListUtils.partition(strings, Integer.MAX_VALUE);
        // removed other assertion
        assertEquals(strings.size(), partitionMax.get(0).size());
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testPartition_9_oe() {
        final List<Integer> strings = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            strings.add(i);
        }

        final List<List<Integer>> partition = ListUtils.partition(strings, 3);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            ListUtils.partition(null, 3);
            // removed other assertion
        } catch (final NullPointerException e) {}

        try {
            ListUtils.partition(strings, 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {}

        try {
            ListUtils.partition(strings, -10);
            // removed other assertion
        } catch (final IllegalArgumentException e) {}

        final List<List<Integer>> partitionMax = ListUtils.partition(strings, Integer.MAX_VALUE);
        // removed other assertion
        // removed other assertion
        assertEquals(strings, partitionMax.get(0));
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testSelect_1_oe() {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        // Ensure that the collection is the input type or a super type
        final List<Integer> output1 = ListUtils.select(list, EQUALS_TWO);
        final List<Number> output2 = ListUtils.<Number>select(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.select(list, EQUALS_TWO, new HashSet<Number>());
        assertTrue(CollectionUtils.isEqualCollection(output1, output3));
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testSelect_2_oe() {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        // Ensure that the collection is the input type or a super type
        final List<Integer> output1 = ListUtils.select(list, EQUALS_TWO);
        final List<Number> output2 = ListUtils.<Number>select(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.select(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        assertEquals(4, list.size());
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testSelect_3_oe() {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        // Ensure that the collection is the input type or a super type
        final List<Integer> output1 = ListUtils.select(list, EQUALS_TWO);
        final List<Number> output2 = ListUtils.<Number>select(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.select(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        // removed other assertion
        assertEquals(1, output1.size());
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testSelect_4_oe() {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        // Ensure that the collection is the input type or a super type
        final List<Integer> output1 = ListUtils.select(list, EQUALS_TWO);
        final List<Number> output2 = ListUtils.<Number>select(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.select(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, output2.iterator().next());
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testSelectRejected_1_oe() {
        final List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        final List<Long> output1 = ListUtils.selectRejected(list, EQUALS_TWO);
        final List<? extends Number> output2 = ListUtils.selectRejected(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.selectRejected(list, EQUALS_TWO, new HashSet<Number>());
        assertTrue(CollectionUtils.isEqualCollection(output1, output2));
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testSelectRejected_2_oe() {
        final List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        final List<Long> output1 = ListUtils.selectRejected(list, EQUALS_TWO);
        final List<? extends Number> output2 = ListUtils.selectRejected(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.selectRejected(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        assertTrue(CollectionUtils.isEqualCollection(output1, output3));
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testSelectRejected_3_oe() {
        final List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        final List<Long> output1 = ListUtils.selectRejected(list, EQUALS_TWO);
        final List<? extends Number> output2 = ListUtils.selectRejected(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.selectRejected(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        // removed other assertion
        assertEquals(4, list.size());
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testSelectRejected_4_oe() {
        final List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        final List<Long> output1 = ListUtils.selectRejected(list, EQUALS_TWO);
        final List<? extends Number> output2 = ListUtils.selectRejected(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.selectRejected(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, output1.size());
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testSelectRejected_5_oe() {
        final List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        final List<Long> output1 = ListUtils.selectRejected(list, EQUALS_TWO);
        final List<? extends Number> output2 = ListUtils.selectRejected(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.selectRejected(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(output1.contains(1L));
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testSelectRejected_6_oe() {
        final List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        final List<Long> output1 = ListUtils.selectRejected(list, EQUALS_TWO);
        final List<? extends Number> output2 = ListUtils.selectRejected(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.selectRejected(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(output1.contains(3L));
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testSelectRejected_7_oe() {
        final List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        final List<Long> output1 = ListUtils.selectRejected(list, EQUALS_TWO);
        final List<? extends Number> output2 = ListUtils.selectRejected(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.selectRejected(list, EQUALS_TWO, new HashSet<Number>());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(output1.contains(4L));
    }

}
