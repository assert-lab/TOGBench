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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 * JUnit tests.
 *
 * @since 3.0
 */
public class SetUniqueListTest_OE25Dev<E> extends AbstractListTest<E> {

    class SetUniqueList307 extends SetUniqueList<E> {
        /**
         * Generated serial version ID.
         */
        private static final long serialVersionUID = 1415013031022962158L;

        public SetUniqueList307(final List<E> list, final Set<E> set) {
            super(list, set);
        }
    }

    boolean extraVerify = true;

    public SetUniqueListTest_OE25Dev(final String testName) {
        super(testName);
    }

    //-----------------------------------------------------------------------
    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] getFullNonNullElements() {
        // override to avoid duplicate "One"
        return (E[]) new Object[] {
                new String(""),
                new String("One"),
                Integer.valueOf(2),
                "Three",
                Integer.valueOf(4),
                new Double(5),
                new Float(6),
                "Seven",
                "Eight",
                new String("Nine"),
                Integer.valueOf(10),
                new Short((short)11),
                new Long(12),
                "Thirteen",
                "14",
                "15",
                new Byte((byte)16)
        };
    }

    //-----------------------------------------------------------------------
    @Override
    public List<E> makeObject() {
        return new SetUniqueList<>(new ArrayList<E>(), new HashSet<E>());
    }

    @Override
    public void testCollectionIteratorRemove() {
        try {
            extraVerify = false;
            super.testCollectionIteratorRemove();
        } finally {
            extraVerify = true;
        }
    }

    //-----------------------------------------------------------------------

    @Override
    public void testListIteratorAdd() {
        // override to cope with Set behaviour
        resetEmpty();
        final List<E> list1 = getCollection();
        final List<E> list2 = getConfirmed();

        final E[] elements = getOtherElements();  // changed here
        ListIterator<E> iter1 = list1.listIterator();
        ListIterator<E> iter2 = list2.listIterator();

        for (final E element : elements) {
            iter1.add(element);
            iter2.add(element);
            super.verify();  // changed here
        }

        resetFull();
        iter1 = getCollection().listIterator();
        iter2 = getConfirmed().listIterator();
        for (final E element : elements) {
            iter1.next();
            iter2.next();
            iter1.add(element);
            iter2.add(element);
            super.verify();  // changed here
        }
    }

    //-----------------------------------------------------------------------
    @Override
    public void testListIteratorSet() {
        // override to block
        resetFull();
        final ListIterator<E> it = getCollection().listIterator();
        it.next();
        try {
            it.set(null);
            fail();
        } catch (final UnsupportedOperationException ex) {}
    }

    public void testSubListIsUnmodifiable() {
        resetFull();
        final List<E> subList = getCollection().subList(1, 3);
        try {
            subList.remove(0);
            fail("subList should be unmodifiable");
        } catch (final UnsupportedOperationException e) {
            // expected
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void verify() {
        super.verify();

        if (extraVerify) {
            final int size = getCollection().size();
            getCollection().add((E) new Long(1000));
            assertEquals(size + 1, getCollection().size());

            getCollection().add((E) new Long(1000));
            assertEquals(size + 1, getCollection().size());
            assertEquals(new Long(1000), getCollection().get(size));

            getCollection().remove(size);
        }
    }

//    public void testCreate() throws Exception {
//        resetEmpty();
//        writeExternalFormToDisk((java.io.Serializable) getCollection(), "src/test/resources/data/test/SetUniqueList.emptyCollection.version4.obj");
//        resetFull();
//        writeExternalFormToDisk((java.io.Serializable) getCollection(), "src/test/resources/data/test/SetUniqueList.fullCollection.version4.obj");
//    }

public void testAdd_1_oe() {
        final SetUniqueList<E> lset = new SetUniqueList<>(new ArrayList<E>(), new HashSet<E>());

        // Duplicate element
        final E obj = (E) Integer.valueOf(1);
        lset.add(obj);
        lset.add(obj);
        assertEquals("Duplicate element was added.", 1, lset.size());
    }

public void testAdd_2_oe() {
        final SetUniqueList<E> lset = new SetUniqueList<>(new ArrayList<E>(), new HashSet<E>());

        // Duplicate element
        final E obj = (E) Integer.valueOf(1);
        lset.add(obj);
        lset.add(obj);
        // removed other assertion

        // Unique element
        lset.add((E) Integer.valueOf(2));
        assertEquals("Unique element was not added.", 2, lset.size());
    }

public void testAddAll_1_oe() {
        final SetUniqueList<E> lset = new SetUniqueList<>(new ArrayList<E>(), new HashSet<E>());

        lset.addAll(
            Arrays.asList((E[]) new Integer[] { Integer.valueOf(1), Integer.valueOf(1)}));

        assertEquals("Duplicate element was added.", 1, lset.size());
    }

public void testCollectionAddAll_1_oe() {
        // override for set behaviour
        resetEmpty();
        E[] elements = getFullElements();
        boolean r = getCollection().addAll(Arrays.asList(elements));
        getConfirmed().addAll(Arrays.asList(elements));
        verify();
        assertTrue("Empty collection should change after addAll", r);
    }

public void testCollectionAddAll_2_oe() {
        // override for set behaviour
        resetEmpty();
        E[] elements = getFullElements();
        boolean r = getCollection().addAll(Arrays.asList(elements));
        getConfirmed().addAll(Arrays.asList(elements));
        verify();
        // removed other assertion
        for (final E element : elements) {
            assertTrue("Collection should contain added element",getCollection().contains(element));
    }
    }

public void testCollectionAddAll_3_oe() {
        // override for set behaviour
        resetEmpty();
        E[] elements = getFullElements();
        boolean r = getCollection().addAll(Arrays.asList(elements));
        getConfirmed().addAll(Arrays.asList(elements));
        verify();
        // removed other assertion
        for (final E element : elements) {
            // removed other assertion
        }

        resetFull();
        final int size = getCollection().size();
        elements = getOtherElements();
        r = getCollection().addAll(Arrays.asList(elements));
        getConfirmed().addAll(Arrays.asList(elements));
        verify();
        assertTrue("Full collection should change after addAll", r);
    }

public void testCollectionAddAll_4_oe() {
        // override for set behaviour
        resetEmpty();
        E[] elements = getFullElements();
        boolean r = getCollection().addAll(Arrays.asList(elements));
        getConfirmed().addAll(Arrays.asList(elements));
        verify();
        // removed other assertion
        for (final E element : elements) {
            // removed other assertion
        }

        resetFull();
        final int size = getCollection().size();
        elements = getOtherElements();
        r = getCollection().addAll(Arrays.asList(elements));
        getConfirmed().addAll(Arrays.asList(elements));
        verify();
        // removed other assertion
        for (int i = 0; i < elements.length; i++) {
            assertTrue("Full collection should contain added element " + i,getCollection().contains(elements[i]));
    }
    }

public void testCollectionAddAll_5_oe() {
        // override for set behaviour
        resetEmpty();
        E[] elements = getFullElements();
        boolean r = getCollection().addAll(Arrays.asList(elements));
        getConfirmed().addAll(Arrays.asList(elements));
        verify();
        // removed other assertion
        for (final E element : elements) {
            // removed other assertion
        }

        resetFull();
        final int size = getCollection().size();
        elements = getOtherElements();
        r = getCollection().addAll(Arrays.asList(elements));
        getConfirmed().addAll(Arrays.asList(elements));
        verify();
        // removed other assertion
        for (int i = 0; i < elements.length; i++) {
            // removed other assertion
        }
        assertEquals("Size should increase after addAll",size + elements.length,getCollection().size());
    }

public void testCollections304_1_oe() {
        final List<String> list = new LinkedList<>();
        final SetUniqueList<String> decoratedList = SetUniqueList.setUniqueList(list);
        final String s1 = "Apple";
        final String s2 = "Lemon";
        final String s3 = "Orange";
        final String s4 = "Strawberry";

        decoratedList.add(s1);
        decoratedList.add(s2);
        decoratedList.add(s3);
        assertEquals(3, decoratedList.size());
    }

public void testCollections304_2_oe() {
        final List<String> list = new LinkedList<>();
        final SetUniqueList<String> decoratedList = SetUniqueList.setUniqueList(list);
        final String s1 = "Apple";
        final String s2 = "Lemon";
        final String s3 = "Orange";
        final String s4 = "Strawberry";

        decoratedList.add(s1);
        decoratedList.add(s2);
        decoratedList.add(s3);
        // removed other assertion

        decoratedList.set(1, s4);
        assertEquals(3, decoratedList.size());
    }

public void testCollections304_3_oe() {
        final List<String> list = new LinkedList<>();
        final SetUniqueList<String> decoratedList = SetUniqueList.setUniqueList(list);
        final String s1 = "Apple";
        final String s2 = "Lemon";
        final String s3 = "Orange";
        final String s4 = "Strawberry";

        decoratedList.add(s1);
        decoratedList.add(s2);
        decoratedList.add(s3);
        // removed other assertion

        decoratedList.set(1, s4);
        // removed other assertion

        decoratedList.add(1, s4);
        assertEquals(3, decoratedList.size());
    }

public void testCollections304_4_oe() {
        final List<String> list = new LinkedList<>();
        final SetUniqueList<String> decoratedList = SetUniqueList.setUniqueList(list);
        final String s1 = "Apple";
        final String s2 = "Lemon";
        final String s3 = "Orange";
        final String s4 = "Strawberry";

        decoratedList.add(s1);
        decoratedList.add(s2);
        decoratedList.add(s3);
        // removed other assertion

        decoratedList.set(1, s4);
        // removed other assertion

        decoratedList.add(1, s4);
        // removed other assertion

        decoratedList.add(1, s2);
        assertEquals(4, decoratedList.size());
    }

public void testCollections307_1_oe() {
        List<E> list = new ArrayList<>();
        List<E> uniqueList = SetUniqueList.setUniqueList(list);

        final String hello = "Hello";
        final String world = "World";
        uniqueList.add((E) hello);
        uniqueList.add((E) world);

        List<E> subList = list.subList(0, 0);
        List<E> subUniqueList = uniqueList.subList(0, 0);

        assertFalse(subList.contains(world));// passes;
    }

public void testCollections307_2_oe() {
        List<E> list = new ArrayList<>();
        List<E> uniqueList = SetUniqueList.setUniqueList(list);

        final String hello = "Hello";
        final String world = "World";
        uniqueList.add((E) hello);
        uniqueList.add((E) world);

        List<E> subList = list.subList(0, 0);
        List<E> subUniqueList = uniqueList.subList(0, 0);

        // removed other assertion
        assertFalse(subUniqueList.contains(world));// fails;
    }

public void testCollections307_3_oe() {
        List<E> list = new ArrayList<>();
        List<E> uniqueList = SetUniqueList.setUniqueList(list);

        final String hello = "Hello";
        final String world = "World";
        uniqueList.add((E) hello);
        uniqueList.add((E) world);

        List<E> subList = list.subList(0, 0);
        List<E> subUniqueList = uniqueList.subList(0, 0);

        // removed other assertion
        // removed other assertion
        List<E> worldList = new ArrayList<>();
        worldList.add((E) world);
        assertFalse(subList.contains("World"));// passes;
    }

public void testCollections307_4_oe() {
        List<E> list = new ArrayList<>();
        List<E> uniqueList = SetUniqueList.setUniqueList(list);

        final String hello = "Hello";
        final String world = "World";
        uniqueList.add((E) hello);
        uniqueList.add((E) world);

        List<E> subList = list.subList(0, 0);
        List<E> subUniqueList = uniqueList.subList(0, 0);

        // removed other assertion
        // removed other assertion
        List<E> worldList = new ArrayList<>();
        worldList.add((E) world);
        // removed other assertion
        assertFalse(subUniqueList.contains("World"));// fails;
    }

public void testCollections307_5_oe() {
        List<E> list = new ArrayList<>();
        List<E> uniqueList = SetUniqueList.setUniqueList(list);

        final String hello = "Hello";
        final String world = "World";
        uniqueList.add((E) hello);
        uniqueList.add((E) world);

        List<E> subList = list.subList(0, 0);
        List<E> subUniqueList = uniqueList.subList(0, 0);

        // removed other assertion
        // removed other assertion
        List<E> worldList = new ArrayList<>();
        worldList.add((E) world);
        // removed other assertion
        // removed other assertion
        list = new ArrayList<>();
        uniqueList = new SetUniqueList307(list, new java.util.TreeSet<E>());

        uniqueList.add((E) hello);
        uniqueList.add((E) world);

        subList = list.subList(0, 0);
        subUniqueList = uniqueList.subList(0, 0);

        assertFalse(subList.contains(world));// passes;
    }

public void testCollections307_6_oe() {
        List<E> list = new ArrayList<>();
        List<E> uniqueList = SetUniqueList.setUniqueList(list);

        final String hello = "Hello";
        final String world = "World";
        uniqueList.add((E) hello);
        uniqueList.add((E) world);

        List<E> subList = list.subList(0, 0);
        List<E> subUniqueList = uniqueList.subList(0, 0);

        // removed other assertion
        // removed other assertion
        List<E> worldList = new ArrayList<>();
        worldList.add((E) world);
        // removed other assertion
        // removed other assertion
        list = new ArrayList<>();
        uniqueList = new SetUniqueList307(list, new java.util.TreeSet<E>());

        uniqueList.add((E) hello);
        uniqueList.add((E) world);

        subList = list.subList(0, 0);
        subUniqueList = uniqueList.subList(0, 0);

        // removed other assertion
        assertFalse(subUniqueList.contains(world));// fails;
    }

public void testCollections307_7_oe() {
        List<E> list = new ArrayList<>();
        List<E> uniqueList = SetUniqueList.setUniqueList(list);

        final String hello = "Hello";
        final String world = "World";
        uniqueList.add((E) hello);
        uniqueList.add((E) world);

        List<E> subList = list.subList(0, 0);
        List<E> subUniqueList = uniqueList.subList(0, 0);

        // removed other assertion
        // removed other assertion
        List<E> worldList = new ArrayList<>();
        worldList.add((E) world);
        // removed other assertion
        // removed other assertion
        list = new ArrayList<>();
        uniqueList = new SetUniqueList307(list, new java.util.TreeSet<E>());

        uniqueList.add((E) hello);
        uniqueList.add((E) world);

        subList = list.subList(0, 0);
        subUniqueList = uniqueList.subList(0, 0);

        // removed other assertion
        // removed other assertion
        worldList = new ArrayList<>();
        worldList.add((E) world);
        assertFalse(subList.contains("World")); // passes;
    }

public void testCollections307_8_oe() {
        List<E> list = new ArrayList<>();
        List<E> uniqueList = SetUniqueList.setUniqueList(list);

        final String hello = "Hello";
        final String world = "World";
        uniqueList.add((E) hello);
        uniqueList.add((E) world);

        List<E> subList = list.subList(0, 0);
        List<E> subUniqueList = uniqueList.subList(0, 0);

        // removed other assertion
        // removed other assertion
        List<E> worldList = new ArrayList<>();
        worldList.add((E) world);
        // removed other assertion
        // removed other assertion
        list = new ArrayList<>();
        uniqueList = new SetUniqueList307(list, new java.util.TreeSet<E>());

        uniqueList.add((E) hello);
        uniqueList.add((E) world);

        subList = list.subList(0, 0);
        subUniqueList = uniqueList.subList(0, 0);

        // removed other assertion
        // removed other assertion
        worldList = new ArrayList<>();
        worldList.add((E) world);
        // removed other assertion
        assertFalse(subUniqueList.contains("World")); // fails;
    }

public void testCollections701_1_oe() {
        final SetUniqueList<Object> uniqueList = new SetUniqueList<>(new ArrayList<>(), new HashSet<>());
        final Integer obj1 = Integer.valueOf(1);
        final Integer obj2 = Integer.valueOf(2);
        uniqueList.add(obj1);
        uniqueList.add(obj2);
        assertEquals(2, uniqueList.size());
    }

public void testCollections701_2_oe() {
        final SetUniqueList<Object> uniqueList = new SetUniqueList<>(new ArrayList<>(), new HashSet<>());
        final Integer obj1 = Integer.valueOf(1);
        final Integer obj2 = Integer.valueOf(2);
        uniqueList.add(obj1);
        uniqueList.add(obj2);
        // removed other assertion
        uniqueList.add(uniqueList);
        assertEquals(3, uniqueList.size());
    }

public void testCollections701_3_oe() {
        final SetUniqueList<Object> uniqueList = new SetUniqueList<>(new ArrayList<>(), new HashSet<>());
        final Integer obj1 = Integer.valueOf(1);
        final Integer obj2 = Integer.valueOf(2);
        uniqueList.add(obj1);
        uniqueList.add(obj2);
        // removed other assertion
        uniqueList.add(uniqueList);
        // removed other assertion
        final List<Object> list = new LinkedList<>();
        final SetUniqueList<Object> decoratedList = SetUniqueList.setUniqueList(list);
        final String s1 = "Apple";
        final String s2 = "Lemon";
        final String s3 = "Orange";
        final String s4 = "Strawberry";
        decoratedList.add(s1);
        decoratedList.add(s2);
        decoratedList.add(s3);
        assertEquals(3, decoratedList.size());
    }

public void testCollections701_4_oe() {
        final SetUniqueList<Object> uniqueList = new SetUniqueList<>(new ArrayList<>(), new HashSet<>());
        final Integer obj1 = Integer.valueOf(1);
        final Integer obj2 = Integer.valueOf(2);
        uniqueList.add(obj1);
        uniqueList.add(obj2);
        // removed other assertion
        uniqueList.add(uniqueList);
        // removed other assertion
        final List<Object> list = new LinkedList<>();
        final SetUniqueList<Object> decoratedList = SetUniqueList.setUniqueList(list);
        final String s1 = "Apple";
        final String s2 = "Lemon";
        final String s3 = "Orange";
        final String s4 = "Strawberry";
        decoratedList.add(s1);
        decoratedList.add(s2);
        decoratedList.add(s3);
        // removed other assertion
        decoratedList.set(1, s4);
        assertEquals(3, decoratedList.size());
    }

public void testCollections701_5_oe() {
        final SetUniqueList<Object> uniqueList = new SetUniqueList<>(new ArrayList<>(), new HashSet<>());
        final Integer obj1 = Integer.valueOf(1);
        final Integer obj2 = Integer.valueOf(2);
        uniqueList.add(obj1);
        uniqueList.add(obj2);
        // removed other assertion
        uniqueList.add(uniqueList);
        // removed other assertion
        final List<Object> list = new LinkedList<>();
        final SetUniqueList<Object> decoratedList = SetUniqueList.setUniqueList(list);
        final String s1 = "Apple";
        final String s2 = "Lemon";
        final String s3 = "Orange";
        final String s4 = "Strawberry";
        decoratedList.add(s1);
        decoratedList.add(s2);
        decoratedList.add(s3);
        // removed other assertion
        decoratedList.set(1, s4);
        // removed other assertion
        decoratedList.add(decoratedList);
        assertEquals(4, decoratedList.size());
    }

public void testFactory_1_oe() {
        final Integer[] array = new Integer[] { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(1) };
        final ArrayList<Integer> list = new ArrayList<>(Arrays.asList(array));
        final SetUniqueList<Integer> lset = SetUniqueList.setUniqueList(list);

        assertEquals("Duplicate element was added.", 2, lset.size());
    }

public void testFactory_2_oe() {
        final Integer[] array = new Integer[] { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(1) };
        final ArrayList<Integer> list = new ArrayList<>(Arrays.asList(array));
        final SetUniqueList<Integer> lset = SetUniqueList.setUniqueList(list);

        // removed other assertion
        assertEquals(Integer.valueOf(1), lset.get(0));
    }

public void testFactory_3_oe() {
        final Integer[] array = new Integer[] { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(1) };
        final ArrayList<Integer> list = new ArrayList<>(Arrays.asList(array));
        final SetUniqueList<Integer> lset = SetUniqueList.setUniqueList(list);

        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(2), lset.get(1));
    }

public void testFactory_4_oe() {
        final Integer[] array = new Integer[] { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(1) };
        final ArrayList<Integer> list = new ArrayList<>(Arrays.asList(array));
        final SetUniqueList<Integer> lset = SetUniqueList.setUniqueList(list);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(1), list.get(0));
    }

