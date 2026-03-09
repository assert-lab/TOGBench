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
package org.apache.commons.collections4.multiset;

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

import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.collection.AbstractCollectionTest;
import org.apache.commons.collections4.set.AbstractSetTest;

/**
 * Abstract test class for {@link org.apache.commons.collections4.MultiSet MultiSet}
 * methods and contracts.
 * <p>
 * To use, simply extend this class, and implement
 * the {@link #makeObject} method.
 * <p>
 * If your multiset fails one of these tests by design,
 * you may still use this base set of cases.  Simply override the
 * test case (method) your multiset fails.
 * <p>
 * This abstract test class does wrap the concrete multiset implementation
 * with such a decorator, see the overridden {@link #resetEmpty()} and
 * {@link #resetFull()} methods.
 * <p>
 * In addition to the generic collection tests (prefix testCollection) inherited
 * from AbstractCollectionTest, there are test methods that test the "normal" MultiSet
 * interface (prefix testMultiSet). For MultiSet specific tests use the {@link #makeObject()} and
 * {@link #makeFullCollection()} methods instead of {@link #resetEmpty()} and resetFull().
 *
 * @since 4.1
 */
public abstract class AbstractMultiSetTest_OE25Dev<T> extends AbstractCollectionTest<T> {

    /**
     * JUnit constructor.
     *
     * @param testName  the test class name
     */
    public AbstractMultiSetTest_OE25Dev(final String testName) {
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
     * Return a new, empty multiset to used for testing.
     *
     * @return the multiset to be tested
     */
    @Override
    public abstract MultiSet<T> makeObject();

    /**
     * {@inheritDoc}
     */
    @Override
    public MultiSet<T> makeFullCollection() {
        final MultiSet<T> multiset = makeObject();
        multiset.addAll(Arrays.asList(getFullElements()));
        return multiset;
    }

    //-----------------------------------------------------------------------

    @Override
    public void resetEmpty() {
        this.setCollection(makeObject());
        this.setConfirmed(makeConfirmedCollection());
    }

    @Override
    public void resetFull() {
        this.setCollection(makeFullCollection());
        this.setConfirmed(makeConfirmedFullCollection());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the {@link #collection} field cast to a {@link MultiSet}.
     *
     * @return the collection field as a MultiSet
     */
    @Override
    public MultiSet<T> getCollection() {
        return (MultiSet<T>) super.getCollection();
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public void testMultiSetAdd() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        assertTrue("Should contain 'A'", multiset.contains("A"));
        assertEquals("Should have count of 1", 1, multiset.getCount("A"));
        multiset.add((T) "A");
        assertTrue("Should contain 'A'", multiset.contains("A"));
        assertEquals("Should have count of 2", 2, multiset.getCount("A"));
        multiset.add((T) "B");
        assertTrue(multiset.contains("A"));
        assertTrue(multiset.contains("B"));
    }

    @SuppressWarnings("unchecked")
    public void testMultiSetEqualsSelf() {
        final MultiSet<T> multiset = makeObject();
        assertTrue(multiset.equals(multiset));

        if (!isAddSupported()) {
            return;
        }

        multiset.add((T) "elt");
        assertTrue(multiset.equals(multiset));
        multiset.add((T) "elt"); // again
        assertTrue(multiset.equals(multiset));
        multiset.add((T) "elt2");
        assertTrue(multiset.equals(multiset));
    }

    @SuppressWarnings("unchecked")
    public void testMultiSetRemove() {
        if (!isRemoveSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        assertEquals("Should have count of 1", 1, multiset.getCount("A"));
        multiset.remove("A");
        assertEquals("Should have count of 0", 0, multiset.getCount("A"));
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        assertEquals("Should have count of 4", 4, multiset.getCount("A"));
        multiset.remove("A", 0);
        assertEquals("Should have count of 4", 4, multiset.getCount("A"));
        multiset.remove("A", 2);
        assertEquals("Should have count of 2", 2, multiset.getCount("A"));
        multiset.remove("A");
        assertEquals("Should have count of 1", 1, multiset.getCount("A"));
    }

    @SuppressWarnings("unchecked")
    public void testMultiSetRemoveAll() {
        if (!isRemoveSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A", 2);
        assertEquals("Should have count of 2", 2, multiset.getCount("A"));
        multiset.add((T) "B");
        multiset.add((T) "C");
        assertEquals("Should have count of 4", 4, multiset.size());
        final List<String> delete = new ArrayList<>();
        delete.add("A");
        delete.add("B");
        multiset.removeAll(delete);
        assertEquals("Should have count of 0", 0, multiset.getCount("A"));
        assertEquals("Should have count of 0", 0, multiset.getCount("B"));
        assertEquals("Should have count of 1", 1, multiset.getCount("C"));
        assertEquals("Should have count of 1", 1, multiset.size());
    }

    @SuppressWarnings("unchecked")
    public void testMultiSetContains() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();

        assertEquals("MultiSet does not have at least 1 'A'", false, multiset.contains("A"));
        assertEquals("MultiSet does not have at least 1 'B'", false, multiset.contains("B"));

        multiset.add((T) "A");  // multiset 1A
        assertEquals("MultiSet has at least 1 'A'", true, multiset.contains("A"));
        assertEquals("MultiSet does not have at least 1 'B'", false, multiset.contains("B"));

        multiset.add((T) "A");  // multiset 2A
        assertEquals("MultiSet has at least 1 'A'", true, multiset.contains("A"));
        assertEquals("MultiSet does not have at least 1 'B'", false, multiset.contains("B"));

        multiset.add((T) "B");  // multiset 2A,1B
        assertEquals("MultiSet has at least 1 'A'", true, multiset.contains("A"));
        assertEquals("MultiSet has at least 1 'B'", true, multiset.contains("B"));
    }

    @SuppressWarnings("unchecked")
    public void testMultiSetContainsAll() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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

        assertEquals("MultiSet containsAll of empty", true, multiset.containsAll(known));
        assertEquals("MultiSet does not containsAll of 1 'A'", false, multiset.containsAll(known1A));
        assertEquals("MultiSet does not containsAll of 2 'A'", false, multiset.containsAll(known2A));
        assertEquals("MultiSet does not containsAll of 1 'B'", false, multiset.containsAll(known1B));
        assertEquals("MultiSet does not containsAll of 1 'A' 1 'B'", false, multiset.containsAll(known1A1B));

        multiset.add((T) "A");  // multiset 1A
        assertEquals("MultiSet containsAll of empty", true, multiset.containsAll(known));
        assertEquals("MultiSet containsAll of 1 'A'", true, multiset.containsAll(known1A));
        assertEquals("MultiSet does not containsAll 'A'", true, multiset.containsAll(known2A));
        assertEquals("MultiSet does not containsAll of 1 'B'", false, multiset.containsAll(known1B));
        assertEquals("MultiSet does not containsAll of 1 'A' 1 'B'", false, multiset.containsAll(known1A1B));

        multiset.add((T) "A");  // multiset 2A
        assertEquals("MultiSet containsAll of empty", true, multiset.containsAll(known));
        assertEquals("MultiSet containsAll of 1 'A'", true, multiset.containsAll(known1A));
        assertEquals("MultiSet containsAll of 2 'A'", true, multiset.containsAll(known2A));
        assertEquals("MultiSet does not containsAll of 1 'B'", false, multiset.containsAll(known1B));
        assertEquals("MultiSet does not containsAll of 1 'A' 1 'B'", false, multiset.containsAll(known1A1B));

        multiset.add((T) "A");  // multiset 3A
        assertEquals("MultiSet containsAll of empty", true, multiset.containsAll(known));
        assertEquals("MultiSet containsAll of 1 'A'", true, multiset.containsAll(known1A));
        assertEquals("MultiSet containsAll of 2 'A'", true, multiset.containsAll(known2A));
        assertEquals("MultiSet does not containsAll of 1 'B'", false, multiset.containsAll(known1B));
        assertEquals("MultiSet does not containsAll of 1 'A' 1 'B'", false, multiset.containsAll(known1A1B));

        multiset.add((T) "B");  // multiset 3A1B
        assertEquals("MultiSet containsAll of empty", true, multiset.containsAll(known));
        assertEquals("MultiSet containsAll of 1 'A'", true, multiset.containsAll(known1A));
        assertEquals("MultiSet containsAll of 2 'A'", true, multiset.containsAll(known2A));
        assertEquals("MultiSet containsAll of 1 'B'", true, multiset.containsAll(known1B));
        assertEquals("MultiSet containsAll of 1 'A' 1 'B'", true, multiset.containsAll(known1A1B));
    }

    @SuppressWarnings("unchecked")
    public void testMultiSetSize() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        assertEquals("Should have 0 total items", 0, multiset.size());
        multiset.add((T) "A");
        assertEquals("Should have 1 total items", 1, multiset.size());
        multiset.add((T) "A");
        assertEquals("Should have 2 total items", 2, multiset.size());
        multiset.add((T) "A");
        assertEquals("Should have 3 total items", 3, multiset.size());
        multiset.add((T) "B");
        assertEquals("Should have 4 total items", 4, multiset.size());
        multiset.add((T) "B");
        assertEquals("Should have 5 total items", 5, multiset.size());
        multiset.remove("A", 2);
        assertEquals("Should have 1 'A'", 1, multiset.getCount("A"));
        assertEquals("Should have 3 total items", 3, multiset.size());
        multiset.remove("B");
        assertEquals("Should have 2 total item", 2, multiset.size());
    }

    @SuppressWarnings("unchecked")
    public void testMultiSetRetainAll() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        final List<String> retains = new ArrayList<>();
        retains.add("B");
        retains.add("C");
        multiset.retainAll(retains);
        assertEquals("Should have 3 total items", 3, multiset.size());
    }

    @SuppressWarnings("unchecked")
    public void testMultiSetIterator() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        assertEquals("MultiSet should have 3 items", 3, multiset.size());
        final Iterator<T> i = multiset.iterator();

        boolean foundA = false;
        while (i.hasNext()) {
            final String element = (String) i.next();
            // ignore the first A, remove the second via Iterator.remove()
            if (element.equals("A")) {
                if (!foundA) {
                    foundA = true;
                } else {
                    i.remove();
                }
            }
        }

        assertTrue("MultiSet should still contain 'A'", multiset.contains("A"));
        assertEquals("MultiSet should have 2 items", 2, multiset.size());
        assertEquals("MultiSet should have 1 'A'", 1, multiset.getCount("A"));
    }

