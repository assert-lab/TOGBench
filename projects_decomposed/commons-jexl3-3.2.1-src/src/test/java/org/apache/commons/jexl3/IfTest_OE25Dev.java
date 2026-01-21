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

import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for the if statement.
 *
 * @since 1.1
 */
@SuppressWarnings({"UnnecessaryBoxing", "AssertEqualsBetweenInconvertibleTypes"})
public class IfTest_OE25Dev extends JexlTestCase {
    public IfTest_OE25Dev() {
        super("IfTest");
    }

    /**
     * Make sure if true executes the true statement
     *
     * @throws Exception on any error
     */

    /**
     * Make sure if false doesn't execute the true statement
     *
     * @throws Exception on any error
     */

    /**
     * Make sure if false executes the false statement
     *
     * @throws Exception on any error
     */

    /**
     * Test the if statement handles blocks correctly
     *
     * @throws Exception on any error
     */

    /**
     * Test the if statement handles blocks in the else statement correctly
     *
     * @throws Exception on any error
     */

    /**
     * Test the if statement evaluates expressions correctly
     *
     * @throws Exception on any error
     */

    /**
     * Test the if statement evaluates arithmetic expressions correctly
     *
     * @throws Exception on any error
     */

    /**
     * Test the if statement evaluates decimal arithmetic expressions correctly
     *
     * @throws Exception on any error
     */

    /**
     * Test the if statement works with assignment
     *
     * @throws Exception on any error
     */

    /**
     * Ternary operator condition undefined or null evaluates to false
     * independantly of engine flags.
     * @throws Exception
     */

    /**
     * Ternary operator condition undefined or null evaluates to false
     * independently of engine flags; same for null coalescing operator.
     * @throws Exception
     */

    @Test
    public void testSimpleIfTrue_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if (true) 1");
        final JexlContext jc = new MapContext();

