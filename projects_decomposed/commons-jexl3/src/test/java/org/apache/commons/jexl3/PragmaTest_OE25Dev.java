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

import java.util.Collections;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for pragmas
 */
public class PragmaTest_OE25Dev extends JexlTestCase {
    /**
     * Create a new test case.
     */
    public PragmaTest_OE25Dev() {
        super("PragmaTest_OE25Dev");
    }

    /**
     * Test creating a script from a string.
     */

    public static class SafeContext extends JexlEvalContext {
        // @Override
        public void processPragmas(final Map<String, Object> pragmas) {
            if (pragmas != null && !pragmas.isEmpty()) {
                final JexlOptions options = getEngineOptions();
                for (final Map.Entry<String, Object> pragma : pragmas.entrySet()) {
                    final String key = pragma.getKey();
                    final Object value = pragma.getValue();
                    if ("jexl.safe".equals(key) && value instanceof Boolean) {
                        options.setSafe((Boolean) value);
                    } else if ("jexl.strict".equals(key) && value instanceof Boolean) {
                        options.setStrict((Boolean) value);
                    } else if ("jexl.silent".equals(key) && value instanceof Boolean) {
                        options.setSilent((Boolean) value);
                    }
                }
            }
        }

        public void sleep(final long ms) {
            try {
                Thread.sleep(ms);
            } catch (final InterruptedException e) {
                // ignore
            }
        }
    }


    public static class StaticSleeper {
        // precludes instantiation
        private StaticSleeper() {}

        public static void sleep(final long ms) {
            try {
                Thread.sleep(ms);
            } catch (final InterruptedException e) {
                // ignore
            }
        }
    }

    public static class Sleeper {
        public void sleep(final long ms) {
            try {
                Thread.sleep(ms);
            } catch (final InterruptedException e) {
                // ignore
            }
        }
    }

