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
package org.apache.commons.collections4;

import java.util.EmptyStackException;

import junit.framework.Test;

/**
 * Tests ArrayStack.
 *
 */
@SuppressWarnings("deprecation") // we test a deprecated class
public class ArrayStackTest_OE25Dev<E> extends AbstractArrayListTest<E> {

    public ArrayStackTest_OE25Dev(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(ArrayStackTest_OE25Dev.class);
    }

    @Override
    public ArrayStack<E> makeObject() {
        return new ArrayStack<>();
    }

    //-----------------------------------------------------------------------

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

//    public void testCreate() throws Exception {
//        resetEmpty();
//        writeExternalFormToDisk((java.io.Serializable) getCollection(), "src/test/resources/data/test/ArrayStack.emptyCollection.version4.obj");
//        resetFull();
//        writeExternalFormToDisk((java.io.Serializable) getCollection(), "src/test/resources/data/test/ArrayStack.fullCollection.version4.obj");
//    }

    public void testNewStack_1_oe() {
        final ArrayStack<E> stack = makeObject();
        assertTrue("New stack is empty", stack.empty());
    }

    public void testNewStack_2_oe() {
        final ArrayStack<E> stack = makeObject();
        // removed other assertion
        assertEquals("New stack has size zero", 0, stack.size());
    }

    public void testPushPeekPop_1_oe() {
        final ArrayStack<E> stack = makeObject();

        stack.push((E) "First Item");
        assertTrue("Stack is not empty", !stack.empty());
    }

    public void testPushPeekPop_2_oe() {
        final ArrayStack<E> stack = makeObject();

        stack.push((E) "First Item");
        // removed other assertion
        assertEquals("Stack size is one", 1, stack.size());
    }

    public void testPushPeekPop_3_oe() {
        final ArrayStack<E> stack = makeObject();

        stack.push((E) "First Item");
        // removed other assertion
        // removed other assertion
        assertEquals("Top item is 'First Item'", "First Item", (String) stack.peek());
    }

    public void testPushPeekPop_4_oe() {
        final ArrayStack<E> stack = makeObject();

        stack.push((E) "First Item");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Stack size is one", 1, stack.size());
    }

    public void testPushPeekPop_5_oe() {
        final ArrayStack<E> stack = makeObject();

        stack.push((E) "First Item");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        stack.push((E) "Second Item");
        assertEquals("Stack size is two", 2, stack.size());
    }

    public void testPushPeekPop_6_oe() {
        final ArrayStack<E> stack = makeObject();

        stack.push((E) "First Item");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        stack.push((E) "Second Item");
        // removed other assertion
        assertEquals("Top item is 'Second Item'", "Second Item", (String) stack.peek());
    }

    public void testPushPeekPop_7_oe() {
        final ArrayStack<E> stack = makeObject();

        stack.push((E) "First Item");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        stack.push((E) "Second Item");
        // removed other assertion
        // removed other assertion
        assertEquals("Stack size is two", 2, stack.size());
    }

    public void testPushPeekPop_8_oe() {
        final ArrayStack<E> stack = makeObject();

        stack.push((E) "First Item");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        stack.push((E) "Second Item");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("Popped item is 'Second Item'", "Second Item", (String) stack.pop());
    }

    public void testSearch_1_oe() {
        final ArrayStack<E> stack = makeObject();

        stack.push((E) "First Item");
        stack.push((E) "Second Item");
        assertEquals("Top item is 'Second Item'", 1, stack.search("Second Item"));
    }

    public void testSearch_2_oe() {
        final ArrayStack<E> stack = makeObject();

        stack.push((E) "First Item");
        stack.push((E) "Second Item");
        // removed other assertion
        assertEquals("Next Item is 'First Item'", 2, stack.search("First Item"));
    }

    public void testSearch_3_oe() {
        final ArrayStack<E> stack = makeObject();

        stack.push((E) "First Item");
        stack.push((E) "Second Item");
        // removed other assertion
        // removed other assertion
        assertEquals("Cannot find 'Missing Item'", -1, stack.search("Missing Item"));
    }

}
