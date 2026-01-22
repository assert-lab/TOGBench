/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.commons.jexl3.scripting;

import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import org.junit.Assert;
import org.junit.Test;

public class JexlScriptEngineTest_OE25Dev {
    private static final List<String> NAMES = Arrays.asList("JEXL", "Jexl", "jexl",
                                                            "JEXL2", "Jexl2", "jexl2",
                                                            "JEXL3", "Jexl3", "jexl3");
    private static final List<String> EXTENSIONS = Arrays.asList("jexl", "jexl2", "jexl3");
    private static final List<String> MIMES = Arrays.asList("application/x-jexl",
                                                            "application/x-jexl2",
                                                            "application/x-jexl3");

    @Test
    public void testScriptEngineFactory_1_oe() throws Exception {
        final JexlScriptEngineFactory factory = new JexlScriptEngineFactory();
        Assert.assertEquals("JEXL Engine", factory.getParameter(ScriptEngine.ENGINE));
    }

    @Test
    public void testScriptEngineFactory_2_oe() throws Exception {
        final JexlScriptEngineFactory factory = new JexlScriptEngineFactory();
        // removed other assertion
        Assert.assertEquals("3.2", factory.getParameter(ScriptEngine.ENGINE_VERSION));
    }

    @Test
    public void testScriptEngineFactory_3_oe() throws Exception {
        final JexlScriptEngineFactory factory = new JexlScriptEngineFactory();
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("JEXL", factory.getParameter(ScriptEngine.LANGUAGE));
    }

    @Test
    public void testScriptEngineFactory_4_oe() throws Exception {
        final JexlScriptEngineFactory factory = new JexlScriptEngineFactory();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("3.2", factory.getParameter(ScriptEngine.LANGUAGE_VERSION));
    }

    @Test
    public void testScriptEngineFactory_5_oe() throws Exception {
        final JexlScriptEngineFactory factory = new JexlScriptEngineFactory();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertNull(factory.getParameter("THREADING"));
    }

    @Test
    public void testScriptEngineFactory_6_oe() throws Exception {
        final JexlScriptEngineFactory factory = new JexlScriptEngineFactory();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals(NAMES, factory.getParameter(ScriptEngine.NAME));
    }

    @Test
    public void testScriptEngineFactory_7_oe() throws Exception {
        final JexlScriptEngineFactory factory = new JexlScriptEngineFactory();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals(EXTENSIONS, factory.getExtensions());
    }

    @Test
    public void testScriptEngineFactory_8_oe() throws Exception {
        final JexlScriptEngineFactory factory = new JexlScriptEngineFactory();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals(MIMES, factory.getMimeTypes());
    }

    @Test
    public void testScriptEngineFactory_9_oe() throws Exception {
        final JexlScriptEngineFactory factory = new JexlScriptEngineFactory();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assert.assertEquals("42;", factory.getProgram("42"));
    }

    @Test
    public void testScriptEngineFactory_10_oe() throws Exception {
        final JexlScriptEngineFactory factory = new JexlScriptEngineFactory();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assert.assertEquals("str.substring(3,4)", factory.getMethodCallSyntax("str", "substring", "3", "4"));
    }

