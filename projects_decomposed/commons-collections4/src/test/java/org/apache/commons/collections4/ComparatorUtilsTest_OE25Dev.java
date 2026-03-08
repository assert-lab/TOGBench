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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;

import org.junit.Test;

/**
 * Tests ComparatorUtils.
 *
 */
public class ComparatorUtilsTest_OE25Dev {

    @Test
    public void booleanComparator_1_oe() {
        Comparator<Boolean> comp = ComparatorUtils.booleanComparator(true);
        assertTrue(comp.compare(Boolean.TRUE, Boolean.FALSE) < 0);
    }

    @Test
    public void booleanComparator_2_oe() {
        Comparator<Boolean> comp = ComparatorUtils.booleanComparator(true);
        assertTrue(comp.compare(Boolean.TRUE, Boolean.TRUE) == 0);
    }

    @Test
    public void booleanComparator_3_oe() {
        Comparator<Boolean> comp = ComparatorUtils.booleanComparator(true);
        assertTrue(comp.compare(Boolean.FALSE, Boolean.TRUE) > 0);
    }

    @Test
    public void booleanComparator_4_oe() {
        Comparator<Boolean> comp = ComparatorUtils.booleanComparator(true);

        comp = ComparatorUtils.booleanComparator(false);
        assertTrue(comp.compare(Boolean.TRUE, Boolean.FALSE) > 0);
    }

    @Test
    public void booleanComparator_5_oe() {
        Comparator<Boolean> comp = ComparatorUtils.booleanComparator(true);

        comp = ComparatorUtils.booleanComparator(false);
        assertTrue(comp.compare(Boolean.TRUE, Boolean.TRUE) == 0);
    }

    @Test
    public void booleanComparator_6_oe() {
        Comparator<Boolean> comp = ComparatorUtils.booleanComparator(true);

        comp = ComparatorUtils.booleanComparator(false);
        assertTrue(comp.compare(Boolean.FALSE, Boolean.TRUE) < 0);
    }

    @Test
    public void chainedComparator_1_oe() {
        final Comparator<Integer> comp = ComparatorUtils.chainedComparator(ComparatorUtils.<Integer>naturalComparator(),
                                                                     ComparatorUtils.<Integer>naturalComparator());
        assertTrue(comp.compare(1, 2) < 0);
    }

    @Test
    public void chainedComparator_2_oe() {
        final Comparator<Integer> comp = ComparatorUtils.chainedComparator(ComparatorUtils.<Integer>naturalComparator(),
                                                                     ComparatorUtils.<Integer>naturalComparator());
        assertTrue(comp.compare(1, 1) == 0);
    }

    @Test
    public void chainedComparator_3_oe() {
        final Comparator<Integer> comp = ComparatorUtils.chainedComparator(ComparatorUtils.<Integer>naturalComparator(),
                                                                     ComparatorUtils.<Integer>naturalComparator());
        assertTrue(comp.compare(2, 1) > 0);
    }

    @Test
    public void max_1_oe() {
        final Comparator<Integer> reversed =
                ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());

        assertEquals(Integer.valueOf(10), ComparatorUtils.max(1, 10, null));
    }

    @Test
    public void max_2_oe() {
        final Comparator<Integer> reversed =
                ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());

        assertEquals(Integer.valueOf(10), ComparatorUtils.max(10, -10, null));
    }

    @Test
    public void max_3_oe() {
        final Comparator<Integer> reversed =
                ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());


        assertEquals(Integer.valueOf(1), ComparatorUtils.max(1, 10, reversed));
    }

    @Test
    public void max_4_oe() {
        final Comparator<Integer> reversed =
                ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());


        assertEquals(Integer.valueOf(-10), ComparatorUtils.max(10, -10, reversed));
    }

    @Test
    public void min_1_oe() {
        final Comparator<Integer> reversed =
                ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());

        assertEquals(Integer.valueOf(1), ComparatorUtils.min(1, 10, null));
    }

    @Test
    public void min_2_oe() {
        final Comparator<Integer> reversed =
                ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());

        assertEquals(Integer.valueOf(-10), ComparatorUtils.min(10, -10, null));
    }

    @Test
    public void min_3_oe() {
        final Comparator<Integer> reversed =
                ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());


        assertEquals(Integer.valueOf(10), ComparatorUtils.min(1, 10, reversed));
    }

    @Test
    public void min_4_oe() {
        final Comparator<Integer> reversed =
                ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());


        assertEquals(Integer.valueOf(10), ComparatorUtils.min(10, -10, reversed));
    }

    @Test
    public void nullLowComparator_1_oe() {
        final Comparator<Integer> comp = ComparatorUtils.nullLowComparator(null);
        assertTrue(comp.compare(null, 10) < 0);
    }

    @Test
    public void nullLowComparator_2_oe() {
        final Comparator<Integer> comp = ComparatorUtils.nullLowComparator(null);
        assertTrue(comp.compare(null, null) == 0);
    }

    @Test
    public void nullLowComparator_3_oe() {
        final Comparator<Integer> comp = ComparatorUtils.nullLowComparator(null);
        assertTrue(comp.compare(10, null) > 0);
    }

    @Test
    public void nullHighComparator_1_oe() {
        final Comparator<Integer> comp = ComparatorUtils.nullHighComparator(null);
        assertTrue(comp.compare(null, 10) > 0);
    }

    @Test
    public void nullHighComparator_2_oe() {
        final Comparator<Integer> comp = ComparatorUtils.nullHighComparator(null);
        assertTrue(comp.compare(null, null) == 0);
    }

    @Test
    public void nullHighComparator_3_oe() {
        final Comparator<Integer> comp = ComparatorUtils.nullHighComparator(null);
        assertTrue(comp.compare(10, null) < 0);
    }

@Test
    public void max_oe_101_oe() {
        try {
            ComparatorUtils.max(1, null, null);
            fail("expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }

@Test
    public void max_oe_102_oe() {
        try {
            ComparatorUtils.max(null, 10, null);
            fail("expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }

@Test
    public void min_oe_101_oe() {
        try {
            ComparatorUtils.min(1, null, null);
            fail("expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }

@Test
    public void min_oe_102_oe() {
        try {
            ComparatorUtils.min(null, 10, null);
            fail("expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }

}
