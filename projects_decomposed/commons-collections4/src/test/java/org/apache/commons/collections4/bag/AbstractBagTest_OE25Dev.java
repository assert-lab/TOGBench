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
package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.collection.AbstractCollectionTest;
import org.apache.commons.collections4.set.AbstractSetTest;

/**
 * Abstract test class for {@link org.apache.commons.collections4.Bag Bag} methods and contracts.
 * <p>
 * To use, simply extend this class, and implement
 * the {@link #makeObject} method.
 * <p>
 * If your bag fails one of these tests by design,
 * you may still use this base set of cases.  Simply override the
 * test case (method) your bag fails.
 * <p>
 * <b>Note:</b> The Bag interface does not conform to the Collection interface
 * so the generic collection tests from AbstractCollectionTest would normally fail.
 * As a work-around since 4.0, a CollectionBag decorator can be used
 * to make any Bag implementation comply to the Collection contract.
 * <p>
 * This abstract test class does wrap the concrete bag implementation
 * with such a decorator, see the overridden {@link #resetEmpty()} and
 * {@link #resetFull()} methods.
 * <p>
 * In addition to the generic collection tests (prefix testCollection) inherited
 * from AbstractCollectionTest, there are test methods that test the "normal" Bag
 * interface (prefix testBag). For Bag specific tests use the {@link #makeObject()} and
 * {@link #makeFullCollection()} methods instead of {@link #resetEmpty()} and resetFull(),
 * otherwise the collection will be wrapped by a {@link CollectionBag} decorator.
 *
 */
public abstract class AbstractBagTest_OE25Dev<T> extends AbstractCollectionTest<T> {

    /**
     * JUnit constructor.
     *
     * @param testName  the test class name
     */
    public AbstractBagTest_OE25Dev(final String testName) {
        super(testName);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns an empty {@link ArrayList}.
     */
    @Override
    public Collection<T> makeConfirmedCollection() {
        final ArrayList<T> list = new ArrayList<>();
        return list;
    }

    /**
     * Returns a full collection.
     */
    @Override
    public Collection<T> makeConfirmedFullCollection() {
        final Collection<T> coll = makeConfirmedCollection();
        coll.addAll(Arrays.asList(getFullElements()));
        return coll;
    }

    /**
     * Return a new, empty bag to used for testing.
     *
     * @return the bag to be tested
     */
    @Override
    public abstract Bag<T> makeObject();

    /**
     * {@inheritDoc}
     */
    @Override
    public Bag<T> makeFullCollection() {
        final Bag<T> bag = makeObject();
        bag.addAll(Arrays.asList(getFullElements()));
        return bag;
    }

    //-----------------------------------------------------------------------

    @Override
    public void resetEmpty() {
        this.setCollection(CollectionBag.collectionBag(makeObject()));
        this.setConfirmed(makeConfirmedCollection());
    }

    @Override
    public void resetFull() {
        this.setCollection(CollectionBag.collectionBag(makeFullCollection()));
        this.setConfirmed(makeConfirmedFullCollection());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the {@link #collection} field cast to a {@link Bag}.
     *
     * @return the collection field as a Bag
     */
    @Override
    public Bag<T> getCollection() {
        return (Bag<T>) super.getCollection();
    }

    //-----------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    public void testBagIteratorFail() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        final Iterator<T> it = bag.iterator();
        it.next();
        bag.remove("A");
        try {
            it.next();
            fail("Should throw ConcurrentModificationException");
        } catch (final ConcurrentModificationException e) {
            // expected
        }
    }

    @SuppressWarnings("unchecked")
    public void testBagIteratorFailNoMore() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        final Iterator<T> it = bag.iterator();
        it.next();
        it.next();
        it.next();
        try {
            it.next();
            fail("Should throw NoSuchElementException");
        } catch (final NoSuchElementException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    /**
     * Bulk test {@link Bag#uniqueSet()}.  This method runs through all of
     * the tests in {@link AbstractSetTest}.
     * After modification operations, {@link #verify()} is invoked to ensure
     * that the bag and the other collection views are still valid.
     *
     * @return a {@link AbstractSetTest} instance for testing the bag's unique set
     */
    public BulkTest bulkTestBagUniqueSet() {
        return new TestBagUniqueSet();
    }

    public class TestBagUniqueSet extends AbstractSetTest<T> {
        public TestBagUniqueSet() {
            super("");
        }

        @Override
        public T[] getFullElements() {
            return AbstractBagTest_OE25Dev.this.getFullElements();
        }

        @Override
        public T[] getOtherElements() {
            return AbstractBagTest_OE25Dev.this.getOtherElements();
        }

        @Override
        public Set<T> makeObject() {
            return AbstractBagTest_OE25Dev.this.makeObject().uniqueSet();
        }

        @Override
        public Set<T> makeFullCollection() {
            return AbstractBagTest_OE25Dev.this.makeFullCollection().uniqueSet();
        }

        @Override
        public boolean isNullSupported() {
            return AbstractBagTest_OE25Dev.this.isNullSupported();
        }

        @Override
        public boolean isAddSupported() {
            return false;
        }

        @Override
        public boolean isRemoveSupported() {
            return false;
        }

        @Override
        public boolean isTestSerialization() {
            return false;
        }

        @Override
        public void resetEmpty() {
            AbstractBagTest_OE25Dev.this.resetEmpty();
            TestBagUniqueSet.this.setCollection(AbstractBagTest_OE25Dev.this.getCollection().uniqueSet());
            TestBagUniqueSet.this.setConfirmed(new HashSet<>(AbstractBagTest_OE25Dev.this.getConfirmed()));
        }

        @Override
        public void resetFull() {
            AbstractBagTest_OE25Dev.this.resetFull();
            TestBagUniqueSet.this.setCollection(AbstractBagTest_OE25Dev.this.getCollection().uniqueSet());
            TestBagUniqueSet.this.setConfirmed(new HashSet<>(AbstractBagTest_OE25Dev.this.getConfirmed()));
        }

        @Override
        public void verify() {
            super.verify();
        }
    }

    //-----------------------------------------------------------------------

    /**
     * Compare the current serialized form of the Bag
     * against the canonical version in SVN.
     */

    /**
     * Compare the current serialized form of the Bag
     * against the canonical version in SVN.
     */

    public void testBagAdd_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        assertTrue("Should contain 'A'", bag.contains("A"));
    }

    public void testBagAdd_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        assertEquals("Should have count of 1", 1, bag.getCount("A"));
    }

    public void testBagAdd_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        assertTrue("Should contain 'A'", bag.contains("A"));
    }

