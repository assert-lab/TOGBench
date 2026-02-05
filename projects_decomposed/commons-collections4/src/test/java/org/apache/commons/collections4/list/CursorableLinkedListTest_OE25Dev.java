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
package org.apache.commons.collections4.list;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import junit.framework.Test;

import org.apache.commons.collections4.BulkTest;

/**
 * Test class.
 *
 */
public class CursorableLinkedListTest_OE25Dev<E> extends AbstractLinkedListTest<E> {
    public CursorableLinkedListTest_OE25Dev(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return new junit.framework.TestSuite(CursorableLinkedListTest_OE25Dev.class);
    }

    private CursorableLinkedList<E> list;

    @Override
    public void setUp() {
        list = new CursorableLinkedList<>();
    }

    @Override
    public CursorableLinkedList<E> makeObject() {
        return new CursorableLinkedList<>();
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    /**
     *  Ignore the serialization tests for sublists and sub-sublists.
     *
     *  @return an array of sublist serialization test names
     */
    @Override
    public String[] ignoredTests() {
        final ArrayList<String> list = new ArrayList<>();
        final String prefix = "CursorableLinkedListTest_OE25Dev";
        final String bulk = ".bulkTestSubList";
        final String[] ignored = new String[] {
                ".testEmptyListSerialization",
                ".testFullListSerialization",
                ".testEmptyListCompatibility",
                ".testFullListCompatibility",
                ".testSimpleSerialization",
                ".testCanonicalEmptyCollectionExists",
                ".testCanonicalFullCollectionExists",
                ".testSerializeDeserializeThenCompare"
        };
        for (final String element : ignored) {
            list.add(prefix + bulk + element);
            list.add(prefix + bulk + bulk + element);
        }
        return list.toArray(new String[0]);
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

//    public void testCreate() throws Exception {
//        resetEmpty();
//        writeExternalFormToDisk((java.io.Serializable) getCollection(), "src/test/resources/data/test/CursorableLinkedList.emptyCollection.version4.obj");
//        resetFull();
//        writeExternalFormToDisk((java.io.Serializable) getCollection(), "src/test/resources/data/test/CursorableLinkedList.fullCollection.version4.obj");
//    }

public void testAdd_1_oe() {
        assertEquals("[]",list.toString());
    }

public void testAdd_2_oe() {
        // removed other assertion
        assertTrue(list.add((E) Integer.valueOf(1)));
    }

public void testAdd_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("[1]",list.toString());
    }

public void testAdd_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.add((E) Integer.valueOf(2)));
    }

public void testAdd_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[1, 2]",list.toString());
    }

public void testAdd_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.add((E) Integer.valueOf(3)));
    }

public void testAdd_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[1, 2, 3]",list.toString());
    }

public void testAdd_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.addFirst((E) Integer.valueOf(0)));
    }

public void testAdd_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[0, 1, 2, 3]",list.toString());
    }

public void testAdd_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.addLast((E) Integer.valueOf(4)));
    }

public void testAdd_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[0, 1, 2, 3, 4]",list.toString());
    }

public void testAdd_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.add(0,(E) Integer.valueOf(-2));
        assertEquals("[-2, 0, 1, 2, 3, 4]",list.toString());
    }

public void testAdd_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.add(0,(E) Integer.valueOf(-2));
        // removed other assertion
        list.add(1,(E) Integer.valueOf(-1));
        assertEquals("[-2, -1, 0, 1, 2, 3, 4]",list.toString());
    }

public void testAdd_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.add(0,(E) Integer.valueOf(-2));
        // removed other assertion
        list.add(1,(E) Integer.valueOf(-1));
        // removed other assertion
        list.add(7,(E) Integer.valueOf(5));
        assertEquals("[-2, -1, 0, 1, 2, 3, 4, 5]",list.toString());
    }

public void testAdd_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.add(0,(E) Integer.valueOf(-2));
        // removed other assertion
        list.add(1,(E) Integer.valueOf(-1));
        // removed other assertion
        list.add(7,(E) Integer.valueOf(5));
        // removed other assertion

        final List<E> list2 = new LinkedList<>();
        list2.add((E) "A");
        list2.add((E) "B");
        list2.add((E) "C");

        assertTrue(list.addAll(list2));
    }

public void testAdd_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.add(0,(E) Integer.valueOf(-2));
        // removed other assertion
        list.add(1,(E) Integer.valueOf(-1));
        // removed other assertion
        list.add(7,(E) Integer.valueOf(5));
        // removed other assertion

        final List<E> list2 = new LinkedList<>();
        list2.add((E) "A");
        list2.add((E) "B");
        list2.add((E) "C");

        // removed other assertion
        assertEquals("[-2, -1, 0, 1, 2, 3, 4, 5, A, B, C]",list.toString());
    }

public void testAdd_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.add(0,(E) Integer.valueOf(-2));
        // removed other assertion
        list.add(1,(E) Integer.valueOf(-1));
        // removed other assertion
        list.add(7,(E) Integer.valueOf(5));
        // removed other assertion

        final List<E> list2 = new LinkedList<>();
        list2.add((E) "A");
        list2.add((E) "B");
        list2.add((E) "C");

        // removed other assertion
        // removed other assertion
        assertTrue(list.addAll(3,list2));
    }

public void testAdd_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.add(0,(E) Integer.valueOf(-2));
        // removed other assertion
        list.add(1,(E) Integer.valueOf(-1));
        // removed other assertion
        list.add(7,(E) Integer.valueOf(5));
        // removed other assertion

        final List<E> list2 = new LinkedList<>();
        list2.add((E) "A");
        list2.add((E) "B");
        list2.add((E) "C");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[-2, -1, 0, A, B, C, 1, 2, 3, 4, 5, A, B, C]",list.toString());
    }

public void testClear_1_oe() {
        assertEquals(0,list.size());
    }

public void testClear_2_oe() {
        // removed other assertion
        assertTrue(list.isEmpty());
    }

public void testClear_3_oe() {
        // removed other assertion
        // removed other assertion
        list.clear();
        assertEquals(0,list.size());
    }

public void testClear_4_oe() {
        // removed other assertion
        // removed other assertion
        list.clear();
        // removed other assertion
        assertTrue(list.isEmpty());
    }

public void testClear_5_oe() {
        // removed other assertion
        // removed other assertion
        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element");
        assertEquals(1,list.size());
    }

public void testClear_6_oe() {
        // removed other assertion
        // removed other assertion
        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element");
        // removed other assertion
        assertTrue(!list.isEmpty());
    }

public void testClear_7_oe() {
        // removed other assertion
        // removed other assertion
        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element");
        // removed other assertion
        // removed other assertion

        list.clear();
        assertEquals(0,list.size());
    }

public void testClear_8_oe() {
        // removed other assertion
        // removed other assertion
        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element");
        // removed other assertion
        // removed other assertion

        list.clear();
        // removed other assertion
        assertTrue(list.isEmpty());
    }

public void testClear_9_oe() {
        // removed other assertion
        // removed other assertion
        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element");
        // removed other assertion
        // removed other assertion

        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element1");
        list.add((E) "element2");
        assertEquals(2,list.size());
    }

public void testClear_10_oe() {
        // removed other assertion
        // removed other assertion
        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element");
        // removed other assertion
        // removed other assertion

        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element1");
        list.add((E) "element2");
        // removed other assertion
        assertTrue(!list.isEmpty());
    }

public void testClear_11_oe() {
        // removed other assertion
        // removed other assertion
        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element");
        // removed other assertion
        // removed other assertion

        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element1");
        list.add((E) "element2");
        // removed other assertion
        // removed other assertion

        list.clear();
        assertEquals(0,list.size());
    }

public void testClear_12_oe() {
        // removed other assertion
        // removed other assertion
        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element");
        // removed other assertion
        // removed other assertion

        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element1");
        list.add((E) "element2");
        // removed other assertion
        // removed other assertion

        list.clear();
        // removed other assertion
        assertTrue(list.isEmpty());
    }

public void testClear_13_oe() {
        // removed other assertion
        // removed other assertion
        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element");
        // removed other assertion
        // removed other assertion

        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element1");
        list.add((E) "element2");
        // removed other assertion
        // removed other assertion

        list.clear();
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 1000; i++) {
            list.add((E) Integer.valueOf(i));
        }
        assertEquals(1000, list.size());
    }

public void testClear_14_oe() {
        // removed other assertion
        // removed other assertion
        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element");
        // removed other assertion
        // removed other assertion

        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element1");
        list.add((E) "element2");
        // removed other assertion
        // removed other assertion

        list.clear();
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 1000; i++) {
            list.add((E) Integer.valueOf(i));
        }
        // removed other assertion
        assertTrue(!list.isEmpty());
    }

public void testClear_15_oe() {
        // removed other assertion
        // removed other assertion
        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element");
        // removed other assertion
        // removed other assertion

        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element1");
        list.add((E) "element2");
        // removed other assertion
        // removed other assertion

        list.clear();
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 1000; i++) {
            list.add((E) Integer.valueOf(i));
        }
        // removed other assertion
        // removed other assertion

        list.clear();
        assertEquals(0,list.size());
    }

public void testClear_16_oe() {
        // removed other assertion
        // removed other assertion
        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element");
        // removed other assertion
        // removed other assertion

        list.clear();
        // removed other assertion
        // removed other assertion

        list.add((E) "element1");
        list.add((E) "element2");
        // removed other assertion
        // removed other assertion

        list.clear();
        // removed other assertion
        // removed other assertion

        for (int i = 0; i < 1000; i++) {
            list.add((E) Integer.valueOf(i));
        }
        // removed other assertion
        // removed other assertion

        list.clear();
        // removed other assertion
        assertTrue(list.isEmpty());
    }

public void testContains_1_oe() {
        assertTrue(!list.contains("A"));
    }

public void testContains_2_oe() {
        // removed other assertion
        assertTrue(list.add((E) "A"));
    }

public void testContains_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue(list.contains("A"));
    }

public void testContains_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.add((E) "B"));
    }

public void testContains_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.contains("A"));
    }

public void testContains_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.addFirst((E) "a"));
    }

public void testContains_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.contains("A"));
    }

public void testContains_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.remove("a"));
    }

public void testContains_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.contains("A"));
    }

public void testContains_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.remove("A"));
    }

public void testContains_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(!list.contains("A"));
    }

public void testContainsAll_1_oe() {
        assertTrue(list.containsAll(list));
    }

public void testContainsAll_2_oe() {
        // removed other assertion
        final java.util.List<E> list2 = new java.util.LinkedList<>();
        assertTrue(list.containsAll(list2));
    }

public void testContainsAll_3_oe() {
        // removed other assertion
        final java.util.List<E> list2 = new java.util.LinkedList<>();
        // removed other assertion
        list2.add((E) "A");
        assertTrue(!list.containsAll(list2));
    }