public void testFactory_5_oe() {
        final Integer[] array = new Integer[] { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(1) };
        final ArrayList<Integer> list = new ArrayList<>(Arrays.asList(array));
        final SetUniqueList<Integer> lset = SetUniqueList.setUniqueList(list);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(2), list.get(1));
    }

public void testIntCollectionAddAll_1_oe() {
      // make a SetUniqueList with one element
      final List<Integer> list = new SetUniqueList<>(new ArrayList<Integer>(), new HashSet<Integer>());
      final Integer existingElement = Integer.valueOf(1);
      list.add(existingElement);

      // add two new unique elements at index 0
      final Integer firstNewElement = Integer.valueOf(2);
      final Integer secondNewElement = Integer.valueOf(3);
      Collection<Integer> collection = Arrays.asList(firstNewElement, secondNewElement);
      list.addAll(0, collection);
      assertEquals("Unique elements should be added.", 3, list.size());
    }

public void testIntCollectionAddAll_2_oe() {
      // make a SetUniqueList with one element
      final List<Integer> list = new SetUniqueList<>(new ArrayList<Integer>(), new HashSet<Integer>());
      final Integer existingElement = Integer.valueOf(1);
      list.add(existingElement);

      // add two new unique elements at index 0
      final Integer firstNewElement = Integer.valueOf(2);
      final Integer secondNewElement = Integer.valueOf(3);
      Collection<Integer> collection = Arrays.asList(firstNewElement, secondNewElement);
      list.addAll(0, collection);
      // removed other assertion
      assertEquals("First new element should be at index 0", firstNewElement, list.get(0));
    }

