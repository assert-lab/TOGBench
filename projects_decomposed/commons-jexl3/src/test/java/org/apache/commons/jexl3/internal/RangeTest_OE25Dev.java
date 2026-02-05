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
package org.apache.commons.jexl3.internal;

import org.apache.commons.jexl3.JexlTestCase;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Basic checks on ranges.
 */
@SuppressWarnings({"UnnecessaryBoxing", "AssertEqualsBetweenInconvertibleTypes"})
public class RangeTest_OE25Dev extends JexlTestCase {

    public RangeTest_OE25Dev() {
        super("InternalTest");
    }

    @Before
    @Override
    public void setUp() throws Exception {
        // ensure jul logging is only error
        java.util.logging.Logger.getLogger(org.apache.commons.jexl3.JexlEngine.class.getName()).setLevel(java.util.logging.Level.SEVERE);
    }

    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    private void checkIteration(final LongRange lr, final long first, final long last) throws Exception {
        final Iterator<Long> ii = lr.iterator();
        if (ii.hasNext()) {
            long l = ii.next();
            Assert.assertEquals(first, l);
            while(ii.hasNext()) {
                l = ii.next();
            }
            Assert.assertEquals(last, l);
        } else {
            Assert.fail("empty iterator?");
        }
    }

    private void checkIteration(final IntegerRange ir, final int first, final int last) throws Exception {
        final Iterator<Integer> ii = ir.iterator();
        if (ii.hasNext()) {
            int l = ii.next();
            Assert.assertEquals(first, l);
            while(ii.hasNext()) {
                l = ii.next();
            }
            Assert.assertEquals(last, l);
        } else {
            Assert.fail("empty iterator?");
        }
    }

@Test
    public void testRanges_1_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        Assert.assertEquals(10L, lr0.getMin());
    }

@Test
    public void testRanges_2_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        Assert.assertEquals(20L, lr0.getMax());
    }

@Test
    public void testRanges_3_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(lr0.isEmpty());
    }

@Test
    public void testRanges_4_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(lr0.contains(10L));
    }

@Test
    public void testRanges_5_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(lr0.contains(20L));
    }

@Test
    public void testRanges_6_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(lr0.contains(30L));
    }

@Test
    public void testRanges_7_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(lr0.contains(5L));
    }

@Test
    public void testRanges_8_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(lr0.contains(null));
    }

@Test
    public void testRanges_9_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        Assert.assertTrue(lr0.containsAll(lr1));
    }

@Test
    public void testRanges_10_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        Assert.assertNotEquals(lr0, lr2);
    }

@Test
    public void testRanges_11_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        Assert.assertTrue(lr0.containsAll(lr2));
    }

@Test
    public void testRanges_12_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(lr2.containsAll(lr1));
    }

@Test
    public void testRanges_13_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        Assert.assertEquals(10, ir0.getMin());
    }

@Test
    public void testRanges_14_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        // removed other assertion
        Assert.assertEquals(20, ir0.getMax());
    }

@Test
    public void testRanges_15_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(ir0.isEmpty());
    }

@Test
    public void testRanges_16_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(ir0.contains(10));
    }

@Test
    public void testRanges_17_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(ir0.contains(20));
    }

@Test
    public void testRanges_18_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(ir0.contains(30));
    }

@Test
    public void testRanges_19_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(ir0.contains(5));
    }

@Test
    public void testRanges_20_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(ir0.contains(null));
    }

@Test
    public void testRanges_21_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir1 = IntegerRange.create(10,20);
        checkIteration(ir1, 10, 20);
        Assert.assertTrue(ir0.containsAll(ir1));
    }

@Test
    public void testRanges_22_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir1 = IntegerRange.create(10,20);
        checkIteration(ir1, 10, 20);
        // removed other assertion
        Assert.assertNotEquals(ir0, lr0);
    }

@Test
    public void testRanges_23_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir1 = IntegerRange.create(10,20);
        checkIteration(ir1, 10, 20);
        // removed other assertion
        // removed other assertion
        Assert.assertNotEquals(ir1, lr1);
    }

@Test
    public void testRanges_24_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir1 = IntegerRange.create(10,20);
        checkIteration(ir1, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir2 = IntegerRange.create(10,15);
        Assert.assertNotEquals(ir0, ir2);
    }

@Test
    public void testRanges_25_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir1 = IntegerRange.create(10,20);
        checkIteration(ir1, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir2 = IntegerRange.create(10,15);
        // removed other assertion
        Assert.assertTrue(ir0.containsAll(ir2));
    }

@Test
    public void testRanges_26_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir1 = IntegerRange.create(10,20);
        checkIteration(ir1, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir2 = IntegerRange.create(10,15);
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(ir2.containsAll(ir1));
    }

@Test
    public void testRanges_27_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir1 = IntegerRange.create(10,20);
        checkIteration(ir1, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir2 = IntegerRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        long lc0 = 20;
        final Iterator<Long> il0 = lr0.iterator();
        while(il0.hasNext()) {
            final long v0 = il0.next();
            Assert.assertEquals(lc0, v0);
    }
    }