public void testContainsAll_4_oe() {
        // removed other assertion
        final java.util.List<E> list2 = new java.util.LinkedList<>();
        // removed other assertion
        list2.add((E) "A");
        // removed other assertion
        list.add((E) "B");
        list.add((E) "A");
        assertTrue(list.containsAll(list2));
    }

public void testContainsAll_5_oe() {
        // removed other assertion
        final java.util.List<E> list2 = new java.util.LinkedList<>();
        // removed other assertion
        list2.add((E) "A");
        // removed other assertion
        list.add((E) "B");
        list.add((E) "A");
        // removed other assertion
        list2.add((E) "B");
        assertTrue(list.containsAll(list2));
    }

public void testContainsAll_6_oe() {
        // removed other assertion
        final java.util.List<E> list2 = new java.util.LinkedList<>();
        // removed other assertion
        list2.add((E) "A");
        // removed other assertion
        list.add((E) "B");
        list.add((E) "A");
        // removed other assertion
        list2.add((E) "B");
        // removed other assertion
        list2.add((E) "C");
        assertTrue(!list.containsAll(list2));
    }

public void testContainsAll_7_oe() {
        // removed other assertion
        final java.util.List<E> list2 = new java.util.LinkedList<>();
        // removed other assertion
        list2.add((E) "A");
        // removed other assertion
        list.add((E) "B");
        list.add((E) "A");
        // removed other assertion
        list2.add((E) "B");
        // removed other assertion
        list2.add((E) "C");
        // removed other assertion
        list.add((E) "C");
        assertTrue(list.containsAll(list2));
    }

public void testContainsAll_8_oe() {
        // removed other assertion
        final java.util.List<E> list2 = new java.util.LinkedList<>();
        // removed other assertion
        list2.add((E) "A");
        // removed other assertion
        list.add((E) "B");
        list.add((E) "A");
        // removed other assertion
        list2.add((E) "B");
        // removed other assertion
        list2.add((E) "C");
        // removed other assertion
        list.add((E) "C");
        // removed other assertion
        list2.add((E) "C");
        assertTrue(list.containsAll(list2));
    }

public void testContainsAll_9_oe() {
        // removed other assertion
        final java.util.List<E> list2 = new java.util.LinkedList<>();
        // removed other assertion
        list2.add((E) "A");
        // removed other assertion
        list.add((E) "B");
        list.add((E) "A");
        // removed other assertion
        list2.add((E) "B");
        // removed other assertion
        list2.add((E) "C");
        // removed other assertion
        list.add((E) "C");
        // removed other assertion
        list2.add((E) "C");
        // removed other assertion
        assertTrue(list.containsAll(list));
    }

public void testCursorNavigation_1_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertTrue(it.hasNext());
    }

public void testCursorNavigation_2_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        assertTrue(!it.hasPrevious());
    }

public void testCursorNavigation_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        assertEquals("1", it.next());
    }

public void testCursorNavigation_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testCursorNavigation_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testCursorNavigation_6_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1", it.previous());
    }

public void testCursorNavigation_7_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testCursorNavigation_8_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(!it.hasPrevious());
    }

public void testCursorNavigation_9_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1", it.next());
    }

public void testCursorNavigation_10_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testCursorNavigation_11_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testCursorNavigation_12_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2", it.next());
    }

public void testCursorNavigation_13_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testCursorNavigation_14_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testCursorNavigation_15_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2", it.previous());
    }

public void testCursorNavigation_16_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testCursorNavigation_17_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testCursorNavigation_18_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2", it.next());
    }

public void testCursorNavigation_19_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testCursorNavigation_20_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testCursorNavigation_21_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", it.next());
    }

public void testCursorNavigation_22_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testCursorNavigation_23_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testCursorNavigation_24_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("4", it.next());
    }

public void testCursorNavigation_25_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testCursorNavigation_26_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testCursorNavigation_27_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("5", it.next());
    }

public void testCursorNavigation_28_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(!it.hasNext());
    }

public void testCursorNavigation_29_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testCursorNavigation_30_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("5", it.previous());
    }

public void testCursorNavigation_31_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testCursorNavigation_32_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testCursorNavigation_33_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("4", it.previous());
    }

public void testCursorNavigation_34_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testCursorNavigation_35_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testCursorNavigation_36_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", it.previous());
    }

public void testCursorNavigation_37_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testCursorNavigation_38_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testCursorNavigation_39_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2", it.previous());
    }

public void testCursorNavigation_40_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testCursorNavigation_41_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testCursorNavigation_42_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1", it.previous());
    }

public void testCursorNavigation_43_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testCursorNavigation_44_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(!it.hasPrevious());
    }

public void testCursorSet_1_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertEquals("1", it.next());
    }

public void testCursorSet_2_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        it.set((E) "a");
        assertEquals("a", it.previous());
    }

public void testCursorSet_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        it.set((E) "a");
        // removed other assertion
        it.set((E) "A");
        assertEquals("A", it.next());
    }

public void testCursorSet_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        it.set((E) "a");
        // removed other assertion
        it.set((E) "A");
        // removed other assertion
        assertEquals("2", it.next());
    }

public void testCursorSet_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        it.set((E) "a");
        // removed other assertion
        it.set((E) "A");
        // removed other assertion
        // removed other assertion
        it.set((E) "B");
        assertEquals("3", it.next());
    }

public void testCursorSet_6_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        it.set((E) "a");
        // removed other assertion
        it.set((E) "A");
        // removed other assertion
        // removed other assertion
        it.set((E) "B");
        // removed other assertion
        assertEquals("4", it.next());
    }

public void testCursorSet_7_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        it.set((E) "a");
        // removed other assertion
        it.set((E) "A");
        // removed other assertion
        // removed other assertion
        it.set((E) "B");
        // removed other assertion
        // removed other assertion
        it.set((E) "D");
        assertEquals("5", it.next());
    }

public void testCursorSet_8_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        // removed other assertion
        it.set((E) "a");
        // removed other assertion
        it.set((E) "A");
        // removed other assertion
        // removed other assertion
        it.set((E) "B");
        // removed other assertion
        // removed other assertion
        it.set((E) "D");
        // removed other assertion
        it.set((E) "E");
        assertEquals("[A, B, 3, D, E]", list.toString());
    }

public void testCursorRemove_2_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        try {
            it.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
            // expected
        }
        assertEquals("1", it.next());
    }

public void testCursorRemove_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        try {
            it.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        assertEquals("2", it.next());
    }

public void testCursorRemove_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        try {
            it.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        assertEquals("[1, 2, 3, 4, 5]", list.toString());
    }

public void testCursorRemove_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        try {
            it.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        assertEquals("[1, 3, 4, 5]", list.toString());
    }

public void testCursorRemove_6_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        try {
            it.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        assertEquals("3", it.next());
    }

public void testCursorRemove_7_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        try {
            it.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        assertEquals("3", it.previous());
    }

public void testCursorRemove_8_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        try {
            it.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1", it.previous());
    }

public void testCursorRemove_9_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        try {
            it.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        assertEquals("[3, 4, 5]", list.toString());
    }

public void testCursorRemove_10_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        try {
            it.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        assertTrue(!it.hasPrevious());
    }

public void testCursorRemove_11_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        try {
            it.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        assertEquals("3", it.next());
    }

public void testCursorRemove_12_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        try {
            it.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        assertEquals("[4, 5]", list.toString());
    }

public void testCursorRemove_13_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        try {
            it.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        try {
            it.remove();
        } catch (final IllegalStateException e) {
            // expected
        }
        assertEquals("4", it.next());
    }

