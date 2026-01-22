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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.StringTokenizer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the foreach statement
 * @since 1.1
 */
@SuppressWarnings({"UnnecessaryBoxing", "AssertEqualsBetweenInconvertibleTypes"})
public class ForEachTest_OE25Dev extends JexlTestCase {

    /** create a named test */
    public ForEachTest_OE25Dev() {
        super("ForEachTest");
    }

    @Test
    public void testForEachWithEmptyStatement_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("for(item : list) ;");
        final JexlContext jc = new MapContext();
        jc.set("list", Collections.emptyList());

        final Object o = e.execute(jc);
        Assert.assertNull("Result is not null", o);
    }

    @Test
    public void testForEachWithEmptyList_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("for(item : list) 1+1");
        final JexlContext jc = new MapContext();
        jc.set("list", Collections.emptyList());

        final Object o = e.execute(jc);
        Assert.assertNull("Result is not null", o);
    }

    @Test
    public void testForEachWithArray_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("for(item : list) item");
        final JexlContext jc = new MapContext();
        jc.set("list", new Object[]{"Hello", "World"});
        final Object o = e.execute(jc);
        Assert.assertEquals("Result is not last evaluated expression", "World", o);
    }

    @Test
    public void testForEachWithCollection_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("for(var item : list) item");
        final JexlContext jc = new MapContext();
        jc.set("list", Arrays.asList("Hello", "World"));
        final Object o = e.execute(jc);
        Assert.assertEquals("Result is not last evaluated expression", "World", o);
    }

    @Test
    public void testForEachWithEnumeration_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("for(var item : list) item");
        final JexlContext jc = new MapContext();
        jc.set("list", new StringTokenizer("Hello,World", ","));
        final Object o = e.execute(jc);
        Assert.assertEquals("Result is not last evaluated expression", "World", o);
    }

    @Test
    public void testForEachWithIterator_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("for(var item : list) item");
        final JexlContext jc = new MapContext();
        jc.set("list", Arrays.asList("Hello", "World").iterator());
        final Object o = e.execute(jc);
        Assert.assertEquals("Result is not last evaluated expression", "World", o);
    }

    @Test
    public void testForEachWithMap_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("for(item : list) item");
        final JexlContext jc = new MapContext();
        final Map<?, ?> map = System.getProperties();
        final String lastProperty = (String) new ArrayList<Object>(map.values()).get(System.getProperties().size() - 1);
        jc.set("list", map);
        final Object o = e.execute(jc);
        Assert.assertEquals("Result is not last evaluated expression", lastProperty, o);
    }

    @Test
    public void testForEachWithBlock_1_oe() throws Exception {
        final JexlScript exs0 = JEXL.createScript("for(var in : list) { x = x + in; }");
        final JexlContext jc = new MapContext();
        jc.set("list", new Object[]{2, 3});
            jc.set("x", new Integer(1));
        final Object o = exs0.execute(jc);
            Assert.assertEquals("Result is wrong", new Integer(6), o);
    }

    @Test
    public void testForEachWithBlock_2_oe() throws Exception {
        final JexlScript exs0 = JEXL.createScript("for(var in : list) { x = x + in; }");
        final JexlContext jc = new MapContext();
        jc.set("list", new Object[]{2, 3});
            jc.set("x", new Integer(1));
        final Object o = exs0.execute(jc);
            // removed other assertion
            Assert.assertEquals("x is wrong", new Integer(6), jc.get("x"));
    }

    @Test
    public void testForEachWithListExpression_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("for(var item : list.keySet()) item");
        final JexlContext jc = new MapContext();
        final Map<?, ?> map = System.getProperties();
        final String lastKey = (String) new ArrayList<Object>(map.keySet()).get(System.getProperties().size() - 1);
        jc.set("list", map);
        final Object o = e.execute(jc);
        Assert.assertEquals("Result is not last evaluated expression", lastKey, o);
    }

    @Test
    public void testForEachWithProperty_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("for(var item : list.cheeseList) item");
        final JexlContext jc = new MapContext();
        jc.set("list", new Foo());
        final Object o = e.execute(jc);
        Assert.assertEquals("Result is not last evaluated expression", "brie", o);
    }

    @Test
    public void testForEachWithIteratorMethod_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("for(var item : list.cheezy) item");
        final JexlContext jc = new MapContext();
        jc.set("list", new Foo());
        final Object o = e.execute(jc);
        Assert.assertEquals("Result is not last evaluated expression", "brie", o);
    }

    @Test
    public void testForEachBreakMethod_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript(
                "var rr = -1; for(var item : [1, 2, 3 ,4 ,5, 6]) { if (item == 3) { rr = item; break; }} rr"
        );
        final JexlContext jc = new MapContext();
        jc.set("list", new Foo());
        final Object o = e.execute(jc);
        Assert.assertEquals("Result is not last evaluated expression", 3, o);
    }

    @Test
    public void testForEachContinueMethod_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript(
                "var rr = 0; for(var item : [1, 2, 3 ,4 ,5, 6]) { if (item <= 3) continue; rr = rr + item;}"
        );
        final JexlContext jc = new MapContext();
        jc.set("list", new Foo());
        final Object o = e.execute(jc);
        Assert.assertEquals("Result is not last evaluated expression", 15, o);
    }

    @Test
    public void testForEachContinueBroken_2_oe() throws Exception {
        try {
            final JexlScript e = JEXL.createScript("var rr = 0; continue;");
            // removed other assertion
        } catch (final JexlException.Parsing xparse) {
            final String str = xparse.detailedMessage();
            Assert.assertTrue(str.contains("continue"));
    }
    }

    @Test
    public void testForEachBreakBroken_2_oe() throws Exception {
        try {
            final JexlScript e = JEXL.createScript("if (true) { break; }");
            // removed other assertion
        } catch (final JexlException.Parsing xparse) {
            final String str = xparse.detailedMessage();
            Assert.assertTrue(str.contains("break"));
    }
    }

}
