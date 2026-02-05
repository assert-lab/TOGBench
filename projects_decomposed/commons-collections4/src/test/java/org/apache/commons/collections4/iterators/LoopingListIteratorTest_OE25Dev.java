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
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the LoopingListIterator class.
 *
 */
public class LoopingListIteratorTest_OE25Dev {

    /**
     * Tests constructor exception.
     */
    @Test
    public void testConstructorEx() throws Exception {
        try {
            new LoopingListIterator<>(null);
            fail();
        } catch (final NullPointerException ex) {
        }
    }

    /**
     * Tests whether an empty looping list iterator works.
     */

    /**
     * Tests whether a looping list iterator works on a list with only
     * one element.
     */

    /**
     * Tests whether a looping list iterator works on a list with two
     * elements.
     */

    /**
     * Tests jogging back and forth between two elements, but not over
     * the begin/end boundary of the list.
     */

    /**
     * Tests jogging back and forth between two elements over the
     * begin/end boundary of the list.
     */

    /**
     * Tests removing an element from a wrapped ArrayList.
     */

    /**
     * Tests removing an element from a wrapped ArrayList.
     */

    /**
     * Tests the reset method.
     */

    /**
     * Tests the add method.
     */

    /**
     * Tests nextIndex and previousIndex.
     */

    /**
     * Tests using the set method to change elements.
     */

@Test
    public void testLooping0_1_oe() throws Exception {
        final List<Object> list = new ArrayList<>();
        final LoopingListIterator<Object> loop = new LoopingListIterator<>(list);
        assertFalse(loop.hasNext());
    }

@Test
    public void testLooping0_2_oe() throws Exception {
        final List<Object> list = new ArrayList<>();
        final LoopingListIterator<Object> loop = new LoopingListIterator<>(list);
        // removed other assertion
        assertFalse(loop.hasPrevious());
    }

@Test
    public void testLooping1_1_oe() throws Exception {
        final List<String> list = Arrays.asList("a");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a>

        assertTrue(loop.hasNext());
    }

@Test
    public void testLooping1_2_oe() throws Exception {
        final List<String> list = Arrays.asList("a");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a>

        // removed other assertion
        assertEquals("a",loop.next());// <a> assertTrue(loop.hasNext());
    }

@Test
    public void testLooping1_3_oe() throws Exception {
        final List<String> list = Arrays.asList("a");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a>

        // removed other assertion
        // removed other assertion
        assertEquals("a",loop.next());// <a> assertTrue(loop.hasNext());
    }

@Test
    public void testLooping1_4_oe() throws Exception {
        final List<String> list = Arrays.asList("a");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a>

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("a",loop.next());// <a> assertTrue(loop.hasPrevious());
    }

@Test
    public void testLooping1_5_oe() throws Exception {
        final List<String> list = Arrays.asList("a");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a>

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("a",loop.previous());// <a> assertTrue(loop.hasPrevious());
    }

@Test
    public void testLooping1_6_oe() throws Exception {
        final List<String> list = Arrays.asList("a");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a>

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("a",loop.previous());// <a> assertTrue(loop.hasPrevious());
    }

@Test
    public void testLooping1_7_oe() throws Exception {
        final List<String> list = Arrays.asList("a");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a>

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("a", loop.previous()); // <a>;
    }

@Test
    public void testLooping2_1_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        assertTrue(loop.hasNext());
    }

@Test
    public void testLooping2_2_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // removed other assertion
        assertEquals("a",loop.next());// a <b> assertTrue(loop.hasNext());
    }

@Test
    public void testLooping2_3_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // removed other assertion
        // removed other assertion
        assertEquals("b",loop.next());// <a> b assertTrue(loop.hasNext());
    }

@Test
    public void testLooping2_4_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("a",loop.next());// a <b> loop.reset();// <a> b assertTrue(loop.hasPrevious());
    }

@Test
    public void testLooping2_5_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("b",loop.previous());// a <b> assertTrue(loop.hasPrevious());
    }

@Test
    public void testLooping2_6_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("a",loop.previous());// <a> b assertTrue(loop.hasPrevious());
    }

@Test
    public void testLooping2_7_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("b", loop.previous()); // a <b>;
    }