public void testCursorRemove_14_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        try {
            it.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        try {
            it.remove();
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        assertEquals("5", it.next());
    }

public void testCursorRemove_15_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        try {
            it.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        try {
            it.remove();
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        it.remove();
        assertEquals("[4]", list.toString());
    }

public void testCursorRemove_16_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        try {
            it.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        try {
            it.remove();
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        assertEquals("4", it.previous());
    }

public void testCursorRemove_17_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        try {
            it.remove();
            // removed other assertion
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        try {
            it.remove();
        } catch (final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        it.remove();
        assertEquals("[]", list.toString());
    }

public void testCursorAdd_1_oe() {
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        it.add((E) "1");
        assertEquals("[1]", list.toString());
    }

public void testCursorAdd_2_oe() {
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        it.add((E) "1");
        // removed other assertion
        it.add((E) "3");
        assertEquals("[1, 3]", list.toString());
    }

public void testCursorAdd_3_oe() {
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        it.add((E) "1");
        // removed other assertion
        it.add((E) "3");
        // removed other assertion
        it.add((E) "5");
        assertEquals("[1, 3, 5]", list.toString());
    }

public void testCursorAdd_4_oe() {
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        it.add((E) "1");
        // removed other assertion
        it.add((E) "3");
        // removed other assertion
        it.add((E) "5");
        // removed other assertion
        assertEquals("5", it.previous());
    }

public void testCursorAdd_5_oe() {
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        it.add((E) "1");
        // removed other assertion
        it.add((E) "3");
        // removed other assertion
        it.add((E) "5");
        // removed other assertion
        // removed other assertion
        it.add((E) "4");
        assertEquals("[1, 3, 4, 5]", list.toString());
    }

public void testCursorAdd_6_oe() {
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        it.add((E) "1");
        // removed other assertion
        it.add((E) "3");
        // removed other assertion
        it.add((E) "5");
        // removed other assertion
        // removed other assertion
        it.add((E) "4");
        // removed other assertion
        assertEquals("4", it.previous());
    }

public void testCursorAdd_7_oe() {
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        it.add((E) "1");
        // removed other assertion
        it.add((E) "3");
        // removed other assertion
        it.add((E) "5");
        // removed other assertion
        // removed other assertion
        it.add((E) "4");
        // removed other assertion
        // removed other assertion
        assertEquals("3", it.previous());
    }

public void testCursorAdd_8_oe() {
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        it.add((E) "1");
        // removed other assertion
        it.add((E) "3");
        // removed other assertion
        it.add((E) "5");
        // removed other assertion
        // removed other assertion
        it.add((E) "4");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.add((E) "2");
        assertEquals("[1, 2, 3, 4, 5]", list.toString());
    }

public void testCursorConcurrentModification_1_oe() {
        // this test verifies that cursors remain valid when the list
        // is modified via other means.
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");
        list.add((E) "7");
        list.add((E) "9");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final CursorableLinkedList.Cursor<E> c2 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        assertEquals("1", li.next());
    }

public void testCursorConcurrentModification_2_oe() {
        // this test verifies that cursors remain valid when the list
        // is modified via other means.
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");
        list.add((E) "7");
        list.add((E) "9");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final CursorableLinkedList.Cursor<E> c2 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        assertEquals("2", li.next());
    }

public void testCursorConcurrentModification_3_oe() {
        // this test verifies that cursors remain valid when the list
        // is modified via other means.
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");
        list.add((E) "7");
        list.add((E) "9");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final CursorableLinkedList.Cursor<E> c2 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        assertEquals("3", li.next());
    }

public void testCursorConcurrentModification_4_oe() {
        // this test verifies that cursors remain valid when the list
        // is modified via other means.
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");
        list.add((E) "7");
        list.add((E) "9");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final CursorableLinkedList.Cursor<E> c2 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        // removed other assertion
        assertEquals("1", c1.next());
    }

public void testCursorConcurrentModification_5_oe() {
        // this test verifies that cursors remain valid when the list
        // is modified via other means.
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");
        list.add((E) "7");
        list.add((E) "9");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final CursorableLinkedList.Cursor<E> c2 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        // removed other assertion
        // removed other assertion
        assertEquals("3", c1.next());
    }

public void testCursorConcurrentModification_6_oe() {
        // this test verifies that cursors remain valid when the list
        // is modified via other means.
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");
        list.add((E) "7");
        list.add((E) "9");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final CursorableLinkedList.Cursor<E> c2 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1", c2.next());
    }

public void testCursorConcurrentModification_7_oe() {
        // this test verifies that cursors remain valid when the list
        // is modified via other means.
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");
        list.add((E) "7");
        list.add((E) "9");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final CursorableLinkedList.Cursor<E> c2 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test cursor c1 can remove elements from previously modified list
        // test cursor c2 skips elements removed via different cursor
        c1.remove();
        assertEquals("5", c2.next());
    }

public void testCursorConcurrentModification_8_oe() {
        // this test verifies that cursors remain valid when the list
        // is modified via other means.
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");
        list.add((E) "7");
        list.add((E) "9");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final CursorableLinkedList.Cursor<E> c2 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test cursor c1 can remove elements from previously modified list
        // test cursor c2 skips elements removed via different cursor
        c1.remove();
        // removed other assertion
        c2.add((E) "6");
        assertEquals("5", c1.next());
    }

public void testCursorConcurrentModification_9_oe() {
        // this test verifies that cursors remain valid when the list
        // is modified via other means.
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");
        list.add((E) "7");
        list.add((E) "9");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final CursorableLinkedList.Cursor<E> c2 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test cursor c1 can remove elements from previously modified list
        // test cursor c2 skips elements removed via different cursor
        c1.remove();
        // removed other assertion
        c2.add((E) "6");
        // removed other assertion
        assertEquals("6", c1.next());
    }

public void testCursorConcurrentModification_10_oe() {
        // this test verifies that cursors remain valid when the list
        // is modified via other means.
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");
        list.add((E) "7");
        list.add((E) "9");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final CursorableLinkedList.Cursor<E> c2 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test cursor c1 can remove elements from previously modified list
        // test cursor c2 skips elements removed via different cursor
        c1.remove();
        // removed other assertion
        c2.add((E) "6");
        // removed other assertion
        // removed other assertion
        assertEquals("7", c1.next());
    }

public void testCursorConcurrentModification_11_oe() {
        // this test verifies that cursors remain valid when the list
        // is modified via other means.
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");
        list.add((E) "7");
        list.add((E) "9");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final CursorableLinkedList.Cursor<E> c2 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test cursor c1 can remove elements from previously modified list
        // test cursor c2 skips elements removed via different cursor
        c1.remove();
        // removed other assertion
        c2.add((E) "6");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test cursors remain valid when list mod via CursorableLinkedList
        // test cursor remains valid when elements inserted into list before
        // the current position of the cursor.
        list.add(0, (E) "0");

        // test cursor remains valid when element inserted immediately after
        // current element of a cursor, and the element is seen on the
        // next call to the next method of that cursor.
        list.add(5, (E) "8");

        assertEquals("8", c1.next());
    }

public void testCursorConcurrentModification_12_oe() {
        // this test verifies that cursors remain valid when the list
        // is modified via other means.
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");
        list.add((E) "7");
        list.add((E) "9");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final CursorableLinkedList.Cursor<E> c2 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test cursor c1 can remove elements from previously modified list
        // test cursor c2 skips elements removed via different cursor
        c1.remove();
        // removed other assertion
        c2.add((E) "6");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test cursors remain valid when list mod via CursorableLinkedList
        // test cursor remains valid when elements inserted into list before
        // the current position of the cursor.
        list.add(0, (E) "0");

        // test cursor remains valid when element inserted immediately after
        // current element of a cursor, and the element is seen on the
        // next call to the next method of that cursor.
        list.add(5, (E) "8");

        // removed other assertion
        assertEquals("9", c1.next());
    }

public void testCursorConcurrentModification_13_oe() {
        // this test verifies that cursors remain valid when the list
        // is modified via other means.
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");
        list.add((E) "7");
        list.add((E) "9");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final CursorableLinkedList.Cursor<E> c2 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test cursor c1 can remove elements from previously modified list
        // test cursor c2 skips elements removed via different cursor
        c1.remove();
        // removed other assertion
        c2.add((E) "6");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test cursors remain valid when list mod via CursorableLinkedList
        // test cursor remains valid when elements inserted into list before
        // the current position of the cursor.
        list.add(0, (E) "0");

        // test cursor remains valid when element inserted immediately after
        // current element of a cursor, and the element is seen on the
        // next call to the next method of that cursor.
        list.add(5, (E) "8");

        // removed other assertion
        // removed other assertion
        c1.add((E) "10");
        assertEquals("7", c2.next());
    }

public void testCursorConcurrentModification_14_oe() {
        // this test verifies that cursors remain valid when the list
        // is modified via other means.
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");
        list.add((E) "7");
        list.add((E) "9");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final CursorableLinkedList.Cursor<E> c2 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test cursor c1 can remove elements from previously modified list
        // test cursor c2 skips elements removed via different cursor
        c1.remove();
        // removed other assertion
        c2.add((E) "6");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test cursors remain valid when list mod via CursorableLinkedList
        // test cursor remains valid when elements inserted into list before
        // the current position of the cursor.
        list.add(0, (E) "0");

        // test cursor remains valid when element inserted immediately after
        // current element of a cursor, and the element is seen on the
        // next call to the next method of that cursor.
        list.add(5, (E) "8");

        // removed other assertion
        // removed other assertion
        c1.add((E) "10");
        // removed other assertion
        assertEquals("8", c2.next());
    }

public void testCursorConcurrentModification_15_oe() {
        // this test verifies that cursors remain valid when the list
        // is modified via other means.
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");
        list.add((E) "7");
        list.add((E) "9");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final CursorableLinkedList.Cursor<E> c2 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test cursor c1 can remove elements from previously modified list
        // test cursor c2 skips elements removed via different cursor
        c1.remove();
        // removed other assertion
        c2.add((E) "6");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test cursors remain valid when list mod via CursorableLinkedList
        // test cursor remains valid when elements inserted into list before
        // the current position of the cursor.
        list.add(0, (E) "0");

        // test cursor remains valid when element inserted immediately after
        // current element of a cursor, and the element is seen on the
        // next call to the next method of that cursor.
        list.add(5, (E) "8");

        // removed other assertion
        // removed other assertion
        c1.add((E) "10");
        // removed other assertion
        // removed other assertion
        assertEquals("9", c2.next());
    }

public void testCursorConcurrentModification_16_oe() {
        // this test verifies that cursors remain valid when the list
        // is modified via other means.
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");
        list.add((E) "7");
        list.add((E) "9");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final CursorableLinkedList.Cursor<E> c2 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test cursor c1 can remove elements from previously modified list
        // test cursor c2 skips elements removed via different cursor
        c1.remove();
        // removed other assertion
        c2.add((E) "6");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test cursors remain valid when list mod via CursorableLinkedList
        // test cursor remains valid when elements inserted into list before
        // the current position of the cursor.
        list.add(0, (E) "0");

        // test cursor remains valid when element inserted immediately after
        // current element of a cursor, and the element is seen on the
        // next call to the next method of that cursor.
        list.add(5, (E) "8");

        // removed other assertion
        // removed other assertion
        c1.add((E) "10");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("10", c2.next());
    }

public void testCursorNextIndexMid_1_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        assertEquals("1", li.next());
    }

public void testCursorNextIndexMid_2_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        assertEquals("2", li.next());
    }

public void testCursorNextIndexMid_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        assertEquals(0, c1.nextIndex());
    }

public void testCursorNextIndexMid_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        // removed other assertion
        assertEquals("1", c1.next());
    }

public void testCursorNextIndexMid_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        // removed other assertion
        // removed other assertion
        assertEquals(1, c1.nextIndex());
    }

public void testCursorNextIndexMid_6_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        // removed other assertion
        // removed other assertion
        li.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", c1.next());
    }

public void testCursorNextIndexFirst_1_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        assertEquals(0, c1.nextIndex());
    }

public void testCursorNextIndexFirst_2_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        // removed other assertion
        list.remove(0);
        assertEquals(0, c1.nextIndex());
    }

public void testCursorNextIndexFirst_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        // removed other assertion
        list.remove(0);
        // removed other assertion
        assertEquals("2", c1.next());
    }

public void testCursorNextIndexFirst_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        // removed other assertion
        list.remove(0);
        // removed other assertion
        // removed other assertion
        assertEquals(1, c1.nextIndex());
    }

public void testCursorNextIndexFirst_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        // removed other assertion
        list.remove(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", c1.next());
    }

public void testCursorNextIndexAddBefore_1_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        assertEquals(0, c1.nextIndex());
    }

public void testCursorNextIndexAddBefore_2_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        // removed other assertion
        assertEquals("1", c1.next());
    }

public void testCursorNextIndexAddBefore_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        // removed other assertion
        // removed other assertion
        list.add(0, (E) "0");
        assertEquals(2, c1.nextIndex());
    }

public void testCursorNextIndexAddBefore_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        // removed other assertion
        // removed other assertion
        list.add(0, (E) "0");
        // removed other assertion
        assertEquals("2", c1.next());
    }

public void testCursorNextIndexAddNext_1_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        assertEquals(0, c1.nextIndex());
    }

public void testCursorNextIndexAddNext_2_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        // removed other assertion
        list.add(0, (E) "0");
        assertEquals(0, c1.nextIndex());
    }

public void testCursorNextIndexAddNext_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        // removed other assertion
        list.add(0, (E) "0");
        // removed other assertion
        assertEquals("0", c1.next());
    }

public void testCursorNextIndexAddNext_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        // removed other assertion
        list.add(0, (E) "0");
        // removed other assertion
        // removed other assertion
        assertEquals(1, c1.nextIndex());
    }

public void testCursorNextIndexAddNext_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        // removed other assertion
        list.add(0, (E) "0");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1", c1.next());
    }

public void testCursorNextIndexAddAfter_1_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        assertEquals(0, c1.nextIndex());
    }