public void testIntCollectionAddAll_3_oe() {
      // make a SetUniqueList with one element
      final List<Integer> list = new SetUniqueList<>(new ArrayList<Integer>(), new HashSet<Integer>());
      final Integer existingElement = Integer.valueOf(1);
      list.add(existingElement);

      // add two new unique elements at index 0
      final Integer firstNewElement = Integer.valueOf(2);
      final Integer secondNewElement = Integer.valueOf(3);
      Collection<Integer> collection = Arrays.asList(firstNewElement, secondNewElement);
      list.addAll(0, collection);
      // removed other assertion
      // removed other assertion
      assertEquals("Second new element should be at index 1", secondNewElement, list.get(1));
    }

public void testIntCollectionAddAll_4_oe() {
      // make a SetUniqueList with one element
      final List<Integer> list = new SetUniqueList<>(new ArrayList<Integer>(), new HashSet<Integer>());
      final Integer existingElement = Integer.valueOf(1);
      list.add(existingElement);

      // add two new unique elements at index 0
      final Integer firstNewElement = Integer.valueOf(2);
      final Integer secondNewElement = Integer.valueOf(3);
      Collection<Integer> collection = Arrays.asList(firstNewElement, secondNewElement);
      list.addAll(0, collection);
      // removed other assertion
      // removed other assertion
      // removed other assertion
      assertEquals("Existing element should shift to index 2", existingElement, list.get(2));
    }

