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
package org.apache.commons.collections4.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.IteratorUtils;

/**
 * Extension of {@link AbstractSetTest} for exercising the
 * {@link ListOrderedSet} implementation.
 *
 * @since 3.0
 */
public class ListOrderedSetTest_OE25Dev<E>
    extends AbstractSetTest<E> {

    private static final Integer ZERO = Integer.valueOf(0);

    private static final Integer ONE = Integer.valueOf(1);

    private static final Integer TWO = Integer.valueOf(2);

    private static final Integer THREE = Integer.valueOf(3);

    public ListOrderedSetTest_OE25Dev(final String testName) {
        super(testName);
    }

    @Override
    public ListOrderedSet<E> makeObject() {
        return ListOrderedSet.listOrderedSet(new HashSet<E>());
    }

    @SuppressWarnings("unchecked")
    protected ListOrderedSet<E> setupSet() {
        final ListOrderedSet<E> set = makeObject();

        for (int i = 0; i < 10; i++) {
            set.add((E) Integer.toString(i));
        }
        return set;
    }

    static class A {

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof A || obj instanceof B;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

    static class B {

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof A || obj instanceof B;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

    public void testDecorator() {
        try {
            ListOrderedSet.listOrderedSet((List<E>) null);
            fail();
        } catch (final NullPointerException ex) {
        }
        try {
            ListOrderedSet.listOrderedSet((Set<E>) null);
            fail();
        } catch (final NullPointerException ex) {
        }
        try {
            ListOrderedSet.listOrderedSet(null, null);
            fail();
        } catch (final NullPointerException ex) {
        }
        try {
            ListOrderedSet.listOrderedSet(new HashSet<E>(), null);
            fail();
        } catch (final NullPointerException ex) {
        }
        try {
            ListOrderedSet.listOrderedSet(null, new ArrayList<E>());
            fail();
        } catch (final NullPointerException ex) {
        }
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

//    public void testCreate() throws Exception {
//        resetEmpty();
//        writeExternalFormToDisk((java.io.Serializable) getCollection(), "src/test/resources/data/test/ListOrderedSet.emptyCollection.version4.obj");
//        resetFull();
//        writeExternalFormToDisk((java.io.Serializable) getCollection(), "src/test/resources/data/test/ListOrderedSet.fullCollection.version4.obj");
//    }

    public void testOrdering_1_oe() {
        final ListOrderedSet<E> set = setupSet();
        Iterator<E> it = set.iterator();

        for (int i = 0; i < 10; i++) {
            assertEquals("Sequence is wrong", Integer.toString(i), it.next());
    }
    }

    public void testOrdering_2_oe() {
        final ListOrderedSet<E> set = setupSet();
        Iterator<E> it = set.iterator();

        for (int i = 0; i < 10; i++) {
            // removed other assertion
        }

        for (int i = 0; i < 10; i += 2) {
            assertTrue("Must be able to remove int", set.remove(Integer.toString(i)));
    }
    }

    public void testOrdering_3_oe() {
        final ListOrderedSet<E> set = setupSet();
        Iterator<E> it = set.iterator();

        for (int i = 0; i < 10; i++) {
            // removed other assertion
        }

        for (int i = 0; i < 10; i += 2) {
            // removed other assertion
        }

        it = set.iterator();
        for (int i = 1; i < 10; i += 2) {
            assertEquals("Sequence is wrong after remove ", Integer.toString(i), it.next());
    }
    }

    public void testOrdering_4_oe() {
        final ListOrderedSet<E> set = setupSet();
        Iterator<E> it = set.iterator();

        for (int i = 0; i < 10; i++) {
            // removed other assertion
        }

        for (int i = 0; i < 10; i += 2) {
            // removed other assertion
        }

        it = set.iterator();
        for (int i = 1; i < 10; i += 2) {
            // removed other assertion
        }

        for (int i = 0; i < 10; i++) {
            set.add((E) Integer.toString(i));
        }

        assertEquals("Size of set is wrong!", 10, set.size());
    }

    public void testOrdering_5_oe() {
        final ListOrderedSet<E> set = setupSet();
        Iterator<E> it = set.iterator();

        for (int i = 0; i < 10; i++) {
            // removed other assertion
        }

        for (int i = 0; i < 10; i += 2) {
            // removed other assertion
        }

        it = set.iterator();
        for (int i = 1; i < 10; i += 2) {
            // removed other assertion
        }

        for (int i = 0; i < 10; i++) {
            set.add((E) Integer.toString(i));
        }

        // removed other assertion

        it = set.iterator();
        for (int i = 1; i < 10; i += 2) {
            assertEquals("Sequence is wrong", Integer.toString(i), it.next());
    }
    }

    public void testOrdering_6_oe() {
        final ListOrderedSet<E> set = setupSet();
        Iterator<E> it = set.iterator();

        for (int i = 0; i < 10; i++) {
            // removed other assertion
        }

        for (int i = 0; i < 10; i += 2) {
            // removed other assertion
        }

        it = set.iterator();
        for (int i = 1; i < 10; i += 2) {
            // removed other assertion
        }

        for (int i = 0; i < 10; i++) {
            set.add((E) Integer.toString(i));
        }

        // removed other assertion

        it = set.iterator();
        for (int i = 1; i < 10; i += 2) {
            // removed other assertion
        }
        for (int i = 0; i < 10; i += 2) {
            assertEquals("Sequence is wrong", Integer.toString(i), it.next());
    }
    }

    public void testListAddRemove_1_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        assertEquals(3, set.size());
    }

    public void testListAddRemove_2_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        // removed other assertion
        assertSame(ZERO, set.get(0));
    }

    public void testListAddRemove_3_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        // removed other assertion
        // removed other assertion
        assertSame(ONE, set.get(1));
    }

    public void testListAddRemove_4_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TWO, set.get(2));
    }

    public void testListAddRemove_5_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, view.size());
    }

    public void testListAddRemove_6_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(ZERO, view.get(0));
    }

    public void testListAddRemove_7_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(ONE, view.get(1));
    }

    public void testListAddRemove_8_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TWO, view.get(2));
    }

    public void testListAddRemove_9_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(0, set.indexOf(ZERO));
    }

    public void testListAddRemove_10_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(1, set.indexOf(ONE));
    }

    public void testListAddRemove_11_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(2, set.indexOf(TWO));
    }

    public void testListAddRemove_12_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(1);
        assertEquals(2, set.size());
    }

    public void testListAddRemove_13_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(1);
        // removed other assertion
        assertSame(ZERO, set.get(0));
    }

    public void testListAddRemove_14_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(1);
        // removed other assertion
        // removed other assertion
        assertSame(TWO, set.get(1));
    }

    public void testListAddRemove_15_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, view.size());
    }

    public void testListAddRemove_16_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(ZERO, view.get(0));
    }

    public void testListAddRemove_17_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.remove(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TWO, view.get(1));
    }

    public void testListAddIndexed_1_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        assertEquals(3, set.size());
    }

    public void testListAddIndexed_2_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        // removed other assertion
        assertSame(ZERO, set.get(0));
    }

    public void testListAddIndexed_3_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        // removed other assertion
        // removed other assertion
        assertSame(ONE, set.get(1));
    }

    public void testListAddIndexed_4_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TWO, set.get(2));
    }

    public void testListAddIndexed_5_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.add(0, (E) ONE);
        assertEquals(3, set.size());
    }

    public void testListAddIndexed_6_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.add(0, (E) ONE);
        // removed other assertion
        assertSame(ZERO, set.get(0));
    }

    public void testListAddIndexed_7_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.add(0, (E) ONE);
        // removed other assertion
        // removed other assertion
        assertSame(ONE, set.get(1));
    }

    public void testListAddIndexed_8_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.add(0, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TWO, set.get(2));
    }

    public void testListAddIndexed_9_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.add(0, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);
        assertEquals(3, set.size());
    }

    public void testListAddIndexed_10_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.add(0, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);
        // removed other assertion
        assertSame(ZERO, set.get(0));
    }

    public void testListAddIndexed_11_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.add(0, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);
        // removed other assertion
        // removed other assertion
        assertSame(ONE, set.get(1));
    }

    public void testListAddIndexed_12_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.add(0, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TWO, set.get(2));
    }

    public void testListAddIndexed_13_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.add(0, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add(0, (E) THREE); // list = [3,0,2]
        set.remove(TWO); //  set = [0,1]
        set.addAll(1, list);
        assertEquals(4, set.size());
    }

    public void testListAddIndexed_14_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.add(0, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add(0, (E) THREE); // list = [3,0,2]
        set.remove(TWO); //  set = [0,1]
        set.addAll(1, list);
        // removed other assertion
        assertSame(ZERO, set.get(0));
    }

    public void testListAddIndexed_15_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.add(0, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add(0, (E) THREE); // list = [3,0,2]
        set.remove(TWO); //  set = [0,1]
        set.addAll(1, list);
        // removed other assertion
        // removed other assertion
        assertSame(THREE, set.get(1));
    }

    public void testListAddIndexed_16_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.add(0, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add(0, (E) THREE); // list = [3,0,2]
        set.remove(TWO); //  set = [0,1]
        set.addAll(1, list);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(TWO, set.get(2));
    }

    public void testListAddIndexed_17_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        set.add(0, (E) ONE);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        list.add(0, (E) THREE); // list = [3,0,2]
        set.remove(TWO); //  set = [0,1]
        set.addAll(1, list);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(ONE, set.get(3));
    }

    public void testListAddReplacing_1_oe() {
        final ListOrderedSet<E> set = makeObject();
        final A a = new A();
        final B b = new B();
        set.add((E) a);
        assertEquals(1, set.size());
    }

    public void testListAddReplacing_2_oe() {
        final ListOrderedSet<E> set = makeObject();
        final A a = new A();
        final B b = new B();
        set.add((E) a);
        // removed other assertion
        set.add((E) b); // will match but not replace A as equal
        assertEquals(1, set.size());
    }

    public void testListAddReplacing_3_oe() {
        final ListOrderedSet<E> set = makeObject();
        final A a = new A();
        final B b = new B();
        set.add((E) a);
        // removed other assertion
        set.add((E) b); // will match but not replace A as equal
        // removed other assertion
        assertSame(a, set.decorated().iterator().next());
    }

    public void testListAddReplacing_4_oe() {
        final ListOrderedSet<E> set = makeObject();
        final A a = new A();
        final B b = new B();
        set.add((E) a);
        // removed other assertion
        set.add((E) b); // will match but not replace A as equal
        // removed other assertion
        // removed other assertion
        assertSame(a, set.iterator().next());
    }

    public void testListAddReplacing_5_oe() {
        final ListOrderedSet<E> set = makeObject();
        final A a = new A();
        final B b = new B();
        set.add((E) a);
        // removed other assertion
        set.add((E) b); // will match but not replace A as equal
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(a, set.get(0));
    }

    public void testListAddReplacing_6_oe() {
        final ListOrderedSet<E> set = makeObject();
        final A a = new A();
        final B b = new B();
        set.add((E) a);
        // removed other assertion
        set.add((E) b); // will match but not replace A as equal
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(a, set.asList().get(0));
    }

    public void testRetainAll_1_oe() {
        final List<E> list = new ArrayList<>(10);
        final Set<E> set = new HashSet<>(10);
        final ListOrderedSet<E> orderedSet = ListOrderedSet.listOrderedSet(set, list);
        for (int i = 0; i < 10; ++i) {
            orderedSet.add((E) Integer.valueOf(10 - i - 1));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E) Integer.valueOf(i * 2));
        }

        assertTrue(orderedSet.retainAll(retained));
    }

    public void testRetainAll_2_oe() {
        final List<E> list = new ArrayList<>(10);
        final Set<E> set = new HashSet<>(10);
        final ListOrderedSet<E> orderedSet = ListOrderedSet.listOrderedSet(set, list);
        for (int i = 0; i < 10; ++i) {
            orderedSet.add((E) Integer.valueOf(10 - i - 1));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E) Integer.valueOf(i * 2));
        }

        // removed other assertion
        assertEquals(5, orderedSet.size());
    }

    public void testRetainAll_3_oe() {
        final List<E> list = new ArrayList<>(10);
        final Set<E> set = new HashSet<>(10);
        final ListOrderedSet<E> orderedSet = ListOrderedSet.listOrderedSet(set, list);
        for (int i = 0; i < 10; ++i) {
            orderedSet.add((E) Integer.valueOf(10 - i - 1));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E) Integer.valueOf(i * 2));
        }

        // removed other assertion
        // removed other assertion
        // insertion order preserved?
        assertEquals(Integer.valueOf(8), orderedSet.get(0));
    }

    public void testRetainAll_4_oe() {
        final List<E> list = new ArrayList<>(10);
        final Set<E> set = new HashSet<>(10);
        final ListOrderedSet<E> orderedSet = ListOrderedSet.listOrderedSet(set, list);
        for (int i = 0; i < 10; ++i) {
            orderedSet.add((E) Integer.valueOf(10 - i - 1));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E) Integer.valueOf(i * 2));
        }

        // removed other assertion
        // removed other assertion
        // insertion order preserved?
        // removed other assertion
        assertEquals(Integer.valueOf(6), orderedSet.get(1));
    }

    public void testRetainAll_5_oe() {
        final List<E> list = new ArrayList<>(10);
        final Set<E> set = new HashSet<>(10);
        final ListOrderedSet<E> orderedSet = ListOrderedSet.listOrderedSet(set, list);
        for (int i = 0; i < 10; ++i) {
            orderedSet.add((E) Integer.valueOf(10 - i - 1));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E) Integer.valueOf(i * 2));
        }

        // removed other assertion
        // removed other assertion
        // insertion order preserved?
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(4), orderedSet.get(2));
    }

    public void testRetainAll_6_oe() {
        final List<E> list = new ArrayList<>(10);
        final Set<E> set = new HashSet<>(10);
        final ListOrderedSet<E> orderedSet = ListOrderedSet.listOrderedSet(set, list);
        for (int i = 0; i < 10; ++i) {
            orderedSet.add((E) Integer.valueOf(10 - i - 1));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E) Integer.valueOf(i * 2));
        }

        // removed other assertion
        // removed other assertion
        // insertion order preserved?
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(2), orderedSet.get(3));
    }

    public void testRetainAll_7_oe() {
        final List<E> list = new ArrayList<>(10);
        final Set<E> set = new HashSet<>(10);
        final ListOrderedSet<E> orderedSet = ListOrderedSet.listOrderedSet(set, list);
        for (int i = 0; i < 10; ++i) {
            orderedSet.add((E) Integer.valueOf(10 - i - 1));
        }

        final Collection<E> retained = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            retained.add((E) Integer.valueOf(i * 2));
        }

        // removed other assertion
        // removed other assertion
        // insertion order preserved?
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(0), orderedSet.get(4));
    }

    public void testDuplicates_1_oe() {
        final List<E> list = new ArrayList<>(10);
        list.add((E) Integer.valueOf(1));
        list.add((E) Integer.valueOf(2));
        list.add((E) Integer.valueOf(3));
        list.add((E) Integer.valueOf(1));

        final ListOrderedSet<E> orderedSet = ListOrderedSet.listOrderedSet(list);

        assertEquals(3, orderedSet.size());
    }

    public void testDuplicates_2_oe() {
        final List<E> list = new ArrayList<>(10);
        list.add((E) Integer.valueOf(1));
        list.add((E) Integer.valueOf(2));
        list.add((E) Integer.valueOf(3));
        list.add((E) Integer.valueOf(1));

        final ListOrderedSet<E> orderedSet = ListOrderedSet.listOrderedSet(list);

        // removed other assertion
        assertEquals(3, IteratorUtils.toArray(orderedSet.iterator()).length);
    }

    public void testDuplicates_3_oe() {
        final List<E> list = new ArrayList<>(10);
        list.add((E) Integer.valueOf(1));
        list.add((E) Integer.valueOf(2));
        list.add((E) Integer.valueOf(3));
        list.add((E) Integer.valueOf(1));

        final ListOrderedSet<E> orderedSet = ListOrderedSet.listOrderedSet(list);

        // removed other assertion
        // removed other assertion

        // insertion order preserved?
        assertEquals(Integer.valueOf(1), orderedSet.get(0));
    }

    public void testDuplicates_4_oe() {
        final List<E> list = new ArrayList<>(10);
        list.add((E) Integer.valueOf(1));
        list.add((E) Integer.valueOf(2));
        list.add((E) Integer.valueOf(3));
        list.add((E) Integer.valueOf(1));

        final ListOrderedSet<E> orderedSet = ListOrderedSet.listOrderedSet(list);

        // removed other assertion
        // removed other assertion

        // insertion order preserved?
        // removed other assertion
        assertEquals(Integer.valueOf(2), orderedSet.get(1));
    }

    public void testDuplicates_5_oe() {
        final List<E> list = new ArrayList<>(10);
        list.add((E) Integer.valueOf(1));
        list.add((E) Integer.valueOf(2));
        list.add((E) Integer.valueOf(3));
        list.add((E) Integer.valueOf(1));

        final ListOrderedSet<E> orderedSet = ListOrderedSet.listOrderedSet(list);

        // removed other assertion
        // removed other assertion

        // insertion order preserved?
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(3), orderedSet.get(2));
    }

}