    @Test
    public void testScriptingGetBy_1_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        Assert.assertNotNull("Manager should not be null", manager);
    }

    @Test
    public void testScriptingGetBy_2_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        for (final String name : NAMES) {
            final ScriptEngine engine = manager.getEngineByName(name);
            Assert.assertNotNull("Engine should not be null (name)", engine);
    }
    }

    @Test
    public void testScriptingGetBy_3_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        for (final String name : NAMES) {
            final ScriptEngine engine = manager.getEngineByName(name);
            // removed other assertion
        }
        for (final String extension : EXTENSIONS) {
            final ScriptEngine engine = manager.getEngineByExtension(extension);
            Assert.assertNotNull("Engine should not be null (extension)", engine);
    }
    }

    @Test
    public void testScriptingGetBy_4_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        for (final String name : NAMES) {
            final ScriptEngine engine = manager.getEngineByName(name);
            // removed other assertion
        }
        for (final String extension : EXTENSIONS) {
            final ScriptEngine engine = manager.getEngineByExtension(extension);
            // removed other assertion
        }
        for (final String mime : MIMES) {
            final ScriptEngine engine = manager.getEngineByMimeType(mime);
            Assert.assertNotNull("Engine should not be null (mime)", engine);
    }
    }

    @Test
    public void testScripting_1_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        Assert.assertNotNull("Manager should not be null", manager);
    }

    @Test
    public void testScripting_2_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("jexl3");
        final Integer initialValue = 123;
        Assert.assertEquals(initialValue,engine.eval("123"));
    }

    @Test
    public void testScripting_3_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("jexl3");
        final Integer initialValue = 123;
        // removed other assertion
        Assert.assertEquals(initialValue,engine.eval("0;123"));// multiple statements;
    }

    @Test
    public void testScripting_4_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("jexl3");
        final Integer initialValue = 123;
        // removed other assertion
        // removed other assertion
        final long time1 = System.currentTimeMillis();
        final Long time2 = (Long) engine.eval(
             "sys=context.class.forName(\"java.lang.System\");"
            +"now=sys.currentTimeMillis();"
            );
        Assert.assertTrue("Must take some time to process this",time1 <= time2);
    }

    @Test
    public void testScripting_5_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("jexl3");
        final Integer initialValue = 123;
        // removed other assertion
        // removed other assertion
        final long time1 = System.currentTimeMillis();
        final Long time2 = (Long) engine.eval(
             "sys=context.class.forName(\"java.lang.System\");"
            +"now=sys.currentTimeMillis();"
            );
        // removed other assertion
        engine.put("value", initialValue);
        Assert.assertEquals(initialValue,engine.get("value"));
    }

    @Test
    public void testScripting_6_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("jexl3");
        final Integer initialValue = 123;
        // removed other assertion
        // removed other assertion
        final long time1 = System.currentTimeMillis();
        final Long time2 = (Long) engine.eval(
             "sys=context.class.forName(\"java.lang.System\");"
            +"now=sys.currentTimeMillis();"
            );
        // removed other assertion
        engine.put("value", initialValue);
        // removed other assertion
        final Integer newValue = 124;
        Assert.assertEquals(newValue,engine.eval("old=value;value=value+1"));
    }

    @Test
    public void testScripting_7_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("jexl3");
        final Integer initialValue = 123;
        // removed other assertion
        // removed other assertion
        final long time1 = System.currentTimeMillis();
        final Long time2 = (Long) engine.eval(
             "sys=context.class.forName(\"java.lang.System\");"
            +"now=sys.currentTimeMillis();"
            );
        // removed other assertion
        engine.put("value", initialValue);
        // removed other assertion
        final Integer newValue = 124;
        // removed other assertion
        Assert.assertEquals(initialValue,engine.get("old"));
    }

    @Test
    public void testScripting_8_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("jexl3");
        final Integer initialValue = 123;
        // removed other assertion
        // removed other assertion
        final long time1 = System.currentTimeMillis();
        final Long time2 = (Long) engine.eval(
             "sys=context.class.forName(\"java.lang.System\");"
            +"now=sys.currentTimeMillis();"
            );
        // removed other assertion
        engine.put("value", initialValue);
        // removed other assertion
        final Integer newValue = 124;
        // removed other assertion
        // removed other assertion
        Assert.assertEquals(newValue,engine.get("value"));
    }

    @Test
    public void testScripting_9_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("jexl3");
        final Integer initialValue = 123;
        // removed other assertion
        // removed other assertion
        final long time1 = System.currentTimeMillis();
        final Long time2 = (Long) engine.eval(
             "sys=context.class.forName(\"java.lang.System\");"
            +"now=sys.currentTimeMillis();"
            );
        // removed other assertion
        engine.put("value", initialValue);
        // removed other assertion
        final Integer newValue = 124;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals(engine.getContext(),engine.get(JexlScriptEngine.CONTEXT_KEY));
    }

    @Test
    public void testScripting_10_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("jexl3");
        final Integer initialValue = 123;
        // removed other assertion
        // removed other assertion
        final long time1 = System.currentTimeMillis();
        final Long time2 = (Long) engine.eval(
             "sys=context.class.forName(\"java.lang.System\");"
            +"now=sys.currentTimeMillis();"
            );
        // removed other assertion
        engine.put("value", initialValue);
        // removed other assertion
        final Integer newValue = 124;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Check behavior of JEXL object
        Assert.assertEquals(engine.getContext().getReader(),engine.eval("JEXL.in"));
    }

    @Test
    public void testScripting_11_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("jexl3");
        final Integer initialValue = 123;
        // removed other assertion
        // removed other assertion
        final long time1 = System.currentTimeMillis();
        final Long time2 = (Long) engine.eval(
             "sys=context.class.forName(\"java.lang.System\");"
            +"now=sys.currentTimeMillis();"
            );
        // removed other assertion
        engine.put("value", initialValue);
        // removed other assertion
        final Integer newValue = 124;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Check behavior of JEXL object
        // removed other assertion
        Assert.assertEquals(engine.getContext().getWriter(),engine.eval("JEXL.out"));
    }

    @Test
    public void testScripting_12_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("jexl3");
        final Integer initialValue = 123;
        // removed other assertion
        // removed other assertion
        final long time1 = System.currentTimeMillis();
        final Long time2 = (Long) engine.eval(
             "sys=context.class.forName(\"java.lang.System\");"
            +"now=sys.currentTimeMillis();"
            );
        // removed other assertion
        engine.put("value", initialValue);
        // removed other assertion
        final Integer newValue = 124;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Check behavior of JEXL object
        // removed other assertion
        // removed other assertion
        Assert.assertEquals(engine.getContext().getErrorWriter(),engine.eval("JEXL.err"));
    }

    @Test
    public void testScripting_13_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("jexl3");
        final Integer initialValue = 123;
        // removed other assertion
        // removed other assertion
        final long time1 = System.currentTimeMillis();
        final Long time2 = (Long) engine.eval(
             "sys=context.class.forName(\"java.lang.System\");"
            +"now=sys.currentTimeMillis();"
            );
        // removed other assertion
        engine.put("value", initialValue);
        // removed other assertion
        final Integer newValue = 124;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Check behavior of JEXL object
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals(System.class,engine.eval("JEXL.System"));
    }

    @Test
    public void testNulls_1_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        Assert.assertNotNull("Manager should not be null", manager);
    }

    @Test
    public void testNulls_2_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("jexl3");
        Assert.assertNotNull("Engine should not be null (name)", engine);
    }

    @Test
    public void testScopes_1_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        Assert.assertNotNull("Manager should not be null", manager);
    }

    @Test
    public void testScopes_2_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("JEXL");
        Assert.assertNotNull("Engine should not be null (JEXL)", engine);
    }

    @Test
    public void testScopes_3_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("JEXL");
        // removed other assertion
        manager.put("global", 1);
        engine.put("local", 10);
        manager.put("both", 7);
        engine.put("both", 7);
        engine.eval("local=local+1");
        engine.eval("global=global+1");
        engine.eval("both=both+1"); // should update engine value only
        engine.eval("newvar=42;");
        Assert.assertEquals(2,manager.get("global"));
    }

    @Test
    public void testScopes_4_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("JEXL");
        // removed other assertion
        manager.put("global", 1);
        engine.put("local", 10);
        manager.put("both", 7);
        engine.put("both", 7);
        engine.eval("local=local+1");
        engine.eval("global=global+1");
        engine.eval("both=both+1"); // should update engine value only
        engine.eval("newvar=42;");
        // removed other assertion
        Assert.assertEquals(11,engine.get("local"));
    }

    @Test
    public void testScopes_5_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("JEXL");
        // removed other assertion
        manager.put("global", 1);
        engine.put("local", 10);
        manager.put("both", 7);
        engine.put("both", 7);
        engine.eval("local=local+1");
        engine.eval("global=global+1");
        engine.eval("both=both+1"); // should update engine value only
        engine.eval("newvar=42;");
        // removed other assertion
        // removed other assertion
        Assert.assertEquals(7,manager.get("both"));
    }

    @Test
    public void testScopes_6_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("JEXL");
        // removed other assertion
        manager.put("global", 1);
        engine.put("local", 10);
        manager.put("both", 7);
        engine.put("both", 7);
        engine.eval("local=local+1");
        engine.eval("global=global+1");
        engine.eval("both=both+1"); // should update engine value only
        engine.eval("newvar=42;");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals(8,engine.get("both"));
    }

    @Test
    public void testScopes_7_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("JEXL");
        // removed other assertion
        manager.put("global", 1);
        engine.put("local", 10);
        manager.put("both", 7);
        engine.put("both", 7);
        engine.eval("local=local+1");
        engine.eval("global=global+1");
        engine.eval("both=both+1"); // should update engine value only
        engine.eval("newvar=42;");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertEquals(42,engine.get("newvar"));
    }

    @Test
    public void testScopes_8_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("JEXL");
        // removed other assertion
        manager.put("global", 1);
        engine.put("local", 10);
        manager.put("both", 7);
        engine.put("both", 7);
        engine.eval("local=local+1");
        engine.eval("global=global+1");
        engine.eval("both=both+1"); // should update engine value only
        engine.eval("newvar=42;");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertNull(manager.get("newvar"));
    }

    @Test
    public void testDottedNames_1_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        Assert.assertNotNull("Manager should not be null", manager);
    }

    @Test
    public void testDottedNames_2_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("JEXL");
        Assert.assertNotNull("Engine should not be null (JEXL)", engine);
    }

    @Test
    public void testDottedNames_3_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("JEXL");
        // removed other assertion
        engine.eval("this.is.a.test=null");
        Assert.assertNull(engine.get("this.is.a.test"));
    }

    @Test
    public void testDottedNames_4_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("JEXL");
        // removed other assertion
        engine.eval("this.is.a.test=null");
        // removed other assertion
        Assert.assertEquals(Boolean.TRUE, engine.eval("empty(this.is.a.test)"));
    }

    @Test
    public void testDottedNames_5_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("JEXL");
        // removed other assertion
        engine.eval("this.is.a.test=null");
        // removed other assertion
        // removed other assertion
        final Object mymap = engine.eval("testmap={ 'key1' : 'value1', 'key2' : 'value2' }");
        Assert.assertTrue(mymap instanceof Map<?, ?>);
    }

    @Test
    public void testDottedNames_6_oe() throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        // removed other assertion
        final ScriptEngine engine = manager.getEngineByName("JEXL");
        // removed other assertion
        engine.eval("this.is.a.test=null");
        // removed other assertion
        // removed other assertion
        final Object mymap = engine.eval("testmap={ 'key1' : 'value1', 'key2' : 'value2' }");
        // removed other assertion
        Assert.assertEquals(2,((Map<?, ?>)mymap).size());
    }

    @Test
    public void testDirectNew_1_oe() throws Exception {
        final ScriptEngine engine = new JexlScriptEngine();
        final Integer initialValue = 123;
        Assert.assertEquals(initialValue,engine.eval("123"));
    }

}