public void testIntCollectionAddAll_5_oe() {
      // make a SetUniqueList with one element
      final List<Integer> list = new SetUniqueList<>(new ArrayList<Integer>(), new HashSet<Integer>());
      final Integer existingElement = Integer.valueOf(1);
      list.add(existingElement);

      // add two new unique elements at index 0
      final Integer firstNewElement = Integer.valueOf(2);
      final Integer secondNewElement = Integer.valueOf(3);
      Collection<Integer> collection = Arrays.asList(firstNewElement, secondNewElement);
      list.addAll(0, collection);
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // add a duplicate element and a unique element at index 0
      final Integer thirdNewElement = Integer.valueOf(4);
      collection = Arrays.asList(existingElement, thirdNewElement);
      list.addAll(0, collection);
      assertEquals("Duplicate element should not be added,unique element should be added.",4,list.size());
    }

public void testIntCollectionAddAll_6_oe() {
      // make a SetUniqueList with one element
      final List<Integer> list = new SetUniqueList<>(new ArrayList<Integer>(), new HashSet<Integer>());
      final Integer existingElement = Integer.valueOf(1);
      list.add(existingElement);

      // add two new unique elements at index 0
      final Integer firstNewElement = Integer.valueOf(2);
      final Integer secondNewElement = Integer.valueOf(3);
      Collection<Integer> collection = Arrays.asList(firstNewElement, secondNewElement);
      list.addAll(0, collection);
      // removed other assertion
      // removed other assertion
      // removed other assertion
      // removed other assertion

      // add a duplicate element and a unique element at index 0
      final Integer thirdNewElement = Integer.valueOf(4);
      collection = Arrays.asList(existingElement, thirdNewElement);
      list.addAll(0, collection);
      // removed other assertion
      assertEquals("Third new element should be at index 0", thirdNewElement, list.get(0));
    }

