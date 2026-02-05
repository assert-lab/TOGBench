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
 * Tests the LoopingIterator class.
 *
 */
public class LoopingIteratorTest_OE25Dev {

    /**
     * Tests constructor exception.
     */
    @Test
    public void testConstructorEx() throws Exception {
        try {
            new LoopingIterator<>(null);
            fail();
        } catch (final NullPointerException ex) {
        }
    }

    /**
     * Tests whether an empty looping iterator works as designed.
     * @throws Exception  If something unexpected occurs.
     */

    /**
     * Tests whether a populated looping iterator works as designed.
     * @throws Exception  If something unexpected occurs.
     */

    /**
     * Tests whether a populated looping iterator works as designed.
     * @throws Exception  If something unexpected occurs.
     */

    /**
     * Tests whether a populated looping iterator works as designed.
     * @throws Exception  If something unexpected occurs.
     */

    /**
     * Tests the remove() method on a LoopingIterator wrapped ArrayList.
     * @throws Exception  If something unexpected occurs.
     */

    /**
     * Tests the reset() method on a LoopingIterator wrapped ArrayList.
     * @throws Exception  If something unexpected occurs.
     */

    /**
     * Tests the size() method on a LoopingIterator wrapped ArrayList.
     * @throws Exception  If something unexpected occurs.
     */

@Test
    public void testLooping0_1_oe() throws Exception {
        final List<Object> list = new ArrayList<>();
        final LoopingIterator<Object> loop = new LoopingIterator<>(list);
        assertTrue("hasNext should return false", !loop.hasNext());
    }

@Test
    public void testLooping1_1_oe() throws Exception {
        final List<String> list = Arrays.asList("a");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        assertTrue("1st hasNext should return true", loop.hasNext());
    }

@Test
    public void testLooping1_2_oe() throws Exception {
        final List<String> list = Arrays.asList("a");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        assertEquals("a", loop.next());
    }

@Test
    public void testLooping1_3_oe() throws Exception {
        final List<String> list = Arrays.asList("a");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion

        assertTrue("2nd hasNext should return true", loop.hasNext());
    }

@Test
    public void testLooping1_4_oe() throws Exception {
        final List<String> list = Arrays.asList("a");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("a", loop.next());
    }

@Test
    public void testLooping1_5_oe() throws Exception {
        final List<String> list = Arrays.asList("a");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertTrue("3rd hasNext should return true", loop.hasNext());
    }

@Test
    public void testLooping1_6_oe() throws Exception {
        final List<String> list = Arrays.asList("a");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("a", loop.next());
    }

@Test
    public void testLooping2_1_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        assertTrue("1st hasNext should return true", loop.hasNext());
    }

@Test
    public void testLooping2_2_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        assertEquals("a", loop.next());
    }

@Test
    public void testLooping2_3_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion

        assertTrue("2nd hasNext should return true", loop.hasNext());
    }

@Test
    public void testLooping2_4_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("b", loop.next());
    }

@Test
    public void testLooping2_5_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertTrue("3rd hasNext should return true", loop.hasNext());
    }

@Test
    public void testLooping2_6_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("a", loop.next());
    }

@Test
    public void testLooping3_1_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        assertTrue("1st hasNext should return true", loop.hasNext());
    }

@Test
    public void testLooping3_2_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        assertEquals("a", loop.next());
    }

@Test
    public void testLooping3_3_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion

        assertTrue("2nd hasNext should return true", loop.hasNext());
    }

@Test
    public void testLooping3_4_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("b", loop.next());
    }

@Test
    public void testLooping3_5_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertTrue("3rd hasNext should return true", loop.hasNext());
    }

@Test
    public void testLooping3_6_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("c", loop.next());
    }

@Test
    public void testLooping3_7_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertTrue("4th hasNext should return true", loop.hasNext());
    }

@Test
    public void testLooping3_8_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("a", loop.next());
    }

@Test
    public void testRemoving1_1_oe() throws Exception {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingIterator<String> loop = new LoopingIterator<>(list);
        assertEquals("list should have 3 elements.", 3, list.size());
    }

@Test
    public void testRemoving1_2_oe() throws Exception {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingIterator<String> loop = new LoopingIterator<>(list);
        // removed other assertion

        assertTrue("1st hasNext should return true", loop.hasNext());
    }

@Test
    public void testRemoving1_3_oe() throws Exception {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingIterator<String> loop = new LoopingIterator<>(list);
        // removed other assertion

        // removed other assertion
        assertEquals("a", loop.next());
    }

@Test
    public void testRemoving1_4_oe() throws Exception {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingIterator<String> loop = new LoopingIterator<>(list);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        loop.remove();  // removes a
        assertEquals("list should have 2 elements.", 2, list.size());
    }