public void testCursorNextIndexAddAfter_2_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        // removed other assertion
        list.add(1, (E) "0");
        assertEquals(0, c1.nextIndex());
    }

public void testCursorNextIndexAddAfter_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        // removed other assertion
        list.add(1, (E) "0");
        // removed other assertion
        assertEquals("1", c1.next());
    }

public void testCursorNextIndexAddAfter_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        // removed other assertion
        list.add(1, (E) "0");
        // removed other assertion
        // removed other assertion
        assertEquals(1, c1.nextIndex());
    }

public void testCursorNextIndexAddAfter_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        // removed other assertion
        list.add(1, (E) "0");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("0", c1.next());
    }

public void testInternalState_CursorNextNextPreviousRemoveIndex1ByList_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
    }

public void testInternalState_CursorNextNextPreviousRemoveIndex1ByList_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        assertEquals("B", c1.next());
    }

public void testInternalState_CursorNextNextPreviousRemoveIndex1ByList_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        assertEquals("B", c1.previous());
    }

public void testInternalState_CursorNextNextPreviousRemoveIndex1ByList_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("B", list.remove(1));
    }

public void testInternalState_CursorNextNextPreviousRemoveIndex1ByList_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertEquals(true, c1.nextIndexValid);
    }

public void testInternalState_CursorNextNextPreviousRemoveIndex1ByList_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertEquals(1, c1.nextIndex);
    }

public void testInternalState_CursorNextNextPreviousRemoveIndex1ByList_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(true, c1.currentRemovedByAnother);
    }

public void testInternalState_CursorNextNextPreviousRemoveIndex1ByList_8_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, c1.current);
    }

public void testInternalState_CursorNextNextPreviousRemoveIndex1ByList_9_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("C", c1.next.value);
    }

public void testInternalState_CursorNextNextPreviousRemoveIndex1ByList_10_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("[A, C]", list.toString());
    }

public void testInternalState_CursorNextNextPreviousRemoveIndex1ByList_11_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        c1.remove();  // works ok
        assertEquals("[A, C]", list.toString());
    }

public void testInternalState_CursorNextRemoveIndex1ByList_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
    }

public void testInternalState_CursorNextRemoveIndex1ByList_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion

        assertEquals("B", list.remove(1));
    }

public void testInternalState_CursorNextRemoveIndex1ByList_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion

        // removed other assertion

        assertEquals(true, c1.nextIndexValid);
    }

public void testInternalState_CursorNextRemoveIndex1ByList_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertEquals(1, c1.nextIndex);
    }

public void testInternalState_CursorNextRemoveIndex1ByList_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(false, c1.currentRemovedByAnother);
    }

public void testInternalState_CursorNextRemoveIndex1ByList_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("A", c1.current.value);
    }

public void testInternalState_CursorNextRemoveIndex1ByList_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("C", c1.next.value);
    }

public void testInternalState_CursorNextRemoveIndex1ByList_8_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("[A, C]", list.toString());
    }

public void testInternalState_CursorNextRemoveIndex1ByList_9_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        c1.remove();  // works ok
        assertEquals("[C]", list.toString());
    }

public void testInternalState_CursorNextNextRemoveIndex1ByList_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
    }

public void testInternalState_CursorNextNextRemoveIndex1ByList_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        assertEquals("B", c1.next());
    }

public void testInternalState_CursorNextNextRemoveIndex1ByList_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        assertEquals("B", list.remove(1));
    }

public void testInternalState_CursorNextNextRemoveIndex1ByList_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertEquals(true, c1.nextIndexValid);
    }

public void testInternalState_CursorNextNextRemoveIndex1ByList_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertEquals(1, c1.nextIndex);
    }

public void testInternalState_CursorNextNextRemoveIndex1ByList_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(true, c1.currentRemovedByAnother);
    }

public void testInternalState_CursorNextNextRemoveIndex1ByList_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, c1.current);
    }

public void testInternalState_CursorNextNextRemoveIndex1ByList_8_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("C", c1.next.value);
    }

public void testInternalState_CursorNextNextRemoveIndex1ByList_9_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("[A, C]", list.toString());
    }

public void testInternalState_CursorNextNextRemoveIndex1ByList_10_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        c1.remove();  // works ok
        assertEquals("[A, C]", list.toString());
    }

public void testInternalState_CursorNextNextNextRemoveIndex1ByList_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
    }

public void testInternalState_CursorNextNextNextRemoveIndex1ByList_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        assertEquals("B", c1.next());
    }

public void testInternalState_CursorNextNextNextRemoveIndex1ByList_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        assertEquals("C", c1.next());
    }

public void testInternalState_CursorNextNextNextRemoveIndex1ByList_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("B", list.remove(1));
    }

public void testInternalState_CursorNextNextNextRemoveIndex1ByList_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertEquals(false, c1.nextIndexValid);
    }

public void testInternalState_CursorNextNextNextRemoveIndex1ByList_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertEquals(false, c1.currentRemovedByAnother);
    }

public void testInternalState_CursorNextNextNextRemoveIndex1ByList_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("C", c1.current.value);
    }

public void testInternalState_CursorNextNextNextRemoveIndex1ByList_8_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("D", c1.next.value);
    }

public void testInternalState_CursorNextNextNextRemoveIndex1ByList_9_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("[A, C, D]", list.toString());
    }

public void testInternalState_CursorNextNextNextRemoveIndex1ByList_10_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        c1.remove();  // works ok
        assertEquals("[A, D]", list.toString());
    }

public void testInternalState_CursorNextNextPreviousRemoveByIterator_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
    }

public void testInternalState_CursorNextNextPreviousRemoveByIterator_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        assertEquals("B", c1.next());
    }

public void testInternalState_CursorNextNextPreviousRemoveByIterator_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        assertEquals("B", c1.previous());
    }

public void testInternalState_CursorNextNextPreviousRemoveByIterator_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        c1.remove();

        assertEquals(true, c1.nextIndexValid);
    }

public void testInternalState_CursorNextNextPreviousRemoveByIterator_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        c1.remove();

        // removed other assertion
        assertEquals(1, c1.nextIndex);
    }

public void testInternalState_CursorNextNextPreviousRemoveByIterator_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        c1.remove();

        // removed other assertion
        // removed other assertion
        assertEquals(false, c1.currentRemovedByAnother);
    }

public void testInternalState_CursorNextNextPreviousRemoveByIterator_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        c1.remove();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, c1.current);
    }

public void testInternalState_CursorNextNextPreviousRemoveByIterator_8_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        c1.remove();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("C", c1.next.value);
    }

public void testInternalState_CursorNextNextPreviousRemoveByIterator_9_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        c1.remove();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("[A, C]", list.toString());
    }

public void testInternalState_CursorNextNextRemoveByIterator_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
    }

public void testInternalState_CursorNextNextRemoveByIterator_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        assertEquals("B", c1.next());
    }

public void testInternalState_CursorNextNextRemoveByIterator_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.remove();

        assertEquals(true, c1.nextIndexValid);
    }

public void testInternalState_CursorNextNextRemoveByIterator_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.remove();

        // removed other assertion
        assertEquals(1, c1.nextIndex);
    }

public void testInternalState_CursorNextNextRemoveByIterator_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.remove();

        // removed other assertion
        // removed other assertion
        assertEquals(false, c1.currentRemovedByAnother);
    }

public void testInternalState_CursorNextNextRemoveByIterator_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.remove();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, c1.current);
    }

public void testInternalState_CursorNextNextRemoveByIterator_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.remove();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("C", c1.next.value);
    }

public void testInternalState_CursorNextNextRemoveByIterator_8_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.remove();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("[A, C]", list.toString());
    }

public void testInternalState_CursorNextNextPreviousAddIndex1ByList_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
    }

public void testInternalState_CursorNextNextPreviousAddIndex1ByList_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        assertEquals("B", c1.next());
    }

public void testInternalState_CursorNextNextPreviousAddIndex1ByList_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        assertEquals("B", c1.previous());
    }

public void testInternalState_CursorNextNextPreviousAddIndex1ByList_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add(1, (E) "Z");

        assertEquals(true, c1.nextIndexValid);
    }

public void testInternalState_CursorNextNextPreviousAddIndex1ByList_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add(1, (E) "Z");

        // removed other assertion
        assertEquals(1, c1.nextIndex);
    }

public void testInternalState_CursorNextNextPreviousAddIndex1ByList_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add(1, (E) "Z");

        // removed other assertion
        // removed other assertion
        assertEquals("B", c1.current.value);
    }

public void testInternalState_CursorNextNextPreviousAddIndex1ByList_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add(1, (E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Z", c1.next.value);
    }

public void testInternalState_CursorNextNextPreviousAddIndex1ByList_8_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add(1, (E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("[A, Z, B, C]", list.toString());
    }

public void testInternalState_CursorNextNextPreviousAddIndex1ByList_9_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add(1, (E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        c1.remove();  // works ok
        assertEquals("[A, Z, C]", list.toString());
    }

public void testInternalState_CursorNextAddIndex1ByList_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
    }

public void testInternalState_CursorNextAddIndex1ByList_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion

        list.add(1, (E) "Z");

        assertEquals(true, c1.nextIndexValid);
    }

public void testInternalState_CursorNextAddIndex1ByList_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion

        list.add(1, (E) "Z");

        // removed other assertion
        assertEquals(1, c1.nextIndex);
    }

public void testInternalState_CursorNextAddIndex1ByList_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion

        list.add(1, (E) "Z");

        // removed other assertion
        // removed other assertion
        assertEquals("A", c1.current.value);
    }

public void testInternalState_CursorNextAddIndex1ByList_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion

        list.add(1, (E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Z", c1.next.value);
    }

public void testInternalState_CursorNextAddIndex1ByList_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion

        list.add(1, (E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("[A, Z, B, C]", list.toString());
    }

public void testInternalState_CursorNextAddIndex1ByList_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion

        list.add(1, (E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        c1.remove();  // works ok
        assertEquals("[Z, B, C]", list.toString());
    }

public void testInternalState_CursorNextNextAddIndex1ByList_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
    }

public void testInternalState_CursorNextNextAddIndex1ByList_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        assertEquals("B", c1.next());
    }

public void testInternalState_CursorNextNextAddIndex1ByList_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        list.add(1, (E) "Z");

        assertEquals(false, c1.nextIndexValid);
    }

public void testInternalState_CursorNextNextAddIndex1ByList_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        list.add(1, (E) "Z");

        // removed other assertion
        assertEquals("B", c1.current.value);
    }

public void testInternalState_CursorNextNextAddIndex1ByList_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        list.add(1, (E) "Z");

        // removed other assertion
        // removed other assertion
        assertEquals("C", c1.next.value);
    }

