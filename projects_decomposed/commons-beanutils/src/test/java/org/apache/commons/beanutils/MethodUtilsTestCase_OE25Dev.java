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
package org.apache.commons.beanutils;

import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.beanutils.priv.PrivateBeanFactory;
import org.apache.commons.beanutils.priv.PublicSubBean;

/**
 * <p> Test case for <code>MethodUtils</code> </p>
 *
 * @version $Id$
 */
public class MethodUtilsTestCase_OE25Dev extends TestCase {

    // ---------------------------------------------------------- Constructors

    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public MethodUtilsTestCase_OE25Dev(final String name) {
        super(name);
    }


    // -------------------------------------------------- Overall Test Methods


    /**
     * Set up instance variables required by this test case.
     */
    @Override
    public void setUp() {
    }


    /**
     * Return the tests included in this test suite.
     */
    public static Test suite() {
        return (new TestSuite(MethodUtilsTestCase_OE25Dev.class));
    }

    /**
     * Tear down instance variables required by this test case.
     */
    @Override
    public void tearDown() {
    }


    // ------------------------------------------------ Individual Test Methods

    /**
     * <p> Test <code>getAccessibleMethod</code>.
     */

    private static void assertMethod(final Method method, final String methodName) {
        assertNotNull(method);
        assertEquals("Method is not named correctly",methodName,method.getName());
        assertTrue("Method is not public",Modifier.isPublic(method.getModifiers()));
    }

    /**
     * <p> Test <code>invokeExactMethod</code>.
     */

    /**
     * <p> Test <code>invokeMethod</code>.
     */


    public void testInvokeMethodUnknown() throws Exception {
        // test that exception is correctly thrown when a method cannot be found with matching params
        try {
            final AbstractParent parent = new AlphaBean("parent");
            final BetaBean childOne = new BetaBean("ChildOne");
            MethodUtils.invokeMethod(parent, "bogus", childOne);

            fail("No exception thrown when no appropriate method exists");
        } catch (final NoSuchMethodException expected) {
            // this is what we're expecting!
        }
    }

    /**
     * Simple tests for accessing static methods via invokeMethod().
     */


    /**
     * Simple tests for accessing static methods via invokeExactMethod().
     */

    /**
     * Simple tests for accessing static methods via getAccessibleMethod()
     */

    /**
     * Test {@link MethodUtils#clearCache()}.
     */

    /**
     * Test {@link MethodUtils#setCacheMethods(boolean)}.
     */

    public void testInvokeExactMethod_1_oe() throws Exception {
            final TestBean bean = new TestBean();
            final Object ret = MethodUtils.invokeExactMethod(bean, "setStringProperty", "TEST");

            assertNull(ret);
    }

    public void testInvokeExactMethod_2_oe() throws Exception {
            final TestBean bean = new TestBean();
            final Object ret = MethodUtils.invokeExactMethod(bean, "setStringProperty", "TEST");

            assertEquals("Method ONE was invoked", "TEST", bean.getStringProperty());
    }

    public void testInvokeExactMethodFromInterface_1_oe() throws Exception {
        final Object ret = MethodUtils.invokeExactMethod(
                PrivateBeanFactory.create(),
                "methodBar",
                "ANOTHER TEST");

        assertEquals("Method TWO wasn't invoked correctly", "ANOTHER TEST", ret);
    }

    public void testInvokeExactMethodIndirectInterface_1_oe() throws Exception {
        final Object ret = MethodUtils.invokeExactMethod(
                PrivateBeanFactory.createSubclass(),
                "methodBaz",
                "YET ANOTHER TEST");

        assertEquals("Method TWO was invoked correctly", "YET ANOTHER TEST", ret);
    }

    public void testInvokeExactMethodNullArray_1_oe() throws Exception {
        final Object result = MethodUtils.invokeExactMethod(
                new AlphaBean("parent"),
                "getName",
                null);
        assertEquals("parent", result);
    }

    public void testInvokeExactMethodNullArrayNullArray_1_oe() throws Exception {
        final Object result = MethodUtils.invokeExactMethod(
                new AlphaBean("parent"),
                "getName",
                null,
                null);

        assertEquals("parent", result);
    }

    public void testInvokeExactMethodNull_1_oe() throws Exception {
        final Object object = new Object();
        final Object result = MethodUtils.invokeExactMethod(object, "toString", (Object) null);
        assertEquals(object.toString(), result);
    }