@Test
    public void testJoggingNotOverBoundary_1_oe() {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // Try jogging back and forth between the elements, but not
        // over the begin/end boundary.
        loop.reset();
        assertEquals("a", loop.next());     // a <b>;
    }

@Test
    public void testJoggingNotOverBoundary_2_oe() {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // Try jogging back and forth between the elements, but not
        // over the begin/end boundary.
        loop.reset();
        // removed other assertion
        assertEquals("a", loop.previous()); // <a> b;
    }

@Test
    public void testJoggingNotOverBoundary_3_oe() {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // Try jogging back and forth between the elements, but not
        // over the begin/end boundary.
        loop.reset();
        // removed other assertion
        // removed other assertion
        assertEquals("a", loop.next());     // a <b>;
    }

@Test
    public void testJoggingNotOverBoundary_4_oe() {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // Try jogging back and forth between the elements, but not
        // over the begin/end boundary.
        loop.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("b", loop.next());     // <a> b;
    }

@Test
    public void testJoggingNotOverBoundary_5_oe() {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // Try jogging back and forth between the elements, but not
        // over the begin/end boundary.
        loop.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("b", loop.previous()); // a <b>;
    }

@Test
    public void testJoggingNotOverBoundary_6_oe() {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // Try jogging back and forth between the elements, but not
        // over the begin/end boundary.
        loop.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("b", loop.next());     // <a> b;
    }

@Test
    public void testJoggingOverBoundary_1_oe() {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // Try jogging back and forth between the elements, but not
        // over the begin/end boundary.
        assertEquals("b", loop.previous()); // a <b>;
    }

@Test
    public void testJoggingOverBoundary_2_oe() {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // Try jogging back and forth between the elements, but not
        // over the begin/end boundary.
        // removed other assertion
        assertEquals("b", loop.next());     // <a> b;
    }

@Test
    public void testJoggingOverBoundary_3_oe() {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // Try jogging back and forth between the elements, but not
        // over the begin/end boundary.
        // removed other assertion
        // removed other assertion
        assertEquals("b", loop.previous()); // a <b>;
    }

@Test
    public void testJoggingOverBoundary_4_oe() {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // Try jogging back and forth between the elements, but not
        // over the begin/end boundary.
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("a", loop.previous()); // <a> b;
    }

@Test
    public void testJoggingOverBoundary_5_oe() {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // Try jogging back and forth between the elements, but not
        // over the begin/end boundary.
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("a", loop.next());     // a <b>;
    }

@Test
    public void testJoggingOverBoundary_6_oe() {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b

        // Try jogging back and forth between the elements, but not
        // over the begin/end boundary.
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("a", loop.previous()); // <a> b;
    }

@Test
    public void testRemovingElementsAndIteratingForward_1_oe() {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        assertTrue(loop.hasNext());
    }

@Test
    public void testRemovingElementsAndIteratingForward_2_oe() {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        assertEquals("a",loop.next());// a <b> c loop.remove();// <b> c assertEquals(2,list.size());
    }

@Test
    public void testRemovingElementsAndIteratingForward_3_oe() {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        assertTrue(loop.hasNext());
    }

@Test
    public void testRemovingElementsAndIteratingForward_4_oe() {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("b",loop.next());// b <c> loop.remove();// <c> assertEquals(1,list.size());
    }

@Test
    public void testRemovingElementsAndIteratingForward_5_oe() {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertTrue(loop.hasNext());
    }

@Test
    public void testRemovingElementsAndIteratingForward_6_oe() {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("c",loop.next());// <c> loop.remove();// --- assertEquals(0,list.size());
    }

@Test
    public void testRemovingElementsAndIteratingForward_7_oe() {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertFalse(loop.hasNext());
    }

@Test
    public void testRemovingElementsAndIteratingBackwards_1_oe() {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        assertTrue(loop.hasPrevious());
    }

@Test
    public void testRemovingElementsAndIteratingBackwards_2_oe() {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        assertEquals("c",loop.previous());// a b <c> loop.remove();// <a> b assertEquals(2,list.size());
    }

@Test
    public void testRemovingElementsAndIteratingBackwards_3_oe() {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        assertTrue(loop.hasPrevious());
    }

@Test
    public void testRemovingElementsAndIteratingBackwards_4_oe() {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("b",loop.previous());// a <b> loop.remove();// <a> assertEquals(1,list.size());
    }