    public void testBagAdd_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        assertEquals("Should have count of 2", 2, bag.getCount("A"));
    }

    public void testBagAdd_5_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        assertTrue(bag.contains("A"));
    }

    public void testBagAdd_6_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        assertTrue(bag.contains("B"));
    }

    public void testBagEqualsSelf_1_oe() {
        final Bag<T> bag = makeObject();
        assertTrue(bag.equals(bag));
    }

    public void testBagEqualsSelf_2_oe() {
        final Bag<T> bag = makeObject();

        if (!isAddSupported()) {
            return;
        }

        bag.add((T) "elt");
        assertTrue(bag.equals(bag));
    }

    public void testBagEqualsSelf_3_oe() {
        final Bag<T> bag = makeObject();

        if (!isAddSupported()) {
            return;
        }

        bag.add((T) "elt");
        bag.add((T) "elt"); // again
        assertTrue(bag.equals(bag));
    }

    public void testBagEqualsSelf_4_oe() {
        final Bag<T> bag = makeObject();

        if (!isAddSupported()) {
            return;
        }

        bag.add((T) "elt");
        bag.add((T) "elt"); // again
        bag.add((T) "elt2");
        assertTrue(bag.equals(bag));
    }

    public void testBagRemove_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        assertEquals("Should have count of 1", 1, bag.getCount("A"));
    }

    public void testBagRemove_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.remove("A");
        assertEquals("Should have count of 0", 0, bag.getCount("A"));
    }

    public void testBagRemove_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.remove("A");
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "A");
        assertEquals("Should have count of 4", 4, bag.getCount("A"));
    }

    public void testBagRemove_4_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.remove("A");
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "A");
        bag.remove("A", 0);
        assertEquals("Should have count of 4", 4, bag.getCount("A"));
    }

    public void testBagRemove_5_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.remove("A");
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "A");
        bag.remove("A", 0);
        bag.remove("A", 2);
        assertEquals("Should have count of 2", 2, bag.getCount("A"));
    }

    public void testBagRemove_6_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.remove("A");
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "A");
        bag.remove("A", 0);
        bag.remove("A", 2);
        bag.remove("A");
        assertEquals("Should have count of 0", 0, bag.getCount("A"));
    }

    public void testBagRemoveAll_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A", 2);
        assertEquals("Should have count of 2", 2, bag.getCount("A"));
    }

    public void testBagRemoveAll_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A", 2);
        bag.add((T) "B");
        bag.add((T) "C");
        assertEquals("Should have count of 4", 4, bag.size());
    }

    public void testBagRemoveAll_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A", 2);
        bag.add((T) "B");
        bag.add((T) "C");
        final List<String> delete = new ArrayList<>();
        delete.add("A");
        delete.add("B");
        bag.removeAll(delete);
        assertEquals("Should have count of 1", 1, bag.getCount("A"));
    }

    public void testBagRemoveAll_4_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A", 2);
        bag.add((T) "B");
        bag.add((T) "C");
        final List<String> delete = new ArrayList<>();
        delete.add("A");
        delete.add("B");
        bag.removeAll(delete);
        assertEquals("Should have count of 0", 0, bag.getCount("B"));
    }

    public void testBagRemoveAll_5_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A", 2);
        bag.add((T) "B");
        bag.add((T) "C");
        final List<String> delete = new ArrayList<>();
        delete.add("A");
        delete.add("B");
        bag.removeAll(delete);
        assertEquals("Should have count of 1", 1, bag.getCount("C"));
    }

    public void testBagRemoveAll_6_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A", 2);
        bag.add((T) "B");
        bag.add((T) "C");
        final List<String> delete = new ArrayList<>();
        delete.add("A");
        delete.add("B");
        bag.removeAll(delete);
        assertEquals("Should have count of 2", 2, bag.size());
    }

    public void testBagContains_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();

        assertEquals("Bag does not have at least 1 'A'", false, bag.contains("A"));
    }

    public void testBagContains_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();

        assertEquals("Bag does not have at least 1 'B'", false, bag.contains("B"));
    }

    public void testBagContains_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();


        bag.add((T) "A");  // bag 1A
        assertEquals("Bag has at least 1 'A'", true, bag.contains("A"));
    }

    public void testBagContains_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();


        bag.add((T) "A");  // bag 1A
        assertEquals("Bag does not have at least 1 'B'", false, bag.contains("B"));
    }

    public void testBagContains_5_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A
        assertEquals("Bag has at least 1 'A'", true, bag.contains("A"));
    }

    public void testBagContains_6_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A
        assertEquals("Bag does not have at least 1 'B'", false, bag.contains("B"));
    }

    public void testBagContains_7_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A

        bag.add((T) "B");  // bag 2A,1B
        assertEquals("Bag has at least 1 'A'", true, bag.contains("A"));
    }

    public void testBagContains_8_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A

        bag.add((T) "B");  // bag 2A,1B
        assertEquals("Bag has at least 1 'B'", true, bag.contains("B"));
    }

    public void testBagContainsAll_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");

        assertEquals("Bag containsAll of empty", true, bag.containsAll(known));
    }

    public void testBagContainsAll_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");

        assertEquals("Bag does not containsAll of 1 'A'", false, bag.containsAll(known1A));
    }

    public void testBagContainsAll_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");

        assertEquals("Bag does not containsAll of 2 'A'", false, bag.containsAll(known2A));
    }

    public void testBagContainsAll_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");

        assertEquals("Bag does not containsAll of 1 'B'", false, bag.containsAll(known1B));
    }

    public void testBagContainsAll_5_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");

        assertEquals("Bag does not containsAll of 1 'A' 1 'B'", false, bag.containsAll(known1A1B));
    }

    public void testBagContainsAll_6_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A
        assertEquals("Bag containsAll of empty", true, bag.containsAll(known));
    }

    public void testBagContainsAll_7_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A
        assertEquals("Bag containsAll of 1 'A'", true, bag.containsAll(known1A));
    }

    public void testBagContainsAll_8_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A
        assertEquals("Bag does not containsAll of 2 'A'", false, bag.containsAll(known2A));
    }

    public void testBagContainsAll_9_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A
        assertEquals("Bag does not containsAll of 1 'B'", false, bag.containsAll(known1B));
    }

    public void testBagContainsAll_10_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A
        assertEquals("Bag does not containsAll of 1 'A' 1 'B'", false, bag.containsAll(known1A1B));
    }

    public void testBagContainsAll_11_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A
        assertEquals("Bag containsAll of empty", true, bag.containsAll(known));
    }

    public void testBagContainsAll_12_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A
        assertEquals("Bag containsAll of 1 'A'", true, bag.containsAll(known1A));
    }

    public void testBagContainsAll_13_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A
        assertEquals("Bag containsAll of 2 'A'", true, bag.containsAll(known2A));
    }

    public void testBagContainsAll_14_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A
        assertEquals("Bag does not containsAll of 1 'B'", false, bag.containsAll(known1B));
    }

    public void testBagContainsAll_15_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A
        assertEquals("Bag does not containsAll of 1 'A' 1 'B'", false, bag.containsAll(known1A1B));
    }

    public void testBagContainsAll_16_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A

        bag.add((T) "A");  // bag 3A
        assertEquals("Bag containsAll of empty", true, bag.containsAll(known));
    }

    public void testBagContainsAll_17_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A

        bag.add((T) "A");  // bag 3A
        assertEquals("Bag containsAll of 1 'A'", true, bag.containsAll(known1A));
    }

    public void testBagContainsAll_18_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A

        bag.add((T) "A");  // bag 3A
        assertEquals("Bag containsAll of 2 'A'", true, bag.containsAll(known2A));
    }

    public void testBagContainsAll_19_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A

        bag.add((T) "A");  // bag 3A
        assertEquals("Bag does not containsAll of 1 'B'", false, bag.containsAll(known1B));
    }

    public void testBagContainsAll_20_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A

        bag.add((T) "A");  // bag 3A
        assertEquals("Bag does not containsAll of 1 'A' 1 'B'", false, bag.containsAll(known1A1B));
    }

    public void testBagContainsAll_21_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A

        bag.add((T) "A");  // bag 3A

        bag.add((T) "B");  // bag 3A1B
        assertEquals("Bag containsAll of empty", true, bag.containsAll(known));
    }

    public void testBagContainsAll_22_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A

        bag.add((T) "A");  // bag 3A

        bag.add((T) "B");  // bag 3A1B
        assertEquals("Bag containsAll of 1 'A'", true, bag.containsAll(known1A));
    }

    public void testBagContainsAll_23_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A

        bag.add((T) "A");  // bag 3A

        bag.add((T) "B");  // bag 3A1B
        assertEquals("Bag containsAll of 2 'A'", true, bag.containsAll(known2A));
    }

    public void testBagContainsAll_24_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A

        bag.add((T) "A");  // bag 3A

        bag.add((T) "B");  // bag 3A1B
        assertEquals("Bag containsAll of 1 'B'", true, bag.containsAll(known1B));
    }

    public void testBagContainsAll_25_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final List<String> known = new ArrayList<>();
        final List<String> known1A = new ArrayList<>();
        known1A.add("A");
        final List<String> known2A = new ArrayList<>();
        known2A.add("A");
        known2A.add("A");
        final List<String> known1B = new ArrayList<>();
        known1B.add("B");
        final List<String> known1A1B = new ArrayList<>();
        known1A1B.add("A");
        known1A1B.add("B");


        bag.add((T) "A");  // bag 1A

        bag.add((T) "A");  // bag 2A

        bag.add((T) "A");  // bag 3A

        bag.add((T) "B");  // bag 3A1B
        assertEquals("Bag containsAll of 1 'A' 1 'B'", true, bag.containsAll(known1A1B));
    }

    public void testBagSize_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        assertEquals("Should have 0 total items", 0, bag.size());
    }

    public void testBagSize_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        assertEquals("Should have 1 total items", 1, bag.size());
    }

    public void testBagSize_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        assertEquals("Should have 2 total items", 2, bag.size());
    }

    public void testBagSize_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "A");
        assertEquals("Should have 3 total items", 3, bag.size());
    }

    public void testBagSize_5_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        assertEquals("Should have 4 total items", 4, bag.size());
    }

    public void testBagSize_6_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        bag.add((T) "B");
        assertEquals("Should have 5 total items", 5, bag.size());
    }

    public void testBagSize_7_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        bag.add((T) "B");
        bag.remove("A", 2);
        assertEquals("Should have 1 'A'", 1, bag.getCount("A"));
    }

    public void testBagSize_8_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        bag.add((T) "B");
        bag.remove("A", 2);
        assertEquals("Should have 3 total items", 3, bag.size());
    }

    public void testBagSize_9_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        bag.add((T) "B");
        bag.remove("A", 2);
        bag.remove("B");
        assertEquals("Should have 1 total item", 1, bag.size());
    }

    public void testBagRetainAll_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        bag.add((T) "B");
        bag.add((T) "C");
        final List<String> retains = new ArrayList<>();
        retains.add("B");
        retains.add("C");
        bag.retainAll(retains);
        assertEquals("Should have 2 total items", 2, bag.size());
    }

    public void testBagIterator_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        assertEquals("Bag should have 3 items", 3, bag.size());
    }

    public void testBagIterator_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        final Iterator<T> i = bag.iterator();

        boolean foundA = false;
        while (i.hasNext()) {
            final String element = (String) i.next();
            if (element.equals("A")) {
                if (!foundA) {
                    foundA = true;
                } else {
                    i.remove();
                }
            }
        }

        assertTrue("Bag should still contain 'A'", bag.contains("A"));
    }

    public void testBagIterator_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        final Iterator<T> i = bag.iterator();

        boolean foundA = false;
        while (i.hasNext()) {
            final String element = (String) i.next();
            if (element.equals("A")) {
                if (!foundA) {
                    foundA = true;
                } else {
                    i.remove();
                }
            }
        }

        assertEquals("Bag should have 2 items", 2, bag.size());
    }

    public void testBagIterator_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        final Iterator<T> i = bag.iterator();

        boolean foundA = false;
        while (i.hasNext()) {
            final String element = (String) i.next();
            if (element.equals("A")) {
                if (!foundA) {
                    foundA = true;
                } else {
                    i.remove();
                }
            }
        }

        assertEquals("Bag should have 1 'A'", 1, bag.getCount("A"));
    }

    public void testBagIteratorFailDoubleRemove_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        final Iterator<T> it = bag.iterator();
        it.next();
        it.next();
        assertEquals(3, bag.size());
    }

    public void testBagIteratorFailDoubleRemove_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        final Iterator<T> it = bag.iterator();
        it.next();
        it.next();
        it.remove();
        assertEquals(2, bag.size());
    }

    public void testBagIteratorFailDoubleRemove_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        final Iterator<T> it = bag.iterator();
        it.next();
        it.next();
        it.remove();
        try {
            it.remove();
        } catch (final IllegalStateException ex) {
        }
        assertEquals(2, bag.size());
    }

    public void testBagIteratorFailDoubleRemove_5_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        final Iterator<T> it = bag.iterator();
        it.next();
        it.next();
        it.remove();
        try {
            it.remove();
        } catch (final IllegalStateException ex) {
        }
        it.next();
        it.remove();
        assertEquals(1, bag.size());
    }

    public void testBagIteratorRemoveProtectsInvariants_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        assertEquals(2, bag.size());
    }

    public void testBagIteratorRemoveProtectsInvariants_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        final Iterator<T> it = bag.iterator();
        assertEquals("A", it.next());
    }

    public void testBagIteratorRemoveProtectsInvariants_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        final Iterator<T> it = bag.iterator();
        assertEquals(true, it.hasNext());
    }

    public void testBagIteratorRemoveProtectsInvariants_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        final Iterator<T> it = bag.iterator();
        it.remove();
        assertEquals(1, bag.size());
    }

    public void testBagIteratorRemoveProtectsInvariants_5_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        final Iterator<T> it = bag.iterator();
        it.remove();
        assertEquals(true, it.hasNext());
    }

    public void testBagIteratorRemoveProtectsInvariants_6_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        final Iterator<T> it = bag.iterator();
        it.remove();
        assertEquals("A", it.next());
    }

    public void testBagIteratorRemoveProtectsInvariants_7_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        final Iterator<T> it = bag.iterator();
        it.remove();
        assertEquals(false, it.hasNext());
    }

    public void testBagIteratorRemoveProtectsInvariants_8_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        final Iterator<T> it = bag.iterator();
        it.remove();
        it.remove();
        assertEquals(0, bag.size());
    }

    public void testBagIteratorRemoveProtectsInvariants_9_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        final Iterator<T> it = bag.iterator();
        it.remove();
        it.remove();
        assertEquals(false, it.hasNext());
    }

    public void testBagIteratorRemoveProtectsInvariants_10_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        final Iterator<T> it = bag.iterator();
        it.remove();
        it.remove();

        final Iterator<T> it2 = bag.iterator();
        assertEquals(false, it2.hasNext());
    }

    public void testBagToArray_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        bag.add((T) "B");
        bag.add((T) "C");
        final Object[] array = bag.toArray();
        int a = 0, b = 0, c = 0;
        for (final Object element : array) {
            a += element.equals("A") ? 1 : 0;
            b += element.equals("B") ? 1 : 0;
            c += element.equals("C") ? 1 : 0;
        }
        assertEquals(2, a);
    }

    public void testBagToArray_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        bag.add((T) "B");
        bag.add((T) "C");
        final Object[] array = bag.toArray();
        int a = 0, b = 0, c = 0;
        for (final Object element : array) {
            a += element.equals("A") ? 1 : 0;
            b += element.equals("B") ? 1 : 0;
            c += element.equals("C") ? 1 : 0;
        }
        assertEquals(2, b);
    }

    public void testBagToArray_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        bag.add((T) "B");
        bag.add((T) "C");
        final Object[] array = bag.toArray();
        int a = 0, b = 0, c = 0;
        for (final Object element : array) {
            a += element.equals("A") ? 1 : 0;
            b += element.equals("B") ? 1 : 0;
            c += element.equals("C") ? 1 : 0;
        }
        assertEquals(1, c);
    }

    public void testBagToArrayPopulate_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        bag.add((T) "B");
        bag.add((T) "C");
        final String[] array = bag.toArray(new String[0]);
        int a = 0, b = 0, c = 0;
        for (final String element : array) {
            a += element.equals("A") ? 1 : 0;
            b += element.equals("B") ? 1 : 0;
            c += element.equals("C") ? 1 : 0;
        }
        assertEquals(2, a);
    }

    public void testBagToArrayPopulate_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        bag.add((T) "B");
        bag.add((T) "C");
        final String[] array = bag.toArray(new String[0]);
        int a = 0, b = 0, c = 0;
        for (final String element : array) {
            a += element.equals("A") ? 1 : 0;
            b += element.equals("B") ? 1 : 0;
            c += element.equals("C") ? 1 : 0;
        }
        assertEquals(2, b);
    }

    public void testBagToArrayPopulate_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        bag.add((T) "B");
        bag.add((T) "C");
        final String[] array = bag.toArray(new String[0]);
        int a = 0, b = 0, c = 0;
        for (final String element : array) {
            a += element.equals("A") ? 1 : 0;
            b += element.equals("B") ? 1 : 0;
            c += element.equals("C") ? 1 : 0;
        }
        assertEquals(1, c);
    }

    public void testBagEquals_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final Bag<T> bag2 = makeObject();
        assertEquals(true, bag.equals(bag2));
    }

    public void testBagEquals_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final Bag<T> bag2 = makeObject();
        bag.add((T) "A");
        assertEquals(false, bag.equals(bag2));
    }

    public void testBagEquals_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final Bag<T> bag2 = makeObject();
        bag.add((T) "A");
        bag2.add((T) "A");
        assertEquals(true, bag.equals(bag2));
    }

    public void testBagEquals_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final Bag<T> bag2 = makeObject();
        bag.add((T) "A");
        bag2.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        bag.add((T) "B");
        bag.add((T) "C");
        bag2.add((T) "A");
        bag2.add((T) "B");
        bag2.add((T) "B");
        bag2.add((T) "C");
        assertEquals(true, bag.equals(bag2));
    }

    public void testBagEqualsHashBag_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final Bag<T> bag2 = new HashBag<>();
        assertEquals(true, bag.equals(bag2));
    }

    public void testBagEqualsHashBag_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final Bag<T> bag2 = new HashBag<>();
        bag.add((T) "A");
        assertEquals(false, bag.equals(bag2));
    }

    public void testBagEqualsHashBag_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final Bag<T> bag2 = new HashBag<>();
        bag.add((T) "A");
        bag2.add((T) "A");
        assertEquals(true, bag.equals(bag2));
    }

    public void testBagEqualsHashBag_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final Bag<T> bag2 = new HashBag<>();
        bag.add((T) "A");
        bag2.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        bag.add((T) "B");
        bag.add((T) "C");
        bag2.add((T) "A");
        bag2.add((T) "B");
        bag2.add((T) "B");
        bag2.add((T) "C");
        assertEquals(true, bag.equals(bag2));
    }

    public void testBagHashCode_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final Bag<T> bag2 = makeObject();
        assertEquals(0, bag.hashCode());
    }

    public void testBagHashCode_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final Bag<T> bag2 = makeObject();
        assertEquals(0, bag2.hashCode());
    }

    public void testBagHashCode_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final Bag<T> bag2 = makeObject();
        assertEquals(bag.hashCode(), bag2.hashCode());
    }

    public void testBagHashCode_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final Bag<T> bag2 = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        bag.add((T) "B");
        bag.add((T) "C");
        bag2.add((T) "A");
        bag2.add((T) "A");
        bag2.add((T) "B");
        bag2.add((T) "B");
        bag2.add((T) "C");
        assertEquals(bag.hashCode(), bag2.hashCode());
    }

    public void testBagHashCode_5_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final Bag<T> bag2 = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        bag.add((T) "B");
        bag.add((T) "C");
        bag2.add((T) "A");
        bag2.add((T) "A");
        bag2.add((T) "B");
        bag2.add((T) "B");
        bag2.add((T) "C");

        int total = 0;
        total += "A".hashCode() ^ 2;
        total += "B".hashCode() ^ 2;
        total += "C".hashCode() ^ 1;
        assertEquals(total, bag.hashCode());
    }

    public void testBagHashCode_6_oe() {
        if (!isAddSupported()) {
            return;
        }

        final Bag<T> bag = makeObject();
        final Bag<T> bag2 = makeObject();
        bag.add((T) "A");
        bag.add((T) "A");
        bag.add((T) "B");
        bag.add((T) "B");
        bag.add((T) "C");
        bag2.add((T) "A");
        bag2.add((T) "A");
        bag2.add((T) "B");
        bag2.add((T) "B");
        bag2.add((T) "C");

        int total = 0;
        total += "A".hashCode() ^ 2;
        total += "B".hashCode() ^ 2;
        total += "C".hashCode() ^ 1;
        assertEquals(total, bag2.hashCode());
    }

    public void testEmptyBagCompatibility_1_oe() throws IOException, ClassNotFoundException {
        final Bag<T> bag = makeObject();
        if (bag instanceof Serializable && !skipSerializedCanonicalTests() && isTestSerialization()) {
            final Bag<?> bag2 = (Bag<?>) readExternalFormFromDisk(getCanonicalEmptyCollectionName(bag));
            assertTrue("Bag is empty",bag2.size()  == 0);
    }
    }

    public void testEmptyBagCompatibility_2_oe() throws IOException, ClassNotFoundException {
        final Bag<T> bag = makeObject();
        if (bag instanceof Serializable && !skipSerializedCanonicalTests() && isTestSerialization()) {
            final Bag<?> bag2 = (Bag<?>) readExternalFormFromDisk(getCanonicalEmptyCollectionName(bag));
            assertEquals(bag, bag2);
    }
    }

    public void testFullBagCompatibility_1_oe() throws IOException, ClassNotFoundException {
        final Bag<T> bag = makeFullCollection();
        if (bag instanceof Serializable && !skipSerializedCanonicalTests() && isTestSerialization()) {
            final Bag<?> bag2 = (Bag<?>) readExternalFormFromDisk(getCanonicalFullCollectionName(bag));
            assertEquals("Bag is the right size",bag.size(), bag2.size());
    }
    }

    public void testFullBagCompatibility_2_oe() throws IOException, ClassNotFoundException {
        final Bag<T> bag = makeFullCollection();
        if (bag instanceof Serializable && !skipSerializedCanonicalTests() && isTestSerialization()) {
            final Bag<?> bag2 = (Bag<?>) readExternalFormFromDisk(getCanonicalFullCollectionName(bag));
            assertEquals(bag, bag2);
    }
    }

}