public void testInternalState_CursorNextNextAddIndex1ByList_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        list.add(1, (E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("[A, Z, B, C]", list.toString());
    }

public void testInternalState_CursorNextNextAddIndex1ByList_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        list.add(1, (E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        c1.remove();  // works ok
        assertEquals("[A, Z, C]", list.toString());
    }

public void testInternalState_CursorNextNextPreviousAddByIterator_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
    }

public void testInternalState_CursorNextNextPreviousAddByIterator_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        assertEquals("B", c1.next());
    }

public void testInternalState_CursorNextNextPreviousAddByIterator_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        assertEquals("B", c1.previous());
    }

public void testInternalState_CursorNextNextPreviousAddByIterator_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        c1.add((E) "Z");

        assertEquals(true, c1.nextIndexValid);
    }

public void testInternalState_CursorNextNextPreviousAddByIterator_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        c1.add((E) "Z");

        // removed other assertion
        assertEquals(2, c1.nextIndex);
    }

public void testInternalState_CursorNextNextPreviousAddByIterator_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        c1.add((E) "Z");

        // removed other assertion
        // removed other assertion
        assertEquals(null, c1.current);
    }

public void testInternalState_CursorNextNextPreviousAddByIterator_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        c1.add((E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("B", c1.next.value);
    }

public void testInternalState_CursorNextNextPreviousAddByIterator_8_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        c1.add((E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("[A, Z, B, C]", list.toString());
    }

public void testInternalState_CursorNextNextAddByIterator_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
    }

public void testInternalState_CursorNextNextAddByIterator_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        assertEquals("B", c1.next());
    }

public void testInternalState_CursorNextNextAddByIterator_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.add((E) "Z");

        assertEquals(true, c1.nextIndexValid);
    }

public void testInternalState_CursorNextNextAddByIterator_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.add((E) "Z");

        // removed other assertion
        assertEquals(3, c1.nextIndex);
    }

public void testInternalState_CursorNextNextAddByIterator_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.add((E) "Z");

        // removed other assertion
        // removed other assertion
        assertEquals(false, c1.currentRemovedByAnother);
    }

public void testInternalState_CursorNextNextAddByIterator_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.add((E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, c1.current);
    }

public void testInternalState_CursorNextNextAddByIterator_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.add((E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("C", c1.next.value);
    }

public void testInternalState_CursorNextNextAddByIterator_8_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.add((E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("[A, B, Z, C]", list.toString());
    }

public void testInternalState_CursorNextNextRemoveByListSetByIterator_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
    }

public void testInternalState_CursorNextNextRemoveByListSetByIterator_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        assertEquals("B", c1.next());
    }

public void testInternalState_CursorNextNextRemoveByListSetByIterator_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        list.remove(1);

        assertEquals(true, c1.nextIndexValid);
    }

public void testInternalState_CursorNextNextRemoveByListSetByIterator_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        list.remove(1);

        // removed other assertion
        assertEquals(1, c1.nextIndex);
    }

public void testInternalState_CursorNextNextRemoveByListSetByIterator_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        list.remove(1);

        // removed other assertion
        // removed other assertion
        assertEquals(null, c1.current);
    }

public void testInternalState_CursorNextNextRemoveByListSetByIterator_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        list.remove(1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("C", c1.next.value);
    }

public void testInternalState_CursorNextNextRemoveByListSetByIterator_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        list.remove(1);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[A, C]", list.toString());
    }

public void testInternalState_CursorNextNextPreviousSetByIterator_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
    }

public void testInternalState_CursorNextNextPreviousSetByIterator_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        assertEquals("B", c1.next());
    }

public void testInternalState_CursorNextNextPreviousSetByIterator_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        assertEquals("B", c1.previous());
    }

public void testInternalState_CursorNextNextPreviousSetByIterator_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        c1.set((E) "Z");

        assertEquals(true, c1.nextIndexValid);
    }

public void testInternalState_CursorNextNextPreviousSetByIterator_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        c1.set((E) "Z");

        // removed other assertion
        assertEquals(1, c1.nextIndex);
    }

public void testInternalState_CursorNextNextPreviousSetByIterator_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        c1.set((E) "Z");

        // removed other assertion
        // removed other assertion
        assertEquals("Z", c1.current.value);
    }

public void testInternalState_CursorNextNextPreviousSetByIterator_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        c1.set((E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Z", c1.next.value);
    }

public void testInternalState_CursorNextNextPreviousSetByIterator_8_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        c1.set((E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("[A, Z, C]", list.toString());
    }

public void testInternalState_CursorNextNextPreviousSetByIterator_9_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        c1.set((E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        c1.remove();  // works ok
        assertEquals("[A, C]", list.toString());
    }

public void testInternalState_CursorNextNextSetByIterator_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
    }

public void testInternalState_CursorNextNextSetByIterator_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        assertEquals("B", c1.next());
    }

public void testInternalState_CursorNextNextSetByIterator_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.set((E) "Z");

        assertEquals(true, c1.nextIndexValid);
    }

public void testInternalState_CursorNextNextSetByIterator_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.set((E) "Z");

        // removed other assertion
        assertEquals(2, c1.nextIndex);
    }

public void testInternalState_CursorNextNextSetByIterator_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.set((E) "Z");

        // removed other assertion
        // removed other assertion
        assertEquals("Z", c1.current.value);
    }

public void testInternalState_CursorNextNextSetByIterator_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.set((E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("C", c1.next.value);
    }

public void testInternalState_CursorNextNextSetByIterator_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.set((E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("[A, Z, C]", list.toString());
    }

public void testInternalState_CursorNextNextSetByIterator_8_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        // removed other assertion
        // removed other assertion

        c1.set((E) "Z");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        c1.remove();  // works ok
        assertEquals("[A, C]", list.toString());
    }

public void testEqualsAndHashCode_1_oe() {
        assertTrue(list.equals(list));
    }

public void testEqualsAndHashCode_2_oe() {
        // removed other assertion
        assertEquals(list.hashCode(),list.hashCode());
    }

public void testEqualsAndHashCode_3_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        assertTrue(list.equals(list));
    }

public void testEqualsAndHashCode_4_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        assertEquals(list.hashCode(),list.hashCode());
    }

public void testEqualsAndHashCode_5_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        assertTrue(!list.equals(list2));
    }

public void testEqualsAndHashCode_6_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        assertTrue(!list2.equals(list));
    }

public void testEqualsAndHashCode_7_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        assertTrue(!list.equals(list3));
    }

public void testEqualsAndHashCode_8_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        assertTrue(!list3.equals(list));
    }

public void testEqualsAndHashCode_9_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        assertTrue(list2.equals(list3));
    }

public void testEqualsAndHashCode_10_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list3.equals(list2));
    }

public void testEqualsAndHashCode_11_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(list2.hashCode(),list3.hashCode());
    }

public void testEqualsAndHashCode_12_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        assertTrue(list.equals(list2));
    }

public void testEqualsAndHashCode_13_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        assertTrue(list2.equals(list));
    }

public void testEqualsAndHashCode_14_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        assertTrue(!list2.equals(list3));
    }

public void testEqualsAndHashCode_15_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(!list3.equals(list2));
    }

public void testEqualsAndHashCode_16_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        assertTrue(list2.equals(list3));
    }

public void testEqualsAndHashCode_17_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        assertTrue(list3.equals(list2));
    }

public void testEqualsAndHashCode_18_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        assertEquals(list2.hashCode(),list3.hashCode());
    }

public void testEqualsAndHashCode_19_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        assertTrue(list.equals(list));
    }

public void testEqualsAndHashCode_20_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        assertTrue(!list.equals(list2));
    }

public void testEqualsAndHashCode_21_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        assertTrue(!list2.equals(list));
    }

public void testEqualsAndHashCode_22_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(!list.equals(list3));
    }

public void testEqualsAndHashCode_23_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(!list3.equals(list));
    }

public void testEqualsAndHashCode_24_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "B");
        list3.add((E) "B");
        assertTrue(list.equals(list));
    }

public void testEqualsAndHashCode_25_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "B");
        list3.add((E) "B");
        // removed other assertion
        assertTrue(list.equals(list2));
    }

public void testEqualsAndHashCode_26_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "B");
        list3.add((E) "B");
        // removed other assertion
        // removed other assertion
        assertTrue(list2.equals(list));
    }

public void testEqualsAndHashCode_27_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "B");
        list3.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list2.equals(list3));
    }

public void testEqualsAndHashCode_28_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "B");
        list3.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list3.equals(list2));
    }

public void testEqualsAndHashCode_29_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "B");
        list3.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(list2.hashCode(),list3.hashCode());
    }

public void testEqualsAndHashCode_30_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "B");
        list3.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        assertTrue(list.equals(list));
    }

public void testEqualsAndHashCode_31_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "B");
        list3.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        // removed other assertion
        assertTrue(list.equals(list2));
    }

public void testEqualsAndHashCode_32_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "B");
        list3.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        // removed other assertion
        // removed other assertion
        assertTrue(list2.equals(list));
    }

public void testEqualsAndHashCode_33_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "B");
        list3.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list2.equals(list3));
    }

public void testEqualsAndHashCode_34_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "B");
        list3.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list3.equals(list2));
    }

public void testEqualsAndHashCode_35_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "B");
        list3.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(list.hashCode(),list2.hashCode());
    }

public void testEqualsAndHashCode_36_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "B");
        list3.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(list2.hashCode(),list3.hashCode());
    }

public void testEqualsAndHashCode_37_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "B");
        list3.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "D");
        list2.addFirst((E) "D");
        assertTrue(list.equals(list));
    }

public void testEqualsAndHashCode_38_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "B");
        list3.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "D");
        list2.addFirst((E) "D");
        // removed other assertion
        assertTrue(!list.equals(list2));
    }

public void testEqualsAndHashCode_39_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        // removed other assertion
        // removed other assertion

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list3.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list2.add((E) "B");
        list3.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add((E) "D");
        list2.addFirst((E) "D");
        // removed other assertion
        // removed other assertion
        assertTrue(!list2.equals(list));
    }

public void testGet_2_oe() {
        try {
            list.get(0);
            // removed other assertion
        } catch(final IndexOutOfBoundsException e) {
            // expected
        }

        assertTrue(list.add((E) "A"));
    }

public void testGet_3_oe() {
        try {
            list.get(0);
            // removed other assertion
        } catch(final IndexOutOfBoundsException e) {
            // expected
        }

        // removed other assertion
        assertEquals("A",list.get(0));
    }

public void testGet_4_oe() {
        try {
            list.get(0);
            // removed other assertion
        } catch(final IndexOutOfBoundsException e) {
            // expected
        }

        // removed other assertion
        // removed other assertion
        assertTrue(list.add((E) "B"));
    }

public void testGet_5_oe() {
        try {
            list.get(0);
            // removed other assertion
        } catch(final IndexOutOfBoundsException e) {
            // expected
        }

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("A",list.get(0));
    }