    public void testInvokeMethod_1_oe() throws Exception {
        final AbstractParent parent = new AlphaBean("parent");
        final BetaBean childOne = new BetaBean("ChildOne");

        assertEquals("Cannot invoke through abstract class(1)","ChildOne",MethodUtils.invokeMethod(parent,"testAddChild",childOne));
    }

    public void testInvokeMethodObject_1_oe() throws Exception {
        final AbstractParent parent = new AlphaBean("parent");
        final Child childTwo = new AlphaBean("ChildTwo");

        assertEquals("Cannot invoke through interface(1)","ChildTwo",MethodUtils.invokeMethod(parent,"testAddChild",childTwo));
    }

    public void testInvokeMethodArray_1_oe() throws Exception {
        final AbstractParent parent = new AlphaBean("parent");
        final AlphaBean childTwo = new AlphaBean("ChildTwo");

        final Object[] params = new Object[2];
        params[0] = "parameter";
        params[1] = childTwo;

        assertEquals("Cannot invoke through abstract class","ChildTwo",MethodUtils.invokeMethod(parent,"testAddChild2",params));
    }

    public void testInvokeMethodNullArray_1_oe() throws Exception {
        final Object result = MethodUtils.invokeMethod(
                new AlphaBean("parent"),
                "getName",
                null);

        assertEquals("parent", result);
    }

    public void testInvokeMethodNullArrayNullArray_1_oe() throws Exception {
        final Object result = MethodUtils.invokeMethod(
                new AlphaBean("parent"),
                "getName",
                null,
                null);

        assertEquals("parent", result);
    }

    public void testInvokeMethodNull_1_oe() throws Exception {
        final Object object = new Object();
        final Object result = MethodUtils.invokeMethod(object, "toString", (Object) null);
        assertEquals(object.toString(), result);
    }

    public void testInvokeMethodPrimitiveBoolean_1_oe() throws Exception {
        final PrimitiveBean bean = new PrimitiveBean();
        MethodUtils.invokeMethod(bean, "setBoolean", Boolean.FALSE);
        assertEquals("Call boolean property using invokeMethod", false, bean.getBoolean());
    }

    public void testInvokeMethodPrimitiveFloat_1_oe() throws Exception {
        final PrimitiveBean bean = new PrimitiveBean();
        MethodUtils.invokeMethod(bean, "setFloat", Float.valueOf(20.0f));
        assertEquals("Call float property using invokeMethod", 20.0f, bean.getFloat(), 0.01f);
    }

    public void testInvokeMethodPrimitiveLong_1_oe() throws Exception {
        final PrimitiveBean bean = new PrimitiveBean();
        MethodUtils.invokeMethod(bean, "setLong", Long.valueOf(10));
        assertEquals("Call long property using invokeMethod", 10, bean.getLong());
    }

    public void testInvokeMethodPrimitiveInt_1_oe() throws Exception {
        final PrimitiveBean bean = new PrimitiveBean();
        MethodUtils.invokeMethod(bean, "setInt", Integer.valueOf(12));
        assertEquals("Set int property using invokeMethod", 12, bean.getInt());
    }

    public void testInvokeMethodPrimitiveDouble_1_oe() throws Exception {
        final PrimitiveBean bean = new PrimitiveBean();
        MethodUtils.invokeMethod(bean, "setDouble", Double.valueOf(25.5d));
        assertEquals("Set double property using invokeMethod", 25.5d, bean.getDouble(), 0.01d);
    }

    public void testStaticInvokeMethod_1_oe() throws Exception {

        Object value = null;
        int current = TestBean.currentCounter();

        value = MethodUtils.invokeStaticMethod(TestBean.class, "currentCounter", new Object[0]);
        assertEquals("currentCounter value", current, ((Integer) value).intValue());
    }

    public void testStaticInvokeMethod_2_oe() throws Exception {

        Object value = null;
        int current = TestBean.currentCounter();

        value = MethodUtils.invokeStaticMethod(TestBean.class, "currentCounter", new Object[0]);

        MethodUtils.invokeStaticMethod(TestBean.class, "incrementCounter", new Object[0]);
        current++;

        value = MethodUtils.invokeStaticMethod(TestBean.class, "currentCounter", new Object[0]);
        assertEquals("currentCounter value", current, ((Integer) value).intValue());
    }

