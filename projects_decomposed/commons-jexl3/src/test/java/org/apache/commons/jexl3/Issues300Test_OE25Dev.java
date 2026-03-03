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

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Test cases for reported issue between JEXL-300 and JEXL-399.
 */
public class Issues300Test_OE25Dev {

    @Test
    public void testIssues301b() throws Exception {
        final JexlEngine jexl = new JexlBuilder().safe(false).arithmetic(new JexlArithmetic(false)).create();
        final Object[] xs = new Object[]{null, null, new Object[]{null, 1}};
        final String[] srcs = new String[]{
                "x.0", "x[0]", "x[0][0]"
        };
        final JexlContext ctxt = new MapContext();
        for (int i = 0; i < xs.length; ++i) {
            ctxt.set("x", xs[i]);
            final String src = srcs[i];
            final JexlScript s = jexl.createScript(src);
            try {
                final Object o = s.execute(null);
                Assert.fail(src + ": Should have failed");
            } catch (final Exception ex) {
                //
            }
        }
    }

    public static class VaContext extends MapContext {
        VaContext(final Map<String, Object> vars) {
            super(vars);
        }

        public int cell(final String... ms) {
            return ms.length;
        }

        public int cell(final List<?> l, final String... ms) {
            return 42 + cell(ms);
        }
    }

    @Test
    public void test322a() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        final JxltEngine jxlt = jexl.createJxltEngine();
        final JexlContext context = new MapContext();

        final String[] ins = new String[]{
                "${'{'}", "${\"{\"}", "${\"{}\"}", "${'{42}'}", "${\"{\\\"\\\"}\"}"
        };
        final String[] ctls = new String[]{
                "{", "{", "{}", "{42}", "{\"\"}"
        };
        StringWriter strw;
        JxltEngine.Template template;
        String output;