public void testGet_6_oe() {
        try {
            list.get(0);
            // removed other assertion
        } catch(final IndexOutOfBoundsException e) {
            // expected
        }

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("B",list.get(1));
    }

public void testIndexOf_1_oe() {
        assertEquals(-1,list.indexOf("A"));
    }

public void testIndexOf_2_oe() {
        // removed other assertion
        assertEquals(-1,list.lastIndexOf("A"));
    }

public void testIndexOf_3_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        assertEquals(0,list.indexOf("A"));
    }

public void testIndexOf_4_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        assertEquals(0,list.lastIndexOf("A"));
    }

public void testIndexOf_5_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion
        assertEquals(-1,list.indexOf("B"));
    }

public void testIndexOf_6_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1,list.lastIndexOf("B"));
    }

public void testIndexOf_7_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.add((E) "B");
        assertEquals(0,list.indexOf("A"));
    }

public void testIndexOf_8_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.add((E) "B");
        // removed other assertion
        assertEquals(0,list.lastIndexOf("A"));
    }

public void testIndexOf_9_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        assertEquals(1,list.indexOf("B"));
    }

public void testIndexOf_10_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1,list.lastIndexOf("B"));
    }

public void testIndexOf_11_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.addFirst((E) "B");
        assertEquals(1,list.indexOf("A"));
    }

public void testIndexOf_12_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.addFirst((E) "B");
        // removed other assertion
        assertEquals(1,list.lastIndexOf("A"));
    }

public void testIndexOf_13_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.addFirst((E) "B");
        // removed other assertion
        // removed other assertion
        assertEquals(0,list.indexOf("B"));
    }

public void testIndexOf_14_oe() {
        // removed other assertion
        // removed other assertion
        list.add((E) "A");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.add((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        list.addFirst((E) "B");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2,list.lastIndexOf("B"));
    }

public void testIsEmpty_1_oe() {
        assertTrue(list.isEmpty());
    }

public void testIsEmpty_2_oe() {
        // removed other assertion
        list.add((E) "element");
        assertTrue(!list.isEmpty());
    }

public void testIsEmpty_3_oe() {
        // removed other assertion
        list.add((E) "element");
        // removed other assertion
        list.remove("element");
        assertTrue(list.isEmpty());
    }

public void testIsEmpty_4_oe() {
        // removed other assertion
        list.add((E) "element");
        // removed other assertion
        list.remove("element");
        // removed other assertion
        list.add((E) "element");
        assertTrue(!list.isEmpty());
    }

public void testIsEmpty_5_oe() {
        // removed other assertion
        list.add((E) "element");
        // removed other assertion
        list.remove("element");
        // removed other assertion
        list.add((E) "element");
        // removed other assertion
        list.clear();
        assertTrue(list.isEmpty());
    }

public void testIterator_1_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        assertTrue(it.hasNext());
    }

public void testIterator_2_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        assertEquals("1", it.next());
    }

public void testIterator_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testIterator_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2", it.next());
    }

public void testIterator_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testIterator_6_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", it.next());
    }

public void testIterator_7_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testIterator_8_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("4", it.next());
    }

public void testIterator_9_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testIterator_10_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("5", it.next());
    }

public void testIterator_11_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(!it.hasNext());
    }

public void testIterator_12_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        it = list.iterator();
        assertTrue(it.hasNext());
    }

public void testIterator_13_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        it = list.iterator();
        // removed other assertion
        assertEquals("1", it.next());
    }

public void testIterator_14_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        it = list.iterator();
        // removed other assertion
        // removed other assertion
        it.remove();
        assertEquals("[2, 3, 4, 5]", list.toString());
    }

public void testIterator_15_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        it = list.iterator();
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testIterator_16_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        it = list.iterator();
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        assertEquals("2", it.next());
    }

public void testIterator_17_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        it = list.iterator();
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        assertEquals("[3, 4, 5]", list.toString());
    }

public void testIterator_18_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        it = list.iterator();
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testIterator_19_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        it = list.iterator();
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        assertEquals("3", it.next());
    }

public void testIterator_20_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        it = list.iterator();
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        assertEquals("[4, 5]", list.toString());
    }

public void testIterator_21_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        it = list.iterator();
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testIterator_22_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        it = list.iterator();
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        assertEquals("4", it.next());
    }

public void testIterator_23_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        it = list.iterator();
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        assertEquals("[5]", list.toString());
    }

public void testIterator_24_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        it = list.iterator();
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testIterator_25_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        it = list.iterator();
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        assertEquals("5", it.next());
    }

public void testIterator_26_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        it = list.iterator();
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        assertEquals("[]", list.toString());
    }

public void testIterator_27_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        it = list.iterator();
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        assertTrue(!it.hasNext());
    }

public void testListIteratorNavigation_1_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertTrue(it.hasNext());
    }

public void testListIteratorNavigation_2_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        assertTrue(!it.hasPrevious());
    }

public void testListIteratorNavigation_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        assertEquals(-1, it.previousIndex());
    }

public void testListIteratorNavigation_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, it.nextIndex());
    }

public void testListIteratorNavigation_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1", it.next());
    }

public void testListIteratorNavigation_6_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testListIteratorNavigation_7_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testListIteratorNavigation_8_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, it.previousIndex());
    }

public void testListIteratorNavigation_9_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, it.nextIndex());
    }

public void testListIteratorNavigation_10_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1", it.previous());
    }

public void testListIteratorNavigation_11_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testListIteratorNavigation_12_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(!it.hasPrevious());
    }

public void testListIteratorNavigation_13_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, it.previousIndex());
    }

public void testListIteratorNavigation_14_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, it.nextIndex());
    }

public void testListIteratorNavigation_15_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1", it.next());
    }

public void testListIteratorNavigation_16_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testListIteratorNavigation_17_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testListIteratorNavigation_18_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, it.previousIndex());
    }

public void testListIteratorNavigation_19_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, it.nextIndex());
    }

public void testListIteratorNavigation_20_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2", it.next());
    }

public void testListIteratorNavigation_21_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testListIteratorNavigation_22_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testListIteratorNavigation_23_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, it.previousIndex());
    }

public void testListIteratorNavigation_24_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, it.nextIndex());
    }

public void testListIteratorNavigation_25_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2", it.previous());
    }

public void testListIteratorNavigation_26_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testListIteratorNavigation_27_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testListIteratorNavigation_28_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, it.previousIndex());
    }

public void testListIteratorNavigation_29_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, it.nextIndex());
    }

public void testListIteratorNavigation_30_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2", it.next());
    }

public void testListIteratorNavigation_31_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testListIteratorNavigation_32_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testListIteratorNavigation_33_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, it.previousIndex());
    }

public void testListIteratorNavigation_34_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, it.nextIndex());
    }

public void testListIteratorNavigation_35_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", it.next());
    }

public void testListIteratorNavigation_36_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testListIteratorNavigation_37_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testListIteratorNavigation_38_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, it.previousIndex());
    }

public void testListIteratorNavigation_39_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, it.nextIndex());
    }

public void testListIteratorNavigation_40_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("4", it.next());
    }

public void testListIteratorNavigation_41_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testListIteratorNavigation_42_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testListIteratorNavigation_43_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, it.previousIndex());
    }

public void testListIteratorNavigation_44_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, it.nextIndex());
    }

public void testListIteratorNavigation_45_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("5", it.next());
    }

public void testListIteratorNavigation_46_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(!it.hasNext());
    }

public void testListIteratorNavigation_47_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testListIteratorNavigation_48_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, it.previousIndex());
    }

public void testListIteratorNavigation_49_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, it.nextIndex());
    }

public void testListIteratorNavigation_50_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("5", it.previous());
    }

public void testListIteratorNavigation_51_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testListIteratorNavigation_52_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testListIteratorNavigation_53_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, it.previousIndex());
    }

public void testListIteratorNavigation_54_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, it.nextIndex());
    }

public void testListIteratorNavigation_55_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("4", it.previous());
    }

public void testListIteratorNavigation_56_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testListIteratorNavigation_57_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testListIteratorNavigation_58_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, it.previousIndex());
    }

public void testListIteratorNavigation_59_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, it.nextIndex());
    }

public void testListIteratorNavigation_60_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", it.previous());
    }

public void testListIteratorNavigation_61_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testListIteratorNavigation_62_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testListIteratorNavigation_63_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, it.previousIndex());
    }

public void testListIteratorNavigation_64_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, it.nextIndex());
    }

public void testListIteratorNavigation_65_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2", it.previous());
    }

public void testListIteratorNavigation_66_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testListIteratorNavigation_67_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasPrevious());
    }

public void testListIteratorNavigation_68_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, it.previousIndex());
    }

public void testListIteratorNavigation_69_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, it.nextIndex());
    }

public void testListIteratorNavigation_70_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1", it.previous());
    }

public void testListIteratorNavigation_71_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(it.hasNext());
    }

public void testListIteratorNavigation_72_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(!it.hasPrevious());
    }

public void testListIteratorNavigation_73_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(-1, it.previousIndex());
    }

public void testListIteratorNavigation_74_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, it.nextIndex());
    }

public void testListIteratorSet_1_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        assertEquals("1", it.next());
    }

public void testListIteratorSet_2_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        it.set((E) "a");
        assertEquals("a", it.previous());
    }

public void testListIteratorSet_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        it.set((E) "a");
        // removed other assertion
        it.set((E) "A");
        assertEquals("A", it.next());
    }

public void testListIteratorSet_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        it.set((E) "a");
        // removed other assertion
        it.set((E) "A");
        // removed other assertion
        assertEquals("2", it.next());
    }

public void testListIteratorSet_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        it.set((E) "a");
        // removed other assertion
        it.set((E) "A");
        // removed other assertion
        // removed other assertion
        it.set((E) "B");
        assertEquals("3", it.next());
    }

public void testListIteratorSet_6_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        it.set((E) "a");
        // removed other assertion
        it.set((E) "A");
        // removed other assertion
        // removed other assertion
        it.set((E) "B");
        // removed other assertion
        assertEquals("4", it.next());
    }

public void testListIteratorSet_7_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        it.set((E) "a");
        // removed other assertion
        it.set((E) "A");
        // removed other assertion
        // removed other assertion
        it.set((E) "B");
        // removed other assertion
        // removed other assertion
        it.set((E) "D");
        assertEquals("5", it.next());
    }

public void testListIteratorSet_8_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        // removed other assertion
        it.set((E) "a");
        // removed other assertion
        it.set((E) "A");
        // removed other assertion
        // removed other assertion
        it.set((E) "B");
        // removed other assertion
        // removed other assertion
        it.set((E) "D");
        // removed other assertion
        it.set((E) "E");
        assertEquals("[A, B, 3, D, E]", list.toString());
    }

public void testListIteratorRemove_1_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        assertEquals("1",it.next());
    }

public void testListIteratorRemove_2_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        assertEquals("2",it.next());
    }