    @SuppressWarnings("unchecked")
    public void testMultiSetIteratorFail() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        final Iterator<T> it = multiset.iterator();
        it.next();
        multiset.remove("A");
        try {
            it.next();
            fail("Should throw ConcurrentModificationException");
        } catch (final ConcurrentModificationException e) {
            // expected
        }
    }

    @SuppressWarnings("unchecked")
    public void testMultiSetIteratorFailNoMore() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        final Iterator<T> it = multiset.iterator();
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

    @SuppressWarnings("unchecked")
    public void testMultiSetIteratorFailDoubleRemove() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        final Iterator<T> it = multiset.iterator();
        it.next();
        it.next();
        assertEquals(3, multiset.size());
        it.remove();
        assertEquals(2, multiset.size());
        try {
            it.remove();
            fail("Should throw IllegalStateException");
        } catch (final IllegalStateException ex) {
            // expected
        }
        assertEquals(2, multiset.size());
        it.next();
        it.remove();
        assertEquals(1, multiset.size());
    }

    @SuppressWarnings("unchecked")
    public void testMultiSetIteratorRemoveProtectsInvariants() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        assertEquals(2, multiset.size());
        final Iterator<T> it = multiset.iterator();
        assertEquals("A", it.next());
        assertEquals(true, it.hasNext());
        it.remove();
        assertEquals(1, multiset.size());
        assertEquals(true, it.hasNext());
        assertEquals("A", it.next());
        assertEquals(false, it.hasNext());
        it.remove();
        assertEquals(0, multiset.size());
        assertEquals(false, it.hasNext());

        final Iterator<T> it2 = multiset.iterator();
        assertEquals(false, it2.hasNext());
    }

    @SuppressWarnings("unchecked")
    public void testMultiSetEntrySetUpdatedToZero() {
        if (!isAddSupported()) {
            return;
        }
        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        final MultiSet.Entry<T> entry = multiset.entrySet().iterator().next();
        assertEquals(2, entry.getCount());
        multiset.remove((T) "A");
        assertEquals(1, entry.getCount());
        multiset.remove((T) "A");
        assertEquals(0, entry.getCount());
    }

    @SuppressWarnings("unchecked")
    public void testMultiSetToArray() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        final Object[] array = multiset.toArray();
        int a = 0, b = 0, c = 0;
        for (final Object element : array) {
            a += element.equals("A") ? 1 : 0;
            b += element.equals("B") ? 1 : 0;
            c += element.equals("C") ? 1 : 0;
        }
        assertEquals(2, a);
        assertEquals(2, b);
        assertEquals(1, c);
    }

    @SuppressWarnings("unchecked")
    public void testMultiSetToArrayPopulate() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        final String[] array = multiset.toArray(new String[0]);
        int a = 0, b = 0, c = 0;
        for (final String element : array) {
            a += element.equals("A") ? 1 : 0;
            b += element.equals("B") ? 1 : 0;
            c += element.equals("C") ? 1 : 0;
        }
        assertEquals(2, a);
        assertEquals(2, b);
        assertEquals(1, c);
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public void testMultiSetEquals() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        final MultiSet<T> multiset2 = makeObject();
        assertEquals(true, multiset.equals(multiset2));
        multiset.add((T) "A");
        assertEquals(false, multiset.equals(multiset2));
        multiset2.add((T) "A");
        assertEquals(true, multiset.equals(multiset2));
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        multiset2.add((T) "A");
        multiset2.add((T) "B");
        multiset2.add((T) "B");
        multiset2.add((T) "C");
        assertEquals(true, multiset.equals(multiset2));
    }

    @SuppressWarnings("unchecked")
    public void testMultiSetEqualsHashMultiSet() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        final MultiSet<T> multiset2 = new HashMultiSet<>();
        assertEquals(true, multiset.equals(multiset2));
        multiset.add((T) "A");
        assertEquals(false, multiset.equals(multiset2));
        multiset2.add((T) "A");
        assertEquals(true, multiset.equals(multiset2));
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        multiset2.add((T) "A");
        multiset2.add((T) "B");
        multiset2.add((T) "B");
        multiset2.add((T) "C");
        assertEquals(true, multiset.equals(multiset2));
    }

    @SuppressWarnings("unchecked")
    public void testMultiSetHashCode() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        final MultiSet<T> multiset2 = makeObject();
        assertEquals(0, multiset.hashCode());
        assertEquals(0, multiset2.hashCode());
        assertEquals(multiset.hashCode(), multiset2.hashCode());
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        multiset2.add((T) "A");
        multiset2.add((T) "A");
        multiset2.add((T) "B");
        multiset2.add((T) "B");
        multiset2.add((T) "C");
        assertEquals(multiset.hashCode(), multiset2.hashCode());

        int total = 0;
        total += "A".hashCode() ^ 2;
        total += "B".hashCode() ^ 2;
        total += "C".hashCode() ^ 1;
        assertEquals(total, multiset.hashCode());
        assertEquals(total, multiset2.hashCode());
    }

    //-----------------------------------------------------------------------

    /**
     * Bulk test {@link MultiSet#uniqueSet()}.  This method runs through all of
     * the tests in {@link AbstractSetTest}.
     * After modification operations, {@link #verify()} is invoked to ensure
     * that the multiset and the other collection views are still valid.
     *
     * @return a {@link AbstractSetTest} instance for testing the multiset's unique set
     */
    public BulkTest bulkTestMultiSetUniqueSet() {
        return new TestMultiSetUniqueSet();
    }

    public class TestMultiSetUniqueSet extends AbstractSetTest<T> {
        public TestMultiSetUniqueSet() {
            super("");
        }

        @Override
        public T[] getFullElements() {
            return AbstractMultiSetTest_OE25Dev.this.getFullElements();
        }

        @Override
        public T[] getOtherElements() {
            return AbstractMultiSetTest_OE25Dev.this.getOtherElements();
        }

        @Override
        public Set<T> makeObject() {
            return AbstractMultiSetTest_OE25Dev.this.makeObject().uniqueSet();
        }

        @Override
        public Set<T> makeFullCollection() {
            return AbstractMultiSetTest_OE25Dev.this.makeFullCollection().uniqueSet();
        }

        @Override
        public boolean isNullSupported() {
            return AbstractMultiSetTest_OE25Dev.this.isNullSupported();
        }

        @Override
        public boolean isAddSupported() {
            return false;
        }

        @Override
        public boolean isRemoveSupported() {
            return AbstractMultiSetTest_OE25Dev.this.isRemoveSupported();
        }

        @Override
        public boolean isTestSerialization() {
            return false;
        }

        @Override
        public void resetEmpty() {
            AbstractMultiSetTest_OE25Dev.this.resetEmpty();
            TestMultiSetUniqueSet.this.setCollection(AbstractMultiSetTest_OE25Dev.this.getCollection().uniqueSet());
            TestMultiSetUniqueSet.this.setConfirmed(new HashSet<>(AbstractMultiSetTest_OE25Dev.this.getConfirmed()));
        }

        @Override
        public void resetFull() {
            AbstractMultiSetTest_OE25Dev.this.resetFull();
            TestMultiSetUniqueSet.this.setCollection(AbstractMultiSetTest_OE25Dev.this.getCollection().uniqueSet());
            TestMultiSetUniqueSet.this.setConfirmed(new HashSet<>(AbstractMultiSetTest_OE25Dev.this.getConfirmed()));
        }

        @Override
        public void verify() {
            super.verify();
        }
    }

    //-----------------------------------------------------------------------

    /**
     * Compare the current serialized form of the MultiSet
     * against the canonical version in SVN.
     */
    public void testEmptyMultiSetCompatibility() throws IOException, ClassNotFoundException {
        // test to make sure the canonical form has been preserved
        final MultiSet<T> multiset = makeObject();
        if (multiset instanceof Serializable && !skipSerializedCanonicalTests() && isTestSerialization()) {
            final MultiSet<?> multiset2 = (MultiSet<?>) readExternalFormFromDisk(getCanonicalEmptyCollectionName(multiset));
            assertTrue("MultiSet is empty",multiset2.size()  == 0);
            assertEquals(multiset, multiset2);
        }
    }

    /**
     * Compare the current serialized form of the MultiSet
     * against the canonical version in SVN.
     */
    public void testFullMultiSetCompatibility() throws IOException, ClassNotFoundException {
        // test to make sure the canonical form has been preserved
        final MultiSet<T> multiset = makeFullCollection();
        if (multiset instanceof Serializable && !skipSerializedCanonicalTests() && isTestSerialization()) {
            final MultiSet<?> multiset2 = (MultiSet<?>) readExternalFormFromDisk(getCanonicalFullCollectionName(multiset));
            assertEquals("MultiSet is the right size",multiset.size(), multiset2.size());
            assertEquals(multiset, multiset2);
        }
    }

    public void testMultiSetAdd_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        assertTrue("Should contain 'A'", multiset.contains("A"));
    }

    public void testMultiSetAdd_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        assertEquals("Should have count of 1", 1, multiset.getCount("A"));
    }

    public void testMultiSetAdd_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        assertTrue("Should contain 'A'", multiset.contains("A"));
    }

    public void testMultiSetAdd_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        assertEquals("Should have count of 2", 2, multiset.getCount("A"));
    }

    public void testMultiSetAdd_5_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        assertTrue(multiset.contains("A"));
    }

    public void testMultiSetAdd_6_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        assertTrue(multiset.contains("B"));
    }

    public void testMultiSetEqualsSelf_1_oe() {
        final MultiSet<T> multiset = makeObject();
        assertTrue(multiset.equals(multiset));
    }

    public void testMultiSetEqualsSelf_2_oe() {
        final MultiSet<T> multiset = makeObject();

        if (!isAddSupported()) {
            return;
        }

        multiset.add((T) "elt");
        assertTrue(multiset.equals(multiset));
    }

    public void testMultiSetEqualsSelf_3_oe() {
        final MultiSet<T> multiset = makeObject();

        if (!isAddSupported()) {
            return;
        }

        multiset.add((T) "elt");
        multiset.add((T) "elt"); // again
        assertTrue(multiset.equals(multiset));
    }

    public void testMultiSetEqualsSelf_4_oe() {
        final MultiSet<T> multiset = makeObject();

        if (!isAddSupported()) {
            return;
        }

        multiset.add((T) "elt");
        multiset.add((T) "elt"); // again
        multiset.add((T) "elt2");
        assertTrue(multiset.equals(multiset));
    }

    public void testMultiSetRemove_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        assertEquals("Should have count of 1", 1, multiset.getCount("A"));
    }

    public void testMultiSetRemove_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.remove("A");
        assertEquals("Should have count of 0", 0, multiset.getCount("A"));
    }

    public void testMultiSetRemove_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.remove("A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        assertEquals("Should have count of 4", 4, multiset.getCount("A"));
    }

    public void testMultiSetRemove_4_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.remove("A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.remove("A", 0);
        assertEquals("Should have count of 4", 4, multiset.getCount("A"));
    }

    public void testMultiSetRemove_5_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.remove("A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.remove("A", 0);
        multiset.remove("A", 2);
        assertEquals("Should have count of 2", 2, multiset.getCount("A"));
    }

    public void testMultiSetRemove_6_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.remove("A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.remove("A", 0);
        multiset.remove("A", 2);
        multiset.remove("A");
        assertEquals("Should have count of 1", 1, multiset.getCount("A"));
    }

    public void testMultiSetRemoveAll_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A", 2);
        assertEquals("Should have count of 2", 2, multiset.getCount("A"));
    }

    public void testMultiSetRemoveAll_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A", 2);
        multiset.add((T) "B");
        multiset.add((T) "C");
        assertEquals("Should have count of 4", 4, multiset.size());
    }

    public void testMultiSetRemoveAll_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A", 2);
        multiset.add((T) "B");
        multiset.add((T) "C");
        final List<String> delete = new ArrayList<>();
        delete.add("A");
        delete.add("B");
        multiset.removeAll(delete);
        assertEquals("Should have count of 0", 0, multiset.getCount("A"));
    }

    public void testMultiSetRemoveAll_4_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A", 2);
        multiset.add((T) "B");
        multiset.add((T) "C");
        final List<String> delete = new ArrayList<>();
        delete.add("A");
        delete.add("B");
        multiset.removeAll(delete);
        assertEquals("Should have count of 0", 0, multiset.getCount("B"));
    }

    public void testMultiSetRemoveAll_5_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A", 2);
        multiset.add((T) "B");
        multiset.add((T) "C");
        final List<String> delete = new ArrayList<>();
        delete.add("A");
        delete.add("B");
        multiset.removeAll(delete);
        assertEquals("Should have count of 1", 1, multiset.getCount("C"));
    }

    public void testMultiSetRemoveAll_6_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A", 2);
        multiset.add((T) "B");
        multiset.add((T) "C");
        final List<String> delete = new ArrayList<>();
        delete.add("A");
        delete.add("B");
        multiset.removeAll(delete);
        assertEquals("Should have count of 1", 1, multiset.size());
    }

    public void testMultiSetContains_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();

        assertEquals("MultiSet does not have at least 1 'A'", false, multiset.contains("A"));
    }

    public void testMultiSetContains_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();

        assertEquals("MultiSet does not have at least 1 'B'", false, multiset.contains("B"));
    }

    public void testMultiSetContains_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();


        multiset.add((T) "A");  // multiset 1A
        assertEquals("MultiSet has at least 1 'A'", true, multiset.contains("A"));
    }

    public void testMultiSetContains_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();


        multiset.add((T) "A");  // multiset 1A
        assertEquals("MultiSet does not have at least 1 'B'", false, multiset.contains("B"));
    }

    public void testMultiSetContains_5_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A
        assertEquals("MultiSet has at least 1 'A'", true, multiset.contains("A"));
    }

    public void testMultiSetContains_6_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A
        assertEquals("MultiSet does not have at least 1 'B'", false, multiset.contains("B"));
    }

    public void testMultiSetContains_7_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A

        multiset.add((T) "B");  // multiset 2A,1B
        assertEquals("MultiSet has at least 1 'A'", true, multiset.contains("A"));
    }

    public void testMultiSetContains_8_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A

        multiset.add((T) "B");  // multiset 2A,1B
        assertEquals("MultiSet has at least 1 'B'", true, multiset.contains("B"));
    }

    public void testMultiSetContainsAll_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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

        assertEquals("MultiSet containsAll of empty", true, multiset.containsAll(known));
    }

    public void testMultiSetContainsAll_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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

        assertEquals("MultiSet does not containsAll of 1 'A'", false, multiset.containsAll(known1A));
    }

    public void testMultiSetContainsAll_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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

        assertEquals("MultiSet does not containsAll of 2 'A'", false, multiset.containsAll(known2A));
    }

    public void testMultiSetContainsAll_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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

        assertEquals("MultiSet does not containsAll of 1 'B'", false, multiset.containsAll(known1B));
    }

    public void testMultiSetContainsAll_5_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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

        assertEquals("MultiSet does not containsAll of 1 'A' 1 'B'", false, multiset.containsAll(known1A1B));
    }

    public void testMultiSetContainsAll_6_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A
        assertEquals("MultiSet containsAll of empty", true, multiset.containsAll(known));
    }

    public void testMultiSetContainsAll_7_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A
        assertEquals("MultiSet containsAll of 1 'A'", true, multiset.containsAll(known1A));
    }

    public void testMultiSetContainsAll_8_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A
        assertEquals("MultiSet does not containsAll 'A'", true, multiset.containsAll(known2A));
    }

    public void testMultiSetContainsAll_9_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A
        assertEquals("MultiSet does not containsAll of 1 'B'", false, multiset.containsAll(known1B));
    }

    public void testMultiSetContainsAll_10_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A
        assertEquals("MultiSet does not containsAll of 1 'A' 1 'B'", false, multiset.containsAll(known1A1B));
    }

    public void testMultiSetContainsAll_11_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A
        assertEquals("MultiSet containsAll of empty", true, multiset.containsAll(known));
    }

    public void testMultiSetContainsAll_12_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A
        assertEquals("MultiSet containsAll of 1 'A'", true, multiset.containsAll(known1A));
    }

    public void testMultiSetContainsAll_13_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A
        assertEquals("MultiSet containsAll of 2 'A'", true, multiset.containsAll(known2A));
    }

    public void testMultiSetContainsAll_14_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A
        assertEquals("MultiSet does not containsAll of 1 'B'", false, multiset.containsAll(known1B));
    }

    public void testMultiSetContainsAll_15_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A
        assertEquals("MultiSet does not containsAll of 1 'A' 1 'B'", false, multiset.containsAll(known1A1B));
    }

    public void testMultiSetContainsAll_16_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A

        multiset.add((T) "A");  // multiset 3A
        assertEquals("MultiSet containsAll of empty", true, multiset.containsAll(known));
    }

    public void testMultiSetContainsAll_17_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A

        multiset.add((T) "A");  // multiset 3A
        assertEquals("MultiSet containsAll of 1 'A'", true, multiset.containsAll(known1A));
    }

    public void testMultiSetContainsAll_18_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A

        multiset.add((T) "A");  // multiset 3A
        assertEquals("MultiSet containsAll of 2 'A'", true, multiset.containsAll(known2A));
    }

    public void testMultiSetContainsAll_19_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A

        multiset.add((T) "A");  // multiset 3A
        assertEquals("MultiSet does not containsAll of 1 'B'", false, multiset.containsAll(known1B));
    }

    public void testMultiSetContainsAll_20_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A

        multiset.add((T) "A");  // multiset 3A
        assertEquals("MultiSet does not containsAll of 1 'A' 1 'B'", false, multiset.containsAll(known1A1B));
    }

    public void testMultiSetContainsAll_21_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A

        multiset.add((T) "A");  // multiset 3A

        multiset.add((T) "B");  // multiset 3A1B
        assertEquals("MultiSet containsAll of empty", true, multiset.containsAll(known));
    }

    public void testMultiSetContainsAll_22_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A

        multiset.add((T) "A");  // multiset 3A

        multiset.add((T) "B");  // multiset 3A1B
        assertEquals("MultiSet containsAll of 1 'A'", true, multiset.containsAll(known1A));
    }

    public void testMultiSetContainsAll_23_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A

        multiset.add((T) "A");  // multiset 3A

        multiset.add((T) "B");  // multiset 3A1B
        assertEquals("MultiSet containsAll of 2 'A'", true, multiset.containsAll(known2A));
    }

    public void testMultiSetContainsAll_24_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A

        multiset.add((T) "A");  // multiset 3A

        multiset.add((T) "B");  // multiset 3A1B
        assertEquals("MultiSet containsAll of 1 'B'", true, multiset.containsAll(known1B));
    }

    public void testMultiSetContainsAll_25_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
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


        multiset.add((T) "A");  // multiset 1A

        multiset.add((T) "A");  // multiset 2A

        multiset.add((T) "A");  // multiset 3A

        multiset.add((T) "B");  // multiset 3A1B
        assertEquals("MultiSet containsAll of 1 'A' 1 'B'", true, multiset.containsAll(known1A1B));
    }

    public void testMultiSetSize_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        assertEquals("Should have 0 total items", 0, multiset.size());
    }

    public void testMultiSetSize_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        assertEquals("Should have 1 total items", 1, multiset.size());
    }

    public void testMultiSetSize_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        assertEquals("Should have 2 total items", 2, multiset.size());
    }

    public void testMultiSetSize_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        assertEquals("Should have 3 total items", 3, multiset.size());
    }

    public void testMultiSetSize_5_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        assertEquals("Should have 4 total items", 4, multiset.size());
    }

    public void testMultiSetSize_6_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        assertEquals("Should have 5 total items", 5, multiset.size());
    }

    public void testMultiSetSize_7_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.remove("A", 2);
        assertEquals("Should have 1 'A'", 1, multiset.getCount("A"));
    }

    public void testMultiSetSize_8_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.remove("A", 2);
        assertEquals("Should have 3 total items", 3, multiset.size());
    }

    public void testMultiSetSize_9_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.remove("A", 2);
        multiset.remove("B");
        assertEquals("Should have 2 total item", 2, multiset.size());
    }

    public void testMultiSetRetainAll_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        final List<String> retains = new ArrayList<>();
        retains.add("B");
        retains.add("C");
        multiset.retainAll(retains);
        assertEquals("Should have 3 total items", 3, multiset.size());
    }

    public void testMultiSetIterator_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        assertEquals("MultiSet should have 3 items", 3, multiset.size());
    }

    public void testMultiSetIterator_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        final Iterator<T> i = multiset.iterator();

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

        assertTrue("MultiSet should still contain 'A'", multiset.contains("A"));
    }

    public void testMultiSetIterator_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        final Iterator<T> i = multiset.iterator();

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

        assertEquals("MultiSet should have 2 items", 2, multiset.size());
    }

    public void testMultiSetIterator_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        final Iterator<T> i = multiset.iterator();

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

        assertEquals("MultiSet should have 1 'A'", 1, multiset.getCount("A"));
    }

    public void testMultiSetIteratorFailDoubleRemove_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        final Iterator<T> it = multiset.iterator();
        it.next();
        it.next();
        assertEquals(3, multiset.size());
    }

    public void testMultiSetIteratorFailDoubleRemove_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        final Iterator<T> it = multiset.iterator();
        it.next();
        it.next();
        it.remove();
        assertEquals(2, multiset.size());
    }

    public void testMultiSetIteratorFailDoubleRemove_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        final Iterator<T> it = multiset.iterator();
        it.next();
        it.next();
        it.remove();
        try {
            it.remove();
        } catch (final IllegalStateException ex) {
        }
        assertEquals(2, multiset.size());
    }

    public void testMultiSetIteratorFailDoubleRemove_5_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        final Iterator<T> it = multiset.iterator();
        it.next();
        it.next();
        it.remove();
        try {
            it.remove();
        } catch (final IllegalStateException ex) {
        }
        it.next();
        it.remove();
        assertEquals(1, multiset.size());
    }

    public void testMultiSetIteratorRemoveProtectsInvariants_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        assertEquals(2, multiset.size());
    }

    public void testMultiSetIteratorRemoveProtectsInvariants_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        final Iterator<T> it = multiset.iterator();
        assertEquals("A", it.next());
    }

    public void testMultiSetIteratorRemoveProtectsInvariants_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        final Iterator<T> it = multiset.iterator();
        assertEquals(true, it.hasNext());
    }

    public void testMultiSetIteratorRemoveProtectsInvariants_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        final Iterator<T> it = multiset.iterator();
        it.remove();
        assertEquals(1, multiset.size());
    }

    public void testMultiSetIteratorRemoveProtectsInvariants_5_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        final Iterator<T> it = multiset.iterator();
        it.remove();
        assertEquals(true, it.hasNext());
    }

    public void testMultiSetIteratorRemoveProtectsInvariants_6_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        final Iterator<T> it = multiset.iterator();
        it.remove();
        assertEquals("A", it.next());
    }

    public void testMultiSetIteratorRemoveProtectsInvariants_7_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        final Iterator<T> it = multiset.iterator();
        it.remove();
        assertEquals(false, it.hasNext());
    }

    public void testMultiSetIteratorRemoveProtectsInvariants_8_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        final Iterator<T> it = multiset.iterator();
        it.remove();
        it.remove();
        assertEquals(0, multiset.size());
    }

    public void testMultiSetIteratorRemoveProtectsInvariants_9_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        final Iterator<T> it = multiset.iterator();
        it.remove();
        it.remove();
        assertEquals(false, it.hasNext());
    }

    public void testMultiSetIteratorRemoveProtectsInvariants_10_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        final Iterator<T> it = multiset.iterator();
        it.remove();
        it.remove();

        final Iterator<T> it2 = multiset.iterator();
        assertEquals(false, it2.hasNext());
    }

    public void testMultiSetEntrySetUpdatedToZero_1_oe() {
        if (!isAddSupported()) {
            return;
        }
        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        final MultiSet.Entry<T> entry = multiset.entrySet().iterator().next();
        assertEquals(2, entry.getCount());
    }

    public void testMultiSetEntrySetUpdatedToZero_2_oe() {
        if (!isAddSupported()) {
            return;
        }
        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        final MultiSet.Entry<T> entry = multiset.entrySet().iterator().next();
        multiset.remove((T) "A");
        assertEquals(1, entry.getCount());
    }

    public void testMultiSetEntrySetUpdatedToZero_3_oe() {
        if (!isAddSupported()) {
            return;
        }
        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        final MultiSet.Entry<T> entry = multiset.entrySet().iterator().next();
        multiset.remove((T) "A");
        multiset.remove((T) "A");
        assertEquals(0, entry.getCount());
    }

    public void testMultiSetToArray_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        final Object[] array = multiset.toArray();
        int a = 0, b = 0, c = 0;
        for (final Object element : array) {
            a += element.equals("A") ? 1 : 0;
            b += element.equals("B") ? 1 : 0;
            c += element.equals("C") ? 1 : 0;
        }
        assertEquals(2, a);
    }

    public void testMultiSetToArray_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        final Object[] array = multiset.toArray();
        int a = 0, b = 0, c = 0;
        for (final Object element : array) {
            a += element.equals("A") ? 1 : 0;
            b += element.equals("B") ? 1 : 0;
            c += element.equals("C") ? 1 : 0;
        }
        assertEquals(2, b);
    }

    public void testMultiSetToArray_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        final Object[] array = multiset.toArray();
        int a = 0, b = 0, c = 0;
        for (final Object element : array) {
            a += element.equals("A") ? 1 : 0;
            b += element.equals("B") ? 1 : 0;
            c += element.equals("C") ? 1 : 0;
        }
        assertEquals(1, c);
    }

    public void testMultiSetToArrayPopulate_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        final String[] array = multiset.toArray(new String[0]);
        int a = 0, b = 0, c = 0;
        for (final String element : array) {
            a += element.equals("A") ? 1 : 0;
            b += element.equals("B") ? 1 : 0;
            c += element.equals("C") ? 1 : 0;
        }
        assertEquals(2, a);
    }

    public void testMultiSetToArrayPopulate_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        final String[] array = multiset.toArray(new String[0]);
        int a = 0, b = 0, c = 0;
        for (final String element : array) {
            a += element.equals("A") ? 1 : 0;
            b += element.equals("B") ? 1 : 0;
            c += element.equals("C") ? 1 : 0;
        }
        assertEquals(2, b);
    }

    public void testMultiSetToArrayPopulate_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        final String[] array = multiset.toArray(new String[0]);
        int a = 0, b = 0, c = 0;
        for (final String element : array) {
            a += element.equals("A") ? 1 : 0;
            b += element.equals("B") ? 1 : 0;
            c += element.equals("C") ? 1 : 0;
        }
        assertEquals(1, c);
    }

    public void testMultiSetEquals_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        final MultiSet<T> multiset2 = makeObject();
        assertEquals(true, multiset.equals(multiset2));
    }

    public void testMultiSetEquals_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        final MultiSet<T> multiset2 = makeObject();
        multiset.add((T) "A");
        assertEquals(false, multiset.equals(multiset2));
    }

    public void testMultiSetEquals_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        final MultiSet<T> multiset2 = makeObject();
        multiset.add((T) "A");
        multiset2.add((T) "A");
        assertEquals(true, multiset.equals(multiset2));
    }

    public void testMultiSetEquals_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        final MultiSet<T> multiset2 = makeObject();
        multiset.add((T) "A");
        multiset2.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        multiset2.add((T) "A");
        multiset2.add((T) "B");
        multiset2.add((T) "B");
        multiset2.add((T) "C");
        assertEquals(true, multiset.equals(multiset2));
    }

    public void testMultiSetEqualsHashMultiSet_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        final MultiSet<T> multiset2 = new HashMultiSet<>();
        assertEquals(true, multiset.equals(multiset2));
    }

    public void testMultiSetEqualsHashMultiSet_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        final MultiSet<T> multiset2 = new HashMultiSet<>();
        multiset.add((T) "A");
        assertEquals(false, multiset.equals(multiset2));
    }

    public void testMultiSetEqualsHashMultiSet_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        final MultiSet<T> multiset2 = new HashMultiSet<>();
        multiset.add((T) "A");
        multiset2.add((T) "A");
        assertEquals(true, multiset.equals(multiset2));
    }

    public void testMultiSetEqualsHashMultiSet_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        final MultiSet<T> multiset2 = new HashMultiSet<>();
        multiset.add((T) "A");
        multiset2.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        multiset2.add((T) "A");
        multiset2.add((T) "B");
        multiset2.add((T) "B");
        multiset2.add((T) "C");
        assertEquals(true, multiset.equals(multiset2));
    }

    public void testMultiSetHashCode_1_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        final MultiSet<T> multiset2 = makeObject();
        assertEquals(0, multiset.hashCode());
    }

    public void testMultiSetHashCode_2_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        final MultiSet<T> multiset2 = makeObject();
        assertEquals(0, multiset2.hashCode());
    }

    public void testMultiSetHashCode_3_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        final MultiSet<T> multiset2 = makeObject();
        assertEquals(multiset.hashCode(), multiset2.hashCode());
    }

    public void testMultiSetHashCode_4_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        final MultiSet<T> multiset2 = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        multiset2.add((T) "A");
        multiset2.add((T) "A");
        multiset2.add((T) "B");
        multiset2.add((T) "B");
        multiset2.add((T) "C");
        assertEquals(multiset.hashCode(), multiset2.hashCode());
    }

    public void testMultiSetHashCode_5_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        final MultiSet<T> multiset2 = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        multiset2.add((T) "A");
        multiset2.add((T) "A");
        multiset2.add((T) "B");
        multiset2.add((T) "B");
        multiset2.add((T) "C");

        int total = 0;
        total += "A".hashCode() ^ 2;
        total += "B".hashCode() ^ 2;
        total += "C".hashCode() ^ 1;
        assertEquals(total, multiset.hashCode());
    }

    public void testMultiSetHashCode_6_oe() {
        if (!isAddSupported()) {
            return;
        }

        final MultiSet<T> multiset = makeObject();
        final MultiSet<T> multiset2 = makeObject();
        multiset.add((T) "A");
        multiset.add((T) "A");
        multiset.add((T) "B");
        multiset.add((T) "B");
        multiset.add((T) "C");
        multiset2.add((T) "A");
        multiset2.add((T) "A");
        multiset2.add((T) "B");
        multiset2.add((T) "B");
        multiset2.add((T) "C");

        int total = 0;
        total += "A".hashCode() ^ 2;
        total += "B".hashCode() ^ 2;
        total += "C".hashCode() ^ 1;
        assertEquals(total, multiset2.hashCode());
    }

    public void testEmptyMultiSetCompatibility_1_oe() throws IOException, ClassNotFoundException {
        final MultiSet<T> multiset = makeObject();
        if (multiset instanceof Serializable && !skipSerializedCanonicalTests() && isTestSerialization()) {
            final MultiSet<?> multiset2 = (MultiSet<?>) readExternalFormFromDisk(getCanonicalEmptyCollectionName(multiset));
            assertTrue("MultiSet is empty",multiset2.size()  == 0);
    }
    }

    public void testEmptyMultiSetCompatibility_2_oe() throws IOException, ClassNotFoundException {
        final MultiSet<T> multiset = makeObject();
        if (multiset instanceof Serializable && !skipSerializedCanonicalTests() && isTestSerialization()) {
            final MultiSet<?> multiset2 = (MultiSet<?>) readExternalFormFromDisk(getCanonicalEmptyCollectionName(multiset));
            assertEquals(multiset, multiset2);
    }
    }

    public void testFullMultiSetCompatibility_1_oe() throws IOException, ClassNotFoundException {
        final MultiSet<T> multiset = makeFullCollection();
        if (multiset instanceof Serializable && !skipSerializedCanonicalTests() && isTestSerialization()) {
            final MultiSet<?> multiset2 = (MultiSet<?>) readExternalFormFromDisk(getCanonicalFullCollectionName(multiset));
            assertEquals("MultiSet is the right size",multiset.size(), multiset2.size());
    }
    }

    public void testFullMultiSetCompatibility_2_oe() throws IOException, ClassNotFoundException {
        final MultiSet<T> multiset = makeFullCollection();
        if (multiset instanceof Serializable && !skipSerializedCanonicalTests() && isTestSerialization()) {
            final MultiSet<?> multiset2 = (MultiSet<?>) readExternalFormFromDisk(getCanonicalFullCollectionName(multiset));
            assertEquals(multiset, multiset2);
    }
    }

}