        final Object o = e.execute(jc);
        Assert.assertEquals("Result is not 1", new Integer(1), o);
    }

    @Test
    public void testSimpleIfFalse_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if (false) 1");
        final JexlContext jc = new MapContext();

        final Object o = e.execute(jc);
        Assert.assertNull("Return value is not empty", o);
    }

    @Test
    public void testSimpleElse_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if (false) 1 else 2;");
        final JexlContext jc = new MapContext();

        final Object o = e.execute(jc);
        Assert.assertEquals("Result is not 2", new Integer(2), o);
    }

    @Test
    public void testBlockIfTrue_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if (true) { 'hello'; }");
        final JexlContext jc = new MapContext();

        final Object o = e.execute(jc);
        Assert.assertEquals("Result is wrong", "hello", o);
    }

    @Test
    public void testBlockElse_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if (false) {1} else {2 ; 3}");
        final JexlContext jc = new MapContext();

        final Object o = e.execute(jc);
        Assert.assertEquals("Result is wrong", new Integer(3), o);
    }

    @Test
    public void testIfWithSimpleExpression_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if (x == 1) true;");
        final JexlContext jc = new MapContext();
        jc.set("x", new Integer(1));

        final Object o = e.execute(jc);
        Assert.assertEquals("Result is not true", Boolean.TRUE, o);
    }

    @Test
    public void testIfElseIfExpression_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if (x == 1) { 10; } else if (x == 2) 20  else 30", "x");
        Object o = e.execute(null, 1);
        Assert.assertEquals(10, o);
    }

    @Test
    public void testIfElseIfExpression_2_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if (x == 1) { 10; } else if (x == 2) 20  else 30", "x");
        Object o = e.execute(null, 1);
        // removed other assertion
        o = e.execute(null, 2);
        Assert.assertEquals(20, o);
    }

    @Test
    public void testIfElseIfExpression_3_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if (x == 1) { 10; } else if (x == 2) 20  else 30", "x");
        Object o = e.execute(null, 1);
        // removed other assertion
        o = e.execute(null, 2);
        // removed other assertion
        o = e.execute(null, 4);
        Assert.assertEquals(30, o);
    }

    @Test
    public void testIfElseIfReturnExpression0_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript(
                "if (x == 1) return 10; if (x == 2)  return 20; else if (x == 3) return 30  else { return 40 }",
                "x");
        Object o = e.execute(null, 1);
        Assert.assertEquals(10, o);
    }

    @Test
    public void testIfElseIfReturnExpression0_2_oe() throws Exception {
        final JexlScript e = JEXL.createScript(
                "if (x == 1) return 10; if (x == 2)  return 20; else if (x == 3) return 30  else { return 40 }",
                "x");
        Object o = e.execute(null, 1);
        // removed other assertion
        o = e.execute(null, 2);
        Assert.assertEquals(20, o);
    }

    @Test
    public void testIfElseIfReturnExpression0_3_oe() throws Exception {
        final JexlScript e = JEXL.createScript(
                "if (x == 1) return 10; if (x == 2)  return 20; else if (x == 3) return 30  else { return 40 }",
                "x");
        Object o = e.execute(null, 1);
        // removed other assertion
        o = e.execute(null, 2);
        // removed other assertion
        o = e.execute(null, 3);
        Assert.assertEquals(30, o);
    }

    @Test
    public void testIfElseIfReturnExpression0_4_oe() throws Exception {
        final JexlScript e = JEXL.createScript(
                "if (x == 1) return 10; if (x == 2)  return 20; else if (x == 3) return 30  else { return 40 }",
                "x");
        Object o = e.execute(null, 1);
        // removed other assertion
        o = e.execute(null, 2);
        // removed other assertion
        o = e.execute(null, 3);
        // removed other assertion
        o = e.execute(null, 4);
        Assert.assertEquals(40, o);
    }

    @Test
    public void testIfElseIfReturnExpression_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript(
                "if (x == 1) return 10;  if (x == 2) return 20  else if (x == 3) return 30; else return 40;",
                "x");
        Object o = e.execute(null, 1);
        Assert.assertEquals(10, o);
    }

    @Test
    public void testIfElseIfReturnExpression_2_oe() throws Exception {
        final JexlScript e = JEXL.createScript(
                "if (x == 1) return 10;  if (x == 2) return 20  else if (x == 3) return 30; else return 40;",
                "x");
        Object o = e.execute(null, 1);
        // removed other assertion
        o = e.execute(null, 2);
        Assert.assertEquals(20, o);
    }

    @Test
    public void testIfElseIfReturnExpression_3_oe() throws Exception {
        final JexlScript e = JEXL.createScript(
                "if (x == 1) return 10;  if (x == 2) return 20  else if (x == 3) return 30; else return 40;",
                "x");
        Object o = e.execute(null, 1);
        // removed other assertion
        o = e.execute(null, 2);
        // removed other assertion
        o = e.execute(null, 3);
        Assert.assertEquals(30, o);
    }

    @Test
    public void testIfElseIfReturnExpression_4_oe() throws Exception {
        final JexlScript e = JEXL.createScript(
                "if (x == 1) return 10;  if (x == 2) return 20  else if (x == 3) return 30; else return 40;",
                "x");
        Object o = e.execute(null, 1);
        // removed other assertion
        o = e.execute(null, 2);
        // removed other assertion
        o = e.execute(null, 3);
        // removed other assertion
        o = e.execute(null, 4);
        Assert.assertEquals(40, o);
    }

    @Test
    public void testIfWithArithmeticExpression_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if ((x * 2) + 1 == 5) true;");
        final JexlContext jc = new MapContext();
        jc.set("x", new Integer(2));

        final Object o = e.execute(jc);
        Assert.assertEquals("Result is not true", Boolean.TRUE, o);
    }

    @Test
    public void testIfWithDecimalArithmeticExpression_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if ((x * 2) == 5) true");
        final JexlContext jc = new MapContext();
        jc.set("x", new Float(2.5f));

        final Object o = e.execute(jc);
        Assert.assertEquals("Result is not true", Boolean.TRUE, o);
    }

    @Test
    public void testIfWithAssignment_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if ((x * 2) == 5) {y = 1} else {y = 2;}");
        final JexlContext jc = new MapContext();
        jc.set("x", new Float(2.5f));

        e.execute(jc);
        final Object result = jc.get("y");
        Assert.assertEquals("y has the wrong value", new Integer(1), result);
    }

    @Test
    public void testTernary_1_oe() throws Exception {
        final JexlEngine jexl = JEXL;

        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = jexl.createExpression("x.y.z = foo ?'bar':'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernary_2_oe() throws Exception {
        final JexlEngine jexl = JEXL;

        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = jexl.createExpression("x.y.z = foo ?'bar':'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernary_3_oe() throws Exception {
        final JexlEngine jexl = JEXL;

        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = jexl.createExpression("x.y.z = foo ?'bar':'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernary_4_oe() throws Exception {
        final JexlEngine jexl = JEXL;

        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = jexl.createExpression("x.y.z = foo ?'bar':'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernary_5_oe() throws Exception {
        final JexlEngine jexl = JEXL;

        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = jexl.createExpression("x.y.z = foo ?'bar':'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernary_6_oe() throws Exception {
        final JexlEngine jexl = JEXL;

        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = jexl.createExpression("x.y.z = foo ?'bar':'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernary_7_oe() throws Exception {
        final JexlEngine jexl = JEXL;

        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = jexl.createExpression("x.y.z = foo ?'bar':'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
        }

        jc.set("foo", Boolean.TRUE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            Assert.assertEquals("Should be bar", "bar", o);
    }
    }

    @Test
    public void testTernary_8_oe() throws Exception {
        final JexlEngine jexl = JEXL;

        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = jexl.createExpression("x.y.z = foo ?'bar':'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
        }

        jc.set("foo", Boolean.TRUE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            Assert.assertEquals("Should be bar", "bar", o);
    }
    }

    @Test
    public void testTernaryShorthand_1_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_2_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_3_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_4_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_5_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_6_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_7_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_8_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_9_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            Assert.assertEquals("Should be false", false, o);
    }
    }

    @Test
    public void testTernaryShorthand_10_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Double.NaN);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_11_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Double.NaN);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_12_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Double.NaN);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            Assert.assertTrue("Should be NaN", Double.isNaN((Double) o));
    }
    }

    @Test
    public void testTernaryShorthand_13_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Double.NaN);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_14_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Double.NaN);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_15_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Double.NaN);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            Assert.assertEquals("Should be empty string", "", o);
    }
    }

    @Test
    public void testTernaryShorthand_16_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Double.NaN);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "false");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_17_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Double.NaN);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "false");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_18_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Double.NaN);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "false");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            Assert.assertEquals("Should be 'false'", "false", o);
    }
    }

    @Test
    public void testTernaryShorthand_19_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Double.NaN);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "false");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", 0d);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_20_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Double.NaN);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "false");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", 0d);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_21_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Double.NaN);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "false");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", 0d);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            Assert.assertEquals("Should be 0", 0.d, o);
    }
    }

    @Test
    public void testTernaryShorthand_22_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Double.NaN);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "false");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", 0d);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", 0);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_23_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Double.NaN);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "false");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", 0d);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", 0);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            Assert.assertEquals("Should be quux", "quux", o);
    }
    }

    @Test
    public void testTernaryShorthand_24_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Double.NaN);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "false");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", 0d);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", 0);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            Assert.assertEquals("Should be 0", 0, o);
    }
    }

    @Test
    public void testTernaryShorthand_25_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Double.NaN);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "false");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", 0d);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", 0);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "bar");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            Assert.assertEquals("Should be bar", "bar", o);
    }
    }

    @Test
    public void testTernaryShorthand_26_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("x.y.z = foo?:'quux'");
        final JexlExpression f = JEXL.createExpression("foo??'quux'");
        Object o;

        // undefined foo
        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", null);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Boolean.FALSE);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", Double.NaN);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "false");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", 0d);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", 0);

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            // removed other assertion
            o = f.evaluate(jc);
            // removed other assertion
        }

        jc.set("foo", "bar");

        for (int l = 0; l < 4; ++l) {
            options.setStrict((l & 1) == 0);
            options.setSilent((l & 2) != 0);
            o = e.evaluate(jc);
            // removed other assertion
            o = jc.get("x.y.z");
            Assert.assertEquals("Should be bar", "bar", o);
    }
    }

    @Test
    public void testNullCoaelescing_1_oe() throws Exception {
        Object o;
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlExpression xtrue = JEXL.createExpression("x??true");
        o = xtrue.evaluate(jc);
        Assert.assertEquals("Should be true", true, o);
    }

    @Test
    public void testNullCoaelescing_2_oe() throws Exception {
        Object o;
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlExpression xtrue = JEXL.createExpression("x??true");
        o = xtrue.evaluate(jc);
        // removed other assertion
        jc.set("x", false);
        o = xtrue.evaluate(jc);
        Assert.assertEquals("Should be false", false, o);
    }

    @Test
    public void testNullCoaelescing_3_oe() throws Exception {
        Object o;
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlExpression xtrue = JEXL.createExpression("x??true");
        o = xtrue.evaluate(jc);
        // removed other assertion
        jc.set("x", false);
        o = xtrue.evaluate(jc);
        // removed other assertion
        final JexlExpression yone = JEXL.createExpression("y??1");
        o = yone.evaluate(jc);
        Assert.assertEquals("Should be 1", 1, o);
    }

    @Test
    public void testNullCoaelescing_4_oe() throws Exception {
        Object o;
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlExpression xtrue = JEXL.createExpression("x??true");
        o = xtrue.evaluate(jc);
        // removed other assertion
        jc.set("x", false);
        o = xtrue.evaluate(jc);
        // removed other assertion
        final JexlExpression yone = JEXL.createExpression("y??1");
        o = yone.evaluate(jc);
        // removed other assertion
        jc.set("y", 0);
        o = yone.evaluate(jc);
        Assert.assertEquals("Should be 0", 0, o);
    }

    @Test
    public void testNullCoaelescingScript_1_oe() throws Exception {
        Object o;
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlScript xtrue = JEXL.createScript("x??true");
        o = xtrue.execute(jc);
        Assert.assertEquals("Should be true", true, o);
    }

    @Test
    public void testNullCoaelescingScript_2_oe() throws Exception {
        Object o;
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlScript xtrue = JEXL.createScript("x??true");
        o = xtrue.execute(jc);
        // removed other assertion
        jc.set("x", false);
        o = xtrue.execute(jc);
        Assert.assertEquals("Should be false", false, o);
    }

    @Test
    public void testNullCoaelescingScript_3_oe() throws Exception {
        Object o;
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlScript xtrue = JEXL.createScript("x??true");
        o = xtrue.execute(jc);
        // removed other assertion
        jc.set("x", false);
        o = xtrue.execute(jc);
        // removed other assertion
        final JexlScript yone = JEXL.createScript("y??1");
        o = yone.execute(jc);
        Assert.assertEquals("Should be 1", 1, o);
    }

    @Test
    public void testNullCoaelescingScript_4_oe() throws Exception {
        Object o;
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlScript xtrue = JEXL.createScript("x??true");
        o = xtrue.execute(jc);
        // removed other assertion
        jc.set("x", false);
        o = xtrue.execute(jc);
        // removed other assertion
        final JexlScript yone = JEXL.createScript("y??1");
        o = yone.execute(jc);
        // removed other assertion
        jc.set("y", 0);
        o = yone.execute(jc);
        Assert.assertEquals("Should be 0", 0, o);
    }

    @Test
    public void testTernaryFail_2_oe() throws Exception {
        final JexlEvalContext jc = new JexlEvalContext();
        final JexlOptions options = jc.getEngineOptions();
        final JexlExpression e = JEXL.createExpression("false ? bar : quux");
        Object o;
        options.setStrict(true);
        options.setSilent(false);
        try {
           o = e.evaluate(jc);
           // removed other assertion
        } catch (final JexlException xjexl) {
           // OK
           Assert.assertTrue(xjexl.toString().contains("quux"));
    }
    }

}
