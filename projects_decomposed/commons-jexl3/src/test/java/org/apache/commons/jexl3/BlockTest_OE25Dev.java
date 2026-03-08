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
 * Tests for blocks
 * @since 1.1
 */
@SuppressWarnings({"UnnecessaryBoxing", "AssertEqualsBetweenInconvertibleTypes"})
public class BlockTest_OE25Dev extends JexlTestCase {

    /**
     * Create the test
     */
    public BlockTest_OE25Dev() {
        super("BlockTest_OE25Dev");
    }

    @Test
    public void testBlockSimple_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if (true) { 'hello'; }");
        final JexlContext jc = new MapContext();
        final Object o = e.execute(jc);
        Assert.assertEquals("Result is wrong", "hello", o);
    }

    @Test
    public void testBlockExecutesAll_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if (true) { x = 'Hello'; y = 'World';}");
        final JexlContext jc = new MapContext();
        final Object o = e.execute(jc);
        Assert.assertEquals("First result is wrong", "Hello", jc.get("x"));
    }

    @Test
    public void testBlockExecutesAll_2_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if (true) { x = 'Hello'; y = 'World';}");
        final JexlContext jc = new MapContext();
        final Object o = e.execute(jc);
        // removed other assertion
        Assert.assertEquals("Second result is wrong", "World", jc.get("y"));
    }

    @Test
    public void testBlockExecutesAll_3_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if (true) { x = 'Hello'; y = 'World';}");
        final JexlContext jc = new MapContext();
        final Object o = e.execute(jc);
        // removed other assertion
        // removed other assertion
        Assert.assertEquals("Block result is wrong", "World", o);
    }

    @Test
    public void testEmptyBlock_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("");
        final JexlContext jc = new MapContext();
        final Object o = e.execute(jc);
        Assert.assertNull("Result is wrong", o);
    }

    @Test
    public void testBlockLastExecuted01_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if (true) { x = 1; } else { x = 2; }");
        final JexlContext jc = new MapContext();
        final Object o = e.execute(jc);
        Assert.assertEquals("Block result is wrong", new Integer(1), o);
    }

    @Test
    public void testBlockLastExecuted02_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if (false) { x = 1; } else { x = 2; }");
        final JexlContext jc = new MapContext();
        final Object o = e.execute(jc);
        Assert.assertEquals("Block result is wrong", new Integer(2), o);
    }

    @Test
    public void testNestedBlock_1_oe() throws Exception {
        final JexlScript e = JEXL.createScript("if (true) { x = 'hello'; y = 'world';" + " if (true) { x; } y; }");
        final JexlContext jc = new MapContext();
        final Object o = e.execute(jc);
        Assert.assertEquals("Block result is wrong", "world", o);
    }

}