public void testListIterator_1_oe() {
        final SetUniqueList<E> lset = new SetUniqueList<>(new ArrayList<E>(), new HashSet<E>());

        final E obj1 = (E) Integer.valueOf(1);
        final E obj2 = (E) Integer.valueOf(2);
        lset.add(obj1);
        lset.add(obj2);

        // Attempts to add a duplicate object
        for (final ListIterator<E> it = lset.listIterator(); it.hasNext();) {
            it.next();

            if (!it.hasNext()) {
                it.add(obj1);
                break;
            }
        }

        assertEquals("Duplicate element was added", 2, lset.size());
    }

public void testListSetByIndex_1_oe() {
        // override for set behaviour
        resetFull();
        final int size = getCollection().size();
        getCollection().set(0, (E) new Long(1000));
        assertEquals(size, getCollection().size());
    }

public void testListSetByIndex_2_oe() {
        // override for set behaviour
        resetFull();
        final int size = getCollection().size();
        getCollection().set(0, (E) new Long(1000));
        // removed other assertion

        getCollection().set(2, (E) new Long(1000));
        assertEquals(size - 1, getCollection().size());
    }

public void testListSetByIndex_3_oe() {
        // override for set behaviour
        resetFull();
        final int size = getCollection().size();
        getCollection().set(0, (E) new Long(1000));
        // removed other assertion

        getCollection().set(2, (E) new Long(1000));
        // removed other assertion
        assertEquals(new Long(1000), getCollection().get(1));  // set into 2, but shifted down to 1;
    }

public void testRetainAll_1_oe() {
        final List<E> list = new ArrayList<>(10);
        final SetUniqueList<E> uniqueList = SetUniqueList.setUniqueList(list);
        for (int i = 0; i < 10; ++i) {
            uniqueList.add((E)Integer.valueOf(i));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E)Integer.valueOf(i * 2));
        }

        assertTrue(uniqueList.retainAll(retained));
    }

public void testRetainAll_2_oe() {
        final List<E> list = new ArrayList<>(10);
        final SetUniqueList<E> uniqueList = SetUniqueList.setUniqueList(list);
        for (int i = 0; i < 10; ++i) {
            uniqueList.add((E)Integer.valueOf(i));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E)Integer.valueOf(i * 2));
        }

        // removed other assertion
        assertEquals(5, uniqueList.size());
    }

public void testRetainAll_3_oe() {
        final List<E> list = new ArrayList<>(10);
        final SetUniqueList<E> uniqueList = SetUniqueList.setUniqueList(list);
        for (int i = 0; i < 10; ++i) {
            uniqueList.add((E)Integer.valueOf(i));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E)Integer.valueOf(i * 2));
        }

        // removed other assertion
        // removed other assertion
        assertTrue(uniqueList.contains(Integer.valueOf(0)));
    }

public void testRetainAll_4_oe() {
        final List<E> list = new ArrayList<>(10);
        final SetUniqueList<E> uniqueList = SetUniqueList.setUniqueList(list);
        for (int i = 0; i < 10; ++i) {
            uniqueList.add((E)Integer.valueOf(i));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E)Integer.valueOf(i * 2));
        }

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(uniqueList.contains(Integer.valueOf(2)));
    }

public void testRetainAll_5_oe() {
        final List<E> list = new ArrayList<>(10);
        final SetUniqueList<E> uniqueList = SetUniqueList.setUniqueList(list);
        for (int i = 0; i < 10; ++i) {
            uniqueList.add((E)Integer.valueOf(i));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E)Integer.valueOf(i * 2));
        }

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(uniqueList.contains(Integer.valueOf(4)));
    }

public void testRetainAll_6_oe() {
        final List<E> list = new ArrayList<>(10);
        final SetUniqueList<E> uniqueList = SetUniqueList.setUniqueList(list);
        for (int i = 0; i < 10; ++i) {
            uniqueList.add((E)Integer.valueOf(i));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E)Integer.valueOf(i * 2));
        }

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(uniqueList.contains(Integer.valueOf(6)));
    }

public void testRetainAll_7_oe() {
        final List<E> list = new ArrayList<>(10);
        final SetUniqueList<E> uniqueList = SetUniqueList.setUniqueList(list);
        for (int i = 0; i < 10; ++i) {
            uniqueList.add((E)Integer.valueOf(i));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E)Integer.valueOf(i * 2));
        }

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(uniqueList.contains(Integer.valueOf(8)));
    }

public void testRetainAllWithInitialList_1_oe() {
        // initialized with empty list
        final List<E> list = new ArrayList<>(10);
        for (int i = 0; i < 5; ++i) {
            list.add((E)Integer.valueOf(i));
        }
        final SetUniqueList<E> uniqueList = SetUniqueList.setUniqueList(list);
        for (int i = 5; i < 10; ++i) {
            uniqueList.add((E)Integer.valueOf(i));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E)Integer.valueOf(i * 2));
        }

        assertTrue(uniqueList.retainAll(retained));
    }

public void testRetainAllWithInitialList_2_oe() {
        // initialized with empty list
        final List<E> list = new ArrayList<>(10);
        for (int i = 0; i < 5; ++i) {
            list.add((E)Integer.valueOf(i));
        }
        final SetUniqueList<E> uniqueList = SetUniqueList.setUniqueList(list);
        for (int i = 5; i < 10; ++i) {
            uniqueList.add((E)Integer.valueOf(i));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E)Integer.valueOf(i * 2));
        }

        // removed other assertion
        assertEquals(5, uniqueList.size());
    }

public void testRetainAllWithInitialList_3_oe() {
        // initialized with empty list
        final List<E> list = new ArrayList<>(10);
        for (int i = 0; i < 5; ++i) {
            list.add((E)Integer.valueOf(i));
        }
        final SetUniqueList<E> uniqueList = SetUniqueList.setUniqueList(list);
        for (int i = 5; i < 10; ++i) {
            uniqueList.add((E)Integer.valueOf(i));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E)Integer.valueOf(i * 2));
        }

        // removed other assertion
        // removed other assertion
        assertTrue(uniqueList.contains(Integer.valueOf(0)));
    }

public void testRetainAllWithInitialList_4_oe() {
        // initialized with empty list
        final List<E> list = new ArrayList<>(10);
        for (int i = 0; i < 5; ++i) {
            list.add((E)Integer.valueOf(i));
        }
        final SetUniqueList<E> uniqueList = SetUniqueList.setUniqueList(list);
        for (int i = 5; i < 10; ++i) {
            uniqueList.add((E)Integer.valueOf(i));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E)Integer.valueOf(i * 2));
        }

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(uniqueList.contains(Integer.valueOf(2)));
    }