    public void testStaticInvokeMethod_3_oe() throws Exception {

        Object value = null;
        int current = TestBean.currentCounter();

        value = MethodUtils.invokeStaticMethod(TestBean.class, "currentCounter", new Object[0]);

        MethodUtils.invokeStaticMethod(TestBean.class, "incrementCounter", new Object[0]);
        current++;

        value = MethodUtils.invokeStaticMethod(TestBean.class, "currentCounter", new Object[0]);

        MethodUtils.invokeStaticMethod(TestBean.class, "incrementCounter", new Object[] { new Integer(8) } );
        current += 8;

        value = MethodUtils.invokeStaticMethod(TestBean.class, "currentCounter", new Object[0]);
        assertEquals("currentCounter value", current, ((Integer) value).intValue());
    }

    public void testStaticInvokeMethod_4_oe() throws Exception {

        Object value = null;
        int current = TestBean.currentCounter();

        value = MethodUtils.invokeStaticMethod(TestBean.class, "currentCounter", new Object[0]);

        MethodUtils.invokeStaticMethod(TestBean.class, "incrementCounter", new Object[0]);
        current++;

        value = MethodUtils.invokeStaticMethod(TestBean.class, "currentCounter", new Object[0]);

        MethodUtils.invokeStaticMethod(TestBean.class, "incrementCounter", new Object[] { new Integer(8) } );
        current += 8;

        value = MethodUtils.invokeStaticMethod(TestBean.class, "currentCounter", new Object[0]);

        MethodUtils.invokeExactStaticMethod(TestBean.class, "incrementCounter",
            new Object[] { new Integer(8) }, new Class[] { Number.class } );
        current += 16;

        value = MethodUtils.invokeStaticMethod(TestBean.class, "currentCounter", new Object[0]);
        assertEquals("currentCounter value", current, ((Integer) value).intValue());
    }

    public void testInvokeStaticMethodNull_1_oe() throws Exception {
        final int current = TestBean.currentCounter();
        final Object value = MethodUtils.invokeStaticMethod(TestBean.class, "currentCounter", (Object) null);
        assertEquals("currentCounter value", current, ((Integer) value).intValue());
    }

    public void testInvokeExactStaticMethodNull_1_oe() throws Exception {
        final int current = TestBean.currentCounter();
        final Object value = MethodUtils.invokeExactStaticMethod(TestBean.class, "currentCounter", (Object) null);
        assertEquals("currentCounter value", current, ((Integer) value).intValue());
    }

    public void testSimpleStatic1_10_oe() {

        final TestBean bean = new TestBean();
        Object value = null;
        int current = TestBean.currentCounter();

        try {

            value = MethodUtils.invokeMethod
                (bean, "currentCounter", new Object[0], new Class[0]);

            MethodUtils.invokeMethod
                (bean, "incrementCounter", new Object[0], new Class[0]);

            current++;
            value = MethodUtils.invokeMethod
                (bean, "currentCounter", new Object[0], new Class[0]);

            MethodUtils.invokeMethod
                (bean, "incrementCounter",
                 new Object[] { new Integer(5) },
                 new Class[] { Integer.TYPE });

            current += 5;
            value = MethodUtils.invokeMethod
                (bean, "currentCounter", new Object[0], new Class[0]);

        } catch (final Exception e) {
            fail("Threw exception" + e);
    }
    }

    public void testSimpleStatic2_10_oe() {

        final TestBean bean = new TestBean();
        Object value = null;
        int current = TestBean.currentCounter();

        try {

            value = MethodUtils.invokeExactMethod
                (bean, "currentCounter", new Object[0], new Class[0]);

            MethodUtils.invokeExactMethod
                (bean, "incrementCounter", new Object[0], new Class[0]);

            current++;
            value = MethodUtils.invokeExactMethod
                (bean, "currentCounter", new Object[0], new Class[0]);

            MethodUtils.invokeExactMethod
                (bean, "incrementCounter",
                 new Object[] { new Integer(5) },
                 new Class[] { Integer.TYPE });

            current += 5;
            value = MethodUtils.invokeExactMethod
                (bean, "currentCounter", new Object[0], new Class[0]);

        } catch (final Exception e) {
            fail("Threw exception" + e);
    }
    }

