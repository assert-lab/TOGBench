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

import org.apache.commons.jexl3.internal.introspection.SandboxUberspect;
import org.apache.commons.jexl3.introspection.JexlSandbox;
import org.apache.commons.jexl3.introspection.JexlUberspect;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * Checking the builder basics.
 */
public class BuilderTest_OE25Dev {
    private static JexlBuilder builder() {
        return new JexlBuilder();
    }

    @Test
    public void testFlags_1_oe() {
        Assert.assertTrue(builder().antish(true).antish());
    }

    @Test
    public void testFlags_2_oe() {
        Assert.assertFalse(builder().antish(false).antish());
    }

    @Test
    public void testFlags_3_oe() {
        Assert.assertTrue(builder().cancellable(true).cancellable());
    }

    @Test
    public void testFlags_4_oe() {
        Assert.assertFalse(builder().cancellable(false).cancellable());
    }

    @Test
    public void testFlags_5_oe() {
        Assert.assertTrue(builder().safe(true).safe());
    }

    @Test
    public void testFlags_6_oe() {
        Assert.assertFalse(builder().safe(false).safe());
    }

    @Test
    public void testFlags_7_oe() {
        Assert.assertTrue(builder().silent(true).silent());
    }

    @Test
    public void testFlags_8_oe() {
        Assert.assertFalse(builder().silent(false).silent());
    }

    @Test
    public void testFlags_9_oe() {
        Assert.assertTrue(builder().lexical(true).lexical());
    }

    @Test
    public void testFlags_10_oe() {
        Assert.assertFalse(builder().lexical(false).lexical());
    }

    @Test
    public void testFlags_11_oe() {
        Assert.assertTrue(builder().lexicalShade(true).lexicalShade());
    }

    @Test
    public void testFlags_12_oe() {
        Assert.assertFalse(builder().lexicalShade(false).lexicalShade());
    }

    @Test
    public void testFlags_13_oe() {
        Assert.assertTrue(builder().silent(true).silent());
    }

    @Test
    public void testFlags_14_oe() {
        Assert.assertFalse(builder().silent(false).silent());
    }

    @Test
    public void testFlags_15_oe() {
        Assert.assertTrue(builder().strict(true).strict());
    }

    @Test
    public void testFlags_16_oe() {
        Assert.assertFalse(builder().strict(false).strict());
    }

    @Test
    public void testValues_1_oe() {
        Assert.assertEquals(1, builder().collectMode(1).collectMode());
    }

    @Test
    public void testValues_2_oe() {
        Assert.assertEquals(0, builder().collectMode(0).collectMode());
    }

    @Test
    public void testValues_3_oe() {
        Assert.assertEquals(32, builder().cacheThreshold(32).cacheThreshold());
    }

    @Test
    public void testValues_4_oe() {
        Assert.assertEquals(8, builder().stackOverflow(8).stackOverflow());
    }

    @Test
    public void testOther_1_oe() {
        ClassLoader cls = getClass().getClassLoader().getParent();
        Assert.assertEquals(cls, builder().loader(cls).loader());
    }

    @Test
    public void testOther_2_oe() {
        ClassLoader cls = getClass().getClassLoader().getParent();
        Charset cs = Charset.forName("UTF16");
        Assert.assertEquals(cs, builder().charset(cs).charset());
    }

    @Test
    public void testOther_3_oe() {
        ClassLoader cls = getClass().getClassLoader().getParent();
        Charset cs = Charset.forName("UTF16");
        Assert.assertEquals(cs, builder().loader(cs).charset());
    }

    @Test
    public void testOther_4_oe() {
        ClassLoader cls = getClass().getClassLoader().getParent();
        Charset cs = Charset.forName("UTF16");
        JexlUberspect u0 = builder().create().getUberspect();
        JexlSandbox sandbox = new JexlSandbox();
        JexlUberspect uberspect = new SandboxUberspect(u0, sandbox);
        Assert.assertEquals(sandbox, builder().sandbox(sandbox).sandbox());
    }

    @Test
    public void testOther_5_oe() {
        ClassLoader cls = getClass().getClassLoader().getParent();
        Charset cs = Charset.forName("UTF16");
        JexlUberspect u0 = builder().create().getUberspect();
        JexlSandbox sandbox = new JexlSandbox();
        JexlUberspect uberspect = new SandboxUberspect(u0, sandbox);
        Assert.assertEquals(uberspect, builder().uberspect(uberspect).uberspect());
    }

}
