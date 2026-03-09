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
package org.apache.commons.jexl3;

import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for ranges.
 * @since 3.0
 */
@SuppressWarnings({"UnnecessaryBoxing", "AssertEqualsBetweenInconvertibleTypes"})
public class RangeTest_OE25Dev extends JexlTestCase {

    public RangeTest_OE25Dev() {
        super("RangeTest_OE25Dev");
    }

    @Test
    public void testIntegerRangeOne() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..1)");
        final JexlContext jc = new MapContext();

        final Object o = e.evaluate(jc);
        Assert.assertTrue(o instanceof Collection<?>);
        final Collection<?> c = (Collection<?>) o;
        Assert.assertEquals(1, c.size());
        final Object[] a = c.toArray();
        Assert.assertEquals(1, a.length);
        Assert.assertEquals(1, ((Number) a[0]).intValue());
        Assert.assertFalse((Boolean) JEXL.createScript("empty x", "x").execute(null, e));
    }

    @Test
    public void testIntegerRange() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..32)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        Assert.assertTrue(o instanceof Collection<?>);
        final Collection<?> c = (Collection<?>) o;
        Assert.assertEquals(32, c.size());

        Assert.assertNotSame(o0, o);
        Assert.assertEquals(o0.hashCode(), o.hashCode());
        Assert.assertEquals(o0, o);

        int i = 0;
        for (final Object v : c) {
            i += 1;
            Assert.assertEquals(i, ((Number) v).intValue());
        }
        Assert.assertEquals(32, i);

        Integer[] aa = c.<Integer>toArray(new Integer[32]);
        Assert.assertEquals(32, aa.length);
        for (int l = 0; l < 32; ++l) {
            Assert.assertEquals((int) aa[l], l + 1);
        }

        aa = c.<Integer>toArray(new Integer[2]);
        Assert.assertEquals(32, aa.length);
        for (int l = 0; l < 32; ++l) {
            Assert.assertEquals((int) aa[l], l + 1);
        }

        aa = c.<Integer>toArray(new Integer[34]);
        Assert.assertEquals(34, aa.length);
        for (int l = 0; l < 32; ++l) {
            Assert.assertEquals((int) aa[l], l + 1);
        }

        final Object[] oaa = c.toArray();
        Assert.assertEquals(32, oaa.length);
        for (int l = 0; l < 32; ++l) {
            Assert.assertEquals(oaa[l], l + 1);
        }
    }

    @Test
    public void testLongRange() throws Exception {
        final JexlExpression e = JEXL.createExpression("(6789000001L..6789000032L)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        Assert.assertTrue(o instanceof Collection<?>);
        final Collection<?> c = (Collection<?>) o;
        Assert.assertEquals(32, c.size());
        Assert.assertFalse((Boolean) JEXL.createScript("empty x", "x").execute(null, e));

        Assert.assertNotSame(o0, o);
        Assert.assertEquals(o0.hashCode(), o.hashCode());
        Assert.assertEquals(o0, o);

        long i = 6789000000L;
        for (final Object v : c) {
            i += 1;
            Assert.assertEquals(i, ((Number) v).longValue());
        }
        Assert.assertEquals(6789000032L, i);

        Long[] aa = c.<Long>toArray(new Long[32]);
        Assert.assertEquals(32, aa.length);
        for (int l = 0; l < 32; ++l) {
            Assert.assertEquals((long) aa[l], 6789000001L + l);
        }

        aa = c.<Long>toArray(new Long[2]);
        Assert.assertEquals(32, aa.length);
        for (int l = 0; l < 32; ++l) {
            Assert.assertEquals((long) aa[l], 6789000001L + l);
        }

        aa = c.<Long>toArray(new Long[34]);
        Assert.assertEquals(34, aa.length);
        for (int l = 0; l < 32; ++l) {
            Assert.assertEquals((long) aa[l], 6789000001L + l);
        }

        final Object[] oaa = c.toArray();
        Assert.assertEquals(32, oaa.length);
        for (int l = 0; l < 32; ++l) {
            Assert.assertEquals(oaa[l], 6789000001L + l);
        }
    }

    @Test
    public void testIntegerSum() throws Exception {
        final JexlScript e = JEXL.createScript("var s = 0; for(var i : (1..5)) { s = s + i; }; s");
        final JexlContext jc = new MapContext();

        final Object o = e.execute(jc);
        Assert.assertEquals(15, ((Number) o).intValue());
    }

    @Test
    public void testIntegerContains() throws Exception {
        final JexlScript e = JEXL.createScript("(x)->{ x =~ (1..10) }");
        final JexlContext jc = new MapContext();

        Object o = e.execute(jc, 5);
        Assert.assertEquals(Boolean.TRUE, o);
        o = e.execute(jc, 0);
        Assert.assertEquals(Boolean.FALSE, o);
        o = e.execute(jc, 100);
        Assert.assertEquals(Boolean.FALSE, o);
    }

    @Test
    public void testLongSum() throws Exception {
        final JexlScript e = JEXL.createScript("var s = 0; for(var i : (6789000001L..6789000001L)) { s = s + i; }; s");
        final JexlContext jc = new MapContext();

        final Object o = e.execute(jc);
        Assert.assertEquals(6789000001L, ((Number) o).longValue());
    }

    @Test
    public void testLongContains() throws Exception {
        final JexlScript e = JEXL.createScript("(x)->{ x =~ (90000000001L..90000000010L) }");
        final JexlContext jc = new MapContext();

        Object o = e.execute(jc, 90000000005L);
        Assert.assertEquals(Boolean.TRUE, o);
        o = e.execute(jc, 0);
        Assert.assertEquals(Boolean.FALSE, o);
        o = e.execute(jc, 90000000011L);
        Assert.assertEquals(Boolean.FALSE, o);
    }

    @Test
    public void testIntegerRangeOne_1_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..1)");
        final JexlContext jc = new MapContext();

        final Object o = e.evaluate(jc);
        Assert.assertTrue(o instanceof Collection<?>);
    }

    @Test
    public void testIntegerRangeOne_2_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..1)");
        final JexlContext jc = new MapContext();

        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;
        Assert.assertEquals(1, c.size());
    }

    @Test
    public void testIntegerRangeOne_3_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..1)");
        final JexlContext jc = new MapContext();

        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;
        final Object[] a = c.toArray();
        Assert.assertEquals(1, a.length);
    }

    @Test
    public void testIntegerRangeOne_4_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..1)");
        final JexlContext jc = new MapContext();

        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;
        final Object[] a = c.toArray();
        Assert.assertEquals(1, ((Number) a[0]).intValue());
    }

    @Test
    public void testIntegerRangeOne_5_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..1)");
        final JexlContext jc = new MapContext();

        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;
        final Object[] a = c.toArray();
        Assert.assertFalse((Boolean) JEXL.createScript("empty x", "x").execute(null, e));
    }

    @Test
    public void testIntegerRange_1_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..32)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        Assert.assertTrue(o instanceof Collection<?>);
    }

    @Test
    public void testIntegerRange_2_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..32)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;
        Assert.assertEquals(32, c.size());
    }

    @Test
    public void testIntegerRange_3_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..32)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;

        Assert.assertNotSame(o0, o);
    }

    @Test
    public void testIntegerRange_4_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..32)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;

        Assert.assertEquals(o0.hashCode(), o.hashCode());
    }

    @Test
    public void testIntegerRange_5_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..32)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;

        Assert.assertEquals(o0, o);
    }

    @Test
    public void testIntegerRange_6_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..32)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        int i = 0;
        for (final Object v : c) {
            i += 1;
            Assert.assertEquals(i, ((Number) v).intValue());
    }
    }

    @Test
    public void testIntegerRange_7_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..32)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        int i = 0;
        for (final Object v : c) {
            i += 1;
        }
        Assert.assertEquals(32, i);
    }

    @Test
    public void testIntegerRange_8_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..32)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        int i = 0;
        for (final Object v : c) {
            i += 1;
        }

        Integer[] aa = c.<Integer>toArray(new Integer[32]);
        Assert.assertEquals(32, aa.length);
    }

    @Test
    public void testIntegerRange_9_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..32)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        int i = 0;
        for (final Object v : c) {
            i += 1;
        }

        Integer[] aa = c.<Integer>toArray(new Integer[32]);
        for (int l = 0; l < 32; ++l) {
            Assert.assertEquals((int) aa[l], l + 1);
    }
    }

    @Test
    public void testIntegerRange_10_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..32)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        int i = 0;
        for (final Object v : c) {
            i += 1;
        }

        Integer[] aa = c.<Integer>toArray(new Integer[32]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Integer>toArray(new Integer[2]);
        Assert.assertEquals(32, aa.length);
    }

    @Test
    public void testIntegerRange_11_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..32)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        int i = 0;
        for (final Object v : c) {
            i += 1;
        }

        Integer[] aa = c.<Integer>toArray(new Integer[32]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Integer>toArray(new Integer[2]);
        for (int l = 0; l < 32; ++l) {
            Assert.assertEquals((int) aa[l], l + 1);
    }
    }

    @Test
    public void testIntegerRange_12_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..32)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        int i = 0;
        for (final Object v : c) {
            i += 1;
        }

        Integer[] aa = c.<Integer>toArray(new Integer[32]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Integer>toArray(new Integer[2]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Integer>toArray(new Integer[34]);
        Assert.assertEquals(34, aa.length);
    }

    @Test
    public void testIntegerRange_13_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..32)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        int i = 0;
        for (final Object v : c) {
            i += 1;
        }

        Integer[] aa = c.<Integer>toArray(new Integer[32]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Integer>toArray(new Integer[2]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Integer>toArray(new Integer[34]);
        for (int l = 0; l < 32; ++l) {
            Assert.assertEquals((int) aa[l], l + 1);
    }
    }

    @Test
    public void testIntegerRange_14_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..32)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        int i = 0;
        for (final Object v : c) {
            i += 1;
        }

        Integer[] aa = c.<Integer>toArray(new Integer[32]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Integer>toArray(new Integer[2]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Integer>toArray(new Integer[34]);
        for (int l = 0; l < 32; ++l) {
        }

        final Object[] oaa = c.toArray();
        Assert.assertEquals(32, oaa.length);
    }

    @Test
    public void testIntegerRange_15_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(1..32)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        int i = 0;
        for (final Object v : c) {
            i += 1;
        }

        Integer[] aa = c.<Integer>toArray(new Integer[32]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Integer>toArray(new Integer[2]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Integer>toArray(new Integer[34]);
        for (int l = 0; l < 32; ++l) {
        }

        final Object[] oaa = c.toArray();
        for (int l = 0; l < 32; ++l) {
            Assert.assertEquals(oaa[l], l + 1);
    }
    }

    @Test
    public void testLongRange_1_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(6789000001L..6789000032L)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        Assert.assertTrue(o instanceof Collection<?>);
    }

    @Test
    public void testLongRange_2_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(6789000001L..6789000032L)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;
        Assert.assertEquals(32, c.size());
    }

    @Test
    public void testLongRange_3_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(6789000001L..6789000032L)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;
        Assert.assertFalse((Boolean) JEXL.createScript("empty x", "x").execute(null, e));
    }

    @Test
    public void testLongRange_4_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(6789000001L..6789000032L)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;

        Assert.assertNotSame(o0, o);
    }

    @Test
    public void testLongRange_5_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(6789000001L..6789000032L)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;

        Assert.assertEquals(o0.hashCode(), o.hashCode());
    }

    @Test
    public void testLongRange_6_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(6789000001L..6789000032L)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;

        Assert.assertEquals(o0, o);
    }

    @Test
    public void testLongRange_7_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(6789000001L..6789000032L)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        long i = 6789000000L;
        for (final Object v : c) {
            i += 1;
            Assert.assertEquals(i, ((Number) v).longValue());
    }
    }

    @Test
    public void testLongRange_8_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(6789000001L..6789000032L)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        long i = 6789000000L;
        for (final Object v : c) {
            i += 1;
        }
        Assert.assertEquals(6789000032L, i);
    }

    @Test
    public void testLongRange_9_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(6789000001L..6789000032L)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        long i = 6789000000L;
        for (final Object v : c) {
            i += 1;
        }

        Long[] aa = c.<Long>toArray(new Long[32]);
        Assert.assertEquals(32, aa.length);
    }

    @Test
    public void testLongRange_10_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(6789000001L..6789000032L)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        long i = 6789000000L;
        for (final Object v : c) {
            i += 1;
        }

        Long[] aa = c.<Long>toArray(new Long[32]);
        for (int l = 0; l < 32; ++l) {
            Assert.assertEquals((long) aa[l], 6789000001L + l);
    }
    }

    @Test
    public void testLongRange_11_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(6789000001L..6789000032L)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        long i = 6789000000L;
        for (final Object v : c) {
            i += 1;
        }

        Long[] aa = c.<Long>toArray(new Long[32]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Long>toArray(new Long[2]);
        Assert.assertEquals(32, aa.length);
    }

    @Test
    public void testLongRange_12_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(6789000001L..6789000032L)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        long i = 6789000000L;
        for (final Object v : c) {
            i += 1;
        }

        Long[] aa = c.<Long>toArray(new Long[32]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Long>toArray(new Long[2]);
        for (int l = 0; l < 32; ++l) {
            Assert.assertEquals((long) aa[l], 6789000001L + l);
    }
    }

    @Test
    public void testLongRange_13_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(6789000001L..6789000032L)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        long i = 6789000000L;
        for (final Object v : c) {
            i += 1;
        }

        Long[] aa = c.<Long>toArray(new Long[32]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Long>toArray(new Long[2]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Long>toArray(new Long[34]);
        Assert.assertEquals(34, aa.length);
    }

    @Test
    public void testLongRange_14_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(6789000001L..6789000032L)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        long i = 6789000000L;
        for (final Object v : c) {
            i += 1;
        }

        Long[] aa = c.<Long>toArray(new Long[32]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Long>toArray(new Long[2]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Long>toArray(new Long[34]);
        for (int l = 0; l < 32; ++l) {
            Assert.assertEquals((long) aa[l], 6789000001L + l);
    }
    }

    @Test
    public void testLongRange_15_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(6789000001L..6789000032L)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        long i = 6789000000L;
        for (final Object v : c) {
            i += 1;
        }

        Long[] aa = c.<Long>toArray(new Long[32]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Long>toArray(new Long[2]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Long>toArray(new Long[34]);
        for (int l = 0; l < 32; ++l) {
        }

        final Object[] oaa = c.toArray();
        Assert.assertEquals(32, oaa.length);
    }

    @Test
    public void testLongRange_16_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("(6789000001L..6789000032L)");
        final JexlContext jc = new MapContext();

        final Object o0 = e.evaluate(jc);
        final Object o = e.evaluate(jc);
        final Collection<?> c = (Collection<?>) o;


        long i = 6789000000L;
        for (final Object v : c) {
            i += 1;
        }

        Long[] aa = c.<Long>toArray(new Long[32]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Long>toArray(new Long[2]);
        for (int l = 0; l < 32; ++l) {
        }

        aa = c.<Long>toArray(new Long[34]);
        for (int l = 0; l < 32; ++l) {
        }

        final Object[] oaa = c.toArray();
        for (int l = 0; l < 32; ++l) {
            Assert.assertEquals(oaa[l], 6789000001L + l);
    }
    }

    @Test
    public void testIntegerSum_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("var s = 0; for(var i : (1..5)) { s = s + i; }; s");
        final JexlContext jc = new MapContext();

        final Object o = e.execute(jc);
        Assert.assertEquals(15, ((Number) o).intValue());
    }

    @Test
    public void testIntegerContains_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("(x)->{ x =~ (1..10) }");
        final JexlContext jc = new MapContext();

        Object o = e.execute(jc, 5);
        Assert.assertEquals(Boolean.TRUE, o);
    }

    @Test
    public void testIntegerContains_2_oe() throws Exception {
        final JexlScript e = JEXL.createScript("(x)->{ x =~ (1..10) }");
        final JexlContext jc = new MapContext();

        Object o = e.execute(jc, 5);
        o = e.execute(jc, 0);
        Assert.assertEquals(Boolean.FALSE, o);
    }

    @Test
    public void testIntegerContains_3_oe() throws Exception {
        final JexlScript e = JEXL.createScript("(x)->{ x =~ (1..10) }");
        final JexlContext jc = new MapContext();

        Object o = e.execute(jc, 5);
        o = e.execute(jc, 0);
        o = e.execute(jc, 100);
        Assert.assertEquals(Boolean.FALSE, o);
    }

    @Test
    public void testLongSum_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("var s = 0; for(var i : (6789000001L..6789000001L)) { s = s + i; }; s");
        final JexlContext jc = new MapContext();

        final Object o = e.execute(jc);
        Assert.assertEquals(6789000001L, ((Number) o).longValue());
    }

    @Test
    public void testLongContains_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("(x)->{ x =~ (90000000001L..90000000010L) }");
        final JexlContext jc = new MapContext();

        Object o = e.execute(jc, 90000000005L);
        Assert.assertEquals(Boolean.TRUE, o);
    }

    @Test
    public void testLongContains_2_oe() throws Exception {
        final JexlScript e = JEXL.createScript("(x)->{ x =~ (90000000001L..90000000010L) }");
        final JexlContext jc = new MapContext();

        Object o = e.execute(jc, 90000000005L);
        o = e.execute(jc, 0);
        Assert.assertEquals(Boolean.FALSE, o);
    }

    @Test
    public void testLongContains_3_oe() throws Exception {
        final JexlScript e = JEXL.createScript("(x)->{ x =~ (90000000001L..90000000010L) }");
        final JexlContext jc = new MapContext();

        Object o = e.execute(jc, 90000000005L);
        o = e.execute(jc, 0);
        o = e.execute(jc, 90000000011L);
        Assert.assertEquals(Boolean.FALSE, o);
    }

}
