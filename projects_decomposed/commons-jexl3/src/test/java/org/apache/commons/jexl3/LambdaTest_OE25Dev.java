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

import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests function/lambda/closure features.
 */
@SuppressWarnings({"UnnecessaryBoxing", "AssertEqualsBetweenInconvertibleTypes"})
public class LambdaTest_OE25Dev extends JexlTestCase {

    public LambdaTest_OE25Dev() {
        super("LambdaTest_OE25Dev");
    }

    @Test
    public void testScriptArguments() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript s = jexl.createScript(" x + x ", "x");
        final JexlScript s42 = jexl.createScript("s(21)", "s");
        final Object result = s42.execute(null, s);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testScriptContext() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript s = jexl.createScript("function(x) { x + x }");
        final String fsstr = s.getParsedText(0);
        Assert.assertEquals("(x)->{ x + x; }", fsstr);
        Assert.assertEquals(42, s.execute(null, 21));
        JexlScript s42 = jexl.createScript("s(21)");
        final JexlEvalContext ctxt = new JexlEvalContext();
        ctxt.set("s", s);
        Object result = s42.execute(ctxt);
        Assert.assertEquals(42, result);
        result = s42.execute(ctxt);
        Assert.assertEquals(42, result);
        s42 = jexl.createScript("x-> { x + x }");
        result = s42.execute(ctxt, 21);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testLambda() throws Exception {
        final JexlEngine jexl = createEngine();
        String strs = "var s = function(x) { x + x }; s(21)";
        JexlScript s42 = jexl.createScript(strs);
        Object result = s42.execute(null);
        Assert.assertEquals(42, result);
        strs = "var s = function(x, y) { x + y }; s(15, 27)";
        s42 = jexl.createScript(strs);
        result = s42.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testLambdaClosure() throws Exception {
        final JexlEngine jexl = createEngine();
        String strs = "var t = 20; var s = function(x, y) { x + y + t}; s(15, 7)";
        JexlScript s42 = jexl.createScript(strs);
        Object result = s42.execute(null);
        Assert.assertEquals(42, result);
        strs = "var t = 19; var s = function(x, y) { var t = 20; x + y + t}; s(15, 7)";
        s42 = jexl.createScript(strs);
        result = s42.execute(null);
        Assert.assertEquals(42, result);
        strs = "var t = 20; var s = function(x, y) {x + y + t}; t = 54; s(15, 7)";
        s42 = jexl.createScript(strs);
        result = s42.execute(null);
        Assert.assertEquals(42, result);
        strs = "var t = 19; var s = function(x, y) { var t = 20; x + y + t}; t = 54; s(15, 7)";
        s42 = jexl.createScript(strs);
        result = s42.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testLambdaLambda() throws Exception {
        final JexlEngine jexl = createEngine();
        String strs = "var t = 19; ( (x, y)->{ var t = 20; x + y + t} )(15, 7);";
        JexlScript s42 = jexl.createScript(strs);
        Object result = s42.execute(null);
        Assert.assertEquals(42, result);

        strs = "( (x, y)->{ ( (xx, yy)->{xx + yy } )(x, y) } )(15, 27)";
        s42 = jexl.createScript(strs);
        result = s42.execute(null);
        Assert.assertEquals(42, result);

        strs = "var t = 19; var s = (x, y)->{ var t = 20; x + y + t}; t = 54; s(15, 7)";
        s42 = jexl.createScript(strs);
        result = s42.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testNestLambda() throws Exception {
        final JexlEngine jexl = createEngine();
        final String strs = "( (x)->{ (y)->{ x + y } })(15)(27)";
        final JexlScript s42 = jexl.createScript(strs);
        final Object result = s42.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testNestLambada() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlContext ctx = null;
        final String strs = "(x)->{ (y)->{ x + y } }";
        final JexlScript s42 = jexl.createScript(strs);
        final JexlScript s42b = jexl.createScript(s42.toString());
        Assert.assertEquals(s42.hashCode(), s42b.hashCode());
        Assert.assertEquals(s42, s42b);
        Object result = s42.execute(ctx, 15);
        Assert.assertTrue(result instanceof JexlScript);
        final Object resultb = s42.execute(ctx, 15);
        Assert.assertEquals(result.hashCode(), resultb.hashCode());
        Assert.assertEquals(result, resultb);
        Assert.assertEquals(result, jexl.createScript(resultb.toString(), "x").execute(ctx, 15));
        final JexlScript s15 = (JexlScript) result;
        final Callable<Object> s15b = s15.callable(ctx, 27);
        result = s15.execute(ctx, 27);
        Assert.assertEquals(42, result);
        result = s15b.call();
        Assert.assertEquals(42, result);
    }

    @Test
    public void testHoistLambda() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlEvalContext ctx = new JexlEvalContext();
        ctx.getEngineOptions().setLexical(false);
        JexlScript s42;
        Object result;
        JexlScript s15;
        String[] localv;
        Set<List<String>> hvars;
        String strs;

        // hosted variables are NOT local variables
        strs = "(x)->{ (y)->{ x + y } }";
        s42 = jexl.createScript(strs);
        result = s42.execute(ctx, 15);
        Assert.assertTrue(result instanceof JexlScript);
        s15 = (JexlScript) result;
        localv = s15.getLocalVariables();
        Assert.assertEquals(0, localv.length);
        hvars = s15.getVariables();
        Assert.assertEquals(1, hvars.size());

        // declaring a local that overrides captured
        // in 3.1, such a local was considered local
        // per 3.2, this local is considered captured
        strs = "(x)->{ (y)->{ var z = 169; var x; x + y } }";
        s42 = jexl.createScript(strs);
        result = s42.execute(ctx, 15);
        Assert.assertTrue(result instanceof JexlScript);
        s15 = (JexlScript) result;
        localv = s15.getLocalVariables();
        Assert.assertNotNull(localv);
        Assert.assertEquals(1, localv.length);
        hvars = s15.getVariables();
        Assert.assertEquals(1, hvars.size());
        // evidence this is not (strictly) a local since it inherited a captured value
        result = s15.execute(ctx, 27);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testRecurse() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlContext jc = new MapContext();
        try {
            final JexlScript script = jexl.createScript("var fact = (x)->{ if (x <= 1) 1; else x * fact(x - 1) }; fact(5)");
            final int result = (Integer) script.execute(jc);
            Assert.assertEquals(120, result);
        } catch (final JexlException xany) {
            final String msg = xany.toString();
            throw xany;
        }
    }

    @Test
    public void testRecurse2() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlContext jc = new MapContext();
        // adding some captured vars to get it confused
        try {
            final JexlScript script = jexl.createScript(
                    "var y = 1; var z = 1; "
                    +"var fact = (x)->{ if (x <= y) z; else x * fact(x - 1) }; fact(6)");
            final int result = (Integer) script.execute(jc);
            Assert.assertEquals(720, result);
        } catch (final JexlException xany) {
            final String msg = xany.toString();
            throw xany;
        }
    }

    @Test
    public void testRecurse3() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlContext jc = new MapContext();
        // adding some captured vars to get it confused
        try {
            final JexlScript script = jexl.createScript(
                    "var y = 1; var z = 1;var foo = (x)->{y + z}; "
                    +"var fact = (x)->{ if (x <= y) z; else x * fact(x - 1) }; fact(6)");
            final int result = (Integer) script.execute(jc);
            Assert.assertEquals(720, result);
        } catch (final JexlException xany) {
            final String msg = xany.toString();
            throw xany;
        }
    }

