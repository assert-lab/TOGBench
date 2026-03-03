package org.apache.commons.jcs3.utils.struct;



/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import junit.framework.TestCase;

/** Unit tests for the double linked list. */
public class DoubleLinkedListUnitTest_OE25Dev
    extends TestCase
{
    /** verify that the last is added when the list is empty. */

    /** verify that the last is added when the list is empty. */

    /** verify that it's added last. */

    /** verify that it's added last. */

    /** verify that it's added last. */

    /** verify that it's added last. */

    public void testAddLast_Empty_1_oe()
    {
        final DoubleLinkedList<DoubleLinkedListNode<String>> list = new DoubleLinkedList<>();

        final String payload1 = "payload1";
        final DoubleLinkedListNode<String> node1 = new DoubleLinkedListNode<>( payload1 );

        list.addLast( node1 );

        assertEquals( "Wrong last", node1, list.getLast() );
    }

    public void testAddLast_NotEmpty_1_oe()
    {
        final DoubleLinkedList<DoubleLinkedListNode<String>> list = new DoubleLinkedList<>();

        final String payload1 = "payload1";
        final DoubleLinkedListNode<String> node1 = new DoubleLinkedListNode<>( payload1 );

        final String payload2 = "payload2";
        final DoubleLinkedListNode<String> node2 = new DoubleLinkedListNode<>( payload2 );

        list.addLast( node1 );
        list.addLast( node2 );

        assertEquals( "Wrong last", node2, list.getLast() );
    }

    public void testMakeLast_wasFirst_1_oe()
    {
        final DoubleLinkedList<DoubleLinkedListNode<String>> list = new DoubleLinkedList<>();

        final String payload1 = "payload1";
        final DoubleLinkedListNode<String> node1 = new DoubleLinkedListNode<>( payload1 );

        final String payload2 = "payload2";
        final DoubleLinkedListNode<String> node2 = new DoubleLinkedListNode<>( payload2 );

        list.addFirst( node2 );
        list.addFirst(  node1 );

        list.makeLast( node1 );

        assertEquals( "Wrong size", 2, list.size() );
    }

    public void testMakeLast_wasFirst_2_oe()
    {
        final DoubleLinkedList<DoubleLinkedListNode<String>> list = new DoubleLinkedList<>();

        final String payload1 = "payload1";
        final DoubleLinkedListNode<String> node1 = new DoubleLinkedListNode<>( payload1 );

        final String payload2 = "payload2";
        final DoubleLinkedListNode<String> node2 = new DoubleLinkedListNode<>( payload2 );

        list.addFirst( node2 );
        list.addFirst(  node1 );

        list.makeLast( node1 );

        assertEquals( "Wrong last", node1, list.getLast() );
    }

    public void testMakeLast_wasFirst_3_oe()
    {
        final DoubleLinkedList<DoubleLinkedListNode<String>> list = new DoubleLinkedList<>();

        final String payload1 = "payload1";
        final DoubleLinkedListNode<String> node1 = new DoubleLinkedListNode<>( payload1 );

        final String payload2 = "payload2";
        final DoubleLinkedListNode<String> node2 = new DoubleLinkedListNode<>( payload2 );

        list.addFirst( node2 );
        list.addFirst(  node1 );

        list.makeLast( node1 );

        assertEquals( "Wrong first", node2, list.getFirst() );
    }

    public void testMakeLast_wasLast_1_oe()
    {
        final DoubleLinkedList<DoubleLinkedListNode<String>> list = new DoubleLinkedList<>();

        final String payload1 = "payload1";
        final DoubleLinkedListNode<String> node1 = new DoubleLinkedListNode<>( payload1 );

        final String payload2 = "payload2";
        final DoubleLinkedListNode<String> node2 = new DoubleLinkedListNode<>( payload2 );

        list.addFirst( node1 );
        list.addFirst(  node2 );

        list.makeLast( node1 );

        assertEquals( "Wrong size", 2, list.size() );
    }

    public void testMakeLast_wasLast_2_oe()
    {
        final DoubleLinkedList<DoubleLinkedListNode<String>> list = new DoubleLinkedList<>();

        final String payload1 = "payload1";
        final DoubleLinkedListNode<String> node1 = new DoubleLinkedListNode<>( payload1 );

        final String payload2 = "payload2";
        final DoubleLinkedListNode<String> node2 = new DoubleLinkedListNode<>( payload2 );

        list.addFirst( node1 );
        list.addFirst(  node2 );

        list.makeLast( node1 );

        assertEquals( "Wrong last", node1, list.getLast() );
    }

    public void testMakeLast_wasLast_3_oe()
    {
        final DoubleLinkedList<DoubleLinkedListNode<String>> list = new DoubleLinkedList<>();

        final String payload1 = "payload1";
        final DoubleLinkedListNode<String> node1 = new DoubleLinkedListNode<>( payload1 );

        final String payload2 = "payload2";
        final DoubleLinkedListNode<String> node2 = new DoubleLinkedListNode<>( payload2 );

        list.addFirst( node1 );
        list.addFirst(  node2 );

        list.makeLast( node1 );

        assertEquals( "Wrong first", node2, list.getFirst() );
    }

    public void testMakeLast_wasAlone_1_oe()
    {
        final DoubleLinkedList<DoubleLinkedListNode<String>> list = new DoubleLinkedList<>();

        final String payload1 = "payload1";
        final DoubleLinkedListNode<String> node1 = new DoubleLinkedListNode<>( payload1 );

        list.addFirst( node1 );

        list.makeLast( node1 );

        assertEquals( "Wrong size", 1, list.size() );
    }

    public void testMakeLast_wasAlone_2_oe()
    {
        final DoubleLinkedList<DoubleLinkedListNode<String>> list = new DoubleLinkedList<>();

        final String payload1 = "payload1";
        final DoubleLinkedListNode<String> node1 = new DoubleLinkedListNode<>( payload1 );

        list.addFirst( node1 );

        list.makeLast( node1 );

        assertEquals( "Wrong last", node1, list.getLast() );
    }

    public void testMakeLast_wasAlone_3_oe()
    {
        final DoubleLinkedList<DoubleLinkedListNode<String>> list = new DoubleLinkedList<>();

        final String payload1 = "payload1";
        final DoubleLinkedListNode<String> node1 = new DoubleLinkedListNode<>( payload1 );

        list.addFirst( node1 );

        list.makeLast( node1 );

        assertEquals( "Wrong first", node1, list.getFirst() );
    }

    public void testMakeLast_wasInMiddle_1_oe()
    {
        final DoubleLinkedList<DoubleLinkedListNode<String>> list = new DoubleLinkedList<>();

        final String payload1 = "payload1";
        final DoubleLinkedListNode<String> node1 = new DoubleLinkedListNode<>( payload1 );

        final String payload2 = "payload2";
        final DoubleLinkedListNode<String> node2 = new DoubleLinkedListNode<>( payload2 );

        final String payload3 = "payload3";
        final DoubleLinkedListNode<String> node3 = new DoubleLinkedListNode<>( payload3 );

        list.addFirst( node2 );
        list.addFirst(  node1 );
        list.addFirst(  node3 );

        list.makeLast( node1 );

        assertEquals( "Wrong size", 3, list.size() );
    }

    public void testMakeLast_wasInMiddle_2_oe()
    {
        final DoubleLinkedList<DoubleLinkedListNode<String>> list = new DoubleLinkedList<>();

        final String payload1 = "payload1";
        final DoubleLinkedListNode<String> node1 = new DoubleLinkedListNode<>( payload1 );

        final String payload2 = "payload2";
        final DoubleLinkedListNode<String> node2 = new DoubleLinkedListNode<>( payload2 );

        final String payload3 = "payload3";
        final DoubleLinkedListNode<String> node3 = new DoubleLinkedListNode<>( payload3 );

        list.addFirst( node2 );
        list.addFirst(  node1 );
        list.addFirst(  node3 );

        list.makeLast( node1 );

        assertEquals( "Wrong last", node1, list.getLast() );
    }

    public void testMakeLast_wasInMiddle_3_oe()
    {
        final DoubleLinkedList<DoubleLinkedListNode<String>> list = new DoubleLinkedList<>();

        final String payload1 = "payload1";
        final DoubleLinkedListNode<String> node1 = new DoubleLinkedListNode<>( payload1 );

        final String payload2 = "payload2";
        final DoubleLinkedListNode<String> node2 = new DoubleLinkedListNode<>( payload2 );

        final String payload3 = "payload3";
        final DoubleLinkedListNode<String> node3 = new DoubleLinkedListNode<>( payload3 );

        list.addFirst( node2 );
        list.addFirst(  node1 );
        list.addFirst(  node3 );

        list.makeLast( node1 );

        assertEquals( "Wrong first", node3, list.getFirst() );
    }

}
