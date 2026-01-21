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

package org.apache.commons.beanutils.converters;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Tests for the ClassReloader utility class.
 *
 * @version $Id$
 */

public class ClassReloaderTestCase_OE25Dev extends TestCase {

    // ------------------------------------------------------------------------

    public ClassReloaderTestCase_OE25Dev(final String name) {
        super(name);
    }


    public static TestSuite suite() {
        return new TestSuite(ClassReloaderTestCase.class);
    }

    // ------------------------------------------------------------------------

    public static class DummyClass {
    }

    /**
     * Test basic operation of the ClassReloader.
     */

    public void testBasicOperation_1_oe() throws Exception {
        final ClassLoader sharedLoader = this.getClass().getClassLoader();
        final ClassReloader componentLoader = new ClassReloader(sharedLoader);

        final Class<?> sharedClass = DummyClass.class;
        final Class<?> componentClass = componentLoader.reload(sharedClass);

        // the two Class objects contain the same bytecode, but are not equal
        assertTrue(sharedClass != componentClass);
    }

    public void testBasicOperation_2_oe() throws Exception {
        final ClassLoader sharedLoader = this.getClass().getClassLoader();
        final ClassReloader componentLoader = new ClassReloader(sharedLoader);

        final Class<?> sharedClass = DummyClass.class;
        final Class<?> componentClass = componentLoader.reload(sharedClass);

        // the two Class objects contain the same bytecode, but are not equal
        // removed other assertion

        // the two class objects have different classloaders
        assertSame(sharedLoader, sharedClass.getClassLoader());
    }

    public void testBasicOperation_3_oe() throws Exception {
        final ClassLoader sharedLoader = this.getClass().getClassLoader();
        final ClassReloader componentLoader = new ClassReloader(sharedLoader);

        final Class<?> sharedClass = DummyClass.class;
        final Class<?> componentClass = componentLoader.reload(sharedClass);

        // the two Class objects contain the same bytecode, but are not equal
        // removed other assertion

        // the two class objects have different classloaders
        // removed other assertion
        assertSame(componentLoader, componentClass.getClassLoader());
    }

    public void testBasicOperation_4_oe() throws Exception {
        final ClassLoader sharedLoader = this.getClass().getClassLoader();
        final ClassReloader componentLoader = new ClassReloader(sharedLoader);

        final Class<?> sharedClass = DummyClass.class;
        final Class<?> componentClass = componentLoader.reload(sharedClass);

        // the two Class objects contain the same bytecode, but are not equal
        // removed other assertion

        // the two class objects have different classloaders
        // removed other assertion
        // removed other assertion
        assertTrue(sharedLoader != componentLoader);
    }

    public void testBasicOperation_5_oe() throws Exception {
        final ClassLoader sharedLoader = this.getClass().getClassLoader();
        final ClassReloader componentLoader = new ClassReloader(sharedLoader);

        final Class<?> sharedClass = DummyClass.class;
        final Class<?> componentClass = componentLoader.reload(sharedClass);

        // the two Class objects contain the same bytecode, but are not equal
        // removed other assertion

        // the two class objects have different classloaders
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // verify that objects of these two types are not assignment-compatible
        final Object obj1 = sharedClass.newInstance();
        final Object obj2 = componentClass.newInstance();

        assertTrue("Obj1 class incorrect", sharedClass.isInstance(obj1));
    }

    public void testBasicOperation_6_oe() throws Exception {
        final ClassLoader sharedLoader = this.getClass().getClassLoader();
        final ClassReloader componentLoader = new ClassReloader(sharedLoader);

        final Class<?> sharedClass = DummyClass.class;
        final Class<?> componentClass = componentLoader.reload(sharedClass);

        // the two Class objects contain the same bytecode, but are not equal
        // removed other assertion

        // the two class objects have different classloaders
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // verify that objects of these two types are not assignment-compatible
        final Object obj1 = sharedClass.newInstance();
        final Object obj2 = componentClass.newInstance();

        // removed other assertion
        assertFalse("Obj1 class incorrect", componentClass.isInstance(obj1));
    }

    public void testBasicOperation_7_oe() throws Exception {
        final ClassLoader sharedLoader = this.getClass().getClassLoader();
        final ClassReloader componentLoader = new ClassReloader(sharedLoader);

        final Class<?> sharedClass = DummyClass.class;
        final Class<?> componentClass = componentLoader.reload(sharedClass);

        // the two Class objects contain the same bytecode, but are not equal
        // removed other assertion

        // the two class objects have different classloaders
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // verify that objects of these two types are not assignment-compatible
        final Object obj1 = sharedClass.newInstance();
        final Object obj2 = componentClass.newInstance();

        // removed other assertion
        // removed other assertion
        assertFalse("Obj2 class incorrect", sharedClass.isInstance(obj2));
    }

    public void testBasicOperation_8_oe() throws Exception {
        final ClassLoader sharedLoader = this.getClass().getClassLoader();
        final ClassReloader componentLoader = new ClassReloader(sharedLoader);

        final Class<?> sharedClass = DummyClass.class;
        final Class<?> componentClass = componentLoader.reload(sharedClass);

        // the two Class objects contain the same bytecode, but are not equal
        // removed other assertion

        // the two class objects have different classloaders
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // verify that objects of these two types are not assignment-compatible
        final Object obj1 = sharedClass.newInstance();
        final Object obj2 = componentClass.newInstance();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Obj2 class incorrect", componentClass.isInstance(obj2));
    }

}