        for (int i = 0; i < ins.length; ++i) {
            final String src = ins[i];
            try {
                template = jxlt.createTemplate("$$", new StringReader(src));
            } catch (final JexlException xany) {
                Assert.fail(src);
                throw xany;
            }
            strw = new StringWriter();
            template.evaluate(context, strw);
            output = strw.toString();
            Assert.assertEquals(ctls[i], output);
        }
    }

    public static class User322 {
        public String getName() {
            return "user322";
        }
    }

    public static class Session322 {
        public User322 getUser() {
            return new User322();
        }
    }

    @Test public void test349() throws Exception {
        String text = "(A ? C.D : E)";
        JexlEngine jexl = new JexlBuilder().safe(true).create();
        JexlExpression expr = jexl.createExpression(text);
        JexlScript script = jexl.createScript(text);
    }

    @Test
    public void testIssue301a_2_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().safe(false).arithmetic(new JexlArithmetic(false)).create();
        final String[] srcs = new String[]{
                "var x = null; x.0", "var x = null; x[0]", "var x = [null,1]; x[0][0]"
        };
        for (int i = 0; i < srcs.length; ++i) {
            final String src = srcs[i];
            final JexlScript s = jexl.createScript(src);
            try {
                final Object o = s.execute(null);
                if (i > 0) {
                }
            } catch (final Exception ex) {
                Assert.assertTrue(ex.getMessage().contains("x"));
    }
    }
    }

    @Test
    public void testIssue302_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final String[] strs = new String[]{
                "{if (0) 1 else 2; var x = 4;}",
                "if (0) 1; else 2; ",
                "{ if (0) 1; else 2; }",
                "{ if (0) { if (false) 1 else -3 } else 2; }"
        };
        final JexlEngine jexl = new JexlBuilder().create();
        for (final String str : strs) {
            final JexlScript e = jexl.createScript(str);
            final Object o = e.execute(jc);
            final int oo = ((Number) o).intValue() % 2;
            Assert.assertEquals("Block result is wrong " + str, 0, oo);
    }
    }

    @Test
    public void testIssue304_1_oe() {
        final JexlEngine jexlEngine = new JexlBuilder().strict(false).create();
        JexlExpression e304 = jexlEngine.createExpression("overview.limit.var");

        final HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("var", "4711");
        final HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("limit", map3);
        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("overview", map2);

        final JexlContext context = new MapContext(map);
        Object value = e304.evaluate(context);
        assertEquals("4711",value);// fails map.clear();
    }

    @Test
    public void testIssue304_3_oe() {
        final JexlEngine jexlEngine = new JexlBuilder().strict(false).create();
        JexlExpression e304 = jexlEngine.createExpression("overview.limit.var");

        final HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("var", "4711");
        final HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("limit", map3);
        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("overview", map2);

        final JexlContext context = new MapContext(map);
        Object value = e304.evaluate(context);
        map.put("overview.limit.var", 42);
        value = e304.evaluate(context);

        final String allkw = "e304.if.else.do.while.new.true.false.null.var.function.empty.size.not.and.or.ne.eq.le.lt.gt.ge";
        map.put(allkw, 42);
        e304 = jexlEngine.createExpression(allkw);
        value = e304.evaluate(context);
        assertEquals(42, value);
    }

    @Test
    public void testIssue305_1_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        JexlScript e;
        e = jexl.createScript("{; var x = 1;}");
        final String str0 = e.getParsedText();
        e = jexl.createScript(str0);
        Assert.assertNotNull(e);
    }

    @Test
    public void testIssue305_2_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        JexlScript e;
        e = jexl.createScript("{; var x = 1;}");
        final String str0 = e.getParsedText();
        e = jexl.createScript(str0);
        final String str1 = e.getParsedText();
        Assert.assertEquals(str0, str1);
    }

    @Test
    public void testIssue306_1_oe() throws Exception {
        final JexlContext ctxt = new MapContext();
        final JexlEngine jexl = new JexlBuilder().create();
        final JexlScript e = jexl.createScript("x.y ?: 2");
        final Object o1 = e.execute(null);
        Assert.assertEquals(2, o1);
    }

    @Test
    public void testIssue306_2_oe() throws Exception {
        final JexlContext ctxt = new MapContext();
        final JexlEngine jexl = new JexlBuilder().create();
        final JexlScript e = jexl.createScript("x.y ?: 2");
        final Object o1 = e.execute(null);
        ctxt.set("x.y", null);
        final Object o2 = e.execute(ctxt);
        Assert.assertEquals(2, o2);
    }

    @Test
    public void testIssue306a_1_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        final JexlScript e = jexl.createScript("x.y ?: 2", "x");
        Object o = e.execute(null, new Object());
        Assert.assertEquals(2, o);
    }

    @Test
    public void testIssue306a_2_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        final JexlScript e = jexl.createScript("x.y ?: 2", "x");
        Object o = e.execute(null, new Object());
        o = e.execute(null);
        Assert.assertEquals(2, o);
    }

    @Test
    public void testIssue306b_1_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        final JexlScript e = jexl.createScript("x?.y ?: 2", "x");
        final Object o1 = e.execute(null, new Object());
        Assert.assertEquals(2, o1);
    }

    @Test
    public void testIssue306b_2_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        final JexlScript e = jexl.createScript("x?.y ?: 2", "x");
        final Object o1 = e.execute(null, new Object());
        final Object o2 = e.execute(null);
        Assert.assertEquals(2, o2);
    }

    @Test
    public void testIssue306c_1_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().safe(true).create();
        final JexlScript e = jexl.createScript("x.y ?: 2", "x");
        Object o = e.execute(null, new Object());
        Assert.assertEquals(2, o);
    }

    @Test
    public void testIssue306c_2_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().safe(true).create();
        final JexlScript e = jexl.createScript("x.y ?: 2", "x");
        Object o = e.execute(null, new Object());
        o = e.execute(null);
        Assert.assertEquals(2, o);
    }

    @Test
    public void testIssue306d_1_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().safe(true).create();
        final JexlScript e = jexl.createScript("x.y[z.t] ?: 2", "x");
        Object o = e.execute(null, new Object());
        Assert.assertEquals(2, o);
    }

    @Test
    public void testIssue306d_2_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().safe(true).create();
        final JexlScript e = jexl.createScript("x.y[z.t] ?: 2", "x");
        Object o = e.execute(null, new Object());
        o = e.execute(null);
        Assert.assertEquals(2, o);
    }

    @Test
    public void testIssue309a_2_oe() throws Exception {
        final String src = "<html lang=\"en\">\n"
                + "  <body>\n"
                + "    <h1>Hello World!</h1>\n"
                + "$$ var i = 12++;\n"
                + "  </body>\n"
                + "</html>";
        final JexlEngine jexl = new JexlBuilder().safe(true).create();
        final JxltEngine jxlt = jexl.createJxltEngine();
        final JexlInfo info = new JexlInfo("template", 1, 1);
        try {
            final JxltEngine.Template tmplt = jxlt.createTemplate(info, src);
        } catch (final JexlException.Parsing xerror) {
            Assert.assertEquals(4, xerror.getInfo().getLine());
    }
    }

    @Test
    public void testIssue309b_2_oe() throws Exception {
        final String src = "<html lang=\"en\">\n"
                + "  <body>\n"
                + "    <h1>Hello World!</h1>\n"
                + "$$ var i = a b c;\n"
                + "  </body>\n"
                + "</html>";
        final JexlEngine jexl = new JexlBuilder().safe(true).create();
        final JxltEngine jxlt = jexl.createJxltEngine();
        final JexlInfo info = new JexlInfo("template", 1, 1);
        try {
            final JxltEngine.Template tmplt = jxlt.createTemplate(info, src);
        } catch (final JexlException.Parsing xerror) {
            Assert.assertEquals(4, xerror.getInfo().getLine());
    }
    }

    @Test
    public void testIssue309c_2_oe() throws Exception {
        final String src = "<html lang=\"en\">\n"
                + "  <body>\n"
                + "    <h1>Hello World!</h1>\n"
                + "$$ var i =12;\n"
                + "  </body>\n"
                + "</html>";
        final JexlEngine jexl = new JexlBuilder().safe(true).create();
        final JxltEngine jxlt = jexl.createJxltEngine();
        final JexlInfo info = new JexlInfo("template", 1, 1);
        try {
            final JxltEngine.Template tmplt = jxlt.createTemplate(info, src);
            final String src1 = tmplt.asString();
            final String src2 = tmplt.toString();
        } catch (final JexlException.Parsing xerror) {
            Assert.assertEquals(4, xerror.getInfo().getLine());
    }
    }

    @Test
    public void test314_1_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).safe(false).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext ctxt = new VaContext(vars);
        JexlScript script;
        Object result;
        script = jexl.createScript("cell()");
        result = script.execute(ctxt);
        Assert.assertEquals(0, result);
    }

    @Test
    public void test314_2_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).safe(false).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext ctxt = new VaContext(vars);
        JexlScript script;
        Object result;
        script = jexl.createScript("cell()");
        result = script.execute(ctxt);
        script = jexl.createScript("x.cell()", "x");
        result = script.execute(ctxt, Arrays.asList(10, 20));
        Assert.assertEquals(42, result);
    }

    @Test
    public void test314_3_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).safe(false).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext ctxt = new VaContext(vars);
        JexlScript script;
        Object result;
        script = jexl.createScript("cell()");
        result = script.execute(ctxt);
        script = jexl.createScript("x.cell()", "x");
        result = script.execute(ctxt, Arrays.asList(10, 20));
        script = jexl.createScript("cell('1', '2')");
        result = script.execute(ctxt);
        Assert.assertEquals(2, result);
    }

    @Test
    public void test314_4_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).safe(false).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext ctxt = new VaContext(vars);
        JexlScript script;
        Object result;
        script = jexl.createScript("cell()");
        result = script.execute(ctxt);
        script = jexl.createScript("x.cell()", "x");
        result = script.execute(ctxt, Arrays.asList(10, 20));
        script = jexl.createScript("cell('1', '2')");
        result = script.execute(ctxt);
        script = jexl.createScript("x.cell('1', '2')", "x");
        result = script.execute(ctxt, Arrays.asList(10, 20));
        Assert.assertEquals(44, result);
    }

    @Test
    public void test314_5_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).safe(false).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext ctxt = new VaContext(vars);
        JexlScript script;
        Object result;
        script = jexl.createScript("cell()");
        result = script.execute(ctxt);
        script = jexl.createScript("x.cell()", "x");
        result = script.execute(ctxt, Arrays.asList(10, 20));
        script = jexl.createScript("cell('1', '2')");
        result = script.execute(ctxt);
        script = jexl.createScript("x.cell('1', '2')", "x");
        result = script.execute(ctxt, Arrays.asList(10, 20));

        vars.put("TVALOGAR", null);
        String jexlExp = "TVALOGAR==null?'SIMON':'SIMONAZO'";
        script = jexl.createScript(jexlExp);
        result = script.execute(ctxt);
        Assert.assertEquals("SIMON", result);
    }

    @Test
    public void test314_6_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).safe(false).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext ctxt = new VaContext(vars);
        JexlScript script;
        Object result;
        script = jexl.createScript("cell()");
        result = script.execute(ctxt);
        script = jexl.createScript("x.cell()", "x");
        result = script.execute(ctxt, Arrays.asList(10, 20));
        script = jexl.createScript("cell('1', '2')");
        result = script.execute(ctxt);
        script = jexl.createScript("x.cell('1', '2')", "x");
        result = script.execute(ctxt, Arrays.asList(10, 20));

        vars.put("TVALOGAR", null);
        String jexlExp = "TVALOGAR==null?'SIMON':'SIMONAZO'";
        script = jexl.createScript(jexlExp);
        result = script.execute(ctxt);

        jexlExp = "TVALOGAR.PEPITO==null?'SIMON':'SIMONAZO'";
        script = jexl.createScript(jexlExp);

        final Map<String, Object> tva = new LinkedHashMap<String, Object>();
        tva.put("PEPITO", null);
        vars.put("TVALOGAR", tva);
        result = script.execute(ctxt);
        Assert.assertEquals("SIMON", result);
    }

    @Test
    public void test314_7_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).safe(false).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext ctxt = new VaContext(vars);
        JexlScript script;
        Object result;
        script = jexl.createScript("cell()");
        result = script.execute(ctxt);
        script = jexl.createScript("x.cell()", "x");
        result = script.execute(ctxt, Arrays.asList(10, 20));
        script = jexl.createScript("cell('1', '2')");
        result = script.execute(ctxt);
        script = jexl.createScript("x.cell('1', '2')", "x");
        result = script.execute(ctxt, Arrays.asList(10, 20));

        vars.put("TVALOGAR", null);
        String jexlExp = "TVALOGAR==null?'SIMON':'SIMONAZO'";
        script = jexl.createScript(jexlExp);
        result = script.execute(ctxt);

        jexlExp = "TVALOGAR.PEPITO==null?'SIMON':'SIMONAZO'";
        script = jexl.createScript(jexlExp);

        final Map<String, Object> tva = new LinkedHashMap<String, Object>();
        tva.put("PEPITO", null);
        vars.put("TVALOGAR", tva);
        result = script.execute(ctxt);

        vars.remove("TVALOGAR");
        ctxt.set("TVALOGAR.PEPITO", null);
        result = script.execute(ctxt);
        Assert.assertEquals("SIMON", result);
    }

    @Test
    public void test315_1_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext ctxt = new VaContext(vars);
        JexlScript script;
        Object result;
        script = jexl.createScript("a?? 42 + 10", "a");
        result = script.execute(ctxt, 32);
        Assert.assertEquals(32, result);
    }

    @Test
    public void test315_2_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext ctxt = new VaContext(vars);
        JexlScript script;
        Object result;
        script = jexl.createScript("a?? 42 + 10", "a");
        result = script.execute(ctxt, 32);
        result = script.execute(ctxt, (Object) null);
        Assert.assertEquals(52, result);
    }

    @Test
    public void test315_3_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext ctxt = new VaContext(vars);
        JexlScript script;
        Object result;
        script = jexl.createScript("a?? 42 + 10", "a");
        result = script.execute(ctxt, 32);
        result = script.execute(ctxt, (Object) null);
        script = jexl.createScript("- a??42 + +10", "a");
        result = script.execute(ctxt, 32);
        Assert.assertEquals(-32, result);
    }

    @Test
    public void test315_4_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext ctxt = new VaContext(vars);
        JexlScript script;
        Object result;
        script = jexl.createScript("a?? 42 + 10", "a");
        result = script.execute(ctxt, 32);
        result = script.execute(ctxt, (Object) null);
        script = jexl.createScript("- a??42 + +10", "a");
        result = script.execute(ctxt, 32);
        result = script.execute(ctxt, (Object) null);
        Assert.assertEquals(52, result);
    }

    @Test
    public void test315_5_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext ctxt = new VaContext(vars);
        JexlScript script;
        Object result;
        script = jexl.createScript("a?? 42 + 10", "a");
        result = script.execute(ctxt, 32);
        result = script.execute(ctxt, (Object) null);
        script = jexl.createScript("- a??42 + +10", "a");
        result = script.execute(ctxt, 32);
        result = script.execute(ctxt, (Object) null);
        script = jexl.createScript("a? a : +42 + 10", "a");
        result = script.execute(ctxt, 32);
        Assert.assertEquals(32, result);
    }

    @Test
    public void test315_6_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext ctxt = new VaContext(vars);
        JexlScript script;
        Object result;
        script = jexl.createScript("a?? 42 + 10", "a");
        result = script.execute(ctxt, 32);
        result = script.execute(ctxt, (Object) null);
        script = jexl.createScript("- a??42 + +10", "a");
        result = script.execute(ctxt, 32);
        result = script.execute(ctxt, (Object) null);
        script = jexl.createScript("a? a : +42 + 10", "a");
        result = script.execute(ctxt, 32);
        result = script.execute(ctxt, (Object) null);
        Assert.assertEquals(52, result);
    }

    @Test
    public void test315_7_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext ctxt = new VaContext(vars);
        JexlScript script;
        Object result;
        script = jexl.createScript("a?? 42 + 10", "a");
        result = script.execute(ctxt, 32);
        result = script.execute(ctxt, (Object) null);
        script = jexl.createScript("- a??42 + +10", "a");
        result = script.execute(ctxt, 32);
        result = script.execute(ctxt, (Object) null);
        script = jexl.createScript("a? a : +42 + 10", "a");
        result = script.execute(ctxt, 32);
        result = script.execute(ctxt, (Object) null);
        script = jexl.createScript("a ?: +42 + 10", "a");
        result = script.execute(ctxt, 32);
        Assert.assertEquals(32, result);
    }

    @Test
    public void test315_8_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext ctxt = new VaContext(vars);
        JexlScript script;
        Object result;
        script = jexl.createScript("a?? 42 + 10", "a");
        result = script.execute(ctxt, 32);
        result = script.execute(ctxt, (Object) null);
        script = jexl.createScript("- a??42 + +10", "a");
        result = script.execute(ctxt, 32);
        result = script.execute(ctxt, (Object) null);
        script = jexl.createScript("a? a : +42 + 10", "a");
        result = script.execute(ctxt, 32);
        result = script.execute(ctxt, (Object) null);
        script = jexl.createScript("a ?: +42 + 10", "a");
        result = script.execute(ctxt, 32);
        result = script.execute(ctxt, (Object) null);
        Assert.assertEquals(52, result);
    }

    @Test
    public void test317_1_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        final JexlContext ctxt = new MapContext();
        JexlScript script;
        Object result;
        JexlInfo info = new JexlInfo("test317", 1, 1);
        script = jexl.createScript(info, "var f = "
                        + "()-> {x + x }; f",
                "x");
        result = script.execute(ctxt, 21);
        Assert.assertTrue(result instanceof JexlScript);
    }

    @Test
    public void test317_2_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        final JexlContext ctxt = new MapContext();
        JexlScript script;
        Object result;
        JexlInfo info = new JexlInfo("test317", 1, 1);
        script = jexl.createScript(info, "var f = "
                        + "()-> {x + x }; f",
                "x");
        result = script.execute(ctxt, 21);
        script = (JexlScript) result;
        info = JexlInfo.from(script);
        Assert.assertNotNull(info);
    }

    @Test
    public void test317_3_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        final JexlContext ctxt = new MapContext();
        JexlScript script;
        Object result;
        JexlInfo info = new JexlInfo("test317", 1, 1);
        script = jexl.createScript(info, "var f = "
                        + "()-> {x + x }; f",
                "x");
        result = script.execute(ctxt, 21);
        script = (JexlScript) result;
        info = JexlInfo.from(script);
        Assert.assertEquals("test317", info.getName());
    }

    @Test
    public void test317_4_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        final JexlContext ctxt = new MapContext();
        JexlScript script;
        Object result;
        JexlInfo info = new JexlInfo("test317", 1, 1);
        script = jexl.createScript(info, "var f = "
                        + "()-> {x + x }; f",
                "x");
        result = script.execute(ctxt, 21);
        script = (JexlScript) result;
        info = JexlInfo.from(script);
        result = script.execute(ctxt, 21);
        Assert.assertEquals(42, result);
    }

    @Test
    public void test322b_1_oe() throws Exception {
        final MapContext ctxt = new MapContext();
        final String src = "L'utilisateur ${session.user.name} s'est connecte";
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        final JxltEngine jxlt = jexl.createJxltEngine();
        StringWriter strw;
        JxltEngine.Template template;
        String output;
        template = jxlt.createTemplate("$$", new StringReader(src));

        ctxt.set("session", new Session322());
        strw = new StringWriter();
        template.evaluate(ctxt, strw);
        output = strw.toString();
        Assert.assertEquals("L'utilisateur user322 s'est connecte", output);
    }

    @Test
    public void test322b_2_oe() throws Exception {
        final MapContext ctxt = new MapContext();
        final String src = "L'utilisateur ${session.user.name} s'est connecte";
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        final JxltEngine jxlt = jexl.createJxltEngine();
        StringWriter strw;
        JxltEngine.Template template;
        String output;
        template = jxlt.createTemplate("$$", new StringReader(src));

        ctxt.set("session", new Session322());
        strw = new StringWriter();
        template.evaluate(ctxt, strw);
        output = strw.toString();

        ctxt.set("session.user", new User322());
        strw = new StringWriter();
        template.evaluate(ctxt, strw);
        output = strw.toString();
        Assert.assertEquals("L'utilisateur user322 s'est connecte", output);
    }

    @Test
    public void test322b_3_oe() throws Exception {
        final MapContext ctxt = new MapContext();
        final String src = "L'utilisateur ${session.user.name} s'est connecte";
        final JexlEngine jexl = new JexlBuilder().strict(true).create();
        final JxltEngine jxlt = jexl.createJxltEngine();
        StringWriter strw;
        JxltEngine.Template template;
        String output;
        template = jxlt.createTemplate("$$", new StringReader(src));

        ctxt.set("session", new Session322());
        strw = new StringWriter();
        template.evaluate(ctxt, strw);
        output = strw.toString();

        ctxt.set("session.user", new User322());
        strw = new StringWriter();
        template.evaluate(ctxt, strw);
        output = strw.toString();

        ctxt.set("session.user.name", "user322");
        strw = new StringWriter();
        template.evaluate(ctxt, strw);
        output = strw.toString();
        Assert.assertEquals("L'utilisateur user322 s'est connecte", output);
    }

    @Test
    public void test323_2_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().safe(false).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext jc = new MapContext(vars);
        JexlScript script;
        Object result;

        try {
            script = jexl.createScript("a.n.t.variable");
            result = script.execute(jc);
        } catch (final JexlException.Variable xvar) {
            Assert.assertTrue(xvar.toString().contains("a.n.t"));
    }
    }

    @Test
    public void test323_3_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().safe(false).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext jc = new MapContext(vars);
        JexlScript script;
        Object result;

        try {
            script = jexl.createScript("a.n.t.variable");
            result = script.execute(jc);
        } catch (final JexlException.Variable xvar) {
        }

        jc.set("a.n.t.variable", null);
        script = jexl.createScript("a.n.t.variable");
        result = script.execute(jc);
        Assert.assertNull(result);
    }

    @Test
    public void test323_5_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().safe(false).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext jc = new MapContext(vars);
        JexlScript script;
        Object result;

        try {
            script = jexl.createScript("a.n.t.variable");
            result = script.execute(jc);
        } catch (final JexlException.Variable xvar) {
        }

        jc.set("a.n.t.variable", null);
        script = jexl.createScript("a.n.t.variable");
        result = script.execute(jc);

        jc.set("a.n.t", null);
        try {
            script = jexl.createScript("a.n.t[0].variable");
            result = script.execute(jc);
        } catch (final JexlException.Variable xvar) {
            Assert.assertTrue(xvar.toString().contains("a.n.t"));
    }
    }

    @Test
    public void test323_7_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().safe(false).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext jc = new MapContext(vars);
        JexlScript script;
        Object result;

        try {
            script = jexl.createScript("a.n.t.variable");
            result = script.execute(jc);
        } catch (final JexlException.Variable xvar) {
        }

        jc.set("a.n.t.variable", null);
        script = jexl.createScript("a.n.t.variable");
        result = script.execute(jc);

        jc.set("a.n.t", null);
        try {
            script = jexl.createScript("a.n.t[0].variable");
            result = script.execute(jc);
        } catch (final JexlException.Variable xvar) {
        }

        vars.remove("a.n.t");
        try {
            script = jexl.createScript("a.n.t[0].variable");
            result = script.execute(jc);
        } catch (final JexlException.Variable xvar) {
            Assert.assertTrue(xvar.toString().contains("a.n.t"));
    }
    }

    @Test
    public void test323_9_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().safe(false).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext jc = new MapContext(vars);
        JexlScript script;
        Object result;

        try {
            script = jexl.createScript("a.n.t.variable");
            result = script.execute(jc);
        } catch (final JexlException.Variable xvar) {
        }

        jc.set("a.n.t.variable", null);
        script = jexl.createScript("a.n.t.variable");
        result = script.execute(jc);

        jc.set("a.n.t", null);
        try {
            script = jexl.createScript("a.n.t[0].variable");
            result = script.execute(jc);
        } catch (final JexlException.Variable xvar) {
        }

        vars.remove("a.n.t");
        try {
            script = jexl.createScript("a.n.t[0].variable");
            result = script.execute(jc);
        } catch (final JexlException.Variable xvar) {
        }
        final List<Object> inner = new ArrayList<Object>();
        vars.put("a.n.t", inner);
        try {
            script = jexl.createScript("a.n.t[0].variable");
            result = script.execute(jc);
        } catch (final JexlException.Property xprop) {
            Assert.assertTrue(xprop.toString().contains("0"));
    }
    }

    @Test
    public void test323_11_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().safe(false).create();
        final Map<String, Object> vars = new HashMap<String, Object>();
        final JexlContext jc = new MapContext(vars);
        JexlScript script;
        Object result;

        try {
            script = jexl.createScript("a.n.t.variable");
            result = script.execute(jc);
        } catch (final JexlException.Variable xvar) {
        }

        jc.set("a.n.t.variable", null);
        script = jexl.createScript("a.n.t.variable");
        result = script.execute(jc);

        jc.set("a.n.t", null);
        try {
            script = jexl.createScript("a.n.t[0].variable");
            result = script.execute(jc);
        } catch (final JexlException.Variable xvar) {
        }

        vars.remove("a.n.t");
        try {
            script = jexl.createScript("a.n.t[0].variable");
            result = script.execute(jc);
        } catch (final JexlException.Variable xvar) {
        }
        final List<Object> inner = new ArrayList<Object>();
        vars.put("a.n.t", inner);
        try {
            script = jexl.createScript("a.n.t[0].variable");
            result = script.execute(jc);
        } catch (final JexlException.Property xprop) {
        }
        inner.add(42);
        try {
            script = jexl.createScript("a.n.t[0].variable");
            result = script.execute(jc);
        } catch (final JexlException.Property xprop) {
            Assert.assertTrue(xprop.toString().contains("variable"));
    }
    }

    @Test
    public void test324_1_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        final String src42 = "new('java.lang.Integer', 42)";
        final JexlExpression expr0 = jexl.createExpression(src42);
        Assert.assertEquals(42, expr0.evaluate(null));
    }

    @Test
    public void test324_2_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        final String src42 = "new('java.lang.Integer', 42)";
        final JexlExpression expr0 = jexl.createExpression(src42);
        final String parsed = expr0.getParsedText();
        Assert.assertEquals(src42, parsed);
    }

    @Test
    public void test324_4_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        final String src42 = "new('java.lang.Integer', 42)";
        final JexlExpression expr0 = jexl.createExpression(src42);
        final String parsed = expr0.getParsedText();
        try {
            final JexlExpression expr = jexl.createExpression("new()");
        } catch (final JexlException.Parsing xparse) {
            Assert.assertTrue(xparse.toString().contains(")"));
    }
    }

    @Test
    public void test325_1_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().safe(false).create();
        final Map<String, Object> map = new HashMap<String, Object>() {
            @Override
            public Object get(final Object key) {
                return super.get(key == null ? "" : key);
            }

            @Override
            public Object put(final String key, final Object value) {
                return super.put(key == null ? "" : key, value);
            }
        };
        map.put("42", 42);
        final JexlContext jc = new MapContext();
        JexlScript script;
        Object result;

        script = jexl.createScript("map[null] = 42", "map");
        result = script.execute(jc, map);
        Assert.assertEquals(42, result);
    }

    @Test
    public void test325_2_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().safe(false).create();
        final Map<String, Object> map = new HashMap<String, Object>() {
            @Override
            public Object get(final Object key) {
                return super.get(key == null ? "" : key);
            }

            @Override
            public Object put(final String key, final Object value) {
                return super.put(key == null ? "" : key, value);
            }
        };
        map.put("42", 42);
        final JexlContext jc = new MapContext();
        JexlScript script;
        Object result;

        script = jexl.createScript("map[null] = 42", "map");
        result = script.execute(jc, map);
        script = jexl.createScript("map[key]", "map", "key");
        result = script.execute(jc, map, null);
        Assert.assertEquals(42, result);
    }

    @Test
    public void test325_3_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().safe(false).create();
        final Map<String, Object> map = new HashMap<String, Object>() {
            @Override
            public Object get(final Object key) {
                return super.get(key == null ? "" : key);
            }

            @Override
            public Object put(final String key, final Object value) {
                return super.put(key == null ? "" : key, value);
            }
        };
        map.put("42", 42);
        final JexlContext jc = new MapContext();
        JexlScript script;
        Object result;

        script = jexl.createScript("map[null] = 42", "map");
        result = script.execute(jc, map);
        script = jexl.createScript("map[key]", "map", "key");
        result = script.execute(jc, map, null);
        result = script.execute(jc, map, "42");
        Assert.assertEquals(42, result);
    }

    @Test
    public void test330_2_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        final String longExpression = ""
                + //
                "'THIS IS A VERY VERY VERY VERY VERY VERY VERY "
                + //
                "VERY VERY LONG STRING CONCATENATION ' + VARIABLE ' <--- "
                + //
                "error: missing + between VARIABLE and literal'";
        try {
            jexl.createExpression(longExpression);
        } catch (final JexlException.Parsing exception) {
            Assert.assertTrue(exception.getMessage().contains("VARIABLE"));
    }
    }

    @Test
    public void test331_1_oe() throws Exception {
        final JexlEngine jexl = new JexlBuilder().create();
        final JexlContext ctxt = new MapContext();
        JexlScript script;
        Object result;
        script = jexl.createScript("a + '\\n' + b", "a", "b");
        result = script.execute(ctxt, "hello", "world");
        Assert.assertTrue(result.toString().contains("\n"));
    }

    @Test
    public void test347_1_oe() throws Exception {
        final String src = "A.B == 5";
        JexlEngine jexl = new JexlBuilder().safe(true).create();
        JexlScript script = jexl.createScript(src);
        Object result = script.execute(null);
        Assert.assertFalse((Boolean) result);
    }

    @Test
    public void test347_3_oe() throws Exception {
        final String src = "A.B == 5";
        JexlEngine jexl = new JexlBuilder().safe(true).create();
        JexlScript script = jexl.createScript(src);
        Object result = script.execute(null);

        jexl = new JexlBuilder().strict(true).safe(false).create();
        JexlContext ctxt = new MapContext();
        script = jexl.createScript(src);
        try {
            result = script.execute(ctxt);
        } catch (JexlException xany) {
            Assert.assertNotNull(xany);
    }
    }

    @Test
    public void test347_5_oe() throws Exception {
        final String src = "A.B == 5";
        JexlEngine jexl = new JexlBuilder().safe(true).create();
        JexlScript script = jexl.createScript(src);
        Object result = script.execute(null);

        jexl = new JexlBuilder().strict(true).safe(false).create();
        JexlContext ctxt = new MapContext();
        script = jexl.createScript(src);
        try {
            result = script.execute(ctxt);
        } catch (JexlException xany) {
        }
        ctxt.set("A", null);
        try {
            result = script.execute(ctxt);
        } catch (JexlException xany) {
            Assert.assertNotNull(xany);
    }
    }

    @Test
    public void test347_6_oe() throws Exception {
        final String src = "A.B == 5";
        JexlEngine jexl = new JexlBuilder().safe(true).create();
        JexlScript script = jexl.createScript(src);
        Object result = script.execute(null);

        jexl = new JexlBuilder().strict(true).safe(false).create();
        JexlContext ctxt = new MapContext();
        script = jexl.createScript(src);
        try {
            result = script.execute(ctxt);
        } catch (JexlException xany) {
        }
        ctxt.set("A", null);
        try {
            result = script.execute(ctxt);
        } catch (JexlException xany) {
        }
        ctxt.set("A.B", null);
        result = script.execute(ctxt);
        Assert.assertFalse((Boolean) result);
    }

}
