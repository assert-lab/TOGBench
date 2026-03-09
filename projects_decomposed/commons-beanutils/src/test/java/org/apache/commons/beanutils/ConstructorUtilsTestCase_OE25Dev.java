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


import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * <p> Test case for <code>ConstructorUtils</code> </p>
 *
 * @version $Id$
 */
public class ConstructorUtilsTestCase_OE25Dev extends TestCase {

    // ---------------------------------------------------------- Constructors

    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public ConstructorUtilsTestCase_OE25Dev(final String name) {
        super(name);
    }


    // -------------------------------------------------- Overall Test Methods


    /**
     * Set up instance variables required by this test case.
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }


    /**
     * Return the tests included in this test suite.
     */
    public static Test suite() {
        return (new TestSuite(ConstructorUtilsTestCase_OE25Dev.class));
    }

    /**
     * Tear down instance variables required by this test case.
     */
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }


    // ------------------------------------------------ Individual Test Methods

    public void testInvokeConstructor() throws Exception {
        {
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,"TEST");
            assertNotNull(obj);
            assertTrue(obj instanceof TestBean);
            assertEquals("TEST",((TestBean)obj).getStringProperty());
        }
        {
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,new Float(17.3f));
            assertNotNull(obj);
            assertTrue(obj instanceof TestBean);
            assertEquals(17.3f,((TestBean)obj).getFloatProperty(),0.0f);
        }
    }

    public void testInvokeConstructorNull() throws Exception {
        final Object obj = ConstructorUtils.invokeConstructor(TestBean.class, (Object) null);
        assertNotNull(obj);
        assertTrue(obj instanceof TestBean);
    }

    public void testInvokeConstructorWithArgArray() throws Exception {
        final Object[] args = { new Float(17.3f), "TEST" };
        final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args);
        assertNotNull(obj);
        assertTrue(obj instanceof TestBean);
        assertEquals(17.3f,((TestBean)obj).getFloatProperty(),0.0f);
        assertEquals("TEST",((TestBean)obj).getStringProperty());
    }

    public void testInvokeConstructorWithTypeArray() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args,types);
            assertNotNull(obj);
            assertTrue(obj instanceof TestBean);
            assertEquals(true,((TestBean)obj).getBooleanProperty());
            assertEquals("TEST",((TestBean)obj).getStringProperty());
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.class, String.class };
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args,types);
            assertNotNull(obj);
            assertTrue(obj instanceof TestBean);
            assertEquals(true,((TestBean)obj).isBooleanSecond());
            assertEquals("TEST",((TestBean)obj).getStringProperty());
        }
    }

    public void testInvokeExactConstructor() throws Exception {
        {
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,"TEST");
            assertNotNull(obj);
            assertTrue(obj instanceof TestBean);
            assertEquals("TEST",((TestBean)obj).getStringProperty());
        }
        {
            try {
                ConstructorUtils.invokeExactConstructor(TestBean.class,new Float(17.3f));
                fail("Expected NoSuchMethodException");
            } catch(final NoSuchMethodException e) {
                // expected
            }
        }
        {
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,Boolean.TRUE);
            assertNotNull(obj);
            assertTrue(obj instanceof TestBean);
            assertEquals(true,((TestBean)obj).isBooleanSecond());
        }
    }

    public void testInvokeExactConstructorWithNull() throws Exception {
        final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class, (Object) null);
        assertNotNull(obj);
        assertTrue(obj instanceof TestBean);
    }

    public void testInvokeExactConstructorWithArgArray() throws Exception {
        {
            final Object[] args = { new Float(17.3f), "TEST" };
            try {
                ConstructorUtils.invokeExactConstructor(TestBean.class,args);
                fail("Expected NoSuchMethodException");
            } catch(final NoSuchMethodException e) {
                // expected
            }
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args);
            assertNotNull(obj);
            assertTrue(obj instanceof TestBean);
            assertEquals(true,((TestBean)obj).isBooleanSecond());
            assertEquals("TEST",((TestBean)obj).getStringProperty());
        }
    }

    public void testInvokeExactConstructorWithTypeArray() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
            assertNotNull(obj);
            assertTrue(obj instanceof TestBean);
            assertEquals(true,((TestBean)obj).getBooleanProperty());
            assertEquals("TEST",((TestBean)obj).getStringProperty());
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.class, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
            assertNotNull(obj);
            assertTrue(obj instanceof TestBean);
            assertEquals(true,((TestBean)obj).isBooleanSecond());
            assertEquals("TEST",((TestBean)obj).getStringProperty());
        }
        {
            final Object[] args = { new Float(17.3f), "TEST" };
            final Class<?>[] types = { Float.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
            assertNotNull(obj);
            assertTrue(obj instanceof TestBean);
            assertEquals(17.3f,((TestBean)obj).getFloatProperty(),0.0f);
            assertEquals("TEST",((TestBean)obj).getStringProperty());
        }
        {
            final Object[] args = { new Float(17.3f), "TEST" };
            final Class<?>[] types = { Float.class, String.class };
            try {
                ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
                fail("Expected NoSuchMethodException");
            } catch(final NoSuchMethodException e) {
                // expected
            }
        }
    }

    public void testGetAccessibleConstructor() throws Exception {
        {
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,String.class);
            assertNotNull(ctor);
            assertTrue(Modifier.isPublic(ctor.getModifiers()));
        }
        {
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,Integer.class);
            assertNotNull(ctor);
            assertTrue(Modifier.isPublic(ctor.getModifiers()));
        }
        {
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,Integer.TYPE);
            assertNull(ctor);
        }
    }

    public void testGetAccessibleConstructorWithTypeArray() throws Exception {
        {
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,types);
            assertNotNull(ctor);
            assertTrue(Modifier.isPublic(ctor.getModifiers()));
        }
        {
            final Class<?>[] types = { Boolean.TYPE, Boolean.TYPE, String.class };
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,types);
            assertNull(ctor);
        }
    }

    public void testGetAccessibleConstructorWithConstructorArg() throws Exception {
        {
            final Class<?>[] types = { Integer.class };
            final Constructor<?> c1 = TestBean.class.getConstructor(types);
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(c1);
            assertNotNull(ctor);
            assertTrue(Modifier.isPublic(ctor.getModifiers()));
        }
        {
            final Class<?>[] types = { Integer.class };
            final Constructor<?> c1 = TestBean.class.getDeclaredConstructor(types);
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(c1);
            assertNotNull(ctor);
            assertTrue(Modifier.isPublic(ctor.getModifiers()));
        }
        {
            final Class<?>[] types = { Integer.TYPE };
            final Constructor<?> c1 = TestBean.class.getDeclaredConstructor(types);
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(c1);
            assertNull(ctor);
        }
    }

    public void testInvokeConstructor_1_oe() throws Exception {
        {
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,"TEST");
            assertNotNull(obj);
    }
    }

    public void testInvokeConstructor_2_oe() throws Exception {
        {
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,"TEST");
            assertTrue(obj instanceof TestBean);
    }
    }

    public void testInvokeConstructor_3_oe() throws Exception {
        {
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,"TEST");
            assertEquals("TEST",((TestBean)obj).getStringProperty());
    }
    }

    public void testInvokeConstructor_4_oe() throws Exception {
        {
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,"TEST");
        }
        {
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,new Float(17.3f));
            assertNotNull(obj);
    }
    }

    public void testInvokeConstructor_5_oe() throws Exception {
        {
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,"TEST");
        }
        {
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,new Float(17.3f));
            assertTrue(obj instanceof TestBean);
    }
    }

    public void testInvokeConstructor_6_oe() throws Exception {
        {
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,"TEST");
        }
        {
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,new Float(17.3f));
            assertEquals(17.3f,((TestBean)obj).getFloatProperty(),0.0f);
    }
    }

    public void testInvokeConstructorNull_1_oe() throws Exception {
        final Object obj = ConstructorUtils.invokeConstructor(TestBean.class, (Object) null);
        assertNotNull(obj);
    }

    public void testInvokeConstructorNull_2_oe() throws Exception {
        final Object obj = ConstructorUtils.invokeConstructor(TestBean.class, (Object) null);
        assertTrue(obj instanceof TestBean);
    }

    public void testInvokeConstructorWithArgArray_1_oe() throws Exception {
        final Object[] args = { new Float(17.3f), "TEST" };
        final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args);
        assertNotNull(obj);
    }

    public void testInvokeConstructorWithArgArray_2_oe() throws Exception {
        final Object[] args = { new Float(17.3f), "TEST" };
        final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args);
        assertTrue(obj instanceof TestBean);
    }

    public void testInvokeConstructorWithArgArray_3_oe() throws Exception {
        final Object[] args = { new Float(17.3f), "TEST" };
        final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args);
        assertEquals(17.3f,((TestBean)obj).getFloatProperty(),0.0f);
    }

    public void testInvokeConstructorWithArgArray_4_oe() throws Exception {
        final Object[] args = { new Float(17.3f), "TEST" };
        final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args);
        assertEquals("TEST",((TestBean)obj).getStringProperty());
    }

    public void testInvokeConstructorWithTypeArray_1_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args,types);
            assertNotNull(obj);
    }
    }

    public void testInvokeConstructorWithTypeArray_2_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args,types);
            assertTrue(obj instanceof TestBean);
    }
    }

    public void testInvokeConstructorWithTypeArray_3_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args,types);
            assertEquals(true,((TestBean)obj).getBooleanProperty());
    }
    }

    public void testInvokeConstructorWithTypeArray_4_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args,types);
            assertEquals("TEST",((TestBean)obj).getStringProperty());
    }
    }

    public void testInvokeConstructorWithTypeArray_5_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args,types);
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.class, String.class };
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args,types);
            assertNotNull(obj);
    }
    }

    public void testInvokeConstructorWithTypeArray_6_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args,types);
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.class, String.class };
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args,types);
            assertTrue(obj instanceof TestBean);
    }
    }

    public void testInvokeConstructorWithTypeArray_7_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args,types);
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.class, String.class };
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args,types);
            assertEquals(true,((TestBean)obj).isBooleanSecond());
    }
    }

    public void testInvokeConstructorWithTypeArray_8_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args,types);
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.class, String.class };
            final Object obj = ConstructorUtils.invokeConstructor(TestBean.class,args,types);
            assertEquals("TEST",((TestBean)obj).getStringProperty());
    }
    }

    public void testInvokeExactConstructor_1_oe() throws Exception {
        {
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,"TEST");
            assertNotNull(obj);
    }
    }

    public void testInvokeExactConstructor_2_oe() throws Exception {
        {
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,"TEST");
            assertTrue(obj instanceof TestBean);
    }
    }

    public void testInvokeExactConstructor_3_oe() throws Exception {
        {
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,"TEST");
            assertEquals("TEST",((TestBean)obj).getStringProperty());
    }
    }

    public void testInvokeExactConstructor_5_oe() throws Exception {
        {
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,"TEST");
        }
        {
            try {
                ConstructorUtils.invokeExactConstructor(TestBean.class,new Float(17.3f));
            } catch(final NoSuchMethodException e) {
            }
        }
        {
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,Boolean.TRUE);
            assertNotNull(obj);
    }
    }

    public void testInvokeExactConstructor_6_oe() throws Exception {
        {
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,"TEST");
        }
        {
            try {
                ConstructorUtils.invokeExactConstructor(TestBean.class,new Float(17.3f));
            } catch(final NoSuchMethodException e) {
            }
        }
        {
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,Boolean.TRUE);
            assertTrue(obj instanceof TestBean);
    }
    }

    public void testInvokeExactConstructor_7_oe() throws Exception {
        {
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,"TEST");
        }
        {
            try {
                ConstructorUtils.invokeExactConstructor(TestBean.class,new Float(17.3f));
            } catch(final NoSuchMethodException e) {
            }
        }
        {
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,Boolean.TRUE);
            assertEquals(true,((TestBean)obj).isBooleanSecond());
    }
    }

    public void testInvokeExactConstructorWithNull_1_oe() throws Exception {
        final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class, (Object) null);
        assertNotNull(obj);
    }

    public void testInvokeExactConstructorWithNull_2_oe() throws Exception {
        final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class, (Object) null);
        assertTrue(obj instanceof TestBean);
    }

    public void testInvokeExactConstructorWithArgArray_2_oe() throws Exception {
        {
            final Object[] args = { new Float(17.3f), "TEST" };
            try {
                ConstructorUtils.invokeExactConstructor(TestBean.class,args);
            } catch(final NoSuchMethodException e) {
            }
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args);
            assertNotNull(obj);
    }
    }

    public void testInvokeExactConstructorWithArgArray_3_oe() throws Exception {
        {
            final Object[] args = { new Float(17.3f), "TEST" };
            try {
                ConstructorUtils.invokeExactConstructor(TestBean.class,args);
            } catch(final NoSuchMethodException e) {
            }
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args);
            assertTrue(obj instanceof TestBean);
    }
    }

    public void testInvokeExactConstructorWithArgArray_4_oe() throws Exception {
        {
            final Object[] args = { new Float(17.3f), "TEST" };
            try {
                ConstructorUtils.invokeExactConstructor(TestBean.class,args);
            } catch(final NoSuchMethodException e) {
            }
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args);
            assertEquals(true,((TestBean)obj).isBooleanSecond());
    }
    }

    public void testInvokeExactConstructorWithArgArray_5_oe() throws Exception {
        {
            final Object[] args = { new Float(17.3f), "TEST" };
            try {
                ConstructorUtils.invokeExactConstructor(TestBean.class,args);
            } catch(final NoSuchMethodException e) {
            }
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args);
            assertEquals("TEST",((TestBean)obj).getStringProperty());
    }
    }

    public void testInvokeExactConstructorWithTypeArray_1_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
            assertNotNull(obj);
    }
    }

    public void testInvokeExactConstructorWithTypeArray_2_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
            assertTrue(obj instanceof TestBean);
    }
    }

    public void testInvokeExactConstructorWithTypeArray_3_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
            assertEquals(true,((TestBean)obj).getBooleanProperty());
    }
    }

    public void testInvokeExactConstructorWithTypeArray_4_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
            assertEquals("TEST",((TestBean)obj).getStringProperty());
    }
    }

    public void testInvokeExactConstructorWithTypeArray_5_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.class, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
            assertNotNull(obj);
    }
    }

    public void testInvokeExactConstructorWithTypeArray_6_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.class, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
            assertTrue(obj instanceof TestBean);
    }
    }

    public void testInvokeExactConstructorWithTypeArray_7_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.class, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
            assertEquals(true,((TestBean)obj).isBooleanSecond());
    }
    }

    public void testInvokeExactConstructorWithTypeArray_8_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.class, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
            assertEquals("TEST",((TestBean)obj).getStringProperty());
    }
    }

    public void testInvokeExactConstructorWithTypeArray_9_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.class, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
        }
        {
            final Object[] args = { new Float(17.3f), "TEST" };
            final Class<?>[] types = { Float.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
            assertNotNull(obj);
    }
    }

    public void testInvokeExactConstructorWithTypeArray_10_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.class, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
        }
        {
            final Object[] args = { new Float(17.3f), "TEST" };
            final Class<?>[] types = { Float.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
            assertTrue(obj instanceof TestBean);
    }
    }

    public void testInvokeExactConstructorWithTypeArray_11_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.class, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
        }
        {
            final Object[] args = { new Float(17.3f), "TEST" };
            final Class<?>[] types = { Float.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
            assertEquals(17.3f,((TestBean)obj).getFloatProperty(),0.0f);
    }
    }

    public void testInvokeExactConstructorWithTypeArray_12_oe() throws Exception {
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
        }
        {
            final Object[] args = { Boolean.TRUE, "TEST" };
            final Class<?>[] types = { Boolean.class, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
        }
        {
            final Object[] args = { new Float(17.3f), "TEST" };
            final Class<?>[] types = { Float.TYPE, String.class };
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,args,types);
            assertEquals("TEST",((TestBean)obj).getStringProperty());
    }
    }

    public void testGetAccessibleConstructor_1_oe() throws Exception {
        {
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,String.class);
            assertNotNull(ctor);
    }
    }

    public void testGetAccessibleConstructor_2_oe() throws Exception {
        {
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,String.class);
            assertTrue(Modifier.isPublic(ctor.getModifiers()));
    }
    }

    public void testGetAccessibleConstructor_3_oe() throws Exception {
        {
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,String.class);
        }
        {
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,Integer.class);
            assertNotNull(ctor);
    }
    }

    public void testGetAccessibleConstructor_4_oe() throws Exception {
        {
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,String.class);
        }
        {
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,Integer.class);
            assertTrue(Modifier.isPublic(ctor.getModifiers()));
    }
    }

    public void testGetAccessibleConstructor_5_oe() throws Exception {
        {
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,String.class);
        }
        {
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,Integer.class);
        }
        {
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,Integer.TYPE);
            assertNull(ctor);
    }
    }

    public void testGetAccessibleConstructorWithTypeArray_1_oe() throws Exception {
        {
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,types);
            assertNotNull(ctor);
    }
    }

    public void testGetAccessibleConstructorWithTypeArray_2_oe() throws Exception {
        {
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,types);
            assertTrue(Modifier.isPublic(ctor.getModifiers()));
    }
    }

    public void testGetAccessibleConstructorWithTypeArray_3_oe() throws Exception {
        {
            final Class<?>[] types = { Boolean.TYPE, String.class };
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,types);
        }
        {
            final Class<?>[] types = { Boolean.TYPE, Boolean.TYPE, String.class };
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(TestBean.class,types);
            assertNull(ctor);
    }
    }

    public void testGetAccessibleConstructorWithConstructorArg_1_oe() throws Exception {
        {
            final Class<?>[] types = { Integer.class };
            final Constructor<?> c1 = TestBean.class.getConstructor(types);
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(c1);
            assertNotNull(ctor);
    }
    }

    public void testGetAccessibleConstructorWithConstructorArg_2_oe() throws Exception {
        {
            final Class<?>[] types = { Integer.class };
            final Constructor<?> c1 = TestBean.class.getConstructor(types);
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(c1);
            assertTrue(Modifier.isPublic(ctor.getModifiers()));
    }
    }

    public void testGetAccessibleConstructorWithConstructorArg_3_oe() throws Exception {
        {
            final Class<?>[] types = { Integer.class };
            final Constructor<?> c1 = TestBean.class.getConstructor(types);
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(c1);
        }
        {
            final Class<?>[] types = { Integer.class };
            final Constructor<?> c1 = TestBean.class.getDeclaredConstructor(types);
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(c1);
            assertNotNull(ctor);
    }
    }

    public void testGetAccessibleConstructorWithConstructorArg_4_oe() throws Exception {
        {
            final Class<?>[] types = { Integer.class };
            final Constructor<?> c1 = TestBean.class.getConstructor(types);
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(c1);
        }
        {
            final Class<?>[] types = { Integer.class };
            final Constructor<?> c1 = TestBean.class.getDeclaredConstructor(types);
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(c1);
            assertTrue(Modifier.isPublic(ctor.getModifiers()));
    }
    }

    public void testGetAccessibleConstructorWithConstructorArg_5_oe() throws Exception {
        {
            final Class<?>[] types = { Integer.class };
            final Constructor<?> c1 = TestBean.class.getConstructor(types);
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(c1);
        }
        {
            final Class<?>[] types = { Integer.class };
            final Constructor<?> c1 = TestBean.class.getDeclaredConstructor(types);
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(c1);
        }
        {
            final Class<?>[] types = { Integer.TYPE };
            final Constructor<?> c1 = TestBean.class.getDeclaredConstructor(types);
            final Constructor<?> ctor = ConstructorUtils.getAccessibleConstructor(c1);
            assertNull(ctor);
    }
    }

public void testInvokeExactConstructor_oe_101_oe() throws Exception {
            final Object obj = ConstructorUtils.invokeExactConstructor(TestBean.class,"TEST");
            try {
                ConstructorUtils.invokeExactConstructor(TestBean.class,new Float(17.3f));
                fail("Expected NoSuchMethodException");
            } catch(final NoSuchMethodException e) {
            }
}

public void testInvokeExactConstructorWithArgArray_oe_101_oe() throws Exception {
            final Object[] args = { new Float(17.3f), "TEST" };
            try {
                ConstructorUtils.invokeExactConstructor(TestBean.class,args);
                fail("Expected NoSuchMethodException");
            } catch(final NoSuchMethodException e) {
            }
}

}
