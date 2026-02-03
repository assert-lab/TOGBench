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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.jexl3.parser.Parser;
import org.junit.Assert;
import org.junit.Test;

/**
 * Simple test cases.
 *
 * @since 1.0
 */
@SuppressWarnings({"UnnecessaryBoxing", "AssertEqualsBetweenInconvertibleTypes"})
public class JexlTest_OE25Dev extends JexlTestCase {
    protected static final String METHOD_STRING = "Method string";
    protected static final String GET_METHOD_STRING = "GetMethod string";

    public JexlTest_OE25Dev() {
        super("JexlTest_OE25Dev");
    }

    /**
     * test a simple property expression
     */
    @Test
    public void testProperty() throws Exception {
        /*
         *  tests a simple property expression
         */

        final JexlExpression e = JEXL.createExpression("foo.bar");
        final JexlContext jc = new MapContext();

        jc.set("foo", new Foo());
        final Object o = e.evaluate(jc);

        Assert.assertTrue("o not instanceof String", o instanceof String);
        Assert.assertEquals("o incorrect", GET_METHOD_STRING, o);
    }

    /**
     * test the new function e.g constructor invocation
     */
    @Test
    public void testNew() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("double", Double.class);
        jc.set("foo", "org.apache.commons.jexl3.Foo");
        JexlExpression expr;
        Object value;
        expr = JEXL.createExpression("new(double, 1)");
        value = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), new Double(1.0), value);
        expr = JEXL.createExpression("new('java.lang.Float', 100)");
        value = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), new Float(100.0), value);
        expr = JEXL.createExpression("new(foo).quux");
        value = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), "String : quux", value);
    }

    /**
     * test some simple mathematical calculations
     */

    /**
     * test some simple conditions
     */

    /**
     * test some simple conditions
     */

    /**
     * test some simple conditions
     */

    /**
     * test some simple conditions
     */

    /**
     * test some null conditions
     */

    /**
     * test quoting in strings
     */

    /**
     * test some blank strings
     */

    /**
     * test some blank strings
     */

    /**
     * test variables with underscore names
     */

    /**
     * test the use of dot notation to lookup map entries
     */

    /**
     * Tests string literals
     */

    /**
     * test the use of an int based property
     */

    /**
     * test the -1 comparison bug
     */

    /**
     * Attempts to recreate bug http://jira.werken.com/ViewIssue.jspa?key=JELLY-8
     */

    /**
     * Test that 'and' only evaluates the second item if needed
     * @throws Exception if there are errors
     */
    @Test
    public void testBooleanShortCircuitAnd() throws Exception {
        // handle false for the left arg of 'and'
        Foo tester = new Foo();
        final JexlContext jc = new MapContext();
        jc.set("first", Boolean.FALSE);
        jc.set("foo", tester);
        final JexlExpression expr = JEXL.createExpression("first and foo.trueAndModify");
        expr.evaluate(jc);
        Assert.assertFalse("Short circuit failure: rhs evaluated when lhs FALSE", tester.getModified());
        // handle true for the left arg of 'and'
        tester = new Foo();
        jc.set("first", Boolean.TRUE);
        jc.set("foo", tester);
        expr.evaluate(jc);
        Assert.assertTrue("Short circuit failure: rhs not evaluated when lhs TRUE", tester.getModified());
    }

    /**
     * Test that 'or' only evaluates the second item if needed
     * @throws Exception if there are errors
     */
    @Test
    public void testBooleanShortCircuitOr() throws Exception {
        // handle false for the left arg of 'or'
        Foo tester = new Foo();
        final JexlContext jc = new MapContext();
        jc.set("first", Boolean.FALSE);
        jc.set("foo", tester);
        final JexlExpression expr = JEXL.createExpression("first or foo.trueAndModify");
        expr.evaluate(jc);
        Assert.assertTrue("Short circuit failure: rhs not evaluated when lhs FALSE", tester.getModified());
        // handle true for the left arg of 'or'
        tester = new Foo();
        jc.set("first", Boolean.TRUE);
        jc.set("foo", tester);
        expr.evaluate(jc);
        Assert.assertFalse("Short circuit failure: rhs evaluated when lhs TRUE", tester.getModified());
    }

    /**
     * Simple test of '+' as a string concatenation operator
     * @throws Exception
     */

    @Test
    public void testToString() throws Exception {
        final String code = "abcd";
        final JexlExpression expr = JEXL.createExpression(code);
        Assert.assertEquals("Bad expression value", code, expr.toString());
    }

    /**
     * Make sure bad syntax throws ParseException
     * @throws Exception on errors
     */
    @Test
    public void testBadParse() throws Exception {
        try {
            assertExpression(new MapContext(), "empty()", null);
            Assert.fail("Bad expression didn't throw ParseException");
        } catch (final JexlException pe) {
            // expected behavior
        }
    }

    /**
     * Test the ## comment in a string
     * @throws Exception
     */

    /**
     * Test assignment.
     * @throws Exception
     */

    public static final class Duck {
        int user = 10;

        @SuppressWarnings("boxing")
        public Integer get(final String val) {
            if ("zero".equals(val)) {
                return 0;
            }
            if ("one".equals(val)) {
                return 1;
            }
            if ("user".equals(val)) {
                return user;
            }
            return -1;
        }

        @SuppressWarnings("boxing")
        public void set(final String val, final Object value) {
            if ("user".equals(val)) {
                if ("zero".equals(value)) {
                    user = 0;
                } else if ("one".equals(value)) {
                    user = 1;
                } else {
                    user = value instanceof Integer ? (Integer) value : -1;
                }
            }
        }
    }

    @SuppressWarnings("boxing")
    @Test
    public void testDuck() throws Exception {
        final JexlEngine jexl = JEXL;
        final JexlContext jc = new MapContext();
        jc.set("duck", new Duck());
        JexlExpression expr;
        Object result;
        expr = jexl.createExpression("duck.zero");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), 0, result);
        expr = jexl.createExpression("duck.one");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), 1, result);
        expr = jexl.createExpression("duck.user = 20");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), 20, result);
        expr = jexl.createExpression("duck.user");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), 20, result);
        expr = jexl.createExpression("duck.user = 'zero'");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), "zero", result);
        expr = jexl.createExpression("duck.user");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), 0, result);
    }

    @SuppressWarnings("boxing")
    @Test
    public void testArray() throws Exception {
        final int[] array = {100, 101, 102};
        final JexlEngine jexl = JEXL;
        final JexlContext jc = new MapContext();
        jc.set("array", array);
        JexlExpression expr;
        Object result;
        expr = jexl.createExpression("array.1");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), 101, result);
        expr = jexl.createExpression("array[1] = 1010");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), 1010, result);
        expr = jexl.createExpression("array.0");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), 100, result);
    }

    /**
     * Asserts that the given expression returns the given value when applied to the
     * given context
     */
    protected void assertExpression(final JexlContext jc, final String expression, final Object expected) throws Exception {
        final JexlExpression e = JEXL.createExpression(expression);
        final Object actual = e.evaluate(jc);
        Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testBoolean_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

                final JexlContext jc1 = jc;
        final String expression = "foo.convertBoolean(a==b)";
        final Object expected = "Boolean : false";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testBoolean_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo.convertBoolean(a==true)";
        final Object expected = "Boolean : true";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testBoolean_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo.convertBoolean(a==false)";
        final Object expected = "Boolean : false";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testBoolean_4_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo.convertBoolean(true==false)";
        final Object expected = "Boolean : false";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testBoolean_5_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "true eq false";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testBoolean_6_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "true ne false";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testStringLit_1_oe_1_oe() throws Exception {
        /*
         *  tests a simple property expression
         */
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
                final JexlContext jc1 = jc;
        final String expression = "foo.repeat(\"woogie\")";
        final Object expected = "Repeat : woogie";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

                final JexlContext jc1 = jc;
        final String expression = "a == b";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "a==true";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "a==false";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_4_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "true==false";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_5_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final JexlContext jc1 = jc;
        final String expression = "2 < 3";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_6_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num < 5";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_7_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num < num";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_8_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num < null";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_9_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num < 2.5";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_10_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "now2 < now";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual); // test comparable;
    }

    @Test
    public void testExpression_11_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
                final JexlContext jc1 = jc;
        final String expression = "'6' <= '5'";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_12_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num <= 5";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_13_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num <= num";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_14_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num <= null";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_15_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num <= 2.5";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_16_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "now2 <= now";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual); // test comparable;
    }

    @Test
    public void testExpression_17_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
                final JexlContext jc1 = jc;
        final String expression = "'6' >= '5'";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_18_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num >= 5";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_19_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num >= num";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_20_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num >= null";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_21_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num >= 2.5";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_22_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "now2 >= now";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual); // test comparable;
    }

    @Test
    public void testExpression_23_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final JexlContext jc1 = jc;
        final String expression = "'6' > '5'";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_24_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num > 4";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_25_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num > num";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_26_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num > null";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_27_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
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
                final JexlContext jc1 = jc;
        final String expression = "num > 2.5";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_28_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
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
                final JexlContext jc1 = jc;
        final String expression = "now2 > now";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual); // test comparable;
    }

    @Test
    public void testExpression_29_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
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
        // removed other assertion

                final JexlContext jc1 = jc;
        final String expression = "\"foo\" + \"bar\" == \"foobar\"";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_30_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
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
        // removed other assertion

        // removed other assertion

                final JexlContext jc1 = jc;
        final String expression = "bdec > num";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_31_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
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
        // removed other assertion

        // removed other assertion

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "bdec >= num";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_32_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
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
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num <= bdec";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_33_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
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
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num < bdec";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_34_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
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
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "bint > num";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_35_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
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
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "bint == bdec";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_36_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
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
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "bint >= num";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_37_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
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
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "num <= bint";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testExpression_38_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);
        jc.set("num", new Integer(5));
        jc.set("now", Calendar.getInstance().getTime());
        final GregorianCalendar gc = new GregorianCalendar(5000, 11, 20);
        jc.set("now2", gc.getTime());
        jc.set("bdec", new BigDecimal("7"));
        jc.set("bint", new BigInteger("7"));

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
//
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