@Test
    public void testRemovingElementsAndIteratingBackwards_5_oe() {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertTrue(loop.hasPrevious());
    }

@Test
    public void testRemovingElementsAndIteratingBackwards_6_oe() {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("a",loop.previous());// <a> loop.remove();// --- assertEquals(0,list.size());
    }

@Test
    public void testRemovingElementsAndIteratingBackwards_7_oe() {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertFalse(loop.hasPrevious());
    }

@Test
    public void testReset_1_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        assertEquals("a", loop.next()); // a <b> c;
    }

@Test
    public void testReset_2_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        assertEquals("b", loop.next()); // a b <c>;
    }

@Test
    public void testReset_3_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion
        loop.reset();                   // <a> b c
        assertEquals("a", loop.next()); // a <b> c;
    }

@Test
    public void testReset_4_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion
        loop.reset();                   // <a> b c
        // removed other assertion
        loop.reset();                   // <a> b c
        assertEquals("a", loop.next()); // a <b> c;
    }

@Test
    public void testReset_5_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion
        loop.reset();                   // <a> b c
        // removed other assertion
        loop.reset();                   // <a> b c
        // removed other assertion
        assertEquals("b", loop.next()); // a b <c>;
    }

@Test
    public void testReset_6_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion
        loop.reset();                   // <a> b c
        // removed other assertion
        loop.reset();                   // <a> b c
        // removed other assertion
        // removed other assertion
        assertEquals("c", loop.next()); // <a> b c;
    }

@Test
    public void testReset_7_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion
        loop.reset();                   // <a> b c
        // removed other assertion
        loop.reset();                   // <a> b c
        // removed other assertion
        // removed other assertion
        // removed other assertion
        loop.reset();                   // <a> b c

        assertEquals("c", loop.previous()); // a b <c>;
    }

@Test
    public void testReset_8_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion
        loop.reset();                   // <a> b c
        // removed other assertion
        loop.reset();                   // <a> b c
        // removed other assertion
        // removed other assertion
        // removed other assertion
        loop.reset();                   // <a> b c

        // removed other assertion
        assertEquals("b", loop.previous()); // a <b> c;
    }

@Test
    public void testReset_9_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion
        loop.reset();                   // <a> b c
        // removed other assertion
        loop.reset();                   // <a> b c
        // removed other assertion
        // removed other assertion
        // removed other assertion
        loop.reset();                   // <a> b c

        // removed other assertion
        // removed other assertion
        loop.reset();                       // <a> b c
        assertEquals("c", loop.previous()); // a b <c>;
    }

@Test
    public void testReset_10_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion
        loop.reset();                   // <a> b c
        // removed other assertion
        loop.reset();                   // <a> b c
        // removed other assertion
        // removed other assertion
        // removed other assertion
        loop.reset();                   // <a> b c

        // removed other assertion
        // removed other assertion
        loop.reset();                       // <a> b c
        // removed other assertion
        loop.reset();                       // <a> b c
        assertEquals("c", loop.previous()); // a b <c>;
    }

@Test
    public void testReset_11_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion
        loop.reset();                   // <a> b c
        // removed other assertion
        loop.reset();                   // <a> b c
        // removed other assertion
        // removed other assertion
        // removed other assertion
        loop.reset();                   // <a> b c

        // removed other assertion
        // removed other assertion
        loop.reset();                       // <a> b c
        // removed other assertion
        loop.reset();                       // <a> b c
        // removed other assertion
        assertEquals("b", loop.previous()); // a <b> c;
    }

@Test
    public void testReset_12_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion
        loop.reset();                   // <a> b c
        // removed other assertion
        loop.reset();                   // <a> b c
        // removed other assertion
        // removed other assertion
        // removed other assertion
        loop.reset();                   // <a> b c

        // removed other assertion
        // removed other assertion
        loop.reset();                       // <a> b c
        // removed other assertion
        loop.reset();                       // <a> b c
        // removed other assertion
        // removed other assertion
        assertEquals("a", loop.previous()); // <a> b c;
    }