public void testListIteratorRemove_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        assertEquals("[1, 2, 3, 4, 5]",list.toString());
    }

public void testListIteratorRemove_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        assertEquals("[1, 3, 4, 5]",list.toString());
    }

public void testListIteratorRemove_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        assertEquals("3",it.next());
    }

public void testListIteratorRemove_6_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        assertEquals("3",it.previous());
    }

public void testListIteratorRemove_7_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("1",it.previous());
    }

public void testListIteratorRemove_8_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        assertEquals("[3, 4, 5]",list.toString());
    }

public void testListIteratorRemove_9_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        assertTrue(!it.hasPrevious());
    }

public void testListIteratorRemove_10_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        assertEquals("3",it.next());
    }

public void testListIteratorRemove_11_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        assertEquals("[4, 5]",list.toString());
    }

public void testListIteratorRemove_12_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        assertEquals("4",it.next());
    }

public void testListIteratorRemove_13_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        assertEquals("5",it.next());
    }

public void testListIteratorRemove_14_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        it.remove();
        assertEquals("[4]",list.toString());
    }

public void testListIteratorRemove_15_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        assertEquals("4",it.previous());
    }

public void testListIteratorRemove_16_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        // removed other assertion
        // removed other assertion
        it.remove();
        // removed other assertion
        // removed other assertion
        it.remove();
        assertEquals("[]",list.toString());
    }

public void testListIteratorAdd_1_oe() {
        final ListIterator<E> it = list.listIterator();
        it.add((E) "1");
        assertEquals("[1]", list.toString());
    }

public void testListIteratorAdd_2_oe() {
        final ListIterator<E> it = list.listIterator();
        it.add((E) "1");
        // removed other assertion
        it.add((E) "3");
        assertEquals("[1, 3]", list.toString());
    }

public void testListIteratorAdd_3_oe() {
        final ListIterator<E> it = list.listIterator();
        it.add((E) "1");
        // removed other assertion
        it.add((E) "3");
        // removed other assertion
        it.add((E) "5");
        assertEquals("[1, 3, 5]", list.toString());
    }

public void testListIteratorAdd_4_oe() {
        final ListIterator<E> it = list.listIterator();
        it.add((E) "1");
        // removed other assertion
        it.add((E) "3");
        // removed other assertion
        it.add((E) "5");
        // removed other assertion
        assertEquals("5", it.previous());
    }

public void testListIteratorAdd_5_oe() {
        final ListIterator<E> it = list.listIterator();
        it.add((E) "1");
        // removed other assertion
        it.add((E) "3");
        // removed other assertion
        it.add((E) "5");
        // removed other assertion
        // removed other assertion
        it.add((E) "4");
        assertEquals("[1, 3, 4, 5]", list.toString());
    }

public void testListIteratorAdd_6_oe() {
        final ListIterator<E> it = list.listIterator();
        it.add((E) "1");
        // removed other assertion
        it.add((E) "3");
        // removed other assertion
        it.add((E) "5");
        // removed other assertion
        // removed other assertion
        it.add((E) "4");
        // removed other assertion
        assertEquals("4", it.previous());
    }

public void testListIteratorAdd_7_oe() {
        final ListIterator<E> it = list.listIterator();
        it.add((E) "1");
        // removed other assertion
        it.add((E) "3");
        // removed other assertion
        it.add((E) "5");
        // removed other assertion
        // removed other assertion
        it.add((E) "4");
        // removed other assertion
        // removed other assertion
        assertEquals("3", it.previous());
    }

public void testListIteratorAdd_8_oe() {
        final ListIterator<E> it = list.listIterator();
        it.add((E) "1");
        // removed other assertion
        it.add((E) "3");
        // removed other assertion
        it.add((E) "5");
        // removed other assertion
        // removed other assertion
        it.add((E) "4");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        it.add((E) "2");
        assertEquals("[1, 2, 3, 4, 5]", list.toString());
    }

public void testRemoveAll_1_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final HashSet<E> set = new HashSet<>();
        set.add((E) "A");
        set.add((E) "2");
        set.add((E) "C");
        set.add((E) "4");
        set.add((E) "D");

        assertTrue(list.removeAll(set));
    }

public void testRemoveAll_2_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final HashSet<E> set = new HashSet<>();
        set.add((E) "A");
        set.add((E) "2");
        set.add((E) "C");
        set.add((E) "4");
        set.add((E) "D");

        // removed other assertion
        assertEquals("[1, 3, 5]", list.toString());
    }

public void testRemoveAll_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final HashSet<E> set = new HashSet<>();
        set.add((E) "A");
        set.add((E) "2");
        set.add((E) "C");
        set.add((E) "4");
        set.add((E) "D");

        // removed other assertion
        // removed other assertion
        assertTrue(!list.removeAll(set));
    }

public void testRemoveByIndex_1_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        assertEquals("[1, 2, 3, 4, 5]", list.toString());
    }

public void testRemoveByIndex_2_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        assertEquals("1", list.remove(0));
    }

public void testRemoveByIndex_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        assertEquals("[2, 3, 4, 5]", list.toString());
    }

public void testRemoveByIndex_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", list.remove(1));
    }

public void testRemoveByIndex_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[2, 4, 5]", list.toString());
    }

public void testRemoveByIndex_6_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("4", list.remove(1));
    }

public void testRemoveByIndex_7_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[2, 5]", list.toString());
    }

public void testRemoveByIndex_8_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("5", list.remove(1));
    }

public void testRemoveByIndex_9_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[2]", list.toString());
    }

public void testRemoveByIndex_10_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2", list.remove(0));
    }

public void testRemoveByIndex_11_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[]", list.toString());
    }

public void testRemove_1_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        assertEquals("[1, 1, 2, 3, 4, 5, 2, 3, 4, 5]", list.toString());
    }

public void testRemove_2_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        assertTrue(!list.remove("6"));
    }

public void testRemove_3_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        assertTrue(list.remove("5"));
    }

public void testRemove_4_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[1, 1, 2, 3, 4, 2, 3, 4, 5]", list.toString());
    }

public void testRemove_5_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.remove("5"));
    }

public void testRemove_6_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[1, 1, 2, 3, 4, 2, 3, 4]", list.toString());
    }

public void testRemove_7_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(!list.remove("5"));
    }

public void testRemove_8_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.remove("1"));
    }

public void testRemove_9_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[1, 2, 3, 4, 2, 3, 4]", list.toString());
    }

public void testRemove_10_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.remove("1"));
    }

public void testRemove_11_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[2, 3, 4, 2, 3, 4]", list.toString());
    }

public void testRemove_12_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.remove("2"));
    }

public void testRemove_13_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[3, 4, 2, 3, 4]", list.toString());
    }

public void testRemove_14_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.remove("2"));
    }

public void testRemove_15_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[3, 4, 3, 4]", list.toString());
    }

public void testRemove_16_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.remove("3"));
    }

public void testRemove_17_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[4, 3, 4]", list.toString());
    }

public void testRemove_18_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.remove("3"));
    }

public void testRemove_19_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[4, 4]", list.toString());
    }

public void testRemove_20_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.remove("4"));
    }

public void testRemove_21_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[4]", list.toString());
    }

public void testRemove_22_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.remove("4"));
    }

public void testRemove_23_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[]", list.toString());
    }

public void testRetainAll_1_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "5");

        final HashSet<E> set = new HashSet<>();
        set.add((E) "A");
        set.add((E) "2");
        set.add((E) "C");
        set.add((E) "4");
        set.add((E) "D");

        assertTrue(list.retainAll(set));
    }

public void testRetainAll_2_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "5");

        final HashSet<E> set = new HashSet<>();
        set.add((E) "A");
        set.add((E) "2");
        set.add((E) "C");
        set.add((E) "4");
        set.add((E) "D");

        // removed other assertion
        assertEquals("[2, 2, 4, 4]", list.toString());
    }

public void testRetainAll_3_oe() {
        list.add((E) "1");
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "4");
        list.add((E) "5");
        list.add((E) "5");

        final HashSet<E> set = new HashSet<>();
        set.add((E) "A");
        set.add((E) "2");
        set.add((E) "C");
        set.add((E) "4");
        set.add((E) "D");

        // removed other assertion
        // removed other assertion
        assertTrue(!list.retainAll(set));
    }

public void testSet_1_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        assertEquals("[1, 2, 3, 4, 5]", list.toString());
    }

public void testSet_2_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        list.set(0, (E) "A");
        assertEquals("[A, 2, 3, 4, 5]", list.toString());
    }

public void testSet_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        list.set(0, (E) "A");
        // removed other assertion
        list.set(1, (E) "B");
        assertEquals("[A, B, 3, 4, 5]", list.toString());
    }

public void testSet_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        list.set(0, (E) "A");
        // removed other assertion
        list.set(1, (E) "B");
        // removed other assertion
        list.set(2, (E) "C");
        assertEquals("[A, B, C, 4, 5]", list.toString());
    }

public void testSet_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        list.set(0, (E) "A");
        // removed other assertion
        list.set(1, (E) "B");
        // removed other assertion
        list.set(2, (E) "C");
        // removed other assertion
        list.set(3, (E) "D");
        assertEquals("[A, B, C, D, 5]", list.toString());
    }

public void testSet_6_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        // removed other assertion
        list.set(0, (E) "A");
        // removed other assertion
        list.set(1, (E) "B");
        // removed other assertion
        list.set(2, (E) "C");
        // removed other assertion
        list.set(3, (E) "D");
        // removed other assertion
        list.set(4, (E) "E");
        assertEquals("[A, B, C, D, E]", list.toString());
    }

public void testSubList_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        assertEquals("[A, B, C, D, E]", list.toString());
    }

public void testSubList_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        // removed other assertion
        assertEquals("[A, B, C, D, E]", list.subList(0, 5).toString());
    }

public void testSubList_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        // removed other assertion
        // removed other assertion
        assertEquals("[B, C, D, E]", list.subList(1, 5).toString());
    }

public void testSubList_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[C, D, E]", list.subList(2, 5).toString());
    }

public void testSubList_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[D, E]", list.subList(3, 5).toString());
    }

public void testSubList_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[E]", list.subList(4, 5).toString());
    }

public void testSubList_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[]", list.subList(5, 5).toString());
    }

public void testSubListAddEnd_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(5, 5);
        sublist.add((E) "F");
        assertEquals("[A, B, C, D, E, F]", list.toString());
    }

public void testSubListAddEnd_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(5, 5);
        sublist.add((E) "F");
        // removed other assertion
        assertEquals("[F]", sublist.toString());
    }

public void testSubListAddEnd_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(5, 5);
        sublist.add((E) "F");
        // removed other assertion
        // removed other assertion
        sublist.add((E) "G");
        assertEquals("[A, B, C, D, E, F, G]", list.toString());
    }

