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
import java.util.Iterator;
import java.util.List;

/**
 * Extension of {@link AbstractSetTest} for exercising the {@link ListOrderedSet}
 * implementation.
 *
 * @since 3.1
 */
public class ListOrderedSet2Test_OE25Dev<E> extends AbstractSetTest<E> {

    private static final Integer ZERO = Integer.valueOf(0);
    private static final Integer ONE = Integer.valueOf(1);
    private static final Integer TWO = Integer.valueOf(2);
    private static final Integer THREE = Integer.valueOf(3);

    public ListOrderedSet2Test_OE25Dev(final String testName) {
        super(testName);
    }

    @Override
    public ListOrderedSet<E> makeObject() {
        return new ListOrderedSet<>();
    }

    @SuppressWarnings("unchecked")
    protected ListOrderedSet<E> setupSet() {
        final ListOrderedSet<E> set = makeObject();

        for (int i = 0; i < 10; i++) {
            set.add((E) Integer.toString(i));
        }
        return set;
    }

    @SuppressWarnings("unchecked")
    public void testOrdering() {
        final ListOrderedSet<E> set = setupSet();
        Iterator<E> it = set.iterator();

        for (int i = 0; i < 10; i++) {
            assertEquals("Sequence is wrong", Integer.toString(i), it.next());
        }

        for (int i = 0; i < 10; i += 2) {
            assertTrue("Must be able to remove int", set.remove(Integer.toString(i)));
        }

        it = set.iterator();
        for (int i = 1; i < 10; i += 2) {
            assertEquals("Sequence is wrong after remove ", Integer.toString(i), it.next());
        }

        for (int i = 0; i < 10; i++) {
            set.add((E) Integer.toString(i));
        }

        assertEquals("Size of set is wrong!", 10, set.size());

        it = set.iterator();
        for (int i = 1; i < 10; i += 2) {
            assertEquals("Sequence is wrong", Integer.toString(i), it.next());
        }
        for (int i = 0; i < 10; i += 2) {
            assertEquals("Sequence is wrong", Integer.toString(i), it.next());
        }
    }

    @SuppressWarnings("unchecked")
    public void testListAddRemove() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        assertEquals(3, set.size());
        assertSame(ZERO, set.get(0));
        assertSame(ONE, set.get(1));
        assertSame(TWO, set.get(2));
        assertEquals(3, view.size());
        assertSame(ZERO, view.get(0));
        assertSame(ONE, view.get(1));
        assertSame(TWO, view.get(2));

        assertEquals(0, set.indexOf(ZERO));
        assertEquals(1, set.indexOf(ONE));
        assertEquals(2, set.indexOf(TWO));

        set.remove(1);
        assertEquals(2, set.size());
        assertSame(ZERO, set.get(0));
        assertSame(TWO, set.get(1));
        assertEquals(2, view.size());
        assertSame(ZERO, view.get(0));
        assertSame(TWO, view.get(1));
    }

    @SuppressWarnings("unchecked")
    public void testListAddIndexed() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        assertEquals(3, set.size());
        assertSame(ZERO, set.get(0));
        assertSame(ONE, set.get(1));
        assertSame(TWO, set.get(2));

        set.add(0, (E) ONE);
        assertEquals(3, set.size());
        assertSame(ZERO, set.get(0));
        assertSame(ONE, set.get(1));
        assertSame(TWO, set.get(2));

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);
        assertEquals(3, set.size());
        assertSame(ZERO, set.get(0));
        assertSame(ONE, set.get(1));
        assertSame(TWO, set.get(2));

        list.add(0, (E) THREE); // list = [3,0,2]
        set.remove(TWO);    //  set = [0,1]
        set.addAll(1, list);
        assertEquals(4, set.size());
        assertSame(ZERO, set.get(0));
        assertSame(THREE, set.get(1));
        assertSame(TWO, set.get(2));
        assertSame(ONE, set.get(3));
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