@Test
    public void testAdd_1_oe() {
        List<String> list = new ArrayList<>(Arrays.asList("b", "e", "f"));
        LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <b> e f

        loop.add("a");                      // <a> b e f
        assertEquals("b",loop.next());// a <b> e f loop.reset();// <a> b e f assertEquals("a",loop.next());// a <b> e f assertEquals("b",loop.next());// a b <e> f loop.add("c");// a b c <e> f assertEquals("e",loop.next());// a b c e <f> assertEquals("e",loop.previous());// a b c <e> f assertEquals("c",loop.previous());// a b <c> e f assertEquals("c",loop.next());// a b c <e> f loop.add("d");// a b c d <e> f loop.reset();// <a> b c d e f assertEquals("a",loop.next());// a <b> c d e f assertEquals("b",loop.next());// a b <c> d e f assertEquals("c",loop.next());// a b c <d> e f assertEquals("d",loop.next());// a b c d <e> f assertEquals("e",loop.next());// a b c d e <f> assertEquals("f",loop.next());// <a> b c d e f assertEquals("a",loop.next());// a <b> c d e f list = new ArrayList<>(Arrays.asList("b","e","f"));
    }

@Test
    public void testAdd_2_oe() {
        List<String> list = new ArrayList<>(Arrays.asList("b", "e", "f"));
        LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <b> e f

        loop.add("a");                      // <a> b e f
        // removed other assertion
        loop = new LoopingListIterator<>(list); // <b> e f

        loop.add("a");                      // a <b> e f
        assertEquals("a",loop.previous());// a b e <f> loop.reset();// <a> b e f assertEquals("f",loop.previous());// a b e <f> assertEquals("e",loop.previous());// a b <e> f loop.add("d");// a b d <e> f assertEquals("d",loop.previous());// a b <d> e f loop.add("c");// a b c <d> e f assertEquals("c",loop.previous());// a b <c> d e f loop.reset();
    }

@Test
    public void testAdd_3_oe() {
        List<String> list = new ArrayList<>(Arrays.asList("b", "e", "f"));
        LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <b> e f

        loop.add("a");                      // <a> b e f
        // removed other assertion
        loop = new LoopingListIterator<>(list); // <b> e f

        loop.add("a");                      // a <b> e f
        // removed other assertion
        assertEquals("a", loop.next());     // a <b> c d e f;
    }

@Test
    public void testAdd_4_oe() {
        List<String> list = new ArrayList<>(Arrays.asList("b", "e", "f"));
        LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <b> e f

        loop.add("a");                      // <a> b e f
        // removed other assertion
        loop = new LoopingListIterator<>(list); // <b> e f

        loop.add("a");                      // a <b> e f
        // removed other assertion
        // removed other assertion
        assertEquals("b", loop.next());     // a b <c> d e f;
    }

@Test
    public void testAdd_5_oe() {
        List<String> list = new ArrayList<>(Arrays.asList("b", "e", "f"));
        LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <b> e f

        loop.add("a");                      // <a> b e f
        // removed other assertion
        loop = new LoopingListIterator<>(list); // <b> e f

        loop.add("a");                      // a <b> e f
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("c", loop.next());     // a b c <d> e f;
    }

@Test
    public void testAdd_6_oe() {
        List<String> list = new ArrayList<>(Arrays.asList("b", "e", "f"));
        LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <b> e f

        loop.add("a");                      // <a> b e f
        // removed other assertion
        loop = new LoopingListIterator<>(list); // <b> e f

        loop.add("a");                      // a <b> e f
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("d", loop.next());     // a b c d <e> f;
    }

@Test
    public void testAdd_7_oe() {
        List<String> list = new ArrayList<>(Arrays.asList("b", "e", "f"));
        LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <b> e f

        loop.add("a");                      // <a> b e f
        // removed other assertion
        loop = new LoopingListIterator<>(list); // <b> e f

        loop.add("a");                      // a <b> e f
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("e", loop.next());     // a b c d e <f>;
    }

@Test
    public void testAdd_8_oe() {
        List<String> list = new ArrayList<>(Arrays.asList("b", "e", "f"));
        LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <b> e f

        loop.add("a");                      // <a> b e f
        // removed other assertion
        loop = new LoopingListIterator<>(list); // <b> e f

        loop.add("a");                      // a <b> e f
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("f", loop.next());     // <a> b c d e f;
    }

