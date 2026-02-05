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

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for array literals.
 * @since 2.0
 */
@SuppressWarnings({"UnnecessaryBoxing", "AssertEqualsBetweenInconvertibleTypes"})
public class ArrayLiteralTest_OE25Dev extends JexlTestCase {

    public ArrayLiteralTest_OE25Dev() {
        super("ArrayLiteralTest_OE25Dev");
    }

    @Test
    public void testEmptyArrayLiteral_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        Object o;
        o = JEXL.createExpression("[]").evaluate(jc);
        Assert.assertTrue(o instanceof Object[]);
    }

    @Test
    public void testEmptyArrayLiteral_2_oe() throws Exception {
        final JexlContext jc = new MapContext();
        Object o;
        o = JEXL.createExpression("[]").evaluate(jc);
        // removed other assertion
        Assert.assertEquals(0, ((Object[]) o).length);
    }

    @Test
    public void testEmptyArrayLiteral_3_oe() throws Exception {
        final JexlContext jc = new MapContext();
        Object o;
        o = JEXL.createExpression("[]").evaluate(jc);
        // removed other assertion
        // removed other assertion
        o = JEXL.createExpression("[...]").evaluate(jc);
        Assert.assertTrue(o instanceof List<?>);
    }

    @Test
    public void testEmptyArrayLiteral_4_oe() throws Exception {
        final JexlContext jc = new MapContext();
        Object o;
        o = JEXL.createExpression("[]").evaluate(jc);
        // removed other assertion
        // removed other assertion
        o = JEXL.createExpression("[...]").evaluate(jc);
        // removed other assertion
        Assert.assertEquals(0, ((List<?>) o).size());
    }

    @Test
    public void testLiteralWithStrings_1_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("[ 'foo' , 'bar' ]");
        final JexlContext jc = new MapContext();

        final Object o = e.evaluate(jc);
        final Object[] check = {"foo", "bar"};
        Assert.assertArrayEquals(check, (Object[]) o);
    }

    @Test
    public void testLiteralWithElipsis_1_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("[ 'foo' , 'bar', ... ]");
        final JexlContext jc = new MapContext();

        final Object o = e.evaluate(jc);
        final Object[] check = {"foo", "bar"};
        Assert.assertEquals(Arrays.asList(check), o);
    }

    @Test
    public void testLiteralWithElipsis_2_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("[ 'foo' , 'bar', ... ]");
        final JexlContext jc = new MapContext();

        final Object o = e.evaluate(jc);
        final Object[] check = {"foo", "bar"};
        // removed other assertion
        Assert.assertEquals(2, ((List<?>) o).size());
    }

    @Test
    public void testLiteralWithOneEntry_1_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("[ 'foo' ]");
        final JexlContext jc = new MapContext();

        final Object o = e.evaluate(jc);
        final Object[] check = {"foo"};
        Assert.assertArrayEquals(check, (Object[]) o);
    }

    @Test
    public void testLiteralWithNumbers_1_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("[ 5.0 , 10 ]");
        final JexlContext jc = new MapContext();

        final Object o = e.evaluate(jc);
        final Object[] check = {new Double(5), new Integer(10)};
        Assert.assertArrayEquals(check, (Object[]) o);
    }

    @Test
    public void testLiteralWithNumbers_2_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("[ 5.0 , 10 ]");
        final JexlContext jc = new MapContext();

        final Object o = e.evaluate(jc);
        final Object[] check = {new Double(5), new Integer(10)};
        // removed other assertion
        Assert.assertTrue(o.getClass().isArray() && o.getClass().getComponentType().equals(Number.class));
    }

    @Test
    public void testLiteralWithNulls_1_oe() throws Exception {
        final String[] exprs = {
            "[ null , 10 ]",
            "[ 10 , null ]",
            "[ 10 , null , 10]",
            "[ '10' , null ]",
            "[ null, '10' , null ]"
        };
        final Object[][] checks = {
            {null, new Integer(10)},
            {new Integer(10), null},
            {new Integer(10), null, new Integer(10)},
            {"10", null},
            {null, "10", null}
        };
        final JexlContext jc = new MapContext();
        for (int t = 0; t < exprs.length; ++t) {
            final JexlExpression e = JEXL.createExpression(exprs[t]);
            final Object o = e.evaluate(jc);
            Assert.assertArrayEquals(exprs[t], checks[t], (Object[]) o);
    }
    }

    @Test
    public void testLiteralWithIntegers_1_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("[ 5 , 10 ]");
        final JexlContext jc = new MapContext();

        final Object o = e.evaluate(jc);
        final int[] check = {5, 10};
        Assert.assertArrayEquals(check, (int[]) o);
    }

    @Test
    public void testSizeOfSimpleArrayLiteral_1_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("size([ 'foo' , 'bar' ])");
        final JexlContext jc = new MapContext();

        final Object o = e.evaluate(jc);
        Assert.assertEquals(new Integer(2), o);
    }

    @Test
    public void notestCallingMethodsOnNewMapLiteral_1_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("size({ 'foo' : 'bar' }.values())");
        final JexlContext jc = new MapContext();

        final Object o = e.evaluate(jc);
        Assert.assertEquals(new Integer(1), o);
    }

    @Test
    public void testNotEmptySimpleArrayLiteral_1_oe() throws Exception {
        final JexlExpression e = JEXL.createExpression("empty([ 'foo' , 'bar' ])");
        final JexlContext jc = new MapContext();

        final Object o = e.evaluate(jc);
        Assert.assertFalse((Boolean) o);
    }

    @Test
    public void testChangeThroughVariables_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final JexlExpression e147 = JEXL.createExpression("quux = [one, two]");

        jc.set("one", 1);
        jc.set("two", 2);
        final int[] o1 = (int[]) e147.evaluate(jc);
        Assert.assertEquals(1, o1[0]);
    }

    @Test
    public void testChangeThroughVariables_2_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final JexlExpression e147 = JEXL.createExpression("quux = [one, two]");

        jc.set("one", 1);
        jc.set("two", 2);
        final int[] o1 = (int[]) e147.evaluate(jc);
        // removed other assertion
        Assert.assertEquals(2, o1[1]);
    }

    @Test
    public void testChangeThroughVariables_3_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final JexlExpression e147 = JEXL.createExpression("quux = [one, two]");

        jc.set("one", 1);
        jc.set("two", 2);
        final int[] o1 = (int[]) e147.evaluate(jc);
        // removed other assertion
        // removed other assertion

        jc.set("one", 10);
        jc.set("two", 20);
        final int[] o2 = (int[]) e147.evaluate(jc);
        Assert.assertEquals(10, o2[0]);
    }

    @Test
    public void testChangeThroughVariables_4_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final JexlExpression e147 = JEXL.createExpression("quux = [one, two]");

        jc.set("one", 1);
        jc.set("two", 2);
        final int[] o1 = (int[]) e147.evaluate(jc);
        // removed other assertion
        // removed other assertion

        jc.set("one", 10);
        jc.set("two", 20);
        final int[] o2 = (int[]) e147.evaluate(jc);
        // removed other assertion
        Assert.assertEquals(20, o2[1]);
    }

}