//    public void testCreate() throws Exception {
//        resetEmpty();
//        writeExternalFormToDisk((java.io.Serializable) collection, "D:/dev/collections/data/test/ListOrderedSet.emptyCollection.version3.1.obj");
//        resetFull();
//        writeExternalFormToDisk((java.io.Serializable) collection, "D:/dev/collections/data/test/ListOrderedSet.fullCollection.version3.1.obj");
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
        }

        for (int i = 0; i < 10; i += 2) {
            assertTrue("Must be able to remove int", set.remove(Integer.toString(i)));
    }
    }

    public void testOrdering_4_oe() {
        final ListOrderedSet<E> set = setupSet();
        Iterator<E> it = set.iterator();

        for (int i = 0; i < 10; i++) {
        }

        for (int i = 0; i < 10; i += 2) {
        }

        it = set.iterator();
        for (int i = 1; i < 10; i += 2) {
        }

        for (int i = 0; i < 10; i++) {
            set.add((E) Integer.toString(i));
        }

        assertEquals("Size of set is wrong!", 10, set.size());
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

        assertSame(ZERO, set.get(0));
    }

    public void testListAddRemove_3_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        assertSame(ONE, set.get(1));
    }

    public void testListAddRemove_4_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        assertSame(TWO, set.get(2));
    }

    public void testListAddRemove_5_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        assertEquals(3, view.size());
    }

    public void testListAddRemove_6_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        assertSame(ZERO, view.get(0));
    }

    public void testListAddRemove_7_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        assertSame(ONE, view.get(1));
    }

    public void testListAddRemove_8_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);

        assertSame(TWO, view.get(2));
    }

    public void testListAddRemove_9_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);


        assertEquals(0, set.indexOf(ZERO));
    }

    public void testListAddRemove_10_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);


        assertEquals(1, set.indexOf(ONE));
    }

    public void testListAddRemove_11_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);


        assertEquals(2, set.indexOf(TWO));
    }

    public void testListAddRemove_12_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);



        set.remove(1);
        assertEquals(2, set.size());
    }

    public void testListAddRemove_13_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);



        set.remove(1);
        assertSame(ZERO, set.get(0));
    }

    public void testListAddRemove_14_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);



        set.remove(1);
        assertSame(TWO, set.get(1));
    }

    public void testListAddRemove_15_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);



        set.remove(1);
        assertEquals(2, view.size());
    }

    public void testListAddRemove_16_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);



        set.remove(1);
        assertSame(ZERO, view.get(0));
    }

    public void testListAddRemove_17_oe() {
        final ListOrderedSet<E> set = makeObject();
        final List<E> view = set.asList();
        set.add((E) ZERO);
        set.add((E) ONE);
        set.add((E) TWO);



        set.remove(1);
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
        assertSame(ZERO, set.get(0));
    }

    public void testListAddIndexed_3_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        assertSame(ONE, set.get(1));
    }

    public void testListAddIndexed_4_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);
        assertSame(TWO, set.get(2));
    }

    public void testListAddIndexed_5_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);

        set.add(0, (E) ONE);
        assertEquals(3, set.size());
    }

    public void testListAddIndexed_6_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);

        set.add(0, (E) ONE);
        assertSame(ZERO, set.get(0));
    }

    public void testListAddIndexed_7_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);

        set.add(0, (E) ONE);
        assertSame(ONE, set.get(1));
    }

    public void testListAddIndexed_8_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);

        set.add(0, (E) ONE);
        assertSame(TWO, set.get(2));
    }

    public void testListAddIndexed_9_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);

        set.add(0, (E) ONE);

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

        set.add(0, (E) ONE);

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);
        assertSame(ZERO, set.get(0));
    }

    public void testListAddIndexed_11_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);

        set.add(0, (E) ONE);

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);
        assertSame(ONE, set.get(1));
    }

    public void testListAddIndexed_12_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);

        set.add(0, (E) ONE);

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);
        assertSame(TWO, set.get(2));
    }

    public void testListAddIndexed_13_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);

        set.add(0, (E) ONE);

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);

        list.add(0, (E) THREE); // list = [3,0,2]
        set.remove(TWO);    //  set = [0,1]
        set.addAll(1, list);
        assertEquals(4, set.size());
    }

    public void testListAddIndexed_14_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);

        set.add(0, (E) ONE);

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);

        list.add(0, (E) THREE); // list = [3,0,2]
        set.remove(TWO);    //  set = [0,1]
        set.addAll(1, list);
        assertSame(ZERO, set.get(0));
    }

    public void testListAddIndexed_15_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);

        set.add(0, (E) ONE);

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);

        list.add(0, (E) THREE); // list = [3,0,2]
        set.remove(TWO);    //  set = [0,1]
        set.addAll(1, list);
        assertSame(THREE, set.get(1));
    }

    public void testListAddIndexed_16_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);

        set.add(0, (E) ONE);

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);

        list.add(0, (E) THREE); // list = [3,0,2]
        set.remove(TWO);    //  set = [0,1]
        set.addAll(1, list);
        assertSame(TWO, set.get(2));
    }

    public void testListAddIndexed_17_oe() {
        final ListOrderedSet<E> set = makeObject();
        set.add((E) ZERO);
        set.add((E) TWO);

        set.add(1, (E) ONE);

        set.add(0, (E) ONE);

        final List<E> list = new ArrayList<>();
        list.add((E) ZERO);
        list.add((E) TWO);

        set.addAll(0, list);

        list.add(0, (E) THREE); // list = [3,0,2]
        set.remove(TWO);    //  set = [0,1]
        set.addAll(1, list);
        assertSame(ONE, set.get(3));
    }

}
