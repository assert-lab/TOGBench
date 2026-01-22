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
        return BulkTest.makeSuite(CursorableLinkedListTest_OE25Dev.class);
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

    public void testAdd_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.add((E) Integer.valueOf(2)));
    }

    public void testAdd_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.add((E) Integer.valueOf(3)));
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

    public void testContains_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.add((E) "B"));
    }

    public void testContains_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(list.addFirst((E) "a"));
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

    public void testInternalState_CursorNextNextPreviousRemoveIndex1ByList_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
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

    public void testInternalState_CursorNextNextRemoveIndex1ByList_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
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

    public void testInternalState_CursorNextNextNextRemoveIndex1ByList_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
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

    public void testInternalState_CursorNextNextPreviousRemoveByIterator_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
    }

    public void testInternalState_CursorNextNextRemoveByIterator_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
    }

    public void testInternalState_CursorNextNextPreviousAddIndex1ByList_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
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

    public void testInternalState_CursorNextAddIndex1ByList_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
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

    public void testInternalState_CursorNextNextAddIndex1ByList_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
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

    public void testInternalState_CursorNextNextPreviousAddByIterator_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
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

    public void testInternalState_CursorNextNextAddByIterator_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
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

    public void testInternalState_CursorNextNextRemoveByListSetByIterator_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
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

    public void testInternalState_CursorNextNextSetByIterator_1_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
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