    public void testSimpleStatic3_25_oe() {

        Object value = null;
        int current = TestBean.currentCounter();

        try {

            final Method currentCounterMethod = MethodUtils.getAccessibleMethod
                (TestBean.class, "currentCounter",
                 new Class[0]);
            final Method incrementCounterMethod1 = MethodUtils.getAccessibleMethod
                (TestBean.class, "incrementCounter",
                 new Class[0]);
            final Method incrementCounterMethod2 = MethodUtils.getAccessibleMethod
                (TestBean.class, "incrementCounter",
                 new Class[] { Integer.TYPE });

            value = currentCounterMethod.invoke(null, new Object[0]);

            incrementCounterMethod1.invoke(null, new Object[0]);

            current++;
            value = currentCounterMethod.invoke(null, new Object[0]);

            incrementCounterMethod2.invoke(null,
                                           new Object[] { new Integer(5) });

            current += 5;
            value = currentCounterMethod.invoke(null, new Object[0]);

        } catch (final Exception e) {
            fail("Threw exception" + e);
    }
    }

    public void testPublicSub_1_oe() throws Exception {
        final PublicSubBean bean = new PublicSubBean();
        assertEquals("Start value (foo)", bean.getFoo(), "This is foo");
    }

    public void testPublicSub_2_oe() throws Exception {
        final PublicSubBean bean = new PublicSubBean();
        assertEquals("Start value (bar)", bean.getBar(), "This is bar");
    }

    public void testPublicSub_3_oe() throws Exception {
        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("new foo");
        bean.setBar("new bar");
        assertEquals("Set value (foo)", bean.getFoo(), "new foo");
    }

    public void testPublicSub_4_oe() throws Exception {
        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("new foo");
        bean.setBar("new bar");
        assertEquals("Set value (bar)", bean.getBar(), "new bar");
    }

    public void testPublicSub_5_oe() throws Exception {
        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("new foo");
        bean.setBar("new bar");

        MethodUtils.invokeMethod(bean, "setFoo", "alpha");
        assertEquals("Set value (foo:2)", bean.getFoo(), "alpha");
    }

    public void testPublicSub_6_oe() throws Exception {
        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("new foo");
        bean.setBar("new bar");

        MethodUtils.invokeMethod(bean, "setFoo", "alpha");
        MethodUtils.invokeMethod(bean, "setBar", "beta");
        assertEquals("Set value (bar:2)", bean.getBar(), "beta");
    }

    public void testPublicSub_7_oe() throws Exception {
        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("new foo");
        bean.setBar("new bar");

        MethodUtils.invokeMethod(bean, "setFoo", "alpha");
        MethodUtils.invokeMethod(bean, "setBar", "beta");

        Method method = null;
        try {
            method = MethodUtils.getAccessibleMethod(PublicSubBean.class, "setFoo", String.class);
        } catch (final Throwable t) {
            fail("getAccessibleMethod() setFoo threw " + t);
    }
    }

    public void testPublicSub_8_oe() throws Exception {
        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("new foo");
        bean.setBar("new bar");

        MethodUtils.invokeMethod(bean, "setFoo", "alpha");
        MethodUtils.invokeMethod(bean, "setBar", "beta");

        Method method = null;
        try {
            method = MethodUtils.getAccessibleMethod(PublicSubBean.class, "setFoo", String.class);
        } catch (final Throwable t) {
        }
        assertNotNull("getAccessibleMethod() setFoo is Null", method);
    }

    public void testPublicSub_9_oe() throws Exception {
        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("new foo");
        bean.setBar("new bar");

        MethodUtils.invokeMethod(bean, "setFoo", "alpha");
        MethodUtils.invokeMethod(bean, "setBar", "beta");

        Method method = null;
        try {
            method = MethodUtils.getAccessibleMethod(PublicSubBean.class, "setFoo", String.class);
        } catch (final Throwable t) {
        }
        try {
            method.invoke(bean, new Object[] {"1111"});
        } catch (final Throwable t) {
            fail("Invoking setFoo threw " + t);
    }
    }