    @Test
    public void testIdentity() throws Exception {
        final JexlEngine jexl = createEngine();
        JexlScript script;
        Object result;

        script = jexl.createScript("(x)->{ x }");
        Assert.assertArrayEquals(new String[]{"x"}, script.getParameters());
        result = script.execute(null, 42);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testCurry1() throws Exception {
        final JexlEngine jexl = createEngine();
        JexlScript script;
        Object result;
        String[] parms;

        final JexlScript base = jexl.createScript("(x, y, z)->{ x + y + z }");
        parms = base.getUnboundParameters();
        Assert.assertEquals(3, parms.length);
        script = base.curry(5);
        parms = script.getUnboundParameters();
        Assert.assertEquals(2, parms.length);
        script = script.curry(15);
        parms = script.getUnboundParameters();
        Assert.assertEquals(1, parms.length);
        script = script.curry(22);
        parms = script.getUnboundParameters();
        Assert.assertEquals(0, parms.length);
        result = script.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testCurry2() throws Exception {
        final JexlEngine jexl = createEngine();
        JexlScript script;
        Object result;
        String[] parms;

        final JexlScript base = jexl.createScript("(x, y, z)->{ x + y + z }");
        script = base.curry(5, 15);
        parms = script.getUnboundParameters();
        Assert.assertEquals(1, parms.length);
        script = script.curry(22);
        result = script.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testCurry3() throws Exception {
        final JexlEngine jexl = createEngine();
        JexlScript script;
        Object result;

        final JexlScript base = jexl.createScript("(x, y, z)->{ x + y + z }");
        script = base.curry(5, 15);
        result = script.execute(null, 22);
        Assert.assertEquals(42, result);
    }

    @Test
    public void test270() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript base = jexl.createScript("(x, y, z)->{ x + y + z }");
        final String text = base.toString();
        JexlScript script = base.curry(5, 15);
        Assert.assertEquals(text, script.toString());

        final JexlEvalContext ctxt = new JexlEvalContext();
        ctxt.set("s", base);
        script = jexl.createScript("return s");
        Object result = script.execute(ctxt);
        Assert.assertEquals(text, result.toString());

        script = jexl.createScript("return s.curry(1)");
        result = script.execute(ctxt);
        Assert.assertEquals(text, result.toString());
    }

    @Test
    public void test271a() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript base = jexl.createScript("var base = 1; var x = (a)->{ var y = (b) -> {base + b}; return base + y(a)}; x(40)");
        final Object result = base.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void test271b() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript base = jexl.createScript("var base = 2; var sum = (x, y, z)->{ base + x + y + z }; var y = sum.curry(1); y(2,3)");
        final Object result = base.execute(null);
        Assert.assertEquals(8, result);
    }

    @Test
    public void test271c() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript base = jexl.createScript("(x, y, z)->{ 2 + x + y + z };");
        final JexlScript y = base.curry(1);
        final Object result = y.execute(null, 2, 3);
        Assert.assertEquals(8, result);
    }