@Test
    public void testRanges_28_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir1 = IntegerRange.create(10,20);
        checkIteration(ir1, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir2 = IntegerRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        long lc0 = 20;
        final Iterator<Long> il0 = lr0.iterator();
        while(il0.hasNext()) {
            final long v0 = il0.next();
            // removed other assertion
            try {
                switch((int)v0) {
                    case 10:  il0.remove(); Assert.fail(); break;
                    case 11: lr1.add(v0); Assert.fail(); break;
                    case 12: lr1.remove(v0); Assert.fail(); break;
                    case 13: lr1.addAll(Collections.singletonList(v0)); Assert.fail(); break;
                    case 14: lr1.removeAll(Collections.singletonList(v0)); Assert.fail(); break;
                    case 15: lr1.retainAll(Collections.singletonList(v0)); Assert.fail(); break;
                }
            } catch(final UnsupportedOperationException xuo) {
                // ok
            }
            lc0 -= 1;
        }
        Assert.assertEquals(9L, lc0);
    }

@Test
    public void testRanges_30_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir1 = IntegerRange.create(10,20);
        checkIteration(ir1, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir2 = IntegerRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        long lc0 = 20;
        final Iterator<Long> il0 = lr0.iterator();
        while(il0.hasNext()) {
            final long v0 = il0.next();
            // removed other assertion
            try {
                switch((int)v0) {
                    case 10:  il0.remove(); Assert.fail(); break;
                    case 11: lr1.add(v0); Assert.fail(); break;
                    case 12: lr1.remove(v0); Assert.fail(); break;
                    case 13: lr1.addAll(Collections.singletonList(v0)); Assert.fail(); break;
                    case 14: lr1.removeAll(Collections.singletonList(v0)); Assert.fail(); break;
                    case 15: lr1.retainAll(Collections.singletonList(v0)); Assert.fail(); break;
                }
            } catch(final UnsupportedOperationException xuo) {
                // ok
            }
            lc0 -= 1;
        }
        // removed other assertion
        try {
            il0.next();
            // removed other assertion
        } catch(final NoSuchElementException xns) {
            // ok
        }

        int ic0 = 20;
        final Iterator<Integer> ii0 = ir0.iterator();
        while(ii0.hasNext()) {
            final int v0 = ii0.next();
            Assert.assertEquals(ic0, v0);
    }
    }

@Test
    public void testRanges_31_oe() throws Exception {
        final LongRange lr0 = LongRange.create(20,10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        checkIteration(lr0, 20L, 10L);
        final LongRange lr1 = LongRange.create(10,20);
        checkIteration(lr1, 10L, 20L);
        // removed other assertion
        final LongRange lr2 = LongRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir0 = IntegerRange.create(20,10);
        checkIteration(ir0, 20, 10);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir1 = IntegerRange.create(10,20);
        checkIteration(ir1, 10, 20);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final IntegerRange ir2 = IntegerRange.create(10,15);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        long lc0 = 20;
        final Iterator<Long> il0 = lr0.iterator();
        while(il0.hasNext()) {
            final long v0 = il0.next();
            // removed other assertion
            try {
                switch((int)v0) {
                    case 10:  il0.remove(); Assert.fail(); break;
                    case 11: lr1.add(v0); Assert.fail(); break;
                    case 12: lr1.remove(v0); Assert.fail(); break;
                    case 13: lr1.addAll(Collections.singletonList(v0)); Assert.fail(); break;
                    case 14: lr1.removeAll(Collections.singletonList(v0)); Assert.fail(); break;
                    case 15: lr1.retainAll(Collections.singletonList(v0)); Assert.fail(); break;
                }
            } catch(final UnsupportedOperationException xuo) {
                // ok
            }
            lc0 -= 1;
        }
        // removed other assertion
        try {
            il0.next();
            // removed other assertion
        } catch(final NoSuchElementException xns) {
            // ok
        }

        int ic0 = 20;
        final Iterator<Integer> ii0 = ir0.iterator();
        while(ii0.hasNext()) {
            final int v0 = ii0.next();
            // removed other assertion
            try {
                switch(v0) {
                    case 10: ii0.remove(); Assert.fail(); break;
                    case 11: ir1.add(v0); Assert.fail(); break;
                    case 12: ir1.remove(v0); Assert.fail(); break;
                    case 13: ir1.addAll(Collections.singletonList(v0)); Assert.fail(); break;
                    case 14: ir1.removeAll(Collections.singletonList(v0)); Assert.fail(); break;
                    case 15: ir1.retainAll(Collections.singletonList(v0)); Assert.fail(); break;
                }
            } catch(final UnsupportedOperationException xuo) {
                // ok
            }
            ic0 -= 1;
        }
        Assert.assertEquals(9, ic0);
    }

}