//
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
                final JexlContext jc1 = jc;
        final String expression = "num < bint";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testEmpty_1_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("string", "");
        jc.set("array", new Object[0]);
        jc.set("map", new HashMap<Object, Object>());
        jc.set("list", new ArrayList<Object>());
        jc.set("set", (new HashMap<Object, Object>()).keySet());
        jc.set("longstring", "thingthing");

        /*
         *  I can't believe anyone thinks this is a syntax.. :)
         */
                final JexlContext jc1 = jc;
        final String expression = "empty nullthing";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testEmpty_2_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("string", "");
        jc.set("array", new Object[0]);
        jc.set("map", new HashMap<Object, Object>());
        jc.set("list", new ArrayList<Object>());
        jc.set("set", (new HashMap<Object, Object>()).keySet());
        jc.set("longstring", "thingthing");

        /*
         *  I can't believe anyone thinks this is a syntax.. :)
         */
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "empty string";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testEmpty_3_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("string", "");
        jc.set("array", new Object[0]);
        jc.set("map", new HashMap<Object, Object>());
        jc.set("list", new ArrayList<Object>());
        jc.set("set", (new HashMap<Object, Object>()).keySet());
        jc.set("longstring", "thingthing");

        /*
         *  I can't believe anyone thinks this is a syntax.. :)
         */
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "empty array";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testEmpty_4_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("string", "");
        jc.set("array", new Object[0]);
        jc.set("map", new HashMap<Object, Object>());
        jc.set("list", new ArrayList<Object>());
        jc.set("set", (new HashMap<Object, Object>()).keySet());
        jc.set("longstring", "thingthing");

        /*
         *  I can't believe anyone thinks this is a syntax.. :)
         */
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "empty map";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testEmpty_5_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("string", "");
        jc.set("array", new Object[0]);
        jc.set("map", new HashMap<Object, Object>());
        jc.set("list", new ArrayList<Object>());
        jc.set("set", (new HashMap<Object, Object>()).keySet());
        jc.set("longstring", "thingthing");

        /*
         *  I can't believe anyone thinks this is a syntax.. :)
         */
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "empty set";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testEmpty_6_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("string", "");
        jc.set("array", new Object[0]);
        jc.set("map", new HashMap<Object, Object>());
        jc.set("list", new ArrayList<Object>());
        jc.set("set", (new HashMap<Object, Object>()).keySet());
        jc.set("longstring", "thingthing");

        /*
         *  I can't believe anyone thinks this is a syntax.. :)
         */
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "empty list";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testEmpty_7_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("string", "");
        jc.set("array", new Object[0]);
        jc.set("map", new HashMap<Object, Object>());
        jc.set("list", new ArrayList<Object>());
        jc.set("set", (new HashMap<Object, Object>()).keySet());
        jc.set("longstring", "thingthing");

        /*
         *  I can't believe anyone thinks this is a syntax.. :)
         */
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "empty longstring";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testEmpty_8_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("string", "");
        jc.set("array", new Object[0]);
        jc.set("map", new HashMap<Object, Object>());
        jc.set("list", new ArrayList<Object>());
        jc.set("set", (new HashMap<Object, Object>()).keySet());
        jc.set("longstring", "thingthing");

        /*
         *  I can't believe anyone thinks this is a syntax.. :)
         */
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "not empty longstring";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSize_1_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("s", "five!");
        jc.set("array", new Object[5]);

        final Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("1", new Integer(1));
        map.put("2", new Integer(2));
        map.put("3", new Integer(3));
        map.put("4", new Integer(4));
        map.put("5", new Integer(5));

        jc.set("map", map);

        final List<String> list = new ArrayList<String>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        jc.set("list", list);

        // 30652 - support for set
        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        // support generic int size() method
        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

                final JexlContext jc1 = jc;
        final String expression = "size(s)";
        final Object expected = new Integer(5);
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSize_2_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("s", "five!");
        jc.set("array", new Object[5]);

        final Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("1", new Integer(1));
        map.put("2", new Integer(2));
        map.put("3", new Integer(3));
        map.put("4", new Integer(4));
        map.put("5", new Integer(5));

        jc.set("map", map);

        final List<String> list = new ArrayList<String>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        jc.set("list", list);

        // 30652 - support for set
        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        // support generic int size() method
        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "size(array)";
        final Object expected = new Integer(5);
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSize_3_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("s", "five!");
        jc.set("array", new Object[5]);

        final Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("1", new Integer(1));
        map.put("2", new Integer(2));
        map.put("3", new Integer(3));
        map.put("4", new Integer(4));
        map.put("5", new Integer(5));

        jc.set("map", map);

        final List<String> list = new ArrayList<String>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        jc.set("list", list);

        // 30652 - support for set
        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        // support generic int size() method
        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "size(list)";
        final Object expected = new Integer(5);
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSize_4_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("s", "five!");
        jc.set("array", new Object[5]);

        final Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("1", new Integer(1));
        map.put("2", new Integer(2));
        map.put("3", new Integer(3));
        map.put("4", new Integer(4));
        map.put("5", new Integer(5));

        jc.set("map", map);

        final List<String> list = new ArrayList<String>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        jc.set("list", list);

        // 30652 - support for set
        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        // support generic int size() method
        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "size(map)";
        final Object expected = new Integer(5);
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSize_5_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("s", "five!");
        jc.set("array", new Object[5]);

        final Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("1", new Integer(1));
        map.put("2", new Integer(2));
        map.put("3", new Integer(3));
        map.put("4", new Integer(4));
        map.put("5", new Integer(5));

        jc.set("map", map);

        final List<String> list = new ArrayList<String>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        jc.set("list", list);

        // 30652 - support for set
        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        // support generic int size() method
        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "size(set)";
        final Object expected = new Integer(5);
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSize_6_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("s", "five!");
        jc.set("array", new Object[5]);

        final Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("1", new Integer(1));
        map.put("2", new Integer(2));
        map.put("3", new Integer(3));
        map.put("4", new Integer(4));
        map.put("5", new Integer(5));

        jc.set("map", map);

        final List<String> list = new ArrayList<String>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        jc.set("list", list);

        // 30652 - support for set
        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        // support generic int size() method
        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "size(bitset)";
        final Object expected = new Integer(64);
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSize_7_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("s", "five!");
        jc.set("array", new Object[5]);

        final Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("1", new Integer(1));
        map.put("2", new Integer(2));
        map.put("3", new Integer(3));
        map.put("4", new Integer(4));
        map.put("5", new Integer(5));

        jc.set("map", map);

        final List<String> list = new ArrayList<String>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        jc.set("list", list);

        // 30652 - support for set
        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        // support generic int size() method
        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "list.size()";
        final Object expected = new Integer(5);
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSize_8_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("s", "five!");
        jc.set("array", new Object[5]);

        final Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("1", new Integer(1));
        map.put("2", new Integer(2));
        map.put("3", new Integer(3));
        map.put("4", new Integer(4));
        map.put("5", new Integer(5));

        jc.set("map", map);

        final List<String> list = new ArrayList<String>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        jc.set("list", list);

        // 30652 - support for set
        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        // support generic int size() method
        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "map.size()";
        final Object expected = new Integer(5);
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSize_9_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("s", "five!");
        jc.set("array", new Object[5]);

        final Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("1", new Integer(1));
        map.put("2", new Integer(2));
        map.put("3", new Integer(3));
        map.put("4", new Integer(4));
        map.put("5", new Integer(5));

        jc.set("map", map);

        final List<String> list = new ArrayList<String>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        jc.set("list", list);

        // 30652 - support for set
        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        // support generic int size() method
        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "set.size()";
        final Object expected = new Integer(5);
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSize_10_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("s", "five!");
        jc.set("array", new Object[5]);

        final Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("1", new Integer(1));
        map.put("2", new Integer(2));
        map.put("3", new Integer(3));
        map.put("4", new Integer(4));
        map.put("5", new Integer(5));

        jc.set("map", map);

        final List<String> list = new ArrayList<String>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        jc.set("list", list);

        // 30652 - support for set
        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        // support generic int size() method
        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "bitset.size()";
        final Object expected = new Integer(64);
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSize_11_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("s", "five!");
        jc.set("array", new Object[5]);

        final Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("1", new Integer(1));
        map.put("2", new Integer(2));
        map.put("3", new Integer(3));
        map.put("4", new Integer(4));
        map.put("5", new Integer(5));

        jc.set("map", map);

        final List<String> list = new ArrayList<String>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        jc.set("list", list);

        // 30652 - support for set
        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        // support generic int size() method
        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

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

                final JexlContext jc1 = jc;
        final String expression = "list.get(size(list) - 1)";
        final Object expected = "5";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSize_12_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("s", "five!");
        jc.set("array", new Object[5]);

        final Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("1", new Integer(1));
        map.put("2", new Integer(2));
        map.put("3", new Integer(3));
        map.put("4", new Integer(4));
        map.put("5", new Integer(5));

        jc.set("map", map);

        final List<String> list = new ArrayList<String>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        jc.set("list", list);

        // 30652 - support for set
        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        // support generic int size() method
        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

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
                final JexlContext jc1 = jc;
        final String expression = "list[size(list) - 1]";
        final Object expected = "5";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSize_13_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("s", "five!");
        jc.set("array", new Object[5]);

        final Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("1", new Integer(1));
        map.put("2", new Integer(2));
        map.put("3", new Integer(3));
        map.put("4", new Integer(4));
        map.put("5", new Integer(5));

        jc.set("map", map);

        final List<String> list = new ArrayList<String>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        jc.set("list", list);

        // 30652 - support for set
        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        // support generic int size() method
        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

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
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "list.get(list.size() - 1)";
        final Object expected = "5";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSizeAsProperty_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

                final JexlContext jc1 = jc;
        final String expression = "map['size']";
        final Object expected = "cheese";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSizeAsProperty_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "map['si & ze']";
        final Object expected = "cheese";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSizeAsProperty_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "map.'si & ze'";
        final Object expected = "cheese";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSizeAsProperty_4_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "map.size()";
        final Object expected = 2;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSizeAsProperty_5_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "size(map)";
        final Object expected = 2;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSizeAsProperty_6_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo.getSize()";
        final Object expected = 22;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testSizeAsProperty_7_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo.'size'";
        final Object expected = 22;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCalculations_1_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        options.setStrictArithmetic(false);

        /*
         * test to ensure new string cat works
         */
        jc.set("stringy", "thingy");
                final JexlContext jc1 = jc;
        final String expression = "stringy + 2";
        final Object expected = "thingy2";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCalculations_2_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        options.setStrictArithmetic(false);

        /*
         * test to ensure new string cat works
         */
        jc.set("stringy", "thingy");
        // removed other assertion

        /*
         * test new null coersion
         */
        jc.set("imanull", null);
                final JexlContext jc1 = jc;
        final String expression = "imanull + 2";
        final Object expected = new Integer(2);
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCalculations_3_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        options.setStrictArithmetic(false);

        /*
         * test to ensure new string cat works
         */
        jc.set("stringy", "thingy");
        // removed other assertion

        /*
         * test new null coersion
         */
        jc.set("imanull", null);
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "imanull + imanull";
        final Object expected = new Integer(0);
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCalculations_4_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        options.setStrictArithmetic(false);

        /*
         * test to ensure new string cat works
         */
        jc.set("stringy", "thingy");
        // removed other assertion

        /*
         * test new null coersion
         */
        jc.set("imanull", null);
        // removed other assertion
        // removed other assertion

        /* test for bugzilla 31577 */
        jc.set("n", new Integer(0));
                final JexlContext jc1 = jc;
        final String expression = "n != null && n != 0";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_1_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

                final JexlContext jc1 = jc;
        final String expression = "foo == 2";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_2_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "2 == 3";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_3_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "3 == foo";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_4_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "3 != foo";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_5_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo != 2";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_6_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test float and double equality
                final JexlContext jc1 = jc;
        final String expression = "aFloat eq aDouble";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_7_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test float and double equality
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "aFloat ne aDouble";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_8_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test float and double equality
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "aFloat == aDouble";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_9_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test float and double equality
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "aFloat != aDouble";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_10_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test float and double equality
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test number and character equality
                final JexlContext jc1 = jc;
        final String expression = "foo == aChar";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_11_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test float and double equality
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test number and character equality
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo != aChar";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_12_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test float and double equality
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test number and character equality
        // removed other assertion
        // removed other assertion
        // test string and boolean
                final JexlContext jc1 = jc;
        final String expression = "aBool == 'true'";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_13_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test float and double equality
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test number and character equality
        // removed other assertion
        // removed other assertion
        // test string and boolean
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "aBool == 'false'";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_14_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test float and double equality
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test number and character equality
        // removed other assertion
        // removed other assertion
        // test string and boolean
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "aBool != 'false'";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_15_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test float and double equality
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test number and character equality
        // removed other assertion
        // removed other assertion
        // test string and boolean
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test null and boolean
        options.setStrict(false);
                final JexlContext jc1 = jc;
        final String expression = "aBool == notThere";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_16_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test float and double equality
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test number and character equality
        // removed other assertion
        // removed other assertion
        // test string and boolean
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test null and boolean
        options.setStrict(false);
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "aBool != notThere";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_17_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test float and double equality
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test number and character equality
        // removed other assertion
        // removed other assertion
        // test string and boolean
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test null and boolean
        options.setStrict(false);
        // removed other assertion
        // removed other assertion
        // anything and string as a string comparison
        options.setStrict(true);
                final JexlContext jc1 = jc;
        final String expression = "aBuffer == 'abc'";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_18_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test float and double equality
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test number and character equality
        // removed other assertion
        // removed other assertion
        // test string and boolean
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test null and boolean
        options.setStrict(false);
        // removed other assertion
        // removed other assertion
        // anything and string as a string comparison
        options.setStrict(true);
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "aBuffer != 'abc'";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_19_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test float and double equality
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test number and character equality
        // removed other assertion
        // removed other assertion
        // test string and boolean
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test null and boolean
        options.setStrict(false);
        // removed other assertion
        // removed other assertion
        // anything and string as a string comparison
        options.setStrict(true);
        // removed other assertion
        // removed other assertion
        // arbitrary equals
                final JexlContext jc1 = jc;
        final String expression = "aList == bList";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testConditions_20_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        jc.set("foo", new Integer(2));
        jc.set("aFloat", new Float(1));
        jc.set("aDouble", new Double(2));
        jc.set("aChar", new Character('A'));
        jc.set("aBool", Boolean.TRUE);
        final StringBuilder buffer = new StringBuilder("abc");
        final List<Object> list = new ArrayList<Object>();
        final List<Object> list2 = new LinkedList<Object>();
        jc.set("aBuffer", buffer);
        jc.set("aList", list);
        jc.set("bList", list2);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test float and double equality
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test number and character equality
        // removed other assertion
        // removed other assertion
        // test string and boolean
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test null and boolean
        options.setStrict(false);
        // removed other assertion
        // removed other assertion
        // anything and string as a string comparison
        options.setStrict(true);
        // removed other assertion
        // removed other assertion
        // arbitrary equals
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "aList != bList";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditions_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

                final JexlContext jc1 = jc;
        final String expression = "!x";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditions_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "x";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditions_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "!bar";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditions_4_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "!foo.isSimple()";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditions_5_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo.isSimple()";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditions_6_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "!foo.simple";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditions_7_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo.simple";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditions_8_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo.getCheeseList().size() == 3";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditions_9_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo.cheeseList.size() == 3";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditions_10_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        jc.set("string", "");
                final JexlContext jc1 = jc;
        final String expression = "not empty string";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditions_11_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        jc.set("string", "");
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "not(empty string)";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditions_12_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        jc.set("string", "");
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "not empty(string)";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditions_13_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        jc.set("string", "");
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "! empty string";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditions_14_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        jc.set("string", "");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "!(empty string)";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditions_15_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        jc.set("string", "");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "! empty(string)";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditionsWithDots_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        jc.set("x.a", Boolean.TRUE);
        jc.set("x.b", Boolean.FALSE);

                final JexlContext jc1 = jc;
        final String expression = "x.a";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditionsWithDots_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        jc.set("x.a", Boolean.TRUE);
        jc.set("x.b", Boolean.FALSE);

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "!x.a";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNotConditionsWithDots_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        jc.set("x.a", Boolean.TRUE);
        jc.set("x.b", Boolean.FALSE);

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "!x.b";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testComparisons_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "the quick and lazy fox");

                final JexlContext jc1 = jc;
        final String expression = "foo.indexOf('quick') > 0";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testComparisons_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "the quick and lazy fox");

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo.indexOf('bar') >= 0";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testComparisons_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "the quick and lazy fox");

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo.indexOf('bar') < 0";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNull_1_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

                final JexlContext jc1 = jc;
        final String expression = "empty foo";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNull_2_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "bar == null";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNull_3_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo == null";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNull_4_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "bar != null";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNull_5_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo != null";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNull_6_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "empty(bar)";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNull_7_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "empty(foo)";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testStringQuoting_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
                final JexlContext jc1 = jc;
        final String expression = "'\"Hello\"'";
        final Object expected = "\"Hello\"";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testStringQuoting_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "\"I'm testing\"";
        final Object expected = "I'm testing";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testBlankStrings_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("bar", "");

                final JexlContext jc1 = jc;
        final String expression = "bar == ''";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testBlankStrings_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("bar", "");

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "empty bar";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testBlankStrings_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("bar", "");

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "bar.length() == 0";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testBlankStrings_4_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("bar", "");

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "size(bar) == 0";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testLogicExpressions_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

                final JexlContext jc1 = jc;
        final String expression = "foo == 'abc' || bar == 'abc'";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testLogicExpressions_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo == 'abc' or bar == 'abc'";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testLogicExpressions_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo == 'abc' && bar == 'abc'";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testLogicExpressions_4_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo == 'abc' and bar == 'abc'";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testLogicExpressions_5_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final JexlContext jc1 = jc;
        final String expression = "foo == 'def' || bar == 'abc'";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testLogicExpressions_6_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo == 'def' or bar == 'abc'";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testLogicExpressions_7_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo == 'abc' && bar == 'def'";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testLogicExpressions_8_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo == 'abc' and bar == 'def'";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testVariableNames_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo_bar", "123");

                final JexlContext jc1 = jc;
        final String expression = "foo_bar";
        final Object expected = "123";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testMapDot_1_oe_1_oe() throws Exception {
        final Map<String, String> foo = new HashMap<String, String>();
        foo.put("bar", "123");

        final JexlContext jc = new MapContext();
        jc.set("foo", foo);

                final JexlContext jc1 = jc;
        final String expression = "foo.bar";
        final Object expected = "123";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testStringLiterals_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "bar");

                final JexlContext jc1 = jc;
        final String expression = "foo == \"bar\"";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testStringLiterals_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "bar");

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo == 'bar'";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testIntProperty_3_oe_1_oe() throws Exception {
        final Foo foo = new Foo();

        // lets check the square function first..
        // removed other assertion
        // removed other assertion

        final JexlContext jc = new MapContext();
        jc.set("foo", foo);

                final JexlContext jc1 = jc;
        final String expression = "foo.count";
        final Object expected = new Integer(5);
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testIntProperty_4_oe_1_oe() throws Exception {
        final Foo foo = new Foo();

        // lets check the square function first..
        // removed other assertion
        // removed other assertion

        final JexlContext jc = new MapContext();
        jc.set("foo", foo);

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo.square(2)";
        final Object expected = new Integer(4);
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testIntProperty_5_oe_1_oe() throws Exception {
        final Foo foo = new Foo();

        // lets check the square function first..
        // removed other assertion
        // removed other assertion

        final JexlContext jc = new MapContext();
        jc.set("foo", foo);

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo.square(-2)";
        final Object expected = new Integer(4);
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNegativeIntComparison_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Foo foo = new Foo();
        jc.set("foo", foo);

                final JexlContext jc1 = jc;
        final String expression = "foo.count != -1";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNegativeIntComparison_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Foo foo = new Foo();
        jc.set("foo", foo);

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo.count == 5";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testNegativeIntComparison_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Foo foo = new Foo();
        jc.set("foo", foo);

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo.count == -1";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCharAtBug_1_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setSilent(true);

        jc.set("foo", "abcdef");

                final JexlContext jc1 = jc;
        final String expression = "foo.substring(2,4)";
        final Object expected = "cd";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCharAtBug_2_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setSilent(true);

        jc.set("foo", "abcdef");

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo.charAt(2)";
        final Object expected = new Character('c');
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCharAtBug_3_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setSilent(true);

        jc.set("foo", "abcdef");

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "foo.charAt(-2)";
        final Object expected = null;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testEmptyDottedVariableName_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        jc.set("this.is.a.test", "");

                final JexlContext jc1 = jc;
        final String expression = "empty(this.is.a.test)";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testEmptySubListOfMap_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, ArrayList<?>> m = new HashMap<String, ArrayList<?>>();
        m.put("aList", new ArrayList<Object>());

        jc.set("aMap", m);

                final JexlContext jc1 = jc;
        final String expression = "empty( aMap.aList )";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCoercionWithComparisionOperators_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

                final JexlContext jc1 = jc;
        final String expression = "'2' > 1";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCoercionWithComparisionOperators_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "'2' >= 1";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCoercionWithComparisionOperators_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "'2' >= 2";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCoercionWithComparisionOperators_4_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "'2' < 1";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCoercionWithComparisionOperators_5_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "'2' <= 1";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCoercionWithComparisionOperators_6_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "'2' <= 2";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCoercionWithComparisionOperators_7_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final JexlContext jc1 = jc;
        final String expression = "2 > '1'";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCoercionWithComparisionOperators_8_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "2 >= '1'";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCoercionWithComparisionOperators_9_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "2 >= '2'";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCoercionWithComparisionOperators_10_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "2 < '1'";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCoercionWithComparisionOperators_11_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

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
                final JexlContext jc1 = jc;
        final String expression = "2 <= '1'";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testCoercionWithComparisionOperators_12_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

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
                final JexlContext jc1 = jc;
        final String expression = "2 <= '2'";
        final Object expected = Boolean.TRUE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testStringConcatenation_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("first", "Hello");
        jc.set("second", "World");
                final JexlContext jc1 = jc;
        final String expression = "first + ' ' + second";
        final Object expected = "Hello World";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testComment_1_oe_1_oe() throws Exception {
                final JexlContext jc = new MapContext();
        final String expression = "## double or nothing\n 1 + 1";
        final Object expected = Integer.valueOf("2");
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testAssignment_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("aString", "Hello");
        final Foo foo = new Foo();
        jc.set("foo", foo);
        final Parser parser = new Parser(";");
        parser.parse(null, new JexlFeatures().register(false), "aString = 'World';", null);

                final JexlContext jc1 = jc;
        final String expression = "hello = 'world'";
        final Object expected = "world";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testAssignment_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("aString", "Hello");
        final Foo foo = new Foo();
        jc.set("foo", foo);
        final Parser parser = new Parser(";");
        parser.parse(null, new JexlFeatures().register(false), "aString = 'World';", null);

        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "result = 1 + 1";
        final Object expected = new Integer(2);
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testAntPropertiesWithMethods_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final String value = "Stinky Cheese";
        jc.set("maven.bob.food", value);
                final JexlContext jc1 = jc;
        final String expression = "maven.bob.food.length()";
        final Object expected = new Integer(value.length());
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testAntPropertiesWithMethods_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final String value = "Stinky Cheese";
        jc.set("maven.bob.food", value);
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "empty(maven.bob.food)";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testAntPropertiesWithMethods_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final String value = "Stinky Cheese";
        jc.set("maven.bob.food", value);
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "size(maven.bob.food)";
        final Object expected = new Integer(value.length());
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testAntPropertiesWithMethods_4_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final String value = "Stinky Cheese";
        jc.set("maven.bob.food", value);
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "maven.bob.food + ' is good'";
        final Object expected = value + " is good";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testUnicodeSupport_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
                final JexlContext jc1 = jc;
        final String expression = "'x' == '\\u0032?ytkownik'";
        final Object expected = Boolean.FALSE;
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testUnicodeSupport_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "'c:\\some\\windows\\path'";
        final Object expected = "c:\\some\\windows\\path";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testUnicodeSupport_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "'foo\\u0020bar'";
        final Object expected = "foo\u0020bar";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testUnicodeSupport_4_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "'foo\\u0020\\u0020bar'";
        final Object expected = "foo\u0020\u0020bar";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

    @Test
    public void testUnicodeSupport_5_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final JexlContext jc1 = jc;
        final String expression = "'\\u0020foobar\\u0020'";
        final Object expected = "\u0020foobar\u0020";
        final JexlExpression e = JEXL.createExpression(expression);
                final Object actual = e.evaluate(jc1);
                Assert.assertEquals(expression, expected, actual);
    }

}
