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

    /**
     * test the new function e.g constructor invocation
     */

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

    /**
     * Test that 'or' only evaluates the second item if needed
     * @throws Exception if there are errors
     */

    /**
     * Simple test of '+' as a string concatenation operator
     * @throws Exception
     */

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
    public void testProperty_1_oe() throws Exception {
        /*
         *  tests a simple property expression
         */

        final JexlExpression e = JEXL.createExpression("foo.bar");
        final JexlContext jc = new MapContext();

        jc.set("foo", new Foo());
        final Object o = e.evaluate(jc);

        Assert.assertTrue("o not instanceof String", o instanceof String);
    }

    @Test
    public void testProperty_2_oe() throws Exception {
        /*
         *  tests a simple property expression
         */

        final JexlExpression e = JEXL.createExpression("foo.bar");
        final JexlContext jc = new MapContext();

        jc.set("foo", new Foo());
        final Object o = e.evaluate(jc);

        Assert.assertEquals("o incorrect", GET_METHOD_STRING, o);
    }

    @Test
    public void testNew_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("double", Double.class);
        jc.set("foo", "org.apache.commons.jexl3.Foo");
        JexlExpression expr;
        Object value;
        expr = JEXL.createExpression("new(double, 1)");
        value = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), new Double(1.0), value);
    }

    @Test
    public void testNew_2_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("double", Double.class);
        jc.set("foo", "org.apache.commons.jexl3.Foo");
        JexlExpression expr;
        Object value;
        expr = JEXL.createExpression("new(double, 1)");
        value = expr.evaluate(jc);
        expr = JEXL.createExpression("new('java.lang.Float', 100)");
        value = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), new Float(100.0), value);
    }

    @Test
    public void testNew_3_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("double", Double.class);
        jc.set("foo", "org.apache.commons.jexl3.Foo");
        JexlExpression expr;
        Object value;
        expr = JEXL.createExpression("new(double, 1)");
        value = expr.evaluate(jc);
        expr = JEXL.createExpression("new('java.lang.Float', 100)");
        value = expr.evaluate(jc);
        expr = JEXL.createExpression("new(foo).quux");
        value = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), "String : quux", value);
    }

    @Test
    public void testIntProperty_1_oe() throws Exception {
        final Foo foo = new Foo();

        Assert.assertEquals(4, foo.square(2));
    }

    @Test
    public void testIntProperty_2_oe() throws Exception {
        final Foo foo = new Foo();

        Assert.assertEquals(4, foo.square(-2));
    }

    @Test
    public void testBooleanShortCircuitAnd_1_oe() throws Exception {
        Foo tester = new Foo();
        final JexlContext jc = new MapContext();
        jc.set("first", Boolean.FALSE);
        jc.set("foo", tester);
        final JexlExpression expr = JEXL.createExpression("first and foo.trueAndModify");
        expr.evaluate(jc);
        Assert.assertFalse("Short circuit failure: rhs evaluated when lhs FALSE", tester.getModified());
    }

    @Test
    public void testBooleanShortCircuitAnd_2_oe() throws Exception {
        Foo tester = new Foo();
        final JexlContext jc = new MapContext();
        jc.set("first", Boolean.FALSE);
        jc.set("foo", tester);
        final JexlExpression expr = JEXL.createExpression("first and foo.trueAndModify");
        expr.evaluate(jc);
        tester = new Foo();
        jc.set("first", Boolean.TRUE);
        jc.set("foo", tester);
        expr.evaluate(jc);
        Assert.assertTrue("Short circuit failure: rhs not evaluated when lhs TRUE", tester.getModified());
    }

    @Test
    public void testBooleanShortCircuitOr_1_oe() throws Exception {
        Foo tester = new Foo();
        final JexlContext jc = new MapContext();
        jc.set("first", Boolean.FALSE);
        jc.set("foo", tester);
        final JexlExpression expr = JEXL.createExpression("first or foo.trueAndModify");
        expr.evaluate(jc);
        Assert.assertTrue("Short circuit failure: rhs not evaluated when lhs FALSE", tester.getModified());
    }

    @Test
    public void testBooleanShortCircuitOr_2_oe() throws Exception {
        Foo tester = new Foo();
        final JexlContext jc = new MapContext();
        jc.set("first", Boolean.FALSE);
        jc.set("foo", tester);
        final JexlExpression expr = JEXL.createExpression("first or foo.trueAndModify");
        expr.evaluate(jc);
        tester = new Foo();
        jc.set("first", Boolean.TRUE);
        jc.set("foo", tester);
        expr.evaluate(jc);
        Assert.assertFalse("Short circuit failure: rhs evaluated when lhs TRUE", tester.getModified());
    }

    @Test
    public void testToString_1_oe() throws Exception {
        final String code = "abcd";
        final JexlExpression expr = JEXL.createExpression(code);
        Assert.assertEquals("Bad expression value", code, expr.toString());
    }

    @Test
    public void testDuck_1_oe() throws Exception {
        final JexlEngine jexl = JEXL;
        final JexlContext jc = new MapContext();
        jc.set("duck", new Duck());
        JexlExpression expr;
        Object result;
        expr = jexl.createExpression("duck.zero");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), 0, result);
    }

    @Test
    public void testDuck_2_oe() throws Exception {
        final JexlEngine jexl = JEXL;
        final JexlContext jc = new MapContext();
        jc.set("duck", new Duck());
        JexlExpression expr;
        Object result;
        expr = jexl.createExpression("duck.zero");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("duck.one");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), 1, result);
    }

    @Test
    public void testDuck_3_oe() throws Exception {
        final JexlEngine jexl = JEXL;
        final JexlContext jc = new MapContext();
        jc.set("duck", new Duck());
        JexlExpression expr;
        Object result;
        expr = jexl.createExpression("duck.zero");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("duck.one");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("duck.user = 20");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), 20, result);
    }

    @Test
    public void testDuck_4_oe() throws Exception {
        final JexlEngine jexl = JEXL;
        final JexlContext jc = new MapContext();
        jc.set("duck", new Duck());
        JexlExpression expr;
        Object result;
        expr = jexl.createExpression("duck.zero");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("duck.one");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("duck.user = 20");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("duck.user");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), 20, result);
    }

    @Test
    public void testDuck_5_oe() throws Exception {
        final JexlEngine jexl = JEXL;
        final JexlContext jc = new MapContext();
        jc.set("duck", new Duck());
        JexlExpression expr;
        Object result;
        expr = jexl.createExpression("duck.zero");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("duck.one");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("duck.user = 20");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("duck.user");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("duck.user = 'zero'");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), "zero", result);
    }

    @Test
    public void testDuck_6_oe() throws Exception {
        final JexlEngine jexl = JEXL;
        final JexlContext jc = new MapContext();
        jc.set("duck", new Duck());
        JexlExpression expr;
        Object result;
        expr = jexl.createExpression("duck.zero");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("duck.one");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("duck.user = 20");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("duck.user");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("duck.user = 'zero'");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("duck.user");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), 0, result);
    }

    @Test
    public void testArray_1_oe() throws Exception {
        final int[] array = {100, 101, 102};
        final JexlEngine jexl = JEXL;
        final JexlContext jc = new MapContext();
        jc.set("array", array);
        JexlExpression expr;
        Object result;
        expr = jexl.createExpression("array.1");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), 101, result);
    }

    @Test
    public void testArray_2_oe() throws Exception {
        final int[] array = {100, 101, 102};
        final JexlEngine jexl = JEXL;
        final JexlContext jc = new MapContext();
        jc.set("array", array);
        JexlExpression expr;
        Object result;
        expr = jexl.createExpression("array.1");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("array[1] = 1010");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), 1010, result);
    }

    @Test
    public void testArray_3_oe() throws Exception {
        final int[] array = {100, 101, 102};
        final JexlEngine jexl = JEXL;
        final JexlContext jc = new MapContext();
        jc.set("array", array);
        JexlExpression expr;
        Object result;
        expr = jexl.createExpression("array.1");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("array[1] = 1010");
        result = expr.evaluate(jc);
        expr = jexl.createExpression("array.0");
        result = expr.evaluate(jc);
        Assert.assertEquals(expr.toString(), 100, result);
    }

    @Test
    public void testBoolean_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

                final JexlContext jc0 = jc;
        final String expression0 = "foo.convertBoolean(a==b)";
        final Object expected0 = "Boolean : false";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testBoolean_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

                final JexlContext jc0 = jc;
        final String expression0 = "foo.convertBoolean(a==true)";
        final Object expected0 = "Boolean : true";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testBoolean_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

                final JexlContext jc0 = jc;
        final String expression0 = "foo.convertBoolean(a==false)";
        final Object expected0 = "Boolean : false";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testBoolean_4_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

                final JexlContext jc0 = jc;
        final String expression0 = "foo.convertBoolean(true==false)";
        final Object expected0 = "Boolean : false";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testBoolean_5_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

                final JexlContext jc0 = jc;
        final String expression0 = "true eq false";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testBoolean_6_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

                final JexlContext jc0 = jc;
        final String expression0 = "true ne false";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testStringLit_1_oe_1_oe() throws Exception {
        /*
         *  tests a simple property expression
         */
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
                final JexlContext jc0 = jc;
        final String expression0 = "foo.repeat(\"woogie\")";
        final Object expected0 = "Repeat : woogie";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "a == b";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "a==true";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "a==false";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "true==false";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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


                final JexlContext jc0 = jc;
        final String expression0 = "2 < 3";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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


                final JexlContext jc0 = jc;
        final String expression0 = "num < 5";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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


                final JexlContext jc0 = jc;
        final String expression0 = "num < num";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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


                final JexlContext jc0 = jc;
        final String expression0 = "num < null";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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


                final JexlContext jc0 = jc;
        final String expression0 = "num < 2.5";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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


                final JexlContext jc0 = jc;
        final String expression0 = "now2 < now";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0); // test comparable;
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


                final JexlContext jc0 = jc;
        final String expression0 = "'6' <= '5'";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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


                final JexlContext jc0 = jc;
        final String expression0 = "num <= 5";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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


                final JexlContext jc0 = jc;
        final String expression0 = "num <= num";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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


                final JexlContext jc0 = jc;
        final String expression0 = "num <= null";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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


                final JexlContext jc0 = jc;
        final String expression0 = "num <= 2.5";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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


                final JexlContext jc0 = jc;
        final String expression0 = "now2 <= now";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0); // test comparable;
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



                final JexlContext jc0 = jc;
        final String expression0 = "'6' >= '5'";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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



                final JexlContext jc0 = jc;
        final String expression0 = "num >= 5";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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



                final JexlContext jc0 = jc;
        final String expression0 = "num >= num";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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



                final JexlContext jc0 = jc;
        final String expression0 = "num >= null";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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



                final JexlContext jc0 = jc;
        final String expression0 = "num >= 2.5";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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



                final JexlContext jc0 = jc;
        final String expression0 = "now2 >= now";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0); // test comparable;
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




                final JexlContext jc0 = jc;
        final String expression0 = "'6' > '5'";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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




                final JexlContext jc0 = jc;
        final String expression0 = "num > 4";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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




                final JexlContext jc0 = jc;
        final String expression0 = "num > num";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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




                final JexlContext jc0 = jc;
        final String expression0 = "num > null";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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




                final JexlContext jc0 = jc;
        final String expression0 = "num > 2.5";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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




                final JexlContext jc0 = jc;
        final String expression0 = "now2 > now";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0); // test comparable;
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





                final JexlContext jc0 = jc;
        final String expression0 = "\"foo\" + \"bar\" == \"foobar\"";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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






                final JexlContext jc0 = jc;
        final String expression0 = "bdec > num";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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






                final JexlContext jc0 = jc;
        final String expression0 = "bdec >= num";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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






                final JexlContext jc0 = jc;
        final String expression0 = "num <= bdec";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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






                final JexlContext jc0 = jc;
        final String expression0 = "num < bdec";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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






                final JexlContext jc0 = jc;
        final String expression0 = "bint > num";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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






                final JexlContext jc0 = jc;
        final String expression0 = "bint == bdec";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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






                final JexlContext jc0 = jc;
        final String expression0 = "bint >= num";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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






                final JexlContext jc0 = jc;
        final String expression0 = "num <= bint";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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






                final JexlContext jc0 = jc;
        final String expression0 = "num < bint";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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
                final JexlContext jc0 = jc;
        final String expression0 = "empty nullthing";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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
                final JexlContext jc0 = jc;
        final String expression0 = "empty string";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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
                final JexlContext jc0 = jc;
        final String expression0 = "empty array";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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
                final JexlContext jc0 = jc;
        final String expression0 = "empty map";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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
                final JexlContext jc0 = jc;
        final String expression0 = "empty set";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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
                final JexlContext jc0 = jc;
        final String expression0 = "empty list";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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
                final JexlContext jc0 = jc;
        final String expression0 = "empty longstring";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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
                final JexlContext jc0 = jc;
        final String expression0 = "not empty longstring";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

                final JexlContext jc0 = jc;
        final String expression0 = "size(s)";
        final Object expected0 = new Integer(5);
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

                final JexlContext jc0 = jc;
        final String expression0 = "size(array)";
        final Object expected0 = new Integer(5);
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

                final JexlContext jc0 = jc;
        final String expression0 = "size(list)";
        final Object expected0 = new Integer(5);
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

                final JexlContext jc0 = jc;
        final String expression0 = "size(map)";
        final Object expected0 = new Integer(5);
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

                final JexlContext jc0 = jc;
        final String expression0 = "size(set)";
        final Object expected0 = new Integer(5);
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

                final JexlContext jc0 = jc;
        final String expression0 = "size(bitset)";
        final Object expected0 = new Integer(64);
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

                final JexlContext jc0 = jc;
        final String expression0 = "list.size()";
        final Object expected0 = new Integer(5);
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

                final JexlContext jc0 = jc;
        final String expression0 = "map.size()";
        final Object expected0 = new Integer(5);
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

                final JexlContext jc0 = jc;
        final String expression0 = "set.size()";
        final Object expected0 = new Integer(5);
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);

                final JexlContext jc0 = jc;
        final String expression0 = "bitset.size()";
        final Object expected0 = new Integer(64);
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);


                final JexlContext jc0 = jc;
        final String expression0 = "list.get(size(list) - 1)";
        final Object expected0 = "5";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);


                final JexlContext jc0 = jc;
        final String expression0 = "list[size(list) - 1]";
        final Object expected0 = "5";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        final Set<String> set = new HashSet<String>(list);
        set.add("1");

        jc.set("set", set);

        final BitSet bitset = new BitSet(5);
        jc.set("bitset", bitset);


                final JexlContext jc0 = jc;
        final String expression0 = "list.get(list.size() - 1)";
        final Object expected0 = "5";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testSizeAsProperty_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

                final JexlContext jc0 = jc;
        final String expression0 = "map['size']";
        final Object expected0 = "cheese";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testSizeAsProperty_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

                final JexlContext jc0 = jc;
        final String expression0 = "map['si & ze']";
        final Object expected0 = "cheese";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testSizeAsProperty_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

                final JexlContext jc0 = jc;
        final String expression0 = "map.'si & ze'";
        final Object expected0 = "cheese";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testSizeAsProperty_4_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

                final JexlContext jc0 = jc;
        final String expression0 = "map.size()";
        final Object expected0 = 2;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testSizeAsProperty_5_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

                final JexlContext jc0 = jc;
        final String expression0 = "size(map)";
        final Object expected0 = 2;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testSizeAsProperty_6_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

                final JexlContext jc0 = jc;
        final String expression0 = "foo.getSize()";
        final Object expected0 = 22;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testSizeAsProperty_7_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

                final JexlContext jc0 = jc;
        final String expression0 = "foo.'size'";
        final Object expected0 = 22;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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
                final JexlContext jc0 = jc;
        final String expression0 = "stringy + 2";
        final Object expected0 = "thingy2";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        /*
         * test new null coersion
         */
        jc.set("imanull", null);
                final JexlContext jc0 = jc;
        final String expression0 = "imanull + 2";
        final Object expected0 = new Integer(2);
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        /*
         * test new null coersion
         */
        jc.set("imanull", null);
                final JexlContext jc0 = jc;
        final String expression0 = "imanull + imanull";
        final Object expected0 = new Integer(0);
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        /*
         * test new null coersion
         */
        jc.set("imanull", null);

        /* test for bugzilla 31577 */
        jc.set("n", new Integer(0));
                final JexlContext jc0 = jc;
        final String expression0 = "n != null && n != 0";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "foo == 2";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "2 == 3";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "3 == foo";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "3 != foo";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "foo != 2";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "aFloat eq aDouble";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "aFloat ne aDouble";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "aFloat == aDouble";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "aFloat != aDouble";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "foo == aChar";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "foo != aChar";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "aBool == 'true'";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "aBool == 'false'";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

                final JexlContext jc0 = jc;
        final String expression0 = "aBool != 'false'";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        options.setStrict(false);
                final JexlContext jc0 = jc;
        final String expression0 = "aBool == notThere";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        options.setStrict(false);
                final JexlContext jc0 = jc;
        final String expression0 = "aBool != notThere";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        options.setStrict(false);
        options.setStrict(true);
                final JexlContext jc0 = jc;
        final String expression0 = "aBuffer == 'abc'";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        options.setStrict(false);
        options.setStrict(true);
                final JexlContext jc0 = jc;
        final String expression0 = "aBuffer != 'abc'";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        options.setStrict(false);
        options.setStrict(true);
                final JexlContext jc0 = jc;
        final String expression0 = "aList == bList";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
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

        options.setStrict(false);
        options.setStrict(true);
                final JexlContext jc0 = jc;
        final String expression0 = "aList != bList";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditions_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

                final JexlContext jc0 = jc;
        final String expression0 = "!x";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditions_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

                final JexlContext jc0 = jc;
        final String expression0 = "x";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditions_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

                final JexlContext jc0 = jc;
        final String expression0 = "!bar";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditions_4_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

                final JexlContext jc0 = jc;
        final String expression0 = "!foo.isSimple()";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditions_5_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

                final JexlContext jc0 = jc;
        final String expression0 = "foo.isSimple()";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditions_6_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

                final JexlContext jc0 = jc;
        final String expression0 = "!foo.simple";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditions_7_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

                final JexlContext jc0 = jc;
        final String expression0 = "foo.simple";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditions_8_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

                final JexlContext jc0 = jc;
        final String expression0 = "foo.getCheeseList().size() == 3";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditions_9_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

                final JexlContext jc0 = jc;
        final String expression0 = "foo.cheeseList.size() == 3";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditions_10_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");


        jc.set("string", "");
                final JexlContext jc0 = jc;
        final String expression0 = "not empty string";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditions_11_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");


        jc.set("string", "");
                final JexlContext jc0 = jc;
        final String expression0 = "not(empty string)";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditions_12_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");


        jc.set("string", "");
                final JexlContext jc0 = jc;
        final String expression0 = "not empty(string)";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditions_13_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");


        jc.set("string", "");
                final JexlContext jc0 = jc;
        final String expression0 = "! empty string";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditions_14_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");


        jc.set("string", "");
                final JexlContext jc0 = jc;
        final String expression0 = "!(empty string)";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditions_15_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");


        jc.set("string", "");
                final JexlContext jc0 = jc;
        final String expression0 = "! empty(string)";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditionsWithDots_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        jc.set("x.a", Boolean.TRUE);
        jc.set("x.b", Boolean.FALSE);

                final JexlContext jc0 = jc;
        final String expression0 = "x.a";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditionsWithDots_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        jc.set("x.a", Boolean.TRUE);
        jc.set("x.b", Boolean.FALSE);

                final JexlContext jc0 = jc;
        final String expression0 = "!x.a";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNotConditionsWithDots_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        jc.set("x.a", Boolean.TRUE);
        jc.set("x.b", Boolean.FALSE);

                final JexlContext jc0 = jc;
        final String expression0 = "!x.b";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testComparisons_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "the quick and lazy fox");

                final JexlContext jc0 = jc;
        final String expression0 = "foo.indexOf('quick') > 0";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testComparisons_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "the quick and lazy fox");

                final JexlContext jc0 = jc;
        final String expression0 = "foo.indexOf('bar') >= 0";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testComparisons_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "the quick and lazy fox");

                final JexlContext jc0 = jc;
        final String expression0 = "foo.indexOf('bar') < 0";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNull_1_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

                final JexlContext jc0 = jc;
        final String expression0 = "empty foo";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNull_2_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

                final JexlContext jc0 = jc;
        final String expression0 = "bar == null";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNull_3_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

                final JexlContext jc0 = jc;
        final String expression0 = "foo == null";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNull_4_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

                final JexlContext jc0 = jc;
        final String expression0 = "bar != null";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNull_5_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

                final JexlContext jc0 = jc;
        final String expression0 = "foo != null";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNull_6_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

                final JexlContext jc0 = jc;
        final String expression0 = "empty(bar)";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNull_7_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

                final JexlContext jc0 = jc;
        final String expression0 = "empty(foo)";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testStringQuoting_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
                final JexlContext jc0 = jc;
        final String expression0 = "'\"Hello\"'";
        final Object expected0 = "\"Hello\"";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testStringQuoting_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
                final JexlContext jc0 = jc;
        final String expression0 = "\"I'm testing\"";
        final Object expected0 = "I'm testing";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testBlankStrings_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("bar", "");

                final JexlContext jc0 = jc;
        final String expression0 = "bar == ''";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testBlankStrings_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("bar", "");

                final JexlContext jc0 = jc;
        final String expression0 = "empty bar";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testBlankStrings_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("bar", "");

                final JexlContext jc0 = jc;
        final String expression0 = "bar.length() == 0";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testBlankStrings_4_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("bar", "");

                final JexlContext jc0 = jc;
        final String expression0 = "size(bar) == 0";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testLogicExpressions_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

                final JexlContext jc0 = jc;
        final String expression0 = "foo == 'abc' || bar == 'abc'";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testLogicExpressions_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

                final JexlContext jc0 = jc;
        final String expression0 = "foo == 'abc' or bar == 'abc'";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testLogicExpressions_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

                final JexlContext jc0 = jc;
        final String expression0 = "foo == 'abc' && bar == 'abc'";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testLogicExpressions_4_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

                final JexlContext jc0 = jc;
        final String expression0 = "foo == 'abc' and bar == 'abc'";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testLogicExpressions_5_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");


                final JexlContext jc0 = jc;
        final String expression0 = "foo == 'def' || bar == 'abc'";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testLogicExpressions_6_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");


                final JexlContext jc0 = jc;
        final String expression0 = "foo == 'def' or bar == 'abc'";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testLogicExpressions_7_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");


                final JexlContext jc0 = jc;
        final String expression0 = "foo == 'abc' && bar == 'def'";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testLogicExpressions_8_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");


                final JexlContext jc0 = jc;
        final String expression0 = "foo == 'abc' and bar == 'def'";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testVariableNames_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo_bar", "123");

                final JexlContext jc0 = jc;
        final String expression0 = "foo_bar";
        final Object expected0 = "123";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testMapDot_1_oe_1_oe() throws Exception {
        final Map<String, String> foo = new HashMap<String, String>();
        foo.put("bar", "123");

        final JexlContext jc = new MapContext();
        jc.set("foo", foo);

                final JexlContext jc0 = jc;
        final String expression0 = "foo.bar";
        final Object expected0 = "123";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testStringLiterals_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "bar");

                final JexlContext jc0 = jc;
        final String expression0 = "foo == \"bar\"";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testStringLiterals_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "bar");

                final JexlContext jc0 = jc;
        final String expression0 = "foo == 'bar'";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testIntProperty_3_oe_1_oe() throws Exception {
        final Foo foo = new Foo();


        final JexlContext jc = new MapContext();
        jc.set("foo", foo);

                final JexlContext jc0 = jc;
        final String expression0 = "foo.count";
        final Object expected0 = new Integer(5);
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testIntProperty_4_oe_1_oe() throws Exception {
        final Foo foo = new Foo();


        final JexlContext jc = new MapContext();
        jc.set("foo", foo);

                final JexlContext jc0 = jc;
        final String expression0 = "foo.square(2)";
        final Object expected0 = new Integer(4);
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testIntProperty_5_oe_1_oe() throws Exception {
        final Foo foo = new Foo();


        final JexlContext jc = new MapContext();
        jc.set("foo", foo);

                final JexlContext jc0 = jc;
        final String expression0 = "foo.square(-2)";
        final Object expected0 = new Integer(4);
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNegativeIntComparison_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Foo foo = new Foo();
        jc.set("foo", foo);

                final JexlContext jc0 = jc;
        final String expression0 = "foo.count != -1";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNegativeIntComparison_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Foo foo = new Foo();
        jc.set("foo", foo);

                final JexlContext jc0 = jc;
        final String expression0 = "foo.count == 5";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testNegativeIntComparison_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Foo foo = new Foo();
        jc.set("foo", foo);

                final JexlContext jc0 = jc;
        final String expression0 = "foo.count == -1";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testCharAtBug_1_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setSilent(true);

        jc.set("foo", "abcdef");

                final JexlContext jc0 = jc;
        final String expression0 = "foo.substring(2,4)";
        final Object expected0 = "cd";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testCharAtBug_2_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setSilent(true);

        jc.set("foo", "abcdef");

                final JexlContext jc0 = jc;
        final String expression0 = "foo.charAt(2)";
        final Object expected0 = new Character('c');
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testCharAtBug_3_oe_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setSilent(true);

        jc.set("foo", "abcdef");

                final JexlContext jc0 = jc;
        final String expression0 = "foo.charAt(-2)";
        final Object expected0 = null;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testEmptyDottedVariableName_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        jc.set("this.is.a.test", "");

                final JexlContext jc0 = jc;
        final String expression0 = "empty(this.is.a.test)";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testEmptySubListOfMap_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, ArrayList<?>> m = new HashMap<String, ArrayList<?>>();
        m.put("aList", new ArrayList<Object>());

        jc.set("aMap", m);

                final JexlContext jc0 = jc;
        final String expression0 = "empty( aMap.aList )";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testCoercionWithComparisionOperators_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

                final JexlContext jc0 = jc;
        final String expression0 = "'2' > 1";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testCoercionWithComparisionOperators_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

                final JexlContext jc0 = jc;
        final String expression0 = "'2' >= 1";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testCoercionWithComparisionOperators_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

                final JexlContext jc0 = jc;
        final String expression0 = "'2' >= 2";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testCoercionWithComparisionOperators_4_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

                final JexlContext jc0 = jc;
        final String expression0 = "'2' < 1";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testCoercionWithComparisionOperators_5_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

                final JexlContext jc0 = jc;
        final String expression0 = "'2' <= 1";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testCoercionWithComparisionOperators_6_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

                final JexlContext jc0 = jc;
        final String expression0 = "'2' <= 2";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testCoercionWithComparisionOperators_7_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();


                final JexlContext jc0 = jc;
        final String expression0 = "2 > '1'";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testCoercionWithComparisionOperators_8_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();


                final JexlContext jc0 = jc;
        final String expression0 = "2 >= '1'";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testCoercionWithComparisionOperators_9_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();


                final JexlContext jc0 = jc;
        final String expression0 = "2 >= '2'";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testCoercionWithComparisionOperators_10_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();


                final JexlContext jc0 = jc;
        final String expression0 = "2 < '1'";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testCoercionWithComparisionOperators_11_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();


                final JexlContext jc0 = jc;
        final String expression0 = "2 <= '1'";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testCoercionWithComparisionOperators_12_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();


                final JexlContext jc0 = jc;
        final String expression0 = "2 <= '2'";
        final Object expected0 = Boolean.TRUE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testStringConcatenation_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("first", "Hello");
        jc.set("second", "World");
                final JexlContext jc0 = jc;
        final String expression0 = "first + ' ' + second";
        final Object expected0 = "Hello World";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testComment_1_oe_1_oe() throws Exception {
                final JexlContext jc0 = new MapContext();
        final String expression0 = "## double or nothing\n 1 + 1";
        final Object expected0 = Integer.valueOf("2");
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testAssignment_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("aString", "Hello");
        final Foo foo = new Foo();
        jc.set("foo", foo);
        final Parser parser = new Parser(";");
        parser.parse(null, new JexlFeatures().register(false), "aString = 'World';", null);

                final JexlContext jc0 = jc;
        final String expression0 = "hello = 'world'";
        final Object expected0 = "world";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testAssignment_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("aString", "Hello");
        final Foo foo = new Foo();
        jc.set("foo", foo);
        final Parser parser = new Parser(";");
        parser.parse(null, new JexlFeatures().register(false), "aString = 'World';", null);

                final JexlContext jc0 = jc;
        final String expression0 = "result = 1 + 1";
        final Object expected0 = new Integer(2);
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testAntPropertiesWithMethods_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final String value = "Stinky Cheese";
        jc.set("maven.bob.food", value);
                final JexlContext jc0 = jc;
        final String expression0 = "maven.bob.food.length()";
        final Object expected0 = new Integer(value.length());
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testAntPropertiesWithMethods_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final String value = "Stinky Cheese";
        jc.set("maven.bob.food", value);
                final JexlContext jc0 = jc;
        final String expression0 = "empty(maven.bob.food)";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testAntPropertiesWithMethods_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final String value = "Stinky Cheese";
        jc.set("maven.bob.food", value);
                final JexlContext jc0 = jc;
        final String expression0 = "size(maven.bob.food)";
        final Object expected0 = new Integer(value.length());
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testAntPropertiesWithMethods_4_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final String value = "Stinky Cheese";
        jc.set("maven.bob.food", value);
                final JexlContext jc0 = jc;
        final String expression0 = "maven.bob.food + ' is good'";
        final Object expected0 = value + " is good";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testUnicodeSupport_1_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
                final JexlContext jc0 = jc;
        final String expression0 = "'x' == '\\u0032?ytkownik'";
        final Object expected0 = Boolean.FALSE;
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testUnicodeSupport_2_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
                final JexlContext jc0 = jc;
        final String expression0 = "'c:\\some\\windows\\path'";
        final Object expected0 = "c:\\some\\windows\\path";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testUnicodeSupport_3_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
                final JexlContext jc0 = jc;
        final String expression0 = "'foo\\u0020bar'";
        final Object expected0 = "foo\u0020bar";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testUnicodeSupport_4_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
                final JexlContext jc0 = jc;
        final String expression0 = "'foo\\u0020\\u0020bar'";
        final Object expected0 = "foo\u0020\u0020bar";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

    @Test
    public void testUnicodeSupport_5_oe_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
                final JexlContext jc0 = jc;
        final String expression0 = "'\\u0020foobar\\u0020'";
        final Object expected0 = "\u0020foobar\u0020";
        final JexlExpression e0 = JEXL.createExpression(expression0);
                final Object actual0 = e0.evaluate(jc0);
                Assert.assertEquals(expression0, expected0, actual0);
    }

}