public void testRetainAllWithInitialList_5_oe() {
        // initialized with empty list
        final List<E> list = new ArrayList<>(10);
        for (int i = 0; i < 5; ++i) {
            list.add((E)Integer.valueOf(i));
        }
        final SetUniqueList<E> uniqueList = SetUniqueList.setUniqueList(list);
        for (int i = 5; i < 10; ++i) {
            uniqueList.add((E)Integer.valueOf(i));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E)Integer.valueOf(i * 2));
        }

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(uniqueList.contains(Integer.valueOf(4)));
    }

public void testRetainAllWithInitialList_6_oe() {
        // initialized with empty list
        final List<E> list = new ArrayList<>(10);
        for (int i = 0; i < 5; ++i) {
            list.add((E)Integer.valueOf(i));
        }
        final SetUniqueList<E> uniqueList = SetUniqueList.setUniqueList(list);
        for (int i = 5; i < 10; ++i) {
            uniqueList.add((E)Integer.valueOf(i));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E)Integer.valueOf(i * 2));
        }

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(uniqueList.contains(Integer.valueOf(6)));
    }

public void testRetainAllWithInitialList_7_oe() {
        // initialized with empty list
        final List<E> list = new ArrayList<>(10);
        for (int i = 0; i < 5; ++i) {
            list.add((E)Integer.valueOf(i));
        }
        final SetUniqueList<E> uniqueList = SetUniqueList.setUniqueList(list);
        for (int i = 5; i < 10; ++i) {
            uniqueList.add((E)Integer.valueOf(i));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E)Integer.valueOf(i * 2));
        }

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(uniqueList.contains(Integer.valueOf(8)));
    }

public void testSet_1_oe() {
        final SetUniqueList<E> lset = new SetUniqueList<>(new ArrayList<E>(), new HashSet<E>());

        // Duplicate element
        final E obj1 = (E) Integer.valueOf(1);
        final E obj2 = (E) Integer.valueOf(2);
        final E obj3 = (E) Integer.valueOf(3);

        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj1);
        assertEquals(2, lset.size());
    }

public void testSet_2_oe() {
        final SetUniqueList<E> lset = new SetUniqueList<>(new ArrayList<E>(), new HashSet<E>());

        // Duplicate element
        final E obj1 = (E) Integer.valueOf(1);
        final E obj2 = (E) Integer.valueOf(2);
        final E obj3 = (E) Integer.valueOf(3);

        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj1);
        // removed other assertion
        assertSame(obj1, lset.get(0));
    }

public void testSet_3_oe() {
        final SetUniqueList<E> lset = new SetUniqueList<>(new ArrayList<E>(), new HashSet<E>());

        // Duplicate element
        final E obj1 = (E) Integer.valueOf(1);
        final E obj2 = (E) Integer.valueOf(2);
        final E obj3 = (E) Integer.valueOf(3);

        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj1);
        // removed other assertion
        // removed other assertion
        assertSame(obj2, lset.get(1));
    }

public void testSet_4_oe() {
        final SetUniqueList<E> lset = new SetUniqueList<>(new ArrayList<E>(), new HashSet<E>());

        // Duplicate element
        final E obj1 = (E) Integer.valueOf(1);
        final E obj2 = (E) Integer.valueOf(2);
        final E obj3 = (E) Integer.valueOf(3);

        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lset.clear();
        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj2);
        assertEquals(1, lset.size());
    }

public void testSet_5_oe() {
        final SetUniqueList<E> lset = new SetUniqueList<>(new ArrayList<E>(), new HashSet<E>());

        // Duplicate element
        final E obj1 = (E) Integer.valueOf(1);
        final E obj2 = (E) Integer.valueOf(2);
        final E obj3 = (E) Integer.valueOf(3);

        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lset.clear();
        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj2);
        // removed other assertion
        assertSame(obj2, lset.get(0));
    }

public void testSet_6_oe() {
        final SetUniqueList<E> lset = new SetUniqueList<>(new ArrayList<E>(), new HashSet<E>());

        // Duplicate element
        final E obj1 = (E) Integer.valueOf(1);
        final E obj2 = (E) Integer.valueOf(2);
        final E obj3 = (E) Integer.valueOf(3);

        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lset.clear();
        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj2);
        // removed other assertion
        // removed other assertion

        lset.clear();
        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj3);
        assertEquals(2, lset.size());
    }

public void testSet_7_oe() {
        final SetUniqueList<E> lset = new SetUniqueList<>(new ArrayList<E>(), new HashSet<E>());

        // Duplicate element
        final E obj1 = (E) Integer.valueOf(1);
        final E obj2 = (E) Integer.valueOf(2);
        final E obj3 = (E) Integer.valueOf(3);

        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lset.clear();
        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj2);
        // removed other assertion
        // removed other assertion

        lset.clear();
        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj3);
        // removed other assertion
        assertSame(obj3, lset.get(0));
    }

public void testSet_8_oe() {
        final SetUniqueList<E> lset = new SetUniqueList<>(new ArrayList<E>(), new HashSet<E>());

        // Duplicate element
        final E obj1 = (E) Integer.valueOf(1);
        final E obj2 = (E) Integer.valueOf(2);
        final E obj3 = (E) Integer.valueOf(3);

        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lset.clear();
        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj2);
        // removed other assertion
        // removed other assertion

        lset.clear();
        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj3);
        // removed other assertion
        // removed other assertion
        assertSame(obj2, lset.get(1));
    }

public void testSet_9_oe() {
        final SetUniqueList<E> lset = new SetUniqueList<>(new ArrayList<E>(), new HashSet<E>());

        // Duplicate element
        final E obj1 = (E) Integer.valueOf(1);
        final E obj2 = (E) Integer.valueOf(2);
        final E obj3 = (E) Integer.valueOf(3);

        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lset.clear();
        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj2);
        // removed other assertion
        // removed other assertion

        lset.clear();
        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj3);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lset.clear();
        lset.add(obj1);
        lset.add(obj2);
        lset.set(1, obj1);
        assertEquals(1, lset.size());
    }

