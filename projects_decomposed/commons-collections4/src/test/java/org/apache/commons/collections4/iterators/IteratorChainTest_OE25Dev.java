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
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.Predicate;

/**
 * Tests the IteratorChain class.
 *
 */
public class IteratorChainTest_OE25Dev extends AbstractIteratorTest<String> {

    protected String[] testArray = {
        "One", "Two", "Three", "Four", "Five", "Six"
    };

    protected List<String> list1 = null;
    protected List<String> list2 = null;
    protected List<String> list3 = null;

    public IteratorChainTest_OE25Dev(final String testName) {
        super(testName);
    }

    @Override
    public void setUp() {
        list1 = new ArrayList<>();
        list1.add("One");
        list1.add("Two");
        list1.add("Three");
        list2 = new ArrayList<>();
        list2.add("Four");
        list3 = new ArrayList<>();
        list3.add("Five");
        list3.add("Six");
    }

    @Override
    public IteratorChain<String> makeEmptyIterator() {
        final ArrayList<String> list = new ArrayList<>();
        return new IteratorChain<>(list.iterator());
    }

    @Override
    public IteratorChain<String> makeObject() {
        final IteratorChain<String> chain = new IteratorChain<>();

        chain.addIterator(list1.iterator());
        chain.addIterator(list2.iterator());
        chain.addIterator(list3.iterator());
        return chain;
    }

    public void testIterator_1_oe() {
        final Iterator<String> iter = makeObject();
        for (final String testValue : testArray) {
            final Object iterValue = iter.next();

            assertEquals( "Iteration value is correct", testValue, iterValue );
    }
    }

    public void testIterator_2_oe() {
        final Iterator<String> iter = makeObject();
        for (final String testValue : testArray) {
            final Object iterValue = iter.next();

        }

        assertTrue("Iterator should now be empty", !iter.hasNext());
    }

    public void testIterator_3_oe() {
        final Iterator<String> iter = makeObject();
        for (final String testValue : testArray) {
            final Object iterValue = iter.next();

        }


        try {
            iter.next();
        } catch (final Exception e) {
            assertTrue("NoSuchElementException must be thrown",e.getClass().equals(new NoSuchElementException().getClass()));
    }
    }

    public void testRemoveFromFilteredIterator_1_oe() {

        final Predicate<Integer> myPredicate = new Predicate<Integer>() {
            @Override
            public boolean evaluate(final Integer i) {
                return i.compareTo(Integer.valueOf(4)) < 0;
            }
        };

        final List<Integer> list1 = new ArrayList<>();
        final List<Integer> list2 = new ArrayList<>();

        list1.add(Integer.valueOf(1));
        list1.add(Integer.valueOf(2));
        list2.add(Integer.valueOf(3));
        list2.add(Integer.valueOf(4)); // will be ignored by the predicate

        final Iterator<Integer> it1 = IteratorUtils.filteredIterator(list1.iterator(), myPredicate);
        final Iterator<Integer> it2 = IteratorUtils.filteredIterator(list2.iterator(), myPredicate);

        final Iterator<Integer> it = IteratorUtils.chainedIterator(it1, it2);
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        assertEquals(0, list1.size());
    }

    public void testRemoveFromFilteredIterator_2_oe() {

        final Predicate<Integer> myPredicate = new Predicate<Integer>() {
            @Override
            public boolean evaluate(final Integer i) {
                return i.compareTo(Integer.valueOf(4)) < 0;
            }
        };

        final List<Integer> list1 = new ArrayList<>();
        final List<Integer> list2 = new ArrayList<>();

        list1.add(Integer.valueOf(1));
        list1.add(Integer.valueOf(2));
        list2.add(Integer.valueOf(3));
        list2.add(Integer.valueOf(4)); // will be ignored by the predicate

        final Iterator<Integer> it1 = IteratorUtils.filteredIterator(list1.iterator(), myPredicate);
        final Iterator<Integer> it2 = IteratorUtils.filteredIterator(list2.iterator(), myPredicate);

        final Iterator<Integer> it = IteratorUtils.chainedIterator(it1, it2);
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        assertEquals(1, list2.size());
    }

    public void testRemove_2_oe() {
        final Iterator<String> iter = makeObject();

        try {
            iter.remove();
        } catch (final IllegalStateException e) {

        }

        for (final String testValue : testArray) {
            final String iterValue = iter.next();

            assertEquals("Iteration value is correct", testValue, iterValue);
    }
    }

    public void testRemove_3_oe() {
        final Iterator<String> iter = makeObject();

        try {
            iter.remove();
        } catch (final IllegalStateException e) {

        }

        for (final String testValue : testArray) {
            final String iterValue = iter.next();


            if (!iterValue.equals("Four")) {
                iter.remove();
            }
        }

        assertTrue("List is empty",list1.size() == 0);
    }

    public void testRemove_4_oe() {
        final Iterator<String> iter = makeObject();

        try {
            iter.remove();
        } catch (final IllegalStateException e) {

        }

        for (final String testValue : testArray) {
            final String iterValue = iter.next();


            if (!iterValue.equals("Four")) {
                iter.remove();
            }
        }

        assertTrue("List is empty",list2.size() == 1);
    }

    public void testRemove_5_oe() {
        final Iterator<String> iter = makeObject();

        try {
            iter.remove();
        } catch (final IllegalStateException e) {

        }

        for (final String testValue : testArray) {
            final String iterValue = iter.next();


            if (!iterValue.equals("Four")) {
                iter.remove();
            }
        }

        assertTrue("List is empty",list3.size() == 0);
    }

    public void testFirstIteratorIsEmptyBug_1_oe() {
        final List<String> empty = new ArrayList<>();
        final List<String> notEmpty = new ArrayList<>();
        notEmpty.add("A");
        notEmpty.add("B");
        notEmpty.add("C");
        final IteratorChain<String> chain = new IteratorChain<>();
        chain.addIterator(empty.iterator());
        chain.addIterator(notEmpty.iterator());
        assertTrue("should have next",chain.hasNext());
    }

    public void testFirstIteratorIsEmptyBug_2_oe() {
        final List<String> empty = new ArrayList<>();
        final List<String> notEmpty = new ArrayList<>();
        notEmpty.add("A");
        notEmpty.add("B");
        notEmpty.add("C");
        final IteratorChain<String> chain = new IteratorChain<>();
        chain.addIterator(empty.iterator());
        chain.addIterator(notEmpty.iterator());
        assertEquals("A",chain.next());
    }

    public void testFirstIteratorIsEmptyBug_3_oe() {
        final List<String> empty = new ArrayList<>();
        final List<String> notEmpty = new ArrayList<>();
        notEmpty.add("A");
        notEmpty.add("B");
        notEmpty.add("C");
        final IteratorChain<String> chain = new IteratorChain<>();
        chain.addIterator(empty.iterator());
        chain.addIterator(notEmpty.iterator());
        assertTrue("should have next",chain.hasNext());
    }

    public void testFirstIteratorIsEmptyBug_5_oe() {
        final List<String> empty = new ArrayList<>();
        final List<String> notEmpty = new ArrayList<>();
        notEmpty.add("A");
        notEmpty.add("B");
        notEmpty.add("C");
        final IteratorChain<String> chain = new IteratorChain<>();
        chain.addIterator(empty.iterator());
        chain.addIterator(notEmpty.iterator());
        assertTrue("should have next",chain.hasNext());
    }

    public void testEmptyChain_1_oe() {
        final IteratorChain<Object> chain = new IteratorChain<>();
        assertEquals(false, chain.hasNext());
    }

}
