/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.collections4.iterators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the IteratorEnumeration.
 *
 */
public class IteratorEnumerationTest_OE25Dev {

@Test
    public void testEnumeration_1_oe() {
        final Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        final IteratorEnumeration<String> enumeration = new IteratorEnumeration<>(iterator);

        assertEquals(iterator, enumeration.getIterator());
    }

@Test
    public void testEnumeration_2_oe() {
        final Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        final IteratorEnumeration<String> enumeration = new IteratorEnumeration<>(iterator);

        // removed other assertion

        assertTrue(enumeration.hasMoreElements());
    }

@Test
    public void testEnumeration_3_oe() {
        final Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        final IteratorEnumeration<String> enumeration = new IteratorEnumeration<>(iterator);

        // removed other assertion

        // removed other assertion
        assertEquals("a", enumeration.nextElement());
    }

@Test
    public void testEnumeration_4_oe() {
        final Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        final IteratorEnumeration<String> enumeration = new IteratorEnumeration<>(iterator);

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("b", enumeration.nextElement());
    }

@Test
    public void testEnumeration_5_oe() {
        final Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        final IteratorEnumeration<String> enumeration = new IteratorEnumeration<>(iterator);

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("c", enumeration.nextElement());
    }

@Test
    public void testEnumeration_6_oe() {
        final Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        final IteratorEnumeration<String> enumeration = new IteratorEnumeration<>(iterator);

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(enumeration.hasMoreElements());
    }

}