    public void testPublicSub_10_oe() throws Exception {
        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("new foo");
        bean.setBar("new bar");

        MethodUtils.invokeMethod(bean, "setFoo", "alpha");
        MethodUtils.invokeMethod(bean, "setBar", "beta");

        Method method = null;
        try {
            method = MethodUtils.getAccessibleMethod(PublicSubBean.class, "setFoo", String.class);
        } catch (final Throwable t) {
        }
        try {
            method.invoke(bean, new Object[] {"1111"});
        } catch (final Throwable t) {
        }
        assertEquals("Set value (foo:3)", "1111", bean.getFoo());
    }

    public void testPublicSub_11_oe() throws Exception {
        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("new foo");
        bean.setBar("new bar");

        MethodUtils.invokeMethod(bean, "setFoo", "alpha");
        MethodUtils.invokeMethod(bean, "setBar", "beta");

        Method method = null;
        try {
            method = MethodUtils.getAccessibleMethod(PublicSubBean.class, "setFoo", String.class);
        } catch (final Throwable t) {
        }
        try {
            method.invoke(bean, new Object[] {"1111"});
        } catch (final Throwable t) {
        }

        try {
            method = MethodUtils.getAccessibleMethod(PublicSubBean.class, "setBar", String.class);
        } catch (final Throwable t) {
            fail("getAccessibleMethod() setBar threw " + t);
    }
    }

    public void testPublicSub_12_oe() throws Exception {
        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("new foo");
        bean.setBar("new bar");

        MethodUtils.invokeMethod(bean, "setFoo", "alpha");
        MethodUtils.invokeMethod(bean, "setBar", "beta");

        Method method = null;
        try {
            method = MethodUtils.getAccessibleMethod(PublicSubBean.class, "setFoo", String.class);
        } catch (final Throwable t) {
        }
        try {
            method.invoke(bean, new Object[] {"1111"});
        } catch (final Throwable t) {
        }

        try {
            method = MethodUtils.getAccessibleMethod(PublicSubBean.class, "setBar", String.class);
        } catch (final Throwable t) {
        }
        assertNotNull("getAccessibleMethod() setBar is Null", method);
    }

    public void testPublicSub_13_oe() throws Exception {
        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("new foo");
        bean.setBar("new bar");

        MethodUtils.invokeMethod(bean, "setFoo", "alpha");
        MethodUtils.invokeMethod(bean, "setBar", "beta");

        Method method = null;
        try {
            method = MethodUtils.getAccessibleMethod(PublicSubBean.class, "setFoo", String.class);
        } catch (final Throwable t) {
        }
        try {
            method.invoke(bean, new Object[] {"1111"});
        } catch (final Throwable t) {
        }

        try {
            method = MethodUtils.getAccessibleMethod(PublicSubBean.class, "setBar", String.class);
        } catch (final Throwable t) {
        }
        try {
            method.invoke(bean, new Object[] {"2222"});
        } catch (final Throwable t) {
            fail("Invoking setBar threw " + t);
    }
    }

    public void testPublicSub_14_oe() throws Exception {
        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("new foo");
        bean.setBar("new bar");

        MethodUtils.invokeMethod(bean, "setFoo", "alpha");
        MethodUtils.invokeMethod(bean, "setBar", "beta");

        Method method = null;
        try {
            method = MethodUtils.getAccessibleMethod(PublicSubBean.class, "setFoo", String.class);
        } catch (final Throwable t) {
        }
        try {
            method.invoke(bean, new Object[] {"1111"});
        } catch (final Throwable t) {
        }

        try {
            method = MethodUtils.getAccessibleMethod(PublicSubBean.class, "setBar", String.class);
        } catch (final Throwable t) {
        }
        try {
            method.invoke(bean, new Object[] {"2222"});
        } catch (final Throwable t) {
        }
        assertEquals("Set value (bar:3)", "2222", bean.getBar());
    }

    public void testParentMethod_1_oe() throws Exception {
        final OutputStream os = new PrintStream(System.out);
        final PrintStream ps = new PrintStream(System.out);

        A a = new A();
        MethodUtils.invokeMethod(a, "foo", os);
        assertTrue("Method Invoked(1)", a.called);
    }

    public void testParentMethod_2_oe() throws Exception {
        final OutputStream os = new PrintStream(System.out);
        final PrintStream ps = new PrintStream(System.out);

        A a = new A();
        MethodUtils.invokeMethod(a, "foo", os);

        a = new A();
        MethodUtils.invokeMethod(a, "foo", ps);
        assertTrue("Method Invoked(2)", a.called);
    }