    @Test
    @SuppressWarnings("AssertEqualsBetweenInconvertibleTypes")
    public void testPragmas_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final JexlScript script = JEXL.createScript("#pragma one 1\n#pragma the.very.hard 'truth'\n2;");
        Assert.assertNotNull(script);
    }

    @Test
    @SuppressWarnings("AssertEqualsBetweenInconvertibleTypes")
    public void testPragmas_2_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final JexlScript script = JEXL.createScript("#pragma one 1\n#pragma the.very.hard 'truth'\n2;");
        final Map<String, Object> pragmas = script.getPragmas();
        Assert.assertEquals(2, pragmas.size());
    }

    @Test
    @SuppressWarnings("AssertEqualsBetweenInconvertibleTypes")
    public void testPragmas_3_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final JexlScript script = JEXL.createScript("#pragma one 1\n#pragma the.very.hard 'truth'\n2;");
        final Map<String, Object> pragmas = script.getPragmas();
        Assert.assertEquals(1, pragmas.get("one"));
    }

    @Test
    @SuppressWarnings("AssertEqualsBetweenInconvertibleTypes")
    public void testPragmas_4_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final JexlScript script = JEXL.createScript("#pragma one 1\n#pragma the.very.hard 'truth'\n2;");
        final Map<String, Object> pragmas = script.getPragmas();
        Assert.assertEquals("truth", pragmas.get("the.very.hard"));
    }

    @Test
    @SuppressWarnings("AssertEqualsBetweenInconvertibleTypes")
    public void testJxltPragmas_1_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final JxltEngine engine = new JexlBuilder().create().createJxltEngine();
        final JxltEngine.Template tscript = engine.createTemplate("$$ #pragma one 1\n$$ #pragma the.very.hard 'truth'\n2;");
        Assert.assertNotNull(tscript);
    }

    @Test
    @SuppressWarnings("AssertEqualsBetweenInconvertibleTypes")
    public void testJxltPragmas_2_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final JxltEngine engine = new JexlBuilder().create().createJxltEngine();
        final JxltEngine.Template tscript = engine.createTemplate("$$ #pragma one 1\n$$ #pragma the.very.hard 'truth'\n2;");
        final Map<String, Object> pragmas = tscript.getPragmas();
        Assert.assertEquals(2, pragmas.size());
    }

    @Test
    @SuppressWarnings("AssertEqualsBetweenInconvertibleTypes")
    public void testJxltPragmas_3_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final JxltEngine engine = new JexlBuilder().create().createJxltEngine();
        final JxltEngine.Template tscript = engine.createTemplate("$$ #pragma one 1\n$$ #pragma the.very.hard 'truth'\n2;");
        final Map<String, Object> pragmas = tscript.getPragmas();
        Assert.assertEquals(1, pragmas.get("one"));
    }

    @Test
    @SuppressWarnings("AssertEqualsBetweenInconvertibleTypes")
    public void testJxltPragmas_4_oe() throws Exception {
        final JexlContext jc = new MapContext();
        final JxltEngine engine = new JexlBuilder().create().createJxltEngine();
        final JxltEngine.Template tscript = engine.createTemplate("$$ #pragma one 1\n$$ #pragma the.very.hard 'truth'\n2;");
        final Map<String, Object> pragmas = tscript.getPragmas();
        Assert.assertEquals("truth", pragmas.get("the.very.hard"));
    }

    @Test
    @SuppressWarnings("AssertEqualsBetweenInconvertibleTypes")
    public void testSafePragma_1_oe() throws Exception {
        SafeContext jc = new SafeContext();
        jc.set("foo", null);
        final JexlScript script = JEXL.createScript("#pragma jexl.safe true\nfoo.bar;");
        Assert.assertNotNull(script);
    }

    @Test
    @SuppressWarnings("AssertEqualsBetweenInconvertibleTypes")
    public void testSafePragma_2_oe() throws Exception {
        SafeContext jc = new SafeContext();
        jc.set("foo", null);
        final JexlScript script = JEXL.createScript("#pragma jexl.safe true\nfoo.bar;");
        jc.processPragmas(script.getPragmas());
        Object result = script.execute(jc);
        Assert.assertNull(result);
    }

    @Test
    @SuppressWarnings("AssertEqualsBetweenInconvertibleTypes")
    public void testStaticNamespacePragma_1_oe() throws Exception {
        final SafeContext jc = new SafeContext();
        final JexlScript script = JEXL.createScript(
                "#pragma jexl.namespace.sleeper " + StaticSleeper.class.getName() + "\n"
                + "sleeper:sleep(100);"
                + "42");
        final Object result = script.execute(jc);
        Assert.assertEquals(42, result);
    }

    @Test
    @SuppressWarnings("AssertEqualsBetweenInconvertibleTypes")
    public void testStatictNamespacePragmaCtl_1_oe() throws Exception {
        final Map<String, Object> ns = Collections.singletonMap("sleeper", StaticSleeper.class.getName());
        final JexlEngine jexl = new JexlBuilder().namespaces(ns).create();
        final SafeContext jc = new SafeContext();
        final JexlScript script = jexl.createScript(
                "sleeper:sleep(100);"
                + "42");
        final Object result = script.execute(jc);
        Assert.assertEquals(42, result);
    }

    @Test
    @SuppressWarnings("AssertEqualsBetweenInconvertibleTypes")
    public void testNamespacePragma_1_oe() throws Exception {
        final SafeContext jc = new SafeContext();
        final JexlScript script = JEXL.createScript(
                "#pragma jexl.namespace.sleeper " + Sleeper.class.getName() + "\n"
                + "sleeper:sleep(100);"
                + "42");
        final Object result = script.execute(jc);
        Assert.assertEquals(42, result);
    }

    @Test
    @SuppressWarnings("AssertEqualsBetweenInconvertibleTypes")
    public void testNamespacePragmaCtl_1_oe() throws Exception {
        final Map<String, Object> ns = Collections.singletonMap("sleeper", Sleeper.class.getName());
        final JexlEngine jexl = new JexlBuilder().namespaces(ns).create();
        final SafeContext jc = new SafeContext();
        final JexlScript script = jexl.createScript(
                "sleeper:sleep(100);"
                + "42");
        final Object result = script.execute(jc);
        Assert.assertEquals(42, result);
    }

}