public void testSubListAddEnd_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(5, 5);
        sublist.add((E) "F");
        // removed other assertion
        // removed other assertion
        sublist.add((E) "G");
        // removed other assertion
        assertEquals("[F, G]", sublist.toString());
    }

public void testSubListAddBegin_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(0, 0);
        sublist.add((E) "a");
        assertEquals("[a, A, B, C, D, E]", list.toString());
    }

public void testSubListAddBegin_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(0, 0);
        sublist.add((E) "a");
        // removed other assertion
        assertEquals("[a]", sublist.toString());
    }

public void testSubListAddBegin_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(0, 0);
        sublist.add((E) "a");
        // removed other assertion
        // removed other assertion
        sublist.add((E) "b");
        assertEquals("[a, b, A, B, C, D, E]", list.toString());
    }

public void testSubListAddBegin_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(0, 0);
        sublist.add((E) "a");
        // removed other assertion
        // removed other assertion
        sublist.add((E) "b");
        // removed other assertion
        assertEquals("[a, b]", sublist.toString());
    }

public void testSubListAddMiddle_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 3);
        sublist.add((E) "a");
        assertEquals("[A, B, C, a, D, E]", list.toString());
    }

public void testSubListAddMiddle_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 3);
        sublist.add((E) "a");
        // removed other assertion
        assertEquals("[B, C, a]", sublist.toString());
    }

public void testSubListAddMiddle_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 3);
        sublist.add((E) "a");
        // removed other assertion
        // removed other assertion
        sublist.add((E) "b");
        assertEquals("[A, B, C, a, b, D, E]", list.toString());
    }

public void testSubListAddMiddle_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 3);
        sublist.add((E) "a");
        // removed other assertion
        // removed other assertion
        sublist.add((E) "b");
        // removed other assertion
        assertEquals("[B, C, a, b]", sublist.toString());
    }

public void testSubListRemove_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 4);
        assertEquals("[B, C, D]", sublist.toString());
    }

public void testSubListRemove_2_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 4);
        // removed other assertion
        assertEquals("[A, B, C, D, E]", list.toString());
    }

public void testSubListRemove_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 4);
        // removed other assertion
        // removed other assertion
        sublist.remove("C");
        assertEquals("[B, D]", sublist.toString());
    }

public void testSubListRemove_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 4);
        // removed other assertion
        // removed other assertion
        sublist.remove("C");
        // removed other assertion
        assertEquals("[A, B, D, E]", list.toString());
    }

public void testSubListRemove_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 4);
        // removed other assertion
        // removed other assertion
        sublist.remove("C");
        // removed other assertion
        // removed other assertion
        sublist.remove(1);
        assertEquals("[B]", sublist.toString());
    }

public void testSubListRemove_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 4);
        // removed other assertion
        // removed other assertion
        sublist.remove("C");
        // removed other assertion
        // removed other assertion
        sublist.remove(1);
        // removed other assertion
        assertEquals("[A, B, E]", list.toString());
    }

public void testSubListRemove_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 4);
        // removed other assertion
        // removed other assertion
        sublist.remove("C");
        // removed other assertion
        // removed other assertion
        sublist.remove(1);
        // removed other assertion
        // removed other assertion
        sublist.clear();
        assertEquals("[]", sublist.toString());
    }

public void testSubListRemove_8_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 4);
        // removed other assertion
        // removed other assertion
        sublist.remove("C");
        // removed other assertion
        // removed other assertion
        sublist.remove(1);
        // removed other assertion
        // removed other assertion
        sublist.clear();
        // removed other assertion
        assertEquals("[A, E]", list.toString());
    }

public void testToArray_1_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        assertEquals("1", elts[0]);
    }

public void testToArray_2_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        assertEquals("2", elts[1]);
    }

public void testToArray_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        assertEquals("3", elts[2]);
    }

public void testToArray_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("4", elts[3]);
    }

public void testToArray_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("5", elts[4]);
    }

public void testToArray_6_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, elts.length);
    }

public void testToArray_7_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        assertEquals("1", elts2[0]);
    }

public void testToArray_8_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        assertEquals("2", elts2[1]);
    }

public void testToArray_9_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        assertEquals("3", elts2[2]);
    }

public void testToArray_10_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("4", elts2[3]);
    }

public void testToArray_11_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("5", elts2[4]);
    }

public void testToArray_12_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, elts2.length);
    }

public void testToArray_13_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts3 = new String[5];
        assertSame(elts3, list.toArray(elts3));
    }

public void testToArray_14_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts3 = new String[5];
        // removed other assertion
        assertEquals("1", elts3[0]);
    }

public void testToArray_15_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts3 = new String[5];
        // removed other assertion
        // removed other assertion
        assertEquals("2", elts3[1]);
    }

public void testToArray_16_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts3 = new String[5];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", elts3[2]);
    }

public void testToArray_17_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts3 = new String[5];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("4", elts3[3]);
    }

public void testToArray_18_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts3 = new String[5];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("5", elts3[4]);
    }

public void testToArray_19_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts3 = new String[5];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, elts3.length);
    }

public void testToArray_20_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts3 = new String[5];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts4 = new String[3];
        final String[] elts4b = list.toArray(elts4);
        assertTrue(elts4 != elts4b);
    }

public void testToArray_21_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts3 = new String[5];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts4 = new String[3];
        final String[] elts4b = list.toArray(elts4);
        // removed other assertion
        assertEquals("1", elts4b[0]);
    }

public void testToArray_22_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts3 = new String[5];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts4 = new String[3];
        final String[] elts4b = list.toArray(elts4);
        // removed other assertion
        // removed other assertion
        assertEquals("2", elts4b[1]);
    }

public void testToArray_23_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts3 = new String[5];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts4 = new String[3];
        final String[] elts4b = list.toArray(elts4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", elts4b[2]);
    }

public void testToArray_24_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts3 = new String[5];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts4 = new String[3];
        final String[] elts4b = list.toArray(elts4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("4", elts4b[3]);
    }

public void testToArray_25_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts3 = new String[5];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts4 = new String[3];
        final String[] elts4b = list.toArray(elts4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("5", elts4b[4]);
    }

public void testToArray_26_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts2 = list.toArray(new String[0]);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts3 = new String[5];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final String[] elts4 = new String[3];
        final String[] elts4b = list.toArray(elts4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, elts4b.length);
    }

public void testSerialization_1_oe() throws Exception {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        final java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(buf);
        out.writeObject(list);
        out.flush();
        out.close();

        final java.io.ByteArrayInputStream bufin = new java.io.ByteArrayInputStream(buf.toByteArray());
        final java.io.ObjectInputStream in = new java.io.ObjectInputStream(bufin);
        final Object list2 = in.readObject();

        assertTrue(list != list2);
    }

public void testSerialization_2_oe() throws Exception {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        final java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(buf);
        out.writeObject(list);
        out.flush();
        out.close();

        final java.io.ByteArrayInputStream bufin = new java.io.ByteArrayInputStream(buf.toByteArray());
        final java.io.ObjectInputStream in = new java.io.ObjectInputStream(bufin);
        final Object list2 = in.readObject();

        // removed other assertion
        assertTrue(list2.equals(list));
    }

public void testSerialization_3_oe() throws Exception {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        final java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(buf);
        out.writeObject(list);
        out.flush();
        out.close();

        final java.io.ByteArrayInputStream bufin = new java.io.ByteArrayInputStream(buf.toByteArray());
        final java.io.ObjectInputStream in = new java.io.ObjectInputStream(bufin);
        final Object list2 = in.readObject();

        // removed other assertion
        // removed other assertion
        assertTrue(list.equals(list2));
    }

public void testSerializationWithOpenCursor_1_oe() throws Exception {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");
        final java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        final java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(buf);
        out.writeObject(list);
        out.flush();
        out.close();

        final java.io.ByteArrayInputStream bufin = new java.io.ByteArrayInputStream(buf.toByteArray());
        final java.io.ObjectInputStream in = new java.io.ObjectInputStream(bufin);
        final Object list2 = in.readObject();

        assertTrue(list != list2);
    }

public void testSerializationWithOpenCursor_2_oe() throws Exception {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");
        final java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        final java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(buf);
        out.writeObject(list);
        out.flush();
        out.close();

        final java.io.ByteArrayInputStream bufin = new java.io.ByteArrayInputStream(buf.toByteArray());
        final java.io.ObjectInputStream in = new java.io.ObjectInputStream(bufin);
        final Object list2 = in.readObject();

        // removed other assertion
        assertTrue(list2.equals(list));
    }

public void testSerializationWithOpenCursor_3_oe() throws Exception {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");
        final java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        final java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(buf);
        out.writeObject(list);
        out.flush();
        out.close();

        final java.io.ByteArrayInputStream bufin = new java.io.ByteArrayInputStream(buf.toByteArray());
        final java.io.ObjectInputStream in = new java.io.ObjectInputStream(bufin);
        final Object list2 = in.readObject();

        // removed other assertion
        // removed other assertion
        assertTrue(list.equals(list2));
    }

public void testLongSerialization_1_oe() throws Exception {
        // recursive serialization will cause a stack
        // overflow exception with long lists
        for (int i = 0; i < 10000; i++) {
            list.add((E) Integer.valueOf(i));
        }

        final java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        final java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(buf);
        out.writeObject(list);
        out.flush();
        out.close();

        final java.io.ByteArrayInputStream bufin = new java.io.ByteArrayInputStream(buf.toByteArray());
        final java.io.ObjectInputStream in = new java.io.ObjectInputStream(bufin);
        final Object list2 = in.readObject();

        assertTrue(list != list2);
    }

public void testLongSerialization_2_oe() throws Exception {
        // recursive serialization will cause a stack
        // overflow exception with long lists
        for (int i = 0; i < 10000; i++) {
            list.add((E) Integer.valueOf(i));
        }

        final java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        final java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(buf);
        out.writeObject(list);
        out.flush();
        out.close();

        final java.io.ByteArrayInputStream bufin = new java.io.ByteArrayInputStream(buf.toByteArray());
        final java.io.ObjectInputStream in = new java.io.ObjectInputStream(bufin);
        final Object list2 = in.readObject();

        // removed other assertion
        assertTrue(list2.equals(list));
    }

public void testLongSerialization_3_oe() throws Exception {
        // recursive serialization will cause a stack
        // overflow exception with long lists
        for (int i = 0; i < 10000; i++) {
            list.add((E) Integer.valueOf(i));
        }

        final java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        final java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(buf);
        out.writeObject(list);
        out.flush();
        out.close();

        final java.io.ByteArrayInputStream bufin = new java.io.ByteArrayInputStream(buf.toByteArray());
        final java.io.ObjectInputStream in = new java.io.ObjectInputStream(bufin);
        final Object list2 = in.readObject();

        // removed other assertion
        // removed other assertion
        assertTrue(list.equals(list2));
    }

}
