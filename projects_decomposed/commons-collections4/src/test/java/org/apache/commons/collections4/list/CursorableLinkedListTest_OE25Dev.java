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

    @SuppressWarnings("unchecked")
    public void testAdd() {
        assertEquals("[]",list.toString());
        assertTrue(list.add((E) Integer.valueOf(1)));
        assertEquals("[1]",list.toString());
        assertTrue(list.add((E) Integer.valueOf(2)));
        assertEquals("[1, 2]",list.toString());
        assertTrue(list.add((E) Integer.valueOf(3)));
        assertEquals("[1, 2, 3]",list.toString());
        assertTrue(list.addFirst((E) Integer.valueOf(0)));
        assertEquals("[0, 1, 2, 3]",list.toString());
        assertTrue(list.addLast((E) Integer.valueOf(4)));
        assertEquals("[0, 1, 2, 3, 4]",list.toString());
        list.add(0,(E) Integer.valueOf(-2));
        assertEquals("[-2, 0, 1, 2, 3, 4]",list.toString());
        list.add(1,(E) Integer.valueOf(-1));
        assertEquals("[-2, -1, 0, 1, 2, 3, 4]",list.toString());
        list.add(7,(E) Integer.valueOf(5));
        assertEquals("[-2, -1, 0, 1, 2, 3, 4, 5]",list.toString());

        final List<E> list2 = new LinkedList<>();
        list2.add((E) "A");
        list2.add((E) "B");
        list2.add((E) "C");

        assertTrue(list.addAll(list2));
        assertEquals("[-2, -1, 0, 1, 2, 3, 4, 5, A, B, C]",list.toString());
        assertTrue(list.addAll(3,list2));
        assertEquals("[-2, -1, 0, A, B, C, 1, 2, 3, 4, 5, A, B, C]",list.toString());
    }

    @SuppressWarnings("unchecked")
    public void testClear() {
        assertEquals(0,list.size());
        assertTrue(list.isEmpty());
        list.clear();
        assertEquals(0,list.size());
        assertTrue(list.isEmpty());

        list.add((E) "element");
        assertEquals(1,list.size());
        assertTrue(!list.isEmpty());

        list.clear();
        assertEquals(0,list.size());
        assertTrue(list.isEmpty());

        list.add((E) "element1");
        list.add((E) "element2");
        assertEquals(2,list.size());
        assertTrue(!list.isEmpty());

        list.clear();
        assertEquals(0,list.size());
        assertTrue(list.isEmpty());

        for (int i = 0; i < 1000; i++) {
            list.add((E) Integer.valueOf(i));
        }
        assertEquals(1000, list.size());
        assertTrue(!list.isEmpty());

        list.clear();
        assertEquals(0,list.size());
        assertTrue(list.isEmpty());
    }

    @SuppressWarnings("unchecked")
    public void testContains() {
        assertTrue(!list.contains("A"));
        assertTrue(list.add((E) "A"));
        assertTrue(list.contains("A"));
        assertTrue(list.add((E) "B"));
        assertTrue(list.contains("A"));
        assertTrue(list.addFirst((E) "a"));
        assertTrue(list.contains("A"));
        assertTrue(list.remove("a"));
        assertTrue(list.contains("A"));
        assertTrue(list.remove("A"));
        assertTrue(!list.contains("A"));
    }

    @SuppressWarnings("unchecked")
    public void testContainsAll() {
        assertTrue(list.containsAll(list));
        final java.util.List<E> list2 = new java.util.LinkedList<>();
        assertTrue(list.containsAll(list2));
        list2.add((E) "A");
        assertTrue(!list.containsAll(list2));
        list.add((E) "B");
        list.add((E) "A");
        assertTrue(list.containsAll(list2));
        list2.add((E) "B");
        assertTrue(list.containsAll(list2));
        list2.add((E) "C");
        assertTrue(!list.containsAll(list2));
        list.add((E) "C");
        assertTrue(list.containsAll(list2));
        list2.add((E) "C");
        assertTrue(list.containsAll(list2));
        assertTrue(list.containsAll(list));
    }

    @SuppressWarnings("unchecked")
    public void testCursorNavigation() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertTrue(it.hasNext());
        assertTrue(!it.hasPrevious());
        assertEquals("1", it.next());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals("1", it.previous());
        assertTrue(it.hasNext());
        assertTrue(!it.hasPrevious());
        assertEquals("1", it.next());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals("2", it.next());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals("2", it.previous());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals("2", it.next());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals("3", it.next());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals("4", it.next());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals("5", it.next());
        assertTrue(!it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals("5", it.previous());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals("4", it.previous());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals("3", it.previous());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals("2", it.previous());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals("1", it.previous());
        assertTrue(it.hasNext());
        assertTrue(!it.hasPrevious());
        it.close();
    }

    @SuppressWarnings("unchecked")
    public void testCursorSet() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertEquals("1", it.next());
        it.set((E) "a");
        assertEquals("a", it.previous());
        it.set((E) "A");
        assertEquals("A", it.next());
        assertEquals("2", it.next());
        it.set((E) "B");
        assertEquals("3", it.next());
        assertEquals("4", it.next());
        it.set((E) "D");
        assertEquals("5", it.next());
        it.set((E) "E");
        assertEquals("[A, B, 3, D, E]", list.toString());
        it.close();
    }

    @SuppressWarnings("unchecked")
    public void testCursorRemove() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> it = list.cursor();
        try {
            it.remove();
            fail();
        } catch (final IllegalStateException e) {
            // expected
        }
        assertEquals("1", it.next());
        assertEquals("2", it.next());
        assertEquals("[1, 2, 3, 4, 5]", list.toString());
        it.remove();
        assertEquals("[1, 3, 4, 5]", list.toString());
        assertEquals("3", it.next());
        assertEquals("3", it.previous());
        assertEquals("1", it.previous());
        it.remove();
        assertEquals("[3, 4, 5]", list.toString());
        assertTrue(!it.hasPrevious());
        assertEquals("3", it.next());
        it.remove();
        assertEquals("[4, 5]", list.toString());
        try {
            it.remove();
        } catch (final IllegalStateException e) {
            // expected
        }
        assertEquals("4", it.next());
        assertEquals("5", it.next());
        it.remove();
        assertEquals("[4]", list.toString());
        assertEquals("4", it.previous());
        it.remove();
        assertEquals("[]", list.toString());
        it.close();
    }

    @SuppressWarnings("unchecked")
    public void testCursorAdd() {
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        it.add((E) "1");
        assertEquals("[1]", list.toString());
        it.add((E) "3");
        assertEquals("[1, 3]", list.toString());
        it.add((E) "5");
        assertEquals("[1, 3, 5]", list.toString());
        assertEquals("5", it.previous());
        it.add((E) "4");
        assertEquals("[1, 3, 4, 5]", list.toString());
        assertEquals("4", it.previous());
        assertEquals("3", it.previous());
        it.add((E) "2");
        assertEquals("[1, 2, 3, 4, 5]", list.toString());
        it.close();
    }

    @SuppressWarnings("unchecked")
    public void testCursorConcurrentModification() {
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
        assertEquals("2", li.next());
        li.remove();
        assertEquals("3", li.next());
        assertEquals("1", c1.next());
        assertEquals("3", c1.next());
        assertEquals("1", c2.next());

        // test cursor c1 can remove elements from previously modified list
        // test cursor c2 skips elements removed via different cursor
        c1.remove();
        assertEquals("5", c2.next());
        c2.add((E) "6");
        assertEquals("5", c1.next());
        assertEquals("6", c1.next());
        assertEquals("7", c1.next());

        // test cursors remain valid when list mod via CursorableLinkedList
        // test cursor remains valid when elements inserted into list before
        // the current position of the cursor.
        list.add(0, (E) "0");

        // test cursor remains valid when element inserted immediately after
        // current element of a cursor, and the element is seen on the
        // next call to the next method of that cursor.
        list.add(5, (E) "8");

        assertEquals("8", c1.next());
        assertEquals("9", c1.next());
        c1.add((E) "10");
        assertEquals("7", c2.next());
        assertEquals("8", c2.next());
        assertEquals("9", c2.next());
        assertEquals("10", c2.next());

        try {
            c2.next();
            fail();
        } catch (final NoSuchElementException nse) {
        }

        try {
            li.next();
            fail();
        } catch (final ConcurrentModificationException cme) {
        }

        c1.close(); // not necessary
        c2.close(); // not necessary
    }

    @SuppressWarnings("unchecked")
    public void testCursorNextIndexMid() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final Iterator<E> li = list.iterator();

        // test cursors remain valid when list modified by std Iterator
        // test cursors skip elements removed via ListIterator
        assertEquals("1", li.next());
        assertEquals("2", li.next());
        li.remove();
        assertEquals(0, c1.nextIndex());
        assertEquals("1", c1.next());
        assertEquals(1, c1.nextIndex());
        assertEquals("3", c1.next());
    }

    @SuppressWarnings("unchecked")
    public void testCursorNextIndexFirst() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        assertEquals(0, c1.nextIndex());
        list.remove(0);
        assertEquals(0, c1.nextIndex());
        assertEquals("2", c1.next());
        assertEquals(1, c1.nextIndex());
        assertEquals("3", c1.next());
    }

    @SuppressWarnings("unchecked")
    public void testCursorNextIndexAddBefore() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        assertEquals(0, c1.nextIndex());
        assertEquals("1", c1.next());
        list.add(0, (E) "0");
        assertEquals(2, c1.nextIndex());
        assertEquals("2", c1.next());
    }

    @SuppressWarnings("unchecked")
    public void testCursorNextIndexAddNext() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        assertEquals(0, c1.nextIndex());
        list.add(0, (E) "0");
        assertEquals(0, c1.nextIndex());
        assertEquals("0", c1.next());
        assertEquals(1, c1.nextIndex());
        assertEquals("1", c1.next());
    }

    @SuppressWarnings("unchecked")
    public void testCursorNextIndexAddAfter() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        assertEquals(0, c1.nextIndex());
        list.add(1, (E) "0");
        assertEquals(0, c1.nextIndex());
        assertEquals("1", c1.next());
        assertEquals(1, c1.nextIndex());
        assertEquals("0", c1.next());
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public void testInternalState_CursorNextNextPreviousRemoveIndex1ByList() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
        assertEquals("B", c1.next());
        assertEquals("B", c1.previous());

        assertEquals("B", list.remove(1));

        assertEquals(true, c1.nextIndexValid);
        assertEquals(1, c1.nextIndex);
        assertEquals(true, c1.currentRemovedByAnother);
        assertEquals(null, c1.current);
        assertEquals("C", c1.next.value);

        assertEquals("[A, C]", list.toString());
        c1.remove();  // works ok
        assertEquals("[A, C]", list.toString());
        try {
            c1.remove();
            fail();
        } catch (final IllegalStateException ex) {}
    }

    @SuppressWarnings("unchecked")
    public void testInternalState_CursorNextRemoveIndex1ByList() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());

        assertEquals("B", list.remove(1));

        assertEquals(true, c1.nextIndexValid);
        assertEquals(1, c1.nextIndex);
        assertEquals(false, c1.currentRemovedByAnother);
        assertEquals("A", c1.current.value);
        assertEquals("C", c1.next.value);

        assertEquals("[A, C]", list.toString());
        c1.remove();  // works ok
        assertEquals("[C]", list.toString());
        try {
            c1.remove();
            fail();
        } catch (final IllegalStateException ex) {}
    }

    @SuppressWarnings("unchecked")
    public void testInternalState_CursorNextNextRemoveIndex1ByList() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
        assertEquals("B", c1.next());

        assertEquals("B", list.remove(1));

        assertEquals(true, c1.nextIndexValid);
        assertEquals(1, c1.nextIndex);
        assertEquals(true, c1.currentRemovedByAnother);
        assertEquals(null, c1.current);
        assertEquals("C", c1.next.value);

        assertEquals("[A, C]", list.toString());
        c1.remove();  // works ok
        assertEquals("[A, C]", list.toString());
        try {
            c1.remove();
            fail();
        } catch (final IllegalStateException ex) {}
    }

    @SuppressWarnings("unchecked")
    public void testInternalState_CursorNextNextNextRemoveIndex1ByList() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
        assertEquals("B", c1.next());
        assertEquals("C", c1.next());

        assertEquals("B", list.remove(1));

        assertEquals(false, c1.nextIndexValid);
        assertEquals(false, c1.currentRemovedByAnother);
        assertEquals("C", c1.current.value);
        assertEquals("D", c1.next.value);

        assertEquals("[A, C, D]", list.toString());
        c1.remove();  // works ok
        assertEquals("[A, D]", list.toString());
        try {
            c1.remove();
            fail();
        } catch (final IllegalStateException ex) {}
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public void testInternalState_CursorNextNextPreviousRemoveByIterator() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
        assertEquals("B", c1.next());
        assertEquals("B", c1.previous());

        c1.remove();

        assertEquals(true, c1.nextIndexValid);
        assertEquals(1, c1.nextIndex);
        assertEquals(false, c1.currentRemovedByAnother);
        assertEquals(null, c1.current);
        assertEquals("C", c1.next.value);

        assertEquals("[A, C]", list.toString());
        try {
            c1.remove();
            fail();
        } catch (final IllegalStateException ex) {}
    }

    @SuppressWarnings("unchecked")
    public void testInternalState_CursorNextNextRemoveByIterator() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
        assertEquals("B", c1.next());

        c1.remove();

        assertEquals(true, c1.nextIndexValid);
        assertEquals(1, c1.nextIndex);
        assertEquals(false, c1.currentRemovedByAnother);
        assertEquals(null, c1.current);
        assertEquals("C", c1.next.value);

        assertEquals("[A, C]", list.toString());
        try {
            c1.remove();
            fail();
        } catch (final IllegalStateException ex) {}
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public void testInternalState_CursorNextNextPreviousAddIndex1ByList() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
        assertEquals("B", c1.next());
        assertEquals("B", c1.previous());

        list.add(1, (E) "Z");

        assertEquals(true, c1.nextIndexValid);
        assertEquals(1, c1.nextIndex);
        assertEquals("B", c1.current.value);
        assertEquals("Z", c1.next.value);

        assertEquals("[A, Z, B, C]", list.toString());
        c1.remove();  // works ok
        assertEquals("[A, Z, C]", list.toString());
        try {
            c1.remove();
            fail();
        } catch (final IllegalStateException ex) {}
    }

    @SuppressWarnings("unchecked")
    public void testInternalState_CursorNextAddIndex1ByList() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());

        list.add(1, (E) "Z");

        assertEquals(true, c1.nextIndexValid);
        assertEquals(1, c1.nextIndex);
        assertEquals("A", c1.current.value);
        assertEquals("Z", c1.next.value);

        assertEquals("[A, Z, B, C]", list.toString());
        c1.remove();  // works ok
        assertEquals("[Z, B, C]", list.toString());
        try {
            c1.remove();
            fail();
        } catch (final IllegalStateException ex) {}
    }

    @SuppressWarnings("unchecked")
    public void testInternalState_CursorNextNextAddIndex1ByList() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
        assertEquals("B", c1.next());

        list.add(1, (E) "Z");

        assertEquals(false, c1.nextIndexValid);
        assertEquals("B", c1.current.value);
        assertEquals("C", c1.next.value);

        assertEquals("[A, Z, B, C]", list.toString());
        c1.remove();  // works ok
        assertEquals("[A, Z, C]", list.toString());
        try {
            c1.remove();
            fail();
        } catch (final IllegalStateException ex) {}
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public void testInternalState_CursorNextNextPreviousAddByIterator() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
        assertEquals("B", c1.next());
        assertEquals("B", c1.previous());

        c1.add((E) "Z");

        assertEquals(true, c1.nextIndexValid);
        assertEquals(2, c1.nextIndex);
        assertEquals(null, c1.current);
        assertEquals("B", c1.next.value);

        assertEquals("[A, Z, B, C]", list.toString());
        try {
            c1.remove();
            fail();
        } catch (final IllegalStateException ex) {}
    }

    @SuppressWarnings("unchecked")
    public void testInternalState_CursorNextNextAddByIterator() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
        assertEquals("B", c1.next());

        c1.add((E) "Z");

        assertEquals(true, c1.nextIndexValid);
        assertEquals(3, c1.nextIndex);
        assertEquals(false, c1.currentRemovedByAnother);
        assertEquals(null, c1.current);
        assertEquals("C", c1.next.value);

        assertEquals("[A, B, Z, C]", list.toString());
        try {
            c1.remove();
            fail();
        } catch (final IllegalStateException ex) {}
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public void testInternalState_CursorNextNextRemoveByListSetByIterator() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
        assertEquals("B", c1.next());

        list.remove(1);

        assertEquals(true, c1.nextIndexValid);
        assertEquals(1, c1.nextIndex);
        assertEquals(null, c1.current);
        assertEquals("C", c1.next.value);
        assertEquals("[A, C]", list.toString());

        try {
            c1.set((E) "Z");
            fail();
        } catch (final IllegalStateException ex) {}
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public void testInternalState_CursorNextNextPreviousSetByIterator() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
        assertEquals("B", c1.next());
        assertEquals("B", c1.previous());

        c1.set((E) "Z");

        assertEquals(true, c1.nextIndexValid);
        assertEquals(1, c1.nextIndex);
        assertEquals("Z", c1.current.value);
        assertEquals("Z", c1.next.value);

        assertEquals("[A, Z, C]", list.toString());
        c1.remove();  // works ok
        assertEquals("[A, C]", list.toString());
        try {
            c1.remove();
            fail();
        } catch (final IllegalStateException ex) {}
    }

    @SuppressWarnings("unchecked")
    public void testInternalState_CursorNextNextSetByIterator() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        assertEquals("A", c1.next());
        assertEquals("B", c1.next());

        c1.set((E) "Z");

        assertEquals(true, c1.nextIndexValid);
        assertEquals(2, c1.nextIndex);
        assertEquals("Z", c1.current.value);
        assertEquals("C", c1.next.value);

        assertEquals("[A, Z, C]", list.toString());
        c1.remove();  // works ok
        assertEquals("[A, C]", list.toString());
        try {
            c1.remove();
            fail();
        } catch (final IllegalStateException ex) {}
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public void testEqualsAndHashCode() {
        assertTrue(list.equals(list));
        assertEquals(list.hashCode(),list.hashCode());
        list.add((E) "A");
        assertTrue(list.equals(list));
        assertEquals(list.hashCode(),list.hashCode());

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        assertTrue(!list.equals(list2));
        assertTrue(!list2.equals(list));

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        assertTrue(!list.equals(list3));
        assertTrue(!list3.equals(list));
        assertTrue(list2.equals(list3));
        assertTrue(list3.equals(list2));
        assertEquals(list2.hashCode(),list3.hashCode());

        list2.add((E) "A");
        assertTrue(list.equals(list2));
        assertTrue(list2.equals(list));
        assertTrue(!list2.equals(list3));
        assertTrue(!list3.equals(list2));

        list3.add((E) "A");
        assertTrue(list2.equals(list3));
        assertTrue(list3.equals(list2));
        assertEquals(list2.hashCode(),list3.hashCode());

        list.add((E) "B");
        assertTrue(list.equals(list));
        assertTrue(!list.equals(list2));
        assertTrue(!list2.equals(list));
        assertTrue(!list.equals(list3));
        assertTrue(!list3.equals(list));

        list2.add((E) "B");
        list3.add((E) "B");
        assertTrue(list.equals(list));
        assertTrue(list.equals(list2));
        assertTrue(list2.equals(list));
        assertTrue(list2.equals(list3));
        assertTrue(list3.equals(list2));
        assertEquals(list2.hashCode(),list3.hashCode());

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        assertTrue(list.equals(list));
        assertTrue(list.equals(list2));
        assertTrue(list2.equals(list));
        assertTrue(list2.equals(list3));
        assertTrue(list3.equals(list2));
        assertEquals(list.hashCode(),list2.hashCode());
        assertEquals(list2.hashCode(),list3.hashCode());

        list.add((E) "D");
        list2.addFirst((E) "D");
        assertTrue(list.equals(list));
        assertTrue(!list.equals(list2));
        assertTrue(!list2.equals(list));
    }

    @SuppressWarnings("unchecked")
    public void testGet() {
        try {
            list.get(0);
            fail("shouldn't get here");
        } catch(final IndexOutOfBoundsException e) {
            // expected
        }

        assertTrue(list.add((E) "A"));
        assertEquals("A",list.get(0));
        assertTrue(list.add((E) "B"));
        assertEquals("A",list.get(0));
        assertEquals("B",list.get(1));

        try {
            list.get(-1);
            fail("shouldn't get here");
        } catch(final IndexOutOfBoundsException e) {
            // expected
        }

        try {
            list.get(2);
            fail("shouldn't get here");
        } catch(final IndexOutOfBoundsException e) {
            // expected
        }
    }

    @SuppressWarnings("unchecked")
    public void testIndexOf() {
        assertEquals(-1,list.indexOf("A"));
        assertEquals(-1,list.lastIndexOf("A"));
        list.add((E) "A");
        assertEquals(0,list.indexOf("A"));
        assertEquals(0,list.lastIndexOf("A"));
        assertEquals(-1,list.indexOf("B"));
        assertEquals(-1,list.lastIndexOf("B"));
        list.add((E) "B");
        assertEquals(0,list.indexOf("A"));
        assertEquals(0,list.lastIndexOf("A"));
        assertEquals(1,list.indexOf("B"));
        assertEquals(1,list.lastIndexOf("B"));
        list.addFirst((E) "B");
        assertEquals(1,list.indexOf("A"));
        assertEquals(1,list.lastIndexOf("A"));
        assertEquals(0,list.indexOf("B"));
        assertEquals(2,list.lastIndexOf("B"));
    }

    @SuppressWarnings("unchecked")
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add((E) "element");
        assertTrue(!list.isEmpty());
        list.remove("element");
        assertTrue(list.isEmpty());
        list.add((E) "element");
        assertTrue(!list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @SuppressWarnings("unchecked")
    public void testIterator() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("1", it.next());
        assertTrue(it.hasNext());
        assertEquals("2", it.next());
        assertTrue(it.hasNext());
        assertEquals("3", it.next());
        assertTrue(it.hasNext());
        assertEquals("4", it.next());
        assertTrue(it.hasNext());
        assertEquals("5", it.next());
        assertTrue(!it.hasNext());

        it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("1", it.next());
        it.remove();
        assertEquals("[2, 3, 4, 5]", list.toString());
        assertTrue(it.hasNext());
        assertEquals("2", it.next());
        it.remove();
        assertEquals("[3, 4, 5]", list.toString());
        assertTrue(it.hasNext());
        assertEquals("3", it.next());
        it.remove();
        assertEquals("[4, 5]", list.toString());
        assertTrue(it.hasNext());
        assertEquals("4", it.next());
        it.remove();
        assertEquals("[5]", list.toString());
        assertTrue(it.hasNext());
        assertEquals("5", it.next());
        it.remove();
        assertEquals("[]", list.toString());
        assertTrue(!it.hasNext());
    }

    @SuppressWarnings("unchecked")
    public void testListIteratorNavigation() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertTrue(it.hasNext());
        assertTrue(!it.hasPrevious());
        assertEquals(-1, it.previousIndex());
        assertEquals(0, it.nextIndex());
        assertEquals("1", it.next());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals(0, it.previousIndex());
        assertEquals(1, it.nextIndex());
        assertEquals("1", it.previous());
        assertTrue(it.hasNext());
        assertTrue(!it.hasPrevious());
        assertEquals(-1, it.previousIndex());
        assertEquals(0, it.nextIndex());
        assertEquals("1", it.next());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals(0, it.previousIndex());
        assertEquals(1, it.nextIndex());
        assertEquals("2", it.next());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals(1, it.previousIndex());
        assertEquals(2, it.nextIndex());
        assertEquals("2", it.previous());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals(0, it.previousIndex());
        assertEquals(1, it.nextIndex());
        assertEquals("2", it.next());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals(1, it.previousIndex());
        assertEquals(2, it.nextIndex());
        assertEquals("3", it.next());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals(2, it.previousIndex());
        assertEquals(3, it.nextIndex());
        assertEquals("4", it.next());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals(3, it.previousIndex());
        assertEquals(4, it.nextIndex());
        assertEquals("5", it.next());
        assertTrue(!it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals(4, it.previousIndex());
        assertEquals(5, it.nextIndex());
        assertEquals("5", it.previous());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals(3, it.previousIndex());
        assertEquals(4, it.nextIndex());
        assertEquals("4", it.previous());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals(2, it.previousIndex());
        assertEquals(3, it.nextIndex());
        assertEquals("3", it.previous());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals(1, it.previousIndex());
        assertEquals(2, it.nextIndex());
        assertEquals("2", it.previous());
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals(0, it.previousIndex());
        assertEquals(1, it.nextIndex());
        assertEquals("1", it.previous());
        assertTrue(it.hasNext());
        assertTrue(!it.hasPrevious());
        assertEquals(-1, it.previousIndex());
        assertEquals(0, it.nextIndex());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void testListIteratorSet() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final ListIterator<E> it = list.listIterator();
        assertEquals("1", it.next());
        it.set((E) "a");
        assertEquals("a", it.previous());
        it.set((E) "A");
        assertEquals("A", it.next());
        assertEquals("2", it.next());
        it.set((E) "B");
        assertEquals("3", it.next());
        assertEquals("4", it.next());
        it.set((E) "D");
        assertEquals("5", it.next());
        it.set((E) "E");
        assertEquals("[A, B, 3, D, E]", list.toString());
    }

    @SuppressWarnings("unchecked")
    public void testListIteratorRemove() {
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
        assertEquals("2",it.next());
        assertEquals("[1, 2, 3, 4, 5]",list.toString());
        it.remove();
        assertEquals("[1, 3, 4, 5]",list.toString());
        assertEquals("3",it.next());
        assertEquals("3",it.previous());
        assertEquals("1",it.previous());
        it.remove();
        assertEquals("[3, 4, 5]",list.toString());
        assertTrue(!it.hasPrevious());
        assertEquals("3",it.next());
        it.remove();
        assertEquals("[4, 5]",list.toString());
        try {
            it.remove();
        } catch(final IllegalStateException e) {
            // expected
        }
        assertEquals("4",it.next());
        assertEquals("5",it.next());
        it.remove();
        assertEquals("[4]",list.toString());
        assertEquals("4",it.previous());
        it.remove();
        assertEquals("[]",list.toString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void testListIteratorAdd() {
        final ListIterator<E> it = list.listIterator();
        it.add((E) "1");
        assertEquals("[1]", list.toString());
        it.add((E) "3");
        assertEquals("[1, 3]", list.toString());
        it.add((E) "5");
        assertEquals("[1, 3, 5]", list.toString());
        assertEquals("5", it.previous());
        it.add((E) "4");
        assertEquals("[1, 3, 4, 5]", list.toString());
        assertEquals("4", it.previous());
        assertEquals("3", it.previous());
        it.add((E) "2");
        assertEquals("[1, 2, 3, 4, 5]", list.toString());
    }

    @SuppressWarnings("unchecked")
    public void testRemoveAll() {
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
        assertEquals("[1, 3, 5]", list.toString());
        assertTrue(!list.removeAll(set));
    }

    @SuppressWarnings("unchecked")
    public void testRemoveByIndex() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        assertEquals("[1, 2, 3, 4, 5]", list.toString());
        assertEquals("1", list.remove(0));
        assertEquals("[2, 3, 4, 5]", list.toString());
        assertEquals("3", list.remove(1));
        assertEquals("[2, 4, 5]", list.toString());
        assertEquals("4", list.remove(1));
        assertEquals("[2, 5]", list.toString());
        assertEquals("5", list.remove(1));
        assertEquals("[2]", list.toString());
        assertEquals("2", list.remove(0));
        assertEquals("[]", list.toString());
    }

    @SuppressWarnings("unchecked")
    public void testRemove() {
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
        assertTrue(!list.remove("6"));
        assertTrue(list.remove("5"));
        assertEquals("[1, 1, 2, 3, 4, 2, 3, 4, 5]", list.toString());
        assertTrue(list.remove("5"));
        assertEquals("[1, 1, 2, 3, 4, 2, 3, 4]", list.toString());
        assertTrue(!list.remove("5"));
        assertTrue(list.remove("1"));
        assertEquals("[1, 2, 3, 4, 2, 3, 4]", list.toString());
        assertTrue(list.remove("1"));
        assertEquals("[2, 3, 4, 2, 3, 4]", list.toString());
        assertTrue(list.remove("2"));
        assertEquals("[3, 4, 2, 3, 4]", list.toString());
        assertTrue(list.remove("2"));
        assertEquals("[3, 4, 3, 4]", list.toString());
        assertTrue(list.remove("3"));
        assertEquals("[4, 3, 4]", list.toString());
        assertTrue(list.remove("3"));
        assertEquals("[4, 4]", list.toString());
        assertTrue(list.remove("4"));
        assertEquals("[4]", list.toString());
        assertTrue(list.remove("4"));
        assertEquals("[]", list.toString());
    }

    @SuppressWarnings("unchecked")
    public void testRetainAll() {
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
        assertEquals("[2, 2, 4, 4]", list.toString());
        assertTrue(!list.retainAll(set));
    }

    @SuppressWarnings("unchecked")
    public void testSet() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        assertEquals("[1, 2, 3, 4, 5]", list.toString());
        list.set(0, (E) "A");
        assertEquals("[A, 2, 3, 4, 5]", list.toString());
        list.set(1, (E) "B");
        assertEquals("[A, B, 3, 4, 5]", list.toString());
        list.set(2, (E) "C");
        assertEquals("[A, B, C, 4, 5]", list.toString());
        list.set(3, (E) "D");
        assertEquals("[A, B, C, D, 5]", list.toString());
        list.set(4, (E) "E");
        assertEquals("[A, B, C, D, E]", list.toString());
    }

    @SuppressWarnings("unchecked")
    public void testSubList() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        assertEquals("[A, B, C, D, E]", list.toString());
        assertEquals("[A, B, C, D, E]", list.subList(0, 5).toString());
        assertEquals("[B, C, D, E]", list.subList(1, 5).toString());
        assertEquals("[C, D, E]", list.subList(2, 5).toString());
        assertEquals("[D, E]", list.subList(3, 5).toString());
        assertEquals("[E]", list.subList(4, 5).toString());
        assertEquals("[]", list.subList(5, 5).toString());
    }

    @SuppressWarnings("unchecked")
    public void testSubListAddEnd() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(5, 5);
        sublist.add((E) "F");
        assertEquals("[A, B, C, D, E, F]", list.toString());
        assertEquals("[F]", sublist.toString());
        sublist.add((E) "G");
        assertEquals("[A, B, C, D, E, F, G]", list.toString());
        assertEquals("[F, G]", sublist.toString());
    }

    @SuppressWarnings("unchecked")
    public void testSubListAddBegin() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(0, 0);
        sublist.add((E) "a");
        assertEquals("[a, A, B, C, D, E]", list.toString());
        assertEquals("[a]", sublist.toString());
        sublist.add((E) "b");
        assertEquals("[a, b, A, B, C, D, E]", list.toString());
        assertEquals("[a, b]", sublist.toString());
    }

    @SuppressWarnings("unchecked")
    public void testSubListAddMiddle() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 3);
        sublist.add((E) "a");
        assertEquals("[A, B, C, a, D, E]", list.toString());
        assertEquals("[B, C, a]", sublist.toString());
        sublist.add((E) "b");
        assertEquals("[A, B, C, a, b, D, E]", list.toString());
        assertEquals("[B, C, a, b]", sublist.toString());
    }

    @SuppressWarnings("unchecked")
    public void testSubListRemove() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 4);
        assertEquals("[B, C, D]", sublist.toString());
        assertEquals("[A, B, C, D, E]", list.toString());
        sublist.remove("C");
        assertEquals("[B, D]", sublist.toString());
        assertEquals("[A, B, D, E]", list.toString());
        sublist.remove(1);
        assertEquals("[B]", sublist.toString());
        assertEquals("[A, B, E]", list.toString());
        sublist.clear();
        assertEquals("[]", sublist.toString());
        assertEquals("[A, E]", list.toString());
    }

    @SuppressWarnings("unchecked")
    public void testToArray() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        assertEquals("1", elts[0]);
        assertEquals("2", elts[1]);
        assertEquals("3", elts[2]);
        assertEquals("4", elts[3]);
        assertEquals("5", elts[4]);
        assertEquals(5, elts.length);

        final String[] elts2 = list.toArray(new String[0]);
        assertEquals("1", elts2[0]);
        assertEquals("2", elts2[1]);
        assertEquals("3", elts2[2]);
        assertEquals("4", elts2[3]);
        assertEquals("5", elts2[4]);
        assertEquals(5, elts2.length);

        final String[] elts3 = new String[5];
        assertSame(elts3, list.toArray(elts3));
        assertEquals("1", elts3[0]);
        assertEquals("2", elts3[1]);
        assertEquals("3", elts3[2]);
        assertEquals("4", elts3[3]);
        assertEquals("5", elts3[4]);
        assertEquals(5, elts3.length);

        final String[] elts4 = new String[3];
        final String[] elts4b = list.toArray(elts4);
        assertTrue(elts4 != elts4b);
        assertEquals("1", elts4b[0]);
        assertEquals("2", elts4b[1]);
        assertEquals("3", elts4b[2]);
        assertEquals("4", elts4b[3]);
        assertEquals("5", elts4b[4]);
        assertEquals(5, elts4b.length);
    }

    @SuppressWarnings("unchecked")
    public void testSerialization() throws Exception {
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
        assertTrue(list2.equals(list));
        assertTrue(list.equals(list2));
    }

    @SuppressWarnings("unchecked")
    public void testSerializationWithOpenCursor() throws Exception {
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
        assertTrue(list2.equals(list));
        assertTrue(list.equals(list2));
    }

    @SuppressWarnings("unchecked")
    public void testLongSerialization() throws Exception {
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
        assertTrue(list2.equals(list));
        assertTrue(list.equals(list2));
    }

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
        assertTrue(list.add((E) Integer.valueOf(1)));
    }

    public void testAdd_4_oe() {
        assertTrue(list.add((E) Integer.valueOf(2)));
    }

    public void testAdd_6_oe() {
        assertTrue(list.add((E) Integer.valueOf(3)));
    }

    public void testAdd_8_oe() {
        assertTrue(list.addFirst((E) Integer.valueOf(0)));
    }

    public void testAdd_10_oe() {
        assertTrue(list.addLast((E) Integer.valueOf(4)));
    }

    public void testClear_1_oe() {
        assertEquals(0,list.size());
    }

    public void testClear_2_oe() {
        assertTrue(list.isEmpty());
    }

    public void testClear_3_oe() {
        list.clear();
        assertEquals(0,list.size());
    }

    public void testClear_4_oe() {
        list.clear();
        assertTrue(list.isEmpty());
    }

    public void testClear_5_oe() {
        list.clear();

        list.add((E) "element");
        assertEquals(1,list.size());
    }

    public void testClear_6_oe() {
        list.clear();

        list.add((E) "element");
        assertTrue(!list.isEmpty());
    }

    public void testClear_7_oe() {
        list.clear();

        list.add((E) "element");

        list.clear();
        assertEquals(0,list.size());
    }

    public void testClear_8_oe() {
        list.clear();

        list.add((E) "element");

        list.clear();
        assertTrue(list.isEmpty());
    }

    public void testClear_9_oe() {
        list.clear();

        list.add((E) "element");

        list.clear();

        list.add((E) "element1");
        list.add((E) "element2");
        assertEquals(2,list.size());
    }

    public void testClear_10_oe() {
        list.clear();

        list.add((E) "element");

        list.clear();

        list.add((E) "element1");
        list.add((E) "element2");
        assertTrue(!list.isEmpty());
    }

    public void testClear_11_oe() {
        list.clear();

        list.add((E) "element");

        list.clear();

        list.add((E) "element1");
        list.add((E) "element2");

        list.clear();
        assertEquals(0,list.size());
    }

    public void testClear_12_oe() {
        list.clear();

        list.add((E) "element");

        list.clear();

        list.add((E) "element1");
        list.add((E) "element2");

        list.clear();
        assertTrue(list.isEmpty());
    }

    public void testClear_13_oe() {
        list.clear();

        list.add((E) "element");

        list.clear();

        list.add((E) "element1");
        list.add((E) "element2");

        list.clear();

        for (int i = 0; i < 1000; i++) {
            list.add((E) Integer.valueOf(i));
        }
        assertEquals(1000, list.size());
    }

    public void testClear_14_oe() {
        list.clear();

        list.add((E) "element");

        list.clear();

        list.add((E) "element1");
        list.add((E) "element2");

        list.clear();

        for (int i = 0; i < 1000; i++) {
            list.add((E) Integer.valueOf(i));
        }
        assertTrue(!list.isEmpty());
    }

    public void testClear_15_oe() {
        list.clear();

        list.add((E) "element");

        list.clear();

        list.add((E) "element1");
        list.add((E) "element2");

        list.clear();

        for (int i = 0; i < 1000; i++) {
            list.add((E) Integer.valueOf(i));
        }

        list.clear();
        assertEquals(0,list.size());
    }

    public void testClear_16_oe() {
        list.clear();

        list.add((E) "element");

        list.clear();

        list.add((E) "element1");
        list.add((E) "element2");

        list.clear();

        for (int i = 0; i < 1000; i++) {
            list.add((E) Integer.valueOf(i));
        }

        list.clear();
        assertTrue(list.isEmpty());
    }

    public void testContains_1_oe() {
        assertTrue(!list.contains("A"));
    }

    public void testContains_2_oe() {
        assertTrue(list.add((E) "A"));
    }

    public void testContains_4_oe() {
        assertTrue(list.add((E) "B"));
    }

    public void testContains_6_oe() {
        assertTrue(list.addFirst((E) "a"));
    }

    public void testContains_11_oe() {
        assertTrue(!list.contains("A"));
    }

    public void testContainsAll_1_oe() {
        assertTrue(list.containsAll(list));
    }

    public void testContainsAll_2_oe() {
        final java.util.List<E> list2 = new java.util.LinkedList<>();
        assertTrue(list.containsAll(list2));
    }

    public void testContainsAll_3_oe() {
        final java.util.List<E> list2 = new java.util.LinkedList<>();
        list2.add((E) "A");
        assertTrue(!list.containsAll(list2));
    }

    public void testContainsAll_4_oe() {
        final java.util.List<E> list2 = new java.util.LinkedList<>();
        list2.add((E) "A");
        list.add((E) "B");
        list.add((E) "A");
        assertTrue(list.containsAll(list2));
    }

    public void testContainsAll_5_oe() {
        final java.util.List<E> list2 = new java.util.LinkedList<>();
        list2.add((E) "A");
        list.add((E) "B");
        list.add((E) "A");
        list2.add((E) "B");
        assertTrue(list.containsAll(list2));
    }

    public void testContainsAll_6_oe() {
        final java.util.List<E> list2 = new java.util.LinkedList<>();
        list2.add((E) "A");
        list.add((E) "B");
        list.add((E) "A");
        list2.add((E) "B");
        list2.add((E) "C");
        assertTrue(!list.containsAll(list2));
    }

    public void testContainsAll_7_oe() {
        final java.util.List<E> list2 = new java.util.LinkedList<>();
        list2.add((E) "A");
        list.add((E) "B");
        list.add((E) "A");
        list2.add((E) "B");
        list2.add((E) "C");
        list.add((E) "C");
        assertTrue(list.containsAll(list2));
    }

    public void testContainsAll_8_oe() {
        final java.util.List<E> list2 = new java.util.LinkedList<>();
        list2.add((E) "A");
        list.add((E) "B");
        list.add((E) "A");
        list2.add((E) "B");
        list2.add((E) "C");
        list.add((E) "C");
        list2.add((E) "C");
        assertTrue(list.containsAll(list2));
    }

    public void testContainsAll_9_oe() {
        final java.util.List<E> list2 = new java.util.LinkedList<>();
        list2.add((E) "A");
        list.add((E) "B");
        list.add((E) "A");
        list2.add((E) "B");
        list2.add((E) "C");
        list.add((E) "C");
        list2.add((E) "C");
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
        assertTrue(!it.hasPrevious());
    }

    public void testCursorNavigation_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertEquals("1", it.next());
    }

    public void testCursorNavigation_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertTrue(it.hasNext());
    }

    public void testCursorNavigation_7_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertTrue(it.hasNext());
    }

    public void testCursorNavigation_8_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertTrue(!it.hasPrevious());
    }

    public void testCursorNavigation_9_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertEquals("1", it.next());
    }

    public void testCursorNavigation_10_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertTrue(it.hasNext());
    }

    public void testCursorNavigation_13_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertTrue(it.hasNext());
    }

    public void testCursorNavigation_16_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertTrue(it.hasNext());
    }

    public void testCursorNavigation_19_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertTrue(it.hasNext());
    }

    public void testCursorNavigation_22_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertTrue(it.hasNext());
    }

    public void testCursorNavigation_25_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertTrue(it.hasNext());
    }

    public void testCursorNavigation_31_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertTrue(it.hasNext());
    }

    public void testCursorNavigation_34_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertTrue(it.hasNext());
    }

    public void testCursorNavigation_37_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertTrue(it.hasNext());
    }

    public void testCursorNavigation_40_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertTrue(it.hasNext());
    }

    public void testCursorNavigation_43_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        assertTrue(it.hasNext());
    }

    public void testCursorNavigation_44_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final CursorableLinkedList.Cursor<E> it = list.cursor();
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
        } catch (final IllegalStateException e) {
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
        } catch (final IllegalStateException e) {
        }
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
        it.add((E) "3");
        assertEquals("[1, 3]", list.toString());
    }

    public void testCursorAdd_3_oe() {
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        it.add((E) "1");
        it.add((E) "3");
        it.add((E) "5");
        assertEquals("[1, 3, 5]", list.toString());
    }

    public void testCursorAdd_4_oe() {
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        it.add((E) "1");
        it.add((E) "3");
        it.add((E) "5");
        assertEquals("5", it.previous());
    }

    public void testCursorAdd_6_oe() {
        final CursorableLinkedList.Cursor<E> it = list.cursor();
        it.add((E) "1");
        it.add((E) "3");
        it.add((E) "5");
        it.add((E) "4");
        assertEquals("4", it.previous());
    }

    public void testCursorConcurrentModification_1_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");
        list.add((E) "7");
        list.add((E) "9");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final CursorableLinkedList.Cursor<E> c2 = list.cursor();
        final Iterator<E> li = list.iterator();

        assertEquals("1", li.next());
    }

    public void testCursorNextIndexMid_1_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();
        final Iterator<E> li = list.iterator();

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

        list.remove(0);
        assertEquals(0, c1.nextIndex());
    }

    public void testCursorNextIndexFirst_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        list.remove(0);
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

        list.add(0, (E) "0");
        assertEquals(0, c1.nextIndex());
    }

    public void testCursorNextIndexAddNext_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        list.add(0, (E) "0");
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

        list.add(1, (E) "0");
        assertEquals(0, c1.nextIndex());
    }

    public void testCursorNextIndexAddAfter_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "5");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        list.add(1, (E) "0");
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

        assertEquals("B", list.remove(1));
    }

    public void testInternalState_CursorNextNextPreviousRemoveIndex1ByList_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();


        assertEquals(true, c1.nextIndexValid);
    }

    public void testInternalState_CursorNextNextPreviousRemoveIndex1ByList_8_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();


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

        assertEquals("B", list.remove(1));
    }

    public void testInternalState_CursorNextRemoveIndex1ByList_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();


        assertEquals(true, c1.nextIndexValid);
    }

    public void testInternalState_CursorNextRemoveIndex1ByList_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();


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

        assertEquals("B", list.remove(1));
    }

    public void testInternalState_CursorNextNextRemoveIndex1ByList_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();


        assertEquals(true, c1.nextIndexValid);
    }

    public void testInternalState_CursorNextNextRemoveIndex1ByList_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();


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

        assertEquals("B", list.remove(1));
    }

    public void testInternalState_CursorNextNextNextRemoveIndex1ByList_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();


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

        list.add(1, (E) "Z");


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

        list.add(1, (E) "Z");


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

        list.add(1, (E) "Z");

        assertEquals(false, c1.nextIndexValid);
    }

    public void testInternalState_CursorNextNextAddIndex1ByList_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        list.add(1, (E) "Z");


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

        c1.add((E) "Z");

        assertEquals(true, c1.nextIndexValid);
    }

    public void testInternalState_CursorNextNextPreviousAddByIterator_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        c1.add((E) "Z");

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

        c1.add((E) "Z");

        assertEquals(true, c1.nextIndexValid);
    }

    public void testInternalState_CursorNextNextAddByIterator_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        c1.add((E) "Z");

        assertEquals(false, c1.currentRemovedByAnother);
    }

    public void testInternalState_CursorNextNextAddByIterator_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        c1.add((E) "Z");

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

        list.remove(1);

        assertEquals(null, c1.current);
    }

    public void testInternalState_CursorNextNextRemoveByListSetByIterator_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");

        final CursorableLinkedList.Cursor<E> c1 = list.cursor();

        list.remove(1);

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
        assertEquals(list.hashCode(),list.hashCode());
    }

    public void testEqualsAndHashCode_3_oe() {
        list.add((E) "A");
        assertTrue(list.equals(list));
    }

    public void testEqualsAndHashCode_4_oe() {
        list.add((E) "A");
        assertEquals(list.hashCode(),list.hashCode());
    }

    public void testEqualsAndHashCode_5_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        assertTrue(!list.equals(list2));
    }

    public void testEqualsAndHashCode_6_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();
        assertTrue(!list2.equals(list));
    }

    public void testEqualsAndHashCode_7_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        assertTrue(!list.equals(list3));
    }

    public void testEqualsAndHashCode_8_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        assertTrue(!list3.equals(list));
    }

    public void testEqualsAndHashCode_9_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        assertTrue(list2.equals(list3));
    }

    public void testEqualsAndHashCode_10_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        assertTrue(list3.equals(list2));
    }

    public void testEqualsAndHashCode_11_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();
        assertEquals(list2.hashCode(),list3.hashCode());
    }

    public void testEqualsAndHashCode_12_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");
        assertTrue(list.equals(list2));
    }

    public void testEqualsAndHashCode_13_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");
        assertTrue(list2.equals(list));
    }

    public void testEqualsAndHashCode_14_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");
        assertTrue(!list2.equals(list3));
    }

    public void testEqualsAndHashCode_15_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");
        assertTrue(!list3.equals(list2));
    }

    public void testEqualsAndHashCode_16_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");
        assertTrue(list2.equals(list3));
    }

    public void testEqualsAndHashCode_17_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");
        assertTrue(list3.equals(list2));
    }

    public void testEqualsAndHashCode_18_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");
        assertEquals(list2.hashCode(),list3.hashCode());
    }

    public void testEqualsAndHashCode_19_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");
        assertTrue(list.equals(list));
    }

    public void testEqualsAndHashCode_20_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");
        assertTrue(!list.equals(list2));
    }

    public void testEqualsAndHashCode_21_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");
        assertTrue(!list2.equals(list));
    }

    public void testEqualsAndHashCode_22_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");
        assertTrue(!list.equals(list3));
    }

    public void testEqualsAndHashCode_23_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");
        assertTrue(!list3.equals(list));
    }

    public void testEqualsAndHashCode_24_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");

        list2.add((E) "B");
        list3.add((E) "B");
        assertTrue(list.equals(list));
    }

    public void testEqualsAndHashCode_25_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");

        list2.add((E) "B");
        list3.add((E) "B");
        assertTrue(list.equals(list2));
    }

    public void testEqualsAndHashCode_26_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");

        list2.add((E) "B");
        list3.add((E) "B");
        assertTrue(list2.equals(list));
    }

    public void testEqualsAndHashCode_27_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");

        list2.add((E) "B");
        list3.add((E) "B");
        assertTrue(list2.equals(list3));
    }

    public void testEqualsAndHashCode_28_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");

        list2.add((E) "B");
        list3.add((E) "B");
        assertTrue(list3.equals(list2));
    }

    public void testEqualsAndHashCode_29_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");

        list2.add((E) "B");
        list3.add((E) "B");
        assertEquals(list2.hashCode(),list3.hashCode());
    }

    public void testEqualsAndHashCode_30_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");

        list2.add((E) "B");
        list3.add((E) "B");

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        assertTrue(list.equals(list));
    }

    public void testEqualsAndHashCode_31_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");

        list2.add((E) "B");
        list3.add((E) "B");

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        assertTrue(list.equals(list2));
    }

    public void testEqualsAndHashCode_32_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");

        list2.add((E) "B");
        list3.add((E) "B");

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        assertTrue(list2.equals(list));
    }

    public void testEqualsAndHashCode_33_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");

        list2.add((E) "B");
        list3.add((E) "B");

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        assertTrue(list2.equals(list3));
    }

    public void testEqualsAndHashCode_34_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");

        list2.add((E) "B");
        list3.add((E) "B");

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        assertTrue(list3.equals(list2));
    }

    public void testEqualsAndHashCode_35_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");

        list2.add((E) "B");
        list3.add((E) "B");

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        assertEquals(list.hashCode(),list2.hashCode());
    }

    public void testEqualsAndHashCode_36_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");

        list2.add((E) "B");
        list3.add((E) "B");

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");
        assertEquals(list2.hashCode(),list3.hashCode());
    }

    public void testEqualsAndHashCode_37_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");

        list2.add((E) "B");
        list3.add((E) "B");

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");

        list.add((E) "D");
        list2.addFirst((E) "D");
        assertTrue(list.equals(list));
    }

    public void testEqualsAndHashCode_38_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");

        list2.add((E) "B");
        list3.add((E) "B");

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");

        list.add((E) "D");
        list2.addFirst((E) "D");
        assertTrue(!list.equals(list2));
    }

    public void testEqualsAndHashCode_39_oe() {
        list.add((E) "A");

        final CursorableLinkedList<E> list2 = new CursorableLinkedList<>();

        final java.util.List<E> list3 = new java.util.LinkedList<>();

        list2.add((E) "A");

        list3.add((E) "A");

        list.add((E) "B");

        list2.add((E) "B");
        list3.add((E) "B");

        list.add((E) "C");
        list2.add((E) "C");
        list3.add((E) "C");

        list.add((E) "D");
        list2.addFirst((E) "D");
        assertTrue(!list2.equals(list));
    }

    public void testGet_2_oe() {
        try {
            list.get(0);
        } catch(final IndexOutOfBoundsException e) {
        }

        assertTrue(list.add((E) "A"));
    }

    public void testGet_4_oe() {
        try {
            list.get(0);
        } catch(final IndexOutOfBoundsException e) {
        }

        assertTrue(list.add((E) "B"));
    }

    public void testIndexOf_1_oe() {
        assertEquals(-1,list.indexOf("A"));
    }

    public void testIndexOf_2_oe() {
        assertEquals(-1,list.lastIndexOf("A"));
    }

    public void testIndexOf_3_oe() {
        list.add((E) "A");
        assertEquals(0,list.indexOf("A"));
    }

    public void testIndexOf_4_oe() {
        list.add((E) "A");
        assertEquals(0,list.lastIndexOf("A"));
    }

    public void testIndexOf_5_oe() {
        list.add((E) "A");
        assertEquals(-1,list.indexOf("B"));
    }

    public void testIndexOf_6_oe() {
        list.add((E) "A");
        assertEquals(-1,list.lastIndexOf("B"));
    }

    public void testIndexOf_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        assertEquals(0,list.indexOf("A"));
    }

    public void testIndexOf_8_oe() {
        list.add((E) "A");
        list.add((E) "B");
        assertEquals(0,list.lastIndexOf("A"));
    }

    public void testIndexOf_9_oe() {
        list.add((E) "A");
        list.add((E) "B");
        assertEquals(1,list.indexOf("B"));
    }

    public void testIndexOf_10_oe() {
        list.add((E) "A");
        list.add((E) "B");
        assertEquals(1,list.lastIndexOf("B"));
    }

    public void testIndexOf_11_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.addFirst((E) "B");
        assertEquals(1,list.indexOf("A"));
    }

    public void testIndexOf_12_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.addFirst((E) "B");
        assertEquals(1,list.lastIndexOf("A"));
    }

    public void testIndexOf_13_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.addFirst((E) "B");
        assertEquals(0,list.indexOf("B"));
    }

    public void testIndexOf_14_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.addFirst((E) "B");
        assertEquals(2,list.lastIndexOf("B"));
    }

    public void testIsEmpty_1_oe() {
        assertTrue(list.isEmpty());
    }

    public void testIsEmpty_2_oe() {
        list.add((E) "element");
        assertTrue(!list.isEmpty());
    }

    public void testIsEmpty_3_oe() {
        list.add((E) "element");
        list.remove("element");
        assertTrue(list.isEmpty());
    }

    public void testIsEmpty_4_oe() {
        list.add((E) "element");
        list.remove("element");
        list.add((E) "element");
        assertTrue(!list.isEmpty());
    }

    public void testIsEmpty_5_oe() {
        list.add((E) "element");
        list.remove("element");
        list.add((E) "element");
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
        assertEquals("1", it.next());
    }

    public void testIterator_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        assertTrue(it.hasNext());
    }

    public void testIterator_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        assertTrue(it.hasNext());
    }

    public void testIterator_7_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        assertTrue(it.hasNext());
    }

    public void testIterator_9_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();
        assertTrue(it.hasNext());
    }

    public void testIterator_12_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        Iterator<E> it = list.iterator();

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

        it = list.iterator();
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
        assertTrue(!it.hasPrevious());
    }

    public void testListIteratorNavigation_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertEquals(-1, it.previousIndex());
    }

    public void testListIteratorNavigation_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertEquals(0, it.nextIndex());
    }

    public void testListIteratorNavigation_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertEquals("1", it.next());
    }

    public void testListIteratorNavigation_6_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertTrue(it.hasNext());
    }

    public void testListIteratorNavigation_11_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertTrue(it.hasNext());
    }

    public void testListIteratorNavigation_12_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertTrue(!it.hasPrevious());
    }

    public void testListIteratorNavigation_13_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertEquals(-1, it.previousIndex());
    }

    public void testListIteratorNavigation_14_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertEquals(0, it.nextIndex());
    }

    public void testListIteratorNavigation_15_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertEquals("1", it.next());
    }

    public void testListIteratorNavigation_16_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertTrue(it.hasNext());
    }

    public void testListIteratorNavigation_21_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertTrue(it.hasNext());
    }

    public void testListIteratorNavigation_26_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertTrue(it.hasNext());
    }

    public void testListIteratorNavigation_31_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertTrue(it.hasNext());
    }

    public void testListIteratorNavigation_36_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertTrue(it.hasNext());
    }

    public void testListIteratorNavigation_41_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertTrue(it.hasNext());
    }

    public void testListIteratorNavigation_51_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertTrue(it.hasNext());
    }

    public void testListIteratorNavigation_56_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertTrue(it.hasNext());
    }

    public void testListIteratorNavigation_61_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertTrue(it.hasNext());
    }

    public void testListIteratorNavigation_66_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertTrue(it.hasNext());
    }

    public void testListIteratorNavigation_71_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertTrue(it.hasNext());
    }

    public void testListIteratorNavigation_72_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertTrue(!it.hasPrevious());
    }

    public void testListIteratorNavigation_73_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
        assertEquals(-1, it.previousIndex());
    }

    public void testListIteratorNavigation_74_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        final ListIterator<E> it = list.listIterator();
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
        }
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
        it.add((E) "3");
        assertEquals("[1, 3]", list.toString());
    }

    public void testListIteratorAdd_3_oe() {
        final ListIterator<E> it = list.listIterator();
        it.add((E) "1");
        it.add((E) "3");
        it.add((E) "5");
        assertEquals("[1, 3, 5]", list.toString());
    }

    public void testListIteratorAdd_4_oe() {
        final ListIterator<E> it = list.listIterator();
        it.add((E) "1");
        it.add((E) "3");
        it.add((E) "5");
        assertEquals("5", it.previous());
    }

    public void testListIteratorAdd_6_oe() {
        final ListIterator<E> it = list.listIterator();
        it.add((E) "1");
        it.add((E) "3");
        it.add((E) "5");
        it.add((E) "4");
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
        list.set(0, (E) "A");
        assertEquals("[A, 2, 3, 4, 5]", list.toString());
    }

    public void testSet_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.set(0, (E) "A");
        list.set(1, (E) "B");
        assertEquals("[A, B, 3, 4, 5]", list.toString());
    }

    public void testSet_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.set(0, (E) "A");
        list.set(1, (E) "B");
        list.set(2, (E) "C");
        assertEquals("[A, B, C, 4, 5]", list.toString());
    }

    public void testSet_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.set(0, (E) "A");
        list.set(1, (E) "B");
        list.set(2, (E) "C");
        list.set(3, (E) "D");
        assertEquals("[A, B, C, D, 5]", list.toString());
    }

    public void testSet_6_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");
        list.set(0, (E) "A");
        list.set(1, (E) "B");
        list.set(2, (E) "C");
        list.set(3, (E) "D");
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

        assertEquals("[A, B, C, D, E]", list.subList(0, 5).toString());
    }

    public void testSubList_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        assertEquals("[B, C, D, E]", list.subList(1, 5).toString());
    }

    public void testSubList_4_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        assertEquals("[C, D, E]", list.subList(2, 5).toString());
    }

    public void testSubList_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        assertEquals("[D, E]", list.subList(3, 5).toString());
    }

    public void testSubList_6_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        assertEquals("[E]", list.subList(4, 5).toString());
    }

    public void testSubList_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

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
        sublist.add((E) "G");
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
        sublist.add((E) "b");
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
        sublist.add((E) "b");
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
        assertEquals("[A, B, C, D, E]", list.toString());
    }

    public void testSubListRemove_3_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 4);
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
        sublist.remove("C");
        assertEquals("[A, B, D, E]", list.toString());
    }

    public void testSubListRemove_5_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 4);
        sublist.remove("C");
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
        sublist.remove("C");
        sublist.remove(1);
        assertEquals("[A, B, E]", list.toString());
    }

    public void testSubListRemove_7_oe() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 4);
        sublist.remove("C");
        sublist.remove(1);
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
        sublist.remove("C");
        sublist.remove(1);
        sublist.clear();
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
        assertEquals("2", elts[1]);
    }

    public void testToArray_3_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        assertEquals("3", elts[2]);
    }

    public void testToArray_4_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        assertEquals("4", elts[3]);
    }

    public void testToArray_5_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        assertEquals("5", elts[4]);
    }

    public void testToArray_6_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();
        assertEquals(5, elts.length);
    }

    public void testToArray_7_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();

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

        final String[] elts2 = list.toArray(new String[0]);
        assertEquals("2", elts2[1]);
    }

    public void testToArray_9_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();

        final String[] elts2 = list.toArray(new String[0]);
        assertEquals("3", elts2[2]);
    }

    public void testToArray_10_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();

        final String[] elts2 = list.toArray(new String[0]);
        assertEquals("4", elts2[3]);
    }

    public void testToArray_11_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();

        final String[] elts2 = list.toArray(new String[0]);
        assertEquals("5", elts2[4]);
    }

    public void testToArray_12_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();

        final String[] elts2 = list.toArray(new String[0]);
        assertEquals(5, elts2.length);
    }

    public void testToArray_13_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();

        final String[] elts2 = list.toArray(new String[0]);

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

        final String[] elts2 = list.toArray(new String[0]);

        final String[] elts3 = new String[5];
        assertEquals(5, elts3.length);
    }

    public void testToArray_20_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();

        final String[] elts2 = list.toArray(new String[0]);

        final String[] elts3 = new String[5];

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

        final String[] elts2 = list.toArray(new String[0]);

        final String[] elts3 = new String[5];

        final String[] elts4 = new String[3];
        final String[] elts4b = list.toArray(elts4);
        assertEquals("1", elts4b[0]);
    }

    public void testToArray_22_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();

        final String[] elts2 = list.toArray(new String[0]);

        final String[] elts3 = new String[5];

        final String[] elts4 = new String[3];
        final String[] elts4b = list.toArray(elts4);
        assertEquals("2", elts4b[1]);
    }

    public void testToArray_23_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();

        final String[] elts2 = list.toArray(new String[0]);

        final String[] elts3 = new String[5];

        final String[] elts4 = new String[3];
        final String[] elts4b = list.toArray(elts4);
        assertEquals("3", elts4b[2]);
    }

    public void testToArray_24_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();

        final String[] elts2 = list.toArray(new String[0]);

        final String[] elts3 = new String[5];

        final String[] elts4 = new String[3];
        final String[] elts4b = list.toArray(elts4);
        assertEquals("4", elts4b[3]);
    }

    public void testToArray_25_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();

        final String[] elts2 = list.toArray(new String[0]);

        final String[] elts3 = new String[5];

        final String[] elts4 = new String[3];
        final String[] elts4b = list.toArray(elts4);
        assertEquals("5", elts4b[4]);
    }

    public void testToArray_26_oe() {
        list.add((E) "1");
        list.add((E) "2");
        list.add((E) "3");
        list.add((E) "4");
        list.add((E) "5");

        final Object[] elts = list.toArray();

        final String[] elts2 = list.toArray(new String[0]);

        final String[] elts3 = new String[5];

        final String[] elts4 = new String[3];
        final String[] elts4b = list.toArray(elts4);
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

        assertTrue(list.equals(list2));
    }

    public void testLongSerialization_1_oe() throws Exception {
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

        assertTrue(list2.equals(list));
    }

    public void testLongSerialization_3_oe() throws Exception {
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

        assertTrue(list.equals(list2));
    }

}