    public void testClearCache_1_oe() throws Exception {
        MethodUtils.clearCache(); // make sure it starts empty
        final PublicSubBean bean = new PublicSubBean();
        MethodUtils.invokeMethod(bean, "setFoo", "alpha");
        assertEquals(1, MethodUtils.clearCache());
    }

    public void testSetCacheMethods_1_oe() throws Exception {
        MethodUtils.setCacheMethods(true);
        MethodUtils.clearCache(); // make sure it starts empty

        final PublicSubBean bean = new PublicSubBean();
        MethodUtils.invokeMethod(bean, "setFoo", "alpha");
        assertEquals(1, MethodUtils.clearCache());
    }

    public void testNoCaching_1_oe() throws Exception {
        MethodUtils.setCacheMethods(false);

        final PublicSubBean bean = new PublicSubBean();
        MethodUtils.invokeMethod(bean, "setFoo", "alpha");
        assertEquals(0, MethodUtils.clearCache());
    }

    public void testGetAccessibleMethod_1_oe_1_oe() {
        final Method method = MethodUtils.getAccessibleMethod
                (TestBean.class, "setStringProperty", String.class);

                final Method method0 = method;
        final String methodName0 = "setStringProperty";
        assertNotNull(method0);
    }

    public void testGetAccessibleMethod_1_oe_2_oe() {
        final Method method = MethodUtils.getAccessibleMethod
                (TestBean.class, "setStringProperty", String.class);

                final Method method0 = method;
        final String methodName0 = "setStringProperty";
                assertEquals("Method is not named correctly",methodName0,method0.getName());
    }

    public void testGetAccessibleMethod_1_oe_3_oe() {
        final Method method = MethodUtils.getAccessibleMethod
                (TestBean.class, "setStringProperty", String.class);

                final Method method0 = method;
        final String methodName0 = "setStringProperty";
                assertTrue("Method is not public",Modifier.isPublic(method0.getModifiers()));
    }

    public void testGetAccessibleMethodFromInterface_1_oe_1_oe() {
        Method method;
        method = MethodUtils.getAccessibleMethod
                (PrivateBeanFactory.create().getClass(),
                        "methodBar",
                        String.class);

                final Method method0 = method;
        final String methodName0 = "methodBar";
        assertNotNull(method0);
    }

    public void testGetAccessibleMethodFromInterface_1_oe_2_oe() {
        Method method;
        method = MethodUtils.getAccessibleMethod
                (PrivateBeanFactory.create().getClass(),
                        "methodBar",
                        String.class);

                final Method method0 = method;
        final String methodName0 = "methodBar";
                assertEquals("Method is not named correctly",methodName0,method0.getName());
    }

    public void testGetAccessibleMethodFromInterface_1_oe_3_oe() {
        Method method;
        method = MethodUtils.getAccessibleMethod
                (PrivateBeanFactory.create().getClass(),
                        "methodBar",
                        String.class);

                final Method method0 = method;
        final String methodName0 = "methodBar";
                assertTrue("Method is not public",Modifier.isPublic(method0.getModifiers()));
    }

    public void testGetAccessibleMethodIndirectInterface_1_oe_1_oe() {
        Method method;
        method = MethodUtils.getAccessibleMethod
                (PrivateBeanFactory.createSubclass().getClass(),
                        "methodBaz",
                        String.class);

                final Method method0 = method;
        final String methodName0 = "methodBaz";
        assertNotNull(method0);
    }

    public void testGetAccessibleMethodIndirectInterface_1_oe_2_oe() {
        Method method;
        method = MethodUtils.getAccessibleMethod
                (PrivateBeanFactory.createSubclass().getClass(),
                        "methodBaz",
                        String.class);

                final Method method0 = method;
        final String methodName0 = "methodBaz";
                assertEquals("Method is not named correctly",methodName0,method0.getName());
    }

    public void testGetAccessibleMethodIndirectInterface_1_oe_3_oe() {
        Method method;
        method = MethodUtils.getAccessibleMethod
                (PrivateBeanFactory.createSubclass().getClass(),
                        "methodBaz",
                        String.class);

                final Method method0 = method;
        final String methodName0 = "methodBaz";
                assertTrue("Method is not public",Modifier.isPublic(method0.getModifiers()));
    }

}