    @Test
    public void test271d() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript base = jexl.createScript("var base = 2; return (x, y, z)->{ base + x + y + z };");
        final JexlScript y = ((JexlScript) base.execute(null)).curry(1);
        final Object result = y.execute(null, 2, 3);
        Assert.assertEquals(8, result);
    }

    // redefining an captured var is not resolved correctly in left hand side;
    // declare the var in local frame, resolved in local frame instead of parent
//    @Test
//    public void test271e() throws Exception {
//        JexlEngine jexl = createEngine();
//        JexlScript base = jexl.createScript("var base = 1000; var f = (x, y)->{ var base = x + y + (base?:-1000); base; }; f(100, 20)");
//        Object result = base.execute(null);
//        Assert.assertEquals(-880, result);
//    }

    @Test
    public void testScriptArguments_1_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript s = jexl.createScript(" x + x ", "x");
        final JexlScript s42 = jexl.createScript("s(21)", "s");
        final Object result = s42.execute(null, s);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testScriptContext_1_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript s = jexl.createScript("function(x) { x + x }");
        final String fsstr = s.getParsedText(0);
        Assert.assertEquals("(x)->{ x + x; }", fsstr);
    }

    @Test
    public void testScriptContext_2_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript s = jexl.createScript("function(x) { x + x }");
        final String fsstr = s.getParsedText(0);
        // removed other assertion
        Assert.assertEquals(42, s.execute(null, 21));
    }

    @Test
    public void testScriptContext_3_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript s = jexl.createScript("function(x) { x + x }");
        final String fsstr = s.getParsedText(0);
        // removed other assertion
        // removed other assertion
        JexlScript s42 = jexl.createScript("s(21)");
        final JexlEvalContext ctxt = new JexlEvalContext();
        ctxt.set("s", s);
        Object result = s42.execute(ctxt);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testScriptContext_4_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript s = jexl.createScript("function(x) { x + x }");
        final String fsstr = s.getParsedText(0);
        // removed other assertion
        // removed other assertion
        JexlScript s42 = jexl.createScript("s(21)");
        final JexlEvalContext ctxt = new JexlEvalContext();
        ctxt.set("s", s);
        Object result = s42.execute(ctxt);
        // removed other assertion
        result = s42.execute(ctxt);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testScriptContext_5_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript s = jexl.createScript("function(x) { x + x }");
        final String fsstr = s.getParsedText(0);
        // removed other assertion
        // removed other assertion
        JexlScript s42 = jexl.createScript("s(21)");
        final JexlEvalContext ctxt = new JexlEvalContext();
        ctxt.set("s", s);
        Object result = s42.execute(ctxt);
        // removed other assertion
        result = s42.execute(ctxt);
        // removed other assertion
        s42 = jexl.createScript("x-> { x + x }");
        result = s42.execute(ctxt, 21);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testLambda_1_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        String strs = "var s = function(x) { x + x }; s(21)";
        JexlScript s42 = jexl.createScript(strs);
        Object result = s42.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testLambda_2_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        String strs = "var s = function(x) { x + x }; s(21)";
        JexlScript s42 = jexl.createScript(strs);
        Object result = s42.execute(null);
        // removed other assertion
        strs = "var s = function(x, y) { x + y }; s(15, 27)";
        s42 = jexl.createScript(strs);
        result = s42.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testLambdaClosure_1_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        String strs = "var t = 20; var s = function(x, y) { x + y + t}; s(15, 7)";
        JexlScript s42 = jexl.createScript(strs);
        Object result = s42.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testLambdaClosure_2_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        String strs = "var t = 20; var s = function(x, y) { x + y + t}; s(15, 7)";
        JexlScript s42 = jexl.createScript(strs);
        Object result = s42.execute(null);
        // removed other assertion
        strs = "var t = 19; var s = function(x, y) { var t = 20; x + y + t}; s(15, 7)";
        s42 = jexl.createScript(strs);
        result = s42.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testLambdaClosure_3_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        String strs = "var t = 20; var s = function(x, y) { x + y + t}; s(15, 7)";
        JexlScript s42 = jexl.createScript(strs);
        Object result = s42.execute(null);
        // removed other assertion
        strs = "var t = 19; var s = function(x, y) { var t = 20; x + y + t}; s(15, 7)";
        s42 = jexl.createScript(strs);
        result = s42.execute(null);
        // removed other assertion
        strs = "var t = 20; var s = function(x, y) {x + y + t}; t = 54; s(15, 7)";
        s42 = jexl.createScript(strs);
        result = s42.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testLambdaClosure_4_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        String strs = "var t = 20; var s = function(x, y) { x + y + t}; s(15, 7)";
        JexlScript s42 = jexl.createScript(strs);
        Object result = s42.execute(null);
        // removed other assertion
        strs = "var t = 19; var s = function(x, y) { var t = 20; x + y + t}; s(15, 7)";
        s42 = jexl.createScript(strs);
        result = s42.execute(null);
        // removed other assertion
        strs = "var t = 20; var s = function(x, y) {x + y + t}; t = 54; s(15, 7)";
        s42 = jexl.createScript(strs);
        result = s42.execute(null);
        // removed other assertion
        strs = "var t = 19; var s = function(x, y) { var t = 20; x + y + t}; t = 54; s(15, 7)";
        s42 = jexl.createScript(strs);
        result = s42.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testLambdaLambda_1_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        String strs = "var t = 19; ( (x, y)->{ var t = 20; x + y + t} )(15, 7);";
        JexlScript s42 = jexl.createScript(strs);
        Object result = s42.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testLambdaLambda_2_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        String strs = "var t = 19; ( (x, y)->{ var t = 20; x + y + t} )(15, 7);";
        JexlScript s42 = jexl.createScript(strs);
        Object result = s42.execute(null);
        // removed other assertion

        strs = "( (x, y)->{ ( (xx, yy)->{xx + yy } )(x, y) } )(15, 27)";
        s42 = jexl.createScript(strs);
        result = s42.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testLambdaLambda_3_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        String strs = "var t = 19; ( (x, y)->{ var t = 20; x + y + t} )(15, 7);";
        JexlScript s42 = jexl.createScript(strs);
        Object result = s42.execute(null);
        // removed other assertion

        strs = "( (x, y)->{ ( (xx, yy)->{xx + yy } )(x, y) } )(15, 27)";
        s42 = jexl.createScript(strs);
        result = s42.execute(null);
        // removed other assertion

        strs = "var t = 19; var s = (x, y)->{ var t = 20; x + y + t}; t = 54; s(15, 7)";
        s42 = jexl.createScript(strs);
        result = s42.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testNestLambda_1_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final String strs = "( (x)->{ (y)->{ x + y } })(15)(27)";
        final JexlScript s42 = jexl.createScript(strs);
        final Object result = s42.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testNestLambada_1_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlContext ctx = null;
        final String strs = "(x)->{ (y)->{ x + y } }";
        final JexlScript s42 = jexl.createScript(strs);
        final JexlScript s42b = jexl.createScript(s42.toString());
        Assert.assertEquals(s42.hashCode(), s42b.hashCode());
    }

    @Test
    public void testNestLambada_2_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlContext ctx = null;
        final String strs = "(x)->{ (y)->{ x + y } }";
        final JexlScript s42 = jexl.createScript(strs);
        final JexlScript s42b = jexl.createScript(s42.toString());
        // removed other assertion
        Assert.assertEquals(s42, s42b);
    }

    @Test
    public void testNestLambada_3_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlContext ctx = null;
        final String strs = "(x)->{ (y)->{ x + y } }";
        final JexlScript s42 = jexl.createScript(strs);
        final JexlScript s42b = jexl.createScript(s42.toString());
        // removed other assertion
        // removed other assertion
        Object result = s42.execute(ctx, 15);
        Assert.assertTrue(result instanceof JexlScript);
    }

    @Test
    public void testNestLambada_4_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlContext ctx = null;
        final String strs = "(x)->{ (y)->{ x + y } }";
        final JexlScript s42 = jexl.createScript(strs);
        final JexlScript s42b = jexl.createScript(s42.toString());
        // removed other assertion
        // removed other assertion
        Object result = s42.execute(ctx, 15);
        // removed other assertion
        final Object resultb = s42.execute(ctx, 15);
        Assert.assertEquals(result.hashCode(), resultb.hashCode());
    }

    @Test
    public void testNestLambada_5_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlContext ctx = null;
        final String strs = "(x)->{ (y)->{ x + y } }";
        final JexlScript s42 = jexl.createScript(strs);
        final JexlScript s42b = jexl.createScript(s42.toString());
        // removed other assertion
        // removed other assertion
        Object result = s42.execute(ctx, 15);
        // removed other assertion
        final Object resultb = s42.execute(ctx, 15);
        // removed other assertion
        Assert.assertEquals(result, resultb);
    }

    @Test
    public void testNestLambada_6_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlContext ctx = null;
        final String strs = "(x)->{ (y)->{ x + y } }";
        final JexlScript s42 = jexl.createScript(strs);
        final JexlScript s42b = jexl.createScript(s42.toString());
        // removed other assertion
        // removed other assertion
        Object result = s42.execute(ctx, 15);
        // removed other assertion
        final Object resultb = s42.execute(ctx, 15);
        // removed other assertion
        // removed other assertion
        Assert.assertEquals(result, jexl.createScript(resultb.toString(), "x").execute(ctx, 15));
    }

    @Test
    public void testNestLambada_7_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlContext ctx = null;
        final String strs = "(x)->{ (y)->{ x + y } }";
        final JexlScript s42 = jexl.createScript(strs);
        final JexlScript s42b = jexl.createScript(s42.toString());
        // removed other assertion
        // removed other assertion
        Object result = s42.execute(ctx, 15);
        // removed other assertion
        final Object resultb = s42.execute(ctx, 15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final JexlScript s15 = (JexlScript) result;
        final Callable<Object> s15b = s15.callable(ctx, 27);
        result = s15.execute(ctx, 27);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testNestLambada_8_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlContext ctx = null;
        final String strs = "(x)->{ (y)->{ x + y } }";
        final JexlScript s42 = jexl.createScript(strs);
        final JexlScript s42b = jexl.createScript(s42.toString());
        // removed other assertion
        // removed other assertion
        Object result = s42.execute(ctx, 15);
        // removed other assertion
        final Object resultb = s42.execute(ctx, 15);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final JexlScript s15 = (JexlScript) result;
        final Callable<Object> s15b = s15.callable(ctx, 27);
        result = s15.execute(ctx, 27);
        // removed other assertion
        result = s15b.call();
        Assert.assertEquals(42, result);
    }

    @Test
    public void testHoistLambda_1_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlEvalContext ctx = new JexlEvalContext();
        ctx.getEngineOptions().setLexical(false);
        JexlScript s42;
        Object result;
        JexlScript s15;
        String[] localv;
        Set<List<String>> hvars;
        String strs;

        // hosted variables are NOT local variables
        strs = "(x)->{ (y)->{ x + y } }";
        s42 = jexl.createScript(strs);
        result = s42.execute(ctx, 15);
        Assert.assertTrue(result instanceof JexlScript);
    }

    @Test
    public void testHoistLambda_2_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlEvalContext ctx = new JexlEvalContext();
        ctx.getEngineOptions().setLexical(false);
        JexlScript s42;
        Object result;
        JexlScript s15;
        String[] localv;
        Set<List<String>> hvars;
        String strs;

        // hosted variables are NOT local variables
        strs = "(x)->{ (y)->{ x + y } }";
        s42 = jexl.createScript(strs);
        result = s42.execute(ctx, 15);
        // removed other assertion
        s15 = (JexlScript) result;
        localv = s15.getLocalVariables();
        Assert.assertEquals(0, localv.length);
    }

    @Test
    public void testHoistLambda_3_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlEvalContext ctx = new JexlEvalContext();
        ctx.getEngineOptions().setLexical(false);
        JexlScript s42;
        Object result;
        JexlScript s15;
        String[] localv;
        Set<List<String>> hvars;
        String strs;

        // hosted variables are NOT local variables
        strs = "(x)->{ (y)->{ x + y } }";
        s42 = jexl.createScript(strs);
        result = s42.execute(ctx, 15);
        // removed other assertion
        s15 = (JexlScript) result;
        localv = s15.getLocalVariables();
        // removed other assertion
        hvars = s15.getVariables();
        Assert.assertEquals(1, hvars.size());
    }

    @Test
    public void testHoistLambda_4_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlEvalContext ctx = new JexlEvalContext();
        ctx.getEngineOptions().setLexical(false);
        JexlScript s42;
        Object result;
        JexlScript s15;
        String[] localv;
        Set<List<String>> hvars;
        String strs;

        // hosted variables are NOT local variables
        strs = "(x)->{ (y)->{ x + y } }";
        s42 = jexl.createScript(strs);
        result = s42.execute(ctx, 15);
        // removed other assertion
        s15 = (JexlScript) result;
        localv = s15.getLocalVariables();
        // removed other assertion
        hvars = s15.getVariables();
        // removed other assertion

        // declaring a local that overrides captured
        // in 3.1, such a local was considered local
        // per 3.2, this local is considered captured
        strs = "(x)->{ (y)->{ var z = 169; var x; x + y } }";
        s42 = jexl.createScript(strs);
        result = s42.execute(ctx, 15);
        Assert.assertTrue(result instanceof JexlScript);
    }

    @Test
    public void testHoistLambda_5_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlEvalContext ctx = new JexlEvalContext();
        ctx.getEngineOptions().setLexical(false);
        JexlScript s42;
        Object result;
        JexlScript s15;
        String[] localv;
        Set<List<String>> hvars;
        String strs;

        // hosted variables are NOT local variables
        strs = "(x)->{ (y)->{ x + y } }";
        s42 = jexl.createScript(strs);
        result = s42.execute(ctx, 15);
        // removed other assertion
        s15 = (JexlScript) result;
        localv = s15.getLocalVariables();
        // removed other assertion
        hvars = s15.getVariables();
        // removed other assertion

        // declaring a local that overrides captured
        // in 3.1, such a local was considered local
        // per 3.2, this local is considered captured
        strs = "(x)->{ (y)->{ var z = 169; var x; x + y } }";
        s42 = jexl.createScript(strs);
        result = s42.execute(ctx, 15);
        // removed other assertion
        s15 = (JexlScript) result;
        localv = s15.getLocalVariables();
        Assert.assertNotNull(localv);
    }

    @Test
    public void testHoistLambda_6_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlEvalContext ctx = new JexlEvalContext();
        ctx.getEngineOptions().setLexical(false);
        JexlScript s42;
        Object result;
        JexlScript s15;
        String[] localv;
        Set<List<String>> hvars;
        String strs;

        // hosted variables are NOT local variables
        strs = "(x)->{ (y)->{ x + y } }";
        s42 = jexl.createScript(strs);
        result = s42.execute(ctx, 15);
        // removed other assertion
        s15 = (JexlScript) result;
        localv = s15.getLocalVariables();
        // removed other assertion
        hvars = s15.getVariables();
        // removed other assertion

        // declaring a local that overrides captured
        // in 3.1, such a local was considered local
        // per 3.2, this local is considered captured
        strs = "(x)->{ (y)->{ var z = 169; var x; x + y } }";
        s42 = jexl.createScript(strs);
        result = s42.execute(ctx, 15);
        // removed other assertion
        s15 = (JexlScript) result;
        localv = s15.getLocalVariables();
        // removed other assertion
        Assert.assertEquals(1, localv.length);
    }

    @Test
    public void testHoistLambda_7_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlEvalContext ctx = new JexlEvalContext();
        ctx.getEngineOptions().setLexical(false);
        JexlScript s42;
        Object result;
        JexlScript s15;
        String[] localv;
        Set<List<String>> hvars;
        String strs;

        // hosted variables are NOT local variables
        strs = "(x)->{ (y)->{ x + y } }";
        s42 = jexl.createScript(strs);
        result = s42.execute(ctx, 15);
        // removed other assertion
        s15 = (JexlScript) result;
        localv = s15.getLocalVariables();
        // removed other assertion
        hvars = s15.getVariables();
        // removed other assertion

        // declaring a local that overrides captured
        // in 3.1, such a local was considered local
        // per 3.2, this local is considered captured
        strs = "(x)->{ (y)->{ var z = 169; var x; x + y } }";
        s42 = jexl.createScript(strs);
        result = s42.execute(ctx, 15);
        // removed other assertion
        s15 = (JexlScript) result;
        localv = s15.getLocalVariables();
        // removed other assertion
        // removed other assertion
        hvars = s15.getVariables();
        Assert.assertEquals(1, hvars.size());
    }

    @Test
    public void testHoistLambda_8_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlEvalContext ctx = new JexlEvalContext();
        ctx.getEngineOptions().setLexical(false);
        JexlScript s42;
        Object result;
        JexlScript s15;
        String[] localv;
        Set<List<String>> hvars;
        String strs;

        // hosted variables are NOT local variables
        strs = "(x)->{ (y)->{ x + y } }";
        s42 = jexl.createScript(strs);
        result = s42.execute(ctx, 15);
        // removed other assertion
        s15 = (JexlScript) result;
        localv = s15.getLocalVariables();
        // removed other assertion
        hvars = s15.getVariables();
        // removed other assertion

        // declaring a local that overrides captured
        // in 3.1, such a local was considered local
        // per 3.2, this local is considered captured
        strs = "(x)->{ (y)->{ var z = 169; var x; x + y } }";
        s42 = jexl.createScript(strs);
        result = s42.execute(ctx, 15);
        // removed other assertion
        s15 = (JexlScript) result;
        localv = s15.getLocalVariables();
        // removed other assertion
        // removed other assertion
        hvars = s15.getVariables();
        // removed other assertion
        // evidence this is not (strictly) a local since it inherited a captured value
        result = s15.execute(ctx, 27);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testIdentity_1_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        JexlScript script;
        Object result;

        script = jexl.createScript("(x)->{ x }");
        Assert.assertArrayEquals(new String[]{"x"}, script.getParameters());
    }

    @Test
    public void testIdentity_2_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        JexlScript script;
        Object result;

        script = jexl.createScript("(x)->{ x }");
        // removed other assertion
        result = script.execute(null, 42);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testCurry1_1_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        JexlScript script;
        Object result;
        String[] parms;

        final JexlScript base = jexl.createScript("(x, y, z)->{ x + y + z }");
        parms = base.getUnboundParameters();
        Assert.assertEquals(3, parms.length);
    }

    @Test
    public void testCurry1_2_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        JexlScript script;
        Object result;
        String[] parms;

        final JexlScript base = jexl.createScript("(x, y, z)->{ x + y + z }");
        parms = base.getUnboundParameters();
        // removed other assertion
        script = base.curry(5);
        parms = script.getUnboundParameters();
        Assert.assertEquals(2, parms.length);
    }

    @Test
    public void testCurry1_3_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        JexlScript script;
        Object result;
        String[] parms;

        final JexlScript base = jexl.createScript("(x, y, z)->{ x + y + z }");
        parms = base.getUnboundParameters();
        // removed other assertion
        script = base.curry(5);
        parms = script.getUnboundParameters();
        // removed other assertion
        script = script.curry(15);
        parms = script.getUnboundParameters();
        Assert.assertEquals(1, parms.length);
    }

    @Test
    public void testCurry1_4_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        JexlScript script;
        Object result;
        String[] parms;

        final JexlScript base = jexl.createScript("(x, y, z)->{ x + y + z }");
        parms = base.getUnboundParameters();
        // removed other assertion
        script = base.curry(5);
        parms = script.getUnboundParameters();
        // removed other assertion
        script = script.curry(15);
        parms = script.getUnboundParameters();
        // removed other assertion
        script = script.curry(22);
        parms = script.getUnboundParameters();
        Assert.assertEquals(0, parms.length);
    }

    @Test
    public void testCurry1_5_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        JexlScript script;
        Object result;
        String[] parms;

        final JexlScript base = jexl.createScript("(x, y, z)->{ x + y + z }");
        parms = base.getUnboundParameters();
        // removed other assertion
        script = base.curry(5);
        parms = script.getUnboundParameters();
        // removed other assertion
        script = script.curry(15);
        parms = script.getUnboundParameters();
        // removed other assertion
        script = script.curry(22);
        parms = script.getUnboundParameters();
        // removed other assertion
        result = script.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testCurry2_1_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        JexlScript script;
        Object result;
        String[] parms;

        final JexlScript base = jexl.createScript("(x, y, z)->{ x + y + z }");
        script = base.curry(5, 15);
        parms = script.getUnboundParameters();
        Assert.assertEquals(1, parms.length);
    }

    @Test
    public void testCurry2_2_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        JexlScript script;
        Object result;
        String[] parms;

        final JexlScript base = jexl.createScript("(x, y, z)->{ x + y + z }");
        script = base.curry(5, 15);
        parms = script.getUnboundParameters();
        // removed other assertion
        script = script.curry(22);
        result = script.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void testCurry3_1_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        JexlScript script;
        Object result;

        final JexlScript base = jexl.createScript("(x, y, z)->{ x + y + z }");
        script = base.curry(5, 15);
        result = script.execute(null, 22);
        Assert.assertEquals(42, result);
    }

    @Test
    public void test270_1_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript base = jexl.createScript("(x, y, z)->{ x + y + z }");
        final String text = base.toString();
        JexlScript script = base.curry(5, 15);
        Assert.assertEquals(text, script.toString());
    }

    @Test
    public void test270_2_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript base = jexl.createScript("(x, y, z)->{ x + y + z }");
        final String text = base.toString();
        JexlScript script = base.curry(5, 15);
        // removed other assertion

        final JexlEvalContext ctxt = new JexlEvalContext();
        ctxt.set("s", base);
        script = jexl.createScript("return s");
        Object result = script.execute(ctxt);
        Assert.assertEquals(text, result.toString());
    }

    @Test
    public void test270_3_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript base = jexl.createScript("(x, y, z)->{ x + y + z }");
        final String text = base.toString();
        JexlScript script = base.curry(5, 15);
        // removed other assertion

        final JexlEvalContext ctxt = new JexlEvalContext();
        ctxt.set("s", base);
        script = jexl.createScript("return s");
        Object result = script.execute(ctxt);
        // removed other assertion

        script = jexl.createScript("return s.curry(1)");
        result = script.execute(ctxt);
        Assert.assertEquals(text, result.toString());
    }

    @Test
    public void test271a_1_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript base = jexl.createScript("var base = 1; var x = (a)->{ var y = (b) -> {base + b}; return base + y(a)}; x(40)");
        final Object result = base.execute(null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void test271b_1_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript base = jexl.createScript("var base = 2; var sum = (x, y, z)->{ base + x + y + z }; var y = sum.curry(1); y(2,3)");
        final Object result = base.execute(null);
        Assert.assertEquals(8, result);
    }

    @Test
    public void test271c_1_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript base = jexl.createScript("(x, y, z)->{ 2 + x + y + z };");
        final JexlScript y = base.curry(1);
        final Object result = y.execute(null, 2, 3);
        Assert.assertEquals(8, result);
    }

    @Test
    public void test271d_1_oe() throws Exception {
        final JexlEngine jexl = createEngine();
        final JexlScript base = jexl.createScript("var base = 2; return (x, y, z)->{ base + x + y + z };");
        final JexlScript y = ((JexlScript) base.execute(null)).curry(1);
        final Object result = y.execute(null, 2, 3);
        Assert.assertEquals(8, result);
    }

}