public void testSet_10_oe() {
        final SetUniqueList<E> lset = new SetUniqueList<>(new ArrayList<E>(), new HashSet<E>());

        // Duplicate element
        final E obj1 = (E) Integer.valueOf(1);
        final E obj2 = (E) Integer.valueOf(2);
        final E obj3 = (E) Integer.valueOf(3);

        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lset.clear();
        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj2);
        // removed other assertion
        // removed other assertion

        lset.clear();
        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj3);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        lset.clear();
        lset.add(obj1);
        lset.add(obj2);
        lset.set(1, obj1);
        // removed other assertion
        assertSame(obj1, lset.get(0));
    }

public void testSetCollections444_1_oe() {
        final SetUniqueList<Integer> lset = new SetUniqueList<>(new ArrayList<Integer>(), new HashSet<Integer>());

        // Duplicate element
        final Integer obj1 = Integer.valueOf(1);
        final Integer obj2 = Integer.valueOf(2);

        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj1);
        assertEquals(2, lset.size());
    }

public void testSetCollections444_2_oe() {
        final SetUniqueList<Integer> lset = new SetUniqueList<>(new ArrayList<Integer>(), new HashSet<Integer>());

        // Duplicate element
        final Integer obj1 = Integer.valueOf(1);
        final Integer obj2 = Integer.valueOf(2);

        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj1);
        // removed other assertion
        assertSame(obj1, lset.get(0));
    }

public void testSetCollections444_3_oe() {
        final SetUniqueList<Integer> lset = new SetUniqueList<>(new ArrayList<Integer>(), new HashSet<Integer>());

        // Duplicate element
        final Integer obj1 = Integer.valueOf(1);
        final Integer obj2 = Integer.valueOf(2);

        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj1);
        // removed other assertion
        // removed other assertion
        assertSame(obj2, lset.get(1));
    }

public void testSetCollections444_4_oe() {
        final SetUniqueList<Integer> lset = new SetUniqueList<>(new ArrayList<Integer>(), new HashSet<Integer>());

        // Duplicate element
        final Integer obj1 = Integer.valueOf(1);
        final Integer obj2 = Integer.valueOf(2);

        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(lset.contains(obj1));
    }

