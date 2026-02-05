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
import org.junit.Before;
import org.junit.Test;

/**
 * Tests public field set/get.
 */
@SuppressWarnings({"UnnecessaryBoxing", "AssertEqualsBetweenInconvertibleTypes"})
public class PublicFieldsTest_OE25Dev extends JexlTestCase {
    // some constants
    private static final String LOWER42 = "fourty-two";
    private static final String UPPER42 = "FOURTY-TWO";
    /**
     * An Inner class.
     */
    public static class Inner {
        public double aDouble = 42.0;
        public static double NOT42 = -42.0;
    }

    /**
     * A Struct, all fields public
     */
    public static class Struct {
        public Inner inner = new Inner();
        public int anInt = 42;
        public String aString = LOWER42;
    }

    // a pub instance
    private Struct pub;
    // the JexlContext to use
    private JexlContext ctxt;

    public PublicFieldsTest_OE25Dev() {
        super("PublicFieldsTest_OE25Dev");
    }

    @Before
    @Override
    public void setUp() {
        pub = new Struct();
        ctxt = new MapContext();
        ctxt.set("pub", pub);
    }

    public enum Gender { MALE, FEMALE }

@Test
    public void testGetInt_1_oe() throws Exception {
        final JexlExpression get = JEXL.createExpression("pub.anInt");
        Assert.assertEquals(42, get.evaluate(ctxt));
    }

@Test
    public void testGetInt_2_oe() throws Exception {
        final JexlExpression get = JEXL.createExpression("pub.anInt");
        // removed other assertion
        JEXL.setProperty(pub, "anInt", -42);
        Assert.assertEquals(-42, get.evaluate(ctxt));
    }

@Test
    public void testSetInt_1_oe() throws Exception {
        final JexlExpression set = JEXL.createExpression("pub.anInt = value");
        ctxt.set("value", -42);
        Assert.assertEquals(-42, set.evaluate(ctxt));
    }

@Test
    public void testSetInt_2_oe() throws Exception {
        final JexlExpression set = JEXL.createExpression("pub.anInt = value");
        ctxt.set("value", -42);
        // removed other assertion
        Assert.assertEquals(-42, JEXL.getProperty(pub, "anInt"));
    }

@Test
    public void testSetInt_3_oe() throws Exception {
        final JexlExpression set = JEXL.createExpression("pub.anInt = value");
        ctxt.set("value", -42);
        // removed other assertion
        // removed other assertion
        ctxt.set("value", 42);
        Assert.assertEquals(42, set.evaluate(ctxt));
    }

@Test
    public void testSetInt_4_oe() throws Exception {
        final JexlExpression set = JEXL.createExpression("pub.anInt = value");
        ctxt.set("value", -42);
        // removed other assertion
        // removed other assertion
        ctxt.set("value", 42);
        // removed other assertion
        Assert.assertEquals(42, JEXL.getProperty(pub, "anInt"));
    }

@Test
    public void testGetString_1_oe() throws Exception {
        final JexlExpression get = JEXL.createExpression("pub.aString");
        Assert.assertEquals(LOWER42, get.evaluate(ctxt));
    }

@Test
    public void testGetString_2_oe() throws Exception {
        final JexlExpression get = JEXL.createExpression("pub.aString");
        // removed other assertion
        JEXL.setProperty(pub, "aString", UPPER42);
        Assert.assertEquals(UPPER42, get.evaluate(ctxt));
    }

@Test
    public void testSetString_1_oe() throws Exception {
        final JexlExpression set = JEXL.createExpression("pub.aString = value");
        ctxt.set("value", UPPER42);
        Assert.assertEquals(UPPER42, set.evaluate(ctxt));
    }

@Test
    public void testSetString_2_oe() throws Exception {
        final JexlExpression set = JEXL.createExpression("pub.aString = value");
        ctxt.set("value", UPPER42);
        // removed other assertion
        Assert.assertEquals(UPPER42, JEXL.getProperty(pub, "aString"));
    }

@Test
    public void testSetString_3_oe() throws Exception {
        final JexlExpression set = JEXL.createExpression("pub.aString = value");
        ctxt.set("value", UPPER42);
        // removed other assertion
        // removed other assertion
        ctxt.set("value", LOWER42);
        Assert.assertEquals(LOWER42, set.evaluate(ctxt));
    }

@Test
    public void testSetString_4_oe() throws Exception {
        final JexlExpression set = JEXL.createExpression("pub.aString = value");
        ctxt.set("value", UPPER42);
        // removed other assertion
        // removed other assertion
        ctxt.set("value", LOWER42);
        // removed other assertion
        Assert.assertEquals(LOWER42, JEXL.getProperty(pub, "aString"));
    }

@Test
    public void testGetInnerDouble_1_oe() throws Exception {
        final JexlExpression get = JEXL.createExpression("pub.inner.aDouble");
        Assert.assertEquals(42.0, get.evaluate(ctxt));
    }

@Test
    public void testGetInnerDouble_2_oe() throws Exception {
        final JexlExpression get = JEXL.createExpression("pub.inner.aDouble");
        // removed other assertion
        JEXL.setProperty(pub, "inner.aDouble", -42);
        Assert.assertEquals(-42.0, get.evaluate(ctxt));
    }

@Test
    public void testSetInnerDouble_1_oe() throws Exception {
        final JexlExpression set = JEXL.createExpression("pub.inner.aDouble = value");
        ctxt.set("value", -42.0);
        Assert.assertEquals(-42.0, set.evaluate(ctxt));
    }

@Test
    public void testSetInnerDouble_2_oe() throws Exception {
        final JexlExpression set = JEXL.createExpression("pub.inner.aDouble = value");
        ctxt.set("value", -42.0);
        // removed other assertion
        Assert.assertEquals(-42.0, JEXL.getProperty(pub, "inner.aDouble"));
    }

@Test
    public void testSetInnerDouble_3_oe() throws Exception {
        final JexlExpression set = JEXL.createExpression("pub.inner.aDouble = value");
        ctxt.set("value", -42.0);
        // removed other assertion
        // removed other assertion
        ctxt.set("value", 42.0);
        Assert.assertEquals(42.0, set.evaluate(ctxt));
    }

@Test
    public void testSetInnerDouble_4_oe() throws Exception {
        final JexlExpression set = JEXL.createExpression("pub.inner.aDouble = value");
        ctxt.set("value", -42.0);
        // removed other assertion
        // removed other assertion
        ctxt.set("value", 42.0);
        // removed other assertion
        Assert.assertEquals(42.0, JEXL.getProperty(pub, "inner.aDouble"));
    }

@Test
    public void testGetEnum_1_oe() throws Exception {
        ctxt.set("com.jexl.gender", Gender.class);
        final String src = "x = com.jexl.gender.FEMALE";
        final JexlScript script = JEXL.createScript(src);
        final Object result = script.execute(ctxt);
        Assert.assertEquals(Gender.FEMALE, result);
    }

@Test
    public void testGetEnum_2_oe() throws Exception {
        ctxt.set("com.jexl.gender", Gender.class);
        final String src = "x = com.jexl.gender.FEMALE";
        final JexlScript script = JEXL.createScript(src);
        final Object result = script.execute(ctxt);
        // removed other assertion
        Assert.assertEquals(Gender.FEMALE, ctxt.get("x"));
    }

@Test
    public void testGetStaticField_1_oe() throws Exception {
        ctxt.set("com.jexl", Inner.class);
        final String src = "x = com.jexl.NOT42";
        final JexlScript script = JEXL.createScript(src);
        final Object result = script.execute(ctxt);
        Assert.assertEquals(Inner.NOT42, result);
    }

@Test
    public void testGetStaticField_2_oe() throws Exception {
        ctxt.set("com.jexl", Inner.class);
        final String src = "x = com.jexl.NOT42";
        final JexlScript script = JEXL.createScript(src);
        final Object result = script.execute(ctxt);
        // removed other assertion
        Assert.assertEquals(Inner.NOT42, ctxt.get("x"));
    }

}
