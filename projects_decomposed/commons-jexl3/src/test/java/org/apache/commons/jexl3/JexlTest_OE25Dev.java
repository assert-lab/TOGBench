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

// TODO: verify inlining
    @Test
    public void testBoolean_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

        assertExpression(jc, "foo.convertBoolean(a==b)", "Boolean : false");
    }

// TODO: verify inlining
    @Test
    public void testBoolean_2_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

        // removed other assertion
        assertExpression(jc, "foo.convertBoolean(a==true)", "Boolean : true");
    }

// TODO: verify inlining
    @Test
    public void testBoolean_3_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

        // removed other assertion
        // removed other assertion
        assertExpression(jc, "foo.convertBoolean(a==false)", "Boolean : false");
    }

// TODO: verify inlining
    @Test
    public void testBoolean_4_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "foo.convertBoolean(true==false)", "Boolean : false");
    }

// TODO: verify inlining
    @Test
    public void testBoolean_5_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "true eq false", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testBoolean_6_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("a", Boolean.TRUE);
        jc.set("b", Boolean.FALSE);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "true ne false", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testStringLit_1_oe() throws Exception {
        /*
         *  tests a simple property expression
         */
        final JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        assertExpression(jc, "foo.repeat(\"woogie\")", "Repeat : woogie");
    }