public void testSetCollections444_5_oe() {
        final SetUniqueList<Integer> lset = new SetUniqueList<>(new ArrayList<Integer>(), new HashSet<Integer>());

        // Duplicate element
        final Integer obj1 = Integer.valueOf(1);
        final Integer obj2 = Integer.valueOf(2);

        lset.add(obj1);
        lset.add(obj2);
        lset.set(0, obj1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(lset.contains(obj2));
    }

public void testSetDownwardsInList_1_oe() {
        /*
         * Checks the following semantics
         * [a,b]
         * set(0,b): [b]->a
         * So UniqList contains [b] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        ul.add(a);
        ul.add(b);
        assertEquals(a, l.get(0));
    }

public void testSetDownwardsInList_2_oe() {
        /*
         * Checks the following semantics
         * [a,b]
         * set(0,b): [b]->a
         * So UniqList contains [b] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        ul.add(a);
        ul.add(b);
        // removed other assertion
        assertEquals(b, l.get(1));
    }

public void testSetDownwardsInList_3_oe() {
        /*
         * Checks the following semantics
         * [a,b]
         * set(0,b): [b]->a
         * So UniqList contains [b] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        ul.add(a);
        ul.add(b);
        // removed other assertion
        // removed other assertion
        assertTrue(s.contains(a));
    }

public void testSetDownwardsInList_4_oe() {
        /*
         * Checks the following semantics
         * [a,b]
         * set(0,b): [b]->a
         * So UniqList contains [b] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        ul.add(a);
        ul.add(b);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(s.contains(b));
    }

public void testSetDownwardsInList_5_oe() {
        /*
         * Checks the following semantics
         * [a,b]
         * set(0,b): [b]->a
         * So UniqList contains [b] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        ul.add(a);
        ul.add(b);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(a, ul.set(0, b));
    }

public void testSetDownwardsInList_6_oe() {
        /*
         * Checks the following semantics
         * [a,b]
         * set(0,b): [b]->a
         * So UniqList contains [b] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        ul.add(a);
        ul.add(b);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(1, s.size());
    }

public void testSetDownwardsInList_7_oe() {
        /*
         * Checks the following semantics
         * [a,b]
         * set(0,b): [b]->a
         * So UniqList contains [b] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        ul.add(a);
        ul.add(b);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(1, l.size());
    }

public void testSetDownwardsInList_8_oe() {
        /*
         * Checks the following semantics
         * [a,b]
         * set(0,b): [b]->a
         * So UniqList contains [b] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        ul.add(a);
        ul.add(b);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(b, l.get(0));
    }

public void testSetDownwardsInList_9_oe() {
        /*
         * Checks the following semantics
         * [a,b]
         * set(0,b): [b]->a
         * So UniqList contains [b] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        ul.add(a);
        ul.add(b);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(s.contains(b));
    }

public void testSetDownwardsInList_10_oe() {
        /*
         * Checks the following semantics
         * [a,b]
         * set(0,b): [b]->a
         * So UniqList contains [b] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        ul.add(a);
        ul.add(b);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(s.contains(a));
    }

public void testSetInBiggerList_1_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(0,b): [b,c]->a
         * So UniqList contains [b,c] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        final E c = (E) new Object();

        ul.add(a);
        ul.add(b);
        ul.add(c);
        assertEquals(a, l.get(0));
    }

public void testSetInBiggerList_2_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(0,b): [b,c]->a
         * So UniqList contains [b,c] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        final E c = (E) new Object();

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        assertEquals(b, l.get(1));
    }

public void testSetInBiggerList_3_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(0,b): [b,c]->a
         * So UniqList contains [b,c] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        final E c = (E) new Object();

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        assertEquals(c, l.get(2));
    }

public void testSetInBiggerList_4_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(0,b): [b,c]->a
         * So UniqList contains [b,c] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        final E c = (E) new Object();

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(s.contains(a));
    }

public void testSetInBiggerList_5_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(0,b): [b,c]->a
         * So UniqList contains [b,c] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        final E c = (E) new Object();

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(s.contains(b));
    }

public void testSetInBiggerList_6_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(0,b): [b,c]->a
         * So UniqList contains [b,c] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        final E c = (E) new Object();

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(s.contains(c));
    }

public void testSetInBiggerList_7_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(0,b): [b,c]->a
         * So UniqList contains [b,c] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        final E c = (E) new Object();

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(a, ul.set(0, b));
    }

public void testSetInBiggerList_8_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(0,b): [b,c]->a
         * So UniqList contains [b,c] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        final E c = (E) new Object();

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(2, s.size());
    }

public void testSetInBiggerList_9_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(0,b): [b,c]->a
         * So UniqList contains [b,c] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        final E c = (E) new Object();

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(2, l.size());
    }

public void testSetInBiggerList_10_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(0,b): [b,c]->a
         * So UniqList contains [b,c] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        final E c = (E) new Object();

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(b, l.get(0));
    }

public void testSetInBiggerList_11_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(0,b): [b,c]->a
         * So UniqList contains [b,c] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        final E c = (E) new Object();

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(c, l.get(1));
    }

public void testSetInBiggerList_12_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(0,b): [b,c]->a
         * So UniqList contains [b,c] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        final E c = (E) new Object();

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(s.contains(a));
    }

public void testSetInBiggerList_13_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(0,b): [b,c]->a
         * So UniqList contains [b,c] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        final E c = (E) new Object();

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(s.contains(b));
    }

public void testSetInBiggerList_14_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(0,b): [b,c]->a
         * So UniqList contains [b,c] and a is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new Object();
        final E b = (E) new Object();
        final E c = (E) new Object();

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(s.contains(c));
    }

public void testSetUpwardsInList_1_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(1,a): [a,c]->b
         * So UniqList contains [a,c] and b is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new String("A");
        final E b = (E) new String("B");
        final E c = (E) new String("C");

        ul.add(a);
        ul.add(b);
        ul.add(c);
        assertEquals(a, l.get(0));
    }

public void testSetUpwardsInList_2_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(1,a): [a,c]->b
         * So UniqList contains [a,c] and b is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new String("A");
        final E b = (E) new String("B");
        final E c = (E) new String("C");

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        assertEquals(b, l.get(1));
    }

public void testSetUpwardsInList_3_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(1,a): [a,c]->b
         * So UniqList contains [a,c] and b is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new String("A");
        final E b = (E) new String("B");
        final E c = (E) new String("C");

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        assertEquals(c, l.get(2));
    }

public void testSetUpwardsInList_4_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(1,a): [a,c]->b
         * So UniqList contains [a,c] and b is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new String("A");
        final E b = (E) new String("B");
        final E c = (E) new String("C");

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(s.contains(a));
    }

public void testSetUpwardsInList_5_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(1,a): [a,c]->b
         * So UniqList contains [a,c] and b is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new String("A");
        final E b = (E) new String("B");
        final E c = (E) new String("C");

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(s.contains(b));
    }

public void testSetUpwardsInList_6_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(1,a): [a,c]->b
         * So UniqList contains [a,c] and b is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new String("A");
        final E b = (E) new String("B");
        final E c = (E) new String("C");

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(s.contains(c));
    }

public void testSetUpwardsInList_7_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(1,a): [a,c]->b
         * So UniqList contains [a,c] and b is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new String("A");
        final E b = (E) new String("B");
        final E c = (E) new String("C");

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(b, ul.set(1, a));
    }

public void testSetUpwardsInList_8_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(1,a): [a,c]->b
         * So UniqList contains [a,c] and b is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new String("A");
        final E b = (E) new String("B");
        final E c = (E) new String("C");

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(2, s.size());
    }

public void testSetUpwardsInList_9_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(1,a): [a,c]->b
         * So UniqList contains [a,c] and b is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new String("A");
        final E b = (E) new String("B");
        final E c = (E) new String("C");

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(2, l.size());
    }

public void testSetUpwardsInList_10_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(1,a): [a,c]->b
         * So UniqList contains [a,c] and b is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new String("A");
        final E b = (E) new String("B");
        final E c = (E) new String("C");

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(a, l.get(0));
    }

public void testSetUpwardsInList_11_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(1,a): [a,c]->b
         * So UniqList contains [a,c] and b is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new String("A");
        final E b = (E) new String("B");
        final E c = (E) new String("C");

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(c, l.get(1));
    }

public void testSetUpwardsInList_12_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(1,a): [a,c]->b
         * So UniqList contains [a,c] and b is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new String("A");
        final E b = (E) new String("B");
        final E c = (E) new String("C");

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(s.contains(a));
    }

public void testSetUpwardsInList_13_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(1,a): [a,c]->b
         * So UniqList contains [a,c] and b is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new String("A");
        final E b = (E) new String("B");
        final E c = (E) new String("C");

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(s.contains(b));
    }

public void testSetUpwardsInList_14_oe() {
        /*
         * Checks the following semantics
         * [a,b,c]
         * set(1,a): [a,c]->b
         * So UniqList contains [a,c] and b is returned
         */
        final ArrayList<E> l = new ArrayList<>();
        final HashSet<E> s = new HashSet<>();
        final SetUniqueList<E> ul = new SetUniqueList<>(l, s);

        final E a = (E) new String("A");
        final E b = (E) new String("B");
        final E c = (E) new String("C");

        ul.add(a);
        ul.add(b);
        ul.add(c);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(s.contains(c));
    }

public void testUniqueListDoubleInsert_1_oe() {
        final List<E> l = SetUniqueList.setUniqueList(new LinkedList<E>());
        l.add((E) new Object());
        l.add((E) new Object());

        // duplicate is removed
        l.set(0, l.get(1));
        assertEquals(1, l.size());
    }

public void testUniqueListDoubleInsert_2_oe() {
        final List<E> l = SetUniqueList.setUniqueList(new LinkedList<E>());
        l.add((E) new Object());
        l.add((E) new Object());

        // duplicate is removed
        l.set(0, l.get(1));
        // removed other assertion

        // duplicate should be removed again
        l.add(1, l.get(0));
        assertEquals(1, l.size());
    }

public void testUniqueListReInsert_1_oe() {
        final List<E> l = SetUniqueList.setUniqueList(new LinkedList<E>());
        l.add((E) new Object());
        l.add((E) new Object());

        final E a = l.get(0);

        // duplicate is removed
        l.set(0, l.get(1));
        assertEquals(1, l.size());
    }

public void testUniqueListReInsert_2_oe() {
        final List<E> l = SetUniqueList.setUniqueList(new LinkedList<E>());
        l.add((E) new Object());
        l.add((E) new Object());

        final E a = l.get(0);

        // duplicate is removed
        l.set(0, l.get(1));
        // removed other assertion

        // old object is added back in
        l.add(1, a);
        assertEquals(2, l.size());
    }

}