@Test
    public void testAdd_9_oe() {
        List<String> list = new ArrayList<>(Arrays.asList("b", "e", "f"));
        LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <b> e f

        loop.add("a");                      // <a> b e f
        // removed other assertion
        loop = new LoopingListIterator<>(list); // <b> e f

        loop.add("a");                      // a <b> e f
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("a", loop.next());     // a <b> c d e f;
    }

@Test
    public void testNextAndPreviousIndex_1_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        assertEquals(0, loop.nextIndex());
    }

@Test
    public void testNextAndPreviousIndex_2_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        assertEquals(2, loop.previousIndex());
    }

@Test
    public void testNextAndPreviousIndex_3_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        assertEquals("a",loop.next());// a <b> c assertEquals(1,loop.nextIndex());
    }

@Test
    public void testNextAndPreviousIndex_4_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, loop.previousIndex());
    }

@Test
    public void testNextAndPreviousIndex_5_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("a",loop.previous());// <a> b c assertEquals(0,loop.nextIndex());
    }

@Test
    public void testNextAndPreviousIndex_6_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(2, loop.previousIndex());
    }

@Test
    public void testNextAndPreviousIndex_7_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("c",loop.previous());// a b <c> assertEquals(2,loop.nextIndex());
    }

@Test
    public void testNextAndPreviousIndex_8_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(1, loop.previousIndex());
    }

@Test
    public void testNextAndPreviousIndex_9_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("b",loop.previous());// a <b> c assertEquals(1,loop.nextIndex());
    }

@Test
    public void testNextAndPreviousIndex_10_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, loop.previousIndex());
    }

@Test
    public void testNextAndPreviousIndex_11_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("a",loop.previous());// <a> b c assertEquals(0,loop.nextIndex());
    }

@Test
    public void testNextAndPreviousIndex_12_oe() {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <a> b c

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(2, loop.previousIndex());
    }

@Test
    public void testSet_1_oe() {
        final List<String> list = Arrays.asList("q", "r", "z");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <q> r z

        assertEquals("z", loop.previous()); // q r <z>;
    }

@Test
    public void testSet_2_oe() {
        final List<String> list = Arrays.asList("q", "r", "z");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <q> r z

        // removed other assertion
        loop.set("c");                      // q r <c>

        loop.reset();                       // <q> r c
        assertEquals("q", loop.next());     // q <r> c;
    }

@Test
    public void testSet_3_oe() {
        final List<String> list = Arrays.asList("q", "r", "z");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <q> r z

        // removed other assertion
        loop.set("c");                      // q r <c>

        loop.reset();                       // <q> r c
        // removed other assertion
        loop.set("a");                      // a <r> c

        assertEquals("r", loop.next());     // a r <c>;
    }

@Test
    public void testSet_4_oe() {
        final List<String> list = Arrays.asList("q", "r", "z");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <q> r z

        // removed other assertion
        loop.set("c");                      // q r <c>

        loop.reset();                       // <q> r c
        // removed other assertion
        loop.set("a");                      // a <r> c

        // removed other assertion
        loop.set("b");                      // a b <c>

        loop.reset();                       // <a> b c
        assertEquals("a", loop.next());     // a <b> c;
    }

@Test
    public void testSet_5_oe() {
        final List<String> list = Arrays.asList("q", "r", "z");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <q> r z

        // removed other assertion
        loop.set("c");                      // q r <c>

        loop.reset();                       // <q> r c
        // removed other assertion
        loop.set("a");                      // a <r> c

        // removed other assertion
        loop.set("b");                      // a b <c>

        loop.reset();                       // <a> b c
        // removed other assertion
        assertEquals("b", loop.next());     // a b <c>;
    }

@Test
    public void testSet_6_oe() {
        final List<String> list = Arrays.asList("q", "r", "z");
        final LoopingListIterator<String> loop = new LoopingListIterator<>(list); // <q> r z

        // removed other assertion
        loop.set("c");                      // q r <c>

        loop.reset();                       // <q> r c
        // removed other assertion
        loop.set("a");                      // a <r> c

        // removed other assertion
        loop.set("b");                      // a b <c>

        loop.reset();                       // <a> b c
        // removed other assertion
        // removed other assertion
        assertEquals("c", loop.next());     // <a> b c;
    }

}