@Test
    public void testRemoving1_5_oe() throws Exception {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingIterator<String> loop = new LoopingIterator<>(list);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        loop.remove();  // removes a
        // removed other assertion

        assertTrue("2nd hasNext should return true", loop.hasNext());
    }

@Test
    public void testRemoving1_6_oe() throws Exception {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingIterator<String> loop = new LoopingIterator<>(list);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        loop.remove();  // removes a
        // removed other assertion

        // removed other assertion
        assertEquals("b", loop.next());
    }

@Test
    public void testRemoving1_7_oe() throws Exception {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingIterator<String> loop = new LoopingIterator<>(list);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        loop.remove();  // removes a
        // removed other assertion

        // removed other assertion
        // removed other assertion
        loop.remove();  // removes b
        assertEquals("list should have 1 elements.", 1, list.size());
    }

@Test
    public void testRemoving1_8_oe() throws Exception {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingIterator<String> loop = new LoopingIterator<>(list);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        loop.remove();  // removes a
        // removed other assertion

        // removed other assertion
        // removed other assertion
        loop.remove();  // removes b
        // removed other assertion

        assertTrue("3rd hasNext should return true", loop.hasNext());
    }

@Test
    public void testRemoving1_9_oe() throws Exception {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingIterator<String> loop = new LoopingIterator<>(list);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        loop.remove();  // removes a
        // removed other assertion

        // removed other assertion
        // removed other assertion
        loop.remove();  // removes b
        // removed other assertion

        // removed other assertion
        assertEquals("c", loop.next());
    }

@Test
    public void testRemoving1_10_oe() throws Exception {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingIterator<String> loop = new LoopingIterator<>(list);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        loop.remove();  // removes a
        // removed other assertion

        // removed other assertion
        // removed other assertion
        loop.remove();  // removes b
        // removed other assertion

        // removed other assertion
        // removed other assertion
        loop.remove();  // removes c
        assertEquals("list should have 0 elements.", 0, list.size());
    }

@Test
    public void testRemoving1_11_oe() throws Exception {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingIterator<String> loop = new LoopingIterator<>(list);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        loop.remove();  // removes a
        // removed other assertion

        // removed other assertion
        // removed other assertion
        loop.remove();  // removes b
        // removed other assertion

        // removed other assertion
        // removed other assertion
        loop.remove();  // removes c
        // removed other assertion

        assertFalse("4th hasNext should return false", loop.hasNext());
    }

@Test
    public void testReset_1_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        assertEquals("a", loop.next());
    }

@Test
    public void testReset_2_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        assertEquals("b", loop.next());
    }

@Test
    public void testReset_3_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion
        loop.reset();
        assertEquals("a", loop.next());
    }

@Test
    public void testReset_4_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion
        loop.reset();
        // removed other assertion
        loop.reset();
        assertEquals("a", loop.next());
    }

@Test
    public void testReset_5_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion
        loop.reset();
        // removed other assertion
        loop.reset();
        // removed other assertion
        assertEquals("b", loop.next());
    }

@Test
    public void testReset_6_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion
        loop.reset();
        // removed other assertion
        loop.reset();
        // removed other assertion
        // removed other assertion
        assertEquals("c", loop.next());
    }

@Test
    public void testReset_7_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion
        loop.reset();
        // removed other assertion
        loop.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        loop.reset();
        assertEquals("a", loop.next());
    }

@Test
    public void testReset_8_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion
        loop.reset();
        // removed other assertion
        loop.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        loop.reset();
        // removed other assertion
        assertEquals("b", loop.next());
    }

@Test
    public void testReset_9_oe() throws Exception {
        final List<String> list = Arrays.asList("a", "b", "c");
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        // removed other assertion
        loop.reset();
        // removed other assertion
        loop.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        loop.reset();
        // removed other assertion
        // removed other assertion
        assertEquals("c", loop.next());
    }

@Test
    public void testSize_1_oe() throws Exception {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        assertEquals(3, loop.size());
    }

@Test
    public void testSize_2_oe() throws Exception {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        loop.next();
        loop.next();
        assertEquals(3, loop.size());
    }

@Test
    public void testSize_3_oe() throws Exception {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        loop.next();
        loop.next();
        // removed other assertion
        loop.reset();
        assertEquals(3, loop.size());
    }

@Test
    public void testSize_4_oe() throws Exception {
        final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        final LoopingIterator<String> loop = new LoopingIterator<>(list);

        // removed other assertion
        loop.next();
        loop.next();
        // removed other assertion
        loop.reset();
        // removed other assertion
        loop.next();
        loop.remove();
        assertEquals(2, loop.size());
    }

}