// TODO: verify inlining
    @Test
    public void testExpression_1_oe() throws Exception {
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

        assertExpression(jc, "a == b", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_2_oe() throws Exception {
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
        assertExpression(jc, "a==true", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_3_oe() throws Exception {
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
        assertExpression(jc, "a==false", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_4_oe() throws Exception {
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
        assertExpression(jc, "true==false", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_5_oe() throws Exception {
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

        assertExpression(jc, "2 < 3", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_6_oe() throws Exception {
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
        assertExpression(jc, "num < 5", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_7_oe() throws Exception {
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
        assertExpression(jc, "num < num", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_8_oe() throws Exception {
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
        assertExpression(jc, "num < null", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_9_oe() throws Exception {
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
        assertExpression(jc, "num < 2.5", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_10_oe() throws Exception {
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
        assertExpression(jc, "now2 < now", Boolean.FALSE); // test comparable;
    }

// TODO: verify inlining
    @Test
    public void testExpression_11_oe() throws Exception {
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
        assertExpression(jc, "'6' <= '5'", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_12_oe() throws Exception {
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
        assertExpression(jc, "num <= 5", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_13_oe() throws Exception {
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
        assertExpression(jc, "num <= num", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_14_oe() throws Exception {
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
        assertExpression(jc, "num <= null", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_15_oe() throws Exception {
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
        assertExpression(jc, "num <= 2.5", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_16_oe() throws Exception {
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
        assertExpression(jc, "now2 <= now", Boolean.FALSE); // test comparable;
    }

// TODO: verify inlining
    @Test
    public void testExpression_17_oe() throws Exception {
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
        assertExpression(jc, "'6' >= '5'", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_18_oe() throws Exception {
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
        assertExpression(jc, "num >= 5", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_19_oe() throws Exception {
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
        assertExpression(jc, "num >= num", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_20_oe() throws Exception {
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
        assertExpression(jc, "num >= null", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_21_oe() throws Exception {
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
        assertExpression(jc, "num >= 2.5", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_22_oe() throws Exception {
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
        assertExpression(jc, "now2 >= now", Boolean.TRUE); // test comparable;
    }

// TODO: verify inlining
    @Test
    public void testExpression_23_oe() throws Exception {
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

        assertExpression(jc, "'6' > '5'", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_24_oe() throws Exception {
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
        assertExpression(jc, "num > 4", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_25_oe() throws Exception {
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
        assertExpression(jc, "num > num", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_26_oe() throws Exception {
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
        assertExpression(jc, "num > null", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_27_oe() throws Exception {
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
        assertExpression(jc, "num > 2.5", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_28_oe() throws Exception {
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
        assertExpression(jc, "now2 > now", Boolean.TRUE); // test comparable;
    }

// TODO: verify inlining
    @Test
    public void testExpression_29_oe() throws Exception {
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

        assertExpression(jc, "\"foo\" + \"bar\" == \"foobar\"", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_30_oe() throws Exception {
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

        assertExpression(jc, "bdec > num", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_31_oe() throws Exception {
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
        assertExpression(jc, "bdec >= num", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_32_oe() throws Exception {
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
        assertExpression(jc, "num <= bdec", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_33_oe() throws Exception {
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
        assertExpression(jc, "num < bdec", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_34_oe() throws Exception {
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
        assertExpression(jc, "bint > num", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_35_oe() throws Exception {
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
        assertExpression(jc, "bint == bdec", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_36_oe() throws Exception {
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
        assertExpression(jc, "bint >= num", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_37_oe() throws Exception {
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
        assertExpression(jc, "num <= bint", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testExpression_38_oe() throws Exception {
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
        assertExpression(jc, "num < bint", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testEmpty_1_oe() throws Exception {
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
        assertExpression(jc, "empty nullthing", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testEmpty_2_oe() throws Exception {
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
        assertExpression(jc, "empty string", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testEmpty_3_oe() throws Exception {
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
        assertExpression(jc, "empty array", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testEmpty_4_oe() throws Exception {
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
        assertExpression(jc, "empty map", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testEmpty_5_oe() throws Exception {
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
        assertExpression(jc, "empty set", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testEmpty_6_oe() throws Exception {
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
        assertExpression(jc, "empty list", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testEmpty_7_oe() throws Exception {
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
        assertExpression(jc, "empty longstring", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testEmpty_8_oe() throws Exception {
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
        assertExpression(jc, "not empty longstring", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testSize_1_oe() throws Exception {
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

        assertExpression(jc, "size(s)", new Integer(5));
    }

// TODO: verify inlining
    @Test
    public void testSize_2_oe() throws Exception {
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
        assertExpression(jc, "size(array)", new Integer(5));
    }

// TODO: verify inlining
    @Test
    public void testSize_3_oe() throws Exception {
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
        assertExpression(jc, "size(list)", new Integer(5));
    }

// TODO: verify inlining
    @Test
    public void testSize_4_oe() throws Exception {
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
        assertExpression(jc, "size(map)", new Integer(5));
    }

// TODO: verify inlining
    @Test
    public void testSize_5_oe() throws Exception {
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
        assertExpression(jc, "size(set)", new Integer(5));
    }

// TODO: verify inlining
    @Test
    public void testSize_6_oe() throws Exception {
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
        assertExpression(jc, "size(bitset)", new Integer(64));
    }

// TODO: verify inlining
    @Test
    public void testSize_7_oe() throws Exception {
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
        assertExpression(jc, "list.size()", new Integer(5));
    }

// TODO: verify inlining
    @Test
    public void testSize_8_oe() throws Exception {
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
        assertExpression(jc, "map.size()", new Integer(5));
    }

// TODO: verify inlining
    @Test
    public void testSize_9_oe() throws Exception {
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
        assertExpression(jc, "set.size()", new Integer(5));
    }

// TODO: verify inlining
    @Test
    public void testSize_10_oe() throws Exception {
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
        assertExpression(jc, "bitset.size()", new Integer(64));
    }

// TODO: verify inlining
    @Test
    public void testSize_11_oe() throws Exception {
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

        assertExpression(jc, "list.get(size(list) - 1)", "5");
    }

// TODO: verify inlining
    @Test
    public void testSize_12_oe() throws Exception {
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
        assertExpression(jc, "list[size(list) - 1]", "5");
    }

// TODO: verify inlining
    @Test
    public void testSize_13_oe() throws Exception {
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
        assertExpression(jc, "list.get(list.size() - 1)", "5");
    }

// TODO: verify inlining
    @Test
    public void testSizeAsProperty_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

        assertExpression(jc, "map['size']", "cheese");
    }

// TODO: verify inlining
    @Test
    public void testSizeAsProperty_2_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

        // removed other assertion
        assertExpression(jc, "map['si & ze']", "cheese");
    }

// TODO: verify inlining
    @Test
    public void testSizeAsProperty_3_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

        // removed other assertion
        // removed other assertion
        assertExpression(jc, "map.'si & ze'", "cheese");
    }

// TODO: verify inlining
    @Test
    public void testSizeAsProperty_4_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", "cheese");
        map.put("si & ze", "cheese");
        jc.set("map", map);
        jc.set("foo", new Foo());

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "map.size()", 2);
    }

// TODO: verify inlining
    @Test
    public void testSizeAsProperty_5_oe() throws Exception {
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
        assertExpression(jc, "size(map)", 2);
    }

// TODO: verify inlining
    @Test
    public void testSizeAsProperty_6_oe() throws Exception {
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
        assertExpression(jc, "foo.getSize()", 22);
    }

// TODO: verify inlining
    @Test
    public void testSizeAsProperty_7_oe() throws Exception {
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
        assertExpression(jc, "foo.'size'", 22);
    }

// TODO: verify inlining
    @Test
    public void testCalculations_1_oe() throws Exception {
        asserter.setStrict(true, false);
        /*
         * test new null coersion
         */
        asserter.setVariable("imanull", null);
        asserter.assertExpression("imanull + 2", new Integer(2));
    }

// TODO: verify inlining
    @Test
    public void testCalculations_2_oe() throws Exception {
        asserter.setStrict(true, false);
        /*
         * test new null coersion
         */
        asserter.setVariable("imanull", null);
        // removed other assertion
        asserter.assertExpression("imanull + imanull", new Integer(0));
    }

// TODO: verify inlining
    @Test
    public void testCalculations_3_oe() throws Exception {
        asserter.setStrict(true, false);
        /*
         * test new null coersion
         */
        asserter.setVariable("imanull", null);
        // removed other assertion
        // removed other assertion
        asserter.setVariable("foo", new Integer(2));

        asserter.assertExpression("foo + 2", new Integer(4));
    }

// TODO: verify inlining
    @Test
    public void testCalculations_4_oe() throws Exception {
        asserter.setStrict(true, false);
        /*
         * test new null coersion
         */
        asserter.setVariable("imanull", null);
        // removed other assertion
        // removed other assertion
        asserter.setVariable("foo", new Integer(2));

        // removed other assertion
        asserter.assertExpression("3 + 3", new Integer(6));
    }

// TODO: verify inlining
    @Test
    public void testConditions_1_oe() throws Exception {
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

        assertExpression(jc, "foo == 2", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_2_oe() throws Exception {
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
        assertExpression(jc, "2 == 3", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_3_oe() throws Exception {
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
        assertExpression(jc, "3 == foo", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_4_oe() throws Exception {
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
        assertExpression(jc, "3 != foo", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_5_oe() throws Exception {
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
        assertExpression(jc, "foo != 2", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_6_oe() throws Exception {
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
        assertExpression(jc, "aFloat eq aDouble", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_7_oe() throws Exception {
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
        assertExpression(jc, "aFloat ne aDouble", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_8_oe() throws Exception {
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
        assertExpression(jc, "aFloat == aDouble", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_9_oe() throws Exception {
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
        assertExpression(jc, "aFloat != aDouble", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_10_oe() throws Exception {
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
        assertExpression(jc, "foo == aChar", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_11_oe() throws Exception {
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
        assertExpression(jc, "foo != aChar", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_12_oe() throws Exception {
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
        assertExpression(jc, "aBool == 'true'", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_13_oe() throws Exception {
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
        assertExpression(jc, "aBool == 'false'", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_14_oe() throws Exception {
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
        assertExpression(jc, "aBool != 'false'", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_15_oe() throws Exception {
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
        assertExpression(jc, "aBool == notThere", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_16_oe() throws Exception {
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
        assertExpression(jc, "aBool != notThere", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_17_oe() throws Exception {
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
        assertExpression(jc, "aBuffer == 'abc'", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_18_oe() throws Exception {
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
        assertExpression(jc, "aBuffer != 'abc'", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_19_oe() throws Exception {
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
        assertExpression(jc, "aList == bList", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testConditions_20_oe() throws Exception {
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
        assertExpression(jc, "aList != bList", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditions_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        assertExpression(jc, "!x", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditions_2_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
        assertExpression(jc, "x", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditions_3_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
        // removed other assertion
        assertExpression(jc, "!bar", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditions_4_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "!foo.isSimple()", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditions_5_oe() throws Exception {
        final JexlContext jc = new MapContext();

        final Foo foo = new Foo();
        jc.set("x", Boolean.TRUE);
        jc.set("foo", foo);
        jc.set("bar", "true");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "foo.isSimple()", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditions_6_oe() throws Exception {
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
        assertExpression(jc, "!foo.simple", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditions_7_oe() throws Exception {
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
        assertExpression(jc, "foo.simple", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditions_8_oe() throws Exception {
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
        assertExpression(jc, "foo.getCheeseList().size() == 3", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditions_9_oe() throws Exception {
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
        assertExpression(jc, "foo.cheeseList.size() == 3", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditions_10_oe() throws Exception {
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
        assertExpression(jc, "not empty string", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditions_11_oe() throws Exception {
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
        assertExpression(jc, "not(empty string)", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditions_12_oe() throws Exception {
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
        assertExpression(jc, "not empty(string)", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditions_13_oe() throws Exception {
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
        assertExpression(jc, "! empty string", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditions_14_oe() throws Exception {
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
        assertExpression(jc, "!(empty string)", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditions_15_oe() throws Exception {
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
        assertExpression(jc, "! empty(string)", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditionsWithDots_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        jc.set("x.a", Boolean.TRUE);
        jc.set("x.b", Boolean.FALSE);

        assertExpression(jc, "x.a", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditionsWithDots_2_oe() throws Exception {
        final JexlContext jc = new MapContext();

        jc.set("x.a", Boolean.TRUE);
        jc.set("x.b", Boolean.FALSE);

        // removed other assertion
        assertExpression(jc, "!x.a", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testNotConditionsWithDots_3_oe() throws Exception {
        final JexlContext jc = new MapContext();

        jc.set("x.a", Boolean.TRUE);
        jc.set("x.b", Boolean.FALSE);

        // removed other assertion
        // removed other assertion
        assertExpression(jc, "!x.b", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testComparisons_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "the quick and lazy fox");

        assertExpression(jc, "foo.indexOf('quick') > 0", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testComparisons_2_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "the quick and lazy fox");

        // removed other assertion
        assertExpression(jc, "foo.indexOf('bar') >= 0", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testComparisons_3_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "the quick and lazy fox");

        // removed other assertion
        // removed other assertion
        assertExpression(jc, "foo.indexOf('bar') < 0", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testNull_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

        assertExpression(jc, "empty foo", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testNull_2_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

        // removed other assertion
        assertExpression(jc, "bar == null", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testNull_3_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

        // removed other assertion
        // removed other assertion
        assertExpression(jc, "foo == null", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testNull_4_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "bar != null", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testNull_5_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "foo != null", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testNull_6_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setStrict(false);
        jc.set("bar", new Integer(2));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "empty(bar)", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testNull_7_oe() throws Exception {
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
        assertExpression(jc, "empty(foo)", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testStringQuoting_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        assertExpression(jc, "'\"Hello\"'", "\"Hello\"");
    }

// TODO: verify inlining
    @Test
    public void testStringQuoting_2_oe() throws Exception {
        final JexlContext jc = new MapContext();
        // removed other assertion
        assertExpression(jc, "\"I'm testing\"", "I'm testing");
    }

// TODO: verify inlining
    @Test
    public void testBlankStrings_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("bar", "");

        assertExpression(jc, "bar == ''", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testBlankStrings_2_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("bar", "");

        // removed other assertion
        assertExpression(jc, "empty bar", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testBlankStrings_3_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("bar", "");

        // removed other assertion
        // removed other assertion
        assertExpression(jc, "bar.length() == 0", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testBlankStrings_4_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("bar", "");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "size(bar) == 0", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testLogicExpressions_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

        assertExpression(jc, "foo == 'abc' || bar == 'abc'", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testLogicExpressions_2_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

        // removed other assertion
        assertExpression(jc, "foo == 'abc' or bar == 'abc'", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testLogicExpressions_3_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

        // removed other assertion
        // removed other assertion
        assertExpression(jc, "foo == 'abc' && bar == 'abc'", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testLogicExpressions_4_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "foo == 'abc' and bar == 'abc'", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testLogicExpressions_5_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertExpression(jc, "foo == 'def' || bar == 'abc'", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testLogicExpressions_6_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertExpression(jc, "foo == 'def' or bar == 'abc'", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testLogicExpressions_7_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "abc");
        jc.set("bar", "def");

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertExpression(jc, "foo == 'abc' && bar == 'def'", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testLogicExpressions_8_oe() throws Exception {
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
        assertExpression(jc, "foo == 'abc' and bar == 'def'", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testVariableNames_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo_bar", "123");

        assertExpression(jc, "foo_bar", "123");
    }

// TODO: verify inlining
    @Test
    public void testMapDot_1_oe() throws Exception {
        final Map<String, String> foo = new HashMap<String, String>();
        foo.put("bar", "123");

        final JexlContext jc = new MapContext();
        jc.set("foo", foo);

        assertExpression(jc, "foo.bar", "123");
    }

// TODO: verify inlining
    @Test
    public void testStringLiterals_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "bar");

        assertExpression(jc, "foo == \"bar\"", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testStringLiterals_2_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("foo", "bar");

        // removed other assertion
        assertExpression(jc, "foo == 'bar'", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testIntProperty_3_oe() throws Exception {
        final Foo foo = new Foo();

        // lets check the square function first..
        // removed other assertion
        // removed other assertion

        final JexlContext jc = new MapContext();
        jc.set("foo", foo);

        assertExpression(jc, "foo.count", new Integer(5));
    }

// TODO: verify inlining
    @Test
    public void testIntProperty_4_oe() throws Exception {
        final Foo foo = new Foo();

        // lets check the square function first..
        // removed other assertion
        // removed other assertion

        final JexlContext jc = new MapContext();
        jc.set("foo", foo);

        // removed other assertion
        assertExpression(jc, "foo.square(2)", new Integer(4));
    }

// TODO: verify inlining
    @Test
    public void testIntProperty_5_oe() throws Exception {
        final Foo foo = new Foo();

        // lets check the square function first..
        // removed other assertion
        // removed other assertion

        final JexlContext jc = new MapContext();
        jc.set("foo", foo);

        // removed other assertion
        // removed other assertion
        assertExpression(jc, "foo.square(-2)", new Integer(4));
    }

// TODO: verify inlining
    @Test
    public void testNegativeIntComparison_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Foo foo = new Foo();
        jc.set("foo", foo);

        assertExpression(jc, "foo.count != -1", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testNegativeIntComparison_2_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Foo foo = new Foo();
        jc.set("foo", foo);

        // removed other assertion
        assertExpression(jc, "foo.count == 5", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testNegativeIntComparison_3_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Foo foo = new Foo();
        jc.set("foo", foo);

        // removed other assertion
        // removed other assertion
        assertExpression(jc, "foo.count == -1", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testCharAtBug_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setSilent(true);

        jc.set("foo", "abcdef");

        assertExpression(jc, "foo.substring(2,4)", "cd");
    }

// TODO: verify inlining
    @Test
    public void testCharAtBug_2_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setSilent(true);

        jc.set("foo", "abcdef");

        // removed other assertion
        assertExpression(jc, "foo.charAt(2)", new Character('c'));
    }

// TODO: verify inlining
    @Test
    public void testCharAtBug_3_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        options.setSilent(true);

        jc.set("foo", "abcdef");

        // removed other assertion
        // removed other assertion
        assertExpression(jc, "foo.charAt(-2)", null);
    }

// TODO: verify inlining
    @Test
    public void testEmptyDottedVariableName_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        jc.set("this.is.a.test", "");

        assertExpression(jc, "empty(this.is.a.test)", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testEmptySubListOfMap_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final Map<String, ArrayList<?>> m = new HashMap<String, ArrayList<?>>();
        m.put("aList", new ArrayList<Object>());

        jc.set("aMap", m);

        assertExpression(jc, "empty( aMap.aList )", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testCoercionWithComparisionOperators_1_oe() throws Exception {
        final JexlContext jc = new MapContext();

        assertExpression(jc, "'2' > 1", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testCoercionWithComparisionOperators_2_oe() throws Exception {
        final JexlContext jc = new MapContext();

        // removed other assertion
        assertExpression(jc, "'2' >= 1", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testCoercionWithComparisionOperators_3_oe() throws Exception {
        final JexlContext jc = new MapContext();

        // removed other assertion
        // removed other assertion
        assertExpression(jc, "'2' >= 2", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testCoercionWithComparisionOperators_4_oe() throws Exception {
        final JexlContext jc = new MapContext();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "'2' < 1", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testCoercionWithComparisionOperators_5_oe() throws Exception {
        final JexlContext jc = new MapContext();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "'2' <= 1", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testCoercionWithComparisionOperators_6_oe() throws Exception {
        final JexlContext jc = new MapContext();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "'2' <= 2", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testCoercionWithComparisionOperators_7_oe() throws Exception {
        final JexlContext jc = new MapContext();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertExpression(jc, "2 > '1'", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testCoercionWithComparisionOperators_8_oe() throws Exception {
        final JexlContext jc = new MapContext();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertExpression(jc, "2 >= '1'", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testCoercionWithComparisionOperators_9_oe() throws Exception {
        final JexlContext jc = new MapContext();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertExpression(jc, "2 >= '2'", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testCoercionWithComparisionOperators_10_oe() throws Exception {
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
        assertExpression(jc, "2 < '1'", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testCoercionWithComparisionOperators_11_oe() throws Exception {
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
        assertExpression(jc, "2 <= '1'", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testCoercionWithComparisionOperators_12_oe() throws Exception {
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
        assertExpression(jc, "2 <= '2'", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testStringConcatenation_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("first", "Hello");
        jc.set("second", "World");
        assertExpression(jc, "first + ' ' + second", "Hello World");
    }

// TODO: verify inlining
    @Test
    public void testBadParse_1_oe() throws Exception {
        try {
            assertExpression(new MapContext(), "empty()", null);
    }
    }

// TODO: verify inlining
    @Test
    public void testComment_1_oe() throws Exception {
        assertExpression(new MapContext(), "## double or nothing\n 1 + 1", Integer.valueOf("2"));
    }

// TODO: verify inlining
    @Test
    public void testAssignment_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("aString", "Hello");
        final Foo foo = new Foo();
        jc.set("foo", foo);
        final Parser parser = new Parser(";");
        parser.parse(null, new JexlFeatures().register(false), "aString = 'World';", null);

        assertExpression(jc, "hello = 'world'", "world");
    }

// TODO: verify inlining
    @Test
    public void testAssignment_3_oe() throws Exception {
        final JexlContext jc = new MapContext();
        jc.set("aString", "Hello");
        final Foo foo = new Foo();
        jc.set("foo", foo);
        final Parser parser = new Parser(";");
        parser.parse(null, new JexlFeatures().register(false), "aString = 'World';", null);

        // removed other assertion
        // removed other assertion
        assertExpression(jc, "result = 1 + 1", new Integer(2));
    }

// TODO: verify inlining
    @Test
    public void testAntPropertiesWithMethods_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final String value = "Stinky Cheese";
        jc.set("maven.bob.food", value);
        assertExpression(jc, "maven.bob.food.length()", new Integer(value.length()));
    }

// TODO: verify inlining
    @Test
    public void testAntPropertiesWithMethods_2_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final String value = "Stinky Cheese";
        jc.set("maven.bob.food", value);
        // removed other assertion
        assertExpression(jc, "empty(maven.bob.food)", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testAntPropertiesWithMethods_3_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final String value = "Stinky Cheese";
        jc.set("maven.bob.food", value);
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "size(maven.bob.food)", new Integer(value.length()));
    }

// TODO: verify inlining
    @Test
    public void testAntPropertiesWithMethods_4_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final String value = "Stinky Cheese";
        jc.set("maven.bob.food", value);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "maven.bob.food + ' is good'", value + " is good");
    }

// TODO: verify inlining
    @Test
    public void testUnicodeSupport_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        assertExpression(jc, "'x' == '\\u0032?ytkownik'", Boolean.FALSE);
    }

// TODO: verify inlining
    @Test
    public void testUnicodeSupport_2_oe() throws Exception {
        final JexlContext jc = new MapContext();
        // removed other assertion
        assertExpression(jc, "'c:\\some\\windows\\path'", "c:\\some\\windows\\path");
    }

// TODO: verify inlining
    @Test
    public void testUnicodeSupport_3_oe() throws Exception {
        final JexlContext jc = new MapContext();
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "'foo\\u0020bar'", "foo\u0020bar");
    }

// TODO: verify inlining
    @Test
    public void testUnicodeSupport_4_oe() throws Exception {
        final JexlContext jc = new MapContext();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "'foo\\u0020\\u0020bar'", "foo\u0020\u0020bar");
    }

// TODO: verify inlining
    @Test
    public void testUnicodeSupport_5_oe() throws Exception {
        final JexlContext jc = new MapContext();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertExpression(jc, "'\\u0020foobar\\u0020'", "\u0020foobar\u0020");
    }

}
